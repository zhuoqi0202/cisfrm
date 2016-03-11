package com.sinosoft.cisfrm.manage.company.service.impl;

import com.google.common.collect.Lists;
import com.sinosoft.cisfrm.common.ValidatorUtil;
import com.sinosoft.cisfrm.manage.company.entity.Company;
import com.sinosoft.cisfrm.manage.company.repository.CompanyRepository;
import com.sinosoft.cisfrm.manage.company.service.CompanyService;
import com.sinosoft.cisfrm.server.vo.DataGridVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Dawn on 16/3/7.
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public DataGridVo find(Company company, int page, int rows) {
        PageRequest pageRequest = new PageRequest(page, rows, new Sort(Sort.Direction.ASC, "cpyCode"));
        Page<Company> companyPage = companyRepository.findAll(new Specification<Company>() {
            @Override
            public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = Lists.newArrayList();
                if (null != company) {
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getCpyCode())) {
                        list.add(cb.like(root.get("cpyCode").as(String.class), "%" + company.getCpyCode() + "%"));
                    }
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getCpyName())) {
                        list.add(cb.like(root.get("cpyName").as(String.class), "%" + company.getCpyName() + "%"));
                    }
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getCpytype1code())) {
                        list.add(cb.equal(root.get("cpytype1code"), company.getCpytype1code()));
                    }
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getCpytype2code())) {
                        list.add(cb.equal(root.get("cpytype2code"), company.getCpytype2code()));
                    }
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getCptltypecode())) {
                        list.add(cb.equal(root.get("cptltypecode"), company.getCptltypecode()));
                    }
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getIsVirtual())) {
                        list.add(cb.equal(root.get("isVirtual"), company.getIsVirtual()));
                    }
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getValidStatus())) {
                        list.add(cb.equal(root.get("validStatus"), company.getValidStatus()));
                    }
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        }, pageRequest);
        DataGridVo<Company> dataGridVo = new DataGridVo<>();
        dataGridVo.setTotal(companyPage.getTotalElements());
        dataGridVo.setRows(companyPage.getContent());
        return dataGridVo;
    }

    @Override
    @Transactional
    public void add(Company company) {
        companyRepository.save(company);
    }

    @Override
    @Transactional
    public void update(Company company) {
        Company companyUpdate = companyRepository.findOne(company.getModuleId());
        if (null != companyUpdate) {
            if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getCpyCode())) {
                companyUpdate.setCpyCode(company.getCpyCode());
            }
            if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getCpyName())) {
                companyUpdate.setCpyName(company.getCpyName());
            }
            if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getCpytype1code())) {
                companyUpdate.setCpytype1code(company.getCpytype1code());
                companyUpdate.setCpytype1name(company.getCpytype1name());
            }
            if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getCptltypecode())) {
                companyUpdate.setCptltypecode(company.getCptltypecode());
                companyUpdate.setCptltypename(company.getCptltypename());
            }
            if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getCpytype2code())) {
                companyUpdate.setCpytype2code(company.getCpytype2code());
                companyUpdate.setCpytype2name(company.getCpytype2name());
            }
            if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getIsVirtual())) {
                companyUpdate.setIsVirtual(company.getIsVirtual());
            }
            if (ValidatorUtil.isNotEmptyIgnoreBlank(company.getValidStatus())) {
                companyUpdate.setValidStatus(company.getValidStatus());
            }
            companyRepository.save(companyUpdate);
        }
    }

    @Override
    public Company findByCpyCode(String cpyCode) {
        return companyRepository.findByCpyCode(cpyCode);
    }

    @Override
    @Transactional
    public void delete(int moduleId) {
        companyRepository.delete(moduleId);
    }

    @Override
    public Company findByCpyName(String value) {
        return companyRepository.findByCpyName(value);
    }
}
