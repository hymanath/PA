<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="Green/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="Green/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"/>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

<script src="js/jquery.google.api/ajax.googleapis.com.ajax.libs.jquery.1.8.2.jquery.min.js"></script>
	
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"/>
<script src="js/jquery.google.api/code.jquery.com.ui.1.10.2.jquery-ui.js"></script>
<script src="js/jquery-ui-themes-1.10.3.js"></script>
<script src="js/jquery-ui-timepicker-addon.js"></script>
<script src="js/jquery-ui-sliderAccess.js"></script>
<script src="js/jquery.google.api/trentrichardson.com.examples.timepicker.jquery-ui-sliderAccess.js"></script>
<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui1.10.3.css"/>
<link rel="stylesheet" media="all" type="text/css" href="jquery-ui-timepicker-addon.css" />
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect1.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
<link rel="stylesheet" type="text/css" href="styles/custom-yui-styles.css">	
		
<script src="js/debate.js"></script>
<style type="text/css">
	#errorMsgDiv,#RerrDiv,#RerrDivForAnalysis,#errorForTotal{
		font-weight:bold;
		margin-bottom:10px;
		color:red;
	}
	
	#debatesTab > thead {
	background:#CDE6FC;
	}

	textarea{
	resize:none;
	}
	select {
	width:220px;
	}
	.errDiv{
	color:red;
	font-weight:bold;
	font-size:12px;
	}
	.succDiv{
	color:green;
	font-weight:bold;
	}
	.scrollit {
	overflow:scroll;
	}

	.evenColor 
	{
		background-color: #E9E9E9;
	}
	.oddColor 
	{
		background-color: #F9F9F9;
	}


	</style>
<title>Debate Edit Form</title>
</head>
<body>
<div id="newDibateDiv"></div>
<div id="dialogueBoxDiv"></div>

<div id="createCandidateDiv" style="display:none;">
				
	<div id="errorMsgDiv"></div>
	<div id="dHintDiv"> Note: Candidate Name should not contain #,$,%,& Special charactors.
	</div>
	<table style="margin-top: 24px;"><tr>
	<td> Party Name </td>
	<td><!-- <select id="partySelectNewList"> --><span id="presentParty"></span>
	</select></td></tr>

	<tr><td>Candidate Name</td>
	<td><input type="text" id="newCandidateName"/></td></tr>
	</table>
	<input type="button" value="submit" class="btn" id="createCandidateId" key="'+key+'" partyListId="'+partyListId+'"/>

</div>
<script>

var debateId = '${debateId}';

