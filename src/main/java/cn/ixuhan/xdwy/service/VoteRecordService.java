package cn.ixuhan.xdwy.service;

import cn.ixuhan.xdwy.model.VoteRecord;

/**
 * Created by hank on 2017/6/4 0004.
 */
public interface VoteRecordService {

    /**
     * 传入用户OPENID和投票id或投票项目id查询投票数量
     * @param openid
     * @param type vote 或 item
     * @param id
     * @return
     */
    long checkMaxVoteCount(String openid, String type, int id);

    void insertVoteRecord(VoteRecord voteRecord);



}
