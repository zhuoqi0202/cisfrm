package com.sinosoft.cisfrm.manage.codetype.repository;

import com.sinosoft.cisfrm.manage.codetype.entity.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by Dawn on 16/3/7.
 */
public interface CodeTypeRepository extends JpaRepository<CodeType, Integer> {
    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}, forCounting = false)
    List<CodeType> findByCodeTypeAndValidStatus(String codeType, String validStatus);
}
