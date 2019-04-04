package com._520it.pss.dao;

import com._520it.pss.page.PageResult;
import com._520it.pss.query.QueryObject;

import java.io.Serializable;
import java.util.List;

public interface IBaseDAO<T> {
    void save(T e);

    void update(T e);

    T get(Serializable id);

    List<T> list();

    void delete(T e);

    PageResult query(QueryObject qo);

    void batchDelete(List<Long> ids);

    public List<T> listByCondition(QueryObject qo);
}
