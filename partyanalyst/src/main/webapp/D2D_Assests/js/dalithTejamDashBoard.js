var spinner = '<div class="row"><div class="col-sm-12"><div class="d2d-loader"><div class="loader"></div><img src="D2D_Assests/images/login_logo.png"/></div></div></div>';
var spinner_count = '<div class="row"><div class="col-sm-12"><img src="D2D_Assests/images/spinner.gif" style="width:20px;height:20px;"/></div></div>';
var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
  wurl = url.substr(0,(url.indexOf(".in")+3));
var glStartDate = moment().subtract(6, 'days').format("DD-MM-YYYY");
var glEndDate = moment().format("DD-MM-YYYY");
$(".chosen-select").chosen();
var activityId=38;
var globallevelIds='';
var globallevelValues='';

$("#dateRangePickerAUM").daterangepicker({
      opens: 'left',
      startDate: glStartDate,
      endDate: glEndDate,
    locale: {
      format: 'DD-MM-YYYY'
    },
	ranges: {
		'Last 7 Days':[moment().subtract(6, 'days').format("DD/MM/YYYY"), moment().format("DD/MM/YYYY")],
		'Today' : [moment(), moment()],
		'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		//'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		//'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		'This Month': [moment().startOf('month'), moment()],
		//'This Year': [moment().startOf('Year'), moment()]
	}
   
});

	var dates= $("#dateRangePickerAUM").val();
    var pickerDates = glStartDate+' - '+glEndDate
	if(dates == pickerDates)
	{
		$("#dateRangePickerAUM").val('Last 7 Days');
		$("#newsDateId").html('Last 7 Days');
	}
  $('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
		glStartDate = picker.startDate.format('DD-MM-YYYY');
		glEndDate = picker.endDate.format('DD-MM-YYYY');
		$("#newsDateId").html(glStartDate+" / "+glEndDate);
		onLoadCalls();
  });
 getUserAccessLevelIdsAndValues(); 
 function getUserAccessLevelIdsAndValues(){
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
 function onLoadCalls(){
	getAllDistricts();
	getActivityWiseCounts();
	getActivityOverAllSummaryforDelithatejam();
	getRecentImagesList(); 
	DalithaTejamnews();
	levelWiseSBData(activityId);
	levelWiseLeaderData("leaderWise")
}

function getAllDistricts(){
	$('#districtId').html("");
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
		
	}
	
	$('#districtId').html(optionStr);
	$('#districtId').chosen();
	$('#districtId').trigger("chosen:updated");
}
function getActivityWiseCounts(){
	$("#totalVillagesCountId").html(spinner_count);
	$("#totalyesCountId").html(spinner_count);
	$("#totalMaybeCountId").html(spinner_count);
	$("#totalNoCountId").html(spinner_count);
	$("#todayVisitedCount").html(spinner_count);
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
		if(result !=null){
			buildActivityCounts(result);
		}
	});
}
function buildActivityCounts(result){
	if(result[0].apTotal !=null && result[0].apTotal>0){
		$("#totalVillagesCountId").html(result[0].apTotal);
	}else{
		$("#totalVillagesCountId").html('-');
	}
	
	if(result[0].yesCount !=null && result[0].yesCount>0){
		$("#totalyesCountId").html(result[0].yesCount);
	}else{
		$("#totalyesCountId").html(' - ');
	}
	
	if(result[0].totalImages !=null && result[0].totalImages>0){
		$("#imagesCovered").html("<b>"+result[0].imagesCovered+"/"+result[0].totalImages+"</b>");
	}else{
		$("#imagesCovered").html(' - ');
	}
	
	if(result[0].mayBecount !=null && result[0].mayBecount>0){
		$("#totalMaybeCountId").html(result[0].mayBecount);
	}else{
		$("#totalMaybeCountId").html(' - ');
	}
	
	if(result[0].noCount !=null && result[0].noCount>0){
		$("#totalNoCountId").html(result[0].noCount);
	}else{
		$("#totalNoCountId").html(' - ');
	}
	if(result[0].noCount !=null && result[0].noCount>0){
		$("#todayVisitedCount").html(result[0].yesCount);
	}else{
		$("#todayVisitedCount").html(' - ');
	}
	
	
}
function getActivityOverAllSummaryforDelithatejam(){
	$("#todayLoanAppliedCount").html(spinner_count);
	$("#todayRegistration").html(spinner_count);
	$("#totalLoanApplied").html(spinner_count);
	$("#totalRegistered").html(spinner_count);
	$("#totalSCPopulation").html(spinner_count);
	$("#totalCoveredPopulation").html(spinner_count);
	$("#notCovered").html(spinner_count);
	var jsObj={
		activityId:activityId,
		activityMemberId : 44,
	    fromDateStr:glStartDate,
		toDateStr:glEndDate
	}	
	$.ajax({
	 type: "POST",
	 url: "getAffiliatedActivityCountAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildActivityaffiliatedCounts(result);
		}
	});
}
function buildActivityaffiliatedCounts(result){
	if(result[0].todayLoanApplied !=null && result[0].todayLoanApplied>0){
		$("#todayLoanAppliedCount").html(result[0].todayLoanApplied);
	}else{
		$("#todayLoanAppliedCount").html(' - ');
	}
	
	if(result[0].todayRegistration !=null && result[0].todayRegistration>0){
		$("#todayRegistration").html(result[0].todayRegistration);
	}else{
		$("#todayRegistration").html(' - ');
	}
	
	if(result[0].totalLoanApplied !=null && result[0].totalLoanApplied>0){
		$("#totalLoanApplied").html(result[0].totalLoanApplied);
	}else{
		$("#totalLoanApplied").html(' - ');
	}
	
	if(result[0].totalRegistration !=null && result[0].totalRegistration>0){
		$("#totalRegistered").html(result[0].totalRegistration);
	}else{
		$("#totalRegistered").html(' - ');
	}
	
	if(result[0].totalMembers !=null && result[0].totalMembers>0){
		$("#totalSCPopulation").html(result[0].totalMembers);
	}else{
		$("#totalSCPopulation").html(' - ');
	}
	
	if(result[0].totalCovered !=null && result[0].totalCovered>0){
		$("#totalCoveredPopulation").html(result[0].totalCovered);
	}else{
		$("#totalCoveredPopulation").html(' - ');
	}
	var totalNotCovered = result[0].totalMembers-result[0].totalCovered;
	if(totalNotCovered !=null && totalNotCovered>0){
		$("#notCovered").html(totalNotCovered);
	}else{
		$("#notCovered").html(' - ');
	}
	
}
function getRecentImagesList(){
	$("#imagesSliderDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	var jsObj={
    	levelIds		:globallevelIds,
		levelValues		:globallevelValues,
		startDateStr	:glStartDate,
		endDateStr  	:glEndDate,
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
							if(result[i].boothNo !=null && result[i].boothNo>0){
								str+='<h5 class="m_top5">'+result[i].boothNo+'</h5>';
							}
							
							
						}else{
							if(result[i].lebName !=null && result[i].lebName.length>15){
								str+='<h5 class="m_top5">Municipality: <span class="tooltipImgCls" data-toogle="tooltip" title="'+result[i].lebName+'" style="cursor:pointer;">'+result[i].lebName.substring(0,15)+'...</span></h5>';
							}else{
								str+='<h5 class="m_top5">Municipality: '+result[i].lebName+'</h5>';
							}
							//str+='<h5 class="m_top5">'+result[i].boothNo+'</h5>';
							
							if(result[i].boothNo !=null && result[i].boothNo>0){
								str+='<h5 class="m_top5">'+result[i].boothNo+'</h5>';
							}
							
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
function DalithaTejamnews(){
	$("#dalithaTejamOnNewsDivId").html("<div class='spinner'><div class='dot1'></div><div class='dot2'></div></div>");
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysis/"+glStartDate+"/"+glStartDate+"/1156"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysis/"+glStartDate+"/"+glStartDate+"/1156"
	}).then(function(result){
		if(result !=null){
			buildNewsModule(result);
		}else{
			$("#dalithaTejamOnNewsDivId").html("No Data Available");	
		}
	});
}

