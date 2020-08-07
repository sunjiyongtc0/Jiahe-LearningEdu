package sjy.jiahe.service.User;

import com.baomidou.mybatisplus.extension.service.IService;
import sjy.jiahe.entity.User.UserCollectionEntity;

import java.util.List;


public interface UserCollectionService  extends IService<UserCollectionEntity> {

     List<UserCollectionEntity> findImgByUserId(long UserId);

     int delByImgName(String ImgName);
}
