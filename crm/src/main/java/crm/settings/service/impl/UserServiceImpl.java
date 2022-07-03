package crm.settings.service.impl;

import crm.commons.contants.Contants;
import crm.commons.pojo.ReturnObject;
import crm.commons.utils.DateUtil;
import crm.settings.mappers.UserMapper;
import crm.settings.pojo.User;
import crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.UIResource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public User login(Map<String,Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);

    }

    @Override
    @Transactional(readOnly = true)
    public List<User> queryAllUser() {
        return userMapper.selectAllUser();
    }
}
