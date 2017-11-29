     var globalEnrollementYearIdsForAlert = 0;
    /* Setting script code start */
	 var impactLevelObj =  {"0":"All","1":"State","2":"District","3":"Constituency","8":"CORP-GMC","5":"Mandal/MUNICIPALITY","7":"Village/ward/PANCHAYAT","2":"State","3":"District","4":"Constituency","6":"Village/ward/HAMLET"};	//2 - State ,3-District,4-Constituency and 6-Village/Ward/Hamlet for location 
	$(document).on("click",".alertSettingBlock",function(e){
		$(this).closest(".alertsBlock").find(".basicAlertBlockDropDown").toggle();
		e.stopPropagation();
	});
	$(document).on("click","#alertImpactScopeSelectAllId",function(){
			 if ($(this).prop('checked')) {
				$(".alertImpactCheckCls").prop('checked', true);
			} else {
				$(".alertImpactCheckCls").prop('checked', false);
			}
    });
	$(document).on("click","#alertStatusSelectAllId",function(){
			 if ($(this).prop('checked')) {
				$(".alertStausCls").prop('checked', true);
			} else {
				$(".alertStausCls").prop('checked', false);
			}
    });
	$(document).on("click",".alertsShowBody",function(){
		if($(this).text() == 'Click to more')
		{
			$(this).text("Click for less");
			$(".alertsHideBody").addClass("active");
		}else{
			$(this).text("Click to more");
			$(".alertsHideBody").removeClass("active");
		}
	});
	  var globalAlertStatusArr = [];
	  var globalImpactScopeArr = [];
	  var globalDistrictImpactLevelScopeArr = [];
	  var globalCorpGhmcImpactScopeSArr = [];
	  var globalConstituencyImpactScopeArr = [];
     function getAlertDtlsBasedOnSelection(type){
		 if (globalEnrollementyearIdArr != null) {
		    globalEnrollementYearIdsForAlert = globalEnrollementyearIdArr;	 
		 }
		  //Clear arrays
		    globalAlertStatusArr = [];
		    globalImpactScopeArr = [];
		    globalDistrictImpactLevelScopeArr = [];
		    globalCorpGhmcImpactScopeSArr = [];
		    globalConstituencyImpactScopeArr = [];
		 $(".alertImpactSettingCls li").each(function() {
		  if($(this).find("input").is(":checked")){
			 var selectionType = $(this).find("input").attr("attr_scope_type").trim();
			 if(selectionType == "State"){
				globalImpactScopeArr.push(1); 
			 }else if(selectionType == "District"){
				globalImpactScopeArr.push(2);
				globalDistrictImpactLevelScopeArr.push(2);
			}else if(selectionType == "Constituency"){
				globalImpactScopeArr.push(3);
				globalDistrictImpactLevelScopeArr.push(3);
				globalConstituencyImpactScopeArr.push(3);
			}else if(selectionType == "mandalMuncipality"){
				globalImpactScopeArr.push(5);  
				globalImpactScopeArr.push(12);  
				globalDistrictImpactLevelScopeArr.push(5);  
				globalDistrictImpactLevelScopeArr.push(12); 
				globalConstituencyImpactScopeArr.push(5);				
				globalConstituencyImpactScopeArr.push(12);				
			}else if(selectionType == "VillageWardPanchayat"){
				globalImpactScopeArr.push(7);  
				globalImpactScopeArr.push(9);	
				globalImpactScopeArr.push(6);	
				globalDistrictImpactLevelScopeArr.push(7);  
				globalDistrictImpactLevelScopeArr.push(9);	
				globalDistrictImpactLevelScopeArr.push(6);
				globalConstituencyImpactScopeArr.push(7);  
				globalConstituencyImpactScopeArr.push(9);	
				globalConstituencyImpactScopeArr.push(6);				
			}else if(selectionType == "MuncipalityGMC"){
			   globalImpactScopeArr.push(8);
			   globalDistrictImpactLevelScopeArr.push(8);
			   globalCorpGhmcImpactScopeSArr.push(8);
			} 
		  }
	   });
	     //Status Setting
	    $(".alertStatusSettingUl li").each(function() {
		  if($(this).find("input").is(":checked")){
			 var alertStatusId = $(this).find("input").attr("attr_status_id");
		       if(alertStatusId != 0){
				   globalAlertStatusArr.push(alertStatusId);
			   }
		  }
	   });
	    if(globalImpactScopeArr.length == 0){
			alert("Please Select Impact Scope.");
			return;
		}
		 if(globalAlertStatusArr.length == 0){
			alert("Please Select Status.");
			return;
		}
	   if(type == "click"){
		        $(".constituencyUl li").removeClass("active");
				 $(".constituencyUl li:first-child").addClass("active");
				 $(".districtUl li").removeClass("active");
				 $(".districtUl li:first-child").addClass("active");
				 
		        $("#alertTypeHiddenId").attr("attr_alert_id",0);
		        $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id",0);  
				$(".alertComparisonblock").hide();
			    $(".alertImpctLevelBlcock").show();	
			    $(".alertFilterCls li").removeClass("active");
			    $(".alertFilterCls li:nth-child(2)").addClass("active");
			    $(".collapseTblViewCls").removeClass("active");
			    $(".collapseHIghChartViewCls").addClass("active");
	  		    $(".impactLevelCls").attr("attr_level","Overview");
				getAlertOverviewDetails();
				getAlertOverviewDetailsNextLevel();
			if($(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".alertLocationDiv").show();
				 $("#hiddenLevelTypeId").attr("attr_level_type","impactScopeWise");
				 $("#locationAlertsId").prop("checked",false);
                 $("#impactAlertsId").prop("checked",true);
				 $(".locImptLevelDivCls").show();
				getAlertCategoryDtlsLocationWise(0,0);
				getStateImpactandItsSubLevelAlert("impactScopeWise");
				getDistrictImpactandItsSubLevelAlert("Decending","0","impactScopeWise");
				getCorpGMCAlert(0,"impactScopeWise");
				getConstituencyImpactandItsSubLevelAlert("Decending","0","0","impactScopeWise");
				getAssignGroupTypeAlertDtlsByImpactLevelWise(0);
		    }
			$(".basicAlertBlockDropDown").hide(); 
		}else if(type= "default"){
		 getAlertOverviewDetails();	
		 getAlertOverviewDetailsNextLevel();
		}
	 }
    /* End */
	var customStartDateAlert = moment().format('DD/MM/YYYY')
	var customEndDateAlert = moment().format('DD/MM/YYYY');
	
	function globalAlertsCalls(type)
	{
		$("#alertTypeHiddenId").attr("attr_alert_id","0");
		$("#alertEditionTypeHiddenId").attr("attr_alert_edition_id","0");
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
		getAlertOverviewDetailsNextLevel();
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
		  
		  $(".alertComparisonblock").hide();
		  $(".alertImpctLevelBlcock").show();	
		  $(".alertFilterCls li").removeClass("active");
		  $(".alertFilterCls li:nth-child(2)").addClass("active");
		  $(".collapseTblViewCls").removeClass("active");
		  $(".collapseHIghChartViewCls").addClass("active");
	  
		 $("#alertTypeHiddenId").attr("attr_alert_id",0);
		 $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id",0);  
		 
		 $(".constituencyUl li").removeClass("active");
	     $(".constituencyUl li:first-child").addClass("active");
		 $(".districtUl li").removeClass("active");
	     $(".districtUl li:first-child").addClass("active");	
          $(".locImptLevelDivCls").show();
          $("#locationAlertsId").prop("checked",false);
          $("#impactAlertsId").prop("checked",true);
		  $("#hiddenLevelTypeId").attr("attr_level_type","impactScopeWise");
			
		 getAlertOverviewDetails();
		 getAlertOverviewDetailsNextLevel();
		 getAlertCategoryDtlsLocationWise(0,0); 
		 getAssignGroupTypeAlertDtlsByImpactLevelWise(0);
		 $(".impactLevelCls").attr("attr_level","Overview");
		 getStateImpactandItsSubLevelAlert("impactScopeWise");
		 getDistrictImpactandItsSubLevelAlert("Decending","0","impactScopeWise");
		 getCorpGMCAlert(0,"impactScopeWise");
		 getConstituencyImpactandItsSubLevelAlert("Decending","0","0","impactScopeWise");
	    //getTotalAlertGroupByDist(scopeIdsArr,"date");  
	    // getStateImpactLevelAlertStatusWise("date");
	 var dates= $("#dateRangeIdForAlert").val();
	 $("#alertDateHeadingId").html(picker.chosenLabel+" ( "+dates+" )");
	});
	
	function defaultAlertCalls()
	{      
		    $(".alertFilterCls li").removeClass("active");
		    $(".alertFilterCls li:nth-child(2)").addClass("active");
			$(".constituencyUl li").removeClass("active");
			$(".constituencyUl li:first-child").addClass("active");
			$(".districtUl li").removeClass("active");
			$(".districtUl li:first-child").addClass("active");
		    $(".collapseTblViewCls").removeClass("active");
		    $(".collapseHIghChartViewCls").addClass("active");
		    $(".impactLevelCls").attr("attr_level","Overview");
			$(".alertComparisonblock").hide();
			$(".alertImpctLevelBlcock").show();	
			$(".locImptLevelDivCls").show();
			$("#locationAlertsId").prop("checked",false);
            $("#impactAlertsId").prop("checked",true);
			$("#hiddenLevelTypeId").attr("attr_level_type","impactScopeWise");
	        getAlertCategoryDtlsLocationWise($("#alertTypeHiddenId").attr("attr_alert_id"),$("#alertEditionTypeHiddenId").attr("attr_alert_edition_id"));
			getStateImpactandItsSubLevelAlert("impactScopeWise");
			getDistrictImpactandItsSubLevelAlert("Decending","0","impactScopeWise");
			getCorpGMCAlert(0,"impactScopeWise");
			getConstituencyImpactandItsSubLevelAlert("Decending","0","0","impactScopeWise");
			getAssignGroupTypeAlertDtlsByImpactLevelWise(0);
	}
	/* $(document).on("click",".alertsIconExpand",function(){
		$(".dateRangePickerClsForAlert").toggleClass("hide");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".alertsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".alertLocationDiv").show();
			//console.log("opening")
			defaultAlertCalls();
			$(".districtAltCtnCls").toggle();
			setTimeout(function(){
				$('html,body').animate({
					scrollTop: $(".alertsBlock").offset().top},
				'slow');
			},500);
		}else{
			//console.log("closing")
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
		}); */
	$(document).on("click",".alertSettingCloseCls",function(){      
		$(".specialAlertDropDown").toggle();                    
	});
	
	function getAlertDetails(optionId)
	{
		var scopeIdsArr = [];
		var option = optionId;
		  $(".alertImpctLevelBlcock").show();	
 		  $(".alertComparisonblock").hide();
		  $(".alertFilterCls li").removeClass("active");
		  $(".alertFilterCls li:nth-child(2)").addClass("active");
		  $(".constituencyUl li").removeClass("active");
	     $(".constituencyUl li:first-child").addClass("active");
		 $(".districtUl li").removeClass("active");
	     $(".districtUl li:first-child").addClass("active");
		 $(".collapseTblViewCls").removeClass("active");
		 $(".collapseHIghChartViewCls").addClass("active");
		if(option == "1"){
			$("#locationAlertsId").prop("checked",false);
            $("#impactAlertsId").prop("checked",true);			
			$(".locImptLevelDivCls").show();
		    $("#hiddenLevelTypeId").attr("attr_level_type","impactScopeWise");
			$(".impactLevelCls").attr("attr_level","Overview");
			getStateImpactandItsSubLevelAlert("impactScopeWise");
			getDistrictImpactandItsSubLevelAlert("Decending","0","impactScopeWise");
			getCorpGMCAlert("0","impactScopeWise");
			getConstituencyImpactandItsSubLevelAlert("Decending","0","0","impactScopeWise");
			getAssignGroupTypeAlertDtlsByImpactLevelWise(0);
		 }else if(option == "2"){
			 $(".locImptLevelDivCls").hide();
			 $(".impactLevelCls").attr("attr_level","Status");
			 getStateImpactLevelAlertStatusWise();
			 getTotalAlertGroupByLocationThenStatus("Decending","0");
			 getGhmcImpactLevelAlertStatusWise("0");
			 getConstituencyAlertStatusWise("Decending","0","0");
			 getAssignGroupTypeAlertDtlsByImpactLevelWise(0);
		}else if(option =="3"){
			 $(".locImptLevelDivCls").hide();
			$(".impactLevelCls").attr("attr_level","Publication");
			getStateImpcatLevelAlertCntPublicationWise();
			getDistrictWisePublicationAlert("Decending","0");
			getCorpGHMCImpcatLevelAlertCntPublicationWise("0");
			getConstituencyWisePublicationAlert("Decending","0","0");
			getAssignGroupTypeAlertDtlsByImpactLevelWise(0);
		}else if(option =="4"){
			 $(".locImptLevelDivCls").hide();
		  $("#alertChildActivityMemberDivId").html('');
		  $("#childUserTypeDetailsDivForAlerts").html('');
		  $("#candidateLocationAlertDtlsStatusWiseDivId").html('');
		  $(".alertImpctLevelBlcock").hide();	
 		  $(".alertComparisonblock").show();
		  getAllItsSubUserTypeIdsByParentUserTypeIdForAlert();
		}
	}
	/* function getTotalAlertGroupByDist(scopeIdsArr,location){ 
	
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
	} */
	$(document).on("click",".alertDtlsCls",function(){
		var alertStatusId = $(this).attr("attr_status_id");
		var alertCategoryId = $(this).attr("attr_category_id");
		var alertTypeId = $(this).attr("attr_alert_type_id");
		var alertCount = $(this).attr("attr_count");
		var editionId = $(this).attr("attr_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		getAlertDtls(alertStatusId, alertCategoryId, alertTypeId,alertCount,editionId,"No",0,0);  
		
	});  
	 $(document).on("click",".alertActionCls",function(){
		var alertVerificationStatusId = $(this).attr("attr_status_id");
		var alertTypeId = $(this).attr("attr_alert_type_id");
		var alertCount = $(this).attr("attr_count");
		var editionId = $(this).attr("attr_edition_id");
		var actionTypeId = $(this).attr("attr_action_type_id");
		if(editionId == undefined){
			editionId = 0;
		}
		getAlertDtls(0,0, alertTypeId,alertCount,editionId,"Yes",actionTypeId,alertVerificationStatusId);  
		
	});  
	
	function getAlertDtls(alertStatusId, alertCategoryId, alertTypeId,alertCount,editionId,isActionType,actionTypeId,alertVerificationStatusId){
		
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");  
		$("#alertCntTitId").html("TOTAL ALERTS-"+alertCount);
		var alertStatusArr = [];
		 if(alertStatusId == 0){
			alertStatusArr = globalAlertStatusArr;
		 }else{
			alertStatusArr.push(alertStatusId);
		 }
		 
		var jsObj = { 
			alertTypeId : alertTypeId,    
			alertStatusArr : alertStatusArr,
			alertCategoryId : alertCategoryId,  
			stateId : globalStateId,             
			fromDate : customStartDateAlert,                
			toDate : customEndDateAlert,                
			activityMemberId : globalActivityMemberId,
			editionIds : editionId,
            isActionType : isActionType,
            actionTypeId :actionTypeId,
            scopeIdsArr : globalImpactScopeArr,
			alertVerificationStatusId:alertVerificationStatusId
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
			editionIds : 		editionId,
			scopeIdsArr : 		globalImpactScopeArr, 
	        alertStatusArr : globalAlertStatusArr
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
						if(result[i].subList[j].locationType != null){
						 locationNameArr.push(result[i].subList[j].locationType.toUpperCase());	
						}
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
									if(this.value.toString() >= 12){
										return this.value.toString().substring(0, 12)+'...';
									}else{
										return this.value;
									}
									
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
								
							 var statusList = obj.statusList;
							 var str='';
							  str+='Total Alerts - '+obj.alertCount+" ";
							 var obj2 = statusList.filter(function ( obj2 ) {
									return str+="<br/>"+obj2.statusType+" - "+obj2.alertCount+" ";
								});
							return str;
                        	/* return "Total Alerts - "+obj.alertCount+"<br/>Pending - " + obj.pendingCnt + "<br/>Notified - " + obj.notifiedCnt + " <br/>Action In Progess - " + obj.actionInProgessCnt+"<br/>Completed - " + obj.completedCnt + "<br/>Unable to Resolve - " + obj.unabletoResolveCnt + "<br/>Action Not Required - " + obj.actionNotRequiredCnt+"<br/>Duplicate - "+obj.duplicatesStatusCnt+"";  */    
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
										return (this.y); 
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
		}else if(locationId == 5){
			scopeIdsArr.push(5);  
			scopeIdsArr.push(12);	
		}else if(locationId == 7){
			scopeIdsArr.push(7);  
			scopeIdsArr.push(9);
			scopeIdsArr.push(6);
		}else if(locationId==8){
			scopeIdsArr.push(8);
		}
		var districtIdArr = [];
	//	var alertStatusArr = [];
		var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,        
			toDate : 			customEndDateAlert,                  
			scopeIdsArr : 		scopeIdsArr,              
			activityMemberId : 	globalActivityMemberId,
			districtIdArr :     districtIdArr,       
			catId : 			catId,
			alertTypeId : 		alertTypeIds,
			editionId : 		editionIds,
			constituencyId : 0 ,
			alertStatusArr : globalAlertStatusArr,
			locationLevel : "",
			isPublication:"false",
			publicationId:0,
            locationElectionBodyId:0,
			type : "impactScopeWise"			
		
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
	/* function getTotalAlertGroupByLocationThenCategory(scopeIdsArr){
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
	} */
	function getTotalAlertGroupByLocationThenStatus(sortingType,districtId){
		/*Hiding Block if impact Level is not selected*/
		$(".districtImpactLevelBlockCls").show();
		if(globalDistrictImpactLevelScopeArr == null || globalDistrictImpactLevelScopeArr.length == 0){
			$(".districtImpactLevelBlockCls").hide();
			return;
		}
		$("#districtImpactLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		 }
		 $("#districtOverviewHeadingId").html("District overview - impact alerts");
  
			var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,      
			toDate : 			customEndDateAlert,  
			scopeIdsArr : 		globalDistrictImpactLevelScopeArr,              
			activityMemberId : 	globalActivityMemberId,       
			group : 			"",
			alertIds : 			alertId,
			editionIds : 		editionId,
			filterType : 	"District",
			locationValue : districtId,
			alertStatusArr : globalAlertStatusArr,
			sortingType : sortingType,
			districtId : 0
			
			
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getTotalAlertGroupByLocationThenStatusAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			globalDistrictLevelRlst = result;
			if(result != null && result.length > 0){
			    buildDistrictOrConstituencyImpactLevelHighChartRsltStatusWise(result,"districtImpactLevelHighChartDivId",districtId,"District");	
			}else{
				$(".districtImpactLevelBlockCls").hide();
			}
		});  
	}
	function buildDistrictOrConstituencyImpactLevelHighChartRsltStatusWise(result,divId,locationValue,locationType){
		
		     if(result != null && result.length > 10){
			    var highChartDivHight = result.length*40;
			    $("#"+divId).height(highChartDivHight);
			 }else{
				$("#"+divId).height(400);		
			 }
			
		 if(result != null && result.length > 0){
			 var locatinNameArr = [];
			 var str = '';
			 if(divId=="districtImpactLevelHighChartDivId"){
			  //  str+='<option value="-1">Select District</option>';	 
			    str+='<option value="0">All</option>';	 
			 }else{
				//str+='<option value="-1">Select Constituency</option>'; 
				str+='<option value="0">All</option>'; 
			 }
			 for(var i in result){
				 locatinNameArr.push(result[i].name);
				  str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			 }
			 if(locationValue == 0){ // Building district for searching first time only
				 if(divId=="districtImpactLevelHighChartDivId"){
					  $("#districtSelectBoxId").val(' ');
					  $("#districtSelectBoxId").html(str);
				  }else{
					  $("#constituencySeletBoxId").val(' ');
					  $("#constituencySeletBoxId").html(str);  
				  }
			 }
			 var pendingAlertArr = [];
			 var notifiedAlertArr = [];
			 var actionInProgessAlertArr = [];
			 var completedAlertArr = [];
			 var unblTRslvAlertArr = [];
			 var actionNotRequiredAlertArr = [];
			 var duplicateAlertArr = [];
			 
			 var wrnglyMppdDsgntnAlertArr = [];
			 var wrnglyMppdDprtmntAlertArr = [];
			 var rejoinderAlertArr = [];
			 var incompleteAlertArr = [];
			 var closedAlertArr = [];
			 var proposalAlertArr = [];
			 
			 for(var i in result){
				 for(var j in result[i].subList1){
				    if(result[i].subList1[j].statusTypeId==1){
						 pendingAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name}); 
					}else if(result[i].subList1[j].statusTypeId==2){
						 notifiedAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==3){
						 actionInProgessAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==4){
						 completedAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==5){
						 unblTRslvAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==6){
						 actionNotRequiredAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==7){
						 duplicateAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==8){
						 wrnglyMppdDsgntnAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==9){
						 wrnglyMppdDprtmntAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==10){
						 rejoinderAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==11){
						 incompleteAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==12){
						 closedAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(result[i].subList1[j].statusTypeId==13){
						 proposalAlertArr.push({y:result[i].subList1[j].totalAlertCnt,"extra":result[i].subList1[j].statusTypeId+"-"+result[i].id+"-"+result[i].subList1[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}
				 }
			 }
		       var mainJosnObjArr = [];
			   if(pendingAlertArr != null && pendingAlertArr.length > 0){
				mainJosnObjArr.push({name:'Pending',data:pendingAlertArr,color:"#A27FC2"});  
			  }
			   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
				mainJosnObjArr.push({name:'Notified',data:notifiedAlertArr,color:"#0175F3"});  
			  }
			  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
				mainJosnObjArr.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#3EC3FF"});  
			  }
			  if(completedAlertArr != null && completedAlertArr.length > 0){
				mainJosnObjArr.push({name:'Completed',data:completedAlertArr,color:"#049968"});  
			  }
			  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
				mainJosnObjArr.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#F21A98"});  
			  }
			  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
				mainJosnObjArr.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#FD6E07"});  
			  }
			  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
				mainJosnObjArr.push({name:'Duplicate',data:duplicateAlertArr,color:"#CF0001"});  
			  }
			  if(wrnglyMppdDsgntnAlertArr != null && wrnglyMppdDsgntnAlertArr.length > 0){
				mainJosnObjArr.push({name:'Wrongly Mapped Designation',data:wrnglyMppdDsgntnAlertArr,color:"#FE9900"});  
			  } 
			  if(wrnglyMppdDprtmntAlertArr != null && wrnglyMppdDprtmntAlertArr.length > 0){
				mainJosnObjArr.push({name:'Wrongly Mapped Department',data:wrnglyMppdDprtmntAlertArr,color:"#0C9514"});  
			  }
			  if(rejoinderAlertArr != null && rejoinderAlertArr.length > 0){
				mainJosnObjArr.push({name:'Rejoinder',data:rejoinderAlertArr,color:"#82CA9C"});  
			  } 
			  if(incompleteAlertArr != null && incompleteAlertArr.length > 0){
				mainJosnObjArr.push({name:'Incomplete',data:incompleteAlertArr,color:"#C9AC82"});  
			  } 
			  if(closedAlertArr != null && closedAlertArr.length > 0){
				mainJosnObjArr.push({name:'Closed',data:closedAlertArr,color:"#ababab"});  
			  }
			 if(proposalAlertArr != null && proposalAlertArr.length > 0){
				mainJosnObjArr.push({name:'Proposal',data:proposalAlertArr,color:"#FFA07A"});  
			  }
			  var getWidth = $("#districtOvervwGraph").width();
			 $("#"+divId).css("width",getWidth);	
			  $("#"+divId).highcharts({
				chart: {
					type: 'bar'
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
					categories: locatinNameArr,
					title: {
						text: null
					}
				},
				yAxis: {
					min: 0,
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: '',
						align: 'high'
					},
					labels: {
						overflow: 'justify'
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						},
						formatter: function() {
                        return  (this.total);
                    },
					}
				},
				tooltip: {
					valueSuffix: ' ',
					shared:true
				},
				plotOptions: {
					bar: {
					stacking: 'normal',
						dataLabels: {
							align: 'center',
							x :5,
							y:-3,
							enabled: false,
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
								click: function () {
									var locationInfo = (this.extra).split("-");
									var alertStatusId = locationInfo[0];
									var locationId = locationInfo[1];
									var totalAlertCnt = locationInfo[2];
									var locationType = locationInfo[3];
									var locationName = locationInfo[4];
									 if(totalAlertCnt == 0){
										return;  
									 }
									locationAlertDetails(alertStatusId,locationId,totalAlertCnt,locationType,locationName);
								}
							}
						}
				 }
				},
				legend: {
						reversed: false,
						verticalAlign:'top'
						},
				credits: {
					enabled: false
				},
				series: mainJosnObjArr
			});
		 }else{
			 $("#"+divId).html("NO DATA AVAILABLE.");
		 }
		 if(result != null && result.length > 10){ 
				$("#constituencyOvervwGraph").mCustomScrollbar();//{setHeight:'600px'}
				$("#constituencyOvervwGraph").css("height","655px");
				//$("#constituencyOvervwGraph").mCustomScrollbar({setHeight:'655px'})
		  }else{
				$("#constituencyOvervwGraph").css("height","auto");
			}
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
			   if(result[i].currentSts == result[i].status)  
			   {  
					str+='<li class="m_top10" role="presentation"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab"><span>'+result[i].status+'</span><span class="glyphicon glyphicon-hourglass pull-right" style="font-size: 22px;color: #777 !important;"></span><br/><span class="color_FF">'+result[i].sublist2[0].date+'</span></a></li>';
				}else{
					str+='<li role="presentation" class="active m_top10"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab"><span>'+result[i].status+'</span><span class="glyphicon glyphicon-ok pull-right" style="font-size: 22px;color: #777 !important;margin-left: 15px;"></span><br/><span class="color_FF">'+result[i].sublist2[0].date+'<span></a></li>';
				}        
				
			}
			str+='</ul>';
			str+='<div class="tab-content alertComment">';
				for(var i in result)
				{
				   if(result[i].currentSts == result[i].status)  
					{
						str+='<div role="tabpanel" class="tab-pane active" id="commentStatus'+i+'">';
					}else{
						str+='<div role="tabpanel" class="tab-pane " id="commentStatus'+i+'">';
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

function getAssignGroupTypeAlertDtlsByImpactLevelWise(disctrictId){
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
		
		var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,        
			toDate : 			customEndDateAlert,             
			scopeIdsArr : 		globalImpactScopeArr,              
			activityMemberId : 	globalActivityMemberId,
			alertTypeId : 		alertId,
		    editionTypeId : 	editionId,
            districtId	  :     disctrictId,
			alertStatusArr : globalAlertStatusArr,
			enrollementYearIds:globalEnrollementYearIdsForAlert
			
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
						 // $(".groupAssignCls").hide();
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
		  if(groupAssignType == "Public Representative"){
			  getTotalAlertGroupByPubRepThenStatus(groupAssignType,0,"alertDetailsDivId",[0],"")
		  }else if(groupAssignType == "Party Committee"){
			  var commitLvlIdArr = [];
			  commitLvlIdArr.push(10);
			  commitLvlIdArr.push(11);
			  commitLvlIdArr.push(5);
			  commitLvlIdArr.push(7);
			  commitLvlIdArr.push(6);
			  commitLvlIdArr.push(8);    
			  commitLvlIdArr.push(12);    
			  getTotalAlertGroupByPubRepThenStatus(groupAssignType,0,"alertDetailsDivId",commitLvlIdArr,"");  
		  }else if(groupAssignType == "Program Committee"){
			  getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(groupAssignType,"alertDetailsDivId")
		  }else if(groupAssignType == "Others"){
			  getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(groupAssignType,"alertDetailsDivId");
		  }
	});
	
$(document).on("click",".alertModalCloseCls",function(){
	 $(".commitLvlCls").prop('checked',false);
	 $("#commitLvlId1").prop('checked',true);
});	
function getTotalAlertGroupByPubRepThenStatus(groupAssignType,publicRepresentativeTypeId,divId,commitLvlIdArr,level){
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
	   var districtId = $("#districtSelectBoxId").val();
	   var jsObj = { 
		stateId : globalStateId,             
		fromDate : customStartDateAlert,      
		toDate : customEndDateAlert,  
		scopeIdsArr : globalImpactScopeArr,                       
		activityMemberId : globalActivityMemberId,
		publicRepresentativeTypeId :publicRepresentativeTypeId,
		groupAssignType : groupAssignType,
		commitLvlIdArr : commitLvlIdArr,
		alertTypeId : alertId,
		editionTypeId : editionId,
		districtId :districtId,
		alertStatusArr : globalAlertStatusArr,
		enrollementYearIds:globalEnrollementYearIdsForAlert
		
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
		
		
 function getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(groupAssignType,divId){
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
		var districtId = $("#districtSelectBoxId").val();
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,             
			scopeIdsArr : globalImpactScopeArr,              
			activityMemberId : globalActivityMemberId,
            resultType:groupAssignType,
			alertTypeId : alertId,
		    editionTypeId : editionId,
            districtId : districtId,			
            alertStatusArr : globalAlertStatusArr,
            enrollementYearIds:globalEnrollementYearIdsForAlert			
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
				getTotalAlertGroupByPubRepThenStatus(selectTypeId,designationId,divId,[0],"bellow");   
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
			getMemForPartyCommitDesg(commitTypeId,designationId,commitLvlIdArr,selectionType,divId);
			$("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			$(".expandIcon2").addClass("glyphicon-plus").removeClass("glyphicon-minus");   
			$(this).removeClass("glyphicon-plus").addClass("glyphicon-minus");
			$(".subElement_").hide();    
			$(this).closest("tr").next("tr.subElement_").show();    
		}             
	});
	function getMemForPartyCommitDesg(commitTypeId,designationId,commitLvlIdArr,selectionType,divId){
		
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var districtId = $("#districtSelectBoxId").val();
		var jsObj = { 
			stateId : globalStateId,               
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                
			scopeIdsArr : globalImpactScopeArr,              
			activityMemberId : globalActivityMemberId,
            commitTypeId : commitTypeId,
			designationId : designationId,
			commitLvlIdArr : commitLvlIdArr,
			alertTypeId : alertId,
		    editionTypeId : editionId,
			districtId : districtId,
			alertStatusArr : globalAlertStatusArr,
			enrollementYearIds:globalEnrollementYearIdsForAlert
	    			
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
		getAlertDtlsAssignedByPartyCommite(commitTypeId,designationId,commitLvlIdArr,cadreId,statusId);  
		
	});
	function getAlertDtlsAssignedByPartyCommite(commitTypeId,designationId,commitLvlIdArr,cadreId,statusId){
		
	   var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
	    var districtId = $("#districtSelectBoxId").val();
		var alertStatusArr = [];
		 if(statusId == 0){
			alertStatusArr= globalAlertStatusArr;
		 }else{
		     alertStatusArr.push(statusId);	 
		 }
		
		var jsObj = { 
			stateId : globalStateId,               
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                
			scopeIdsArr : globalImpactScopeArr,              
			activityMemberId : globalActivityMemberId,
            commitTypeId : commitTypeId,
			designationId : designationId,
			commitLvlIdArr : commitLvlIdArr,
			cadreId : cadreId,
			alertStatusArr : alertStatusArr,
			alertTypeId : alertId,
		    editionTypeId : editionId,
            districtId : districtId,
			enrollementYearIds:globalEnrollementYearIdsForAlert			
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
			getTotalAlertGroupByPubRepThenStatus("Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");
		}else if(commitLvlVal == "State"){
			 commitLvlIdArr.push(10);
			 getTotalAlertGroupByPubRepThenStatus("Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");
		}else if(commitLvlVal == "District"){
			commitLvlIdArr.push(11);
			getTotalAlertGroupByPubRepThenStatus("Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");
		}else if(commitLvlVal == "Mandal"){
			commitLvlIdArr.push(5);
			commitLvlIdArr.push(7);
			getTotalAlertGroupByPubRepThenStatus("Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");
		}else if(commitLvlVal == "Village"){
			commitLvlIdArr.push(6);
			commitLvlIdArr.push(8);    
			getTotalAlertGroupByPubRepThenStatus("Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");
		}else if(commitLvlVal == "Central"){
			commitLvlIdArr.push(12);    
			getTotalAlertGroupByPubRepThenStatus("Party Committee",0,"alertDetailsDivId",commitLvlIdArr,"");	
		}
	});
	function getTotalAlertGroupByPubRepThenStatusBellow(commitLvlIdArr,commitTypeId,divId,selectTypeId){
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var districtId = $("#districtSelectBoxId").val();
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                  
			scopeIdsArr : globalImpactScopeArr,              
			activityMemberId : globalActivityMemberId,
			commitLvlIdArr : commitLvlIdArr,
			designationId : commitTypeId,
			groupAssignType : selectTypeId,      
			position : "bellow",
		    alertTypeId : alertId,
		    editionTypeId : editionId,
			districtId : districtId,
			alertStatusArr : globalAlertStatusArr,
			enrollementYearIds:globalEnrollementYearIdsForAlert
			
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
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var alertStatusArr = [];
		if(statusId == 0){
		  alertStatusArr = globalAlertStatusArr;	
		}else{
		  alertStatusArr.push(statusId);	
		}
		var districtId = $("#districtSelectBoxId").val();
		
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                  
			scopeIdsArr : globalImpactScopeArr,              
			activityMemberId : globalActivityMemberId,
			publicRepresentativeTypeId : publicRepresentativeTypeId,
			cadreId : cadreId,
			alertStatusArr : alertStatusArr,
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
	 function getStateImpactLevelAlertStatusWise(){
		 $(".stateImpactLevelBlockCls").show();
		$("#stateImpactLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		$("#stateOverviewHeadingId").html("State overview - impact alerts");
		var jsObj={  
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate:			customStartDateAlert,        
			toDate :			customEndDateAlert,
			scopeIdsArr:		globalImpactScopeArr,
			alertIds : 			alertId,
			editionIds : 		editionId,
			alertStatusArr : globalAlertStatusArr,
			districtId : 0
		};
		$.ajax({
			type : 'GET',
			url : 'getStateOrGhmcImpactLevelAlertStatusWiseAction.action',  
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			globalStateLevelRslt = result;
			$("#stateImpactLevelHighChartDivId").html(' ');
			if(result !=null && result.statusList != null && result.statusList.length > 0){
			   buildStateImpactLevelHighChartStatusWiseRslt(result);	
			}else{
			  $(".stateImpactLevelBlockCls").hide();	
			}
		    
	  });	
	}
	
	function buildStateImpactLevelHighChartStatusWiseRslt(result){
			if(result !=null){
				var statusList = result.statusList;
			    var statusNameArr =[];
				var alertCnt = [];
				var count = [];
				var totalAlertCnt = result.alertCount;
					alertCnt.push({"y":totalAlertCnt,"extra":"0-"+"0-"+result.alertCount+"-StateImpactLevel-"+""});
					count.push({"y":parseInt(result.alertCount)-parseInt(result.alertCount),"extra":"0-"+"-0-"+result.alertCount+"-StateImpactLevel-"+"",color:"#D3D3D3"})
					statusNameArr.push("Total");
					for(var i in statusList){    
				   var uniqCnt = {};
					statusNameArr.push(statusList[i].name);
					alertCnt.push({"y":statusList[i].alertCount,"extra":statusList[i].id+"-0-"+statusList[i].alertCount+"-StateImpactLevel-"+statusList[i].name});
					var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(statusList[i].alertCount),"extra":statusList[i].id+"-0-"+statusList[i].alertCount+"-StateImpactLevel-"+statusList[i].name,color:"#D3D3D3"};
					count.push(uniqCnt);
					}
			       var getWidth = $("#stateOvervwGraph").width();
					$("#stateImpactLevelHighChartDivId").css("width",getWidth);	
				   $(function () {
					$('#stateImpactLevelHighChartDivId').highcharts({
						colors: ['#66728C','#A185BF','#0166FF','#32CCFE','#019966','#FF6600','#CC0001'],     
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
										if(this.value.toString() >=8){
											return this.value.toString().substring(0, 8)+'...';
										}else{
											return this.value;
										}
										
									},
									style: {
										fontSize: '11px',
										fontFamily: '"Lucida Grande","Lucida Sans Unicode",Arial,Helvetica,sans-serif',
										textTransform: "uppercase"
									}
								}
						},
						yAxis: {
							min: 0,
								   gridLineWidth: 0,
									minorGridLineWidth: 0,
							title: {
								text: ''
							},
							stackLabels: {
								enabled: true,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
								},
								formatter: function() {
								return  (this.total);
							},
							}
							
						},
					 	tooltip: {
							formatter: function () {
								var s = '<b>' + this.x + '</b>';

									$.each(this.points, function () {
									if(this.series.name != "Series 1")  
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
										this.y/* +' - ' +
										(Highcharts.numberFormat(this.percentage,1)+'%'); */
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
								series: {
								cursor: 'pointer',
								point: {
								events: {
									click: function () {
										var stateImpactInfo = (this.extra).split("-");
										 var alertStatusId = stateImpactInfo[0];
										var locationId = stateImpactInfo[1];
										var totalAlertCnt = stateImpactInfo[2];
										var impactLevel = stateImpactInfo[3];
										var alertStatus = stateImpactInfo[4];
										 if(totalAlertCnt == 0){
											return;  
										 }
										locationAlertDetails(alertStatusId,locationId,totalAlertCnt,impactLevel,alertStatus);
									}
								}
							}
						}
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
				$("#stateImpactLevelHighChartDivId").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
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
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var districtId = $("#districtSelectBoxId").val();
		var alertStatusArr = [];
		    if(statusId == 0){
			alertStatusArr = globalAlertStatusArr;
			}else{
			  alertStatusArr.push(statusId);	
			}
		    
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                  
			scopeIdsArr : globalImpactScopeArr,              
			activityMemberId : globalActivityMemberId,
			cadreId : cadreId,
			alertStatusArr : alertStatusArr,
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
	function buildDistrictWiseAlert(districtIdArr,totalAlertCnt,constituencyId,alertStatusId,locationLevel,locationType,isPublication,publicationValue){
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');   
		$("#alertCntTitId").html("TOTAL ALERTS - "+totalAlertCnt);        
		$("#tourDocumentId").modal("show");
		var scopeIdsArr = [];	
		 if(locationType != null && locationType=="Constituency"){
			 scopeIdsArr = globalConstituencyImpactScopeArr;
		 }else if(locationType=="District"){
			  scopeIdsArr = globalDistrictImpactLevelScopeArr;
		 }else if(locationType=="StateImpactLevel")	{
			 scopeIdsArr = globalImpactScopeArr;
		 }else if(locationType=="GHMCImpactLevel"){
			 var districtId = $("#districtSelectBoxId").val();
			 if(districtId > 0){
				districtIdArr.push(districtId); 
			 }
			  scopeIdsArr = globalCorpGhmcImpactScopeSArr; 
		 }
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionIds = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionIds == undefined){
			editionIds = 0;
		}
		var alertStatusArr =[];
		   if(alertStatusId == 0){
			   alertStatusArr = globalAlertStatusArr;
		   }else{
			 alertStatusArr.push(alertStatusId);   
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
			alertStatusArr : alertStatusArr,
			locationLevel : locationLevel,
			isPublication:isPublication,
			publicationId:publicationValue,
			locationElectionBodyId:0,
			type : "impactScopeWise"
		
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
								str+='<div class="col-md-6">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">LINKED ARTICLES</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
										     if( result.linkedList != null && result.linkedList.length > 1){
											str+='<div class="row">';
												for( var i in result.linkedList){
													if(result.linkedList[i].articleId !=articleId ){
														str+='<div class="col-md-4" style="margin-top:5px;">';
															str+='<img  class="thumbnail img-responsive linkedArticlesClickId" src="http://mytdp.com/NewsReaderImages/'+result.linkedList[i].imageURL+'" style="display:block;margin:auto;height:90px;cursor:pointer"/>';
														str+='</div>';
													}
												}
											str+='</div>';
											}else{
												str+="<h5> No Linked Articles Available </h5>";
											}
											
										str+='</div>';
									str+='</div>';
								str+='</div>';
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
	$(document).on("click","[alert_overview_col]",function(){
		var id = $(this).attr("alert_overview_col");
		$("[alert_overview_col_body]").hide();
		if($(this).html() == '+')
		{
			$("[alert_overview_col]").html("+");
			$(this).html("-")
			$("[alert_overview_col_body="+id+"]").show();
			
		}else{
			$(this).html("+")
			$("[alert_overview_col_body="+id+"]").hide();
		}
		
		
	});
	function setAlertLastUpdatedTime(lastUpdatedTime){
	 $("#lastAlertUpdatedTimeId").html(" Last Updated : "+lastUpdatedTime+"");
	}
	function getAlertOverviewDetails(){
		$("#alertTypeHiddenId").attr("attr_alert_id",0);
		$("#alertEditionTypeHiddenId").attr("attr_alert_edition_id",0);        
 		$("#alertOverview").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var dates=$("#dateRangeIdForAlert").val();
		
		var alertTypeStr = 0;          
		var alertEdition = 0;  
		var ImpactScopeArr = [];
		var alertStatusArr = [];
	 	var jsObj={
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate:			customStartDateAlert,        
			toDate :			customEndDateAlert,
			alertType : 		alertTypeStr,
			editionType:		alertEdition,
			scopeIdsArr : globalImpactScopeArr,
			alertStatusArr : globalAlertStatusArr
			
		};
		$.ajax({
			type : 'GET',
			url : 'getAlertOverviewDetailsAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			if(result != null){
			  buildAlertOverviewDetailsAction(result);
			  //buildAlertOverviewDetails(result,0,0)
			}else{
			  $("#alertOverview").html("NO DATA AVAILABLE.");	
			}
      });	
	}
	function getAlertOverviewDetailsNextLevel(){
		$("#alertTypeHiddenId").attr("attr_alert_id",0);
		$("#alertEditionTypeHiddenId").attr("attr_alert_edition_id",0);        
 		$("#alertOverviewDetails").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var dates=$("#dateRangeIdForAlert").val();
		
		var alertTypeStr = 1;          
		var alertEdition = 0;  
		var ImpactScopeArr = [];
		var alertStatusArr = [];
	 	var jsObj={
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate:			customStartDateAlert,        
			toDate :			customEndDateAlert,
			alertType : 		alertTypeStr,
			editionType:		alertEdition,
			scopeIdsArr : globalImpactScopeArr,
			alertStatusArr : globalAlertStatusArr
			
		};
		$.ajax({
			type : 'GET',
			url : 'getAlertOverviewDetailsAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			if(result != null){
			 
			  buildAlertOverviewDetails(result,0,1)
			}else{
			  $("#alertOverviewDetails").html("NO DATA AVAILABLE.");	
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
				   //typeId,count,type
				str+='<td colspan="2" onclick ="getEditioDtls(0,0);arrowPositionMove(overAllArrowPostion);" id="overAllArrowPostion" class=" alertOverViewDetailsCls" attr_alert_type_id="0" attr_edition_type_id="0">';
				str+='<div class=" alertInnerArrow" >';
					if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
						
						str+='<h3 class="alertColorFont">'+result.overAllVO.totalAlertCnt+'&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" class="glyphicon glyphicon-info-sign alertDetailsCls" onclick="alertDetails(0,'+result.overAllVO.totalAlertCnt+',\'TOTAL ALERTS\');" attr_alert_type_name="TOTAL ALERTS" attr_alert_count="'+result.overAllVO.totalAlertCnt+'" attr_alert_type="0" ></i>';
						str+='</h3>';
					
					}else{
						str+='<h3 class="alertColorFont">'+result.overAllVO.totalAlertCnt+'&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" class="glyphicon glyphicon-info-sign alertDetailsCls" onclick="alertDetails(0,'+result.overAllVO.totalAlertCnt+',\'TOTAL ALERTS\');" attr_alert_type_name="TOTAL ALERTS" attr_alert_count="'+result.overAllVO.totalAlertCnt+'" attr_alert_type="0" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details." aria-describedby="tooltip681435"></i>';
						str+='</h3>';
					}
				
				
				str+='<p>TOTAL ALERTS</p>';
				str+='</div>';
				str+='</td>';
			   }else{
				str+='<td colspan="2"><div class="alertsArrow alertInnerArrow"><h3 class="alertColorFont">'+result.overAllVO.totalAlertCnt+'</h3><p>TOTAL ALERTS</p></div></td>';   
			   }
				
				if(!(result.overAllVO.partyAlertCnt == 0)){
					var partyAlertPerc = ((parseInt(result.overAllVO.partyAlertCnt)*100)/parseInt(result.overAllVO.totalAlertCnt)).toFixed(2);
					
					str+='<td colspan="2" onclick ="getEditioDtls(1,0);arrowPositionMove(overAllPartyArrowPostion);" id="overAllPartyArrowPostion" class="alertOverViewDetailsCls alertsArrow" attr_alert_type_id="1" attr_edition_type_id="0">';
					str+='<div class="alertInnerArrow alertsArrow" >';
					if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
						str+='<h3 class="alertColorFont">'+result.overAllVO.partyAlertCnt+'&nbsp<small>('+partyAlertPerc+'%)</small>&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" class="glyphicon glyphicon-info-sign alertDetailsCls" onclick="alertDetails(1,'+result.overAllVO.partyAlertCnt+',\'PARTY ALERTS\');" attr_alert_type_name="PARTY ALERTS" attr_alert_count="'+result.overAllVO.partyAlertCnt+'" attr_alert_type="1" ></i>';
						str+='</h3>';
					}else{
						str+='<h3 class="alertColorFont">'+result.overAllVO.partyAlertCnt+'&nbsp<small>('+partyAlertPerc+'%)</small>&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" class="glyphicon glyphicon-info-sign alertDetailsCls" onclick="alertDetails(1,'+result.overAllVO.partyAlertCnt+',\'PARTY ALERTS\');" attr_alert_type_name="PARTY ALERTS" attr_alert_count="'+result.overAllVO.partyAlertCnt+'" attr_alert_type="1" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details." aria-describedby="tooltip681435"></i>';
						str+='</h3>';
					}
					
					
					str+='<p>PARTY</p>';
					str+='</div>';
					str+='</td>';
				}
				/* if(!(result.overAllVO.govtAlertCnt == 0)){
					var govtAlertPerc = ((parseInt(result.overAllVO.govtAlertCnt)*100)/parseInt(result.overAllVO.totalAlertCnt)).toFixed(2);
					
					str+='<td colspan="2" onclick ="getEditioDtls(2,0);arrowPositionMove(overAllgovtArrowPostion);" id="overAllgovtArrowPostion" class="alertOverViewDetailsCls " attr_alert_type_id="2" attr_edition_type_id="0">';
					str+='<div class="alertInnerArrow " >';
					if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
						str+='<h3 class="alertColorFont">'+result.overAllVO.govtAlertCnt+'&nbsp<small>('+govtAlertPerc+'%)</small>&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" class="glyphicon glyphicon-info-sign alertDetailsCls" onclick="alertDetails(2,'+result.overAllVO.govtAlertCnt+',\'GOVT ALERTS\');"  attr_alert_count="'+result.overAllVO.govtAlertCnt+'" attr_alert_type_name="GOVT ALERTS" attr_alert_type="2" ></i>';
						str+='</h3>';
					}else{
						str+='<h3 class="alertColorFont">'+result.overAllVO.govtAlertCnt+'&nbsp<small>('+govtAlertPerc+'%)</small>&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" class="glyphicon glyphicon-info-sign alertDetailsCls" onclick="alertDetails(2,'+result.overAllVO.govtAlertCnt+',\'GOVT ALERTS\');"  attr_alert_count="'+result.overAllVO.govtAlertCnt+'" attr_alert_type_name="GOVT ALERTS" attr_alert_type="2" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details." aria-describedby="tooltip681435"></i>';
						str+='</h3>';
					}
					
					
					str+='<p>GOVT</p>';
					str+='</div></td>';
				} */
				if(!(result.overAllVO.otherAlertCnt == 0)){
					var otherAlertPerc = ((parseInt(result.overAllVO.otherAlertCnt)*100)/parseInt(result.overAllVO.totalAlertCnt)).toFixed(2);
					
				str+='<td colspan="2" onclick ="getEditioDtls(3,0);arrowPositionMove(overAllotherdArrowPostion);" id="overAllotherdArrowPostion"  class="alertOverViewDetailsCls " 	attr_alert_type_id="3" attr_edition_type_id="0">';
				str+='<div class="alertInnerArrow " >';
				if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
					str+='<h3 class="alertColorFont">'+result.overAllVO.otherAlertCnt+'&nbsp<small>('+otherAlertPerc+'%)</small>&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" attr_alert_type_name="OTHERS ALERTS" class="glyphicon glyphicon-info-sign alertDetailsCls" onclick="alertDetails(3,'+result.overAllVO.otherAlertCnt+',\'OTHERS ALERTS\');"   attr_alert_count="'+result.overAllVO.otherAlertCnt+'" attr_alert_type="3" ></i>';
					str+='</h3>';
				}else{
					str+='<h3 class="alertColorFont">'+result.overAllVO.otherAlertCnt+'&nbsp<small>('+otherAlertPerc+'%)</small>&nbsp&nbsp<i style="cursor: pointer; font-size: 16px; margin-top: 10px;color:#524C4C;" attr_alert_type_name="OTHERS ALERTS" class="glyphicon glyphicon-info-sign alertDetailsCls" onclick="alertDetails(3,'+result.overAllVO.otherAlertCnt+',\'OTHERS ALERTS\');"   attr_alert_count="'+result.overAllVO.otherAlertCnt+'" attr_alert_type="3" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details." aria-describedby="tooltip681435"></i>';
					str+='</h3>';
				}
				
				
				str+='<p>OTHERS</p>';
				str+='</div></td>';
				}
			str+='</tr>';
			str+='<tr>';
			   if(totalMainCount > 0){
				str+='<td onclick ="arrowPositionMove1(overAllArrowPostionLow);" id="overAllArrowPostionLow"  class="alertOverViewDetailsLowCls" >';
				str+='<div class="alertInnerArrowLow">';
				if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
					str+='<p style="font-size:13px;" class="text-muted text-capital">Main<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="1" attr_category_id="0" attr_status_id="0" attr_count="'+totalMainCount+'" attr_alert_type_id="0" ></i>';
					str+='</p>';
				}else{
					str+='<p style="font-size:13px;" class="text-muted text-capital">Main<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="1" attr_category_id="0" attr_status_id="0" attr_count="'+totalMainCount+'" attr_alert_type_id="0" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';
					str+='</p>';
				}
				
				
				str+='<p style="font-size:13px;cursor:pointer;"  onclick ="getEditioDtls(0,1);" attr_alert_type_id="0" attr_edition_type_id="1"><span class="alertColorFont">'+totalMainCount+'</span></p></div></td>';
		       }else{
			    str+='<td  id="overAllArrowPostionLow" class="alertOverViewDetailsLowCls" ><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted text-capital">Main</p>';
				str+='<p style="font-size:13px;"><span class="alertColorFont">0</span></p></div></td>';
	  	       }
				
				if(totalDistCount > 0){
				str+='<td onclick ="arrowPositionMove1(overAllMainArrowPostionLow);" id="overAllMainArrowPostionLow" class="alertOverViewDetailsLowCls" >';
				str+='<div class="alertInnerArrowLow">';
				if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
					str+='<p style="font-size:13px;" class="text-muted text-capital">District<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="2" attr_category_id="0" attr_status_id="0" attr_count="'+totalDistCount+'" attr_alert_type_id="0" ></i>';
					str+='</p>';
				}else{
					str+='<p style="font-size:13px;" class="text-muted text-capital">District<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="2" attr_category_id="0" attr_status_id="0" attr_count="'+totalDistCount+'" attr_alert_type_id="0" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';
					str+='</p>';
				}
				
				
				str+='<p style="font-size:13px;cursor:pointer;" onclick="getEditioDtls(0,2);" attr_alert_type_id="0" attr_edition_type_id="2"><span class="alertColorFont">'+totalDistCount+'</span></p></div></td>';
				}else{
				str+='<td class="alertOverViewDetailsLowCls" ><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted text-capital">District</p>';
				str+='<p style="font-size:13px;"><span class="alertColorFont">0</span></p></div></td>';
				}
				
				if(!(result.overAllVO.partyAlertCnt == 0)){
					
					str+='<td class="alertOverViewDetailsLowCls" onclick="arrowPositionMove1(overAlltotalpartyArrowPostion)" id="overAlltotalpartyArrowPostion" ><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted text-capital">'+result.totalPartyList[0].edition+''
					if(result.totalPartyList[0].editionCnt > 0){
						if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalPartyList[0].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalPartyList[0].editionCnt+'" attr_alert_type_id="'+result.totalPartyList[0].alertTypeId+'" ></i>';	
						}else{
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalPartyList[0].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalPartyList[0].editionCnt+'" attr_alert_type_id="'+result.totalPartyList[0].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';	
						}
					
					}
					str+='</p>';
					if(result.totalPartyList[0].editionCnt == 0){
						str+='<p style="font-size:13px;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalPartyList[0].alertTypeId+','+result.totalPartyList[0].editionId+');" style="font-size:13px;cursor:pointer;" class="" attr_alert_type_id="'+result.totalPartyList[0].alertTypeId+'" attr_edition_type_id="'+result.totalPartyList[0].editionId+'"><span class="alertColorFont">'+result.totalPartyList[0].editionCnt+'</span></p></div></td>';
					}
					
					
					str+='<td class="alertOverViewDetailsLowCls" onclick="arrowPositionMove1(overAlltotalpartyEdArrowPostion)" id="overAlltotalpartyEdArrowPostion"><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted text-capital">'+result.totalPartyList[1].edition+''
					if(result.totalPartyList[1].editionCnt > 0){
						if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalPartyList[1].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalPartyList[1].editionCnt+'" attr_alert_type_id="'+result.totalPartyList[1].alertTypeId+'" ></i>';
						}else{
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalPartyList[1].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalPartyList[1].editionCnt+'" attr_alert_type_id="'+result.totalPartyList[1].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';
						}
					
					}
					str+='</p>';
					if(result.totalPartyList[1].editionCnt == 0){  
						str+='<p style="font-size:13px;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalPartyList[1].alertTypeId+','+result.totalPartyList[1].editionId+');" style="font-size:13px;cursor:pointer;" class="" attr_alert_type_id="'+result.totalPartyList[1].alertTypeId+'" attr_edition_type_id="'+result.totalPartyList[1].editionId+'"><span class="alertColorFont">'+result.totalPartyList[1].editionCnt+'</span></p></div></td>';
					}
					
				}
				/* if(!(result.overAllVO.govtAlertCnt == 0)){
					str+='<td class="alertOverViewDetailsLowCls" onclick="arrowPositionMove1(overAlltotalgovtEdArrowPostion)" id="overAlltotalgovtEdArrowPostion"><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted text-capital">'+result.totalGovtList[0].edition+''
					if(result.totalGovtList[0].editionCnt > 0){
						if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
							 str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalGovtList[0].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalGovtList[0].editionCnt+'" attr_alert_type_id="'+result.totalGovtList[0].alertTypeId+'" ></i>';
						}else{
							 str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalGovtList[0].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalGovtList[0].editionCnt+'" attr_alert_type_id="'+result.totalGovtList[0].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';
						}
					
					}
					str+='</p>';    
					if(result.totalGovtList[0].editionCnt == 0){
						str+='<p style="font-size:13px;cursor:pointer;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalGovtList[0].alertTypeId+','+result.totalGovtList[0].editionId+');" style="font-size:13px;cursor:pointer;" class="" attr_alert_type_id="'+result.totalGovtList[0].alertTypeId+'" attr_edition_type_id="'+result.totalGovtList[0].editionId+'"><span class="alertColorFont">'+result.totalGovtList[0].editionCnt+'</span></p></div></td>';
					}
					
					str+='<td class="alertOverViewDetailsLowCls " onclick="arrowPositionMove1(overAlltotalgovtEd1ArrowPostion)" id="overAlltotalgovtEd1ArrowPostion"><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted text-capital">'+result.totalGovtList[1].edition+''
					if(result.totalGovtList[1].editionCnt > 0){
						if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalGovtList[1].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalGovtList[1].editionCnt+'" attr_alert_type_id="'+result.totalGovtList[1].alertTypeId+'" ></i>';
						}else{
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalGovtList[1].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalGovtList[1].editionCnt+'" attr_alert_type_id="'+result.totalGovtList[1].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';
						}
						
					}
					str+='</p>';
					if(result.totalGovtList[1].editionCnt == 0){
						str+='<p style="font-size:13px;cursor:pointer;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalGovtList[1].alertTypeId+','+result.totalGovtList[1].editionId+');" style="font-size:13px;cursor:pointer;" class="" attr_alert_type_id="'+result.totalGovtList[1].alertTypeId+'" attr_edition_type_id="'+result.totalGovtList[1].editionId+'"><span class="alertColorFont">'+result.totalGovtList[1].editionCnt+'</span></p></div></td>';
					}
					
				} */
				if(!(result.overAllVO.otherAlertCnt == 0)){
					str+='<td class="alertOverViewDetailsLowCls" onclick="arrowPositionMove1(overAlltotalothersEd1ArrowPostion)" id="overAlltotalothersEd1ArrowPostion"><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted text-capital">'+result.totalOtherList[0].edition+''
					if(result.totalOtherList[0].editionCnt > 0){
						if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalOtherList[0].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalOtherList[0].editionCnt+'" attr_alert_type_id="'+result.totalOtherList[0].alertTypeId+'" ></i>';	
						}else{
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalOtherList[0].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalOtherList[0].editionCnt+'" attr_alert_type_id="'+result.totalOtherList[0].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';	
						}
					
					}
					str+='</p>';
					if(result.totalOtherList[0].editionCnt == 0){
						str+='<p style="font-size:13px;cursor:pointer;">0</p></td>';
					}else{
						str+='<p onclick ="getEditioDtls('+result.totalOtherList[0].alertTypeId+','+result.totalOtherList[0].editionId+');" style="font-size:13px;cursor:pointer;"  attr_alert_type_id="'+result.totalOtherList[0].alertTypeId+'" attr_edition_type_id="'+result.totalOtherList[0].editionId+'"><span class="alertColorFont">'+result.totalOtherList[0].editionCnt+'</span></p></div></td>';
					}
					
					str+='<td class="alertOverViewDetailsLowCls1" onclick="arrowPositionMove1(overAlltotalothersEd2ArrowPostion);" id="overAlltotalothersEd2ArrowPostion"><div class="alertInnerArrowLow"><p style="font-size:13px;" class="text-muted text-capital">'+result.totalOtherList[1].edition+''
					if(result.totalOtherList[1].editionCnt > 0){
						if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalOtherList[1].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalOtherList[1].editionCnt+'" attr_alert_type_id="'+result.totalOtherList[1].alertTypeId+'" ></i>';	
						}else{
							str+='<i style="cursor: pointer; font-size: 12px; margin-top: 10px;color:#524C4C;margin-left:5px;" class="glyphicon glyphicon-info-sign alertDtlsCls" attr_edition_id="'+result.totalOtherList[1].editionId+'" attr_category_id="0" attr_status_id="0" attr_count="'+result.totalOtherList[1].editionCnt+'" attr_alert_type_id="'+result.totalOtherList[1].alertTypeId+'" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get alert details."></i>';	
						}
					
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
					str+='<div class="row alertsHideBody" style="box-shadow:none;">';
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
												if(result.statusList[i].statusType.length > 10)
												{
													str+='<p title="'+result.statusList[i].statusType+'" class="alertStatusTooltip">'+result.statusList[i].statusType.substring(0,10)+'..&nbsp;&nbsp;</p>';
												}else{
													str+='<p>'+result.statusList[i].statusType+'&nbsp;&nbsp;</p>';
												}
												
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
					str+='<p class="text-center alertsShowBody" style="text-decoration:underline">Click to more</p>';
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
								str+='<h4 class="panel-title text-capital " style="cursor:pointer;" attr_edition_id="'+alertEdition+'" attr_category_id="'+result.categoryList[i].statusTypeId+'" attr_status_id="0" attr_count="'+result.categoryList[i].statusCnt+'" attr_alert_type_id="'+alertTypeId+'">'+result.categoryList[i].statusType+' - <span class="alertColorFont alertDtlsCls">'+result.categoryList[i].statusCnt+'</span><span class="pull-right" alert_overview_col="alertOverview'+result.categoryList[i].statusTypeId+'" style="cursor: pointer;padding: 0px 5px;font-size: 15px;border: 1px solid #333;">+</span></h4>';
							str+='</div>';
							if(alertEdition == 0 && result.categoryList[i].statusTypeId == 2){//printmedia      
								str+='<div class="col-md-12 col-xs-12 col-sm-12" style="display:none" alert_overview_col_body="alertOverview'+result.categoryList[i].statusTypeId+'">';
									
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
							str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10" style="display:none" alert_overview_col_body="alertOverview'+result.categoryList[i].statusTypeId+'">';
								str+='<div class="pad_5 bg_ED">';
								if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
									
										str+='<div class="table-responsive">';
									
								}else{
									//if($(window).width() < 800)
									//{
										str+='<div class="table-responsive">';
									//}
								}  
									
									str+='<table class="table">';
										str+='<tr>';
										for(var j in result.categoryList[i].statusList)
										{
										   
											str+='<td>';
												str+='<p class="text-muted">'+result.categoryList[i].statusList[j].statusType+'</p>';
											str+='</td>';
										}
										str+='</tr>';   
										str+='<tr>';
										for(var j in result.categoryList[i].statusList)
										{
										
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
										//if($(window).width() < 800)
										//{
											str+='</div>';
										//}
									}
									
								str+='</div>';
							str+='</div>';
						str+='</div>';
					}
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#alertOverviewDetails").html(str);
		$(".alertStatusTooltip").tooltip();
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
		  $("#alertOverviewDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		  
		$("#alertTypeHiddenId").attr("attr_alert_id",alertTypeStr);
		$("#alertEditionTypeHiddenId").attr("attr_alert_edition_id",alertEdition);  //undefined  
		 var districtId = $("#districtSelectBoxId").val();
	   	if($(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			getAlertCategoryDtlsLocationWise($("#alertTypeHiddenId").attr("attr_alert_id"),$("#alertEditionTypeHiddenId").attr("attr_alert_edition_id"));
			getAssignGroupTypeAlertDtlsByImpactLevelWise(districtId);
			$("#alertOverviewDetails").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		  var locVal ='';
			$( "li.optionsCls" ).each(function() {
				if($( this ).hasClass( "active" )){
					locVal = $(this).attr("attr_id");
				}
			});
			 // Make Highcharts block active in selected area */
			 $(".collapseTblViewCls").removeClass("active");
		     $(".collapseHIghChartViewCls").addClass("active");
			if(locVal == "1"){  
			    $(".locImptLevelDivCls").show();
				$(".impactLevelCls").attr("attr_level","Overview");
			    $("#locationAlertsId").prop("checked",false);
                $("#impactAlertsId").prop("checked",true);	
                $("#hiddenLevelTypeId").attr("attr_level_type","impactScopeWise");				
				getStateImpactandItsSubLevelAlert("impactScopeWise");
				getDistrictImpactandItsSubLevelAlert("Decending","0","impactScopeWise");
				getCorpGMCAlert("0","impactScopeWise");
				getConstituencyImpactandItsSubLevelAlert("Decending","0","0","impactScopeWise");
				getAssignGroupTypeAlertDtlsByImpactLevelWise(districtId);
			}else if(locVal == "2"){
				 $(".impactLevelCls").attr("attr_level","Status");
				 getStateImpactLevelAlertStatusWise();
				 getTotalAlertGroupByLocationThenStatus("Decending","0");
				 getGhmcImpactLevelAlertStatusWise("0");
				 getConstituencyAlertStatusWise("Decending","0","0");
				 getAssignGroupTypeAlertDtlsByImpactLevelWise(districtId);
			}else if(locVal == "3"){
				$(".impactLevelCls").attr("attr_level","Publication");
				getStateImpcatLevelAlertCntPublicationWise();
				getCorpGHMCImpcatLevelAlertCntPublicationWise("0");
				getDistrictWisePublicationAlert("Decending","0");
				getConstituencyWisePublicationAlert("Decending","0","0");
				getAssignGroupTypeAlertDtlsByImpactLevelWise(districtId);
			}else if(locVal == "4") {
				  $("#alertChildActivityMemberDivId").html('');
				  $("#childUserTypeDetailsDivForAlerts").html('');
				  $("#candidateLocationAlertDtlsStatusWiseDivId").html('');
				  $(".alertImpctLevelBlcock").hide();	
				  $(".alertComparisonblock").show();
				  getAllItsSubUserTypeIdsByParentUserTypeIdForAlert();
			}
		}
		      
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			fromDate:customStartDateAlert,        
			toDate :customEndDateAlert,
			alertType : alertTypeStr,
			editionType : alertEdition,
			alertStatusArr : globalAlertStatusArr,
			scopeIdsArr : globalImpactScopeArr
		};
		$.ajax({
			type : 'GET',
			url : 'getAlertOverviewDetailsAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			$("#alertOverviewDetails").html('');
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
	function alertDetails(typeId,count,type)
	{
		var alertTypeId = typeId;
		var alertCount = count;
		var selectedAlertType = type;
		getAlertDetatilsByAlertType(alertTypeId,alertCount,selectedAlertType,globalImpactScopeArr,0,"impactScopeWise");
	}
	
	function getImpactLevelWiseAlertDtls(impactLevelId,totalAlertCnt,selectionType){
		
		var impactScopArr = [];
		if(selectionType != null && selectionType=="impactScopeWise"){
			if(impactLevelId == 5){
			  impactScopArr.push(5);
			  impactScopArr.push(12);
			}else if(impactLevelId == 7){
				impactScopArr.push(6);
				impactScopArr.push(7);
				impactScopArr.push(9);
			}else if(impactLevelId == 0){
				impactScopArr = globalImpactScopeArr;
			}else{
			   impactScopArr.push(impactLevelId);
			}
		}else if(selectionType=="locationWise"){
				if(impactLevelId == 5){
			  impactScopArr.push(5);
			  impactScopArr.push(7);
			}else if(impactLevelId == 6){
				impactScopArr.push(6);
				impactScopArr.push(8);
				impactScopArr.push(11);
			}else if(impactLevelId == 0){
				impactScopArr = [2,3,4,5,6,7,8,11];
			}else{
			   impactScopArr.push(impactLevelId);
			}
		}
		
		var alertTypeId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertTypeId == undefined){
			alertTypeId = 0;
		}
		var editionIds = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionIds == undefined){
			editionIds = 0;
		}
		var impactLevel ="";
		if(impactLevelId == 0){
		 impactLevel = "Total Alerts";	
		}else{
		 impactLevel = impactLevelObj[impactLevelId]+" Impact Alerts";	
		}
		
		getAlertDetatilsByAlertType(alertTypeId,totalAlertCnt,impactLevel.toUpperCase(),impactScopArr,editionIds,selectionType);
	}
	function getAlertDetatilsByAlertType(alertTypeId,alertCount,selectedAlertType,impactScopArr,editionIds,selectionType){
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");  
		$("#alertCntTitId").html(""+selectedAlertType+"-"+alertCount);
			
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			fromDate:customStartDateAlert,        
			toDate :customEndDateAlert,
			alertTypeId : alertTypeId,
			alertStatusArr : globalAlertStatusArr,
			scopeIdsArr : impactScopArr,
			editionId : editionIds,
			type : selectionType
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
	/* function getDistrictListByStateId(globalActivityMemberId,globalUserTypeId){
		
		var alertTypeId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertTypeId == undefined){
			alertTypeId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var jsObj = { 
			stateId :globalStateId,
            activityMemberId:globalActivityMemberId,
			userTypeId:globalUserTypeId,
			fromDate : customStartDateAlert,      
			toDate : customEndDateAlert,
			alertTypeId:alertTypeId,
			editionId:editionId,
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
	} */
	function locationAlertDetails(alertStatusId,locationIdStr,totalAlertCnt,loctionType,locationLevel){
		var locationLevel = locationLevel;
		var districtIdArr =[];
		var constituencyId = 0;
		 if(loctionType=="Constituency"){
			constituencyId = locationIdStr;  
		}else if(loctionType=="District"){
		   districtIdArr = locationIdStr.split(",");	
		} 
		buildDistrictWiseAlert(districtIdArr,totalAlertCnt,constituencyId,alertStatusId,locationLevel,loctionType,"false",0);
	}
	
	function getLocationWiseAlertDtls(locationIdStr,totalAlertCnt,loctionType,impactLevelId,selectionType){
		var locationLevel = "";
		var districtIdArr =[];
		var constituencyId = 0;
		var locationLevel = "";
		var locationElectionBodyId = 0;
		 if(loctionType=="Constituency"){
			constituencyId = locationIdStr;  
		}else if(loctionType=="District"){
			districtIdArr = locationIdStr.split(",");	
		}else if(loctionType == "localElectionBody"){
			if(locationIdStr ==0){
				var districtId = $("#districtSelectBoxId").val();
				   if(districtId > 0){
					 districtIdArr.push(districtId);  
				   }
				   locationLevel="OtherLocations";
			}
			locationElectionBodyId = locationIdStr;
		} 
	
		var impactScopArr = [];
		if(selectionType != null && selectionType=="impactScopeWise"){
			if(impactLevelId == 5){
				impactScopArr.push(5);
				impactScopArr.push(12);
			}else if(impactLevelId == 7){
				impactScopArr.push(6);
				impactScopArr.push(7);
				impactScopArr.push(9);
			}else if(impactLevelId == 0){
				if(loctionType=="Constituency"){
					impactScopArr = globalConstituencyImpactScopeArr;
				}else if(loctionType=="District"){
					impactScopArr = globalDistrictImpactLevelScopeArr;
				}
			}else{
			   impactScopArr.push(impactLevelId);
			}
		}else if( selectionType=="locationWise"){
			if(impactLevelId == 5){
				impactScopArr.push(5);
				impactScopArr.push(7);
			}else if(impactLevelId == 6){
				impactScopArr.push(6);
				impactScopArr.push(8);
				impactScopArr.push(11);
			}else if(impactLevelId == 0){
				if(loctionType=="Constituency"){ 
					impactScopArr = [4,5,6,7,8,11];
				}else if(loctionType=="District"){
					impactScopArr = [3,4,5,6,7,8,11];
				}
			}else{
			   impactScopArr.push(impactLevelId);
			}
		}
		
		var impactLevel ="";
		if(impactLevelId == 0){
		 impactLevel = "Total Alerts";	
		}else{
		 impactLevel = impactLevelObj[impactLevelId]+" Impact Alerts";	
		}
		
		getLcatnWiseAlertDtls(locationLevel,impactLevel.toUpperCase(),districtIdArr,totalAlertCnt,constituencyId,impactScopArr,locationElectionBodyId,selectionType);
	}

	function getLcatnWiseAlertDtls(locationLevel,impactLevel,districtIdArr,totalAlertCnt,constituencyId,impactScopArr,locationElectionBodyId,selectionType){
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');   
		$("#alertCntTitId").html(impactLevel+" - "+totalAlertCnt);        
		$("#tourDocumentId").modal("show");
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionIds = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionIds == undefined){
			editionIds = 0;
		}
		//var alertStatusArr = [];
		var jsObj = { 
			stateId : globalStateId,             
			fromDate : customStartDateAlert,        
			toDate : customEndDateAlert,                  
			scopeIdsArr : impactScopArr,              
			activityMemberId : globalActivityMemberId,
			districtIdArr : districtIdArr,
			catId : 0,
			alertTypeId : alertId,
			editionId : editionIds,
			constituencyId:constituencyId,
			alertStatusArr : globalAlertStatusArr,
			locationLevel : locationLevel,
			isPublication:false,
			publicationId:0,
			locationElectionBodyId:locationElectionBodyId,
			type : selectionType
			
		
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
	function getConstituencyAlertStatusWise(sortingType,constituencyId,districtId){
		$(".constituencyImpactLevelBlockCls").show();	
		if(globalConstituencyImpactScopeArr == null || globalConstituencyImpactScopeArr.length == 0){
			$(".constituencyImpactLevelBlockCls").hide();
			return;
		}
		$("#constituencyLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		$("#constituencyOverviewHeadingId").html("Constituency overview - impact alerts");
		var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,      
			toDate : 			customEndDateAlert,  
			scopeIdsArr : 		globalConstituencyImpactScopeArr,              
			activityMemberId : 	globalActivityMemberId,       
			group : 			"",
			alertIds : 			alertId,
			editionIds : 		editionId,
			filterType : 	"Constituency",
			locationValue : constituencyId,
			alertStatusArr : globalAlertStatusArr,
			sortingType : sortingType,
			districtId : districtId
			
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getTotalAlertGroupByLocationThenStatusAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			globalConstituencyLevelRlst = result;
			$("#constituencyLevelHighChartDivId").html(' ');
			if(result != null && result.length > 0){  
				buildDistrictOrConstituencyImpactLevelHighChartRsltStatusWise(result,"constituencyLevelHighChartDivId",constituencyId,"Constituency"); 
			}else{
				$(".constituencyImpactLevelBlockCls").hide();
			}
		});  
	}
	function buildDistrictOrConstituencyLevelRlstInTabularFormatStatusWise(result,locationType,divId){
	 var str='';
	 // if($(window).width() < 800)
	//	{
			str+='<div class="table-responsive">';
		//}
		  if(divId=="constituencyLevelTblDivId"){
		   str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed table-bordered text_align_center" id="constituencyAlertDataTblId">'; 
	     }else if(divId=="districtImpactLevelTblDivId"){
		   str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed table-bordered text_align_center" id="districtAlertDataTblId">';  
	     }
		  str+='<thead>';
		       str+='<th></th>';
			   str+='<th>Total</th>';
		       if(result != null && result.length > 0){
				    if(result[0].subList1 != null && result[0].subList1.length > 0){
						 for(var k in result[0].subList1){
							str+='<th>'+result[0].subList1[k].statusType+'</th>';		 
						 }
					}
			   }
	   str+='</thead>';
		 str+='<tbody>';
		  for(var i in result){
				str+='<tr>';
				str+='<td>'+result[i].name+'</td>';
				 if(result[i].totalAlertCnt > 0){
					  str+='<td  style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" class="locationAlertCls" attr_location_level="'+result[i].name+'" attr_location_type='+locationType+' attr_alert_count='+result[i].totalAlertCnt+' onClick="locationAlertDetails(0,\''+result[i].id+'\',\''+result[i].totalAlertCnt+'\',\''+locationType+'\',\''+result[i].name+'\');" attr_location_id = '+result[i].statusTypeId+' attr_alert_status_id="0">'+result[i].totalAlertCnt+'</td>'; 
				 }else{
				     str+='<td class="text-center">-</td>';      		
				 } 
				if(result[i].subList1 != null && result[i].subList1.length > 0){
					for(var j in result[i].subList1){
							if(result[i].subList1[j].totalAlertCnt > 0){
								str+='<td  style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" class="locationAlertCls" onClick="locationAlertDetails(\''+result[i].subList1[j].statusTypeId+'\',\''+result[i].id+'\',\''+result[i].subList1[j].totalAlertCnt+'\',\''+locationType+'\',\''+result[i].name+'\');" attr_location_type='+locationType+' attr_alert_count='+result[i].subList1[j].totalAlertCnt+' attr_location_level="'+result[i].name+'" attr_location_id = '+result[i].id+' attr_alert_status_id='+result[i].subList1[j].statusTypeId+'>'+result[i].subList1[j].totalAlertCnt+'</td>';      	
							}else{
								str+='<td class="text-center">-</td>';      	
							} 
					 }	
				}
				str+='</tr>';
			}
			 str+='</tbody>';
			 str+='</table>';
	      $("#"+divId).html(str);
		 // if($(window).width() < 800)
		//{
			str+='</div>';
		//}
		if(divId=="constituencyLevelTblDivId"){
			 $("#constituencyAlertDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 15,
			"aLengthMenu": [[10,15,20,30,50, 100, -1], [10,15,20,30,50, 100, "All"]]			
		   });   	
		}else if(divId == "districtImpactLevelTblDivId"){
			 $("#districtAlertDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 15,
			"aLengthMenu": [[10,15,20,30,50, 100, -1], [10,15,20,30,50, 100, "All"]]
		   });   	
		   }
		}
	function getDistrictWisePublicationAlert(sortingType,districtId){
		/*Hiding Block if impact Level is not selected*/
		 $(".districtImpactLevelBlockCls").show();	
		if(globalDistrictImpactLevelScopeArr == null || globalDistrictImpactLevelScopeArr.length == 0){
			$(".districtImpactLevelBlockCls").hide();
			return;
		}
		 $("#districtOverviewHeadingId").html("District overview - impact alerts");
		$("#districtImpactLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
			scopeIdsArr : 		globalDistrictImpactLevelScopeArr,              
			activityMemberId : 	globalActivityMemberId,       
			alertIds : 			alertId,
			editionIds : 		editionId,
			filterType : 	"District",
			locationValue : districtId,
			alertStatusArr : globalAlertStatusArr,
			sortingType : sortingType,
			disctrictId : 0
			
			
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getPublicationWiseAlertAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			globalDistrictLevelRlst = result;
			$("#districtImpactLevelHighChartDivId").html('');
			if(result != null && result.length > 0){
			 buildDistrictOrConstituencyImpactLevelHighChartRsltPublicationWise(result,"districtImpactLevelHighChartDivId",districtId,"District");	
			}else{
			 $(".districtImpactLevelBlockCls").hide();		
			}
		});  
	}
	
	function getConstituencyWisePublicationAlert(sortingType,constituencyId,districtId){
		 $(".constituencyImpactLevelBlockCls").show();	
		if(globalConstituencyImpactScopeArr == null || globalConstituencyImpactScopeArr.length == 0){
			$(".constituencyImpactLevelBlockCls").hide();
			return;
		}
		 $("#constituencyOverviewHeadingId").html("Constituency overview - impact alerts");
		$("#constituencyLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
    	var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
            var districtArr=[];
			var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,      
			toDate : 			customEndDateAlert,  
			scopeIdsArr : 		globalConstituencyImpactScopeArr,              
			activityMemberId : 	globalActivityMemberId,       
			alertIds : 			alertId,
			editionIds : 		editionId,
			filterType : 	"Constituency",
			locationValue : constituencyId,
			alertStatusId : 0,
			alertStatusArr : globalAlertStatusArr,
			sortingType : sortingType,
			disctrictId : districtId
			
			
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getPublicationWiseAlertAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			globalConstituencyLevelRlst = result;
			$("#constituencyLevelHighChartDivId").html('');
			if(result != null && result.length > 0){
			   buildDistrictOrConstituencyImpactLevelHighChartRsltPublicationWise(result,"constituencyLevelHighChartDivId",constituencyId,"Constituency");	
			}else{
			   $(".constituencyImpactLevelBlockCls").hide();
			}
		});  
	}
	 function buildDistrictOrConstituencyImpactLevelHighChartRsltPublicationWise(result,divId,locationValue,locationType){
		   
		if(result != null && result.length > 10){
			var highChartDivHight = result.length*40;
			$("#"+divId).height(highChartDivHight);
		 }else{
			$("#"+divId).height(400);		
		 }
		 if(result != null && result.length > 0){
			 var locationName = [];
			 var str = '';
			 if(divId == "districtImpactLevelHighChartDivId"){
				  //str+='<option value="-1">Select District</option>';	 
			      str+='<option value="0">All</option>';	 
			 }else{
				  // str+='<option value="-1">Select Constituency</option>';
				   str+='<option value="0">All</option>';
			    
			 }
			 
			 for(var i in result){
				 locationName.push(result[i].name);
				 str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			 }
			     if(locationValue == 0){ // Building district for searching first time only
				  if(divId=="districtImpactLevelHighChartDivId"){
					  $("#districtSelectBoxId").val(' ');
					  $("#districtSelectBoxId").html(str);
				  }else{
					  $("#constituencySeletBoxId").val(' ');
					  $("#constituencySeletBoxId").html(str);  
				  }
			    }
             	var andhraBhoomiAlertArr = []; //Appending value 2
				var andhraJyothiAlertArr = [];
				var eenaaduAlertArr = [];
				var vaarthaAlertArr = [];
				var vishalandhraAlertArr= [];
				var prajaSaktiAlertArr= [];
				var sakshiAlertArr= [];
				var newIndianExpressAlertArr= [];
				var deccanChronicleAlertArr= [];
				var timesOfIndiaAlertArr= [];
				var hinduAlertArr= [];
				var hansIndiaAlertArr= [];
				var namastheTelangaanaAlertArr= [];
				var navaTelangaanaAlertArr= [];
				var aNDHRAPRABHAAlertArr= [];
				var oNLINEAlertArr= [];
				
				var tV9AlertArr= [];  //Appending value 1
				var tV5AlertArr= [];
				var  eTVAPAlertArr= [];
				var sAKSHITVAlertArr= [];
				var aBNAertArrAlertArr= [];
				var tenTVArrAlertArr= [];
				var nTVArr = []; 
				
			   for(var i in result){
				 for(var j in result[i].subList){
					 var publicatinId = parseInt(result[i].subList[j].publicationId);
				    if(publicatinId==21){
						 andhraBhoomiAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name}); 
					}else if(publicatinId==22){
					andhraJyothiAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==23){
						 eenaaduAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==24){
						 vaarthaAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==25){
						 vishalandhraAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==27){
						 prajaSaktiAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==28){
						 sakshiAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==210){
						 newIndianExpressAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==211){
						 deccanChronicleAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==212){
						 timesOfIndiaAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==213){
						 hinduAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==214){
						 hansIndiaAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==215){
						 namastheTelangaanaAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==216){
						 navaTelangaanaAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==217){
						 aNDHRAPRABHAAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==218){
						 oNLINEAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==11){
						 tV9AlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==12){
						 tV5AlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==13){
						 eTVAPAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==14){
						 sAKSHITVAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==15){
						 aBNAertArrAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==16){
						 tenTVArrAlertArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}else if(publicatinId==17){
						 nTVArr.push({y:result[i].subList[j].totalAlertCnt,"extra":result[i].subList[j].publicationId+"-"+result[i].id+"-"+result[i].subList[j].totalAlertCnt+"-"+locationType+"-"+result[i].name});
					}
					
				 }
			 }
			 	
		       var mainJosnObjArr = [];
			   if(andhraBhoomiAlertArr != null && andhraBhoomiAlertArr.length > 0){
				mainJosnObjArr.push({name:'AndhraBhoomi',data:andhraBhoomiAlertArr,color:"#87C9D9"});  
			   }
			   if(andhraJyothiAlertArr != null && andhraJyothiAlertArr.length > 0){
				mainJosnObjArr.push({name:'AndhraJyothi',data:andhraJyothiAlertArr,color:"#3B82BE"});  
			  }
			  if(eenaaduAlertArr != null && eenaaduAlertArr.length > 0){
				mainJosnObjArr.push({name:'Eenaadu',data:eenaaduAlertArr,color:"#59BEF3"});  
			  }
			  if(vaarthaAlertArr != null && vaarthaAlertArr.length > 0){
				mainJosnObjArr.push({name:'Vaartha',data:vaarthaAlertArr,color:"#433EC0"});  
			  }
			  if(vishalandhraAlertArr != null && vishalandhraAlertArr.length > 0){
				mainJosnObjArr.push({name:'Vishalandhra',data:vishalandhraAlertArr,color:"#EA0F0D"});  
			  }
			  if(prajaSaktiAlertArr != null && prajaSaktiAlertArr.length > 0){
				mainJosnObjArr.push({name:'PrajaSakti',data:prajaSaktiAlertArr,color:"#6E82BD"});  
			  }
			  if(sakshiAlertArr != null && sakshiAlertArr.length > 0){
				mainJosnObjArr.push({name:'Sakshi',data:sakshiAlertArr,color:"#FEE60C"});  
			  }
			  if(newIndianExpressAlertArr != null && newIndianExpressAlertArr.length > 0){
				mainJosnObjArr.push({name:'NewIndianExpress',data:newIndianExpressAlertArr,color:"#5AF471"});  
			  }
			   if(deccanChronicleAlertArr != null && deccanChronicleAlertArr.length > 0){
				mainJosnObjArr.push({name:'DeccanChronicle',data:deccanChronicleAlertArr,color:"#54D2F2"});  
			  }
			   if(timesOfIndiaAlertArr != null && timesOfIndiaAlertArr.length > 0){
				mainJosnObjArr.push({name:'TimesOfIndia',data:timesOfIndiaAlertArr,color:"#ACA0F7"});  
			  }
			   if(hinduAlertArr != null && hinduAlertArr.length > 0){
				mainJosnObjArr.push({name:'Hindu',data:hinduAlertArr,color:"#F56CF7"});  
			  }
			   if(hansIndiaAlertArr != null && hansIndiaAlertArr.length > 0){
				mainJosnObjArr.push({name:'HansIndia',data:hansIndiaAlertArr,color:"#ED7B2F"});  
			  }
			  if(namastheTelangaanaAlertArr != null && namastheTelangaanaAlertArr.length > 0){
				mainJosnObjArr.push({name:'NamastheTelangaana',data:namastheTelangaanaAlertArr,color:"#ED7B2F"});  
			  }
			   if(navaTelangaanaAlertArr != null && navaTelangaanaAlertArr.length > 0){
				mainJosnObjArr.push({name:'NavaTelangaana',data:navaTelangaanaAlertArr,color:"#EDA8E2"});  
			  }
			   if(aNDHRAPRABHAAlertArr != null && aNDHRAPRABHAAlertArr.length > 0){
				mainJosnObjArr.push({name:'ANDHRAPRABHA',data:aNDHRAPRABHAAlertArr,color:"#2C81CB"});  
			  }
			  if(oNLINEAlertArr != null && oNLINEAlertArr.length > 0){
				mainJosnObjArr.push({name:'ONLINE',data:oNLINEAlertArr,color:"#EAE3C5"});  
			  }
			  if(tV9AlertArr != null && tV9AlertArr.length > 0){
				mainJosnObjArr.push({name:'TV9',data:tV9AlertArr,color:"#D62624"});  
			  }
			   if(tV5AlertArr != null && tV5AlertArr.length > 0){
				mainJosnObjArr.push({name:'TV5',data:tV5AlertArr,color:"#8510e5"});  
			  }
			   if(eTVAPAlertArr != null && eTVAPAlertArr.length > 0){
				mainJosnObjArr.push({name:'E TV AP',data:eTVAPAlertArr,color:"#FEC16D"});  
			  }
			   if(sAKSHITVAlertArr != null && sAKSHITVAlertArr.length > 0){
				mainJosnObjArr.push({name:'SAKSHI TV',data:sAKSHITVAlertArr,color:"#EA701E"});  
			  }
			   if(aBNAertArrAlertArr != null && aBNAertArrAlertArr.length > 0){
				mainJosnObjArr.push({name:'ABN',data:aBNAertArrAlertArr,color:"#B85C5C"});  
			  }
			   if(tenTVArrAlertArr != null && tenTVArrAlertArr.length > 0){
				mainJosnObjArr.push({name:'10 TV',data:tenTVArrAlertArr,color:"#F2CCAF"});  
			  }
			   if(nTVArr != null && nTVArr.length > 0){
				mainJosnObjArr.push({name:'N TV',data:nTVArr,color:"#FA0290"});  
			  }
		     var getWidth = $("#constituencyOvervwGraph").width();
			  $("#"+divId).css("width",getWidth);
			  
			  $("#"+divId).highcharts({
				chart: {
					type: 'bar'
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
					categories: locationName,
					title: {
						text: null
					}
				},
				yAxis: {
					min: 0,
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: '',
						align: 'high'
					},
					labels: {
						overflow: 'justify'
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						},
						formatter: function() {
                        return  (this.total);
                    },
					}
				},
				tooltip: {
					valueSuffix: ' ',
					shared :true
				},
				plotOptions: {
					bar: {
					stacking: 'normal',
						dataLabels: {
							align:"center",
							y:-5,
							enabled: false,
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
								click: function () {
									var publicationInfo = (this.extra).split("-");
									var publicationId = publicationInfo[0];
									var locationId = publicationInfo[1];
									var totalAlertCnt = publicationInfo[2];
									var locatinType = publicationInfo[3];
									var locationName = publicationInfo[4];
									 if(totalAlertCnt == 0){
										return;  
									 }
									getAlertPublicationDetails(publicationId,locationId,totalAlertCnt,locatinType,locationName);
								}
							}
						}
				        }
				},
				legend: {
						reversed: false,
						verticalAlign:'top',
						useHTML:true,
						labelFormatter: function () {return '<img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+this.name+'.png"  style="width:40px;"/>';}
						
						},
				credits: {
					enabled: false
				},
				series: mainJosnObjArr
			});
		 }else{
			 $("#"+divId).html("NO DATA AVAILABLE.");
		 }
		 if(divId == "constituencyLevelHighChartDivId"){
			 if(result != null && result.length > 15){
				$("#constituencyOvervwGraph").mCustomScrollbar();//{setHeight:'600px'}
				$("#constituencyOvervwGraph").css("height","600px");
		     }else{
				$("#constituencyOvervwGraph").css("height","auto");
			 } 
		 }
	}  
	
  function buildDistrictOrConstituencyImpactLevelTabularRsltPublicationWise(result,divId,locationType){
	 var str='';
	  str+='<div class="table-responsive">';
	 if(divId=="constituencyLevelTblDivId"){
		  str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed table-bordered text_align_center" id="cnsttncyWsPublctnAlrtCntSmmryDataTblId">'; 
	 }else if(divId=="districtImpactLevelTblDivId"){
		   str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed table-bordered text_align_center" id="dstrctWsPublctnAlrtCntSmmryDataTblId">';
	 }
	 
		 str+='<thead>';
		 if(divId=="constituencyLevelTblDivId"){
				str+='<th>Constituency</th>';
		 }else if(divId=="districtImpactLevelTblDivId"){
			  str+='<th>District</th>';
		 }
		      
			   str+='<th>Total</th>';
		       if(result != null && result.length > 0){
				    if(result[0].subList != null && result[0].subList.length > 0){
						 for(var k in result[0].subList){
							str+='<th>'+result[0].subList[k].publicationName+'</th>';		 
						 }
					}
			   }
	   str+='</thead>';
		 str+='<tbody>';
		  for(var i in result){
				str+='<tr>';
				str+='<td>'+result[i].name+'</td>';
				 if(result[i].totalAlertCnt > 0){
					str+='<td  style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" class="publicationCls" onClick="getAlertPublicationDetails(0,\''+result[i].id+'\',\''+result[i].totalAlertCnt+'\',\''+locationType+'\',\''+result[i].name+'\');" attr_location_level="'+result[i].name+'" attr_location_type="'+locationType+'" attr_alert_count="'+result[i].totalAlertCnt+'" attr_location_id = "'+result[i].id+'" attr_alert_publication_id="0" >'+result[i].totalAlertCnt+'</td>'; 
				}else{
				  str+='<td class="text-center">-</td>';      		
				} 
				if(result[i].subList != null && result[i].subList.length > 0){
					for(var j in result[i].subList){
							if(result[i].subList[j].totalAlertCnt > 0){
								str+='<td  style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" class="publicationCls" onClick="getAlertPublicationDetails(\''+result[i].subList[j].publicationId+'\',\''+result[i].id+'\',\''+result[i].subList[j].totalAlertCnt+'\',\''+locationType+'\',\''+result[i].name+'\');" attr_location_type="'+locationType+'" attr_alert_count="'+result[i].subList[j].totalAlertCnt+'" attr_location_level="'+result[i].name+'" attr_location_id = "'+result[i].id+'" attr_alert_publication_id="'+result[i].subList[j].publicationId+'"   >'+result[i].subList[j].totalAlertCnt+'</td>';      	
							}else{
								str+='<td class="text-center">-</td>';      	
							} 
					 }	
				}
				str+='</tr>';
			}
			 str+='</tbody>';
			 str+='</table>';
	      $("#"+divId).html(str);
		  str+='</div>';
		   if(divId=="constituencyLevelTblDivId"){
			 $("#cnsttncyWsPublctnAlrtCntSmmryDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 15,
			"aLengthMenu": [[10,15,20,30,50, 100, -1], [10,15,20,30,50, 100, "All"]]			
		   });   	
		   }else if(divId == "districtImpactLevelTblDivId"){
			 $("#dstrctWsPublctnAlrtCntSmmryDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 15,
			"aLengthMenu": [[10,15,20,30,50, 100, -1], [10,15,20,30,50, 100, "All"]]
		   });   	
		   }
		}
		//santosh
	function getAlertPublicationDetails(publicationId,locationIdStr,totalAlertCnt,loctionType,locationLevel){
		
		var locationIdStr = locationIdStr
		var loctionType = loctionType
		var districtIdArr =[];
		var constituencyId = 0;
		if(loctionType=="Constituency"){
			constituencyId = locationIdStr;  
		}else if(loctionType=="District"){
			 districtIdArr = locationIdStr.split(",");	
		}
		var alertStatusId = 0;
		var isPublication = "true";
		buildDistrictWiseAlert(districtIdArr,totalAlertCnt,constituencyId,alertStatusId,locationLevel,loctionType,isPublication,publicationId);
	}	
	 $(document).on("click",".linkedArticlesClickId",function(){	 
			var temp=$(this).attr('src');
			$(this).attr('src',$(".mainImage").attr('src'));
			$(".mainImage").attr('src',temp);
		});
		
 /* New Ajax Call Based on New Design */
 
 /*Sorting script start */
 			
 function getSortedDistrictInRequiredFormat(type){
	   var selectedLevel = $(".impactLevelCls").attr("attr_level");
	     $(".districtCollapseTblViewCls").removeClass("active");
	     $(".districtCollapseHIghChartViewCls").addClass("active");
		 $(".collapseTblViewCls").removeClass("active");
	     $(".collapseHIghChartViewCls").addClass("active");
		 $(".constituencyCollapseTblViewCls").removeClass("active");
	     $(".constituencyHighChartViewCls").addClass("active");
	     var districtId = $("#districtSelectBoxId").val();
		 var status="";
	     if(type=="Search"){
			$(".districtUl li").removeClass("active");
		    $(".districtUl li:first-child").addClass("active");	
			status = type;
			type = "Decending";
			
		}
	  var selectionType =  $("#hiddenLevelTypeId").attr("attr_level_type");
	  if(selectedLevel == "Overview"){
		  if(status =="Search"){
			getDistrictImpactandItsSubLevelAlert(type,districtId,selectionType); 
			getCorpGMCAlert(districtId,selectionType); 
			getConstituencyImpactandItsSubLevelAlert(type,0,districtId,selectionType); 
			getAssignGroupTypeAlertDtlsByImpactLevelWise(districtId);	  
		  }else{
			getDistrictImpactandItsSubLevelAlert(type,districtId,selectionType);  
		  }
	  }else if(selectedLevel == "Status"){
		   if(status =="Search"){
			 getTotalAlertGroupByLocationThenStatus(type,districtId); 
			 getGhmcImpactLevelAlertStatusWise(districtId);
			 getConstituencyAlertStatusWise(type,0,districtId);
			 getAssignGroupTypeAlertDtlsByImpactLevelWise(districtId);
		  }else{
			getTotalAlertGroupByLocationThenStatus(type,districtId);  
		  }
		
	  }else if(selectedLevel == "Publication"){
		   if(status =="Search"){
			    getDistrictWisePublicationAlert(type,districtId); 
				getCorpGHMCImpcatLevelAlertCntPublicationWise(districtId);
				getConstituencyWisePublicationAlert(type,0,districtId);
				getAssignGroupTypeAlertDtlsByImpactLevelWise(districtId); 
		   }else{
			   getDistrictWisePublicationAlert(type,districtId);    
		   }
	  } 
 }
  function getSortedConstituencyInRequiredFormat(type){
	  $(".constituencyCollapseTblViewCls").removeClass("active");
	  $(".constituencyHighChartViewCls").addClass("active");
	    var selectedLevel = $(".impactLevelCls").attr("attr_level");
	    var constituencyId = $("#constituencySeletBoxId").val();
		var districtId = $("#districtSelectBoxId").val();;
	    if(type=="Search"){
			 $(".constituencyUl li").removeClass("active");
		     $(".constituencyUl li:first-child").addClass("active");
		     type = "Decending";
		}
		var selectionType =  $("#hiddenLevelTypeId").attr("attr_level_type");
	  if(selectedLevel == "Overview"){
		 getConstituencyImpactandItsSubLevelAlert(type,constituencyId,districtId,selectionType);  
	  }else if(selectedLevel == "Status"){
		getConstituencyAlertStatusWise(type,constituencyId,districtId);
	  }else if(selectedLevel == "Publication"){
	    getConstituencyWisePublicationAlert(type,constituencyId,districtId);  
	  } 
 }
 /*End */
 
 
 
