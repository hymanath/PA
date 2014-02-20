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
	#errorMsgDiv,#RerrDiv,#RerrDivForAnalysis{
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

function callAjax(jsObj,url)
{

	var callback =
	{			
		success : function( o )
		{
			try
			{ 
				myResults = YAHOO.lang.JSON.parse(o.responseText); 
				if (jsObj.task == "getDebateDetails")
				{
					if(myResults != null)
					prepopulateDebateForm(myResults);	
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
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Subject : <font class="requiredFont">*</font><a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMoreSubject();"><i class="icon-plus" style="margin-left:15px;"></i></a></label>';
	for(var i in result.debateNames)
	{
		if(i == 0)
		{
			str += '<input type="text" Class="subjectClass span12" name="subject1" id="subject1" value="'+result.debateNames[i]+'"></input>';
		}
		else
		{
			str += "<span id='addedsubject"+subjCount+"'><label style='font-size: 17px;font-weight: bold;line-height: 1.5;'>Subject : <font class='requiredFont'>*</font><a href='javascript:{}'  title='Click here to remove another Subject' onclick='removeSubject(\"addedsubject"+subjCount+"\");'><i class='icon-trash pull-right' style='margin-left:15px;'></i></a></label>";
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
	str += '<a class="btn btn-mini pull-right" onclick="addAttribute("Channel");" title="Click here to add another Channel" href="javascript:{}"><i class="icon-plus"></i></a></label>';
	str += '<select name="channel"  id="channel">';
	for(var i in channelsArray)
	{
		str += '<option value='+channelsArray[i].id+'>'+channelsArray[i].name+'</option>';
	}
	str += '</select>';
	
	str += '</div>';
	str += '<div class="span3">';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Observer : <font class="requiredFont">*</font><a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Observer" onClick="addAttribute("Observer");"><i class="icon-plus"></i></a></label>';
	str += '<select name="observer"  id="observer" >';
	for(var i in observerArray)
	{
			str += '<option value='+observerArray[i].id+'>'+observerArray[i].name+'</option>';
	}
	str += '</select>';
	str += '</div>	';
	str += '<div class="span3">';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Start Date : <font class="requiredFont">*</font></label>';
	str += '<input type="text" class="input-block-level selectWidth" name="startTime" id="startTime" value="'+result.stDate+'"></input>';
	str += '</div>			';	
	str += '<div class="span3">';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">End Date : <font class="requiredFont">*</font></label>';
	str += '<input type="text" class="input-block-level selectWidth" name="endTime" id="endTime" value="'+result.edDate+'"></input>';
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
	var candCount = 1;
    for(var p in result.participantsList)
	{
		str += "<tr id='row"+candCount+"' class='particepntDetailsRow'>";
		str += "<td><select name='party"+candCount+"'  id='party"+candCount+"' onChange='getCandidatesOfSelectedParty(this.value,this.id,1)' class='partysClass'><option value='0' selected='selected'>Select</option>";
		for ( var i in partiesArray)
		{
			str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
		}
		str+='</select></td>';

		str +='<td><select theme="simple" Class="selectWidth candidatesClass" name="candidate'+candCount+'" id="candidate'+candCount+'" >';
		str+='<option value="'+result.participantsList[p].id+'"> '+result.participantsList[p].name+'</option>';
		str +='</select>';
		str +='<a href="javascript:{}" onclick="createNewCandidate(\'candidate1\',\'party1\',1)"><span class="btn btn-mini pull-right m_topN65" style="width: 20px;"><img  title="Click Here To Create New Candidate" src="images/user.png" class="createNewCandidate" id="candidate'+candCount+'"></span></a></td>';
	    for(var i in result.participantsList[p].scaleList)
		{
	
			var myClass1 = result.participantsList[p].scaleList[i].name+""+candCount+"";
			str +='<td>';
			str +='<input type="text" Class="selectWidth '+charsArray[i].id+'CharClass participntRoles '+myClass1.replace(/\s/g, '')+'" name="'+charsArray[i].name+''+candCount+'" id="'+myClass1.replace(/\s/g, '')+'" style="width:30px;" onKeyup="isNumber(\'scale\',\''+myClass1.replace(/\s/g, "")+'\');" value="'+result.participantsList[p].scaleList[i].perc+'"/><div style="font-weight:normal;">(0-5)</div>';
			str +='</td>';
		}
     	str +='<td class="participantRolesblock1"><input type="hidden" id="'+candCount+'participantRoles" class="partiRoleClass" value="'+result.participantsList[p].roleList[0].totalCount+'"></input>';
		var roleArray = ""
		str += '<select theme="simple" multiple="true"  Class="selectWidth participantsRoles" name="'+candCount+'participantRoles" id="participantRoles'+candCount+'" key="'+candCount+'participantRoles">';
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
		str +='</select></td>';
		
		if(result.participantsList[p].partyId == 872)
		{
			str +='<td class="expparticipantRolesblock1"><input type="hidden" id="'+candCount+'expparticipantRoles" class="expPartyClass expPartyClass1 expPartiesRoleClass" ></input>';
			str += '<div id="expReoleDiv'+candCount+'"><select  multiple="multiple" theme="simple" Class="selectWidth expparticipantsRoles expPartyClass" name="'+candCount+'expparticipantRoles" id="expparticipantRoles'+candCount+'" key ="'+candCount+'expparticipantRoles" >';
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
			str += '</select></div></td>';
		}
		else
		{
			str +='<td class="expparticipantRolesblock1"><input type="hidden" id="'+1+'expparticipantRoles" class="expPartyClass expPartyClass1 expPartiesRoleClass" value="0"></input>';
			str += '<div id="expReoleDiv'+candCount+'"><select style="display:none;" theme="simple" Class="selectWidth expparticipantsRoles expPartyClass" name="expparticipantRoles'+candCount+'" id="expparticipantRoles'+candCount+'" key ="'+1+'expparticipantRoles" >';
			for (var j in rolesArray)
			{
				str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
			}
			str += '</select></div></td>';
		}
	
		if(result.participantsList[p].summery != null)
		{
			str += '<td><textarea placeholder="Please Enter Candidate Summary ..." rows="2" cols="25" class="candSummary" name="candSummary'+candCount+'" id="candSummary'+candCount+'" >'+result.participantsList[p].summery+'</textarea></td>';
		}
		else
		{
			str += '<td><textarea placeholder="Please Enter Candidate Summary ..." rows="2" cols="25" class="candSummary" name="candSummary'+candCount+'" id="candSummary'+candCount+'" ></textarea></td>';
		}
		if(p == 0)
		{
			str +='<td></td>';
		}
		else
		{
			str +='<td><a class="icon-trash" style="cursor: pointer;" onclick="removeCandidate(this.name);" title="Click here to add another Subject" name="row'+candCount+'"></a></td>';
		}
		str +='</tr>';
		
		candCount++;
	}
	str +='</table>';
	
	str += '</div>';
	str += '<div  class="span12">';
	str += '<a title="Click here to add another Subject" onClick="addMoreCandidates();"><input type="button"  class="btn btn-success" value="Add More" id=""  style="float: right; margin-bottom: 10px; margin-top: 10px;"/></a>';
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
		str += 'Answer : <font class="requiredFont">*</font>';
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
		for(var i in result.smsPoleList)
		{
			str += '<div class="row">';
			str += '<div class="span7" >';
			str += '<label>';
			str += '<strong>Option : </strong>';
			str += '</label>';
			str += '<input type="text" Class="selectWidth smsOptin span12" name="smsoption1" id="smsoption1" value="'+result.smsPoleList[i].type+'"/>';
			str += '</div>';
			str += '<div class="span3">';
			str += '<label><strong>Percentage : </strong></label>';							
			str += '<input type="text" Class="selectWidth smsOptinPerc inuput-block-level" name="smsper1" id="smsper1" key="smsoption1" onKeyup="isNumber(\'percentage\',\'smsper1\')",updatePercntage("smsper1"); value="'+result.smsPoleList[i].perc+'"/>';
			str += '</div>';
			/* if(i == 0)
			{
				str += '<div style="width: 160px; float: right; font-size: 12px; color: green;">Remaining Percent  : <span id="percandCount">0</span>%</div>';
			} */
			
			str += '</div>';
		}
	}
	else
	{
		str += '<div class="row">';
			str += '<div class="span7" >';
			str += '<label>';
			str += '<strong>Option : </strong>';
			str += '</label>';
			str += '<input type="text" Class="selectWidth smsOptin span12" name="smsoption1" id="smsoption1" value="'+result.smsPoleList[i].type+'"/>';
			str += '</div>';
			str += '<div class="span3">';
			str += '<label><strong>Percentage : </strong></label>';							
			str += '<input type="text" Class="selectWidth smsOptinPerc inuput-block-level" name="smsper1" id="smsper1" key="smsoption1" onKeyup="isNumber(\'percentage\',\'smsper1\')",updatePercntage("smsper1"); value="'+result.smsPoleList[i].perc+'"/>';
			str += '</div>';
			
			str += '<div style="width: 160px; float: right; font-size: 12px; color: green;">Remaining Percent  : <span id="percandCount">0</span>%</div>';
			
			str += '</div>';
	}
	
	
	str += '</div>';
	str += ' </div>';

	str += '</div>';
	str += '<div  class="span1 offset10">';
	str += '<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Subject" onClick="addMorePole();"><i class="icon-plus"></i></a>';
	str += '</div>';

				
	str += '<div id="debateSummery" class="row-fluid m_top10">';
	str += '<legend class="boxHeading">Summary :</legend>';
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
</script>
</body>


</html>