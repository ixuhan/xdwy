package cn.ixuhan.xdwy.util;

import cn.ixuhan.xdwy.action.WechatAction;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* @author hank 2017/6/4
* @time 22:12:33
* @version V1.0.0
* @description 获取微信配置信息
*/
public class WechatInfo {
    private static String APPID = "";
    private static String SECRET = "";

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

    //返回APPID
    public static String getAPPID(){
        return APPID;
    }

    //返回SECRET
    public static String getSECRET(){
        return SECRET;
    }
}
