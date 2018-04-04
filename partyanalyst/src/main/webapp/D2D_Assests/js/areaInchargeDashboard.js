var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

var globalLevelId=4;
var globalLevelValue='';
var globalConstituencyName='';
var globalLevelType='';

if(globalLevelId ==4){
	globalLevelValue=262;
	globalLocationName='NANDYAL';
	globalLevelType="constituency";
	$("#locationNameId").html(globalLocationName+" Assembly Details")
	$(".constituencySelectBoxCls").hide();
}else if(globalLevelId ==3){
	globalLevelValue=21;
	globalLocationName='KURNOOL';
	globalLevelType="district";
	$("#locationNameId").html(globalLocationName+" District Details")
	$(".constituencySelectBoxCls").show();
}

setTimeout(function(){ 
	onloadCalls("onload");
}, 1500);

function onloadCalls(type){
	 $(".addAssignedBlockCls").hide();
	 $(".showHideCls").hide();
	 $(".assignedUnAssignedDivCls").hide();
	 $(".submitBtnCls").hide();
	 if(globalLevelType == "district" && type =="onchange"){
		 globalLevelId =3;
		 globalLevelValue=21;
		 globalLocationName='KURNOOL';
	 }
	 if(globalLevelId ==3){
		 getAreaInchargesStatusWiseCountDistrict("mainOverViewConstituencyBlockDivId",'constituency','district',type);
		$("#constituencyId").chosen();
		if(type=="onload"){
			getConstituenciesByDistrict();
		}
		
		$(".showHideAddAreaIncharge").hide();
	 }else{
		 getAreaInchargesStatusWiseCount("mainOverViewConstituencyBlockDivId",'constituency','constituency',type);
		 getAreaInchargeAssignedBoothDetails();
		 getAssignedAndUnAssignedBooths();
	 }
}

 
function highcharts(id,type,data,plotOptions,title,tooltip,legend){
	'use strict';
	$('#'+id).highcharts({
		colors:['#FF9900','#8D4553','#CCCCCC','#F25C81','#0D9615'],
		chart: type,
		title: title,
		tooltip:tooltip,
		subtitle: {
			text: null
		},
		plotOptions: plotOptions,
		legend:legend,
		series: data
	});
}

 function getAreaInchargesStatusWiseCountDistrict(divId,locationType,loadType,changeType){
	 $("#"+divId).html(spinner);
    
	var jsObj={
        levelId :3,
        levelValue : globalLevelValue
    }
    $.ajax({
      type:'GET',
      url:'getAreaInchargesStatusWiseCountAction.action',
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			buildAreaInchargesStatusWiseCount(result,divId,locationType,loadType,changeType);
		}else{
			 $("#"+divId).html("No Data Available");
		}
    });
}

  function getAreaInchargesStatusWiseCount(divId,locationType,loadType,changeType){
	 $("#"+divId).html(spinner);
    
	var jsObj={
        levelId :globalLevelId,
        levelValue : globalLevelValue
    }
    $.ajax({
      type:'GET',
      url:'getAreaInchargesStatusWiseCountAction.action',
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			buildAreaInchargesStatusWiseCount(result,divId,locationType,loadType,changeType);
		}else{
			 $("#"+divId).html("No Data Available");
		}
    });
}

