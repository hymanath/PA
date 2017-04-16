var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
var deptIdArr =[];
var paperIdArr =[];
var chanelIdArr =[];
var printIdArr =[];
var electronicIdArr =[];
var globalDepartmentIdsArr=[];
//check
var globalUserLevelId;
var globalUserLevelValues = [];	
var globalDesignationId;	
var globalOfficerIds = [];	
var globalGovtDeptDesigOffcrIds = [];	
onLoadCalls();

function getAlertType(){
		 var alertType = ''; 
		$('.switch-btn-alertType li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  alertType = $(this).attr("attr_type");
			 }
		});
		return alertType;
	}
	function getgroupType(){
		 var groupType = ''; 
		$('.switch-btn li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  groupType = $(this).attr("attr_type");
			 }
		});
		return groupType;
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
	
	function getDeptDetails(){
	var jsObj = {};
	$.ajax({
      type:'GET',
      url: 'getDeptDetailsAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			globalUserLevelId = result.levelId;
			if(result.todayAlertIds != null && result.todayAlertIds.length > 0)
			  globalDesignationId = result.todayAlertIds[0];
		   
			
			globalDepartmentIdsArr=result.deptIds;
			
			if(result.levelValues != null && result.levelValues.length > 0)
				globalUserLevelValues=result.levelValues;
			
			if(result.govtOfficerIds != null && result.govtOfficerIds.length > 0)
			  globalOfficerIds = result.govtOfficerIds;
		  
		  if(result.govtDeptDesigOffcrIds != null && result.govtDeptDesigOffcrIds.length > 0)
			  globalGovtDeptDesigOffcrIds = result.govtDeptDesigOffcrIds;
			
		}
		
		stateLevelDeptOfficerStatusOverview();
		stateLevelDeptOfficerLocationLevelOverview();
		stateLevelDeptOfficerDepartmentWiseAlertsView();
		
	});
}

function onLoadCalls(){
	getDeptDetails();
	getIASOfficerMyAlertsCountMainView();
	getIASOfficerMySubTasksCountView();
	getIASOfficerMyAssignedSubTasksCountView();
	
	
	
	
	//status and location wise click start
		$(document).on("click",".switch-btn li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			
			var groupType = $(this).attr("attr_type");
			var departmentId = $(this).attr("attr_department_id");
			var alertType = getAlertType();
			$("#DistrictNamesId").val(0);
			$("#DivisionDistNamesId").val(0);
			
			$("#DivisionNamesId").html('');
			$("#DivisionNamesId").append('<option value="0">Select Division</option>');
			
			$("#SubDivisionDistNamesId").val(0);
			$("#SubDivisionDiviNamesId").html('');
			$("#SubDivisionDiviNamesId").append('<option value="0">Select Division</option>');
			$("#SubDivisionNamesId").html('');
			$("#SubDivisionNamesId").append('<option value="0">Select Sub Division</option>');
			
			getStatusWiseForStateLevel(departmentId,"desc","count",alertType,groupType,0,0,0)
			getStatusWiseForZoneLevel(departmentId,"desc","count",alertType,groupType,0,0,0)
			getStatusWiseForDistrictLevel(departmentId,"desc","count",alertType,groupType,0,0,0);
			getStatusWiseForDivisionLevel(departmentId,"desc","count",alertType,groupType,0,0,0);
			getStatusWiseForSubDivisionLevel(departmentId,"desc","count",alertType,groupType,0,0,0);
		});
		$(document).on("click",".switch-btn-alertType li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var alertType = $(this).attr("attr_type");
			var groupType = getgroupType();
			var departmentId = $(this).attr("attr_department_id");
			$("#DistrictNamesId").val(0);
			$("#DivisionDistNamesId").val(0);
			$("#DivisionNamesId").html('');
			$("#DivisionNamesId").append('<option value="0">Select Division</option>');
			$("#SubDivisionDistNamesId").val(0);
			$("#SubDivisionDiviNamesId").html('');
			$("#SubDivisionDiviNamesId").append('<option value="0">Select Division</option>');
			$("#SubDivisionNamesId").html('');
			$("#SubDivisionNamesId").append('<option value="0">Select Sub Division</option>');
			
			getStatusWiseForStateLevel(departmentId,"desc","count",alertType,groupType,0,0,0)
			getStatusWiseForZoneLevel(departmentId,"desc","count",alertType,groupType,0,0,0)
			getStatusWiseForDistrictLevel(departmentId,"desc","count",alertType,groupType,0,0,0);
			getStatusWiseForDivisionLevel(departmentId,"desc","count",alertType,groupType,0,0,0);
			getStatusWiseForSubDivisionLevel(departmentId,"desc","count",alertType,groupType,0,0,0);
			
			
		});
		//status and location wise click start
		
		//zone level click
		
		$(document).on("click",".locationWiseSortingZone li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var alertType = $(this).attr("attr_type");
			var groupType = getgroupType();
			var departmentId = $(this).attr("attr_department_id");
			var sortingType = $(this).attr("attr_sorting_type");
			var orderType = $(this).attr("attr_order_type");
						
			getStatusWiseForZoneLevel(departmentId,sortingType,orderType,alertType,groupType,0,0,0)
			
			
			
		});
		
		//district wise graph click start
		$(document).on("click",".locationWiseSortingDistrict li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var departmentId = $(this).attr("attr_department_id");
			var groupType = getgroupType();
			var alertType = getAlertType();
			var sortingType = $(this).attr("attr_sorting_type");
			var orderType = $(this).attr("attr_order_type");
			$("#DistrictNamesId").val(0);
			
			getStatusWiseForDistrictLevel(departmentId,sortingType,orderType,alertType,groupType,0,0,0);
			
		});
		
		$(document).on("change",".locationWiseDistOnChange",function(){
			var departmentId = $(this).attr('attr_department_id');
			
    alert(departmentId);//this will show the value of the atribute of that option.
			var groupType = getgroupType();
			var alertType = getAlertType();
			var sortingType = getDistrictWiseSorting().districtSortingType; // 'value1'
			var orderType = getDistrictWiseSorting().districtOrderType; // 'value2'
			var districtId =  $("#DistrictNamesId").val();
			getStatusWiseForDistrictLevel(departmentId,sortingType,orderType,alertType,groupType,districtId,0,0);
			
		});
		
		//district wise graph click End
		
		//division wise graph click start
		$(document).on("click",".locationWiseSortingDivision li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var departmentId = $(this).attr("attr_department_id");
			var groupType = getgroupType();
			var alertType = getAlertType();
			var sortingType = $(this).attr("attr_sorting_type");
			var orderType = $(this).attr("attr_order_type");
			$("#DivisionDistNamesId").val(0);
			$("#DivisionNamesId").html('');
			$("#DivisionNamesId").append('<option value="0">Select Division</option>');
			
			getStatusWiseForDivisionLevel(departmentId,sortingType,orderType,alertType,groupType,0,0,0);
			
		});
		$(document).on("change",".locationWiseDiviDistOnChange",function(){
			
			var departmentId = $(this).attr('attr_department_id');
			
			var districtId = $(this).val();
			var groupType = getgroupType();
			var alertType = getAlertType();
			var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
			var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
			getAllDivisionDetails(districtId);
			getStatusWiseForDivisionLevel(departmentId,sortingType,orderType,alertType,groupType,districtId,0,0);
			
			
			
		});
		$(document).on("change",".locationWiseDiviOnChange",function(){
			var departmentId = $(this).attr('attr_department_id');
			
			var groupType = getgroupType();
			var alertType = getAlertType();
			var sortingType = getDivisionWiseSorting().divisionSortingType; // 'value1'
			var orderType = getDivisionWiseSorting().divisionOrderType; // 'value2'
			
			var districtId =  $("#DivisionDistNamesId").val();
			var districtDivisionId =  $("#DivisionNamesId").val();
			
			getStatusWiseForDivisionLevel(departmentId,sortingType,orderType,alertType,groupType,districtId,districtDivisionId,0);
			
		});
		
		//division wise graph click End
		
		//sub-division wise graph click start
		$(document).on("click",".locationWiseSortingSubDivision li",function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var departmentId = $(this).attr('attr_department_id');
			var groupType = getgroupType();
			var alertType = getAlertType();
			var sortingType = $(this).attr("attr_sorting_type");
			var orderType = $(this).attr("attr_order_type");
			$("#SubDivisionDistNamesId").val(0);
		
			
			$("#SubDivisionDiviNamesId").html('');
			$("#SubDivisionDiviNamesId").append('<option value="0">Select Division</option>');
			$("#SubDivisionNamesId").html('');
			$("#SubDivisionNamesId").append('<option value="0">Select Sub Division</option>');
			getStatusWiseForSubDivisionLevel(departmentId,sortingType,orderType,alertType,groupType,0,0,0);
			
		});
		$(document).on("change",".locationWiseSubDiviDistOnChange",function(){
			var departmentId = $(this).attr('attr_department_id');
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			
			var groupType = getgroupType();
			var alertType = getAlertType();
			
			var districtId =$("#SubDivisionDistNamesId").val();
			getAllDivisionDetails(districtId);
			
			getStatusWiseForSubDivisionLevel(departmentId,sortingType,orderType,alertType,groupType,districtId,0,0);
		});
		$(document).on("change",".locationWiseSubDiviDiviOnChange",function(){
			var departmentId = $(this).attr('attr_department_id');
			var districtId =$("#SubDivisionDistNamesId").val();
			var districtDivisionId =$("#SubDivisionDiviNamesId").val();
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			var groupType = getgroupType();
			var alertType = getAlertType();
			getAllDivisionDetails(districtId);
			getAllSubDivisionDetails(districtDivisionId);
			getStatusWiseForSubDivisionLevel(departmentId,sortingType,orderType,alertType,groupType,districtId,districtDivisionId,0);
		});
		$(document).on("change",".locationWiseSubDiviOnChange",function(){
			var departmentId = $(this).attr("attr_department_id");
			var districtId =$("#SubDivisionDistNamesId").val();
			var districtDivisionId =$("#SubDivisionDiviNamesId").val();
			var districtSubDivisionId =$("#SubDivisionNamesId").val();
			var sortingType = getSubDivision().subSortingType; // 'value1'
			var orderType = getSubDivision().subOrderType; // 'value2'
			var searchType = getSearchType();
			var alertType = getAlertType();
			
			getStatusWiseForSubDivisionLevel(departmentId,sortingType,orderType,alertType,groupType,districtId,districtDivisionId,districtSubDivisionId);
			
			
		});
		//sub-division wise graph click end
		
}

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
		onLoadCalls();
	});
	
