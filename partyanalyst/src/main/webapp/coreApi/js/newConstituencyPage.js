onLoadAjaxCalls(); //ajaxcalls through newconstituencypage.js
var globalConsId = 232;
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
function onLoadAjaxCalls()
{
	getLocationWiseCommitteesCount();
	getEnrollmentIds();
	getLevelWiseMeetingStatusCounts();
	getLocationWiseInsuranceStatusCount();
	getLocationWiseGrivanceTrustStatusCounts();
	getGovtSchemeWiseBenefitMembersCount();
	getLocationWiseTourMembersComplainceDtls();
	getCasteGroupNAgeWiseVoterNCadreCounts();
	getActivityStatusList();
	getTotalAlertDetailsForConstituencyInfo();
	getAllPartiesAllElectionResultsChart(); //Assembly Election Detail
	getCandidateAndPartyInfoForConstituency();//get candidates inforamtion
	getDetailedElectionInformaction();//get detailed election information
	/*getCountsForConstituency(); //Assembly election Cadre
	
	getPrintMediaCountsForConstituencyPage(); //electronic media
	getDetailedGovtOverAllAnalysisProblemsForConstituencyPage(); //problems
	getDetailedGovtOverAllAnalysisProblemsForView();
	getDetailedElectionInformaction()//get detailed election information
	getVotersAndcadreAgeWiseCount();
	getVotersAndCadreCasteWiseCount("voter");
	getVotersAndCadreCasteWiseCount("cadre");
	getCasteGroupNAgeWiseVoterNCadreCounts(); */
	//getCasteNAgeWiseVoterNCadreCounts();
	getEnrollmentYearWiseCadres();
	
	//meetings(duplicate)
	getLocationWiseMeetingsCount();
	//cadre
	 getLocationTypeWiseCadreCount();
	// getAgeRangeGenerAndCasteGroupByCadreCount();
	//Benefit
	getPositionWiseMemberCount();
	getNominatedPostApplicationDetails();
	getNominatedPostStatusWiseCount();
	getCountsForConstituency();
	
	
}
function highChartsDonut(id,data,legend,title,height)
{
	$('#'+id).highcharts({
		chart: {
			height: height,
            type: 'pie',
			backgroundColor: 'transparent',
            options3d: {
                enabled: false,
                alpha: 45
            }
        },	
		title: {
			text: title
		},
		subtitle: {
			text: ''
		},
		plotOptions: {	
			pie: {
                innerSize: 40,
                depth: 50,
				showInLegend: legend,
				dataLabels: {
                    enabled: false,
				}
            }, 
		},
		legend: {
			itemStyle: {
				fontWeight: 'normal',
				'font-family':'roboto',
				'font-size':'16px'
            },
			enabled: true,
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
			useHTML: true,
			
			labelFormatter: function() {
				return '<span style="color:'+this.color+'">'+this.name + '-' + this.y + '';
			}
		},
		series:[{
            name : 'Count',
            data: data
        }]
	});
}
function getCandidateAndPartyInfoForConstituency(){
	$("#candidateProfile").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var $list = $('#candidateProfile'),
        templatess = Handlebars.compile($('#candidateProfileTemplate').html());
    jsObj={
    	constituencyId:232
    }
    $.ajax({
      type : "GET",
      url : "getCandidateAndPartyInfoForConstituencyAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		if(result !=null)
		{
			$("#candidateProfile").html('');
			$list.append(templatess(result.assemblyCandidateInfo[0]));
			$list.append(templatess(result.parliamentCandidateInfo));
		}else{
			$("#candidateProfile").html('NO DATA');
		}
		
	});
  }
	
function getCountsForConstituency(){
	$("#consCountsId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var tehsilId=0;
	var publicationDateId = 22;
	var type = 'constituency';
	id = 232;
	var $list = $('#consCountsId'),
        templatess = Handlebars.compile($('#cons-counts').html());
	
	var jsObj=
	{
		tehsilId:tehsilId,
		type:type,	
		id:id,
		publicationDateId:publicationDateId,
		constituencyId:232,
		task:"getCountForLevel"
	}
	$.ajax({
		type:'GET',
		url: 'getCountForLevelAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(results){
		if(results != null)
		{
			$list.html(templatess(results[0]));
		}else{
			$("#consCountsId").html('NO DATA');
		}
	});		
		 			
}
function getAllPartiesAllElectionResultsChart()
{
	var id = 'assemblyElectionDetails';
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
    var jsObj = {
		constituencyId:232,
		task:"partiesPerformanceInDiffElectionsAjax"
    };
    
    $.ajax({
		type:'GET',
		url: 'getPartiesPerformanceInDiffElectionsAjax.action',
		data: {task :JSON.stringify(jsObj)}
    }).done(function(results){
		
		if(results !=null && results.length > 0){
			var mainArray = [];
			var type = 'line';
			var years = [];
			
			for(var i in results[0].partiesList)
			{
				
				if(results[0].partiesList != null && results[0].partiesList.length > 0)
				{
					var tempArr = [];
					var Obj = {
						name : results[0].partiesList[i].name , 
						data : tempArr
					}
					mainArray.push(Obj)
				}
			}
			for(var i in results)
			{
				years.push(results[i].electionYear+" "+results[i].electionType);
				if(results[i].partyResultsVO != null && results[i].partyResultsVO.length > 0)
				{
					for(var j in results[i].partyResultsVO)
					{
						mainArray[j].data.push(parseFloat(results[i].partyResultsVO[j].percentage))
					}
				}
				
			}
			
			var type = {
				type: 'line',
				backgroundColor:'transparent'
			}
			var legend = {
				enabled: true,
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'top',
				x: 00,
				y: 00,
				floating: false,
				shadow: false
			}
			var yAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ''
				},
				labels: {
					enabled: true,
				},
				stackLabels: {
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			}
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: years,
				labels: {
					enabled:true
				}
			}
			var plotOptions = {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return Highcharts.numberFormat(this.y,2) + '%';
							}
						}
					}
				}
			}
			highcharts(id,type,xAxis,yAxis,legend,mainArray,plotOptions);	
		}
    });
}


/* Old Page COde*/
function getDistrictDetails(){
    jsObj={
      locationId:1
    }
    $.ajax({
      type : "GET",
      url : "getDistrictsDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select district</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].districtId+'">'+result[i].districtName+'</option>';
        }  
    }  
       $("#districtId").html(str);    
  });
  }
  function getConstituencyDetails(){
    jsObj={
      districtId:19
    }
    $.ajax({
      type : "GET",
      url : "getConstituenciesForADistrictDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select constituency</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#constituencyId").html(str);    
  });
  }
 function getMandalDetails(){
    jsObj={
      constituencyId:232
    }
    $.ajax({
      type : "GET",
      url : "getMandalsForAConstituencyDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select mandal</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#mandalId").html(str);    
  });
  }
  function getPanchayatDetails(){
    jsObj={
      mandalId:844
    }
    $.ajax({
      type : "GET",
      url : "getVillagesForMandalIdDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select mandal</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#municipalityId").html(str);    
  });
  }
  function getBoothDetails(){
    jsObj={
      panchayatId:1
    }
    $.ajax({
      type : "GET",
      url : "getAllBoothsForPanchayatDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select mandal</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#boothId").html(str);    
  });
  }
