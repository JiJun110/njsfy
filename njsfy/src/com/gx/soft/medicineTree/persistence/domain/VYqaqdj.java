package com.gx.soft.medicineTree.persistence.domain;

import javax.persistence.*;

/**
 * Created by adminstrator on 2019/10/9.
 */
@Entity
@Table(name = "v_yqaqdj", schema = "njsfy", catalog = "")
public class VYqaqdj {
    private String yqAqdj;

    @Id
    @Column(name = "yq_aqdj")
    public String getYqAqdj() {
        return yqAqdj;
    }

    public void setYqAqdj(String yqAqdj) {
        this.yqAqdj = yqAqdj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VYqaqdj vYqaqdj = (VYqaqdj) o;

        if (yqAqdj != null ? !yqAqdj.equals(vYqaqdj.yqAqdj) : vYqaqdj.yqAqdj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return yqAqdj != null ? yqAqdj.hashCode() : 0;
    }
}
