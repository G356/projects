package com.sample.domain.repository.system;

import com.sample.domain.dao.system.RoleDao;
import com.sample.domain.dao.system.RolePermissionDao;
import com.sample.domain.dto.common.Page;
import com.sample.domain.dto.common.Pageable;
import com.sample.domain.dto.system.Role;
import com.sample.domain.dto.system.RoleCriteria;
import com.sample.domain.dto.system.RolePermission;
import com.sample.domain.dto.system.RolePermissionCriteria;
import com.sample.domain.exception.NoDataFoundException;
import com.sample.domain.service.BaseRepository;
import lombok.val;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

import static com.sample.common.util.ValidateUtils.isNotEmpty;
import static com.sample.common.util.ValidateUtils.isTrue;
import static com.sample.domain.util.DomaUtils.createSelectOptions;
import static java.util.stream.Collectors.toList;

/**
 * 役割リポジトリ
 */
@Repository
public class RoleRepository extends BaseRepository {

    @Resource
    RoleDao roleDao;

    @Resource
    RolePermissionDao rolePermissionDao;

    /**
     * 役割を複数取得します。
     *
     * @param criteria
     * @param pageable
     * @return
     */
    public Page<Role> findAll(RoleCriteria criteria, Pageable pageable) {
        // ページングを指定する
        val options = createSelectOptions(pageable).count();
        val data = roleDao.selectAll(criteria, options, toList());
        return pageFactory.create(data, pageable, options.getCount());
    }

    /**
     * 役割を取得します。
     *
     * @param criteria
     * @return
     */
    public Optional<Role> findOne(RoleCriteria criteria) {
        // 1件取得
        val role = roleDao.select(criteria);

        role.ifPresent(r -> {
            val rolePermissions = findRolePermissions(r);
            if (isNotEmpty(rolePermissions)) {
                Map<Integer, Boolean> permissions = new HashMap<>();
                rolePermissions.forEach(rp -> permissions.putIfAbsent(rp.getPermissionId(), true));
                r.setPermissions(permissions);
            }
        });

        return role;
    }

    /**
     * 役割を取得します。
     *
     * @return
     */
    public Role findById(final Long id) {
        // 1件取得
        val role = roleDao.selectById(id)
                .orElseThrow(() -> new NoDataFoundException("role_id=" + id + " のデータが見つかりません。"));

        val rolePermissions = findRolePermissions(role);
        if (isNotEmpty(rolePermissions)) {
            Map<Integer, Boolean> permissions = new HashMap<>();
            rolePermissions.forEach(rp -> permissions.putIfAbsent(rp.getPermissionId(), true));
            role.setPermissions(permissions);
        }

        return role;
    }

    /**
     * 役割を追加します。
     *
     * @param inputRole
     * @return
     */
    public Role create(final Role inputRole) {
        // 1件登録
        roleDao.insert(inputRole);

        // 役割権限紐付けを登録する
        insertRolePermissions(inputRole);

        return inputRole;
    }

    /**
     * 役割を更新します。
     *
     * @param inputRole
     * @return
     */
    public Role update(final Role inputRole) {
        // 1件更新
        int updated = roleDao.update(inputRole);

        if (updated < 1) {
            throw new NoDataFoundException("role_id=" + inputRole.getId() + " のデータが見つかりません。");
        }

        // 役割権限紐付けを論理削除する
        deleteRolePermissions(inputRole);

        // 役割権限紐付けを登録する
        insertRolePermissions(inputRole);

        return inputRole;
    }

    /**
     * 役割を論理削除します。
     *
     * @return
     */
    public Role delete(final Long id) {
        val role = roleDao.selectById(id)
                .orElseThrow(() -> new NoDataFoundException("role_id=" + id + " のデータが見つかりません。"));

        int updated = roleDao.delete(role);

        if (updated < 1) {
            throw new NoDataFoundException("role_id=" + id + " は更新できませんでした。");
        }

        // 役割権限紐付けを論理削除する
        deleteRolePermissions(role);

        return role;
    }

    /**
     * 役割権限紐付けを登録する
     *
     * @param inputRole
     */
    protected void insertRolePermissions(final Role inputRole) {
        // 入力値がない場合はスキップする
        if (isNotEmpty(inputRole.getPermissions())) {
            List<RolePermission> rolePermissionsToInsert = new ArrayList<>();

            // 権限のチェックがある場合
            inputRole.getPermissions().forEach((key, value) -> {
                // チェックされている
                if (isTrue(value)) {
                    val rolePermission = new RolePermission();
                    rolePermission.setRoleKey(inputRole.getRoleKey());
                    rolePermission.setPermissionId(key);
                    rolePermissionsToInsert.add(rolePermission);
                }
            });

            // 一括登録
            rolePermissionDao.insert(rolePermissionsToInsert);
        }
    }

    /**
     * 役割権限紐付けを論理削除する
     *
     * @param inputRole
     */
    protected void deleteRolePermissions(final Role inputRole) {
        List<RolePermission> rolePermissionsToDelete = findRolePermissions(inputRole);

        if (isNotEmpty(rolePermissionsToDelete)) {
            rolePermissionDao.delete(rolePermissionsToDelete);// 一括論理削除
        }
    }

    /**
     * 役割権限紐付けを取得する
     *
     * @param inputRole
     * @return
     */
    protected List<RolePermission> findRolePermissions(Role inputRole) {
        // 役割権限紐付けを役割キーで取得する
        val criteria = new RolePermissionCriteria();
        criteria.setRoleKey(inputRole.getRoleKey());

        val options = createSelectOptions(Pageable.NO_LIMIT);
        return rolePermissionDao.selectAll(criteria, options, toList());
    }
}
