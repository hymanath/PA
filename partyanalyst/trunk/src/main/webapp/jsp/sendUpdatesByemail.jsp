<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Send Updates to user emails</title>
<SCRIPT type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>
<!--<SCRIPT type="text/javascript" src="js/AddNewProblem/addFileInput.js"></SCRIPT>-->
<LINK rel="stylesheet" type="text/css" href="styles/addNewProblem/addNewProblem.css">
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
 <link href="calendar.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<script src="js/cleditor/jquery.cleditor.min.js"></script>
	<script src="js/sendUpdatesByEmail.js"></script>
	<!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>-->
<script src="js/sendUpdatesByEmail.js"></script>
<link rel="stylesheet" type="text/css" href="css/cleditor/jquery.cleditor.css">
	<!-- YUI Skin Sam -->
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<style type="text/css">
#descTextArea{background:#ffffff;margin-left:365px;}
.questionHeader {
    background-image: url("images/icons/cadreReport/bg_center.png");
    background-repeat: repeat-x;
    color: #FFFFFF;
    font-size: 14px;
    font-weight: bold;
    height: 24px;
    padding-top: 1px;
    text-align: center;
     width: 210px;
    margin-left: 396px;
    border-radius: 3px;
}
</style>
<script type="text/javascript">
 $(document).ready(function() {
	    $('#All').attr('checked', true);
		$('#Customers').attr('checked', false);
		$('#FreeUsers').attr('checked', false);
        $("#descriptionForEmail").cleditor();
		$('#locationScope').val(0);
	});
</script>
</head>
<body>
<s:if test="#session.USER.isAdmin == 'true'">
<div style="width:998px;background:#ffffff;margin-left:auto;margin-right:auto;">
<br>
<div class="questionHeader">
<span style="margin-top:2px;">Send Updates to User E-mails</span>
</div><br>
		<div id="errorDiv" style="color: red; width: 300px; margin-top: -1px; float: left; margin-left: 50px;"></div>
		<div id="selectDiv" style="margin-left:395px; font-weight: bold;">
			<span>Select Users</span> :  
			<input type="radio" id="All" name="user" value="All" onclick="defaultForRadio(),hideTableDiv()"
			checked="checked"/>&nbsp;All
			<input type="radio" id="Customers" name="user"  value="Selected User"
			onclick="defaultForRadio(),hideTableDiv()"/>&nbsp;Customers
			<input type="radio" id="FreeUsers" name="user"  value="Selected User" onclick="defaultForRadio(),hideTableDiv()"/>&nbsp;Free User
			</div><br>
		<div style=" font-weight: bold; margin-left: 70px; margin-bottom: 20px;">
		<div id="scopeDiv"  style="margin-left: -20px;">
		Location Scope:<select id="locationScope" name="locationScope" onChange="selectLocation(),getDetailsForStates(),hideTableDiv()" style="width: 175px;">
		<option value="1" selected="selected"> All </option>
		<option value="2"> State </option>
		<option value="3"> District </option>
		<option value="4"> Constituency </option>
		</select>
		</div>
		<div id="Statediv" style="display:none;margin-top: 15px;">
		Select State:<select id="state" name="state"  onChange="getDetailsForallDistricts(this.value)" style="width: 175px;">
		<option value="0"> Select State</option>
		</select>
		</div>
		<div id="Districtdiv" style="display: none; margin-top: -28px; margin-left: 270px;">
		Select District:<select id="district" name="district" onchange="getDetailsForallConstituencies(this.value)" style="width: 175px;">
		<option value="0"> Select District </option>
		</select>
		</div>
		<div id="Constituencydiv" style="display: none; margin-top: -28px; margin-left: 555px;">	
		Select Constituency:<select id="constituency" name="constituency"  style="width: 175px;">
		<option value="0"> Select Constituency </option>
		</select>
		</div>
		<div id="viewByajax">
		<input type="button" id="view" value="View"  onclick="getUsers()" style="margin-left: 380px; width: 80px; height: 25px; margin-top: 20px;" onclick="getUsers()" value="View"/>
		</div>
	</div>
		<div id="commentsData_outer" class="yui-skin-sam">
		<div id="table">
		</div>
	</div>
	<div style="height:30px"> 
	<div id="imageForMail"  class = "span3"  style="display:none;font-weight:bold;color: #0174DF;font-size:small;margin-left:400px">
		<font  style="font-size:small;">Sending Mail, please wait...</font>
		<img src="../PartyAnalyst/images/icons/loading.gif" style="padding-left:10px;" width="18" height="11"/>
	</div>
	</div>
	<div align="center">
	<div id="errorDiv1" style="color:red"></div>
	<div id="subjectDiv" style="margin-left: -50px; margin-top: 15px;"><span style="font-weight: bold;">Subject:</span><input type="text" id="subject" style="width: 617px;"/></div><br>
	
	<textarea id="descriptionForEmail" style="width:618px;height:92px;margin:2px 0px 0px 20px;" ></textarea>
	</div>
	<div id="buttonDiv" align="center" style="margin-top: 10px; margin-bottom: 10px;">
		 <input type="button" value="Send" onClick="sendEmailsForSelectedUser();" style="width: 80px; height: 25px;"/>
		<!--
		<input type="button" value="Preview" onClick="showPreview();" style="width: 80px; height: 25px;"/> -->
</div>
<!--
	<div id="previewDiv" style="background-color:#fff;border:1px solid #e5e5e5;padding:20px;">${specilaPageText}
	</div> 
	-->
	<div id="errorDiv" style="margin-left:372px;">
	</div>
	
</s:if>
<s:else>
<%
	response.sendRedirect("loginInputAction.action");
%>
</s:else>
		
<script type="text/javascript">
var dataArr;
function printUserDetatils(userDetails){
	dataArr=new Array();
	for(var i in userDetails){
	var obj = {
					checkBox:"<input type='checkbox'   name='userCheckBox1' value="+userDetails[i].email+">",
					userName :userDetails[i].name,
					email:userDetails[i].email,
					state:userDetails[i].state,
					district:userDetails[i].district,
					constituency:userDetails[i].constituency
	};
	dataArr.push(obj);
  }
				sendEmailsToUsersTable();
}
</script>
</body>
</html>