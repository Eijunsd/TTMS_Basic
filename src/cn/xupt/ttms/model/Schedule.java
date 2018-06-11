package cn.xupt.ttms.model;

import java.util.Date;

public class Schedule {
    private Integer schedId;

    private Integer studioId;

    private Integer playId;

    private Date schedTime;

    private Float schedTicketPrice;


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

    public Date getSchedTime() {
        return schedTime;
    }

    public void setSchedTime(Date schedTime) {
        this.schedTime = schedTime;
    }

    public Float getSchedTicketPrice() {
        return schedTicketPrice;
    }

    public void setSchedTicketPrice(Float schedTicketPrice) {
        this.schedTicketPrice = schedTicketPrice;
    }
}