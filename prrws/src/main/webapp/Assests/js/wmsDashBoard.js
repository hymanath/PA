var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalStatusObj={"ALL":"#000","Earth Work":"#F64340","Supply Pipeline":"#EB6F2C","Laying of Pipeline":"#EB9A1D","Refilling":"#9DC639","Testing":"#97A702","Completed":"#00CA85"}
var globalStatusBackGroundObj={"ALL":"#000","Earth Work":"#FEEBEA","Supply Pipeline":"#FCEFE8","Laying of Pipeline":"#FCF4E7","Refilling":"#F4F8EA","Testing":"#F4F5E4","Completed":"#E4F9F2"}

onloadCalls();
function onloadCalls(){
	getAllDistrictsOfAp('districtId','District');
	getAllDistrictsLocationOfAp('locationWiseDistrictId','District');
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
	getAllStatusOfWorkType(workTypeId);
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
		}
	});
}

function buildSelectBox(result,divId,LevelVal){
	
	$("#"+divId).append("<option value='0'>All</option>");
	for(var i in result){
		$("#"+divId).append("<option value="+result[i].key+">"+result[i].value+" </option>");
	}
	$("#"+divId).chosen();
	$("#"+divId).trigger("chosen:updated");
}
function getAllStatusOfWorkType(workTypeId){
	
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
		buildworkStagesDetails(result,workTypeId);
	});
}
function buildworkStagesDetails(result,workTypeId){
	var str='';
	str+='<hr class="m_bottom_0" style="border-top: 1px solid #777 !important;"/>';
	if(result !=null && result.length>0){
		str+='<div class="pad_10 status">';
			str+='<ul class="list-inline workStagesCls">';
				str+='<li style="border:2px solid #000" attr_work_statusid ="0" attr_value="ALL" attr_work_id="'+workTypeId+'"><h5 style="background-color:#000">All</h5></li>';
				for(var i in result){
					str+='<li  attr_work_statusid="'+result[i].key+'" attr_value="'+result[i].value+'" attr_work_id="'+workTypeId+'"><h5 style="background-color:'+globalStatusObj[result[i].value]+'">'+result[i].value+'</h5></li>';
				}
			str+='</ul>';
		str+='</div>';
	}
	str+='<hr class="m_bottom_0" style="border-top: 1px solid #777 !important;"/>';
	$("#workStagesDivId").html(str);
}									
$(document).on("change","#levelId",function(){
	var levelId=$(this).val();
	if(levelId == 3){
		$(".districtCls").show();
		$(".divisionCls,.subdivisionCls,.mandalCls").hide();
	}else if(levelId == 12){
		$(".districtCls,.divisionCls").show();
		$(".subdivisionCls,.mandalCls").hide();
	}else if(levelId == 13){
		$(".districtCls,.divisionCls,.subdivisionCls").show();
		$(".mandalCls").hide();
	}else if(levelId == 5){
		$(".districtCls,.divisionCls,.subdivisionCls,.mandalCls").show();
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
		  "fromDate":"28-03-2018",
		  "toDate":"31-03-2018",
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
	}).done(function(result){
		if(result !=null && result.length>0){
			$("#workStagesCommulativeGraphDivId").css("height","200px");
			buildLocationStatusDayWiseKmsMainGraph(result);
		}else{
			$("#workStagesCommulativeGraphDivId").html("No Data Available")
			$("#workStagesCommulativeGraphDivId").removeAttr("style");
		}
	});
}

function buildLocationStatusDayWiseKmsMainGraph(result){
	
	var mainDataArr=[];
	var categoriesArr=[];
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
		
		console.log(mainDataArr)	
	$('#workStagesCommulativeGraphDivId').highcharts({
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
					return this.value + '';
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
		  "fromDate":"28-03-2018",
		  "toDate":"31-03-2018",
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
			buildLocationLevelStatusDayWiseKmsMainTable(result,locationLevelId);
		}else{
			$("#timeLineLocationDetailsDivId").html("No Data Available");
		}
	});
}

function buildLocationLevelStatusDayWiseKmsMainTable(result,locationLevelId){
	var str='';
	var listLenth=result[0].list.length+2
	str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table_custom_WMS">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Location</th>';
					str+='<th style="background-color:#C4EEE6 !important;">Target&nbsp;KM</th>';
					str+='<th style="background-color:#C4EEE6 !important;">Average&nbsp;KM</th>';
					for(var i in result[0].list){
						str+='<th>'+result[0].list[i].insertedTime+'</th>';
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td style="text-align:left !important;"><h5><i class="fa fa-plus plus_icon_WMS openNextTrShowCls active" aria-hidden="true" attr_tr_id="'+i+'"  attr_tr_name="'+result[i].documentName.replace(/\s+/g, '')+'" attr_locationScopeId="'+locationLevelId+'" attr_locationLevelId="'+result[i].documentId+'"></i> '+result[i].documentName+'</h5></td>';
						str+='<td style="background-color:#E5FBF7 !important;">'+result[i].kms.toFixed(2)+'&nbsp;km</td>';
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
	if($(this).hasClass('active')){
		$(this).removeClass('fa fa-plus').addClass('fa fa-minus');
		$(this).removeClass('plus_icon_WMS').addClass('minus_icon_WMS');
		$(this).removeClass('active');
		$(".show"+tr_name+tr_id).show(); 
		getLocationLevelSubDayWiseKms(locationScopeId,locationLevelId,tr_id)
	}else{
		$(this).removeClass('fa fa-minus').addClass('fa fa-plus');
		$(this).removeClass('minus_icon_WMS').addClass('plus_icon_WMS');
		$(this).addClass('active');
		$(".show"+tr_name+tr_id).hide(); 
	}
});

