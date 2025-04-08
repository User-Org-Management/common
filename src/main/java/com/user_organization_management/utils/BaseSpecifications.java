package com.user_organization_management.utils;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BaseSpecifications {

    public static <T> Specification<T> nameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(name)) {
                return criteriaBuilder.like(root.get("name"), "%" + name + "%");
            }
            return null;
        };
    }

    public static <T> Specification<T> emailContains(String email) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(email)) {
                return criteriaBuilder.like(root.get("email"), "%" + email + "%");
            }
            return null;
        };
    }

    public static <T> Specification<T> mobileContains(String mobile) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(mobile)) {
                return criteriaBuilder.like(root.get("mobile"), "%" + mobile + "%");
            }
            return null;
        };
    }

    public static <T> Specification<T> equalTo(String field, String value) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(value)) {
                return criteriaBuilder.equal(root.get(field), value);
            }
            return null;
        };
    }
}
