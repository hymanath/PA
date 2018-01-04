
/* New Tours Ajax Call Based on New Screen */	

  var globalStateIdForTour=1; //for 
 var globalFormTourDate;
 var glovalToTourDate;
var customStartToursDate = moment().startOf('month').format('DD/MM/YYYY')
var customEndToursDate = moment().format('DD/MM/YYYY');
	function globalToursCalls(type)
	{
		 if (type == "selectedDate") {
			 customStartToursDate = globalFormTourDate;
			 customEndToursDate  = glovalToTourDate;
		 }
		if(type == "default"){
			
			$('#tourNewDateRangePickerId').data('daterangepicker').setStartDate(moment());
			$('#tourNewDateRangePickerId').data('daterangepicker').setEndDate(moment());
			customStartToursDate = moment().format('DD/MM/YYYY')
			customEndToursDate = moment().format('DD/MM/YYYY')
			$("#toursNewHeadingId").html("TODAY"+" ( "+moment().format('DD/MM/YYYY')+"-"+moment().format('DD/MM/YYYY')+" )");
		}else if(type == "currentMonth"){
			$('#tourNewDateRangePickerId').data('daterangepicker').setStartDate(moment().startOf("month"));
			$('#tourNewDateRangePickerId').data('daterangepicker').setEndDate(moment().endOf("month"));
			customStartToursDate = moment().startOf("month").format('DD/MM/YYYY')
			customEndToursDate = moment().endOf("month").format('DD/MM/YYYY')
			$("#toursNewHeadingId").html("THIS MONTH"+" ( "+moment().startOf("month").format('DD/MM/YYYY')+"-"+moment().endOf("month").format("DD/MM/YYYY")+" )");
		}else if(type == "lastMonth"){
			$('#tourNewDateRangePickerId').data('daterangepicker').setStartDate(moment().subtract(1,'month').startOf("month"));
			$('#tourNewDateRangePickerId').data('daterangepicker').setEndDate(moment().subtract(1,'month').endOf("month"));
			customStartToursDate = moment().subtract(1,'month').startOf("month").format('DD/MM/YYYY')
			customEndToursDate = moment().subtract(1,'month').endOf("month").format('DD/MM/YYYY')
			$("#toursNewHeadingId").html("LAST MONTH"+" ( "+moment().subtract(1,'month').startOf("month").format('DD/MM/YYYY')+"-"+moment().subtract(1,'month').endOf("month").format('DD/MM/YYYY')+" )");
		}
		$("#tourNewDateRangePickerId").val(customStartToursDate+" - "+customEndToursDate);
		getToursBasicOverviewDtls();
		if($(".NewTourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			getDesignationWiseMembersDtls();
			if($('.moreNewToursBlocksDetailed').css('display') != 'none'){
				var isFilterApply = "No";
				var filterType = "";
				var desgnatnIdsLst = [];
				getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,0,0,0,0,0,0,0,0,0,0,"");
			}
		}
		
	}
	$(document).on("click",".toursRefresh",function(){
		globalToursCalls('selectedDate');
	});
	$("#tourNewDateRangePickerId").daterangepicker({
		opens: 'left',
	     startDate: moment().subtract(1, 'month').startOf('month'),
         endDate:moment().subtract(1, 'month').endOf('month'),  
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: { ////moment().endOf('Year')
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(1, 'years').startOf('year'), moment()],
        }
	});
	function getDay(){
		var date = new Date();
		var dd = date.getDate(); 
		return dd;
	}
	   var dates=$("#tourNewDateRangePickerId").val();
		if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			globalFormTourDate = datesArr[0]; 
			glovalToTourDate = datesArr[1]; 
		}
      $("#toursNewHeadingId").html("THIS Month( "+globalFormTourDate+" To "+glovalToTourDate+")");
      $('#tourNewDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
	   var dates= $("#tourNewDateRangePickerId").val();
	   $("#toursNewHeadingId").html(picker.chosenLabel+" ( "+dates+" )");
	   	if(dates != null && dates!=undefined){
			var datesArr = dates.split("-");
			globalFormTourDate = datesArr[0]; 
			glovalToTourDate = datesArr[1]; 
		}
		if($(".NewTourExpand").find("i").hasClass("glyphicon-resize-small"))
		{
			getDesignationWiseMembersDtls();
		}
		
		 getToursBasicOverviewDtls();
	 });
	 
	
	 $(document).on("click",".closeShowPdfCls",function(){
		setTimeout(function(){
		$('body').addClass("modal-open");
		}, 500);                     
	}); 
