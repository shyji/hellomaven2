package ${basePackage}.service.impl;

import ${basePackage}.dao.IBaseDAO;
import ${basePackage}.dao.I${className}DAO;
import ${basePackage}.domain.${className};
import ${basePackage}.service.I${className}Service;
import lombok.Setter;

public class ${className}ServiceImpl extends GenericServiceImpl<${className}> implements I${className}Service{
    @Setter
    private I${className}DAO ${objectName}DAO;


    @Override
    protected IBaseDAO<${className}> getDAO() {
        return ${objectName}DAO;
    }
}
