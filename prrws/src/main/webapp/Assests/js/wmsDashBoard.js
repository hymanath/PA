var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalStatusObj={"ALL":"#000","Earth Work":"#F64340","Supply Pipeline":"#EB6F2C","Laying of Pipeline":"#EB9A1D","Refilling":"#9DC639","Testing":"#97A702","Completed":"#00CA85"}
var globalStatusBackGroundObj={"ALL":"#000","Earth Work":"#FEEBEA","Supply Pipeline":"#FCEFE8","Laying of Pipeline":"#FCF4E7","Refilling":"#F4F8EA","Testing":"#F4F5E4","Completed":"#E4F9F2"}
var currentFromDate=moment().startOf('month').format("DD-MM-YYYY");
var currentToDate=moment().format("DD-MM-YYYY");

$("#dateRangePicker").daterangepicker({
		opens: 'left',
		startDate: currentFromDate,
		endDate: currentToDate,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		ranges: {
		   'Today' : [moment(), moment()],
		   'Yesterday': [moment().subtract(1, 'day'), moment().subtract(1, 'day')],
		   'This Month': [moment().startOf('month'),moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'This Year': [moment().startOf('Year'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'year').startOf('year'), moment().subtract(1, 'year').endOf('year')],
		}
	});
	var dates= $("#dateRangePicker").val();
	var pickerDates = currentFromDate+' - '+currentToDate
	if(dates == pickerDates)
	{
		$("#dateRangePicker").val('This Month');
	}
	$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
		currentFromDate = picker.startDate.format('DD-MM-YYYY');
		currentToDate = picker.endDate.format('DD-MM-YYYY');
		if(picker.chosenLabel == 'This Month')
		{
			$("#dateRangePicker").val('This Month');
		}
		
		onloadCalls();
	});
	
onloadCalls();
function onloadCalls(){
	getWorkTypeWiseCompletedDetails();
}
function getWorkTypeWiseCompletedDetails(){
	$("#overAllWorkTypesDivId").html(spinner);
	var json = {};
	$.ajax({                
		type:'POST',    
		url: 'getWorkTypeWiseCompletedDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildWorkTypeWiseCompletedDetails(result);
		}else{
			$("#overAllWorkTypesDivId").html("No Data Available");
		}
	});
}
function buildWorkTypeWiseCompletedDetails(result){
	var str='';
	var k=0;
	$("#noofUGDWorksId").html(result.length);
	str+='<ul class="list-inline sliderNewCls">';
	for(var i in result){
		k=k+1;
		var completedworks =0;
		if(result[i].completedWorksCount !=null && result[i].completedWorksCount>0){
			completedworks = (result[i].completedWorksCount*100)/result[i].worksCount
		}
		if(i==0){
			str+='<li class="m_top10 active" attr_work_id="'+result[i].govtMainWorkId+'">';
		}else{
			str+='<li class="m_top10" attr_work_id="'+result[i].govtMainWorkId+'">';
		}
		
			str+='<div class="panel panel-default panel_WMS">';
			 str+='<div class="panel-heading">';
				str+='<h4 class="font_BebasNeue f_22_normal text-capital"><span class="border_number">'+k+'</span>&nbsp;&nbsp;&nbsp;'+result[i].govtMainWork+' WORKS</h4>';
			  str+='</div>';
			  str+='<div class="panel-body" style="background-color:#F9F9F9;">';
					str+='<div class="row">';
						str+='<div class="col-sm-12">';
							str+='<h4 class="font_BebasNeue f_22_normal text-capital" style="color:#252D30;">Phase I</h4>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row">';
						str+='<div class="col-sm-6 m_top10">';
							str+='<h3 class="font_BebasNeue worksCountColor f_32">'+completedworks+'</h3>';
							str+='<h5 class="font_BebasNeue">completed works</h5>';
						str+='</div>';
						str+='<div class="col-sm-6 m_top10">';
							str+='<h3 class="font_BebasNeue worksCountColor f_32">'+result[i].completedKms+' KM</h3>';
							str+='<h5 class="font_BebasNeue">completed KM</h5>';
						str+='</div>';
					str+='</div>';
					str+='<div class="bg_yash_color_10 m_top10">';
						str+='<div class="row">';
							str+='<div class="col-sm-4">';
								str+='<h4 class="font_BebasNeue text-capital f_16">Work proposal for</h4>';
								str+='<h4 class="font_BebasNeue text-capital m_top5 f_15">'+result[i].workPraposedAreas+' WORKS</h4>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								str+='<i class="fa fa-hand-o-right hand_icon" aria-hidden="true"></i>';
							str+='</div>';
							str+='<div class="col-sm-3">';
								str+='<h4 class="font_BebasNeue text-capital f_16">Works</h4>';
								str+='<h4 class="font_BebasNeue m_top5 f_15">'+result[i].mainWorksCount+'</h4>';
							str+='</div>';
							str+='<div class="col-sm-3">';
								str+='<h4 class="font_BebasNeue text-capital f_16">Work Zones</h4>';
								str+='<h4 class="font_BebasNeue m_top5 f_15">'+result[i].worksCount+'</h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
			  str+='</div>';
			str+='</div>';
		str+='</li>';
	}
	str+='</ul>';
	$("#overAllWorkTypesDivId").html(str);
	
	timeLineAndLocationWiseDetails(result[0].govtMainWorkId);
	stateLevelWiseDetails(result[0].govtMainWorkId);
	locationWiseDetails(result[0].govtMainWorkId);

	$(".sliderNewCls").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 3,
		slidesToScroll: 1,
		variableWidth: false,
		responsive: [
		{
		  breakpoint: 1024,
		  settings: {
			slidesToShow: 3,
			slidesToScroll: 1,
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
}

function timeLineAndLocationWiseDetails(workTypeId){
	var collapse='';
		collapse+='<div class="panel-group" id="accordionTimeLine" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="headingTimeLine">';
					collapse+='<a role="button" class="panelCollapseIcon"   data-toggle="collapse" data-parent="#accordionTimeLine" href="#collapseTimeLine" aria-expanded="true" aria-controls="collapseTimeLine">';
						collapse+='<h4 class="panel-title text-capital">TIME LINE vs LOCATION - WORKS MONITERING</h4>';
					collapse+='</a>';
				collapse+='</div>';
				collapse+='<div id="collapseTimeLine" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTimeLine">';
					collapse+='<div class="panel-body">';
						collapse+='<div class="row">';
							collapse+='<div class="col-sm-2">';
								collapse+='<label>Level</label>';
								collapse+='<select class="form-control chosen-select" id="levelId">';
									collapse+='<option value="0">Select Level</option>';
									collapse+='<option value="3" selected>District</option>';
									collapse+='<option value="12">Division</option>';
									collapse+='<option value="13">Sub Division</option>';
									collapse+='<option value="5">Mandal</option>';
									//collapse+='<option value="6">Village</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2 districtCls">';
								collapse+='<label>District</label>';
								collapse+='<select class="form-control chosen-select" id="districtId">';
									collapse+='<option value="0">Select District</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2 divisionCls" style="display:none;">';
								collapse+='<label>Division</label>';
								collapse+='<select class="form-control chosen-select" id="divisionId">';
									collapse+='<option value="0">Select Division</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2 subdivisionCls" style="display:none;">';
								collapse+='<label>Sub-Division</label>';
								collapse+='<select class="form-control chosen-select" id="subdivisionId">';
									collapse+='<option value="0">Select Sub Division</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2 mandalCls" style="display:none;">';
								collapse+='<label>Mandal</label>';
								collapse+='<select class="form-control chosen-select" id="mandalId">';
									collapse+='<option value="0">Select Mandal</option>';
								collapse+='</select>';
							collapse+='</div>';
							/* collapse+='<div class="col-sm-2">';
								collapse+='<label>Village</label>';
								collapse+='<select class="form-control chosen-select" id="villageId">';
									collapse+='<option value="0">Select Village</option>';
								collapse+='</select>';
							collapse+='</div>'; */
							collapse+='<div class="col-sm-2">';
								collapse+='<button type="button" class="btn btn-primary btn-sm getTimeLocationCls m_top20" attr_work_id="'+workTypeId+'">SUBMIT</button>';
							collapse+='</div>';
						collapse+='</div>';
						collapse+='<div class="row">';
							collapse+='<div id="workStagesDivId"></div>';
						collapse+='</div>';
						collapse+='<div class="row">';
							collapse+='<div class="col-sm-12">';
								collapse+='<div class="pad_border m_top10">';
									collapse+='<div id="workStagesCommulativeGraphDivId" style="height:200px;"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
						
						
						collapse+='<div class="row">';
							collapse+='<hr class="m_bottom_0" style="border-top: 1px solid #777 !important;"/>';
							collapse+='<div class="col-sm-12 m_top10">';
								collapse+='<div id="timeLineLocationDetailsDivId"></div>';
							collapse+='</div>';
						collapse+='</div>';
				
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	
	$("#timeLinesWorkTypesDivId").html(collapse);
	$(".chosen-select").chosen();
	getAllDistrictsOfAp('districtId','District');
	getAllDistrictsLocationOfAp('locationWiseDistrictId','District');	
	getAllStatusOfWorkType(workTypeId,"workStagesDivId","");
	getLocationStatusDayWiseKms(0,workTypeId);//Graph Call
	getLocationLevelStatusDayWiseKms(12,workTypeId)//Location Level Main Table
}
function getAllDistrictsOfAp(divId,LevelVal){
	$("#"+divId).html('');
	var json = {};
	$.ajax({                
		type:'POST',    
		url: 'getAllDistrictsOfAp',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildSelectBox(result,divId,LevelVal);
		}
	});
}
function getAllDistrictsLocationOfAp(divId,LevelVal){
	$("#"+divId).html('');
	var json = {};
	$.ajax({                
		type:'POST',    
		url: 'getAllDistrictsOfAp',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildSelectBox(result,divId,LevelVal);
		}
	});
}
$(document).on("change","#districtId",function(){
	var districtId=$(this).val();
	getDivisionsOfDistrict(districtId,'divisionId','Division');
});	
$(document).on("change","#locationWiseDistrictId",function(){
	var districtId=$(this).val();
	getDivisionsOfDistrict(districtId,'locationWiseDivisionId','Division');
});
function getDivisionsOfDistrict(districtId,divId,LevelVal){
	$("#"+divId).html('');
	var json = {
		 "districtId":districtId
	};
	$.ajax({                
		type:'POST',    
		url: 'getDivisionsOfDistrict',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildSelectBox(result,divId,LevelVal);
		}
	});
}
$(document).on("change","#divisionId",function(){
	var divisonId=$(this).val();
	getSubDivisionsOfDivision(divisonId,'subdivisionId','Sub Division');
});	
$(document).on("change","#locationWiseDivisionId",function(){
	var divisonId=$(this).val();
	getSubDivisionsOfDivision(divisonId,'locationWiseSubdivisionId','Sub Division');
});	
function getSubDivisionsOfDivision(divisonId,divId,LevelVal){
	$("#"+divId).html('');
	var json = {
		 "divisonId":divisonId
	};
	$.ajax({                
		type:'POST',    
		url: 'getSubDivisionsOfDivision',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildSelectBox(result,divId,LevelVal);
		}
	});
}
$(document).on("change","#subdivisionId",function(){
	var subDivisonId=$(this).val();
	getTehsilsOfSubDivision(subDivisonId,'mandalId','Mandal');
});	
$(document).on("change","#locationWiseSubdivisionId",function(){
	var subDivisonId=$(this).val();
	getTehsilsOfSubDivision(subDivisonId,'locationWiseMandalId','Mandal');
});
function getTehsilsOfSubDivision(subDivisonId,divId,LevelVal){
	$("#"+divId).html('');
	var json = {
		 "subDivisonId":subDivisonId
	};
	$.ajax({                
		type:'POST',    
		url: 'getTehsilsOfSubDivision',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildSelectBox(result,divId,LevelVal);
		}else{
			$("#"+divId).html("<option value='0'>All</option>").chosen().trigger("chosen:updated");
		}
	});
}

