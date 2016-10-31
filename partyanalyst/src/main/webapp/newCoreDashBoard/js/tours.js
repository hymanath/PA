
var globalStateIdForTour=1; //for ap
function getToursBasicOverviewCountDetails()
	{    
		$("#tourOverviewDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={ 
					 activityMemberId : globalActivityMemberId,
					 stateId : globalStateIdForTour,
					 fromDate : "27/10/2016",
					 toDate : "29/10/2016"
				  }
		$.ajax({
			type : 'POST',
			url : 'getToursBasicOverviewCountDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			    $("#tourOverviewDivId").html(' ');
				buildToursBasicOverviewRslt(result);
		});
	}
	 function buildToursBasicOverviewRslt(result){
		var overAllResult= result.overAllDetailsVO;
		var designationWiseRlst = result.subList;
		 var str='';
		  str+'<div class="row">';
		  if(overAllResult != null){
			 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10" >';
			 str+='<h4>Leaders</h4>';
		      str+='<table class="table tableTraining bg_ED">';
			  str+='<tbody><tr>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.noOfLeaderCnt+'</h4>';
					  str+='<p class="text-muted text-capital">Total<br>Leaders</p>';
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.submitedLeaderCnt+'<span class="font-10 text-success"> '+overAllResult.submitedCandidateTourPer+'%</span></h4>';
						str+='<p class="text-muted text-capital">Submited<br>Leaders</p>';  
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.notSubmitedLeaserCnt+'<span class="font-10 text-danger"> '+overAllResult.notsubmitedCandidateTourPer+'%</span></h4>';
					str+='<p class="text-muted text-capital">Not Submited<br>Leaders</p>';  
				  str+='</td>';
				  str+='<td>';
					  str+='<h4>'+overAllResult.totalSubmittedToursCnt+'</h4>';
						str+='<p class="text-muted text-capital">Total<br>Tours</p>';
				   str+='</td>';
				    str+='<td>';
						 str+='<h4>'+overAllResult.averageTours.toFixed(2)+'</h4>';
						str+='<p class="text-muted text-capital">Average<br>Tours</p>';  
				str+='</td>';
			  str+='</tr>';
	       str+='</tbody></table>';
		  str+='<hr class="m_0">';
		  str+='</div>';  
		  }
		 if(designationWiseRlst != null && designationWiseRlst.length > 0){
		   for(var i in designationWiseRlst){
				 str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10" >';
				  str+='<h4>'+designationWiseRlst[i].designation+'</h4>';
				  str+='<table class="table tableTraining bg_ED">';
				  str+='<tbody><tr>';
					  str+='<td>';
					  	  str+='<p class="text-muted text-capital">Total<br>Leaders</p>';
						  str+='<h4>'+designationWiseRlst[i].noOfLeaderCnt+'</h4>';
					  str+='</td>';
					  str+='<td>';
					  	  str+='<p class="text-muted text-capital">Submited<br>Leaders</p>';  
						  str+='<h4>'+designationWiseRlst[i].submitedLeaderCnt+'<span class="font-10 text-success"> '+designationWiseRlst[i].submitedCandidateTourPer.toFixed(2)+'%</span></h4>';
					  str+='</td>';
					  str+='<td>';
					  	  str+='<p class="text-muted text-capital">Not Submited<br>Leaders</p>';  
						  str+='<h4>'+designationWiseRlst[i].notSubmitedLeaserCnt+'<span class="font-10 text-danger"> '+designationWiseRlst[i].notsubmitedCandidateTourPer.toFixed(2)+'%</span></h4>';
					  str+='</td>';
					  str+='<td>';
					  	  str+='<p class="text-muted text-capital">Total<br>Tours</p>';
						  str+='<h4>'+designationWiseRlst[i].totalSubmittedToursCnt+'</h4>';
					   str+='</td>';
						str+='<td>';
							 str+='<p class="text-muted text-capital">Average<br>Tours</p>';  
							 str+='<h4>'+designationWiseRlst[i].averageTours.toFixed(2)+'</h4>';
					str+='</td>';
				  str+='</tr>';
			   str+='</tbody></table>';
			  str+='<hr class="m_0">';
			  str+='</div>';  
			}
		  }else{
		  str+='No Data Available';	  
		  }
		str+='</div>';
	  $("#tourOverviewDivId").html(str);  
	 }
	 
	 function getDistrictWiseToursSubmitedDetails()
		{    
			$("#tourOverviewDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			var jsObj ={ 
						 activityMemberId : globalActivityMemberId,
						 stateId : globalStateIdForTour,
						 fromDate : "27/10/2016",
						 toDate : "29/10/2016",
						 userTypeId : 2
					  }
			$.ajax({
				type : 'POST',
				url : 'getDistrictWiseToursSubmitedDetailsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				  console.log(result);
			});
		}