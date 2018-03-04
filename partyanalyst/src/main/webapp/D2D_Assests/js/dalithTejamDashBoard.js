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
var globalcategoryId=1156;
var globalPartyIds=[];
var globalStateId=1;
var globalActivityMemberId=44;
var globalUserTypeId=2;
var globalEndIndex=6;
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
		'Last 3 Months': [moment().subtract(3, 'month'), moment()],
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
		$("#blockId").html('Last 7 Days');
	}
  $('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
	  var name =picker.chosenLabel;
		glStartDate = picker.startDate.format('DD-MM-YYYY');
		glEndDate = picker.endDate.format('DD-MM-YYYY');
		$("#newsDateId").html(name+"&nbsp;&nbsp;("+glStartDate+"/"+glEndDate+")");
		$("#blockId").html(name+"&nbsp;&nbsp;("+glStartDate+"/"+glEndDate+")");
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
	levelWiseLeaderData("leaderWise");
	getDateWiseCount();
}
function getDateWiseCount(){
	$("#todayVisitedCount").html(spinner_count);
	$("#todayLoanAppliedCount").html(spinner_count);
	$("#todayRegistration").html(spinner_count);
	var jsObj={
    	levelId			:2,
		levelValue		:1,
		startDateStr	:glStartDate,
		endDateStr  	:glEndDate,
		activityId		:activityId
    }
	 $.ajax({
      type : 'POST',
      url : 'getDayWiseCountFordalithatejamAction.action',
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result !=null && result.length>0){
			buildResultForToday(result);
		}else{
			$("#imagesSliderDivId").html("NO DATA AVAILABLE");
		}
		
	});
}
function buildResultForToday(result){
	if(result[0].todayvisted !=null && result[0].todayvisted>0){
		$("#todayVisitedCount").html(result[0].todayvisted);
	}else{
		$("#todayVisitedCount").html(' - ');
	}
	if(result[0].todayLoanApplied !=null && result[0].todayLoanApplied>0){
		$("#todayLoanAppliedCount").html(result[0].todayLoanApplied);
	}else{
		$("#todayLoanAppliedCount").html(' - ');
	}
	
	if(result[0].todayRegistred !=null && result[0].todayRegistred>0){
		$("#todayRegistration").html(result[0].todayRegistred);
	}else{
		$("#todayRegistration").html(' - ');
	}
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
	$("#imagesCovered").html(spinner_count);
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
	
	/* if(result[0].totalImages !=null && result[0].totalImages>0){
		$("#imagesCovered").html("<b>"+result[0].imagesCovered+"/"+result[0].totalImages+"</b>");
	}else{
		$("#imagesCovered").html(' - ');
	} */
	if(result[0].totalImages != null && result[0].totalImages>0)
		$("#imagesCovered").html('<u><b style="cursor:pointer;" class="getImageCls"  type="overAll"  activity_name="Dhalita Tejam Telugu Dhesam" attr_activity_scopeid="'+result[0].tdpcadreId+'" attr_level_id="'+result[0].id+'" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="Click here to view images." >'+result[0].imagesCovered+'/'+result[0].totalImages+'</b></u>');
	else
		$("#imagesCovered").html('<b style="cursor:pointer;" class="" attr_activity_scopeid="'+result[0].tdpcadreId+'" attr_level_id="'+result[i].id+'" data-toggle="tooltip" data-placement="top"  >'+result[0].imagesCovered+' <small><span class="text-success">'+result[0].totalImages+' images covered</span></small></b>');
							
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
	
	
}
function getActivityOverAllSummaryforDelithatejam(){
	
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
		str+='<ul class="list-inline slider-nav1">';
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
		$('.slider-nav1').slick({
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
		url: wurl+"/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysis/"+glStartDate+"/"+glEndDate+"/1156"
		//url: "http://localhost:8446/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysis/"+glStartDate+"/"+glEndDate+"/1156"
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
								if(result.coreDashBoardVOList[i].count !=null && result.coreDashBoardVOList[i].count>0){
									str+='<p><a class="partyMainEditionCls" attr_editiontype="1" attr_type="tdp" attr_benefitid ="0" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].count+'</a></p>';
								}else{
									str+='<p> - </p>';
								}
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								if(result.coreDashBoardVOList[i].positivePerc !=null && result.coreDashBoardVOList[i].positivePerc>0 && result.coreDashBoardVOList[i].positiveCountMain >0){
									
									str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_type="tdp"  attr_benefitid ="1" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].positiveCountMain+'</a></span>';
									str+='<small id="" class="text-success"> '+result.coreDashBoardVOList[i].positivePerc+' %</small>';
								}else{
									str+='<span> - </span>';
								}
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								if(result.coreDashBoardVOList[i].negativePerc !=null && result.coreDashBoardVOList[i].negativePerc>0 && result.coreDashBoardVOList[i].negativCountMain >0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="2" attr_type="tdp"  attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].negativCountMain+'</a></span>';
									str+='<small id="" class="text-danger"> '+result.coreDashBoardVOList[i].negativePerc+' %</small>';
								}else{
									str+='<span> - </span>';
								}
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">DIST EDITION</h5>';
								if(result.coreDashBoardVOList[i].totalCount != null && result.coreDashBoardVOList[i].totalCount >0){
									str+='<p><a class="partyMainEditionCls" attr_editiontype="2" attr_type="tdp"  attr_benefitid ="0" attr_benefitid ="0" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].totalCount+'</a></p>';
								}else{
									str+='<span> - </span>';
								}
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								if(result.coreDashBoardVOList[i].positiveCountDist != null && result.coreDashBoardVOList[i].positiveCountDist >0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="2" attr_type="tdp"  attr_benefitid ="1" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].positiveCountDist+'</a></span>';
									str+='<small class="text-success" id=""> '+result.coreDashBoardVOList[i].positiveDistPerc+' %</small>';
								}else{
									str+='<span> - </span>';
								}
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								if(result.coreDashBoardVOList[i].negativCountDist != null && result.coreDashBoardVOList[i].negativCountDist >0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="2" attr_benefitid ="2" attr_type="tdp" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].negativCountDist+'</a></span>';
									str+='<small class="text-danger" id=""> '+result.coreDashBoardVOList[i].negativeDistPerc+' %</small>';
								}else{
									str+='<span> - </span>';
								}
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
				
				
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-6">';
			str+='<div class="yash_color_news">';
				str+='<h4 class="font_weight">Other Party</h4>';
				var totalmainOtherParty=0;
				var totalmainOtherPartyPosCount=0;
				var totalmainOtherPartyNegCount=0;
				var totalDistOtherPartyCount=0;
				var totalDistOtherPartyPosCount=0;
				var totalDistOtherPartyNegCount=0;
				
				var otherMediaMainPostivePercentage = 0;
				var otherMediaMainNegativePercentage = 0;
				
				var otherMediaDistrictPostivePercentage = 0;
				var otherMediaDistrictNegativePercentage = 0;
				
				for(var i in result.coreDashBoardVOList1){
					
					
					globalPartyIds.push(result.coreDashBoardVOList1[i].organizationId);
					totalmainOtherParty = totalmainOtherParty+result.coreDashBoardVOList1[i].count;
					totalmainOtherPartyPosCount = totalmainOtherPartyPosCount+result.coreDashBoardVOList1[i].positiveCountMain;
					totalmainOtherPartyNegCount = totalmainOtherPartyNegCount+result.coreDashBoardVOList1[i].positiveCountMain;
					
					
					totalDistOtherPartyCount = totalDistOtherPartyCount+result.coreDashBoardVOList1[i].totalCount;
					totalDistOtherPartyPosCount = totalDistOtherPartyPosCount+result.coreDashBoardVOList1[i].positiveCountDist;
					totalDistOtherPartyNegCount = totalDistOtherPartyNegCount+result.coreDashBoardVOList1[i].negativCountDist;
					
					otherMediaMainPostivePercentage = parseFloat(totalmainOtherPartyPosCount/totalmainOtherParty).toFixed(2)+"%";
					otherMediaMainNegativePercentage = parseFloat(totalmainOtherPartyNegCount/totalmainOtherParty).toFixed(2)+"%";
					
					otherMediaDistrictPostivePercentage = parseFloat(totalDistOtherPartyPosCount/totalDistOtherPartyCount).toFixed(2)+"%";
					otherMediaDistrictNegativePercentage = parseFloat(totalDistOtherPartyNegCount/totalDistOtherPartyCount).toFixed(2)+"%";
				}	
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">MAIN EDITION</h5>';
								if(totalmainOtherParty != null && totalmainOtherParty >0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_type="other" attr_benefitid ="0" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+totalmainOtherParty+'</a></span>';
								}else{
									str+='<span> - </span>';
								}
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								if(totalmainOtherPartyPosCount !=null && totalmainOtherPartyPosCount>0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="1"  attr_benefitid ="1" attr_partyids="'+globalPartyIds+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+totalmainOtherPartyPosCount+'</a></span>';
									str+='<small class="text-success"> '+otherMediaMainPostivePercentage+' %</small>';
								}else{
									str+='<span> - </span>';
								}
								
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								if(totalmainOtherPartyNegCount !=null && totalmainOtherPartyNegCount>0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_type="other" attr_benefitid ="2" attr_partyids="'+globalPartyIds+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+totalmainOtherPartyNegCount+'</a></span>';
											str+='<small class="text-danger" id=""> '+otherMediaMainNegativePercentage+' %</small>';
								}else{
									str+='<span> - </span>';
								}
								
								
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">DIST EDITION</h5>';
								if(totalDistOtherPartyCount != null && totalDistOtherPartyCount >0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="2"  attr_type="other" attr_benefitid ="0" attr_partyids="'+globalPartyIds+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+totalDistOtherPartyCount+'</a></span>';
								}else{
									str+='<span> - </span>';
								}
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">POSITIVE</h5>';
								if(totalDistOtherPartyPosCount !=null && totalDistOtherPartyPosCount>0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="2"  attr_type="other" attr_benefitid ="0" attr_partyids="'+globalPartyIds+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+totalDistOtherPartyPosCount+'</a></span>';
									str+='<small class="text-danger" id=""> '+otherMediaDistrictPostivePercentage+' %</small>';
								}else{
									str+='<span> - </span>';
								}
								
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<div class="dark_yash_box">';
								str+='<h5 class="font_weight">NEGATIVE</h5>';
								if(totalDistOtherPartyPosCount !=null && totalDistOtherPartyPosCount>0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="2"  attr_type="other" attr_benefitid ="2" attr_partyids="'+globalPartyIds+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+totalDistOtherPartyPosCount+'</a></span>';
									str+='<small class="text-danger" id=""> '+otherMediaDistrictNegativePercentage+' %</small>';
									
								}else{
									str+='<span class="red-text-news m_top10" > - </span>';
								}
								
							str+='</div>';
						str+='</div>';
					str+='</div>';
				
				
				
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
		collapse+='<div class="col-sm-12 m_top10">';
		collapse+='<div class="panel-group" id="accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-gray" style="box-shadow:none;">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBArr[i]+'">';
				
				//collapse+='<a role="button" class="panelCollapseIcon1 '+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"   data-toggle="collapse" data-parent="#accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" >';
				
					 if(i == 0)
					{
						collapse+='<a role="button" class="panelCollapseIcon1 '+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"   data-toggle="collapse" data-parent="#accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" >';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon1 collapsed '+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  overview-level-new='+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+' data-toggle="collapse" data-parent="#accordion'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" >';
					} 
					collapse+='<h5 class="text-capital" style="color:#000;">'+levelWiseSBArr[i]+' level overview</h5>';
						
					collapse+='</a>';
				collapse+='</div>';
				
				//collapse+='<div id="collapse'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.toString().replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
				
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
			
			
	
	$("#levelWiseOverviewId").html(collapse);
	 setTimeout(function(){ 
		for(var i in levelWiseSBArr){
			if(levelWiseSBArr[i] != "district"){
				$("."+divId+''+levelWiseSBArr[i]).trigger("click");
			}
			
			getSettingActivitiesJBMData(levelWiseSBArr[i],divId);
			
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
		fromDate :glStartDate,
	    toDate :glEndDate,
		activityId:divId,
		locationScopeId:locationTypeId,
		locationValue:locationValue
	}	
	$.ajax({
	 type: "POST",
	 url: "getDalithTejamLocationWiseDataAction.action",
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
					if(locationId == 'constituency'){
						tableView+='<th rowspan ="2">ఈ కార్యక్రమం లో ఎమ్మెల్యే/ఇంచార్జి హాజరు అయిన  రోజులు </th>';
					}
					if(locationId != 'parliament'){
						tableView+='<th rowspan ="2">News CoverageDays </th>';
					}
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
					if(locationId == 'constituency'){
						if( result[i].count !=null && result[i].count >0){
							tableView+='<td align="center">'+result[i].newsCount+'</td>';
						}else{
							tableView+='<td align="center">-</td>';
						}
					}
					if(locationId != 'parliament'){
						if( result[i].newsCount !=null && result[i].newsCount >0){
							tableView+='<td align="center">'+result[i].newsCount+'</td>';
						}else{
							tableView+='<td align="center">-</td>';
						}
						
					}
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
	if(locationId != "district"){
		 $("."+divId+''+locationId).trigger("click");
	 }

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


function levelWiseLeaderData(divId)
{
	var levelWiseSBLeaderArr=['district','parliament','constituency'];
	
	
	var collapse='';
	for(var i in levelWiseSBLeaderArr)
	{
		collapse+='<div class="col-sm-12 m_top10">';
		collapse+='<div class="panel-group" id="accordionleader'+divId+''+levelWiseSBLeaderArr[i]+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-gray" style="box-shadow:none;">';
			
				collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBLeaderArr[i]+'">';
				
				//collapse+='<a role="button" class="panelCollapseIcon1 '+divId+''+levelWiseSBLeaderArr[i]+'"   data-toggle="collapse" data-parent="#accordionleader'+divId+''+levelWiseSBLeaderArr[i]+'" href="#collapse'+divId+''+levelWiseSBLeaderArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" >';
				
					if(i == 0)
					{
						collapse+='<a role="button" class="panelCollapseIcon1 '+divId+''+levelWiseSBLeaderArr[i]+'"   data-toggle="collapse" data-parent="#accordionleader'+divId+''+levelWiseSBLeaderArr[i]+'" href="#collapse'+divId+''+levelWiseSBLeaderArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" >';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon1 collapsed '+divId+''+levelWiseSBLeaderArr[i]+'"  overview-level-new='+divId+''+levelWiseSBLeaderArr[i]+' data-toggle="collapse" data-parent="#accordionleader'+divId+''+levelWiseSBLeaderArr[i]+'" href="#collapse'+divId+''+levelWiseSBLeaderArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" >';
					} 
					collapse+='<h5 class="text-capital" style="color:#000;">Leaders Participated- '+levelWiseSBLeaderArr[i]+' level overview</h5>';
						
					collapse+='</a>';
				collapse+='</div>';
				//collapse+='<div id="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId+''+levelWiseSBLeaderArr[i]+'">';
				if(i == 0)
				{
					collapse+='<div id="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId+''+levelWiseSBLeaderArr[i]+'">';
				}else{
					collapse+='<div id="collapse'+divId+''+levelWiseSBLeaderArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId+''+levelWiseSBLeaderArr[i]+'">';
				} 
				
				
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
			if(levelWiseSBLeaderArr[i] !="district"){
				$("."+divId+''+levelWiseSBLeaderArr[i]).trigger("click");
			}
			
			getSettingActivitiesJBMData1(levelWiseSBLeaderArr[i],divId);
			
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
		fromDate :glStartDate,
	    toDate :glEndDate,
		activityId:38,
		locationScopeId:locationTypeId,
		locationValue:locationValue
	}	
	$.ajax({
	 type: "POST",
	 url: "getDalithTejamLocationWiseDataAction.action",
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
		"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]],
		"scrollX": true,
			"scrollX": true,
			"scrollCollapse": true,
			"fixedColumns":   {
				"leftColumns": 1,
			}
				
	 }); 
	 if(locationId != "district"){
		 $("."+divId+''+locationId).trigger("click");
	 }
	 
	
}

$(document).on("click",".partyMainEditionCls",function(){
	var editionType="Main";var attrPartyids =[];
	var type =$(this).attr('attr_type');
	var attrBenifitId=$(this).attr('attr_benefitid');
	var attrEditionType=$(this).attr('attr_editiontype');
	if(type.toString()=='tdp'){
		attrPartyids.push($(this).attr('attr_partyids'));
	}else{
		attrPartyids=globalPartyIds
	}
	var attrCategoryid=$(this).attr('attr_categoryid');
	
	globalPartyId = attrPartyids;
	globalBenefitId = attrBenifitId;
	globalChannelId=attrEditionType;
	getEditionTypeWisePartiesAnalysisForArticles(editionType,attrCategoryid,attrPartyids,attrBenifitId,attrEditionType);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
	$("#prajaSankalpaHeadingId").html("Location Wise Articles");
});

function getEditionTypeWisePartiesAnalysisForArticles(editionType,attrCategoryid,attrPartyids,attrBenifitId,attrEditionType){
$("#popImgDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$.ajax({	
       url: wurl+"/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysisForArticles/"+glStartDate+"/"+glEndDate+"/"+attrCategoryid+"/"+attrPartyids+"/"+attrBenifitId+"/"+attrEditionType
		//url: "http://localhost:8446/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysisForArticles/"+glStartDate+"/"+glEndDate+"/"+attrCategoryid+"/"+attrPartyids+"/"+attrBenifitId+"/"+attrEditionType
	}).then(function(result){
		$("#popImgDiv").html("");
		if(result != null && result.length >0){
		buildingPmTable1(result);
		}
	});
	
}

function buildingPmTable1(jsonObject){
	var str=" ";
		if(jsonObject[0].stateVoList !=null && jsonObject[0].stateVoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading' style='padding-top: 5px; padding-bottom: 5px;'>";
				str+="<h3 class='panel-title'>State Level</h3>";
			str+="</div>";
			str+="<div class='panel-body' style='background-color: rgb(255, 255, 255) ! important; padding-bottom: 8px; padding-top: 8px;'>";
		
				
				str+='<ul class="list-inline memberMeetingCls">';
					for(var i in jsonObject[0].stateVoList)
					{
							str+="<li class='text-capital' style='margin-left:5px;'>";
							str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_count="+jsonObject[0].stateVoList[i].count+" attr_region_scopeid="+jsonObject[0].stateVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].stateVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].stateVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].stateVoList[i].locationName+"</span></h5>";
							str+="</li>";
					}
				str+='</ul>';
		str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraStateTableId').html(str);
		}
		if(jsonObject[0].districtVoList !=null && jsonObject[0].districtVoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading' style='padding-top: 5px; padding-bottom: 5px;'>";
				str+="<h3 class='panel-title'>District Level</h3>";
			str+="</div>";
			str+="<div class='panel-body' style='background-color: rgb(255, 255, 255) ! important; padding-bottom: 8px; padding-top: 8px;'>";
			
			str+='<ul class="list-inline memberMeetingCls">';
					for(var i in jsonObject[0].districtVoList)
					{
							str+="<li class='text-capital' style='margin-left:5px;'>";
							str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_count="+jsonObject[0].districtVoList[i].count+" attr_region_scopeid="+jsonObject[0].districtVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].districtVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].districtVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].districtVoList[i].locationName+"</span></h5>";
							str+="</li>";
					}
			str+='</ul>';
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraDistrictTableId').html(str);
		}
		if(jsonObject[0].panchayatVoList !=null && jsonObject[0].panchayatVoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading' style='padding-top: 5px; padding-bottom: 5px;'>";
				str+="<h3 class='panel-title'>Panchayat Level</h3>";
			str+="</div>";
			str+="<div class='panel-body' style='background-color: rgb(255, 255, 255) ! important; padding-bottom: 8px; padding-top: 8px;'>";
			
			str+='<ul class="list-inline memberMeetingCls">';
					for(var i in jsonObject[0].panchayatVoList)
					{
						str+="<li class='text-capital' style='margin-left:5px;'>";
						str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_count="+jsonObject[0].panchayatVoList[i].count+" attr_region_scopeid="+jsonObject[0].panchayatVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].panchayatVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].panchayatVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].panchayatVoList[i].locationName+"</span></h5>";
						str+="</li>";
					}
			str+='</ul>';
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraPanchayatTableId').html(str);
		}
		if(jsonObject[0].mandalVoList !=null && jsonObject[0].mandalVoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading' style='padding-top: 5px; padding-bottom: 5px;'>";
				str+="<h3 class='panel-title'>Mandal Level</h3>";
			str+="</div>";
			str+="<div class='panel-body' style='background-color: rgb(255, 255, 255) ! important; padding-bottom: 8px; padding-top: 8px;'>";
			
			str+='<ul class="list-inline memberMeetingCls">';
					for(var i in jsonObject[0].mandalVoList)
					{
						str+="<li class='text-capital' style='margin-left:5px;'>";
						str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_count="+jsonObject[0].mandalVoList[i].count+" attr_region_scopeid="+jsonObject[0].mandalVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].mandalVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].mandalVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].mandalVoList[i].locationName+"</span></h5>";
						str+="</li>";
					}
			str+='</ul>';
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraMandalTableId').html(str);
		}
		if(jsonObject[0].constoList !=null && jsonObject[0].constoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading' style='padding-top: 5px; padding-bottom: 5px;'>";
				str+="<h3 class='panel-title'>Constituency Level</h3>";
			str+="</div>";
			str+="<div class='panel-body' style='background-color: rgb(255, 255, 255) ! important; padding-bottom: 8px; padding-top: 8px;'>";
			
			str+='<ul class="list-inline memberMeetingCls">';
					for(var i in jsonObject[0].constoList)
					{
						str+="<li class='text-capital' style='margin-left:5px;'>";
						str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_count="+jsonObject[0].constoList[i].count+" attr_region_scopeid="+jsonObject[0].constoList[i].scopeId+" attr_scopeValue="+jsonObject[0].constoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].constoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].constoList[i].locationName+"</span></h5>";
						str+="</li>";
					}
			str+='</ul>';
			
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraConstituencyTableId').html(str);
		}
		if(jsonObject[0].corpGmcVoList !=null && jsonObject[0].corpGmcVoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading' style='padding-top: 5px; padding-bottom: 5px;'>";
				str+="<h3 class='panel-title'>Corporation Level</h3>";
			str+="</div>";
			str+="<div class='panel-body' style='background-color: rgb(255, 255, 255) ! important; padding-bottom: 8px; padding-top: 8px;'>";
			
			str+='<ul class="list-inline memberMeetingCls">';
					for(var i in jsonObject[0].corpGmcVoList)
					{
						str+="<li class='text-capital' style='margin-left:5px;'>";
						str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_count="+jsonObject[0].corpGmcVoList[i].count+" attr_region_scopeid="+jsonObject[0].corpGmcVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].corpGmcVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].corpGmcVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].corpGmcVoList[i].locationName+"</span></h5>";
						str+="</li>";
					}
			str+='</ul>';
			
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraCorpTableId').html(str);
		}	
}

$(document).on("click",".articleWisePrintMediaCls",function(){
		var scopeId = $(this).attr("attr_region_scopeid"); 
		var scopeValue = $(this).attr("attr_scopeValue");
		getEditionTypeWisePartiesAnalysisForClick(globalPartyId,globalBenefitId,globalChannelId,scopeId,scopeValue
		,globalcategoryId,globalEndIndex,0);
		$('#articleDataID').modal('show');
		$("#articleId").html("All News Articles");
	
});

 function getEditionTypeWisePartiesAnalysisForClick(globalPartyId,globalBenefitId,globalChannelId,scopeId,scopeValue
		,globalcategoryId,globalEndIndex,globalstartIndex){
	$("#newspopImgDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysisForClick/"+glStartDate+"/"+glEndDate+"/"+globalcategoryId+"/"+globalstartIndex+"/"+globalEndIndex+"/"+globalPartyId+"/"+globalBenefitId+"/"+globalChannelId+"/"+scopeId+"/"+scopeValue
		
		//url: "http://localhost:8446/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysisForClick/"+glStartDate+"/"+glEndDate+"/"+globalcategoryId+"/"+globalstartIndex+"/"+globalEndIndex+"/"+globalPartyId+"/"+globalBenefitId+"/"+globalChannelId+"/"+scopeId+"/"+scopeValue
	}).then(function(result){
		buildArticlesByDateRangeWise(result,globalPartyId,globalBenefitId,globalChannelId,scopeId,scopeValue
		,globalcategoryId,globalEndIndex,globalstartIndex);
	});
}
function buildArticlesByDateRangeWise(results,globalPartyId,globalBenefitId,globalChannelId,scopeId,scopeValue
		,globalcategoryId,countByDate,globalStIndex){
		var str ='';
		
		if(results !=null && results.length>0){
			
			if(globalStIndex == 0){
				countByDate=results[0].totalArticlesCount;
			}
				//str+='<div class="row">';
				for(var i in results){
					var artclNmbr  = parseInt(globalStIndex) + parseInt(i);
					
						if(i == 0 || i%3 == 0){
							str+='<div class="row">';
						}
					
						str+='<div class="col-md-4 col-sm-3 widgets widget-hide autoWidthBlock" id="articlediv'+results[i].articleId+'">';
							str+='<div class="thumbnail thumbnail-widget" style="height:auto;">';
							if(results[i].imageURL !='images/NoImageUpd.png'){
								str+='<a class="viewArticleDetailsByAllArticlesPage" attr_articleNo="'+artclNmbr+'" attr_articleId='+results[i].articleId+' attr_paginCnt="'+countByDate+'" style="cursor:pointer;"><img src="http://mytdp.com/NewsReaderImages/'+results[i].imageURL+'" class="groupingimage" alt="image Not Available." style="width:311px;height:140px;"/></a>';
							}else{
								str+='<a class="viewArticleDetailsByAllArticlesPage" attr_articleNo="'+artclNmbr+'" attr_articleId='+results[i].articleId+' attr_paginCnt="'+countByDate+'" style="cursor:pointer;"><img src="http://mytdp.com/NewsReaderImages/'+results[i].imageURL+'" class="groupingimage" alt="image Not Available." style="width:311px;height:140px;"/></a>';
							}
							str+='<div class="caption">';
							 if(results[i].articleTitle == null || results[i].articleTitle.length>50){
								 str+='<p style="font-size:14px;">'+results[i].articleTitle.substring(0,50)+'...</p>';
							 }else{
								 str+='<p style="font-size:14px;">'+results[i].articleTitle+'</p>';
							 }
								
							
								 if(results[i].description == null || results[i].description == 0){
									str+='<p> - </p>';
								}else{
									if(results[i].description !=null && results[i].description.length>100){
									   str+='<p  style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="'+results[i].description+'" >'+results[i].description.substring(0,100)+'...</p>';
									}else{
										str+='<p>'+results[i].description+'</p>';
									}
								} 
								
							str+='</div>';
							
						str+='<div class="row" >';
						var scrollstr = results[i].articleId;
							
						if(results[i].subList !=null && results[i].subList.length>0){
							
							str+='<div class="scrolllengthDiv">';
							for(var j in results[i].subList){
								
								str+='<div class="row"  style="margin: auto;" >';
							
								if(results[i].subList[j].fromList !=null && results[i].subList[j].fromList.length>0){
									str+='<div class="col-xs-6" style="padding-right: 5px;">';
								   str+='<div class="col-xs-12 subBlock m_top10">';
										str+='<span><i>From</i></span>';
									  for(var l in results[i].subList[j].fromList){
										 
										   str+='<table class="table table-bordered tableGr">';
											str+='<tbody>';
											str+='<tr>';
											  str+='<td style="width:50%;">';
												  str+='<ul class="list-inline m_0">';
													
													 str+='<li><img class="img-circle" style="display: inline-block; width: 20px; height: 20px; " src="newCoreDashBoard/img/'+results[i].subList[j].fromList[l].organizationName+'.png" onerror="setDefaultImage1(this);"></li>';
													 
													 if(results[i].subList[j].fromList[l].organizationName == null || results[i].subList[j].fromList[l].organizationName == 0){
														  str+='<li class="text-muted" style="font-size: 12px;text-align:center;"> - </li>';
													 }else{
														 if(results[i].subList[j].fromList[l].organizationName != null && results[i].subList[j].fromList[l].organizationName.length>7){
															  str+='<li   data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].organizationName+'" class="text-muted " style="font-size: 12px;cursor:pointer;">'+results[i].subList[j].fromList[l].organizationName.substring(0,7)+'...</li>';
														 }else{
															 str+='<li class="text-muted" style="font-size: 12px;">'+results[i].subList[j].fromList[l].organizationName+'</li>';
														 }
														 
													 }
													 
												  str+='</ul>';
											  str+='</td>';
											  str+='<td style="width:50%;">';
												   str+='<ul class="list-inline m_0">';
													   str+='<li><img  src="images/'+results[i].subList[j].fromList[l].benefit+'.png" style="display: inline-block; font-size: 20px;width: 20px; height: 20px;"></i></li>';
													  if(results[i].subList[j].fromList[l].benefit == null || results[i].subList[j].fromList[l].benefit == 0){
														   str+='<li class="text-muted" style="font-size: 12px;text-align:center;"> - </li>';
													   }else{
														   str+='<li class="text-muted" style="font-size: 12px">'+results[i].subList[j].fromList[l].benefit+'</li>';
													   }
													   
												   str+='</ul>';
											   str+='</td>';
											str+='</tr>';
											str+='</tbody>';
										str+=' </table>';
										
										var FromcandidateName = results[i].subList[j].fromList[l].candidateName == null || results[i].subList[j].fromList[l].	candidateName == 0;
										   var Fromdesignation = results[i].subList[j].fromList[l].designation == null || results[i].subList[j].fromList[l].designation == 0
											if( FromcandidateName &&  Fromdesignation ){
												str+=' <div style="text-align:center;"><span > - </span></div> ';
											}else{
												if(FromcandidateName){
												 str+='<span > - </span>';
												}else{
													if(results[i].subList[j].fromList[l].candidateName != null && results[i].subList[j].fromList[l].candidateName.length>17){
														str+='<span  data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].candidateName+'"  style="font-size: 12px;cursor:pointer;">'+results[i].subList[j].fromList[l].candidateName.substring(0,17)+'...</span>';
													}else{
														str+='<span style="font-size:12px;">'+results[i].subList[j].fromList[l].candidateName+'</span>';
													}
													
												}
												
												str+=' <span > - </span> ';
												if(Fromdesignation){
													str+='<span >( - )</span>';
												}else{
													if(results[i].subList[j].fromList[l].designation != null && results[i].subList[j].fromList[l].designation.length>6){
														str+='<span data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].designation+'"  style="font-size: 12px;cursor:pointer;">('+results[i].subList[j].fromList[l].designation.substring(0,6)+'...)</span>';
													}else{
														str+='<span style="font-size:12px;">('+results[i].subList[j].fromList[l].designation+')</span>';
													}
													
												}
											}
											
										
										
										str+='<table class="table table-bordered tableGr">';
									  str+='<tbody>';
									   str+='<tr>';
										  str+='<td>';
										  if(results[i].subList[j].fromList[l].categories == null || results[i].subList[j].fromList[l].categories == 0){
											  str+='<p><span>Category : <span class="text-muted" style="text-align:center;"> - </span></p>';
										  }else{
											if(results[i].subList[j].fromList[l].categories !=null && results[i].subList[j].fromList[l].categories.length>21){
												  str+='<p><span>Category: &nbsp;&nbsp;<span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].categories+'"  style="cursor:pointer;">'+results[i].subList[j].fromList[l].categories.substring(0,21)+'...</span></p>';
											  }else{
												  str+='<p><span>Category :&nbsp; <span class="text-muted">'+results[i].subList[j].fromList[l].categories+'</span></p>';
											  }
											  
										  }
										  if(results[i].subList[j].fromList[l].newsType == null || results[i].subList[j].fromList[l].newsType == 0){
											  str+='<p><span>News Type: <span class="text-muted" style="text-align:center;"> - </span></p>';
										  }else{
											   if(results[i].subList[j].fromList[l].newsType !=null && results[i].subList[j].fromList[l].newsType.length>25){
												   str+='<p><span>News Type:&nbsp;&nbsp; <span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].newsType+'" style="cursor:pointer;">'+results[i].subList[j].fromList[l].newsType.substring(0,25)+'...</span></p>';
											   }else{
												   str+='<p><span>News Type: &nbsp;&nbsp;<span class="text-muted">'+results[i].subList[j].fromList[l].newsType+'</span></p>';
											   }
											  
										  }
										  
										   if( results[i].subList[j].fromList[l].newsType != null && results[i].subList[j].fromList[l].newsType == "Problems"){
											if(results[i].subList[j].fromList[l].newsRelated != null && $.trim(results[i].subList[j].fromList[l].newsRelated).length > 0){
											  str+='<p class="m_0">News Related : <span class="text-muted">'+results[i].subList[j].fromList[l].newsRelated+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">News Related : - </p>';	
											}
											if(results[i].subList[j].fromList[l].priority != null && $.trim(results[i].subList[j].fromList[l].priority).length > 0){
											  str+='<p class="m_0">Priority : <span class="text-muted">'+results[i].subList[j].fromList[l].priority+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">Priority : - </p>';	
											}
											if(results[i].subList[j].fromList[l].solution != null && $.trim(results[i].subList[j].fromList[l].solution).length > 0){
											  str+='<p class="m_0">Solution : <span class="text-muted">'+results[i].subList[j].fromList[l].solution+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">Solution : - </p>';	
											}
										}
										
										  if(results[i].subList[j].fromList[l].newsActivity == null || results[i].subList[j].fromList[l].newsActivity == 0){
											  str+='<p><span>News Activity: </span><span class="text-muted" style="text-align:center;"> - </span></p>';
										  }else{
											  if(results[i].subList[j].fromList[l].newsActivity !=null && results[i].subList[j].fromList[l].newsActivity.length>25){
												   str+='<p><span>News Activity: &nbsp;&nbsp;</span><span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].fromList[l].newsActivity+'" style="cursor:pointer;">'+results[i].subList[j].fromList[l].newsActivity.substring(0,25)+'...</span></p>';
											  }else{
												   str+='<p><span>News Activity: &nbsp;&nbsp;</span><span class="text-muted">'+results[i].subList[j].fromList[l].newsActivity+'</span></p>';
											  }
											 
										  }
										   str+='</td>';
										  str+='</tr>';
									  str+='</tbody>';
									  str+='</table>';
									 
										
									}
									str+='</div>';  
									str+='</div>';
								}
							if(results[i].subList[j].toList !=null && results[i].subList[j].toList.length>0){	
									str+='<div class="col-xs-6" style="padding-left: 5px;">';
										  str+='<div class="col-xs-12 subBlock m_top10" >';
											str+='<span><i>To</i></span>';
										
									 for(var k in results[i].subList[j].toList){
										 
										 str+='<table class="table table-bordered tableGr">';
												str+='<tbody>';
												str+='<tr>';
												  str+='<td style="width:50%;">';
													  str+='<ul class="list-inline m_0">';
														   str+='<li><img class="img-circle" style="display: inline-block; width: 20px; height: 20px;" src="newCoreDashBoard/img/'+results[i].subList[j].toList[k].organizationName+'.png" onerror="setDefaultImage1(this);"></li>';
														  if(results[i].subList[j].toList[k].organizationName != null && results[i].subList[j].toList[k].organizationName.length>7){
															  str+='<li   data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].organizationName+'" class="text-muted " style="font-size: 12px;cursor:pointer;">'+results[i].subList[j].toList[k].organizationName.substring(0,7)+'...</li>';
														 }else{
															 str+='<li class="text-muted" style="font-size: 12px;">'+results[i].subList[j].toList[k].organizationName+'</li>';
														 }
														  
													  str+='</ul>';
												  str+='</td>';
												  str+='<td style="width:50%;">';
													   str+='<ul class="list-inline m_0">';
														  str+='<li><img  src="images/'+ results[i].subList[j].toList[k].benefit+'.png" style="display: inline-block; font-size: 20px;width: 20px; height: 20px;"></i></li>';
														  if(results[i].subList[j].toList[k].benefit == null || results[i].subList[j].toList[k].benefit == 0){
															   str+='<li class="text-muted" style="font-size: 12px"> - </li>';
														  }else{
															   str+='<li class="text-muted" style="font-size: 12px">'+results[i].subList[j].toList[k].benefit+'</li>';
														  }
														  
													   str+='</ul>';
												   str+='</td>';
												str+='</tr>';
												str+='</tbody>';
											str+=' </table>';
						                   var tocandidateName = results[i].subList[j].toList[k].candidateName == null || results[i].subList[j].toList[k].	candidateName == 0;
										   var todesignation = results[i].subList[j].toList[k].designation == null || results[i].subList[j].toList[k].designation == 0
											if( tocandidateName &&  todesignation ){
												str+=' <div style = "text-align:center;"><span> - </span></div> ';
											}else{
												if(tocandidateName){
												 str+='<span> - </span>';
												}else{
													if(results[i].subList[j].toList[k].candidateName != null && results[i].subList[j].toList[k].candidateName.length>17){
														str+='<span  data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].candidateName+'"  style="font-size: 12px;cursor:pointer;">'+results[i].subList[j].toList[k].candidateName.substring(0,17)+'...</span>';
													}else{
														str+='<span style="font-size:12px;">'+results[i].subList[j].toList[k].candidateName+'</span>';
													}
												}
												
												str+=' <span> - </span> ';
												if(todesignation){
													str+='<span>( - )</span>';
												}else{
													if(results[i].subList[j].toList[k].designation != null && results[i].subList[j].toList[k].designation.length>6){
														str+='<span data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].designation+'"  style="font-size: 12px;cursor:pointer;">('+results[i].subList[j].toList[k].designation.substring(0,6)+'...)</span>';
													}else{
														str+='<span style="font-size:12px;">('+results[i].subList[j].toList[k].designation+')</span>';
													}
													
													
												}
											}
											
											
											str+='<table class="table table-bordered tableGr">';
										  str+='<tbody>';
										  str+='<tr>';
										  str+='<td>';
										  
										  if(results[i].subList[j].toList[k].categories == null || results[i].subList[j].toList[k].categories == 0){
											  str+='<p>Category:&nbsp;&nbsp;<span class="text-muted" style = "text-align:center;"> - </span></p>';
										  }else{
											  if(results[i].subList[j].toList[k].categories !=null && results[i].subList[j].toList[k].categories.length>21){
												  str+='<p>Category:&nbsp;&nbsp;<span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].categories+'"  style="cursor:pointer;">'+results[i].subList[j].toList[k].categories.substring(0,21)+'...</span></p>';
											  }else{
												  str+='<p>Category:&nbsp;&nbsp;<span class="text-muted">'+results[i].subList[j].toList[k].categories+'</span></p>';
											  }
											  
											 
										  }
										  
										 
										  
										 
										  
										  if(results[i].subList[j].toList[k].newsType == null || results[i].subList[j].toList[k].newsType == 0){
											  str+='<p>News Type:&nbsp;&nbsp;<span class="text-muted" style = "text-align:center;"> - </span></p>';
										  }else{
											  if(results[i].subList[j].toList[k].newsType !=null && results[i].subList[j].toList[k].newsType.length>25){
												   str+='<p>News Type :&nbsp;&nbsp;<span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].newsType+'" style="cursor:pointer;">'+results[i].subList[j].toList[k].newsType.substring(0,25)+'...</span></p>';
											   }else{
												    str+='<p>News Type:&nbsp;&nbsp;<span class="text-muted">'+results[i].subList[j].toList[k].newsType+'</span></p>';
											   }
											   
											 
										  }
										  if( results[i].subList[j].toList[k].newsType != null && results[i].subList[j].toList[k].newsType == "Problems"){
											if(results[i].subList[j].toList[k].newsRelated != null && $.trim(results[i].subList[j].toList[k].newsRelated).length > 0){
											  str+='<p class="m_0">News Related : <span class="text-muted">'+results[i].subList[j].toList[k].newsRelated+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">News Related : - </p>';	
											}
											if(results[i].subList[j].toList[k].priority != null && $.trim(results[i].subList[j].toList[k].priority).length > 0){
											  str+='<p class="m_0">Priority : <span class="text-muted">'+results[i].subList[j].toList[k].priority+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">Priority : - </p>';	
											}
											if(results[i].subList[j].toList[k].solution != null && $.trim(results[i].subList[j].toList[k].solution).length > 0){
											  str+='<p class="m_0">Solution : <span class="text-muted">'+results[i].subList[j].toList[k].solution+'</span> </p>';
											}else{ 
											  str+='<p class="m_0">Solution : - </p>';	
											}
										}
										
										  
										  if(results[i].subList[j].toList[k].newsActivity == null || results[i].subList[j].toList[k].newsActivity == 0){
											  str+='<p>News Activity :&nbsp;&nbsp;<span class="text-muted">- </span></p>';
										  }else{
											if(results[i].subList[j].toList[k].newsActivity !=null && results[i].subList[j].toList[k].newsActivity.length>25){
												   str+='<p>News Activity:&nbsp;&nbsp;<span class="text-muted" data-toggle="tooltip" data-placement="top" title="'+results[i].subList[j].toList[k].newsActivity+'" style="cursor:pointer;">'+results[i].subList[j].toList[k].newsActivity.substring(0,25)+'...</span></p>';
											  }else{
												   str+='<p>News Activity:&nbsp;&nbsp;<span class="text-muted">'+results[i].subList[j].toList[k].newsActivity+'</span></p>';
											  }
											  
											  
										  }
										  
										  str+='</td>';
										 
											
										  str+='</tr>';
										  str+='</tbody>';
										  str+='</table>';
										 
										
									}
									str+='</div>';  
									str+='</div>';
								}
								  str+='</div>';
							}
							 str+='</div>';
						}
						 str+='<div class="col-xs-12">';
							str+='<table class="table table-bordered m_top10 table-condensed">';
								str+='<tbody>';
									if(results[i].trackLocationScope == null || results[i].trackLocationScope == 0){
										str+='<p class="text-muted m_top10"><b>Article Tracking</b>  &nbsp;: &nbsp; <i class="glyphicon glyphicon-remove text-danger"></i></p>';
									}else{
										str+='<tr>';
										str+='<td colspan="2">';
										str+='<p class="text-muted m_top10" style="text-align: center;"><b>Article Tracking</b>  &nbsp;: &nbsp; <i class="glyphicon glyphicon-ok text-success"></i></p>';
										str+='</td>';
										str+='</tr>';
										str+='<tr>';
									str+='<td >';
											str+='<p class="text-muted"><b>Tracking Location Scope</b>  &nbsp;: &nbsp;'+results[i].trackLocationScope+'</p>';
									str+='</td>';
									str+='<td>';
											str+='<p class="text-muted"><b>Tracking Location</b> :&nbsp;'+results[i].trackLocationValue+'</p>';
									str+='</td>';
									str+='</tr>';
									str+='<tr>';
									str+='<td colspan="2">';
											str+='<p class="text-muted"><b>Label Name</b>  &nbsp;&nbsp;&nbsp;:&nbsp;'+results[i].trackLabelName+'</p>';
									str+='</td>';
									str+='</tr>';
										
									}
										
									
									
								str+='</tbody>';
						  str+='</table>';
						str+='</div>';
						 str+='<div class="col-xs-12">';
							str+='<table class="table table-condensed m_top10" style="border:1px solid #ddd;">';
								str+='<tbody>';
									str+='<tr>';
										 if(results[i].important == 'Y'){
											 str+='<td>Is Important : &nbsp;&nbsp;Yes</label></td>';
										 }else{
											 str+='<td>Is Important  : &nbsp;&nbsp;No</label></td>';
										 }
										
										 if(results[i].actionable == 'Y'){
											  str+='<td>Actionable : &nbsp;&nbsp;Yes</label></td>';
										 }else{
											  str+='<td>Actionable : &nbsp;&nbsp;No</label></td>';
										 }
										 
										 if(results[i].newsBulliten == 'Y'){
											 str+='<td>News Bulletin : &nbsp;&nbsp;Yes</label></td>';
										 }else{
											 str+='<td>News Bulletin : &nbsp;&nbsp;No</label></td>';
										 }
									str+='</tr>';
						  
								str+='</tbody>';
						  str+='</table>';
						str+='</div>';
						
						  str+='<div class="col-xs-12">';
							str+='<table class="table table-bordered m_top10">';
								str+='<tbody>';
									str+='<tr>';
										str+='<td colspan="3"><p class="text-muted">'+results[i].editionName+'&nbsp;&nbsp; (Page No:'+results[i].pageNo+');&nbsp; '+results[i].articleInsertedTime+'</p></td>';
									str+='</tr>';
						  
								str+='</tbody>';
						  str+='</table>';
						str+='</div>';
						 str+='<div class="col-xs-12 m_top10">';
						
					 	 if(results[i].groupedArtcls=="N"){
							
							str+='<i class="icon-article-group pull-right" style="cursor:default;padding-left:5px;color:#ccc;" data-toggle="group" data-placement="top" title="Grouped Article"></i>';
						}else{
							str+='<i class="icon-article-group pull-right" style="cursor:default;padding-left:5px;color:#FC5AB8;font-weight: bold;" data-toggle="group" data-placement="top" title="Grouped Article"></i>';
						}
						if(results[i].lnkdArtcls=='N'){
										
							str+='<i class="icon-linked-articles pull-right" style="cursor:default;padding-left:5px;color:#ccc;" data-toggle="group" data-placement="top" title="Linked Article"></i>';
						}else{
							str+='<i class="icon-linked-articles pull-right" style="cursor:default;padding-left:5px;color:red;" data-toggle="group" data-placement="top" title="Linked Article"></i>';
						}
						if(results[i].trackLabelName == null || results[i].trackLabelName == 0){
										
							str+='<i class="icon-track-articles pull-right" style="cursor:default;padding-left:5px;color:#ccc;" data-toggle="group" data-placement="top" title="Tracked Article"></i>';
						}else{
							str+='<i class="icon-track-articles pull-right" style="cursor:default;padding-left:5px;color:green;" data-toggle="group" data-placement="top" title="Tracked Article"></i>';
						}
					  str+='</div>';
					  
					 
								  
						 str+='</div>';
					str+='</div>';
				str+='</div>';
			//str+='</div>';
			 
				if(i == 2 || i%3 == 2){
						str+='</div>';
					}
	
			
			}
			if(globalStIndex == 0 && countByDate > 6){
				$(".paginationId").pagination({
					items: countByDate,
					itemsOnPage: 6,
					cssStyle: 'light-theme',
					hrefTextPrefix: '#pages-',
					onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*6;
						
						/* if(globalCallFrom == "fblk"){
							getArticlesOfNewsBasicCounts(num);
						}else if(globalCallFrom == "dpdepok"){
							getArticlesForPartyDetailedDistEdiPartiesOverView(num);
						}else if(globalCallFrom == "dpnta"){
							getArticlesForDetailedPartyNewsTypeAnalysisOverView(num);
						}else if(globalCallFrom == "govdepwisedistoverview"){
							getArticlesForgetDetailedGovtDepartmentWiseDistrictsOverview(num);
						}else if(globalCallFrom == "detailgovtimmedproblems"){
							getDetailedGovtOverAllAnalysisOfActionImmediatelyProblemsArticles(num);
						}else if(globalCallFrom == "prajaSankalpa"){
							if(globaltype == "OverAllprintMedia"){
								getEditionTypeWisePartiesAnalysisForClick(num);
							}else if(globaltype == "PartyprintMedia"){
								getPublicationWisePartiesAnalysisForClick(num);
							}else if(globaltype == "PublicationprintMedia"){
								getDistrictWisePartyOverViewForClick(num);
							}
						}else{ */
							getEditionTypeWisePartiesAnalysisForClick(globalPartyId,globalBenefitId,globalChannelId,scopeId,scopeValue,globalcategoryId,globalEndIndex,num);
						//}
					}
					
				});
			}
				str+='</div>';
			
		}else{
			$(".paginationId").html("");
				
				str+='<div class="col-md-5 widgets widget-hide" style="margin-left:200px;color:red;"> <h4> NO ARTICLES AVAILABLE WITH SEARCHED FILTERS </h4> </div>';
		}
		
		$("#newspopImgDiv").html(str);
		setTimeout(function () {
		$(".scrolllengthDiv").each(function(){
			$('.scrolllengthDiv').wrapInner('<div class="scrollable" />');
			var scrolllengthDiv = $(this).find("tr").length;
			
			if(scrolllengthDiv >= 8){
				$(this).find('.scrollable').slimscroll({
					height: '250px',
					alwaysVisible: true
				});
			}else{
				$('.scrolllengthDiv').css("height","auto");
			}
			
		});
		}, 1000);
		$('[data-toggle="tooltip"]').tooltip();
		
	}
	
function setDefaultImage1(img)
{

	img.onerror = "";
	img.src = "newCoreDashBoard/img/NoImageUpd.png";
	return true;
}


var globallocationScope;
var globalPopupresult = "";
var globlbuildType="dayswise";
var isBuildDate=false;
$(document).on("click",".getImageCls",function(){

	$("#myModalImageId").modal("show");
	var attr_activity_scopeid = $(this).attr('attr_activity_scopeid');
	var activityLevelId = $(this).attr('attr_level_id');
	var activity_name = $(this).attr('activity_name');
	globlbuildType =  $(this).attr('type');
	$("#hiddenActivityScopeId").val(attr_activity_scopeid);
	$("#hiddenActivityLevelId").val(activityLevelId);
	
	if(activity_name != null && activity_name.length>0)
		isBuildDate = false;
	
	if(!isBuildDate){
		isBuildDate = true;
		isWeeksBuild = false;
			$("#buildPoupupImage").html('');
		if(activity_name != null && activity_name.length>0){
		   $('#myModalLabelId').html(activity_name.replace("\'","").replace("\'",""));		
			//getDistrictNames($(this).attr('activity_name').replace("\'","").replace("\'",""),'','');
		}else{
			//getDistrictNames($(this).attr('activity_name').replace("\'","").replace("\'",""),'','');
		}
				var str='';
					str+='<div class="row">';
						str+='<div class="col-md-9">';
							str+='<nav class="navbar navbar-default navbarCollapseCustom">';
								str+='<div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">';
								
								 str+='<div class="row"  style="margin-top:5px;margin-bottom:5px;margin-left: 600px;">';
									 str+='<select id="daysListId">';
									 str+='</select>';
								 str+='</div>';
								 
								  str+='<div id="popupDaysDiv1" ></div>';
								 
								str+='</div>';
							str+='</nav>';
							str+='<div class=" pad_10" id="popupImages">';
								
							str+='</div>';
							str+=' <div id="paginationDivId"></div>';
						str+='</div>';
						str+='<div class="col-md-3" style="box-shadow:0 2px 10px 0 rgba(0, 0, 0, 0.35);padding:0px">';
							str+='<div id="districtsUlId"></div>';
						str+='</div>';
					str+='</div>';

				$("#buildPoupupImage").html(str);
	}
	
globalActivityScope = attr_activity_scopeid;
getEventDocumentForPopup("district",1,0,0,'',attr_activity_scopeid,"state",1,"firstClick",activity_name);
	 
});
$(document).on("change","#daysListId",function(){
	
	var weekDays = $(this).val();
	var datesArr = [];
	if(weekDays != null){
		datesArr = weekDays.split(' to ');
	}
	$("#paginationDivId").html('');
	//alert(666);
	if(weekDays == 0){
		getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,'',globalActivityScope,globallocationScope,globallocationValue,"","","");
		getTempAvailablDates(globallocationScope,globallocationValue,0,'',globalActivityScope,"","");
		getDistrictNames('',"","");	
	}else{
		if(datesArr != null && datesArr.length==2){
			getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,'',globalActivityScope,globallocationScope,globallocationValue,"",datesArr[0].trim(),datesArr[1].trim());
			getTempAvailablDates(globallocationScope,globallocationValue,0,'',globalActivityScope,datesArr[0].trim(),datesArr[1].trim());
			getDistrictNames('',datesArr[0].trim(),datesArr[1].trim());	
		}else{
			getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,'',globalActivityScope,globallocationScope,globallocationValue,"",'','');
			getTempAvailablDates(globallocationScope,globallocationValue,0,'',globalActivityScope,'','');
			getDistrictNames('','','');	
		}
	}
	
});
$(document).on('click','.dayssCls',function(){
	 $(".dayssCls").removeClass("active" )
	 $(this).addClass("active");
	 var day = $(this).attr("attr");
	 var locationScope = $(this).attr("locationScope");
	 var locationScopeValue = $(this).attr("locationScopeValue");
	 var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
	 var path = $(this).attr("path");
	 var date= $(this).attr("dateValue");
	 
	 var date = date;
	 var newdate = date.split("-").reverse().join("-");
	// alert(888);
		getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"");
		//getTempAvailablDates(GlobalPopupScope,GlobalPopuplocation,0,'',attr_activity_scopeid,newdate,newdate);
		getDistrictNames('',newdate,newdate);	
  });
  
