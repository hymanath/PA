var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
/* $("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menuCls").toggle();
});
 $(document).on("click",function(){
	$(".menu-data-cls").hide();
});  */
getDeptIdsListBYUserIdsLst();
function onLoadCalls(){
	//getDeptIdsListBYUserIdsLst();
	getCompleteOrStatusOverviewDetails();
	if(loginDesigId == 2 || loginDesigId == 23 || loginDesigId ==86){
		$("#leadWiseDivId").show();
		getLeadWiseOverviewDetails();
	}
	if(loginDesigId == 2 || loginDesigId == 23 || loginDesigId ==86){
		$("#refWiseOverViewId").show();
		$("#desigWiseCountDivId").show();
		$("#refWiseOverViewDivId").show();
		getReferralWiseOverviewDetails("");
		getBriefLeads();
	}
}

function getDeptIdsListBYUserIdsLst(){
	$("#designationrepresent").html('');
	  var json = {
	};
	$.ajax({              
		type:'POST',    
		url: 'getDeptIdsListBYUserIdsLst',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null){
			loginDesigId = result.designationId; 
			if(loginDesigId > 0 ){
				onLoadCalls();
			}
		}
	});	
}

//getPmDeptStatusIdsByUserIdsLst();
function getPmDeptStatusIdsByUserIdsLst(){
	var json = {};
	$.ajax({              
		type:'POST',    
		url: 'getPmDeptStatusIdsByUserIdsLst',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
	});	
}
//getCompleteOrStatusOverviewDetails();
function getCompleteOrStatusOverviewDetails(){
	if(loginDesigId == 2 || loginDesigId == 23 || loginDesigId==86){
		$("#completeOverViewDivId").show();
		$("#myActionDivId").show();
		$("#statusDivId").show();
		$("#completeOverviewId").html(spinner);
		$("#statusOverviewId").html(spinner);
		$("#myActionsId").html(spinner);
	}else{
		$("#completeOverViewDivId").hide();
		$("#myActionDivId").show();
		$("#statusDivId").hide();
		$("#myActionsId").html(spinner);
	}
var json = {
		 fromDate :"",
		 toDate:""
		}           
	$.ajax({              
		type:'POST',    
		url: 'getCompleteOrStatusOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		//$("#completeOverviewId").html(spinner);
		//$("#statusOverviewId").html(spinner);
		//$("#completeOverviewId").html(spinner);
		if(loginDesigId == 2 || loginDesigId == 23 || loginDesigId == 86){
			buildMyActionsDetails(result);
			buildCompleteOrStatusOverviewDetails(result);
			buildStatusOverviewDetails(result);
		}else{
			buildMyActionsDetails(result);
		}
		
	});	
}
function buildStatusOverviewDetails(result){
	var str='';
	if(result != null && result.list != null && result.list.length >0){
	
		for(var i in result.list){
			if(parseInt(result.list[i].id) == 9  || parseInt(result.list[i].id) == 10 || parseInt(result.list[i].id) == 11 || parseInt(result.list[i].id) == 12)
				continue;
			
		str+='<div class="col-sm-2 status_blocks">';
										str+='<div class="panel panel-default">';
											//str+='<div class="panel-heading" style="background-color:#FFF8EF">';
											if(result.list[i].id ==1){
												str+='<div class="panel-heading" style="background-color:#ECEEEF">';
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4363.png">';
											}else if(result.list[i].id ==2){
												str+='<div class="panel-heading" style="background-color:#FFF8EF">';
												
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4362.png">';
											}else if(result.list[i].id ==8 ){
												str+='<div class="panel-heading" style="background-color:#E5F6Ed">';
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4364.png">';
											}else if(result.list[i].id ==4 ){
												str+='<div class="panel-heading" style="background-color:#FCEDFF">';
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4369.png">';
											}else if(result.list[i].id ==5 ){
												str+='<div class="panel-heading" style="background-color:#FDE8F5">';
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4427.png">';
											}else{
												str+='<div class="panel-heading" style="background-color:#FDE8F5">';
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4369.png">';
											}
											str+='<b style="padding-left:5px;font-size:13px">'+result.list[i].name+'</b></h5>';
											str+='</div>';
											str+='<div class="panel-body">';
												str+='<div class="row">';
												str+='<div class="col-sm-8" style="padding-left:5px">';
													str+='<p>Representations</p>';
														str+='<h4><b>'+result.list[i].petitionIds.length+'</b></h4>';
												str+='</div>';
												str+='<div class="col-sm-4" style="padding-left:5px">';
													str+='<p>Works</p>';
														str+='<h4><b>'+result.list[i].subWorkIds.length+'</b></h4>';
												str+='</div>';
												str+='</div>';
												
											str+='</div>';
										str+='</div>';
									//str+='</div>';
									
								//str+='</div>';
									str+='</div>';
									
		}
		
		$("#statusOverviewId").html(str);
	}else{
		$("#statusOverviewId").html("No data available");
	}
}
function buildMyActionsDetails(result){
	var str='';
	var isMyactionApplied=false;
	if(result != null && result.statusList.length >0){
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
		str+='<div class="table-desig-scroll">';
			for(var i in result.statusList){
				if(result.statusList[i].statusType == "UserStatus"){
					str+='<div class="panel-group" id="accordionView">';
						str+='<div class="panel panel-default panel-pending">';
							str+='<div class="panel-heading" role="tab" id="headingView1'+i+'" style="padding-top: 10px;padding-bottom: 10px;padding-right: 10px;padding-left: 10px;">';
							if(!isMyactionApplied){
								//isMyactionApplied = true;
								str+='<a role="button" class="panelCollapseIconChangePE"  data-toggle="collapse" data-parent="#accordionView" href="#collapseView1'+i+'" aria-expanded="true" aria-controls="collapseView1'+i+'">';
								}else{
									str+='<a role="button" class="panelCollapseIconChangePE collapsed"  data-toggle="collapse" data-parent="#accordionView" href="#collapseView1'+i+'" aria-expanded="true" aria-controls="collapseView1'+i+'">';
								}
								str+='<h4 class="panel-title text-capital">'+result.statusList[i].name+'</h4>';
								str+='</a>';
							str+='</div>';
							if(!isMyactionApplied){
								isMyactionApplied = true;
									str+='<div id="collapseView1'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingView1'+i+'">';
								}else{
									str+='<div id="collapseView1'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingView1'+i+'">';
								}
								str+='<div class="panel-body pad_0">';
								
									str+='<div class="" style="background-color:#FFF8E5;padding:10px;">';
										str+='<div class="row">';
											str+='<div class="col-sm-12">';
											str+='<div class="col-sm-4">';
													str+='<div class="media">';
														str+='<div class="media-left">';
															str+='<img src="Assests/icons/Group 4370.png">';
														str+='</div>';
														str+='<div class="media-body">';
															str+='<h5 attr_id="'+result.statusList[i].id+'"><b>'+result.statusList[i].name+'</b></h5>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
												str+='<div class="col-sm-4">';
													str+='<div class="white-block pad_5">';
														str+='<div class="media">';
															str+='<div class="media-left">';
															str+='<img src="Assests/icons/Group 4377.png">';
															str+='</div>';
															str+='<div class="media-body">';
																str+='<p>Representations</p>';
																str+='<h4><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=total&desigId=0&statusId='+result.statusList[i].id+'&deptId=0" target="_blank">'+result.statusList[i].petitionIds.length+'</a></h4>';
																var convertToAmt = result.statusList[i].estimationCost*100000;
																var crores = (convertToAmt/10000000).toFixed(2);
																str+='<h5><b data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
															str+='</div>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
												str+='<div class="col-sm-4">';
													str+='<div class="white-block pad_5">';
														str+='<div class="media">';
															str+='<div class="media-left">';
																str+='<img src="Assests/icons/Group 4378.png">';
															str+='</div>';
															str+='<div class="media-body">';
																str+='<p>Works</p>';
																str+='<h4><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=total&desigId=0&statusId='+result.statusList[i].id+'&deptId=0" target="_blank">'+result.statusList[i].subWorkIds.length+'</a></h4>';
															str+='</div>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
												str+='</div>';
										str+='</div>';
									str+='</div>';
									
									str+='<div class="white-block">';
										str+='<h5 style="background-color:#E2F2F9;" class="grad_bg_orange">Referral wise</h5>';
											str+='<div class="row">';
												str+='<div class="col-sm-12">';
													for(var j in result.statusList[i].referrerList){
														str+='<div class="col-sm-2 petition_border">';
															str+='<p>'+result.statusList[i].referrerList[j].name.toUpperCase()+'</p>';
															str+='<h5><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.statusList[i].referrerList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].referrerList[j].petitionIds.length+'</a> - <a title="Works" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.statusList[i].referrerList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].referrerList[j].subWorkIds.length+'</a></h5>';
															//var convertToAmt = result.statusList[i].referrerList[j].estimationCost*100000;
														   // var crores = (convertToAmt/10000000).toFixed(3);
															//str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
														str+='</div>';
													}
											str+='</div>';
										str+='</div>';
									str+='</div>';
									
									str+='<div class="row pad_10">';
										str+='<div class="border-styling" style="border-top:1px solid #FFBB00"></div>';
										str+='<h5 style="background-color:#E2F2F9;margin-left:5px" class="grad_bg_orange">Subject wise</h5>';
											str+='<div class="col-sm-12">';
											for(var j in result.statusList[i].subList){
												str+='<div class="col-sm-2 petition_border">';
												str+='	<div class="">';
													str+='<p>'+result.statusList[i].subList[j].name.toUpperCase()+'</p>';
													str+='<h5><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=subject&subjId='+result.statusList[i].subList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].subList[j].petitionIds.length+'</a> - <a title="Works" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=subject&subjId='+result.statusList[i].subList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].subList[j].subWorkIds.length+'</a></h5>';
													//var convertToAmt = result.statusList[i].subList[j].estimationCost*100000;
													//var crores = (convertToAmt/10000000).toFixed(3);
													//str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
												str+='</div>';
												str+='</div>';
											}
											str+='</div>';
											
										str+='</div>';
									
									str+='<div class="row pad_10">';
										str+='<div class="border-styling" style="border-top:1px solid #FFBB00"></div>';
										str+='<h5 style="background-color:#E2F2F9;margin-left:5px" class="grad_bg_orange">Department wise</h5>';
											str+='<div class="col-sm-12">';
											for(var j in result.statusList[i].deptList){
												str+='<div class="col-sm-2 petition_border">';
												str+='	<div class="">';
													str+='<p style="font-size:11px"><b>'+result.statusList[i].deptList[j].name.toUpperCase()+'</b></p>';
													str+='<h5><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=department&desigId=0&statusId='+result.statusList[i].id+'&deptId='+result.statusList[i].deptList[j].id+'" target="_blank">'+result.statusList[i].deptList[j].petitionIds.length+'</a> - <a title="Works" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=department&desigId=0&statusId='+result.statusList[i].id+'&deptId='+result.statusList[i].deptList[j].id+'" target="_blank">'+result.statusList[i].deptList[j].subWorkIds.length+'</a></h5>';
													//var convertToAmt = result.statusList[i].deptList[j].estimationCost*100000;
													//var crores = (convertToAmt/10000000).toFixed(3);
													//str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
												str+='</div>';
												str+='</div>';
											}
											str+='</div>';
											
										str+='</div>';
				
				
								str+='</div>';
							str+='</div>';
							
						str+='</div>';
					str+='</div>';
				}
			}
		str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#myActionsId").html(str);
	var statusLength=[];
	for(var i in result.statusList){
		if(result.statusList[i].statusType == "UserStatus"){
			statusLength.push(result.statusList[i].statusType)
		}
	}
	if(statusLength.length>2){
		$(".table-desig-scroll").mCustomScrollbar({setHeight:'392px'});
	}
	}else{
		$("#myActionsId").html("No data available");
	}
}
function buildCompleteOrStatusOverviewDetails(result){
	
	var str ='';
	if(result != null){
	 str+='<div class="row">';
										str+='<div class="col-sm-12">';
											str+='<div class="col-sm-4">';
												str+='<div class="media">';
													str+='<div class="media-left">';
														str+='<img src="Assests/icons/Group 4370.png">';
													str+='</div>';
													str+='<div class="media-body">';
														str+='<h5><b>Total Representations</b></h5>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="col-sm-4">';
												str+='<div class="white-block pad_5">';
													str+='<div class="media">';
														str+='<div class="media-left">';
															str+='<img src="Assests/icons/Group 4377.png">';
														str+='</div>';
														str+='<div class="media-body">';
															str+='<p>Representations</p>';
															str+='<h4><b>'+result.petitionIds.length+'</b></h4>';
															var convertToAmt = result.estimationCost*100000;
															var crores = (convertToAmt/10000000).toFixed(2);
															str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="col-sm-4">';
												str+='<div class="white-block pad_5">';
												str+='	<div class="media">';
													str+='	<div class="media-left">';
															str+='<img src="Assests/icons/Group 4378.png">';
														str+='</div>';
														str+='<div class="media-body">';
														str+='	<p>Works</p>';
														str+='	<h4><b>'+result.subWorkIds.length+'</b></h4>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
									str+='<div class="row m_top10 ">';
										str+='<div class="">';
											str+='<div class="white-block">';
											
											str+='	<div class="row" style="padding:0px 10px 10px 10px">';
													str+='	<h5 style="background-color:#E2F2F9;margin-left:5px" class="grad_bg_lightblue">Referral wise</h5>';
													str+='<div class="col-sm-12 m_top5">';
													//str+='	<h5 style="background-color:#E2F2F9" class="grad_bg_lightblue">Referral wise</h5>';
													str+='<div class="clearfix"></div>'
													for(var i in result.referrerList){
													str+='	<div class="col-sm-2 petition_border">';
													str+='	<div class="">';
														str+='	<p attr_id='+result.referrerList[i].id+'>'+result.referrerList[i].name.toUpperCase()+'</p>';
														str+='	<h5><b title="Represents">'+result.referrerList[i].petitionIds.length+'</b> - <b title="Works">'+result.referrerList[i].subWorkIds.length+'</b></h5>';
														//var convertToAmt = result.referrerList[i].estimationCost*100000;
														//var crores = (convertToAmt/10000000).toFixed(3);
														//str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
														str+='</div>';
														str+='</div>';
														}
													str+='</div>';
												str+='</div>';
												
												
												str+='<div class="row pad_10">';
												str+='<div class="border-styling" style="border-top:1px solid #1283C9;"></div>';
												str+='	<h5 class="grad_bg_lightblue" style="background-color:#E2F2F9;margin-left:5px">Subject wise</h5>';
												str+='	<div class="col-sm-12 m_top5">';
												//str+='	<h5 class="grad_bg_lightblue" style="background-color:#E2F2F9">Subject wise</h5>';
												str+='<div class="clearfix"></div>';
												for(var i in result.subList){
													str+='	<div class="col-sm-2 petition_border">';
													str+='	<div class="">';
														str+='	<p attr_id="'+result.subList[i].id+'">'+result.subList[i].name.toUpperCase()+'</p>';
														str+='	<h5><b title="Represents">'+result.subList[i].petitionIds.length+'</b> - <b title="Works">'+result.subList[i].subWorkIds.length+'</b></h5>';
														//var convertToAmt = result.subList[i].estimationCost*100000;
														//var crores = (convertToAmt/10000000).toFixed(3);
														//str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
														str+='</div>';
														str+='</div>';
												}
													str+='</div>';
													
												str+='</div>';
												str+='<div class="row pad_10">';
												str+='<div class="border-styling" style="border-top:1px solid #1283C9;"></div>';
												str+='<h5 class="grad_bg_lightblue" style="background-color:#E2F2F9;margin-left:5px">Department wise</h5>';
												str+='	<div class="col-sm-12 m_top5">';
												//str+='<h5 class="grad_bg_lightblue" style="background-color:#E2F2F9;margin-left:5px">Department wise</h5>';
												str+='<div class="clearfix"></div>';
												for(var i in result.deptList){
													str+='	<div class="col-sm-2 petition_border">';
													str+='	<div class="">';
														str+='	<p attr_id="'+result.deptList[i].id+'" style="font-size:11px"><b>'+result.deptList[i].name.toUpperCase()+'</b></p>';
														str+='	<h5><b title="Represents">'+result.deptList[i].petitionIds.length+'</b> - <b title="Works">'+result.deptList[i].subWorkIds.length+'</b></h5>';
														//var convertToAmt = result.deptList[i].estimationCost*100000;
														//var crores = (convertToAmt/10000000).toFixed(3);
														//str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
														str+='</div>';
														str+='</div>';
												}
													str+='</div>';
													
												str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
									$("#completeOverviewId").html(str);
	}else{
		$("#completeOverviewId").html("No data available");
	}
}
//getLeadWiseOverviewDetails();
function getLeadWiseOverviewDetails(){
	$("#completeOverviewId").html(spinner);
	$("#leadWiseOverviewId").html(spinner);
    $("#myActionsId").html(spinner);
var json = {
		 fromDate :"",
		 toDate:""
		}           
	$.ajax({              
		type:'POST',    
		url: 'getLeadWiseOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		
		 if(result!= null){
			buildLeadWiseOverviewDetails(result);
		}
		else{
			$("#leadWiseOverviewId").html("No data available");
		} 
	});	
}

