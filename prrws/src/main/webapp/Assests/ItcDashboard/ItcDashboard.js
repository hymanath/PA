var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

//var departmentWiseArr=[{name:'Promotions',id:'1',color:'#0D3B54',image:'promotions',blockName:'promotions'},{name:'E Office',id:'2',color:'#1394B9',image:'eOffice',blockName:'eOffice'},{name:'Meeseva & SLA',id:'3',color:'#638D00',image:'meeseva',blockName:'meesevaSla'},{name:'Meeseva & KPI',id:'4',color:'#9B7A00',image:'meesevaHigh',blockName:'meesevaKpi'},{name:'eProcurement',id:'5',color:'#F06C1F',image:'eProcurement',blockName:'eProcurement'},{name:'CM eoDB',id:'6',color:'#C02D1D',image:'cMeoDB',blockName:'cMeoDB'}];

var departmentWiseArr=[{name:'Meeseva & SLA',id:'3',color:'#638D00',image:'meeseva',blockName:'meesevaSla'}];
onloadCalls();
function onloadCalls(){
	departmentBlockWiseDetails("meesevaSla");
	departmentWiseOverView();
	/*getPromotionsOverviewByDepartmentType();
	getPromotionsDetailedDepartmentWise();
	getEOfficePendencyDtlsByDepartmentType();
	getEOfficePendencyByDepartmentAndDayWise();*/
	/*getMeesevaKPIIndicatorsProgressDtls();
	getMeesevaKPIIndicatorsPeriodWise();
	getCMEDOBOverview();
	getCMEDOBReportStatusWise();
	getAPInnovationSocietyOverview();
	getAPISXLR8APDetailedData();
	getCampaignsDetailedData();
	getCampusInnovationCentersDetailedData();
	getSuoMotoProposalsDetailedData();
	getInnovationAwardsDetailedData();*/
}
function departmentWiseOverView(){
	var block='';
	for(var i in departmentWiseArr){
		block+='<div class="col-sm-2 m_top10">';
			block+='<div style="background-color:'+departmentWiseArr[i].color+';" class="block_style_ITC blockWiseDetails" attr_block_name="'+departmentWiseArr[i].blockName+'">';
					block+='<div class="media" style="margin-left: 25px;">';
						block+='<img src="Assests/icons/ITC/'+departmentWiseArr[i].image+'.png" class="pull-left">';
					  block+='<div class="media-body">';
						block+='<h5><b>'+departmentWiseArr[i].name+'</b></h5>';
						if(departmentWiseArr[i].id ==3){
							block+='<h6 style="font-size:8px;color:#d3d3d3;">Department & District Wise SLA Monitoring</h6>';
						}else if(departmentWiseArr[i].id ==4){
							block+='<h6 style="font-size:8px;color:#d3d3d3;">Highest Performance</h6>';
						}
					  block+='</div>';
					block+='</div>';
					if(departmentWiseArr[i].id ==1){
						block+='<div class="m_top40">';
							block+='<h2>11,25.Cr</h2>';
							block+='<h6>Committed Investment</h6>';
						block+='<h6>(IT,E&F)</h6>';
						block+='</div>';
					}else if(departmentWiseArr[i].id ==2){
						block+='<div class="m_top50">';
							block+='<h2>11,25.Cr</h2>';
							block+='<h6 class="m_top5">Total Pendency</h6>';
						block+='</div>';
					}else if(departmentWiseArr[i].id ==3){
						block+='<div class="m_top40">';
							block+='<h2>11,25.Cr</h2>';
							block+='<h6 class="m_top5">Beyond SLA</h6>';
						block+='</div>';
					}else if(departmentWiseArr[i].id ==4){
						block+='<div class="m_top40">';
							block+='<h2>11,25.Cr</h2>';
							block+='<h6 class="m_top10">etaal - KPI</h6>';
						block+='</div>';
					}else if(departmentWiseArr[i].id ==5){
						block+='<div class="m_top40">';
							block+='<h2>11,25.Cr</h2>';
							block+='<h6 class="m_top10">IT&E Department Tenders</h6>';
						block+='</div>';
					}else if(departmentWiseArr[i].id ==6){
						block+='<div class="m_top25">';
							block+='<h4>2369/</h4>';
							block+='<h2>11,25.Cr</h2>';
							block+='<h6 class="m_top10">Total / Approved</h6>';
						block+='</div>';
					}
			block+='</div>';
		block+='</div>';
	}
	$("#departmentWiseDivId").html(block);
	
}
$(document).on('click','.blockWiseDetails',function(){
	var blockName = $(this).attr("attr_block_name");
	departmentBlockWiseDetails(blockName);
})
function departmentBlockWiseDetails(divId)
{
	var levelWiseBlockArr='';
	if(divId == "promotions"){
		
		levelWiseBlockArr=[{name:'Promotions',id:'1'},{name:'Electronics',id:'2'},{name:'FinTech',id:'3'}];
		
	}else if(divId == "eOffice"){
		
		levelWiseBlockArr=[{name:'E Office',id:'4'}];
		
	}else if(divId == "meesevaSla"){
		
		levelWiseBlockArr=[{name:'Meeseva-SLA',id:'5'}];
		
	}else if(divId == "meesevaKpi"){
		
		levelWiseBlockArr=[{name:'Meeseva & KPI',id:'6'}];
		
	}else if(divId == "eProcurement"){
		levelWiseBlockArr=[{name:'eProcurement',id:'7'}];
		
	}else if(divId == "cMeoDB"){
		
		levelWiseBlockArr=[{name:'CM eoDB',id:'8'}];
	}
	
			var collapse='';
				for(var i in levelWiseBlockArr)
				{
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseBlockArr[i].id+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'">';
								}else{
									collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'">';
								}
								collapse+='<h4 class="panel-title text-capital">'+levelWiseBlockArr[i].name+' overview</h4>';
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'">';
							}else{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+divId.replace(/\s+/g, '')+'Block'+levelWiseBlockArr[i].id+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			$("#departmentBlockWiseDetailsId").html(collapse);
			
			for(var i in levelWiseBlockArr){
				if(divId == "meesevaSla"){
					getMeesevaSLAOverviewDtls(divId,levelWiseBlockArr[i].id);
					getMeesevaSLAMonitoringDtlsDepartmentWise();
				}
			}
}