//Closing model when esc key has been pressed
  $('body').keypress(function(e){
    if(e.keyCode == 27){
          var isModalOpened = $("#tourNewDocumentId").attr("isModalOpened");
		   if(isModalOpened == "true"){
			   setTimeout(function(){
				 $('body').addClass("modal-open");
			   }, 500);      
			   $("#tourNewDocumentId").attr("isModalOpened","false");
           }
	    var selectLevel = $(".tourIndividualCls").attr("attr_type");
		 if(selectLevel == "subLevel"){
			setTimeout(function(){
			$('body').addClass("modal-open");
			}, 500);       
		  $(".tourIndividualCls").attr("attr_type","subLevel");			
		 }
    }
  }); 
  
	/* $(document).on("click",".NewTourExpand",function(){
		$(this).attr("isExpand","true");
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".NewToursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".NewToursBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		
		$(".NewTourExpandCls").show();
		
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			getDesignationWiseMembersDtls();
			$(".NewToursHiddenBlock").show();
	        $(".hideShowNewToursDateRangeCls").show();	
			setTimeout(function(){
				$('html,body').animate({
					scrollTop: $(".NewToursBlock").offset().top},
				'slow');
			},500);
		}else{
			$(this).attr("isExpand","false");
			$(".NewTourExpandCls").hide();
			$(".moreNewToursBlocksIcon").show();
			$(".NewToursHiddenBlock").hide();
			$(".moreNewToursBlocksDetailed").hide(); 
			$(".hideShowNewToursDateRangeCls").hide();
		}
		if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
			$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerCls").toggleClass("hide");
			$(".moreBlocksIcon").removeClass("unExpandBlock");
		}else if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
			$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
			$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".meetingsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".meetingsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").hide();
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForMeetings").toggleClass("hide");
			$(".moreMeetingsBlocks1").hide();
			$(".moreMeetingsBlocksDetailed").hide();
			$(".moreMeetingsBlocksComparision").hide();
		}else if( $(".newsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".newsHiddenBlock,.morenewsBlocksIcon,.newsHiddenMoreBlock").hide();
			$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForNews").toggleClass("hide");
		}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents,.comparisonBlockActivities ").hide();
			$(".panelBlockCollapseIcon").addClass("collapsed");
			$(".activitesExpandIcon").parent().parent().parent().parent().find(".collapse").removeClass("in").addClass("collapsed");
			$(".activitesExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
			$(".eventsListExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
			$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForEvents").toggleClass("hide");
		}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
			$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
			$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".attendaceIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".attendaceIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".attendanceBlockMore,.moreAttBlocks,.moreAttBlocksIcon").hide();
			$(".dateRangePickerClsForAttendance").toggleClass('hide');
			$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
			$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".alertsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".districtAltCtnCls ,.alertLocationDiv,.dateRangePickerClsForAlert,.alertComparisonblock").hide();
				$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
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
	$(document).on("click",".moreNewToursBlocksIcon",function(){	
		$(".moreNewToursBlocks,.moreNewToursBlocksDetailed").toggle(); 
		//$(".moreNewToursBlocksDetailed").toggle(); 
		var isFilterApply = "No";
		var filterType = "";
		var desgnatnIdsLst = [];
	   getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,0,0,0,0,0,0,0,0,0,0,"");
		
	});

	$(document).on("click",".showHideFiltersToursSec",function(){
		$(".sliderCategoryTypeCls").attr("checked",true);
		var dropBlockId = $(this).attr("attr_id");
		$("#toursSessionDropDown"+dropBlockId).toggle(); 
		
   });
   $(document).on("click",".toursBlockCloseCls",function(){
	var dropBlockId = $(this).attr("attr_id");
	$("#toursSessionDropDown"+dropBlockId).toggle(); 
	
   });
	function isPageExpand(){
		var isExpand = $(".NewTourExpand").attr("isExpand");
		 if(isExpand=="true"){
			getDesignationWiseMembersDtls();
			var isFilterApply = "No";
			var filterType = "";
			var desgnatnIdsLst = [];
		    getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,0,0,0,0,0,0,0,0,0,0,"");
		 }
	}
	
	
	var globalDesignationSelectBoxString="";
	var globalDesignationComplainceSelectBoxString="";
	var globalDesignationNonCmplncSlctBxStrng="";
	var globalDesignationNotSubmttedSlctBxStrng="";
	var globalDesignationSubmttedSlctBxStrng="";
	
	function getToursBasicOverviewDtls()
	{    globalDesignationSelectBoxString="";
	     globalDesignationComplainceSelectBoxString="";
		 globalDesignationNonCmplncSlctBxStrng="";
		 globalDesignationNotSubmttedSlctBxStrng="";
		 globalDesignationSubmttedSlctBxStrng="";
		 isPageExpand();
	    getCandiateWiseTourSubmittedDetails();//getting unique candiate wise details
		$("#tourOverviewNewDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#tourOverviewNewDivId").html(' ');
			 return;
		}
		var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate : globalFormTourDate,
					 toDate : glovalToTourDate,
					 userTypeId : globalUserTypeId
				  }
		$.ajax({
			type : 'POST',
			url : 'getToursBasicOverviewDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null ){
			  buildTourOverviewRslt(result);	
			}else{
			 $("#tourOverviewNewDivId").html("NO DATA AVAILABLE.");	
			}
		});
	}
	function buildTourOverviewRslt(result){
		var overViewRslt = result.overAllDetailsVO;
		var designationWiseList = result.subList;
	    var tursDesgntnIdsString;
		var str='';
		str+='<div class="toursScroll">';
		 if(overViewRslt != null){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h4><span class="headingColor text-capital">Overall&nbspDesignation Wise Leaders</span></h4>';
					str+='<div id="" class="m_top10 ">';
						str+='<div class="row">';
							str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top5">';
								str+='<div class="pad_5  bg_ED">';
									if(overViewRslt.noOfLeaderCnt > 0){
									  str+='<h3 class="tourOverViewCls overAllTourCls"  attr_designation_name="Overall"  style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="all">'+overViewRslt.noOfLeaderCnt+'</h3>';	
									}else{
									  str+='<h3>0</h3>';	
									}
									str+='<p class="text-muted text-capital">total leaders</p>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top5">';
								str+='<div class="pad_5  bg_ED">';
								   if(overViewRslt.submitedLeaderCnt > 0){
									  str+='<h3 class="tourOverViewCls overAllTourCls"  attr_designation_name="Overall"  style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="submitted" >'+overViewRslt.submitedLeaderCnt+'&nbsp<small class="text-success" style="font-size:13px">'+overViewRslt.submitedCandidateTourPer+'%</small></h3>';	
									}else{
									  str+='<h3>0</h3>';	
									}
									//str+='<small class="text-success" style="font-size:13px">'+overViewRslt.submitedCandidateTourPer+'%</small></h3>';
									str+='<p class="text-muted text-capital">Submitted leaders</p>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top5">';
								str+='<div class="pad_5  bg_ED">';
									if(overViewRslt.notSubmitedLeaserCnt > 0){
									  str+='<h3 class="tourOverViewCls overAllTourCls" attr_designation_name="Overall"  style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="notSubmitteed">'+overViewRslt.notSubmitedLeaserCnt+'&nbsp<small class="text-success" style="font-size:13px">'+overViewRslt.notsubmitedCandidateTourPer+'%</small></h3>';	
									}else{
									  str+='<h3>0</h3>';	
									}
									//str+='<small class="text-success" style="font-size:13px">'+overViewRslt.notsubmitedCandidateTourPer+'%</small></h3>';
									str+='<p class="text-muted text-capital">Not Submitted leaders</p>';
								str+='</div>';
							str+='</div>';
							if(overViewRslt.submitedLeaderCnt > 0){
									str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top5">';
									str+='<div class="pad_5 bg_ED">';
									   if(overViewRslt.submitedLeaderCnt > 0){
										  str+='<h3 class="tourOverViewCls overAllTourCls" attr_designation_name="Overall"  style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="submitted">'+overViewRslt.submitedLeaderCnt+'</h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										str+='<p class="text-muted text-capital">Submitted leaders</p>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top5">';
									str+='<div class="pad_5 bg_ED">';
									  if(overViewRslt.complainceCnt > 0){
										  str+='<h3 class="tourOverViewCls overAllTourCls" attr_designation_name="Overall" style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="Complaince">'+overViewRslt.complainceCnt+'</h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										str+='<p class="text-muted text-capital">Complaince</p>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top5">';
									str+='<div class="pad_5 bg_ED">';
										if(overViewRslt.nonComplainceCnt > 0){
										  str+='<h3 class="tourOverViewCls overAllTourCls"  attr_designation_name="Overall" style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="nonComplaince">'+overViewRslt.nonComplainceCnt+'</h3>';	
										}else{
										  str+='<h3>0</h3>';	
										}
										str+='<p class="text-muted text-capital">Non-Complaince</p>';
									str+='</div>';
								str+='</div>';	
							}
						str+='</div>';
					str+='</div>';
				str+='</div>';	
		    }
		
		    if(designationWiseList != null && designationWiseList.length > 0){
				
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10 ">';
				str+='<div class="" >';
				for(var i in designationWiseList){
				  var designationids;
				   if(designationWiseList[i].id==4){//organization SECRETARY and SECRETARY
					  designationids='4,5'; 
				   }else{
					designationids=designationWiseList[i].id;  
				   }
				   
				     globalDesignationSelectBoxString+='<option value="'+designationWiseList[i].id+'">'+designationWiseList[i].name+'</option>';
					 
				   if(designationWiseList[i].complainceCnt > 0){
					 globalDesignationComplainceSelectBoxString+='<option value="'+designationWiseList[i].id+'">'+designationWiseList[i].name+'</option>';	
					}
				    if(designationWiseList[i].nonComplainceCnt > 0){
				      globalDesignationNonCmplncSlctBxStrng+='<option value="'+designationWiseList[i].id+'">'+designationWiseList[i].name+'</option>';	
					}
					if(designationWiseList[i].submitedLeaderCnt > 0){
					  globalDesignationSubmttedSlctBxStrng+='<option value="'+designationWiseList[i].id+'">'+designationWiseList[i].name+'</option>';		
					}
					if(designationWiseList[i].notSubmitedLeaserCnt > 0){
					 globalDesignationNotSubmttedSlctBxStrng+='<option value="'+designationWiseList[i].id+'">'+designationWiseList[i].name+'</option>';	
					} 
					
					
					if(i== 0){
					 tursDesgntnIdsString = designationids;	
					 }else{
					  tursDesgntnIdsString = tursDesgntnIdsString+','+designationids;	
					}
				   str+='<div >';
							str+='<h4><span class="text-capital">'+designationWiseList[i].name+'</span></h4>';
							str+='<div class="dropup findclass">';
							str+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -16px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
								str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;">';
									str+='<p><span style="font-size: 20px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 17px;">Tours Days Target Per Month</i></p>';
									str+='<table class="table">';
									 var monthWiseTarget = designationWiseList[i].subList; 
											 if(monthWiseTarget != null && monthWiseTarget.length > 0){
												 for(var k in monthWiseTarget){
												    str+='<tr>';
													if(monthWiseTarget[k].name != null && monthWiseTarget[k].name.length > 0){
													str+='<td style="border-top:none !important">'+monthWiseTarget[k].name+'</td>';	
													}else{
													str+='<td style="border-top:none !important"> - </td>';	
													}
													str+='<td>'+monthWiseTarget[k].targetDays+'</td>';
												   str+='</tr>'; 
												 }
											 }
									str+='</table>';
								str+='</div>';
							str+='</div>';
							
							str+='<div class="bg_ED m_top10">';
								str+='<div class="row">';
									str+='<div class="col-md-12 col-xs-12 col-sm-12">';
										str+='<div class="table-responsive">';
											str+='<table class="table border_top_none">';
												str+='<tr>';
													str+='<td class="text-muted f_14">Total Leaders</td>';
													str+='<td class="text-muted f_14">Not Submitted</td>';
													str+='<td class="text-muted f_14">Submitted</td>';
													str+='<td class="text-muted f_14">Complaince</td>';
													str+='<td class="text-muted f_14">Non-Complaince</td>';
												str+='</tr>';
													str+='<tr>';
													if(designationWiseList[i].noOfLeaderCnt > 0){
													str+='<td><h4 attr_dsgntn_ids='+designationids+' attr_tour_filter_type="all" attr_designation_name="'+designationWiseList[i].name+'" style="cursor:pointer;color:rgb(51, 122, 183)"  class="responsiveFont tourOverViewCls">'+designationWiseList[i].noOfLeaderCnt+'</h4></td>';	
													}else{
													str+='<td><h4   class="responsiveFont">0</h4></td>';	
													}
													if(designationWiseList[i].notSubmitedLeaserCnt > 0){
													str+='<td><h4 class="responsiveFont tourOverViewCls" attr_dsgntn_ids='+designationids+' attr_tour_filter_type="notSubmitteed" attr_designation_name="'+designationWiseList[i].name+'" style="cursor:pointer;color:rgb(51, 122, 183)">'+designationWiseList[i].notSubmitedLeaserCnt+'&nbsp<small class="text-success">'+designationWiseList[i].notsubmitedCandidateTourPer+'%</small></h4></td>';
													}else{
													str+='<td><h4   class="responsiveFont">0</h4></td>';	
													}
													if(designationWiseList[i].submitedLeaderCnt > 0){
													str+='<td><h4 class="responsiveFont tourOverViewCls" attr_dsgntn_ids='+designationids+' attr_tour_filter_type="submitted" attr_designation_name="'+designationWiseList[i].name+'" style="cursor:pointer;color:rgb(51, 122, 183)">'+designationWiseList[i].submitedLeaderCnt+'&nbsp<small class="text-success">'+designationWiseList[i].submitedCandidateTourPer+'%</small></h4></td>';
													}else{
													str+='<td><h4   class="responsiveFont">0</h4></td>';	
													}
													if(designationWiseList[i].complainceCnt > 0){
													str+='<td><h4 class="responsiveFont tourOverViewCls" attr_dsgntn_ids='+designationids+' attr_tour_filter_type="Complaince" attr_designation_name="'+designationWiseList[i].name+'" style="cursor:pointer;color:rgb(51, 122, 183)">'+designationWiseList[i].complainceCnt+'</h4></td>';
													}else{
													str+='<td><h4   class="responsiveFont">0</h4></td>';	
													}
													if(designationWiseList[i].nonComplainceCnt > 0){
													str+='<td><h4 class="responsiveFont tourOverViewCls" attr_dsgntn_ids='+designationids+' attr_tour_filter_type="nonComplaince" attr_designation_name="'+designationWiseList[i].name+'" style="cursor:pointer;color:rgb(51, 122, 183)">'+designationWiseList[i].nonComplainceCnt+'</h4></td>';
													}else{
													str+='<td><h4   class="responsiveFont">0</h4></td>';	
													}
												str+='</tr>';
											str+='</table>';
										str+='</div>';
										str+='<hr style="margin: 10px 0 0;border-top: 1px solid #ccc"/>';
									str+='</div>';
									if(designationWiseList[i].submitedLeaderCnt > 0){
													str+='<div class="col-md-12 col-xs-12 col-sm-12">';
										str+='<div class="table-responsive">';
										str+='<table class="table tableEMN ">';
											str+='<thead>';
												str+='<th style="border-bottom:none !important"></th>';
												str+='<th class="text-muted f_14">Submitted</th>';
												str+='<th class="text-muted f_14">Compliance</th>';
												str+='<th class="text-muted f_14">Non-Compliance</th>';
											str+='</thead>';
											str+='<tbody>';
											 var categoryList = designationWiseList[i].subList3; 
											 if(categoryList != null && categoryList.length > 0){
												 for(var j in categoryList){
												    str+='<tr>';
													if(categoryList[j].name != null && categoryList[j].name.length > 0){
													str+='<td style="border-top:none !important">'+categoryList[j].name+'</td>';	
													}else{
													str+='<td style="border-top:none !important"> - </td>';	
													}
													str+='<td class="bg_D8 text-center">'+categoryList[j].submitedLeaderCnt+'</td>';
													str+='<td class="bg_D8 text-center">'+categoryList[j].complainceCnt+'</td>';
													str+='<td class="bg_D8 text-center">'+categoryList[j].nonComplainceCnt+'</td>';
												   str+='</tr>'; 
												 }
											 }
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
									str+='</div>';	
								}
								str+='</div>';
							str+='</div>';
						str+='</div>';	
				}
				str+='</div>';	
				str+='</div>';	
			}
			str+='</div>';	
		    $("#tourOverviewNewDivId").html(str);  
			//$( ".findclass" ).first().addClass("dropdown");
			//$( ".findclass" ).first().removeClass("dropup");
			$(".overAllTourCls").attr("attr_dsgntn_ids",tursDesgntnIdsString);  
			if(designationWiseList.length > 3)
			{
				$(".toursScroll").mCustomScrollbar({setHeight:'340px'});
			}
		}
	
	
	$(document).on("click",".tourComplainceCls",function(){
		  var resultType=$(this).attr("attr_value");
		 if(resultType != null && resultType == "strong"){
		  buildgDesignationWiseToursTopFiveComplainceRslt(globalUserTypeWiseTourComplainceRslt); 
		 }else if(resultType == "poor"){
		  buildgDesignationWiseToursPoorFiveComplainceRslt(globalUserTypeWiseTourComplainceRslt);
		 }
    });
	
    var globalUserTypeWiseTourComplainceRslt;  
	function getDesignationWiseMembersDtls()
	{  
		 
		$(".tourNewComplainceFilterCls li").removeClass("active");
		$(".tourNewComplainceFilterCls li:first-child").addClass("active");

 		$("#buildgDesignationWiseToursTopFiveComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#buildgDesignationWiseToursTopFiveComplainceDivId").html(' ');
			 return;
		}
		var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate : globalFormTourDate,
					 toDate : glovalToTourDate,
					 userTypeId : globalUserTypeId
				  }
		$.ajax({
			type : 'POST',
			url : 'getDesignationWiseMembersDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#buildgDesignationWiseToursTopFiveComplainceDivId").html(' ');
			 buildgDesignationWiseToursTopFiveComplainceRslt(result);
			 globalUserTypeWiseTourComplainceRslt = result;
		});
	}
	
	function buildgDesignationWiseToursTopFiveComplainceRslt(result){
		var str='';
		if(result != null && result.length > 0){
		  var str='';
		  
		  str+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
		  str+='<div class="scollGraphDiv">';
		  str+='<div class="row">';
		  for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
				 if(result[i][0].designationId==4 || result[i][0].designationId==5){
				  if(result[i][0].designationId==4){
				  if(result[i][0].complaincePer!=0){
					  str+='<h5 class="text-capital">'+result[i][0].designation+' / SECRETARY</h5>';      
				  }
				  }
				  if(result[i][0].designationId==5){
				   if(result[i][0].complaincePer!=0){
					 str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].designation+'</h5>';      
				   }
			     }
			   }else{ 
				 if(result[i][0].complaincePer!=0){
					str+='<h5 class="text-capital">'+result[i][0].designation+'</h5>'; 
				 }
		      }
			  str+='<div id="designationWiseComplainceTour'+i+'" style="height:180px;"></div>';
			str+='</div>'
		  }
		  str+='</div>'
		  str+='</div>'
		  str+='</div>'
		}
		$("#buildgDesignationWiseToursTopFiveComplainceDivId").html(str);
		if(result != null && result.length > 0){
			$(".scollGraphDiv").mCustomScrollbar({setHeight:'800px'})
		}
	   if(result != null && result.length > 0){
			for(var i in result){
				var candidateNameArray = [];
				var totalComplainceArr = [];
				var countVar =0;
			  if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						countVar =countVar+1;
						candidateNameArray.push(result[i][j].name.toUpperCase());
						totalComplainceArr.push({"y":result[i][j].complaincePer,"extra":result[i][j].id+"-"+result[i][j].name+"-"+result[i][j].designation});
						
						if (countVar === 5) {
							break;
						}
					}
				}
		if(result[i][0].complaincePer!=0){
			$(function () {
			  $('#designationWiseComplainceTour'+i).highcharts({
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
					categories: candidateNameArray,
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
					
				},
				tooltip: {formatter: function(){
					return '<b>Tour Complaince:'+ Highcharts.numberFormat(this.y, 2) +'%</b><br/>'+
                    'Name: '+ this.x;   
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
									return Highcharts.numberFormat(this.y,2) +"%"; 
								}
							}
						  
						}
					},
					series: {
						cursor: 'pointer',
							point: {
								events: {
								click: function () {
									getIndividualPersonTourDetails(this.extra);
								}
							}
						}
					},
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
				
				series: [{
					name: 'Complaince',
					data: totalComplainceArr
				}]
			});
		});
		}else{
		$("#designationWiseComplainceTour"+i).html("No Data Available");
		$("#designationWiseComplainceTour"+i).css("height","35px");
		$("#designationWiseComplainceTour"+i).hide();
		} 
	}
	}else{
    $("#buildgDesignationWiseToursTopFiveComplainceDivId").html('NO DATA AVAILABLE.');
	}
	}
	
	function buildgDesignationWiseToursPoorFiveComplainceRslt(result){
		
		var str='';
		if(result != null && result.length > 0){
			var str='';
			  str+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
			  str+='<div class="scollGraphDiv">';
			  str+='<div class="row">';
			for(var i in result){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				 if(result[i][0].designationId==4 || result[i][0].designationId==5){
				  if(result[i][0].designationId==4){
				   str+='<h5 class="text-capital">'+result[i][0].designation+' / SECRETARY </h5>';      
				  }
				  if(result[i][0].designationId==5){
				   str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].designation+'</h5>';      
				  }
			   }else{
				str+='<h5 class="text-capital">'+result[i][0].designation+'</h5>'; 
			   }
				str+='<div id="designationWiseComplainceTour'+i+'" style="height:180px;"></div>';
				str+='</div>'
			}
			str+='</div>'
			str+='</div>'
			str+='</div>'
		}
		$("#buildgDesignationWiseToursTopFiveComplainceDivId").html(str);
		if(result != null && result.length > 0){
			$(".scollGraphDiv").mCustomScrollbar({setHeight:'800px'})
		}
	  if(result != null && result.length > 0){
		for(var i in result){
				var candidateNameArray = [];
				var totalComplainceArr = [];
				var countVar = 0;
				if(result[i] != null && result[i].length > 0){
					var length = result[i].length - 1;
					for(var j = length; j >= 0; j--){
						candidateNameArray.push(result[i][j].name.toUpperCase());
						  totalComplainceArr.push({"y":result[i][j].complaincePer,"extra":result[i][j].id+"-"+result[i][j].name+"-"+result[i][j].designation});
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}	
				}
			//if( result[i][j].complaincePer!=0){
		//if(totalComplainceArr.length > 0){	
			var getWidth = $("#designationWiseComplainceTour"+i).parent().width()+'px';
				$("#designationWiseComplainceTour"+i).width(getWidth);
				$(function () {
			   $('#designationWiseComplainceTour'+i).highcharts({
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
					categories: candidateNameArray,
					title: {
						text: null
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
						enabled: false,
					}
				},
				tooltip: {formatter: function(){
					return '<b>Total Complaince:'+ Highcharts.numberFormat(this.y, 2) +'%</b><br/>'+
                    'Name: '+ this.x; 
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
									return Highcharts.numberFormat(this.y,2)+"%";
								}
							}
						  
						}
					},
					series: {
						cursor: 'pointer',
							point: {
								events: {
								click: function () {
									getIndividualPersonTourDetails(this.extra);
								}
							}
						}
					},
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
					name: 'Complaince',
					data: totalComplainceArr
				}]
			});
		});
		//}
		/* }else{
		$("#genSecTour"+i).html("No Data Available");
		$("#genSecTour"+i).css("height","35px");	
		} */
		}
	}else{
	 $("#buildgDesignationWiseToursTopFiveComplainceDivId").html('NO DATA AVAILABLE.');
	}
	} 
