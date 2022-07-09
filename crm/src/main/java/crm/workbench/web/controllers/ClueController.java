package crm.workbench.web.controllers;

import crm.commons.contants.Contants;
import crm.commons.pojo.ReturnObject;
import crm.commons.utils.DateUtil;
import crm.commons.utils.UUIDUtil;
import crm.settings.pojo.DicValue;
import crm.settings.pojo.User;
import crm.settings.service.DicValueService;
import crm.settings.service.UserService;
import crm.workbench.pojo.Clue;
import crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ClueController {
    @Autowired
    private UserService userService;
    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private ClueService clueService;

    @RequestMapping("/workbench/clue/index.do")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> usersList = userService.queryAllUser();
        List<DicValue> appellation = dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> clueState = dicValueService.queryDicValueByTypeCode("clueState");
        List<DicValue> source = dicValueService.queryDicValueByTypeCode("source");

        modelAndView.addObject("userList", usersList);
        modelAndView.addObject("appellationList", appellation);
        modelAndView.addObject("clueStateList", clueState);
        modelAndView.addObject("sourceList", source);
        modelAndView.setViewName("workbench/clue/index");
        return modelAndView;
    }


    @RequestMapping("/workbench/clue/createClue.do")
    public Object createClue(Clue clue, HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateUtil.formatDateTime(new Date()));
        clue.setCreateBy(user.getId());
        try {
            int i = clueService.saveClue(clue);
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
