package com.sinosoft.cisfrm.manage.codetype.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Dawn on 16/3/6.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entityCache")
@Cacheable(true)
@Entity
@Table(name = "CD_CODETYPE")
public class CodeType implements Serializable {
    private int moduleId;
    private String codeType;
    private String codeCode;
    private String typeName;
    private String codeCName;
    private String validStatus;
    private Date validDate;
    private Date invalidDate;
    private String flag;
    private String rmak;

    @Id
    @Column(name = "MODULEID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMODULEID() {
        return moduleId;
    }

    public void setMODULEID(int MODULEID) {
        this.moduleId = MODULEID;
    }

    @Column(name = "CodeType")
    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    @Column(name = "CodeCode")
    public String getCodeCode() {
        return codeCode;
    }

    public void setCodeCode(String codeCode) {
        this.codeCode = codeCode;
    }

    @Column(name = "TypeName")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Column(name = "CodeCName")
    public String getCodeCName() {
        return codeCName;
    }

    public void setCodeCName(String codeCName) {
        this.codeCName = codeCName;
    }

    @Column(name = "ValidStatus")
    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    @Column(name = "ValidDate")
    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    @Column(name = "InvalidDate")
    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    @Column(name = "Flag")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Column(name = "Rmak")
    public String getRmak() {
        return rmak;
    }

    public void setRmak(String rmak) {
        this.rmak = rmak;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CodeType codeType1 = (CodeType) o;

        if (moduleId != codeType1.moduleId) return false;
        if (codeType != null ? !codeType.equals(codeType1.codeType) : codeType1.codeType != null) return false;
        if (codeCode != null ? !codeCode.equals(codeType1.codeCode) : codeType1.codeCode != null) return false;
        if (typeName != null ? !typeName.equals(codeType1.typeName) : codeType1.typeName != null) return false;
        if (codeCName != null ? !codeCName.equals(codeType1.codeCName) : codeType1.codeCName != null) return false;
        if (validStatus != null ? !validStatus.equals(codeType1.validStatus) : codeType1.validStatus != null)
            return false;
        if (validDate != null ? !validDate.equals(codeType1.validDate) : codeType1.validDate != null) return false;
        if (invalidDate != null ? !invalidDate.equals(codeType1.invalidDate) : codeType1.invalidDate != null)
            return false;
        if (flag != null ? !flag.equals(codeType1.flag) : codeType1.flag != null) return false;
        return rmak != null ? rmak.equals(codeType1.rmak) : codeType1.rmak == null;

    }

    @Override
    public int hashCode() {
        int result = moduleId;
        result = 31 * result + (codeType != null ? codeType.hashCode() : 0);
        result = 31 * result + (codeCode != null ? codeCode.hashCode() : 0);
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + (codeCName != null ? codeCName.hashCode() : 0);
        result = 31 * result + (validStatus != null ? validStatus.hashCode() : 0);
        result = 31 * result + (validDate != null ? validDate.hashCode() : 0);
        result = 31 * result + (invalidDate != null ? invalidDate.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (rmak != null ? rmak.hashCode() : 0);
        return result;
    }
}
