var spinner = '<div class="row"><div class="col-sm-12"><div class="d2d-loader"><div class="loader"></div><img src="D2D_Assests/images/login_logo.png"/></div></div></div>';
var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
  wurl = url.substr(0,(url.indexOf(".in")+3));
var glStartDate = moment().subtract(6, 'days').format("DD-MM-YYYY");
var glEndDate = moment().format("DD-MM-YYYY");
var activityId=38;
var globallevelIds='';
var globallevelValues='';
getUserAccessLevelIdsAndValues();

function getUserAccessLevelIdsAndValues()
{
	var jsObj={
		
	}
	$.ajax({
	  type : "POST",
	  url : "getUserAccessLevelIdsAndValuesAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null){
			globallevelIds = result.accessLevelIds
			globallevelValues = result.accessLevelValuesList
		}
		onLoadCalls();
	});
	
	
}

$("#dateRangePickerAUM").daterangepicker({
      opens: 'left',
      startDate: glStartDate,
      endDate: glEndDate,
    locale: {
      format: 'DD-MM-YYYY'
    },
   /*  ranges: {
        'All':[moment().subtract(6, 'days').format("DD-MM-YYYY"), moment().format("DD-MM-YYYY")],
        'Today' : [moment(), moment()],
		'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
        'This Month': [moment().startOf('month'), moment()]s
       
    } */
});

 var dates= $("#dateRangePickerAUM").val();
    var pickerDates = glStartDate+' - '+glEndDate
 
  $('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
    glStartDate = picker.startDate.format('DD-MM-YYYY')
    glEndDate = picker.endDate.format('DD-MM-YYYY')
	onLoadCalls();
  });

getAllDistricts();
function getAllDistricts(){
	$('#district').html("ALL districts");
	var jsObj={
			"stateId":1
		}
	$.ajax({
		type : "GET",
		url : "getAllDistrictsForLoationDashBoardAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		return buildResultforWordCloud(result);
	});
		
}
function buildResultforWordCloud(result){
	var optionStr="";
	
	optionStr+='<option value="0" selected>ALL Districts</option>';
	for(var i in result){
		optionStr+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>';
		
	}$('#district').html(optionStr);

}
	
function onLoadCalls(){
	getRecentImagesList()
	levelWiseSBData(activityId);
	getActivityWiseCounts();
}

