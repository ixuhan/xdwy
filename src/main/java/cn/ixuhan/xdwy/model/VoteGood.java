package cn.ixuhan.xdwy.model;

import java.io.Serializable;

/**
 * @author 
 */
public class VoteGood implements Serializable {
    private Integer id;

    private Integer voteId;

    private String openid;

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}