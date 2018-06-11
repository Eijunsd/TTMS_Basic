package cn.xupt.ttms.model;

import java.io.Serializable;
import java.util.Date;

public class Sale implements Serializable {

    private static final long serialVersionUID = 3L;
    private Long saleId;

    private Integer empId;

    private Date saleTime;

    private Float salePayment;

    private Float saleChange;

    private Integer saleType;

    private Integer saleStatus;


    public Sale() {
        super();
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Float getSalePayment() {
        return salePayment;
    }

    public void setSalePayment(Float salePayment) {
        this.salePayment = salePayment;
    }

    public Float getSaleChange() {
        return saleChange;
    }

    public void setSaleChange(Float saleChange) {
        this.saleChange = saleChange;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }
}