function getIASOfficerMyAlertsCountMainView(){

    var jsObj ={};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMyAlertsCountMainViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildIASOfficerMyAlertsCountMainView(result);
		
    });
}
function buildIASOfficerMyAlertsCountMainView(result){
	
	
	
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var str='';
		str+='<div class="row">';
		for(var i in result.list1){
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
					str+='<h5 class="" style="font-weight: bold;">TODAY</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
				if(result.list1[i].subList1 !=null && result.list1[i].subList1.length>0){
					for(var j in result.list1[i].subList1){
						str+='<tr>';
						str+='<td>';
						if(result.list1[i].subList1[i].count == 0 || result.list1[i].subList1[i].count == null){
							str+='<p class="pad_3">No Data Available</p>';
						}else{
							str+='<p class="pad_3">'+result.list1[i].subList1[i].name+'<span class="pull-right badge alertCountCls " style="cursor:pointer;" attr_dept_id ="'+result.list1[i].subList1[i].id+'" attr_type="alert" attr_dept_name ="'+result.list1[i].subList1[i].name+'" attr_count ="'+result.list1[i].subList1[i].count+'">'+result.list1[i].subList1[i].count+'</span></p>';
						}
							
						str+='</td>';
						str+='</tr>';
					}
					
				}
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
				
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<h5 class="" style="font-weight: bold;">COMPLETED</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
						if(result.list1[i].subList2 !=null && result.list1[i].subList2.length>0){
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
								if(result.list1[i].subList2[i].count == 0 || result.list1[i].subList2[i].count == null){
									str+='<p class="pad_3">No Data Available</p>';
								}else{
									str+='<p class="pad_3">'+result.list1[i].subList2[i].name+'<span class="pull-right badge alertCountCls " style="cursor:pointer;" attr_type="alert" attr_dept_id ="'+result.list1[i].subList2[i].id+'" attr_dept_name ="'+result.list1[i].subList2[i].name+'" attr_count ="'+result.list1[i].subList2[i].count+'">'+result.list1[i].subList2[i].count+'</span></p>';
								}
									
								str+='</td>';
								str+='</tr>';
							}
						}
					str+='</tbody>';
					str+='</table>';
					str+='</div>';	
					str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<div id="myAlertGraphView" style="height:250px"></div>';
					str+='</div>';	
		}
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
				if(result.list1[i].subList3 !=null && result.list1[i].subList3.length>0){
					for(var j in result.list1[i].subList3){
							var uniqCnt = {};
							var totalAlertCnt = result.list1[0].overAllCnt;
							namesArrAT.push(result.list1[i].subList3[j].name);
							var tempArrAT = {"y":result.list1[i].subList3[j].count,color:result.list1[i].subList3[j].color};
							var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].subList3[j].count),color:"#D3D3D3"};
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
			}
		
		}else{
			 $('#myAlertGraphView').html("No Data Available")
		}
		
		
}
function getIASOfficerMySubTasksCountView(){

    var jsObj ={};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMySubTasksCountViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildIASOfficerMySubTasksCountView(result);
    });
}

