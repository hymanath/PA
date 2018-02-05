$(".chosen-select").chosen();
var smallSpinner = '<img src="Assests/images/spinner.gif" style="width:25px;height:25px;"/>';
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalStartDate=moment().subtract(6, 'days').format('YYYY-MM-DD');
var globalEndDate =moment().format('YYYY-MM-DD');
setTimeout(function(){ 
		getAllDistricts();
		getTodayVendorDetails();
		getDateWiseVendorDetails();
		levelWiseSBData("levelWise");
		levelWiseSBDataLedDashBoard("levelWiseDahBoard");
		//buildDashBoardVendorView()
		getVendorDashBoardOverviewDetails();
}, 1000);
$("#dateRangePickerMGNF").daterangepicker({
  singleDatePicker: true,
  locale: {
	format: 'YYYY-MM-DD'
  }

});
$("#dateRangePickerRanges").daterangepicker({
	opens:'left',
	startDate: globalStartDate,
	endDate: globalEndDate,
	locale: {
        format: "YYYY-MM-DD",
	},
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
});
$('#dateRangePickerRanges').on('apply.daterangepicker', function(ev, picker) {
	globalStartDate = picker.startDate.format("YYYY-MM-DD");
	globalEndDate = picker.endDate.format("YYYY-MM-DD");
	getTodayVendorDetails();
	getDateWiseVendorDetails();
	levelWiseSBData("levelWise");
});
$("#dateRangePickerLedDashBoard").daterangepicker({
	opens:'left',
	startDate: globalStartDate,
	endDate: globalEndDate,
	locale: {
        format: "YYYY-MM-DD",
	},
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
});
$('#dateRangePickerLedDashBoard').on('apply.daterangepicker', function(ev, picker) {
	globalStartDate = picker.startDate.format("YYYY-MM-DD");
	globalEndDate = picker.endDate.format("YYYY-MM-DD");
	getVendorDashBoardOverviewDetails();
	levelWiseSBDataLedDashBoard("levelWiseDahBoard");
});
function getAllDistricts(){
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getAllDistrictsForPris',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0){
			for(var i in result){
				$("#districtId").append("<option value="+result[i].id+">"+result[i].name+" </option>")
			}
		}
		$("#districtId").trigger("chosen:updated");
	});
}
$(document).on("change","#districtId",function(){
	$("#mandalLoading").html(smallSpinner);
	getMandalsByDistrict();
});
function getMandalsByDistrict(){
	var districtId = $("#districtId").val();
	var json = {
		locationId : districtId
	}
	$.ajax({                
		type:'POST',    
		url: 'getMandalsByDistrict',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#mandalLoading").html('');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#mandalId").append("<option value="+result[i].id+">"+result[i].name+" </option>")
			}
		}
		$("#mandalId").trigger("chosen:updated");
	});
}
$(document).on("change","#mandalId",function(){
	$("#villageLoading").html(smallSpinner);
	getPanchayatsByTehsil();
});
function getPanchayatsByTehsil(){
	var mandalId = $("#mandalId").val();
	var json = {
		locationId : mandalId
	}
	$.ajax({                
		type:'POST',    
		url: 'getPanchayatsByTehsil',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#villageLoading").html('');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#villageId").append("<option value="+result[i].id+">"+result[i].name+" </option>")
			}
		}
		$("#villageId").trigger("chosen:updated");
	});
}
function getTodayVendorDetails(){
	$("#todayOverViewId").html(spinner);
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getTodayVendorDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildTodayVendorDetails(result);
		}else{
			$("#todayOverViewId").html("No Data Available.");
		}
	});	
}

function buildTodayVendorDetails(result){
	var str='';
	str+='<table class="table table-bordered" id="VendorDataTableId">';
	str+='<thead>';
		str+='<tr>';
			str+='<th>DISTRICT</th>';
			str+='<th>VILLAGE</th>';
			str+='<th>NO.OF&nbsp;LIGHTS&nbsp;FITTED</th>';
			str+='<th>TEAMS WORKED</th>';
		str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
		str+='<tr>';
			str+='<td>'+result[i].districtName+'</td>';
			str+='<td>'+result[i].panchayatName+'</td>';
			str+='<td>'+result[i].lightCount+'</td>';
			str+='<td>'+result[i].teamCount+'</td>';
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	$("#todayOverViewId").html(str);
	$("#VendorDataTableId").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});
}
function getDateWiseVendorDetails(){
	$("#dateWiseSummaryDivId").html(spinner);
	var json = {
		fromDate : globalStartDate,
		toDate : globalEndDate
	}
	$.ajax({                
		type:'POST',    
		url: 'getDateWiseVendorDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null){
			buildDateWiseVendorDetails(result);
		}else{
			$("#dateWiseSummaryDivId").html("No Data Available.");
		}
	});	
}

