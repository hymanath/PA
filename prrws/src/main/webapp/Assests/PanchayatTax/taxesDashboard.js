var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD-MM-YYYY");
var globallocationId =0;
var globallocationType = 2;
var globallocationlevelIdType=1;
var globallocationDistrictName='';
var globallocationConsName='';
var globallocationMandalName='';
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
$("#dateRangePicker").daterangepicker({
		opens: 'left',
		startDate: currentFromDate,
		endDate: currentToDate,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		ranges: {
		   'All':[moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY"), moment().add(10, 'years').endOf('year').format("DD-MM-YYYY")],
		   'Today' : [moment(), moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()]
		}
	});
	var dates= $("#dateRangePicker").val();
	var pickerDates = currentFromDate+' - '+currentToDate
	if(dates == pickerDates)
	{
		$("#dateRangePicker").val('All');
	}
	
	$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
		currentFromDate = picker.startDate.format('DD-MM-YYYY');
		currentToDate = picker.endDate.format('DD-MM-YYYY');
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePicker").val('All');
		}
	
		onloadTaxCalls();
	});
onloadTaxCalls();
function onloadTaxCalls(){
	getTaxesAndCategoryWiseOverViewDetails();
	getTaxesIndicatorDetails();
	getTaxesDefaultOverviewDetails();
	
		if(globallocationType == 2){
			getAllSubLocationsTax(2,1,'',"district");
		}else if(globallocationType == 3){
			$(".locationLevelCls").val('district').trigger("chosen:updated");
			$(".districtCls").show();
			$(".constituencyCls").hide();
			$(".mandalCls").hide();
			
			$("#districtId").html('');
			$("#districtId").append("<option value="+globallocationId+">"+globallocationDistrictName+" </option>");
			$("#districtId").trigger("chosen:updated");
			//getAllSubLocationsTax(globallocationType,globallocationlevelIdType,"constituency","constituency");
		}else if(globallocationType == 4){
			$(".locationLevelCls").val('assembly').trigger("chosen:updated");
			$(".districtCls").show();
			$(".constituencyCls").show();
			$(".mandalCls").hide();
			
			$("#districtId").html('');
			$("#districtId").append("<option value="+globallocationId+">"+globallocationDistrictName+" </option>");
			$("#districtId").trigger("chosen:updated");
			
			$("#constituencyId").html('');
			$("#constituencyId").append("<option value="+globallocationId+">"+globallocationConsName+" </option>");
			$("#constituencyId").trigger("chosen:updated");
			
			//getAllSubLocationsTax(globallocationType,globallocationlevelIdType,'',"mandal");
		}else if(globallocationType == 5){
			$(".locationLevelCls").val('assembly').trigger("chosen:updated");
			
			$(".districtCls").show();
			$(".constituencyCls").show();
			$(".mandalCls").show();
			
			$("#districtId").html('');
			$("#districtId").append("<option value="+globallocationId+">"+globallocationDistrictName+" </option>");
			$("#districtId").trigger("chosen:updated");
			
			$("#constituencyId").html('');
			$("#constituencyId").append("<option value="+globallocationId+">"+globallocationConsName+" </option>");
			$("#constituencyId").trigger("chosen:updated");
			
			$("#mandalId").html('');
			$("#mandalId").append("<option value="+globallocationId+">"+globallocationMandalName+" </option>");
			$("#mandalId").trigger("chosen:updated");
			
			//getAllSubLocationsTax(globallocationType,globallocationlevelIdType,'',"mandal");
		}
		
	
	
		if(globallocationType == 2){
			getPanchyatTaxDashboardFilterWiseDetails(0,0,'','all','all','district',0,'','');
		}else if(globallocationType == 3){
			getPanchyatTaxDashboardFilterWiseDetails(0,0,'','all','all','district',globallocationId,'','');
		}else if(globallocationType == 4){
			getPanchyatTaxDashboardFilterWiseDetails(0,0,'','all','all','assembly',globallocationId,'','');
		}else if(globallocationType == 5){
			getPanchyatTaxDashboardFilterWiseDetails(0,0,'','all','all','mandal',globallocationId,'','');
		}
		
	
	
	
}	

function getTaxesAndCategoryWiseOverViewDetails(){
	$("#completeOverviewDivId").html(spinner);
	$("#categoryTaxOverViewDivId").html(spinner);
	$("#categoryFeeOverViewDivId").html(spinner);
	var locationType='';
	var locationId='';
	if(globallocationType == 2 || globallocationType == 1){
		locationType='district';
		locationId=0;
	}else if(globallocationType == 3){
		locationType='district';
		locationId = globallocationId;
	}else if(globallocationType == 4){
		locationType='assembly';
		locationId = globallocationId;
	}else if(globallocationType == 4){
		locationType='mandal';
		locationId = globallocationId;
	}
	var json = {
		locationId : locationId,
		locationType : locationType,
		fromDate : currentFromDate,
		toDate : currentToDate
	};
	$.ajax({                
		type:'POST',    
		url: 'getTaxesAndCategoryWiseOverViewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null){
			buildCompleteOverviewDetails(result);
		}else{
			$("#completeOverviewDivId").html('No Data Available.');
		}
	});
}

