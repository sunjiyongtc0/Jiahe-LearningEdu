
package sjy.jiahe.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sjy.jiahe.dao.SysUserDao;
import sjy.jiahe.entity.SysDeptEntity;
import sjy.jiahe.entity.SysUserEntity;
import sjy.jiahe.resoult.PageUtils;
import sjy.jiahe.service.SysDeptService;
import sjy.jiahe.service.SysUserRoleService;
import sjy.jiahe.service.SysUserService;
import sjy.jiahe.shiro.ShiroUtils;
import sjy.jiahe.utils.Constant;
import sjy.jiahe.utils.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysDeptService sysDeptService;

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
//	@DataFilter(subDept = true, user = false)
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");

		IPage<SysUserEntity> page = this.page(
				new Query<SysUserEntity>().getPage(params),
				new QueryWrapper<SysUserEntity>()
						.like(StrUtil.isNotBlank(username),"username", username)
						.apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
		);

		for(SysUserEntity sysUserEntity : page.getRecords()){
			SysDeptEntity sysDeptEntity = sysDeptService.getById(sysUserEntity.getDeptId());
			sysUserEntity.setDeptName(sysDeptEntity.getName());
			//获取用户所属的角色列表
			List<Long> roleIdList = sysUserRoleService.queryRoleIdList(sysUserEntity.getUserId());
			sysUserEntity.setRoleIdList(roleIdList);
		}

		return new PageUtils(page);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setSalt(salt);
		user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
		this.save(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserEntity user) {
		if(StrUtil.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			SysUserEntity userEntity = this.getById(user.getUserId());
			user.setPassword(ShiroUtils.sha256(user.getPassword(), userEntity.getSalt()));
		}
		this.updateById(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}


	public  SysUserEntity  wxSync(String openId){
	 return 	baseMapper.wxSync(openId);
	}




//
//
//	@Override
//	public boolean updatePassword(Long userId, String password, String newPassword) {
//        SysUserEntity userEntity = new SysUserEntity();
//        userEntity.setPassword(newPassword);
//        return this.update(userEntity,
//        	new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
//    }

}
