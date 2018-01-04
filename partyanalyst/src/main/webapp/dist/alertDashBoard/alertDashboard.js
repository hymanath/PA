
$(document).ready(function(){
	getLocationLevelAlertCount();
	setTimeout(function(){ 
		var levelId = 0;
		var levelValue = 0;
		$('.stateCls').each(function(){
			if($(this).hasClass("active"))
				levelValue = $(this).attr("attr_state_id");
		});
		  
		var statusId=0;
		 var fromDate='';
		 var toDate='';  
		 var dateStr = $("#dateRangePickerId").val(); 
			if(dateStr !=null && dateStr.length>0){
				fromDate = dateStr.split("-")[0];
				toDate = dateStr.split("-")[1];
			}
		var	categoryId =0;
		$("#errorId").html("");
		
		getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,"totalBlock");
	}, 2000);
});
/* $(document).on("click",'.applyBtn',function(){
	$("#locationLevelDataId").html('');
	getLocationLevelAlertCount();
	var levelId = 0;
	var levelValue = 0;
	$('.stateCls').each(function(){
		if($(this).is(":checked"))
			levelValue = $(this).attr("attr_state_id");
	});
	
	var statusId=0;
	 var fromDate='';
	 var toDate='';
	 var dateStr = $("#dateRangePickerId").val(); 
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
	var	categoryId =0;
	$("#errorId").html("");  
	
	getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,"totalBlock");	
}) */
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
		str+='<p><a title="Click here to View Alert Details" class="locationLevelCls" style="cursor:pointer;font-size: 26px;" attr-levelId="0" attr-statusId="0" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+totalAlerts+'</a></p>';
	else
		str+='<p>'+totalAlerts+'</p>';
    str+='<h5 class="text-capital textColor_333">total alerts</h5>';
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
			str+='<p><a title="Click here to View Alert Details" class="locationLevelCls" style="cursor:pointer;font-size: 26px;" attr-levelId="0" attr-statusId="'+result[0].locationsList[i].id+'" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+totalCnt+'</a></p>';
			else
			{
					str+='<p>'+totalCnt+'</p>';
			}
		str+='<h5 class="text-capital textColor_333">'+result[0].locationsList[i].name+'</h5>';
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
			str+='<td><p><a title="Click here to View Alert Details" class="locationLevelCls" style="cursor:pointer;font-size: 26px;" attr-levelId="'+result[i].id+'" attr-statusId="'+returnList[j].id+'" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+returnList[j].count+'</a></p></td>';
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

$(document).on("click",".belowLocationCls",function(){
	$("#verificationPosId").attr("attr_get_pos_id","nonVerify");
	$("#verificationDateRangePickerId").val(' ');
	$("#alertVerificationStatusId").val(0);
	$("#alertVerificationStatusId").trigger("chosen:updated");
	var id = $(this).attr("attr_id");
	var category = $(this).attr("attr_category_id");
	initSelectBoxForSecondPos(id,category);
	
	
	var levelId = $(this).attr("attr_levlid");
	var locationBlock = $(this).attr("attr_search_Location");
	var levelValue = $(this).attr("attr_levlvalue");
		
	var statusId=$(this).attr("attr_id");
	var fromDate = '';
	var toDate='';
	var dateStr = $("#dateRangePickerId").val(); 
	
	if(dateStr !=null && dateStr.length>0){
		fromDate = dateStr.split("-")[0];
		toDate = dateStr.split("-")[1];
	}
	var	categoryId =$(this).attr("attr_category_id");;
	$("#errorId").html("");
	
	getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,locationBlock);
	
});

