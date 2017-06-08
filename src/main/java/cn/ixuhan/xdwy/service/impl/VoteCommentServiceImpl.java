package cn.ixuhan.xdwy.service.impl;

import cn.ixuhan.xdwy.data.VoteCommentMapper;
import cn.ixuhan.xdwy.model.VoteComment;
import cn.ixuhan.xdwy.model.VoteCommentExample;
import cn.ixuhan.xdwy.service.VoteCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hank on 2017/6/4 0004.
 */
@Service("voteCommentService")
public class VoteCommentServiceImpl implements VoteCommentService {

    @Resource
    private VoteCommentMapper voteCommentMapper;

    public List<VoteComment> getVoteCommentByVoteIdAndLimit(int voteId, int offset) {
        VoteCommentExample example = new VoteCommentExample();
        example.setOffset(offset);
        example.setLimit(offset+1);
        example.setOrderByClause("create_time desc");
        example.createCriteria().andVoteIdEqualTo(voteId).andValidEqualTo((byte) 1);
        return voteCommentMapper.selectByExample(example);
    }

    public void saveVoteComment(VoteComment voteComment) {
        voteCommentMapper.insert(voteComment);
    }
}
