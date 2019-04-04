package com._520it.pss.domain;

import com._520it.pss.uitl.Mark;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter@Mark("员工")
@Setter@ToString(exclude = {"dept","roles"})
public class Employee extends BaseDomain{
    private String name;
    private String email;
    private Integer age;
    private Boolean admin=false;
    private String password;
    private Department dept;

    private List<Role> roles= new ArrayList<>();

    public String getRolesName(){
        if(admin){
            return "[系统管理员]";
        }
        if(roles.size()==0){
            return "[暂未分配角色]";
        }
      StringBuilder sb =new StringBuilder(80).append("[");
        for(Role role :roles){
            sb.append(role.getName()).append(",");
        }
        sb.replace(sb.length()-1,sb.length(),"]");

        return sb.toString();
    }

}
