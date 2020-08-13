package sjy.jiahe.entity.Seckill;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_success_killed")
public class SuccessKilledEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId
    private long id;
    private long seckillId;
    private long userId;
    private int state;
    /**
     * 创建时间
     */
    private Date createTime;

}

