 
package com.qa.service;

import com.qa.model.User;

public class UserService {

    public boolean isAdult(User user) {
        return user.getAge() >= 18;
    }

    public boolean isValidEmail(User user) {
        return user.getEmail() != null && user.getEmail().contains("@") && user.getEmail().contains(".");
    }

    public String getDisplayName(User user) {
        if (user.getFirstName() == null || user.getLastName() == null) {
            return "Unknown User";
        }
        return user.getFullName();
    }
}