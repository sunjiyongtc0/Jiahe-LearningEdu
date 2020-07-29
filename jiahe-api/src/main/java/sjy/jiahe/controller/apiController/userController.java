package sjy.jiahe.controller.apiController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sjy.jiahe.annotation.SysLog;
import sjy.jiahe.controller.BaseController;
import sjy.jiahe.entity.SysUserEntity;
import sjy.jiahe.resoult.PageUtils;
import sjy.jiahe.resoult.Res;
import sjy.jiahe.service.SysUserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class userController   extends BaseController {

    @Autowired
    private SysUserService  user;

    @GetMapping("/t")
    public List<Long> t(){
        return user.queryAllMenuId(1l);
    }

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("sys:user:list")
    public Res list(@RequestParam Map<String, Object> params){
        PageUtils page = user.queryPage(params);
        return Res.ok().put("page", page);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
//    @RequestMapping("/save")
//    @RequiresPermissions("sys:user:save")
    @PostMapping(value="/save",produces = "application/json;charset=UTF-8")
    public Res save(@RequestBody SysUserEntity userEntity){
//        ValidatorUtils.validateEntity(userEntity, AddGroup.class);
        user.saveUser(userEntity);
        return Res.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
//    @RequiresPermissions("sys:user:update")
    public Res update(@RequestBody SysUserEntity userEntity){
//        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        user.update(userEntity);

        return Res.ok();
    }



/**
 *
 * 删除
 * */
    @SysLog("删除用户")
    @DeleteMapping("/del/{id}")
    public  Res del(@PathVariable("id") long id){
        System.out.println(id);
        user.removeById(id);
        return Res.ok();
    }


  /**
   * 根据id获取用户
   * */
  @PostMapping("/finduser/{id}")
  public  Res finduser(@PathVariable("id") long id){
      SysUserEntity u=user.getById(id);
      return Res.ok().put("user",u);
  }

}