$( document ).ready(function() {

		$('#pcConstituencyRow').hide();
		$('#acConstituencyRow').hide();
	$("#createCandidateId").live("click",function(){
		
		$("#errorMsgDiv").html('');
		var candidateName = $.trim($("#newCandidateName").val());
		candidteName = $.trim($("#newCandidateName").val());
		if(isValid(candidateName)){
			$('#errorMsgDiv').html('<b style="color:red;margin-left:-125px;">Candidate Name should not contain #,$,%,& Special charactors</b>');
			return false;
		}
	  
		if(candidateName.length == 0)
		{
		 $("#errorMsgDiv").html("Please Enter Candidate Name.");
		  return;
		}
		
		var jsObj =
			{ 
				partyId : debateNewCandiPartyId,
				name:candidateName,
				divId :partyDiv,
				task:"saveCandidateForDebate"
			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "createCandidateAction.action?"+rparam;						
		callAjax(jsObj,url);
			
	});
	
	$('#myTab > li').click(function(){
	
	$(".subjectClass,#channel,#observer,#telecastTime").css("border","1px solid #CCCCCC");
	$(".hasDatepicker,.partysClass,.candidatesClass").css("border","1px solid #CCCCCC");
	$(".participntRoles,.participantsRoles,.smsOptinPerc").css("border","1px solid #CCCCCC");
	$("#debetSum,#smsques1,.debateAnswr,.smsOptin").css("border","1px solid #CCCCCC");
	$(".ui-state-default").removeClass("ui-state-error");
	
	$('#RerrDiv').html('');
	
	});
	getSelectedDebate();
});


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
var channelsArray = new Array();
<c:forEach var="channel" items="${channelList}">
	var channels ={
	id:"${channel.id}",
	name:"${channel.name}"
	}
	channelsArray.push(channels);
</c:forEach>

var debateQuestionArray = new Array();
<c:forEach var="debateQuestion" items="${debateQuestionList}">
	var debateQuestions ={
	id:"${debateQuestion.id}",
	name:"${debateQuestion.name}"
	}
	debateQuestionArray.push(debateQuestions);
</c:forEach>

var observerArray = new Array();
<c:forEach var="observer" items="${observerList}">
	var observers ={
	id:"${observer.id}",
	name:"${observer.name}"
	}
	observerArray.push(observers);
</c:forEach>


</script>
<script>

var candCunt = 1;
function getSelectedDebate()
{
	var jsObj = {
				debateId :debateId,
				task : "getDebateDetails"	
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "retriveDebateDetailsAction.action?"+rparam;
		callAjax(jsObj,url);
}


function prepopulateDebateForm(result)
{
	var str = '';

	str += '';
	str += '<div id="successMsg" style="display:none;" align="center"></div>';
	str += '<div id="debateDiv" class="container" style="font-size: 17px;font-weight: bold;line-height: 1.5;">';
	str += '<div class="row-fluid" >';
	str += '<legend class="boxHeading">Debate Information : </legend>';

	str += '<div class="row-fluid" >';
	str += '<div class="span12">';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Subject : <font class="requiredFont">*</font><span id="subject1Err" class="errDiv" style="margin-left: 100px;"> </span><a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMoreSubject();"><i class="icon-plus" style="margin-left:15px;"></i></a></label>';
	for(var i in result.debateNames)
	{
		if(i == 0)
		{
			str += '<input type="text" Class="subjectClass span12" name="subject1" id="subject1" value="'+result.debateNames[i]+'"></input>';
		}
		else
		{
			str += "<span id='addedsubject"+subjCount+"'><label style='font-size: 17px;font-weight: bold;line-height: 1.5;'>Subject : <font class='requiredFont'>*</font><span id='subject"+subjCount+"'Err' class='errDiv' style='margin-left: 100px;'> </span><a href='javascript:{}'  title='Click here to remove another Subject' onclick='removeSubject(\"addedsubject"+subjCount+"\");'><i class='icon-trash pull-right' style='margin-left:15px;'></i></a></label>";
			str +='<input type="text" Class="subjectClass span12" name="subject'+subjCount+'" id="subject'+subjCount+'" value="'+result.debateNames[i]+'"></input>';
			str += "</br></span>";

		    subjCount++;
		}
	}
	
	str += '<div id="addedSubjectDiv"></div>	';			
							
	str += '</div>';

	str += '</div>';
	str += '<div id="dialogueBoxDiv"></div>';
	str += '<div class="row-fluid" >';
	str += '<div class="span3" >';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: ';
	str += '1.5;">Channel : <font class="requiredFont">*</font>';
	str += '<a class="btn btn-mini pull-right" onclick="addAttribute(\'Channel\');" title="Click here to add another Channel" href="javascript:{}"><i class="icon-plus"></i></a></label>';
	str += '<select name="channel"  id="channel">';
	for(var i in channelsArray)
	{
		str += '<option value='+channelsArray[i].id+'>'+channelsArray[i].name+'</option>';
	}
	str += '</select>';
	
	str += '<span id="channelErr" class="errDiv"></span></div>';
	str += '<div class="span3">';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Observer : <font class="requiredFont">*</font><a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Observer" onClick="addAttribute(\'Observer\');"><i class="icon-plus"></i></a></label>';
	str += '<select name="observer"  id="observer" >';
	for(var i in observerArray)
	{
			str += '<option value='+observerArray[i].id+'>'+observerArray[i].name+'</option>';
	}
	str += '</select>';
	str += '<span id="observerErr" class="errDiv" ></span></div>	';
	str += '<div class="span3">';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Start Date : <font class="requiredFont">*</font></label>';
	str += '<input type="text" class="input-block-level selectWidth" name="startTime" id="startTime" value="'+result.stDate+'"></input><span id="startTimeErr" class="errDiv"></span>';
	str += '</div>			';	
	str += '<div class="span3">';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">End Date : <font class="requiredFont">*</font></label>';
	str += '<input type="text" class="input-block-level selectWidth" name="endTime" id="endTime" value="'+result.edDate+'"></input><span id="endTimeErr" class="errDiv"></span>';
	str += '</div>	';
	str += '</div>	';
	str += '</div>';
	str += '<div id="participantDiv" class="row-fluid m_top10" >';
	str += '<legend class="boxHeading">Participant Details And Performance:</legend>';
	str += '<div><b>Scale (5 points scale : 0 Poor - 5 Excellent)</b></div>';
	str += '<div id="participantInnerDiv1"  class="participantDetailsClass scrollit">';
	
	str +='<table id="participantTable" class="table table-bordered particepatedTable" style="width: 100%;overflow-x: scroll;">';
	str +='<thead><tr><th>Party</th><th> Candidate</th>';
	for(var i in charsArray){
		str +='<th>'+charsArray[i].name+'</th>';
	}
	str +='<th>Participant Roles</th><th>Expected Roles</th><th>Summary </th><th>delete</th></tr></thead>';
	
    for(var p in result.participantsList)
	{
		str += "<tr id='row"+candCunt+"' class='particepntDetailsRow'>";
		str += "<td><select name='party"+candCunt+"'  id='party"+candCunt+"' onChange='getCandidatesOfSelectedPartyEdit(this.value,this.id,"+candCunt+")' class='partysClass'><option value='0' selected='selected'>Select</option>";
		for ( var i in partiesArray)
		{
			str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
		}
		str+='</select><span id="party'+candCunt+'Err" class="errDiv"></span></td>';

		str +='<td><select theme="simple" Class="selectWidth candidatesClass" name="candidate'+candCunt+'" id="candidate'+candCunt+'" >';
		str+='<option value="'+result.participantsList[p].id+'"> '+result.participantsList[p].name+'</option>';
		str +='</select>';
		str +='<a href="javascript:{}" onclick="createNewCandidate(\'candidate1\',\'party1\',1)"><span class="btn btn-mini pull-right m_topN65" style="width: 20px;"><img  title="Click Here To Create New Candidate" src="images/user.png" class="createNewCandidate" id="candidate'+candCunt+'"></span></a><span id="candidate'+candCunt+'Err" class="errDiv"></span></td>';
	    for(var i in result.participantsList[p].scaleList)
		{
	
			var myClass1 = result.participantsList[p].scaleList[i].name+""+candCunt+"";
			str +='<td>';
			str +='<input type="text" Class="selectWidth '+charsArray[i].id+'CharClass participntRoles '+myClass1.replace(/\s/g, '')+'" name="'+charsArray[i].name+''+candCunt+'" id="'+myClass1.replace(/\s/g, '')+'" style="width:30px;" onKeyup="isNumber(\'scale\',\''+myClass1.replace(/\s/g, "")+'\');" value="'+result.participantsList[p].scaleList[i].perc+'"/>';
			
			var rolesids1 = myClass1.replace(/\s/g, '');
			rolesids1 = rolesids1.replace('(Scale)', '');
			
			str +='<div id="'+rolesids1+'Err" class="errDiv"></div>';
			str +='</td>';
		}
	
			str +='<td class="participantRolesblock1"><input type="hidden" id="'+candCunt+'participantRoles" class="partiRoleClass" value="'+result.participantsList[p].roleList[0].totalCount+'"></input>';
		var roleArray = ""
		str += '<select theme="simple" multiple="true"  Class="selectWidth participantsRoles" name="'+candCunt+'participantRoles" id="participantRoles'+candCunt+'" key="'+candCunt+'participantRoles">';
		for (var j in rolesArray)
		{
			var flag = true;
			for(var i in result.participantsList[p].roleList)
			{
				if(result.participantsList[p].roleList[i].id == rolesArray[j].id)
				{
					str += '<option selected="selected" value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
					flag = false;
				}
				
				
			}
			if(flag)
			{
				str += '<option  value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
			}
			
		}
		str +='</select><span id="'+candCunt+'participantRolesErr" class="errDiv"></span></td>';
		
		if(result.participantsList[p].partyId == 872)
		{
			str +='<td class="party'+candCunt+'"><input type="hidden" id="'+candCunt+'expparticipantRoles" class="expPartyClass expPartyClass1 expPartiesRoleClass" ></input>';
			str += '<div id="expReoleDiv'+candCunt+'"><select  multiple="multiple" theme="simple" Class="selectWidth expparticipantsRoles expPartyClass" name="'+candCunt+'expparticipantRoles" id="expparticipantRoles'+candCunt+'" key ="'+candCunt+'expparticipantRoles" >';
			for (var j in rolesArray)
			{
				var flag = true;
				for(var i in result.participantsList[p].expRoleList)
				{
					if(result.participantsList[p].expRoleList[i].id == rolesArray[j].id)
					{
						str += '<option selected="selected" value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
						flag = false;
					}
					
					
				}
				if(flag)
				{
					str += '<option  value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
				}
			}
			str += '</select></div><span id="'+candCunt+'expparticipantRolesErr" class="errDiv"></span></td>';
		}
		else
		{
			str +='<td class="party'+candCunt+'"><input type="hidden" id="'+candCunt+'expparticipantRoles" class="expPartyClass expPartyClass1 expPartiesRoleClass" value="0"></input>';
			str += '<div id="expReoleDiv'+candCunt+'"></div><span id="'+candCunt+'expparticipantRolesErr" class="errDiv"></span></td>';
		}
	
		if(result.participantsList[p].summery != null)
		{
			str += '<td><textarea placeholder="Please Enter Candidate Summary ..." rows="2" cols="25" class="candSummary" name="candSummary'+candCunt+'" id="candSummary'+candCunt+'" >'+result.participantsList[p].summery+'</textarea></td>';
		}
		else
		{
			str += '<td><textarea placeholder="Please Enter Candidate Summary ..." rows="2" cols="25" class="candSummary" name="candSummary'+candCunt+'" id="candSummary'+candCunt+'" ></textarea></td>';
		}
		if(p == 0)
		{
			str +='<td></td>';
		}
		else
		{
			str +='<td><a class="icon-trash" style="cursor: pointer;" onclick="removeCandidate(this.name);" title="Click here to add another Subject" name="row'+candCunt+'"></a></td>';
		}
		str +='</tr>';
		
		candCunt++;
	}
	str +='</table>';
	str += '</div>';
	str += '<div  class="span12">';
	str += '<a title="Click here to add another Subject" onClick="addMoreCandidatesForEdit();"><input type="button"  class="btn btn-success" value="Add More" id=""  style="float: right; margin-bottom: 10px; margin-top: 10px;"/></a>';
	str += '</div>';
	str += '</div>';
	str += '<div id="debateQuestnsDiv">	';
	str += '<div class="row-fluid" >';
	str += '<div id="questionDiv1" class="span12">';
	var count = 1;
	for(var i in result.questionAnswersList)
	{

		str += '<div class="questionAnswerClass row-fluid">';
		str += '<div class="span5">';
		str += '<input type="hidden" Class="selectWidth"  id="question'+count+'" name="question'+count+'" value="'+result.questionAnswersList[i].id+'"></input>';
		str += '<label><p style="font-size: 17px;font-weight: bold;line-height: 1.5;"> '+count+' )'+result.questionAnswersList[i].location+' </p></label>';
		str += '</div>';
		str += '<div class="span7" >';
		str += 'Answer : <font class="requiredFont">*</font><span id="answer'+count+'Err" class="errDiv"></span>';
		str += '<input type="text" Class="selectWidth debateAnswr input-block-level" name="answer'+count+'" id="answer'+count+'" value="'+result.questionAnswersList[i].name+'"></input>';
		str += '</div>';
		str += '</div>';
		count++;
	}
	
	str += '</div>';
	str += '</div>';
	str += '</div>';
	str += '<div id="smsQuestnsDiv" class="row-fluid m_top10">	';
	str += '<legend class="boxHeading">SMS Question :</legend>';
	str += '<div id="smsPole" class="span12">';
	str += '<div class="smsPoleClass">';
	str += '<div class="row">';
	str += '<textarea placeholder="Please Enter SMS Question ..."class="input-block-level" rows="4" cols="50" name="smsques1" id="smsques1" >'+result.smsPoleList[0].name+'</textarea> ';
	str += '</div>';
	
	if(result.smsPoleList != null)
	{
	var percCount = 1;
		for(var i in result.smsPoleList)
		{
			str += '<div class="row">';
			str += '<div class="span7" >';
			str += '<label>';
			str += '<strong>Option : </strong>';
			str += '<span id="smsoption'+percCount+'Err" class="errDiv"></span></label>';
			str += '<input type="text" Class="selectWidth smsOptin span12" name="smsoption1" id="smsoption'+percCount+'" value="'+result.smsPoleList[i].type+'"/>';
			str += '</div>';
			str += '<div class="span3">';
			str += '<label><strong>Percentage : </strong></label><span id="smsper'+percCount+'Err" class="errDiv"></span>';
			if(result.smsPoleList[i].perc > 0)
			{
				str += '<input type="text" Class="selectWidth smsOptinPerc inuput-block-level" name="smsper1" id="smsper'+percCount+'" key="smsoption1" onKeyup="isNumber(\'percentage\',\'smsper1\')",updatePercntage("smsper1"); value="'+result.smsPoleList[i].perc+'"/>';
				str += '</div>';
			}
			else
			{
				str += '<input type="text" Class="selectWidth smsOptinPerc inuput-block-level" name="smsper1" id="smsper'+percCount+'" key="smsoption1" onKeyup="isNumber(\'percentage\',\'smsper1\')",updatePercntage("smsper1"); />';
				str += '</div>';
			}
			/* if(i == 0)
			{
				str += '<div style="width: 160px; float: right; font-size: 12px; color: green;">Remaining Percent  : <span id="percandCunt">0</span>%</div>';
			} */
			
			str += '</div>';
			percCount = percCount+1;
		}
	}
	else
	{
		str += '<div class="row">';
			str += '<div class="span7" >';
			str += '<label>';
			str += '<strong>Option : </strong>';
			str += '<span id="smsoption1Err" class="errDiv"></span></label>';
			str += '<input type="text" Class="selectWidth smsOptin span12" name="smsoption1" id="smsoption1" value="'+result.smsPoleList[i].type+'"/>';
			str += '</div>';
			str += '<div class="span3">';
			str += '<label><strong>Percentage : </strong></label><span id="smsper1Err" class="errDiv"></span>';							
			str += '<input type="text" Class="selectWidth smsOptinPerc inuput-block-level" name="smsper1" id="smsper1" key="smsoption1" onKeyup="isNumber(\'percentage\',\'smsper1\')",updatePercntage("smsper1"); value="'+result.smsPoleList[i].perc+'"/>';
			str += '</div>';
			
			str += '<div style="width: 160px; float: right; font-size: 12px; color: green;">Remaining Percent  : <span id="percandCunt">0</span>%</div>';
			
			str += '</div>';
	}
	
	
	str += '</div>';
	str += ' </div>';

	str += '</div>';
	str += '<div  class="span1 offset10">';
	str += '<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMorePole();"><i class="icon-plus"></i></a>';
	str += '</div>';

				
	str += '<div id="debateSummery" class="row-fluid m_top10">';
	str += '<legend class="boxHeading">Summary :<font class="requiredFont">*</font><span id="debetSumErr" class="errDiv"></span></legend>';
	str += '<div class="control-group form-horizontal">';

	str += '<textarea placeholder="Please Enter Debate Summary ..." rows="4" cols="50" class="span12" name="debetSum" id="debetSum" >'+result.debateSummery+'</textarea>';
	str += '</div>';
					
							
	str += '</div>';
				
	str += '<div align="center" style="margin-bottom: 15px; margin-top: 10px;">';
	str += '<a class="btn btn-success" onClick="submitForm(\'edit\');">Submit</a>';
	str += '</div>';

	str += '</div>';
	str += '<form id="debateFromDiv" method="post" action="saveDebateDetailsAction.action" name="debateFromDiv">';
	str += '<input type="hidden" name="task" id="getDebateDetails" />';
	str += '<input type="hidden" name="debateId" value="'+debateId+'" /></form>';
	
	$('#newDibateDiv').html(str);
	
	$('#channel').val(result.channelId);
	
	for(var i in result.observerList)
	{
		$('#observer').val(result.observerList[i].id);
	}
	
	var pCount = 1;
	for(var p in result.participantsList)
	{
		$('#party'+pCount+'').val(result.participantsList[p].partyId);
		$('#candidate'+pCount+'').val(result.participantsList[p].id);
		$('#participantRoles'+pCount+'').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
		}).multiselectfilter({    
		});
		if(result.participantsList[p].partyId == 872)
		{
			$('#expparticipantRoles'+pCount+'').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
			}).multiselectfilter({    
			});
		}
		
		pCount++;
	}
	
	
	$('#startTime').datetimepicker({
	addSliderAccess: true,
	maxDate:new Date(),
	sliderAccessArgs: { touchonly: false }
	});
    
	$('#endTime').datetimepicker({
	addSliderAccess: true,
	maxDate:new Date(),
	sliderAccessArgs: { touchonly: false }
    });

}