function buildSelectBox(result,divId,LevelVal){
	var str='';
	str+='<option value="0">All</option>';
	for(var i in result){
		str+='<option value="'+result[i].key+'">'+result[i].value+'</option>';
	}
	$("#"+divId).html(str).chosen().trigger("chosen:updated");
}
function getAllStatusOfWorkType(workTypeId,divId,locationValue){
	
	$("#"+divId).html(spinner);
	
	var json = {
		"workTypeId":workTypeId
	};
	$.ajax({                
		type:'POST',    
		url: 'getAllStatusOfWorkType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildworkStagesDetails(result,workTypeId,divId,locationValue);
		}else{
			$("#"+divId).html("No Data Available");
		}
		
	});
}
function buildworkStagesDetails(result,workTypeId,divId,locationValue){
	var str='';
	str+='<hr class="m_bottom_0" style="border-top: 1px solid #777 !important;"/>';
	if(result !=null && result.length>0){
			if(divId == "workStagesDivId"){
				str+='<div class="pad_10 status">';
				str+='<ul class="list-inline workStagesCls">';
						str+='<li style="border:2px solid #000" attr_work_statusid ="0" attr_value="ALL" attr_work_id="'+workTypeId+'"><h5 style="background-color:#000">All</h5></li>';
						for(var i in result){
							str+='<li  attr_work_statusid="'+result[i].key+'" attr_value="'+result[i].value+'" attr_work_id="'+workTypeId+'"><h5 style="background-color:'+globalStatusObj[result[i].value]+'">'+result[i].value+'</h5></li>';
						}
					str+='</ul>';
				str+='</div>';
				str+='<hr class="m_bottom_0" style="border-top: 1px solid #777 !important;"/>';
			}else if(divId == "locationWiseWorkStagesDivId"){
				str+='<div class="pad_10 location_status">';
				str+='<ul class="list-inline locationWiseworkStagesCls">';
						str+='<li style="border:2px solid #000" attr_work_statusid ="0" attr_value="ALL" attr_work_id="'+workTypeId+'"><h5 style="background-color:#000">All</h5></li>';
						for(var i in result){
							str+='<li  attr_work_statusid="'+result[i].key+'" attr_value="'+result[i].value+'" attr_work_id="'+workTypeId+'"><h5 style="background-color:'+globalStatusObj[result[i].value]+'" attr_locationLevelId="'+locationValue+'">'+result[i].value+'</h5></li>';
						}
					str+='</ul>';
				str+='</div>';
				str+='<hr class="m_bottom_0" style="border-top: 1px solid #777 !important;margin-top: 10px;"/>';
			}else{
				str+='<div class="pad_10 workZone_status">';
				str+='<ul class="list-inline workZoneworkStagesCls">';
						str+='<li style="border:2px solid #000" attr_work_statusid ="0" attr_value="ALL" attr_work_id="'+workTypeId+'"><h5 style="background-color:#000">All</h5></li>';
						for(var i in result){
							str+='<li  attr_work_statusid="'+result[i].key+'" attr_value="'+result[i].value+'" attr_work_id="'+workTypeId+'"><h5 style="background-color:'+globalStatusObj[result[i].value]+'" attr_locationLevelId="'+locationValue+'">'+result[i].value+'</h5></li>';
						}
					str+='</ul>';
				str+='</div>';
				str+='<hr class="m_bottom_0" style="border-top: 1px solid #777 !important;margin-top: 10px;"/>';
			}
	}
	
	$("#"+divId).html(str);
}
$(document).on("change","#levelId",function(){
	var levelId=$(this).val();
	getAllDistrictsOfAp('districtId','District');
	if(levelId == 3){
		$(".districtCls").show();
		$(".divisionCls,.subdivisionCls,.mandalCls").hide();
	}else if(levelId == 12){
		$(".districtCls,.divisionCls").show();
		$(".subdivisionCls,.mandalCls").hide();
		$("#divisionId").html('');
		$("#divisionId").append('<option value="0">All</option>');
		$("#divisionId").trigger("chosen:updated");
	}else if(levelId == 13){
		$(".districtCls,.divisionCls,.subdivisionCls").show();
		$(".mandalCls").hide();
		$("#divisionId").html('');
		$("#divisionId").append('<option value="0">All</option>');
		$("#divisionId").trigger("chosen:updated");
		$("#subdivisionId").html('');
		$("#subdivisionId").append('<option value="0">All</option>');
		$("#subdivisionId").trigger("chosen:updated");
	}else if(levelId == 5){
		$(".districtCls,.divisionCls,.subdivisionCls,.mandalCls").show();
		$("#divisionId").html('');
		$("#divisionId").append('<option value="0">All</option>');
		$("#divisionId").trigger("chosen:updated");
		$("#subdivisionId").html('');
		$("#subdivisionId").append('<option value="0">All</option>');
		$("#subdivisionId").trigger("chosen:updated");
		$("#mandalId").html('');
		$("#mandalId").append('<option value="0">All</option>');
		$("#mandalId").trigger("chosen:updated");
	}
});	