var globalStateLevelRslt; 
var globalDistrictLevelRlst;
var globalCorpGmcLevelRlst;
var globalConstituencyLevelRlst;
 function stateLevelHighchartBuildingFunction(){
	  var selectedLevel = $(".impactLevelCls").attr("attr_level");
	  var selectionType =  $("#hiddenLevelTypeId").attr("attr_level_type");
	  if(selectedLevel == "Overview"){
		 buildStateImpactLevelHighChartRslt(globalStateLevelRslt,selectionType);  
	  }else if(selectedLevel == "Status"){
		buildStateImpactLevelHighChartStatusWiseRslt(globalStateLevelRslt); 
	  }else if(selectedLevel == "Publication"){
	    buildStateOrGhmcImpactLevelHighChartRsltPublicationWise(globalStateLevelRslt,"stateImpactLevelHighChartDivId","StateImpactLevel");  
	  }
 }
  function stateLevelTblBuildingFunction(){
	  var selectedLevel = $(".impactLevelCls").attr("attr_level");
	   var selectionType =  $("#hiddenLevelTypeId").attr("attr_level_type");
	  if(selectedLevel == "Overview"){
		 buildStateImpactLevelTabularRslt(globalStateLevelRslt,selectionType);  
	  }else if(selectedLevel == "Status"){
		buildStateOrGhmcImpactLevelTabularRsltStatusWise(globalStateLevelRslt,"stateImpactLevelTblDivId","StateImpactLevel");
	  }else if(selectedLevel == "Publication"){
		buildStateOrGhmcImpactLevelTabularRsltPublicationWise(globalStateLevelRslt,"stateImpactLevelTblDivId","StateImpactLevel");  
	  }
 }
 
 function districtLevelHighchartBuildingFunction(){
	 var selectionType =  $("#hiddenLevelTypeId").attr("attr_level_type");
	 var selectedLevel = $(".impactLevelCls").attr("attr_level");
	 var districtId = $("#districtSelectBoxId").val();
	  if(selectedLevel == "Overview"){
		  buildDistrictImpactLevelHighChartRslt(globalDistrictLevelRlst,districtId,selectionType);  
	  }else if(selectedLevel == "Status"){
		buildDistrictOrConstituencyImpactLevelHighChartRsltStatusWise(globalDistrictLevelRlst,"districtImpactLevelHighChartDivId",districtId,"District");
	  }else if(selectedLevel == "Publication"){
	   buildDistrictOrConstituencyImpactLevelHighChartRsltPublicationWise(globalDistrictLevelRlst,"districtImpactLevelHighChartDivId",districtId,"District");  
	  }
 }
  function districtLevelTblBuildingFunction(){
	   var selectionType =  $("#hiddenLevelTypeId").attr("attr_level_type");
	   var selectedLevel = $(".impactLevelCls").attr("attr_level");
	  if(selectedLevel == "Overview"){
		  buildDistrictOrConstituencyImpactLevelTabularRslt(globalDistrictLevelRlst,"districtImpactLevelTblDivId","District",selectionType);  
	  }else if(selectedLevel == "Status"){
		buildDistrictOrConstituencyLevelRlstInTabularFormatStatusWise(globalDistrictLevelRlst,"District","districtImpactLevelTblDivId");
	  }else if(selectedLevel == "Publication"){
		buildDistrictOrConstituencyImpactLevelTabularRsltPublicationWise(globalDistrictLevelRlst,"districtImpactLevelTblDivId","District");  
	  }
 }
 function gmcLevelHighchartBuildingFunction(){
	   var selectedLevel = $(".impactLevelCls").attr("attr_level");
	  if(selectedLevel == "Overview"){
		 buildCorpGmcImpactLevelHighChartRslt(globalCorpGmcLevelRlst);  
	  }else if(selectedLevel == "Status"){
		buildCorpGmcImpactLevelHighChartRsltStatusWise(globalCorpGmcLevelRlst);
	  }else if(selectedLevel == "Publication"){
	    buildStateOrGhmcImpactLevelHighChartRsltPublicationWise(globalCorpGmcLevelRlst,"gmcImpactLevelHighChartDivId","GHMCImpactLevel");	  
	  }
 }
  function gmcLevelTblBuildingFunction(){
	   var selectionType =  $("#hiddenLevelTypeId").attr("attr_level_type");
	   var selectedLevel = $(".impactLevelCls").attr("attr_level");
	  if(selectedLevel == "Overview"){
		 buildCorpGmcImpactLevelTabularRslt(globalCorpGmcLevelRlst,"localElectionBody",selectionType);  
	  }else if(selectedLevel == "Status"){
		buildStateOrGhmcImpactLevelTabularRsltStatusWise(globalCorpGmcLevelRlst,"gmcImpactLevelTblDivId","GHMCImpactLevel"); 
	  }else if(selectedLevel == "Publication"){
		buildStateOrGhmcImpactLevelTabularRsltPublicationWise(globalCorpGmcLevelRlst,"gmcImpactLevelTblDivId","GHMCImpactLevel");  
	  }
 }
 function constituencyLevelHighchartBuildingFunction(){
	   var selectionType =  $("#hiddenLevelTypeId").attr("attr_level_type");
	   var selectedLevel = $(".impactLevelCls").attr("attr_level");
	    var constituencyId = $("#constituencySeletBoxId").val();
	  if(selectedLevel == "Overview"){
		 buildConstituencyImpactLevelHighChartRslt(globalConstituencyLevelRlst,constituencyId,selectionType);  
	  }else if(selectedLevel == "Status"){
		buildDistrictOrConstituencyImpactLevelHighChartRsltStatusWise(globalConstituencyLevelRlst,"constituencyLevelHighChartDivId",constituencyId,"Constituency"); 
	  }else if(selectedLevel == "Publication"){
		buildDistrictOrConstituencyImpactLevelHighChartRsltPublicationWise(globalConstituencyLevelRlst,"constituencyLevelHighChartDivId",constituencyId,"Constituency");  
	  }
 }
  function constituencyTblBuildingFunction(){
	    var selectedLevel = $(".impactLevelCls").attr("attr_level");
		var selectionType =  $("#hiddenLevelTypeId").attr("attr_level_type");
	  if(selectedLevel == "Overview"){
		 buildDistrictOrConstituencyImpactLevelTabularRslt(globalConstituencyLevelRlst,"constituencyLevelTblDivId","Constituency",selectionType);  
	  }else if(selectedLevel == "Status"){
		 buildDistrictOrConstituencyLevelRlstInTabularFormatStatusWise(globalConstituencyLevelRlst,"Constituency","constituencyLevelTblDivId"); 
	  }else if(selectedLevel == "Publication"){
		buildDistrictOrConstituencyImpactLevelTabularRsltPublicationWise(globalConstituencyLevelRlst,"constituencyLevelTblDivId","Constituency");  
	  }
 }
