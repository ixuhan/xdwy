package cn.ixuhan.xdwy.action;

import com.google.gson.Gson;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with Hank.
 * User: Hank
 * Date: 2016/11/9 0009
 * Time: 12:38
 * Des:微信基础action，用于与微信后台通讯
 */
@Controller
@Scope("prototype")
public class WechatAction extends BaseSupport {

    @Autowired
    private SqlSessionFactoryBean sqlSessionFactory;
    private static String APPID;
    private static String SECRET;
    private static String OPENID;
    private static String NICKNAME;

    //初始化获取appid和secret
    static {
        //建立配置容器
        Properties properties = new Properties();
        //读取配置文件
        InputStream inputStream = WechatAction.class.getResourceAsStream("/wechat.properties");
        try {
            //把读取到的配置文件加载到配置容器
            properties.load(inputStream);
            //获取APPID
            APPID = properties.get("wechat.APPID").toString();
            //获取SERCET
            SECRET = properties.get("wechat.SECRET").toString();
        }catch (IOException io){
            System.out.println(io.getMessage());
            System.out.println("cant load wechat.properties");
        }

    }

    /**
     * 重定向后跳转投票页面，由菜单的连接重定向至该方法，方法从url中得到code参数后，
     * 再次获取openid和token，再由token获取nickname。
     * openid和nickname为全局变量
     * @return 返回的网页
     */
    @Action(value = "weChatInit", results = {@Result(name = "success", location = "/voteInfo.jsp")})
    public String weChatInit() {

        String state = "1";
        String code = "123456789";
        //String code = getRequest().getParameter("code");
        System.out.println("进入了weChatInit");
        System.out.println("获取到的code为"+code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+SECRET+"&code="+code+"&grant_type=authorization_code";
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
            String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+OPENID+"&lang=zh_CN";
            doc = Jsoup.connect(url2).get();
            html = doc.body().html().toString();
            map = gson.fromJson(html, HashMap.class);
            NICKNAME = map.get("nickname").toString();

        }catch (IOException io){
            System.out.println(io.getMessage());
            System.out.println("cant touch url");
        }
        OPENID = "aaa";
        NICKNAME = "xiaoming";


        /*String resource = "cn/ixuhan/xdwy/data/vote.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(reader).openSession();
        sqlSession.getMapper().*/


        return SUCCESS;
    }



    //测试
    @Action(value = "vote",results = {@Result(name = "success", location = "/index.jsp")})
    public String test(){

        return "success";
    }
}
