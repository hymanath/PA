<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> Constituency Committee Summary </title>
	
    <!-- Bootstrap -->
    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
	<link rel="stylesheet" type="text/css" href="css/cadreCommitee/slick/slick.css"/>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->

	<!----slick Js----->
	<script type="text/javascript" src="js/cadreCommittee/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/cadreCommitteeRequest/cadreCommitteeRequest.js"></script>
	
</head>
<body>
<style>
	.locationName{text-transform: uppercase;}
</style>
<div class="container">
    <div class="row m_top20">
  	   <div class="col-md-4 col-md-offset-2 col-sm-6 col-xs-6">Select District:<select id="districtsId" class="form-control" onChange="getAllConstituencysForADistrict()"><option value="0">Select District</option></select> </div>
       <div class="col-md-4  col-sm-6 col-xs-6">Select Constituency:<select id="constituencysId" class="form-control"><option value="0">Select Constituency</option></select> </div>
    </div>
	<div class="row m_top20">
		<div class="col-md-4 col-md-offset-3"><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=1 name="reportType" checked="true"> VILLAGE / WARD</label></div>
		<div class="col-md-4 "><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=2 name="reportType">MANDAL / MUNCIPALITY / DIVISON</label></div>
	</div>
						
	<div id="constSummary" style="display:none;">
		<img id="summaryAjax" src="./images/Loading-data.gif" alt="Processing Image"/>
	</div>
</div>
<script type="text/javascript">
getAllDistricts();
function getAllDistricts()
{

 $.ajax({
			type : "GET",
			url : "getAllDistrictsAction.action",
			data : {} ,
		}).done(function(result){
			$("#districtsId  option").remove();
			$("#districtsId").append('<option value="0">Select District</option>');
			if(result!=null){
			for(var i in result){
				   $("#districtsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				 }
			}	 
		});
}

$("#constituencysId").change(function(){
	$("#constSummary").show();
	getConstituencySummary();
});
$(".reportTypeCls").click(function(){
	getConstituencySummary();
	
});
	
function getAllConstituencysForADistrict()
{
     var districtId = $("#districtsId").val();
	 $("#constituencysId  option").remove();
	 $("#constituencysId").append('<option value="0">Select Constituency</option>');
	if(districtId==0){
	  return;	 
	}
 
    $.ajax({
			type : "GET",
			url : "getAllConstituencysForADistrictAction.action",
			data : {districtId:districtId} ,
		}).done(function(result){
			if(result!=null){
			   for(var i in result){
				   $("#constituencysId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			    }
			}	 
		});
}

function getConstituencySummary(){
	var constiId = $("#constituencysId").val();
	
	if(constiId==0){
	  return;	 
	}
	var reportType = $("input[type='radio'][name='reportType']:checked").val();
	
	var jsObj ={
		 constituencyId:constiId,reportType :reportType            
	}	
	$.ajax({
		type : "POST",
		url : "getConstituencyCommitteSummary.action",
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		buildConstituencySummary(result,jsObj);
	});
	
}

function buildConstituencySummary(results,jsObj){
	var repType = jsObj.reportType;
	
	$("#constSummary").html("");
		var str = '';
		
		if(repType==1){
			for(var i in results.mandalsList){
				var rest = results.mandalsList[i]
				str += '<h4 class="locationName">'+rest.locationName+'</h4>'
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; background-color:rgba(0,0,0,0.1);">';
				str+='<thead>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th> Location </th>';
						for(var k in results.resultList){
							str+='<th>'+results.resultList[k].basicCommitteeName+'</th>';
						}
						
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
						for(var j in rest.locationsList){
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
								str+='<td>'+rest.locationsList[j].locationName+'</td>';
									for(var k in rest.locationsList[j].resultList){
										if(rest.locationsList[j].resultList[k].membersCount!=null){
											str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="6">'+rest.locationsList[j].resultList[k].membersCount+'</td>';
										}else{
											str+='<td> </td>';
										}
										
									}
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table> ';
			}
			for(var i in results.localBodiesList){
				var rest = results.localBodiesList[i]
				str += '<h4 class="locationName">'+rest.locationName+'</h4>'
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; background-color:rgba(0,0,0,0.1);">';
				str+='<thead>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th> Location </th>';
						for(var k in results.resultList){
							str+='<th>'+results.resultList[k].basicCommitteeName+'</th>';
						}
						
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
						for(var j in rest.locationsList){
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
								str+='<td>'+rest.locationsList[j].locationName+'</td>';
									for(var k in rest.locationsList[j].resultList){
										if(rest.locationsList[j].resultList[k].membersCount!=null){
											str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8">'+rest.locationsList[j].resultList[k].membersCount+'</td>';
										}else{
											str+='<td> </td>';
										}
										
									}
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table> ';
			}
		}else{
				str += '<h4 class="locationName"> Mandal/Munciapality/Division Wise Committees Summary</h4>'
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; background-color:rgba(0,0,0,0.1);">';
				str+='<thead>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th> Location </th>';
						for(var k in results.resultList){
							str+='<th>'+results.resultList[k].basicCommitteeName+'</th>';
						}
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
						for(var i in results.mandalsList){
							var rest = results.mandalsList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="6">'+rest.resultList[j].membersCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
							}
							str+='</tr>';
						}
						
						for(var i in results.localBodiesList){
							var rest = results.localBodiesList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="7">'+rest.resultList[j].membersCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
							}
							str+='</tr>';
						}
						
						for(var i in results.divisionList){
							var rest = results.divisionList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="7">'+rest.resultList[j].membersCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
							}
							str+='</tr>';
						}
						
					str+='</tbody>';
				str+='</table> ';
			
		}
		
		
					
	$("#constSummary").html(str);
}

	$(".lctnCmmty").click(function(){
		var locationType = $(this).attr("attr_locationType");
		var location = $(this).attr("attr_locationId");
		var basicCmmtyId = $(this).attr("attr_cmmtyType");
	});
	
	
</script>

</body>
</html>