package com.sinosoft.cisfrm.manage.company.repository;

import com.sinosoft.cisfrm.manage.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

/**
 * Created by Dawn on 16/3/6.
 */
public interface CompanyRepository extends JpaRepository<Company, Integer>, JpaSpecificationExecutor<Company> {
    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}, forCounting = false)
    Company findByCpyCode(String cpyCode);

    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}, forCounting = false)
    Company findByCpyName(String cpyName);
}
