<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-validate/jquery.validate.min.js"></script>
    <script type="text/javascript">
        $(function(){
           /* $("#editForm").validate({
                rules:{
                    "employee.name":{
                        required : true,
                        minlength : 4
                    },"employee.password":{
                        required : true,
                        minlength : 4
                    },"repassword":{
                        required : true,
                        equalTo:"[name='employee.password']"
                    },"employee.email":{

                        email:true
                    }
                },messages:{
                    "employee.name":{
                        required:"请输入用户名",
                        minlength:"用户名不能少于4位数"
                    }, "employee.password":{
                        required:"请输入密码",
                        minlength:"密码不能少于4位数"
                    },"repassword":{
                        required : "请确认密码",
                        equalTo:"请保证两次输入密码相同!"
                    },"employee.email":{

                        email:"邮箱格式不正确"
                    }

                }
            });
*/
        });
    </script>
</head>
<body>
<s:debug/>
<s:actionerror/>
<s:form name="editForm" action="employee_saveOrUpdate" method="post" id="editForm">
    <s:hidden name="employee.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">员工编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">


                <tr>
                    <td class="ui_text_rt" width="140">用户名</td>
                    <td class="ui_text_lt">
                        <s:textfield name="employee.name" cssClass="ui_input_txt02"/><span style="color: red"><s:property value="fieldErrors['employee.name'][0]"/></span>
                    </td>
                </tr>
                <s:if test="employee.id==null">
                    <tr>
                        <td class="ui_text_rt" width="140">密码</td>
                        <td class="ui_text_lt">
                            <s:textfield name="employee.password" cssClass="ui_input_txt02"/>
                        </td>
                    </tr>

                    <tr>
                        <td class="ui_text_rt" width="140">验证密码</td>
                        <td class="ui_text_lt">
                            <s:textfield name="repassword" cssClass="ui_input_txt02"/>
                        </td>
                    </tr>
                </s:if>
                <tr>
                    <td class="ui_text_rt" width="140">Email</td>
                    <td class="ui_text_lt">
                        <s:textfield name="employee.email" cssClass="ui_input_txt02"/>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">年龄</td>
                    <td class="ui_text_lt">
                        <s:textfield name="employee.age" cssClass="ui_input_txt02"/>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">所属部门</td>
                    <td class="ui_text_lt">
                        <s:select list="#depts" listKey="id" listValue="name" name="employee.dept.id" headerKey="-1"
                                  headerValue="请选择"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">超级管理员</td>
                    <td class="ui_text_lt">
                        <s:checkbox name="employee.admin" cssClass="ui_checkbox01"/>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">角色</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <s:select list="#roles" cssClass="ui_multiselect01 items" multiple="true"
                                              listKey="id" listValue="name"/>
                                </td>
                                <td align="center">
                                    <input type="button" id="select" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAll" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselect" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAll" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <s:select list="employee.roles" name="employee.roles.id"
                                              cssClass="ui_multiselect01 itemSelect" multiple="true"
                                              listKey="id" listValue="name"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>