function getEventDocumentForPopup(searchType,locationId,day,num,path,attr_activity_scopeid,locationScope,locationScopeValue,calFrom, activity_name)
{
	 $("#popupImages").html('<img src="./images/Loading-data.gif" />');
	// var dates=$('.searchDateCls ').val();
	 var dates=$('#daysListId').val();
	  var fromDateStr='';
	  var toDateStr='';
	  
	 if(dates != null && dates.length>0){
		 var dateArray=dates.split(" to ");
		  fromDateStr=dateArray[0];
		  toDateStr=dateArray[1];
		  if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			} 
	 }
 	  
		globallocationScope=locationScope;
		globallocationValue=locationScopeValue;
		
		
		var jObj = {
		activityId:globalActivityScope,
		locationScope:locationScope,
		locationValue:locationScopeValue,		
		day:day,
		//fromDateStr:"01-01-2015",
		//toDateStr:"22-01-2017",
		fromDateStr :fromDateStr,
	    toDateStr : toDateStr,
		type:"popup",
		startIndex:num,
		maxIndex:10,
		callFrom:calFrom,
		//locationName:obj.locationName,
		activityMemberId : globalActivityMemberId,
	    stateId : globalStateId,
	    userTypeId : globalUserTypeId,
		 task:"popupdaywise"
		};
		//alert("image2");
				$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildDayWisImagesForPopup1(result,jObj,path,attr_activity_scopeid,locationScope,locationScopeValue,searchType,locationId, activity_name);
			});
}

