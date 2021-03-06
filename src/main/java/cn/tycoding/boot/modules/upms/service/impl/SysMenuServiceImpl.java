package cn.tycoding.boot.modules.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.constant.CommonConstant;
import cn.tycoding.boot.common.core.utils.MenuTreeUtil;
import cn.tycoding.boot.modules.auth.exception.TumoOAuth2Exception;
import cn.tycoding.boot.modules.upms.dto.MenuTree;
import cn.tycoding.boot.modules.upms.entity.SysMenu;
import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.mapper.SysMenuMapper;
import cn.tycoding.boot.modules.upms.service.SysMenuService;
import cn.tycoding.boot.modules.upms.service.SysRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:45:51
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> getUserMenuList(List<SysRole> sysRoleList) {
        List<Long> roleIds = sysRoleList.stream().map(SysRole::getId).collect(Collectors.toList());
        return baseMapper.build(roleIds, null);
    }

    @Override
    public List<MenuTree<SysMenu>> tree() {
        return MenuTreeUtil.build(this.list());
    }

    @Override
    public Dict baseTree() {
        List<SysMenu> list = this.list(new SysMenu());
        // 构建树形结构
        List<TreeNode<Object>> nodeList = CollUtil.newArrayList();
        list.forEach(t -> nodeList.add(
                new TreeNode<>(
                        t.getId(),
                        t.getParentId(),
                        t.getName(),
                        0
                )
        ));
        List<Long> ids = list.stream().map(SysMenu::getId).collect(Collectors.toList());
        List<Tree<Object>> tree = TreeUtil.build(nodeList, 0L);
        return Dict.create().set("ids", ids).set("tree", tree);
    }

    @Override
    public List<MenuTree<SysMenu>> build() {
        List<Long> roleIds = AuthUtil.getRoleIds();
        if (roleIds.size() == 0) {
            throw new TumoOAuth2Exception(AuthUtil.NOT_ROLE_ERROR);
        }
        if (AuthUtil.getRoleNames().contains(AuthUtil.ADMINISTRATOR)) {
            // 超级管理员，不做权限过滤
            roleIds.clear();
        }
        List<SysMenu> sysMenuList = baseMapper.build(roleIds, CommonConstant.MENU_TYPE_MENU);
        return MenuTreeUtil.buildTree(sysMenuList);
    }

    @Override
    public boolean checkName(SysMenu sysMenu) {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getName, sysMenu.getName());
        if (sysMenu.getId() != null && sysMenu.getId() != 0) {
            queryWrapper.ne(SysMenu::getId, sysMenu.getId());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    public List<SysMenu> list(SysMenu sysMenu) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().like(sysMenu.getName() != null, SysMenu::getName, sysMenu.getName()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysMenu sysMenu) {
        this.format(sysMenu);
        baseMapper.insert(sysMenu);
    }

    private void format(SysMenu sysMenu) {
        if (sysMenu.getParentId() == null) {
            sysMenu.setParentId(0L);
        } else {
            if (sysMenu.getPath().startsWith("/")) {
                sysMenu.setPath(sysMenu.getPath().substring(1));
            }
        }
        if (CommonConstant.MENU_TYPE_MENU.equals(sysMenu.getType())) {
            if (sysMenu.getIcon() == null) {
                sysMenu.setIcon(CommonConstant.MENU_ICON);
            }
            if (sysMenu.getComponent() != null && !sysMenu.getComponent().startsWith("/")) {
                sysMenu.setComponent("/" + sysMenu.getComponent());
            }
        }
        if (CommonConstant.MENU_TYPE_BUTTON.equals(sysMenu.getType())) {
            sysMenu.setPath(null);
            sysMenu.setIcon(null);
            sysMenu.setComponent(null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysMenu sysMenu) {
        this.format(sysMenu);
        baseMapper.updateById(sysMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        List<SysMenu> list = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, id));
        if (list.size() > 0) {
            throw new RuntimeException("该菜单包含子节点，不能删除");
        }
        sysRoleMenuService.deleteRoleMenusByMenuId(id);
        baseMapper.deleteById(id);
    }
}
