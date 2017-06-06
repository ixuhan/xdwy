package cn.ixuhan.xdwy.service.impl;

import cn.ixuhan.xdwy.data.VoteItemMapper;
import cn.ixuhan.xdwy.data.VoteMapper;
import cn.ixuhan.xdwy.model.Vote;
import cn.ixuhan.xdwy.model.VoteItem;
import cn.ixuhan.xdwy.model.VoteItemExample;
import cn.ixuhan.xdwy.service.VoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hank on 2017/6/4 0004.
 */
@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Resource
    private VoteMapper voteMapper;
    @Resource
    private VoteItemMapper voteItemMapper;

    public Vote getVoteById(int id) {
        return this.voteMapper.selectByPrimaryKey(id);
    }

    public int getMaxVoteCount(int id) {
        return voteMapper.selectByPrimaryKey(id).getAllCount();
    }

    public int getMaxVoteItemCount(int id) {
        return voteMapper.selectByPrimaryKey(id).getCheckOpt();
    }

    public List<VoteItem> getVoteItemsByVoteId(int voteId) {
        VoteItemExample example = new VoteItemExample();
        example.createCriteria().andVoteIdEqualTo(voteId);
        return voteItemMapper.selectByExample(example);
    }
}
