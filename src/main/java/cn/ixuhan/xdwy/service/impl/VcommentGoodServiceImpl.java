package cn.ixuhan.xdwy.service.impl;

import cn.ixuhan.xdwy.data.VcommentGoodMapper;
import cn.ixuhan.xdwy.data.VoteCommentMapper;
import cn.ixuhan.xdwy.model.VcommentGood;
import cn.ixuhan.xdwy.model.VcommentGoodExample;
import cn.ixuhan.xdwy.model.VoteComment;
import cn.ixuhan.xdwy.model.VoteCommentExample;
import cn.ixuhan.xdwy.service.VcommentGoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hank on 2017/6/4 0004.
 */
@Service("vcommentGoodService")
public class VcommentGoodServiceImpl implements VcommentGoodService {

    @Resource
    private VcommentGoodMapper vcommentGoodMapper;


    public void addCommentGoods(int commId, String openid) {
        VcommentGood good = new VcommentGood();
        good.setCommId(commId);
        good.setOpenid(openid);
        vcommentGoodMapper.insert(good);
    }

    public void deleteCommentGoods(int commId, String openid) {
        VcommentGoodExample goodExample = new VcommentGoodExample();
        goodExample.createCriteria().andCommIdEqualTo(commId).andOpenidEqualTo(openid);
        vcommentGoodMapper.deleteByExample(goodExample);
    }
}
