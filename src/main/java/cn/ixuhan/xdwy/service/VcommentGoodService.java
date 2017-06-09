package cn.ixuhan.xdwy.service;

import cn.ixuhan.xdwy.model.VoteComment;

import java.util.List;

/**
 * 评论点赞service
 */
public interface VcommentGoodService {

    /**
     * 添加点赞记录
     */
    void addCommentGoods(int commId, String openid);

    /**
     * 删除点赞记录
     * @param commId
     * @param openid
     */
    void deleteCommentGoods(int commId, String openid);
}
