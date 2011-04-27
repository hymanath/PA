<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ResourceBundle;" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Add A Question</title>

	<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
    <link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">

<!-- YUI Dependency files (End) -->
	
	<script type="text/javascript" src="js/constituencyManagement/constituencyManagement.js"></script>
	<script type="text/javascript" src="js/constituencyManagement/cadreManagement.js"></script>
	<script type="text/javascript" src="js/problemManagementReport/problemManagementReport.js"></script>
	<script type="text/javascript" src="js/influencingPeople/influencingPeople.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>
    <LINK rel="stylesheet" type="text/css" href="styles/addNewProblem/addNewProblem.css"> 
	
 <style type="text/css">
	.fieldBoxesWidth
	{
		width:178px;
	}
	.headingStyle
	{
		color:#247CD4;
		font-size:19px;
		font-weight:bold;
		padding:0px;
		text-align:center;
	}
	.yui-skin-sam .yui-calcontainer {
		background-color:#F2F2F2;
		border:1px solid #808080;
		font-size:10px;
		padding:10px;
		width:150px;
	}
	.button 
	{
		background-attachment:scroll;
		background-color:#335291;
		background-image:none;
		background-position:0 0;
		background-repeat:repeat;
		color:#FFFFFF;
		width:100px;
		align:center;
	}
	.fieldset {
	border:4px solid #F6DFC9;
	margin-bottom:10px;
	width :400px;
	}
	#questionPageDiv
	{
		width:600px;
		border:3px solid #567AAF;
	}
	.required
	{
		font-family:verdana;
		font-weight:bold;
		color:red;
		text-align:left;
		width:500px;	
	}
	.th
	{
		width:65px;
	}

	.questionHeader {
    background-image: url("images/icons/cadreReport/bg_center.png");
    background-repeat: repeat-x;
    color: #FFFFFF;
    font-size: 14px;
    font-weight: bold;
    height: 24px;
    padding-top: 1px;
    text-align: center;
    width: 150px;
}
	
</style>
	


<%			
	ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
	String questionStartDate   = rb.getString("questionStartDate");
	String questionEndDate     = rb.getString("questionEndDate");
	String question			   = rb.getString("question");
	String ansDescription	   = rb.getString("ansDescription");
	String ansValue			   = rb.getString("ansValue");
	String mandatoryFields	   = rb.getString("mandatoryFields");	
	String answerDetails	   = rb.getString("answerDetails");


	String enterQuestionStartDate = rb.getString("enterQuestionStartDate");
	String enterQuestionEndDate = rb.getString("enterQuestionEndDate");
	String enQuestion          = rb.getString("enQuestion");
	String enAnswer			   = rb.getString("enAnswer");
	String validEDate          = rb.getString("validEDate");
	
 %> 

<script type="text/javascript">


