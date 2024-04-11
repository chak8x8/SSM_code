package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/roleController")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

     /*
        查询所有角色&条件进行查询
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色成功", allRole);
        return responseResult;
    }

    /*
       查询所有父子菜单讯息(分配菜单的第一个接口)
    */
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){

        // -1表示所有的父子级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        // 响应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList", menuList);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有父子菜单讯息成功", map);
        return responseResult;
    }

     /*
       根据角色ID查询该角色关联的菜单讯息ID [1,2,3,5]
    */
     @RequestMapping("/findAllMfindMenuByRoleIdenu")
    public ResponseResult findMenuByRoleId(Integer roleId){
         List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
         ResponseResult responseResult = new ResponseResult(true, 200, "查询角色ID查询该角色关联的菜单讯息成功", menuByRoleId);
         return responseResult;
     }

     /*
        为角色分配菜单讯息
      */
    @RequestMapping("/roleContextMenu")
    public  ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);

        return responseResult;
    }

    /*
       删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer roleId){
        roleService.deleteRole(roleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色", null);
        return responseResult;
    }
}
