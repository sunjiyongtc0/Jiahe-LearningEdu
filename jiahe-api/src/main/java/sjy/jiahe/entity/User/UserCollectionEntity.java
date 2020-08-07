package sjy.jiahe.entity.User;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户收藏表
 * */
@Data
@TableName("t_user_collection")
public class UserCollectionEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long Id;

    /**
     * 用户ID
     */
    private Long userId;

    private int  typeId;

    private int  flagId;

    private String  remarks;

    private String pathSrc;

    private Date creatTime;


}
