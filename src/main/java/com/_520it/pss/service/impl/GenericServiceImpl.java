package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.page.PageResult;
import com._520it.pss.query.QueryObject;
import com._520it.pss.service.IBaseService;

import java.io.Serializable;
import java.util.List;

abstract public class GenericServiceImpl<T> implements IBaseService<T> {
    protected abstract IBaseDAO<T> getDAO();

    @Override
    public void save(T e) {
        getDAO().save(e);
    }

    @Override
    public void update(T e) {
        getDAO().update(e);
    }

    @Override
    public T get(Serializable id) {
        return getDAO().get(id);
    }

    @Override
    public List<T> list() {
        return getDAO().list();
    }

    @Override
    public void delete(T e) {
        getDAO().delete(e);
    }

    @Override
    public PageResult query(QueryObject qo) {
        return getDAO().query(qo);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        getDAO().batchDelete(ids);
    }
}