$(document).on("click",".workStagesCls li",function(){	
	var statusId = $(this).attr("attr_work_statusid")
	var statusName = $(this).attr("attr_value");
	var workTypeId = $(this).attr("attr_work_id");
	$(".workStagesCls li").removeAttr("style");
	$(this).css("border","2px solid "+globalStatusObj[statusName]+"")
	getLocationStatusDayWiseKms(statusId,workTypeId);
	if(statusId == 0){
		getLocationLevelStatusDayWiseKms(12,workTypeId);
	}else{
		getLocationLevelStatusDayWiseKms(statusId,workTypeId);
	}
	
});
$(document).on("click",".locationWiseworkStagesCls li",function(){	
	var statusId = $(this).attr("attr_work_statusid")
	var statusName = $(this).attr("attr_value");
	var workTypeId = $(this).attr("attr_work_id");
	var locationValue = $(this).attr("attr_locationLevelId");
	$(".locationWiseworkStagesCls li").removeAttr("style");
	$(this).css("border","2px solid "+globalStatusObj[statusName]+"")
	
	getLocationOverviewStatusDayWiseKms(workTypeId,statusId,locationValue);
	
	
});
$(document).on("click",".workZoneworkStagesCls li",function(){	
	var statusId = $(this).attr("attr_work_statusid")
	var statusName = $(this).attr("attr_value");
	var workTypeId = $(this).attr("attr_work_id");
	var locationValue = $(this).attr("attr_locationLevelId");
	$(".workZoneworkStagesCls li").removeAttr("style");
	$(this).css("border","2px solid "+globalStatusObj[statusName]+"")
	
	getWorkZoneWorkStategsDetailsInfo(workTypeId,statusId)
	
	
});
$(document).on("click",".getTimeLocationCls",function(){
	var workTypeId = $(this).attr("attr_work_id");	
	$(".workStagesCls li").removeAttr("style");
	$(".workStagesCls li:nth-child(1)").css("border","2px solid #000");
	
	getLocationStatusDayWiseKms(0,workTypeId);
	getLocationLevelStatusDayWiseKms(12,workTypeId);
});

function getLocationStatusDayWiseKms(statusId,workTypeId){
	$("#workStagesCommulativeGraphDivId").html(spinner);
	
	var districtId=$("#districtId").val();
	var divisonId=$("#divisionId").val();
	var subDivisonId=$("#subdivisionId").val();
	var mandalId=$("#mandalId").val();
	
	var json ={
		  "fromDate":currentFromDate,
		  "toDate":currentToDate,
		  "statusId":statusId,
		  "workTypeId":workTypeId,
		  "districtId":districtId,
		  "divisonId":divisonId,
		  "subDivisonId":subDivisonId,
		  "mandalId":mandalId
		}
	$.ajax({                
		type:'POST',    
		url: 'getLocationStatusDayWiseKms',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){//ara
		if(result !=null && result.length>0){
			$("#workStagesCommulativeGraphDivId").css("height","200px");
			buildLocationStatusDayWiseKmsMainGraph(result,"workStagesCommulativeGraphDivId");
		}else{
			$("#workStagesCommulativeGraphDivId").html("No Data Available")
			$("#workStagesCommulativeGraphDivId").removeAttr("style");
		}
	});
}

function buildLocationStatusDayWiseKmsMainGraph(result,divId){
	
	var mainDataArr=[];
	var categoriesArr=[];
	var wpPosArr1=[];
		if(divId == "workStagesCommulativeGraphDivId"){
			for(var i in result[0].list){
				categoriesArr.push(result[0].list[i].insertedTime);
			}
			
			for(var i in result){
				var wpPosArr=[];
				for(var j in result[i].list){
					wpPosArr.push(result[i].list[j].kms)
				}
				var obj={
					name:result[i].documentName,
					data: wpPosArr,
					color:globalStatusObj[result[i].documentName]
				};
				mainDataArr.push(obj)
			}
		}else if(divId == "locationLevelFourthBlockDivId"){
			for(var i in result){
				categoriesArr.push(result[i].insertedTime);
				wpPosArr1.push(result[i].kms)
			}
			var obj={
				name:'CUMULATIVE GRAPH',
				data: wpPosArr1,
				color:"#B2D3F3"
			};
			mainDataArr.push(obj)
		}else{
			for(var i in result){
				categoriesArr.push(result[i].date);
				wpPosArr1.push(result[i].workLenght)
			}
			var obj={
				name:'CUMULATIVE GRAPH',
				data: wpPosArr1,
				color:"#B2D3F3"
			};
			mainDataArr.push(obj)
		}
		
		
	$('#'+divId).highcharts({
		chart: {
			type: 'spline'
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
			categories:categoriesArr
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: ''
			},
			labels: {
				formatter: function () {
					return this.value + 'KM';
				}
			}
		},
		tooltip: {
			formatter: function () {
			var s = '<b>' + this.x + '</b>';

				$.each(this.points, function () {
					if(this.series.name != "Series 1")  
					s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
					this.y+"";
				});

				return s;
			},
			shared: true
		},
		plotOptions: {
			
		},
		series: mainDataArr
	});
	
	
	
	
}

function getLocationLevelStatusDayWiseKms(statusId,workTypeId){
	
	$("#timeLineLocationDetailsDivId").html(spinner);
	
	var locationLevelId = $("#levelId").val();
	var districtId=$("#districtId").val();
	var divisonId=$("#divisionId").val();
	var subDivisonId=$("#subdivisionId").val();
	var mandalId=$("#mandalId").val();
	
	var json ={
		  "fromDate":currentFromDate,
		  "toDate":currentToDate,
		  "workTypeId":workTypeId,
		  "statusId":statusId,
		  "locationLevelId":locationLevelId,
		  "districtId":districtId,
		  "divisonId":divisonId,
		  "subDivisonId":subDivisonId,
		  "mandalId":mandalId
		  
		}
	$.ajax({                
		type:'POST',    
		url: 'getLocationLevelStatusDayWiseKms',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildLocationLevelStatusDayWiseKmsMainTable(result,locationLevelId,workTypeId);
		}else{
			$("#timeLineLocationDetailsDivId").html("No Data Available");
		}
	});
}

function buildLocationLevelStatusDayWiseKmsMainTable(result,locationLevelId,workTypeId){
	var str='';
	var listLenth=result[0].list.length+4
	str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table_custom_WMS">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Location</th>';
					str+='<th style="background-color:#C4EEE6 !important;">Target&nbsp;KMS</th>';
					str+='<th style="background-color:#C4EEE6 !important;">Total&nbsp;KMS</th>';
					str+='<th style="background-color:#C4EEE6 !important;">Average&nbsp;KMS</th>';
					for(var i in result[0].list){
						str+='<th>'+result[0].list[i].insertedTime+'</th>';
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td style="text-align:left !important;"><h5><i class="fa fa-plus plus_icon_WMS openNextTrShowCls active" aria-hidden="true" attr_tr_id="'+i+'"  attr_tr_name="'+result[i].documentName.replace(/\s+/g, '')+'" attr_locationScopeId="'+locationLevelId+'" attr_locationLevelId="'+result[i].documentId+'" attr_work_id="'+workTypeId+'"></i> '+result[i].documentName+'</h5></td>';
						str+='<td style="background-color:#E5FBF7 !important;">'+result[i].kms.toFixed(2)+'&nbsp;km</td>';
						str+='<td style="background-color:#E5FBF7 !important;">'+result[i].completedKms.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].completedPercentage.toFixed(2)+' %</span></td>';
						str+='<td style="background-color:#E5FBF7 !important;">'+result[i].totalAvgKms.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].totalAvgPerc.toFixed(2)+' %</span></td>';
						for(var j in result[i].list){
							str+='<td>'+result[i].list[j].kms.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].list[j].completedPercentage.toFixed(2)+' %</span></td>';
						}
					str+='</tr>';
					str+='<tr class="show'+result[i].documentName.replace(/\s+/g, '')+''+i+'" style="display:none;">';
						str+='<td colspan="'+listLenth+'" style="background-color: #dbdbdb !important;">';
							str+='<div id="subLocationLevelWiseStatusDivId'+i+'"></div>';
						str+='</td">';
					str+='</tr>';	
						
				}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#timeLineLocationDetailsDivId").html(str);
}
$(document).on("click",".openNextTrShowCls",function(){
	var tr_id = $(this).attr("attr_tr_id");
	var tr_name = $(this).attr("attr_tr_name");
	var locationScopeId = $(this).attr("attr_locationScopeId");
	var locationLevelId = $(this).attr("attr_locationLevelId");
	var workTypeId = $(this).attr("attr_work_id");
	if($(this).hasClass('active')){
		$(this).removeClass('fa fa-plus').addClass('fa fa-minus');
		$(this).removeClass('plus_icon_WMS').addClass('minus_icon_WMS');
		$(this).removeClass('active');
		$(".show"+tr_name+tr_id).show(); 
		getLocationLevelSubDayWiseKms(locationScopeId,locationLevelId,tr_id,workTypeId)
	}else{
		$(this).removeClass('fa fa-minus').addClass('fa fa-plus');
		$(this).removeClass('minus_icon_WMS').addClass('plus_icon_WMS');
		$(this).addClass('active');
		$(".show"+tr_name+tr_id).hide(); 
	}
});

