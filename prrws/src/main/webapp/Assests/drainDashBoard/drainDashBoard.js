var globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
var globalToDate = moment().format('DD-MM-YYYY');
var spinner_Drain = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner_Drain"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
onloadCalls();
function onloadCalls(){
	getDrainsInfoStateWise();
	getDrainsInfoLocationWise("district",'0','','0','',"districtView");
	getDrainsInfoLocationWise("assembly",'0','','0','',"assemblyView");
	getDrainsInfoLocationWise("mandal",'0','','0','',"mandalView");
	getAllDistricts("constituencyDistrictNames","DISTRICTS");
	getAllDistricts("mandalDistrictNames","DISTRICTS");
	$(".chosen-select").chosen();
	
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
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
	
	onloadCalls();
});

$(document).on('click','.calendar_active_cls li', function(){
	var date = $(this).attr("attr_val");
	$(".tableMenu li").removeClass("active");
	$(".tableMenu li:first-child").addClass("active");
	$("#mandalConstituencyNames").html('');
	$("#mandalConstituencyNames").trigger("chosen:updated");
	
	if(date == 'Today')
	{
		globalFromDate = moment().format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		
	}else if(date == 'Week'){
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
		globalFromDate = moment().subtract(15,'years').startOf("year").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
	}
	
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	if(date != "custom"){
		onloadCalls();
	}
	
});

function getDrainsInfoStateWise(){
	$("#totalSpinnerId").html(spinner_Drain);
	$("#undergroundSpinnerId").html(spinner_Drain);
	$("#pakkaSpinnerId").html(spinner_Drain);
	$("#kachaSpinnerId").html(spinner_Drain);
	
	var json = {
			fromDate : globalFromDate,
			toDate : globalToDate,
			locationType : "district" ,
			locationId:"0"
		}
		$.ajax({                
			type:'POST',    
			url: 'getDrainsInfoStateWise',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			$("#totalSpinnerId").html('');
			$("#undergroundSpinnerId").html('');
			$("#pakkaSpinnerId").html('');
			$("#kachaSpinnerId").html('');
			if(result != null){
				var totalStr='';
					totalStr+='<div class="row">'
					totalStr+='<div class="col-sm-6 subBlock">';
					totalStr+='<h5>AVAILABLE</h5>';
					totalStr+='<h4 id="totalAvailableCountId">'+result.totalAvailable+'</h4>'
					totalStr+='<span id="totalAvailableKmId">'+result.totalAvailableKms+' KM</span>';
					totalStr+='</div>';
					totalStr+='<div class="col-sm-6 subBlock">';
					totalStr+='<h5>CLEANED</h5>';
					totalStr+='<h4 id="totalCleanedCountId">'+result.totalCleaned+'<small class="pull-right" id="totalPercentageId">'+result.percentage+' %</small></h4>';
					totalStr+='<span id="totalCleanedKmId">'+result.totalCleanedKms+' KM</span>';
					totalStr+='</div>';
					totalStr+='</div>';
					$("#totalBodyId").html(totalStr);
					
				var kachaStr='';
					kachaStr+='<div class="row">';
					kachaStr+='<div class="col-sm-6 subBlock">';
					kachaStr+='<h5>AVAILABLE</h5>';
					kachaStr+='<h4 id="kachaAvailableCountId">'+result.kachaAvailable+'</h4>';
					kachaStr+='<span id="kachaAvailableKmId">'+result.kachaAvailableKms+' KM</span>';
					kachaStr+='</div>';
					kachaStr+='<div class="col-sm-6 subBlock">';
					kachaStr+='<h5>CLEANED</h5>';
					kachaStr+='<h4 id="kachaCleanedCountId">'+result.kachaCleaned+'<small class="pull-right" id="kachaPercentageId">'+result.kachaPercentage+' %</small></h4>';
					kachaStr+='<span id="kachaCleanedKmId">'+result.kachaCleanedKM+' KM</span>';
					kachaStr+='</div>';
					kachaStr+='</div>';
					$("#kachaBodyId").html(kachaStr);
					
				var pakkaStr='';
					pakkaStr+='<div class="row">';
					pakkaStr+='<div class="col-sm-6 subBlock">';
					pakkaStr+='<h5>AVAILABLE</h5>';
					pakkaStr+='<h4 id="pakkaAvailableCountId">'+result.pakkaAvailable+'</h4>';
					pakkaStr+='<span id="pakkaAvailableKmId">'+result.pakkaAvailableKms+' KM</span>';
					pakkaStr+='</div>';
					pakkaStr+='<div class="col-sm-6 subBlock">';
					pakkaStr+='<h5>CLEANED</h5>';
					pakkaStr+='<h4 id="pakkaCleanedCountId">'+result.pakkaCleaned+'<small class="pull-right" id="pakkaPercentageId">'+result.pakkaPercentage+' %</small></h4>';
					pakkaStr+='<span id="pakkaCleanedKmId">'+result.pakkaCleanedKM+' KM</span>';
					pakkaStr+='</div>';
					pakkaStr+='</div>';
					$("#pakkaBodyId").html(pakkaStr);
				
				var ugStr='';
					ugStr+='<div class="row">';
					ugStr+='<div class="col-sm-6 subBlock">';
					ugStr+='<h5>AVAILABLE</h5>';
					ugStr+='<h4 id="ugAvailableCountId">'+result.ugAvailable+'</h4>';
					ugStr+='<span id="ugAvailableKmId">'+result.ugAvailableKms+' KM</span>';
					ugStr+='</div>';
					ugStr+='<div class="col-sm-6 subBlock">';
					ugStr+='<h5>CLEANED</h5>';
					ugStr+='<h4 id="udCleanedCountId">'+result.ugCleaned+'<small class="pull-right" id="ugPercentageId">'+result.ugPercentage+' %</small></h4>';
					ugStr+='<span id="ugCleanedKmId">'+result.ugCleanedKms+' KM</span>';
					ugStr+='</div>';
					ugStr+='</div>';
					$("#ugBodyId").html(ugStr);
			}
		});
}

	
function getDrainsInfoLocationWise(locationType,filterId,filterType,subFilterId,subFilterType,divId){
	
	$("#"+divId+"TableDivId").html(spinner);
		var json = {
			fromDate : globalFromDate,
			toDate : globalToDate,
			locationId:0,
			locationType:locationType,
			filterType:filterType,
			filterId:filterId,
			subFilterId:subFilterId,
			subFilterType:subFilterType
		}
	
	$.ajax({                
		type:'POST',    
		url: 'getDrainsInfoLocationWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		buildingTable(result,locationType,divId);
	});
}

