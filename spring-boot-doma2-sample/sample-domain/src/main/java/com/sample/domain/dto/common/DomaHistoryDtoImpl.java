package com.sample.domain.dto.common;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
@Entity
@Setter
@Getter
public abstract class DomaHistoryDtoImpl extends DomaDtoImpl implements DomaHistoryDto, Serializable {

    // 更新項目名
    String changedPropertyNames;

    // 更新者ID
    Integer changedBy;

    // 更新日時
    LocalDateTime changedAt;
}
