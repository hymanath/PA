
var subjCount = 2;
var candCount = 2;
var questionCount = 2;
var poleCount = 2;
var partyDiv;
var roleOptionsID ;
var debateNewCandiPartyId;

function validateFields(){

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

		/*$( ".subjectClass" ).each(function( index ) {
		 var subject = $( this ).val();
			if(subject.trim().length <= 0){
				var divId = $(this ).attr("id");			
				$("#"+divId+"Err").html("Please enter subject.");
				flag = false;
			}
		});*/
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
		 console.log(divId);
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
									
				if(exppartiRole == null || exppartiRole.trim().length <= 0){
					var myclass=  $(this).closest('td').attr("class"); 
					var divId = $(this).attr("id");
					$("#"+divId+"Err").html('Please select expected participation role(s).');
					flag = false;
				}
		});
		/*$(".debateAnswr ").each(function(index){
			var answr = $( this ).val();
			if(answr == null || answr.trim().length <= 0){
				var divId = $(this ).attr("id");				
				$("#"+divId+"Err").html('Answer is Required.');
				flag = false;
			}
				
		});*/
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
		/*if(debetSum == null || debetSum.trim().length <= 0){
				$("#debetSumErr").html('Debate Summary is Required.');
				flag = false;
		}	*/

	return flag;

}

function addMoreSubject(){
//console.log(subjCount);
var str = "";

	str += "<span id='addedsubject"+subjCount+"'><label style='font-size: 17px;font-weight: bold;line-height: 1.5;'>Subject : <font class='requiredFont'>*</font><span id='subject"+subjCount+"Err' class='errDiv' style='margin-left: 100px;'></span><a href='javascript:{}'  title='Click here to remove another Subject' onclick='removeSubject(\"addedsubject"+subjCount+"\");'><i class='icon-trash pull-right' style='margin-left:15px;'></i></a></label>";
	str +="<input type='text' Class='subjectClass span12' name='subject"+subjCount+"' id='subject"+subjCount+"' '/>";
	str += "</br></span>";	
	
	$('#addedSubjectDiv').append(str);
subjCount++;
}

