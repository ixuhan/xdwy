package cn.ixuhan.xdwy.util;

import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLEncoder;

/**
 * @author hank 2017/6/5
 * @version V1.0.0
 * @time 17:26:45
 * @description 智能回复青云接口
 */
public class QingYun {
    public static String getReply(String data) {
        String parseData = "1";
        try {
            parseData = URLEncoder.encode(data, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String apiUrl = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=" +
                parseData;
        System.out.println(apiUrl);
        String result = null;
        String content = null;
        try {
            Document document = Jsoup.connect(apiUrl).get();
            JSONObject jsonObject = JSONObject.fromObject(document
                    .getElementsByTag("body").text());
            result = jsonObject.getString("result");
            System.out.println("result=" + result);
            content = jsonObject.getString("content");
            System.out.println("content=" + content);
        } catch (Exception e) {
            e.getMessage();
        }

        if ("0".equals(result)) {
            return content;
        }
        return "您发的信息出错了!";
    }

    public static void main(String args[]){
        System.out.println("http://wx.qlogo.cn/mmopen/eTBFF2uBxbAH5IhygqicKGsupu2HYxFlyFaFicxmfPl6icLc3ajzFRRo0ODaFKhVAiaicJdqS0lu09U9cOibQ3fmcQCA/0".split("/")[4]);
    }
}