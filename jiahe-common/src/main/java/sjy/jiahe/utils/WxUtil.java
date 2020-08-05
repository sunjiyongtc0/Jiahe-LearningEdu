package sjy.jiahe.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sjy.jiahe.properties.WxConfigProperties;

import javax.annotation.PostConstruct;

@Component
public class WxUtil {

    public static WxConfigProperties wxInitProperties;

    public static  String appId;
    public static  String secret;
    public static  String webAccessTokenhttps;

    @Autowired
    private  WxConfigProperties wxConfigProperties;

    /**
     * 静态方法想使要使用一个非静态对象，需要做一个初始化【重要】
     */
    @PostConstruct
    public void init() {
        wxInitProperties=wxConfigProperties;
        appId=wxConfigProperties.getAppId();
        secret=wxConfigProperties.getSecret();
        webAccessTokenhttps=wxConfigProperties.getWebAccessTokenhttps();
    }
}
