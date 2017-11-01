var globalFromDate = moment().subtract(3,'month').startOf("month").format('DD-MM-YYYY');    
var globalToDate = moment().format('DD-MM-YYYY');
var levelWiseData=["DISTRICT","VILLAGE"]
getSessionToken();
function getSessionToken(){
	
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
function onloadCalls(sessionToken){
	
	buildlevelWiseData("WaterTank",sessionToken);
}
$(document).on('click','.calendar_active_cls li', function(){
	var date = $(this).attr("attr_val");
	
	if(date == 'Week'){
		globalFromDate = moment().subtract(1,'week').format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html("This week")
	}else if(date == 'Month'){
		globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html("This Month - "+moment().format("MMMM"));
	}else if(date == '3Months'){
		globalFromDate = moment().subtract(3,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html(moment().subtract(3,'month').format("MMMM")+" - "+moment().format("MMMM"));
	}else if(date == '6Months'){
		globalFromDate = moment().subtract(6,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html(moment().subtract(6,'month').format("MMMM")+" - "+moment().format("MMMM"));
	}else if(date == 'Overall'){
		globalFromDate = moment().subtract(20,'years').startOf("year").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html("Overall");
	}else if(date == 'Year'){
		globalFromDate = moment().startOf("year").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html(globalFromDate+" to "+globalToDate);
	}
	
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	if(date != "custom"){
		//onloadCalls();
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
		getDistrictWiseClorinationDetails(sessionToken);
}

function getDistrictWiseClorinationDetails(sessionToken){
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
			
		});
	}