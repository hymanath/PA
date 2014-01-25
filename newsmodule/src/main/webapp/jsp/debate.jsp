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
 .scrollit {
    overflow:scroll;
}
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


var partiesArray = new Array();

function submitForm()
{
	
	var debateDetails={
	endTime : '',
	startTime : '',
	channelId : '',
	telecastTimeId : '',
	debetSummery : ''
	}; 
	var  observer = new Array();
	var  subjectArray = new Array();
	var  participant = new Array();
	var questionAnswer = new Array();
	var smsPole= new Array();
	
	
	var questionAnswerObj = {
				questionId : '',
				answer     : ''
	};

	var smaPoleObj = {
				questionId   : '',
				option  : '',
				percentage :''
	};
	$( ".subjectClass " ).each(function( index ) {
		subjectArray.push($(this ).val());	
	});
	debateDetails.endTime         = $('#endTime').val();
	debateDetails.startTime       = $('#startTime').val();
	debateDetails.channelId       = $('#channel option:selected').val();
	debateDetails.telecastTimeId  = $('#telecastTime option:selected').val();
	observer.push($('#observer option:selected').val());
	/* var i = 0;
	$( ".participantDetailsClass " ).each(function( index ) {
	     i++;
		participantObj.partyId      = $('#party'+i+'').val();	
		participantObj.candidateId  = $('#candidate'+i+'').val();	
		participantObj.summery      = $('#candiSummery'+i+'').val();	
		participantObj.subject      = $('#subject'+i+'').val();	
		participantObj.presentation = $('#presentation'+i+'').val();	
		participantObj.counterAttack= $('#counterAttack'+i+'').val();	
		participantObj.bodyLanguage = $('#bodyLanguage'+i+'').val();
		participant.push(participantObj);		
	}); */
	$('.particepntDetailsRow').each(function() {
		var participantObj = {
			  partyId: '' ,
			  candidateId : '' ,
			  summery     : '',
			  participantRoles:[],
			  scale : [
			  {
				 scaleId : '',
				 scaleTotal : ''
			  }
			  ]
		};
		
		participantObj.partyId  = $(this).closest("tr").find('.partysClass').val();
		participantObj.candidateId = $(this).closest("tr").find('.candidatesClass').val();
		participantObj.summery = 'prasad';
		for(var i=0 ;i <charsArray.length;i++)
		{
			var scaleObj = {
			 scaleId : '',
			 scaleTotal : ''
			};
			scaleObj.scaleId    = charsArray[i].id;
			scaleObj.scaleTotal = $(this).closest("tr").find('.'+charsArray[i].id+'CharClass').val();
			participantObj.scale.push(scaleObj);
		}
		participant.push(participantObj);	
	});
	console.log(participant);
	var j = 0;
	$( ".questionAnswerClass " ).each(function( index ) {

		questionAnswerObj.questionId = $('#question'+j+'').val();	
		questionAnswerObj.answer     = $('#answer'+j+'').val();	
		questionAnswer.push(questionAnswerObj);
		j++;
	});
	
	var l = 0;
	$( ".smsPoleClass " ).each(function( index ) {
	l++;
		smaPoleObj.questionId  = $('#smsques1').val();	
		smaPoleObj.option      = $('#smsoption'+l+'').val();
		smaPoleObj.percentage  = $('#smsper'+l+'').val();
		smsPole.push(smaPoleObj);	
	});
	
	debateDetails.debetSummery = $('#debetSum').val();
	
	 /* var jsObj = {
				debateDetails :debateDetails,
				participant   : participant,
				observer     : observer,
				subjectArray : subjectArray,
				questionAnswer : questionAnswer,
				smsPole : smsPole,
				task : "saveDebateDetails"	
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveDebateDetailsAction.action?"+rparam;
		callAjax(jsObj,url);  */
	//console.log(debateDetails);
}
	
function addMoreSubject()
{
    var str = "";
	str += "<tr>";
    str += "<td>Subject1</td><td></td>";
    str += "<td>";
	str += "<input type='text' Class='selectWidth subjectClass' name='subject"+subjCount+"' id='subject"+subjCount+"'/>";
    str += "</td>";
	str += "</tr>";
$('#subjectDiv').after(str);
subjCount++;
}