$(document).on("click","#summaryId",function(){
	constiELecId = 232;
	  getcandidateAssetsAndLiabilities(constiELecId);
	  getAssetsElectionYearsInfo(constiELecId);
});
function getcandidateAssetsAndLiabilities(constiELecId)
	{
		showBusyImgWithId("electionYearSelectForAssets");

		var jsObj=
		{
				constiElecId:constiELecId,
				task:"getCandidateAssetsAndLiabilitiesInfo"						
		};

	/* 	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = <%=request.getContextPath()%>+"/candidateAssetsAndLiabilitiesAction.action?"+rparam; */
	  $.ajax({
      type : "GET",
      url : "candidateAssetsAndLiabilitiesAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildAssetsAndLiabilities(result);
	});
		
}
function getAssetsElectionYearsInfo(constiId){
        var jsObj=
		{
				constituencyId:constiId,
				task:"getConstituencyElectionYears"						
		};
	$.ajax({
      type : "GET",
      url : "constituencyElectionYearsWithAssets.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildElectionYearsWithAssets(result);
	});
}
function showBusyImgWithId(elmtId){		
		
		var spanElmt = document.getElementById(elmtId+"_ImgSpan");
		if(spanElmt)
			spanElmt.style.display = 'block';
}


function buildAssetsAndLiabilities(results){
	$("#myModal").modal("show");
	if(results != null && results.candidateNominations.length > 0){
		var str='';
 str+='<table style="width:100%">';
  str+='<thead>';
 str+='<tr>';
		str+='<th>CandidateName</th>';
		str+='<th>Party</th>'; 
		str+='<th>Gender</th>';
		str+='<th>Education</th>';
		str+='<th>Assets</th>';
		str+='<th>Liabilities</th>';
  str+='</tr>';
  str+='</thead>';
  str+='<tbody>';
  for (var i in results.candidateNominations){
	  	 str+='<tr>';
	  	 str+='<td>'+results.candidateNominations[i].candidateName+'</td>';
	  	 str+='<td>'+results.candidateNominations[i].party+'</td>';
		 if(results.candidateNominations[i].gender != null){
			 str+='<td>'+results.candidateNominations[i].gender+'</td>'; 
		 }else{
			  str+='<td>-</td>';
		 }
		 if(results.candidateNominations[i].education != null){
			str+='<td>'+results.candidateNominations[i].education+'</td>'; 
		 }else{
			 str+='<td>-</td>';
		 }
		 if(results.candidateNominations[i].assets != null){
			 str+='<td>'+results.candidateNominations[i].assets+'</td>';
		 }else{
			 str+='<td>-</td>';
		 }
	  	 if(results.candidateNominations[i].liabilities != null){
			 str+='<td>'+results.candidateNominations[i].liabilities+'</td>';
		 }else{
			 str+='<td>-</td>';
		 }
		 str+='</tr>';
  }
  str+='</tbody>';
str+='</table>';
$("#buildPoupupImage").html(str);
	}
	
}
function buildElectionYearsWithAssets(myResults)
	{

		var divElmt = document.getElementById("electionYearsWithAssets_Div");
		var selectedVal = '';

        var electionYearSelect = '';

        electionYearSelect += '<table>';
		electionYearSelect += '<th>Select Election Year to view Assets & Liabilities :</th>';
		electionYearSelect += '<th style="text-align:left;">';
		electionYearSelect += '<select id="electionYearSelectForAssets" class = "selectWidth" onchange = "getcandidateAssetsAndLiabilities(this.options[this.selectedIndex].value)">';
		for(var i in myResults)
		{			
			electionYearSelect += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
			if(i == 0)
				selectedVal = myResults[i].id;
		}
		electionYearSelect += '</select>';
		electionYearSelect += '<td><span id="electionYearSelectForAssets_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></span></td>';
		electionYearSelect += '</th>';

		electionYearSelect += '</table>';
	    divElmt.innerHTML = electionYearSelect; 

		getcandidateAssetsAndLiabilities(selectedVal);

	}
$(document).on("click","#candidateProfileId",function(){
	window.open('cadreDetailsAction.action?cadreId=5709765','_blank');
});
	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
wurl = url.substr(0,(url.indexOf(".in")+3));

function getPrintMediaCountsForConstituencyPage(){
	var $list = $('#printMediaCounts')
	var $list1 = $('#electronicMediaCounts')
    var templatess = Handlebars.compile($('#media-counts').html());
	$list.html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$list1.html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var userAccessLevelValuesArray=232;
	var userAccessLevelId=5;
	var state="ap";
	var startDate="18-03-2015",endDate="18-03-2017";
    var newsPaperIdsStr=" ";
    var impactScopeIdsStr=" ";
    var benefitIdsStr=" ";
    var orgIdsStr=" ";
    var type="Print";
    var isDept="N";
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaCountsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+1+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr+"/"+orgIdsStr+"/"+type+"/"+benefitIdsStr+"/"+isDept
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getPrintMediaCountsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+"/"+newsPaperIdsStr+"/"+impactScopeIdsStr+"/"+orgIdsStr+"/"+type+"/"+benefitIdsStr+"/"+isDept
		
	}).then(function(result){
		$list.html(templatess(result.coreDashBoardVOList));
		$list1.html(templatess(result.coreDashBoardVOList1));
	});
}
	
function getDetailedGovtOverAllAnalysisProblemsForConstituencyPage(){
	var $list = $('#probDetailedCounts'),
        templatess = Handlebars.compile($('#prob-counts').html());
	$('#probDetailedCounts').html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var userAccessLevelValuesArray=232;
	var userAccessLevelId=5;
	var state="ap";
	var startDate="18-03-2016",endDate="18-03-2017";
	var npIdsStr=" ";
	var propertyIdStr=" ";
	var impactScopeIdsStr=[1,2];
	var orgIdStr=" ";
	var isDept="N";
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisProblemsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+npIdsStr+"/"+impactScopeIdsStr+"/"+orgIdsStr+"/"+0+"/"+12
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisProblemsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyIdStr+"/"+impactScopeIdsStr
		
	}).then(function(result){
		$list.html(templatess(result));
		var data = []
		for(var i in result){
			var Obj = {
				name : result[i].name,
				y: result[i].positivePerc
			}
			data.push(Obj);
		}
		//console.log(data);
		highChartsDonut('problemsDetailedGraph',data,false,200);
	});
}
function getDetailedGovtOverAllAnalysisProblemsForView(){
	var $list = $('#problemsSolveId'),
        templatess = Handlebars.compile($('#problem-graph').html());
	$('#problemsSolveId,#problemsSolveGraph').html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var userAccessLevelValuesArray=1;
	var userAccessLevelId=2;
	var state="ap";
	var startDate="01-01-2017",endDate="18-03-2017";
	var npIdsStr=[1,2,3,10,11,12];
	var propertyIdStr=[26,28,29,32];
	var impactScopeIdsStr=[1,2,3,4,5,6,8];
	var orgIdStr=" ";
	var isDept="N";
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisProblemsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+npIdsStr+"/"+impactScopeIdsStr+"/"+orgIdsStr+"/"+0+"/"+12
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedGovtOverAllAnalysisProblemsForConstituencyPage/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+"/"+npIdsStr+"/"+propertyIdStr+"/"+impactScopeIdsStr
		
	}).then(function(result){
		$list.html(templatess(result));
		minimise(".problemsMinimiseText",20)
		if(result !=null && result.length > 0){
			var dynamicHeight;
				dynamicHeight = (result.length*30)+"px";
			$("#problemsSolveGraph").css("height",dynamicHeight);
			var departmentStatusOvrVwArr = [];
			for(var i in result)
			{
				var departmentStatus = [];
				departmentStatus.push(result[i].name)
				departmentStatus.push(result[i].positivePerc)
				departmentStatusOvrVwArr.push(departmentStatus)
			}
			
			var data = [{
					name : '',
					colorByPoint: true,
					data: departmentStatusOvrVwArr
				}]
			 var type = {
				type: 'bar'
				
			}
			var legend = {
				enabled: false,
			}
			var yAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ''
				},
				labels: {
					enabled: false,
						
					},
				stackLabels: {
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			}
			var xAxis = {
				 min: 0,
				 gridLineWidth: 0,
				 minorGridLineWidth: 0,
				categories: '',
				labels: {
					enabled: false,
				}
			}
			var plotOptions = {
				bar: {
					dataLabels: {
						enabled: true,
						formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return Highcharts.numberFormat(this.y,2) + '%';
							}
						}
					}
				}
			}
			highcharts('problemsSolveGraph',type,xAxis,yAxis,legend,data,plotOptions);
		}
	});
}
function getDetailedElectionInformaction(){
	$("#assemblyElectionDetailsView").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	var $list = $('#assemblyElectionDetailsView'),
        templatess = Handlebars.compile($('#assemblyElectionDetailsTable').html());
	jsObj={
    	constituencyId:232
    }
    $.ajax({
      type : "GET",
      url : "getDetailedElectionInformactionAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		$list.html(templatess(result));
	});
  }

