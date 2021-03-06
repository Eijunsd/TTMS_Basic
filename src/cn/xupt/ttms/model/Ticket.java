package cn.xupt.ttms.model;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {

    private static final long serialVersionUID = 8L;
    private Integer ticketId;

    private Integer seatId;

    private Integer schedId;

    private Float ticketPrice;

    private Integer ticketStatus;

    private Date ticketLockedTime;


    public Ticket() {
        super();
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getSchedId() {
        return schedId;
    }

    public void setSchedId(Integer schedId) {
        this.schedId = schedId;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Date getTicketLockedTime() {
        return ticketLockedTime;
    }

    public void setTicketLockedTime(Date ticketLockedTime) {
        this.ticketLockedTime = ticketLockedTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", seatId=" + seatId +
                ", schedId=" + schedId +
                ", ticketPrice=" + ticketPrice +
                ", ticketStatus=" + ticketStatus +
                ", ticketLockedTime=" + ticketLockedTime +
                '}';
    }
}