
//getLevelWisePostsOverView();
function getLevelWisePostsOverView()
		{ 
				alert(567);
				 // $("#meetingBasicCountDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
					var jsObj ={
								 "locationValuesArr" : [],
								 "fromDateStr" : " ",
								 "toDateStr" : " ",
								 "locationTypeId" : 2,
								 "boardLevelId":2
								 
							  }
					$.ajax({
						type : 'POST',
						url : 'getLevelWisePostsOverViewAction1.action',
						dataType : 'json',
						data : {task:JSON.stringify(jsObj)}
					}).done(function(result){
						//buildLevelWisePostsOverView(result);
					});
		}					
		
		/* function buildLevelWisePostsOverView(result)
			{
				alert(5678);
				var str='';				
					 for(var i in result)
					 {
						str+='<div class="row">';	
							str+='<div class="col-sm-12">';
								str+='<h4 class="header-block white-text">'+result[i].board+' Level</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="row">';
							str+='<div class="col-sm-12">';
								str+='<div class="table-responsive">';
									str+='<table class="table table-responsive borderless">';
										str+='<tr>';
											str+='<td class="border-top0">';
														str+='<div class="col-sm-12 div-block">';
															str+='<h4>Received <br>Applications</h4><h5>'+result[i].recivedCount+'</h5>';
														str+='</div>';
													str+='</td>';
													str+='<td class="border-top0">';
														str+='<div class="col-sm-12 div-block">';
															str+='<h4>Total <br>Posts</h4><h5>'+result[i].totalPosts+'</h5>';
														str+='</div>';
													str+='</td>';
													str+='<td class="border-top0">';
														str+='<div class="col-sm-12 div-block">';
														str+='<h4>Open <br>Posts</h4><h5>'+result[i].openCount+'<span class="small-green-font pad-left-20">'+result[i].openPostPer+'%</span></h5>';
														str+='</div>';
													str+='</td>';
													str+='<td class="border-top0">';
														str+='<div class="col-sm-12 div-block">';
															str+='<h4>Finalized <br>Posts</h4><h5>'+result[i].finalizedPost+'<span class="small-green-font pad-left-20">'+result[i].finalizedPer+'%</span></h5>';
														str+='</div>';
													str+='</td>';
													str+='<td class="border-top0">';
														str+='<div class="col-sm-12 div-block">';
															str+='<h4>Completed / <br>G.O Issued</h4><h5>'+result[i].goIsuuedCount+'<span class="small-green-font pad-left-20">'+result[i].goIssuedPer+'%</span></h5>';
														str+='</div>';
													str+='</td>';
										str+='</tr>';
									str+='</table>';
								str+='</div>';
							str+='</div>';
						str+='</div>';	
				}	
				$("#nominatedPostContentId").html(str);
									
			 }*/
//getUserTypeWiseNominatedPostDetailsCnt()
 function getUserTypeWiseNominatedPostDetailsCnt()
	{ 
	alert(567);
	
		var jsObj ={
					 "fromDateStr" : " ",
					 "toDateStr" : " ",
					 "activityMemberId" :44,
					 "stateId" : 1,
					 "userTypeId" :2 
				  }
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseNominatedPostDetailsCntAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			alert(78);
		});
	}
getDepartmentWisePostAndApplicationDetails()
  function getDepartmentWisePostAndApplicationDetails()
	{ 
	alert(567);    
		var jsObj ={
		             "locationValuesArr" : [],
					 "fromDateStr" : " ",
					 "toDateStr" : " ",
					 "locationTypeId" : 2,
					 "year" : " ",
					 "boardLevelId":2
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getDepartmentWisePostAndApplicationDetailsAction1.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			alert(78);
		});
	}
getNominatedPostStateWiseCount();	
function getNominatedPostStateWiseCount()
	{ 
	alert(567);
	
		var jsObj ={
					 "fromDateStr" : " ",
					 "toDateStr" : " "
				  }
		$.ajax({
			type : 'POST',
			url : 'getNominatedPostStateWiseCountAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			alert(78);
			
		});
	}
getNominatedPostLocationWiseBoardLevelCount();	
function getNominatedPostLocationWiseBoardLevelCount()
	{ 
	alert(567);
		var jsObj ={
		             "locationValuesArr" : [],
					 "fromDateStr" : " ",
					 "toDateStr" : " ",
					 "locationTypeId" : 3,
					 "boardLevelId":3
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getNominatedPostLocationWiseBoardLevelCountAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			alert(78);
			
		});
	}
//getDepartMentAndBoardWisePositinsStatusCount();
function getDepartMentAndBoardWisePositinsStatusCount()
	{ 
	alert(567);
	 // $("#meetingBasicCountDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	 
	    
		var jsObj ={
		             "locationValuesArr" : [17],
					 "fromDateStr" : " ",
					 "toDateStr" : " ",
					 "locationTypeId" : 3,
					 "boardLevelId":3,
					 "statusId" :1
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getDepartMentAndBoardWisePositinsStatusCountAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			alert(78);
			//$("#meetingBasicCountDivId").html(' ');
			//buildMeetingBasicCountDetails(result);
		});
	}	
								