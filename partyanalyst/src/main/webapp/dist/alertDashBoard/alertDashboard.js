
$(document).ready(function(){
	getLocationLevelAlertCount();
});
$(document).on("click",'.applyBtn',function(){
	getLocationLevelAlertCount();
})
function getLocationLevelAlertCount()
{
	$("#locationLevelId").html('');
	    var fromDate='';
		var toDate='';
		var dateStr = $("#dateRangePickerId").val(); 
		
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		var jsObj = {
			fromDate:fromDate,
			toDate :toDate
			
		          }
		
				$.ajax({
					  type:'GET',
					  url: 'getLocationLevelWiseAlertsAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					
						buildLocationLevelAlert(result,jsObj);
					
				});
}
function buildLocationLevelAlert(result,jsObj){
	
	
	var str='';
	if(result == null || result.length == 0)
	{
		
		$("#locationLevelId").html('No data Available..');
		return;
	}
	str+='<table class="table table-bordered">';
	str+='<thead>';
	str+='<th>Level</th>';
	str+='<th>Total</th>';
	for(var i in result[0].locationsList)
	str+='<td>'+result[0].locationsList[i].name+'</td>';
	str+='</thead>';
	str+='<tbody>';
	str+='<tr>';
	for(var i in result){
		str+='<td>'+result[i].name+'</td>';
		if(result[i].count > 0)
		{
		str+='<td><a class="locationLevelCls" style="cursor:pointer;" attr-levelId="'+result[i].id+'" attr-statusId="0" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+result[i].count+'</a></td>';	
		}
		else
		{
				str+='<td>'+result[i].count+'</td>';
		}
		
		var returnList=result[i].locationsList;
		for(var j in returnList){
			if(returnList[j].count > 0)
			{
			str+='<td><a class="locationLevelCls" style="cursor:pointer;" attr-levelId="'+result[i].id+'" attr-statusId="'+returnList[j].id+'" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+returnList[j].count+'</a></td>';	
			}
			else{
				str+='<td>'+returnList[j].count+'</td>';
			}
			
			
		}
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>'
	$("#locationLevelId").html(str);
}
$(document).on("click",".locationLevelCls",function(){
	var levelId = $(this).attr("attr-levelId");
	var statusId=$(this).attr("attr-statusId");
	var fromDate = $(this).attr("attr-fromDate");
	var toDate=$(this).attr("attr-toDate");
	$("#errorId").html("");
	getLocationLevelAlertData(levelId,statusId,fromDate,toDate);
});

var GlobalAlertData;
function getLocationLevelAlertData(levelId,statusId,fromDate,toDate)
{
	$("#locationLevelDataId").html('');
    GlobalAlertData = [];
		var jsObj =
		     {
			levelId  : levelId,
			statusId :statusId,
			fromDate :"",
			toDate   :"",
			task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'getLocationLevelAlertDataAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
			       GlobalAlertData = result;
					buildAlertData(result,jsObj);
				});
}

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
	str+='<th>No. Of Candidate</th>';
	str+='<th>User Type</th>';
	str+='</thead>';
	var j=0;
	for(var i in result)
	{
		j++;
	str+='<tr>';	
	str+='<td>'+j+'</td>';
	str+='<td><a  class="alertModel" style="cursor:pointer;" attr-id="'+result[i].id+'" attr-des="'+result[i].desc+'">'+result[i].desc+'</a></td>';
	str+='<td>'+result[i].alertType+'</td>';
	str+='<td>'+result[i].severity+'</td>';
	str+='<td>'+result[i].status+'</td>';
	str+='<td>'+result[i].count+'</td>';
	str+='<td>'+result[i].userType+'</td>';
	str+='</tr>';	
	}
	$("#locationLevelDataId").html(str);
}
var GlobalalertId;
var globalAlertName;
$(document).on("click",".alertModel",function(){
	$("#ModalShow").modal('show');
	GlobalalertId = $(this).attr("attr-id");
	showPopUpAlertData(GlobalalertId);
	 globalAlertName=$(this).attr("attr-des");
	 	$("#descriptionTitleId").html(globalAlertName);
	getAlertStatusCommentsTrackingDetails();
	
	
});
function showPopUpAlertData(alertId)
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
			
		}
	}
	
}
function updateAlertStatus()
{
	var comments = $("#commentsId").val();
	var statusId=$("#statusId").val();
	 $('#errorId').html('');
	 
	//var str = '';
	if(comments.length==0||comments=='')
	{
		  $('#errorId').html(' comments required').css("color","red");
		  return; 
	}
	if(statusId==0)
	{
		//alert(2);
	   $('#errorId').html(' Status required').css("color","red"); 
        return;	   
	}
	
	var jsObj =
		     {
		alertId : GlobalalertId,
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


function getAlertStatusCommentsTrackingDetails()
	{
		$("#alertCommentsDiv").html('<img src="images/search.gif" />');
		var jsObj={
	    			alertId:GlobalalertId,
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
			str+='<div class="panel panel-default m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title">'+aptName+' Alert Status Tracking</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
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
		$('html,body').animate({scrollTop: $("#alertCommentsDiv").offset().top}, 'slow');
	}
	



