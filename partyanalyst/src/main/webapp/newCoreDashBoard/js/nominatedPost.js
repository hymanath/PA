
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
	//alert(567);
	
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
			 buildNominatedPostCategoriesDlsHIghchart(result);
		});
	}
	
function buildNominatedPostCategoriesDlsHIghchart(result){
    var str='';
    if(result != null && result.length > 0){
      var str='';
      var count=0;
        str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
        for(var i in result){
          
            str+='<h5 class="text-capital m_top10 alertCategoryCls">'+result[i][count].userType+'</h5>';      
            str+='<div id="nominatedPostCategory'+i+'" attr_category_name='+result[i][count].userType+' attr_id="nominatedPostCategory'+i+'" style="height:170px;" class="scrollLenDiv"></div>';
          count++;      
        }
        str+='</div>';
    }
    $("#nominatedPostLocationWiseDivId").html(str);
    if(result != null && result.length > 0){
      for(var i in result){
        var districtCountArr=[];
        var constCountArr=[];
        var mandalCountArr=[];
		var centralCountArr=[];
		var stateCountArr=[];
		var vilageCountArr=[];
        var candidateNameArray=[]; 
        var countVar =0;
        
        if(result[i] !=null && result[i].length>0){
          for(var j in result[i]){
            candidateNameArray.push(result[i][j].name.toUpperCase());
            centralCountArr.push({"y":parseFloat(result[i][j].centralPostsPerc),"extra":result[i][j].centralPostsPerc});
			stateCountArr.push({"y":parseFloat(result[i][j].statePostsPerc),"extra":result[i][j].statePostsPerc});
			districtCountArr.push({"y":parseFloat(result[i][j].districtPostsPerc),"extra":result[i][j].districtPostsPerc});
            constCountArr.push({"y":parseFloat(result[i][j].constncyPostsPerc),"extra":result[i][j].constncyPostsPerc});
            mandalCountArr.push({"y":parseFloat(result[i][j].mandalPostsPerc),"extra":result[i][j].mandalPostsPerc});
			vilageCountArr.push({"y":parseFloat(result[i][j].villagePostsPerc),"extra":result[i][j].villagePostsPerc});
			countVar =countVar+1;
            if (countVar === 5) {
              break;
            }
          }
        }
        
          
        if( result[i][j].installedPerc !=0 || result[i][j].pendingPerc !=0 || result[i][j].notSmartPhonePerc !=0){
          
          
            $("#genSecKaizala1"+i).width("400px");
          
             $("#nominatedPostCategory"+i).highcharts({
               colors: ['#ddd','#333','#E4D254','#8085E9','#FD9700','#E9F8F5'],
              chart: {
                type: 'column'
              },
              title: {
                text: ''
              },
              subtitle: {
                text: ''
              },
              xAxis: {
                min: 0,
                gridLineWidth: 0,
                minorGridLineWidth: 0,
                categories: candidateNameArray,
                type: 'category',
                labels: {
                      formatter: function() {
                        return this.value.toString().substring(0, 10)+'...';
                      },
                      
                    }
                
              },
              yAxis: {
                min: 0,
                gridLineWidth: 0,
                minorGridLineWidth: 0,
                title: {
                  text: ''
                },
                labels: {
                  enabled:false
                }
              },
              legend: {
                enabled: false
              },
              
                  
              plotOptions: {
                column: {
                  stacking: 'percent',
                  dataLabels: {
                    enabled: true,
                     formatter: function() {
                      if (this.y === 0) {
                        return null;
                      } else {
                        return (this.y) +'%';
                      }
                    }
                    
                  }
                }
              },
              tooltip: {
                formatter: function () {
                  var s = '<b>' + this.x + '</b>';
                  $.each(this.points, function () {
                    s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :'+this.point.extra+' -  ' +
                      (this.y)+'%';
                  });
                },
                shared: true
              },
                
                series: [{
                  name: 'Central',
                  data: centralCountArr
                },
				{
                  name: 'State',
                  data: stateCountArr
                },
				{
				  name: 'District',
                  data: districtCountArr
                  
                },{
                  name: 'Const',
                  data: constCountArr
                },
				{
                  name: 'Mandal',
                  data: mandalCountArr
                },
				{
                  name: 'Vilage',
                  data: vilageCountArr
                }],
             
            });
          
        } 
        else{
          $("#nominatedPostCategory"+i).html("No Data Available");
          $("#nominatedPostCategory"+i).css("height","35px");
            
        } 
      }
    }
        
  }
 $(document).on("click",".departmentCls",function(e){
		//alert(000);
		getDepartmentWisePostAndApplicationDetails();
		//getDepartmentWisePostAndApplicationDetails()
});
//locationLevelCls
  function getDepartmentWisePostAndApplicationDetails()
	{   
		var jsObj ={
		             "locationValuesArr" : [],
					 "fromDateStr" : " ",
					 "toDateStr" : " ",
					 "locationTypeId" : 2,
					 "year" : " ",
					 "boardLevelId":2,
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
		str+='<div class="row m-top20">';
			str+='<div class="col-sm-12">';
				str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default">';
						str+='<div class="panel-heading" role="tab" id="headingOne">';
							str+='<h4 class="panel-title custom-panel">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">';
									str+='<i class="fa fa-plus-square-o more-less icon-size25" aria-hidden="true"></i>';
										str+='State Level Overview';
								str+='</a>';
							str+='</h4>';
						str+='</div>';
						str+='<div id="collapseOne" class="panel-collapse collapse custom-border" role="tabpanel" aria-labelledby="headingTwo">';
							str+='<div class="panel-body">';
								str+='<div class="table-responsive" id="depamentId">';
									str+='<table class="table table-bordered">';
										 str+='<thead>';
											str+='<tr class="header-bg">';
											  str+='<th scope="col">Department</th>';
											  str+='<th scope="col">Total Posts</th>';
											  str+='<th scope="col">Finalized</th>';
											  str+='<th scope="col">G.O Issued</th>';
											  str+='<th scope="col">Open Posts</th>';
											  str+='<th scope="col">Expire in 1 Month</th>';
											  str+='<th scope="col">Expire in 2 Months</th>';
											  str+='<th scope="col">Expire in 3 Months</th>';
											str+='</tr>';
										 str+='</thead>';
										 str+='<tbody>';
										for(var i in result)
										{
										  
											
											str+='<tr>';
											 str+='<td>'+result[i].name+'</td>';
											 str+='<td>'+result[i].totalCount+'</td>';
											 str+='<td>'+result[i].finalizedCnt+'</td>';
											 str+='<td>'+result[i].goIsuuedCnt+'</td>';
											 str+='<td>'+result[i].openCnt+'</td>';
											 str+='<td>'+result[i].expireOneMnth+'</td>';
											 str+='<td>'+result[i].expireTwoMnth+'</td>';
											 str+='<td>'+result[i].expireThreMnth+'</td>';
											str+='</tr>';
										 
										}
										str+=' </tbody>';
									str+='</table>';
								str+='</div>';
								
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		/* str+='<div id="collapseOne" class="panel-collapse collapse custom-border " role="tabpanel" aria-labelledby="headingOne">';
			str+='<div class="panel-body">'; */
				
			/* str+='</div>';
		str+='</div>'; */
								
	
	$("#departmentId").html(str);
	$("#depamentId").dataTable();			
}	
	
//getNominatedPostStateWiseCount();	
function getNominatedPostStateWiseCount()
	{ 
	//alert(567);
	
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
			//alert(78);
			
		});
	}