function getStateImpactandItsSubLevelAlert(selectionType){
	    $(".stateImpactLevelBlockCls").show();
		$("#stateImpactLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
        var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		var level = "impact";
		 if(selectionType=="locationWise"){
			 level="location"
		 }
		$("#stateOverviewHeadingId").html("state overview - "+level+" alerts");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		var impactScopeArr = [];
		if(selectionType=="impactScopeWise"){
			impactScopeArr = globalImpactScopeArr;
		}else if(selectionType=="locationWise"){
			impactScopeArr =[2,3,4,5,6,7,8,11];
		}
		var jsObj={  
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate:			customStartDateAlert,        
			toDate :			customEndDateAlert,
			scopeIdsArr:		impactScopeArr,
			alertIds : 			alertId,
			editionIds : 		editionId,
			alertStatusArr :  globalAlertStatusArr,
			selectionType : selectionType
		};
		$.ajax({
			type : 'GET',
			url : 'getStateImpactandItsSubLevelAlertAction.action',  
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
		   globalStateLevelRslt = result;
		   if(result != null && result.subList1 != null && result.subList1.length > 0){
			   buildStateImpactLevelHighChartRslt(result,selectionType);
		   }else{
			   $(".stateImpactLevelBlockCls").hide();  
		   }
      });	
	}
	
	function buildStateImpactLevelHighChartRslt(result,selectionType){
		if(result.subList1 != null && result.subList1.length > 0){
			var impactLevelColorObj =  {"1":"#967BDC","2":"#51D9B1","3":"#34CCFD","8":"#1062FB","5":"#009662","7":"#FC690D"};	
			var locationLevelColorObj =  {"2":"#967BDC","3":"#51D9B1","4":"#34CCFD","7":"#1062FB","5":"#009662","6":"#FC690D"};	
			var jsonObjArr = [];
			var colorArr = [];
			
			for(var i in result.subList1){
				jsonObjArr.push({"name":result.subList1[i].name,"data":[{y:result.subList1[i].alertCount,extra: result.subList1[i].id+"-"+selectionType}]});
				 if(selectionType=="impactScopeWise"){
					colorArr.push(impactLevelColorObj[result.subList1[i].id]);	
				 }else if(selectionType=="locationWise"){
					colorArr.push(locationLevelColorObj[result.subList1[i].id]); 
				 }
				
			}
			var getWidth = $("#stateOvervwGraph").width();
			$("#stateImpactLevelHighChartDivId").css("width",getWidth);	
			 $("#stateImpactLevelHighChartDivId").highcharts({
				 colors:colorArr,
				chart: {
					type: 'bar'
				},
				title: {
					text: null
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ['']
				},
				yAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						 title: {
							text: null
						},
						 stackLabels: {
						enabled: true,
						
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						},
						formatter: function() {
                        return  (this.total);
                    },
					}
				},
				legend: {
					reversed: false,
					verticalAlign:'top'
				},
				 tooltip: {
					 formatter: function() {
                        return  (this.series.name)+" - "+(this.y);
                    },
					//headerFormat: '<b>{series.name}</b><br/>',
					//pointFormat: '{point.y}'
				},
				 plotOptions: {
					bar: {
						stacking: 'normal',
						dataLabels: {
							enabled: false,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,0);      
								}
							}
						},
						
					},
					  series: {
						 cursor: 'pointer',
						 events: {
							click: function (event) {
								var impactLevelId=this.data[0].options.extra.split("-")[0];
								var levelType=this.data[0].options.extra.split("-")[1];
								getImpactLevelWiseAlertDtls(impactLevelId,this.data[0].options.y,levelType);
							}
						}
					}
				},
				series: jsonObjArr
			});
		}else{
		 $("#stateImpactLevelHighChartDivId").html("NO DATA AVAILABLE.");	
		}
	}
	function buildStateImpactLevelTabularRslt(result,selectionType){
	  var str= '';
	   if(result.subList1 != null && result.subList1.length > 0){
		   str+='<div class="table-responsive">';
			str+='<table class="table table-bordered text_align_center">';
				str+='<thead class="bg_ED">';
				str+='<th>TOTAL</th>';
				for(var i in result.subList1){
					str+='<th>'+result.subList1[i].name+'</th>';
				}
				str+='</thead>';
				str+='<tbody>';
				str+='<tr>';
				str+='<td style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" onClick="getImpactLevelWiseAlertDtls(0,\''+result.alertCount+'\',\''+selectionType+'\');">'+result.alertCount+'</td>';
				for(var j in result.subList1){
					 if(result.subList1[j].alertCount > 0){
						str+='<td style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" onClick="getImpactLevelWiseAlertDtls(\''+result.subList1[j].id+'\',\''+result.subList1[j].alertCount+'\',\''+selectionType+'\');">'+result.subList1[j].alertCount+'</td>';	
					 }else{
					str+='<td style="text-align:center;">-</td>';	 
					 }
					
				}
				str+='</tr>';
			str+='</table>';
		str+='</div>'
	   }else{
		 str+='NO DATA AVAILABLE';  
	   }
	   $("#stateImpactLevelTblDivId").html(str);
	}
	
	function getDistrictImpactandItsSubLevelAlert(sortingType,districtId,selectionType){
		
		/*Hiding Block if impact Level is not selected*/
		$(".districtImpactLevelBlockCls").show();
		if(globalDistrictImpactLevelScopeArr == null || globalDistrictImpactLevelScopeArr.length == 0){
			$(".districtImpactLevelBlockCls").hide();
			return;
		}
		$("#districtImpactLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		
		var level = "impact";
		 if(selectionType=="locationWise"){
			 level="location"
		 }
		 
		$("#districtOverviewHeadingId").html("District overview - "+level+" alerts");
		
		
		var impactScopeArr = [];
		if(selectionType=="impactScopeWise"){
			impactScopeArr = globalDistrictImpactLevelScopeArr;
		}else if(selectionType=="locationWise"){
			impactScopeArr =[3,4,5,6,7,8,11];
		}
		
		var jsObj={  
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate:			customStartDateAlert,        
			toDate :			customEndDateAlert,
			scopeIdsArr:		impactScopeArr,
			alertIds : 			alertId,
			editionIds : 		editionId,
			alertStatusArr :  globalAlertStatusArr,
			resultType : "District",
			locationValue : districtId,
			sortingType : sortingType,
			districtId : 0,
			selectionType : selectionType
		};
		$.ajax({
			type : 'POST',
			url : 'getDistrictOrConstituencyImpactandItsSubLevelAlert.action',  
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
		   globalDistrictLevelRlst = result;
		   if(result != null && result.subList1 != null && result.subList1.length > 0){
			   buildDistrictImpactLevelHighChartRslt(result,districtId,selectionType);
		   }else{
			  $(".districtImpactLevelBlockCls").hide();  
		   }
      });	
	}
	function buildDistrictImpactLevelHighChartRslt(result,districtId,selectionType){
		 if(result.subList1 != null && result.subList1.length > 0){
			 var districtNameArr = [];
			 var str='';
			 str+='<option value="0">All</option>';
			 for(var i in result.subList1){
				   districtNameArr.push(result.subList1[i].name);
				   str+='<option value="'+result.subList1[i].id+'">'+result.subList1[i].name+'</option>';
			 }
			 if(districtId == 0){ // Building district for searching first time only
				  $("#districtSelectBoxId").val(' ');
		          $("#districtSelectBoxId").html(str);
			 }
		    
			 var districtImpactArr = [];
			 var corpGhmcImpactArr = [];
			 var constituencyImpactArr = [];
			 var mndlMuncpltyImprArr = [];
			 var vllgWrdPnchytImpctArr = [];
			 for(var i in result.subList1){
				 for(var j in result.subList1[i].subList1){
					 if(selectionType != null && selectionType=="impactScopeWise"){
						  if(result.subList1[i].subList1[j].id==2){
							districtImpactArr.push({"y":result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-District"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType});
						  }else if(result.subList1[i].subList1[j].id==8){
							corpGhmcImpactArr.push({"y":result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-District"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType});  
						  }else if(result.subList1[i].subList1[j].id==3){
							 constituencyImpactArr.push({"y":result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-District"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType}); 
						  }else if(result.subList1[i].subList1[j].id==5){
							  mndlMuncpltyImprArr.push({"y":result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-District"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType});
						  }else if(result.subList1[i].subList1[j].id==7){
							 vllgWrdPnchytImpctArr.push({"y":result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-District"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType}); 
						  }
					 }else if(selectionType != null && selectionType=="locationWise"){
						 if(result.subList1[i].subList1[j].id==3){
							districtImpactArr.push({"y":result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-District"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType});
						  }else if(result.subList1[i].subList1[j].id==4){
							 constituencyImpactArr.push({"y":result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-District"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType}); 
						  }else if(result.subList1[i].subList1[j].id==5){
							  mndlMuncpltyImprArr.push({"y":result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-District"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType});
						  }else if(result.subList1[i].subList1[j].id==6){
							 vllgWrdPnchytImpctArr.push({"y":result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-District"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType}); 
						  } 
					 }
					 
				 }
			 }
			 	
			
		       var mainJosnObjArr = [];
			  if(districtImpactArr != null && districtImpactArr.length > 0){
				mainJosnObjArr.push({name:'DISTRICT',data:districtImpactArr,color:"#51D9B1"});  
			  }
			   if(corpGhmcImpactArr != null && corpGhmcImpactArr.length > 0){
				mainJosnObjArr.push({name:'CORP-GMC',data:corpGhmcImpactArr,color:"#1062FB"});  
			  }
			   if(constituencyImpactArr != null && constituencyImpactArr.length > 0){
				mainJosnObjArr.push({name:'CONSTITUENCY',data:constituencyImpactArr,color:"#34CCFD"});  
			  }
			   if(mndlMuncpltyImprArr != null && mndlMuncpltyImprArr.length > 0){
				mainJosnObjArr.push({name:'MANDAL/MUNICIPALITY',data:mndlMuncpltyImprArr,color:"#009662"});  
			  }
			  if(selectionType != null && selectionType=="impactScopeWise"){
				   if(vllgWrdPnchytImpctArr != null && vllgWrdPnchytImpctArr.length > 0){
				    mainJosnObjArr.push({name:'VILLAGE/WARD/PANCHAYAT',data:vllgWrdPnchytImpctArr,color:"#FC690D"});  
			      }
			  }else if(selectionType != null && selectionType=="locationWise"){
				 if(vllgWrdPnchytImpctArr != null && vllgWrdPnchytImpctArr.length > 0){
				  mainJosnObjArr.push({name:'VILLAGE/WARD/HAMLET',data:vllgWrdPnchytImpctArr,color:"#FC690D"});  
			     }  
			  }
			   
			  
			  var getWidth = $("#districtOvervwGraph").width();
			  $("#districtImpactLevelHighChartDivId").height(650);	
			  $("#districtImpactLevelHighChartDivId").css("width",getWidth);	
			  $("#districtImpactLevelHighChartDivId").highcharts({
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
					categories: districtNameArr,
					title: {
						text: null
					},
					labels: {
						//rotation: -45,
						style: {
							fontSize: '11px',
							fontFamily: '"Lucida Grande","Lucida Sans Unicode",Arial,Helvetica,sans-serif',
							textTransform: "uppercase"
						}
					}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: '',
						align: 'high'
					},
					labels: {
						overflow: 'justify'
					},
					 stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						},
						formatter: function() {
                        return  (this.total);
                    },
					}
				},
				tooltip: {
					valueSuffix: ' ',
					shared:true
					
				},
				plotOptions: {
					column: {
					stacking: 'normal',
						dataLabels: {
							align: "center",
							x:-8,
							enabled: false,
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
									click: function () {
										var distInfo = (this.extra).split("-");
										var districtId = distInfo[0];
										var totalAlertCnt = distInfo[1];
										var locationType = distInfo[2];
										var impactScopeId = distInfo[3];
										var selectionType = distInfo[4];
										getLocationWiseAlertDtls(districtId,totalAlertCnt,locationType,impactScopeId,selectionType);
									}
								}
							}
						}
				},
				legend: {
						reversed: false,
						verticalAlign:'top'
						},
				credits: {
					enabled: false
				},
				series: mainJosnObjArr
			});
		 }else{
			 $("#districtImpactLevelHighChartDivId").html("NO DATA AVAILABLE.");
		 }
	}
	function buildDistrictOrConstituencyImpactLevelTabularRslt(result,divId,locationType,selectionType){
	  var str= '';
	   if(result.subList1 != null && result.subList1.length > 0){
		   var impactLevelObj = result.subList1[0].subList1;
		   str+='<div class="table-responsive">';
		    if(divId=="constituencyLevelTblDivId"){
			   str+='<table class="table table-bordered text_align_center" id="constituencyLevelDataTblId">';	
			}else{
			   str+='<table class="table table-bordered text_align_center" id="DistrictLevelDataTblId">';	
			}
				str+='<thead class="bg_ED">';
				 if(divId=="constituencyLevelTblDivId"){
					str+='<th>Constituency</th>';
				}else{
				  str+='<th>District</th>';
				}
				
				str+='<th>Total</th>';
				for(var i in impactLevelObj){
					str+='<th>'+impactLevelObj[i].name+'</th>';
				}
				str+='</thead>';
				str+='<tbody>';
				 for(var i in result.subList1){
				  str+='<tr>';
				  str+='<td>'+result.subList1[i].name+'</td>';
				  if(result.subList1[i].totalAlertCnt > 0){
				     str+='<td style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" onClick="getLocationWiseAlertDtls(\''+result.subList1[i].id+'\',\''+result.subList1[i].totalAlertCnt+'\',\''+locationType+'\',0,\''+selectionType+'\');">'+result.subList1[i].totalAlertCnt+'</td>';	  
				  }else{
				      str+='<td style="text-align:center;">-</td>';	  
				  }
				 for(var j in result.subList1[i].subList1){
					 if(result.subList1[i].subList1[j].alertCount > 0){
						str+='<td style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" onClick="getLocationWiseAlertDtls(\''+result.subList1[i].id+'\',\''+result.subList1[i].subList1[j].alertCount+'\',\''+locationType+'\',\''+result.subList1[i].subList1[j].id+'\',\''+selectionType+'\');">'+result.subList1[i].subList1[j].alertCount+'</td>';	 	
					 }else{
						str+='<td  style="text-align:center;"> - </td>';	  
					 }
				    
				 }
				}
				str+='</tr>';
			str+='</table>';
		str+='</div>'
	   }else{
		 str+='NO DATA AVAILABLE';  
	   }
	     $("#"+divId).html(str);
		   if(divId=="constituencyLevelTblDivId"){
			    $("#constituencyLevelDataTblId").dataTable({
					"aaSorting": [],
					"iDisplayLength" : 15,
					"aLengthMenu": [[10,15,20,30,50, 100, -1], [10,15,20,30,50, 100, "All"]]
				});   
			}else{
				  $("#DistrictLevelDataTblId").dataTable({
					"aaSorting": [],
					"iDisplayLength" : 15,
					"aLengthMenu": [[10,15,20,30,50, 100, -1], [10,15,20,30,50, 100, "All"]]
				});   
			}
	   
	   
	}
	function getCorpGMCAlert(districtId,selectionType){
		$(".gmcImpactLevelBlockCls").show();	
		/*Hiding Block if impact Level is not selected*/
		if(globalCorpGhmcImpactScopeSArr == null || globalCorpGhmcImpactScopeSArr.length == 0){
			$(".gmcImpactLevelBlockCls").hide();
			 return;
		}
		$("#gmcImpactLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		
		 var level = "impact";
		 if(selectionType=="locationWise"){
			 level="location"
		 }
		$("#corpOverviewHeadingId").html("CORP-GMC overview - "+level+" alerts");
		
	    var impactScopeArr = [];
		if(selectionType=="impactScopeWise"){
			impactScopeArr = globalCorpGhmcImpactScopeSArr;
		}else if(selectionType=="locationWise"){
			impactScopeArr =[7];
		}
		var jsObj={  
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate:			customStartDateAlert,        
			toDate :			customEndDateAlert,
			scopeIdsArr:		impactScopeArr,
			alertIds : 			alertId,
			editionIds : 		editionId,
			alertStatusArr :  globalAlertStatusArr,
			districtId :districtId,
			selectionType : selectionType
		};
		$.ajax({
			type : 'POST',
			url : 'getCorpGMCAlertAction.action',  
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			globalCorpGmcLevelRlst = result;
		    if(result != null && result.subList1 != null && result.subList1.length > 0){
				buildCorpGmcImpactLevelHighChartRslt(result,selectionType);
			}else{
				$(".gmcImpactLevelBlockCls").hide();
			}
      });	
	}
	
	function buildCorpGmcImpactLevelHighChartRslt(result,selectionType){  
		var corpGmcImparArr = [];
		 if(result.subList1.length ==1){
			 if(result.subList1[0].id==0){
				$(".gmcImpactLevelBlockCls").hide(); 
			 }
		 }
	  	if(result.subList1 != null && result.subList1.length > 0){
			for(var i in result.subList1){
				if(result.subList1[i].id ==0)
					continue;
				var obj1 = {
					name: result.subList1[i].name,
					y: result.subList1[i].alertCount,
					extra:result.subList1[i].id+"-"+selectionType       
				};
				corpGmcImparArr.push(obj1);
			}	
		}
		var getWidth = $("#corpGmcOvervwGraph").width();
		$("#gmcImpactLevelHighChartDivId").css("width",getWidth);	
	   $("#gmcImpactLevelHighChartDivId").highcharts({  
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
									if(this.value.toString()>=10){
										return this.value.toString().substring(0, 10)+'...';
									}else{
										return this.value;
									}
									
								},
								style: {
									fontSize: '11px',
									fontFamily: '"Lucida Grande","Lucida Sans Unicode",Arial,Helvetica,sans-serif',
									textTransform: "uppercase"
								}
							},
							
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
					},
					 stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						},
						formatter: function() {
                        return  (this.total);
                    },
					}
				},
				legend: {
					enabled: false
				},	
				plotOptions: {
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: false,
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
								click: function () {
									var localElectionBodyId = this.extra.split("-")[0];
									var selectionType = this.extra.split("-")[1];
									var totalAlertCnt = this.y;
									var scopeId = 0;
									if(selectionType=="impactScopeWise"){
										scopeId = 8;
									}else if(selectionType=="locationWise"){
										scopeId = 7;
									}
									getLocationWiseAlertDtls(localElectionBodyId,totalAlertCnt,'localElectionBody',scopeId,selectionType);
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
					data: corpGmcImparArr
				}],
			 
			});  
	}
	
	function buildCorpGmcImpactLevelTabularRslt(result,locationType,selectionType){
	  var str= '';
	  var scopeId =0;
	  if(selectionType == "impactScopeWise"){
		  scopeId = 8;
	  }else if(selectionType == "locationWise"){
		  scopeId = 7;
	  }
	   if(result.subList1 != null && result.subList1.length > 0){
		   var impactLevelObj = result.subList1[0].subList1;
		   str+='<div class="table-responsive">';
			str+='<table class="table table-bordered text_align_center">';
				str+='<thead class="bg_ED text-capital">';
				str+='<th style="text-align:center;">CORP-GMC</th>';
				str+='<th style="text-align:center;">Total</th>';
				str+='</thead>';
				str+='<tbody>';
				 for(var i in result.subList1){
				  if(result.subList1[i].id ==0)
					continue;
				  str+='<tr>';
				  str+='<td style="text-align:center;">'+result.subList1[i].name+'</td>';
				  if(result.subList1[i].alertCount > 0){
					str+='<td style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" onClick="getLocationWiseAlertDtls(\''+result.subList1[i].id+'\',\''+result.subList1[i].alertCount+'\',\''+locationType+'\',\''+scopeId+'\',\''+selectionType+'\');">'+result.subList1[i].alertCount+'</td>';  
				  }else{
					str+='<td style="text-align:center;">-</td>';  
				  }
				  str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>'
	   }else{
		 str+='NO DATA AVAILABLE';  
	   }
	   $("#gmcImpactLevelTblDivId").html(str);
	}
	function getConstituencyImpactandItsSubLevelAlert(sortingType,constituencyId,districtId,selectionType){
		/*Hiding Block if impact Level is not selected*/
		$(".constituencyImpactLevelBlockCls").show();	
		if(globalConstituencyImpactScopeArr == null || globalConstituencyImpactScopeArr.length == 0){
			$(".constituencyImpactLevelBlockCls").hide();
			return;
		}
	  $("#constituencyLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		 var level = "impact";
		 if(selectionType=="locationWise"){
			 level="location"
		 }
		$("#constituencyOverviewHeadingId").html("Constituency overview - "+level+" alerts");
		var impactScopeArr = [];
		if(selectionType=="impactScopeWise"){
			impactScopeArr = globalConstituencyImpactScopeArr;
		}else if(selectionType=="locationWise"){
			impactScopeArr =[4,5,6,7,8,11];
		}
		
		var jsObj={  
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate:			customStartDateAlert,        
			toDate :			customEndDateAlert,
			scopeIdsArr:		impactScopeArr,
			alertIds : 			alertId,
			editionIds : 		editionId,
			alertStatusArr :  globalAlertStatusArr,
			locationValue : constituencyId,
			resultType : "Constituency",
			sortingType : sortingType,
			districtId : districtId,
			selectionType : selectionType
		};
		$.ajax({
			type : 'POST',
			url : 'getDistrictOrConstituencyImpactandItsSubLevelAlert.action',  
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
		    globalConstituencyLevelRlst = result;
			if(result != null && result.subList1!= null && result.subList1.length>0){
			   buildConstituencyImpactLevelHighChartRslt(result,constituencyId,selectionType);	
			}else{
				$(".constituencyImpactLevelBlockCls").hide();
			}
      });	
	}
	function buildConstituencyImpactLevelHighChartRslt(result,constituencyId,selectionType){
		   if(result.subList1!= null && result.subList1.length > 10){
				var highChartDivHight = result.subList1.length*40;
				$("#constituencyLevelHighChartDivId").height(highChartDivHight);
			}else{
				$("#constituencyLevelHighChartDivId").height(250);		
			}
		 if(result.subList1 != null && result.subList1.length > 0){
			 var str = '';
			 str+='<option value="0">All</option>';
			  var constituencyName = [];
			 for(var i in result.subList1){
				 constituencyName.push(result.subList1[i].name);
				 str+='<option value="'+result.subList1[i].id+'">'+result.subList1[i].name+'</option>';
			 }
			 if(constituencyId == 0){
			    $("#constituencySeletBoxId").val('');//building first time only constituency for searching type
                $("#constituencySeletBoxId").html(str);	 
			 }
			  //constituency
			  
			 var constituencyImpactArr = [];
			 var mndlMuncpltyImprArr = [];
			 var vllgWrdPnchytImpctArr = [];
			 for(var i in result.subList1){
				 for(var j in result.subList1[i].subList1){
					 if(selectionType!= null && selectionType=="impactScopeWise"){
						   if(result.subList1[i].subList1[j].id==3){
							if(result.subList1[i].subList1[j].alertCount !=0){
							 constituencyImpactArr.push({y:result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-Constituency"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType}); 
							}
						  }else if(result.subList1[i].subList1[j].id==5){
							  if(result.subList1[i].subList1[j].alertCount !=0){
							  mndlMuncpltyImprArr.push({y:result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-Constituency"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType});
							  }
						  }else if(result.subList1[i].subList1[j].id==7){
							if(result.subList1[i].subList1[j].alertCount !=0){
						  vllgWrdPnchytImpctArr.push({y:result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-Constituency"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType});
							}					  
						  }
					 }else if(selectionType != null && selectionType=="locationWise"){
						  if(result.subList1[i].subList1[j].id==4){
							if(result.subList1[i].subList1[j].alertCount !=0){
							   constituencyImpactArr.push({y:result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-Constituency"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType}); 
							}
						  }else if(result.subList1[i].subList1[j].id==5){
							  if(result.subList1[i].subList1[j].alertCount !=0){
							    mndlMuncpltyImprArr.push({y:result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-Constituency"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType});
							  }
						  }else if(result.subList1[i].subList1[j].id==6){
							if(result.subList1[i].subList1[j].alertCount !=0){
						      vllgWrdPnchytImpctArr.push({y:result.subList1[i].subList1[j].alertCount,"extra":result.subList1[i].id+"-"+result.subList1[i].subList1[j].alertCount+"-Constituency"+"-"+result.subList1[i].subList1[j].id+"-"+selectionType});
							}					  
						  }  
					 }
				 }
			 }
		       var mainJosnObjArr = [];
			   if(constituencyImpactArr != null && constituencyImpactArr.length > 0){
				 mainJosnObjArr.push({name:'CONSTITUENCY',data:constituencyImpactArr,color:"#34CCFD"});  
			  }
			   if(mndlMuncpltyImprArr != null && mndlMuncpltyImprArr.length > 0){
				 mainJosnObjArr.push({name:'MANDAL/MUNICIPALITY',data:mndlMuncpltyImprArr,color:"#059E69"});  
			  }
			  if(selectionType != null && selectionType=="impactScopeWise"){
				  if(vllgWrdPnchytImpctArr != null && vllgWrdPnchytImpctArr.length > 0){
				   mainJosnObjArr.push({name:'VILLAGE/WARD/PANCHAYAT',data:vllgWrdPnchytImpctArr,color:"#FE6406"}); 
				  }				  
			  }else if(selectionType != null && selectionType=="locationWise"){
				   if(vllgWrdPnchytImpctArr != null && vllgWrdPnchytImpctArr.length > 0){
				    mainJosnObjArr.push({name:'VILLAGE/WARD/HAMLET',data:vllgWrdPnchytImpctArr,color:"#FE6406"});  
			        } 
			  }
			 
			  var getWidth = $("#constituencyOvervwGraph").width();
			$("#constituencyLevelHighChartDivId").css("width",getWidth);	
			  $("#constituencyLevelHighChartDivId").highcharts({
				chart: {
					type: 'bar'
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
					categories: constituencyName,
					title: {
						text: null
					},
					
				},
				yAxis: {
					min: 0,
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: '',
						align: 'high'
					},
					labels: {
						overflow: 'justify'
					},
					 stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						},
						formatter: function() {
                        return  (this.total);
                    },
					}
				},
				tooltip: {
					valueSuffix: ' ',
					shared:true
				},
				plotOptions: {
					bar: {
					stacking: 'normal',
						dataLabels: {
							align:"center",
							x:5,
							y:-5,
							enabled: false,
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
									click: function () {
										var constituencyInfo = (this.extra).split("-");
										var constituencyId = constituencyInfo[0];
										var totalAlertCnt = constituencyInfo[1];
										var locationType = constituencyInfo[2];
										var impactScopeId = constituencyInfo[3];
										var selectionType = constituencyInfo[4];
										getLocationWiseAlertDtls(constituencyId,totalAlertCnt,locationType,impactScopeId,selectionType);
									}
								}
							}
						}
					
				},
				legend: {
						reversed: true,
						verticalAlign:'top'
						},
				credits: {
					enabled: false
				},
				series: mainJosnObjArr
			});
		 }else{
			 $("#constituencyLevelHighChartDivId").html("NO DATA AVAILABLE.");
		 }
			if(result.subList1!= null && result.subList1.length > 15){
				$("#constituencyOvervwGraph").mCustomScrollbar();//{setHeight:'600px'}
				$("#constituencyOvervwGraph").css("height","600px");
			}else{
				$("#constituencyOvervwGraph").css("height","auto");
			}
			
		
	}
 function getGhmcImpactLevelAlertStatusWise(districtId){
	   /*Hiding Block if impact Level is not selected*/
	      $(".gmcImpactLevelBlockCls").show();	
		if(globalCorpGhmcImpactScopeSArr == null || globalCorpGhmcImpactScopeSArr.length == 0){
			$(".gmcImpactLevelBlockCls").hide();
			 return;
		}
		$("#gmcImpactLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		
		$("#corpOverviewHeadingId").html("CORP-GMC overview - impact alerts");
		var jsObj={  
			activityMemberId : 	globalActivityMemberId,      
			stateId : 			globalStateId,           
			fromDate:			customStartDateAlert,        
			toDate :			customEndDateAlert,
			scopeIdsArr:		globalCorpGhmcImpactScopeSArr,
			alertIds : 			alertId,
			editionIds : 		editionId,
			alertStatusArr : globalAlertStatusArr,
			districtId :districtId
		};
		$.ajax({
			type : 'POST',
			url : 'getStateOrGhmcImpactLevelAlertStatusWiseAction.action',  
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			globalCorpGmcLevelRlst = result;
			if(result.alertCount == 0){
			  $(".gmcImpactLevelBlockCls").hide();	
			}
			if(result != null && result.statusList != null && result.statusList.length > 0){
			  buildCorpGmcImpactLevelHighChartRsltStatusWise(result);	
			}else{
			  $(".gmcImpactLevelBlockCls").hide();		
			}
			
      });	
	}
	function buildCorpGmcImpactLevelHighChartRsltStatusWise(result){
		
		if(result.statusList != null && result.statusList.length > 0 ){
				 var statusList = result.statusList;
			     var statusNameArr =[];
				 var alertCnt = [];
				 var count = [];
				  var totalAlertCnt = result.alertCount;
				     //Pushing Total Alert
					alertCnt.push({"y":totalAlertCnt,"extra":"0-"+"0-"+result.alertCount+"-GHMCImpactLevel-"+""});
					count.push({"y":parseInt(result.alertCount)-parseInt(result.alertCount),"extra":"0-"+"0-"+result.alertCount+"-GHMCImpactLevel-"+"",color:"#D3D3D3"})
					statusNameArr.push("Total");
					for(var i in statusList){    
				   var uniqCnt = {};
					statusNameArr.push(statusList[i].name);
					alertCnt.push({"y":statusList[i].alertCount,"extra":statusList[i].id+"-0-"+statusList[i].alertCount+"-GHMCImpactLevel-"+statusList[i].name});
					var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(statusList[i].alertCount),"extra":statusList[i].id+"-0-"+statusList[i].alertCount+"-GHMCImpactLevel-"+statusList[i].name,color:"#D3D3D3"};
					count.push(uniqCnt);
					}

				   var getWidth = $("#corpGmcOvervwGraph").width();
					$("#gmcImpactLevelHighChartDivId").css("width",getWidth);	
				   $(function () {
					$('#gmcImpactLevelHighChartDivId').highcharts({
						colors: ['#53BF8B','#53BF8B','#53BF8B','#53BF8B','#53BF8B','#53BF8B','#53BF8B'],     
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
										if(this.value.toString() >=8){
											return this.value.toString().substring(0, 8)+'...';
										}else{
											return this.value;
										}
										
									},
									style: {
										fontSize: '11px',
										fontFamily: '"Lucida Grande","Lucida Sans Unicode",Arial,Helvetica,sans-serif',
										textTransform: "uppercase"
									}
								}
						},
						yAxis: {
							min: 0,
								   gridLineWidth: 0,
									minorGridLineWidth: 0,
							title: {
								text: ''
							},
							stackLabels: {
								enabled: true,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
								},
								formatter: function() {
									return  (this.total);
								},
							}
							
						},
					 	tooltip: {
							formatter: function () {
								var s = '<b>' + this.x + '</b>';

									$.each(this.points, function () {
									if(this.series.name != "Series 1")  
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
										this.y/* +' - ' +
										(Highcharts.numberFormat(this.percentage,1)+'%'); */
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
									series: {
								cursor: 'pointer',
								point: {
								events: {
									click: function () {
										var ghmcInfo = (this.extra).split("-");
										 var alertStatusId = ghmcInfo[0];
										var locationId = ghmcInfo[1];
										var totalAlertCnt = ghmcInfo[2];
										var impactLevel = ghmcInfo[3];
										var alertStatus = ghmcInfo[4];
										 if(totalAlertCnt == 0){
											return;  
										 }
										locationAlertDetails(alertStatusId,locationId,totalAlertCnt,impactLevel,alertStatus);
									}
								}
							}
						   }
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
				$("#gmcImpactLevelHighChartDivId").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
	}
	function buildStateOrGhmcImpactLevelTabularRsltStatusWise(result,divId,locationType){
	  var str= '';
	   if(result.statusList != null && result.statusList.length > 0){
		   str+='<div class="table-responsive">';
			str+='<table class="table table-bordered text_align_center">';
				str+='<thead class="bg_ED">';
				str+='<th>Total</th>';
				for(var i in result.statusList){
				str+='<th>'+result.statusList[i].name+'</th>';	
				}
				str+='</thead>';
				str+='<tbody>';
				 str+='<tr>';
				   if(result.alertCount >0){
					 str+='<td style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" onClick="locationAlertDetails(0,0,\''+result.alertCount+'\',\''+locationType+'\',\''+result.name+'\');">'+result.alertCount+'</td>';  
				   }else{
					str+='<td style="text-align:center;">-</td>';   
				   }
				  
				 for(var i in result.statusList){
					 if(result.statusList[i].alertCount > 0){
						 str+='<td style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" onClick="locationAlertDetails(\''+result.statusList[i].id+'\',0,\''+result.statusList[i].alertCount+'\',\''+locationType+'\',\''+result.statusList[i].name+'\');">'+result.statusList[i].alertCount+'</td>';
					 }else{
						str+='<td style="text-align:center">-</td>'; 
					 }
				}
				str+='</tr>';
			str+='</table>';
		str+='</div>'
	   }else{
		 str+='NO DATA AVAILABLE';  
	   }
	   $("#"+divId).html(str);
	}
 function getStateImpcatLevelAlertCntPublicationWise(){
	     $(".stateImpactLevelBlockCls").show();
		$("#stateImpactLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
    	var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
		if(alertId == undefined){
			alertId = 0;
		}
		var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
		if(editionId == undefined){
			editionId = 0;
		}
		$("#stateOverviewHeadingId").html("State overview - impact alerts");
    	var jsObj = { 
			stateId : 			globalStateId,             
			fromDate : 			customStartDateAlert,      
			toDate : 			customEndDateAlert,  
			scopeIdsArr : 		globalImpactScopeArr,              
			activityMemberId : 	globalActivityMemberId,       
			alertIds : 			alertId,
			editionIds : 		editionId,
			alertStatusArr : globalAlertStatusArr,
			districtId : 0
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getStateOrGHMCImpcatLevelAlertCntPublicationWiseAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			globalStateLevelRslt = result;
			if(result != null && result.subList1 != null && result.subList1.length > 0){
			 buildStateOrGhmcImpactLevelHighChartRsltPublicationWise(result,"stateImpactLevelHighChartDivId","StateImpactLevel");	
			}else{
			  $(".stateImpactLevelBlockCls").hide();
			}
			
		});  
	}
	function buildStateOrGhmcImpactLevelHighChartRsltPublicationWise(result,divId,impactLevel){
		
		var publicationNameArr =[];
		var alertCntArr = [];
		 var count = [];
	  	if(result.subList1 != null && result.subList1.length > 0){
			var totalAlertCnt = result.alertCnt;
			publicationNameArr.push("Total");
			count.push({y:parseInt(totalAlertCnt)-parseInt(totalAlertCnt),"extra":"0-0-"+result.alertCnt+"-"+impactLevel+"-"+"Total",color:"#D3D3D3"})
			alertCntArr.push({y:totalAlertCnt,"extra":"0-0-"+result.alertCnt+"-"+impactLevel+"-"+"Total"});
				for(var i in result.subList1){
						publicationNameArr.push(result.subList1[i].name);
						alertCntArr.push({y:result.subList1[i].alertCnt,"extra":result.subList1[i].id+"-0-"+result.subList1[i].alertCnt+"-"+impactLevel+"-"+result.subList1[i].name});
						var uniqCnt = {y:parseInt(totalAlertCnt)-parseInt(result.subList1[i].alertCnt),"extra":result.subList1[i].id+"-0-"+result.subList1[i].alertCnt+"-"+impactLevel+"-"+result.subList1[i].name,color:"#D3D3D3"};
						count.push(uniqCnt);
				}	

				   var getWidth = $("#stateOvervwGraph").width();
		            $("#"+divId).css("width",getWidth);	
				   $(function () {
					$("#"+divId).highcharts({
						colors: ['#53BF8B'],     
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
							categories: publicationNameArr,
							type: 'category',
							labels: {
										useHTML: true,
										formatter: function() {
											if(this.value=="Total"){
												return this.value;
											}
											return '<img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+this.value+'.png" style="width:40px;"/>';
											
										},
										//rotation: -55,
										style: {
											fontSize: '10px',
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
							
							
						},
					 	tooltip: {
							formatter: function () {
								var s = '<b>' + this.x + '</b>';

									$.each(this.points, function () {
									if(this.series.name != "Series 1")  
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
										this.y/* +' - ' +
										(Highcharts.numberFormat(this.percentage,1)+'%'); */
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
												return (this.y);
											}
										}
									},
									
								},
								series: {
							cursor: 'pointer',
							point: {
							events: {
								click: function () {
									var publicationInfo = (this.extra).split("-");
									var publicationId = publicationInfo[0];
									var locationId = publicationInfo[1];
									var totalAlertCnt = publicationInfo[2];
									var impactLevel = publicationInfo[3];
									var locationName = publicationInfo[4];
									 if(totalAlertCnt == 0){
										return;  
									 }
									getAlertPublicationDetails(publicationId,locationId,totalAlertCnt,impactLevel,locationName);
								}
							}
						}
				        }
							},
						series: [{
							data: count    
						}, {
							name: "Number of alerts",
							data: alertCntArr,
							colorByPoint: true,
							stackLabels: {
								enabled: true,
								formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return (this.y);
									}
								},
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
								},
							
						}
						}]
					});
				});	
			}else{
				$("#"+divId).html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
	}
 function getCorpGHMCImpcatLevelAlertCntPublicationWise(districtId){
		/*Hiding Block if impact Level is not selected*/
		 $(".gmcImpactLevelBlockCls").show();	
		if(globalCorpGhmcImpactScopeSArr == null || globalCorpGhmcImpactScopeSArr.length == 0){
			$(".gmcImpactLevelBlockCls").hide();
			 return;
		}
		 $("#corpOverviewHeadingId").html("CORP-GMC overview - impact alerts");
		$("#gmcImpactLevelHighChartDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
			scopeIdsArr : 		globalCorpGhmcImpactScopeSArr,              
			activityMemberId : 	globalActivityMemberId,       
			alertIds : 			alertId,
			editionIds : 		editionId,
			alertStatusArr : globalAlertStatusArr,
			districtId : districtId
		}                  
		$.ajax({
			type : 'POST',        
			url : 'getStateOrGHMCImpcatLevelAlertCntPublicationWiseAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}    
		}).done(function(result){
			globalCorpGmcLevelRlst = result;
			if(result != null && result.subList1 != null && result.subList1.length > 0){
				buildStateOrGhmcImpactLevelHighChartRsltPublicationWise(result,"gmcImpactLevelHighChartDivId","GHMCImpactLevel");
			}else{
			 	$(".gmcImpactLevelBlockCls").hide();
			}
			
		});  
	}
	function buildStateOrGhmcImpactLevelTabularRsltPublicationWise(result,divId,locationType){
	  var str= '';
	   if(result.subList1 != null && result.subList1.length > 0){
		   str+='<div class="table-responsive">';
			str+='<table class="table table-bordered text_align_center">';
				str+='<thead class="bg_ED">';
				str+='<th>Total</th>';
				for(var i in result.subList1){
				str+='<th>'+result.subList1[i].name+'</th>';	
				}
				str+='</thead>';
				str+='<tbody>';
				 str+='<tr>';
				  if(result.alertCnt > 0){
					  str+='<td style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" class="publicationCls" onClick="getAlertPublicationDetails(0,0,\''+result.alertCnt+'\',\''+locationType+'\',\''+result.name+'\');">'+result.alertCnt+'</td>';
				  }else{
					str+='<td style="text-align:center;">-</td>';  
				  }
				  
				 for(var i in result.subList1){
				  if(result.subList1[i].alertCnt > 0){
				      str+='<td style="text-align:center;cursor:pointer;color:rgb(51, 122, 183);font-size:13px;" class="publicationCls" onClick="getAlertPublicationDetails(\''+result.subList1[i].id+'\',0,\''+result.subList1[i].alertCnt+'\',\''+locationType+'\',\''+result.subList1[i].name+'\');">'+result.subList1[i].alertCnt+'</td>';	  
				  }else{
					  str+='<td style="text-align:center;">-</td>'; 
				  }
				}
				 str+='</tr>';
			str+='</table>';
		str+='</div>'
	   }else{
		 str+='NO DATA AVAILABLE';  
	   }
	   $("#"+divId).html(str);
	}
	
	
/* $(document).on("click",".childUserTypeClsForAlert",function(){
	var childUserTypeId = $(this).attr("attr_userTypeId");
	var childUserType = $(this).attr("attr_userType");
	  getSelectedChildTypeMembersForAlert(childUserTypeId,childUserType);
}); */
function childUserTypeClsForAlert(childUserTypeId,childUserType){
	
	getSelectedChildTypeMembersForAlert(childUserTypeId,childUserType);
}
/* $(document).on("click",".remveAlertSlcUsrType",function(){
		 var removeSelected = $(this).attr("attr_removeSelecUserType"); 
		 $("#"+removeSelected).html(' ');
		 $("#"+removeSelected).closest('.showHideTr').hide();
}); */

function remveAlertSlcUsrType(removeSelected){
	$("#"+removeSelected).html(' ');
	$("#"+removeSelected).closest('.showHideTr').hide();
}
/*  
$(document).on("click",".childActivityMemberDtlsCls",function(){
	    $(this).next('tr.showHideTr').show(); 
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
		 if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getCandidateAccessLocationAlertDtlsStatusWise(userTypeId,activityMemberId,selectedMemberName,selectedUserType);			  
		 }else{
		 getDirectChildActivityAlertMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
	     getCandidateAccessLocationAlertDtlsStatusWise(userTypeId,activityMemberId,selectedMemberName,selectedUserType);	
		 }	
}); */
function childActivityMemberDtlsCls(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId,id){
	
	 $("#"+id).show(); 
	 //var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id"); 
	 if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
	  getCandidateAccessLocationAlertDtlsStatusWise(userTypeId,activityMemberId,selectedMemberName,selectedUserType);			  
	 }else{
	 getDirectChildActivityAlertMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
	 getCandidateAccessLocationAlertDtlsStatusWise(userTypeId,activityMemberId,selectedMemberName,selectedUserType);	
	 }	
	 
}

	/* $(document).on("click",".activityMemberClsForAlertBlock",function(){
	    
		$(".alertslickPanelSlider").find("li").find(".panelSlick").removeClass("panelActiveSlick");
		$(this).find(".panelSlick").addClass("panelActiveSlick");
	    var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
    	var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype"); 	
		var childActivityMemberId = $(this).attr("attr_id");  
		if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
          getCandidateAccessLocationAlertDtlsStatusWise(userTypeId,activityMemberId,selectedMemberName,selectedUserType);			  		
		 }else{
		  getDirectChildActivityAlertMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
	      getCandidateAccessLocationAlertDtlsStatusWise(userTypeId,activityMemberId,selectedMemberName,selectedUserType);			  		
		 }
}); */
function activityMemberClsForAlertBlock(selectedUserType,childActivityMemberId,selectedMemberName,activityMemberId,userTypeId){
	
	
	$(".alertslickPanelSlider").find("li").find(".panelSlick").removeClass("panelActiveSlick");
	$("#panelColor"+activityMemberId).find(".panelSlick").addClass("panelActiveSlick");
	if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
	  getCandidateAccessLocationAlertDtlsStatusWise(userTypeId,activityMemberId,selectedMemberName,selectedUserType);			  		
	 }else{
	  getDirectChildActivityAlertMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
	  getCandidateAccessLocationAlertDtlsStatusWise(userTypeId,activityMemberId,selectedMemberName,selectedUserType);			  		
	 }
}
	function getAllItsSubUserTypeIdsByParentUserTypeIdForAlert(){
		   $(".alertComparisonblock").show();
		   $(".alertComBlockCls").show();
	     if(globalDistrictImpactLevelScopeArr == null || globalDistrictImpactLevelScopeArr.length == 0){
			 $(".alertComparisonblock").hide();
			 return;
		 }
	     $("#alertChildActivityMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 $("#childUserTypeDetailsDivForAlerts").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 $("#candidateLocationAlertDtlsStatusWiseDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj = {parentUserTypeId : globalUserTypeId}
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#childUserTypeDetailsDivForAlerts").html(" ");	
			if(result != null && result.length > 0){
			buildUserTypesAndItsChildRlstForAlert(result);	
			}else{
			$("#childUserTypeDetailsDivForAlerts").html("NO DATA AVAILABLE");
			$(".alertComBlockCls").hide();
			}
		});		 
	}
	
	function buildUserTypesAndItsChildRlstForAlert(result){
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeIdString;
		 var userType;
		 if(result !=null && result.length >0){
			  firstChildUserTypeIdString = result[0].shortName;
			  userType=result[0].userType;
			 for(var i in result){
              //str+='<li attr_userTypeId="'+result[i].shortName+'" attr_userType=\''+result[i].userType+'\' class="childUserTypeClsForAlert">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			  
			   str+='<li onclick="childUserTypeClsForAlert(\''+result[i].shortName+'\',\''+result[i].userType+'\');">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#childUserTypeDetailsDivForAlerts").html(str);
		$(".comparisonSelect li:first-child").addClass("active")
		
		getSelectedChildTypeMembersForAlert(firstChildUserTypeIdString,userType);
	}
	function getSelectedChildTypeMembersForAlert(firstChildUserTypeIdString,childUserType){
	  $("#alertChildActivityMemberDivId").show();
	  $("#alertChildActivityMemberDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  $("#userTypeWiseChildDtlsTabForAlertId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  $("#candidateLocationAlertDtlsStatusWiseDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  
	  var childUserTypeIdsArray = firstChildUserTypeIdString.split(",");
	   var parentActivityMemberId = globalActivityMemberId;
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
					   parentActivityMemberId : parentActivityMemberId,
					   childUserTypeIdsArray : childUserTypeIdsArray,
					   reportType :"selectedUserType",
					   stateId : globalStateId,           
					   fromDate: customStartDateAlert,        
					   toDate :	customEndDateAlert,
					   scopeIdsArr:globalDistrictImpactLevelScopeArr,
					   alertIds :alertId,
					   editionIds :editionId,
					   alertStatusArr :  globalAlertStatusArr
				  }
	  $.ajax({
			type : 'POST',
			url : 'getAlertByUserTypeBasedOnAccessLevelAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#alertChildActivityMemberDivId").html(" ");
		  if(result != null && result.length > 0){
			  buildChildTypeMembersForAlertReslt(result,childUserType);
		  }else{
			 // $("#alertChildActivityMemberDivId").html("NO DATA AVAILABLE."); 
			  //$("#userTypeWiseChildDtlsTabForAlertId").hide();
			 // $(".impactLevelWiseComparisonBlockCls").hide();
		  }
		});
 }
 function buildChildTypeMembersForAlertReslt(result,childUserType){
	 var isDataAvailale = false;
	  var userTypeId = result[0].userTypeId;
	  var activityMemberId = result[0].activityMemberId;
	  var selectedMemberName = result[0].name;
	  var selectedUserType = result[0].userType;
	  var str = '';
	  str+='<ul class="list-inline alertslickPanelSlider">';
	  var rank=1; 
	   for(var i in result){
	    if(result[i].totalCount > 0){
			 isDataAvailale = true;
		 } 
	     //str+='<li style="cursor:pointer;" class="activityMemberClsForAlertBlock"  attr_selectedusertype="'+result[i].userType+'"  attr_id="userTypeWiseChildDtlsTabForAlertId"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' style="width:380px !important;">';
		 
		  str+='<li style="cursor:pointer;" id="panelColor'+result[i].activityMemberId+'" onclick="activityMemberClsForAlertBlock(\''+result[i].userType+'\',\'userTypeWiseChildDtlsTabForAlertId\',\''+result[i].name+'\',\''+result[i].activityMemberId+'\',\''+result[i].userTypeId+'\');" class=""  style="width:380px !important;">';
	     if(i==0){
			str+='<div class="panel panel-default panelSlick panelActiveSlick">';
		  }else{
		  str+='<div class="panel panel-default panelSlick">';
		  }
		  str+='<div class="panel-heading">';
			 str+='<h4 class="panel-title">'+result[i].name+'</h4>';
			 str+='<span class="count">'+rank+'</span>';
		 str+='</div>';
		 str+='<div class="panel-body">';
	   if(result[i].userTypeId != null && result[i].userTypeId==7 || result[i].userTypeId==9 || result[i].userTypeId==5 || result[i].userTypeId==6){// MLA Constituency Incharge, MP and District President Incharge 
		   var lctnName = result[i].locationName;
           lctnName = lctnName.substring(0, lctnName.lastIndexOf(" "));
		   str+='<h4 class="text-capital">'+result[i].userType+' ('+lctnName+')</h4>';	 
		 }else{
		   str+='<h4 class="text-capital">'+result[i].userType+'</h4>';	 
		 }
			str+='<div class="table-responsive">';
			 str+='<table class="table table-condensed text_align_center">';
				 str+='<thead>';
					 str+='<th>Total Alerts</th>';
					 str+='<th>Involved</th>';
				    str+='<th>Assigned</th>';
					str+='<th>Pending</th>';
					str+='<th>Completed</th>';
				 str+='</thead>';
				 str+='</tbody>';
				 str+='<tr>';
					 str+='<td>'+result[i].totalCount+'</td>';
					 str+='<td>'+result[i].negativeCount+'</td>';
					 str+='<td>'+result[i].positiveCount+'</td>';
					 var pendingPer=0;
					 var completedper=0;
					 if(result[i].subList != null && result[i].subList.length > 0){
						  for(var j in result[i].subList){
							  if(result[i].subList[j].id==1 || result[i].subList[j].id==4 ){
								if(result[i].subList[j].id==1){//Pending
									pendingPer = result[i].subList[j].positivePercentage;
								}
								if(result[i].subList[j].id==4){//Completed
									completedper = result[i].subList[j].positivePercentage;
								}
							  }
						  }
					 }
				   if(pendingPer > 0){
						str+='<td>'+pendingPer+'%</td>';	  
				   }else{
					   str+='<td> - </td>';	  
				   }
				  if(completedper > 0){
						str+='<td>'+completedper+'%</td>';	  
				   }else{
					   str+='<td> - </td>';	  
				   }
				 str+='</tr>';
				 str+='</tbody>';
			 str+='</table>';
			  str+='</div>';
		 str+='</div>';
	 str+='</div> ';
    str+=' </li> ';  
	rank=rank+1;
   }
   $("#alertChildActivityMemberDivId").html(str);
	$(".alertslickPanelSlider").slick({
			 slide: 'li',
			 slidesToShow: 3,
			 slidesToScroll: 3,
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 2
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
				// You can unslick at a given breakpoint now by adding:
				// settings: "unslick"
				// instead of a settings object
			  ]
		});  
		if(!isDataAvailale){
		  $("#alertChildActivityMemberDivId").hide();	
		} 
	     getDirectChildActivityAlertMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,"userTypeWiseChildDtlsTabForAlertId");
		 getCandidateAccessLocationAlertDtlsStatusWise(userTypeId,activityMemberId,selectedMemberName,selectedUserType);
 }
 function getDirectChildActivityAlertMemberDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId){
	           $("#"+childActivityMemberId).show();
			   $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	             var alertId = $("#alertTypeHiddenId").attr("attr_alert_id");
				if(alertId == undefined){
					alertId = 0;
				}
				var editionId = $("#alertEditionTypeHiddenId").attr("attr_alert_edition_id");
				if(editionId == undefined){
					editionId = 0;
				}
			
			   var childUserTypeIdsArray=[];
	             childUserTypeIdsArray.push(userTypeId);
				   var jsObj = { 
					   activityMemberId : activityMemberId,
					   childUserTypeIdsArray : childUserTypeIdsArray,
					   reportType :"directChild",
					   stateId : globalStateId,           
					   fromDate: customStartDateAlert,        
					   toDate :	customEndDateAlert,
					   scopeIdsArr:globalDistrictImpactLevelScopeArr,
					   alertIds :alertId,
					   editionIds :editionId,
					   alertStatusArr :  globalAlertStatusArr
				  }
				  
	   	$.ajax({
			type : 'POST',
			url : 'getDirectChildActivityAlertDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		  $("#"+childActivityMemberId).html('');
		  if(result != null && result.length > 0){
			  buildChildActivityMembersAlertDetails(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId);
		  }else{
			  $("#"+childActivityMemberId).hide();
			  if(childActivityMemberId == "userTypeWiseChildDtlsTabForAlertId"){
				//$("#"+childActivityMemberId).html("<h5><span  class='text-capital'>"+selectedMemberName+"</span> - <span class='text-capitalize'>"+selectedUserType+"</span> - ( No Data Available )</h5>");
			}
		  }
		});
	}
	
	function buildChildActivityMembersAlertDetails(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId){
		var str=''; 
		var isDataAvailale = false;
		str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
		 if(childActivityMemberId != "userTypeWiseChildDtlsTabForAlertId"){
			//str+='<span class="remveAlertSlcUsrType pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
			
			str+='<span onclick="remveAlertSlcUsrType(\''+childActivityMemberId+'\');" class=" pull-right" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
		 } 
		 str+='<div class="table-responsive">';
		 if(childActivityMemberId != "userTypeWiseChildDtlsTabForAlertId"){
			 str+='<table  class="table table-condensed tableHoverLevelsInner m_top20">';
		 }else{
			str+='<table class="table table-condensed tableHoverLevels m_top20">';  
		 }
				str+='<thead   class="bg_D8 text-capital">';
					str+='<th>Rank</th>';
					str+='<th>Designation</th>';
					str+='<th>Name</th>';
					str+='<th style="text-align:center;">Total Alerts</th>';
					str+='<th style="text-align:center;">Involved</th>';
					str+='<th style="text-align:center;">Assigned</th>';
					str+='<th style="text-align:center;">Pending</th>';
					str+='<th style="text-align:center;">Completed</th>';
				str+'=</thead>';
		str+='<tbody>';
		var rank=1;
		 for(var i in result){
			if(result[i].totalCount > 0){
				 isDataAvailale = true;
			 } 
		var yourValues = result[i].locationName;
		   //str+='<tr  class="childActivityMemberDtlsCls"  attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
		   
		    str+='<tr  onclick="childActivityMemberDtlsCls(\''+result[i].activityMemberId+'\',\''+result[i].userTypeId+'\',\''+result[i].name+'\',\''+result[i].userType+'\',\'childMemDtslId'+result[i].userTypeId+''+i+'\',\'tr'+result[i].userTypeId+''+i+'\');" class="" >';
			str+='<td>';
				str+='<span class="tableCount">'+rank+'</span>';
			str+='</td>';
		  if(yourValues.indexOf(',') == -1){
				//  var locationNameArr=result[i].locationName.split(" ");
			 	var locatinName = result[i].locationName;
                 locatinName = locatinName.substring(0, locatinName.lastIndexOf(" "));
				str+='<td>'+result[i].userType+' (<b>'+locatinName+'</b>)</td>';
			}else{
				str+='<td>'+result[i].userType+'</td>';
			}
		    str+='<td>'+result[i].name+'</td>';
			str+='<td style="text-align:center;">'+result[i].totalCount+'</td>';
			str+='<td style="text-align:center;">'+result[i].negativeCount+'</td>';
			str+='<td style="text-align:center;">'+result[i].positiveCount+'</td>';
			 var pendingPer=0;
			 var completedper=0;
			 if(result[i].subList != null && result[i].subList.length > 0){
				  for(var j in result[i].subList){
					  if(result[i].subList[j].id==1 || result[i].subList[j].id==4 ){
						if(result[i].subList[j].id==1){//Pending
							pendingPer = result[i].subList[j].positivePercentage;
						}
						if(result[i].subList[j].id==4){//Completed
							completedper = result[i].subList[j].positivePercentage;
						}
					  }
				  }
			 }
		   if(pendingPer > 0){
				str+='<td>'+pendingPer+'%</td>';	  
		   }else{
			   str+='<td> - </td>';	  
		   }
		  if(completedper > 0){
				str+='<td>'+completedper+'%</td>';	  
		   }else{
			   str+='<td> - </td>';	  
		   }
		 str+='</tr>';
		str+='<tr class="showHideTr" style="display:none" id="tr'+result[i].userTypeId+''+i+'" attr_id = "childMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='<td colspan="8"  id="childMemDtslId'+result[i].userTypeId+''+i+'">';
		str+='</td>';
		 rank=rank+1;
		 }
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
	    $("#"+childActivityMemberId).html(str);
		if(!isDataAvailale){
		  $("#userTypeWiseChildDtlsTabForAlertId").hide();	
		} 
	}
 function getCandidateAccessLocationAlertDtlsStatusWise(userTypeId,activityMemberId,selectedMemberName,selectedUserType){
	    $(".impactLevelWiseComparisonBlockCls").show();
	   // $("#selectedMemberHeadingId").html('<span class="text-capital">('+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+')</span>');	 
	    $("#candidateLocationAlertDtlsStatusWiseDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
					   activityMemberId : activityMemberId,
					   stateId : globalStateId,           
					   fromDate: customStartDateAlert,        
					   toDate :	customEndDateAlert,
					   scopeIdsArr:globalDistrictImpactLevelScopeArr,
					   alertIds :alertId,
					   editionIds :editionId,
					   alertStatusArr :  globalAlertStatusArr
				  }
	  $.ajax({
			type : 'POST',
			url : 'getDirectChildMemberAlertStatusWiseAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		  $("#candidateLocationAlertDtlsStatusWiseDivId").html(' ');
		   if(result != null && result.length > 0){
			buildCandidateAccessLocationAlertDtlsStatusWise(result,selectedMemberName,selectedUserType);   
		   }else{
			  // $("#candidateLocationAlertDtlsStatusWiseDivId").html("NO DATA Available.")
			   $(".impactLevelWiseComparisonBlockCls").hide();
		   }
		});
 } 
 function buildCandidateAccessLocationAlertDtlsStatusWise(result,selectedMemberName,selectedUserType)
 {
	 var isDataAvailale = false;
    var statusColorObj =  {"1":"#A27FC2","2":"#0175F3","3":"#3EC3FF","4":"#049968","5":"#F21A98","6":"#FD6E07","7":"#CF0001","8":"#FE9900","9":"#0C9514","10":"#82CA9C","11":"#C9AC82","12":"#ababab","13":"#5a8476"};	
	var str='';
	 var width = 100 / result.length;
	  str+='<div class="col-xs-12 col-sm-12 col-md-12">';
		   str+='<h4 class="text-capital impactLevelHeadingCls" style="background-color:#ddd;padding:6px;">Impact Scope Wise Alert<span class="text-capital">&nbsp&nbsp('+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+')</span></h4>';
	  for(var i in result){
		 str+='<h4 class="text-capital m_top10">'+result[i].name+'</h4><br>';
		      if(result[i].subList1 != null && result[i].subList1.length > 0){
				 var statusObj = result[i].subList1[0].statusList;
				 
				  str+='<ul class="list-inline">';
				  if(statusObj != null && statusObj.length > 0){
					 for(var k in statusObj){
						 str+='<li><span style="background-color:'+statusColorObj[statusObj[k].statusTypeId]+';border-radius:50%;display:inline-block;height:12px;width:12px;"></span>&nbsp;'+statusObj[k].statusType+'</li>';
					 } 
				  }  
				   str+='</ul>';
				  
				  str+='<ul class="alertsGraphUl list-inline slickSlider">';
				  for(var j in result[i].subList1){
					//str+='<h4 class="panel-title text-capital">'+result[i].subList1[j].name+'</h4>';
					if(result[i].subList1[j].alertCount > 0){
						str+='<li class="col-md-4 col-xs-12 col-sm-12">';//width
					     str+='<div id="statusWiseHighChart'+i+''+j+'"></div>';
						str+='</li>';					  
					}
					
				  } 
			 str+='</ul>';  
			
			  }
	  }
	   str+='</div>';  
	 $("#candidateLocationAlertDtlsStatusWiseDivId").html(str);
	 $(".slickSlider").slick({
			slide: 'li',
				 slidesToShow: 4,
				 slidesToScroll: 1,
				 infinite: false,
				//variableWidth: true,
				responsive: [
						{
						  breakpoint: 1024,
						  settings: {
							slidesToShow: 2,
							slidesToScroll: 2,
							infinite: true,
							dots: true
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
						// You can unslick at a given breakpoint now by adding:
						// settings: "unslick"
						// instead of a settings object
					  ]
				 
		});
	 
	 for(var i in result){
		  if(result[i].subList1 != null && result[i].subList1.length > 0){
			  for(var j in result[i].subList1){
				     var pendingAlertArr = [];
					 var notifiedAlertArr = [];
					 var actionInProgessAlertArr = [];
					 var completedAlertArr = [];
					 var unblTRslvAlertArr = [];
					 var actionNotRequiredAlertArr = [];
					 var duplicateAlertArr = [];
				  if(result[i].subList1[j].statusList != null && result[i].subList1[j].statusList.length > 0){
					  for(var k in result[i].subList1[j].statusList){
						   if(result[i].subList1[j].statusList[k].alertCount > 0){
							   isDataAvailale = true;
						   }
						   if(result[i].subList1[j].statusList[k].statusTypeId==1){
								pendingAlertArr.push(result[i].subList1[j].statusList[k].alertCount); 
								pendingAlertArr.push(result[i].subList1[j].statusList[k].assignedAlertCnt); 
								pendingAlertArr.push(result[i].subList1[j].statusList[k].involveAlertCnt); 
							}else if(result[i].subList1[j].statusList[k].statusTypeId==2){
								 notifiedAlertArr.push(result[i].subList1[j].statusList[k].alertCount);
								 notifiedAlertArr.push(result[i].subList1[j].statusList[k].assignedAlertCnt);
								 notifiedAlertArr.push(result[i].subList1[j].statusList[k].involveAlertCnt);
							}else if(result[i].subList1[j].statusList[k].statusTypeId==3){
								 actionInProgessAlertArr.push(result[i].subList1[j].statusList[k].alertCount);
								 actionInProgessAlertArr.push(result[i].subList1[j].statusList[k].assignedAlertCnt);
								 actionInProgessAlertArr.push(result[i].subList1[j].statusList[k].involveAlertCnt);
							}else if(result[i].subList1[j].statusList[k].statusTypeId==4){
								 completedAlertArr.push(result[i].subList1[j].statusList[k].alertCount);
								 completedAlertArr.push(result[i].subList1[j].statusList[k].assignedAlertCnt);
								 completedAlertArr.push(result[i].subList1[j].statusList[k].involveAlertCnt);
							}else if(result[i].subList1[j].statusList[k].statusTypeId==5){
								 unblTRslvAlertArr.push(result[i].subList1[j].statusList[k].alertCount);
								 unblTRslvAlertArr.push(result[i].subList1[j].statusList[k].assignedAlertCnt);
								 unblTRslvAlertArr.push(result[i].subList1[j].statusList[k].involveAlertCnt);
							}else if(result[i].subList1[j].statusList[k].statusTypeId==6){
								 actionNotRequiredAlertArr.push(result[i].subList1[j].statusList[k].alertCount);
								 actionNotRequiredAlertArr.push(result[i].subList1[j].statusList[k].assignedAlertCnt);
								 actionNotRequiredAlertArr.push(result[i].subList1[j].statusList[k].involveAlertCnt);
							}else if(result[i].subList1[j].statusList[k].statusTypeId==7){
								 duplicateAlertArr.push(result[i].subList1[j].statusList[k].alertCount);
								 duplicateAlertArr.push(result[i].subList1[j].statusList[k].assignedAlertCnt);
								 duplicateAlertArr.push(result[i].subList1[j].statusList[k].involveAlertCnt);
							}
					  }
				  }
				var mainJosnObjArr = [];
			   if(pendingAlertArr != null && pendingAlertArr.length > 0){
				mainJosnObjArr.push({name:'Pending',data:pendingAlertArr,color:"#A27FC2"});  
			  }
			   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
				mainJosnObjArr.push({name:'Notified',data:notifiedAlertArr,color:"#0175F3"});  
			  }
			  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
				mainJosnObjArr.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#3EC3FF"});  
			  }
			  if(completedAlertArr != null && completedAlertArr.length > 0){
				mainJosnObjArr.push({name:'Completed',data:completedAlertArr,color:"#049968"});  
			  }
			  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
				mainJosnObjArr.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#F21A98"});  
			  }
			  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
				mainJosnObjArr.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#FD6E07"});  
			  }
			  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
				mainJosnObjArr.push({name:'Duplicate',data:duplicateAlertArr,color:"#CF0001"});  
			  }
		    
			if(mainJosnObjArr.length > 0){
					$("#statusWiseHighChart"+i+''+j).highcharts({
						 chart: {
							type: 'column'
						},
						title: {
							useHTML: true,
							text: ''+result[i].subList1[j].name+'',
							style: {
								fontSize: '12px',
								fontFamily: '"Helvetica Neue",Helvetica,Arial,sans-serif',
								textTransform: "uppercase"
								
						}
						},
						xAxis: {
							 min: 0,
							 gridLineWidth: 0,
							 minorGridLineWidth: 0,
							categories: ['Total','Assigned','Involved']
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
					     valueSuffix: ' ',
					      shared:true
				        },
						legend: {
							reversed: true,
							 enabled:false
						},
						plotOptions: {
							series: {
								stacking: 'normal'
							}
						},
						series: mainJosnObjArr
					});	
			}
		 } 
	}     
}
   	if(!isDataAvailale){
		  $(".impactLevelWiseComparisonBlockCls").hide();	
	} 
 
 }
    $(document).on("click",".constituencyUl li",function(){
			$(".constituencyUl li").removeClass("active");
			$(this).addClass("active");
	});
	$(document).on("click",".districtUl li",function(){
			$(".districtUl li").removeClass("active");
			$(this).addClass("active");
	});
	$(document).on("click",".basicAlertSetClose",function(){
		$(this).closest(".basicAlertBlockDropDown").hide();
	});
function getLocationWiseAlertDetails(){
$(".collapseTblViewCls").removeClass("active");
$(".collapseHIghChartViewCls").addClass("active");
$("#hiddenLevelTypeId").attr("attr_level_type","locationWise");
getStateImpactandItsSubLevelAlert("locationWise");
getDistrictImpactandItsSubLevelAlert("Decending","0","locationWise");
getCorpGMCAlert("0","locationWise");
getConstituencyImpactandItsSubLevelAlert("Decending","0","0","locationWise");
getAssignGroupTypeAlertDtlsByImpactLevelWise(0);
};	

$(document).on("click",".checkedAlertsCls",function(){      
	$(".checkedAlertsCls").prop("checked",false);
	$(this).prop("checked",true);	
});