/* 	$(document).on("click",".complainceDaysCls",function(){
		var monthList = $(this).attr("attr_month_list");
		console.log(monthList);
	}); */
	
	$(document).on("click",".tourFilterCls",function(){
		var designationStr = $(this).attr("attr_designation_id");
		var divId = $(this).attr("attr_div_id");
		var filterType = $(this).attr("attr_result_type");
		var isFilterApply = "Yes";
		var desgnatnIdsLst = designationStr.split(",");
		 $(".toursSessionDropDownCls").hide();	
		getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,0,0,0,0,0,0,0,0,0,0,divId);
		//$(".tourFilteCheckBoxCls").trigger("click");
	});
	
	 $(document).on("click",".tourFilteCheckBoxCls",function(){
		$(".tourFilteCheckBoxCls").prop('checked', false);
		$(this).prop('checked', true);
		/* var sliderName = $(this).closest(".tab-pane").find(".tourSearchBtnCls").attr("attr_slider_name");
		var mainSliderName = $(this).closest(".tab-pane").find(".tourSearchBtnCls").attr("attr_main_slider_name");
		var designationStr = $(this).attr("attr_designation_id");
		var sliderNameArr = sliderName.split(",");
		if($(this).is(":checked")){
			console.log("checked");
			var sliderType = $(this).attr("attr_slider_type");
			  if(sliderType=="tourCategory"){
				  for(var i in sliderNameArr){
					 var className = sliderNameArr[i]+''+designationStr+"cls";
					 if(designationStr != null && designationStr.length > 1){
						var secOrgIds = designationStr.split(",");
						 className = sliderNameArr[i]+''+secOrgIds[0]+secOrgIds[1]+"cls";
					 }
					  // $('.'+className).slider('setAttribute', 'max', 0);
					  // $('.'+className).slider('setAttribute', 'min', 0);
					  // $('.'+className).slider('refresh');
					  console.log("class name  "+className)
					    var strMin=0;
						var strMax=0;

						 $('.'+className).slider( "option", "max", 0);
						 $('.'+className).slider( "option", "min", 0);

					//$('.'+className).attr("data-slider-min",0);
					// $('.'+className).attr("data-slider-max",0);
					// var value = $slider.data('slider').getValue();
				 	//$('.'+className).slider('disable');
				  } 
			  }else if(sliderType=="main"){
				    var mainSliderCls = mainSliderName+''+designationStr+"cls";
					 if(designationStr != null && designationStr.length > 1){
						var secOrgIds = designationStr.split(",");
						 mainSliderCls = mainSliderName+''+secOrgIds[0]+secOrgIds[1]+"cls";
					 }
					 // $('.'+mainSliderCls).slider('disable');
					  $('.'+mainSliderCls).attr("data-slider-min",0);
					  $('.'+mainSliderCls).attr("data-slider-max",0);
			 }
		}  */
	}); 
	
	$(document).on("click",".tourSearchBtnCls",function(){
		  var designationStr = $(this).attr("attr_designation_id");
		  var divId = $(this).attr("attr_div_id");
		  var sliderName = $(this).attr("attr_slider_name");
		  var mainSliderName = $(this).attr("attr_main_slider_name");
		  var desgnatnIdsLst = designationStr.split(",");
		  var sliderNameArr = sliderName.split(",");
		  var isFilterApply="Yes";
		  var filterType=" ";
		  var ownDistValue = 0;
		  var ownCnsttuncyValue = 0;
		  var incharegeConstituencyValue = 0;
		  var ichargeDistrictValue = 0;
		  var govtWorkValue = 0;
		  var stateTourCategoryValue = 0;
		  var complainceValue = 0; 
		  var anganwadiVisitValue = 0;
		  var ownAreaValue = 0;
		  var inchargeParliamentValue = 0;
		  var tourCategorySliderType = $(this).closest(".tab-pane").find('.sliderCategoryTypeCls:checkbox:checked').attr("attr_slider_type");
		  var mainSlider = $(this).closest(".tab-pane").find('.mainSliderCls:checkbox:checked').attr("attr_slider_type");
		  if(tourCategorySliderType == undefined && mainSlider==undefined){
			 $(this).closest(".tab-pane").find(".errorCls").html("Please Select Filter Type.");
			 return;
		  }
		  $(this).closest(".tab-pane").find(".errorCls").html(' ');
		  if(tourCategorySliderType != undefined && tourCategorySliderType=="tourCategory"){
			 if(sliderNameArr != null && sliderNameArr.length > 0){
					 for(var i in sliderNameArr){
					
						 var className = sliderNameArr[i]+''+designationStr+"cls";
						 if(desgnatnIdsLst != null && desgnatnIdsLst.length > 1){
							var secOrgIds = designationStr.split(",");
							 className = sliderNameArr[i]+''+secOrgIds[0]+secOrgIds[1]+"cls";
						 }
					   if(sliderNameArr[i]=="InchargeDistrict"){
						   
							var InchargeDistrictSliderValue = $("."+className).val();
							ichargeDistrictValue = InchargeDistrictSliderValue;
							
						 }else if(sliderNameArr[i]=="OwnDistrict"){
							 
							var ownDistrictSliderValue = $("."+className).val();
							ownDistValue = ownDistrictSliderValue;
						 
						 }else if(sliderNameArr[i]=="InchargeConstituency"){
							 
						var  InchargeConstituencySliderValue = $("."+className).val();
							incharegeConstituencyValue = InchargeConstituencySliderValue;
							
						 }else if(sliderNameArr[i]=="OwnConstituency"){
							 
							var ownConstituencySliderValue = $("."+className).val();
							  ownCnsttuncyValue = ownConstituencySliderValue;
							  
						 }else if(sliderNameArr[i]=="Govt"){
							var govtSliderValue = $("."+className).val();
							govtWorkValue = govtSliderValue;
						
						} else if(sliderNameArr[i]=="State"){
							var stateTourCategorySliderValue = $("."+className).val();
							stateTourCategoryValue = stateTourCategorySliderValue;
							
						} else if(sliderNameArr[i]=="AnganwadiVisits"){
							var anganwadiVisitSliderValue = $("."+className).val();
							anganwadiVisitValue = anganwadiVisitSliderValue;
							
						}else if(sliderNameArr[i]=="OwnArea"){
							var ownAreaValueSliderValue = $("."+className).val();
							 ownAreaValue = ownAreaValueSliderValue;
							
						}else if(sliderNameArr[i]=="InchargeParliament"){
							var inchargeParliamentValueSliderValue = $("."+className).val();
							 inchargeParliamentValue = inchargeParliamentValueSliderValue;
						}
					 }
				 }	 
		 }
	      if(mainSlider != null && mainSlider=="main"){
			 var mainSlderCls = mainSliderName+''+designationStr+"cls";
			  if(desgnatnIdsLst != null && desgnatnIdsLst.length > 1){
				var secOrgIds = designationStr.split(",");
				 mainSlderCls = mainSliderName+''+secOrgIds[0]+secOrgIds[1]+"cls";
			  }
			   var mainSliderValue = $("."+mainSlderCls).val();
			   complainceValue = mainSliderValue;  
		  }
		  getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,ownDistValue,ownCnsttuncyValue,ichargeDistrictValue,incharegeConstituencyValue,govtWorkValue,stateTourCategoryValue,anganwadiVisitValue,ownAreaValue,inchargeParliamentValue,complainceValue,divId);
		  $(".toursSessionDropDownCls").hide();	
    });
	
	function getDesignationWiseAverageTourPerformanceDtls(desgnatnIdsLst,isFilterApply,filterType,ownDistValue,ownCnsttuncyValue,ichargeDistrictValue,incharegeConstituencyValue,govtWorkValue,stateTourCategoryValue,anganwadiVisitValue,ownAreaValue,inchargeParliamentValue,complainceValue,divId){
    	 if(isFilterApply=="No"){
		   $("#toursPerformanceDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 }else{
		  $("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 }
	 	if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#toursPerformanceDivId").html(' ');
			 return;
		} 
		
		var jsObj ={ 
					 activityMemberId :globalActivityMemberId ,
					 stateId : globalStateIdForTour,
					 fromDate : globalFormTourDate, 
					 toDate : glovalToTourDate,
					 userTypeId : globalUserTypeId,
					 designationIds : desgnatnIdsLst,
					 isFilterApply :isFilterApply,
					 filterType :filterType,
					 ownDistValue :ownDistValue,
					 ownCnsttuncyValue :ownCnsttuncyValue,
					 ichargeDistrictValue :ichargeDistrictValue,
					 incharegeConstituencyValue :incharegeConstituencyValue,
					 govtWorkValue :govtWorkValue,
					 stateTourCategoryValue:stateTourCategoryValue,
					 anganwadiVisitValue : anganwadiVisitValue,
					 ownAreaValue : ownAreaValue,
					 inchargeParliamentValue : inchargeParliamentValue,
					 complainceValue :complainceValue
				
				  }
		$.ajax({
			type : 'POST',
			url : 'getDesignationWiseAverageTourPerformanceDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			   if(isFilterApply != null && isFilterApply=="No"){
			    buildDesignationWiseAverageTourPerformanceDtls(result);		   
			   }else if(isFilterApply != null && isFilterApply=="Yes"){
				buildFilterWiseRslt(result,divId);   
			   }
		});
	}
	function buildDesignationWiseAverageTourPerformanceDtls(result){
		
		if(result != null && result.length > 0){
			var str1='';
		  for(var i in result){
			str1+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str1+='<div class="panel panel-default panelNew">';
					str1+='<div class="panel-heading">';
						str1+='<div class="row">';
							str1+='<div class="col-md-5 col-xs-12 col-sm-3">';
									str1+='<h4 class="panel-title"><span class="headingColor">'+result[i].name+'</span></h4>';
							str1+='</div>';
							str1+='<div class="col-md-7 col-xs-12 col-sm-9">';
								str1+='<ul class="list-inline pull-right activeUlCls">';
								 if(result[i].id==4){//organization SECRETARY and SECRETARY
									str1+='<li class="active tourFilterCls" attr_result_type="all" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id="4,5" style="margin-right: 20px;">All</li>';
									str1+='<li class="tourFilterCls" attr_result_type="complaince" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id="4,5" style="margin-right: 20px;">COMPLAINCE</li>';
									str1+='<li class="tourFilterCls" attr_result_type="nonComplaince" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id="4,5" style="margin-right: 20px;">NON-COMPLAINCE</li>';
									str1+='<li class="showHideFiltersToursSec text-capital" attr_id="'+result[i].id+'">SHOW/HIDE FILTERS</li>';
                         		 }else{
								  	str1+='<li class="active tourFilterCls" attr_result_type="all" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id='+result[i].id+' style="margin-right: 20px;">All</li>';
									str1+='<li class="tourFilterCls" attr_result_type="complaince" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id='+result[i].id+' style="margin-right: 20px;">COMPLAINCE</li>';
									str1+='<li class="tourFilterCls" attr_result_type="nonComplaince" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id='+result[i].id+' style="margin-right: 20px;">NON-COMPLAINCE</li>';
									str1+='<li class="showHideFiltersToursSec " attr_id="'+result[i].id+'">SHOW/HIDE FILTERS</li>';
                          	 }
                            str1+='</ul>';
							str1+='</div>';
						str1+='</div>';
						 str1+='<div class="toursSessionDropDownCls col-md-7 col-xs-12 col-sm-6" id="toursSessionDropDown'+result[i].id+'" style="right:13px;top:30px;display:none;z-index:999">';  
										str1+='<i class="glyphicon glyphicon-remove pull-right toursBlockCloseCls" attr_id="'+result[i].id+'" style="cursor:pointer;"></i>';
											str1+='<div role="tabpanel" class="tab-pane" id="">';
												//str1+='<h4 class="text-capital" style="color:#99A0A5;">Select Impact Scope</h4>';
												//str1+='<hr style ="margin-bottom:0px;" />';
												str1+='<div class="checkbox">';
												  str1+='<label>';
												
												   if(result[i].id==4){//organization SECRETARY and SECRETARY
													str1+='<input type="checkbox" value="" attr_designation_id="4,5"  class="tourFilteCheckBoxCls sliderCategoryTypeCls" attr_slider_type="tourCategory" checked style="margin-top: 3px;">';   
												   }else{
												     str1+='<input type="checkbox" value="" attr_designation_id='+result[i].id+'  class="tourFilteCheckBoxCls sliderCategoryTypeCls" attr_slider_type="tourCategory" checked style="margin-top: 3px;">';	   
												   }
													
													str1+='<h4 class="text-muted" style="font-size: 17px;">COMPLAINCE</h4><span class="errorCls" style="color:red"></span>';
												  str1+='</label>';
												str1+='</div>';
												//str1+='<hr style ="margin-bottom:0px;margin-top: 0px;" />';
												    var sliderName='';
													for(var k in result[i].subList3[0].subList3){
														var sliderNameStrWithoutSpace='';
														var strSliderName = result[i].subList3[0].subList3[k].name;
														sliderNameStrWithoutSpace = strSliderName.replace(/\s+/g, '');
														if(k == 0){
															   sliderName = sliderNameStrWithoutSpace;	 
														}else{
															   sliderName = sliderName+ "," + sliderNameStrWithoutSpace;	 
														}
														str1+='<div class="row" style="border-top:1px solid #d3d3d3;margin:0px;">';
															str1+='<div class="col-md-3 col-xs-12 col-sm-3 m_top10">';
																str1+='<p>'+result[i].subList3[0].subList3[k].name+'</p>';
															str1+='</div>';
															str1+='<div class="col-md-9 col-xs-12 col-sm-3 m_top10">';
																 if(result[i].id==4){//organization SECRETARY and SECRETARY
																	str1+='<input class="'+sliderNameStrWithoutSpace+'45'+'cls" id="ownDisConsSlider'+i+''+k+'"  type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1" data-slider-enabled="true" />';	 
																 }else{
																	str1+='<input class="'+sliderNameStrWithoutSpace+''+result[i].id+''+'cls" id="ownDisConsSlider'+i+''+k+'" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1" data-slider-enabled="true" />';	 
																 }
																
																	str1+='<span>0%</span><span class="col-xs-offset-8">100%</span>';
															str1+='</div>';
															
														str1+='</div>';
														
													}
												str1+='<div class="row" style="border-top:1px solid #d3d3d3;padding:10px;">';
												str1+='<p class="underlineLineCss" style="text-align: center;">(OR)</p>';
												str1+='<div class="col-md-3 col-xs-12 col-sm-3">';
													str1+='<div class="checkbox">';
														  str1+='<label>';
														    if(result[i].id==4){
															str1+='<input type="checkbox" attr_slider_type="main" attr_designation_id="4,5" class="tourFilteCheckBoxCls mainSliderCls" value="" style="margin-top: 3px;">';		
															}else{
															str1+='<input type="checkbox" attr_slider_type="main" attr_designation_id="'+result[i].id+'" class="tourFilteCheckBoxCls mainSliderCls" value="" style="margin-top: 3px;">';	
															}
															str1+='<h4 class="text-muted" style="font-size: 17px;">COMPLAINCE</h4>';
														  str1+='</label>';
														str1+='</div>';
												str1+='</div>';
												 var mainSliderNameWithoutSpace='';
												  var mainSliderName ='';
												  if(result[i].name != null && result[i].name.length > 0){
													  mainSliderName = result[i].name;
												  }
												   mainSliderNameWithoutSpace = mainSliderName.replace(/\s+/g, '');
												str1+='<div class="col-md-9 col-xs-12 col-sm-3 m_top10">';
												 if(result[i].id==4){//organization SECRETARY and SECRETARY
												  mainSliderNameWithoutSpace="orgSecAndSec";
												    str1+='<input id="mainSlider'+i+'" class="'+mainSliderNameWithoutSpace+'45'+'cls" data-slider-id="mainSlider'+i+'" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1" data-slider-enabled="true"/>';	 
												 }else{
												    str1+='<input id="mainSlider'+i+'" class="'+mainSliderNameWithoutSpace+''+result[i].id+''+'cls" data-slider-id="mainSlider'+i+'" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1" data-slider-enabled="true"/>';	 
												 }
												str1+='</div>';
												str1+='<span style="margin-left: 10px;">0%</span><span class="col-xs-offset-6">100%</span>';
												str1+='</div>';
													 if(result[i].id==4){//organization SECRETARY and SECRETARY
													  str1+='<button type="button" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id="4,5" attr_slider_name="'+sliderName+'" attr_main_slider_name='+mainSliderNameWithoutSpace+' class="btn btn-success tourSearchBtnCls btn-sm pull-right">Get Details</button>'; 	 
													 }else{
													  str1+='<button type="button" attr_div_id="toursPerformanceBlocks'+i+'" attr_designation_id='+result[i].id+' attr_slider_name="'+sliderName+'" attr_main_slider_name='+mainSliderNameWithoutSpace+' class="btn btn-success tourSearchBtnCls btn-sm pull-right">Get Details</button>'; 
													 }
											
										   str1+='</div>';
									   str1+='</div>';
					str1+='</div>';
					str1+='<div class="panel-body">';
						str1+='<div class="row">';
							str1+='<div class="col-md-12 col-xs-12 col-sm-12">';
								str1+='<div id="toursPerformanceBlocks'+i+'"></div>';
							str1+='</div>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>';
		  }
		  $("#toursPerformanceDivId").html(str1);
		}else{
		 $("#toursPerformanceDivId").html("NO DATA AVAILABLE.");	
		}
		
		if(result != null && result.length > 0){
			for(var i in result){
				var str='';
				var length = result
				if($(window).width() < 800)
				{  
					str+='<div class="table-responsive">'
				}
					str+='<table class="table table-bordered borderedWeight" style="font-size:12px;" id="dataTableApplyAveragePerf'+i+'">';
					str+='<thead class="bg_D8">';
						str+='<tr>';
			
								var maxLengthForSpan = 0;
								if(result[i].subList3 != null && result[i].subList3.length > 0){
									for(var j in result[i].subList3){
										if(result[i].subList3[j].subList3 != null && result[i].subList3[j].subList3.length > 0)
											maxLengthForSpan = result[i].subList3[j].subList3.length;
									}
								}
								str+='<th rowspan="2" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
								/* if(maxLengthForSpan >=3){
									str+='<th rowspan="'+(maxLengthForSpan)+'" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
								}else{
									str+='<th rowspan="'+(maxLengthForSpan+1)+'" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
								}
								 */
								str+='<th colspan="'+(maxLengthForSpan+1)+'" rowspan="1" class="text-capital text-center" style="vertical-align: middle;">COMPLAINCE RATIO</th>';
								
								for(var k in result[i].subList3[0].subList3){
									str+='<th colspan="2" rowspan="1" class="text-capital text-center" style="vertical-align: middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
								}
							
							str+='</tr>';
							str+='<tr>';
								
								str+='<th class="text-capital text-center" style="vertical-align: middle;">over all</th>';
										for(var k in result[i].subList3[0].subList3){
											str+='<th class="text-capital text-center" style="vertical-align: middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
											
										}
								for(var k in result[i].subList3[0].subList3){
								str+='<th class="text-capital text-center" style="vertical-align: middle;">Target</th>';
								str+='<th class="text-capital text-center" style="vertical-align: middle;">Toured</th>';
								}
								
							str+='</tr>';
								str+='</thead>';
								str+='<tbody>';
								//str+='<td></td>';
										for(var j in result[i].subList3){
											str+='<tr>';
												str+='<td style="cursor:pointer;color:rgb(51, 122, 183)" attr_type="direct" class="candiateCls text-capital" attr_candiate_id="'+result[i].subList3[j].id+'" attr_candiate_name="'+result[i].subList3[j].name+'" attr_designation_name="'+result[i].subList3[j].designation+'"><a>'+result[i].subList3[j].name+'</a></td>';
												str+='<td class="text-center">'+result[i].subList3[j].complaincePer+'%</td>';

												   for(var k in result[i].subList3[j].subList3){
													   
														str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complaincePer+'%</td>';
													}												
											for(var k in result[i].subList3[j].subList3){
												var monthList = result[i].subList3[j].subList3[k].monthList;
												str+='<td class="text-center">'+result[i].subList3[j].subList3[k].targetDays+'</td>';
												str+='<td class="text-center complainceDaysCls">'+result[i].subList3[j].subList3[k].complainceDays+'';
												
												str+='<div class="dropup">';
													str+='<span class="pull-right dropdown-toggle" style="font-weight: 600; cursor: pointer; font-size: 16px;margin-top: -21px;margin-right:-7px;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
														str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom verticalScrollBar" aria-labelledby="dropdownMenu2" style="padding:10px;">';
															str+='<table class="table table-bordered scrollApplyDiv">';
															str+='<thead>';
															str+='<th>Month</th>';
															str+='<th>Target</th>';
															str+='<th>Tour</th>';
															str+='<th>Complaince</th>';
															str+='</thead>';
															str+='<tbody>';
																	 if(monthList != null && monthList.length > 0){
																		 for(var l in monthList){
																			str+='<tr>';
																			if(monthList[l].name != null && monthList[l].name.length > 0){
																			str+='<td style="border-top:none !important">'+monthList[l].name+'</td>';	
																			}else{
																			str+='<td style="border-top:none !important"> - </td>';	
																			}
																			str+='<td class="text-center;">'+monthList[l].targetDays+'</td>';
																			if(monthList[l].complainceDays > 0){
																			str+='<td class="text-center;">'+monthList[l].complainceDays+'</td>';	
																			}else{
																			str+='<td> - </td>';	
																			}
																			if(monthList[l].complainceDays == 0){
																			str+='<td>-</td>';	
																			}else if(monthList[l].complainceDays>=monthList[l].targetDays){
																			str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#3DBC93;color:#fff;margin-left:7px;" class="glyphicon glyphicon-ok"></i></small>&nbsp&nbspYES<small></td>';		
																			}else if(monthList[l].complainceDays<monthList[l].targetDays){
																			 str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#E35B69;color:#fff;margin-left:7px;" class="glyphicon glyphicon-remove"></i></small>&nbsp&nbspNO<small></td>';			
																			}
																		   str+='</tr>'; 
																		 }
																	 }
															str+='</tbody>';
															str+='</table>';
														str+='</div>';
													str+='</div>';
													
												'</td>';
											}
											str+='</tr>';
										}
									
								str+='</tbody>';
								
					str+='</table>';
				if($(window).length < 800)
				{
					str+='</div>';
				}
				
				$("#toursPerformanceBlocks"+i).html(str);
					       var stateSliderval;
							for(var k in result[i].subList3[0].subList3){
									var slider = new Slider('#ownDisConsSlider'+i+''+k+'', {
								   formatter: function(value) {
								   stateSliderval=value;
								  // $("#stateSliderValue").text(value);
								 return 'Current value: ' + value;
							     }
							  });
							}
				
				//alert(stateSliderval);
				   var mainSliderVa1;
				    var slider = new Slider('#mainSlider'+i+'', {
				   formatter: function(value) {
					  // $("#constituencySliderValue").text(value);
					   mainSliderVa1=value;
					 return 'Current value: ' + value;
				   }
				});
					//alert(mainSliderVa1);
				$("#dataTableApplyAveragePerf"+i).dataTable({
					"aaSorting": [],
					"iDisplayLength" : 10	
				 });
				$('#dataTableApplyAveragePerf'+i).removeClass("dataTable");
				if($(window).length < 800){
					$(".scrollApplyDiv").each(function(){
					var scrollengthDiv = $(this).find("tr").length;
					if(scrollengthDiv >= 3){
						$(".verticalScrollBar").mCustomScrollbar({setHeight:'150px'})
						
					}else{
						$(".verticalScrollBar").css("height","auto");
					
					}
				}); 
				}else{
					$(".scrollApplyDiv").each(function(){
					var scrollengthDiv = $(this).find("tr").length;
					if(scrollengthDiv >= 5){
						$(".verticalScrollBar").mCustomScrollbar({setHeight:'250px'})
						
					}else{
						$(".verticalScrollBar").css("height","auto");
					
					}
				}); 
				}
				 
			}
		}
	}

	function  buildFilterWiseRslt(result,divId){
        $("#"+divId).html(' ');
			if(result != null && result.length > 0){
					for(var i in result){
						if(result[i].subList3 == null || result[i].subList3.length == 0){
						   $("#"+divId).html('NO DATA AVAILABLE');
                            return;			
						}
						var str='';
						var length = result
							if($(window).width() < 800)
							{
								str+='<div class="table-responsive">';
							}
							str+='<table style="font-size:12px;"  class="table table-bordered borderedWeight" id="dataTable'+divId+'">';
							str+='<thead class="bg_D8">';
								str+='<tr>';
					
										var maxLengthForSpan = 0;
										if(result[i].subList3 != null && result[i].subList3.length > 0){
											for(var j in result[i].subList3){
												if(result[i].subList3[j].subList3 != null && result[i].subList3[j].subList3.length > 0)
													maxLengthForSpan = result[i].subList3[j].subList3.length;
											}
										}
										str+='<th  class="text-capital text-center" style="vertical-align:middle;" rowspan="2" >Leaders Name</th>';
										/* if(maxLengthForSpan >=3){
											str+='<td rowspan="'+(maxLengthForSpan)+'" class="">Leaders Name</td>';
										}else{
											str+='<td rowspan="'+(maxLengthForSpan+1)+'" class="">Leaders Name</td>';
										} */
										
										str+='<th class="text-capital text-center" style="vertical-align:middle;" colspan="'+(maxLengthForSpan+1)+'" rowspan="1" class="">COMPLAINCE RATIO</th>';
										
										for(var k in result[i].subList3[0].subList3){
											str+='<th class="text-capital text-center" style="vertical-align:middle;" colspan="2" rowspan="1">'+result[i].subList3[0].subList3[k].name+'</th>';
										}
									
									str+='</tr>';
									str+='<tr>';
										
										str+='<th class="text-capital text-center" style="vertical-align:middle;" >over all</th>';
												for(var k in result[i].subList3[0].subList3){
													str+='<th class="text-capital text-center" style="vertical-align:middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
													
												}
										for(var k in result[i].subList3[0].subList3){
										str+='<th class="text-capital text-center" style="vertical-align:middle;">Target</th>';
										str+='<th class="text-capital text-center" style="vertical-align:middle;">Toured</th>';
										}
										
									str+='</tr>';
										str+='</thead>';
										str+='<tbody>';
										//str+='<td></td>';
												for(var j in result[i].subList3){
													str+='<tr>';
														str+='<td style="cursor:pointer;color:rgb(51, 122, 183)" attr_type="direct" class="candiateCls text-capital" attr_candiate_id="'+result[i].subList3[j].id+'" attr_candiate_name="'+result[i].subList3[j].name+'" attr_designation_name="'+result[i].subList3[j].designation+'"><a>'+result[i].subList3[j].name+'</a></td>';
														str+='<td class="text-center">'+result[i].subList3[j].complaincePer+'%</td>';

														   for(var k in result[i].subList3[j].subList3){
															   
																str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complaincePer+'%</td>';
															}												
													for(var k in result[i].subList3[j].subList3){
														var monthList = result[i].subList3[j].subList3[k].monthList;
														str+='<td class="text-center">'+result[i].subList3[j].subList3[k].targetDays+'</td>';
														str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complainceDays+'';
														
														
														str+='<div class="dropup">';
													    str+='<span class="pull-right dropdown-toggle" style="font-weight: 600; cursor: pointer; font-size: 16px;margin-top: -21px;margin-right:-7px;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
														str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom verticalScrollBar1" aria-labelledby="dropdownMenu2" style="padding:10px;">';
															str+='<table class="table table-bordered scrollApplyDiv1">';
															str+='<thead>';
															str+='<th>Month</th>';
															str+='<th>Target</th>';
															str+='<th>Tour</th>';
															str+='<th>Complaince</th>';
															str+='</thead>';
															str+='<tbody>';
																	 if(monthList != null && monthList.length > 0){
																		 for(var l in monthList){
																			str+='<tr>';
																			if(monthList[l].name != null && monthList[l].name.length > 0){
																			str+='<td style="border-top:none !important">'+monthList[l].name+'</td>';	
																			}else{
																			str+='<td style="border-top:none !important"> - </td>';	
																			}
																			str+='<td class="text-center;">'+monthList[l].targetDays+'</td>';
																			if(monthList[l].complainceDays > 0){
																			str+='<td class="text-center;">'+monthList[l].complainceDays+'</td>';	
																			}else{
																			str+='<td> - </td>';	
																			}
																			if(monthList[l].complainceDays == 0){
																			str+='<td>-</td>';	
																			}else if(monthList[l].complainceDays>=monthList[l].targetDays){
																			str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#3DBC93;color:#fff;margin-left:7px;" class="glyphicon glyphicon-ok"></i></small>&nbsp&nbspYES<small></td>';		
																			}else if(monthList[l].complainceDays<monthList[l].targetDays){
																			 str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#E35B69;color:#fff;margin-left:7px;" class="glyphicon glyphicon-remove"></i></small>&nbsp&nbspNO<small></td>';			
																			}
																		   str+='</tr>'; 
																		 }
																	 }
															str+='</tbody>';
															str+='</table>';
														str+='</div>';
													str+='</div>';
														
														
														str+='</td>';
														
														
													}
													str+='</tr>';
												}
											
										str+='</tbody>';
										
							str+='</table>';
						  if($(window).width() < 800)
							{
								str+='</div>';
							}
						$("#"+divId).html(str);
					}
				}else{
				$("#"+divId).html("NO DATA AVAILABLE.");	
				}	
			  $("#dataTable"+divId).dataTable({
				"aaSorting": [],
				"iDisplayLength" : 10	
			 });
			 $("#dataTable"+divId).removeClass("dataTable"); 
			 if($(window).length < 800){
				 $(".scrollApplyDiv1").each(function(){
					var scrollengthDiv = $(this).find("tr").length;
					if(scrollengthDiv >= 3){
						$(".verticalScrollBar1").mCustomScrollbar({setHeight:'150px'})
						
					}else{
						$(".verticalScrollBar1").css("height","auto");
					
					}
				}); 
			 }else{
				 $(".scrollApplyDiv1").each(function(){
					var scrollengthDiv = $(this).find("tr").length;
					if(scrollengthDiv >= 5){
						$(".verticalScrollBar1").mCustomScrollbar({setHeight:'250px'})
						
					}else{
						$(".verticalScrollBar1").css("height","auto");
					
					}
				}); 
			 }
			 
		} 
		
	function getIndividualPersonTourDetails(value)
	{          
	   //select Slider Year
		//var selectedDate = globalFormTourDate.split("/");
	    $("#dateRangeSliderYear").val(0);
		
		var temp = value.split("-");
		var candiateId = temp[0];
		var topFivecandidateName = temp[1];
		var topFivedesignationName = temp[2];
	     $("#subMitBtn").attr("attr_candidate_id",candiateId);
		 
		$("#tourIndividualPerformanceDivId").modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		
		 var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
		var fromDate = globalFormTourDate.split("/")
		var toDate = glovalToTourDate.split("/")
				
				$("#tourSlider").dateRangeSlider({
					    bounds: {min: new Date(fromDate[2], 0, 1), max: new Date(toDate[2], 11, 31)},
						//defaultValues: {min: new Date(2012, 1, 10), max: new Date(2012, 4, 22)},
						defaultValues: {min: new Date(fromDate[2], fromDate[1]-1,fromDate[0]), max: new Date(toDate[2],toDate[1]-1,toDate[0])},
						scales: [{
						  first: function(value){ return value; },
						  end: function(value) {return value; },
						  next: function(value){
							var next = new Date(value);
							return new Date(next.setMonth(value.getMonth() + 1));
						  },
						  label: function(value){
							return months[value.getMonth()];
						  },
						  format: function(tickContainer, tickStart, tickEnd){
							tickContainer.addClass("myCustomClass");
						  }
						}]
					
				});
				
		
	    //var selectedDate = $("#toursNewHeadingId").html();
		//&nbsp&nbsp<small style='color:green;'>"+selectedDate+"</small>&nbsp<small>DETAILS</small>
		$(".tourIndividualCls").attr("attr_type","direct");
		$("#nameOfMemberHeadingId").html('');
		$("#nameOfMemberHeadingId").html("<h4 class='modal-title text-capital'>"+topFivecandidateName+" - <small style='color:#4A5863'>"+topFivedesignationName+"</small></h4>");
		$("#tourIndividualDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#tourIndividualDetailsTableBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#monthWiseComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
					 candiateId : candiateId,
					 fromDate :globalFormTourDate ,
					 toDate : glovalToTourDate 
				  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourIndividualDetailsBlock").html('');
			$("#tourIndividualDetailsTableBlock").html('');
			$("#monthWiseComplainceDivId").html('');
			buildIndividualPersonTourDetails(result);
		});
	}
	   $(document).on("change","#tourDesignationSelectBoxId",function(){
		  var designationId = $(this).val(); 
		  var filterType = $(this).attr("attr_tour_filter_type");
		   var designationIds=[];
		   if(designationId == 0){//ALL 
			  $('#tourDesignationSelectBoxId option').each(function(){
					var dsgntnId = $(this).val();
					 if(dsgntnId == 4){
						designationIds.push(4); 
						designationIds.push(5); 
					 }else{
						if(dsgntnId != 0){
						  designationIds.push(dsgntnId);		 		
						} 
					 }
				}); 
			}else{
				if(designationId == 4){
					designationIds.push(4);			
					designationIds.push(5);			
				}else{
				designationIds.push(designationId);			
				}
			}
		  getTourLeaderDtlsBasedOnSelectionType(designationIds,filterType);
	   });
	  $(document).on("click",".tourOverViewCls",function(){
			
		var filterType = $(this).attr("attr_tour_filter_type");
		var designationIdsStr = $(this).attr("attr_dsgntn_ids");
		var designationName = $(this).attr("attr_designation_name");
		 $("#tourDetailsModalId").modal("show");
		 var designationIds=[];
	    if(designationName.trim()=="Overall"){
			$(".designationSelectBoxCls").show();
			$("#tourDesignationSelectBoxId").html(" ");
			
		        $("#tourDesignationSelectBoxId").append('<option value="0">All</option>');
				if(filterType=="Complaince"){
				$("#tourDesignationSelectBoxId").append(globalDesignationComplainceSelectBoxString);				
				}else if(filterType=="nonComplaince"){
				$("#tourDesignationSelectBoxId").append(globalDesignationNonCmplncSlctBxStrng);		
				}else if(filterType=="submitted"){
				$("#tourDesignationSelectBoxId").append(globalDesignationSubmttedSlctBxStrng);		
				}else if(filterType=="notSubmitteed"){
				$("#tourDesignationSelectBoxId").append(globalDesignationNotSubmttedSlctBxStrng);		
				}else{
				$("#tourDesignationSelectBoxId").append(globalDesignationSelectBoxString);		
				}
				
			var firstOption = $("#tourDesignationSelectBoxId option:first").val();
			$("#tourDesignationSelectBoxId").val(firstOption);
			
			if(firstOption == 0){
				 $('#tourDesignationSelectBoxId option').each(function(){
					var dsgntnId = $(this).val();
					 if(dsgntnId == 4){
						designationIds.push(4); 
						designationIds.push(5); 
					 }else{
						 if(dsgntnId != 0){
							 designationIds.push(dsgntnId);	 
						 }
					 }
				}); 
			}
			$("#tourDesignationSelectBoxId").attr("attr_tour_filter_type",filterType);
		}else{
		    $(".designationSelectBoxCls").hide();
			if(designationIdsStr != null && designationIdsStr != undefined){
			  designationIds=designationIdsStr.split(",");	
			}		   
		}
		  getTourLeaderDtlsBasedOnSelectionType(designationIds,filterType);
	});

	function getTourLeaderDtlsBasedOnSelectionType(designationIds,filterType)
	{    
		  var selectedDate = $("#toursNewHeadingId").html();
		  $("#tourLeadrDtlsHeadingId").html('Leaders Detailed Report&nbsp&nbsp<small  style="color:green;">'+selectedDate+'</small>');
	       $("#tourDetailsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 	var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate :globalFormTourDate ,
					 toDate : glovalToTourDate,
					 userTypeId:globalUserTypeId,
					 designationIds : designationIds,
					 filterType :filterType,
					 locationTypeId		:0,
		             locationValuesArr	:[],
					 type:"coreDashbaord"
				  }
		$.ajax({
			type : 'POST',
			url : 'getTourLeaderDtlsBasedOnSelectionTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildTourMemberDetails(result);
			}else{
			 $("#tourDetailsDivId").html("NO DATA AVAILABLE.");	
			}
		});
	}
	
