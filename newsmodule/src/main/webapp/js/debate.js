
var subjCount = 2;
var candCount = 2;
var questionCount = 2;
var poleCount = 2;


function validateFields(){

$("#errTab").css("display","none");
$("#debatErrDiv1,#debatErrDiv2,#debatErrDiv3").html('');
var flag = true;
var channel = $("#channel").val();
var telecastTime = $("#telecastTime").val();
var startTime =$("#startTime").val();
var endTime = $("#endTime").val();
var observer = $("#observer").val();

var smsQuestin =  $("#smsques1").val();
var debetSum = $("#debetSum").val();
var partiRol1e = $(".partiRoleClass").val();

var errStr1='';
var errStr2='';
var errStr3='';


	$( ".subjectClass" ).each(function( index ) {
	 var subject = $( this ).val();

		if(subject.trim().length <= 0){
			errStr1 +='Please Enter Subject .<br/>';
		}
	});
if(channel <=0){
	errStr1 +='Please Select Channel .<br/>';
}
if(telecastTime <=0){
	errStr1 +='Please select Telecaste Time .<br/>';
}
	
if(startTime.trim().length <= 0){
	errStr1 +='Please Enter Start Time .<br/>';
}

if(endTime.trim().length <= 0){
	errStr1 +='Please Enter End Time .<br/>';
}
if(observer <=0){
	errStr1 +='Please Select Observer .<br/>';
}
	$( ".partysClass" ).each(function( index ) {
	 var partyId = $(this ).val();
		if(partyId <= 0){
			errStr2 +='Please Select Party .<br/>';
		}
	});
	
	$( ".candidatesClass" ).each(function( index ) {
	 var candidate = $( this ).val();

		if(candidate <= 0){
			errStr2+='Please Select Candidate .<br/>';
		}
	});

	var presErr = '';
	$( ".participntRoles" ).each(function( index ) {
	 var prsentation = $( this ).val();
		presErr = '';
		if(prsentation == null ||  prsentation.trim().length <= 0){
		presErr = 'Please Enter Presentations.<br/>';
			
		}
	});
	errStr2 =errStr2+' '+presErr;

	if(partiRol1e.trim().length <= 0){
			errStr2 += 'Please Select Participant Roles.<br/>';
		}
		
	$( ".expPartyClass1" ).each(function( index ) {
		var subject1 = $( this ).attr('id');
		var val1 = $('#'+subject1+'').val(); 
		//console.log(val1.length);
		if(val1.length == 0){
		errStr2 += 'Please Select Expected Participant Roles.<br/>';
		}
	});
	$( ".participantRolesClass" ).each(function( index ) {
	 var participantRoles = $( this ).val();

		if(participantRoles == null || participantRoles.trim().length <= 0){
			errStr2 +='Please Select Participant Roles .<br/>';
		}
	});
	
	
	$( ".debateAnswr" ).each(function( index ) {
	 var debateAnswer = $( this ).val();
		presErr='';
		if(debateAnswer == null || debateAnswer.trim().length <= 0){
			presErr +='Please Enter Answer for each Question.<br/>';
		}
	});
	errStr3 +=' '+presErr;
	if(smsQuestin == null || smsQuestin.trim().length <= 0){
			errStr3 +='Please Enter sms Question .<br/>';
	}
		
	$( ".smsOptin" ).each(function( index ) {
	 var smsOption = $( this ).val();

		if(smsOption == null || smsOption.trim().length <= 0){
			errStr3 +='Please Enter SMS Option Value .<br/>';
		}
	});
	
	$( ".smsOptinPerc" ).each(function( index ) {
	 var smsOptionPerc = $( this ).val();

		if(smsOptionPerc == null || smsOptionPerc.trim().length <= 0){
			errStr3 +='Please Enter SMS Option Percentage .<br/>';
		}
	});
	if(debetSum == null || debetSum.trim().length <= 0){
			errStr3 +='Please Enter Debate Summery .<br/>';
	}
	
	if(errStr1.trim().length >0){
	$("#debatErrDiv1").html('');
	$("#errTab").css("display","block");
	$("#debatErrDiv1").html(errStr1);
		flag = false;
	}

	if(errStr2.trim().length >0){
	$("#debatErrDiv2").html('');
	$("#errTab").css("display","block");
	$("#debatErrDiv2").html(errStr2);
		flag = false;
	}

	if(errStr3.trim().length >0){
	$("#debatErrDiv3").html('');
	$("#errTab").css("display","block");
	$("#debatErrDiv3").html(errStr3);
		flag = false;
	}

return flag;

}

