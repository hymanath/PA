 getAreaInchargesStatusWiseCount();
 getAreaInchargeAssignedBoothDetails();
 getAssignedAndUnAssignedBooths();
 
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

 
  function getAreaInchargesStatusWiseCount(){
    var jsObj={
        levelId :4,
        levelValue : 262
    }
    $.ajax({
      type:'GET',
      url:'getAreaInchargesStatusWiseCountAction.action',
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			buildAreaInchargesStatusWiseCount(result);
		}
    });
}

function buildAreaInchargesStatusWiseCount(result){
	var str='';
	
	str+='<div class="row">';
		str+='<div class="col-sm-4">';
			str+='<div class="border_block_style panelheights">';
				str+='<h4 class="text-center">Ichapuram - (<b>'+result.total+'</b> Booths)</h4>';
				str+='<div class="row">';
					str+='<div id="mainBlockPieChartDivId" style="height:200px;"></div>';
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
	$("#mainOverViewBlockDivId").html(str);
	
	
	var categoryNameArr=[];
	var assignedCountArr=0;
	var pendingCountArr=0;
	if(result !=null){
			categoryNameArr.push("AssignedBooths");
			categoryNameArr.push("PendingBooths");
			assignedCountArr=result.assignPer;
			pendingCountArr=result.pendingPers;
	}

	var id = 'mainBlockPieChartDivId';
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
}

function getAreaInchargeAssignedBoothDetails(){
      
    var jsObj={
        levelId :4,
        levelValue : 262
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
 $(document).on("click",".addAssignedBlockCls",function(){
	 $(".addAssignedBlockCls").show();
	 
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
	getAreaInchargeDetails(memberShipId,voterId,mobileNo);
 });
 
   function getAreaInchargeDetails(memberShipId,voterId,mobileNo){
	   
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
										  str+='<input type="checkbox" name="inlineRadioOptions" id="inlineRadio11" value="'+result[i].isActive+'" class="makeInchargeCls">Make Area Incharge';
										str+='</label>';
									str+='</li>';
									str+='<li><button type="button" class="btn btn-success btn-sm makeInchargeAssignedBoothsCls">Assigned Booths</button></li>';
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

function getAssignedAndUnAssignedBooths(){
    var jsObj={
      levelId :4,
      levelValue : 262
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
						
						for(var i in result.assignBoothList){
							if( xindex == 0)
							{
								str+='<tr>';
							}
							str+='<td><button type="button" class="booths-btn-un tooltipCls assignedUnAssignedCls"  data-toogle="tooltip" title="Assigned To:'+result.assignBoothList[i].name+' Location: '+result.assignBoothList[i].panchayatName+','+result.assignBoothList[i].tehsilName+' Click For Assigned Booth">'+result.assignBoothList[i].id+'</button></td>';
							xindex++;
							if( xindex == 17){
								str+='</tr>';
								xindex = 0;
							} 
						}
						
						
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			/* str+='<ul class="list-inline workStagesCls">';
			for(var i in result.assignBoothList){
					str+='<li>';
						str+='<span class="tooltipCls" data-toogle="tooltip" title="Assigned To:'+result.assignBoothList[i].name+' <br/>Location: '+result.assignBoothList[i].panchayatName+','+result.assignBoothList[i].tehsilName+' <br/> Click For Assigned Booth">'+result.assignBoothList[i].id+'</span>';
					str+='</li>';
			} */
		 str+='</div>';
	 str+='</div>';
	 $("#areaInchargeassignedBoothsDivId").html(str);
	 $(".tooltipCls").tooltip();
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
						for(var i in result.unAssignBoothList){
							if( xindex == 0)
							{
								str+='<tr>';
							}
							str+='<td><button type="button" class="booths-btn-un tooltipCls assignedUnAssignedCls" data-toogle="tooltip" title="Assigned To:'+result.unAssignBoothList[i].name+' Location: '+result.unAssignBoothList[i].panchayatName+','+result.unAssignBoothList[i].tehsilName+' Click For Assigned Booth">'+result.unAssignBoothList[i].id+'</button></td>';
							xindex++;
							if( xindex == 17){
								str+='</tr>';
								xindex = 0;
							} 
						}
						
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
			
			/* str+='<ul class="list-inline workStagesCls">';
			for(var i in result.unAssignBoothList){
					str+='<li>';
						str+='<span class="tooltipCls" data-toogle="tooltip" title="Assigned To:'+result.unAssignBoothList[i].name+' <br/>Location: '+result.unAssignBoothList[i].panchayatName+','+result.unAssignBoothList[i].tehsilName+' <br/> Click For Assigned Booth">'+result.unAssignBoothList[i].id+'</span>';
					str+='</li>';
			} */
		 str+='</div>';
	 str+='</div>';
	 $("#areaInchargeUnAssignedboothsDivId").html(str);
	  $(".tooltipCls").tooltip();
 }
  $(document).on("click",".assignedUnAssignedCls",function(){
		$(this).addClass("booths-btn-green");
		
  });
   $(document).on("click",".makeInchargeAssignedBoothsCls",function(){
		$(".assignedUnAssignedDivCls").show();
		
  });