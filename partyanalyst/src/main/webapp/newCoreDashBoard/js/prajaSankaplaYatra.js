	var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

	var currentFromDatePa = moment().format("DD-MM-YYYY");
	var currentToDatePa = moment().format("DD-MM-YYYY");
	var globalcategoryId = $("#categoryId").find("option:selected").val();
	var categoryName = $("#categoryName").find("option:selected").text();
	$(".chosen-select").chosen();
	$("#prajaHeadDate").html("Today("+currentFromDatePa+")"); 
	var globalPartyId =0;
	var globalBenefitId =0;
	var globalChannelId =0;
	function globalPrajaSankalpaYatraCalls(type)
	{
		if(type == "default"){
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setStartDate(moment());
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setEndDate(moment());
			currentFromDatePa = moment().format("DD-MM-YYYY")
			toDate = moment().format("DD-MM-YYYY")
			$("#prajaHeadDate").html("Today("+currentFromDatePa+")");
		}else if(type == "currentMonth"){
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setStartDate(moment().startOf("month"));
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setEndDate(moment().endOf("month"));
			currentFromDatePa = moment().startOf("month").format("DD-MM-YYYY")
			toDate = moment().endOf("month").format("DD-MM-YYYY")
			$("#prajaHeadDate").html("THIS MONTH"+" ( "+moment().startOf("month").format("DD-MM-YYYY")+"-"+moment().endOf("month").format("DD-MM-YYYY")+" )");
		}else if(type == "lastMonth"){
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setStartDate(moment().subtract(1,'month').startOf("month"));
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setEndDate(moment().subtract(1,'month').endOf("month"));
			currentFromDatePa = moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY")
			toDate = moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")
			$("#prajaHeadDate").html("LAST MONTH"+" ( "+moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY")+"-"+moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")+" )");
		}
		$("#dateRangePrajaSankalpaId").val(fromDate+" - "+toDate);
		onloadPrajaSankaplaYatraCalls();
		 
	}

	$("#dateRangePrajaSankalpaId").daterangepicker({
		opens: 'left',
		startDate:currentFromDatePa,
		//minDate:"06-11-2017",
		endDate: currentToDatePa,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		ranges: {
		   'Today': [moment(), moment()],
		   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		   'This Month': [moment().startOf("month").format("DD-MM-YYYY"), moment().endOf('month').format("DD-MM-YYYY")],
		   'Last Month': [moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY"),moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()]
		   
		}
	});
	$('#dateRangePrajaSankalpaId').on('apply.daterangepicker', function(ev, picker) {
	  currentFromDatePa = picker.startDate.format('DD-MM-YYYY');
	  currentToDatePa = picker.endDate.format('DD-MM-YYYY');
	  $("#prajaHeadDate").html("("+picker.startDate.format("DD/MM/YY")+" to "+picker.endDate.format("DD/MM/YY")+")");
	  onloadPrajaSankaplaYatraCalls();
	  /* if($(".prajaSankaplaYatraIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
			getChannelWisePartiesAnalysis(globalcategoryId); // Main Block Electronic Media
		}
		$("[role='tabCummulative'] li").removeClass("active");
		$("[role='tabCummulative'] li:nth-child(1)").addClass("active");
		$(".ChannelWisePartyCls").hide();
		$(".districtWiseChannelCls").hide();
		
		$("#ChannelWisePartyDivId").html('');
		$("#districtWiseChannelDivId").html('');
		
		$(".publicationWisePartiesCls").show();
		$(".districtWisePartiesCls").show();
		
		getPublicationWisePartiesAnalysis(globalcategoryId);// Expand Block Print Media
		getDistrictWisePartyOverView(globalcategoryId);// Expand Block Print Media District */
		
		
	});

function onloadPrajaSankaplaYatraCalls(){
	getEditionTypeWisePartiesAnalysis(globalcategoryId); //Main Block Print Media
	if($(".prajaSankaplaYatraIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
		getChannelWisePartiesAnalysis(globalcategoryId); // Main Block Electronic Media
	}
	 if($(".moreNewsPrajaSankalpaBlocksIcon").hasClass("expandBlock")){
		 $("[role='tabCummulative'] li").removeClass("active");
		 $("[role='tabCummulative'] li:nth-child(1)").addClass("active");
		 $(".ChannelWisePartyCls").hide();
		 $(".districtWiseChannelCls").hide();
		
		$("#ChannelWisePartyDivId").html('');
		$("#districtWiseChannelDivId").html('');
		
		$(".publicationWisePartiesCls").show();
		$(".districtWisePartiesCls").show();
		
		 getPublicationWisePartiesAnalysis(globalcategoryId);// Expand Block Print Media
		 getDistrictWisePartyOverView(globalcategoryId);// Expand Block Print Media District
	 }
}
$(document).on("click",".prajaSankaplaYatraIconExpand",function(){
	if($(this).find("i").hasClass("glyphicon glyphicon-resize-small" )){
		getChannelWisePartiesAnalysis(globalcategoryId); // Main Block Electronic Media
	}
		
});
$(document).on("click",".moreNewsPrajaSankalpaBlocksIcon",function(){
		$(this).addClass("expandBlock");
		$(".moreNewsprajaSankaplaBlocksDetailed").show(); 
		
		 $("[role='tabCummulative'] li").removeClass("active");
		 $("[role='tabCummulative'] li:nth-child(1)").addClass("active");
		 $(".ChannelWisePartyCls").hide();
		 $(".districtWiseChannelCls").hide();
		
			$("#ChannelWisePartyDivId").html('');
			$("#districtWiseChannelDivId").html('');
			
			$(".publicationWisePartiesCls").show();
			$(".districtWisePartiesCls").show();
		
		
		getPublicationWisePartiesAnalysis(globalcategoryId);// Expand Block Print Media
		getDistrictWisePartyOverView(globalcategoryId);// Expand Block Print Media District
});
$(document).on("click",".expandBlock",function(){
		$(this).removeClass("expandBlock");
		$(".moreNewsprajaSankaplaBlocksDetailed").hide(); 
		
});
function getEditionTypeWisePartiesAnalysis(categoryId){
	$("#overAllPrintMediaNewsDivId").html("");
	$("#overAllPrintMediaNewsDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysis/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
		//url: "http://192.168.11.195:8080/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysis/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
	}).then(function(result){
		if(result !=null){
			mainNewsBlockPraja(result);
		}else{
			$("#overAllPrintMediaNewsDivId").html("No Data Available");
		}
	});
}

function getChannelWisePartiesAnalysis(categoryId){
	$("#overAllElectronicMediaNewsDivId").html("");
	$("#overAllElectronicMediaNewsDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysis/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
		//url: "http://192.168.11.194:8086/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysis/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
	}).then(function(result){
		if(result !=null){
			getpartyWiseChannelCountsPraja(result)
		}else{
			$("#overAllElectronicMediaNewsDivId").html("No Data Available");
		}
	});
}
function getpartyWiseChannelCountsPraja(result){
	var str='';
	var totalCount=0;
	var newsBulletinCount=0;
		str+='<h4 class="text-capital"><span class="headingColor" style="margin-right:5px"><img src="newCoreDashBoard/img/TDP.png" alt="tdp icon" class="newsIcon"/>TDP Party</span></h4>';
		
		str+='<div class="table-responsive m_XsTop10">';
		str+='<table class="table tableEMN m_top10">';
			str+='<tr>';
			if(result.idNameVoList !=null && result.idNameVoList.length>0){
				for(var i in result.idNameVoList)
				{
					totalCount = result.idNameVoList[i].posCount+result.idNameVoList[i].negCount
					str+='<td>';
						str+='<h5 class="text-capitalize">News Bulletin</h5>';
						
						if(totalCount !=null && totalCount>0){
							str+='<h4 class="EMnCls" attr_benefitId="0" attr_party="'+result.idNameVoList[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="0" style="cursor:pointer;"><a>'+totalCount+'</a></h4>';
						}else{
							str+='<h4>0</h4>';
						}
						
						
						str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
						if(result.idNameVoList[i].description!= "" && result.idNameVoList[i].description !=null){
							str+='<h4>'+result.idNameVoList[i].description+'</h4>';
						}else{
							str+='<h4>00:00</h4>';
						}
					str+='</td>';
					str+='<td>';
						str+='<h5 class="text-capitalize">positive</h5>';
						
						if(result.idNameVoList[i].posCount !=null && result.idNameVoList[i].posCount>0){
							str+='<h4 class="EMnCls" attr_benefitId="1" attr_party="'+result.idNameVoList[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="0" style="cursor:pointer;"><a>'+result.idNameVoList[i].posCount+'</a></h4>';
							str+='<small id="" class="text-success"> '+result.idNameVoList[i].posPercent+' %</small>';
						}else{
							str+='<h4>0</h4>';
						}
						
						
						str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
						if(result.idNameVoList[i].postiveDesc!= ""){
							str+='<h4>'+result.idNameVoList[i].postiveDesc+'</h4>';
						}else{
							str+='<h4>00:00</h4>';
						}
					str+='</td>';
					str+='<td>';
						str+='<h5 class="text-capitalize">negative</h5>';
						if(result.idNameVoList[i].negCount !=null && result.idNameVoList[i].negCount>0){
							str+='<h4 class="EMnCls" attr_benefitId="2" attr_party="'+result.idNameVoList[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="0" style="cursor:pointer;"><a>'+result.idNameVoList[i].negCount+'</a></h4>';
							str+='<small id="" class="text-success"> '+result.idNameVoList[i].negPercent+' %</small>';
						}else{
							str+='<h4>0</h4>';
						}
						
						
						str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
						if(result.idNameVoList[i].negativeDesc!= ""){
							str+='<h4>'+result.idNameVoList[i].negativeDesc+'</h4>';
						}else{
							str+='<h4>00:00</h4>';
						}
					str+='</td>';
				}
			}else{
				str+='<td>No Data Available</td>';
			}
				
			str+='</tr>';
		str+='</table>';
		str+='</div>';
		
		str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/opp.png" style="width:25px;" alt="tdp icon" class="debatesPartyIcon"/>Other Parties</span></h4>';
		if(result.idNameVoList1 !=null && result.idNameVoList1.length>0){
			for(var i in result.idNameVoList1)
			{
				newsBulletinCount = result.idNameVoList1[i].posCount+result.idNameVoList1[i].negCount
				str+='<h4 class="panel-title m_top10"><img src="newCoreDashBoard/img/'+result.idNameVoList1[i].name+'.png" class="debatesPartyIcon"/>'+result.idNameVoList1[i].name+'</h4>';
				
				str+='<div class="table-responsive m_XsTop10">';
				str+='<table class="table tableEMN m_top10">';
					str+='<tr>';
							str+='<td>';
								str+='<h5 class="text-capitalize">News Bulletin</h5>';
								
								if(newsBulletinCount !=null && newsBulletinCount>0){
									str+='<h4 class="EMnCls" attr_benefitId="0" attr_party="'+result.idNameVoList1[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="0" style="cursor:pointer;"><a>'+newsBulletinCount+'</a></h4>';
								}else{
									str+='<h4>0</h4>';
								}
								
								
								str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
								str+='<h4>'+result.idNameVoList1[i].description != ""?result.idNameVoList1[i].description:"00:00"+'</h4>';
								
							str+='</td>';
							
							str+='<td>';
								str+='<h5 class="text-capitalize">positive</h5>';
								
								if(result.idNameVoList1[i].posCount !=null && result.idNameVoList1[i].posCount>0){
									str+='<h4 class="EMnCls" attr_benefitId="1" attr_party="'+result.idNameVoList1[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="0" style="cursor:pointer;"><a>'+result.idNameVoList1[i].posCount+'</a></h4>';
									str+='<small id="" class="text-success"> '+result.idNameVoList1[i].posPercent+' %</small>';
								}else{
									str+='<h4>0</h4>';
								}
								
								
								str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
								str+='<h4>'+result.idNameVoList1[i].postiveDesc != ""?result.idNameVoList1[i].postiveDesc:"00:00"+'</h4>';
								
							str+='</td>';
							
							str+='<td>';
								str+='<h5 class="text-capitalize">negative</h5>';
								
								if(result.idNameVoList1[i].negCount !=null && result.idNameVoList1[i].negCount>0){
									str+='<h4 class="EMnCls" attr_benefitId="2" attr_party="'+result.idNameVoList1[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="0" style="cursor:pointer;"><a>'+result.idNameVoList1[i].negCount+'</a></h4>';
									str+='<small id="" class="text-success"> '+result.idNameVoList1[i].negPercent+' %</small>';
								}else{
									str+='<h4>0</h4>';
								}
								
								
								str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
								str+='<h4>'+result.idNameVoList1[i].negativeDesc != ""?result.idNameVoList1[i].negativeDesc:"00:00"+'</h4>';
								
							str+='</td>';
					str+='</tr>';
				str+='</table>';
				
				str+='</div>';
			}
		}else{
			str+='<h4 class="panel-title m_top10">No Data Available</h4>';
		}
		//str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/opp.png" style="width:25px;" alt="tdp icon" class="debatesPartyIcon"/>Other Parties</span></h4>';
		str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/GOVT.png" style="width:25px;" alt="government icon" class="newsIcon"/>Government</span></h4>';
		if(result.idNameVoList2 !=null && result.idNameVoList2.length>0){
			for(var i in result.idNameVoList2)
			{
				newsBulletinCount = result.idNameVoList2[i].posCount+result.idNameVoList2[i].negCount
				//str+='<h4 class="panel-title m_top10">'+result.idNameVoList2[i].name+'</h4>';
				var partyId=0;
				str+='<div class="table-responsive m_XsTop10">';
				str+='<table class="table tableEMN m_top10">';
					str+='<tr>';
							str+='<td>';
								str+='<h5 class="text-capitalize">News Bulletin</h5>';
								
								if(newsBulletinCount !=null && newsBulletinCount>0){
									str+='<h4 class="EMnCls" attr_benefitId="0" attr_party="'+partyId+'" attr_category_id="'+globalcategoryId+'" attr_edition="0" style="cursor:pointer;"><a>'+newsBulletinCount+'</a></h4>';
								}else{
									str+='<h4>0</h4>';
								}
								
								
								str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
								str+='<h4>'+result.idNameVoList2[i].description != ""?result.idNameVoList2[i].description:"00:00"+'</h4>';
								
							str+='</td>';
							
							str+='<td>';
								str+='<h5 class="text-capitalize">positive</h5>';
								
								if(result.idNameVoList2[i].posCount !=null && result.idNameVoList2[i].posCount>0){
									str+='<h4 class="EMnCls" attr_benefitId="1" attr_party="'+partyId+'" attr_category_id="'+globalcategoryId+'" attr_edition="0" style="cursor:pointer;"><a>'+result.idNameVoList2[i].posCount+'</a></h4>';
									str+='<small id="" class="text-success"> '+result.idNameVoList2[i].posPercent+' %</small>';
								}else{
									str+='<h4>0</h4>';
								}
								
								
								str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
								str+='<h4>'+result.idNameVoList2[i].postiveDesc != ""?result.idNameVoList2[i].postiveDesc:"00:00"+'</h4>';
								
							str+='</td>';
							
							str+='<td>';
								str+='<h5 class="text-capitalize">negative</h5>';
								
								if(result.idNameVoList2[i].negCount !=null && result.idNameVoList2[i].negCount>0){
									str+='<h4 class="EMnCls" attr_benefitId="2" attr_party="'+partyId+'" attr_category_id="'+globalcategoryId+'" attr_edition="0" style="cursor:pointer;"><a>'+result.idNameVoList2[i].negCount+'</a></h4>';
									str+='<small id="" class="text-success"> '+result.idNameVoList2[i].negPercent+' %</small>';
								}else{
									str+='<h4>0</h4>';
								}
								
								
								str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
								str+='<h4>'+result.idNameVoList2[i].negativeDesc != ""?result.idNameVoList2[i].negativeDesc:"00:00"+'</h4>';
								
							str+='</td>';
					str+='</tr>';
				str+='</table>';
				
				str+='</div>';
			}
		}else{
			str+='<h4 class="panel-title m_top10">No Data Available</h4>';
		}
		
	$("#overAllElectronicMediaNewsDivId").html(str);
}

function mainNewsBlockPraja(result){
		var str='';
		str+='';
		str+='<h4 class="text-capital"><span class="headingColor" style="margin-right:5px"><img src="newCoreDashBoard/img/TDP.png" alt="tdp icon" class="newsIcon"/>TDP Party</span></h4>';
		str+='<div class="row">';
			str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">';
				str+='<table class="table table-condensed tableNews bg_ED">';
				if(result.coreDashBoardVOList !=null && result.coreDashBoardVOList.length>0){
					for(var i in result.coreDashBoardVOList){
						str+='<tr>';
							str+='<td>';
								str+='<p class="text-capital responsiveFont">Main&nbsp;Edition</p>';
								if(result.coreDashBoardVOList[i].count !=null && result.coreDashBoardVOList[i].count>0){
									str+='<p><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="0" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].count+'</a></p>';
								}else{
									str+='<p> - </p>';
								}
								
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Positive</p>';
								if(result.coreDashBoardVOList[i].positivePerc !=null && result.coreDashBoardVOList[i].positivePerc>0 && result.coreDashBoardVOList[i].positiveCountMain >0){
									
									str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="1" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].positiveCountMain+'</a></span>';
									str+='<small id="" class="text-success"> '+result.coreDashBoardVOList[i].positivePerc+' %</small>';
								}else{
									str+='<span> - </span>';
								}
								
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
								if(result.coreDashBoardVOList[i].negativePerc !=null && result.coreDashBoardVOList[i].negativePerc>0 && result.coreDashBoardVOList[i].negativCountMain >0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="2" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].negativCountMain+'</a></span>';
									str+='<small id="" class="text-danger"> '+result.coreDashBoardVOList[i].negativePerc+' %</small>';
								}else{
									str+='<span> - </span>';
								}
									
							str+='</td>';
						str+='</tr>';
					}
				}else{
					str+='<tr>';
						str+='<td>No Data Available</td>';
					str+='</tr>';
				}
				
					
					
				str+='</table>';
			str+='</div>';
			str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 pad_left0">';
				str+='<table class="table table-condensed tableNews bg_ED">';
				if(result.coreDashBoardVOList !=null && result.coreDashBoardVOList.length>0){
					for(var i in result.coreDashBoardVOList){
						str+='<tr>';
							str+='<td>';
								str+='<p class="text-capital">Dist&nbsp;edition</p>';
								if(result.coreDashBoardVOList[i].totalCount != null && result.coreDashBoardVOList[i].totalCount >0){
									str+='<p><a class="partyMainEditionCls" attr_editiontype="2" attr_benefitid ="0" attr_benefitid ="0" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].totalCount+'</a></p>';
								}else{
									str+='<span> - </span>';
								}
								
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">Positive</p>';
								if(result.coreDashBoardVOList[i].positiveCountDist != null && result.coreDashBoardVOList[i].positiveCountDist >0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="2" attr_benefitid ="1" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].positiveCountDist+'</a></span>';
									str+='<small class="text-success" id=""> '+result.coreDashBoardVOList[i].positiveDistPerc+' %</small>';
								}else{
									str+='<span> - </span>';
								}
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">Negative</p>';
								if(result.coreDashBoardVOList[i].negativCountDist != null && result.coreDashBoardVOList[i].negativCountDist >0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="2" attr_benefitid ="2" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].negativCountDist+'</a></span>';
									str+='<small class="text-danger" id=""> '+result.coreDashBoardVOList[i].negativeDistPerc+' %</small>';
								}else{
									str+='<span> - </span>';
								}
							str+='</td>';
						str+='</tr>';
					}
				}else{
					str+='<tr>';
						str+='<td> No Data Available';	
						str+='</td>'
					str+='</tr>';
					
				}
				
				str+='</table>';
			str+='</div>';
		str+='</div>';
		
		/* Opposition Parties*/
		str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/opp.png" style="width:25px;" alt="tdp icon" class="debatesPartyIcon"/>Other Parties</span></h4>';
		
			str+='<div class="row">';
				
					str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">';
						str+='<table class="table table-condensed tableNews ">';
						
							str+='<tr class="bg_ED">';
								str+='<td>';
								
									str+='<p class="text-capital">Main&nbsp;Edition</p>';
									/* str+='<p><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid =" " attr_isdepartment = "N" attr_partyids="">100<small>(20%)</small></a></p>'; */
								str+='</td>';
								str+='<td>';
									str+='<p class="text-capital text-muted">Positive</p>';
									/* str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="1" attr_isdepartment = "N" attr_partyids="">50</a></span>';
									str+='<small id="" class="text-success"> 0.0 %</small>'; */
								str+='</td>';
								str+='<td>';
									str+='<p class="text-capital text-muted">Negative</p>';
									/* str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="2" attr_isdepartment = "N" attr_partyids="">50</a></span>';
									str+='<small class="text-danger" id=""> 0.0 %</small>'; */
								str+='</td>';
							str+='</tr>';
							if(result.coreDashBoardVOList1 !=null && result.coreDashBoardVOList1.length>0){
								for(var i in result.coreDashBoardVOList1){
									str+='<tr>';
										str+='<td>';
										if(result.coreDashBoardVOList1[i].count != null && result.coreDashBoardVOList1[i].count >0){
											str+='<img src="newCoreDashBoard/img/'+result.coreDashBoardVOList1[i].organization+'.png" alt="cong logo" class="debatesPartyIcon"/><span><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="0" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].count+'</a></span>';
										}else{
											str+='<span> - </span>';
										}
											
										str+='</td>';
										str+='<td>';
										if(result.coreDashBoardVOList1[i].positiveCountMain != null && result.coreDashBoardVOList1[i].positiveCountMain>0){
											str+='<span><a class="partyMainEditionCls" attr_editiontype="1"  attr_benefitid ="1" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].positiveCountMain+'</a></span>';
											str+='<small class="text-success"> '+result.coreDashBoardVOList1[i].positivePerc+' %</small>';
										}else{
											str+='<span> - </span>';
										}
										str+='</td>';
										str+='<td>';
										if(result.coreDashBoardVOList1[i].negativCountMain != null && result.coreDashBoardVOList1[i].negativCountMain >0){
											str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="2" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].negativCountMain+'</a></span>';
											str+='<small class="text-danger" id=""> '+result.coreDashBoardVOList1[i].negativePerc+' %</small>';
										}else{
											str+='<span> - </span>';
										}
										str+='</td>';
									str+='</tr>';
								}
							}else{
								str+='<tr>';
									str+='<td> No Data Available';
									str+='</td>';
								str+='</tr>';
							}
							
							
						str+='</table>';
					str+='</div>';
				
				str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">';
						str+='<table class="table table-condensed tableNews ">';
						
							str+='<tr class="bg_ED">';
								str+='<td>';
								
									str+='<p class="text-capital">Dist&nbsp;Edition</p>';
									/* str+='<p><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid =" " attr_isdepartment = "N" attr_partyids="">100<small>(20%)</small></a></p>'; */
								str+='</td>';
								str+='<td>';
									str+='<p class="text-capital text-muted">Positive</p>';
									/* str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="1" attr_isdepartment = "N" attr_partyids="">50</a></span>';
									str+='<small id="" class="text-success"> 0.0 %</small>'; */
								str+='</td>';
								str+='<td>';
									str+='<p class="text-capital text-muted">Negative</p>';
									/* str+='<span><a class="newsBasicCountDetailsDiv cursorPo" attr_editiontype="1" attr_benefitid ="2" attr_isdepartment = "N" attr_partyids="">50</a></span>';
									str+='<small class="text-danger" id=""> 0.0 %</small>'; */
								str+='</td>';
							str+='</tr>';
							if(result.coreDashBoardVOList1 !=null && result.coreDashBoardVOList1.length>0){
								for(var i in result.coreDashBoardVOList1){
									str+='<tr>';
										str+='<td>';
										if(result.coreDashBoardVOList1[i].totalCount != null && result.coreDashBoardVOList1[i].totalCount>0){
											str+='<img src="newCoreDashBoard/img/'+result.coreDashBoardVOList1[i].organization+'.png" alt="cong logo" class="debatesPartyIcon"/><span><a class="partyMainEditionCls" attr_editiontype="2"  attr_benefitid ="0" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].totalCount+'</a></span>';
										}	
										str+='</td>';
										str+='<td>';
										if(result.coreDashBoardVOList1[i].positiveCountDist != null && result.coreDashBoardVOList1[i].positiveCountDist>0){
											str+='<span><a class="partyMainEditionCls" attr_editiontype="2"  attr_benefitid ="1" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].positiveCountDist+'</a></span>';
											str+='<small class="text-success"> '+result.coreDashBoardVOList1[i].positiveDistPerc+' %</small>';
										}
										str+='</td>';
										str+='<td>';
										if(result.coreDashBoardVOList1[i].negativCountDist != null && result.coreDashBoardVOList1[i].negativCountDist>0){
											str+='<span><a class="partyMainEditionCls" attr_editiontype="2"  attr_benefitid ="2" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].negativCountDist+'</a></span>';
											str+='<small class="text-danger" id=""> '+result.coreDashBoardVOList1[i].negativeDistPerc+' %</small>';
										}	
										str+='</td>';
									str+='</tr>';
								}
							}else{
								str+='<tr>';
									str+='<td> No Data Available';
									str+='</td>';
								str+='</tr>';
							}
							
							
						str+='</table>';
					str+='</div>';
					str+='</div>';
			/*Government*/
		     str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/GOVT.png" style="width:25px;" alt="government icon" class="newsIcon"/>Government</span></h4>';
			str+='<div class="row">';
				var partyId=0;
					str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">';
						str+='<table class="table table-condensed tableNews ">';
						
							str+='<tr class="bg_ED">';
								str+='<td>';
									str+='<p class="text-capital">Main&nbsp;Edition</p>';
								str+='</td>';
								str+='<td>';
									str+='<p class="text-capital text-muted">Positive</p>';
								str+='</td>';
								str+='<td>';
									str+='<p class="text-capital text-muted">Negative</p>';
								str+='</td>';
							str+='</tr>';
							if(result.coreDashBoardVOList2 !=null && result.coreDashBoardVOList2.length>0){
								for(var i in result.coreDashBoardVOList2){
									str+='<tr>';
										str+='<td>';
										if(result.coreDashBoardVOList2[i].count != null && result.coreDashBoardVOList2[i].count >0){
											str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="0" attr_partyids="'+partyId+'"  attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList2[i].count+'</a></span>';
										}else{
											str+='<span> - </span>';
										}
											
										str+='</td>';
										str+='<td>';
										if(result.coreDashBoardVOList2[i].positiveCountMain != null && result.coreDashBoardVOList2[i].positiveCountMain>0){
											str+='<span><a class="partyMainEditionCls" attr_editiontype="1"  attr_benefitid ="1" attr_partyids="'+partyId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList2[i].positiveCountMain+'</a></span>';
											str+='<small class="text-success"> '+result.coreDashBoardVOList2[i].positivePerc+' %</small>';
										}else{
											str+='<span> - </span>';
										}
										str+='</td>';
										str+='<td>';
										if(result.coreDashBoardVOList2[i].negativCountMain != null && result.coreDashBoardVOList2[i].negativCountMain >0){
											str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="2" attr_partyids="'+partyId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList2[i].negativCountMain+'</a></span>';
											str+='<small class="text-danger" id=""> '+result.coreDashBoardVOList2[i].negativePerc+' %</small>';
										}else{
											str+='<span> - </span>';
										}
										str+='</td>';
									str+='</tr>';
								}
							}else{
								str+='<tr>';
									str+='<td> No Data Available';
									str+='</td>';
								str+='</tr>';
							}
							
							
						str+='</table>';
					str+='</div>';
					str+='<div class="col-md-6 col-xs-12 col-sm-12 m_top10 ">';
						str+='<table class="table table-condensed tableNews ">';
						
							str+='<tr class="bg_ED">';
								str+='<td>';
								
									str+='<p class="text-capital">Dist&nbsp;Edition</p>';
									
								str+='</td>';
								str+='<td>';
									str+='<p class="text-capital text-muted">Positive</p>';
									
								str+='</td>';
								str+='<td>';
									str+='<p class="text-capital text-muted">Negative</p>';
									
								str+='</td>';
							str+='</tr>';
							if(result.coreDashBoardVOList2 !=null && result.coreDashBoardVOList2.length>0){
								for(var i in result.coreDashBoardVOList2){
									str+='<tr>';
										str+='<td>';
										if(result.coreDashBoardVOList2[i].totalCount != null && result.coreDashBoardVOList2[i].totalCount>0){
											str+='<span><a class="partyMainEditionCls" attr_editiontype="2"  attr_benefitid ="0" attr_partyids="'+partyId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList2[i].totalCount+'</a></span>';
										}	
										str+='</td>';
										str+='<td>';
										if(result.coreDashBoardVOList2[i].positiveCountDist != null && result.coreDashBoardVOList2[i].positiveCountDist>0){
											str+='<span><a class="partyMainEditionCls" attr_editiontype="2"  attr_benefitid ="1" attr_partyids="'+partyId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList2[i].positiveCountDist+'</a></span>';
											str+='<small class="text-success"> '+result.coreDashBoardVOList2[i].positiveDistPerc+' %</small>';
										}
										str+='</td>';
										str+='<td>';
										if(result.coreDashBoardVOList2[i].negativCountDist != null && result.coreDashBoardVOList2[i].negativCountDist>0){
											str+='<span><a class="partyMainEditionCls" attr_editiontype="2"  attr_benefitid ="2" attr_partyids="'+partyId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList2[i].negativCountDist+'</a></span>';
											str+='<small class="text-danger" id=""> '+result.coreDashBoardVOList2[i].negativeDistPerc+' %</small>';
										}	
										str+='</td>';
									str+='</tr>';
								}
							}else{
								str+='<tr>';
									str+='<td> No Data Available';
									str+='</td>';
								str+='</tr>';
							}
							
							
						str+='</table>';
					str+='</div>';
			str+='</div>';
		$("#overAllPrintMediaNewsDivId").html(str);
	}

function getPublicationWisePartiesAnalysis(categoryId){
	$("#publicationWisePartiesDivId").html("");
	$("#publicationWisePartiesDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getPublicationWisePartiesAnalysis/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getPublicationWisePartiesAnalysis/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
	}).then(function(result){
		if(result !=null && result.length>0){
			buildPublicationWisePartiesAnalysis(result);
		}else{
			$("#publicationWisePartiesDivId").html("No Data Available");
		}
	});
}

