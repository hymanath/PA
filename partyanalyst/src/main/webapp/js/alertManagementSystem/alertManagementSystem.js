
$("#dateRangePicker").daterangepicker({
	opens: 'left',
	startDate: moment(),
	endDate: moment(),
	locale: {
	  format: 'DD-MM-YYYY'  
	},
});
		
var alertId = 3725;
buildtotalAlertsModalTabId()
	$("#totalAlertsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
function buildtotalAlertsModalTabId()
{
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var str='';
	if($(window).width() < 500)
	{
		str+='<div class="table-responsive" >';
	}
		str+='<table class="table table-condensed" style="border:1px solid #ddd" id="dataTableTotalAlerts">';
			str+='<thead>';
				str+='<th>Alert Source</th>';
				str+='<th>Alert Title</th>';
				str+='<th></th>';
			str+='</thead>';
			str+='<tbody>';
				str+='<tr>';
					str+='<td></td>';
					str+='<td></td>';
					str+='<td><button class="btn btn-success alertDetailsModalCls" attr_alertId="3725">Alert Details</button></td>';
				str+='</tr>';
			str+='</tbody>';
		str+='</table>';
	if($(window).width() < 500)
	{
		str+='</div>';
	}
	$("#totalAlertsModalTabId").html(str);
	$("#dataTableTotalAlerts").dataTable();
}

$(document).on("click",".alertDetailsModalCls",function(){
	$("#alertDetailsModal").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	
	var alertId = $(this).attr("attr_alertId");
	getAlertData();
	getAlertStatusCommentsTrackingDetails()
});
$(document).on("click",".alertDetailsModalClose",function(){
	setTimeout(function(){
		$("body").addClass("modal-open")
	},500);
});

function getAlertData()
{
	$("#alertCandidateDataId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
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
		if(result != null)
		
		buildAlertData(result);
	});
}

function buildAlertData(result)
{
	
	for(var i in result)
	{
		var severityTdId = result[i].categoryId;
		
		$("#typeId").html('<b>'+result[i].alertType+'</b>');
		$("#createdDate").html('<b>'+result[i].date+'</b>');
		$("#alertStatus").html('<b>'+result[i].status+'</b>');
		if(severityTdId ==1){
			$("#severityTdId").show();
		}else{
			$("#severityTdId").hide();
		}
		$(".severityIdColorCls").addClass('<b>'+result[i].severity+'</b>');
		$("#severityId").html('<b>'+result[i].severity+'</b>');
		$("#levelId").html('<b>'+result[i].regionScope+'</b>');
		
		var location ='';
			
		if(result[i].locationVO.stateId !=null){
			location +='<b>'+result[i].locationVO.state+' , ';
		}
		if(result[i].locationVO.districtId !=null){
			location +=''+result[i].locationVO.districtName+' District, ';
		}
		if(result[i].locationVO.constituencyId !=null){
			location +=''+result[i].locationVO.constituencyName+' Constituency, ';
		}
		
		if(result[i].locationVO.localEleBodyName !=null && result[i].locationVO.localEleBodyName.length>0){
			location +=''+result[i].locationVO.localEleBodyName+' Municipality, ';
		}
		if(result[i].locationVO.tehsilName !=null && result[i].locationVO.tehsilName.length>0){
			location +=''+result[i].locationVO.tehsilName+' Mandal, ';
		}
		if(result[i].locationVO.wardName !=null && result[i].locationVO.wardName.length>0){
			location +=''+result[i].locationVO.wardName+' Ward, ';
		}
		if(result[i].locationVO.villageName !=null && result[i].locationVO.villageName.length>0){
			location +=''+result[i].locationVO.villageName+' Panchayat </b>';
		}
		
		$("#LocationId").html(''+location+'');
		
		$("#titleId,#mainTitleId").html('<b>'+result[i].title+'</b>');
		
		$("#descriptionId").html('<b>'+result[i].desc+'</b>');
		
		if(result[i].imageUrl !=null && result[i].imageUrl.length>0){
			$(".imageUrlUlCls").html("<li class='articleDetailsCls' attr_articleId="+result[i].alertCategoryTypeId+" style='cursor:pointer'><img src='http://mytdp.com/NewsReaderImages/"+result[i].imageUrl+"' style='width: 150px; height: 150px;'></img></li>");
			$("#imageUrlTrId").show();
		}else{
			$(".imageUrlUlCls").html("");
			$("#imageUrlTrId").hide();
		}
		
		buildAlertCandidateData(result[i].subList,result[i].categoryId);
	}
}

