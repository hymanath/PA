var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY");
var glEndDate = moment().add(10, 'years').endOf('year').format("DD-MM-YYYY");
//var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var spinner = '<div class="row"><div class="col-sm-12"><div class="d2d-loader"><div class="loader"></div><img src="D2D_Assests/images/login_logo.png"/></div></div></div>'
var levelWiseArr='';
var campaignLevelArr=["district","parliament","constituency"]
var campaignLevelArr1=["mandal","panchayat","muncipality"]
$(".tooltipCls").tooltip();
var globallevelIds='';
var globallevelValues='';
var globallevelChangeIds='';
var globallevelValuesChange ='';
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
function onLoadCalls(){
	 getUsersCountsByLocation();
	 getHouseHoldsCounts();
	 getGrievancesCounts();
	 getRecentImagesList("survey");
	 getDistrictsForStateAction("consWiseDistritsId");
	 getDepartmentWiseGrievanceCounts(1);
	 
	for(var i in globallevelIds){
		if(globallevelIds[i] == 2){
			
			 levelWiseArr=[{name:'district',id:'3'},{name:'parliament',id:'10'},{name:'constituency',id:'4'}];
			 levelWiseData();
			 //$("#consWiseDistritsId").show();
			for(var i in campaignLevelArr){
				 getLevelWiseCount(campaignLevelArr[i]);
			 }
			 for(var i in campaignLevelArr1){
				 getCampaignCountFrMandalPancMuncip(campaignLevelArr1[i]);
			 }
		}else if(globallevelIds[i] == 3){
			
			 levelWiseArr=[{name:'district',id:'3'},{name:'parliament',id:'10'},{name:'constituency',id:'4'}];
			 levelWiseData();
			 $("#consWiseDistritsId_chosen").hide();
			for(var i in campaignLevelArr){
				 getLevelWiseCount(campaignLevelArr[i]);
			 }
			for(var i in campaignLevelArr1){
				 getCampaignCountFrMandalPancMuncip(campaignLevelArr1[i]);
			 }
		}else if(globallevelIds[i] == 4){
			 levelWiseArr=[{name:'constituency',id:'4'}];
			 levelWiseData();
			 getLevelWiseCount("constituency");
			 getCampaignCountFrMandalPancMuncip("panchayat");
			 getCampaignCountFrMandalPancMuncip("mandal");
			 getCampaignCountFrMandalPancMuncip("muncipality");
			 $("#consWiseDistritsId_chosen").hide();
			
		}else if(globallevelIds[i] == 10){
			levelWiseArr=[{name:'parliament',id:'10'},{name:'constituency',id:'4'}];
			 levelWiseData();
			getLevelWiseCount("parliament");
			getLevelWiseCount("constituency");
			getCampaignCountFrMandalPancMuncip("panchayat");
			getCampaignCountFrMandalPancMuncip("mandal");
			$("#consWiseDistritsId_chosen").hide();
		}
	}
	 
}

setInterval(function() {
	var $inputs = $(".imagesTypeCls");
	var random = Math.floor(Math.random() * $inputs.length);
	  $inputs.each(function(i, node) {
		node.checked = (i === random);
	  });
	  
	 var imageType="";
	 $(".imagesTypeCls").each(function(){
		if($(this).is(':checked')){
			imageType =$(this).val();
		}
	});
	getRecentImagesList(imageType);
}, 480 * 1000);

$(document).on("click",".iconRefresh",function(){
	$("#surveyId").prop("checked",true);
	$("#hoistingId").prop("checked",false);
	onLoadCalls();
});

$("#dateRangePickerAUM").daterangepicker({
	opens: 'left',
	startDate: glStartDate,
	endDate: glEndDate,
	locale: {
		format: 'DD-MM-YYYY'
	},
	ranges: {
		'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
		'Today' : [moment(), moment()],
		'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		'This Month': [moment().startOf('month'), moment()],
		'This Year': [moment().startOf('Year'), moment()]
	}
});

var dates= $("#dateRangePickerAUM").val();
var pickerDates = glStartDate+' - '+glEndDate
if(dates == pickerDates)
{
	$("#dateRangePickerAUM").val('All');
}

