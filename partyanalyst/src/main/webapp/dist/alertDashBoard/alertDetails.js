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
				 setTimeout(function(){ $("#assignEroorDiv").html("");
					 }, 1000);
					$("#involvedCandidatesDiv").hide();
					$(".membersBlock").html('');
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
						str+='<div class="arrow_box_left">';
						if(result[i].id == 1)
						{
						str+='<p> <span class="text-success"></span> Alert '+result[i].status+' on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';	
						}
						else
						{
							str+='<p>Alert status changed to <span class="text-success"><b>'+result[i].status+'</b></span> on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';
						}
						if(result[i].commentsList != null && result[i].commentsList.length > 0){
							
								str+='<u style="font-size:16px;margin-bottom:10px;"><b>Comments</b></u>';
								str+='<ul class="commentsUlCls">';
							for(var j in result[i].commentsList){
								str+='<li>'+result[i].commentsList[j]+'</li>';	
							}
								str+='</ul>';
						}
					
					str+='</div>';
					str+='</li>';	
				}
			str+='</ul>';
		
		$("#alertCommentsDiv").html(str);
		if(result.length >= 3)
		{
			$("#alertCommentsDiv").mCustomScrollbar({setHeight:'320px'});
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
			   str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;"/>';
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
	$("#involvedCandidatesCnt").html('-' +result.length);
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
						 setTimeout(function(){ $("#errorId").html("");
					 }, 1000);
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
			$(".severityIdColorCls").addClass(''+result[i].severity+'');
			$("#createdDate").html(''+result[i].date+'');
			$("#levelId").html(''+result[i].regionScope+'');
			var location ='';
			if(result[i].regionScope == "STATE")
			{
				location +='<p>S:'+result[i].locationVO.state+'</p>';
			}
			else if(result[i].regionScope == "DISTRICT")
			{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p>';
			}
			
			else if(result[i].regionScope == "CONSTITUENCY")
			{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p><p> C :'+result[i].locationVO.constituencyName+'</p>';
			}
			else if(result[i].regionScope == "MANDAL" || result[i].regionScope == "MUNICIPAL-CORP-GMC" )
			{
				
					if(result[i].locationVO.localEleBodyName != null && result[i].locationVO.localEleBodyName.length > 0)
				{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p><p> C :'+result[i].locationVO.constituencyName+'</p><p> T : '+result[i].locationVO.localEleBodyName+' </p>';	
				}
				else{
					location +='<p>S:'+result[i].locationVO.state+' </p><p>D : '+result[i].locationVO.districtName+' </p><p>C :'+result[i].locationVO.constituencyName+'</p><p> M : '+result[i].locationVO.tehsilName+'</p>';
				}
			}
			
			else if(result[i].regionScope == "VILLAGE" || result[i].regionScope == "WARD" )
			{
			
				if(result[i].locationVO.localEleBodyName != null && result[i].locationVO.localEleBodyName.length > 0)
				{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p><p> C :'+result[i].locationVO.constituencyName+'</p><p> T : '+result[i].locationVO.localEleBodyName+' </p><p>W : '+result[i].locationVO.wardName+'</p>';	
				}
				else{
					location +='<p>S:'+result[i].locationVO.state+' </p><p>D : '+result[i].locationVO.districtName+' </p><p>C :'+result[i].locationVO.constituencyName+'</p><p> M : '+result[i].locationVO.tehsilName+'</p><p> P : '+result[i].locationVO.villageName+'</p>';
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
function getConfirmation(tdpCadreId){
               var retVal = confirm("Are you sure want to delete?");
			
			    if(retVal == true){
				   deleteAlertAssignedCandidates(tdpCadreId);
                  return true;
               }
               else{
                  return false;
               }
            }








