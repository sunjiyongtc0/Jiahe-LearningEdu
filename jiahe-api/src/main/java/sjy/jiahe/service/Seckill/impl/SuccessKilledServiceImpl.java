package sjy.jiahe.service.Seckill.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sjy.jiahe.dao.Seckill.SuccessKilledDao;
import sjy.jiahe.entity.Seckill.SuccessKilledEntity;
import sjy.jiahe.resoult.Res;
import sjy.jiahe.service.Seckill.SuccessKilledService;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service("SuccessKilledService")
public class SuccessKilledServiceImpl extends ServiceImpl<SuccessKilledDao, SuccessKilledEntity> implements SuccessKilledService {
    /**
     * 思考：为什么不用synchronized
     * service 默认是单例的，并发下lock只有一个实例
     */
    private Lock lock = new ReentrantLock(true);//互斥锁 参数默认false，不公平锁


    @Override
    public int startSeckilLock(long seckillId, long userId) {
        int state=0;
        try {
            lock.lock();
          long sum=  baseMapper.findKillCount(seckillId);
          if(sum<3){
              System.out.println("seckillId"+seckillId);
              System.out.println("userId"+userId);
              SuccessKilledEntity successKilledEntity=new SuccessKilledEntity();
              successKilledEntity.setSeckillId(seckillId);
              successKilledEntity.setUserId(userId);
              successKilledEntity.setCreateTime(new Date());
              successKilledEntity.setState(1);
              baseMapper.insert(successKilledEntity);
              state=1;
          }else{
              state=2;
          }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return state;
    }

    public int findKillByUserId(long seckillId, long userId){
        int i=0;
        SuccessKilledEntity successKilledEntity=baseMapper.findKillByUserId(seckillId,userId);
        if(successKilledEntity!=null){
            i=1;
        }
        return i;
    }

}
