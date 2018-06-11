package cn.xupt.ttms.model;

public class Studio {
    private Integer studioId;

    private String studioName;

    private Integer studioRowCount;

    public Studio(String studioName, Integer studioRowCount, Integer studioColCount, String studioIntroduction, Integer studioFlag) {
        this.studioName = studioName;
        this.studioRowCount = studioRowCount;
        this.studioColCount = studioColCount;
        this.studioIntroduction = studioIntroduction;
        this.studioFlag = studioFlag;
    }

    private Integer studioColCount;

    private String studioIntroduction;

    private Integer studioFlag;


    public Studio() {
        super();
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName == null ? null : studioName.trim();
    }

    public Integer getStudioRowCount() {
        return studioRowCount;
    }

    public void setStudioRowCount(Integer studioRowCount) {
        this.studioRowCount = studioRowCount;
    }

    public Integer getStudioColCount() {
        return studioColCount;
    }

    public void setStudioColCount(Integer studioColCount) {
        this.studioColCount = studioColCount;
    }

    public String getStudioIntroduction() {
        return studioIntroduction;
    }

    public void setStudioIntroduction(String studioIntroduction) {
        this.studioIntroduction = studioIntroduction == null ? null : studioIntroduction.trim();
    }

    public Integer getStudioFlag() {
        return studioFlag;
    }

    public void setStudioFlag(Integer studioFlag) {
        this.studioFlag = studioFlag;
    }
}