package cn.ixuhan.xdwy.action;

import cn.ixuhan.xdwy.model.Vote;
import cn.ixuhan.xdwy.model.VoteItem;
import cn.ixuhan.xdwy.model.VoteRecord;
import cn.ixuhan.xdwy.service.VoteRecordService;
import cn.ixuhan.xdwy.service.VoteService;
import cn.ixuhan.xdwy.util.WechatInfo;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Hill on 2017/6/5.
 */
@Namespace("/vote")
public class VoteAction extends BaseSupport{

    @Autowired
    private VoteService voteService;
    @Autowired
    private VoteRecordService voteRecordService;

    //属性
    private static String OPENID;
    private static String NICKNAME;

    /**
     * 投票主页
     */
    @Action(value = "index",results = {@Result(name = "success", type="freemarker", location = "/html/voteInfo.html")})
    public String vote(){
        try {
            //从url中获得code
            //String code = getRequest().getParameter("code");
            //从url中获得state参数，标识投票id
            //int voteId = Integer.parseInt(getRequest().getParameter("state"));
            String code = "123456789";
            int voteId = 1;
            System.out.println("获取到的code为"+code);
            //发送请求获取token和openid
            /*JSONObject json = WechatInfo.getOpenIdAndToken(code);
            OPENID = json.getString("OPENID");
            NICKNAME = json.getString("NICKNAME");*/

            //获取投票相关信息用于展示
            List<VoteItem> voteItems = voteService.getVoteItemsByVoteId(voteId);
            Vote vote = voteService.getVoteById(voteId);
            getRequest().setAttribute("vote",vote);
            getRequest().setAttribute("voteItems",voteItems);

        } catch (Exception e) {
            e.printStackTrace();
         }
        //返回投票主页面
        return SUCCESS;
    }

    /**
     * 投票方法，url需要传入voteId和vItemId
     * @return
     */
    @Action(value = "record")
    public String record()
    {
        OPENID = "1212";
        NICKNAME = "小强";
        //OPENID存在且不为空说明当前投票人员是正确认证的
        if (null != OPENID && !OPENID.equals(""))
        {
            int voteId = 1;
            getRequest().getParameter("voteId");
            //判断是否达到投票最大限制
            int maxVoteCount = voteService.getMaxVoteCount(voteId);//投票最大数目
            long votedCount = voteRecordService.checkMaxVoteCount(OPENID, "vote", voteId);//当前已投数目
            if (votedCount < maxVoteCount)
            {
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
                }
                else
                {
                    msg = "该选项已达最大选择次数";
                }
            }
            else
            {
                msg = "投票次数已达上限";
            }
        }
        else
        {
            msg = "您没有投票权限，请刷新页面重试";
        }
        try {
            //将结果输出到前台
            getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
            PrintWriter writer = getResponse().getWriter();
            writer.write(msg);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
