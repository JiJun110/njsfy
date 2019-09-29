package com.gx.soft.medicineTree.persistence.domain;

import javax.persistence.*;

/**
 * Created by adminstrator on 2019/9/29.
 */
@Entity
@Table(name = "v_changshang", schema = "njsfy", catalog = "")
public class VChangshang {
    private String changShang;
    private String rowId;

    @Basic
    @Column(name = "chang_shang")
    public String getChangShang() {
        return changShang;
    }

    public void setChangShang(String changShang) {
        this.changShang = changShang;
    }

    @Id
    @Column(name = "row_id")
    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VChangshang that = (VChangshang) o;

        if (changShang != null ? !changShang.equals(that.changShang) : that.changShang != null) return false;
        if (rowId != null ? !rowId.equals(that.rowId) : that.rowId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = changShang != null ? changShang.hashCode() : 0;
        result = 31 * result + (rowId != null ? rowId.hashCode() : 0);
        return result;
    }
}