function getLocationLevelSubDayWiseKms(locationScopeId,locationLevelId,tr_id,workTypeId){
	$("#subLocationLevelWiseStatusDivId"+tr_id).html(spinner);
	var json ={
		  "fromDate":currentFromDate,
		  "toDate":currentToDate,
		  "workTypeId":workTypeId,
		  "locationScopeId":locationScopeId,
		  "locationLevelId":locationLevelId
		}
	$.ajax({                
		type:'POST',    
		url: 'getLocationLevelSubDayWiseKms',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildLocationLevelSubDayWiseKmsSubTable(result,tr_id);
		}else{
			$("#subLocationLevelWiseStatusDivId"+tr_id).html("No Data Available");
		}
	});
}

function buildLocationLevelSubDayWiseKmsSubTable(result,tr_id){
	var str='';
	str+='<div class="">';
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered table_custom_WMS">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Location</th>';
						str+='<th>Target&nbsp;KMS</th>';
						str+='<th>Total&nbsp;KMS</th>';
						str+='<th>Average&nbsp;KMS</th>';
						for(var i in result[0].list){
							str+='<th>'+result[0].list[i].insertedTime+'</th>';
						}
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				var k=0;
					for(var i in result){
						k=k+1;
						str+='<tr>';
							str+='<td style="background-color:'+globalStatusBackGroundObj[result[i].documentName]+' !important;text-align:left !important;border-right: none !important;"><span class="rounder_WMS" style="background-color:'+globalStatusObj[result[i].documentName]+'">0'+k+'</span> '+result[i].documentName+'</td>';
							str+='<td style="background-color:'+globalStatusBackGroundObj[result[i].documentName]+' !important;">'+result[i].kms.toFixed(2)+'&nbsp;km</td>';
							str+='<td style="background-color:'+globalStatusBackGroundObj[result[i].documentName]+' !important;">'+result[i].completedKms.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].completedPercentage.toFixed(2)+' %</span></td>';
							str+='<td style="background-color:'+globalStatusBackGroundObj[result[i].documentName]+' !important;">'+result[i].totalAvgKms.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].totalAvgPerc.toFixed(2)+' %</span></td>';
							for(var j in result[i].list){
								str+='<td style="background-color:'+globalStatusBackGroundObj[result[i].documentName]+' !important;">'+result[i].list[j].kms.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].list[j].completedPercentage.toFixed(2)+' %</span></td>';
							}
						str+='</tr>';
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	str+='</div>';
	
	$("#subLocationLevelWiseStatusDivId"+tr_id).html(str);
}
function stateLevelWiseDetails(workTypeId){
	var collapse='';
		collapse+='<div class="panel-group" id="accordionStateLevel" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="headingStateLevel">';
					collapse+='<a role="button" class="panelCollapseIcon"   data-toggle="collapse" data-parent="#accordionStateLevel" href="#collapseStateLevel" aria-expanded="true" aria-controls="collapseStateLevel">';
						collapse+='<h4 class="panel-title text-capital">STATE LEVEL OVERVIEW</h4>';
					collapse+='</a>';
				collapse+='</div>';
				collapse+='<div id="collapseStateLevel" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingStateLevel">';
					collapse+='<div class="panel-body">';
						collapse+='<div class="row">';
							collapse+='<div id="stateLevelFirstBlockDivId"></div>';
					    collapse+='</div>';
						collapse+='<div class="row">';
							collapse+='<hr class="m_bottom_0" style="border-top: 1px solid #777 !important;">';
							collapse+='<div id="stateLevelSecondBlockDivId"></div>';
					    collapse+='</div>';
						
						collapse+='<div class="row">';
							collapse+='<hr class="m_bottom_0" style="border-top: 1px solid #777 !important;">';
							collapse+='<div id="stateLevelThirdBlockDivId"></div>';
					    collapse+='</div>';
						
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	
	$("#stateLevelWorkTypesDivId").html(collapse);
	getStateLevelOverAllDetails(workTypeId);
	getRecentWorkDocuments(workTypeId);
	getStatusWiseWorksAndKms(workTypeId);
}
function getStateLevelOverAllDetails(workTypeId){
	$("#stateLevelFirstBlockDivId").html(spinner);
	var json ={
		 	 "workTypeId":workTypeId//comple/target*100 %
		}
	$.ajax({                
		type:'POST',    
		url: 'getStateLevelOverAllDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildStateLevelOverAllDetails(result);
		}else{
			$("#stateLevelFirstBlockDivId").html("No Data Available");
		}
		
	});
}
function buildStateLevelOverAllDetails(result){
	
	var str='';
	var completePerc=(result.completedKms/result.totalKms)*100;
	str+='<div class="col-sm-4">';
		str+='<div class="bg_yash_color_10 m_top10">';
				str+='<div class="row">';
					str+='<div class="col-sm-4">';
						str+='<h3 class="font_BebasNeue text-capital f_16">Work proposal for</h3>';
						str+='<h4 class="font_BebasNeue text-capital m_top10" style="font-size:21px;">'+result.workPraposedAreas+' WORKS</h4>';
					str+='</div>';
					str+='<div class="col-sm-2">';
						str+='<i class="fa fa-hand-o-right hand_icon" aria-hidden="true" ></i>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<h3 class="font_BebasNeue text-capital f_16">Works</h3>';
						str+='<h4 class="font_BebasNeue m_top10" style="font-size:21px;">'+result.mainWorksCount+'</h4>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<h3 class="font_BebasNeue text-capital f_16">Work Zones</h3>';
						str+='<h4 class="font_BebasNeue m_top10" style="font-size:21px;">'+result.worksCount+'</h4>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
	str+='</div>';
	str+='<div class="col-sm-2">';
		str+='<div class="bg_yash_color_10 m_top10 text-center">';
			str+='<h2 class="font_BebasNeue" style="color:#0080FF">'+result.totalKms.toFixed(2)+' KM</h2>';
			str+='<h4 class="font_BebasNeue m_top5 text-capital">TARGET KM</h4>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="col-sm-2">';
		str+='<div class="bg_yash_color_10 m_top10 text-center">';
			str+='<h2 class="font_BebasNeue" style="color:#009A33">'+result.completedKms+' KM</h2>';
			str+='<h4 class="font_BebasNeue m_top5 text-capital">completed KM</h4>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="col-sm-2">';
		str+='<div class="bg_yash_color_10 m_top10 text-center">';
			str+='<h2 class="font_BebasNeue" style="color:#009A33">'+completePerc+' %</h2>';
			str+='<h4 class="font_BebasNeue m_top5 text-capital">completed works</h4>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-2">';
		str+='<div class="bg_yash_color_10 m_top10 text-center">';
			str+='<h3 class="font_BebasNeue text-capital">sanctioned amount</h3>';
			str+='<h3 class="font_BebasNeue m_top5">'+result.estimationCost.toFixed(2)+' Lakhs</h3>';
		str+='</div>';
	str+='</div>';
	$("#stateLevelFirstBlockDivId").html(str);
	
}

