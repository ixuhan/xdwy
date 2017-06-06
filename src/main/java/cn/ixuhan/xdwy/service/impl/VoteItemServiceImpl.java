package cn.ixuhan.xdwy.service.impl;

import cn.ixuhan.xdwy.data.VoteItemMapper;
import cn.ixuhan.xdwy.model.VoteRecord;
import cn.ixuhan.xdwy.service.VoteItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hank on 2017/6/4 0004.
 */
@Service("voteItemsService")
public class VoteItemServiceImpl implements VoteItemService {

    @Resource
    private VoteItemMapper voteItemsMapper;

    public VoteRecord getVoteRecordById(int id) {
        return null;
    }

    public long checkMaxVoteCount(String openid, int voteId) {
        return 0;
    }
}
