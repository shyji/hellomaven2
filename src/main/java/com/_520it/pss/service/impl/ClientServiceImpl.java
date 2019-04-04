package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IClientDAO;
import com._520it.pss.domain.Client;
import com._520it.pss.service.IClientService;
import lombok.Setter;

public class ClientServiceImpl extends GenericServiceImpl<Client> implements IClientService{
    @Setter
    private IClientDAO clientDAO;


    @Override
    protected IBaseDAO<Client> getDAO() {
        return clientDAO;
    }
}