function buildingTable(result,locationType,divId){
	var str='';
	str+='<div class="table-responsive">';
	str+='<table class="table" id="datatable'+locationType+'">';
        str+='<thead>';
            str+='<tr>';
				if(locationType == "district")
					str+='<th style="background-color:#ccc;">DISTRICTS</th>';
				else if(locationType == "assembly")
					str+='<th style="background-color:#ccc;">CONSTITUENCIES</th>';
				else if(locationType == "mandal")
					str+='<th style="background-color:#ccc;">MANDALS</th>';
				else if(locationType == "constituency")
					str+='<th style="background-color:#ccc;">PARLIAMENTS</th>';
				
                str+='<th style="background-color:#ccc;" colspan="5" class="text-center">TOTAL</th>';
                str+='<th style="background-color:#ccc;" colspan="5" class="text-center">KACCHA</th>';
                str+='<th style="background-color:#ccc;" colspan="5" class="text-center">PAKKA</th>';
                str+='<th style="background-color:#ccc;" colspan="5" class="text-center">UNDERGROUND</th>';
            str+='</tr>';
            str+='<tr>';
                str+='<th style="background-color:#fff"></th>';
                str+='<th style="background-color:#fff">Avi</th>';
				str+='<th style="background-color:#fff">km</th>';
				str+='<th style="background-color:#fff">Cle</th>';
				str+='<th style="background-color:#fff">km</th>';
                str+='<th style="background-color:#fff">%</th>';
				
                str+='<th style="background-color:#E6EFEE">Avi</th>';
				str+='<th style="background-color:#E6EFEE">km</th>';
                str+='<th style="background-color:#E6EFEE">Cle</th>';
				str+='<th style="background-color:#E6EFEE">km</th>';
                str+='<th style="background-color:#E6EFEE">%</th>';
				
				str+='<th style="background-color:#FFEAEA">Avi</th>';
				str+='<th style="background-color:#FFEAEA">km</th>';
                str+='<th style="background-color:#FFEAEA">Cle</th>';
				str+='<th style="background-color:#FFEAEA">km</th>';
                str+='<th style="background-color:#FFEAEA">%</th>';
				
				str+='<th style="background-color:#F2F1E6">Avi</th>';
				str+='<th style="background-color:#F2F1E6">km</th>';
                str+='<th style="background-color:#F2F1E6">Cle</th>';
				str+='<th style="background-color:#F2F1E6">km</th>';
                str+='<th style="background-color:#F2F1E6">%</th>';
            str+='</tr>';
        str+='</thead>';
		str+='<tbody>';
			if(result != null && result.length > 0){
				for(var i in result){
					//if(result[i].totalAvailable !=null && result[i].totalAvailable>0){
						str+='<tr>';
						if(locationType == "district"){
							str+='<td><img src="Assests/icons/'+result[i].name+'.png" style="height: 30px;margin-right: 7px;"/><br/>'+result[i].name+'</td>';
						}else{
							str+='<td>'+result[i].name+'</td>';
						}
						
						//<p><span>174</span>/<span>1003</span></p>
						str+='<td>'+result[i].totalAvailable+'</td>';
						str+='<td>'+result[i].totalAvailableKms+'</td>';
						str+='<td>'+result[i].totalCleaned+'</td>';
						str+='<td>'+result[i].totalCleanedKms+'</td>';
						str+='<td style="color:#FD3367">'+result[i].percentage+'</td>';
						
						str+='<td style="background-color:#E6EFEE">'+result[i].kachaAvailable+'</td>';
						str+='<td style="background-color:#E6EFEE">'+result[i].kachaAvailableKms+'</td>';
						str+='<td style="background-color:#E6EFEE">'+result[i].kachaCleaned+'</td>';
						str+='<td style="background-color:#E6EFEE">'+result[i].kachaCleanedKM+'</td>';
						str+='<td style="background-color:#E6EFEE;color:#FD3367">'+result[i].kachaPercentage+'</td>';
						
						str+='<td style="background-color:#FFEAEA">'+result[i].pakkaAvailable+'</td>';
						str+='<td style="background-color:#FFEAEA">'+result[i].pakkaAvailableKms+'</td>';
						str+='<td style="background-color:#FFEAEA">'+result[i].pakkaCleaned+'</td>';
						str+='<td style="background-color:#FFEAEA">'+result[i].pakkaCleanedKM+'</td>';
						str+='<td style="background-color:#FFEAEA;color:#FD3367">'+result[i].pakkaPercentage+'</td>';
						
						str+='<td style="background-color:#F2F1E6">'+result[i].ugAvailable+'</td>';
						str+='<td style="background-color:#F2F1E6">'+result[i].ugAvailableKms+'</td>';
						str+='<td style="background-color:#F2F1E6">'+result[i].ugCleaned+'</td>';
						str+='<td style="background-color:#F2F1E6">'+result[i].ugCleanedKms+'</td>';
						str+='<td style="background-color:#F2F1E6;color:#FD3367">'+result[i].ugPercentage+'</td>';
						str+='</tr>';
					//}					
				}
			}else{
				str+='No Data Available.';
			}
        str+='</tbody>';
    str+='</table>';
    str+='</div>';
	$("#"+divId+"TableDivId").html(str);
	$("#datatable"+locationType).dataTable();	
}

