var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
onLoadCallsAMU();
	
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
		
		getDistrictOfficerAlertsCountView();
	});
	
	function onLoadCallsAMU(){
		getDistrictOfficerAlertsCountView();
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
	var fromDateStr= "";
	var toDateStr= "";
	//var userId=19601;
    var jObj ={
      //userId:userId,
	  startDate:currentFromDate,
	  endDate:currentToDate
    }
    $.ajax({
      type:'GET',
      url: 'getDistrictOfficerAlertsCountViewAction.action',
      data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#myAlertsDivID").html('');
		$("#mySubTasksDivID").html('');
		$("#assignedSubTasksDivID").html('');
		buildDistrictOfficerAlertsCountView(result);
    });
}
var overAllAlertIds =[];
var totalCoutAlertIds =[];
var globalUserLevelId;
var globalUserLevelValues = [];
var globalDepartmentId;

function buildDistrictOfficerAlertsCountView(result){
	var str='';
	if(result !=null){
		globalUserLevelId = result.levelId;
		globalDepartmentId = result.departmentId;
		if(result.levelValues != null && result.levelValues.length > 0)
			globalUserLevelValues=result.levelValues;
	}
		if(result !=null && result.list1 !=null && result.list1.length>0){
		str+='<div class="row">';
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0){
				if(result.list1[0].todayCount !=null && result.list1[0].todayCount !=0 && esult.list1[0].todayCount>0){
					totalCoutAlertIds.push(result.list1[0].todayAlertIds);
				str+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' attr_name ="TODAY" attr_total_count = "'+result.list1[0].todayCount+'">TODAY <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].todayCount+'</span></p>';
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
				str+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+' attr_name ="OVERALL" attr_total_count = "'+result.list1[0].overAllCnt+'">OVERALL <span class="pull-right badge" style="cursor:pointer">'+result.list1[0].overAllCnt+'</span></p>';
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
	}
	$("#myAlertsDivID").html(str);
	
	var mainStatusArr=[];
	var count = [];
	var percArr = [];
	if(result !=null && result.list1 !=null && result.list1.length>0){
		for(var i in result.list1){
			var statusArr=[];
			var statusNamesArr=[];
			var uniqCnt = {};
			var color;
			var totalAlertCnt = result.list1[0].overAllCnt;
			statusArr.push(result.list1[i].count);
			statusNamesArr.push(result.list1[i].name);
			percArr.push(result.list1[i].perc);
			color=result.list1[i].color;
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].count),color:"#D3D3D3"};
			count.push(uniqCnt);
			var obj1={
				data: count    
			}
			var obj={
						name: 'No.of Alerts',
						data: statusArr,
						color: color
					}
			
			mainStatusArr.push(obj1);
			mainStatusArr.push(obj);
			
		}
	}
	
		var data = mainStatusArr
			var id = 'myAlertGraphView';
			var type = {
				type: 'bar',
				backgroundColor:'transparent'
				
			}
			var legend = {
				enabled: false,
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
					alertPerc: percArr,
					//align: 'left',
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					 formatter: function() {
						
						//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
						return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
					} 
					
				}
				
			}
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: statusNamesArr
			}
			var plotOptions =  {
	             series: {
					stacking: 'normal',
					pointWidth: 30,
					gridLineWidth: 15
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

			
		var str1='';
		if(result !=null && result.list2 !=null && result.list2.length>0){
		str1+='<div class="row">';
			str1+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list2[0].todayCount !=null && result.list2[0].todayCount !=0){
			if(result.list2[0].todayCount !=null && result.list2[0].todayCount !=0 && result.list2[0].todayCount>0){
				  totalCoutAlertIds.push(result.list2[0].todayAlertIds);
				str1+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' >TODAY <span class="pull-right badge">'+result.list2[0].todayCount+'</span></p>';
			}else{
				str1+='<p class="pad_5">TODAY <span class="pull-right badge">'+result.list2[0].todayCount+'</span></p>';
			}
			}else{
				str1+='<p class="pad_5">TODAY <span class="pull-right badge">0</span></p>';
			}
			str1+='<hr class="m_0"/>';
			if(result.list2[0].overAllCnt !=null && result.list2[0].overAllCnt !=0){
			 if(result.list2[0].overAllCnt !=null && result.list2[0].overAllCnt !=0 && result.list2[0].overAllCnt>0){
				overAllAlertIds.push(result.list2[0].overAllAlertIds);
				str1+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+'>OVERALL <span class="pull-right badge">'+result.list2[0].overAllCnt+'</span></p>';
			 }else{
				 str1+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list2[0].overAllCnt+'</span></p>';
			 }
			}else{
				str1+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str1+='<div id="mySubTasksGraphView" style="height:250px"></div>';
			str1+='</div>';
		str1+='</div>';
	}
	$("#mySubTasksDivID").html(str1);
	
	var mainStatusArrST=[];
	var countST = [];
	var percArrST = [];
	if(result !=null && result.list2 !=null && result.list2.length>0){
		for(var i in result.list2){
			var statusArrST=[];
			var statusNamesArrST=[];
			var colorST;
			var uniqCnt = {};
			var totalAlertCnt = result.list2[0].overAllCnt;
			statusArrST.push(result.list2[i].count);
			statusNamesArrST.push(result.list2[i].name);
			percArrST.push(result.list2[i].perc);
			colorST=result.list2[i].color;
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list2[i].count),color:"#D3D3D3"};
			countST.push(uniqCnt);
			var obj1={
				data: countST    
			}
			var obj={
						name: 'No.of Alerts',
						data: statusArrST,
						color: colorST
					}
			
			mainStatusArrST.push(obj1);
			mainStatusArrST.push(obj);
			
		}
	}
	
		var dataST = mainStatusArrST
			var idST = 'mySubTasksGraphView';
			var type = {
				type: 'bar',
				backgroundColor:'transparent'
				
			}
			var legend = {
				enabled: false,
			}
			var yAxisST = {
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
					alertPerc: percArrST,
					//align: 'left',
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					 formatter: function() {
						
						//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
						return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
					} 
					
				}
				
			}
			var xAxisST = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: statusNamesArrST
			}
			
			
			highcharts(idST,type,xAxisST,yAxisST,legend,dataST,plotOptions,tooltip); 
			
			var str2='';
		if(result !=null && result.list3 !=null && result.list3.length>0){
		str2+='<div class="row">';
			str2+='<div class="col-sm-12 col-xs-12 col-md-12">';
			if(result.list3[0].todayCount !=null && result.list3[0].todayCount !=0){
				if(result.list3[0].todayCount !=null && result.list3[0].todayCount !=0 && esult.list3[0].todayCount>0){
					totalCoutAlertIds.push(result.list3[0].todayAlertIds);
				str2+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' >TODAY <span class="pull-right badge">'+result.list3[0].todayCount+'</span></p>';
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
				str2+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+'>OVERALL <span class="pull-right badge">'+result.list3[0].overAllCnt+'</span></p>';
				}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">'+result.list3[0].overAllCnt+'</span></p>';
				}
			}else{
				str2+='<p class="pad_5">OVERALL <span class="pull-right badge">0</span></p>';
			}
				str2+='<div id="assignedSubTasksGraphView" style="height:250px"></div>';
			str2+='</div>';
		str2+='</div>';
	}
	$("#assignedSubTasksDivID").html(str2);
	
	var mainStatusArrAST=[];
	var countAST = [];
	var percArrAST = [];
	if(result !=null && result.list3 !=null && result.list3.length>0){
		for(var i in result.list3){
			var statusArrAST=[];
			var statusNamesArrAST=[];
			var colorAST;
			var uniqCnt = {};
			var totalAlertCnt = result.list3[0].overAllCnt;
			statusArrAST.push(result.list3[i].count);
			statusNamesArrAST.push(result.list3[i].name);
			percArrAST.push(result.list3[i].perc);
			colorAST = result.list3[i].color;
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list3[i].count),color:"#D3D3D3"};
			countAST.push(uniqCnt);
			var obj1={
				data: countAST    
			}
			var obj={
						name: 'No.of Alerts',
						data: statusArrAST,
						color: colorAST
					}
			
			mainStatusArrAST.push(obj1);
			mainStatusArrAST.push(obj);
			
		}
	}
	
		var dataAST = mainStatusArrAST
			var idAST = 'assignedSubTasksGraphView';
			var type = {
				type: 'bar',
				backgroundColor:'transparent'
				
			}
			var legend = {
				enabled: false,
			}
			var yAxisAST = {
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
					alertPerc: percArrAST,
					//align: 'left',
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
					},
					 formatter: function() {
						
						//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
						return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
					} 
					
				}
				
			}
			var xAxisAST = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: statusNamesArrAST
			}
			
			
			highcharts(idAST,type,xAxisAST,yAxisAST,legend,dataAST,plotOptions,tooltip); 
			alert(globalDepartmentId)
			getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,"scopeWise","alert","desc","count",0);
			getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,"scopeWise","alert","desc","count",0,0);
			getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,"scopeWise","alert","desc","count",0,0,0);
			getDistIdListForDistFilter(globalDepartmentId);
			getDistIdListForDivisionFilter(globalDepartmentId);
			getDistrictIdListForSubDivisionFilter(globalDepartmentId);  
}

