package crm.workbench.service.impl;

import crm.workbench.mappers.ContactsMapper;
import crm.workbench.pojo.Contacts;
import crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {
    @Autowired
    private ContactsMapper contactsMapper;
    @Override
    public List<Contacts> queryContactsByName(String contactsName) {
        return contactsMapper.selectContactsByName(contactsName);
    }
}
