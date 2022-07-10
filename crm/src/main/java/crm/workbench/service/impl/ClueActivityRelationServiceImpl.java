package crm.workbench.service.impl;


import crm.workbench.mappers.ClueActivityRelationMapper;
import crm.workbench.pojo.ClueActivityRelation;
import crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("clueActivityRelationService")
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public int saveBund(List<ClueActivityRelation> clueActivityRelationList) {
        return clueActivityRelationMapper.insertBund(clueActivityRelationList);
    }

    @Override
    public int saveunBund(ClueActivityRelation clueActivityRelation) {
        return clueActivityRelationMapper.deleteClueActivityRelationByClueActivityId(clueActivityRelation);
    }
}