//getSubOrdinateAlertsOverview();
function getSubOrdinateAlertsOverview(){
	//var userId="";
	var fromDateStr= "";
	var toDateStr= "";
	var govtScopeIds=[];
	var locationValues=[];
	var desigIds=[];
	var priorityId="";
	
	var jObj = {
	fromDateStr : currentFromDate, 
	toDateStr : currentToDate,
	govtScopeIds : govtScopeIds,
	locationValues : locationValues,
	desigIds : desigIds,
	priorityId : priorityId
	}
	$.ajax({
	      type:'GET',
	      url: 'getSubOrdinateAlertsOverviewAction.action',
		  data: {task :JSON.stringify(jObj)}
	    }).done(function(result){
		});
 }
//getSubOrdinateLevel();
function getSubOrdinateLevel(){
	var designationId =0;
	var jObj = {
		designationId : designationId
	}
	$.ajax({
      type:'GET',
      url: 'getSubOrdinateLevelAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
	});
	
}


function viewAlertHistory()
{
	$("#alertManagementPopupBody1").html(spinner)
	var jsObj ={
		alertId : 11346
	}
	$.ajax({
		type:'GET',
		url: 'viewAlertHistoryAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length> 0)
		{
			alertHistory(result);
		}else{
			$("#alertManagementPopupBody1").html("NO DATA AVAILABLE")
		}
	});
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
	var totalCount = $(this).attr("attr_total_count")
	alertIdArr.push(parseInt($(this).attr("attr_overCunt")));
	var jObj = {
		alertIdArr: alertIdArr		
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
	alertIdArr.push(parseInt($(this).attr("attr_todayCunt")));
	var jObj = {
		alertIdArr: alertIdArr		
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

//getDistrictLevelDeptWiseFlterClick();
function getDistrictLevelDeptWiseFlterClick()
{
	
	var jsObj = {
		deptId : 49,
		levelId : 5,
		statusId : 2
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseFlterClickAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		//buildAssignedOfficersDetailsForAlert(result);
	});
}


//getDistrictLevelWiseClick(5) // graphical click for statusOverview and Locationlevel
function getDistrictLevelWiseClick(parentGovtDepartmentScopeId){
	var stateId;
    var paperIdList =[];
    var chanelIdList =[];
    var govtDepartmentId;
    var parentGovtDepartmentScopeId;
    var sortType;
    var order;
    var alertType;
    var districtWorkLocationId;
    var divisionWorkLocationId;
    var subDivisionWorkLocationId;
    var group;
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:parentGovtDepartmentScopeId,
    govtDepartmentId:49,
    sortType:"count",
    order:"asc",
    districtWorkLocationId:0,
    divisionWorkLocationId:0,
    subDivisionWorkLocationId:0,
    group:"status",
    alertType:"alert",
    searchType :"statusWise",
	statusId : 2,
	govtDeprtMentScopeId : 0
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictLevelWiseClickAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
      });
  
}

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
	
	function getSubDivisionWiseSorting(){
		 var subdivisionSortingType = ''; 
		 var subdivisionOrderType = ''; 
		$('.locationWiseSortingSubDivision li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  subdivisionSortingType = $(this).attr("attr_sorting_type");
			  subdivisionOrderType = $(this).attr("attr_order_type");
			 }
		});
		return {
			subdivisionSortingType : subdivisionSortingType,
			subdivisionOrderType :subdivisionOrderType
			};
	}
	
	
	
	$(document).on("click",".switch-btn li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		
		var searchType = $(this).attr("attr_type");
		
		getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,"alert","desc","count",0);
		getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,"alert","desc","count",0,0);
		getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,"alert","desc","count",0,0,0);
	});
	$(document).on("click",".switch-btn-alertType li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var alertType = $(this).attr("attr_type");
		var searchType = getSearchType();
		
		getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,"desc","count",0);
		getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,"desc","count",0,0);
		getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,"desc","count",0,0,0);
		
	});
	
	//district
	
	function getDistIdListForDistFilter(globalDepartmentId){
		
		
	 $("#DistrictNamesId").html('');
		var searchType = getSearchType();
		var alertType = getAlertType();
		var paperIdArr = [];
		var chanelIdArr = [];

		var jsObj ={
		fromDate:currentFromDate,
		toDate:currentToDate,
		stateId : 1,
		paperIdArr : paperIdArr,
		chanelIdArr : chanelIdArr,    
		govtDepartmentId : globalDepartmentId,
		parentGovtDepartmentScopeId : 5,
		group : "status",
		alertType:alertType,//alertType=(alert/subTask)
		searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
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
	
	$(document).on("click",".locationWiseSortingDistrict li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var searchType = getSearchType();
		var alertType = getAlertType();
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,sortingType,orderType,0);
	});
	
	$(document).on("change",".locationWiseSortingDist",function(){
		var searchType = getSearchType();
		var alertType = getAlertType();
		var sortingType = getDistrictWiseSorting().districtSortingType; // 'value1'
		var orderType = getDistrictWiseSorting().districtOrderType; // 'value2'
		var districtId =  $("#DistrictNamesId").val();
		getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId);
	});
	
	//division
	
	function getDistIdListForDivisionFilter(globalDepartmentId){
	$("#DivisionDistNamesId").html('');
	var searchType = getSearchType();
	var alertType = getAlertType();
	var paperIdArr = [];
	var chanelIdArr = [];
	
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : globalDepartmentId,
    parentGovtDepartmentScopeId : 6,
	group : "status",
	alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
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

	

	$(document).on("click",".locationWiseSortingDivision li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var searchType = getSearchType();
		var alertType = getAlertType();
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,0,0);
	});
	$(document).on("change",".locationWiseDiviDistOnChange",function(){
		var districtId = $(this).val();
		var searchType = getSearchType();
		var alertType = getAlertType();
		var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
		var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
			
		getDivisionIdListForDivisionFilter(globalDepartmentId,districtId);
		getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,0);
		
	});
	$(document).on("change",".locationWiseDiviOnChange",function(){
		var searchType = getSearchType();
		var alertType = getAlertType();
		var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
		var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
		
		var districtId =  $("#DivisionDistNamesId").val();
		var districtDivisionId =  $("#DivisionNamesId").val();
		getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId);
	});
	
	
	function getDivisionIdListForDivisionFilter(globalDepartmentId,districtId){
	$("#DivisionNamesId").html('');
	
		var searchType = getSearchType();
		var alertType = getAlertType();
		var paperIdArr = [];
		var chanelIdArr = [];

    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : departmentId,
    parentGovtDepartmentScopeId : 6,
	districtWorkLocationId : districtId, //district id here
	group : "status",
	alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
    type:'GET',                  
    url: 'getDivisionIdListForDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#DivisionNamesId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#DivisionNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}


	//subdivision
	
	//sub division
//district filter

function getDistrictIdListForSubDivisionFilter(globalDepartmentId){
	$("#SubDivisionDistNamesId").html('');
  var paperIdArr = [];
  var chanelIdArr = [];

  var searchType = getSearchType();
		var alertType = getAlertType();
		
		
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : globalDepartmentId,
    parentGovtDepartmentScopeId : 7,
	group : "status",
	alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
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


	$(document).on("click",".locationWiseSortingSubDivision li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var searchType = getSearchType();
		var alertType = getAlertType();
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,0,0,0);
	});
	$(document).on("change",".locationWiseSubDiviDistOnChange",function(){
		
		var districtId =$("#SubDivisionDistNamesId").val();
		getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,0,0);
		getDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId);  
	});
	$(document).on("change",".locationWiseSubDiviDiviOnChange",function(){
		
		var districtId =$("#SubDivisionDistNamesId").val();
		var districtDivisionId =$("#SubDivisionDiviNamesId").val();
		var sortingType = getSubDivisionWiseSorting().subDivisionSortingType; // 'value1'
		var orderType = getSubDivisionWiseSorting().subDivisionOrderType; // 'value2'
		var searchType = getSearchType();
		var alertType = getAlertType();
		
		getSubDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId,districtDivisionId);  
		getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,0);
	});
	$(document).on("change",".locationWiseSubDiviOnChange",function(){
	
		var districtId =$("#SubDivisionDistNamesId").val();
		var districtDivisionId =$("#SubDivisionDiviNamesId").val();
		var sortingType = getSubDivisionWiseSorting().subDivisionSortingType; // 'value1'
		var orderType = getSubDivisionWiseSorting().subDivisionOrderType; // 'value2'
		var searchType = getSearchType();
		var alertType = getAlertType();
		
		getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,districtDivisionId);
		
	});
	

	//division filter

function getDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId){
	
	$("#SubDivisionDiviNamesId").html('');
  var paperIdArr = [];
  var chanelIdArr = [];
var searchType = getSearchType();
		var alertType = getAlertType();
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : globalDepartmentId,
    parentGovtDepartmentScopeId : 7,
  districtWorkLocationId : districtId,//district id here, default 0
  group : "status",
  alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
    type:'GET',                  
    url: 'getDivisionIdListForSubDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
		if(result !=null && result.length>0){
				$("#SubDivisionDiviNamesId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#SubDivisionDiviNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}
//sub division filter

function getSubDivisionIdListForSubDivisionFilter(globalDepartmentId,districtId,districtDivisionId){
	
	$("#SubDivisionNamesId").html('');
  var paperIdArr = [];
  var chanelIdArr = [];
var searchType = getSearchType();
		var alertType = getAlertType();
    var jsObj ={
    fromDate:currentFromDate,
    toDate:currentToDate,
    stateId : 1,
    paperIdArr : paperIdArr,
    chanelIdArr : chanelIdArr,    
    govtDepartmentId : globalDepartmentId,
    parentGovtDepartmentScopeId : 7,
  districtWorkLocationId :districtId, //district id here ,default 0
  divisionWorkLocationId :districtDivisionId, //division id here ,default 0
  group : "status",
  alertType:alertType,//alertType=(alert/subTask)
	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
    type:'GET',                  
    url: 'getSubDivisionIdListForSubDivisionFilterAction.action',
    data: {task :JSON.stringify(jsObj)}     
    }).done(function(result){
    if(result !=null && result.length>0){
				$("#SubDivisionNamesId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#SubDivisionNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
    });    
}

function getDistrictOfficeGraphicalViewForDistrict(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId){
	
    var paperIdList =[];
    var chanelIdList =[];
    
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:5,
    govtDepartmentId:globalDepartmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:districtId,
    divisionWorkLocationId:0,
    subDivisionWorkLocationId:0,
    group:"status",//status only
    alertType:alertType,//alertType=(alert/subTask)
  	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictOfficeGraphicalViewAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			buildStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(result,searchType)
      });
  
}

function buildStateThenGovtDeptScopeWiseAlertCountForDistrictLevel(result,searchType){
	
	if(searchType == "statusWise"){
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
				
				 locationNamesArrDistrict.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						 if(result[i].subList[j].id==1){
							 pendingAlertArr.push(result[i].subList[j].count); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push(result[i].subList[j].count);
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==11){
							 Incomplete.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==12){
							 Closed.push(result[i].subList[j].count);
						}
						
						
						
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
					 series: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
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
		}else{
			$("#districtLevelSubOrdinarteDetails").html('No Data Available');
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
							 stateArr.push(result[i].subList[j].count); 
						}else if(result[i].subList[j].id==2){
							 goneArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==3){
							 regionArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==4){
							 circleArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==5){
							 districtArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==6){
							 divisionArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==7){
							 subDivisionArr.push(result[i].subList[j].count);
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push(result[i].subList[j].count);
						}
						
						
						
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
					 series: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
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
		}else{
			$("#districtLevelSubOrdinarteDetails").html('No Data Available');
		}
	}
	
}

function getDistrictOfficeGraphicalViewForDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId){
	
    var paperIdList =[];
    var chanelIdList =[];
    
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:6,
    govtDepartmentId:globalDepartmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:districtId,
    divisionWorkLocationId:districtDivisionId,
    subDivisionWorkLocationId:0,
    group:"status",//status only
    alertType:alertType,//alertType=(alert/subTask)
  	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictOfficeGraphicalViewAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
			buildStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(result,searchType);
      });
  
}


function buildStateThenGovtDeptScopeWiseAlertCountForDivisionLevel(result,searchType){
	
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
				
				 locationNamesArrDivision.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						 if(result[i].subList[j].id==1){
							 pendingAlertArr.push(result[i].subList[j].count); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push(result[i].subList[j].count);
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==11){
							 Incomplete.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==12){
							 Closed.push(result[i].subList[j].count);
						}
						
						
						
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
					 series: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
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
		}else{
			$("#divisionLevelSubOrdinarteDetails").html('No Data Available');
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
							 stateArr.push(result[i].subList[j].count); 
						}else if(result[i].subList[j].id==2){
							 goneArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==3){
							 regionArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==4){
							 circleArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==5){
							 districtArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==6){
							 divisionArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==7){
							 subDivisionArr.push(result[i].subList[j].count);
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push(result[i].subList[j].count);
						}
						
						
						
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
					 series: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
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
		}else{
			$("#divisionLevelSubOrdinarteDetails").html('No Data Available');
		}
	}
	
}

