package sjy.jiahe.controller.modules;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sjy.jiahe.controller.BaseController;
import sjy.jiahe.service.SysLogService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogInfoController extends BaseController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService logService;


    @RequestMapping(value = {"/", "logList.html"})
    public String userList(){
        return "modules/logList";
    }






}
