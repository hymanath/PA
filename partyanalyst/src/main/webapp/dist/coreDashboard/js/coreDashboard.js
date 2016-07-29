	function getUserAccessLevelAndValues(){
	   var jsObj ={userId:globalUserId}
	   $.ajax({
			type : 'GET',
			url : 'getUserAccessLevelAndValuesAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
   }
    function getUserTypeByUserId(){
	   var jsObj ={userId:globalUserId}
	   $.ajax({
			type : 'GET',
			url : 'getUserTypeByUserIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
	}
	function getMainCommitteeCountDetails(){
		
		var committeeId = 1;
		var state ="AP"
	   var jsObj ={committeeId:committeeId,state:state}
	   $.ajax({
			type : 'GET',
			url : 'getCompleteAndStartedAndNotYetStartedCommittesCountAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(results){
			buildMainCommitteChart(results);
		});
	}
	function buildMainCommitteChart(results){
		
		var CommCompletedArray ="";
		var CommStartedArray ="";
		var CommYetToStarted ="";
		if(results !=null ){
			for(var i in results){
				CommCompletedArray=results[i].mainCommCompletedCount;
				CommStartedArray=results[i].mainCommStartedCount;
				CommYetToStarted=results[i].mainCommNotYetStarted;
				
			}
			
		}
		
		$('#committees').highcharts({
			colors:['#CBE7B9','#00C7F7','#D6EB59'],
			chart: {
			  plotBackgroundColor: null,
			  plotBorderWidth: null,
			  plotShadow: false,
			  type: 'pie',
			 
			},
			title: {
						text: null
					},
			tooltip: {
			  pointFormat: ' <b>{point.percentage:.1f}%</b>'
			},
			legend: {
				enabled: true,
				floating: true,
				verticalAlign: 'bottom',
				align:'left',
				y:8,
				x:-0.5,
				//width:100,
				useHtml: ' {point.x}'
			},
			plotOptions: {
			  series: {
				allowPointSelect: false,
				cursor: 'pointer',
				 dataLabels: {
				
					distance: -43,
					formatter: function () {
						if(this.y!=0)  return Math.round(this.y);

					}
				},
				showInLegend: true
			  }
			},
			series: [{
			  //name: '',
			  colorByPoint: true,
			  data: [{
				name: 'Completed',
				y: CommCompletedArray
			  }, {
				name: 'In Progress',
				y: CommStartedArray
			  }, {
				name: 'Yet To Start',
				y: CommYetToStarted
			  }]
			}]

		  });
	
	}