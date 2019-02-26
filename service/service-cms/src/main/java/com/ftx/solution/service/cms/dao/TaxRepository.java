package com.ftx.solution.service.cms.dao;

import com.ftx.solution.service.cms.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author puan
 * @date 2018-11-20 15:05
 **/
@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {

}