function getDistrictWisePartyOverView(categoryId){
	$("#districtWisePartiesDivId").html("");
	$("#districtWisePartiesDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getDistrictWisePartyOverView/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWisePartyOverView/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
	}).then(function(result){
		if(result !=null && result.length>0){
			buildDistrictWisePartyOverView(result);
		}else{
			$("#districtWisePartiesDivId").html("No Data Available");
		}
	});
}

function getChannelWisePartiesAnalysisInfo(categoryId){
	$("#ChannelWisePartyDivId").html("");
	$("#ChannelWisePartyDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisInfo/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisInfo/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
	}).then(function(result){
		if(result !=null && result.length>0){
			buildChannelWisePartiesAnalysisInfo(result);
		}else{
			$("#ChannelWisePartyDivId").html("No Data Available");
		}
	});
}

function getDistrictWisePartyViewForElectrronicMediaInfo(categoryId){
	$("#districtWiseChannelDivId").html("");
	$("#districtWiseChannelDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getDistrictWisePartyViewForElectrronicMediaInfo/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWisePartyViewForElectrronicMediaInfo/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId
	}).then(function(result){
		if(result !=null && result.length>0){
			buildDistrictWisePartyViewForElectrronicMediaInfo(result);
		}else{
			$("#districtWiseChannelDivId").html("No Data Available");
		}
	});
}

