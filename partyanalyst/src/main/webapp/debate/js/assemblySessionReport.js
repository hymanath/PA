function globalOnLoadCalls()
{
	getElectionYears(1);
	getElectionYears(2);
	//getElectionYears1();
	//getSessionYears();
	//getAllSessions();
	//getDates();
	//getSessionDetails();
	//getDayWiseDetails();
}
function onLoadInitialisations()
{
	$("#dateRange").daterangepicker({
		locale:{
			format: 'DD/MM/YYYY'
		},
	});
	
	$(document).on("click",".expand-icon",function(){
		var count = $(this).attr("attr_id");
		if($(this).find("i").hasClass("glyphicon-plus"))
		{
			$(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus");
			$("#expandView"+count).show();
		}else{
			$(this).find("i").addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$("#expandView"+count).hide();
		}
	});
	
	$("#UpdateStartdateRange").daterangepicker({
		 singleDatePicker: true,
		locale:{
			format: 'DD/MM/YYYY'
		},
	});
	$(document).on("click",".expand-Member-icon",function(){
		var count = $(this).attr("attr_id");
		if($(this).find("i").hasClass("glyphicon-plus"))
		{
			$(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus");
			$(".expandViewModal"+count).show();
		}else{
			$(this).find("i").addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$(".expandViewModal"+count).hide();
		}
	});
	$(document).on("click",".panelCollapse",function(){
		$(".panelCollapse").find(".glyphicon-chevron-down").addClass("glyphicon-chevron-down").removeClass("glyphicon-chevron-up");
		if($(this).find("i").hasClass("glyphicon-chevron-down"))
		{
			$(this).find("i").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
		}else{
			$(this).find("i").addClass("glyphicon-chevron-down").removeClass("glyphicon-chevron-up");
		}
		
		$(this).closest(".panel").find(".panel-body").collapse('toggle');
	});
}
function getElectionYears(id){
	
	var jObj = {
	};		
	$.ajax({
		type:'POST',
		url: 'getAllElectionYearsAction.action',
		data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		var str='';
		if(result != null && result.length >0)
		{
			
			//str+='<select class="chosen-select" onchange="getSessionYears();">';
			str+='<option value="0">Select Election Year</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].date+'</option>';
			}
			str+='</select>';
		}
		if(id == 1){
			$("#electionYear").html(str);
			$("#electionYear").trigger("chosen:updated");
			
		}if(id == 2){
			$("#electionYearId").html(str);
			$("#electionYearId").trigger("chosen:updated");
		}
		
		$(".chosen-select").chosen({width:'100%'});
	});
}


function getSessionYears(id){
	var electionYear;
	if(id == 1){
		$("#procesingImg1").show();
		 electionYear = $("#electionYear").val();
	}else if(id == 2){
		$("#procesingImg3").show();
		electionYear = $("#electionYearId").val();
	}
	
	var jObj = {
		elctionYearId : electionYear
	};		
	$.ajax({
		  type:'POST',
		  url: 'getSessionYearsAction.action',
		 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		$("#procesingImg1").hide();
		$("#procesingImg3").hide();
		var str='';
		if(result != null && result.length >0)
		{
			str+='<option value="">Select Session Year</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].name+'">'+result[i].partyName+'</option>';
			}
			//str+='</select>';
		}
		if(id == 1){
			$("#sessionYear").html(str);
			$("#sessionYear").trigger("chosen:updated");
			var electinDate = $("#electionYear_chosen").find(".chosen-single span").html();
			var fYear = electinDate.split('-')[0];
			var tYear = electinDate.split('-')[1];
			$("#dateRange").daterangepicker({
				startDate : '01/01'+fYear,
				minDate : '01/01'+fYear,
				endDate : '31/12'+tYear,
				maxDate : '31/12'+tYear,
				locale:{
					format: 'DD/MM/YYYY'
				},
			});
		}else if(id == 2){
			$("#sessionYearId").html(str);
			$("#sessionYearId").trigger("chosen:updated");
		}
		$(".chosen-select").chosen({width:'100%'});
	});
}


