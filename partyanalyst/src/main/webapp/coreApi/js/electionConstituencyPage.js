getDetailedElectionInformaction();
getLocationWiseElectionResults();
getElectionInformationLocationWiseStatus();
getElectionDetailsData();
function getDetailedElectionInformaction(){
	
	jsObj={
	  	constituencyId: 232
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
		navTabs+='<div class="col-sm-5" style="padding-right: 3px;">';
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

function getLocationWiseElectionResults(){
	
	var jsObj={
		
		electionScopeIdsArr: [1,2,3,4,5,6,7,8,9],
		subType:"MAIN",
		lelevlId:2,
		locationValuesArr:[1],
		yearsArr:[2014],
		partyIdsArr:[872,362,1117,886,72,269,265,163]


    }
    $.ajax({
      type : "GET",
      url : "getLocationWiseElectionResultsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildLocationWiseElectionResults(result);
		}
	});
	
	function buildLocationWiseElectionResults(result){
		
		var str='';
		
		str+='<ul class="list-inline levelWiseCandiRstsCls">';
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
					
					str+='<div class="table-responsive">';	
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
					str+='</div>';
					
				str+='</div>';
			
			str+='</li>';
		}
		str+='</ul>';
		
		$("#levelWiseCandidatesResultsDivId").html(str);
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
function getElectionDetailsData(){
	var jsObj={
		
		locationTypeId:2,
		locationValuesArr:[1],
		electionYear:"2014,2009,2004",
		electionId:0
    }
    $.ajax({
      type : "GET",
      url : "getElectionDetailsDataAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		
	});
}
function getElectionInformationLocationWiseStatus(){
	
	var jsObj={
	  	locationTypeId 		:2,
		locationValue 		:1,
		electionScopeIds	:[2],
		partyIdsList  		:[872],
		electionYears     	:[2014,2009,2004],
		electionSubTypeArr 	:["MAIN"]

    }
    $.ajax({
      type : "GET",
      url : "getElectionInformationLocationWiseStatusAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildElectionInformationLocationWiseStatus(result);
		}
	});
	
	function buildElectionInformationLocationWiseStatus(result){
		
		var str='';
		str+='<div class="table-responsive">';
			str+='<table class="table table-condensed">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Location Name</th>';
							if(result[0].list !=null && result[0].list.length>0){
								for(var j in result[0].list){
									str+='<th>'+result[0].list[j].electionYear+'</th>';
								}
							}
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].locationName+'</td>';
						for(var j in result[i].list){
								str+='<td>';
									str+='<h5>'+result[i].list[j].status+'</h5>';
									str+='<h6>'+result[i].list[j].earnedVotes+' ('+result[i].list[j].perc+')</h6>';
								str+='</td>';
							
						}
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
		$("#locationWiseStrongVsPoor").html(str);
		
	}
}