function buildAreaInchargesStatusWiseCount(result,divId,locationType,loadType,changeType){
	var str='';
	
	str+='<div class="row">';
		str+='<div class="col-sm-4">';
			str+='<div class="border_block_style panelheights">';
				str+='<h4 class="text-center">'+globalLocationName+' - (<b>'+result.total+'</b> Booths)</h4>';
				str+='<div class="row">';
					str+='<div id="mainBlockPieChartDivId'+locationType+'" style="height:200px;"></div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-8">';
			str+='<div class="border_block_style panelheights">';
				str+='<div class="row">';
					str+='<div class="col-sm-4">';
						str+='<div class="border_block_style m_top30">';
							str+='<h4 class="m_top20 text-center">Added Area incharges</h4>';
							str+='<h3 class="font_weight m_top20 text-center">'+result.inchargeCount+'</h3>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="border_block_style m_top30">';
							str+='<h4 class="m_top20 text-center">Assigned Booths</h4>';
							str+='<h3 class="font_weight m_top20 text-center">'+result.assignedCount+' <span style="font-size:18px;color:green;">'+result.assignPer+' %</span></h3>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="border_block_style m_top30">';
							str+='<h4 class="m_top20 text-center">Pending Booths</h4>';
							str+='<h3 class="font_weight m_top20 text-center">'+result.unAssignedCount+' <span style="font-size:18px;color:red;">'+result.pendingPers+' %</span></h3>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#"+divId).html(str);
	
	
	var categoryNameArr=[];
	var assignedCountArr=0;
	var pendingCountArr=0;
	if(result !=null){
			categoryNameArr.push("AssignedBooths");
			categoryNameArr.push("PendingBooths");
			assignedCountArr=result.assignPer;
			pendingCountArr=result.pendingPers;
	}

	var id = 'mainBlockPieChartDivId'+locationType+'';
	var type = {
		type: 'pie',
		backgroundColor:'transparent',
		options3d: {
			enabled: true,
			alpha: 25
		}
	};
	var title = {
		text: ''
	};
	var tooltip = {
		useHTML: true,
		backgroundColor: '#FCFFC5', 
		formatter: function() {
			var cnt = this.point.count;
			return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+(this.y)+"%</b>";
		}  
	}; 
	var plotOptions ={ 
		pie: {
			innerSize: 80,
			depth: 70,
			dataLabels:{
				useHTML: true,
				enabled: false,
				formatter: function() {
						if (this.y === 0) {
							return null;
						} else {
							return "<b style='color:"+this.point.color+"'>"+this.point.name+"<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
						}
					} 
			},
			showInLegend: true
		},
	};
	var legend = {
		enabled: true,
		layout: 'horizontal',
		align: 'left',
		verticalAlign: 'bottom',
		useHTML: true,
		
		labelFormatter: function() {
			return '<div><span style="color:'+this.color+'">'+this.name + '-'+(this.y)+'%</span></div>';
		}
	};
	var data = [{
		name: '',
		data: [
				{
				  name: 'Assigned',
				  y: assignedCountArr,
				  color:"green"
				},
				{
				  name: 'Pending',
				  y: pendingCountArr,
				  color:"red"
				}
			]
	}];
	setTimeout(function(){
		highcharts(id,type,data,plotOptions,title,tooltip,legend);
	}, 1000);  
	
	var maxHeight = 0;

	$(".panelheights").each(function(){
	   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
	});
	$(".panelheights").height(maxHeight);
	
	if(loadType == "district" && changeType == "onchange"){
		 globalLevelId=4;
		 globalLevelValue=$("#constituencyId").val();
		 globalLocationName=$("#constituencyId option:selected").text();
		 getAreaInchargesStatusWiseCount("mainOverDistrictViewBlockDivId",'district','constituency',changeType);
		 getAreaInchargeAssignedBoothDetails();
		 getAssignedAndUnAssignedBooths();
		 $(".showHideAddAreaIncharge").show();
	}
}

