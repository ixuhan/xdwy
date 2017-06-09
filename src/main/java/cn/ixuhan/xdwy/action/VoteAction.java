package cn.ixuhan.xdwy.action;

import cn.ixuhan.xdwy.model.Vote;
import cn.ixuhan.xdwy.model.VoteComment;
import cn.ixuhan.xdwy.model.VoteItem;
import cn.ixuhan.xdwy.model.VoteRecord;
import cn.ixuhan.xdwy.service.VoteCommentService;
import cn.ixuhan.xdwy.service.VoteItemService;
import cn.ixuhan.xdwy.service.VoteRecordService;
import cn.ixuhan.xdwy.service.VoteService;
import cn.ixuhan.xdwy.util.WechatInfo;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hill on 2017/6/5.
 */
@Controller
@Scope("prototype")
public class VoteAction extends BaseSupport {

    @Autowired
    private VoteService voteService;
    @Autowired
    private VoteRecordService voteRecordService;
    @Autowired
    private VoteItemService voteItemService;
    @Autowired
    private VoteCommentService voteCommentService;

    //属性
    private static String OPENID;
    private static String NICKNAME;
    private static int voteId;
    private static JSONObject json = null;

    /**
     * 投票主页
     */
    @Action(value = "index", results = {@Result(name = "success", type = "freemarker", location = "/voteInfo.html")})
    public String vote() {
        try {
            //从url中获得code
            String code = getRequest().getParameter("code");
            //从url中获得state参数，标识投票id
            voteId = Integer.parseInt(getRequest().getParameter("state"));

            //使用获取到的code 换取OPENID和NICKNAME和headImg
            if (json == null) {
                json = WechatInfo.getOpenIdAndToken(code);
            }
            OPENID = json.getString("OPENID");
            NICKNAME = json.getString("NICKNAME");
            String headImg = json.getString("headImg");

            //计算所有项目总投票数 A1.fake+A1.real +...
            int totalCount = voteItemService.sumRealAndFakeCount(voteId);
            getRequest().getSession().setAttribute("totalCount", totalCount);

            //数据库中查找openid是否已达最大投票数目
            int maxVoteCount = voteService.getMaxVoteCount(voteId);//投票最大数目
            long votedCount = voteRecordService.checkMaxVoteCount(OPENID, "vote", voteId);//当前已投数目

            //获取投票相关信息用于展示
            List<VoteItem> voteItems = voteService.getVoteItemsByVoteId(voteId);
            Vote vote = voteService.getVoteById(voteId);
            getRequest().getSession().setAttribute("NICKNAME", NICKNAME);
            getRequest().getSession().setAttribute("vote", vote);
            System.out.println(vote.getDescription());
            getRequest().getSession().setAttribute("voteItems", voteItems);
            getRequest().getSession().setAttribute("headImg", headImg);
            getRequest().getSession().setAttribute("openid", OPENID);

            List<HashMap> voteComments = voteCommentService.getVoteCommentByVoteId(OPENID, 1);

            getRequest().getSession().setAttribute("voteComments", voteComments);

            if (votedCount < maxVoteCount) {
                getRequest().getSession().setAttribute("voteRemain", maxVoteCount - votedCount);
            } else {
                getRequest().getSession().setAttribute("voteRemain", "0");
                JSONObject itemStatus = new JSONObject();
                //投票数目已达最大值，直接显示结果
                for (VoteItem item : voteItems) {
                    int count = item.getFakeCount() + item.getRealCount();
                    itemStatus.put(item.getId() + "", Math.round(count * 10000 / totalCount) / 100.0 + "%");
                }
                getRequest().getSession().setAttribute("itemStatus", itemStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回投票主页面
        return SUCCESS;
    }

    /**
     * 投票方法，url需要传入voteId和vItemId
     *
     * @return
     */
    @Action(value = "record")
    public String record() {
        System.out.println("进入record");
        //OPENID存在且不为空说明当前投票人员是正确认证的
        if (null != OPENID && !OPENID.equals("")) {
            //判断是否达到投票最大限制
            int maxVoteCount = voteService.getMaxVoteCount(voteId);//投票最大数目
            long votedCount = voteRecordService.checkMaxVoteCount(OPENID, "vote", voteId);//当前已投数目
            if (votedCount < maxVoteCount) {
                //获取投票选项id
                int vItemId = Integer.parseInt(getRequest().getParameter("vItemId"));
                //判断当前选项已被该openid投票多少次，不大于最大单项投票数可投
                int maxVoteItemCount = voteService.getMaxVoteItemCount(voteId);//每个项目最大可投数目
                long itemVotedCount = voteRecordService.checkMaxVoteCount(OPENID, "item", vItemId);//该项目已投次数
                if (itemVotedCount < maxVoteItemCount)//仍有投该项目的余地
                {
                    VoteRecord record = new VoteRecord();
                    record.setOpenid(OPENID);
                    record.setNickname(NICKNAME);
                    record.setVoteId(voteId);
                    record.setVitemId(vItemId);
                    voteRecordService.insertVoteRecord(record);
                    //将投票记录在item中
                    voteItemService.updateVoteItemRealCount(1, vItemId);
                } else {
                    msg = "该选项已达最大选择次数";
                }
            } else {
                msg = "投票次数已达上限";
            }
        } else {
            msg = "您没有投票权限，请刷新页面重试";
        }
        try {
            //将结果输出到前台
            getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
            PrintWriter writer = getResponse().getWriter();
            writer.write("{\"msg\":\"" + msg + "\"}");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
