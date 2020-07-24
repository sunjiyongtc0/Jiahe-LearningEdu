package sjy.jiahe.controller.apiController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sjy.jiahe.annotation.SysLog;
import sjy.jiahe.controller.BaseController;
import sjy.jiahe.resoult.PageUtils;
import sjy.jiahe.resoult.Res;
import sjy.jiahe.service.SysLogService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/log")
public class logController extends BaseController {

    @Autowired
    private SysLogService log;


    /**
     * 所有日志列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("sys:user:list")
//    public Res list(@RequestParam Map<String, Object> params){
    public Res list(){
        Map<String, Object> params=new HashMap<>();
            params.put("key","");
            PageUtils page = log.queryPage(params);
        return Res.ok().put("page", page);
    }



/**
 *
 * 删除角色
 * */
    @SysLog("删除日志")
    @DeleteMapping("/del/{id}")
    public  Res del(@PathVariable("id") long id){
        log.removeById(id);
        return Res.ok();
    }


}
