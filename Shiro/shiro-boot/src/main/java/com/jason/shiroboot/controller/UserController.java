package com.jason.shiroboot.controller;

import com.jason.shiroboot.pojo.User;
import com.jason.shiroboot.service.UserService;
import com.jason.shiroboot.util.VerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Api(tags = "用户接口")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    //@ApiOperation(value = "用户登录",notes = "密码账号正确登录，错误返回登陆界面")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "username",value = "用户名",required = true),
    //        @ApiImplicitParam(name = "password",value = "密码",required = true)
    //})
    @PostMapping("/login")
    public String login(String username, String password, String verifyCode, HttpSession session){
        //校验验证码
        String verifyCodes = (String) session.getAttribute("verifyCode");
        // 获取当前登录用户
        Subject subject = SecurityUtils.getSubject();

        try {
            if(verifyCodes.equalsIgnoreCase(verifyCode)) {
                // 执行登录操作
                subject.login(new UsernamePasswordToken(username, password));
                // 认证通过后直接跳转到index.jsp
                return "redirect:/toIndex";
            } else {
                throw new RuntimeException("验证码错误");
            }
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误~");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误~");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 如果认证失败仍然回到登录页面
        return "redirect:/user/toLogin";
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        //退出
        subject.logout();
        // 退出后仍然会到登录页面
        return "redirect:/user/toLogin";
    }

    @PostMapping("/register")
    public String register(User user){
        try {
            userService.register(user);
            return "redirect:/user/toLogin";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/toRegister";
    }

    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        //验证码放入session
        session.setAttribute("verifyCode",verifyCode);
        //验证码存入图片
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtil.outputImage(180,40,os,verifyCode);
    }
}
