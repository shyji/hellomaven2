<bean id="${objectName}Action" class="${basePackage}.web.action.${className}Action" scope="prototype">
        <property name="${objectName}Service" ref="${objectName}Service"/>
</bean>
