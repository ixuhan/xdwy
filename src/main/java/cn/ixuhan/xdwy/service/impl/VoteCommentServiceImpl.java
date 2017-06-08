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

    public List<VoteComment> getVoteCommentByVoteId(int voteId) {
        VoteCommentExample example = new VoteCommentExample();
        example.setOrderByClause("TOPTOP DESC , create_time DESC");
        example.createCriteria().andVoteIdEqualTo(voteId).andValidEqualTo((byte) 1);
        return voteCommentMapper.selectByExample(example);
    }

    public void saveVoteComment(VoteComment voteComment) {
        voteCommentMapper.insert(voteComment);
    }
}
