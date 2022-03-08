package com.example.useroperationsapp.repository;

import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface SetQueryParams {

    default void setParamQueryStrLike(
            List<Predicate> predicates,
            Root<?> root,
            CriteriaBuilder cb,
            String columnName,
            String paramQuery
    ) {
        if (!StringUtils.isEmpty(paramQuery)) {
            predicates.add(cb.like(
                    cb.lower(root.get(columnName)),
                    cb.lower(cb.literal("%" + paramQuery  + "%"))
            ));
        }
    }

    default void setParamQueryChar(
            List<Predicate> predicates,
            Root<?> root,
            CriteriaBuilder cb,
            String columnName,
            char paramQuery
    ) {
        if (paramQuery != ' ') {
            predicates.add(cb.and(cb.equal(
                    root.get(columnName),
                    paramQuery)
            ));
        }
    }

    default void setParamQueryStrLike(
            List<Predicate> predicates,
            Root<?> root,
            CriteriaBuilder cb,
            String relationName,
            String columnName,
            String paramQuery
    ) {
        if (!StringUtils.isEmpty(paramQuery)) {
            predicates.add(cb.like(
                    cb.lower(root.get(relationName).get(columnName)),
                    cb.lower(cb.literal("%" + paramQuery  + "%"))
            ));
        }
    }

    default void setParamQueryLong(
            List<Predicate> predicates,
            Root<?> root,
            CriteriaBuilder cb,
            String columnName,
            Long paramQuery
    ) {
        if (paramQuery != null) {
            predicates.add(cb.and(cb.equal(
                    root.get(columnName),
                    paramQuery
            )));
        }
    }

    default void setParamQueryInteger(
            List<Predicate> predicates,
            Root<?> root,
            CriteriaBuilder cb,
            String columnName,
            Integer paramQuery
    ) {
        if (paramQuery != null) {
            predicates.add(cb.and(cb.equal(
                    root.get(columnName),
                    paramQuery
            )));
        }
    }

    default void setParamQueryLong(
            List<Predicate> predicates,
            Root<?> root,
            CriteriaBuilder cb,
            String columnName,
            long paramQuery
    ) {
        if (paramQuery > 0) {
            predicates.add(cb.and(cb.equal(
                    root.get(columnName),
                    paramQuery
            )));
        }
    }

    default void setParamQueryDouble(
            List<Predicate> predicates,
            Root<?> root,
            CriteriaBuilder cb,
            String columnName,
            double paramQuery
    ) {
        if (paramQuery > 0) {
            predicates.add(cb.and(cb.equal(
                    root.get(columnName),
                    paramQuery
            )));
        }
    }

    default void setParamQueryString(
            List<Predicate> predicates,
            Root<?> root,
            CriteriaBuilder cb,
            String columnName,
            String paramQuery
    ) {
        if (!StringUtils.isEmpty(paramQuery)) {
            predicates.add(cb.like(
                    cb.lower(root.get(columnName)),
                    cb.lower(cb.literal(paramQuery))
            ));
        }
    }
}