function buildPublicationWisePartiesAnalysis(result){
	var str='';
	
	str+='<h4 class="text-capital m_top10"><span class="headingColor">Publication Wise Party Counts</span></h4>';
	str+='<div class="table-responsive m_top10">';
	str+='<table class="table table-bordered table_custom_praja" id="dataTablepublicationWiseParties">';
			str+='<thead style="background-color:#7773;">';
				str+='<tr>';
					str+='<th rowspan="2">Publication</th>';
					for(var i in result[0].coreDashBoardVOList){
						str+='<th colspan="4">'+result[0].coreDashBoardVOList[i].organization+'</th>';
					}
				str+='</tr>';
				str+='<tr>';
					for(var i in result[0].coreDashBoardVOList){
						str+='<th>+ve </th>';
						str+='<th>%</th>';
						str+='<th>-ve </th>';
						str+='<th>%</th>';
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				
				for(var i in result){
					str+='<tr>';
							str+='<td>'+result[i].organization+'</td>';
							for(var j in result[i].coreDashBoardVOList){
								if(result[i].coreDashBoardVOList[j].positivePerc !=null && result[i].coreDashBoardVOList[j].positivePerc>0){
									str+='<td><a class="publicationWiseCntCls" attr_benefitid="1" attr_paper_type="'+result[i].organizationId+'" attr_party="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_categoryid="'+globalcategoryId+'" style="cursor:pointer;">'+result[i].coreDashBoardVOList[j].positiveCountMain+'</a></td>';
									str+='<td><small style="color:green;">'+result[i].coreDashBoardVOList[j].positivePerc+' </small></td>';
								}else{
									str+='<td> - </td>';
									str+='<td> - </td>';
								}
								if(result[i].coreDashBoardVOList[j].negativePerc !=null && result[i].coreDashBoardVOList[j].negativePerc>0){
									str+='<td><a class="publicationWiseCntCls" attr_benefitid="2" attr_paper_type="'+result[i].organizationId+'" attr_party="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_categoryid="'+globalcategoryId+'" style="cursor:pointer;">'+result[i].coreDashBoardVOList[j].negativCountMain+'</a></td>'; 
									str+='<td><small style="color:green;">'+result[i].coreDashBoardVOList[j].negativePerc+' </small></td>';
								}else{
									str+='<td> - </td>';
									str+='<td> - </td>';
								}
								
								
							}
						str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
		str+='</div>';
		
		$("#publicationWisePartiesDivId").html(str);
		$("#dataTablepublicationWiseParties").dataTable({
				"iDisplayLength": 10,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			});
}
function buildChannelWisePartiesAnalysisInfo(result){
	var str='';
	str+='<h4 class="text-capital m_top10"><span class="headingColor">Channel Wise Party Counts</span></h4>';
	str+='<div class="table-responsive m_top10">';
	str+='<table class="table table-bordered table_custom_praja" id="dataTableChannelWiseParties">';
			str+='<thead style="background-color:#7773;">';
				str+='<tr>';
					str+='<th rowspan="2">Channel</th>';
					for(var i in result[0].coreDashBoardVOList){
						str+='<th colspan="4">'+result[0].coreDashBoardVOList[i].organization+'</th>';
					}
				str+='</tr>';
				str+='<tr>';
					for(var i in result[0].coreDashBoardVOList){
						str+='<th>+ve </th>';
						str+='<th>%</th>';
						str+='<th>-ve</th>';
						str+='<th>%</th>';
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				
				for(var i in result){
					str+='<tr>';
							str+='<td>'+result[i].organization+'</td>';
							for(var j in result[i].coreDashBoardVOList){
								if(result[i].coreDashBoardVOList[j].positivePerc !=null && result[i].coreDashBoardVOList[j].positivePerc>0){
									str+='<td><a class="channelWiseCntsForEMNcls" attr_benefit_id="1" attr_categoryId="'+globalcategoryId+'" attr_party="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_channelid="'+result[i].organizationId+'" style="cursor:pointer;">'+result[i].coreDashBoardVOList[j].positiveCountMain+'</a></td>'; 
									str+='<td><small style="color:green;">'+result[i].coreDashBoardVOList[j].positivePerc+' </small></td>';
								}else{
									str+='<td> - </td>';
									str+='<td> - </td>';
								}
								if(result[i].coreDashBoardVOList[j].negativePerc !=null && result[i].coreDashBoardVOList[j].negativePerc>0){
									str+='<td><a class="channelWiseCntsForEMNcls" attr_benefit_id="2" attr_categoryId="'+globalcategoryId+'" attr_party="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_channelid="'+result[i].organizationId+'" style="cursor:pointer;">'+result[i].coreDashBoardVOList[j].negativCountMain+'</a></td>';
									str+='<td><small style="color:green;">'+result[i].coreDashBoardVOList[j].negativePerc+' </small></td>';
								}else{
									str+='<td> - </td>';
									str+='<td> - </td>';
								}
								
								
							}
						str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
		str+='</div>';
		
		$("#ChannelWisePartyDivId").html(str);
		$("#dataTableChannelWiseParties").dataTable({
				"iDisplayLength": 10,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			});
}
function buildDistrictWisePartyOverView(result){
	var str='';
	str+='<h4 class="text-capital m_top10"><span class="headingColor">District Wise Party Counts</span></h4>';
	str+='<div class="table-responsive m_top10">';
	str+='<table class="table table-bordered table_custom_praja" id="dataTableDistrictWiseParties">';
			str+='<thead style="background-color:#7773;">';
				str+='<tr>';
					str+='<th rowspan="2">District</th>';
					for(var i in result[0].coreDashBoardVOList){
						str+='<th colspan="4">'+result[0].coreDashBoardVOList[i].organization+'</th>';
					}
				str+='</tr>';
				str+='<tr>';
					for(var i in result[0].coreDashBoardVOList){
						str+='<th>+ve </th>';
						str+='<th>% </th>';
						str+='<th>-ve </th>';
						str+='<th>% </th>';
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				
				for(var i in result){
					str+='<tr>';
							str+='<td>'+result[i].organization+'</td>';
							for(var j in result[i].coreDashBoardVOList){
								if(result[i].coreDashBoardVOList[j].positivePerc !=null && result[i].coreDashBoardVOList[j].positivePerc>0){
									str+='<td><a class="districtWisePartyForPmCls" attr_benefit_id="1" attr_categoryId="'+globalcategoryId+'" attr_party="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_districtId="'+result[i].organizationId+'" style="cursor:pointer;">'+result[i].coreDashBoardVOList[j].positiveCountMain+'</a></td>'; 
									str+='<td><small style="color:green;">'+result[i].coreDashBoardVOList[j].positivePerc+' </small></td>';
								}else{
									str+='<td> - </td>';
									str+='<td> - </td>';
								}
								if(result[i].coreDashBoardVOList[j].negativePerc !=null && result[i].coreDashBoardVOList[j].negativePerc>0){
									str+='<td><a class="districtWisePartyForPmCls" attr_benefit_id="2" attr_categoryId="'+globalcategoryId+'" attr_party="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_districtId="'+result[i].organizationId+'" style="cursor:pointer;">'+result[i].coreDashBoardVOList[j].negativCountMain+'</a></td>'; 
									str+='<td><small style="color:green;">'+result[i].coreDashBoardVOList[j].negativePerc+' </small></td>';
								}else{
									str+='<td> - </td>';
									str+='<td> - </td>';
								}
								
								
							}
						str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
		str+='</div>';
		
		$("#districtWisePartiesDivId").html(str);
		$("#dataTableDistrictWiseParties").dataTable({
				"iDisplayLength": 10,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			});
}
function buildDistrictWisePartyViewForElectrronicMediaInfo(result){
	var str='';
	str+='<h4 class="text-capital m_top10"><span class="headingColor">District Wise Party Counts</span></h4>';
	str+='<div class="table-responsive m_top10">';
	str+='<table class="table table-bordered table_custom_praja" id="dataTableDistrictWiseChannels">';
			str+='<thead style="background-color:#7773;">';
				str+='<tr>';
					str+='<th rowspan="2">District</th>';
					for(var i in result[0].coreDashBoardVOList){
						str+='<th colspan="4">'+result[0].coreDashBoardVOList[i].organization+'</th>';
					}
				str+='</tr>';
				str+='<tr>';
					for(var i in result[0].coreDashBoardVOList){
						str+='<th>+ve </th>';
						str+='<th>% </th>';
						str+='<th>-ve </th>';
						str+='<th>% </th>';
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				
				for(var i in result){
					str+='<tr>';
							str+='<td>'+result[i].organization+'</td>';
							for(var j in result[i].coreDashBoardVOList){
								if(result[i].coreDashBoardVOList[j].positivePerc !=null && result[i].coreDashBoardVOList[j].positivePerc>0){
									str+='<td><a class="districtWisePartyForEMNCls" attr_benefit_id="1" attr_categoryId="'+globalcategoryId+'" attr_party="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_districtId="'+result[i].organizationId+'" style="cursor:pointer;">'+result[i].coreDashBoardVOList[j].positiveCountMain+'</a></td>'; 
									str+='<td><small style="color:green;">'+result[i].coreDashBoardVOList[j].positivePerc+' </small></td>';
								}else{
									str+='<td> - </td>';
									str+='<td> - </td>';
								}
								if(result[i].coreDashBoardVOList[j].negativePerc !=null && result[i].coreDashBoardVOList[j].negativePerc>0){
									str+='<td><a class="districtWisePartyForEMNCls" attr_benefit_id="2" attr_categoryId="'+globalcategoryId+'" attr_party="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_districtId="'+result[i].organizationId+'" style="cursor:pointer;">'+result[i].coreDashBoardVOList[j].negativCountMain+'</a></td>'; 
									str+='<td><small style="color:green;">'+result[i].coreDashBoardVOList[j].negativePerc+' </small></td>';
								}else{
									str+='<td> - </td>';
									str+='<td> - </td>';
								}
								
								
							}
						str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
		str+='</div>';
		
		$("#districtWiseChannelDivId").html(str);
		$("#dataTableDistrictWiseChannels").dataTable({
				"iDisplayLength": 10,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			});
}
$(document).on("click","[role='tabCummulative'] li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var blockType = $(this).attr("attr_value");
	
	if(blockType == "print"){
		$(".publicationWisePartiesCls").show();
		$(".districtWisePartiesCls").show();
		$(".ChannelWisePartyCls").hide();
		$(".districtWiseChannelCls").hide();
		$("#publicationWisePartiesDivId").html('');
		$("#districtWisePartiesDivId").html('');
		
		getPublicationWisePartiesAnalysis(globalcategoryId);// Expand Block Print Media
		getDistrictWisePartyOverView(globalcategoryId);// Expand Block Print Media District
		
	}else{
		$(".publicationWisePartiesCls").hide();
		$(".districtWisePartiesCls").hide();
		$(".ChannelWisePartyCls").show();
		$(".districtWiseChannelCls").show();
		$("#ChannelWisePartyDivId").html('');
		$("#districtWiseChannelDivId").html('');
		getChannelWisePartiesAnalysisInfo(globalcategoryId);// Expand Block Electronic Media
		getDistrictWisePartyViewForElectrronicMediaInfo(globalcategoryId);// Expand Block Electronic Media//District
	}
	
});
$(document).on("change","#categoryId",function(){
	var categoryName=$(this).find('option:selected').text();
	if(categoryName=='KAPU RESERVATIONS')
	{
		$('#prajaSankalpaYatraDivId').html(categoryName+"-<span style='color:green;'>5%</span>");
	}
	else{
	$('#prajaSankalpaYatraDivId').html(categoryName+"-");
	}	
	globalcategoryId = $(this).val();
	onloadPrajaSankaplaYatraCalls();
});
$(document).on("click",".partyMainEditionCls",function(){
	var editionType="Main";
	var attrBenifitId=$(this).attr('attr_benefitid');
	var attrEditionType=$(this).attr('attr_editiontype');
	var attrPartyids=$(this).attr('attr_partyids');
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
$("#popImgDiv").html(spinner);
	$.ajax({	
       url: wurl+"/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysisForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+attrCategoryid+"/"+attrPartyids+"/"+attrBenifitId+"/"+attrEditionType
		//url: "http://192.168.11.173:8080/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysisForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+attrCategoryid+"/"+attrPartyids+"/"+attrBenifitId+"/"+attrEditionType
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
							str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_region_scopeid="+jsonObject[0].stateVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].stateVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].stateVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].stateVoList[i].locationName+"</span></h5>";
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
							str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_region_scopeid="+jsonObject[0].districtVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].districtVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].districtVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].districtVoList[i].locationName+"</span></h5>";
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
						str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_region_scopeid="+jsonObject[0].panchayatVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].panchayatVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].panchayatVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].panchayatVoList[i].locationName+"</span></h5>";
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
						str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_region_scopeid="+jsonObject[0].mandalVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].mandalVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].mandalVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].mandalVoList[i].locationName+"</span></h5>";
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
						str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_region_scopeid="+jsonObject[0].constoList[i].scopeId+" attr_scopeValue="+jsonObject[0].constoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].constoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].constoList[i].locationName+"</span></h5>";
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
						str+="<h5><span class='articleWisePrintMediaCls roundCssCls' attr_region_scopeid="+jsonObject[0].corpGmcVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].corpGmcVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].corpGmcVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].corpGmcVoList[i].locationName+"</span></h5>";
						str+="</li>";
					}
			str+='</ul>';
			
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraCorpTableId').html(str);
		}	
			
	
}
function buildingPmTable2(jsonObject){
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
						str+="<h5><span class='articleWisePMPartyCls roundCssCls' attr_region_scopeid="+jsonObject[0].stateVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].stateVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].stateVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].stateVoList[i].locationName+"</span></h5>";
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
						str+="<h5><span class='articleWisePMPartyCls roundCssCls' attr_region_scopeid="+jsonObject[0].districtVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].districtVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].districtVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].districtVoList[i].locationName+"</span></h5>";
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
						str+="<h5><span class='articleWisePMPartyCls roundCssCls' attr_region_scopeid="+jsonObject[0].panchayatVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].panchayatVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].panchayatVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].panchayatVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePMPartyCls roundCssCls' attr_region_scopeid="+jsonObject[0].mandalVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].mandalVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].mandalVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].mandalVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePMPartyCls roundCssCls' attr_region_scopeid="+jsonObject[0].constoList[i].scopeId+" attr_scopeValue="+jsonObject[0].constoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].constoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].constoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePMPartyCls roundCssCls' attr_region_scopeid="+jsonObject[0].corpGmcVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].corpGmcVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].corpGmcVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].corpGmcVoList[i].locationName+"</span></h5>";
						str+="</li>";
					}
			str+='</ul>';
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraCorpTableId').html(str);
		}
}
function buildingPmTable3(jsonObject){
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePMDistCls roundCssCls' attr_region_scopeid="+jsonObject[0].stateVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].stateVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].stateVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].stateVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePMDistCls roundCssCls' attr_region_scopeid="+jsonObject[0].districtVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].districtVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].districtVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].districtVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePMDistCls roundCssCls' attr_region_scopeid="+jsonObject[0].panchayatVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].panchayatVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].panchayatVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].panchayatVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePMDistCls roundCssCls' attr_region_scopeid="+jsonObject[0].mandalVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].mandalVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].mandalVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].mandalVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePMDistCls roundCssCls' attr_region_scopeid="+jsonObject[0].constoList[i].scopeId+" attr_scopeValue="+jsonObject[0].constoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].constoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].constoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePMDistCls roundCssCls' attr_region_scopeid="+jsonObject[0].corpGmcVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].corpGmcVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].corpGmcVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].corpGmcVoList[i].locationName+"</span></h5>";
						str+="</li>";
					}
			str+='</ul>';
			
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraCorpTableId').html(str);
		}
}
function buildingTable1(jsonObject)
{
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWiseEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].stateVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].stateVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].stateVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].stateVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWiseEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].districtVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].districtVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].districtVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].districtVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWiseEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].panchayatVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].panchayatVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].panchayatVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].panchayatVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWiseEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].mandalVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].mandalVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].mandalVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].mandalVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWiseEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].constoList[i].scopeId+" attr_scopeValue="+jsonObject[0].constoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].constoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].constoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWiseEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].corpGmcVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].corpGmcVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].corpGmcVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].corpGmcVoList[i].locationName+"</span></h5>";
						str+="</li>";
					}
			str+='</ul>';	
			
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraCorpTableId').html(str);
		}	
			
	
}
function buildingTable2(jsonObject)
{
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePartyEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].stateVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].stateVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].stateVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].stateVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePartyEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].districtVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].districtVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].districtVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].districtVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePartyEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].panchayatVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].panchayatVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].panchayatVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].panchayatVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePartyEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].mandalVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].mandalVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].mandalVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].mandalVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePartyEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].constoList[i].scopeId+" attr_scopeValue="+jsonObject[0].constoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].constoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].constoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWisePartyEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].corpGmcVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].corpGmcVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].corpGmcVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].corpGmcVoList[i].locationName+"</span></h5>";
						str+="</li>";
					}
			str+='</ul>';
	
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraCorpTableId').html(str);
		}	
			
	
}
function buildingTable3(jsonObject){
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWiseDistEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].stateVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].stateVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].stateVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].stateVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWiseDistEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].districtVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].districtVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].districtVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].districtVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWiseDistEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].panchayatVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].panchayatVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].panchayatVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].panchayatVoList[i].locationName+"</span></h5>";
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
						str+="<li class='text-capital' style='margin-left:10px;'>";
						str+="<h5><span class='articleWiseDistEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].mandalVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].mandalVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].mandalVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].mandalVoList[i].locationName+"</span></h5>";
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
						str+="<h5><span class='articleWiseDistEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].constoList[i].scopeId+" attr_scopeValue="+jsonObject[0].constoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].constoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].constoList[i].locationName+"</span></h5>";
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
						str+="<h5><span class='articleWiseDistEmnCls roundCssCls' attr_region_scopeid="+jsonObject[0].corpGmcVoList[i].scopeId+" attr_scopeValue="+jsonObject[0].corpGmcVoList[i].scopeValue+" style='cursor:pointer;'>"+jsonObject[0].corpGmcVoList[i].count+"</span>&nbsp;<span class='f-12'>"+jsonObject[0].corpGmcVoList[i].locationName+"</span></h5>";
						str+="</li>";
					}
			str+='</ul>';
	
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraCorpTableId').html(str);
		}	
			
	
}
$(document).on("click",".publicationWiseCntCls",function(){
	var benefitId=$(this).attr('attr_benefitid');
	var paperId=$(this).attr('attr_paper_type');
	var partyId=$(this).attr('attr_party');
	var categoryid=$(this).attr('attr_categoryid');
	globalPartyId = partyId;
	globalBenefitId = benefitId;
	globalChannelId=paperId;
	getPublicationWisePartiesAnalysisForArticles(benefitId,paperId,partyId,categoryid);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
	$("#prajaSankalpaHeadingId").html("Location Wise Articles");
});
$(document).on("click",".districtWisePartyForPmCls",function(){
	var benefitId=$(this).attr('attr_benefit_id');
	var districtId=$(this).attr('attr_districtId');
	var partyId=$(this).attr('attr_party');
	var categoryid=$(this).attr('attr_categoryId');
	globalPartyId = partyId;
	globalBenefitId = benefitId;
	globalChannelId=districtId;
	getDistrictWisePartyOverViewForArticles(benefitId,districtId,partyId,categoryid);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
	$("#prajaSankalpaHeadingId").html("Location Wise Articles");
});
$(document).on("click",".channelWiseCntsForEMNcls",function(){
	var benefitId=$(this).attr('attr_benefit_id');
	var channelId=$(this).attr('attr_channelid');
	var partyId=$(this).attr('attr_party');
	var categoryid=$(this).attr('attr_categoryId');
	globalPartyId = partyId;
	globalBenefitId = benefitId;
	globalChannelId=channelId;
	getChannelWisePartiesAnalysisForArticles(benefitId,channelId,partyId,categoryid);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
	$("#prajaSankalpaHeadingId").html("Location Wise News");
});
$(document).on("click",".districtWisePartyForEMNCls",function(){
	var benefitId=$(this).attr('attr_benefit_id');
	var districtId=$(this).attr('attr_districtId');
	var partyId=$(this).attr('attr_party');
	var categoryid=$(this).attr('attr_categoryId');
	globalPartyId = partyId;
	globalBenefitId = benefitId;
	globalChannelId=districtId;
	getDistrictWisePartyViewForElectrronicMediaInfoForArticles(benefitId,districtId,partyId,categoryid);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
	$("#prajaSankalpaHeadingId").html("Location Wise News");
});
$(document).on("click",".EMnCls",function(){
	var benefitId=$(this).attr('attr_benefitId');
	var editionId=$(this).attr('attr_edition');
	var partyId=$(this).attr('attr_party');
	globalPartyId = partyId;
	globalBenefitId = benefitId;
	globalChannelId=editionId;
	var categoryid=$(this).attr('attr_category_id');
	getChannelWisePartiesAnalysisInfoForArticles(benefitId,editionId,partyId,categoryid);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
	$("#prajaSankalpaHeadingId").html("Location Wise News");
});
function getPublicationWisePartiesAnalysisForArticles(benefitId,paperId,partyId,categoryId){
	$("#popImgDiv").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getPublicationWisePartiesAnalysisForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+paperId
		//url: "http://192.168.11.173:8080/CommunityNewsPortal/webservice/getPublicationWisePartiesAnalysisForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+paperId
	}).then(function(result){
		$("#popImgDiv").html("");
		if(result != null && result.length >0){
		buildingPmTable2(result);
		}
	});
}
function getDistrictWisePartyOverViewForArticles(benefitId,districtId,partyId,categoryId){
	$("#popImgDiv").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getDistrictWisePartyOverViewForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+districtId
		//url: "http://192.168.11.173:8080/CommunityNewsPortal/webservice/getDistrictWisePartyOverViewForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+districtId
	}).then(function(result){
		$("#popImgDiv").html("");
		if(result != null && result.length >0){
		buildingPmTable3(result);
		}
	});
}
function getChannelWisePartiesAnalysisForArticles(benefitId,channelId,partyId,categoryId){
	$("#popImgDiv").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+channelId
		//url: "http://192.168.11.173:8080/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+channelId
	}).then(function(result){
		$("#popImgDiv").html("");
		if(result != null && result.length >0){
		buildingTable2(result);
		}
	});
}
function getDistrictWisePartyViewForElectrronicMediaInfoForArticles(benefitId,districtId,partyId,categoryId){
	$("#popImgDiv").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getDistrictWisePartyViewForElectrronicMediaInfoForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+districtId
		//url: "http://192.168.11.173:8080/CommunityNewsPortal/webservice/getDistrictWisePartyViewForElectrronicMediaInfoForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+districtId
	}).then(function(result){
		$("#popImgDiv").html("");
		buildingTable3(result);
	});
}
function getChannelWisePartiesAnalysisInfoForArticles(benefitId,editionId,partyId,categoryId){
	 $("#popImgDiv").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisInfoForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+editionId
		//url: "http://192.168.11.173:8080/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisInfoForArticles/"+currentFromDatePa+"/"+currentToDatePa+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+editionId
	}).then(function(result){
		$("#popImgDiv").html("");
		if(result != null && result.length >0){
			buildingTable1(result);
		}
	});
}

