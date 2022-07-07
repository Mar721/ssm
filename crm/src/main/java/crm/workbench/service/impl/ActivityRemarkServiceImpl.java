package crm.workbench.service.impl;

import crm.workbench.mappers.ActivityRemarkMapper;
import crm.workbench.pojo.ActivityRemark;
import crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("activityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> getRemarkByActivityId(String id) {
        return activityRemarkMapper.selectRemarkByActivityId(id);
    }

    @Override
    public int addRemark(ActivityRemark activityRemark) {
        return activityRemarkMapper.insertRemark(activityRemark);
    }

    @Override
    public int deleteRemark(String id) {
        return activityRemarkMapper.deleteRemark(id);
    }

    @Override
    public int changeRemark(ActivityRemark activityRemark) {
        return activityRemarkMapper.changeRemark(activityRemark);
    }
}
