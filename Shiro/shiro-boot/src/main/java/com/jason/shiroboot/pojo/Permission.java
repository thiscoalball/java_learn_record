package com.jason.shiroboot.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_permission")
@ApiModel("权限实体类")
public class Permission {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "ID主键")
    private Integer id;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(name = "name", value = "权限名称")
    private String name;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(name = "url", value = "权限菜单URL")
    private String url;

}