function buildTourMemberDetails(result){
    
    if(result != null && result.length > 0){
      var str1='';
	      
      for(var i in result){
      str1+='<div class="col-md-12 col-xs-12 col-sm-12">';
        str1+='<div class="panel panel-default panelNew">';
          str1+='<div class="panel-heading">';
            str1+='<div class="row">';
              str1+='<div class="col-md-8 col-xs-12 col-sm-6">';
                str1+='<h4 class="panel-title"><span class="headingColor text-capital">'+result[i].name+'&nbsp&nbspTours Submitted Report</span></h4>';
              str1+='</div>';
			 
          str1+='</div>';
          str1+='<div class="panel-body">';
            str1+='<div class="row">';
			  str1+='<div class="col-md-12 col-xs-12 col-sm-12">';
              str1+='<div id="tourMemberDtls'+i+'"></div>';
			   str1+='</div>';
            str1+='</div>';
          str1+='</div>';
        str1+='</div>';
      str1+='</div>';
      str1+='</div>';
      }
      $("#tourDetailsDivId").html(str1);
    }
    if(result != null && result.length > 0){
      for(var i in result){
	  if(result[i].subList3 == null || result[i].subList3.length == 0){
	    $("#tourDetailsDivId").html('NO DATA AVAILABLE');
		 return;			
	  }
        var str='';
        var length = result
	    if($(window).width() < 800)
		{
			str+='<div class="table-responsive">';
		}
		
          str+='<table class="table table-bordered borderedWeight" id="tourDetailsDataTabelId'+i+'">';
          str+='<thead class="bg_D8">';
            str+='<tr>';
                var maxLengthForSpan = 0;
                if(result[i].subList3 != null && result[i].subList3.length > 0){
                  for(var j in result[i].subList3){
                    if(result[i].subList3[j].subList3 != null && result[i].subList3[j].subList3.length > 0)
                      maxLengthForSpan = result[i].subList3[j].subList3.length;
                  }
                }
				str+='<th rowspan="2" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
                /* if(maxLengthForSpan >=3){
                  str+='<th rowspan="'+(maxLengthForSpan)+'" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
                }else{
                  str+='<th rowspan="'+(maxLengthForSpan+1)+'" class="text-capital text-center" style="vertical-align: middle;">Leaders Name</th>';
                } */
                
                str+='<th colspan="'+(maxLengthForSpan+1)+'" rowspan="1" class="text-capital text-center" style="vertical-align: middle;">Complaince RATIO</th>';
                
                for(var k in result[i].subList3[0].subList3){
                  str+='<th colspan="2" rowspan="1" class="text-capital text-center" style="vertical-align: middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
                }
              
              str+='</tr>';
              str+='<tr>';
                
                str+='<th class="text-capital text-center" style="vertical-align: middle;">over all</th>';
                    for(var k in result[i].subList3[0].subList3){
                      str+='<th class="text-capital text-center" style="vertical-align: middle;">'+result[i].subList3[0].subList3[k].name+'</th>';
                      
                    }
                for(var k in result[i].subList3[0].subList3){
                str+='<th class="text-capital text-center" style="vertical-align: middle;">Target</th>';
                str+='<th class="text-capital text-center" style="vertical-align: middle;">Toured</th>';
                }
              str+='</tr>';
                str+='</thead>';
                str+='<tbody>';
                //str+='<td></td>';
                    for(var j in result[i].subList3){
                      str+='<tr>';
                        str+='<td style="cursor:pointer;color:rgb(51, 122, 183)" attr_type="subLevel" class="candiateCls text-capital" attr_candiate_id="'+result[i].subList3[j].id+'" attr_candiate_name="'+result[i].subList3[j].name+'" attr_designation_name="'+result[i].subList3[j].designation+'"><a>'+result[i].subList3[j].name+'</a></td>';//santosh
                        str+='<td class="text-center">'+result[i].subList3[j].complaincePer+'%</td>';

							for(var k in result[i].subList3[j].subList3){
                             
                            str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complaincePer+'%</td>';
                          }                        
                      for(var k in result[i].subList3[j].subList3){
                        var monthList = result[i].subList3[j].subList3[k].monthList;
                        str+='<td class="text-center">'+result[i].subList3[j].subList3[k].targetDays+'</td>';
                        str+='<td class="text-center">'+result[i].subList3[j].subList3[k].complainceDays+'';
								str+='<div class="dropup">';
								str+='<span class="pull-right dropdown-toggle" style="font-weight: 600; cursor: pointer;font-size: 16px; margin-top: -21px;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
								str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom verticalScrollBar2" aria-labelledby="dropdownMenu2" style="padding:10px;">';
									str+='<table class="table table-bordered scrollApplyDiv2">';
									str+='<thead>';
									str+='<th>Month</th>';
									str+='<th>Target</th>';
									str+='<th>Tour</th>';
									str+='<th>Complaince</th>';
									str+='</thead>';
									str+='<tbody>';
											 if(monthList != null && monthList.length > 0){
												 for(var l in monthList){
													str+='<tr>';
													if(monthList[l].name != null && monthList[l].name.length > 0){
													str+='<td style="border-top:none !important">'+monthList[l].name+'</td>';	
													}else{
													str+='<td style="border-top:none !important"> - </td>';	
													}
													str+='<td class="text-center;">'+monthList[l].targetDays+'</td>';
													if(monthList[l].complainceDays > 0){
													str+='<td class="text-center;">'+monthList[l].complainceDays+'</td>';	
													}else{
													str+='<td> - </td>';	
													}
													if(monthList[l].complainceDays == 0){
													str+='<td>-</td>';	
													}else if(monthList[l].complainceDays>=monthList[l].targetDays){
													str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#3DBC93;color:#fff;margin-left:7px;" class="glyphicon glyphicon-ok"></i></small>&nbsp&nbspYES<small></td>';		
													}else if(monthList[l].complainceDays<monthList[l].targetDays){
													 str+='<td><i style="padding:2px 3px;border-radius:50%;background-color:#E35B69;color:#fff;margin-left:7px;" class="glyphicon glyphicon-remove"></i></small>&nbsp&nbspNO<small></td>';			
													}
												   str+='</tr>'; 
												 }
											 }
									str+='</tbody>';
									str+='</table>';
								str+='</div>';
							str+='</div>';
						
						str+='</td>';
                      }
                      str+='</tr>';
                    }
                  
                str+='</tbody>';
                
          str+='</table>';
              if($(window).width() < 800)
			{
				str+='</div>';
			 }
        $("#tourMemberDtls"+i).html(str);
	 	$("#tourDetailsDataTabelId"+i+"").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 10	
		 });
		$("#tourDetailsDataTabelId"+i+"").removeClass("dataTable");
		
		
		if($(window).width() < 800)
			{
				$(".scrollApplyDiv2").each(function(){
					var scrollengthDiv = $(this).find("tr").length;
					if(scrollengthDiv >= 3){
						$(".verticalScrollBar2").mCustomScrollbar({setHeight:'150px'})
						
					}else{
						$(".verticalScrollBar2").css("height","auto");
					
					}
				}); 
			}else{
				$(".scrollApplyDiv2").each(function(){
					var scrollengthDiv = $(this).find("tr").length;
					if(scrollengthDiv >= 5){
						$(".verticalScrollBar2").mCustomScrollbar({setHeight:'240px'})
						
					}else{
						$(".verticalScrollBar2").css("height","auto");
					
					}
				}); 
			}
		  
		}
    }
  }
	
 $(document).on("click",".tourIndividualCls",function(){
	$("#tourSlider").dateRangeSlider("destroy");
	 var selectLevel = $(this).attr("attr_type");
	 if(selectLevel == "subLevel"){
		setTimeout(function(){
		$('body').addClass("modal-open");
		}, 500);                     
	 }
	}); 