function getAllSessions(id){
	var electionYear;
	var sessionYear
	if(id == 1){
		$("#procesingImg2").show();
		 electionYear = $("#electionYear").val();
		 sessionYear = $("#sessionYear").val();
	}else if(id == 2){
		$("#procesingImg4").show();
		electionYear = $("#electionYearId").val();
		sessionYear = $("#sessionYearId").val();
	}
		
	var jObj = {
		elctionYearId : electionYear,
		sessionYear : sessionYear //"2017"
	};		
	$.ajax({
		type:'POST',
		url: 'getAllSessionListAction.action',
		data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		$("#procesingImg2").hide();
		$("#procesingImg4").hide();
		var str='';
		if(result != null && result.length >0)
		{
			//str+='<select class="chosen-select">';
			str+='<option value="0">Select Assembly Session</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			//str+='</select>';
		}
		if(id == 1){
			$("#assemblySession").html(str);
			$("#assemblySession").trigger("chosen:updated");
			var electinDate = $("#sessionYear_chosen").find(".chosen-single span").html();
			var fYear = electinDate.split('-')[0];
			var tYear = electinDate.split('-')[1];
			$("#dateRange").daterangepicker({
				startDate : '01/01'+fYear,
				minDate : '01/01'+fYear,
				endDate : '31/12'+tYear,
				maxDate : '31/12'+tYear,
				locale:{
					format: 'DD/MM/YYYY'
				},
			});
		}else if(id == 2){
			$("#assemblySessionId").html(str);
			$("#assemblySessionId").trigger("chosen:updated");
		}
		$(".chosen-select").chosen({width:'100%'});
	});
}


function getDates(){
	
	//var electionYear = $("#electionYear").val();
	//var sessionYear = $("#sessionYear").val();
	var sessionId = $("#assemblySession").val();
	
	var jObj = {
		//elctionYearId : electionYear,
		//sessionYear : sessionYear,//"2017",
		adminHusseSessionId : sessionId
	};		
	$.ajax({
		  type:'POST',
		  url: 'getDatesAction.action',
		 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result[0].name != null && result[0].name != ""){
		var fYear = result[0].name.split('-')[0];
		var fMonth = result[0].name.split('-')[1];
		var fDate = result[0].name.split('-')[2].substring(0,2);
		var tYear = result[0].partyName.split('-')[0];
		var tMonth = result[0].partyName.split('-')[1];
		var tDate = result[0].partyName.split('-')[2].substring(0,2);
		$("#dateRange").daterangepicker({
			startDate : fDate+'/'+fMonth+'/'+fYear,
			minDate : fDate+'/'+fMonth+'/'+fYear,
			endDate : tDate+'/'+tMonth+'/'+tYear,
			maxDate : tDate+'/'+tMonth+'/'+tYear,
			locale:{
				format: 'DD/MM/YYYY'
			},
		});
		}
	});
}

function getSessionDetails(){
	var startDate;
	var endDate;
	var electionYear = $("#electionYear").val();
	var sessionYear = $("#sessionYear").val();
	var sessionId = $("#assemblySession").val();
	var dateStr = $('#dateRange').val();
	var dateArr = dateStr.split("-");
	if(dateArr != null && dateArr.length > 0){
		
		startDate = dateArr[0];
		endDate =  dateArr[1]; 
	}
	if(electionYear == null || electionYear == 0){
		$("#errElecMsgId").html("Select Election Year.");
		return;
	}
	/* if(sessionYear == "" || sessionYear == 0){
		$("#errSesMsgId").html("Select Election Year.");
		return;
	} */
	/* if(sessionId == null || sessionId == 0){
		$("#errSesMsgId").html("Select Election Year.");
		return;
	} */
	
	$("#assmblySessionBlock").show();
	$("#errElecMsgId").html("");
	$('#sessionDetails').html("<img src='images/Loading-data.gif'/>");
	var jObj = {
		elctionYearId : electionYear,
		sessionYear : sessionYear,//"2017",
		sessionId : sessionId,
		startDate : startDate,//23/02/2016
		endDate : endDate//11/05/2016
	};		
	$.ajax({
		type:'POST',
		url: 'getSessionDetailsAction.action',
		data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null )
		{
			buildSessionDetails(result)
		}else{
			$("#sessionDetails").html("NO DATA AVAILABLE");
		}
	});
}
function buildSessionDetails(result)
{
	var str='';
	str+='<table class="table">';
		str+='<thead>';
			str+='<th>Session Start Date</th>';
			str+='<th>Season</th>';
			str+='<th>No Of Days</th>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result)
			{
				str+='<tr>';
					str+='<td><span class="expand-icon" attr_id="'+i+'"><i class="glyphicon glyphicon-plus"></i></span>'+result[i].startDate+'</td>';
					str+='<td>'+result[i].name+'</td>';
					str+='<td>'+result[i].count+'</td>';
				str+='</tr>';
				str+='<tr id="expandView'+i+'" style="display:none;" class="expandView"><td colspan="3">';
					str+='<table class="table table-hover table-striped" style="border:1px solid #ddd;">';
						str+='<thead>';
							str+='<th>Date</th>';
							str+='<th>Party-Members</th>';
							str+='<th>Total</th>';
							str+='<th>Submited Date</th>';
						str+='</thead>';
						for(var j in result[i].candidateList)
						{
							str+='<tr>';
								str+='<td>'+result[i].candidateList[j].date+'</td>';
								str+='<td>';
									str+='<ul class="list-inline">';
									for(var k in result[i].candidateList[j].partyList)
									{
										if(result[i].candidateList[j].partyList[k].count != null && result[i].candidateList[j].partyList[k].count > 0 )
											str+='<li>'+result[i].candidateList[j].partyList[k].partyName+'</li>';
									}
									str+='</ul>';
								str+='</td>';
								str+='<td atr_session_day_id="'+result[i].candidateList[j].adminHouseSessionDayId+'" class="sessionCls" style="cursor:pointer;"><u>'+result[i].candidateList[j].count+'</u></td>';
								str+='<td>'+result[i].candidateList[j].startDate+'</td>';
							str+='</tr>';
						}
					str+='</table>';
				str+='</td></tr>';
			}
		str+='</tbody>';
	str+='</table>';
	$("#sessionDetails").html(str);
}
$(document).on("click",".sessionCls",function(){
	var sessionDayId = $(this).attr("atr_session_day_id");
	$("#sessionDayId").val(sessionDayId);
	updateMemberDetials(0,0);
});

