var globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
var globalToDate = moment().format('DD-MM-YYYY');
var spinner_Drain = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner_Drain"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globallocId = 0;
var globallevelId = 0;
var globalLocationName='';
$("#selectedName").attr("attr_id","0");
$("#selectedName").attr("attr_levelidvalue","2");
onloadCalls();
function DefaultValuesSetMainBlock(){
	$("#totalAvailableCountId").html(0);
	$("#totalAvailableKmId").html(0);
	$("#totalCleanedCountId").html(0);
	$("#totalCleanedKmId").html(0);
	$("#kachaAvailableCountId").html(0);
	$("#kachaAvailableKmId").html(0);
	$("#kachaCleanedCountId").html(0);
	$("#kachaCleanedKmId").html(0);
	$("#pakkaAvailableCountId").html(0);
	$("#pakkaAvailableKmId").html(0);
	$("#pakkaCleanedCountId").html(0);
	$("#pakkaCleanedKmId").html(0);
	$("#ugAvailableCountId").html(0);
	$("#ugAvailableKmId").html(0);
	$("#udCleanedCountId").html(0);
	$("#ugCleanedKmId").html(0);
}
function onloadCalls(){
	
	if(globallevelId == 2 || globallevelId == 0){
		
		$(".tableMenu li:nth-child(2)").show();
		$("[overview-level]").show();
		DefaultValuesSetMainBlock();
		$("#constituencyDistrictNames_chosen").show();
		$("#mandalDistrictNames_chosen").show();
		$("#mandalConstituencyNames_chosen").show();
		getDrainsInfoStateWise(globallocId,"district");
		getAllDistricts("constituencyDistrictNames","DISTRICTS");
		getAllDistricts("mandalDistrictNames","DISTRICTS");
		//District
		getDrainsInfoLocationWise("district",'0','','0','',"districtView",globallocId);
		//constituency
		getDrainsInfoLocationWise("assembly",globallocId,'','0','',"assemblyView",0);
		//Mandal
		getDrainsInfoLocationWise("mandal",globallocId,'','0','',"mandalView",0);
	}else if(globallevelId == 3){
		$(".tableMenu li:nth-child(1)").addClass("active");
		$(".tableMenu li:nth-child(2)").hide();
		$("[overview-level]").show();
		DefaultValuesSetMainBlock();
		getDrainsInfoStateWise(globallocId,"district");
		
		//District
		getDrainsInfoLocationWise("district",'0','','0','',"districtView",globallocId);
		
		//constituency
		$("#constituencyDistrictNames_chosen").show();
		$("#constituencyDistrictNames").html('');
		$("#constituencyDistrictNames").append("<option value="+globallocId+">"+globalLocationName+" </option>");
		$("#constituencyDistrictNames").trigger("chosen:updated");
		getDrainsInfoLocationWise("assembly",globallocId,'district','0','',"assemblyView",0);
		//Mandal
		$("#mandalDistrictNames_chosen").show();
		$("#mandalConstituencyNames_chosen").show();
		$("#mandalDistrictNames").html('');
		$("#mandalDistrictNames").append("<option value="+globallocId+">"+globalLocationName+" </option>");
		$("#mandalDistrictNames").trigger("chosen:updated");
		getAllConstituenciesForDistrict('mandalConstituencyNames','CONSTITUENCIES',globallocId);
		getDrainsInfoLocationWise("mandal",globallocId,'district','0','',"mandalView",0);
	}else if(globallevelId == 4){
		$(".tableMenu li:nth-child(1)").addClass("active");
		$(".tableMenu li:nth-child(2)").hide();
		$("[overview-level='district']").hide();
		DefaultValuesSetMainBlock();
		getDrainsInfoStateWise(globallocId,"assembly");
		
		//constituency
		$("#constituencyDistrictNames_chosen").hide();
		getDrainsInfoLocationWise("assembly",'0','','0','',"assemblyView",globallocId);
		//Mandal
		$("#mandalDistrictNames_chosen").hide();
		$("#mandalConstituencyNames_chosen").hide();
		getDrainsInfoLocationWise("mandal",'0','',globallocId,'assembly',"mandalView",0);
	}else if(globallevelId == 5){
		$(".tableMenu li:nth-child(1)").addClass("active");
		$(".tableMenu li:nth-child(2)").hide();
		$("[overview-level='district']").hide();
		$("[overview-level='constituency']").hide();
		DefaultValuesSetMainBlock();
		getDrainsInfoStateWise(globallocId,"mandal");
		//Mandal
		$("#mandalDistrictNames_chosen").hide();
		$("#mandalConstituencyNames_chosen").hide();
		getDrainsInfoLocationWise("mandal",'0','','0','',"mandalView",globallocId);
	}
	
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
$(document).on("click",".menuDataCollapse",function(){
	globallocId = $(this).attr("attr_id");
	globallevelId = $(this).attr("attr_levelidvalue");
	globalLocationName=$(this).attr("attr_name");
	$("#selectedName").text($(this).html());
	$("#selectedName").attr("attr_id",globallocId);
	$("#selectedName").attr("attr_levelidvalue",globallevelId);
	$("#selectedName").attr("attr_name",globalLocationName);
	
	if(globallevelId == 2 || globallevelId == 0){
		$(".tableMenu li:nth-child(1)").removeClass("active");
		$(".tableMenu li:nth-child(2)").show();
		$("[overview-level]").show();
		DefaultValuesSetMainBlock();
		$("#constituencyDistrictNames_chosen").show();
		$("#mandalDistrictNames_chosen").show();
		$("#mandalConstituencyNames_chosen").show();
		getDrainsInfoStateWise(globallocId,"district");
		getAllDistricts("constituencyDistrictNames","DISTRICTS");
		getAllDistricts("mandalDistrictNames","DISTRICTS");
		//District
		getDrainsInfoLocationWise("district",'0','','0','',"districtView",globallocId);
		//constituency
		getDrainsInfoLocationWise("assembly",globallocId,'','0','',"assemblyView",0);
		//Mandal
		getDrainsInfoLocationWise("mandal",globallocId,'','0','',"mandalView",0);
	}else if(globallevelId == 3){
		$(".tableMenu li:nth-child(1)").addClass("active");
		$(".tableMenu li:nth-child(2)").hide();
		$("[overview-level]").show();
		DefaultValuesSetMainBlock();
		getDrainsInfoStateWise(globallocId,"district");
		
		//District
		getDrainsInfoLocationWise("district",'0','','0','',"districtView",globallocId);
		
		//constituency
		$("#constituencyDistrictNames_chosen").show();
		$("#constituencyDistrictNames").html('');
		$("#constituencyDistrictNames").append("<option value="+globallocId+">"+globalLocationName+" </option>");
		$("#constituencyDistrictNames").trigger("chosen:updated");
		getDrainsInfoLocationWise("assembly",globallocId,'district','0','',"assemblyView",0);
		//Mandal
		$("#mandalDistrictNames_chosen").show();
		$("#mandalConstituencyNames_chosen").show();
		$("#mandalDistrictNames").html('');
		$("#mandalDistrictNames").append("<option value="+globallocId+">"+globalLocationName+" </option>");
		$("#mandalDistrictNames").trigger("chosen:updated");
		getAllConstituenciesForDistrict('mandalConstituencyNames','CONSTITUENCIES',globallocId);
		getDrainsInfoLocationWise("mandal",globallocId,'district','0','',"mandalView",0);
	}else if(globallevelId == 4){
		$(".tableMenu li:nth-child(1)").addClass("active");
		$(".tableMenu li:nth-child(2)").hide();
		$("[overview-level='district']").hide();
		DefaultValuesSetMainBlock();
		getDrainsInfoStateWise(globallocId,"assembly");
		
		//constituency
		$("#constituencyDistrictNames_chosen").hide();
		getDrainsInfoLocationWise("assembly",'0','','0','',"assemblyView",globallocId);
		//Mandal
		$("#mandalDistrictNames_chosen").hide();
		$("#mandalConstituencyNames_chosen").hide();
		getDrainsInfoLocationWise("mandal",'0','',globallocId,'assembly',"mandalView",0);
	}else if(globallevelId == 5){
		$(".tableMenu li:nth-child(1)").addClass("active");
		$(".tableMenu li:nth-child(2)").hide();
		$("[overview-level='district']").hide();
		$("[overview-level='constituency']").hide();
		DefaultValuesSetMainBlock();
		getDrainsInfoStateWise(globallocId,"mandal");
		//Mandal
		$("#mandalDistrictNames_chosen").hide();
		$("#mandalConstituencyNames_chosen").hide();
		getDrainsInfoLocationWise("mandal",'0','','0','',"mandalView",globallocId);
	}
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

function getDrainsInfoStateWise(locId,locationType){
	$("#totalSpinnerId").html(spinner_Drain);
	$("#undergroundSpinnerId").html(spinner_Drain);
	$("#pakkaSpinnerId").html(spinner_Drain);
	$("#kachaSpinnerId").html(spinner_Drain);
	
	var json = {
			fromDate : globalFromDate,
			toDate : globalToDate,
			locationType : locationType ,
			locationId:locId
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

	
function getDrainsInfoLocationWise(locationType,filterId,filterType,subFilterId,subFilterType,divId,locId){
	
	$("#"+divId+"TableDivId").html(spinner);
		var json = {
			fromDate : globalFromDate,
			toDate : globalToDate,
			locationId:locId,
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
	str+='<div><span class="pull-right" style="margin:10px;"><b>Avi - Availabel , Cle - Cleaned , Km - kilometers</b></span></div>';
	str+='<div class="col-md-12 table-responsive">';
	str+='<table class="table table-condensed table-striped" id="datatable'+locationType+'" style="width:100%;">';
        str+='<thead>';
            str+='<tr>';
				if(locationType == "district")
					str+='<th rowspan="2">DISTRICTS</th>';
				else if(locationType == "assembly")
					str+='<th rowspan="2">CONSTITUENCIES</th>';
				else if(locationType == "mandal")
					str+='<th rowspan="2">MANDALS</th>';
				else if(locationType == "constituency")
					str+='<th rowspan="2">PARLIAMENTS</th>';
				
                str+='<th colspan="5" class="text-center">TOTAL</th>';
                str+='<th colspan="5" class="text-center">KACCHA</th>';
                str+='<th colspan="5" class="text-center">PAKKA</th>';
                str+='<th colspan="5" class="text-center">UNDERGROUND</th>';
            str+='</tr>';
            str+='<tr>';
                str+='<th style="background-color:#fff">Avi</th>';
				str+='<th style="background-color:#fff">km</th>';
				str+='<th style="background-color:#fff">Cle</th>';
				str+='<th style="background-color:#fff">km</th>';
                str+='<th style="background-color:#fff">%</th>';
				
                str+='<th style="background-color:#d9c5f2">Avi</th>';
				str+='<th style="background-color:#d9c5f2">km</th>';
                str+='<th style="background-color:#d9c5f2">Cle</th>';
				str+='<th style="background-color:#d9c5f2">km</th>';
                str+='<th style="background-color:#d9c5f2">%</th>';
				
				str+='<th style="background-color:#b1ebf7">Avi</th>';
				str+='<th style="background-color:#b1ebf7">km</th>';
                str+='<th style="background-color:#b1ebf7">Cle</th>';
				str+='<th style="background-color:#b1ebf7">km</th>';
                str+='<th style="background-color:#b1ebf7">%</th>';
				
				str+='<th style="background-color:#fcddef">Avi</th>';
				str+='<th style="background-color:#fcddef">km</th>';
                str+='<th style="background-color:#fcddef">Cle</th>';
				str+='<th style="background-color:#fcddef">km</th>';
                str+='<th style="background-color:#fcddef">%</th>';
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
						if(result[i].totalAvailable !=null && result[i].totalAvailable>0){
							str+='<td style="background-color:#fff">'+result[i].totalAvailable+'</td>';
						}else{
							str+='<td style="background-color:#fff"> - </td>';
						}
						if(result[i].totalAvailableKms !=null && result[i].totalAvailableKms>0){
							str+='<td style="background-color:#fff">'+result[i].totalAvailableKms+'</td>';
						}else{
							str+='<td style="background-color:#fff"> - </td>';
						}
						
						if(result[i].totalCleaned !=null && result[i].totalCleaned>0){
							str+='<td style="background-color:#fff">'+result[i].totalCleaned+'</td>';
						}else{
							str+='<td style="background-color:#fff"> - </td>';
						}
						
						if(result[i].totalCleanedKms !=null && result[i].totalCleanedKms>0){
							str+='<td style="background-color:#fff">'+result[i].totalCleanedKms+'</td>';
						}else{
							str+='<td style="background-color:#fff"> - </td>';
						}
						if(result[i].percentage !=null && result[i].percentage>0){
							str+='<td style="color:#FD3367">'+result[i].percentage+'</td>';
						}else{
							str+='<td style="color:#FD3367"> - </td>';
						}
						
						if(result[i].kachaAvailable !=null && result[i].kachaAvailable >0){
							str+='<td style="background-color:#d9c5f2">'+result[i].kachaAvailable+'</td>';
						}else{
							str+='<td style="background-color:#d9c5f2"> - </td>';
						}
						if(result[i].kachaAvailableKms !=null && result[i].kachaAvailableKms >0){
							str+='<td style="background-color:#d9c5f2">'+result[i].kachaAvailableKms+'</td>';
						}else{
							str+='<td style="background-color:#d9c5f2"> - </td>';
						}
						if(result[i].kachaCleaned !=null && result[i].kachaCleaned >0){
							str+='<td style="background-color:#d9c5f2">'+result[i].kachaCleaned+'</td>';
						}else{
							str+='<td> - </td>';
						}
						
						if(result[i].kachaCleanedKM !=null && result[i].kachaCleanedKM >0){
							str+='<td style="background-color:#d9c5f2">'+result[i].kachaCleanedKM+'</td>';
						}else{
							str+='<td style="background-color:#d9c5f2"> - </td>';
						}
						
						if(result[i].kachaPercentage !=null && result[i].kachaPercentage >0){
							str+='<td style="background-color:#d9c5f2;color:#FD3367">'+result[i].kachaPercentage+'</td>';
						}else{
							str+='<td style="background-color:#d9c5f2;color:#FD3367"> - </td>';
						}
						
						if(result[i].pakkaAvailable !=null && result[i].pakkaAvailable >0){
							str+='<td style="background-color:#b1ebf7">'+result[i].pakkaAvailable+'</td>';
						}else{
							str+='<td style="background-color:#b1ebf7"> - </td>';
						}
						if(result[i].pakkaAvailableKms !=null && result[i].pakkaAvailableKms >0){
								str+='<td style="background-color:#b1ebf7">'+result[i].pakkaAvailableKms+'</td>';
						}else{
							str+='<td style="background-color:#b1ebf7"> - </td>';
						}
						if(result[i].pakkaCleaned !=null && result[i].pakkaCleaned >0){
							str+='<td style="background-color:#b1ebf7">'+result[i].pakkaCleaned+'</td>';
						}else{
							str+='<td style="background-color:#b1ebf7"> - </td>';
						}
						if(result[i].pakkaCleanedKM !=null && result[i].pakkaCleanedKM >0){
						str+='<td style="background-color:#b1ebf7">'+result[i].pakkaCleanedKM+'</td>';
						}else{
							str+='<td style="background-color:#b1ebf7"> - </td>';
						}
						if(result[i].pakkaPercentage !=null && result[i].pakkaPercentage >0){
							str+='<td style="background-color:#b1ebf7;color:#FD3367">'+result[i].pakkaPercentage+'</td>';
						}else{
							str+='<td style="background-color:#b1ebf7;color:#FD3367"> - </td>';
						}
						
						if(result[i].ugAvailable !=null && result[i].ugAvailable >0){
							str+='<td style="background-color:#fcddef">'+result[i].ugAvailable+'</td>';
						}else{
							str+='<td style="background-color:#fcddef"> - </td>';
						}
						if(result[i].ugAvailableKms !=null && result[i].ugAvailableKms >0){
							str+='<td style="background-color:#fcddef">'+result[i].ugAvailableKms+'</td>';
						}else{
							str+='<td style="background-color:#fcddef"> - </td>';
						}
						if(result[i].ugCleaned !=null && result[i].ugCleaned >0){
							str+='<td style="background-color:#fcddef">'+result[i].ugCleaned+'</td>';
						}else{
							str+='<td style="background-color:#fcddef"> - </td>';
						}
						if(result[i].ugCleanedKms !=null && result[i].ugCleanedKms >0){
							str+='<td style="background-color:#fcddef">'+result[i].ugCleanedKms+'</td>';
						}else{
							str+='<td style="background-color:#fcddef"> - </td>';
						}
						if(result[i].ugPercentage !=null && result[i].ugPercentage >0){
							str+='<td style="background-color:#fcddef;color:#FD3367">'+result[i].ugPercentage+'</td>';
						}else{
							str+='<td style="background-color:#fcddef;color:#FD3367"> - </td>';
						}
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
	$("#datatable"+locationType).dataTable({
					"iDisplayLength": 10,
					"aaSorting": [],
					"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});	
}

$(document).on("click","[role='tabDrains_menu'] li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var blockId = $(this).closest("ul").attr("attr_blockId");
	if(blockId == 3){
		if($(this).attr("attr_location_type") == "districts"){
			getDrainsInfoLocationWise("district",'0','','0','','districtView',globallocId);
		}else if($(this).attr("attr_location_type") == "parliament"){
			getDrainsInfoLocationWise("constituency",'0','','0','','districtView',0);
		}
	}else if(blockId == 4){
		if($(this).attr("attr_location_type") == "districts"){
			
			if(globallevelId ==2 || globallevelId == 0){
				getAllDistricts("constituencyDistrictNames","DISTRICTS");
				getDrainsInfoLocationWise("assembly",globallocId,'','0','','assemblyView',0);
			}else{
				$("#constituencyDistrictNames").html('');
				$("#constituencyDistrictNames").append("<option value="+globallocId+">"+globalLocationName+" </option>");
				$("#constituencyDistrictNames").trigger("chosen:updated");
				getDrainsInfoLocationWise("assembly",globallocId,'district','0','','assemblyView',0);
			}
			
		}else if($(this).attr("attr_location_type") == "parliament"){
			getAllParliaments("constituencyDistrictNames","PARLIAMENTS");
			getDrainsInfoLocationWise("assembly",'0','','0','','assemblyView',0);
		}
	}else if(blockId == 5){
		$("#mandalConstituencyNames").html("");
		$("#mandalConstituencyNames").trigger('chosen:updated');
		if($(this).attr("attr_location_type") == "districts"){
			if(globallevelId ==2 || globallevelId == 0){
				getAllDistricts("mandalDistrictNames","DISTRICTS");
				getDrainsInfoLocationWise("mandal",'0','','0','','mandalView',0);
			}else{
				$("#mandalDistrictNames").html('');
				$("#mandalDistrictNames").append("<option value="+globallocId+">"+globalLocationName+" </option>");
				$("#mandalDistrictNames").trigger("chosen:updated");
				getDrainsInfoLocationWise("mandal",globallocId,'district','0','','mandalView',0);
			}
			
		}else if($(this).attr("attr_location_type") == "parliament"){
			getAllParliaments("mandalDistrictNames","PARLIAMENTS");
			getDrainsInfoLocationWise("mandal",'0','','0','','mandalView',0);
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
	getDrainsInfoLocationWise("assembly",districtId,filterType,parlConstId,subFilterType,'assemblyView',0)
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
		getDrainsInfoLocationWise("mandal",districtId,filterType,'0','','mandalView',0);
		getAllConstituenciesForDistrict('mandalConstituencyNames','CONSTITUENCIES',districtId);
	}else if(locationType == "parliament"){
		filterType = "district";
		getDrainsInfoLocationWise("mandal",'0','district',districtId,'constituency','mandalView',0);
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
	getDrainsInfoLocationWise("mandal",'0','district',constId,'assembly','mandalView',0);
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