package cn.tycoding.boot.modules.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 角色表(Role)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:45:22
 */
@Data
@TableName("sys_role")
@ApiModel(value = "角色表实体")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 547891924677981054L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "角色ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 上级节点
     */
    @ApiModelProperty(value = "上级节点")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @NotNull(message = "角色名称不能为空")
    private String name;

    /**
     * 角色别名
     */
    @ApiModelProperty(value = "角色别名")
    private String alias;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String des;
}