var isWeeksBuild=false;
function buildDayWisImagesForPopup1(result,jObj,path,attr_activity_scopeid,locationScope,locationScopeValue,searchType,locationId, activity_name)
{
	$("#popupImages").html('');
	var datesArr = [];
	var str ='';
	$('.slider-for,.slider-nav').slick('unslick');
	if(result != null)
	{
		str+='<ul class="slider-for">';
			for(var i in result)
			{
				for(var j in result[i].subList)
				{					
					str+='<li> ';
					if(result[i].subList[j].address != null)
					str+='<div class="" style="margin-bottom: 5px;margin-left: -7px;margin-top: -8px;">';
						str+='<ul class="breadcrumb">';
						//if(result[i].subList[j].address.stateName != null && result[i].subList[j].address.stateName.trim().length>0)
						//	str+='<li>'+result[i].subList[j].address.stateName+'</li>';
						if(result[i].subList[j].address.districtName != null && result[i].subList[j].address.districtName.trim().length>0 ){
							if(locationScope=='district')
								str+='<li  class="text-primary"  title="'+result[i].subList[j].address.districtName+' District ">'+result[i].subList[j].address.districtName+' </li>';
							else
								str+='<li title="'+result[i].subList[j].address.districtName+' District ">'+result[i].subList[j].address.districtName+' </li>';
						}
						if(result[i].subList[j].address.constituencyName != null && result[i].subList[j].address.constituencyName.trim().length>0 ){
							if(locationScope=='constituency')
								str+='<li  class="text-primary"  title="'+result[i].subList[j].address.constituencyName+' Assembly ">'+result[i].subList[j].address.constituencyName+' </li>';
							else
								str+='<li  title="'+result[i].subList[j].address.constituencyName+' Assembly ">'+result[i].subList[j].address.constituencyName+' </li>';
						}					
						if(result[i].subList[j].address.tehsilName != null && result[i].subList[j].address.tehsilName.trim().length>0 ){
							if(locationScope=='mandal')
								str+='<li  class="text-primary"  title="'+result[i].subList[j].address.tehsilName+' Mandal ">'+result[i].subList[j].address.tehsilName+' Mandal  </li>';
							else
								str+='<li  title="'+result[i].subList[j].address.tehsilName+' Mandal ">'+result[i].subList[j].address.tehsilName+' Mandal </li>';
						}					
						if(result[i].subList[j].address.townshipName != null && result[i].subList[j].address.townshipName.trim().length>0 ){
							if(locationScope=='mandal')
								str+='<li  class="text-primary"  title="'+result[i].subList[j].address.townshipName+' Mandal/Munci/Greater City " >'+result[i].subList[j].address.townshipName+' </li>';
							else
								str+='<li  title="'+result[i].subList[j].address.townshipName+' Mandal/Munci/Greater City  ">'+result[i].subList[j].address.townshipName+' </li>';
						}
						if(result[i].subList[j].address.panchayatName != null && result[i].subList[j].address.panchayatName.trim().length>0 ){
							if(locationScope=='village')
								str+='<li  class="text-primary"   title="'+result[i].subList[j].address.panchayatName+' Village  " >'+result[i].subList[j].address.panchayatName+' Village </li>';
							else
								str+='<li   title="'+result[i].subList[j].address.panchayatName+' Village  " >'+result[i].subList[j].address.panchayatName+' Village </li>';
						}
						if(result[i].subList[j].address.wardName != null && result[i].subList[j].address.wardName.trim().length>0 ){
							if(locationScope=='village')
								str+='<li  class="text-primary">'+result[i].subList[j].address.wardName+' </li>';
							else
								str+='<li>'+result[i].subList[j].address.wardName+' </li>';
						}
						
					str+='</ul>';
					str+='</div>';
					str+='<img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'"></li>';
				}
				
				if(!isWeeksBuild){
					isWeeksBuild= true;
					$('#daysListId').find('option').remove();  
					$("#daysListId").append('<option value="0"> ALL </option>');				
					//$("#daysListId").append('<option value="0" attr_scope_id="'+globalActivityScope+'" > Select Week </option>');
					
					var maxlenght = result[i].subList2.length-1;
					
					for(var j in result[i].subList2){
						if(parseInt(j) == parseInt(maxlenght)){
							$("#daysListId").append('<option value="'+result[i].subList2[j].strDate+'" attr_scope_id="'+globalActivityScope+'" attr_location_scope_id="'+locationScope+'" attr_location_value="'+locationScopeValue+'" selected> Week-'+(parseInt(j)+1)+' ('+result[i].subList2[j].strDate+' ) </option>');	
						}else{
							$("#daysListId").append('<option value="'+result[i].subList2[j].strDate+'" attr_scope_id="'+globalActivityScope+'" attr_location_scope_id="'+locationScope+'" attr_location_value="'+locationScopeValue+'"> Week-'+(parseInt(j)+1)+' ('+result[i].subList2[j].strDate+' ) </option>');
						}
						
					}
						var weekDays = $("#daysListId").val();
						//alert(7777);
						if(weekDays != null){
							datesArr = weekDays.split('to');
						}
						if(datesArr != null && datesArr.length==2){
							getTempEventDocumentForPopup(searchType,locationId,jObj.day,jObj.startIndex,path,attr_activity_scopeid,locationScope,locationScopeValue,jObj.callFrom,datesArr[0].trim(),datesArr[1].trim());
							getTempAvailablDates(globallocationScope,globallocationValue,0,'',globalActivityScope,datesArr[0].trim(),datesArr[1].trim());
							getDistrictNames(activity_name,datesArr[0].trim(),datesArr[1].trim());	
						}else{
							getTempEventDocumentForPopup(searchType,locationId,jObj.day,jObj.startIndex,path,attr_activity_scopeid,locationScope,locationScopeValue,jObj.callFrom,'','');
							getTempAvailablDates(globallocationScope,globallocationValue,0,'',globalActivityScope,'','');
							getDistrictNames('','','');	
						}
				}
			  
			}
			  str+='</ul>';
		str+='<ul class="slider-nav m_top20">';	
	
		for(var i in result)
		{	 
			for(var j in result[i].subList)
			{
				str+='<li><img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'" style="cursor:pointer;"/></li>';	
			}
		}
				str+='</ul>';
			$("#popupImages").html(str);
			
			setTimeout(function(){		
			$('.slider-for').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  slide: 'li',
			  arrows: true,
			  fade: true,
			  asNavFor: '.slider-nav'
			});
			$('.slider-nav').slick({
			  slidesToShow: 11,
			  slidesToScroll: 0,
			  slide: 'li',
			  asNavFor: '.slider-for',
			  dots: false,
			 // centerMode: true,
			focusOnSelect: true,
			  variableWidth: true

				})
			$(".slick-list").css("margin-left","17px;");	
			$(".slick-list").css("margin-right","17px;");	
			//$('.slider-nav li:first-child').trigger('click');
			//$('.slider-nav li:first-child').trigger('click');
		},300);
		
			var itemsCount=result[0].totalResult;
			
			
	    var maxResults=jObj.maxIndex;
	   if(jObj.startIndex==0){
		   $("#paginationDivId").html('');
		   $("#paginationDivId").pagination({
				items: itemsCount,
				itemsOnPage: maxResults,
				cssStyle: 'light-theme',
				
				onPageClick: function(pageNumber, event) {
					var num=(pageNumber-1)*10;
					// getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,jObj.day,num,path,attr_activity_scopeid,locationScope,locationScopeValue,""); .
					var weekDays = $("#daysListId").val();
					if(weekDays != null){
						datesArr = weekDays.split('to');
					}					
					if(datesArr != null && datesArr.length==2){
						getTempEventDocumentForPopup(jObj.locationScope,jObj.locationValue,jObj.day,num,path,attr_activity_scopeid,locationScope,locationScopeValue,"",datesArr[0].trim(),datesArr[1].trim());
					}else{
						getTempEventDocumentForPopup(jObj.locationScope,jObj.locationValue,jObj.day,num,path,attr_activity_scopeid,locationScope,locationScopeValue,"",'','');
					}					
				}
			});
			$("#paginationDivId").find("ul").addClass("pagination");
		}
	
	}
}

