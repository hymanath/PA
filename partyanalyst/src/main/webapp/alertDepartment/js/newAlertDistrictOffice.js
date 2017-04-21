
var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");



$("#dateRangePickerAUM").daterangepicker({
		opens: 'left',
		startDate: currentFromDate,
		endDate: currentToDate,
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
			'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
			'Today' : [moment(), moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()]
		}
	});
	var dates= $("#dateRangePickerAUM").val();
	var pickerDates = currentFromDate+' - '+currentToDate
	if(dates == pickerDates)
	{
		$("#dateRangePickerAUM").val('All');
	}
	
	$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
		currentFromDate = picker.startDate.format('DD/MM/YYYY');
		currentToDate = picker.endDate.format('DD/MM/YYYY');
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePickerAUM").val('All');
		}
		
		onLoadCallsAMU();
	});
	
	function getAlertType(){
		 var alertType = ''; 
		$('.switch-btn-alertType li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  alertType = $(this).attr("attr_type");
			 }
		});
		return alertType;
	}
	function getSearchType(){
		 var searchType = ''; 
		$('.switch-btn li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  searchType = $(this).attr("attr_type");
			 }
		});
		return searchType;
	}


	function getDistrictWiseSorting(){
		 var districtSortingType = ''; 
		 var districtOrderType = ''; 
		$('.locationWiseSortingDistrict li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  districtSortingType = $(this).attr("attr_sorting_type");
			  districtOrderType = $(this).attr("attr_order_type");
			 }
		});
		return {
			districtSortingType : districtSortingType,
			districtOrderType :districtOrderType
			};
	}
	
	function getDivisionWiseSorting(){
		 var divisionSortingType = ''; 
		 var divisionOrderType = ''; 
		$('.locationWiseSortingDivision li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  divisionSortingType = $(this).attr("attr_sorting_type");
			  divisionOrderType = $(this).attr("attr_order_type");
			 }
		});
		return {
			divisionSortingType : divisionSortingType,
			divisionOrderType :divisionOrderType
			};
	}
	
	function getSubDivision(){
		 var subSortingType = ''; 
		 var subOrderType = ''; 
		$('.locationWiseSortingSubDivision li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  subSortingType = $(this).attr("attr_sorting_type");
			  subOrderType = $(this).attr("attr_order_type");
			 }
		});
		return {
			subSortingType : subSortingType,
			subOrderType :subOrderType
			};
	}
	function getdisWiseSubLeveltLevelType(){
		 var disWiseSubLeveltLevelType = []; 
		$('.distWiseDistLevelCls').each(function(i, obj){
			 if($(this).val() == 0){
				 disWiseSubLeveltLevelType = []
			 }else{
				 disWiseSubLeveltLevelType.push($(this).val());
			 }
			
		});
		return disWiseSubLeveltLevelType;
	}
	function getdivWiseSubLeveltLevelType(){
		 var divWiseSubLeveltLevelType = []; 
		$('.divisionWiseDistLevelIdCls').each(function(i, obj){
			
			 if($(this).val() == 0){
				 divWiseSubLeveltLevelType = []
			 }else{
				 divWiseSubLeveltLevelType.push($(this).val());
			 }
		});
		return divWiseSubLeveltLevelType;
	}
	function onLoadCallsAMU(){
			getDistrictOfficerAlertsCountView();
		
		//status and location wise click start
		$(document).on("click",".switch-btn li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			
			var searchType = $(this).attr("attr_type");
			var alertType = getAlertType();
			var disWiseSubLeveltLevelType = getdisWiseSubLeveltLevelType();
			var divWiseSubLeveltLevelType = getdivWiseSubLeveltLevelType();
			$("#DistrictNamesId").val(0);
			$("#DivisionDistNamesId").val(0);
			
			$("#DivisionNamesId").html('');
			$("#DivisionNamesId").append('<option value="0">Select Division</option>');
			
			$("#SubDivisionDistNamesId").val(0);
			$("#SubDivisionDiviNamesId").html('');
			$("#SubDivisionDiviNamesId").append('<option value="0">Select Division</option>');
			$("#SubDivisionNamesId").html('');
			$("#SubDivisionNamesId").append('<option value="0">Select Sub Division</option>');
			if(searchType == "statusWise"){
				$(".distWiseDistLevelShowCls").show();
				$(".divisionWiseDistLevelIdShowCls").show();
				
			}else{
				$(".distWiseDistLevelShowCls").hide();
				$(".divisionWiseDistLevelIdShowCls").hide();
			}
		
			getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,"desc","count",0,disWiseSubLeveltLevelType,golablSelectedValue);
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,"desc","count",0,0,divWiseSubLeveltLevelType,divisionSelectedValue);
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,"desc","count",0,0,0);
		});
		$(document).on("click",".switch-btn-alertType li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var alertType = $(this).attr("attr_type");
			var searchType = getSearchType();
			var disWiseSubLeveltLevelType = getdisWiseSubLeveltLevelType();
			var divWiseSubLeveltLevelType = getdivWiseSubLeveltLevelType();
			$("#DistrictNamesId").val(0);
			$("#DistrictNamesId").val(0);
			$("#DivisionDistNamesId").val(0);
			$("#DivisionNamesId").html('');
			$("#DivisionNamesId").append('<option value="0">Select Division</option>');
			$("#SubDivisionDistNamesId").val(0);
			$("#SubDivisionDiviNamesId").html('');
			$("#SubDivisionDiviNamesId").append('<option value="0">Select Division</option>');
			$("#SubDivisionNamesId").html('');
			$("#SubDivisionNamesId").append('<option value="0">Select Sub Division</option>');
			getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,"desc","count",0,disWiseSubLeveltLevelType,golablSelectedValue);
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,"desc","count",0,0,divWiseSubLeveltLevelType,divisionSelectedValue);
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,"desc","count",0,0,0);
			
		});
		//status and location wise click start
		
		//district wise graph click start
		$(document).on("click",".locationWiseSortingDistrict li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = $(this).attr("attr_sorting_type");
			var orderType = $(this).attr("attr_order_type");
			$("#DistrictNamesId").val(0);
			var disWiseSubLeveltLevelType = getdisWiseSubLeveltLevelType()
			getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,sortingType,orderType,0,disWiseSubLeveltLevelType,golablSelectedValue);
		});
		
		$(document).on("change",".locationWiseDistOnChange",function(){
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = getDistrictWiseSorting().districtSortingType; // 'value1'
			var orderType = getDistrictWiseSorting().districtOrderType; // 'value2'
			var districtId =  $("#DistrictNamesId").val();
			var disWiseSubLeveltLevelType = getdisWiseSubLeveltLevelType()
			getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,disWiseSubLeveltLevelType,"");
		});
		$(document).on("change",".distWiseDistLevelCls",function(){
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = getDistrictWiseSorting().districtSortingType; // 'value1'
			var orderType = getDistrictWiseSorting().districtOrderType; // 'value2'
			var districtId =  $("#DistrictNamesId").val();
			var disWiseSubLeveltLevelType = getdisWiseSubLeveltLevelType()
			getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,disWiseSubLeveltLevelType,"");
		});
		
		//district wise graph click End
		
		//division wise graph click start
		$(document).on("click",".locationWiseSortingDivision li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = $(this).attr("attr_sorting_type");
			var orderType = $(this).attr("attr_order_type");
			$("#DivisionDistNamesId").val(0);
			$("#DivisionNamesId").html('');
			$("#DivisionNamesId").append('<option value="0">Select Division</option>');
			var divWiseSubLeveltLevelType = getdivWiseSubLeveltLevelType();
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,0,0,divWiseSubLeveltLevelType,divisionSelectedValue);
		});
		$(document).on("change",".locationWiseDiviDistOnChange",function(){
			var districtId = $(this).val();
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
			var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
			var divWiseSubLeveltLevelType = getdivWiseSubLeveltLevelType();	
			getDivisionIdListForDivisionFilter(globalDepartmentId,districtId);
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,0,divWiseSubLeveltLevelType,"");
			
		});
		$(document).on("change",".locationWiseDiviOnChange",function(){
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
			var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
			var divWiseSubLeveltLevelType = getdivWiseSubLeveltLevelType();	
			var districtId =  $("#DivisionDistNamesId").val();
			var districtDivisionId =  $("#DivisionNamesId").val();
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,divWiseSubLeveltLevelType,"");
		});
		
		$(document).on("change",".divisionWiseDistLevelIdCls",function(){
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
			var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
			var divWiseSubLeveltLevelType = getdivWiseSubLeveltLevelType();	
			var districtId =  $("#DivisionDistNamesId").val();
			var districtDivisionId =  $("#DivisionNamesId").val();
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,divWiseSubLeveltLevelType," ");
		});
		//division wise graph click End
		
		//sub-division wise graph click start
		$(document).on("click",".locationWiseSortingSubDivision li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var searchType = getSearchType();
			var alertType = getAlertType();
			var sortingType = $(this).attr("attr_sorting_type");
			var orderType = $(this).attr("attr_order_type");
			$("#SubDivisionDistNamesId").val(0);
		
			
			$("#SubDivisionDiviNamesId").html('');
			$("#SubDivisionDiviNamesId").append('<option value="0">Select Division</option>');
			$("#SubDivisionNamesId").html('');
			$("#SubDivisionNamesId").append('<option value="0">Select Sub Division</option>');
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,0,0,0);
		});
		$(document).on("change",".locationWiseSubDiviDistOnChange",function(){
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			
			var searchType = getSearchType();
			var alertType = getAlertType();
			
			var districtId =$("#SubDivisionDistNamesId").val();
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,0,0);
			getDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId);  
		});
		$(document).on("change",".locationWiseSubDiviDiviOnChange",function(){
			
			var districtId =$("#SubDivisionDistNamesId").val();
			var districtDivisionId =$("#SubDivisionDiviNamesId").val();
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			var searchType = getSearchType();
			var alertType = getAlertType();
			
			getSubDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId,districtDivisionId);  
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,0);
		});
		$(document).on("change",".locationWiseSubDiviOnChange",function(){
		
			var districtId =$("#SubDivisionDistNamesId").val();
			var districtDivisionId =$("#SubDivisionDiviNamesId").val();
			var districtSubDivisionId =$("#SubDivisionNamesId").val();
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			var searchType = getSearchType();
			var alertType = getAlertType();
			
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,districtSubDivisionId);
			
		});
		//sub-division wise graph click end
		

	}
	
	

	function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip)
	{
		'use strict';
		
		$('#'+id).highcharts({
			chart: type,
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			xAxis: xAxis,
			yAxis: yAxis,
			tooltip: tooltip,
			plotOptions: plotOptions,
			legend: legend,
			series: data
		});
	}
	
	
	function getDistrictOfficerAlertsCountView(){
	$("#myAlertsDivID").html(spinner);
	$("#mySubTasksDivID").html(spinner);
	$("#assignedSubTasksDivID").html(spinner);
	
	//var userId=19601;
    var jObj ={
      //userId:userId,
	  startDate:currentFromDate,
	  endDate:currentToDate,
	  paperIdArr:newspapersGlobalArr,
	  chanelIdArr:channelGlobalArr,
	  callCenterArr:callCenterGlobalArr
    }
    $.ajax({
      type:'GET',
      url: 'getDistrictOfficerAlertsCountViewAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#myAlertsDivID").html('');
		$("#mySubTasksDivID").html('');
		$("#assignedSubTasksDivID").html('');
		if(result !=null){
			globalDepartmentId = result.departmentId;
			globalgovtDeptDesigOffcrId = result.govtDeptDesigOffcrIds[0];
			globalgovtOfficerId = result.govtOfficerIds[0];
			globalDesignationId = result.designationId;
			globalUserLevelId = result.levelId;
			if(result.levelValues != null && result.levelValues.length > 0)
				globalUserLevelValues=result.levelValues;
		
		}
		buildDistrictOfficerAlertsCountView(result);
		var disWiseSubLeveltLevelType = getdisWiseSubLeveltLevelType();
		var divWiseSubLeveltLevelType = getdivWiseSubLeveltLevelType();	
		getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,"scopeWise","alert","desc","count",0,disWiseSubLeveltLevelType,golablSelectedValue);
		getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,"scopeWise","alert","desc","count",0,0,divWiseSubLeveltLevelType,divisionSelectedValue);
		getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,"scopeWise","alert","desc","count",0,0,0);
		getDistIdListForDistFilter(globalDepartmentId);
		getDistIdListForDivisionFilter(globalDepartmentId);
		getDistrictIdListForSubDivisionFilter(globalDepartmentId); 
    });
}



