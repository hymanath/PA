var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');    
var globalToDate = moment().format('DD-MM-YYYY');

var globalFromTodayDate = moment().format('DD-MM-YYYY');    
var globalToTodayDate = moment().format('DD-MM-YYYY');

var globalFromYesterdayDate = moment().subtract(1,'day').format('DD-MM-YYYY');    
var globalToYesterdayDate = moment().subtract(1,'day').format('DD-MM-YYYY');

var levelWiseData=["DISTRICT","VILLAGE"]
getSessionToken();

$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
function getSessionToken(){
	$("#clorinatedMainViewId").html(spinner);
	var json = {
		leadName:"village_chlorination@psmri.com",
		category:"vc_2991_12",
		year	:0
	}
	$.ajax({                
		type:'POST',    
		url: 'getSessionToken',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			onloadCalls(result.sessionToken);
		}
		
	});
}
$("#singleDateRangePicker").daterangepicker({
	opens: 'left',
	startDate: globalFromDate,
	endDate: globalToDate,
	locale: {
	  format: 'DD-MM-YYYY'
	}
});

$('#singleDateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	globalFromDate = picker.startDate.format('DD-MM-YYYY')
	globalToDate = picker.endDate.format('DD-MM-YYYY')
	getSessionToken();
	
});
function onloadCalls(sessionToken){
	buildlevelWiseData("WaterTank",sessionToken);
	buildWaterBodyCumulativeCounts(sessionToken);
	
}
$(document).on('click','.calendar_active_cls li', function(){
	var date = $(this).attr("attr_val");
	
	if(date == 'Week'){
		globalFromDate = moment().subtract(1,'week').format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		
	}else if(date == 'Month'){
		globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		
	}else if(date == '3Months'){
		globalFromDate = moment().subtract(3,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		
	}else if(date == '6Months'){
		globalFromDate = moment().subtract(6,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		
	}else if(date == 'Overall'){
		globalFromDate = moment().subtract(20,'years').startOf("year").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		
	}else if(date == 'Year'){
		globalFromDate = moment().startOf("year").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		
	}
	
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	if(date != "custom"){
		getSessionToken();
	}
	
});
function buildlevelWiseData(divId,sessionToken)
{
	var collapse='';
			collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in levelWiseData)
				{
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseData[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'">';
								}else{
									collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'">';
								}
								collapse+='<h4 class="panel-title text-capital">'+levelWiseData[i]+' level overview</h4>';
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'">';
							}else{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+divId.replace(/\s+/g, '')+''+levelWiseData[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
			collapse+='</div>';
		$("#levelWiseWaterTankDetails").html(collapse);
		getDistrictWiseClorinationDetails(sessionToken,"DISTRICT");
		getLocationWiseClorinationDetails(sessionToken,"VILLAGE");
}
	
	function buildWaterBodyCumulativeCounts(sessionToken){
		
		
		var ChlorinatArr=["Cumulative","Today","Yesterday"];
		var str='';
			str+='<div class="col-sm-12">';
			for(var i in ChlorinatArr){
					str+='<div id="levelWise'+ChlorinatArr[i]+'"></div>';
			}
			str+='</div>';
		$("#clorinatedMainViewId").html(str);
		
		getWaterBodyCumulativeCounts(sessionToken,globalFromDate,globalToDate,"Cumulative");
		getWaterBodyCumulativeCounts(sessionToken,globalFromTodayDate,globalToTodayDate,"Today");
		getWaterBodyCumulativeCounts(sessionToken,globalFromYesterdayDate,globalToYesterdayDate,"Yesterday");
	}
	
	function getWaterBodyCumulativeCounts(sessionToken,fromDateStr,toDateStr,type){
		$("#levelWise"+type).html(spinner);	
		var json = {
			fromDateStr	:fromDateStr,
			toDateStr 	:toDateStr,
			session		:sessionToken
		}
		$.ajax({                
			type:'POST',    
			url: 'getWaterBodyCumulativeCounts',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null){
				buildWaterBodyCumulativeCountsDetails(result,type);
			}else{
				$("#levelWise"+type).html("No Data Available");	
			}
		});
	}
	
	function buildWaterBodyCumulativeCountsDetails(result,type){
		var str='';
		var clorinatedMainPerc = (result.clorinated/result.checked*100).toFixed(2);
		var notClorinatedMainPerc = (result.notClorinated/result.checked*100).toFixed(2); 
		str+='<div class="col-sm-4">';
				str+='<div class="block_styles" style="background-color:#fff">';
					str+='<h3>'+type+' <span class="pull-right" style="margin-top: 10px; font-size: 14px;">(District - '+result.noOfDistricts+') (SP - '+result.noOfSPs+') </span> </h3>';
						if(result.checked !=null && result.checked>0){
							str+='<h3 class="m_top10 font_weight">'+result.checked+'</h3>';
						}else{
							str+='<h3 class="m_top10 font_weight">0</h3>';
						}
						str+='<div class="row m_top20">';
						str+='<div class="col-sm-4 border-right">';
							str+='<h5 class="font_weight">Checked</h5>';
							if(result.checked !=null && result.checked>0){
								str+='<h4 class="m_top10 font_weight">'+result.checked+'</h4>';
							}else{
								str+='<h4 class="m_top10 font_weight">0</h4>';
							}
						str+='</div>';
						str+='<div class="col-sm-4 border-right">';
							str+='<h5 class="font_weight">Chlorinated</h5>';
							if(result.clorinated !=null && result.clorinated>0){
								str+='<h4  class="m_top10 font_weight">'+result.clorinated+' <small style="color:red;">'+clorinatedMainPerc+' %</small></h4>';
							}else{
								str+='<h4  class="m_top10 font_weight">0</h4>';
							}
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<h5 class="font_weight">Not Chlorinated</h5>';
							if(result.notClorinated !=null && result.notClorinated>0){
								str+='<h4 class="m_top10 font_weight">'+result.notClorinated+' <small style="color:red;">'+notClorinatedMainPerc+' %</small></h4>';
							}else{
								str+='<h4 class="m_top10 font_weight">0</h4>';
							}
						str+='</div>';
						str+='</div>';
						
				str+='</div>';
			str+='</div>';
		$("#levelWise"+type).html(str);	
	}
	
	function getDistrictWiseClorinationDetails(sessionToken,divId){
		$("#WaterTank"+divId).html(spinner);
		var json = {
			fromDateStr	:globalFromDate,
			toDateStr 	:globalToDate,
			session		:sessionToken
		}
		$.ajax({                
			type:'POST',    
			url: 'getDistrictWiseClorinationDetails',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildDistrictWiseClorinationDetails(result,divId);
			}else{
				$("#WaterTank"+divId).html("No Data Available");
			}
		});
	}
	function getLocationWiseClorinationDetails(sessionToken,divId){
		$("#WaterTank"+divId).html(spinner);
		
		var json = {
			fromDateStr	:globalFromDate,
			toDateStr 	:globalToDate,
			session		:sessionToken
		}
		$.ajax({                
			type:'POST',    
			url: 'getLocationWiseClorinationDetails',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildDistrictWiseClorinationDetails(result,divId);
			}else{
				$("#WaterTank"+divId).html("No Data Available");
			}
		});
	}
	function buildDistrictWiseClorinationDetails(result,divId){
		
		var str='';
		
		str+='<div class="table-responsive">';
			str+='<table class="table table_custom" id="dataTable'+divId+'">';
				str+='<thead>';
				if(divId == "DISTRICT"){
					str+='<tr>';
						str+='<th>DISTRICT</th>';
						str+='<th>NO OF SERVICE POINTS</th>';
						str+='<th>CHECKED</th>';
						str+='<th>CHLORINATED</th>';
						str+='<th>CHLORINATED %</th>';
						str+='<th>NOT-CHLORINATED</th>';
						str+='<th>NOT-CHLORINATED %</th>';
					str+='</tr>';
				}else{
					str+='<tr>';
						str+='<th>DISTRICT</th>';
						str+='<th>AREA&nbsp;NAME</th>';
						str+='<th>SERVICE POINT</th>';
						str+='<th>VAN NO</th>';
						str+='<th>VISITED DATE</th>';
						str+='<th>CHECKED</th>';
						str+='<th>CHLORINATED</th>';
						str+='<th>CHLORINATED&nbsp;%</th>';
						str+='<th>NOT-CHLORINATED</th>';
						str+='<th>NOT-CHLORINATED&nbsp;%</th>';
					str+='</tr>';
				}
					
				str+='</thead>';
				str+='<tbody>';
					for(var i in result){
						if(divId == "DISTRICT"){
							var clorinatedVal = "0.00";
							var notClorinatedVal = "0.00";
							var totalCount = 0;
							
							clorinatedVal=result[i].clorinated;
							 notClorinatedVal=result[i].notClorinated;
							 totalCount = result[i].checked;
							
							if(clorinatedVal > 0 && totalCount >0){
							   clorinatedDisPerc = (clorinatedVal/totalCount*100).toFixed(2);
							 }
							 if(notClorinatedVal > 0 && totalCount >0){
								notClorinatedDisPerc = (notClorinatedVal/totalCount*100).toFixed(2);
							 }
							
							str+='<tr>';
								str+='<td>'+result[i].districtName+'</td>';
								str+='<td>'+result[i].noOfSPs+'</td>';
								str+='<td>'+result[i].checked+'</td>';
								str+='<td>'+result[i].clorinated+'</td>';
								str+='<td>'+clorinatedDisPerc+'</td>';
								str+='<td>'+result[i].notClorinated+'</td>';
								str+='<td>'+notClorinatedDisPerc+'</td>';
							str+='</tr>';
						}else{
							var clorinatedPerc = "0.00";
							var notClorinatedPerc = "0.00";
							 if(result[i].clorinated > 0 && result[i].checked >0){
							    clorinatedPerc = (result[i].clorinated/result[i].checked*100).toFixed(2);
							 }
							 if(result[i].notClorinated > 0 && result[i].checked >0){
								notClorinatedPerc = (result[i].notClorinated/result[i].checked*100).toFixed(2);
							 }
							str+='<tr>';
								str+='<td>'+result[i].districtName+'</td>';
								str+='<td>'+result[i].areaName+'</td>';
								str+='<td>'+result[i].servicePointName+'</td>';
								str+='<td>'+result[i].vanNo+'</td>';
								str+='<td>'+result[i].visitDate+'</td>';
								str+='<td>'+result[i].checked+'</td>';
								str+='<td>'+result[i].clorinated+'</td>';
								str+='<td>'+clorinatedPerc+'</td>';
								str+='<td>'+result[i].notClorinated+'</td>';
								str+='<td>'+notClorinatedPerc+'</td>';
						str+='</tr>';
						}
						
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
		$("#WaterTank"+divId).html(str);
		$("#dataTable"+divId).dataTable({
			"iDisplayLength": 10,
			"order": [ 1, 'desc' ],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			
		});
	}