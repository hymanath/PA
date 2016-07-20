getAlertData();
getAlertStatusCommentsTrackingDetails();


$(document).on("click",".assignModel",function(){

	$("#ModalShow").modal('show');
	
});
function saveAlertAssignedUser ()
	{
	
		if(commontdpCadreIds.length == 0)
		{
			$("#assignEroorDiv").html("at least one Candidate Required").css("color","red");
			return;
		}
		var jsObj =
		     {
				 tdpCadreIds : commontdpCadreIds,
				 alertId : alertId,
				task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'saveAlertAssignedUserAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
				   if(result.resultCode == 0)
					$("#assignEroorDiv").html("assigned Successfully").css("color","green");
					$("#involvedCandidatesDiv").html('');
					$("#apptmemberDetailsDiv").html('');
					clearCommonFields();
			 })
	}
function getAlertData()
{
	$("#alertCandidateDataId").html('<img src="images/search.gif" />');
    var jsObj =
		     {
			alertId  :alertId,
			task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'getAlertsDataAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
			      buildAlertData(result);
				});
}
function getAlertStatusCommentsTrackingDetails()
	{
		
		$("#alertCommentsDiv").html('<img src="images/search.gif" />');
		var jsObj={
	    			alertId:alertId,
					task:""
	    		}
		$.ajax({
		  type : 'GET',
		  url : 'getAlertStatusCommentsTrackingDetails.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
		  buildAlertCommentsForTracking(result,"");
		});
		
	}
	function buildAlertCommentsForTracking(result,aptName){
		
		var str='';
		if(result == null || result.length == 0)
		{
			$("#alertCommentsDiv").html('No Data Available');
			return;
		}
			str+='<div class="panel panel-default panelAlert">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-success">'+aptName+' Alert Status Tracking</h4>';
			str+='</div>';
			str+='<div class="panel-body" style="height:320px;">';
			str+='<ul class="alertStatusTracking">';
				for(var i in result){
					str+='<li>';
						str+='<div class="arrow_box">';
						if(result[i].id == 1)
							str+='<p> <span class="text-success"></span> Alert Created on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';	
						else
							str+='<p>Alert status changed to <span class="text-success"><b>'+result[i].status+'</b></span> on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';
						if(result[i].commentsList != null && result[i].commentsList.length > 0 && result[i].commentsList[0].length > 0){
								str+='<u style="font-size:15px;">Comments</u>';
							for(var j in result[i].commentsList){
					
								str+='<p>'+result[i].commentsList[j]+'</p>';	
							}
						}	
				
						str+='</div>';
					str+='</li>';	
				}
		str+='</ul>';
		str+='</div>';
		str+='</div>';
		$("#alertCommentsDiv").html(str);
		if(result.length >= 3)
		{
			$("#alertCommentsDiv .panel-body").mCustomScrollbar({setHeight:'320px'});
		}
		
		
		//$('html,body').animate({scrollTop: $("#alertCommentsDiv").offset().top}, 'slow');
	}
