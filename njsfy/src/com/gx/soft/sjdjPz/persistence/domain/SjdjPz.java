package com.gx.soft.sjdjPz.persistence.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by adminstrator on 2019/9/17.
 */
@Entity
@Table(name = "sjdj_pz", schema = "njsfy", catalog = "")
public class SjdjPz {
    private String rowId;
    private String url;
    private String userName;
    private String userPwd;
    private String createUser;
    private Timestamp createTime;
    private Timestamp uploadTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;

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
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_pwd")
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SjdjPz sjdjPz = (SjdjPz) o;

        if (rowId != null ? !rowId.equals(sjdjPz.rowId) : sjdjPz.rowId != null) return false;
        if (url != null ? !url.equals(sjdjPz.url) : sjdjPz.url != null) return false;
        if (userName != null ? !userName.equals(sjdjPz.userName) : sjdjPz.userName != null) return false;
        if (userPwd != null ? !userPwd.equals(sjdjPz.userPwd) : sjdjPz.userPwd != null) return false;
        if (createUser != null ? !createUser.equals(sjdjPz.createUser) : sjdjPz.createUser != null) return false;
        if (createTime != null ? !createTime.equals(sjdjPz.createTime) : sjdjPz.createTime != null) return false;
        if (uploadTime != null ? !uploadTime.equals(sjdjPz.uploadTime) : sjdjPz.uploadTime != null) return false;
        if (ext1 != null ? !ext1.equals(sjdjPz.ext1) : sjdjPz.ext1 != null) return false;
        if (ext2 != null ? !ext2.equals(sjdjPz.ext2) : sjdjPz.ext2 != null) return false;
        if (ext3 != null ? !ext3.equals(sjdjPz.ext3) : sjdjPz.ext3 != null) return false;
        if (ext4 != null ? !ext4.equals(sjdjPz.ext4) : sjdjPz.ext4 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rowId != null ? rowId.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPwd != null ? userPwd.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (ext1 != null ? ext1.hashCode() : 0);
        result = 31 * result + (ext2 != null ? ext2.hashCode() : 0);
        result = 31 * result + (ext3 != null ? ext3.hashCode() : 0);
        result = 31 * result + (ext4 != null ? ext4.hashCode() : 0);
        return result;
    }
}
