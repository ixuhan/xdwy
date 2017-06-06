package cn.ixuhan.xdwy.service;

import cn.ixuhan.xdwy.model.Vote;
import cn.ixuhan.xdwy.model.VoteItem;

import java.util.List;

/**
 * Created by hank on 2017/6/4 0004.
 */
public interface VoteService {
    public Vote getVoteById(int id);

    /**
     * 获得投票的最大投票数目
     * @param id
     * @return
     */
    public int getMaxVoteCount(int id);

    /**
     * 获得单个项目的最大投票数目
     * @param id
     * @return
     */
    public int getMaxVoteItemCount(int id);

    /**
     * 根据投票id获得所有投票项目
     * @param voteId
     * @return
     */
    List<VoteItem> getVoteItemsByVoteId(int voteId);

}
