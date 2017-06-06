package cn.ixuhan.xdwy.action;

import cn.ixuhan.xdwy.data.VoteMapper;
import cn.ixuhan.xdwy.model.Vote;
import cn.ixuhan.xdwy.model.VoteExample;
import cn.ixuhan.xdwy.service.VoteService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hank on 2017/6/4 0004.
 */
public class Test extends BaseSupport{

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteMapper voteMapper;

    /**
     * 测试
     */
    @Action(value = "test", results = {@Result(name = "success", location = "/index.jsp")})
    public String test() {
        this.getRequest().setAttribute("result","asd");

        //第一种方式
        System.out.println(((Vote)voteService.getVoteById(1)).getName());

        //第二种方式
        VoteExample voteExample = new VoteExample();
        voteExample.createCriteria().andDescriptionIsNotNull();
        System.out.println(((List<Vote>) (voteMapper.selectByExample(voteExample))).size());
        System.out.println((((Vote)((List<Vote>) (voteMapper.selectByExample(voteExample))).get(0))).getDescription());

        return SUCCESS;
    }
}
