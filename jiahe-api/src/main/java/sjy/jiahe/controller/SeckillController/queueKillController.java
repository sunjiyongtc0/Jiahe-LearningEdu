package sjy.jiahe.controller.SeckillController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sjy.jiahe.controller.BaseController;
import sjy.jiahe.resoult.Res;
import sjy.jiahe.service.Seckill.SuccessKilledService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/seckill")
public class queueKillController  extends BaseController {
    private static AtomicInteger atomicLoop = new AtomicInteger(0);
    @Autowired
    private SuccessKilledService successKilledService;

    private final static Logger log = LoggerFactory.getLogger(queueKillController.class);
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    //创建线程池  调整队列数 拒绝服务
    private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(5));


    /**
     * 秒杀类型一
     * AtomicInteger线程安全，控制名额数，多线程入库用lock锁
     * atomicLoop安全int值可由数据库锁获得
     * 缺点，五人抢三张名额，两个未成功者不能返回秒杀失败
     * */
    @PostMapping("/startLock/{seckillId}")
    public Res startLock(@PathVariable("seckillId") long seckillId){
        long userId =getUserId();
        long killId =  seckillId;
        int  seckillCode=0;
//        final CountDownLatch latch = new CountDownLatch(5);//N个购买者
        if(atomicLoop.get()<5) {
            Runnable task = () -> {
                int state = successKilledService.startSeckilLock(killId, userId);
                System.out.println(state);
                if(state==1||state==2){
                    atomicLoop.incrementAndGet();
                    System.out.println(atomicLoop.get());
                }else{
                    System.out.println("网络异常！不影响秒杀名额");
                }
//                latch.countDown();
            };
            executor.execute(task);
        }else{
            return Res.ok().put("count" ,"名额已满！");
        }
        try {
            Thread.sleep(1000);
            seckillCode= successKilledService.findKillByUserId(seckillId,userId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            return Res.ok().put("seckillCode" ,seckillCode);
        }
//        try {
//            latch.await();// 等待所有人任务结束
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    @PostMapping("/startRedis/{seckillId}")
    public Res startRedis(@PathVariable("seckillId") long seckillId){
        long userId =getUserId();
        int  seckillCode=0;
        Runnable task = () -> {
            int state = successKilledService.startSeckilRedis(seckillId,userId);
            if(state==1||state==2){
                System.out.println("入库成功");
            }else{
            }
        };
        executor.execute(task);
        try {
            Thread.sleep(1000);
            seckillCode= successKilledService.findKillByUserId(seckillId,userId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            return Res.ok().put("seckillCode" ,seckillCode);
        }
    }

}
