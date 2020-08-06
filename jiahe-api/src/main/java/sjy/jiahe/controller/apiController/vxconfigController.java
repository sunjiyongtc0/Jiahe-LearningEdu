package sjy.jiahe.controller.apiController;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sjy.jiahe.controller.BaseController;
import sjy.jiahe.entity.SysUserEntity;
import sjy.jiahe.resoult.Res;
import sjy.jiahe.service.SysUserService;
import sjy.jiahe.utils.ApiUserUtils;



@Controller
@RequestMapping("/api/vx")
public class vxconfigController extends BaseController {

    @Autowired
    SysUserService  sysUserService;

    //用户登录同步，指向userid与openid关联；
    @PostMapping("/loginUser/{logincode}")
    @ResponseBody
    public Res loginUser(@PathVariable("logincode") String  logincode){
        JSONObject sessionData =ApiUserUtils.getWebAccess(logincode);
                System.out.println(sessionData);
        if (null == sessionData || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
            return Res.error("登录失败");
        }else{
            String openid=sessionData.getString("openid");
            String sessionKey=sessionData.getString("session_key");
            SysUserEntity user= sysUserService.wxSync(openid);
            //根据微信的openid 获取用户信息
            System.out.println(Res.ok().put("openid",openid).put("user",user));
            return Res.ok().put("openid",openid).put("user",user);
        }

    }
    //注册微信用户信息同步；数据库如果username&mobile相同这userID与openid 关联在sys_wx_user表中
    @RequestMapping(value="/relationUser",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Res relationUser(@RequestBody JSONObject params){
        String logincode=params.getString("code");
        JSONObject sessionData =ApiUserUtils.getWebAccess(logincode);
        System.out.println(sessionData);
        if (null == sessionData || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
            return Res.error("登录失败");
        }else{
            String openid=sessionData.getString("openid");
            String username=params.getJSONObject("user").getString("username");
            String mobile=params.getJSONObject("user").getString("mobile");
            System.out.println(openid+"|"+username+"|"+mobile);
            //TODO
            //数据库如果username&mobile相同这userID与openid 关联在sys_wx_user表中
        return Res.ok();
        }
    }

}
