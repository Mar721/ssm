package crm.workbench.service;

import crm.workbench.pojo.ClueActivityRelation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ClueActivityRelationService {
    int saveBund(List<ClueActivityRelation> clueActivityRelationList);

    int saveunBund(ClueActivityRelation clueActivityRelation);
}