/* function addMoreCandidates(){
var str = '';

str += "<tr id='row"+candCount+"' class='particepntDetailsRow'><td><select name='party"+candCount+"'  id='party"+candCount+"' list='partiesList' theme='simple' listKey='id' listValue='name' onChange='getCandidatesOfSelectedParty(this.value,this.id);'><option value='0' selected='selected'>Select</option>";
	for ( var i in partiesArray) {
	str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
	}
	str+='</select></td>';
	str +='<td><select theme="simple" Class="selectWidth" name="candidate'+candCount+'" id="candidate'+candCount+'" ></select></td>';
	for(var i in charsArray){
		str +='<td><table style="border-left: hidden;">';
		str +='<tr><td><input type="text" Class="selectWidth" name="'+charsArray[i].name+''+candCount+'" id="'+charsArray[i].name+''+candCount+'"style="width:30px;"/></td></tr>';
		str +='</table></td>';
	}
	str +='<td><select theme="simple" Class="selectWidth" name="participantRoles'+candCount+'" id="participantRoles'+candCount+'" multiple="multiple"></select></td>';
	str +='<td><input type="text" Class="selectWidth" name="candiSummery'+candCount+'" id="candiSummery'+candCount+'" /></td>';
	str +='<td><a name="row'+candCount+'" class="icon-remove-sign" title="Click here to add another Subject" onClick="removeCandidate(this.name);"></a></td>';
    str +='</tr></table>';

$("#participantTable").append(str);
candCount++;
} */

function addMoreCandidates()
{
	var str ='';
	str += "<tr id='row"+candCount+"' class='particepntDetailsRow'>";
	str += "<td><select name='party1'  id='party"+candCount+"' list='partiesList' theme='simple' listKey='id' listValue='name' onChange='getCandidatesOfSelectedParty(this.value,this.id);' class='partysClass'><option value='0' selected='selected'>Select</option>";
	for ( var i in partiesArray)
	{
		str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
	}
	str+='</select></td>';

	str +='<td><select theme="simple" Class="selectWidth candidatesClass" name="candidate1" id="candidate'+candCount+'" ></select></td>';
		for(var i in charsArray){
		str +='<td>';
		str +='<input type="text" Class="selectWidth '+charsArray[i].id+'CharClass" name="'+charsArray[i].name+'1" id="'+charsArray[i].name+''+candCount+'" style="width:30px;" />';
		str +='</td>';
	}
	str +='<td><select theme="simple" Class="selectWidth" name="participantRoles1" id="participantRoles'+candCount+'" multiple="multiple"></select></td>';
	str +='<td><select theme="simple" Class="selectWidth" name="expparticipantRoles1" id="expparticipantRoles'+candCount+'" multiple="multiple"></select></td>';
	str +='<td><a  name="row1" class="icon-remove-sign" title="Click here to add another Subject" onClick="removeCandidate(this.name);"></a></td>';
    str +='</tr>';
    
	$("#participantTable").append(str);
	candCount++;
}
 
function addMoreQuestions(){
var str = "";
str += "<div class='questionAnswerClass'>";
str += "<div class='span4'>";
str += "<label><strong>Question </strong></label>";
str += "<select theme='simple' Class='selectWidth' name='question"+questionCount+"' id='question"+questionCount+"'  ></select>";
str += "</div>";
str += "<div class='span4'>";
str += "<label><strong>Answer </strong></label>";
str += "<select theme='simple' Class='selectWidth' name='answer"+questionCount+"' id='answer"+questionCount+"'  ></select>";
str += "</div>";
str += "</div>";
$('#questionDiv1').append(str);
questionCount++;
}

function addMorePole(){
	
	var str = "";
	str += "<div class='smsPoleClass'>";
	str += "<div class='span5'>";
	str += "<label><strong>Option </strong></label>";
	str += "<input type='text' Class='selectWidth' name='smsoption"+poleCount+"' id='smsoption"+poleCount+"'/>";
	str += "</div>";
	str += "<div class='span5'>";
	str += "<label><strong>Percentage </strong></label>";
	str += "<input type='text' Class='selectWidth' name='smsper"+poleCount+"' id='smsper"+poleCount+"'/>";
	str += "</div>";
	str += "</div>";
	$('#smsPole').append(str);
	poleCount++;
}

