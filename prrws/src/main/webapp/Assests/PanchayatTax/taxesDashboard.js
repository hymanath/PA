var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

getTaxesAndCategoryWiseOverViewDetails();
getTaxesIndicatorDetails();
getTaxesDefaultOverviewDetails();

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
	for(var i in result){
	str+='<div class="col-sm-3">';
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
					str+='<div class="col-sm-4 m_top15">';
						str+='<div class="media" style="overflow:unset;">';
							str+='<div class="media-left">';
								str+='<img src="Assests/images/green-strip.png" class="media-object" style="height:97px;width: 8px;">';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h6 class="font_weight">Collection</h6>';
								str+='<h5 class="font_weight m_top10 Collection_clr">'+result[i].totalCollectionAmount+'<small style="color:#141F27;">&nbsp;Cr</small></h5>';
								str+='<h6 class="font_weight m_top5 Collection_clr">('+result[i].collectionAmuntPerc+'%)</h6>';
								str+='<h6 class="font_weight m_top10">Units</h6>';
								str+='<h6 class="font_weight m_top5 Collection_clr">'+result[i].totalCollectionUnts+' <small>&nbsp;('+result[i].collectionUnitsPerc+'%)</small></h6>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4 m_top15">';
						str+='<div class="media" style="overflow:unset;">';
							str+='<div class="media-left">';
								str+='<img src="Assests/images/red-strip.png" class="media-object" style="height:97px;width: 8px;">';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h6 class="font_weight">Balance</h6>';
								str+='<h5 class="font_weight m_top10 Balance_Clr">'+result[i].totalBalanceAmount+'<small style="color:#141F27;">&nbsp;Cr</small></h5>';
								str+='<h6 class="font_weight m_top5 Balance_Clr">('+result[i].balAmuntPerc+'%)</h6>';
								str+='<h6 class="font_weight m_top10">Units</h6>';
								str+='<h6 class="font_weight m_top5 Balance_Clr">'+result[i].totalBalanceUnits+' <small>&nbsp;('+result[i].balUnitsPerc+'%)</small></h6>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';	
			str+='</div>';
		str+='</div>';	
	str+='</div>';
	$("#indicatorsOverviewDivId").html(str);
	}
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