package com.gx.soft.medicineTree.persistence.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by adminstrator on 2019/9/11.
 */
@Entity
@Table(name = "medicine_type", schema = "njsfy", catalog = "")
public class MedicineType {
    private String rowId;
    private String medicineTypeId;
    private String medicineTypeName;
    private Timestamp createTime;
    private Timestamp uploadTime;
    private Timestamp createName;
    private Timestamp uploadName;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String medicineParentTypeId;
    private String medicineParentTypeName;

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
    @Column(name = "medicine_type_id")
    public String getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(String medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }

    @Basic
    @Column(name = "medicine_type_name")
    public String getMedicineTypeName() {
        return medicineTypeName;
    }

    public void setMedicineTypeName(String medicineTypeName) {
        this.medicineTypeName = medicineTypeName;
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
    @Column(name = "create_name")
    public Timestamp getCreateName() {
        return createName;
    }

    public void setCreateName(Timestamp createName) {
        this.createName = createName;
    }

    @Basic
    @Column(name = "upload_name")
    public Timestamp getUploadName() {
        return uploadName;
    }

    public void setUploadName(Timestamp uploadName) {
        this.uploadName = uploadName;
    }

    @Basic
    @Column(name = "ext1")
    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
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
    @Column(name = "medicine_parent_type_id")
    public String getMedicineParentTypeId() {
        return medicineParentTypeId;
    }

    public void setMedicineParentTypeId(String medicineParentTypeId) {
        this.medicineParentTypeId = medicineParentTypeId;
    }

    @Basic
    @Column(name = "medicine_parent_type_name")
    public String getMedicineParentTypeName() {
        return medicineParentTypeName;
    }

    public void setMedicineParentTypeName(String medicineParentTypeName) {
        this.medicineParentTypeName = medicineParentTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineType that = (MedicineType) o;

        if (rowId != null ? !rowId.equals(that.rowId) : that.rowId != null) return false;
        if (medicineTypeId != null ? !medicineTypeId.equals(that.medicineTypeId) : that.medicineTypeId != null)
            return false;
        if (medicineTypeName != null ? !medicineTypeName.equals(that.medicineTypeName) : that.medicineTypeName != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        if (createName != null ? !createName.equals(that.createName) : that.createName != null) return false;
        if (uploadName != null ? !uploadName.equals(that.uploadName) : that.uploadName != null) return false;
        if (ext1 != null ? !ext1.equals(that.ext1) : that.ext1 != null) return false;
        if (ext2 != null ? !ext2.equals(that.ext2) : that.ext2 != null) return false;
        if (ext3 != null ? !ext3.equals(that.ext3) : that.ext3 != null) return false;
        if (ext4 != null ? !ext4.equals(that.ext4) : that.ext4 != null) return false;
        if (medicineParentTypeId != null ? !medicineParentTypeId.equals(that.medicineParentTypeId) : that.medicineParentTypeId != null)
            return false;
        if (medicineParentTypeName != null ? !medicineParentTypeName.equals(that.medicineParentTypeName) : that.medicineParentTypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rowId != null ? rowId.hashCode() : 0;
        result = 31 * result + (medicineTypeId != null ? medicineTypeId.hashCode() : 0);
        result = 31 * result + (medicineTypeName != null ? medicineTypeName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (createName != null ? createName.hashCode() : 0);
        result = 31 * result + (uploadName != null ? uploadName.hashCode() : 0);
        result = 31 * result + (ext1 != null ? ext1.hashCode() : 0);
        result = 31 * result + (ext2 != null ? ext2.hashCode() : 0);
        result = 31 * result + (ext3 != null ? ext3.hashCode() : 0);
        result = 31 * result + (ext4 != null ? ext4.hashCode() : 0);
        result = 31 * result + (medicineParentTypeId != null ? medicineParentTypeId.hashCode() : 0);
        result = 31 * result + (medicineParentTypeName != null ? medicineParentTypeName.hashCode() : 0);
        return result;
    }
}
