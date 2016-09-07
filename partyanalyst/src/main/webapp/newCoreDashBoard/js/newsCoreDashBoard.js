	function getNewsBasicCounts(){
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate='08-01-2016',endDate='08-31-2016';
		var state = globalState;
		var url = window.location.href;
		var wurl = url.substr(0,(url.indexOf(".com")+4));
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			if(result != null && result.length > 0){
				$("#tdpMainTotal").html(result[0].positiveCountMain+result[0].negativCountMain);
				$("#tdpMainPositive").html(result[0].positiveCountMain);
				$("#tdpMainNegative").html(result[0].negativCountMain);
				if((result[0].positiveCountMain+result[0].negativCountMain) > 0){
					$("#tdpMainPositivePercent").html(((result[0].positiveCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2));
					$("#tdpMainNegativePercent").html(((result[0].negativCountMain*100)/(result[0].positiveCountMain+result[0].negativCountMain)).toFixed(2));
				}
				
				$("#tdpDistTotal").html(result[0].positiveCountDist+result[0].negativCountDist);				
				$("#tdpDistPositive").html(result[0].positiveCountDist);
				$("#tdpDistNegative").html(result[0].negativCountDist);
				if((result[0].positiveCountDist+result[0].negativCountDist) > 0){
					$("#tdpDistPositivePercent").html(((result[0].positiveCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2));
					$("#tdpDistNegativePercent").html(((result[0].negativCountDist*100)/(result[0].positiveCountDist+result[0].negativCountDist)).toFixed(2));
				}
				
				var oppTotal=(result[1].positiveCountMain+result[1].negativCountMain)+(result[2].positiveCountMain+result[2].negativCountMain)+(result[3].positiveCountMain+result[3].negativCountMain);
				$("#oppMainTotal").html(oppTotal);
				$("#oppPositiveTotal").html(result[1].positiveCountMain+result[2].positiveCountMain+result[3].positiveCountMain);
				$("#oppNegativeTotal").html(result[1].negativCountMain+result[2].negativCountMain+result[3].negativCountMain);
				if(oppTotal > 0){
					$("#oppPositiveTotalPercent").html((((result[1].positiveCountMain+result[2].positiveCountMain+result[3].positiveCountMain)/oppTotal)*100).toFixed(2));
					$("#oppNegativeTotalPercent").html((((result[1].negativCountMain+result[2].negativCountMain+result[3].negativCountMain)/oppTotal)*100).toFixed(2));
				}
				
				var ysrcMainTotal = result[1].positiveCountMain+result[1].negativCountMain;
				$("#ysrcMainTotal").html(ysrcMainTotal);
				$("#ysrcMainPositive").html(result[1].positiveCountMain);
				$("#ysrcMainNegative").html(result[1].negativCountMain);
				
				if(ysrcMainTotal > 0){
					$("#ysrcMainPositivePercent").html(((result[1].positiveCountMain*100)/ysrcMainTotal).toFixed(2));
					$("#ysrcMainNegativePercent").html(((result[1].negativCountMain*100)/ysrcMainTotal).toFixed(2));
					
				}
				
				var incMainTotal = result[2].positiveCountMain+result[2].negativCountMain;
				$("#incMainTotal").html(incMainTotal);
				$("#incMainPositive").html(result[2].positiveCountMain);
				$("#incMainNegative").html(result[2].negativCountMain);
				if(incMainTotal > 0){
					$("#incMainPositivePercent").html(((result[2].positiveCountMain*100)/incMainTotal).toFixed(2));
					$("#incMainNegativePercent").html(((result[2].negativCountMain*100)/incMainTotal).toFixed(2));
				}
				 var bjpMainTotal = result[3].positiveCountMain+result[3].negativCountMain;
				 $("#bjpMainTotal").html(bjpMainTotal);
				 $("#bjpMainPositive").html(result[3].positiveCountMain);
				 $("#bjpMainNegative").html(result[3].negativCountMain);
				 
				 if(bjpMainTotal>0){
					 $("#bjpMainPositivePercent").html(((result[3].positiveCountMain*100)/bjpMainTotal).toFixed(2));
					 $("#bjpMainNegativePercent").html(((result[3].negativCountMain*100)/bjpMainTotal).toFixed(2));
				 }
				 
				 var oppDistTotal=(result[1].positiveCountDist+result[1].negativCountDist)+(result[2].positiveCountDist+result[2].negativCountDist)+(result[3].positiveCountDist+result[3].negativCountDist);
				 $("#oppDistTotal").html(oppDistTotal);
				 
				 $("#oppDistPositive").html(result[1].positiveCountDist+result[2].positiveCountDist+result[3].positiveCountDist);
				 $("#oppDistNegative").html(result[1].negativCountDist+result[2].negativCountDist+result[3].negativCountDist);
				 
				 if(oppDistTotal > 0){
					 $("#oppDistPositivePercent").html((((result[1].positiveCountDist+result[2].positiveCountDist+result[3].positiveCountDist)*100)/oppDistTotal).toFixed(2));
					 $("#oppDistNegativePercent").html((((result[1].negativCountDist+result[2].negativCountDist+result[3].negativCountDist)*100)/oppDistTotal).toFixed(2));
				 }
				 
				 var ysrcDistTotal = result[1].positiveCountDist+result[1].negativCountDist;
				$("#ysrcDistTotal").html(ysrcDistTotal);
				$("#ysrcDistPositive").html(result[1].positiveCountDist);
				$("#ysrcDistNegative").html(result[1].negativCountDist);
				
				if(ysrcDistTotal > 0){
					$("#ysrcDistPositivePercent").html(((result[1].positiveCountDist*100)/ysrcDistTotal).toFixed(2));
					$("#ysrcDistNegativePercent").html(((result[1].negativCountDist*100)/ysrcDistTotal).toFixed(2));
					
				}
				
				var incDistTotal = result[2].positiveCountDist+result[2].negativCountDist;
				$("#incDistTotal").html(incDistTotal);
				$("#incDistPositive").html(result[2].positiveCountDist);
				$("#incDistNegative").html(result[2].negativCountDist);
				if(incDistTotal > 0){
					$("#incDistPositivePercent").html(((result[2].positiveCountDist*100)/incDistTotal).toFixed(2));
					$("#incDistNegativePercent").html(((result[2].negativCountDist*100)/incDistTotal).toFixed(2));
				}
				
				var bjpDistTotal = result[3].positiveCountDist+result[3].negativCountDist;
				 $("#bjpDistTotal").html(bjpDistTotal);
				 $("#bjpDistPositive").html(result[3].positiveCountDist);
				 $("#bjpDistNegative").html(result[3].negativCountDist);
				 
				 if(bjpDistTotal>0){
					 $("#bjpDistPositivePercent").html(((result[3].positiveCountDist*100)/bjpDistTotal).toFixed(2));
					 $("#bjpDistNegativePercent").html(((result[3].negativCountDist*100)/bjpDistTotal).toFixed(2));
				 }
				 
				 var govtMainTotal = result[4].positiveCountMain+result[4].negativCountMain;
				 $("#govtMainTotal").html(govtMainTotal);
				 $("#govtMainPositive").html(result[4].positiveCountMain);
				 $("#govtMainNegative").html(result[4].negativCountMain);
				 if(govtMainTotal > 0){
					 $("#govtMainPositivePercent").html(((result[4].positiveCountMain*100)/govtMainTotal).toFixed(2));
					 $("#govtMainNegativePercent").html(((result[4].negativCountMain*100)/govtMainTotal).toFixed(2));
				 }
				 
				var govtDistTotal = result[4].positiveCountDist+result[4].negativCountDist;
				 $("#govtDistTotal").html(govtDistTotal);
				 $("#govtDistPositive").html(result[4].positiveCountDist);
				 $("#govtDistNegative").html(result[4].negativCountDist);
				 if(govtDistTotal > 0){
					 $("#govtDistPositivePercent").html(((result[4].positiveCountDist*100)/govtDistTotal).toFixed(2));
					 $("#govtDistNegativePercent").html(((result[4].negativCountDist*100)/govtDistTotal).toFixed(2));
				 } 
			}
		});
	}
	
	function getUserTypeWiseNewsCounts(){
		var startDate,endDate;
		var jsObj={
				activityMemberId : globalActivityMemberId ,
				userTypeId : globalUserTypeId,
				state:globalState,
				fromDate:'08-01-2016',
				toDate:'08-31-2016',
				benefitId:1
			}
		
			$.ajax({
				type : 'POST',
				url : 'getUserTypeWiseNewsCountsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result != null && result.length > 0){
					
				}
			});
	}
	
	$(document).on("click","#detailedPartyId",function(){
		getDetailedPartyMainEditionsOverview();
		getDetailedPartyDistrictEditionsOverview();
		getDetailedPartyNewsTypeAnalysis();
		getDetailedPartyPartyVsPublications();
	});
	
	function getDetailedPartyMainEditionsOverview(){
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate="08-01-2016",endDate="08-31-2016";
		
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+globalUserAccessLevelId+"/"+temp+"/"+globalState+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			if(result != null && result.length > 0){
				
			}
		});
		
	}
	
	function getDetailedPartyDistrictEditionsOverview(){
		var arr = globalUserAccessLevelValues;
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate,endDate;
		var state = globalState;
		var jsObj ={
				userAccessLevelId : globalUserAccessLevelId,
				userAccessLevelValuesArray : temp,
				state : state,
				startDate : startDate,
				endDate : endDate
		}
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyDistrictEditionsOverview/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			if(result != null && result.length > 0){
				
			}
		});
	}
	
	function getDetailedPartyNewsTypeAnalysis(){
		
	}
	
	function getDetailedPartyPartyVsPublications(){
		
	}
