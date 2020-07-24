package sjy.jiahe.controller.modules;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sjy.jiahe.controller.BaseController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserInfoController  extends BaseController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = {"/", "userList.html"})
    public String userList(){
        return "modules/userList";
    }

}
