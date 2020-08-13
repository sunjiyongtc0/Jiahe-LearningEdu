
package sjy.jiahe.dao.Seckill;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sjy.jiahe.entity.Seckill.SuccessKilledEntity;

/**
 * 秒杀成功入库
 *
 */
@Mapper
public interface SuccessKilledDao extends BaseMapper<SuccessKilledEntity> {

 long findKillCount(Long seckillId);

 SuccessKilledEntity findKillByUserId(Long seckillId ,Long userId);

}