$(document).on("click",".locationLevelCls",function(){
	var levelId = $(this).attr("attr-levelId");
	var locationBlock = $(this).attr("attr_search_Location");
	var levelValue = 0;
	$('.stateCls').each(function(){
		if($(this).hasClass("active"))
			levelValue = $(this).attr("attr_state_id");
	});
	
	var statusId=$(this).attr("attr-statusId");
	var fromDate = $(this).attr("attr-fromDate");
	var toDate=$(this).attr("attr-toDate");
	var	categoryId =0;
	$("#errorId").html("");
	
	getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,locationBlock);
});
$(document).on("click",".headerWiseDataCls",function(){
	$("#verificationPosId").attr("attr_get_pos_id","nonVerify");
	$("#verificationDateRangePickerId").val(' ');
	$("#alertVerificationStatusId").val(0);
	$("#alertVerificationStatusId").trigger("chosen:updated");
	var levelId = $(this).attr("attr_levlId");
	var levelValue = 0;
	var locationBlock = $(this).attr("attr_search_Location");  
	$('.stateCls').each(function(){
		if($(this).hasClass("active"))   
			levelValue = $(this).attr("attr_state_id");
	});
	
	var statusId=$(this).attr("attr_id");
	var categoryId=$(this).attr("attr_category_id");
	var fromDate='';
	var toDate='';
	var dateStr = $("#dateRangePickerId").val(); 
	
	if(dateStr !=null && dateStr.length>0){
		fromDate = dateStr.split("-")[0];
		toDate = dateStr.split("-")[1];
	}
	
	$("#errorId").html("");
	
	getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,locationBlock);
	//swadhin
	var postion = $(this).attr("attr_position");
	var id = $(this).attr("attr_id");
	if(postion == "first"){
		initSelectBoxForFirstPos(id);
	}
	if(postion == "second"){
		var category = $(this).attr("attr_category_id");
		initSelectBoxForSecondPos(id,category);
	}
	if(postion == "third"){
		initSelectBoxForFirstPos(id);
	}
});
function initSelectBoxForFirstPos(statusId){         
	$("#alertCategoryId").val(0);
	$("#alertCategoryId").trigger("chosen:updated");
	$("#alertStatusId").val(statusId);
	$("#alertStatusId").trigger("chosen:updated"); 
}  
function initSelectBoxForSecondPos(statusId,category){    
	$("#alertStatusId").val(statusId);
	$("#alertStatusId").trigger("chosen:updated");
	$("#alertCategoryId").val(category);
	$("#alertCategoryId").trigger("chosen:updated");   
}

//999
var GlobalAlertData;
function getLocationLevelAlertData(levelValue,levelId,statusId,fromDate,toDate,categoryId,locationBlock)
{
	 $("#assignedCadreId").val(0).trigger('chosen:updated'); 
	 $('html, body').animate({
        scrollTop: $('#locationLevelDataId').offset().top
     }, 2000);
	
	$("#locationLevelDataId").html('<img src="images/search.gif" />');
	var assignId = $('#assignedCadreId').val();
	var alertTpeId = $('#alertTypeId').val();
	if(assignId== null || assignId.length==0)
		assignId=0;
	if(alertTpeId== null || alertTpeId.length==0)
		alertTpeId=0;
	
    GlobalAlertData = [];
	var impactScopeId=0;
	if(locationBlock == "locationBlock"){
		impactScopeId=levelId;
		levelId=0;
	}
		var jsObj =
		     {
				alertTypeId:alertTpeId,
				levelId  : levelId,
				statusId :statusId,
				fromDate :fromDate,
				toDate   :toDate,
				levelValue:levelValue,
				categoryId:categoryId,
				assignId:assignId,
				impactScopeId:impactScopeId,
				task : locationBlock
		      }
			$.ajax({
					  type:'GET',
					  url: 'getLocationLevelAlertDataAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
			       GlobalAlertData = result;
					buildAlertData(result,jsObj);
					getDistrictsForReferPopup('');
				});
}



