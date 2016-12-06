	$(document).on("click",".alertsIconExpand",function(){
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".alertsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			//getUserTypeWiseNewsCounts(1);
			console.log("opening")
		}else{
			//$(".newsHiddenMoreBlock,.moreAttBlocks").hide();
			console.log("closing")		
		}
		if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".dateRangePickerClsForTraining").addClass("hide");
			$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
			$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
			$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
			$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
			$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
			$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerCls").toggleClass("hide");
			$(".moreBlocksIcon").removeClass("unExpandBlock");
		}else if( $(".meetingsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".meetingsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").hide();
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForMeetings").toggleClass("hide");
			$(".moreMeetingsBlocks1").hide();
			$(".moreMeetingsBlocksDetailed").hide();
			$(".moreMeetingsBlocksComparision").hide();
		}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents").hide();
			$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForEvents").toggleClass("hide");
		}else if( $(".newsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".newsHiddenBlock,.morenewsBlocksIcon,.newsHiddenMoreBlock").hide();
			$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForNews").toggleClass("hide");
		}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
			$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}
		});
    getAlertOverviewDetails();
	function getAlertOverviewDetails(){
		$("#alertOverview").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={  
			activityMemberId : 44,      
			stateId : globalStateId,           
			fromDate :"",        
			toDate :""    
		};
		$.ajax({
			type : 'GET',
			url : 'getAlertOverviewDetailsAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
           console.log(result);
		   buildAlertOverviewDetails(result);
		});	
	}
	
	function buildAlertOverviewDetails(result)
	{
		var str='';
		str+='<div class="pad_15 bg_ED">';
			str+='<table class="table table-bordered alertOverviewTable bg_ED">';
				str+='<tr>';
					str+='<td>';
						str+='<h3>'+result.overAllVO.totalAlertCnt+'</h3>';
						str+='<p class="text-capital">TOTAL ALERTS</p>';
					str+='</td>';
					str+='<td>';
						str+='<h3>'+result.overAllVO.partyAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.partyAlertCntPer+'</small> </h3>';
						str+='<p class="text-capital">party</p>';
					str+='</td>';
					str+='<td>';
						str+='<h3>'+result.overAllVO.otherAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.otherAlertCntPer+'</small></h3>';
						str+='<p class="text-capital">others</p>';
					str+='</td>';
				str+='</tr>';
			str+='</table>';
		str+='</div>';
		str+='<div class="row">';
			for(var i in result.statusList)
			{
				str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top10">';
					str+='<div class="bg_ED pad_5">';
						str+='<h3>'+result.statusList[i].statusCnt+'&nbsp;&nbsp;<small class="text-success" style="font-size:13px">'+result.statusList[i].statusCntPer+'</small></h3>';
						str+='<p class="text-capital text-muted">'+result.statusList[i].statusType+'</p>';
					str+='</div>';
				str+='</div>';
			}
		str+='</div>';
		
		for(var i in result.categoryList)
		{
			str+='<h4 class="panel-title m_top10">'+result.categoryList[i].statusType+' - '+result.categoryList[i].statusCnt+'</h4>';
			str+='<table class="table table-condensed bg_ED">';
				str+='<tbody>';
					str+='<tr>';
					for(var j in result.categoryList[i].statusList)
					{
						str+='<td><p class="text-muted text-capitalize responsiveFont">'+result.categoryList[i].statusList[j].statusType+'</p><p class="responsiveFont">'+result.categoryList[i].statusList[j].statusCnt+'&nbsp;&nbsp;<small class="text-success">'+result.categoryList[i].statusList[j].statusCntPer+'%</small></p></td>';
					}
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		}
		$("#alertOverview").html(str)
			
	}