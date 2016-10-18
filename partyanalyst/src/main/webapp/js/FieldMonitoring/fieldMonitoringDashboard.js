	
	function onLoadCalls(){
		getOverAllDataCollectorsCounts();
		getIssueStatusWiseCounts();
		getIssueTypeWiseCounts();
	}
	
	function getOverAllDataCollectorsCounts(){
		var jsObj = { 
		  fromDate : "10/01/2016",
		  toDate : "10/18/2016"  
		}
		$.ajax({
			type : 'GET',
			url : 'getOverAllDataCollectorsDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result != null){
				$("#totalDataCollectorsId").html(result.totalDataCollectors);
				$("#activeDataCollectorsId").html(result.activeUsers);
				$("#passiveDataCollectorsId").html(result.passiveUsers);
			}	
		});
	}
	function getIssueStatusWiseCounts(){
			$("#statusCountDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
			var dates = $(".singleDate").val();
	        var dateArr = dates.split("-");
	        var fromDate;
	        var toDate;
	      if(dateArr != null){
		    fromDate = dateArr[0];
		    toDate = dateArr[1];
	       }

			var jsObj = { 
			              fromDate : fromDate,    //"2016-10-01",
		                  toDate : toDate			//"2016-10-18" 
						}
			$.ajax({
				type : 'GET',
				url : 'getIssueStatusWiseCountsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
			if(result != null && result.length >0){
				buildIssueStatusWiseCounts(result);
			}	
			});
	}
	function buildIssueStatusWiseCounts(result){
			var str='';
			
			str +='<h4 class="text-capital panel-title"><b>field monitoring system</b></h4>';
			str +='<div class="row">';
			str +='<div class="col-md-8 col-xs-12 col-sm-10">';
			str +='<ul class="dashedB">';
			for(var i in result){
			   str +='<li>'+result[i].name+' issues<span>'+result[i].inviteeCount+'</span></li>';
			}
			str +='</ul>';
			str +='</div>';
			str +='</div>';
			$("#statusCountDivId").html(str);
		
		}	
	function getIssueTypeWiseCounts(){
			var dates = $(".singleDate").val();
	        var dateArr = dates.split("-");
	        var fromDate;
	        var toDate;
	   if(dateArr != null){
		     fromDate = dateArr[0];
		     toDate = dateArr[1];
	       }
			var jsObj = { 
			              fromDate : fromDate,    //"2016-10-01",
		                  toDate : toDate			//"2016-10-18" 
						}
			$.ajax({
				type : 'GET',
				url : 'getIssueTypeWiseCountsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
				if(result != null && result.length >0){
					
				}
			});
			
		}