function buildDistrictOfficerAlertsCountView(result){
	
	
		if(result !=null && result.list1 !=null && result.list1.length>0){
			var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0){
				if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0 && result.list1[0].todayCount>0){
					totalCoutAlertIds.push(result.list1[0].todayAlertIds);
				str+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' attr_name ="TODAY" attr_total_count = "'+result.list1[0].todayCount+'" attr_alert_type="alert">TODAY <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].todayCount+'</span></p>';
				}
			else{
				str+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list1[0].todayCount+'</span></p>';
			}
			}else{
				str+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str+='<hr class="m_0"/>';
			if(result.list1[0].overAllCnt !=null && result.list1[0].overAllCnt !=0){
				
				if(result.list1[0].overAllCnt !=null && result.list1[0].overAllCnt !=0 && result.list1[0].overAllCnt>0){
					overAllAlertIds.push(result.list1[0].overAllAlertIds);
				str+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+' attr_name ="OVERALL" attr_total_count = "'+result.list1[0].overAllCnt+'" attr_alert_type="alert">OVERALL <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].overAllCnt+'</span></p>';
				}
			  else{
				  str+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list1[0].overAllCnt+'</span></p>';
			  }
			}else{
				str+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str+='<div id="myAlertGraphView" style="height:250px"></div>';
			str+='</div>';
		str+='</div>';
		$("#myAlertsDivID").html(str);
	}else{
		$("#myAlertsDivID").html("No Data Available");
	}
	
	
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var mainArrTempAT=[];
		var namesArrAT=[];
		var countAT = [];
		
		for(var i in result.list1){
			
			var uniqCnt = {};
			
			var totalAlertCnt = result.list1[0].overAllCnt;
			namesArrAT.push(result.list1[i].name);
			var tempArrAT = {"y":result.list1[i].count,color:result.list1[i].color,"extra":result.list1[i].id+"-"+result.list1[i].name+"-"+result.list1[i].count};
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].count),color:"#D3D3D3","extra":result.list1[i].id+"-"+result.list1[i].name+"-"+result.list1[i].count};
			countAT.push(uniqCnt);
			
			mainArrTempAT.push(tempArrAT);
		}
	
	
	 $('#myAlertGraphView').highcharts({
			
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
				categories: namesArrAT,
				labels: {
				enabled: true,
					
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
					enabled: false,
						
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
				verticalAlign:'top',
				enabled: false
			},
			plotOptions: {
					bar: {
						stacking: 'percent',  
						pointWidth: 25,
						gridLineWidth: 15
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var stateImpactInfo = (this.extra).split("-");
										 var StatusId = stateImpactInfo[0];
										var StatusName = stateImpactInfo[1];
										var totalAlertCnt = stateImpactInfo[2];
										
										 if(totalAlertCnt == 0){
											return;  
										 }
										getDistrictLevelDeptWiseAlertClick(StatusId,StatusName,totalAlertCnt,"alert");
									}
								}
							}
				        }
				
				},
			series: [{
				
				data: countAT,
					
			},
			{
				name: "Number of alerts",
				 data: mainArrTempAT,
				colorByPoint: true,
				 dataLabels: {
					useHTML: true,
					align: 'left',
					
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					 formatter: function() {
						return '<span style="position: absolute;"><br/>'+Highcharts.numberFormat(this.percentage,2)+'%'+' '+'('+this.y+')</span>';
					} 
					
				}  
				
			}]
		}); 
		}else{
			 $('#myAlertGraphView').html("No Data Available")
		}

			
		
		if(result !=null && result.list2 !=null && result.list2.length>0){
			var str1='';
		str1+='<div class="row">';
			str1+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list2[0].todayCount !=null && result.list2[0].todayCount !=0){
			if(result.list2[0].todayCount !=null && result.list2[0].todayCount !=0 && result.list2[0].todayCount>0){
				  totalCoutAlertIds.push(result.list2[0].todayAlertIds);
				str1+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' attr_name ="TODAY" attr_total_count = "'+result.list2[0].todayCount+'" attr_alert_type="mySubTasks">TODAY <span class="pull-right badge" style="cursor:pointer;">'+result.list2[0].todayCount+'</span></p>';
			}else{
				str1+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list2[0].todayCount+'</span></p>';
			}
			}else{
				str1+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str1+='<hr class="m_0"/>';
			if(result.list2[0].overAllCnt !=null && result.list2[0].overAllCnt !=0){
				overAllAlertIds = [];
			 if(result.list2[0].overAllCnt !=null && result.list2[0].overAllCnt !=0 && result.list2[0].overAllCnt>0){
				overAllAlertIds.push(result.list2[0].overAllAlertIds);
				str1+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+' attr_name ="OVERALL" attr_total_count = "'+result.list2[0].overAllCnt+'" attr_alert_type="mySubTasks">OVERALL <span class="pull-right badge" style="cursor:pointer;">'+result.list2[0].overAllCnt+'</span></p>';
			 }else{
				 str1+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list2[0].overAllCnt+'</span></p>';
			 }
			}else{
				str1+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str1+='<div id="mySubTasksGraphView" style="height:250px"></div>';
			str1+='</div>';
		str1+='</div>';
		$("#mySubTasksDivID").html(str1);
	}else{
		$("#mySubTasksDivID").html("No Data Available");
	}
	
	
if(result !=null && result.list2 !=null && result.list2.length>0){
		var mainArrTempST=[];
		var namesArrST=[];
		var countST = [];
		
		for(var i in result.list2){
			
			var uniqCnt = {};
			
			var totalAlertCnt = result.list2[0].overAllCnt;
			namesArrST.push(result.list2[i].name);
			var tempArrST = {"y":result.list2[i].count,color:result.list2[i].color,"extra":result.list2[i].id+"-"+result.list2[i].name+"-"+result.list2[i].count};
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list2[i].count),color:"#D3D3D3","extra":result.list2[i].id+"-"+result.list2[i].name+"-"+result.list2[i].count};
			countST.push(uniqCnt);
			
			mainArrTempST.push(tempArrST);
		}
	
	 $('#mySubTasksGraphView').highcharts({
			
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
				categories: namesArrST,
				labels: {
				enabled: true,
					
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
					enabled: false,
						
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
				verticalAlign:'top',
				enabled: false
			},
			plotOptions: {
					bar: {
						stacking: 'percent',  
						pointWidth: 25,
						gridLineWidth: 15
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var stateImpactInfo = (this.extra).split("-");
										 var StatusId = stateImpactInfo[0];
										var StatusName = stateImpactInfo[1];
										var totalAlertCnt = stateImpactInfo[2];
										
										 if(totalAlertCnt == 0){
											return;  
										 }
										getDistrictLevelDeptWiseAlertClick(StatusId,StatusName,totalAlertCnt,"mySubTasks");
									}
								}
							}
				        }
				
				},
			series: [{
				
				data: countST,
					
			},
			{
				name: "Number of alerts",
				 data: mainArrTempST,
				 colorByPoint: true,
				 dataLabels: {
					useHTML: true,
					align: 'left',
					
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					 formatter: function() {
						return '<span style="position: absolute;"><br/>'+Highcharts.numberFormat(this.percentage,2)+'%'+' '+'('+this.y+')</span>';
					} 
					
				}  
				
			}]
		}); 
		}else{
			 $('#mySubTasksGraphView').html("No Data Available")
		}
			
		
		if(result !=null && result.list3 !=null && result.list3.length>0){
				var str2='';
		str2+='<div class="row">';
			str2+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list3[0].todayCount !=null && result.list3[0].todayCount !=0){
				if(result.list3[0].todayCount !=null && result.list3[0].todayCount !=0 && result.list3[0].todayCount>0){
					totalCoutAlertIds.push(result.list3[0].todayAlertIds);
				str2+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' attr_name ="TODAY" attr_total_count = "'+result.list3[0].todayCount+'" attr_alert_type="myAssignedSubTasks" >TODAY <span class="pull-right badge" style="cursor:pointer;">'+result.list3[0].todayCount+'</span></p>';
				}else{
				str2+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list3[0].todayCount+'</span></p>';
				}
			}else{
				str2+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str2+='<hr class="m_0"/>';
			if(result.list3[0].overAllCnt !=null && result.list3[0].overAllCnt !=0){
				
				if(result.list3[0].overAllCnt !=null && result.list3[0].overAllCnt !=0 && result.list3[0].overAllCnt>0){
					overAllAlertIds.push(result.list3[0].overAllAlertIds);
				str2+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+' attr_name ="OVERALL" attr_total_count = "'+result.list3[0].overAllCnt+'" attr_alert_type="myAssignedSubTasks" >OVERALL <span class="pull-right badge" style="cursor:pointer;">'+result.list3[0].overAllCnt+'</span></p>';
				}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list3[0].overAllCnt+'</span></p>';
				}
			}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str2+='<div id="assignedSubTasksGraphView" style="height:250px"></div>';
			str2+='</div>';
		str2+='</div>';
		$("#assignedSubTasksDivID").html(str2);
	}else{
		$("#assignedSubTasksDivID").html("No Data Available");
	}
	
	
	if(result !=null && result.list3 !=null && result.list3.length>0){
		var mainArrTemp=[];
		var namesArr=[];
		var countAST = [];
		
		for(var i in result.list3){
			
			var uniqCnt = {};
			var totalAlertCnt = result.list3[0].overAllCnt;
			namesArr.push(result.list3[i].name);
			var tempArr = {"y":result.list3[i].count,color:result.list3[i].color,"extra":result.list3[i].id+"-"+result.list3[i].name+"-"+result.list3[i].count};
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list3[i].count),color:"#D3D3D3","extra":result.list3[i].id+"-"+result.list3[i].name+"-"+result.list3[i].count};
			countAST.push(uniqCnt);
			
			mainArrTemp.push(tempArr);
		}
	
	
	 $('#assignedSubTasksGraphView').highcharts({
			
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
				categories: namesArr,
				labels: {
				enabled: true,
					
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
					enabled: false,
						
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
				verticalAlign:'top',
				enabled: false
			},
			plotOptions: {
					bar: {
						stacking: 'percent',  
						pointWidth: 25,
						gridLineWidth: 15
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var stateImpactInfo = (this.extra).split("-");
										 var StatusId = stateImpactInfo[0];
										var StatusName = stateImpactInfo[1];
										var totalAlertCnt = stateImpactInfo[2];
										
										 if(totalAlertCnt == 0){
											return;  
										 }
										getDistrictLevelDeptWiseAlertClick(StatusId,StatusName,totalAlertCnt,"myAssignedSubTasks");
									}
								}
							}
				        }
				
				},
			series: [{
				
				data: countAST,
					
			},
			{
				name: "Number of alerts",
				 data: mainArrTemp,
				 colorByPoint: true,
				 dataLabels: {
					useHTML: true,
					align: 'left',
					
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					 formatter: function() {
						return '<span style="position: absolute;"><br/>'+Highcharts.numberFormat(this.percentage,2)+'%'+' '+'('+this.y+')</span>';
					} 
					
				}  
				
			}]
		}); 
		}else{
			$('#assignedSubTasksGraphView').html("No Data Available")
		}
		
			 
}
	
	
	//district Level Filter
	function getDistIdListForDistFilter(globalDepartmentId){
	 $("#DistrictNamesId").html('');
		var searchType = getSearchType();
		var alertType = getAlertType(); 
		
		var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : 1,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,    
		govtDepartmentId : globalDepartmentId,
		parentGovtDepartmentScopeId : 5,
		group : "status",
		alertType:alertType,//alertType=(alert/subTask)
		searchType :searchType,//locationLevel="scopeWise" or statusOverview="statusWise"
		callCenterArr:callCenterGlobalArr
		}
		$.ajax({
		type:'GET',                  
		url: 'getDistIdListForDistFilterAction.action',
		data: {task :JSON.stringify(jsObj)}     
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#DistrictNamesId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#DistrictNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		});    
	}
	
	//division wise district names Filter
	function getDistIdListForDivisionFilter(globalDepartmentId){
	$("#DivisionDistNamesId").html('');
	var searchType = getSearchType();
	var alertType = getAlertType();
	
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : newspapersGlobalArr,
    chanelIdArr : channelGlobalArr,    
    govtDepartmentId : globalDepartmentId,
    parentGovtDepartmentScopeId : 6,
	group : "status",
	alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType,//locationLevel="scopeWise" or statusOverview="statusWise"
	callCenterArr:callCenterGlobalArr
    }
    $.ajax({
    type:'GET',                  
    url: 'getDistIdListForDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#DivisionDistNamesId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#DivisionDistNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}

	//division wise division names 
	function getDivisionIdListForDivisionFilter(globalDepartmentId,districtId){
		$("#DivisionNamesId").html('');
		
		var searchType = getSearchType();
		var alertType = getAlertType();
			

		var jsObj ={
			fromDate:currentFromDate,
			toDate:currentToDate,
			stateId : 1,
			paperIdArr : newspapersGlobalArr,
			chanelIdArr : channelGlobalArr,    
			govtDepartmentId : globalDepartmentId,
			parentGovtDepartmentScopeId : 6,
			districtWorkLocationId : districtId, //district id here
			group : "status",
			alertType:alertType,//alertType=(alert/subTask)
			searchType :searchType,//locationLevel="scopeWise" or statusOverview="statusWise"
			callCenterArr:callCenterGlobalArr
		}
		$.ajax({
		type:'GET',                  
		url: 'getDivisionIdListForDivisionFilterAction.action',
		data: {task :JSON.stringify(jsObj)}     
		}).done(function(result){
			if(result !=null && result.length>0){
					$("#DivisionNamesId").append('<option value="0">Select Division</option>');
					for(var i in result){
						$("#DivisionNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
				}
		});    
}

	
//sub division wise district names
function getDistrictIdListForSubDivisionFilter(globalDepartmentId){
	$("#SubDivisionDistNamesId").html('');
 
	var searchType = getSearchType();
	var alertType = getAlertType();
	
		
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : 1,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,    
		govtDepartmentId : globalDepartmentId,
		parentGovtDepartmentScopeId : 7,
		group : "status",
		alertType:alertType,//alertType=(alert/subTask)
		searchType :searchType,//locationLevel="scopeWise" or statusOverview="statusWise"
		callCenterArr:callCenterGlobalArr
    }
    $.ajax({
    type:'GET',                  
    url: 'getDistrictIdListForSubDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#SubDivisionDistNamesId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#SubDivisionDistNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}
//sub division wise division names 
function getDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId){
	
	$("#SubDivisionDiviNamesId").html('');
	
	var searchType = getSearchType();
	var alertType = getAlertType();
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : 1,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,    
		govtDepartmentId : globalDepartmentId,
		parentGovtDepartmentScopeId : 7,
		districtWorkLocationId : districtId,//district id here, default 0
		group : "status",
		alertType:alertType,//alertType=(alert/subTask)
		searchType :searchType,//locationLevel="scopeWise" or statusOverview="statusWise"
		callCenterArr:callCenterGlobalArr
    }
    $.ajax({
    type:'GET',                  
    url: 'getDivisionIdListForSubDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#SubDivisionDiviNamesId").append('<option value="0">Select Division</option>');
				for(var i in result){
					$("#SubDivisionDiviNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}
//sub division  wise sub division names 
function getSubDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId,districtDivisionId){
	
	$("#SubDivisionNamesId").html('');
	
	var searchType = getSearchType();
	var alertType = getAlertType();
    var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : 1,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,     
		govtDepartmentId : globalDepartmentId,
		parentGovtDepartmentScopeId : 7,
		districtWorkLocationId :districtId, //district id here ,default 0
		divisionWorkLocationId :districtDivisionId, //division id here ,default 0
		group : "status",
		alertType:alertType,//alertType=(alert/subTask)
		searchType :searchType,//locationLevel="scopeWise" or statusOverview="statusWise"
		callCenterArr:callCenterGlobalArr
    }
    $.ajax({
    type:'GET',                  
    url: 'getSubDivisionIdListForSubDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
    if(result !=null && result.length>0){
			$("#SubDivisionNamesId").append('<option value="0">Select SubDivision</option>');
			for(var i in result){
				$("#SubDivisionNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
			}
		}
    });    
}
//For District Level
var golablSelectedValue ="statusOverView";
function getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,disWiseSubLeveltLevelType,golablSelectedValue){
	
	$("#districtLevelSubOrdinarteDetails").html(spinner);
	console.log(disWiseSubLeveltLevelType)
	
   if(disWiseSubLeveltLevelType == null || disWiseSubLeveltLevelType == ""){
	   disWiseSubLeveltLevelType =[];
   }
    var jObj = {
		fromDate : currentFromDate, 
		toDate : currentToDate,
		stateId:1,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,  
		parentGovtDepartmentScopeId:5,
		govtDepartmentId:globalDepartmentId,
		sortType:sortingType,
		order:orderType,
		districtWorkLocationId:districtId,
		divisionWorkLocationId:0,
		subDivisionWorkLocationId:0,
		group:"status",//status only
		alertType:alertType,//alertType=(alert/subTask)
		searchType :searchType,//locationLevel="scopeWise" or statusOverview="statusWise"
		subLevels:disWiseSubLeveltLevelType,
		callCenterArr:callCenterGlobalArr
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictOfficeGraphicalViewAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			$("#districtLevelSubOrdinarteDetails").html('')
			buildStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(result,searchType,golablSelectedValue)
      });
  
}

function buildStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(result,searchType,golablSelectedValue){

	if(searchType == "statusWise"){
		//$("#distWiseDistLevelId").html('');
		if(result !=null && result.length>0){
			var locationNamesArrDistrict=[];
			 var pendingAlertArr = [];
				 var notifiedAlertArr = [];
				 var actionInProgessAlertArr = [];
				 var completedAlertArr = [];
				 var unblTRslvAlertArr = [];
				 var actionNotRequiredAlertArr = [];
				 var duplicateAlertArr = [];
				 var WronglyMappedDesignationArr = [];
				 var WronglyMappedDepartmentArr = [];
				 var RejoinderArr = [];
				 var Incomplete = [];
				 var Closed = [];
			for(var i in result){
					if(result[i].subLevels !=null &&  result[i].subLevels.length>0){
						distWiseDistLevelArr = result[i].subLevels;
					}
					
				locationNamesArrDistrict.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						 if(result[i].subList[j].id==1){
							 pendingAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id}); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==11){
							 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==12){
							 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						
						
						
					}
					
				}
			
			
			var mainJosnObjArrDistrict = [];
			   if(pendingAlertArr != null && pendingAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Pending',data:pendingAlertArr,color:"#ff4c64"});  
			  }
			   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Notified',data:notifiedAlertArr,color:"#EFA5B6"});  
			  }
			  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#FFCB7F"});  
			  }
			  if(completedAlertArr != null && completedAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Completed',data:completedAlertArr,color:"#85CA8B"});  
			  }
			  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#C6A3A9"});  
			  }
			  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#9698C8"});  
			  }
			  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Duplicate',data:duplicateAlertArr,color:"#DEC6E0"});  
			  }
			   if(WronglyMappedDesignationArr != null && WronglyMappedDesignationArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Duplicate',data:WronglyMappedDesignationArr,color:"#FE9900"});  
			  }
			   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Duplicate',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
			  }
			   if(RejoinderArr != null && RejoinderArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Duplicate',data:RejoinderArr,color:"#82CA9C"});  
			  } if(Incomplete != null && Incomplete.length > 0){
				mainJosnObjArrDistrict.push({name:'Duplicate',data:Incomplete,color:"#C9AC82"});  
			  }if(Closed != null && Closed.length > 0){
				mainJosnObjArrDistrict.push({name:'Closed',data:Closed,color:"#ababab"});  
			  }
		
		
		}	
			var heightOfDiv = locationNamesArrDistrict.length ;
			if(heightOfDiv >9){
				heightOfDiv = heightOfDiv * 50;
				$("#districtLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrict
				var id = 'districtLevelSubOrdinarteDetails';
				var type = {
					type: 'bar',
					backgroundColor:'transparent'
					
				}
				var legend = {
					verticalAlign:'top',
					enabled: true
				}
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					labels: {
							enabled:false
						},
					stackLabels: {
						//useHTML: true,
						//align: 'left',
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
						},
						 formatter: function() {
							
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						} 
						
					}
					
				}
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: locationNamesArrDistrict
				}
				var plotOptions =  {
					 bar: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var value = (this.extra).split("-");
										var value = (this.extra).split("-");
										var statusId = value[0];
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=0;
										var distrctId = value[3];
										getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,distrctId);
									}
								}
							}
				        }
				}
				var tooltip = {
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
				};
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
			
				 $.each($('#districtLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;"); 
					$(this).attr("onclick","getlevelAndStatusWiseClickForDistrict(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");		
					//$(this).attr("class","getTotaldistrictCls");    
					//$(this).attr("attr_district_id",result[index].id);         
					//$(this).attr("attr_district_name",result[index].name);	
                   //$(this).attr("attr_total_count",result[index].totalCount);		
				});
		}else{
			$("#districtLevelSubOrdinarteDetails").html('No Data Available');
		}
	if(golablSelectedValue == "statusOverView"){
		distWiseDistLevelValues();
	}
	}else if(searchType == "scopeWise"){
		
		if(result !=null && result.length>0){
			
			var locationNamesArrDistrictOverView=[];
				var stateArr = [];
				 var goneArr = [];
				 var regionArr = [];
				 var circleArr = [];
				 var districtArr = [];
				 var divisionArr = [];
				 var subDivisionArr = [];
				 var mandalArr = [];
				 var municipalityArr = [];
				 var panchayatArr = [];
				
			for(var i in result){
				locationNamesArrDistrictOverView.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						 if(result[i].subList[j].id==1){
							 stateArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id}); 
						}else if(result[i].subList[j].id==2){
							 goneArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==3){
							 regionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==4){
							 circleArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==5){
							 districtArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==6){
							 divisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==7){
							 subDivisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						
						
						
					}
					
				}
			
			
			
			var mainJosnObjArrDistrictOverview = [];
			   if(stateArr != null && stateArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'State',data:stateArr,color:"#957ADB"});  
			  }
			   if(goneArr != null && goneArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Gone',data:goneArr,color:"#EEEFF0"});  
			  }
			  if(regionArr != null && regionArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Region',data:regionArr,color:"#0065FE"});  
			  }
			  if(circleArr != null && circleArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Circle',data:circleArr,color:"#BCF0E1"});  
			  }
			  if(districtArr != null && districtArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'District',data:districtArr,color:"#FE6603"});  
			  }
			  if(divisionArr != null && divisionArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Division',data:divisionArr,color:"#C8A11A"});  
			  }
			  if(subDivisionArr != null && subDivisionArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Sub-Division',data:subDivisionArr,color:"#4546B6"});  
			  }
			   if(mandalArr != null && mandalArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Mandal',data:mandalArr,color:"#CC329A"});  
			  }
			   if(municipalityArr != null && municipalityArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Municipality',data:municipalityArr,color:"#A0400D"});  
			  }
			   if(panchayatArr != null && panchayatArr.length > 0){
				mainJosnObjArrDistrictOverview.push({name:'Panchayat',data:panchayatArr,color:"#663198"});  
			  } 
		
		}
			
			var heightOfDiv = locationNamesArrDistrictOverView.length ;
			if(heightOfDiv >25){
				heightOfDiv = heightOfDiv * 36;
				$("#districtLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrictOverview
				var id = 'districtLevelSubOrdinarteDetails';
				var type = {
					type: 'bar',
					backgroundColor:'transparent'
					
				}
				var legend = {
					verticalAlign:'top',
					enabled: true
				}
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					labels: {
							enabled:false
						},
					stackLabels: {
						//useHTML: true,
						//align: 'left',
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
						},
						 formatter: function() {
							
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						} 
						
					}
					
				}
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: locationNamesArrDistrictOverView
				}
				var plotOptions =  {
					 bar: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var value = (this.extra).split("-");
										var statusId = 0;
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=value[0];
										var districtId = value[3]
										getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,districtId);
									}
								}
							}
				        }
				}
				var tooltip = {
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
				};
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
			
				 $.each($('#districtLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					//$(this).attr("class","getTotaldistrictCls");   
					$(this).attr("onclick","getlevelAndStatusWiseClickForDistrict(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");	
					//$(this).attr("attr_district_id",result[index].id);         
					//$(this).attr("attr_district_name",result[index].name);	
					//$(this).attr("attr_total_count",result[index].totalCount);		
				});
				
		}else{
			$("#districtLevelSubOrdinarteDetails").html('No Data Available');
		}
		
		
	}
	
}

