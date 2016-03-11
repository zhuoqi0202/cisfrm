package com.sinosoft.cisfrm.manage.area.service.impl;

import com.google.common.collect.Lists;
import com.sinosoft.cisfrm.common.ValidatorUtil;
import com.sinosoft.cisfrm.manage.area.entity.Area;
import com.sinosoft.cisfrm.manage.area.repository.AreaRepository;
import com.sinosoft.cisfrm.manage.area.service.AreaService;
import com.sinosoft.cisfrm.server.vo.DataGridVo;
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
@Service("areaService")
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public DataGridVo find(Area area, int page, int rows) {
        PageRequest pageRequest = new PageRequest(page, rows, new Sort(Sort.Direction.ASC, "areaCode"));
        Page<Area> areaPage = areaRepository.findAll(new Specification<Area>() {
            @Override
            public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = Lists.newArrayList();
                if (area != null) {
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(area.getAreaCode())) {
                        list.add(cb.like(root.get("areaCode").as(String.class), "%" + area.getAreaCode() + "%"));
                    }
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(area.getAreaName())) {
                        list.add(cb.like(root.get("areaName").as(String.class), "%" + area.getAreaName() + "%"));
                    }
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(area.getSingleName())) {
                        list.add(cb.like(root.get("singleName").as(String.class), "%" + area.getSingleName() + "%"));
                    }
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(area.getAreaLevel())) {
                        list.add(cb.like(root.get("areaLevel").as(String.class), "%" + area.getAreaLevel() + "%"));
                    }
                    if (ValidatorUtil.isNotEmptyIgnoreBlank(area.getValidStatus())) {
                        list.add(cb.equal(root.get("validStatus"), area.getValidStatus()));
                    }
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        }, pageRequest);
        DataGridVo<Area> dataGridVo = new DataGridVo<>();
        dataGridVo.setTotal(areaPage.getTotalElements());
        dataGridVo.setRows(areaPage.getContent());
        return dataGridVo;
    }

    @Override
    @Transactional
    public void add(Area area) {
        areaRepository.save(area);
    }

    @Override
    @Transactional
    public void update(Area area) {
        Area areaUpdate = areaRepository.findOne(area.getModuleId());
        if (null != areaUpdate) {
            if (ValidatorUtil.isNotEmptyIgnoreBlank(area.getAreaCode())) {
                areaUpdate.setAreaCode(area.getAreaCode());
            }
            if (ValidatorUtil.isNotEmptyIgnoreBlank(area.getAreaName())) {
                areaUpdate.setAreaName(area.getAreaName());
            }
            if (ValidatorUtil.isNotEmptyIgnoreBlank(area.getSingleName())) {
                areaUpdate.setSingleName(area.getSingleName());
            }
            if (ValidatorUtil.isNotEmptyIgnoreBlank(area.getAreaLevel())) {
                areaUpdate.setAreaLevel(area.getAreaLevel());
            }
            if (ValidatorUtil.isNotEmptyIgnoreBlank(area.getValidStatus())) {
                areaUpdate.setValidStatus(area.getValidStatus());
            }
        }
        areaRepository.save(areaUpdate);
    }

    @Override
    @Transactional
    public void delete(int moduleId) {
        areaRepository.delete(moduleId);
    }

    @Override
    public Area find(int moduleId) {
        return areaRepository.findOne(moduleId);
    }

    @Override
    public Area findByAreaCode(String areaCode) {
        return areaRepository.findByAreaCode(areaCode);
    }

    @Override
    public Area findByAreaName(String areaName) {
        return areaRepository.findByAreaName(areaName);
    }
}