function buildIASOfficerMySubTasksCountView(result){
	
	
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var str='';
		str+='<div class="row">';
		for(var i in result.list1){
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
					str+='<h5 class="" style="font-weight: bold;">TODAY</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
				if(result.list1[i].subList1 !=null && result.list1[i].subList1.length>0){
					for(var j in result.list1[i].subList1){
						str+='<tr>';
						str+='<td>';
						if(result.list1[i].subList1[i].count !=null && result.list1[i].subList1[i].count>0){
							str+='<p class="pad_3">'+result.list1[i].subList1[i].name+'<span class="pull-right badge" style="cursor:pointer">'+result.list1[i].subList1[i].count+'</span></p>';
							
						}else{
							str+='<p class="pad_3">No Data Available</p>';
						}
							
						str+='</td>';
						str+='</tr>';
					}
					
				}
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
				
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<h5 class="" style="font-weight: bold;">COMPLETED</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
						if(result.list1[i].subList2 !=null && result.list1[i].subList2.length>0){
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
								if(result.list1[i].subList2[i].count !=null && result.list1[i].subList2[i].count>0){
									str+='<p class="pad_3">'+result.list1[i].subList2[i].name+'<span class="pull-right badge alertCountCls " style="cursor:pointer;" attr_dept_id ="'+result.list1[i].subList2[i].id+'" attr_dept_name ="'+result.list1[i].subList2[i].name+'" attr_count ="'+result.list1[i].subList2[i].count+'">'+result.list1[i].subList2[i].count+'</span></p>';
								}else{
									str+='<p class="pad_3">No Data Available</p>';
								}
									
								str+='</td>';
								str+='</tr>';
							}
						}
					str+='</tbody>';
					str+='</table>';
					str+='</div>';	
					str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<div id="mySubTasksGraphView" style="height:250px"></div>';
					str+='</div>';	
		}
			str+='</div>';
		$("#mySubTasksDivID").html(str);
	}else{
		$("#mySubTasksDivID").html("No Data Available");
	}
	
	if(result !=null && result.list1 !=null && result.list1.length>0){
			var mainArrTempAT=[];
			var namesArrAT=[];
			var countAT = [];
			for(var i in result.list1){
				if(result.list1[i].subList3 !=null && result.list1[i].subList3.length>0){
					for(var j in result.list1[i].subList3){
							var uniqCnt = {};
							var totalAlertCnt = result.list1[0].overAllCnt;
							namesArrAT.push(result.list1[i].subList3[j].name);
							var tempArrAT = {"y":result.list1[i].subList3[j].count,color:result.list1[i].subList3[j].color};
							var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].subList3[j].count),color:"#D3D3D3"};
							countAT.push(uniqCnt);
				
							mainArrTempAT.push(tempArrAT);
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
					$('#mySubTasksGraphView').html("No Data Available")
				}
			}
		
		}else{
			 $('#mySubTasksGraphView').html("No Data Available")
		}
}
function getIASOfficerMyAssignedSubTasksCountView(){
    var jsObj ={};
    $.ajax({
      type:'GET',
      url: 'getIASOfficerMyAssignedSubTasksCountViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildIASOfficerMyAssignedSubTasksCountView(result)
    });
}
function buildIASOfficerMyAssignedSubTasksCountView(result){
	
	
	if(result !=null && result.list1 !=null && result.list1.length>0){
		var str='';
		str+='<div class="row">';
		for(var i in result.list1){
			str+='<div class="col-sm-12 col-xs-12 col-md-12">';
					str+='<h5 class="" style="font-weight: bold;">TODAY</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
				if(result.list1[i].subList1 !=null && result.list1[i].subList1.length>0){
					for(var j in result.list1[i].subList1){
						str+='<tr>';
						str+='<td>';
						if(result.list1[i].subList1[i].count !=null && result.list1[i].subList1[i].count>0){
							str+='<p class="pad_3">'+result.list1[i].subList1[i].name+'<span class="pull-right badge" style="cursor:pointer">'+result.list1[i].subList1[i].count+'</span></p>';
						}else{
								str+='<p class="pad_3">No Data Available</p>';
						}
							
						str+='</td>';
						str+='</tr>';
					}
					
				}
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
				
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<h5 class="" style="font-weight: bold;">COMPLETED</h5>';
					str+='<table class="table  table_styled m_top5">';
					str+='<tbody>';
						if(result.list1[i].subList2 !=null && result.list1[i].subList2.length>0){
							for(var j in result.list1[i].subList2){
								str+='<tr>';
								str+='<td>';
								if(result.list1[i].subList2[i].count !=null &&  result.list1[i].subList2[i].count>0){
									str+='<p class="pad_3">'+result.list1[i].subList2[i].name+'<span class="pull-right badge alertCountCls " style="cursor:pointer;" attr_dept_id ="'+result.list1[i].subList2[i].id+'" attr_dept_name ="'+result.list1[i].subList2[i].name+'" attr_count ="'+result.list1[i].subList2[i].count+'">'+result.list1[i].subList2[i].count+'</span></p>';
								}else{
									str+='<p class="pad_3">No Data Available</p>';
								}
									
								str+='</td>';
								str+='</tr>';
							}
						}
					str+='</tbody>';
					str+='</table>';
					str+='</div>';	
					
					str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top10">';
					str+='<div id="assignedSubTasksGraphView" style="height:250px"></div>';
					str+='</div>';
		}
			str+='</div>';
		$("#assignedSubTasksDivID").html(str);
	}else{
		$("#assignedSubTasksDivID").html("No Data Available");
	}
	
	
		if(result !=null && result.list1 !=null && result.list1.length>0){
			var mainArrTempAT=[];
			var namesArrAT=[];
			var countAT = [];
			for(var i in result.list1){
				if(result.list1[i].subList3 !=null && result.list1[i].subList3.length>0){
					for(var j in result.list1[i].subList3){
							var uniqCnt = {};
							var totalAlertCnt = result.list1[0].overAllCnt;
							namesArrAT.push(result.list1[i].subList3[j].name);
							var tempArrAT = {"y":result.list1[i].subList3[j].count,color:result.list1[i].subList3[j].color};
							var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].subList3[j].count),color:"#D3D3D3"};
							countAT.push(uniqCnt);
				
							mainArrTempAT.push(tempArrAT);
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
					$('#assignedSubTasksGraphView').html("No Data Available")
				}
			}
		
		}else{
			 $('#assignedSubTasksGraphView').html("No Data Available")
		}
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
//State Level view


function stateLevelDeptOfficerStatusOverview(){
	
	
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerStatusOverViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildstateLevelDeptOfficerStatusOverview(result);
		}else{
			$("#statusOverview").html('NO DATA AVAILABLE')
		}
    });
}

function buildstateLevelDeptOfficerStatusOverview(result)
{
	var str='';
	var totalAlert = 0;
	
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="totalAlertGroupByStatusForGovt" style="height:300px"></div>';
			str+='<div id="statusOverViewTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].alertCnt;
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsAlertsCls" attr_type="alert" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'" attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#statusOverview").html(str);
	$("#statusOverViewTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
	var statusOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].name;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		statusOverviewArr.push(obj);
	}
	
	$(function() {
		$("#totalAlertGroupByStatusForGovt").highcharts({
			chart: {
				type: 'pie',
				options3d: {
					enabled: true,
					alpha: 25
				}
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			tooltip: {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 180,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: statusOverviewArr
			}]
		});
	});
}
function stateLevelDeptOfficerLocationLevelOverview(){
	var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerLocationLevelOverviewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length > 0)
		{
			buildstateLevelDeptOfficerLocationLevelOverviewt(result);
		}else{
			$("#levelWiseAlertOverview").html("NO DATA AVAILABLE");
		}
    });
}

function buildstateLevelDeptOfficerLocationLevelOverviewt(result)
{
	var str='';
	var totalAlert = 0;
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="levelWiseAlertOverviewCnt" style="height:300px"></div>';
			str+='<div id="levelWiseAlertOverviewCntTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].alertCnt;
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsAlertsCls" attr_type="alert" attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#levelWiseAlertOverview").html(str);
	$("#levelWiseAlertOverviewCntTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
	var locationOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].name;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		locationOverviewArr.push(obj);
	}
	$(function() {
		$("#levelWiseAlertOverviewCnt").highcharts({
			chart: {
				type: 'pie',
				options3d: {
					enabled: true,
					alpha: 25
				}
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			tooltip: {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 180,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: locationOverviewArr
			}]
		});
	});
}