/* function getOverAllDetils(divId,blockId){
	
	var str='';
	
	if(divId == "promotions"){
		if(blockId == 1){
			str+='Promotionsfsdfsdfs';
		}else if(blockId == 2){
			str+='electronics';
		}else if(blockId == 3){
			str+='fintech';
		}
	}else if(divId == "eOffice"){
		str+='Promotionsdfsdfs';
	}else if(divId == "meesevaSla"){
		
		
		
	}else if(divId == "meesevaKpi"){
		str+='Promotiondasdsadada233sdfss';
	}else if(divId == "eProcurement"){
		str+='Promotionsadfse';
	}else if(divId == "cMeoDB"){
		str+='Promotiosadsaedsansf';
	} 
	$("#"+divId+"Block"+blockId).html(str);
} */

function getMeesevaSLAOverviewDtls(divId,blockId){
	var json = {
	    fromDate:"01/04/2016",
		toDate:"01/09/2017",
		year:"",
		filterId:"2",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLAOverviewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildMeesevaSLAOverviewDtls(result,divId,blockId);
		}
	});		
}

function buildMeesevaSLAOverviewDtls(result,divId,blockId){
	var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-12">';
				for(var i in result){
					str+='<div class="col-sm-4">';
						str+='<div class="white_block_ITC">';
							if(result[i].name == "TOTAL TRANSACTIONS"){
								str+='<div style="border-left:5px solid #000;">';
							}else if(result[i].name == "With in SLA"){
								str+='<div style="border-left:5px solid #009587;">';
							}else if(result[i].name == "Beyond SLA"){
								str+='<div style="border-left:5px solid #F75C5D;">';
							}
							
								str+='<h4 class="m_left10"><b>'+result[i].name+'</b></h4>';
								str+='<h5 class="m_left10">('+result[i].departmentCount+' DEPARTMENTS)</h5>';
								str+='<h1 class="m_top10 m_left10">'+result[i].totalCount+'</h1>';
							str+='</div>';
							str+='<div class="row m_top20">';
								str+='<div class="col-sm-12 white_block_Dep">';
									for(var j in result[i].subList){
										str+='<div class="col-sm-6">';
											str+='<h5 class="m_top5"><b>'+result[i].subList[j].name+'</b>';
											str+='<h3 class="m_top10">'+result[i].subList[j].totalCount+'</h3>';
										str+='</div>';
									}
								str+='</div>';	
							str+='</div>';
						str+='</div>';
						str+='</div>';
				}
				
						
				
				
			str+='</div>';
		str+='</div>';
	$("#"+divId+"Block"+blockId).html(str);
}

function getMeesevaSLAMonitoringDtlsDepartmentWise(){
	var json = {
		fromDate:"01/04/2016",
		toDate:"01/09/2017",
		year:"",
		filterId:"2"
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLAMonitoringDtlsDepartmentWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}

function getPromotionsOverviewByDepartmentType(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getPromotionsOverviewByDepartmentType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getPromotionsDetailedDepartmentWise(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getPromotionsDetailedDepartmentWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getEOfficePendencyDtlsByDepartmentType(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfficePendencyDtlsByDepartmentType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getEOfficePendencyByDepartmentAndDayWise(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfficePendencyByDepartmentAndDayWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}


function getMeesevaKPIIndicatorsProgressDtls(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIIndicatorsProgressDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getMeesevaKPIIndicatorsPeriodWise(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIIndicatorsPeriodWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getCMEDOBOverview(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getCMEDOBOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getCMEDOBReportStatusWise(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getCMEDOBReportStatusWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getAPInnovationSocietyOverview(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getAPInnovationSocietyOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getAPISXLR8APDetailedData(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getAPISXLR8APDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getCampaignsDetailedData(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getCampaignsDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}
function getCampusInnovationCentersDetailedData(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getCampusInnovationCentersDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}function getSuoMotoProposalsDetailedData(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getSuoMotoProposalsDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}function getInnovationAwardsDetailedData(){
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getInnovationAwardsDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		console.log(result);
	});		
}