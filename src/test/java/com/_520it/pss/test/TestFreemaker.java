package com._520it.pss.test;

import com._520it.generator.ClassInfo;
import com._520it.pss.domain.Role;
import com._520it.pss.uitl.MD5;
import com._520it.pss.uitl.PermissionUtil;
import com._520it.pss.web.action.EmployeeAction;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestFreemaker {
    @Test
    public void testFree() throws Exception {
        /* 在整个应用的生命周期中，这个工作你应该只做一次。 */
        /* 创建和调整配置。 */
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(
                new File("templates"));
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        /* 在整个应用的生命周期中，这个工作你可以执行多次 */
        /* 获取或创建模板*/
        Template temp = cfg.getTemplate("user.ftl");
        /* 创建数据模型 */

        /* 将模板和数据模型合并 */
        Writer out = new OutputStreamWriter(System.out);
        temp.process(new ClassInfo(Role.class), out);

        out.flush();


    }

    @Test
    public void test40() {

        System.out.println(new ClassInfo(Role.class));
        ;

    }


    @Test
    public void test47() {

        Method[] declaredMethods = EmployeeAction.class.getDeclaredMethods();
        for (Method m : declaredMethods
                ) {
            System.out.println(PermissionUtil.buildExperssion(m));;

        }


    }
    @Test
    public void test65(){
        System.out.println(MD5.encode("1"));;
        
        
    }
    
    @Test
    public void test73(){
      
        Long a = null;
        System.out.println(a>0L);
        
    }
}