function getTempEventDocumentForPopup(searchType,locationId,day,num,path,attr_activity_scopeid,locationScope,locationScopeValue,calFrom,fromDateStr,toDateStr)
{
	 $("#popupImages").html('<img src="./images/Loading-data.gif" />');
	
	 var dates=$('#daysListId').val();
	  var fromDateStr='';
	  var toDateStr='';
	  
	 if(dates != null && dates.length>0){
		 var dateArray=dates.split(" to ");
		  fromDateStr=dateArray[0];
		  toDateStr=dateArray[1];
		  if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			} 
	 }
		 
	 var dates=$('.searchDateCls ').val();
		var jObj = {
		activityId:globalActivityScope,
		locationScope:locationScope,
		locationValue:locationScopeValue,		
		day:day,
		//fromDateStr:"01-01-2015",
		//toDateStr:"22-01-2017",
		fromDateStr : fromDateStr,
	    toDateStr :toDateStr,
		type:"popup",
		startIndex:num,
		maxIndex:10,
		callFrom:calFrom,
		//locationName:obj.locationName,
		activityMemberId : globalActivityMemberId,
	    stateId : globalStateId,
	    userTypeId : globalUserTypeId,
		 task:"popupdaywise"
		};
		//alert("image1");
	
		$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildDayWisImagesForPopup1(result,jObj,path,attr_activity_scopeid,locationScope,locationScopeValue,searchType,locationId);
			});
			
}