function addMoreCandidatesForEdit()
{
	var str ='';
	str += "<tr id='row"+candCunt+"' class='particepntDetailsRow'>";
	str += "<td><select name='party"+candCunt+"'  id='party"+candCunt+"' list='partiesList' theme='simple' listKey='id' listValue='name' onChange='getCandidatesOfSelectedPartyEdit(this.value,this.id,"+candCunt+");' class='partysClass'><option value='0' selected='selected'>Select</option>";
	for ( var i in partiesArray)
	{
		str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
	}
	str+='</select><span id="party'+candCunt+'Err" class="errDiv"></span></td><td>';

	
	str +='<select theme="simple" Class="selectWidth candidatesClass" name="candidate1" id="candidate'+candCunt+'" >';
	str +='<option value="0"> Select </option>';
	str +='</select>  <span id="candidate'+candCunt+'Err" class="errDiv"></span>';
	str +='<a href="javascript:{}" onclick="createNewCandidate(\'candidate'+candCunt+'\',\'party'+candCunt+'\','+candCunt+')"><span class="btn btn-mini pull-right m_topN65" style="width: 20px;"><img  title="Click Here To Create New Candidate" src="images/user.png" class="createNewCandidate" id="candidate'+candCunt+'"></span></a></td>';
	for(var i in charsArray){
		var myclass =charsArray[i].name+''+candCunt;
		str +='<td>';
		str +='<input type="text" Class="selectWidth '+charsArray[i].id+'CharClass participntRoles '+myclass.replace(/\s/g, '')+'" name="'+charsArray[i].name+'1" id="'+myclass.replace(/\s/g, '')+'" style="width:30px;" onKeyup="isNumber(\'scale\',\''+myclass.replace(/\s/g, '')+'\');"/>';
		var rolesids = myclass.replace(/\s/g, '');
			rolesids = rolesids.replace('(Scale)', '');
		str +='<div id="'+rolesids+'Err" class="errDiv"></div></td>';
	}
	
	str +='<td class="participantRolesblock'+candCunt+'">';
	str +='<input type="hidden" id="'+candCunt+'participantRoles" class="partiRoleClass"></input>';
	str +='<select theme="simple" multiple="multiple" Class="selectWidth participantsRoles" name="'+candCunt+'participantRoles" id="participantRoles'+candCunt+'" key="'+candCunt+'participantRoles">';
	for (var j in rolesArray)
	{
		str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
	}
	
	str +='</select><span id="'+candCunt+'participantRolesErr" class="errDiv"></span></td><td class="party'+candCunt+'">';
	str +='<input type="hidden" id="'+candCunt+'expparticipantRoles" class="expPartyClass  expPartyClass1 expPartiesRoleClass" value="0"></input>';
	str +='<div id="expReoleDiv'+candCunt+'"></div><span id="'+candCunt+'expparticipantRolesErr" class="errDiv"></td>';
	str += '<td><textarea placeholder="Please Enter Candidate Summary ..." rows="2" cols="25" class="candSummary" name="candSummary" id="candSummary'+candCunt+'" ></textarea></td>';
	str +='<td><a  name="row'+candCunt+'" class="icon-trash" title="Click here to add another Subject" onClick="removeCandidate(this.name);" style="cursor: pointer;"></a></td>';
    str +='</tr>';
    
	$("#participantTable").append(str);
	$('#participantRoles'+candCunt+'').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	}).multiselectfilter({    
	});

	
	candCunt++;
}