function removeSubject(id){
  
 var isConfirm = confirm("Do you want to continue ?");
   if( isConfirm == true ){
      	$("#"+id+"").html("");
	  return true;
   }else{
	  return false;
   }

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
	str += "<div class='row"+poleCount+"' style='margin-left:-11px'>";
	str += "<div class='span7'>";
	str += "<label><strong>Option : <font class='requiredFont'>*</font></strong><span id='smsoption"+poleCount+"Err' class='errDiv'></span>";
	str += "<input type='text' Class='selectWidth smsOptin span12' name='smsoption"+poleCount+"' id='smsoption"+poleCount+"'/>";
	str += "</div>";
	str += "<div class='span3'>";
	str += "<label><strong>Percentage : <font class='requiredFont'>*</font></strong><span id='smsper"+poleCount+"Err' class='errDiv'></span></label>";
	str += "<input type='text' Class='selectWidth smsOptinPerc' name='smsper"+poleCount+"' id='smsper"+poleCount+"' key='smsoption"+poleCount+"' onKeyup='isNumber(\"percentage\",\"smsper"+poleCount+"\"),updatePercntage(\"smsper"+poleCount+"\");'/>";
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
var isConfirm = confirm("Are you want to delete ?")
  if( isConfirm == true ){
		$('.'+divId+'').html('');
		updatePercntage();
	  return true;
   }else{
	  return false;
   }		
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

function submitForm(type)
{
	if(type == 'edit'){
		if(validateFieldsForEdit()){
				saveDetails(type)
		}
	}
	else if(validateFields()){
			saveDetails(type)
	}
	else{
		console.log("error");
	}
}

function saveDetails(type){
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
			//debateDetails.telecastTimeId  = $('#telecastTime option:selected').val();
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
				participantObj.summery = $(this).closest("tr").find('.candSummary').val();
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
				smaPoleObj.percentage  = $(this).val() !="" ? $(this).val():0.0;
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
						type : type,
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
							//$('#successMsg').delay( 2000 );
							$('html, body').animate({
								 scrollTop: $("#successMsg").offset().top
							 }, 2000);
							 
						//	 setTimeout('window.location = "debateAction.action"',5000);							
							 setTimeout('window.location = "debateAction.action?fromDate='+fromDateEdit+'&toDate='+toDateEdit+'&channel='+channelEdit+'&partyId='+partyIdEdit+'&candidateId='+candidateIdEdit+'&"',5000);
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
	
function getValues(){
	var str ='';
	str +='<table id="participantTable" class="table table-bordered particepatedTable" style="width: 100%;overflow-x: scroll;">';
	str +='<thead><tr><th>Party</th><th> Candidate</th>';
	for(var i in charsArray){
		str +='<th>'+charsArray[i].name+'</th>';
	}
	str +='<th>Participant Roles</th><th>Expected Roles</th><th>Summary </th><th>delete</th></tr></thead>';
   
	str += "<tr id='row1' class='particepntDetailsRow'>";
	str += "<td><select name='party1'  id='party1' list='partiesList' theme='simple' listKey='id' listValue='name' onChange='getCandidatesOfSelectedParty(this.value,this.id,1)' class='partysClass'><option value='0' selected='selected'>Select</option>";
	for ( var i in partiesArray)
	{
		str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
	}
	str+='</select><span id="party1Err" class="errDiv"></span></td>';

	str +='<td><select theme="simple" Class="selectWidth candidatesClass" name="candidate1" id="candidate1" >';
	str+='<option value="0"> Select </option>';
	str +='</select>';
	str +='<a href="javascript:{}" onclick="createNewCandidate(\'candidate1\',\'party1\',1)"><span class="btn btn-mini pull-right m_topN65" style="width: 20px;"><img  title="Click Here To Create New Candidate" src="images/user.png" class="createNewCandidate" id="candidate1"></span></a> <span id="candidate1Err" class="errDiv"></span></td>';
		for(var i in charsArray){
		var myClass1 = charsArray[i].name+"1";
		str +='<td>';
		str +='<input type="text" Class="selectWidth '+charsArray[i].id+'CharClass participntRoles '+myClass1.replace(/\s/g, '')+'" name="'+charsArray[i].name+'1" id="'+myClass1.replace(/\s/g, '')+'" style="width:30px;" onKeyup="isNumber(\'scale\',\''+myClass1.replace(/\s/g, "")+'\');"/>';
		var rolesids1 = myClass1.replace(/\s/g, '');
			rolesids1 = rolesids1.replace('(Scale)', '');
		str +='<div id="'+rolesids1+'Err" class="errDiv"></div></td>';
		}
     	str +='<td class="participantRolesblock1"><input type="hidden" id="'+1+'participantRoles" class="partiRoleClass"></input>';
		str += '<select theme="simple" Class="selectWidth participantsRoles" name="participantRoles1" id="participantRoles1" key="'+1+'participantRoles">';
		for (var j in rolesArray)
		{
			str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
		}
		str +='</select><span id="1participantRolesErr" class="errDiv"></span></td>';
		str +='<td class="expparticipantRolesblock1"><input type="hidden" id="'+1+'expparticipantRoles" class="expPartyClass expPartyClass1 expPartiesRoleClass" value="0"></input>';
		str += '<div id="expReoleDiv1"><select style="display:none;" theme="simple" Class="selectWidth expparticipantsRoles expPartyClass" name="expparticipantRoles1" id="expparticipantRoles1" key ="'+1+'expparticipantRoles" >';
		for (var j in rolesArray)
		{
			str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
		}
		str += '</select></div><span id="1expparticipantRolesErr" class="errDiv"></span></td>';
	
	//str +='<td><!--<a  name="row1" class="icon-trash" title="Click here to add another Subject" onClick="removeCandidate(this.name);"></a></td>';
	str += '<td><textarea placeholder="Please Enter Candidate Summary ..." rows="2" cols="25" class="candSummary" name="candSummary" id="candSummary1" ></textarea></td>';
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
var isConfirm = confirm("Are you want to delete ?")
  if( isConfirm == true ){
     $("#"+name+"").remove();
	  return true;
   }else{
	  return false;
   }
  }


function addMoreCandidates()
{
	var str ='';
	str += "<tr id='row"+candCount+"' class='particepntDetailsRow'>";
	str += "<td><select name='party"+candCount+"'  id='party"+candCount+"' list='partiesList' theme='simple' listKey='id' listValue='name' onChange='getCandidatesOfSelectedParty(this.value,this.id,"+candCount+");' class='partysClass'><option value='0' selected='selected'>Select</option>";
	for ( var i in partiesArray)
	{
		str += '<option value='+ partiesArray[i].id + '>'+ partiesArray[i].name + '</option>';
	}
	str+='</select><span id="party'+candCount+'Err" class="errDiv"></span></td><td>';

	
	str +='<select theme="simple" Class="selectWidth candidatesClass" name="candidate1" id="candidate'+candCount+'" >';
	str +='<option value="0"> Select </option>';
	str +='</select> <span id="candidate'+candCount+'Err" class="errDiv"></span>';
	str +='<a href="javascript:{}" onclick="createNewCandidate(\'candidate'+candCount+'\',\'party'+candCount+'\','+candCount+')"><span class="btn btn-mini pull-right m_topN65" style="width: 20px;"><img  title="Click Here To Create New Candidate" src="images/user.png" class="createNewCandidate" id="candidate'+candCount+'"></span></a></td>';
	for(var i in charsArray){
		var myclass =charsArray[i].name+''+candCount;
		str +='<td>';
		str +='<input type="text" Class="selectWidth '+charsArray[i].id+'CharClass participntRoles '+myclass.replace(/\s/g, '')+'" name="'+charsArray[i].name+'1" id="'+myclass.replace(/\s/g, '')+'" style="width:30px;" onKeyup="isNumber(\'scale\',\''+myclass.replace(/\s/g, '')+'\');"/>';

		var rolesids = myclass.replace(/\s/g, '');
			rolesids = rolesids.replace('(Scale)', '');
		str +='<div id="'+rolesids+'Err" class="errDiv"></div></td>';
	}
	
	str +='<td class="participantRolesblock'+candCount+'">';
	str +='<input type="hidden" id="'+candCount+'participantRoles" class="partiRoleClass"></input>';
	str +='<select theme="simple" Class="selectWidth participantsRoles" name="participantRoles'+candCount+'" id="participantRoles'+candCount+'" key="'+candCount+'participantRoles">';
	for (var j in rolesArray)
	{
		str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
	}
	
	str +='</select><span id="'+candCount+'participantRolesErr" class="errDiv"></span></td><td class="expparticipantRolesblock'+candCount+'">';
	str +='<input type="hidden" id="'+candCount+'expparticipantRoles" class="expPartyClass  expPartyClass1 expPartiesRoleClass" value="0"></input>';
	str +='<div id="expReoleDiv'+candCount+'"><select style="display:none" ;theme="simple" Class="selectWidth expparticipantsRoles expPartyClass " name="expparticipantRoles'+candCount+'" id="expparticipantRoles'+candCount+'" key="'+candCount+'expparticipantRoles">';
	for (var j in rolesArray)
	{
		str += '<option value="'+rolesArray[j].id+'">'+rolesArray[j].name+'</option>';
	}
	str += '</select></div><span id="'+candCount+'expparticipantRolesErr" class="errDiv"></span></td>';
	str += '<td><textarea placeholder="Please Enter Candidate Summary ..." rows="2" cols="25" class="candSummary" name="candSummary" id="candSummary'+candCount+'" ></textarea></td>';
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
		str +='<select style="display:none" ;theme="simple" multiple="multiple" Class="selectWidth expparticipantsRoles expPartyClass " name="expparticipantRoles'+id+'" id="expparticipantRoles'+id+'" key="'+id+'expparticipantRoles">';
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

var addedCandidteId=0;
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
	$('#'+selectedVal+'').val(candidateIdEdit);	
	}
	var name1 = candidteName.trim().toLowerCase();
	var designtion = $("#designationsList option :selected").text();
		
	if(name1.length >0 && name1 != '')
		$("#"+selectedVal+" option:contains("+name1+" ("+designtion.toLowerCase()+")").attr('selected', true);	
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
				}else if(jsObj.task == "getPartyList"){
					buildPartyList(myResults,jsObj);
				}else if(jsObj.task == "getDesignationsList"){
					builddesignationsList(myResults,jsObj);
				}else if(jsObj.task == "saveCandidateForDebate"){
					if(myResults.resultCode == 0){
					 $("#errorMsgDiv").html("<font style='font-weight:bold;color:green;text-transform: capitalize;'> Candidate Created Successfully </font>").fadeOut(3000);
						setTimeout('$("#createCandidateDiv").dialog("close");',2000);	
						$('#locationId').val(0);
						
						getCandidatesOfSelectedParty(jsObj.partyId,jsObj.divId,jsObj.roleOptionsID);			
					}else if(myResults.resultCode == 2){
						$("#errorMsgDiv").html(" Candidate Exits With This Name");
					}
					else {
						$("#errorMsgDiv").html(" Exception occured While saving Debate Candidate.");
					}
				}
				else if(jsObj.task == "smsPole")
				{
					buildSmsPoleDetails(myResults);
				}
				else if(jsObj.task == "party")
				{
					buildPartyDetails(myResults);
				}
				else if(jsObj.task == "candidate")
				{
					buildCandidateDetails(myResults);
				}	
				else if (jsObj.task == "getDebateDetails")
				{
					if(myResults != null)
					prepopulateDebateForm(myResults);	
				}
				else if (jsObj.task == "deleteDebateReport")
				{
					if(myResults != null)
					{
						if(myResults == "Success")
						{
							alert("Deleted Successfully");
						}
						else
						{
							alert("Error Occured While Deleting");
						}
						
					}
					else
					{
						alert("Error Occured While Deleting");
					}
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
	

	
function buildSmsPoleDetails(myResults)
{
	$('#analysisDiv').html('');
	if(myResults != null && myResults.length > 0)
	{
		 var str = '';
		 str += '<table class="table table-bordered " id="smsPoleTable">';
		 str +='<thead>';
		 str += '<tr>';
		 str += '<th>DATE</th>';
		 str += '<th>CHANNEL</th>';
		 str += '<th>QUESTION</th>';
		 str += '<th>YES</th>';
		 str += '<th>NO</th>';
		 str += '</tr>';
		  str +='</thead><tbody>';
		 for(var i in myResults)
		 {
			if(myResults[i].name != "")
			{
				str += '<tr>';
				str += '<td>'+myResults[i].url+'</td>';
				str += '<td>'+myResults[i].partno+'</td>';
				str += '<td>'+myResults[i].name+'</td>';
				for(var j in myResults[i].selectOptionsList)
				{
					str += '<td>'+myResults[i].selectOptionsList[j].perc+'</td>';
				}
				str += '</tr>';
			}
			
		 }
		 str += '</tbody></table>';
		 $('#analysisDiv').html(str);
		 window.open(myResults[0].type);
	}
}

function buildPartyDetails(myResults)
{
	$('#analysisDiv').html('');
	if(myResults != null && myResults.length > 0)
	{
		 var str = '';
		 str += '<table class="table table-bordered " id="partyTable">';
		 str +='<thead>';
		 str += '<tr>';
		 str += '<th>S.NO</th>';
		 str += '<th>PARTY</th>';
		 str += '<th>COUNT/SCALE</th>';
		 str += '</tr>';
		  str +='</thead><tbody>';
		  var count = 1;
		 for(var i in myResults)
		 {
			str += '<tr>';
			str += '<td>'+count+'</td>';
			str += '<td>'+myResults[i].name+'</td>';
			str += '<td>'+myResults[i].constituencyId+'/'+myResults[i].perc+'</td>';
			str += '</tr>';
			count++;
		 }
		 str += '</tbody></table>';
		 $('#analysisDiv').html(str);
		 
		window.open(myResults[0].url);
	}
}

function buildCandidateDetails(myResults)
{
	$('#analysisDiv').html('');
	if(myResults != null && myResults.length > 0)
	{
		var str = '';
		for(var i in myResults)
		{
			str  += '<div class="accordion" id="accordion'+i+'">';
			str  += '<div class="accordion-group">';
			str  += '<div class="accordion-heading">';
			if(i%2 ==  0)
			{
				str  += '<a class="accordion-toggle evenColor" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseOne'+i+'" style="color: rgb(73, 175, 205); font-weight: bold; text-align: start;">';
			}
			else
			{
				str  += '<a class="accordion-toggle oddColor" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseOne'+i+'" style="color: rgb(73, 175, 205); font-weight: bold; text-align: start;">';
			}
			str  += ''+myResults[i].selectOptionsList[0].name+'';
			str  += '</a>';
			str  += '</div>';
			if(i == 0)
			{
				str  += '<div id="collapseOne'+i+'" class="accordion-body collapse in">';
			}
			else
			{
				str  += '<div id="collapseOne'+i+'" class="accordion-body collapse ">';
			}
			str  += '<div class="accordion-inner">';
			str += '<table class="table table-bordered " id="candidateTable">';
			str +='<thead>';
			str += '<tr>';
			str += '<th>S.NO</th>';
			str += '<th>CANDIDATE</th>';
			str += '<th>COUNT/SCALE</th>';
			str += '</tr>';
			str +='</thead><tbody>';
			var count = 1;
			for(var j in myResults[i].selectOptionsList)
			{
				str += '<tr>';
				str += '<td>'+count+'</td>';
				str += '<td>'+myResults[i].selectOptionsList[j].location+'</td>';
				str += '<td>'+myResults[i].selectOptionsList[j].count+'/'+myResults[i].selectOptionsList[j].perc+'</td>';
				str += '</tr>';
				count++;
			}
			str += '</tbody></table>';
			str  += '</div>';
			str  += '</div>';
			str  += '</div>';
			str  += '</div>';
		}
	    $('#analysisDiv').html(str);
		window.open(myResults[0].url);
	}
}
function builddesignationsList(results,jsObj)
{

  $("#"+jsObj.designationList+"").find('option').remove();
  $("#"+jsObj.designationList+"").append('<option value="0">Select Designation</option>');
  
  if(results != null && results.length > 0)
  {	
    for(var i in results){
		$("#"+jsObj.designationList+"").append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
	 }
  }
}
function buildPartyList(results,jsObj)
{

  $("#"+jsObj.partySelectBoxId+"").find('option').remove();
  $("#"+jsObj.partySelectBoxId+"").append('<option value="0">Select Party</option>');
  
  if(jsObj.partiesListForWhome != null)
  {
   $("#"+jsObj.partiesListForWhome+"").find('option').remove();
   $("#"+jsObj.partiesListForWhome+"").append('<option value="0">Select Party</option>');
  }
  
  if(results != null)
  {
   for(var i in results)
    $("#"+jsObj.partySelectBoxId+"").append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
  
   if(jsObj.partiesListForWhome != null)
	for(var i in results)
    $("#"+jsObj.partiesListForWhome+"").append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
  }
}
function getDebateDetailsBetwinDates(fromDate,toDate,channelId,partyId,candidateId)
{

  YAHOO.widget.DataTable.viewReport = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var debateId = oRecord.getData("id");
		var subjectName = oRecord.getData("name");

		str +='<input type="button" class="btn btn-info" value="Generate URL " onCLick="generateURL('+debateId+',\'reportId'+debateId+'\',\''+subjectName+'\')"/>';
		elLiner.innerHTML=str;
					
	};
	
	YAHOO.widget.DataTable.generatePDF = function(elLiner, oRecord, oColumn, oData) 
	{
	   var str='';
		var name = oData;
		var debateId = oRecord.getData("id");

		str +='<a class="btn btn-info" value="'+debateId+'"';
		str +='onClick="openDebateReport('+debateId+')">view</a>';
		elLiner.innerHTML=str;
					
	};
	YAHOO.widget.DataTable.editReport = function(elLiner, oRecord, oColumn, oData) 
	{
	   var str='';
		var name = oData;
		var debateId = oRecord.getData("id");

		str +='<a class="btn btn-info" value="'+debateId+'"';
		str +='onClick="editDebateReport('+debateId+')">Edit</a>';
		elLiner.innerHTML=str;
					
	};
	
	YAHOO.widget.DataTable.deleteReport = function(elLiner, oRecord, oColumn, oData) 
	{
	   var str='';
		var name = oData;
		var debateId = oRecord.getData("id");

		str +='<a class="btn btn-info" value="'+debateId+'"';
		str +='onClick="deleteDebateReport('+debateId+')">Delete</a>';
		elLiner.innerHTML=str;
					
	};
	YAHOO.widget.DataTable.textArea = function(elLiner, oRecord, oColumn, oData) 
	{
	   var str='';
		var name = oData;
		var debateId = oRecord.getData("id");
		str +='<textarea id="reportId'+debateId+'" placeholder="Generated URL Details..."></textarea>';
		elLiner.innerHTML=str;
					
	};
	
  var newsReportColumns = [
   {key:"name",label:"SUBJECT",width:400},
   {key:"type",label:"CREATED DATE"},
   {key:"viewReport",label:"VIEW REPORT",formatter:YAHOO.widget.DataTable.generatePDF},
   {key:"editReport",label:"EDIT",formatter:YAHOO.widget.DataTable.editReport},
   {key:"deleteReport",label:"DELETE",formatter:YAHOO.widget.DataTable.deleteReport},
   {key:"generatePDF",label:"GENERATE URL ",formatter:YAHOO.widget.DataTable.viewReport},
   {key:"textArea",label:"URL",formatter:YAHOO.widget.DataTable.textArea}

   
  ];
  
   var newsDataSource = new YAHOO.util.DataSource("debateDetaildBtDatesAction.action?fromDate="+fromDate+"&toDate="+toDate+"&channel="+channelId+"&partyId="+partyId+"&candidateId="+candidateId+"&");
  newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
  newsDataSource.responseSchema = {
  resultsList: "smsPoleList",
   fields: [{key:"id" ,parser:"number"},"name","type"],
    metaFields: {
    totalRecords: "totalCount" // Access to value in the server response
     },
  };  

  var myConfigs = {
initialRequest: "sort=name&dir=desc&startIndex=0&results=15", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"name", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10
			    })  // Enables pagination
};

