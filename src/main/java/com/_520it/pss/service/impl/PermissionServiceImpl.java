package com._520it.pss.service.impl;

import com._520it.pss.dao.IBaseDAO;
import com._520it.pss.dao.IPermissionDAO;
import com._520it.pss.domain.Permission;
import com._520it.pss.service.IPermissionService;
import com._520it.pss.uitl.PermissionUtil;
import com._520it.pss.uitl.RequiredPermission;
import com._520it.pss.web.action.BaseAction;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PermissionServiceImpl extends GenericServiceImpl<Permission> implements IPermissionService,ApplicationContextAware{
    private ApplicationContext ctx;
    @Setter
    private IPermissionDAO permissionDAO;


    @Override
    protected IBaseDAO<Permission> getDAO() {
        return permissionDAO;
    }

    @Override
    public void reload() {
        //数据库中查的数据
        List<Permission> permissions = permissionDAO.list();
        Map<String, BaseAction> beansMap = ctx.getBeansOfType(BaseAction.class);
        List<String> expressions = new ArrayList<>();
        for(Permission p : permissions){
            expressions.add(p.getExpression());
        }
        for(BaseAction action: beansMap.values()){
            Method[] methods = action.getClass().getDeclaredMethods();
            for(Method m : methods){
                if(m.isAnnotationPresent(RequiredPermission.class)){
                    String expression = PermissionUtil.buildExperssion(m);
                    //System.out.println(expression);
                    if(!expressions.contains(expression)){
                        System.out.println(expression);
                        Permission p = new Permission();
                        p.setExpression(expression);
                        p.setName(m.getAnnotation(RequiredPermission.class).value());
                        permissionDAO.save(p);
                    }
                }
            }
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx=applicationContext;
    }
}
