package cn.tycoding.boot.modules.upms.service;

import cn.tycoding.boot.modules.upms.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 角色资源关联表(RoleMenu)表服务接口
 *
 * @author tycoding
 * @since 2020-10-15 12:34:08
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 根据角色ID删除该角色的权限关联信息
     */
    void deleteRoleMenusByRoleId(Long roleId);

    /**
     * 根据权限ID删除角色权限关联信息
     */
    void deleteRoleMenusByMenuId(Long menuId);
}