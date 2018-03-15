var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
getWorkTypeWiseCompletedDetails();
timeLineAndLocationWiseDetails();
function getWorkTypeWiseCompletedDetails(){
	
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
		str+='<li class="m_top10">';
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
							str+='<div class="col-sm-5">';
								str+='<h4 class="font_BebasNeue text-capital">Work proposal for</h4>';
								str+='<h4 class="font_BebasNeue text-capital m_top5 f_15">'+result[i].workPraposedAreas+' WORKS</h4>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								str+='<i class="fa fa-hand-o-right hand_icon" aria-hidden="true"></i>';
							str+='</div>';
							str+='<div class="col-sm-5">';
								str+='<h4 class="font_BebasNeue text-capital">Work Zones</h4>';
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

function timeLineAndLocationWiseDetails(){
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
									collapse+='<option value="3">District</option>';
									collapse+='<option value="12">Division</option>';
									collapse+='<option value="13">Sub Division</option>';
									collapse+='<option value="5">Mandal</option>';
									//collapse+='<option value="6">Village</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2">';
								collapse+='<label>District</label>';
								collapse+='<select class="form-control chosen-select" id="districtId">';
									collapse+='<option value="0">Select District</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2">';
								collapse+='<label>Division</label>';
								collapse+='<select class="form-control chosen-select" id="divisionId">';
									collapse+='<option value="0">Select Division</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2">';
								collapse+='<label>Sub-Division</label>';
								collapse+='<select class="form-control chosen-select" id="subdivisionId">';
									collapse+='<option value="0">Select Sub Division</option>';
								collapse+='</select>';
							collapse+='</div>';
							collapse+='<div class="col-sm-2">';
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
								collapse+='<button type="button" class="btn btn-primary btn-sm getTimeLocationCls m_top20">SUBMIT</button>';
							collapse+='</div>';
						collapse+='</div>';
						collapse+='<div class="row">';
							collapse+='<div id="workStagesDivId"></div>';
						collapse+='</div>';
						collapse+='<div class="row">';
							collapse+='<div class="pad_border">';
								collapse+='<div id="workStagesCommulativeGraphDivId"></div>';
							collapse+='</div>';
						collapse+='</div>';
						
						collapse+='<div id="timeLineLocationDetailsDivId"></div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	
	$("#timeLinesWorkTypesDivId").html(collapse);
	$(".chosen-select").chosen();	
	getAllDistrictsOfAp('districtId','District');
	getAllStatusOfWorkType();
	getLocationStatusDayWiseKms();
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
$(document).on("change","#districtId",function(){
	var districtId=$(this).val();
	getDivisionsOfDistrict(districtId,'divisionId','Division');
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
		}
	});
}

function buildSelectBox(result,divId,LevelVal){
	
	$("#"+divId).append("<option value='0'>Select "+LevelVal+"</option>");
	for(var i in result){
		$("#"+divId).append("<option value="+result[i].key+">"+result[i].value+" </option>");
	}
	$("#"+divId).chosen();
	$("#"+divId).trigger("chosen:updated");
}
function getAllStatusOfWorkType(){
	
	var json = {
		"workTypeId":1
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
		buildworkStagesDetails(result);
	});
}
function buildworkStagesDetails(result){
	var str='';
	var globalStatusObj={"ALL":"#000","Earth Work":"#F64340","Supply Pipeline":"#EB6F2C","Laying of Pipeline":"#EB9A1D","Refilling":"#9DC639","Testing":"#97A702","Completed":"#00CA85"}
	str+='<hr class="m_bottom_0"/>';
	str+='<div class="pad_10 status">';
		str+='<ul class="list-inline workStagesCls">';
			str+='<li style="background-color:#000">All</li>';
			for(var i in result){
				str+='<li style="background-color:'+globalStatusObj[result[i].value]+'" attr_work_statusid="'+result[i].key+'">'+result[i].value+'</li>';
			}
		str+='</ul>';
	str+='</div>';
	str+='<hr class="m_bottom_0"/>';
	$("#workStagesDivId").html(str);
}

function getLocationStatusDayWiseKms(){
	
	var json ={
		  "fromDate":"01-03-2018",
		  "toDate":"14-03-2018",
		  "statusId":"8",
		  "workTypeId":1,
		  "districtId":15
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
	}).done(function(result){
		if(result !=null && result.length>0){
			
		}
	});
}