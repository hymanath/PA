getTaxesAndCategoryWiseOverViewDetails();

function getTaxesAndCategoryWiseOverViewDetails(){
	
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
		if(result != null)
			buildCompleteOverviewDetails(result);
	});
}

function buildCompleteOverviewDetails(result){
	var str='';
	
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
}