$(document).on("click",".subTaskViewDts",function(){
		stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick();
});

function stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(){
	var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildstateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(result);
    });
}

function buildstateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(result)
{
	var str='';
	var totalAlert = 0;
	
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="totalAlertGroupByStatusWiseSubTask" style="height:300px"></div>';
			str+='<div id="statusWiseSubTaskOverViewTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].alertCnt;
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].status+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsAlertsCls" attr_type="subTask" attr_status_name="'+result[i].status+'" attr_status_count="'+result[i].alertCnt+'" attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#statusWiseSubTasksOverview").html(str);
	$("#statusWiseSubTaskOverViewTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
	var statusSubTaskOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].status;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		statusSubTaskOverviewArr.push(obj);
	}
	
	$(function() {
		$("#totalAlertGroupByStatusWiseSubTask").highcharts({
			chart: {
				type: 'pie',
				options3d: {
					enabled: true,
					alpha: 25
				}
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			tooltip: {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 180,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: statusSubTaskOverviewArr
			}]
		});
	});
}

$(document).on("click",".subTaskLocViewDts",function(){
		stateLevelDeptOfficerLocationLevelOverviewBySubTasks();
});

function stateLevelDeptOfficerLocationLevelOverviewBySubTasks(){

    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : globalDepartmentIdsArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerLocationLevelOverviewBySubTasksAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildstateLevelDeptOfficerLocationLevelOverviewBySubTasks(result)
    });
}

function buildstateLevelDeptOfficerLocationLevelOverviewBySubTasks(result)
{
	var str='';
	var totalAlert = 0;
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<div id="levelWiseSubTaskAlertOverviewCnt" style="height:300px"></div>';
			str+='<div id="levelWiseSubTaskAlertOverviewCntTotal"></div>';
		str+='</div>';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].alertCnt;
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].name+'</td>';
							str+='<td style="cursor:pointer;" class="getDtlsAlertsCls" attr_type="subTask"  attr_status_name="'+result[i].name+'" attr_status_count="'+result[i].alertCnt+'"  attr_status_id="'+result[i].id+'">'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#levelWiseSubTasksAlertOverview").html(str);
	$("#levelWiseSubTaskAlertOverviewCntTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
	var locationSubTaskOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].name;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		locationSubTaskOverviewArr.push(obj);
	}
	$(function() {
		$("#levelWiseSubTaskAlertOverviewCnt").highcharts({
			chart: {
				type: 'pie',
				options3d: {
					enabled: true,
					alpha: 25
				}
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			tooltip: {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 180,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: locationSubTaskOverviewArr
			}]
		});
	});
}


function stateLevelDeptOfficerDepartmentWiseAlertsView(){
	
	
    var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : [49,1,3,4],  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerDepartmentWiseAlertsViewAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildstateLevelDeptOfficerDepartmentWiseAlertsView(result);
		
    });
}

function buildstateLevelDeptOfficerDepartmentWiseAlertsView(result){
	var str='';
	if(result !=null && result.length>0){
		var totalAlert = 0;
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div class="row">';
			for(var i in result){
				
				str+='<div class="col-sm-4">';
					str+='<div class="panel panel-default">';
						str+='<div class="pad_5">';
							str+='<h4 class="panel-title text-capital fontColor">location level overview</h4>';
						str+='</div>';
						str+='<div class="panel-body">';
							str+='<div class="panel-group" id="accordion'+i+'" role="tablist" aria-multiselectable="true">';
								str+='<div class="panel panel-default">';
									str+='<div class="" role="tab" id="headingOne'+i+'" style="padding: 15px;">';
									 
										str+='<a class ="collapseIconForIAS" role="button" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
										str+=' <h4 class="panel-title"> ALERTS</h4>';
										
										str+='</a>';
									  
									str+='</div>';
									str+='<div id="collapseOne'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+i+'">';
									  str+='<div class="panel-body">';
									  str+='<div id="departmentWiseAlertGraphViewId" style="height:250px"></div>';
									str+='<div id="departmentAlertOverviewCntTotal"></div>';
									  str+='<table class="table tableGraph">';
											str+='<thead>';
												str+='<th>Status</th>';
												str+='<th>Total</th>';
												str+='<th>%</th>';
											str+='</thead>';
											str+='<tbody>';
											 if(result[i].subList2 !=null && result[i].subList2.length>0){
												for(var j in result[i].subList2){
													totalAlert+=result[i].subList2[j].alertCnt;
													str+='<tr>';
														str+='<td><span class="label" style="background-color:'+result[i].subList2[j].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].subList2[j].name+'</td>';
														str+='<td style="cursor:pointer;" >'+result[i].subList2[j].alertCnt+'</td>';
														str+='<td>'+result[i].subList2[j].percentage+'%</td>';
													str+='</tr>';
												}
											}
												
											str+='</tbody>';  
										str+='</table>';
									  str+='</div>';
									str+='</div>';
								str+='</div>';
								
								str+='<div class="panel panel-default">';
									str+='<div class="" role="tab" id="headingTwo'+i+'" style="padding: 15px;">';
									
										str+='<a class="collapsed collapseIconForIAS departmentSubTask" role="button" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseTwo'+i+'" aria-expanded="false" aria-controls="collapseTwo'+i+'">';
										  str+='<h4 class="panel-title">';
										 str+=' SUB TASKS</h4>';
										str+='</a>';
									  
									str+='</div>';
									str+='<div id="collapseTwo'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo'+i+'">';
									  str+='<div class="panel-body">';
										str+='<div id="departmentWiseSubTaskGraphViewId" style="height:250px"></div>';
										str+='<div id="departmentSubTaskOverviewCntTotal"></div>';
										str+='<div id="departmentSubTaskTableView"></div>';
									  str+='</div>';
									str+='</div>';
										str+='<div class="m_top20" style="text-align:center;"><button type="button" class="btn btn-default btn-sm buttonCustomStyle detailedBlockDiv" attr_department_id="'+result[i].id+'" attr_department_name="'+result[i].name+'">Detailed Information</button></div>';
								str+='</div>';
							str+='</div>';
							
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
			str+='</div>';
		str+='</div>';
		$("#departmentWiseAlertsDetailsId").html(str);
		$("#departmentAlertOverviewCntTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>");
	}
	if(result !=null && result.length>0){
		var deptStatusOverviewArr =[];
		for(var i in result)
		{
			if(result[i].subList2 !=null && result[i].subList2.length>0){
				for(var j in result[i].subList2){
					statusPercent = result[i].subList2[j].percentage;
					statusName = result[i].subList2[j].name;
					var cnt = result[i].subList2[j].alertCnt;
					var stsId = result[i].subList2[j].id;
					var colorsId = result[i].subList2[j].color
					//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
					
					var obj = {
						name: statusName,
						y:statusPercent,
						count:cnt,   
						sts:stsId,
						color:colorsId
					}
					deptStatusOverviewArr.push(obj);
				}
			}
		}
		
		
			$("#departmentWiseAlertGraphViewId").highcharts({
				chart: {
					type: 'pie',
					options3d: {
						enabled: true,
						alpha: 25
					}
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				tooltip: {
					useHTML: true,
					backgroundColor: '#FCFFC5', 
					formatter: function() {
						var cnt = this.point.count;
						return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
					}  
				}, 
				plotOptions: {
					series: {
						dataLabels: {
							enabled: false,
							formatter: function() {
								return Math.round(this.percentage*100)/100 + ' %';
							},
							distance: -30,
							color:'black'
						},
						point:{
							events:{
								click:function(){
									getData(this.count,this.sts);     
								}
							}
						}
					},
					pie: {
						innerSize: 130,
						depth: 180,
						dataLabels:{
							enabled: false,
							  formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return Highcharts.numberFormat(this.percentage,1)+ '%';
									}
								} 
						},
						showInLegend: false
					},
				},
				series: [{
					data: deptStatusOverviewArr
				}]
			});
		
	}
}

