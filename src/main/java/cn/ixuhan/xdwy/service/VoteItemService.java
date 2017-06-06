package cn.ixuhan.xdwy.service;

import cn.ixuhan.xdwy.model.VoteRecord;

/**
 * Created by hank on 2017/6/4 0004.
 */
public interface VoteItemService {
    public VoteRecord getVoteRecordById(int id);

    long checkMaxVoteCount(String openid, int voteId);
}