function getCandidatesOfSelectedParty(partyId,divId)
	{
		var numb = divId.match(/\d/g);
		//$('#candidate1').find('option').remove();
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
	
function getValues(){
	var str ='';
	str +='<table id="participantTable" class="table table-bordered particepatedTable" style="width: 100%;overflow-x: scroll;">';
	str +='<tr><th>Party</th><th>Candidate</th>';
	for(var i in charsArray){
		str +='<th>'+charsArray[i].name+'</th>';
	}
	str +='<th>Participant Roles</th><th>Expected Roles</th><th>delete</th></tr>';
   
	str += "<tr id='row1' class='particepntDetailsRow'>";
	str += "<td><select name='party1'  id='party1' list='partiesList' theme='simple' listKey='id' listValue='name' onChange='getCandidatesOfSelectedParty(this.value,this.id);' class='partysClass'><option value='0' selected='selected'>Select</option>";
	for ( var i in partiesArray)
	{
		str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
	}
	str+='</select></td>';

	str +='<td><select theme="simple" Class="selectWidth candidatesClass" name="candidate1" id="candidate1" ></select></td>';
		for(var i in charsArray){
		str +='<td>';
		str +='<input type="text" Class="selectWidth '+charsArray[i].id+'CharClass" name="'+charsArray[i].name+'1" id="'+charsArray[i].name+'1" style="width:30px;" />';
		str +='</td>';
	}
	str +='<td><select theme="simple" Class="selectWidth" name="participantRoles1" id="participantRoles1" multiple="multiple"></select></td>';
	str +='<td><select theme="simple" Class="selectWidth" name="expparticipantRoles1" id="expparticipantRoles1" multiple="multiple"></select></td>';
	str +='<td><a  name="row1" class="icon-remove-sign" title="Click here to add another Subject" onClick="removeCandidate(this.name);"></a></td>';
    str +='</tr></table>';
    
$("#participantInnerDiv1").append(str);
}

function removeCandidate(name){
$("#"+name+"").remove();
}

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

$( document ).ready(function() {
 
	$( "#startTime" ).datepicker();
	$( "#endTime" ).datepicker();
	getValues();
});

</script>

 <div id="mainDiv" class="container">
 <div  class="row-fluid">
  <div class="widget blue">
	
   <div id="debetDiv">
   <h4>Debate Information</h4>
   <table id="createMobileApp">
   <tbody>
	<tr id="errorMsgDiv" style="font-family:verdana;margin-left:100px;"></tr>
	<tr id="subjectDiv">
    <td>Subject</td><td></td>
    <td>
	<input type="text" Class="selectWidth subjectClass" name="subject1" id="subject1"/>
    </td>
	<td>
	<a class="icon-remove-sign" title="Click here to add another Subject" onClick="addMoreSubject();"></a>
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
	<td>Observer</td> <td></td>
    <td>
    <s:select name="observer"  id="observer" list="observerList" theme="simple" listKey="id" listValue="name" multiple="true"/></td>
    </td>
    </tr>
	</tbody>
	</table>
	
	</div>

	<div id="participantDiv">
		<div id="participantInnerDiv1"  class="widget blue well span12 participantDetailsClass scrollit" style="margin-top:45px;">
			<h4>Participant Details And Performance:</h4>
		</div>
		<div  class="span10">
			<a title="Click here to add another Subject" onClick="addMoreCandidates();"><input type="button"  class="btn btn-success" value="Add" id=""  style="float:right;"/></a>
	</div>

		
	</div>

	<div id="questionOuterDiv" >
	<div id="questionDiv1" class="widget well span10">
	<h4>Questions:</h4>
	<s:iterator value="debateQuestionList" var="parties" status="stateIndex">
		<div class="questionAnswerClass">
		<div class="span5">
		<input type="hidden" Class="selectWidth"  id="question<s:property value="%{#stateIndex.index}"/>" name="question1" value="${parties.id}"></input>
		<p>${parties.name}</p>
		</div>
		<div class="span5">
		<label>
		<strong>Answer </strong>
		</label>
		<input type="text" Class="selectWidth" id="answer<s:property value="%{#stateIndex.index}"/>" name="answer1"/>
		</div>
		</div>
	</s:iterator>
	</div>
	</div>
	
		

	<div id="smsPoleOuterDiv">
		<div id="smsPole" class="widget well span10">
		<div class="smsPoleClass">
		<label>
		<strong>Question </strong>
		</label>
		<textarea rows="4" cols="50" name="smsques1" id="smsques1" style="width: 618px;"></textarea> 
		<div class="span5">
		<label>
		<strong>Option </strong>
		</label>
		<input type="text" Class="selectWidth" name="smsoption1" id="smsoption1"/>
		</div>
		<div class="span5">
		<label><strong>Percentage </strong></label>
		<input type="text" Class="selectWidth" name="smsper1" id="smsper1"/>
		</div>
		</div>
		</div>

		<div  class="span10">
		<a title="Click here to add another Subject" onClick="addMorePole();"><input type="button"  class="btn btn-success" value="addMore" id=""  style="float:right;"/></a>
		</div>
		
	</div>
	
	<div>
	<div id="debateSummery" class="widget well span10">
		<div class="control-group form-horizontal span10">
			<label>
			<strong>Dabet Summery </strong></label>
			<textarea rows="4" cols="50" name="debetSum" id="debetSum" style="width: 618px;"></textarea>
		</div>
	</div>
	</div>

</div>
<a class="btn btn-success" onClick="submitForm();">Submit</a>
 </div>
 </div>
 </body>