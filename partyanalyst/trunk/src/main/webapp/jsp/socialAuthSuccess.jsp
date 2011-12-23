<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <title>SocialAuth Demo</title>
        <style>
                .sectiontableheader {background-color:#C8D7E3;color:#293D6B;font-size:8pt;font-weight:bold;padding:2px;}
                .sectiontableentry2 {background:none repeat scroll 0 0 #F7F7F7;padding:2px;}
                .sectiontableentry1 {background:none repeat scroll 0 0 #FFFFF0;padding:2px;}
        </style>
        <script>
        function updateStatus(){
                var btn = document.getElementById('btnUpdateStatus');
                btn.disabled=true;
                        var msg = prompt("Enter your status here:");
                        if(msg == null || msg.length == 0){
                                btn.disabled=false;
                        return false;
                }
                        msg = "statusMessage="+msg;
                        var req = new XMLHttpRequest();
                        req.open("POST", "<%=request.getContextPath()%>/socialAuthUpdateStatusAction.do");
                        req.setRequestHeader("Accept", "text/xml");
                        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        req.setRequestHeader("Content-length", msg.length);
                        req.setRequestHeader("Connection", "close");
                        req.onreadystatechange = function () {
                                if (req.readyState == 4) {
                                        if(req.responseText.length > 0) {
                                                        alert(req.responseText);
                                                        btn.disabled=false;
                                        }
                                }
                        };
                        req.send(msg);
        }
        </script>
</head>
<body>
<%@page import="org.brickred.socialauth.Profile,java.util.*;" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<h2 align="center">Authentication has been successful.</h2>
<br/>
<div align="center"><a href="index.jsp">Back</a></div>
<br />
<h3 align="center">Profile Information</h3>
<table cellspacing="1" cellspacing="4" border="0" bgcolor="e5e5e5" width="60%" align="center">
        <tr class="sectiontableheader">
                <th>Profile Field</th>
                <th>Value</th>
        </tr>
        <tr class="sectiontableentry1">
                <td>Email:</td>
                <td><c:out value="${profile.email}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>First Name:</td>
                <td><c:out value="${profile.firstName}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Last Name:</td>
                <td><c:out value="${profile.lastName}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Country:</td>
                <td><c:out value="${profile.country}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Language:</td>
                <td><c:out value="${profile.language}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Full Name:</td>
                <td><c:out value="${profile.fullName}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Display Name:</td>
                <td><c:out value="${profile.displayName}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>DOB:</td>
                <td><c:out value="${profile.dob}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Gender:</td>
                <td><c:out value="${profile.gender}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Location:</td>
                <td><c:out value="${profile.location}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Profile Image:</td>
                <td>
                        <c:if test="${profile.profileImageURL != null}">
                                <img src='<c:out value="${profile.profileImageURL}"/>'/>
                        </c:if>
                </td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Update status:</td>
                <td>
                        <input type="button" value="Click to Update Status" onclick="updateStatus();" 
            id="btnUpdateStatus"/>              
                </td>
        </tr>
</table>
<h3 align="center">Contact Details</h3>
<table cellspacing="1" cellspacing="4" border="0" bgcolor="e5e5e5" align="center" width="60%">
        <tr class="sectiontableheader">
                <th width="15%">Name</th>
                <th>Email</th>
                <th>Profile URL</th>
        </tr>
        <c:forEach var="contact" items="${contactsList}" varStatus="index">
                <tr class='<c:if test="${index.count % 2 == 0}">sectiontableentry2</c:if>
        <c:if test="${index.count % 2 != 0}">sectiontableentry1</c:if>'>
                        <td><c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/></td>
                        <td><c:out value="${contact.email}"/></td>
                        <td>
                <a href='<c:out value="${contact.profileUrl}"/>' target="_new">
                    <c:out value="${contact.profileUrl}"/>
                </a>
            </td>
                </tr>
        </c:forEach>
</table>
</body>
</html>