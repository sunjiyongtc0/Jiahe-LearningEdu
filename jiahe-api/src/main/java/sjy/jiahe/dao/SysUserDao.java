
package sjy.jiahe.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sjy.jiahe.entity.SysUserEntity;

import java.util.List;

/**
 * 系统用户
 *
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);


	/**
	 * 将微信openid与用户关联
	 * */
	SysUserEntity wxSync(String openId);
}
