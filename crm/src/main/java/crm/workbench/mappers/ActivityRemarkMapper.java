package crm.workbench.mappers;

import crm.workbench.pojo.ActivityRemark;

import java.util.List;

public interface ActivityRemarkMapper {
    List<ActivityRemark> selectRemarkByActivityId(String id);

    int insertRemark(ActivityRemark activityRemark);

    int deleteRemark(String id);

    int changeRemark(ActivityRemark activityRemark);
}
