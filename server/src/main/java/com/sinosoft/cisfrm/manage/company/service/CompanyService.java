package com.sinosoft.cisfrm.manage.company.service;

import com.sinosoft.cisfrm.manage.company.entity.Company;
import com.sinosoft.cisfrm.server.validation.CompanyCpycodeUnique;
import com.sinosoft.cisfrm.server.validation.CompanyCpynameUnique;
import com.sinosoft.cisfrm.server.vo.DataGridVo;
import org.springframework.validation.annotation.Validated;

/**
 * Created by Dawn on 16/2/24.
 */
@Validated
public interface CompanyService {
    DataGridVo find(Company company, int page, int rows);

    void add(Company company);

    void update(@CompanyCpycodeUnique(message = "{validation.company.cpycode.cpycodeunique}") @CompanyCpynameUnique(message = "{validation.company.cpyname.cpynameunique}") Company company);

    Company findByCpyCode(String cpyCode);

    void delete(int moduleId);

    Company findByCpyName(String value);
}
