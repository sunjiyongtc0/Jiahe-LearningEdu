package sjy.jiahe.controller.apiController;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sjy.jiahe.controller.BaseController;

@RequestMapping("/api")
@RestController
//@Api(value = "测试Controller", tags = { "测试访问接口" })
public class testController extends BaseController {

    @GetMapping("/t1")
//    @ApiOperation(value = "打印一条数据")
    public  String t1(){
        System.out.println("ssswwwwwwt1");

        return "ssssssssssssss";
    }

    @PostMapping ("/t2")
//    @ApiOperation(value = "打印一条数据")
    public  String t2(){

        System.out.println("ssswwwwwwt2");
        return "ssssssssssssss";
    }
}
