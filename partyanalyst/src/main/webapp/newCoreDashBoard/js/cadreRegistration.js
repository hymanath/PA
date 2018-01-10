$(document).on("click",".compareBlockSwitchCls",function(){
	$(".detailsCls").hide();           
	$(".compareCls").show();  
	$(".usrWseCnsttuncyUlCls li").removeClass("active");
	$(".usrWseCnsttuncyUlCls li:first-child").addClass("active");
	getAllItsSubUserTypeIdsByParentUserTypeIdForCadreRegistration(globalUserTypeId);    
});
$(document).on("click",".cadreDetailsCls",function(){
	$(".detailsCls").show();     
	$(".compareCls").hide(); 
    var filterApplyType="No";
	var is2014Active="No";
	var accessLevelId=0;
	var accessLevelValues=[];
    var renewal2016CheckboxIsChecked="Y";
	var new2016CheckboxIsChecked="Y";
	var cadre2014CheckboxIsChecked="Y";  
	var isKuppamExcluded="True";
	getCadreDetailsBasedOnUserType(filterApplyType);  
	getSourceOfRegistrationDtls(globalActivityMemberId);       
	getSourceOfRegistrationTSDtls(globalActivityMemberId);       
	getTsDistrictWiseTsDetails(accessLevelId,accessLevelValues,filterApplyType);
	getApConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active,isKuppamExcluded);
	getTsConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active);	
}); 
   
$(document).ready(function() {
    initialiseDatePickerForCadreRegistration();
});

