getMultiLocationWiseMeetingGroupsData();
function getMultiLocationWiseMeetingGroupsData(){
	$("#MultiLocationWiseMeetingGroupsData").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	 var dates=$('.searchDateCls ').val();

		var jObj = {
			fromDateStr : '01/02/2000'  ,//customStartDateMeetings,
			toDateStr : '01/02/2020',//customEndDateMeetings,
			activityMemberId : 44,
			stateId : globalStateId,
			partyMeetingMainTypeId:4
		};
		 
		$.ajax({
          type:'GET',
          url: 'getMultiLocationWiseMeetingGroupsDataAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildMultiLocationWiseMeetingGroupsData(result);
		});
}
function buildMultiLocationWiseMeetingGroupsData(result)
{
	var str='';
	var levelId=0;
	var meetingGrpId =0;
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			for(var i in result.userAccessLevelList)
			{
				if(i==0)
					meetingGrpId = result.userAccessLevelList[i].levelId;
				str+='<h4 class="panel-title text-capital ">'+result.userAccessLevelList[i].name+'</h4>';
				str+='<table class="table table-bordered bg_ED">';
				str+='<tr>';
					
					for(var j in result.userAccessLevelList[i].userAccessLevelValuesList)
					{
						if(result.userAccessLevelList[i].userAccessLevelValuesList != null && result.userAccessLevelList[i].userAccessLevelValuesList.length>0)
						{
							if(j==0)
							{
								levelId = result.userAccessLevelList[i].userAccessLevelValuesList[j].levelId; 
							    locationName =result.userAccessLevelList[i].name;
								str+='<td style="position:relative;" class="bg_E0"> ';
									str+='<p class="text-muted text-capital">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].name+'<span class="meetingsHeadingExpandIcon multicLocationMeetingCls" attr_levelId="'+levelId+'" attr_group_id="'+meetingGrpId+'"  attr_sessionId="0" style="margin-left:5px;font-size:12px;"><i class="glyphicon glyphicon-fullscreen"></i></span></p>';
									str+='<span class="multiLocationWiseMeetingCount" attr_count="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'" attr_group_id="'+result.userAccessLevelList[i].levelId+'" attr_levelId="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].levelId+'" attr_locationName ="'+result.userAccessLevelList[i].name+'">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'</span>';
								str+='</td>';	
							}else{
								str+='<td style="position:relative;">';
									str+='<p class="text-muted text-capital">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].name+'<span class="meetingsHeadingExpandIcon multicLocationMeetingCls"  attr_levelId="'+levelId+'" attr_group_id="'+meetingGrpId+'"  attr_sessionId="0"  style="margin-left:5px;display:none;font-size:12px;"><i class="glyphicon glyphicon-fullscreen"></i></span></p>';
									str+='<span class="multiLocationWiseMeetingCount" attr_count="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'" attr_group_id="'+result.userAccessLevelList[i].levelId+'" attr_levelId="'+result.userAccessLevelList[i].userAccessLevelValuesList[j].levelId+'" attr_locationName ="'+result.userAccessLevelList[i].name+'">'+result.userAccessLevelList[i].userAccessLevelValuesList[j].count+'</span>';
								str+='</td>';	
							}
								
													
						}
					}
						str+='</tr>';	
				str+='</table>';
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						str+='<div class="bg_E0 pad_10">';
							str+='<div id="partyLevelIdWiseMeetingsCount"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
		str+='</div>';
	str+='</div>';
	$("#MultiLocationWiseMeetingGroupsData").html(str);
	//getPartyLevelIdWiseMeetingsCount(meetingGrpId,levelId,result.userAccessLevelList[i].userAccessLevelValuesList[j].count,locationName);
}
$(document).on("click",".multiLocationWiseMeetingCount",function(){
	$(".multiLocationWiseMeetingCount").removeClass("active").parent("td").removeClass("bg_E0");
	$(".multiLocationWiseMeetingCount").closest("td").find(".meetingsHeadingExpandIcon").hide();
	$(this).parent("td").addClass("bg_E0");
	$(this).addClass("active");
	$(this).closest("td").find(".meetingsHeadingExpandIcon").show();
	var count = $(this).attr("attr_count");
	var attr_group_id = $(this).attr("attr_group_id");
	var attr_levelId = $(this).attr("attr_levelId");
    var attr_location_name=$(this).attr("attr_locationName");
	getPartyLevelIdWiseMeetingsCount(attr_group_id,attr_levelId,count,attr_location_name);
});

function getPartyLevelIdWiseMeetingsCount(meetingGrpId,levelId,count,locationName)
{

	$("#partyLevelIdWiseMeetingsCount").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');

	var jObj = {
		fromDateStr : '01/02/2000'  ,//customStartDateMeetings,
		toDateStr : '01/02/2020',//customEndDateMeetings,
		activityMemberId : globalActivityMemberId,
		stateId : globalStateId,
		partyMeetingMainTypeId:4,
		partyMeetingLevelId:levelId,
		meetingGrpId:meetingGrpId
	};
	
	$.ajax({
	  type:'GET',
	  url: 'getPartyLevelIdWiseMeetingsCountAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildPartyLevelIdWiseMeetingsCount(result,count,levelId,locationName,meetingGrpId);
	});
}

function buildPartyLevelIdWiseMeetingsCount(result,count,levelId,locationName,meetingGrpId)
{
	var str='';
	str+='<table class="table bg_E0">';
		str+='<tr>';
			str+='<td>';
				str+='<p class="text-muted">Planned</p>';
				str+='<p><a class="locWisedetails" attr_meetingGrpId="'+meetingGrpId+'" attr_level_id="'+levelId+'" attr_type="All" style="cursor:pointer;">'+count+'</a></p>';	
			str+='</td>';
			str+='<td class="bg_ED">';
				str+='<p class="text-muted">Conducted</p>';
				if(result.conducted != null && result.conducted > 0)
				{
					str+='<p><a class="locWisedetails" attr_meetingGrpId="'+meetingGrpId+'" attr_level_id="'+levelId+'" attr_type="conducted" style="cursor:pointer;">'+result.conducted+'</a></p>';
				}else{
					str+='<p> - </p>';
				}
			str+='</td>';
			str+='<td>';
				str+='<p class="text-muted">Not Conducted</p>';
				str+='<p><a class="locWisedetails" attr_meetingGrpId="'+meetingGrpId+'" attr_level_id="'+levelId+'" attr_type="Not-Conducted" style="cursor:pointer;">'+((count)-(result.conducted))+'  </a><small></small></p>';	
			str+='</td>';
			str+='<td>';
				str+='<p class="text-muted">Image Covered</p>';
				if(result.imagesCovered != null && result.imagesCovered>0)
					str+='<p>'+result.imagesCovered+'</p>';	
				else
					str+='<p> - </p>';	
			str+='</td>';
			str+='<td>';
				str+='<p class="text-muted">Images</p>';
				if(result.totalImages != null && result.totalImages>0)
					str+='<p><a class="getModalImagesCls" attr_Meeting_level_id="'+levelId+'"  attr_location ="'+locationName+'" attr_location_value="0" attr_count="0" style="cursor:pointer;">'+result.totalImages+'</a></p>';	
				else
					str+='<p> - </p>';					
			str+='</td>';
		str+='</tr>';
		str+='<tr>';
			str+='<td class="bg_ED">';
			if(result.invited != null && result.invited > 0)
			{
				str+='<p> <a style="font-weight:bold;" href="javascript:{getAttendedCadreDetails(\'invited\',0,'+levelId+','+meetingGrpId+',0,4,0);}"> '+result.invited+'</a></p>';
			}
			else
				str+='<p> - </p>';
				str+='<p class="text-muted">Invited</p>';
			str+='</td>';
			str+='<td class="bg_ED">';
			if(result.attended != null && result.attended > 0)
			{
				str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'attended\',0,'+levelId+','+meetingGrpId+',0,4,0);}"> '+result.attended+' </a></p>';
			}
			else
				str+='<p> - </p>';
				str+='<p class="text-success">Attended</p>';
			str+='</td>';
			
			str+='<td class="bg_ED">';
			if(result.late != null && result.late > 0)
			{
					str+='<p>  <a   class="text-danger" style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'late\',0,'+levelId+','+meetingGrpId+');}"> '+result.late+' </a></p>';
			}else
				str+='<p> - </p>';
				str+='<p class="text-danger">Late</p>';
			str+='</td>';
			str+='<td class="bg_ED">';
			if(result.absent != null && result.absent > 0)
			{				
				str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'absent\',0,'+levelId+','+meetingGrpId+',0,4,0);}"> '+result.absent+' </a></p>';
			}else
				str+='<p> - </p>';
				str+='<p class="text-muted">Absent</p>';
			str+='</td>';
			str+='<td class="bg_ED">';
			if(result.nonInvitee != null && result.nonInvitee > 0)
			{				
				str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'noninvitees\',0,'+levelId+','+meetingGrpId+',0,4,0);}"> '+result.nonInvitee+' </a></p>';				
			}
			else
				str+='<p> - </p>';
			str+='<p class="text-muted">Non-Invitee</p>';
				str+='</td>';
				
		str+='</tr>';
		
		if(result.subList != null && result.subList.length>0){
			for(var k in result.subList){
				str+='<tr>';
					
						str+='<td class="bg_ED">';
							str+='<p>'+result.subList[k].name+'</p>';
						str+='</td>';
					
					if(result.subList[k].inviteeAttendedCount != null && result.subList[k].inviteeAttendedCount > 0)
					{
						str+='<td class="bg_ED">';
								str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'attended\','+result.subList[k].id+','+levelId+','+meetingGrpId+',0,4,0);}"> '+result.subList[k].inviteeAttendedCount+'</p>';
								//str+='<p class="text-success">Attended</p>';
						str+='</td>';
					}
					else{
						str+='<td class="bg_ED">';
								str+='<p> - </p>';								
						str+='</td>';
					}
					if(result.subList[k].lateCount != null && result.subList[k].lateCount > 0)
					{
						str+='<td class="bg_ED">';
							str+='<p >  <a  class="text-danger" style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'late\','+result.subList[k].id+','+levelId+','+meetingGrpId+',0,4,0);}"> '+result.subList[k].lateCount+' </a></p>';
							//str+='<p class="text-danger">Late</p>';
						str+='</td>';
					}else{
						str+='<td class="bg_ED">';
								str+='<p> - </p>';								
						str+='</td>';
					}
					var absent =result.subList[k].absentCount;
					
					if(absent != null && absent > 0)
					{
						str+='<td class="bg_ED">';
							str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'absent\','+result.subList[k].id+','+levelId+','+meetingGrpId+',0,4,0);}"> '+absent+'  </a> </p>';
							//str+='<p class="text-muted">Absent</p>';
						str+='</td>';
					}else{
						str+='<td class="bg_ED">';
								str+='<p>  -  </p>';								
						str+='</td>';
					}
					
					if(result.subList[k].nonInviteeCount != null && result.subList[k].nonInviteeCount > 0)
					{
						str+='<td class="bg_ED">';
							str+='<p>  <a  style="font-weight:bold;"  href="javascript:{getAttendedCadreDetails(\'nonInvitees\','+result.subList[k].id+','+levelId+','+meetingGrpId+',0,4,0);}"> '+result.subList[k].nonInviteeCount+' </a></p>';
							//str+='<p class="text-muted">Non-Invitee</p>';
						str+='</td>';
					}else{
						str+='<td class="bg_ED">';
								str+='<p> - </p>';								
						str+='</td>';
					}
						
				str+='</tr>';
			}
		}
	str+='</table>';
	$("#partyLevelIdWiseMeetingsCount").html(str);
}
/*
function getAttendedCadreDetails(cadreType,sessionId,levelId,meetingGrpId,locationId){
	$("#meetingMemDetailsId").modal("show");     
	$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
	var jObj = {
		fromDateStr : '01/02/2000'  ,//customStartDateMeetings,
		toDateStr : '01/02/2020',//customEndDateMeetings,
		activityMemberId : globalActivityMemberId,
		stateId : globalStateId,
		partyMeetingMainTypeId:4,
		partyMeetingLevelId:levelId,
		meetingGrpId:meetingGrpId,
		sessionTypeId:sessionId,
		cadreType:cadreType,
		partyMeetingId:0,
		locationId:locationId
	};
	
	$.ajax({
	  type:'GET',
	  url: 'getPartyLevelIdWiseMeetingsAttendanceDetails.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildPartyLevelIdWiseMeetingsAttendanceDetails(result);
	});
	
}
*/

