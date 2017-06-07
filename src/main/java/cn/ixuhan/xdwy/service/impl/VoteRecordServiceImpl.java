package cn.ixuhan.xdwy.service.impl;

import cn.ixuhan.xdwy.data.VoteMapper;
import cn.ixuhan.xdwy.data.VoteRecordMapper;
import cn.ixuhan.xdwy.model.VoteRecord;
import cn.ixuhan.xdwy.model.VoteRecordExample;
import cn.ixuhan.xdwy.service.VoteRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hank on 2017/6/4 0004.
 */
@Service("voteRecordService")
public class VoteRecordServiceImpl implements VoteRecordService {

    @Resource
    private VoteRecordMapper voteRecordMapper;
    @Resource
    private VoteMapper voteMapper;

    public VoteRecord getVoteRecordById(int id) {
        return this.voteRecordMapper.selectByPrimaryKey(id);
    }

    public long checkMaxVoteCount(String openid, String type, int id) {
        VoteRecordExample recordExample = new VoteRecordExample();
        if (type.equals("vote"))
        {
            recordExample.createCriteria().andOpenidEqualTo(openid).andVoteIdEqualTo(id);
        }
        else
        {
            recordExample.createCriteria().andOpenidEqualTo(openid).andVitemIdEqualTo(id);
        }
        long count = voteRecordMapper.countByExample(recordExample);
        return count;
    }

    public void insertVoteRecord(VoteRecord voteRecord)
    {
        voteRecordMapper.insert(voteRecord);
    }


}
