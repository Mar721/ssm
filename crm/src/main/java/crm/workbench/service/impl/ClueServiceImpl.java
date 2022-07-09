package crm.workbench.service.impl;

import crm.workbench.mappers.ClueMapper;
import crm.workbench.pojo.Clue;
import crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Override
    public int saveClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }
}