function getTempAvailablDates(locationScope,locationValue,day,path,attr_activity_scopeid,fromDateStr,toDateStr)
{
		
	  $("#popupDaysDiv1").html('');
	  var activityScopeId = $("#ActivityList").val();

		var jObj = {
		activityId:attr_activity_scopeid,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		activityMemberId : globalActivityMemberId,
		stateId : globalStateId,
		userTypeId : globalUserTypeId,
		task:""
		};
		$.ajax({
		  type:'GET',
		  url: 'getAvailableDatesForActivitiesAction.action',
		 data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			var str ='';
			str+='<ul class="slickApplyPopupDays" style="width:810px;">';
			for(var i in result)
			{ 
				/*if(i==0 && (globlbuildType =='dayswise')){
					str+='<li class="" ><a href="javascript:{};" class="getImageCls" activity_name=""  type="overAll" locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'"  > OVER ALL <span class="sr-only">(current)</span></a></li>';
				}
				else if(i==0 && globlbuildType =='overAll'){
					str+='<li class=" " ><a href="javascript:{};" class="getImageCls"   activity_name=""   type="dayswise" locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" > DAY WISE <span class="sr-only">(current)</span></a></li>';
				}
				*/
				
				if(i==0 && globlbuildType =='overAll'){
					//str+='<li class="" ><a href="javascript:{};" class="dayssCls" locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].date+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="0" > OVER ALL <span class="sr-only">(current)</span></a></li>';
					
					//str+='<li class="" ><a href="javascript:{};" class="dayssCls active " locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="0" ><a href="javascript:{};">OVER ALL 222</a><span class="sr-only">(current)</span></a></li>';
					
					str+='<li class=" dayssCls active"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="0"><a href="javascript:{};">OVER ALL  </a></li>';
					
					
				}
					
				if(globlbuildType =='dayswise'){						
					if(result[i].id==day)
					{//attr_activity_scopeid,locationScope,locationScopeValue
						str+='<li class=" dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].documentsVOList[j].strDate+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="javascript:{};">Day '+result[i].id+' (<span title="Total Images ">'+result[i].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
						/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */
					}else if (i==0){
						str+='<li class=" dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].documentsVOList[j].strDate+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="javascript:{};">Day '+result[i].id+' (<span title="Total Images ">'+result[i].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
						/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */ 
					}else{
						str+='<li class="dayssCls" locationScope="'+locationScope+'" locationScopeValue="'+locationValue+'"  dateValue="'+result[i].documentsVOList[j].strDate+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].id+'"><a href="javascript:{};">Day '+result[i].id+' (<span title="Total Images ">'+result[i].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
					 }
				}
				else if(globlbuildType =='overAll' && i == 0 ){
					for(var j in result[i].documentsVOList)
					{
						if(result[i].documentsVOList[j].strDate==day)
						{//attr_activity_scopeid,locationScope,locationScopeValue
							str+='<li class=" dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].documentsVOList[j].strDate+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].documentsVOList[j].day+'"><a href="javascript:{};"> Day '+result[i].documentsVOList[j].day+' <br>  '+result[i].documentsVOList[j].strDate+' (<span title="Total Images ">'+result[i].documentsVOList[j].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].documentsVOList[j].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
							/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */
						}else if (j==0){
							str+='<li class=" dayssCls"  locationScope="'+locationScope+'"   locationScopeValue="'+locationValue+'"  dateValue="'+result[i].documentsVOList[j].strDate+'" path = "'+path+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].documentsVOList[j].day+'"><a href="javascript:{};"> Day '+result[i].documentsVOList[j].day+' <br>'+result[i].documentsVOList[j].strDate+' (<span title="Total Images ">'+result[i].documentsVOList[j].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].documentsVOList[j].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
							/* getEventDocumentForPopup(jObj.locationScope,jObj.locationValue,day,0,path,attr_activity_scopeid,locationScope,locationValue); */ 
						}else{
							str+='<li class="dayssCls" locationScope="'+locationScope+'" locationScopeValue="'+locationValue+'"  dateValue="'+result[i].documentsVOList[j].strDate+'" attr_activity_scopeid="'+attr_activity_scopeid+'" attr="'+result[i].documentsVOList[j].day+'"><a href="javascript:{};">Day '+result[i].documentsVOList[j].day+' <br>'+result[i].documentsVOList[j].strDate+' (<span title="Total Images ">'+result[i].documentsVOList[j].totalResult+'</span> / <span title="Total Images Covered Locations ">'+result[i].documentsVOList[j].coveredCount+'</span>) <span class="sr-only">(current)</span></a></li>';
						 }
					}
				}
			}
			str+='</ul>';
			$("#popupDaysDiv1").html(str);
			$('.slickApplyPopupDays').slick({
				slide: 'li',
				slidesToShow: 3,
				slidesToScroll: 3,
				infinite: false,
				variableWidth:true
			});
			GlobalPopupScope = jObj.locationScope;
			GlobalPopuplocation =jObj.locationValue;
			
	});
}