function distWiseDistLevelValues(){
	$("#distWiseDistLevelId").html('');
	if(distWiseDistLevelArr !=null && distWiseDistLevelArr.length>0){
		$("#distWiseDistLevelId").append("<option value='0'>Select Level</option>");
			for(var i in distWiseDistLevelArr){
				$("#distWiseDistLevelId").append("<option value="+distWiseDistLevelArr[i].id+">"+distWiseDistLevelArr[i].name+"</option>");
			}
	}
	
}
//For Division Level
var divisionSelectedValue="divisionStatusValue";
function getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,divWiseSubLeveltLevelType,divisionSelectedValue){
	
	$("#divisionLevelSubOrdinarteDetails").html(spinner);
	
    if(divWiseSubLeveltLevelType == null || divWiseSubLeveltLevelType == ""){
		divWiseSubLeveltLevelType =[];
	}
    var jObj = {
		fromDate : currentFromDate, 
		toDate : currentToDate,
		stateId:1,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,  
		parentGovtDepartmentScopeId:6,
		govtDepartmentId:globalDepartmentId,
		sortType:sortingType,
		order:orderType,
		districtWorkLocationId:districtId,
		divisionWorkLocationId:districtDivisionId,
		subDivisionWorkLocationId:0,
		group:"status",//status only
		alertType:alertType,//alertType=(alert/subTask)
		searchType :searchType,//locationLevel="scopeWise" or statusOverview="statusWise"
		subLevels:divWiseSubLeveltLevelType,
		callCenterArr:callCenterGlobalArr
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictOfficeGraphicalViewAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			$("#divisionLevelSubOrdinarteDetails").html('');
			buildStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(result,searchType,divisionSelectedValue);
      });
  
}

function buildStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(result,searchType,divisionSelectedValue){
	
	if(searchType == "statusWise"){
		if(result !=null && result.length>0){
			var locationNamesArrDivision=[];
			 var pendingAlertArr = [];
				 var notifiedAlertArr = [];
				 var actionInProgessAlertArr = [];
				 var completedAlertArr = [];
				 var unblTRslvAlertArr = [];
				 var actionNotRequiredAlertArr = [];
				 var duplicateAlertArr = [];
				 var WronglyMappedDesignationArr = [];
				 var WronglyMappedDepartmentArr = [];
				 var RejoinderArr = [];
				 var Incomplete = [];
				 var Closed = [];
			for(var i in result){
				if(result[i].subLevels !=null &&  result[i].subLevels.length>0){
					divisionWiseDistLevelArr = result[i].subLevels;
					
				}
				 locationNamesArrDivision.push(result[i].name)
					
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						if(result[i].subList[j].id==1){
							 pendingAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id}); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==11){
							 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==12){
							 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						
						
						
					}
					
				}
			
			
			var mainJosnObjArrDivision = [];
			   if(pendingAlertArr != null && pendingAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Pending',data:pendingAlertArr,color:"#ff4c64"});  
			  }
			   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Notified',data:notifiedAlertArr,color:"#EFA5B6"});  
			  }
			  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#FFCB7F"});  
			  }
			  if(completedAlertArr != null && completedAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Completed',data:completedAlertArr,color:"#85CA8B"});  
			  }
			  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#C6A3A9"});  
			  }
			  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#9698C8"});  
			  }
			  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
				mainJosnObjArrDivision.push({name:'Duplicate',data:duplicateAlertArr,color:"#DEC6E0"});  
			  }
			   if(WronglyMappedDesignationArr != null && WronglyMappedDesignationArr.length > 0){
				mainJosnObjArrDivision.push({name:'Duplicate',data:WronglyMappedDesignationArr,color:"#FE9900"});  
			  }
			   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
				mainJosnObjArrDivision.push({name:'Duplicate',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
			  }
			   if(RejoinderArr != null && RejoinderArr.length > 0){
				mainJosnObjArrDivision.push({name:'Duplicate',data:RejoinderArr,color:"#82CA9C"});  
			  } if(Incomplete != null && Incomplete.length > 0){
				mainJosnObjArrDivision.push({name:'Duplicate',data:Incomplete,color:"#C9AC82"});  
			  }if(Closed != null && Closed.length > 0){
				mainJosnObjArrDivision.push({name:'Closed',data:Closed,color:"#ababab"});  
			  }
		
		}
			
			var heightOfDiv = locationNamesArrDivision.length ;
			if(heightOfDiv >9){
				heightOfDiv = heightOfDiv * 50;
				$("#divisionLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDivision
				var id = 'divisionLevelSubOrdinarteDetails';
				var type = {
					type: 'bar',
					backgroundColor:'transparent'
					
				}
				var legend = {
					verticalAlign:'top',
					enabled: true
				}
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					labels: {
							enabled:false
						},
					stackLabels: {
						//useHTML: true,
						//align: 'left',
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
						},
						 formatter: function() {
							
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						} 
						
					}
					
				}
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: locationNamesArrDivision
				}
				var plotOptions =  {
					 bar: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var value = (this.extra).split("-");
										var value = (this.extra).split("-");
										var statusId = value[0];
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=0;
										var divisionId =value[3];
										getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,divisionId);
									}
								}
							}
				        }
				}
				var tooltip = {
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
				};
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
				
				$.each($('#divisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");  
					$(this).attr("onclick","getlevelAndStatusWiseClickForDivision(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");		
					//$(this).attr("class","getTotaldivisionCls");    
					//$(this).attr("attr_district_id",result[index].id);         
					//$(this).attr("attr_district_name",result[index].name);	
					//$(this).attr("attr_total_count",result[index].totalCount);		
				});
		}else{
			$("#divisionLevelSubOrdinarteDetails").html('No Data Available');
		}
	if(divisionSelectedValue == "divisionStatusValue"){
		divisionWiseDistLevelValues();
	}
	}else if(searchType == "scopeWise"){
		
		if(result !=null && result.length>0){
			
			var locationNamesArrDivisionOverView=[];
				var stateArr = [];
				 var goneArr = [];
				 var regionArr = [];
				 var circleArr = [];
				 var districtArr = [];
				 var divisionArr = [];
				 var subDivisionArr = [];
				 var mandalArr = [];
				 var municipalityArr = [];
				 var panchayatArr = [];
				
			for(var i in result){
				
				 locationNamesArrDivisionOverView.push(result[i].name)
			
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						if(result[i].subList[j].id==1){
							 stateArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id}); 
						}else if(result[i].subList[j].id==2){
							 goneArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==3){
							 regionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==4){
							 circleArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==5){
							 districtArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==6){
							 divisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==7){
							 subDivisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						
						
						
					}
					
				}
			
			
			
			var mainJosnObjArrDivisionOverview = [];
			   if(stateArr != null && stateArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'State',data:stateArr,color:"#957ADB"});  
			  }
			   if(goneArr != null && goneArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Gone',data:goneArr,color:"#EEEFF0"});  
			  }
			  if(regionArr != null && regionArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Region',data:regionArr,color:"#0065FE"});  
			  }
			  if(circleArr != null && circleArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Circle',data:circleArr,color:"#BCF0E1"});  
			  }
			  if(districtArr != null && districtArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'District',data:districtArr,color:"#FE6603"});  
			  }
			  if(divisionArr != null && divisionArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Division',data:divisionArr,color:"#C8A11A"});  
			  }
			  if(subDivisionArr != null && subDivisionArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Sub-Division',data:subDivisionArr,color:"#4546B6"});  
			  }
			   if(mandalArr != null && mandalArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Mandal',data:mandalArr,color:"#CC329A"});  
			  }
			   if(municipalityArr != null && municipalityArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Municipality',data:municipalityArr,color:"#A0400D"});  
			  }
			   if(panchayatArr != null && panchayatArr.length > 0){
				mainJosnObjArrDivisionOverview.push({name:'Panchayat',data:panchayatArr,color:"#663198"});  
			  } 
		
		}
			
			var heightOfDiv = locationNamesArrDivisionOverView.length ;
			if(heightOfDiv >25){
				heightOfDiv = heightOfDiv * 36;
				$("#divisionLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDivisionOverview
				var id = 'divisionLevelSubOrdinarteDetails';
				var type = {
					type: 'bar',
					backgroundColor:'transparent'
					
				}
				var legend = {
					verticalAlign:'top',
					enabled: true
				}
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					labels: {
							enabled:false
						},
					stackLabels: {
						//useHTML: true,
						//align: 'left',
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
						},
						 formatter: function() {
							
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						} 
						
					}
					
				}
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: locationNamesArrDivisionOverView
				}
				var plotOptions =  {
					 bar: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var value = (this.extra).split("-");
										var statusId = 0;
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=value[0];
										var divisionId =value[3];
										getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,divisionId);
									}
								}
							}
				        }
				}
				var tooltip = {
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
				};
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
			
				$.each($('#divisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");  
					$(this).attr("onclick","getlevelAndStatusWiseClickForDivision(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");	
					//$(this).attr("class","getTotaldivisionCls");    
					//$(this).attr("attr_district_id",result[index].id);         
					//$(this).attr("attr_district_name",result[index].name);	
					//$(this).attr("attr_total_count",result[index].totalCount);	
				});
		}else{
			$("#divisionLevelSubOrdinarteDetails").html('No Data Available');
		}
	}
	
}


