onloadCalls();
function onloadCalls(){
	getDrainsInfobyLocation();
	getDrainsInfo();
}

function getDrainsInfobyLocation(){
	var json = {
			fromDate : "01-04-2017",
			toDate : "30-06-2017",
			locationType : "district" ,
			locationId:"0"
		}
		$.ajax({                
			type:'POST',    
			url: 'getDrainsInfobyLocation',
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

function getDrainsInfo(){

		var json = {
			fromDate:"01-06-2017",
			toDate:"30-06-2017",
			locationId:0,
			locationType:"district"
			}
		$.ajax({                
			type:'POST',    
			url: 'getDrainsInfobyLocationNew',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		});
}