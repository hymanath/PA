	var customStartDateAlert = moment().format('DD/MM/YYYY')
	var customEndDateAlert = moment().format('DD/MM/YYYY');
	
	function globalAlertsCalls(type)
	{
		if(type == "default"){
			$('#dateRangeIdForAlert').data('daterangepicker').setStartDate(moment());
			$('#dateRangeIdForAlert').data('daterangepicker').setEndDate(moment());
			customStartDateAlert = moment().format("DD/MM/YYYY")
			customEndDateAlert = moment().format("DD/MM/YYYY")
			$("#alertDateHeadingId").html("TODAY"+"("+moment().format("DD/MM/YYYY")+"-"+moment().format("DD/MM/YYYY")+" )");
		}else if(type == "currentMonth"){
			$('#dateRangeIdForAlert').data('daterangepicker').setStartDate(moment().startOf("month"));
			$('#dateRangeIdForAlert').data('daterangepicker').setEndDate(moment().endOf("month"));
			customStartDateAlert = moment().startOf("month").format("DD/MM/YYYY")
			customEndDateAlert = moment().endOf("month").format("DD/MM/YYYY")
			$("#alertDateHeadingId").html("THIS MONTH"+"("+moment().startOf("month").format("DD/MM/YYYY")+"-"+moment().endOf("month").format("DD/MM/YYYY")+" )");
		}else if(type == "lastMonth"){
			$('#dateRangeIdForAlert').data('daterangepicker').setStartDate(moment().subtract(1,'month').startOf("month"));
			$('#dateRangeIdForAlert').data('daterangepicker').setEndDate(moment().subtract(1,'month').endOf("month"));
			customStartDateAlert = moment().subtract(1,'month').startOf("month").format("DD/MM/YYYY")
			customEndDateAlert = moment().subtract(1,'month').endOf("month").format("DD/MM/YYYY")
			$("#alertDateHeadingId").html("LAST MONTH"+"("+moment().subtract(1,'month').startOf("month").format("DD/MM/YYYY")+"-"+moment().subtract(1,'month').endOf("month").format("DD/MM/YYYY")+" )");
		}
		$("#dateRangeIdForAlert").val(customStartDateAlert+"-"+customEndDateAlert);
		getAlertOverviewDetails();
		if($(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".alertLocationDiv").show();
			defaultAlertCalls();
		}
	}
	$(document).on("click",".alertsIconRefresh",function(){
		globalAlertsCalls('');
	});
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
	$("#alertDateHeadingId").html(" Today("+dates+")");
	var singleBlockDateStart = moment().startOf('month').format('MMM YY');
	var singleBlockDateEnd = moment().format('MMM YY');
	$('#dateRangeIdForAlert').on('apply.daterangepicker', function(ev, picker) {
	  customStartDateAlert = picker.startDate.format('DD/MM/YYYY');
	  customEndDateAlert = picker.endDate.format('DD/MM/YYYY');
	  var scopeIdsArr = [2,3,8,5,7,9,6];
		/* scopeIdsArr.push(2);  
		scopeIdsArr.push(3);  
		scopeIdsArr.push(6);  
		scopeIdsArr.push(7);  
		scopeIdsArr.push(9); 
		scopeIdsArr.push(5); 
		scopeIdsArr.push(8);  
		scopeIdsArr.push(10);  
		scopeIdsArr.push(11);  
		scopeIdsArr.push(1);  
		scopeIdsArr.push(4);   */
		
		//hide district and constituency div
		 $(".districtAlertCls").hide();
		 $(".constituencyAlertCls").hide();
		 $(".districtSelectBoxCls").hide();
		 $("#dstrctSlctBxId").val(0);
	 
	  $(".alertImpactCheckCls").prop('checked', true); //checked all scope level
	  $(".alertConImpactCheckCls").prop('checked', true); //checked all scope level
	  $(".alertFilterCls li").removeClass("active");
	  $(".alertFilterCls li:first-child").addClass("active");
	 $("#alertTypeHiddenId").attr("attr_alert_id",0);
	 $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id",0);        
	 getAlertOverviewDetails();
	 getAlertCategoryDtlsLocationWise(0,0); 
	 getAssignGroupTypeAlertDtlsByImpactLevelWise(scopeIdsArr);
	 getTotalAlertGroupByDist(scopeIdsArr,"date");  
	 getStateImpactLevelAlertDtlsCnt("date");
	 var dates= $("#dateRangeIdForAlert").val();
	 $("#alertDateHeadingId").html(picker.chosenLabel+" ( "+dates+" )");
	
	
	});
	
	function defaultAlertCalls()
	{
		$(".alertImpactCheckCls").prop('checked', true);
		//var scopeIdsArr = [2,3,6,7,9,5,8,10,11,1,4];
		var scopeIdsArr = [2,3,8,5,7,9,6];
		$("#districtSummaryAlertDivId").html(" ");
		$("#constituencyAlertDivId").html("");
		$(".constituencyAlertCls").hide();
		$(".districtAlertCls").hide();
		$("#dstrctSlctBxId").val(0);
		$(".districtSelectBoxCls").hide();
		getAlertCategoryDtlsLocationWise($("#alertTypeHiddenId").attr("attr_alert_id"),$("#alertEditionTypeHiddenId").attr("attr_alert_edition_id"));
		getTotalAlertGroupByDist(scopeIdsArr,"other");
		$(".alertFilterCls li").removeClass("active");
		$(".alertFilterCls li:first-child").addClass("active");
		getAssignGroupTypeAlertDtlsByImpactLevelWise(scopeIdsArr);
		getStateImpactLevelAlertDtlsCnt("other");
		//santosh
	}
	$(document).on("click",".alertsIconExpand",function(){
		$(".dateRangePickerClsForAlert").toggleClass("hide");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".alertsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".alertLocationDiv").show();
			//console.log("opening")
			defaultAlertCalls();
			
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
			$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents,.comparisonBlockActivities ").hide();
			$(".panelBlockCollapseIcon").addClass("collapsed");
			$(".activitesExpandIcon").parent().parent().parent().parent().find(".collapse").removeClass("in").addClass("collapsed");
			$(".activitesExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
			$(".eventsListExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
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
		}else if( $(".NewTourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".NewTourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".NewTourExpandCls,.NewToursHiddenBlock,.moreNewToursBlocksDetailed").hide();
			$(".NewToursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".tourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".tourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".tourExpandCls ,.toursHiddenBlock,.moreToursBlocks1,.moreToursBlocksDetailed ,.comparisonBlockTours ,.toursDateRangePickerCls").hide();
				$(".toursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".emnIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".emnIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".moreBlockEMN ,.newEmnHideCls,.dateRangePickerClsForEmn,.newsComparisonUl").hide();
				$(".electronicMediaBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}
		});
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
			}else if(selectionType == "VillageWard"){
				scopeIdsArr.push(7);  
				scopeIdsArr.push(9);	
				scopeIdsArr.push(6);	
			}else if(selectionType == "MuncipalityGMC"){
			   scopeIdsArr.push(8);
			}/* else if(selectionType == "National"){
				scopeIdsArr.push(10);  	
			}else if(selectionType == "International"){
				scopeIdsArr.push(11);  
			}else if(selectionType == "State"){
				scopeIdsArr.push(1);	
			}else if(selectionType == "Parliament"){
				scopeIdsArr.push(4);	
			} */
		  }
	   });
	  var locVal ='';
		$( "li.optionsCls" ).each(function() {
			if($( this ).hasClass( "active" )){
				locVal = $(this).attr("attr_id");
			}
		});
		if(scopeIdsArr.length == 0){
			alert("Please select atleast one option");
			return;  
		}
		$("#dstrctSlctBxId").val(0);
		if(locVal == "1"){   
			$(".districtAlertCls").hide();
			$(".constituencyAlertCls").hide();	
			$(".districtSelectBoxCls").hide();			
			getTotalAlertGroupByDist(scopeIdsArr,"other");      
		}else if(locVal == "2"){
			$(".districtAlertCls").hide();
			$(".constituencyAlertCls").hide();
			$(".districtSelectBoxCls").hide();
			getTotalAlertGroupByLocationThenCategory(scopeIdsArr);
		}else{
			$(".districtAlertCls").show();
			$(".constituencyAlertCls").show();
			$(".districtSelectBoxCls").show();
			getTotalAlertGroupByLocationThenStatus(scopeIdsArr);
			
			$(".alertConImpactCheckCls").prop('checked', true);//select all constituency impact scope
			var consScopeIdsArr = [];
		 $(".alertConsSettingsUl li").each(function() {
		  if($(this).find("input").is(":checked")){
			 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
		     if(selectionType == "Constituency"){
				consScopeIdsArr.push(3);
			}else if(selectionType == "mandal"){
				consScopeIdsArr.push(5);  
			}else if(selectionType == "VillageWard"){
				consScopeIdsArr.push(7);  
				consScopeIdsArr.push(9);	
				consScopeIdsArr.push(6);	
			}
		  }
	   });
		getTotalAlertConstituencyWise(consScopeIdsArr);
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
				}else if(selectionType == "VillageWard"){
					scopeIdsArr.push(7);  
					scopeIdsArr.push(9);	
					scopeIdsArr.push(6);	
				}else if(selectionType == "MuncipalityGMC"){
				   scopeIdsArr.push(8);
				}/* else if(selectionType == "National"){
				   scopeIdsArr.push(10);  	
			    }else if(selectionType == "International"){
				   scopeIdsArr.push(11);  
				}else if(selectionType == "State"){
				   scopeIdsArr.push(1);	
				}else if(selectionType == "Parliament"){
				   scopeIdsArr.push(4);	
			    } */
			  }
		   });
		if(option == "1"){
			$("#dstrctSlctBxId").val(0);
			$(".districtAlertCls").hide();
			$(".constituencyAlertCls").hide();	
			$(".districtSelectBoxCls").hide();
			getTotalAlertGroupByDist(scopeIdsArr,"other");
		}else if(option == "2"){
			$("#dstrctSlctBxId").val(0);
			$(".districtAlertCls").hide();
			$(".constituencyAlertCls").hide();
            $(".districtSelectBoxCls").hide();			
			getTotalAlertGroupByLocationThenCategory(scopeIdsArr);
		}else{
			$(".districtAlertCls").show();
			$(".constituencyAlertCls").show();
			$(".districtSelectBoxCls").show();			
			getTotalAlertGroupByLocationThenStatus(scopeIdsArr);
			
			getAssignGroupTypeAlertDtlsByImpactLevelWise(scopeIdsArr);
			
		 var consScopeIdsArr = [];
		 $(".alertConsSettingsUl li").each(function() {
		  if($(this).find("input").is(":checked")){
			 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
		     if(selectionType == "Constituency"){
				consScopeIdsArr.push(3);
			}else if(selectionType == "mandal"){
				consScopeIdsArr.push(5);  
			}else if(selectionType == "VillageWard"){
				consScopeIdsArr.push(7);  
				consScopeIdsArr.push(9);	
				consScopeIdsArr.push(6);	
			}
		  }
	   });
	   getTotalAlertConstituencyWise(consScopeIdsArr);
		}
	});
	function getTotalAlertGroupByDist(scopeIdsArr,location){ 
	
		$("#districtWiseAlertCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		if(location == "date"){
			alertId = 0;
			editionId = 0;  
		}  
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,             
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
			alertIds : alertId,
			editionIds : editionId
			
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
				extra:result[i].locationId       
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
					},
					series: {
						cursor: 'pointer',
						point: {
							events: {
								click: function () {//santosh
									var districtId = this.extra;
									var totalAlertCnt = this.y;
									var districtIdArr=[districtId];
									buildDistrictWiseAlert(districtIdArr,totalAlertCnt,0,0,"district","");
								}
							}
						}  
				     },
				},
				tooltip: {
					headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>'
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
		var editionId = $(this).attr("attr_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		getAlertDtls(alertStatusId, alertCategoryId, alertTypeId,alertCount,editionId,"No",0);  
		
	});  
	 $(document).on("click",".alertActionCls",function(){
		var alertStatusId = $(this).attr("attr_status_id");
		var alertTypeId = $(this).attr("attr_alert_type_id");
		var alertCount = $(this).attr("attr_count");
		var editionId = $(this).attr("attr_edition_id");
		var actionTypeId = $(this).attr("attr_action_type_id");
		if(editionId == undefined){
			editionId = 0;
		}
		getAlertDtls(alertStatusId,0, alertTypeId,alertCount,editionId,"Yes",actionTypeId);  
		
	});  
	
	function getAlertDtls(alertStatusId, alertCategoryId, alertTypeId,alertCount,editionId,isActionType,actionTypeId){
		
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");  
		$("#alertCntTitId").html("TOTAL ALERTS-"+alertCount);
		var jsObj = { 
			alertTypeId : alertTypeId,    
			alertStatusId : alertStatusId,
			alertCategoryId : alertCategoryId,  
			stateId : globalStateId,             
			fromDate : customStartDateAlert,                
			toDate : customEndDateAlert,                
			activityMemberId : globalActivityMemberId,
			editionIds : editionId,
            isActionType : isActionType,
            actionTypeId :actionTypeId			
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
		if($(window).width() < 800)
		{
			str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed alertDtlsTabStyle" id="alertDtlsTabId">';   
		}else{
			str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed" id="alertDtlsTabId">'; 
		}  
		str+='<thead>';
			str+='<tr>';
             str+='<th>Alert Source</th>';
             str+='<th>Title</th>';
             str+='<th>Created Date</th>';
			 str+='<th>Last Updated Date</th>';
			 str+='<th>Current Status</th>'	 
			 str+='<th>LAG Days</th>';
			 str+='<th>Alert Impact Level</th>';
			 str+='<th>Location</th>';
			 str+='</tr>';
		 str+='</thead>';
		 str+='<tbody>';
		 for(var i in result){
			str+='<tr>';
			if(result[i].source != null && result[i].source.length > 0){
				str+='<td><strong>'+result[i].source+'</strong></td>';         
			}else{
				str+='<td> - </td>';     
			}
			if(result[i].title != null && result[i].title.length > 0){
				str+='<td class="descAlertCls" style="cursor:pointer;" attr_alert_status="'+result[i].status+'" attr_alert_id="'+result[i].id+'"><strong><u>'+result[i].title+'</u></strong></td>';         
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
				str+='<td>'+(parseInt(result[i].interval)-parseInt(1))+'</td>';            
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
		$("#sourceHeadingId").html("");
		$("#headingNameId").html("");
		$("#alertAttachTitId").html("");
		$("#alertAttachImgId").html("");
		$("#alertInvolvedCandidates").html("");
		$("#alertAssignedCandidates").html("");
		$("#alertCommentsDiv").html("");
		$("#tourDocHeadingId").html("ALERT TITLE <br>");
		$("#alertVerificationDiv").html("");
		$("#alertVerificationDtlsDiv").html("");
		
		$("#alertDocHeadingId").html("");
		$("#alertDocId").html("");
		
		$("#cdrModelDivId").modal("show");
		var alertId = $(this).attr("attr_alert_id");
		var alertStatus = $(this).attr("attr_alert_status");
		getAlertData(alertId);
		getAlertAssignedCandidates(alertId);    
		getAlertStatusCommentsTrackingDetails(alertId,alertStatus);
		getVerificationDtls(alertId);
	});
	$(document).on("click",".modalClose",function(){  
		$(this).removeClass("modalClose");
		setTimeout(function(){
			$("body").addClass("modal-open");
		},1000);
		
	});
	$(document).on("click",".topModalClose",function(){
		setTimeout(function(){
			$('body').addClass("modal-open");
		}, 400);                     
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
			}else{
				var str = '';
				str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>';  
				$("#alertAssignedCandidates").html(str);    
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
			$("#alertGroupAttachTitId,#alertGroupAttachImgId").html(' ');
			if(result != null && result.length > 0){
				buildAlertData(result);
				if(result[0].categoryId == 2)
				{
					getGroupedArticlesInfo(result[0].alertCategoryTypeId)
				}
			}    
		});
	}
	function buildAlertData(result){
		var docName = '';
		var extName =[];
		$("#tourDocHeadingId").html("<h5 style='color:#FFFFFF;font-size:14px;'>ALERT TITLE</h5><h5 class='text-capital m_top10' style='color:#000'>"+result[0].title+"</h5>");
		$("#cdrModelId").html("<h5 class='text-muted headingColorStyling'>ALERT DESCRIPTION</h5>");
		$("#alertDestId").html("<p style='border: 1px solid rgb(211, 211, 211); padding: 6px;'>"+result[0].desc+"</p>");
		$("#sourceHeadingId").html("<h5 class='text-muted headingColorStyling'>ALERT SOURCE</h5>");
		$("#headingNameId").html("<p style='border: 1px solid rgb(211, 211, 211); padding: 10px;'>"+result[0].alertSource+"</p>");
		
		if(result[0].documentList != null && result[0].documentList.length >= 1){
			$("#alertDocHeadingId").html("<h5  class='text-muted headingColorStyling'>ALERT DOCUMENTS</h5>");
			var docStr = '';
			docStr+='<ul>';
			for(var i in result[0].documentList){
				docName = result[0].documentList[i];
				extName = docName.split(".");
				if(result[0].documentNameList[i].search('#') != -1 || result[0].documentNameList[i].search('u0') != -1){
					var randumNum = result[0].documentList[i].substring(result[0].documentList[i].indexOf("/")+1,result[0].documentList[i].lastIndexOf("."));      
					docStr+='<li id="document0'+i+'"><a href="/Reports/'+result[0].documentList[i]+'" target="_blank">'+randumNum+'.'+extName[1]+'</a></li>';  
				}else{
					docStr+='<li id="document0'+i+'"><a href="/Reports/'+result[0].documentList[i]+'" target="_blank">'+result[0].documentNameList[i]+'.'+extName[1]+'</a></li>';  
				}
				
			}
			docStr+='</ul>';
			$("#alertDocId").html(docStr);    
		}
		if(result[0].imageUrl != null && result[0].imageUrl.length > 1){    
			$("#alertAttachTitId").html("<h5  class='text-muted headingColorStyling'>ALERT ATTACHMENTS</h5>");
			var imgStr = '';
			imgStr+='<ul class="list-inline imageUrlUlCls" style="border: 1px solid rgb(211, 211, 211); padding:5px;">';
			imgStr+='<li><img src="http://mytdp.com/NewsReaderImages/'+result[0].imageUrl+'" style="width: 90px; height: 90px;cursor:pointer;" class="articleImgDetailsCls" attr_articleId="'+result[0].alertCategoryTypeId+'"></img></li>';
			imgStr+='</ul> '; 
			$("#alertAttachImgId").html(imgStr);  
		}
		var str='';
		var invCandCnt = 0;
		if(result[0].subList.length > 0){
			for(var i in result){
				for(var j in result[i].subList){
					if(result[i].subList[j].name != null && result[i].subList[j].name.length > 1){    
						invCandCnt+=1;
					}
				}    
			}
			str+='<h5 class="text-muted text-capital headingColorStyling">Involved Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;'+invCandCnt+'</h5>';           
			str+='<ul class="list-inline assignedCandidatesUl1">';     
			for(var i in result){
				for(var j in result[i].subList){   
					if(result[i].subList[j].name != null && result[i].subList[j].name.length > 1){
						str+='<li>';      
							str+='<p style="color: rgb(0, 0, 0);"><b>'+result[i].subList[j].name+'</b></p>';
							if(result[i].subList[j].mobileNo.length <= 1  || result[i].subList[j].mobileNo == null){
							}else{
								str+='<p><i> - </i>'+result[i].subList[j].mobileNo+'</p>';      
							}  
							if(result[i].subList[j].committeePosition != null){
								str+='<p><i> - </i>'+result[i].subList[j].committeePosition+'</p>';  
							}     
						str+='</li>';      
					}
				}    
			}
			str+='</ul>';      
			
			$("#alertInvolvedCandidates").html(str);       
		}else{
			str+='<h5 class="text-muted text-capital headingColorStyling">Involved Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>'; 
			$("#alertInvolvedCandidates").html(str);        
		}
		$(".assignedCandidatesUl1").slick({          
			 slide: 'li',
			 slidesToShow: 4,
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
	function getGroupedArticlesInfo(articleId)
	{
		$.ajax({
			  type : 'GET',      
			  url: wurl+"/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
			  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
		}).then(function(result){
			$("#alertGroupAttachTitId").html("<h5 class='text-muted headingColorStyling'>GROUPED ARTICLES</h5>");
			var str='';
			if(result !=null && result.length>0){
				str+='<ul class="list-inline imageUrlUlCls" style="border: 1px solid rgb(211, 211, 211); padding:5px;">';
				for(var i in result)
				{
					if(articleId != result[i].id){
						str+='<li class="articleImgDetailsCls" attr_articleId='+result[i].id+' style="cursor:pointer"><img src="http://mytdp.com/NewsReaderImages/'+result[i].name+'" style="width: 150px; height: 150px;margin-top:5px;"></img></li>';
					}
				}
				str+='</ul>';
			}
			$("#alertGroupAttachImgId").html(str);
		});
	}
	function getAlertStatusCommentsTrackingDetails(alertId,alertStatus){  
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
			if(result != null)           
				buildAlertStatusCommentsTrackingDetails(result,alertStatus);    
		});
	}
	function getAlertCategoryDtlsLocationWise(alertId,editionId){ 
		$("#locationWiseAlertDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		if(alertId == undefined){
			alertId = 0;
		}
		if(editionId == undefined){
			editionId = 0;
		}
		var jsObj={
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate :			customStartDateAlert,        
			toDate :			customEndDateAlert,
			alertIds : 			alertId,
			editionIds : 		editionId
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
		  str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
		  for(var i in result){
			  str+='<h5 class="text-capital m_top10 alertCategoryCls">'+result[i].name+'</h5>';      
			  str+='<div id="alertCategory'+i+'" attr_category_name='+result[i].name+' attr_id="alertCategory'+i+'" style="height:145px;"></div>';
		  }
		  str+='</div>';
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
						alertCntArr.push({"y":result[i].subList[j].alertCount,"extra":result[i].name,"id":result[i].subList[j].locationTypeId,"catId":result[i].id});   
					}
				}
		//if(alertCntArr !=null && alertCntArr.length > 0 ){
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
							return "Total Alerts - "+obj.alertCount+"<br/>Pending - " + obj.pendingCnt + "<br/>Notified - " + obj.notifiedCnt + " <br/>Action In Progess - " + obj.actionInProgessCnt+"<br/>Completed - " + obj.completedCnt + "<br/>Unable to Resolve - " + obj.unabletoResolveCnt + "<br/>Action Not Required - " + obj.actionNotRequiredCnt+"<br/>Duplicate - "+obj.duplicatesStatusCnt+"";     
						}
					},
					plotOptions: {
						column: {  
							stacking: 'normal',
							dataLabels: {
								enabled: true,
								align: 'left',
								 formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return Highcharts.numberFormat(this.y, 0) +""; 
									}
								}
							  
							}
						},
						series: {
							cursor: 'pointer',
							point: {
								events: {
									click: function () {
										var locationId = this.id;
										var totalAlertCnt = this.y;
										var catId = this.catId;
										buildLocationWiseAlertCnt(locationId,totalAlertCnt,catId);//swadhin
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
		
			}
		}else{
			$("#locationWiseAlertDivId").html('NO DATA AVAILABLE.');
		}
	}
	//ssss
	function buildLocationWiseAlertCnt(locationId,totalAlertCnt,catId){
	 	var alertTypeIds = $("#alertTypeHiddenId").attr("attr_alert_id")
		if(alertTypeIds == undefined){
			alertTypeIds = 0;
		}
		var editionIds = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id")
		if(editionIds == undefined){
			editionIds = 0;
		}
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');   
		$("#alertCntTitId").html("TOTAL ALERTS - "+totalAlertCnt);        
		$("#tourDocumentId").modal("show");
		var scopeIdsArr = [];		
		
		if(locationId == 1){
			scopeIdsArr.push(1);
		}else if(locationId == 2){
			scopeIdsArr.push(2);
		}else if(locationId == 3){
			scopeIdsArr.push(3);
		}else if(locationId == 4){
			scopeIdsArr.push(5);  
			scopeIdsArr.push(8);	
		}else if(locationId == 5){
			scopeIdsArr.push(7);  
			scopeIdsArr.push(9);
			scopeIdsArr.push(6);
		}
		var districtIdArr = [];
		var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,        
			toDate : 			customEndDateAlert,                  
			scopeIdsArr : 		scopeIdsArr,              
			activityMemberId : 	globalActivityMemberId,
			districtIdArr : 		districtIdArr,       
			catId : 			catId,
			alertTypeId : 		alertTypeIds,
			editionId : 		editionIds,
			constituencyId : 0 ,
			alertStatusId : 0,
			locationLevel : "District"
		
		}                  
		$.ajax({   
			type : 'POST',                
			url : 'getDistrictAndStateImpactLevelWiseAlertDtlsAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}           
		}).done(function(result){    
            if(result != null && result.length > 0){
				buildAlertDtls(result);    
			}else{
			$("#tourDocumentBodyId").html("NO DATA AVAILABLE.");	
			}
		}); 
	}
	function getTotalAlertGroupByLocationThenCategory(scopeIdsArr){
		$("#districtWiseAlertCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var dates=$("#dateRangeIdForAlert").val();
		 
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}


		var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,      
			toDate : 			customEndDateAlert,  
			scopeIdsArr : 		scopeIdsArr,              
			activityMemberId : 	globalActivityMemberId,       
			group : 			"",
			alertIds : 			alertId,
			editionIds : 		editionId
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
				str+='<li style="border-right:1px solid #ccc">';
				str+='<h5 class="districtcls text-capital" attr_alert_count="'+result[i].count+'" attr_district_id='+result[i].statusId+' style="text-align:center;cursor:pointer;color:rgb(51, 122, 183)">'+result[i].status+" - "+result[i].count+'</h5>';
				str+='<div id="distwisegraph'+i+'"  style="height:200px;width:220px"></div>';
				str+='</li>';
				
			}
			str+='</ul>'; 			
			str+='</div>'; 
			str+='</div>';			
		}
		$("#districtWiseAlertCountId").html(str);
			if(result !=null && result.length >0){
				for(var i in result){
				
					if(result[i].subList1 !=null && result[i].subList1.length >0){
						var categoryName =[];
						var count =[];
						for(var j in result[i].subList1){
							categoryName.push(result[i].subList1[j].category);
							var percent = (parseInt(result[i].subList1[j].categoryCount)/((parseInt(result[i].count))/100));      
							count.push({"y":percent,"locName":result[i].status,"catName":result[i].subList1[j].category});     
							//count.push(percent);    
						}
							if(categoryName.length !=0 && count.length !=0){
								$(function () {
									$('#distwisegraph'+i+'').highcharts({
										colors: ['#808000','#00FFFF','#FF00FF'],     
										chart: {
											type: 'column'
										},
										title: {
											text:''
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
										tooltip: {//ssss
												useHTML: true,
												backgroundColor: '#FCFFC5',
												formatter: function() {
													 var locName = this.point.locName;
													 var locationObj = result.filter(function ( obj1 ) {
															return obj1.status.toUpperCase().trim() === locName.toUpperCase().trim();
														})[0];
													 var categoryName = this.point.catName;
													 var catObj = locationObj.subList1.filter(function ( obj1 ) {
															return obj1.category.toUpperCase().trim() === categoryName.toUpperCase().trim();
														})[0];
													 return "Total Alerts - "+catObj.categoryCount+"";
												}
										},
										legend: {  
																
												enabled: false,				
																
											},				
										plotOptions: {
											column: { 
												stacking: 'normal',    
												dataLabels:{
													enabled: true,
													 formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															return Highcharts.numberFormat(this.y,1) +"%";
														}
													}
												}
											}
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
		$(".districtAlertCls").show();
		$("#districtWiseAlertCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#districtSummaryAlertDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
        var districtArr=[];
		if($("#dstrctSlctBxId").is(":visible")){
			var districtId = $("#dstrctSlctBxId").val();
			if(districtId > 0){
				districtArr.push(districtId);
			}
		}
		var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,      
			toDate : 			customEndDateAlert,  
			scopeIdsArr : 		scopeIdsArr,              
			activityMemberId : 	globalActivityMemberId,       
			group : 			"",
			alertIds : 			alertId,
			editionIds : 		editionId,
			filterType : 	"District",
			districtArr : districtArr
			
			
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getTotalAlertGroupByLocationThenStatusAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			$("#districtWiseAlertCountId").html(' ');
			$("#districtSummaryAlertDivId").html(' ');
			if(result != null && result.length > 0){  
				buildTotalAlertGroupByLocationThenStatus(result);
				buildAlertDetailsInDebularFormat(result,"districtSummaryAlertDivId","District");
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
				if(result[i].statusId == 0)
					continue;
				str+='<li style="border-right:1px solid #ccc">';
				str+='<h5 class="districtcls text-capital" attr_alert_count="'+result[i].count+'" attr_district_id='+result[i].statusId+' style="text-align:center;cursor:pointer;color:rgb(51, 122, 183)">'+result[i].status+" - "+result[i].count+'</h5>';
				str+='<div id="distwisegraph'+i+'"  style="height:300px;width:250px"></div>';
				str+='</li>';
			}
			str+='</ul>';			  
			str+='</div>'; 
			str+='</div>';			
		}
		$("#districtWiseAlertCountId").html(str);
			if(result !=null && result.length >0){
				for(var i in result){
					if(result[i].statusId == 0)
					  continue;
			
					if(result[i].subList1 !=null && result[i].subList1.length >0){
						var categoryName =[];
						var countAlert = [];
						var count = [];
						for(var j in result[i].subList1){
							if(result[i].subList1[j].categoryId == 5)  
								continue;    
							var uniqCnt = {};
							categoryName.push(result[i].subList1[j].category);
							countAlert.push(result[i].subList1[j].categoryCount);
							var uniqCnt = {y:parseInt(result[i].count)-parseInt(result[i].subList1[j].categoryCount),color:"#D3D3D3"};
							count.push(uniqCnt);
						}
							if(categoryName.length !=0 && count.length !=0){
								$(function () {
									$('#distwisegraph'+i+'').highcharts({
										colors: ['#A185BF','#0166FF','#32CCFE','#019966','#FF6600','#CC0001'],     
										chart: {
											type: 'column'
										},
										title: {
											text: ''
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
													enabled: true,      
													formatter: function() {
														if (this.y === 0) {
															return null;
														} else {
															//return Highcharts.numberFormat(this.y,0);
														}
													}
												}  
											}
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
		str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;'+result[0].subList.length+'</h5>';
		str+='<ul class="list-inline assignedCandidatesUl">';
		for(var i in result)
		{
			for(var j in result[i].subList)
			{
				str+='<li>';
					str+='<p style="color:#000"><b>'+result[i].subList[j].name+'</b></p>';
					if(result[i].subList[j].committeePosition == null || result[i].subList[j].committeePosition.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].committeePosition+'</i></p>';
					}
					if(result[i].subList[j].mobileNo == null || result[i].subList[j].mobileNo.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].mobileNo+'</i></p>';
					}
					if(result[i].subList[j].locationVO.districtName == null || result[i].subList[j].locationVO.districtName.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].locationVO.districtName+'</i></p>';
					}  
				str+='</li>';
			}
		}
		str+='</ul>';
		
		$("#alertAssignedCandidates").html(str);
	}else{
		str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>';  
		$("#alertAssignedCandidates").html(str);                    
	}
	
	$(".assignedCandidatesUl").slick({
		 slide: 'li',
		 slidesToShow: 4,
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
function buildAlertStatusCommentsTrackingDetails(result,alertStatus)
{
	var docName = '';
	var extName = [];
	$("#alertStatusDiv").html("<h4 class='text-muted headingColorStyling' style='font-size:15px;'>ALERT STATUS</h4>");          
	if(result != null && result.length > 0){  
		var length = result.length;
		length = length - 1;
		var str='';  
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<ul class="nav nav-tabs alertCommentUl" role="tablist">';  
			for(var i in result)
			{
				
				if(length != i){
					str+='<li class="m_top10" role="presentation"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab"><span>'+result[i].status+'</span><span class="glyphicon glyphicon-ok  pull-right" style="font-size: 22px;color: #777 !important;"></span><br/><span class="color_FF">'+result[i].sublist2[0].date+'</span></a></li>';
				}else{
					str+='<li role="presentation" class="active m_top10"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab"><span>'+result[i].status+'</span><span class="glyphicon glyphicon-hourglass pull-right" style="font-size: 22px;color: #777 !important;margin-left: 15px;"></span><br/><span class="color_FF">'+result[i].sublist2[0].date+'<span></a></li>';
				}        
				
			}
			str+='</ul>';
			str+='<div class="tab-content alertComment">';
				for(var i in result)
				{
					if(length != i)
					{
						str+='<div role="tabpanel" class="tab-pane " id="commentStatus'+i+'">';
					}else{
						str+='<div role="tabpanel" class="tab-pane active" id="commentStatus'+i+'">';
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
										str+='<div class="arrow_box_left" style="background: #f5f3f8 none repeat scroll 0 0 !important;">';
										for(var k in result[i].sublist2[j].sublist)
										{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE&nbsp;&nbsp;:&nbsp;</span>';
												for(var l in result[i].sublist2[j].sublist[k])
												{
													str+='&nbsp;&nbsp;<span class="glyphicon glyphicon-user"></span> <span>'+result[i].sublist2[j].sublist[k][l].cadreName+'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 18px;">|</span>';
												}   
												str+='&nbsp;&nbsp;&nbsp;&nbsp; <small style="font-size:11px">'+result[i].sublist2[j].sublist[k][0].timeString+'</small>';
												str+='</p>';  
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p class="m_top10">'+result[i].sublist2[j].sublist[k][0].comment+'</p>';
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
												str+='<hr style="margin-top:20px;border-color:#a792d2 -moz-use-text-color -moz-use-text-color;"/>';
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
	}else{
		var str = '';
		var statusArr = {"1":"Pending","2":"Notified","3":"Action In Progess","4":"Completed","5":"Unable To Resolve","6":"Action Not Required","7":"Duplicate"};            
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<ul class="nav nav-tabs alertCommentUl" role="tablist">';
		for(var i = 1 ; i <= 7 ; i++){
			if(alertStatus == statusArr[i]){
				str+='<li class="m_top10" role="presentation" style="pointer-events: none;"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab">'+statusArr[i]+'<span class="glyphicon glyphicon-ok"></span><br/></a></li>';
			}else{
				str+='<li class="m_top10" role="presentation" style="pointer-events: none;"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab">'+statusArr[i]+'<br/></a></li>';
			}
		}
		str+='</ul>';       
		str+='<div class="tab-content alertComment">';    
		$("#alertCommentsDiv").html(str);       
	}//glyphicon glyphicon-ok
	//alertStatus
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
	 	
       	var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
	 	var districtId = 0;
		if($("#dstrctSlctBxId").is(":visible")){
			districtId = $("#dstrctSlctBxId").val();
		}
		
		var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,        
			toDate : 			customEndDateAlert,             
			scopeIdsArr : 		scopeIdsArr,              
			activityMemberId : 	globalActivityMemberId,
			alertTypeId : 		alertId,
		    editionTypeId : 	editionId,
            districtId	  :     districtId	
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
					  str+='<div class="col-md-4 col-xs-12 col-sm-12">';
						
						str+='<h4 class="alertAssignCls text-capital" attr_type='+result[i].name+' style="text-align:center;cursor:pointer;color:rgb(51, 122, 183)">'+result[i].name+" - "+result[i].totalAlertCnt+'</h4>';
						str+='<div id="groupAssign'+i+'" style="height:300px;"></div>'; 
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
				}else if(selectionType == "VillageWard"){
					scopeIdsArr.push(7);  
					scopeIdsArr.push(9);	
					scopeIdsArr.push(6);	
				}else if(selectionType == "MuncipalityGMC"){
				   scopeIdsArr.push(8);
				}/* else if(selectionType == "National"){
				   scopeIdsArr.push(10);  	
			    }else if(selectionType == "International"){
				   scopeIdsArr.push(11);  
				}else if(selectionType == "State"){
				   scopeIdsArr.push(1);	
				}else if(selectionType == "Parliament"){
				   scopeIdsArr.push(4);	
			    } */
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
			  commitLvlIdArr.push(12);    
			  getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,groupAssignType,0,"alertDetailsDivId",commitLvlIdArr,"");  
		  }else if(groupAssignType == "Program Committee"){
			  getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(scopeIdsArr,groupAssignType,"alertDetailsDivId")
		  }else if(groupAssignType == "Others"){
			  getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(scopeIdsArr,groupAssignType,"alertDetailsDivId");
		  }
	});
	
$(document).on("click",".alertModalCloseCls",function(){
	 $(".commitLvlCls").prop('checked',false);
	 $("#commitLvlId1").prop('checked',true);
});	
function getTotalAlertGroupByPubRepThenStatus(scopeIdsArr,groupAssignType,publicRepresentativeTypeId,divId,commitLvlIdArr,level){
	$("#commitLvlId").hide();  
	if(groupAssignType == "Party Committee"){
		$("#commitLvlId").show();
	}else{
		$("#commitLvlId").hide();        
	}
  $("#alertModalHeadingId").html(groupAssignType+" Alert Details");
  $("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
  $("#alertModalId").modal("show");
	
	    var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var districtId = 0;
		if($("#dstrctSlctBxId").is(":visible")){
			districtId = $("#dstrctSlctBxId").val();
		}
	  var jsObj = { 
		stateId : globalStateId,             
		fromDate : customStartDateAlert,      
		toDate : customEndDateAlert,  
		scopeIdsArr : scopeIdsArr,                       
		activityMemberId : globalActivityMemberId,
		publicRepresentativeTypeId :publicRepresentativeTypeId,
		groupAssignType : groupAssignType,
		commitLvlIdArr : commitLvlIdArr,
		alertTypeId : alertId,
		editionTypeId : editionId,
		districtId :districtId
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
			str+='<thead class="text-capitalize">';
				if(groupAssignType == "Party Committee" && level == "bellow"){
					str+='<th>Designation</th>';       
				}else if(groupAssignType == "Public Representative" && level == "bellow"){
					str+='<th>Candidate Name</th>';            
				}else{
					str+='<th>'+groupAssignType+'</th>';  
				}  
				str+='<th>Total Alerts</th>';
				if(result[0].subList1 != null && result[0].subList1.length > 0){
					for(var i in result[0].subList1){
						if(result[0].subList1[i].categoryId == 5)
							continue;
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
					str+='<td style="cursor:pointer;" class="pubRepDtlsCls" attr_cadre_id="'+result[i].statusId+'" attr_pub_rep_type_id="'+publicRepresentativeTypeId+'" attr_status_id="0" attr_alert_count="'+result[i].count+'">'+result[i].count+'</td>';
				}else{
					str+='<td>'+result[i].count+'</td>';
				}  
					   
				if(result[i].subList1 != null && result[i].subList1.length > 0){
					for(var j in result[i].subList1){
							if(result[i].subList1[j].categoryId == 5)
							            continue;
						if(result[i].subList1[j].categoryCount == 0){
							str+='<td>'+result[i].subList1[j].categoryCount+'</td>';
						}else{
							if(groupAssignType == "Public Representative" && level == "bellow"){
								str+='<td style="cursor:pointer;" class="pubRepDtlsCls" attr_cadre_id="'+result[i].statusId+'" attr_pub_rep_type_id="'+publicRepresentativeTypeId+'" attr_status_id="'+result[i].subList1[j].categoryId+'" attr_alert_count="'+result[i].subList1[j].categoryCount+'">'+result[i].subList1[j].categoryCount+'</td>';
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
					str+='<td id="memberTblId_'+result[i].statusId+'" colspan="8">';     
				}else{
					str+='<tr class="subElement" style="display: none">';
					str+='<td id="memberTblId'+result[i].statusId+'" colspan="8">';     
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
	  $("#alertModalHeadingId").html(groupAssignType+" Alert Details");
	  $("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  $("#alertModalId").modal("show");
	  
		
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var districtId = 0;
		if($("#dstrctSlctBxId").is(":visible")){
			districtId = $("#dstrctSlctBxId").val();
		}
		
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,             
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
            resultType:groupAssignType,
			alertTypeId : alertId,
		    editionTypeId : editionId,
            districtId : districtId			
		}                  
		$.ajax({
			type : 'POST',      
			url : 'getOtherAndPrgrmCmmtteeTypeAlertCndtDtlsAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){       
			if(result != null && result.length > 0){
				buildProgramCommiteeAndOtherMemberDtls(result,divId,groupAssignType);
			}else{
			  $("#"+divId).html("NO DATA AVAILABLE.");	
			}
		});  
	}
function buildProgramCommiteeAndOtherMemberDtls(result,divId,groupAssignType){
		var str = '';
		str+='<div class="table-responsive">';
		 str+='<table class="table table-bordered tablePopup">';
			 str+='<thead>';
			   str+='<th>Candidate Name</th>';
			   str+='<th>Total Alerts</th>';
			   if(result[0].subList1 != null && result[0].subList1.length > 0){
				   for(var i in result[0].subList1){
					   if(result[0].subList1[i].statusTypeId==5)
						   continue;
					   str+='<th>'+result[0].subList1[i].statusType+'</th>';
				   }  
			   }
			 str+='</thead>';
			 str+='<tbody>';
			 for(var i in result){
				   str+='<tr>';
				   str+='<td>'+result[i].name+'</td>';
				   if(result[i].totalAlertCnt > 0){
					str+='<td class="prgrmCmmttAndOthrCls" attr_selected_type=\''+groupAssignType+'\' attr_alert_count="'+result[i].totalAlertCnt+'" attr_cadre_id="'+result[i].id+'" attr_status_id="0" style="cursor:pointer;">'+result[i].totalAlertCnt+'</td>';  
				   }else{
					str+='<td>'+result[i].totalAlertCnt+'</td>';   
				   }
					 if(result[i].subList1 != null && result[i].subList1.length > 0){
						 for(var j in result[i].subList1){
							  if(result[i].subList1[j].statusTypeId==5)
						             continue;
							    if(result[i].subList1[j].statusCnt > 0){
								str+='<td class="prgrmCmmttAndOthrCls" attr_selected_type=\''+groupAssignType+'\' attr_alert_count="'+result[i].subList1[j].statusCnt+'" attr_cadre_id="'+result[i].id+'" attr_status_id="'+result[i].subList1[j].statusTypeId+'" style="cursor:pointer;">'+result[i].subList1[j].statusCnt+'</td>';		
								}else{
								str+='<td>'+result[i].subList1[j].statusCnt+'</td>';	
								}
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
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
						scopeIdsArr.push(6);	
					}else if(selectionType == "MuncipalityGMC"){
					   scopeIdsArr.push(8);
					}/* else if(selectionType == "National"){
				        scopeIdsArr.push(10);  	
			        }else if(selectionType == "International"){
				       scopeIdsArr.push(11);  
				    }else if(selectionType == "State"){
				       scopeIdsArr.push(1);	
				    }else if(selectionType == "Parliament"){
				       scopeIdsArr.push(4);	
			      } */
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
				commitLvlIdArr.push(12);
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
			}else if(commitLvlVal == "Central")  {
			  commitLvlIdArr.push(12);	
			}
			if(selectTypeId == "Party Committee"){    
				getTotalAlertGroupByPubRepThenStatusBellow(commitLvlIdArr,designationId,divId,selectTypeId);
				$("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');  
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
				commitLvlIdArr.push(12);
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
			}else if(commitLvlVal == "Central"){
			  commitLvlIdArr.push(12);	
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
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
						scopeIdsArr.push(6);	
					}else if(selectionType == "MuncipalityGMC"){
					   scopeIdsArr.push(8);
					}/* else if(selectionType == "National"){
				        scopeIdsArr.push(10);  	
			        }else if(selectionType == "International"){
				       scopeIdsArr.push(11);  
				    }else if(selectionType == "State"){
				       scopeIdsArr.push(1);	
				    }else if(selectionType == "Parliament"){
				      scopeIdsArr.push(4);	
			      } */
				}
			});  
			getMemForPartyCommitDesg(commitTypeId,designationId,commitLvlIdArr,scopeIdsArr,selectionType,divId);
			$("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			$(".expandIcon2").addClass("glyphicon-plus").removeClass("glyphicon-minus");   
			$(this).removeClass("glyphicon-plus").addClass("glyphicon-minus");
			$(".subElement_").hide();    
			$(this).closest("tr").next("tr.subElement_").show();    
		}             
	});
	function getMemForPartyCommitDesg(commitTypeId,designationId,commitLvlIdArr,scopeIdsArr,selectionType,divId){
		
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var districtId = 0;
		if($("#dstrctSlctBxId").is(":visible")){
			districtId = $("#dstrctSlctBxId").val();
		}
		var jsObj = { 
			stateId : globalStateId,               
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
            commitTypeId : commitTypeId,
			designationId : designationId,
			commitLvlIdArr : commitLvlIdArr,
			alertTypeId : alertId,
		    editionTypeId : editionId,
			districtId : districtId
	    			
		}                  
		$.ajax({
			type : 'POST',      
			url : 'getMemForPartyCommitDesgAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){       
			if(result != null && result.length > 0){       
				buildMemForPartyCommitDesg(result,selectionType,commitTypeId,designationId,divId);
			}else{
			  $("#"+divId).html("NO DATA AVAILABLE.");  	
			}
		});  
	}
	function buildMemForPartyCommitDesg(result,selectionType,commitTypeId,designationId,divId){
		var str = '';
		str+='<div class="table-responsive">';
		str+='<table class="table table-bordered tablePopup">';
			str+='<thead>';
				str+='<th>Candidate Name</th>';        
				str+='<th>Total Alerts</th>';
				if(result[0].subList1 != null && result[0].subList1.length > 0){
					for(var i in result[0].subList1){
						if(result[0].subList1[i].categoryId == 5)
							continue;
						str+='<th>'+result[0].subList1[i].category+'</th>';
					}  
				}
				str+='</thead>';
				str+='<tbody>';
			for(var i in result){
				str+='<tr>';
				
				str+='<td>'+result[i].status+'</td>';
				if(result[i].count != 0){
					str+='<td style="cursor:pointer;" class="commettDtlsCls" attr_commit_id="'+commitTypeId+'" attr_designation_id="'+designationId+'" attr_cadre_id="'+result[i].statusId+'" attr_status_id="0" attr_alert_count="'+result[i].count+'">'+result[i].count+'</td>';
				}else{
					str+='<td>'+result[i].count+'</td>';
				}  
					   
				if(result[i].subList1 != null && result[i].subList1.length > 0){
					for(var j in result[i].subList1){
						if(result[i].subList1[j].categoryId == 5)
							continue;
						if(result[i].subList1[j].categoryCount == 0){
							str+='<td>'+result[i].subList1[j].categoryCount+'</td>';
						}else{
							str+='<td style="cursor:pointer;" class="commettDtlsCls" attr_cadre_id="'+result[i].statusId+'" attr_commit_id="'+commitTypeId+'" attr_designation_id="'+designationId+'" attr_status_id="'+result[i].subList1[j].categoryId+'" attr_alert_count="'+result[i].subList1[j].categoryCount+'">'+result[i].subList1[j].categoryCount+'</td>';
						}        	
					}
				}      
				str+='</tr>';
			}
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		$("#"+divId).html(str);  
	}
	$(document).on("click",".commettDtlsCls",function(){
		$("#tourDocumentId").find(".close").addClass("modalClose");
		$("#tourDocumentId").find(".modal-footer .btn").addClass("modalClose");
		
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");  
		var alertCount = $(this).attr("attr_alert_count");
		$("#alertCntTitId").html("TOTAL ALERTS-"+alertCount);
		var cadreId = $(this).attr("attr_cadre_id");
		var designationId = $(this).attr("attr_designation_id");
		var commitTypeId = $(this).attr("attr_commit_id");
		var statusId = $(this).attr("attr_status_id");
		var commitLvlIdArr = [];
		var commitLvlVal = $("input:radio[name=commitLvlName]:checked").val();
		if(commitLvlVal == "All"){
			commitLvlIdArr.push(10);
			commitLvlIdArr.push(11);
			commitLvlIdArr.push(5);
			commitLvlIdArr.push(7);
			commitLvlIdArr.push(6);
			commitLvlIdArr.push(8);
			commitLvlIdArr.push(12);
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
		}else if(commitLvlVal == "Central"){
			commitLvlIdArr.push(12);	
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
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
						scopeIdsArr.push(6);	
					}else if(selectionType == "MuncipalityGMC"){
					   scopeIdsArr.push(8);
					}/* else if(selectionType == "National"){
				    scopeIdsArr.push(10);  	
			    }else if(selectionType == "International"){
				    scopeIdsArr.push(11);  
				}else if(selectionType == "State"){
				    scopeIdsArr.push(1);	
				}else if(selectionType == "Parliament"){
				    scopeIdsArr.push(4);	
			    } */
			}
		});
		getAlertDtlsAssignedByPartyCommite(scopeIdsArr,commitTypeId,designationId,commitLvlIdArr,cadreId,statusId);  
		
	});
	function getAlertDtlsAssignedByPartyCommite(scopeIdsArr,commitTypeId,designationId,commitLvlIdArr,cadreId,statusId){
		
	   var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var districtId = 0;
		if($("#dstrctSlctBxId").is(":visible")){
			districtId = $("#dstrctSlctBxId").val();
		}
		var jsObj = { 
			stateId : globalStateId,               
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
            commitTypeId : commitTypeId,
			designationId : designationId,
			commitLvlIdArr : commitLvlIdArr,
			cadreId : cadreId,
			statusId : statusId,
			alertTypeId : alertId,
		    editionTypeId : editionId,
            districtId : districtId			
		}                  
		$.ajax({
			type : 'POST',      
			url : 'getAlertDtlsAssignedByPartyCommiteAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}           
		}).done(function(result){       
			if(result != null && result.length > 0){       
				buildAlertDtls(result);      
			}else{  
			  	
			}
		});  
	}
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
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
						scopeIdsArr.push(6);	
					}else if(selectionType == "MuncipalityGMC"){
					   scopeIdsArr.push(8);
					}/* else if(selectionType == "National"){
				    scopeIdsArr.push(10);  	
				}else if(selectionType == "International"){
				    scopeIdsArr.push(11);  
				}else if(selectionType == "State"){
				    scopeIdsArr.push(1);	
				}else if(selectionType == "Parliament"){
				    scopeIdsArr.push(4);	
				} */
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
			commitLvlIdArr.push(12);
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
		}else if(commitLvlVal == "Central"){
			commitLvlIdArr.push(12);    
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
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
						scopeIdsArr.push(6);	
					}else if(selectionType == "MuncipalityGMC"){
					   scopeIdsArr.push(8);
					}/* else if(selectionType == "National"){
				    scopeIdsArr.push(10);  	
				}else if(selectionType == "International"){
				    scopeIdsArr.push(11);  
				}else if(selectionType == "State"){
				    scopeIdsArr.push(1);	
				}else if(selectionType == "Parliament"){
				    scopeIdsArr.push(4);	
				} */
			  }
		   });
		
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var districtId = 0;
		if($("#dstrctSlctBxId").is(":visible")){
			districtId = $("#dstrctSlctBxId").val();
		}
		
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                  
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
			commitLvlIdArr : commitLvlIdArr,
			designationId : commitTypeId,
			groupAssignType : selectTypeId,      
			position : "bellow",
		    alertTypeId : alertId,
		    editionTypeId : editionId,
			districtId : districtId
			
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
		$("#tourDocumentId").find(".close").addClass("modalClose");
		$("#tourDocumentId").find(".modal-footer .btn").addClass("modalClose");
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");
		var publicRepresentativeTypeId = $(this).attr("attr_pub_rep_type_id");
		var cadreId = $(this).attr("attr_cadre_id");
		var statusId = $(this).attr("attr_status_id");
		var alertCount = $(this).attr("attr_alert_count");
		$("#alertCntTitId").html("TOTAL ALERTS-"+alertCount);  
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
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
						scopeIdsArr.push(6);	
					}else if(selectionType == "MuncipalityGMC"){
					   scopeIdsArr.push(8);
					}/* else if(selectionType == "National"){
				    scopeIdsArr.push(10);  	
				}else if(selectionType == "International"){
				    scopeIdsArr.push(11);  
				}else if(selectionType == "State"){
				    scopeIdsArr.push(1);	
				}else if(selectionType == "Parliament"){
				    scopeIdsArr.push(4);	
				} */
			}
		});
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
	    var districtId = 0;
		if($("#dstrctSlctBxId").is(":visible")){
			districtId = $("#dstrctSlctBxId").val();
		}
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                  
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
			publicRepresentativeTypeId : publicRepresentativeTypeId,
			cadreId : cadreId,
			statusId : statusId,
			alertTypeId : alertId,
		    editionTypeId : editionId,
			districtId : districtId
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
	 function getStateImpactLevelAlertDtlsCnt(location){
		 $(".hideStateLevelAlertCls").show();
		 $("#stateWiseHeadingId").html(' ');
		 $("#categoryWiseHeadingId").html(' ');
		 $("#statusWiseHeadingId").html(' ');
		$("#processingImgDivId").show();
		$("#processingImgDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#stateWiseAlertDtlsDiv").html(' ');
		$("#categoryWiseAlertDiv").html(' ');
		$("#statusWiseAlertDiv").html(' ');
		var scopeIdsArr = [];
            scopeIdsArr.push(1);
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		if(location == "date"){
			alertId = 0;
			editionId = 0;
		}
		var jsObj={  
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate:			customStartDateAlert,        
			toDate :			customEndDateAlert,
			scopeIdsArr:		scopeIdsArr,
			alertIds : 			alertId,
			editionIds : 		editionId
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
			if(obj1.name == 'Andhra Pradesh')
			{
				obj1.name = 'A.P'
			}else if(obj1.name == 'Telangana')
			{
				obj1.name = 'TS'
			}
			totalAlertCnt = totalAlertCnt+parseInt(result[i].alertCount);
			stateWiseAlertCnt.push(obj1);
		}
		var heading = "OVERVIEW-"+(totalAlertCnt);
		$("#stateWiseHeadingId").html(heading);
		$("#stateWiseHeadingId").attr("attr_alert_count",totalAlertCnt);
		$(function () {
			 $("#stateWiseAlertDtlsDiv").highcharts({  
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
						rotation: 0,
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
				tooltip: {
					headerFormat: '<span style="font-size:11px">No Of<br/> Alerts</span><br>',
					pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.extra}</b>'
				},
				plotOptions: {
					column: {
						//stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<b>' + this.y + '</b>';      
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
					name: 'No Of Alerts',
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
					var heading = "CATEGORY WISE - "+totalAlertCnt;
					$("#categoryWiseHeadingId").html(heading);
					$("#categoryWiseHeadingId").attr("attr_alert_count",totalAlertCnt);
					$(function () {
							$('#categoryWiseAlertDiv').highcharts({
								colors: ['#808000','#00FFFF','#FF00FF'],     
								chart: {
									type: 'column'
								},
								title: {
									text: ''
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
									headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
									pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>'
									
								},
								legend: {
														
										enabled: false,				
														
									},				
								plotOptions: {
									column: {        
										dataLabels:{
											enabled: true,
											formatter: function() {
												if (this.y === 0) {
													return null;
												} else {
													return '<b>' + this.y + '</b>';
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
						if(result[i].name == "Unable to Resolve"){
							continue;
						}
								 var uniqCnt = {};
									statusNameArr.push(result[i].name);
									alertCnt.push(result[i].alertCount);
									var uniqCnt = {y:parseInt(totalAlertCnt)-parseInt(result[i].alertCount),color:"#D3D3D3"};
									count.push(uniqCnt);
					}
				    var heading = "STATUS WISE - "+totalAlertCnt;
					$("#statusWiseHeadingId").html(heading);
					$("#statusWiseHeadingId").attr("attr_alert_count",totalAlertCnt);
			 $(function () {
					$('#statusWiseAlertDiv').highcharts({
						colors: ['#A185BF','#0166FF','#32CCFE','#019966','#FF6600','#CC0001'],     
						chart: {
							type: 'column'
						},
						title: {
							text: ''
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
		
	$(document).on("click",".prgrmCmmttAndOthrCls",function(){
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");
		var totalAlertCnt = $(this).attr("attr_alert_count");
		$("#alertCntTitId").html("TOTAL ALERTS - "+totalAlertCnt);
		var cadreId = $(this).attr("attr_cadre_id");
		var statusId = $(this).attr("attr_status_id");
		var selectType = $(this).attr("attr_selected_type");
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
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
						scopeIdsArr.push(6);	
					}else if(selectionType == "MuncipalityGMC"){
					   scopeIdsArr.push(8);
					}/* else if(selectionType == "National"){
				    scopeIdsArr.push(10);  	
				}else if(selectionType == "International"){
				    scopeIdsArr.push(11);  
				}else if(selectionType == "State"){
				    scopeIdsArr.push(1);	
				}else if(selectionType == "Parliament"){
				    scopeIdsArr.push(4);	
				} */
			}
		});
		
		  var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var districtId = 0;
		if($("#dstrctSlctBxId").is(":visible")){
			districtId = $("#dstrctSlctBxId").val();
		}
		
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                  
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
			cadreId : cadreId,
			statusId : statusId,
			resultType : selectType,
			alertTypeId : alertId,
		    editionTypeId : editionId,
			districtId : districtId
		}                  
		$.ajax({
			type : 'POST',                
			url : 'getAlertDetailsTdpCadreWiseAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){    
            if(result != null && result.length > 0){
				buildAlertDtls(result);    
			}
		});  
	}); 	
	
    $(document).on("click",".districtcls",function(){
		var districtId = $(this).attr("attr_district_id");
		var totalAlertCnt = $(this).attr("attr_alert_count");
		var districtIdArr=[districtId];
		buildDistrictWiseAlert(districtIdArr,totalAlertCnt,0,0,"district","");
	}); 	
	function buildDistrictWiseAlert(districtIdArr,totalAlertCnt,constituencyId,alertStatusId,locationLevel,locationType){
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');   
		$("#alertCntTitId").html("TOTAL ALERTS - "+totalAlertCnt);        
		$("#tourDocumentId").modal("show");
		var dates=$("#dateRangeIdForAlert").val();
		var scopeIdsArr = [];	
         if(locationType != null && locationType=="Constituency"){
			$(".alertConsSettingsUl li").each(function() {
			  if($(this).find("input").is(":checked")){
				 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
				 if(selectionType == "Constituency"){
					scopeIdsArr.push(3);
				}else if(selectionType == "mandal"){
					scopeIdsArr.push(5);  
				}else if(selectionType == "VillageWard"){
					scopeIdsArr.push(7);  
					scopeIdsArr.push(9);	
					scopeIdsArr.push(6);	
				}
			  }
		    }); 
		 }else{
			$(".alertSettingsUl li").each(function() {
				if($(this).find("input").is(":checked")){
					var selectionType = $(this).find("input").attr("attr_scope_type").trim();
					  if(selectionType == "District"){
						scopeIdsArr.push(2);
						}else if(selectionType == "Constituency"){
							scopeIdsArr.push(3);
						}else if(selectionType == "mandalMuncipality"){
							scopeIdsArr.push(5);  
						}else if(selectionType == "VillageWard"){
							scopeIdsArr.push(7);  
							scopeIdsArr.push(9);	
							scopeIdsArr.push(6);	
						}else if(selectionType == "MuncipalityGMC"){
						   scopeIdsArr.push(8);
						}/* else if(selectionType == "National"){
						scopeIdsArr.push(10);  	
					}else if(selectionType == "International"){
						scopeIdsArr.push(11);  
					}else if(selectionType == "State"){
						scopeIdsArr.push(1);	
					}else if(selectionType == "Parliament"){
						scopeIdsArr.push(4);	
					} */
				}	 
		  });
		 }	
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionIds = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionIds == undefined){
			editionIds = 0;
		}
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                  
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
			districtIdArr : districtIdArr,
			catId : 0,
			alertTypeId : alertId,
			editionId : editionIds,
			constituencyId:constituencyId,
			alertStatusId : alertStatusId,
			locationLevel : locationLevel
		
		}                  
		$.ajax({
			type : 'POST',                
			url : 'getDistrictAndStateImpactLevelWiseAlertDtlsAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){    
            if(result != null && result.length > 0){
				buildAlertDtls(result);    
			}else{
			$("#tourDocumentBodyId").html("NO DATA AVAILABLE.");	
			}
		}); 
	}
	
	$(document).on("click",".stateImpactScopeCls",function(){
		var impactScopeId = $(this).attr("attr_impact_level");
		var totalAlertCnt = $(this).attr("attr_alert_count");
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");
		$("#alertCntTitId").html("TOTAL ALERTS - "+totalAlertCnt);
		var dates=$("#dateRangeIdForAlert").val();
		var scopeIdsArr = [];		
		scopeIdsArr.push(1);
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionIds = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionIds == undefined){
			editionIds = 0;
		}
		var districtIdArr =[];
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                  
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : globalActivityMemberId,
			districtIdArr : districtIdArr,
			catId : 0,
			alertTypeId : alertId,     
			editionId : editionIds,
			constituencyId : 0,
            alertStatusId :0,
            locationLevel : "district"			
		}                  
		$.ajax({
			type : 'POST',                
			url : 'getDistrictAndStateImpactLevelWiseAlertDtlsAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){    
            if(result != null && result.length > 0){
				buildAlertDtls(result);    
			}else{
			 $("#tourDocumentBodyId").html("NO DATA AVAILABLE.");
			}
		});  
	});
$(document).on("click",".articleImgDetailsCls",function(){
	var articleId= $(this).attr("attr_articleId");
	getTotalArticledetails(articleId);
});

function getTotalArticledetails(articleId){
	$("#myModalShowNew").modal('show');
	$("#myModalShowNewId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	var url = window.location.href;
	  var wurl = url.substr(0,(url.indexOf(".com")+4));
	  if(wurl.length == 3)
	  wurl = url.substr(0,(url.indexOf(".in")+3));
	  $.ajax({       
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
		  
		  //url: "http://mytdp.com/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""          
		  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""     
	}).then(function(results){
			var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","Muncipality/Corporation/GHMC/GVMC","Ward"];
				var result = results[0];
				var str = '';
					str+='<div class="modal-header">';
					str+='<h4 class="modal-title" id="myModalLabel">';
					str+='<button type="button" class="close topModalClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
						str+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result.articleTitle+'</p>';
					str+='</h4>';
					str+='</div>';
					str+='<div class="modal-body">';
					str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						str+='<p class="m_bottom0 text-italic font-16" id="mdlArtclDesc"><i>Edition Source :'+result.editionSource+' ['+result.articleInsertedTime+' ]</i></p>';
						str+='<img class="mainImage"  src="http://mytdp.com/NewsReaderImages/'+result.imageURL+'" style="display:block;margin:auto;border:1px solid #ddd;width:100%" alt="Img Title"/>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h4 class="panel-title text-success">Description</h4>';
					str+='<p class="m_0 f_14">'+result.description+'</p>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					if( result.subList != null && result.subList.length > 0){
						for(var i in result.subList){
							/* Candidate*/
							str+='<div class="row m_top10">';
							str+='<div class="col-md-6 col-xs-12 col-sm-12">';
							str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title">FROM WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* From Table*/
								if(result.subList[i].fromList != null && result.subList[i].fromList.length > 0){
									for( var j in result.subList[i].fromList){
										str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
										if( result.subList[i].fromList[j].organizationName != null && $.trim(result.subList[i].fromList[j].organizationName).length > 0 ){
											str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].fromList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].fromList[j].organizationName+'</td>';
										}
										str+='<td><img class="img-circle" src="images/'+result.subList[i].fromList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].fromList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										var candidataExist = false;
										if( result.subList[i].fromList[j].candidateName != null && $.trim(result.subList[i].fromList[j].candidateName).length > 0 ){
											candidataExist = true; 
											str+=''+result.subList[i].fromList[j].candidateName;
										}
										if( result.subList[i].fromList[j].designation != null && $.trim(result.subList[i].fromList[j].designation).length > 0 ){
											candidataExist = true; 
											str+=' ('+result.subList[i].fromList[j].designation + ")";
										}
										if(!candidataExist){
											str+=' - ';
										}
										str+='</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										if(result.subList[i].fromList[j].impactLevel != null && $.trim(result.subList[i].fromList[j].impactLevel).length > 0){
											str+='<p class="m_0">Impact Level : '+result.subList[i].fromList[j].impactLevel+'</p>';	
										}else{ 
											str+='<p class="m_0">Impact Level : - </p>';	
										}
										if(result.subList[i].fromList[j].categories != null && $.trim(result.subList[i].fromList[j].categories).length > 0){
											str+='<p class="m_0">Category : '+result.subList[i].fromList[j].categories+'</p>';	
										}else{ 
											str+='<p class="m_0">Category : - </p>';	
										}
										if(result.subList[i].fromList[j].newsActivity != null && $.trim(result.subList[i].fromList[j].newsActivity).length > 0){
											str+='<p class="m_0">News Activity : '+result.subList[i].fromList[j].newsActivity+' </p>';
										}else{ 
											str+='<p class="m_0">News Activity : - </p>';	
										}
										if(result.subList[i].fromList[j].newsType != null && $.trim(result.subList[i].fromList[j].newsType).length > 0){
											str+='<p class="m_0">News type : '+result.subList[i].fromList[j].newsType+' </p>';
										}else{ 
											str+='<p class="m_0">News type : - </p>';	
										}
										if( result.subList[i].fromList[j].newsType != null && result.subList[i].fromList[j].newsType == "Problems"){
											if(result.subList[i].fromList[j].newsRelated != null && $.trim(result.subList[i].fromList[j].newsRelated).length > 0){
												str+='<p class="m_0">News Related : '+result.subList[i].fromList[j].newsRelated+' </p>';
											}else{ 
												str+='<p class="m_0">News Related : - </p>';	
											}
											if(result.subList[i].fromList[j].priority != null && $.trim(result.subList[i].fromList[j].priority).length > 0){
												str+='<p class="m_0">Priority : '+result.subList[i].fromList[j].priority+' </p>';
											}else{ 
												str+='<p class="m_0">Priority : - </p>';	
											}
											if(result.subList[i].fromList[j].solution != null && $.trim(result.subList[i].fromList[j].solution).length > 0){
												str+='<p class="m_0">Solution : '+result.subList[i].fromList[j].solution+' </p>';
											}else{ 
												str+='<p class="m_0">Solution : - </p>';	
											}
										}
										str+='</td>';
										str+='</tr>';
										str+='</table>';
									}
								}
							str+='</div>';//panel-body
							str+='</div>';//panel
							str+='</div>';//colmd6
							str+='<div class="col-md-6 col-xs-12  col-sm-12">';
							str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title">TO WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* TO Table*/
								if(result.subList[i].toList != null && result.subList[i].toList.length > 0){
									for( var j in result.subList[i].toList){
										str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
										if( result.subList[i].toList[j].organizationName != null && $.trim(result.subList[i].toList[j].organizationName).length > 0 ){
											str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].toList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].toList[j].organizationName+'</td>';
										}else{
											str+='<td> - </td>';
										}
										str+='<td><img class="img-circle" src="images/'+result.subList[i].toList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].toList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										var candidataExist = false;
										if( result.subList[i].toList[j].candidateName != null && $.trim(result.subList[i].toList[j].candidateName).length > 0 ){
											candidataExist = true; 
											str+=''+result.subList[i].toList[j].candidateName;
																			}
																			if( result.subList[i].toList[j].designation != null && $.trim(result.subList[i].toList[j].designation).length > 0 ){
																				candidataExist = true; 
																				str+=' ('+result.subList[i].toList[j].designation + ")";
																			}
																			if(!candidataExist){
																				str+=' - ';
																			}
																		   str+='</td>';
																	str+='</tr>';
																	str+='<tr>';
																		str+='<td colspan="2">';
																		    
																			if(result.subList[i].toList[j].impactLevel != null && $.trim(result.subList[i].toList[j].impactLevel).length > 0){
																			  str+='<p class="m_0">Impact Level : '+result.subList[i].toList[j].impactLevel+'</p>';	
																			}else{ 
																			  str+='<p class="m_0">Impact Level : - </p>';	
																			}
																		
																		    if(result.subList[i].toList[j].categories != null && $.trim(result.subList[i].toList[j].categories).length > 0){
																			  str+='<p class="m_0">Category : '+result.subList[i].toList[j].categories+'</p>';	
																			}else{ 
																			  str+='<p class="m_0">Category : - </p>';	
																			}
																			if(result.subList[i].toList[j].newsActivity != null && $.trim(result.subList[i].toList[j].newsActivity).length > 0){
																			  str+='<p class="m_0">News Activity : '+result.subList[i].toList[j].newsActivity+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News Activity : - </p>';	
																			}
																			if(result.subList[i].toList[j].newsType != null && $.trim(result.subList[i].toList[j].newsType).length > 0){
																			  str+='<p class="m_0">News type : '+result.subList[i].toList[j].newsType+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News type : - </p>';	
																			}
																			if( result.subList[i].toList[j].newsType != null && result.subList[i].toList[j].newsType == "Problems"){
																				
																				if(result.subList[i].toList[j].newsRelated != null && $.trim(result.subList[i].toList[j].newsRelated).length > 0){
																				  str+='<p class="m_0">News Related : '+result.subList[i].toList[j].newsRelated+' </p>';
																				}else{ 
																				  str+='<p class="m_0">News Related : - </p>';	
																				}
																				if(result.subList[i].toList[j].priority != null && $.trim(result.subList[i].toList[j].priority).length > 0){
																				  str+='<p class="m_0">Priority : '+result.subList[i].toList[j].priority+' </p>';
																				}else{ 
																				  str+='<p class="m_0">Priority : - </p>';	
																				}
																				if(result.subList[i].toList[j].solution != null && $.trim(result.subList[i].toList[j].solution).length > 0){
																				  str+='<p class="m_0">Solution : '+result.subList[i].toList[j].solution+' </p>';
																				}else{ 
																				  str+='<p class="m_0">Solution : - </p>';	
																				}
																			}
																		str+='</td>';
																	str+='</tr>';
																str+='</table>';
															}
														}
														
													str+='</div>';//panelbody
												str+='</div>';//panel
											str+='</div>';//colmd6
											
										str+='</div>';//row
								  }
								}
								
								str+='</div>';//colmd12
							str+='</div>';//row
								/* Article Scope Location */
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-condensed">';
												str+='<tr>';
													str+='<td>Impact Scope : </td>';
													if(result.impactScopeId!=null){
														str+='<td>'+obj[result.impactScopeId]+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
												str+='<tr>';
													str+='<td>Location : </td>';
													if(result.scopeLocation!=null){
														str+='<td>'+result.scopeLocation+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
											str+='</table>';       
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							
							str+='<div class="row">';
							/*Lnking*/
							str+='</div>';  
					$("#myModalShowNewId").html(str);
		});    
} 	
	getAlertLastUpdatedTime();
	window.setInterval(function(){
		getAlertLastUpdatedTime(); 
	},10*60*1000);/*every 10 minutes .this method will update time  */
	function getAlertLastUpdatedTime(){
	 	$.ajax({
			type : 'POST',
			url : 'getAlertLastUpdatedTimeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify( )}
		}).done(function(result){
			if(result != null){
			 setAlertLastUpdatedTime(result)	
			}
		});
	}
	function setAlertLastUpdatedTime(lastUpdatedTime){
	 $("#lastAlertUpdatedTimeId").html(" Last Updated : "+lastUpdatedTime+"");
	}
	function getAlertOverviewDetails(){
		getDistrictListByStateId(globalActivityMemberId,globalUserTypeId);//getting district by access level
		$("#alertOverview,#alertOverviewDetails").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var dates=$("#dateRangeIdForAlert").val();
		
		var alertTypeStr = 0;          
		var alertEdition = 0;          
		var jsObj={
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate:			customStartDateAlert,        
			toDate :			customEndDateAlert,
			alertType : 		alertTypeStr,
			editionType:		alertEdition
		};
		$.ajax({
			type : 'GET',
			url : 'getAlertOverviewDetailsAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			if(result != null){
			  buildAlertOverviewDetailsAction(result);
			  buildAlertOverviewDetails(result,0,0)
			}else{
			  $("#alertOverview,#alertOverviewDetails").html("NO DATA AVAILABLE.");	
			}
      });	
	}
	function buildAlertOverviewDetailsAction(result)
	{
		var str='';
		var totalMainCount = result.totalPartyList[0].editionCnt + result.totalGovtList[0].editionCnt + result.totalOtherList[0].editionCnt
		var totalDistCount = result.totalPartyList[1].editionCnt + result.totalGovtList[1].editionCnt + result.totalOtherList[1].editionCnt
		if($(window).width() < 800)
		{
			str+='<div class="table-responsive">';
		}
		
		str+='<table class="table table-bordered bg_ED text-center">';
		
		str+='<tbody>';
			str+='<tr>';
			   if(result.overAllVO.totalAlertCnt > 0){
				str+='<td colspan="2" onclick ="getEditioDtls(0,0);arrowPositionMove(overAllArrowPostion);" id="overAllArrowPostion" class="alertsArrow alertOverViewDetailsCls" attr_alert_type_id="0" attr_edition_type_id="0"><div class="alertsArrow alertInnerArrow" ><h3 class="alertColorFont">'+result.overAllVO.totalAlertCnt+'&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" class="glyphicon glyphicon-info-sign alertDetailsCls" attr_alert_type_name="TOTAL ALERTS" attr_alert_count="'+result.overAllVO.totalAlertCnt+'" attr_alert_type="0" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details." aria-describedby="tooltip681435"></i></h3><p>TOTAL ALERTS</p></div></td>';
			   }else{
				str+='<td colspan="2"><div class="alertsArrow alertInnerArrow"><h3 class="alertColorFont">'+result.overAllVO.totalAlertCnt+'</h3><p>TOTAL ALERTS</p></div></td>';   
			   }
				
				if(!(result.overAllVO.partyAlertCnt == 0)){
				str+='<td colspan="2" onclick ="getEditioDtls(1,0);arrowPositionMove(overAllPartyArrowPostion);" id="overAllPartyArrowPostion" class="alertOverViewDetailsCls " attr_alert_type_id="1" attr_edition_type_id="0"><div class="alertInnerArrow " ><h3 class="alertColorFont">'+result.overAllVO.partyAlertCnt+'&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" class="glyphicon glyphicon-info-sign alertDetailsCls" attr_alert_type_name="PARTY ALERTS" attr_alert_count="'+result.overAllVO.partyAlertCnt+'" attr_alert_type="1" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details." aria-describedby="tooltip681435"></i></h3><p>PARTY</p></div></td>';
				}
				if(!(result.overAllVO.govtAlertCnt == 0)){
					str+='<td colspan="2" onclick ="getEditioDtls(2,0);arrowPositionMove(overAllgovtArrowPostion);" id="overAllgovtArrowPostion" class="alertOverViewDetailsCls " attr_alert_type_id="2" attr_edition_type_id="0"><div class="alertInnerArrow " ><h3 class="alertColorFont">'+result.overAllVO.govtAlertCnt+'&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" class="glyphicon glyphicon-info-sign alertDetailsCls" attr_alert_count="'+result.overAllVO.govtAlertCnt+'" attr_alert_type_name="GOVT ALERTS" attr_alert_type="2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details." aria-describedby="tooltip681435"></i></h3><p>GOVT</p></div></td>';
				}
				if(!(result.overAllVO.otherAlertCnt == 0)){
				str+='<td colspan="2" onclick ="getEditioDtls(3,0);arrowPositionMove(overAllotherdArrowPostion);" id="overAllotherdArrowPostion"  class="alertOverViewDetailsCls " 	attr_alert_type_id="3" attr_edition_type_id="0"><div class="alertInnerArrow " ><h3 class="alertColorFont">'+result.overAllVO.otherAlertCnt+'&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" attr_alert_type_name="OTHERS ALERTS" class="glyphicon glyphicon-info-sign alertDetailsCls" attr_alert_count="'+result.overAllVO.otherAlertCnt+'" attr_alert_type="3" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details." aria-describedby="tooltip681435"></i></h3><p>OTHERS</p></div></td>';
				}
			str+='</tr>';
			str+='<tr>';
			   if(totalMainCount > 0){
				str+='<td onclick ="arrowPositionMove1(overAllArrowPostionLow);" id="overAllArrowPostionLow"  class="alertOverViewDetailsLowCls" ><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted">Main<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="1" attr_category_id="0" attr_status_id="0" attr_count="'+totalMainCount+'" attr_alert_type_id="0" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i></p>';
				str+='<p style="font-size:13px;cursor:pointer;"  onclick ="getEditioDtls(0,1);" attr_alert_type_id="0" attr_edition_type_id="1"><span class="alertColorFont">'+totalMainCount+'</span></p></div></td>';
		       }else{
			    str+='<td  id="overAllArrowPostionLow" class="alertOverViewDetailsLowCls" ><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted">Main</p>';
				str+='<p style="font-size:13px;"><span class="alertColorFont">0</span></p></div></td>';
	  	       }
				
				if(totalDistCount > 0){
				str+='<td onclick ="arrowPositionMove1(overAllMainArrowPostionLow);" id="overAllMainArrowPostionLow" class="alertOverViewDetailsLowCls" ><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted">District<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="2" attr_category_id="0" attr_status_id="0" attr_count="'+totalDistCount+'" attr_alert_type_id="0" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i></p>';
				str+='<p style="font-size:13px;cursor:pointer;" onclick="getEditioDtls(0,2);" attr_alert_type_id="0" attr_edition_type_id="2"><span class="alertColorFont">'+totalDistCount+'</span></p></div></td>';
				}else{
				str+='<td class="alertOverViewDetailsLowCls" ><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted">District</p>';
				str+='<p style="font-size:13px;"><span class="alertColorFont">0</span></p></div></td>';
				}
				
				if(!(result.overAllVO.partyAlertCnt == 0)){
					
					str+='<td class="alertOverViewDetailsLowCls" onclick="arrowPositionMove1(overAlltotalpartyArrowPostion)" id="overAlltotalpartyArrowPostion" ><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted">'+result.totalPartyList[0].edition+''
					if(result.totalPartyList[0].editionCnt > 0){
					str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalPartyList[0].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalPartyList[0].editionCnt+'" attr_alert_type_id="'+result.totalPartyList[0].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';	
					}
					str+='</p>';
					if(result.totalPartyList[0].editionCnt == 0){
						str+='<p style="font-size:13px;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalPartyList[0].alertTypeId+','+result.totalPartyList[0].editionId+');" style="font-size:13px;cursor:pointer;" class="" attr_alert_type_id="'+result.totalPartyList[0].alertTypeId+'" attr_edition_type_id="'+result.totalPartyList[0].editionId+'"><span class="alertColorFont">'+result.totalPartyList[0].editionCnt+'</span></p></div></td>';
					}
					
					
					str+='<td class="alertOverViewDetailsLowCls" onclick="arrowPositionMove1(overAlltotalpartyEdArrowPostion)" id="overAlltotalpartyEdArrowPostion"><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted">'+result.totalPartyList[1].edition+''
					if(result.totalPartyList[1].editionCnt > 0){
					str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalPartyList[1].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalPartyList[1].editionCnt+'" attr_alert_type_id="'+result.totalPartyList[1].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';
					}
					str+='</p>';
					if(result.totalPartyList[1].editionCnt == 0){  
						str+='<p style="font-size:13px;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalPartyList[1].alertTypeId+','+result.totalPartyList[1].editionId+');" style="font-size:13px;cursor:pointer;" class="" attr_alert_type_id="'+result.totalPartyList[1].alertTypeId+'" attr_edition_type_id="'+result.totalPartyList[1].editionId+'"><span class="alertColorFont">'+result.totalPartyList[1].editionCnt+'</span></p></div></td>';
					}
					
				}
				if(!(result.overAllVO.govtAlertCnt == 0)){
					str+='<td class="alertOverViewDetailsLowCls" onclick="arrowPositionMove1(overAlltotalgovtEdArrowPostion)" id="overAlltotalgovtEdArrowPostion"><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted">'+result.totalGovtList[0].edition+''
					if(result.totalGovtList[0].editionCnt > 0){
					 str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalGovtList[0].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalGovtList[0].editionCnt+'" attr_alert_type_id="'+result.totalGovtList[0].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';
					}
					str+='</p>';    
					if(result.totalGovtList[0].editionCnt == 0){
						str+='<p style="font-size:13px;cursor:pointer;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalGovtList[0].alertTypeId+','+result.totalGovtList[0].editionId+');" style="font-size:13px;cursor:pointer;" class="" attr_alert_type_id="'+result.totalGovtList[0].alertTypeId+'" attr_edition_type_id="'+result.totalGovtList[0].editionId+'"><span class="alertColorFont">'+result.totalGovtList[0].editionCnt+'</span></p></div></td>';
					}
					
					str+='<td class="alertOverViewDetailsLowCls " onclick="arrowPositionMove1(overAlltotalgovtEd1ArrowPostion)" id="overAlltotalgovtEd1ArrowPostion"><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted">'+result.totalGovtList[1].edition+''
					if(result.totalGovtList[1].editionCnt > 0){
						str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalGovtList[1].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalGovtList[1].editionCnt+'" attr_alert_type_id="'+result.totalGovtList[1].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';
					}
					str+='</p>';
					if(result.totalGovtList[1].editionCnt == 0){
						str+='<p style="font-size:13px;cursor:pointer;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalGovtList[1].alertTypeId+','+result.totalGovtList[1].editionId+');" style="font-size:13px;cursor:pointer;" class="" attr_alert_type_id="'+result.totalGovtList[1].alertTypeId+'" attr_edition_type_id="'+result.totalGovtList[1].editionId+'"><span class="alertColorFont">'+result.totalGovtList[1].editionCnt+'</span></p></div></td>';
					}
					
				}
				if(!(result.overAllVO.otherAlertCnt == 0)){
					str+='<td class="alertOverViewDetailsLowCls" onclick="arrowPositionMove1(overAlltotalothersEd1ArrowPostion)" id="overAlltotalothersEd1ArrowPostion"><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted">'+result.totalOtherList[0].edition+''
					if(result.totalOtherList[0].editionCnt > 0){
					str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalOtherList[0].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalOtherList[0].editionCnt+'" attr_alert_type_id="'+result.totalOtherList[0].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';	
					}
					str+='</p>';
					if(result.totalOtherList[0].editionCnt == 0){
						str+='<p style="font-size:13px;cursor:pointer;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalOtherList[0].alertTypeId+','+result.totalOtherList[0].editionId+');" style="font-size:13px;cursor:pointer;"  attr_alert_type_id="'+result.totalOtherList[0].alertTypeId+'" attr_edition_type_id="'+result.totalOtherList[0].editionId+'"><span class="alertColorFont">'+result.totalOtherList[0].editionCnt+'</span></p></div></td>';
					}
					
					str+='<td class="alertOverViewDetailsLowCls1" onclick="arrowPositionMove1(overAlltotalothersEd2ArrowPostion);" id="overAlltotalothersEd2ArrowPostion"><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted">'+result.totalOtherList[1].edition+''
					if(result.totalOtherList[1].editionCnt > 0){
					str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalOtherList[1].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalOtherList[1].editionCnt+'" attr_alert_type_id="'+result.totalOtherList[1].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';	
					}
					str+='</p>';
					if(result.totalOtherList[1].editionCnt == 0){
						str+='<p style="font-size:13px;cursor:pointer;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalOtherList[1].alertTypeId+','+result.totalOtherList[1].editionId+');" style="font-size:13px;cursor:pointer;" attr_alert_type_id="'+result.totalOtherList[1].alertTypeId+'" attr_edition_type_id="'+result.totalOtherList[1].editionId+'"><span class="alertColorFont">'+result.totalOtherList[1].editionCnt+'</span></p></div></td>';
					}
				}
				
			str+='</tr>';
				str+='</tbody>';
		str+='</table>';
		if($(window).width() < 800)
		{
		str+='</div>';
		}
		
		$("#alertOverview").html(str);  
		$('[data-toggle="tooltip"]').tooltip();
	}
	
	
	function buildAlertOverviewDetails(result,alertEdition,alertTypeId)  
	{   
		var str='';
		str+='<div class="row m_top10">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div style="border-top:3px solid #D5D5D5;border-left:1px solid #D5D5D5;border-right:1px solid #D5D5D5;border-bottom:1px solid #D5D5D5;padding:10px;">';
					str+='<div class="row">';
						str+='<div class="col-md-12 col-xs-12 col-sm-12">';
							if(alertTypeId == 0){
								if(alertEdition == 1){
									str+='<i class="pull-right"> Showing : Total Main Edition Alerts</i>';
								}else if(alertEdition == 2){
									str+='<i class="pull-right"> Showing : Total District Edition Alerts</i>';
								}else if(alertEdition == 0){
									str+='<i class="pull-right"> Showing : Total Alerts</i>';
								}
							}else if(alertTypeId == 1){
								if(alertEdition == 1){
									str+='<i class="pull-right"> Showing : Party Main Edition Alerts</i>';
								}else if(alertEdition == 2){
									str+='<i class="pull-right"> Showing : Party District Edition Alerts</i>';
								}else if(alertEdition == 0){
									str+='<i class="pull-right"> Showing : Party Total Alerts</i>';
								}
							}else if(alertTypeId == 2){
								if(alertEdition == 1){
									str+='<i class="pull-right"> Showing : Govt Main Edition Alerts</i>';
								}else if(alertEdition == 2){
									str+='<i class="pull-right"> Showing : Govt District Edition Alerts</i>';
								}else if(alertEdition == 0){
									str+='<i class="pull-right"> Showing : Govt Total Alerts</i>';
								}
							}else if(alertTypeId == 3){
								if(alertEdition == 1){
									str+='<i class="pull-right"> Showing : Others Main Edition Alerts</i>';
								}else if(alertEdition == 2){
									str+='<i class="pull-right"> Showing : Others District Edition Alerts</i>';
								}else if(alertEdition == 0){
									str+='<i class="pull-right"> Showing : Others Total Alerts</i>';
								}
							}  
							
						str+='</div>';
						for(var i in result.statusList)     
						{
							if(result.statusList[i].statusType == "Unable to Resolve"){
								continue;           
							}
							str+='<div class="col-md-4 col-xs-12 col-sm-12 m_top10">';
								str+='<div class="bg_ED">';
								if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
									
										str+='<div class="table-responsive">';
									
								}else{
									if($(window).width() < 800)
									
									{
										str+='<div class="table-responsive">';
									}
								}
								
									str+='<table class="table table-bordered">';
										
										str+='<tr>';
											str+='<td colspan="2">';
												if(result.statusList[i].statusCnt == 0){  
													str+='<h4>'+result.statusList[i].statusCnt+'&nbsp;&nbsp;<small class="text-success">'+result.statusList[i].statusCntPer+'%</small></h4>';
												}else{
													str+='<h4 style="cursor:pointer;" class="alertDtlsCls" attr_edition_id="'+alertEdition+'" attr_category_id="0" attr_status_id="'+result.statusList[i].statusTypeId+'" attr_count="'+result.statusList[i].statusCnt+'" attr_alert_type_id="'+alertTypeId+'" ><span class="alertColorFont">'+result.statusList[i].statusCnt+'</span>&nbsp;&nbsp;<small class="text-success">'+result.statusList[i].statusCntPer+'%</small></h4>';
												}
												
												str+='<p>'+result.statusList[i].statusType+'&nbsp;&nbsp;</p>';
											str+='</td>';
										str+='</tr>';
									  
										if(alertEdition == 0){
											str+='<tr>';
												str+='<td>';
													str+='<p class="text-capitalize text-muted">'+result.statusList[i].editionList[0].edition+'</p>';
													if(result.statusList[i].editionList[0].editionCnt == 0){
														str+='<p class="text-capitalize text-muted">0</p>';
													}else{
														str+='<p class="text-capitalize text-muted alertDtlsCls"  style="cursor:pointer;" attr_category_id="0" attr_status_id="'+result.statusList[i].statusTypeId+'" attr_count="'+result.statusList[i].editionList[0].editionCnt+'" attr_alert_type_id="'+alertTypeId+'" attr_edition_id="'+result.statusList[i].editionList[0].editionId+'"><span class="alertColorFont">'+result.statusList[i].editionList[0].editionCnt+'</span></p>';
													}
													
												str+='</td>';
												str+='<td>';
													str+='<p class="text-capitalize text-muted">'+result.statusList[i].editionList[1].edition+'</p>';
													if(result.statusList[i].editionList[1].editionCnt == 0){
														str+='<p class="text-capitalize text-muted">0</p>';
													}else{
														str+='<p class="text-capitalize text-muted alertDtlsCls" style="cursor:pointer;" attr_category_id="0" attr_status_id="'+result.statusList[i].statusTypeId+'" attr_count="'+result.statusList[i].editionList[1].editionCnt+'" attr_alert_type_id="'+alertTypeId+'" attr_edition_id="'+result.statusList[i].editionList[1].editionId+'"><span class="alertColorFont">'+result.statusList[i].editionList[1].editionCnt+'</span></p>';
													}  
													
												str+='</td>'; 
											str+='</tr>';
										}
									str+='</table>';
									if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
										
											str+='</div>';
										
									}else{
										if($(window).width() < 800)
										{
											str+='</div>';
										}
									}
									
								str+='</div>';
							str+='</div>';
							/* for(var j in result.statusList[i].editionList)
							{
								
							} */
						}
						
					str+='</div>';
					
				 	/* Alert Action Wise Result Block */
				    if(result.actionTypeList != null && result.actionTypeList.length > 0){
							for(var k in result.actionTypeList){
								if(result.actionTypeList[k].alertCnt != null && result.actionTypeList[k].alertCnt > 0){
									str+="<div class='row'>";
										str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
										str+="<div class='pad_15 bg_ED'>";
											str+='<h4 class="panel-title text-capital alertActionCls" style="cursor:pointer;" attr_alert_type_id="'+alertTypeId+'" attr_edition_id="'+alertEdition+'"  attr_action_type_id='+result.actionTypeList[k].id+'  attr_status_id="0" attr_count="'+result.actionTypeList[k].alertCnt+'"><b>'+result.actionTypeList[k].name+' - <span class="alertColorFont">'+result.actionTypeList[k].alertCnt+'</span></b></h4>';
										str+='</div>';
										str+='</div>';
										if(result.actionTypeList[k].subList1 != null && result.actionTypeList[k].subList1.length > 0){
											for(var l in result.actionTypeList[k].subList1){
												if(l==0)
												{
													str+="<div class='col-md-6 col-xs-12 col-sm-6' style='padding-right:0px;padding-top:1px'>";
												}else{
													str+="<div class='col-md-6 col-xs-12 col-sm-6' style='padding-left:1px;padding-top:1px'>";
												}
												
													str+="<div class='pad_15 bg_ED'>";
												   if(result.actionTypeList[k].subList1[l].alertCnt > 0){
													var percent = parseFloat(((result.actionTypeList[k].subList1[l].alertCnt)*100)/result.actionTypeList[k].alertCnt).toFixed(2);
													str+="<p class='text-capitalize text-muted' style='cursor:pointer;'>"+result.actionTypeList[k].subList1[l].statusType+"</p><p> <span style='cursor:pointer;' class='alertColorFont alertActionCls' attr_alert_type_id="+alertTypeId+" attr_edition_id="+alertEdition+" attr_action_type_id="+result.actionTypeList[k].id+" attr_status_id="+result.actionTypeList[k].subList1[l].statusTypeId+" attr_count="+result.actionTypeList[k].subList1[l].alertCnt+">"+result.actionTypeList[k].subList1[l].alertCnt+"</span> <small class='text-success' style='margin-left: 15px;'>"+percent+"%</small></p>";   
												   }else{
													  str+="<p class='text-capitalize text-muted'>"+result.actionTypeList[k].subList1[l].statusType+"</p><p><span class=''>"+result.actionTypeList[k].subList1[l].alertCnt+"</span> <small class='text-success' style='margin-left: 15px;'>0%</small></p>"; 
												   }
													str+="</div>";
												str+="</div>";
											}
										}
								str+='</div>';	
								}
							}
					} 
				    
					for(var i in result.categoryList)
					{
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
								str+='<h4 class="panel-title text-capital alertDtlsCls" style="cursor:pointer;" attr_edition_id="'+alertEdition+'" attr_category_id="'+result.categoryList[i].statusTypeId+'" attr_status_id="0" attr_count="'+result.categoryList[i].statusCnt+'" attr_alert_type_id="'+alertTypeId+'">'+result.categoryList[i].statusType+' - <span class="alertColorFont">'+result.categoryList[i].statusCnt+'</span></h4>';
							str+='</div>';
							if(alertEdition == 0 && result.categoryList[i].statusTypeId == 2){//printmedia      
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									
										str+='<div class="row">';
										for(var j in result.categoryList[i].editionList)
										{
											str+='<div class="col-md-6 col-xs-12 col-sm-6">';
												str+='<div class="pad_15 bg_ED">';
												if(result.categoryList[i].editionList[j].editionCnt == 0){
													str+='<p class="panel-title">'+result.categoryList[i].editionList[j].edition+' Edition - '+result.categoryList[i].editionList[j].editionCnt+'</p>';
												}else{
													str+='<p class="panel-title alertDtlsCls" style="cursor:pointer;" attr_category_id="'+result.categoryList[i].statusTypeId+'" attr_status_id="0" attr_count="'+result.categoryList[i].editionList[j].editionCnt+'" attr_alert_type_id="'+alertTypeId+'" attr_edition_id="'+result.categoryList[i].editionList[j].editionId+'">'+result.categoryList[i].editionList[j].edition+' Edition - <span class="alertColorFont">'+result.categoryList[i].editionList[j].editionCnt+'</span></p>';
												}
												 str+='</div>';
											str+='</div>';  
										}
										
									str+='</div>';
								str+='</div>';
							}  
							str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
								str+='<div class="pad_5 bg_ED">';
								if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
									
										str+='<div class="table-responsive">';
									
								}else{
									if($(window).width() < 800)
									{
										str+='<div class="table-responsive">';
									}
								}  
									
									str+='<table class="table">';
										str+='<tr>';
										for(var j in result.categoryList[i].statusList)
										{
											if(result.categoryList[i].statusList[j].statusTypeId == 5)
												continue;       
											str+='<td>';
												str+='<p class="text-muted">'+result.categoryList[i].statusList[j].statusType+'</p>';
											str+='</td>';
										}
										str+='</tr>';   
										str+='<tr>';
										for(var j in result.categoryList[i].statusList)
										{
											if(result.categoryList[i].statusList[j].statusTypeId == 5)
												continue;
											str+='<td>';
												if(result.categoryList[i].statusList[j].statusCnt == 0){
													str+='<p class="text-muted">'+result.categoryList[i].statusList[j].statusCnt+'&nbsp;&nbsp;<small class="text-success">'+result.categoryList[i].statusList[j].statusCntPer+'%</small></p>';
												}else{
													str+='<p class="text-muted alertDtlsCls" style="cursor:pointer;" attr_edition_id="'+alertEdition+'" attr_category_id="'+result.categoryList[i].statusTypeId+'" attr_status_id="'+result.categoryList[i].statusList[j].statusTypeId+'" attr_count="'+result.categoryList[i].statusList[j].statusCnt+'"  attr_alert_type_id="'+alertTypeId+'"><span class="alertColorFont">'+result.categoryList[i].statusList[j].statusCnt+'</span>&nbsp;&nbsp;<small class="text-success">'+result.categoryList[i].statusList[j].statusCntPer+'%</small></p>';
												}  
												
											str+='</td>';
									
										}
										str+='</tr>';
									str+='</table>';
									if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
										
											str+='</div>';
										
									}else{
										if($(window).width() < 800)
										{
											str+='</div>';
										}
									}
									
								str+='</div>';
							str+='</div>';
						str+='</div>';
					}
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#alertOverviewDetails").html(str);
	}
	function arrowPositionMove(id){
	$(".alertOverViewDetailsLowCls,.alertInnerArrowLow").removeClass("alertsArrowLow");
	$(".alertOverViewDetailsCls,.alertInnerArrow").removeClass("alertsArrow");
	$(id).addClass("alertsArrow");
	$(id).find(".alertInnerArrow").addClass("alertsArrow");
	}
	function arrowPositionMove1(id1){
		$(".alertOverViewDetailsCls,.alertInnerArrow").removeClass("alertsArrow");
		$(".alertOverViewDetailsLowCls,.alertInnerArrowLow").removeClass("alertsArrowLow");
		$(id1).addClass("alertsArrowLow");
		$(id1).find(".alertInnerArrowLow").addClass("alertsArrowLow");
	}
	function getEditioDtls(alertTypeStr,alertEdition){
		$("#alertTypeHiddenId").attr("attr_alert_id",alertTypeStr);
		$("#alertEditionTypeHiddenId").attr("attr_alert_edition_id",alertEdition);  //undefined  
	
		//alert($("#alertTypeHiddenId").attr("attr_alert_id"));
		//alert($("#alertEditionTypeHiddenId").attr("attr_alert_edition_id"));
		
		if($(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			var scopeIdsArr = [2,3,6,7,9,5,8,10,11,1,4];          
			getAlertCategoryDtlsLocationWise($("#alertTypeHiddenId").attr("attr_alert_id"),$("#alertEditionTypeHiddenId").attr("attr_alert_edition_id"));
			getStateImpactLevelAlertDtlsCnt("other"); 
			getAssignGroupTypeAlertDtlsByImpactLevelWise(scopeIdsArr);
			$("#alertOverviewDetails").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			
			 scopeIdsArr = [];
			 $(".alertSettingsUl li").each(function() {
			  if($(this).find("input").is(":checked")){
				 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
				   if(selectionType == "District"){
					scopeIdsArr.push(2);
					}else if(selectionType == "Constituency"){
						scopeIdsArr.push(3);
					}else if(selectionType == "mandalMuncipality"){
						scopeIdsArr.push(5);  
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
						scopeIdsArr.push(6);	
					}else if(selectionType == "MuncipalityGMC"){
					   scopeIdsArr.push(8);
					}/* else if(selectionType == "National"){
				    scopeIdsArr.push(10);  	
				}else if(selectionType == "International"){
				    scopeIdsArr.push(11);  
				}else if(selectionType == "State"){
				    scopeIdsArr.push(1);	
				}else if(selectionType == "Parliament"){
				    scopeIdsArr.push(4);	
				}else if(selectionType == "All"){
					scopeIdsArr.push(2);
					scopeIdsArr.push(3);
					scopeIdsArr.push(5);
					scopeIdsArr.push(6);
					scopeIdsArr.push(7);
					scopeIdsArr.push(8);
					scopeIdsArr.push(9);  
					scopeIdsArr.push(10);  
					scopeIdsArr.push(11);  
					scopeIdsArr.push(1);  
					scopeIdsArr.push(4);  
				} */
			  }
		   });
		  var locVal ='';
			$( "li.optionsCls" ).each(function() {
				if($( this ).hasClass( "active" )){
					locVal = $(this).attr("attr_id");
				}
			});
			if(locVal == "1"){  
			   $(".districtAlertCls").hide();
			    $(".constituencyAlertCls").hide();	
				$(".districtSelectBoxCls").hide();
				getTotalAlertGroupByDist(scopeIdsArr,"other");      
			}else if(locVal == "2"){
				$(".districtAlertCls").hide();
			    $(".constituencyAlertCls").hide();	
				$(".districtSelectBoxCls").hide();
				getTotalAlertGroupByLocationThenCategory(scopeIdsArr);
			}else{
				$(".districtSelectBoxCls").show();
				$(".districtAlertCls").show();
			    $(".constituencyAlertCls").show();	
				getTotalAlertGroupByLocationThenStatus(scopeIdsArr);
				
				 var consScopeIdsArr = [];
				 $(".alertConsSettingsUl li").each(function() {
				  if($(this).find("input").is(":checked")){
					 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
					 if(selectionType == "Constituency"){
						consScopeIdsArr.push(3);
					}else if(selectionType == "mandal"){
						consScopeIdsArr.push(5);  
					}else if(selectionType == "VillageWard"){
						consScopeIdsArr.push(7);  
						consScopeIdsArr.push(9);	
						consScopeIdsArr.push(6);	
					}
				  }
			   });
				getTotalAlertConstituencyWise(consScopeIdsArr);
			}
		}
		      
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			fromDate:customStartDateAlert,        
			toDate :customEndDateAlert,
			alertType : alertTypeStr,
			editionType : alertEdition
		};
		$.ajax({
			type : 'GET',
			url : 'getAlertOverviewDetailsAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			if(result != null){
			  buildAlertOverviewDetails(result,alertEdition,alertTypeStr)
			}else{
			  $("#alertOverviewDetails").html("NO DATA AVAILABLE.");	
			}
      });	
	}
	function getVerificationDtls(alertId){
		var jsObj={
			alertId:alertId
		}
        $.ajax({
        type : 'POST',
        url : 'getAlertVerificationDetailsAction.action',
        dataType : 'json',
        data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#converSationDtlsDivId").html(' ');
			buildAlertVerificationStatusRlst(result);
		});
	}
	
	function buildAlertVerificationStatusRlst(result){
		var str = '';
		if(result.conversationList != null && result.conversationList.length > 0){
			$("#alertVerificationDiv").html("<h4 class='text-muted verifyHeadingColorStyling' style='font-size:15px;'>VERIFICATION STATUS-"+result.actionTypeStatus+"</h4>");  
			for(var i in result.conversationList){
				str+='<p class="text-capital panelTitleFont m_top20 verifyHeadingColorStyling" style="font-size:12px;">'+result.conversationList[i].heading+'</p>';  
				if(result.conversationList[i].comments != null && result.conversationList[i].comments.length > 0){
					str+='<p style="border: 1px solid rgb(211, 211, 211); padding: 6px;">'+result.conversationList[i].comments+'</p>';     
				}
				var documentList = result.conversationList[i].documentList;
				if(documentList != null && documentList.length > 0){
					str+='<p style="font-weight:bold;font-size:12px;" class="text-capital m_top10 panelTitleFont headingColorStyling">Attachments</p>';
					str+='<ul class="attachmentsBlock">';
					var order = 0;
					for(var k in documentList){
						order = order+1;
						var fullName = documentList[k];
						var nameArr = fullName.split(".");
						var type = nameArr[1];  
						var orderStr='';
						if(k<9){
							orderStr ="0"+order;
						}else{
							orderStr = order;  
						}
						var attachment = orderStr+'&nbspAttachment.'+type;
						str+='<li id="showAlertVerificationPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;"><i class="glyphicon glyphicon-paperclip"></i><span class="border"> '+attachment+' </span></li>';
					}
					str+='</ul>';
				}
				if(result.conversationList[i].name != null && result.conversationList[i].name.length > 0){
					str+='<p class="text-right" style="color:#7155D6;font-size:12px;">Created By:'+result.conversationList[i].name+'('+result.conversationList[i].updateTime+'&nbsp'+result.conversationList[i].time+')</p>';     
				}  
			}
			str+='<hr class="m_top10" style="border-top: 1px solid #ccc;">';
			$("#alertVerificationDtlsDiv").html(str);
		}
   }
   $(document).on('click','#showAlertVerificationPdfId',function(){      
		var dbFilePath = $(this).attr("attr_filePath");    
     	var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		window.open('/Reports/tour_documents/'+dbFilePath,'_blank');
	});          
	
	$(document).on("click",".alertDetailsCls",function(){
	var alertTypeId = $(this).attr("attr_alert_type");
	var alertCount = $(this).attr("attr_alert_count");
	var selectedAlertType = $(this).attr("attr_alert_type_name");
     getAlertDetatilsByAlertType(alertTypeId,alertCount,selectedAlertType);
	});
	function getAlertDetatilsByAlertType(alertTypeId,alertCount,selectedAlertType){
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");  
		$("#alertCntTitId").html(""+selectedAlertType+"-"+alertCount);
		      
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			fromDate:customStartDateAlert,        
			toDate :customEndDateAlert,
			alertTypeId : alertTypeId
		};
		$.ajax({
			type : 'POST',
			url : 'getAlertDetatilsByAlertTypeAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAlertDtls(result);   
			}
      });	
	}
	function getDistrictListByStateId(globalActivityMemberId,globalUserTypeId){
		var jsObj = { 
			stateId :globalStateId,
            activityMemberId:globalActivityMemberId,
			userTypeId:globalUserTypeId,
			fromDate : customStartDateAlert,      
			toDate : customEndDateAlert
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getDistrictListByStateIdAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			$("#dstrctSlctBxId").html(' ');
			if(result != null && result.length > 0){
				var str = '';
				str+='<option value="0">All DISTRICTS</option>';
                for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';			
				}				
				$("#dstrctSlctBxId").html(str);
			}
		});  
	}
	
	 $(document).on("click",".locationAlertCls",function(){
		var locationIdStr = $(this).attr("attr_location_id");
		var totalAlertCnt = $(this).attr("attr_alert_count");
		var loctionType = $(this).attr("attr_location_type");
		var alertStatusId = $(this).attr("attr_alert_status_id");
		var locationLevel = $(this).attr("attr_location_level");
		var districtIdArr =[];
		var constituencyId = 0;
		if(loctionType=="Constituency"){
		  if(locationLevel=="District/GMC CORP Impact Level"){
			 districtIdArr= locationIdStr.split(",");
		  }else{
			constituencyId = locationIdStr;  
		  }
		}else if(loctionType=="District"){
			if(locationLevel!="State Level"){
			  districtIdArr = locationIdStr.split(",");	
			}
		}
		//console.log(districtIdArr);
	
		buildDistrictWiseAlert(districtIdArr,totalAlertCnt,constituencyId,alertStatusId,locationLevel,loctionType);
	}); 
	$(document).on("change","#dstrctSlctBxId",function(){
		 var locVal ='';
		$( "li.optionsCls" ).each(function() {
			if($( this ).hasClass( "active" )){
				locVal = $(this).attr("attr_id");
			}
		});
		if(locVal=="1" || locVal=="2"){
		 return;	
		}
		
		var districtArr = [];
		var districtId = $(this).val();
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
					}else if(selectionType == "VillageWard"){
						scopeIdsArr.push(7);  
						scopeIdsArr.push(9);	
						scopeIdsArr.push(6);	
					}else if(selectionType == "MuncipalityGMC"){
					   scopeIdsArr.push(8);
					}/* else if(selectionType == "National"){
				scopeIdsArr.push(10);  	
			}else if(selectionType == "International"){
				scopeIdsArr.push(11);  
			}else if(selectionType == "State"){
				scopeIdsArr.push(1);	
			}else if(selectionType == "Parliament"){
				scopeIdsArr.push(4);	
			} */
		  }
	   }); 
	   
			var consScopeIdsArr = [];
			 $(".alertConsSettingsUl li").each(function() {
			  if($(this).find("input").is(":checked")){
				 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
				 if(selectionType == "Constituency"){
					consScopeIdsArr.push(3);
				}else if(selectionType == "mandal"){
					consScopeIdsArr.push(5);  
				}else if(selectionType == "VillageWard"){
					consScopeIdsArr.push(7);  
					consScopeIdsArr.push(9);	
					consScopeIdsArr.push(6);	
				}
			  }
		   });
	   getTotalAlertConstituencyWise(consScopeIdsArr);
	   getTotalAlertGroupByLocationThenStatus(scopeIdsArr);
	   getAssignGroupTypeAlertDtlsByImpactLevelWise(scopeIdsArr);
	});
	
	
	function getTotalAlertConstituencyWise(scopeIdsArr){
		$(".constituencyAlertCls").show();
		$("#constituencyAlertDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		 var districtArr=[];
		if($("#dstrctSlctBxId").is(":visible")){
			var districtId = $("#dstrctSlctBxId").val();
			if(districtId > 0){
				districtArr.push(districtId);
			}
		}
		
		var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,      
			toDate : 			customEndDateAlert,  
			scopeIdsArr : 		scopeIdsArr,              
			activityMemberId : 	globalActivityMemberId,       
			group : 			"",
			alertIds : 			alertId,
			editionIds : 		editionId,
			filterType : 	"Constituency",
			districtArr : districtArr
			
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getTotalAlertGroupByLocationThenStatusAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			$("#constituencyAlertDivId").html(' ');
			if(result != null && result.length > 0){  
				buildAlertDetailsInDebularFormat(result,"constituencyAlertDivId","Constituency");
			}
		});  
	}
	function buildAlertDetailsInDebularFormat(result,divId,locationType){
	 var str='';
	  if($(window).width() < 800)
		{
			str+='<div class="table-responsive">';
		}
	 if(divId=="constituencyAlertDivId"){
		  str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed table-bordered" id="constituencyAlertDataTblId">'; 
	 }else if(divId=="districtSummaryAlertDivId"){
		   str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed table-bordered" id="districtAlertDataTblId">';
	 }
	 
		 str+='<thead>';
		       str+='<th></th>';
			   str+='<th>Total</th>';
		       if(result != null && result.length > 0){
				    if(result[0].subList1 != null && result[0].subList1.length > 0){
						 for(var k in result[0].subList1){
							 if(result[0].subList1[k].categoryId == 5)
								 continue;
							str+='<th>'+result[0].subList1[k].category+'</th>';		 
						 }
					}
			   }
	   str+='</thead>';
		 str+='<tbody>';
		  for(var i in result){
				str+='<tr>';
				str+='<td>'+result[i].status+'</td>';
				 if(result[i].count > 0){
					if(result[i].statusId==0){
					str+='<td  style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" class="locationAlertCls" attr_location_level="'+result[i].status+'" attr_location_type='+locationType+' attr_alert_count='+result[i].count+' attr_location_id = '+result[i].deptIdList+' attr_alert_status_id="0">'+result[i].count+'</td>'; 
					}else{
					str+='<td  style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" class="locationAlertCls" attr_location_level="'+result[i].status+'" attr_location_type='+locationType+' attr_alert_count='+result[i].count+' attr_location_id = '+result[i].statusId+' attr_alert_status_id="0">'+result[i].count+'</td>'; 
					}
					}else{
				  str+='<td>-</td>';      		
				} 
				if(result[i].subList1 != null && result[i].subList1.length > 0){
					for(var j in result[i].subList1){
						if(result[i].subList1[j].categoryId == 5)
									continue;
							if(result[i].subList1[j].categoryCount > 0){
								if(result[i].statusId==0){
								str+='<td  style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" class="locationAlertCls" attr_location_type='+locationType+' attr_alert_count='+result[i].subList1[j].categoryCount+' attr_location_level="'+result[i].status+'" attr_location_id = '+result[i].deptIdList+' attr_alert_status_id='+result[i].subList1[j].categoryId+'>'+result[i].subList1[j].categoryCount+'</td>';  	
								}else{
								str+='<td  style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" class="locationAlertCls" attr_location_type='+locationType+' attr_alert_count='+result[i].subList1[j].categoryCount+' attr_location_level="'+result[i].status+'" attr_location_id = '+result[i].statusId+' attr_alert_status_id='+result[i].subList1[j].categoryId+'>'+result[i].subList1[j].categoryCount+'</td>';      	
								}
							}else{
								str+='<td>-</td>';      	
							} 
					 }	
				}
				str+='</tr>';
			}
			 str+='</tbody>';
			 str+='</table>';
	      $("#"+divId).html(str);
		  if($(window).width() < 800)
		{
			str+='</div>';
		}
		   if(divId=="constituencyAlertDivId"){
			 $("#constituencyAlertDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 15,
			"aLengthMenu": [[10,15,20,30,50, 100, -1], [10,15,20,30,50, 100, "All"]]			
		   });   	
		   }else if(divId == "districtSummaryAlertDivId"){
			 $("#districtAlertDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 15,
			"aLengthMenu": [[10,15,20,30,50, 100, -1], [10,15,20,30,50, 100, "All"]]
		   });   	
		   }
		}
		getAlertStatus();
	function getAlertStatus(){
		var jsObj = { 
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getAlertStatusAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			//console.log(result);
		});  
	}
	//getPublicationWiseAlert()
	function getPublicationWiseAlert(){
		var scopeIdsArr = [2,3,4,5,6,7,8];
		$(".districtAlertCls").show();
		$("#districtWiseAlertCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#districtSummaryAlertDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
        var districtArr=[];
		if($("#dstrctSlctBxId").is(":visible")){
			var districtId = $("#dstrctSlctBxId").val();
			if(districtId > 0){
				districtArr.push(districtId);
			}
		}
		var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,      
			toDate : 			customEndDateAlert,  
			scopeIdsArr : 		scopeIdsArr,              
			activityMemberId : 	globalActivityMemberId,       
			group : 			"",
			alertIds : 			alertId,
			editionIds : 		editionId,
			filterType : 	"District",
			districtArr : districtArr,
			alertStatusId : 0
			
			
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getPublicationWiseAlertAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
		   console.log(result);
		});  
	}
	
	$(document).on('click','#alertConstSettingsId',function(){
		$(".specialAlertDropDownAlertCons").toggle();            
	});
	 $(document).on("click",".alertSetCloseCons",function(){
			$(".specialAlertDropDownAlertCons").hide(); 
	 });
	 $(document).on("click","#alertImpactSelectAllIdCons",function(){
			 if ($(this).prop('checked')) {
				$(".alertConImpactCheckCls").prop('checked', true);
			} else {
				$(".alertConImpactCheckCls").prop('checked', false);
			}
	});
	$(document).on("click",".alertConstDtlsBtnCls",function(){
		$(".specialAlertDropDownAlertCons").toggle(); 
		var consScopeIdsArr = [];
		 $(".alertConsSettingsUl li").each(function() {
		  if($(this).find("input").is(":checked")){
			 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
		     if(selectionType == "Constituency"){
				consScopeIdsArr.push(3);
			}else if(selectionType == "mandal"){
				consScopeIdsArr.push(5);  
			}else if(selectionType == "VillageWard"){
				consScopeIdsArr.push(7);  
				consScopeIdsArr.push(9);	
				consScopeIdsArr.push(6);	
			}
		  }
	   });
	   if(consScopeIdsArr.length == 0){
			alert("Please select atleast one option");
			return;  
		}
	 	getTotalAlertConstituencyWise(consScopeIdsArr);
	});  