function buildNewsModule(result){
	var str='';
	
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-6">';
			str+='<div class="yash_color_news">';
				str+='<h4 class="font_weight">TDP Party</h4>';
				for(var i in result.coreDashBoardVOList){
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">MAIN EDITION</h5>';
								str+='<h4 class="blue-text-news m_top10">'+result.coreDashBoardVOList[i].count+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								str+='<h4 class="green-text-news m_top10">'+result.coreDashBoardVOList[i].positiveCountMain	+' </h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								str+='<h4 class="red-text-news m_top10" >'+result.coreDashBoardVOList[i].negativCountMain+'</h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">DIST EDITION</h5>';
								str+='<h4 class="blue-text-news m_top10">'+result.coreDashBoardVOList[i].totalCount+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								str+='<h4 class="green-text-news m_top10" > '+result.coreDashBoardVOList[i].positiveCountDist+' <span class="small-font" >'+result.coreDashBoardVOList[i].positiveDistPerc+'%</span></h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								str+='<h4 class="red-text-news m_top10" > '+result.coreDashBoardVOList[i].negativCountDist+' <span class="small-font" >'+result.coreDashBoardVOList[i].negativeDistPerc+'%</span></h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
				
				
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-6">';
			str+='<div class="yash_color_news">';
				str+='<h4 class="font_weight">Other Party</h4>';
				for(var i in result.coreDashBoardVOList1){
					var totalmainOtherParty=0;
					var totalmainOtherPartyPosCount=0;
					var totalmainOtherPartyNegCount=0;
					var totalDistOtherPartyCount=0;
					var totalDistOtherPartyPosCount=0;
					var totalDistOtherPartyNegCount=0;
					
					
					
					totalmainOtherParty = totalmainOtherParty+result.coreDashBoardVOList1[i].count;
					totalmainOtherPartyPosCount = totalmainOtherPartyPosCount+result.coreDashBoardVOList1[i].positiveCountMain;
					totalmainOtherPartyNegCount = totalmainOtherPartyNegCount+result.coreDashBoardVOList1[i].positiveCountMain;
					
					
					totalDistOtherPartyCount = totalDistOtherPartyCount+result.coreDashBoardVOList1[i].totalCount;
					totalDistOtherPartyPosCount = totalDistOtherPartyPosCount+result.coreDashBoardVOList1[i].positiveCountDist;
					totalDistOtherPartyNegCount = totalDistOtherPartyNegCount+result.coreDashBoardVOList1[i].negativCountDist;
					
					var otherMediaMainPostivePercentage = parseFloat(totalmainOtherPartyPosCount/totalmainOtherParty).toFixed(2)+"%";
					var otherMediaMainNegativePercentage = parseFloat(totalmainOtherPartyNegCount/totalmainOtherParty).toFixed(2)+"%";
					
					var otherMediaDistrictPostivePercentage = parseFloat(totalDistOtherPartyPosCount/totalDistOtherPartyCount).toFixed(2)+"%";
					var otherMediaDistrictNegativePercentage = parseFloat(totalDistOtherPartyNegCount/totalDistOtherPartyCount).toFixed(2)+"%";
					
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">MAIN EDITION</h5>';
								str+='<h4 class="blue-text-news m_top10">'+totalmainOtherParty+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								if(totalmainOtherPartyPosCount !=null && totalmainOtherPartyPosCount>0){
									str+='<h4 class="green-text-news m_top10" > '+totalmainOtherPartyPosCount+' <span class="small-font">'+otherMediaMainPostivePercentage+'</span></h4>';
								}else{
									str+='<h4 class="green-text-news m_top10" > '+totalmainOtherPartyPosCount+'</h4>';
								}
								
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								if(totalmainOtherPartyNegCount !=null && totalmainOtherPartyNegCount>0){
									str+='<h4 class="red-text-news m_top10" >'+totalmainOtherPartyNegCount+' <span class="small-font">'+otherMediaMainNegativePercentage+'</span></h4>';
								}else{
									str+='<h4 class="red-text-news m_top10" > '+totalmainOtherPartyNegCount+'</h4>';
								}
								
								
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">DIST EDITION</h5>';
								str+='<h4 class="blue-text-news m_top10" >'+totalDistOtherPartyCount+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								if(totalDistOtherPartyPosCount !=null && totalDistOtherPartyPosCount>0){
									str+='<h4 class="green-text-news m_top10" >'+totalDistOtherPartyPosCount+' <span class="small-font" >'+otherMediaDistrictPostivePercentage+'</span></h4>';
								}else{
									str+='<h4 class="green-text-news m_top10" > '+totalDistOtherPartyPosCount+'</h4>';
								}
								
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								if(totalDistOtherPartyPosCount !=null && totalDistOtherPartyPosCount>0){
									str+='<h4 class="red-text-news m_top10" >'+totalDistOtherPartyPosCount+'<span class="small-font" >'+otherMediaDistrictNegativePercentage+'</span></h4>';
								}else{
									str+='<h4 class="red-text-news m_top10" > '+totalDistOtherPartyPosCount+'</h4>';
								}
								
								
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
				
				
			str+='</div>';
		str+='</div>';
		
	str+='</div>';
		
			$("#dalithaTejamOnNewsDivId").html(str);
}

function levelWiseSBData(divId)
{
	levelWiseSBArr=['district','parliament','constituency'];
	var collapse='';
	for(var i in levelWiseSBArr)
	{
		collapse+='<div class="col-sm-12">';
		collapse+='<div class="panel-group" id="accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-gray" style="box-shadow:none;">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBArr[i]+'">';
				
				collapse+='<a role="button" class="panelCollapseIcon1 '+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"   data-toggle="collapse" data-parent="#accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" >';
				
					/* if(i == 0)
					{
						collapse+='<a role="button" class="panelCollapseIcon1 '+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"   data-toggle="collapse" data-parent="#accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" >';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon1 collapsed '+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  overview-level-new='+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+' data-toggle="collapse" data-parent="#accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" >';
					} */
					collapse+='<h5 class="text-capital" style="color:#000;">'+levelWiseSBArr[i]+' level overview</h5>';
						
					collapse+='</a>';
				collapse+='</div>';
				
				collapse+='<div id="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
				
				/* if(i == 0)
				{
					collapse+='<div id="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
				}else{
					collapse+='<div id="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
				}
				 */
					collapse+='<div class="panel-body">';
						collapse+='<div id="'+levelWiseSBArr[i]+'"></div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	}
			
			
	
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
		fromDate :'',
	    toDate :'',
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
		tableView+='<table class="table table-bordered dataTable1'+locationId+' table_custom_data" id="" style="width:100%;border:1px solid lightgrey">';
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
					tableView+='<th rowspan ="2">ఈ కార్యక్రమం లో ఎమ్మెల్యే/ఇంచార్జి హాజరు అయిన  రోజులు </th>';
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

}
$(document).on("click",".generateExcelcdfdf",function(){
	var id = $(this).attr("attr_id");
	tableToExcel(id, 'DALITHA TEJAM DASHBOARD');
});
$(document).on("click",".refreshDalithaTejam",function(){
	onLoadCalls();
});
$(document).on("change","#districtId",function(){
	//onLoadCalls();
});

function buildNewsModule(result){
	var str='';
	
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-6">';
			str+='<div class="yash_color_news">';
				str+='<h4 class="font_weight">TDP Party</h4>';
				for(var i in result.coreDashBoardVOList){
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">MAIN EDITION</h5>';
								str+='<h4 class="blue-text-news m_top10">'+result.coreDashBoardVOList[i].count+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								str+='<h4 class="green-text-news m_top10">'+result.coreDashBoardVOList[i].positiveCountMain	+' </h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								str+='<h4 class="red-text-news m_top10" >'+result.coreDashBoardVOList[i].negativCountMain+'</h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">DIST EDITION</h5>';
								str+='<h4 class="blue-text-news m_top10">'+result.coreDashBoardVOList[i].totalCount+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								str+='<h4 class="green-text-news m_top10" > '+result.coreDashBoardVOList[i].positiveCountDist+' <span class="small-font" >'+result.coreDashBoardVOList[i].positiveDistPerc+'%</span></h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								str+='<h4 class="red-text-news m_top10" > '+result.coreDashBoardVOList[i].negativCountDist+' <span class="small-font" >'+result.coreDashBoardVOList[i].negativeDistPerc+'%</span></h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
				
				
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-6">';
			str+='<div class="yash_color_news">';
				str+='<h4 class="font_weight">Other Party</h4>';
				for(var i in result.coreDashBoardVOList1){
					var totalmainOtherParty=0;
					var totalmainOtherPartyPosCount=0;
					var totalmainOtherPartyNegCount=0;
					var totalDistOtherPartyCount=0;
					var totalDistOtherPartyPosCount=0;
					var totalDistOtherPartyNegCount=0;
					
					
					
					totalmainOtherParty = totalmainOtherParty+result.coreDashBoardVOList1[i].count;
					totalmainOtherPartyPosCount = totalmainOtherPartyPosCount+result.coreDashBoardVOList1[i].positiveCountMain;
					totalmainOtherPartyNegCount = totalmainOtherPartyNegCount+result.coreDashBoardVOList1[i].positiveCountMain;
					
					
					totalDistOtherPartyCount = totalDistOtherPartyCount+result.coreDashBoardVOList1[i].totalCount;
					totalDistOtherPartyPosCount = totalDistOtherPartyPosCount+result.coreDashBoardVOList1[i].positiveCountDist;
					totalDistOtherPartyNegCount = totalDistOtherPartyNegCount+result.coreDashBoardVOList1[i].negativCountDist;
					
					var otherMediaMainPostivePercentage = parseFloat(totalmainOtherPartyPosCount/totalmainOtherParty).toFixed(2)+"%";
					var otherMediaMainNegativePercentage = parseFloat(totalmainOtherPartyNegCount/totalmainOtherParty).toFixed(2)+"%";
					
					var otherMediaDistrictPostivePercentage = parseFloat(totalDistOtherPartyPosCount/totalDistOtherPartyCount).toFixed(2)+"%";
					var otherMediaDistrictNegativePercentage = parseFloat(totalDistOtherPartyNegCount/totalDistOtherPartyCount).toFixed(2)+"%";
					
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">MAIN EDITION</h5>';
								str+='<h4 class="blue-text-news m_top10">'+totalmainOtherParty+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								if(totalmainOtherPartyPosCount !=null && totalmainOtherPartyPosCount>0){
									str+='<h4 class="green-text-news m_top10" > '+totalmainOtherPartyPosCount+' <span class="small-font">'+otherMediaMainPostivePercentage+'</span></h4>';
								}else{
									str+='<h4 class="green-text-news m_top10" > '+totalmainOtherPartyPosCount+'</h4>';
								}
								
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								if(totalmainOtherPartyNegCount !=null && totalmainOtherPartyNegCount>0){
									str+='<h4 class="red-text-news m_top10" >'+totalmainOtherPartyNegCount+' <span class="small-font">'+otherMediaMainNegativePercentage+'</span></h4>';
								}else{
									str+='<h4 class="red-text-news m_top10" > '+totalmainOtherPartyNegCount+'</h4>';
								}
								
								
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">DIST EDITION</h5>';
								str+='<h4 class="blue-text-news m_top10" >'+totalDistOtherPartyCount+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								if(totalDistOtherPartyPosCount !=null && totalDistOtherPartyPosCount>0){
									str+='<h4 class="green-text-news m_top10" >'+totalDistOtherPartyPosCount+' <span class="small-font" >'+otherMediaDistrictPostivePercentage+'</span></h4>';
								}else{
									str+='<h4 class="green-text-news m_top10" > '+totalDistOtherPartyPosCount+'</h4>';
								}
								
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								if(totalDistOtherPartyPosCount !=null && totalDistOtherPartyPosCount>0){
									str+='<h4 class="red-text-news m_top10" >'+totalDistOtherPartyPosCount+'<span class="small-font" >'+otherMediaDistrictNegativePercentage+'</span></h4>';
								}else{
									str+='<h4 class="red-text-news m_top10" > '+totalDistOtherPartyPosCount+'</h4>';
								}
								
								
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
				
				
			str+='</div>';
		str+='</div>';
		
	str+='</div>';
		
			$("#dalithaTejamOnNewsDivId").html(str);
}

function levelWiseLeaderData(divId)
{
	var levelWiseSBLeaderArr=['district','parliament','constituency'];
	var collapse='';
	for(var i in levelWiseSBLeaderArr)
	{
		collapse+='<div class="col-sm-12">';
		collapse+='<div class="panel-group" id="accordionleader'+divId+''+levelWiseSBLeaderArr[i]+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-gray" style="box-shadow:none;">';
			
				collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBLeaderArr[i]+'">';
				
				collapse+='<a role="button" class="panelCollapseIcon1 '+divId+''+levelWiseSBLeaderArr[i]+'"   data-toggle="collapse" data-parent="#accordionleader'+divId+''+levelWiseSBLeaderArr[i]+'" href="#collapse'+divId+''+levelWiseSBLeaderArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" >';
				
					/* if(i == 0)
					{
						collapse+='<a role="button" class="panelCollapseIcon1 '+divId+''+levelWiseSBLeaderArr[i]+'"   data-toggle="collapse" data-parent="#accordionleader'+divId+''+levelWiseSBLeaderArr[i]+'" href="#collapse'+divId+''+levelWiseSBLeaderArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" >';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon1 collapsed '+divId+''+levelWiseSBLeaderArr[i]+'"  overview-level-new='+divId+''+levelWiseSBLeaderArr[i]+' data-toggle="collapse" data-parent="#accordionleader'+divId+''+levelWiseSBLeaderArr[i]+'" href="#collapse'+divId+''+levelWiseSBLeaderArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" >';
					} */
					collapse+='<h5 class="text-capital" style="color:#000;">Leaders Participated- '+levelWiseSBLeaderArr[i]+' level overview</h5>';
						
					collapse+='</a>';
				collapse+='</div>';
				collapse+='<div id="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId+''+levelWiseSBLeaderArr[i]+'">';
				/* if(i == 0)
				{
					collapse+='<div id="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId+''+levelWiseSBLeaderArr[i]+'">';
				}else{
					collapse+='<div id="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId+''+levelWiseSBLeaderArr[i]+'">';
				} */
				
				
					collapse+='<div class="panel-body">';
						//collapse+='<div class="row">';
							collapse+='<div id="'+levelWiseSBLeaderArr[i]+''+divId+'"></div>';
						//collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	}
			
			
	
	$("#leaderandlocationwiseOverviewId").html(collapse);
	setTimeout(function(){ 
		for(var i in levelWiseSBLeaderArr){
			getSettingActivitiesJBMData1(levelWiseSBLeaderArr[i],divId);
			$("[overview-level-new]").trigger("click");
		}	
	
	}, 2000);
}

function getSettingActivitiesJBMData1(locationId,divId){
	$("#"+locationId+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
		fromDate :'',
	    toDate :'',
		activityId:38,
		locationScopeId:locationTypeId,
		locationValue:locationValue
	}	
	$.ajax({
	 type: "POST",
	 url: "getLocationWiseJBDataAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildActivityEventdata1(result,locationId,divId);
		}else{
			$("#"+locationId+divId).html('NO DATA AVAILABLE');
		}
	});
}
function buildActivityEventdata1(result,locationId,divId){
	var tableView='';
	tableView+='<div class="table-responsive">';
		tableView+='<table class="table table-bordered dataTable1leaderandlocarionwise'+locationId+' table_custom_data" style="width:100%;border:1px solid lightgrey">';
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
					tableView+='<th rowspan="2">Total Villages Covered</th>';
					for(var i in result[0].questionList){
						if(result[0].questionList[i].questionId==33){
							tableView+='<th colspan="'+result[0].questionList[i].optionList.length+'">'+result[0].questionList[i].questionName+'</th>';
						}
					}
					tableView+='<th rowspan ="2">Images</th>';
				tableView+='</tr>';
				tableView+='<tr>';
					for(var i in result[0].questionList){
						if(result[0].questionList[i].questionId==33){
							for(var j in result[0].questionList[i].optionList){
								tableView+='<th>'+result[0].questionList[i].optionList[j].optionName+'</th>';
							}											
						}
					}
					tableView+='</tr>';
				tableView+='</tr>';
			tableView+='</thead>';	
			tableView+='<tbody>';
			for(var i in result){
				tableView+='<tr>';
					if(locationId == 'constituency'){
						tableView+='<td>'+result[i].districtName+'</td>';
					}
					tableView+='<td>'+result[i].locationName+'</td>';
					tableView+='<td align="center">'+result[i].conductedCount+'</td>';
					for(var j in result[i].questionList){
						if(result[i].questionList[j].questionId==33){
							for(var k in result[i].questionList[j].optionList){
								tableView+='<th>'+result[i].questionList[j].optionList[k].count+'</th>';
							}											
						}
					}
					if(result[i].imageCount !=null){
						tableView+='<td>'+result[i].imageCount+'</td>';
					}else{
						tableView+='<td>0</td>';
					}
				tableView+='</tr>';
			}
			tableView+='</tbody>';
		tableView+='</table>';
	tableView+='</div>';
	
	$("#"+locationId+divId).html(tableView);
	 $(".dataTable1leaderandlocarionwise"+locationId).dataTable({  
		 "aaSorting": [[ 1, "desc" ]], 
		"iDisplayLength" : 10,
		"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
	 }); 
	
}