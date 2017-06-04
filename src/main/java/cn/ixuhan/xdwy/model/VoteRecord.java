package cn.ixuhan.xdwy.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class VoteRecord implements Serializable {
    private String id;

    /**
     * 投票中的选项id
     */
    private String vitemsId;

    /**
     * 微信用户openid
     */
    private String openid;

    /**
     * 评论昵称
     */
    private String nickname;

    /**
     * 投票时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVitemsId() {
        return vitemsId;
    }

    public void setVitemsId(String vitemsId) {
        this.vitemsId = vitemsId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}