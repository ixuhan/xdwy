package cn.ixuhan.xdwy.model;

import java.io.Serializable;

/**
 * @author 
 */
public class VoteRecord implements Serializable {
    private Integer id;

    /**
     * 投票所属id
     */
    private Integer voteId;

    /**
     * 投票中的选项id
     */
    private Integer vitemId;

    /**
     * 微信用户openid
     */
    private String openid;

    /**
     * 评论昵称
     */
    private String nickname;

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

    public Integer getVitemId() {
        return vitemId;
    }

    public void setVitemId(Integer vitemId) {
        this.vitemId = vitemId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}