function buildCompleteOverviewDetails(result){
	var str='';
	var str1='';
	var str2='';
	
	str+='<div class="col-sm-4 m_top10">';
		str+='<div class="panel" style="text-align:center; background-color:#3393CD;">';
			str+='<div class="panel-heading">';
				str+='<h4 class="white_color">Demand</h4>';
			str+='</div>';
			str+='<div class="panel-body" style="background-color:#2277AA;">';
				str+='<div class="row">';
					str+='<div class="Pad_14_10">';
						str+='<h2 class="white_color">'+result.totalDemandAmount+'</h2>';
						str+='<h5 class="m_top5 font_weight">Units:&nbsp;'+result.totalDemandUnits+'</h5>';
						str+='<hr class="m_top_bottom">';
						str+='<div class="row">';
							str+='<div class="col-sm-6 Bor_rgt_white">';
								str+='<h5 class="font_weight m_top5">Arrears</h5>';
								str+='<h5 class="font_weight m_top5 white_color">'+result.demandArrearAmount+'&nbsp;<!--<small class="white_color font_weight">Cr</small>--></h5>';
								str+='<h6 class="font_weight m_top5">Units:&nbsp;'+result.demandArrearUnits+'</h6>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<h5 class="font_weight m_top5">Current</h5>';
								str+='<h5 class="font_weight m_top5 white_color">'+result.demandCurrentAmount+'&nbsp;<!--<small class="white_color font_weight">Cr</small>--></h5>';
								str+='<h6 class="font_weight m_top5">Units:&nbsp;'+result.demandCurrentUnits+'</h6>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
				str+='</div>';	
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-4 m_top10">';
		str+='<div class="panel" style="text-align:center; background-color:#74B747;">';
			str+='<div class="panel-heading">';
				str+='<h4 class="white_color">Collection</h4>';
			str+='</div>';
			str+='<div class="panel-body" style="background-color:#5D9440;">';
				str+='<div class="row">';
					str+='<div class="Pad_14_10">';
						str+='<h2 class="white_color">'+result.totalCollectionAmount+'</h2>';
						str+='<h5 class="m_top5 font_weight">Units:&nbsp;'+result.totalCollectionUnts+'</h5>';
						str+='<hr class="m_top_bottom">';
						str+='<div class="row">';
							str+='<div class="col-sm-6 Bor_rgt_white">';
								str+='<h5 class="font_weight m_top5">Arrears</h5>';
								str+='<h5 class="font_weight m_top5 white_color">'+result.collectionArrearAmount+'&nbsp;<!--<small class="white_color font_weight">Cr</small>--></h5>';
								str+='<h6 class="font_weight m_top5">Units:&nbsp;'+result.collectionArrearUnits+'</h6>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<h5 class="font_weight m_top5">Current</h5>';
								str+='<h5 class="font_weight m_top5 white_color">'+result.collectionCurrentAmount+'&nbsp;<!--<small class="white_color font_weight">Cr</small>--></h5>';
								str+='<h6 class="font_weight m_top5">Units:&nbsp;'+result.collectionCurrentUnits+'</h6>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
				str+='</div>';	
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-4 m_top10">';
		str+='<div class="panel" style="text-align:center; background-color:#F26A69;">';
			str+='<div class="panel-heading">';
				str+='<h4 class="white_color">Balance</h4>';
			str+='</div>';
			str+='<div class="panel-body" style="background-color:#F1595B;">';
				str+='<div class="row">';
					str+='<div class="Pad_14_10">';
						str+='<h2 class="white_color">'+result.totalBalanceAmount+'</h2>';
						str+='<h5 class="m_top5 font_weight">Units:&nbsp;'+result.totalBalanceUnits+'</h5>';
						str+='<hr class="m_top_bottom">';
						str+='<div class="row">';
							str+='<div class="col-sm-6 Bor_rgt_white">';
								str+='<h5 class="font_weight m_top5">Arrears</h5>';
								str+='<h5 class="font_weight m_top5 white_color">'+result.balanceArrearAmount+'&nbsp;<!--<small class="white_color font_weight">Cr</small>--></h5>';
								str+='<h6 class="font_weight m_top5">Units:&nbsp;'+result.balanceArrearUnits+'</h6>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<h5 class="font_weight m_top5">Current</h5>';
								str+='<h5 class="font_weight m_top5 white_color">'+result.balanceCurentAmount+'&nbsp;<!--<small class="white_color font_weight">Cr</small>--></h5>';
								str+='<h6 class="font_weight m_top5">Units:&nbsp;'+result.balanceCurrentUnits+'</h6>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
				str+='</div>';	
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#completeOverviewDivId").html(str);
	
	if(result.taxesList != null && result.taxesList.length > 0){
		for(var i in result.taxesList){
			str1+='<div class="col-sm-4">';
				str1+='<div class="panel panel-default m_top10" style="background-color:#FCFAFA;">';
					str1+='<div class="panel-body" style="box-shadow:0px 0px 2px rgba(0,0,0,0.4);">';
						str1+='<div class="row">';
							str1+='<div class="col-sm-6">';
								str1+='<div class="media">';
									str1+='<div class="media-left imgCss">';
									if(result.taxesList[i].name != null && result.taxesList[i].name == 'House'){
										str1+='<img src="Assests/images/icon-home.png" class="media-object" style="width:30px">';
									}else if(result.taxesList[i].name != null && result.taxesList[i].name == 'Kolagaaram'){
										str1+='<img src="Assests/images/icon-kolagaaram.png" class="media-object" style="width:30px">';
									}else if(result.taxesList[i].name != null && result.taxesList[i].name == 'Advertisement'){
										str1+='<img src="Assests/images/icon-advertisement.png" class="media-object" style="width:30px">';
									}	
									str1+='</div>';
									str1+='<div class="media-body">';
										str1+='<h5 class="media-heading m_top10 margin_left_5 font_weight">'+result.taxesList[i].name+'</h5>';
									str1+='</div>';
								str1+='</div>';
							str1+='</div>';
							str1+='<div class="col-sm-6">';
								str1+='<div class="pull-right ">';
									str1+='<div class="boxCss">';
										str1+='<h5 class="font_weight">Total Units</h5>';
										str1+='<h5 class="font_weight m_top5 Demand_Clr text-center">'+result.taxesList[i].totalDemandUnits+'</h5>';
									str1+='</div>';	
								str1+='</div>';
							str1+='</div>';
						str1+='</div>';
						str1+='<div class="row">';
							str1+='<div class="col-sm-4 m_top15">';
								str1+='<div class="media">';
									str1+='<div class="media-left">';
										str1+='<img src="Assests/images/blue-strip.png" class="media-object" style="height:97px;width: 8px;">';
									str1+='</div>';
									str1+='<div class="media-body">';
										str1+='<h6 class="font_weight">Demand</h6>';
										str1+='<h5 class="font_weight m_top20 Demand_Clr">'+result.taxesList[i].totalDemandAmount+'<small style="color:#141F27;"><b>&nbsp;Cr</b></small></h5>';
									str1+='</div>';
								str1+='</div>';
							str1+='</div>';
							str1+='<div class="col-sm-4 m_top15">';
								str1+='<div class="media" style="overflow:unset;">';
									str1+='<div class="media-left">';
										str1+='<img src="Assests/images/green-strip.png" class="media-object" style="height:97px;width: 8px;">';
									str1+='</div>';
									str1+='<div class="media-body">';
										str1+='<h6 class="font_weight">Collection</h6>';
										str1+='<h5 class="font_weight m_top10 Collection_clr">'+result.taxesList[i].totalCollectionAmount+'<small style="color:#141F27;">&nbsp;Cr</small></h5>';
										str1+='<h6 class="font_weight m_top5 Collection_clr">('+result.taxesList[i].collectionAmuntPerc+'%)</h6>';
										str1+='<h6 class="font_weight m_top10">Units</h6>';
										str1+='<h6 class="font_weight m_top5 Collection_clr">'+result.taxesList[i].totalCollectionUnts+' <small>&nbsp;('+result.taxesList[i].collectionUnitsPerc+'%)</small></h6>';
									str1+='</div>';
								str1+='</div>';
							str1+='</div>';
							str1+='<div class="col-sm-4 m_top15">';
								str1+='<div class="media" style="overflow:unset;">';
									str1+='<div class="media-left">';
										str1+='<img src="Assests/images/red-strip.png" class="media-object" style="height:97px;width: 8px;">';
									str1+='</div>';
									str1+='<div class="media-body">';
										str1+='<h6 class="font_weight">Balance</h6>';
										str1+='<h5 class="font_weight m_top10 Balance_Clr">'+result.taxesList[i].totalBalanceAmount+'<small style="color:#141F27;">&nbsp;Cr</small></h5>';
										str1+='<h6 class="font_weight m_top5 Balance_Clr">('+result.taxesList[i].balAmuntPerc+'%)</h6>';
										str1+='<h6 class="font_weight m_top10">Units</h6>';
										str1+='<h6 class="font_weight m_top5 Balance_Clr">'+result.taxesList[i].totalBalanceUnits+'<small>&nbsp;('+result.taxesList[i].balUnitsPerc+'%)</small></h6>';
									str1+='</div>';
								str1+='</div>';
							str1+='</div>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>';
			$("#categoryTaxOverViewDivId").html(str1);
		}
	}else{
		$("#categoryTaxOverViewDivId").html('No Data Available.');
	}
	
	if(result.feeList != null && result.feeList.length > 0){
	for(var j in result.feeList){
	str2+='<div class="col-sm-3">';
		str2+='<div class="panel panel-default m_top10" style="background-color:#FCFAFA;">';
			str2+='<div class="panel-body" style="box-shadow:0px 0px 2px rgba(0,0,0,0.4);">';
				str2+='<div class="row">';
					str2+='<div class="col-sm-6">';
						str2+='<div class="media">';
							str2+='<div class="media-left imgCss">';
							if(result.feeList[j].name != null && result.feeList[j].name == 'Private Tap'){
								str2+='<img src="Assests/images/icon-private-tap-fee.png" class="media-object" style="width:30px">';
							}else if(result.feeList[j].name != null && result.feeList[j].name == 'Auctions'){
								str2+='<img src="Assests/images/icon-auction.png" class="media-object" style="width:30px">';
							}else if(result.feeList[j].name != null && result.feeList[j].name == 'Trade License'){
								str2+='<img src="Assests/images/icon-trade.png" class="media-object" style="width:30px">';
							}else if(result.feeList[j].name != null && result.feeList[j].name == 'Others'){
								str2+='<img src="Assests/images/icon-others.png" class="media-object" style="width:30px">';
							}	
							str2+='</div>';
							str2+='<div class="media-body">';
								str2+='<h5 class="media-heading m_top10 margin_left_5 font_weight">'+result.feeList[j].name+'</h5>';
							str2+='</div>';
						str2+='</div>';
					str2+='</div>';
					str2+='<div class="col-sm-6">';
						str2+='<div class="pull-right ">';
							str2+='<div class="boxCss">';
								str2+='<h5 class="font_weight">Total Units</h5>';
								str2+='<h5 class="font_weight m_top5 Demand_Clr text-center">'+result.feeList[j].totalDemandUnits+'</h5>';
							str2+='</div>';	
						str2+='</div>';
					str2+='</div>';	
				str2+='</div>';
				str2+='<div class="row">';
					str2+='<div class="col-sm-4 m_top15">';
						str2+='<div class="media">';
							str2+='<div class="media-left">';
								str2+='<img src="Assests/images/blue-strip.png" class="media-object" style="height:97px;width: 8px;">';
							str2+='</div>';
							str2+='<div class="media-body">';
								str2+='<h6 class="font_weight">Demand</h6>';
								str2+='<h5 class="font_weight m_top20 Demand_Clr">'+result.feeList[j].totalDemandAmount+'<small style="color:#141F27;"><b>&nbsp;Cr</b></small></h5>';
							str2+='</div>';
						str2+='</div>';
					str2+='</div>';
					str2+='<div class="col-sm-4 m_top15">';
						str2+='<div class="media" style="overflow:unset;">';
							str2+='<div class="media-left">';
								str2+='<img src="Assests/images/green-strip.png" class="media-object" style="height:97px;width: 8px;">';
							str2+='</div>';
							str2+='<div class="media-body">';
								str2+='<h6 class="font_weight">Collection</h6>';
								str2+='<h5 class="font_weight m_top10 Collection_clr">'+result.feeList[j].totalCollectionAmount+'<small style="color:#141F27;">&nbsp;Cr</small></h5>';
								str2+='<h6 class="font_weight m_top5 Collection_clr">('+result.feeList[j].collectionAmuntPerc+'%)</h6>';
								str2+='<h6 class="font_weight m_top10">Units</h6>';
								str2+='<h6 class="font_weight m_top5 Collection_clr">'+result.feeList[j].totalCollectionUnts+' <small>&nbsp;('+result.feeList[j].collectionUnitsPerc+'%)</small></h6>';
							str2+='</div>';
						str2+='</div>';
					str2+='</div>';
					str2+='<div class="col-sm-4 m_top15">';
						str2+='<div class="media" style="overflow:unset;">';
							str2+='<div class="media-left">';
								str2+='<img src="Assests/images/red-strip.png" class="media-object" style="height:97px;width: 8px;">';
							str2+='</div>';
							str2+='<div class="media-body">';
								str2+='<h6 class="font_weight">Balance</h6>';
								str2+='<h5 class="font_weight m_top10 Balance_Clr">'+result.feeList[j].totalBalanceAmount+'<small style="color:#141F27;">&nbsp;Cr</small></h5>';
								str2+='<h6 class="font_weight m_top5 Balance_Clr">('+result.feeList[j].balAmuntPerc+'%)</h6>';
								str2+='<h6 class="font_weight m_top10">Units</h6>';
								str2+='<h6 class="font_weight m_top5 Balance_Clr">'+result.feeList[j].totalBalanceUnits+' <small>&nbsp;('+result.feeList[j].balUnitsPerc+'%)</small></h6>';
							str2+='</div>';
						str2+='</div>';
					str2+='</div>';
				str2+='</div>';	
			str2+='</div>';
		str2+='</div>';	
	str2+='</div>';
	$("#categoryFeeOverViewDivId").html(str2);
	}
  }else{
	$("#categoryFeeOverViewDivId").html('No Data Available.');
  }
}