function getDistrictNames(activity_name,startDate,endDate){
	
	$("#districtsUlId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var scopeId = $("#hiddenActivityScopeId").val();
	var activityLevelId= $("#hiddenActivityLevelId").val();
	var date1Str ='';
	var date2Str ='';
	
	if( (startDate==null || startDate =='') || (endDate==null || endDate =='')){
		//var date1 = $("#dateRangeIdForEvents").val();
		var date1 = $("#daysListId").val();
		var date2Arr =date1.split(" to ");	
		if(date2Arr != null && date2Arr.length>1){
			date1Str = date2Arr[0].replace("/", "-");
			date1Str = date1Str.replace("/", "-");
			date2Str = date2Arr[1].replace("/", "-");
			date2Str = date2Str.replace("/", "-");
		}
					
	}
	else{
		date1Str =startDate;
		date2Str =endDate;
	
	}
	var jObj = {
		activityScopeId:scopeId,
		activityMemberId : globalActivityMemberId,
		startDate:date1Str,
		endDate:date2Str,
	    stateId : globalStateId,
	    userTypeId : globalUserTypeId,		
	};
	//alert(999);
	$.ajax({
	  type:'GET',
	  url: 'getDistrictListsAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildDistrictNames(result,activityLevelId,scopeId,activity_name);
	});
}

function buildDistrictNames(result,activityLevelId,scopeId,activity_name)
{
	var str='';
	/* str+='<div class="panel-group" id="accordionModal" role="tablist" aria-multiselectable="true">';
	
	var coveredCount =0;
	var imagesCount =0;
	for(var i in result)
	{
		if(result[i].count != null )
			imagesCount = parseInt(result[i].count)+imagesCount;
		if(result[i].imagesCnt != null )
			coveredCount = parseInt(result[i].imagesCnt)+coveredCount;
	}
	
	for(var i in result)
	{
		if(i==0){
			str+='<div style="text-align:right;margin:10px ">';
			//str+='<div class="panel-heading panel-headingModal" role="tab"  id="headingOneModa1l'+i+'" >';
				str+='<a role="button" class="getImageCls"  type="overAll"   activity_name="'+activity_name+'" attr_activity_scopeid="'+scopeId+'" attr_level_id="'+activityLevelId+'" aria-controls="collapseOneModa1l'+i+'"data-toggle="collapse" data-parent="#accordionModal"  aria-expanded="true" attr_activity_level_id="'+activityLevelId+'" >';
				/*str+='<h4 class="panel-title"> Andhra Pradesh ( <span title="Total Uploaded Images ">'+imagesCount+' </span>/<span title="Images Covered Locations ">'+coveredCount+')</h4>';
				str+='<i class="glyphicon glyphicon-refresh" title="click here to refresh"></i>';
				str+='</a>';
				
				str+='</div>';
		//str+='<div id="collapseOneModa1l'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneModa1l'+i+'">';
		//  str+='<div class="panel-body pad_0">';
		//	str+='<div id="constituenciesBlocks'+result[i].districtId+'"></div>';
		//  str+='</div>';
		//str+='</div>';
	 // str+='</div>';
		
		}
		
		
	  str+='<div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModal'+i+'">';
		if(activityLevelId == 1 || activityLevelId == 2 || activityLevelId == 5){
			str+='<a role="button" class="constituencyPopups accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModal" attr_distId="'+result[i].districtId+'" attr_dist_name="'+result[i].name+'" href="#collapseOneModal'+i+'" aria-expanded="true" attr_activity_level_id="'+activityLevelId+'" aria-controls="collapseOneModal'+i+'">';
			str+='<h4 class="panel-title">'+result[i].name+'( <span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCnt+')</h4>';
		  str+='</a>';
		}else{
			 str+='<a role="button" class="constituencyPopups accordionmodal-toggle collapsed"  data-parent="#accordionModal" attr_distId="'+result[i].districtId+'" attr_dist_name="'+result[i].name+'" href="#collapseOneModal'+i+'" aria-expanded="true" aria-controls="collapseOneModal'+i+'">'; 
			str+='<h4 class="panel-title">'+result[i].name+'( <span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCnt+')</h4>';
		  str+='</a>';
		}
		str+='</div>';
		str+='<div id="collapseOneModal'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneModal'+i+'">';
		  str+='<div class="panel-body pad_0">';
			str+='<div id="constituenciesBlock'+result[i].districtId+'"></div>';
		  str+='</div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>'; */
	
	
	str+='<div class="panel-group" id="accordionCons" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
	 str+=' <div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading" role="tab" id="headingCons'+i+'">';
			str+='<a role="button" class="constituencyPopups collapsed accordionmodal-toggle" data-toggle="collapse" data-parent="#accordionCons" href="#collapseCons'+i+'" aria-expanded="true"  attr_distId="'+result[i].districtId+'" attr_dist_name="'+result[i].name+'" aria-controls="collapseCons'+i+'">';
			  str+='<h4 class="panel-title">'+result[i].name+'( <span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCnt+')</h4>';
			str+='</a>';
		str+='</div>';
		str+='<div id="collapseCons'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingCons'+i+'">';
		  str+='<div class="panel-body">';
			str+='<div id="constituenciesBlock'+result[i].districtId+'"></div>';
		  str+='</div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>';
	$("#districtsUlId").html(str);
}
$(document).on("click",".constituencyPopups",function(){
	/* var getOpenId = $(this).attr('href');
	var removeClass = $(this).closest(".panel-group")
		removeClass.find(".panel-collapse").removeClass("in");
	$(".accordionmodal-toggle").addClass("collapsed");
	if($(this).hasClass("collapsed"))
	{
		$(this).removeClass("collapsed");
		$(getOpenId).addClass("in");
	}else{
		$(this).addClass("collapsed");
		$(getOpenId).removeClass("in");
	} */
		
	var distId = $(this).attr("attr_distId");
	var activityLevelId = $(this).attr("attr_activity_level_id");
	var attr_dist_name = $(this).attr("attr_dist_name");
	getConstituencyList(distId,activityLevelId);
});

