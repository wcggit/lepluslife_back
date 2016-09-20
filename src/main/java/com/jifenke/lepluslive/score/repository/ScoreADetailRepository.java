package com.jifenke.lepluslive.score.repository;

import com.jifenke.lepluslive.score.domain.entities.ScoreA;
import com.jifenke.lepluslive.score.domain.entities.ScoreADetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wcg on 16/3/18.
 */
public interface ScoreADetailRepository extends JpaRepository<ScoreADetail,Long>{

  List<ScoreADetail> findAllByScoreA(ScoreA scoreA);

  Page findAll(Specification<ScoreADetail> whereClause, Pageable pageRequest);

  @Query(value = "SELECT * FROM scorea_detail WHERE DATE_FORMAT(date_created,'%Y%m%d')=?1 ", nativeQuery = true)
  List<ScoreADetail> findAllByDate(String dateStr);
}
