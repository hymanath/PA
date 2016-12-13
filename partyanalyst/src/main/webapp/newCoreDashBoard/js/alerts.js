	   $(document).on("click",".alertSetClose",function(){
			$(".specialAlertDropDown").hide(); 
		 });
		 
		$(document).on("click","#alertImpactSelectAllId",function(){
			 if ($(this).prop('checked')) {
				$(".alertImpactCheckCls").prop('checked', true);
			} else {
				$(".alertImpactCheckCls").prop('checked', false);
			}
		});


   	var customStartDateAlert = moment().startOf('month').format('DD/MM/YYYY')
	var customEndDateAlert = moment().format('DD/MM/YYYY');

	$("#dateRangeIdForAlert").daterangepicker({
		opens: 'left',
	     startDate: moment(),
         endDate: moment(),
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
			'Today': [moment(), moment()],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(30, 'years').startOf('year'), moment()],
        }
	})
	
	var dates= $("#dateRangeIdForAlert").val();
	//$("#alertDateHeadingId").html(" THIS MONTH ( "+customStartDate+" to "+customEndDate+" )");
	$("#alertDateHeadingId").html(" Today ( "+dates+" )");
	var singleBlockDateStart = moment().startOf('month').format('MMM YY');
	var singleBlockDateEnd = moment().format('MMM YY');
	$('#dateRangeIdForAlert').on('apply.daterangepicker', function(ev, picker) {
	  customStartDateAlert = picker.startDate.format('DD/MM/YYYY');
	  customEndDateAlert = picker.endDate.format('DD/MM/YYYY');
	  var scopeIdsArr = [];
		scopeIdsArr.push(2);  
		scopeIdsArr.push(3);  
		scopeIdsArr.push(7);  
		scopeIdsArr.push(9); 
		scopeIdsArr.push(5); 
		scopeIdsArr.push(8);  
	  $(".alertImpactCheckCls").prop('checked', true); //checked all scope level
	 getAlertOverviewDetails();
	 getAlertCategoryDtlsLocationWise();
	 getAssignGroupTypeAlertDtlsByImpactLevelWise(scopeIdsArr);
	 getTotalAlertGroupByDist(scopeIdsArr);
	 getStateImpactLevelAlertDtlsCnt();
	 var dates= $("#dateRangeIdForAlert").val();
	 $("#alertDateHeadingId").html(picker.chosenLabel+" ( "+dates+" )");
	});

	$(document).on("click",".alertsIconExpand",function(){
		$(".dateRangePickerClsForAlert").toggleClass("hide");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".alertsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".alertLocationDiv").show();
			getAlertCategoryDtlsLocationWise();
			console.log("opening")
			var scopeIdsArr = [];
			scopeIdsArr.push(2);  
			scopeIdsArr.push(3);  
			scopeIdsArr.push(7);  
			scopeIdsArr.push(9); 
			scopeIdsArr.push(5); 
			scopeIdsArr.push(8); 
			getTotalAlertGroupByDist(scopeIdsArr);
			getAssignGroupTypeAlertDtlsByImpactLevelWise(scopeIdsArr);
			   getStateImpactLevelAlertDtlsCnt();
			$(".districtAltCtnCls").toggle();
		}else{
			console.log("closing")
			$("#districtWiseAlertCountId").html("");    
			$(".districtAltCtnCls").toggle();     
            $(".alertLocationDiv").hide();  			
		}
		if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".dateRangePickerClsForTraining").addClass("hide");
			$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
			$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
			$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
			$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
			$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
			$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerCls").toggleClass("hide");
			$(".moreBlocksIcon").removeClass("unExpandBlock");
		}else if( $(".meetingsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".meetingsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").hide();
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForMeetings").toggleClass("hide");
			$(".moreMeetingsBlocks1").hide();
			$(".moreMeetingsBlocksDetailed").hide();
			$(".moreMeetingsBlocksComparision").hide();
		}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents").hide();
			$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForEvents").toggleClass("hide");
		}else if( $(".newsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".newsHiddenBlock,.morenewsBlocksIcon,.newsHiddenMoreBlock").hide();
			$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForNews").toggleClass("hide");
		}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
			$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}
		});
  function getAlertOverviewDetails(){
		$("#alertOverview").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var dates=$("#dateRangeIdForAlert").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			fromDate:fromDateStr,        
			toDate :toDateStr    
		};
		$.ajax({
			type : 'GET',
			url : 'getAlertOverviewDetailsAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			if(result != null){
			  buildAlertOverviewDetails(result);
			}else{
			  $("#alertOverview").html("NO DATA AVAILABLE.");	
			}
      });	
	}
	
	function buildAlertOverviewDetails(result)
	{
		var str='';
		if(result.overAllVO != null){
		str+='<div class="pad_5 bg_ED">';
			str+='<table class="table table-bordered alertOverviewTable bg_ED">';
				str+='<tr>';
					str+='<td>';
						if(result.overAllVO.totalAlertCnt == 0){  
							str+='<h3>'+result.overAllVO.totalAlertCnt+'</h3>';
						}else{
							str+='<h3 class="alertDtlsCls" style="cursor:pointer;" attr_category_id="0" attr_status_id="0" attr_alert_type_id="0" attr_count="'+result.overAllVO.totalAlertCnt+'">'+result.overAllVO.totalAlertCnt+'</h3>';
						}
						str+='<p class="text-capital">TOTAL ALERTS</p>';
					str+='</td>';
					str+='<td>';
						if(result.overAllVO.partyAlertCnt == 0){
							str+='<h3>'+result.overAllVO.partyAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.partyAlertCntPer+'%</small> </h3>';
						}else{
							str+='<h3 class="alertDtlsCls" style="cursor:pointer;" attr_category_id="0" attr_status_id="0" attr_alert_type_id="1" attr_count="'+result.overAllVO.partyAlertCnt+'">'+result.overAllVO.partyAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.partyAlertCntPer+'%</small> </h3>';
						}
						str+='<p class="text-capital">party</p>';
					str+='</td>';
					str+='<td>';
						if(result.overAllVO.otherAlertCnt == 0){
							str+='<h3>'+result.overAllVO.otherAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.otherAlertCntPer+'%</small></h3>';
						}else{
							str+='<h3 class="alertDtlsCls" style="cursor:pointer;" attr_category_id="0" attr_status_id="0" attr_alert_type_id="3" attr_count="'+result.overAllVO.otherAlertCnt+'">'+result.overAllVO.otherAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.otherAlertCntPer+'%</small></h3>';
						}
						str+='<p class="text-capital">others</p>';
					str+='</td>';
				str+='</tr>';
			str+='</table>';
		str+='</div>';
	}
	if(result.statusList != null && result.statusList.length > 0){
		  str+='<div class="row">';
		  //str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
		  str+='<h4 class="panel-title m_top10 text-capital" style="margin-left:20px !important">Status</h4>';
			for(var i in result.statusList)
			{
				str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top10">';
				  str+='<div class="bg_ED pad_5">';
						if(result.statusList[i].statusCnt == 0){
							str+='<h3>'+result.statusList[i].statusCnt+'&nbsp;&nbsp;<small class="text-success" style="font-size:13px">'+result.statusList[i].statusCntPer+'%</small></h3>';
						}else{
							str+='<h3 class="alertDtlsCls" style="cursor:pointer;" attr_category_id="0" attr_status_id="'+result.statusList[i].statusTypeId+'" attr_alert_type_id="0" attr_count="'+result.statusList[i].statusCnt+'">'+result.statusList[i].statusCnt+'&nbsp;&nbsp;<small class="text-success" style="font-size:13px">'+result.statusList[i].statusCntPer+'%</small></h3>';
						}
						str+='<p class="text-capital text-muted">'+result.statusList[i].statusType+'</p>';
					str+='</div>';  
				str+='</div>';
			}    
			//str+='</div>';  
			str+='</div>';  
		}
		if(result.categoryList != null && result.categoryList.length > 0){
		str+='<div class="row">';	
		str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
		str+='<div class="table-responsive">';
		for(var i in result.categoryList)  
		{
			if(result.categoryList[i].statusCnt == 0){
				str+='<h4 class="panel-title m_top10 text-capital">'+result.categoryList[i].statusType+' - '+result.categoryList[i].statusCnt+'%</h4>';
			}else{
				str+='<h4 class="panel-title m_top10 alertDtlsCls text-capital" style="cursor:pointer;" attr_category_id="'+result.categoryList[i].statusTypeId+'" attr_status_id="0" attr_alert_type_id="0" attr_count="'+result.categoryList[i].statusCnt+'">'+result.categoryList[i].statusType+' - '+result.categoryList[i].statusCnt+'</h4>';
			}
					
			str+='<table class="table table-condensed bg_ED">';
				str+='<tbody>';
					str+='<tr>';
					for(var j in result.categoryList[i].statusList)
					{
						if(result.categoryList[i].statusList[j].statusCnt == 0){
							str+='<td><p class="text-muted text-capital responsiveFont">'+result.categoryList[i].statusList[j].statusType+'</p><p class="responsiveFont">'+result.categoryList[i].statusList[j].statusCnt+'&nbsp;&nbsp;<small class="text-success">'+result.categoryList[i].statusList[j].statusCntPer+'%</small></p></td>';
						}else{
							str+='<td><p class="text-muted text-capital responsiveFont">'+result.categoryList[i].statusList[j].statusType+'</p><p class="responsiveFont alertDtlsCls" style="cursor:pointer;" attr_category_id="'+result.categoryList[i].statusTypeId+'" attr_status_id="'+result.categoryList[i].statusList[j].statusTypeId+'" attr_alert_type_id="0" attr_count="'+result.categoryList[i].statusList[j].statusCnt+'">'+result.categoryList[i].statusList[j].statusCnt+'&nbsp;&nbsp;<small class="text-success">'+result.categoryList[i].statusList[j].statusCntPer+'%</small></p></td>';
						}             
					}
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
			}
			str+='</div>';
			str+='</div>';
			str+='</div>';
		}
		$("#alertOverview").html(str)
			
	}
	$(document).on("click",".alertDtlsBtnCls",function(){
		$(".specialAlertDropDown").toggle(); 
		var scopeIdsArr = [];
		 $(".alertSettingsUl li").each(function() {
		  if($(this).find("input").is(":checked")){
			 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
		     if(selectionType == "District"){
				scopeIdsArr.push(2);
			}else if(selectionType == "Constituency"){
				scopeIdsArr.push(3);
			}else if(selectionType == "mandalMuncipality"){
				scopeIdsArr.push(5);  
				scopeIdsArr.push(8);
			}else if(selectionType == "VillageWard"){
				scopeIdsArr.push(7);  
				scopeIdsArr.push(9);	
			}
		  }
	   });
	  var locVal ='';
		$( "li.optionsCls" ).each(function() {
			if($( this ).hasClass( "active" )){
				locVal = $(this).attr("attr_id");
			}
		});
		if(locVal == "1"){  
			getTotalAlertGroupByDist(scopeIdsArr);      
		}else if(locVal == "2"){
			getTotalAlertGroupByLocationThenCategory(scopeIdsArr);
		}else{
			getTotalAlertGroupByLocationThenStatus(scopeIdsArr);
		}
		getAssignGroupTypeAlertDtlsByImpactLevelWise(scopeIdsArr);
	});     
	$(document).on("click",".alertSettingCloseCls",function(){      
		$(".specialAlertDropDown").toggle();                    
	});
	
	
	$(document).on("click",".optionsCls",function(){
		var scopeIdsArr = [];
		var option = $(this).attr("attr_id");
		//var selectionType=$("input:radio[name=locationLevel]:checked").attr("attr_val");
			$(".alertSettingsUl li").each(function() {
			  if($(this).find("input").is(":checked")){
				 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
				 if(selectionType == "District"){
					scopeIdsArr.push(2);
				}else if(selectionType == "Constituency"){
					scopeIdsArr.push(3);
				}else if(selectionType == "mandalMuncipality"){
					scopeIdsArr.push(5);  
					scopeIdsArr.push(8);
				}else if(selectionType == "VillageWard"){
					scopeIdsArr.push(7);  
					scopeIdsArr.push(9);	
				}
			  }
		   });
		if(option == "1"){
			getTotalAlertGroupByDist(scopeIdsArr);
		}else if(option == "2"){
			getTotalAlertGroupByLocationThenCategory(scopeIdsArr);
		}else{
			getTotalAlertGroupByLocationThenStatus(scopeIdsArr);
		}
	});
	function getTotalAlertGroupByDist(scopeIdsArr){ 
		$("#districtWiseAlertCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var dates=$("#dateRangeIdForAlert").val();
	    var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : fromDateStr,        
			toDate : toDateStr,             
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId                                 
		}                  
		$.ajax({
			type : 'POST',      
			url : 'getTotalAlertGroupByDistAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){       
			$("#districtWiseAlertCountId").html(' ');
			if(result != null && result.length > 0){
				buildTotalAlertGroupByDist(result);       
			}  
		});  
	}
	function buildTotalAlertGroupByDist(result){  
		var str='';
		var distWiseArticlesRelated = [];
		str+='<div id="districtAlertCountId" style="height:300px;"></div>';            
		for(var i in result){
			var obj1 = {
				name: result[i].locaitonName,
				y: result[i].count,
				extra:result[i].count       
			};
			distWiseArticlesRelated.push(obj1);
		}
		$("#districtWiseAlertCountId").html(str);
		$(function () {
			 $("#districtAlertCountId").highcharts({  
				colors: ['#53BF8B'],    
				chart: {
					type: 'column'
				},
				title: {
					text: ''
				},
				subtitle: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					
					type: 'category',
					labels: {
								formatter: function() {
									return this.value.toString().substring(0, 10)+'...';
								},
							}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: ''
					},
					labels: {
						enabled:false
					}
				},
				legend: {
					enabled: false
				},	
				plotOptions: {
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,0);      
								}
							}
						}
					}
				},
				tooltip: {
					headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.extra}</b>'
				},
				series: [{    
					name: 'Number Of Alerts',    
					data: distWiseArticlesRelated
				}],
			 
			});  
		});
	}
	$(document).on("click",".alertDtlsCls",function(){
		var alertStatusId = $(this).attr("attr_status_id");
		var alertCategoryId = $(this).attr("attr_category_id");
		var alertTypeId = $(this).attr("attr_alert_type_id");
		var alertCount = $(this).attr("attr_count");  
		getAlertDtls(alertStatusId, alertCategoryId, alertTypeId,alertCount);
		
	});
	
	function getAlertDtls(alertStatusId, alertCategoryId, alertTypeId,alertCount){
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");  
		$("#alertCntTitId").html("TOTAL ALERTS-"+alertCount);
		var dates=$("#dateRangeIdForAlert").val();
		 var fromDateStr;
		 var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj = { 
			alertTypeId : alertTypeId,    
			alertStatusId : alertStatusId,
			alertCategoryId : alertCategoryId,  
			stateId : globalStateId,             
			fromDate : fromDateStr,                
			toDate : toDateStr,                
			activityMemberId : globalActivityMemberId                                 
		}                          
		$.ajax({
			type : 'POST',      
			url : 'getAlertDtlsAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){       
			if(result != null && result.length > 0){
				buildAlertDtls(result);   
			}
		});  
	}
	function buildAlertDtls(result){
		$("#cadreExcelExpBtnId").show();
		var str='';
		str+='<div class="table-responsive">';
	 	str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed " id="alertDtlsTabId">';   
		str+='<thead>';
             str+='<th>Title</th>';
             str+='<th>Created Date</th>';
			 str+='<th>Last Updated Date</th>';
			 str+='<th>Current Status</th>'	 
			 str+='<th>LAG Days</th>';
			 str+='<th>Alert Level</th>';
			 str+='<th>Location</th>';
		 str+='</thead>';
		 str+='<tbody>';
		 for(var i in result){
			str+='<tr>';
			if(result[i].title != null && result[i].title.length > 0){
				str+='<td class="descAlertCls" style="cursor:pointer;" attr_alert_id="'+result[i].id+'"><strong><u>'+result[i].title+'</u></strong></td>';         
			}else{
				str+='<td> - </td>';     
			}
			if(result[i].createdDate != null && result[i].createdDate.length > 0){
				str+='<td>'+result[i].createdDate+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].updatedDate != null && result[i].updatedDate.length > 0){
				str+='<td>'+result[i].updatedDate+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].status != null && result[i].status.length > 0){
				str+='<td>'+result[i].status+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].interval != null){
				str+='<td>'+result[i].interval+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].alertLevel != null && result[i].alertLevel.length > 0){
				str+='<td>'+result[i].alertLevel+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].location != null && result[i].location.length > 0){
				str+='<td>'+result[i].location+'</td>';      
			}else{
				str+='<td> - </td>';        
			}
			//str+='<td><button type="button" class="btn btn-default btn-success descAlertCls" attr_alert_id="'+result[i].id+'">Alert Details</button></td>';  
			
			str+='</tr>';
			}
			 str+='</tbody>';
			 str+='</table>';
			 str+='</div>';
		 $("#tourDocumentBodyId").html(str);          
		  $("#alertDtlsTabId").dataTable({  
				 "aaSorting": [[ 4, "desc" ]], 
				"iDisplayLength" : 10,
				"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
			 }); 
	}
	var globalStr1 = '';  
	$(document).on("click",".descAlertCls",function(){
		$("#cdrModelDivId").find(".close").addClass("modalClose");
		$("#cdrModelDivId").find(".modal-footer .btn").addClass("modalClose");
		$("#tourDocHeadingId").html("");
		$("#cdrModelId").html("");
		$("#alertDestId").html("");
		$("#alertAttachTitId").html("");
		$("#alertAttachImgId").html("");
		$("#alertInvolvedCandidates").html("");
		$("#alertAssignedCandidates").html("");
		$("#alertCommentsDiv").html("");
		$("#tourDocHeadingId").html("ALERT TITLE <br>");
		$("#cdrModelDivId").modal("show");
		var alertId = $(this).attr("attr_alert_id");           
		getAlertData(alertId);
		getAlertAssignedCandidates(alertId);    
		getAlertStatusCommentsTrackingDetails(alertId);
	});
	$(document).on("click",".modalClose",function(){  
		$(this).removeClass("modalClose");
		$("body").addClass("modal-open");
	});
	
	function getAlertAssignedCandidates(alertId){
		GlobalAlertData = [];
		var jsObj ={
			alertId  : alertId,    
			task : ""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertAssignedCandidatesAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAlertAssignedCandidates(result);  
			}
		});
	}
	function getAlertData(alertId){  
		var jsObj ={
			alertId  :alertId,
			task : ""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertsDataAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAlertData(result);
			}    
		});
	}
	function buildAlertData(result){
		$("#tourDocHeadingId").html("<h5 style='color:#FFFFFF;font-size:14px;'>ALERT TITLE</h5><br><h5 class='text-capital'>"+result[0].title+"</h5>");
		$("#cdrModelId").html("<h5 class='text-muted'>ALERT DESCRIPTION</h5>");
		$("#alertDestId").html(result[0].desc);
		$("#alertAttachTitId").html("<h5  class='text-muted'>ALERT ATTACHMENTS</h5>");
		var imgStr = '';
		
		imgStr+='<ul class="list-inline imageUrlUlCls">';
		imgStr+='<li class="articleDetailsCls" attr_articleid="414441" style="cursor:pointer">';
		imgStr+='<img src="http://mytdp.com/NewsReaderImages/'+result[0].imageUrl+'" style="width: 150px; height: 150px;">';
		imgStr+='</li>';
		imgStr+='</ul> '; 
		$("#alertAttachImgId").html(imgStr);

		var str='';
		if(result[0].subList.length > 0){
			str+='<h5 class="text-muted text-capital">Involved Candidates-'+result[0].subList.length+'</h5>';
			str+='<ul class="list-inline assignedCandidatesUl1">';     
			for(var i in result)
			{
				for(var j in result[i].subList)
				{
					str+='<li>';      
						str+='<p><b>'+result[i].subList[j].name+'</b></p>';
						if(result[i].subList[j].committeePosition == null){
							str+='<p><i> - </i></p>';           
						}else{
							str+='<p><i> - </i>'+result[i].subList[j].committeePosition+'</p>';
						}
					str+='</li>';  
				}    
			}
			str+='</ul>';  
			
			$("#alertInvolvedCandidates").html(str);    
		}else{
			$("#alertInvolvedCandidates").html('');        
		}
		$(".assignedCandidatesUl1").slick({          
			 slide: 'li',
			 slidesToShow: 5,
			 slidesToScroll: 3,    
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 5,
					slidesToScroll: 3,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 2
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
				
			  ]  
		}); 
	}
	function getAlertStatusCommentsTrackingDetails(alertId){  
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
			if(result != null && result.length > 0)        
				buildAlertStatusCommentsTrackingDetails(result);    
		});
	}
	function getAlertCategoryDtlsLocationWise(){  
		$("#locationWiseAlertDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var dates=$("#dateRangeIdForAlert").val();
		 var fromDateStr;
		 var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			fromDate :fromDateStr,        
			toDate :toDateStr   
		};
		$.ajax({
			type : 'POST',
			url : 'getAlertCategoryDtlsLocationWiseAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
           if(result != null && result.length > 0){
			   buildAlertCategoriesDlsHIghchart(result);
		   }else{
			   $("#locationWiseAlertDivId").html("NO DATA AVAILABLE.");
		   }
		});	
	}
	
	function buildAlertCategoriesDlsHIghchart(result){
		var str='';
		if(result != null && result.length > 0){
		  var str='';
		  for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			  str+='<h5 class="text-capital m_top10 alertCategoryCls">'+result[i].name+'</h5>';      
			  str+='<div id="alertCategory'+i+'" attr_category_name='+result[i].name+' attr_id="alertCategory'+i+'" class="dddddd" style="height:130px;"></div>';
			str+='</div>'
		  }
		}
		$("#locationWiseAlertDivId").html(str);
	   if(result != null && result.length > 0){
			for(var i in result){
				var locationNameArr = [];
				var alertCntArr = [];
			  if(result[i].subList !=null && result[i].subList.length>0){
					for(var j in result[i].subList){
						locationNameArr.push(result[i].subList[j].locationType);
						//alertCntArr.push(result[i].subList[j].alertCount);
						alertCntArr.push({"y":result[i].subList[j].alertCount,"extra":result[i].name});
					}
				}
		//if(result[i][0].totalTour!=0){
			var getWidth = $("#alertCategory"+i).parent().width()+'px';
				$("#alertCategory"+i).width(getWidth);
		    $(function () {
				$('#alertCategory'+i).highcharts({
					colors: ['#0066DC'],
					chart: {
						type: 'column'
					},
					title: {
						text: null
					},
					subtitle: {
						text: null
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: locationNameArr,
						title: {
							text: null
						},
						labels: {
								formatter: function() {
									return this.value.toString().substring(0, 10)+'...';
								},
								
							}
					},
					yAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						title: {
							text: null,
							align: 'high'
						},
						labels: {
							overflow: 'justify',
							enabled: true,
						}
					},
					tooltip: {
					 useHTML: true,
					 backgroundColor: '#FCFFC5',
					 formatter: function() {
							 var categoryName = this.point.extra;
							 var obj1 = result.filter(function ( obj1 ) {
									return obj1.name.toUpperCase().trim() === categoryName.toUpperCase().trim();
								})[0];
							 var locationList = obj1.subList;
							 var _locationName = this.x;
							 var obj = locationList.filter(function ( obj ) {
									return obj.locationType.toUpperCase().trim() === _locationName.toUpperCase().trim();
								})[0];
								return "Total Alerts - "+obj.alertCount+"<br/>Pending - " + obj.pendingCnt + "<br/>Notified - " + obj.notifiedCnt + " <br/>Action In Progess - " + obj.actionInProgessCnt+"<br/>Completed - " + obj.completedCnt + "<br/>Unable to Resolve - " + obj.unabletoResolveCnt + "<br/>Action Not Required - " + obj.actionNotRequiredCnt+"";     
						}
					},
					plotOptions: {
						column: {  
							stacking: 'normal',
							dataLabels: {
								enabled: true,
								 formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return Highcharts.numberFormat(this.y, 0) +""; 
									}
								}
							  
							}
						}
					},
					legend: {
						enabled: false,
						layout: 'vertical',
						align: 'right',
						verticalAlign: 'top',
						x: -40,
						y: 80,
						floating: true,
						borderWidth: 1,
						backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
						shadow: true
					},
					credits: {
						enabled: false
					},
					
					series: [{
						name: 'Number Of Alert',
						data: alertCntArr
					}]
				});
			});
		//}else{
		//$("#alertCategory"+i).html("No Data Available");
		//$("#alertCategory"+i).css("height","35px");
		//$("#alertCategory"+i).hide();
		//} 
			}
		}else{
			$("#locationWiseAlertDivId").html('NO DATA AVAILABLE.');
		}
	}
	
	function getTotalAlertGroupByLocationThenCategory(scopeIdsArr){
		$("#districtWiseAlertCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var dates=$("#dateRangeIdForAlert").val();
		 var fromDateStr;
		 var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : fromDateStr,      
			toDate : toDateStr,  
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,       
			group : ""
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getTotalAlertGroupByLocationThenCategoryAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
				$("#districtWiseAlertCountId").html(' ');
			if(result != null && result.length > 0){
				buildTotalAlertGroupByLocationThenCategory(result);
			}
		});  
	}
	function buildTotalAlertGroupByLocationThenCategory(result){  
		$("#districtWiseAlertCountId").html("");
		var str ='';
		if(result !=null && result.length >0){
			str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-ms-12">';
			str+='<ul class="distWiseSlickApply">';
			for(var i in result){
				str+='<li style="border-right:1px solid #ccc"><div id="distwisegraph'+i+'"  style="height:200px;width:220px"></div></li>';
				
			}
			str+='</ul>'; 			
			str+='</div>'; 
			str+='</div>';			
		}
		
		
		$("#districtWiseAlertCountId").html(str);
			if(result !=null && result.length >0){
				for(var i in result){
					var districtName;
					districtName = (result[i].status).toUpperCase();
					districtName+='-'+result[i].count+'';     
					if(result[i].subList1 !=null && result[i].subList1.length >0){
						var categoryName =[];
						var count =[];
						for(var j in result[i].subList1){
							categoryName.push(result[i].subList1[j].category);   
							count.push(result[i].subList1[j].categoryCount);
						}
							if(categoryName.length !=0 && count.length !=0  && districtName != 0 && districtName != null){
								$(function () {
									$('#distwisegraph'+i+'').highcharts({
										colors: ['#808000','#00FFFF','#FF00FF'],     
										chart: {
											type: 'column'
										},
										title: {
											text: districtName
										},
									   
										xAxis: {
											 min: 0,
												 gridLineWidth: 0,
												 minorGridLineWidth: 0,
												 categories: categoryName,
											labels: {
													rotation: -45,
													style: {
														fontSize: '13px',
														fontFamily: 'Verdana, sans-serif'
													},
												}
										},
										yAxis: {
											min: 0,
												   gridLineWidth: 0,
													minorGridLineWidth: 0,
											title: {
												text: ''
											}
										},
										tooltip: {
											formatter: function () {
												var s = '<b>' + this.x + '</b>';

												$.each(this.points, function () {
													s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :'+(this.y);
												});

												return s;
											},
											shared: true
										},
										legend: {
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: {        
												dataLabels:{
													enabled: false,
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.percentage,1) + '%';
														}
													}
												},
												
											},
										},
										series: [{
											name: 'Number of alert',
											data: count,
											colorByPoint: true
										}]
									});
								});
							}else{
								$('#distwisegraph'+i+'').html("No Data Available");
								$('#distwisegraph'+i+'').css("height","20px");
							}
					}
				}
			}	  
			else{
				$("#districtWiseAlertCountId").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
			
		$(".distWiseSlickApply").slick({      
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false,
			 responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3
				  }
				},
				{
				  breakpoint: 768,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
			  ]
		}); 
	}
	//getTotalAlertGroupByLocationThenStatusAction  
	function getTotalAlertGroupByLocationThenStatus(scopeIdsArr){
		$("#districtWiseAlertCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var dates=$("#dateRangeIdForAlert").val();
		 var fromDateStr;
		 var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : fromDateStr,      
			toDate : toDateStr,  
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,       
			group : ""
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getTotalAlertGroupByLocationThenStatusAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			$("#districtWiseAlertCountId").html(' ');
			if(result != null && result.length > 0){  
				buildTotalAlertGroupByLocationThenStatus(result);
			}
		});  
	}
	function buildTotalAlertGroupByLocationThenStatus(result){  
	 	$("#districtWiseAlertCountId").html("");
		var str ='';
		if(result !=null && result.length >0){
			str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-ms-12">';
			str+='<ul class="distWiseSlickApply">';
			for(var i in result){
				str+='<li style="border-right:1px solid #ccc"><div id="distwisegraph'+i+'"  style="height:300px;width:250px"></div></li>';        
			}
			str+='</ul>';			  
			str+='</div>'; 
			str+='</div>';			
		}
		$("#districtWiseAlertCountId").html(str);
			if(result !=null && result.length >0){
				for(var i in result){
					var districtName;
					districtName = (result[i].status).toUpperCase()+"-"+result[i].count+"";
					if(result[i].subList1 !=null && result[i].subList1.length >0){
						var categoryName =[];
						var countAlert = [];
						var count = [];
						for(var j in result[i].subList1){
							var uniqCnt = {};
							categoryName.push(result[i].subList1[j].category);
							countAlert.push(result[i].subList1[j].categoryCount);
							var uniqCnt = {y:parseInt(result[i].count)-parseInt(result[i].subList1[j].categoryCount),color:"#D3D3D3"};
							count.push(uniqCnt);
						}
							if(categoryName.length !=0 && count.length !=0  && districtName != 0 && districtName != null){
								$(function () {
									$('#distwisegraph'+i+'').highcharts({
										colors: ['#A185BF','#0166FF','#32CCFE','#019966','#FF6600','#CC0001'],     
										chart: {
											type: 'column'
										},
										title: {
											text: districtName
										},
									   
										xAxis: {
											 min: 0,
												 gridLineWidth: 0,
												 minorGridLineWidth: 0,
												 categories: categoryName,
											labels: {
													rotation: -45,
													style: {
														fontSize: '13px',
														fontFamily: 'Verdana, sans-serif'
													},
												}
										},
										yAxis: {
											min: 0,
												   gridLineWidth: 0,
													minorGridLineWidth: 0,
											title: {
												text: ''
											}
										},
										tooltip: {
											formatter: function () {
												var s = '<b>' + this.x + '</b>';

												$.each(this.points, function () {
													if(this.series.name != "Series 1")  
													s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
														Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
														(this.y);
												});

												return s;
											},
											shared: true
										},
										legend: {   
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: {
												stacking: 'percent',  
												dataLabels:{
													enabled: false,
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.percentage,1) + '%';
														}
													}
												},
												
											},
										},
										series: [{
											data: count  
										}, {
											name: "Number of alerts",
											data: countAlert,
											colorByPoint: true
										}]
									});
								});
							}else{
								$('#distwisegraph'+i+'').html("No Data Available");
								$('#distwisegraph'+i+'').css("height","20px");
							}
					}
				}
			}	  
			else{
				$("#districtWiseAlertCountId").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
			
		$(".distWiseSlickApply").slick({    
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false,
			 responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3
				  }
				},
				{
				  breakpoint: 768,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
			  ]
		}); 
	}
	
	function buildAlertAssignedCandidates(result)
	{
	var str='';
	if(result[0].subList.length > 0){  
		str+='<h5 class="text-muted text-capital">Assigned Candidates-'+result[0].subList.length+'</h5>';
		str+='<ul class="list-inline assignedCandidatesUl">';
		for(var i in result)
		{
			for(var j in result[i].subList)
			{
				str+='<li>';
					str+='<p><b>'+result[i].subList[j].name+'</b></p>';
					if(result[i].subList[j].committeePosition == null){
						str+='<p><i> - </i></p>';       
					}else{
						str+='<p><i> - '+result[i].subList[j].committeePosition+'</i></p>';
					}
					
					str+='<p>'+result[i].subList[j].mobileNo+'</p>';
					str+='<p>'+result[i].subList[j].locationVO.districtName+'</p>';
				str+='</li>';
			}
		}
		str+='</ul>';
		
		$("#alertAssignedCandidates").html(str);
	}else{
		$("#alertAssignedCandidates").html('');      
	}
	
	$(".assignedCandidatesUl").slick({
		 slide: 'li',
		 slidesToShow: 5,
		 slidesToScroll: 3,
		 infinite: false,
		  responsive: [
			{
			  breakpoint: 1024,
			  settings: {
				slidesToShow: 5,
				slidesToScroll: 3,
				infinite: false,
				dots: false
			  }
			},
			{
			  breakpoint: 800,
			  settings: {
				slidesToShow: 3,
				slidesToScroll: 2
			  }
			},
			{
			  breakpoint: 600,
			  settings: {
				slidesToShow: 2,
				slidesToScroll: 1
			  }
			},
			{
			  breakpoint: 480,
			  settings: {
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			}
			
		  ]
	});  
}
function buildAlertStatusCommentsTrackingDetails(result)
{
	var str='';
	
	str+='<div class="col-md-12 col-xs-12 col-sm-3">';
		str+='<ul class="nav nav-tabs alertCommentUl" role="tablist">';
		for(var i in result)
		{
			if(i==0)
			{
				str+='<li role="presentation" class="active m_top10"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab">'+result[i].status+'<br/><span class="color_FF">'+result[i].sublist2[0].date+'<span></a></li>';
			}else{
				str+='<li class="m_top10" role="presentation"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab">'+result[i].status+'<br/><span class="color_FF">'+result[i].sublist2[0].date+'</span></a></li>';
			}
			
		}
		str+='</ul>';
		str+='<div class="tab-content alertComment">';
			for(var i in result)
			{
				if(i==0)
				{
					str+='<div role="tabpanel" class="tab-pane active" id="commentStatus'+i+'">';
				}else{
					str+='<div role="tabpanel" class="tab-pane" id="commentStatus'+i+'">';
				}
				for(var j in result[i].sublist2)
				{
					str+='<div class="row m_top10">';
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
											str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].sublist2[j].sublist[k][0].userName+'</span></p>';
											str+='<hr style="margin-top:20px;"/>';
										str+='</div>';   
									}
									str+='</div>';    
								str+='</li>';
							str+='</ul>';
						str+='</div>';
					str+='</div>';
				}           
			str+='</div>';
			}
		str+='</div>';
	str+='</div>';
	$("#alertCommentsDiv").html(str);
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

