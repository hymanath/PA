var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var eletionSubType=["MAIN"];
var globalStrongPoorColor={"VERY STRONG":'#428AE9',"OK":"#009900","POOR":"#FFCC00","VERY POOR":"#FF9966","WORST":"#CC6600","STRONG":"#FF3E3E"}
var electionYrVal = [];
setTimeout(function(){
	onLoadCalls();
},1500)

function onLoadCalls()
{
	$("#mianHeadingId").html(locationLevelName)
	if(locationLevelId == "2"){
		$(".searchLevelCls").show();
		$(".elctionTypeValCls").show();
	}else{
		$(".searchLevelCls").hide();
		$(".elctionTypeValCls").show();
	}
	$(".chosen-select").chosen();
	if(locationLevelId == '4'){
		getDetailedElectionInformaction();
	}
	getElectionYears(eletionSubType,"onload")
	
}

function getDetailedElectionInformaction(){
	$("#candidatesResultsDivId").html(spinner);
	jsObj={
	  	constituencyId: constituencyId
    }
    $.ajax({
      type : "GET",
      url : "getDetailedElectionInformactionAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildDetailedElectionInformaction(result);
		}
	});
	
	function buildDetailedElectionInformaction(result){
		var navTabs = '';
		navTabs+='<div class="block">';
			navTabs+='<h4 class="theme-title-color">Assembly Election Year Wise Details</h4>';
		navTabs+='<div class="row">';
			navTabs+='<div class="col-sm-5" style="padding-right: 10px;">';
				navTabs+='<div class="panel panel-default panel-border">';
					navTabs+='<div class="panel-heading winnersColor" >';
						navTabs+='<div class="media">';
							navTabs+='<div class="media-left">';
								navTabs+='<img src="coreApi/img/winner_icon.png" alt="winner_icon"></img>';
							navTabs+='</div>';
							navTabs+='<div class="media-body">';
								navTabs+='<h3 class="m_top20">Winners</h3>';
							navTabs+=' </div>';
						navTabs+='</div>';
					navTabs+='</div>';
					navTabs+='<div class="panel-body">';
						navTabs+='<ul class="nav nav-tabs nav-tabs-horizontal_Elec" role="tablist">';
							for(var i in result){
								if(i == 0){
									navTabs+='<li class="active">';
										navTabs+='<a href="#candidatesResult'+i+'" navTabs-click="" aria-controls="candidatesResult'+i+'" role="tab" data-toggle="tab">';
											navTabs+='<div class="media">';
												navTabs+='<div class="media-left">';
													navTabs+='<div class="row">';
															navTabs+='<div class="col-sm-5">';
																navTabs+='<h4 class="m_top10">'+result[i].electionYear+'</h4>';
															navTabs+=' </div>';
															navTabs+='<div class="col-sm-5">';
															if(result[i].candidateResultsVO.partyShortName == "TDP" || result[i].candidateResultsVO.partyShortName == "YSRC"){
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.PNG" alt="'+result[i].candidateResultsVO.partyShortName+'"></img>';
															}else{
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="'+result[i].candidateResultsVO.partyShortName+'"></img>';
															}
																navTabs+='<p class="">'+result[i].candidateResultsVO.partyShortName+'</p>';
															navTabs+=' </div>';
													navTabs+='</div>';
													
												navTabs+='</div>';
													navTabs+='<div class="media_body_custom">';
														navTabs+='<h5>'+result[i].candidateResultsVO.candidateName+'</h5>';
															navTabs+='<div class="row m_top5">';
																navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Votes&nbsp;Earned</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesEarned+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentage+'%</span></h6>';
															navTabs+='</div>';
															navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Margin&nbsp;Gained</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesMargin+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentMargin+'%</span></h6>';
															navTabs+='</div>';
														navTabs+=' </div>';
													navTabs+=' </div>';
											navTabs+='</div>';
										navTabs+='</a>';
									navTabs+='</li>'; 
								}else{
									navTabs+='<li class="">';
										navTabs+='<a href="#candidatesResult'+i+'" navTabs-click="" aria-controls="candidatesResult'+i+'" role="tab" data-toggle="tab">';
											navTabs+='<div class="media">';
												navTabs+='<div class="media-left">';
													navTabs+='<div class="row">';
															navTabs+='<div class="col-sm-5">';
																navTabs+='<h4 class="m_top10">'+result[i].electionYear+'</h4>';
															navTabs+=' </div>';
															navTabs+='<div class="col-sm-5">';
															if(result[i].candidateResultsVO.partyShortName == "TDP" || result[i].candidateResultsVO.partyShortName == "YSRC"){
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.PNG" alt="'+result[i].candidateResultsVO.partyShortName+'"></img>';
															}else{
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="'+result[i].candidateResultsVO.partyShortName+'"></img>';
															}
																navTabs+='<p class="">'+result[i].candidateResultsVO.partyShortName+'</p>';
															navTabs+=' </div>';
													navTabs+='</div>';
													
												navTabs+='</div>';
													navTabs+='<div class="media_body_custom">';
														navTabs+='<h5>'+result[i].candidateResultsVO.candidateName+'</h5>';
															navTabs+='<div class="row m_top5">';
																navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Votes&nbsp;Earned</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesEarned+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentage+'%</span></h6>';
															navTabs+='</div>';
															navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Margin&nbsp;Gained</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesMargin+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentMargin+'%</span></h6>';
															navTabs+='</div>';
														navTabs+=' </div>';
													navTabs+=' </div>';
											navTabs+='</div>';
										navTabs+='</a>';
									navTabs+='</li>';
								}
							}
							
							
						navTabs+='</ul>';
					navTabs+='</div>';
				navTabs+='</div>';
			navTabs+='</div>';
			
				
			
			navTabs+='<div class="col-sm-7 pad_left0">';
				navTabs+='<div class="panel panel-default panel-border">';
					navTabs+='<div class="panel-heading looserColor" >';
					
						navTabs+='<div class="media">';
							navTabs+='<div class="media-left">';
								navTabs+='<img src="coreApi/img/loser_icon.png" alt="loser_icon"></img>';
							navTabs+='</div>';
							navTabs+='<div class="media-body">';
								navTabs+='<h3 class="m_top20">Losers</h3>';
							navTabs+=' </div>';
						navTabs+='</div>';
						
					navTabs+='</div>';
					navTabs+='<div class="panel-body panel_body_custom">';
						navTabs+='<div class="tab-content">';
								for(var i in result){
									if(i == 0){
										navTabs+='<div role="tabpanel" class="tab-pane active pad_10 block" id="candidatesResult'+i+'" style="border-radius: 10px;">';
									}else{
										navTabs+='<div role="tabpanel" class="tab-pane pad_10 block" id="candidatesResult'+i+'" style="border-radius: 10px;">';
									}
										navTabs+='<div class="table-responsive">';
										navTabs+='<table class="table table-condensed table_padding" id="candidatesResultsDT">';
											navTabs+='<thead>';
												navTabs+='<tr>';
													navTabs+='<th>Candidate Name</th>';
													navTabs+='<th>Party</th>';
													navTabs+='<th>Party Flag</th>';
													navTabs+='<th>Votes Earned</th>';
													navTabs+='<th>Earned %</th>';
													navTabs+='<th>Rank</th>';
												navTabs+='</tr>';
											navTabs+='</thead>';
											
											navTabs+='<tbody>';
												for(var j in result[i].candidateOppositionList){
													navTabs+='<tr>';
														if(result[i].candidateOppositionList[j].candidateName !=null && result[i].candidateOppositionList[j].candidateName.length>10){
															navTabs+='<td><span class="candidate_rounded">'+result[i].candidateOppositionList[j].candidateName.charAt(0)+'</span><span class="tooltipCls" style="cursor:pointer; data-toggle="tooltip" data-placement="right" title="'+result[i].candidateOppositionList[j].candidateName+'">'+result[i].candidateOppositionList[j].candidateName.substring(0,10)+'...</span></td>';
														}else{
															navTabs+='<td><span class="candidate_rounded">'+result[i].candidateOppositionList[j].candidateName.charAt(0)+'</span>'+result[i].candidateOppositionList[j].candidateName+'</td>';
														}
														
														
														navTabs+='<td>'+result[i].candidateOppositionList[j].partyShortName+'</td>';
														
														if(result[i].candidateOppositionList[j].partyShortName == "TDP" || result[i].candidateOppositionList[j].partyShortName == "YSRC"){
															navTabs+='<td><img class="" src="images/party_flags/'+result[i].candidateOppositionList[j].partyShortName+'.PNG" alt="'+result[i].candidateOppositionList[j].partyShortName+'"></img></td>';
														}else{
															navTabs+='<td><img class="" src="images/party_flags/'+result[i].candidateOppositionList[j].partyShortName+'.png" alt="'+result[i].candidateOppositionList[j].partyShortName+'"></img></td>';
														}
														
														navTabs+='<td>'+result[i].candidateOppositionList[j].votesEarned+'</td>';
														navTabs+='<td>'+result[i].candidateOppositionList[j].votesPercentage+'</td>';
														navTabs+='<td>'+result[i].candidateOppositionList[j].rank+'</td>';
													navTabs+='</tr>';
												}
											navTabs+='</tbody>';
										navTabs+='</table>';
										navTabs+='</div>';
									navTabs+='</div>';
								}	
								
							
						navTabs+='</div>'; 
					navTabs+='</div>';
				navTabs+='</div>';
			navTabs+='</div>';
		navTabs+='</div>';
		navTabs+='</div>';
		$("#candidatesResultsDivId").html(navTabs);
		$(".tooltipCls").tooltip();
		$("#candidatesResultsDT").dataTable({
			"paging":   true,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}
}

function getLocationWiseElectionResults(electionYrVal,eletionSubType){
	$("#levelWiseCandidatesResultsDivId").html(spinner);
	var jsObj={
		
		electionScopeIdsArr: [1,2,3,4,5,6,7,8,9],
		electionSubTypeArr:eletionSubType,
		lelevlId:locationLevelId,
		locationValuesArr:userAccessLevelValuesArray,
		yearsArr:electionYrVal,
		partyIdsArr:[872,362,1117,886,72,269,265,163,1887]
    }
    $.ajax({
      type : "GET",
      url : "getLocationWiseElectionResultsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildLocationWiseElectionResults(result);
		}else{
			$("#levelWiseCandidatesResultsDivId").html("No Data Available");
		}
	});
	
	function buildLocationWiseElectionResults(result){
		
		var str='';
		str+='<h4 class="text-capital">'+locationLevelName+' level party wise election trends</h4>';
		str+='<ul class="list-inline levelWiseCandiRstsCls m_top10">';
		for(var i in result){
			str+='<li style="margin-right:15px;margin-left:15px;">';
				str+='<div class="block">';
					str+='<h4>'+result[i].electionType+' Election Results</h4>';
					str+='<p>'+result[i].electionYear+'</p>';
					
					str+='<div class="bg_ED" style="padding:10px;">';
						str+='<div class="row">';
							str+='<div class="col-sm-5">';
								str+='<h6>Total Seats: '+result[i].totalSeatsCount+'</h6>';
							str+='</div>';
							str+='<div class="col-sm-7">';
								str+='<h6>Participated Seats: '+result[i].participatedSeatsCount+'</h6>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					if(result[i].list.length > 4)
					{
						str+='<div class="table-scroll">';	
					}
					
						str+='<table class="table table-condensed table_custom" id="dataTablelevelWiseBlock">';
							str+='<thead>';
								str+='<tr>';
									str+='<th>Party</th>';
									str+='<th class="text-center">Seats(P)</th>';
									str+='<th class="text-center">Won</th>';
									str+='<th class="text-center">Voting%</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var j in result[i].list){
									str+='<tr>';
										if(result[i].list[j].partyName == "TDP" || result[i].list[j].partyName == "YSRC"){
											str+='<td><p><img class="" src="images/party_flags/'+result[i].list[j].partyName+'.PNG" alt="'+result[i].list[j].partyName+'"></img>  '+result[i].list[j].partyName+'</p></td>';
										}else{
											str+='<td><p><img class="" src="images/party_flags/'+result[i].list[j].partyName+'.png" alt="'+result[i].list[j].partyName+'"></img>   '+result[i].list[j].partyName+'</p></td>';
										}
										str+='<td class="text-center">'+result[i].list[j].totalSeatsCount+'</td>';
										str+='<td class="text-center">'+result[i].list[j].wonSeatsCount+'</td>';
										str+='<td class="text-center">'+result[i].list[j].perc+' %</td>';
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table>';
					if(result[i].list.length > 6)
					{
						str+='</div>';
					}
					
				str+='</div>';
			
			str+='</li>';
		}
		str+='</ul>';
		
		$("#levelWiseCandidatesResultsDivId").html(str);
		$(".table-scroll").mCustomScrollbar({setHeight:'254px'});
		$("#dataTablelevelWiseBlock").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"sDom": '<"top"iflp>rt<"bottom"><"clear">'
		});
		$('.levelWiseCandiRstsCls').slick({
				slide: 'li',
				slidesToShow: 3,
				slidesToScroll: 1,
				infinite: false,
				swipe:false,
				touchMove:false,
				variableWidth: false,
				responsive: [
					{
						breakpoint: 1024,
						settings: {
							slidesToShow: 3,
							slidesToScroll: 3,
						}
					},
					{
						breakpoint: 600,
						settings: {
							slidesToShow: 2,
							slidesToScroll: 2
						}
					},
					{
						breakpoint: 480,
						settings: {
							slidesToShow: 1,
							slidesToScroll: 1
						}
					}
				]
			});
		
	}
}
function getElectionDetailsData(electionYrVal,eletionSubType){
	$("#locationWiseCandidatesResultsDivId").html(spinner);
	var jsObj={
		locationTypeId     	:locationLevelId,
		locationValuesArr   :userAccessLevelValuesArray,
		electionId       	:258,
		electionYearArr     :electionYrVal,
		partyIdsArr       	:[872,362,1117,886,72,269,265,163,1887],
		electionSubTypeArr  :eletionSubType
    }
    $.ajax({
      type : "GET",
      url : "getElectionDetailsDataAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildElectionDetailsData(result);
		}
	});
	
	function buildElectionDetailsData(result){
		
		var str='';
		str+='<div class="block">';
			if(locationLevelId == '2')
			{
				str+='<h4 class="text-capital">District Level Party Election Results</h4>';
			}else if(locationLevelId == '3')
			{
				str+='<h4 class="text-capital">District Level Party Election Results</h4>';		
			}else if(locationLevelId == '10')
			{
				str+='<h4 class="text-capital">Parliament Level Party Election Results</h4>';
			}else if(locationLevelId == '4')
			{
				str+='<h4 class="text-capital">Constituency Level Party Election Results</h4>';
			}else if(locationLevelId == '5')
			{
				str+='<h4 class="text-capital">Mandal Level Party Election Results</h4>';
			}else if(locationLevelId == '6')
			{
				str+='<h4 class="text-capital">Panchayat Level Party Election Results</h4>';
			}else
			{
				str+='<h4 class="text-capital">Location Level Party Election Results</h4>';
			}
			
			str+='<div class="table-responsive m_top10">';
				str+='<table class="table tableAlignmentElecResults table-condensed" id="dataTableElecTypeRsts">';
					str+='<thead>';
						str+='<tr>';
							if(locationLevelId == '2')
							{
								str+='<th rowspan="2">District Name</th>';
							}else if(locationLevelId == '3')
							{
								str+='<th rowspan="2">District Name</th>';	
							}else if(locationLevelId == '10')
							{
								str+='<th rowspan="2">Parliament Name</th>';
							}else if(locationLevelId == '4')
							{
								str+='<th rowspan="2">Constituency Name</th>';
							}else if(locationLevelId == '5')
							{
								str+='<th rowspan="2">Mandal Name</th>';
							}else if(locationLevelId == '6')
							{
								str+='<th rowspan="2">Panchayat Name</th>';
							}else if(locationLevelId == '7')
							{
								str+='<th rowspan="2">Location Name</th>';
							}else
							{
								str+='<th rowspan="2">Location Name</th>';
							}
							
							if(result[0].subList1 !=null && result[0].subList1.length>0){
								for(var i in result[0].subList1){
									str+='<th colspan="'+result[0].subList1[i].subList1.length+'" class="text-center">'+result[0].subList1[i].electionYear+' ('+result[0].subList1[i].electionType+')</th>';
									
								}
							}
								
						str+='</tr>';
						str+='<tr>';
							if(result[0].subList1 !=null && result[0].subList1.length>0){
								for(var i in result[0].subList1){
									if(result[0].subList1[i].subList1 !=null && result[0].subList1[i].subList1.length>0){
										for(var j in result[0].subList1[i].subList1){
											if(result[0].subList1[i].subList1[j].partyName == "TDP" || result[0].subList1[i].subList1[j].partyName == "YSRC"){
												str+='<th class="text-center"><img class="" src="images/party_flags/'+result[0].subList1[i].subList1[j].partyName+'.PNG" alt="'+result[0].subList1[i].subList1[j].partyName+'"></img></th>';
											}else{
												str+='<th class="text-center"><img class="" src="images/party_flags/'+result[0].subList1[i].subList1[j].partyName+'.png" alt="'+result[0].subList1[i].subList1[j].partyName+'"></img></th>';
											}
											str+='<th class="text-center">%</th>';
										}
									}
								}
							}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
							str+='<tr>';
								str+='<td>'+result[i].locationName+'</td>';
								for(var j in result[i].subList1){
									if(result[i].subList1[j].subList1 !=null && result[i].subList1[j].subList1.length>0){
										for(var j in result[i].subList1[j].subList1){
											if(result[i].subList1[j].subList1[j].earnedVote !=null && result[i].subList1[j].subList1[j].earnedVote>0){
												str+='<td>'+result[i].subList1[j].subList1[j].earnedVote+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList1[j].subList1[j].perc !=null && result[i].subList1[j].subList1[j].perc>0){
												str+='<td>'+result[i].subList1[j].subList1[j].perc+'</td>';
											}else{
												str+='<td> - </td>';
											}
											
										}
									}
								}
								
							str+='</tr>';
						}
					str+='</tbody>';
			str+='</div>';
		str+='</div>';
		
		$("#locationWiseCandidatesResultsDivId").html(str);
		$("#dataTableElecTypeRsts").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}
}
function getElectionInformationLocationWiseStatus(eletionSubType,electionYrVal,partyId,searchLevelVal,electionTypeVal,partyName,electionTypetext){
	$("#locationWiseStrongVsPoor").html(spinner);
	
	var partyIdsList=[];
	var electionScopeIds=[];
	if(partyId !=0){
		partyIdsList.push(partyId)
	}else{
		partyIdsList=[]
	}
	electionScopeIds.push(electionTypeVal);
	
	var jsObj={
	  	locationTypeId 		:locationLevelId,
		locationValue 		:userAccessLevelValue,
		electionScopeIds	:electionScopeIds,
		partyIdsList  		:partyIdsList,
		electionYears     	:electionYrVal,
		electionSubTypeArr 	:eletionSubType,
		searchType			:searchLevelVal
    }
    $.ajax({
      type : "GET",
      url : "getElectionInformationLocationWiseStatusAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildElectionInformationLocationWiseStatus(result,electionTypeVal,searchLevelVal,partyName,electionTypetext);
		}else{
			$("#locationWiseStrongVsPoor").html("No Data Available");
		}
	});
	
	function buildElectionInformationLocationWiseStatus(result,electionTypeVal,searchLevelVal,partyName,electionTypetext){
		
		var str='';
		if(locationLevelId == '2')
		{
			if(electionTypeVal == "2" && searchLevelVal == "constituency" || searchLevelVal == "parliament"){
				str+='<h4 class="text-capital">Constituency Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
			}else if(electionTypeVal == "3" && searchLevelVal =="constituency"){
				str+='<h4 class="text-capital">Panchayat Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
			}else if(electionTypeVal == "4" && searchLevelVal == "constituency"){
				str+='<h4 class="text-capital">Mandal Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
			}else{
				str+='<h4 class="text-capital">District Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
			}
			
		}else if(locationLevelId == '3')
		{
			if(electionTypeVal == "3"){
				str+='<h4 class="text-capital">Mandal Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
			}else if(electionTypeVal == "4"){
				str+='<h4 class="text-capital"> Panchayat '+electionTypetext+' Level Election Comparision Results For '+partyName+' Party</h4>';
			}else{
				str+='<h4 class="text-capital">Constituency '+electionTypetext+' Level Election Comparision Results For '+partyName+' Party</h4>';
			}
			
		}else if(locationLevelId == '10')
		{
			str+='<h4 class="text-capital">Constituency Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}else if(locationLevelId == '4' || electionTypeVal == "3")
		{
			str+='<h4 class="text-capital">Mandal Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}else if(locationLevelId == '5' || electionTypeVal == "4")
		{
			str+='<h4 class="text-capital">panchayat Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}else if(locationLevelId == '6')
		{
			str+='<h4 class="text-capital">Panchayat Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}else if(locationLevelId == '7' || electionTypeVal == "5")
		{
			str+='<h4 class="text-capital">Municipality Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}else
		{
			str+='<h4 class="text-capital">Location Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}
		
		str+='<div class="table-responsive m_top10">';
		
		str+='<table class="table table-condensed table-bordered" >';
		str+='<tr>';
		str+='<th style="background-color:#CC6600;">WORST %</th>';
		str+='<th style="background-color:#FF9966;">VERY POOR %</th>';
		str+='<th style="background-color:#FFCC00;">POOR %</th>';
		str+='<th style="background-color:#009900;">OK %</th>';
		str+='<th style="background-color:#FF3E3E;">STRONG %</th>';
		str+='<th style="background-color:#428AE9;">VERY STRONG %</th>';
		str+='</tr>';
		str+='<th> 0 - 30 </th>';
		str+='<th> 31 - 50 </th>';
		str+='<th> 51 - 60 </th>';
		str+='<th> 61 - 80 </th>';
		str+='<th> 81 - 90 </th>';
		str+='<th> 91 - 100 </th>';
		str+='</tr>';
		str+='</table>';
		str+='</div>';
		
		str+='<div class="table-responsive m_top10">';
			str+='<table class="table table-election" id="electionResults">';
				str+='<thead>';
					str+='<tr>';
						if(locationLevelId == '2')
						{
							if(electionTypeVal == "2" && searchLevelVal == "constituency" || searchLevelVal == "parliament"){
								str+='<th>Constituency Name</th>';
							}else if(electionTypeVal == "3" && searchLevelVal =="constituency"){
								str+='<th>Panchayat Name</th>';
							}else if(electionTypeVal == "4" && searchLevelVal == "constituency"){
								str+='<th>Mandal Name</th>';
							}else{
								str+='<th>District Name</th>';
							}
							
						}else if(locationLevelId == '3')
						{
							if(electionTypeVal == "3"){
								str+='<th>Mandal Name</th>';
							}else if(electionTypeVal == "4"){
								str+='<th>Panchayat Name</th>';
							}else{
								str+='<th>Constituency Name</th>';
							}
							
						}else if(locationLevelId == '10')
						{
							str+='<th>Constituency Name</th>';
						}else if(locationLevelId == '4' || electionTypeVal == "3")
						{
							str+='<th>Mandal Name</th>';
						}else if(locationLevelId == '5' || electionTypeVal == "4")
						{
							str+='<th>Panchayat Name</th>';
						}else if(locationLevelId == '6')
						{
							str+='<th>Panchayat Name</th>';
						}else if(locationLevelId == '7' || electionTypeVal == "5")
						{
							str+='<th>Municipality Name</th>';
						}else
						{
							str+='<th>Location Name</th>';
						}
						
						if(result[0].list !=null && result[0].list.length>0){
							for(var j in result[0].list){
								str+='<th style="min-width:210px;">Election Year<h3>'+result[0].list[j].electionYear+'</h3>';
									str+='<div class="row">';
										for(var k in result[0].list[j].subList1){
											str+='<div class="col-sm-2">';
											if(result[0].list[j].subList1[k].wonSeatsCount !=null && result[0].list[j].subList1[k].wonSeatsCount>0){
												str+='<span class="statusClr" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+'">'+result[0].list[j].subList1[k].wonSeatsCount+'</span>';
											}else{
												str+='<span class="statusClr" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+'">0</span>';
											}
												
											str+='</div>';
										}
										
										
									str+='</div>';
								str+='</th>';
							}
						}
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].locationName+'</td>';
						for(var j in result[i].list){							
							if(result[i].list[j].status == 'STRONG')
							{
								str+='<td style="background-color:#FF3E3E">';
							}else if(result[i].list[j].status == 'VERY STRONG')
							{
								str+='<td style="background-color:#428AE9">';
							}else if(result[i].list[j].status == 'OK')
							{
								str+='<td style="background-color:#009900">';
							}else if(result[i].list[j].status == 'POOR')
							{
								str+='<td style="background-color:#FFCC00">';
							}else if(result[i].list[j].status == 'VERY POOR')
							{
								str+='<td style="background-color:#FF9966">';
							}else if(result[i].list[j].status == 'WORST')
							{
								str+='<td style="background-color:#CC6600">';
							}else{
								str+='<td class="text-center">';
							}
									str+='<h5 >'+result[i].list[j].status+'</h5>';
									str+='<h6>Earned Votes:'+result[i].list[j].earnedVotes+' ('+result[i].list[j].perc+'%)</h6>';
								str+='</td>';
							
						}
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
		$("#locationWiseStrongVsPoor").html(str);
		$("#electionResults").dataTable();
	}
}
$(document).on("click",".electionSubTypeCls",function(){
	eletionSubType=[];
		if ($(this).is(':checked')){
			eletionSubType.push($(this).val());
		}
	getElectionYears(eletionSubType,"change");
});
function getElectionYears(eletionSubType,type){
	 $('#electionYearId, #electionYearId1').html('');
	 
  var jsObj={
      electionSubTypeArr:eletionSubType
    }
    $.ajax({   
      type:'GET',
      url:'getElectionYearsAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.imageList !=null && result.imageList.length>0){
			var str='';
			for(var i in result.imageList){
				str+='<option value="'+result.imageList[i]+'" selected>'+result.imageList[i]+'</option>';
					
			} 
			$('#electionYearId,#electionYearId1').html(str);
			$('#electionYearId,#electionYearId1').multiselect({
				enableFiltering: true,
				includeSelectAllOption: true,
				selectAllText: 'All Election Years',
				maxHeight: 300,
				maxWidth: 300,
				dropDown: true,
				selectAllNumber: true,
				allSelectedText: 'All Election Years selected'
			});
			$("#electionYearId,#electionYearId1").multiselect("refresh");
			
		}
		if(type == "onload"){
			electionYrVal=[];
			electionYrVal = $("#electionYearId").val();
			if(locationLevelId == "2"){
				
				getElectionInformationLocationWiseStatus(eletionSubType,electionYrVal,"872","district","2","TDP","Assembly");
			}else{
				
				getElectionInformationLocationWiseStatus(eletionSubType,electionYrVal,"872","district","2","TDP","Assembly");
			}
			
			getLocationWiseElectionResults(electionYrVal,eletionSubType);
			getElectionDetailsData(electionYrVal,eletionSubType);
		}
		
    });
  }
$(document).on("click",".getDetailsCls",function(){
	var searchLevelVal = $("#searchLevelId").val();
	var electionTypeVal = $("#elctionTypeValId").val();
	var partyId = $("#partyId").val();
	var partyName = $("#partyId option:selected").text();
	var electionTypetext = $("#elctionTypeValId option:selected").text();
	
	eletionSubType=[];
	 $('.electionSubTypeCls').each(function(){
		if ($(this).is(':checked')){
			eletionSubType.push($(this).val());
		}
	 });
	electionYrVal=[];
		electionYrVal = $("#electionYearId").val();
			
	getElectionInformationLocationWiseStatus(eletionSubType,electionYrVal,partyId,searchLevelVal,electionTypeVal,partyName,electionTypetext);
});

$(document).on("click",".getDetailsCls1",function(){
	
	var partyId = $("#partyId").val();
	
	eletionSubType=[];
	 $('.electionSubTypeCls1').each(function(){
		if ($(this).is(':checked')){
			eletionSubType.push($(this).val());
		}
	 });
	electionYrVal=[];
		electionYrVal = $("#electionYearId1").val();
			
	getLocationWiseElectionResults(electionYrVal,eletionSubType);
	getElectionDetailsData(electionYrVal,eletionSubType);
});

  