	
	function onLoadCalls(){
		getIssueStatusWiseCounts();
		getIssueTypeWiseCounts();
	}
	
	function getIssueStatusWiseCounts(){
			
			var jsObj = { 
			              fromDate : "12/07/2016",
			              toDate : "12/07/2016"  
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
			
			var jsObj = { 
			              fromDate : "12/07/2016",
			              toDate : "12/07/2016" 
						}
			$.ajax({
				type : 'GET',
				url : 'getIssueTypeWiseCountsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
				if(result != null && result.length >0){getIssueTypeWiseCounts
					
				}
			});
			
		}
		
		
		
		