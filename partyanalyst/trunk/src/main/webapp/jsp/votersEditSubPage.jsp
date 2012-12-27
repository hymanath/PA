<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>

<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Voters </title>
<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>	
<style>
fieldset {
    border: 1px dotted #61B5CF;
    margin-top: 1.4em;
    padding: 0.6em;
    width: 130%;
}
.legendClass {
    background-color: #FFFFFF;
    border-style: none;
    color: #00008B;
    font: bold 16px Arial,Helvetica,sans-serif;
    margin-bottom: 0;
    text-align: center;
    width: 42%;
}
form div {
    clear: left;
    margin: 0;
    padding: 0;
    padding-top: 0.6em;
}
 
form div label {
    float: left;
    width: 40%;
    font: bold 0.9em Arial, Helvetica, sans-serif;
}
</style>
</head>
<body style="position: relative;">
<br><br>

<form id="voterDetailsForm" name="voterDetailsForm" method="POST" action="voterEditAction.action">

<div id="mainDiv" style="float: right;">
 <fieldset style="width: 335px;">
       <legend class="legendClass">Voter Information</legend>
 		<div>
		<label for="name">Voter Name:</label>
				<s:select theme="simple" style="width: 169px;"
				label="Select Category" name="voterHouseInfoVO.userCategoryValuesId" 
				id="userCategoryValuesId" list="userCategorysList" 
				listKey="sNo" listValue="name"/>
		</div>
		
		<!--
		<div>
			<label for="name">Voter Categories:</label> 
			<input type="text" style="width: 165px;" name="voterHouseInfoVO.name" value="${voterHouseInfoVO.name}" readonly='true'/>
		</div>-->

		<div>
		<label for="name">set value:</label> 
			<input type="text" style="width: 165px;"" name="voterHouseInfoVO.gender" value="${voterHouseInfoVO.gender}" readonly='true'/>
		</div>





		</fieldset>

<div style="float:right;">
<input class="btn btn-success" type="submit" value="Update" >
</div>

</div>
 
</form>

</body>
</html>