function getVotersAndcadreAgeWiseCount(){
    jsObj={
    	constituencyId:232,
    	publicationDateId:22
    }
    $.ajax({
      type : "GET",
      url : "getVotersAndcadreAgeWiseCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
  });
}

function getVotersAndCadreCasteWiseCount(type){
	jsObj={
		type:type,
    	constituencyId:232,
    	publicationDateId:22
    }
	 $.ajax({
      type : "GET",
      url : "getVotersAndCadreCasteWiseCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
	});	
}

var casteInfoResultGlob = "";
function getCasteGroupNAgeWiseVoterNCadreCounts(){
	$("#casteGroupsTabsDivId").html("");
	jsObj={
		constituencyId:232,
    	publicationDateId:22
    }
	 $.ajax({
      type : "GET",
      url : "getCasteGroupNAgeWiseVoterNCadreCountsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
		if(result != null && result.length > 0){
			casteInfoResultGlob = result;
			setTimeout(function(){
				buildCasteGroupWiseInfo();
				buildCasteInfoForVoter();
			}, 1000);
		}else{
			$("#casteGroupsTabsDivId").html("No Data Available");
		}
	});	
}

function getCasteNAgeWiseVoterNCadreCounts(casteGroupId,casteId,casteName){
	$("#expandCaste"+casteId).html(spinner);
	jsObj={
		casteGroupId:casteGroupId,
		casteId:casteId,
		constituencyId:232,
    	publicationDateId:22
    }
	 $.ajax({
      type : "GET",
      url : "getCasteNAgeWiseVoterNCadreCountsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
		if(result != null && result.length > 0){
			var str='';
			str+='<h4 class="panel-title"><span class="text-capital">'+casteName+'</span> - <span class="text-capitalize">voter and cadre information b/w age group</span></h4>';
			str+='<p class="text-muted text-right">';
			str+='<span class="f-11"><i class="glyphicon glyphicon-info-sign"></i> _(C) = Cadres ; _(V) = Voter</span>';
			str+='</p>';
			str+='<table class="table table-noborder m_top10">';
			str+='<thead class="text-capitalize">';
			str+='<th></th>';
			str+='<th>voters <span class="text-success">%</span></th>';
			str+='<th>cadres <span class="text-success">%</span></th>';
			str+='<th>Male(V) <span class="text-success">%</span></th>';
			str+='<th>Male(C) <span class="text-success">%</span></th>';
			str+='<th>FeMale(V) <span class="text-success">%</span></th>';
			str+='<th>FeMale(C) <span class="text-success">%</span></th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].ageRange+'</td>';
					str+='<td>'+result[i].totalVoters+' '+result[i].totalVotersPerc+'</td>';
					str+='<td>'+result[i].totalCadres+' '+result[i].totalCadrePerc+'</td>';
					str+='<td>'+result[i].maleVoters+' '+result[i].maleVotersPerc+'</td>';
					str+='<td>'+result[i].maleCadres+' '+result[i].maleCadrePerc+'</td>';
					str+='<td>'+result[i].femaleVoters+' '+result[i].femaleVotersPerc+'</td>';
					str+='<td>'+result[i].femaleCadres+' '+result[i].femaleCadrePerc+'</td>';
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
			$("#expandCaste"+casteId).html(str);
		}else{
			$("#expandCaste"+casteId).html("No Data Available.");
		}
	});	
}
function getEnrollmentYearWiseCadres(){
	jsObj={
		enrollmentYearId:4,
		constituencyId:232
	}
	 $.ajax({
      type : "GET",
      url : "getEnrollmentYearAgeGroupWiseCadresAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
	});	
}

function getLocationWiseMeetingsCount(){
	jsObj={
		locationtype:"constituency",
		constituencyId:232
	}
	 $.ajax({
      type : "GET",
      url : "getLocationWiseMeetingsCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
	});	
}

