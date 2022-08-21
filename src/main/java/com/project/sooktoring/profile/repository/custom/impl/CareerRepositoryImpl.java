package com.project.sooktoring.profile.repository.custom.impl;

import com.project.sooktoring.profile.repository.custom.CareerRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.project.sooktoring.profile.domain.QCareer.career;


@RequiredArgsConstructor
public class CareerRepositoryImpl implements CareerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteByIdNotInBatch(Long userId, List<Long> ids) {
        queryFactory
                .delete(career)
                .where(
                        career.profile.id.eq(userId)
                                .and(career.id.notIn(ids))
                )
                .execute();
    }
}