function buildDateWiseVendorDetails(result){
	var str='';
	str+='<div class="row">';
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered">';
				str+='<tbody>';
					str+='<tr>';
						str+='<td>';
							str+='<h5 class="font_weight text-center m_top30">Villages<br>Worked</h5>';
							str+='<h4 class="font_weight m_top30 text-center" style="padding:8px;background-color:#672504;border-radius:5px;color:#fff;margin-bottom:40px;width: 60%;margin-left: 25px;">'+result.totalVillages+'</h4>';
						str+='</td>';
						str+='<td>';
							str+='<h5 class="font_weight text-center m_top30">Lights<br>Fitted</h5>';
							str+='<h4 class="font_weight m_top30 text-center" style="padding:8px;background-color:#056EA7;border-radius:5px;color:#fff;margin-bottom:40px;width: 60%;margin-left: 25px;">'+result.lightCount+'</h4>';
						str+='</td>';
						str+='<td>';
							str+='<h5 class="font_weight text-center m_top30">Teams<br>Worked</h5>';
							str+='<h4 class="font_weight m_top30 text-center" style="padding:8px;background-color:#4A5B39;border-radius:5px;color:#fff;margin-bottom:40px;width: 60%;margin-left: 25px;">'+result.teamCount+'</h4>';
						str+='</td>';
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';	
		str+='</div>';
	str+='</div>';
	$("#dateWiseSummaryDivId").html(str);
}

function getLevelWiseVendorDetails(levelType){
	$("#levelWise"+levelType).html(spinner);
	var json = {
		 type : levelType,
		 fromDate : globalStartDate,
		 toDate : globalEndDate
	}
	$.ajax({                
		type:'POST',    
		url: 'getLevelWiseVendorDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildLevelWiseVendorDetails(result,levelType);
		}else{
			$("#levelWise"+levelType).html("No Data Available.");
		}
	});	
}
function buildLevelWiseVendorDetails(result,levelType){
	var str='';
	str+='<table  class="table table-bordered" id="dataTableId'+levelType+'">';
	str+='<thead>';
		str+='<tr>';
			str+='<th>DISTRICT</th>';
			if(levelType != null && levelType == 'panchayat'){
				str+='<th>VILLAGE</th>';
			}
			str+='<th>NO.OF LIGHTS FITTED</th>';
			str+='<th>TEAMS WORKED</th>';
		str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
		str+='<tr>';
		if(levelType != null && levelType == 'district'){
			if(result[i].districtName != null && result[i].districtName.length > 0){
			str+='<td class="dateWiseCls" attr_location_id="'+result[i].districtId+'" attr_vendor_id="'+vendorId+'" attr_level_type="'+levelType+'"  attr_location_name="'+result[i].districtName+'" style="cursor:pointer;"><u>'+result[i].districtName+'</u></td>';
			}else{
				str+='<td>-</td>';
			}
		}
		
		if(levelType != null && levelType == 'panchayat'){
			str+='<td>'+result[i].districtName+'</td>';
			if(result[i].panchayatName != null && result[i].panchayatName.length > 0){
				str+='<td class="dateWiseCls" attr_location_id="'+result[i].panchayatId+'" attr_vendor_id="'+vendorId+'" attr_level_type="'+levelType+'"  attr_location_name="'+result[i].panchayatName+'" style="cursor:pointer;"><u>'+result[i].panchayatName+'</u></td>';
			}else{
				str+='<td>-</td>';
			}
		}
		if(result[i].lightCount != null && result[i].lightCount != 0){
			str+='<td>'+result[i].lightCount+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].teamCount != null && result[i].teamCount != 0){
			str+='<td>'+result[i].teamCount+'</td>';
		}else{
			str+='<td>-</td>';
		}	
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	$("#levelWise"+levelType).html(str);
	$("#dataTableId"+levelType).dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});
}
function levelWiseSBData(divId)
{
	var levelWiseSBArr = ['district','panchayat'];
	var collapse='';
			collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in levelWiseSBArr)
				{
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBArr[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}else{
									collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}
								collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview</h4>';
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
			collapse+='</div>';
	$("#levelWiseLedEntryId").html(collapse);
	
	for(var i in levelWiseSBArr){
		getLevelWiseVendorDetails(levelWiseSBArr[i]);
	}	
}
function levelWiseSBDataLedDashBoard(divId)
{
	var levelWiseSBArr = ['district','panchayat'];
	var collapse='';
			collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in levelWiseSBArr)
				{
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBArr[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}else{
									collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}
								collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview</h4>';
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
			collapse+='</div>';
	$("#levelWiseLedDashBoardId").html(collapse);
	
	for(var i in levelWiseSBArr){
		getDashBoardLevelWiseVendorDetails(levelWiseSBArr[i]);
	}	
}
function getDashBoardLevelWiseVendorDetails(levelType){
	$("#levelWiseDahBoard"+levelType).html(spinner);
	var json = {
		  fromDate : globalStartDate,
		  toDate : globalEndDate,
		  type : levelType
	}
	$.ajax({                
		type:'POST',    
		url: 'getDashBoardLevelWiseVendorDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildDashBoardLevelWiseVendorDetails(result,levelType);
		}else{
			$("#levelWiseDahBoard"+levelType).html("No Data Available.");
		}
	});	
}

