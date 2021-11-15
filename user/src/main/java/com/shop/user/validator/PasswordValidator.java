package com.shop.user.validator;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "confirmed password is different than password";

    Class<?>[] groups() default {
    };

    Class<? extends Payload>[] payload() default {};
}
