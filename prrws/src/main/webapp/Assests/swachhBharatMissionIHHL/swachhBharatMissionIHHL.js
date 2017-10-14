
	getSwachhBharatMissionOverviewDtls(); // first block
	getSwachhBharatMissionStatusOverviewDtls();//secondBlock #NOT USED
	getIHHLCategoryWiseAnalysis();//third block #NOT USED
	getIHHLAchivementProgressDtls();//fourth block
	getSwachhBharatMissionLocationWiseDetails();//last 
	function getSwachhBharatMissionOverviewDtls(){
		var json = {
			fromDate:"",
			toDate:"",
			location:"state",
			locationId:"-1",
			subLocation:"state"
		}
		$.ajax({                
			type:'POST',    
			url: 'getSwachhBharatMissionOverviewDtls',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			console.log(result);
		});	
	}
	function getSwachhBharatMissionStatusOverviewDtls(){
		var json = {
			fromDate:"",
			toDate:"",
			year:""
		}
		$.ajax({                
			type:'POST',    
			url: 'getSwachhBharatMissionStatusOverviewDtls',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			console.log(result);
		});	
	}
	function getIHHLCategoryWiseAnalysis(){
			var json = {
				fromDate:"",
				toDate:"",
				year:""
			}
			$.ajax({                
				type:'POST',    
				url: 'getIHHLCategoryWiseAnalysis',
				dataType: 'json',
				data : JSON.stringify(json),
				beforeSend :   function(xhr){
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				}
			}).done(function(result){
				console.log(result);
			});	
		}
		
		function getIHHLAchivementProgressDtls(){
				var json = {
					fromDate:"02-10-2017",
					toDate:"07-10-2017",
					location:"state",
					locationId:"-1",
					subLocation:"state",
					reportType:"daily",//daily
					displayType:"month"//day/week/month
				}
				$.ajax({                
					type:'POST',    
					url: 'getIHHLAchivementProgressDtls',
					dataType: 'json',
					data : JSON.stringify(json),
					beforeSend :   function(xhr){
						xhr.setRequestHeader("Accept", "application/json");
						xhr.setRequestHeader("Content-Type", "application/json");
					}
				}).done(function(result){
					console.log(result);
				});	
			}
			function getSwachhBharatMissionLocationWiseDetails(){
					var json = {
						fromDate:"",
						toDate:"",
						location:"state",
						locationId:"-1",
						subLocation:"district", //state/district/constituency/mandal
						reportType:"status",//daily
						displayType:"day"//day/week/month
					}
					$.ajax({                
						type:'POST',    
						url: 'getSwachhBharatMissionLocationWiseDetails',
						dataType: 'json',
						data : JSON.stringify(json),
						beforeSend :   function(xhr){
							xhr.setRequestHeader("Accept", "application/json");
							xhr.setRequestHeader("Content-Type", "application/json");
						}
					}).done(function(result){
						console.log(result);
					});	
				}
					
					