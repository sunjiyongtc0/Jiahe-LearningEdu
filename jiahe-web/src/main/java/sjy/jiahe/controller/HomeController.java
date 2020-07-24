package sjy.jiahe.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sjy.jiahe.annotation.SysLog;
import sjy.jiahe.entity.SysUserEntity;
import sjy.jiahe.service.SysUserRoleService;
import sjy.jiahe.service.SysUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

//    @SysLog("登录系统")
    @RequestMapping(value = {"/", "index.html"})
    public String index(){
        request.setAttribute("username", getUser().getUsername());
        return "index";
    }

    @RequestMapping(value = {"/", "home.html"})
    public String home(){
        return "home";
    }

    @RequestMapping(value = {"/", "class.html"})
    public String classIndex(){
        return "modules/class";
    }
    @RequestMapping(value = {"/", "teacher.html"})
    public String teacherIndex(){
        return "modules/teacher";
    }
    @RequestMapping(value = {"/", "info.html/{id}"})
    public String infoIndex(@PathVariable ("id") long id){
        if(id==-1) {
            SysUserEntity sysUserEntity=getUser();
            //获取用户所属的角色列表
            List<Long> roleIdList = sysUserRoleService.queryRoleIdList(sysUserEntity.getUserId());
            sysUserEntity.setRoleIdList(roleIdList);
            String s = JSONObject.toJSONString(sysUserEntity);
            String ss = s.replace("\"", "\'");
            request.setAttribute("user", ss);
        }else if(id==0){
            request.setAttribute("user", 0);
        }else{
            SysUserEntity sysUserEntity= userService.getById(id);
            //获取用户所属的角色列表
            List<Long> roleIdList = sysUserRoleService.queryRoleIdList(sysUserEntity.getUserId());
            sysUserEntity.setRoleIdList(roleIdList);
            String s = JSONObject.toJSONString(sysUserEntity);
            String ss = s.replace("\"", "\'");
            request.setAttribute("user", ss);
        }
        return "modules/info";
    }
    @RequestMapping(value = {"/", "payment.html"})
    public String paymentIndex(){
        return "modules/payment";
    }
}