function updateMemberDetials(partyValue,number){
	 
	$("#partyListDivId").hide();
	 //$("#partyId").val('');
	 //$("#partyId").trigger("chosen:updated");
	$("#memberDetailsModalDiv").modal('show');
	$('#memberDetailsId').html("<img src='images/Loading-data.gif'/>");
	var sessionDayId = $("#sessionDayId").val();
	var partyId;
	if(partyValue == 0){
		partyId = 0;
	}else{
		partyId = partyValue;
	}
	if(number == 0){
		$("#partyId").val('0');
		$("#partyId").trigger("chosen:updated");
	}
	var jObj = {
				 adminHouseSessionDayId : sessionDayId,
				 partyId : partyId
			};		
			$.ajax({
				  type:'POST',
				  url: 'getDayWiseDetailsAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 if(result!= null && result.length > 0){
					 $("#partyListDivId").show();
					buildMemberDetails(result,sessionDayId);
				 }else{
					$("#memberDetailsId").html("NO DATA AVAILABLE."); 
				 }
			 });
}

var candidateArr=[];
function buildMemberDetails(result,sessionDayId){
	var str='';
	var total;
	//str+='<form name="submitAssemblySessionCanScoreByEdit" id="submitApplicationId"  method="post">';
	str+='<table class="table table-bordered memberDataTableCls">';
		str+='<thead>';
			str+='<th style="background-color:#D3D3D3;">Party</th>';
			str+='<th style="background-color:#D3D3D3;">No of Member Participated</th>';
			//str+='<th>';
			//str+='<ul class="list-inline">';
				for(var k in result[0].partyList)
				{
					str+='<th style="background-color:#D3D3D3;">'+result[0].partyList[k].aspect+'</th>';
				}
				//str+='</ul>';
			//str+='</th>';
			str+='<th style="background-color:#D3D3D3;">total</th>';
			str+='<th style="background-color:#D3D3D3;">Edit</th>'; 
			str+='<th style="background-color:#D3D3D3;">Delete</th>'; 
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
			str+='<td>'+result[i].partyName+'</td>';
			str+='<td>'+result[i].count+'&nbsp;&nbsp;<span class="expand-Member-icon" attr_id="'+i+'"><i class="glyphicon glyphicon-plus" style="cursor:pointer;" title="Click Here to View Member Details."></i></span></td>';
			str+='<td> <span  class="constantClsd'+(i+1)+'"> '+result[i].avgSubCount.toFixed(1)+'</span>';
			str+='<input type="text" value="'+result[i].avgSubCount.toFixed(1)+'"  class="editClassd'+(i+1)+'" style="display:none;"/>';
			str+='</td>';
			str+='<td> <span  class="constantClsd'+(i+1)+'"> '+result[i].avgPresCount.toFixed(1)+'</span>';
			str+='<input type="text" value="'+result[i].avgPresCount.toFixed(1)+'"  class="editClassd'+(i+1)+'" style="display:none;"/>';
			str+='</td>';
			str+='<td> <span  class="constantClsd'+(i+1)+'"> '+result[i].avgCunterAttCount.toFixed(1)+'</span>';
			str+='<input type="text" value="'+result[i].avgCunterAttCount.toFixed(1)+'"  class="editClassd'+(i+1)+'" style="display:none;"/>';
			str+='</td>';
			str+='<td> <span  class="constantClsd'+(i+1)+'"> '+result[i].avgBdyLanCount.toFixed(1)+'</span>';
			str+='<input type="text" value="'+result[i].avgBdyLanCount.toFixed(1)+'"  class="editClassd'+(i+1)+'" style="display:none;"/>';
			str+='</td>';
			
			total=result[i].avgSubCount+result[i].avgPresCount+result[i].avgCunterAttCount+result[i].avgBdyLanCount;
			str+='<td> <span  class="constantClsd'+(i+1)+'"> '+total.toFixed(1)+'</span>';
			str+='<input type="text" value="'+total.toFixed(1)+'"  class="editClassd'+(i+1)+'" style="display:none;"/>';
			str+='</td>';
			str+='<td class=" " ><span class="glyphicon glyphicon-edit updateSessionDetailsd constantClsd'+(i+1)+'" attr_constant_cls="constantClsd'+(i+1)+'" attr_edit_cls="editClassd'+(i+1)+'" style="cursor:pointer;"></span>';
			str+='<span class="glyphicon glyphicon-save saveSessionDetailsd editClassd'+(i+1)+'" attr_constant_cls="constantClsd'+(i+1)+'" attr_edit_cls="editClassd'+(i+1)+'" style="display:none;cursor:pointer;" ></span>';
			str+='</td>';
			str+='<td><span class="glyphicon glyphicon-trash"></span></td>';
			//str+='<button type="button"  class="btn btn-custom btn-success" onClick="saveCandDetails();">Edit</button>';
			str+='</tr>';
			
			for(var j in result[i].candidateList)
			{
					
				str+='<tr id="expandViewId" style="display:none;" class="expandViewModal'+i+'">';
					str+='<td ></td>';
					str+='<td><span> '+result[i].candidateList[j].name+' </span> ';
					str+='<input type="hidden" id="adminHseMemId'+i+j+'" value="'+result[i].candidateList[j].adminHouseMemberId+'"/>';
					str+='<input type="hidden" value="'+sessionDayId+'" id="adminHsesesnDayId'+i+j+'"/>';
						/* str+='<select class="form-control editClass'+(i+j+1)+'" style="display:none;" >';
						if(candidateArr !=null && candidateArr.length>0){
							for(var ca in candidateArr){
								
								str+='<option value="'+candidateArr[ca].adminHouseMemberId+'">'+candidateArr[ca].name+'</option>';
							}
						}
							
						str+='</select>'; */
					str+='</td>';
					str+='<span id="errMsgFrScre"></span>';
						for(var k in result[i].candidateList[j].partyList){
							str+='<td> <span class="constantCls'+(i+j+1)+'"> '+result[i].candidateList[j].partyList[k].score+'</span> ';
							str+='<input type="text"  value="'+result[i].candidateList[j].partyList[k].score+'" class="editClass'+(i+j+1)+'" style="display:none;" id="editAspectScore'+i+j+k+'" />';
							str+='<input type="hidden" id="editAspectId'+i+j+k+'"  value="'+result[i].candidateList[j].partyList[k].speechAsceptId+'"/>';
							str+='</td>';
						}
					
					str+='<td>'+result[i].candidateList[j].total+'</td>';
					/* str+='<input type="text" value="'+result[i].candidateList[j].total+'"  class="editClass'+(i+j+1)+'" style="display:none;"/>'; */
					//str+='</td>';
					
					str+='<td class=" " ><span class="glyphicon glyphicon-edit updateSessionDetails constantCls'+(i+j+1)+'" attr_constant_cls="constantCls'+(i+j+1)+'" attr_edit_cls="editClass'+(i+j+1)+'" style="cursor:pointer;" title="Click Here to Edit Member Details."></span>';
					str+='<span class="glyphicon glyphicon-floppy-disk saveSessionDetails editClass'+(i+j+1)+'" attr_constant_cls="constantCls'+(i+j+1)+'" attr_edit_cls="editClass'+(i+j+1)+'" style="display:none;cursor:pointer;" attr_no="'+i+j+'" title="Click Here to update Member Details." attr_admin_hose_mem_id=""></span><span id="errMsgFrSaveId"></span>';
					str+='</td>';
					
					str+='<td><span class="glyphicon glyphicon-trash deleteMemberDetaCls" attr_no="'+i+j+'" style="cursor:pointer;" title="Click Here to delete a member"></span><span id="errMsgFrDelId"></span></td>';
				str+='</tr>';
			}
			
		}
		str+='</tbody>';
		str+='</table>';
		//str+='</form>';
	$("#memberDetailsId").html(str);
	/* $(".memberDataTableCls").dataTable({
		"iDisplayLength": 10,
		"aLengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]]
	 });
	  $(".memberDataTableCls").removeClass("dataTable"); */
}

