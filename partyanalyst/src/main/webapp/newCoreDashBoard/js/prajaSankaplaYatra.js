	var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

	var currentFromDate = moment().format("DD-MM-YYYY");
	var currentToDate = moment().format("DD-MM-YYYY");
	var globalcategoryId = $("#categoryId").find("option:selected").val();
	var categoryName = $("#categoryName").find("option:selected").text();
	$(".chosen-select").chosen();
	$("#prajaHeadDate").html("Today("+currentFromDate+")"); 
	function globalPrajaSankalpaYatraCalls(type)
	{
		if(type == "default"){
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setStartDate(moment());
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setEndDate(moment());
			currentFromDate = moment().format("DD-MM-YYYY")
			toDate = moment().format("DD-MM-YYYY")
			$("#prajaHeadDate").html("Today("+currentFromDate+")");
		}else if(type == "currentMonth"){
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setStartDate(moment().startOf("month"));
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setEndDate(moment().endOf("month"));
			currentFromDate = moment().startOf("month").format("DD-MM-YYYY")
			toDate = moment().endOf("month").format("DD-MM-YYYY")
			$("#prajaHeadDate").html("THIS MONTH"+" ( "+moment().startOf("month").format("DD-MM-YYYY")+"-"+moment().endOf("month").format("DD-MM-YYYY")+" )");
		}else if(type == "lastMonth"){
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setStartDate(moment().subtract(1,'month').startOf("month"));
			$('#dateRangePrajaSankalpaId').data('daterangepicker').setEndDate(moment().subtract(1,'month').endOf("month"));
			currentFromDate = moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY")
			toDate = moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")
			$("#prajaHeadDate").html("LAST MONTH"+" ( "+moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY")+"-"+moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")+" )");
		}
		$("#dateRangePrajaSankalpaId").val(fromDate+" - "+toDate);
		onloadPrajaSankaplaYatraCalls();
		 
	}

	$("#dateRangePrajaSankalpaId").daterangepicker({
		opens: 'left',
		startDate:currentFromDate,
		//minDate:"06-11-2017",
		endDate: currentToDate,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		ranges: {
			'Today': [moment(), moment()],
		   'This Month': [moment().startOf("month").format("DD-MM-YYYY"), moment().endOf('month').format("DD-MM-YYYY")],
		   'Last Month': [moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY"),moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   'This Year': [moment().startOf('Year'), moment()]
		}
	});
	$('#dateRangePrajaSankalpaId').on('apply.daterangepicker', function(ev, picker) {
	  currentFromDate = picker.startDate.format('DD-MM-YYYY');
	  currentToDate = picker.endDate.format('DD-MM-YYYY');
	  $("#prajaHeadDate").html("("+picker.startDate.format("DD/MM/YY")+" to "+picker.endDate.format("DD/MM/YY")+")");
	  onloadPrajaSankaplaYatraCalls();
	  if($(".prajaSankaplaYatraIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
			getChannelWisePartiesAnalysis(globalcategoryId); // Main Block Electronic Media
		}
		$("[role='tabCummulative'] li").removeClass("active");
		$("[role='tabCummulative'] li:nth-child(1)").addClass("active");
		setTimeout(function(){
			getPublicationWisePartiesAnalysis(globalcategoryId);// Expand Block Print Media
			getDistrictWisePartyOverView(globalcategoryId);// Expand Block Print Media District
		},2000);
		
		
	});

function onloadPrajaSankaplaYatraCalls(){
	getEditionTypeWisePartiesAnalysis(globalcategoryId); //Main Block Print Media
	if($(".prajaSankaplaYatraIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
		getChannelWisePartiesAnalysis(globalcategoryId); // Main Block Electronic Media
	}
	 if($(".moreNewsPrajaSankalpaBlocksIcon").hasClass("expandBlock")){
		 $("[role='tabCummulative'] li").removeClass("active");
		 $("[role='tabCummulative'] li:nth-child(1)").addClass("active");
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
		
		getPublicationWisePartiesAnalysis(globalcategoryId);// Expand Block Print Media
		getDistrictWisePartyOverView(globalcategoryId);// Expand Block Print Media District
});
$(document).on("click",".expandBlock",function(){
		$(this).removeClass("expandBlock");
		$(".moreNewsprajaSankaplaBlocksDetailed").hide(); 
		
});
function getEditionTypeWisePartiesAnalysis(categoryId){
	$("#overAllPrintMediaNewsDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysis/"+currentFromDate+"/"+currentToDate+"/"+categoryId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysis/"+currentFromDate+"/"+currentToDate+"/"+categoryId
	}).then(function(result){
		if(result !=null){
			mainNewsBlockPraja(result);
		}else{
			$("#overAllPrintMediaNewsDivId").html("No Data Available");
		}
	});
}

function getChannelWisePartiesAnalysis(categoryId){
	$("#overAllElectronicMediaNewsDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysis/"+currentFromDate+"/"+currentToDate+"/"+categoryId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysis/"+currentFromDate+"/"+currentToDate+"/"+categoryId
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
							str+='<h4 class="EMnCls" attr_benefitId="0" attr_party="'+result.idNameVoList[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="2" style="cursor:pointer;"><a>'+totalCount+'</a></h4>';
						}else{
							str+='<h4>0</h4>';
						}
						
						
						str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
						if(result.idNameVoList[i].description!= ""){
							str+='<h4>'+result.idNameVoList[i].description+'</h4>';
						}else{
							str+='<h4>00:00</h4>';
						}
					str+='</td>';
					str+='<td>';
						str+='<h5 class="text-capitalize">positive</h5>';
						
						if(result.idNameVoList[i].posCount !=null && result.idNameVoList[i].posCount>0){
							str+='<h4 class="EMnCls" attr_benefitId="1" attr_party="'+result.idNameVoList[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="2" style="cursor:pointer;"><a>'+result.idNameVoList[i].posCount+'</a></h4>';
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
							str+='<h4 class="EMnCls" attr_benefitId="2" attr_party="'+result.idNameVoList[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="2" style="cursor:pointer;"><a>'+result.idNameVoList[i].negCount+'</a></h4>';
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
									str+='<h4 class="EMnCls" attr_benefitId="0" attr_party="'+result.idNameVoList1[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="2" style="cursor:pointer;"><a>'+newsBulletinCount+'</a></h4>';
								}else{
									str+='<h4>0</h4>';
								}
								
								
								str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
								str+='<h4>'+result.idNameVoList1[i].description != ""?result.idNameVoList1[i].description:"00:00"+'</h4>';
								
							str+='</td>';
							
							str+='<td>';
								str+='<h5 class="text-capitalize">positive</h5>';
								
								if(result.idNameVoList1[i].posCount !=null && result.idNameVoList1[i].posCount>0){
									str+='<h4 class="EMnCls" attr_benefitId="1" attr_party="'+result.idNameVoList1[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="2" style="cursor:pointer;"><a>'+result.idNameVoList1[i].posCount+'</a></h4>';
								}else{
									str+='<h4>0</h4>';
								}
								
								
								str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
								str+='<h4>'+result.idNameVoList1[i].postiveDesc != ""?result.idNameVoList1[i].postiveDesc:"00:00"+'</h4>';
								
							str+='</td>';
							
							str+='<td>';
								str+='<h5 class="text-capitalize">negative</h5>';
								
								if(result.idNameVoList1[i].negCount !=null && result.idNameVoList1[i].negCount>0){
									str+='<h4 class="EMnCls" attr_benefitId="2" attr_party="'+result.idNameVoList1[i].id+'" attr_category_id="'+globalcategoryId+'" attr_edition="2" style="cursor:pointer;"><a>'+result.idNameVoList1[i].negCount+'</a></h4>';
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
								if(result.coreDashBoardVOList[i].positivePerc !=null && result.coreDashBoardVOList[i].positivePerc>0){
									str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="1" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+' " style="cursor:pointer;">'+result.coreDashBoardVOList[i].positiveCountMain+'</a></span>';
									str+='<small id="" class="text-success"> '+result.coreDashBoardVOList[i].positivePerc+' %</small>';
								}else{
									str+='<span> - </span>';
								}
								
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted responsiveFont">Negative</p>';
								if(result.coreDashBoardVOList[i].negativePerc !=null && result.coreDashBoardVOList[i].negativePerc>0){
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
								str+='<p><a class="partyMainEditionCls" attr_editiontype="2" attr_benefitid ="0" attr_benefitid ="0" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].totalCount+'</a></p>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">Positive</p>';
								str+='<span><a class="partyMainEditionCls" attr_editiontype="2" attr_benefitid ="1" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].positiveCountDist+'</a></span>';
								str+='<small class="text-success" id=""> '+result.coreDashBoardVOList[i].positiveDistPerc+' %</small>';
							str+='</td>';
							str+='<td>';
								str+='<p class="text-capital text-muted">Negative</p>';
								str+='<span><a class="partyMainEditionCls" attr_editiontype="2" attr_benefitid ="2" attr_partyids="'+result.coreDashBoardVOList[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList[i].negativCountDist+'</a></span>';
								str+='<small class="text-danger" id=""> '+result.coreDashBoardVOList[i].negativeDistPerc+' %</small>';
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
											str+='<img src="newCoreDashBoard/img/'+result.coreDashBoardVOList1[i].organization+'.png" alt="cong logo" class="debatesPartyIcon"/><span><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="0" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].count+'</a></span>';
										str+='</td>';
										str+='<td>';
											str+='<span><a class="partyMainEditionCls" attr_editiontype="1"  attr_benefitid ="1" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].positiveCountMain+'</a></span>';
											str+='<small class="text-success"> '+result.coreDashBoardVOList1[i].positivePerc+' %</small>';
										str+='</td>';
										str+='<td>';
											str+='<span><a class="partyMainEditionCls" attr_editiontype="1" attr_benefitid ="2" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].negativCountMain+'</a></span>';
											str+='<small class="text-danger" id=""> '+result.coreDashBoardVOList1[i].negativePerc+' %</small>';
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
											str+='<img src="newCoreDashBoard/img/'+result.coreDashBoardVOList1[i].organization+'.png" alt="cong logo" class="debatesPartyIcon"/><span><a class="partyMainEditionCls" attr_editiontype="2"  attr_benefitid ="0" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].totalCount+'</a></span>';
										str+='</td>';
										str+='<td>';
											str+='<span><a class="partyMainEditionCls" attr_editiontype="2"  attr_benefitid ="1" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].positiveCountDist+'</a></span>';
											str+='<small class="text-success"> '+result.coreDashBoardVOList1[i].positiveDistPerc+' %</small>';
										str+='</td>';
										str+='<td>';
											str+='<span><a class="partyMainEditionCls" attr_editiontype="2"  attr_benefitid ="2" attr_partyids="'+result.coreDashBoardVOList1[i].organizationId+'" attr_categoryId="'+globalcategoryId+'" style="cursor:pointer;">'+result.coreDashBoardVOList1[i].negativCountDist+'</a></span>';
											str+='<small class="text-danger" id=""> '+result.coreDashBoardVOList1[i].negativeDistPerc+' %</small>';
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
	$("#publicationWisePartiesDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getPublicationWisePartiesAnalysis/"+currentFromDate+"/"+currentToDate+"/"+categoryId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getPublicationWisePartiesAnalysis/"+currentFromDate+"/"+currentToDate+"/"+categoryId
	}).then(function(result){
		if(result !=null && result.length>0){
			buildPublicationWisePartiesAnalysis(result);
		}else{
			$("#publicationWisePartiesDivId").html("No Data Available");
		}
	});
}

function getDistrictWisePartyOverView(categoryId){
	$("#districtWisePartiesDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getDistrictWisePartyOverView/"+currentFromDate+"/"+currentToDate+"/"+categoryId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWisePartyOverView/"+currentFromDate+"/"+currentToDate+"/"+categoryId
	}).then(function(result){
		if(result !=null && result.length>0){
			buildDistrictWisePartyOverView(result);
		}else{
			$("#districtWisePartiesDivId").html("No Data Available");
		}
	});
}

function getChannelWisePartiesAnalysisInfo(categoryId){
	$("#ChannelWisePartyDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisInfo/"+currentFromDate+"/"+currentToDate+"/"+categoryId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisInfo/"+currentFromDate+"/"+currentToDate+"/"+categoryId
	}).then(function(result){
		if(result !=null && result.length>0){
			buildChannelWisePartiesAnalysisInfo(result);
		}else{
			$("#ChannelWisePartyDivId").html("No Data Available");
		}
	});
}

function getDistrictWisePartyViewForElectrronicMediaInfo(categoryId){
	$("#districtWiseChannelDivId").html(spinner);
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getDistrictWisePartyViewForElectrronicMediaInfo/"+currentFromDate+"/"+currentToDate+"/"+categoryId
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWisePartyViewForElectrronicMediaInfo/"+currentFromDate+"/"+currentToDate+"/"+categoryId
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
	$('#prajaSankalpaYatraDivId').html($(this).find('option:selected').text()+" -");
	globalcategoryId = $(this).val();
	onloadPrajaSankaplaYatraCalls();
});
$(document).on("click",".partyMainEditionCls",function(){
	var editionType="Main";
	var attrBenifitId=$(this).attr('attr_benefitid');
	var attrEditionType=$(this).attr('attr_editiontype');
	var attrPartyids=$(this).attr('attr_partyids');
	var attrCategoryid=$(this).attr('attr_categoryid');
	getEditionTypeWisePartiesAnalysisForArticles(editionType,attrCategoryid,attrPartyids,attrBenifitId,attrEditionType);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
});

function getEditionTypeWisePartiesAnalysisForArticles(editionType,attrCategoryid,attrPartyids,attrBenifitId,attrEditionType)
{	
var urlString;
if(editionType=="Main")
{
	urlString="getEditionTypeWisePartiesAnalysisForArticles";
}
else if(editionType=="District")
{
	urlString="getDistrictWisePartyOverViewForArticles";
}

	$.ajax({	
//url: wurl+"/CommunityNewsPortal/webservice/getEditionTypeWisePartiesAnalysisForArticles/"+currentFromDate+"/"+currentToDate+"/"+attrCategoryid+"/"+attrPartyids+"/"+attrBenifitId+"/"+attrEditionType
		url: "http://localhost:8080/CommunityNewsPortal/webservice/"+urlString+"/"+currentFromDate+"/"+currentToDate+"/"+attrCategoryid+"/"+attrPartyids+"/"+attrBenifitId+"/"+attrEditionType
	}).then(function(result){
		buildingTable(result);
	});
	
}
function buildingTable(jsonObject)
{
	var str=" ";
		if(jsonObject[0].stateVoList !=null && jsonObject[0].stateVoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading'>";
				str+="<h3 class='panel-title'>State Level</h3>";
			str+="</div>";
			str+="<div class='panel-body'>";
		
				str+="<table class='table table-bordered'>";
			for(var i in jsonObject[0].stateVoList)
			{
					str+="<tr>";
						str+="<td>"+jsonObject[0].stateVoList[i].locationName+"</td>";
						str+="<td>"+jsonObject[0].stateVoList[i].count+"</td>";
					str+="</tr>";
			}
				str+="</table>";
		str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraStateTableId').html(str);
		}
		if(jsonObject[0].districtVoList !=null && jsonObject[0].districtVoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading'>";
				str+="<h3 class='panel-title'>District Level</h3>";
			str+="</div>";
			str+="<div class='panel-body'>";
		
			str+="<table class='table table-bordered'>";
			for(var i in jsonObject[0].districtVoList)
			{
				str+="<tr>";
					str+="<td>"+jsonObject[0].districtVoList[i].locationName+"</td>";
					str+="<td>"+jsonObject[0].districtVoList[i].count+"</td>";
				str+="</tr>";
			}
			str+="</table>";
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraDistrictTableId').html(str);
		}
		if(jsonObject[0].panchayatVoList !=null && jsonObject[0].panchayatVoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading'>";
				str+="<h3 class='panel-title'>Panchayat Level</h3>";
			str+="</div>";
			str+="<div class='panel-body'>";
	
			str+="<table class='table table-bordered'>";
			for(var i in jsonObject[0].panchayatVoList)
			{
				str+="<tr>";
					str+="<td>"+jsonObject[0].panchayatVoList[i].locationName+"</td>";
					str+="<td>"+jsonObject[0].panchayatVoList[i].count+"</td>";
				str+="</tr>";
			}			
			str+="</table>";
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraPanchayatTableId').html(str);
		}
		if(jsonObject[0].mandalVoList !=null && jsonObject[0].mandalVoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading'>";
				str+="<h3 class='panel-title'>Mandal Level</h3>";
			str+="</div>";
			str+="<div class='panel-body'>";
	
			str+="<table class='table table-bordered'>";
			for(var i in jsonObject[0].mandalVoList)
			{
				str+="<tr>";
					str+="<td>"+jsonObject[0].mandalVoList[i].locationName+"</td>";
					str+="<td>"+jsonObject[0].mandalVoList[i].count+"</td>";
				str+="</tr>";
			}			
			str+="</table>";
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraMandalTableId').html(str);
		}
		if(jsonObject[0].constoList !=null && jsonObject[0].constoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading'>";
				str+="<h3 class='panel-title'>Constituency Level</h3>";
			str+="</div>";
			str+="<div class='panel-body'>";
	
			str+="<table class='table table-bordered'>";
			for(var i in jsonObject[0].constoList)
			{
				str+="<tr>";
					str+="<td>"+jsonObject[0].constoList[i].locationName+"</td>";
					str+="<td>"+jsonObject[0].constoList[i].count+"</td>";
				str+="</tr>";
			}			
			str+="</table>";
			str+="</div>";
	str+="</div>";
			$('#prajaSankalpaYatraConstituencyTableId').html(str);
		}
		if(jsonObject[0].corpGmcVoList !=null && jsonObject[0].corpGmcVoList.length>0){
		str="<div class='panel panel-default'>";
			str+="<div class='panel-heading'>";
				str+="<h3 class='panel-title'>Corporation Level</h3>";
			str+="</div>";
			str+="<div class='panel-body'>";
	
			str+="<table class='table table-bordered'>";
			for(var i in jsonObject[0].corpGmcVoList)
			{
				str+="<tr>";
					str+="<td>"+jsonObject[0].corpGmcVoList[i].locationName+"</td>";
					str+="<td>"+jsonObject[0].corpGmcVoList[i].count+"</td>";
				str+="</tr>";
			}			
			str+="</table>";
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
	getPublicationWisePartiesAnalysisForArticles(benefitId,paperId,partyId,categoryid);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
});
$(document).on("click",".districtWisePartyForPmCls",function(){
	var benefitId=$(this).attr('attr_benefit_id');
	var districtId=$(this).attr('attr_districtId');
	var partyId=$(this).attr('attr_party');
	var categoryid=$(this).attr('attr_categoryId');
	getDistrictWisePartyOverViewForArticles(benefitId,districtId,partyId,categoryid);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
});
$(document).on("click",".channelWiseCntsForEMNcls",function(){
	var benefitId=$(this).attr('attr_benefit_id');
	var channelId=$(this).attr('attr_channelid');
	var partyId=$(this).attr('attr_party');
	var categoryid=$(this).attr('attr_categoryId');
	getChannelWisePartiesAnalysisForArticles(benefitId,channelId,partyId,categoryid);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
});
$(document).on("click",".districtWisePartyForEMNCls",function(){
	var benefitId=$(this).attr('attr_benefit_id');
	var districtId=$(this).attr('attr_districtId');
	var partyId=$(this).attr('attr_party');
	var categoryid=$(this).attr('attr_categoryId');
	getDistrictWisePartyViewForElectrronicMediaInfoForArticles(benefitId,districtId,partyId,categoryid);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
});

function getPublicationWisePartiesAnalysisForArticles(benefitId,paperId,partyId,categoryId){
	$.ajax({	
		//url: wurl+"/CommunityNewsPortal/webservice/getPublicationWisePartiesAnalysisForArticles/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+paperId
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getPublicationWisePartiesAnalysisForArticles/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+paperId
	}).then(function(result){
		buildingTable(result);
	});
}
function getDistrictWisePartyOverViewForArticles(benefitId,districtId,partyId,categoryId){
	$.ajax({	
		//url: wurl+"/CommunityNewsPortal/webservice/getDistrictWisePartyOverViewForArticles/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+districtId
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWisePartyOverViewForArticles/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+districtId
	}).then(function(result){
		buildingTable(result);
	});
}
function getChannelWisePartiesAnalysisForArticles(benefitId,channelId,partyId,categoryId){
	$.ajax({	
		//url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisForArticles/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+channelId
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisForArticles/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+channelId
	}).then(function(result){
		buildingTable(result);
	});
}
function getDistrictWisePartyViewForElectrronicMediaInfoForArticles(benefitId,districtId,partyId,categoryId){
	$.ajax({	
		//url: wurl+"/CommunityNewsPortal/webservice/getDistrictWisePartyViewForElectrronicMediaInfoForArticles/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+districtId
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getDistrictWisePartyViewForElectrronicMediaInfoForArticles/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+districtId
	}).then(function(result){
		buildingTable(result);
	});
}
$(document).on("click",".EMnCls",function(){
	var benefitId=$(this).attr('attr_benefitId');
	var editionId=$(this).attr('attr_edition');
	var partyId=$(this).attr('attr_party');
	var categoryid=$(this).attr('attr_category_id');
	getChannelWisePartiesAnalysisInfoForArticles(benefitId,editionId,partyId,categoryid);
	$('.clearModalTables').html("");
	$('#prajaSankalpaYatraModalId').modal('show');
});
function getChannelWisePartiesAnalysisInfoForArticles(benefitId,editionId,partyId,categoryId){
	$.ajax({	
		//url: wurl+"/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisInfoForArticles/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+editionId
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getChannelWisePartiesAnalysisInfoForArticles/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+partyId+"/"+benefitId+"/"+editionId
	}).then(function(result){
		buildingTable(result);
	});
}