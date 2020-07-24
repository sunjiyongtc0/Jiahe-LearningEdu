package sjy.jiahe.controller.apiController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sjy.jiahe.annotation.SysLog;
import sjy.jiahe.controller.BaseController;
import sjy.jiahe.entity.SysRoleEntity;
import sjy.jiahe.resoult.PageUtils;
import sjy.jiahe.resoult.Res;
import sjy.jiahe.service.SysRoleService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/role")
public class roleController extends BaseController {

    @Autowired
    private SysRoleService  role;

    /**
     * 所有角色列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("sys:user:list")
    public Res list(@RequestParam Map<String, Object> params){
        PageUtils page = role.queryPage(params);
        return Res.ok().put("page", page);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
//    @RequiresPermissions("sys:user:save")
    @PostMapping(value="/save",produces = "application/json;charset=UTF-8")
    public Res save(@RequestBody SysRoleEntity roleEntity){
//        ValidatorUtils.validateEntity(userEntity, AddGroup.class);
        role.saveRole(roleEntity);
        return Res.ok();
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @RequestMapping("/update")
//    @RequiresPermissions("sys:user:update")
    public Res update(@RequestBody SysRoleEntity roleEntity){
//        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        role.update(roleEntity);

        return Res.ok();
    }

/**
 *
 * 删除角色
 * */
    @SysLog("删除角色")
    @DeleteMapping("/del/{id}")
    public  Res del(@PathVariable("id") long id){
        role.removeById(id);
        return Res.ok();
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
//    @RequiresPermissions("sys:role:select")
    public Res select(){
        List<SysRoleEntity> list = role.list();
        return Res.ok().put("list", list);
    }

}
