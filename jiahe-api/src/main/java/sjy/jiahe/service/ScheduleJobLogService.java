
package sjy.jiahe.service;



import com.baomidou.mybatisplus.extension.service.IService;
import sjy.jiahe.entity.ScheduleJobLogEntity;
import sjy.jiahe.resoult.PageUtils;

import java.util.Map;

/**
 * 定时任务日志
 *
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
}
