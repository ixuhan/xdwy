package cn.ixuhan.xdwy.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Vote implements Serializable {
    private String id;

    /**
     * 投票名称
     */
    private String name;

    /**
     * 投票介绍
     */
    private String description;

    /**
     * 投票开始时间
     */
    private Date startTime;

    /**
     * 投票截止时间
     */
    private Date endTime;

    /**
     * 投票访问量
     */
    private Integer viewCount;

    /**
     * 投票是否可用（0不可用）
     */
    private Boolean valid;

    /**
     * 投票点赞情况
     */
    private Integer toptop;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Integer getToptop() {
        return toptop;
    }

    public void setToptop(Integer toptop) {
        this.toptop = toptop;
    }
}