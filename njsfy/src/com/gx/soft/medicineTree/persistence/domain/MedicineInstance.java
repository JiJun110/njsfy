package com.gx.soft.medicineTree.persistence.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by adminstrator on 2019/9/17.
 */
@Entity
@Table(name = "medicine_instance", schema = "njsfy", catalog = "")
public class MedicineInstance {
    private String rowId;
    private String medicineName;
    private String medicineGuige;
    private Double price;
    private String changShang;
    private String medicineType;
    private String fileName;
    private String warn;
    private String syz;
    private String yfyl;
    private String brqAqdj;
    private String yqAqdj;
    private String isJy;
    private String isGwy;
    private String ybType;
    private String ypSsMl;
    private String tsCcTj;
    private String cSmSy;
    private Integer isCfy;
    private Integer isBrq;
    private String aqdj;
    private Timestamp createTime;
    private Timestamp uploadTime;
    private String createUser;
    private String uploadUser;
    private String ext2;
    private String ext3;
    private String ext4;
    private String medicineTypeName;

    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "row_id", unique = true, nullable = false, length = 40)
    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    @Basic
    @Column(name = "medicine_name")
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @Basic
    @Column(name = "medicine_guige")
    public String getMedicineGuige() {
        return medicineGuige;
    }

    public void setMedicineGuige(String medicineGuige) {
        this.medicineGuige = medicineGuige;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "chang_shang")
    public String getChangShang() {
        return changShang;
    }

    public void setChangShang(String changShang) {
        this.changShang = changShang;
    }

    @Basic
    @Column(name = "medicine_type")
    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    @Basic
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "warn")
    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    @Basic
    @Column(name = "syz")
    public String getSyz() {
        return syz;
    }

    public void setSyz(String syz) {
        this.syz = syz;
    }

    @Basic
    @Column(name = "yfyl")
    public String getYfyl() {
        return yfyl;
    }

    public void setYfyl(String yfyl) {
        this.yfyl = yfyl;
    }

    @Basic
    @Column(name = "brq_aqdj")
    public String getBrqAqdj() {
        return brqAqdj;
    }

    public void setBrqAqdj(String brqAqdj) {
        this.brqAqdj = brqAqdj;
    }

    @Basic
    @Column(name = "yq_aqdj")
    public String getYqAqdj() {
        return yqAqdj;
    }

    public void setYqAqdj(String yqAqdj) {
        this.yqAqdj = yqAqdj;
    }

    @Basic
    @Column(name = "is_jy")
    public String getIsJy() {
        return isJy;
    }

    public void setIsJy(String isJy) {
        this.isJy = isJy;
    }

    @Basic
    @Column(name = "is_gwy")
    public String getIsGwy() {
        return isGwy;
    }

    public void setIsGwy(String isGwy) {
        this.isGwy = isGwy;
    }

    @Basic
    @Column(name = "yb_type")
    public String getYbType() {
        return ybType;
    }

    public void setYbType(String ybType) {
        this.ybType = ybType;
    }

    @Basic
    @Column(name = "yp_ss_ml")
    public String getYpSsMl() {
        return ypSsMl;
    }

    public void setYpSsMl(String ypSsMl) {
        this.ypSsMl = ypSsMl;
    }

    @Basic
    @Column(name = "ts_cc_tj")
    public String getTsCcTj() {
        return tsCcTj;
    }

    public void setTsCcTj(String tsCcTj) {
        this.tsCcTj = tsCcTj;
    }

    @Basic
    @Column(name = "c_sm_sy")
    public String getcSmSy() {
        return cSmSy;
    }

    public void setcSmSy(String cSmSy) {
        this.cSmSy = cSmSy;
    }

    @Basic
    @Column(name = "is_cfy")
    public Integer getIsCfy() {
        return isCfy;
    }

    public void setIsCfy(Integer isCfy) {
        this.isCfy = isCfy;
    }

    @Basic
    @Column(name = "is_brq")
    public Integer getIsBrq() {
        return isBrq;
    }

    public void setIsBrq(Integer isBrq) {
        this.isBrq = isBrq;
    }

    @Basic
    @Column(name = "aqdj")
    public String getAqdj() {
        return aqdj;
    }

    public void setAqdj(String aqdj) {
        this.aqdj = aqdj;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "upload_time")
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "create_user")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "upload_user")
    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    @Basic
    @Column(name = "ext2")
    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    @Basic
    @Column(name = "ext3")
    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    @Basic
    @Column(name = "ext4")
    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    @Basic
    @Column(name = "medicine_type_name")
    public String getMedicineTypeName() {
        return medicineTypeName;
    }

    public void setMedicineTypeName(String medicineTypeName) {
        this.medicineTypeName = medicineTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineInstance that = (MedicineInstance) o;

        if (rowId != null ? !rowId.equals(that.rowId) : that.rowId != null) return false;
        if (medicineName != null ? !medicineName.equals(that.medicineName) : that.medicineName != null) return false;
        if (medicineGuige != null ? !medicineGuige.equals(that.medicineGuige) : that.medicineGuige != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (changShang != null ? !changShang.equals(that.changShang) : that.changShang != null) return false;
        if (medicineType != null ? !medicineType.equals(that.medicineType) : that.medicineType != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (warn != null ? !warn.equals(that.warn) : that.warn != null) return false;
        if (syz != null ? !syz.equals(that.syz) : that.syz != null) return false;
        if (yfyl != null ? !yfyl.equals(that.yfyl) : that.yfyl != null) return false;
        if (brqAqdj != null ? !brqAqdj.equals(that.brqAqdj) : that.brqAqdj != null) return false;
        if (yqAqdj != null ? !yqAqdj.equals(that.yqAqdj) : that.yqAqdj != null) return false;
        if (isJy != null ? !isJy.equals(that.isJy) : that.isJy != null) return false;
        if (isGwy != null ? !isGwy.equals(that.isGwy) : that.isGwy != null) return false;
        if (ybType != null ? !ybType.equals(that.ybType) : that.ybType != null) return false;
        if (ypSsMl != null ? !ypSsMl.equals(that.ypSsMl) : that.ypSsMl != null) return false;
        if (tsCcTj != null ? !tsCcTj.equals(that.tsCcTj) : that.tsCcTj != null) return false;
        if (cSmSy != null ? !cSmSy.equals(that.cSmSy) : that.cSmSy != null) return false;
        if (isCfy != null ? !isCfy.equals(that.isCfy) : that.isCfy != null) return false;
        if (isBrq != null ? !isBrq.equals(that.isBrq) : that.isBrq != null) return false;
        if (aqdj != null ? !aqdj.equals(that.aqdj) : that.aqdj != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        if (createUser != null ? !createUser.equals(that.createUser) : that.createUser != null) return false;
        if (uploadUser != null ? !uploadUser.equals(that.uploadUser) : that.uploadUser != null) return false;
        if (ext2 != null ? !ext2.equals(that.ext2) : that.ext2 != null) return false;
        if (ext3 != null ? !ext3.equals(that.ext3) : that.ext3 != null) return false;
        if (ext4 != null ? !ext4.equals(that.ext4) : that.ext4 != null) return false;
        if (medicineTypeName != null ? !medicineTypeName.equals(that.medicineTypeName) : that.medicineTypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rowId != null ? rowId.hashCode() : 0;
        result = 31 * result + (medicineName != null ? medicineName.hashCode() : 0);
        result = 31 * result + (medicineGuige != null ? medicineGuige.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (changShang != null ? changShang.hashCode() : 0);
        result = 31 * result + (medicineType != null ? medicineType.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (warn != null ? warn.hashCode() : 0);
        result = 31 * result + (syz != null ? syz.hashCode() : 0);
        result = 31 * result + (yfyl != null ? yfyl.hashCode() : 0);
        result = 31 * result + (brqAqdj != null ? brqAqdj.hashCode() : 0);
        result = 31 * result + (yqAqdj != null ? yqAqdj.hashCode() : 0);
        result = 31 * result + (isJy != null ? isJy.hashCode() : 0);
        result = 31 * result + (isGwy != null ? isGwy.hashCode() : 0);
        result = 31 * result + (ybType != null ? ybType.hashCode() : 0);
        result = 31 * result + (ypSsMl != null ? ypSsMl.hashCode() : 0);
        result = 31 * result + (tsCcTj != null ? tsCcTj.hashCode() : 0);
        result = 31 * result + (cSmSy != null ? cSmSy.hashCode() : 0);
        result = 31 * result + (isCfy != null ? isCfy.hashCode() : 0);
        result = 31 * result + (isBrq != null ? isBrq.hashCode() : 0);
        result = 31 * result + (aqdj != null ? aqdj.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (uploadUser != null ? uploadUser.hashCode() : 0);
        result = 31 * result + (ext2 != null ? ext2.hashCode() : 0);
        result = 31 * result + (ext3 != null ? ext3.hashCode() : 0);
        result = 31 * result + (ext4 != null ? ext4.hashCode() : 0);
        result = 31 * result + (medicineTypeName != null ? medicineTypeName.hashCode() : 0);
        return result;
    }
}