function getLocationWiseTourMembersComplainceDtls(){
	jsObj={
		locationTypeId:4,
		locationValuesArr:[232],
		fromDate:"01/05/2017",
		toDate:"30/05/2017",
		year:""
	}
	 $.ajax({
      type : "POST",
      url : "getLocationWiseTourMembersComplainceDtlsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	//console.log(result);
		var str='';
		if(result!=null && result.length>0){
			str+='<table class="table table-hover">';
			str+='<thead class="text-capitalize bg-E9">';
			str+='<th>Designation</th>';
			str+='<th>status</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
			str+='<td>'+result[i].designation+'</td>';
			if(result[i].isComplaince=="False"){
			str+='<td><span class="text-success" style="color:red">Non-Complaince</span></td>';	
			}else{
				str+='<td><span class="text-success" style="color:green">Complaince</span></td>';	
			}
			str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
        $("#toursId").html(str);
		}
	});	
}
function getGovtSchemeWiseBenefitMembersCount(){
$("#benefitsId").html(spinner);
	jsObj={
		locationType:4,
		locationValue:232
	}
	 $.ajax({
      type : "POST",
      url : "getGovtSchemeWiseBenefitMembersCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		var str='';
		
		if(result!=null && result.length>0){
			str+='<select class="form-control" role="tabListMobile">';
				for(var i in result){
					str+='<option class="tabClick" id="'+result[i].id+'" tab_id="'+result[i].id+'">'+result[i].name+'</option>';
				}
			str+='</select>';
			str+='<ul class="nav nav-tabs nav-tabs-horizontal" role="tablist">';
				getMandalWiseBenefitMembersCount(result[0].id,result[0].name);
				for(var i in result){
				
				if(i==0)
					str+='<li role="benfitPresentation" class="active"><a class="tabClick" href="#" id="'+result[i].id+'" aria-controls="'+result[i].name+'" role="tab" data-toggle="tab">'+result[i].name+'<span class="pull-right">'+result[i].totalCount+'</span></a></li>';
				else	
					str+='<li role="benfitPresentation"><a class="tabClick" href="#" id="'+result[i].id+'" aria-controls="'+result[i].name+'" role="tab" data-toggle="tab">'+result[i].name+'<span class="pull-right">'+result[i].totalCount+'</span></a></li>';

				}
			str+='</ul>'
			$("#benefitsId").html(str);
			responsiveTabs()
		}
	});	
}
$(document).on("click",".tabClick",function(){
	var tab_id = $(this).attr('id');
	var name = $(this).attr('aria-controls');
	getMandalWiseBenefitMembersCount(tab_id,name);	
});
function getMandalWiseBenefitMembersCount(id, name){
$("#benefits1").html('');
$("#benefits1").html(spinner);
	var schemeId=id;
	jsObj={
		locationType:4,
		locationValue:232,
		govtSchemeId:schemeId
	}
	$.ajax({
      type : "POST",
      url : "getMandalWiseBenefitMembersCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	var str= '';
		var mainMandal1=[];
		if(result!= null){
			var totalBenfitedCount =0;
			var totalPoPulation =0;
			for (var i in result){
				totalBenfitedCount=totalBenfitedCount+result[i].totalCount;
				totalPoPulation=totalPoPulation+result[i].totalPopulation;
			}
			/* str+='<div class="row"><div class="col-md-6 col-xs-12 col-sm-6">';
			//str+='<div id="benefitsGraph"></div></div>';
			str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<table class="table table-noborder">';
			str+='<thead class="text-capitalize bg-E9">';
			str+='<th></th>';
			str+='<th>Total</th>';
			//str+='<th>%</th></thead>';							
			str+='<tbody class="text-capitalize">';	
			
			str+='<tr>';
			str+='<td><span class="chart-legend-color"></span>TotalPopulation</td>'
			str+='<td>'+totalPoPulation+'</td></tr> ';
			str+='<tr>';
			str+='<td><span class="chart-legend-color"></span>benefited</td>'
			str+='<td>'+totalBenfitedCount+'</td>';
			str+='</tr></tbody></table></div>';  */
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
			str+='<table class="table table-noborder">';
			str+='<thead class="text-capitalize bg-E9">';
			str+='<th>mandal name</th>';
			//<th>population</th><th>eligible</th><th>non-eligible benefited</th>
			str+='<th>benefited</th></thead><tbody class="text-capitalize">'
			for (var i in result){
				str+='<tr>';
				str+='<td>'+result[i].name+'</td>';
				str+='<td>'+result[i].totalCount+'</td>';
				str+='</tr>';
			}
			str+='</tbody></table></div></div>';
		}
		mainMandal1=[{name:'Population',y:totalPoPulation,color:'#fc8db7'},
			{name:'Benfited',y:totalBenfitedCount,color:'#27b720'}];
		$("#"+'benefitsGraph').html();
			highChartsDonut('benefitsGraph',mainMandal1,true,name,200)
		$("#benefits1").html(str);
	});	
	
}
function getLocationTypeWiseCadreCount(){
	jsObj={
		locationTypeId:4,
		locationValuesArr:[232]
	}
	 $.ajax({
      type : "POST",
      url : "getLocationTypeWiseCadreCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
	//console.log(result);
	});	
}

function getAgeRangeGenerAndCasteGroupByCadreCount(){
	jsObj={
		locationType:"constituency",
		locationValue:232,
		enrollmentYearId:4
	}
	 $.ajax({
      type : "POST",
      url : "getAgeRangeGenerAndCasteGroupByCadreCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
	//console.log(result);
	});	
}

//Location wise committees count
function getLocationWiseCommitteesCount(){
	var jsObj={
			locationType : "constituency",
			locationId : 232,
			enrollmentId : 2
			
		}
	 $.ajax({
      type : "POST",
      url : "getLocationWiseCommitteesCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	//console.log(result);
		var str='';
		var mainMandal = [];
		var mainVillage= [];
		var affMandal = [];
		var affVillage = [];
		var mainMandalId = '';
		var mainVillageId = '';
		var affMandalId = '';
		var affVillageId = '';
		var depth = 60;
		if(result != null){
			var mainMandalTotal=result.mainCommStartedCount+result.mainCommCompletedCount+result.mainCommNotYetStarted;
			var affltdAffltedTotal=result.affiCommStartedCount+result.affiCommCompletedCount+result.affiCommMandalNotStarted;
			var mainVlgTotal=result.mainCommTotalMembers+result.mainCommTotalCount+result.mainCommVillageNotStarted;
			var affltdVlgTotal=result.affiCommTotalMembers+result.affiCommTotalCount+result.affiCommVillageNotStarted;
			var mandalNotStartedPer= Math.round((result.mainCommNotYetStarted/mainMandalTotal)*100);
			var affltdMandalNotStartedPer= Math.round((result.affiCommMandalNotStarted/affltdAffltedTotal)*100);
			var mainVlgNotStartedPer= Math.round((result.mainCommVillageNotStarted/mainVlgTotal)*100);
			var affltdVlgNotStartedPer= Math.round((result.affiCommVillageNotStarted/affltdVlgTotal)*100);

			str+='<table class="table table-bordered">';
			str+='<thead>';
			str+='<tr class="text-capital">';
			str+='<th rowspan="2"></th>';
			str+='<th colspan="3">main committee</th>';
			str+='<th colspan="3">affliated committee</th>';
			str+='</tr>';
			str+='<tr class="text-capitalize bg-E9">';
			str+='<th>total</th>';
			str+='<th>started</th>';
			str+='<th>completed</th>';
			str+='<th>NotStarted</th>';
			str+='<th>total</th>';
			str+='<th>started</th>';
			str+='<th>completed</th>';
			str+='<th>NotStarted</th>';
			str+='</tr>';
			str+='</thead>';
			str+='<tbody class="text-capitalize">';
			str+='<tr>';
			str+='<td>mandal/town/division</td>';
			str+='<td>'+mainMandalTotal+'</td>';
			str+='<td>'+result.mainCommStartedCount+'</td>';
			str+='<td>'+result.mainCommCompletedCount+'</td>';
			str+='<td>'+result.mainCommNotYetStarted+'</td>';
			str+='<td>'+affltdAffltedTotal+'</td>';
			str+='<td>'+result.affiCommStartedCount+'</td>';
			str+='<td>'+result.affiCommCompletedCount+'</td>';
			str+='<td>'+result.affiCommMandalNotStarted+'</td>';
			str+='</tr>';
			str+='<tr>';
			str+='<td>village/ward</td>';
			str+='<td>'+mainVlgTotal+'</td>';
			str+='<td>'+result.mainCommTotalMembers+'</td>';
			str+='<td>'+result.mainCommTotalCount+'</td>';
			str+='<td>'+result.mainCommVillageNotStarted+'</td>';
			str+='<td>'+affltdVlgTotal+'</td>';
			str+='<td>'+result.affiCommTotalMembers+'</td>';
			str+='<td>'+result.affiCommTotalCount+'</td>';
			str+='<td>'+result.affiCommVillageNotStarted+'</td>';
			str+='</tr>';
			str+='</tbody>';
			str+='</table>';

			mainMandal=[
				{name:'Completed',y:result['mainMandalCompletePer'],color:'#eda809'},
				{name:'Started',y:result['mainMandalStartPer'],color:'#862d59'},
				{name:'NotStarted',y:mandalNotStartedPer,color:'#c3c6c3'}];	
			mainVillage=[{name:'Completed',y:result['mainVillageCompletePer'],color:'#eda809'},
					{name:'Started',y:result['mainVillageStartPer'],color:'#862d59'},
					{name:'NotStarted',y:mainVlgNotStartedPer,color:'#c3c6c3'}];	
			affMandal = [{name:'Completed',y:result['affMandalCompletePer'],color:'#eda809'},
					{name:'Started',y:result['affMandalStartPer'],color:'#862d59'},
					{name:'NotStarted',y:affltdMandalNotStartedPer,color:'#c3c6c3'}];
		    affVillage = [{name:'Completed',y:result['affVillageCompletePer'],color:'#eda809'},
					{name:'Started',y:result['affVillageStartPer'],color:'#862d59'},
					{name:'NotStarted',y:affltdVlgNotStartedPer,color:'#c3c6c3'}];
		mainMandalId = 'mandalLevelId';
		mainVillageId = 'villageLevelId';
		affMandalId = 'affMandalLevelId';
		affVillageId = 'affVillageLevelId';
		
		$("#"+mainMandalId).html();
		highChartsDonut(mainMandalId,mainMandal,true,'mandal/town level',200)
		$("#"+mainVillageId).html();
		highChartsDonut(mainVillageId,mainVillage,true,'vilalge/ward level',200)
		$("#"+affMandalId).html();
			highChartsDonut(affMandalId,affMandal,true,'mandal/town level',200)
			$("#"+affVillageId).html();
			highChartsDonut(affVillageId,affVillage,true,'vilalge/ward level',200)	
				$("#committees").append(str);
		}
		 
	});	
}