function getRecentImagesList(){
	$("#imagesSliderDivId").html(spinner);
	
	var jsObj={
    	levelIds		:globallevelIds,
		levelValues		:globallevelValues,
		startDateStr	:"01-02-2018",
		endDateStr  	:moment().format("DD-MM-YYYY"),
		activityId		:activityId
    }
    $.ajax({
      type : 'POST',
      url : 'getImagesFordalithatejamAction.action',
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result !=null && result.length>0){
			return buildRecentImagesList(result);
		}else{
			$("#imagesSliderDivId").html("NO DATA AVAILABLE");
		}
		
	});
	function buildRecentImagesList(result){
		var str='';
		//str+='<div class="col-sm-12">';
		str+='<ul class="list-inline slider-nav">';
		for(var i in result){
				str+='<li style="background-color:#F75C5D;color:#fff !important;margin:0px 8px;border:1px solid #F75C5D;">';
			
				str+='<img onerror="setDefaultImage(this);" src="https://mytdp.com/activity_documents/'+result[i].imagePath+'" alt="" class="img-responsive" style="width: 240px; height: 185px;margin-bottom:5px;"/>';
				str+='<div style="margin-top:-10px;padding:4px 8px;">';
					if(typeof result[i].districtName == "undefined" || typeof result[i].districtName === undefined)
					{
						str+='<h5 class="m_top5">District: -</h5>';
					}else{
						str+='<h5 class="m_top5">District: '+result[i].districtName+'</h5>';
					}
					if(typeof result[i].constituencyName == "undefined" || typeof result[i].constituencyName === undefined)
					{
						str+='<h5 class="m_top5">Constituency: -</h5>';
					}else{
						if(result[i].constituencyName !=null && result[i].constituencyName.length>15){
							str+='<h5 class="m_top5" >Constituency: <span class="tooltipImgCls" data-toogle="tooltip" title="'+result[i].constituencyName+'" style="cursor:pointer;">'+result[i].constituencyName.substring(0,15)+'...</span></h5>';
						}else{
							str+='<h5 class="m_top5">Constituency: '+result[i].constituencyName+'</h5>';
						}
						
					}
					if((typeof result[i].tehsilName == "undefined" || typeof result[i].tehsilName === undefined) || (typeof result[i].villageName == "undefined" || typeof result[i].villageName === undefined)) 
					{
						
					}else{
						if((result[i].tehsilName != null && $.trim(result[i].tehsilName).length > 0) || (result[i].villageName != null && $.trim(result[i].villageName).length > 0)){
							if(result[i].tehsilName !=null && result[i].tehsilName.length>15){
								str+='<h5 class="m_top5">Mandal: <span class="tooltipImgCls" data-toogle="tooltip" title="'+result[i].tehsilName+'" style="cursor:pointer;">'+result[i].tehsilName.substring(0,15)+'...</span></h5>';
							}else{
								str+='<h5 class="m_top5">Mandal: '+result[i].tehsilName+'</h5>';
							}
							if(result[i].villageName !=null && result[i].villageName.length>15){
							
								str+='<h5 class="m_top5">Panchayat: <span class="tooltipImgCls" data-toogle="tooltip" title="'+result[i].villageName+'" style="cursor:pointer;">'+result[i].villageName.substring(0,15)+'...</span></h5>';
							}else{
								str+='<h5 class="m_top5">Panchayat: '+result[i].villageName+'</h5>';
							}
							
							str+='<h5 class="m_top5">'+result[i].boothNo+'</h5>';
							
						}else{
							if(result[i].lebName !=null && result[i].lebName.length>15){
								str+='<h5 class="m_top5">Municipality: <span class="tooltipImgCls" data-toogle="tooltip" title="'+result[i].lebName+'" style="cursor:pointer;">'+result[i].lebName.substring(0,15)+'...</span></h5>';
							}else{
								str+='<h5 class="m_top5">Municipality: '+result[i].lebName+'</h5>';
							}
							//str+='<h5 class="m_top5">'+result[i].boothNo+'</h5>';
							
							str+='<h5 class="m_top5">'+result[i].boothNo+'</h5>';
							
						}
					}
				str+='</div>';
			str+='</li>';
		}
		str+='</ul>';
		//str+='</div>';
		$("#imagesSliderDivId").html(str);
		$(".tooltipImgCls").tooltip();
		$('.slider-nav').slick({
			slide: 'li',
			slidesToShow: 4,
			slidesToScroll: 1,
			autoplay: true,
			autoplaySpeed: 2000,
			variableWidth:true,
			responsive: [
			{
			  breakpoint: 1024,
			  settings: {
				slidesToShow: 3,
				slidesToScroll: 3,
				infinite: true,
				dots: true
			  }
			},
			{
			  breakpoint: 600,
			  settings: {
				slidesToShow: 2,
				slidesToScroll: 2
			  }
			},
			{
			  breakpoint: 480,
			  settings: {
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			}
			]
		});
		
		
	}
	
}