$(document).on("click",".requiredClass",function(){
	var search = $(this).attr("attr_search");
	var designation = $(this).attr("attr_desg_name");
	buildPartyLevelIdWiseMeetingsAttendanceDetails(globalMembersResult,"overview",search,designation);
});

function buildPartyLevelIdWiseMeetingsAttendanceDetails(result,position,searchReq,designationName){
	
	var str='';
	//var position = "overview";
	if(position == "overview"){
			if(result.subList1[0].publicRepDesgList != null && result.subList1[0].publicRepDesgList.length > 0 ){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading">';
					str+='<h3 class="panel-title">DESIGNATIONS SUMMARY</h3>';
				  str+='</div>';
				  str+='<div class="panel-body">';
						str+='<p style="font-size:15px;" class="m_top10">';
							if(result.subList1[0].publicRepDesgList != null && result.subList1[0].publicRepDesgList.length > 0){
								 for(var i in result.subList1[0].publicRepDesgList){
									str+='<span style="text-transform:uppercase;">'+result.subList1[0].publicRepDesgList[i].name+'</span> ';
									str+='( <span class ="requiredClass" attr_search="required" attr_desg_name="'+result.subList1[0].publicRepDesgList[i].name+'" style="font-weight:bold;color:green;cursor:pointer;">'+result.subList1[0].publicRepDesgList[i].count+'</span> )';
									if( i!= result.subList1[0].publicRepDesgList.length -1 ){
										str+=' , ';
									}
								 }
							}
						str+='</p>';
				  str+='</div>';
				str+='</div>';
				str+='</div>';
	      }
		}
		
		
	str+='<div class="row m_top10">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		  str+='<div class="table-responsive">';
		  str+='<table class="table border_top_apply table-condensed table-bordered" id="cmtMemberDtlsTableId11">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>District Name</th>';
					str+='<th>Leader Name</th>';
					str+='<th>Designation</th>';
					str+='<th>Contact Number</th>';
					str+='<th>Invitation Status</th>';  
					str+='<th>All Sessions</th>';
					if(result.subList1 != null && result.subList1.length > 0 && result.subList1[0].sessionsList != null && result.subList1[0].sessionsList.length>0 ){
						for(var k in result.subList1[0].sessionsList){      
							str+='<th>'+result.subList1[0].sessionsList[k].name+'</th>';
						}  
					}else{
						str+='<th>Attendance</th>';    
					}
				str+='<th> Remarks </th>';    
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			
			for(var i in result.subList1){
				var build = false;
				if(searchReq == "required"){
					if(result.subList1[i].subList != null && result.subList1[i].subList.length > 0){
						for( var j in result.subList1[i].subList){
							if(result.subList1[i].subList[j].trim().toUpperCase() == designationName.trim().toUpperCase()){
								build = true;
								break;
							}
						}
					}
				}else{
					build = true;
				}
				if(build){
					var designations ="";
					if(result.subList1[i].subList != null){
						for(var n in result.subList1[i].subList){
							if(n == 0)
								designations = result.subList1[i].subList[n];
							else
								designations = designations+","+result.subList1[i].subList[n];
						}
					}	
								
                    str+='<tr>';	
					str+='<td>'+result.subList1[i].districtName+'</td>';
					str+='<td>'+result.subList1[i].name+'</td>';
					if(designations.length>0)
						str+='<td> '+designations+'</td> ';
					else
						str+='<td> - </td>';
					
					str+='<td>  '+result.subList1[i].mobileNo+' </td>';
					if(result.subList1[i].isInvitee =='true')
						str+='<td> Invitee </td>';
					else
						str+='<td> Non Invitee </td>';
					
					if(result.subList1 != null && result.subList1.length > 0 && result.subList1[i].sessionsList != null && result.subList1[i].sessionsList.length>0 ){
						str+='<td>'+result.subList1[i].sessionsList.length+'</td>';
						for(var k in result.subList1[i].sessionsList){      
							if(result.subList1[i].sessionsList[k].attendedTime != null){
								if(result.subList1[i].sessionsList[k].isLate =='true')
									str+='<td class="text-danger"> Y ('+result.subList1[i].sessionsList[k].attendedTime+')</td>';
								else
									str+='<td class="text-success"> Y ('+result.subList1[i].sessionsList[k].attendedTime+')</td>';
							}else{
								str+='<td class="text-danger"> N </td>';
							}
						}  
					}else{
						str+='<td> - </td>';   
					}
					if(result.subList1[i].remark != null)
						str+='<td>'+result.subList1[i].remark+'</td>'; 
					else
						str+='<td> - </td>';  
					str+='</tr>';	
				}
			}
		   str+='</tbody>';
		   str+='</table>';
		str+='</div>';
		str+='</div>';
		
	$("#meetingMemDetailsBodyId").html(str);
	$("#cmtMemberDtlsTableId11").dataTable();   
			
}

$(document).on("click",".locWisedetails",function(){ 
var dataType= $(this).attr("attr_type");
var meetingGrpId = $(this).attr("attr_meetingGrpId");
var levelId = $(this).attr("attr_level_id");
locationWiseMeetingDetails(dataType,meetingGrpId,levelId,"countClick");
});

