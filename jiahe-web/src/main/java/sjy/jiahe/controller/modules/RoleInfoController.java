package sjy.jiahe.controller.modules;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sjy.jiahe.controller.BaseController;
import sjy.jiahe.service.SysRoleService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RoleInfoController extends BaseController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysRoleService roleService;


    @RequestMapping(value = {"/", "roleList.html"})
    public String userList(){
        return "modules/roleList";
    }



    @RequestMapping(value = {"/", "roleInfo.html/{id}"})
    public String infoIndex(@PathVariable("id") long id){
         if(id==0){
            request.setAttribute("role", 0);
        }else{
            String s = JSONObject.toJSONString( roleService.getById(id));
            String ss = s.replace("\"", "\'");
            request.setAttribute("role", ss);
        }
        return "modules/roleInfo";
    }


}
