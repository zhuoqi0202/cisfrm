package com.sinosoft.cisfrm.server.controller.manage;

import com.alibaba.fastjson.JSON;
import com.sinosoft.cisfrm.common.ValidatorUtil;
import com.sinosoft.cisfrm.manage.area.entity.Area;
import com.sinosoft.cisfrm.manage.area.service.AreaService;
import com.sinosoft.cisfrm.manage.company.entity.Company;
import com.sinosoft.cisfrm.server.component.message.Message;
import com.sinosoft.cisfrm.server.component.message.MessageFactory;
import com.sinosoft.cisfrm.server.validation.AreaCodeUnique;
import com.sinosoft.cisfrm.server.validation.AreaNameUnique;
import com.sinosoft.cisfrm.server.validation.group.Add;
import com.sinosoft.cisfrm.server.validation.group.Update;
import com.sinosoft.cisfrm.server.vo.DataGridVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Dawn on 16/2/24.
 */
@Controller()
@RequestMapping(path = "/manage/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public DataGridVo<Area> get(String data, int page, int rows) {
        Area area = null;
        if (ValidatorUtil.isNotEmptyIgnoreBlank(data)) {
            area = JSON.parseObject(data, Area.class);
        }
        return areaService.find(area, page - 1, rows);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message add(@Validated(Add.class) @RequestBody Area area) {
        areaService.add(area);
        return MessageFactory.success();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message update(@RequestBody Area area) {
        areaService.update(area);
        return MessageFactory.success();
    }

    @RequestMapping(value = "{moduleId}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message delete(@PathVariable("moduleId") int moduleId) {
        areaService.delete(moduleId);
        return MessageFactory.success();
    }
}
