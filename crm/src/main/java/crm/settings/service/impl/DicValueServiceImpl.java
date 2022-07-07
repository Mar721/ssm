package crm.settings.service.impl;

import crm.settings.mappers.DicValueMapper;
import crm.settings.pojo.DicValue;
import crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DicValueServiceImpl implements DicValueService {
    @Autowired
    private DicValueMapper dicValueMapper;

    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }
}