function divisionWiseDistLevelValues(){
	$("#divisionWiseDistLevelId").html('');
	if(divisionWiseDistLevelArr !=null && divisionWiseDistLevelArr.length>0){
		$("#divisionWiseDistLevelId").append("<option value='0'>Select Level</option>");
		for(var i in divisionWiseDistLevelArr){
			$("#divisionWiseDistLevelId").append("<option value="+divisionWiseDistLevelArr[i].id+">"+divisionWiseDistLevelArr[i].name+"</option>");
		}
	}
	
}
//For Sub Division Level
function getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,districtSubDivisionId){
	
	$("#SubdivisionLevelSubOrdinarteDetails").html(spinner);
	
	if(subLevels == null || subLevels == [])
		subLevels = [];
    var jObj = {
		fromDate : currentFromDate, 
		toDate : currentToDate,
		stateId:1,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,  
		parentGovtDepartmentScopeId:7,
		govtDepartmentId:globalDepartmentId,
		sortType:sortingType,
		order:orderType,
		districtWorkLocationId:districtId,
		divisionWorkLocationId:districtDivisionId,
		subDivisionWorkLocationId:districtSubDivisionId,
		group:"status",//status only
		alertType:alertType,//alertType=(alert/subTask)
		searchType :searchType,//locationLevel="scopeWise" or statusOverview="statusWise"
		subLevels:subLevels,
		callCenterArr:callCenterGlobalArr
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictOfficeGraphicalViewAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			$("#SubdivisionLevelSubOrdinarteDetails").html('');
			 buildStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(result,searchType)
      });
  
}