function getLocationLevelSubDayWiseKms(locationScopeId,locationLevelId,tr_id){
	$("#subLocationLevelWiseStatusDivId"+tr_id).html(spinner);
	var json ={
		  "fromDate":"28-03-2018",
		  "toDate":"31-03-2018",
		  "workTypeId":1,
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
						str+='<th>Total&nbsp;KM</th>';
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
					}else{
						str+='<span>Lat: - </span>';
					}
					if(result[i].longitude !=null && result[i].longitude>0){
						str+='<span>Long:'+result[i].longitude+'</span>';
					}else{
						str+='<span>Long: - </span>';
					}
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
		str+='<h3 class="font_BebasNeue" style="color:#252D30;">Work Progress stage wise Overview</h3>';
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
								str+='<h5 class="font_weight">Works</h5>';
								str+='<input class="m_top10" id="statusBar'+i+'" data-slider-id="statusBar'+i+'Slider" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="'+result[i].completedKmsPercentage.toFixed(0)+'" data-slider-tooltip="hide"/><span class="font_weight pull-right">'+result[i].completedKmsPercentage.toFixed(0)+' %</span>';
								str+='<h5 class="font_weight m_top5">'+result[i].totalWorks+'</h5>';
								str+='<hr class="m_bottom_0 m_top10" style="border-top: 1px solid #ddd !important;"/>';
								str+='<h5 class="font_weight">Works in Km</h5>';
								str+='<input class="m_top10" id="statusBarWorks'+i+'" data-slider-id="statusBarWorks'+i+'Slider" type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="'+result[i].completedPercentage.toFixed(0)+'" data-slider-tooltip="hide"/><span class="font_weight pull-right">'+result[i].completedPercentage.toFixed(0)+' %</span>';
								str+='<h5 class="font_weight m_top5">'+result[i].statusWorks+'</h5>';
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
	if(levelId == 3){
		$(".locationWiseDistrictCls").show();
		$(".locationWiseDivisionCls").hide();
		$(".locationWiseSubdivisionCls").hide();
		$(".locationWiseMandalCls").hide();
	}else if(levelId == 12){
		$(".locationWiseDistrictCls").show();
		$(".locationWiseDivisionCls").show();
		$(".locationWiseSubdivisionCls").hide();
		$(".locationWiseMandalCls").hide();
	}else if(levelId == 13){
		$(".locationWiseDistrictCls").show();
		$(".locationWiseDivisionCls").show();
		$(".locationWiseSubdivisionCls").show();
		$(".locationWiseMandalCls").hide();
	}else if(levelId == 5){
		$(".locationWiseDistrictCls").show();
		$(".locationWiseDivisionCls").show();
		$(".locationWiseSubdivisionCls").show();
		$(".locationWiseMandalCls").show();
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
										str+='<td style="text-align:left !important;">'+result[i].location+'</td>';
										str+='<td style="text-align:left !important;">'+result[i].worksList[j].workName+'</td>';
										str+='<td>'+result[i].worksList[j].totalKms.toFixed(2)+'&nbsp;km</td>';
										for(var k in result[i].worksList[j].statusList){
											str+='<td>'+result[i].worksList[j].statusList[k].totalKms.toFixed(2)+'&nbsp;km<br/><span style="font-size:12px;color:#00CA90;">'+result[i].worksList[j].statusList[k].completedPercentage.toFixed(2)+' %</span></td>';
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
	
	getAllDistrictsOfAp('districtId','District');
	getAllDistrictsLocationOfAp('locationWiseDistrictId','District');
	
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
	
	$("#locationLevelHeadingId").html(locationName+" "+levelName+" Level Details");
	getLocationLevelWiseOverviewDetails(workId,locationValue);
	getLocationLevelStatusWiseOverviewDetails(workId,locationValue)
	getWorkZoneStatusWiseKms(workId,locationValue);
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
		}
	});
}

function buildWorkZoneStatusWiseKms(result){
	var str='';
	
	$("#locationLevelThirdBlockDivId").html(str);
}