package com.sinosoft.cisfrm.manage.area.service;

import com.sinosoft.cisfrm.manage.area.entity.Area;
import com.sinosoft.cisfrm.server.validation.AreaCodeUnique;
import com.sinosoft.cisfrm.server.validation.AreaNameUnique;
import com.sinosoft.cisfrm.server.vo.DataGridVo;
import org.springframework.validation.annotation.Validated;

/**
 * Created by Dawn on 16/2/24.
 */
@Validated
public interface AreaService {
    DataGridVo find(Area area, int page, int rows);

    void add(@AreaCodeUnique(message = "{validation.area.areacode.areacodeunique}") @AreaNameUnique(message = "{validation.area.areaname.areanameunique}") Area area);

    void update(@AreaCodeUnique(message = "{validation.area.areacode.areacodeunique}") @AreaNameUnique(message = "{validation.area.areaname.areanameunique}") Area area);

    void delete(int moduleId);

    Area find(int moduleId);

    Area findByAreaCode(String areaCode);

    Area findByAreaName(String areaName);
}