function getCandidatesOfSelectedPartyEdit(partyId,divId,id)
{

	if(partyId == 872)
	{
		var str  = '';
		str +='<select style="display:none" ;theme="simple" multiple="multiple" Class="selectWidth expparticipantsRoles expPartyClass " name="'+id+'expparticipantRoles" id="expparticipantRoles'+id+'" key="'+id+'expparticipantRoles">';
		for (var j in rolesArray)
		{
			str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
		}
		str += '</select>';
		$('#expReoleDiv'+id+'').html(str);
		$('#expparticipantRoles'+id+'').show();
		$('#expparticipantRoles'+id+'').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	}).multiselectfilter({    
	});
	}
	else
	{
		$('#expReoleDiv'+id+'').html('');
		//$('#expparticipantRoles'+id+'').remove();
	}
	var numb = divId.match(/\d/g);
	//$('#candidate1').find('option').remove();
	var jsObj = {
			partyId :partyId,
			selectedVal :"candidate"+numb+"",
			task : "getCandidatesOfAParty"	
	};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesListForDebateAction.action?"+rparam;
	callAjax(jsObj,url);
}

function validateFieldsForEdit(){

	//$(".subjectClass,#channel,#observer,#telecastTime").css("border","1px solid #CCCCCC");
	//$(".hasDatepicker,.partysClass,.candidatesClass").css("border","1px solid #CCCCCC");
	//$(".participntRoles,.participantsRoles,.smsOptinPerc").css("border","1px solid #CCCCCC");
	//$("#debetSum,#smsques1,.debateAnswr,.smsOptin").css("border","1px solid #CCCCCC");
	//$(".ui-state-default").removeClass("ui-state-error");
	
	$(".errDiv").html('');
	var flag = true;
	var channel = $("#channel").val();
	//var telecastTime = $("#telecastTime").val();
	var startTime =$("#startTime").val();
	var endTime = $("#endTime").val();
	var observer = $("#observer").val();

	var smsQuestin =  $("#smsques1").val();
	var debetSum = $("#debetSum").val();
	var partiRol1e = $(".partiRoleClass").val();

		$( ".subjectClass" ).each(function( index ) {
		 var subject = $( this ).val();
			if(subject.trim().length <= 0){
				var divId = $(this ).attr("id");			
				$("#"+divId+"Err").html("Please enter subject.");
				flag = false;
			}
		});
		if(channel <=0){		
				$("#channelErr").html('Please Select Channel.');
			flag = false;
		}
		/*if(telecastTime <=0){
			$("#telecastTime").css("border","1px solid #D14719");
			flag = false;
		}
			*/
		if(startTime.trim().length <= 0){
			$("#startTimeErr").html('Please Select Start Time.');
			flag = false;
		}
		if(observer <=0){
			$("#observerErr").html('Please Select Observer.');
			flag = false;
		}
		if(endTime.trim().length <= 0){
			$("#endTimeErr").html("Please Select End Time.");
			flag = false;
		}
		var fromTimeArr = startTime.substring(11).split(":");
		var toTimeArr = endTime.substring(11).split(":");	
		
			var fromhours =fromTimeArr[0];
			var frommin   = fromTimeArr[1];

			var tohours = toTimeArr[0];
			var tomin   = toTimeArr[1];
			
		startTime = startTime.substring(0,startTime.length - 6);
		endTime = endTime.substring(0,endTime.length - 6);

		var fromDateArrr = startTime.split("/");			
				var frommonth=fromDateArrr[0];
				var fromDat=fromDateArrr[1];
				var fromyear=fromDateArrr[2];
				
		var toDateArr = endTime.split("/");			
				var tomonth=toDateArr[0];
				var toDat=toDateArr[1];
				var toyear=toDateArr[2];				
		
		if(fromyear>toyear){
			//$("#startTime,#endTime").css("border","1px solid #D14719");
			$("#startTimeErr").html('Start Date should be less than End Date.');
			flag = false;
		}
		 if(frommonth>tomonth){
			   if(fromyear == toyear){
				 //$("#startTime,#endTime").css("border","1px solid #D14719");
				$("#startTimeErr").html('Start Date should be less than End Date.');
				 flag = false;
			}		
		}
		
		if(fromDat>toDat){	
			if(frommonth == tomonth && fromyear == toyear){
				// $("#startTime,#endTime").css("border","1px solid #D14719");
			$("#startTimeErr").html('Start Date should be less than End Date.');
				 flag = false;				 
			   }
		}	
		if( fromhours > tohours ){
			if(frommonth == tomonth && fromyear == toyear && fromDat == toDat){
				//$("#startTime,#endTime").css("border","1px solid #D14719");
				$("#startTimeErr").html('Start Date hour and End Date hour not matching.');
				flag = false;				
			}					
		}		
		if(frommin >= tomin ){
			if(frommonth == tomonth && fromyear == toyear && fromDat == toDat && fromhours == tohours){
				//$("#startTime,#endTime").css("border","1px solid #D14719");
				$("#startTimeErr").html('Start Date minutes and End Date minutes not matching.');
				flag = false;				
			}		
		}
			
		
		$( ".partysClass" ).each(function( index ) {
		 var partyId = $(this ).val();	 
			if(partyId <= 0){
				var divId = $(this ).attr("id");		
				$("#"+divId+"Err").html('Please select Party .');
				flag = false;
			}
		});
		
		$( ".candidatesClass" ).each(function( index ) {
		 var candidate = $( this ).val();

			if(candidate <= 0){
				var divId = $(this ).attr("id");	
				$("#"+divId+"Err").html('Please select Candidate .');				
				flag = false;
			}
		});

		$( ".participntRoles" ).each(function( index ) {
		
		 var prsentation = $( this ).val();
		 var divId = $(this ).attr("id");
		 var rolesids = divId.replace(/\s/g, '');
			rolesids = rolesids.replace('(Scale)', '');
				if(prsentation == null ||  prsentation.trim().length <= 0){		
				$("#"+rolesids+"Err").html(''+rolesids.slice(0, rolesids.lastIndexOf("1"))+' required.');				
				flag = false;			
			}
			if(prsentation >5){
				$("#"+rolesids+"Err").html(''+rolesids.slice(0, rolesids.lastIndexOf("1"))+' between 1 - 5.');
				flag = false;	
			}

		});

		$( ".partiRoleClass" ).each(function( index ) {
		 var participantRoles = $( this ).val();
		 
			if(participantRoles == null || participantRoles.trim().length <= 0){
				var myclass=  $(this).closest('td').attr("class"); 
				var divId = $(this ).attr("id");			
				$("#"+divId+"Err").html('Please select participation role(s).');
				flag = false;
			}
		});		
		
	
		$(".expPartiesRoleClass ").each(function(index){
			var exppartiRole = $(this).val();
			var myclass=  $(this).closest('td').attr("class"); 
		 if($('#'+myclass+'').val() == 872 ){
				if(exppartiRole == null || exppartiRole == 0 || exppartiRole.trim().length <= 0){
					var divId = $(this).attr("id");
					$("#"+divId+"Err").html('Please select expected participation role(s).');
					flag = false;
				}
			}
		});
		
		$(".debateAnswr ").each(function(index){
			var answr = $( this ).val();
			if(answr == null || answr.trim().length <= 0){
				var divId = $(this ).attr("id");				
				$("#"+divId+"Err").html('Answer is Required.');
				flag = false;
			}
				
		});
		/*if(smsQuestin == null || smsQuestin.trim().length <= 0){
				$("#smsques1").css("border","1px solid #D14719");
				flag = false;
		}*/
		if(smsQuestin.trim().length > 0){
			$( ".smsOptin" ).each(function( index ) {
			 var smsOption = $( this ).val();

				if(smsOption == null || smsOption.trim().length <= 0){
					var divId = $(this ).attr("id");
					$("#"+divId+"Err").html('Option is Required.');
					flag = false;
				}
			});
			
			$( ".smsOptinPerc" ).each(function( index ) {
			 var smsOptionPerc = $( this ).val();
			 
				if(smsOptionPerc == null || smsOptionPerc.trim().length <= 0){
					var divId = $(this ).attr("id");
					$("#"+divId+"Err").html('Percentage is Required.');
					flag = false;
				}
			});

			if(totalPerc < 0 || totalPerc > 0 || totalPerc > 100){
				$("#errorForTotal").html('Percentage Must be 100.');
				flag = false;
			}
		}
		if(debetSum == null || debetSum.trim().length <= 0){
				$("#debetSumErr").html('Debate Summary is Required.');
				flag = false;
		}	

	return flag;

}

</script>
</body>


</html>