function getAssignGroupTypeAlertDtlsByImpactLevelWise(scopeIdsArr){
	 $(".groupAssignCls").show();
	 $("#groupAssignAlertDlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 	var dates=$("#dateRangeIdForAlert").val();
	     var fromDateStr;
		 var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : fromDateStr,        
			toDate : toDateStr,             
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId                                 
		}                  
		$.ajax({
			type : 'POST',      
			url : 'getAssignGroupTypeAlertDtlsByImpactLevelWiseAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){    
           if(result != null && result.length > 0){
				buildAlertAssignGroupAlertsDtls(result);
			}else{
				$("#groupAssignAlertDlsDivId").html('NO DATA AVAILABLE.');
			}
		});  
	}
	function buildAlertAssignGroupAlertsDtls(result){  
		$("#groupAssignAlertDlsDivId").html("");
		var str ='';
		if(result !=null && result.length >0){
			    
				   for(var i in result){
		              if(result[i].totalAlertCnt > 0){
					  str+='<div class="col-md-3 col-xs-12 col-sm-12">';
						
						str+='<h4 class="alertAssignCls text-capital" attr_type='+result[i].name+' style="text-align:center;cursor:pointer;color:rgb(51, 122, 183)">'+result[i].name+" - "+result[i].totalAlertCnt+'</h4>';
						str+='<div id="groupAssign'+i+'" style="height:300px;width:250px"></div>'; 
					  str+='</div>';
			         }
			      }
		 			
		}
		$("#groupAssignAlertDlsDivId").html(str);  
			if(result !=null && result.length >0){
				for(var i in result){
					var groupAssingName;
					groupAssingName = result[i].name+"["+result[i].totalAlertCnt+"]";
					if(result[i].subList1 !=null && result[i].subList1.length >0){
						var groupAssignTypeName =[];
						var alertCnt = [];
						var count = [];
						for(var j in result[i].subList1){
							var uniqCnt = {};
							if(result[i].totalAlertCnt > 0){
								groupAssignTypeName.push(result[i].subList1[j].statusType);
								alertCnt.push(result[i].subList1[j].alertCount);
								var uniqCnt = {y:parseInt(result[i].totalAlertCnt)-parseInt(result[i].subList1[j].alertCount),color:"#D3D3D3"};
								count.push(uniqCnt);
							}
						}
						if(alertCnt.length != 0){
						$(function () {
									$('#groupAssign'+i+'').highcharts({
										colors: ['#A185BF','#0166FF','#32CCFE','#019966','#FF6600','#CC0001'],     
										chart: {
											type: 'column'
										},
										title: {
											text: null
										},
									   
										xAxis: {
											 min: 0,
												 gridLineWidth: 0,
												 minorGridLineWidth: 0,
												 categories: groupAssignTypeName,
											labels: {
													rotation: -45,
													style: {
														fontSize: '11px',
														fontFamily: 'Verdana, sans-serif'
													},
												}
										},
										yAxis: {
											min: 0,
												   gridLineWidth: 0,
													minorGridLineWidth: 0,
											title: {
												text: ''
											}
										},
										tooltip: {
											formatter: function () {
												var s = '<b>' + this.x + '</b>';

													$.each(this.points, function () {
													if(this.series.name != "Series 1")  
													s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
														this.y+' - ' +
														(Highcharts.numberFormat(this.percentage,1)+'%');
												});

												return s;
											},
											shared: true
										},
										legend: {   
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: {
												stacking: 'percent',  
												dataLabels:{
													enabled: false,
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.percentage,1) + '%';
														}
													}
												},
												
											},
										},
										series: [{
											data: count  
										}, {
											name: "Number of alerts",
											data: alertCnt,
											colorByPoint: true
										}]
									});
								});	
						}else{
						 $("#groupAssign"+i).css("height","35px");
						}
					}
				}
			}else{
				$("#groupAssignAlertDlsDivId").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
			if ($('#groupAssignAlertDlsDivId').is(':empty')){ // hiding heading if highchart is not building
				$(".groupAssignCls").hide();
			}
	}
	
	$(document).on("click",".alertAssignCls",function(){
		   var groupAssignType = $(this).html().split("-")[0].trim();
		 	var scopeIdsArr = [];
		  $(".alertSettingsUl li").each(function() {
			  if($(this).find("input").is(":checked")){
				 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
				 if(selectionType == "District"){
					scopeIdsArr.push(2);
				}else if(selectionType == "Constituency"){
					scopeIdsArr.push(3);
				}else if(selectionType == "mandalMuncipality"){
					scopeIdsArr.push(5);  
					scopeIdsArr.push(8);
				}else if(selectionType == "VillageWard"){
					scopeIdsArr.push(7);  
					scopeIdsArr.push(9);	
				}
			  }
		   });
		  if(groupAssignType == "Public Representative"){
			  getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,groupAssignType,0,"alertDetailsDivId",[0],"")
		  }else if(groupAssignType == "Party Committee"){
			  var commitLvlIdArr = [];
			  commitLvlIdArr.push(10);
			  commitLvlIdArr.push(11);
			  commitLvlIdArr.push(5);
			  commitLvlIdArr.push(7);
			  commitLvlIdArr.push(6);
			  commitLvlIdArr.push(8);    
			  getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,groupAssignType,0,"alertDetailsDivId",commitLvlIdArr,"");  
		  }else if(groupAssignType == "Program Committee"){
			  getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(scopeIdsArr,groupAssignType,"alertDetailsDivId")
		  }else if(groupAssignType == "Others"){
			  getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(scopeIdsArr,groupAssignType,"alertDetailsDivId");
		  }
	});
	
function getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,groupAssignType,publicRepresentativeTypeId,divId,commitLvlIdArr,level){
	$("#commitLvlId").hide();  
	if(groupAssignType == "Party Committee"){
		$("#commitLvlId").show();
	}else{
		$("#commitLvlId").hide();        
	}
  $("#alertModalHeadingId").html(groupAssignType+" Alert Details")
  $("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
  $("#alertModalId").modal("show");
  	var dates=$("#dateRangeIdForAlert").val();
	var fromDateStr;
	var toDateStr;
	if(dates != null && dates!=undefined){
		var datesArr = dates.split("-");
		fromDateStr = datesArr[0]; 
		toDateStr = datesArr[1]; 
	}
  var jsObj = { 
    stateId : globalStateId,             
    fromDate : fromDateStr,      
    toDate : toDateStr,  
    scopeIdsArr : scopeIdsArr,                       
    activityMemberId : globalActivityMemberId,
    publicRepresentativeTypeId :publicRepresentativeTypeId,
	groupAssignType : groupAssignType,
	commitLvlIdArr : commitLvlIdArr  
  }                  
  $.ajax({
    type : 'POST',        
    url : 'getTotalAlertGroupByPubRepThenStatusAction.action',
    dataType : 'json',      
    data : {task:JSON.stringify(jsObj)}      
  }).done(function(result){
   if(result != null && result.length > 0){
       buildAlertGroupAssignDtlsRlst(result,divId,groupAssignType,level,publicRepresentativeTypeId,0);
    }else{
	  $("#"+divId).html("NO DATA AVAILABLE.");	  
	}
  });  
}  
	function buildAlertGroupAssignDtlsRlst(result,divId,groupAssignType,level,publicRepresentativeTypeId,commitTypeId){
		var str = '';
		str+='<div class="table-responsive">';
		str+='<table class="table table-bordered tablePopup">';
			str+='<thead>';
				str+='<th>'+groupAssignType+'</th>';
				str+='<th>total alerts</th>';
				if(result[0].subList1 != null && result[0].subList1.length > 0){
					for(var i in result[0].subList1){
						str+='<th>'+result[0].subList1[i].category+'</th>';
					}  
				}
				str+='</thead>';
				str+='<tbody>';
			for(var i in result){
				str+='<tr>';
				if(groupAssignType == "Public Representative" && level == "bellow"){
					str+='<td>'+result[i].status+'</td>';
				}else if(groupAssignType == "Party Committee" && level == "bellow"){  
					str+='<td>'+result[i].status+'<i class="glyphicon glyphicon-plus expandIcon2" attr_designation_id="'+result[i].statusId+'" attr_commit_id="'+commitTypeId+'" attr_selected_type=\''+groupAssignType+'\' attr_div_id="memberTblId_'+result[i].statusId+'"></i></td>';       
				}else{      
					str+='<td>'+result[i].status+'<i class="glyphicon glyphicon-plus expandIcon" attr_designation_id="'+result[i].statusId+'"  attr_selected_type=\''+groupAssignType+'\' attr_div_id="memberTblId'+result[i].statusId+'"></i></td>';
				} 
				if(groupAssignType == "Public Representative" && level == "bellow"){
					str+='<td style="cursor:pointer;" class="pubRepDtlsCls" attr_cadre_id="'+result[i].statusId+'" attr_pub_rep_type_id="'+publicRepresentativeTypeId+'" attr_status_id="0">'+result[i].count+'</td>';
				}else{
					str+='<td>'+result[i].count+'</td>';
				}  
					   
				if(result[i].subList1 != null && result[i].subList1.length > 0){
					for(var j in result[i].subList1){
						if(result[i].subList1[j].categoryCount == 0){
							str+='<td>'+result[i].subList1[j].categoryCount+'</td>';
						}else{
							if(groupAssignType == "Public Representative" && level == "bellow"){
								str+='<td style="cursor:pointer;" class="pubRepDtlsCls" attr_cadre_id="'+result[i].statusId+'" attr_pub_rep_type_id="'+publicRepresentativeTypeId+'" attr_status_id="'+result[i].subList1[j].categoryId+'">'+result[i].subList1[j].categoryCount+'</td>';
							}else{
								str+='<td>'+result[i].subList1[j].categoryCount+'</td>';      
							}
						}
							
					}
				}
				str+='</tr>';
				//str+='<tr class="subElement" style="display: none">';
				if(groupAssignType == "Party Committee" && level == "bellow"){
					str+='<tr class="subElement_" style="display: none">';
					str+='<td id="memberTblId_'+result[i].statusId+'" colspan="7">';     
				}else{
					str+='<tr class="subElement" style="display: none">';
					str+='<td id="memberTblId'+result[i].statusId+'" colspan="7">';
				}
				str+='</td>';      
				str+='</tr>';
			}
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		$("#"+divId).html(str);
	}  
		
		
 function getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(scopeIdsArr,groupAssignType,divId){
	  $("#commitLvlId").hide();
	  $("#alertModalHeadingId").html(groupAssignType+" Alert Details")
	  $("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  $("#alertModalId").modal("show");
	  	var dates=$("#dateRangeIdForAlert").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : fromDateStr,        
			toDate : toDateStr,             
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
            resultType:groupAssignType			
		}                  
		$.ajax({
			type : 'POST',      
			url : 'getOtherAndPrgrmCmmtteeTypeAlertCndtDtlsAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){       
			if(result != null && result.length > 0){
				buildProgramCommiteeAndOtherMemberDtls(result,divId);
			}else{
			  $("#"+divId).html("NO DATA AVAILABLE.");	
			}
		});  
	}
function buildProgramCommiteeAndOtherMemberDtls(result,divId){
		var str = '';
		str+='<div class="table-responsive">';
		 str+='<table class="table table-bordered tablePopup">';
			 str+='<thead>';
			   str+='<th>Candidate Name</th>';
			   str+='<th>total alerts</th>';
			   if(result[0].subList1 != null && result[0].subList1.length > 0){
				   for(var i in result[0].subList1){
					   str+='<th>'+result[0].subList1[i].statusType+'</th>';
				   }  
			   }
			 str+='</thead>';
			 str+='<tbody>';
			 for(var i in result){
				   str+='<tr>';
				   str+='<td>'+result[i].name+'</td>';
					 str+='<td>'+result[i].totalAlertCnt+'</td>';
					 if(result[i].subList1 != null && result[i].subList1.length > 0){
						 for(var j in result[i].subList1){
								str+='<td>'+result[i].subList1[j].statusCnt+'</td>';
						}
					 }
				  str+='</tr>';
		 }
		  str+='</tbody>';
		  str+='</table>';
		  str+='</div>';
			 $("#"+divId).html(str);
	}

	$(document).on("click",".expandIcon",function(){
		if($(this).hasClass("glyphicon-minus") == true){
			$(".expandIcon").addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$(this).closest("tr").next("tr.subElement").hide();
		}else{
			var divId = $(this).attr("attr_div_id");
			var selectTypeId = $(this).attr("attr_selected_type");
			var designationId = $(this).attr("attr_designation_id");
			var scopeIdsArr = [];
		  	$(".alertSettingsUl li").each(function() {
				if($(this).find("input").is(":checked")){
					var selectionType = $(this).find("input").attr("attr_scope_type").trim();
					if(selectionType == "District"){
						scopeIdsArr.push(2);
					}else if(selectionType == "Constituency"){
						scopeIdsArr.push(3);
					}else if(selectionType == "mandalMuncipality"){
						scopeIdsArr.push(5);  
						scopeIdsArr.push(8);
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
					}
				}
			});
			var commitLvlIdArr = [];
			var commitLvlVal = $("input:radio[name=commitLvlName]:checked").val();
			if(commitLvlVal == "All"){
				commitLvlIdArr.push(10);
				commitLvlIdArr.push(11);
				commitLvlIdArr.push(5);
				commitLvlIdArr.push(7);
				commitLvlIdArr.push(6);
				commitLvlIdArr.push(8);
			}else if(commitLvlVal == "State"){
				 commitLvlIdArr.push(10);
			}else if(commitLvlVal == "District"){
				commitLvlIdArr.push(11);
			}else if(commitLvlVal == "Mandal"){
				commitLvlIdArr.push(5);
				commitLvlIdArr.push(7);
			}else if(commitLvlVal == "Village"){
				commitLvlIdArr.push(6);
				commitLvlIdArr.push(8);
			}  
			if(selectTypeId == "Party Committee"){    
				getTotalAlertGroupByPubRepThenStatusBellow(commitLvlIdArr,designationId,divId,selectTypeId);
			} 
			if(selectTypeId == "Public Representative"){           
				getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,selectTypeId,designationId,divId,[0],"bellow");   
			}
			
			$(".expandIcon").addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$(this).removeClass("glyphicon-plus").addClass("glyphicon-minus");
			$(".subElement").hide();
			$(this).closest("tr").next("tr.subElement").show();
		}  
	});
	$(document).on("click",".expandIcon2",function(){     
		if($(this).hasClass("glyphicon-minus") == true){
			$(".expandIcon2").addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$(this).closest("tr").next("tr.subElement_").hide();
		}else{
			var designationId = $(this).attr("attr_designation_id");
			var commitTypeId = $(this).attr("attr_commit_id");
			var selectionType = $(this).attr("attr_selected_type");
			var divId = $(this).attr("attr_div_id");
			var commitLvlIdArr = [];
			var commitLvlVal = $("input:radio[name=commitLvlName]:checked").val();
			if(commitLvlVal == "All"){
				commitLvlIdArr.push(10);
				commitLvlIdArr.push(11);
				commitLvlIdArr.push(5);
				commitLvlIdArr.push(7);
				commitLvlIdArr.push(6);
				commitLvlIdArr.push(8);
			}else if(commitLvlVal == "State"){
				 commitLvlIdArr.push(10);
			}else if(commitLvlVal == "District"){
				commitLvlIdArr.push(11);
			}else if(commitLvlVal == "Mandal"){
				commitLvlIdArr.push(5);
				commitLvlIdArr.push(7);
			}else if(commitLvlVal == "Village"){
				commitLvlIdArr.push(6);
				commitLvlIdArr.push(8);
			} 
			var scopeIdsArr = [];
		  	$(".alertSettingsUl li").each(function() {
				if($(this).find("input").is(":checked")){
					var selectionType = $(this).find("input").attr("attr_scope_type").trim();
					if(selectionType == "District"){
						scopeIdsArr.push(2);
					}else if(selectionType == "Constituency"){
						scopeIdsArr.push(3);
					}else if(selectionType == "mandalMuncipality"){
						scopeIdsArr.push(5);  
						scopeIdsArr.push(8);
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
					}
				}
			});
			getMemForPartyCommitDesg(commitTypeId,designationId,commitLvlIdArr,scopeIdsArr,selectionType,divId);
			alert(commitTypeId+":"+designationId+":"+commitLvlIdArr+":"+scopeIdsArr+":"+selectionType+":"+divId);
			//1:1:10,11,5,7,6,8:2,3,5,8,7,9:Party Committee:memberTblId_1
			$(".expandIcon2").addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$(this).removeClass("glyphicon-plus").addClass("glyphicon-minus");
			$(".subElement_").hide();    
			$(this).closest("tr").next("tr.subElement_").show();    
		}             
	}); 

	$(document).on("click",".commitLvlCls",function(){
		var scopeIdsArr = [];
		$(".alertSettingsUl li").each(function() {
			  if($(this).find("input").is(":checked")){
				 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
				 if(selectionType == "District"){
					scopeIdsArr.push(2);
				}else if(selectionType == "Constituency"){
					scopeIdsArr.push(3);
				}else if(selectionType == "mandalMuncipality"){
					scopeIdsArr.push(5);  
					scopeIdsArr.push(8);
				}else if(selectionType == "VillageWard"){
					scopeIdsArr.push(7);  
					scopeIdsArr.push(9);	
				}
			  }
		   });
		var commitLvlIdArr = [];
		var commitLvlVal = $("input:radio[name=commitLvlName]:checked").val();
		if(commitLvlVal == "All"){
			commitLvlIdArr.push(10);
			commitLvlIdArr.push(11);
			commitLvlIdArr.push(5);
			commitLvlIdArr.push(7);
			commitLvlIdArr.push(6);
			commitLvlIdArr.push(8);
			getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,"Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");
		}else if(commitLvlVal == "State"){
			 commitLvlIdArr.push(10);
			 getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,"Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");
		}else if(commitLvlVal == "District"){
			commitLvlIdArr.push(11);
			getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,"Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");
		}else if(commitLvlVal == "Mandal"){
			commitLvlIdArr.push(5);
			commitLvlIdArr.push(7);
			getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,"Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");
		}else if(commitLvlVal == "Village"){
			commitLvlIdArr.push(6);
			commitLvlIdArr.push(8);    
			getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,"Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");
		}
	});
	function getTotalAlertGroupByPubRepThenStatusBellow(commitLvlIdArr,commitTypeId,divId,selectTypeId){
		  var scopeIdsArr = [];		
		  $(".alertSettingsUl li").each(function() {
			  if($(this).find("input").is(":checked")){
				 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
				 if(selectionType == "District"){
					scopeIdsArr.push(2);
				}else if(selectionType == "Constituency"){
					scopeIdsArr.push(3);
				}else if(selectionType == "mandalMuncipality"){
					scopeIdsArr.push(5);  
					scopeIdsArr.push(8);
				}else if(selectionType == "VillageWard"){
					scopeIdsArr.push(7);  
					scopeIdsArr.push(9);	
				}
			  }
		   });
		var dates=$("#dateRangeIdForAlert").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : fromDateStr,        
			toDate : toDateStr,                  
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
			commitLvlIdArr : commitLvlIdArr,
			designationId : commitTypeId,
			groupAssignType : selectTypeId,      
			position : "bellow"
		}                  
		$.ajax({
			type : 'POST',                
			url : 'getDesigWiseTdpCommitAlertCountAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){       
			if(result != null && result.length > 0){
				buildAlertGroupAssignDtlsRlst(result,divId,selectTypeId,"bellow",0,commitTypeId);
			}else{
			  $("#"+divId).html("NO DATA AVAILABLE.");	  
			}
		});  
	}
	$(document).on("click",".pubRepDtlsCls",function(){
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");
		$("#alertCntTitId").html("TOTAL ALERTS");
		var publicRepresentativeTypeId = $(this).attr("attr_pub_rep_type_id");
		var cadreId = $(this).attr("attr_cadre_id");
		var statusId = $(this).attr("attr_status_id");
		var dates=$("#dateRangeIdForAlert").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var scopeIdsArr = [];		
		$(".alertSettingsUl li").each(function() {
			if($(this).find("input").is(":checked")){
				var selectionType = $(this).find("input").attr("attr_scope_type").trim();
				if(selectionType == "District"){
					scopeIdsArr.push(2);
				}else if(selectionType == "Constituency"){
					scopeIdsArr.push(3);
				}else if(selectionType == "mandalMuncipality"){
					scopeIdsArr.push(5);  
					scopeIdsArr.push(8);
				}else if(selectionType == "VillageWard"){
					scopeIdsArr.push(7);  
					scopeIdsArr.push(9);	
				}
			}
		});
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : fromDateStr,        
			toDate : toDateStr,                  
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
			publicRepresentativeTypeId : publicRepresentativeTypeId,
			cadreId : cadreId,
			statusId : statusId
		}                  
		$.ajax({
			type : 'POST',                
			url : 'getAlertDtlsForPubRepAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){       
			if(result != null && result.length > 0){
				buildAlertDtls(result);    
			}else{
			    
			}
		});  
	});  
	 function getStateImpactLevelAlertDtlsCnt(){
		 $(".hideStateLevelAlertCls").show();
		$("#processingImgDivId").show();
		$("#processingImgDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#stateWiseAlertDtlsDiv").html(' ');
		$("#categoryWiseAlertDiv").html(' ');
		$("#statusWiseAlertDiv").html(' ');
		var dates=$("#dateRangeIdForAlert").val();
		var fromDateStr;
		var toDateStr;
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			fromDateStr = datesArr[0]; 
			toDateStr = datesArr[1]; 
		}
		var scopeIdsArr = [];
            scopeIdsArr.push(1);		
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			fromDate:fromDateStr,        
			toDate :toDateStr,
			scopeIdsArr:scopeIdsArr			
		};
		$.ajax({
			type : 'GET',
			url : 'getStateImpactLevelAlertDtlsCntAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			$("#processingImgDivId").hide();
			if(result != null && result.subList.length > 0){
				buildStateWiseAlertCnt(result.subList);
			}else{
				$(".hideStateLevelAlertCls").hide();
			}
			if(result != null && result.categoryList.length > 0){
				buildCategoryWiseAlertCnt(result.categoryList);
			}
			if(result != null && result.statusList.length > 0){
				buildStatusWiseAlertCnt(result.statusList);
			}
      });	
	}
	
	function buildStateWiseAlertCnt(result){  
		var str='';
		var stateWiseAlertCnt = [];
		var totalAlertCnt = 0;
		for(var i in result){
			var obj1 = {
				name: result[i].name,
				y: result[i].alertCount,
				extra:result[i].alertCount       
			};
			totalAlertCnt = totalAlertCnt+parseInt(result[i].alertCount);
			stateWiseAlertCnt.push(obj1);
		}
		var heading = "OVERVIEW-"+(totalAlertCnt);
		$(function () {
			 $("#stateWiseAlertDtlsDiv").highcharts({  
				colors: ['#53BF8B'],    
				chart: {
					type: 'column'
				},
				title: {
					text: heading
					
				},
				subtitle: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					type: 'category',
					labels: {
						//rotation: -45,
						style: {
							fontSize: '11px',
							fontFamily: 'Verdana, sans-serif'
						},
					}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: ''
					},
					labels: {
						enabled:false
					}
				},
				legend: {
					enabled: false
				},	
				plotOptions: {
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,0);      
								}
							}
						}
					}
				},
				tooltip: {
					headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.extra}</b>'
				},
				series: [{    
					name: 'Number Of Alerts',    
					data: stateWiseAlertCnt
				}],
			});  
		});
	}
	 function buildCategoryWiseAlertCnt(result){  
			if(result !=null && result.length >0){
				var categoryName =[];
				var alertCounArr =[];	
                var totalAlertCnt = 0;				
					for(var i in result){
							categoryName.push(result[i].name);   
							alertCounArr.push(result[i].alertCount);
							totalAlertCnt = totalAlertCnt + parseInt(result[i].alertCount);
				     }
					 if(totalAlertCnt == 0){
						 return ;
					 }
					var heading = "CATEGORY WISE - "+totalAlertCnt
					$(function () {
							$('#categoryWiseAlertDiv').highcharts({
								colors: ['#808000','#00FFFF','#FF00FF'],     
								chart: {
									type: 'column'
								},
								title: {
									text: heading
								},
							   
								xAxis: {
									 min: 0,
										 gridLineWidth: 0,
										 minorGridLineWidth: 0,
										 categories: categoryName,
									labels: {
											//rotation: -45,
											style: {
												fontSize: '11px',
												fontFamily: 'Verdana, sans-serif'
											},
											formatter: function() {
												return this.value.toString().substring(0, 8)+'...';
											},
										}
								},
								yAxis: {
									min: 0,
										   gridLineWidth: 0,
											minorGridLineWidth: 0,
									title: {
										text: ''
									}
								},
								tooltip: {
									formatter: function () {
										var s = '<b>' + this.x + '</b>';

										$.each(this.points, function () {
											s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :'+(this.y);
										});

										return s;
									},
									shared: true
								},
								legend: {
														
										enabled: false,				
														
									},				
								plotOptions: {
									column: {        
										dataLabels:{
											enabled: false,
											formatter: function() {
												if (this.y === 0) {
													return null;
												} else {
													return Highcharts.numberFormat(this.percentage,1) + '%';
												}
											}
										},
										
									},
								},
								series: [{
									name: 'Number of alert',
									data: alertCounArr,
									colorByPoint: true
								}]
							});
						});
			    }	  
			else{
				$("#categoryWiseAlertDiv").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
	 }
			
	function buildStatusWiseAlertCnt(result){
			if(result !=null && result.length >0){
			  var statusNameArr =[];
				var alertCnt = [];
				var count = [];
				var totalAlertCnt = 0;
				   for(var i in result){
					  totalAlertCnt = totalAlertCnt+parseInt(result[i].alertCount);
					}
					if(totalAlertCnt == 0){
						return;
					}
					for(var i in result){
								 var uniqCnt = {};
									statusNameArr.push(result[i].name);
									alertCnt.push(result[i].alertCount);
									var uniqCnt = {y:parseInt(totalAlertCnt)-parseInt(result[i].alertCount),color:"#D3D3D3"};
									count.push(uniqCnt);
					}
				    var heading = "STATUS WISE - "+totalAlertCnt
				             $(function () {
									$('#statusWiseAlertDiv').highcharts({
										colors: ['#A185BF','#0166FF','#32CCFE','#019966','#FF6600','#CC0001'],     
										chart: {
											type: 'column'
										},
										title: {
											text: heading
										},
									   
										xAxis: {
											 min: 0,
												 gridLineWidth: 0,
												 minorGridLineWidth: 0,
												 categories: statusNameArr,
											labels: {
													//rotation: -45,
													style: {
														fontSize: '11px',
														fontFamily: 'Verdana, sans-serif'
													},
													formatter: function() {
														return this.value.toString().substring(0, 8)+'...';
													},
												}
										},
										yAxis: {
											min: 0,
												   gridLineWidth: 0,
													minorGridLineWidth: 0,
											title: {
												text: ''
											},
											
										},
										tooltip: {
											formatter: function () {
												var s = '<b>' + this.x + '</b>';

													$.each(this.points, function () {
													if(this.series.name != "Series 1")  
													s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
														this.y+' - ' +
														(Highcharts.numberFormat(this.percentage,1)+'%');
												});

												return s;
											},
											shared: true
										},
										
										legend: {   
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: {
												stacking: 'percent',  
												dataLabels:{
													enabled: false,
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.percentage,1) + '%';
														}
													}
												},
												
											},
										},
										series: [{
											data: count    
										}, {
											name: "Number of alerts",
											data: alertCnt,
											colorByPoint: true
										}]
									});
								});	
			}else{
				$("#statusWiseAlertDiv").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
		}	 