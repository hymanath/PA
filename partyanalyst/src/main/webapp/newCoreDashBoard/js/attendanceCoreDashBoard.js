$(document).on("click",".attendaceIconExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".attendanceBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	$(".attendanceBlockMore,.moreAttBlocksIcon").toggle();
	$(".dateRangePickerClsForAttendance").toggleClass("hide")
	if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		//getUserTypeWiseNewsCounts(1);
	}else{
		$(".newsHiddenMoreBlock,moreAttBlocks").hide();
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
		$(".eventsHiddenBlock,.moreEventsBlocks").hide();
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForEvents").toggleClass("hide");
	}else if( $(".newsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".newsHiddenBlock,.morenewsBlocksIcon,.newsHiddenMoreBlock").hide();
		$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForNews").toggleClass("hide");
	}
});

$("#dateRangeIdForAttendance").daterangepicker({
	singleDatePicker : true
})
$('#dateRangeIdForAttendance').on('apply.daterangepicker', function(ev, picker) {
  customStartDate = picker.startDate.format('DD/MM/YYYY');
  customEndDate = picker.endDate.format('DD/MM/YYYY');  
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
	$(function () {
		getAttendanceOverViewForPartyOffice();
		getAttendanceOverViewForPartyOfficeWise();
	});
	function getAttendanceOverViewForPartyOffice(){
		$("#officeAttendanceTdlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var fromDate = "09-21-2016";
		var toDate = "09-21-2016";
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
		str+='<h4 class="text-capital">total members - <small>today</small></h4>';
		str+='<table class="table tableTraining bg_ED">';
		str+='<tr>';
			str+='<td>';
				str+='<p class="text-muted text-capital">total members</p>';
				str+='<h4>'+result.actualCount+'</h4>';
			str+='</td>';
			str+='<td>';
			var presentPrecent = (result.availableCount*(100/result.actualCount)).toFixed(2);
				str+='<p class="text-muted text-capital">prsent</p>';
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
		var fromDate = "09-21-2016";
		var toDate = "09-21-2016";
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
		for(var i in result){
			str+='<h4 class="text-capital m_top20">'+result[i].name+' - <small>today</small></h4>';  
			str+='<table class="table tableTraining bg_ED">';
				str+='<tr>';
					str+='<td>';
						str+='<p class="text-muted text-capital">total members</p>';
						str+='<h4>'+result[i].actualCount+'</h4>';
					str+='</td>';
					str+='<td>';
					var presentPrecent = (result[i].availableCount*(100/result[i].actualCount)).toFixed(2);
						str+='<p class="text-muted text-capital">prsent</p>';
						str+='<h4>'+result[i].availableCount+'<span class="font-10 text-success"> '+presentPrecent+'</span></h4>';
					str+='</td>';
					str+='<td>';
					var absentPrecent = (result[i].count*(100/result[i].actualCount)).toFixed(2);
						str+='<p class="text-muted text-capital">absent</p>';
						str+='<h4>'+result[i].count+'<span class="font-10 text-danger"> '+absentPrecent+'</span></h4>';
					str+='</td>';
				str+='</tr>';
			str+='</table>'; 
			$("#officeAttendanceTdlsDeptWiseId").html(str);     
		}  
	}
	$(document).on('click','.mainExpandCls',function(){
		getAttendanceOverViewForPartyOfficeDeptWise();
	});
	function getAttendanceOverViewForPartyOfficeDeptWise(){
		$("#deptWiseAttendanceDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var fromDate = "09-21-2016";
		var toDate = "09-21-2016";
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
		str+='<h4><span class="headingColor text-capital">total - departments employee attendance  - <small>Today</small></span></h4>';
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
		var fromDate = "09-21-2016";
		var toDate = "09-21-2016";
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
			
			if(result != null && result.length > 0){
				showDetailsOfEmployee(result);  
			}else{
				
			}  
		});  
	});
	function showDetailsOfEmployee(result){
		myModalLabel
		$("#myModalLabel").html('PARTY OFFICE EMPLOYEE DETAILS'); 
		$("#myModelId").modal('show'); 
		$("#processingImgId").html('<div><center><img style="height:20px" src="images/icons/loading.gif"></center></div>');
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
	