function addMoreSubject(){
//console.log(subjCount);
var str = "";

	str += "<span id='subject"+subjCount+"'><label style='font-size: 17px;font-weight: bold;line-height: 1.5;'>Subject : <font class='requiredFont'>*</font><a href='javascript:{}'  title='Click here to remove another Subject' onclick='removeSubject(\"subject"+subjCount+"\");'><i class='icon-trash pull-right' style='margin-left:15px;'></i></a></label>";
	str +="<input type='text' Class='subjectClass span12' name='subject"+subjCount+"' id='subject"+subjCount+"' '/>";
	str += "</br></span>";	
	
	$('#addedSubjectDiv').append(str);
subjCount++;
}

function removeSubject(id){

	$("#"+id+"").html("");
}

function addAttribute(type){
	var str='';
	str +='<div id="innerCreationDiv">';
	str +='<div id="CHerrDiv"></div>';
	str +='<h5 class="text-success"> Create New '+type+'  </h5>';
	str +='	'+type+' Name :  <input type="text" name="'+type+'" id="'+type+'Name"/><div><input type="button" class="btn btn-success" value="Create" onClick="insertAttribtDetails(\''+type+'\');"/></div>';
	str +='</div>';
	
	$("#dialogueBoxDiv").html(str);

	$("#dialogueBoxDiv").dialog({
	title:"Add New "+type,
	            backgroundColor: "purple",
            color: "white",
            width: 500
        });
}

function insertAttribtDetails(id){
	var attrbtName = $("#"+id+"Name").val();
	var task ='';
	if(attrbtName.trim().length<=0){
			$("#CHerrDiv").html(' '+id+'  Name is Required.').addClass("errDiv");
			return;	
		}
	if(id != null && id.length >0 && id.indexOf('Channel') != -1){
		task = 'createNewChannel';
	}
	else
	{
	task = 'createNewObserver';	
	}
	
	$.ajax({
		type: "POST",
		url:"saveDebateRelatedAttributesAction.action",
		data:{attributeName:attrbtName,task:task}
	}).done(function(result){

			if(result != null){
				if(result.resultCode == 0){
					$("#CHerrDiv").html('New '+id+' Created Successfully.').addClass("succDiv").fadeOut(3000);
						setTimeout('$("#dialogueBoxDiv").dialog("close");',1000);
						updateAttributeField(id);
					return;
				}else if(result.resultCode == 1)
				{
					$("#CHerrDiv").html(''+id+' Already Exists With This Name').addClass("errDiv");
					return;
				}
				else
				{
					$("#CHerrDiv").html('Error occured while saving new '+id+'').addClass("errDiv");
					return;
				}
			}else
				{
					$("#CHerrDiv").html('Error occured while saving new '+id+'').addClass("errDiv");
					return;
				}
	});
	
}
function addMorePole(){
	
	var str = "";
	str += "<div class='smsPoleClass row-fluid'>";
	str += "<div class='row"+poleCount+"' style='margin-left:-21px'>";
	str += "<div class='span7'>";
	str += "<label><strong>Option : <font class='requiredFont'>*</font></strong>";
	str += "<input type='text' Class='selectWidth smsOptin span12' name='smsoption"+poleCount+"' id='smsoption"+poleCount+"'/>";
	str += "</div>";
	str += "<div class='span3'>";
	str += "<label><strong>Percentage : <font class='requiredFont'>*</font></strong></label>";
	str += "<input type='text' Class='selectWidth smsOptinPerc' name='smsper"+poleCount+"' id='smsper"+poleCount+"' key='smsoption"+poleCount+"'/>";
	str += "</div>";
	str += "<div class='span1' style='float: left; margin-top: 30px;'>";
	str += "<a href='javascript:{}'  title='Click here to remove another Option' onclick='removeOptins(\"row"+poleCount+"\");'><i class='icon-trash pull-right' style='margin-left:15px;'></i></a>";
	str += "</div>";
	str += "</div>";
	str += "</div>";
	$('#smsPole').append(str);
	poleCount++;
}

