package com._520it.pss.dao.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.page.PageResult;
import com._520it.pss.query.QueryObject;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAOImpl<T> implements IBaseDAO<T> {

    @Setter
    protected SessionFactory sessionFactory;

    private Class<T> tClass;

    public GenericDAOImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.tClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public void save(T e) {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(e);
    }

    @Override
    public void update(T e) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(e);
    }

    @Override
    public T get(Serializable id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (T) currentSession.get(tClass, id);
    }

    @Override
    public List<T> list() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createCriteria(tClass).list();
    }

    @Override
    public List<T> listByCondition(QueryObject qo) {
        String typeName = tClass.getSimpleName();
        Session currentSession = sessionFactory.getCurrentSession();
        String hql = "SELECT obj FROM " + typeName + " obj" + qo.getQuery();
        List<T> list = currentSession.createQuery(hql).setProperties(qo.getParameterMap()).list();
        return list;
    }

    @Override
    public void delete(T e) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(e);
    }

    @Override
    public PageResult query(QueryObject qo) {
        String typeName = tClass.getSimpleName();
        Session currentSession = sessionFactory.getCurrentSession();
        String hqlCount = "SELECT COUNT(obj) FROM " + typeName + " obj" + qo.getQuery();
        Long totalCount = (Long) currentSession.createQuery(hqlCount).setProperties(qo.getParameterMap())
                .uniqueResult();
        if(totalCount==0L){
            return PageResult.EMPTY;
        }
        String hql = "SELECT obj FROM " + typeName + " obj" + qo.getQuery();
        List<T> list = currentSession.createQuery(hql).setProperties(qo.getParameterMap())
                .setFirstResult((qo.getCurrentPage() - 1) * qo.getPageSize())
                .setMaxResults(qo.getPageSize()).list();
        return new PageResult(list,totalCount.intValue(),qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void batchDelete(List<Long> ids) {
        String typeName = tClass.getSimpleName();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.createQuery("DELETE FROM "+typeName+" obj WHERE obj.id in(:ids)").setParameterList("ids",ids).executeUpdate();
    }
}
