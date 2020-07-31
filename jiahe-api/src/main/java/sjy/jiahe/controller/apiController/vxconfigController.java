package sjy.jiahe.controller.apiController;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sjy.jiahe.controller.BaseController;
import sjy.jiahe.resoult.Res;
import sjy.jiahe.utils.ApiUserUtils;
import sjy.jiahe.utils.CommonUtil;

@Controller
@RequestMapping("/api/vx")
public class vxconfigController extends BaseController {

    @Value("${wx.appId}")
    private String wxAppId;
    @Value("${wx.secret}")
    private String wxSecret;
    @Value("${wx.webAccessTokenhttps}")
    private String  wxWebAccessTokenhttps;


    @GetMapping("/loginUser")
    public Res loginUser(){
        String requestUrl= ApiUserUtils.getWebAccess(wxWebAccessTokenhttps,wxAppId,wxSecret,"001pXoTm0nVscn1Xj7Tm0f36Li1pXoTg");
        System.out.println(requestUrl);
        JSONObject sessionData = CommonUtil.httpsRequest(requestUrl, "GET", null);
        System.out.println(sessionData);
        if (null == sessionData || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
            return Res.error("登录失败");
        }else{
            System.out.println(sessionData.getString("openid"));
            System.out.println(sessionData.getString("session_key"));
            return Res.ok();
        }

        //验证用户信息完整性
//        String sha1 = CommonUtil.getSha1(fullUserInfo.getRawData() + sessionData.getString("session_key"));
//        if (!fullUserInfo.getSignature().equals(sha1)) {
//            return toResponsFail("登录失败");
//        }
    }

}
