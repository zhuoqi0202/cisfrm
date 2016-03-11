package com.sinosoft.cisfrm.manage.company.entity;

import com.sinosoft.cisfrm.server.validation.CpyCodeUnique;
import com.sinosoft.cisfrm.server.validation.CpyNameUnique;
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
import java.io.Serializable;

/**
 * Created by Dawn on 16/3/4.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entityCache")
@Cacheable(true)
@Entity
@Table(name = "CD_COMPANY")
public class Company implements Serializable {
    private int moduleId;
    @NotBlank(message = "{validation.company.cpycode.notblank}", groups = {Add.class})
    @CpyCodeUnique(message = "{validation.company.cpycode.cpycodeunique}", groups = {Add.class})
    private String cpyCode;
    @NotBlank(message = "{validation.company.cpyname.notblank}", groups = {Add.class})
    @CpyNameUnique(message = "{validation.company.cpyname.cpynameunique}", groups = {Add.class})
    private String cpyName;
    private String cpytype1code;
    private String cpytype1name;
    private String cpytype2code;
    private String cpytype2name;
    private String cptltypecode;
    private String cptltypename;
    private String isVirtual;
    private String validStatus;

    @Id
    @Column(name = "MODULEID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "CPYCODE")
    public String getCpyCode() {
        return cpyCode;
    }

    public void setCpyCode(String cpyCode) {
        this.cpyCode = cpyCode;
    }

    @Column(name = "CPYNAME")
    public String getCpyName() {
        return cpyName;
    }

    public void setCpyName(String cpyName) {
        this.cpyName = cpyName;
    }

    @Column(name = "CPYTYPE1CODE")
    public String getCpytype1code() {
        return cpytype1code;
    }

    public void setCpytype1code(String cpytype1code) {
        this.cpytype1code = cpytype1code;
    }

    @Column(name = "CPYTYPE1NAME")
    public String getCpytype1name() {
        return cpytype1name;
    }

    public void setCpytype1name(String cpytype1name) {
        this.cpytype1name = cpytype1name;
    }

    @Column(name = "CPYTYPE2CODE")
    public String getCpytype2code() {
        return cpytype2code;
    }

    public void setCpytype2code(String cpytype2code) {
        this.cpytype2code = cpytype2code;
    }

    @Column(name = "CPYTYPE2NAME")
    public String getCpytype2name() {
        return cpytype2name;
    }

    public void setCpytype2name(String cpytype2name) {
        this.cpytype2name = cpytype2name;
    }

    @Column(name = "CPTLTYPECODE")
    public String getCptltypecode() {
        return cptltypecode;
    }

    public void setCptltypecode(String cptltypecode) {
        this.cptltypecode = cptltypecode;
    }

    @Column(name = "CPTLTYPENAME")
    public String getCptltypename() {
        return cptltypename;
    }

    public void setCptltypename(String cptltypename) {
        this.cptltypename = cptltypename;
    }

    @Column(name = "ISVIRTUAL")
    public String getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(String isVirtual) {
        this.isVirtual = isVirtual;
    }

    @Column(name = "VALIDSTATUS")
    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (moduleId != company.moduleId) return false;
        if (cpyCode != null ? !cpyCode.equals(company.cpyCode) : company.cpyCode != null) return false;
        if (cpyName != null ? !cpyName.equals(company.cpyName) : company.cpyName != null) return false;
        if (cpytype1code != null ? !cpytype1code.equals(company.cpytype1code) : company.cpytype1code != null)
            return false;
        if (cpytype1name != null ? !cpytype1name.equals(company.cpytype1name) : company.cpytype1name != null)
            return false;
        if (cpytype2code != null ? !cpytype2code.equals(company.cpytype2code) : company.cpytype2code != null)
            return false;
        if (cpytype2name != null ? !cpytype2name.equals(company.cpytype2name) : company.cpytype2name != null)
            return false;
        if (cptltypecode != null ? !cptltypecode.equals(company.cptltypecode) : company.cptltypecode != null)
            return false;
        if (cptltypename != null ? !cptltypename.equals(company.cptltypename) : company.cptltypename != null)
            return false;
        if (isVirtual != null ? !isVirtual.equals(company.isVirtual) : company.isVirtual != null) return false;
        return validStatus != null ? validStatus.equals(company.validStatus) : company.validStatus == null;

    }

    @Override
    public int hashCode() {
        int result = moduleId;
        result = 31 * result + (cpyCode != null ? cpyCode.hashCode() : 0);
        result = 31 * result + (cpyName != null ? cpyName.hashCode() : 0);
        result = 31 * result + (cpytype1code != null ? cpytype1code.hashCode() : 0);
        result = 31 * result + (cpytype1name != null ? cpytype1name.hashCode() : 0);
        result = 31 * result + (cpytype2code != null ? cpytype2code.hashCode() : 0);
        result = 31 * result + (cpytype2name != null ? cpytype2name.hashCode() : 0);
        result = 31 * result + (cptltypecode != null ? cptltypecode.hashCode() : 0);
        result = 31 * result + (cptltypename != null ? cptltypename.hashCode() : 0);
        result = 31 * result + (isVirtual != null ? isVirtual.hashCode() : 0);
        result = 31 * result + (validStatus != null ? validStatus.hashCode() : 0);
        return result;
    }
}
