package cn.tycoding.boot.modules.upms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2020/10/15
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuTree<T> implements Serializable {
    private static final long serialVersionUID = 547891924677981054L;

    /**
     * 节点ID
     */
    @ApiModelProperty(value = "节点ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 父节点ID
     */
    @ApiModelProperty(value = "父节点ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 路由名称
     */
    @ApiModelProperty(value = "路由名称")
    private String name;

    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String path;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
     * 重定向地址
     */
    @ApiModelProperty(value = "重定向地址")
    private String redirect;

    /**
     * Vue组件地址
     */
    @ApiModelProperty(value = "Vue组件地址")
    private String component;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String perms;

    /**
     * icon && title 信息
     */
    @ApiModelProperty(value = "icon && title 信息")
    private MenuMeta meta;

    /**
     * 是否隐藏该节点
     */
    @ApiModelProperty(value = "是否隐藏该节点")
    private Boolean hidden;

    /**
     * 是否是外链
     */
    @ApiModelProperty(value = "是否是外链")
    private Boolean frame;

    /**
     * 子节点
     */
    @ApiModelProperty(value = "子节点")
    private List<MenuTree<T>> children = new ArrayList<>();
}
