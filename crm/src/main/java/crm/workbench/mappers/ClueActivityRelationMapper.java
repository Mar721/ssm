package crm.workbench.mappers;


import crm.workbench.pojo.ClueActivityRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClueActivityRelationMapper {

    int insertBund(@Param("clueActivityRelationList") List<ClueActivityRelation> clueActivityRelationList);

    int deleteClueActivityRelationByClueActivityId(ClueActivityRelation clueActivityRelation);
}