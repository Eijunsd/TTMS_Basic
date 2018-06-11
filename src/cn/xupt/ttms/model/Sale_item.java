package cn.xupt.ttms.model;

import java.io.Serializable;

public class Sale_item implements Serializable {

    private static final long serialVersionUID = 4L;
    private Integer saleItemId;

    private Integer ticketId;

    private Integer saleId;

    private Float saleItemPrice;


    public Sale_item() {
        super();
    }

    public Integer getSaleItemId() {
        return saleItemId;
    }

    public void setSaleItemId(Integer saleItemId) {
        this.saleItemId = saleItemId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Float getSaleItemPrice() {
        return saleItemPrice;
    }

    public void setSaleItemPrice(Float saleItemPrice) {
        this.saleItemPrice = saleItemPrice;
    }

    @Override
    public String toString() {
        return "Sale_item{" +
                "saleItemId=" + saleItemId +
                ", ticketId=" + ticketId +
                ", saleId=" + saleId +
                ", saleItemPrice=" + saleItemPrice +
                '}';
    }
}