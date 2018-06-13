package cn.xupt.ttms.model;

import java.io.Serializable;
import java.util.Date;

public class Schedule implements Serializable {

    private static final long serialVersionUID = 5L;
    private Integer schedId;

    private Integer studioId;

    private Integer playId;

    private String schedTime;

    private Integer schedTicketPrice;


    public Schedule() {
        super();
    }

    public Integer getSchedId() {
        return schedId;
    }

    public void setSchedId(Integer schedId) {
        this.schedId = schedId;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public Integer getPlayId() {
        return playId;
    }

    public void setPlayId(Integer playId) {
        this.playId = playId;
    }

    public String getSchedTime() {
        return schedTime;
    }

    public void setSchedTime(String schedTime) {
        this.schedTime = schedTime;
    }

    public Integer getSchedTicketPrice() {
        return schedTicketPrice;
    }

    public void setSchedTicketPrice(Integer schedTicketPrice) {
        this.schedTicketPrice = schedTicketPrice;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "schedId=" + schedId +
                ", studioId=" + studioId +
                ", playId=" + playId +
                ", schedTime=" + schedTime +
                ", schedTicketPrice=" + schedTicketPrice +
                '}';
    }
}