function locationWiseMeetingDetails(dataType,meetingGrpId,levelId,clickType){
	$("#debateModelDivId").modal('show');
	$("#modalLabelNameId").html('Location Wise Meeting Details');	
	$(".debateModelCls").html("");	
	$(".debateModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var partyMeetingTypeIds = [];
	//partyMeetingTypeIds.push(29);
	var locLevelIdList = [];
	if(clickType == "countClick"){
		locLevelIdList.push(levelId);
	}else if(clickType == "chartClick"){
		locLevelIdList = levelId;
	}
	
	var jsObj ={
		activityMemberId:globalActivityMemberId,
		state:globalStateId, 
		partyMeetingMainTypeId : 4,          
		partyMeetingTypeIds:  partyMeetingTypeIds,                  
		startDateString:"01/02/2000",
		endDateString:"01/02/2020", 
		meetingGrpId:meetingGrpId,		
		locLevelIdList: locLevelIdList              
	}      
		$.ajax({
			type : 'POST',
			url : 'locationWiseMeetingDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			buildLocationWiseMeetingDetails(result,dataType,levelId);
		});  
}
function buildLocationWiseMeetingDetails(result,dataType,levelId){
	var str = '';
	str +='<table class="table bg_ED table-responsive" id="partMeetingModalData">'; 
	str +='<thead>';
	str +='<th> MEETING NAME</th>';
	str +='<th> STATUS</th>';
	str +='<th> INVITED</th>';
	str +='<th> ATTENDED</th>';
	str +='<th> LATE</th>';
	str +='<th> ABSENT</th>';
	str +='<th> NON INVITEE</th>';
	str +='<th> TOTAL IMAGES</th>';
	str +='<th> RECENT IMAGES</th>';
	str +='</thead>';
	str +='<tbody>';
	for(var i in result){		
		if(result[i].status != null && result[i].status == dataType){
			str+='<tr>';
		if(result[i].partyMeetingName != null){
			str+='<td id="'+result[i].partyMeetingId+'">'+result[i].partyMeetingName+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].status != null && result[i].status == "Conducted"){
			str+='<td style="color:green">'+result[i].status+'</td>';
		}else if(result[i].status != null && result[i].status == "Not-Conducted"){
			str+='<td style="">'+result[i].status+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].invited != null){
			str+='<td>'+result[i].invited+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].attended != null){
			str+='<td>'+result[i].attended+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].late != null){
			str+='<td>'+result[i].late+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].absent != null){
			str+='<td>'+result[i].absent+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].nonInvitee != null){
			str+='<td>'+result[i].nonInvitee+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].totalImages != null){
			str+='<td>'+result[i].totalImages+'</td>';
		}else{
			str+='<td>-</td>';
		}
		 str+='<td>';
		if(result[i].totalImages > 0){  
				str+='<ul class="list-inline modalImagesUl">';
     	if(result[i].totalImages > 1){
			var remaingImagePath = result[i].totalImages-2;
			var time = result[i].subList[0].uploadedTime;
			//formatAMPM(time);
				str+='<li>';
				    str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].subList[0].imagePath+'" alt=""/>';
					//str+='<img src="https://www.mytdp.com/party_meetings/ea18acce-756a-4fc2-aec9-6d8090a27dcd_Tulips.jpg"  alt=""/>';
					str+='<p>'+time+'</p>';
					str+='<p>'+result[i].subList[0].upLoadedDate+'</p>';
				str+='</li>';
				var time1 = result[i].subList[1].uploadedTime;
				//formatAMPM(time1);
				str+='<li>';
				   str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].subList[1].imagePath+'" alt=""/>';
					//str+='<img src="https://www.mytdp.com/party_meetings/ea18acce-756a-4fc2-aec9-6d8090a27dcd_Tulips.jpg"  alt=""/>';
					str+='<p>'+time1+'</p>';
					str+='<p>'+result[i].subList[1].upLoadedDate+'</p>';
				str+='</li>';
				str+='<li   class="getModalImagesCls" attr_Meeting_level_id="'+levelId+'" attr_Meeting_id="'+result[i].partyMeetingId+'" attr_location_value="0" attr_count="1" style="cursor:pointer;" >';
				if(remaingImagePath>result[i].totalImages)
				str+='<p>'+remaingImagePath+'+'+'</p>';
				 str+='<p>View All</p>';
				str+='</li>';
			}else{
				str+='<li>';
				    str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].subList[0].imagePath+'" alt=""/>';
					//str+='<img src="https://www.mytdp.com/party_meetings/ea18acce-756a-4fc2-aec9-6d8090a27dcd_Tulips.jpg"  alt=""/>';
					str+='<p>'+result[i].subList[0].uploadedTime+'</p>';
					str+='<p>'+result[i].subList[0].upLoadedDate+'</p>';
				str+='</li>';
			}
				str+='</ul>';
			}
		else{
				str+='-';
		}
		str+='</td>'; 
		str+='</tr>';
		}else if(dataType == "All"){
			str+='<tr>';
		if(result[i].partyMeetingName != null){
			str+='<td id="'+result[i].partyMeetingId+'">'+result[i].partyMeetingName+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].status != null && result[i].status == "conducted"){
			str+='<td style="color:green">'+result[i].status+'</td>';
		}else if(result[i].status != null && result[i].status == "Notconducted"){
			str+='<td style="">'+result[i].status+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].invited != null){
			str+='<td>'+result[i].invited+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].attended != null){
			str+='<td>'+result[i].attended+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].late != null){
			str+='<td>'+result[i].late+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].absent != null){
			str+='<td>'+result[i].absent+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].nonInvitee != null){
			str+='<td>'+result[i].nonInvitee+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].totalImages != null){
			str+='<td>'+result[i].totalImages+'</td>';
		}else{
			str+='<td>-</td>';
		}
		 str+='<td>';
		if(result[i].totalImages > 0){  
				str+='<ul class="list-inline modalImagesUl">';
     	if(result[i].totalImages > 1){
			var remaingImagePath = result[i].totalImages-2;
				str+='<li>';
				    str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].subList[0].imagePath+'" alt=""/>';
					//str+='<img src="https://www.mytdp.com/party_meetings/ea18acce-756a-4fc2-aec9-6d8090a27dcd_Tulips.jpg"  alt=""/>';
					str+='<p>'+result[i].subList[0].uploadedTime+'</p>';
					str+='<p>'+result[i].subList[0].upLoadedDate+'</p>';
				str+='</li>';
				str+='<li>';
				   str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].subList[1].imagePath+'" alt=""/>';
					//str+='<img src="https://www.mytdp.com/party_meetings/ea18acce-756a-4fc2-aec9-6d8090a27dcd_Tulips.jpg"  alt=""/>';
					str+='<p>'+result[i].subList[1].uploadedTime+'</p>';
					str+='<p>'+result[i].subList[1].upLoadedDate+'</p>';
				str+='</li>';
				str+='<li  class="getModalImagesCls" attr_Meeting_level_id="'+levelId+'" attr_Meeting_id="'+result[i].partyMeetingId+'" attr_location_value="0" attr_count="1" style="cursor:pointer;" >';
				if(remaingImagePath>result[i].totalImages)
					str+='<p >'+remaingImagePath+'+'+'</p>';
				 str+='<p>View All</p>';
				str+='</li>';
			}else{
				str+='<li  class="getModalImagesCls" attr_Meeting_level_id="'+levelId+'" attr_Meeting_id="'+result[i].partyMeetingId+'"  attr_location_value="0" attr_count="1" style="cursor:pointer;" >';
				    str+='<img src="https://www.mytdp.com/party_meetings/'+result[i].subList[0].imagePath+'" alt=""/>';
					//str+='<img src="https://www.mytdp.com/party_meetings/ea18acce-756a-4fc2-aec9-6d8090a27dcd_Tulips.jpg"  alt=""/>';
					str+='<p>'+result[i].subList[0].uploadedTime+'</p>';
					str+='<p>'+result[i].subList[0].upLoadedDate+'</p>';
				str+='</li>';
			}
				str+='</ul>';
			}
		else{
				str+='-';
		}
		str+='</td>'; 
		str+='</tr>';
		}
	}
	str +='</tbody>';
	str +='</table>';
	$(".debateModelCls").html(str); 
	$("#partMeetingModalData").dataTable()
	
}


/*Swadhin*/ 
$(document).on("click",".multicLocationMeetingCls",function(){
	
	$(".multicLocationMeetingCls").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	var locLevelId = $(this).attr('attr_levelId'); 
	var partyMeetingGroupId = $(this).attr('attr_group_id');
	var sessionId= $(this).attr('attr_sessionId');
	
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	
	if(!$(".meetingsIconExpand").find("i").hasClass("glyphicon-resize-small"))
	{
		$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	}
	
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".moreMeetingsBlocks1,.moreMeetingsBlocksComparision,.moreMeetingsBlocksDetailed,.moreMeetingsBlocks,.moreMeetingsBlocksList,.moreMultiMeetingsBlocksDetailed,.meetingsHiddenBlock,.stateLevelMeetingBlock1,.detailedMeetngsBlkId").hide();
		$(".stateGeneralMeeting,.specialMeetings,.stateLevelMeetingsExpand,.statelevelSessionMeeting,.mainMeetingsIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".meetingsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".meetingsIconExpand").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen")
	}else{
		$(".meetingsHiddenBlock").show();
		$(".meetingsHiddenBlock").find("i").show();
		$(".moreMeetingsBlocksIcon").addClass("moreMeetingsBlocksIconMulti").removeClass("moreMeetingsBlocksIcon");
		$(".moreMeetingsBlocksIconMulti").attr('attr_levelId',locLevelId).attr('attr_group_id',partyMeetingGroupId).attr('attr_sessionId',sessionId); 
		
		getCmmttsAndPblcRprsnttvMembersInvitedAndAttendedToSeeionWiseMultiLocationMeetingDtls(locLevelId,partyMeetingGroupId);
		$(".meetingsIconExpand").find("i").addClass("glyphicon-resize-small").removeClass("glyphicon-fullscreen");
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $(".meetingsBlock").offset().top},
			'slow');
		},500);
	}
	if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".moreMeetingsBlocks1").hide();
		$(".moreMeetingsBlocksDetailed").hide();
		$(".moreMeetingsBlocksComparision").hide();
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock").hide();
		$(".dateRangePickerClsForDebates").toggleClass("hide");
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".moreMeetingsBlocks1").hide();
		$(".moreMeetingsBlocksDetailed").hide();
		$(".moreMeetingsBlocksComparision").hide();
	}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
		$(".moreMeetingsBlocks1").hide();
		$(".moreMeetingsBlocksDetailed").hide();
		$(".moreMeetingsBlocksComparision").hide();
	}else if( $(".newsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".newsHiddenBlock,.morenewsBlocksIcon,.newsHiddenMoreBlock").hide();
		$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForNews").toggleClass("hide");
	}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents,.comparisonBlockActivities ").hide();
		$(".panelBlockCollapseIcon").addClass("collapsed");
		$(".activitesExpandIcon").parent().parent().parent().parent().find(".collapse").removeClass("in").addClass("collapsed");
		$(".activitesExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(".eventsListExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForEvents").toggleClass("hide");
	}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
		$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".attendaceIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".attendaceIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".attendanceBlockMore,.moreAttBlocks,.moreAttBlocksIcon").hide();
		$(".dateRangePickerClsForAttendance").toggleClass('hide');
		$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".NewTourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".NewTourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".NewTourExpandCls,.NewToursHiddenBlock,.moreNewToursBlocksDetailed").hide();
		$(".NewToursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".alertsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".districtAltCtnCls ,.alertLocationDiv,.dateRangePickerClsForAlert,.alertComparisonblock").hide();
		$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".tourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".tourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".tourExpandCls ,.toursHiddenBlock,.moreToursBlocks1,.moreToursBlocksDetailed ,.comparisonBlockTours ,.toursDateRangePickerCls").hide();
		$(".toursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".emnIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".emnIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".moreBlockEMN ,.newEmnHideCls,.dateRangePickerClsForEmn,.newsComparisonUl").hide();
		$(".electronicMediaBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}
}); 
$(document).on("click",".moreMeetingsBlocksIconMulti",function(){
	$(this).removeClass("moreMeetingsBlocksIconMulti").addClass("moreMeetingsBlocksIcon");
	var locLevelId = $(this).attr('attr_levelId'); 
	var partyMeetingGroupId = $(this).attr('attr_group_id');
	var sessionId= $(this).attr('attr_sessionId');
	$(".moreMultiMeetingsBlocksDetailed,.moreMeetingsBlocksList,.detailedMeetngsBlkId").show();
	getDistWiseMeetingDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,0);
});

