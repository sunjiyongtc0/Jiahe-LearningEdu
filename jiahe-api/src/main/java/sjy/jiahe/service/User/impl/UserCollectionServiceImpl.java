package sjy.jiahe.service.User.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sjy.jiahe.dao.User.UserCollectionDao;
import sjy.jiahe.entity.User.UserCollectionEntity;
import sjy.jiahe.service.User.UserCollectionService;

import java.util.List;

@Service("UserCollectionService")
public class UserCollectionServiceImpl  extends ServiceImpl<UserCollectionDao, UserCollectionEntity> implements UserCollectionService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserCollectionEntity> findImgByUserId(long UserId){
        List<UserCollectionEntity> luc=  baseMapper.findImgByUserId(UserId);
        return luc;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delByImgName(String ImgName){
        return  baseMapper.delByImgName(ImgName);
    }
}
