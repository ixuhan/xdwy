import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author hank 2017/6/4
 * @version V1.0.0
 * @time 13:36:06
 * @description Ceate Menu
 */
public class Menu {
    private static String APPID;
    private static String SECRET;

    //初始化获取appid和secret
    static {
        //建立配置容器
        Properties properties = new Properties();
        //读取配置文件
        InputStream inputStream = Menu.class.getResourceAsStream("/wechat.config");
        try {
            //把读取到的配置文件加载到配置容器
            properties.load(inputStream);
            //获取APPID
            APPID = properties.get("wechat.APPID").toString();
            //获取SERCET
            SECRET = properties.get("wechat.SECRET").toString();
        } catch (IOException io) {
            System.out.println(io.getMessage());
            System.out.println("cant load wechat.config");
        }
    }

    public static void main(String args[]) {
        String access_uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET;
        String access_token = "";
        try {
            Document doc = Jsoup.connect(access_uri).ignoreContentType(true).get();
            //返回网页内容
            String html = doc.body().html().toString();
            Gson gson = new Gson();
            Map map = gson.fromJson(html, HashMap.class);
            //解析网页拿到access_token
            access_token = map.get("access_token").toString();
        }catch (IOException io){
            System.out.println(io.getMessage());
            System.out.println("cant touch url");
        }
        String uri = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
        System.out.println(access_token);
    }
}
