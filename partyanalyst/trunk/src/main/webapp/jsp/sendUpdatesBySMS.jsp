<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Communication Centre </title>
<head>
	
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
	<script src="js/sendUpdatesBySMS.js"></script>
	
<script>
var locationDetails={
						
							constituencyArr:[],
						
						};

<c:forEach var="constituency"  items="${userAccessConstituencyList}" >
	var ob={
				id:'${constituency.id}',
				value:'${constituency.name}'
			};
	locationDetails.constituencyArr.push(ob);	
</c:forEach>
</script>

<style type="text/css">	
	select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 250px;
	}
	select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input,#fromDate,#toDate {
		border-radius: 4px 4px 4px 4px;
		color: #000000;
		display: inline-block;
		font-size: 13px;
		line-height: 18px;
		padding: 4px;
	}
	input, button, select {
		font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		 border: 1px solid lightBlue;
	}
	.tdWidth1,#tdWidth{
	width: 175px;
	}
	#smstxt{
	width: 500px;
	height: 100px; 
	background: none repeat scroll 0% 0% white; 
	margin-bottom: 15px;
	}
	
	#requiredValue{
	color:red;
	font-size:large;
	}	
	#mainDiv{
	border: 1px lightBlue solid ;
	width: 920px; 
	height: 600px;
	margin-left: 70px;
	margin-bottom: 15px;
	background-color:white;
	font-weight:bold;
	border-radius: 5px 5px 5px 5px;
	}	
	textarea {    
	border: 1px solid lightblue!important	
	}
	#sendButton{
	width:100px;
	height:30px;
	background:#3E678F;
	border-radius:5px;
	color:#ffffff;
	}
	#tableRowS,#tableRowC,#tableRowM,#tableRowP,#tableRowB{
	margin-left: -10px
	}
	#radioLabel{
	font-weight:bold;
	}
	</style>
</head>
<body>
<div id="mainDiv" align="center" >
<h4 align="center" style="height: 30px;margin-top: 10px;"> SEND UPDATES TO THE PEOPLE BY SMS </h4>
<div id="imageForMail"  class = "span3"  style="display:none;width: 820px;font-weight:bold;color: #0174DF;font-size:small;">
	<font  style="font-size:small;">Sending SMS, please wait...</font>
	<img src="/images/icons/loading.gif" style="padding-left:10px;" width="18" height="11"/>
</div>
<div id="successMsgDiv" style="font-weight: normal; color: rgb(1, 116, 223); float: left; width: 820px;">&nbsp;</div>
<div id="errorMsgDiv" style="font-weight: bold; color: red; float: left; width: 815px;">&nbsp;</div>
<br>
<table>
	<tr id="tableRowS">
		<td id="tdWidth">
			Scope :<font id="requiredValue" class="requiredFont">*</font> 
		</td>
		<td>
			<select id="listValue" onchange="populateLocations(this.options[this.selectedIndex].value)">
			<option value="0"> Select Scope </option>
			<option value="Constituency"> Constituency </option>
			<option value="Mandal"> Mandal / Muncipality / Corp</option>
			<option value="Panchayat"> Panchayath / Ward </option>
			<option value="Booth"> Booth </option>
			</select>
		</td>
	</tr>
</table>
<div id="showScopeSubs" style="margin-bottom: 10px;"></div>
<div id="showScopeSubs1" style="margin-bottom: 10px;"></div>
<div id="showScopeSubs2" style="margin-bottom: 10px;"></div>
<div id="showScopeSubs3" style="margin-bottom: 10px;"></div>	

<br>
<fieldset>
<div id="rButtonsDiv" align="center" style="font-weight: bold;"> 
	<input type="radio" name="voterType" value="cadre" id="cadreIds"  checked = "true"/> 
	<label for="cadreIds" id="radioLabel">Cadre People</label>
	<input type="radio" name="voterType" value="influence" id="influenceId"/> 
	<label for="influenceId" id="radioLabel">Influence People</label>	
	<input type="radio" name="voterType" value="voter" id="votersId"/> 
	<label for="votersId" id="radioLabel">Voters</label>		
 </div>
</fieldset>
<fieldset>
<div align="center">	
	<span><textarea  id="smstxt" maxlength='300' onkeyup="limitText('smstxt','maxcount',300)"  placeholder="Enter Your Message "></textarea></span>
 </div>
</fieldset>
<fieldset>
<div id="limitDiv" align="center"> 
	<span id="maxcount" style="margin-left: 65px;font-weight:bold">300 
	</span><span style="font-weight:normal">chars remaining... </span>
	<span style="margin-left: 100px;font-weight:normal">Should not exceed 300 chars</span>
 </div>
</fieldset>
<fieldset>
	<button id="sendButton" onclick="handleSubmit()" > Send SMS </button> 
</fieldset>
</div>
</body>
</html>