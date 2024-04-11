package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
       用户分页以及多条件组合查询
    */
    @RequestMapping("/findAllUsersByPage")
    public ResponseResult findAllUsersByPage(@RequestBody UserVo userVo) {
        PageInfo pageInfo = userService.findAllUsersByPage(userVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "分页多条件组合查询成功", pageInfo);
        return responseResult;
    }

    /*
        修改用户状态
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id, String status) {

        if ("ENABLE".equalsIgnoreCase(status)) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }

        userService.updateUserStatus(id, status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改用户状态成功", null);
        return responseResult;
    }

    /*
        帐户登陆 （根据用户名查询具体的用户讯息）
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest httpServletRequest) throws Exception {
        User user1 = userService.login(user);

        if (user1 != null) {
            // 保存用户ID及access_token到session中
            HttpSession session = httpServletRequest.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", user1.getId());

            // 将查询出来的讯息响应给前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id", user1.getId());

            return new ResponseResult(true, 200, "登陆成功", map);

        } else {
            return new ResponseResult(true, 400, "用户名密码错误", null);
        }
    }

    /*
        分配角色（回显）
     */
    @RequestMapping("/findUserRelationRoleById")
    public ResponseResult findUserRelationRoleById(Integer userId) {
        List<Role> roleList = userService.findUserRelationRoleById(userId);
        return new ResponseResult(true, 200, "分配角色回显成功", roleList);
    }

    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {
        userService.userContextRole(userVo);
        return new ResponseResult(true, 200, "分配角色成功", null);
    }

    /*
        获取用户权限，进行菜单动态展示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest httpServletRequest){

        // 1. 获取请求头中的token
        String header_token = httpServletRequest.getHeader("Authorization");

        // 2. 获取Session中的token
        String session_token = (String) httpServletRequest.getSession().getAttribute("access_token");

        // 3. 判断是否一致
        if(header_token.equals(session_token)){

            // 获取用户ID
            Integer user_id = (Integer) httpServletRequest.getSession().getAttribute("user_id");

            // 调用Service，进行菜单讯息查询
            ResponseResult userPermissions = userService.getUserPermissions(user_id);
            return userPermissions;

        } else {
            return new ResponseResult(false, 400, "获取菜单讯息失败", null);
        }

    }
}
