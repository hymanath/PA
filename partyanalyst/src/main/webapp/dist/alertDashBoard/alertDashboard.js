
$(document).ready(function(){
	getLocationLevelAlertCount();
});
$(document).on("click",'.applyBtn',function(){
	$("#locationLevelDataId").html('');
	getLocationLevelAlertCount();
})
function getLocationLevelAlertCount()
{
	$("#locationLevelId").html('<img src="images/search.gif" />');
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

/*function buildLocationLevelAlert(result,jsObj){
	
	
	var str='';
	if(result == null || result.length == 0)
	{
		
		$("#locationLevelId").html('No data Available..');
		return;
	}
	str+='<table class="table table-bordered tableDashboard">';
	for(var i in result){
    str+='<tr>';
    str+=' <td>';
    str+='<h2>500</h2>';
    str+='<h4 class="text-capital textColor_333">total alerts</h4>';
     str+='</td>';
     str+=' <td>';
     str+=' <h2>500</h2>';
     str+='<h4 class="text-capital textColor_333">low</h4>';
     str+=' </td>';
     str+='<td>';
     str+='<h2>500</h2>';
     str+='<h4 class="text-capital textColor_333">medium</h4>';
     str+='</td>';
     str+=' <td>';
     str+=' <h2>500</h2>';
     str+=' <h4 class="text-capital textColor_333">high</h4>';
     str+=' </td>';
     str+=' <td>';
     str+=' <h2>500</h2>';
     str+='<h4 class="text-capital textColor_333">critical</h4>';
     str+=' </td>';
     str+=' </tr>';
      str+='<tr>';
     str+=' <td>';
     str+=' <p>100</p>';
     str+=' <p class="text-capital">State</p>';
     str+=' </td>';
      str+='<td>30</td>';
      str+='  <td>30</td>';
      str+='<td>30</td>';
      str+='<td>30</td>';
      str+=' </tr>';
      str+=' <tr>';
      str+='<td>';
      str+=' <p>100</p>';
      str+='<p class="text-capital">District</p>';
      str+=' </td>';
      str+='<td>30</td>';
      str+='<td>30</td>';
      str+='<td>30</td>';
      str+=' <td>30</td>';
      str+=' </tr>';
      str+='<tr>';
      str+='<td>';
      str+=' <p>100</p>';
      str+=' <p class="text-capital">constituency</p>';
      str+=' </td>';
      str+='<td>30</td>';
      str+='<td>30</td>';
      str+=' <td>30</td>';
      str+='<td>30</td>';
      str+=' </tr>';
	}
      str+='</table>';
	$("#locationLevelId").html(str);
}*/
function buildLocationLevelAlert(result,jsObj){
	
	
	var str='';
	if(result == null || result.length == 0)
	{
		
		$("#locationLevelId").html('No data Available..');
		return;
	}
	str+='<table class="table table-bordered tableDashboard">';
	str+='<tbody>';
	str+='<tr>';
	 str+=' <td>';
	 var totalAlerts = 0;
	 for(var i in result)
	 {
		 totalAlerts = totalAlerts + result[i].count;
	 }
	 if(totalAlerts > 0)
    str+='<p><a title="Click here to View Alert Details" class="locationLevelCls" style="cursor:pointer;" attr-levelId="0" attr-statusId="0" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+totalAlerts+'</a></p>';
	else
	str+='<p>'+totalAlerts+'</p>';
    str+='<p class="text-capital textColor_333">total alerts</p>';
     str+='</td>';
	for(var i in result[0].locationsList)
	{
		var totalCnt = 0;
		str+='<td>';
		for(var j in result)
		{
			var returnList=result[j].locationsList;
			for(var k in returnList){
				if(result[0].locationsList[i].id == returnList[k].id)
				totalCnt = totalCnt + returnList[k].count;
			}
		}
			if(totalCnt > 0)
			str+='<p><a title="Click here to View Alert Details" class="locationLevelCls" style="cursor:pointer;" attr-levelId="0" attr-statusId="'+result[0].locationsList[i].id+'" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+totalCnt+'</a></p>';
			else
			{
					str+='<p>'+totalCnt+'</p>';
			}
		str+='<p class="text-capital textColor_333">'+result[0].locationsList[i].name+'</p>';
		str+='</td>';
	}
	
	str+='</tr>';
	str+='<tr>';
	for(var i in result){
		
		str+='<td>';
		if(result[i].count > 0)
		{
		 str+=' <p><a title="Click here to View Alert Details" class="locationLevelCls" style="cursor:pointer;" attr-levelId="'+result[i].id+'" attr-statusId="0" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+result[i].count+'</a></p>';
		}
		else
		{
			 str+='<p>'+result[i].count+'</p>';
		}
		str+='<p class="text-capital textColor_333">'+result[i].name+'</p>';
		str+='</td>';
		var returnList=result[i].locationsList;
		for(var j in returnList){
			if(returnList[j].count > 0)
			{
			str+='<td><p><a title="Click here to View Alert Details" class="locationLevelCls" style="cursor:pointer;" attr-levelId="'+result[i].id+'" attr-statusId="'+returnList[j].id+'" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+returnList[j].count+'</a></p></td>';
			}
			else{
				str+='<td style="vertical-align:middle"><p>'+returnList[j].count+'</p></td>';
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
	$("#locationLevelDataId").html('<img src="images/search.gif" />');
    GlobalAlertData = [];
		var jsObj =
		     {
			levelId  : levelId,
			statusId :statusId,
			fromDate :fromDate,
			toDate   :toDate,
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
	else if(jsObj.levelId == 3)
	{
		Level = "DISTRICT";
	}
	else if(jsObj.levelId == 4)
	{
		Level = "CONSTITUENCY";
	}
	else if(jsObj.levelId == 5)
	{
		Level = "MANDAL";
	}
	else if(jsObj.levelId == 6)
	{
		Level = "VILLAGE";
	}
	else
	{
		Level = "";
	}
	var str='';
	
	if(jsObj.statusId > 0)
	{
		if(Level.length > 0)
		{
			str+='<h4 class="text-success text-capital m_top10">'+Level+ " Wise "+result[0].status+' Alert Details</h4>';
		}
		
		else
		{
			str+='<h4 class="text-success text-capital m_top10"> '+result[0].status+' Alert Details</h4>';
		}
	}
	
	else
	{
		if(Level.length > 0)
		{
			str+='<h4 class="text-success text-capital m_top10">'+Level+' Wise  Alert Details</h4>';
		}
		else
		{
			str+='<h4 class="text-success text-capital m_top10"> Alert Details</h4>';
		}
	}
	
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered bg_ff" id="alertDataTableId">';
	str+='<thead>';
	//str+='<th>S.NO</th>';
	//str+='<th>Desc</th>';
	str+='<th>Alert Type</th>';
	str+='<th>Status</th>';
	str+='<th>Involved No Of Candidates</th>';
	str+='<th>Notified Date</th>';
	str+='<th>Information Source</th>';
	str+='<th>Severity</th>';
	if(jsObj.levelId == 2)
	{
	str+='<th>STATE</th>';	
	}
	else
	{
		str+='<th>LOCATION</th>';
	}
	str+='<th></th>';	
	str+='</thead>';
	str+='</tbody>';
	var j=0;
	for(var i in result)
	{
		j++;
	str+='<tr>';	
	//str+='<td>'+j+'</td>';
	//str+='<td><a target="_blank" title="Click here to View Alert Details" class="alertModel" style="cursor:pointer;" attr-id="'+result[i].id+'" attr-des="'+result[i].desc+' ">'+result[i].desc+'</a></td>';
	str+='<td>'+result[i].alertType+'</td>';
	str+='<td>'+result[i].status+'</td>';
	str+='<td>'+result[i].count+'</td>';
	str+='<td>'+result[i].date+'</td>';
	str+='<td>'+result[i].userType+'</td>';
	str+='<td><span class="circle '+result[i].severity+'"></span>'+result[i].severity+'</td>';
	//str+='<td><a  class="alertCandidate" style="cursor:pointer;" attr-id="'+result[i].id+'" attr-des="'+result[i].desc+'">'+result[i].count+'</a></td>';
	if(result[i].regionScopeId== 2)
			{
				str+='<td>'+result[i].locationVO.state+'</td>';
			}
			else
			{
			str+='<td>'+result[i].locationVO.districtName+'</td>';	
			}
	//str+='<td><button class="btn btn-success">VIEW</button></td>';
	str+='<td><i class="glyphicon glyphicon-eye-open alertModel"  target="_blank" title="Click here to View Alert Details" style="cursor:pointer;" attr-id="'+result[i].id+'" attr-des="'+result[i].desc+' "></i>';
	//<button class="btn btn-success alertModel" target="_blank" title="Click here to View Alert Details" style="cursor:pointer;" attr-id="'+result[i].id+'" attr-des="'+result[i].desc+' ">VIEW</button></td>';
	
	str+='</tr>';	
	}
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	$("#locationLevelDataId").html(str);
	$("#alertDataTableId").dataTable(); 
}
var GlobalalertId;
var globalAlertName;
$(document).on("click",".alertModel",function(){
GlobalalertId = $(this).attr("attr-id");
	/*$("#ModalShow").modal('show');
	showPopUpAlertData(GlobalalertId);
	 globalAlertName=$(this).attr("attr-des");
	 	$("#descriptionTitleId").html(globalAlertName);
	getAlertStatusCommentsTrackingDetails();*/
	//window.location.href = "alertDetailsAction.action?alertId="+GlobalalertId+"";
	window.open("alertDetailsAction.action?alertId="+GlobalalertId+"", '_blank');
});

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
	str+='<div  style="border-radius:50% solid #ddd;padding:8px;margin-top:5px;" class="media">';
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
	/*str+='<div class="panel panel-default panelAlert">';
		str+='<div class="panel-heading">';
			str+='<h4 style="text-transform: uppercase;" class="text-success panel-title">Candidate Details</h4>';	
		str+='</div>';
		str+='<div class="panel-body">';
			str+='<table class="table table-bordered">';
				str+='<thead>';
				str+='<th>Name</th>';
				str+='<th>Location</th>';
				
				str+='</thead>';
				var j=0;
				for(var i in result)
				{
					j++;
				str+='<tr>';	
				str+='<td>'+result[i].name+'</td>';
				var location ='';
				if(result[i].locationVO.localEleBodyName != null && result[i].locationVO.localEleBodyName.length > 0)
							{
							location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p><p> C :'+result[i].locationVO.constituencyName+'</p><p> T: '+result[i].locationVO.localEleBodyName+' </p><p>W: '+result[i].locationVO.wardName+'</p>';	
							}
							else{
								location +='<p>S:'+result[i].locationVO.state+' </p><p>D : '+result[i].locationVO.districtName+' </p><p>C :'+result[i].locationVO.constituencyName+'</p><p> M: '+result[i].locationVO.tehsilName+'</p><p> V : '+result[i].locationVO.villageName+'</p>';
							}
				str+='<td>'+location+'</td>';
				str+='</tr>';	
				}
				str+='</table>';
		str+='</div>';
	str+='</div>';*/
	
	
	$("#alertCandidateDataId").html(str);
}

	function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }
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
			buildAlertCandidateData(GlobalAlertData[i].subList);
		}
	}
	
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
			str+='<div class="panel panel-default panelAlert m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-success">'+aptName+' Alert Status Tracking</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
			str+='<ul class="alertStatusTracking">';
				for(var i in result){
					str+='<li>';
						str+='<div class="arrow_box_left">';
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
	
	



