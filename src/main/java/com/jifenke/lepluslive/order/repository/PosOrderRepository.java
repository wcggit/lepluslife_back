package com.jifenke.lepluslive.order.repository;

import com.jifenke.lepluslive.order.domain.entities.PosOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wcg on 16/8/2.
 */
public interface PosOrderRepository extends JpaRepository<PosOrder,Long> {

  PosOrder findByOrderSid(String orderNo);

  Page findAll(Specification<PosOrder> whereClause, Pageable pageRequest);
}