// Location Wise Meetings Status Counts
function getLevelWiseMeetingStatusCounts(){
	var jsObj={
			fromDate : "",
			toDate:"",
			locationTypeId : 4,
			locationValuesArr :[232],
			year:""
			
		}
	 $.ajax({
      type : "POST",
      url : "getLevelWiseMeetingStatusCountsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	//console.log(result);
		var str='';
		if(result!=null && result.length>0){
		var vwTotal=result[0].completedCnt+result[0].pendingCnt+result[0].unabletoResolveCnt+result[0].notifiedCnt;
		var mtdTotal=result[1].completedCnt+result[1].pendingCnt+result[1].unabletoResolveCnt+result[1].notifiedCnt;
		var cTotal=result[2].completedCnt+result[2].pendingCnt+result[2].unabletoResolveCnt+result[2].notifiedCnt;
		str+='<table class="table table-bordered">';
		str+='<thead class="text-capitalize">';
			str+='<th></th>';
			str+='<th class="bg-E9">Village / Ward</th>';
			str+='<th class="bg-E9">mandal/town/division</th>';
			str+='<th class="bg-E9">constituency</th>';
		str+='</thead>';
		str+='<tbody>';
		  str+='<tr>';
		    str+='<td>Total</td>';
			str+='<td>'+vwTotal+'</td>';
			str+='<td>'+mtdTotal+'</td>';
			str+='<td>'+cTotal+'</td>';
		  str+='</tr>';
		  str+='<tr>';
		   str+='<td>Yes</td>';
		   str+='<td>'+result[0].completedCnt+'</td>';
		str+='<td>'+result[1].completedCnt+'</td>';
		str+='<td>'+result[2].completedCnt+'</td>';
		str+='</tr>';
		str+='<tr>';
		str+='<td>No</td>';
		str+='<td>'+result[0].pendingCnt+'</td>';
		str+='<td>'+result[1].pendingCnt+'</td>';
		str+='<td>'+result[2].pendingCnt+'</td>';
		str+='</tr>';
		str+='<tr>';
		str+='<td>MayBe</td>';
		str+='<td>'+result[0].unabletoResolveCnt+'</td>';
		str+='<td>'+result[1].unabletoResolveCnt+'</td>';
		str+='<td>'+result[2].unabletoResolveCnt+'</td>';
		str+='</tr>';
		str+='<tr>';
		str+='<td>Not Updated</td>';
		str+='<td>'+result[0].notifiedCnt+'</td>';
		str+='<td>'+result[1].notifiedCnt+'</td>';
		str+='<td>'+result[2].notifiedCnt+'</td>';
		str+='</tr>';
		str+='</tbody>';
		str+='</table>';
		$("#meetingsId").append(str);
		}
		 
	});	
}

// Get enrollment Ids from db
function getEnrollmentIds(){
	var jsObj={
			
	}
	 $.ajax({
      type : "GET",
      url : "getEnrollmentYearsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	//console.log(result);
	});	
}
//Location wise insurance status counts
function getLocationWiseInsuranceStatusCount(){
	var jsObj={
			fromDate : "",
			toDate:"",
			locationTypeId : 4,
			locationValuesArr :[232],
			year:""
			
		}
	 $.ajax({
      type : "POST",
      url : "getLocationWiseInsuranceStatusCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	//console.log(result);
		//console.log(result['accountRejected']);
		var data = [];
		var depth = 60;
		var id  = '';
		if(result!=null){
			data=[
					{name:'AccountRejected',y:result['accountRejected']},
					{name:'Approved',y:result['approved']},
					{name:'ClosedAtInsurance',y:result['closedAtInsurance']},
					{name:'ClosedAtParty',y:result['closedAtParty']},
					{name:'ClosedLetters',y:result['closedLetters']},
					{name:'ForeadedToInsurance',y:result['foreadedToInsurance']},
					{name:'SubmittedInparty',y:result['submittedInparty']},
					{name:'WaitingForDocs',y:result['waitingForDocs']},
						];
			id = 'insuranceId';
		$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		highChartsDonut(id,data,true,'Insurance',250)
		}
	});	
}