$(document).on("click",".locationLevelCls",function(e){
		//getNominatedPostLocationWiseBoardLevelCount();
		buildCollapseLocationWise();
});


function buildCollapseLocationWise(){

	$("#locLevelId").html('');
	var locationArr=["State","District","Constituency"];
	var str='';	
		str+='<div class="row m-top20">';
			str+='<div class="col-sm-12">';
				str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
					for(var i in locationArr)
					{
						str+='<div class="panel panel-default">';
							str+='<div class="panel-heading" role="tab" id="#'+locationArr[i]+'Heading">';
								str+='<h4 class="panel-title custom-panel">';
									str+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#'+locationArr[i]+'Body" aria-expanded="true" aria-controls="#'+locationArr[i]+'Body">';
										str+='<i class="fa fa-plus-square-o more-less icon-size25" aria-hidden="true"></i>';
											str+=locationArr[i]+' Level Overview';
									str+='</a>';
								str+='</h4>';
							str+='</div>';
							str+='<div id="'+locationArr[i]+'Body" class="panel-collapse collapse custom-border" role="tabpanel" aria-labelledby="#'+locationArr[i]+'Heading">';
								str+='<div class="panel-body">';
									if(locationArr[i]=="District")
									{
										str+='<div class="row">';
											str+='<div class="col-sm-2">';
												str+='<ul class="menuPosition list-inline">';
													str+='<li class="active" attr_level_id="districtLevel" attr_tab_id="'+locationArr[i]+'tables">District</li>';
													str+='<li attr_level_id="parliamentLevel" attr_tab_id="'+locationArr[i]+'tables">Parliament</li>';
												str+='</ul>';
											str+='</div>';	
										str+='</div>';
										
									}
									str+='<div class="row" style="margin-top:20px;">';
										str+='<div class="col-sm-12">';
											str+='<div id="'+locationArr[i]+'tables"></div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					}
					str+='</div>';
				str+='</div>';
		str+='</div>';
		/* str+='<div id="collapseOne" class="panel-collapse collapse custom-border " role="tabpanel" aria-labelledby="headingOne">';
			str+='<div class="panel-body">'; */
				
			/* str+='</div>';
		str+='</div>'; */
								
	
	$("#locLevelId").html(str);
	getNominatedPostLocationWiseBoardLevelCount(locationArr[1]+'tables',3,3);
				
}	

function getNominatedPostLocationWiseBoardLevelCount(tabId,locationTypeId,boardLevelId)
	{ 
	//alert(567);
	$("#"+tabId).html('');
		var jsObj ={
		             "locationValuesArr" : [],
					 "fromDateStr" : " ",
					 "toDateStr" : " ",
					 "locationTypeId" : locationTypeId,
					 "boardLevelId":boardLevelId
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getNominatedPostLocationWiseBoardLevelCountAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			//alert(78);
			buildGetNominatedPostLocationWiseBoardLevelCount(result,tabId);
			
		});
	}
	  function buildGetNominatedPostLocationWiseBoardLevelCount(result,tabId){
		var str='';	
			str+='<div class="table-responsive m-top20">';
				str+='<table class="table table-bordered">';
					str+='<thead>';
						str+='<tr class="">';
							str+='<td rowspan="2" class="header-bg">Districts</td>';
								for(var i in result[0].levelList)
								{
									str+='<td colspan="4" class="district-level-bg text-center">'+result[0].levelList[i].locationName+' Level Posts</td>';
									
								}
							
						str+='</tr>';
						str+='<tr class="">';	
							for(var i in result[0].levelList)
								{
									
									str+='<td>TOTAL  POSTS</td>';
									str+='<td>OPEN POSTS</td>';
									str+='<td>FINAL REVIEW</td>';
									str+='<td>COMPLETED / G.O ISSSUED</td>';
									
								}
						str+='</tr>';
						
						str+='</tr>';
						
					str+='</thead>';
					str+='<tbody>';
						for(var i in result)	
						{
							str+='<tr>';
								str+='<td>'+result[i].board+'</td>';
							for(var j in result[i].levelList)
								{
									
									str+='<td>'+result[i].levelList[j].totalPosts+'</td>';
									str+='<td>'+result[i].levelList[j].openCount+'</td>';
									str+='<td>'+result[i].levelList[j].finalizedPost+'</td>';
									str+='<td>'+result[i].levelList[j].goIsuuedCount+'</td>';
									
								}
							str+='</tr>';	
						}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			//alert(str);
		$("#"+tabId).html(str);	
	}  
