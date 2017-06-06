package cn.ixuhan.xdwy.model;

import java.io.Serializable;

/**
 * @author 
 */
public class VoteItem implements Serializable {
    private Integer id;

    /**
     * 选项所属投票id
     */
    private Integer voteId;

    /**
     * 选项位置
     */
    private Integer weight;

    /**
     * 选项内容
     */
    private String content;

    /**
     * 选项是否有图片
     */
    private String picture;

    /**
     * 选项真实投票数目
     */
    private Integer realCount;

    /**
     * 选项虚拟投票数目
     */
    private Integer fakeCount;

    /**
     * 选项是否可用
     */
    private Byte valid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getRealCount() {
        return realCount;
    }

    public void setRealCount(Integer realCount) {
        this.realCount = realCount;
    }

    public Integer getFakeCount() {
        return fakeCount;
    }

    public void setFakeCount(Integer fakeCount) {
        this.fakeCount = fakeCount;
    }

    public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }
}