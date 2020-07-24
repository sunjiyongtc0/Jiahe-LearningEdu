package sjy.jiahe.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    //内容错误，走500页
    @RequestMapping("500.html")
    public String error_500(){
        return "error/500";
    }

    //404重定向到首页
    @RequestMapping("404.html")
    public String notFound(){
        return "redirect:index.html";
    }
}
