var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
setTimeout(function(){ 
	onLoadCalls();
},1500)

function onLoadCalls(){
	getGovtSchemeWiseBenefitMembersCount();
}
function getGovtSchemeWiseBenefitMembersCount(){
	$("#benefitsOverViewBlockId").html(spinner);
	
	
	jsObj={
		locationScopeId	:locationLevelId,
		locationValue	:userAccessLevelValue
	}
	 $.ajax({
      type : "POST",
      url : "getLocationwiseSchemesOverviewAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
		if(result!=null){
			return buildTabs(result);
		}else{
			$("#benefitsOverViewBlockId").html(noData);
		}
	});	
	function buildTabs(result)
	{
		var str='';
		var totalBenefitsMemberesCount=0;
		var totalBenefitsAmountCount=0;
		var mainArr=[];
		var percentage=0;
		var totalSchemesCount=0;
		var totalBenefitsCount=0;
		if(result !=null && result.list !=null){
				totalBenefitsMemberesCount =result.list[0].earnedVote;
				totalBenefitsAmountCount =result.list[0].range;
		}
		if(result !=null && result.subList1 !=null && result.subList1.length>0){
			totalSchemesCount =totalSchemesCount+result.subList1.length;
			 for(var i in result.subList1){
				 if(result.subList1[i].totalSeatsCount !=null && result.subList1[i].totalSeatsCount != 0){
					totalBenefitsCount = totalBenefitsCount+1;
				} 
			}
		}
		mainArr.push(totalSchemesCount)
		mainArr.push(totalBenefitsCount)
		percentage = parseFloat((totalBenefitsCount*100)/totalSchemesCount);
		
		str+='<div class="block boderBlock">';
		str+='<h4 class="theme-title-color">Overall Overview</h4>';	
		str+='<div class="row">';
		str+='<div class="col-sm-5">';
			str+='<div class="benefit_block">';
				str+='<div id="benefitMainGraphDivId" style="height:180px;"></div>';
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-6">';
						str+='<div class="media media_padding">';
							str+='<div class="media-left">';
								str+='<img src="coreApi/img/group.png" alt="Group"/></img>';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h6>Benefited Members</h6>';
								/* if(totalBenefitsMemberesCount !=null && totalBenefitsMemberesCount>0){
									str+='<h5>'+totalBenefitsMemberesCount+'</h5>';
								}else{
									str+='<h5> - </h5>';
								} */
								str+='<h5 class="m_top10">'+totalBenefitsMemberesCount+'</h5>';
								
							str+=' </div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-6">';
						str+='<div class="media media_padding">';
							str+='<div class="media-left">';
								str+='<i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:28px"></i>';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h6 class="m_top5">Benefited Amount</h6>';
								/* if(totalBenefitsAmountCount !=null && totalBenefitsAmountCount>0){
									str+='<h5>'+totalBenefitsAmountCount+'</h5>';
								}else{
									str+='<h5> - </h5>';
								} */
								str+='<h5 class="m_top10">'+totalBenefitsAmountCount+'</h5>';
							str+=' </div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		
		str+='</div>';
		str+='<div class="col-sm-7">';
				str+='<div class="posts-block">';
					str+='<h4 class="panel-title">Top 5 - People Benifited Schemes</h4>';
					str+='<div class="pad_10">';
						str+='<ul class="list-border list-border-responsive">';
						var countVar =0;
						if(result !=null && result.subList1 !=null && result.subList1.length>0){
							for(var i in result.subList1){
								str+='<li>';
									if(result.subList1[i].partyName !=null && result.subList1[i].partyName.length>10){
										str+='<p class="text-muted"><span class="tooltipCls" data-toggle="tooltip" style="cursor:pointer" title="'+result.subList1[i].partyName+'" >'+result.subList1[i].partyName.substring(0,10)+'...</span></p>';
									}else{
										str+='<p class="text-muted">'+result.subList1[i].partyName+'</p>';
									}
									if(result.subList1[i].totalSeatsCount !=null && result.subList1[i].totalSeatsCount>0){
										str+='<h4 class="m_top10">'+result.subList1[i].totalSeatsCount+'</h4>';
									}else{
										str+='<h4 class="m_top10"> - </h4>';
									}
									if(result.subList1[i].participatedSeatsCount !=null && result.subList1[i].participatedSeatsCount>0){
										str+='<h4 class="m_top10"><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> '+result.subList1[i].participatedSeatsCount+'</h4>';
									}else{
										str+='<h4 class="m_top10"><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> - </h4>';
									}
									
								str+='</li>';
								
								countVar =countVar+1;
								if (countVar === 5) {
									break;
								}
							}
						}else{
							str+='<li>';
								str+='<p class="text-muted"> - </p>';
								str+='<h4 class="m_top10"> - </h4>';
								str+='<h4 class="m_top10"> - </h4>';
							str+='</li>';
							
						}
						str+='</ul>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="posts-block">';
					str+='<h4 class="panel-title">Low 5 - People Benifited Schemes</h4>';
					str+='<div class="pad_10">';
						str+='<ul class="list-border list-border-responsive">';
							var countVar =0;
								if(result !=null && result.subList1[0].subList2 !=null && result.subList1[0].subList2.length>0){
									for(var i in result.subList1[0].subList2){
										str+='<li>';
											if(result.subList1[0].subList2[i].partyName !=null && result.subList1[0].subList2[i].partyName.length>10){
												str+='<p class="text-muted"><span class="tooltipCls" data-toggle="tooltip" style="cursor:pointer" title="'+result.subList1[0].subList2[i].partyName+'" >'+result.subList1[0].subList2[i].partyName.substring(0,10)+'...</span></p>';
											}else{
												str+='<p class="text-muted">'+result.subList1[0].subList2[i].partyName+'</p>';
											}
											if(result.subList1[0].subList2[i].totalSeatsCount !=null && result.subList1[0].subList2[i].totalSeatsCount>0){
												str+='<h4 class="m_top10">'+result.subList1[0].subList2[i].totalSeatsCount+'</h4>';
											}else{
												str+='<h4 class="m_top10"> - </h4>';
											}
											if(result.subList1[0].subList2[i].participatedSeatsCount !=null && result.subList1[0].subList2[i].participatedSeatsCount>0){
												str+='<h4 class="m_top10"><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> '+result.subList1[0].subList2[i].participatedSeatsCount+'</h4>';
											}else{
												str+='<h4 class="m_top10"><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> - </h4>';
											}
											
										str+='</li>';
										
										/* countVar =countVar+1;
										if (countVar === 5) {
											break;
										} */
									}
								}else{
									str+='<li>';
										str+='<p class="text-muted"> - </p>';
										str+='<h4 class="m_top10"> - </h4>';
										str+='<h4 class="m_top10"> - </h4>';
									str+='</li>';
									
								}
						str+='</ul>';
					str+='</div>';
				str+='</div>';
			
			str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='<div class="block boderBlock m_top20">';
			str+='<div class="row">';
				str+='<div class="col-sm-12">';
					var locationLevelName='';
					if(locationLevelId == '2')
					{
						str+='<h4 class="theme-title-color">District Wise Benifit Overview</h4>';
						locationLevelName = "District";
					}else if(locationLevelId == '3')
					{
						str+='<h4 class="theme-title-color">Constituency Wise Benifit Overview</h4>';
						locationLevelName = "Constituency";
					}else if(locationLevelId == '10')
					{
						str+='<h4 class="theme-title-color">'+locationName+' Wise Benifit Overview</h4>';
					}else if(locationLevelId == '4')
					{
						str+='<h4 class="theme-title-color">'+locationName+' Wise Benifit Overview</h4>';
						locationLevelName = "Constituency";
					}else if(locationLevelId == '5')
					{
						str+='<h4 class="theme-title-color">'+locationName+' Wise Benifit Overview</h4>';
						locationLevelName = "Mandal";
					}else if(locationLevelId == '6')
					{
						str+='<h4 class="theme-title-color">'+locationName+' Wise Benifit Overview</h4>';
					}else
					{
						str+='<h4 class="theme-title-color">Location Wise Benifit Overview</h4>';
					}
					
					str+='<div class="col-sm-12">';
						str+='<div class="table-responsive">';
						str+='<table class="table table-bordered table_benefits1" id="locationWiseBenefitDT">';
							str+='<thead>';
								str+='<tr>';
									str+='<th style="text-transform:capitalize;">'+locationLevelName+'</th>';
									str+='<th>Total Voters</th>';
									str+='<th>Benified</th>';
									str+='<th>Amount</th>';
									if(result !=null && result.list !=null && result.list.length>0){
										for(var i in result.list[0].schemesList){
											if(result.list[0].schemesList[i].partyName !=null && result.list[0].schemesList[i].partyName.length>10){
												str+='<th><span class="tooltipCls" data-toggle="tooltip" style="cursor:pointer" title="'+result.list[0].schemesList[i].partyName+'">'+result.list[0].schemesList[i].partyName.substring(0,10)+'...</span></th>';
											}else{
												str+='<th>'+result.list[0].schemesList[i].partyName+'</th>';
											}
											
										}
									}
								str+='</tr>';
								
							str+='</thead>';
							str+='<tbody>';
								for(var i in result.list){
									str+='<tr>';
									str+='<td>'+result.list[i].name+'</td>';
									str+='<td>'+result.list[i].totalVoters+'</td>';
									if(result.list[i].wonSeatsCount !=null && result.list[i].wonSeatsCount>0){
										str+='<td>'+result.list[i].wonSeatsCount+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result.list[i].participatedSeatsCount !=null && result.list[i].participatedSeatsCount>0){
										str+='<td>'+result.list[i].participatedSeatsCount+'</td>';
									}else{
										str+='<td> - </td>';
									}
									
									for(var j in result.list[i].schemesList){
										if(result.list[i].schemesList[j].totalSeatsCount !=null && result.list[i].schemesList[j].totalSeatsCount>0){
											str+='<td>'+result.list[i].schemesList[j].totalSeatsCount+'</td>';
										}else{
											str+='<td> - </td>';
										}
										
									}
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		$("#benefitsOverViewBlockId").html(str);
		$("#locationWiseBenefitDT").dataTable({
			"iDisplayLength": 10,
			 "aaSorting": [[ 3, "desc" ]], 
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		}); 
		$(".tooltipCls").tooltip();
		
		$("#benefitMainGraphDivId").highcharts({
			colors:["#339900","#3BB878"],
			chart: {
				type: 'column'
			},
			title: {
				text: '',
				style: {
				 color: '#000',
				 font: 'bold 13px "Lato", sans-serif'
			  }
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,	
				type: 'category',
				categories: ['Total Schemes','Benefite Schemes'],
				
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				}
			},
			legend: {
				enabled: false
			},
			tooltip: {
				useHTML:true,	
				formatter: function () {
					if(this.x !="Total Schemes"){
						var pcnt = percentage;
						return '<b>' + this.x + '</b><br/>' +
						this.y+"-"+((Highcharts.numberFormat(pcnt)))+'%';
					}else{
						return '<b>' + this.x + '</b><br/>' +
						this.y+'';
					}
					
				}
			},
			plotOptions: { 
				column: {
					pointWidth: 30,
					gridLineWidth: 25,
					colorByPoint: true
				}
			},
			series: [{
				name: 'Count',
				data: mainArr,
				dataLabels: {
					enabled: true,
					color: '#000',
					align: 'center',
					formatter: function() {
						if(this.x !="Total Schemes"){
							var pcnt = percentage;
							return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
						}else{
							return '<span>'+this.y+'</span>';
						}
						
					}
				}
			}]
		});
	}
}