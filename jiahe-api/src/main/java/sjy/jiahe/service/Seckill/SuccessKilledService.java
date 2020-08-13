package sjy.jiahe.service.Seckill;

import com.baomidou.mybatisplus.extension.service.IService;
import sjy.jiahe.entity.Seckill.SuccessKilledEntity;
import sjy.jiahe.resoult.Res;

public interface SuccessKilledService extends IService<SuccessKilledEntity> {

    int startSeckilLock(long seckillId, long userId);

    int  findKillByUserId(long seckillId, long userId);
}
