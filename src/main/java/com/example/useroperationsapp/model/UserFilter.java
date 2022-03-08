package com.example.useroperationsapp.model;
import lombok.Getter;

@Getter
public enum UserFilter implements FilterEnum {
    BY_AGE("По возрасту"),
    BY_PHONE("По номеру телефона"),
    BY_NAME("По имени"),
    BY_EMAIL("По электронной почте");

    private final String filterName;

    UserFilter(String filterName) {
        this.filterName = filterName;
    }
}