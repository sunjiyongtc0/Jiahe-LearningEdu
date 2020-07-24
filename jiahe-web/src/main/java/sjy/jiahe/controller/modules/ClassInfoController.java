package sjy.jiahe.controller.modules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sjy.jiahe.controller.BaseController;

@Controller
public class ClassInfoController extends BaseController {

    @RequestMapping(value = {"/", "classinfo.html"})
    public String index(){
            return "modules/classInfo";
    }

}
