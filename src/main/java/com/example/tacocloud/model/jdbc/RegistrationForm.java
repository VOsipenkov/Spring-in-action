package com.example.tacocloud.model.jdbc;

import com.example.tacocloud.model.jpa.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        var user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullname);
        user.setStreet(street);
        user.setCity(city);
        user.setState(state);
        user.setZip(zip);
        user.setPhoneNumber(phone);
        return user;
    }
}
