package crm.settings.web.controllers;

import crm.commons.contants.Contants;
import crm.commons.pojo.ReturnObject;
import crm.commons.utils.CookieUtil;
import crm.commons.utils.DateUtil;
import crm.settings.pojo.User;
import crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }


    @RequestMapping(value = "/settings/qx/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isrememberpwd,
                        HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userService.login(map);
        if (user == null) {
            return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL, "用户或密码错误", null);
        } else {
            if (DateUtil.formatDateTime(new Date()).compareTo(user.getExpireTime())>0){
                return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL,"用户已过期",null);
            }else if ("0".equals(user.getLockState())){
                return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL,"用户已被锁定",null);
            }else if (!user.getAllowIps().contains(request.getRemoteAddr())){
                return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL,"用户ip受限",null);
            }else{
                HttpSession session = request.getSession();
                session.setAttribute(Contants.SESSION_USER,user);
                if ("true".equals(isrememberpwd)){
                    Cookie cookie1 = new Cookie("loginAct",loginAct);
                    Cookie cookie2 = new Cookie("loginPwd", loginPwd);
                    cookie1.setMaxAge(10*24*60*60);
                    cookie2.setMaxAge(10*24*60*60);
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                }else {
                    CookieUtil.destroyLoginCookie(response);
                }
                return new ReturnObject(Contants.RETURN_OBJECT_CODE_SUCCESS,null,null);
            }
        }
    }

    @RequestMapping("settings/qx/user/loginOut.do")
    public String loginOut(HttpServletResponse response, HttpSession session){
        CookieUtil.destroyLoginCookie(response);
        session.invalidate();
        return "redirect:/";
    }
}
