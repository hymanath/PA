<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Voters </title>
<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
<style>
 h1 {
    font: 1.2em Arial, Helvetica, sans-serif;
}
 
input.txt {
    color: #00008B;
    /*background-color: #E3F2F7;*/
    border: 1px solid #c3c3c3;
    width: 200px;
	border-radius:2px;
	height:17px;
}

select {
    color: #00008B;
    /*background-color: #E3F2F7;*/
    border: 1px solid #c3c3c3;
    width: 212px;
	height:27px;
	border-radius:2px;
}
 
input.btn {
    color: #00008B;
    background-color: #ADD8E6;
    border: 1px outset #00008B;
}
 
form {
    width: 40%;
	margin-left:250px;
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

.spanClass {
    float: left;   
    font-weight: bold;
	font-family:verdana;
	width: 140px;
}
 fieldset {
    border: 1px dotted #61B5CF;
    margin-top: 1.4em;
    padding: 0.6em;
	width: 130%;
}
 
.legendClass {
    font: bold 0.8em Arial, Helvetica, sans-serif;
    color: #00008B;
    background-color: #FFFFFF;
	border-style:none ;
	margin-bottom:0px;
	width:42%;
	font-weight:bold;
	font-size:16px;
	text-align:center;
}
.error{
	color:red;
	font-size:12px;
}
 
 </style>
</head>
<body align="center" style="position: relative;">
<br><br>

<form id="voterDetailsForm" name="voterDetailsForm" method="POST" action="voterEditAction.action">

<input type="hidden" name="voterId" value="${voterHouseInfoVO.voterId}"/>
<input type="hidden" name="voterHouseInfoVO.userId" value="${voterHouseInfoVO.userId}"/>
<input type="hidden" name="voterHouseInfoVO.userVoterDetailsId" value="${voterHouseInfoVO.userVoterDetailsId}"/>
            <fieldset style="width: 335px;">
                <legend class="legendClass">Voter Information</legend>
 
            <div style="width: 380px;">
                <span class="spanClass">Voter Name:</span>
                <input type="text" style="margin-left: 46px; width: 165px;" name="voterHouseInfoVO.name" value="${voterHouseInfoVO.name}" readonly='true'/>
            </div>
			 
            <div style="width: 380px;"> 
                 <span class="spanClass">Gender:</span>
                <input type="text" style="margin-left: 46px; width: 165px;"" name="voterHouseInfoVO.gender" value="${voterHouseInfoVO.gender}" readonly='true'/>
				<span id="emailErrMsg" class="error" style="float:right;margin-right:18px;"></span>
            </div>

            <div style="width: 380px;">
                 <span class="spanClass">Age :</span>
                <input type="text" style="margin-left: 46px; width: 165px;" name="voterHouseInfoVO.age" value="${voterHouseInfoVO.age}" readonly='true'/>
            </div>
 
            <div style="width: 380px;">
                <span class="spanClass">House No :</span>
                <input type="text" style="margin-left: 46px; width: 165px;" name="voterHouseInfoVO.houseNo" value="${voterHouseInfoVO.houseNo}" readonly='true'/>
            </div>

			<div style="width: 380px;">
                 <span class="spanClass">GuardianName :</span>
                <input type="text" style="margin-left: 46px; width: 165px;" name="voterHouseInfoVO.gaurdian" value="${voterHouseInfoVO.gaurdian}" readonly='true'/>
            </div>

			<div style="width: 380px;">
                 <span class="spanClass">RelationShip :</span>
                <input type="text" style="margin-left: 46px; width: 165px;" name="voterHouseInfoVO.relationship" value="${voterHouseInfoVO.relationship}" readonly='true'/>
            </div>
			
			<div style="width: 380px;">
                 <span class="spanClass">Caste :</span>
                <input type="text" style="margin-left: 46px; width: 165px;" name="voterHouseInfoVO.cast" value="${voterHouseInfoVO.cast}"/>
            </div>
			
			<div style="width: 380px;">
                 <span class="spanClass">CasteCategory :</span>
                <input type="text" style="margin-left: 46px; width: 165px;" name="voterHouseInfoVO.castCategory" value="${voterHouseInfoVO.castCategory}" readonly='true'/>
            </div>
			
			<div style="width: 380px;">
                 <span class="spanClass">Party :</span>
                <s:select theme="simple" style="margin-left: 46px; width: 165px;"
				label="Select  party" name="voterHouseInfoVO.partyId" 
				id="partyId" list="partyGroupList" 
				listKey="id" listValue="name"/>
            </div>
			 </fieldset>
<!--
Name:<input type="text" name="voterHouseInfoVO.name" value="${voterHouseInfoVO.name}" readonly='true'/><br>
Gender:<input type="text" name="voterHouseInfoVO.gender" value="${voterHouseInfoVO.gender}" readonly='true'/><br>
Age:<input type="text" name="voterHouseInfoVO.age" value="${voterHouseInfoVO.age}" readonly='true'/><br>
House No:<input type="text" name="voterHouseInfoVO.houseNo" value="${voterHouseInfoVO.houseNo}" readonly='true'/><br>
Guardian Name:<input type="text" name="voterHouseInfoVO.gaurdian" value="${voterHouseInfoVO.gaurdian}" readonly='true'/><br>
RelationShip:<input type="text" name="voterHouseInfoVO.relationship" value="${voterHouseInfoVO.relationship}" readonly='true'/><br>
Caste:<input type="text" name="voterHouseInfoVO.cast" value="${voterHouseInfoVO.cast}"/><br>
CasteCategory<input type="text" name="voterHouseInfoVO.castCategory" value="${voterHouseInfoVO.castCategory}" readonly='true'/><br>
-->
 <div style="float:right;">
<input class="btn btn-success" type="submit" value="Update">
</div>
</form>

</body>
</html>