function getDistrictOfficeGraphicalViewForSubDivision(globalDepartmentId,searchType,alertType,sortingType,orderType,districtId,districtDivisionId,districtDivisionId){
	
    var paperIdList =[];
    var chanelIdList =[];
   
	
    var jObj = {
    fromDate : currentFromDate, 
    toDate : currentToDate,
    stateId:1,
    paperIdArr:paperIdList,
    chanelIdArr:chanelIdList,
    parentGovtDepartmentScopeId:7,
    govtDepartmentId:globalDepartmentId,
    sortType:sortingType,
    order:orderType,
    districtWorkLocationId:districtId,
    divisionWorkLocationId:districtDivisionId,
    subDivisionWorkLocationId:districtDivisionId,
    group:"status",//status only
    alertType:alertType,//alertType=(alert/subTask)
  	searchType :searchType//locationLevel="scopeWise" or statusOverview="statusWise"
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictOfficeGraphicalViewAction.action',
        data: {task :JSON.stringify(jObj)}
        }).done(function(result){
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
							 pendingAlertArr.push(result[i].subList[j].count); 
						}else if(result[i].subList[j].id==2){
							 notifiedAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==3){
							 actionInProgessAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==4){
							 completedAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==5){
							 unblTRslvAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==6){
							 actionNotRequiredAlertArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==7){
							 duplicateAlertArr.push(result[i].subList[j].count);
						}
						else if(result[i].subList[j].id==8){
							 WronglyMappedDesignationArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==9){
							 WronglyMappedDepartmentArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==10){
							 RejoinderArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==11){
							 Incomplete.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==12){
							 Closed.push(result[i].subList[j].count);
						}
						
						
						
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
					 series: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
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
				
				 locationNamesArrsubDivisionOverView.push(result[i].name)
				
				if(result[i].subList !=null &&  result[i].subList.length>0){
					for(var j in result[i].subList){
							
						 if(result[i].subList[j].id==1){
							 stateArr.push(result[i].subList[j].count); 
						}else if(result[i].subList[j].id==2){
							 goneArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==3){
							 regionArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==4){
							 circleArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==5){
							 districtArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==6){
							 divisionArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==7){
							 subDivisionArr.push(result[i].subList[j].count);
						}
						else if(result[i].subList[j].id==8){
							 mandalArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==9){
							 municipalityArr.push(result[i].subList[j].count);
						}else if(result[i].subList[j].id==10){
							 panchayatArr.push(result[i].subList[j].count);
						}
						
						
						
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
					 series: {
						stacking: 'normal',
						pointWidth: 30,
						gridLineWidth: 15
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
		}else{
			$("#SubdivisionLevelSubOrdinarteDetails").html('No Data Available');
		}
	}
	
}