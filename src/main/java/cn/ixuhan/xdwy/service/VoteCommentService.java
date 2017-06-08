package cn.ixuhan.xdwy.service;

import cn.ixuhan.xdwy.model.VoteComment;

import java.util.List;

/**
 * 评论使用service
 */
public interface VoteCommentService {

    /**
     * 根据投票id和评论起始位置获取评论，每次获取后10条
     * @param voteId 投票id
     * @param Limit 评论起始位置索引
     * @return
     */
    public List<VoteComment> getVoteCommentByVoteIdAndLimit(int voteId, int Limit);

    /**
     * 保存投票评论
     * @param voteComment
     */
    public void saveVoteComment(VoteComment voteComment);
}