//compareCls
//detailsCls
var globalStatusCount=0;
var globalTargetCount=0;
var globalTotalCnt=0;
var globalTodayTotalCnt=0;
function initialiseDatePickerForCadreRegistration(){
		$("#dateRangeIdForCadre").daterangepicker({
			opens: 'right',
			startDate: moment(),
			endDate: moment(),
			locale: {
			  format: 'DD-MM-YYYY'  
			},
		})
}
$(document).on("click",".cadreExpand",function(){
	if( $(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".detailsCls").hide();         
		$(".compareCls").hide();     
		$(".moreBlocksCadre").hide();      
	}    
});
/* $(document).on("click",".cadreExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".cadreBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	
	setTimeout(function(){
		$(".moreCadreBlock,.moreBlocksCadreIcon").toggle();      
		//getSpokesPersonWiseDebate("top");
	},800);
	getUserTypeWiseTotalCadreRegistrationCount();
	//getRegistrationCountDtls("booth","overall"); 
	$("#constituencySeletBoxId").val(0);
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		setTimeout(function(){
			$(".moreCadreBlock,.moreBlocksCadreIcon,.showTabUserWiseDetails").hide();
		},1000);		
	}else{
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $(".cadreBlock").offset().top},
			'slow');
		},500);
		//getSpokesPersonWiseDebate("top");
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
	}else if( $(".NewTourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".NewTourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".NewTourExpandCls,.NewToursHiddenBlock,.moreNewToursBlocksDetailed").hide();
			$(".NewToursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
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

$(document).on("click",".moreBlocksCadreIcon",function(){
	//aaa
	$(".moreBlocksCadre").toggle();
	$(".compareCls").hide();         
	$(".cadreDetailsCls").addClass("active");
	var filterApplyType="No";
	var accessLevelId=0;
	var accessLevelValues=[];
    var renewal2016CheckboxIsChecked="Y";
	var new2016CheckboxIsChecked="Y";
	var cadre2014CheckboxIsChecked="Y";  
	var is2014Active='No';
	var isKuppamExcluded="True";
	getCadreDetailsBasedOnUserType(filterApplyType);  
	getSourceOfRegistrationDtls(globalActivityMemberId);       
	getSourceOfRegistrationTSDtls(globalActivityMemberId);       
	getTsDistrictWiseTsDetails(accessLevelId,accessLevelValues,filterApplyType);
	getApConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active,isKuppamExcluded);
	getTsConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active);
})
	
   function cadreRegistrationBasicCall(globalActivityMemberId){   
		showCadreRegistreredCount(globalActivityMemberId);
		showCadreRegistreredCountTS(globalActivityMemberId,36);
		getEnumeratorsInfo(globalActivityMemberId);
		getEnumeratorsInfoTS(globalActivityMemberId,36);  
		getCadreRecentTime();    
	}
	  
	/* function getInFieldCountTS(stateId){
		var startDate = '';
		var endDate = '';
		var jsObj={
			stateId : stateId,           
			startDate : '02/10/2016',        
			endDate : getTodayDate()    
		};
		$.ajax({
			type : 'GET',
			url : 'getInFieldCountAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
            if(result != null){
				inFieldTS = result.id;
				totalMemTS = result.attenteeCount;
				$("#memDtlsUpdateProcessImgTShourId").hide();
				$("#memDtlsUpdateProcessImgTStotalId").hide(); 
				$("#inFieldTS").html(inFieldTS);
				$("#totalInFieldTS").html(totalMemTS);      
				//alert("inFieldTS:"+inFieldTS+"totalMemTS:"+totalMemTS);   
			}else{    
			}	    
		});
	} */
	
	//swadhin
	function showCadreRegistreredCount(globalActivityMemberId){
		$("#totalTodayCadreRegistrationBlockDivAPId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		var startDate = '';
		var endDate = '';
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			startDate : '02/10/2016',        
			endDate : getTodayDate()    
		};
		$.ajax({
			type : 'GET',
			url : 'getTotalNewRenewalCadreStateWiseAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
            if(result != null){
				buildTotalTodayRegistrationsAP(result);
				//buildTotalTodayRegistrationsTS(result);
			}else{
				$("#totalTodayCadreRegistrationBlockDivAPId").html('NO DATA AVAILABLE');
			}	
		});	
           
	}
	function showCadreRegistreredCountTS(globalActivityMemberId,stateId){
		$("#totalTodayCadreRegistrationBlockDivAPId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		var startDate = '';
		var endDate = '';
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : stateId,           
			startDate : '02/10/2016',        
			endDate : getTodayDate()    
		};
		$.ajax({
			type : 'GET',
			url : 'getTotalNewRenewalCadreStateWiseTSAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}  
		}).done(function(result){
            if(result != null){
				//buildTotalTodayRegistrationsAP(result,globalStateId);
				buildTotalTodayRegistrationsTS(result);
			}else{
				$("#totalTodayCadreRegistrationBlockDivAPId").html('NO DATA AVAILABLE');
			}	
		});	  
           
	}
	function buildTotalTodayRegistrationsTS(result){
		globalStatusCount=globalStatusCount+1;
		globalTargetCount=globalTargetCount+result.target;
		globalTotalCnt=globalTotalCnt+result.totalCount;
		globalTodayTotalCnt=globalTodayTotalCnt+result.todayTotalCount;
		if(globalStatusCount == 2){
			calculatingPercentage(globalTotalCnt,globalTodayTotalCnt,globalTargetCount);
		}
	 var str= '';
	 // Total today Registrations block
	  str+='<div class="row m_top10">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h4 class="text-capital m_top10"><span class="headingColor">TELANGANA</span></h4>';       
			str+='</div>';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_5 m_top10">';
					str+='<div class="row m_top10">';
						str+='<div class="col-md-12 col-xs-12 col-sm-12">';
							str+='<h5 class="text-capital">total-'+emptyCheck(result.totalPercent)+'%</h5>';
							str+='<h4 class="cadreCount">'+emptyCheck(result.totalCount)+'';  
							str+='<i style="cursor:pointer;font-size:14px;margin-left:5px;" class="glyphicon glyphicon-info-sign compCls"  attr_state_id="36" attr_option="single" data-toggle="tooltip" data-placement="top" title="" data-original-title="Today And Yesterday Comparison"></i></h4>';
						str+='</div>';
						str+='<div class="col-md-12 col-xs-12 col-sm-12 m_XsTop10 m_top10">';
							str+='<h4 class="f_14 text-success">Renewal  <span class="pull-right cadreCount f_14">'+emptyCheck(result.renewalCount)+'</span></h4>';
							str+='<h4 class="f_14" style="color:#F7A423">New  <span class="pull-right cadreCount f_14">'+emptyCheck(result.newCount)+'</span></h4>';
						str+='</div>';
					str+='</div>';
					str+='<div id="totalOverAllRegistrationGraphTS" class="chartLiD" style="height:120px" ></div>'; 
					if(emptyCheck(result.todayRenewalCount) != ' - ')
					{
						str+='<h5 class="text-capital">Today Renewal : <span class="text-success">'+emptyCheck(result.todayRenewalCount)+'</span></h5>';
					}
					if(emptyCheck(result.todayNewCount) != ' - ')
					{
						str+='<h5 class="text-capital">Today New : <span style="color:#F7A423">'+emptyCheck(result.todayNewCount)+'</span></h5>';
					}
				str+='</div>';
			str+='</div>';
			
			/*str+='<div class="col-md-6 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_5 m_top10">';  
					if(result.todayTotalCount != 0){    
					str+='<div class="row m_top10">';
						str+='<div class="col-md-5 col-xs-12 col-sm-12 pad_right0">';
							str+='<h5 class="text-capital">today-'+emptyCheck(result.totalPercentToday)+'%</h5>';
							str+='<h4 class="cadreCount">'+emptyCheck(result.todayTotalCount)+'</h4>';
						str+='</div>';
						str+='<div class="col-md-7 col-xs-12 col-sm-12 pad_left0 m_XsTop10">';
							str+='<h4 class="f_14 text-success">Renewal  <span class="pull-right cadreCount f_14">'+emptyCheck(result.todayRenewalCount)+'</span></h4>';
							str+='<h4 class="f_14" style="color:#F7A423">New  <span class="pull-right cadreCount f_14">'+emptyCheck(result.todayNewCount)+'</span></h4>';
						str+='</div>';  

					str+='</div>';
					str+='<div id="todayOverAllRegistrationGraphTS" class="chartLiD m_top10" style="height:120px" ></div>';
					}      
				str+='</div>';
				
			str+='</div>';*/
			
		str+='</div>';
		
		$("#totalTodayCadreRegistrationBlockDivTSId").html(str);
		$('[data-toggle="tooltip"]').tooltip();
		/* $('.cadreCount').each(function () {
				$(this).prop('Counter',0).animate({
					Counter: $(this).text()
				}, {
					duration: 1500,
					easing: 'swing',
					step: function (now) {
						if(now != null && now > 0)
						$(this).text(Math.ceil(now));
					}
				});
			}); */      
		//total block graph.
			var totalRenewalCountArray = [];
			var totalNewCountArray =[];
			totalRenewalCountArray.push(emptyCheck(result.renewalCount));
			totalNewCountArray.push(emptyCheck(result.newCount));
			
			$('#totalOverAllRegistrationGraphTS').highcharts({
				colors: ['#53BF8B','#f7a423'],
				chart: {
					backgroundColor: '#EDEEF0',
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					 gridLineWidth: 0,  
					 minorGridLineWidth: 0,
					categories: ['Telangana']     
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
						}
					}
				},
				legend: {
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
								(this.y);
						});

						return s;
					},
					shared: true
				},
				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							} 
						  
						}
					}
				},
				series: [{
					name: 'Renewal',
					data: totalRenewalCountArray
				}, {
					name: 'New',
					data: totalNewCountArray
				}]
			});
		
		   //today block graph.
			var todayRenewalCountArray = [];
			var todayNewCountArray =[];
			todayRenewalCountArray.push(emptyCheck(result.todayRenewalCount));
			todayNewCountArray.push(emptyCheck(result.todayNewCount));
			$('#todayOverAllRegistrationGraphTS').highcharts({
				colors: ['#53BF8B','#f7a423'],
				chart: {
					backgroundColor: '#EDEEF0', 
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					 gridLineWidth: 0,
					 minorGridLineWidth: 0,
					categories: ['Telangana']     
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
						}
					}
				},
				legend: {
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
								(this.y);
						});

						return s;
					},
					shared: true
				},
				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							} 
						  
						}
					}
				},
				series: [{
					name: 'Renewal',
					data: todayRenewalCountArray
				}, {
					name: 'New',
					data: todayNewCountArray
				}]  
			});
	         
	}
	function emptyCheck(filedValue){
		var returnVal = ' - ';
		if( filedValue !=null && filedValue > 0){
			returnVal = filedValue;
		}
		return returnVal;
	}
	function buildTotalTodayRegistrationsAP(result){
	 var str= ''; 
	 // Total today Registrations block
	 //$("#overallTodalRegId").html(result.totalCount);
	
	    globalStatusCount=globalStatusCount+1;
		globalTargetCount=globalTargetCount+result.target;
		globalTotalCnt=globalTotalCnt+result.totalCount;
		globalTodayTotalCnt=globalTodayTotalCnt+result.todayTotalCount;
		if(globalStatusCount == 2){
			calculatingPercentage(globalTotalCnt,globalTodayTotalCnt,globalTargetCount);
		}
	  str+='<div class="row m_top10">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h4 class="text-capital m_top10"><span class="headingColor">ANDHRA PRADESH</span></h4>'; 
			str+='</div>';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_5 m_top10">';    
					str+='<div class="row m_top10">';
						str+='<div class="col-md-12 col-xs-12 col-sm-12">';
							
							str+='<h5 class="text-capital">total-'+emptyCheck(result.totalPercent)+'%</h5>';
							str+='<h4 class="cadreCount">'+emptyCheck(result.totalCount)+'';
							str+='<i style="cursor:pointer;font-size:14px;margin-left:5px;" class="glyphicon glyphicon-info-sign compCls" attr_state_id="1" attr_option="single" data-toggle="tooltip" data-placement="top" title="" data-original-title="Today And Yesterday Comparison"></i></h4>';
						str+='</div>';
						str+='<div class="col-md-12 col-xs-12 col-sm-12 m_XsTop10 m_top10">';
							str+='<h4 class="f_14 text-success">Renewal  <span class="pull-right cadreCount f_14">'+emptyCheck(result.renewalCount)+'</span></h4>';
							str+='<h4 class="f_14" style="color:#F7A423">New  <span class="pull-right cadreCount f_14">'+emptyCheck(result.newCount)+'</span></h4>';
						str+='</div>';
					str+='</div>';
					str+='<div id="totalOverAllRegistrationGraph" class="chartLiD" style="height:120px" ></div>'; 
					if(emptyCheck(result.todayRenewalCount) != ' - ')
					{
						str+='<h5 class="text-capital">Today Renewal : <span class="text-success">'+emptyCheck(result.todayRenewalCount)+'</span></h5>';
					}
					if(emptyCheck(result.todayNewCount) != ' - ')
					{
						str+='<h5 class="text-capital">Today New : <span style="color:#F7A423">'+emptyCheck(result.todayNewCount)+'</span></h5>';
					}
				str+='</div>';
				
			str+='</div>';
			
			/*str+='<div class="col-md-6 col-xs-12 col-sm-12">';
				str+='<div class="bg_ED pad_5 m_top10">';  
					if(result.todayTotalCount != 0){    
					str+='<div class="row m_top10">';
						str+='<div class="col-md-5 col-xs-12 col-sm-12 pad_right0">';
							str+='<h5 class="text-capital">today-'+emptyCheck(result.totalPercentToday)+'%</h5>';
							str+='<h4 class="cadreCount">'+emptyCheck(result.todayTotalCount)+'</h4>';
						str+='</div>';
						str+='<div class="col-md-7 col-xs-12 col-sm-12 pad_left0 m_XsTop10">';
							str+='<h4 class="text-success" style="font-size:14px">Renewal  <span class="pull-right cadreCount f_14">'+emptyCheck(result.todayRenewalCount)+'</span></h4>';
							str+='<h4 style="color:#F7A423;font-size:14px">New  <span class="pull-right cadreCount f_14">'+emptyCheck(result.todayNewCount)+'</span></h4>';
						str+='</div>';  

					str+='</div>';
					str+='<div id="todayOverAllRegistrationGraph" class="chartLiD m_top10" style="height:120px" ></div>';
					}      
				str+='</div>';
				
			str+='</div>';*/
			
		str+='</div>';
		
		$("#totalTodayCadreRegistrationBlockDivAPId").html(str);
		$('[data-toggle="tooltip"]').tooltip();
		/* $('.cadreCount').each(function () {
				$(this).prop('Counter',0).animate({
					Counter: $(this).text()
				}, {
					duration: 1500,
					easing: 'swing',
					step: function (now) {
						if(now != null && now > 0)
						$(this).text(Math.ceil(now));
					}
				});
			}); */  
		//total block graph.
			var totalRenewalCountArray = [];
			var totalNewCountArray =[];
			totalRenewalCountArray.push(emptyCheck(result.renewalCount));
			totalNewCountArray.push(emptyCheck(result.newCount));
			
			$('#totalOverAllRegistrationGraph').highcharts({
				colors: ['#53BF8B','#f7a423'],
				chart: {
					backgroundColor: '#EDEEF0',
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					 gridLineWidth: 0,  
					 minorGridLineWidth: 0,
					categories: ['Andhra Pradesh']
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
						}
					}
				},
				legend: {
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
								(this.y);
						});

						return s;
					},
					shared: true
				},
				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							} 
						  
						}
					}
				},
				series: [{
					name: 'Renewal',
					data: totalRenewalCountArray
				}, {
					name: 'New',
					data: totalNewCountArray
				}]
			});
		
		   //today block graph.
			var todayRenewalCountArray = [];
			var todayNewCountArray =[];
			todayRenewalCountArray.push(emptyCheck(result.todayRenewalCount));
			todayNewCountArray.push(emptyCheck(result.todayNewCount));
			$('#todayOverAllRegistrationGraph').highcharts({
				colors: ['#53BF8B','#f7a423'],
				chart: {
					backgroundColor: '#EDEEF0', 
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					 gridLineWidth: 0,
					 minorGridLineWidth: 0,
					categories: ['Andhra Pradesh']
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
						}
					}
				},
				legend: {
					enabled: false,
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
					formatter: function () {
						var s = '<b>' + this.x + '</b>';

						$.each(this.points, function () {
							s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
								Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
								(this.y);
						});

						return s;
					},
					shared: true
				},
				plotOptions: {
					column: {
						stacking: 'percent',
						dataLabels: {
							enabled: true,
							  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							} 
						  
						}
					}
				},
				series: [{
					name: 'Renewal',
					data: todayRenewalCountArray
				}, {
					name: 'New',
					data: todayNewCountArray
				}]  
			});
	         
	}
	function emptyCheck(filedValue){
		var returnVal = ' - ';
		if( filedValue !=null && filedValue > 0){
			returnVal = filedValue;
		}
		return returnVal;
	}
	//swadhin
	function getEnumeratorsInfo(globalActivityMemberId){
		$("#enumeratorsInfoDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var startDate = '';    
		var endDate = ''; 
		var jsObj={  
			activityMemberId : globalActivityMemberId,  
			stateId : globalStateId,         
			startDate : '02/10/2016',      
			endDate : getTodayDate()           
		};
		$.ajax({          
			type : 'GET',       
			url : 'getStateDtls.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
				if(result != null){
					buildEnumeratorsInfo(result);
				}else{
					$("#enumeratorsInfoDivId").html('NO DATA AVAILABLE');
				}	
		});
	} 
function getEnumeratorsInfoTS(globalActivityMemberId,stateId){  
		$("#enumeratorsInfoDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var startDate = '';    
		var endDate = ''; 
		var jsObj={  
			activityMemberId : globalActivityMemberId,
			stateId : stateId,         
			startDate : '02/10/2016',      
			endDate : getTodayDate()           
		};
		$.ajax({          
			type : 'GET',       
			url : 'getStateDtlsTS.action',       
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
				if(result != null){
					buildEnumeratorsInfoTS(result);  
				}else{
					$("#enumeratorsInfoDivTSId").html('NO DATA AVAILABLE');
				}	
		});
	}	
	 
	function buildEnumeratorsInfoTS(result){
		//Enumerators block	
			var str1='';
			str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str1+='<div class="bg_ED pad_15 table-responsive">';
					str1+='<div class="row">';
						str1+='<div class="col-md-2 col-xs-12 col-sm-2">';
							str1+='<img src="newCoreDashBoard/img/TS.png" class="img-responsive" alt="Telangana States" style="width:65px"/><h5 class="text-center">Today</h5>';
						str1+='</div>';
						str1+='<div class="col-md-5 col-xs-12 col-sm-5 text-center" style="text-transform:uppercase;">';
							str1+='<h5 style="cursor:pointer;color:rgb(51, 122, 183)" attr_state_id="36" attr_location_ids="0" class="EnumCadreCount cadreCount getConstituencyCls m_top10">'+emptyCheck(result.todayStartedConsttuncyCnt)+'</h5>';
							str1+='<h5> Started Constituencies</h5>';
							 
							 str1+='<h5 style="cursor:pointer;color:rgb(51, 122, 183)" attr_state_id="36"  attr_location_ids="0" class="EnumCadreCount cadreCount getManalMuncipalityCls m_top10">'+emptyCheck(result.todayStartedMandalMuncipalityCnt)+'</h5>';
							 str1+='<h5>Started<br> Mandals/MUNCIPALITIES</h5>';
							 
						str1+='</div>';
						str1+='<div class="col-md-5 col-xs-12 col-sm-5 text-center" style="text-transform:uppercase;">';
							str1+='<h5 style="cursor:pointer;color:rgb(51, 122, 183)" attr_state_id="36" attr_location_ids='+result.locationIdsList+' class="EnumCadreCount cadreCount getConstituencyCls m_top10">'+emptyCheck(result.todayNotStartedConsttuncyCnt)+'</h5>';
							str1+='<h5> Yet to start Constituencies</h5>';
							str1+='<h5 style="cursor:pointer;color:rgb(51, 122, 183)" attr_state_id="36" attr_location_ids='+result.locationIdsList1+' class="EnumCadreCount cadreCount getManalMuncipalityCls m_top10">'+emptyCheck(result.todayNotStartedMandalMuncipalityCnt)+'</h5>';
							str1+='<h5>Yet To Start<br>  Mandals/MUNCIPALITIES</h5>';
						str1+='</div>';
					str1+='</div>';
					str1+='<hr style="border-color:#B0B4B7;margin-top:10px;margin-bottom:10px;"/>';
					str1+='<span style="position: relative; text-align: center; top: -20px; padding: 3px 8px; background-color: #edeef0; left: 35%;">Today Enumerators Info</span>';
					str1+='<div class="row" style="margin-top:-10px">';
						str1+='<div class="col-md-6 col-xs-12 col-sm-5 text-center">';
							str1+='<h3 class="EnumCadreCount cadreCount"  id="inFieldTS">'+result.inField+'</h3>'; 
							str1+='<h5 class="text-capital">LAST ONE HOUR IN FIELD</h5>';
						str1+='</div>';
						str1+='<div class="col-md-6 col-xs-12 col-sm-5 text-center">';
							str1+='<h3 class="EnumCadreCount cadreCount" id="totalInFieldTS">'+result.todayFieldMembersCount+'</h3>';
							str1+='<h5 class="text-capital">TODAY-TOTAL IN FIELD</h5>';  
						str1+='</div>';
						//str1+='<div class="col-md-4 col-xs-12 col-sm-4 text-center">';
							//str1+='<h3 class="EnumCadreCount cadreCount">'+emptyCheck(result.todaySubmittedCount)+'</h3>';
							//str1+='<h3 class="EnumCadreCount cadreCount">'+emptyCheck(result.totalSubmittedToday)+'</h3>';
							//str1+='<h5 class="text-capital">today submitted data</h5>';    
						//str1+='</div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>';
			$("#enumeratorsInfoDivTSId").html(str1);  
			$('[data-toggle="tooltip"]').tooltip();
			$('.EnumCadreCount').each(function () {
				$(this).prop('Counter',0).animate({
					Counter: $(this).text()
				}, {
					duration: 1500,
					easing: 'swing',
					step: function (now) {
						if(now != null && now > 0)
						$(this).text(Math.ceil(now));
					}
				});
			});
			$("#memDtlsUpdateProcessImgTShourId").show();  
			$("#memDtlsUpdateProcessImgTStotalId").show();
			
	}
	function buildEnumeratorsInfo(result){
		//Enumerators block
		
			var str1='';
			str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str1+='<div class="bg_ED pad_15 table-responsive">';
				str1+='<div class="row">';
						str1+='<div class="col-md-2 col-xs-12 col-sm-2">';
							str1+='<img src="newCoreDashBoard/img/AP.png" class="img-responsive" alt="Andhra Prasdesh" style="width:65px"/><h5>Today</h5>';
						str1+='</div>';
						str1+='<div class="col-md-5 col-xs-12 col-sm-5 text-center" style="text-transform:uppercase;">';
								str1+='<h5 style="cursor:pointer;color:rgb(51, 122, 183)" attr_state_id="1"  attr_location_ids="0" class="EnumCadreCountAP cadreCount getConstituencyCls m_top10">'+emptyCheck(result.todayStartedConsttuncyCnt)+'</h5>';
								str1+='<h5>Started<br> Constituencies</h5>';
								str1+='<h5 style="cursor:pointer;color:rgb(51, 122, 183)" attr_state_id="1"  attr_location_ids="0" class="EnumCadreCountAP cadreCount getManalMuncipalityCls m_top10">'+emptyCheck(result.todayStartedMandalMuncipalityCnt)+'</h5>';
								str1+='<h5>Started<br> Mandals/MUNCIPALITIES</h5>';
							 
						str1+='</div>';
						str1+='<div class="col-md-5 col-xs-12 col-sm-5 text-center" style="text-transform:uppercase;">';
								str1+='<h5 style="cursor:pointer;color:rgb(51, 122, 183)" attr_state_id="1" attr_location_ids='+result.locationIdsList+' class="EnumCadreCountAP cadreCount getConstituencyCls m_top10">'+emptyCheck(result.todayNotStartedConsttuncyCnt)+'</h5>';
								str1+='<h5>Yet To Start<br> Constituencies</h5>';
								str1+='<h5 style="cursor:pointer;color:rgb(51, 122, 183)" attr_state_id="1" attr_location_ids='+result.locationIdsList1+' class="EnumCadreCountAP cadreCount getManalMuncipalityCls m_top10">'+emptyCheck(result.todayNotStartedMandalMuncipalityCnt)+'</h5>';
								str1+='<h5>Yet To Start<br>  Mandals/MUNCIPALITIES</h5>';
						str1+='</div>';
					str1+='</div>';
					str1+='<hr style="border-color:#B0B4B7;margin-top:10px;margin-bottom:10px;"/>';
					str1+='<span style="position: relative; text-align: center; top: -20px; padding: 3px 8px; background-color: #edeef0; left: 35%;">Today Enumerators Info</span>';
					str1+='<div class="row" style="margin-top:-10px">';
						str1+='<div class="col-md-6 col-xs-12 col-sm-5 text-center">';
							str1+='<h3 class="EnumCadreCountAP cadreCount  id="inFieldAP">'+result.inField+'</h3>';       
							str1+='<h5 class="text-capital">LAST ONE HOUR IN FIELD</h5>';
						str1+='</div>';
						str1+='<div class="col-md-6 col-xs-12 col-sm-5 text-center">';
							str1+='<h3 class="EnumCadreCountAP cadreCount"  id="totalInFieldAP">'+result.todayFieldMembersCount+'</h3>';
							str1+='<h5 class="text-capital">TODAY-TOTAL IN FIELD</h5>';  
						str1+='</div>';
						//str1+='<div class="col-md-4 col-xs-12 col-sm-4 text-center">';      
							//str1+='<h3 class="EnumCadreCountAP cadreCount">'+emptyCheck(result.todaySubmittedCount)+'</h3>';     
							//str1+='<h3 class="EnumCadreCountAP cadreCount">'+emptyCheck(result.totalSubmittedToday)+'</h3>';
							//str1+='<h5 class="text-capital">today submitted data</h5>';    
						//str1+='</div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>';
			$("#enumeratorsInfoDivId").html(str1);  
			//$('[data-toggle="tooltip"]').tooltip();
			$('.EnumCadreCountAP').each(function () {
				$(this).prop('Counter',0).animate({
					Counter: $(this).text()
				}, {
					duration: 1500,
					easing: 'swing',
					step: function (now) {
						if(now != null && now > 0)
						$(this).text(Math.ceil(now));  
					}
				});
			});
			$("#memDtlsUpdateProcessImgAPhourId").show();
			$("#memDtlsUpdateProcessImgAPtotalId").show();
			
			
	}
	function getRegistrationCountDtls(location,scope){
		$("#kupamRegDtlsId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		//var location = "booth";
		var jsObj={  
			location : location,       
			constId : 282,
			scope : scope    
		};
		$.ajax({          
			type : 'GET',      
			url : 'getRegistrationCountDtlsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#kupamRegDtlsId").html('');
			//console.log(result);
			buildRegistrationCountDtls(result,location,scope);       
		});
	}
	function buildRegistrationCountDtls(result,location,scope){
		var str = '';
		str+='<div class="table-responsive m_top20">';
          str+='<table class="table table-bordered" id="regCadreCountTableId">';
            str+='<thead class="text-capital text-center">';
              str+='<tr>';
                str+='<th rowspan="2">mandal</th>';
				if(location == "panchayat"){
					str+='<th rowspan="2">panchayat</th>';
				}
                if(location == "booth"){
					str+='<th rowspan="2">panchayat</th>';
					str+='<th rowspan="2">Booth No</th>'; 
				}
                str+='<th rowspan="2">total voters</th>';
                str+='<th rowspan="2">2014 Total Cadre</th>';
				if(scope == "today"){
					str+='<th colspan="6" class="text-capital text-center">2016 Cadre</th>';
				}else{
					str+='<th colspan="5" class="text-capital text-center">2016 Cadre</th>';
				}
                    
              str+='</tr>';
              str+='<tr>';
				str+='<th>renewal Cadre 2016</th>';
                str+='<th>renewal cadre percent(%)</th>';
                str+='<th>total Cadre 2016</th>';
				if(scope == "today"){
					str+='<th>total Cadre On Today</th>'; 
				}
                
                str+='<th>new cadre</th>';
                str+='<th>new cadre percent(%)</th>';
              str+='</tr>';
            str+='</thead>';
			for(var i in result.responseData){  
				str+='<tr>';
				str+='<td>'+result.responseData[i].mandalName+'</td> ';
				if(location == "panchayat"){
					str+='<td>'+result.responseData[i].panchayatName+'</td>';
				}
				if(location == "booth"){
					str+='<td>'+result.responseData[i].panchayatName+'</td>';
					str+='<td>'+result.responseData[i].boothName+'</td>'; 
				}
			   str+='<td>'+result.responseData[i].totalVoter+'</td>';  
			   str+='<td>'+result.responseData[i].cadreCount2014+'</td>';
			   str+='<td>'+result.responseData[i].renewalCount+'</td>';
			   if(result.responseData[i].cadreCount2014 > 0){
				   var precent = (result.responseData[i].renewalCount*(100/result.responseData[i].cadreCount2014)).toFixed(0);
				   str+='<td>'+precent+'</td>';
			   }else{
				   str+='<td>0</td>';
			   }
			   str+='<td>'+result.responseData[i].cadreCount2016OverAll+'</td>';
			   if(scope == "today"){
					str+='<td>'+result.responseData[i].cadreCount2016Today+'</td>';  
			   }			  
            
              
              str+='<td>'+result.responseData[i].newCount+'</td>'; 
			  if(result.responseData[i].cadreCount2016OverAll > 0){    
				  var precent = (result.responseData[i].newCount*(100/result.responseData[i].cadreCount2016OverAll)).toFixed(0);   
				  str+='<td>'+precent+'</td>';     
			  }else{
				  str+='<td>0</td>';  
			  }
              
			  str+='</tr>';
			}
          str+='</table>';
        str+='</div>';
		$("#kupamRegDtlsId").html(str);  
		$("#regCadreCountTableId").dataTable();   
	}
	
	
$(document).on('click','.locationRadioCls',function(){
	var selectionType=$("input:radio[name=selectionType]:checked").val();
	var scopeType=$("input:radio[name=scopeType]:checked").val();
	getRegistrationCountDtls(selectionType,scopeType);  
});
$(document).on('click','.scopeRadioCls',function(){
	var selectionType=$("input:radio[name=selectionType]:checked").val();
	var scopeType=$("input:radio[name=scopeType]:checked").val();
	getRegistrationCountDtls(selectionType,scopeType);    
});
$(document).on('click','#cadreModalDivid',function(){
	$("#cadreModal").modal('show');
	$(".tabModal").hide();
	$(".webModal").show();
	$("#myModalLabel1").html("KUPPAM CONSTITUENCY DETAILED REPORT");
	var location = $("input:radio[name=selectionType]:checked").val();
	var scope = $("input:radio[name=scopeType]:checked").val();
	getRegistrationCountDtls(location,scope);  
});  
$(document).on('click','#cadreModalTabDivid',function(){
	
	$("#tabUserWiseReportDiv").html(' ');
	$("#cadreModal").modal('show');
	$(".tabModal").show();
	$(".webModal").hide();
	$("#myModalLabel1").html("KUPPAM CONSTITUENCY TAB USER DETAILED REPORT");
	 var constituencyId = $("#constituencySeletBoxId").val();
		var dates = $("#dateRangeIdForCadre").val();
		 var fromDate;
		 var toDate;
		 if(dates != null ){
			 var datesArr = dates.split("-");
			 fromDate=datesArr[0]+"-"+datesArr[1]+"-"+datesArr[2];
			 toDate=datesArr[3]+"-"+datesArr[4]+"-"+datesArr[5];
		 }
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth()+1;
		var curr_year = d.getFullYear();
		var fromDates= new Date(convertRequiredDate(fromDate.trim()));
		var toDates= new Date(convertRequiredDate(toDate.trim()));
		var currentDate =curr_date+ "-"+curr_month+"-"+curr_year;
		var currentDates = currentDate;
		var currentDates = new Date(convertRequiredDate(currentDates.trim()));
	   if(currentDates.getTime() == fromDates.getTime() && currentDates.getTime() == toDates.getTime()){
		   $("#notReceiveRegistrationFieldStaffDivId").show();
		  getNotReceiveRegistrationPerson(constituencyId,currentDate);
	   }else{
			$("#notReceiveRegistrationFieldStaffDivId").html(' ');   
			$("#notReceiveRegistrationFieldStaffDivId").hide();   
	   }
	   
		$(".showTabUserWiseDetails").show();
		getCadreRegistrationCountByConstituency(constituencyId,fromDate.trim(),toDate.trim());
});  
 function convertRequiredDate(date){
	   var dateArr=date.split("-");
	   return dateArr[1]+"/"+dateArr[0]+"/"+dateArr[2];
   }
$(document).on('click','.closeModal',function(){
	$("#noOfSamplesModal").modal('hide');
	$("body").addClass('modal-open');
});

$(document).on("click",".applyBtn",function(){
		
		var constituencyId = $("#constituencySeletBoxId").val();
		var dates = $("#dateRangeIdForCadre").val();
		/* if(constituencyId == 0){
			$("#constituencyErrorId").html("Please Select Constituency.");
			return;
		}
		$("#constituencyErrorId").html(' '); */
		 var fromDate;
		 var toDate;
		 if(dates != null ){
			 var datesArr = dates.split("-");
			 fromDate=datesArr[0]+"-"+datesArr[1]+"-"+datesArr[2];
			 toDate=datesArr[3]+"-"+datesArr[4]+"-"+datesArr[5];
		 }
		 var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth()+1;
		var curr_year = d.getFullYear();
		var fromDates= new Date(convertRequiredDate(fromDate.trim()));
		var toDates= new Date(convertRequiredDate(toDate.trim()));
		var currentDate =curr_date+ "-"+curr_month+"-"+curr_year;
		var currentDates = currentDate;
		var currentDates = new Date(convertRequiredDate(currentDates.trim()));
	   if(currentDates.getTime() == fromDates.getTime() && currentDates.getTime() == toDates.getTime()){
		   $("#notReceiveRegistrationFieldStaffDivId").show();
		   getNotReceiveRegistrationPerson(constituencyId,currentDate);
	   }else{
			$("#notReceiveRegistrationFieldStaffDivId").html(' ');   
			$("#notReceiveRegistrationFieldStaffDivId").hide();   
	   }
		$(".showTabUserWiseDetails").show();
		getCadreRegistrationCountByConstituency(constituencyId,fromDate.trim(),toDate.trim());
	});
	
$(document).on("click",".tabUserWiseDetails",function(){
		
		var constituencyId = $("#constituencySeletBoxId").val();
		var dates = $("#dateRangeIdForCadre").val();
		if(constituencyId == 0){
			$("#constituencyErrorId").html("Please Select Constituency.");
			return;
		}
		$("#constituencyErrorId").html(' ');
		 var fromDate;
		 var toDate;
		 if(dates != null ){
			 var datesArr = dates.split("-");
			 fromDate=datesArr[0]+"-"+datesArr[1]+"-"+datesArr[2];
			 toDate=datesArr[3]+"-"+datesArr[4]+"-"+datesArr[5];
		 }
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth()+1;
		var curr_year = d.getFullYear();
		var fromDates= new Date(convertRequiredDate(fromDate.trim()));
		var toDates= new Date(convertRequiredDate(toDate.trim()));
		var currentDate =curr_date+ "-"+curr_month+"-"+curr_year;
		var currentDates = currentDate;
		var currentDates = new Date(convertRequiredDate(currentDates.trim()));
		if(currentDates.getTime() == fromDates.getTime() && currentDates.getTime() == toDates.getTime()){
			   $("#notReceiveRegistrationFieldStaffDivId").show();
			  getNotReceiveRegistrationPerson(constituencyId,currentDate);
		}else{
			$("#notReceiveRegistrationFieldStaffDivId").html(' ');   
			$("#notReceiveRegistrationFieldStaffDivId").hide();   
		 }
		   
		$(".showTabUserWiseDetails").show();
		getCadreRegistrationCountByConstituency(constituencyId,fromDate.trim(),toDate.trim());
	});
function getCadreRegistrationCountByConstituency(constituencyId,fromDate,toDate){
		 $("#tabUserWiseReportDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={  
			constituencyId : constituencyId,      
			fromDate : fromDate,
			toDate : toDate
		};
		$.ajax({          
			type : 'GET',    
			url : 'getCadreRegistrationCountByConstituencyAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#tabUserWiseReportDiv").html('');
		  if(result != null && result.length > 0){
			  buildCadreRegistrationOverViewResult(result);
		  }else{
			  $("#tabUserWiseReportDiv").html("NO DATA AVAILABLE.");
		  }
		});
	}
	function buildCadreRegistrationOverViewResult(tabUserInfoList){
		
			var str='';
			str+='<h4>FIELD USER REGISTRATION DETAILS</h4>';
			str+='<table class="table table-bordered table-condensed m_top10" id="tabUserWiseReportDataTableId"> ';
				str+='<thead> ';
					str+='<tr>';
					    str+='<th>Survey UserId</th>';
						str+='<th>Field Staff Name </th>';
						str+='<th>Image</th>';
						str+='<th>MobileNo</th>';
						str+='<th>No.Of Samples</th>';
						str+='<th>First Record Time</th>';
						str+='<th>Last Record Time</th>';
					str+='</tr>'; 
				str+='</thead>'; 
				str+='<tbody>';
				for(var j in tabUserInfoList){
					if(tabUserInfoList[j].name == "prasad"){
						continue;   
					}
						str+='<tr> ';
						   if(tabUserInfoList[j].userName != null && tabUserInfoList[j].userName.length > 0){
							str+='<td>'+tabUserInfoList[j].userName+'</td>';   
						   }else{
							 str+='<td> - </td>';  
						   }
						   if(tabUserInfoList[j].sampleCount > 0){
							 	if(tabUserInfoList[j].name != null){
							str+='<td>'+tabUserInfoList[j].name+'</td>';
							}else{
								str+='<td> - </td>';	
							}  
						   }else{
							if(tabUserInfoList[j].name != null){
							str+='<td style="color:red;">'+tabUserInfoList[j].name+'</td>';
							}else{
								str+='<td> - </td>';	
							}  
						   }
						 
							str+='<td><img src="http://mytdp.com/CR/tab_user_images/'+tabUserInfoList[j].imagePath+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
							 if(tabUserInfoList[j].mobileNo != null && tabUserInfoList[j].mobileNo.length > 0){
							 str+='<td>'+tabUserInfoList[j].mobileNo+'</td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
							 if(tabUserInfoList[j].sampleCount != null && tabUserInfoList[j].sampleCount> 0){
							 str+='<td><a style="cursor:pointer;" class="noOfSamplesDetailsPopUpView" attr_tab_user_info_id='+tabUserInfoList[j].id+'>'+tabUserInfoList[j].sampleCount+'</a></td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
							 if(tabUserInfoList[j].firstRecordInsertedTime != null && tabUserInfoList[j].firstRecordInsertedTime.length> 0){
							 str+='<td>'+tabUserInfoList[j].firstRecordInsertedTime.substring(0,19)+'</td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
							  if(tabUserInfoList[j].lastRecordInsertedTime != null && tabUserInfoList[j].lastRecordInsertedTime.length> 0){
							 str+='<td>'+tabUserInfoList[j].lastRecordInsertedTime.substring(0,19)+'</td> ';	 	 
							 }else{
							 str+='<td> - </td> ';	 
							  }
						str+='</tr>';
						  }
			str+='</tbody>'; 
		str+='</table>';
		
		$("#tabUserWiseReportDiv").html(str);
		$("#tabUserWiseReportDataTableId").dataTable();  
	}
	
	function setDefaultImage(img){
		img.onerror = "";
		img.src = "images/cadre_images/human.jpg";
		return true;
	}
	
	$(document).on("click",".noOfSamplesDetailsPopUpView",function(){
	   var constituencyId = $("#constituencySeletBoxId").val();
	    var dates = $("#dateRangeIdForCadre").val();
		 var fromDate;
		 var toDate;
		 if(dates != null ){
			 var datesArr = dates.split("-");
			 fromDate=datesArr[0]+"-"+datesArr[1]+"-"+datesArr[2];
			 toDate=datesArr[3]+"-"+datesArr[4]+"-"+datesArr[5];
		 }
		var tabUserInfoId = $(this).attr("attr_tab_user_info_id");
		//var surveyUserId = $(this).attr("attr_survey_user_info_id");
		$("#tabUserInfoDivId").html(' ');
		$("#tabUserInfoDetailsHeadingId").html("No.Of Samples Day Wise Tab User Details");
		$("#noOfSamplesModal").modal("show");
		getDaysByCadreRegistrationCount(constituencyId,fromDate.trim(),toDate.trim(),tabUserInfoId,0);
	});	

function getDaysByCadreRegistrationCount(constituencyId,fromDate,toDate,tabUserInfoId,surveyUserId){
		$("#noOfSamplesDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={  
			constituencyId : constituencyId,      
			fromDate : fromDate,
			toDate : toDate,
			tabUserInfoId : tabUserInfoId,
			cadreSurveyUserId : surveyUserId
		};
		$.ajax({          
			type : 'GET',    
			url : 'getDaysByCadreRegistrationCountAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#noOfSamplesDetailsDiv").html(' ');
			 if(result != null && result.length > 0){
				 buildDaysByCadreRegistrationResult(result);
			 }else{
				$("#noOfSamplesDetailsDiv").html("NO DATA AVAILABLE"); 
			 }
		});
	}

	function buildDaysByCadreRegistrationResult(result){
			var str='';
			str+='<table class="table table-bordered table-condensed"> ';
				str+='<thead> ';
					str+='<tr>';
						str+='<th>Day</th>';
						str+='<th>No.Of Samples</th>';
						str+='<th>First Record Time</th>';
						str+='<th>Last Record Time</th>';
					str+='</tr>'; 
				str+='</thead>'; 
				str+='<tbody>';
				for(var i in result){
					str+='<tr> ';
						 if(result[i].days != null && result[i].days.length > 0){
								str+='<td>'+result[i].days+'</td> '; 
						 }else{
								str+='<td> - </td> ';
						 }
						if(result[i].sampleCount != null && result[i].sampleCount> 0){
						 str+='<td>'+result[i].sampleCount+'</td> ';	 	 
						 }else{
						 str+='<td> - </td> ';	 
						  }
						 if(result[i].firstRecordInsertedTime != null && result[i].firstRecordInsertedTime.length> 0){
						 str+='<td>'+result[i].firstRecordInsertedTime.substring(0,19)+'</td> ';	 	 
						 }else{
						 str+='<td> - </td> ';	 
						  }
						  if(result[i].lastRecordInsertedTime != null && result[i].lastRecordInsertedTime.length> 0){
						 str+='<td>'+result[i].lastRecordInsertedTime.substring(0,19)+'</td> ';	 	 
						 }else{
						 str+='<td> - </td> ';	 
						  }
				   str+='</tr>';
				
				}
			str+='</tbody>'; 
		str+='</table>';
		$("#noOfSamplesDetailsDiv").html(str);
	}
	
	
	function getCadreRecentTime()
	{
		var dateTimeStr = moment().format('DD/MM/YYYY HH:mm');
	/*	$.ajax({
			type : 'GET',
			url : 'getCadreLastUpdatedTimeAction.action',  
			dataType : 'json'
		}).done(function(result){
			if(result != null){
			setLastUpdatedTime(result);      	
			}
		}); */
		setLastUpdatedTime(dateTimeStr);
	}
	
	setInterval(function() {
	statusCall = 0;           
    cadreRegistrationBasicCall();  
  }, 60 * 1000000);  
  

function setLastUpdatedTime(dateTimeStr){ 
	$("#lastUpdatedTimeCadreId").html(" Last Updated : "+dateTimeStr+"");  
}

function getNotReceiveRegistrationPerson(constituencyId,currentDate){
	$("#notReceiveRegistrationFieldStaffDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={  
			constituencyId : constituencyId,      
			date : currentDate
		};
		$.ajax({          
			type : 'GET',    
			url : 'getNotReceiveRegistrationPersonAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#notReceiveRegistrationFieldStaffDivId").html(' ');
		  if(result != null && result.length > 0){
			  buildNotReceivedFieldStaffResult(result);
		  }else{
		  $("#notReceiveRegistrationFieldStaffDivId").html("NO DATA AVAILABLE.");  
		  }
		});
	}
	var htmlColorCodeArr=[];
	    htmlColorCodeArr.push("#FFA500");
	    htmlColorCodeArr.push("#FF8D00");
	    htmlColorCodeArr.push("#ff3232");
	    htmlColorCodeArr.push("#FF0000");
	function buildNotReceivedFieldStaffResult(result){
	var str='';
			str+='<h4>IDLE TAB USER DETAILS</h4>';
			str+='<table class="table table-bordered table-condensed m_top10">';
				str+='<tbody>';
				str+='<tr> ';
				for(var i in result){
					if(result[i].count != null && result[i].count>0){
				     str+='<td><h2><a style="cursor:pointer;" class="tabUserCountcls" attr_Ids='+result[i].idsList+'>'+result[i].count+'</a></h2>';
				     str+='<p style="color:'+htmlColorCodeArr[i]+'">'+result[i].name+'</p></td> ';
					}else{
					  str+='<td><h2>'+result[i].count+'</h2>';
			          str+='<p style="color:'+htmlColorCodeArr[i]+'">'+result[i].name+'</p></td> ';
					}
				}
			 str+='</tr>';
			str+='</tbody>'; 
		str+='</table>';
		$("#notReceiveRegistrationFieldStaffDivId").html(str);
	}
$(document).on("click",".tabUserCountcls",function(){
	var tabUserIdsString = $(this).attr("attr_Ids");
	var idsArr = tabUserIdsString.split(",");
	var tabUserIdStr;
	for(var i=0;i<idsArr.length;i++){
		if(i==0){
		tabUserIdStr = 	idsArr[i];
		}else{
		tabUserIdStr = tabUserIdStr+","+idsArr[i];	
		}
	}
	$("#noOfSamplesDetailsDiv").html(' ');
	$("#tabUserInfoDetailsHeadingId").html("IDLE TAB USER DETAILS.");
	$("#noOfSamplesModal").modal("show");
    getTabUserInfoDetails(tabUserIdStr);
});		
function getTabUserInfoDetails(tabUserIdStr){
	$("#tabUserInfoDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div   class="dot2"></div></div></div>');
		var jsObj={  
			tabUserInfoStrIds :tabUserIdStr      
		};
		$.ajax({          
			type : 'GET',    
			url : 'getTabUserInfoDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
		  if(result != null && result.length > 0){
			buildNotReceivedTabUserDetails(result);  
		  }else{
			$("#tabUserInfoDivId").html("NO DATA AVAILABLE.");  
		  }
		});
	}	
	function buildNotReceivedTabUserDetails(result){
		var str=''
		str+='<table class="table table-bordered table-condensed" id="fieldStaffDetailsDataTableId"> ';
		        str+='<thead>';
				 str+='<th>Name</th>'
				 str+='<th>MobileNo</th>'
				 str+='<th>Image</th>'
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr> ';
					 if(result[i].name != null && result[i].name.length > 0){
							str+='<td>'+result[i].name+'</td> '; 
					 }else{
							str+='<td> - </td> ';
					 }
					 if(result[i].mobileNo != null && result[i].mobileNo.length > 0){
					 str+='<td>'+result[i].mobileNo+'</td> ';	 	 
					 }else{
					 str+='<td> - </td> ';	 
					  }
					 str+='<td><img src="http://mytdp.com/CR/tab_user_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
				   str+='</tr>';
				}
			str+='</tbody>'; 
		str+='</table>';
		$("#tabUserInfoDivId").html(str);
		$("#fieldStaffDetailsDataTableId").dataTable();
	}
	
	
	/*  cadre registration  new core dashboard block      */
	
	var globalUserTypeWiseCadreRegistrationCountRslt;
	var globalUserTypeBySubLevelRslt;
	var globalTsDistrictRslt;
	var globalApConstituencyRslt;
	var globalTsConstituencyRslt;
	var globalTsConstituencyWithDefaultSorting;
	var globalApConstituencyWithDefaultSorting;
	var globalTsConstituencyWithAscendingArr=[];
	var globalApConstituencyWithAscendingArr=[];
	var globalSubLevelRslt;
	var globalSortingType = '';
	 function getUserTypeWiseTotalCadreRegistrationCount(){
		$("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		 var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});
		globalSortingType = sortingType;  
		var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 userTypeId : globalUserTypeId,
				 fromDate : "2/10/2016",
				 todate : getTodayDate(),
				 sortingType:sortingType
			  }
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseTotalCadreRegistrationCountAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html(' ');
			buildgUserTypeWiseCadreRegistrationTopPositiveRslt(result,sortingType);
			globalUserTypeWiseCadreRegistrationCountRslt = result;
		});
 }
 function buildgUserTypeWiseCadreRegistrationTopPositiveRslt(result,sortingType){
		var str='';
		if(result != null && result.length > 0){
		  var str='';
		  for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				 if(result[i][0].userTypeId==4 || result[i][0].userTypeId==11){
				  if(result[i][0].userTypeId==4){
				  if(result[i][0].totalCadreCountPer!=0){
					  str+='<h5 class="text-capital">'+result[i][0].userType+' / SECRETARY </h5>';      
				  }
				  }
				  if(result[i][0].userTypeId==11){
				   if(result[i][0].totalCadreCountPer!=0){
					 str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].userType+'</h5>';      
				   }
			     }
			   }else{
				 if(result[i][0].totalCadreCountPer!=0){
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>'; 
				 }
		      }
			  str+='<div id="genSecCadre'+i+'" style="height:80px;"></div>';
			str+='</div>'
		  }
		}
		$("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html(str);
	   if(result != null && result.length > 0){
			for(var i in result){
				var candidateNameArray = [];
				var totalCadreCountPerArr = [];
				var countVar =0;
			  if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						countVar =countVar+1;
						candidateNameArray.push(result[i][j].name);
						if(sortingType == "TargetWise"){
							totalCadreCountPerArr.push(result[i][j].totalCadreCountPer);//totalCadreCount
						}else{
							totalCadreCountPerArr.push(result[i][j].totalCadreCount);//totalCadreCount
						}
						
						if (countVar === 5) {
							break;
						}
					}
				}
		if(result[i][0].totalCadreCountPer!=0){
				var getWidth = $("#genSecCadre"+i).parent().width()+'px';
				$("#genSecCadre"+i).width(getWidth);
		     $(function () {
			$('#genSecCadre'+i).highcharts({
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
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
				tooltip: {
				
				formatter: function(){
					if(sortingType == "TargetWise"){
						return '<b>'+ Highcharts.numberFormat(this.y, 2) +'%</b><br/>'+
                    'Name: '+ this.x;
					}else{
						return '<b>'+ Highcharts.numberFormat(this.y, 0) +'</b><br/>'+
                    'Name: '+ this.x;      
					}  
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
									if(sortingType == "TargetWise"){
										return Highcharts.numberFormat(this.y,2) +"%";
									}else{
										return Highcharts.numberFormat(this.y,0);
									}
								}
							}
						  
						}
					}
				},
				legend: {  
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
					name: 'Cadre',
					data: totalCadreCountPerArr
				}]
			});
		});
		}else{
		$("#genSecCadre"+i).html("No Data Available");
		$("#genSecCadre"+i).css("height","35px");
		$("#genSecCadre"+i).hide();
		} 
	}
	}else{
    $("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html('NO DATA AVAILABLE.');
	}
	}
	
	$(document).on("click",".cadrePositiveNegativeCls",function(){
	var resultType=$(this).attr("attr_value");
	 if(resultType != null && resultType == "positive"){
	  buildgUserTypeWiseCadreRegistrationTopPositiveRslt(globalUserTypeWiseCadreRegistrationCountRslt,globalSortingType); 
	 }else if(resultType == "negative"){
	  buildgUserTypeWiseCadreRegistrationTopNegitiveRslt(globalUserTypeWiseCadreRegistrationCountRslt,globalSortingType);
	 }
});
	function buildgUserTypeWiseCadreRegistrationTopNegitiveRslt(result,globalSortingType){
		var str='';
		if(result != null && result.length > 0){
			var str='';
			for(var i in result){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				 if(result[i][0].userTypeId==4 || result[i][0].userTypeId==11){
				  if(result[i][0].userTypeId==4){
				   str+='<h5 class="text-capital">'+result[i][0].userType+' / SECRETARY </h5>';      
				  }
				  if(result[i][0].userTypeId==11){
				   str+='<h5 class="text-capital">ORGANIZING SECRETARY /'+result[i][0].userType+'</h5>';      
				  }
			   }else{
				str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>'; 
			   }
				str+='<div id="genSecCadre'+i+'" style="height:100px;"></div>';
				str+='</div>'
			}
		}
		$("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html(str);   
	if(result != null && result.length > 0){
		for(var i in result){
				var candidateNameArray = [];
				var totalCadreCountPerArr = [];
				var countVar = 0;
				if(result[i] != null && result[i].length > 0){
					var length = result[i].length - 1;
					for(var j = length; j >= 0; j--){
						candidateNameArray.push(result[i][j].name);
						if(globalSortingType == "TargetWise"){
							totalCadreCountPerArr.push(result[i][j].totalCadreCountPer);//totalCadreCount
						}else{
							totalCadreCountPerArr.push(result[i][j].totalCadreCount);//totalCadreCount
						}
						//totalCadreCountPerArr.push(result[i][j].totalCadreCountPer);
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}	
				}
			//if( result[i][j].totalCadreCountPer!=0){
		//if(totalCadreCountPerArr.length > 0){	
			var getWidth = $("#genSecCadre"+i).parent().width()+'px';
				$("#genSecCadre"+i).width(getWidth);
				$(function () {
			   $('#genSecCadre'+i).highcharts({
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
								return this.value.toString().substring(0, 10);
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
				tooltip: {
				formatter: function(){  
					if(globalSortingType == "TargetWise"){
						return '<b>'+ Highcharts.numberFormat(this.y, 2) +'%</b><br/>'+
						'Name: '+ this.x;
					}else{
						return '<b>'+ Highcharts.numberFormat(this.y, 0) +'</b><br/>'+
						'Name: '+ this.x;        
					}  
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
									if(globalSortingType == "TargetWise"){
										return Highcharts.numberFormat(this.y,2) +"%";
									}else{
										return Highcharts.numberFormat(this.y,0);
									}
								}
							}
						  
						}
					}
				},
				legend: {
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
					name: 'Cadre',
					data: totalCadreCountPerArr
				}]
			});
		});
		//}
		/* }else{
		$("#genSecCadre"+i).html("No Data Available");
		$("#genSecCadre"+i).css("height","35px");	
		} */
		}
	}else{
	 $("#userTypeWiseTop5PositiveAndNegitiveCadreDivId").html('NO DATA AVAILABLE.');
	}
	} 
	
	 function getCadreDetailsBasedOnUserType(filterApplyType){
		 $("#userTypeWiseHighChartDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var sortingType = '';
		
	     $(".districtFilterUlCls li").removeClass("active");
	     $(".districtFilterUlCls li:first-child").addClass("active");
		
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
		 $("#dstrctOverlAllCntId").html("All - 0 ");
		 $("#dstrctyveryGoodCntId").html("Very Good - 0");
		 $("#dstrctGoodCntId").html("Good - 0");
		 $("#dstrctOkCntId").html("Ok - 0");
		 $("#dstrctPoorCntId").html("Poor - 0");
		 $("#dstrctVeryPoorCntId").html("Very Poor - 0");
		 
		 $("#dstrctOverlAllCntId").attr("attr_over_all_cnt",0);
		 $("#dstrctyveryGoodCntId").attr("attr_very_good_cnt",0);
		 $("#dstrctGoodCntId").attr("attr_good_cnt",0);
		 $("#dstrctOkCntId").attr("attr_ok_cnt",0);
		 $("#dstrctPoorCntId").attr("attr_poor_cnt",0);
		 $("#dstrctVeryPoorCntId").attr("attr_very_poor_cnt",0);
		 
		var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : globalStateId,
				 userTypeId : globalUserTypeId,
				 fromDate : "2/10/2016",
				 todate : getTodayDate(),
				 sortingType:sortingType
				 
			  }
		$.ajax({
			type : 'POST',
			url : 'getCadreDetailsBasedOnUserTypeAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		       buildUserTypeWiseHighchartsRslt(result,"userTypeWiseHighChartDivId",filterApplyType,sortingType);
			   globalUserTypeBySubLevelRslt = result;
			    if(result != null && result.length > 0){
			     var overAllDistrictRankRslt = result[0];
			     $("#dstrctOverlAllCntId").html("All - "+((overAllDistrictRankRslt.allDistrictCnt)+(parseInt($("#dstrctOverlAllCntId").attr("attr_over_all_cnt")))));
				 $("#dstrctyveryGoodCntId").html("Very Good - "+((overAllDistrictRankRslt.veryGoodCnt)+(parseInt($("#dstrctyveryGoodCntId").attr("attr_very_good_cnt")))));
				 $("#dstrctGoodCntId").html("Good - "+((overAllDistrictRankRslt.goodCnt)+(parseInt($("#dstrctGoodCntId").attr("attr_good_cnt")))));
				 $("#dstrctOkCntId").html("Ok - "+((overAllDistrictRankRslt.okCnt)+(parseInt($("#dstrctOkCntId").attr("attr_ok_cnt")))));
				 $("#dstrctPoorCntId").html("Poor - "+((overAllDistrictRankRslt.poorCnt)+(parseInt($("#dstrctPoorCntId").attr("attr_poor_cnt")))));
				 $("#dstrctVeryPoorCntId").html("Very Poor - "+((overAllDistrictRankRslt.veryPoorCnt)+(parseInt($("#dstrctVeryPoorCntId").attr("attr_very_poor_cnt")))));
				 
				 $("#dstrctOverlAllCntId").attr("attr_over_all_cnt",overAllDistrictRankRslt.allDistrictCnt);
				 $("#dstrctyveryGoodCntId").attr("attr_very_good_cnt",overAllDistrictRankRslt.veryGoodCnt);
				 $("#dstrctGoodCntId").attr("attr_good_cnt",overAllDistrictRankRslt.goodCnt);
				 $("#dstrctOkCntId").attr("attr_ok_cnt",overAllDistrictRankRslt.okCnt);
				 $("#dstrctPoorCntId").attr("attr_poor_cnt",overAllDistrictRankRslt.poorCnt);
				 $("#dstrctVeryPoorCntId").attr("attr_very_poor_cnt",overAllDistrictRankRslt.veryPoorCnt);
		  }		   
		  });
	}

 $(document).on("click",".districtFilterCls",function(){
     var filterApplyType = $(this).attr("attr_filter_value");
	     var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
     buildUserTypeWiseHighchartsRslt(globalUserTypeBySubLevelRslt,"userTypeWiseHighChartDivId",filterApplyType,sortingType);
	 buildUserTypeWiseHighchartsRslt(globalTsDistrictRslt,"tsDistrictWiseRegistrationDivId",filterApplyType,sortingType);
  });
   function getTsDistrictWiseTsDetails(accessLevelId,accessLevelValues,filterApplyType,sortingType){
	   $("#tsDistrictWiseRegistrationDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	   	 var sortingType = '';
		 
		 $(".districtFilterUlCls li").removeClass("active");
	     $(".districtFilterUlCls li:first-child").addClass("active");
		
		$("#dstrctOverlAllCntId").html("All - 0 ");
		 $("#dstrctyveryGoodCntId").html("Very Good - 0");
		 $("#dstrctGoodCntId").html("Good - 0");
		 $("#dstrctOkCntId").html("Ok - 0");
		 $("#dstrctPoorCntId").html("Poor - 0");
		 $("#dstrctVeryPoorCntId").html("Very Poor - 0");
		 
		 $("#dstrctOverlAllCntId").attr("attr_over_all_cnt",0);
		 $("#dstrctyveryGoodCntId").attr("attr_very_good_cnt",0);
		 $("#dstrctGoodCntId").attr("attr_good_cnt",0);
		 $("#dstrctOkCntId").attr("attr_ok_cnt",0);
		 $("#dstrctPoorCntId").attr("attr_poor_cnt",0);
		 $("#dstrctVeryPoorCntId").attr("attr_very_poor_cnt",0);
		 
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
		var jsObj ={ 
				 locationType : "District",
				 stateId : 36,
				 fromDate : "2/10/2016",
				 todate : getTodayDate(),
				 accessLevelId:accessLevelId,
				 accessLevelValues:accessLevelValues,
				 sortingType:sortingType
				 
			  }
		$.ajax({
			type : 'POST',
			url : 'getTsDistrictDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   buildUserTypeWiseHighchartsRslt(result,"tsDistrictWiseRegistrationDivId",filterApplyType,sortingType);
           globalTsDistrictRslt	= result;	
          if(result != null && result.length > 0){
			  var overAllDistrictRankRslt = result[0];
			    $("#dstrctOverlAllCntId").html("All - "+((overAllDistrictRankRslt.allDistrictCnt)+(parseInt($("#dstrctOverlAllCntId").attr("attr_over_all_cnt")))));
				 $("#dstrctyveryGoodCntId").html("Very Good - "+((overAllDistrictRankRslt.veryGoodCnt)+(parseInt($("#dstrctyveryGoodCntId").attr("attr_very_good_cnt")))));
				 $("#dstrctGoodCntId").html("Good - "+((overAllDistrictRankRslt.goodCnt)+(parseInt($("#dstrctGoodCntId").attr("attr_good_cnt")))));
				 $("#dstrctOkCntId").html("Ok - "+((overAllDistrictRankRslt.okCnt)+(parseInt($("#dstrctOkCntId").attr("attr_ok_cnt")))));
				 $("#dstrctPoorCntId").html("Poor - "+((overAllDistrictRankRslt.poorCnt)+(parseInt($("#dstrctPoorCntId").attr("attr_poor_cnt")))));
				 $("#dstrctVeryPoorCntId").html("Very Poor - "+((overAllDistrictRankRslt.veryPoorCnt)+(parseInt($("#dstrctVeryPoorCntId").attr("attr_very_poor_cnt")))));
				 
				 $("#dstrctOverlAllCntId").attr("attr_over_all_cnt",overAllDistrictRankRslt.allDistrictCnt);
				 $("#dstrctyveryGoodCntId").attr("attr_very_good_cnt",overAllDistrictRankRslt.veryGoodCnt);
				 $("#dstrctGoodCntId").attr("attr_good_cnt",overAllDistrictRankRslt.goodCnt);
				 $("#dstrctOkCntId").attr("attr_ok_cnt",overAllDistrictRankRslt.okCnt);
				 $("#dstrctPoorCntId").attr("attr_poor_cnt",overAllDistrictRankRslt.poorCnt);
				 $("#dstrctVeryPoorCntId").attr("attr_very_poor_cnt",overAllDistrictRankRslt.veryPoorCnt);
		  }		   
	     });
 }			
  function buildUserTypeWiseHighchartsRslt(result,divId,filterApplyType,sortingType){
	   $("#"+divId).html(' ');
	    var locationNameArr =[];
		var newCadreArr = [];
		var cadre2014ArrPer = [];
		var renewalArr=[];
		var newCadre2016CntArr = [];
		var renewalCadre2016CntArr = [];
		var cadre2014CntArr = [];
	if(result != null && result.length > 0){
			for(var i in result){
			    if(filterApplyType != null && filterApplyType=="All" || filterApplyType =="No"){
						locationNameArr.push(result[i].locationName);
						if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
						}else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
						}
				 }else if(filterApplyType=="verygood"){
				 if(result[i].total2016CadrePer > 100){
					  locationNameArr.push(result[i].locationName);
					  if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
						}else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
						}
				 }	
				}else if(filterApplyType=="good"){
				  if(result[i].total2016CadrePer > 90 && result[i].total2016CadrePer<=100){
					   locationNameArr.push(result[i].locationName);
					  if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
					  }else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
					  }
				  }
				}else if(filterApplyType=="ok"){
					if(result[i].total2016CadrePer > 80 && result[i].total2016CadrePer<=90){
					   locationNameArr.push(result[i].locationName);
				       if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
						}else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
						}
				    }
				}else if(filterApplyType=="poor"){
				  if(result[i].total2016CadrePer > 60 && result[i].total2016CadrePer <= 80){
					 locationNameArr.push(result[i].locationName);
					   if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
						}else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
						}
				  }
				}else if(filterApplyType == "verypoor"){
					if(result[i].total2016CadrePer < 60){
				      locationNameArr.push(result[i].locationName);
				      if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
						}else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
						}
					}
				}
			}
		 var jsonDataArr=[];	
		 var colorArr=[];
			if(sortingType == "2016CadreWise"){
				 if(renewalCadre2016CntArr.length > 0){
				  jsonDataArr.push({name: '2016 Renewal Cadre',data: renewalCadre2016CntArr,stack: '2016'});
				  colorArr.push('#30AA74');		  
				  }
				  if(newCadre2016CntArr.length > 0){
				  jsonDataArr.push({name: '2016 New Cadre',data: newCadre2016CntArr,stack: '2016'});
				  colorArr.push('#F36800');		  
				  }
				  if(cadre2014CntArr.length > 0){
				  jsonDataArr.push({name: '2014 Cadre',data: cadre2014CntArr,stack: '2014'});
				  colorArr.push('#FFCA00');		  
				  }
			}else if(sortingType == "TargetWise"){ 
				 if(renewalArr.length > 0){
				  jsonDataArr.push({name: '2016 Renewal Cadre',data: renewalArr,stack: '2016'});
				  colorArr.push('#30AA74');		  
				  }
				  if(newCadreArr.length > 0){
				  jsonDataArr.push({name: '2016 New Cadre',data: newCadreArr,stack: '2016'});
				  colorArr.push('#F36800');		  
				  }
				  if(cadre2014ArrPer.length > 0){
				  jsonDataArr.push({name: '2014 Cadre',data: cadre2014ArrPer,stack: '2014'});
				  colorArr.push('#FFCA00');		  
				  }
			}
		  if(divId=="tsDistrictWiseRegistrationDivId"){
			  if(sortingType == "2016CadreWise"){
				  if(renewalCadre2016CntArr.length == 0 && newCadre2016CntArr.length == 0 && cadre2014CntArr.length==0){
					  return;
				  }else{
					  $("#tsDistrictHeadingId").show();
				  }  
			  }else if(sortingType == "TargetWise"){
				  if(cadre2014ArrPer.length == 0 && newCadreArr.length == 0 && renewalArr.length==0){
					  return;
				  }else{
					  $("#tsDistrictHeadingId").show();
				  }  
			  }
		  }else if(divId=="userTypeWiseHighChartDivId"){
			   if(sortingType == "2016CadreWise"){
				  if(renewalCadre2016CntArr.length == 0 && newCadre2016CntArr.length == 0 && cadre2014CntArr.length==0){
					  return;
				  }else{
					  $("#apDistrictHeadingId").show();
				  }  
			  }else if(sortingType == "TargetWise"){
				  if(cadre2014ArrPer.length == 0 && newCadreArr.length == 0 && renewalArr.length==0){
					  return;
				  }else{
					  $("#apDistrictHeadingId").show();
				  }  
			  }
		  }
		  $("#"+divId).highcharts({
			 colors: colorArr,
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
			   categories: locationNameArr
			},

			yAxis: {
				allowDecimals: true,
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ' '
				}
			},
			tooltip: {
			 formatter: function() {
					var series = this.point.series.chart.series, // get all series 
					index = this.point.series.xData.indexOf(this.point.x); // get index	
					var _locationName = this.x;
					var obj = result.filter(function ( obj ) {
						var objLocation = obj.locationName;
						return objLocation.toUpperCase() === _locationName.toUpperCase();
					})[0];
					var renewalPer = this.series.name=="2016 Renewal Cadre"? " - " + this.y + "%":'',
					newPer = this.series.name=="2016 New Cadre"? " - " + this.y + "%":'',
					cadre2014 = this.series.name=="2014 Cadre"?"<br/>"+this.series.name +": " +obj.total2014CadreCnt +" - "+obj.total2014CadrePer + "%":'';
							  
					return '<b>'+this.x +'<br/>2016 Total Registrations:' + obj.total2016CadreCnt+" - "+obj.total2016CadrePer+'%<br/>2016 New Cadre:' + obj.total2016NewCadreCount +" - "+obj.total2016NewCadrePer+'%<br/>2016 Renewal Cadre:'+ obj.total2016RenewalCadreCount+" - "+obj.total2016RenewalCadrePer+"%"+cadre2014; 
				}
			},
           plotOptions: {
				column: {
					stacking: 'normal'
				}
			},
			series: jsonDataArr
		}); 
   }else{
	 $("#"+divId).html('NO DATA AVAILABLE.');	 
	}
	 $("#"+divId).find(".highcharts-legend-item:nth-child(3)").trigger("click");
  }
  function getApConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active,isKuppamExcluded){
	  $("#apConstituencyRegistrationReportDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	    $(".apConstituencyUlCls li").removeClass("active");
	    $(".apConstituencyUlCls li:first-child").addClass("active");
		
	     $("#cnsttncyverlAllCntId").html("All - 0 ");
		 $("#cnsttncyveryGoodCntId").html("Very Good - 0 ");
		 $("#cnsttncyverlGoodCntId").html("Good - 0 ");
		 $("#cnsttncyOkCntId").html("Ok - 0");
		 $("#cnsttncyverlPoorCntId").html("Poor - 0 ");
		 $("#cnsttncyveryPoorCntId").html("Very Poor - 0 ");
	   var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
       var filterApplyType="No";
		var jsObj ={ 
				 locationType : "Constituency",
				 stateId : 1,
				 fromDate : "2/10/2016",
				 todate : getTodayDate(),
				 accessLevelId:accessLevelId, 
				 accessLevelValues:accessLevelValues,
				 isKuppamExcluded:"False",
				 sortingType:sortingType
			  }
		$.ajax({
			type : 'POST',
			url : 'getApAndTsConstituenciesDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			  buildConstituecnyWiseCadreResult(result,"apConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType);
			  buildDropDown(result,"apConstituencySelectBoxId",filterApplyType);
			  globalApConstituencyRslt=result;
		      globalApConstituencyWithDefaultSorting=result;
	
			  if(result != null && result.length > 0){
				  var overallApConstituencyRankRslt=result[0]; 
					 $("#cnsttncyverlAllCntId").html("All - "+(overallApConstituencyRankRslt.allConstituencyCnt));
					 $("#cnsttncyveryGoodCntId").html("Very Good - "+(overallApConstituencyRankRslt.veryGoodCnt));
					 $("#cnsttncyverlGoodCntId").html("Good - "+(overallApConstituencyRankRslt.goodCnt));
					 $("#cnsttncyOkCntId").html("Ok - "+(overallApConstituencyRankRslt.okCnt));
					 $("#cnsttncyverlPoorCntId").html("Poor - "+(overallApConstituencyRankRslt.poorCnt));
					 $("#cnsttncyveryPoorCntId").html("Very Poor - "+(overallApConstituencyRankRslt.veryPoorCnt));
					 if(result != null && result.length > 0){
						 globalApConstituencyWithAscendingArr=[];
						 for(var counter=result.length - 1; counter >= 0;counter--){
						    globalApConstituencyWithAscendingArr.push(result[counter]);
						 } 
					 }
			  }
	     });
 }
   function getTsConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active){
	   $("#tsConstituencyRegistrationReportDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	   
	     var filterApplyType="No";
		 
		 $(".tsConstituencyUlCls li").removeClass("active");
	     $(".tsConstituencyUlCls li:first-child").addClass("active");
		 
		 $("#tsCnsttncyverlAllCntId").html("All - 0 ");
		 $("#tsCnsttncyveryGoodCntId").html("Very Good - 0 ");
		 $("#tsCnsttncyverlGoodCntId").html("Good - 0 ");
		 $("#tsCnsttncyOkCntId").html("Ok - ");
		 $("#tsCnsttncyverlPoorCntId").html("Poor - 0 ");
		 $("#tsCnsttncyveryPoorCntId").html("Very Poor - 0 ");
		var sortingType = '';
	    $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
		var jsObj ={ 
				 locationType : "Constituency",
				 stateId : 36,
				 fromDate : "2/10/2016",
				 todate : getTodayDate(),
				 accessLevelId:accessLevelId,
				 accessLevelValues:accessLevelValues,
				 isKuppamExcluded :"False",
				 sortingType:sortingType
			  }
		$.ajax({
			type : 'POST',
			url : 'getApAndTsConstituenciesDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
	    	  buildConstituecnyWiseCadreResult(result,"tsConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType);
			  globalTsConstituencyRslt=result; 
			  globalTsConstituencyWithDefaultSorting=result;
			  buildDropDown(result,"tsConstituencySelectBoxId",filterApplyType);
			 if(result != null && result.length > 0){
				  var overallApConstituencyRankRslt=result[0]; 
				 $("#tsCnsttncyverlAllCntId").html("All - "+(overallApConstituencyRankRslt.allConstituencyCnt));
				 $("#tsCnsttncyveryGoodCntId").html("Very Good - "+(overallApConstituencyRankRslt.veryGoodCnt));
				 $("#tsCnsttncyverlGoodCntId").html("Good - "+(overallApConstituencyRankRslt.goodCnt));
				 $("#tsCnsttncyOkCntId").html("Ok - "+(overallApConstituencyRankRslt.okCnt));
				 $("#tsCnsttncyverlPoorCntId").html("Poor - "+(overallApConstituencyRankRslt.poorCnt));
				 $("#tsCnsttncyveryPoorCntId").html("Very Poor - "+(overallApConstituencyRankRslt.veryPoorCnt));
			
			if(result != null && result.length > 0){
					  globalTsConstituencyWithAscendingArr=[];
					for(var counter=result.length - 1; counter >= 0;counter--){
					  globalTsConstituencyWithAscendingArr.push(result[counter]);
					} 
				  }
			 }
	     });
 }
  function buildDropDown(result,divId,filterApplyType){
	  $("#"+divId).empty();
	  $("#"+divId).append("<option value='0'>All</option>");
	 	if(result != null && result.length > 0){
			  for(var i in result){
				 if(filterApplyType=="No" || filterApplyType=="All"){
					  $("#"+divId).append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>")
				 }else if(filterApplyType=="verygood"){
				 if(result[i].total2016CadrePer > 100){
				   $("#"+divId).append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>")
				 }	
				}else if(filterApplyType=="good"){
				  if(result[i].total2016CadrePer > 90 && result[i].total2016CadrePer<=100){
						$("#"+divId).append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>")
				  }
				}else if(filterApplyType=="ok"){
					  if(result[i].total2016CadrePer > 80 && result[i].total2016CadrePer<=90){
					  $("#"+divId).append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>")
					  }
				}else if(filterApplyType=="poor"){
				  if(result[i].total2016CadrePer > 60 && result[i].total2016CadrePer <= 80){
					 $("#"+divId).append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>")
				  }
				}else if(filterApplyType == "verypoor"){
					if(result[i].total2016CadrePer < 60){
					   $("#"+divId).append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>")
					}
				}	  
		  }
	}
  }
  $(document).on("change","#apConstituencySelectBoxId",function(){
	 var constituencyId = $(this).val();
	 var searchFilterArr=[];
	   if(constituencyId == 0){
		 searchFilterArr = globalApConstituencyRslt; 
	  }else{
		  for(var i in globalApConstituencyRslt){
		  if(globalApConstituencyRslt[i].locationId==constituencyId){
			searchFilterArr.push(globalApConstituencyRslt[i]);  
		  }
	   }	 
	  }
       var renewal2016CheckboxIsChecked="Y";
	   var new2016CheckboxIsChecked="Y";
	   var cadre2014CheckboxIsChecked="Y";
	   var is2014Active="No";
	   var filterApplyType=$(this).attr("attr_filter_value");
	   if($("#2016RenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016RenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016NewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016NewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014CadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014CadreCheckBoxId").attr("attr_cadre_search_type");
		  is2014Active=$("#2014CadreCheckBoxId").attr("attr_2014_active");
	  }
	   var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
	 buildConstituecnyWiseCadreResult(searchFilterArr,"apConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType);
  });
    $(document).on("change","#tsConstituencySelectBoxId",function(){
	  var constituencyId = $(this).val();
      var searchFilterArr=[];
	  if(constituencyId == 0){
		 searchFilterArr = globalTsConstituencyRslt; 
	  }else{
		  for(var i in globalTsConstituencyRslt){
		  if(globalTsConstituencyRslt[i].locationId==constituencyId){
			searchFilterArr.push(globalTsConstituencyRslt[i]);  
		  }
	   }	   
	  }
      var renewal2016CheckboxIsChecked="Y";
	   var new2016CheckboxIsChecked="Y";
	   var cadre2014CheckboxIsChecked="Y";
	   var is2014Active='No';
	   var filterApplyType=$(this).attr("attr_filter_value");
	   if($("#2016TsRenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016TsRenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016TsNewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016TsNewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014TsCadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014TsCadreCheckBoxId").attr("attr_cadre_search_type");
		  is2014Active=$("#2014TsCadreCheckBoxId").attr("attr_2014_active");
	  }
	   var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
	   buildConstituecnyWiseCadreResult(searchFilterArr,"tsConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType);
  });
  
  $(document).on("click",".ascendingApSorting",function(){
	   var apConsascendingOrderArr=[];
	   var renewal2016CheckboxIsChecked="Y";
	   var new2016CheckboxIsChecked="Y";
	   var cadre2014CheckboxIsChecked="Y";
	   var is2014Active="No";
	   var filterApplyType=$(this).attr("attr_filter_value");
	   if($("#2016RenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016RenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016NewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016NewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014CadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014CadreCheckBoxId").attr("attr_cadre_search_type");
		    is2014Active=$("#2014CadreCheckBoxId").attr("attr_2014_active");
	  }
	   var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
	  $("#apConstituencySelectBoxId").val(0);
		buildConstituecnyWiseCadreResult(globalApConstituencyWithAscendingArr,"apConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType);
	   globalApConstituencyRslt = globalApConstituencyWithAscendingArr;
 	
 });
  $(document).on("click",".decendingApSorting",function(){
	   var renewal2016CheckboxIsChecked="Y";
	   var new2016CheckboxIsChecked="Y";
	   var cadre2014CheckboxIsChecked="Y";
	   var is2014Active="No";
	   var filterApplyType=$(this).attr("attr_filter_value");
	   if($("#2016RenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016RenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016NewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016NewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014CadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014CadreCheckBoxId").attr("attr_cadre_search_type");
		  is2014Active=$("#2014CadreCheckBoxId").attr("attr_2014_active");
	  }
	   var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
	  $("#apConstituencySelectBoxId").val(0);
      buildConstituecnyWiseCadreResult(globalApConstituencyWithDefaultSorting,"apConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType);
      globalApConstituencyRslt = globalApConstituencyWithDefaultSorting;
 }); 
 
 $(document).on("click",".ascendingTsSorting",function(){
	   var tsConsascendingOrderArr=[];
	   var renewal2016CheckboxIsChecked="Y"; 
	   var new2016CheckboxIsChecked="Y";
	   var cadre2014CheckboxIsChecked="Y";
	   var  is2014Active='No';
	   var filterApplyType=$(this).attr("attr_filter_value");  
	   $("#tsConstituencySelectBoxId").val(0);
	  if($("#2016TsRenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016TsRenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016TsNewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016TsNewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014TsCadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014TsCadreCheckBoxId").attr("attr_cadre_search_type");
		  is2014Active=$("#2014TsCadreCheckBoxId").attr("attr_2014_active");
	  }
	   var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
     buildConstituecnyWiseCadreResult(globalTsConstituencyWithAscendingArr,"tsConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType);
	 globalTsConstituencyRslt = globalTsConstituencyWithAscendingArr;
 });
  $(document).on("click",".decendingTsSorting",function(){
	   var renewal2016CheckboxIsChecked="Y";
	   var new2016CheckboxIsChecked="Y";
	   var cadre2014CheckboxIsChecked="Y";
	   var  is2014Active="No";
	   var filterApplyType=$(this).attr("attr_filter_value");
	   if($("#2016TsRenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016TsRenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016TsNewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016TsNewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014TsCadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014TsCadreCheckBoxId").attr("attr_cadre_search_type");
		  is2014Active=$("#2014TsCadreCheckBoxId").attr("attr_2014_active");
	  }
	   var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
	$("#tsConstituencySelectBoxId").val(0);
	buildConstituecnyWiseCadreResult(globalTsConstituencyWithDefaultSorting,"tsConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType);
	globalTsConstituencyRslt = globalTsConstituencyWithDefaultSorting;
 }); 
  $(document).on("click",".constituencyFilterCls",function(){
	   var filterApplyType = $(this).attr("attr_filter_value");
	   $("#apConstituencySelectBoxId").attr("attr_filter_value",filterApplyType);
	   $(".ascendingApSorting").attr("attr_filter_value",filterApplyType);
	   $(".decendingApSorting").attr("attr_filter_value",filterApplyType);
	   var renewal2016CheckboxIsChecked="Y";
	   var new2016CheckboxIsChecked="Y";
	   var cadre2014CheckboxIsChecked="Y";
	   var is2014Active='No';
	   if($("#2016RenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016RenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016NewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016NewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014CadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014CadreCheckBoxId").attr("attr_cadre_search_type");
		  is2014Active=$("#2014CadreCheckBoxId").attr("attr_2014_active");
	  }
	   var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
	 buildDropDown(globalApConstituencyRslt,"apConstituencySelectBoxId",filterApplyType);
     buildConstituecnyWiseCadreResult(globalApConstituencyRslt,"apConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType);
  });
    $(document).on("click",".tsConstituencyFilterCls",function(){
       var filterApplyType = $(this).attr("attr_filter_value");
	   $("#tsConstituencySelectBoxId").attr("attr_filter_value",filterApplyType);
	   $(".decendingTsSorting").attr("attr_filter_value",filterApplyType);
	   $(".ascendingTsSorting").attr("attr_filter_value",filterApplyType);
	   var renewal2016CheckboxIsChecked="Y";
	   var new2016CheckboxIsChecked="Y";
	   var cadre2014CheckboxIsChecked="Y";
	   var is2014Active='No';
	   if($("#2016TsRenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016TsRenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016TsNewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016TsNewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014TsCadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014TsCadreCheckBoxId").attr("attr_cadre_search_type");
		  is2014Active=$("#2014TsCadreCheckBoxId").attr("attr_2014_active");
	  }
	   var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
	 buildDropDown(globalTsConstituencyRslt,"tsConstituencySelectBoxId",filterApplyType);
	 buildConstituecnyWiseCadreResult(globalTsConstituencyRslt,"tsConstituencyRegistrationReportDivId",renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType);
  });
  
 function buildConstituecnyWiseCadreResult(result,divId,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,filterApplyType,is2014Active,sortingType){
	   $("#"+divId).html(' ');
    	var locationNameArr =[];
		var newCadreArr = [];
		var cadre2014ArrPer = [];
		var renewalArr=[];
		
		
		var newCadre2016CntArr = [];
		var renewalCadre2016CntArr = [];
		var cadre2014CntArr = [];
		if(result != null && result.length > 0){
			for(var i in result){
			   if((renewal2016CheckboxIsChecked=="Y" && new2016CheckboxIsChecked=="Y" && cadre2014CheckboxIsChecked=="Y")||(renewal2016CheckboxIsChecked=="2016Renewal" && new2016CheckboxIsChecked=="2016New" && cadre2014CheckboxIsChecked=="2014Cadre")){
					if(filterApplyType != null && filterApplyType=="No" || filterApplyType=="All"){
					locationNameArr.push(result[i].locationName);
					  if(sortingType == "2016CadreWise"){
						  cadre2014CntArr.push(result[i].total2014CadreCnt);
						  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
						  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
					 }else if(sortingType == "TargetWise"){
						 renewalArr.push(result[i].total2016RenewalCadrePer);
						 newCadreArr.push(result[i].total2016NewCadrePer);
						 cadre2014ArrPer.push(result[i].total2014CadrePer);	
					 }	 
					}else{
						if(filterApplyType=="verygood"){
							if(result[i].total2016CadrePer > 100){
								locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  cadre2014CntArr.push(result[i].total2014CadreCnt);
									  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									 renewalArr.push(result[i].total2016RenewalCadrePer);
									 newCadreArr.push(result[i].total2016NewCadrePer);
									 cadre2014ArrPer.push(result[i].total2014CadrePer);	
								 }	 
							}	
						}else if(filterApplyType=="good"){
						  if(result[i].total2016CadrePer > 90 && result[i].total2016CadrePer<=100){
								locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  cadre2014CntArr.push(result[i].total2014CadreCnt);
									  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									 renewalArr.push(result[i].total2016RenewalCadrePer);
									 newCadreArr.push(result[i].total2016NewCadrePer);
									 cadre2014ArrPer.push(result[i].total2014CadrePer);	
								 }	 
						  }
						}else if(filterApplyType=="ok"){
							  if(result[i].total2016CadrePer > 80 && result[i].total2016CadrePer<=90){
							      locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  cadre2014CntArr.push(result[i].total2014CadreCnt);
									  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									 renewalArr.push(result[i].total2016RenewalCadrePer);
									 newCadreArr.push(result[i].total2016NewCadrePer);
									 cadre2014ArrPer.push(result[i].total2014CadrePer);	
								 }	 
							  }
						}else if(filterApplyType=="poor"){
						  if(result[i].total2016CadrePer > 60 && result[i].total2016CadrePer <= 80){
							    locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  cadre2014CntArr.push(result[i].total2014CadreCnt);
									  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									 renewalArr.push(result[i].total2016RenewalCadrePer);
									 newCadreArr.push(result[i].total2016NewCadrePer);
									 cadre2014ArrPer.push(result[i].total2014CadrePer);	
								 }	 
						  }
						}else if(filterApplyType == "verypoor"){
							if(result[i].total2016CadrePer < 60){
							     locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  cadre2014CntArr.push(result[i].total2014CadreCnt);
									  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									 renewalArr.push(result[i].total2016RenewalCadrePer);
									 newCadreArr.push(result[i].total2016NewCadrePer);
									 cadre2014ArrPer.push(result[i].total2014CadrePer);	
								 }	 
							}
						}	
					}
				}else if(renewal2016CheckboxIsChecked=="2016Renewal" && new2016CheckboxIsChecked=="2016New"){
					if(filterApplyType != null && filterApplyType=="No" || filterApplyType=="All"){
							 locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							 }else if(sortingType == "TargetWise"){
								 renewalArr.push(result[i].total2016RenewalCadrePer);
								 newCadreArr.push(result[i].total2016NewCadrePer);
							 }	 
					}else{
				        if(filterApplyType=="verygood"){
							 if(result[i].total2016CadrePer > 100){
								locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									 renewalArr.push(result[i].total2016RenewalCadrePer);
									 newCadreArr.push(result[i].total2016NewCadrePer);
								 }	 
							 }	
						}else if(filterApplyType=="good"){
						  if(result[i].total2016CadrePer > 90 && result[i].total2016CadrePer<=100){
						       locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							 }else if(sortingType == "TargetWise"){
								 renewalArr.push(result[i].total2016RenewalCadrePer);
								 newCadreArr.push(result[i].total2016NewCadrePer);
							 }	 
						  }
						}else if(filterApplyType=="ok"){
							  if(result[i].total2016CadrePer > 80 && result[i].total2016CadrePer<=90){
							    locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							 }else if(sortingType == "TargetWise"){
								 renewalArr.push(result[i].total2016RenewalCadrePer);
								 newCadreArr.push(result[i].total2016NewCadrePer);
							 }	 
							  }
						}else if(filterApplyType=="poor"){
						  if(result[i].total2016CadrePer > 60 && result[i].total2016CadrePer <= 80){
							     locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							 }else if(sortingType == "TargetWise"){
								 renewalArr.push(result[i].total2016RenewalCadrePer);
								 newCadreArr.push(result[i].total2016NewCadrePer);
							 }	 
						  }
						}else if(filterApplyType == "verypoor"){
							if(result[i].total2016CadrePer < 60){
							    locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								  newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							 }else if(sortingType == "TargetWise"){
								 renewalArr.push(result[i].total2016RenewalCadrePer);
								 newCadreArr.push(result[i].total2016NewCadrePer);
							 }	 
							}
						}	
					}
				}else if(renewal2016CheckboxIsChecked=="2016Renewal" && cadre2014CheckboxIsChecked=="2014Cadre"){
					if(filterApplyType != null && filterApplyType=="No" || filterApplyType=="All" ){
					           locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								   cadre2014CntArr.push(result[i].total2014CadreCnt);
								   renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							 }else if(sortingType == "TargetWise"){
								  renewalArr.push(result[i].total2016RenewalCadrePer);
					              cadre2014ArrPer.push(result[i].total2014CadrePer);
							 }	 
					}else{
				        if(filterApplyType=="verygood"){
							 if(result[i].total2016CadrePer > 100){
								locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									  renewalArr.push(result[i].total2016RenewalCadrePer);
									  cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
							 }	
						}else if(filterApplyType=="good"){
						  if(result[i].total2016CadrePer > 90 && result[i].total2016CadrePer<=100){
						         locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									  renewalArr.push(result[i].total2016RenewalCadrePer);
									  cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
						  }
						}else if(filterApplyType=="ok"){
							  if(result[i].total2016CadrePer > 80 && result[i].total2016CadrePer<=90){
							       locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									  renewalArr.push(result[i].total2016RenewalCadrePer);
									  cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
							  }
						}else if(filterApplyType=="poor"){
						  if(result[i].total2016CadrePer > 60 && result[i].total2016CadrePer <= 80){
							      locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									  renewalArr.push(result[i].total2016RenewalCadrePer);
									  cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
						  }
						}else if(filterApplyType == "verypoor"){
							if(result[i].total2016CadrePer < 60){
							 locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									  renewalArr.push(result[i].total2016RenewalCadrePer);
									  cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
							}
						}	
					}
				}else if(new2016CheckboxIsChecked=="2016New" && cadre2014CheckboxIsChecked=="2014Cadre"){
				
					if(filterApplyType != null && filterApplyType=="No" || filterApplyType=="All" ){
					             locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								 }else if(sortingType == "TargetWise"){
									   newCadreArr.push(result[i].total2016NewCadrePer);
					                   cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
					}else{
						if(filterApplyType=="verygood"){
							 if(result[i].total2016CadrePer > 100){
								  locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								 }else if(sortingType == "TargetWise"){
									   newCadreArr.push(result[i].total2016NewCadrePer);
					                   cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
							 }	
						}else if(filterApplyType=="good"){
						  if(result[i].total2016CadrePer > 90 && result[i].total2016CadrePer<=100){
						      locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								 }else if(sortingType == "TargetWise"){
									   newCadreArr.push(result[i].total2016NewCadrePer);
					                   cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
						  }
						}else if(filterApplyType=="ok"){
							  if(result[i].total2016CadrePer > 80 && result[i].total2016CadrePer<=90){
							   locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								 }else if(sortingType == "TargetWise"){
									   newCadreArr.push(result[i].total2016NewCadrePer);
					                   cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
							  }
						}else if(filterApplyType=="poor"){
						  if(result[i].total2016CadrePer > 60 && result[i].total2016CadrePer <= 80){
							    locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								 }else if(sortingType == "TargetWise"){
									   newCadreArr.push(result[i].total2016NewCadrePer);
					                   cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
						  }
						}else if(filterApplyType == "verypoor"){
							if(result[i].total2016CadrePer < 60){
							     locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									   cadre2014CntArr.push(result[i].total2014CadreCnt);
									   newCadre2016CntArr.push(result[i].total2016NewCadreCount);
								 }else if(sortingType == "TargetWise"){
									   newCadreArr.push(result[i].total2016NewCadrePer);
					                   cadre2014ArrPer.push(result[i].total2014CadrePer);
								 }	 
							}
						}	
					}
				}else if(renewal2016CheckboxIsChecked=="2016Renewal"){
					if(filterApplyType != null && filterApplyType=="No" || filterApplyType=="All"){
						 locationNameArr.push(result[i].locationName);
						if(sortingType == "2016CadreWise"){
							  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
						 }else if(sortingType == "TargetWise"){
							  renewalArr.push(result[i].total2016RenewalCadrePer);
						 }	 
					}else{
						 if(filterApplyType=="verygood"){
							 if(result[i].total2016CadrePer > 100){
								 locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									  renewalArr.push(result[i].total2016RenewalCadrePer);
								 }	 
							 }	
						}else if(filterApplyType=="good"){
						  if(result[i].total2016CadrePer > 90 && result[i].total2016CadrePer<=100){
						        locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									  renewalArr.push(result[i].total2016RenewalCadrePer);
								 }	 
						  }
						}else if(filterApplyType=="ok"){
							  if(result[i].total2016CadrePer > 80 && result[i].total2016CadrePer<=90){
							          locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									  renewalArr.push(result[i].total2016RenewalCadrePer);
								 }	 
							  }
						}else if(filterApplyType=="poor"){
						  if(result[i].total2016CadrePer > 60 && result[i].total2016CadrePer <= 80){
							     locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									  renewalArr.push(result[i].total2016RenewalCadrePer);
								 }	 
						  }
						}else if(filterApplyType == "verypoor"){
							if(result[i].total2016CadrePer < 60){
							   locationNameArr.push(result[i].locationName);
								if(sortingType == "2016CadreWise"){
									  renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
								 }else if(sortingType == "TargetWise"){
									  renewalArr.push(result[i].total2016RenewalCadrePer);
								 }	 
							}
						}	
					}
				}else if(new2016CheckboxIsChecked=="2016New"){
					 if(filterApplyType != null && filterApplyType=="No" || filterApplyType=="All"){
							locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								    newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							 }else if(sortingType == "TargetWise"){
								   newCadreArr.push(result[i].total2016NewCadrePer);
							 }	 
					}else{
						 if(filterApplyType=="verygood"){
							 if(result[i].total2016CadrePer > 100){
								 locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								    newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							 }else if(sortingType == "TargetWise"){
								   newCadreArr.push(result[i].total2016NewCadrePer);
							 }	 
							 }	
						}else if(filterApplyType=="good"){
						  if(result[i].total2016CadrePer > 90 && result[i].total2016CadrePer<=100){
						     locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								    newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							 }else if(sortingType == "TargetWise"){
								   newCadreArr.push(result[i].total2016NewCadrePer);
							 }	 
					 
						  }
						}else if(filterApplyType=="ok"){
							  if(result[i].total2016CadrePer > 80 && result[i].total2016CadrePer<=90){
							   locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								    newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							 }else if(sortingType == "TargetWise"){
								   newCadreArr.push(result[i].total2016NewCadrePer);
							 }	 
					 
							  }
						}else if(filterApplyType=="poor"){
						  if(result[i].total2016CadrePer > 60 && result[i].total2016CadrePer <= 80){
							     locationNameArr.push(result[i].locationName);
							if(sortingType == "2016CadreWise"){
								    newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							 }else if(sortingType == "TargetWise"){
								   newCadreArr.push(result[i].total2016NewCadrePer);
							 }	 
					 
						  }
						}else if(filterApplyType == "verypoor"){
							if(result[i].total2016CadrePer < 60){
							  locationNameArr.push(result[i].locationName);
							  if(sortingType == "2016CadreWise"){
								    newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							  }else if(sortingType == "TargetWise"){
								   newCadreArr.push(result[i].total2016NewCadrePer);
							  }	 
					 
							}
						}	
					}
				}else if(cadre2014CheckboxIsChecked=="2014Cadre"){
					 if(filterApplyType != null && filterApplyType=="No" || filterApplyType=="All"){
					          locationNameArr.push(result[i].locationName);
							  if(sortingType == "2016CadreWise"){
								    cadre2014CntArr.push(result[i].total2014CadreCnt);
							  }else if(sortingType == "TargetWise"){
								   cadre2014ArrPer.push(result[i].total2014CadrePer);
							  }	 
					}else{
					   if(filterApplyType=="verygood"){
							 if(result[i].total2016CadrePer > 100){
								    locationNameArr.push(result[i].locationName);
								    if(sortingType == "2016CadreWise"){
								    cadre2014CntArr.push(result[i].total2014CadreCnt);
							       }else if(sortingType == "TargetWise"){
								   cadre2014ArrPer.push(result[i].total2014CadrePer);
							       }	 
							 }	
						}else if(filterApplyType=="good"){
						  if(result[i].total2016CadrePer > 90 && result[i].total2016CadrePer<=100){
						            locationNameArr.push(result[i].locationName);
								    if(sortingType == "2016CadreWise"){
								    cadre2014CntArr.push(result[i].total2014CadreCnt);
							       }else if(sortingType == "TargetWise"){
								   cadre2014ArrPer.push(result[i].total2014CadrePer);
							       }	 
						  }
						}else if(filterApplyType=="ok"){
							  if(result[i].total2016CadrePer > 80 && result[i].total2016CadrePer<=90){
							     locationNameArr.push(result[i].locationName);
								    if(sortingType == "2016CadreWise"){
								    cadre2014CntArr.push(result[i].total2014CadreCnt);
							       }else if(sortingType == "TargetWise"){
								   cadre2014ArrPer.push(result[i].total2014CadrePer);
							       }	 
							  }
						}else if(filterApplyType=="poor"){
						  if(result[i].total2016CadrePer > 60 && result[i].total2016CadrePer <= 80){
							      locationNameArr.push(result[i].locationName);
								    if(sortingType == "2016CadreWise"){
								    cadre2014CntArr.push(result[i].total2014CadreCnt);
							       }else if(sortingType == "TargetWise"){
								   cadre2014ArrPer.push(result[i].total2014CadrePer);
							       }	 
						  }
						}else if(filterApplyType == "verypoor"){
							if(result[i].total2016CadrePer < 60){
							    locationNameArr.push(result[i].locationName);
								    if(sortingType == "2016CadreWise"){
								    cadre2014CntArr.push(result[i].total2014CadreCnt);
							       }else if(sortingType == "TargetWise"){
								   cadre2014ArrPer.push(result[i].total2014CadrePer);
							       }	 
							}
						}	
					}
				}
			}
			 var jsonDataArr=[];
			 var colorArr=[];
			 if(sortingType == "2016CadreWise"){
				 if(renewalCadre2016CntArr.length > 0){
				  jsonDataArr.push({name: '2016 Renewal Cadre',data: renewalCadre2016CntArr,stack: '2016'});
				  colorArr.push('#30AA74');		  
				  }
				  if(newCadre2016CntArr.length > 0){
				  jsonDataArr.push({name: '2016 New Cadre',data: newCadre2016CntArr,stack: '2016'});
				  colorArr.push('#F36800');		  
				  }
				 if(is2014Active=="Yes"){
					  if(cadre2014CntArr.length > 0){
					  jsonDataArr.push({name: '2014 Cadre',data: cadre2014CntArr,stack: '2014'});
					  colorArr.push('#FFCA00');		  
				 }
				 }
			}else if(sortingType == "TargetWise"){ 
				 if(renewalArr.length > 0){
				  jsonDataArr.push({name: '2016 Renewal Cadre',data: renewalArr,stack: '2016'});
				  colorArr.push('#30AA74');		  
				  }
				  if(newCadreArr.length > 0){
				  jsonDataArr.push({name: '2016 New Cadre',data: newCadreArr,stack: '2016'});
				  colorArr.push('#F36800');		  
				  }
				  if(is2014Active=="Yes"){
				   if(cadre2014ArrPer.length > 0){
				   jsonDataArr.push({name: '2014 Cadre',data: cadre2014ArrPer,stack: '2014'});
				   colorArr.push('#FFCA00');		  
				   } 
				  }
			}
			 /*Setting Dynamic height for highChart */
			 if(locationNameArr!= null && locationNameArr.length > 10){
			  var highChartDivHight = locationNameArr.length*25;
			  $("#"+divId).height(highChartDivHight); 
             }else{
			  $("#"+divId).height(280);		
			  }
				$(function () {
					$("#"+divId).highcharts({
						colors: colorArr,
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
							categories: locationNameArr
						},
						yAxis: {
							min: 0,
							gridLineWidth: 0,
							minorGridLineWidth: 0,
							title: {
								text: null
							}
						},
						tooltip: {
				         formatter: function() {
								var series = this.point.series.chart.series, // get all series 
								index = this.point.series.xData.indexOf(this.point.x); // get index	
								var _locationName = this.x;
									var obj = result.filter(function ( obj ) {
										return obj.locationName === _locationName.toUpperCase();
									})[0];
								 var renewalPer = this.series.name=="2016 Renewal Cadre"? " - " + this.y + "%":'',
								  newPer = this.series.name=="2016 New Cadre"? " - " + this.y + "%":'',
								  cadre2014 = this.series.name=="2014 Cadre"?"<br/>"+this.series.name +": " +obj.total2014CadreCnt +" - "+obj.total2014CadrePer + "%":'';
								 
								 /* return '<b>'+this.x +'<br/>2016 Total Registrations:' + obj.total2016CadreCnt + '<br/>2016 New Cadre:' + obj.total2016NewCadreCount + newPer +'<br/>2016 Renewal Cadre:'+ obj.total2016RenewalCadreCount+renewalPer + cadre2014; */
							return '<b>'+this.x +'<br/>2016 Total Registrations:' + obj.total2016CadreCnt+" - "+obj.total2016CadrePer+ '%<br/>2016 New Cadre:' + obj.total2016NewCadreCount +" - "+obj.total2016NewCadrePer+'%<br/>2016 Renewal Cadre:'+ obj.total2016RenewalCadreCount+" - "+obj.total2016RenewalCadrePer+"%"+cadre2014; 

							}
						 },
						legend: {
							enabled: true,
							floating: false,
							verticalAlign: 'top',
							align: 'left'
						},

						plotOptions: {
							series: {
								stacking: 'normal'
							}
						},
						series:jsonDataArr 
					});
				});
				if(divId=="apConstituencyRegistrationReportDivId"){ /* applying scroll bar */
					if(locationNameArr.length > 10){
				       $("#apscrollBarDivId").mCustomScrollbar({setHeight:'560px'});	
			        }else{
				        $("#apscrollBarDivId").css({setHeight:'200px'});
			         }	 	
				}else if(divId=="tsConstituencyRegistrationReportDivId"){
					if(locationNameArr.length > 10){
				      $("#tsscrollBarDivId").mCustomScrollbar({setHeight:'560px'});	
			       }else{
				      $("#tsscrollBarDivId").css({setHeight:'200px'});
			       }	 	
			   }
		}else{
		  $("#"+divId).html("NO DATA AVAILABLE.");	
		}
 }  
  getApTsDistrictList();
  function getApTsDistrictList(){
	 	$.ajax({
			type : 'POST',
			url : 'getApTsDistrictListAction.action',
			dataType : 'json'
		}).done(function(result){
	       if(result != null ){
			 buildDistrictRslt(result);  
		   }
		});
 }
 function buildDistrictRslt(result){
	 var apDistrictList = result.subList1;
	 var tsDistrictList = result.subList2;
	 var str1='';
	 var str2='';
	 str1+='<ul class="cadreSelectD" id="apDistrictUlId">';
    if(apDistrictList != null && apDistrictList.length > 0){
		  for(var i in apDistrictList){
				 str1+='<li><label class="checkbox-inline"><input id="'+apDistrictList[i].locationId+'" type="checkbox" checked/>'+apDistrictList[i].locationName+'</label></li>';
		  }
	  }
	  str1+='</ul>';
	  
	  str2+='<ul class="cadreSelectD" id="tsDistrictULId">';
     if(tsDistrictList != null && tsDistrictList.length > 0){
		  for(var i in tsDistrictList){
			 	str2+='<li><label class="checkbox-inline"><input id="'+tsDistrictList[i].locationId+'" type="checkbox" checked/>'+tsDistrictList[i].locationName+'</label></li>';
		  }
	  }
	 str2+='<ul>';
	 $("#apDistrictId").html(str1);
	 $("#tsDistrictId").html(str2);
 }
 $(".closePopUpCls").click(function(){
	$(".cadreRDD").hide(); 
 });
 $(document).on("click",".selectAllApDistrict",function(){
   if($(this).is(":checked")){
	$("#apDistrictUlId li").each(function() {
	  $(this).find("input").prop("checked",true)
	});
   }else{
	 $("#apDistrictUlId li").each(function() {
	  $(this).find("input").prop("checked",false)
	});
   }	
});
$(document).on("click",".selectAllTsDistrict",function(){
   if($(this).is(":checked")){
	$("#tsDistrictULId li").each(function() {
	  $(this).find("input").prop("checked",true)
	});
   }else{
	 $("#tsDistrictULId li").each(function() {
	  $(this).find("input").prop("checked",false)
	});
   }	
});
  $(document).on("click","#settingsCadre",function(){
	$("#cadreRegSearchErrorId").html(' ');
	$("#cadreTsRegSearchErrorId").html(' ');
	$(".cadreRDD").hide();
	$(this).closest(".settingsBlockCadre").find(".cadreRDD").toggle();
	e.stopPropagation();
});
$(document).on("click","#getCadreRegistrationDetailsBtnId",function(){
	 
	  var renewal2016CheckboxIsChecked="N";
	  var new2016CheckboxIsChecked="N";
	  var cadre2014CheckboxIsChecked="N";
	  var is2014Active="No";
	  var isKuppamExcluded="True";
	  $('#kuppamConstituencyCheckBoxId').prop('checked', true);
	 
	  if($("#2016RenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016RenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016NewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016NewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014CadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014CadreCheckBoxId").attr("attr_cadre_search_type");
		  is2014Active=$("#2014CadreCheckBoxId").attr("attr_2014_active");
	  }
	  if(renewal2016CheckboxIsChecked=="N" && new2016CheckboxIsChecked=="N" && cadre2014CheckboxIsChecked=="N"){
		  $("#cadreRegSearchErrorId").html("Please Select Search Type");
		  return;
	  }
	  var apDistrictArr=[];
	  $("#apDistrictUlId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  apDistrictArr.push($(this).find("input").attr("id"));
		  }
	   });
	   if(apDistrictArr.length == 0){
		 $("#cadreRegSearchErrorId").html("Please Select District.");
		   return;
	   }
	    $("#cadreRegSearchErrorId").html(' ');
	   	$(".apConstituencyUlCls li").removeClass("active");
		$(".apConstituencyUlCls li:first-child").addClass("active");
	    $("#apConstituencySelectBoxId").attr("attr_filter_value","All");
	    $("#tsConstituencySelectBoxId").attr("attr_filter_value","All");
	    $(".ascendingApSorting").attr("attr_filter_value","All");
	    $(".decendingApSorting").attr("attr_filter_value","All");
	   if(apDistrictArr.length > 0){
		getApConstituencyCadreRegistrationDetails(3,apDistrictArr,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active,isKuppamExcluded);  
	   }else{
		   $("#apConstituencyRegistrationReportDivId").html(' ');
		   globalApConstituencyRslt="";  
	   }
	   $(".cadreRDD").hide();
});  
$(document).on("click","#getTsCadreRegistrationDetailsBtnId",function(){
	 
	  var renewal2016CheckboxIsChecked="N";
	  var new2016CheckboxIsChecked="N";
	  var cadre2014CheckboxIsChecked="N";
	  var is2014Active="No";
	 
	  if($("#2016TsRenewalCheckBoxId").is(':checked')){
		  renewal2016CheckboxIsChecked=$("#2016TsRenewalCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2016TsNewCheckBoxId").is(':checked')){
		  new2016CheckboxIsChecked=$("#2016TsNewCheckBoxId").attr("attr_cadre_search_type");
	  }
	  if($("#2014TsCadreCheckBoxId").is(':checked')){
		  cadre2014CheckboxIsChecked=$("#2014TsCadreCheckBoxId").attr("attr_cadre_search_type");
		  is2014Active=$("#2014TsCadreCheckBoxId").attr("attr_2014_active");
	  }
	  if(renewal2016CheckboxIsChecked=="N" && new2016CheckboxIsChecked=="N" && cadre2014CheckboxIsChecked=="N"){
		  $("#cadreTsRegSearchErrorId").html("Please Select Search Type");
		  return;
	  }
	   var tsDistrictArr=[];
	    $("#tsDistrictULId li").each(function() {
		  if($(this).find("input").is(":checked")){
			  tsDistrictArr.push($(this).find("input").attr("id"));
		  }
	   });
	   if(tsDistrictArr.length==0 ){
		 $("#cadreTsRegSearchErrorId").html("Please Select District.");
		   return;
	   }
	    $("#cadreTsRegSearchErrorId").html(' ');
	   	$(".tsConstituencyUlCls li").removeClass("active");
		$(".tsConstituencyUlCls li:first-child").addClass("active");
	    $("#tsConstituencySelectBoxId").attr("attr_filter_value","All");
	    $(".decendingTsSorting").attr("attr_filter_value","All");
	    $(".ascendingTsSorting").attr("attr_filter_value","All");
	   if(tsDistrictArr.length > 0){
		  getTsConstituencyCadreRegistrationDetails(3,tsDistrictArr,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active);
	   }else{
		    $("#tsConstituencyRegistrationReportDivId").html(' ');
		    globalTsConstituencyRslt=""; 
	   }
	   $(".cadreRDD").hide();
});  

	function getSourceOfRegistrationDtls(globalActivityMemberId){
		
		$("#sourceTypeId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var startDate = '';    
		var endDate = '';    
		var jsObj={  
			activityMemberId : globalActivityMemberId,
			stateId : globalStateId,         
			startDate : '02/10/2016',      
			endDate : getTodayDate()
		};
		$.ajax({          
			type : 'GET',            
			url : 'getSourceOfRegistrationDtls.action',    
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){        
			 $("#sourceTypeId").html('');  
			 if(result != null && result.length > 0){
				buildSourceOfRegistrationDtls(result,1);  
			 }  
		});
	}
	function getSourceOfRegistrationTSDtls(globalActivityMemberId){
		
		$("#sourceTypeTsId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var startDate = '';    
		var endDate = '';    
		var jsObj={  
			activityMemberId : globalActivityMemberId,
			stateId : 36,         
			startDate : '02/10/2016',      
			endDate : getTodayDate()
		};
		$.ajax({          
			type : 'GET',            
			url : 'getSourceOfRegistrationDtls.action',    
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){        
			 $("#sourceTypeTsId").html('');  
			 if(result != null && result.length > 0){
				buildSourceOfRegistrationDtls(result,36);  
			 }  
		});
	}
//for dynamic calls  	
	 function buildSourceOfRegistrationDtls(result,globalStateId){  
		 var str = '';
		 var totalCount = 0;
		 var newCount = 0;
		 var renewalCount = 0;
		 for(var i in result){
			 totalCount = totalCount + result[i].totalCount;  
			 newCount = newCount + result[i].newCount;
			 renewalCount = renewalCount + result[i].renewalCount;
		 }
		 var colorArr = ["color:#1770C4","color:#55E1DA","color:#CDA737","color:#540B88"];
		 str+='<div class="col-md-12 col-xs-12 col-sm-6">';
			 if(globalStateId == 1){           
				str+='<h4 class="bg_ED pad_15 text-capitalize text-center">Andhra Pradesh</h4>';
			}else{
				str+='<h4 class="bg_ED pad_15 text-capitalize text-center">Telangana States</h4>';  
			}
			
			str+='<div class="col-md-4 col-xs-12 col-sm-4 pad_right0" >';
				str+='<div class="pad_5">';
					str+='<h5 class="text-capital">total</h5>';	
					str+='<h3>'+totalCount+'</h3>';
					str+='<hr style="border-color:#CCC;"></hr>';
					str+='<table class="table">';
					for(var i in result){
						str+='<tr style="'+colorArr[i]+'">';
							str+='<td>'+result[i].sourceName+'</td>';
							str+='<td>'+result[i].totalCount+'</td>';
							var percent = (result[i].totalCount * (100/totalCount)).toFixed(2);   
							str+='<td><small class="text-muted">'+percent+'%</small></td>';
						str+='</tr>';
					}
					str+='</table>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-md-4 col-xs-12 col-sm-4 pad_right0 pad_left0" >';
				str+='<div class="pad_5">';
					str+='<h5 class="text-capital">renewal</h5>';	
					str+='<h3>'+renewalCount+'</h3>';
					str+='<hr style="border-color:#CCC;"></hr>';
					str+='<table class="table">';
					for(var i in result){
						str+='<tr style="'+colorArr[i]+'">';
							str+='<td>'+result[i].sourceName+'</td>';
							str+='<td>'+result[i].renewalCount+'</td>';
							var percent = (result[i].renewalCount * (100/renewalCount)).toFixed(2);
							str+='<td><small class="text-muted">'+percent+'%</small></td>';
						str+='</tr>';
					}	
					str+='</table>';
				str+='</div>';
			str+='</div>';      
			str+='<div class="col-md-4 col-xs-12 col-sm-4 pad_left0" >';    
				str+='<div class="pad_5">';
					str+='<h5 class="text-capital">new</h5>';
					str+='<h3>'+newCount+'</h3>';
					str+='<hr style="border-color:#CCC;"></hr>';
					str+='<table class="table">';
					for(var i in result){
						str+='<tr style="'+colorArr[i]+'">';
							str+='<td>'+result[i].sourceName+'</td>';
							str+='<td>'+result[i].newCount+'</td>';     
							var percent = (result[i].newCount * (100/newCount)).toFixed(2);    
							str+='<td><small class="text-muted">'+percent+'%</small></td>';          
						str+='</tr>';
					}	
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		if(globalStateId == 1){           
			$("#sourceTypeId").html(str);  
		}else{
			$("#sourceTypeTsId").html(str);
		}
		      
	 }
	function getAllItsSubUserTypeIdsByParentUserTypeIdForCadreRegistration(globalUserTypeId){  
		$("#designationListId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#childMembersId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#directChildId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		//$("#enumeratorsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');        
		$("#individualDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
		//$("#voterDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');  
		var jsObj = {parentUserTypeId : globalUserTypeId}
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#designationListId").html('');    
			if(result != null && result.length > 0){
			buildgetChildUserTypesByItsParentUserTypeForCadreRegistration(result);	
			}else{
			}
		});		
	}
	//swadhin
	function buildgetChildUserTypesByItsParentUserTypeForCadreRegistration(result){
		
		var firstChildUserTypeIdString = result[0].shortName;
		var str = '';
		if(result != null && result.length > 0){
			str += '<ul class="comparisonSelect">';
			for(var i in result){
				str += '<li class="childMemCls" id="desigPosition'+i+'" attr_userTypeId="'+result[i].shortName+'" attr_userType="'+result[i].userType+'">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			}
			str += '</ul>';    
		}
		$("#designationListId").html(str); 
		//$("#desigPosition0").trigger("click");   
		$(".comparisonSelect li:first-child").addClass("active");
		getSelectedChildTypeMembersForCadreRegistration(firstChildUserTypeIdString);
	}
	$(document).on("click","#desigPosition0",function(){
		firstChildUserTypeIdString = $(this).attr("attr_userTypeId");
		getSelectedChildTypeMembersForCadreRegistration(firstChildUserTypeIdString);            
	});
	$(document).on('click','.childMemCls',function(){      
		var firstChildUserTypeIdString = $(this).attr("attr_userTypeId");
		$("#childMembersId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#directChildId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		//$("#enumeratorsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');        
		$("#individualDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
		//$("#voterDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$(".headingColor1").hide();          
		getSelectedChildTypeMembersForCadreRegistration(firstChildUserTypeIdString);      
	});
	function getSelectedChildTypeMembersForCadreRegistration(firstChildUserTypeIdString){  
		$("#childMembersId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var childUserTypeIdsArray = [];
		childUserTypeIdsArray = firstChildUserTypeIdString.split(",");        
		 var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});
		var jsObj={  
			parentActivityMemberId : globalActivityMemberId,        
			childUserTypeIdsArray : childUserTypeIdsArray,
			stateId : globalStateId,
			fromDateStr : '02/10/2016',
			toDateStr : getTodayDate(),
			sortingType : sortingType
		};
		$.ajax({                   
			type : 'GET',    
			url : 'getSelectedChildTypeMembersForCadreRegistration.action',    
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#childMembersId").html('');      
			 if(result != null){
				buildChildMembers(result);         
			 }
		});
	} 
	
	function buildChildMembers(result){
		var attrActivityMemberId=result[0].activityMemberId;
		var attrUserTypeId=result[0].userTypeId;
		var selectedMemberName = result[0].name;
		var selectedUserType = result[0].userType;
		var str = '';
		str += '<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<ul class="list-inline slickPanelSliderCadre">';
			var k = 0;
			for(var i in result){
				k = parseInt(k) + 1;     
			str+='<li style="cursor:pointer;" style="width:380px !important;">';
			  if(i==0){
			   str+='<div class="panel panel-default panelSlick panelActiveSlick">';
		      }else{
		     str+='<div class="panel panel-default panelSlick">';
		     }
				str += '<div class="panel-heading">';
					str += '<h4 class="panel-title">'+result[i].name+'</h4><span class="count">'+(k)+'</span>';     
				str += '</div>';
				str += '<div class="panel-body directChildCls" attr_name="'+result[i].name+'" attr_desig="'+result[i].userType+'" id="directChildId'+i+'" attr_activity_member_id="'+result[i].activityMemberId+'" attr_user_type_id="'+result[i].userTypeId+'">';
				  var yourValues = result[i].locationName;
					 if(yourValues != null){
						  if(yourValues.indexOf(',') == -1){
							  var locatinName = yourValues.substring(0, yourValues.lastIndexOf(" "));
							   str += '<h4 class="text-capital">'+result[i].userType+' (<b>'+locatinName+'</b>)</h4>';
							}else{
							    str += '<h4 class="text-capital">'+result[i].userType+'</h4>';
							}
					 }else{
						    str += '<h4 class="text-capital">'+result[i].userType+'</h4>';
					 }
				 	str += '<table class="table table-condensed">';
						str += '<thead>';
							str += '<tr>';   
								str += '<th>2014-16</th>';  
								str += '<th >2016-18</th>';
								str += '<th >%</th>';
							str += '</tr>';
						str += '</thead>';
						str += '<tbody>';
							str += '<tr>';
								str += '<td>'+result[i].totalTargetCount2014+'</td>';
								str += '<td>'+result[i].totalCadreCount+'(Today-'+result[i].totalCadreCountToday+')</td>';
								str += '<td>'+result[i].totalCadreCountPer+'%</td>';
							str += '</tr>';
						str += '</tbody>';
					str += '</table>';
					/* if(result[i].subLocationList.length > 0){
						str += '<h4 class="text-capital">'+result[i].locationLevelName+' Wise Registrations</h4>';    
						str += '<table class="table table-condensed">';  
							str += '<tbody>';
							for(var j in result[i].subLocationList){
								str += '<tr>';
									str += '<td>'+result[i].subLocationList[j].name+'</td>';
									str += '<td>'+result[i].subLocationList[j].count+'(Today-'+result[i].subLocationList[j].wishCount+')</td>';
								str += '</tr>';
							}
							str += '</tbody>';  
						str += '</table>';    
					}     */
				str += '</div>';
			str += '</div>';
			str += '</li>';
			}
			
		str += '</ul>';
		str += '</div>';
		$("#childMembersId").html(str);  
		//$("#directChildId0").trigger("click"); 
		getDirectChildMembers(selectedMemberName,selectedUserType,attrActivityMemberId,attrUserTypeId,"directChildId");
		//change
		//getEnumerationDtlsForMem(attrActivityMemberId);
		if(attrUserTypeId == 3 || attrUserTypeId == 5){
			 $("#individualDtlsId").show();
			 getGSAndDPDtls(attrActivityMemberId,selectedMemberName,selectedUserType);
		}else{
			$("#individualDtlsId").html("");
			$("#individualDtlsId").hide();
		}
		getDtlsOfBellowLvlMember(attrActivityMemberId,selectedMemberName,selectedUserType);
		//getVoterInfo(attrActivityMemberId);        
		  
		$("#childActivityMemberDivIdForMeeting").html(str);
			$(".slickPanelSliderCadre").slick({
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
	} 
	$(document).on('click','.directChildCls',function(){
		var activityMemberId = $(this).attr("attr_activity_member_id");
		var userTypeId = $(this).attr("attr_user_type_id");
		var selectedMemberName = $(this).attr("attr_name");
		var selectedUserType = $(this).attr("attr_desig");
  	  	$(".slickPanelSliderCadre").find("li").find(".panelSlick").removeClass("panelActiveSlick");
		$(this).parent().parent().find(".panelSlick").addClass("panelActiveSlick");
		//changed 
		if(userTypeId == 3 || userTypeId == 5){ 
			$("#individualDtlsId").show();				
			getGSAndDPDtls(activityMemberId,selectedMemberName,selectedUserType);
		}else{
			$("#individualDtlsId").html(" "); 
            $("#individualDtlsId").hide();			
		}
		//getEnumerationDtlsForMem(activityMemberId);      
		//getVoterInfo(activityMemberId);
		getDirectChildMembers(selectedMemberName,selectedUserType,activityMemberId,userTypeId,"directChildId");
		getDtlsOfBellowLvlMember(activityMemberId,selectedMemberName,selectedUserType);  
	});
	function getDirectChildMembers(selectedMemberName,selectedUserType,ActivityMemberId,userTypeId,divId){
		$("#accessMemberId").show();    
		$("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		 if(userTypeId == 3 || userTypeId == 5){    
			$("#individualDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
		}else{
			$("#individualDtlsId").hide();
		}
		//$("#enumeratorsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
	 	//$("#voterDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
		$(".headingColor1").hide();
		var userTypeIdArr = [];
		userTypeIdArr.push(userTypeId);
		var sortingType = '';
		$(".selectOneSpecialCadre").each(function() {
			if($(this).is(":checked")){  
				sortingType = $(this).attr("attr_sort_type");
			}  
		});        
		var jsObj ={ parentActivityMemberId : ActivityMemberId,        
			         childUserTypeIdsArray : userTypeIdArr,    
					 stateId : globalStateId,
					 fromDateStr : '02/10/2016',
					 toDateStr : getTodayDate(),
					 sortingType : sortingType  
				  }       
	   	$.ajax({  
			type : 'POST',   
			url : 'getSelectedChildTypeMembersForCadreReg.action',    
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildDirectChildMembers(result,divId,selectedMemberName,selectedUserType);
			}else{
				$("#"+divId).html('');
				//$("#enumeratorsId").html('');        
				$("#individualDtlsId").html(''); 
				//$("#voterDtlsId").html('');       
				$("#accessMemberId").hide();         
			}
		});
	}
	
	function buildDirectChildMembers(result,divId,selectedMemberName,selectedUserType){    
		var activityMemberId = result[0].activityMemberId;  
		var str = '';
		 str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					if($(window).width < 768)
					{
						str+='<div class="table-responsive">';
					}
			         str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
					 if(divId != "directChildId"){
							str+='<span class="remveInnerTblCls pull-right" attr_removeSelecUserType = "'+divId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
					 } 
					 str+='<div class="table-responsive">';
					 if(divId != "directChildId"){
						 str+='<table  class="table table-condensed tableHoverLevelsInner m_top20">';
					 }else{
						str+='<table class="table table-condensed tableHoverLevels m_top20">';  
					 }
						str+='<thead>';
						str+='<th>RANK</th>';
						str+='<th>DESIGNATION</th>';
						str+='<th class="text-capital">NAME</th>';      
						str+='<th>TOTAL</th>';
						str+='<th>TODAY</th>';
						str+='<th>%</th>';
					str+='</thead>';
					str+='<tbody>';
						var k = 0;
						for(var i in result){
							if(result[i].totalCadreCount > 0){         
								str+='<tr id="belowLvlMemId'+i+'"  class="bellowLvlCls"    attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'"  attr_selectedusertype = "'+result[i].userType+'" attr_activity_member_id="'+result[i].activityMemberId+'">'; 
									k = parseInt(k) + 1;      
									str+='<td>';
										str+='<span class="tableCount">'+k+'</span>';
									str+='</td>';
								     var yourValues = result[i].locationName;
									 if(yourValues != null){
										  if(yourValues.indexOf(',') == -1){
											  var locatinName = yourValues.substring(0, yourValues.lastIndexOf(" "));
											 str+='<td>'+result[i].userType+' (<b>'+locatinName+'</b>)</td>';
											}else{
											  str+='<td>'+result[i].userType+'</td>';
											}
									 }else{
									     str+='<td>'+result[i].userType+'</td>';	 
									 }
									str+='<td>'+result[i].name+'</td>';
									str+='<td>'+result[i].totalCadreCount+'</td>';
									str+='<td>'+result[i].totalCadreCountToday+'</td>';
									str+='<td>'+result[i].totalCadreCountPer+'</td>';
									str+='<tr class="showHideTr" style="display:none" attr_id = "subLevelMembersdivId'+result[i].userTypeId+''+i+'">';
									str+='<td colspan="8"  id="subLevelMembersdivId'+result[i].userTypeId+''+i+'">';
								str+='</tr>';   
							}
						}  
					str+='</tbody>';
				str+='</table>';  
			str+='</div>'
			if($(window).width < 768)
			{
				str+='</div>';
			}
		str+='</div>';
		$("#"+divId).html(str);
		//$("#belowLvlMemId0").trigger("click");
		//$("#enumeratorsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');        
		//$("#individualDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
		//$("#voterDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		//$(".headingColor").hide();
		/* if(k == 0){
			$("#directChildId").html('');
			$("#enumeratorsId").html('');        
			$("#individualDtlsId").html('');   
			$("#voterDtlsId").html('');
			$(".headingColor").hide();
			$("#accessMemberId").hide();  
			
		}else{
			$("#enumeratorsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');        
			$("#individualDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
			$("#voterDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			$(".headingColor").hide();
			$("#accessMemberId").show();              
			getEnumerationDtlsForMem(activityMemberId);    
			getVoterInfo(activityMemberId);    
		}  */  
		          
			   
	}  
	$(document).on('click','.bellowLvlCls',function(){
		//$("#enumeratorsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');        
		$("#individualDtlsId").html(''); 
		//$("#voterDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$(".headingColor1").hide();
		$(this).next('tr.showHideTr').show(); 
		var activityMemberId = $(this).attr("attr_activity_member_id");
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
		 getDirectChildMembers(selectedMemberName,selectedUserType,activityMemberId,userTypeId,childActivityMemberId);
		//getEnumerationDtlsForMem(activityMemberId);   
		//getVoterInfo(activityMemberId);
		getDtlsOfBellowLvlMember(activityMemberId,selectedMemberName,selectedUserType);
	});
	
	$(document).on("click",".remveInnerTblCls",function(){
		 var removeSelected = $(this).attr("attr_removeSelecUserType"); 
		 $("#"+removeSelected).html(' ');
		 $("#"+removeSelected).closest('.showHideTr').hide();
	});
	function getEnumerationDtlsForMem(globalActivityMemberId){
		
		var startDate = '';      
		var endDate = '';    
		var jsObj={  
			activityMemberId : globalActivityMemberId,
			stateId : globalStateId,         
			startDate : '02/10/2016',         
			endDate : getTodayDate()
		};
		$.ajax({          
			type : 'GET',       
			url : 'getEnumerationDtlsForMem.action',      
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){                
			
			 if(result != null){
				buildEnumerationDtlsForMem(result);
				
			 }else{  
				 $("#enumeratorsId").html("No Data Available");   
			 }
		});
	}
	function buildEnumerationDtlsForMem(result){
		var str = '';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<span class="headingColor">ENUMERATORS INFO</span>';  
		str+='</div>';
		str+='<div class="col-md-3 col-xs-12 col-sm-3 text-capital">';
			str+='<h3>'+result.todayFieldMembersCount+'</h3>';
			str+='<p>LOGGED IN TODAY</p>';
		str+='</div>';
		str+='<div class="col-md-3 col-xs-12 col-sm-3 text-capital">';
			str+='<h3>'+result.inField+'</h3>';
			str+='<p>IN FIELD NOW</p>';
		str+='</div>';
		str+='<div class="col-md-3 col-xs-12 col-sm-3 text-capital">';
			str+='<h3>'+result.todayTotalCount+'</h3>';
			str+='<p>TODAY SUBMITTED DATA</p>';
		str+='</div>'; 
		$("#enumeratorsId").html(str);    
	}
	$(document).on("click",".consFilterCls",function(){
     var filterApplyType = $(this).attr("attr_filter_value");
	 var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
      buildDtlsOfBellowLvlMember(globalSubLevelRslt,"individualDtls",filterApplyType,sortingType);
  });
	function getDtlsOfBellowLvlMember(globalActivityMemberId,selectedMemberName,selectedUserType){
		$("#individualDtls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
		$(".hideConReport").show();
		$("#constituencyReportHeadingId").html("CONSTITUENCY WISE REGISTRATIONS<b>("+selectedMemberName+" - "+selectedUserType+")</b>");
	    $(".usrWseCnsttuncyUlCls li").removeClass("active");
		$(".usrWseCnsttuncyUlCls li:first-child").addClass("active");
		 
		 $("#usrWseCnsttuncyAllCntId").html("All-0");
		 $("#usrWseCnsttuncyVryGdCntId").html("Very Good-0");
		 $("#usrWseCnsttuncyGdCntId").html("Good-0");
		 $("#usrWseCnsttuncyOkCntId").html("Ok-0");
		 $("#usrWseCnsttuncyPoorCntId").html("Poor-0");
		 $("#usrWseCnsttuncyVryPoorCntId").html("Very Poor-0");
		 
		var startDate = '';    
		var endDate = '';  
        var flterApplyType="No";	
		var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
		var jsObj={  
			activityMemberId : globalActivityMemberId,
			stateId : globalStateId,         
			startDate : '02/10/2016',         
			endDate : getTodayDate(),
			sortingType:sortingType
		};
		$.ajax({           
			type : 'GET',       
			url : 'getConstituencyWiseReportBasedOnUserTypeAction.action',    
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){        
		   buildDtlsOfBellowLvlMember(result,"individualDtls",flterApplyType,sortingType);
		   globalSubLevelRslt = result;
		   if(result != null && result.length > 0){
			   var overAllCntRlst = result[0]; 
			     $("#usrWseCnsttuncyAllCntId").html("All-"+(overAllCntRlst.allConstituencyCnt));
				 $("#usrWseCnsttuncyVryGdCntId").html("Very Good-"+(overAllCntRlst.veryGoodCnt));
				 $("#usrWseCnsttuncyGdCntId").html("Good-"+(overAllCntRlst.goodCnt));
				 $("#usrWseCnsttuncyOkCntId").html("Ok-"+(overAllCntRlst.okCnt));
				 $("#usrWseCnsttuncyPoorCntId").html("Poor-"+(overAllCntRlst.poorCnt));
				 $("#usrWseCnsttuncyVryPoorCntId").html("Very Poor-"+(overAllCntRlst.veryPoorCnt));
		   }
		});
	}  
	function buildDtlsOfBellowLvlMember(result,divId,filterApplyType,sortingType){
		var locationNameArr = [];
		var renewalArr = [];
		var newCadreArr = []; 
		var cadre2014ArrPer = [];
		var newCadre2016CntArr = [];
		var renewalCadre2016CntArr = [];
		var cadre2014CntArr = [];
		var colorArr=[];
		var jsonDataArr=[];
		if(result != null && result.length > 0){
		  for(var i in result){
				if(filterApplyType != null && filterApplyType=="All" || filterApplyType =="No"){
						locationNameArr.push(result[i].locationName);
						if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
						}else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
						}
				 }else if(filterApplyType=="verygood"){
				 if(result[i].total2016CadrePer > 100){
					  locationNameArr.push(result[i].locationName);
					  if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
						}else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
						}
				 }	
				}else if(filterApplyType=="good"){
				  if(result[i].total2016CadrePer > 90 && result[i].total2016CadrePer<=100){
					   locationNameArr.push(result[i].locationName);
					  if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
					  }else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
					  }
				  }
				}else if(filterApplyType=="ok"){
					if(result[i].total2016CadrePer > 80 && result[i].total2016CadrePer<=90){
					   locationNameArr.push(result[i].locationName);
				       if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
						}else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
						}
				    }
				}else if(filterApplyType=="poor"){
				  if(result[i].total2016CadrePer > 60 && result[i].total2016CadrePer <= 80){
					 locationNameArr.push(result[i].locationName);
					   if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
						}else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
						}
				  }
				}else if(filterApplyType == "verypoor"){
					if(result[i].total2016CadrePer < 60){
				      locationNameArr.push(result[i].locationName);
				      if(sortingType == "2016CadreWise"){
							if(result[i].total2014CadreCnt > 0){
							  cadre2014CntArr.push(result[i].total2014CadreCnt);
							}
							if(result[i].total2016NewCadreCount > 0){
							 newCadre2016CntArr.push(result[i].total2016NewCadreCount);
							}
							if(result[i].total2016RenewalCadreCount > 0){
							 renewalCadre2016CntArr.push(result[i].total2016RenewalCadreCount);
							}
						}else if(sortingType == "TargetWise"){
							if(result[i].total2014CadrePer > 0){
							  cadre2014ArrPer.push(result[i].total2014CadrePer);
							}
							if(result[i].total2016RenewalCadrePer > 0){
							 renewalArr.push(result[i].total2016RenewalCadrePer);
							}
							if(result[i].total2016NewCadrePer > 0){
							 newCadreArr.push(result[i].total2016NewCadrePer);
							}
						}
					}
				}
			}
			if(sortingType == "2016CadreWise"){
				 if(renewalCadre2016CntArr.length > 0){
				  jsonDataArr.push({name: '2016 Renewal Cadre',data: renewalCadre2016CntArr,stack: '2016'});
				  colorArr.push('#30AA74');		  
				  }
				  if(newCadre2016CntArr.length > 0){
				  jsonDataArr.push({name: '2016 New Cadre',data: newCadre2016CntArr,stack: '2016'});
				  colorArr.push('#F36800');		  
				  }
				  if(cadre2014CntArr.length > 0){
				  jsonDataArr.push({name: '2014 Cadre',data: cadre2014CntArr,stack: '2014'});
				  colorArr.push('#FFCA00');		  
				  }
			}else if(sortingType == "TargetWise"){ 
				 if(renewalArr.length > 0){
				  jsonDataArr.push({name: '2016 Renewal Cadre',data: renewalArr,stack: '2016'});
				  colorArr.push('#30AA74');		  
				  }
				  if(newCadreArr.length > 0){
				  jsonDataArr.push({name: '2016 New Cadre',data: newCadreArr,stack: '2016'});
				  colorArr.push('#F36800');		  
				  }
				  if(cadre2014ArrPer.length > 0){
				  jsonDataArr.push({name: '2014 Cadre',data: cadre2014ArrPer,stack: '2014'});
				  colorArr.push('#FFCA00');		  
				  }
			}
			
			if(result!= null && result.length > 10){
			var highChartDivHight = result.length*25;
			$("#"+divId).height(highChartDivHight);  
			}else{
			$("#"+divId).height(280);    
			}
			$(function () {
			  $("#"+divId).highcharts({
				colors: colorArr,
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
				  categories: locationNameArr
				},
				yAxis: {
				  min: 0,
				  gridLineWidth: 0,
				  minorGridLineWidth: 0,
				  title: {
					text: null
				  }
				},
				tooltip: {
				 formatter: function() {
						var series = this.point.series.chart.series, // get all series 
						index = this.point.series.xData.indexOf(this.point.x); // get index	
						var _locationName = this.x;
							var obj = result.filter(function ( obj ) {
								return obj.locationName === _locationName.toUpperCase();
							})[0];
						 var renewalPer = this.series.name=="2016 Renewal Cadre"? " - " + this.y + "%":'',
						  newPer = this.series.name=="2016 New Cadre"? " - " + this.y + "%":'',
						  cadre2014 = this.series.name=="2014 Cadre"?"<br/>"+this.series.name +": " +obj.total2014CadreCnt +" - "+obj.total2014CadrePer + "%":'';
						  return '<b>'+this.x +'<br/>2016 Total Registrations:' + obj.total2016CadreCnt+" - "+obj.total2016CadrePer+ '%<br/>2016 New Cadre:' + obj.total2016NewCadreCount +" - "+obj.total2016NewCadrePer+'%<br/>2016 Renewal Cadre:'+ obj.total2016RenewalCadreCount+" - "+obj.total2016RenewalCadrePer+"%" +cadre2014; 
					}
				},
				legend: {
				  reversed: true
				},
				plotOptions: {
				  series: {
					stacking: 'normal'
				  }
				},
				series:jsonDataArr 
			  });
			});
		}else{
		 $("#"+divId).html("NO DATA AVAILABLE");	
		}
		$("#"+divId).find(".highcharts-legend-item:nth-child(1)").trigger("click");
	}
	function getVoterInfo(activityMemberId){          
		var startDate = '';    
		var endDate = '';    
		var jsObj={  
			activityMemberId : activityMemberId,    
			stateId : globalStateId,         
			startDate : '02/10/2016',         
			endDate : getTodayDate()
		};
		$.ajax({          
			type : 'GET',       
			url : 'getVoterInfo.action',    
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){        
			
			 if(result != null){
				 buildVoterInfo(result);
			 }else{
				 $("#voterDtlsId").html("No Data Available")
			 } 
		});
	}
	function buildVoterInfo(result){   
		var ownVoter = result.id;
		var familyVoter = result.attenteeCount;
		var total = ownVoter + familyVoter; 
		var ownPercent = ownVoter * (100/total);
		var familyPercent = familyVoter * (100/total);
		var meetingLevelArr =[];
		var ownVoterArr = [];
		var familyVoterArr = [];
		var mayBeMeetingArr = [];
			
		meetingLevelArr.push(" ");
		ownVoterArr.push(ownPercent);
		familyVoterArr.push(familyPercent);  
		$(function () {
			$("#voterDtlsId").height(260);      
			$("#voterDtlsId").highcharts({
				colors: ['#53BF8B','#F56800','#66728C'],
				chart: {
					type: 'column',
					backgroundColor:'transparent'
				},
				title: {
					text: ''
				},
				xAxis: {
					 min: 0,
						 gridLineWidth: 0,
						 minorGridLineWidth: 0,
						categories: meetingLevelArr,
					labels: {
							rotation: -45,
							style: {
								fontSize: '13px',
								fontFamily: 'Verdana, sans-serif'
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
						enabled: false,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},
				legend: {
					enabled: true,
					/* //align: 'right',
					x: -40,
					y: 30,
					verticalAlign: 'top',
					//y: -32,
					floating: true, */
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false
				},
				tooltip: {
				headerFormat: '<b>{point.x}</b>',
				pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.1f}%</b><br/>',
				shared: true
			},
				plotOptions: {
					column: {
						dataLabels: {
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
					name: 'Own Voter',
					data: ownVoterArr
				}, {
					name: 'Family Voter',    
					data: familyVoterArr
				}]
			});        
		});
	}
	function getTodayDate(){
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!

		var yyyy = today.getFullYear();  
		if(dd<10){
			dd='0'+dd
		} 
		if(mm<10){
			mm='0'+mm
		} 
		var today = dd+'/'+mm+'/'+yyyy;
		return today;
	}
	function getGSAndDPDtls(globalActivityMemberId,selectedMemberName,selectedUserType){
	  $("#individualDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
	  $("#districtWiseRportHeadingId").html("DISTRICT WISE REGISTRATIONS<b>("+selectedMemberName+" - "+selectedUserType+")</b>");
		var startDate = '';    
		var endDate = '';    
		var jsObj={  
			activityMemberId : globalActivityMemberId,
			stateId : globalStateId,         
			startDate : '02/10/2016',         
			endDate : getTodayDate()
		};
		$.ajax({           
			type : 'GET',         
			url : 'getDtlsOfBellowLvlMember.action',    
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){        
			 if(result != null && result.length > 0){
				 buildGetGSAndDPDtls(result);
				 $(".headingColor1").show();     
			 }else{        
				 $("#individualDtlsId").html("")
			 }
		});
	}
	function buildGetGSAndDPDtls(result){    
		var locationNameArr = [];
		var renewalArr = [];
		var newCadreArr = [];
		var cadre2014ArrPer = [];
		var colorArr=[];
		var jsonDataArr=[];
		for(var i in result){
			locationNameArr.push(result[i].sourceName);         
			renewalArr.push(parseFloat(result[i].renewalPerCount));
			newCadreArr.push(parseFloat(result[i].newPercCnt));   
			cadre2014ArrPer.push(result[i].cadreCountPer2014);
		}
		if(renewalArr.length > 0){
        jsonDataArr.push({name: '2016 Renewal Cadre',data: renewalArr,stack: '2016'});
        colorArr.push('#30AA74');      
        }
        if(newCadreArr.length > 0){
        jsonDataArr.push({name: '2016 New Cadre',data: newCadreArr,stack: '2016'});
        colorArr.push('#F36800');      
        }
        if(cadre2014ArrPer.length > 0){
        jsonDataArr.push({name: '2014 Cadre',data: cadre2014ArrPer,stack: '2014'});
        colorArr.push('#FFCA00');      
        }
		if(result!= null && result.length > 10){
        var highChartDivHight = result.length*20;
        $("#individualDtlsId").height(highChartDivHight);  
        }else{
        $("#individualDtlsId").height(260);    
        }
        $(function () {
          $("#individualDtlsId").highcharts({
            colors: colorArr,
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
              categories: locationNameArr
            },
            yAxis: {
              min: 0,
              gridLineWidth: 0,
              minorGridLineWidth: 0,
              title: {
                text: null
              }
            },
            legend: {
              reversed: true
            },
            plotOptions: {
              series: {
                stacking: 'normal'
              }
            },
            series:jsonDataArr 
          });
        });
	$("#individualDtlsId").find(".highcharts-legend-item:nth-child(1)").trigger("click");  
	}
	
	function refreshCadre(){        
		$("#totalTodayCadreRegistrationBlockDivAPId").html('');
		$("#totalTodayCadreRegistrationBlockDivTSId").html('');
		$("#enumeratorsInfoDivId").html('');
		$("#enumeratorsInfoDivTSId").html('');
		 $(".showCadrecls").hide();
		showCadreRegistreredCount(globalActivityMemberId);
		showCadreRegistreredCountTS(globalActivityMemberId,36);
		getEnumeratorsInfo(globalActivityMemberId);
		getEnumeratorsInfoTS(globalActivityMemberId,36);
		getCadreRecentTime();
		globalStatusCount=0;
        globalTargetCount=0;
        globalTotalCnt=0;
        globalTodayTotalCnt=0;
	}    
  /* $(document).on("click","#kuppamConstituencyCheckBoxId",function(){
	  var isKuppamExcluded;
	  if($(this).prop("checked")){
		  isKuppamExcluded="True";
	  }else{
		  isKuppamExcluded="False";
	  }
	var is2014Active="No";
	var accessLevelId=0;
	var accessLevelValues=[];
    var renewal2016CheckboxIsChecked="Y";
	var new2016CheckboxIsChecked="Y";
	var cadre2014CheckboxIsChecked="Y";  
	getApConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active,isKuppamExcluded);
   }); */      	
   
  $(document).on("click",".getConstituencyCls",function(){
	  $("#cadreExcelExpBtnId").hide();
	  $("#cadreExcelExpBtnId").attr("attr_tab_user_type","location");
		var stateId = $(this).attr("attr_state_id");
		var constituencyIds = $(this).attr("attr_location_ids");
		var reportType="Started";
		var locationIdsArr=[];
		 if(constituencyIds != null && constituencyIds != 0){
			locationIdsArr = constituencyIds.split(",");
			reportType="NotStarted";
		} 
		var locationType="Constituency";
		 getLocationWiseCadreInfoTodayDetails(stateId,locationIdsArr,reportType,locationType);
	});

	function getLocationWiseCadreInfoTodayDetails(stateId,locationIdsArr,reportType,locationType){
		$("#locationWiseCadreReportHeadingId").html("CONSTITUENCY WISE REPORT");
		$("#locationWiseCadreReportModalId").modal("show");
		$("#locationWiseCadreReportDivId").html(' ');
		$("#locationWiseProcessImgReport").show();
		var jsObj={  
			stateId : stateId,
			locationIdsArr : locationIdsArr
			
		};
		$.ajax({          
			type : 'POST',       
			url : 'getLocationWiseCadreInfoTodayDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#locationWiseProcessImgReport").hide();
			if(result != null && result.length > 0){
				buildLocationWiseReports(result,reportType,locationType);
			}else{
			   $("#locationWiseCadreReportDivId").html("NO DATA AVAILABLE.");	
			}
		});
	} 
$(document).on("click",".getManalMuncipalityCls",function(){
	    $("#cadreExcelExpBtnId").hide();
		$("#cadreExcelExpBtnId").attr("attr_tab_user_type","location");
		var stateId = $(this).attr("attr_state_id");
		var mandalMuncipalityIds = $(this).attr("attr_location_ids");
		var reportType="Started";
		var mandalMuncipalityIdsArr=[];
		 if(mandalMuncipalityIds != null && mandalMuncipalityIds != 0){
			mandalMuncipalityIdsArr = mandalMuncipalityIds.split(",");
			reportType="NotStarted";
		} 
		var locationType = "mandalMuncipality";
		 getMndlMncpalityTodayStatedNotStartedDetails(stateId,mandalMuncipalityIdsArr,reportType,locationType);
	});
 	function getMndlMncpalityTodayStatedNotStartedDetails(stateId,mandalMuncipalityIdsArr,reportType,locationType){
		$("#locationWiseCadreReportHeadingId").html("MANDAL/MUNCIPALITY WISE REPORT");
		$("#locationWiseCadreReportModalId").modal("show");
		$("#locationWiseCadreReportDivId").html(' ');
		$("#locationWiseProcessImgReport").show();
	
		var jsObj={  
			stateId : stateId,
			locationIdsArr : mandalMuncipalityIdsArr
		};
		$.ajax({          
			type : 'POST',       
			url : 'getMndlMncpalityTodayStatedNotStartedDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#locationWiseProcessImgReport").hide();
		  if(result != null && result.length > 0){
			 buildLocationWiseReports(result,reportType,locationType);  
		  }else{
			 $("#locationWiseCadreReportDivId").html("NO DATA AVAILABLE."); 
		  }
		});
	} 
	function buildLocationWiseReports(result,reportType,locationType){
		$("#cadreExcelExpBtnId").show();
	   var str='';
	   str+='<div class="table-responsive">';
	 	str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed" id="constituencyDtlsDataTblId">';   
	   str+='<thead>';
             str+='<th>District Name</th>';
			 str+='<th>Constituency Id</th>';
			 str+='<th>Constituency Name</th>'	 
			 if(locationType == "mandalMuncipality"){
			 str+='<th>Mandal/Muncipality</th>';
			 }
			 if(reportType=="Started"){
			  str+='<th>Total Registrations </th>';	 
			 }
			 
		 str+='</thead>';
		 str+='<tbody>';
		  for(var i in result){
				str+='<tr>';
				 if(result[i].name != null && result[i].name.length > 0){
					str+='<td>'+result[i].name+'</td>';      
				  }else{
					str+='<td> - </td>';  
				  }
				  if(result[i].locationId != null && result[i].locationId > 0){
					str+='<td>'+result[i].locationId+'</td>';  
				  }else{
				  str+='<td> - </td>';  
				  }
				  if(result[i].locationName != null && result[i].locationName.length > 0){
					str+='<td>'+result[i].locationName+'</td>';  
				  }else{
				  str+='<td> - </td>';  
				  }
				  if(locationType == "mandalMuncipality"){
					  if(result[i].locationName2 != null && result[i].locationName2.length > 0){
							str+='<td>'+result[i].locationName2+'</td>';  
						  }else{
						  str+='<td> - </td>';  
						  }
			          }
				   if(reportType=="Started"){
			 	 if(result[i].total2016CadreCnt != null && result[i].total2016CadreCnt > 0){
						 str+='<td>'+result[i].total2016CadreCnt+'</td>';       
					  }else{
						str+='<td> - </td>';  
					  }			  
			        }
				str+='</tr>';
			}
			 str+='</tbody>';
			 str+='</table>';
			 str+='</div>';
		 $("#locationWiseCadreReportDivId").html(str);
		if(locationType =="mandalMuncipality" || locationType=="Constituency"){
			 if(locationType == "mandalMuncipality"){
				if(reportType=="Started"){
					 $("#constituencyDtlsDataTblId").dataTable({
					 "aaSorting": [[ 4, "desc" ]], 
					"iDisplayLength" : 10,
					"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
				 }); 
			  }else{
			  $("#constituencyDtlsDataTblId").dataTable({
			    "aaSorting": [[ 2, "asc" ]], 
			    "iDisplayLength" : 10,
				"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]
		      });    
			 }
		  }else if(locationType == "Constituency"){
			   if(reportType=="Started"){
				  $("#constituencyDtlsDataTblId").dataTable({
					 "aaSorting": [[ 3, "desc" ]], 
					"iDisplayLength" : 10,
					"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
				 });   
		      }else{
				$("#constituencyDtlsDataTblId").dataTable({
			    "aaSorting": [[ 2, "asc" ]], 
			    "iDisplayLength" : 10,
                "aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]				
		        });    
			 }
		  }
		}
		 }
  $(document).on("click","#statewiseoverview",function(){
	  
	  if($(this).data("hidden")== "false"){
		  $(this).data("hidden", "true");
		  $(this).html("Hide");
		  $("#statewiseoverviewPanel").show();
	  }
	  else {
		  $(this).data("hidden", "false");
		  $(this).html("Show");
		  $("#statewiseoverviewPanel").hide();
	  }
	  
  });  // end of click function statewiseoverview
  
 function calculatingPercentage(globalTotalCnt,globalTodayTotalCnt,globalTargetCount){
	  $(".showCadrecls").show();
	  $("#totalRegId").html(""+globalTotalCnt+"");
	  $("#totalTodayId").html(""+globalTodayTotalCnt+"");
	  $("#totalperc").html("<span style='font-size:12px;'>"+((globalTotalCnt*100)/globalTargetCount).toFixed(2)+"%</span>");
	  $("#todayperc1").html("<span style='font-size:12px;'>"+((globalTodayTotalCnt*100)/globalTargetCount).toFixed(2)+"%</span>");	
 }
 $(document).on('click','.compCls',function(){ 
	 $("#tourDocHeadingId").html("Cadre Registration Comparison Details");
	 $("#alertDestId").html("");
	 $("#alertAttachTitId").html("");
	 $("#alertAttachImgId").html("");
	 $("#alertInvolvedCandidates").html("");
	 $("#alertAssignedCandidates").html("");
	 $("#alertCommentsDiv").html("");      
	 $("#alertStatusDiv").html("");          
	 $("#cdrModelDivId").modal("show");
	 $("#cdrModelId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
	 var stateId = $(this).attr("attr_state_id");
	 var oprion = $(this).attr("attr_option");
	 getHourWiseRegDtls(stateId,oprion);
 });
 function getHourWiseRegDtls(stateId,oprion){  
	 var jObj = {
			  stateId : stateId,      
			  option : oprion    
		}
		$.ajax({
			type:'GET',
			url: 'getHourWiseRegDtlsAction.action',    
			data : {task:JSON.stringify(jObj)}
		}).done(function(result){ 
			$("#cdrModelId").html("");
			if(result != null && result.length > 0){
				buildHourWiseRegDtls(result);
			}      
		});                     
 }
 function buildHourWiseRegDtls(result){ 
	
	var todaySummation = 0;
	var yesterDaySummation = 0;
	//var todayUserSummation = 0;
	//var yesterDayUserSummation = 0;
	//var hour = getTodayHour();
	todaySummation = parseInt(result[0].todayTotalReg);
	yesterDaySummation = parseInt(result[0].lastDayTotalReg);
	//todayUserSummation = parseInt(result[0].todayTotalUsers);
	//yesterDayUserSummation = parseInt(result[0].lastDayTotalUsers);
	 var str='';
	        str+='<div class="table-responsive">';
			str+='<table class="table table-bordered table-condensed "> ';
				str+='<thead> ';
					str+='<tr>';
						str+='<th>Hour</th>';
						str+='<th>Today Total Reg</th>';
						str+='<th>Yesterday Total Reg</th>';
						str+='<th>Today Total Active Users</th>';
						str+='<th>Yesterday Total Active Users</th>';  
					str+='</tr>'; 
				str+='</thead>'; 
				str+='<tbody>';
				str+='<tr> ';
					str+='<td>'+result[0].label+'</td> ';
					
					if(result[0].todayTotalReg == 0){
							str+='<td>-</td> ';
					}else{
						str+='<td>'+result[0].todayTotalReg+'-('+todaySummation+')</td> ';
					}
					if(result[0].lastDayTotalReg == 0){
						str+='<td>-</td> ';
					}else{
						str+='<td>'+result[0].lastDayTotalReg+'-('+yesterDaySummation+')</td> ';
					}
					
					if(result[0].todayTotalUsers == 0){
						str+='<td>-</td> ';
					}else{
						str+='<td>'+result[0].todayTotalUsers+'</td> ';
					}
					if(result[0].lastDayTotalUsers == 0){  
						str+='<td>-</td> ';
					}else{
						str+='<td>'+result[0].lastDayTotalUsers+'</td> ';  
					}
				str+='<tr> ';   	
				for(var i in result){
					if((parseInt(i)+parseInt(1)) == result.length){    
						break;
					}  
						i = parseInt(i) + parseInt(1);   
					str+='<tr> ';	
						str+='<td>'+result[i].label+'</td> ';
						if(result[i].todayTotalReg == 0){
							str+='<td>-</td> ';
						}else{
							todaySummation = parseInt(todaySummation) + parseInt(result[i].todayTotalReg);
							str+='<td>'+result[i].todayTotalReg+'-('+todaySummation+')</td> ';
						}
						
						
						if(result[i].lastDayTotalReg == 0){
							str+='<td>-</td> ';
						}else{
							yesterDaySummation = parseInt(yesterDaySummation) + parseInt(result[i].lastDayTotalReg);
							str+='<td>'+result[i].lastDayTotalReg+'-('+yesterDaySummation+')</td> ';
							
						}
						if(result[i].todayTotalUsers == 0){
							str+='<td>-</td> ';
						}else{
							//todayUserSummation = parseInt(todayUserSummation) + parseInt(result[i].todayTotalUsers);
							str+='<td>'+result[i].todayTotalUsers+'</td> ';
							
						}
						if(result[i].lastDayTotalUsers == 0){
							str+='<td>-</td> ';
						}else{
							//yesterDayUserSummation = parseInt(yesterDayUserSummation) + parseInt(result[i].lastDayTotalUsers);
							str+='<td>'+result[i].lastDayTotalUsers+'</td> ';
							
						}  
				   str+='</tr>';   
				
				}
				str+='<tr>';
					str+='<td>Total</td>';
					str+='<td>'+todaySummation+'</td>';
					str+='<td>'+yesterDaySummation+'</td>';
					str+='<td></td>';
					str+='<td></td>';
				str+='</tr>';
			str+='</tbody>';   
		str+='</table>';
		str+='</div>';
		$("#cdrModelId").html(str);
 }
 
 $(document).on("click","#cadreExcelExpBtnId",function(){
  var type=$(this).attr("attr_tab_user_type");
 	if(type=="Tour"){
	 generateExcelReportForToursDetails();	
	}else if(type=="location"){
	generateExcelReportForCadre();	
	} 
 });
 function generateExcelReportForCadre(){
	tableToExcel(constituencyDtlsDataTblId, 'Location Wise Registrations Report');
}
/* $(document).on("click",".tabUserDtlsCls",function(){
	    $("#cadreExcelExpBtnId").hide();
		$("#cadreExcelExpBtnId").attr("attr_tab_user_type","Tab");
		var stateId = $(this).attr("attr_state_id");
		 getTodayAndYesterdayTabUserRgstrtnComparisonDetails(stateId);
	}); */
function getTodayAndYesterdayTabUserRgstrtnComparisonDetails(stateId){
		$("#locationWiseCadreReportHeadingId").html("TAB USER WISE COMPARISON REPORT");
		$("#locationWiseCadreReportModalId").modal("show");
		$("#locationWiseCadreReportDivId").html(' ');
		$("#locationWiseProcessImgReport").show();
	
		var jsObj={  
			stateId : stateId
		};
		$.ajax({          
			type : 'POST',       
			url : 'getTodayAndYesterdayTabUserRgstrtnComparisonDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
		 $("#locationWiseProcessImgReport").hide();
	 	  if(result != null && result.length > 0){
			 buildTabUserComparisonRslt(result); 
		  }else{
			$("#locationWiseCadreReportDivId").html("NO DATA AVAILABLE.");  
		  }
		});
	} 	
function buildTabUserComparisonRslt(result){
	 $("#cadreExcelExpBtnId").show();
	 var str='';
	 str+='<div class="table-responsive">';
	 	str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed " id="tabUserComparisonDtlsTabId">';   
	   str+='<thead>';
             str+='<th>Tab UserName</th>';
			 str+='<th>MobileNo</th>';
			 str+='<th>Image</th>'	 
			 str+='<th>Yesterday Status</th>';
			 str+='<th>Total Registrations</th>';
			 str+='<th>Today Status</th>';
			 str+='<th>Total Registrations </th>';	 
			 str+='<th>today(IsActive)</th>';	 
		 str+='</thead>';
		 str+='<tbody>';
		  for(var i in result){
				str+='<tr>';
				 if(result[i].name != null && result[i].name.length > 0){
					str+='<td>'+result[i].name+'</td>';      
				  }else{
					str+='<td> - </td>';  
				  }
				  if(result[i].mobileNo != null && result[i].mobileNo > 0){
					str+='<td>'+result[i].mobileNo+'</td>';  
				  }else{
				  str+='<td> - </td>';  
				  }
				  if(result[i].imagePath != null && result[i].imagePath.length > 0){
					str+='<td><img src="http://mytdp.com/CR/tab_user_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';  
				  }else{
					 str+='<td> - </td>';   
				  }
				  if(result[i].yesPersent != null && result[i].yesPersent.length > 0){
					str+='<td>'+result[i].yesPersent+'</td>';  
				  }else{
				    str+='<td> - </td>';  
				  }
				  if(result[i].yesRegCount != null && result[i].yesRegCount > 0){
						str+='<td>'+result[i].yesRegCount+'</td>';  
				  }else{
					  str+='<td> - </td>';  
				  }
				if(result[i].todayPersent != null && result[i].todayPersent.length > 0){
					str+='<td>'+result[i].todayPersent+'</td>';  
				  }else{
				  str+='<td> - </td>';  
				  }
			     if(result[i].todayRegCount != null && result[i].todayRegCount > 0){
						 str+='<td>'+result[i].todayRegCount+'</td>';       
				 }else{
						str+='<td> - </td>';  
				 }			
                 if(result[i].isActive != null && result[i].isActive.length > 0){
						 str+='<td>'+result[i].isActive+'</td>';       
				 }else{
						str+='<td> - </td>';  
				 }							 
				str+='</tr>';
			}
			 str+='</tbody>';
			 str+='</table>';
			  str+='</div>';
		 $("#locationWiseCadreReportDivId").html(str);
		  $("#tabUserComparisonDtlsTabId").dataTable({
				 "aaSorting": [[ 4, "desc" ]], 
				"iDisplayLength" : 10,
				"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
			 }); 
	}	
	$(document).on('click','#cadreSettingsId',function(){ 
		$("#checkErrId").html("");
		var count = 0;
		$(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			count += 1;
		  }
	   });
	   if(count == 2){
		   $("#checkErrId").html("Please Select Only One Option")
		   return;      
	   }
	   if(count == 0){  
		   $("#checkErrId").html("Please Select Only One Option")  
		   return;  
	   }            
		$(".specialCadreDropDown").toggle();          
	});
	$(document).on('click','.cadreExpand',function(){
		if($(this).find("i").hasClass("glyphicon glyphicon-fullscreen")){
			$(".specialCadreDropDown").addClass("left270");
			$(".specialCadreDropDown").removeClass("left850"); 
		}else{
			$(".specialCadreDropDown").addClass("left850");     
			$(".specialCadreDropDown").removeClass("left270");	  
		}  
	});
	$(document).on("click",".specialCadreBtncls",function(){
		$("#checkErrId").html("");
		var count = 0;
		$(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			count += 1;
		  }
	   });
	   if(count == 2){
		   $("#checkErrId").html("Please Select Only One Option")
		   return;      
	   }
	   if(count == 0){
		   $("#checkErrId").html("Please Select Only One Option")    
		   return;  
	   }      
	   //call here.....   
	   var sortingType = '';
	   $(".selectOneSpecialCadre").each(function() {
		  if($(this).is(":checked")){  
			sortingType = $(this).attr("attr_sort_type");
		  }
		});  
		$("#totalTodayCadreRegistrationBlockDivAPId").html('');
		$("#totalTodayCadreRegistrationBlockDivTSId").html('');
		$("#enumeratorsInfoDivId").html('');
		$("#enumeratorsInfoDivTSId").html('');
		$(".showCadrecls").hide();  
		globalStatusCount=0;
        globalTargetCount=0;
        globalTotalCnt=0;
        globalTodayTotalCnt=0;
		
		getUserTypeWiseTotalCadreRegistrationCount();//settingscall
		showCadreRegistreredCount(globalActivityMemberId);
		showCadreRegistreredCountTS(globalActivityMemberId,36);
		getEnumeratorsInfo(globalActivityMemberId);
		getEnumeratorsInfoTS(globalActivityMemberId,36);      
		getAllItsSubUserTypeIdsByParentUserTypeIdForCadreRegistration(globalUserTypeId);      

		var filterApplyType="No";
		var is2014Active="No";
		var accessLevelId=0;
		var accessLevelValues=[];
		var renewal2016CheckboxIsChecked="Y";
		var new2016CheckboxIsChecked="Y";
		var cadre2014CheckboxIsChecked="Y";  
		var isKuppamExcluded="True";
		getCadreDetailsBasedOnUserType(filterApplyType);  
		getTsDistrictWiseTsDetails(accessLevelId,accessLevelValues,filterApplyType);	getApConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active,isKuppamExcluded);
		getTsConstituencyCadreRegistrationDetails(accessLevelId,accessLevelValues,renewal2016CheckboxIsChecked,new2016CheckboxIsChecked,cadre2014CheckboxIsChecked,is2014Active);	
			
		$(".specialCadreDropDown").toggle();    
	});
	$(document).on("click",".settingCloseCls",function(){  
		$(".specialCadreDropDown").toggle();    
	});
	
	/* Sorting type validation in setting */
	$(document).on("click",".selectOneSpecialCadre",function(){
		$(".selectOneSpecialCadre").prop("checked",false);
		$(this).prop("checked",true);
	});
	