function getAreaInchargeAssignedBoothDetails(){
	$("#alreadyAreaInchargeDetailsDivId").html(spinner);		
    var jsObj={
        levelId :globalLevelId,
        levelValue : globalLevelValue
    }
    $.ajax({
      type:'GET',
      url:'getAreaInchargeAssignedBoothDetailsAction.action',
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
      if(result !=null && result.length>0){
		  buildAreaInchargeAssignedBoothDetails(result);
	  }
    });
  }
  function  buildAreaInchargeAssignedBoothDetails(result){
		var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-12 m_top10">';
			str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" >';
				  str+='<div class="panel panel-default" style="box-shadow:none !important;">';
					str+='<div class="panel-heading" role="tab" id="headingOne">';
					  str+='<h4 class="panel-title">';
						str+='<a role="button" class="panelCollapseIcon1" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">';
							str+='<h4 class="panel-title text-capital">Area Incharge Details</h4>';
						str+='</a>';
					  str+='</h4>';
					str+='</div>';
					str+='<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">';
					  str+='<div class="panel-body">';
						str+='<div class="row">';
							str+='<div class="col-sm-12">';
							for(var i in result){
								str+='<div class="border_block_style m_top10">';
									str+='<div class="media">';
									  str+='<div class="media-left">';
										if(result[i].image == null || result[i].image == ""){
											str+='<img class="media-object" src="coreApi/img/profile.jpg" alt="" style="width:100px;height:80px;">';
										}else{
											str+='<img class="media-object" src="https://mytdp.com/images/cadre_images/'+result[i].image+'" alt="'+result[i].image+'" style="width:100px;height:80px;">';
										}
										  
									  str+='</div>';
									  str+='<div class="media-body">';
											str+='<div class="row">';
												str+='<div class="col-sm-12">';
													str+='<ul class="list-inline list_style">';
														str+='<li>Name : <b class="text-capital">'+result[i].name+'</b></li>';
														str+='<li>Relative Name : <b class="text-capital">'+result[i].relativeName+'</b></li>';
														str+='<li>Age : <b>'+result[i].age+'</b></li>';
														str+='<li>Gender : <b>'+result[i].gender+'</b></li>';
													str+='</ul>';
												str+='</div>';
											str+='</div>';
											str+='<div class="row">';
												str+='<div class="col-sm-12 m_top10">';
													str+='<ul class="list-inline list_style">';
														str+='<li>MemberShip No: : <b>'+result[i].memberShipNo+'</b></li>';
														str+='<li>Phone No : <b>'+result[i].mobileNo+'</b></li>';
													str+='</ul>';
												str+='</div>';
											str+='</div>';
											str+='<div class="row">';
												str+='<div class="col-sm-12 m_top10">';
													str+='<ul class="list-inline list_style">';
														str+='<li>Assigned Booths :';
														for(var j in result[i].assignIds){
															str+='<b>'+result[i].assignIds[j]+',</b>';
														}
														str+='</li>';
													str+='</ul>';
												str+='</div>';
											str+='</div>';
											str+='<div class="row">';
												str+='<div class="col-sm-12">';
													str+='<h5 class="fa fa-edit popupViewAreaIncDetUCls pull-right"  attr_cadre_id="'+result[i].id+'" style="font-size:20px;margin-top: -20px;"></h5>';
												str+='</div>';
											str+='</div>';
									  str+='</div>';
									str+='</div>';
								str+='</div>';
							}
							str+='</div>';
						str+='</div>';
					  str+='</div>';
					str+='</div>';
				 str+='</div>';
			 str+='</div>';
		str+='</div>';
		str+='</div>';
		$("#alreadyAreaInchargeDetailsDivId").html(str);
  }
 $(document).on("click",".showHideAddAreaIncharge",function(){
	 $(".addAssignedBlockCls").show();
	 $("#searchValId").val('');
	 
 });
 $(document).on("click",".getSearchDetailsCls",function(){
	var voterId=0;
	var mobileNo="";
	var memberShipId="";
	
	var selectedType="";
	 $( ".getSelectedVal" ).each(function() {
		if($(this).is(":checked")){
			selectedType=$(this).val();
		}
	});
	if(selectedType == "membershipId"){
		memberShipId = $("#searchValId").val();
		voterId=0;
		mobileNo="";
	}else if(selectedType == "voterId"){
		memberShipId = "";
		voterId=$("#searchValId").val();
		mobileNo="";
	}else if(selectedType == "mobileNo"){
		memberShipId =""; 
		voterId=0;
		mobileNo=$("#searchValId").val();
	}
	$(".showHideCls").show();
	getAreaInchargeDetails(memberShipId,voterId,mobileNo,selectedType);
 });
 
   function getAreaInchargeDetails(memberShipId,voterId,mobileNo,selectedType){
	    if(selectedType == "membershipId"){
	   var numericExpression = /^[0-9]+$/;
					if(!memberShipId.trim().match(numericExpression)){
						$('#searchErrDiv').html('Enter  Number Digits Only.');
						return;
					}else{
						$('#searchErrDiv').html(' ');
					}
			if(memberShipId.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Membership Card No.');
				return;
			}else{
						$('#searchErrDiv').html(' ');
					}
			if(memberShipId.trim().length != 8||memberShipId.trim().length > 8)
			{
				$('#searchErrDiv').html('Invalid memberShipCardNo.');
				return;	
			}else{
						$('#searchErrDiv').html(' ');
					}
	   }
    if(selectedType == "voterId"){
		if(voterId.length == 0 || voterId == null )
			{
			   $('#searchErrDiv').html('Please enter Voter Card No.');
				return;
			}
     }
	 if(selectedType == "mobileNo"){
		var numericExpression = /^[0-9]+$/;
		 if(!mobileNo.trim().match(numericExpression)){
			$('#searchErrDiv').html('Enter Number Digits Only.');
						return;
		}else{
			$('#searchErrDiv').html(' ');
		  }	
		if(mobileNo.trim().length == 0 )
			{
			  $('#searchErrDiv').html('Please enter Mobile No.');
				return;
			}
			if(mobileNo.trim().length != 10 ||mobileNo.trim().length > 10)
			{
				$('#searchErrDiv').html('Invalid Mobile No.');
				return;
			} 
	 }
	 
	   $("#areaInchargeSearchDetailsDivId").html(spinner);
      var jsObj={
		    voterId:voterId,
			mobileNo :mobileNo,
		    memberShipId :memberShipId
		}
		$.ajax({
		  type:'GET',
		  url:'getAreaInchargeDetailsAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		  if(result !=null && result.length){
			  buildAreaInchargeDetails(result);
		  }else{
			    $("#areaInchargeSearchDetailsDivId").html("No Data Available");
		  }
		});
}

