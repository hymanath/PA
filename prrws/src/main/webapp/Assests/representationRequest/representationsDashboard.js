var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var currentFromDate=moment().subtract(7, 'year').format("DD-MM-YYYY");
var currentToDate=moment().add(38,"year").format("DD-MM-YYYY");
/* $("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menuCls").toggle();
});
 $(document).on("click",function(){
	$(".menu-data-cls").hide();
});  */
getDeptIdsListBYUserIdsLst();
$(".chosen-select").chosen();

$("#dateRangePicker").daterangepicker({
		opens: 'left',
		startDate: currentFromDate,
		endDate: currentToDate,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		ranges: {
		   'All':[moment().subtract(7,"year").format("DD-MM-YYYY"), moment().add(38,"year").format("DD-MM-YYYY")],
		   'Today' : [moment(), moment()],
		   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   
		}
	});
	
	var dates= $("#dateRangePicker").val();
	var pickerDates = currentFromDate+' - '+currentToDate
	if(dates == pickerDates)
	{
		$("#dateRangePicker").val('All');
	}

	$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
		currentFromDate = picker.startDate.format('DD-MM-YYYY');
		currentToDate = picker.endDate.format('DD-MM-YYYY');
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePicker").val('All');
		}
		onLoadCalls();
		
	});
