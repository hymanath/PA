var globalLevelId =0;
var globallevelValues =[];
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
				$(".sortingDivClsDept").hide();
				$(".sortingDivClsLevel").hide();
				getDistrictLevelDeptWiseFilterViewDetails(alertType);
			}else if(type == "status"){
				$(".sortingDivClsDept").show();
				$(".sortingDivClsLevel").show();
				$(".departmentlocationShow").hide();
				$(".departmentStatusShow").show();
				$(".departmentAlertCountShow").hide();
				getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,0); 
			}else if(type == "location"){
				$(".sortingDivClsLevel").hide();
				$(".sortingDivClsDept").show();
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
	
	globalLevelId = result.levelId;
	globallevelValues = result.levelValues;
		if(result !=null && result.list1 !=null && result.list1.length>0){
			var str='';
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
			var tempArrAT = {"y":result.list1[i].count,color:result.list1[i].color};
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list1[i].count),color:"#D3D3D3"};
			countAT.push(uniqCnt);
			
			mainArrTempAT.push(tempArrAT);
		}
	
	console.log(mainArrTempAT)
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
	
	if(result !=null && result.list2 !=null && result.list2.length>0){
			var str1='';
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
			var tempArrST = {"y":result.list2[i].count,color:result.list2[i].color};
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list2[i].count),color:"#D3D3D3"};
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
			var tempArr = {"y":result.list3[i].count,color:result.list3[i].color};
			var uniqCnt = {"y":parseInt(totalAlertCnt)-parseInt(result.list3[i].count),color:"#D3D3D3"};
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
		if(result !=null && result.length>0){
			buildDistrictLevelDeptWiseLocationLevelView(result);
		}else{
			$("#departmentlocationCountDivId").html("No Data Available");
		}
		
	});
}

function buildDistrictLevelDeptWiseLocationLevelView(result){
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
				
				if(result[i].subList2 !=null &&  result[i].subList2.length>0){
					for(var j in result[i].subList2){
							
						 if(result[i].subList2[j].id==1){
							 stateArr.push(result[i].subList2[j].count); 
						}else if(result[i].subList2[j].id==2){
							 goneArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==3){
							 regionArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==4){
							 circleArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==5){
							 districtArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==6){
							 divisionArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==7){
							 subDivisionArr.push(result[i].subList2[j].count);
						}
						else if(result[i].subList2[j].id==8){
							 mandalArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==9){
							 municipalityArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==10){
							 panchayatArr.push(result[i].subList2[j].count);
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
				$("#departmentlocationCountDivId").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrictOverview
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
			$("#departmentlocationCountDivId").html('No Data Available');
		}
}

function getDistrictLevelDeptWiseStatusOverView(alertType,sortingType,departmentId,levelId){
	$("#departmentStatusCountDivId").html(spinner);
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
		$("#departmentStatusCountDivId").html('');
		if(result !=null && result.length>0){
			buildDistrictLevelDeptWiseStatusOverView(result);
		}else{
			$("#departmentStatusCountDivId").html('<div class="col-xs-12">NO DATA AVAILABLE</div>');
		}
		
	});
}

function buildDistrictLevelDeptWiseStatusOverView(result){
	
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
				
				if(result[i].subList2 !=null &&  result[i].subList2.length>0){
					for(var j in result[i].subList2){
							
						 if(result[i].subList2[j].id==1){
							 pendingAlertArr.push(result[i].subList2[j].count); 
						}else if(result[i].subList2[j].id==2){
							 notifiedAlertArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==3){
							 actionInProgessAlertArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==4){
							 completedAlertArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==5){
							 unblTRslvAlertArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==6){
							 actionNotRequiredAlertArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==7){
							 duplicateAlertArr.push(result[i].subList2[j].count);
						}
						else if(result[i].subList2[j].id==8){
							 WronglyMappedDesignationArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==9){
							 WronglyMappedDepartmentArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==10){
							 RejoinderArr.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==11){
							 Incomplete.push(result[i].subList2[j].count);
						}else if(result[i].subList2[j].id==12){
							 Closed.push(result[i].subList2[j].count);
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
				mainJosnObjArrDistrict.push({name:'WronglyMappedDesignation',data:WronglyMappedDesignationArr,color:"#FE9900"});  
			  }
			   if(WronglyMappedDepartmentArr != null && WronglyMappedDepartmentArr.length > 0){
				mainJosnObjArrDistrict.push({name:'WronglyMappedDepartment',data:WronglyMappedDepartmentArr,color:"#0C9514"});  
			  }
			   if(RejoinderArr != null && RejoinderArr.length > 0){
				mainJosnObjArrDistrict.push({name:'Rejoinder',data:RejoinderArr,color:"#82CA9C"});  
			  } if(Incomplete != null && Incomplete.length > 0){
				mainJosnObjArrDistrict.push({name:'Incomplete',data:Incomplete,color:"#C9AC82"});  
			  }if(Closed != null && Closed.length > 0){
				mainJosnObjArrDistrict.push({name:'Closed',data:Closed,color:"#ababab"});  
			  }
		
		
			
			var heightOfDiv = locationNamesArrDistrict.length ;
			if(heightOfDiv >9){
				heightOfDiv = heightOfDiv * 50;
				$("#departmentStatusCountDivId").css("height",heightOfDiv);
			}
			
			
			var data = mainJosnObjArrDistrict
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
			$("#departmentStatusCountDivId").html('No Data Available');
		}
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
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
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

function viewAlertHistory(alertId)
{
	$("#alertManagementPopupBody1").html(spinner)
	var jsObj ={
		subTaskId : alertId
	}
	$.ajax({
		type:'GET',
		url: 'viewSubTaskHistoryAction.action',
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

function rightSideExpandView(subTaskId)
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
									str+='<li status-icon-block="alertStatus" attr_alert_id="'+subTaskId+'" data-toggle="tooltip" data-placement="top" title="alert status">';
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
									str+='<li status-icon-block="subTask" attr_alert_id="'+subTaskId+'" class="status-icon-subtask" data-toggle="tooltip" data-placement="top" title="sub task">';
										str+='<i class="fa fa-mail-forward"></i>';
										str+='<i class="fa fa-level-down"></i>';
									str+='</li>';
									str+='<li status-icon-block="alertHistory" attr_alert_id="'+subTaskId+'">';
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
	assignedOfficersDetailsForAlert(subTaskId);

}
function assignedOfficersDetailsForAlert(subTaskId)
{
	var jsObj = {
		alertId : subTaskId
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