package cn.ixuhan.xdwy.service;

import cn.ixuhan.xdwy.model.VoteRecord;

/**
 * Created by hank on 2017/6/4 0004.
 */
public interface VoteItemService {
    public VoteRecord getVoteRecordById(int id);

    long checkMaxVoteCount(String openid, int voteId);

    /**
     * 计算真实和虚假总票数
     * @param voteId
     * @return
     */
    int sumRealAndFakeCount(int voteId);

    /**
     * 更新投票真实记录
     * @param count 需要新增的数目
     * @param vItemId 选项Id
     */
    void updateVoteItemRealCount(int count, int vItemId);
}
