package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    /*
        用户分页以及多条件组合查询
     */
    public PageInfo findAllUsersByPage(UserVo userVo);

    /*
        修改用户状态
     */
    public void updateUserStatus(@Param("id") Integer id, @Param("status") String status);

    /*
       帐户登陆 （根据用户名查询具体的用户讯息）
    */
    public User login(User user) throws Exception;

    /*
        分配角色（回显）
     */
    public List<Role> findUserRelationRoleById(Integer userId);

    /*
        用户关联角色
     */
    public void userContextRole(UserVo userVo);

    /*
        获取用户权限，进行菜单动态展示
     */
    public ResponseResult getUserPermissions(Integer userId);
}