function buildAreaInchargeDetails(result){
	var str='';
	
		for(var i in result){
			str+='<div class="border_block_style m_top10">';
				str+='<div class="media">';
				  str+='<div class="media-left">';
					if(result[i].image == null || result[i].image == ""){
						str+='<img class="media-object" src="coreApi/img/profile.jpg" alt="" style="width:100px;height:80px;">';
					}else{
						str+='<img class="media-object" src="https://mytdp.com/images/cadre_images/'+result[i].image+'" alt="'+result[i].image+'" style="width:100px;height:80px;">';
					}
					  
				  str+='</div>';
				  str+='<div class="media-body">';
						str+='<div class="row">';
							str+='<div class="col-sm-12">';
								str+='<ul class="list-inline list_style">';
									str+='<li>Name : <b class="text-capital">'+result[i].name+'</b></li>';
									str+='<li>Relative Name : <b class="text-capital">'+result[i].relativeName+'</b></li>';
									str+='<li>Age : <b>'+result[i].age+'</b></li>';
									str+='<li>Gender : <b>'+result[i].gender+'</b></li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
						str+='<div class="row">';
							str+='<div class="col-sm-12 m_top10">';
								str+='<ul class="list-inline list_style">';
									str+='<li>Address : <b>'+result[i].address+'</b></li>';
									str+='<li>Caste : <b>'+result[i].caste+'</b></li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
						str+='<div class="row">';
							str+='<div class="col-sm-12 m_top10">';
								if(result[i].isActive == 'N'){
									str+='<ul class="list-inline list_style">';
									str+='<li class="text-center" style="font-size:14px;">';
										str+='<label class="checkbox-inline">';
										  str+='<input type="checkbox"  name="inlineRadioOptions" id="inlineRadio11" attr_count="'+i+'" value="'+result[i].isActive+'" class="makeInchargeCls">Make Area Incharge';
										str+='</label>';
									str+='</li>';
									str+='<li><button type="button" id="btn'+i+'" class="btn btn-success btn-sm makeInchargeAssignedBoothsCls" style="display:none;" attr_cadre_id="'+result[i].id+'">Assigned Booths</button></li>';
									str+='</ul>';
								}else{
									str+='<h4 class="text-center font_weight" style="color:green;">Already Area Incharge</h4>';
								}
								
							str+='</div>';
						str+='</div>';
				  str+='</div>';
				str+='</div>';
			str+='</div>';
		}
	$("#areaInchargeSearchDetailsDivId").html(str);
}

