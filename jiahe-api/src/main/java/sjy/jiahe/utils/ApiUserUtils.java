package sjy.jiahe.utils;


import com.alibaba.fastjson.JSONObject;

public class ApiUserUtils {



//    //替换字符串
//    public static String getWebAccess(String wxWebAccessTokenhttps,String wxAppId ,String wxSecret,String CODE) {
//       String AccessToken= wxWebAccessTokenhttps.replaceFirst("%s",wxAppId).replaceFirst("%s",wxSecret).replaceFirst("%s",CODE);
//        return AccessToken;
//    }

    public static JSONObject getWebAccess(String CODE) {
        System.out.println("getWebAccess====="+WxUtil.webAccessTokenhttps);
        String requestUrl= WxUtil.webAccessTokenhttps.replaceFirst("%s",WxUtil.appId).replaceFirst("%s",WxUtil.secret).replaceFirst("%s",CODE);
        JSONObject sessionData = CommonUtil.httpsRequest(requestUrl, "GET", null);
        return sessionData;
    }




}
