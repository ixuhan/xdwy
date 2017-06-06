import cn.ixuhan.xdwy.util.WechatInfo;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author hank 2017/6/4
 * @version V1.0.0
 * @time 13:36:06
 * @description 创建菜单
 */
public class Menu {
    public static void main(String args[]) throws Exception {
        //urlEncode编码
        String redirect_uri = "http%3a%2f%2fxdwy.ixuhan.cn%2fweChatVote.do";
        String uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WechatInfo.getAPPID() + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        String menu_String = "{\n" +
                "    \"button\": [\n" +
                "        {\n" +
                "            \"type\": \"view\", \n" +
                "            \"name\": \"满意度投票\", \n" +
                "            \"url\": \"" + uri + "\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"type\": \"click\", \n" +
                "            \"name\": \"报修流程\", \n" +
                "            \"key\": \"bxlc\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"type\": \"click\", \n" +
                "            \"name\": \"客服电话\", \n" +
                "            \"key\": \"kfdh\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        System.out.println(menu_String);
        String access_token = WechatInfo.getAccess_token();
        String post_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
        System.out.println(access_token);
        String result = sendPost(post_URL, menu_String);
        System.out.println("post结果=" + result);
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
                result = result + line;
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null)
                    in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
