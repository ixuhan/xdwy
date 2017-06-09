package cn.ixuhan.xdwy.service;

import cn.ixuhan.xdwy.model.VoteComment;

import java.util.HashMap;
import java.util.List;

/**
 * 评论使用service
 */
public interface VoteCommentService {

    /**
     * 根据投票id和评论起始位置获取评论，每次获取后10条
     * @param voteId 投票id
     * @return
     */
    public List<HashMap> getVoteCommentByVoteId(String openid, int voteId);

    /**
     * 保存投票评论
     * @param voteComment
     */
    public void saveVoteComment(VoteComment voteComment);
}