function buildAlertCandidateData(result,categoryId)
{

	var str='';
	
	if(result == null || result.length == 0)
	{
		$("#alertCandidateDataId").html('No Involved Members..');
		return;
	}
	
	if(categoryId !=null && categoryId>1){
		for(var i in result)
		{
			
			str+='<div class="col-md-12 col-xs-12 col-sm-4 m_top10" style="padding: 3px;">';
				str+='<div class="media" style="border:1px solid #ddd;height:100px">';
					str+='<div class="media-left">';						
					if(result[i].image !=null && result[i].image.length>0){
						str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="dist/Appointment/img/thumb.jpg" style="width:50px;"/>';
					}else{
						str+=' <img src="dist/Appointment/img/thumb.jpg"  onerror="setDefaultImage(this);" alt="dist/Appointment/img/thumb.jpg" style="width:50px;"/>';
					}					   					  
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
					   if(result[i].committeePosition != null && result[i].committeePosition.length > 0)
						//str+='  <p>'+result[i].committeeName+' Committee '+result[i].committeePosition+' </p>';
					str+='  <p><b class="text-capital text-danger">Designation : </b>'+result[i].committeePosition+' </p>';
					 // str+='  <p>'+result[i].mobileNo+'</p>';
					 // str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
					  if(result[i].impactId == 1)
					  {
						 str+=' <span class="label label-success" style="margin-top: 7px;">+ Ve</span>'; 
					  }else if(result[i].impactId == 2){
						  str+=' <span class="label label-danger" style="margin-top: 7px;">- Ve</span>';
					  }else{
						  str+=' <span class="label label-neutral" style="margin-top: 7px;">N</span>';
					  }
					  
					  if(result[i].organization !=null){
						  str+='<p><b class="text-capital text-danger">Organization</b> : '+result[i].organization+'</p>';
					  }
					  if(result[i].membershipNo !=null && result[i].membershipNo.length>0){
						  str+='<p><b class="text-capital text-danger">Membership No </b> : '+result[i].membershipNo+'</p>';
					  }
					  
				  str+='  </div>';
				str+='</div>';
		   str+=' </div>';

		}
	}else{
		for(var i in result)
		{
			str+='<div class="col-md-12 col-xs-12 col-sm-4 m_top10" style="padding: 3px;">';
				str+='<div class="media" style="border:1px solid #ddd;height:100px">';
					str+='<div class="media-left">';
					   str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;"/>';
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
					   if(result[i].committeePosition != null && result[i].committeePosition.length > 0)
							str+='<b><p class="text-capital">'+result[i].electionType+" "+result[i].committeeName+' Committee '+result[i].committeePosition+'</b></p>';
						if(result[i].designation != null && result[i].designation != "")
							str+='<b><p class="text-capital">'+result[i].designation+'</p></b>';
					  str+='  <p>'+result[i].mobileNo+'</p>';
					  str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
					  
					  if(result[i].membershipNo !=null && result[i].membershipNo.length>0){
						  str+='<p><a>'+result[i].membershipNo+'</a></p>';
					  }
					  if(result[i].impactId == 1)
					  {
						 str+=' <span class="label label-success" style="margin-top: 7px;">+ Ve</span>'; 
					  }else if(result[i].impactId == 2){
						  str+=' <span class="label label-danger" style="margin-top: 7px;">- Ve</span>';
					  }else{
						  str+=' <span class="label label-neutral" style="margin-top: 7px;">N</span>';
					  }
					  
				  str+='  </div>';
				str+='</div>';
		   str+=' </div>';
		}
	}
	$("#involvedCandidatesCnt").html(result.length);	
	$("#alertCandidateDataId").html(str);
}


