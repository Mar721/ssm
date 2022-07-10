package crm.workbench.mappers;

import crm.workbench.pojo.Clue;

import java.util.List;
import java.util.Map;

public interface ClueMapper {
    int insertClue(Clue clue);

    List<Clue> selectClueByCondition(Map<String, Object> map);

    Clue selectClueById(String id);
}
