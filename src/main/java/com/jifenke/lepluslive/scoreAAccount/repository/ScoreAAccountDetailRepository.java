package com.jifenke.lepluslive.scoreAAccount.repository;

import com.jifenke.lepluslive.scoreAAccount.domain.entities.ScoreAAccountDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2016/9/9.
 */
public interface ScoreAAccountDetailRepository extends JpaRepository<ScoreAAccountDetail,Long> {
  Page findAll(Specification<ScoreAAccountDetail> ScoreAAccountClause, Pageable pageRequest);
}