var newsDataTable = new YAHOO.widget.DataTable("dateWiseReportDiv",
newsReportColumns, newsDataSource, myConfigs);

newsDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;

}
}
function getDebateAnalysisDetails(task)
{
	var startDate = $('#fromDateIdForAnalysis').val();
	var endDate = $('#toDateIdForAnalysis').val();
	$("#RerrDivForAnalysis").html('');
	if(startDate != undefined && startDate.length <=0){
		$("#RerrDivForAnalysis").html("From Date is Required.");
		return;
	}
	if(endDate != undefined && endDate.length <=0){	
		$("#RerrDivForAnalysis").html("To Date is Required.");
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
		$("#RerrDivForAnalysis").html('From Date should not greater then To Date ');	
		return;
	}
	 if(frommonth>tomonth){
		   if(fromyear == toyear){
			$("#RerrDivForAnalysis").html('From Date should not greater then To Date ');		
		return;
		}		
	}
	
	if(fromDat>toDat){	
		if(frommonth == tomonth && fromyear == toyear){
			$("#RerrDivForAnalysis").html('From Date should not greater then End Date ');		
			return;				
		   }
	}
	
	$("#RerrDivForAnalysis").html('');
	
	var jsObj = {
				fromDate : $('#fromDateIdForAnalysis').val(),
				toDate   : $('#toDateIdForAnalysis').val(),
				task     : task	
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getDebateAnalysisDetailsAction.action?"+rparam;
		callAjax(jsObj,url);
	
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
	var partyId = $('#partySelecction').val();
	if(partyId == undefined)
	{
		partyId = "0";
	}
	var candidateId = $('#candidateSelecction').val();
	if(candidateId == undefined)
	{
		candidateId = "0";
	}
	var channelId = $('#channelSelecction').val();
	if(channelId == undefined)
	{
		channelId = "0";
	}
	getDebateDetailsBetwinDates($('#fromDateId').val(),$('#toDateId').val(),channelId,partyId,candidateId);
}

function openDebateReport(debateId)
{
	window.open("debateReportAction.action?debateId="+debateId+"");
}


function editDebateReport(debateId)
{
	var fromDate = $('#fromDateId').val();
	var toDate = $('#toDateId').val();
	var channel = $('#channelSelecction').val();
	var partyId = $('#partySelecction').val();
	var candidateId = $('#candidateSelecction').val();
	
	window.location="debateEditAction.action?debateId="+debateId+"&fromDate="+fromDate+"&toDate="+toDate+"&channel="+channel+"&partyId="+partyId+"&candidateId="+candidateId+"&";
}

function deleteDebateReport(debateId)
{

	var isConfirm = confirm("Are you want to delete ?")
	if( isConfirm == true )
	{
		var jsObj = {
				debateId : debateId,
				task     : "deleteDebateReport"	
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "deleteSelectedDEbateAction.action?"+rparam;
		callAjax(jsObj,url);
    }
	else
	{
	  return false;
	}
	
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

function isNumber(type,id){
var smsQuestin =  $("#smsques1").val();
	if(smsQuestin.trim().length > 0){
	if(type == "percentage"){			
				var smsOptionPerc =$( "#"+id+"").val();
				if(isNaN(smsOptionPerc)){					
					$("#"+id+"").css("border","1px solid #D14719");
				}else{
					$("#"+id+"").css("border","1px solid #CCCCCC");
				}	
	}else{
		 var prsentation = $( "#"+id+"").val();
			if(isNaN(prsentation) || prsentation >5){
				$("#"+id+"").css("border","1px solid #D14719");	
			}
			else{
				$("#"+id+"").css("border","1px solid #CCCCCC");
			}
	}		
	}
	
}

	
function getTypeOfConstituency(value)
{	

	if(value == 1)
	{  
		$('#pcConstituencyRow').hide();
		$('#acConstituencyRow').show();
	}
	else if(value == 2)
	{
		$('#pcConstituencyRow').show();
		$('#acConstituencyRow').hide();
	}
	else
	{
		$('#pcConstituencyRow').hide();
		$('#acConstituencyRow').hide();
	}
}

function isValid(str){
 var iChars = "#$%&";
 var flag = false;
	for (var i = 0; i < str.length; i++) {
		if (iChars.indexOf(str.charAt(i)) != -1) {			
			flag = true;
		}
    }
	return flag;
}

function getPartiesList(partySelectBoxId,partiesListForWhome)
{
 var jsObj={
		partySelectBoxId:partySelectBoxId,
		partiesListForWhome:partiesListForWhome,
		task:'getPartyList'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getPartiesListAction.action?"+rparam;
	callAjax(jsObj, url);
}
function getDesignationList(designationList1)
{
  var jsObj={
		designationList:designationList1,
		task:'getDesignationsList'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getDesignationsListAction.action?"+rparam;
	callAjax(jsObj, url);
}

var totalPerc;
function updatePercntage(id){

	totalPerc = 100
	var perc=parseFloat(0);
	totalPerc = parseFloat(totalPerc);
	$( ".smsOptinPerc " ).each(function( index ) {	
			var perc1 = parseFloat($(this).val());
			if(!isNaN(perc1)){
					perc = perc + perc1;
				if(totalPerc <0)
					perc = perc - perc1;
			}		
	});
	if(perc > 100 || perc < 0){
				$("#"+id+"").val('')
	}
	totalPerc = totalPerc -perc;
	if(totalPerc >= 0){
		$('#percCount').html('');
		$('#percCount').html(totalPerc);
	}
}


	
function createNewCandidate(listId,party,id){
	$('#'+party+'').css("border","1px solid #CCCCCC");
	$('#presentParty').html('');
	$('#errorMsgDiv').html('');

	var partyName = $('#'+party+' :selected').text();
	var partyId = $('#'+party+'').val();
	debateNewCandiPartyId = partyId;
	partyDiv = party;
	roleOptionsID = id;
	if(partyId <= 0){
		$('#'+party+'').css("border","1px solid #D14719");
		return;
	}

	$('#presentParty').html(''+partyName+'');
	$("#createCandidateDiv").dialog({
		modal: true,
		title: "<b>Create New Candidate</b>",
		width: 500,
		height: 220       
	});   
	
   //getPartiesList("partySelectNewList",null);
   //getDesignationList("designationsList");   

}
	
	
function showNewDebateDiv(){
	$('#newDibateDiv').show();
	$('#debateReportDiv').hide();
	$('#debateAnalysisDiv').hide();
	$('#newDebateAnalysisDiv').hide();
}
function showDebateReportDiv(){
	$('#newDibateDiv').hide();
	$('#dateWiseReportDiv').html('');
	$('#newDebateAnalysisDiv').hide();
	$('#fromDateId').val('');
	$('#toDateId').val('');
	$('#debateReportDiv').show();
	$('#debateAnalysisDiv').hide();
}

function showDebateAnalysisDiv(){
	$('#newDibateDiv').hide();
	$('#debateAnalysisDiv').show();
	$('#debateReportDiv').hide();
	$('#newDebateAnalysisDiv').hide();
	$('#fromDateIdForAnalysis').val('');
	$('#toDateIdForAnalysis').val('');
}

function showNewDebateAnalysisDiv()
{
	$('#newDibateDiv').hide();
	$('#debateAnalysisDiv').hide();
	$('#debateReportDiv').hide();
	$('#newDebateAnalysisDiv').show();
}
function buildDebateAnalysisDiv()
{
	var str = '';
	str += '<div class="span3">';
	str += '<label >Party : </label>';
	str += '<select name="partyAnalysis"  id="partyAnalysis" onChange="getCandidatesList(this.value)">';
	for(var i in partiesArray)
	{
		str += '<option value='+partiesArray[i].id+'>'+partiesArray[i].name+'</option>';
	}
	str += '</select>';
	str += '</div>';
	str += '<div class="span3">';
	str += '<label >Candidate : </label>';
	str += '<select id="candidateiseAnalysis">';
	str += '<option value="0">Select Candidate</option>';
	str += '</select>';
	str += '</div>';
	str += '<div class="span3">';
	str += '<label >Debate Roles : </label>';
	str += '<select name="roleWiseAnalysis"  id="roleWiseAnalysis" >';
	str += '<option value="0">Select Roles </option>';
	for(var i in rolesArray)
	{
		str += '<option value='+rolesArray[i].id+'>'+rolesArray[i].name+'</option>';
	}
	str += '</select>';
	str += '</div>';
	str += '<div class="span3">';
	str += '<label >Chars : </label>';
	str += '<select name="charsWiseAnalysis"  id="charsWiseAnalysis">';
	str += '<option value="0">Select Chars</option>';
	for(var i in charsArray)
	{
		str += '<option value='+charsArray[i].id+'>'+charsArray[i].name+'</option>';
	}
	str += '</select>';
	str += '</div>';
	str += '<div align="center"><input type="button" value="Submit" class="btn btn-success" onClick="getAnalysedData();"></input></div>';
	$('#debateAnalysisDiv').html(str);
}

function getAnalysedData()
{
	var partyId = $('#partyAnalysis').val();
	var candidateId = $('#candidateiseAnalysis').val();
	var roleId = $('#roleWiseAnalysis').val();
	var charsId = $('#charsWiseAnalysis').val();
	var type = "";
	if(charsId > 0)
	{
		type = "chars";
	}
	else if(roleId > 0)
	{
		type = "role";
	}
	else if(candidateId > 0)
	{
		type = "candidate";
	}
	else
	{
		type = "party";
	}
}
function getCandidatesList(partyId)
{
	var jsObj = {
			partyId :partyId,
			task : "getCandidatesLIstOfAParty"	
	};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesListForDebateAction.action?"+rparam;
	callAjax(jsObj,url);
}
function getCandidatesForSelectedParty(partyId)
{
	var jsObj = {
			partyId :partyId,
			selectedVal :"candidateSelecction",
			task : "getCandidatesOfAParty"	
	};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesListForDebateAction.action?"+rparam;
	callAjax(jsObj,url);
}

function getRespectiveSelection()
{
		var str = '';
		str += '<div class="span4" > ';
		str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Party  </label>'; 
		str +='<select id="partySelecction" onChange="getCandidatesForSelectedParty(this.value)">';
		for(var i in partiesArray)
		{
			str +='<option value="'+partiesArray[i].id+'">'+partiesArray[i].name+'</option>';
		}
		str +='</select>';
		str += '</div>';
		str += '<div class="span4" > ';
		str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Candidate  </label>'; 
		str +='<select id="candidateSelecction"><option value="0">Select Candidate</option></select>';
		str += '</div>';
		$('#reportTypeSelectionDiv').html(str);
}

getEachTopicWisePartyAndCandidateDetails();
function getEachTopicWisePartyAndCandidateDetails()
{
	var jsObj={
			task:"getEachTopicWisePartyAndCandidateDetails"
	}
	$.ajax({
          type:'GET',
          url: 'getPartyWiseTotalDebatesAndScalesService.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
			buildEachTopicWisePartyAndCandidateDetails(result);
	});
}

function buildEachTopicWisePartyAndCandidateDetails(result)
{
	var str = '';
	str += '<table class="table table-bordered table-hover table-striped" id="thirdReport">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>Topic</th>';
	var partiesList = result[0].subList;
	for(var i in partiesList)
	{
		str += '<th colspan=2>'+partiesList[i].party+'</th>';
	}
	str += '</tr>';
	str += '<tr>';
	str += '<th></th>';
	for(var i in partiesList)
	{
		str += '<th>Candidate</th>';
		str += '<th>Performance Count</th>';
	}
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td>'+result[i].subject+'</td>';
		var details = result[i].subList;
		for(var j in details)
		{
			str += '<td>'+details[j].candidate+'</td>';
			if(details[j].countScale  != null)
			str += '<td>'+details[j].countScale+'</td>';
			else
			str += '<td>-</td>';
		}
		str += '</tr>';
	}
	str += '</tbody>';
	str += '</table>';
	$('#partyCandidatePerformanceDiv').html(str);
	//$('#secondReport').dataTable();
}
function getCandidateCharacteristicsDetails(){
	var jsObj={
			task:"candidateDetails"
	}
	$.ajax({
          type:'GET',
          url: 'getCandidateCharacteristicsDetailsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
			buildCandidateCharacteristics(result);
	});
}
getCandidateCharacteristicsDetails();
function buildCandidateCharacteristics(myResults){
	var result = myResults.debateSubjectList;
	$('#sampleId').html('');
	if(result != null && result.length > 0){
		 var str = '';
		 str += '<table class="table table-bordered table-hover table-striped " id="fourthReport">';
		str += '<thead class="alert alert-success">';
		 str += '<tr>';
		 str += '<th>Topic</th>';
		 for(var i in result[0].partyList){
			str += '<th colspan="5" align="center">'+result[0].partyList[i].partyName+'</th>';
		 }
		  str += '</tr>';
		  str += '<tr>';
		   str += '<th></th>';
			
			for(var j in result[0].partyList){
					str += '<th> Candidate Name</th>';
						for(var l in result[0].partyList[0].characList){
						
							str += '<th  class="alert alert-success">'+result[0].partyList[0].characList[l].characteristics+'</th>';
					
					
				}
			
			}
		
		str += '</tr>';
		str += '</thead>';
		str += '<tbody>';
		 for(var i in result){
			str += '<tr>';		
			str += '<td>'+result[i].debateSubject+'</td>';
			if(result[i].partyList.length > 0){
				for(var j in result[i].partyList){
					if(result[i].partyList[j].candidatesList.length > 0 ){
						for(var k in result[i].partyList[j].candidatesList){
							str += '<td>'+result[i].partyList[j].candidatesList[k].candidateName+'</td>';
						}
					}
					else{	
					    str += '<td>-</td>';
					}	
					if(result[i].partyList[j].characList.length > 0){
						for(var l in result[i].partyList[j].characList){
							str += '<td>'+result[i].partyList[j].characList[l].scale+'</td>';
						}
					}
					else{
						 str += '<td>-</td>';
						  str += '<td>-</td>';
						   str += '<td>-</td>';
						    str += '<td>-</td>';
							 
					}
				}			
			}
			else{
			str += '<td>-</td>';
			str += '<td>-</td>';
			str += '<td>-</td>';
			str += '<td>-</td>';
			str += '<td>-</td>';
			
			}
			str += '</tr>';
		 }
		 str += '</tbody>';
		 str += '</table>';
		 $('#candidatePartyPerformanceId').html(str);
		//$('#fourthReport').dataTable();
	}
}
partyWiseCandidatePerformance();
function partyWiseCandidatePerformance()
{
	var jsObj={
			task:"partyWiseCandidatePerformance"
	}
	$.ajax({
          type:'GET',
          url: 'partyWiseCandidatePerformance.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
			buildpartyWiseCandidatePerformance(result);
	});
}
function buildpartyWiseCandidatePerformance(result)
{
	var str = '';
	str += '<table class="table table-bordered table-hover table-striped" id="secondReport">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>Party</th>';
	str += '<th>Candidate</th>';
	str += '<th>Total Debates</th>';
	str += '<th>performance Count</th>';
	str += '<th>Subject</th>';
	str += '<th>Presentation</th>	';
	str += '<th>Counter Attack</th>';
	str += '<th>Body Language</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td>'+result[i].partyName+'</td>';
		str += '<td>'+result[i].candidate+'</td>';
		str += '<td>'+result[i].totalDebates+'</td>';
		str += '<td>'+result[i].performanceCount+'</td>';
		str += '<td>'+result[i].subject+'</td>';
		str += '<td>'+result[i].presentation+'</td>';
		str += '<td>'+result[i].counterAttack+'</td>';
		str += '<td>'+result[i].bodyLanguage+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '</table>';
	$('#eachAttributePartyCandidateId').html(str);
	//$('#thirdReport').dataTable();
}

getPartyWiseOverAllSummery();
function getPartyWiseOverAllSummery()
{
	var jsObj={
			task:"getPartyWiseOverAllSummery"
	}
	$.ajax({
          type:'GET',
          url: 'getPartyWiseOverAllPerformance.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
			buildPartyWiseOverAllSummery(result);
	});
}

function buildPartyWiseOverAllSummery(result)
{
var str = '';
	str += '<table class="table table-bordered table-hover table-striped" id="firstReport">';
	str += '<thead class="alert alert-success">';
	str += '<tr>';
	str += '<th>Party</th>';
	str += '<th>Total Debates</th>';
	str += '<th colspan=4>Ranking</th>';
	str += '<th>Subject</th>';
	str += '<th>Presentation</th>	';
	str += '<th>Counter Attack</th>';
	str += '<th>Body Language</th>';						
	str += '</tr>';
	
	str += '<tr>';
	str += '<th></th>';
	str += '<th></th>';
	str += '<th>1 st</th>';
	str += '<th>2 nd</th>';
	str += '<th>3 rd</th>';
	str += '<th>4 th</th>';
	str += '<th></th>';
	str += '<th></th>	';
	str += '<th></th>';
	str += '<th></th>';						
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td>'+result[i].partyName+'</td>';
		str += '<td>'+result[i].totalDebates+'</td>';
		str += '<td>'+result[i].rankingVO.firstRank+'</td>';
		str += '<td>'+result[i].rankingVO.secondRank+'</td>';
		str += '<td>'+result[i].rankingVO.thirdRank+'</td>';
		str += '<td>'+result[i].rankingVO.fourthRank+'</td>';
		for(var j in result[i].subList)
		{
			str += '<td>'+result[i].subList[j].debateScale+'</td>';
		}
		str += '</tr>';
	}
	str += '</tbody>';
	str += '</table>';	
	$('#partyOverallSummery').html(str);
	//$('#firstReport').dataTable();
}
getStrongAndWeakTopicsByPartyWise();
function getStrongAndWeakTopicsByPartyWise()
{
	var jsObj={
			task:"getStrongAndWeakTopicsByPartyWise"
	}
	$.ajax({
          type:'GET',
          url: 'getPartyWiseStrongAndWeakTopicAndCandidates.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
			buildStrongAndWeakTopicsByPartyWise(result);
	});
}
function buildStrongAndWeakTopicsByPartyWise(result)
{
	var str = '';
	for(var i in result)
	{
		str += '<div class="row">';
		str += '<div class="span6">';
		str += '<h4>'+result[i].party+' Strong</h4>';
		str += '<table class="table table-bordered table-hover table-striped" >';
		str += '<thead class="alert alert-success">';
		str += '<tr>';
		str += '<th>Topic</th>';
		str += '<th>Channel</th>';
		str += '<th>Candidate</th>';
		str += '</tr>';
		str += '</thead>';
		str += '<tbody>';
		if(result[i].top != null && result[i].top.length > 0)
		{
			for(var j in result[i].top)
			{
				str += '<tr>';
				if(result[i].top[j].count > 1)
				{
					str += '<td>'+result[i].top[j].subject+'('+result[i].top[j].count+')</td>';
				}
				else
				{
					str += '<td>'+result[i].top[j].subject+'</td>';
				}
				
				str += '<td>'+result[i].top[j].channel+'</td>';
				str += '<td>'+result[i].top[j].candidate+'</td>';
				str += '</tr>';
			}
		}
		str += '</tbody>';
		str += '</table>';
		str += '</div>';
		
		str += '<div class="span6">';
		str += '<h4>'+result[i].party+' Weak</h4>';
		str += '<table class="table table-bordered table-hover table-striped" >';
		str += '<thead class="alert alert-success">';
		str += '<tr>';
		str += '<th>Topic</th>';
		str += '<th>Channel</th>';
		str += '<th>Candidate</th>';
		str += '</tr>';
		str += '</thead>';
		str += '<tbody>';
		if(result[i].weak != null && result[i].weak.length > 0)
		{
			for(var j in result[i].weak)
			{
				str += '<tr>';
				if(result[i].weak[j].count > 1)
				{
					str += '<td>'+result[i].weak[j].subject+'('+result[i].weak[j].count+')</td>';
				}
				else
				{
					str += '<td>'+result[i].weak[j].subject+'</td>';
				}
				str += '<td>'+result[i].weak[j].channel+'</td>';
				str += '<td>'+result[i].weak[j].candidate+'</td>';
				str += '</tr>';
			}
		}
		str += '</tbody>';
		str += '</table>';
		str += '</div>';
		str += '</div>';
	}
	
	$('#topicwiseStrongAndWeak').html(str);
	
	
	
	
}