function getAlertStatusCommentsTrackingDetails()
{
	$("#alertCommentsDivIdNew").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj={
				alertId:3725,
				task:""
			}
	$.ajax({
	  type : 'GET',
	  url : 'getAlertStatusCommentsTrackingDetails.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
		alertComments(result);		
	});
	
}
function alertComments(result)
{
	var docName = '';
	var extName = [];
	var statusId = 0;
	var length = result.length;
	length = length - 1;
	var str = '';
	if(status == 'false'){  
		$(".cadreAlertCommentsDivId").show();
		str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true" style="margin-bottom:0px;">';
	}
	str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
		statusId = result[i].statusId;
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
			if(length == i)  
			{
				str+='<a role="button" class="alertCommentColapse" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
			}else{
				str+='<a class="collapsed alertCommentColapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="false" aria-controls="collapse'+i+'">';
			}
			if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i><span class="pull-right" style="padding-right:20px;">'+result[i].sublist2[0].date+'</span>';    
				str+='</h4>';
			}else{
				str+='<h4 class="panel-title">'+result[i].status+'';
					str+='<i class="glyphicon glyphicon-hourglass"></i><span class="pull-right" style="padding-right:20px;">'+result[i].sublist2[0].date+'</span>';
				str+='</h4>';
			} 
				str+='</a>';  
			str+='</div>';
			if(length == i)  
			{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
			}else{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">';
			}
				str+='<div class="panel-body" style="padding:5px;">';
					str+='<div class="row">';
						for(var j in result[i].sublist2){
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].sublist2[j].date
								var dateArr = date.split("-");
								var year = dateArr[0];
								var month = dateArr[1];
								var day = dateArr[2];
								str+='<table class="table tableCalendar">';
									str+='<tr>';
										str+='<td colspan="2">';
											str+='<h3>'+day+'</h3>';
										str+='</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>'+getMonth(month)+'</td>';        
										str+='<td>'+year+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
							str+='<div class="col-md-10 col-xs-12 col-sm-10" style="padding-left:0px;">';
								str+='<ul class="alertStatusTracking">';
									str+='<li>';
										str+='<div class="arrow_box_left">';
										for(var k in result[i].sublist2[j].sublist)
										{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:'+result[i].sublist2[j].sublist[k][0].timeString+'</span><br>';
												for(var l in result[i].sublist2[j].sublist[k])
												{
													str+='<img src="dist/Appointment/img/thumb.jpg" style="width:10px;display:inline-block"/> '+result[i].sublist2[j].sublist[k][l].cadreName+'<br>';
												}
												str+='</p>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p>'+result[i].sublist2[j].sublist[k][0].comment+'</p>';
												if(result[i].sublist2[j].sublist[k][0].docList != null && result[i].sublist2[j].sublist[k][0].docList.length > 0){
													str+='<p><span style="color:#A286C0;font-size:13px;">DOCUMENTS:</span><br>';
													str+='<ul>';
													for(var t in result[i].sublist2[j].sublist[k][0].docList){
														docName = result[i].sublist2[j].sublist[k][0].docList[t].name;
														extName = docName.split("/");
														str+='<li id="document'+result[i].id+'"><a href="/Reports/'+result[i].sublist2[j].sublist[k][0].docList[t].name+'" target="_blank">'+extName[1]+'</a></li>';          
													}
													str+='</ul>';              
												}
												str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].sublist2[j].sublist[k][0].userName+'</span></p>';
												str+='<hr style="margin-top:20px;"/>';
											str+='</div>';   
										}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						}           
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	str+='</div>';
	$("#alertCommentsDivIdNew").html(str)
}
function getMonth(month){
	if(month=="01"){
		return "Jan"
	}else if(month=="02"){
		return "Feb"
	}else if(month=="03"){
		return "Mar"
	}else if(month=="04"){
		return "Apr"
	}else if(month=="05"){
		return "May"
	}else if(month=="06"){
		return "Jun"
	}else if(month=="07"){
		return "Jul"
	}else if(month=="08"){
		return "Aug"
	}else if(month=="09"){
		return "Sep"
	}else if(month=="10"){
		return "Oct"
	}else if(month=="11"){
		return "Nov"
	}else if(month=="12"){  
		return "Dec"
	}  
}

