$(window).load(function() {
 getCountsForConstituency();
});


function getCountsForConstituency()
{
	var tehsilId=0;
	$("#reportLevelheading").html("");

	var level =  $("#reportLevel").val();
	var publicationDateId = 22;
	var typeName = '';

	type = 'constituency';
	id = 232;

	var jsObj=
		{
			tehsilId:tehsilId,
			type:type,	
			id:id,
			publicationDateId:publicationDateId,
			constituencyId:232,
			task:"getCountForLevel"
		}
		$.ajax({
		  type:'GET',
		  url: 'getCountForLevelAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(results){
			var str="";	

			if(results !=null && results.length>0){
				
				if(results[0].totalmandals == null)
					results[0].totalmandals = 0;
				if(results[0].noOfLocalBodies == null)
				results[0].noOfLocalBodies = 0;
				if(results[0].totalPanchayats == null)
					results[0].totalPanchayats = 0;
				if(results[0].totalBooths == null)
					results[0].totalBooths = 0;

				if(results[0].totalmandals !=null && results[0].totalmandals!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Mandals</h4>';
							str +='<h3>'+results[0].totalmandals+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				
				if(results[0].noOfLocalBodies !=null && results[0].noOfLocalBodies!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Muncipalities</h4>';
							str +='<h3>'+results[0].noOfLocalBodies+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				
				
				if(results[0].totalPanchayats !=null && results[0].totalPanchayats!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Panchayats</h4>';
							str +='<h3>'+results[0].totalPanchayats+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				if(results[0].totalNoOfWards !=null && results[0].totalNoOfWards!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Wards</h4>';
							str +='<h3>'+results[0].totalNoOfWards+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				if(results[0].totalBooths !=null && results[0].totalBooths!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Booths</h4>';
							str +='<h3>'+results[0].totalBooths+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				if(results[0].totalNoOfHamlets !=null && results[0].totalNoOfHamlets!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Hamlets</h4>';
							str +='<h3>'+results[0].totalNoOfHamlets+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				
				$("#belowLevelsSizeId").html(str);
				
			}
		});		
		 			
}