function removeOptins(divId)
{
	$('.'+divId+'').html('');
}
function updateAttributeField(id){
	
	$.ajax({
		type: "POST",
		url:"updateFieldAttributesAction.action",
		data:{attributeName:id,task:"update"+id+""}
	}).done(function(result){
		//console.log(result);		
		if(result != null){
		id = id.toLowerCase();
		$('#'+id+" option").remove();
			$.each( result, function( index, value ){
				$("#"+id+"").append("<option id="+result[index].id+">"+result[index].name+"</option>");
			});
		}
		
	});

}

function submitForm()
{
	if(validateFields()){
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
			$( ".subjectClass " ).each(function( index ) {
				subjectArray.push($(this ).val());	
			});
			debateDetails.endTime         = $('#endTime').val();
			debateDetails.startTime       = $('#startTime').val();
			debateDetails.channelId       = $('#channel option:selected').val();
			debateDetails.telecastTimeId  = $('#telecastTime option:selected').val();
			observer.push($('#observer option:selected').val());
			$('.particepntDetailsRow').each(function() {
				var participantObj = {
					  partyId: '' ,
					  candidateId : '' ,
					  summery     : '',
					  participantRoles:[],
					  expparticipantRoles:[],
					  scale : [
					  {
						 scaleId : '',
						 scaleTotal : ''
					  }
					  ]
				};
				
				participantObj.partyId  = $(this).closest("tr").find('.partysClass').val();
				participantObj.candidateId = $(this).closest("tr").find('.candidatesClass').val();
				//console.log(charsArray);
				 var scaleObj = {
					 scaleId : '',
					 scaleTotal : ''
					}; 
				participantObj.summery = 'prasad';
				participantObj.scale = [];
				for(var i=0;i <charsArray.length;i++)
				{
					scaleObj = {};
					scaleObj.scaleId    = charsArray[i].id;
					scaleObj.scaleTotal = $(this).closest("tr").find('.'+charsArray[i].id+'CharClass').val();
					participantObj.scale.push(scaleObj);
				}
				var partiRoles = $(this).closest("tr").find('.partiRoleClass').val();
				var partiArray = partiRoles.split(',');
				var expRoles = $(this).closest("tr").find('.expPartyClass').val();
				if(expRoles != '')
				{
					var exppartiArray = expRoles.split(',');
					participantObj.expparticipantRoles = exppartiArray;
				}
				
				//participantObj.participantRoles.push(partiRoles);
				participantObj.participantRoles = partiArray;
				//participantObj.expparticipantRoles.push(expRoles);
				
				participant.push(participantObj);	
			});
			//console.log(participant);
			var j = 0;
			$( ".questionAnswerClass " ).each(function( index ) {
				j++;
				var questionAnswerObj = {};
				questionAnswerObj.questionId = $('#question'+j+'').val();	
				questionAnswerObj.answer     = $('#answer'+j+'').val();	
				questionAnswer.push(questionAnswerObj);
			});
			
			$( ".smsOptinPerc " ).each(function( index ) {
				var smaPoleObj = {};
				smaPoleObj.questionId  = $('#smsques1').val();	
				smaPoleObj.option      = $('#'+$(this).attr('key')).val();
				smaPoleObj.percentage  = $(this).val();
				smsPole.push(smaPoleObj);		
			});
			//console.log(smsPole);
			debateDetails.debetSummery = $('#debetSum').val();
			
				var jsObj = {
						debateDetails :debateDetails,
						participant   : participant,
						observer     : observer,
						subjectArray : subjectArray,
						questionAnswer : questionAnswer,
						smsPole : smsPole,
						task : "saveDebateDetails"	
				};
					 $("#getDebateDetails").val(YAHOO.lang.JSON.stringify(jsObj));
							  
					 var uploadHandler = {
					 success: function(o) {
					 var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
					    $('#successMsg').show();
						if(uploadResult.resultCode == 0)
						{
							$('#successMsg').html('<b style="color:green;">Debate Created Successfully</b>');
							$('html, body').animate({
								 scrollTop: $("#successMsg").offset().top
							 }, 2000);
							 $('#successMsg').delay( 8000 )
							 location.reload();
						}
						else
						{
							$('#successMsg').html('<b style="color:red;">Error Occured While Saving the debate information</b>');
						}
						
					 }
					 };
					 YAHOO.util.Connect.setForm('debateFromDiv',false);
					 YAHOO.util.Connect.asyncRequest('POST','saveDebateDetailsAction.action',uploadHandler);
			//console.log(debateDetails);
		}
		else{
		console.log("error");
		}
}
	