function buildStateThenGovtDeptScopeWiseAlertCountForSubDivisionLevel(result,searchType){
	
	if(searchType == "statusWise"){
		if(result !=null && result.length>0){
			var locationNamesArrsubDivision=[];
			 var pendingAlertArr = [];
				 var notifiedAlertArr = [];
				 var actionInProgessAlertArr = [];
				 var completedAlertArr = [];
				 var unblTRslvAlertArr = [];
				 var actionNotRequiredAlertArr = [];
				 var duplicateAlertArr = [];
				 var WronglyMappedDesignationArr = [];
				 var WronglyMappedDepartmentArr = [];
				 var RejoinderArr = [];
				 var Incomplete = [];
				 var Closed = [];
			for(var i in result){
				
				 locationNamesArrsubDivision.push(result[i].name)
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						if(result[i].subList[j].id==1){
							 pendingAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id}); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==11){
							 Incomplete.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==12){
							 Closed.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						
						
						
					}
					
				}
			
			
			var mainJosnObjArrsubDivision = [];
			   if(pendingAlertArr != null && pendingAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Pending',data:pendingAlertArr,color:"#ff4c64"});  
			  }
			   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Notified',data:notifiedAlertArr,color:"#EFA5B6"});  
			  }
			  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#FFCB7F"});  
			  }
			  if(completedAlertArr != null && completedAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Completed',data:completedAlertArr,color:"#85CA8B"});  
			  }
			  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#C6A3A9"});  
			  }
			  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#9698C8"});  
			  }
			  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Duplicate',data:duplicateAlertArr,color:"#DEC6E0"});  
			  }
			   if(WronglyMappedDesignationArr != null && WronglyMappedDesignationArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Duplicate',data:WronglyMappedDesignationArr,color:"#FE9900"});  
			  }
			   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Duplicate',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
			  }
			   if(RejoinderArr != null && RejoinderArr.length > 0){
				mainJosnObjArrsubDivision.push({name:'Duplicate',data:RejoinderArr,color:"#82CA9C"});  
			  } if(Incomplete != null && Incomplete.length > 0){
				mainJosnObjArrsubDivision.push({name:'Duplicate',data:Incomplete,color:"#C9AC82"});  
			  }if(Closed != null && Closed.length > 0){
				mainJosnObjArrsubDivision.push({name:'Closed',data:Closed,color:"#ababab"});  
			  }
		
		}
			
			var heightOfDiv = locationNamesArrsubDivision.length ;
			if(heightOfDiv >9){
				heightOfDiv = heightOfDiv * 50;
				$("#SubdivisionLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrsubDivision
				var id = 'SubdivisionLevelSubOrdinarteDetails';
				var type = {
					type: 'bar',
					backgroundColor:'transparent'
					
				}
				var legend = {
					verticalAlign:'top',
					enabled: true
				}
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					labels: {
							enabled:false
						},
					stackLabels: {
						//useHTML: true,
						//align: 'left',
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
						},
						 formatter: function() {
							
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						} 
						
					}
					
				}
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: locationNamesArrsubDivision
				}
				var plotOptions =  {
					 bar: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var value = (this.extra).split("-");
										var value = (this.extra).split("-");
										var statusId = value[0];
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=0;
										var subDivisionId = value[3];
										getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,subDivisionId);
									}
								}
							}
				        }
				}
				var tooltip = {
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
				};
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
				
			
			$.each($('#SubdivisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
				//	$(this).attr("class","getTotalSubdivisionCls"); 
					$(this).attr("onclick","getlevelAndStatusWiseClickForSubDivision(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')"); 					
					/* $(this).attr("attr_district_id",result[index].id);         
					$(this).attr("attr_district_name",result[index].name);	
					$(this).attr("attr_total_count",result[index].totalCount);	 */
				});
		}else{
			$("#SubdivisionLevelSubOrdinarteDetails").html('No Data Available');
		}
	}else if(searchType == "scopeWise"){
		
		if(result !=null && result.length>0){
			
			var locationNamesArrsubDivisionOverView=[];
				var stateArr = [];
				 var goneArr = [];
				 var regionArr = [];
				 var circleArr = [];
				 var districtArr = [];
				 var divisionArr = [];
				 var subDivisionArr = [];
				 var mandalArr = [];
				 var municipalityArr = [];
				 var panchayatArr = [];
				
			for(var i in result){
				subdivisionLevelDistrictId = result[i].id;
				subdivisionLevelTotalCount = result[i].totalCount;
				subdivisionLevelDistrictName = result[i].name;
				 locationNamesArrsubDivisionOverView.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						if(result[i].subList[j].id==1){
							 stateArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id}); 
						}else if(result[i].subList[j].id==2){
							 goneArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==3){
							 regionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==4){
							 circleArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==5){
							 districtArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==6){
							 divisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==7){
							 subDivisionArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push({"y":result[i].subList[j].count,"extra":result[i].subList[j].id+"-"+result[i].subList[j].name+"-"+result[i].subList[j].count+"-"+result[i].id});
						}
						
						
						
					}
					
				}
			
			
			
			var mainJosnObjArrsubDivisionOverview = [];
			   if(stateArr != null && stateArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'State',data:stateArr,color:"#957ADB"});  
			  }
			   if(goneArr != null && goneArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Gone',data:goneArr,color:"#EEEFF0"});  
			  }
			  if(regionArr != null && regionArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Region',data:regionArr,color:"#0065FE"});  
			  }
			  if(circleArr != null && circleArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Circle',data:circleArr,color:"#BCF0E1"});  
			  }
			  if(districtArr != null && districtArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'District',data:districtArr,color:"#FE6603"});  
			  }
			  if(divisionArr != null && divisionArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Division',data:divisionArr,color:"#C8A11A"});  
			  }
			  if(subDivisionArr != null && subDivisionArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Sub-Division',data:subDivisionArr,color:"#4546B6"});  
			  }
			   if(mandalArr != null && mandalArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Mandal',data:mandalArr,color:"#CC329A"});  
			  }
			   if(municipalityArr != null && municipalityArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Municipality',data:municipalityArr,color:"#A0400D"});  
			  }
			   if(panchayatArr != null && panchayatArr.length > 0){
				mainJosnObjArrsubDivisionOverview.push({name:'Panchayat',data:panchayatArr,color:"#663198"});  
			  } 
		
		
		}	
			var heightOfDiv = locationNamesArrsubDivisionOverView.length ;
			if(heightOfDiv >25){
				heightOfDiv = heightOfDiv * 36;
				$("#SubdivisionLevelSubOrdinarteDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrsubDivisionOverview
				var id = 'SubdivisionLevelSubOrdinarteDetails';
				var type = {
					type: 'bar',
					backgroundColor:'transparent'
					
				}
				var legend = {
					verticalAlign:'top',
					enabled: true
				}
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					labels: {
							enabled:false
						},
					stackLabels: {
						//useHTML: true,
						//align: 'left',
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
						},
						 formatter: function() {
							
							//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
							//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
							return (this.total);
						} 
						
					}
					
				}
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: locationNamesArrsubDivisionOverView
				}
				var plotOptions =  {
					 bar: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
					},
					series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {
										var value = (this.extra).split("-");
										var statusId = 0;
										var statusName = value[1];
										var totalCount = value[2];
										var scopeId=value[0];
										var subDivisionId = value[3];
										getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,subDivisionId);
									}
								}
							}
				        }
				}
				var tooltip = {
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
				};
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
				
			
				$.each($('#SubdivisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){
					$(this).attr("style","cursor:pointer;");    
					//$(this).attr("onclick","getTotalSubdivisionCls");    
					$(this).attr("onclick","getlevelAndStatusWiseClickForSubDivision(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");    
					/* $(this).attr("attr_district_id",result[index].id);         
					$(this).attr("attr_district_name",result[index].name);	
					$(this).attr("attr_total_count",result[index].totalCount);	 */
				});
		}else{
			$("#SubdivisionLevelSubOrdinarteDetails").html('No Data Available');
		}
	}
	
}

