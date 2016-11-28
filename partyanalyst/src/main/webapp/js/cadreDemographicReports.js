//Over All Age Report Start
	function getAgeWiseTdpCadreSummaryReport(stateId){
		 $("#ageWiseSummaryDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			         stateId:stateId
					}
		
		$.ajax({
			type : 'POST',
			url : 'ageWiseTdpCadreSummaryReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#ageWiseSummaryDetails").html('');
			buildAgeWiseTdpCadreSummaryReport(result,stateId);
		});
	}
	
	function buildAgeWiseTdpCadreSummaryReport(result,stateId){
		var str='';
		if(result !=null && result.subList !=null && result.subList.length>0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
						if(stateId == 1){
							str+='<h3 class="panel-title text-capital">overall age wise AP report</h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">overall age wise TS report</h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="overAllReportExcel" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-reponsive">';
					str+='<table class="table table-bordered" id="overaAllAgeWiseTableExcelDivId">';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b class="f_14">age range</th></b>';
								str+='<th class="text-capital " ><b class="f_14">2014 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2014 cadre %</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre %</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal %</br>(From 2014)</b></th>';
								str+='<th class="text-capital "><b class="f_14">Renewal %</br>(From 2016)</b></th>';
								str+='<th class="text-capital "><b class="f_14">New cadre</th>';
								str+='<th class="text-capital "><b class="f_14">New cadre %</b></th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result.subList){
							str+='<tr>';
								str+='<td>'+result.subList[i].name+'</td>';
								if(result.subList[i].previousCadreCount !=null && result.subList[i].previousCadreCount>0){
									str+='<td>'+result.subList[i].previousCadreCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].previousCadrePercent !=null && result.subList[i].previousCadrePercent>0){
									str+='<td>'+result.subList[i].previousCadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].cadreCount !=null && result.subList[i].cadreCount>0){
									str+='<td>'+result.subList[i].cadreCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].cadrePercent !=null && result.subList[i].cadrePercent>0){
									str+='<td>'+result.subList[i].cadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].renewalCadre !=null && result.subList[i].renewalCadre>0){
									str+='<td>'+result.subList[i].renewalCadre+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].previousCadreRenewalPercent !=null && result.subList[i].previousCadreRenewalPercent>0){
									str+='<td>'+result.subList[i].previousCadreRenewalPercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].renewalCadrePercent !=null && result.subList[i].renewalCadrePercent>0){
									str+='<td>'+result.subList[i].renewalCadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].newCadre !=null && result.subList[i].newCadre>0){
									str+='<td>'+result.subList[i].newCadre+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].newCadrePercent !=null && result.subList[i].newCadrePercent>0){
									str+='<td>'+result.subList[i].newCadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
							str+='</tr>';
						}
						str+='<tr style="background-color:#efefef">';
							str+='<th><b>Grand Total</b></th>';
							if(result.previousCadreTotalCount !=null && result.previousCadreTotalCount>0){
								str+='<th><b>'+result.previousCadreTotalCount+'</b></th>';
								str+='<th><b>100</th>';
							}else{
								str+='<th> - </th>';
								str+='<th> - </th>';
							}
							
							if(result.cadreTotalCount !=null && result.cadreTotalCount>0){
								str+='<th><b>'+result.cadreTotalCount+'</b></th>';
								str+='<th><b>100</b></th>';
							}else{
								str+='<th> - </th>';
								str+='<th> - </th>';
							}
							if(result.renewalCadreTotalCount !=null && result.renewalCadreTotalCount>0){
								str+='<th><b>'+result.renewalCadreTotalCount+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.previousCadreRenewalPercent !=null && result.previousCadreRenewalPercent>0){
								str+='<th><b>'+result.previousCadreRenewalPercent+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.renewalCadrePercent !=null && result.renewalCadrePercent>0){
								str+='<th><b>'+result.renewalCadrePercent+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.newCadreTotalCount !=null && result.newCadreTotalCount>0){
								str+='<th><b>'+result.newCadreTotalCount+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.newCadrePercent !=null && result.newCadrePercent>0){
								str+='<th><b>'+result.newCadrePercent+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							
						str+='</tr>';
						str+='</tbody>';
						str+='</table>';
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#ageWiseSummaryDetails").html("NO DATA AVAILABLE");
		}
		
		$("#ageWiseSummaryDetails").html(str);
	}
	
	$(document).on("click","#overAllReportExcel",function(){
		generateExcelReportForOverAllAgeWise();	
	});
	
	function generateExcelReportForOverAllAgeWise(){
		tableToExcel(overaAllAgeWiseTableExcelDivId, 'OVERALL AGE WISE REPORT');        
	}
	
	//Over All Age Report End
	
	//District Wise Age Report Start
	function getDistrictWisegeWiseTdpCadreCounts(stateId,districtId,searchType){
		 $("#ageWiseDistrictDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
		 
		var jobj =	{  
			         stateId:stateId,
					 districtId:districtId,
					 searchType:searchType
					}
		
		$.ajax({
			type : 'POST',
			url : 'getLocationWisegeWiseTdpCadreCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#ageWiseDistrictDetails").html('');
			 buildDistrictWisegeWiseTdpCadreCounts(result,stateId);
			
		});
	}
	
	function buildDistrictWisegeWiseTdpCadreCounts(result,stateId){
		
		var str='';
		var str1='';
		if(result !=null && result.length>0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
						if(stateId == 1){
							str+='<h3 class="panel-title text-capital">district age wise AP report</h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">district age wise TS report</h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="districtWiseExcelReport" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-reponsive">';
					
					
					str+='<table class="table table-bordered tableHeaderStyle districtWiseDataTableId" id="" >';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b>DISTRICT</th></b>';
								str+='<th class="text-capital " ><b>Age Range</th></b>';
								str+='<th class="text-capital "><b>2014 Cadre</th></b>';
								str+='<th class="text-capital "><b>2014 Cadre %</th></b>';
								str+='<th class="text-capital "><b>2016 Cadre</th></b>';
								str+='<th class="text-capital "><b>2016 Cadre %</th></b>';
								str+='<th class="text-capital "><b>Renewal</b></th>';
								str+='<th class="text-capital "><b>Renewal %</br>(From 2014)</b></th>';
								str+='<th class="text-capital "><b>Renewal %</br>(From 2016)</th>';
								str+='<th class="text-capital "><b>New Cadre</b></th>';
								str+='<th class="text-capital "><b>New Cadre %</b></th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							
								
								 if(result[i].subList !=null && result[i].subList.length>0){
									for(var j in result[i].subList){
										str+='<tr>';
										str+='<td>'+result[i].name+'</td>';
										str+='<td>'+result[i].subList[j].name+'</td>';
										str+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
										str+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].cadreCount+'</td>';
										str+='<td>'+result[i].subList[j].cadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].renewalCadre+'</td>';
										str+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
										str+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].newCadre+'</td>';
										str+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
										str+='</tr>';
									}
								} 
								
							
						}
						
						str+='</tbody>';
						str+='</table>';
						//for fetching all records start
						str1+='<table class="table table-bordered tableHeaderStyle " id="districtWiseTableExcelDivId" >';
						str1+='<thead>';
							str1+='<tr>';
								str1+='<th class="text-capital "><b>DISTRICT</th></b>';
								str1+='<th class="text-capital " ><b>Age Range</th></b>';
								str1+='<th class="text-capital "><b>2014 Cadre</th></b>';
								str1+='<th class="text-capital "><b>2014 Cadre %</th></b>';
								str1+='<th class="text-capital "><b>2016 Cadre</th></b>';
								str1+='<th class="text-capital "><b>2016 Cadre %</th></b>';
								str1+='<th class="text-capital "><b>Renewal</b></th>';
								str1+='<th class="text-capital "><b>Renewal %</br>(From 2014)</b></th>';
								str1+='<th class="text-capital "><b>Renewal %</br>(From 2016)</th>';
								str1+='<th class="text-capital "><b>New Cadre</b></th>';
								str1+='<th class="text-capital "><b>New Cadre %</b></th>';
							str1+='</tr>';
						str1+='</thead>';
						str1+='<tbody>';
						for(var i in result){
							
								
								 if(result[i].subList !=null && result[i].subList.length>0){
									for(var j in result[i].subList){
										str1+='<tr>';
										str1+='<td>'+result[i].name+'</td>';
										str1+='<td>'+result[i].subList[j].name+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].cadreCount+'</td>';
										str1+='<td>'+result[i].subList[j].cadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].renewalCadre+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
										str1+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].newCadre+'</td>';
										str1+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
										str1+='</tr>';
									}
								} 
								
							
						}
						
						str1+='</tbody>';
						str1+='</table>';
						//for fetching all records end
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#ageWiseDistrictDetails").html("NO DATA AVAILABLE");
		}
		
		$("#ageWiseDistrictDetails").html(str);
		$("#excelDivageWiseDistrictDetails").html(str1);
		$('.districtWiseDataTableId').DataTable();
	}
	
	$(document).on("click","#districtWiseExcelReport",function(){
		generateExcelReportDistrictWiseAgeReport();	
	});
	
	function generateExcelReportDistrictWiseAgeReport(){
		tableToExcel(districtWiseTableExcelDivId, 'DISTRICT AGE WISE REPORT');        
	}
	//District Wise Age Report End
	
	
	//Constituency Wise Age  Report Start
	function getConstituencyWisegeWiseTdpCadreCounts(stateId,districtId,searchType){
		
		 $("#ageWiseConstituencyDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			         stateId:stateId,
					 districtId:districtId,
					 searchType:searchType
					}
		
		$.ajax({
			type : 'POST',
			url : 'getLocationWisegeWiseTdpCadreCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#ageWiseConstituencyDetails").html('');
			 buildConstituencyWisegeWiseTdpCadreCounts(result,stateId);
			
		});
	}
	
	function buildConstituencyWisegeWiseTdpCadreCounts(result,stateId){
		
		var str='';
		var str1='';
		if(result !=null && result.length>0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
						if(stateId == 1){
							str+='<h3 class="panel-title text-capital">constituency age wise AP report</h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">constituency age wise TS report</h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="constituencyWiseExcelReport" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-reponsive">';
					
					
					str+='<table class="table table-bordered tableHeaderStyle constituencyWiseDataTableId" id="" >';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b>District</th></b>';
								str+='<th class="text-capital "><b>Constituency</th></b>';
								str+='<th class="text-capital " ><b>Age Range</th></b>';
								str+='<th class="text-capital "><b>2014 Cadre</th></b>';
								str+='<th class="text-capital "><b>2014 Cadre %</th></b>';
								str+='<th class="text-capital "><b>2016 Cadre</th></b>';
								str+='<th class="text-capital "><b>2016 Cadre %</th></b>';
								str+='<th class="text-capital "><b>Renewal</b></th>';
								str+='<th class="text-capital "><b>Renewal %</br>(From 2014)</b></th>';
								str+='<th class="text-capital "><b>Renewal %</br>(From 2016)</th>';
								str+='<th class="text-capital "><b>New Cadre</b></th>';
								str+='<th class="text-capital "><b>New Cadre %</b></th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							
								
								 if(result[i].subList !=null && result[i].subList.length>0){
									for(var j in result[i].subList){
										str+='<tr>';
										str+='<td>'+result[i].superlocationName+'</td>';
										str+='<td>'+result[i].name+'</td>';
										str+='<td>'+result[i].subList[j].name+'</td>';
										str+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
										str+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].cadreCount+'</td>';
										str+='<td>'+result[i].subList[j].cadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].renewalCadre+'</td>';
										str+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
										str+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
										str+='<td>'+result[i].subList[j].newCadre+'</td>';
										str+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
										str+='</tr>';
									}
								} 
								
							
						}
						
						str+='</tbody>';
						str+='</table>';
						
						//for fetching all records start
						str1+='<table class="table table-bordered tableHeaderStyle " id="constituencyWiseTableExcelDivId" >';
						str1+='<thead>';
							str1+='<tr>';
								str1+='<th class="text-capital "><b>DISTRICT</th></b>';
								str1+='<th class="text-capital " ><b>Age Range</th></b>';
								str1+='<th class="text-capital "><b>2014 Cadre</th></b>';
								str1+='<th class="text-capital "><b>2014 Cadre %</th></b>';
								str1+='<th class="text-capital "><b>2016 Cadre</th></b>';
								str1+='<th class="text-capital "><b>2016 Cadre %</th></b>';
								str1+='<th class="text-capital "><b>Renewal</b></th>';
								str1+='<th class="text-capital "><b>Renewal %</br>(From 2014)</b></th>';
								str1+='<th class="text-capital "><b>Renewal %</br>(From 2016)</th>';
								str1+='<th class="text-capital "><b>New Cadre</b></th>';
								str1+='<th class="text-capital "><b>New Cadre %</b></th>';
							str1+='</tr>';
						str1+='</thead>';
						str1+='<tbody>';
						for(var i in result){
								 if(result[i].subList !=null && result[i].subList.length>0){
									for(var j in result[i].subList){
										str1+='<tr>';
										str1+='<td>'+result[i].name+'</td>';
										str1+='<td>'+result[i].subList[j].name+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].cadreCount+'</td>';
										str1+='<td>'+result[i].subList[j].cadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].renewalCadre+'</td>';
										str1+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
										str1+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
										str1+='<td>'+result[i].subList[j].newCadre+'</td>';
										str1+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
										str1+='</tr>';
									}
								} 
						}
						str1+='</tbody>';
						str1+='</table>';
						//for fetching all records end
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#ageWiseConstituencyDetails").html("NO DATA AVAILABLE");
		}
		
		$("#ageWiseConstituencyDetails").html(str);
		$("#excelDivageWiseConstituencyDetails").html(str1);
		$('.constituencyWiseDataTableId').DataTable();
	}
	
	$(document).on("click","#constituencyWiseExcelReport",function(){
		generateExcelReportConstituencyWiseAgeReport();	
	});
	
	function generateExcelReportConstituencyWiseAgeReport(){
		tableToExcel(constituencyWiseTableExcelDivId, 'CONSTITUENCY AGE WISE REPORT'); 
	}
	//Constituency Wise Age Report End
	
	//caste wise overAll reports start
	function getCasteCategoryWiseTdpCadreSummaryReport(stateId){
		 $("#overAllCasteWiseSummaryDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			         stateId:stateId
					}
		
		$.ajax({
			type : 'POST',
			url : 'casteCategoryWiseTdpCadreSummaryReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#overAllCasteWiseSummaryDetails").html('');
			 buildCasteCategoryWiseTdpCadreSummaryReport(result,stateId);
			
		});
	}
	
	function buildCasteCategoryWiseTdpCadreSummaryReport(result,stateId){
		var str='';
		if(result !=null && result.subList !=null && result.subList.length>0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
						if(stateId == 1){
							str+='<h3 class="panel-title text-capital">overall caste Category Wise AP report</h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">overall caste Category Wise TS report</h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="overAllcasteWiseReportExcel" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-reponsive">';
					str+='<table class="table table-bordered" id="overaAllCasteWiseTableExcelDivId">';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b class="f_14">age range</th></b>';
								str+='<th class="text-capital " ><b class="f_14">2014 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2014 cadre %</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre %</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal %</br>(From 2014)</b></th>';
								str+='<th class="text-capital "><b class="f_14">Renewal %</br>(From 2016)</b></th>';
								str+='<th class="text-capital "><b class="f_14">New cadre</th>';
								str+='<th class="text-capital "><b class="f_14">New cadre %</b></th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result.subList){
							str+='<tr>';
								str+='<td>'+result.subList[i].name+'</td>';
								if(result.subList[i].previousCadreCount !=null && result.subList[i].previousCadreCount>0){
									str+='<td>'+result.subList[i].previousCadreCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].previousCadrePercent !=null && result.subList[i].previousCadrePercent>0){
									str+='<td>'+result.subList[i].previousCadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].cadreCount !=null && result.subList[i].cadreCount>0){
									str+='<td>'+result.subList[i].cadreCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].cadrePercent !=null && result.subList[i].cadrePercent>0){
									str+='<td>'+result.subList[i].cadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].renewalCadre !=null && result.subList[i].renewalCadre>0){
									str+='<td>'+result.subList[i].renewalCadre+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].previousCadreRenewalPercent !=null && result.subList[i].previousCadreRenewalPercent>0){
									str+='<td>'+result.subList[i].previousCadreRenewalPercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].renewalCadrePercent !=null && result.subList[i].renewalCadrePercent>0){
									str+='<td>'+result.subList[i].renewalCadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].newCadre !=null && result.subList[i].newCadre>0){
									str+='<td>'+result.subList[i].newCadre+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.subList[i].newCadrePercent !=null && result.subList[i].newCadrePercent>0){
									str+='<td>'+result.subList[i].newCadrePercent+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
							str+='</tr>';
						}
						str+='<tr style="background-color:#efefef">';
							str+='<th><b>Grand Total</b></th>';
							if(result.previousCadreTotalCount !=null && result.previousCadreTotalCount>0){
								str+='<th><b>'+result.previousCadreTotalCount+'</b></th>';
								str+='<th><b>100</th>';
							}else{
								str+='<th> - </th>';
								str+='<th> - </th>';
							}
							
							if(result.cadreTotalCount !=null && result.cadreTotalCount>0){
								str+='<th><b>'+result.cadreTotalCount+'</b></th>';
								str+='<th><b>100</b></th>';
							}else{
								str+='<th> - </th>';
								str+='<th> - </th>';
							}
							if(result.renewalCadreTotalCount !=null && result.renewalCadreTotalCount>0){
								str+='<th><b>'+result.renewalCadreTotalCount+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.previousCadreRenewalPercent !=null && result.previousCadreRenewalPercent>0){
								str+='<th><b>'+result.previousCadreRenewalPercent+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.renewalCadrePercent !=null && result.renewalCadrePercent>0){
								str+='<th><b>'+result.renewalCadrePercent+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.newCadreTotalCount !=null && result.newCadreTotalCount>0){
								str+='<th><b>'+result.newCadreTotalCount+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							if(result.newCadrePercent !=null && result.newCadrePercent>0){
								str+='<th><b>'+result.newCadrePercent+'</b></th>';
							}else{
								str+='<th> - </th>';
							}
							
						str+='</tr>';
						str+='</tbody>';
						str+='</table>';
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#overAllCasteWiseSummaryDetails").html("NO DATA AVAILABLE");
		}
		
		$("#overAllCasteWiseSummaryDetails").html(str);
	}
	$(document).on("click","#overAllcasteWiseReportExcel",function(){
		generateExcelReportForOverAllCasteWise();	
	});
	
	function generateExcelReportForOverAllCasteWise(){
		tableToExcel(overaAllCasteWiseTableExcelDivId, 'OVERALL CASTE WISE REPORT');        
	}
	
	//caste wise overAll reports End
	//Gender Wise OverAll Reports Start
	function getstateWiseCadreGenderCounts(stateId){
		 $("#overAllGenderWiseSummaryDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			         stateId:stateId
					}
		
		$.ajax({
			type : 'POST',
			url : 'stateWiseCadreGenderCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#overAllGenderWiseSummaryDetails").html('');
			buildStateWiseCadreGenderCounts(result,stateId);
		});
	}
	function buildStateWiseCadreGenderCounts(result,stateId){
		
		var str='';
		if(result !=null ){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
						if(stateId == 1){
							str+='<h3 class="panel-title text-capital">overall gender wise AP report</h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">overall gender wise TS report</h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="overAllGenderWiseExcelId" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					 str+='<label class="pull-right text-muted" style="margin-top: -10px;">Note: M - Male & F - Female</label>';
					str+='<div class="table-reponsive">';
					str+='<table class="table table-bordered tableHeaderStyle table-condensed" id="overAllGenderWiseTableToExcel">';
						str+='<thead>';
							str+='<tr>';

								str+='<th class="text-capital " colspan="5" style="text-align:center;"><b class="f_14">2014 cadre</b></th>';
								str+='<th class="text-capital " colspan="12" style="text-align:center;"><b class="f_14">2016 cadre</b></th>';
							str+='</tr>';
							str+='<tr>';
								str+='<th class="text-capital " ><b>Total</b></th>';
								str+='<th class="text-capital " ><b >M</b></th>';
								str+='<th class="text-capital " ><b>M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
							
								str+='<th class="text-capital " ><b >Total</b></th>';
								str+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >Renewal</th></b>';
								str+='<th class="text-capital " ><b >Renewal&nbsp;%&nbsp; (From 2014)</b></th>';
								str+='<th class="text-capital " ><b >Renewal&nbsp;%&nbsp; (From 2016)</b></th>';
								str+='<th class="text-capital " ><b >M</b></th>';
								str+='<th class="text-capital " ><b >F</b></th>';
								str+='<th class="text-capital " ><b >New Cadre</b></th>';
								str+='<th class="text-capital " ><b >New Cadre&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >M</b></th>';
								str+='<th class="text-capital " ><b >F</b></th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							
								str+='<tr>';
									str+='<td>'+result.previousCadreTotalCount+'</td>';
									str+='<td>'+result.previousCadreMaleCount+'</td>';
									str+='<td>'+result.previousCadreMalePerc+'</td>';
									str+='<td>'+result.previousCadreFemaleCount+'</td>';
									str+='<td>'+result.previousCadreFemalePerc+'</td>';
									
									str+='<td>'+result.cadreTotalCount+'</td>';
									str+='<td>'+result.cadreMalePerc+'</td>';
									str+='<td>'+result.cadreFemalePerc+'</td>';
									
									str+='<td>'+result.renewalCadreTotalCount+'</td>';
									str+='<td>'+result.previousCadreRenewalPerc+'</td>';
									str+='<td>'+result.renewalCadrePerc+'</td>';
									str+='<td>'+result.renewalCadreMaleCount+'</td>';
									str+='<td>'+result.renewalCadreFemaleCount+'</td>';
									
									str+='<td>'+result.newCadreTotalCount+'</td>';
									str+='<td>'+result.newCadrePerc+'</td>';
									str+='<td>'+result.newCadreMaleCount+'</td>';
									str+='<td>'+result.newCadreFemaleCount+'</td>';
								str+='</tr>';
							
						str+='</tbody>';
						str+='</table>';
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#overAllGenderWiseSummaryDetails").html("NO DATA AVAILABLE");
		}
		
		$("#overAllGenderWiseSummaryDetails").html(str);
		
	}
	$(document).on("click","#overAllGenderWiseExcelId",function(){
		generateExcelReportForOverAllGenderWise();	
	});
	
	function generateExcelReportForOverAllGenderWise(){
		tableToExcel(overAllGenderWiseTableToExcel, 'OVERALL GENDER WISE REPORT');        
	}
	//Gender Wise OverAll Reports End
	
	//Gender Wise District Reports Start
	function getDistrictWiseCadreGenderCounts(stateId,districtId,searchType){
		 $("#genderWiseDistrictDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
		 
		var jobj =	{  
			         stateId:stateId,
					 districtId:districtId,
					 searchType:searchType
					}
		
		$.ajax({
			type : 'POST',
			url : 'locationWiseCadreGenderCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#genderWiseDistrictDetails").html('');
			buildDistrictWiseCadreGenderCounts(result,stateId);
			
		});
	}
		
	function buildDistrictWiseCadreGenderCounts(result,stateId){
		
		var str='';
		if(result !=null && result.length>0 ){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
						if(stateId == 1){
							str+='<h3 class="panel-title text-capital">district gender wise AP report</h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">district gender wise TS report</h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="districtGenderWiseExcelId" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
				  str+='<label class="pull-right text-muted" style="margin-top: -10px;">Note: M - Male & F - Female</label>';
					str+='<div class="table-reponsive">';
					str+='<table class="table table-bordered tableHeaderStyle table-condensed " id="districtGenderWiseTableToExcel">';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital " rowspan="2" ><b>District</b></th>';
								str+='<th class="text-capital " colspan="5" style="text-align:center;"><b class="f_14">2014 cadre</b></th>';
								str+='<th class="text-capital " colspan="12" style="text-align:center;"><b class="f_14">2016 cadre</b></th>';
							str+='</tr>';
							str+='<tr>';
								str+='<th class="text-capital " ><b>Total</b></th>';
								str+='<th class="text-capital " ><b >M</b></th>';
								str+='<th class="text-capital " ><b>M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
							
								str+='<th class="text-capital " ><b >Total</b></th>';
								str+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >Renewal</th></b>';
								str+='<th class="text-capital " ><b >Renewal&nbsp;%&nbsp; (From 2014)</b></th>';
								str+='<th class="text-capital " ><b >Renewal&nbsp;%&nbsp; (From 2016)</b></th>';
								str+='<th class="text-capital " ><b >M</b></th>';
								str+='<th class="text-capital " ><b >F</b></th>';
								str+='<th class="text-capital " ><b >New Cadre</b></th>';
								str+='<th class="text-capital " ><b >New Cadre&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >M</b></th>';
								str+='<th class="text-capital " ><b >F</b></th>';
							str+='</tr>';
							
						str+='</thead>';
						str+='<tbody>';
							for(var i in result){
								str+='<tr>';
									str+='<td >'+result[i].name+'</td>';
									str+='<td>'+result[i].previousCadreTotalCount+'</td>';
									str+='<td>'+result[i].previousCadreMaleCount+'</td>';
									str+='<td>'+result[i].previousCadreMalePerc+'</td>';
									str+='<td>'+result[i].previousCadreFemaleCount+'</td>';
									str+='<td>'+result[i].previousCadreFemalePerc+'</td>';
									
									str+='<td>'+result[i].cadreTotalCount+'</td>';
									str+='<td>'+result[i].cadreMalePerc+'</td>';
									str+='<td>'+result[i].cadreFemalePerc+'</td>';
									
									str+='<td>'+result[i].renewalCadreTotalCount+'</td>';
									str+='<td>'+result[i].previousCadreRenewalPerc+'</td>';
									str+='<td>'+result[i].renewalCadrePerc+'</td>';
									str+='<td>'+result[i].renewalCadreMaleCount+'</td>';
									str+='<td>'+result[i].renewalCadreFemaleCount+'</td>';
									
									str+='<td>'+result[i].newCadreTotalCount+'</td>';
									str+='<td>'+result[i].newCadrePerc+'</td>';
									str+='<td>'+result[i].newCadreMaleCount+'</td>';
									str+='<td>'+result[i].newCadreFemaleCount+'</td>';
								str+='</tr>';
							}
								
							
						str+='</tbody>';
						str+='</table>';
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#genderWiseDistrictDetails").html("NO DATA AVAILABLE");
		}
		
		$("#genderWiseDistrictDetails").html(str);
	}
	$(document).on("click","#districtGenderWiseExcelId",function(){
		generateExcelReportForDistrictGenderWise();	
	});
	
	function generateExcelReportForDistrictGenderWise(){
		tableToExcel(districtGenderWiseTableToExcel, 'DISTRICT GENDER WISE REPORT');        
	}
	//Gender Wise District Reports End
	
	
	//Gender Wise District Reports Start
	function getConstituencyWiseCadreGenderCounts(stateId,districtId,searchType){
		 $("#genderWiseConstituencyDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
		 
		var jobj =	{  
			         stateId:stateId,
					 districtId:districtId,
					 searchType:searchType
					}
		
		$.ajax({
			type : 'POST',
			url : 'locationWiseCadreGenderCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#genderWiseConstituencyDetails").html('');
			buildConstituencyWiseCadreGenderCounts(result,stateId);
			
		});
	}
		
	function buildConstituencyWiseCadreGenderCounts(result,stateId){
		
		var str='';
		var str1='';
		if(result !=null && result.length>0 ){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
						if(stateId == 1){
							str+='<h3 class="panel-title text-capital">constituency gender wise AP report</h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">constituency gender wise TS report</h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="constituencyGenderWiseExcelId" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
				    str+='<label class="pull-right text-muted" style="margin-top: -10px;">Note: M - Male & F - Female</label>';
					str+='<div class="table-reponsive" style="width:1050px;overflow-x:scroll;">';
					str+='<table class="table table-bordered tableHeaderStyle table-condensed  constituencyWiseGenderDataTableId" id="">';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital " rowspan="2" ><b>District</b></th>';
								str+='<th class="text-capital " rowspan="2" ><b>Constituency</b></th>';
								str+='<th class="text-capital " colspan="5" style="text-align:center;"><b class="f_14">2014 cadre</b></th>';
								str+='<th class="text-capital " colspan="12" style="text-align:center;"><b class="f_14">2016 cadre</b></th>';
							str+='</tr>';
							str+='<tr>';
								str+='<th class="text-capital " ><b>Total</b></th>';
								str+='<th class="text-capital " ><b >M</b></th>';
								str+='<th class="text-capital " ><b>M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
							
								str+='<th class="text-capital " ><b >Total</b></th>';
								str+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >Renewal</th></b>';
								str+='<th class="text-capital " ><b >Renewal&nbsp;%&nbsp; (From 2014)</b></th>';
								str+='<th class="text-capital " ><b >Renewal&nbsp;%&nbsp; (From 2016)</b></th>';
								str+='<th class="text-capital " ><b >M</b></th>';
								str+='<th class="text-capital " ><b >F</b></th>';
								str+='<th class="text-capital " ><b >New Cadre</b></th>';
								str+='<th class="text-capital " ><b >New Cadre&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >M</b></th>';
								str+='<th class="text-capital " ><b >F</b></th>';
							str+='</tr>';
							
						str+='</thead>';
						str+='<tbody>';
							for(var i in result){
								str+='<tr>';
									str+='<td >'+result[i].superlocationName+'</td>';
									str+='<td >'+result[i].name+'</td>';
									str+='<td>'+result[i].previousCadreTotalCount+'</td>';
									str+='<td>'+result[i].previousCadreMaleCount+'</td>';
									str+='<td>'+result[i].previousCadreMalePerc+'</td>';
									str+='<td>'+result[i].previousCadreFemaleCount+'</td>';
									str+='<td>'+result[i].previousCadreFemalePerc+'</td>';
									
									str+='<td>'+result[i].cadreTotalCount+'</td>';
									str+='<td>'+result[i].cadreMalePerc+'</td>';
									str+='<td>'+result[i].cadreFemalePerc+'</td>';
									
									str+='<td>'+result[i].renewalCadreTotalCount+'</td>';
									str+='<td>'+result[i].previousCadreRenewalPerc+'</td>';
									str+='<td>'+result[i].renewalCadrePerc+'</td>';
									str+='<td>'+result[i].renewalCadreMaleCount+'</td>';
									str+='<td>'+result[i].renewalCadreFemaleCount+'</td>';
									
									str+='<td>'+result[i].newCadreTotalCount+'</td>';
									str+='<td>'+result[i].newCadrePerc+'</td>';
									str+='<td>'+result[i].newCadreMaleCount+'</td>';
									str+='<td>'+result[i].newCadreFemaleCount+'</td>';
								str+='</tr>';
							}
								
							
						str+='</tbody>';
						str+='</table>';
						
						str1+='<table class="table table-bordered tableHeaderStyle table-condensed m_top20 " id="constituencyGenderWiseTableToExcel">';
						str1+='<thead>';
							str1+='<tr>';
								str1+='<th class="text-capital " rowspan="2" ><b>District</b></th>';
								str1+='<th class="text-capital " rowspan="2" ><b>Constituency</b></th>';
								str1+='<th class="text-capital " colspan="5" style="text-align:center;"><b class="f_14">2014 cadre</b></th>';
								str1+='<th class="text-capital " colspan="12" style="text-align:center;"><b class="f_14">2016 cadre</b></th>';
							str1+='</tr>';
							str1+='<tr>';
								str1+='<th class="text-capital " ><b>Total</b></th>';
								str1+='<th class="text-capital " ><b >M</b></th>';
								str1+='<th class="text-capital " ><b>M&nbsp;%</b></th>';
								str1+='<th class="text-capital " ><b >F</b></th>';
								str1+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
							
								str1+='<th class="text-capital " ><b >Total</b></th>';
								str1+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str1+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
								str1+='<th class="text-capital " ><b >Renewal</th></b>';
								str1+='<th class="text-capital " ><b >Renewal&nbsp;%&nbsp; (From 2014)</b></th>';
								str1+='<th class="text-capital " ><b >Renewal&nbsp;%&nbsp; (From 2016)</b></th>';
								str1+='<th class="text-capital " ><b >M</b></th>';
								str1+='<th class="text-capital " ><b >F</b></th>';
								str1+='<th class="text-capital " ><b >New Cadre</b></th>';
								str1+='<th class="text-capital " ><b >New Cadre&nbsp;%</b></th>';
								str1+='<th class="text-capital " ><b >M</b></th>';
								str1+='<th class="text-capital " ><b >F</b></th>';
							str1+='</tr>';
							
						str1+='</thead>';
						str1+='<tbody>';
							for(var i in result){
								str1+='<tr>';
									str1+='<td >'+result[i].superlocationName+'</td>';
									str1+='<td >'+result[i].name+'</td>';
									str1+='<td>'+result[i].previousCadreTotalCount+'</td>';
									str1+='<td>'+result[i].previousCadreMaleCount+'</td>';
									str1+='<td>'+result[i].previousCadreMalePerc+'</td>';
									str1+='<td>'+result[i].previousCadreFemaleCount+'</td>';
									str1+='<td>'+result[i].previousCadreFemalePerc+'</td>';
									
									str1+='<td>'+result[i].cadreTotalCount+'</td>';
									str1+='<td>'+result[i].cadreMalePerc+'</td>';
									str1+='<td>'+result[i].cadreFemalePerc+'</td>';
									
									str1+='<td>'+result[i].renewalCadreTotalCount+'</td>';
									str1+='<td>'+result[i].previousCadreRenewalPerc+'</td>';
									str1+='<td>'+result[i].renewalCadrePerc+'</td>';
									str1+='<td>'+result[i].renewalCadreMaleCount+'</td>';
									str1+='<td>'+result[i].renewalCadreFemaleCount+'</td>';
									
									str1+='<td>'+result[i].newCadreTotalCount+'</td>';
									str1+='<td>'+result[i].newCadrePerc+'</td>';
									str1+='<td>'+result[i].newCadreMaleCount+'</td>';
									str1+='<td>'+result[i].newCadreFemaleCount+'</td>';
								str1+='</tr>';
							}
								
							
						str1+='</tbody>';
						str1+='</table>';
						
						
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#genderWiseConstituencyDetails").html("NO DATA AVAILABLE");
		}
		
		$("#genderWiseConstituencyDetails").html(str);
		$("#excelDivGenderWiseConstituencyDetails").html(str1);
		$('.constituencyWiseGenderDataTableId').DataTable();
	}
	$(document).on("click","#constituencyGenderWiseExcelId",function(){
		generateExcelReportForConstituencyGenderWise();	
	});
	
	function generateExcelReportForConstituencyGenderWise(){
		tableToExcel(constituencyGenderWiseTableToExcel, 'CONSTITUENCY GENDER WISE REPORT');        
	}
	//Gender Wise Constituency Reports End