package cn.ixuhan.xdwy.util;

import cn.ixuhan.xdwy.model.VoteRecord;
import cn.ixuhan.xdwy.service.VoteItemService;
import cn.ixuhan.xdwy.service.VoteService;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

/**
 * @author hank 2017/6/5
 * @version V1.0.0
 * @time 15:13:59
 * @description 解析微信传来的消息类型
 */
@Service("messageAnalyze")
public class MessageAnalyze {
    @Autowired
    private VoteItemService voteItemService;

    public void Message(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InputStream is = request.getInputStream();
        int size = request.getContentLength();
        byte[] buffer = new byte[size];
        byte[] xmldataByte = new byte[size];
        int count = 0;
        int rbyte = 0;

        while (count < size) {
            rbyte = is.read(buffer);
            for (int i = 0; i < rbyte; ++i) {
                xmldataByte[(count + i)] = buffer[i];
            }
            count += rbyte;
        }
        is.close();
        String requestStr = new String(xmldataByte, "UTF-8");
        System.out.println(requestStr);
        try {
            ManageMessage(requestStr, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析收到的微信消息
     *
     * @param requestStr
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void ManageMessage(String requestStr, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            XMLSerializer xmlSerializer = new XMLSerializer();
            JSONObject jsonObject = (JSONObject) xmlSerializer.read(requestStr);
            String msgtype = jsonObject.getString("MsgType");

            if ("text".equals(msgtype)) {//如果接收到文本
                String Content = jsonObject.getString("Content");//获取接收到的文本内容
                if ("你是谁".equals(Content)) {
                    jsonObject.put("Content", "我是襄阳襄大同源物业服务有限公司，做专业物业，让您安享美好校园生活！");
                } else if (Content.indexOf("密码xmm") != -1) { //如果是秘密
                    //1选项加X人密码xmm
                    try {
                        int Operate = ("加".equals("" + Content.split("选项")[1].split("人")[0].charAt(0))) ? 0 : 1;
                        int Count_Num = Integer.valueOf(Content.split("选项")[1].split("人")[0].substring(1, Content.split("选项")[1].split("人")[0].length()));
                        int Which = Integer.valueOf(Content.split("选项")[0]) - 1;
                        int count = voteItemService.change_count(Operate, Count_Num, Which);
                        System.out.println("conut:" + count);
                        jsonObject.put("Content", "命令出错");
                        if (count > 0) {
                            jsonObject.put("Content", "操作成功");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        jsonObject.put("Content", "命令出错");
                    }
                } else if ("操作指南xmm".equals(Content)) {
                    jsonObject.put("Content", "回复：x选项加(减)n人密码xmm\n比如：\n1选项加10人密码xmm，代表把A选项加10人\n2选项减10人密码xmm，代表把B选项减10人");
                } else {
                    jsonObject.put("Content", QingYun.getReply(Content));//调用人工智能接口回复
                }
            } else if ("event".equals(msgtype)) {//如果接收到是事件
                String event = jsonObject.getString("Event");
                if ("CLICK".equals(event)) {//按键事件
                    String EventKey = jsonObject.getString("EventKey");//判断按了哪个按钮
                    if ("bxlc".equals(EventKey)) {//保修流程按钮被点击
                        jsonObject.put("Content", "拨打服务热线→报修登记→客服排单→上门维修→售后回访");
                    } else if ("kfdh".equals(EventKey)) {//客服电话按钮被点击
                        jsonObject.put("Content", "服务热线：15797189010\n" +
                                "(短号:689010)\n" +
                                "投诉/建议电话：18972075116");
                    }
                } else if ("subscribe".equals(event)) {//用户未关注时，进行关注后的事件推送
                    jsonObject.put("Content", "多谢您关注襄阳襄大同源物业服务有限公司，做专业物业，让您安享美好校园生活！");
                } else {
                    return;
                }
            }
            System.out
                    .println("SendMessage:" + jsonObject.getString("Content"));
            String responseStr = CreateRevertText(jsonObject);
            System.out.println(responseStr);
            OutputStream os = response.getOutputStream();
            os.write(responseStr.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String CreateRevertText(JSONObject jsonObject) {
        StringBuffer revert = new StringBuffer();
        revert.append("<xml>");
        revert.append("<ToUserName><![CDATA[" + jsonObject.get("FromUserName") +
                "]]></ToUserName>");
        revert.append("<FromUserName><![CDATA[" + jsonObject.get("ToUserName") +
                "]]></FromUserName>");
        revert.append("<CreateTime>" + jsonObject.get("CreateTime") +
                "</CreateTime>");
        revert.append("<MsgType><![CDATA[text]]></MsgType>");
        revert.append("<Content><![CDATA[" + jsonObject.get("Content") +
                "]]></Content>");
        revert.append("</xml>");
        return revert.toString();
    }
}