function getCmmttsAndPblcRprsnttvMembersInvitedAndAttendedToSeeionWiseMultiLocationMeetingDtls(locLevelId,partyMeetingGroupId){
	var jsObj ={    
		activityMemberId:globalActivityMemberId,
		partyMeetingMainTypeId : 4,          
		locLevelId:locLevelId,                
		startDateString:"01/03/2017",
		endDateString:"30/03/2025",   
		state:globalStateId,                             
		partyMeetingGroupId:partyMeetingGroupId  
	}      
		$.ajax({
			type : 'POST',
			url : 'getCmmttsAndPblcRprsnttvMembersInvitedAndAttendedToSeeionWiseMultiLocationMeetingDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildCommitteesAndPublicRepresentativeMembersInvitedAndDtls(result,"noClick");	
		});    
}

function getDistWiseMeetingDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,num){  
		if(sessionId == 0){
			$("#districtWiseSpecialMeetingsGraph").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}else{
			$("#districtWiseGraphSpecialMee"+num).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}
	
	if(locLevelId != 0 && locLevelId.length == 1){
		locLevelId = parseInt(locLevelId);
	}else if(locLevelId != 0 && locLevelId.length == 3){
		locLevelId = 7;
	}else if(locLevelId != 0 && locLevelId.length == 5){
		locLevelId = 4;
	}
	var jsObj ={
		activityMemberId:globalActivityMemberId,
		partyMeetingMainTypeId : 4,          
		locLevelId:locLevelId,                          
		startDateString:"01/03/2010",
		endDateString:"30/03/2017",   
		state:globalStateId,                                 
		partyMeetingGroupId:partyMeetingGroupId,
		sessionId:sessionId               
	}      
		$.ajax({
			type : 'POST',
			url : 'getDistWiseMeetingDtlsForDiffLevelOfMeetingsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			buildParyMeetingDetailsLocLvlWise(result,sessionId,num);
		});  
}

	function buildParyMeetingDetailsLocLvlWise(resultList,sessionId,num){
		if(sessionId == 0){
			$("#districtWiseSpecialMeetingsGraph").html('');  
		}else{
			$("#districtWiseGraphSpecialMee"+num).html('');
		}
		var str='';
		for(var j in resultList){
			var result = resultList[j];
			if(result != null && result.length > 0){
				
				if(sessionId == 0){
					str+='<div><h4><span class="headingColor text-capitalize">'+result[0].name+' Level Multi Location Meetings </span>'; 
					for(var k in result[0].sessionVOs){
						str+='<span style="margin-left : 20px" class="active sessnWiseAttendnceBsd btn btn-mini btn-xs " attr_levelId="'+result[0].locLevelIdList+'" attr_group_id="1" attr_num="'+j+'" attr_sessionId="'+result[0].sessionVOs[k].id+'" >'+result[0].sessionVOs[k].name+'</span>';
					}
					str+='<div id="districtWiseGraphSpecialMee'+j+'" class="chartLiD" style="height:300px" ></div></h4></div>';
				}else{
					str+='<div id="districtWiseGraphSpecialMee'+num+'" class="chartLiD" style="height:300px" ></div></h4></div>';
				}
			}
		}

		if(sessionId == 0){
			$("#districtWiseSpecialMeetingsGraph").html(str);  
		}else{
			$("#districtWiseGraphSpecialMee"+num).html(str);
		}		
		
		for(var j in resultList){  
			var result = resultList[j];
			if(result != null && result.length > 0){
				var locationNameArray =[];
				var attendedCountArray = [];
				var inviteAbsentCountArray = [];
				for(var i in result){
					locationNameArray.push(result[i].locationName);
					var sessionId = 0;
					if(result[i].sessionId != null && parseInt(result[i].sessionId)>0){
						sessionId = parseInt(result[i].sessionId);
					}
					attendedCountArray.push({"dataBuildType":"attended","y":parseInt(result[i].attendedCount),"id":parseInt(result[i].locationId),"meetingId":parseInt(result[i].meetingId),"sessionId":parseInt(sessionId),"locLevelIdList":result[i].locLevelIdList});
					inviteAbsentCountArray.push({"dataBuildType":"absent","y":parseInt(result[i].inviteAbsentCount),"id":parseInt(result[i].locationId),"meetingId":parseInt(result[i].meetingId),"sessionId":parseInt(sessionId),"locLevelIdList":result[i].locLevelIdList});
				}  
			}
			if(sessionId == 0){
				functionHighchartsForAttendence(j,locationNameArray,attendedCountArray,inviteAbsentCountArray);
			}else{
				functionHighchartsForAttendence(num,locationNameArray,attendedCountArray,inviteAbsentCountArray);
			}
		}
	}
	function functionHighchartsForAttendence(id,locationNameArray,attendedCountArray,inviteAbsentCountArray){
		$(function () {
			$('#districtWiseGraphSpecialMee'+id).highcharts({
				colors: ['#3C763D','#A94442'],
				chart: {
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					 min: 0,
						 gridLineWidth: 0,
						 minorGridLineWidth: 0,
						categories: locationNameArray,
					labels: {
							rotation: -45,
							style: {
								fontSize: '13px',
								fontFamily: 'Verdana, sans-serif'
							}
						}
				},
				yAxis: {
					min: 0,
						   gridLineWidth: 0,
							minorGridLineWidth: 0,
					title: {
						text: ''
					},
					stackLabels: {
						enabled: false,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				legend: {
					enabled: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					headerFormat: '<b>{point.x}</b><br/>',
					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y} - {point.percentage:.1f}%</b><br/>',
					shared: true
				},
				plotOptions: {
					useHTML: true,
					//text-align:'center',
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							}
						  
						}
					},
					series: {
						cursor: 'pointer',
						point: {
						events: {
						click: function () {
								var locationId = this.id;
								var locLevelIdList = this.locLevelIdList;
								var sessionId = this.sessionId;
								var dataBuildType = this.dataBuildType;
								getInviteeAtendedDetails(locationId,locLevelIdList,sessionId,dataBuildType);
							}  
						}
					}
				}
				},
				series: [{
					name: 'Attended',  
					data: attendedCountArray
				}, {
					name: 'Absent',
					data: inviteAbsentCountArray
				}]
			});
		});
	}
	
$(document).on("click",".multiMetingDetailedBlock",function(){
	$(".moreMeetingsBlocksMultiLocationComparision,.moreMultiMeetingsBlocksDetailed,#childUserTypeDetailsDivIdForMeetingMultiLocation,#childActivityMemberDivIdForMeetingMultiLocation,#directChildActivityMeetingMemberDivMultiLocation,#topPoorLocationsMeetingDiv").hide();
	$("#meetingLevelHIghChartsDivId,#districtWiseSpecialMeetingsGraph,#userAccessLevelLocationDivId").show();
	$(".attendedMetngs").addClass("active");
	$(".meetingBased").removeClass("active");
	$(".multiMeetingChortCls,.detailedMeetngsBlkId").show();
	var locLevelId = $(this).attr('attr_levelId');
	var partyMeetingGroupId = $(this).attr('attr_group_id');
	var sessionId= $(this).attr('attr_sessionId');
	getDistWiseMeetingDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,0);
});
$(document).on("click",".attendedMetngs",function(){
	$(".moreMeetingsBlocksMultiLocationComparision,.moreMultiMeetingsBlocksDetailed").hide();
	
	$(".multiMeetingChortCls,.detailedMeetngsBlkId").show();
	var locLevelId = $(this).attr('attr_levelId');
	var partyMeetingGroupId = $(this).attr('attr_group_id');
	var sessionId= $(this).attr('attr_sessionId');
	getDistWiseMeetingDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,0);
	
});
$(document).on("click",".meetingBased",function(){
	$(".moreMeetingsBlocksMultiLocationComparision,.moreMultiMeetingsBlocksDetailed").hide();
	
	$(".multiMeetingChortCls,.detailedMeetngsBlkId").show();
	var locLevelId = $(this).attr('attr_levelId');
	var partyMeetingGroupId = $(this).attr('attr_group_id');
	var sessionId= $(this).attr('attr_sessionId');
	getDistWiseMeetingsBasedDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,0);
});
$(document).on("click",".sessnWiseMeetigBsd",function(){
	$(".moreMeetingsBlocksMultiLocationComparision,.moreMultiMeetingsBlocksDetailed").hide();
	
	$(".multiMeetingChortCls,.detailedMeetngsBlkId").show();
	var locLevelId = $(this).attr('attr_levelId');
	var partyMeetingGroupId = $(this).attr('attr_group_id');
	var sessionId= $(this).attr('attr_sessionId');
	var num = $(this).attr('attr_num');
	getDistWiseMeetingsBasedDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,num);
});
$(document).on("click",".sessnWiseAttendnceBsd",function(){
	$(".moreMeetingsBlocksMultiLocationComparision,.moreMultiMeetingsBlocksDetailed").hide();
	
	$(".multiMeetingChortCls,.detailedMeetngsBlkId").show();
	var locLevelId = $(this).attr('attr_levelId');
	var partyMeetingGroupId = $(this).attr('attr_group_id');
	var sessionId= $(this).attr('attr_sessionId');
	var num = $(this).attr('attr_num');
	getDistWiseMeetingDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,num);
});
//SRAVANTH.
$(document).on("click",".multiLocation",function(){
	//getChildUserTypesByItsParentUserTypeForMeeting();
	getAllItsSubUserTypeIdsByParentUserTypeIdForMeetingMultiLocation();
	$(".moreMeetingsBlocksMultiLocationComparision,.moreMeetingsBlocksDetailed,#childUserTypeDetailsDivIdForMeetingMultiLocation,#childActivityMemberDivIdForMeetingMultiLocation,#directChildActivityMeetingMemberDivMultiLocation,#topPoorLocationsMeetingDiv").show();
	$("#meetingLevelHIghChartsDivId,#districtWiseSpecialMeetingsGraph,.moreMultiMeetingsBlocksDetailed,.detailedMeetngsBlkId,#userAccessLevelLocationDivId").hide();
});
$(document).on("click",".removeSelectTr",function(){
	var removeSelected = $(this).attr("attr_removeSelecUserType"); 
	$("#"+removeSelected).html(' ');
	$("#"+removeSelected).hide();
});
function getAllItsSubUserTypeIdsByParentUserTypeIdForMeetingMultiLocation(){
		 
	   $("#childUserTypeDetailsDivIdForMeetingMultiLocation").html('');
	   $("#childActivityMemberDivIdForMeetingMultiLocation").html(' ');
	   $("#directChildActivityMeetingMemberDivMultiLocation").html(' ');
	   $("#topPoorLocationsMeetingDivMultiLocation").html('');
	
	var jsObj = { parentUserTypeId : globalUserTypeId }
	$.ajax({
		type : 'POST',
		url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
		 buildgetChildUserTypesByItsParentUserTypeForMeetingMultiLocation(result)	
		}else{
		 $("#childUserTypeDetailsDivIdForMeetingMultiLocation").html('NO DATA AVAILABLE.');	
		}
	});			 
}
function buildgetChildUserTypesByItsParentUserTypeForMeetingMultiLocation(result){
	
	var str='';
	 str+='<ul class="comparisonSelect">';
	 
	 var firstChildUserTypeIdString;
	 var userType;
	 if(result !=null && result.length >0){
		 firstChildUserTypeIdString = result[0].shortName;
		 userType=result[0].userType;
		 for(var i in result){
			 str+='<li attr_usertypeid="'+result[i].shortName+'" attr_userType=\''+result[i].userType+'\'  class="childUserTypeClsForMeetingMultiLocation">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
		 }
	 }
	str+='</ul>';
	$("#childUserTypeDetailsDivIdForMeetingMultiLocation").html(str);
	$(".comparisonSelect li:first-child").addClass("active")
	
	getSelectedChildUserTypeMembersWithMeetingsCountMultiLocation(firstChildUserTypeIdString,userType);
	
}