function getValues(){
	var str ='';
	str +='<table id="participantTable" class="table table-bordered particepatedTable" style="width: 100%;overflow-x: scroll;">';
	str +='<thead><tr><th>Party</th><th>Candidate</th>';
	for(var i in charsArray){
		str +='<th>'+charsArray[i].name+'</th>';
	}
	str +='<th>Participant Roles</th><th>Expected Roles</th><th>delete</th></tr></thead>';
   
	str += "<tr id='row1' class='particepntDetailsRow'>";
	str += "<td><select name='party1'  id='party1' list='partiesList' theme='simple' listKey='id' listValue='name' onChange='getCandidatesOfSelectedParty(this.value,this.id,1)' class='partysClass'><option value='0' selected='selected'>Select</option>";
	for ( var i in partiesArray)
	{
		str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
	}
	str+='</select></td>';

	str +='<td><select theme="simple" Class="selectWidth candidatesClass" name="candidate1" id="candidate1" >';
	str+='<option value="0"> Select </option>';
	str +='</select></td>';
		for(var i in charsArray){
		str +='<td>';
		str +='<input type="text" Class="selectWidth '+charsArray[i].id+'CharClass participntRoles" name="'+charsArray[i].name+'1" id="'+charsArray[i].name+'1" style="width:30px;" />';
		str +='</td>';
		}
     	str +='<td><input type="hidden" id="'+1+'participantRoles" class="partiRoleClass"></input>';
		str += '<select theme="simple" Class="selectWidth participantsRoles" name="participantRoles1" id="participantRoles1" key="'+1+'participantRoles">';
		for (var j in rolesArray)
		{
			str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
		}
		str +='</select></td>';
		str +='<td><input type="hidden" id="'+1+'expparticipantRoles" class="expPartyClass expPartyClass1" value="0"></input>';
		str += '<div id="expReoleDiv1"><select style="display:none;" theme="simple" Class="selectWidth expparticipantsRoles expPartyClass" name="expparticipantRoles1" id="expparticipantRoles1" key ="'+1+'expparticipantRoles" >';
		for (var j in rolesArray)
		{
			str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
		}
		str += '</select></div></td>';
	
	//str +='<td><!--<a  name="row1" class="icon-trash" title="Click here to add another Subject" onClick="removeCandidate(this.name);"></a></td>';
	str +='<td></td>';
    str +='</tr></table>';
    
$("#participantInnerDiv1").append(str);

	$('#participantRoles1').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	}).multiselectfilter({    
	});

	
}

function removeCandidate(name){
$("#"+name+"").remove();
}


function addMoreCandidates()
{
	var str ='';
	str += "<tr id='row"+candCount+"' class='particepntDetailsRow'>";
	str += "<td><select name='party1'  id='party"+candCount+"' list='partiesList' theme='simple' listKey='id' listValue='name' onChange='getCandidatesOfSelectedParty(this.value,this.id,"+candCount+");' class='partysClass'><option value='0' selected='selected'>Select</option>";
	for ( var i in partiesArray)
	{
		str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
	}
	str+='</select></td><td>';

	
	str +='<select theme="simple" Class="selectWidth candidatesClass" name="candidate1" id="candidate'+candCount+'" >';
	str +='<option value="0"> Select </option>';
	str +='</select></td>';
		for(var i in charsArray){
		str +='<td>';
		str +='<input type="text" Class="selectWidth '+charsArray[i].id+'CharClass participntRoles" name="'+charsArray[i].name+'1" id="'+charsArray[i].name+''+candCount+'" style="width:30px;" />';
		str +='</td>';
	}
	
	str +='<td>';
	str +='<input type="hidden" id="'+candCount+'participantRoles" class="partiRoleClass"></input>';
	str +='<select theme="simple" Class="selectWidth participantsRoles" name="participantRoles'+candCount+'" id="participantRoles'+candCount+'" key="'+candCount+'participantRoles">';
	for (var j in rolesArray)
	{
		str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
	}
	
	str +='</select></td><td>';
	str +='<input type="hidden" id="'+candCount+'expparticipantRoles" class="expPartyClass  expPartyClass1" value="0"></input>';
	str +='<div id="expReoleDiv'+candCount+'"><select style="display:none" ;theme="simple" Class="selectWidth expparticipantsRoles expPartyClass " name="expparticipantRoles'+candCount+'" id="expparticipantRoles'+candCount+'" key="'+candCount+'expparticipantRoles">';
	for (var j in rolesArray)
	{
		str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
	}
	str += '</select></td>';
	str +='<td><a  name="row'+candCount+'" class="icon-trash" title="Click here to add another Subject" onClick="removeCandidate(this.name);" style="cursor: pointer;"></a></td>';
    str +='</tr>';
    
	$("#participantTable").append(str);
	$('#participantRoles'+candCount+'').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	}).multiselectfilter({    
	});

	
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