function getConstituencyList(distId,activityLevelId){
	$("#constituenciesBlock"+distId).html('');
	$(".allConstCls").html('');
	var datevalue="";
	$('.dayssCls').each(function(){
		if($(this).hasClass("active")){
			// var day = $(this).attr("attr");
			var locationScope = 'district';
			var locationScopeValue = distId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			var day = $(this).attr("attr");
			datevalue = $(this).attr("datevalue");
			
			var path = $(this).attr("path");
			//getAvailablDates(locationScope,locationScopeValue,0,path,attr_activity_scopeid);
			//getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"");
			getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,'',attr_activity_scopeid,locationScope,locationScopeValue,"",datevalue,datevalue);
			/*var weekDays = $("#daysListId").val();
			var datesArr = [];
			if(weekDays != null){
				datesArr = weekDays.split('to');
			}
			if(datesArr != null && datesArr.length==2){ 
				getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,'',attr_activity_scopeid,locationScope,locationScopeValue,"",datevalue,datevalue);
				//getTempAvailablDates(locationScope,locationScopeValue,day,'',attr_activity_scopeid,datevalue,datevalue);
			}else{
				getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,'',attr_activity_scopeid,locationScope,locationScopeValue,"",'','');
				//getTempAvailablDates(locationScope,locationScopeValue,day,'',attr_activity_scopeid,'','');
			}
			*/
		}
	});
	
	$("#constituenciesBlock"+distId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var scopeId = $("#hiddenActivityScopeId").val();
	var date = $("#daysListId").val();
	var datesArr = [];
	
	if(datevalue == null || datevalue.length==0){
		if(date != null){
			datesArr = date.split(' to ');
			
		}
	}else{
		var dateArr1 =datevalue.split("-");
		if(dateArr1 != null && dateArr1.length>1){
			datesArr.push(dateArr1[2]+"-"+dateArr1[1]+"-"+dateArr1[0]);
			datesArr.push(dateArr1[2]+"-"+dateArr1[1]+"-"+dateArr1[0]);
		}
	}
	
	var jObj;
	if(datesArr != null && datesArr.length==2){
		jObj = {
				activityScopeId:scopeId,
				districtId : distId,
				fromDate: datesArr[0].trim(),
				toDate: datesArr[1].trim()
			};
	}else{
		jObj = {
			activityScopeId:scopeId,
			districtId : distId,
			fromDate: '',
			toDate: ''
		};	
	}
		
	
	
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyListsAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0)
			buildConstituencyList(result,distId,activityLevelId);
		else
		$("#constituenciesBlock"+distId).html("No Data Available.");
	});
}
function buildConstituencyList(result,distId,activityLevelId)
{
	var str='';
	/* str+='<div class="panel-group allConstCls" id="accordionModalCons'+distId+'" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
	  str+='<div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModalCons'+i+'">';
		if(activityLevelId == 1 || activityLevelId == 2){
			 str+='<a role="button" class="mandalPopups accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModalCons'+distId+'" attr_consId="'+result[i].constituencyId+'" href="#collapseOneModalCons'+i+'" attr_activity_level_id="'+activityLevelId+'"aria-expanded="true" aria-controls="collapseOneModalCons'+i+'" attr_num="'+i+'">';
			str+='<h4 class="panel-title">'+result[i].name+' ASSEMBLY ( <span title="Total Uploaded Images ">'+result[i].imagesCnt+' </span>/<span title="Images Covered Locations ">'+result[i].count+')</h4>';
		  str+='</a>';
		}else{
			  str+='<a role="button" class="mandalPopups accordionmodal-toggle collapsed" data-parent="#accordionModalCons'+distId+'" attr_consId="'+result[i].constituencyId+'" href="#collapseOneModalCons'+i+'" aria-expanded="true" aria-controls="collapseOneModalCons'+i+'">'; 
			str+='<h4 class="panel-title">'+result[i].name+' ASSEMBLY ( <span title="Total Uploaded Images ">'+result[i].imagesCnt+' </span>/<span title="Images Covered Locations ">'+result[i].count+'</span>)</h4>';
		  str+='</a>';
		}
		 
		str+='</div>';
		str+='<div id="collapseOneModalCons'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneModalCons'+i+'">';
		  str+='<div class="panel-body pad_0">';
			str+='<div id="mandalsBlock'+result[i].constituencyId+'"></div>';
		  str+='</div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>'; */
	str+='<div class="panel-group" id="accordionMandal" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
	 str+=' <div class="panel panel-default">';
		str+='<div class="panel-heading" role="tab" id="headingMandal'+i+'">';
			str+='<a role="button" class="mandalPopups collapsed accordionmodal-toggle" data-toggle="collapse" data-parent="#accordionMandal" href="#collapseMandal'+i+'" aria-expanded="true" attr_consId="'+result[i].constituencyId+'" aria-controls="collapseMandal'+i+'">';
			  str+='<h4 class="panel-title">'+result[i].name+' ASSEMBLY ( <span title="Total Uploaded Images ">'+result[i].imagesCnt+' </span>/<span title="Images Covered Locations ">'+result[i].count+'</span>)</h4>';
			str+='</a>';
		str+='</div>';
		str+='<div id="collapseMandal'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingMandal'+i+'">';
		  str+='<div class="panel-body">';
			str+='<div id="mandalsBlock'+result[i].constituencyId+'"></div>';
		  str+='</div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>';
	$("#constituenciesBlock"+distId).html(str);
	
}
$(document).on("click",".mandalPopups",function(){
	/* var getOpenId = $(this).attr('href');
	var removeClass = $(this).closest(".panel-group")
		removeClass.find(".panel-collapse").removeClass("in");
	$(".accordionmodal-toggle").addClass("collapsed");
	if($(this).hasClass("collapsed"))
	{
		$(this).removeClass("collapsed");
		$(getOpenId).addClass("in");
	}else{
		$(this).addClass("collapsed");
		$(getOpenId).removeClass("in");
	} */
	var constituencyId = $(this).attr("attr_consId");
	var activityLevelId = $(this).attr("attr_activity_level_id");
	getMandalOrMuncList(constituencyId,activityLevelId,0,"");
});

