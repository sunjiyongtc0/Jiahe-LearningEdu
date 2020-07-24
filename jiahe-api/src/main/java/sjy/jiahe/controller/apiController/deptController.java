
package sjy.jiahe.controller.apiController;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sjy.jiahe.controller.BaseController;
import sjy.jiahe.entity.SysDeptEntity;
import sjy.jiahe.resoult.Res;
import sjy.jiahe.service.SysDeptService;
import sjy.jiahe.utils.Constant;

import java.util.HashMap;
import java.util.List;


/**
 * 部门管理
 *
 */
@RestController
@RequestMapping("/api/dept")
public class deptController extends BaseController {
	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
//	@RequiresPermissions("sys:dept:list")
	public List<SysDeptEntity> list(){
		List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());

		return deptList;
	}

	/**
	 * 选择部门(添加、修改菜单)
	 */
	@RequestMapping("/select")
//	@RequiresPermissions("sys:dept:select")
	public Res select(){
		List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());

		//添加一级部门
		if(getUserId() == Constant.SUPER_ADMIN){
			SysDeptEntity root = new SysDeptEntity();
			root.setDeptId(0L);
			root.setName("一级部门");
			root.setParentId(-1L);
			root.setOpen(true);
			deptList.add(root);
		}

		return Res.ok().put("deptList", deptList);
	}

	/**
	 * 上级部门Id(管理员则为0)
	 */
	@RequestMapping("/info")
	@RequiresPermissions("sys:dept:list")
	public Res info(){
		long deptId = 0;
		if(getUserId() != Constant.SUPER_ADMIN){
			List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());
			Long parentId = null;
			for(SysDeptEntity sysDeptEntity : deptList){
				if(parentId == null){
					parentId = sysDeptEntity.getParentId();
					continue;
				}

				if(parentId > sysDeptEntity.getParentId().longValue()){
					parentId = sysDeptEntity.getParentId();
				}
			}
			deptId = parentId;
		}

		return Res.ok().put("deptId", deptId);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{deptId}")
//	@RequiresPermissions("sys:dept:info")
	public Res info(@PathVariable("deptId") Long deptId){
		SysDeptEntity dept = sysDeptService.getById(deptId);

		return Res.ok().put("dept", dept);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
//	@RequiresPermissions("sys:dept:save")
	public Res save(@RequestBody SysDeptEntity dept){
		sysDeptService.save(dept);
		return Res.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
//	@RequiresPermissions("sys:dept:update")
	public Res update(@RequestBody SysDeptEntity dept){
		sysDeptService.updateById(dept);
		
		return Res.ok();
	}
	
	/**
	 * 删除
	 */
//	@RequiresPermissions("sys:dept:delete")
	@DeleteMapping("/del/{id}")
	public  Res del(@PathVariable("id") long deptId){
		//判断是否有子部门
		List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
		if(deptList.size() > 0){
			return Res.error("请先删除子部门");
		}
		sysDeptService.removeById(deptId);
		
		return Res.ok();
	}
	
}