function getRecentWorkDocuments(workTypeId){
	$("#stateLevelSecondBlockDivId").html(spinner);
	var json ={
		 	 "workTypeId":workTypeId
		}
	$.ajax({                
		type:'POST',    
		url: 'getRecentWorkDocuments',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildgetRecentWorkDocuments(result);
		}else{
			$("#stateLevelSecondBlockDivId").html("No Data Available");
		}
	});
}
function buildgetRecentWorkDocuments(result){
	var str='';
	str+='<div class="col-sm-12 m_top10">';
		str+='<h3 class="font_BebasNeue" style="color:#252D30;">WORK Images</h3>';
	str+='</div>';
	str+='<div class="col-sm-12">';
	str+='<ul class="list-inline ugdImagesSlickSlider">';
	for(var i in result){
		str+='<li class="col-sm-3 m_top10">';
			str+='<div class="ugdImagesCss">';
				str+='<img src="http://www.mydepartments.in/PRRWS/'+result[i].path+'" style="width: 100%;height: 200px;"/>';
				str+='<div class="card-img-overlay1">';
					str+='<h4 class="font_weight color_white f_16">Mandal : '+result[i].mandalName+'</h4>';
					str+='<h4 class="font_weight color_white m_top5 f_16">Village : '+result[i].panchayatName+'</h4>';
					
					str+='<h4 class="font_weight color_white m_top5 f_16">';
					if(result[i].lattitude !=null && result[i].lattitude>0){
						str+='<span>Lat:'+result[i].lattitude+'</span>';
					}/*else{
						str+='<span>lat: - </span>';
					}*/
					if(result[i].longitude !=null && result[i].longitude>0){
						str+='<span>Long:'+result[i].longitude+'</span>';
					}/*else{
						str+='<span>Long: - </span>';
					}*/
					str+='</h4>';
					str+='<h4 class="font_weight color_white m_top5 f_16">'+result[i].insertedTime+'</h4>';
				str+='</div>';
			str+='</div>';
		str+='</li>';
	}
	str+='</ul>';
	str+='</div>';
	
	$("#stateLevelSecondBlockDivId").html(str);
	$(".ugdImagesSlickSlider").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 4,
		slidesToScroll: 1,
		variableWidth: false,
		responsive: [
		{
		  breakpoint: 1024,
		  settings: {
			slidesToShow: 3,
			slidesToScroll: 1,
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
}

function getStatusWiseWorksAndKms(workTypeId){
	
	$("#stateLevelThirdBlockDivId").html(spinner);
	
	var json ={
		 	 "workTypeId":workTypeId
		}
	$.ajax({                
		type:'POST',    
		url: 'getStatusWiseWorksAndKms',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildStatusWiseWorksAndKms(result);
		}else{
			$("#stateLevelThirdBlockDivId").html("No Data Available");
		}
	});
}
function buildStatusWiseWorksAndKms(result){
	var str='';
	str+='<div class="col-sm-12 m_top10">';
		str+='<h3 class="font_BebasNeue" style="color:#252D30;">Work Zones Status Wise Progress Overview</h3>';
	str+='</div>';	
	str+='<div class="pad_10 m_top10">';
		str+='<div class="row">';
		var k=0;
			for(var i in result){
				k=k+1;
				str+='<div class="col-sm-2">';
					str+='<img src="Assests/images/'+result[i].status+'.png" style="width: 100%;height: 100px;"/>';
					str+='<div class="card-img-overlay" style="height:50px;">';	
						str+='<h5 class="statusRoundCss">0'+k+'</h5>';
					str+='</div>';
					str+='<div class="card-img-overlay2">';	
						str+='<h3 class="color_white font_weight font_BebasNeue">'+result[i].status+'</h3>';
					str+='</div>';
				
					str+='<div class="row">';
						str+='<div class="col-sm-12 m_top5">';
							str+='<div class="bg_StatusWorkCss">';
								str+='<h5 class="font_weight">Work Zones</h5>';
								str+='<input class="m_top10" id="statusBar'+i+'" data-slider-id="statusBar'+i+'Slider" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="'+result[i].completedPercentage.toFixed(0)+'" data-slider-tooltip="hide"/><span class="font_weight pull-right">'+result[i].completedPercentage.toFixed(0)+' %</span>';
								
								str+='<h5 class="font_weight m_top5"><span style="font-size:11px">Total Work Zones -  </span> '+result[i].totalWorks+'<br><span style="position: relative; top: 2px; font-size: 11px;">Started Work Zones - </span>  <span style="position: relative; top: 2px; font-size: 14px;">'+result[i].statusWorks+'</span></h5>';
								
								str+='<hr class="m_bottom_0 m_top10" style="border-top: 1px solid #ddd !important;"/>';
								str+='<h5 class="font_weight m_top10">Works in Km</h5>';
								str+='<input class="m_top10" id="statusBarWorks'+i+'" data-slider-id="statusBarWorks'+i+'Slider" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="'+result[i].completedKmsPercentage.toFixed(0)+'" data-slider-tooltip="hide"/><span class="font_weight pull-right">'+result[i].completedKmsPercentage.toFixed(0)+' %</span>';
								
								str+='<h5 class="font_weight m_top5"><span style="font-size:11px">Total Kms -  </span> '+result[i].totalKms.toFixed(2)+'<br><span style="position: relative; top: 2px; font-size: 11px;">'+result[i].status+' Kms - </span>  <span style="position: relative; top: 2px; font-size: 14px;">'+result[i].statusKms.toFixed(2)+'</span></h5>';
								
								
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
				
		str+='</div>';	
	str+='</div>';
	
	$("#stateLevelThirdBlockDivId").html(str);
	for(var i in result){
		$('#statusBar'+i).slider({
		/* formatter: function(value) {
			return 'Current value: ' + value;
		} */
	});
	$('#statusBarWorks'+i).slider({
		/* formatter: function(value) {
			return 'Current value: ' + value;
		} */
	});
	}
	
}
function locationWiseDetails(workTypeId){
	var collapse='';
		collapse+='<div class="panel-group" id="accordionLocationWise" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="headingLocationWise">';
					collapse+='<a role="button" class="panelCollapseIcon"   data-toggle="collapse" data-parent="#accordionLocationWise" href="#collapseLocationWise" aria-expanded="true" aria-controls="collapseLocationWise">';
						collapse+='<h4 class="panel-title text-capital">LOCATION WISE OVERVIEW</h4>';
					collapse+='</a>';
				collapse+='</div>';
				collapse+='<div id="collapseLocationWise" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingLocationWise">';
					collapse+='<div class="panel-body">';
						collapse+='<div class="row">';
							collapse+='<div class="col-sm-2">';
								collapse+='<label>Level</label>';
								collapse+='<select class="form-control chosen-select" id="locationWiseLevelId">';
									collapse+='<option value="0">Select Level</option>';
									collapse+='<option value="3" selected>District</option>';
									collapse+='<option value="12">Division</option>';
									collapse+='<option value="13">Sub Division</option>';
									collapse+='<option value="5">Mandal</option>';
									//collapse+='<option value="6">Village</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2 locationWiseDistrictCls">';
								collapse+='<label>District</label>';
								collapse+='<select class="form-control chosen-select" id="locationWiseDistrictId">';
									collapse+='<option value="0">Select District</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2 locationWiseDivisionCls" style="display:none;">';
								collapse+='<label>Division</label>';
								collapse+='<select class="form-control chosen-select" id="locationWiseDivisionId">';
									collapse+='<option value="0">Select Division</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2 locationWiseSubdivisionCls" style="display:none;">';
								collapse+='<label>Sub-Division</label>';
								collapse+='<select class="form-control chosen-select" id="locationWiseSubdivisionId">';
									collapse+='<option value="0">Select Sub Division</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2 locationWiseMandalCls" style="display:none;">';
								collapse+='<label>Mandal</label>';
								collapse+='<select class="form-control chosen-select" id="locationWiseMandalId">';
									collapse+='<option value="0">Select Mandal</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2">';
									collapse+='<div class="checkbox m_top30">';
									  collapse+='<label>';
										collapse+='<input type="checkbox" value="" class="workZoneCls">';
										collapse+='Work Zone';
									  collapse+='</label>';
									collapse+='</div>';
							collapse+='</div>'; 
							collapse+='<div class="col-sm-2">';
								collapse+='<button type="button" class="btn btn-primary btn-sm getLocationWiseCls m_top20" attr_work_id="'+workTypeId+'">SUBMIT</button>';
							collapse+='</div>';
						collapse+='</div>';
						
						collapse+='<div class="row">';
							collapse+='<div id="locationWiseDetailsBlockDivId"></div>';
					    collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	
	$("#locationWiseWorkTypesDivId").html(collapse);
	$(".chosen-select").chosen();
	getLocationWiseOverview(workTypeId);
}
$(document).on("change","#locationWiseLevelId",function(){
	var levelId=$(this).val();
	getAllDistrictsLocationOfAp('locationWiseDistrictId','District');
	if(levelId == 3){
		$(".locationWiseDistrictCls").show();
		$(".locationWiseDivisionCls,.locationWiseSubdivisionCls,.locationWiseMandalCls").hide();
	}else if(levelId == 12){
		$(".locationWiseDistrictCls,.locationWiseDivisionCls").show();
		$(".locationWiseSubdivisionCls,.locationWiseMandalCls").hide();
		$("#locationWiseDivisionId").html('');
		$("#locationWiseDivisionId").append('<option value="0">All</option>');
		$("#locationWiseDivisionId").trigger("chosen:updated");
	}else if(levelId == 13){
		$(".locationWiseDistrictCls,.locationWiseDivisionCls,.locationWiseSubdivisionCls").show();
		$(".locationWiseMandalCls").hide();
		
		$("#locationWiseDivisionId").html('');
		$("#locationWiseDivisionId").append('<option value="0">All</option>');
		$("#locationWiseDivisionId").trigger("chosen:updated");
		
		$("#locationWiseSubdivisionId").html('');
		$("#locationWiseSubdivisionId").append('<option value="0">All</option>');
		$("#locationWiseSubdivisionId").trigger("chosen:updated");
	}else if(levelId == 5){
		$(".locationWiseDistrictCls,.locationWiseDivisionCls,.locationWiseSubdivisionCls,.locationWiseMandalCls").show();
		$("#locationWiseDivisionId").html('');
		$("#locationWiseDivisionId").append('<option value="0">All</option>');
		$("#locationWiseDivisionId").trigger("chosen:updated");
		
		$("#locationWiseSubdivisionId").html('');
		$("#locationWiseSubdivisionId").append('<option value="0">All</option>');
		$("#locationWiseSubdivisionId").trigger("chosen:updated");
		
		$("#locationWiseMandalId").html('');
		$("#locationWiseMandalId").append('<option value="0">All</option>');
		$("#locationWiseMandalId").trigger("chosen:updated");
	}
	
});	
$(document).on("click",".getLocationWiseCls",function(){
	var workTypeId = $(this).attr("attr_work_id")
	getLocationWiseOverview(workTypeId);
});		
function getLocationWiseOverview(workTypeId){
	
	var locationLevelId = $("#locationWiseLevelId").val();
	var districtId=$("#locationWiseDistrictId").val();
	var divisonId=$("#locationWiseDivisionId").val();
	var subDivisonId=$("#locationWiseSubdivisionId").val();
	var mandalId=$("#locationWiseMandalId").val();
	var workZone='N';
	$('.workZoneCls').each(function(i, obj){
		 if($(this).is(':checked')){
			workZone = 'Y'
		 }
	});
	
	$("#locationWiseDetailsBlockDivId").html(spinner);
	
	var json ={
		 	  "workTypeId":workTypeId,
			  "locationScopeId":locationLevelId,
			  "districtId":districtId,
			  "divisonId":divisonId,
			  "subDivisonId":subDivisonId,
			  "mandalId":mandalId,
			  "workZone":workZone
		}
	$.ajax({                
		type:'POST',    
		url: 'getLocationWiseOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildLocationWiseOverview(result,workZone,workTypeId);
		}else{
			$("#locationWiseDetailsBlockDivId").html("No Data Available");
		}
	});
}
function buildLocationWiseOverview(result,workZone,workTypeId){
	var str='';
	if(workZone == 'N'){
		str+='<div class="col-sm-12 m_top10">';
			str+='<div class="table-responsive">';
					str+='<table class="table table-bordered table_custom_WMS">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Location</th>';
								str+='<th>Target&nbsp;KM</th>';
								str+='<th>Average&nbsp;KM</th>';
								for(var i in result[0].statusList){
									str+='<th>'+result[0].statusList[i].status+'</th>';
								}
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							for(var i in result){
								str+='<tr>';
									str+='<td style="text-align:left !important;"><a class="locationWisePopupCls" attr_locationLevelId="'+result[i].locationValue+'" attr_work_id="'+workTypeId+'" attr_locationName="'+result[i].location+'">'+result[i].location+'</a></td>';
									str+='<td>'+result[i].totalKms.toFixed(2)+'&nbsp;km</td>';
									str+='<td>'+result[i].totalAvgKms.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].totalAvgPerc.toFixed(2)+' %</span></td>';
									for(var j in result[i].statusList){
										str+='<td>'+result[i].statusList[j].totalKms.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].statusList[j].completedPercentage.toFixed(2)+' %</span></td>';
									}
								str+='</tr>';
							}
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		}else{
			str+='<div class="col-sm-12 m_top10">';
			str+='<div class="table-responsive">';
					str+='<table class="table table-bordered table_custom_WMS">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Location</th>';
								str+='<th>Work Zone</th>';
								str+='<th>Total&nbsp;KM</th>';
								for(var i in result[0].worksList[0].statusList){
									str+='<th>'+result[0].worksList[0].statusList[i].status+'</th>';
								}
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							for(var i in result){
								for(var j in result[i].worksList){
									str+='<tr>';
										str+='<td style="text-align:left !important;"><a class="locationWisePopupCls" attr_locationLevelId="'+result[i].locationValue+'" attr_work_id="'+workTypeId+'" attr_locationName="'+result[i].location+'">'+result[i].location+'</a></td>';
										
										//str+='<td style="text-align:left !important;">'+result[i].location+'</td>';
										str+='<td style="text-align:left !important;"><a class="workZonePopupCls" attr_locationLevelId="'+result[i].locationValue+'" attr_workType_id="'+workTypeId+'" attr_workzone_id="'+result[i].worksList[j].workId+'" attr_locationName="'+result[i].worksList[j].workName+'">'+result[i].worksList[j].workName+'</a></td>';
										str+='<td>'+result[i].worksList[j].totalKms.toFixed(2)+'&nbsp;km</td>';
										for(var k in result[i].worksList[j].statusList){
											if(typeof result[i].worksList[j].statusList[k].completedPercentage == null || typeof result[i].worksList[j].statusList[k].completedPercentage == undefined || typeof result[i].worksList[j].statusList[k].completedPercentage == "undefined"){
												str+='<td> - </td>';
											}else{
												str+='<td>'+result[i].worksList[j].statusList[k].totalKms.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].worksList[j].statusList[k].completedPercentage.toFixed(2)+' %</span></td>';
											}
											
										}
									str+='</tr>';
								}
								
							}
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		}	
	$("#locationWiseDetailsBlockDivId").html(str);
}


