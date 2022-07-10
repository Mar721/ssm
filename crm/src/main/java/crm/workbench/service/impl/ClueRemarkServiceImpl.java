package crm.workbench.service.impl;

import crm.workbench.mappers.ClueRemarkMapper;
import crm.workbench.pojo.ClueRemark;
import crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("clueRemarkService")
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public List<ClueRemark> queryClueRemarkListByClueId(String id) {
        return clueRemarkMapper.selectClueRemarkListByClueId(id);
    }
}
