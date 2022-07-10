package crm.workbench.service.impl;

import crm.workbench.mappers.ClueMapper;
import crm.workbench.pojo.Clue;
import crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int saveClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Clue> queryClueByCondition(Map<String, Object> map) {
        return clueMapper.selectClueByCondition(map);
    }

    @Override
    public Clue queryClueById(String id) {
        return clueMapper.selectClueById(id);
    }

    @Override
    public int queryCountOfClueByCondition(Map<String, Object> map) {
        return clueMapper.selectCountOfClueBycondition(map);
    }
}
