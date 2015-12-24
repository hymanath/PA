<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voter Address</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<s:select cssClass="canSelect" theme="simple" id="districtId" name="district" list="locations" listKey="id" listValue="name"/>
		</div>
	</div>
</div>
</body>
</html>