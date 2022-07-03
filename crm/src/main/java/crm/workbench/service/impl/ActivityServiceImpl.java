package crm.workbench.service.impl;

import crm.workbench.mappers.ActivityMapper;
import crm.workbench.pojo.Activity;
import crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(value = "activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int saveActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    @Override
    @Transactional(readOnly = true)
    public int queryCountActivityCondition(Map<String, Object> map) {
        return activityMapper.selectCountActivityCondition(map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int deleteActivityIds(String[] ids) {
        return activityMapper.deleteActivityIds(ids);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int changeActivity(Activity activity) {
        return activityMapper.changeActivity(activity);
    }

    @Override
    @Transactional(readOnly = true)
    public Activity queryActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }

}
