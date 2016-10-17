alert(54)
getStatusWiseIssueTypeCount();
function getStatusWiseIssueTypeCount(){
			
			var jsObj = { 
			              fromDate : 12/07/2016,
			              toDate : 12/07/2016 
						}
			
			$.ajax({
				type : 'GET',
				url : 'getStatusWiseIssueTypeCountAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
				if(result != null && result.length >0)
				//buildStatusWiseIssueTypesCount(result);
			});
			
		}
		 getAllIssueStatusCount();
		function getAllIssueStatusCount(){
			
			var jsObj = { 
			              fromDate : 12/07/2016,
			              toDate : 12/07/2016  
						}
			
			$.ajax({
				type : 'GET',
				url : 'getAllIssueStatusCountAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
			if(result != null && result.length >0)
				buildStatusCount(result);
			});
		}
		function buildStatusCount(result){
		var str='';
		
		str +='h4 class="text-capital panel-title"><b>field monitoring system</b></h4>';
        str +='<div class="row">';
        str +='<div class="col-md-8 col-xs-12 col-sm-10">';
        str +='<ul class="dashedB">';
		for(var i in result){
        str +='<li>open issues<span>1000</span></li>';
        
		}
        str +='</ul>';
        str +='</div>';
        str +='</div>';
		$("#statusCountDivId").html(str);
		
		}