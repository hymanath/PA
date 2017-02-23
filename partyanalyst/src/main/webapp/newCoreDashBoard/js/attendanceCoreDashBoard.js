	
	$(document).on("click",".attendaceIconExpand",function(){
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".attendanceBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".attendanceBlockMore,.moreAttBlocksIcon").toggle();
		$(".dateRangePickerClsForAttendance").toggleClass("hide")
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			//getUserTypeWiseNewsCounts(1);
			setTimeout(function(){
				$('html,body').animate({
					scrollTop: $(".attendanceBlock").offset().top},
				'slow');
			},500);
		}else{
			$(".newsHiddenMoreBlock,.moreAttBlocks").hide();
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
		}else if( $(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".alertsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".districtAltCtnCls ,.alertLocationDiv,.dateRangePickerClsForAlert").hide();
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
	
	});
	$(window).resize(function(){
		if($(document).width() < 500)
		{
			$("#officeAttendanceTdlsId,#officeAttendanceTdlsDeptWiseId").addClass("table-responsive");
		}else{
			$("#officeAttendanceTdlsId,#officeAttendanceTdlsDeptWiseId").removeClass("table-responsive");	
		}
	});


	$(document).on('change','#attenDatePickerModal', function() {
		var strDate = $(this).val();
		var dates = [];
		dates = strDate.split('-');
		var fromDate = dates[0];
		var toDate = dates[1]; 
		var deptName = '';
		var officeName = '';
		if(officeId==1){
			$("#officeNameId").html('Hyderabad Party Office');
			officeName='Hyderabad Party Office';
		}
		if(officeId==2){
			$("#officeNameId").html('Guntur Party Office');
			officeName='Guntur Party Office';
		} 
		deptName = $("#diptNameId").html();
		//fromDate = picker.startDate.format('MM/DD/YYYY');
		//toDate = picker.endDate.format('MM/DD/YYYY');
		var officeId = $("#officeHidId").attr("attr_office_hid_id");
		var deptId = $("#deptHidId").attr("attr_dept_hid_id");      
		getTotalDtls(deptId,officeId,fromDate,toDate);
		getTimeWiseDtls(deptId,officeId,fromDate,toDate);
		getAttendanceReportTimeToTime(deptId,officeId,fromDate,toDate,deptName,officeName);
		getDateWisePresentAbsentDtls(deptId,officeId,fromDate,toDate); 
		
		
		$("#totalCountId").html('');  
		$('#attedanceModalId').html('');
		$("#employeeOverViewId").html('');
		$("#dayWiseOvervwModal").html('');
		$("#totalCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#attedanceModalId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#employeeOverViewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
		$("#dayWiseOvervwModal").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#attendanceModal").modal('show');
	});
	$(document).on('change','#attenDatePickerModalForEmpId', function() {    
		var strDate = $(this).val();
		var dates = [];
		dates = strDate.split('-');
		var fromDate = dates[0];
		var toDate = dates[1];    
		//fromDate = picker.startDate.format('MM/DD/YYYY');
		//toDate = picker.endDate.format('MM/DD/YYYY');    
		
		var officeId = $("#officeHidId").attr("attr_office_hid_id");
		var deptId = $("#deptHidId").attr("attr_dept_hid_id");
		var cadreId = $("#cadreHidId").attr("attr_cadre_hid_id");
		
		getTotalDtlsEmployee(deptId,officeId,fromDate,toDate,cadreId);
		getTimeWiseDtlsEmployee(deptId,officeId,fromDate,toDate,cadreId);
		getDateWisePresentAbsentDtlsEmployee(deptId,officeId,fromDate,toDate,cadreId);  
		
		$("#singleEmployeeOverViewId").html('');
		$("#tableAttendanceId").html('');
		$("#attedanceModalForEmpId").html('');
		
		$("#singleEmployeeOverViewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#tableAttendanceId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#attedanceModalForEmpId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		   
	});
	$("#dateRangeIdForAttendance1").daterangepicker({
		opens: 'left',
		startDate: moment(),
		endDate: moment(),
		locale: {
			format: 'MM/DD/YYYY'       
		},  
		ranges: {
			'Today': [moment(), moment()],
			'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			'This Month': [moment().startOf('month'), moment()],
			'Last 30 Days': [moment().subtract(29, 'days'), moment()],
			'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'Last 3 Months': [moment().subtract(3, 'month'), moment()],
			'Last 6 Months': [moment().subtract(6, 'month'), moment()],
			'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
			'This Year': [moment().startOf('Year'), moment()]    
		}   
	})
	$('#dateRangeIdForAttendance1').on('apply.daterangepicker', function(ev, picker) {
		customFromDate1 = picker.startDate.format('MM/DD/YYYY');
		customToDate1 = picker.endDate.format('MM/DD/YYYY');
		if(customFromDate1==customToDate1){
		
			var tableId = "hydDtlsId";
			var officeId = 1;//for Hyd 
			if(officeId==1){
				$("#hydTopId").html('');
			}
			if(officeId==2){
				$("#gunTopId").html('');
			} 
			$("#hydDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			getLocationDtls(tableId,officeId,customFromDate1,customToDate1);  
		}else{
			var tableId = "hydDtlsId";
			var officeId = 1;//for Hyd
			$("#hydDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			getLocationDtlsForMultiDates(tableId,officeId,customFromDate1,customToDate1);  
		}
	});
	$("#dateRangeIdForAttendance2").daterangepicker({
		opens: 'left',
		startDate: moment(),
		endDate: moment(),
		locale: {
			format: 'MM/DD/YYYY'
		},  
		ranges: {  
			'Today': [moment(), moment()],
			'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			'This Month': [moment().startOf('month'), moment()],
			'Last 30 Days': [moment().subtract(29, 'days'), moment()],
			'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'Last 3 Months': [moment().subtract(3, 'month'), moment()],
			'Last 6 Months': [moment().subtract(6, 'month'), moment()],
			'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
			'This Year': [moment().startOf('Year'), moment()]  
		}
	})
	$('#dateRangeIdForAttendance2').on('apply.daterangepicker', function(ev, picker) {
		customFromDate2 = picker.startDate.format('MM/DD/YYYY');
		customToDate2 = picker.endDate.format('MM/DD/YYYY');       
		if(customFromDate2==customToDate2){  
			var tableId = "gunDtlsId";      
			var officeId = 2;//for Gun
			if(officeId==1){
				$("#hydTopId").html('');
			}
			if(officeId==2){
				$("#gunTopId").html('');  
			}   
			$("#gunDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			getLocationDtls(tableId,officeId,customFromDate2,customToDate2);  
		}else{
			var tableId = "gunDtlsId";
			var officeId = 2;//for Gun
			$("#gunDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			getLocationDtlsForMultiDates(tableId,officeId,customFromDate2,customToDate2);      
		}  
	});
  
	$("#dateRangeIdForAttendance").daterangepicker({
		singleDatePicker: true,
		startDate:moment(),
		minDate:'01/01/2014',
		maxDate:moment(),
		format: 'MM/DD/YYYY',
		opens:'left'
	})

	/* $("#dateRangeIdForAttendance1").daterangepicker({    
		opens: 'left',
		startDate: moment().subtract(1, 'month').startOf('month'),
		endDate: moment().subtract(1, 'month').endOf('month'),
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()]
		}
	}); */
	var today = $("#dateRangeIdForAttendance").val();  
	$('#dateRangeIdForAttendance1').on('apply.daterangepicker', function(ev, picker) {
		//customStartDate = picker.startDate.format('DD/MM/YYYY');
		//customEndDate = picker.endDate.format('DD/MM/YYYY');
		
	});
	$('#dateRangeIdForAttendance').on('apply.daterangepicker', function(ev, picker) {
		customStartDate = picker.startDate.format('MM/DD/YYYY');
		customEndDate = picker.endDate.format('MM/DD/YYYY');
		var changedDate = $("#dateRangeIdForAttendance").val();
		
		var datStr = changeDateFormat($("#dateRangeIdForAttendance").val());

		if(today == $("#dateRangeIdForAttendance").val()){
			$("#attendanceId").html('TODAY ('+datStr+')');
		}else{
			$("#attendanceId").html('('+datStr+')');     
		}  
		  
		getAttendanceOverViewForPartyOffice();  
		getAttendanceOverViewForPartyOfficeWise();
		getAttendanceOverViewForPartyOfficeDeptWise(); 
		
		/* var tableId = "hydDtlsId";
		var officeId = 1;//for Hyd  
		$("#hydDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		getLocationDtls(tableId,officeId,changedDate,changedDate);
		tableId = "gunDtlsId";
		officeId = 2;//for Gun
		$("#gunDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		getLocationDtls(tableId,officeId,changedDate,changedDate);  */   
	 
	});

	$(document).on("click",".moreAttBlocksIcon",function(){
		$(".moreAttBlocks").toggle();
	});

	$('#attendance').highcharts({
		colors: ['#FFC21F'],
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
			categories: ['JAN', 'FEB', 'MAR'],
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
			pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
			shared: true,
			valueSuffix: '%'
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
			name: 'Attendance',
			data: [107, 31, 635, 203, 2]
		}]
	});
	/*$(function () {
		//added in onloadMethod
		getAttendanceOverViewForPartyOffice();
		getAttendanceOverViewForPartyOfficeWise();
		var datStr = changeDateFormat($("#dateRangeIdForAttendance").val());
		$("#attendanceId").html('TODAY ('+datStr+')');
	});*/
	function getAttendanceOverViewForPartyOffice(){
		$("#officeAttendanceTdlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var fromDate = $("#dateRangeIdForAttendance").val();
		var toDate = $("#dateRangeIdForAttendance").val();
		var jsObj={ 
			fromDate : fromDate,
			toDate : toDate
		};
		$.ajax({  
			type : 'GET',
			url : 'getAttendanceOverViewForPartyOfficeAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#officeAttendanceTdlsId").html('');
			if(result != null){
				buildOfficeAttendanceDtls(result);
			}else{
				$("#officeAttendanceTdlsId").html('Data Not Available.');
			}  
		});
	}
	function buildOfficeAttendanceDtls(result){
		var str = '';
		var datStr = changeDateFormat($("#dateRangeIdForAttendance").val());
		if(today==$("#dateRangeIdForAttendance").val()){
			str+='<h4 class="text-capital">total members - <small>(TODAY - '+datStr+')</small></h4>';
		}else{
			str+='<h4 class="text-capital">total members - <small>('+datStr+')</small></h4>';			
		}  
		
		str+='<table class="table tableTraining bg_ED">';
		str+='<tr>';
			str+='<td>';
				str+='<p class="text-muted text-capital">total members</p>';
				str+='<h4>'+result.actualCount+'</h4>';
			str+='</td>';
			str+='<td>';
			var presentPrecent = (result.availableCount*(100/result.actualCount)).toFixed(2);
				str+='<p class="text-muted text-capital">present</p>';  
				str+='<h4>'+result.availableCount+'<span class="font-10 text-success"> '+presentPrecent+'</span></h4>';
			str+='</td>';
			str+='<td>';
			var absentPrecent = (result.count*(100/result.actualCount)).toFixed(2);
				str+='<p class="text-muted text-capital">absent</p>';
				str+='<h4>'+result.count+'<span class="font-10 text-danger"> '+absentPrecent+'</span></h4>';
			str+='</td>';    
		str+='</tr>';
		str+='</table> ';
		$("#officeAttendanceTdlsId").html(str);   
	}
	function getAttendanceOverViewForPartyOfficeWise(){
		$("#officeAttendanceTdlsDeptWiseId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var fromDate = $("#dateRangeIdForAttendance").val();
		var toDate = $("#dateRangeIdForAttendance").val();
		var jsObj={ 
			fromDate : fromDate,   
			toDate : toDate
		};
		$.ajax({
			type : 'GET',
			url : 'getAttendanceOverViewForPartyOfficeWiseAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#officeAttendanceTdlsDeptWiseId").html('');
			if(result != null && result.length > 0){
				buildOfficeAttendanceOfficeWiseDtls(result);
			}else{
				$("#officeAttendanceTdlsDeptWiseId").html("Data Not Available.");
			} 
		});
	}
	function buildOfficeAttendanceOfficeWiseDtls(result){
		var str = '';
		var datStr = changeDateFormat($("#dateRangeIdForAttendance").val());
		for(var i in result){
			if(today==$("#dateRangeIdForAttendance").val()){
				str+='<h4 class="text-capital m_top20">'+result[i].name+' - <small>(TODAY - '+datStr+')</small></h4>';  
			}else{
				str+='<h4 class="text-capital m_top20">'+result[i].name+' - <small>('+datStr+')</small></h4>';
			}
			str+='<table class="table tableTraining bg_ED">';
				str+='<tr>';
					str+='<td>';
						str+='<p class="text-muted text-capital">total members</p>';
						str+='<h4 class="mainBlockCls" style="cursor:pointer;" attr_office_id="'+result[i].id+'" attr_status="total">'+result[i].actualCount+'</h4>';
					str+='</td>';
					str+='<td>';
					var presentPrecent = (result[i].availableCount*(100/result[i].actualCount)).toFixed(2);
						str+='<p class="text-muted text-capital">present</p>';  
						str+='<h4 class="mainBlockCls" style="cursor:pointer;" attr_office_id="'+result[i].id+'" attr_status="present">'+result[i].availableCount+'<span class="font-10 text-success"> '+presentPrecent+'</span></h4>';
					str+='</td>';
					str+='<td>';
					var absentPrecent = (result[i].count*(100/result[i].actualCount)).toFixed(2);
						str+='<p class="text-muted text-capital">absent</p>';
						str+='<h4 class="mainBlockCls" style="cursor:pointer;" attr_office_id="'+result[i].id+'" attr_status="absent">'+result[i].count+'<span class="font-10 text-danger"> '+absentPrecent+'</span></h4>';
					str+='</td>';      
				str+='</tr>';
			str+='</table>'; 
			$("#officeAttendanceTdlsDeptWiseId").html(str);     
		}  
	}
	$(document).on('click','.mainBlockCls',function(){
		
		var officeId = $(this).attr("attr_office_id");
		var status = $(this).attr("attr_status");
		var fromDate = $("#dateRangeIdForAttendance").val();
		var toDate = $("#dateRangeIdForAttendance").val(); 
		var officeIdArr = [];
		officeIdArr.push(officeId);
		$("#memberId").html(''); 
		$("#positionId").html('');       
		$("#myModalLabel").html('');
		if(officeId==1){
			$("#myModalLabel").html('HYDERABAD PARTY OFFICE EMPLOYEE DETAILS');
		}
		if(officeId==2){
			$("#myModalLabel").html('GUNTUR PARTY OFFICE EMPLOYEE DETAILS');
		}
		
		$("#processingImgAttendId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#myModelId").modal("show");
		var jsObj={ 
			fromDate : fromDate,
			toDate : toDate,
			deptIdArr : globalDeptIdsArr,
			officeIdArr : officeIdArr,
			status : "",
			type : "office"  
		};    
		$.ajax({          
			type : 'GET',    
			url : 'getAttendeeDtlsOfficeWiseForDayAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#processingImgAttendId").html('');
			
			if(result != null && result.length > 0){
				if(status == "present"){
					buildPresentEmployeeDtls(result,status);
				}else if(status == "absent"){
					buildAbsentEmployeeDtls(result,status);
				}else{
					buildEmployeeDtls(result);
				}
				
			}else{
				$("#memberId").html('Data Not Available');  
			}           
		});  
	});
	function buildAbsentEmployeeDtls(result,status){
		var str = '';
		str+='<table class="table table-condensed" id="employeeDtlsId">';
		str+='<thead>';
		str+='<th>DEPT NAME</th>';
		str+='<th>EMPLOYEE NAME</th>';    
		str+='<th>MOBILE NO</th>'; 
		str+='<th>STATUS</th>';
		str+='<th>ATTENDED TIME</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			if(result[i].status=="present"){      
				continue;  
			}   
			str+='<tr>'; 
			str+='<td>'+result[i].districtName.toUpperCase()+'</td>';
			str+='<td>'+result[i].name.toUpperCase()+'</td>';
			str+='<td>'+result[i].mobileNo+'</td>'; 
			if(result[i].status=="absent"){
				str+='<td class="text-danger">'+result[i].status.toUpperCase()+'</td>';    
			}else{
				str+='<td class="text-success">'+result[i].status.toUpperCase()+'</td>';   
			} 
			if(result[i].wish != null){
				str+='<td>'+result[i].wish.toUpperCase()+'</td>';
			}else{  
				str+='<td>-</td>';  
			}	
			str+='</tr>';   
		} 
		$("#processingImgId").html('');   
		$("#memberId").html(str);    
		$("#employeeDtlsId").dataTable();    
	}
	function buildPresentEmployeeDtls(result,status){
		var totalLate = 0;
		var str = '';
		var str2 = '';
		str+='<table class="table table-condensed" id="employeeDtlsId">';
		str+='<thead>';
		str+='<th>DEPT NAME</th>';
		str+='<th>EMPLOYEE NAME</th>';    
		str+='<th>MOBILE NO</th>'; 
		str+='<th>STATUS</th>';
		str+='<th>ATTENDED TIME</th>';
		str+='<th>LATE COMER</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){  
			if(result[i].status=="absent"){
				continue;  
			}   
			str+='<tr>'; 
			str+='<td>'+result[i].districtName.toUpperCase()+'</td>';
			str+='<td>'+result[i].name.toUpperCase()+'</td>';
			str+='<td>'+result[i].mobileNo+'</td>'; 
			if(result[i].status=="absent"){
				str+='<td class="text-danger">'+result[i].status.toUpperCase()+'</td>';    
			}else{
				str+='<td class="text-success">'+result[i].status.toUpperCase()+'</td>';   
			} 
			if((parseInt(result[i].wish.substring(0,2)) == 10 && parseInt(result[i].wish.substring(3,5)) >= 31) || (parseInt(result[i].wish.substring(0,2)) >= 11 )){
				str+='<td class="text-danger">'+result[i].wish.toUpperCase()+'</td>';    
				totalLate = totalLate + 1;    
			}else{  
				str+='<td class="text-success">'+result[i].wish.toUpperCase()+'</td>';  
			}
			if((parseInt(result[i].wish.substring(0,2)) == 10 && parseInt(result[i].wish.substring(3,5)) >= 31) || (parseInt(result[i].wish.substring(0,2)) >= 11 )){
				str+='<td class="text-danger">Yes</td>';
			}else{  
				str+='<td class="text-success">No</td>';  
			}
			str+='</tr>';         
		} 
		str2+='<span class="label label-warning" style="margin-right: 5px;">Late Commings-'+totalLate+'</span>';
		$("#positionId").html(str2); 
		$("#processingImgId").html('');   
		$("#memberId").html(str);    
		$("#employeeDtlsId").dataTable();    
	}
	function buildEmployeeDtls(result){
		var total = 0; 
		var present = 0;
		var absent = 0;
		var totalLate = 0;
		var str = '';
		var str2 = '';
		str+='<table class="table table-condensed" id="employeeDtlsId">';
		str+='<thead>';
		str+='<th>DEPT NAME</th>';
		str+='<th>EMPLOYEE NAME</th>';    
		str+='<th>MOBILE NO</th>'; 
		str+='<th>STATUS</th>';
		str+='<th>ATTENDED TIME</th>';
		str+='<th>LATE COMER</th>';      
		str+='</thead>';
		str+='<tbody>';
		total = result.length;
		for(var i in result){
			if(result[i].wish != null){
				if((parseInt(result[i].wish.substring(0,2)) == 10 && parseInt(result[i].wish.substring(3,5)) >= 31) || (parseInt(result[i].wish.substring(0,2)) >= 11 )){
					totalLate = totalLate + 1;      
				}
			}
			
			
			str+='<tr>'; 
			str+='<td>'+result[i].districtName.toUpperCase()+'</td>';
			str+='<td>'+result[i].name.toUpperCase()+'</td>';
			str+='<td>'+result[i].mobileNo+'</td>'; 
			if(result[i].status=="absent"){
				str+='<td class="text-danger">'+result[i].status.toUpperCase()+'</td>';
				absent = absent + 1;
			}else{
				str+='<td class="text-success">'+result[i].status.toUpperCase()+'</td>';
				present = present + 1;
			} 
			if(result[i].wish != null){
				if((parseInt(result[i].wish.substring(0,2)) == 10 && parseInt(result[i].wish.substring(3,5)) >= 31) || (parseInt(result[i].wish.substring(0,2)) >= 11 )){
					str+='<td class="text-danger">'+result[i].wish.toUpperCase()+'</td>';    
					
				}else{  
					str+='<td class="text-success">'+result[i].wish.toUpperCase()+'</td>';  
				}
			}else{    
				str+='<td>-</td>';  
			}
			if(result[i].wish != null){
				if((parseInt(result[i].wish.substring(0,2)) == 10 && parseInt(result[i].wish.substring(3,5)) >= 31) || (parseInt(result[i].wish.substring(0,2)) >= 11 )){
					str+='<td class="text-danger">Yes</td>';  
				}else{  
					str+='<td class="text-success">No</td>';  
				}
			}else{    
				str+='<td>-</td>';  
			}
			
			str+='</tr>';       
		} 
		str2+='<span class="label label-primary" style="margin-right: 5px;">All-'+total+'</span>'; 
		str2+='<span class="label label-success" style="margin-right: 5px;">Attended-'+present+'</span>';    
		str2+='<span class="label label-warning" style="margin-right: 5px;">Late Commings-'+totalLate+'</span>';     
		str2+='<span class="label label-danger" style="margin-right: 5px;">Absent-'+absent+'</span>';    
		$("#positionId").html(str2); 
		$("#processingImgId").html('');   
		$("#memberId").html(str);    
		$("#employeeDtlsId").dataTable();    
	}
	$(document).on('click','.mainExpandCls',function(){
		getAttendanceOverViewForPartyOfficeDeptWise();
	});
	function getAttendanceOverViewForPartyOfficeDeptWise(){
		$("#deptWiseAttendanceDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var fromDate = $("#dateRangeIdForAttendance").val();
		var toDate = $("#dateRangeIdForAttendance").val();
		var jsObj={ 
			fromDate : fromDate,
			toDate : toDate
		};
		$.ajax({
			type : 'GET',
			url : 'getAttendanceOverViewForPartyOfficeDeptWiseAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#deptWiseAttendanceDtlsId").html('');
			if(result != null && result.length > 0){
				buildAttendanceOverViewForPartyOfficeDeptWise(result);
			}else{
				$("#deptWiseAttendanceDtlsId").html('Data Not Availble.');  
			}  
		});  
	}
	function buildAttendanceOverViewForPartyOfficeDeptWise(result){
		var str = '';
		if(today==$("#dateRangeIdForAttendance").val()){
			str+='<h4><span class="headingColor text-capital">total - departments employee attendance  - <small>TODAY</small></span></h4>';
		}else{
			str+='<h4><span class="headingColor text-capital">total - departments employee attendance  - <small style="color:#fff">'+$("#dateRangeIdForAttendance").val()+'</small></span></h4>';
		}  
		
		str+='<table class="table tableAttendance" cellspacing="10">';
			str+='<thead>';
				str+='<th></th>';
				str+='<th>total members</th>';    
				str+='<th>present</th>';
				str+='<th>absent</th>';
			str+='</thead>';    
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					if(result[i].actualCount==0){
						str+='<td attr_dept_id="'+result[i].id+'" attr_status="all" >'+result[i].actualCount+'</td>';  
					}else{
						str+='<td attr_dept_id="'+result[i].id+'" attr_status="all" style="cursor:pointer;" class="showMemCls">'+result[i].actualCount+'</td>';  
					}
					if(result[i].availableCount==0){
						str+='<td attr_dept_id="'+result[i].id+'" attr_status="present" >'+result[i].availableCount+'</td>';
					}else{
						str+='<td attr_dept_id="'+result[i].id+'" attr_status="present" style="cursor:pointer;" class="showMemCls">'+result[i].availableCount+'</td>';
					}
					if(result[i].count==0){
						str+='<td attr_dept_id="'+result[i].id+'" attr_status="absent" >'+result[i].count+'</td>';
					}else{
						str+='<td attr_dept_id="'+result[i].id+'" attr_status="absent" style="cursor:pointer;" class="showMemCls">'+result[i].count+'</td>'; 
					}
					     
				str+='</tr>';    
			}
		str+='</table>';
		$("#deptWiseAttendanceDtlsId").html(str); 
	}
	$(document).on('click','.showMemCls',function(){
		$("#myModalLabel").html('PARTY OFFICE EMPLOYEE DETAILS');
		$("#memberId").html('');
		$("#myModelId").modal('show'); 
		$("#processingImgId").show();
		$("#processingImgId").html('<div><center><img style="height:20px" src="images/icons/loading.gif"></center></div>');
		var fromDate = $("#dateRangeIdForAttendance").val();
		var toDate = $("#dateRangeIdForAttendance").val();  
		var deptIdArr = [];
		var deptId = $(this).attr("attr_dept_id");
		if(deptId==0){
			deptIdArr = [5,6,7,8,9,10,11,12,13,15,16,17]; 
		}else{
			deptIdArr.push(deptId);
		}
		
		deptIdArr.push(deptId);
		var officeIdArr = [1,2,3];
		var status = $(this).attr("attr_status");
		var jsObj={ 
			fromDate : fromDate,
			toDate : toDate,
			deptIdArr : deptIdArr,
			officeIdArr : officeIdArr,
			status : status,
			type : "dept"
		};
		$.ajax({          
			type : 'GET',    
			url : 'getAttendeeDtlsOfficeWiseForDayAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#processingImgId").hide();
			if(result != null && result.length > 0){
				showDetailsOfEmployee(result);  
			}else{
				$("#memberId").html('Data Not Available.');   
			}  
		});  
	});
	function showDetailsOfEmployee(result){
		
		var str = '';
		str+='<table class="table table-condensed" id="employeeDtlsId">';
		str+='<thead>';
		str+='<th>DEPT NAME</th>';
		str+='<th>EMPLOYEE NAME</th>';    
		str+='<th>MOBILE NO</th>'; 
		str+='<th>STATUS</th>';
		str+='<th>ATTENDED TIME</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>'; 
			str+='<td>'+result[i].districtName.toUpperCase()+'</td>';
			str+='<td>'+result[i].name.toUpperCase()+'</td>';
			str+='<td>'+result[i].mobileNo+'</td>'; 
			if(result[i].status=="absent"){
				str+='<td class="text-danger">'+result[i].status.toUpperCase()+'</td>';    
			}else{
				str+='<td class="text-success">'+result[i].status.toUpperCase()+'</td>';   
			} 
			if(result[i].wish != null){
				str+='<td>'+result[i].wish.toUpperCase()+'</td>';
			}else{  
				str+='<td>-</td>';  
			}	
			str+='</tr>';   
		} 
		$("#processingImgId").html('');   
		$("#memberId").html(str);
		$("#employeeDtlsId").dataTable();      
	}
	$(document).on('click','#expandForMoreId',function(){
		$("#hydDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#gunDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var dateStr = $("#dateRangeIdForAttendance1").val();
		var dateArr = [];
		dateArr = dateStr.split("-");
		var fromDate = dateArr[0];  
		var toDate = dateArr[1];
		var tableId = "hydDtlsId";
		var officeId = 1;//for Hyd
		getLocationDtls(tableId,officeId,fromDate,toDate);
		$("#hydTopId").html('');
		
		tableId = "gunDtlsId";
		officeId = 2;//for Gun
		$("#gunTopId").html('');      
		getLocationDtls(tableId,officeId,fromDate,toDate);
	});
	function getLocationDtls(tableId,officeId,fromDate,toDate){ 
		var officeIdArr = []; 
		officeIdArr.push(officeId);
		//"09/16/2016"; 
		var jsObj={ 
			fromDate : fromDate,
			toDate : toDate,
			deptIdArr : globalDeptIdsArr,
			officeIdArr : officeIdArr,
			status : "",
			type : "office"  
		};
		$.ajax({          
			type : 'GET',    
			url : 'getAttendeeDtlsOfficeWiseForDayAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			if(officeId==1){
				$("#hydDtlsId").html('');
			}
			if(officeId==2){
				$("#gunDtlsId").html('');
			}
			
			if(result != null && result.length > 0){
				buildLocationDtls(result,tableId);
			}else{
				if(officeId==1){
					$("#hydDtlsId").html('Data Not Available');
				}
				if(officeId==2){
					$("#gunDtlsId").html('Data Not Available');
				}	  
			}  
		});  
	}  
	
	function buildLocationDtls(result,tableId){
		var str = '';
		str+='<table class="table text-capital tableAtten" id="view'+tableId+'">';
			str+='<thead>';
				str+='<th>dept name</th>';
				str+='<th>employee name</th>';
				str+='<th>mobile no</th>';
				str+='<th>status</th>';
				str+='<th>attended time</th>';
			str+='</thead>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].districtName+'</td>';
					str+='<td>'+result[i].name+'</td>';
					str+='<td>'+result[i].mobileNo+'</td>';
					if(result[i].status=="absent"){
						str+='<td class="text-danger">'+result[i].status+'</td>';
					}else{
						str+='<td class="text-success">'+result[i].status+'</td>';
					}
					if(result[i].wish == null){
						str+='<td>-</td>';
					}else{
						str+='<td>'+result[i].wish+'</td>';
					}
					
				str+='</tr>';
			}
		str+='</table>';
		$("#"+tableId).html(str);
		$("#view"+tableId).dataTable();           
	}
	function getLocationDtlsForMultiDates(tableId,officeId,customFromDate1,customToDate1){
		var officeIdArr = []; 
		officeIdArr.push(officeId); 
		var jsObj={ 
			fromDate : customFromDate1,
			toDate : customToDate1,
			deptIdArr : globalDeptIdsArr,
			officeIdArr : officeIdArr,
			status : "",
			type : "office"  
		};
		$.ajax({          
			type : 'GET',    
			url : 'getAttendeeDtlsOfficeWiseForDayAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			if(officeId==1){
				$("#hydDtlsId").html('');
			}
			if(officeId==2){
				$("#gunDtlsId").html('');
			}
			
			if(result != null && result.length > 0){
				buildLocationDtlsForMultiDates(result,tableId,officeId,customFromDate1,customToDate1);
			}else{
				if(officeId==1){
					$("#hydDtlsId").html('Data Not Available');
				}
				if(officeId==2){
					$("#gunDtlsId").html('Data Not Available');
				}	  
			}    
		});  
	}
	function buildLocationDtlsForMultiDates(result,tableId,officeId,customFromDate1,customToDate1){
		       
		$("#"+tableId).html('');  
		var str = '';
		str+='<table class="table text-capital tableAtten" id="view'+tableId+'">';
			str+='<thead>';
				str+='<th>dept name</th>';
				str+='<th>employee name</th>';
				str+='<th>mobile no</th>';
				str+='<th>working days</th>';
				str+='<th>holidays</th>';  
				str+='<th>working days present</th>';  
				str+='<th>holidays present</th>';  
				str+='<th class="text-danger">late comings(>10:30)</th>';
				str+='<th>absent</th>';       
			str+='</thead>';
			for(var i in result){      
				str+='<tr>';
					str+='<td class="deptDtlsCls" attr_dept_name="'+result[i].districtName+'" attr_from_date="'+customFromDate1+'" attr_to_date="'+customToDate1+'" attr_dept_id="'+result[i].districtid+'" attr_office_id="'+officeId+'"  style="cursor:pointer;"><u>'+result[i].districtName+'</u></td>';
					str+='<td class="empDtlsCls" attr_name="'+result[i].name+'" attr_dept_name="'+result[i].districtName+'" attr_from_date="'+customFromDate1+'" attr_to_date="'+customToDate1+'" attr_cadre_id="'+result[i].cadreId+'" attr_dept_id="'+result[i].districtid+'" attr_office_id="'+officeId+'" style="cursor:pointer;"><u>'+result[i].name+'</u></td>'; 
					str+='<td>'+result[i].mobileNo+'</td>';    
					str+='<td>'+result[i].id+'</td>';
					str+='<td>'+result[i].lessThan9+'</td>';
					str+='<td>'+result[i].availableCount+'</td>';
					str+='<td>'+result[i].greaterThan13+'</td>';  
					str+='<td class="text-danger">'+result[i].orderId+'</td>';   
					str+='<td>'+result[i].count+'</td>';  
					
					
				str+='</tr>';
			}
		str+='</table>';
		$("#"+tableId).html(str);
		$("#view"+tableId).dataTable();
		getTopAbsentAndIgegular(tableId,officeId,customFromDate1,customToDate1);   
	}
	function getTopAbsentAndIgegular(tableId,officeId,customFromDate1,customToDate1){
		if(officeId==1){
			$("#hydTopId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		}
		if(officeId==2){
			$("#gunTopId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		}
		   
		var officeIdArr = []; 
		officeIdArr.push(officeId);
		var jsObj={ 
			fromDate : customFromDate1,  
			toDate : customToDate1,
			deptIdArr : globalDeptIdsArr,
			officeIdArr : officeIdArr
			 
		};
		$.ajax({          
			type : 'GET',    
			url : 'getTopAbsentAndIregularAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			if(officeId==1){
				$("#hydTopId").html('');
			}
			if(officeId==2){
				$("#gunTopId").html('');
			}
			
			if(result != null && result.length > 0){
				buildTopAbsentAndIgegular(result,officeId,customFromDate1,customToDate1);
			}else{  
				if(officeId==1){
					$("#hydTopId").html('Data Not Available');
				}
				if(officeId==2){
					$("#gunTopId").html('Data Not Available');
				}	  
			}    
		});  
	}
	function buildTopAbsentAndIgegular(result,officeId,customFromDate1,customToDate1){
		var str = '';
		str+='<div class="row">';
			str+='<div class="col-md-6 col-xs-12 col-sm-6">';
				str+='<h4><span class="attenHeading">top absenties</span></h4>';	
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table tableAttenBlock">';
						str+='<thead>';
							str+='<th>Dept name</th>';
							str+='<th>employee name</th>';
							str+='<th>absent</th>';
						str+='</thead>';
						for(var i in result[0]){
							if(result[0][i].count==0){
								break;
							}
							str+='<tr>';
								str+='<td class="deptDtlsCls" attr_dept_name="'+result[0][i].districtName+'" attr_from_date="'+customFromDate1+'" attr_to_date="'+customToDate1+'" attr_dept_id="'+result[0][i].districtid+'" attr_office_id="'+officeId+'" style="cursor:pointer;"><u>'+result[0][i].districtName+'</u></td>';
								str+='<td class="empDtlsCls" attr_name="'+result[0][i].name+'" attr_dept_name="'+result[0][i].districtName+'" attr_from_date="'+customFromDate1+'" attr_to_date="'+customToDate1+'" attr_cadre_id="'+result[0][i].cadreId+'" attr_dept_id="'+result[0][i].districtid+'" attr_office_id="'+officeId+'" style="cursor:pointer;"><u>'+result[0][i].name+'</u></td>';  
								str+='<td>'+result[0][i].count+'</td>';
							str+='</tr>';
							if(i==5){
								break;
							}
						}
						
					str+='</table>';
				str+='</div>';
			str+='</div>';
			
			str+='<div class="col-md-6 col-xs-12 col-sm-6 m_XsTop10">';
				str+='<h4><span class="attenHeading">top irregulars</span></h4>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table tableAttenBlock">';
						str+='<thead>';
							str+='<th>Dept name</th>';
							str+='<th>employee name</th>';
							str+='<th>absent</th>';
						str+='</thead>'; 
						for(var j in result[1]){
							if(result[1][j].orderId==0){
								break;     
							}
							str+='<tr>';     
								str+='<td class="deptDtlsCls" attr_dept_name="'+result[1][j].districtName+'" attr_from_date="'+customFromDate1+'" attr_to_date="'+customToDate1+'" attr_dept_id="'+result[1][j].districtid+'" attr_office_id="'+officeId+'" style="cursor:pointer;"><u>'+result[1][j].districtName+'</u></td>';
								str+='<td class="empDtlsCls" attr_name="'+result[1][j].name+'" attr_dept_name="'+result[1][j].districtName+'" attr_from_date="'+customFromDate1+'" attr_to_date="'+customToDate1+'" attr_cadre_id="'+result[1][j].cadreId+'" attr_dept_id="'+result[1][j].districtid+'" attr_office_id="'+officeId+'" style="cursor:pointer;"><u>'+result[1][j].name+'</u></td>';       
								str+='<td>'+result[1][j].orderId+'</td>';    
							str+='</tr>';
							if(j==5){
								break;
							}
						}  
						
					str+='</table>';    
				str+='</div>';
			str+='</div>';
		str+='</div>';
		if(officeId==1){
			$("#hydTopId").html(str);
		}
		if(officeId==2){
			$("#gunTopId").html(str);
		} 
	}  
	$(document).on('click','.deptDtlsCls',function(){
		var deptId = $(this).attr("attr_dept_id");
		var officeId = $(this).attr("attr_office_id");
		var fromDate = $(this).attr("attr_from_date");
		var toDate = $(this).attr("attr_to_date");
		var deptName = '';
		var officeName = '';
		if(officeId==1){
			$("#officeNameId").html('Hyderabad Party Office');
			officeName = 'Hyderabad Party Office';
		}
		if(officeId==2){
			$("#officeNameId").html('Guntur Party Office');
			officeName = 'Guntur Party Office';
		} 
		$("#diptNameId").html($(this).attr("attr_dept_name"));
		deptName = $(this).attr("attr_dept_name");
		getTotalDtls(deptId,officeId,fromDate,toDate);
		getTimeWiseDtls(deptId,officeId,fromDate,toDate);
		getAttendanceReportTimeToTime(deptId,officeId,fromDate,toDate,deptName,officeName);
		getDateWisePresentAbsentDtls(deptId,officeId,fromDate,toDate); 
		$("#officeHidId").attr("attr_office_hid_id",officeId);
		$("#deptHidId").attr("attr_dept_hid_id",deptId);
		//$("#deptHidId").html($(this).attr("attr_dept_name"));
		
		
		$("#totalCountId").html('');  
		$('#attedanceModalId').html('');
		$("#employeeOverViewId").html('');
		$("#totalCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#attedanceModalId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#employeeOverViewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>'); 
		$("#dayWiseOvervwModal").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		//initialize datepicker
		$("#attenDatePickerModal").daterangepicker({  
			  opens: 'left',
			  startDate: fromDate,
			  endDate: toDate,
			  locale: {
				format: 'MM/DD/YYYY'               
			  },  
			  ranges: {  
				 'This Month': [moment().startOf('month'), moment()],
				 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
				 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
				 'Last 3 Months': [moment().subtract(3, 'month'), moment()],
				 'Last 6 Months': [moment().subtract(6, 'month'), moment()],
				 'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
				 'This Year': [moment().startOf('Year'), moment()]    
			  }   
		})  
		$("#attendanceModal").modal('show');
		
		
		
	});
	function getTotalDtls(deptId,officeId,fromDate,toDate){
		var jsObj={ 
			fromDate : fromDate,  
			toDate : toDate,
			deptId : deptId,
			officeId : officeId
		};
		$.ajax({          
			type : 'GET',    
			url : 'getAttendanceCountForMulitDateAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){  
			$("#totalCountId").html('');
			if(result != null){
				buildTotalDtls(result);
			}else{  
				
			}    
		});  
	}
	function buildTotalDtls(result){
		var str = '';
		str+='<h4><span class="headingColor text-capitalize">department overview</span></h4>';
        str+='<table class="table tableAttendance text-capital">';
          str+='<tr>';
            str+='<td>total employees</td>';
            str+='<td>'+result.count+'</td>';
          str+='</tr>';  
          str+='<tr>';
            str+='<td>total working days</td>';
            str+='<td>'+result.actualCount+'</td>';
          str+='</tr>';
          str+='<tr>';
            str+='<td>total present</td>';
            str+='<td>'+result.availableCount+'</td>';
          str+='</tr>';
          str+='<tr>';
            str+='<td>total Absent</td>';
			if(result.id < 0){
				str+='<td>0</td>';    
			}else{
				str+='<td>'+result.id+'</td>';    
			}
            
          str+='</tr>';
        str+='</table>';
		$("#totalCountId").html(str);
	}
	function getTimeWiseDtls(deptId,officeId,fromDate,toDate){
		var jsObj={ 
			fromDate : fromDate,  
			toDate : toDate,
			deptId : deptId,
			officeId : officeId     
		};
		$.ajax({          
			type : 'GET',      
			url : 'getAttendanceCountForMulitDateTimeWiseAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){  
			
			if(result != null){  
				buildTimeWiseDtls(result,officeId);
			}else{  
				 $("#attedanceModalId").html('');  
			}    
		});  
	}
	function buildTimeWiseDtls(result,officeId){  
	if(officeId==1){
		var title = "HYDERABAD PARTY OFFICE"
	}
	if(officeId==2){
		var title = "GUNTUR PARTY OFFICE"    
	}
	
  $('#attedanceModalId').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: title  
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
        showInLegend: true,
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: [{
                name: 'Before 9',
				//value:result.id,       
                y: result.id,
        color:'#A4C73C'
            }, {
                name: '9 - 10:30',
				//value:result.id,
                y: result.count,
        color:'#5FB3EA'  
            }, {
                name: '10:30 - 11:30',
				//value:result.id,
                y: result.actualCount,
        color:'#FFEE66'
            }, {
                name: '11:30 - 13',
				//value:result.id,
                y: result.availableCount,
        color:'#EEB703'  
            }, {
                name: 'After 13',
				//value:result.id, 
                y: result.orderId,      
        color:'#FE3902'
            }]
        }]
    });
	}
	function getDateWisePresentAbsentDtls(deptId,officeId,fromDate,toDate){  
		var jsObj={ 
			fromDate : fromDate,  
			toDate : toDate,
			deptId : deptId,
			officeId : officeId       
		};
		$.ajax({          
			type : 'GET',      
			url : 'getDateWisePresentAbsentDtlsAction.action',    
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){  
			$("#dayWiseOvervwModal").html('');
			if(result != null && result.length > 0){
				buildEmpDayWisePresentAndAbsent(result);
			}else{  
				 $("#dayWiseOvervwModal").html("Data Not Available");
			}    
		});    
	}
	function buildEmpDayWisePresentAndAbsent(result){
		var presentCountArr = [];
		var absentCountArr = [];
		var holidayAbsentCountArr = [];
		var dateArr = [];
		for(var i in result){
			presentCountArr.push(result[i].presentCount);
			absentCountArr.push(result[i].absentCount);
			holidayAbsentCountArr.push(result[i].holidayAbsentCount);
			dateArr.push(changeFormat(result[i].date));    
		}
		  
		$('#dayWiseOvervwModal').highcharts({    

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
					categories: dateArr,  
					
				labels: {
						rotation: -45,
						style: {
							fontSize: '13px',
							fontFamily: 'Verdana, sans-serif'
						}
					}
			},

		yAxis: {
		  allowDecimals: false,
		  min: 0,
		  title: {
			text: 'Number of employees'    
		  }
		},

		tooltip: {
		  formatter: function () {
			return '<b>' + this.x + '</b><br/>' +
			  this.series.name + ': ' + this.y + '<br/>' +
			  'Total: ' + this.point.stackTotal;
		  }
		},

		plotOptions: {
		  column: {
			stacking: 'normal'
		  }
		},

		series: [ {
		  name: 'Holiday',
		  data: holidayAbsentCountArr,
		  stack: 'attendance',
		  color:'#465866'
		}, {
		  name: 'Absent',  
		  data: absentCountArr,        
		  stack: 'attendance',
		  color:'#F36800'
		},{
		  name: 'Present',
		  data: presentCountArr,
		  stack: 'attendance',
		  color:'#31AA74'
		}]    
	  }); 

	}
	function getAttendanceReportTimeToTime(deptId,officeId,fromDate,toDate,deptName,officeName){
		var jsObj={ 
			fromDate : fromDate,  
			toDate : toDate,
			deptId : deptId,
			officeId : officeId       
		};
		$.ajax({          
			type : 'GET',      
			url : 'getAttendanceReportTimeToTimeAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){  
			$("#employeeOverViewId").html('');
			if(result != null && result.length > 0){
				buildAttendanceReportTimeToTime(result,officeId,deptId,deptName,officeName);
			}else{  
				 $("#employeeOverViewId").html("Data Not Available");
			}    
		});  
	}
	function buildAttendanceReportTimeToTime(result,officeId,deptId,deptName,officeName){
		var dateArrStr = $("#attenDatePickerModal").val();
		var dateArr = dateArrStr.split("-");
		var fromDate = dateArr[0].trim();    
		var toDate = dateArr[1].trim();
		
		var str = '';
		str+='<table class="table text-capital" id="employeeOverVwId">';
			str+='<thead>';
				str+='<th>employee name</th>';
				str+='<th>mobile no</th>';
				str+='<th>total working days</th>';
				str+='<th>absent total</th>';
				str+='<th>present total</th>';
				str+='<th style="color:#A4C73C;"> <9:00 </th>';
				str+='<th style="color:#5FB3EA;"> 09:00 TO 10:30 </th>';
				str+='<th style="color:#FFEE66;"> 10:30 TO 11:30 </th>';
				str+='<th style="color:#EEB703;"> 11:30 TO 13:00 </th>';
				str+='<th style="color:#FE3902;"> >13:00 </th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td class="empDtlsCls" attr_name="'+result[i].name+'" attr_office_id="'+officeId+'" attr_dept_name="'+deptName+'" attr_from_date="'+fromDate+'" attr_to_date="'+toDate+'" attr_cadre_id="'+result[i].id+'" attr_dept_id="'+deptId+'" style="cursor:pointer;"><u>'+result[i].name+'</u></td>';      
					str+='<td>'+result[i].mobileNo+'</td>';       
					str+='<td>'+result[i].availableCount+'</td>';
					if(result[i].actualCount < 0){
						str+='<td>0</td>';
					}else{      
						str+='<td>'+result[i].actualCount+'</td>';
					}
					str+='<td>'+result[i].count+'</td>';
					str+='<td>'+result[i].lessThan9+'</td>';
					str+='<td>'+result[i].between9To1030+'</td>';
					str+='<td>'+result[i].between1030To1130+'</td>';
					str+='<td>'+result[i].between1130To13+'</td>';
					str+='<td>'+result[i].greaterThan13+'</td>';
				str+='</tr>';
			}	 
			str+='</tbody>';
		str+='</table>';
		$("#employeeOverViewId").html(str);
		$("#employeeOverVwId").dataTable();
		
	}
	$(document).on('click','.empDtlsCls',function(){     
		//debugger; 
		var deptId = $(this).attr("attr_dept_id");
		var officeId = $(this).attr("attr_office_id");
		var fromDate = $(this).attr("attr_from_date");
		var toDate = $(this).attr("attr_to_date");
		var cadreId = $(this).attr("attr_cadre_id");
		
		$("#officeHidId").attr("attr_office_hid_id",officeId);
		$("#deptHidId").attr("attr_dept_hid_id",deptId);
		$("#cadreHidId").attr("attr_cadre_hid_id",cadreId);
		
		
		$("#singleEmployeeOverViewId").html('');
		$("#tableAttendanceId").html('');
		$("#attedanceModalForEmpId").html('');
		$("#officeNameForEmpId").html('');      
		$("#diptNameForEmpId").html('');
		$("#attendanceModalEmplo").modal('show');  
		$("#singleEmployeeOverViewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#tableAttendanceId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#attedanceModalForEmpId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		getTotalDtlsEmployee(deptId,officeId,fromDate,toDate,cadreId);
		getTimeWiseDtlsEmployee(deptId,officeId,fromDate,toDate,cadreId);
		getDateWisePresentAbsentDtlsEmployee(deptId,officeId,fromDate,toDate,cadreId);  
		
		if(officeId==1){
			$("#officeNameForEmpId").html('HYDERABAD PARTY OFFICE');
		}
		if(officeId==2){
			$("#officeNameForEmpId").html('GUNTUR PARTY OFFICE');
			
		}
		$("#diptNameForEmpId").html($(this).attr("attr_dept_name")+'-'+$(this).attr("attr_name"));
		$("#attenDatePickerModalForEmpId").daterangepicker({  
			  opens: 'left',
			  startDate: fromDate,
			  endDate: toDate,
			  locale: {
				format: 'MM/DD/YYYY'               
			  },  
			  ranges: {  
				 'This Month': [moment().startOf('month'), moment()],
				 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
				 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
				 'Last 3 Months': [moment().subtract(3, 'month'), moment()],
				 'Last 6 Months': [moment().subtract(6, 'month'), moment()],
				 'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
				 'This Year': [moment().startOf('Year'), moment()]    
			  }   
		})  
		 
		
	});
	function getTotalDtlsEmployee(deptId,officeId,fromDate,toDate,cadreId){
		var jsObj={ 
			fromDate : fromDate,  
			toDate : toDate,
			deptId : deptId,
			officeId : officeId,
			cadreId : cadreId
		};
		$.ajax({          
			type : 'GET',    
			url : 'getAttendanceCountForMulitDateForEmpAction.action',    
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){  
			
			$("#tableAttendanceId").html('');
		
			if(result != null){
				buildTotalDtlsEmployee(result);
			}else{  
				$("#tableAttendanceId").html('Data Not Available');
			}    
		});  
	}
	function buildTotalDtlsEmployee(result){
		var str ='';
		str+='<table class="table tableAttendance text-capital" >';
		 
		  str+='<tr>';
			str+='<td>total working days</td>';
			str+='<td>'+result.actualCount+'</td>';
		  str+='</tr>';
		 str+=' <tr>';
			str+='<td>total present</td>';
			str+='<td>'+result.availableCount+'</td>';
		  str+='</tr>';
		  str+='<tr>';
			str+='<td>total Absent</td>'; 
			str+='<td>'+result.id+'</td>';  
		
			
		  str+='</tr>';
		str+='</table>';
		$("#tableAttendanceId").html(str); 
		
		
	}
	function getTimeWiseDtlsEmployee(deptId,officeId,fromDate,toDate,cadreId){
		var jsObj={   
			fromDate : fromDate,  
			toDate : toDate,
			deptId : deptId,
			officeId : officeId,
			cadreId : cadreId
		};
		$.ajax({          
			type : 'GET',      
			url : 'getAttendanceCountForMulitDateTimeWiseForEmpAction.action',  
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){  
			$('#attedanceModalForEmpId').html('');
			if(result != null){  
				buildTimeWiseDtlsForEmp(result,officeId);
				
			}else{  
				 $("#attedanceModalForEmpId").html('Data Not Available');  
			}    
		});  
	}
	function buildTimeWiseDtlsForEmp(result,officeId){
		$('#attedanceModalForEmpId').highcharts({
        chart: {
            plotBackgroundColor: null,  
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: "EMPLOYEE OVERVIEW"    
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
        showInLegend: true,
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: [{
                name: 'Before 9',
				//value:result.id,         
                y: result.id,
        color:'#A4C73C'
            }, {
                name: '9 - 10:30',
				//value:result.id,
                y: result.count,
        color:'#5FB3EA'  
            }, {
                name: '10:30 - 11:30',
				//value:result.id,
                y: result.actualCount,
        color:'#FFEE66'
            }, {
                name: '11:30 - 13',
				//value:result.id,
                y: result.availableCount,
        color:'#EEB703'  
            }, {
                name: 'After 13',
				//value:result.id, 
                y: result.orderId,      
        color:'#FE3902'
            }]
        }]
    });
	
	}
	function getDateWisePresentAbsentDtlsEmployee(deptId,officeId,fromDate,toDate,cadreId){
		var jsObj={ 
			fromDate : fromDate,  
			toDate : toDate,
			deptId : deptId,
			officeId : officeId,
			cadreId : cadreId     
		};
		$.ajax({          
			type : 'GET',      
			url : 'getDateWisePresentAbsentDtlsForEmployeeAction.action',        
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){  
			$("#singleEmployeeOverViewId").html('');
			if(result != null && result.length > 0){
				buildEmpDayWisePresentAndAbsentForEmp(result);
			}else{  
				 $("#singleEmployeeOverViewId").html("Data Not Available");
			}      
		});      
	}
	function buildEmpDayWisePresentAndAbsentForEmp(result){
		var presentCount = [];
		var absentCount=[];
		var holidayCount = [];
		var dateArr = [];
		for(var i in result){
			
			if(result[i].isHoliday=="yes"){
				presentCount.push(0);  
				holidayCount.push(1);   
				absentCount.push(0);
			}else{
				presentCount.push(result[i].presentCount);
				holidayCount.push(0);
				absentCount.push(result[i].absentCount);    
			}
			if(result[i].isHoliday=="yes" && result[i].presentCount==1){
				dateArr.push(changeFormat(result[i].date)+"(P)");         
			}else{
				dateArr.push(changeFormat(result[i].date)); 
			}
			  			
		}
		$('#singleEmployeeOverViewId').highcharts({    

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
					categories: dateArr,  
					
				labels: {
						rotation: -45,
						style: {
							fontSize: '13px',
							fontFamily: 'Verdana, sans-serif'
						}
					}
			},

		yAxis: {
		  allowDecimals: false,  
		  min: 0,
		  title: {
			text: 'Day Wise Status'    
		  }
		},

		tooltip: {
		  formatter: function () {
			return '<b>' + this.x + '</b><br/>' +
			  this.series.name + ': ' + this.y + '<br/>' +
			  'Total: ' + this.point.stackTotal;
		  }
		},

		plotOptions: {
		  column: {
			stacking: 'normal'
		  }
		},

		series: [ {
		  name: 'Holiday',
		  data: holidayCount,
		  stack: 'attendance',
		  color:'#465866'
		}, {
		  name: 'Absent',  
		  data: absentCount,          
		  stack: 'attendance',
		  color:'#F36800'
		},{
		  name: 'Present',
		  data: presentCount,
		  stack: 'attendance',
		  color:'#31AA74'
		}]    
	  }); 
		  
	}  
	$(document).on('click','.closeButtonCls',function(){
		setTimeout(function(){
		$('body').addClass("modal-open");
		}, 500);    
	});
	/*Notes Functionality*/
	function displayDashboardCommentsForAttendance(dashBoardComponentId){
		var jsObj={
			dashBoardComponentId:dashBoardComponentId
		}	
		$.ajax({
		 type: "POST",
		 url: "displayDashboardCommentsAction.action",
		 data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length >0){
			 var str=''; 
				 
			 str+='<ul class="notesUlAttendance m_top20" style="text-transform: none;font-weight: normal;font-size: 14px;">';  	
				for(var i in result){ 
					str+='<li style="margin-top:3px;">'; 
					str+='<span class="notesTextAttendance" id="editTextAttendanceId'+i+'"  attr_commentId="'+result[i].dashBoardCommentId+'">'+result[i].comment+' </span>- <span class="text-muted"><i>'+result[i].insertedTime+'</i></span>';
					str+='<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesAttendance" attr_cmt_id="editTextAttendanceId'+i+'" id="'+result[i].dashBoardCommentId+'" onClick="deleteDashBoardcomments(this.id);"></i>';
					str+='<i class="glyphicon glyphicon-edit pull-right hoverBlock editNotesAttendance" attr_cmt_id="editTextAttendanceId'+i+'" attr_comment="'+result[i].comment+'"></i>';
					str+='</li>';
				}
			str+='</ul>';
				
				$("#notesAttendanceId").html(str);	 
			}
		});
	}
	function deleteDashBoardcomments(dashboardCommentId)
	{
		var jsObj={
			dashboardCommentId : dashboardCommentId
		}	
		$.ajax({
		 type: "POST",
		 url: "deleteDashBoardcommentsAction.action",
		 data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){	
				if(result.message == "success"){
					
				}
			}
		});
	}

	function savingDashboardCommentForAttendance(dashboardComponentId){  
		var comment=$(".notesAreaAttendance").val();
		if(comment.trim() ==""){
			  $("#attendanceId").html("Notes Required.");
			  return;
		  }
		var editId = $("#cmtAttendanceId").val();
		//$("#"+editId).parent().html(' ');
		$("#"+editId).html(comment);
		 var dashboardCommentId=0;
		 if($(".notesAreaAttendance").attr("attr_commentid")>0)
		 {
			dashboardCommentId=$(".notesAreaAttendance").attr("attr_commentid");		
		 }

		var jsObj={
			comment:comment,
			dashboardComponentId: dashboardComponentId,
			dashboardCommentId : dashboardCommentId
		}	
		$.ajax({
		 type: "POST",
		 url: "savingDashboardCommentAction.action",
		 data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){	
				if(result.message == "success"){
					displayDashboardCommentsForAttendance(7);
				}
			}			
		});    
	}
	$(document).on("click",".notesIconattendance",function(){
		$(this).closest(".panel-heading").find(".notesDropDown").toggle();
	});
	$(document).on("click",".deleteNotesAttendance",function(){
		$(this).closest("li").remove();
	});
	$(document).on("click",".editNotesAttendance",function(){ 
		var commentId = $(this).attr("attr_cmt_id");
		var commentId1 = $(this).parent().find(".notesTextAttendance").attr("attr_commentid");
		var notesHtml = $("#"+commentId).html();
		$(".notesAreaAttendance").val(notesHtml);  
		$(".notesAreaAttendance").attr("attr_commentid",commentId1);  
		$("#cmtId").val(commentId); 
		//$("#cmtId").val();
		$("#attendanceId").html('');		
	});

	$(document).on("click",".btnCustomCreateAttendance",function(){
		var getNewNotes = $(".notesAreaAttendance").val();
		var todayDate = moment().format("DD MMMM YYYY");
		var cmtId = $("#cmtId").val();
		var commentText = '<span class="notesText" id="'+cmtId+'" >'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i  class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesAttendance"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes" attr_cmt_id="'+cmtId+'"></i>'; 
		if(cmtId>0)
		$(".notesUlAttendance").append("<li>"+commentText+"</li>");
		$(".notesAreaAttendance").val('');	
	});

	/*Notes Functionality End*/	

	function changeDateFormat(dateStr)
	{
		var todayArr = dateStr.split("/");
		return todayArr[1]+'-'+todayArr[0]+'-'+todayArr[2];
	}
	function changeFormat(dateStr){
		var date = dateStr.split('-');
		return date[2]+'-'+date[1]+'-'+date[0];    
	}  