$(document).on("click",".sliderNewCls li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var workId = $(this).attr("attr_work_id");
	
	//getAllDistrictsOfAp('districtId','District');
	//getAllDistrictsLocationOfAp('locationWiseDistrictId','District');
	
	timeLineAndLocationWiseDetails(workId);
	stateLevelWiseDetails(workId);
	locationWiseDetails(workId);
});	

$(document).on("click",".locationWisePopupCls",function(){
	var locationValue = $(this).attr("attr_locationLevelId");
	var locationName = $(this).attr("attr_locationName");
	var workId = $(this).attr("attr_work_id");
	var levelName = $("#locationWiseLevelId option:selected").text();
	
	$("#locationLevelPopupId").modal("show");
	
	$("#locationLevelHeadingId").html(locationName+" "+levelName+" Details");
	
	getLocationLevelWiseOverviewDetails(workId,locationValue);
	getLocationLevelStatusWiseOverviewDetails(workId,locationValue)
	getWorkZoneStatusWiseKms(workId,locationValue);
	getLocationOverviewStatusDayWiseKms(workId,0,locationValue);
	getAllStatusOfWorkType(workId,"locationWiseWorkStagesDivId",locationValue);
});	
function getLocationLevelWiseOverviewDetails(workId,locationValue){
	
	$("#locationLevelFirstBlockDivId").html(spinner);
	
	var locationScopeId = $("#locationWiseLevelId").val();
	var json ={
		  "workTypeId":workId,
		  "locationScopeId":locationScopeId,
		  "locationValue":locationValue

		}
	$.ajax({                
		type:'POST',    
		url: 'getLocationLevelWiseOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildLocationLevelWiseOverviewDetails(result);
		}else{
			$("#locationLevelFirstBlockDivId").html("No Data Available");
		}
	});
}
function buildLocationLevelWiseOverviewDetails(result){
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-2">';
		str+='<div class="bg_yash_color_10 m_top10 text-center">';
			str+='<h2 class="font_BebasNeue" style="color:#0080FF">'+result.worksCount+'</h2>';
			str+='<h4 class="font_BebasNeue m_top5 text-capital">Work Zones</h4>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="col-sm-2">';
		str+='<div class="bg_yash_color_10 m_top10 text-center">';
			str+='<h2 class="font_BebasNeue" style="color:#0080FF">'+result.totalKms.toFixed(2)+' KM</h2>';
			str+='<h4 class="font_BebasNeue m_top5 text-capital">TARGET KM</h4>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="col-sm-2">';
		str+='<div class="bg_yash_color_10 m_top10 text-center">';
			str+='<h2 class="font_BebasNeue" style="color:#009A33">'+result.completedKms+' KM</h2>';
			str+='<h4 class="font_BebasNeue m_top5 text-capital">COMPLETED KM</h4>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-3">';
		str+='<div class="bg_yash_color_10 m_top10 text-center">';
			str+='<h2 class="font_BebasNeue" style="color:#009A33">'+result.completedWorksCount+' %</h2>';
			str+='<h4 class="font_BebasNeue m_top5 text-capital">completed works</h4>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-3">';
		str+='<div class="bg_yash_color_10 m_top10 text-center">';
			str+='<h3 class="font_BebasNeue text-capital">sanctioned&nbsp;amount</h3>';
			str+='<h3 class="font_BebasNeue m_top5">'+result.estimationCost.toFixed(2)+' Lakhs</h3>';
		str+='</div>';
	str+='</div>';
	$("#locationLevelFirstBlockDivId").html(str);
}
function getLocationLevelStatusWiseOverviewDetails(workId,locationValue){
	
	var locationScopeId = $("#locationWiseLevelId").val();
	$("#locationLevelSecondBlockDivId").html(spinner);
	var json ={
		  "workTypeId":workId,
		  "locationScopeId":locationScopeId,
		  "locationValue":locationValue

		}
	$.ajax({                
		type:'POST',    
		url: 'getLocationLevelStatusWiseOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildLocationLevelStatusWiseOverviewDetails(result);
		}else{
			$("#locationLevelSecondBlockDivId").html("No Data Available");
		}
	});
}

