package cn.xupt.ttms.model;

import java.io.Serializable;

public class Seat implements Serializable {

    private static final long serialVersionUID = 6L;
    private Integer seatId;

    private Integer studioId;

    private Integer seatRow;

    private Integer seatColumn;

    private Integer seatStatus;


    public Seat() {
        super();
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    public Integer getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(Integer seatColumn) {
        this.seatColumn = seatColumn;
    }

    public Integer getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Integer seatStatus) {
        this.seatStatus = seatStatus;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", studioId=" + studioId +
                ", seatRow=" + seatRow +
                ", seatColumn=" + seatColumn +
                ", seatStatus=" + seatStatus +
                '}';
    }
}