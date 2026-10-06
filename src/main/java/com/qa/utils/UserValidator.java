package com.qa.utils;

import com.qa.model.User;

public class UserValidator {

    public static boolean isValidUser(User user) {
        if (user == null) {
            return false;
        }

        if (ValidationUtils.isNullOrEmpty(user.getFirstName()) ||
            ValidationUtils.isNullOrEmpty(user.getLastName())) {
            return false;
        }

        if (!ValidationUtils.isValidAge(user.getAge())) {
            return false;
        }

        return true;
    }
}