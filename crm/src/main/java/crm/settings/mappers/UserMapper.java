package crm.settings.mappers;

import crm.settings.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    User selectUserByLoginActAndPwd(Map<String,Object> map);
    List<User> selectAllUser();
}
