onLoadAjaxCalls(); //ajaxcalls through newconstituencypage.js
var globalConsId = 232;
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
function onLoadAjaxCalls()
{
	/* getCandidateAndPartyInfoForConstituency();//get candidates inforamtion
	getCountsForConstituency(); //Assembly election details
	getAllPartiesAllElectionResultsChart(); //Assembly Election Detail
	getPrintMediaCountsForConstituencyPage(); //electronic media
	getDetailedGovtOverAllAnalysisProblemsForConstituencyPage(); //problems
	getDetailedGovtOverAllAnalysisProblemsForView();
	getDetailedElectionInformaction()//get detailed election information
	getVotersAndcadreAgeWiseCount();
	getVotersAndCadreCasteWiseCount("voter");
	getVotersAndCadreCasteWiseCount("cadre");
	getCasteGroupNAgeWiseVoterNCadreCounts(); */
	getCasteNAgeWiseVoterNCadreCounts();
	getEnrollmentYearWiseCadres();
	
	//meetings
	getLocationWiseMeetingsCount();
	
	
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
		highChartsDonut('problemsDetailedGraph',data,false);
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

function getCasteGroupNAgeWiseVoterNCadreCounts(){
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
	});	
}

function getCasteNAgeWiseVoterNCadreCounts(){
	jsObj={
		casteGroupId:1,
		casteId:284,
		constituencyId:232,
    	publicationDateId:22
    }
	 $.ajax({
      type : "GET",
      url : "getCasteNAgeWiseVoterNCadreCountsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
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