function buildLeadWiseOverviewDetails(result){
	var str='';
	var count=0;
	//str+='<div class="row">';
	for(var i in result){
		count++;
		str+='<div class="col-sm-3">';
			str+='<div class="panel panel-default">';
				str+='<div class="panel-heading" style="background-color:#D6E8F2">';
					if(result[i].name.length > 28)
					{
						str+='<h5><span class="count_cls">'+count+'</span><b class="tooltipCls" title="'+result[i].name+'">'+result[i].name.substring(0,26)+'..</b></h5>';
						
					}else{
						str+='<h5><span class="count_cls">'+count+'</span><b  class="tooltipCls" >'+result[i].name+'</b></h5>';
						}
					
				str+='</div>';
				str+='<div class="panel-body">';
				
					str+='<div class="row m_top5">';
						str+='<div class="col-sm-6">';
							str+='<p>Representations</p>';
							str+='<h4><b>'+result[i].petitionIds.length+'</b></h4>';
							//var convertToAmt = result[i].estimationCost*100000;
							//var crores = (convertToAmt/10000000).toFixed(3);
							//str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<p>Works</p>';
							str+='<h4><b>'+result[i].subWorkIds.length+'</b></h4>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row m_top5">';
						for(var j in result[i].statusList){
							str+='<div class="col-sm-6" style="padding:2px">';
								str+='<div class="well pad_5 m_bottom_0">';
									str+='<div class="row">';
										if(result[i].statusList[j].id == 1){
											str+='<div class="col-sm-6"><span>Pending</span><br><b>'+result[i].statusList[j].petitionIds.length+'</b></div>';
										}else if(result[i].statusList[j].id == 2){
											str+='<div class="col-sm-6"><span>Completed</span><br><b>'+result[i].statusList[j].petitionIds.length+'</b></div>';
										}
										//str+='<div class="col-sm-6"><span>Pending</span><br><b>'+result[i].statusList[j].totalRepresents+'</b></div>';
										str+='<div class="col-sm-6"><span>Works</span><br><b>'+result[i].statusList[j].subWorkIds.length+'</b></div>';
										//var convertToAmt = result[i].statusList[j].estimationCost*100000;
                                        //var crores = (convertToAmt/10000000).toFixed(3);
                                        //str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						}
					str+='</div>';
					
                str+='</div>';
				
			str+='</div>';
		str+='</div>';

	}
	//str+='</div>';
	$("#leadWiseOverviewId").html(str);
	$(".tooltipCls").tooltip();
}
$(document).on("click",".desigClsDivId",function(){
	var designationId = $(this).attr("attr_desigId");
	getReferralWiseOverviewDetails(designationId);
	$('.desigClsDivId').removeClass("activeCls");
	$(this).addClass("activeCls");
});
$(document).on("change","#briefLeadId",function(){
	$("#desigWiseCandidatesView").html("");
	//$("#desigWiseCountId").html("");
	var designationId = '';
	$(".desigClsDivId").each(function(){
				if($(this).hasClass("activeCls")){
					designationId = $(this).attr("attr_desigId");
				}
			}); 
	getReferralWiseOverviewDetails(designationId);
});

//getReferralWiseOverviewDetails("");
function getReferralWiseOverviewDetails(desigId){
	if(desigId == ""){
		$("#desigWiseCountId").html(spinner);	
	}else if(desigId >=0){
		$("#desigWiseCandidatesView").html(spinner);
	}
	var desigIds = [];
	if(desigId != ""){
		desigIds.push(desigId);
	}
	var briefLeadIds = [];
	if($("#briefLeadId").val() >0){
		briefLeadIds.push($("#briefLeadId").val());
	}
	
	var json = {
			designationIds:desigIds,
				lightVendorIdList:briefLeadIds
	  };
	$.ajax({              
		type:'POST',    
		url: 'getReferralWiseOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		 if(result!= null){
			 if(desigId =="")
			 buildDesignationsWiseCount(result);
			 if(desigId != "" && desigId >=0 && result.referrerList.length >0){
				 buildDesignationsWiseInformation(result);
			 }else if(desigId != ""){
				 $("#desigWiseCandidatesView").html("No data avialable");
			 }
		}
		else{
			$("#desigWiseCountId").html("No data available");
		} 		
		
	});	
}

 function buildDesignationsWiseCount(result){
	var str ='';
	
	  for(var i in result.subList){
		
	   str+='<div class="col-sm-3" id="column2">';
	   if(i==0)
	   {
	   str+='<div class="panel panel-default desigClsDivId activeCls"  style="cursor:pointer;" attr_desigId="'+result.subList[i].deptDesigId+'">';
	   }
		else
		{
	   str+='<div class="panel panel-default desigClsDivId"  style="cursor:pointer;" attr_desigId="'+result.subList[i].deptDesigId+'">';
		}
		str+='<div class="panel-heading" style="background-color:#D2DEF1;"><h4><b>'+result.subList[i].desigName+'-'+result.subList[i].subWorkIds.length+'</b></h4></div>';
			str+='<div class="panel-body" style="background-color:#E7EDF8;">';
				
				   str+='<div class="row">';			
					str+='<div class="col-sm-6">';
					str+='<p><b>Representations</b></p>';
					//str+='<h4><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.subList[i].deptDesigId+'" target="_blank">'+result.subList[i].petitionIds.length+'</a></h4>';
					str+='<h4><span>'+result.subList[i].petitionIds.length+'</span></h4>';
					str+='</div>';
					str+='<div class="col-sm-6">';
					str+='<p><b>Works</b></p>';
					//str+='<h4><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.subList[i].deptDesigId+'" target="_blank">'+result.subList[i].noOfWorks+'</a></h4>';
					str+='<h4><span>'+result.subList[i].subWorkIds.length+'</span></h4>';
					 //var convertToAmt = result.subList[i].estimationCost*100000;
                     //var crores = (convertToAmt/10000000).toFixed(3);
                     //str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
					str+='</div>';				
				str+='</div>';
					
			str+='</div>';
		str+='</div>';
	str+='</div>';
	  }
	   if(result.subList != null && result.subList.length>0){
		  getReferralWiseOverviewDetails(result.subList[0].deptDesigId);
	  } 
	  
	$("#desigWiseCountId").html(str);
	} 
	
 function buildDesignationsWiseInformation(result){
	
	var str='';
			str+='<div class="col-md-12">';
			str+='<div class="table-responsive">';
				str+='<table class="table table-bordered" id="workDetailsTab">';
					str+='<thead>';
						str+='<tr>';
							str+='<th>Name</th>';
							str+='<th>Total Representations</th>';
							str+='<th>Works</th>';
							str+='<th>Pending Representations</th>';
							str+='<th>Works</th>';
							str+='<th>Rejected Representations</th>';
							str+='<th>Works</th>';
							str+='<th>Completed Representations</th>';
							str+='<th>Works</th>';
							str+='<th>Amount</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result.referrerList){
							str+='<tr style="text-align:center;">';
								str+='<td><h5><b>'+result.referrerList[i].referrerName+'</b><h5><p>'+result.referrerList[i].desigName+'</p></td>';
								
								if(result.referrerList[i].petitionIds.length >0){
									str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referralCan&desigId='+result.referrerList[i].deptDesigId+'&refCanId='+result.referrerList[i].id+'" target="_blank">'+result.referrerList[i].petitionIds.length+'</a></td>';
								}else{
									str+='<td>-</td>';
								}
								if(result.referrerList[i].subWorkIds.length >0){
									str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referralCan&desigId='+result.referrerList[i].deptDesigId+'&refCanId='+result.referrerList[i].id+'" target="_blank">'+result.referrerList[i].subWorkIds.length+'</a></td>';
								}else{
									str+='<td>-</td>';
								}
								
								for(var j in result.referrerList[i].statusList){
										var statusIds= '';
										if(result.referrerList[i].statusList[j].id == 1){
											statusIds='1,3,6,7';
										}else if(result.referrerList[i].statusList[j].id == 2){
											statusIds='5';
										}if(result.referrerList[i].statusList[j].id == 3){
											statusIds='4,8';
										}
										
										if(result.referrerList[i].statusList[j].petitionIds.length >0){
											str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referralCan&desigId='+result.referrerList[i].deptDesigId+'&statusId='+statusIds+'&refCanId='+result.referrerList[i].id+'" target="_blank">'+result.referrerList[i].statusList[j].petitionIds.length+'</a></td>';
										}else{
											str+='<td>-</td>';
										}
									
									if(result.referrerList[i].statusList[j].subWorkIds.length >0){
										str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referralCan&desigId='+result.referrerList[i].deptDesigId+'&statusId='+statusIds+'&refCanId='+result.referrerList[i].id+'" target="_blank">'+result.referrerList[i].statusList[j].subWorkIds.length+'</a></td>';
									}else{
										str+='<td>-</td>';
									}
								}
									//for(var j in result.referrerList[i].statusList){
									if(typeof(result.referrerList[i].estimationCost) != "undefined"){
										var convertToAmt = result.referrerList[i].estimationCost*100000;
										var crores = (convertToAmt/10000000).toFixed(2);
										str+='<td><b>'+crores+'</b></td>';
									}else{
										str+='<td><b>-</b></td>';
									}	
								//}	
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';	
	
	
		$("#desigWiseCandidatesView").html(str);
$(".scrollCls").mCustomScrollbar({setHeight:'600px'})
$("#workDetailsTab").dataTable({
		"paging":   true,
		"info":     false,
		"searching": true,
		"autoWidth": true,
		//"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "All"]]
	});
}

//getBriefLeads();
function getBriefLeads(){
$.ajax({ 
 type:'POST', 
 url: 'getBriefLeads',
 dataType: 'json',
 beforeSend : function(xhr){
 xhr.setRequestHeader("Accept", "application/json");
 xhr.setRequestHeader("Content-Type", "application/json");
 }
}).done(function(result){
  $("#briefLeadId").empty();
		if(result !=null && result.length >0){
			$("#briefLeadId").append("<option value=0>All</option>");
			for(var i in result){
				$("#briefLeadId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
				}
		}
		$("#briefLeadId").trigger('chosen:updated');
		 
}); 
}