$(document).on("click",".makeInchargeAssignedBoothsCls",function(){//srujana
		$(".assignedUnAssignedDivCls").show();
		$(".submitBtnCls").show();
		var cadreId =$(this).attr('attr_cadre_id');
		$('.submitBtnCls').attr("attr_cadre_id",cadreId)
		getAssignedAndUnAssignedBooths();
});
$(document).on("click",".makeInchargeCls",function(){
  if($(this).is(':checked')){
		$("#btn"+$(this).attr('attr_count')).show();
  }
  else{
	  $("#btn"+$(this).attr('attr_count')).hide();
  }
});
  
function getAssignedAndUnAssignedBooths(){
    var jsObj={
      levelId :globalLevelId,
      levelValue : globalLevelValue
    }
    $.ajax({
      type:'GET',
      url:'getAssignedAndUnAssignedBoothsAction.action',
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
      if(result !=null){
		  buildAssignedBoothsDetils(result);
		  buildUnAssignedBoothsDetails(result);
	  }
    });
  }

 function  buildAssignedBoothsDetils(result){
	 var str='';
	 str+='<div class="col-sm-12">';
		str+='<h4 class="">Assigned Booths : '+result.assignedCount+' Booths out of '+result.total+' booths</h4>';
	 str+='</div>';
	 
	  str+='<div class="col-sm-12 ">';
		str+='<div class="border_block_style m_top5">';
			str+='<div class="table-responsive">';
			var xindex = 0;
				str+='<table>';
					str+='<tbody>';
						var panchayatName = "";
						for(var i in result.assignBoothList){
							if( xindex == 0)
							{
								str+='<tr>';
							}
							if(result.assignBoothList[i].panchayatName != null && result.assignBoothList[i].panchayatName != "Panchayat"){
								panchayatName = result.assignBoothList[i].panchayatName;
							}else{
								panchayatName = "";
							}
							str+='<td><button type="button" class="booths-btn-un" data-toggle="popover" data-trigger="focus" title="Details" data-content="Assigned To:<b>'+result.assignBoothList[i].name+'</b><br/>Location:<b>'+panchayatName+','+result.assignBoothList[i].tehsilName+'</b>">'+result.assignBoothList[i].id+'</button></td>';
							xindex++;
							if( xindex == 17){
								str+='</tr>';
								xindex = 0;
							} 
						}
						
						
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		 str+='</div>';
	 str+='</div>';
	 $("#areaInchargeassignedBoothsDivId").html(str);
	    $('[data-toggle="popover"]').popover({
			placement : 'top',
			trigger : 'hover',
			html : true,
			content : ''
    });
 }
 function  buildUnAssignedBoothsDetails(result){
	 var str='';
	 str+='<div class="col-sm-12 m_top10">';
		str+='<h4 class="">Un Assigned Booths : '+result.unAssignedCount+' Booths out of '+result.total+' booths</h4>';
	 str+='</div>';
	 
	  str+='<div class="col-sm-12">';
		str+='<div class="border_block_style m_top5">';
			str+='<div class="table-responsive">';
			var xindex = 0;
				str+='<table>';
					str+='<tbody>';
					var panchayatName = "";
						for(var i in result.unAssignBoothList){
							if( xindex == 0)
							{
								str+='<tr>';
							}
							if(result.unAssignBoothList[i].panchayatName != null && result.unAssignBoothList[i].panchayatName != "Panchayat"){
								panchayatName = result.unAssignBoothList[i].panchayatName;
							}else{
								panchayatName = "";
							}
							str+='<td><button type="button" class="booths-btn-un assignedUnAssignedCls" data-placement="top" data-toggle="popover" data-trigger="focus" title="Details" data-content="Location:<b>'+panchayatName+','+result.unAssignBoothList[i].tehsilName+'</b>">'+result.unAssignBoothList[i].id+'</button></td>';
							xindex++;
							if( xindex == 17){
								str+='</tr>';
								xindex = 0;
							} 
						}
						
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			
		 str+='</div>';
	 str+='</div>';
	 $("#areaInchargeUnAssignedboothsDivId").html(str);
	 $('[data-toggle="popover"]').popover({
			placement : 'top',
			trigger : 'hover',
			html : true,
			content : ''
    });
 }
 
  $(document).on("click",".assignedUnAssignedCls",function(){
		if($(this).hasClass("booths-btn-green")){
			$(this).removeClass("booths-btn-green");
			$(this).addClass("booths-btn-un");
		}else{
			$(this).removeClass("booths-btn-un");
			$(this).addClass("booths-btn-green");
		}
		
  });
  
   $(document).on("click",".submitBtnCls",function(){
		var cadreId =$(this).attr('attr_cadre_id');
		var boothIds=[];
		$('.booths-btn-green').each(function()
		{
			boothIds.push($(this).text());
		});
		savingAssigningBooths(cadreId,boothIds,"save");
	});
	function savingAssigningBooths(cadreId,boothIds,saveType){
		if(saveType == "save"){
			$(".spinnerDivId").show();
		}else{
			$(".spinnereditDivId").show();
		}
		
		var jsObj={
			boothIds:boothIds,
		    cadreId : cadreId
		}
		$.ajax({
			type:'GET',
			url:'savingAssigningBoothsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(saveType == "save"){
				$(".spinnerDivId").hide();
			}else{
				$(".spinnereditDivId").hide();
			}
			
			$("#popupSuccessModalDivId").modal("show");
			if(result != null && result.message == "SUCCESS"){
				$("#successMsgDivId").html("<h4 class='font_weight' style='color:green;text-align:center;'>Assigned Booths Successfully</h4>")
				setTimeout(function(){ 
					$("#popupSuccessModalDivId").modal("hide");
					if(saveType == "edit"){
						$("#popupViewAreaIncDetModal").modal("hide");
					}
					onloadCalls("onchange");
					
				}, 3500);
			}else{
				$("#successMsgDivId").html("<h4 class='font_weight' style='color:red;text-align:center;'>Please Try Again Later</h4>")
				setTimeout(function(){ 
					$("#popupSuccessModalDivId").modal("hide");
					if(saveType == "edit"){
						$("#popupViewAreaIncDetModal").modal("hide");
					}
					
				}, 3500);
			}
		});
	}
  function getConstituenciesByDistrict(){
	  $("#constituencyId").html('');
    var jsObj={
        districtId:globalLevelValue
    }
    $.ajax({
      type:'GET',
      url:'getConstituenciesByDistrictForLoationDashBoardAction.action',
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			$("#constituencyId").append("<option value='0'>Select Constituency</option>");
			for(var i in result){
				$("#constituencyId").append("<option value="+result[i].locationId+">"+result[i].locationName+"</option>")
			}
			$("#constituencyId").chosen();
			$("#constituencyId").trigger("chosen:updated");
		}
    });
}

 $(document).on("change","#constituencyId",function(){	 
	 globalLevelId=4;
	 globalLevelValue=$("#constituencyId").val();
	 globalLocationName=$("#constituencyId option:selected").text();
	 getAreaInchargesStatusWiseCount("mainOverDistrictViewBlockDivId",'district','constituency',"onchange");
	 getAreaInchargeAssignedBoothDetails();
     getAssignedAndUnAssignedBooths();
	 $(".showHideAddAreaIncharge").show();
});