$(document).on("click",".overAllCount",function(){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
	
	var alertIdArr =[];
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count");
	var alertType = $(this).attr("attr_alert_type");
	//alertIdArr=($(this).attr("attr_overcunt").split(','));
	var jObj = {
		govtDepDesigOffcrId : globalgovtDeptDesigOffcrId,
		govtOfficerId : globalgovtOfficerId,
		countType: "overAll",
		alertType : alertType,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,  
		callCenterArr:callCenterGlobalArr		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelOfficerClickAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
	});
});

$(document).on("click",".todayCountCls",function(){
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		
	var alertIdArr =[];
	var statusName = $(this).attr("attr_name");
	var totalCount = $(this).attr("attr_total_count")
	//alertIdArr.push(parseInt($(this).attr("attr_todayCunt")));
	var alertType = $(this).attr("attr_alert_type");
	var jObj = {
		govtDepDesigOffcrId : globalgovtDeptDesigOffcrId,
		govtOfficerId : globalgovtOfficerId,
		countType: "today",
		alertType : alertType,
		paperIdArr : newspapersGlobalArr,
		chanelIdArr : channelGlobalArr,  
		callCenterArr:callCenterGlobalArr	
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelOfficerClickAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
	});
});

function getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,districtId){

	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
   
	var searchType = getSearchType();
	var alertType = getAlertType();
	var sortingType = getDistrictWiseSorting().districtSortingType; // 'value1'
	var orderType = getDistrictWiseSorting().districtOrderType; // 'value2'
	
	var disWiseSubLeveltLevelType = getdisWiseSubLeveltLevelType();
    var jObj = {
		fromDate : currentFromDate, 
		toDate : currentToDate,
		stateId:1,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		parentGovtDepartmentScopeId:5,
		govtDepartmentId:globalDepartmentId,
		sortType:sortingType,
		order:orderType,
		districtWorkLocationId:districtId,
		divisionWorkLocationId:0,
		subDivisionWorkLocationId:0,
		group:"status",
		alertType:alertType,
		searchType :searchType,
		statusId : statusId,
		govtDeprtMentScopeId : scopeId,
		callCenterArr:callCenterGlobalArr,
		subLevels:disWiseSubLeveltLevelType
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictLevelWiseClickAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			if(result != null && result.length > 0){
				$("#totalAlertsModalTabId").html('');
				buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
			}else{
				$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
			}
		});
  
}
/*  $(document).on("click",".getTotaldistrictCls",function(){ 
	
	var districtId = $(this).attr("attr_district_id");
	var districtName = $(this).attr("attr_district_name");
	var totalCount = $(this).attr("attr_total_count");
	
	getlevelAndStatusWiseClickForDistrict(0,districtName,totalCount,0,districtId)
});  */

function getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,divisionId){
	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
 
	var searchType = getSearchType();
	var alertType = getAlertType();
	var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
	var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
	
	var divWiseSubLeveltLevelType = getdivWiseSubLeveltLevelType();	

    var jObj = {
		fromDate : currentFromDate, 
		toDate : currentToDate,
		stateId:1,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		parentGovtDepartmentScopeId:6,
		govtDepartmentId:globalDepartmentId,
		sortType:sortingType,
		order:orderType,
		districtWorkLocationId:0,
		divisionWorkLocationId:divisionId,
		subDivisionWorkLocationId:0,
		group:"status",
		alertType:alertType,
		searchType :searchType,
		statusId : statusId,
		govtDeprtMentScopeId : scopeId,
		callCenterArr:callCenterGlobalArr,
		subLevels:divWiseSubLeveltLevelType
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictLevelWiseClickAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			if(result != null && result.length > 0){
				$("#totalAlertsModalTabId").html('');
				buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
			}else{
				$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
			}
		});
  
}
 /* $(document).on("click",".getTotaldivisionCls",function(){ 
	
	var divisionId = $(this).attr("attr_district_id");
	var divisionName = $(this).attr("attr_district_name");
	var totalCount = $(this).attr("attr_total_count");
	
	getlevelAndStatusWiseClickForDivision(0,divisionName,totalCount,0,divisionId)
}); */ 

function getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,subDivisionId){

	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
    
	var searchType = getSearchType();
	var alertType = getAlertType();
	var sortingType = getSubDivision().subSortingType; // 'value1'
	var orderType = getSubDivision().subOrderType; // 'value2'
	
	
    var jObj = {
		fromDate : currentFromDate, 
		toDate : currentToDate,
		stateId:1,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		parentGovtDepartmentScopeId:7,
		govtDepartmentId:globalDepartmentId,
		sortType:sortingType,
		order:orderType,
		districtWorkLocationId:0,
		divisionWorkLocationId:0,
		subDivisionWorkLocationId:subDivisionId,
		group:"status",
		alertType:alertType,
		searchType :searchType,
		statusId : statusId,
		govtDeprtMentScopeId : scopeId,
		callCenterArr:callCenterGlobalArr,
		subLevels:subLevels
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictLevelWiseClickAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			if(result != null && result.length > 0){
				$("#totalAlertsModalTabId").html('');
				buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
			}else{
				$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
			}
		});
  
}
/*  $(document).on("click",".getTotalsubdivisionCls",function(){ 
	
	var subDivisionId = $(this).attr("attr_district_id");
	var subDivisionName = $(this).attr("attr_district_name");
	var totalCount = $(this).attr("attr_total_count");
	
	getlevelAndStatusWiseClickForSubDivision(0,subDivisionName,totalCount,0,subDivisionId)
}); */ 


function getDistrictLevelDeptWiseAlertClick(StatusId,name,totalCount,clickType)
{
	$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
		
		var jsObj = {
		govtDeptDesigOffceId : globalgovtDeptDesigOffcrId,
		govtOffceId : globalgovtOfficerId,
		statusId : StatusId,
		formDate:currentFromDate, 
		toDate: currentToDate,
		clickType:clickType, //
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		callCenterArr:callCenterGlobalArr
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseAlertClickAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			$("#totalAlertsModalTabId").html('');
			buildAlertDtlsBasedOnStatusClick(result,name,totalCount);
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
	});
}

