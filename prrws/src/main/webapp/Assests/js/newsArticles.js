var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
	wurl = url.substr(0,(url.indexOf(".in")+3));

	var glStartDate = moment().format("DD-MM-YYYY");
	var glEndDate = moment().format("DD-MM-YYYY");
	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
	
	$("#dateRangePickerAUM").daterangepicker({
		opens:'left',
		startDate: glStartDate,
		endDate: glEndDate,
		locale: {
			format: "DD-MM-YYYY",
		},
		ranges: {
		  // 'All':[moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY"), moment().add(10, 'years').endOf('year').format("DD-MM-YYYY")],
		   'Today' : [moment(), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()]
		  
		}
	});
	$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
		glStartDate = picker.startDate.format("DD-MM-YYYY");
		glEndDate = picker.endDate.format("DD-MM-YYYY");
		onLoadCalls();
	});
	setTimeout(function(){
		onLoadCalls();
	},2000);
	function onLoadCalls(){
		if(globalDeptId !="0"){
			$("#newsDiv").hide();
		$(".removecss").removeClass("active")
		$(".addcss").addClass("active")
		getDepartmentWiSeBlockDetails("PrintMediadepartment","printMedia","department");
		}else if(globalDeptId =="0"){
			$("#newsDiv").show();
		getPrintMediaCountsDetailsInfo();
		getDepartMentWiseAllNewsBulletinsAndPrograms();
		getDistrictWiseTotalOverViewInfo();
		getDistrictWiseNewsTotalOverviewForElectronicMedia();
		$(".removecss").removeClass("active")
		$(".addcss").addClass("active")
		getDepartmentWiSeBlockDetails("PrintMediadepartment","printMedia","department");
		}
		
	}
	$(document).on("click",".switch-btn li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var type = $(this).attr("attr_type");
		if(type == "electronic"){
			getDepartmentWiSeBlockEMDetails("ElectronicMediadepartment","electronicMedia","departmentEle");
		}else{
			getDepartmentWiSeBlockDetails("PrintMediadepartment","printMedia","department");
		}
		
	});	
	 function getPrintMediaCountsDetailsInfo(){
		 $("#overAllPrintMediaDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaCountsDetailsInfo/"+glStartDate+"/"+glEndDate+"/"+globalDeptId
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getPrintMediaCountsDetailsInfo/"+glStartDate+"/"+glEndDate+"/"+globalDeptId
		}).then(function(result){
			if(result !=null && result.length>0){
				buildOverAllPrintMediaDetails(result,"PrintMedia","overAllPrintMediaDivId","overAll",0);
			}else{
				$("#overAllPrintMediaDivId").html("No Data Available");
			}
		});
	} 
	function getDepartMentWiseAllNewsBulletinsAndPrograms(){
		 $("#overAllElectronicMediaDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDepartMentWiseAllNewsBulletinsAndPrograms/"+glStartDate+"/"+glEndDate+"/All"+"/"+globalDeptId
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartMentWiseAllNewsBulletinsAndPrograms/"+glStartDate+"/"+glEndDate+"/All"+"/"+globalDeptId
		}).then(function(result){
				if(result !=null && result.length>0){
					for(var i in result){
						if(result[i].tdpCount !=null && result[i].tdpCount >0){
							buildOverAllPrintMediaDetails(result,"ElectronicMedia","overAllElectronicMediaDivId","overAllEle",0);
						}else{
							$("#overAllElectronicMediaDivId").html("No Data Available");
						}
					}
				}else{
					$("#overAllElectronicMediaDivId").html("No Data Available");
				}
		});
	}
	function getDistrictWiseTotalOverViewInfo(){
		$("#overAllDistrictWiseDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDistrictWiseTotalOverViewInfo/"+glStartDate+"/"+glEndDate+"/"+globalDeptId+"/"
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDistrictWiseTotalOverViewInfo/"+glStartDate+"/"+glEndDate+"/"+globalDeptId+"/"
		}).then(function(result){
			if(result !=null && result.length>0){
					buildOverAllDistrictWiseDetails(result,"overAllDistrictWiseDivId",globalDeptId,"overAllPrint");
			}else{
				$("#overAllDistrictWiseDivId").html("No Data Available");
			}
		});
	}
	function getDistrictWiseNewsTotalOverviewForElectronicMedia(){
		$("#overAllDistrictEMWiseDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDistrictWiseNewsTotalOverviewForElectronicMedia/"+glStartDate+"/"+glEndDate+"/''"+"/"+globalDeptId+"/"
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDistrictWiseNewsTotalOverviewForElectronicMedia/"+glStartDate+"/"+glEndDate+"/''"+"/"+globalDeptId+"/"
		}).then(function(result){
			if(result !=null && result.length>0){
					buildOverAllDistrictWiseDetails(result,"overAllDistrictEMWiseDivId",globalDeptId,"overAllEM");
			}else{
				$("#overAllDistrictEMWiseDivId").html("No Data Available");
			}
		});
	}
	function getDepartmentWiSeBlockDetails(type,divId,typeVal){
		$("#departmentWiseDetailsDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDepartmentWiSeBlockDetails/"+glStartDate+"/"+glEndDate+"/"+globalDeptId+"/"
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartmentWiSeBlockDetails/"+glStartDate+"/"+glEndDate+"/"+globalDeptId+"/"
		}).then(function(result){
			if(result !=null && result.length>0){
				buildDepartmentWiSeBlockDetails(result,type,divId,typeVal);
			}else{
				$("#departmentWiseDetailsDivId").html("No Data Available");
			}
		});
	}
	function getDepartmentWiSeBlockEMDetails(type,divId,typeVal){
		$("#departmentWiseDetailsDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDepartmentWiSeBlockDetailsForEMN/"+glStartDate+"/"+glEndDate+"/"+globalDeptId+"/"
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartmentWiSeBlockDetailsForEMN/"+glStartDate+"/"+glEndDate+"/"+globalDeptId+"/"
		}).then(function(result){
			if(result !=null && result.length>0){
				buildDepartmentWiSeBlockDetails(result,type,divId,typeVal);
			}else{
				$("#departmentWiseDetailsDivId").html("No Data Available");
			}
		});
	}