function getSelectedChildUserTypeMembersWithMeetingsCountMultiLocation(childUserTypeIdString,userType){
	 $("#childActivityMemberDivIdForMeetingMultiLocation").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
  var parentActivityMemberId = globalActivityMemberId;
  var childUserTypeIdsArray = childUserTypeIdString.split(",");
  
   var state = globalState
  var partyMeetingTypeArr=[];
  $("#committeeTypeId li").each(function() {
	  if($(this).find("input").is(":checked")){
		  partyMeetingTypeArr.push($(this).find("input").attr("id"));
	  }
   });
   
  var jsObj ={ 
			   /*parentActivityMemberId : parentActivityMemberId,
			   childUserTypeIdsArray : childUserTypeIdsArray,
			   stateId : globalStateId,
			   reportType :"selectedUserType",
			   eventIds:[4]*/
			   fromDateStr : "01/02/2015",//customStartDateMeetings,
			   toDateStr : "02/03/2020",
			   parentActivityMemberId : parentActivityMemberId,
			   childUserTypeIdsArray : childUserTypeIdsArray,
			   stateId : globalStateId,
			   reportType :"selectedUserType",
			   partyMeetingMainTypeId:4,
			   partyMeetingLevelId:3,
			   meetingGrpId:1
			   //startDateString : customStartDateMeetings,
			  // endDateString : customEndDateMeetings
			 }
  $.ajax({
		type : 'POST',
		url : 'getSelectedChildTypeMembersForMultiLocationMeetingsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
	   $("#childActivityMemberDivIdForMeetingMultiLocation").html(' ');
	   $("#directChildActivityMeetingMemberDivMultiLocation").html(' ');
	  if(result != null && result.length > 0){
		  buildgetSelectedChildUserTypeMembersForMeetingMultiLocation(result,userType);
	  }else{
		  $("#childActivityMemberDivIdForMeetingMultiLocation").html("NO DATA AVAILABLE");
	  }
	});
	
}
function buildgetSelectedChildUserTypeMembersForMeetingMultiLocation(result,childUserType){
var str='';
		 var firstChildActivityMemberId = "directChildActivityMeetingMemberDivMultiLocation";
		var firstActivityMemberId = result[0].activityMemberId;
		var firstUserTypeId = result[0].userTypeId;
		var firstuserType = result[0].userType;
		var firstUserMemberName = result[0].name;
		var firstLocationLevelId = result[0].locationLevelId;
		var firstLocationValues = result[0].locationValuesSet;
if(childUserType != null && childUserType.trim()=="MLA/CI" || childUserType.trim()=="MLA" || childUserType.trim()=="CONSTITUENCY INCHARGE"){
	 str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed tableHoverLevels" id="meetingMemberDtlsDataTblIdMultiLocation">';
	 str+='<thead>';
		 str+='<th>Rank</th>';
		 str+='<th>Name</th>';
		 str+='<th>Designation</th>';
		 str+='<th>Location</th>';
		 str+='<th>Total</th>';
		 str+='<th>Yes</th>';
		 str+='<th>%</th>';
		 str+='<th>No</th>';
		 str+='<th>%</th>';
		 str+='<th>Maybe</th>';
		 str+='<th>%</th>';
	 str+='</thead>';
	 str+='<tbody>';
	 var rank=1;
	  for(var i in result){
		str+='<tr style="cursor:pointer;" class="compareActivityMemberClsForMeetingMultiLocation locationWiseHighChartsCls"  attr_selectedusertype="'+result[i].userType+'"  attr_id="directChildActivityMeetingMemberDivMultiLocation"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+' attr_usertypeid='+result[i].userTypeId+' attr_location_level="'+result[i].locationLevelId+'" attr_location_values="'+result[i].locationValuesSet+'">';
		 str+='<td><span class="counts">'+rank+'</span></td>';
		 str+='<td>'+result[i].name+'</td>';
		 str+='<td>'+result[i].userType+'</td>';
		 str+='<td>'+result[i].locationName+'</td>';
		 if(result[i].totalMeetingCnt !=null && result[i].totalMeetingCnt >0){
				str+='<td>'+result[i].totalMeetingCnt+'</td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].conductedMeetingCnt !=null && result[i].conductedMeetingCnt >0){
				str+='<td>'+result[i].conductedMeetingCnt+'</td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].conductedMeetingPerc !=null && result[i].conductedMeetingPerc >0){
				str+='<td>'+result[i].conductedMeetingPerc+'%</td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].notConductedMeetingCnt !=null && result[i].notConductedMeetingCnt >0){
				str+='<td>'+result[i].notConductedMeetingCnt+'</td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].notConductedMeetingPerc !=null && result[i].notConductedMeetingPerc >0){
				str+='<td>'+result[i].notConductedMeetingPerc+'%</td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].mayBeMeetingCnt !=null && result[i].mayBeMeetingCnt >0){
				str+='<td>'+result[i].mayBeMeetingCnt+'</td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].mayBeMeetingPerc !=null && result[i].mayBeMeetingPerc >0){
				str+='<td>'+result[i].mayBeMeetingPerc+'%</td>';
			}else{
				str+='<td> - </td>';
			}
		 str+='</tr>';
		 rank=rank+1;			 
		}
		 str+='</tbody>';
		 str+='</table>';
	 $("#childActivityMemberDivIdForMeetingMultiLocation").html(str);
	  $("#meetingMemberDtlsDataTblIdMultiLocation").dataTable({
		"aaSorting": [],
		"iDisplayLength" : 5	
	   });
	
	
 //getTopPoorMeetingLocationsMultiLocation(firstActivityMemberId,firstUserMemberName,firstuserType);
  }else{
   if(result !=null && result.length >0){
	str+='<ul class="list-inline slickPanelSliderMeetingMultiLocation">';
	var rankVar =0;
	for(var i in result){
		rankVar =rankVar+1;
		
		if(i == 0){
			str+='<li  style="cursor:pointer;" class="compareActivityMemberClsForMeetingMultiLocation panelActiveSlick locationWiseHighChartsCls" attr_id ="directChildActivityMeetingMemberDivMultiLocation" attr_selectedmembername="'+result[i].name+'" attr_selectedusertype="'+result[i].userType+'" attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' attr_location_level="'+result[i].locationLevelId+'" attr_location_values="'+result[i].locationValuesSet+'">';
		}else{
			
			str+='<li  style="cursor:pointer;" class="compareActivityMemberClsForMeetingMultiLocation locationWiseHighChartsCls" attr_id ="directChildActivityMeetingMemberDivMultiLocation" attr_selectedmembername="'+result[i].name+'" attr_selectedusertype="'+result[i].userType+'" attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' attr_location_level="'+result[i].locationLevelId+'" attr_location_values="'+result[i].locationValuesSet+'">';
			
		}
			str+='<div class="panel panel-default panelSlick">';
				str+='<div class="panel-heading">';
					str+='<h4 class="panel-title"  >'+result[i].name+'</h4>';
						str+='<span class="count">'+rankVar+'</span>';
				str+='</div>';
				str+='<div class="panel-body">';
				
				var userTypeId = result[i].userTypeId;
				 if(userTypeId!=5 && userTypeId!=6 && userTypeId!=7 && userTypeId!=9){//General Secretery or Organising secretery/secretery.
					 str+='<h4 class="text-capital">'+result[i].userType+'</h4>';
				 }else{//district President or Mp or MLA or Constituency Incharge
					 var locationName = result[i].locationName.split(" ")[0];
					 str+='<h4 class="text-capital">'+result[i].userType+' (' + locationName+ ' ) </h4>';
				 }	
					str+='<div class="table-responsive">';
						str+='<table class="table table-condensed">';
							str+='<thead>';
							 str+='<th>Total Meeting Planned</th>';
							  str+='<th>Conducted</th>';
							  str+='<th>Not-Conducted</th>';
							  str+='<th>Attendance Ratio</th>';
						 str+='</thead>';
							str+='</thead>';
							str+='<tr>';
							if(result[i].totalMeetingCnt !=null && result[i].totalMeetingCnt >0){
								str+='<td>'+result[i].totalMeetingCnt+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].conductedMeetingCnt !=null && result[i].conductedMeetingCnt >0){
								str+='<td>'+result[i].conductedMeetingCnt+'</td>';
							}else{
								str+='<td> - </td>';
							}
							/*if(result[i].conductedMeetingPerc !=null && result[i].conductedMeetingPerc >0){
								str+='<td>'+result[i].conductedMeetingPerc+'%</td>';
							}else{
								str+='<td> - </td>';
							}*/
							if(result[i].notConductedMeetingCnt !=null && result[i].notConductedMeetingCnt >0){
								str+='<td>'+result[i].notConductedMeetingCnt+'</td>';
							}else{
								str+='<td> - </td>';
							}
							/*if(result[i].notConductedMeetingPerc !=null && result[i].notConductedMeetingPerc >0){
								str+='<td>'+result[i].notConductedMeetingPerc+'%</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].mayBeMeetingCnt !=null && result[i].mayBeMeetingCnt >0){
								str+='<td>'+result[i].mayBeMeetingCnt+'</td>';
							}else{
								str+='<td> - </td>';
							}*/
							if(result[i].inviteeAttendedCntPer !=null && result[i].inviteeAttendedCntPer >0){
								str+='<td>'+result[i].inviteeAttendedCntPer+'%</td>';
							}else{
								str+='<td> - </td>';
							}
							str+='</tr>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</li>';
	}
	str+='</ul>';
	$("#childActivityMemberDivIdForMeetingMultiLocation").html(str);
	
		$(".slickPanelSliderMeetingMultiLocation").slick({
			 slide: 'li',
			 slidesToShow: 3,
			 slidesToScroll: 3,
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 2
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
				// You can unslick at a given breakpoint now by adding:
				// settings: "unslick"
				// instead of a settings object
			  ]
		});
		getDirectChildActivityMemberMeetingDetailsMultiLocation(firstActivityMemberId,firstUserTypeId,firstUserMemberName,firstuserType,firstChildActivityMemberId);
		var locationValuesArr = [];
		if(jQuery.type(firstLocationValues) == 'string'){
			var locationtempArr = firstLocationValues.split(",");
			if(locationtempArr != null && locationtempArr.length > 0){
				for(var i in locationtempArr)
					locationValuesArr.push(locationtempArr[i]);
			}
		}
		else{
			locationValuesArr = firstLocationValues;
		}
		getPartyMeetingLevelIds(firstLocationLevelId,locationValuesArr);
		//getLocationWiseMeetingsDetails(firstLocationLevelId,locationValuesArr);
		//getTopPoorMeetingLocationsMultiLocation(firstActivityMemberId,firstUserMemberName,firstuserType);
}else{
 $("#childActivityMemberDivIdForMeetingMultiLocation").html("No Data Available");
}
}
}
function getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType){
	$("#topPoorLocationsMeetingDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var state = globalState;
   
	var partyMeetingTypeArr=[];
	$("#committeeTypeId li").each(function() {
	  if($(this).find("input").is(":checked")){
		  partyMeetingTypeArr.push($(this).find("input").attr("id"));
	  }
	});
  
   
	var jsObj ={  activityMemberId : activityMemberId,
				 state:state,
				 partyMeetingTypeIds : partyMeetingTypeArr,
				 startDateString :  customStartDateMeetings ,
				 endDateString   :  customEndDateMeetings
			  }
	$.ajax({
		type : 'POST',
		url : 'getTopPoorMeetingLocationsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildTopPoorMeetingLocations(result,selectedMemberName,selectedUserType);
	});
}
function buildTopPoorMeetingLocations(result,selectedMemberName,selectedUserType){
	$("#topPoorLocationsMeetingDiv").html('');
	var str ='';
	
	if(result !=null && result.length >0){
		str+='<b><span class="color_333 pad_5 bg_CC text-capital">top five <span class="text-danger">poor</span> locations - (<span style="font-size:11px;"><i> '+selectedMemberName+' - '+selectedUserType+'</i></span>)</span></b>';
		str+='<div class="row m_top20">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<p class="text-capital"><b>'+result[0].requiredName+'</b><span style="margin-left:150px">Conducted Percentage<span></p>';
				str+='<table class="table tableCumulative">';
		var countVar =0;
		var BGColor = 1;
			for(var i in  result){
				
				//top 5 should build.
				countVar =countVar+1;
				if (countVar === 6) {
					break;
				}
					str+='<tr>';
						str+='<td><span class="count" style="background-color:rgba(237, 29, 38,'+BGColor+')">'+countVar+'</span></td>';
						
						if(result[0].requiredName == "Mandals/Muncipalitys/Divisions" || result[0].requiredName == "Villages/Wards"){
							str+='<td>'+result[i].name+' ('+result[i].locationLevelName+')</td>';
						}else{
							str+='<td>'+result[i].name+'</td>';
						}
						str+='<td>';
						if(result[i].conductedCount !=null && result[i].conductedCount >0){
							str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+result[i].conductedPerc+'%">';
							  str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result[i].conductedCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result[i].conductedPerc+'%;">';
								str+='<span class="sr-only">'+result[i].conductedPerc+'% Complete</span>';
							  str+='</div>';
							str+='</div>';
						str+='</td>';
						str+='<td class="text-danger">'+result[i].conductedCount+'('+(result[i].conductedPerc+"%")+')</td>';
						}else{
							str+='<td class="text-danger"> - </td>';
						}
							
					str+='</tr>';
					BGColor = BGColor - 0.2;
			}
			str+='</table>';
			str+='</div>';
		str+='</div>';
		$("#topPoorLocationsMeetingDiv").html(str);
		$('.progressCustom').tooltip();
	}else{
		$("#topPoorLocationsMeetingDiv").html("No Data Available");
	}			
}
function getLocationWiseMeetingsDetails(locationLevelId,locationValuesArray,partyMeetingLevelId){
	//$("#locationWiseHighChartsDiv").html("");
	
	var jsObj ={ 
			   /*activityMemberId : activityMemberId,
			   userTypeId : userTypeId,
			   state :state,
			   partyMeetingTypeIds : partyMeetingTypeArr,
			   startDateString : customStartDateMeetings,
			   endDateString : customEndDateMeetings*/
			   fromDateStr : "01/02/2017",//customStartDateMeetings,
			   toDateStr : "02/03/2017",
			   locationLevelId : locationLevelId,
			   locationValuesArray : locationValuesArray,
			   stateId : globalStateId,
			   partyMeetingMainTypeId:4,
			   partyMeetingLevelId:partyMeetingLevelId,
			   meetingGrpId:1
			 }
   
	$.ajax({
		type : 'POST',
		url : 'getLocationWiseSelectedChildMembersForMultiLocationMeetingsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildLocationWiseHighCharts(result,partyMeetingLevelId);
		}
	}); 
}

