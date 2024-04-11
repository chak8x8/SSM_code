package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /*
        用户分页以及多条件组合查询
     */
    public List<User> findAllUsersByPage(UserVo userVo);

    /*
        修改用户状态
     */
    public void updateUserStatus(@Param("id") Integer id, @Param("status") String status);

    /*
        帐户登陆 （根据用户名查询具体的用户讯息）
     */
    public User login(User user);

    /*
        根据用户ID查询关联的角色讯息
     */
    public List<Role> findUserRelationRoleById(Integer userId);

    /*
       根据用户ID清空中间表
     */
    public void deleteUserContextRole(Integer userId);

    /*
        分配角色
     */
    public void userContextRole(User_Role_relation userRoleRelation);

    /*
        1. 调用findUserRelationRoleById 多个角色

        2. 根据角色ID，查询角色所拥有的顶级菜单(-1)
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> roleIdList);

    /*
        3. 根据Parent ID，查询子菜单讯息
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /*
        4. 获取用户拥有的资源权限讯息
     */
    public List<Resource> findResourceByRoleId(List<Integer> roleIdList);
}