$(document).on("click",".candiateCls",function(){
	var candiateId = $(this).attr("attr_candiate_id");
	 $("#subMitBtn").attr("attr_candidate_id",candiateId);
	var designationName = $(this).attr("attr_designation_name");
	var candiateName = $(this).attr("attr_candiate_name");
	var selectedLevel = $(this).attr("attr_type");
	$(".tourIndividualCls").attr("attr_type",selectedLevel);
	//var selectedDate = globalFormTourDate.split("/");
	$("#dateRangeSliderYear").val(0);
   getCandiateWiseTourDetails(candiateId,designationName,candiateName);
});

function getCandiateWiseTourDetails(candiateId,designationName,candiateName)
	{ 
		$("#tourIndividualPerformanceDivId").modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		
		 var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
		var fromDate = globalFormTourDate.split("/")
		var toDate = glovalToTourDate.split("/")
				
		$("#tourSlider").dateRangeSlider({
				bounds: {min: new Date(fromDate[2], 0, 1), max: new Date(toDate[2], 11, 31)},
				//defaultValues: {min: new Date(2012, 1, 10), max: new Date(2012, 4, 22)},
				defaultValues: {min: new Date(fromDate[2], fromDate[1]-1,fromDate[0]), max: new Date(toDate[2],toDate[1]-1,toDate[0])},
				scales: [{
				  first: function(value){ return value; },
				  end: function(value) {return value; },
				  next: function(value){
					var next = new Date(value);
					return new Date(next.setMonth(value.getMonth() + 1));
				  },
				  label: function(value){
					return months[value.getMonth()];
				  },
				  format: function(tickContainer, tickStart, tickEnd){
					tickContainer.addClass("myCustomClass");
				  }
				}]
			
		});
		//var selectedDate = $("#toursNewHeadingId").html();
		//&nbsp&nbsp<small style='color:green;'>"+selectedDate+"</small>&nbsp<small>DETAILS</small>
		$("#nameOfMemberHeadingId").html('');
		$("#nameOfMemberHeadingId").html("<h4 class='modal-title text-capital'>"+candiateName+" - <small style='color:#4A5863'>"+designationName+"</small></h4>");
		$("#tourIndividualDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#tourIndividualDetailsTableBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#monthWiseComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
					 candiateId : candiateId,
					 fromDate :globalFormTourDate ,
					 toDate : glovalToTourDate
				  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourIndividualDetailsBlock").html('');
			$("#tourIndividualDetailsTableBlock").html('');
			$("#monthWiseComplainceDivId").html(' ');
			buildIndividualPersonTourDetails(result);
		});
	}
	
   function buildIndividualPersonTourDetails(result){
	  
	   $("#tourIndividualDetailsBlock").html('');
	 	if(result !=null && result.subList != null && result.subList.length > 0){
			 var str='';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<div class="row">';
					
						str+='<div class="col-md-4 col-xs-12 col-md-12">';
							str+='<h4 class="text-capital">TOTAL COMPLAINCE OVERVIEW</h4>';
							str+='<div id="overAllComplainsGraph" class="" style="height:150px" ></div>';
						str+='</div>';
						str+='<div class="col-md-8 col-xs-12 col-md-12">';
							str+='<div class="row">';
						for(var i in result.subList){
							str+='<div class="col-md-4 col-xs-12 col-md-12">';
							str+='<h4 class="text-capital">'+result.subList[i].name+'</h4>';
							str+='<div id="individualComplainsGraph'+i+'" class="" style="height:150px" ></div>';
							str+='</div>';
						
						}
					str+='</div>';
					str+='</div>';
					str+='</div>';
					
			str+='</div>';
			$("#tourIndividualDetailsBlock").html(str);
		}else{
			$("#tourIndividualDetailsBlock").html("NO DATA AVAILABLE.");
		}
		
		if(result !=null && result.subList != null && result.subList.length > 0){
			
			var mainArrNma=[];
			var individualPerfArr=[];
			var nameArr;
			var jsonObj=[];	
			mainArrNma.push("All")
			jsonObj.push(result.complaincePer);
			for(var i in result.subList){
				jsonObj.push(result.subList[i].complaincePer);
				mainArrNma.push(result.subList[i].name);
				nameArr = result.subList[i].name;
			}
		
		 $(function () {
			  $('#overAllComplainsGraph').highcharts({
				colors: ['#80F6F8'],
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
					categories: mainArrNma,
					title: {
						text: null
					},
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
						enabled: false,
					}
				},
				 tooltip: {formatter: function(){
					return '<b>'+this.x+' : '+ Highcharts.numberFormat(this.y, 2) +'%</b>';
				}      
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
									return Highcharts.numberFormat(this.y,2) +"%"; 
								}
							}
						  
						},
					},
					
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
				
				series:  [{
					name: nameArr,
					data: jsonObj
				}]
			});
		});
	}
	
	if(result !=null && result.subList != null && result.subList.length > 0){
			for(var i in result.subList){
				var performanceArr =[];
				performanceArr.push(result.subList[i].targetDays);
				performanceArr.push(result.subList[i].complainceDays);
				//performanceArr.push(result.subList[i].yetToTourCnt);
		if(performanceArr != 0 && performanceArr.length > 0){		
		 $(function () {
			  $('#individualComplainsGraph'+i+'').highcharts({
				colors: ['#7F7037','#80F6F8'],
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
					categories: ["Target","Toured"],
					title: {
						text: null
					},
					labels: {
							formatter: function() {
								return this.value.toString().substring(0, 5)+'...';
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
						enabled: false,
					}
				},
				 tooltip: {formatter: function(){
					return '<b>'+this.x+' : '+ (this.y) +'</b>';
					
				}      
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
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							}
						  
						},
					},
					
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
				
				series:  [{
					name: '',
					data: performanceArr,
					colorByPoint:true
				}]
			});
		});
		}else{
			 $('#individualComplainsGraph'+i+'').html("No Data Availble");
		}
	}	
	}
	
	if(result !=null && result.monthList != null && result.monthList.length > 0){
		  var str2='';
		  
		  	 str2+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
				str2+='<h4 class="text-capital">MONTH WISE COMPLIANCE OVERVIEW</h4>';
					 str2+='<div class="slickApplyTourCls" >';	
				for(var i in result.monthList){
					
						//str2+='<li>';
						if(i == 0 || i%3 == 0){
							str2+='<div class="row">';
						}
					   if($(window).width() < 500)
						{
							str2+='<div class="table-responsive">';
						}
						str2+='<div class="col-md-4 col-xs-12 col-sm-12 m_top10">';
							str2+='<table class="table table-bordered">';
							 var categoryList = result.monthList[i].subList;
							 
							 if(categoryList != null && categoryList.length > 0){
									var moxCategoryLength = categoryList.length;
									var categoryVO = result.monthList[i].subList[0];
								str2+='<tr>';
									str2+='<td rowspan='+(moxCategoryLength+1)+' style="font-size:22px;background-color:#EDECE7">'+result.monthList[i].name+'<br>'+result.monthList[i].year+'</td>';
									str2+='<td style="background-color:#EDECE7"><p>'+categoryVO.name+'('+categoryVO.complainceDays+')';
									   if(categoryVO.complainceDays >= categoryVO.targetDays){
											str2+='<small style="text-align: center;"><i  style="color:#3DBC93;margin-left:7px;" class="glyphicon glyphicon-ok text-success "></i></small>';
										   }else{
											str2+='<small style="text-align: center;"><i  style="color:#E35B69;margin-left:7px;" class="glyphicon glyphicon-remove text-danger"></i></small>';
										   } 
									  str2+='<div class="dropup">';
									str2+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -23px;margin-right:-6px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
										str2+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;z-index:999;width:220px">';
											str2+='<p><span style="font-size: 14px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 13px;">Tours Days Target Per Month</i></p>';
											str2+='<table class="table">';
												str2+='<tr><td style="background-color:#EDECE7">'+categoryVO.name+' - '+categoryVO.targetDays+'</td></tr>';
											str2+='</table>';
										str2+='</div>';
									str2+='</div></p>';  
										  str2+='</td>';  
								        if(result.monthList[i].isComplaince != null && result.monthList[i].isComplaince.trim()=="True"){
										   str2+='<td style="background-color:#3DBC93;text-align:center;" rowspan="'+(moxCategoryLength+1)+'"><i class="glyphicon glyphicon-ok" style="font-size:55px;display:block;color:#068057;"></i><small style="color:#fff;">Compliance</small></td>';
										}else{
										   str2+='<td style="background-color:#E35B69;text-align:center;width:50px;" rowspan="'+(moxCategoryLength+1)+'"><i class="glyphicon glyphicon-remove" style="font-size:55px;display:block;color:#bf3646;"></i><small style="color:#fff;">Non Compliance</small></td>';
										} 	 
								  str2+='</tr>';
								
									for(var k in categoryList){
										if(k==0)
										continue;
										 str2+='<tr>';
										if(categoryList[k].name == null || categoryList[k].name == ""){
											str2+='<td style="background-color:#EDECE7;"> - </td>';
										}else{
										   str2+='<td style="background-color:#EDECE7"><p>'+categoryList[k].name+'('+categoryList[k].complainceDays+')';
											   if(categoryList[k].complainceDays >= categoryList[k].targetDays){
												str2+='<small style="text-align: center;"><i style="color:#3DBC93;margin-left:7px;" class="glyphicon glyphicon-ok text-danger"></i></small>';
											   }else{
													str2+='<small style="text-align: center;"><i style="color:#E35B69;margin-left:7px;" class="glyphicon glyphicon-remove text-danger"></i></small>';
											   } 
											 str2+='<div class="dropup">';
											str2+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -23px;margin-right:-6px;cursor:pointer;" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
												str2+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu3" style="padding:10px;z-index:999;width:220px !important;">';
													str2+='<p><span style="font-size: 14px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 13px;">Tours Days Target Per Month</i></p>';
													str2+='<table class="table">';
														str2+='<tr style="background-color:#EDECE7"><td>'+categoryList[k].name+' - '+categoryList[k].targetDays+'</td></tr>';
													str2+='</table>';
												str2+='</div>';
											str2+='</div></p>';  
										   str2+='</td>';
										}
									str2+='</tr>';
								  }
							 }
							str2+='</table>';
							str2+='</div>';
							//str2+='</li>';
						   if(i == 2 || i%3 == 2){
								str2+='</div>';
							}
						 if($(window).width() < 500)
						{
							str2+='</div>';
						}
						
				}
				str2+='</div>';
				str2+='</div>';
			
			
		$("#monthWiseComplainceDivId").html(str2);
		/* $('.slickApplyTourCls').slick({
			//slide: 'li',
		 	 slidesToShow: 3,
			 slidesToScroll: 3,
			  infinite: false,
			 variableWidth: false
			 
		}); */
	}
	 if(result !=null && result.subList3 != null && result.subList3.length > 0){
		var str1='';
		str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
		str1='<h4 class="text-capital">MONTH WISE COMPLIANCE COMMENTS & ATTACHMENTS</h4>';
				str1+='<div class="m_top20">';
					 if($(window).width() < 500)
					{
						str1+='<div class="table-responsive">';
					}
					str1+='<table class="table table-bordered borderedWeight">';
						str1+='<thead class="bg_D8">';
							str1+='<tr>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Month & Date</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Category</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Type</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">NO OF Days</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">ATTACHMENT</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Comment</th>';
							str1+='</tr>';
							str1+='<tbody>';
							  
								for(var i in result.subList3){
									str1+='<tr>';
									var moxCategoryLength = 0;
									if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
									   moxCategoryLength = result.subList3[i].subList.length;
									}
										str1+='<td rowspan='+(moxCategoryLength)+'>'+result.subList3[i].tourDate+'</td>';
										if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
											 var monthVO = result.subList3[i].subList[0];
											    if(monthVO != null){
													 if(monthVO.tourCategory == null || monthVO.tourCategory == ""){
															str1+='<td> - </td>';
														}else{
															str1+='<td>'+monthVO.tourCategory+'</td>';
														}
														if(monthVO.tourType == null || monthVO.tourType == ""){
															str1+='<td> - </td>';
														}else{
															str1+='<td>'+monthVO.tourType+'</td>';
														}
														if(monthVO.count == null || monthVO.count==0){
															str1+='<td> - </td>';
														}else{
															str1+='<td class="text-center">'+monthVO.count+'</td>';
														}	
												}
										} 
											if(result.subList3[i].filePath != null && result.subList3[i].filePath.length > 0){
													var filePathArr = result.subList3[i].filePath.split(",");
													if(filePathArr != null && filePathArr.length > 0){
														str1+='<td rowspan='+(moxCategoryLength)+'>';
													for (var m = 0; m < filePathArr.length; m++) { 
															var fullName = filePathArr[m];
															var nameArr = fullName.split(".");
															var type = nameArr[1];
															if(type=="pdf" || type=="PDF"){
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/pdf.jpg" class="media-object" alt="" style="width:30px;"/></span>';
															}else if(type=="xls" ||type=="xlsx"){  
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/excel.jpg" class="media-object" alt="" style="width:30px;"/></span>';
															}else if(type=="doc" || type=="docx"){
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/word.jpg" class="media-object" alt="" style="width:30px;"/></span>';
															}else if(type != null){  
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/fileImage.png" class="media-object" alt="" style="width:30px;"/></span>';
															}           
														}
														str1+='</td>';	
													}
												}else{    
													str1+='<td rowspan='+(moxCategoryLength)+'> - </td>';  
												} 	
												
												
												if(result.subList3[i].comment != null && result.subList3[i].comment.length > 0){
														if(result.subList3[i].comment.length > 15){
														 str1+='<td rowspan='+(moxCategoryLength)+' style="cursor:pointer;" title="'+result.subList3[i].comment+'">'+result.subList3[i].comment.substring(0,30)+'...</td>';	
														}else{
														 str1+='<td rowspan='+(moxCategoryLength)+'>'+result.subList3[i].comment+'</td>';	
														}
													}else{
													  str1+='<td rowspan='+(moxCategoryLength)+'> - </td>';	
													}
									str1+='</tr>';
										if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
											var categoryList = result.subList3[i].subList;
												for(var k in categoryList){
													if(k == 0)
													continue;
													str1+='<tr>';
													if(categoryList[k].tourCategory == null || categoryList[k].tourCategory == ""){
														str1+='<td> - </td>';
													}else{
														str1+='<td>'+categoryList[k].tourCategory+'</td>';
													}
													if(categoryList[k].tourType == null || categoryList[k].tourType == ""){
														str1+='<td> - </td>';
													}else{
														str1+='<td>'+categoryList[k].tourType+'</td>';
													}
													if(categoryList[k].count == null || categoryList[k].count==0){
														str1+='<td> - </td>';
													}else{
														str1+='<td class="text-center">'+categoryList[k].count+'</td>';
													}
											 str1+='</tr>';
											}
										}
							
							  }
			
							str1+='</tbody>';
						str1+='</thead>';
					str1+='</table>';	
					if($(window).width() < 500)
					{
						str1+='</div>';
					}
				str1+='</div>';
			str1+='</div>';
		$("#tourIndividualDetailsTableBlock").html(str1); 
	}
}
$(document).on('click','#showTourPdfId',function(){
		//$("#cdrModelId").modal("show");
		var dbFilePath = $(this).attr("attr_filePath");         
		var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		var extName = fileNameArr[1];
		if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
			$("#tourNewDocumentId").modal("hide");
			window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open('http://ieee802.org/secmail/docIZSEwEqHFr.doc','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}else{
			
			if(extName.trim()=="pdf" || extName.trim()=="PDF"){
				$("#tourNewDocumentId").modal("show");
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}
			if(extName.trim()=="jpg"){  
				$("#tourNewDocumentId").modal("show");
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}              
			if(extName.trim()=="doc" || extName.trim()=="docx"){
				$("#tourNewDocumentId").modal("show");
				str += '<iframe src="https://docs.google.com/gview?url=http://mytdp.com/Reports/tour_documents/'+dbFilePath+'&embedded=true" frameborder="0" style="width: 100%; height: 500px;">';
				str += '</iframe>';
			}
			if(extName.trim()=="xls" || extName.trim()=="xlsx"){      
				window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			}            
			$("#tourNewDocumentBodyId").html(str);
			$("#tourNewDocumentId").attr("isModalOpened","true");
			//window.open(wurl+'/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			// window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}      
	});
$(document).on("change","#dateRangeSliderYear",function(){
	var getYear = $(this).val();
	$("#tourSlider").dateRangeSlider("destroy");
	var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
	$("#tourSlider").dateRangeSlider({
		  bounds: {min: new Date(getYear, 0, 1), max: new Date(getYear, 11, 31)},
		//defaultValues: {min: new Date(2012, 1, 10), max: new Date(2012, 4, 22)},
		defaultValues: {min: new Date(getYear, 0,1), max: new Date(getYear,2,30)},
		scales: [{
		  first: function(value){ return value; },
		  end: function(value) {return value; },
		  next: function(value){
			var next = new Date(value);
			return new Date(next.setMonth(value.getMonth() + 1));
		  },
		  label: function(value){
			return months[value.getMonth()];
		  },
		  format: function(tickContainer, tickStart, tickEnd){
			tickContainer.addClass("myCustomClass");
		  }
		 }] 
	});
});

$(document).on("click","#subMitBtn",function(){
	var candiateId = $(this).attr("attr_candidate_id");
	var fromDateDate = $(".ui-rangeSlider-leftLabel").find(".ui-rangeSlider-label-value").html(); 
	var toDateDate = $(".ui-rangeSlider-rightLabel").find(".ui-rangeSlider-label-value").html(); 
	var frmDateInRequiredFormat;
	var toDateInRequiredFormat;
	if(fromDateDate != null && fromDateDate.length > 0){
		var fromDateArr = fromDateDate.split("-");
		frmDateInRequiredFormat =fromDateArr[2].trim()+"/"+fromDateArr[1].trim()+"/"+fromDateArr[0].trim();
	}
	if(toDateDate != null && toDateDate.length > 0){
		var toDateArr = toDateDate.split("-");
		toDateInRequiredFormat =toDateArr[2].trim()+"/"+toDateArr[1].trim()+"/"+toDateArr[0].trim();
	}
	getIndividualRslBasedOnDateSelection(candiateId,frmDateInRequiredFormat,toDateInRequiredFormat);
}); 

function getIndividualRslBasedOnDateSelection(candiateId,frmDateInRequiredFormat,toDateInRequiredFormat){
	
	$("#tourIndividualDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#tourIndividualDetailsTableBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#monthWiseComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj ={ 
					 candiateId : candiateId,
					 fromDate :frmDateInRequiredFormat ,
					 toDate : toDateInRequiredFormat
				  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#tourIndividualDetailsBlock").html('');
			$("#tourIndividualDetailsTableBlock").html('');
			$("#monthWiseComplainceDivId").html(' ');
			buildIndividualPersonTourDetails(result);
		});
}

