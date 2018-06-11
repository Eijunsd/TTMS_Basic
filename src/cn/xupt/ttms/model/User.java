package cn.xupt.ttms.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 9L;
    private String empNo;

    private String empPass;

    private Integer type;

    private String headPath;

    public User(String empNo, String empPass) {
        this.empNo = empNo;
        this.empPass = empPass;
    }

    public User() {
        super();
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo == null ? null : empNo.trim();
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass == null ? null : empPass.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath == null ? null : headPath.trim();
    }
}