var validationSuccess="true";
function addAnotherAnswer(divName){

		validateAnswerFields(0);
		if(validationSuccess=="false"){
		validationSuccess = "true";
		return;
		}
		var newdiv = document.createElement('div');
		var str	= "";
		str += "<table>"
		str += "<tr><th align='left' class='th'>";
		str += "<b><%=ansDescription%></b>";
		str += "</th><td >"
		str += "<input class='fieldBoxesWidth' name='description' id='descriptionInput[]' type='text'";
		str += "width='300px' style='margin-left:2px;'></input></td>"
		str += "<th align='center' class='th' >";
		str += "<b><%=ansValue%></b>";
		str += "</th><td>"
		str += "<textarea  class='fieldBoxesWidth' id='valueInput[]'"
		str += "rows='2' cols='20' name='value'></textarea>";
		str += "</td></tr></table>"
        newdiv.innerHTML = str;
		document.getElementById(divName).appendChild(newdiv);


  }

  function validateAnswerFields(startIndex){
  	
	descriptionArray = document.getElementsByName("description");
	valueArray		 = document.getElementsByName("value");

	document.getElementById("errorMsgDiv").innerHTML = " ";
	
	if(startIndex == 1){
	  if((descriptionArray.item(1).value == 0) && (valueArray.item(1).value == 0)){
		return;
	  }
	}

	for(var i = startIndex; i < descriptionArray.length; i++)
	{
		  
	  if((descriptionArray.item(i).value==0) || (valueArray.item(i).value==0)){
	 
			var message="Please Enter Both Lable and Data for existing answer fields";
			validationSuccess="false";
			document.getElementById("errorMsgDiv").innerHTML = message;
			break;
	  }
	}
 }

  function validateAndForwardToAction()
	{			
			
			var errorFlag=0;
			var message="";	
			descriptionArray = document.getElementsByName("description");
			valueArray		 = document.getElementsByName("value");

			document.getElementById("errorMsgDiv").innerHTML = " ";

			if(document.getElementById("identifiedFromText").value==0){
				message+='<%=enterQuestionStartDate %>';
				message+='<br/>';
				errorFlag=1;
			}
			if(document.getElementById("reportedFromText").value==0){
				message+='<%=enterQuestionEndDate %>';
				message+='<br/>';
				errorFlag=1;
			} 
			if(document.getElementById("questionTextBox").value==0){
				message+='<%=enQuestion %>';
				message+='<br/>';
				errorFlag=1;
			}

			if((descriptionArray.item(0).value==0) || (valueArray.item(0).value==0)){

				message+='<%=enAnswer %>';
				message+='<br/>';
				errorFlag=1;
				
			}			
			
			
    		 if(document.getElementById("identifiedFromText").value > document.getElementById("reportedFromText").value){

				message+='<%=validEDate %>';
				message+='<br/>';
				errorFlag=1;
			} 

			

			if(errorFlag==1){
				document.getElementById("errorMsgDiv").innerHTML = message;

				return false;
			}


			else{

				var optionsArray = new Array();
				
				var str ="";

				validateAnswerFields(1);
				if(validationSuccess == "false"){
				validationSuccess ="true";
				return false;
				}

				for(var i = 0; i < descriptionArray.length; i++)
				{

				if(!((descriptionArray.item(i).value==0) || (valueArray.item(i).value==0))){
					
				str = str+descriptionArray.item(i).value+":"+"^";
				  str = str+valueArray.item(i).value+"^";
				}


			    }
				
				document.getElementById('answersId').value = str; 
				document.form.action="homePageQuestionsPostAction.action";
				document.form.method="post";
				document.form.submit();
				return true;							
			}	
	}

var identifyDate = 1;
var reportDate = 2;
var strMore="";

var minDate = (new Date().getMonth() + 1) +"/" +new Date().getDate()+"/"+new Date().getFullYear();

function showDateCalendar(eleId) {
	
		var id ;
		elementId = eleId;
		
		if(eleId==1){		
			id = document.getElementById("identifiedFromText_Div");
		}else if(eleId==2){
			id = document.getElementById("reportedFromText_Div");
		}

		if (dateCalendar)
			dateCalendar.destroy();
	
		var navConfig = {
			strings : {
				month : "Choose Month",
				year : "Enter Year",
				submit : "OK",
				cancel : "Cancel",
				invalidYear : "Please enter a valid year"
			},
			monthFormat : YAHOO.widget.Calendar.SHORT,
			initialFocus : "year"
		};
	
		var dateCalendar = new YAHOO.widget.Calendar(id, {
			navigator : navConfig,
			mindate : minDate,
			title : "Choose a date:",
			close : true
		});
		
		dateCalendar.selectEvent.subscribe(displayDateTextBoxs, dateCalendar, true);
		dateCalendar.render();
		dateCalendar.show();
	}

	function displayDateTextBoxs(type, args, obj) 
	{
		var dates = args[0];
		var date = dates[0];
		var year = date[0], month = date[1], day = date[2];
		var txtDate1;
		txtDate1 = document.getElementById("identifiedFromText");	
		txtDate1.value = day + "/" + month + "/" + year;
	}
	
	function displayDateTextMethod(type, args, obj) {
		var dates = args[0];
		var date = dates[0];
		var year = date[0], month = date[1], day = date[2];
		var txtDate1;
		txtDate1 = document.getElementById("reportedFromText");
		txtDate1.value = day + "/" + month + "/" + year;		
	}
	
	function showDateCal(eleId) {
		var id ;
		elementID = eleId;
		id = document.getElementById("reportedFromText_Div");

		if (dateCalendar1)
			dateCalendar1.destroy();

		var navConfig = {
			strings : {
				month : "Choose Month",
				year : "Enter Year",
				submit : "OK",
				cancel : "Cancel",
				invalidYear : "Please enter a valid year"
			},
			monthFormat : YAHOO.widget.Calendar.SHORT,
			initialFocus : "year"
		};

		var dateCalendar1 = new YAHOO.widget.Calendar(id, {
			navigator : navConfig,
			mindate : minDate,
			title : "Choose a date:",
			close : true
		});
		dateCalendar1.selectEvent.subscribe(displayDateTextMethod, dateCalendar1, true);
		dateCalendar1.render();
		dateCalendar1.show();		
	}

	
