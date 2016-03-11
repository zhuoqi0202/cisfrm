package com.sinosoft.cisfrm.server.controller.manage;

import com.sinosoft.cisfrm.manage.codetype.entity.CodeType;
import com.sinosoft.cisfrm.manage.codetype.service.CodeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by Dawn on 16/3/7.
 */
@Controller
@RequestMapping(value = "/manage/codetype")
public class CodeTypeController {
    @Autowired
    private CodeTypeService codeTypeService;

    @RequestMapping(value = "isValid/{codeType}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<CodeType> get(@PathVariable("codeType") String codeType) {
        List<CodeType> result = null;
        if (codeType.equalsIgnoreCase("orgType")) {
            result = codeTypeService.findOrgTypeIsValid();
        }
        if (codeType.equalsIgnoreCase("cpyType")) {
            result = codeTypeService.findCpyTypeIsValid();
        }
        if (codeType.equalsIgnoreCase("capitalType")) {
            result = codeTypeService.findCapitalTypeIsValid();
        }
        return result;
    }
}