function buildDashBoardLevelWiseVendorDetails(result,levelType){
	var str='';
	str+='<table  class="table table-bordered" id="dashBoarddataTableId'+levelType+'">';
	str+='<thead>';
		str+='<tr>';
			str+='<th>VENDOR</th>';
			str+='<th>DISTRICT</th>';
			if(levelType != null && levelType == 'panchayat'){
				str+='<th>VILLAGE</th>';
			}
			str+='<th>NO.OF LIGHTS FITTED</th>';
			str+='<th>NO.OF CCMS CONNECTED</th>';
			str+='<th>NO.OF TEAMS WORKED</th>';
		str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
		str+='<tr>';
		if(result[i].lightVendorName != null && result[i].lightVendorName.length > 0){
			str+='<td><h5><span><img src="Assests/icons/'+result[i].lightVendorName+'.jpg" style="20px;height:20px;"/></span>'+result[i].lightVendorName+'</h5></td>';
		}else{
			str+='<td>-</td>';
		}
		if(levelType != null && levelType == 'district'){
			if(result[i].districtName != null && result[i].districtName.length > 0){
			str+='<td class="dashBoardDateWiseCls" attr_location_id="'+result[i].districtId+'" attr_vendor_id="'+result[i].lightVendorId+'" attr_level_type="'+levelType+'"  attr_location_name="'+result[i].districtName+'" style="cursor:pointer;"><u>'+result[i].districtName+'</u></td>';
			}else{
				str+='<td>-</td>';
			}
		}
		
		if(levelType != null && levelType == 'panchayat'){
			str+='<td>'+result[i].districtName+'</td>';
			if(result[i].panchayatName != null && result[i].panchayatName.length > 0){
				str+='<td class="dashBoardDateWiseCls" attr_location_id="'+result[i].panchayatId+'" attr_vendor_id="'+result[i].lightVendorId+'" attr_level_type="'+levelType+'"  attr_location_name="'+result[i].panchayatName+'" style="cursor:pointer;"><u>'+result[i].panchayatName+'</u></td>';
			}else{
				str+='<td>-</td>';
			}
		}
		if(result[i].lightCount != null && result[i].lightCount != 0){
			str+='<td>'+result[i].lightCount+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].totalPanels != null && result[i].totalPanels != 0){
			str+='<td>'+result[i].totalPanels+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].teamCount != null && result[i].teamCount != 0){
			str+='<td>'+result[i].teamCount+'</td>';
		}else{
			str+='<td>-</td>';
		}	
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	$("#levelWiseDahBoard"+levelType).html(str);
	$("#dashBoarddataTableId"+levelType).dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});
}
$(document).on("click",".dateWiseCls",function(){
	$("#dateModalId").modal("show");
	$("#dateModalDivId").html(spinner);
	var locationId = $(this).attr("attr_location_id");
	var vendorId = $(this).attr("attr_vendor_id");
	var levelType = $(this).attr("attr_level_type");
	var locationName = $(this).attr("attr_location_name");
	getDateWiseVendorDetailsByLocationId(locationId,vendorId,levelType,locationName);
});

