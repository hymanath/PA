 //ajaxcalls through newconstituencypage.js
var globalConsId = 232;
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
onLoadAjaxCalls();
function onLoadAjaxCalls()
{
	//candidate Profiles 1st block
	getCandidateAndPartyInfoForConstituency();
	//Second Block
	getCountsForConstituency();
	//Constituency Voter Information
	getVotersAndcadreAgeWiseCount();
	
	//caste information
	getCasteGroupNAgeWiseVoterNCadreCounts("voter")
	getActivityStatusList();
}
function getCandidateAndPartyInfoForConstituency(){
   var jsObj={
    	constituencyId: 232
    }
    $.ajax({
      type : "GET",
      url : "getCandidateAndPartyInfoForConstituencyAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			buildCandidateAndPartyInfoForConstituency(result);	
		}	
		
	});
}

function buildCandidateAndPartyInfoForConstituency(result){
	
	if(result !=null){
		if(result.assemblyCandidateInfo !=null && result.assemblyCandidateInfo.length>0){
			var str='';
			for(var i in result.assemblyCandidateInfo){
				str+='<div class="media media-profile">';
					str+='<div class="media-left">';
						str+='<img src="images/candidates/'+result.assemblyCandidateInfo[i].candidateName+'.jpg" class="media-object profile-image img-border" alt="profile"/>';
						str+='<span class="border-image img-border">';
							str+='<img src="images/party_flags/'+result.assemblyCandidateInfo[i].partyFlag+'" alt="party"/>';
						str+='</span>';
					str+='</div>';
					str+='<div class="media-body">';
						str+='<h4 class="m_top20 text-success text-capital">'+result.assemblyCandidateInfo[i].candidateName+'</h4>';
						str+='<p class="text-muted">MLA<span class="text-capitalize"> [Member of legislative assembly]</span></p>';
					str+='</div>';
				str+='</div>';
			}
			
			$("#assemblyMemberId").html(str);
		}
		if(result.parliamentCandidateInfo !=null){
			var str1='';
			str1+='<div class="media media-profile">';
				str1+='<div class="media-left">';
					str1+='<img src="images/candidates/'+result.parliamentCandidateInfo.candidateName+'.jpg" class="media-object profile-image img-border" alt="profile"/>';
					str1+='<span class="border-image img-border">';
						str1+='<img src="images/party_flags/'+result.parliamentCandidateInfo.partyFlag+'" alt="party"/>';
					str1+='</span>';
				str1+='</div>';
				str1+='<div class="media-body">';
					str1+='<h4 class="m_top20 text-success text-capital">'+result.parliamentCandidateInfo.candidateName+'</h4>';
					str1+='<p class="text-muted">MLA<span class="text-capitalize"> [Member of legislative assembly]</span></p>';
				str1+='</div>';
			str1+='</div>';
			$("#parliamentMemberId").html(str1);
		}
		
	}	
}