function buildLocationWiseHighCharts(result,partyMeetingLevelId){
	var divId;
	if(partyMeetingLevelId == 1){
		divId = "stateWiseHighChartsDiv";
		$("#stateWiseDiv").show();
	}
	if(partyMeetingLevelId == 2){
		divId = "districtWiseHighChartsDiv";
		$("#districtWiseDiv").show();
	}
	if(partyMeetingLevelId == 3){
		divId = "constituencyWiseHighChartsDiv";
		$("#constituencyWiseDiv").show();
	}
	if(partyMeetingLevelId == 4){
		divId = "mandalWiseHighChartsDiv";
		$("#mandalWiseDiv").show();
	}
	if(partyMeetingLevelId == 5){
		divId = "lebWiseHighChartsDiv";
		$("#lebWiseDiv").show();
	}
	if(partyMeetingLevelId == 7){
		divId = "villageWiseHighChartsDiv";
		$("#villageDiv").show();
	}
	if(partyMeetingLevelId == 8){
		divId = "wardWiseHighChartsDiv";
		$("#wardWiseDiv").show();
	}
	
	var locationNames = [];
	var conductedArr = [];
	var plannedArr = [];
	var presentArr = [];
	var abscentArr = [];
	var nonInviteeArr = [];
	for(var i in result){
		locationNames.push(result[i].name);
		conductedArr.push(result[i].conductedCount);
		plannedArr.push(result[i].count);
		presentArr.push(result[i].inviteeAttendedPerc);
		abscentArr.push(result[i].inviteeAbscentPerc);
		nonInviteeArr.push(result[i].nonInviteePerc);
	}
	
	Highcharts.chart(divId, {
	//Highcharts.chart('districtWiseHighChartsDiv', {

   // colors:['#dddd','#333','#fef'],
    chart: {
        type: 'bar',
        backgroundColor:'transparent',
		width:900,
		height:120
    },
    title: {
        text: null
    },
    subtitle: {
        text: null
    },
    xAxis: {
        min: 0,
        gridLineWidth: 0,
        minorGridLineWidth: 0,
        categories: locationNames,
        title: {
            text: null
        }
    },
    yAxis: {
        min: 0,
        gridLineWidth: 0,
        minorGridLineWidth: 0,
        title: {
            text: null,
            align: 'high'
        },
        labels: {
            overflow: 'justify',
            enabled:false
        }
    },
    tooltip: {
        valueSuffix: ' %'
    },
    plotOptions: {
        bar: {
            stacking: 'normal',
            dataLabels: {
                enabled: true
            }
        }
    },
    legend: {
        layout: 'horizontal',
        align: 'center',
        verticalAlign: 'bottom',
        x: 30,
        y: 20,
        floating: false,
        borderWidth: 0,
        backgroundColor: 'transparent',
        shadow: false
    },
    credits: {
        enabled: false
    },
    series: [{
        name: 'Meetings Conducted',
        data: conductedArr,
        stack: 'counts'
    }, {
        name: 'Meetings Planned',
        data: plannedArr,
        stack: 'counts'
    }, {
        name: 'Present',
        data: presentArr,
        stack: 'perc'
    }, {
        name: 'Absent',
        data: abscentArr,
        stack: 'perc'
    }, {
        name: 'Non-Invitee',
        data: nonInviteeArr,
        stack: 'perc'
    }]
});
}

