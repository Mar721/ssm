package crm.workbench.web.controllers;

import com.alibaba.druid.sql.dialect.h2.visitor.H2ASTVisitor;
import crm.commons.contants.Contants;
import crm.commons.pojo.ReturnObject;
import crm.settings.pojo.DicValue;
import crm.settings.pojo.User;
import crm.settings.service.DicValueService;
import crm.settings.service.UserService;
import crm.workbench.service.ActivityService;
import crm.workbench.service.ContactsService;
import crm.workbench.service.CustomerService;
import crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
public class TransactionController {
    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private TranService tranService;

    @RequestMapping("/workbench/transaction/index.do")
    public ModelAndView toIndex() {
        ModelAndView modelAndView = new ModelAndView();
        List<DicValue> transactionTypeList = dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");

        modelAndView.addObject("transactionTypeList", transactionTypeList);
        modelAndView.addObject("sourceList", sourceList);
        modelAndView.addObject("stageList", stageList);

        modelAndView.setViewName("workbench/transaction/index");

        return modelAndView;
    }

    @RequestMapping("/workbench/transaction/toSave.do")
    public ModelAndView toSavePage() {
        ModelAndView mav = new ModelAndView();
        List<User> userList = userService.queryAllUser();
        List<DicValue> transactionTypeList = dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");

        mav.addObject("userList", userList);
        mav.addObject("transactionTypeList", transactionTypeList);
        mav.addObject("sourceList", sourceList);
        mav.addObject("stageList", stageList);

        mav.setViewName("workbench/transaction/save");

        return mav;
    }

    @RequestMapping("/workbench/transaction/getPossibilityByStage.do")
    @ResponseBody
    public Object getPossibilityByStage(String stageValue){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("possibility");
        return resourceBundle.getString(stageValue);
    }

    /**
     * 查询数据传给前台的自动补全插件
     * @param customerName
     * @return
     */
    @RequestMapping("/workbench/transaction/queryCustomerNameByName.do")
    @ResponseBody
    public Object queryCustomerNameByName(String customerName){
        return customerService.queryCustomerNameByName(customerName);
    }

    @RequestMapping("/workbench/transaction/queryActivityByName.do")
    @ResponseBody
    public Object queryActivityByName(String activityName){
        return activityService.queryActivityByActivityName(activityName);
    }

    @RequestMapping("/workbench/transaction/queryContactsByName.do")
    @ResponseBody
    public Object queryContactsByName(String contactsName){
        return contactsService.queryContactsByName(contactsName);
    }


    @RequestMapping("/workbench/transaction/saveCreateTran.do")
    @ResponseBody
    //参数中加注解 @RequestParam ，mvc会把前台的键值封装到这个map中
    public Object saveTran(@RequestParam Map<String,Object> map, HttpSession session){
        map.put(Contants.SESSION_USER,session.getAttribute(Contants.SESSION_USER));
        try {
            tranService.saveCreateTran(map);
            return new ReturnObject(Contants.RETURN_OBJECT_CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnObject(Contants.RETURN_OBJECT_CODE_FAIL,
                    "系统忙，请稍后重试...");
        }
    }
}