function levelWiseSBData(divId)
{
	levelWiseSBArr=['district','parliament','constituency'];
	//levelWiseSBArr=['state'];
	var collapse='';
		collapse+='<section>';
			collapse+='<div class="row">';
			
				for(var i in levelWiseSBArr)
				{
					collapse+='<div class="col-sm-12">';
					collapse+='<div class="panel-group" id="accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBArr[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="collapseDebatesIcon '+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"   data-toggle="collapse" data-parent="#accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}else{
									collapse+='<a role="button" class="collapseDebatesIcon collapsed '+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  overview-level-new='+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+' data-toggle="collapse" data-parent="#accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}
								if(levelWiseSBArr[i] == "state" || levelWiseSBArr[i] == "district" || levelWiseSBArr[i] == "constituency")
									collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview</h4>';
								else
									collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview</h4>';
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+levelWiseSBArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
					collapse+='</div>';
				}
			
			collapse+='</div>';
			collapse+='</section>';
	
	$("#levelWiseOverviewId").html(collapse);
	
	
	setTimeout(function(){ 
		for(var i in levelWiseSBArr){
			getSettingActivitiesJBMData(levelWiseSBArr[i],divId);
			$("[overview-level-new]").trigger("click");
		}	
	
	}, 1000);
	
}
function getSettingActivitiesJBMData(locationId,divId){
	$("#"+locationId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			var locationTypeId =0;var locationValue =0;
	if(locationId == 'district'){
		locationTypeId =3
		locationValue=0;
	}else if(locationId == 'constituency'){
		locationTypeId =4
		locationValue=0;
	}else if(locationId == 'parliament'){
		locationValue=0;
		locationTypeId =10
	}

	var jsObj={
		fromDate : '',
	    toDate : '',
		activityId:divId,
		locationScopeId:locationTypeId,
		locationValue:locationValue
	}	
	$.ajax({
	 type: "POST",
	 url: "getLocationWiseJBDataAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildActivityEventdata(result,locationId,divId);
		}else{
			$("#"+locationId).html('NO DATA AVAILABLE');
		}
	});
}
function buildActivityEventdata(result,locationId,divId){
	var tableView='';
	tableView+='<div class="table-responsive">';
		tableView+='<table class="table table-bordered dataTable1'+locationId+'" id="" style="width:100%;border:1px solid lightgrey">';
			tableView+='<thead class="text-capital" style="background-color:#f2f2f2;">';
				tableView+='<tr>';
					if(locationId == 'district'){
						tableView+='<th rowspan =2>District</th>';
					}else if(locationId == 'constituency'){
						tableView+='<th rowspan =2>districtName</th>';
						tableView+='<th rowspan =2>Assembly Constituency</th>';
					}else if(locationId == 'parliament'){
						tableView+='<th rowspan =2>Parliment Constituency</th>';
					}else if(locationId == 'state'){
						tableView+='<th rowspan =2>State</th>';
					}
					tableView+='<th rowspan ="2">Total Panchayaths/ Wards</th>';
					tableView+='<th rowspan ="2">Total SC Population</th>';
					tableView+='<th rowspan ="2">Covered SC Population</th>';
					tableView+='<th rowspan ="2">%</th>';
					tableView+='<th rowspan ="2">Active Dalitha Youth Registered count</th>';
					tableView+='<th rowspan="2">SC Loan Applied Count</th>';
					tableView+='<th colspan="2">Info Cell Conducted</th>';
					//tableView+='<th rowspan="2">Conducted%</th>';
					for(var i in result[0].questionList){
						if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId != 23 && divId !=38){
							tableView+='<th colspan="2">'+result[0].questionList[i].questionName+'</th>';
						}else if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].		questionId == 23){
							tableView+='<th colspan="5">'+result[0].questionList[i].questionName+'</th>';
						}else if (result[0].questionList[i].optionList.length==1){
							if(result[0].questionList[i].questionId != 21){
								tableView+='<th rowspan ="2">'+result[0].questionList[i].questionName+'</th>';
							}else{
								tableView+='<th rowspan ="2">'+result[0].questionList[i].questionName+'-(Minutes)</th>';
							}
						}else if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].		questionId != 23 && divId ==38){
							tableView+='<th colspan="4">'+result[0].questionList[i].questionName+'</th>';
						}
						else{
							tableView+='<th colspan="'+result[0].questionList[i].optionList.length+'">'+result[0].questionList[i].questionName+'</th>';
						}
						
					}
					tableView+='<th rowspan ="2">ఈ కార్యక్రమం లో ఎమ్మెల్యే/ఇంచార్జి హాజరు అయిన  రోజులు  </th>';
				tableView+='</tr>';
				tableView+='<tr>';
					tableView+='<th>Total Count</th>';
					tableView+='<th>%</th>';
				for(var i in result[0].questionList){
						if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId != 23 && divId !=38){
							
							tableView+='<th>Yes Count</th>';
							tableView+='<th>%</th>';
						}else if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId == 23){
							tableView+='<th>Total Count</th>';
							tableView+='<th>Yes Count</th>';
							tableView+='<th>%</th>';
							tableView+='<th>No Count</th>';
							tableView+='<th>%</th>';
						}else if(result[0].questionList[i].optionList.length !=2 && result[0].questionList[i].optionList.length !=1){
							for(var j in result[0].questionList[i].optionList){
								tableView+='<th>'+result[0].questionList[i].optionList[j].optionName+'</th>';
							}
						}else if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId != 23 && divId ==38){
							for(var j in result[0].questionList[i].optionList){
								tableView+='<th>'+result[0].questionList[i].optionList[j].optionName+'</th>';
								tableView+='<th>%</th>';
							}
							
						}
					//tableView+='<th></th>';
				}
				tableView+='</tr>';
			tableView+='</thead>';
			tableView+='<tbody style=align:center">';
				for(var i in result){
					tableView+='<tr>';
					if(locationId == 'constituency'){
						tableView+='<td>'+result[i].districtName+'</td>';
					}
					tableView+='<td>'+result[i].locationName+'</td>';
					tableView+='<td align="center">'+result[i].totalCount+'</td>';
					tableView+='<td align="center">'+result[i].totalPopulation+'</td>';
					tableView+='<td align="center">'+result[i].coveredPopulation+'</td>';
					if(result[i].totalPopulation != null && result[i].totalPopulation >0){
						tableView+='<td align="center">'+parseFloat((result[i].coveredPopulation/result[i].totalPopulation)*100).toFixed(2)+'%</td>';
					}else{
					tableView+='<td>-</td>'
					}
					tableView+='<td align="center">'+result[i].totalRegistered+'</td>';
					tableView+='<td align="center">'+result[i].totalLoanApplied+'</td>';
					tableView+='<td align="center">'+result[i].conductedCount+'</td>';
					tableView+='<td align="center">'+parseFloat((result[i].conductedCount/result[i].totalCount)*100).toFixed(2)+'%</td>';
					for(var j in result[i].questionList){
						if(result[i].questionList[j].optionList.length==2 && result[i].questionList[j].questionId == 23){
							tableView+='<td align="center">'+result[i].questionList[j].count+'</td>';
						}
						 for(var k in result[i].questionList[j].optionList){
							if(result[i].questionList[j].optionList.length==2 && result[i].questionList[j].questionId != 23){
								if(result[i].questionList[j].optionList[k].optionId ==1){
									tableView+='<td align="center">'+result[i].questionList[j].optionList[k].count+'</td>';
									if(result[i].conductedCount !== null && result[i].conductedCount != 0 && result[i].conductedCount != '0'){
										tableView+='<td align="center">'+parseFloat((result[i].questionList[j].optionList[k].count/result[i].conductedCount)*100).toFixed(2)+'%</td>';
									}else{
									tableView+='<td align="center">-</td>';
									}
								}else{
									tableView+='<td align="center">'+result[i].questionList[j].optionList[k].count+'</td>';
									if(result[i].conductedCount !== null && result[i].conductedCount != 0 && result[i].conductedCount != '0'){
										tableView+='<td align="center">'+parseFloat((result[i].questionList[j].optionList[k].count/result[i].conductedCount)*100).toFixed(2)+'%</td>';
									}else{
									tableView+='<td align="center">-</td>';
									}
								}
							}else if(result[i].questionList[j].optionList.length==2 && result[i].questionList[j].questionId == 23){
								tableView+='<td align="center">'+result[i].questionList[j].optionList[k].count+'</td>';
							 	if(result[i].totalCount !==null && result[i].totalCount !=0  && result[i].totalCount != '0' ){
									tableView+='<td align="center">'+parseFloat((result[i].questionList[j].optionList[k].count/result[i].totalCount)*100).toFixed(2)+'%</td>';
								}else{
								tableView+='<td align="center">-</td>';
								}  
							}else if (result[i].questionList[j].optionList.length==1){
								if(result[i].questionList[j].questionId !=21){
									tableView+='<td align="center">'+result[i].questionList[j].optionList[k].percentage+'</td>';
								}else{
									tableView+='<td align="center">'+parseInt(result[i].questionList[j].optionList[k].count/(result[i].conductedCount))+'</td>';
								}
								
							}else{
								tableView+='<td align="center">'+result[i].questionList[j].optionList[k].count+'</td>';
							}
						}
						
					}
					tableView+='<td align="center">'+result[i].count+'</td>';
					tableView+='</tr>';
				}
			tableView+='</tbody>';
		tableView+='</table>';
	tableView+='</div>';
	
	tableView+='<table class="table table-bordered" id="exportExcel'+locationId+'" style="width:100%;border:1px solid lightgrey;display:none;">';
			tableView+='<thead class="text-capital">';
				tableView+='<tr>';
					if(locationId == 'district'){
						tableView+='<th rowspan =2>District</th>';
					}else if(locationId == 'constituency'){
						tableView+='<th rowspan =2>districtName</th>';
						tableView+='<th rowspan =2>Assembly Constituency</th>';
					}else if(locationId == 'parliament'){
						tableView+='<th rowspan =2>Parliment Constituency</th>';
					}
					tableView+='<th rowspan ="2">Total Panchayaths/ Wards</th>';
					tableView+='<th rowspan ="2">Total SC Population</th>';
					tableView+='<th rowspan ="2">Covered SC Population</th>';
					tableView+='<th rowspan ="2">%</th>';
					tableView+='<th rowspan ="2">Total Registered count</th>';
					tableView+='<th rowspan="2">Total Loan Applied Count</th>';
					tableView+='<th colspan="2">Info Cell Conducted</th>';
					//tableView+='<th rowspan="2">Conducted%</th>';
					for(var i in result[0].questionList){
						if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId != 23){
							tableView+='<th colspan="2">'+result[0].questionList[i].questionName+'</th>';
						}else if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId == 23){
							tableView+='<th colspan="5">'+result[0].questionList[i].questionName+'</th>';
						}else if (result[0].questionList[i].optionList.length==1){
							if(result[0].questionList[i].questionId != 21){
								tableView+='<th rowspan ="2">'+result[0].questionList[i].questionName+'</th>';
							}else{
								tableView+='<th rowspan ="2">'+result[0].questionList[i].questionName+'-(Minutes)</th>';
							}
						}else{
							tableView+='<th colspan="'+result[0].questionList[i].optionList.length+'">'+result[0].questionList[i].questionName+'</th>';
						}
						
					}
				tableView+='</tr>';
				tableView+='<tr>';
					tableView+='<th>Total Count</th>';
					tableView+='<th>%</th>';
				for(var i in result[0].questionList){
						if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId != 23){
							
							tableView+='<th>Yes Count</th>';
							tableView+='<th>%</th>';
						}else if(result[0].questionList[i].optionList.length==2 && result[0].questionList[i].questionId == 23){
							tableView+='<th>Total Count</th>';
							tableView+='<th>Yes Count</th>';
							tableView+='<th>%</th>';
							tableView+='<th>No Count</th>';
							tableView+='<th>%</th>';
						}
						if(result[0].questionList[i].optionList.length !=2 && result[0].questionList[i].optionList.length !=1){
							for(var j in result[0].questionList[i].optionList){
								tableView+='<th>'+result[0].questionList[i].optionList[j].optionName+'</th>';
						}
						
					}
					//tableView+='<th></th>';
				}
				tableView+='</tr>';
			tableView+='</thead>';
			tableView+='<tbody>';
				for(var i in result){
					tableView+='<tr>';
					if(locationId == 'constituency'){
						tableView+='<td>'+result[i].districtName+'</td>';
					}
					tableView+='<td>'+result[i].locationName+'</td>';
					tableView+='<td>'+result[i].totalCount+'</td>';
					tableView+='<td>'+result[i].totalPopulation+'</td>';
					tableView+='<td>'+result[i].coveredPopulation+'</td>';
					if(result[i].totalPopulation != null && result[i].totalPopulation >0){
						tableView+='<td>'+parseFloat((result[i].coveredPopulation/result[i].totalPopulation)*100).toFixed(2)+'%</td>';
					}else{
					tableView+='<td>-</td>'
					}
					tableView+='<td>'+result[i].totalRegistered+'</td>';
					tableView+='<td>'+result[i].totalLoanApplied+'</td>';
					tableView+='<td>'+result[i].conductedCount+'</td>';
					tableView+='<td>'+parseFloat((result[i].conductedCount/result[i].totalCount)*100).toFixed(2)+'%</td>';
					for(var j in result[i].questionList){
						if(result[i].questionList[j].optionList.length==2 && result[i].questionList[j].questionId == 23){
							tableView+='<td>'+result[i].questionList[j].count+'</td>';
						}
						 for(var k in result[i].questionList[j].optionList){
							if(result[i].questionList[j].optionList.length==2 && result[i].questionList[j].questionId != 23){
								if(result[i].questionList[j].optionList[k].optionId ==1){
									tableView+='<td>'+result[i].questionList[j].optionList[k].count+'</td>';yesCount=result[i].questionList[j].optionList[k].count;
									if(result[i].conductedCount !==null && result[i].conductedCount !=0){
										tableView+='<td>'+parseFloat((result[i].questionList[j].optionList[k].count/result[i].conductedCount)*100).toFixed(2)+'%</td>';
									}else{
									tableView+='<td>-</td>';
									}
								}
							}else if(result[i].questionList[j].optionList.length==2 && result[i].questionList[j].questionId == 23){
								tableView+='<td>'+result[i].questionList[j].optionList[k].count+'</td>';
							 	if(result[i].conductedCount !==null && result[i].conductedCount !=0 ){
									tableView+='<td>'+parseFloat((result[i].questionList[j].optionList[k].count/result[i].questionList[j].count)*100).toFixed(2)+'%</td>';
								}else{
								tableView+='<td>-</td>';
								} 
							}else if (result[i].questionList[j].optionList.length==1){
								if(result[i].questionList[j].questionId !=21){
									tableView+='<td>'+result[i].questionList[j].optionList[k].percentage+'</td>';
								}else{
									tableView+='<td>'+parseInt(result[i].questionList[j].optionList[k].count/(result[i].conductedCount))+'</td>';
								}
								
							}else{
								tableView+='<td>'+result[i].questionList[j].optionList[k].count+'</td>';
							}
						}
						
					}
					tableView+='</tr>';
				}
			tableView+='</tbody>';
		tableView+='</table>';
		
	$("#"+locationId).html(tableView);
	if(locationId !="district" && locationId !="constituency"){
		$(".dataTable1"+locationId).dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"order": [ 0, 'asc' ],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					//extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o generateExcelcdfdf" attr_id="exportExcel'+locationId+'"></i>',
					titleAttr	: 'CSV',
					//title		:  "ENC WORKS DASHBOARD",
					//filename	:  locationId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				}
			] ,
			"scrollX": true,
			"scrollX": true,
			"scrollCollapse": true,
			"fixedColumns":   {
				"leftColumns": 1,
			}
		});
	}else if(locationId !="parliament" && locationId !="district" ){
		$(".dataTable1"+locationId).dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"order": [ 0, 'asc' ],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					//extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o generateExcelcdfdf" attr_id="exportExcel'+locationId+'"></i>',
					titleAttr	: 'CSV',
					//title		:  "ENC WORKS DASHBOARD",
					//filename	:  locationId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				}
			] ,
			"scrollX": true,
			"scrollX": true,
			"scrollCollapse": true,
			"fixedColumns":   {
				"leftColumns": 2,
			}
		});
	}else{
		$(".dataTable1"+locationId).dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			"scrollX":        true,
			"scrollCollapse": false,
			"fixedColumns":   {
				"leftColumns": 1,
			}
		});
	}
	if(locationId !="district"){
		$("."+divId+locationId).trigger("click");
	}

}
$(document).on("click",".generateExcelcdfdf",function(){
	var id = $(this).attr("attr_id");
	tableToExcel(id, 'Activity DASHBOARD');
});

