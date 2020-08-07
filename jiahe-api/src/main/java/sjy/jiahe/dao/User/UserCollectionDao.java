
package sjy.jiahe.dao.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sjy.jiahe.entity.User.UserCollectionEntity;

import java.util.List;

/**
 * 系统用户
 *
 */
@Mapper
public interface UserCollectionDao extends BaseMapper<UserCollectionEntity> {


    List<UserCollectionEntity> findImgByUserId(long UserId);

    int delByImgName(String ImgName);
}
