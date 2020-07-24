
package sjy.jiahe.service;




import com.baomidou.mybatisplus.extension.service.IService;
import sjy.jiahe.entity.SysLogEntity;
import sjy.jiahe.resoult.PageUtils;

import java.util.Map;


/**
 * 系统日志
 *
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
