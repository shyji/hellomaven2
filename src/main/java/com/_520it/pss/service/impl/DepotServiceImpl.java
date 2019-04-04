package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IDepotDAO;
import com._520it.pss.domain.Depot;
import com._520it.pss.service.IDepotService;
import lombok.Setter;

public class DepotServiceImpl extends GenericServiceImpl<Depot> implements IDepotService{
    @Setter
    private IDepotDAO depotDAO;


    @Override
    protected IBaseDAO<Depot> getDAO() {
        return depotDAO;
    }
}