function getCandidatesOfSelectedParty(partyId,divId,id)
{
	if(partyId == 872)
	{
		var str  = '';
		str +='<select style="display:none" ;theme="simple" Class="selectWidth expparticipantsRoles expPartyClass " name="expparticipantRoles'+id+'" id="expparticipantRoles'+id+'" key="'+id+'expparticipantRoles">';
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
				}else if (jsObj.task == "getDebateDetailsBtDates")
				{
				 buildDebateBTDatesTable(myResults);
				}else if (jsObj.task == "generateURL")
				{
					$('#'+jsObj.div).text(myResults + "&debateId="+jsObj.debateId);
					$('#'+jsObj.div).show();
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

function getDebateDetailsBtDates()
{
	var startDate = $('#fromDateId').val();
	var endDate = $('#toDateId').val();
	$("#RerrDiv").html('');
	$("#dateWiseReportDiv").html("");
	if(startDate != undefined && startDate.length <=0){
		$("#RerrDiv").html("From Date is Required.");
		return;
	}
	if(endDate != undefined && endDate.length <=0){	
		$("#RerrDiv").html("To Date is Required.");
		return;		
	}
	
	var fromDateArrr = startDate.split("/");			
			var frommonth=fromDateArrr[0];
			var fromDat=fromDateArrr[1];
			var fromyear=fromDateArrr[2];
			
	var toDateArr = endDate.split("/");			
			var tomonth=toDateArr[0];
			var toDat=toDateArr[1];
			var toyear=toDateArr[2];
	
	if(fromyear>toyear){
		$("#RerrDiv").html('From Date should not greater then To Date ');	
		return;
	}
	 if(frommonth>tomonth){
		   if(fromyear == toyear){
			$("#RerrDiv").html('From Date should not greater then To Date ');		
		return;
		}		
	}
	
	if(fromDat>toDat){	
		if(frommonth == tomonth && fromyear == toyear){
			$("#RerrDiv").html('From Date should not greater then End Date ');		
			return;				
		   }
	}
	
	$("#RerrDiv").html('');
	
	var jsObj = {
				fronDate :$('#fromDateId').val(),
				toDate   :$('#toDateId').val(),
				task : "getDebateDetailsBtDates"	
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "debateDetaildBtDatesAction.action?"+rparam;
		callAjax(jsObj,url);
}

function openDebateReport(debateId)
{
	window.open("debateReportAction.action?debateId="+debateId+"");
}

function generateURL(debateId,div,description)
{
	    var jsObj = {
				debateId : debateId,
				description   : description,
				div:  div,
				task     : "generateURL"	
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "generateKeyReportAction.action?"+rparam;
		callAjax(jsObj,url);
}
function  buildDebateBTDatesTable(results)
{
	var str = '';
	if(results.length >0){
		
		str +='<table id="debatesTab">';
		str +='<thead>';
		str +='<th>Subject</th>';
		str +='<th>Created Date</th>';
		str +='<th>View Report</th>';
		str +='<th>Generate URL </th>';
		str +='<th> </th>';
		str +='</thead>';
		str +='<tbody>';

		for(var i in results){
		str +='<tr>';
		str +='<td>'+results[i].name+'</td>';
		str +='<td>'+results[i].type+'</td>';
		str +='<td><a class="btn btn-info" value="'+results[i].id+'"';
		str +='onClick="openDebateReport('+results[i].id+')">view</a></td>';
		str +='<td><input type="button" class="btn btn-info" value="Generate URL " onCLick="generateURL('+results[i].id+',\'reportId'+results[i].id+'\',\''+results[i].name+'\')"/></td>';
		str +='<td><textarea id="reportId'+results[i].id+'" placeholder="Generated URL..."></textarea></td>';
		str +='</tr>';
		}
		str +='</tbody>';
		str +='</table>';
		
		$("#dateWiseReportDiv").html(str);
		
		$("#debatesTab").dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null]
		});

	}
}