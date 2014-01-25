<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Activiti Demo - Login Page</title>
</head>

<body>
<center>
    <form:form commandName="user" method="post" cssStyle="margin-top: 15%;">
        <form:errors path="*" cssStyle="color:red"></form:errors><br/>
        <table>
            <tr>
                <td>用户名:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;码:</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="登录" />
                    <input type="reset" value="重置" />
                </td>
            </tr>
        </table>
    </form:form>
</center>
</body>
</html>