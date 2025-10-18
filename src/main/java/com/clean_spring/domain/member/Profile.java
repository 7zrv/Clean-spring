package com.clean_spring.domain.member;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public record Profile(String address) {
    private static final Pattern PROFILE_ADDRESS_PATTERN =
            Pattern.compile("^[a-z0-9_-]+$");

    public Profile {
        if (!PROFILE_ADDRESS_PATTERN.matcher(address).matches()) {
            throw new IllegalArgumentException("프로필 주소 형식이 바르지 않습니다" + address);
        }

        if (address.length() > 155) {
            throw new IllegalArgumentException("프로필 주소는 155자를 넘길 수 없습니다. " + address);
        }
    }

    public String url() {
        return "@" + address;
    }


}