function getDirectChildActivityMemberMeetingDetailsMultiLocation(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId){
   $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
   var state = globalState
   var childUserTypeIdsArray=[];
	childUserTypeIdsArray.push(userTypeId);
  var partyMeetingTypeArr=[];
  $("#committeeTypeId li").each(function() {
	  if($(this).find("input").is(":checked")){
		  partyMeetingTypeArr.push($(this).find("input").attr("id"));
	  }
   });
   
  var jsObj ={ 
			   /*activityMemberId : activityMemberId,
			   userTypeId : userTypeId,
			   state :state,
			   partyMeetingTypeIds : partyMeetingTypeArr,
			   startDateString : customStartDateMeetings,
			   endDateString : customEndDateMeetings*/
			   fromDateStr : "01/02/2015",//customStartDateMeetings,
			   toDateStr : "02/03/2020",
			   parentActivityMemberId : activityMemberId,
			   childUserTypeIdsArray : childUserTypeIdsArray,
			   stateId : globalStateId,
			   reportType :"directChild",
			   partyMeetingMainTypeId:4,
			   partyMeetingLevelId:3,
			   meetingGrpId:1
			 }
   
	$.ajax({
		type : 'POST',
		url : 'getSelectedChildTypeMembersForMultiLocationMeetingsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		$("#"+childActivityMemberId).html('');
		buildgetDirectChildActivityMemberMeetingsDetailsMultiLocation(result,selectedMemberName,selectedUserType,childActivityMemberId,activityMemberId);
	});
}
function buildgetDirectChildActivityMemberMeetingsDetailsMultiLocation(result,selectedMemberName,selectedUserType,childActivityMemberId,activityMemberId){
	$("#"+childActivityMemberId).html('');
	var str ='';
	
	if(result != null && result.length >0){
		var rankVar =0;
		str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
		if(childActivityMemberId != "directChildActivityMeetingMemberDivMultiLocation"){
			str+='<span class="removeSelectTr pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
		}
			if(childActivityMemberId != "directChildActivityMeetingMemberDivMultiLocation")
			{
				str+='<table class="table table-condensed tableLevels m_top20">';
			}else{
				str+='<table class="table table-condensed tableHoverLevels m_top20">';
			}
			
				str+='<thead class="bg_D8 text-capital">';
					str+='<th>Rank</th>';
					str+='<th>Designation</th>';
					str+='<th>Name</th>';
					str+='<th style="text-align:center;">total meetings planned</th>';
					str+='<th style="text-align:center;">Conducted</th>';
					//str+='<th style="text-align:center;">%</th>';
					str+='<th style="text-align:center;">Not-conducted</th>';
					//str+='<th style="text-align:center;">%</th>';
					str+='<th style="text-align:center;">attendance ratio</th>';
					//str+='<th style="text-align:center;">%</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					rankVar = rankVar+1;
					 var locationNamevar = result[i].locationName;
					str+='<tr class="compareLowLevelActivityMeetingMemberClsMultiLocation locationWiseHighChartsCls"  attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'" attr_location_level="'+result[i].locationLevelId+'" attr_location_values="'+result[i].locationValuesSet+'">';
						str+='<td>';
						str+='<span class="tableCount">'+rankVar+'</span>';	
						str+='</td>';
						if( locationNamevar.indexOf(',') == -1){
							str+='<td>'+result[i].userType+' (<b>'+result[i].locationName+'</b>)</td>';
						}else{
							str+='<td>'+result[i].userType+'</td>';
						}
						if( result[i].name != null && $.trim(result[i].name).length > 0 ){
								str+='<td>'+result[i].name+'</td>';
						}else{
							str+='<td> - </td>';
						}
						if(result[i].totalMeetingCnt !=null && result[i].totalMeetingCnt >0){
							str+='<td style="text-align:center;" >'+result[i].totalMeetingCnt+'</td>';
						}else{
							str+='<td> - </td>';
						}
						if(result[i].conductedMeetingCnt !=null && result[i].conductedMeetingCnt >0){
							str+='<td style="text-align:center;">'+result[i].conductedMeetingCnt+'</td>';
						}else{
							str+='<td> - </td>';
						}
						/*if(result[i].conductedMeetingPerc !=null && result[i].conductedMeetingPerc >0){
							str+='<td style="text-align:center;">'+result[i].conductedMeetingPerc+'%</td>';
						}else{
							str+='<td> - </td>';
						}*/
						if(result[i].notConductedMeetingCnt !=null && result[i].notConductedMeetingCnt >0){
							str+='<td style="text-align:center;">'+result[i].notConductedMeetingCnt+'</td>';
						}else{
							str+='<td> - </td>';
						}
						/*if(result[i].notConductedMeetingPerc !=null && result[i].notConductedMeetingPerc >0){
							str+='<td style="text-align:center;">'+result[i].notConductedMeetingPerc+'%</td>';
						}else{
							str+='<td> - </td>';
						}
						if(result[i].mayBeMeetingCnt !=null && result[i].mayBeMeetingCnt >0){
							str+='<td style="text-align:center;">'+result[i].mayBeMeetingCnt+'</td>';
						}else{
							str+='<td> - </td>';
						}*/
						if(result[i].inviteeAttendedCntPer !=null && result[i].inviteeAttendedCntPer >0){
							str+='<td style="text-align:center;">'+result[i].inviteeAttendedCntPer+'%</td>';
						}else{
							str+='<td> - </td>';
						}
					str+='</tr>';
					str+='<tr class="showHideTr" style="display:none" attr_id = "districtpositionId'+result[i].userTypeId+''+i+'">';
						
						str+='<td colspan="10"  id="districtpositionId'+result[i].userTypeId+''+i+'">';
						
						str+='</td>';
					str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$("#"+childActivityMemberId).html(str);
		getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType); 
	}else{
		 if(childActivityMemberId == "directChildActivityMeetingMemberDivMultiLocation"){
			$("#"+childActivityMemberId).html("<h5><span  class='text-capital'>"+selectedMemberName+"</span> - <span class='text-capitalize'>"+selectedUserType+"</span> - ( No Data Available )</h5>");
		}		
	}
}

$(document).on("click",".childUserTypeClsForMeetingMultiLocation",function(){
	var childUserTypeIdString = $(this).attr("attr_userTypeId");
	var childUserType = $(this).attr("attr_userType");
	getSelectedChildUserTypeMembersWithMeetingsCountMultiLocation(childUserTypeIdString,childUserType);
});

$(document).on("click",".locationWiseHighChartsCls",function(){
	var locationLevelId = $(this).attr("attr_location_level");
	var locationValues = $(this).attr("attr_location_values");
	var locationValuesArr = [];
		if(jQuery.type(locationValues) == 'string'){
			var locationtempArr = locationValues.split(",");
			if(locationtempArr != null && locationtempArr.length > 0){
				for(var i in locationtempArr)
					locationValuesArr.push(locationtempArr[i]);
			}
		}
		else{
			locationValuesArr = locationValues;
		}
	
	getPartyMeetingLevelIds(locationLevelId,locationValuesArr);
});

var globalMeetingLevelIds = [];
function getPartyMeetingLevelIds(locationLevelId,locationValuesArray){
	
	$("#stateWiseHighChartsDiv").html('');
	$("#stateWiseDiv").hide();
	$("#districtWiseHighChartsDiv").html('');
	$("#districtWiseDiv").hide();
	$("#constituencyWiseHighChartsDiv").html('');
	$("#constituencyWiseDiv").hide();
	$("#mandalWiseHighChartsDiv").html('');
	$("#mandalWiseDiv").hide();
	$("#lebWiseHighChartsDiv").html('');
	$("#lebWiseDiv").hide();
	$("#villageWiseHighChartsDiv").html('');
	$("#villageDiv").hide();
	$("#wardWiseHighChartsDiv").html('');
	$("#wardWiseDiv").hide();
	
	var jsObj ={ 
			   fromDateStr : "01/02/2017",//customStartDateMeetings,
			   toDateStr : "02/03/2017",
			   locationLevelId : locationLevelId,
			   locationValuesArray : locationValuesArray,
			   stateId : globalStateId,
			   partyMeetingMainTypeId:4,
			   partyMeetingLevelId:3,
			   meetingGrpId:1
			 }
   
	$.ajax({
		type : 'POST',
		url : 'getPartyMeetingLevelIdsByAccessLevelAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			globalMeetingLevelIds = result;
			for(var i in result){
				getLocationWiseMeetingsDetails(locationLevelId,locationValuesArray,result[i]);
			}
		}
	});
}