$(document).on("click","[role='tabDrains_menu'] li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var blockId = $(this).closest("ul").attr("attr_blockId");
	if(blockId == 3){
		if($(this).attr("attr_location_type") == "districts"){
			getDrainsInfoLocationWise("district",'0','','0','','districtView');
		}else if($(this).attr("attr_location_type") == "parliament"){
			getDrainsInfoLocationWise("constituency",'0','','0','','districtView');
		}
	}else if(blockId == 4){
		if($(this).attr("attr_location_type") == "districts"){
			getAllDistricts("constituencyDistrictNames","DISTRICTS");
			getDrainsInfoLocationWise("assembly",'0','','0','','assemblyView');
		}else if($(this).attr("attr_location_type") == "parliament"){
			getAllParliaments("constituencyDistrictNames","PARLIAMENTS");
			getDrainsInfoLocationWise("assembly",'0','','0','','assemblyView');
		}
	}else if(blockId == 5){
		$("#mandalConstituencyNames").html("");
		$("#mandalConstituencyNames").trigger('chosen:updated');
		if($(this).attr("attr_location_type") == "districts"){
			getAllDistricts("mandalDistrictNames","DISTRICTS");
			getDrainsInfoLocationWise("mandal",'0','','0','','mandalView');
		}else if($(this).attr("attr_location_type") == "parliament"){
			getAllParliaments("mandalDistrictNames","PARLIAMENTS");
			getDrainsInfoLocationWise("mandal",'0','','0','','mandalView');
		}
	}
	
});
$(document).on("change","#constituencyDistrictNames",function(){
	var districtId = 0;
	var parlConstId = 0;
	var locationType='';
	$('.tableMenuCons li').each(function(i, obj){
		 if($(this).hasClass('active')){
			locationType = $(this).attr("attr_location_type");
		 }
	});
	var filterType = "";
	var subFilterType = "";
	if(locationType == "districts" ){
		filterType = "district";
		districtId = $(this).val();
	}else if(locationType == "parliament"){
		filterType = "district";
		subFilterType = "constituency";
		parlConstId = $(this).val();
	}
	//getDrainsInfoLocationWise("assembly",districtId,filterType,'0','','assemblyView')
	getDrainsInfoLocationWise("assembly",districtId,filterType,parlConstId,subFilterType,'assemblyView')
});

