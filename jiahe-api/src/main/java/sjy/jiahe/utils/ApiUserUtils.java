package sjy.jiahe.utils;


public class ApiUserUtils {



    //替换字符串
    public static String getWebAccess(String wxWebAccessTokenhttps,String wxAppId ,String wxSecret,String CODE) {
       String AccessToken= wxWebAccessTokenhttps.replaceFirst("%s",wxAppId).replaceFirst("%s",wxSecret).replaceFirst("%s",CODE);
        return AccessToken;
    }

}
