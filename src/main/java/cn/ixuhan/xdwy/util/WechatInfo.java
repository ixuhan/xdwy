package cn.ixuhan.xdwy.util;

import cn.ixuhan.xdwy.action.WechatAction;
import com.google.gson.Gson;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author hank 2017/6/4
 * @version V1.0.0
 * @time 22:12:33
 * @description 获取微信配置信息
 */
public class WechatInfo {
    private static String APPID = "";
    private static String SECRET = "";
    private static String TOKEN = "";
    private static String access_token = "";
    private static long expires_in = 7200;//token 过期时间
    private static Date getTokenTime;

    //初始化获取appid和secret
    static {
        //建立配置容器
        Properties properties = new Properties();
        //读取配置文件
        InputStream inputStream = WechatAction.class.getResourceAsStream("/wechat.properties");
        try {
            //初始化时间
            getTokenTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1995-01-01 00:00:00");
            //把读取到的配置文件加载到配置容器
            properties.load(inputStream);
            //获取APPID
            APPID = properties.get("wechat.APPID").toString();
            //获取SERCET
            SECRET = properties.get("wechat.SECRET").toString();
            //获取TOKEN
            TOKEN = properties.get("wechat.TOKEN").toString();
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
            System.out.println("cant init pe");
        } catch (IOException io) {
            System.out.println(io.getMessage());
            System.out.println("cant load wechat.properties");
        }
    }

    public static String getAccess_token() {
        long diff = (getNowTime().getTime() - getTokenTime.getTime()) / 1000; //获取上次与本次TOKEN索要时间差
        if (diff < expires_in) {//如果相隔时间小于expires_in，则直接返回之前的access_token
            return access_token;
        }
        String access_uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET;
        try {
            Document doc = Jsoup.connect(access_uri).ignoreContentType(true).get();
            //返回网页内容
            String html = doc.body().html().toString();
            Gson gson = new Gson();
            Map map = gson.fromJson(html, HashMap.class);
            //解析网页拿到access_token
            access_token = map.get("access_token").toString();
            //解析网页拿到expires_in
            expires_in = Long.valueOf(map.get("expires_in").toString().split("\\.")[0]);
            getTokenTime = getNowTime(); //刷新获取token时间
        } catch (IOException io) {
            System.out.println(io.getMessage());
            System.out.println("cant touch url");
        }
        return access_token;
    }

    public static Date getNowTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = new Date().toString();
        Date date = null;
        Date returnDate = null;
        try {
            date = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK).parse(dateStr);
            returnDate = df.parse(df.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnDate;
    }

    //返回APPID
    public static String getAPPID() {
        return APPID;
    }

    //返回SECRET
    public static String getSECRET() {
        return SECRET;
    }

    //返回TOKEN
    public static String getTOKEN() {
        return TOKEN;
    }

    public static JSONObject getOpenIdAndToken(String code)
    {
        JSONObject json = new JSONObject();
        String OPENID = "";
        String NICKNAME = "";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+SECRET+"&code="+code+"&grant_type=authorization_code";
        try {
            Document doc = Jsoup.connect(url).get();
            //返回网页内容
            String html = doc.body().html().toString();
            JSONObject object = new JSONObject(html);

            String access_token = object.getString("access_token");
            OPENID = object.getString("openid");
            json.put("OPENID",OPENID);

            //继续访问获取用户昵称
            String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+OPENID+"&lang=zh_CN";
            doc = Jsoup.connect(url2).get();
            html = doc.body().html().toString();
            object = new JSONObject(html);
            NICKNAME = object.getString("nickname");
            json.put("NICKNAME",NICKNAME);

        }catch (IOException io){
            System.out.println(io.getMessage());
            System.out.println("can't touch url");
        } catch (JSONException e) {
            System.out.println("json error");
        }
        return json;
    }
}