$(document).on("click",".popupViewAreaIncDetUCls",function(){
		$("#popupViewAreaIncDetModal").modal('show');
		var cadreId =$(this).attr("attr_cadre_id");
		editAssignedInchargeDetails(cadreId)
	});
	function editAssignedInchargeDetails(cadreId){
		$("#editAreaInchargeDetailsDivId").html(spinner);
		var jsObj={
			levelId :globalLevelId,
			levelValue : globalLevelValue,
		    cadreId : cadreId
		}
		$.ajax({
			type:'GET',
			url:'editAssignedInchargeDetailsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
			   buildingAssignedAndUnAssignedBooths(result);
			}else{
				$("#editAreaInchargeDetailsDivId").html("No Data Available")
			}
			
		});
	}
	function buildingAssignedAndUnAssignedBooths(result) {
		var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-12">';
			
				str+='<div class="border_block_style m_top10">';
					str+='<div class="media">';
					  str+='<div class="media-left">';
						if(result.image == null || result.image == ""){
							str+='<img class="media-object" src="coreApi/img/profile.jpg" alt="" style="width:100px;height:80px;">';
						}else{
							str+='<img class="media-object" src="https://mytdp.com/images/cadre_images/'+result.image+'" alt="'+result.image+'" style="width:100px;height:80px;">';
						}
						  
					  str+='</div>';
					  str+='<div class="media-body">';
							str+='<div class="row">';
								str+='<div class="col-sm-12">';
									str+='<ul class="list-inline list_style">';
										str+='<li>Name : <b class="text-capital">'+result.name+'</b></li>';
										str+='<li>Relative Name : <b class="text-capital">'+result.relativeName+'</b></li>';
										str+='<li>Age : <b>'+result.age+'</b></li>';
										str+='<li>Gender : <b>'+result.gender+'</b></li>';
									str+='</ul>';
								str+='</div>';
							str+='</div>';
							str+='<div class="row">';
								str+='<div class="col-sm-12 m_top10">';
									str+='<ul class="list-inline list_style">';
										str+='<li>Address: <b>'+result.address+'</b></li>';
										str+='<li>Caste: <b>'+result.caste+'</b></li>';
									str+='</ul>';
								str+='</div>';
							str+='</div>';
							str+='<div class="row">';
								str+='<div class="col-sm-12 m_top10">';
									str+='<ul class="list-inline list_style">';
										str+='<li>Designation: <b>Area Incharge</b></li>';
										str+='<li>Assigned Booths: ';
											for(var j in result.assignBoothIds){
												str+='<b>'+result.assignBoothIds[j]+',</b>';
											}
										str+='</li>';
									str+='</ul>';
								str+='</div>';
							str+='</div>';
							
					str+='</div>';		
				str+='</div>';		
			str+='</div>';				
		str+='</div>';
	str+='</div>';
		
		
		
		str+='<div class="row">';
			str+='<div class="col-sm-12">';
				str+='<ul class="list-inline list_style pull-right">';
					str+='<li><img src="D2D_Assests/images/spinner.gif" style="width:40px;height:40px;display:none;" id="removeAssigspinnerDivId"></img></li>';
					str+='<li class="font_weight removeInchageCls m_top10" style="font-size:17px;" attr_cadre_id="'+result.id+'" attr_booth_id="0"><span class="label label-warning">Remove From Area Incharge</span></li>';
					str+='<li class="font_weight menu-cls m_top10" style="font-size:17px;"><span class="label label-warning">Remove Assigned Booths</span></li>';
				str+='</ul>';
				str+='<div class="menu-data-cls">';
					str+='<div class="arrow_box_top">';
						str+='<div class="row">';
							str+='<div class="col-sm-12">';
								str+='<ul class="list-inline removeAssignedBoothsCls">';
									
										for(var j in result.assignBoothIds){
											str+='<li>'+result.assignBoothIds[j]+'<span class="glyphicon glyphicon-trash removeInchageCls" style="font-size:12px;" attr_cadre_id="'+result.id+'" attr_booth_id="'+result.assignBoothIds[j]+'"></span></li>';
										}
									
								str+='</ul">';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
			str+='</div>';
		str+='</div>';	
		
		str+='<div class="row">';
			 str+='<div class="col-sm-12 m_top10">';
				str+='<h4 class="">Add Booths</h4>';
			str+='</div>';
		str+='</div>';
	 
	 str+='<div class="row">';
		str+='<div class="col-sm-12">';
			str+='<div class="border_block_style m_top5">';
				str+='<h4 class="">Un Assigned Booths : '+result.unAssignedCount+' Booths out of '+result.total+' booths</h4>';
				str+='<div class="table-responsive">';
				var xindex = 0;
					str+='<table class="m_top10">';
						str+='<tbody>';
						var panchayatName = "";
							for(var i in result.unAssignBoothList){
								if( xindex == 0)
								{
									str+='<tr>';
								}
								
								str+='<td><button type="button" class="booths-btn-un assignedUnAssignedCls" data-placement="top" data-toggle="popover" data-trigger="focus" title="Details" data-content="Location:<b>'+panchayatName+','+result.unAssignBoothList[i].tehsilName+'</b><br/> Click For Assign Booth">'+result.unAssignBoothList[i].id+'</button></td>';
								xindex++;
								if( xindex == 16){
									str+='</tr>';
									xindex = 0;
								} 
							}
							
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
				
			 str+='</div>';
		 str+='</div>';
	 str+='</div>';
	 str+='<div class="row">';
			str+='<div class="col-sm-1 pull-right">';
				str+='<img src="D2D_Assests/images/spinner.gif" style="width:40px;height:40px;display:none;" class="spinnereditDivId"></img>';
			str+='</div>';
			str+='<div class="col-sm-5 pull-right">';
				str+='<button class="btn btn-success btn-sm pull-right applyChangesCls" style="width: 150px;" attr_cadre_id="'+result.id+'">Apply Changes</button>';
			str+='</div>';
		str+='</div>';
		
	$("#editAreaInchargeDetailsDivId").html(str);
	 $('[data-toggle="popover"]').popover({
			placement : 'top',
			trigger : 'hover',
			html : true,
			content : ''
    });
}
$(document).on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});	
$(document).on("click",".removeInchageCls",function(){
	var cadreId =$(this).attr("attr_cadre_id");
	var boothId =$(this).attr("attr_booth_id");
	
	if(!confirm('Are you sure want to remove?'))
	return;
	
	deleteAreaInchargeAssignBooths(cadreId,boothId);
});
	
