
package sjy.jiahe.service;


import com.baomidou.mybatisplus.extension.service.IService;
import sjy.jiahe.entity.SysRoleEntity;
import sjy.jiahe.resoult.PageUtils;

import java.util.Map;

/**
 * 角色
 *
 */
public interface SysRoleService extends IService<SysRoleEntity> {

	PageUtils queryPage(Map<String, Object> params);

	void saveRole(SysRoleEntity role);

	void update(SysRoleEntity role);
	
	void deleteBatch(Long[] roleIds);

}