//Location wise Grivance and trust status counts
function getLocationWiseGrivanceTrustStatusCounts(){
	var jsObj={
			"fromDate" : "",
			"toDate":"",
			"locationTypeId" : 4,
			"locationValuesArr" :[232],
			"year":""
		}
	 $.ajax({
      type : "POST",
      url : "getLocationWiseGrivanceTrustStatusCountsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	//console.log(result);
		var grivance = [];
		var trust = [];
		var depth = 60;
		var grivanceId  = '';
		var trustId = '';
		if(result!=null && result.length>0){
		grivance=[{name:'Approved',y:result[0]['approves']},
		{name:'Completed',y:result[0]['completed']},
		{name:'InProgress',y:result[0]['inProgress']},
		{name:'NotEligible',y:result[0]['notEligible']},
		{name:'NotPossible',y:result[0]['notPossible']},
		{name:'NotVerified',y:result[0]['notVerified']},];
		
		trust=[{name:'Approved',y:result[1]['approves']},
		{name:'Completed',y:result[1]['completed']},
		{name:'InProgress',y:result[1]['inProgress']},
		{name:'NotEligible',y:result[1]['notEligible']},
		{name:'NotPossible',y:result[1]['notPossible']},
		{name:'NotVerified',y:result[1]['notVerified']},];
			grivanceId = 'grivanceId';
			trustId = 'trustId';
		$("#"+grivanceId).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#"+trustId).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		highChartsDonut(grivanceId,grivance,true,'Grievance',250)
		highChartsDonut(trustId,trust,true,'Trust Education',250)
		}
	});	
}
function getTotalAlertDetailsForConstituencyInfo(){
	var jsObj={
			"fromDateStr" : "",
			"toDateStr":"",
			"locationValuesArr" : [232],
			"alertTypeIdsStr" : [1],
			"locationTypeId":4,
			"year" :""

		}
	 $.ajax({
      type : "POST",
      url : "getTotalAlertDetailsForConstituencyInfoAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	buildAlertsBlock(result);
	});	
}
function buildAlertsBlock(result){
	var str ='';
	str+='<div class="col-md-12 col-xs-12 col-ms-12 m_top20" navbar-index="alerts">';
				str+='<h3>Alerts <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>';
				str+='<div class="block">';
					str+='<div class="row">';
						str+='<div class="col-md-6 col-xs-12 col-sm-6">';
							str+='<div class="block">';
								str+='<ul class="list-inline list-border">';
									str+='<li>';
										if(result[0].idNamesList[0].count != null && result[0].idNamesList[0].count > 0){
											str+='<h4>'+result[0].idNamesList[0].count+'</h4>';
										}else{
											str+='<h4>0</h4>';
										}
									str+='<p>Total Alerts</p>';
									str+='</li>';
									str+='<li>';
										if(result[0].docList[0].count != null && result[0].docList[0].count > 0){
											str+='<h4>'+result[0].docList[0].count+'</h4>';
										}else{
											str+='<h4>0</h4>';
										}
										
										str+='<p>Involved Members</p>';
									str+='</li>';
									str+='<li>';
										//if(result[0].assignList[0].count != null && result[0].assignList[0].count > 0 && result[0].assignList[0].count !=""){
											//str+='<h4>'+result[0].assignList[0].count+'</h4>';
										//}else{
											str+='<h4>0</h4>';
										//}
										
										str+='<p>Assigned Memebers</p>';
									str+='</li>';
								str+='</ul>';
							str+='</div>';
							str+='<div class="row m_top15">';
								str+='<div class="col-md-5 col-xs-12 col-sm-5">';
									str+='<div id="overallAlerts" style="height:200px"></div>';
								str+='</div>';
								str+='<div class="col-md-7 col-xs-12 col-sm-7">';
									 
								  /*str+='<select class="form-control" role="tabListMobile">';
									  str+='<option tab_id="alerts1">Constitunety Level</option>';
									  str+='<option tab_id="alerts2">Mandal/Muncipality</option>';
									  str+='<option tab_id="alerts3">Village/Ward</option>';
								  str+='</select>';*/
								  str+='<ul class="nav nav-tabs nav-tabs-horizontal" role="tablist">';
									str+='<li role="presentation" class="active"><a href="#alerts1" aria-controls="alerts1" role="tab" data-toggle="tab">Constitunety Level</a></li>';
									str+='<li role="presentation"><a href="#alerts2" aria-controls="alerts1" role="tab" data-toggle="tab">Mandal/Muncipality</a></li>';
									str+='<li role="presentation"><a href="#alerts3" aria-controls="alerts1" role="tab" data-toggle="tab">Village/Ward</a></li>';
								  str+='</ul>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-md-6 col-xs-12 col-sm-6 pad_left0">';
							str+='<div>';
							  str+='<div class="tab-content">';
								str+='<div role="tabpanel" class="tab-pane active pad_10" id="alerts1">';
									str+='<table class="table table-noborder">';
										str+='<thead class="text-capitalize bg-E9">';
											str+='<th>alert status</th>';
											str+='<th>total</th>';
											str+='<th>party</th>';
											str+='<th>govt</th>';
										str+='</thead>';
										str+='<tbody>';
										
										if(result!= null && result.length > 0){
											for(var i in result){
												for(var j in result[i].subList2){
											str+='<tr>';
											str+='<td>'+result[i].subList2[j].status+'</td>';
											// str+='<td>''</td>';
											str+='<td>'+result[i].subList2[j].count+'</td>';
										/*	if(result[i].subList2[i].alertTypeId == 1){
												str+='<td>'+result[i].subList2[i].count+'</td>';
											}else if(result[i].subList2[i].alertTypeId == 2){
												str+='<td>'+result[i].subList2[i].count+'</td>';
											} */
											
										  str+='</tr>';
										  }
											}
										}											
										str+='</tbody>';
									str+='</table>';
								str+='</div>';
								str+='<div role="tabpanel" class="tab-pane pad_10" id="alerts2">';
									
								str+='</div>';
								str+='<div role="tabpanel" class="tab-pane pad_10" id="alerts3">';
									  
								str+='</div>';
							  str+='</div>';

							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="block">';
					str+='<h4>Alert Impact Level</h4>';
					str+='<ul class="list-inline blockStyleAlign">';
					if(result[0].subList1 != null && result[0].subList1.length > 0){
						for(var k in result[0].subList1){
						str+='<li><h2 style="text-align: center;">'+result[0].subList1[k].count+'</h2> <p>'+result[0].subList1[k].status+'</p></li>';
						}
					}
						
					str+='</ul>';
				str+='</div>';
			str+='</div>';
			$("#alertsBlockDiv").html(str);
			
		
		$("#overallAlerts").highcharts({
			chart: {
				type: 'pie',
				options3d: {
					enabled: true,
					alpha: 25
				}
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			tooltip: {
				useHTML: true,
				backgroundColor: '#FCFFC5', 
				formatter: function() {
					var cnt = this.point.count;
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+cnt+"("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
				}  
			}, 
			plotOptions: {
				series: {
					dataLabels: {
						enabled: false,
						formatter: function() {
							return Math.round(this.percentage*100)/100 + ' %';
						},
						distance: -30,
						color:'black'
					},
					point:{
						events:{
							click:function(){
								getData(this.count,this.sts);     
							}
						}
					}
				},
				pie: {
					innerSize: 130,
					depth: 180,
					dataLabels:{
						enabled: false,
						  formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1)+ '%';
								}
							} 
					},
					showInLegend: false
				},
			},
			    series: [{
				name: 'Brands',
				colorByPoint: true,
				data: [{
					name: 'Constituency',
					y: 56.33
					
				}, {
					name: 'Mandal/Municipality',
					y: 24.03
					
				}, {
					name: 'Village/Ward',
					y: 10.38
					
				}]
			}]
		});

}

