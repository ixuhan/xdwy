package cn.ixuhan.xdwy.action;

import cn.ixuhan.xdwy.model.VoteComment;
import cn.ixuhan.xdwy.service.VcommentGoodService;
import cn.ixuhan.xdwy.service.VoteCommentService;
import com.google.gson.Gson;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 评论处理相关
 */
@Controller
public class CommentAction extends BaseSupport {

    @Resource
    private VoteCommentService voteCommentService;
    @Resource
    private VcommentGoodService vcommentGoodService;

    private VoteComment voteComment;

    /**
     * 返回评论列表,分页显示，每次显示10条
     * @return
     */
    /*@Action(value = "comments")
    public String getVoteCommentList()
    {
        try {
            int voteId = Integer.parseInt(getRequest().getParameter("voteId"));
            int offset = Integer.parseInt(getRequest().getParameter("offset"));
            List<VoteComment> commentsList = voteCommentService.getVoteCommentByVoteId(voteId);
            Gson gson = new Gson();
            String json = gson.toJson(commentsList);
            getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * 添加投票评论
     *
     * @return
     */
    @Action(value = "addComment")
    public String addVoteComment() {
        try {
            System.out.println(voteComment.getContent());
            voteComment.setValid((byte) 0);
            voteCommentService.saveVoteComment(voteComment);
            getResponse().getWriter().write("{\"msg\":\"" + msg + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Action(value = "topdown")
    public String topdown()
    {
        try {
            int commId = Integer.parseInt(getRequest().getParameter("comm_id"));
            String openid = getRequest().getParameter("openid");
            vcommentGoodService.deleteCommentGoods(commId,openid);
            getResponse().getWriter().write("{\"msg\":\"" + msg + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Action(value = "topup")
    public String topup()
    {
        try {
            int commId = Integer.parseInt(getRequest().getParameter("comm_id"));
            String openid = getRequest().getParameter("openid");
            vcommentGoodService.addCommentGoods(commId,openid);
            getResponse().getWriter().write("{\"msg\":\"" + msg + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public VoteComment getVoteComment() {
        return voteComment;
    }

    public void setVoteComment(VoteComment voteComment) {
        this.voteComment = voteComment;
    }
}
