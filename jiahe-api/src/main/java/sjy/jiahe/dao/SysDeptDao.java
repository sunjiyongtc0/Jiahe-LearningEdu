
package sjy.jiahe.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sjy.jiahe.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {

    List<SysDeptEntity> queryList(Map<String, Object> params);

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);

}