/* Tdp Cadre wise unique member detail */

function getCandiateWiseTourSubmittedDetails()
	{    
		$("#tourUniquememberSubmittedDltsDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#tourUniquememberSubmittedDltsDivId").html('');
			 return;
		}
		var jsObj = { 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate : globalFormTourDate,
					 toDate : glovalToTourDate,
					 userTypeId : globalUserTypeId
				  }
		$.ajax({
			type : 'POST',
			url : 'getCandiateWiseTourSubmittedDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		  buildPeopleWiseToursOverviewDtl(result);
		});
	}
	function buildPeopleWiseToursOverviewDtl(overViewRslt) {
		var str='';
		 if(overViewRslt != null){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h4><span class="headingColor text-capital">Unique Candidate Wise Details</span></h4>';
					str+='<div id="" class="m_top10 ">';
						str+='<div class="row">';
							str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top5">';
								str+='<div class="pad_5  bg_ED">';
									if(overViewRslt.totalCandiateCount > 0){
									  str+='<h3 class="tourMemberOverViewCls"   style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="all">'+overViewRslt.totalCandiateCount+'</h3>';	
									}else{
									  str+='<h3>0</h3>';	
									}
									str+='<p class="text-muted text-capital">total Candidate</p>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top5">';
								str+='<div class="pad_5  bg_ED">';
								   if(overViewRslt.submittedCandiateCount > 0){
									  str+='<h3 class="tourMemberOverViewCls"   style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="Submitted" >'+overViewRslt.submittedCandiateCount+'&nbsp<small class="text-success" style="font-size:13px">'+overViewRslt.submittedPer+'%</small></h3>';	
									}else{
									  str+='<h3>0</h3>';	
									}
									str+='<p class="text-muted text-capital">Submitted candidate </p>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top5">';
								str+='<div class="pad_5  bg_ED">';
									if(overViewRslt.notSubmittedCandidateCount > 0){
									  str+='<h3 class="tourMemberOverViewCls"   style="cursor:pointer;color:rgb(51, 122, 183)" attr_tour_filter_type="notSubmitted">'+overViewRslt.notSubmittedCandidateCount+'&nbsp<small class="text-success" style="font-size:13px">'+overViewRslt.notSubmittedPer+'%</small></h3>';	
									}else{
									  str+='<h3>0</h3>';	
									}
									str+='<p class="text-muted text-capital">Not Submitted candidate </p>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';	
		    } else {
				str += "NO DATA AVAILABLE.";
			}
		$("#tourUniquememberSubmittedDltsDivId").html(str);
	}
	
	$(document).on("click",".tourMemberOverViewCls",function(){
		var filterType = $(this).attr("attr_tour_filter_type");
		$("#tourUniqueMembersReportHeadingDtlsId").html(filterType.toUpperCase()+"&nbspCandidate Detailed Report");
		$("#tourUniqueMemberDetailsModalId").modal("show");
		getCandaiteDetailsByType(filterType);
	});
	function getCandaiteDetailsByType(filterType)
	{    
		$("#tourMemberDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		if(globalUserTypeId == 7 || globalUserTypeId==8 || globalUserTypeId==9)
		{ 
			$("#tourMemberDetails").html(' ');
			 return;
		}
		var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate : globalFormTourDate,
					 toDate : glovalToTourDate,
					 userTypeId : globalUserTypeId,
					 type:filterType
				  }
		$.ajax({
			type : 'POST',
			url : 'getCandaiteDetailsByTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 if (result != null && result.length > 0) {
				 buildUniqueTourMemberDtls(result);
			 } else {
				 $("#tourMemberDetails").html("NO DATA AVAILABLE.");
			 }
		});
	}
	function buildUniqueTourMemberDtls(result){
	  var str='';
		str+='<div class="table-responsive">';	
			str+='<table  class="table table-bordered" id="tourMemberDtlsDataTableId">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Candiate Name</th>';
						str+='<th>Mobile Number</th>';
						str+='<th>MemberShip No</th>';
						str+='<th>Designation</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
				str+='<tr>';
							if (result[i].name != null && result[i].name.length > 0) {
									str+='<td>'+result[i].name.toUpperCase()+'</td>';
							} else {
								str+='<td>-</td>';
							}
							if (result[i].moblieNo != null ) {
							str+='<td>'+result[i].moblieNo+'</td>';	
							} else {
								str+='<td>-</td>';
							}
							if (result[i].memberShipNo != null ) {
							str+='<td>'+result[i].memberShipNo+'</td>';	
							} else {
								str+='<td>-</td>';
							}
							if (result[i].designation != null ) {
								if (result[i].designation.length > 15) {
									str+='<td class="tourDesigtooltipCls" data-container="body" title="'+result[i].designation.substring(1,result[i].designation.length)+'" style="cursor:pointer;">'+result[i].designation.substring(1,15)+"..."+'</td>';
								} else {
									str+='<td>'+result[i].designation.substring(1,result[i].designation.length)+'</td>';	
								}
							} else {
								str+='<td>-</td>';
							}
							
				str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	   $("#tourMemberDetails").html(str);
	   $("#tourMemberDtlsDataTableId").dataTable({
		   "aLengthMenu": [[10,15,20,30,50, 100, -1], [10,15,20,30,50, 100, "All"]]
	   });
	   $(".tourDesigtooltipCls").tooltip();
	}
	