package crm.settings.service;

import crm.commons.pojo.ReturnObject;
import crm.settings.mappers.UserMapper;
import crm.settings.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface UserService {
    User login(Map<String,Object> map);
    List<User> queryAllUser();
}
