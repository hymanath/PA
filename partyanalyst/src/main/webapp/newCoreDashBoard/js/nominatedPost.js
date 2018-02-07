
//getLevelWisePostsOverView();
function getLevelWisePostsOverView()
		{ 
				//alert(567);
				 // $("#meetingBasicCountDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
					var jsObj ={
								 "locationValuesArr" :[],
								 "fromDateStr" : " ",
								 "toDateStr" : " ",
								 "locationTypeId" : 2,
								 "boardLevelId": 2
								 
							  }
					$.ajax({
						type : 'POST',
						url : 'getLevelWisePostsOverViewAction1.action',
						dataType : 'json',
						data : {task:JSON.stringify(jsObj)}
					}).done(function(result){
						buildLevelWisePostsOverView(result);
					});
		}					
		
		 function buildLevelWisePostsOverView(result)
			{
				//alert(5678);
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
															str+='<h4>Final <br>Review</h4><h5>'+result[i].finalizedPost+'<span class="small-green-font pad-left-20">'+result[i].finalizedPer+'%</span></h5>';
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
									
			 }
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
	$(document).on("click",".departmentCls",function(e){
		alert(000);
		getDepartmentWisePostAndApplicationDetails();
	//e.stopPropagation();
	//$(this).closest("[expand-block-heading1='debates']").find(".debatesSettingsBody").show();
});

  function getDepartmentWisePostAndApplicationDetails()
	{   
		var jsObj ={
		             "locationValuesArr" : [],
					 "fromDateStr" : " ",
					 "toDateStr" : " ",
					 "locationTypeId" : 3,
					 "year" : " ",
					 "boardLevelId":4,
					 "deptId": 0
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getDepartmentWisePostAndApplicationDetailsAction1.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			//alert(78);
			buildDepartmentWisePostAndApplicationDetails(result);
		});
	}
function buildDepartmentWisePostAndApplicationDetails(result){

	var str='';
		/* str+='<div id="collapseOne" class="panel-collapse collapse custom-border " role="tabpanel" aria-labelledby="headingOne">';
			str+='<div class="panel-body">'; */
				str+='<div class="table-responsive" id="depamentId">';
					str+='<table class="table table-bordered">';
						 str+='<thead>';
							str+='<tr class="header-bg">';
							  str+='<th scope="col">Department</th>';
							  str+='<th scope="col">Total Posts</th>';
							  str+='<th scope="col">G.O Issued</th>';
							  str+='<th scope="col">Open Posts</th>';
							  str+='<th scope="col">Expire in 1 Month</th>';
							  str+='<th scope="col">Expire in 2 Months</th>';
							  str+='<th scope="col">Expire in 3 Months</th>';
							str+='</tr>';
						 str+='</thead>';
						for(var i in result)
						{
						  str+='<tbody>';
							
							str+='<tr>';
							 str+='<td>'+result[i].name+'</td>';
							  str+='<td>'+result[i].totalCount+'</td>';
							  str+='<td>'+result[i].goIsuuedCnt+'</td>';
							  str+='<td>'+result[i].openCnt+'</td>';
							  str+='<td>'+result[i].expireOneMnth+'</td>';
							  str+='<td>'+result[i].expireTwoMnth+'</td>';
							  str+='<td>'+result[i].expireThreMnth+'</td>';
							str+='</tr>';
						 str+=' </tbody>';
						}
					str+='</table>';
				str+='</div>';
			/* str+='</div>';
		str+='</div>'; */
								
	
	$("#departmentId").html(str);
	$("#depamentId").dataTable();			
}	
	
//getNominatedPostStateWiseCount();	
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
//getNominatedPostLocationWiseBoardLevelCount();	
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
			//buildGetNominatedPostLocationWiseBoardLevelCount(result);
			
		});
	}
	/* function buildGetNominatedPostLocationWiseBoardLevelCount(result){
		var str='';
		str+='<div class="table-responsive m-top20">';
														str+='<table class="table table-bordered">';
															  
																str+='<tr class="">';
																 str+='<td rowspan="2" class="header-bg">Districts</td>';
																  str+='<td colspan="3" class="district-level-bg">District Level Posts</td>';
																  str+='<td colspan="3" class="const-level-bg">Constituency Level Posts</td>';
																  str+='<td colspan="3" class="mandal-level-bg">Mandal Level Posts</td>';
																  str+='<td colspan="3" class="village-level-bg">Village Level Posts</td>';
																str+='</tr>';
															  
																str+='<tr>';
																  str+='<td class="district-level-bg td-width2">Total <br>Posts </td>';
																  str+='<td class="district-level-bg td-width2">Open <br>Posts</td>';
																  str+='<td class="district-level-bg td-width">Completed / <br>G.O Issued</td>';
																str+='</tr>';
																str+='<tr>';
																  str+='<td>Srikakulam</td>';
																  str+='<td class="district-level-bg2">1241</td>';
																  str+='<td class="district-level-bg2">80</td>';
																  str+='<td class="district-level-bg2">1000</td>';
																  str+='<td class="const-level-bg2">1241</td>';
																  str+='<td class="const-level-bg2">80</td>';
																  str+='<td class="const-level-bg2">1000</td>';
																  str+='<td class="mandal-level-bg2">1241</td>';
																  str+='<td class="mandal-level-bg2">80</td>';
																  str+='<td class="mandal-level-bg2">1000</td>';
																  str+='<td class="village-level-bg2">1241</td>';
																  str+='<td class="village-level-bg2">80</td>';
																  str+='<td class="village-level-bg2">1000</td>';
																str+='</tr>';
																
														str+='</table>';
													str+='</div>';
	} */
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
								