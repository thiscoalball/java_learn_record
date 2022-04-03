package pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//mybatis-plus注解 和数据库里的表名映射
@TableName("tb_user")
public class User {
    @TableId("user_id")
    private Long id;
    @TableField("user_name")
    private String username;
    private String password;
    private String name;
    private Integer age;
    private String email;
}
