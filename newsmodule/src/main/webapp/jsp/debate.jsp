<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=utf-8" %>

<html>
 <head>
 <link href="Green/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="Green/js/bootstrap.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"/>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script src="js/debate.js"></script>
  <title> Debate Information - TDP Portal </title>
 </head>
<style>
 .scrollit {
overflow:scroll;
}
textarea{
resize:none;
}
#debatErrDiv1,#debatErrDiv2,#debatErrDiv3{
color:red;
font-weight:bold;
}
select {
width:220px;
}
.errDiv{
color:red;
font-weight:bold;
}
.succDiv{
color:green;
font-weight:bold;
}
</style>
<script>

var partiesArray = new Array();
<c:forEach var="parties" items="${partiesList}">
	var parties1 ={
	id:"${parties.id}",
	name:"${parties.name}"
	}
	partiesArray.push(parties1);
</c:forEach>

var charsArray = new Array();
<c:forEach var="chars" items="${characteristicsList}">
	var chars1 ={
	id:"${chars.id}",
	name:"${chars.name}"
	}
	charsArray.push(chars1);
</c:forEach>
var rolesArray = new Array();
<c:forEach var="role" items="${rolesList}">
	var roles ={
	id:"${role.id}",
	name:"${role.name}"
	}
	rolesArray.push(roles);
</c:forEach>
$( document ).ready(function() {
 
	$( "#startTime" ).datepicker();
	$( "#endTime" ).datepicker();
	getValues();
});

</script>
<body>
<div id="errTab" style="display:none;width:777px;margin-left:50px;">
	<table  class="table">
		<tr>
			<td> Errors in Debate Information : </td>
			<td> Participant Details And Performance : </td>
			<td> Errors in Question : </td>
		</tr>
		<tr>
			<td> <div id="debatErrDiv1"></div></td>
			<td> <div id="debatErrDiv2"></div></td>
			<td> <div id="debatErrDiv3"></div></td>
		</tr>
	</table>
</div>
<div id="debateDiv">
	<div class="row-fluid" >
		<legend class="span10" style="margin-left:20px;">Debate Information : </legend>

	<div class="row-fluid" >
			<div class="span10" style="margin-left:20px;">
				<label>Subject : <font class="requiredFont">*</font></label>
				<input type="text" Class="subjectClass" name="subject1" id="subject1" style="width:650px;"/><a href="javascript:{}"  title="Click here to add another Subject" onClick="addMoreSubject();"><i class="icon-plus" style="margin-left:15px;"></i></a>
				<div id="addedSubjectDiv"></div>				
				
			</div>

	</div>
	<div id="dialogueBoxDiv"></div>
	<div class="row-fluid" >
			<div class="span3" style="margin-left:20px;">
				<label>Channel : <font class="requiredFont">*</font></label>
				<s:select name="channel"  id="channel" list="channelList" theme="simple" listKey="id" listValue="name"/><a href="javascript:{}"  title="Click here to add another Channel" onClick="addAttribute('Channel');"><i class="icon-plus"></i></a>
			</div>
			<div class="span3">
				<label>Observer : <font class="requiredFont">*</font></label>
				    <s:select name="observer"  id="observer" list="observerList" theme="simple" listKey="id" listValue="name" multiple="multiple"/><a href="javascript:{}"  title="Click here to add another Observer" onClick="addAttribute('Observer');"><i class="icon-plus"></i></a>

			</div>	
			<div class="span3">
				<label>Telecast Time : <font class="requiredFont">*</font></label>
				<s:select name="telecastTime"  id="telecastTime" list="telecastTimeList" theme="simple" listKey="id" listValue="name"/>
			</div>
			<div class="span3">
				<label>Start Time : <font class="requiredFont">*</font></label>
				<input type="text" Class="selectWidth" name="startTime" id="startTime"/>
			</div>				
			<div class="span3">
				<label>End Time : <font class="requiredFont">*</font></label>
				<input type="text" Class="selectWidth" name="endTime" id="endTime"/> 
			</div>	
	</div>	
</div>

	<div id="participantDiv">
		<div id="participantInnerDiv1"  class="widget blue well span12 participantDetailsClass scrollit" style="margin-top:45px;">
		<h4>Participant Details And Performance:</h4>
		
		</div>

		<div  class="span10">
		<a title="Click here to add another Subject" onClick="addMoreCandidates();"><input type="button"  class="btn btn-success" value="addMore" id=""  style="float:right;"/></a>
		</div>
	</div>

	<div id="debateQuestnsDiv">	
		<div class="row-fluid" >
			<legend class="span10" style="margin-left:20px;">Questions :</legend>
		<div id="questionDiv1" class="widget well span10">
			<c:forEach  var="parties" items="${debateQuestionList}" varStatus="i">
				<div class="questionAnswerClass">
				<div class="span5">
				<input type="hidden" Class="selectWidth"  id="question${i.index+1}" name="question${i.index+1}" value="${parties.id}"></input>
				<label> ${i.index+1} )${parties.name} </label>
				</div>
				<div class="span5" style="margin-top: -10px;">
					Answer : <font class="requiredFont">*</font>
				<input type="text" Class="selectWidth debateAnswr" name="answer${i.index+1}" id="answer${i.index+1}"/>	
			</div>

				</div>
			</c:forEach>
		</div>
		</div>
	</div>
	<div id="smsQuestnsDiv">	
		<div class="row-fluid" >
		<legend class="span10" style="margin-left:20px;">SMS Question :</legend>
			<div id="smsPole" class="widget well span10">
			
					<textarea rows="4" cols="50" name="smsques1" id="smsques1" style="width: 618px;"></textarea> 
					<div class="span5" style="margin-left: 0px;">
						<label>
						<strong>Option : <font class="requiredFont">*</font></strong>
						</label>
						<input type="text" Class="selectWidth smsOptin" name="smsoption1" id="smsoption1"/>
					</div>
					<div class="span5">
						<label><strong>Percentage : <font class="requiredFont">*</font></strong></label>
						<input type="text" Class="selectWidth smsOptinPerc" name="smsper1" id="smsper1"/>
					</div>
				
				<div  class="span10">
					<a title="Click here to add another Subject" onClick="addMorePole();"><input type="button"  class="btn btn-success" value="addMore" id=""  style="float:right;"/></a>
				</div>
			</div>
		</div>

	</div>
		<div>
	<div id="debateSummery" class="widget well span10">
		<div class="control-group form-horizontal span10">
			<label>
			<strong>Dabet Summery : <font class="requiredFont">*</font></strong></label>

			<textarea rows="4" cols="50" name="debetSum" id="debetSum" style="width: 618px;"></textarea>
		</div>
		
				<a class="btn btn-success" onClick="submitForm();">Submit</a>
	</div>
	</div>
<script>
	var subjCount = 2;
	var candCount = 2;
	var questionCount = 2;
	var poleCount = 2;

</script>
</body>
</html>

