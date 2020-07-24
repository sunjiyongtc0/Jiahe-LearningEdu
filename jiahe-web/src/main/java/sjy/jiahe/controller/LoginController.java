package sjy.jiahe.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjy.jiahe.resoult.Res;
import sjy.jiahe.shiro.ShiroUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController extends BaseController {
    @Autowired
    private Producer producer;


    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
    @PostMapping("loginto")
    @ResponseBody
    public Res loginto(String username, String password, String captcha,String type){
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//        if(!captcha.equalsIgnoreCase(kaptcha)){
//            return Res.error("验证码不正确");
//        }
            try{
            Subject subject = ShiroUtils.getSubject();
                if("1".equals(type)){
                    UsernamePasswordToken token = new UsernamePasswordToken("1", "2");
                    subject.login(token);
                    return Res.ok();
                }
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return Res.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return Res.error("账号或密码不正确");
        }catch (LockedAccountException e) {
            return Res.error("账号已被锁定,请联系管理员");
        }catch (AuthenticationException e) {
            return Res.error("账户验证失败");
        }

        return Res.ok();

    }


    @GetMapping("loginout")
    @ResponseBody
    public Res loginout(){
        ShiroUtils.logout();
        return Res.ok();
    }


}