function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title){
	'use strict';
	$('#'+id).highcharts({
		colors: colors,
		chart: type,
		title: title,
		subtitle: {
			text: null
		},
		xAxis: xAxis,
		yAxis: yAxis,
		tooltip: tooltip,
		plotOptions: plotOptions,
		legend: legend,
		series: data
	});
}
function highchartsPieChart(id,type,data,plotOptions,title,tooltip,legend){
	'use strict';
	$('#'+id).highcharts({
		colors:['#FF9900','#8D4553','#CCCCCC','#F25C81','#0D9615'],
		chart: type,
		title: title,
		tooltip:tooltip,
		subtitle: {
			text: null
		},
		plotOptions: plotOptions,
		legend:legend,
		series: data
	});
}
function buildOverAllPrintMediaDetails(result,typeval,divId,departmentType,departmentId){
	var str='';
	var categoryNamesArr=['Total','Positive','Negative'];
	var dataArr=[];
	var totalArr = [];
	var positivePercArr = [];
	var negativePercArr = [];
	var totalCount=0;
	var totalPosCount=0;
	var totalNegCount=0;
	var totalMainEdCount=0;
	var totalDistEdCount=0;
	var totalEdCount=0;
	var totalPosPerc=0;
	var totalNegperc=0;
	var overAllcategoryTime=0;
	var overAllcategoryPosTime=0;
	var overAllcategoryNegTime=0;
	var totalCountMainDist=0;
	var totalPosCountMain=0;
	var totalNegCountMain=0;
	for(var i in result){
	    totalCountMainDist=totalCountMainDist+result[i].positiveCountMain+result[i].negativCountMain;
		if(result[i].organization == "Main")
		{
			totalPosCountMain=totalPosCountMain+result[i].positiveCountMain+result[i].negativCountMain;
		}else if(result[i].organization == "District"){
			totalNegCountMain=totalNegCountMain+result[i].positiveCountMain+result[i].negativCountMain;
		}
		
		
		
		totalCount = totalCount+result[i].count+result[i].grpCount;
		totalPosCount = totalPosCount+result[i].positiveCountMain+result[i].positiveCountGrpMain;
		totalNegCount = totalNegCount+result[i].negativCountMain+result[i].negativCountGrpMain;
		overAllcategoryTime=result[i].positiveIsPrimeCoveredTime;
		overAllcategoryPosTime=result[i].totalIsPrimePositiveCoveredTime;
		overAllcategoryNegTime=result[i].totalIsPrimeNegativeCoveredTime
		if(result[i].organization == "Main")
		{
		totalMainEdCount=result[i].positiveCountMain+result[i].negativCountMain;
		}
		else if(result[i].organization == "District")
		{ 
		totalDistEdCount=result[i].positiveCountMain+result[i].negativCountMain;
		}
		
	}
	totalEdCount=totalMainEdCount+totalDistEdCount;
	
	totalPosPerc = (totalPosCountMain/totalCountMainDist*100).toFixed(2);
	totalNegperc = (totalNegCountMain/totalCountMainDist*100).toFixed(2);
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-5 border_right_yash">';
		if(departmentType == "overAll"){
			if(typeval == "PrintMedia"){
				str+='<div id="overAll'+typeval+'GraphDivId'+departmentId+'" style="height:330px;"></div>';
			}
		}else if(departmentType == "department"){
			if(typeval == "PrintMediadepartment"+departmentId+""){
				str+='<div id="overAll'+typeval+'GraphDivId'+departmentId+'" style="height:310px;"></div>';
			}
		}else{
			str+='<div id="overAll'+typeval+'GraphDivId'+departmentId+'" style="height:250px;"></div>';
		}
			
		str+='</div>';
		str+='<div class="col-sm-7">';
			str+='<div class="row">';
				for(var i in result){
					if(i==0){
						str+='<div class="col-sm-6 border_right_yash">';
					}else{
						str+='<div class="col-sm-6">';
					}
						str+='<div id="edition'+typeval+''+i+''+departmentId+'"></div>';
						str+='<div id="overAll'+typeval+'MainDistEditionDivId'+i+''+departmentId+'" style="height:200px;"></div>';
						str+='<div class="row">';
							str+='<div class="col-sm-6">';
								str+='<h5 class="font_weight">Positive</h5>';
								if(departmentType == "overAllEle"){
									 if(typeval == "ElectronicMedia"){
										 if(result[i].positiveCountMain != null && result[i].positiveCountMain >0){
											 str+='<h5 class="font_weight m_top5" style="font-size:12px;"><a class="printOverAllCls" attr_editionId="0" attr_benefitId="1" style="cursor:pointer;" attr_deptId="'+departmentId+'" attr_categoryId="'+result[i].organizationId+'" attr_type="electronic">'+result[i].positiveCountMain+'</a>(<span style="color:#63C563">'+result[i].positivePerc.toFixed(0)+'%</span>)</h5>';
										//str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].isPrimedescription+'</h5>';
										
											str+='<h5 class="font_weight m_top5" style="font-size:11px;"><span data-placement="top" class="emToolTipCls2"  style="cursor: pointer;font-size: 11px;" title=" Prime&nbsp;Time:'+result[i].overalIsPrimedescription+'\nNon&nbsp;Prime&nbsp;Time:'+result[i].overalIsNotPrimedescription+'">'+result[i].isPrimedescription+'</span></h5>';
										 }else{
											 str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].positiveCountMain+'(<span style="color:#63C563">'+result[i].positivePerc.toFixed(0)+'%</span>)</h5>';
											str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].isPrimedescription+'</h5>';
										 }
									 }
								}else if(departmentType == "overAll"){
									if(typeval == "PrintMedia"){
										str+='<h5 class="font_weight m_top5" style="font-size:12px;">';
											if(result[i].positiveCountMain !=null && result[i].positiveCountMain >0){
												str+='<span class="printOverAllCls" attr_editionId="'+result[i].organizationId+'" attr_benefitId="2" style="cursor:pointer;color:royalblue" attr_deptId="0" attr_type="print" title="Individual Articles('+result[i].mainPositiveArticlePerc+')"  attr_categoryId="0" attr_group_type="individualArticles">'+result[i].positiveCountMain+'</span>'; 
											}else{
												str+='<span>'+result[i].positiveCountMain+'</span>'; 
											}
											str+=' - ';
											if(result[i].positiveCountGrpMain != null && result[i].positiveCountGrpMain !=""){
												str+='<span class="printOverAllCls" attr_editionId="'+result[i].organizationId+'" attr_benefitId="2" style="cursor:pointer;color:royalblue" attr_deptId="'+departmentId+'" attr_type="print" attr_categoryId="'+result[i].organizationId+'" title="Grouped Articles(('+result[i].mainPositiveArticleGrpPerc+')" attr_group_type="groupedArticles">'+result[i].positiveCountGrpMain+'</span>';
											}else{
												str+='<span>'+result[i].positiveCountGrpMain+'</span>';
											}
											
											str+='</h5>';
											str+='<h5><span style="color:#EB2F2F">('+result[i].positivePerc.toFixed(0)+'%)</span></h5>';
										
									}
								}else if(departmentType == "departmentEle"){
									if(typeval == "ElectronicMediadepartment"+departmentId+""){
										if(result[i].positiveCountMain !=null && result[i].positiveCountMain >0){
											str+='<h5 class="font_weight m_top5" style="font-size:12px;"><a class="printOverAllCls" attr_editionId="0" attr_benefitId="1" style="cursor:pointer;" attr_deptId="'+departmentId+'" attr_type="electronic" attr_categoryId="'+result[i].organizationId+'">'+result[i].positiveCountMain+'</a>(<span style="color:#63C563">'+result[i].positivePerc.toFixed(0)+'%</span>)</h5>';
											//str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].isPrimedescription+'</h5>';
											str+='<h5 class="font_weight m_top5" style="font-size:11px;"><span data-placement="top" class="emToolTipCls2"  style="cursor: pointer;font-size: 11px;" title="Prime&nbsp;Time:'+result[i].overalIsPrimedescription+'\n Non&nbsp;Prime&nbsp;Time:'+result[i].overalIsNotPrimedescription+'">'+result[i].isPrimedescription+'</span></h5>';
										}else{
											str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].positiveCountMain+'(<span style="color:#63C563">'+result[i].positivePerc.toFixed(0)+'%</span>)</h5>';
											//str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].isPrimedescription+'</h5>';
										}
										
									}
								}else if(departmentType == "department"){
									if(typeval == "PrintMediadepartment"+departmentId+""){
										str+='<h5 class="font_weight m_top5" style="font-size:12px;">';
											if(result[i].positiveCountMain !=null && result[i].positiveCountMain >0){
												str+='<span class="printOverAllCls" attr_editionId="'+result[i].organizationId+'" attr_benefitId="2" style="cursor:pointer;color:royalblue" attr_deptId="0" attr_type="print" title="Individual Articles('+result[i].mainPositiveArticlePerc+')"  attr_categoryId="0" attr_group_type="individualArticles">'+result[i].positiveCountMain+'</span>'; 
											}else{
												str+='<span>'+result[i].positiveCountMain+'</span>'; 
											}
											str+=' - ';
											if(result[i].positiveCountGrpMain != null && result[i].positiveCountGrpMain !=""){
												str+='<span class="printOverAllCls" attr_editionId="'+result[i].organizationId+'" attr_benefitId="2" style="cursor:pointer;color:royalblue" attr_deptId="'+departmentId+'" attr_type="print" attr_categoryId="'+result[i].organizationId+'" title="Grouped Articles(('+result[i].mainPositiveArticleGrpPerc+')" attr_group_type="groupedArticles">'+result[i].positiveCountGrpMain+'</span>';
											}else{
												str+='<span>'+result[i].positiveCountGrpMain+'</span>';
											}
											str+='</h5>';
											str+='<h5><span style="color:#EB2F2F">('+result[i].positivePerc.toFixed(0)+'%)</span></h5>';
									}
								} 
								
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<h5 class="font_weight">Negative</h5>';
								if(departmentType == "overAllEle"){
									 if(typeval == "ElectronicMedia"){
										 if(result[i].negativCountMain !=null && result[i].negativCountMain>0){
											 str+='<h5 class="font_weight m_top5" style="font-size:12px;"><a class="printOverAllCls" attr_editionId="'+result[i].organizationId+'" attr_benefitId="2" style="cursor:pointer;" attr_deptId="'+departmentId+'" attr_type="electronic" attr_categoryId="'+result[i].organizationId+'">'+result[i].negativCountMain+'</a>(<span style="color:#EB2F2F">'+result[i].negativePerc.toFixed(0)+'%</span>)</h5>';
											//str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].isNotPrimedescription+'</h5
											str+='<h5 class="font_weight m_top5" style="font-size:11px;"><span data-placement="top" class="emToolTipCls2"  style="cursor: pointer;font-size: 11px;" title="Prime&nbsp;Time:'+result[i].negativeIsPrimeCoveredTime+'\n Non&nbsp;Prime&nbsp;Time:'+result[i].negativeIsNotPrimeCoveredTime+'">'+result[i].isNotPrimedescription+'</span></h5>';
											}else{
												 str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].negativCountMain+'(<span style="color:#EB2F2F">'+result[i].negativePerc.toFixed(0)+'%</span>)</h5>';
												//str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].isNotPrimedescription+'</h5>';
											 }
										 
									}
								}else if(departmentType == "overAll"){
									if(typeval == "PrintMedia"){
										str+='<h5 class="font_weight m_top5" style="font-size:12px;">';
											if(result[i].negativCountMain !=null && result[i].negativCountMain >0){
												str+='<span class="printOverAllCls" attr_editionId="'+result[i].organizationId+'" attr_benefitId="2" style="cursor:pointer;color:royalblue" attr_deptId="0" attr_type="print" title="Individual Articles('+result[i].mainNegativeArticlePerc+')"  attr_categoryId="0" attr_group_type="individualArticles">'+result[i].negativCountMain+'</span>'; 
											}else{
												str+='<span>'+result[i].negativCountMain+'</span>'; 
											}
											str+=' - ';
											if(result[i].negativCountGrpMain != null && result[i].negativCountGrpMain !=""){
												str+='<span class="printOverAllCls" attr_editionId="'+result[i].organizationId+'" attr_benefitId="2" style="cursor:pointer;color:royalblue" attr_deptId="'+departmentId+'" attr_type="print" attr_categoryId="'+result[i].organizationId+'" title="Grouped Articles(('+result[i].mainNegativeArticleGrpPerc+')" attr_group_type="groupedArticles">'+result[i].negativCountGrpMain+'</span>';
											}else{
												str+='<span>'+result[i].negativCountGrpMain+'</span>';
											}
											str+='</h5>';
											str+='<h5><span style="color:#EB2F2F">('+result[i].negativePerc.toFixed(0)+'%)</span></h5>';
									}
								}else if(departmentType == "departmentEle"){
									if(typeval == "ElectronicMediadepartment"+departmentId+""){
										if(result[i].negativCountMain !=null && result[i].negativCountMain>0){
											str+='<h5 class="font_weight m_top5" style="font-size:12px;"><a class="printOverAllCls" attr_editionId="'+result[i].organizationId+'" attr_benefitId="2" style="cursor:pointer;" attr_deptId="'+departmentId+'" attr_type="electronic" attr_categoryId="'+result[i].organizationId+'">'+result[i].negativCountMain+'</a>(<span style="color:#EB2F2F">'+result[i].negativePerc.toFixed(0)+'%</span>)</h5>';
											//str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].isNotPrimedescription+'</h5>';
											str+='<h5 class="font_weight m_top5" style="font-size:11px;"><span data-placement="top" class="emToolTipCls2"  style="cursor: pointer;font-size: 11px;" title="Prime&nbsp;Time:'+result[i].negativeIsPrimeCoveredTime+'\n Non&nbsp;Prime&nbsp;Time:'+result[i].negativeIsNotPrimeCoveredTime+'">'+result[i].isNotPrimedescription+'</span></h5>';
											}else{
												str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].negativCountMain+'(<span style="color:#EB2F2F">'+result[i].negativePerc.toFixed(0)+'%</span>)</h5>';
												//str+='<h5 class="font_weight m_top5" style="font-size:12px;">'+result[i].isNotPrimedescription+'</h5>';
											}
										
									}
								}else if(departmentType == "department"){
									if(typeval == "PrintMediadepartment"+departmentId+""){
										str+='<h5 class="font_weight m_top5" style="font-size:12px;">';
											if(result[i].negativCountMain !=null && result[i].negativCountMain >0){
												str+='<span class="printOverAllCls" attr_editionId="'+result[i].organizationId+'" attr_benefitId="2" style="cursor:pointer;color:royalblue" attr_deptId="0" attr_type="print" title="Individual Articles('+result[i].mainNegativeArticlePerc+')"  attr_categoryId="0" attr_group_type="individualArticles">'+result[i].negativCountMain+'</span>'; 
											}else{
												str+='<span>'+result[i].negativCountMain+'</span>'; 
											}
											str+=' - ';
											if(result[i].negativCountGrpMain != null && result[i].negativCountGrpMain !=""){
												str+='<span class="printOverAllCls" attr_editionId="'+result[i].organizationId+'" attr_benefitId="2" style="cursor:pointer;color:royalblue" attr_deptId="'+departmentId+'" attr_type="print" attr_categoryId="'+result[i].organizationId+'" title="Grouped Articles(('+result[i].mainNegativeArticleGrpPerc+')" attr_group_type="groupedArticles">'+result[i].negativCountGrpMain+'</span>';
											}else{
												str+='<span>'+result[i].negativCountGrpMain+'</span>';
											}
											str+='</h5>';
											str+='<h5><span style="color:#EB2F2F">('+result[i].negativePerc.toFixed(0)+'%)</span></h5>';
									}
								} 
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#"+divId).html(str);
	$('.emToolTipCls2').tooltip();
	totalArr.push(parseInt(totalCount));
	positivePercArr.push(parseInt(totalPosCount));
	negativePercArr.push(parseInt(totalNegCount));
	dataArr.push(totalArr);
	dataArr.push(positivePercArr);
	dataArr.push(negativePercArr);
	var colors = ['#343B54','#63C563','#EB2F2F']
	var id = 'overAll'+typeval+'GraphDivId'+departmentId+'';
	var type = {
		type: 'column',
		backgroundColor:'transparent'
	};
	var title = {
		text: '',
		
	};

	var legend = {
		enabled: false
	};
	var yAxis = {
		min: 0,
		gridLineWidth: 0,
		minorGridLineWidth: 0,
		title: {
			text: null
		},
		
	};
	var xAxis = {
		min: 0,
		gridLineWidth: 0,
		minorGridLineWidth: 0,
		categories: categoryNamesArr
	};
	
	var plotOptions ={ column: {
			colorByPoint: true
		}};
	var tooltip = {
		useHTML:true,
		formatter: function () {
			return '<b>' + this.x + '</b><br/>' +
				this.y+'';
		}
	};

	var data = [{
		name: '',
		data: dataArr,

		dataLabels: {
			useHTML:true,
			enabled: true,
			color: '#000',
			align: 'center',
			y: 7,
			formatter: function() {
					return '<span>'+this.y+'</span>';
			} 
		}
	}];
	highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
	for(var i in result){
		
		var postiveCountPerc = result[i].positivePerc;
		var negativCountPerc = result[i].negativePerc;
		var totalCount=result[i].positiveCountMain+result[i].negativCountMain+result[i].positiveCountGrpMain+result[i].negativCountGrpMain;
		var headingCountPrint='';
			if(departmentType == "overAllEle"){
				 if(typeval == "ElectronicMedia"){
					if(result[i].organization == "News Bulletin"){
					if(totalCount != null && totalCount >0){	//headingCountPrint+='<h5><span>'+result[i].organization+'</span><br><b>'+totalCount+'('+totalPosPerc+'%)<br>('+result[i].positiveIsPrimeCoveredTime+')</b></h5>';
						headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;"><span>'+result[i].organization+'</span><br><b>'+totalCount+'('+result[i].tdpPerc.toFixed(1)+'%) <br/><span data-placement="top" class="emToolTipCls2"  style="cursor: pointer;font-size: 11px;" title="Prime&nbsp;Time:'+result[i].totalIsPrimePositiveCoveredTime+'\n Non&nbsp;Prime&nbsp;Time:'+result[i].totalIsPrimeNegativeCoveredTime+'">'+result[i].positiveIsPrimeCoveredTime+'</span></b></h5>';
					}else{
						headingCountPrint+='<h5><span>'+result[i].organization+'</span><br><b>'+totalCount+'('+totalPosPerc+'%)<br>('+result[i].positiveIsPrimeCoveredTime+')</b></h5>';
					}
					}else if(result[i].organization == "News Program"){
						if(totalCount != null && totalCount >0){	
						//headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;"><span>'+result[i].organization+'</span><br><b>'+totalCount+' ('+result[i].tdpPerc.toFixed(1)+'%) ('+result[i].positiveIsPrimeCoveredTime+')</b></h5>';
						headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;"><span>'+result[i].organization+'</span><br><b>'+totalCount+'('+result[i].tdpPerc.toFixed(1)+'%) <br/><span data-placement="top" class="emToolTipCls2"  style="cursor: pointer;font-size: 11px;" title="Prime&nbsp;Time:'+result[i].totalIsPrimePositiveCoveredTime+'\n Non&nbsp;Prime&nbsp;Time:'+result[i].totalIsPrimeNegativeCoveredTime+'">'+result[i].positiveIsPrimeCoveredTime+'</span></b></h5>';
						}else{
							headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;"><span>'+result[i].organization+'</span><br><b>'+totalCount+' ('+result[i].tdpPerc.toFixed(1)+'%) ('+result[i].positiveIsPrimeCoveredTime+')</b></h5>';
						}
					}
				}
			}else if(departmentType == "overAll"){
				if(typeval == "PrintMedia"){
					if(result[i].organization == "Main"){
						headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;">Main Edition<br/><span class="font_weight m_top5">'+totalCount+' ('+totalPosPerc+'%)</span></h5>';
					}else if(result[i].organization == "District"){
						headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;">DistrictEdition<br/><span class="font_weight m_top5">'+totalCount+' ('+totalNegperc+'%)</span></h5>';
					}
				}
			}
			
			else if(departmentType == "departmentEle"){
				if(typeval == "ElectronicMediadepartment"+departmentId+""){
					if(result[i].organization == "News Bulletin"){
						//headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;"><span>'+result[i].organization+'</span><br><b>'+totalCount+' ('+result[i].tdpPerc.toFixed(1)+'%) ('+result[i].positiveIsNotPrimeCoveredTime+')</b></h5>';
						headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;"><span>'+result[i].organization+'</span><br><b>'+totalCount+'('+result[i].tdpPerc.toFixed(1)+'%) <br/><span data-placement="top" class="emToolTipCls2"  style="cursor: pointer;font-size: 11px;" title=" Prime&nbsp;Time:'+result[i].totalIsPrimeNegativeCoveredTime+'\n Non&nbsp;Prime&nbsp;Time:'+result[i].totalIsNotPrimeNegativeCoveredTime+'">'+result[i].positiveIsNotPrimeCoveredTime+'</span></b></h5>';
					}else if(result[i].organization == "News Program"){
						//headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;"><span>'+result[i].organization+'</span><br><b>'+totalCount+' ('+result[i].tdpPerc.toFixed(1)+'%) ('+result[i].positiveIsNotPrimeCoveredTime+')</b></h5>';
						headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;"><span>'+result[i].organization+'</span><br><b>'+totalCount+'('+result[i].tdpPerc.toFixed(1)+'%) <br/><span data-placement="top" class="emToolTipCls2"  style="cursor: pointer;font-size: 11px;" title=" Prime&nbsp;Time:'+result[i].totalIsPrimeNegativeCoveredTime+'\n Non&nbsp;Prime&nbsp;Time:'+result[i].totalIsNotPrimeNegativeCoveredTime+'">'+result[i].positiveIsNotPrimeCoveredTime+'</span></b></h5>';
					}
				}
			}else if(departmentType == "department"){
				if(typeval == "PrintMediadepartment"+departmentId+""){
					if(result[i].organization == "Main"){
						headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;">Main Edition<br/><span class="font_weight m_top5">'+totalCount+' ('+totalPosPerc+'%)</span></h5>';
					}else if(result[i].organization == "District"){
						headingCountPrint+='<h5 style="text-align: center;line-height: 20px;font-weight: bold;">DistrictEdition<br/><span class="font_weight m_top5">'+totalCount+' ('+totalNegperc+'%)</span></h5>';
					}
				}
			} 
		$("#edition"+typeval+i+departmentId).html(headingCountPrint);
		var id = 'overAll'+typeval+'MainDistEditionDivId'+i+departmentId;
		var type = {
			type: 'pie',
			backgroundColor:'transparent',
			options3d: {
				enabled: true,
				alpha: 25
			}
		};
		var title = {
			text: '',
			align:'center',
			 style: {
				 color: '#000',
				 font: '12px "Lato", sans-serif'
			  }
		};
		
		
		
		var tooltip = {
			useHTML: true,
			backgroundColor: '#FCFFC5', 
			formatter: function() {
				var cnt = this.point.count;
				return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+(this.y)+"%)</b>";
			}  
		}; 
		var plotOptions ={
			pie: {
				innerSize: 60,
				depth: 40,
				dataLabels: {
					enabled: false,
					formatter: function() {
						return (this.y) + ' %';
					},
					distance: -10,
					color:'#333'
				},
				showInLegend: false
			},
		};
		var legend={enabled: false};
		var data = [{
			name: '',
			data: [
				{
				  name: 'Positive',
				  y: postiveCountPerc,
				  color:"#63C563"
				},
				{
				  name: 'Negative',
				  y: negativCountPerc,
				  color:"#EB2F2F"
				}
			]
		}];
		highchartsPieChart(id,type,data,plotOptions,title,tooltip,legend);
		$('.emToolTipCls2').tooltip();
	}			
	
	
}
function buildOverAllDistrictWiseDetails(result,divId,deptId,type){
	var districtWiseNegativeCountArray=[];
	var districtWisePositiveCountArray=[];
	var districtNamesArray=[];
	for(var i in result){
		districtNamesArray.push(result[i].organization)
		districtWiseNegativeCountArray.push({y:result[i].negativePerc,"extra":result[i].negativCountMain+"-"+result[i].organizationId+"-"+deptId+"-2","countVal":result[i].negativCountMain})
		districtWisePositiveCountArray.push({y:result[i].positivePerc,"extra":result[i].positiveCountMain+"-"+result[i].organizationId+"-"+deptId+"-1","countVal":result[i].positiveCountMain})
	}
	
	$('#'+divId).highcharts({
		colors: ['#64C664','#D33E39'],
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
		xAxis: {
			 min: 0,
				 gridLineWidth: 0,
				 minorGridLineWidth: 0,
				categories: districtNamesArray,
			labels: {
					rotation: -45,
					style: {
						fontSize: '12px',
						fontFamily: 'Verdana, sans-serif'
					}
				}
		},
		yAxis: {
			min: 0,
				   gridLineWidth: 0,
					minorGridLineWidth: 0,
			title: {
				text: ''
			},
			stackLabels: {
				enabled: false,
				style: {
					fontWeight: 'bold',
					color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
				}
			}
		},
		legend: {
			enabled: true,
			/* //align: 'right',
			x: -40,
			y: 30,
			verticalAlign: 'top',
			//y: -32,
			floating: true, */
			backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
			borderColor: '#CCC',
			borderWidth: 1,
			shadow: false
		},
		tooltip: {
			formatter: function () {
				var s = '<b>' + this.x + '</b>';

				$.each(this.points, function () {
					s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
						Highcharts.numberFormat(this.y,1)+'%'+' - ' +
						(this.point.countVal);
				});

				return s;
			},
			shared: true
		},
		
		plotOptions: {
			pointPadding: 0.2,
			borderWidth: 2,
			groupPadding: 0.2,
			column: {
				stacking: 'percent',
				dataLabels: {
					enabled: true,
					 formatter: function() {
						if (this.y === 0) {
							return null;
						} else {
							return Highcharts.numberFormat(this.y,0) +'%';
						}
					}
				  
				}
			},
			series: {
				cursor: 'pointer',
				point: {
					events: {
						click: function () {
							var value = (this.extra).split("-");
							var districtId = value[1];
							var departmentId = value[2];;
							var benefitId = value[3];
							if(type == "overAllPrint"){
								getArtilcesForDistrict(districtId,departmentId,benefitId);
							}else if(type == "overAllEM"){
								//overAllElecDistrict
								getArtilcesForDistrictFOrEMN(districtId,departmentId,benefitId);
							}else if(type == "department"){
								getArtilcesForDistrict(districtId,departmentId,benefitId);
							}else if(type == "departmentEle"){
								getArtilcesForDistrictFOrEMN(districtId,departmentId,benefitId);
							}
							
							
						}
					}
				}
			}
		},
		series: [{
			name: 'Positive',
			data: districtWisePositiveCountArray
		}, {
			name: 'Negative',
			data: districtWiseNegativeCountArray
		}]
	});
}
function buildDepartmentWiSeBlockDetails(result,type,divId,typeVal){
	var collapse='';
	
	for(var i in result){
		if(globalDeptId != "0"){
			if(result[i].organizationId == globalDeptId){
				collapse+='<div class="row m_top20">';
					collapse+='<div class="col-sm-12">';
					if(type == "PrintMediadepartment"){
						collapse+='<div class="row pull-right" style="margin:5px;">';
							collapse+='<button type="button" class="btn btn-success pdfGenerateCls" attr_pdf_type="positive" attr_pdf_dept_id="'+result[i].organizationId+'">Positive</button> ';
							collapse+='<button type="button" class="btn btn-danger pdfGenerateCls" attr_pdf_type="negative" attr_pdf_dept_id="'+result[i].organizationId+'">Negative</button>';
						collapse+='</div>';
					}
					collapse+='<div class="white_block">';
								collapse+='<h4 class="panel-title text-capital font_weight">'+result[i].organization+'</h4>';
									collapse+='<div class="row m_top10 departmentWiseTotalCls" id="departmentDivId'+result[i].organizationId+'">';
										collapse+='<div class="col-sm-6">';
											collapse+='<div class="pad_light_yash_bg border_yash">';
												collapse+='<h4>OverAll Details</h4>';
												collapse+='<div id="'+divId+''+result[i].organizationId+'" style="width:100%"></div>';
											collapse+='</div>';
										collapse+='</div>';
										
										for(var j in result[i].coreDashBoardVOList2){
													if(result[i].coreDashBoardVOList2[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList2[j].coreDashBoardVOList.length>0){
													collapse+='<div class="col-sm-6">';
														collapse+='<div class="pad_light_yash_bg border_yash" style="height:362px;>';
															collapse+='<h5 class="font_weight text-capital">Analysis of Action Immediately</h5>';
															collapse+='<h5 class="m_top10 font_weight">Total Count - <a><span id="totalCountAnalysisId'+result[i].organizationId+'" class="propertiesCls" attr_deptId="'+result[i].organizationId+'" attr_propertyId="0" attr_type="'+type+'" style="cursor:pointer;"></span></a></h5>';
															collapse+='<div class="row">';
																collapse+='<div class="col-sm-6">';
																	collapse+='<div id="actionWiseAnalysis'+result[i].organizationId+'" class="" style="height:180px;width:100%;margin-top:20px;"></div>';
																collapse+='</div>';
																collapse+='<div class="col-sm-6">';
																	collapse+='<ul class="list-inline" style="margin-top:50px;">';
																		collapse+='<div id="problemWise'+result[i].organizationId+'"></div>';
																	collapse+='</ul>';
																collapse+='</div>';
															collapse+='</div>';
														collapse+='</div>';
													collapse+='</div>';
													
												}
											}
											
										
										
									collapse+='</div>';
									
									collapse+='<div class="row m_top20">';
										for(var j in result[i].coreDashBoardVOList3){
											if(result[i].coreDashBoardVOList3[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList3[j].coreDashBoardVOList.length>0){
												collapse+='<div class="col-sm-3">';
													collapse+='<h5 class="font_weight text-capital">State wise Overview</h5>';
														collapse+='<div id="stateLevelMedia'+result[i].organizationId+'" style="height:330px;width:100%"></div>';
													collapse+='</div>';
												
											}
										}
										for(var j in result[i].coreDashBoardVOList1){
											if(result[i].coreDashBoardVOList1[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList1[j].coreDashBoardVOList.length>0){
										collapse+='<div class="col-sm-9">';
											collapse+='<h5 class="font_weight text-capital">District wise Overview</h5>';
											collapse+='<div id="districtWise'+result[i].organizationId+'" class="m_top10" style="height:330px;width:100%"></div>';
										collapse+='</div>';
											}
										}///
									collapse+='</div>';
							collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			}
		}else{
			collapse+='<div class="row m_top20">';
			collapse+='<div class="col-sm-12">';
				if(type == "PrintMediadepartment"){
						collapse+='<div class="row pull-right" style="margin:5px;">';
							collapse+='<button type="button" class="btn btn-success pdfGenerateCls" attr_pdf_type="positive" attr_pdf_dept_id="'+result[i].organizationId+'">Positive</button> ';
							collapse+='<button type="button" class="btn btn-danger pdfGenerateCls" attr_pdf_type="negative" attr_pdf_dept_id="'+result[i].organizationId+'">Negative</button>';
						collapse+='</div>';
					}
				collapse+='<div class="white_block">';
				collapse+='<h4 class="panel-title text-capital font_weight">'+result[i].organization+'</h4>';
							collapse+='<div class="row m_top10 departmentWiseTotalCls" id="departmentDivId'+result[i].organizationId+'">';
								collapse+='<div class="col-sm-6">';
									collapse+='<div class="pad_light_yash_bg border_yash">';
										collapse+='<h4>OverAll Details</h4>';
										collapse+='<div id="'+divId+''+result[i].organizationId+'" style="width:100%"></div>';
									collapse+='</div>';
								collapse+='</div>';
								
								for(var j in result[i].coreDashBoardVOList2){
											if(result[i].coreDashBoardVOList2[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList2[j].coreDashBoardVOList.length>0){
											collapse+='<div class="col-sm-6">';
												collapse+='<div class="pad_light_yash_bg border_yash" style="height:362px;>';
													collapse+='<h5 class="font_weight text-capital">Analysis of Action Immediately</h5>';
													collapse+='<h5 class="m_top10 font_weight">Total Count - <a><span id="totalCountAnalysisId'+result[i].organizationId+'" class="propertiesCls" attr_deptId="'+result[i].organizationId+'" attr_propertyId="0" attr_type="'+type+'" style="cursor:pointer;"></span></a></h5>';
													collapse+='<div class="row">';
														collapse+='<div class="col-sm-6">';
															collapse+='<div id="actionWiseAnalysis'+result[i].organizationId+'" class="" style="height:180px;width:100%;margin-top:20px;"></div>';
														collapse+='</div>';
														collapse+='<div class="col-sm-6">';
															collapse+='<ul class="list-inline" style="margin-top:50px;">';
																collapse+='<div id="problemWise'+result[i].organizationId+'"></div>';
															collapse+='</ul>';
														collapse+='</div>';
													collapse+='</div>';
												collapse+='</div>';
											collapse+='</div>';
											
										}
									}
									
								
								
							collapse+='</div>';
							
							collapse+='<div class="row m_top20">';
								for(var j in result[i].coreDashBoardVOList3){
									if(result[i].coreDashBoardVOList3[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList3[j].coreDashBoardVOList.length>0){
										collapse+='<div class="col-sm-3">';
											collapse+='<h5 class="font_weight text-capital">State wise Overview</h5>';
												collapse+='<div id="stateLevelMedia'+result[i].organizationId+'" style="height:330px;width:100%"></div>';
											collapse+='</div>';
										
									}
								}
								for(var j in result[i].coreDashBoardVOList1){
									if(result[i].coreDashBoardVOList1[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList1[j].coreDashBoardVOList.length>0){
								collapse+='<div class="col-sm-9">';
									collapse+='<h5 class="font_weight text-capital">District wise Overview</h5>';
									collapse+='<div id="districtWise'+result[i].organizationId+'" class="m_top10" style="height:330px;width:100%"></div>';
								collapse+='</div>';
							   }
							}
							collapse+='</div>';
					collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		}
		
		
		}
			
	$("#departmentWiseDetailsDivId").html(collapse);
	for(var i in result){
		for(var j in result[i].coreDashBoardVOList1){
			//DistrictCall
			buildOverAllDistrictWiseDetails(result[i].coreDashBoardVOList1[j].coreDashBoardVOList,'districtWise'+result[i].organizationId+'',result[i].organizationId,typeVal)
		}
		if(type =="PrintMediadepartment"){
			for(var j in result[i].coreDashBoardVOList){
			//printMediaCall
			buildOverAllPrintMediaDetails(result[i].coreDashBoardVOList[j].coreDashBoardVOList,''+type+''+result[i].organizationId+'',''+divId+''+result[i].organizationId+'',''+typeVal+'',result[i].organizationId)
			}
			
			for(var j in result[i].coreDashBoardVOList3){
			//ElectronicCall
				buildOverAllStateWiseDetails(result[i].coreDashBoardVOList3[j].coreDashBoardVOList,'stateLevelMedia'+result[i].organizationId+'',result[i].organizationId,type)
			}
			for(var j in result[i].coreDashBoardVOList2){
			//ActionWiseCall
				buildactionWiseAnalysisDetails(result[i].coreDashBoardVOList2[j].coreDashBoardVOList,"actionWiseAnalysis"+result[i].organizationId+"",result[i].organizationId,type)
			
			}
		}else if(type == "ElectronicMediadepartment"){
			for(var j in result[i].coreDashBoardVOList){
			//Electronic Media Call
				if(result[i].coreDashBoardVOList[j].tdpCount !=null && result[i].coreDashBoardVOList[j].tdpCount >0){
					buildOverAllPrintMediaDetails(result[i].coreDashBoardVOList[j].coreDashBoardVOList,''+type+''+result[i].organizationId+'',''+divId+''+result[i].organizationId+'',''+typeVal+'',result[i].organizationId)
				}else{
					$("#departmentDivId"+result[i].organizationId).html("No Data Available...");
				}
			}
			for(var j in result[i].coreDashBoardVOList3){
			//ElectronicCall
				buildOverAllStateWiseDetails(result[i].coreDashBoardVOList3[j].coreDashBoardVOList,'stateLevelMedia'+result[i].organizationId+'',result[i].organizationId,type)
			}
			for(var j in result[i].coreDashBoardVOList2){
			//ActionWiseCall
				buildactionWiseAnalysisDetails(result[i].coreDashBoardVOList2[j].coreDashBoardVOList,"actionWiseAnalysis"+result[i].organizationId+"",result[i].organizationId,type)
			
			}
		}
	}
}
function buildactionWiseAnalysisDetails(result,divId,departmentId,type){
	var withOutAmoneyArr=[];
	var below10LakhsArr=[];
	var above10Lakhs=[];
	var stateWIdeIssueArr=[];
	
	var mainArr = [];
	var str='';
	var totalCount=0;
	if(result !=null && result.length>0){
		for(var i in result){
			
			totalCount=totalCount+result[i].count;
			$("#totalCountAnalysisId"+departmentId).html(totalCount);
			var colorObj={"Without Money":"#E4D254","<10L":"#8D4653",">10L":"#F15C81","State Wide Issue":"#8085E9"}
			if(result[i].count !=null && result[i].count>0){
				str+='<li class="m_top10"><span class="square_csss" style="background-color:'+colorObj[result[i].organization]+'"></span>'+result[i].organization+'<span class="pull-right"><a class="propertiesCls" attr_propertyId="'+result[i].organizationId+'" attr_deptId="'+departmentId+'" attr_type="'+type+'" style="cursor:pointer;">'+result[i].count+'</a> - <span style="color:green">'+result[i].positivePerc+'%</span></span></li>';
			}else{
				str+='<li class="m_top10"><span class="square_csss" style="background-color:'+colorObj[result[i].organization]+'"></span>'+result[i].organization+'<span class="pull-right">'+result[i].count+' - <span style="color:green">'+result[i].positivePerc+'%</span></span></li>';
			}
			
			
			$("#problemWise"+departmentId).html(str);					
			var subArr = [];
			subArr={name:result[i].organization,y:result[i].positivePerc};
			mainArr.push(subArr);			
		}
	}
	
		$('#actionWiseAnalysis'+departmentId).highcharts({
			colors:['#E4D254','#8D4653','#F15C81','#8085E9'],
			chart: {
				type: 'pie',
				backgroundColor: 'transparent',
				options3d: {
					enabled: false,
					alpha: 45
				}
			},	
			title: {
				text: null
			},
			subtitle: {
				text: ''
			},
			tooltip :{
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+(this.y)+"%)</b>";
				}  
			},
			plotOptions: {	
				pie: {
					innerSize: 80,
					depth: 40,
					dataLabels: {
						enabled: false,
						formatter: function() {
							return (this.y) + ' %';
						},
						distance: -10,
						color:'#333'
					},
					showInLegend: false
				},
			},
			legend:{
				enabled:false
			},
			series:[{
				name : 'Count',
				colorByPoint: true,
				data: mainArr
			}]
		});
}
function buildOverAllStateWiseDetails(result,divId,deptId,typeVal){
	var districtWiseNegativeCountArray=[];
	var districtWisePositiveCountArray=[];
	var districtNamesArray=[];
	for(var i in result){
		districtNamesArray.push(result[i].organization)
		districtWiseNegativeCountArray.push({y:result[i].negativePerc,"extra":result[i].negativCountMain+"-"+result[i].organizationId+"-"+deptId+"-2","countVal":result[i].negativCountMain})
		districtWisePositiveCountArray.push({y:result[i].positivePerc,"extra":result[i].positiveCountMain+"-"+result[i].organizationId+"-"+deptId+"-1","countVal":result[i].positiveCountMain})
	}
	
	$('#'+divId).highcharts({
		colors: ['#64C664','#D33E39'],
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
		xAxis: {
			 min: 0,
				 gridLineWidth: 0,
				 minorGridLineWidth: 0,
				categories: districtNamesArray,
			labels: {
					rotation: -45,
					style: {
						fontSize: '12px',
						fontFamily: 'Verdana, sans-serif'
					}
				}
		},
		yAxis: {
			min: 0,
				   gridLineWidth: 0,
					minorGridLineWidth: 0,
			title: {
				text: ''
			},
			stackLabels: {
				enabled: false,
				style: {
					fontWeight: 'bold',
					color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
				}
			}
		},
		legend: {
			enabled: true,
			/* //align: 'right',
			x: -40,
			y: 30,
			verticalAlign: 'top',
			//y: -32,
			floating: true, */
			backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
			borderColor: '#CCC',
			borderWidth: 1,
			shadow: false
		},
		tooltip: {
			formatter: function () {
				var s = '<b>' + this.x + '</b>';

				$.each(this.points, function () {
					s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
						Highcharts.numberFormat(this.y,0)+'%'+' - ' +
						(this.point.countVal);
				});

				return s;
			},
			shared: true
		},
		
		plotOptions: {
			pointPadding: 0.2,
			borderWidth: 2,
			groupPadding: 0.2,
			column: {
				stacking: 'percent',
				dataLabels: {
					enabled: true,
					 formatter: function() {
						if (this.y === 0) {
							return null;
						} else {
							return Highcharts.numberFormat(this.y,1) +'%';
						}
					}
				  
				}
			},
			series: {
				cursor: 'pointer',
				point: {
					events: {
						click: function () {
							var value = (this.extra).split("-");
							var stateId = value[1];
							var departmentId = value[2];
							var benefitId = value[3];
							if(typeVal =="PrintMediadepartment"){
								getArtilcesForStateForPM(stateId,departmentId,benefitId);
							}else if(typeVal =="ElectronicMediadepartment"){
								getArtilcesForStateForEMN(stateId,departmentId,benefitId);
							}
						}
					}
				}
			}
		},
		series: [{
			name: 'Positive',
			data: districtWisePositiveCountArray
		}, {
			name: 'Negative',
			data: districtWiseNegativeCountArray
		}]
	});
}
$(document).on("click",".printOverAllCls",function(){
	var deptId =$(this).attr("attr_deptId");
	var editionId =$(this).attr("attr_editionId");
	var benefitId =$(this).attr("attr_benefitId");
	var type =$(this).attr("attr_type");
	var categoryId =$(this).attr("attr_categoryId");
	var groupType= $(this).attr("attr_group_type");
	if(type =="print"){
		window.open('showCnpArticles.action?organizationId='+deptId+'&benefitId='+benefitId+'&editionId='+editionId+'&sdat='+glStartDate+'&edat='+glEndDate+'&temp='+groupType+'&type=print&&orgType=Y&stIdx=0&edIdx=6&callFrom=newsArtclesInPrrws');
	}else if(type == "electronic"){
		window.open('electronicMediaBulletinsCD.action?organizationId='+deptId+'&benefitId='+benefitId+'&categoryId='+categoryId+'&sdat='+glStartDate+'&edat='+glEndDate+'&type=electronic&&orgType=&stIdx=0&edIdx=6&callFrom=electrincMediaFOrPrrws');
	}
});
$(document).on("click",".propertiesCls",function(){
	var deptId =$(this).attr("attr_deptId");
	var type =$(this).attr("attr_type");
	var propertyId =$(this).attr("attr_propertyId");
	if(type =="PrintMediadepartment"){
		window.open('showCnpArticles.action?organizationId='+deptId+'&benefitId=0&editionId='+propertyId+'&sdat='+glStartDate+'&edat='+glEndDate+'&type=actionData&&orgType=Y&stIdx=0&edIdx=6&callFrom=propForPrrws');
	}else if(type =="ElectronicMediadepartment"){
		window.open('electronicMediaBulletinsCD.action?organizationId='+deptId+'&benefitId=0&categoryId='+propertyId+'&sdat='+glStartDate+'&edat='+glEndDate+'&type=districtdata&&orgType=Y&stIdx=0&edIdx=6&callFrom=propertiesData');
	}
});
function getArtilcesForDistrict(districtId,deptId,benefitId){
	window.open('showCnpArticles.action?organizationId='+deptId+'&benefitId='+benefitId+'&editionId='+districtId+'&sdat='+glStartDate+'&edat='+glEndDate+'&type=districtdata&&orgType=Y&stIdx=0&edIdx=6&callFrom=propForPrrws');
}
function getArtilcesForStateForPM(stateId,deptId,benefitId){
	window.open('showCnpArticles.action?organizationId='+deptId+'&benefitId='+benefitId+'&editionId='+stateId+'&sdat='+glStartDate+'&edat='+glEndDate+'&type=stateData&&orgType=Y&stIdx=0&edIdx=6&callFrom=propForPrrws');
}
function getArtilcesForStateForEMN(stateId,deptId,benefitId){
	window.open('electronicMediaBulletinsCD.action?organizationId='+deptId+'&benefitId='+benefitId+'&categoryId='+stateId+'&sdat='+glStartDate+'&edat='+glEndDate+'&type=electronic&&orgType=&stIdx=0&edIdx=6&callFrom=stateData');
}
function getArtilcesForDistrictFOrEMN(districtId,deptId,benefitId){
	window.open('electronicMediaBulletinsCD.action?organizationId='+deptId+'&benefitId='+benefitId+'&categoryId='+districtId+'&sdat='+glStartDate+'&edat='+glEndDate+'&type=districtdata&&orgType=Y&stIdx=0&edIdx=6&callFrom=districtData');
}
$(document).on("click",".pdfGenerateCls",function(){
	var benefitId=0;
	var attrType = $(this).attr("attr_pdf_type");
	var pdfDeptId = $(this).attr("attr_pdf_dept_id");
	if(attrType =="positive"){
		benefitId=1;
	}else if(attrType =="negative"){
		benefitId=2;
	}
	getDepartmentWiseNewsSummaryForPrintMedia(benefitId,pdfDeptId);
});
 function getDepartmentWiseNewsSummaryForPrintMedia(benefitId,pdfDeptId){
		 $("#overAllPrintMediaDivId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDepartmentWiseNewsSummary/"+glStartDate+"/"+glEndDate+"/"+pdfDeptId+"/"+benefitId
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartmentWiseNewsSummary/"+glStartDate+"/"+glEndDate+"/"+pdfDeptId+"/"+benefitId
		}).then(function(result){
			 if(results.exceptionMsg == "error"){
				 alert("Error Occured.Please Try Again Later.");
			 }else if(results.exceptionMsg == "success"){
				 window.open(results.message);
			 }
		});
	} 