$(document).on("click",".locationLevelCls",function(){
	var levelId = $(this).attr("attr-levelId");
	var statusId=$(this).attr("attr-statusId");
	var fromDate = $(this).attr("attr-fromDate");
	var toDate=$(this).attr("attr-toDate");
	$("#errorId").html("");
	getLocationLevelAlertData(levelId,statusId,fromDate,toDate);
});
function buildAlertData(result,jsObj)
{
	if(result == null || result.length == 0)
	{
		$("#locationLevelDataId").html('No Data Available..');
		return;
	}
		
	var Level = "";
	
	if(jsObj.levelId == 2)
	{
		Level = "STATE";
	}
	if(jsObj.levelId == 3)
	{
		Level = "DISTRICT";
	}
	if(jsObj.levelId == 4)
	{
		Level = "CONSTITUENCY";
	}
	if(jsObj.levelId == 5)
	{
		Level = "MANDAL";
	}
	if(jsObj.levelId == 6)
	{
		Level = "VILLAGE";
	}
	var str='';
	if(jsObj.statusId > 0)
	str+='<h4 style="text-transform: uppercase;">'+Level+ " Wise "+result[0].status+' Alert Details</h4>';
	else
	str+='<h4 style="text-transform: uppercase;">'+Level+' Wise  Alert Details</h4>';	
	str+='<table class="table table-bordered">';
	str+='<thead>';
	str+='<th>S.NO</th>';
	str+='<th>Desc</th>';
	str+='<th>Alert Type</th>';
	str+='<th>Severity</th>';
	str+='<th>Status</th>';
	str+='<th>involved Candidates</th>';
	str+='<th>Source</th>';
	if(jsObj.levelId == 2)
	{
	str+='<th>STATE</th>';	
	}
	else
	{
		str+='<th>DISTRICT</th>';
	}
	
	str+='</thead>';
	var j=0;
	for(var i in result)
	{
		j++;
	str+='<tr>';	
	str+='<td>'+j+'</td>';
	str+='<td><a  class="alertModel" style="cursor:pointer;" attr-id="'+result[i].id+'" attr-des="'+result[i].desc+' ">'+result[i].desc+'</a></td>';
	str+='<td>'+result[i].alertType+'</td>';
	str+='<td>'+result[i].severity+'</td>';
	str+='<td>'+result[i].status+'</td>';
	//str+='<td><a  class="alertCandidate" style="cursor:pointer;" attr-id="'+result[i].id+'" attr-des="'+result[i].desc+'">'+result[i].count+'</a></td>';
	str+='<td>'+result[i].count+'</td>';
	str+='<td>'+result[i].userType+'</td>';
	
		if(jsObj.levelId == 2)
			{
				str+='<td>'+result[i].locationVO.state+'</td>';
			}
			else
			{
			str+='<td>'+result[i].locationVO.districtName+'</td>';	
			}
	
	str+='</tr>';	
	}
	str+='</table>';
	$("#locationLevelDataId").html(str);
}
$(document).on("click",".alertCandidate",function(){
getAlertCandidatesData($(this).attr("attr-id"));
});
function getAlertCandidatesData(alertId)
{
	$("#alertCandidateDataId").html('<img src="images/search.gif" />');
    GlobalAlertData = [];
		var jsObj =
		     {
			alertId  : alertId,
			task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'getAlertCandidatesDataAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
			      buildAlertCandidateData(result);
				});
}
function buildAlertCandidateData(result)
{
	
	if(result == null || result.length == 0)
	{
		$("#alertCandidateDataId").html('No Data Available..');
		return;
	}
	var str='';
	for(var i in result)
				{
	str+='<div  style="border:1px solid #ddd;padding:8px;margin-top:5px;" class="media">';
	str+='<div class="media-left">';
	str+='<img src="'+result[i].image+' "  onerror="setDefaultImage(this);" class="media-object img-center img-responsive  thumbnailSearch thumbnail" alt="Image" style="width: 60px !important; height: 60px  !important;"/>';
	str+='</div>';
	str+='<div class="media-body">';
	str+='<p><b>Name </b>:'+result[i].name+' </p>';
		if(result[i].locationVO.localEleBodyName != null && result[i].locationVO.localEleBodyName.length > 0)
							{
	str+='<p><b>State </b>:'+result[i].locationVO.state+' <b>District </b>: '+result[i].locationVO.districtName+'<br/><b>Constituency </b>:'+result[i].locationVO.constituencyName+' <b>Town </b>:'+result[i].locationVO.localEleBodyName+' <b>Ward </b>:'+result[i].locationVO.wardName+'</p>';
	
				}
				else
				{
			str+='<p><b>State </b>:'+result[i].locationVO.state+' <b>District </b>: '+result[i].locationVO.districtName+'<br/><b>Constituency </b>:'+result[i].locationVO.constituencyName+' <b>Mandal </b>:'+result[i].locationVO.tehsilName+' <b>Village </b>:'+result[i].locationVO.villageName+'</p>';
			
				}
				str+='</div>';
				str+='</div>';
				}
	$("#alertCandidateDataId").html(str);
	if(result.length > 3)
	{
		$("#alertCandidateDataId").mCustomScrollbar({setHeight:'320px'});
	}
	
}
function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }
/*function showPopUpAlertData(alertId)
{
	
	for(var i in GlobalAlertData)
	{
		if(alertId == GlobalAlertData[i].id)
		{
		
			$("#typeId").html(''+GlobalAlertData[i].alertType+'');
			$("#severityId").html(''+GlobalAlertData[i].severity+'');
			$("#createdDate").html(''+GlobalAlertData[i].date+'');
			
			$("#levelId").html(''+GlobalAlertData[i].regionScope+'');
			var location ='';
			
			if(GlobalAlertData[i].regionScope == "STATE")
			{
				location +='<p>State:'+GlobalAlertData[i].locationVO.state+'</p>';
			}
			
			else if(GlobalAlertData[i].regionScope == "DISTRICT")
			{
				location +='<p>State:'+GlobalAlertData[i].locationVO.state+'</p><p> Dist : '+GlobalAlertData[i].locationVO.districtName+'</p>';
			}
			
			else if(GlobalAlertData[i].regionScope == "CONSTITUENCY")
			{
				location +='<p>State:'+GlobalAlertData[i].locationVO.state+'</p><p> Dist : '+GlobalAlertData[i].locationVO.districtName+'</p><p> Constituency :'+GlobalAlertData[i].locationVO.constituencyName+'</p>';
			}
			
			
			else if(GlobalAlertData[i].regionScope == "MANDAL" || GlobalAlertData[i].regionScope == "MUNICIPAL-CORP-GMC" )
			{
				
					if(GlobalAlertData[i].locationVO.localEleBodyName != null && GlobalAlertData[i].locationVO.localEleBodyName.length > 0)
				{
				location +='<p>State:'+GlobalAlertData[i].locationVO.state+'</p><p> Dist : '+GlobalAlertData[i].locationVO.districtName+'</p><p> Constituency :'+GlobalAlertData[i].locationVO.constituencyName+'</p><p> Town : '+GlobalAlertData[i].locationVO.localEleBodyName+' </p>';	
				}
				else{
					location +='<p>State:'+GlobalAlertData[i].locationVO.state+' </p><p>Dist : '+GlobalAlertData[i].locationVO.districtName+' </p><p>Constituency :'+GlobalAlertData[i].locationVO.constituencyName+'</p><p> Mandal : '+GlobalAlertData[i].locationVO.tehsilName+'</p>';
				}
			}
			
			else if(GlobalAlertData[i].regionScope == "VILLAGE" || GlobalAlertData[i].regionScope == "WARD" )
			{
			
				if(GlobalAlertData[i].locationVO.localEleBodyName != null && GlobalAlertData[i].locationVO.localEleBodyName.length > 0)
				{
				location +='<p>State:'+GlobalAlertData[i].locationVO.state+'</p><p> Dist : '+GlobalAlertData[i].locationVO.districtName+'</p><p> Constituency :'+GlobalAlertData[i].locationVO.constituencyName+'</p><p> Town : '+GlobalAlertData[i].locationVO.localEleBodyName+' </p><p>Ward : '+GlobalAlertData[i].locationVO.wardName+'</p>';	
				}
				else{
					location +='<p>State:'+GlobalAlertData[i].locationVO.state+' </p><p>Dist : '+GlobalAlertData[i].locationVO.districtName+' </p><p>Constituency :'+GlobalAlertData[i].locationVO.constituencyName+'</p><p> Mandal : '+GlobalAlertData[i].locationVO.tehsilName+'</p><p> Panchayat : '+GlobalAlertData[i].locationVO.villageName+'</p>';
				}
				
			}	
			
			$("#LocationId").html(''+location+'');
			$("#statusId").val(''+GlobalAlertData[i].statusId+'');
			  $("#statusId").dropkick('refresh')
			$("#descriptionId").html(''+GlobalAlertData[i].desc+'');
			buildAlertCandidateData(GlobalAlertData[i].subList);
		}
	}
	}*/