function getActivityWiseCounts(){
	
	$("#totalVillagesCountId").html(spinner);
	$("#totalyesCountId").html(spinner);
	$("#imagesCovered").html(spinner);
	$("#totalMaybeCountId").html(spinner);
	$("#totalNoCountId").html(spinner);
	$("#todayVisitedCount").html(spinner);
	var jsObj={
		activityId:activityId,
		activityMemberId : 44,
	    stateId : 1,
	    userTypeId : 2
	}	
	$.ajax({
	 type: "POST",
	 url: "getActivityOverAllSummaryAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
			buildActivityCounts(result);
	});
};

function buildActivityCounts(result){
	$("#totalVillagesCountId").html(result[0].apTotal);
	$("#totalyesCountId").html(result[0].yesCount);
	$("#imagesCovered").html(result[0].imagesCovered+"/"+result[0].totalImages);
	$("#totalMaybeCountId").html(result[0].mayBecount);
	$("#totalNoCountId").html(result[0].noCount);
	$("#todayVisitedCount").html(result[0].yesCount);
}

getActivityOverAllSummaryforDelithatejam();
function getActivityOverAllSummaryforDelithatejam(){
	$("#todayLoanAppliedCount").html(spinner);
	$("#todayRegistration").html(spinner);
	$("#totalLoanApplied").html(spinner);
	$("#totalRegistered").html(spinner);
	$("#totalSCPopulation").html(spinner);
	$("#totalCoveredPopulation").html(spinner);
	$("#notCovered").html(spinner);
	var jsObj={
		activityId:activityId,
		activityMemberId : 44,
	    //stateId : globalStateId,
	    //userTypeId : globalUserTypeId,
		fromDateStr:"",
		toDateStr:""
	}	
	$.ajax({
	 type: "POST",
	 url: "getAffiliatedActivityCountAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildActivityaffiliatedCounts(result);
		}else{
			$("#todayLoanAppliedCount").html("NO DATA AVAILABLE");
			$("#todayRegistration").html("NO DATA AVAILABLE");
			$("#totalLoanApplied").html("NO DATA AVAILABLE");
			$("#totalRegistered").html("NO DATA AVAILABLE");
			$("#totalSCPopulation").html("NO DATA AVAILABLE");
			$("#totalCoveredPopulation").html("NO DATA AVAILABLE");
			$("#notCovered").html("NO DATA AVAILABLE");
		}
	});
}