$(document).on("click",".departmentSubTask",function(){
	stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickss();
});	
function stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickss(){
	var jsObj ={
      fromDate:currentFromDate,
      toDate:currentToDate,
      stateId : 1,
      deptIdArr : deptIdArr,  
      paperIdArr : paperIdArr,
      chanelIdArr :chanelIdArr
    }
    $.ajax({
      type:'GET',
      url: 'stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildstateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickss(result);
    });
}

function buildstateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClickss(result)
{
	var str='';
	var totalAlert = 0;
	
	str+='<div class="row">';
		
			str+='<table class="table tableGraph">';
				str+='<thead>';
					str+='<th>Status</th>';
					str+='<th>Total</th>';
					str+='<th>%</th>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result)
					{	
						totalAlert+=result[i].alertCnt;
						str+='<tr>';
							str+='<td><span class="label" style="background-color:'+result[i].color+';padding:0px 6px;margin-right:5px;"> </span>'+result[i].status+'</td>';
							str+='<td style="cursor:pointer;" >'+result[i].alertCnt+'</td>';
							str+='<td>'+result[i].percentage+'%</td>';
						str+='</tr>';
					}
				str+='</tbody>';  
			str+='</table>';
		
	str+='</div>';
	$("#departmentSubTaskTableView").html(str);
	$("#departmentSubTaskOverviewCntTotal").html("<h4 class='text-center'>TOTAL "+totalAlert+"</h4>")
	var departmentstatusSubTaskOverviewArr =[];
	for(var i in result)
	{
		statusPercent = result[i].percentage;
		statusName = result[i].status;
		var cnt = result[i].alertCnt;
		var stsId = result[i].id;
		var colorsId = result[i].color
		//var color = getColorCodeByStatus(result[i].coreDashBoardVOList[j].organization);
		
		var obj = {
			name: statusName,
			y:statusPercent,
			count:cnt,   
			sts:stsId,
			color:colorsId
		}
		departmentstatusSubTaskOverviewArr.push(obj);
	}
	
	
		$("#departmentWiseSubTaskGraphViewId").highcharts({
			chart: {
				type: 'pie',
				options3d: {
					enabled: true,
					alpha: 25
				}
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			tooltip: {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 180,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			series: [{
				data: departmentstatusSubTaskOverviewArr
			}]
		});
	
}
$(document).on("click",".detailedBlockDiv",function(){
	var departmentId = $(this).attr("attr_department_id");
	var departmentName = $(this).attr("attr_department_name");
	
	getdepartmentWiseDetailedInformation(departmentId,departmentName);
	getAllDistrictDetails();
});	

function getdepartmentWiseDetailedInformation(departmentId,departmentName){
	var str='';
	
	str+='<div class="panel panel-default">';
		str+='<div class="panel-heading headingColor ">';
				str+='<div class="row">';
					str+='<div class="col-md-4 col-xs-12 col-sm-4">';
						str+='<h4 class="panel-title text-capital fontColor">'+departmentName+'</h4>';
					str+='</div>';
					str+='<div class="col-md-6 col-xs-12 col-sm-4">';
						str+='<ul class="switch-btn pull-right">';
							str+='<li attr_type="status" attr_department_id="'+departmentId+'">status overview</li>';
							str+='<li attr_type="overview" class="active" attr_department_id="'+departmentId+'">location level</li>';
						str+='</ul>';
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-4 ">';
						str+='<ul class="switch-btn-alertType pull-right">';
							str+='<li  attr_type="alert" class="active" attr_department_id="'+departmentId+'">Alerts</li>';
							str+='<li attr_type="subTask" attr_department_id="'+departmentId+'">Sub Tasks</li>';
						str+='</ul>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="panel-body">';
			str+='<div class="row">';
				str+='<div class="col-sm-12 col-xs-12 col-md-12 ">';
					str+='<h4>STATE LEVEL</h4>';
						str+='<div id="stateLevelIASDetails" class=""></div>';
					str+='<hr class="m_0"/>';
				str+='</div>';
				
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top20">';
					str+='<h4>ZONE LEVEL</h4>';
					str+='<div class="col-md-2 col-xs-12 col-sm-4 m_top10">';
						str+='<ul class="list-inline activeUlCls  constituencyUl locationWiseSortingZone">';
						str+='<li class="active " attr_sorting_type="count" attr_order_type="desc" attr_department_id="'+departmentId+'">';
							str+='<i class="glyphicon glyphicon-sort-by-attributes" ></i>';
						str+='</li>';
						str+='<li class="" attr_sorting_type="count" attr_order_type="asc" attr_department_id="'+departmentId+'">';
							str+='<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>';
						str+='</li>';
						str+='<li class="" attr_sorting_type="name" attr_order_type="asc" attr_department_id="'+departmentId+'">';
							str+='A-Z';
						str+='</li>';
						str+='<li class="" attr_sorting_type="name" attr_order_type="desc" attr_department_id="'+departmentId+'">';
							str+='Z-A';
						str+='</li>';
						str+='</ul>';
					str+='</div>';
					
					str+='<div id="zoneLevelIASDetails" class=""></div>';
					str+='<hr class="m_0"/>';
				str+='</div>';
			
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top20">';
					str+='<h4>DISTRICT LEVEL</h4>';
					str+='<div class="col-md-2 col-xs-12 col-sm-4 m_top10">';
						str+='<ul class="list-inline activeUlCls  constituencyUl locationWiseSortingDistrict">';
						str+='<li class="active " attr_sorting_type="count" attr_order_type="desc" attr_department_id="'+departmentId+'">';
						str+='<i class="glyphicon glyphicon-sort-by-attributes" ></i>';
						str+='</li>';
						str+='<li class="" attr_sorting_type="count" attr_order_type="asc" attr_department_id="'+departmentId+'">';
							str+='<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>';
						str+='</li>';
						str+='<li class="" attr_sorting_type="name" attr_order_type="asc" attr_department_id="'+departmentId+'">';
							str+='A-Z';
						str+='</li>';
						str+='<li class="" attr_sorting_type="name" attr_order_type="desc" attr_department_id="'+departmentId+'">';
							str+='Z-A';
						str+='</li>';
						str+='</ul>';
					str+='</div>';
					
					str+='<div class="col-sm-4 col-xs-12 col-md-2 locationWiseSortingDist">';
						str+='<select class="form-control locationWiseDistOnChange" id="DistrictNamesId" attr_department_id="'+departmentId+'">';
						str+='</select>';
					str+='</div>';
					
						str+='<div id="districtLevelSubOrdinarteDetails" class=""></div>';
					str+='<hr class="m_0"/>';					
				str+='</div>';
			
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top20">';
				str+='<h4>DIVISION LEVEL</h4>';
					str+='<div class="col-md-2 col-xs-12 col-sm-4 m_top10">';
					
						str+='<ul class="list-inline activeUlCls  constituencyUl locationWiseSortingDivision">';
						str+='<li class="active " attr_sorting_type="count" attr_order_type="desc" attr_department_id="'+departmentId+'">';
							str+='<i class="glyphicon glyphicon-sort-by-attributes" ></i>';
						str+='</li>';
						str+='<li class="" attr_sorting_type="count" attr_order_type="asc" attr_department_id="'+departmentId+'">';
							str+='<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>';
						str+='</li>';
						str+='<li class="" attr_sorting_type="name" attr_order_type="asc" attr_department_id="'+departmentId+'">';
							str+='A-Z';
						str+='</li>';
						str+='<li class="" attr_sorting_type="name" attr_order_type="desc" attr_department_id="'+departmentId+'">';
							str+='Z-A';
						str+='</li>';
						str+='</ul>';
					str+='</div>';
					str+='<div class="col-sm-4 col-xs-12 col-md-2 ">';
						str+='<select class="form-control locationWiseDiviDistOnChange" id="DivisionDistNamesId" attr_department_id="'+departmentId+'">';
							
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-4 col-xs-12 col-md-2">';
						str+='<select class="form-control locationWiseDiviOnChange" id="DivisionNamesId" attr_department_id="'+departmentId+'">';
							str+='<option value="0">Select Division</option>';
						str+='</select>';
					str+='</div>';
					
						str+='<div id="divisionLevelSubOrdinarteDetails" class=""></div>';
					
						str+='<hr class="m_0"/>';
				str+='</div>';
			
				str+='<div class="col-sm-12 col-xs-12 col-md-12 m_top20">';
					str+='<h4>SUB-DIVISION LEVEL</h4>';
					str+='<div class="col-md-2 col-xs-12 col-sm-4 m_top10">';
				
						str+='<ul class="list-inline activeUlCls  constituencyUl locationWiseSortingSubDivision">';
						str+='<li class="active" attr_sorting_type="count" attr_order_type="desc" attr_department_id="'+departmentId+'">';
							str+='<i class="glyphicon glyphicon-sort-by-attributes" ></i>';
						str+='</li>';
						str+='<li class="" attr_sorting_type="count" attr_order_type="asc" attr_department_id="'+departmentId+'">';
							str+='<i class="glyphicon glyphicon-sort-by-attributes-alt" style="transform:rotate(180deg)"></i>';
						str+='</li>';
						str+='<li class="" attr_sorting_type="name" attr_order_type="asc" attr_department_id="'+departmentId+'">';
							str+='A-Z';
						str+='</li>';
						str+='<li class="" attr_sorting_type="name" attr_order_type="desc" attr_department_id="'+departmentId+'">';
							str+='Z-A';
						str+='</li>';
						str+='</ul>';
					str+='</div>';
					str+='<div class="col-sm-4 col-xs-12 col-md-2">';
						str+='<select class="form-control locationWiseSubDiviDistOnChange" id="SubDivisionDistNamesId" attr_department_id="'+departmentId+'">';
						
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-4 col-xs-12 col-md-2">';
						str+='<select class="form-control locationWiseSubDiviDiviOnChange" id="SubDivisionDiviNamesId" attr_department_id="'+departmentId+'">';
						str+='<option value="0">Select Division</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-4 col-xs-12 col-md-2">';
						str+='<select class="form-control locationWiseSubDiviOnChange" id="SubDivisionNamesId" attr_department_id="'+departmentId+'">';
						str+='<option value="0">Select SubDivision</option>';
						str+='</select>';
					str+='</div>';
				
						str+='<div id="SubdivisionLevelSubOrdinarteDetails" class=""></div>';
						str+='</div>';
						
				str+='</div>';
			str+='</div>';
		str+='</div>';		
	str+='</div>';
	
	$("#departmentWiseLocationBlockId").html(str);
	getStatusWiseForStateLevel(departmentId,"desc","count","alert","location",0,0,0)
	getStatusWiseForZoneLevel(departmentId,"desc","count","alert","location",0,0,0)
	getStatusWiseForDistrictLevel(departmentId,"desc","count","alert","location",0,0,0);
	getStatusWiseForDivisionLevel(departmentId,"desc","count","alert","location",0,0,0);
	getStatusWiseForSubDivisionLevel(departmentId,"desc","count","alert","location",0,0,0);					
}

function getAllDistrictDetails(){
	$("#DistrictNamesId").html('');
	$("#DivisionDistNamesId").html('');
	$("#SubDivisionDistNamesId").html('');
	var jsObj ={		
	}
	$.ajax({
		type:'GET',
		url: 'getAllDistrictDetailsAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){	
		if(result !=null && result.length>0){
				$("#DistrictNamesId").append('<option value="0">Select District</option>');
				$("#DivisionDistNamesId").append('<option value="0">Select District</option>');
				$("#SubDivisionDistNamesId").append('<option value="0">Select District</option>');
				for(var i in result){
					$("#DistrictNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					$("#DivisionDistNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					$("#SubDivisionDistNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
	});
}

function getAllDivisionDetails(){
	$("#DivisionNamesId").html('');
	$("#SubDivisionDiviNamesId").html('');
	var jsObj ={
		districtId : 5,
	}
	$.ajax({
		type:'GET',
		url: 'getAllDivisionDetailsAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
			if(result !=null && result.length>0){
				$("#DivisionNamesId").append('<option value="0">Select Division</option>');
				$("#SubDivisionDiviNamesId").append('<option value="0">Select Division</option>');
				for(var i in result){
					$("#DivisionNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					$("#SubDivisionDiviNamesId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
	});
}

function getAllSubDivisionDetails(){
	$("#SubDivisionNamesId").html('');
	var jsObj ={
			divisionId : 20 ,
	}
	$.ajax({
		type:'GET',
		url: 'getAllSubDivisionDetailsAction.action',
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

function getStatusWiseForStateLevel(departmentId,sortingType,orderType,alertType,groupType,districtId,divisionId,subDivisionId){
	var jsObj={
				fromDateStr:currentFromDate,
				toDateStr:currentToDate,
				stateId : 1,
				printIdArr : printIdArr,
				electronicIdArr : electronicIdArr,		
				govtDepartmentId : departmentId,
				parentGovtDepartmentScopeId : 1,
				sortingType :sortingType,
				order :orderType,
				alertType :alertType,
				districtWorkLocationId : districtId,
				divisionWorkLocationId : divisionId,
				subDivisionWorkLocationId : subDivisionId,
				group :groupType
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){	
		buildStatusWiseForStateLevel(result,groupType);
	});
}

function buildStatusWiseForStateLevel(result,groupType){
	
	if(groupType == "status"){
		if(result !=null && result.length>0){
			var locationNamesArrState=[];
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
				
				 locationNamesArrState.push(result[i].name)
				
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
			
			var mainJosnObjArrState = [];
			   if(pendingAlertArr != null && pendingAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Pending',data:pendingAlertArr,color:"#ff4c64"});  
			  }
			   if(notifiedAlertArr != null && notifiedAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Notified',data:notifiedAlertArr,color:"#EFA5B6"});  
			  }
			  if(actionInProgessAlertArr != null && actionInProgessAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Action In Progess',data:actionInProgessAlertArr,color:"#FFCB7F"});  
			  }
			  if(completedAlertArr != null && completedAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Completed',data:completedAlertArr,color:"#85CA8B"});  
			  }
			  if(unblTRslvAlertArr != null && unblTRslvAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Unable to Resolve',data:unblTRslvAlertArr,color:"#C6A3A9"});  
			  }
			  if(actionNotRequiredAlertArr != null && actionNotRequiredAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Action Not Required',data:actionNotRequiredAlertArr,color:"#9698C8"});  
			  }
			  if(duplicateAlertArr != null && duplicateAlertArr.length > 0){
				mainJosnObjArrState.push({name:'Duplicate',data:duplicateAlertArr,color:"#DEC6E0"});  
			  }
			   if(WronglyMappedDesignationArr != null && WronglyMappedDesignationArr.length > 0){
				mainJosnObjArrState.push({name:'Duplicate',data:WronglyMappedDesignationArr,color:"#FE9900"});  
			  }
			   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
				mainJosnObjArrState.push({name:'Duplicate',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
			  }
			   if(RejoinderArr != null && RejoinderArr.length > 0){
				mainJosnObjArrState.push({name:'Duplicate',data:RejoinderArr,color:"#82CA9C"});  
			  } if(Incomplete != null && Incomplete.length > 0){
				mainJosnObjArrState.push({name:'Duplicate',data:Incomplete,color:"#C9AC82"});  
			  }if(Closed != null && Closed.length > 0){
				mainJosnObjArrState.push({name:'Closed',data:Closed,color:"#ababab"});  
			  }
		
		
			
			var heightOfDiv = locationNamesArrState.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 50;
				$("#stateLevelIASDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrState
				var id = 'stateLevelIASDetails';
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
					categories: locationNamesArrState
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
			$("#stateLevelIASDetails").html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#stateLevelIASDetails").css("height","25px");
		}
	}else if(groupType == "location"){
		
		if(result !=null && result.length>0){
				var mainlocationArr =[];
					var nmaesArr =[];
					var colorArr=[];
				if(result[0].subList !=null && result[0].subList.length>0){
					for(var j in result[0].subList){
						var tempArr = {"y":result[0].subList[j].count,color:result[0].subList[j].severtyColor};
						nmaesArr.push(result[0].subList[j].name);
						mainlocationArr.push(tempArr);
						
					}
					
				}
			
			var heightOfDiv = nmaesArr.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 50;
				$("#stateLevelIASDetails").css("height",heightOfDiv);
			}
			
			$('#stateLevelIASDetails').highcharts({
				
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
					categories: nmaesArr,
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
					/* stackLabels: {
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
							return (this.y);
						} 
						
					} */
				},
				tooltip: {
					 pointFormat: '<b>{point.y}</b>',
					 shared:true
				},
				
				legend: {
					verticalAlign:'top',
					enabled: false
				},
				
				series: [{
					 name: '',
					 data: mainlocationArr,
					 colorByPoint:true,
					 dataLabels: {
							enabled: true,
							 format: '{point.y}', // one decimal
						}
					 
				}]
			});
		}else{
			$("#stateLevelIASDetails").html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#stateLevelIASDetails").css("height","25px");
		}
	}
	
}

function getStatusWiseForZoneLevel(departmentId,sortingType,orderType,alertType,groupType,districtId,divisionId,subDivisionId){
	
	var jsObj={
			fromDateStr:currentFromDate,
			toDateStr:currentToDate,
			stateId : 1,
			printIdArr : printIdArr,
			electronicIdArr : electronicIdArr,		
			govtDepartmentId :departmentId,
			parentGovtDepartmentScopeId : 2,
			sortingType :sortingType,
			order :orderType,
			alertType :alertType,
			districtWorkLocationId : districtId,
			divisionWorkLocationId : divisionId,
			subDivisionWorkLocationId : subDivisionId,
			group :groupType
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
			buildStatusWiseForZoneLevel(result,groupType)
	});
}

function buildStatusWiseForZoneLevel(result,groupType){
	
	if(groupType == "status"){
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
		
		
			
			var heightOfDiv = locationNamesArrDistrict.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 50;
				$("#zoneLevelIASDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrict
				var id = 'zoneLevelIASDetails';
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
										//getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,distrctId);
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
			}
				 /* $.each($('#zoneLevelIASDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;"); 
					$(this).attr("onclick","getlevelAndStatusWiseClickForDistrict(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");		
					//$(this).attr("class","getTotaldistrictCls");    
					//$(this).attr("attr_district_id",result[index].id);         
					//$(this).attr("attr_district_name",result[index].name);	
                   //$(this).attr("attr_total_count",result[index].totalCount);		
				}); */
		}else{
			$("#zoneLevelIASDetails").html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#zoneLevelIASDetails").css("height","25px");
		}
	}else if(groupType == "location"){
		
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
		
		
			
			var heightOfDiv = locationNamesArrDistrictOverView.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 50;
				$("#zoneLevelIASDetails").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrictOverview
				var id = 'zoneLevelIASDetails';
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
										//getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,districtId);
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
			}
				/*  $.each($('#zoneLevelIASDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					//$(this).attr("class","getTotaldistrictCls");   
					$(this).attr("onclick","getlevelAndStatusWiseClickForDistrict(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");	
					//$(this).attr("attr_district_id",result[index].id);         
					//$(this).attr("attr_district_name",result[index].name);	
					//$(this).attr("attr_total_count",result[index].totalCount);		
				}); */
				
		}else{
			$("#zoneLevelIASDetails").html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#zoneLevelIASDetails").css("height","25px");
		}
		
		
	}
	
}

function getStatusWiseForDistrictLevel(departmentId,sortingType,order,alertType,groupType,districtId,divisionId,subDivisionId){
	var jsObj={
			fromDateStr:currentFromDate,
			toDateStr:currentToDate,
			stateId : 1,
			printIdArr : printIdArr,
			electronicIdArr : electronicIdArr,		
			govtDepartmentId : departmentId,
			parentGovtDepartmentScopeId : 5,
			sortingType :sortingType,
			order :order,
			alertType :alertType,
			districtWorkLocationId : districtId,
			divisionWorkLocationId : divisionId,
			subDivisionWorkLocationId : subDivisionId,
			group :groupType
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
			buildStatusWiseForDistrictLevel(result,groupType);
	});
}
function buildStatusWiseForDistrictLevel(result,groupType){
	
	if(groupType == "status"){
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
		
		
			
			var heightOfDiv = locationNamesArrDistrict.length ;
			if(heightOfDiv >10){
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
										//getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,distrctId);
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
			}
				 /* $.each($('#districtLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;"); 
					$(this).attr("onclick","getlevelAndStatusWiseClickForDistrict(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");		
					//$(this).attr("class","getTotaldistrictCls");    
					//$(this).attr("attr_district_id",result[index].id);         
					//$(this).attr("attr_district_name",result[index].name);	
                   //$(this).attr("attr_total_count",result[index].totalCount);		
				}); */
		}else{
			$("#districtLevelSubOrdinarteDetails").html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#districtLevelSubOrdinarteDetails").css("height","25px");
		}
	}else if(groupType == "location"){
		
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
		
		
			
			var heightOfDiv = locationNamesArrDistrictOverView.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 50;
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
										//getlevelAndStatusWiseClickForDistrict(statusId,statusName,totalCount,scopeId,districtId);
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
			}
				/*  $.each($('#districtLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
					//$(this).attr("class","getTotaldistrictCls");   
					$(this).attr("onclick","getlevelAndStatusWiseClickForDistrict(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");	
					//$(this).attr("attr_district_id",result[index].id);         
					//$(this).attr("attr_district_name",result[index].name);	
					//$(this).attr("attr_total_count",result[index].totalCount);		
				}); */
				
		}else{
			$("#districtLevelSubOrdinarteDetails").html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#districtLevelSubOrdinarteDetails").css("height","25px");
		}
		
		
	}
	
}
function getStatusWiseForDivisionLevel(departmentId,sortingType,order,alertType,groupType,districtId,divisionId,subDivisionId){
	var jsObj={
			fromDateStr:currentFromDate,
			toDateStr:currentToDate,
			stateId : 1,
			printIdArr : printIdArr,
			electronicIdArr : electronicIdArr,		
			govtDepartmentId :departmentId,
			parentGovtDepartmentScopeId : 6,
			sortingType :sortingType,
			order :order,
			alertType :alertType,
			districtWorkLocationId :districtId,
			divisionWorkLocationId :divisionId,
			subDivisionWorkLocationId :subDivisionId,
			group :groupType
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
			buildStatusWiseForDivisionLevel(result,groupType);
	});
}
function buildStatusWiseForDivisionLevel(result,groupType){
	
	if(groupType == "status"){
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
		
		
			
			var heightOfDiv = locationNamesArrDivision.length ;
			if(heightOfDiv >10){
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
										//getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,divisionId);
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
			}	
				/* $.each($('#divisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");  
					$(this).attr("onclick","getlevelAndStatusWiseClickForDivision(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");		
					//$(this).attr("class","getTotaldivisionCls");    
					//$(this).attr("attr_district_id",result[index].id);         
					//$(this).attr("attr_district_name",result[index].name);	
					//$(this).attr("attr_total_count",result[index].totalCount);		
				}); */
		}else{
			$("#divisionLevelSubOrdinarteDetails").html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#divisionLevelSubOrdinarteDetails").css("height","25px");
		}
	}else if(groupType == "location"){
		
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
		
		
			
			var heightOfDiv = locationNamesArrDivisionOverView.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 50;
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
										//getlevelAndStatusWiseClickForDivision(statusId,statusName,totalCount,scopeId,divisionId);
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
			}
				/* $.each($('#divisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");  
					$(this).attr("onclick","getlevelAndStatusWiseClickForDivision(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");	
					//$(this).attr("class","getTotaldivisionCls");    
					//$(this).attr("attr_district_id",result[index].id);         
					//$(this).attr("attr_district_name",result[index].name);	
					//$(this).attr("attr_total_count",result[index].totalCount);	
				}); */
		}else{
			
			$("#divisionLevelSubOrdinarteDetails").html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#divisionLevelSubOrdinarteDetails").css("height","25px");
		}
	}
	
}
function getStatusWiseForSubDivisionLevel(departmentId,sortingType,order,alertType,groupType,districtId,divisionId,subDivisionId){
	var jsObj={
			fromDateStr:currentFromDate,
			toDateStr:currentToDate,
			stateId : 1,
			printIdArr : printIdArr,
			electronicIdArr : electronicIdArr,		
			govtDepartmentId : departmentId,
			parentGovtDepartmentScopeId : 7,
			sortingType :sortingType,
			order :order,
			alertType :alertType,
			districtWorkLocationId :districtId,
			divisionWorkLocationId :divisionId,
			subDivisionWorkLocationId :subDivisionId,
			group :groupType
			}
	$.ajax({
		type:'GET',
		url: 'getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
			buildStatusWiseForSubDivisionLevel(result,groupType);
	});
}