function getDateWiseVendorDetailsByLocationId(locationId,vendorId,levelType,locationName){
	$("#dateModalTitleId").html(locationName.toUpperCase()+" "+levelType.toUpperCase()+" DATE WISE DETAILS.");
	var json = {
		 fromDate : globalStartDate,
		  toDate : globalEndDate,
		  type : levelType,
		  locationId :locationId,
		  lightVendorId : vendorId
	}
	$.ajax({                
		type:'POST',    
		url: 'getDateWiseVendorDetailsByLocationId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null){
			buildDateWiseVendorDetailsByLocationId(result);
		}else{
			$("#dateModalDivId").html("No Data Available.");
		}
	});	
}
function buildDateWiseVendorDetailsByLocationId(result){
	var str='';
	str+='<table  class="table table-bordered" id="dataTableId">';
	str+='<thead>';
		str+='<tr>';
			str+='<th>WORK DATE</th>';
			str+='<th>NO.OF LIGHTS FITTED</th>';
			str+='<th>TEAMS WORKED</th>';
		str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
		str+='<tr>';
		if(result[i].workDate != null && result[i].workDate != 0){
			str+='<td>'+result[i].workDate+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].lightCount != null && result[i].lightCount != 0){
			str+='<td>'+result[i].lightCount+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].teamCount != null && result[i].teamCount != 0){
			str+='<td>'+result[i].teamCount+'</td>';
		}else{
			str+='<td>-</td>';
		}	
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	$("#dateModalDivId").html(str);
	$("#dataTableId").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});
}
$(document).on("click",".dashBoardDateWiseCls",function(){
	$("#dashboardDateModalId").modal("show");
	$("#dashboardDateModalDivId").html(spinner);
	var locationId = $(this).attr("attr_location_id");
	var vendorId = $(this).attr("attr_vendor_id");
	var levelType = $(this).attr("attr_level_type");
	var locationName = $(this).attr("attr_location_name");
	getDashBoardVendorDetailsByLocationId(locationId,vendorId,levelType,locationName);
});

function getDashBoardVendorDetailsByLocationId(locationId,vendorId,levelType,locationName){
	$("#dashBoardDateModalTitleId").html(locationName.toUpperCase()+" "+levelType.toUpperCase()+" DATE WISE DETAILS.");
	var json = {
		  fromDate : globalStartDate,
		  toDate : globalEndDate,
		  type : levelType,
		  locationId :locationId,
		  lightVendorId : vendorId
	}
	$.ajax({                
		type:'POST',    
		url: 'getDashBoardVendorDetailsByLocationId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildDashBoardVendorDetailsByLocationId(result);
		}else{
			$("#dashboardDateModalDivId").html("No Data Available.");
		}
	});	
}
function buildDashBoardVendorDetailsByLocationId(result){
	var str='';
	str+='<table  class="table table-bordered" id="dashBoarddataTableId">';
	str+='<thead>';
		str+='<tr>';
			str+='<th>WORK DATE</th>';
			str+='<th>NO.OF LIGHTS FITTED</th>';
			str+='<th>NO.OF CCMS CONNECTED</th>';
			str+='<th>TEAMS WORKED</th>';
		str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
		str+='<tr>';
		if(result[i].workDate != null && result[i].workDate != 0){
			str+='<td>'+result[i].workDate+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].lightCount != null && result[i].lightCount != 0){
			str+='<td>'+result[i].lightCount+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].totalPanels != null && result[i].totalPanels != 0){
			str+='<td>'+result[i].totalPanels+'</td>';
		}else{
			str+='<td>-</td>';
		}
		if(result[i].teamCount != null && result[i].teamCount != 0){
			str+='<td>'+result[i].teamCount+'</td>';
		}else{
			str+='<td>-</td>';
		}	
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	$("#dashboardDateModalDivId").html(str);
	$("#dashBoarddataTableId").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});
}
$(document).on("click","#submitId",function(){
	saveLEDVendorWiseDetails();
});