$(document).on("change","#mandalDistrictNames",function(){
	var districtId = $(this).val();
	var locationType='';
	$('.tableMenuMandal li').each(function(i, obj){
		 if($(this).hasClass('active')){
			locationType = $(this).attr("attr_location_type");
		 }
	});
	var filterType = "";
	if(locationType == "districts"){
		filterType = "district";
		getDrainsInfoLocationWise("mandal",districtId,filterType,'0','','mandalView');
		getAllConstituenciesForDistrict('mandalConstituencyNames','CONSTITUENCIES',districtId);
	}else if(locationType == "parliament"){
		filterType = "district";
		getDrainsInfoLocationWise("mandal",'0','district',districtId,'constituency','mandalView');
		getAllConstituenciesForParliament('mandalConstituencyNames','CONSTITUENCIES',districtId);
	}
	
});
$(document).on("change","#mandalConstituencyNames",function(){
	var constId = $(this).val();
	var parlconstId= $("#mandalDistrictNames").val();
	var locationType='';
	$('.tableMenuMandal li').each(function(i, obj){
		 if($(this).hasClass('active')){
			locationType = $(this).attr("attr_location_type");
		 }
	});
	getDrainsInfoLocationWise("mandal",'0','district',constId,'assembly','mandalView');
});
//All Districts 
function getAllDistricts(divId,levelName){
		$("#"+divId).html('');
		var json = {}
		$.ajax({                
			type:'POST',    
			url: 'getAllDistricts',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			
			if(result !=null && result.length>0){
				 $("#"+divId).append('<option value="0">ALL '+levelName+'</option>');
					for(var i in result){
						$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
			}
			$("#"+divId).trigger('chosen:updated');
		});
	}
	function getAllParliaments(divId,levelName){
	//All Parliaments
		$("#"+divId).html('');
		var json = {}
		$.ajax({                
			type:'POST',    
			url: 'getAllParliaments',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				 $("#"+divId).append('<option value="0">ALL '+levelName+'</option>');
					for(var i in result){
						$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
			}
			$("#"+divId).trigger('chosen:updated');
		});
	}
	
	//District Onchange Constituency
	function getAllConstituenciesForDistrict(divId,levelName,value){
		$("#"+divId).html("");
		$("#"+divId).trigger('chosen:updated');
		var json = {
			districtId :parseInt(value)
		}
		$.ajax({                
			type:'POST',    
			url: 'getAllConstituenciesForDistrict',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				 $("#"+divId).append('<option value="0">ALL '+levelName+'</option>');
					for(var i in result){
						$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
			}
			$("#"+divId).trigger('chosen:updated');
		});
	}
	
	//parliament onchange Constituency
	function getAllConstituenciesForParliament(divId,levelName,value){
		$("#"+divId).html('');
		$("#"+divId).trigger('chosen:updated');
		var json = {
			parliamentId:parseInt(value)
			}
		$.ajax({                
			type:'POST',    
			url: 'getAllConstituenciesForParliament',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				 $("#"+divId).append('<option value="0">ALL '+levelName+'</option>');
					for(var i in result){
						$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
			}
			$("#"+divId).trigger('chosen:updated');
		});
	}