function updateAlertStatus()
{
	var comments = $("#commentsId").val();
	var statusId=$("#statusId").val();
	 $('#errorId').html('');
	if(comments.length==0||comments=='')
	{
		  $('#errorId').html(' comments required').css("color","red");
		  return; 
	}
	if(statusId==0)
	{
	 $('#errorId').html(' Status required').css("color","red"); 
        return;	   
	}
	var jsObj =
		     {
		alertId : alertId,
		alertStatusId :statusId,
		comments:comments,
			task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'updateAlertStatusAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
				  $("#commentsId").val('');
					if(result="success")
					{
						$("#errorId").html(" Alert Updated Successfully ").css("color","green");
						getAlertStatusCommentsTrackingDetails();
					}
					
				});
			
}
$(document).on("click",".updateAlertStatusCls",function(){
	
	updateAlertStatus();
});


function buildAlertData(result)
{
	
	for(var i in result)
	{
			$("#typeId").html(''+result[i].alertType+'');
			$("#severityId").html(''+result[i].severity+'');
			$("#createdDate").html(''+result[i].date+'');
			$("#levelId").html(''+result[i].regionScope+'');
			var location ='';
			if(result[i].regionScope == "STATE")
			{
				location +='<p>State:'+result[i].locationVO.state+'</p>';
			}
			else if(result[i].regionScope == "DISTRICT")
			{
				location +='<p>State:'+result[i].locationVO.state+'</p><p> Dist : '+result[i].locationVO.districtName+'</p>';
			}
			
			else if(result[i].regionScope == "CONSTITUENCY")
			{
				location +='<p>State:'+result[i].locationVO.state+'</p><p> Dist : '+result[i].locationVO.districtName+'</p><p> Constituency :'+result[i].locationVO.constituencyName+'</p>';
			}
			else if(result[i].regionScope == "MANDAL" || result[i].regionScope == "MUNICIPAL-CORP-GMC" )
			{
				
					if(result[i].locationVO.localEleBodyName != null && result[i].locationVO.localEleBodyName.length > 0)
				{
				location +='<p>State:'+result[i].locationVO.state+'</p><p> Dist : '+result[i].locationVO.districtName+'</p><p> Constituency :'+result[i].locationVO.constituencyName+'</p><p> Town : '+result[i].locationVO.localEleBodyName+' </p>';	
				}
				else{
					location +='<p>State:'+result[i].locationVO.state+' </p><p>Dist : '+result[i].locationVO.districtName+' </p><p>Constituency :'+result[i].locationVO.constituencyName+'</p><p> Mandal : '+result[i].locationVO.tehsilName+'</p>';
				}
			}
			
			else if(result[i].regionScope == "VILLAGE" || result[i].regionScope == "WARD" )
			{
			
				if(result[i].locationVO.localEleBodyName != null && result[i].locationVO.localEleBodyName.length > 0)
				{
				location +='<p>State:'+result[i].locationVO.state+'</p><p> Dist : '+result[i].locationVO.districtName+'</p><p> Constituency :'+result[i].locationVO.constituencyName+'</p><p> Town : '+result[i].locationVO.localEleBodyName+' </p><p>Ward : '+result[i].locationVO.wardName+'</p>';	
				}
				else{
					location +='<p>State:'+result[i].locationVO.state+' </p><p>Dist : '+result[i].locationVO.districtName+' </p><p>Constituency :'+result[i].locationVO.constituencyName+'</p><p> Mandal : '+result[i].locationVO.tehsilName+'</p><p> Panchayat : '+result[i].locationVO.villageName+'</p>';
				}
				
			}	
			
			$("#LocationId").html(''+location+'');
			$("#statusId").val(''+result[i].statusId+'');
			  $("#statusId").dropkick('refresh')
			$("#descriptionId").html(''+result[i].desc+'');
			buildAlertCandidateData(result[i].subList);
		
	}
	
}




