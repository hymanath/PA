onloadCalls();
function onloadCalls(){
	getDrainsInfoStateWise();
	getDrainsInfoLocationWise("district");
	getDrainsInfoLocationWise("assembly");
	getDrainsInfoLocationWise("mandal");
}

function getDrainsInfoStateWise(){
	var json = {
			fromDate : "01-04-2017",
			toDate : "30-06-2017",
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

function getDrainsInfoLocationWise(locationType){
	var json = {
		fromDate:"01-06-2017",
		toDate:"30-06-2017",
		locationId:0,
		locationType:locationType
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
		buildingTable(result,locationType);
	});
}

function buildingTable(result,locationType){
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
				
                str+='<th style="background-color:#ccc;" colspan="5">TOTAL</th>';
                str+='<th style="background-color:#ccc;" colspan="5">KACCHA</th>';
                str+='<th style="background-color:#ccc;" colspan="5">PAKKA</th>';
                str+='<th style="background-color:#ccc;" colspan="5">UNDERGROUND</th>';
            str+='</tr>';
            str+='<tr>';
                str+='<th></th>';
                str+='<th>Avi</th>';
				str+='<th>km</th>';
				str+='<th>Cle</th>';
				str+='<th>km</th>';
                str+='<th>%</th>';
                str+='<th>Avi</th>';
				str+='<th>km</th>';
                str+='<th>Cle</th>';
				str+='<th>km</th>';
                str+='<th>%</th>';
				str+='<th>Avi</th>';
				str+='<th>km</th>';
                str+='<th>Cle</th>';
				str+='<th>km</th>';
                str+='<th>%</th>';
				str+='<th>Avi</th>';
				str+='<th>km</th>';
                str+='<th>Cle</th>';
				str+='<th>km</th>';
                str+='<th>%</th>';
            str+='</tr>';
        str+='</thead>';
		str+='<tbody>';
			if(result != null && result.length > 0){
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].name;
						//<p><span>174</span>/<span>1003</span></p>
						str+='</td>';
						str+='<td>'+result[i].totalAvailable+'</td>';
						str+='<td>'+result[i].totalAvailableKms+'</td>';
						str+='<td>'+result[i].totalCleaned+'</td>';
						str+='<td>'+result[i].totalCleanedKms+'</td>';
						str+='<td>'+result[i].percentage+'</td>';
						str+='<td>'+result[i].kachaAvailable+'</td>';
						str+='<td>'+result[i].kachaAvailableKms+'</td>';
						str+='<td>'+result[i].kachaCleaned+'</td>';
						str+='<td>'+result[i].kachaCleanedKM+'</td>';
						str+='<td>'+result[i].kachaPercentage+'</td>';
						str+='<td>'+result[i].pakkaAvailable+'</td>';
						str+='<td>'+result[i].pakkaAvailableKms+'</td>';
						str+='<td>'+result[i].pakkaCleaned+'</td>';
						str+='<td>'+result[i].pakkaCleanedKM+'</td>';
						str+='<td>'+result[i].pakkaPercentage+'</td>';
						str+='<td>'+result[i].ugAvailable+'</td>';
						str+='<td>'+result[i].ugAvailableKms+'</td>';
						str+='<td>'+result[i].ugCleaned+'</td>';
						str+='<td>'+result[i].ugCleanedKms+'</td>';
						str+='<td>'+result[i].ugPercentage+'</td>';
					str+='</tr>';
				}
			}else{
				str+='No Data Available.';
			}
        str+='</tbody>';
    str+='</table>';
    str+='</div>';
	
	if(locationType == "district" || locationType == "constituency")
		$("#districtTableDivId").html(str);
	else if(locationType == "assembly")
		$("#assemblyTableDivId").html(str);
	else if(locationType == "mandal")
		$("#mandalTableDivId").html(str);
	
	$("#datatable"+locationType).dataTable();	
}

$(document).on("click","[role='tabDrains_menu'] li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	if($(this).attr("attr_location_type") == "districts"){
		getDrainsInfoLocationWise("district");
	}else if($(this).attr("attr_location_type") == "parliament"){
		getDrainsInfoLocationWise("constituency");
	}
});