function buildActivityaffiliatedCounts(result){
	$("#todayLoanAppliedCount").html(result[0].todayLoanApplied);
	$("#todayRegistration").html(result[0].todayRegistration);
	$("#totalLoanApplied").html(result[0].totalLoanApplied);
	$("#totalRegistered").html(result[0].totalRegistration);
	$("#totalSCPopulation").html(result[0].totalMembers);
	$("#totalCoveredPopulation").html(result[0].totalCovered);
	$("#notCovered").html(result[0].totalMembers-result[0].totalCovered);
}
DalithaTejamnews();
function DalithaTejamnews(){
	$("#tdpMediaDistricttotalCount").html(spinner);
	$("#tdpMediaDistrictPostiveCount").html(spinner);
	$("#tdpMediaDistrictnegativeCount").html(spinner);
	$("#tdpMediaDistrictpostivePercentage").html(spinner);
	$("#tdpMediaDistrictnaegativePercentage").html(spinner);
//var url1 =wurl+"/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysis/"+glStartDate+"/"+glEndDate+"/1156"
var url1="http://localhost:8446/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysis/01-02-2018/26-02-2018/1156"  
$.ajax({
		type : 'GET', 
		url: url1
    }).then(function(result){
	if(result !=null){
		return buildNewsModule(result);
	}else{
		$("#tdpMediaDistricttotalCount").html("-");
		$("#tdpMediaDistrictPostiveCount").html("-");
		$("#tdpMediaDistrictnegativeCount").html("-");
		$("#tdpMediaDistrictpostivePercentage").html("-");
		$("#tdpMediaDistrictnaegativePercentage").html("-");
		$("#tdpMediaDistricttotalCount").html("-");
		$("#tdpMediaDistrictPostiveCount").html("-");
		$("#tdpMediaDistrictnegativeCount").html("-");
		$("#tdpMediaDistrictpostivePercentage").html("-");
		$("#tdpMediaDistrictnaegativePercentage").html("-");
	}
    });    
}
function buildNewsModule(result){
var otherPartyResult=[]; var tdpPartyResult=[];
	otherPartyResult =result.coreDashBoardVOList1;
	tdpPartyResult =result.coreDashBoardVOList;
	if(tdpPartyResult !=null && tdpPartyResult.length >0){
	$("#tdpMediaDistricttotalCount").html(tdpPartyResult.count);
	$("#tdpMediaDistrictPostiveCount").html(tdpPartyResult.positiveCountMain);
	$("#tdpMediaDistrictnegativeCount").html(tdpPartyResult.negativCountMain);
	$("#tdpMediaDistrictpostivePercentage").html(tdpPartyResult.positiveDistPerc+"%");
	$("#tdpMediaDistrictnaegativePercentage").html(tdpPartyResult.negativeDistPerc+"%");

	$("#tdpMediatotalCount").html(tdpPartyResult.totalCount);
	$("#tdpMediaPostiveCount").html(tdpPartyResult.positiveCountDist);
	$("#tdpMedianegativeCount").html(tdpPartyResult.negativCountDist);
	$("#tdpMediapostivePercentage").html(tdpPartyResult.positiveDistPerc+"%");
	$("#tdpMedianaegativePercentage").html(tdpPartyResult.negativeDistPerc+"%");

	}else{
		$("#tdpMediaDistricttotalCount").html("-");
		$("#tdpMediaDistrictPostiveCount").html("-");
		$("#tdpMediaDistrictnegativeCount").html("-");
		$("#tdpMediaDistrictpostivePercentage").html("-");
		$("#tdpMediaDistrictnaegativePercentage").html("-");
		$("#tdpMediatotalCount").html("-");
	$("#tdpMediaPostiveCount").html("-");
	$("#tdpMedianegativeCount").html("-");
	$("#tdpMediapostivePercentage").html("-");
	$("#tdpMedianaegativePercentage").html("-");

	}if(otherPartyResult !=null && otherPartyResult.length>0){
		var totalnewsCount=0; var totalPosititveCount=0; var totalNegatieveCount=0; 
		var totalDistrictCount=0; var districtPostiveCount=0; var districtNegativeCount=0;
		for (var i in otherPartyResult){
			totalnewsCount=totalnewsCount+otherPartyResult[i].count;
			totalPosititveCount=totalPosititveCount+otherPartyResult[i].positiveCountMain;
			totalNegatieveCount=totalNegatieveCount+otherPartyResult[i].negativCountMain;
			totalDistrictCount=toatlCount+otherPartyResult[i].totalCount;
			districtPostiveCount=districtPostiveCount+otherPartyResult[i].positiveCountDist;
			districtNegativeCount=districtNegativeCount+otherPartyResult[i].negativCountDist;
		}
		$("#otherMediaDistricttotalCount").html(totalDistrictCount);
	$("#otherMediaDistrictPostiveCount").html(districtPostiveCount);
	$("#otherMediaDistrictnegativeCount").html(districtNegativeCount);
	$("#otherMediaDistrictpostivePercentage").html(parseFloat(districtPostiveCount/totalDistrictCount).toFixed(2)+"%");
	$("#otherMediaDistrictnaegativePercentage").html(parseFloat(districtNegativeCount/totalDistrictCount).toFixed(2)+"%");

	$("#otherMediatotalCount").html(totalnewsCount);
	$("#otherMediaPostiveCount").html(totalPosititveCount);
	$("#otherMedianegativeCount").html(totalNegatieveCount);
	$("#otherMediapostivePercentage").html(parseFloat(totalPosititveCount/totalnewsCount).toFixed(2)+"%");
	$("#otherMedianaegativePercentage").html(parseFloat(totalNegatieveCount/totalnewsCount).toFixed(2)+"%");
	}else{
		$("#otherMediaDistricttotalCount").html("-");
	$("#otherMediaDistrictPostiveCount").html("-");
	$("#otherMediaDistrictnegativeCount").html("-");
	$("#otherMediaDistrictpostivePercentage").html("-");
	$("#otherMediaDistrictnaegativePercentage").html("-");

	$("#otherMediatotalCount").html("-");
	$("#otherMediaPostiveCount").html("-");
	$("#otherMedianegativeCount").html("-");
	$("#otherMediapostivePercentage").html("-");
	$("#otherMedianaegativePercentage").html("-");
	}
}