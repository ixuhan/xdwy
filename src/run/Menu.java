import cn.ixuhan.xdwy.util.WechatInfo;

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
        String menu_String = "{\"button\":[{\"name\":\"家电控制\",\"sub_button\":[{\"type\":\"click\",\"name\":\"控制灯\",\"key\":\"Light_Button\"},{\"type\":\"click\",\"name\":\"控制空调\",\"key\":\"AC_Button\"},{\"type\":\"click\",\"name\":\"控制热水器\",\"key\":\"HotWater_Button\"},{\"type\":\"click\",\"name\":\"人体监控器\",\"key\":\"Body_Button\"}]}],\"button\":[{\"name\":\"模式设置\",\"sub_button\":[{\"type\":\"click\",\"name\":\"外出模式\",\"key\":\"WCMS_Button\"},{\"type\":\"click\",\"name\":\"睡眠模式\",\"key\":\"SMMS_Button\"},{\"type\":\"click\",\"name\":\"休闲模式\",\"key\":\"XXMS_Button\"},{\"type\":\"click\",\"name\":\"普通模式\",\"key\":\"PTMS_Button\"}]}],\"button\":[{\"name\":\"其他\",\"sub_button\":[{\"type\":\"click\",\"name\":\"获取监控\",\"key\":\"JK_Button\"},{\"type\":\"click\",\"name\":\"设备状态\",\"key\":\"Status_Button\"},{\"type\":\"click\",\"name\":\"绑定手机\",\"key\":\"Setting_Button\"},{\"type\":\"click\",\"name\":\"数据记录\",\"key\":\"View_Button\"},{\"type\":\"click\",\"name\":\"帮助文档\",\"key\":\"Help_Button\"}]}]}";
        String access_token = WechatInfo.getAccess_token();
        String post_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" +access_token;
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
