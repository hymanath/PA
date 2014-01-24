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
  <title> Survey Details </title>
 </head>

<body>
 <style>
 #mainDiv{
margin-left:auto;
margin-right:auto;
width:900px;
}

.selectWidth{
width:145px;
}
  </style>
<script>
var subjCount = 2;
var candCount = 2;
var questionCount = 2;
var poleCount = 2;
function addMoreSubject(){
var str = "";
	str += "<tr>";
    str += "<td>Subject1</td><td></td>";
    str += "<td>";
	str += "<input type='text' Class='selectWidth' name='subject"+subjCount+"' id='subject"+subjCount+"'/>";
    str += "</td>";
	str += "</tr>";
$('#subjectDiv').after(str);
subjCount++;
}
var partiesArray = new Array();
<c:forEach var="parties" items="${partiesList}">
	var parties1 ={
	id:"${parties.id}",
	name:"${parties.name}"
	}
	partiesArray.push(parties1);
</c:forEach>
function addMoreCandidates(){


var str = "";
str += "<div id='participantInnerDiv"+candCount+"'  class='widget well span10'>";
str += "<div class='span3' style='margin-left: 15px;'>";
str += "<label><strong>Party</strong></label>";
str += "<select name='party"+candCount+"'  id='party"+candCount+"' list='partiesList' theme='simple' listKey='id' listValue='name' onChange='getCandidatesOfSelectedParty(this.value,this.id);'><option value='0' selected='selected'>Select</option>";
for ( var i in partiesArray) {
str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
}
str+='</select>';

str += "</div>";
str += "<div class='span3'>";
str += "<label><strong>Candidate</strong></label>";
str += "<select theme='simple' Class='selectWidth' name='candidate"+candCount+"' id='candidate"+candCount+"'  ></select>";
str += "</div>";
str += "<div class='span4'>";
str += "<label><strong>Subject</strong></label>";
str += "<input type='text' Class='selectWidth' name='subject"+candCount+"' id='subject"+candCount+"'/>";
str += "</div>";
str += "<div class='span3'>";
str += "<label><strong>Presentation</strong></label>";
str += "<input type='text' Class='selectWidth' name='presentation"+candCount+"' id='presentation"+candCount+"'/>";
str += "</div>";
str += "<div class='span3'>";
str += "<label><strong>Counter Attack</strong></label>";
str += "<input type='text' Class='selectWidth' name='counterAttack"+candCount+"' id='counterAttack"+candCount+"'/>";
str += "</div>";
str += "<div class='span4'>";
str += "<label><strong>Body Language</strong></label>";
str += "<input type='text' Class='selectWidth' name='bodyLanguage"+candCount+"' id='bodyLanguage"+candCount+"'/>";
str += "</div>";
str += "<div class='span3'>";
str += "<label><strong>Participant Roles</strong></label>";
str += "<select theme='simple' Class='selectWidth' name='participantRoles"+candCount+"' id='participantRoles"+candCount+"' multiple='multiple' ></select>";
str += "</div>";
str += "</div>";
if(candCount == 2)
	$('#participantInnerDiv1').after(str);
else
	$('#participantInnerDiv'+(candCount-1)+'').after(str);
candCount++;
$("party"+(candCount+1)+" option[value='0']").attr('selected','selected');
}

function addMoreQuestions(){
var str = "";
str += "<div class='span4'>";
str += "<label><strong>Question </strong></label>";
str += "<select theme='simple' Class='selectWidth' name='question"+questionCount+"' id='question"+questionCount+"'  ></select>";
str += "</div>";
str += "<div class='span4'>";
str += "<label><strong>Answer </strong></label>";
str += "<select theme='simple' Class='selectWidth' name='answer"+questionCount+"' id='answer"+questionCount+"'  ></select>";
str += "</div>";
$('#questionDiv1').append(str);
questionCount++;
}

