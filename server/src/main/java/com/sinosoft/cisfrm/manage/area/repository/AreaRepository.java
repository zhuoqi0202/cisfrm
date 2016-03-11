package com.sinosoft.cisfrm.manage.area.repository;

import com.sinosoft.cisfrm.manage.area.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

/**
 * Created by Dawn on 16/3/10.
 */
public interface AreaRepository extends JpaRepository<Area, Integer>, JpaSpecificationExecutor<Area> {
    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Area findByAreaCode(String areaCode);

    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Area findByAreaName(String areaName);
}