//getDepartMentAndBoardWisePositinsStatusCount();
/* function getDepartMentAndBoardWisePositinsStatusCount()
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
	getDepartMentWiseBoards();
	function getDepartMentWiseBoards()
	{ 
	alert(567);
	 // $("#meetingBasicCountDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={
		             "locationValuesArr" : [17],
					 "fromDateStr" : " ",
					 "toDateStr" : " ",
					 "locationTypeId" : 3,
					 "boardLevelId": 3,
					 "statusId" : 1,
					 "departmentId" : 6
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getDepartMentWiseBoardsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			alert(78);
			//$("#meetingBasicCountDivId").html(' ');
			//buildMeetingBasicCountDetails(result);
		});
	}
 */	getBoardWisePositions();
	function getBoardWisePositions()
	{ 
	//alert(567);
	 // $("#meetingBasicCountDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj ={
		             "locationValuesArr" : [17],
					 "fromDateStr" : " ",
					 "toDateStr" : " ",
					 "locationTypeId" : 3,
					 "boardLevelId": 3,
					 "statusId" : 1,
					 "boardId" : 1753
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getBoardWisePositionsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			//alert(78);
			//$("#meetingBasicCountDivId").html(' ');
			//buildMeetingBasicCountDetails(result);
		});
	}						
	$(document).on("click",".menuPosition li",function()
	{
		var locationLevel=$(this).attr('attr_level_id');
		var tableId=$(this).attr('attr_tab_id');	
		$(".menuPosition li").removeClass('active');
		$(this).addClass('active');
		if(locationLevel=="districtLevel")
		{
			getNominatedPostLocationWiseBoardLevelCount(tableId,3,3);
			
		}
		else if(locationLevel=="parliamentLevel")
		{
			getNominatedPostLocationWiseBoardLevelCount(tableId,10,3);
		}
		
		
	});					
						