function addMorePole(){
var str = "";
str += "<div class='span3'>";
str += "</div>";
str += "<div class='span3'>";
str += "<label><strong>Option </strong></label>";
str += "<input type='text' Class='selectWidth' name='smsoption"+poleCount+"' id='smsoption"+poleCount+"'/>";
str += "</div>";
str += "<div class='span4'>";
str += "<label><strong>Percentage </strong></label>";
str += "<input type='text' Class='selectWidth' name='smsper"+poleCount+"' id='smsper"+poleCount+"'/>";
str += "</div>";

$('#smsPole').append(str);
poleCount++;
}

function getCandidatesOfSelectedParty(partyId,divId)
	{
		var numb = divId.match(/\d/g);
		$('#candidate1').find('option').remove();
		var jsObj = {
				partyId :partyId,
				selectedVal :"candidate"+numb+"",
				task : "getCandidatesOfAParty"	
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCandidatesOfAParty.action?"+rparam;
		callAjax(jsObj,url);
	}

function fillSelectOptionsVO(results,selectedVal)
	{
		$('#'+selectedVal+'').find('option').remove();
		$('#'+selectedVal+'').append('<option value="0">Select</option>');
					
		if(results != null)
		{
			for(var i in results)
			{
				$('#'+selectedVal+'').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
			}
			
		}
			
	}

function callAjax(jsObj,url)
	{

		var callback =
		{			
			success : function( o )
			{
				try
				{ 
					myResults = YAHOO.lang.JSON.parse(o.responseText); 

					if (jsObj.task == "getCandidatesOfAParty")
					{
						fillSelectOptionsVO(myResults,jsObj.selectedVal);	
					}
				}catch(e)
				{   
				 $("#submitDataImg").hide();
				}  
			},
			scope : this,
			failure : function( o )
			{
				
			}
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
</script>

 <div id="mainDiv" class="container">
 <div  class="row-fluid">
  <div class="widget blue">
	
	<div id="debetDiv">
   <table id="createMobileApp">
   <tbody>
	<tr id="errorMsgDiv" style="font-family:verdana;margin-left:100px;"></tr>
	<tr id="subjectDiv">
    <td>Subject</td><td></td>
    <td>
	<input type="text" Class="selectWidth" name="subject1" id="subject1"/>
    </td>
	<td>
	<a class="icon-remove-sign" title="Click here to add another Subject" onClick="addMoreSubject();"></a>
	</td>
   </tr>

  <tr>
	<td> Start Time</td><td></td>
	<td>
	<input type="text" Class="selectWidth" name="startTime" id="startTime"/>
	</td>
    <td>End Time</td>
    <td></td>
    <td>
    <input type="text" Class="selectWidth" name="endTime" id="endTime"/> 
    </td>
  </tr>

  <tr>
    <td>Channel</td> <td></td>
    <td>
	<s:select name="channel"  id="channel" list="channelList" theme="simple" listKey="id" listValue="name"/></td>
    </td>
    <td>Telecast Time</td> <td></td>
    <td>
	<s:select name="telecastTime"  id="telecastTime" list="telecastTimeList" theme="simple" listKey="id" listValue="name"/></td>
    </td>
  </tr>
   
  <tr>
	<td>Observer</td> <td></td>
    <td>
    <s:select name="observer"  id="observer" list="observerList" theme="simple" listKey="id" listValue="name" multiple="multiple"/></td>
    </td>
  </tr>
	</tbody>
	</table>
	
	</div>

	<div id="participantDiv">
		<div id="participantInnerDiv1"  class="widget well span10" style="margin-top:45px;">
		<h4>Participant Details:</h4>
			<div class="span3">
			<label>
			<strong>Party</strong>
			</label>
			<s:select name="party1"  id="party1" list="partiesList" theme="simple" listKey="id" listValue="name" onChange="getCandidatesOfSelectedParty(this.value,this.id);"/>
			</div>
			<div class="span3">
			<label>
			<strong>Candidate</strong>
			</label>
			<select theme="simple" Class="selectWidth" name="candidate1" id="candidate1"  ></select>
			</div>
			<div class="span4">
			<label>
			<strong>Subject</strong>
			</label>
			<input type="text" Class="selectWidth" name="subject1" id="subject1"/>
			</div>
			<div class="span3">
			<label>
			<strong>Presentation</strong>
			</label>
			<input type="text" Class="selectWidth" name="presentation1" id="presentation1"/>
			</div>
			<div class="span3">
			<label>
			<strong>Counter Attack</strong>
			</label>
			<input type="text" Class="selectWidth" name="counterAttack1" id="counterAttack1"/>
			</div>
			<div class="span4">
			<label>
			<strong>Body Language</strong>
			</label>
			<input type="text" Class="selectWidth" name="bodyLanguage1" id="bodyLanguage1"/>
			</div>
			<div class="span3">
			<label>
			<strong>Participant Roles</strong>
			</label>
			<select theme="simple" Class="selectWidth" name="participantRoles1" id="participantRoles1" multiple="multiple" ></select>
			</div>
		</div>

		<div  class="span10">
		<a title="Click here to add another Subject" onClick="addMoreCandidates();"><input type="button"  class="btn btn-success" value="addMore" id=""  style="float:right;"/></a>
		</div>
	</div>

	<div id="questionOuterDiv" >
	<div id="questionDiv1" class="widget well span10">
	<h4>Questions:</h4>
		<div class="span4">
		<label>
		<strong>Question </strong>
		</label>
		<s:select name="question1"  id="question1" list="debateQuestionList" theme="simple" listKey="id" listValue="name" onChange=""/>
		</div>
		<div class="span4">
		<label>
		<strong>Answer </strong>
		</label>
		<select theme="simple" Class="selectWidth" name="answer1" id="answer1"  ></select>
		</div>
	</div>

		<div  class="span10">
		<a title="Click here to add another Subject" onClick="addMoreQuestions();"><input type="button"  class="btn btn-success" value="addMore" id=""  style="float:right;"/></a>
		</div>
	</div>

		<div id="candidateSummery" class="widget well span10">
			<div class="control-group form-horizontal span10">
			<label>
			<strong>Dabet Summery </strong></label><input type="text" id="debetSum" placeholder="DabetSummery"  name="debetSum"/>
			</div>
			<div class="span3">
			<label>
			<strong>Question </strong>
			</label>
			<s:select name="cparty"  id="cparty" list="debateQuestionList" theme="simple" listKey="id" listValue="name" onChange=""/>
			</div>
			<div class="span3">
			<label>
			<strong>Candidate </strong>
			</label>
			<select theme="simple" Class="selectWidth" name="ccand" id="ccand"  ></select>
			</div>
			<div class="span4">
			<label><strong>Summary </strong></label>
			<input type="text" Class="selectWidth" name="csum" id="csum"/>
			</div>
		</div>

	<div id="smsPoleOuterDiv">
		<div id="smsPole" class="widget well span10">
		<div class="span3">
		<label>
		<strong>Question </strong>
		</label>
		<s:select name="smsques1"  id="smsques1" list="debateSmsQuestionList" theme="simple" listKey="id" listValue="name" onChange=""/>
		</div>
		<div class="span3">
		<label>
		<strong>Option </strong>
		</label>
		<input type="text" Class="selectWidth" name="smsoption1" id="smsoption1"/>
		</div>
		<div class="span4">
		<label><strong>Percentage </strong></label>
		<input type="text" Class="selectWidth" name="smsper1" id="smsper1"/>
		</div>
		</div>

		<div  class="span10">
		<a title="Click here to add another Subject" onClick="addMorePole();"><input type="button"  class="btn btn-success" value="addMore" id=""  style="float:right;"/></a>
		</div>
	</div>

</div>
 </div>
 </div>
 </body>