var GlobalalertId;
var globalAlertName;
$(document).on("click",".alertModel",function(){
	GlobalalertId = $(this).attr("attr-id");
	//window.open("alertDetailsAction.action?alertId="+GlobalalertId+"", '_blank');
	window.open("http://mytdp.com/alertDetailsAction.action?alertId="+GlobalalertId+"", '_blank');
});
$(document).on("click",".alertEditModel",function(){
	GlobalalertId = $(this).attr("attr-id");
	window.open("createAlertAction.action?alertId="+GlobalalertId+"", '_blank');
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
	
	
	function getLocationFilterAlertData()
	{	    
		var clickPosVal = $("#verificationPosId").attr("attr_get_pos_id");
		var actionTypeStatusId = $('#alertVerificationStatusId').val();
		var dateStr2 = $("#verificationDateRangePickerId").val();
		if(clickPosVal == "verification" && actionTypeStatusId > 0){
			$("#locationLevelDataId").html('<img src="images/search.gif" />');
			var fromDate='';
			var toDate='';
			var dateStr = $("#dateRangePickerId").val(); 
			if(dateStr !=null && dateStr.length>0){
				fromDate = dateStr.split("-")[0];
				toDate = dateStr.split("-")[1];  
			}
			
			var levelValue = 0;
			$('.stateCls').each(function(){
				if($(this).hasClass("active"))   
					levelValue = $(this).attr("attr_state_id");
			});
			var alertTypeId = $("#alertTypeId").val();
			var alertCategoryId = $('#alertCategoryId').val();
			
			var statusId = $('#alertStatusId').val();
			var actionTypeId = 1;
			var impactScopeId = 0;      
			var fromDate2='';
			var toDate2='';
			
			if(dateStr2 != null && dateStr2.length>1){  
				fromDate2 = dateStr2.split("-")[0];
				toDate2 = dateStr2.split("-")[1];
			}
			getAllAlertsWithoutFilter(alertTypeId,alertCategoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId,statusId,fromDate2,toDate2);
		}else if(actionTypeStatusId > 0 || dateStr2.length > 1){
			$("#locationLevelDataId").html('<img src="images/search.gif" />');
			var fromDate='';
			var toDate='';
			var dateStr = $("#dateRangePickerId").val();    
			if(dateStr !=null && dateStr.length>0){
				fromDate = dateStr.split("-")[0];
				toDate = dateStr.split("-")[1];
			}
			
			var levelValue = 0;
			$('.stateCls').each(function(){
				if($(this).hasClass("active"))   
					levelValue = $(this).attr("attr_state_id");
			});
			var alertTypeId = $("#alertTypeId").val();
			var alertCategoryId = $('#alertCategoryId').val();
			
			var statusId = $('#alertStatusId').val();
			var actionTypeId = 1;
			var impactScopeId = 0;      
			var fromDate2='';
			var toDate2='';
			
			if(dateStr2 != null && dateStr2.length>1){  
				fromDate2 = dateStr2.split("-")[0];    
				toDate2 = dateStr2.split("-")[1];
			}
			getAllAlertsWithoutFilter(alertTypeId,alertCategoryId,actionTypeId,actionTypeStatusId,levelValue,fromDate,toDate,impactScopeId,statusId,fromDate2,toDate2);
		}else{
			$(".filterBlockDiv").hide();
			$("#locationLevelDataId").html('<img src="images/search.gif" />');
			GlobalAlertData = [];
			var stateId = $("#stateId").val();
			var districtId = $("#referdistrictId").val();
			var constituencyId = $("#referconstituencyId").val();
			var mandalId = $("#refermandalNameId").val();
			var panchayatId = $("#referpanchayatId").val();
			var mandalType = $("#refermandalNameId option:selected").text();
		
			if(mandalType.indexOf("Mandal") == -1){
				mandalType = "localbody";
			}else{
				mandalType = "mandal";
			}
			var assignedCadreId =  $("#assignedCadreId").val();
			if(assignedCadreId.length == 0)
				assignedCadreId =0;
			var involvedCadreId =  $("#involvedCadreId").val();
			if(involvedCadreId == null || involvedCadreId.length == 0)
				involvedCadreId = 0;
			var benefitId =  $("#benefitId").val();
			if(benefitId == null || benefitId.length == 0)
				benefitId = 0;       
			var fromDate='';
			var toDate='';
			var dateStr = $("#dateRangePickerId").val(); 
			if(dateStr !=null && dateStr.length>0){
				fromDate = dateStr.split("-")[0];
				toDate = dateStr.split("-")[1];
			}
			var alertTpeId = $('#alertTypeId').val();	
			var alertCategoryId = $('#alertCategoryId').val();	
			var statusId = $('#alertStatusId').val();	
			if(alertTpeId== null || alertTpeId.length==0)
				alertTpeId=0;
			
			var jsObj =
				 {
					statusId:statusId,
					alertTypeId:alertTpeId,
					stateId  : stateId,
					districtId :districtId,
					constituencyId :constituencyId,
					mandalId   :mandalId,
					panchayatId:panchayatId,
					mandalType:mandalType,
					fromDate:fromDate,
					toDate:toDate,
					assignedCadreId:assignedCadreId,
					involvedCadreId:involvedCadreId,                 
					impactId:benefitId,         
					categoryId:alertCategoryId,
					actionTypeStatusId : 0,            
					task : "",  
					fromDate2 : "",
					toDate2 : "",
					radioVal : '',
					verificationUserType :"programCommittee"
			}
			$.ajax({      
				type:'GET',
				url: 'getLocationFilterAlertDataAction.action',
				data: {task :JSON.stringify(jsObj)}
			}).done(function(result){
				GlobalAlertData = result;
				buildAlertData(result,jsObj);
			});
		}            
	}
	function getAdvanceLocationFilterAlertData(){   
		var clickPosVal = $("#verificationPosId").attr("attr_get_pos_id");
		var actionTypeStatusId = $('#alertVerificationStatusId').val();
		var dateStr2 = $("#verificationDateRangePickerId").val();
		if(clickPosVal == "verification" && actionTypeStatusId > 0){
			$(".filterBlockDiv").hide();
			$("#locationLevelDataId").html('<img src="images/search.gif" />');
			var stateId = $("#stateId").val();
			var districtId = $("#referdistrictId").val();
			var constituencyId = $("#referconstituencyId").val();
			var mandalId = $("#refermandalNameId").val();
			var panchayatId = $("#referpanchayatId").val();
			var mandalType = $("#refermandalNameId option:selected").text();
			
			if(mandalType.indexOf("Mandal") == -1){
				mandalType = "localbody";
			}
			else{
				mandalType = "mandal";
			}
			var assignedCadreId =  $("#assignedCadreId").val();
			if(assignedCadreId.length == 0)
				assignedCadreId =0;
			var involvedCadreId =  $("#involvedCadreId").val();
			if(involvedCadreId == null || involvedCadreId.length == 0)
				involvedCadreId = 0;
			var benefitId =  $("#benefitId").val();
			if(benefitId == null || benefitId.length == 0)
				benefitId = 0;
			var fromDate='';
			var toDate='';
			var dateStr = $("#dateRangePickerId").val(); 
			if(dateStr !=null && dateStr.length>0){
				fromDate = dateStr.split("-")[0];
				toDate = dateStr.split("-")[1];
			}
			var alertTpeId = $('#alertTypeId').val();	
			var alertCategoryId = $('#alertCategoryId').val();	
			
			var statusId = $('#alertStatusId').val();
			var actionTypeId = 1;
			var impactScopeId = 0;      
			var fromDat2='';
			var toDat2='';
			
			if(dateStr2 != null && dateStr2.length>1){  
				fromDat2 = dateStr2.split("-")[0];
				toDat2 = dateStr2.split("-")[1];
			}
			if(alertTpeId== null || alertTpeId.length==0)
				alertTpeId=0;
			var jsObj =
				{
					statusId:statusId,
					alertTypeId:alertTpeId,  
					stateId  : stateId,
					districtId :districtId,
					constituencyId :constituencyId,
					mandalId   :mandalId,
					panchayatId:panchayatId,
					mandalType:mandalType,
					fromDate:fromDate,
					toDate:toDate,
					assignedCadreId:assignedCadreId,
					involvedCadreId:involvedCadreId,                 
					impactId:benefitId,   
					categoryId:alertCategoryId,
					actionTypeStatusId:actionTypeStatusId,        
					task : "verification",
					fromDate2 : fromDat2,
					toDate2 : toDat2,
					radioVal : '',
					verificationUserType :"programCommittee"
					
				}
			$.ajax({
				type:'GET',
				url: 'getLocationFilterAlertDataAction.action',//imp
				data: {task :JSON.stringify(jsObj)}
			}).done(function(result){
				buildAlertData(result,jsObj);
			});
		}else if(actionTypeStatusId > 0 || dateStr2.length > 1){
			$(".filterBlockDiv").hide();
			$("#locationLevelDataId").html('<img src="images/search.gif" />');
			var stateId = $("#stateId").val();
			var districtId = $("#referdistrictId").val();
			var constituencyId = $("#referconstituencyId").val();
			var mandalId = $("#refermandalNameId").val();
			var panchayatId = $("#referpanchayatId").val();
			var mandalType = $("#refermandalNameId option:selected").text();
			
			if(mandalType.indexOf("Mandal") == -1){
				mandalType = "localbody";
			}
			else{
				mandalType = "mandal";
			}
			var assignedCadreId =  $("#assignedCadreId").val();
			if(assignedCadreId.length == 0)
				assignedCadreId =0;
			var involvedCadreId =  $("#involvedCadreId").val();
			if(involvedCadreId == null || involvedCadreId.length == 0)
				involvedCadreId = 0;
			var benefitId =  $("#benefitId").val();
			if(benefitId == null || benefitId.length == 0)
				benefitId = 0;
			var fromDate='';
			var toDate='';
			var dateStr = $("#dateRangePickerId").val(); 
			if(dateStr !=null && dateStr.length>0){
				fromDate = dateStr.split("-")[0];
				toDate = dateStr.split("-")[1];
			}
			var alertTpeId = $('#alertTypeId').val();	
			var alertCategoryId = $('#alertCategoryId').val();	
			
			var statusId = $('#alertStatusId').val();
			var actionTypeId = 1;
			var impactScopeId = 0;      
			var fromDat2='';
			var toDat2='';
			
			if(dateStr2 != null && dateStr2.length>1){  
				fromDat2 = dateStr2.split("-")[0];
				toDat2 = dateStr2.split("-")[1];
			}
			if(alertTpeId== null || alertTpeId.length==0)
				alertTpeId=0;
			var jsObj =
				{
					statusId:statusId,
					alertTypeId:alertTpeId,  
					stateId  : stateId,
					districtId :districtId,
					constituencyId :constituencyId,
					mandalId   :mandalId,
					panchayatId:panchayatId,
					mandalType:mandalType,
					fromDate:fromDate,
					toDate:toDate,
					assignedCadreId:assignedCadreId,
					involvedCadreId:involvedCadreId,                 
					impactId:benefitId,   
					categoryId:alertCategoryId,
					actionTypeStatusId:actionTypeStatusId,        
					task : "verification",
					fromDate2 : fromDat2,
					toDate2 : toDat2,
					radioVal : '',
					verificationUserType :"programCommittee"
				}
			$.ajax({
				type:'GET',
				url: 'getLocationFilterAlertDataAction.action',//imp
				data: {task :JSON.stringify(jsObj)}
			}).done(function(result){
				buildAlertData(result,jsObj);
			});
		}else{
			$(".filterBlockDiv").hide();
			$("#locationLevelDataId").html('<img src="images/search.gif" />');
			GlobalAlertData = [];   
			var stateId = $("#stateId").val();
			var districtId = $("#referdistrictId").val();
			var constituencyId = $("#referconstituencyId").val();
			var mandalId = $("#refermandalNameId").val();
			var panchayatId = $("#referpanchayatId").val();
			var mandalType = $("#refermandalNameId option:selected").text();
		
			if(mandalType.indexOf("Mandal") == -1){
				mandalType = "localbody";
			}else{
				mandalType = "mandal";
			}
			var assignedCadreId =  $("#assignedCadreId").val();
			if(assignedCadreId.length == 0)
				assignedCadreId =0;
			var involvedCadreId =  $("#involvedCadreId").val();
			if(involvedCadreId == null || involvedCadreId.length == 0)
				involvedCadreId = 0;
			var benefitId =  $("#benefitId").val();
			if(benefitId == null || benefitId.length == 0)
				benefitId = 0;
			var fromDate='';
			var toDate='';
			var dateStr = $("#dateRangePickerId").val(); 
			if(dateStr !=null && dateStr.length>0){
				fromDate = dateStr.split("-")[0];
				toDate = dateStr.split("-")[1];
			}
			var alertTpeId = $('#alertTypeId').val();	
			var alertCategoryId = $('#alertCategoryId').val();	
			var statusId = $('#alertStatusId').val();	
			if(alertTpeId== null || alertTpeId.length==0)
				alertTpeId=0;
			
			var jsObj =
				 {
					statusId:statusId,
					alertTypeId:alertTpeId,
					stateId  : stateId,
					districtId :districtId,
					constituencyId :constituencyId,
					mandalId   :mandalId,
					panchayatId:panchayatId,
					mandalType:mandalType,  
					fromDate:fromDate,              
					toDate:toDate,     
					assignedCadreId:assignedCadreId,              
					involvedCadreId:involvedCadreId,                 
					impactId:benefitId,                                     
					categoryId:alertCategoryId,
					actionTypeStatusId : 0,              
					task : "",
					fromDate2 : "",
					toDate2 : "" ,
					radioVal : '',
					verificationUserType :"programCommittee"
					
			}
			$.ajax({
				type:'GET',       
				url: 'getLocationFilterAlertDataAction.action',
				data: {task :JSON.stringify(jsObj)}
			}).done(function(result){
				GlobalAlertData = result;
				buildAlertData(result,jsObj);
			});   
		}        
		$("#assignedCadreId").val(0).trigger('chosen:updated');
		$("#involvedCadreId").val(0).trigger('chosen:updated');
		$("#benefitId").val(0).trigger('chosen:updated');    
		$("#stateId").val(globalStateId).trigger('chosen:updated');
		$("#referdistrictId").val(0).trigger('chosen:updated');
		$('#referconstituencyId').empty(); 
		$('#referconstituencyId').append('<option value="0">All</option>'); 
		$("#referconstituencyId").trigger('chosen:updated');
		$('#refermandalNameId').empty(); 
		$('#refermandalNameId').append('<option value="0">All</option>'); 
		$("#refermandalNameId").val(0).trigger('chosen:updated');
		$('#referpanchayatId').empty();
		$('#referpanchayatId').append('<option value="0">All</option>'); 			
		$("#referpanchayatId").val(0).trigger('chosen:updated');   
	}                

 function getAlertStatusByAlertType(){
	  var alertTpeId = $("#alertTypeId").val();
	  var jsObj ={
				  alertTypeId:alertTpeId,
				  alertId : 0
				 }
			$.ajax({
				type:'POST',
				url: 'getAlertStatusByAlertTypeAction.action',
				data: {task :JSON.stringify(jsObj)}
			}).done(function(result){
				var str='';
				str+="<option value='0' selected='selected'>All</option>";
				if(result != null && result.length > 0){
					for(var i in result){
					 str+='<option value='+result[i].statusId+'>'+result[i].status+'</option>';	
					}
				}
				$("#alertStatusId").html(str);
		        $("#alertStatusId").trigger("chosen:updated");
			});	
	}    