$(document).on("click",".updateSessionDetails",function(){
	var constantCls = $(this).attr('attr_constant_cls');
	var editClass = $(this).attr('attr_edit_cls');
	
	
	$("."+constantCls+"").hide();
	$("."+editClass+"").show();
	//$("."+editClass+"").val(0);
	//$("."+editClass+"").val('');
	});	
$(document).on("click",".saveSessionDetails",function(){
	var constantCls = $(this).attr('attr_constant_cls');
	var editClass = $(this).attr('attr_edit_cls');
	var num  = $(this).attr('attr_no');
	
	saveCandDetails(num);
	//$("."+constantCls+"").show();
	//$("."+editClass+"").hide();
	//$("."+editClass+"").val(0);
	//$("."+editClass+"").val('');
});	

/*$(document).on("click",".updateSessionDetailsd",function(){
	var constantCls = $(this).attr('attr_constant_cls');
	var editClass = $(this).attr('attr_edit_cls');
	
	
	$("."+constantCls+"").hide();
	$("."+editClass+"").show();
	//$("."+editClass+"").val(0);
	//$("."+editClass+"").val('');
	
	});	*/
/*$(document).on("click",".saveSessionDetailsd",function(){
	var constantCls = $(this).attr('attr_constant_cls');
	var editClass = $(this).attr('attr_edit_cls');
	
	$("."+constantCls+"").show();
	$("."+editClass+"").hide();
	//$("."+editClass+"").val(0);
	//$("."+editClass+"").val('');
});*/

	var appendId=0;
	$(document).on("click",".addObserversCls",function(){
		appendId=appendId+1;
		var str='';
		str+='<div class="col-md-3 col-xs-12 col-sm-6">';
		str+='<label>Observer - '+appendId+'</label>';
			str+='<select class="chosen-select">';
				str+='<option value="1">2014-2019</option>';
				str+='<option value="1">2014-2019</option>';
				str+='<option value="1">2014-2019</option>';
			str+='</select>';
			str+='</div>';
			$("#observerNamesId").append(str);
			$(".chosen-select").chosen({width:'100%'});
			
		
	});
	
	
	var toatlUpdatedCloneCount =0;
	var mainCloneCount =0;	
	var updatedCloneCount =0;  
	var totalIndividualCloneCount =1;  
	
	//add individual for default append
	$(document).on("click","#addMemberDetailsId",function(){
		updatedCloneCount = updatedCloneCount +1;
		var memberNameCountId =0;
		
		var c = $("#updateAppendHtml").clone(true);
		c.attr({
			'id': 'updateAppendHtml'+updatedCloneCount
		});
		
		c.css("display","block");
		c.find(".memberNameCls").attr("class","memberNameValApp"+memberNameCountId);
		c.find(".memberNameCls").attr("attr_no","");
		c.find(".memberNameValApp"+memberNameCountId).attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].memberId');
		getCandidates('memberNameValApp'+memberNameCountId,'partyCls')
		
		c.find(".subjectCls").attr("id","subjectId"+updatedCloneCount);
		c.find(".subjectCls").attr("attr_no",updatedCloneCount);
		c.find(".subjectCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[0].score');
		
		c.find(".presentationCls").attr("id","presentationId"+updatedCloneCount);
		c.find(".presentationCls").attr("attr_no",updatedCloneCount);
		c.find(".presentationCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[1].score');
		
			
		c.find(".counterAttackCls").attr("id","counterAttackId"+updatedCloneCount);
		c.find(".counterAttackCls").attr("attr_no",updatedCloneCount);
		c.find(".counterAttackCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[2].score');
		
		c.find(".bodyLanguageCls").attr("id","bodyLanguageId"+updatedCloneCount);
		c.find(".bodyLanguageCls").attr("attr_no",updatedCloneCount);
		c.find(".bodyLanguageCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[3].score');
		
		//c.find(".summaryCls").attr("id","summaryId"+updatedCloneCount);
		//c.find(".summaryCls").attr("attr_count",updatedCloneCount);
		
		
		c.find(".updatingRemoveBtnCls").attr("attr_div_id","updateAppendHtml"+updatedCloneCount);
		$("#updatingClonedElements").append(c);
		
    
		 $(".memberNameValApp"+memberNameCountId).chosen();
		 $(".memberNameValApp"+memberNameCountId).trigger("chosen:updated");
		
	});
	
	


	
	//total append block
	$(document).on("click","#addTotalOneMoreBlockId",function(){
		       updatedCloneCount = updatedCloneCount +1;
		       mainCloneCount = mainCloneCount +1;
		       var generatedId = mainCloneCount+''+toatlUpdatedCloneCount;
		       var c = $("#totalUpdateAppendHtml").clone(true);
		       c.attr({
		       	'id': 'totalUpdateAppendHtml'+updatedCloneCount
		       });
		       c.find(".totalIndividualClonedElements").addClass("totalIndividualClonedElementId"+updatedCloneCount);
		       c.find(".addMemberDetailsCls").addClass("addMemberDetailsId"+updatedCloneCount);
		       c.find(".addMemberDetailsCls").removeClass("addMemberDetailsCls");
	           
		       c.find(".addMemberDetailsId"+updatedCloneCount).attr("onclick","getTotIndividuaAppBlock("+updatedCloneCount+");")
		       c.css("display","block");
		       c.find(".partyCls").attr("class","partycls"+updatedCloneCount)
		       c.find(".partycls"+updatedCloneCount).addClass("partyclsAppendCls")
		       c.find(".partycls"+updatedCloneCount).attr("attr_partyCls",updatedCloneCount);
		       getPatries("partycls"+updatedCloneCount);
		       c.find(".partycls"+updatedCloneCount).attr("onchange","getCandidates('memberNameValApp"+updatedCloneCount+"','partycls"+updatedCloneCount+"')");
		       
		       c.find(".memberNameCls").attr("class","memberNameValApp"+updatedCloneCount)
		       c.find(".memberNameCls").attr("attr_no","");
		       c.find(".memberNameValApp"+updatedCloneCount).attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].memberId');
		       
		       getCandidates('memberNameValApp'+updatedCloneCount,'');
		       c.find(".subjectCls").attr("id","subjectId"+updatedCloneCount);
		       c.find(".subjectCls").attr("attr_no",updatedCloneCount);
		       c.find(".subjectCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[0].score');
		       
		       c.find(".presentationCls").attr("id","presentationId"+updatedCloneCount);
		       c.find(".presentationCls").attr("id","presentationId"+updatedCloneCount);
		       c.find(".presentationCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[1].score');
		       	
		       c.find(".counterAttackCls").attr("id","counterAttackId"+updatedCloneCount);
		       c.find(".counterAttackCls").attr("id","presentationId"+updatedCloneCount);
		       c.find(".counterAttackCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[2].score');
		       
		       c.find(".bodyLanguageCls").attr("id","bodyLanguageId"+updatedCloneCount);
		       c.find(".bodyLanguageCls").attr("id","presentationId"+updatedCloneCount);
		       c.find(".bodyLanguageCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[3].score');
		       
		       //c.find(".summaryCls").attr("id","summaryId"+generatedId)
		       //c.find(".summaryCls").attr("attr_count",generatedId)
		       
		       c.find(".totalUpdatingRemoveBtnCls").attr("attr_div_id","totalUpdateAppendHtml"+updatedCloneCount);
		       $("#totalUpdatingClonedElements").append(c);
               
		        $(".memberNameValApp"+updatedCloneCount).chosen();
		        $(".memberNameValApp"+updatedCloneCount).trigger("chosen:updated");
		       
		       
		       $(".partycls"+updatedCloneCount).chosen();
		       $(".partycls"+updatedCloneCount).trigger("chosen:updated");
		
		
	});
	function getTotIndividuaAppBlock(count){
		totalIndividualCloneCount = totalIndividualCloneCount+1;
		var generatedId = mainCloneCount+''+toatlUpdatedCloneCount+''+totalIndividualCloneCount;
		var c = $("#updateAppendHtml").clone(true);
		c.attr({
			'id': 'updateAppendHtml'+generatedId
		});
		c.css("display","block");
		c.find(".memberNameCls").attr("class","memberNameValApp"+count);
		c.find(".memberNameCls").attr("attr_no","");
		c.find(".memberNameValApp"+count).attr("name",'assemblySessionReportVO.membersList['+count+'].memberId');
		getCandidates('memberNameValApp'+count,'partycls'+count);
		
		c.find(".subjectCls").attr("id","subjectId"+count);
		c.find(".subjectCls").attr("attr_no",count);
		c.find(".subjectCls").attr("name",'assemblySessionReportVO.membersList['+count+'].scalesList[0].score');
		
		c.find(".presentationCls").attr("id","presentationId"+count);
		c.find(".presentationCls").attr("id","presentationId"+count);
		c.find(".presentationCls").attr("name",'assemblySessionReportVO.membersList['+count+'].scalesList[1].score');
		
		c.find(".counterAttackCls").attr("id","counterAttackId"+count);
		c.find(".counterAttackCls").attr("id","presentationId"+count);
		c.find(".counterAttackCls").attr("name",'assemblySessionReportVO.membersList['+count+'].scalesList[2].score');
		
		c.find(".bodyLanguageCls").attr("id","bodyLanguageId"+count);
		c.find(".bodyLanguageCls").attr("id","presentationId"+count);
		c.find(".bodyLanguageCls").attr("name",'assemblySessionReportVO.membersList['+count+'].scalesList[3].score');
		
		//c.find(".summaryCls").attr("id","summaryId"+generatedId)
		//c.find(".summaryCls").attr("attr_count",generatedId)
		
		c.find(".updatingRemoveBtnCls").attr("attr_div_id","updateAppendHtml"+generatedId);
		
		$(".totalIndividualClonedElementId"+count).append(c);
		$(".memberNameValApp"+count).chosen();
		$(".memberNameValApp"+count).trigger("chosen:updated");
		
	}
	
	
	$(document).on("click",".updatingRemoveBtnCls",function(){
		var divId = $(this).attr("attr_div_id");
		$("#"+divId).remove();
	});
	$(document).on("click",".totalUpdatingRemoveBtnCls",function(){
		var divId = $(this).attr("attr_div_id");
		$("#"+divId).remove();
	});
	
getCandidates("memberNameValApp0","");
function getCandidates(classVal,partyDivId){
	$('.'+classVal).html('');
	var partyId = 0;
	
	if(partyDivId != ""){
		 partyId = $("."+partyDivId).val();
		
	}
	
	var jObj = {
				partyId : partyId
			};		
			$.ajax({
				  type:'POST',
				  url: 'getcandidatesForPartyAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				var str='';
				 
				 if(result != null && result.length >0)
					{
						str+='<option value="0">All</option>';
						
						for(var i in result){
							candidateArr =result; 
							str+='<option value="'+result[i].adminHouseMemberId+'">'+result[i].name+'</option>';
							
						}
						
					}
					setTimeout(function() {
						$('.'+classVal).html(str);
						$('.'+classVal).trigger("chosen:updated");
					},500);
			 });
}
	
getPatries("partyCls0");
function getPatries(id){
	 $('.'+id).html('');
	var jObj = {
			};		
			$.ajax({
				  type:'POST',
				  url: 'getPartiesAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				
				
				 var str='';
				 str+='<option value="0">All</option>';
				 if(result != null && result.length >0)
					{
						for(var i in result){
							str+='<option value="'+result[i].partyId+'">'+result[i].partyName+'</option>';
						
						}
					}
					setTimeout(function() {
						$('.'+id).html(str);
						$('.'+id).trigger("chosen:updated");
					},500);
			 });
}

	function savingApplication(){
		if($("#UpdateStartdateRange").val() > 0){
			$("#adminHouseSessionDayId").val($("#UpdateStartdateRange").val());
		}
		if(!validatingFields()){
			return;
		}
		var uploadHandler = {
				upload: function(o) {
					$("#savingAjaxImg").css("display","none");
					uploadResult = o.responseText;
					
					showSbmitStatus(uploadResult);
				}
			};
			YAHOO.util.Connect.setForm('submitAssemblySessionCanScore',true);
			YAHOO.util.Connect.asyncRequest('POST','submitAssemblySessionCanScoreDetailsAction.action',uploadHandler);
		
	}
	
	function showSbmitStatus(uploadResult){
		var stringext = uploadResult.substr(6,7);
		$("#errMsg").attr("style","color:green;");
		if(stringext == "SUCCESS"){
			$("#errMsg").html("Application Saved Successfully").css("style","color:red");
		}else{
			$("#errMsg").html("Application not saved successfully").css("style","color:red");
		}
	}
	function validatingFields(){
		$("#errMsg").html("");
		$("#errMsg").attr("style","color:red;");
		var flag =  true;
		var electionYearId = $("#electionYearId").val();
		var sessionYearId = $("#sessionYearId").val();
		var assemblySessionId = $("#assemblySessionId").val();
		var dateId = $("#UpdateStartdateRange").val();
		
		 if(electionYearId != null && electionYearId > 0){
			 flag =  true;
		}else{
			$("#errMsg").html("Please select election year");
			return flag =  false;
		}
		if(sessionYearId != null && sessionYearId > 0){
			 flag =  true;
		}else{
			$("#errMsg").html("Please select session year");
			return flag =  false;
		}
		
		if(assemblySessionId != null && assemblySessionId > 0){
			 flag =  true;
		}else{
			$("#errMsg").html("Please select assembly session ");
			return flag =  false;
		}
		
		if(assemblySessionId != null && assemblySessionId > 0){
			 flag =  true;
		}else{
			$("#errMsg").html("Please select assembly session ");
			return flag =  false;
		}
		
		if(dateId != null && dateId > 0){
			 flag =  true;
		}else{
			$("#errMsg").html("Please select date range ");
			return flag =  false;
		}  
		 $(".memberNameValApp0").each(function(){
			var num = $(this).attr("attr_no");
			if(num != "" ){
					if($(this).val() != null && $(this).val() >0){
						$("#errMsg").html("");
						flag =  true;
						$(".subjectCls").each(function(){
							var num = $(this).attr("attr_no");
							if(num != ""){
								if($(this).val() != "" && $(this).val().length >0){
									$("#errMsg").html("");
									flag =  true;
									$(".presentationCls").each(function(){
									var num = $(this).attr("attr_no");
										if(num != ""){
											if($(this).val() != "" && $(this).val().length >0){
												$("#errMsg").html("");
												flag =  true;
												$(".counterAttackCls").each(function(){
													var num = $(this).attr("attr_no");
													if(num != ""){
														if($(this).val() != "" && $(this).val().length >0){
															$("#errMsg").html("");
															flag =  true;
															$(".bodyLanguageCls").each(function(){
																var num = $(this).attr("attr_no");
																if(num != ""){
																	if($(this).val() != "" && $(this).val().length >0){
																		$("#errMsg").html("");
																		flag =  true;
																	}else{
																		$("#errMsg").html("Please enter body language score");
																		return flag =  false;
																	}
																}
															}); 
														}else{
															$("#errMsg").html("Please enter counter attack score");
															return flag =  false;
														}
													}
												}); 
											}else{
												$("#errMsg").html("Please enter presentation score ");
												return flag =  false;
											}
										}
									}); 
								}else{
									$("#errMsg").html("Please enter subject score");
									return flag =  false;
								}
							}
						});
					}else{
						$("#errMsg").html("Please select  member");
						return flag =  false;
					}
					
				}
			});  
		  return flag;
		}
	function clearFields(){
		$("#updateAppendHtml").empty();
	}

function getDatesForSaving(){
	var sessionId = $("#assemblySessionId").val();
	
	var jObj = {
		adminHuSessionId : sessionId
	};		
	$.ajax({
		  type:'POST',
		  url: 'getDatesForSavingAction.action',
		 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		var str='';
		if(result != null && result.length >0)
		{
			str+='<option value="0">Select Session Dates</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].date+'</option>';
			}
			str+='</select>';
		}
			$("#UpdateStartdateRange").html(str);
			$("#UpdateStartdateRange").trigger("chosen:updated");
			$(".chosen-select").chosen({width:'100%'});
	});
}

function saveCandDetails(num){
	 var value;
	var indexArr = ["0","1","2","3"];
	for(var i in indexArr){
		var id = indexArr[i];
		value = $("#editAspectScore"+num+id+'').val();
		if(value == "" || value == 0){
			$("#errMsgFrScre").html("<span style='color:red'>Please Enter Score.");
			return;
		}
	} 
	
	var subjScore = $("#editAspectScore"+num+0).val();
	var presScore = $("#editAspectScore"+num+1).val();
	var countrScore = $("#editAspectScore"+num+2).val();
	var bdyLangScore = $("#editAspectScore"+num+3).val();
	var adminHouseMemId=$("#adminHseMemId"+num).val();
	var adminHsesesnDayId = $("#adminHsesesnDayId"+num).val();
	
	var jObj = {
		subScore : subjScore,
		presScore : presScore,
		countrScore : countrScore,
		bdyLangScore : bdyLangScore,
		adminHouseMemId : adminHouseMemId,
		adminHsesesnDayId : adminHsesesnDayId
		
		
	}
	$.ajax({
				  type:'POST',
				  url: 'updateMemberDetailsAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				if(result == "success"){
					$("#errMsgFrSaveId").html("<span style='color:green'>Updated Successfully..</span>");
					setTimeout(function(){ 
						updateMemberDetials(0,0); 
					},1500);
				}else{
					$("#errMsgFrSaveId").html("<span style='color:green'>Updation Failed.Please Try Again.</span>");
				}
			 });
}
$(document).on("click",".deleteMemberDetaCls",function(){
	var num = $(this).attr("attr_no");
	deleteCandDetails(num);
});
function deleteCandDetails(num){
	var subjScore = $("#editAspectScore"+num+0).val();
	var presScore = $("#editAspectScore"+num+1).val();
	var countrScore = $("#editAspectScore"+num+2).val();
	var bdyLangScore = $("#editAspectScore"+num+3).val();
	var adminHouseMemId=$("#adminHseMemId"+num).val();
	var adminHsesesnDayId = $("#adminHsesesnDayId"+num).val();
	
	var jObj = {
		subScore : subjScore,
		presScore : presScore,
		countrScore : countrScore,
		bdyLangScore : bdyLangScore,
		adminHouseMemId : adminHouseMemId,
		adminHsesesnDayId : adminHsesesnDayId
		
		
	}
	$.ajax({
				  type:'POST',
				  url: 'deleteMemberDetailsAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 if(result == "success"){
					$("#errMsgFrDelId").html("<span style='color:green'>Deleted Successfully..</span>");
					setTimeout(function(){ 
						updateMemberDetials(0,0); 
					},1500);
				}else{
					$("#errMsgFrDelId").html("<span style='color:green'>Deleted failed..Please try Again.</span>");
				}
			 });
}