function getMandalOrMuncList(constituencyId,activityLevelId,value,scopeId){
	
	$("#mandalsBlock"+constituencyId).html('');
	var datevalue="";
	$('.dayssCls').each(function(){
		if($(this).hasClass("active")){
			 //var day = $(this).attr("attr");
			var locationScope = 'constituency';
			var locationScopeValue = constituencyId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			var path = $(this).attr("path");
			var day = $(this).attr("attr");
			datevalue = $(this).attr("datevalue");
			
			//getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"");
			//getAvailablDates(locationScope,locationScopeValue,0,path,attr_activity_scopeid);
			getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"",datevalue,datevalue);
			/*
						var weekDays = $("#daysListId").val();
						
						if(weekDays != null){
							datesArr = weekDays.split(' to ');
						}
						if(datesArr != null && datesArr.length==2){
							getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"",datesArr[0].trim(),datesArr[1].trim());
							getTempAvailablDates(locationScope,locationScopeValue,day,'',attr_activity_scopeid,datesArr[0].trim(),datesArr[1].trim());
						}
						else{
							getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"",datevalue,datevalue);
							getTempAvailablDates(locationScope,locationScopeValue,day,'',attr_activity_scopeid,"","");
						}
		*/
		}
	});
	
	$("#mandalsBlock"+constituencyId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var activtyScopeId;
	if(scopeId !=null && scopeId.length > 0){
		activtyScopeId = scopeId;
	}else{
		activtyScopeId = $("#hiddenActivityScopeId").val();
	}
	//var scopeId = $("#hiddenActivityScopeId").val();
	var date = $("#daysListId").val();
	var datesArr = [];
	
	if(datevalue == null || datevalue.length==0){
		if(date != null){
			datesArr = date.split(' to ');
		}
	}else{
		var dateArr1 = datevalue.split("-");
		if(dateArr1 != null && dateArr1.length>1){
			datesArr.push(dateArr1[2]+"-"+dateArr1[1]+"-"+dateArr1[0]);
			datesArr.push(dateArr1[2]+"-"+dateArr1[1]+"-"+dateArr1[0]);
		}
	}	
	
		
	var jObj;
	if(datesArr != null && datesArr.length==2){
		jObj = {
			activityScopeId:activtyScopeId,
			constituencyId : constituencyId,
			fromDate: datesArr[0].trim(),
			toDate: datesArr[1].trim()
		};
	}else{
		jObj = {
			activityScopeId:activtyScopeId,
			constituencyId : constituencyId,
			fromDate: '',
			toDate: ''
		};
	}
		
	$.ajax({
	  type:'GET',
	  url: 'getMandalOrMuncpalityListAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0 )
		buildMandalOrMuncList(result,constituencyId,activityLevelId,value,scopeId);
	else
		$("#mandalsBlock"+constituencyId).html("No Data Available.");
	});
}
function buildMandalOrMuncList(result,constituencyId,activityLevelId,value,scopeId)
{
	var str='';
	str+='<div class="panel-group" id="accordionModalMandal'+constituencyId+'" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
	//srinu
	  str+='<div class="panel panel-default panel-custommodal">';
		str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModalMandal'+i+'">';
		if(activityLevelId == 1){
			str+='<a role="button" class="panchayatPopups accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModalMandal'+constituencyId+'" attr_mandalId="'+result[i].mandalId+'" attr_scopeId="'+scopeId+'"  href="#collapseOneModalMandal'+i+'" aria-expanded="true" aria-controls="collapseOneModalMandal'+i+'">';
			str+='<h4 class="panel-title">'+result[i].name+'(<span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCount+')</h4>';
		  str+='</a>';
		}else{
			 str+='<a role="button" class="panchayatPopups accordionmodal-toggle" data-parent="#accordionModalMandal'+constituencyId+'" attr_mandalId="'+result[i].mandalId+'"  attr_scopeId="'+scopeId+'" href="#collapseOneModalMandal'+i+'" aria-expanded="true" aria-controls="collapseOneModalMandal'+i+'">'; 
			str+='<h4 class="panel-title">'+result[i].name+'(<span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCount+')</h4>';
		  str+='</a>';
		}
		str+='</div>';
		str+='<div id="collapseOneModalMandal'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneModalMandal'+i+'">';
		  str+='<div class="panel-body pad_0"><div id="panchayatBlock'+result[i].mandalId+'"></div></div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>';
	if(value == 0){
		$("#mandalsBlock"+constituencyId).html(str);
	}else{
		$("#mandalsUlId").html(str);
	}
	
}
$(document).on("click",".panchayatPopups",function(){
	var mandalId = $(this).attr("attr_mandalId");
	var scopeId = $(this).attr("attr_scopeId");
	var hrefRef = $(this).attr("href");
	$(hrefRef).collapse('toggle');
	getPanchayatList(mandalId,scopeId,0);
});

function getPanchayatList(mandalId,scopeId,value){
	$("#panchayatBlock"+mandalId).html('');
	var datevalue="";
	$('.dayssCls').each(function(){
		if($(this).hasClass("active")){
			 var day = $(this).attr("attr");
			var locationScope = 'mandal';
			var locationScopeValue = mandalId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			var path = $(this).attr("path");
			datevalue = $(this).attr("datevalue");
			
			getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,'',attr_activity_scopeid,locationScope,locationScopeValue,"",datevalue,datevalue);

		}
	});
	
	$("#panchayatBlock"+mandalId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var activtyScopeId;
	if(scopeId != null && scopeId.length > 0){
		activtyScopeId = scopeId;
	}else{
		activtyScopeId = $("#hiddenActivityScopeId").val();
	}
	
	//var scopeId = $("#hiddenActivityScopeId").val();
	
	var date = $("#daysListId").val();
	var datesArr = [];
	
	if(datevalue == null || datevalue.length==0){
		if(date != null){
			datesArr = date.split(' to ');
		}
	}else{
		var dateArr1 = datevalue.split("-");
		if(dateArr1 != null && dateArr1.length>1){
			datesArr.push(dateArr1[2]+"-"+dateArr1[1]+"-"+dateArr1[0]);
			datesArr.push(dateArr1[2]+"-"+dateArr1[1]+"-"+dateArr1[0]);
		}
	}
	var jObj;
	if(datesArr != null && datesArr.length==2){
		jObj = {
				activityScopeId:activtyScopeId,
				mandalOrMuncipalityId : mandalId,
				fromDate: datesArr[0].trim(),
				toDate: datesArr[1].trim()
			};
	}else{
		jObj  = {
			activityScopeId:activtyScopeId,
			mandalOrMuncipalityId : mandalId,
			fromDate: '',
			toDate: ''
		};
	}
	
	$.ajax({
	  type:'GET',
	  url: 'getPanchayatOrWardListAction.action',
	 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		buildPanchayatList(result,mandalId,value);
	});
}
function buildPanchayatList(result,mandalId,value)
{
	var str='';
	if(result !=null && result.length > 0){
	str+='<ul class="villageDaysModal">';
		for(var i in result)
		{
			str+='<li><a class="villagePopup" attr_villageId="'+result[i].panchayatId+'" style="cursor:pointer;">'+result[i].name+'(<span title="Total Uploaded Images ">'+result[i].count+' </span>/<span title="Images Covered Locations ">'+result[i].imagesCount+')</a></li>';
		}
	 str+='</ul>';
	 if(value == 0)
		 $("#panchayatBlock"+mandalId).html(str);
	 else
		 $("#villageUlId").html(str);
	}else{
		 if(value == 0)
			$("#panchayatBlock"+mandalId).html("No Data Available.");
		else
		 $("#villageUlId").html("No Data Available.");
	}
}
$(document).on("click",".villagePopup",function(){
	var panchayatId = $(this).attr("attr_villageId");
	$('.dayssCls').each(function(){
		if($(this).hasClass("active")){
			 var day = $(this).attr("attr");
			var locationScope = 'village';
			var locationScopeValue = panchayatId;
			var attr_activity_scopeid = $(this).attr("attr_activity_scopeid");
			var path = $(this).attr("path");
			
			var weekDays = $("#daysListId").val();
			if(weekDays != null){
				datesArr = weekDays.split('to');
			}
			if(datesArr != null && datesArr.length==2){
				getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"",datesArr[0].trim(),datesArr[1].trim());
				getTempAvailablDates(locationScope,locationScopeValue,day,'',attr_activity_scopeid,datesArr[0].trim(),datesArr[1].trim());
			}
			else{
				getTempEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"","","");
				getTempAvailablDates(locationScope,locationScopeValue,day,'',attr_activity_scopeid,"","");
			}
			/* getEventDocumentForPopup(GlobalPopupScope,GlobalPopuplocation,0,0,path,attr_activity_scopeid,locationScope,locationScopeValue,"");
			getAvailablDates(locationScope,locationScopeValue,0,path,attr_activity_scopeid); */

		}
	});
});

$(document).on("click",".newsImagemodal",function(){
	  setTimeout(function(){
	    $('body').addClass("modal-open");
	  }, 1000);                     
	});