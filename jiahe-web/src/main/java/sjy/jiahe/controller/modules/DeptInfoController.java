package sjy.jiahe.controller.modules;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sjy.jiahe.controller.BaseController;
import sjy.jiahe.service.SysDeptService;
import sjy.jiahe.service.SysRoleService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeptInfoController extends BaseController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysDeptService deptService;


    @RequestMapping(value = {"/", "deptList.html"})
    public String userList(){
        return "modules/deptList";
    }



    @RequestMapping(value = {"/", "deptInfo.html/{id}"})
    public String infoIndex(@PathVariable("id") long id){
         if(id==0){
            request.setAttribute("dept", 0);
        }else{
            String s = JSONObject.toJSONString( deptService.getById(id));
            String ss = s.replace("\"", "\'");
            request.setAttribute("dept", ss);
        }
        return "modules/deptInfo";
    }


}
