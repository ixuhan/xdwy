package cn.ixuhan.xdwy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
* @author hank 2017/6/5
* @time 12:07:23
* @version V1.0.0
* @description 微信认证步骤
*/
public class SHA1 {
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String token = WechatInfo.getTOKEN();

        //1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {token, timestamp, nonce};
        Arrays.sort(arr);
        System.out.println("sort:" + arr);

        //2.将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; ++i) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("tmpStr:" + tmpStr);

        //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return ((tmpStr != null) ? tmpStr.equals(signature.toUpperCase()) : false);
    }

    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; ++i) {
            strDigest = strDigest + byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4 & 0xF)];
        tempArr[1] = Digit[(mByte & 0xF)];
        String s = new String(tempArr);
        return s;
    }
}