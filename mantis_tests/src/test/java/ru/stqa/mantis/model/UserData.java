package ru.stqa.mantis.model;

import io.swagger.client.model.AccessLevel;

public record UserData(String username, String password, String real_name, String email, AccessLevel access_level,
                       Boolean enabled, Boolean protectet) {

    public UserData() {
        this("", "", "", "", new AccessLevel().name(""), true, false);
    }

    public UserData withUsername(String username) {
        return new UserData(username, this.password, this.real_name, this.email, this.access_level, this.enabled, this.protectet);
    }

    public UserData withPassword(String password) {
        return new UserData(this.username, password, this.real_name, this.email, this.access_level, this.enabled, this.protectet);
    }

    public UserData withRealName(String realName) {
        return new UserData(this.username, this.password, real_name, this.email, this.access_level, this.enabled, this.protectet);
    }

    public UserData withEmail(String email) {
        return new UserData(this.username, this.password, this.real_name, email, this.access_level, this.enabled, this.protectet);
    }

    public UserData withAccessLevel(AccessLevel accessLevel) {
        return new UserData(this.username, this.password, this.real_name, this.email, access_level, this.enabled, this.protectet);
    }

    public UserData withEnabled(Boolean enabled) {
        return new UserData(this.username, this.password, this.real_name, this.email, this.access_level, enabled, this.protectet);
    }

    public UserData withProtected(Boolean protectet) {
        return new UserData(this.username, this.password, this.real_name, this.email, this.access_level, this.enabled, protectet);
    }
}
