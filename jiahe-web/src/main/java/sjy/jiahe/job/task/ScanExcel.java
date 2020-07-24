package sjy.jiahe.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 测试定时任务(演示Demo，可删除)
 *
 * scanExcel为spring bean的名称
 *
 */
@Component("scanExcel")
public class ScanExcel implements ITask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(String params){
        logger.debug("ScanExcel时任务正在执行，参数为：{}", params);
    }
}