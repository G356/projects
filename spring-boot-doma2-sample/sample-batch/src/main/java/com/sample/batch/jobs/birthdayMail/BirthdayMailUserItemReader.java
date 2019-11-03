package com.sample.batch.jobs.birthdayMail;

import com.sample.batch.jobs.BasePageableItemReader;
import com.sample.domain.dao.users.UserDao;
import com.sample.domain.dto.user.User;
import com.sample.domain.dto.user.UserCriteria;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 誕生メールの対象を検索する
 */
public class BirthdayMailUserItemReader extends BasePageableItemReader<User> {

    @Autowired
    UserDao userDao;

    @Override
    protected List<User> getList() {
        val criteria = new UserCriteria();
        val options = getSelectOptions();
        return userDao.selectAll(criteria, options, toList());
    }
}
