package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /*
        查询所有角色&条件进行查询
     */
    public List<Role> findALlRole(Role role);

    /*
        根据角色ID查询该角色关联的菜单讯息ID [1,2,3,5]
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
        根据roleId清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer rid);

    /*
        为角色分配菜单讯息
     */
    public void roleContextMenu(Role_menu_relation roleMenuRelation);

    /*
        删除角色
     */
    public void deleteRole(Integer roleId);
}

