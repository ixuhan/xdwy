package cn.ixuhan.xdwy.service.impl;

import cn.ixuhan.xdwy.data.VoteMapper;
import cn.ixuhan.xdwy.model.Vote;
import cn.ixuhan.xdwy.service.VoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hank on 2017/6/4 0004.
 */
@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Resource
    private VoteMapper voteMapper;

    public Vote getVoteById(String id) {
        return this.voteMapper.selectByPrimaryKey(id);
    }
}