function getActivityStatusList(){
	var jsObj={
			"fromDate" : "",
			"toDate":"",
			"year":"",
			"locationValues" : [12],
			"locationId" : 3
		}
	 $.ajax({
      type : "POST",
      url : "getActivityStatusListAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	//console.log(result);
		var str = '';
		var per='%';
		str+='<table class="table table-bordered">';
		str+='<thead class="text-capitalize">';
		str+='<th>Activity Name</th>';
		str+='<th>level</th>';
		str+='<th>status</th>';
		str+='</thead>';
		str+='<tbody>';
		if(result!=null && result.length>0){
			for(var i in result){
		str+='<tr>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].description+'</td>';
		str+='<td>'+result[i].perc+''+per+'</td>';
		str+='</tr>';
			}
		}
		str+='</tbody>';
		str+='</table>';
		
		$("#activiteId").html(str);
	});	
}
function getNominatedPostStatusWiseCount(){
	var jsObj={
			"year":"",
			"fromDateStr" : "",
			"toDateStr":"",
			"locationTypeId":4,
			"locationValues":[232]
		}
	 $.ajax({
      type : "POST",
      url : "getNominatedPostStatusWiseCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		var subArr = [];
		var colors = ['#0FBE08','#FF0909','#FFBA00','#FFBAB0']
		if(result != null && result.length >0){
			var mainArr =[];
			var totArr=[];
			var totalCount =0;
			for(var i in result){
				var subArr = [];
				totalCount=totalCount+result[i].count;
				subArr={name:result[i].name,y:result[i].count};
				mainArr.push(subArr);
				str+='<div class="col-md-6 col-xs-12 col-sm-6">';
					str+='<ul class="graph-legend">';
						str+='<li><span class="statusBox"></span>'+result[i].name+'<span class="count">'+result[i].count+'</span></li>';
					str+='</ul>';
				str+='</div>';
				$("#nominatedPostDiv").html(str);
			}
			totArr={name:'Total',y:totalCount};	
			mainArr.push(totArr);
				$('#postsDiv').highcharts({
					chart: {
						type: 'column',
						height:350,
						backgroundColor: 'transparent',
						options3d: {
							enabled: false,
							alpha: 45
							}
					},
					colors: colors,
					title: {
						text: 'Posts'
					},
					subtitle: {
						text: ''
					},
					plotOptions: {	
						column: {
							colorByPoint: true
						}, 
					},
					legend:{
						enabled:false
					},
					xAxis: {
						type: 'category',
						labels: {
							rotation: -45,
						style: {
						fontSize: '13px',
						fontFamily: 'Verdana, sans-serif'
						}
					
						}
					},
					yAxis: {
						min: 0,
						title: {
							text: 'Posts Count'
						}
					},
					series:[{
						name: 'Posts Count',
						data: mainArr,
						dataLabels: {
							enabled: false,
							rotation: -90,
							color: '#FFFFFF',
							align: 'right',
							format: '{point.y}', 
							y: 10, 
							style: {
								fontSize: '13px',
								fontFamily: 'Verdana, sans-serif'
							}
						}
					}]
				});
		
			//chart end
			
		}
	});	
}
function getNominatedPostApplicationDetails(){
	var jsObj={
			"year":"",
			"fromDateStr" : "",
			"toDateStr":"",
			"locationTypeId":4,
			"locationValues":[232]
		}
	 $.ajax({
      type : "POST",
      url : "getNominatedPostApplicationStatusWiseCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		var mainArr = [];
		if(result != null && result.length >0){
			for(var i in result){
				var subArr = [];
				var name = "",
				name = result[i].name;
				subArr={name:result[i].name,y:parseInt(result[i].count)};
				mainArr.push(subArr);			
				/* str+='<div class="col-md-6 col-xs-12 col-sm-6">';
					str+='<ul class="graph-legend">';
						str+='<li><span class="statusBox" ></span>'+result[i].name+'<span class="count">'+result[i].count+'</span></li>';
					str+='</ul>';
				str+='</div>';
				$("#applicationCntBlock").html(str); */
			}
			highChartsDonut('NominatedPostInfoChartDivId',mainArr,true,'Applications',250)
			$("#NominatedPostInfoChartDivId").html();
		}
	});	
}
function getPositionWiseMemberCount(){
	var jsObj={
			"fromDateStr" : "",
			"toDateStr":"",
			"constituencyId":232
		}
	 $.ajax({
      type : "POST",
      url : "getPositionWiseMemberCountAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		if(result != null && result.length >0){
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<ul class="list-inline blockStyleAlign" >';
			for(var i in result){
				str+='<li>';
					str+='<h3>'+result[i].count+'</h3>';
					str+='<p class="text-capitalize">'+result[i].name+'</p>';
				str+='</li>';
			}
			str+='</ul>';
				str+='</div>';
			$("#nominatedMembersDiv").html(str);
		}
	});	
}
$(document).on("click",".voterCadreSwitchCls",function(){
	if($(this).attr("attr_type") == "voter"){
		buildCasteInfoForVoter();
	}else if($(this).attr("attr_type") == "cadre"){
		builsCasteInfoForCadre();
	}
});

