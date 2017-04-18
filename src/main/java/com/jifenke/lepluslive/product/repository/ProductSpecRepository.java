package com.jifenke.lepluslive.product.repository;

import com.jifenke.lepluslive.product.domain.entities.Product;
import com.jifenke.lepluslive.product.domain.entities.ProductSpec;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wcg on 16/3/25.
 */
public interface ProductSpecRepository extends JpaRepository<ProductSpec, Integer> {

  List<ProductSpec> findAllByProduct(Product product);

  List<ProductSpec> findAllByProductAndState(Product product,int state);

  void deleteByProduct(Product product);

}