function buildStatusWiseForSubDivisionLevel(result,groupType){
	
	if(groupType == "status"){
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
		
		
			
			var heightOfDiv = locationNamesArrsubDivision.length ;
			if(heightOfDiv >10){
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
										//getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,subDivisionId);
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
				
			}
			/* $.each($('#SubdivisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){   
					$(this).attr("style","cursor:pointer;");    
				//	$(this).attr("class","getTotalSubdivisionCls"); 
					$(this).attr("onclick","getlevelAndStatusWiseClickForSubDivision(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')"); 					
					/* $(this).attr("attr_district_id",result[index].id);         
					$(this).attr("attr_district_name",result[index].name);	
					$(this).attr("attr_total_count",result[index].totalCount);	 
				}); */
		}else{
			$("#SubdivisionLevelSubOrdinarteDetails").html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#SubdivisionLevelSubOrdinarteDetails").css("height","25px");
		}
	}else if(groupType == "location"){
		
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
		
		
			
			var heightOfDiv = locationNamesArrsubDivisionOverView.length ;
			if(heightOfDiv >10){
				heightOfDiv = heightOfDiv * 50;
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
										//getlevelAndStatusWiseClickForSubDivision(statusId,statusName,totalCount,scopeId,subDivisionId);
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
				
			}
				/* $.each($('#SubdivisionLevelSubOrdinarteDetails').find(".highcharts-xaxis-labels").find("text"),function(index,item){
					$(this).attr("style","cursor:pointer;");    
					//$(this).attr("onclick","getTotalSubdivisionCls");    
					$(this).attr("onclick","getlevelAndStatusWiseClickForSubDivision(0,\'"+result[index].name+"\',\'"+result[index].totalCount+"\',0,\'"+result[index].id+"\')");    
					/* $(this).attr("attr_district_id",result[index].id);         
					$(this).attr("attr_district_name",result[index].name);	
					$(this).attr("attr_total_count",result[index].totalCount);	 
				}); */
		}else{
			$("#SubdivisionLevelSubOrdinarteDetails").html('<div class="col-md-12 col-xs-12 col-sm-12 m_top10">No Data Available</div>');
			$("#SubdivisionLevelSubOrdinarteDetails").css("height","25px");
		}
	}
	
}