function saveLEDVendorWiseDetails(){
	var isErr ;
	var districtId = $("#districtId").val();
	var mandalId = $("#mandalId").val();
	var locationId = $("#villageId").val();
	var lightFittedVal = $("#lightFitedId").val();
	var teamCount = $("#teamWorkId").val();
	var workDate = $("#dateRangePickerMGNF").val();
	/* if(districtId == 0){
		$("#districtErrMsgId").html("Please Select Any District");
		isErr = true;
	}else{
		$("#districtErrMsgId").html('');
	}
	if(mandalId == 0){
		$("#mandalErrMsgId").html("Please Select Any Mandal");
		isErr = true;
	}else{
		$("#mandalErrMsgId").html('');
	}
	if(locationId == 0){
		$("#villageErrMsgId").html("Please Select Any Village");
		isErr = true;
	}else{
		$("#villageErrMsgId").html('');
	}
	var numericExpression = /^[0-9]+$/;
	if(!$('#lightFitedId').val().trim().match(numericExpression)){
		alert("Enter Only Numbers");
	}
	if(lightFittedVal == 0){
		$("#lightFittedErrMsgId").html("Please Give No.Of Lights.");
		isErr = true;
	}else{
		$("#lightFittedErrMsgId").html('');
	}
	var numericExpression = /^[0-9]+$/;
	if(!$('#teamWorkId').val().trim().match(numericExpression)){
		alert("Enter Only Numbers");
	}
	if(teamCount == 0){
		$("#teamWorkErrMsgId").html("Please Give No.Of Teams Worked.");
		isErr = true;
	}else{
		$("#teamWorkErrMsgId").html('');
	}
	if(isErr =='true'){
		return ;
	} */
	$("#submitLoading").html(spinner);
	
	var json = {
		  fromDate : workDate,
		  sourceId : lightFittedVal,
		  schemeId : teamCount,
		  locationId : locationId
 
	}
	$.ajax({                
		type:'POST',    
		url: 'saveLEDVendorWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null){
			if(result.name != null && result.name.length > 0){
				$("#submitLoading").html('<span style="color:green;">Vendor Details Submit Successfully..');
			}else{
				$("#submitLoading").html('<span style="color:red;">Submition Failed..</span>');
			}
		}
		setTimeout(function(){ 
			$("#submitLoading").html('');
			getTodayVendorDetails();
			getDateWiseVendorDetails();
			levelWiseSBData("levelWise");
		}, 1000);
	});	
}
function getVendorDashBoardOverviewDetails(){
	$("#ledDashBoardVendorViewDivId").html(spinner);
	var json = {
		  fromDate : globalStartDate,
		  toDate : globalEndDate
	}
	$.ajax({                
		type:'POST',    
		url: 'getVendorDashBoardOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildDashBoardVendorView(result);
		}else{
			$("#ledDashBoardVendorViewDivId").html("No Data Available.");
		}
	});	
}

function buildDashBoardVendorView(result){
	var str='';
	str+='<div class="row">';
	for(var i in result){
		str+='<div class="col-sm-6">';
			str+='<div  style="border:1px solid #ddd;padding:10px;">';
				str+='<div class="row">';
				str+='<div class="col-sm-3 border_right_yash text-center">';
					
						if(result[i].lightVendorId != null && result[i].lightVendorId == 1){
							str+='<img src="Assests/icons/EESL.jpg"/>';
						}else if(result[i].lightVendorId != null && result[i].lightVendorId == 2){
							str+='<img src="Assests/icons/NREDCAP.jpg"/>';
						}
				
					str+='</div>';
					
					str+='<div class="col-sm-3 border_right_yash text-center">';
						str+='<h5 class="font_weight" style="color:green">No. of<br/>Lights Fitted</h5>';
						str+='<h5 class="m_top15"><img src="Assests/icons/LightFitted.jpg"/></h5>';
						str+='<h4 class="font_weight m_top20" style="color:green">'+result[i].lightCount+'</h4>';
					str+='</div>';
					str+='<div class="col-sm-3 border_right_yash text-center">';	
						str+='<h5 class="font_weight" style="color:#993127">No.of CCMS<br/>Connected</h5>';
						str+='<h5 class="m_top15"><img src="Assests/icons/CCMS.jpg"/></h5>';
						str+='<h4 class="font_weight m_top20" style="color:#993127">'+result[i].totalPanels+'</h4>';
					str+='</div>';
					str+='<div class="col-sm-3 text-center">';
						str+='<h5 class="font_weight" style="color:#F44209">No. of Teams<br/>Worked</h5>';
						str+='<h5 class="m_top15"><img src="Assests/icons/TeamWork.jpg"/></h5>';
						str+='<h4 class="font_weight m_top20" style="color:#F44209">'+result[i].teamCount+'</h4>';
					str+='</div>';
					
				str+='</div>';
			str+='</div>';
		str+='</div>';
		}
	str+='</div>';
	$("#ledDashBoardVendorViewDivId").html(str);
}