function buildCasteInfoForVoter(){
	if(casteInfoResultGlob != null && casteInfoResultGlob != "" && casteInfoResultGlob.length > 0){
		//chart start
		var mainArr = [];
		for(var i in casteInfoResultGlob){
			var subArr = [];
			var name = "",count = 0;
			name = casteInfoResultGlob[i].ageRange;
			if(casteInfoResultGlob[i].locationVotersVOList != null && casteInfoResultGlob[i].locationVotersVOList.length > 0){
				for(var j in casteInfoResultGlob[i].locationVotersVOList){
					count = count+casteInfoResultGlob[i].locationVotersVOList[j].femaleVoters+casteInfoResultGlob[i].locationVotersVOList[j].maleVoters;
				}
				subArr.push(name);
				subArr.push(parseInt(count));
				mainArr.push(subArr);
			}
		}
		
		var obj = {
					name: 'Proprietary or Undetectable',
					y: 0.2,
					dataLabels: {
						enabled: false
					}
				}
			mainArr.push(obj);		
		
		$('#casteGroupInfoChartDivId').highcharts({
			chart: {
				plotBackgroundColor: null,
				plotBorderWidth: 0,
				plotShadow: false
			},
			title: {
				text: '',
				align: 'center',
				verticalAlign: 'middle',
				y: 40
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions: {
				pie: {
					dataLabels: {
						enabled: true,
						distance: -50,
						style: {
							fontWeight: 'bold',
							color: 'white'
						}
					},
				   
					center: ['50%', '75%']
				}
			},
			series: [{
				type: 'pie',
				name: 'Browser share',
				innerSize: '50%',
				data:mainArr
			}]
		});
		//chart end
		
		//table start
		var str='';
		str+='<table class="table table-bordered bg-E9">';
		str+='<thead>';
		for(var i in casteInfoResultGlob){
			str+='<th>'+casteInfoResultGlob[i].ageRange+'</th>';
		}
		str+='</thead>';
		str+='<tbody>';
		str+='<tr>';
		for(var i in casteInfoResultGlob){
			str+='<td>'+casteInfoResultGlob[i].totalVoters+'</td>';
		}
		str+='</tr>';
		str+='</tbody>';
		str+='</table>';
		$("#casteGroupTableDivId").html(str);
		//table end				
	}
}

function builsCasteInfoForCadre(){
	if(casteInfoResultGlob != null && casteInfoResultGlob != "" && casteInfoResultGlob.length > 0){
		//chart start
		var mainArr = [];
		for(var i in casteInfoResultGlob){
			var subArr = [];
			var name = "",count = 0;
			name = casteInfoResultGlob[i].ageRange;
			if(casteInfoResultGlob[i].locationVotersVOList != null && casteInfoResultGlob[i].locationVotersVOList.length > 0){
				for(var j in casteInfoResultGlob[i].locationVotersVOList){
					count = count+casteInfoResultGlob[i].locationVotersVOList[j].maleCadres+casteInfoResultGlob[i].locationVotersVOList[j].femaleCadres;
				}
				subArr.push(name);
				subArr.push(parseInt(count));
				mainArr.push(subArr);
			}
		}
		
		var obj = {
					name: 'Proprietary or Undetectable',
					y: 0.2,
					dataLabels: {
						enabled: false
					}
				}
			mainArr.push(obj);		
		
		$('#casteGroupInfoChartDivId').highcharts({
			chart: {
				plotBackgroundColor: null,
				plotBorderWidth: 0,
				plotShadow: false
			},
			title: {
				text: '',
				align: 'center',
				verticalAlign: 'middle',
				y: 40
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions: {
				pie: {
					dataLabels: {
						enabled: true,
						distance: -50,
						style: {
							fontWeight: 'bold',
							color: 'white'
						}
					},
				   
					center: ['50%', '75%']
				}
			},
			series: [{
				type: 'pie',
				name: '',
				innerSize: '50%',
				data:mainArr
			}]
		});
		//chart end
		
		//table start
		var str='';
		str+='<table class="table table-bordered bg-E9">';
		str+='<thead>';
		for(var i in casteInfoResultGlob){
			str+='<th>'+casteInfoResultGlob[i].ageRange+'</th>';
		}
		str+='</thead>';
		str+='<tbody>';
		str+='<tr>';
		for(var i in casteInfoResultGlob){
			str+='<td>'+casteInfoResultGlob[i].totalCadres+'</td>';
		}
		str+='</tr>';
		str+='</tbody>';
		str+='</table>';
		$("#casteGroupTableDivId").html(str);
		//table end				
	}
}

function buildCasteGroupWiseInfo(){
	if(casteInfoResultGlob != null && casteInfoResultGlob != "" && casteInfoResultGlob.length > 0){
		var str='';
		str+='<ul class="nav nav-tabs" role="tablist">';
		for(var i in casteInfoResultGlob){
			if(i==0)
				str+='<li role="presentation" class="active"><a href="#'+casteInfoResultGlob[i].ageRange+'" aria-controls="'+casteInfoResultGlob[i].ageRange+'" role="tab" data-toggle="tab">'+casteInfoResultGlob[i].ageRange+'</a></li>';
			else	
				str+='<li role="presentation"><a href="#'+casteInfoResultGlob[i].ageRange+'" aria-controls="'+casteInfoResultGlob[i].ageRange+'" role="tab" data-toggle="tab">'+casteInfoResultGlob[i].ageRange+'</a></li>';
		}
		str+='</ul>';
		
		str+='<div class="tab-content">';
		str+='<h4 class="panel-title text-capitalize">Caste Wise - Voter & Cadre Information b/w age group</h4>';
		for(var i in casteInfoResultGlob){
			if(i==0)
				str+='<div role="tabpanel" class="tab-pane active pad_10" id="'+casteInfoResultGlob[i].ageRange+'">';
			else
				str+='<div role="tabpanel" class="tab-pane pad_10" id="'+casteInfoResultGlob[i].ageRange+'">';
			str+='<table class="table table-noborder" style="font-size:12px;">';
			str+='<thead class="text-capitalize">';
			str+='<th>Caste Name</th>';
			str+='<th>voters <span class="text-success">%</span></th>';
			str+='<th>cadres <span class="text-success">%</span></th>';
			str+='<th>Male(V) <span class="text-success">%</span></th>';
			str+='<th>Male(C) <span class="text-success">%</span></th>';
			str+='<th>FeMale(V) <span class="text-success">%</span></th>';
			str+='<th>FeMale(C) <span class="text-success">%</span></th>';
			str+='</thead>';
			str+='<tbody>';
			if(casteInfoResultGlob[i].locationVotersVOList != null && casteInfoResultGlob[i].locationVotersVOList.length > 0){
				for(var j in casteInfoResultGlob[i].locationVotersVOList){
					str+='<tr>';
					str+='<td><i class="glyphicon glyphicon-plus td-expand-icon expandCasteIconCls" attr_caste_name="'+casteInfoResultGlob[i].locationVotersVOList[j].ageRange+'" attr_caste_group_id="'+casteInfoResultGlob[i].ageRangeId+'" attr_caste_id="'+casteInfoResultGlob[i].locationVotersVOList[j].ageRangeId+'" collapseid="td-expand-'+casteInfoResultGlob[i].locationVotersVOList[j].ageRangeId+'"></i></td>';
					str+='<td>'+casteInfoResultGlob[i].locationVotersVOList[j].ageRange+'</td>';
					str+='<td>'+casteInfoResultGlob[i].locationVotersVOList[j].totalVoters+' '+casteInfoResultGlob[i].locationVotersVOList[j].totalVotersPerc+'%</td>';
					str+='<td>'+casteInfoResultGlob[i].locationVotersVOList[j].totalCadres+' '+casteInfoResultGlob[i].locationVotersVOList[j].totalCadrePerc+'</td>';
					str+='<td>'+casteInfoResultGlob[i].locationVotersVOList[j].maleVoters+' '+casteInfoResultGlob[i].locationVotersVOList[j].maleVotersPerc+'</td>';
					str+='<td>'+casteInfoResultGlob[i].locationVotersVOList[j].maleCadres+' '+casteInfoResultGlob[i].locationVotersVOList[j].maleCadrePerc+'</td>';
					str+='<td>'+casteInfoResultGlob[i].locationVotersVOList[j].femaleVoters+' '+casteInfoResultGlob[i].locationVotersVOList[j].femaleVotersPerc+'</td>';
					str+='<td>'+casteInfoResultGlob[i].locationVotersVOList[j].femaleCadres+' '+casteInfoResultGlob[i].locationVotersVOList[j].femaleCadrePerc+'</td>';
					str+='</tr>';
					str+='<tr class="td-expand-body" collapseBodyId="td-expand-'+casteInfoResultGlob[i].locationVotersVOList[j].ageRangeId+'">';
					str+='<td colspan="8" class="top-arrow" id="expandCaste'+casteInfoResultGlob[i].locationVotersVOList[j].ageRangeId+'"></td></tr>';
				}
			}
			str+='</tbody>';
			str+='</table>';
			
			str+='</div>';
		}
		str+='</div>';
		$("#casteGroupsTabsDivId").html(str);
	}else{
		$("#casteGroupsTabsDivId").html("No Data Available.");
	}
}/* 
$(document).on("click",".benefitsRefresh",function(){
		getGovtSchemeWiseBenefitMembersCount();
}); */
$(document).on("click",".expandCasteIconCls",function(){
	var casteGroupId = $(this).attr("attr_caste_group_id");
	var casteId = $(this).attr("attr_caste_id");
	var casteName = $(this).attr("attr_caste_name");
	getCasteNAgeWiseVoterNCadreCounts(casteGroupId,casteId,casteName);
});