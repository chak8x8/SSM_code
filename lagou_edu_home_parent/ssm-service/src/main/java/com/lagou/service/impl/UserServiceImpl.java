package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUsersByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> allUsersByPage = userMapper.findAllUsersByPage(userVo);

        PageInfo<User> userPageInfo = new PageInfo<>(allUsersByPage);
        return userPageInfo;
    }

    @Override
    public void updateUserStatus(Integer id, String status) {
        userMapper.updateUserStatus(id, status);
    }

    @Override
    public User login(User user) throws Exception {
        // 调用Mapper user1: 包含了密文密碼
        User user1 = userMapper.login(user);

        if(user1 != null && Md5.verify(user.getPassword(), "lagou", user1.getPassword())){
            return user1;
        } else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer userId) {
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);
        return roleList;
    }

    @Override
    public void userContextRole(UserVo userVo) {

        // 1. 根据用户ID清空中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());

        // 2. 再重新建立关联关系
        for (Integer roleId : userVo.getRoleIdList()) {

            // 封装数据
            User_Role_relation userRoleRelation = new User_Role_relation();
            userRoleRelation.setUserId(userVo.getUserId());
            userRoleRelation.setRoleId(roleId);

            Date date = new Date();
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);

            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedby("system");

            userMapper.userContextRole(userRoleRelation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        // 1. 获取当前用户拥有的角色
        List<Role> userRelationRole = userMapper.findUserRelationRoleById(userId);

        //2. 获取角色ID，保存到list集合中
        List<Integer> roleIdList = new ArrayList<>();

        for (Role role : userRelationRole) {
            roleIdList.add(role.getId());
        }

        // 3. 根据角色ID查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIdList);

        // 4. 查询封装父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenuByPid);
        }

        // 5. 获取资源讯息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIdList);

        // 6. 安装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenu);
        map.put("resourceList", resourceList);

        return new ResponseResult(true, 200, "获取用户权限讯息成功", map);
    }


}
