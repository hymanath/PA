var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
onLoadCallsAMU();
	function getAlertType(){
		 var alertType = ''; 
		$('.switch-btn-alertType li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  alertType = $(this).attr("attr_type");
			 }
		});
		return alertType;
	}
	function getLevelType(){
		 var levelType = ''; 
		$('.switch-btn li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  levelType = $(this).attr("attr_type");
			 }
		});
		return levelType;
	}
	function getSortingType(){
		 var sortingType = ''; 
		$('.locationWiseSorting li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  sortingType = $(this).attr("attr_sorting_type");
			 }
		});
		return sortingType;
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
		var alertType = getAlertType();
		getDistrictOfficerAlertsCountView();
		getDistrictLevelDeptWiseLocationLevelView(alertType,"Decending",0);
	});
	
	function onLoadCallsAMU(){
		onLoadClicks();
		var alertType = getAlertType();
		getDistrictOfficerAlertsCountView();
		getDistrictLevelDeptWiseLocationLevelView(alertType,"Decending",0);
		getGovtDepartmentDetails();
		getGovtDeptScopeDetails();
	}
	function onLoadClicks()
	{
		$(document).on('change', '#departmentsId', function(){
			var deptId = $(this).val();
			getDepartmentSubLevels(deptId);
			
		});
		$(document).on('change', '#locationLevelSelectId', function(){
			getChildLevelValuesForSubTask();
			
		});
		$(document).on('change','.locationCls', function(evt, params) {
			designationsByDepartment();
		});
		$(document).on('change','#designationsId', function(evt, params) {
			var designationId = $(this).val();
			officersByDesignationAndLevel(designationId)
		});
		$(document).on("click","#assignOfficerId",function(){
			if($("#departmentsId").val() == null || $("#departmentsId").val() == "" || $("#departmentsId").val() == 0)
			{
				alert("please select department");
				return;
			}
			if($("#locationLevelSelectId").val() == null || $("#locationLevelSelectId").val() == "" || $("#locationLevelSelectId").val() == 0)
			{
				alert("please select impact level");
				return;
			}
			if($("#locationLevelSelectId").val() == 5)
			{
				if($("#locationSubLevelSelectId5").val() == null || $("#locationSubLevelSelectId5").val() == "" || $("#locationSubLevelSelectId5").val() == 0)
				{
					alert("please select location");
					return;
				}
				if($("#locationSubLevelSelectId6").val() == null || $("#locationSubLevelSelectId6").val() == "" || $("#locationSubLevelSelectId6").val() == 0)
				{
					alert("please select designation");
					return;
				}
			}
			if($("#locationLevelSelectId").val() == 6)
			{
				if($("#locationSubLevelSelectId5").val() == null || $("#locationSubLevelSelectId5").val() == "" || $("#locationSubLevelSelectId5").val() == 0)
				{
					alert("please select district");
					return;
				}
				if($("#locationSubLevelSelectId6").val() == null || $("#locationSubLevelSelectId6").val() == "" || $("#locationSubLevelSelectId6").val() == 0)
				{
					alert("please select location");
					return;
				}
				if($("#locationSubLevelSelectId7").val() == null || $("#locationSubLevelSelectId7").val() == "" || $("#locationSubLevelSelectId7").val() == 0)
				{
					alert("please select designation");
					return;
				}
			}
			if($("#locationLevelSelectId").val() == 7)
			{
				if($("#locationSubLevelSelectId5").val() == null || $("#locationSubLevelSelectId5").val() == "" || $("#locationSubLevelSelectId5").val() == 0)
				{
					alert("please select district");
					return;
				}
				if($("#locationSubLevelSelectId6").val() == null || $("#locationSubLevelSelectId6").val() == "" || $("#locationSubLevelSelectId6").val() == 0)
				{
					alert("please select division");
					return;
				}
				if($("#locationSubLevelSelectId7").val() == null || $("#locationSubLevelSelectId7").val() == "" || $("#locationSubLevelSelectId7").val() == 0)
				{
					alert("please select location");
					return;
				}
				if($("#locationSubLevelSelectId8").val() == null || $("#locationSubLevelSelectId8").val() == "" || $("#locationSubLevelSelectId8").val() == 0)
				{
					alert("please select designation");
					return;
				}
			}
			if($("#locationLevelSelectId").val() == 8)
			{
				if($("#locationSubLevelSelectId5").val() == null || $("#locationSubLevelSelectId5").val() == "" || $("#locationSubLevelSelectId5").val() == 0)
				{
					alert("please select district");
					return;
				}
				if($("#locationSubLevelSelectId6").val() == null || $("#locationSubLevelSelectId6").val() == "" || $("#locationSubLevelSelectId6").val() == 0)
				{
					alert("please select division");
					return;
				}
				if($("#locationSubLevelSelectId7").val() == null || $("#locationSubLevelSelectId7").val() == "" || $("#locationSubLevelSelectId7").val() == 0)
				{
					alert("please select sub division");
					return;
				}
				if($("#locationSubLevelSelectId8").val() == null || $("#locationSubLevelSelectId8").val() == "" || $("#locationSubLevelSelectId8").val() == 0)
				{
					alert("please select location");
					return;
				}
				if($("#locationSubLevelSelectId9").val() == null || $("#locationSubLevelSelectId9").val() == "" || $("#locationSubLevelSelectId9").val() == 0)
				{
					alert("please select designation");
					return;
				}
			}
			if($("#designationsId").val() == null || $("#designationsId").val() == "" || $("#designationsId").val() == 0)
			{
				alert("please select designations");
				return;
			}
			if($("#officerNamesId").val() == null || $("#officerNamesId").val() == "" || $("#officerNamesId").val() == 0)
			{
				alert("please select officer name");
				return;
			}
			$("#assiningLdngImg").show();
			$("#assignOfficerId").hide();
			var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					displayStatus(uploadResult);
				}
			};
			

			YAHOO.util.Connect.setForm('alertAssignForm',true);
			YAHOO.util.Connect.asyncRequest('POST','assigningSubTaskToOfficerAction.action',uploadHandler); 
			
			
		});
		$(document).on("click",".switch-btn li",function(){
			$(this).parent("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var type= $(this).attr("attr_type");
			var alertType = getAlertType();
			var sortingType = getSortingType();
			var departmentId = $( "#govtDepartmentsLocId option:selected" ).val();
			if(type == 'filter')
			{
				$(".departmentlocationShow").hide();
				$(".departmentStatusShow").hide();
				$(".departmentAlertCountShow").show();
				$(".sortingDivCls").hide();
				getDistrictLevelDeptWiseFilterViewDetails(alertType);
			}else if(type == "status"){
				$(".sortingDivCls").show();
				$(".departmentlocationShow").hide();
				$(".departmentStatusShow").show();
				$(".departmentAlertCountShow").hide();
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,0); 
			}else if(type == "location"){
				$(".sortingDivCls").show();
				$(".departmentlocationShow").show();
				$(".departmentStatusShow").hide();
				$(".departmentAlertCountShow").hide();
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId);
			}
		});
		$(document).on("click",".switch-btn-alertType li",function(){
			$(this).parent("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var alertType = $(this).attr("attr_type");
			var levelType = getLevelType();
			var sortingType = getSortingType();
			var departmentId = $( "#govtDepartmentsLocId option:selected" ).val();
			if(levelType == 'filter')
			{
				$(".departmentlocationShow").hide();
				$(".departmentStatusShow").hide();
				$(".departmentAlertCountShow").show();
				$(".sortingDivCls").hide();
				getDistrictLevelDeptWiseFilterViewDetails(alertType);
			}else if(levelType == "status"){
				$(".sortingDivCls").show();
				$(".departmentlocationShow").hide();
				$(".departmentStatusShow").show();
				$(".departmentAlertCountShow").hide();
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,0); 
			}else if(levelType == "location"){
				$(".sortingDivCls").show();
				$(".departmentlocationShow").show();
				$(".departmentStatusShow").hide();
				$(".departmentAlertCountShow").hide();
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId);
			}
		});
		$(document).on("click",".locationWiseSorting li",function(){
			$(this).parent("ul").find("li").removeClass("active");
			$(this).addClass("active");
			var sortingType = $(this).attr("attr_sorting_type");
			var levelType = getLevelType();
			var alertType = getAlertType();
			var departmentId = $( "#govtDepartmentsLocId option:selected" ).val();
			
			if(levelType == "status"){
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,0); 
			}else if(levelType == "location"){
				
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId);
			}
			
		});
		$(document).on("change",".locationWiseDeptOnChange",function(){
			var levelType = getLevelType();
			var alertType = getAlertType();
			var sortingType = getSortingType();
			var departmentId = $(this).val();
			if(levelType == "status"){
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,0); 
			}else if(levelType == "location"){
				getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId);
			}
		});	
		$(document).on("change","#districtWiseLevelLocId",function(){
			var levelId = $(this).val();
			getDistrictLevelDeptWiseStatusOverView("alert","Decending",0,levelId); 
		});
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
	}
	function getGovtDepartmentDetails(){
		$("#govtDepartmentsLocId").html('');
		$.ajax({
		  type:'GET',
		  url: 'getGovtDepartmentDetailsAction.action',
		  data: {}
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#govtDepartmentsLocId").append('<option value="0">All</option>');
				for(var i in result){
					$("#govtDepartmentsLocId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		});
		
	}
	function getGovtDeptScopeDetails(){
		$("#districtWiseLevelLocId").html('');
		$.ajax({
		  type:'GET',
		  url: 'getGovtDeptScopeDetailsAction.action',
		  data: {}
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#districtWiseLevelLocId").append('<option value="0">All</option>');
				for(var i in result){
					$("#districtWiseLevelLocId").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		});
		
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
	var userId=19601;
    var jObj ={
      userId:userId,
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
function buildDistrictOfficerAlertsCountView(result){
	var str='';
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
				str1+='<p class="pad_5 todayCountCls" attr_todayCunt='+totalCoutAlertIds+' attr_name ="TODAY" attr_total_count = "'+result.list2[0].todayCount+'">TODAY <span class="pull-right badge" style="cursor:pointer">'+result.list2[0].todayCount+'</span></p>';
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
				str1+='<p class="pad_5 overAllCount" attr_overCunt='+overAllAlertIds+' attr_name ="OVERALL" attr_total_count = "'+result.list2[0].overAllCnt+'">OVERALL <span class="pull-right badge" style="cursor:pointer">'+result.list2[0].overAllCnt+'</span></p>';
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
				str2+='<p class="pad_5 todayCountCls"  attr_todayCunt='+totalCoutAlertIds+' attr_name ="TODAY" attr_total_count = "'+result.list3[0].todayCount+'">TODAY <span style="cursor:pointer" class="pull-right badge">'+result.list3[0].todayCount+'</span></p>';
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
				str2+='<p class="pad_5 overAllCount"   attr_overCunt='+overAllAlertIds+' attr_name ="OVERALL" attr_total_count = "'+result.list3[0].overAllCnt+'" >OVERALL <span class="pull-right badge" style="cursor:pointer">'+result.list3[0].overAllCnt+'</span></p>';
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
}

function getDistrictLevelDeptWiseFilterViewDetails(alertType){
	$("#departmentAlertCountDivId").html(spinner);
	
	var jObj = {
		startDate: currentFromDate,
	    fromDate: currentToDate,
		type:alertType
		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseFilterViewDetailsAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
			$("#departmentAlertCountDivId").html('');
			buildDistrictLevelDeptWiseFilterViewDetails(result);
	});

}

function buildDistrictLevelDeptWiseFilterViewDetails(result){
	
	if(result != null && result.length > 0){	
		var str1='';
		str1+='<table class="table detailedTableStyle" id="departmentAlertCountDT">';
			str1+='<thead>';
				str1+='<tr class="text-capital">';
				str1+='<th>Department</th>';
				str1+='<th>Total Alerts</th>';
					if(result[0].subList2 !=null && result[0].subList2.length>0){
						
						for(var j in result[0].subList2){
							str1+='<th>'+result[0].subList2[j].name+'</th>';
							
						}
						
					}
				str1+='</tr>';
			str1+='</thead>';
			str1+='<tbody>';
				for(var i in result){
					str1+='<tr>';
						str1+='<td>'+result[i].name+'</td>';
						str1+='<td class="distWiseDeptFilterCls" attr_dept_name="'+result[i].name+'" attr_dept_id="'+result[i].id+'" attr_level_id="0" attr_status_id = "0" attr_count = "'+result[i].categoryCount+'" style="cursor:pointer;" >'+result[i].categoryCount+'</td>';
						if(result[i].subList2 !=null && result[i].subList2.length>0){
							
							for(var j in result[i].subList2){
								str1+='<td class="distWiseDeptFilterCls" attr_dept_name="'+result[i].name+'" attr_dept_id='+result[i].id+' attr_level_id='+result[i].subList2[j].id+'  attr_count ="'+result[i].subList2[j].count+'" attr_status_id = "0" style="cursor:pointer;">'+result[i].subList2[j].count+'</td>';
								
							}
						}
					str1+='</tr>';
				}
			str1+='</tbody>';
		str1+='</table>';
		$("#departmentAlertCountDivId").html(str1);
		
	}else{
		$("#departmentAlertCountDivId").html("No Data Available");
	}
		$("#departmentAlertCountDT").dataTable({
			"lengthMenu": [[10, 25, 50,100, -1], [10, 25, 50,100, "All"]]
		});
		
		
	}
	
	
	
function getDistrictLevelDeptWiseLocationLevelView(alertType,sortingType,departmentId){
	
	$("#departmentlocationCountDivId").html(spinner);
	var jObj = {
		startDate: currentFromDate,
	    fromDate: currentToDate,
		type:alertType,
		deptId:departmentId,
		sortingType:sortingType
		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseLocationLevelViewAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		$("#departmentlocationCountDivId").html('');
		buildDistrictLevelDeptWiseLocationLevelView(result);
	});
}

function buildDistrictLevelDeptWiseLocationLevelView(result){
	var mainlocationArr=[];
	
	if(result !=null && result.length>0){
		for(var i in result){
			
			var locationNamesArr=[];
				locationNamesArr.push(result[i].name)
			if(result[i].subList2 !=null &&  result[i].subList2.length>0){
				for(var j in result[i].subList2){
					var locationArr=[];
					var locationColorArr;
					locationArr.push(result[i].subList2[j].count);
					locationColorArr = result[i].subList2[j].color
					var obj={
								name: result[i].subList2[j].name,
								data: locationArr,
								color:locationColorArr
							}
					
					mainlocationArr.push(obj);
					
				}
			}
		}
		
		
	}
	
		var data = mainlocationArr
			var id = 'departmentlocationCountDivId';
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
				categories: locationNamesArr
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
}

function getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,levelId){
	var jObj = {
		startDate: currentFromDate,
	    fromDate: currentToDate,
		type:alertType,
		deptId:departmentId,
		sortingType:sortingType,
		levelId:levelId
		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseStatusOverViewAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		buildDistrictLevelDeptWiseStatusOverView(result);
	});
}

function buildDistrictLevelDeptWiseStatusOverView(result){
	var mainlStatusArr=[];
	
	if(result !=null && result.length>0){
		for(var i in result){
			
			var locStatusNamesArr=[];
				locStatusNamesArr.push(result[i].name)
			if(result[i].subList2 !=null &&  result[i].subList2.length>0){
				
				for(var j in result[i].subList2){
					var locStatusArr=[];
					var locStatusColorArr;
					locStatusArr.push({y:result[i].subList2[j].count,"extra":result[i].subList2[j].id+"-"+result[i].subList2[j].name+"-"+result[i].subList2[j].count+"-"+result[i].id});
					locStatusColorArr = result[i].subList2[j].color
					var obj={
								name: result[i].subList2[j].name,
								data: locStatusArr,
								color:locStatusColorArr
							}
					
					mainlStatusArr.push(obj);
					
				}
			}
		}
		
		
	}
	
		var data = mainlStatusArr
			var id = 'departmentStatusCountDivId';
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
				categories: locStatusNamesArr
			}
			var plotOptions =  {
	             bar: {
					stacking: 'percent',
					pointWidth: 30,
					gridLineWidth: 15
				},
				series: {
					cursor: 'pointer',
					point: {
					events: {
						click: function () {
							var value = (this.extra).split("-");
							var statusId = value[0];
							var statusName = value[1];
							var count = value[2];
							var departmentId = value[3];
							
							 
							getTotalAlertCountDetails(statusId,statusName,count,departmentId);
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





//getSubOrdinateAlertsOverview();
function getSubOrdinateAlertsOverview(){
	var userId="";
	var fromDateStr= "";
	var toDateStr= "";
	var govtScopeIds=[];
	var locationValues=[];
	var desigIds=[];
	var priorityId="";
	
	var jObj = {
	userId : userId,
	fromDateStr : fromDateStr, 
	toDateStr : toDateStr,
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
//click functioality...
function getTotalAlertCountDetails(statusId,statusName,count,departmentId){
	
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		
		var jObj = {
			deptId: departmentId,
			levelId: 0,
			statusId:statusId
		
		}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseFlterClickAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,count);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
	});
	
}
/* $(document).on("click",".distWiseDeptFilterCls",function(){
	
	$("#totalAlertsModalTabId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		
	var deptId = $(this).attr("attr_dept_id");
	var levelId = $(this).attr("attr_level_id");
	var statusId = $(this).attr("attr_status_id");
	var departmentName = $(this).attr("attr_dept_name");
	var count = $(this).attr("attr_count");
	var jObj = {
		deptId: deptId,
	    levelId: levelId,
		statusId:statusId
		
	}
	$.ajax({
      type:'GET',
      url: 'getDistrictLevelDeptWiseFlterClickAction.action',
	  data: {task :JSON.stringify(jObj)}
    }).done(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,departmentName,count);
		}else{
			$("#alertManagementPopupBody").html('NO DATA AVAILABLE')
		}
	});
}); */

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

function rightSideExpandView(alertId)
{
	$("#rightSideExpandView").html(spinner);
	var str='';
	str+='<div class="col-sm-8 pad_left0" expanded-block="block1" style="display: none;">';
		str+='<div class="panel-right">';
			str+='<div class="arrow_box_left">';
				str+='<i class="glyphicon glyphicon-remove pull-right"  expanded-close="block1"></i>';
				str+='<div class="panel panel-default">';
					str+='<div class="panel-heading">';
						str+='<div class="row">';
							str+='<div class="col-sm-4">';
								str+='<div id="assignedUser"></div>';
							str+='</div>';
							str+='<div class="col-sm-8">';
								str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
									str+='<li status-icon-block="alertStatus" data-toggle="tooltip" data-placement="top" title="alert status">';
										str+='<span class="status-icon arrow-icon"></span>Pending';
									str+='</li>';
									str+='<li class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="due date">';
										str+='<i class="glyphicon glyphicon-calendar"></i><span class="modal-date">DUe date</span>';
									str+='</li>';
									str+='<li status-icon-block="alertStatusChange" data-toggle="tooltip" data-placement="top" title="status change">';
										str+='<i class="glyphicon glyphicon-cog"></i>';
										str+='<ul class="alert-status-change-list arrow_box_top" style="display:none;">';
											str+='<li>high <input type="radio" name="alert-status-change-list" value="1" class="pull-right" /></li>';
											str+='<li>medium <input type="radio" name="alert-status-change-list" value="2" class="pull-right" /></li>';
											str+='<li>low <input type="radio" name="alert-status-change-list" value="3" class="pull-right" /></li>';
											str+='<li><button class="btn btn-primary btn-sm text-capital" id="priorityChangeSaveId">save</button></li>';
										str+='</ul>';
									str+='</li>';
									str+='<li status-icon-block="subTask" class="status-icon-subtask" data-toggle="tooltip" data-placement="top" title="sub task">';
										str+='<i class="fa fa-mail-forward"></i>';
										str+='<i class="fa fa-level-down"></i>';
									str+='</li>';
									str+='<li status-icon-block="alertHistory">';
										str+='<i class="fa fa-road" data-toggle="tooltip" data-placement="top" title="Alert History"></i>';
									str+='</li>';
									str+='<li>';
										str+='<i class="glyphicon glyphicon-paperclip" data-toggle="tooltip" data-placement="top" title="Attachments"></i>';
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="panel-body">';
						str+='<p><i class="fa fa-fire"></i> Impact Level : State';
							str+='<span class="text-danger pull-right"><i class="glyphicon glyphicon-cog"></i> Priority:<span id="priorityBodyId"> HIGH</span></span>';
						str+='</p>';
						str+='<div status-body="task" class="m_top20"></div>';
						str+='<div status-body="subTask" class="m_top20"></div>';
					str+='</div>';
					str+='<div class="panel-footer">';
						str+='<div class="row">';
							str+='<div class="col-sm-1 text-center">';
								str+='<span class="icon-name icon-primary">Ra</span>';
							str+='</div>';
							str+='<div class="col-sm-11">';
								str+='<div class="panel panel-default panel-border-white">';
									str+='<div class="panel-heading">';
										str+='<label class="radio-inline" name="language">';
											str+='<input type="radio"/>Telugu';
										str+='</label>';
										str+='<label class="radio-inline" name="language">';
											str+='<input type="radio"/>English';
										str+='</label>';
									str+='</div>';
									str+='<div class="panel-body">';
										str+='<div class="comment-area">Comment Here</div>';
										str+='<textarea class="form-control comment-area" id="alertCommentId" placeholder="Comment here..."></textarea>';
									str+='</div>';
									str+='<div class="panel-footer text-right">';
										str+='<button class="btn btn-primary comment-btn" id="commentChangeId">Comment</button>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#rightSideExpandView").html(str);
	$('[data-toggle="tooltip"]').tooltip();
	dateRangePicker();
	assignedOfficersDetailsForAlert(alertId);

}
function assignedOfficersDetailsForAlert(alertId)
{
	var jsObj = {
		alertId : alertId
	}
	$.ajax({
		type:'GET',
		url: 'getAssignedOfficersDetailsAction.action',
	data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			buildAssignedOfficersDetailsForAlert(result);
		}else{
			$("#assignedUser").html("NO DATA");
		}
	});
}



function getDepartmentSubLevels(deptId){
	
	var jsObj = {
		departmentId : deptId,
		levelId:5
	}
	$.ajax({
      type:'GET',
      url: 'getDepartmentSubLevelsAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildDepartmentSubLevels(result);
		}
			
	});
	
}
function buildDepartmentSubLevels(result){
	
	var str='';	
	str+='<option value="0">Select Level</option>';
	for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}
	
	$("#locationLevelSelectId").html(str);
	$("#locationLevelSelectId").trigger("chosen:updated");
}

function getChildLevelValuesForSubTask(){
	var departmentId = $("#departmentsId").val();
	var jsObj = {
		departmentId : departmentId,
		levelId : $("#locationLevelSelectId").val(),
		parentLevelId : 5,
		parentLevelValue : 3
		
	}
	$.ajax({
      type:'GET',
      url: 'getChildLevelValuesForSubTaskAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildChildLevelValuesForSubTask(result,departmentId);
		}
			
	});
}
function buildChildLevelValuesForSubTask(result,departmentId){
	var str='';
		
		for(var i in result){
			if(i<result.length-1){
				str+='<div class="col-sm-6">';
					str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select  class="chosenSelect" id="locationSubLevelSelectId'+result[i].id+'" onchange="getGovtSubLevelInfo('+departmentId+','+result[i].id+')"  ></select>';
				str+='</div>';
			}else{
				str+='<div class="col-sm-6">';
					str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select  class="chosenSelect locationCls" id="locationSubLevelSelectId'+result[i].id+'" name="alertAssigningVO.levelValue" ></select>';
				str+='</div>';
			}
			
		}
	
	$("#parentLevelDivId").html(str);
	$(".chosenSelect").chosen();
	
	for(var i in result){
		
		if(result[i].idnameList !=null && result[i].idnameList.length>0){
			var newStr='';		
			newStr+='<option value="0">Select '+result[i].name+'</option>';
			for(var j in result[i].idnameList){
				 newStr+='<option value="'+result[i].idnameList[j].id+'">'+result[i].idnameList[j].name+'</option>';
			}			
			$("#locationSubLevelSelectId"+result[i].id+"").html(newStr);
			$("#locationSubLevelSelectId"+result[i].id+"").trigger("chosen:updated");
		}
	}
	
}

function designationsByDepartment()
{
	$("#designationsId").empty();
	$("#designationsId").trigger("chosen:updated");
	$("#officerNamesId").empty();
	$("#officerNamesId").trigger("chosen:updated");
	var LevelId = $("#locationLevelSelectId").chosen().val();
	var deprtmntId = $("#departmentsId").chosen().val();
	var levelValue = $(".locationCls").chosen().val();
	
	var jsObj = {
		departmentId	: deprtmntId,
		levelId			: LevelId,
		levelValue			: levelValue
	}
	$.ajax({
      type:'GET',
      url: 'getDesignationsByDepartmentNewAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		str+='<option value="0">Select Designation</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#designationsId").html(str);
		$("#designationsId").trigger("chosen:updated");
	});
}

function officersByDesignationAndLevel(designationId)
{
	$("#officerNamesId").empty();
	$("#officerNamesId").trigger("chosen:updated");
	var LevelId = $("#locationLevelSelectId").chosen().val()
	var LevelValue = $(".locationCls").chosen().val()
	
	var jsObj = {
		levelId				: LevelId,
		levelValue			: LevelValue,
		designationId		: designationId
	}
	$.ajax({
      type:'GET',
      url: 'getOfficersByDesignationAndLevelNewAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		str+='<option value="0">Select Officer</option>';
		for(var i in result)
		{
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
		$("#officerNamesId").html(str);
		$("#officerNamesId").trigger("chosen:updated");
	});
}
///
function getGovtSubLevelInfo(departmentId,levelId){
	
	$("#designationsId").empty();
	$("#designationsId").trigger("chosen:updated");
	$("#officerNamesId").empty();
	$("#officerNamesId").trigger("chosen:updated");	
	
	var levelValue=$("#locationSubLevelSelectId"+levelId+"").val();	
	
	var jsObj = {
		departmentId : departmentId,
		levelId :levelId,
		levelValue:levelValue
	}
	$.ajax({
      type:'GET',
      url: 'getGovtSubLevelInfoAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			buildGovtSubLevelInfoAction(result);
		}
			
	});
}
function buildGovtSubLevelInfoAction(result){
	
	var str='';
	if(result !=null){		
		if(result.idnameList !=null && result.idnameList.length>0){
			str+='<option value="0">Select '+result.name+'</option>';
			for(var i in result.idnameList){
				str+='<option value="'+result.idnameList[i].id+'">'+result.idnameList[i].name+'</option>';
			}
		}
		
		$("#locationSubLevelSelectId"+result.id+"").html(str);
		$("#locationSubLevelSelectId"+result.id+"").trigger("chosen:updated");
	}
	
}
function statusBody(name)
{
	var str='';
	if(name == 'subTask')
	{
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-users fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="panel-title text-capital"><b>involved members in this alert</b></h4>';
			str+='<ul class="list-inline involved-members-list m_top20">';
				str+='<li><img src=""/></li>';
				str+='<li><img src=""/></li>';
				str+='<li><img src=""/></li>';
				str+='<li><img src=""/></li>';
				str+='<li><img src=""/></li>';
				str+='<li><img src=""/></li>';
			str+='</ul>';
		str+='</div>';
		str+='<h4 class="text-capital text-muted panel-title"><i class="fa fa-level-down"></i>&nbsp;&nbsp; assign subtask</h4>';
		str+='<ul class="assign-subtask-list">';
			str+='<li class="assigned">';
				str+='<i class="glyphicon glyphicon-trash remove-task"></i>';
				str+='<div class="row">';
					str+='<div class="col-sm-1">';
						str+='<i class="glyphicon glyphicon-ok"></i>';
					str+='</div>';
					str+='<div class="col-sm-9">';
						str+='<p>sgibdda sgf</p>';
					str+='</div>';
					str+='<div class="col-sm-2">';
						str+='<i class="glyphicon glyphicon-menu-right pull-right"></i>';
						str+='<span class="icon-name icon-primary"></span>';
						str+='<span class="label label-default">...</span>';
					str+='</div>';
				str+='</div>';
			str+='</li>';
			str+='<li class="new">';
				str+='<div class="row">';
					str+='<div class="col-sm-1">';
						str+='<i class="glyphicon glyphicon-plus"></i>';
					str+='</div>';
					str+='<div class="col-sm-9">';
						str+='<input type="text" class="form-control" placeholder="Enter Sub Task Title"/>';
					str+='</div>';
					str+='<div class="col-sm-2">';
						str+='<i class="glyphicon glyphicon-menu-right pull-right"></i>';
						str+='<i class="glyphicon glyphicon-user assign-subtask-btn"></i>';
						str+='<i class="glyphicon glyphicon-calendar"></i>';
					str+='</div>';
				str+='</div>';
				str+='<div class="assign-subtask">';
					str+='<form id="alertAssignSubTask" name="alertAssignForm">';
						str+='<div class="arrow_box_top">';
							str+='<div>';
								str+='<div class="row">';
									str+='<div class="col-sm-6">';
										str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
										str+='<select class="chosenSelect" id="departmentsId" name="alertAssigningVO.departmentId">	';
											str+='<option value="0">Select Department</option>';
											str+='<option value="49">RWS</option>';
										str+='</select>';
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
										str+='<select  class="chosenSelect" id="locationLevelSelectId" name="alertAssigningVO.levelId">	';
											str+='<option></option>';
										str+='</select>';
									str+='</div>';
									str+='<div id="parentLevelDivId"> </div>';
									
									str+='<div class="col-sm-6">';
										str+='<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDesgId"></span></label>';
										str+='<select name="alertAssigningVO.designationId" id="designationsId" class="chosenSelect">';
											str+='<option></option>	';
										str+='</select>';
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgOffcrId"></span></label>';
										str+='<select name="alertAssigningVO.govtOfficerId" id="officerNamesId" class="chosenSelect">';
											str+='<option></option>';
										str+='</select>';
									str+='</div>';
								str+='</div>';
								str+='<input type="hidden" id="hiddenAlertId" value="13817" name="alertAssigningVO.alertId"/>';
							str+='</div>';
						str+='</div>';
					str+='<div class="panel-footer text-right pad_5 border_1 bg_EE">';
						str+='<button class="btn btn-primary btn-sm text-capital" id="assignOfficerId" type="button">assign</button>';
						str+='<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="assiningLdngImg">';
						str+='<span class="text-success" id="assignSuccessSubTask"></span>';
					str+='</div>';
					str+='</form>';
				str+='</div>';
			str+='</li>';
		str+='</ul>';
	}
	
	$("[status-body]").html(' ');
	$("[status-body="+name+"]").html(str);
	$(".chosenSelect").chosen({width:'100%'})
}

function displayStatus(result)
{
	var result = (String)(result);
	if(result.search('success') != -1){
		$("#assiningLdngImg").hide();
		$("#assignOfficerId").show();
		alert("Alert Assigned Successfully.");
		$("#assignSuccessSubTask").html('Alert Assigned Successfully')
		location.reload();
	}else{
		alert("Please Try Again.");
		$("#assignSuccessSubTask").addClass("text-danger");
		$("#assignSuccessSubTask").html('Try Again');
	}	
}