function onLoadCalls(){
	//getDeptIdsListBYUserIdsLst();
	getCompleteOrStatusOverviewDetails();
	$("#officerBlock").hide();
	if(loginDesigId == 2 || loginDesigId == 23 || loginDesigId ==86){
		$("#refWiseOverViewId").show();
		$("#locationWiseBlockId").show();
		$("#leadWiseDivId").show();
		$("#desigWiseCountDivId").show();
		$("#refWiseOverViewDivId").show();
		$("#officerBlock").show();
		getDepartmentsBySearchType("department","departmntId",0,0);
		getStatusList(0);
		getSubjectsBySearchType("subject","subjectId",0,0);

		getLocationWiseRepresentationsOverviewDetails("district","districtWiseLocationDetailsDivId");
		getLocationWiseRepresentationsOverviewDetails("constituency","constituencyWiseLocationDetailsDivId");
		getReferralWiseOverviewDetails("");
		getBriefLeads();
		getLeadWiseOverviewDetails();
		getPmOfficerWisePetitionDetails("","",loginDesigId);
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
	
	var departmentIdMainList =[];
	var deptIds =  $("#departmntId").val();
		if(deptIds != null && deptIds.length >0){
			departmentIdMainList=deptIds;
		}
	if(departmentIdMainList != null && departmentIdMainList.length>0){
		for(var i in departmentIdMainList){
			if(parseInt(departmentIdMainList[i])==0){
				departmentIdMainList=[];
			}
		}
	}
	
var json = {
		 fromDate :currentFromDate,
		 toDate:currentToDate,
		 deptIdsList:departmentIdMainList
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
	//str+='<ul class="list-inline slickSilderStatusOverView">';
	if(result != null && result.list != null && result.list.length >0){
	
		for(var i in result.list){
			if(i==6 || parseInt(result.list[i].id) == 9  || parseInt(result.list[i].id) == 10 || parseInt(result.list[i].id) == 11 || parseInt(result.list[i].id) == 12)
				continue;
			
		str+='<div class="col-sm-3 status_blocks m_top10">';
			str+='<div class="panel panel-default">';
				//str+='<div class="panel-heading" style="background-color:#FFF8EF">';
				if(result.list[i].id ==1){
					str+='<div class="panel-heading" style="background-color:#ECEEEF;border:1px solid #ddd;">';
					str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4363.png" style="display:inline-block">';
				}else if(result.list[i].id ==2){
					str+='<div class="panel-heading" style="background-color:#FFF8EF;border:1px solid #ddd;">';
					
					str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4362.png" style="display:inline-block">';
				}else if(result.list[i].id ==8 ){
					str+='<div class="panel-heading" style="background-color:#E5F6Ed;border:1px solid #ddd;">';
					str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4364.png" style="display:inline-block">';
				}else if(result.list[i].id ==4 ){
					str+='<div class="panel-heading" style="background-color:#FCEDFF;border:1px solid #ddd;">';
					str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4369.png" style="display:inline-block">';
				}else if(result.list[i].id ==5 ){
					str+='<div class="panel-heading" style="background-color:#FDE8F5;border:1px solid #ddd;">';
					str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4427.png" style="display:inline-block">';
				}else{
					str+='<div class="panel-heading" style="background-color:#FDE8F5;border:1px solid #ddd;">';
					str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4369.png" style="display:inline-block">';
				}
				str+='<b style="padding-left:5px;font-size:13px;color:#000;">'+result.list[i].name+'</b></h5>';
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
		//str+='</ul>';
		
		$("#statusOverviewId").html(str);
		$(".tooltipCls").tooltip();
		/* $(".slickSilderStatusOverView").slick({
			slides:'li',
			infinite: false,
			slidesToShow: 4,
			slidesToScroll: 1,
			variableWidth: false
		 }); */
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
									str+='<div id="collapseView1'+i+'" class="panel-collapse collapsePan" role="tabpanel" aria-labelledby="headingView1'+i+'">';
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
																
																str+='<h4 class="font_weight"><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=total&desigId=0&statusId='+result.statusList[i].id+'&deptId=0&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.statusList[i].petitionIds.length+'</a> <small data-toggle="tooltip" title="Total Budget" class="tooltipCls font_weight">('+crores+')</small></h4>';
							
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
																str+='<h4><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=total&desigId=0&statusId='+result.statusList[i].id+'&deptId=0&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.statusList[i].subWorkIds.length+'</a></h4>';
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
															str+='<h5  class="text-center m_top5"><a title="Represents" data-toggle="tooltip" class="tooltipCls" data-placement="right" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.statusList[i].referrerList[j].id+'&statusId='+result.statusList[i].id+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.statusList[i].referrerList[j].petitionIds.length+'</a> - <a title="Works" data-toggle="tooltip" class="tooltipCls" data-placement="right" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.statusList[i].referrerList[j].id+'&statusId='+result.statusList[i].id+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.statusList[i].referrerList[j].subWorkIds.length+'</a></h5>';
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
													str+='<h5 class="text-center m_top5"><a title="Represents" data-toggle="tooltip" class="tooltipCls" data-placement="right" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=subject&subjId='+result.statusList[i].subList[j].id+'&statusId='+result.statusList[i].id+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.statusList[i].subList[j].petitionIds.length+'</a> - <a title="Works" data-toggle="tooltip" class="tooltipCls" data-placement="right" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=subject&subjId='+result.statusList[i].subList[j].id+'&statusId='+result.statusList[i].id+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.statusList[i].subList[j].subWorkIds.length+'</a></h5>';
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
													str+='<h5 class="text-center m_top5"><a title="Represents" data-toggle="tooltip" class="tooltipCls" data-placement="right" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=department&desigId=0&statusId='+result.statusList[i].id+'&deptId='+result.statusList[i].deptList[j].id+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.statusList[i].deptList[j].petitionIds.length+'</a> - <a data-toggle="tooltip" class="tooltipCls" data-placement="right" title="Works" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=department&desigId=0&statusId='+result.statusList[i].id+'&deptId='+result.statusList[i].deptList[j].id+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.statusList[i].deptList[j].subWorkIds.length+'</a></h5>';
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
	$('.collapsePan').addClass('collapse');
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
	
	var departmentIdMainList =[];
	var deptIds =  $("#departmntId").val();
		if(deptIds != null && deptIds.length >0){
			departmentIdMainList=deptIds;
		}
	if(departmentIdMainList != null && departmentIdMainList.length>0){
		for(var i in departmentIdMainList){
			if(parseInt(departmentIdMainList[i])==0){
				departmentIdMainList=[];
			}
		}
	}
var json = {
		 fromDate :currentFromDate,
		 toDate:currentToDate,
		 deptIdsList:departmentIdMainList
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
	//str+='<ul class="list-inline slickSilderLeadWiseOverView">';
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
							str+='<h4 class="font_weight"><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=lead&desigId=0&leadId='+result[i].id+'&deptId=0&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result[i].petitionIds.length+'</a> </h4>';
							
							var convertToAmt = result[i].estimationCost*100000;
							var crores = (convertToAmt/10000000).toFixed(2);
							str+='<h5><b  data-toggle="tooltip" title="Total Budget" class="tooltipCls">('+crores+')</b></h5>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<p>Works</p>';
							//str+='<h4><b>'+result[i].subWorkIds.length+'</b></h4>';
							str+='<h4 class="font_weight"><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=lead&desigId=0&leadId='+result[i].id+'&deptId=0&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result[i].subWorkIds.length+'</a> </h4>';
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
	//str+='</ul>';
	$("#leadWiseOverviewId").html(str);
	$(".tooltipCls").tooltip();
	/* $(".slickSilderLeadWiseOverView").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 4,
		slidesToScroll: 1,
		variableWidth: false
	 }); */
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
	
	var departmentIdMainList =[];
	var deptIds =  $("#departmntId").val();
		if(deptIds != null && deptIds.length >0){
			departmentIdMainList=deptIds;
		}
	if(departmentIdMainList != null && departmentIdMainList.length>0){
		for(var i in departmentIdMainList){
			if(parseInt(departmentIdMainList[i])==0){
				departmentIdMainList=[];
			}
		}
	}
	
	var json = {
		  designationIds:desigIds,
		  lightVendorIdList:briefLeadIds,
		  fromDate : currentFromDate,
		  toDate : currentToDate,
		  deptIdsList : departmentIdMainList
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
									str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referralCan&desigId='+result.referrerList[i].deptDesigId+'&refCanId='+result.referrerList[i].id+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.referrerList[i].petitionIds.length+'</a></td>';
								}else{
									str+='<td>-</td>';
								}
								if(result.referrerList[i].subWorkIds.length >0){
									str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referralCan&desigId='+result.referrerList[i].deptDesigId+'&refCanId='+result.referrerList[i].id+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.referrerList[i].subWorkIds.length+'</a></td>';
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
											str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referralCan&desigId='+result.referrerList[i].deptDesigId+'&statusId='+statusIds+'&refCanId='+result.referrerList[i].id+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.referrerList[i].statusList[j].petitionIds.length+'</a></td>';
										}else{
											str+='<td>-</td>';
										}
									
									if(result.referrerList[i].statusList[j].subWorkIds.length >0){
										str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referralCan&desigId='+result.referrerList[i].deptDesigId+'&statusId='+statusIds+'&refCanId='+result.referrerList[i].id+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result.referrerList[i].statusList[j].subWorkIds.length+'</a></td>';
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
	var str='';
			var xindex = 0;
			for(var i in result){
				if( xindex == 0){
					str+='<div class="col-sm-12">';
				}
				
			if( loginUsr!= 23 || result[i].id != 2 ){
				str+='<div class="col-sm-3 m_top10">';
					if(i==0 && loginUsr!= 23){
						str+='<div class="panel panel-default officerWiseBlockCls panel_active" style="cursor:pointer;" attr_desig_id='+result[i].id+'>';
					}else if(i==1 && loginUsr== 23){
						str+='<div class="panel panel-default officerWiseBlockCls panel_active" style="cursor:pointer;" attr_desig_id='+result[i].id+'>';
					}else if(i==1 && loginUsr== 23){
						str+='<div class="panel panel-default officerWiseBlockCls panel_active" style="cursor:pointer;" attr_desig_id='+result[i].id+'>';
					}else{
						str+='<div class="panel panel-default officerWiseBlockCls dafault-panelCLS" style="cursor:pointer;" attr_desig_id='+result[i].id+'>';
					}
					str+='<div class="panel-heading" >';
						str+='<h3 class="panel-title"><i class="fa fa-user" style="font-size:18px"></i><span class="m_left">'+result[i].name+'</span></h3>';
					str+='</div>';
					  
					  if(i==0)
						str+='<div class="panel-body desig_bg officer_bg_Css">';
					  else
						str+='<div class="panel-body desig_bg officer_bg_Css">';	
						str+='<div class="border_bottom_css">';
							str+='<div class="row">';
								str+='<div class="col-sm-6">';
									str+='<h5 class="font_weight text-center">Representations</h5>';
									if(typeof(result[i].petitionIds) != "undefined" && result[i].petitionIds.length >0)
										str+='<h4 class="font_weight m_top10 text-center">'+result[i].petitionIds.length+'</h4>';
									else
										str+='<h4 class="font_weight m_top10 text-center">-</h4>';
								str+='</div>';
								str+='<div class="col-sm-6">';
									str+='<h5 class="font_weight text-center">Works</h5>';
									if(typeof(result[i].subWorkCnt) != "undefined" && result[i].subWorkCnt >0)
										str+='<h4 class="font_weight m_top10 text-center">'+result[i].subWorkCnt+'</h4>';
									else
										str+='<h4 class="font_weight m_top10 text-center">-</h4>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						var pendingRep =0;
						var pendingWorks=0;
						for(var j in result[i].subList){
							if(result[i].subList[j].id != 4 && result[i].subList[j].id != 5 ){
								if(typeof(result[i].subList[j].petitionIds) != "undefined")
									pendingRep=pendingRep+result[i].subList[j].petitionIds.length;
								if(typeof(result[i].subList[j].subWorkCnt) != "undefined")
									pendingWorks =pendingWorks+result[i].subList[j].subWorkCnt;
							}
						}
						str+='<div class="desig_bg_white m_top10">';
							str+='<div class="row">';
								str+='<div class="col-sm-3">';
									str+='<h5 class="font_weight m_top5">Pending</h5>';
								str+='</div>';
								str+='<div class="col-sm-3">';
									str+='<h5 class="font_weight m_top5">'+pendingRep+'</h5>';
								str+='</div>';
								str+='<div class="col-sm-6">';
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
			xindex++;
			if(result.length-1 == i){
				if(xindex % 4 == 1){
					str+='<div class="col-sm-3"></div>';
					str+='</div>';
				}
			}
			 if( xindex == 4){
				str+='</div>';
				xindex = 0;
			}
	}
			
		if(result[0].id != null && result[0].id>0 && loginUsr!= 23){
		  getPmOfficerWisePetitionDetails(result[0].id,"OfficerDetails",loginDesigId);
	  } else if(result[1].id != null && result[1].id>0){
		  getPmOfficerWisePetitionDetails(result[1].id,"OfficerDetails",loginDesigId);
	  }	
			
	  			
   $("#officerWiseBlockDetailsDivId").html(str);
	
}

$(document).on("click",".officerWiseBlockCls",function(){
	$(".officerWiseBlockCls").removeClass("panel_active");
	
	$(".officerWiseBlockCls").addClass("dafault-panelCLS");
	$(this).removeClass("dafault-panelCLS");
	$(this).addClass("panel_active");
	
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
	
	var departmentIdMainList =[];
	var deptIds =  $("#departmntId").val();
		if(deptIds != null && deptIds.length >0){
			departmentIdMainList=deptIds;
		}
	if(departmentIdMainList != null && departmentIdMainList.length>0){
		for(var i in departmentIdMainList){
			if(parseInt(departmentIdMainList[i])==0){
				departmentIdMainList=[];
			}
		}
	}
	
	var json = {
		designationIds:desigIds,
		displayType:dataType,
		fromDate: currentFromDate,
		toDate: currentToDate,
		deptIdsList : departmentIdMainList
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
								
								if(typeof(result[i].petitionIds) != "undefined" && result[i].petitionIds.length >0){
									str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=officer&officerId='+result[i].id+'&statusId=0&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result[i].petitionIds.length+'</a></td>';
								}else{
									str+='<td>-</td>';
								}
								if(typeof(result[i].subWorkCnt) != "undefined" && result[i].subWorkCnt >0){
									str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=officer&officerId='+result[i].id+'&statusId=0&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result[i].subWorkCnt+'</a></td>';
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
											str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=officer&officerId='+result[i].id+'&statusId='+result[i].subList[j].id+'&statusName='+result[i].subList[j].name+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result[i].subList[j].petitionIds.length+'</a></td>';
										}else{
											str+='<td>-</td>';
										}
									
										if(typeof(result[i].subList[j].subWorkCnt) != 'undefined' && result[i].subList[j].subWorkCnt>0){
											str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=officer&officerId='+result[i].id+'&statusId='+result[i].subList[j].id+'&statusName='+result[i].subList[j].name+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result[i].subList[j].subWorkCnt+'</a></td>';
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
	$("#officerDesignationWiseTableDivId").html(str);
	$("#officerTableid").dataTable({
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
 
function getLocationWiseRepresentationsOverviewDetails(locationtype,divId){
	var selStatusId = $("#statusLocId").val();
	var statusIds = [];
	if(selStatusId != null && selStatusId.length >0){
		statusIds=selStatusId;
	}
	if(locationtype == "district"){
		$("#districtWiseLocationDetailsDivId").html(spinner);
	}else{
		$("#constituencyWiseLocationDetailsDivId").html(spinner);
	}
	var subjArr = [];
	var subjIds =$("#subjectId").val();
	if(subjIds != null && subjIds !=0){
		subjArr=subjIds;
	}
	var deptIds =  $("#departmntId").val();
	var deptIdsList = [];
		if(deptIds != null && deptIds.length >0){
			deptIdsList=deptIds;
		}
	if(deptIdsList != null && deptIdsList.length>0){
		for(var i in deptIdsList){
			if(parseInt(deptIdsList[i])==0){
				deptIdsList=[];
			}
		}
	}
var json = {
		 deptIdsList :deptIdsList,
		 statusIds:statusIds,
		 lightVendorIdList:subjArr,
		 assetType:locationtype,
		 fromDate:currentFromDate,
		 toDate:currentToDate
		}           
	$.ajax({              
		type:'POST',    
		url: 'getLocationWiseRepresentationsOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildLocationWiseRepresentationsOverviewDetails(result,locationtype,divId);
		}else{
			$("#"+divId).html("No Data Available");
		}
		
	});	
}
function buildLocationWiseRepresentationsOverviewDetails(result,locationtype,divId){
	var str='';
	var selStatusId = $("#statusLocId").val();
	var selDptId = $("#departmntId").val();
	var selSubjId = $("#subjectId").val();
	var searchBy = '';
	if(locationtype == "district"){
		//str+='<h4 class="font_weight0"> DISTRICT WISE REPRESENTATIONS OVERVIEW </h4>';
		searchBy=locationtype;
	}else{
		//str+='<h4 class="font_weight0">CONSTITUENCY WISE REPRESENTATIONS OVERVIEW </h4>';
		searchBy=locationtype;
	}
	if(selSubjId != null && selSubjId != 0){
		searchBy = "subject";
	}
	str+='<div class="table-responsive m_top10">';
		str+='<table class="table table-bordered" id="dataTableLoc'+divId+'">';
			str+='<thead>';
				str+='<tr>';
				if(locationtype == "district"){
					str+='<th>DISTRICT</th>';
				}else{
					str+='<th>DISTRICT</th>';
					str+='<th>CONSTITUENCY</th>';
				}
				str+='<th>TOTAL&nbsp;REPR</th>';
				str+='<th>TOTAL&nbsp;WORKS</th>';
				str+='<th>WITH&nbsp;COST&nbsp;<br>REPR</th>';
				str+='<th>WITH&nbsp;COST&nbsp;<br>WORKS</th>';
				str+='<th>EST&nbsp;COST</th>';
				str+='<th>WITHOUT&nbsp;<br>COST&nbsp;REPR</th>';
				str+='<th>WITHOUT&nbsp;<br>COST&nbsp;WORKS</th>';
				
				if(locationtype == "district"){
					;
				}else{
						str+='<th> </th>';
				}
				
			
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			var distId = 0;
			var constId =0;
			
				for(var i in result){
					str+='<tr>';
						if(locationtype == "district"){
							str+='<td class="text-capital">'+result[i].locationName+'</td>';
							distId=result[i].locationId;
						}else{
							constId=result[i].locationId;
							distId=result[i].id;
							str+='<td class="text-capital">'+result[i].name+'</td>';
							str+='<td class="text-capital"> <a  title=" Click here to get '+result[i].locationName+' constituency page." href="'+wurl+'/constituencyWiseWorkStatus?distId='+distId+'&constId='+constId+'&deptId='+selDptId+'" target="_blank">'+result[i].locationName+'</a></td>';
						}
						
						if(result[i].petitionIds.length !=null && result[i].petitionIds.length>0){
							str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy='+searchBy+'&distId='+distId+'&constId='+constId+'&deptId='+selDptId+'&subjId='+selSubjId+'&statusId='+selStatusId+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+result[i].petitionIds.length+'</a></td>';
						}else{
							str+='<td> - </td>';
						}
						
						if(result[i].withOutCostSubWorkIds !=null && result[i].subWorkIds !=null){
							var totalWorks =result[i].subWorkIds.length+result[i].withOutCostSubWorkIds.length; 
							str+='<td><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy='+searchBy+'&distId='+distId+'&constId='+constId+'&deptId='+selDptId+'&subjId='+selSubjId+'&statusId='+selStatusId+'&fromDate='+currentFromDate+'&toDate='+currentToDate+'" target="_blank">'+totalWorks+'</a></td>';
						}else{
							str+='<td> - </td>';
						}
						if(result[i].petitionIds !=null && result[i].petitionIds.length>0 && result[i].withOutCostPetitionIds !=null){
							var withCostRep = result[i].petitionIds.length-result[i].withOutCostPetitionIds.length;
							if(withCostRep >0){
								str+='<td>'+withCostRep+'</td>';
							}else{
								str+='<td> - </td>';
							}
						}else{
							str+='<td> - </td>';
						}
						if(result[i].subWorkIds !=null && result[i].subWorkIds.length>0){
							str+='<td>'+result[i].subWorkIds.length+'</td>';
						}else{
							str+='<td> - </td>';
						}
						if(typeof(result[i].estimationCost) != "undefined" && result[i].estimationCost != 0){
								var convertToAmt = result[i].estimationCost*100000;
								var crores = (convertToAmt/10000000).toFixed(2);
								str+='<td><b>'+crores+'</b></td>';
						}else{
								str+='<td><b>-</b></td>';
						}
						//str+='<td>'+result[i].estimationCost+'</td>';
						if(result[i].withOutCostPetitionIds !=null && result[i].withOutCostPetitionIds.length>0){
							str+='<td>'+result[i].withOutCostPetitionIds.length+'</td>';
						}else{
							str+='<td> - </td>';
						}
						
						if(result[i].withOutCostSubWorkIds !=null && result[i].withOutCostSubWorkIds.length>0){
							str+='<td>'+result[i].withOutCostSubWorkIds.length+'</td>';
						}else{
							str+='<td> - </td>';
						}
						
						if(locationtype == "district"){
							;
						}else{
							str+='<td style="text-align:center;"> <button class="btn btn-md btn-warning btn-xs prntCls" attr_distict_name="'+result[i].name+'" attr_const_name="'+result[i].locationName+' " attr_id="'+result[i].locationId+'" title="Click here to download PDF document." > PRINT </button>  <span id="loadingId'+result[i].locationId+'"> </span></td>';
						}
						
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
		str+='</div>';
		
		$("#"+divId).html(str);
		$("#dataTableLoc"+divId).dataTable({
			"iDisplayLength": 20,
			"aaSorting": [[ 2, "desc" ]],
			"aLengthMenu": [[20, ,50,100, -1], [20,50,100, "All"]]
		});
		
		
}
function getDepartmentsBySearchType(searchType,selBoxId,ondeptId,statusId){
	var selStatusId = $("#statusLocId").val();
	var statusIds = [];
	if(selStatusId != null && selStatusId.length >0){
		statusIds=selStatusId;
	}else if(statusId.length >0){
		var statusList = statusId.split(',');
		for(var i=0;i<=statusList.length-1;i++){
			statusIds.push(statusList[i]);
		}
	}
	var dptId = $("#departmntId").val();
	if(dptId != null && dptId.length >0){
		ondeptId=dptId;
	}
 var json = {
		 reportType :searchType,
		 fromDate :currentFromDate,
		 toDate : currentToDate,
		 departmentId:ondeptId,
		 statusId:statusIds
		}           
	$.ajax({              
		type:'POST',    
		url: 'getDepartmentsBySearchType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#"+selBoxId).empty();
		if(result !=null && result.length >0){
			$("#departMentsDiv").show();
			//$("#"+selBoxId).html("<option value='0' selected>All Departments</option>");
			for(var i in result){
				//if(deptId != null && deptId==result[i].key){
					//$("#"+selBoxId).append("<option value='"+result[i].key+"' selected>"+result[i].value+"</option>");
				//}else{
					$("#"+selBoxId).append("<option value='"+result[i].key+"' >"+result[i].value+"</option>");
				//}
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}

function getSubjectsBySearchType(searchType,selBoxId,subjectId,statusId){
	$("#"+selBoxId).html("");
	$("#"+selBoxId).empty();
	$("#"+selBoxId).trigger('chosen:updated');
	var selStatusId = $("#statusLocId").val();
	//alert(statusId)
	var statusIds = [];
	if(selStatusId != null && selStatusId.length >0){
		statusIds=selStatusId;
	}else if(statusId >0){
		var statusList = statusId.split(',');
		for(var i=0;i<statusList.length-1;i++){
			statusIds.push(statusList[i]);
		}
	}
	var selSubjId = $("#subjectId").val();
	if(selSubjId != null && selSubjId.length >0){
		subjectId =selSubjId;
	}
	
	var deptIds =  $("#departmntId").val();
	var deptIdsList = [];
		if(deptIds != null && deptIds.length >0){
			deptIdsList=deptIds;
		}
	if(deptIdsList != null && deptIdsList.length>0){
		for(var i in deptIdsList){
			if(parseInt(deptIdsList[i])==0){
				deptIdsList=[];
			}
		}
	}

 var json = {
		 reportType :searchType,
		 fromDate :currentFromDate,
		 toDate : currentToDate,
		 statusIds:statusIds,
		 assetType:subjectId,
		 deptIdsList :deptIdsList
		}           
	$.ajax({              
		type:'POST',    
		url: 'getSubjectsBySearchType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		 //$("#"+selBoxId).empty();
		if(result !=null && result.length >0){
			//$("#"+selBoxId).html("<option value='0' selected>All Subjects</option>");
			for(var i in result){
				//if(subjId != null && subjId==result[i].key){
					//$("#"+selBoxId).append("<option value='"+result[i].key+"' selected>"+result[i].value+"</option>");
				//}else{
					$("#"+selBoxId).append("<option value='"+result[i].key+"' >"+result[i].value+"</option>");
				//}
			}
		}
		$("#"+selBoxId).trigger('chosen:updated');
	});	
}

function getStatusList(onLoadstatusId){
	 $("#statusLocId").html('');
	var selStatusId = $("#statusLocId").val();
	var statusIds = [];
	if(selStatusId != null && selStatusId.length >0){
		statusIds=selStatusId;
	}else if(onLoadstatusId.length >0){
		var statusList = onLoadstatusId.split(',');
		for(var i=0;i<=statusList.length-1;i++){
			statusIds.push(statusList[i]);
		}
	}
	var json = {
		statusIds :statusIds
	}
  $.ajax({                
    type:'POST',    
    url: 'getStatusList',
    dataType: 'json',
	data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
   
		if(result !=null && result.length >0){
			//$("#statusLocId").html("<option value='0' selected>All Status</option>");
			for(var i in result){
				$("#statusLocId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
			}
		}
		$("#statusLocId").trigger('chosen:updated');
  }); 
}
$(document).on("change","#statusLocId",function(){
	getSubjectsBySearchType("subject","subjectId",0,0);
	getLocationWiseRepresentationsOverviewDetails("district","districtWiseLocationDetailsDivId");
	getLocationWiseRepresentationsOverviewDetails("constituency","constituencyWiseLocationDetailsDivId");
});
$(document).on("change","#subjectId",function(){
	getLocationWiseRepresentationsOverviewDetails("district","districtWiseLocationDetailsDivId");
	getLocationWiseRepresentationsOverviewDetails("constituency","constituencyWiseLocationDetailsDivId");
});
$(document).on('click','.prntCls',function(){
	
	var locationId =$(this).attr('attr_id');
	var districtName=$(this).attr('attr_distict_name');
	var constituencyName =$(this).attr('attr_const_name');
	
	var locationIDsArr =[];
	locationIDsArr.push(locationId);
	 
	var selStatusId = $("#statusLocId").val();
	var statusIds = [];
	if(selStatusId != null && selStatusId.length >0){
		statusIds=selStatusId;
	}
	
	if(statusIds != null && statusIds.length>0){
		for(var i in statusIds){
			if(parseInt(statusIds[i])==0){
				statusIds=[];
			}
		}
	}
	
	var subjArr = [];
	var subjIds =$("#subjectId").val();
	if(subjIds != null && subjIds !=0){
		subjArr=subjIds;
	}
	
	if(subjArr != null && subjArr.length>0){
		for(var i in subjArr){
			if(parseInt(subjArr[i])==0){
				subjArr=[];
			}
		}
	}
	
	var deptIds =  $("#departmntId").val();
	var deptIdsList = [];
		if(deptIds != null && deptIds.length >0){
			deptIdsList=deptIds;
		}
	if(deptIdsList != null && deptIdsList.length>0){
		for(var i in deptIdsList){
			if(parseInt(deptIdsList[i])==0){
				deptIdsList=[];
			}
		}
	}

	var fromDateArr = currentFromDate.split('-');
	var toDateArr = currentToDate.split('-');
	
	var finalFromDateStr="";
	var finalToDateStr="";
	if(fromDateArr != null && fromDateArr.length == 3 && toDateArr != null && toDateArr.length == 3){
		finalFromDateStr=fromDateArr[2]+'-'+fromDateArr[1]+'-'+fromDateArr[0];
		finalToDateStr=toDateArr[2]+'-'+toDateArr[1]+'-'+toDateArr[0];
	}
	$('#loadingId'+locationId+'').html(spinner);
	var json = {
			  constituencyIdsList: locationIDsArr, 
			  deptIdsList: deptIdsList,
			  statusIdsList: statusIds,
			  subjectIdsList: subjArr,
			  subSubjectIdsList: [],
			  fromDate:finalFromDateStr,
			  endDate:finalToDateStr
		};
	
	$.ajax({              
		type:'POST',    
		url: 'getPetitionDetailsForPDFDocument',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$('#loadingId'+locationId+'').html('');
		if(result != null){
			buildPetitionDetailsForPDF(result,districtName,constituencyName);
		}
	});	
});


function buildPetitionDetailsForPDF(result,districtName,assemblyName){

	var str='';
		str+='<div class="row">';
			str+='<button class="btn btn-md btn-success printViewCls pull-right dispalyNone" attr_divId="printableArea">Print</button>';
		str+='</div>';
		
		str+='<div class="petition_print_heading m_top10">';
			str+='<div class="row">';
				str+='<h4 class="font_weight text-center">'+assemblyName.toUpperCase()+' CONSTITUENCY, '+districtName.toUpperCase()+' DISTRICT </h4>';
			str+='</div>';
		str+='</div>';
		str+='<div class="row m_top10">'
			str+='<div class="col-sm-6">';
				str+='<div class="pad_border line_heightCss">';
					str+='<h5 class="font_weight font_size_12">NO OF PETITIONS <span class="pull-right">'+result[0].totalCount+'</span></h5>';
					str+='<h5 class="font_weight font_size_12">NO OF WORKS WITH COST <span class="pull-right">'+result[0].noOfWorksWithCost+'</span></h5>';
					str+='<h5 class="font_weight font_size_12">TOTAL ESTIMATED COST (IN LAKHS) <span class="pull-right">'+result[0].estimationCost+'</span></h5>';
					str+='<h5 class="font_weight font_size_12">SANCTIONED WORKS <span class="pull-right">'+result[0].sanctionedWorksCount+'</span></h5>';
					str+='<h5 class="font_weight font_size_12">TO BE SANCTIONED WORKS <span class="pull-right">'+result[0].toBeSanctionedWorksCount+'</span></h5>';
					str+='<h5 class="font_weight font_size_12">NO. OF WORKS MEMOS ISSUED <span class="pull-right">'+result[0].noOfMemoIssuedCount+'</span></h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-6">';
				str+='<div class="pad_border line_heightCss">';
					str+='<h5 class="font_weight font_size_12">NO OF WORKS <span class="pull-right">'+result[0].totalWorksCount+'</span></h5>';
					str+='<h5 class="font_weight font_size_12">NO OF WORKS WITHOUT COST <span class="pull-right">'+result[0].noOfWorksWithoutCost+'</span></h5>';
					str+='<h5 class="font_weight font_size_12">SANCTIONED COST (IN LAKHS) <span class="pull-right">'+result[0].sanctionedCost+'</span></h5>';
					str+='<h5 class="font_weight font_size_12">TO BE SANCTIONED COST (IN LAKHS) <span class="pull-right">'+result[0].toBeSanctionedCost+'</span></h5>';
					str+='<h5 class="font_weight font_size_12">NO. OF WORKS G.O. ISSUED <span class="pull-right">'+result[0].noOfGOIssuedCount+'</span></h5>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top10">'
		str+='<div class="col-sm-12">'
			str+='<div class="table-responsive">';
			str+='<table class="table details-overview_petition_print">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>GIVEN&nbsp;DATE</th>';
						str+='<th>ENDORS&nbsp;NO</th>';
						str+='<th>WORK&nbsp;REF&nbsp;NO </th>';
						str+='<th>LOCATION</th>';
						str+='<th>WORK&nbsp;DESC</th>';
						str+='<th>EST&nbsp;COST<br>(in Lakhs)</th>';
						str+='<th>REF BY</th>';
						str+='<th>ACTION MEMO</th>';
						str+='<th>GO ISSUED </th>';
						str+='<th>PENDING&nbsp;@</th>';
						str+='<th>INDICATE PRIORITY<br/><span class="f-12">(Ex:1,2,3...)</span></th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
							
							if(result[i].subWorksList != null && result[i].subWorksList.length>0){
								for(var k in result[i].subWorksList){
									str+='<tr>';
									str+='<td>'+result[i].subWorksList[k].representationDate+'</td>';
									str+='<td>'+result[i].subWorksList[k].endorsmentNo+'</td>';
									str+='<td>'+result[i].subWorksList[k].workId+'</td>';
									str+='<td>';
									if(result[i].subWorksList[k].addressVO != null){
										if(typeof result[i].subWorksList[k].addressVO.districtName != 'undefined' && result[i].subWorksList[k].addressVO.districtName .length>0)
											str+='D:'+result[i].subWorksList[k].addressVO.districtName+'<br/>';
										if(typeof result[i].subWorksList[k].addressVO.assemblyName != 'undefined'  && result[i].subWorksList[k].addressVO.assemblyName .length>0)
											str+='A:'+result[i].subWorksList[k].addressVO.assemblyName+'<br/>';
										if(typeof result[i].subWorksList[k].addressVO.tehsilName != 'undefined'  && result[i].subWorksList[k].addressVO.tehsilName .length>0)
											str+='M:'+result[i].subWorksList[k].addressVO.tehsilName+'<br/>';
										if(typeof result[i].subWorksList[k].addressVO.panchayatName != 'undefined'  && result[i].subWorksList[k].addressVO.panchayatName .length>0)
											str+='P:'+result[i].subWorksList[k].addressVO.panchayatName+'';
									}
									str+='</td>';
									str+='<td>'+result[i].subWorksList[k].workDescription+'</td>';
									str+='<td>'+result[i].subWorksList[k].estimationCost+'</td>';
									/*str+='<td>';
									if(result[i].subWorksList[k].subList1 != null && result[i].subWorksList[k].subList1.length>0){
										for(var j in result[i].subWorksList[k].subList1){
											if(j >0)
												str+=' ,';
											str+=''+result[i].subWorksList[k].subList1[j].name+' ('+result[i].subWorksList[k].subList1[j].designation+')';
										}
									}
									str+='</td>';
									*/
									str+='<td>';
									if(result[i].subWorksList[k].subList2 != null && result[i].subWorksList[k].subList2.length>0){
										for(var j in result[i].subWorksList[k].subList2){
											if(j >0)
												str+=' ,';
											if(typeof result[i].subWorksList[k].subList2[j].designation != 'undefined' && result[i].subWorksList[k].subList2[j].designation.length>0)
												str+=''+result[i].subWorksList[k].subList2[j].name+' ('+result[i].subWorksList[k].subList2[j].designation+')';
										}
									}
									str+='</td>';
									str+='<td>'+result[i].subWorksList[k].actionMemo+'</td>';
									str+='<td>'+result[i].subWorksList[k].goRefNo+'</td>';
									str+='<td>'+result[i].subWorksList[k].pendingAt+'</td>';
									str+='<td></td>';
									str+='</tr>';
								}
							}
						}
					str+='</tbody>';
			str+='</table>';
			str+='</div>';
		str+='</div>';
		str+='</div>';
		$('#pdfWiswPetitionsView').html(str);
		
		$('.printViewCls').trigger('click');
		
	/* str+='<div>';
		str+='<span> NO OF PETITIONS : </span> '+result[0].totalCount+' <br> ';
		str+='<span> NO OF WORKS WITH COST : </span> '+result[0].noOfWorksWithCost+'  <br> ';
		str+='<span> TOTAL ESTIMATED COST (IN LAKHS) : </span> '+result[0].estimationCost+'  <br> ';
		str+='<span> SANCTIONED WORKS  : </span> '+result[0].sanctionedWorksCount+' <br> ';
		str+='<span> TO BE SANCTIONED WORKS : </span>'+result[0].toBeSanctionedWorksCount+' <br>  ';
		str+='<span> NO. OF WORKS MEMOS ISSUED  : </span>'+result[0].noOfMemoIssuedCount+' <br>  ';
		
		str+='<span> NO OF WORKS : </span>'+result[0].totalWorksCount+'  <br> ';
		str+='<span> NO OF WORKS WITHOUT COST : </span>'+result[0].noOfWorksWithoutCost+' <br>  ';
		
		str+='<span> SANCTIONED COST (IN LAKHS)  : </span> '+result[0].sanctionedCost+' <br> ';
		str+='<span> TO BE SANCTIONED COST (IN LAKHS)  : </span>'+result[0].toBeSanctionedCost	+' <br>  ';
		str+='<span> NO. OF WORKS G.O. ISSUED  : </span>'+result[0].noOfGOIssuedCount+' <br>  ';
	str+='</div>'; */
	/* str+='<div class="table-responsive">';
		str+='<table class="table table-bordered" id="petionsDtailsTab">';
		str+='<thead>';
		str+='<tr>';
			str+='<th> GIVEN DATE </th>';
			str+='<th> ENDORSMENT NO</th>';
			str+='<th> WORK REF NO </th>';
			str+='<th> LOCATION </th>';
			str+='<th> WORK DESC </th>';
			str+='<th> ESTIMATED COST </th>';
			str+='<th> REF BY </th>';
			str+='<th> ACTION MEMO </th>';
			str+='<th> GO ISSUED </th>';
			str+='<th> PENDING </th>';
			str+='</tr>';
		str+='</thead>';
		
		str+='<tbody>';
			for(var i in result){
				
				if(result[i].subWorksList != null && result[i].subWorksList.length>0){
					for(var k in result[i].subWorksList){
						str+='<tr>';
						str+='<td>'+result[i].subWorksList[k].representationDate+'</td>';
						str+='<td>'+result[i].subWorksList[k].endorsmentNo+'</td>';
						str+='<td>'+result[i].subWorksList[k].workId+'</td>';
						str+='<td>';
						if(result[i].subWorksList[k].addressVO != null){
							if(typeof result[i].subWorksList[k].addressVO.districtName != 'undefined')
								str+=' DISTRICT: '+result[i].subWorksList[k].addressVO.districtName+'';
							if(typeof result[i].subWorksList[k].addressVO.assemblyName != 'undefined')
								str+=' ASSEMBLY: '+result[i].subWorksList[k].addressVO.assemblyName+'';
							if(typeof result[i].subWorksList[k].addressVO.tehsilName != 'undefined')
								str+=' MANDAL: '+result[i].subWorksList[k].addressVO.tehsilName+'';
							if(typeof result[i].subWorksList[k].addressVO.panchayatName != 'undefined')
								str+=' PANCHAYAT: '+result[i].subWorksList[k].addressVO.panchayatName+'';
						}
						str+='</td>';
						str+='<td>'+result[i].subWorksList[k].workDescription+'</td>';
						str+='<td>'+result[i].subWorksList[k].estimationCost+'</td>';
						/*str+='<td>';
						if(result[i].subWorksList[k].subList1 != null && result[i].subWorksList[k].subList1.length>0){
							for(var j in result[i].subWorksList[k].subList1){
								if(j >0)
									str+=' ,';
								str+=''+result[i].subWorksList[k].subList1[j].name+' ('+result[i].subWorksList[k].subList1[j].designation+')';
							}
						}
						str+='</td>';///////////
						
						str+='<td>';
						if(result[i].subWorksList[k].subList2 != null && result[i].subWorksList[k].subList2.length>0){
							for(var j in result[i].subWorksList[k].subList2){
								if(j >0)
									str+=' ,';
								str+=''+result[i].subWorksList[k].subList2[j].name+' ('+result[i].subWorksList[k].subList2[j].designation+')';
							}
						}
						str+='</td>';
						str+='<td>'+result[i].subWorksList[k].actionMemo+'</td>';
						str+='<td>'+result[i].subWorksList[k].goRefNo+'</td>';
						str+='<td>'+result[i].subWorksList[k].pendingAt+'</td>';
						str+='</tr>';
					}
				}
			}
		str+='</tbody>';
	str+='</table>';
	str+='</div>';
	
	$('#pdfWiswPetitionsView').html(str);
	$("#petionsDtailsTab").dataTable({
		"paging":   true,
		"info":     false,
		"searching": true,
		"autoWidth": true,
		//"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "All"]]
	}); */
}


 $(document).on("click",".printViewCls",function(){
	printDiv();
});
function printDiv() {
	 var printContents = document.getElementById('printableArea').innerHTML;
	 var originalContents = document.getElementById("printcontent").innerHTML;
	 document.title = "";
     document.getElementById("printcontent").innerHTML = printContents;
	 window.print();
     document.getElementById("printcontent").innerHTML = originalContents;
	 $(".dispalyNone").show();
}


getDepartmntsDetails();
function getDepartmntsDetails(){
 var json = {
		 reportType :"department",
		 fromDate :"",
		 toDate : "",
		 departmentId:0,
		 statusId:""
		}           
	$.ajax({              
		type:'POST',    
		url: 'getDepartmentsBySearchType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		
		$("#departmntId").empty();
		$("#departmntId").append("<option value='0' selected> ALL </option>");
		if(result !=null && result.length >0){
			for(var i in result)
				$("#departmntId").append("<option value='"+result[i].key+"' >"+result[i].value+"</option>");
			
		}
		$("#departmntId").trigger('chosen:updated');
	});	 
}
$(document).on('change','#departmntId',function(){
	var deptId = $(this).val();
	
		if(deptId != null){
		 onLoadCalls();

		}

})