$(document).on("click",".articleWiseEmnCls",function(){
		var scopeId = $(this).attr("attr_region_scopeid"); 
		var scopeValue = $(this).attr("attr_scopeValue");
		
		window.open('showElectronicBulletinsAction.action?organizationId='+globalPartyId+'&benefitId='+globalBenefitId+'&categoryId='+globalcategoryId+'&sdat='+currentFromDatePa+'&edat='+currentToDatePa+'&npsStr='+globalChannelId+'&levelId='+scopeId+'&temp='+scopeValue+'&type=mainBlock&&orgType=Y&stIdx=0&edIdx=6&callFrom=prajaSankalpa');
});
$(document).on("click",".articleWiseDistEmnCls",function(){
		var scopeId = $(this).attr("attr_region_scopeid"); 
		var scopeValue = $(this).attr("attr_scopeValue");
		
		window.open('showElectronicBulletinsAction.action?organizationId='+globalPartyId+'&benefitId='+globalBenefitId+'&categoryId='+globalcategoryId+'&sdat='+currentFromDatePa+'&edat='+currentToDatePa+'&npsStr='+globalChannelId+'&levelId='+scopeId+'&temp='+scopeValue+'&type=distWiseCnt&&orgType=Y&stIdx=0&edIdx=6&callFrom=prajaSankalpa');
});
$(document).on("click",".articleWisePartyEmnCls",function(){
		var scopeId = $(this).attr("attr_region_scopeid"); 
		var scopeValue = $(this).attr("attr_scopeValue");
		
		window.open('showElectronicBulletinsAction.action?organizationId='+globalPartyId+'&benefitId='+globalBenefitId+'&categoryId='+globalcategoryId+'&sdat='+currentFromDatePa+'&edat='+currentToDatePa+'&npsStr='+globalChannelId+'&levelId='+scopeId+'&temp='+scopeValue+'&type=partyWiseCnt&&orgType=Y&stIdx=0&edIdx=6&callFrom=prajaSankalpa');
});
$(document).on("click",".articleWisePrintMediaCls",function(){
		var scopeId = $(this).attr("attr_region_scopeid"); 
		var scopeValue = $(this).attr("attr_scopeValue");
		
		window.open('showArticlesAction.action?organizationId='+globalPartyId+'&benefitId='+globalBenefitId+'&categoryId='+globalcategoryId+'&sdat='+currentFromDatePa+'&edat='+currentToDatePa+'&npsStr='+globalChannelId+'&levelId='+scopeId+'&temp='+scopeValue+'&type=OverAllprintMedia&&orgType=Y&stIdx=0&edIdx=6&callFrom=prajaSankalpa');
});
$(document).on("click",".articleWisePMPartyCls",function(){
		var scopeId = $(this).attr("attr_region_scopeid"); 
		var scopeValue = $(this).attr("attr_scopeValue");
		
		window.open('showArticlesAction.action?organizationId='+globalPartyId+'&benefitId='+globalBenefitId+'&categoryId='+globalcategoryId+'&sdat='+currentFromDatePa+'&edat='+currentToDatePa+'&npsStr='+globalChannelId+'&levelId='+scopeId+'&temp='+scopeValue+'&type=PartyprintMedia&&orgType=Y&stIdx=0&edIdx=6&callFrom=prajaSankalpa');
});
$(document).on("click",".articleWisePMDistCls",function(){
		var scopeId = $(this).attr("attr_region_scopeid"); 
		var scopeValue = $(this).attr("attr_scopeValue");
		
		window.open('showArticlesAction.action?organizationId='+globalPartyId+'&benefitId='+globalBenefitId+'&categoryId='+globalcategoryId+'&sdat='+currentFromDatePa+'&edat='+currentToDatePa+'&npsStr='+globalChannelId+'&levelId='+scopeId+'&temp='+scopeValue+'&type=PublicationprintMedia&&orgType=Y&stIdx=0&edIdx=6&callFrom=prajaSankalpa');
});
$(document).on("click",".refreshPrajaSankalpaCls",function(){
	onloadPrajaSankaplaYatraCalls();
});