function buildLocationLevelStatusWiseOverviewDetails(result){
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-12 m_top10">';
			str+='<h3 class="font_BebasNeue" style="color:#252D30;">Work Progress stage wise Overview</h3>';
		str+='</div>';	
		str+='<div class="pad_10 m_top10">';
			str+='<div class="row">';
			var k=0;
				for(var i in result){
					k=k+1;
					str+='<div class="col-sm-2">';
						str+='<img src="Assests/images/'+result[i].govtWorkStatus+'.png" style="width: 100%;height: 100px;"/>';
						str+='<div class="card-img-overlay" style="height:50px;">';	
							str+='<h5 class="statusRoundCss">0'+k+'</h5>';
						str+='</div>';
						str+='<div class="card-img-overlay2">';	
							str+='<h3 class="color_white font_weight font_BebasNeue">'+result[i].govtWorkStatus+'</h3>';
						str+='</div>';
					
						str+='<div class="row">';
							str+='<div class="col-sm-12 m_top5">';
								str+='<div class="bg_StatusWorkCss">';
									
									str+='<h5 class="font_weight">Works in Km</h5>';
									if(typeof result[i].workCompletedPercentage == null || typeof result[i].workCompletedPercentage =="undefined" || typeof result[i].workCompletedPercentage == undefined){
										str+='<input class="m_top10" id="statusBarLcationWorks'+i+'" data-slider-id="statusBarLcationWorks'+i+'Slider" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0" data-slider-tooltip="hide"/><span class="font_weight pull-right">0 %</span>';
									}else{
										str+='<input class="m_top10" id="statusBarLcationWorks'+i+'" data-slider-id="statusBarLcationWorks'+i+'Slider" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="'+result[i].workCompletedPercentage.toFixed(0)+'" data-slider-tooltip="hide"/><span class="font_weight pull-right">'+result[i].workCompletedPercentage.toFixed(0)+' %</span>';
									}
									
									if(typeof result[i].workLenght == null || typeof result[i].workLenght =="undefined" || typeof result[i].workLenght == undefined){
										str+='<h5 class="font_weight m_top5">0</h5>';
									}else{
										str+='<h5 class="font_weight m_top5">'+result[i].workLenght.toFixed(0)+'</h5>';
									}
									
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
					
			str+='</div>';	
		str+='</div>';
	str+='</div>';
	str+='<hr class="m_bottom_0 m_top10" style="border-top: 1px solid #ddd !important;"/>';
	$("#locationLevelSecondBlockDivId").html(str);
	for(var i in result){
		$('#statusBarLcationWorks'+i).slider({
			/* formatter: function(value) {
				return 'Current value: ' + value;
			} */
		});
	}
}
function getWorkZoneStatusWiseKms(workId,locationValue){
	var locationScopeId = $("#locationWiseLevelId").val();
	$("#locationLevelThirdBlockDivId").html(spinner);
	var json ={
		  "workTypeId":workId,
		  "locationScopeId":locationScopeId,
		  "locationValue":locationValue

		}
	$.ajax({                
		type:'POST',    
		url: 'getWorkZoneStatusWiseKms',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildWorkZoneStatusWiseKms(result);
		}else{
			$("#locationLevelThirdBlockDivId").html("No Data Available");
		}
	});
}

function buildWorkZoneStatusWiseKms(result){
	var str='';
	
	str+='<div class="table-responsive">';
		str+='<table class="table table_custom_WMS table-bordered m_top10">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Work Zone</th>';
					str+='<th>Target KM</th>';
					for(var i in result[0].workStatusVOList){
						str+='<th>'+result[0].workStatusVOList[i].govtWorkStatus+'</th>';
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].govtWorkStatus+'</td>';
						str+='<td>'+result[i].workLenght.toFixed(2)+'</td>';
						for(var j in result[i].workStatusVOList){
							if(typeof result[i].workStatusVOList[j].workCompletedPercentage == "null" || typeof result[i].workStatusVOList[j].workCompletedPercentage == "undefined" || typeof result[i].workStatusVOList[j].workCompletedPercentage == undefined){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[i].workStatusVOList[j].workLenght.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].workStatusVOList[j].workCompletedPercentage.toFixed(2)+' %</span></td>';
							}
							
						}	
					str+='</tr>';
				}
				
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#locationLevelThirdBlockDivId").html(str);
}
function getLocationOverviewStatusDayWiseKms(workId,statusId,locationValue){
	
	var locationScopeId = $("#locationWiseLevelId").val();
	$("#locationLevelFourthBlockDivId").html(spinner);
	var json ={
		  "fromDate":"28-03-2018",
		  "toDate":"4-05-2018",
		  "workTypeId":workId,
		  "locationScopeId":locationScopeId,
		  "locationValue":locationValue,
		  "statusId":statusId

		}
	$.ajax({                
		type:'POST',    
		url: 'getLocationOverviewStatusDayWiseKms',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			$("#locationLevelFourthBlockDivId").css("height","200px");
			buildLocationStatusDayWiseKmsMainGraph(result,"locationLevelFourthBlockDivId");
		}else{
			$("#locationLevelFourthBlockDivId").html("No Data Available")
			$("#locationLevelFourthBlockDivId").removeAttr("style");
		}
	});
}


