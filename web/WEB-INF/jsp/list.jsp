<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table>
        <div><a class="add" href="resume?action=add"><img src="img/add.png"><label>Add resume</label></a></div>
        <tr>
            <th>Имя</th>
            <th>E-Mail</th>
            <th>Remove</th>
            <th>Edit</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
            <tr>
                <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                <td><%=ContactType.MAIL.toHtml(resume.getContact(ContactType.MAIL))%><br>
                    <%=ContactType.PHONE.toHtml(resume.getContact(ContactType.PHONE))%><br>
                    <%=ContactType.HOME_PHONE.toHtml(resume.getContact(ContactType.HOME_PHONE))%><br>
                    <%=ContactType.MOBILE.toHtml(resume.getContact(ContactType.MOBILE))%>
                </td>
                <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a></td>
                <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
