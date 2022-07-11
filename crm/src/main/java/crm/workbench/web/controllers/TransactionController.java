package crm.workbench.web.controllers;

import crm.settings.pojo.DicValue;
import crm.settings.pojo.User;
import crm.settings.service.DicValueService;
import crm.settings.service.UserService;
import crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.ResourceBundle;

@Controller
public class TransactionController {
    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

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
        String possibility = resourceBundle.getString(stageValue);
        return possibility;
    }


    @RequestMapping("/workbench/transaction/queryCustomerNameByName.do")
    @ResponseBody
    public Object queryCustomerNameByName(String customerName){
        List<String> customerNameList = customerService.queryCustomerNameByName(customerName);

        return customerNameList;
    }
}
