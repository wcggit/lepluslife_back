package com.jifenke.lepluslive.groupon.repository;

import com.jifenke.lepluslive.groupon.domain.entities.GrouponCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 团购券码 Repository
 * Created by xf on 17-6-16.
 */
public interface GrouponCodeRepository extends JpaRepository<GrouponCode,Long> {

}
