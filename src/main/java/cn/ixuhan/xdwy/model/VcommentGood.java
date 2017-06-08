package cn.ixuhan.xdwy.model;

import java.io.Serializable;

/**
 * @author 
 */
public class VcommentGood implements Serializable {
    private Integer id;

    /**
     * 评论id
     */
    private Integer commId;

    /**
     * 用户openid
     */
    private String openid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommId() {
        return commId;
    }

    public void setCommId(Integer commId) {
        this.commId = commId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}