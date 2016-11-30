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
							str+='<h3 class="panel-title text-capital">ap state - age wise summary </h3>';
						}else{
							  str+='<h3 class="panel-title text-capital">ts state - age wise summary </h3>';
						}
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="overAllReportExcel" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
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
		var stateName= getState();
		var reportName = stateName +" STATE - AGE WISE SUMMARY  ";      
		tableToExcel(overaAllAgeWiseTableExcelDivId, reportName);   
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
					str+='<h3 class="panel-title text-capital">'+getState()+' state - district wise age report</h3>';
						
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="districtWiseExcelReport" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
					
					
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
		$('.districtWiseDataTableId').removeClass("dataTable");
	}
	
	$(document).on("click","#districtWiseExcelReport",function(){
		tableToExcel(districtWiseTableExcelDivId, getState() + " STATE - DISTRICT WISE AGE REPORT");  
	});
	
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
					str+='<h3 class="panel-title text-capital">'+getState()+' state - constituency wise age report</h3>';
						
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="constituencyWiseExcelReport" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
					
					
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
		$('.constituencyWiseDataTableId').removeClass("dataTable");
	}
	
	$(document).on("click","#constituencyWiseExcelReport",function(){
		tableToExcel(constituencyWiseTableExcelDivId, getState() + " STATE - CONSTITUENCY WISE AGE REPORT"); 
	});
	
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
					str+='<h3 class="panel-title text-capital"> '+getState()+' STATE - CASTE CATEGORY WISE SUMMARY </h3>';
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="overAllcasteWiseReportExcel" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
					str+='<table class="table table-bordered" id="overaAllCasteWiseTableExcelDivId">';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b class="f_14">Caste Group</th></b>';
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
		tableToExcel(overaAllCasteWiseTableExcelDivId, getState()+' STATE - CASTE CATEGORY WISE SUMMARY');       
	});
	
	//caste wise overAll reports End
	
	//caste Category state wise  reports start
	function getstateWiseTdpCadreCasteCounts(stateId){
		 $("#CasteCategoryStateWiseSummaryDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			         stateId:stateId
					}
		
		$.ajax({
			type : 'POST',
			url : 'stateWiseTdpCadreCasteCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#CasteCategoryStateWiseSummaryDetails").html('');
			 buildStateWiseTdpCadreCasteCountsAction(result,stateId);
			
		});
	}
	function buildStateWiseTdpCadreCasteCountsAction(result,stateId){
		var str='';
		var str1='';
		if(result !=null &&  result.length>0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
					str+='<h3 class="panel-title text-capital">'+getState()+' state - caste wise report </h3>';
						
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="CasteCategoryStateWiseReportExcel" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
					str+='<table class="table table-bordered table-condensed CasteCategoryStateWiseDataTableId" id="">';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b class="f_14">Caste Group</th></b>';
								str+='<th class="text-capital "><b class="f_14">Caste Name</th></b>';
								str+='<th class="text-capital " ><b class="f_14">2014 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2014 cadre&nbsp;%</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre&nbsp;%</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2014)</b></th>';
								str+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2016)</b></th>';
								str+='<th class="text-capital "><b class="f_14">New cadre</th>';
								str+='<th class="text-capital "><b class="f_14">New cadre&nbsp;%</b></th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							if(result[i].subList !=null && result[i].subList.length>0){
								for(var j in result[i].subList){
									str+='<tr>';
									str+='<td>'+result[i].name+'</td>';
									str+='<td>'+result[i].subList[j].name+'</td>';
									if(result[i].subList[j].previousCadreCount !=null && result[i].subList[j].previousCadreCount>0){
										str+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].subList[j].previousCadrePercent !=null && result[i].subList[j].previousCadrePercent>0){
										str+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].subList[j].cadreCount !=null && result[i].subList[j].cadreCount>0){
										str+='<td>'+result[i].subList[j].cadreCount+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].subList[j].cadrePercent !=null && result[i].subList[j].cadrePercent>0){
										str+='<td>'+result[i].subList[j].cadrePercent+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].subList[j].renewalCadre !=null && result[i].subList[j].renewalCadre>0){
										str+='<td>'+result[i].subList[j].renewalCadre+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].subList[j].previousCadreRenewalPercent !=null && result[i].subList[j].previousCadreRenewalPercent>0){
										str+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].subList[j].renewalCadrePercent !=null && result[i].subList[j].renewalCadrePercent>0){
										str+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].subList[j].newCadre !=null && result[i].subList[j].newCadre>0){
										str+='<td>'+result[i].subList[j].newCadre+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].subList[j].newCadrePercent !=null && result[i].subList[j].newCadrePercent>0){
										str+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
									}else{
										str+='<td> - </td>';
									}
									
									str+='</tr>';
								}
							}
						}
						
						str+='</tbody>';
						str+='</table>';
						
						str1+='<table class="table table-bordered table-condensed" id="casteCategoryStateWiseTableExcelDivId">';
						str1+='<thead>';
							str1+='<tr>';
								str1+='<th class="text-capital "><b class="f_14">Caste Group</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Caste Name</th></b>';
								str1+='<th class="text-capital " ><b class="f_14">2014 cadre</th></b>';
								str1+='<th class="text-capital "><b class="f_14">2014 cadre&nbsp;%</th></b>';
								str1+='<th class="text-capital "><b class="f_14">2016 cadre</th></b>';
								str1+='<th class="text-capital "><b class="f_14">2016 cadre&nbsp;%</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Renewal</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2014)</b></th>';
								str1+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2016)</b></th>';
								str1+='<th class="text-capital "><b class="f_14">New cadre</th>';
								str1+='<th class="text-capital "><b class="f_14">New cadre&nbsp;%</b></th>';
							str1+='</tr>';
						str1+='</thead>';
						str1+='<tbody>';
						for(var i in result){
							if(result[i].subList !=null && result[i].subList.length>0){
								for(var j in result[i].subList){
									str1+='<tr>';
									str1+='<td>'+result[i].name+'</td>';
									str1+='<td>'+result[i].subList[j].name+'</td>';
									if(result[i].subList[j].previousCadreCount !=null && result[i].subList[j].previousCadreCount>0){
										str1+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
									}else{
										str1+='<td> - </td>';
									}
									if(result[i].subList[j].previousCadrePercent !=null && result[i].subList[j].previousCadrePercent>0){
										str1+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
									}else{
										str1+='<td> - </td>';
									}
									if(result[i].subList[j].cadreCount !=null && result[i].subList[j].cadreCount>0){
										str1+='<td>'+result[i].subList[j].cadreCount+'</td>';
									}else{
										str1+='<td> - </td>';
									}
									if(result[i].subList[j].cadrePercent !=null && result[i].subList[j].cadrePercent>0){
										str1+='<td>'+result[i].subList[j].cadrePercent+'</td>';
									}else{
										str1+='<td> - </td>';
									}
									if(result[i].subList[j].renewalCadre !=null && result[i].subList[j].renewalCadre>0){
										str1+='<td>'+result[i].subList[j].renewalCadre+'</td>';
									}else{
										str1+='<td> - </td>';
									}
									if(result[i].subList[j].previousCadreRenewalPercent !=null && result[i].subList[j].previousCadreRenewalPercent>0){
										str1+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
									}else{
										str1+='<td> - </td>';
									}
									if(result[i].subList[j].renewalCadrePercent !=null && result[i].subList[j].renewalCadrePercent>0){
										str1+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
									}else{
										str1+='<td> - </td>';
									}
									if(result[i].subList[j].newCadre !=null && result[i].subList[j].newCadre>0){
										str1+='<td>'+result[i].subList[j].newCadre+'</td>';
									}else{
										str1+='<td> - </td>';
									}
									if(result[i].subList[j].newCadrePercent !=null && result[i].subList[j].newCadrePercent>0){
										str1+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
									}else{
										str1+='<td> - </td>';
									}
									
									str1+='</tr>';
								}
							}
						}
						
						str1+='</tbody>';
						str1+='</table>';
						
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
					str+='</div>';
				str+='</div>';
		}else{
			$("#CasteCategoryStateWiseSummaryDetails").html("NO DATA AVAILABLE");
		}
		
		$("#CasteCategoryStateWiseSummaryDetails").html(str);
		$("#excelReportCastCategoryStateWiseDetails").html(str1);
		$(".CasteCategoryStateWiseDataTableId").dataTable();
		$(".CasteCategoryStateWiseDataTableId").removeClass("dataTable");
		
		$(document).on("click","#CasteCategoryStateWiseReportExcel",function(){
		   tableToExcel(casteCategoryStateWiseTableExcelDivId, getState() + 'STATE - CASTE WISE REPORT');      
		});
	}
	//caste Category state wise  reports End
	
	
	//caste Category district wise  reports start
	function getdistrictWiseTdpCadreCasteCounts(stateId,districtId){
		 $("#CasteCategoryDistrictWiseSummaryDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			         stateId:stateId,
					 districtId:districtId
					}
		
		$.ajax({
			type : 'POST',
			url : 'districtWiseTdpCadreCasteCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#CasteCategoryDistrictWiseSummaryDetails").html('');
			 buildDistrictWiseTdpCadreCasteCounts(result,stateId);
			
		});
	}
	function buildDistrictWiseTdpCadreCasteCounts(result,stateId){
		var str='';
		var str1='';
		if(result !=null &&  result.length>0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
					str+='<h3 class="panel-title text-capital">'+getState()+' STATE - DISTRICT WISE CASTE REPORT</h3>';
						
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="CasteCategoryDistrictWiseReportExcel" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
					str+='<table class="table table-bordered table-condensed CasteCategoryDistrictWiseDataTableId" id="">';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b class="f_14">District</th></b>';
								str+='<th class="text-capital "><b class="f_14">Caste Group</th></b>';
								str+='<th class="text-capital "><b class="f_14">Caste Name</th></b>';
								str+='<th class="text-capital " ><b class="f_14">2014 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2014 cadre&nbsp;%</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre&nbsp;%</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2014)</b></th>';
								str+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2016)</b></th>';
								str+='<th class="text-capital "><b class="f_14">New cadre</th>';
								str+='<th class="text-capital "><b class="f_14">New cadre&nbsp;%</b></th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							if(result[i].subList !=null && result[i].subList.length>0){
								for(var j in result[i].subList){
									
									if(result[i].subList[j].subList !=null && result[i].subList[j].subList.length>0){
										for(var k in result[i].subList[j].subList){
											str+='<tr>';
											str+='<td>'+result[i].name+'</td>';
											str+='<td>'+result[i].subList[j].name+'</td>';
											str+='<td>'+result[i].subList[j].subList[k].name+'</td>';
											if(result[i].subList[j].subList[k].previousCadreCount !=null && result[i].subList[j].subList[k].previousCadreCount>0){
											str+='<td>'+result[i].subList[j].subList[k].previousCadreCount+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].previousCadrePercent !=null && result[i].subList[j].subList[k].previousCadrePercent>0){
												str+='<td>'+result[i].subList[j].subList[k].previousCadrePercent+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].cadreCount !=null && result[i].subList[j].subList[k].cadreCount>0){
												str+='<td>'+result[i].subList[j].subList[k].cadreCount+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].cadrePercent !=null && result[i].subList[j].subList[k].cadrePercent>0){
												str+='<td>'+result[i].subList[j].subList[k].cadrePercent+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].renewalCadre !=null && result[i].subList[j].subList[k].renewalCadre>0){
												str+='<td>'+result[i].subList[j].subList[k].renewalCadre+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].previousCadreRenewalPercent !=null && result[i].subList[j].subList[k].previousCadreRenewalPercent>0){
												str+='<td>'+result[i].subList[j].subList[k].previousCadreRenewalPercent+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].renewalCadrePercent !=null && result[i].subList[j].subList[k].renewalCadrePercent>0){
												str+='<td>'+result[i].subList[j].subList[k].renewalCadrePercent+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].newCadre !=null && result[i].subList[j].subList[k].newCadre>0){
												str+='<td>'+result[i].subList[j].subList[k].newCadre+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].newCadrePercent !=null && result[i].subList[j].subList[k].newCadrePercent>0){
												str+='<td>'+result[i].subList[j].subList[k].newCadrePercent+'</td>';
											}else{
												str+='<td> - </td>';
											}
										}
										str+='</tr>';
									}
									
									
									
									
								}
							}
						}
						
						str+='</tbody>';
						str+='</table>';
						//for fetching all records start 
						str1+='<table class="table table-bordered table-condensed" id="CasteCategoryDistrictWiseTableExcelDivId">';
						str1+='<thead>';
							str1+='<tr>';
								str1+='<th class="text-capital "><b class="f_14">District</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Caste Group</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Caste Name</th></b>';
								str1+='<th class="text-capital " ><b class="f_14">2014 cadre</th></b>';
								str1+='<th class="text-capital "><b class="f_14">2014 cadre&nbsp;%</th></b>';
								str1+='<th class="text-capital "><b class="f_14">2016 cadre</th></b>';
								str1+='<th class="text-capital "><b class="f_14">2016 cadre&nbsp;%</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Renewal</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2014)</b></th>';
								str1+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2016)</b></th>';
								str1+='<th class="text-capital "><b class="f_14">New cadre</th>';
								str1+='<th class="text-capital "><b class="f_14">New cadre&nbsp;%</b></th>';
							str1+='</tr>';
						str1+='</thead>';
						str1+='<tbody>';
						for(var i in result){
							if(result[i].subList !=null && result[i].subList.length>0){
								for(var j in result[i].subList){
									
									if(result[i].subList[j].subList !=null && result[i].subList[j].subList.length>0){
										for(var k in result[i].subList[j].subList){
											str1+='<tr>';
											str1+='<td>'+result[i].name+'</td>';
											str1+='<td>'+result[i].subList[j].name+'</td>';
											str1+='<td>'+result[i].subList[j].subList[k].name+'</td>';
											if(result[i].subList[j].subList[k].previousCadreCount !=null && result[i].subList[j].subList[k].previousCadreCount>0){
											str1+='<td>'+result[i].subList[j].subList[k].previousCadreCount+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].previousCadrePercent !=null && result[i].subList[j].subList[k].previousCadrePercent>0){
												str1+='<td>'+result[i].subList[j].subList[k].previousCadrePercent+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].cadreCount !=null && result[i].subList[j].subList[k].cadreCount>0){
												str1+='<td>'+result[i].subList[j].subList[k].cadreCount+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].cadrePercent !=null && result[i].subList[j].subList[k].cadrePercent>0){
												str1+='<td>'+result[i].subList[j].subList[k].cadrePercent+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].renewalCadre !=null && result[i].subList[j].subList[k].renewalCadre>0){
												str1+='<td>'+result[i].subList[j].subList[k].renewalCadre+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].previousCadreRenewalPercent !=null && result[i].subList[j].subList[k].previousCadreRenewalPercent>0){
												str1+='<td>'+result[i].subList[j].subList[k].previousCadreRenewalPercent+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].renewalCadrePercent !=null && result[i].subList[j].subList[k].renewalCadrePercent>0){
												str1+='<td>'+result[i].subList[j].subList[k].renewalCadrePercent+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].newCadre !=null && result[i].subList[j].subList[k].newCadre>0){
												str1+='<td>'+result[i].subList[j].subList[k].newCadre+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].subList[k].newCadrePercent !=null && result[i].subList[j].subList[k].newCadrePercent>0){
												str1+='<td>'+result[i].subList[j].subList[k].newCadrePercent+'</td>';
											}else{
												str1+='<td> - </td>';
											}
										}
										str1+='</tr>';
									}
									
									
									
									
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
			$("#CasteCategoryDistrictWiseSummaryDetails").html("NO DATA AVAILABLE");
		}
		
		$("#CasteCategoryDistrictWiseSummaryDetails").html(str);
		$("#excelDivGCasteCategoryDistrictWiseSummaryDetails").html(str1);
		$(".CasteCategoryDistrictWiseDataTableId").dataTable();//{"aaSorting": [ [0,'asc'], [4,'desc'] ] }
		$(".CasteCategoryDistrictWiseDataTableId").removeClass("dataTable");
		
		$(document).on("click","#CasteCategoryDistrictWiseReportExcel",function(){
		  tableToExcel(CasteCategoryDistrictWiseTableExcelDivId, getState() +'STATE - DISTRICT WISE CASTE REPORT'); 
		});
		
	}
	//caste Category District wise  reports End
	
	
	//caste Category Constituency wise  reports start
	function getConstituencyWiseTdpCadreCasteCountsAction(stateId,districtId){
		 $("#CasteCategoryConstituencyWiseSummaryDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			         stateId:stateId,
					 districtId:districtId
					}
		
		$.ajax({
			type : 'POST',
			url : 'constituencyWiseTdpCadreCasteCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			 $("#CasteCategoryConstituencyWiseSummaryDetails").html('');
			 buildConstituencyWiseTdpCadreCasteCountsAction(result,stateId);
			
		});
	}
	function buildConstituencyWiseTdpCadreCasteCountsAction(result,stateId){
		var str='';
		var str1='';
		if(result !=null &&  result.length>0){
				str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
				str+='<div class="panel panel-default">';
				  str+='<div class="panel-heading" style="background-color: #f3f3f3 ! important;">';
				  str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-4">';
					str+='<h3 class="panel-title text-capital">'+getState()+' STATE - CONSTITUENCY WISE CASTE REPORT</h3>';
						
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="CasteCategoryConstituencyWiseReportExcel" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
					str+='<table class="table table-bordered table-condensed CasteCategoryConstituencyWiseDataTableId" id="">';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital "><b class="f_14">District</th></b>';
								str+='<th class="text-capital "><b class="f_14">Constituency</th></b>';
								str+='<th class="text-capital "><b class="f_14">Caste Name</th></b>';
								str+='<th class="text-capital " ><b class="f_14">2014 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2014 cadre&nbsp;%</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre</th></b>';
								str+='<th class="text-capital "><b class="f_14">2016 cadre&nbsp;%</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal</th></b>';
								str+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2014)</b></th>';
								str+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2016)</b></th>';
								str+='<th class="text-capital "><b class="f_14">New cadre</th>';
								str+='<th class="text-capital "><b class="f_14">New cadre&nbsp;%</b></th>';
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
											if(result[i].subList[j].previousCadreCount !=null && result[i].subList[j].previousCadreCount>0){
											str+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].previousCadrePercent !=null && result[i].subList[j].previousCadrePercent>0){
												str+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].cadreCount !=null && result[i].subList[j].cadreCount>0){
												str+='<td>'+result[i].subList[j].cadreCount+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].cadrePercent !=null && result[i].subList[j].cadrePercent>0){
												str+='<td>'+result[i].subList[j].cadrePercent+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].renewalCadre !=null && result[i].subList[j].renewalCadre>0){
												str+='<td>'+result[i].subList[j].renewalCadre+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].previousCadreRenewalPercent !=null && result[i].subList[j].previousCadreRenewalPercent>0){
												str+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].renewalCadrePercent !=null && result[i].subList[j].renewalCadrePercent>0){
												str+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].newCadre !=null && result[i].subList[j].newCadre>0){
												str+='<td>'+result[i].subList[j].newCadre+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList[j].newCadrePercent !=null && result[i].subList[j].newCadrePercent>0){
												str+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
											}else{
												str+='<td> - </td>';
											}
										
										str+='</tr>';
									
									
									
									
									
								}
							}
						}
						
						str+='</tbody>';
						str+='</table>';
						//for fetching all records start 
						str1+='<table class="table table-bordered table-condensed" id="CasteCategoryConstituencyWiseTableExcelDivId">';
						str1+='<thead>';
							str1+='<tr>';
								str1+='<th class="text-capital "><b class="f_14">District</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Caste Group</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Caste Name</th></b>';
								str1+='<th class="text-capital " ><b class="f_14">2014 cadre</th></b>';
								str1+='<th class="text-capital "><b class="f_14">2014 cadre&nbsp;%</th></b>';
								str1+='<th class="text-capital "><b class="f_14">2016 cadre</th></b>';
								str1+='<th class="text-capital "><b class="f_14">2016 cadre&nbsp;%</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Renewal</th></b>';
								str1+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2014)</b></th>';
								str1+='<th class="text-capital "><b class="f_14">Renewal&nbsp;%</br>(From&nbsp;2016)</b></th>';
								str1+='<th class="text-capital "><b class="f_14">New cadre</th>';
								str1+='<th class="text-capital "><b class="f_14">New cadre&nbsp;%</b></th>';
							str1+='</tr>';
						str1+='</thead>';
						str1+='<tbody>';
						for(var i in result){
							if(result[i].subList !=null && result[i].subList.length>0){
								for(var j in result[i].subList){
									
											str1+='<tr>';
											str1+='<td>'+result[i].superlocationName+'</td>';
											str1+='<td>'+result[i].name+'</td>';
											str1+='<td>'+result[i].subList[j].name+'</td>';
											if(result[i].subList[j].previousCadreCount !=null && result[i].subList[j].previousCadreCount>0){
											str1+='<td>'+result[i].subList[j].previousCadreCount+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].previousCadrePercent !=null && result[i].subList[j].previousCadrePercent>0){
												str1+='<td>'+result[i].subList[j].previousCadrePercent+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].cadreCount !=null && result[i].subList[j].cadreCount>0){
												str1+='<td>'+result[i].subList[j].cadreCount+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].cadrePercent !=null && result[i].subList[j].cadrePercent>0){
												str1+='<td>'+result[i].subList[j].cadrePercent+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].renewalCadre !=null && result[i].subList[j].renewalCadre>0){
												str1+='<td>'+result[i].subList[j].renewalCadre+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].previousCadreRenewalPercent !=null && result[i].subList[j].previousCadreRenewalPercent>0){
												str1+='<td>'+result[i].subList[j].previousCadreRenewalPercent+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].renewalCadrePercent !=null && result[i].subList[j].renewalCadrePercent>0){
												str1+='<td>'+result[i].subList[j].renewalCadrePercent+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].newCadre !=null && result[i].subList[j].newCadre>0){
												str1+='<td>'+result[i].subList[j].newCadre+'</td>';
											}else{
												str1+='<td> - </td>';
											}
											if(result[i].subList[j].newCadrePercent !=null && result[i].subList[j].newCadrePercent>0){
												str1+='<td>'+result[i].subList[j].newCadrePercent+'</td>';
											}else{
												str1+='<td> - </td>';
											}
										
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
			$("#CasteCategoryConstituencyWiseSummaryDetails").html("NO DATA AVAILABLE");
		}
		
		$("#CasteCategoryConstituencyWiseSummaryDetails").html(str);
		$("#excelDivGCasteCategoryConstituencyWiseSummaryDetails").html(str1);
		$(".CasteCategoryConstituencyWiseDataTableId").dataTable();
		$(".CasteCategoryConstituencyWiseDataTableId").removeClass("dataTable");
		
		$(document).on("click","#CasteCategoryConstituencyWiseReportExcel",function(){
			tableToExcel(CasteCategoryConstituencyWiseTableExcelDivId, getState() +' STATE - CONSTITUENCY WISE CASTE REPORT');   
		});
		
	}
	//caste Category Constituency wise  reports End
	
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
					str+='<h3 class="panel-title text-capital">'+getState()+' STATE - GENDER WISE REPORT</h3>';
						
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="overAllGenderWiseExcelId" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
					 str+='<label class="pull-right text-muted" style="margin-top: -10px;">Note: M - Male & F - Female</label>';
					str+='<div class="table-responsive m_top20">';
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
								str+='<th class="text-capital " ><b >Renewal&nbsp;%&nbsp;</br>(From 2014)</b></th>';
								str+='<th class="text-capital " ><b >Renewal&nbsp;%&nbsp;</br>(From 2016)</b></th>';
								str+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >New Cadre</b></th>';
								str+='<th class="text-capital " ><b >New Cadre&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
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
									str+='<td>'+result.renewalCadreMalePerc+'</td>';
									str+='<td>'+result.renewalCadreFemalePerc+'</td>';
									
									str+='<td>'+result.newCadreTotalCount+'</td>';
									str+='<td>'+result.newCadrePerc+'</td>';
									str+='<td>'+result.newCadreMalePerc+'</td>';
									str+='<td>'+result.newCadreFemalePerc+'</td>';
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
		tableToExcel(overAllGenderWiseTableToExcel, getState() + ' STATE - GENDER WISE REPORT');  
	});
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
					str+='<h3 class="panel-title text-capital"> '+getState()+' STATE - DISRTICT WISE GENDER REPORT </h3>';
						
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="districtGenderWiseExcelId" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
				  str+='<label class="pull-right text-muted" style="margin-top: -10px;">Note:R - Renewal & N-New Cadre & M - Male & F - Female</label>';
					str+='<div class="table-responsive m_top20">';
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
								str+='<th class="text-capital " ><b >R</th></b>';
								str+='<th class="text-capital " ><b >R&nbsp;%&nbsp;</br>(From 2014)</b></th>';
								str+='<th class="text-capital " ><b >R&nbsp;%&nbsp;</br>(From 2016)</b></th>';
								str+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >N</b></th>';
								str+='<th class="text-capital " ><b >N&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
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
									str+='<td>'+result[i].renewalCadreMalePerc+'</td>';
									str+='<td>'+result[i].renewalCadreFemalePerc+'</td>';
									
									str+='<td>'+result[i].newCadreTotalCount+'</td>';
									str+='<td>'+result[i].newCadrePerc+'</td>';
									str+='<td>'+result[i].newCadreMalePerc+'</td>';
									str+='<td>'+result[i].newCadreFemalePerc+'</td>';
									
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
		tableToExcel(districtGenderWiseTableToExcel, getState() +' STATE - DISRTICT WISE GENDER REPORT');    
	});
	
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
					
					str+='<h3 class="panel-title text-capital"> '+getState()+' STATE - CONSTITUENCY WISE GENDER REPORT </h3>';
						
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						str+='<button class="btn btn-success btn-xs" id="constituencyGenderWiseExcelId" >Export To Excel</button></h4>';
					str+='</div>';
				  str+='</div>';
				  str+='</div>';
				  str+='<div class="panel-body">';
				    str+='<label class="pull-right text-muted" style="margin-top: -10px;">Note:R-Renewal & N-New Cadre & M - Male & F - Female</label>';
					str+='<div class="table-responsive m_top20" >';
					str+='<table class="table table-bordered tableHeaderStyle table-condensed  constituencyWiseGenderDataTableId" id="">';
						str+='<thead>';
							str+='<tr>';
								str+='<th class="text-capital " rowspan="2" ><b>District</b></th>';
								str+='<th class="text-capital " rowspan="2" ><b>cons</b></th>';
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
								str+='<th class="text-capital " ><b >R</th></b>';
								str+='<th class="text-capital " ><b >R&nbsp;%&nbsp;</br>(From 2014)</b></th>';
								str+='<th class="text-capital " ><b >R&nbsp;%&nbsp;</br>(From 2016)</b></th>';
								str+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >N</b></th>';
								str+='<th class="text-capital " ><b >N&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
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
									str+='<td>'+result[i].renewalCadreMalePerc+'</td>';
									str+='<td>'+result[i].renewalCadreFemalePerc+'</td>';
									
									str+='<td>'+result[i].newCadreTotalCount+'</td>';
									str+='<td>'+result[i].newCadrePerc+'</td>';
									str+='<td>'+result[i].newCadreMalePerc+'</td>';
									str+='<td>'+result[i].newCadreFemalePerc+'</td>';
									
									
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
								str1+='<th class="text-capital " ><b >R</th></b>';
								str1+='<th class="text-capital " ><b >R&nbsp;%&nbsp; (From 2014)</b></th>';
								str1+='<th class="text-capital " ><b >R&nbsp;%&nbsp; (From 2016)</b></th>';
								str1+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str1+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
								str1+='<th class="text-capital " ><b >N</b></th>';
								str1+='<th class="text-capital " ><b >N&nbsp;%</b></th>';
								str1+='<th class="text-capital " ><b >M&nbsp;%</b></th>';
								str1+='<th class="text-capital " ><b >F&nbsp;%</b></th>';
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
									str1+='<td>'+result[i].renewalCadreMalePerc+'</td>';
									str1+='<td>'+result[i].renewalCadreFemalePerc+'</td>';
									
									str1+='<td>'+result[i].newCadreTotalCount+'</td>';
									str1+='<td>'+result[i].newCadrePerc+'</td>';
									str1+='<td>'+result[i].newCadreMalePerc+'</td>';
									str1+='<td>'+result[i].newCadreFemalePerc+'</td>';
									
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
		$('.constituencyWiseGenderDataTableId').removeClass("dataTable")
	}
	$(document).on("click","#constituencyGenderWiseExcelId",function(){
		tableToExcel(constituencyGenderWiseTableToExcel, getState() + 'STATE - CONSTITUENCY WISE GENDER REPORT ');   
	});
	
	//Gender Wise Constituency Reports End
	