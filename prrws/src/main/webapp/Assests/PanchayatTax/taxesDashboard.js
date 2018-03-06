var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

getTaxesAndCategoryWiseOverViewDetails();
getTaxesIndicatorDetails();
getTaxesDefaultOverviewDetails();
getAllSubLocations(2,1,"","district");
function getTaxesAndCategoryWiseOverViewDetails(){
	$("#completeOverviewDivId").html(spinner);
	$("#categoryTaxOverViewDivId").html(spinner);
	$("#categoryFeeOverViewDivId").html(spinner);
	var json = {
		locationId : "0",
		locationType : "district",
		fromDate : "01-06-2017",
		toDate : "03-03-2018"
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
	var json = {
		locationId : "0",
		locationType : "district",
		fromDate : "01-06-2017",
		toDate : "03-03-2018"
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
		variableWidth: false
	 });
}

function getTaxesDefaultOverviewDetails(){
	$("#defaulterOverviewDivId").html(spinner);
	var json = {
		locationId : "0",
		locationType : "district"
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
					str+='<h4 class="font_weight Demand_Clr text-center">'+result[i].totalUnits+'&nbsp;Units</h4>';
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
		getAllSubLocations(2,1,"","district");//All Districts
	}else if(levelId == 'assembly'){
		$(".districtCls").show();
		$(".constituencyCls").show();
		$(".mandalCls").hide();
		getAllSubLocations(2,1,"","district");//All Districts
	}else if(levelId == 'mandal'){
		$(".districtCls").show();
		$(".constituencyCls").show();
		$(".mandalCls").show();
		getAllSubLocations(2,1,"","district");//All Districts
	}/* else if(levelId == 'State'){
		$(".districtCls").hide();
		$(".constituencyCls").hide();
		$(".mandalCls").hide();
	} */
});
$(document).on("change","#districtId",function(){
	var levelValId = $(this).val();
	getAllSubLocations(3,levelValId,"constituency","constituency");
});
$(document).on("change","#constituencyId",function(){
	var levelValId = $(this).val();
	getAllSubLocations(4,levelValId,"","mandal");
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
	}else{
		$('#taxId').prop('disabled', false).trigger("chosen:updated");
		$('#feeId').prop('disabled', false).trigger("chosen:updated");
	}
	
});
function getAllSubLocations(levelId,levelValue,typeVal,appendDivId){
	if(appendDivId == "district"){
		$("#districtId").html('');
	}else if(appendDivId == "assembly"){
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
	str+='<option value="0">Select All</option>';
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
	
	var json = {
		taxTypeId :taxTypeId,
		feeTypeId :feeTypeId,
		yearType : yearTypeVal,
		dataType : dataType,
		defaultersType:defaultersType,
		locationType:locationType,
		locationValue:locationValue,
		fromDateStr:"01-06-2017",
		toDateStr:"03-03-2018"
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
			buildPanchyatTaxDashboardFilterWiseDetails(result,taxTypeId,feeTypeId,taxTypeName,feeTypeName,dataType,defaultersType);
		}
	});
}
function buildPanchyatTaxDashboardFilterWiseDetails(result,taxTypeId,feeTypeId,taxTypeName,feeTypeName,dataType,defaultersType){
	var str='';
	str+='<div class="table-responsive">';
	if(defaultersType =="all"){
		str+='<table class="table table-bordered">';
			str+='<thead>';
				str+='<tr>';
					str+='<th rowspan="3">LOCATION WISE</th>';
					str+='<th colspan="3" rowspan="2" class="text-center">TOTAL</th>';
					if($('#taxId').hasClass('active')){
						if(taxTypeId == 0){
							str+='<th colspan="9" class="text-center">TAXES</th>';
						}else{
							str+='<th colspan="3" class="text-center">TAXES</th>';
						}
					}
					if($('#feeId').hasClass('active')){
						if(feeTypeId == 0){
							str+='<th colspan="12" class="text-center">FEES</th>';
						}else{
							str+='<th colspan="3" class="text-center">FEES</th>';
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
						str+='<td>'+result[i].districtName+'</td>';
						str+='<td>';
							str+='<h5 class="font_weight">';
							if(dataType == "all"){
								str+='<span>'+result[i].totalDemand+'</span><br/>';//amount
								str+='<span>'+result[i].totalDemandAssmts+'</span>';//units
							}else if(dataType == "amount"){
								str+='<span>'+result[i].totalDemand+'</span>';//amount
							}else if(dataType == "unit"){
								str+='<span>'+result[i].totalDemandAssmts+'</span>';//units
							}
							str+='</h5>';
						str+='</td>';
						
						
						str+='<td>';
							str+='<h5 class="font_weight">';
							if(dataType == "all"){
								str+='<span>'+result[i].totalCollection+'</span><br/>';//amount
								str+='<span>'+result[i].totalCollectionAssmts+'</span>';//units
							}else if(dataType == "amount"){
								str+='<span>'+result[i].totalCollection+'</span>';//amount
							}else if(dataType == "unit"){
								str+='<span>'+result[i].totalCollectionAssmts+'</span>';//units
							}
							str+='</h5>';
						str+='</td>';
						
						str+='<td>';
							str+='<h5 class="font_weight">';
							if(dataType == "all"){
								str+='<span>'+result[i].totalBalance+'</span><br/>';//amount
								str+='<span>'+result[i].totalBalanceAssmts+'</span>';//units
							}else if(dataType == "amount"){
								str+='<span>'+result[i].totalBalance+'</span>';//amount
							}else if(dataType == "unit"){
								str+='<span>'+result[i].totalBalanceAssmts+'</span>';//units
							}
							str+='</h5>';
						str+='</td>';
						
						for(var j in result[i].subList){
							str+='<td>';
								str+='<h5 class="font_weight">';
								if(dataType == "all"){
									str+='<span>'+result[i].subList[j].totalDemand+'</span><br/>';//amount
									str+='<span>'+result[i].subList[j].totalDemandAssmts+'</span>';//units
								}else if(dataType == "amount"){
									str+='<span>'+result[i].subList[j].totalDemand+'</span>';//amount
								}else if(dataType == "unit"){
									str+='<span>'+result[i].subList[j].totalDemandAssmts+'</span>';//units
								}
								str+='</h5>';
							str+='</td>';
							
							str+='<td>';
								str+='<h5 class="font_weight">';
								if(dataType == "all"){
									str+='<span>'+result[i].subList[j].totalCollection+'</span><br/>';//amount
									str+='<span>'+result[i].subList[j].totalCollectionAssmts+'</span>';//units
								}else if(dataType == "amount"){
									str+='<span>'+result[i].subList[j].totalCollection+'</span>';//amount
								}else if(dataType == "unit"){
									str+='<span>'+result[i].subList[j].totalCollectionAssmts+'</span>';//units
								}
								str+='</h5>';
							str+='</td>';
							
							str+='<td>';
								str+='<h5 class="font_weight">';
								if(dataType == "all"){
									str+='<span>'+result[i].subList[j].totalBalance+'</span><br/>';//amount
									str+='<span>'+result[i].subList[j].totalBalanceAssmts+'</span>';//units
								}else if(dataType == "amount"){
									str+='<span>'+result[i].subList[j].totalBalance+'</span>';//amount
								}else if(dataType == "unit"){
									str+='<span>'+result[i].subList[j].totalBalanceAssmts+'</span>';//units
								}
								str+='</h5>';
							str+='</td>';
							
						}
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	}else if(defaultersType =="defaulters"){
		str+='<table class="table table-bordered">';
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
		str+='<table class="table table-bordered">';
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
						str+='<td>'+result[i].demand+'</td>';
						str+='<td>'+result[i].collection+'</td>';
						str+='<td>'+result[i].balance+'</td>';
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	}
	str+='</div>';
	$("#tableBuildDivId").html(str);
}