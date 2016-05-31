	
/* $('#questnsListId').change(function(){
		getOptionDetailsForQuestion();
				getActivityQuestionInfo();
	}); */
function getQuestionsForReportTypeAction(){
var activityScopeId = $("#ActivityList").val();
if(activityScopeId == null){
	return;
}
$('#questnsListId').find('option').remove();
$('#questnsListId').append('<option value="0"> All </option>');
	 var jsObj=
	   {				
		  scopeId:activityScopeId,
		  task:""				
		}
		$.ajax({
				  type:'GET',
				  url: 'getQuestionsForReportTypeAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			  // console.log(result)
			   				for(var i in result)
					$('#questnsListId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				getOptionDetailsForQuestion();
				//getActivityQuestionInfo();
		   });
	}


function getOptionDetailsForQuestion(){
	
getActivityQuestionInfo();
 $('#optnsCntDiv').show();
$('#optnsCntDiv').html('<div > <div class="col-md-12" style="text-align:center;margin-top:15px;"><img src="images/ajaxImg2.gif" style="width:20px;margin-top:-20px;margin-left:-25px;display:none;" id="procesingImg"></div></div>');
	var activityLevelIdsArr=[];
	activityLevelIdsArr.push($('#activityLevelList').val());
	var questionArr=[];
	
	  if($('#questnsListId').val() != 0)
		questionArr.push($('#questnsListId').val());
	  else
	  {
		$('#questnsListId option').each(function(){
			questionArr.push(this.value);
		});
	  }
  
		var jObj = {
				stateId:1,
				activityScopeId:$('#ActivityList').val(),
				searchType:$('#reportList option:selected').text().trim(),
				activityLevelIdsArr:activityLevelIdsArr,
				questionArr:questionArr
			};		
			$.ajax({
				  type:'GET',
				  url: 'getActivityQuestionnnairWiseReportAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){	
				buildQuestionResponseTable(result);
				//$("#buildActivityReasonReportTableId").show();
			 });
}

function getActivityQuestionInfo(){
	$("#questionWiseDetailsDiv").html("");
	
	var questionArr=[];
	if($('#questnsListId').val() != 0)
		questionArr.push($('#questnsListId').val());
	  else
	  {
		$('#questnsListId option').each(function(){
				   questionArr.push(this.value);
		});
	  }
  
	var jObj = {
		activityScopeId:$('#ActivityList').val(),
		activityLevelId:$('#activityLevelList').val(),
		questionIds:questionArr
	};		
	$.ajax({
		  type:'GET',
		  url: 'getActivityLocationInfoDetailsByActivityScopeAction.action',
		 data : {task:JSON.stringify(jObj)} ,
	 }).done(function(result){
		if(result != null && result.length > 0){
			buildQuestionsDetails(result);
		}else{
			$("#questionDetailsDivId").show();
			$("#questionWiseDetailsDiv").html("NO DATA AVAILABLE...");
		}	
	});
}

function buildQuestionsDetails(result){
	var str='';
	str+='<table class="table table-condensed table-bordered">';
		str+='<thead>'
			str+='<th> QUESTION </th>'
			str+='<th> TOTAL </th>'
			str+='<th> AP </th>'
			str+='<th> TS </th>'
		str+='</thead>'
		str+='<tbody>'
			for(var i in result){
				str+='<tr>'
					str+='<td>'+result[i].question+'</td>';
					if(result[i].desTotalCount > 0){
						str+='<td>'+result[i].totalCount+' ('+result[i].desTotalCount+')</td>';
						str+='<td>'+result[i].APCount+' ('+result[i].desAPCount+')</td>';
						str+='<td>'+result[i].TSCount+' ('+result[i].desTSCount+')</td>';
					}
					else{
						str+='<td>'+result[i].totalCount+'</td>';
						str+='<td>'+result[i].APCount+'</td>';
						str+='<td>'+result[i].TSCount+'</td>';
					}
				str+='</tr>'
			}
		str+='</tbody>'
	str+='</table>'
	
	$("#questionDetailsDivId").show();
	$("#questionWiseDetailsDiv").html(str);
}


function buildQuestionResponseTable(myResult){
	
	if(myResult != null && myResult.sublist != null && myResult.sublist.length>0){
		$("#showData").hide();
	    $("#hideData").show();
		$("#responceDivId").show();
		$("#procesingImg").hide();
		var str='';
		str+='<table id="responseTab" class="table table-condensed table-bordered">';
		str+='<thead>';
		for(var i in myResult.sublist){
			
			if(myResult.sublist[0].sublist != null && myResult.sublist[0].sublist.length>0){
				for(var j in myResult.sublist[i].sublist){
					if(i==0){
					var result = myResult.sublist[0]
				
						if(result != null && result.sublist != null && result.sublist.length>0){
							
							var colspancount1=1;
							if(result.sublist[0].sublist1 != null && result.sublist[0].sublist1.length>0)
								colspancount1 = colspancount1+parseInt(1)*2;							
							if(result.sublist[0].sublist2 != null && result.sublist[0].sublist2.length>0)
								colspancount1 = colspancount1+parseInt(result.sublist[0].sublist2.length);							
							
							var colspancount2=2;
							if(result.sublist.length>1){
									if(result.sublist[1].sublist1 != null && result.sublist[1].sublist1.length>0)
										colspancount2 = colspancount2+parseInt(1)*2;							
									if(result.sublist[1].sublist2 != null && result.sublist[1].sublist2.length>0)
										colspancount2 = colspancount2+parseInt(result.sublist[1].sublist2.length);	
							}
							str+='<tr>';
							str+='<th rowspan="3" style="text-align:center;"> LOCATION  </th>';
							str+='<th colspan="'+colspancount1+'" style="text-align:center;"> '+result.sublist[0].levelStr+'</th>';
							if(result.sublist.length>1)
								str+='<th colspan="'+(colspancount2-1)+'" style="text-align:center;"> '+result.sublist[1].levelStr+'</th>';							
							str+='</tr>';
							str+='<tr>';
							
							str+='<th rowspan="2" style="text-align:center;"> TOTAL  </th>';							
							if(result.sublist[0].sublist1 != null && result.sublist[0].sublist1.length>0){
								for(var i in result.sublist[0].sublist1){
									if(i==0)
										str+='<th colspan="2" style="text-align:center;"> '+result.sublist[0].sublist1[i].name+' </th>';
								}
							}
							if(result.sublist[0].sublist2 != null && result.sublist[0].sublist2.length>0){
								for(var i in result.sublist[0].sublist2){
									str+='<th rowspan="2" style="text-align:center;"> '+result.sublist[0].sublist2[i].name+' </th>';
								}
							}
							
							if(result.sublist.length>1){								
								str+='<th rowspan="2" style="text-align:center;"> TOTAL  </th>';
								
								if(result.sublist[1].sublist1 != null && result.sublist[1].sublist1.length>0){
									for(var i in result.sublist[1].sublist1){
										if(i==0)
											str+='<th colspan="2" style="text-align:center;"> '+result.sublist[1].sublist1[i].name+' </th>';
									}
								}
								if(result.sublist[1].sublist2 != null && result.sublist[1].sublist2.length>0){
									for(var i in result.sublist[1].sublist2){
										str+='<th rowspan="2" style="text-align:center;"> '+result.sublist[1].sublist2[i].name+' </th>';
									}
								}								
							}
							str+='</tr>';
							str+='<tr>';
							//str+='<th rowspan="2"> LOCATION NAME  </th>';
							if(result.sublist[0].sublist1 != null && result.sublist[0].sublist1.length>0)
								for(var i in result.sublist[0].sublist1){
									if(i==0){
										str+='<th style="text-align:center;"> CALLED </th>';
										str+='<th style="text-align:center;"> PENDING </th>';
									}
									if(result.sublist.length>1){
										if(i==0){
											str+='<th style="text-align:center;"> CALLED </th>';
											str+='<th style="text-align:center;"> PENDING </th>';
										}
									}									
								}			
							str+='</tr>';
							str+='</thead>';
						}
					}
				}
			}
		}
		str+='<tbody>';
		for(var i in myResult.sublist){
			if(myResult.sublist[i].sublist != null && myResult.sublist[i].sublist.length>0){
				//for(var j in myResult.sublist[i].sublist){
					var result = myResult.sublist[i];				
						if(result != null && result.sublist != null && result.sublist.length>0){
								
							for(var k in result.sublist){
								if(k==0){
								str+='<tr>';
									str+='<td>'+result.sublist[k].name+'</td>';
									str+='<td>'+result.sublist[k].totalCount+'</td>';
									for(var n in result.sublist[k].sublist1){										
										if(n==0){
											str+='<td>'+result.sublist[k].sublist1[n].called+'</td>';
											str+='<td>'+result.sublist[k].sublist1[n].pending+'</td>';
										}
									}
									for(var n in result.sublist[k].sublist2){
										if(result.sublist[k].sublist2[n].sumcount>0)
											str+='<td>'+result.sublist[k].sublist2[n].totalCount+' ('+result.sublist[k].sublist2[n].sumcount+')</td>';
										else
											str+='<td>'+result.sublist[k].sublist2[n].totalCount+' </td>';
									}
											
										
									if(result.sublist.length>1){	
									//str+='<td>'+result.sublist[1].name+'</td>';
									str+='<td>'+result.sublist[1].totalCount+'</td>';
									for(var n in result.sublist[1].sublist1){										
										if(n==0){
											str+='<td>'+result.sublist[1].sublist1[n].called+'</td>';
											str+='<td>'+result.sublist[1].sublist1[n].pending+'</td>';
										}
									}
									for(var n in result.sublist[k].sublist2){
										if(result.sublist[1].sublist2[n].sumcount>0)
											str+='<td>'+result.sublist[1].sublist2[n].totalCount+' ('+result.sublist[1].sublist2[n].sumcount+')</td>';
										else
											str+='<td>'+result.sublist[1].sublist2[n].totalCount+' </td>';
										}
									}
									str+='</tr>';
								}
							}
							
						
						}
				//}
			}
		}
		
		str+='</tbody>';
		str+='</table>';
		// $('#optnsCntDiv').html(str);
			//console.log(str);
		/* $('#responseTab').dataTable({
			"iDisplayLength": 20,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			
		});  */
		 $('#optnsCntDiv').show();
		  $("#lcnExcelBtn").show();
	}else{
		str='NO DATA AVAILABLE';
		$('#optnsCntDiv').show();
		  $("#lcnExcelBtn").hide();
		  $("#responceDivId").show();
	}
	$('#optnsCntDiv').html(str);
	$('#responseTab').dataTable({
			"iDisplayLength": 20,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			
		});
			//console.log(str);
}
function buildQuestionsResponseTable(result){
	
	if(result != null && result.sublist1 != null && result.sublist1.length>0){
		var colspancount=2;
		if(result.sublist1[0].sublist1 != null && result.sublist1[0].sublist1.length>0){
			colspancount = colspancount+parseInt(1)*2;
		}
		if(result.sublist1[0].sublist2 != null && result.sublist1[0].sublist2.length>0){
			colspancount = colspancount+parseInt(result.sublist1[0].sublist2.length);
		}	
		
		var str='';
		str+='<table id="responseTab" class="table table-condensed table-bordered">';
		str+='<thead>';
		str+='<tr>';
		str+='<th colspan="'+colspancount+'" style="text-align:center;"> '+result.name+'</th>';
		str+='</tr>';
		str+='<tr>';
		str+='<th rowspan="2" style="text-align:center;"> LOCATION  </th>';
		str+='<th rowspan="2" style="text-align:center;"> TOTAL  </th>';
		if(result.sublist1[0].sublist1 != null && result.sublist1[0].sublist1.length>0)
			for(var i in result.sublist1[0].sublist1){
				if(i==0)
					str+='<th colspan="2" style="text-align:center;"> '+result.sublist1[0].sublist1[i].name+' </th>';
			}
		
		if(result.sublist1[0].sublist2 != null && result.sublist1[0].sublist2.length>0)
			for(var i in result.sublist1[0].sublist2){
				str+='<th rowspan="2" style="text-align:center;"> '+result.sublist1[0].sublist2[i].name+' </th>';
			}
			
		str+='</tr>';
		str+='<tr>';
		//str+='<th rowspan="2"> LOCATION NAME  </th>';
		if(result.sublist1[0].sublist1 != null && result.sublist1[0].sublist1.length>0)
			for(var i in result.sublist1[0].sublist1){
				if(i==0){
					str+='<th style="text-align:center;"> CALLED </th>';
					str+='<th style="text-align:center;"> PENDING </th>';
				}
			}			
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result.sublist1){
				str+='<tr>';
				str+='<td>'+result.sublist1[i].name+'</td>';
				str+='<td>'+result.sublist1[i].totalCount+'</td>';
				for(var j in result.sublist1[i].sublist1){
					//str+='<td>'+result.sublist1[i].sublist1[j].totalCount+'</td>';
					if(j==0){
					str+='<td style="text-align:center;">'+result.sublist1[i].sublist1[j].called+'</td>';
					str+='<td style="text-align:center;">'+result.sublist1[i].sublist1[j].pending+'</td>';
					}
				}
				for(var j in result.sublist1[i].sublist2){
					//str+='<td>'+result.sublist1[i].sublist1[j].totalCount+'</td>';
					str+='<td style="text-align:center;">'+result.sublist1[i].sublist2[j].totalCount+'</td>';
				}
				str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	$('#optnsCntDiv').html(str);
	
	$('#responseTab').dataTable({
		"iDisplayLength": 20,
		"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		
	});
	 $('#optnsCntDiv').show();
	  //$("#lcnExcelBtn").show();
	}	
}
$("#hideData").click(function(){
	//$("#questionsDiv").hide();
	//$("#questionWiseDetailsDiv").hide();
	//$("#optnsCntDiv").hide();
	$("#qstnsDivId").hide();
	$("#responceDivId").hide();
	//$("#reportTypeId").hide();
	//$("#questionDetailsDivId").hide();
	$("#lcnExcelBtn").hide();
	$("#showData").show();
	$("#hideData").hide();
});
$("#showData").click(function(){
	 $("#qstnsDivId").show();
	 $("#responceDivId").show();
	 $("#lcnExcelBtn").show();
	$("#showData").hide();
	$("#hideData").show();
});