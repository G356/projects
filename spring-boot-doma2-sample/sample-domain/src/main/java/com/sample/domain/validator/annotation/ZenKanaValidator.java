package com.sample.domain.validator.annotation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 入力チェック（全角カナ）
 */
public class ZenKanaValidator implements ConstraintValidator<ZenKana, String> {

    static final Pattern p = Pattern.compile("^[ァ-ヶ]+$");

    @Override
    public void initialize(ZenKana ZenKana) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = false;

        if (StringUtils.isEmpty(value)) {
            isValid = true;
        } else {
            Matcher m = p.matcher(value);

            if (m.matches()) {
                isValid = true;
            }
        }

        return isValid;
    }
}
