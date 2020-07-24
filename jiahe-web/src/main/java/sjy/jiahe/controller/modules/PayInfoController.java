package sjy.jiahe.controller.modules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sjy.jiahe.controller.BaseController;

@Controller
public class PayInfoController extends BaseController {

    @RequestMapping(value = {"/", "payshow.html"})
    public String payshow(){
            return "modules/payshow";
    }

    @RequestMapping(value = {"/", "payline.html"})
    public String payline(){
        return "modules/payLine";
    }
}