$(document).on("click",".workZonePopupCls",function(){
	var locationValue = $(this).attr("attr_locationLevelId");
	var locationName = $(this).attr("attr_locationName");
	var workTypeId = $(this).attr("attr_worktype_id");
	var workZoneId = $(this).attr("attr_workzone_id");
	$("#workZoneModalDivId").modal("show");
	
	$("#workZoneHeadingId").html("Work Zone : " +locationName);
	getWorkZoneMainOverview(workZoneId);
	getWorkZoneDocumentDetailsInfo(workZoneId);
	getWorkZoneWorkStategsDetailsInfo(workZoneId);
	getWorkZoneStatusDetailsInfo(workZoneId,workTypeId);
	getAllStatusOfWorkType(workTypeId,"workZoneWorkStagesDivId","");
	getWorkZoneWorkStategsDetailsInfo(workZoneId,0);
});	
function getWorkZoneMainOverview(workZoneId){
	$("#workZoneOverviewBlockDivId").html(spinner);
	var json ={
		  "workId":workZoneId
		}
	$.ajax({                
		type:'POST',    
		url: 'getWorkZoneMainOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildWorkZoneMainOverview(result);
		}else{
			$("#workZoneOverviewBlockDivId").html("No Data Available");
		}
	});
}

function buildWorkZoneMainOverview(result){
	var str='';
	str+='<div class="bg_color">';
		str+='<div class="row">';
			str+='<div class="col-sm-4">';
				str+='<h4 class="font_weight">Target km</h4>';
				str+='<h4 class="m_top10 font_weight">'+result.workLenght.toFixed(2)+' km</h4>';
			str+='</div>';
			str+='<div class="col-sm-4">';
				str+='<h4 class="font_weight">Completed km</h4>';
				str+='<h3 class="m_top10 font_weight">'+result.completedLength.toFixed(2)+' km</h4>';
			str+='</div>';
			str+='<div class="col-sm-4">';
				str+='<h4 class="font_weight">Sanctioned Amount</h4>';
				str+='<h4 class="m_top10 font_weight">'+result.fundAllocated.toFixed(2)+' Lakhs</h4>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#workZoneOverviewBlockDivId").html(str);
	
}

function getWorkZoneStatusDetailsInfo(workZoneId,workTypeId){
	$("#workZoneWorkStatusBlockDivId").html(spinner);
	var json ={
		  "workId":workZoneId,
		  "workTypeId":workTypeId
		}
	$.ajax({                
		type:'POST',    
		url: 'getWorkZoneStatusDetailsInfo',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildWorkZoneStatusDetailsInfo(result);
		}else{
			$("#workZoneWorkStatusBlockDivId").html("No Data Available");
		}
	});
}
function buildWorkZoneStatusDetailsInfo(result){
	
	var str='';
		str+='<div class="bg_color m_top10">';
			str+='<div class="row">';
			var k=0;
				for(var i in result){
					k=k+1;
					str+='<div class="col-sm-2" style="padding-left: 5px;padding-right: 5px;">';
						str+='<img src="Assests/images/'+result[i].govtWorkStatus+'.png" style="width: 100%;height: 100px;"/>';
						str+='<div class="card-img-overlay" style="height:50px;">';	
							str+='<h5 class="statusRoundCss">0'+k+'</h5>';
						str+='</div>';
						str+='<div class="card-img-overlay2">';	
							str+='<h5 class="color_white font_weight">'+result[i].govtWorkStatus+'</h5>';
						str+='</div>';
					
						str+='<div class="row">';
							str+='<div class="col-sm-12">';
								str+='<div class="bg_StatusWorkCss">';
								//<i class="fa fa-check-circle-o" aria-hidden="true"></i>
								//<i class="fa fa-pause-circle-o" aria-hidden="true"></i>
								if(result[i].currentWorkLength>=100){
									str+='<h5 class="text-center"><i class="fa fa-check-circle-o" aria-hidden="true" style="font-size: 28px !important;color: green;"></i></h5>';
									str+='<h5 class="workZoneAlignment m_top10">'+result[i].workLenght.toFixed(1)+' KM ON</h5>';	
									str+='<h5 class="workZoneAlignment m_top5">'+result[i].date+'</h5>';	
								}else{
									str+='<h5 class="text-center"><i class="fa fa-pause-circle-o" aria-hidden="true" style="font-size: 28px !important;color: #FFBA00;"></i></h5>';
									str+='<div class="row">';
										str+='<div class="col-sm-6">';
											if(result[i].currentWorkLength !=null && result[i].currentWorkLength>0){
												str+='<h5 class="workZoneAlignment m_top10">'+result[i].currentWorkLength.toFixed(1)+'%</h5>';	
											}else{
												str+='<h5 class="workZoneAlignment m_top10"> - </h5>';	
											}
											
										str+='</div>';
										str+='<div class="col-sm-6">';
											if(result[i].workLenght !=null && result[i].workLenght>0){
												str+='<h5 class="workZoneAlignment m_top10">'+result[i].workLenght.toFixed(1)+'KM</h5>';	
											}else{
												str+='<h5 class="workZoneAlignment m_top10"> - </h5>';	
											}
											
										str+='</div>';
									str+='</div>';
									if(typeof result[i].date ==null || typeof result[i].date ==undefined || typeof result[i].date =="undefined"){
											str+='<h5 class="workZoneAlignment m_top5"> - </h5>';
									}else{
										str+='<h5 class="workZoneAlignment m_top5">'+result[i].date+'</h5>';
									}
									
								}
								
									
	
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
					
			str+='</div>';	
		str+='</div>';
	$("#workZoneWorkStatusBlockDivId").html(str);
	
}
function getWorkZoneDocumentDetailsInfo(workZoneId){
	$("#workZoneDocumentsDivId").html(spinner);
	var json ={
		  "workId":workZoneId
		}
	$.ajax({                
		type:'POST',    
		url: 'getWorkZoneDocumentDetailsInfo',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildWorkZoneDocumentDetailsInfo(result);
		}else{
			$("#workZoneDocumentsDivId").html("No Data Available");
		}
	});
}

function buildWorkZoneDocumentDetailsInfo(result){
	var str='';
	
	str+='<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">';
		 str+='<ol class="carousel-indicators">';
			 for(var i in result){
				 if(i==0){
					  str+='<li data-target="#carousel-example-generic" data-slide-to="'+i+'" class="active"></li>';
				 }else{
					  str+='<li data-target="#carousel-example-generic" data-slide-to="'+i+'" class=""></li>';
				 }
				
			 }
		 str+='</ol>';
		
	  str+='<div class="carousel-inner" role="listbox">';
	  for(var i in result){
		  if(i==0){
			  str+='<div class="item active">';
				str+=' <img src="http://www.mydepartments.in/PRRWS/'+result[i].path+'" alt="'+result[i].path+'" style="height: 303px;width:100%;">';
			str+='</div>';
		  }else{
			  str+='<div class="item">';
				str+=' <img src="http://www.mydepartments.in/PRRWS/'+result[i].path+'" alt="'+result[i].path+'" style="height: 303px;width:100%;">';
			str+='</div>';
		  }
		  
	  }
	  str+='</div>';

	  str+='<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">';
		str+='<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>';
		str+='<span class="sr-only">Previous</span>';
	  str+='</a>';
	  str+='<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">';
		str+='<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>';
		str+='<span class="sr-only">Next</span>';
	  str+='</a>';
	str+='</div>';
					
	$("#workZoneDocumentsDivId").html(str);
	$('.carousel').carousel();
}
function getWorkZoneWorkStategsDetailsInfo(workZoneId,statusId){
	$("#workZoneGraphBlockDivId").html(spinner);
	var json ={
		  "fromDate":currentFromDate,
		  "toDate":currentToDate,
		  "workId":workZoneId,
		  "statusId":statusId
		}
	$.ajax({                
		type:'POST',    
		url: 'getWorkZoneWorkStategsDetailsInfo',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			$("#workZoneGraphBlockDivId").css("height","200px");
			buildLocationStatusDayWiseKmsMainGraph(result,"workZoneGraphBlockDivId");
		}else{
			$("#workZoneGraphBlockDivId").html("No Data Available")
			$("#workZoneGraphBlockDivId").removeAttr("style");
		}
	});
}

getStatusDistrictDayWiseDocuments();
function getStatusDistrictDayWiseDocuments(){
	var json ={
		  "fromDate":"28-03-2018",
		  "toDate":"30-03-2018",
		  "districtId":0,
		  "statusId":"7"
		}
	$.ajax({                
		type:'POST',    
		url: 'getStatusDistrictDayWiseDocuments',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			
		}
	});
}