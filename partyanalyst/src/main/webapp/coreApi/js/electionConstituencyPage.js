getDetailedElectionInformaction();
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
													navTabs+='<td><span class="candidate_rounded">'+result[i].candidateOppositionList[j].candidateName.charAt(0)+'</span>'+result[i].candidateOppositionList[j].candidateName+'</td>';
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
		$("#candidatesResultsDT").dataTable({
			"paging":   true,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"iDisplayLength": 9,
			"aaSorting": [],
			"aLengthMenu": [[9, 15, 20, -1], [9, 15, 20, "All"]]
		});
	}
}
function getElectionInformationLocationWiseStatus(){
	
	var jsObj={
	  	locationTypeId 		:2,
		locationValue 		:1,
		electionScopeIds	:[2],
		partyIdsList  		:[872],
		electionYears     	:electionYrVal,
		electionSubTypeArr 	:electionSubTypeArr

    }
    $.ajax({
      type : "GET",
      url : "getElectionInformationLocationWiseStatus.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		
	});
}