function getCountsForConstituency(){
	
	var tehsilId=0;
	var publicationDateId = 22;
	var type = 'constituency';
	id = 232;
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
		if(results !=null){
			buildCountsForConstituency(results);
		}
		
	});		
		 			
}
function buildCountsForConstituency(results){
	var str='';
	
	str+='<table class="table table-bordered block">';
		str+='<tr>';
		for(var i in results){
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Mandals</h4>';
				if(results[i].totalmandals !=null && results[i].totalmandals>0){
					str+='<h2>'+results[i].totalmandals+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Municipalities</h4>';
				if(results[i].noOfLocalBodies !=null && results[i].noOfLocalBodies>0){
					str+='<h2>'+results[i].noOfLocalBodies+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Panchayats</h4>';
				if(results[i].totalPanchayats !=null && results[i].totalPanchayats>0){
					str+='<h2>'+results[i].totalPanchayats+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Wards</h4>';
				if(results[i].totalNoOfWards !=null && results[i].totalNoOfWards>0){
					str+='<h2>'+results[i].totalNoOfWards+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Booths</h4>';
				if(results[i].totalBooths !=null && results[i].totalBooths>0){
					str+='<h2>'+results[i].totalBooths+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
			str+='<td>';
				str+='<h4 class="text-capitalize text-muted">Hamlets</h4>';
				if(results[i].totalNoOfHamlets !=null && results[i].totalNoOfHamlets>0){
					str+='<h2>'+results[i].totalNoOfHamlets+'</h2>';
				}else{
					str+='<h2> - </h2>';
				}
				str+='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
			str+='</td>';
		}
		str+='</tr>';
	str+='</table>';
	
	$("#levelWiseCountDivId").html(str);
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
		if(result !=null && result.length>0){
			buildVotersAndcadreAgeWiseCount(result);
		}
		
  });
}
function buildVotersAndcadreAgeWiseCount(result){
	
	if(result !=null && result.length>0){
		var theadArr=['total population','total Voters','total Cadre','Male Voter','Male Cadre','Female Voter','Female Cadre'];
		var totalVotersCount =0;
		var totalCadresCount =0;
		var totalMaleVoterCount=0;
		var totalMaleCadreCount=0;
		var totalFemaleVoterCount=0;
		var totalFemaleCadreCount=0;
		var countVar1 =0;
		var countVar =0;
		var str='';
			for(var i in result){
					totalVotersCount =totalVotersCount+result[i].totalVoters;
					totalCadresCount =totalCadresCount+result[i].totalCadres;
					totalMaleVoterCount =totalMaleVoterCount+result[i].maleVoters;
					totalMaleCadreCount =totalMaleCadreCount+result[i].maleCadres;
					totalFemaleVoterCount =totalFemaleVoterCount+result[i].femaleVoters;
					totalFemaleCadreCount =totalFemaleCadreCount+result[i].femaleCadres;
					countVar1 =countVar1+1;
					if (countVar1 === 6) {
						break;
					}
			}
			str+='<div class="block">';
				str+='<table class="table tableVoters">';
					str+='<thead>';
					for(var i in theadArr){
						str+='<th>';
							if(theadArr[i] == "total Cadre" || theadArr[i] == "Male Cadre" || theadArr[i] =="Female Cadre"){
								str+='<img src="coreApi/img/tableHead1.png" alt="cadres"/>';
							}else{
								str+='<img src="coreApi/img/tableHead.png" alt="voters"/>';
							}
							str+='<h4 class="text-capitalize">'+theadArr[i]+'</h4>';
							if(theadArr[i] == "total population"){
								str+='<h3>12000</h3>';
							}else if(theadArr[i] == "total Voters"){
								str+='<h3>'+totalVotersCount+'</h3>';
							}else if(theadArr[i] == "total Cadre"){
								str+='<h3>'+totalCadresCount+'</h3>';
							}else if(theadArr[i] == "Male Voter"){
								str+='<h3>'+totalMaleVoterCount+'</h3>';
							}else if(theadArr[i] == "Male Cadre"){
								str+='<h3>'+totalMaleCadreCount+'</h3>';
							}else if(theadArr[i] == "Female Voter"){
								str+='<h3>'+totalFemaleVoterCount+'</h3>';
							}else if(theadArr[i] == "Female Cadre"){
								str+='<h3>'+totalFemaleCadreCount+'</h3>';
							}
						str+='</th>';
					}
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						str+='<tr>';
							str+='<td>';
								str+='<b>'+result[i].ageRange+'</b>';
								/* str+='2000';
								str+='<span class="pull-right text-success">';
									str+='2%';
								str+='</span>'; */
							str+='</td>';
							str+='<td>';
								str+=''+result[i].totalVoters+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].totalVotersPerc+'';
								str+='</span>';
							str+='</td>';
							str+='<td>';
								str+=''+result[i].totalCadres+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].totalCadrePerc+'';
								str+='</span>';
							str+='</td>';
							str+='<td>';
								str+=''+result[i].maleVoters+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].maleVotersPerc+'';
								str+='</span>';
							str+='</td>';
							str+='<td>';
								str+=''+result[i].maleCadres+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].maleCadrePerc+'';
								str+='</span>';
							str+='</td>';
							str+='<td>';
								str+=''+result[i].femaleVoters+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].femaleVotersPerc+'';
								str+='</span>';
							str+='</td>';
							str+='<td>';
								str+=''+result[i].femaleCadres+'';
								str+='<span class="pull-right text-success">';
									str+=''+result[i].femaleCadrePerc+'';
								str+='</span>';
							str+='</td>';
						str+='</tr>';
						countVar =countVar+1;
						if (countVar === 6) {
							break;
						}
					}
						
					str+='</tbody>';
				str+='</table>';
				str+='<div class="row m_top20">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 text-right">';
						str+='<button class="btn btn-success btn-success-white text-capital">detailed information</button>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
			$("#constituencyVoterInfo").html(str);
	}
	
}
function getCasteGroupNAgeWiseVoterNCadreCounts(groupType){
	
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
		if(result !=null && result.length>0){
			buildCasteGroupNAgeWiseVoterNCadreCounts(result,groupType);
		}
	});	
}
$(document).on("click","[role='casteGrouplist'] li",function(){	
		var groupType = $(this).attr("attr_type");
		getCasteGroupNAgeWiseVoterNCadreCounts(groupType)
});
function buildCasteGroupNAgeWiseVoterNCadreCounts(result,groupType){
	
	var str='';
		str+='<table class="table table-bordered bg-E9">';
			str+='<thead>';
			str+='<tr>';
			for(var i in result){
				str+='<th>'+result[i].ageRange+'</th>';
			}
			str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			str+='<tr>';
			for(var i in result){
				if(groupType == "voter"){
					str+='<td>'+result[i].totalVoters+'</td>';
				}else{
					str+='<td>'+result[i].totalCadres+'</td>';
				}
					
			}
			str+='</tr>';	
			str+='</tbody>';
		str+='</table>';
		$("#leftSideCasteGroupWiseDivId").html(str);
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
		str+='<div class="pad_10">'
		str+='<table class="table table-striped">';
		str+='<thead class="text-capitalize bg-E9">';
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
		str+='<tr><td><button class="btn btn-success btn-success-white text-capital">detailed information</button><td></tr>';
		str+='</tbody>';
		str+='</table>';
		str+='</div>'
		$("#activitesId").html(str);
	});	
}