$(document).on("click",".alertCountCls",function(){
  var deptId = $(this).attr("attr_dept_id");
  var deptName = $(this).attr("attr_dept_name");
  var count = $(this).attr("attr_count");
  var type = $(this).attr("attr_type");
  getTotalAlertCountDetails(deptId,0,0,type,deptName,count)
});
//click functionality
$(document).on("click",".getDtlsAlertsCls",function(){
		$("#totalAlertsModalTabId").html(spinner);
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		var statusId = $(this).attr("attr_status_id");
		var statusName = $(this).attr("attr_status_name");
		var statuscount = $(this).attr("attr_status_count");
		var type = $(this).attr("attr_type");
		getTotalAlertCountDetails(0,statusId,0,type,statusName,statuscount)
	});
function getTotalAlertCountDetails(departmentId,statusId,levelId,type,statusName,statuscount){
  $("#alertManagementPopupBody").html('')
  
    $("#alertManagementPopup").modal({
      show: true,
      keyboard: false,
      backdrop: 'static'
    });
    $("#alertManagementPopupBody").html(spinner);
    var jObj = {
      deptId: departmentId,//status and location 0
      statusId:statusId,
	  type:type,
	  officerId:globalOfficerIds[0],
	  desigDeptOfficerId:globalGovtDeptDesigOffcrIds[0],
		searchType:"completed"
    }
  $.ajax({
      type:'GET',
      url: 'getStateLevelAlertclickViewAction.action',
    data: {task :JSON.stringify(jObj)}
    }).done(function(result){
    if(result != null && result.length > 0){
      $("#totalAlertsModalTabId").html('');
      buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount);
    }else{
      $("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
    }
  });
  
}
	
