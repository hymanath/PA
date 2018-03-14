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
		$("#refWiseOverViewId").show();
		$("#leadWiseDivId").show();
		$("#desigWiseCountDivId").show();
		$("#refWiseOverViewDivId").show();
		//$("#officerBlock").show();
		getReferralWiseOverviewDetails("");
		getBriefLeads();
		getLeadWiseOverviewDetails();
		//getPmOfficerWisePetitionDetails("","",loginDesigId);
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
														var convertToAmt = result.list[i].estimationCost*100000;
														var crores = (convertToAmt/10000000).toFixed(2);
														str+='<h5 class="font_weight">'+result.list[i].petitionIds.length+' <small data-toggle="tooltip" title="Total Budget" class="tooltipCls font_weight" style="color:#000">('+crores+')</small></h5>';
														str+='<h5 class="font_weight m_top10"><i class="fa fa-inr"></i> '+result.list[i].withCostPetitionsCount+'</h5>';
														str+='<h5 class="font_weight m_top5"><i class="fa fa-krw"></i> '+result.list[i].noCostPetitionsCount+'</h5>';
												str+='</div>';
												str+='<div class="col-sm-4" style="padding-left:5px">';
													str+='<p>Works</p>';
														str+='<h5><b>'+result.list[i].subWorkIds.length+'</b></h5>';
														str+='<h5 class="font_weight m_top10"><i class="fa fa-inr"></i> '+result.list[i].withCostWorksCount+'</h5>';
														str+='<h5 class="font_weight m_top5"><i class="fa fa-krw"></i> '+result.list[i].noCostWorksCount+'</h5>';
												str+='</div>';
												str+='</div>';
												
											str+='</div>';
										str+='</div>';
									//str+='</div>';
									
								//str+='</div>';
									str+='</div>';
									
		}
		
		$("#statusOverviewId").html(str);
		$(".tooltipCls").tooltip();
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
																var convertToAmt = result.statusList[i].estimationCost*100000;
																var crores = (convertToAmt/10000000).toFixed(2);
																
																str+='<h4 class="font_weight"><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=total&desigId=0&statusId='+result.statusList[i].id+'&deptId=0" target="_blank">'+result.statusList[i].petitionIds.length+'</a> <small data-toggle="tooltip" title="Total Budget" class="tooltipCls font_weight">('+crores+')</small></h4>';
							
																str+='<h5 class="font_weight m_top10"><i class="fa fa-inr"></i> '+result.statusList[i].withCostPetitionsCount+'</h5>';
																str+='<h5 class="font_weight m_top5"><i class="fa fa-krw"></i> '+result.statusList[i].noCostPetitionsCount+'</h5>';
																
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
																str+='<h5 class="font_weight m_top10"><i class="fa fa-inr"></i> '+result.statusList[i].withCostWorksCount+'</h5>';
																str+='<h5 class="font_weight m_top5"><i class="fa fa-krw"></i> '+result.statusList[i].noCostWorksCount+'</h5>';
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
												str+='<div class="col-sm-12" style="padding-left: 30px;padding-right: 30px;">';
												str+='<ul class="list-inline referralPendindSlickCls m_top10">';
													for(var j in result.statusList[i].referrerList){
														str+='<li class="petition_border">';
															str+='<p class="text-center">'+result.statusList[i].referrerList[j].name.toUpperCase()+'</p>';
															str+='<h5  class="text-center m_top5"><a title="Represents" data-toggle="tooltip" class="tooltipCls" data-placement="right" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.statusList[i].referrerList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].referrerList[j].petitionIds.length+'</a> - <a title="Works" data-toggle="tooltip" class="tooltipCls" data-placement="right" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.statusList[i].referrerList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].referrerList[j].subWorkIds.length+'</a></h5>';
															var convertToAmt = result.statusList[i].referrerList[j].estimationCost*100000;
														    var crores = (convertToAmt/10000000).toFixed(2);
															str+='<h5 class="text-center"><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls" data-placement="right">('+crores+')</b></h5>';
															
															str+='<h5 class="m_top5 font_weight text-center"><span><i class="fa fa-inr"></i> '+result.statusList[i].referrerList[j].withCostPetitionsCount+'</span> - <span>'+result.statusList[i].referrerList[j].withCostWorksCount+'</span></h5>';
															str+='<h5 class=" font_weight text-center"><span><i class="fa fa-krw"></i> '+result.statusList[i].referrerList[j].noCostPetitionsCount+'</span> - <span>'+result.statusList[i].referrerList[j].noCostWorksCount+'</span></h5>';
									
														str+='</li>';
													}
												str+='</ul>';	
											str+='</div>';
										str+='</div>';
									str+='</div>';
									
									str+='<div class="row pad_10">';
										str+='<div class="border-styling" style="border-top:1px solid #FFBB00"></div>';
										str+='<h5 style="background-color:#E2F2F9;margin-left:5px" class="grad_bg_orange">Subject wise</h5>';
											str+='<div class="col-sm-12" style="padding-left: 30px;padding-right: 30px;">';
											str+='<ul class="list-inline subjectPendindSlickCls m_top10">';
											for(var j in result.statusList[i].subList){
												str+='<li class="petition_border">';
													str+='<p class="text-center">'+result.statusList[i].subList[j].name.toUpperCase()+'</p>';
													str+='<h5 class="text-center m_top5"><a title="Represents" data-toggle="tooltip" class="tooltipCls" data-placement="right" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=subject&subjId='+result.statusList[i].subList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].subList[j].petitionIds.length+'</a> - <a title="Works" data-toggle="tooltip" class="tooltipCls" data-placement="right" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=subject&subjId='+result.statusList[i].subList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].subList[j].subWorkIds.length+'</a></h5>';
													var convertToAmt = result.statusList[i].subList[j].estimationCost*100000;
													var crores = (convertToAmt/10000000).toFixed(2);
													str+='<h5 class="text-center"><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls" data-placement="right">('+crores+')</b></h5>';
													
													str+='<h5 class="m_top5 font_weight text-center"><span><i class="fa fa-inr"></i> '+result.statusList[i].subList[j].withCostPetitionsCount+'</span> - <span>'+result.statusList[i].subList[j].withCostWorksCount+'</span></h5>';
													str+='<h5 class=" font_weight text-center"><span><i class="fa fa-krw"></i> '+result.statusList[i].subList[j].noCostPetitionsCount+'</span> - <span>'+result.statusList[i].subList[j].noCostWorksCount+'</span></h5>';
												str+='</li>';
											}
											str+='</ul>';
											str+='</div>';
											
										str+='</div>';
									
									str+='<div class="row pad_10">';
										str+='<div class="border-styling" style="border-top:1px solid #FFBB00"></div>';
										str+='<h5 style="background-color:#E2F2F9;margin-left:5px" class="grad_bg_orange">Department wise</h5>';
											str+='<div class="col-sm-12" style="padding-left: 30px;padding-right: 30px;">';
											str+='<ul class="list-inline departmentPendindSlickCls m_top10">';
											for(var j in result.statusList[i].deptList){
												str+='<li class="petition_border">';
												
													str+='<p class="text-center" style="font-size:11px"><b>'+result.statusList[i].deptList[j].name.toUpperCase()+'</b></p>';
													str+='<h5 class="text-center m_top5"><a title="Represents" data-toggle="tooltip" class="tooltipCls" data-placement="right" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=department&desigId=0&statusId='+result.statusList[i].id+'&deptId='+result.statusList[i].deptList[j].id+'" target="_blank">'+result.statusList[i].deptList[j].petitionIds.length+'</a> - <a data-toggle="tooltip" class="tooltipCls" data-placement="right" title="Works" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=department&desigId=0&statusId='+result.statusList[i].id+'&deptId='+result.statusList[i].deptList[j].id+'" target="_blank">'+result.statusList[i].deptList[j].subWorkIds.length+'</a></h5>';
													var convertToAmt = result.statusList[i].deptList[j].estimationCost*100000;
													var crores = (convertToAmt/10000000).toFixed(2);
													str+='<h5 class="text-center"><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls" data-placement="right">('+crores+')</b></h5>';
													
													str+='<h5 class="m_top5 font_weight text-center"><span><i class="fa fa-inr"></i> '+result.statusList[i].deptList[j].withCostPetitionsCount+'</span> - <span>'+result.statusList[i].deptList[j].withCostWorksCount+'</span></h5>';
													str+='<h5 class=" font_weight text-center"><span><i class="fa fa-krw"></i> '+result.statusList[i].deptList[j].noCostPetitionsCount+'</span> - <span>'+result.statusList[i].deptList[j].noCostWorksCount+'</span></h5>';
													
												str+='</li>';
												
											}
											str+='</ul>';
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
	$(".tooltipCls").tooltip();
	$(".referralPendindSlickCls").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 3,
		slidesToScroll: 1,
		variableWidth: false
	 });
	 $(".subjectPendindSlickCls").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 3,
		slidesToScroll: 1,
		variableWidth: false
	 });
	 $(".departmentPendindSlickCls").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 3,
		slidesToScroll: 1,
		variableWidth: false
	 });
	 
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
							var convertToAmt = result.estimationCost*100000;
							var crores = (convertToAmt/10000000).toFixed(2);
							str+='<p>Representations</p>';
							str+='<h4 class="font_weight">'+result.petitionIds.length+' <small data-toggle="tooltip" title="Total Budget" class="tooltipCls font_weight">('+crores+')</small></h4>';
							
							str+='<h5 class="font_weight m_top10"><i class="fa fa-inr"></i> '+result.withCostPetitionsCount+'</h5>';
							str+='<h5 class="font_weight m_top5"><i class="fa fa-krw"></i> '+result.noCostPetitionsCount+'</h5>';
							
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
						str+='<h5 class="font_weight m_top10"><i class="fa fa-inr"></i> '+result.withCostWorksCount+'</h5>';
						str+='<h5 class="font_weight m_top5"><i class="fa fa-krw"></i> '+result.noCostWorksCount+'</h5>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top10">';
			str+='<div class="white-block">';
			
				str+='<div class="row" style="padding:0px 10px 10px 10px">';
					str+='<h5 style="background-color:#E2F2F9;margin-left:5px" class="grad_bg_lightblue">Referral wise</h5>';
					str+='<div class="col-sm-12 m_top5" style="padding-left: 30px;padding-right: 30px;">';
						str+='<ul class="list-inline referralSlickCls m_top10">';
							for(var i in result.referrerList){
								str+='<li class="petition_border">';
									str+='<p  class="text-center" attr_id='+result.referrerList[i].id+'>'+result.referrerList[i].name.toUpperCase()+'</p>';
									str+='<h5 class="text-center m_top5"><b title="Represents" data-toggle="tooltip" class="tooltipCls" data-placement="right">'+result.referrerList[i].petitionIds.length+'</b> - <b title="Works" data-toggle="tooltip" class="tooltipCls" data-placement="right">'+result.referrerList[i].subWorkIds.length+'</b></h5>';
									var convertToAmt = result.referrerList[i].estimationCost*100000;
									var crores = (convertToAmt/10000000).toFixed(2);
									str+='<h5 class="text-center"><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls" data-placement="right">('+crores+')</b></h5>';
									str+='<h5 class="m_top5 font_weight text-center"><span><i class="fa fa-inr"></i> '+result.referrerList[i].withCostPetitionsCount+'</span> - <span>'+result.referrerList[i].withCostWorksCount+'</span></h5>';
									str+='<h5 class=" font_weight text-center"><span><i class="fa fa-krw"></i> '+result.referrerList[i].noCostPetitionsCount+'</span> - <span>'+result.referrerList[i].noCostWorksCount+'</span></h5>';
								str+='</li>';
								
								}
						str+='</ul>';	
					str+='</div>';
				str+='</div>';
				
				
				str+='<div class="row pad_10">';
					str+='<div class="border-styling" style="border-top:1px solid #1283C9;"></div>';
						str+='<h5 class="grad_bg_lightblue" style="background-color:#E2F2F9;margin-left:5px">Subject wise</h5>';
						str+='<div class="col-sm-12 m_top5" style="padding-left: 30px;padding-right: 30px;">';
							str+='<ul class="list-inline subjectSlickCls m_top10">';
								for(var i in result.subList){
									str+='<li class="col-sm-2 petition_border">';
										str+='<p class="text-center" attr_id="'+result.subList[i].id+'">'+result.subList[i].name.toUpperCase()+'</p>';
										str+='<h5 class="text-center m_top5"><b title="Represents" data-toggle="tooltip" class="tooltipCls" data-placement="right">'+result.subList[i].petitionIds.length+'</b> - <b title="Works" data-toggle="tooltip" class="tooltipCls" data-placement="right">'+result.subList[i].subWorkIds.length+'</b></h5>';
										var convertToAmt = result.subList[i].estimationCost*100000;
										var crores = (convertToAmt/10000000).toFixed(2);
										str+='<h5 class="text-center"><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls" data-placement="right">('+crores+')</b></h5>';
										
										str+='<h5 class="m_top5 font_weight text-center"><span><i class="fa fa-inr"></i> '+result.subList[i].withCostPetitionsCount+'</span> - <span>'+result.subList[i].withCostWorksCount+'</span></h5>';
										str+='<h5 class=" font_weight text-center"><span><i class="fa fa-krw"></i> '+result.subList[i].noCostPetitionsCount+'</span> - <span>'+result.subList[i].noCostWorksCount+'</span></h5>';
									str+='</li>';
										
								}
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					
					
				str+='<div class="row pad_10">';
					str+='<div class="border-styling" style="border-top:1px solid #1283C9;"></div>';
						str+='<h5 class="grad_bg_lightblue" style="background-color:#E2F2F9;margin-left:5px">Department wise</h5>';
						str+='<div class="col-sm-12 m_top5" style="padding-left: 30px;padding-right: 30px;">';
							str+='<ul class="list-inline departmentSlickCls m_top10">';
								for(var i in result.deptList){
										str+='<li class="col-sm-2 petition_border">';
											str+='<p class="text-center" attr_id="'+result.deptList[i].id+'" style="font-size:11px"><b>'+result.deptList[i].name.toUpperCase()+'</b></p>';
											str+='<h5 class="text-center m_top5"><b title="Represents" data-toggle="tooltip" class="tooltipCls" data-placement="right">'+result.deptList[i].petitionIds.length+'</b> - <b title="Works" data-toggle="tooltip" class="tooltipCls" data-placement="right">'+result.deptList[i].subWorkIds.length+'</b></h5>';
											var convertToAmt = result.deptList[i].estimationCost*100000;
											var crores = (convertToAmt/10000000).toFixed(2);
											str+='<h5 class="text-center"><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls" data-placement="right">('+crores+')</b></h5>';
											
											str+='<h5 class="m_top5 font_weight text-center"><span><i class="fa fa-inr"></i> '+result.deptList[i].withCostPetitionsCount+'</span> - <span>'+result.deptList[i].withCostWorksCount+'</span></h5>';
											str+='<h5 class=" font_weight text-center"><span><i class="fa fa-krw"></i> '+result.deptList[i].noCostPetitionsCount+'</span> - <span>'+result.deptList[i].noCostWorksCount+'</span></h5>';
										str+='</li>';
										
								}
							str+='</ul>';
					str+='</div>';
				str+='</div>';
				
		str+='</div>';	
	str+='</div>';
	
	$("#completeOverviewId").html(str);
	$(".tooltipCls").tooltip();
	$(".referralSlickCls").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 4,
		slidesToScroll: 1,
		variableWidth: false
	 });
	 $(".subjectSlickCls").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 4,
		slidesToScroll: 1,
		variableWidth: false
	 });
	 $(".departmentSlickCls").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 4,
		slidesToScroll: 1,
		variableWidth: false
	 });
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
		str+='<div class="col-sm-3 m_top10">';
			str+='<div class="panel panel-default">';
				str+='<div class="panel-heading" style="background-color:#D6E8F2; color:#333;">';
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
							//str+='<h4 style="float:left; margin-right:5px;"><b>'+result[i].petitionIds.length+'</b></h4>';
							str+='<h4 class="font_weight"><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=lead&desigId=0&leadId='+result[i].id+'&deptId=0" target="_blank">'+result[i].petitionIds.length+'</a> </h4>';
							
							var convertToAmt = result[i].estimationCost*100000;
							var crores = (convertToAmt/10000000).toFixed(2);
							str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<p>Works</p>';
							//str+='<h4><b>'+result[i].subWorkIds.length+'</b></h4>';
							str+='<h4 class="font_weight"><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=lead&desigId=0&leadId='+result[i].id+'&deptId=0" target="_blank">'+result[i].subWorkIds.length+'</a> </h4>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="row m_top5">';
						str+='<div class="col-sm-6">';
							str+='<h5 class="m_top5 font_weight "><span><i class="fa fa-inr"></i> '+result[i].withCostPetitionsCount+'</span></h5>';
						str+='<h5 class=" font_weight"><span><i class="fa fa-krw"></i> '+result[i].noCostPetitionsCount+'</span></h5>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<h5 class="m_top5 font_weight "><span><i class="fa fa-inr"></i> '+result[i].withCostWorksCount+'</span></h5>';
						    str+='<h5 class=" font_weight "><span><i class="fa fa-krw"></i> '+result[i].noCostWorksCount+'</span></h5>';
						str+='</div>';
						
					str+='</div>';
					
					str+='<div class="row m_top5">';
						for(var j in result[i].statusList){
							str+='<div class="col-sm-6" style="padding:2px">';
								str+='<div class="well pad_5 m_bottom_0">';
									str+='<div class="row">';
										if(result[i].statusList[j].id == 1){
											var convertToAmt = result[i].statusList[j].estimationCost*100000;
											var crores = (convertToAmt/10000000).toFixed(2);
											/* str+='<div class="col-sm-6"><span>Pending</span><br><b>'+result[i].statusList[j].petitionIds.length+'</b>';
											
											str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
											str+='</div>'; */
											
											str+='<div class="col-sm-6">';
												str+='<h5 class="font_weight">Pending</h5>';
												str+='<h5 class="font_weight">'+result[i].statusList[j].petitionIds.length+'</h5>';
												str+='<h5 class="font_weight" data-toggle="tooltip" title="Total Budget" class="tooltipCls font_weight" style="color:#000">('+crores+')</h5>';
											str+='</div>';
											
										}else if(result[i].statusList[j].id == 2){
											str+='<div class="col-sm-6"><span>Completed</span><br><span class="m_top10"><b >'+result[i].statusList[j].petitionIds.length+'</b></span></div>';
										}
										//str+='<div class="col-sm-6"><span>Pending</span><br><b>'+result[i].statusList[j].totalRepresents+'</b></div>';
										str+='<div class="col-sm-6"><span>Works</span><br><span class="m_top10"><b >'+result[i].statusList[j].subWorkIds.length+'</b></span></div>';
										
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
	var desgName = $(this).attr("attr_desg_name");
	var desCapText;
	if(designationId == 0){
		desCapText ="OTHERS";
		$("#headingDetailsId").html(desCapText+" Refferal Wise OverView Details".toUpperCase());
	}else if(designationId == 7){
		desCapText ="STATE MINISTER";
		$("#headingDetailsId").html(desCapText+" Refferal Wise OverView Details".toUpperCase());
	}else{
		$("#headingDetailsId").html(desgName+" Refferal Wise OverView Details".toUpperCase());
	}
	 $('#LeadersId').val(designationId);
	 $('#LeadersId').trigger('chosen:Updated');
	getReferralWiseOverviewDetails(designationId);
	$('.desigClsDivId').removeClass("activeCls");
	$(this).addClass("activeCls");
});
$(document).on("change","#briefLeadId",function(){
	$("#desigWiseCandidatesView").html("");
	//$("#desigWiseCountId").html("");
	var designationId = '';
	/*$(".desigClsDivId").each(function(){
				if($(this).hasClass("activeCls")){
					designationId = $(this).attr("attr_desigId");
				}
			}); */
	getReferralWiseOverviewDetails(designationId);
});
$(document).on("change","#LeadersId",function(){
	$("#desigWiseCandidatesView").html("");
	//$("#desigWiseCountId").html("");
	var designationId = '';
	designationId = $(this).val();
	var desgName=$(this).find('option:selected').text();
	var desCapText;
	if(designationId == 0){
		desCapText ="OTHERS";
		//alert(desCapText);
		$("#headingDetailsId").html(desCapText+" Refferal Wise OverView Details".toUpperCase());
	}else if(designationId == 7){
		desCapText ="STATE MINISTER";
		$("#headingDetailsId").html(desCapText+" Refferal Wise OverView Details".toUpperCase());
	}else{
		$("#headingDetailsId").html(desgName+" Refferal Wise OverView Details".toUpperCase());
	}
	
	$('.desigClsDivId').removeClass("activeCls");
	$('.desigClsDivId').each(function()
	{
		var textName=$(this).attr('attr_desg_name');
		if(textName==desgName)
		{
			$(this).addClass('activeCls');
		}
		
	});
	//$("#headingDetailsId").html(desgName+" Refferal Wise OverView Details".toUpperCase());	
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
	var str2='';
	  for(var i in result.subList){
		
	   str+='<div class="col-sm-3 m_top10" id="column2">';
	   if(i==0)
	   {
	   str+='<div class="panel panel-default desigClsDivId activeCls"  style="cursor:pointer;" attr_desigId="'+result.subList[i].deptDesigId+'" attr_desg_name="'+result.subList[i].desigName+'">';
	   }
		else
		{
	   str+='<div class="panel panel-default desigClsDivId"  style="cursor:pointer;" attr_desigId="'+result.subList[i].deptDesigId+'" attr_desg_name="'+result.subList[i].desigName+'">';
		}
		str+='<div class="panel-heading" style="background-color:#D2DEF1; color:#333;"><h4><b>'+result.subList[i].desigName+'-'+result.subList[i].subWorkIds.length+'</b></h4></div>';
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
					 var convertToAmt = result.subList[i].estimationCost*100000;
                     var crores = (convertToAmt/10000000).toFixed(2);
                     str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
					str+='</div>';				
				str+='</div>';
					
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str2+='<option value ="'+result.subList[i].deptDesigId+'">'+result.subList[i].desigName+'</option>'
	  }
	   if(result.subList != null && result.subList.length>0){
		  getReferralWiseOverviewDetails(result.subList[0].deptDesigId);
	  } 
	  
	$("#desigWiseCountId").html(str);
	$("#LeadersId").html(str2);
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

function buildOfficerBlock(result,loginUsr){
	//alert(2)
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
			for(var i in result){
			if( loginUsr!= 23 || result[i].id != 2 ){
				str+='<div class="col-sm-3">';
					str+='<div class="panel panel-default officerWiseBlockCls" attr_desig_id='+result[i].id+'>';
					if(i==0)
					  str+='<div class="panel-heading panel_active">';
					else
					  str+='<div class="panel-heading">';
						str+='<h3 class="panel-title" style="color:#000"><img src="Assests/icons/Green.png"> <span class="m_left">'+result[i].name+'</span></h3>';
					  str+='</div>';
					  if(i==0)
						str+='<div class="panel-body desig_bg officer_bg_Css">';
					  else
						str+='<div class="panel-body officer_bg_Css">';	
						str+='<div class="border_bottom_css">';
							str+='<div class="row">';
								str+='<div class="col-sm-6">';
									str+='<h5 class="font_weight text-center">Representations</h5>';
									str+='<h4 class="font_weight m_top10 text-center">'+result[i].petitionIds.length+'</h4>';
								str+='</div>';
								str+='<div class="col-sm-6">';
									str+='<h5 class="font_weight text-center">Works</h5>';
									str+='<h4 class="font_weight m_top10 text-center">'+result[i].subWorkIds.length+'</h4>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						var pendingRep =0;
						var pendingWorks=0;
						for(var j in result[i].subList){
							if(result[i].subList[j].id != 4 && result[i].subList[j].id != 5 ){
								if(typeof(result[i].subList[j].petitionIds) != "undefined")
									pendingRep=pendingRep+result[i].subList[j].petitionIds.length;
								if(typeof(result[i].subList[j].subWorkIds) != "undefined")
									pendingWorks =pendingWorks+result[i].subList[j].subWorkIds.length;
							}
						}
						str+='<div class="desig_bg_white m_top10">';
							str+='<div class="row">';
								str+='<div class="col-sm-4">';
									str+='<h5 class="font_weight m_top5">Pending</h5>';
								str+='</div>';
								str+='<div class="col-sm-3">';
									str+='<h5 class="font_weight m_top5">'+pendingRep+'</h5>';
								str+='</div>';
								str+='<div class="col-sm-5">';
									str+='<div class="bg_yash_5">';
										str+='<div class="row">';
											str+='<div class="col-sm-6">';
												str+='<h5 class="font_weight">Works</h5>';
											str+='</div>';
											str+='<div class="col-sm-6">';
												str+='<h5 class="font_weight">'+pendingWorks+'</h5>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						
					  str+='</div>';
					str+='</div>';
				str+='</div>';
			}
	}
			
		if(result[0].id != null && result[0].id>0){
		  getPmOfficerWisePetitionDetails(result[0].id,"OfficerDetails",loginDesigId);
	  } 	
			
	  str+='</div>';			
   str+='</div>';			
	$("#officerWiseBlockDetailsDivId").html(str);
}

$(document).on("click",".officerWiseBlockCls",function(){
	$(".officerWiseBlockCls").find('.panel-heading').removeClass("panel_active");
	$(this).find('.panel-heading').addClass("panel_active");
	
	$(".officer_bg_Css").removeClass("desig_bg");
	$(this).parent().find('.officer_bg_Css').addClass("desig_bg");
	var desigId = $(this).attr("attr_desig_id");
	getPmOfficerWisePetitionDetails(desigId,"OfficerDetails",loginDesigId)
	//tableBuildOfficerBlock();
});
function getPmOfficerWisePetitionDetails(desiId,dataType,loginUsr){
	var desigIds = [];
	if(desiId != ""){
		$("#officerDesignationWiseTableDivId").html(spinner);
		desigIds.push(desiId);
	}else{
		$("#officerWiseBlockDetailsDivId").html(spinner);
	}
	var json = {
		designationIds:desigIds,
		displayType:dataType
	};
	$.ajax({              
		type:'POST',    
		url: 'getPmOfficerWisePetitionDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null ){
			if(desiId == ""){
				buildOfficerBlock(result,loginUsr);
			}else{
				tableBuildOfficerBlock(result);
			}
		}else{
			
		}
	});	
}
function tableBuildOfficerBlock(result){
	var str='';
	var str='';
			str+='<div class="col-md-12">';
			str+='<div class="table-responsive">';
				str+='<table class="table table-bordered" id="officerTableid">';
					str+='<thead>';
						str+='<tr>';
							str+='<th>Name</th>';
							str+='<th>Total Representations</th>';
							str+='<th>Works</th>';
							str+='<th>Estimation Cost</th>';
							for(var j in result[0].subList){
								if(result[0].subList[j].id != 4 && result[0].subList[j].id != 5 ){
								str+='<th>'+result[0].subList[j].name+'</th>';
								str+='<th>Works</th>';
								str+='<th>Estimation Cost</th>';
							  }
							}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
							str+='<tr style="text-align:center;">';
								str+='<td><h5><b>'+result[i].name+'</b><h5>';
								//str+='<p>'+result.referrerList[i].desigName+'</p>';
								str+='</td>';
								
								if(result[i].petitionIds.length >0){
									str+='<td>'+result[i].petitionIds.length+'</td>';
								}else{
									str+='<td>-</td>';
								}
								if(result[i].subWorkIds.length >0){
									str+='<td>'+result[i].subWorkIds.length+'</td>';
								}else{
									str+='<td>-</td>';
								}
								if(typeof(result[i].estimationCost) != "undefined" && result[i].estimationCost != 0){
										var convertToAmt = result[i].estimationCost*100000;
										var crores = (convertToAmt/10000000).toFixed(2);
										str+='<td><b>'+crores+'</b></td>';
								}else{
										str+='<td><b>-</b></td>';
								}	
								
								for(var j in result[i].subList){
										/* var statusIds= '';
										if(result.referrerList[i].statusList[j].id == 1){
											statusIds='1,3,6,7';
										}else if(result.referrerList[i].statusList[j].id == 2){
											statusIds='5';
										}if(result.referrerList[i].statusList[j].id == 3){
											statusIds='4,8';
										}
										 */
									if(result[i].subList[j].id != 4 && result[i].subList[j].id != 5 ){
										if(typeof(result[i].subList[j].petitionIds) != 'undefined' && result[i].subList[j].petitionIds.length >0){
											str+='<td>'+result[i].subList[j].petitionIds.length+'</td>';
										}else{
											str+='<td>-</td>';
										}
									
										if(typeof(result[i].subList[j].subWorkIds) != 'undefined' && result[i].subList[j].subWorkIds.length>0){
											str+='<td>'+result[i].subList[j].subWorkIds.length+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(typeof(result[i].subList[j].estimationCost) != "undefined" && result[i].subList[j].estimationCost != 0){
											var convertToAmt = result[i].subList[j].estimationCost*100000;
											var crores = (convertToAmt/10000000).toFixed(2);
											str+='<td><b>'+crores+'</b></td>';
										}else{
											str+='<td><b>-</b></td>';
										}
									 }									
								}
									//for(var j in result.referrerList[i].statusList){
									
								//}	
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';	
	
	$("#officerDesignationWiseTableDivId").html(str);
}