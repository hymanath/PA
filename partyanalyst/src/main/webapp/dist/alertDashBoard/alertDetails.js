getAlertData();
getAlertStatusCommentsTrackingDetails();
getAlertAssignedCandidates(alertId);
$(document).on("click",".assignModel",function(){
	clearAssignFields();
	$("#ModalShow").modal('show');
	
});
function clearAssignFields()
{
commontdpCadreIds = [];
$("#involvedCandidatesDiv").hide();
 $(".membersBlock").html('');
 $("#apptmemberDetailsDiv").html('');
	$("#advanceSearchTypeId").val(0);
	  var select = new Dropkick("#advanceSearchTypeId");
		select.refresh();
	showHideBySearchType();//Clear Fields	
}
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
					getAlertAssignedCandidates(alertId);
			 })
	}
function getAlertData()
{
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
		  buildAlertCommentsForTracking(result);
		});
		
	}
	function buildAlertCommentsForTracking(result){
		
		var str='';
		
		if(result == null || result.length == 0)
		{
			$("#alertCommentsDiv").html('No Data Available');
			return;
		}
			
			str+='<ul class="alertStatusTracking">';
				for(var i in result){
					str+='<li>';
						str+='<div class="arrow_box">';
						if(result[i].id == 1)
							str+='<p> <span class="text-success"></span> Alert Created on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';	
						else
							str+='<p>Alert status changed to <span class="text-success"><b>'+result[i].status+'</b></span> on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';
						if(result[i].commentsList != null && result[i].commentsList.length > 0 && result[i].commentsList[0].length > 0 && result[i].id != 1){
								str+='<u style="font-size:15px;">Comments</u>';
							for(var j in result[i].commentsList){
					
								str+='<p>'+result[i].commentsList[j]+'</p>';	
							}
						}	
				
						str+='</div>';
					str+='</li>';	
				}
		str+='</ul>';
		
		$("#alertCommentsDiv").html(str);
		if(result.length >= 3)
		{
			$("#alertCommentsDiv .panel-body").mCustomScrollbar({setHeight:'320px'});
		}
		
		
		//$('.modal-content').animate({scrollTop: $("#alertCommentsDiv").offset().top}, 'slow');
	}
$(document).on("click",".locationLevelCls",function(){
	var levelId = $(this).attr("attr-levelId");
	var statusId=$(this).attr("attr-statusId");
	var fromDate = $(this).attr("attr-fromDate");
	var toDate=$(this).attr("attr-toDate");
	$("#errorId").html("");
	getLocationLevelAlertData(levelId,statusId,fromDate,toDate);
});
/*
function getAlertCandidatesData(alertId)
{
	alert('aa')
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
}*/
function buildAlertCandidateData(result)
{
	
	if(result == null || result.length == 0)
	{
		$("#alertCandidateDataId").html('No Involved Members..');
		return;
	}
	var str='';
	for(var i in result)
	{
	str+='<li>';
		str+='<div class="media">';
			str+='<div class="media-left">';
			   str+=' <img src="'+result[i].image+'"  onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;"/>';
		   str+=' </div>';
		   str+=' <div class="media-body">';
			   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
			  str+='  <p>'+result[i].mobileNo+'</p>';
			  str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
			  if(result[i].impactId == 1)
			  {
				 str+=' <span class="label label-success">+ Ve</span>'; 
			  }else{
				  str+=' <span class="label label-danger">- Ve</span>';
			  }
			  
		  str+='  </div>';
		str+='</div>';
   str+=' </li>';

	}
	$("#alertCandidateDataId").html(str);
	if(result.length > 3)
	{
		$("#alertCandidateDataId").mCustomScrollbar({setHeight:'250px'});
	}
	
}
function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }

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
	$("#updateAlertajaxImg").html('<img src="images/search.gif"/>');
	var jsObj =
		     {
		alertId : alertId,
		alertStatusId :statusId,
		comments:comments,
			task : ""
		      }
			$.ajax({
					  type:'POST',
					  url: 'updateAlertStatusAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
				  $("#commentsId").val('');
				  $("#updateAlertajaxImg").html('');
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
			$("#alertStatus").html(''+result[i].status+'');
			buildAlertCandidateData(result[i].subList);
		
	}
	
}
function getAlertAssignedCandidates(alertId)
{
	$("#alertAssignedCandidateDataId").html('<img src="images/search.gif" />');
    GlobalAlertData = [];
		var jsObj =
		     {
			alertId  : alertId,
			task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'getAlertAssignedCandidatesAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
			      buildAlertAssignedCandidateData(result);
				});
}
function buildAlertAssignedCandidateData(result)
{
	
	if(result == null || result.length == 0)
	{
	 $("#alertAssignedCandidateDataId").html('No Assigned Candidates..');
	 $("#assignCandidatesCnt").html('0');
		return;
	}
	var str='';
	for(var i in result)
				{
	for(var j in result[i].subList)
	{
		str+='<div class="media" style="margin-top:5px;border:1px solid #ddd;">';
		str+='<div class="media-left">';
        str+='<img src="'+result[i].image+'" onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;"/>';
       str+=' </div>';
        str+=' <div class="media-body" >';
		str+='<p class="text-capital"><b>'+result[i].subList[j].name+'</b></p>';
        //str+=' <p class="text-capital"><i><b>-Constituency Incharge</b></i></p>';
        str+=' <p>'+result[i].subList[j].mobileNo+'</p>';
        str+='<p>'+result[i].subList[j].locationVO.constituencyName+'</p>';
		 str+=' </div>';
		 str+=' </div>';
	}
	$("#assignCandidatesCnt").html(result[0].subList.length);
	}
	$("#alertAssignedCandidateDataId").html(str);
	/*str+='<div  style="border:1px solid #ddd;padding:8px;margin-top:5px;" class="media">';
	str+='<div class="media-left">';
	str+='<img src="'+result[i].image+' "  onerror="setDefaultImage(this);" class="media-object img-center img-responsive  thumbnailSearch thumbnail" alt="Image" style="width: 60px !important; height: 60px  !important;"/>';
	str+='</div>';
	str+='<div class="media-body">';
	
	str+='<p><b>Name </b>:'+result[i].subList[j].name+' </p>';	
	str+='<p><b>State </b>:'+result[i].subList[j].locationVO.state+' <b>District </b>: '+result[i].subList[j].locationVO.districtName+'<br/><b>Constituency </b>:'+result[i].subList[j].locationVO.constituencyName+' <b>Mandel </b>:'+result[i].subList[j].locationVO.tehsilName+' <b>Village </b>:'+result[i].subList[j].locationVO.villageName+'</p>';
	str+='</div>';
	str+='</div>';
	}
	}
	$("#alertAssignedCandidateDataId").html(str);
	
	$("#assignCandidatesCnt").html(result[0].subList.length);
	if(result[0].subList.length > 3)
	{
		$("#alertAssignedCandidateDataId").mCustomScrollbar({setHeight:'290px'});
	}*/
	
}






