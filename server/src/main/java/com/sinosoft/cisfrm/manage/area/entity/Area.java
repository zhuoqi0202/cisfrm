package com.sinosoft.cisfrm.manage.area.entity;

import com.sinosoft.cisfrm.server.validation.group.Add;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Dawn on 16/2/24.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entityCache")
@Cacheable(true)
@Entity
@Table(name = "CD_AREA")
public class Area implements java.io.Serializable {
    private int moduleId;

    @NotBlank(message = "{validation.area.areacode.notblank}", groups = {Add.class})
    private String areaCode;

    @NotBlank(message = "{validation.area.areaname.notblank}", groups = {Add.class})
    private String areaName;

    @NotBlank(message = "{validation.area.singleName.notblank}", groups = {Add.class})
    private String singleName;

    private String validStatus;

    private String areaLevel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODULEID")
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "AREACODE")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Column(name = "AREANAME")
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Column(name = "SINGLENAME")
    public String getSingleName() {
        return singleName;
    }

    public void setSingleName(String singleName) {
        this.singleName = singleName;
    }


    @Column(name = "VALIDSTATUS")
    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    @Column(name = "AREALEVEL")
    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        if (moduleId != area.moduleId) return false;
        if (areaCode != null ? !areaCode.equals(area.areaCode) : area.areaCode != null) return false;
        if (areaName != null ? !areaName.equals(area.areaName) : area.areaName != null) return false;
        if (singleName != null ? !singleName.equals(area.singleName) : area.singleName != null) return false;
        if (validStatus != null ? !validStatus.equals(area.validStatus) : area.validStatus != null) return false;
        return areaLevel != null ? areaLevel.equals(area.areaLevel) : area.areaLevel == null;

    }

    @Override
    public int hashCode() {
        int result = moduleId;
        result = 31 * result + (areaCode != null ? areaCode.hashCode() : 0);
        result = 31 * result + (areaName != null ? areaName.hashCode() : 0);
        result = 31 * result + (singleName != null ? singleName.hashCode() : 0);
        result = 31 * result + (validStatus != null ? validStatus.hashCode() : 0);
        result = 31 * result + (areaLevel != null ? areaLevel.hashCode() : 0);
        return result;
    }
}
