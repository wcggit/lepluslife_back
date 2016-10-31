package com.jifenke.lepluslive.merchant.service;

import com.jifenke.lepluslive.merchant.domain.entities.MerchantScroll;
import com.jifenke.lepluslive.merchant.repository.MerchantScrollRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by wcg on 16/6/8.
 */
@Service
@Transactional(readOnly = true)
public class MerchantScrollService {

  @Inject
  private MerchantScrollRepository merchantScrollRepository;

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Page findScorllPicByPage(Integer offset, Long merchantId) {
    Sort sort = new Sort(Sort.Direction.ASC, "sid");
    return merchantScrollRepository
        .findAll(getWhereClause(merchantId), new PageRequest(offset - 1, 10, sort));
  }

  private static Specification<MerchantScroll> getWhereClause(Long merchantId) {
    return new Specification<MerchantScroll>() {
      @Override
      public Predicate toPredicate(Root<MerchantScroll> r, CriteriaQuery<?> q,
                                   CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        predicate.getExpressions().add(
            cb.equal(r.get("merchant").get("id"),
                     merchantId));
        return predicate;
      }
    };
  }


  public MerchantScroll findScrollPictureById(Long id) {
    return merchantScrollRepository.findOne(id);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void editScrollPicture(MerchantScroll merchantScroll) {
    MerchantScroll origin = null;
    if (merchantScroll.getId() != null) {
      origin = merchantScrollRepository.findOne(merchantScroll.getId());
    } else {
      origin = new MerchantScroll();
    }
    origin.setPicture(merchantScroll.getPicture());
    origin.setSid(merchantScroll.getSid());
    origin.setMerchant(merchantScroll.getMerchant());
    merchantScrollRepository.save(origin);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void deleteScrollPicture(Long id) {
    merchantScrollRepository.delete(id);
  }

}
