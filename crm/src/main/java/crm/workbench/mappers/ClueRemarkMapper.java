package crm.workbench.mappers;

import crm.workbench.pojo.ClueRemark;

import java.util.List;

public interface ClueRemarkMapper {
    List<ClueRemark> selectClueRemarkListByClueId(String id);
}