window.history.forward(1);
</script>
</head>
<body>
<center>
<table cellspacing="0" cellpadding="0" style="margin-top:10px;">
<tbody>
<tr>
<td>
<img border="none" src="images/icons/cadreReport/bg_left.png">
</td>
<td>
<div class="questionHeader">
<span style="margin-top:2px;">Add a Question</span>
</div>
</td>
<td>
<img border="none" src="images/icons/cadreReport/bg_right.png">
</td>
</tr>
</tbody>
</table>
</center>



<div id="errorMsgDiv1" align="center">
	<table class="registrationTable" align="center">
	<tr>
		<td colspan="2">
		<div id="errorMsgDiv" style="color: red;">
		</div>
		</td>
	</tr>
	</table>
</div>		



<div id="successMsg" class="required"  style="color:green"></div>
	<c:if  test="${resultStatus == '0'}">
	<div id="successMsg" style="color:green;" >Question Added Successfully!</div>
	</c:if>	
	<c:if  test="${resultStatus == '1'}">
	<div id="successMsg" style="color:red;">Error Raised while saving data please check log for details</div>
	</c:if>



	<s:form action="opinionPollPostAction" method="POST" theme="simple" name="form">			
	<div id="questionPageDiv" align="center">

	<div class="required" id="requiredMessage">*<%=mandatoryFields%></div>
		<table style="margin-top:15px;margin-right:150px;" >	
		
		
		<tr>
			<th align="left"><%=question%><font class="required">*</font></th>
				<td width="50" style="padding: 10px;"><textarea  class="fieldBoxesWidth" id="questionTextBox" name="question" rows="2" cols="20"></textarea></td>
		</tr>
		
		<tr>
				<th align="left"><%=questionStartDate%><font class="required">*</font></th>
				<td width="50" style="padding: 10px;;">
					<input type="text"  READONLY="READONLY" name ="questionStartDate" class="fieldBoxesWidth" id="identifiedFromText" style="margin-top:0px;" name="identifiedFromText" size="20"/>
					<div class="yui-skin-sam"><div id="identifiedFromText_Div" class="tinyDateCal"></div></div></td>					
				<td valign="top">
					<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCalendar(identifyDate)"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="politicalChangesCalendarImage" border="0"/></a>
				</td>
			</tr>	
			<tr>		
				<th align="left"><%=questionEndDate%><font class="required">*</font></th>
				<td width="50" style="padding: 10px;">
					<input type="text" READONLY="READONLY" name ="questionEndDate" class="fieldBoxesWidth" id="reportedFromText" style="margin-top:0px;" name="reportedFromText" size="20"/>
					<div class="yui-skin-sam"><div id="reportedFromText_Div" class="tinyDateCal"></div></div></td>
				<td valign="top">
					<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal(reportDate)"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="politicalChangesCalendarImage" border="0"/></a>
				</td>
			</tr>
		</table><br>



		<fieldset class="fieldset" align="center">
		<legend style="font-family:arial,helvetica,clean,sans-serif;"><%=answerDetails%></legend>
			<table id="questinsTable">


			<tr>
				  <th align="left" class="th"><%=ansDescription%><font class="required">*</font> </th>
			<td><input class="fieldBoxesWidth" name="description" id="descriptionInput[]" type="text" width="300px"></input></td>

				  <th align="left" class="th"><%=ansValue%><font class="required">*</font> </th>
			<td><textarea  class="fieldBoxesWidth" id="valueInput[]" name="value" rows="2" cols="20"></textarea></td></tr>
			
			<tr>
				  <th align="left" class="th"><%=ansDescription%> </th>
			<td><input class="fieldBoxesWidth" name="description" id="descriptionInput[]" type="text" width="300px"></input></td>

				  <th align="left" class="th"><%=ansValue%> </th>
			<td><textarea  class="fieldBoxesWidth" id="valueInput[]" name="value" rows="2" cols="20"></textarea></td></tr>
				
			
			</table>
			<table >
			<div id="dynamicDiv">
			</div>
			</table>
			<tr><td><input type="button" value="Add Another Answer" onClick='addAnotherAnswer("dynamicDiv");' style="background-color: #335291;color: #FFFFFF;"></td></tr>
						
		  
		 </fieldset>
		 <input type="hidden" id="answersId" name="answers"/>
		 <table style="margin-top:15px;" align="center">
			
		<th></th>
   		<td><div id="saveDiv" align="center">
			<input type="button" Class="button" value="Add Question" name="post" onclick="validateAndForwardToAction()"></input>
			</div>
		</td>
	</table>
		</div>
	</s:form>

</body>
</html>


