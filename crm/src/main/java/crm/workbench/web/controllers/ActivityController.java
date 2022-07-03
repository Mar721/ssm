package crm.workbench.web.controllers;

import crm.commons.contants.Contants;
import crm.commons.pojo.ReturnObject;
import crm.commons.utils.DateUtil;
import crm.commons.utils.UUIDUtil;
import crm.settings.pojo.User;
import crm.settings.service.UserService;
import crm.workbench.pojo.Activity;
import crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/index.do")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> usersList = userService.queryAllUser();
        modelAndView.addObject("userList", usersList);
        modelAndView.setViewName("workbench/activity/index");
        return modelAndView;
    }

    @RequestMapping("/workbench/activity/createActivity.do")
    @ResponseBody
    public Object createActivity(Activity activity, HttpSession session) {
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateUtil.formatDateTime(new Date()));
        activity.setCreateBy(user.getId());
        try {
            int i = activityService.saveActivity(activity);
            if (i > 0) {
                return new ReturnObject(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL,
                        "系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL,
                    "系统忙，请稍后重试...");
        }
    }

    @RequestMapping("/workbench/activity/queryActivity.do")
    @ResponseBody
    public Object QueryActivity(String name, String owner, String beginDate,
                                String endDate, int pageNo, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("beginDate", beginDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int activityCount = activityService.queryCountActivityCondition(map);
        Map<String,Object> returnMap=new HashMap<>();
        returnMap.put("activityList",activityList);
        returnMap.put("activityCount",activityCount);
        return returnMap;
    }

    @RequestMapping("/workbench/activity/deleteActivity.do")
    @ResponseBody
    public Object deleteActivity(String[] ids){
        try {
            int i = activityService.deleteActivityIds(ids);
            if (i > 0) {
                return new ReturnObject(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL,
                        "系统忙，请稍后重试...");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL,
                    "系统忙，请稍后重试...");
        }
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(String id){
        Activity activity = activityService.queryActivityById(id);
        return activity;
    }



    @RequestMapping("/workbench/activity/changeActivity.do")
    @ResponseBody
    public Object changeActivity(Activity activity,HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        activity.setEditBy(user.getId());
        activity.setEditTime(DateUtil.formatDateTime(new Date()));
        try {
            int i = activityService.changeActivity(activity);
            if (i > 0) {
                return new ReturnObject(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL,
                        "系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL,
                    "系统忙，请稍后重试...");
        }
    }
}
