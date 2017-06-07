package cn.ixuhan.xdwy.action;

import cn.ixuhan.xdwy.service.VoteItemService;
import cn.ixuhan.xdwy.util.MessageAnalyze;
import cn.ixuhan.xdwy.util.WechatConfirm;
import cn.ixuhan.xdwy.util.WechatInfo;
import com.google.gson.Gson;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with Hank.
 * User: Hank
 * Date: 2016/11/9
 * Time: 12:38
 * Des:微信基础action，用于与微信后台通讯
 */
@Controller
@Scope("prototype")
public class WechatAction extends BaseSupport {
    @Autowired
    private MessageAnalyze messageAnalyze;

    private static String APPID;
    private static String SECRET;
    private static String OPENID;
    private static String NICKNAME;
    private static String headimgurl;

    /**
     * 微信接入服务器认证
     */
    @Action(value = "weChatJoin")
    public void weChatJoin() throws Exception {
        String method = ServletActionContext.getRequest().getMethod();
        if (method.equals("GET")) { //GET请求，用于微信认证
            String signature = getRequest().getParameter("signature");
            String timestamp = getRequest().getParameter("timestamp");
            String nonce = getRequest().getParameter("nonce");
            String echostr = getRequest().getParameter("echostr");
            if (WechatConfirm.checkSignature(signature, timestamp, nonce)) {//微信认证，若确认此次GET请求来自微信服务器，
                // 请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
                PrintWriter out = getResponse().getWriter();
                out.print(echostr);
                out.close();
            } else {
                return;
            }
        } else {//POST请求，用于接收微信消息
            getRequest().setCharacterEncoding("UTF-8");
            getResponse().setCharacterEncoding("UTF-8");
            messageAnalyze.Message(getRequest(), getResponse());//解析消息内容
        }
    }

    /**
     * 重定向后跳转投票页面，由菜单的连接重定向至该方法，方法从url中得到code参数后，
     * 再次获取openid和token，再由token获取nickname。
     * openid和nickname为全局变量
     *
     * @return 返回到voteInfo
     *//*
    @Action(value = "weChatVote", results = {@Result(name = "success", location = "/jsp/voteInfo.jsp")})
     */
    /*@Action(value = "weChatVote", results = {@Result(name = "success", location = "/jsp/voteInfo.jsp")})
    public String weChatInit() {
        APPID = WechatInfo.getAPPID();
        SECRET = WechatInfo.getSECRET();

        //如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
        String code = getRequest().getParameter("code");
        System.out.println("进入了weChatInit");
        System.out.println("获取到的code为" + code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + SECRET + "&code=" + code + "&grant_type=authorization_code";
        try {
            Document doc = Jsoup.connect(url).get();
            //返回网页内容
            String html = doc.body().html().toString();
            Gson gson = new Gson();
            Map map = gson.fromJson(html, HashMap.class);
            //解析网页拿到access_token
            String access_token = map.get("access_token").toString();
            OPENID = map.get("openid").toString();
            //继续访问
            String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + OPENID + "&lang=zh_CN";
            doc = Jsoup.connect(url2).get();
            html = doc.body().html().toString();
            map = gson.fromJson(html, HashMap.class);

            if (headimgurl != null && !"".equals(headimgurl)) {
                headimgurl = "http://wx.qlogo.cn/mmopen/" + headimgurl.split("/")[4] + "/46";
                System.out.println(headimgurl);
            }

            headimgurl = map.get("headimgurl").toString();
            NICKNAME = map.get("nickname").toString();

            getRequest().setAttribute("headimgurl", headimgurl);
            getRequest().setAttribute("NICKNAME", NICKNAME);

        } catch (IOException io) {
            System.out.println(io.getMessage());
            System.out.println("cant touch url");
        }
        return SUCCESS;
    }*/
}
