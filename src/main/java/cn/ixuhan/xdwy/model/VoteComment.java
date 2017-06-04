package cn.ixuhan.xdwy.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class VoteComment implements Serializable {
    private String id;

    /**
     * 用户ID
     */
    private String openid;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 点赞数量
     */
    private Integer toptop;

    /**
     * 是否可以显示
     */
    private Byte valid;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getToptop() {
        return toptop;
    }

    public void setToptop(Integer toptop) {
        this.toptop = toptop;
    }

    public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }
}