function deleteAreaInchargeAssignBooths(cadreId,boothId){
	$("#removeAssigspinnerDivId").show();
	  var jsObj={
	   candidateId:cadreId,
	   boothId:boothId
	  }
	  $.ajax({
	   type:'GET',
	   url: 'deleteAreaInchargeAssignBoothsAction.action',
	   data: {task:JSON.stringify(jsObj)}
	 }).done(function(result){
		 $("#removeAssigspinnerDivId").hide();
		 $("#popupSuccessModalDivId").modal("show");
			if(result != null && result == "success"){
				$("#successMsgDivId").html("<h4 class='font_weight' style='color:green;text-align:center;'>Deleted Successfully</h4>")
				setTimeout(function(){ 
					$("#popupSuccessModalDivId").modal("hide");
					$("#popupViewAreaIncDetModal").modal("hide");
					onloadCalls("onchange");
					
					
				}, 3500);
			}else{
				$("#successMsgDivId").html("<h4 class='font_weight' style='color:red;text-align:center;'>Please Try Again Later</h4>")
				setTimeout(function(){ 
					$("#popupSuccessModalDivId").modal("hide");
				}, 3500);
			}
	});
} 
 $(document).on("click",".applyChangesCls",function(){
		var cadreId =$(this).attr('attr_cadre_id');
		var boothIds=[];
		$('.booths-btn-green').each(function()
		{
			boothIds.push($(this).text());
		});
		savingAssigningBooths(cadreId,boothIds,"edit");
	});
	$(document).on("click",".getSelectedVal",function(){
   		$("#searchValId").val('');
		$("#areaInchargeSearchDetailsDivId").html('');
		$("#areaInchargeassignedBoothsDivId").html('');
		$("#areaInchargeUnAssignedboothsDivId").html('');
		$(".submitBtnCls").hide();
	});