$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
	glStartDate = picker.startDate.format('DD-MM-YYYY')
	glEndDate = picker.endDate.format('DD-MM-YYYY')
	if(picker.chosenLabel == 'All')
	{
		$("#dateRangePickerAUM").val('All');
	}
	
	$("#surveyId").prop("checked",true);
	$("#hoistingId").prop("checked",false);
	onLoadCalls();
	
});

 function getUsersCountsByLocation(){
	$("#usersWiseCountsId").html(spinner);
	
	var jsObj={
    	levelIds		:globallevelIds,
		levelValues		:globallevelValues,
		startDateStr	:glStartDate,
		endDateStr  	:glEndDate
    }
    $.ajax({
      type : "POST",
      url : "getUsersCountsByLocationAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			return buildUsersCountsByLocation(result);
		}
	});
	function buildUsersCountsByLocation(result){
		var str='';
		str+='<div class="media">';
			str+='<div class="media-left">';
				str+='<img src="D2D_Assests/icons/Total_Users_icon.png" class="main_block_logo"></img>';
			str+='</div>';
			str+='<div class="media-body">';
				/* str+='<h4>Total Users</h4>';
				if(result.totalUsers !=null && result.totalUsers>0){
					str+='<h3>'+result.totalUsers+'</h3>';
				}else{
					str+='<h3> - </h3>';
				}  */
				
				str+='<h4>Active Users</h4>';
				if(result.activeUsers !=null && result.activeUsers>0){
					str+='<h3>'+result.activeUsers+'</h3>';
				}else{
					str+='<h3>  - </h3>';
				}

			str+=' </div>';
		str+='</div>';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<hr class="m_0" style="border-color: #000;margin-top:0px;"/>';
				str+='<p class="text-center m_top-10"><span class="bg-fff" style="padding:0px 8px;">Active Users</span></p>';
				
			str+='</div>';
			str+='<div class="row m_top20">';
			str+='<div class="col-sm-4 text-center border_right">';
				str+='<h4>Last 1Hr</h4>';
				if(result.oneHourActive !=null && result.oneHourActive>0){
					str+='<h3>'+result.oneHourActive+'</h3>';
				}else{
					str+='<h3> - </h3>';
				}
				
			str+='</div>';
			str+='<div class="col-sm-4 text-center border_right">';
				str+='<h4>Last 3Hr</h4>';
				if(result.threeHoursActive !=null && result.threeHoursActive>0){
					str+='<h3>'+result.threeHoursActive+'</h3>';
				}else{
					str+='<h3> - </h3>';
				}
			str+='</div>';
			str+='<div class="col-sm-4 text-center">';
				str+='<h4>Last 5Hr</h4>';
				if(result.fiveHoursActive !=null && result.fiveHoursActive>0){
					str+='<h3>'+result.fiveHoursActive+'</h3>';
				}else{
					str+='<h3> - </h3>';
				}
			str+='</div>';
			str+='</div>';
		str+='</div>';
		/*str+='<div class="row m_top15">';
			str+='<div class="col-sm-6 text-center border_right">';
				str+='<h4>Active Users</h4>';
				if(result.activeUsers !=null && result.activeUsers>0){
					str+='<h3><span><img src="D2D_Assests/icons/Active_User_icon.png"  class="imageWidthHeigth"/></span>  '+result.activeUsers+'</h3>';
				}else{
					str+='<h3><span><img src="D2D_Assests/icons/Active_User_icon.png"  class="imageWidthHeigth"/></span>  - </h3>';
				}
				
			str+='</div>';
			str+='<div class="col-sm-6 text-center">';
				str+='<h4>In-Active Users</h4>';
				if(result.inActiveUsers !=null && result.inActiveUsers>0){
					str+='<h3><span><img src="D2D_Assests/icons/Inactive_user_icon.png" class="imageWidthHeigth" /></span>  '+result.inActiveUsers+'</h3>';
				}else{
					str+='<h3><span><img src="D2D_Assests/icons/Inactive_user_icon.png" class="imageWidthHeigth" /></span>  - </h3>';
				}
				
			str+='</div>';
		str+='</div>';*/
		$("#usersWiseCountsId").html(str);
		
	}
}
function getHouseHoldsCounts(){	 
	$("#houseHoldsWiseCountsId").html(spinner);
	
	
	var jsObj={
    	levelIds		:globallevelIds,
		levelValues		:globallevelValues,
		startDateStr	:glStartDate,
		endDateStr  	:glEndDate
    }
    $.ajax({
      type : "POST",
      url : "getHouseHoldsCountsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			return buildHouseHoldsCounts(result);
		}
		
	});
	
	function buildHouseHoldsCounts(result){
		var str='';
		str+='<div class="media">';
			str+='<div class="media-left">';
				str+='<img src="D2D_Assests/icons/Total Househols_icon.png" class="main_block_logo" style="height: 50px;"></img>';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h4>Total Households</h4>';
				if(result.totalHouseHolds !=null && result.totalHouseHolds>0){
					str+='<h3>'+result.totalHouseHolds+'</h3>';
				}else{
					str+='<h3> - </h3>';
				}
				
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top15">';
			str+='<div class="col-sm-12">';
				str+='<hr class="m_0" style="border-color: #000;margin-top:0px;"/>';
				if(result.totalImagesReceived !=null && result.totalImagesReceived>0){
					str+='<p class="text-center m_top-10"><span class="bg-fff" style="padding:0px 8px;">Images Received '+result.totalImagesReceived+'</span></p>';
				}else{
					str+='<p class="text-center m_top-10"><span class="bg-fff" style="padding:0px 8px;">Images Received 0</span></p>';
				}
				
			str+='</div>';
			str+='<div class="col-sm-6 text-center border_right m_top10">';
				str+='<h4>Visited</h4>';
				if(result.visitedHouseHolds !=null && result.visitedHouseHolds>0){
					str+='<h3><span><img src="D2D_Assests/icons/householder_visited_icon.png" class="imageWidthHeigth" /></span>  '+result.visitedHouseHolds+'</h3>';
				}else{
					str+='<h3><span><img src="D2D_Assests/icons/householder_visited_icon.png" class="imageWidthHeigth" /></span>  - </h3>';
				}
				
			str+='</div>';
			str+='<div class="col-sm-6 text-center m_top10">';
				str+='<h4>OverAll Pending</h4>';
				if(result.pendingHouseHolds !=null && result.pendingHouseHolds>0){
					str+='<h3><span><img src="D2D_Assests/icons/householder_pending_icon.png" class="imageWidthHeigth" /></span>  '+result.pendingHouseHolds+'</h3>';
				}else{
					str+='<h3><span><img src="D2D_Assests/icons/householder_pending_icon.png" class="imageWidthHeigth" /></span>  - </h3>';
				}
				
			str+='</div>';
		str+='</div>';
		
		//str+='<div class="row m_top10">';
		/* 	str+='<div class="col-sm-12">';
				str+='<hr class="m_0" style="border-color: #000;margin-top:0px;"/>';
				str+='<p class="text-center m_top-10"><span class="bg-fff" style="padding:0px 8px;">Visited</span></p>';
				
			str+='</div>'; */
			str+='<div class="row m_top5">';
			str+='<div class="col-sm-4 text-center border_right">';
				str+='<h6>Calls Dialed</h6>';
				if(result.callsCunt !=null && result.callsCunt>0){
					str+='<h5 class="m_top5"><span><img src="D2D_Assests/icons/Calls_dialed_icon.png" class="imageWidthHeigth" /></span>  <b>'+result.callsCunt+'</b></h5>';
				}else{
					str+='<h5 class="m_top5"><span><img src="D2D_Assests/icons/Calls_dialed_icon.png" class="imageWidthHeigth" /></span>   - </h5>';
				}
				
				
			str+='</div>';
			str+='<div class="col-sm-4 text-center border_right">';
				str+='<h6>SMS Sent</h6>';
				if(result.smsCount !=null && result.smsCount>0){
					str+='<h5 class="m_top5"><span><img src="D2D_Assests/icons/SMS_sent_icon.png" class="imageWidthHeigth" /></span>  <b>'+result.smsCount+'</b></h5>';
				}else{
					str+='<h5 class="m_top5"><span><img src="D2D_Assests/icons/SMS_sent_icon.png" class="imageWidthHeigth" /></span>   - </h5>';
				}
			str+='</div>';
			str+='<div class="col-sm-4 text-center">';
				str+='<h6 class="tooltipImages" data-toggle="tooltip" title="Flag Hoisting Images" style="cursor:pointer;text-align:left;">Flag Hoisting Im..</h6>';
				if(result.flagHoistingImgCunt !=null && result.flagHoistingImgCunt>0){
					str+='<h5 class="m_top5"><span><img src="D2D_Assests/icons/Images_recived_icon.png" class="imageWidthHeigth" /></span>  <b>'+result.flagHoistingImgCunt+'</b></h5>';
				}else{
					str+='<h5 class="m_top5"><span><img src="D2D_Assests/icons/Images_recived_icon.png" class="imageWidthHeigth" /></span>   - </h5>';
				}
			str+='</div>';
			str+='</div>';
		//str+='</div>';
		
		$("#houseHoldsWiseCountsId").html(str);
		$(".tooltipImages").tooltip();
	}
}
function getGrievancesCounts(){	  
	$("#grievanceWiseCountsId").html(spinner);
	
	
	var jsObj={
    	levelIds		:globallevelIds,
		levelValues		:globallevelValues,
		startDateStr	:glStartDate,
		endDateStr  	:glEndDate
    }
    $.ajax({
      type : "POST",
      url : "getGrievancesCountsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			return buildGrievancesCounts(result);
		}else{
			$("#grievanceWiseCountsId").html("NO DATA AVAILABLE");
		}
		
	});
	function buildGrievancesCounts(result){
		var str='';
		str+='<div class="media">';
			str+='<div class="media-left">';
				str+='<img src="D2D_Assests/icons/Total_Grievance_icon.png" class="main_block_logo" style="height: 85px;"></img>';
			str+=' </div>';
			str+='<div class="media-body">';
				str+='<h4>Total Grievances</h4>';
				if(result.totalGrievances !=null && result.totalGrievances>0){
					str+='<h3>'+result.totalGrievances+'</h3>';
				}else{
					str+='<h3> - </h3>';
				}
				
			str+='</div>';
		str+='</div>';
		str+='<div class="row" style="margin-top: 30px !important;">';
			str+='<div class="col-sm-6 text-center border_right">';
				str+='<h4>Individual</h4>';
				if(result.individual !=null && result.individual>0){
					str+='<h3><span><img src="D2D_Assests/icons/Individual_icon.png" class="imageWidthHeigth" /></span>  '+result.individual+'</h3>';
				}else{
					str+='<h3><span><img src="D2D_Assests/icons/Individual_icon.png" class="imageWidthHeigth" /></span>  - </h3>';
				}
				
			str+='</div>';
			str+='<div class="col-sm-6 text-center">';
				str+='<h4>Community</h4>';
				if(result.community !=null && result.community>0){
					str+='<h3><span><img src="D2D_Assests/icons/Community_icon.png" class="imageWidthHeigth" /></span>  '+result.community+'</h3>';
				}else{
					str+='<h3><span><img src="D2D_Assests/icons/Community_icon.png" class="imageWidthHeigth" /></span>  - </h3>';
				}
				
			str+='</div>';			
		str+='</div>';
		$("#grievanceWiseCountsId").html(str);
	}
}
function getRecentImagesList(imageType){
	$("#imagesSliderDivId").html(spinner);
	
	var jsObj={
    	levelIds		:globallevelIds,
		levelValues		:globallevelValues,
		imageType		:imageType,
		startDateStr	:glStartDate,
		endDateStr  	:glEndDate
    }
    $.ajax({
      type : "POST",
      url : "getRecentImagesListAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			return buildRecentImagesList(result,imageType);
		}else{
			$("#imagesSliderDivId").html("NO DATA AVAILABLE");
		}
		
	});
	function buildRecentImagesList(result,imageType){
		var str='';
		//str+='<div class="col-sm-12">';
		str+='<ul class="list-inline slider-nav">';
		for(var i in result.subList){
			if(imageType == "survey"){
				str+='<li style="background-color:#F75C5D;color:#fff !important;margin:0px 8px;border:1px solid #F75C5D;">';
			}else{
				str+='<li style="background-color:#009688;color:#fff !important;margin:0px 8px;border:1px solid #009688;">';
			}
				str+='<img onerror="setDefaultImage(this);" src="'+result.subList[i].name+'" alt="" class="img-responsive" style="width: 240px; height: 185px;margin-bottom:5px;"/>';
				str+='<div style="margin-top:-10px;padding:4px 8px;">';
					if(typeof result.subList[i].districtName == "undefined" || typeof result.subList[i].districtName === undefined)
					{
						str+='<h5 class="m_top5">District: -</h5>';
					}else{
						str+='<h5 class="m_top5">District: '+result.subList[i].districtName+'</h5>';
					}
					if(typeof result.subList[i].constituencyName == "undefined" || typeof result.subList[i].constituencyName === undefined)
					{
						str+='<h5 class="m_top5">Constituency: -</h5>';
					}else{
						if(result.subList[i].constituencyName !=null && result.subList[i].constituencyName.length>15){
							str+='<h5 class="m_top5" >Constituency: <span class="tooltipImgCls" data-toogle="tooltip" title="'+result.subList[i].constituencyName+'" style="cursor:pointer;">'+result.subList[i].constituencyName.substring(0,15)+'...</span></h5>';
						}else{
							str+='<h5 class="m_top5">Constituency: '+result.subList[i].constituencyName+'</h5>';
						}
						
					}
					if((typeof result.subList[i].tehsilName == "undefined" || typeof result.subList[i].tehsilName === undefined) || (typeof result.subList[i].pancahyatName == "undefined" || typeof result.subList[i].pancahyatName === undefined)) 
					{
						
					}else{
						if((result.subList[i].tehsilName != null && $.trim(result.subList[i].tehsilName).length > 0) || (result.subList[i].pancahyatName != null && $.trim(result.subList[i].pancahyatName).length > 0)){
							if(result.subList[i].tehsilName !=null && result.subList[i].tehsilName.length>15){
								str+='<h5 class="m_top5">Mandal: <span class="tooltipImgCls" data-toogle="tooltip" title="'+result.subList[i].tehsilName+'" style="cursor:pointer;">'+result.subList[i].tehsilName.substring(0,15)+'...</span></h5>';
							}else{
								str+='<h5 class="m_top5">Mandal: '+result.subList[i].tehsilName+'</h5>';
							}
							
							if(result.subList[i].pancahyatName !=null && result.subList[i].pancahyatName.length>15){
								str+='<h5 class="m_top5">Panchayat: <span class="tooltipImgCls" data-toogle="tooltip" title="'+result.subList[i].pancahyatName+'" style="cursor:pointer;">'+result.subList[i].pancahyatName.substring(0,15)+'...</span></h5>';
							}else{
								str+='<h5 class="m_top5">Panchayat: '+result.subList[i].pancahyatName+'</h5>';
							}
							
							
							//str+='<h5 class="m_top5">'+result.subList[i].boothNo+'</h5>';
							if(imageType == "survey"){
								str+='<h5 class="m_top5">'+result.subList[i].boothNo+'</h5>';
							}
						}else{
							if(result.subList[i].lebName !=null && result.subList[i].lebName.length>15){
								str+='<h5 class="m_top5">Municipality: <span class="tooltipImgCls" data-toogle="tooltip" title="'+result.subList[i].lebName+'" style="cursor:pointer;">'+result.subList[i].lebName.substring(0,15)+'...</span></h5>';
							}else{
								str+='<h5 class="m_top5">Municipality: '+result.subList[i].lebName+'</h5>';
							}
							//str+='<h5 class="m_top5">'+result.subList[i].boothNo+'</h5>';
							if(imageType != "survey"){
								if(result.subList[i].wardName !=null && result.subList[i].wardName.length>15){
									str+='<h5 class="m_top5"><span class="tooltipImgCls" data-toogle="tooltip" title="'+result.subList[i].wardName+'" style="cursor:pointer;">'+result.subList[i].wardName.substring(0,15)+'...</span></h5>';
								}else{
									str+='<h5 class="m_top5">'+result.subList[i].wardName+'</h5>';
								}
							}
							
							if(imageType == "survey"){
								str+='<h5 class="m_top5">'+result.subList[i].boothNo+'</h5>';
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
function levelWiseData()
{
	var collapse='';
	for(var i in levelWiseArr)
	{
		collapse+='<div class="panel-group levelWiseBlock'+levelWiseArr[i].name+'" id="accordion'+levelWiseArr[i].id+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+levelWiseArr[i].id+'">';
					if(i == 0)
					{
						collapse+='<a role="button" class="panelCollapseIcon panelCollapseClick '+levelWiseArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+levelWiseArr[i].id+'" href="#collapse'+levelWiseArr[i].id+'" aria-expanded="true" level_name="'+levelWiseArr[i].name+'" aria-controls="collapse'+levelWiseArr[i].id+'">';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon panelCollapseClick collapsed '+levelWiseArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+levelWiseArr[i].id+'" href="#collapse'+levelWiseArr[i].id+'" level_name="'+levelWiseArr[i].name+'" aria-expanded="true" aria-controls="collapse'+levelWiseArr[i].id+'">';
					}
					collapse+='<h4 class="panel-title text-capital">'+levelWiseArr[i].name+' Wise Overview</h4>';
						
					collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapse'+levelWiseArr[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+levelWiseArr[i].id+'">';
				}else{
					collapse+='<div id="collapse'+levelWiseArr[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+levelWiseArr[i].id+'">';
				}
				
					collapse+='<div class="panel-body">';
						if(levelWiseArr[i].id == 4){
							collapse+='<div class="col-sm-3">';
								collapse+='<select class="form-control chosen-select" id="consWiseDistritsId">';
								collapse+='</select>';
							collapse+='</div>';
						}
						collapse+='<div class="row">';
						collapse+='<div class="col-sm-12">';
						collapse+='<div id="campaign'+levelWiseArr[i].name+'"></div>';
					collapse+='</div>';
					collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
	}
	$("#levelWiseDetailsDivId").html(collapse);
	$("#consWiseDistritsId").chosen();
	 
	getLocationWiseCountDetails(levelWiseArr[0].name,"onLoad");	
}
$(document).on("click",".panelCollapseClick",function(){
	var blockName = $(this).attr("level_name");
	for(var i in globallevelIds){
		if(globallevelIds[i] == 2){
			 $("#consWiseDistritsId").show();
		}else if(globallevelIds[i] == 3){
			 $("#consWiseDistritsId").hide();
		}else if(globallevelIds[i] == 4){
			 $("#consWiseDistritsId").hide();
		}else if(globallevelIds[i] == 10){
			$("#consWiseDistritsId").hide();
		}
	}
	getLocationWiseCountDetails(blockName,"onLoad");
});
function getLocationWiseCountDetails(locationType,type)
{
	$("#campaign"+locationType).html(spinner);
	var levelIds=[];
	var levelValues=[];
	
	if(type == "onLoad"){
		levelIds = globallevelIds;
		levelValues = globallevelValues;
	}else if(type == "onChange"){
		levelIds = globallevelChangeIds
		levelValues = globallevelValuesChange
	}
	
	
	var jsObj={
    	locationType    : locationType,
		levelIds		:levelIds,
		levelValues		:levelValues,
		startDateStr	: glStartDate,
		endDateStr 		:  glEndDate
    }
    $.ajax({
      type : "POST",
      url : "getLocationWiseCountDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
			if(result !=null && result.length>0){
				buildLocationWiseCountDetails(result,locationType);
			}else{
				$("#campaign"+locationType).html("No Data Available");
			}
		
	});
}
function buildLocationWiseCountDetails(ajaxresp,locationType){
	if(ajaxresp != null && ajaxresp.length > 0){
			var str = '';
			str+='<div class="table-responsive">';
				str+='<table class="table table-condensed table-noborder dataTable'+locationType+'">';
					str+='<thead class="text-capital">';
						
						if(locationType == "district"){
							str+='<th class="text-capital">District</th>';
						}else if(locationType == "parliament"){
							str+='<th class="text-capital">parliament</th>';
						}
						else if(locationType == "constituency"){
							str+='<th class="text-capital">District</th>';
							str+='<th class="text-capital">Constituency</th>';
						}
						
						str+='<th class="text-capital">Total Users</th>';
						str+='<th class="text-capital">Last 1Hr</th>';
						str+='<th class="text-capital">Last 3Hr</th>';
						str+='<th class="text-capital">Last 5Hr</th>';
						str+='<th class="text-capital">Today Active</th>';
						/*str+='<th class="text-capital">In-active</th>';*/
						str+='<th class="text-capital">Households</th>';
						str+='<th class="text-capital">Visited Count</th>';
						str+='<th class="text-capital">Visited Perc</th>';
						//str+='<th class="text-capital">Pending Count</th>';
						str+='<th class="text-capital">Pending Perc</th>';
						str+='<th class="text-capital">Total Grievances</th>';
						//str+='<th class="text-capital">Individual</th>';
						//str+='<th class="text-capital">Community</th>';
						str+='<th class="text-capital">Images Received</th>';
						str+='<th class="text-capital">Calls Dialed</th>';
						str+='<th class="text-capital">SMS Sent</th>';
						str+='<th class="text-capital">Flag Hoisting Images</th>';
						
					str+='</thead>';
					str+='<tbody>';
						for(var i in ajaxresp){
							str+='<tr>';
								
								if(locationType == "district"){
									str+='<td class="text-capital">'+ajaxresp[i].name+'</td>';
								}
								else if(locationType == "parliament"){
									str+='<td class="text-capital">'+ajaxresp[i].name+'</td>';
								}
								else if(locationType == "constituency"){
									str+='<td class="text-capital">'+ajaxresp[i].districtName+'</td>';
									str+='<td class="text-capital">'+ajaxresp[i].name+'</td>';
								}
								if(ajaxresp[i].totalUsers !=null && ajaxresp[i].totalUsers>0){
									str+='<td class="text-capital">'+ajaxresp[i].totalUsers+'</td>';		
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								if(ajaxresp[i].oneHourActive !=null && ajaxresp[i].oneHourActive>0){
									str+='<td class="text-capital">'+ajaxresp[i].oneHourActive+'</td>';			
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								if(ajaxresp[i].threeHoursActive !=null && ajaxresp[i].threeHoursActive>0){
									str+='<td class="text-capital">'+ajaxresp[i].threeHoursActive+'</td>';			
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								if(ajaxresp[i].fiveHoursActive !=null && ajaxresp[i].fiveHoursActive>0){
									str+='<td class="text-capital">'+ajaxresp[i].fiveHoursActive+'</td>';			
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								if(ajaxresp[i].activeUsers !=null && ajaxresp[i].activeUsers>0){
									str+='<td class="text-capital">'+ajaxresp[i].activeUsers+'</td>';			
								}else{
									str+='<td class="text-capital"> - </td>';	
								}								
								/*if(ajaxresp[i].inActiveUsers !=null && ajaxresp[i].inActiveUsers>0){
									str+='<td class="text-capital">'+ajaxresp[i].inActiveUsers+'</td>';					
								}else{
									str+='<td class="text-capital"> - </td>';	
								}*/									
								if(ajaxresp[i].totalHouseHolds !=null && ajaxresp[i].totalHouseHolds>0){
									str+='<td class="text-capital">'+ajaxresp[i].totalHouseHolds+'</td>';				
								}else{
									str+='<td class="text-capital"> - </td>';	
								}	

								if(ajaxresp[i].visitedHouseHolds !=null && ajaxresp[i].visitedHouseHolds>0){
									str+='<td class="text-capital">'+ajaxresp[i].visitedHouseHolds+'</td>';				
								}else{
									str+='<td class="text-capital"> - </td>';	
								}								
								
								if(ajaxresp[i].vistedPerc !=null && ajaxresp[i].vistedPerc>0){
									if(ajaxresp[i].vistedPerc =="100.00"){
									str+='<td class="text-capital">100 %</td>';	
									}else{
										str+='<td class="text-capital">'+ajaxresp[i].vistedPerc+' %</td>';	
									}	
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								
								/* if(ajaxresp[i].pendingHouseHolds !=null && ajaxresp[i].pendingHouseHolds>0){
									str+='<td class="text-capital">'+ajaxresp[i].pendingHouseHolds+'</td>';				
								}else{
									str+='<td class="text-capital"> - </td>';	
								} */
								
								if(ajaxresp[i].pendingPerc !=null && ajaxresp[i].pendingPerc>0){
									if(ajaxresp[i].pendingPerc =="100.00"){
									str+='<td class="text-capital">100 %</td>';	
									}else{
										str+='<td class="text-capital">'+ajaxresp[i].pendingPerc+' %</td>';	
									}	
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								if(ajaxresp[i].totalGrievances !=null && ajaxresp[i].totalGrievances>0){
									str+='<td class="text-capital">'+ajaxresp[i].totalGrievances+'</td>';	
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								/* if(ajaxresp[i].individual !=null && ajaxresp[i].individual>0){
									str+='<td class="text-capital">'+ajaxresp[i].individual+'</td>';	
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								if(ajaxresp[i].community !=null && ajaxresp[i].community>0){
									str+='<td class="text-capital">'+ajaxresp[i].community+'</td>';		
								}else{
									str+='<td class="text-capital"> - </td>';	
								} */
								if(ajaxresp[i].totalImagesReceived !=null && ajaxresp[i].totalImagesReceived>0){
									str+='<td class="text-capital">'+ajaxresp[i].totalImagesReceived+'</td>';		
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								if(ajaxresp[i].callsCunt !=null && ajaxresp[i].callsCunt>0){
									str+='<td class="text-capital">'+ajaxresp[i].callsCunt+'</td>';		
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								if(ajaxresp[i].smsCount !=null && ajaxresp[i].smsCount>0){
									str+='<td class="text-capital">'+ajaxresp[i].smsCount+'</td>';		
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
								if(ajaxresp[i].flagHoistingImgCunt !=null && ajaxresp[i].flagHoistingImgCunt>0){
									str+='<td class="text-capital flagHoistingImagesCls" attr_level_value="'+ajaxresp[i].id+'" attr_level_id="'+locationType+'">'+ajaxresp[i].flagHoistingImgCunt+'</td>';		
								}else{
									str+='<td class="text-capital"> - </td>';	
								}
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			$("#campaign"+locationType).html(str);
			
			if(locationType !="district"){
				$(".dataTable"+locationType).dataTable({
					"iDisplayLength": 15,
					"aaSorting": [],
					"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
					"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
					"<'row'<'col-sm-12'tr>>" +
					"<'row'<'col-sm-5'i><'col-sm-7'p>>",
					buttons: [
						{
							extend:    'csvHtml5',
							text:      '<i class="fa fa-file-text-o"></i>',
							titleAttr: 'CSV',
						}
					]
				});
			}else{
				$(".dataTable"+locationType).dataTable({
					"paging":   false,
					"info":     false,
					"searching": true,
					"autoWidth": true,
					"iDisplayLength": 15,
					"aaSorting": [],
					"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
					"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
					"<'row'<'col-sm-12'tr>>" +
					"<'row'<'col-sm-5'i><'col-sm-7'p>>",
					buttons: [
						{
							extend:    'csvHtml5',
							text:      '<i class="fa fa-file-text-o"></i>',
							titleAttr: 'CSV',
						}
					]
				});
			}
		}
}


function getDepartmentIssueWiseGrievanceCounts(value){
	$("#departmentWiseGrievanceDivId").html(spinner);
	
	
	var jsObj={
    	levelIds		:globallevelIds,
		levelValues		:globallevelValues,
		startDateStr	:glStartDate,
		endDateStr  	:glEndDate
    }
    $.ajax({
      type : "POST",
      url : "getDepartmentIssueWiseGrievanceCountsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildDepartmentWiseGrievanceCounts(result,value);
		}else{
			$("#departmentWiseGrievanceDivId").html("No Data Available");
		}
		
	});
}
	
function getDepartmentSubIssueWiseGrievanceCounts(value){
	$("#departmentWiseGrievanceDivId").html(spinner);	
		
		
		var jsObj={
			levelIds		:globallevelIds,
			levelValues		:globallevelValues,
			startDateStr	:glStartDate,
			endDateStr  	:glEndDate
		}
		$.ajax({
		  type : "POST",
		  url : "getDepartmentSubIssueWiseGrievanceCountsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				return buildDepartmentWiseGrievanceCounts(result,value);
			}else{
				$("#departmentWiseGrievanceDivId").html("No Data Available");
			}
			
		});
}

$(document).on("click",".deptIssueCls",function(){
	var value = $(this).val();
	if(value == 1)
		getDepartmentWiseGrievanceCounts(value);
	else if(value == 2)
		getDepartmentIssueWiseGrievanceCounts(value);
	else if(value == 3)
		getDepartmentSubIssueWiseGrievanceCounts(value);
});

 function getDepartmentWiseGrievanceCounts(value){
	$("#departmentWiseGrievanceDivId").html(spinner);
	
	
	var jsObj={
		levelIds		:globallevelIds,
		levelValues		:globallevelValues,
		startDateStr	:glStartDate,
		endDateStr  	:glEndDate
	}
	$.ajax({
	  type : "POST",
	  url : "getDepartmentWiseGrievanceCountsAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			return buildDepartmentWiseGrievanceCounts(result,value);
		}else{
			$("#departmentWiseGrievanceDivId").html("No Data Available");
		}
		
	});
 }
	
function buildDepartmentWiseGrievanceCounts(result,value){
		
		var str='';
					/* str+='<div class="col-sm-12">';
						str+='<div class="dt-buttons generateExcel pull-right"><a class="dt-button buttons-csv buttons-html5" tabindex="0" aria-controls="DataTables_Table_0"  title="CSV"><span><i class="fa fa-file-text-o"></i></span></a></div>';
					str+='</div>'; */
					str+='<div class="col-sm-12">';
					  str+='<div class="table-responsive m_top5">';
							str+='<table class="table table-noborder table-condensed dataTableDepartmentWise">';
								str+='<thead>';
									str+='<th class="class="text-capital"">Department Name</th>';
									if(value == 2 || value == 3){
										str+='<th class="class="text-capital"">Issue Type</th>';
									}
									if(value == 3){
										str+='<th class="class="text-capital"">Sub Issue Type</th>';
									}
									str+='<th class="class="text-capital"">Total Grievances</th>';
									str+='<th class="class="text-capital"">Individual</th>';
									str+='<th class="class="text-capital"">Community</th>';
								str+='</thead>';
								str+='<tbody>';
									for(var i in result){
										str+='<tr>';
										 if(value == 1){
											if(result[i].name !=null && result[i].name.length>45){
												str+='<td><span class="tooltipDeptCls" data-toogle="tooltip" title="'+result[i].name+'" style="cursor:pointer;" data-placement="right">'+result[i].name.substr(0,45)+'...</span></td>';
											}else{
												str+='<td>'+result[i].name+'</td>';
											}
										}else{
											if(result[i].name !=null && result[i].name.length>25){
												str+='<td><span class="tooltipDeptCls" data-toogle="tooltip" title="'+result[i].name+'" style="cursor:pointer;" data-placement="right">'+result[i].name.substr(0,25)+'...</span></td>';
											}else{
												str+='<td>'+result[i].name+'</td>';
											}
										}
											
											if(value == 2 || value == 3){
												if(result[i].issueType !=null && result[i].issueType.length>15){
													str+='<td><span class="tooltipDeptCls" data-toogle="tooltip" title="'+result[i].issueType+'" style="cursor:pointer;" data-placement="top">'+result[i].issueType.substr(0,15)+'...</span></td>';
												}else{
													str+='<td>'+result[i].issueType+'</td>';
												}
												
											}
												
											if(value == 3){
												if(result[i].subIssueType !=null && result[i].subIssueType.length>10){
													str+='<td><span class="tooltipDeptCls" data-toogle="tooltip" title="'+result[i].subIssueType+'" style="cursor:pointer;" data-placement="top">'+result[i].subIssueType.substr(0,10)+'...</span></td>';
												}else{
													str+='<td>'+result[i].subIssueType+'</td>';
												}
												
											}
												
											if(result[i].totalGrievances !=null && result[i].totalGrievances>0){
												str+='<td>'+result[i].totalGrievances+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].individual !=null && result[i].individual>0){
												str+='<td>'+result[i].individual+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].community !=null && result[i].community>0){
												str+='<td>'+result[i].community+'</td>';
											}else{
												str+='<td> - </td>';
											}
										str+='</tr>';
									}
								str+='</tbody>';
							str+='</table>';
							str+='</div>';
							//export Excel Start
							str+='<table class="table table-noborder table-condensed" id="deptWiseReportTable" style="display:none;">';
								str+='<thead>';
									str+='<th class="class="text-capital"">Department Name</th>';
									if(value == 2 || value == 3)
										str+='<th class="class="text-capital"">Issue Type</th>';
									if(value == 3)
										str+='<th class="class="text-capital"">Sub Issue Type</th>';
									str+='<th class="class="text-capital"">Total Grievances</th>';
									str+='<th class="class="text-capital"">Individual</th>';
									str+='<th class="class="text-capital"">Community</th>';
								str+='</thead>';
								str+='<tbody>';
									for(var i in result){
										str+='<tr>';
											str+='<td>'+result[i].name+'</td>';
											
											if(value == 2 || value == 3)
												str+='<td>'+result[i].issueType+'</td>';
											if(value == 3)
												str+='<td>'+result[i].subIssueType+'</td>';
											if(result[i].totalGrievances !=null && result[i].totalGrievances>0){
												str+='<td>'+result[i].totalGrievances+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].individual !=null && result[i].individual>0){
												str+='<td>'+result[i].individual+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].community !=null && result[i].community>0){
												str+='<td>'+result[i].community+'</td>';
											}else{
												str+='<td> - </td>';
											}
										str+='</tr>';
									}
								str+='</tbody>';
							str+='</table>';
							//export Excel End
		$("#departmentWiseGrievanceDivId").html(str);
		$(".tooltipDeptCls").tooltip();
		$(".dataTableDepartmentWise").dataTable({
			"iDisplayLength": 16,
			"aaSorting": [],
			"aLengthMenu": [[16, 20, -1], [16, 20, "All"]],
			"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					//extend:    'csvHtml5',
					text:      '<i class="fa fa-file-text-o generateExcel"></i>',
					//titleAttr: 'CSV',
				}
			]
		});
		
	}

 function setDefaultImage(img){
	img.src = "D2D_Assests/images/NoImage.png";
}

function getLevelWiseCount(locationType)
{
	$("#campignLevel"+locationType).html(spinner);
	
	
	var jsObj={
		locationType    : locationType,
		levelIds		:globallevelIds,
		levelValues		:globallevelValues,
		startDateStr    : glStartDate,
		endDateStr      :  glEndDate
	}
	$.ajax({
	  type : "POST",
	  url : "getLevelWiseCountAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null){
			buildLevelWiseCount(result,locationType);
		}
		
	});
	
	
}

function buildLevelWiseCount(ajaxresp,locationType){
	var str='';
		str+='<div class="col-sm-12 m_top20">';
		if(locationType == "district"){
			str+='<h4 style="color:#640095;font-size: 21px;">District Level</h4>';
		}else if(locationType == "parliament"){
			str+='<h4 style="color:red;font-size: 21px;">Parliament Level</h4>';
		}else if(locationType == "constituency"){
			str+='<h4 style="color:#02AFAC;font-size: 21px;">Constituency Level</h4>';
		}else if(locationType == "mandal"){
			str+='<h4 style="color:#A17B13;font-size: 21px;">Mandal Level</h4>';
		}else if(locationType == "panchayat"){
			str+='<h4 style="color:#640095;font-size: 21px;">Panchayat Level</h4>';
		}else if(locationType == "muncipality"){
			str+='<h4 style="color:#F45CB5;font-size: 21px;">Muncipality/Corporation Level</h4>';
		}
		str+='</div>';
			str+='<div class="col-sm-4 text-center">';
				str+='<h5 >Total</h5>';
				str+='<h4 class="m_top5">'+ajaxresp.totalCount+'</h4>';
			str+='</div>';
			str+='<div class="col-sm-4 text-center">';
				str+='<h5>Started</h5>';
				var percentage=0;
				percentage = (ajaxresp.startedCount/ajaxresp.totalCount*100).toFixed(2);
				str+='<h4 class="m_top5">'+ajaxresp.startedCount+' <small style="color:">'+percentage+' %</small></h4>';
			str+='</div>';
			 str+='<div class="col-sm-4 text-center">';
				str+='<h5>Not Started</h5>';
				str+='<h4 class="m_top5" attr_ids="'+ajaxresp.notVistedLst+'" attr_location_type="'+locationType+'">'+ajaxresp.notStartedCount+'</h4>';
			str+='</div>'; 
			
			$("#campignLevel"+locationType).html(str);
			
		
}
function getCampaignCountFrMandalPancMuncip(locationType)
{
	$("#campignLevel"+locationType).html(spinner);
	
	
	var jsObj={
		locationType : locationType,
		levelIds		:globallevelIds,
		levelValues		:globallevelValues,
		startDateStr: glStartDate,
		endDateStr :  glEndDate
	}
	$.ajax({
	  type : "POST",
	  url : "getCampaignCountFrMandalPancMuncipAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null){
			buildLevelWiseCount(result,locationType);
		}
		
	});
	
	
}
$(document).on("click",".generateExcel",function(){
	tableToExcel('deptWiseReportTable', 'Department wise Grievance');
});
function getDistrictsForStateAction(divId)
{
	
	var jsObj={
		stateId : 1,
	}
	$.ajax({
	  type : "POST",
	  url : "getDistrictsForStateAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			var str='';
			for(var i in result){
				if(result[i].id !=517){
					 str+='<option value="'+result[i].id+'">'+result[i].name+' </option>';
				}
				
			}
			$("#"+divId).html(str);
			$("#"+divId).trigger('chosen:updated');
		}
		
	});
}	

$(document).on("change","#consWiseDistritsId",function(){
	var districtId = $(this).val();
	
	if(districtId == 0){
		globallevelChangeIds=[2];
		globallevelValuesChange=[1];
	}else{
		globallevelIds=[3];
		globallevelValues.push(parseInt(districtId))
	}	
	getLocationWiseCountDetails("constituency","onChange");
});

$(document).on("click",".imagesTypeCls",function(){
	
	var imageType = $(this).val();
	getRecentImagesList(imageType);
});