<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%--
	专门用来做消息提示或错误提示
 --%>

 <script type="text/javascript">

	<s:if test="hasActionMessages()">
		var msg = '<s:property value="actionMessages"/>';
		$.dialog({
			title:"温馨提示",
			icon:"face-smile",
			content:msg,
			drag:false,
			ok:true
		});
	</s:if>
	
	<s:if test="hasActionErrors()">
		var msg = '<s:property value="actionErrors"/>';
		$.dialog({
			title:"温馨提示",
			icon:"error",
			content:msg,
			drag:false,
			ok:true
		});
	</s:if>
</script>


 

	
	