function getTaxesIndicatorDetails(){
	$("#indicatorsOverviewDivId").html(spinner);
	var locationType='';
	var locationId='';
	if(globallocationType == 2 || globallocationType == 1){
		locationType='district';
		locationId=0;
	}else if(globallocationType == 3){
		locationType='district';
		locationId = globallocationId;
	}else if(globallocationType == 4){
		locationType='assembly';
		locationId = globallocationId;
	}else if(globallocationType == 4){
		locationType='mandal';
		locationId = globallocationId;
	}
	var json = {
		locationId : locationId,
		locationType : locationType,
		fromDate : currentFromDate,
		toDate : currentToDate
	};
	$.ajax({                
		type:'POST',    
		url: 'getTaxesIndicatorDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildIndicatorDetails(result);
		}else{
			$("#indicatorsOverviewDivId").html('No Data Available.');
		}
	});
}

function buildIndicatorDetails(result){
	var str = '';
	str+='<ul class="list-inline sliderNewCls">';
	for(var i in result){
	str+='<li class="">';
		str+='<div class="panel panel-default m_top10" style="background-color:#FCFAFA;">';
			str+='<div class="panel-body" style="box-shadow:0px 0px 2px rgba(0,0,0,0.4);">';
				str+='<div class="row">';
					str+='<div class="col-sm-6">';
						str+='<h5 class="media-heading m_top10 margin_left_5 font_weight">'+result[i].name+'</h5>';
					str+='</div>';
					str+='<div class="col-sm-6">';
						str+='<div class="pull-right ">';
							str+='<div class="boxCss">';
								str+='<h5 class="font_weight">Total Units</h5>';
								str+='<h5 class="font_weight m_top5 Demand_Clr text-center">'+result[i].totalDemandUnits+'</h5>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="row">';
					str+='<div class="col-sm-4 m_top15">';
						str+='<div class="media">';
							str+='<div class="media-left">';
								str+='<img src="Assests/images/blue-strip.png" class="media-object" style="height:97px;width: 8px;">';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h6 class="font_weight">Demand</h6>';
								str+='<h5 class="font_weight m_top20 Demand_Clr">'+result[i].totalDemandAmount+'<small style="color:#141F27;"><b>&nbsp;Cr</b></small></h5>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4 m_top15" style="padding-right: 0px;">';
						str+='<div class="media" style="overflow:unset;">';
							str+='<div class="media-left">';
								str+='<img src="Assests/images/green-strip.png" class="media-object" style="height:97px;width: 8px;">';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h6 class="font_weight">Collection</h6>';
								str+='<h5 class="font_weight m_top10 Collection_clr">'+result[i].totalCollectionAmount+'<small style="color:#141F27;">&nbsp;Cr</small></h5>';
								str+='<h6 class="font_weight m_top5 Collection_clr">('+result[i].collectionAmuntPerc+'%)</h6>';
								str+='<h6 class="font_weight m_top10">Units</h6>';
								str+='<h6 class="font_weight m_top5 Collection_clr">'+result[i].totalCollectionUnts+' <small>&nbsp;('+parseFloat(result[i].collectionUnitsPerc).toFixed(0)+'%)</small></h6>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4 m_top15" style="padding-left: 0px;padding-right: 0px;">';
						str+='<div class="media" style="overflow:unset;">';
							str+='<div class="media-left">';
								str+='<img src="Assests/images/red-strip.png" class="media-object" style="height:97px;width: 8px;">';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h6 class="font_weight">Balance</h6>';
								str+='<h5 class="font_weight m_top10 Balance_Clr">'+result[i].totalBalanceAmount+'<small style="color:#141F27;">&nbsp;Cr</small></h5>';
								str+='<h6 class="font_weight m_top5 Balance_Clr">('+result[i].balAmuntPerc+'%)</h6>';
								str+='<h6 class="font_weight m_top10">Units</h6>';
								str+='<h6 class="font_weight m_top5 Balance_Clr">'+result[i].totalBalanceUnits+' <small>&nbsp;('+parseFloat(result[i].balUnitsPerc).toFixed(0)+'%)</small></h6>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';	
			str+='</div>';
		str+='</div>';	
	str+='</li>';
	}
	str+='</ul>';
	$("#indicatorsOverviewDivId").html(str);
	$(".sliderNewCls").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 4,
		slidesToScroll: 1,
		variableWidth: false,
		responsive: [
		{
		  breakpoint: 1024,
		  settings: {
			slidesToShow: 4,
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

function getTaxesDefaultOverviewDetails(){
	$("#defaulterOverviewDivId").html(spinner);
	var locationType='';
	var locationId='';
	if(globallocationType == 2 || globallocationType == 1){
		locationType='district';
		locationId=0;
	}else if(globallocationType == 3){
		locationType='district';
		locationId = globallocationId;
	}else if(globallocationType == 4){
		locationType='assembly';
		locationId = globallocationId;
	}else if(globallocationType == 4){
		locationType='mandal';
		locationId = globallocationId;
	}
	var json = {
		locationId : locationId,
		locationType : locationType,
	};
	$.ajax({                
		type:'POST',    
		url: 'getTaxesDefaultOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildDefaulterDetails(result);
		}else{
			$("#defaulterOverviewDivId").html('No Data Available.');
		}
	});
}

function buildDefaulterDetails(result){
	var str = '';
	for(var i in result){
		str+='<div class="col-sm-2 m_top10">';
			str+='<div class="panel panel-default" style="border: 2px solid #ccc;">';
				str+='<div class="panel-heading" style="background-color:#EAE6E7;padding-top: 5px;padding-bottom: 5px;">';
					str+='<h4 class="panel-title font_weight text-center">'+result[i].name+'</h4>';
				str+='</div>';
				str+='<div class="panel-body">';
					str+='<h4 class="font_weight Demand_Clr text-center">'+result[i].totalUnits+'&nbsp;&nbsp;&nbsp;<small class="Demand_Clr">Units</small></h4>';
					str+='<hr style="margin-top: 10px;margin-bottom: 10px;" class="">';
					str+='<h3 class="font_weight text-center">'+result[i].totalAmount+'&nbsp;Cr</h3>';
					str+='<h5 class="text-right Collection_clr m_top5 font_weight">'+result[i].percentage+'%</h5>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#defaulterOverviewDivId").html(str);
	}
}
$(document).on("change",".locationLevelCls",function(){
	var levelId = $(this).val();
	if(levelId == 'district'){
		$(".districtCls").show();
		$(".constituencyCls").hide();
		$(".mandalCls").hide();
		getAllSubLocationsTax(2,1,"","district");//All Districts
	}else if(levelId == 'assembly'){
		$(".districtCls").show();
		$(".constituencyCls").show();
		$(".mandalCls").hide();
		getAllSubLocationsTax(2,1,"","district");//All Districts
	}else if(levelId == 'mandal'){
		$(".districtCls").show();
		$(".constituencyCls").show();
		$(".mandalCls").show();
		getAllSubLocationsTax(2,1,"","district");//All Districts
	}/* else if(levelId == 'State'){
		$(".districtCls").hide();
		$(".constituencyCls").hide();
		$(".mandalCls").hide();
	} */
});
$(document).on("change","#districtId",function(){
	var levelValId = $(this).val();
	getAllSubLocationsTax(3,levelValId,"constituency","constituency");
});
$(document).on("change","#constituencyId",function(){
	var levelValId = $(this).val();
	getAllSubLocationsTax(4,levelValId,"","mandal");
});
$(document).on("change","#taxId",function(){
	var taxVal = $(this).val();
	if(taxVal !=0){
		 $('#feeId').prop('disabled', true).trigger("chosen:updated");
		 $('#feeId').removeClass("active")
		 $('#feeId').addClass("NotActive")
		 
		 $('#taxId').removeClass("NotActive")
		 $('#taxId').addClass("active")
	}else{
		 $('#feeId').prop('disabled', false).trigger("chosen:updated");
		 $('#feeId').removeClass("NotActive")
		 $('#feeId').addClass("active")
		 
		 $('#taxId').removeClass("NotActive")
		 $('#taxId').addClass("active")
	}
	
});
$(document).on("change","#feeId",function(){
	var taxVal = $(this).val();
	if(taxVal !=0){
		$('#taxId').prop('disabled', true).trigger("chosen:updated");
		$('#taxId').removeClass("active")
		$('#taxId').addClass("NotActive")
		
		$('#feeId').removeClass("NotActive")
		$('#feeId').addClass("active")
		 
	}else{
		$('#taxId').prop('disabled', false).trigger("chosen:updated");
		$('#taxId').removeClass("NotActive")
		$('#taxId').addClass("active")
		
		$('#feeId').removeClass("NotActive")
		$('#feeId').addClass("active")
	}
	
});
$(document).on("change",".defaulterCls",function(){
	var defaluerVal = $(this).val();
	$('.defaulterCls').not(this).prop('checked', false);
	if(defaluerVal == "defaulters"){
		$('#taxId').prop('disabled', true).trigger("chosen:updated");
		$('#feeId').prop('disabled', true).trigger("chosen:updated");
		if(!$(".defaulterCls").prop("checked")){
			$('#taxId').prop('disabled', false).trigger("chosen:updated");
			$('#feeId').prop('disabled', false).trigger("chosen:updated");
		}
		//$('.locationLevelCls').prop('disabled', true).trigger("chosen:updated");
		//$('.locationLevelCls').val('0').trigger("chosen:updated");
		//$(".districtCls").hide();
		//$(".constituencyCls").hide();
		//$(".mandalCls").hide();
	}else{
		$('#taxId').prop('disabled', false).trigger("chosen:updated");
		$('#feeId').prop('disabled', false).trigger("chosen:updated");
	//	$('#locationLevelCls').prop('disabled', true).trigger("chosen:updated");
		//$('.locationLevelCls').val('0').trigger("chosen:updated");
		//$(".districtCls").hide();
		//$(".constituencyCls").hide();
		//$(".mandalCls").hide();
	}
	/*if(!$(this).is(":checked") && !$(this).is(":checked")){
		$('.locationLevelCls').prop('disabled', false).trigger("chosen:updated");
		$('.locationLevelCls').val('0').trigger("chosen:updated");
		
	}*/
});
function getAllSubLocationsTax(levelId,levelValue,typeVal,appendDivId){
	if(appendDivId == "district"){
		$("#districtId").html('');
	}else if(appendDivId == "constituency"){
		$("#constituencyId").html('');
	}else if(appendDivId == "mandal"){
		$("#mandalId").html('');
	}
	var json = {
		searchLevelId :	levelId,
		searchLevelValue : levelValue,
		type :typeVal
	};
	$.ajax({                
		type:'POST',    
		url: 'getAllSubLocations',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildLocationsDetails(result,appendDivId);
		}
	});
}
function buildLocationsDetails(result,appendDivId){
	var str='';
	str+='<option value="0">All</option>';
	for(var i in result){
		str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}
	if(appendDivId == "district"){
		$("#districtId").html(str);
		$("#districtId").trigger("chosen:updated");
	}else if(appendDivId == "constituency"){
		$("#constituencyId").html(str);
		$("#constituencyId").trigger("chosen:updated");
	}else if(appendDivId == "mandal"){
		$("#mandalId").html(str);
		$("#mandalId").trigger("chosen:updated");
	}
}
$(document).on("click",".getDetailsCls",function(){
	var taxTypeId=$("#taxId").val();
	var feeTypeId=$("#feeId").val();
	
	var taxTypeName=$("#taxId option:selected").text();
	var feeTypeName=$("#feeId option:selected").text();
	
	var yearTypeVal='';
	var yearTypeArr = [];
	$('.yearTypeCls').each(function(i, obj){
		 if($(this).is(':checked')){
			yearTypeArr.push($(this).val());
		 }
	});
	if(yearTypeArr.length==2){
		yearTypeVal = '';
	}else{
		$('.yearTypeCls').each(function(i, obj){
		 if($(this).is(':checked')){
			yearTypeVal = $(this).val();
		 }
	});
	}
	var dataType = $('input[name=inlineRadioOptionsUnits]:checked').val();
	var defaultersType='';
	var defaultersTypeArr=[];
	$('.defaulterCls').each(function(i, obj){
		 if(!$(this).is(':checked')){
			defaultersTypeArr.push($(this).val());
		 }
	 });
	 if(defaultersTypeArr.length == 2){
		 defaultersType='all'
	 }else{
		 $('.defaulterCls').each(function(i, obj){
			 if($(this).is(':checked')){
				defaultersType = $(this).val();
			 }
		 });
	 }
	var locationType=$(".locationLevelCls").val();
	
	var locationValue="";
	if(locationType != null && locationType == 'State'){
		locationValue = 1;
	}else if(locationType != null && locationType == 'district'){
		locationValue = $("#districtId").val();
	}else if(locationType != null && locationType == 'assembly'){
		locationValue = $("#constituencyId").val();
	}else if(locationType != null && locationType == 'mandal'){
		locationValue = $("#mandalId").val();
	}
	getPanchyatTaxDashboardFilterWiseDetails(taxTypeId,feeTypeId,yearTypeVal,dataType,defaultersType,locationType,locationValue,taxTypeName,feeTypeName);
});

function getPanchyatTaxDashboardFilterWiseDetails(taxTypeId,feeTypeId,yearTypeVal,dataType,defaultersType,locationType,locationValue,taxTypeName,feeTypeName){
	$("#tableBuildDivId").html(spinner);
	var json = {
		taxTypeId :taxTypeId,
		feeTypeId :feeTypeId,
		yearType : yearTypeVal,
		dataType : dataType,
		defaultersType:defaultersType,
		locationType:locationType,
		locationValue:locationValue,
		fromDate : currentFromDate,
		toDate : currentToDate
	};
	$.ajax({                
		type:'POST',    
		url: 'getPanchyatTaxDashboardFilterWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildPanchyatTaxDashboardFilterWiseDetails(result,taxTypeId,feeTypeId,taxTypeName,feeTypeName,dataType,defaultersType,locationType,yearTypeVal);
		}else{
			$("#tableBuildDivId").html("No Data Available");
		}
	});
}
function buildPanchyatTaxDashboardFilterWiseDetails(result,taxTypeId,feeTypeId,taxTypeName,feeTypeName,dataType,defaultersType,locationType,yearTypeVal){
	var str='';
	str+='<div class="table-responsive">';
	if(defaultersType =="all"){
		str+='<table class="table table-bordered table_custom_tax" id="dataTableAll">';
			str+='<thead>';
				str+='<tr>';
					if(locationType == "district"){
						str+='<th rowspan="3">District</th>';
					}else if(locationType == "assembly"){
						str+='<th rowspan="3">District</th>';
						str+='<th rowspan="3">Constituency</th>';
					}else if(locationType == "mandal"){
						str+='<th rowspan="3">District</th>';
						str+='<th rowspan="3">Mandal</th>';
						
					}
					
					str+='<th colspan="3" rowspan="2" class="text-center">TOTAL TAX</th>';
					if($('#taxId').hasClass('active')){
						if(taxTypeId == 0){
							str+='<th colspan="9" class="text-center tax_color">TAXES</th>';
						}else{
							str+='<th colspan="3" class="text-center tax_color">TAXES</th>';
						}
					}
					if($('#feeId').hasClass('active')){
						if(feeTypeId == 0){
							str+='<th colspan="12" class="text-center fee_color">FEES</th>';
						}else{
							str+='<th colspan="3" class="text-center fee_color">FEES</th>';
						}
					}
					
					
				str+='</tr>';
				str+='<tr>';
					if($('#taxId').hasClass('active')){
						if(taxTypeId == 0){
							str+='<th colspan="3">HOUSE TAX</th>';
							str+='<th colspan="3">Kolagaaram TAX</th>';
							str+='<th colspan="3">Advertisement TAX</th>';
						}else{
							str+='<th colspan="3">'+taxTypeName+' TAX</th>';
						}
					}
					if($('#feeId').hasClass('active')){
						if(feeTypeId == 0){
							str+='<th colspan="3">Trade License</th>';
							str+='<th colspan="3">Auctions</th>';
							str+='<th colspan="3">Private Tap</th>';
							str+='<th colspan="3">Others</th>';
						}else{
							str+='<th colspan="3">'+feeTypeName+'</th>';
						}
					}
					
				str+='</tr>';
				str+='<tr>';
					str+='<th>DEM</th>';
					str+='<th>COL</th>';
					str+='<th>BAL</th>';
					if($('#taxId').hasClass('active')){
						if(taxTypeId == 0){
							str+='<th>DEM</th>';
							str+='<th>COL</th>';
							str+='<th>BAL</th>';
							str+='<th>DEM</th>';
							str+='<th>COL</th>';
							str+='<th>BAL</th>';
							str+='<th>DEM</th>';
							str+='<th>COL</th>';
							str+='<th>BAL</th>';
						}else{
							str+='<th>DEM</th>';
							str+='<th>COL</th>';
							str+='<th>BAL</th>';
						}
					}
					if($('#feeId').hasClass('active')){
						if(feeTypeId == 0){
							str+='<th>DEM</th>';
							str+='<th>COL</th>';
							str+='<th>BAL</th>';
							str+='<th>DEM</th>';
							str+='<th>COL</th>';
							str+='<th>BAL</th>';
							str+='<th>DEM</th>';
							str+='<th>COL</th>';
							str+='<th>BAL</th>';
							str+='<th>DEM</th>';
							str+='<th>COL</th>';
							str+='<th>BAL</th>';
						}else{
							str+='<th>DEM</th>';
							str+='<th>COL</th>';
							str+='<th>BAL</th>';
						}
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						if(locationType == "district"){
							str+='<td>'+result[i].districtName+'</td>';
						}else if(locationType == "assembly"){
							str+='<td>'+result[i].districtName+'</td>';
							str+='<td>'+result[i].assemblyName+'</td>';
						}else if(locationType == "mandal"){
							str+='<td>'+result[i].districtName+'</td>';
							str+='<td>'+result[i].mandalName+'</td>';
							
						}
						str+='<td>';
							str+='<h5 class="font_weight">';
							if(dataType == "all"){
								if(yearTypeVal != null && yearTypeVal == 'current'){
									if(result[i].currentDemand !=null && result[i].currentDemand>0){
										str+='<span>'+result[i].currentDemand+'</span><br/>';//amount
										str+='<span class="dem_color f_11">'+result[i].currentDemandAssmts+'</span>';//units
									}else{
										str+='<span> - </span><br/>'
										if(result[i].currentDemandAssmts !=null && result[i].currentDemandAssmts>0){
											str+='<span class="dem_color f_11">'+result[i].currentDemandAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
									if(result[i].arrearDemand !=null && result[i].arrearDemand>0){
										str+='<span>'+result[i].arrearDemand+'</span><br/>';//amount
										str+='<span class="dem_color f_11">'+result[i].arrearDemandAssmts+'</span>';//units
									}else{
										str+='<span> - </span><br/>'
										if(result[i].arrearDemandAssmts !=null && result[i].arrearDemandAssmts>0){
											str+='<span class="dem_color f_11">'+result[i].arrearDemandAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}else{
									if(result[i].totalDemand !=null && result[i].totalDemand>0){
										str+='<span>'+result[i].totalDemand+'</span><br/>';//amount
										str+='<span class="dem_color f_11">'+result[i].totalDemandAssmts+'</span>';//units
									}else{
										str+='<span> - </span><br/>'
										if(result[i].totalDemandAssmts !=null && result[i].totalDemandAssmts>0){
											str+='<span class="dem_color f_11">'+result[i].totalDemandAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}
							}else if(dataType == "amount"){
								if(yearTypeVal != null && yearTypeVal == 'current'){
									if(result[i].currentDemand !=null && result[i].currentDemand>0){
										str+='<span>'+result[i].currentDemand+'</span>';//amount
									}else{
										str+='<span> - </span>';//amount
									}
								}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
									if(result[i].arrearDemand !=null && result[i].arrearDemand>0){
										str+='<span>'+result[i].arrearDemand+'</span>';//amount
									}else{
										str+='<span> - </span>';//amount
									}
								}else{
									if(result[i].totalDemand !=null && result[i].totalDemand>0){
										str+='<span>'+result[i].totalDemand+'</span>';//amount
									}else{
										str+='<span> - </span>';//amount
									}
								}
							}else if(dataType == "unit"){
								if(yearTypeVal != null && yearTypeVal == 'current'){
									if(result[i].currentDemand !=null && result[i].currentDemand>0){
										str+='<span class="dem_color f_11">'+result[i].currentDemandAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
									if(result[i].arrearDemand !=null && result[i].arrearDemand>0){
										str+='<span class="dem_color f_11">'+result[i].arrearDemandAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}else{
									if(result[i].totalDemandAssmts !=null && result[i].totalDemandAssmts>0){
										str+='<span class="dem_color f_11">'+result[i].totalDemandAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}
							}
							str+='</h5>';
						str+='</td>';
						
						
						str+='<td>';
							str+='<h5 class="font_weight">';
							if(dataType == "all"){
								if(yearTypeVal != null && yearTypeVal == 'current'){
									if(result[i].currentCollection !=null && result[i].currentCollection>0){
										str+='<span>'+result[i].currentCollection+'</span><br/>';//amount
										str+='<span class="col_color f_11">'+result[i].currentCollectionAssmts+'</span>';//units
									}else{
										str+='<span> - </span><br/>'
										if(result[i].currentCollectionAssmts !=null && result[i].currentCollectionAssmts>0){
											str+='<span class="col_color f_11">'+result[i].currentCollectionAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
									if(result[i].arrearCollection !=null && result[i].arrearCollection>0){
										str+='<span>'+result[i].arrearCollection+'</span><br/>';//amount
										str+='<span class="col_color f_11">'+result[i].arrearCollectionAssmts+'</span>';//units
									}else{
										str+='<span> - </span><br/>'
										if(result[i].arrearCollectionAssmts !=null && result[i].arrearCollectionAssmts>0){
											str+='<span class="col_color f_11">'+result[i].arrearCollectionAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}else{
									if(result[i].totalCollection !=null && result[i].totalCollection>0){
										str+='<span>'+result[i].totalCollection+'</span><br/>';//amount
										str+='<span class="col_color f_11">'+result[i].totalCollectionAssmts+'</span>';//units
									}else{
										str+='<span> - </span><br/>'
										if(result[i].totalCollectionAssmts !=null && result[i].totalCollectionAssmts>0){
											str+='<span class="col_color f_11">'+result[i].totalCollectionAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}
							}else if(dataType == "amount"){
								if(yearTypeVal != null && yearTypeVal == 'current'){
									if(result[i].currentCollection !=null && result[i].currentCollection>0){
										str+='<span>'+result[i].currentCollection+'</span>';//amount
									}else{
										str+='<span> - </span>';//amount
									}
								}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
									if(result[i].arrearCollection !=null && result[i].arrearCollection>0){
										str+='<span>'+result[i].arrearCollection+'</span>';//amount
									}else{
										str+='<span> - </span>';//amount
									}
								}else{
									if(result[i].totalCollection !=null && result[i].totalCollection>0){
										str+='<span>'+result[i].totalCollection+'</span>';//amount
									}else{
										str+='<span> - </span>';//amountt
									}
								}
							}else if(dataType == "unit"){
								if(yearTypeVal != null && yearTypeVal == 'current'){
									if(result[i].currentCollection !=null && result[i].currentCollection>0){
										str+='<span class="col_color f_11">'+result[i].currentCollectionAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
									if(result[i].arrearCollection !=null && result[i].arrearCollection>0){
										str+='<span class="col_color f_11">'+result[i].arrearCollectionAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}else{
									if(result[i].totalCollectionAssmts !=null && result[i].totalCollectionAssmts>0){
										str+='<span class="col_color f_11">'+result[i].totalCollectionAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}
							}
							str+='</h5>';
						str+='</td>';
						
						str+='<td>';
							str+='<h5 class="font_weight">';
							if(dataType == "all"){
								if(yearTypeVal != null && yearTypeVal == 'current'){
									if(result[i].currentBalance !=null && result[i].currentBalance>0){
										str+='<span>'+result[i].currentBalance+'</span><br/>';//amount
										str+='<span class="bal_color f_11">'+result[i].currentBalanceAssmts+'</span>';//units
									}else{
										str+='<span> - </span><br/>'
										if(result[i].currentBalanceAssmts !=null && result[i].currentBalanceAssmts>0){
											str+='<span class="bal_color f_11">'+result[i].currentBalanceAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
									if(result[i].arrearBalance !=null && result[i].arrearBalance>0){
										str+='<span>'+result[i].arrearBalance+'</span><br/>';//amount
										str+='<span class="bal_color f_11">'+result[i].arrearBalanceAssmts+'</span>';//units
									}else{
										str+='<span> - </span><br/>'
										if(result[i].arrearBalanceAssmts !=null && result[i].arrearBalanceAssmts>0){
											str+='<span class="bal_color f_11">'+result[i].arrearBalanceAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}else{
									if(result[i].totalBalance !=null && result[i].totalBalance>0){
										str+='<span>'+result[i].totalBalance+'</span><br/>';//amount
										str+='<span class="bal_color f_11">'+result[i].totalBalanceAssmts+'</span>';//units
									}else{
										str+='<span> - </span><br/>'
										if(result[i].totalBalanceAssmts !=null && result[i].totalBalanceAssmts>0){
											str+='<span class="bal_color f_11">'+result[i].totalBalanceAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}
							}else if(dataType == "amount"){
								if(yearTypeVal != null && yearTypeVal == 'current'){
									if(result[i].currentBalance !=null && result[i].currentBalance>0){
										str+='<span>'+result[i].currentBalance+'</span>';//amount
									}else{
										str+='<span> - </span>';//amount
									}
								}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
									if(result[i].arrearBalance !=null && result[i].arrearBalance>0){
										str+='<span>'+result[i].arrearBalance+'</span>';//amount
									}else{
										str+='<span> - </span>';//amount
									}
								}else{
									if(result[i].totalBalance !=null && result[i].totalBalance>0){
										str+='<span>'+result[i].totalBalance+'</span>';//amount
									}else{
										str+='<span> - </span>';//amount
									}
								}
							}else if(dataType == "unit"){
								if(yearTypeVal != null && yearTypeVal == 'current'){
									if(result[i].currentBalance !=null && result[i].currentBalance>0){
										str+='<span class="bal_color f_11">'+result[i].currentBalanceAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
									if(result[i].arrearBalance !=null && result[i].arrearBalance>0){
										str+='<span class="bal_color f_11">'+result[i].arrearBalanceAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}else{
									if(result[i].totalBalanceAssmts !=null && result[i].totalBalanceAssmts>0){
										str+='<span class="bal_color f_11">'+result[i].totalBalanceAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}
							}
							str+='</h5>';
						str+='</td>';
						
						for(var j in result[i].subList){
							str+='<td>';
								str+='<h5 class="font_weight">';
								if(dataType == "all"){
									if(yearTypeVal != null && yearTypeVal == 'current'){
										if(result[i].subList[j].currentDemand !=null && result[i].subList[j].currentDemand>0){
											str+='<span>'+result[i].subList[j].currentDemand+'</span><br/>';//amount
											str+='<span class="dem_color f_11">'+result[i].subList[j].currentDemandAssmts+'</span>';//units
										}else{
											str+='<span> - </span><br/>'
											if(result[i].subList[j].currentDemandAssmts !=null && result[i].subList[j].currentDemandAssmts>0){
												str+='<span class="dem_color f_11">'+result[i].subList[j].currentDemandAssmts+'</span>';//units
											}else{
												str+='<span> - </span>';//amount
											}
										}
									}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
										if(result[i].subList[j].arrearDemand !=null && result[i].subList[j].arrearDemand>0){
											str+='<span>'+result[i].subList[j].arrearDemand+'</span><br/>';//amount
											str+='<span class="dem_color f_11">'+result[i].subList[j].arrearDemandAssmts+'</span>';//units
										}else{
											str+='<span> - </span><br/>'
											if(result[i].subList[j].arrearDemandAssmts !=null && result[i].subList[j].arrearDemandAssmts>0){
												str+='<span class="dem_color f_11">'+result[i].subList[j].arrearDemandAssmts+'</span>';//units
											}else{
												str+='<span> - </span>';//amount
											}
										}
									}else{
										if(result[i].subList[j].totalDemand !=null && result[i].subList[j].totalDemand>0){
											str+='<span>'+result[i].subList[j].totalDemand+'</span><br/>';//amount
											str+='<span class="dem_color f_11">'+result[i].subList[j].totalDemandAssmts+'</span>';//units
										}else{
											str+='<span> - </span><br/>'
											if(result[i].subList[j].totalDemandAssmts !=null && result[i].subList[j].totalDemandAssmts>0){
												str+='<span class="dem_color f_11">'+result[i].subList[j].totalDemandAssmts+'</span>';//units
											}else{
												str+='<span> - </span>';//amount
											}
										}
									}	
								}else if(dataType == "amount"){
									if(yearTypeVal != null && yearTypeVal == 'current'){
									if(result[i].subList[j].currentDemand !=null && result[i].subList[j].currentDemand>0){
										str+='<span>'+result[i].subList[j].currentDemand+'</span>';//amount
										}else{
											str+='<span> - </span>';//amount
										}
									}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
										if(result[i].subList[j].arrearDemand !=null && result[i].subList[j].arrearDemand>0){
											str+='<span>'+result[i].subList[j].arrearDemand+'</span>';//amount
										}else{
											str+='<span> - </span>';//amount
										}
									}else{
										if(result[i].subList[j].totalDemand !=null && result[i].subList[j].totalDemand>0){
										str+='<span>'+result[i].subList[j].totalDemand+'</span>';//amount
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}else if(dataType == "unit"){
									if(yearTypeVal != null && yearTypeVal == 'current'){
										if(result[i].subList[j].currentDemand !=null && result[i].subList[j].currentDemand>0){
											str+='<span class="dem_color f_11">'+result[i].subList[j].currentDemandAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
										if(result[i].subList[j].arrearDemand !=null && result[i].subList[j].arrearDemand>0){
											str+='<span class="dem_color f_11">'+result[i].subList[j].arrearDemandAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}else{
										if(result[i].subList[j].totalDemandAssmts !=null && result[i].subList[j].totalDemandAssmts>0){
											str+='<span class="dem_color f_11">'+result[i].subList[j].totalDemandAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}
								str+='</h5>';
							str+='</td>';
							
							str+='<td>';
								str+='<h5 class="font_weight">';
								if(dataType == "all"){
									if(yearTypeVal != null && yearTypeVal == 'current'){
										if(result[i].subList[j].currentCollection !=null && result[i].subList[j].currentCollection>0){
											str+='<span>'+result[i].subList[j].currentCollection+'</span><br/>';//amount
											str+='<span class="col_color f_11">'+result[i].subList[j].currentCollectionAssmts+'</span>';//units
										}else{
											str+='<span> - </span><br/>'
											if(result[i].subList[j].currentCollectionAssmts !=null && result[i].subList[j].currentCollectionAssmts>0){
												str+='<span class="col_color f_11">'+result[i].subList[j].currentCollectionAssmts+'</span>';//units
											}else{
												str+='<span> - </span>';//amount
											}
										}
									}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
										if(result[i].subList[j].arrearCollection !=null && result[i].subList[j].arrearCollection>0){
											str+='<span>'+result[i].subList[j].arrearCollection+'</span><br/>';//amount
											str+='<span class="col_color f_11">'+result[i].subList[j].arrearCollectionAssmts+'</span>';//units
										}else{
											str+='<span> - </span><br/>'
											if(result[i].subList[j].arrearCollectionAssmts !=null && result[i].subList[j].arrearCollectionAssmts>0){
												str+='<span class="col_color f_11">'+result[i].subList[j].arrearCollectionAssmts+'</span>';//units
											}else{
												str+='<span> - </span>';//amount
											}
										}
									}else{
										if(result[i].subList[j].totalCollection !=null && result[i].subList[j].totalCollection>0){
											str+='<span>'+result[i].subList[j].totalCollection+'</span><br/>';//amount
											str+='<span class="col_color f_11">'+result[i].subList[j].totalCollectionAssmts+'</span>';//units
										}else{
											str+='<span> - </span><br/>'
											if(result[i].subList[j].totalCollectionAssmts !=null && result[i].subList[j].totalCollectionAssmts>0){
												str+='<span class="col_color f_11">'+result[i].subList[j].totalCollectionAssmts+'</span>';//units
											}else{
												str+='<span> - </span>';//amount
											}
										}
									}
								}else if(dataType == "amount"){
									if(yearTypeVal != null && yearTypeVal == 'current'){
										if(result[i].subList[j].currentCollection !=null && result[i].subList[j].currentCollection>0){
											str+='<span>'+result[i].subList[j].currentCollection+'</span>';//amount
										}else{
											str+='<span> - </span>';//amount
										}
									}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
										if(result[i].subList[j].arrearCollection !=null && result[i].subList[j].arrearCollection>0){
											str+='<span>'+result[i].subList[j].arrearCollection+'</span>';//amount
										}else{
											str+='<span> - </span>';//amount
										}
									}else{
										if(result[i].subList[j].totalCollection !=null && result[i].subList[j].totalCollection>0){
											str+='<span>'+result[i].subList[j].totalCollection+'</span>';//amount
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}else if(dataType == "unit"){
									if(yearTypeVal != null && yearTypeVal == 'current'){
										if(result[i].subList[j].currentCollection !=null && result[i].subList[j].currentCollection>0){
											str+='<span class="col_color f_11">'+result[i].subList[j].currentCollectionAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
										if(result[i].subList[j].arrearCollection !=null && result[i].subList[j].arrearCollection>0){
											str+='<span class="col_color f_11">'+result[i].subList[j].arrearCollectionAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}else{
										if(result[i].subList[j].totalCollectionAssmts !=null && result[i].subList[j].totalCollectionAssmts>0){
											str+='<span class="col_color f_11">'+result[i].subList[j].totalCollectionAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}
								str+='</h5>';
							str+='</td>';
							
							str+='<td>';
								str+='<h5 class="font_weight">';
								if(dataType == "all"){
									if(yearTypeVal != null && yearTypeVal == 'current'){
										if(result[i].subList[j].currentBalance !=null && result[i].subList[j].currentBalance>0){
											str+='<span>'+result[i].subList[j].currentBalance+'</span><br/>';//amount
											str+='<span class="bal_color f_11">'+result[i].subList[j].currentBalanceAssmts+'</span>';//units
										}else{
											str+='<span> - </span><br/>'
											if(result[i].subList[j].currentBalanceAssmts !=null && result[i].subList[j].currentBalanceAssmts>0){
												str+='<span class="bal_color f_11">'+result[i].subList[j].currentBalanceAssmts+'</span>';//units
											}else{
												str+='<span> - </span>';//amount
											}
										}
									}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
										if(result[i].subList[j].arrearBalance !=null && result[i].subList[j].arrearBalance>0){
											str+='<span>'+result[i].subList[j].arrearBalance+'</span><br/>';//amount
											str+='<span class="bal_color f_11">'+result[i].subList[j].arrearBalanceAssmts+'</span>';//units
										}else{
											str+='<span> - </span><br/>'
											if(result[i].subList[j].totalBalanceAssmts !=null && result[i].subList[j].totalBalanceAssmts>0){
												str+='<span class="bal_color f_11">'+result[i].subList[j].arrearBalanceAssmts+'</span>';//units
											}else{
												str+='<span> - </span>';//amount
											}
										}
									}else{
										if(result[i].subList[j].totalBalance !=null && result[i].subList[j].totalBalance>0){
										str+='<span>'+result[i].subList[j].totalBalance+'</span><br/>';//amount
											str+='<span class="bal_color f_11">'+result[i].subList[j].totalBalanceAssmts+'</span>';//units
										}else{
											str+='<span> - </span><br/>'
											if(result[i].subList[j].totalBalanceAssmts !=null && result[i].subList[j].totalBalanceAssmts>0){
												str+='<span class="bal_color f_11">'+result[i].subList[j].totalBalanceAssmts+'</span>';//units
											}else{
												str+='<span> - </span>';//amount
											}
										}
									}
								}else if(dataType == "amount"){
									if(yearTypeVal != null && yearTypeVal == 'current'){
										if(result[i].subList[j].currentBalance !=null && result[i].subList[j].currentBalance>0){
											str+='<span>'+result[i].subList[j].currentBalance+'</span>';//amount
										}else{
											str+='<span> - </span>';//amount
										}
									}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
										if(result[i].subList[j].arrearBalance !=null && result[i].subList[j].arrearBalance>0){
											str+='<span>'+result[i].subList[j].arrearBalance+'</span>';//amount
										}else{
											str+='<span> - </span>';//amount
										}
									}else{
										if(result[i].subList[j].totalBalance !=null && result[i].subList[j].totalBalance>0){
											str+='<span>'+result[i].subList[j].totalBalance+'</span>';//amount
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}else if(dataType == "unit"){
									if(yearTypeVal != null && yearTypeVal == 'current'){
										if(result[i].subList[j].currentBalance !=null && result[i].subList[j].currentBalance>0){
											str+='<span class="bal_color f_11">'+result[i].subList[j].currentBalanceAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}else if(yearTypeVal != null && yearTypeVal == 'arrears'){
										if(result[i].subList[j].arrearBalance !=null && result[i].subList[j].arrearBalance>0){
											str+='<span class="bal_color f_11">'+result[i].subList[j].arrearBalanceAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}else{
										if(result[i].subList[j].totalBalanceAssmts !=null && result[i].subList[j].totalBalanceAssmts>0){
											str+='<span class="bal_color f_11">'+result[i].subList[j].totalBalanceAssmts+'</span>';//units
										}else{
											str+='<span> - </span>';//amount
										}
									}
								}
								str+='</h5>';
							str+='</td>';
							
						}
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	}else if(defaultersType =="defaulters"){
		str+='<table class="table table-bordered" id="dataTabledefaulters">';
			str+='<thead>';
				str+='<tr>';
				str+='<th>Defaulter Name</th>';
				str+='<th>Due Amount</th>';
				str+='<th>Due Year</th>';
				str+='<th>Tax Type</th>';
				str+='<th>Panchayat</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].name+'</td>';
						str+='<td>'+result[i].amount+'</td>';
						str+='<td>'+result[i].dueYear+'</td>';
						str+='<td>'+result[i].taxType+'</td>';
						str+='<td>'+result[i].panchyatName+'</td>';
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	}else if(defaultersType =="indicators"){
		str+='<table class="table table-bordered" id="dataTableindicators">';
			str+='<thead>';
				str+='<tr>';
				str+='<th>INDICATORS</th>';
				str+='<th>DEMAND</th>';
				str+='<th>COLLECTION</th>';
				str+='<th>BALANCE</th>';
				
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].usageName+'</td>';
						str+='<td>';
							str+='<h5 class="font_weight">';
							if(dataType == "all"){
								if(result[i].demand !=null && result[i].demand>0){
									str+='<span>'+result[i].demand+'</span><br/>';//amount
									str+='<span class="dem_color f_11">'+result[i].demandAssmts+'</span>';//units
								}else{
									str+='<span> - </span><br/>'
									if(result[i].demandAssmts !=null && result[i].demandAssmts>0){
										str+='<span class="dem_color f_11">'+result[i].demandAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}
							}else if(dataType == "amount"){
								if(result[i].demand !=null && result[i].demand>0){
									str+='<span>'+result[i].demand+'</span>';//amount
								}else{
									str+='<span> - </span>';//amount
								}
								
							}else if(dataType == "unit"){
								if(result[i].demandAssmts !=null && result[i].demandAssmts>0){
									str+='<span>'+result[i].demandAssmts+'</span>';//units
								}else{
									str+='<span> - </span>';//amount
								}
							}
							str+='</h5>';
						str+='</td>';
							
						str+='<td>';
							str+='<h5 class="font_weight">';
							if(dataType == "all"){
								if(result[i].collection !=null && result[i].collection>0){
									str+='<span>'+result[i].collection+'</span><br/>';//amount
									str+='<span class="col_color f_11">'+result[i].collectionAssmts+'</span>';//units
								}else{
									str+='<span> - </span><br/>'
									if(result[i].collectionAssmts !=null && result[i].collectionAssmts>0){
										str+='<span class="col_color f_11">'+result[i].collectionAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}
							}else if(dataType == "amount"){
								if(result[i].collection !=null && result[i].collection>0){
									str+='<span>'+result[i].collection+'&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:green;">'+parseFloat(result[i].collectinAmntPerc).toFixed(0)+'%</span></span>';//amount
								}else{
									str+='<span> - </span>';//amount
								}
								
							}else if(dataType == "unit"){
								if(result[i].collectionAssmts !=null && result[i].collectionAssmts>0){
									str+='<span>'+result[i].collectionAssmts+'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="col_color f_11">'+parseFloat(result[i].collectinAsstsPerc).toFixed(0)+'%</span>';//units
								}else{
									str+='<span> - </span>';//amount
								}
							}
							str+='</h5>';
							str+='</td>';
						
						str+='<td>';
							str+='<h5 class="font_weight">';
							if(dataType == "all"){
								if(result[i].balance !=null && result[i].balance>0){
									str+='<span>'+result[i].balance+'</span><br/>';//amount
									str+='<span class="bal_color f_11">'+result[i].balanceAssmts+'</span>';//units
								}else{
									str+='<span> - </span><br/>'
									if(result[i].balanceAssmts !=null && result[i].balanceAssmts>0){
										str+='<span class="bal_color f_11">'+result[i].balanceAssmts+'</span>';//units
									}else{
										str+='<span> - </span>';//amount
									}
								}
							}else if(dataType == "amount"){
								if(result[i].balance !=null && result[i].balance>0){
									str+='<span>'+result[i].balance+'&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red;">'+parseFloat(result[i].balanceAmntPerc).toFixed(0)+'%</span></span>';//amount
								}else{
									str+='<span> - </span>';//amount
								}
								
							}else if(dataType == "unit"){
								if(result[i].balanceAssmts !=null && result[i].balanceAssmts>0){
									str+='<span>'+result[i].balanceAssmts+'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="bal_color f_11">'+parseFloat(result[i].balanceAsstsPerc).toFixed(0)+'%</span>';//units
								}else{
									str+='<span> - </span>';//amount
								}
							}
							str+='</h5>';
						str+='</td>';
						/* str+='<td>'+result[i].demand+'</td>';
						str+='<td>'+result[i].collection+'&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:green;">'+parseFloat(result[i].collectinAmntPerc).toFixed(0)+'%</span></td>';
						str+='<td>'+result[i].balance+'&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red;">'+parseFloat(result[i].balanceAmntPerc).toFixed(0)+'%</span></td>'; */
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	}
	str+='</div>';
	$("#tableBuildDivId").html(str);
	if(defaultersType =="all"){
		$("#dataTableAll").dataTable({
			"iDisplayLength": 13,
			"aaSorting": [],
			"aLengthMenu": [[13, 15, 20,50, -1], [13, 15, 20,50, "All"]]
		});
	}else if(defaultersType =="defaulters"){
		$("#dataTabledefaulters").dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20,50, -1], [10, 15, 20,50, "All"]]
		});
	}else if(defaultersType =="indicators"){
		$("#dataTableindicators").dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20,50, -1], [10, 15, 20,50, "All"]]
		});
	}
	
	
}
$(document).on("click",".menuDataCollapse",function(){
		globallocationId = $(this).attr("attr_id");
		globallocationType = $(this).attr("attr_levelidvalue");
		globallocationlevelIdType = $(this).attr("attr_levelid");
		if(globallocationType == 3){
			globallocationDistrictName = $(this).attr("attr_name");
		}else if(globallocationType == 4){
			globallocationConsName = $(this).attr("attr_name");
		}else if(globallocationType == 5){
			globallocationMandalName = $(this).attr("attr_name");
		}
		$("#selectedName").html($(this).html())
		onloadTaxCalls();
});