$(document).on("click",".compareActivityMemberClsForMeetingMultiLocation",function(){
	//$(".slickPanelSlider").find("li").removeClass("active");
	//$(this).addClass("active");
	$(".slickPanelSliderMeetingMultiLocation").find("li").removeClass("panelActiveSlick");
	$(this).addClass("panelActiveSlick");
	var activityMemberId = $(this).attr("attr_activitymemberid");  
	var userTypeId = $(this).attr("attr_usertypeid"); 
	var selectedMemberName = $(this).attr("attr_selectedmembername");  
	var selectedUserType = $(this).attr("attr_selectedusertype");  
	var childActivityMemberId = $(this).attr("attr_id");  
	var locationLevelId = $(this).attr("attr_location_level");
	var locationValues = $(this).attr("attr_location_values");
	/*var locationValuesArr = [];
	if(jQuery.type(locationValues) == 'string'){
		var locationtempArr = locationValues.split(",");
		if(locationtempArr != null && locationtempArr.length > 0){
			for(var i in locationtempArr)
				locationValuesArr.push(locationtempArr[i]);
		}
	}
	else{
		locationValuesArr = locationValues;
	}*/
	if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
	  getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType); 
	 }else{
	  getDirectChildActivityMemberMeetingDetailsMultiLocation(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
	  getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType);
	}
	//getLocationWiseMeetingsDetails(locationLevelId,locationValuesArr);
});

 $(document).on("click",".compareLowLevelActivityMeetingMemberClsMultiLocation",function(){
	$(this).next('tr.showHideTr').show(); 
	 
	var activityMemberId = $(this).attr("attr_activitymemberid");  
	var userTypeId = $(this).attr("attr_usertypeid"); 
	var selectedMemberName = $(this).attr("attr_selectedmembername");  
	var selectedUserType = $(this).attr("attr_selectedusertype");  
	var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id"); 
	var locationLevelId = $(this).attr("attr_location_level");
	var locationValues = $(this).attr("attr_location_values");
	/*var locationValuesArr = [];
	if(jQuery.type(locationValues) == 'string'){
		var locationtempArr = locationValues.split(",");
		if(locationtempArr != null && locationtempArr.length > 0){
			for(var i in locationtempArr)
				locationValuesArr.push(locationtempArr[i]);
		}
	}
	else{
		locationValuesArr = locationValues;
	}	*/
	if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType); 
		 }else{
	      getDirectChildActivityMemberMeetingDetailsMultiLocation(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		  getTopPoorMeetingLocations(activityMemberId,selectedMemberName,selectedUserType);
		}
	//getLocationWiseMeetingsDetails(locationLevelId,locationValuesArr);
});

function getInviteeAtendedDetails(locationId, levelIdsArr,sesionId,cadreType){
	getAttendedCadreDetails(cadreType,sesionId,levelIdsArr,1,locationId,4,0)
}

var globalMembersResult;
function getAttendedCadreDetails(cadreType,sessionId,levelId,meetingGrpId,locationId,partyMeetingMainTypeId,partyMeetingId){
	$("#meetingMemDetailsId").modal("show");     
	$("#meetingMemDetailsBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div>');
	var levelIdArr = new Array();
	if(locationId == 0){
		if(levelId.length>1)
			levelIdArr=levelId;
		else
			levelIdArr.push(levelId);
	}else{
		levelIdArr = levelId;
	}
	
	var jObj = {
		fromDateStr : '01/02/2000'  ,//customStartDateMeetings,
		toDateStr : '01/02/2020',//customEndDateMeetings,
		activityMemberId : globalActivityMemberId,
		stateId : globalStateId,
		partyMeetingMainTypeId:partyMeetingMainTypeId,
		partyMeetingLevelId:levelId,
		levelIdsLsit:levelIdArr,
		meetingGrpId:meetingGrpId,
		sessionTypeId:sessionId,
		cadreType:cadreType,
		partyMeetingId:partyMeetingId,
		locationId:locationId
	};
	
	$.ajax({
	  type:'GET',
	  url: 'getPartyLevelIdWiseMeetingsAttendanceDetails.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.subList1 != null && result.subList1.length > 0){
			globalMembersResult = result;
			buildPartyLevelIdWiseMeetingsAttendanceDetails(result,"overview","","");
		}
		else
			$("#meetingMemDetailsBodyId").html("NO DATA AVAILABLE...");
	});
	
}
function getDistWiseMeetingsBasedDtlsForDiffLevelOfMeetings(locLevelId,partyMeetingGroupId,sessionId,num){ 

		if(sessionId == 0){
			$("#districtWiseSpecialMeetingsGraph").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}else{
			//$("#districtWiseGraphSpecialMee"+num).html('');
			$("#districtWiseGraphSpecialMee"+num).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		}
if(locLevelId != 0 && locLevelId.length == 1){
		locLevelId = parseInt(locLevelId);
}else if(locLevelId != 0 && locLevelId.length == 3){
		locLevelId = 7;
}else if(locLevelId != 0 && locLevelId.length == 5){
		locLevelId = 4;
}
var jsObj ={
		activityMemberId:globalActivityMemberId,
		partyMeetingMainTypeId : 4,          
		locLevelId:locLevelId,                          
		startDateString:"01/03/2010",
		endDateString:"30/03/2017",   
		state:globalStateId,                                 
		partyMeetingGroupId:partyMeetingGroupId,
		sessionId:sessionId ,
		type : "meetingsBased"
	}      
		$.ajax({
			type : 'POST',
			url : 'getDistWiseMeetingsBasedDtlsForDiffLevelOfMeetingsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			buildParyMeetingsBasedDetailsLocLvlWise(result,sessionId,num);
		});  
}
function buildParyMeetingsBasedDetailsLocLvlWise(resultList,sessionId,num){
		
		if(sessionId == 0){
			$("#districtWiseSpecialMeetingsGraph").html('');  
		}else{
			$("#districtWiseGraphSpecialMee"+num).html('');
		}
		var str='';
		for(var j in resultList){
			var result = resultList[j];
			if(result != null && result.length > 0){
				 
				
				//var levelIds = result[0].locLevelIdList;
					if(sessionId == 0){
						str+='<div><h4><span class="headingColor text-capitalize">'+result[0].name+' Level Multi Location Meetings </span>';
						for(var k in result[0].sessionVOs){
									str+='<span style="margin-left : 20px" class="active sessnWiseMeetigBsd btn btn-mini btn-xs " attr_levelId="'+result[0].locLevelIdList+'" attr_group_id="1"  attr_num="'+j+'" attr_sessionId="'+result[0].sessionVOs[k].id+'" >'+result[0].sessionVOs[k].name+'</span>';
								}  
					str+='<div id="districtWiseGraphSpecialMee'+j+'" class="chartLiD" style="height:300px" ></div></h4></div>'; 
					}else{
						str+='<div id="districtWiseGraphSpecialMee'+num+'" class="chartLiD" style="height:300px" ></div></h4></div>';
					}
			}
		}
		if(sessionId == 0){
			$("#districtWiseSpecialMeetingsGraph").html(str);  
		}else{
			$("#districtWiseGraphSpecialMee"+num).html(str);
		}
		
		for(var j in resultList){  
			var result = resultList[j];
			if(result != null && result.length > 0){
				var locationNameArray =[];
				var conductedCountArray = [];
				var notConductedCountArray = [];
				for(var i in result){
					locationNameArray.push(result[i].locationName);
					var sessionId = 0;
					if(result[i].sessionId != null && parseInt(result[i].sessionId)>0){
						sessionId = parseInt(result[i].sessionId);
					}
					conductedCountArray.push({"dataBuildType":"Conducted","y":parseInt(result[i].conductedCnt),"id":parseInt(result[i].locationId),"meetingId":parseInt(result[i].meetingId),"sessionId":parseInt(sessionId),"locLevelIdList":result[i].locLevelIdList});
					notConductedCountArray.push({"dataBuildType":"Not-Conducted","y":parseInt(result[i].notConductedCnt),"id":parseInt(result[i].locationId),"meetingId":parseInt(result[i].meetingId),"sessionId":parseInt(sessionId),"locLevelIdList":result[i].locLevelIdList});
				}  
			}
			if(sessionId == 0){
				functionHighcharts(j,locationNameArray,conductedCountArray,notConductedCountArray);
			}else{
				functionHighcharts(num,locationNameArray,conductedCountArray,notConductedCountArray);
			}
			
		}
	}
	function getconductedNotConductedDetails(dataType,meetingGrpId,levelId){
	locationWiseMeetingDetails(dataType,meetingGrpId,levelId,"chartClick");
}
	function functionHighcharts(id,locationNameArray,conductedCountArray,notConductedCountArray){
		$(function () {
			$('#districtWiseGraphSpecialMee'+id).highcharts({
				colors: ['#3C763D','#A94442'],
				chart: {
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					 min: 0,
						 gridLineWidth: 0,
						 minorGridLineWidth: 0,
						categories: locationNameArray,
					labels: {
							rotation: -45,
							style: {
								fontSize: '13px',
								fontFamily: 'Verdana, sans-serif'
							}
						}
				},
				yAxis: {
					min: 0,
						   gridLineWidth: 0,
							minorGridLineWidth: 0,
					title: {
						text: ''
					},
					stackLabels: {
						enabled: false,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				legend: {
					enabled: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					headerFormat: '<b>{point.x}</b><br/>',
					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y} - {point.percentage:.1f}%</b><br/>',
					shared: true
				},
				plotOptions: {
					useHTML: true,
					//text-align:'center',
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							}
						  
						}
					},
					series: {
						cursor: 'pointer',
						point: {
						events: {
						click: function () {
								var locationId = this.id;
								var locLevelIdList = this.locLevelIdList;
								var sessionId = this.sessionId;
								var dataBuildType = this.dataBuildType;
								getconductedNotConductedDetails(dataBuildType,1,locLevelIdList);
							}  
						}
					}
				}
				},
				series: [{
					name: 'Conducted',  
					data: conductedCountArray
				}, {
					name: 'Not Conducted',
					data: notConductedCountArray
				}]
			});
		});
	}
