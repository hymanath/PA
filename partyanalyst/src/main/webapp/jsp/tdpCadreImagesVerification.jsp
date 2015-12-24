<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Image Verification</title>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/dailyVerificationReports.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
</head>
<body>
<div class="container" style="border: 2px solid rgb(204, 204, 204); padding: 20px; min-height:380px;">
	<h4 class="text-center">CADRE IMAGES FOR VERIFICATION </h4>
	<div class="row-fluid offset" >
		<div class="span4 offset2">
			<span> Select District : </span>
			<select id="districtSelect"  onChange="getAllConstituencyes(this.value)" class="input-block-level">
				<option value=0>Select District</option>
			</select>
		</div>

		<div class="span4">									
			<span> Select Constituency : </span>
			<select id="constituencySelect" class="input-block-level">
				<option value=0>Select Constituency</option>
			</select>
		</div>
		
		<div class="span2">
			<a class="btn btn-success" onClick="getDetailsForSelected();" style="margin-top: 20px;">SUBMIT</a>
		</div>	
	</div>
<div class="row" id="checkForButtons" style="display:none">
	<div class="span pull-right">
	<a class="btn btn-success" onClick="getValidOrInValidImages('VALID');" style="margin-top: 20px;">VALID IMAGES</a>
	<a class="btn btn-danger" onClick="getValidOrInValidImages('INVALID');" style="margin-top: 20px;">INVALID IMAGES</a>
	</div>
</div>
<div class="row">
	<div class="row-fluid" >
		<div id="resultDiv" class="span8 offset2"></div>	
	</div>
</div>
</div>	
	<script>
		getAllDistricts();
		function getAllDistricts()
		{
			var jobj =
			{
			     address:"cadreLevel",
				 areaType:"null",
				 id:1,
				 isParliament:"null",
				 selectElementId : "districtField_s",
				 taskType : "cadreReg",
				 task : "districtsInState"
	  	    }
			$.ajax({
	          type:'GET',
	          url: 'locationsHierarchiesAjaxAction.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jobj)}
			}).done(function(result){
				$("#districtSelect option").remove();
				//$("#districtSelect").append("<option value='0'>Select District</option>");
				for(var i in result)
				{
					$("#districtSelect").append("<option value="+result[i].id+">"+result[i].name+"</option>");
				}
			});
		}
		
		function getAllConstituencyes(id)
		{
			var jobj =
			{
			     address:"cadreLevel",
				 areaType:"null",
				 id:id,
				 isParliament:"null",
				 selectElementId : "constituencyField_s",
				 taskType : "cadreReg",
				 task : "constituenciesInDistrict"
	  	    }
			$.ajax({
	          type:'GET',
	          url: 'locationsHierarchiesAjaxAction.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jobj)}
			}).done(function(result){
				$("#constituencySelect option").remove();
				for(var i in result)
				{
					$("#constituencySelect").append("<option value="+result[i].id+">"+result[i].name+"</option>");
				}
			});
		}
		
		function getDetailsForSelected()
		{
			$('#resultDiv').html('');
			$('#checkForButtons').show();
			var jobj =
			{
			     districtId:$('#districtSelect').val(),
				 constituencyId:$('#constituencySelect').val()
	  	    }
			$.ajax({
	          type:'GET',
	          url: 'getImageDetailsForCheckingAction.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jobj)}
			}).done(function(result){
				if(result != null && result.length > 0)
				{
					var str = '';
					str += '<h4 class="text-center">ALL IMAGES FOR VERIFICATION</h4>';
					str += '<table class="table table-bordered m_top20 table-hover table-striped" id="dayWiseReportTable">';
					str+='<thead class="alert alert-success">';
					str+= '<tr>';
					str+= '<th>NAME</th>';
					str+= '<th>IMAGE</th>';
					str+= '<th>STATUS</th>';
					str+= '</tr>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result)
					{
						str+= '<tr id="image'+result[i].tdpCadreId+'">';
						str+= '<td>'+result[i].name+'</td>';
						var img = 'images/cadre_images/'+result[i].image;
						str+= '<td><img src='+img+' style="width:70px"></img></td>';
						str+= '<td><a class="btn btn-mini btn-success"  style="margin-top: 15px;" onClick="getValidDetails('+result[i].tdpCadreId+',\'VALID\');"><i class="icon-ok icon-white"></i> VALID</a>&nbsp;&nbsp;<a class="btn btn-mini btn-danger" style="margin-top: 15px;" onClick="getValidDetails('+result[i].tdpCadreId+',\'INVALID\');"><i class="icon-remove icon-white"></i> INVALID</a></td>';
						str+= '</tr>';
						
					}
					str+='</tbody>';
					str+= '</table>';
					$('#resultDiv').html(str);
					
					/*$('#dayWiseReportTable').dataTable({
						"iDisplayLength": 100,
						"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
						});*/
				}
			});
		}
		
		function getValidDetails(tdpCadreId,status)
		{
			var jobj =
			{
			     tdpCadreId:tdpCadreId,
				 status:status
	  	    }
			$.ajax({
	          type:'GET',
	          url: 'saveCheckedImagesAction.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jobj)}
			}).done(function(result){
				if(result== 'SUCCESS')
				$('#image'+tdpCadreId+'').hide();
			});
		}
		
		//getValidOrInValidImages("INVALID");
		function getValidOrInValidImages(type)
		{
			$('#resultDiv').html('');
			var jobj =
			{
			     districtId:$('#districtSelect').val(),
				 constituencyId:$('#constituencySelect').val(),
				 type : type
	  	    }
			$.ajax({
	          type:'GET',
	          url: 'getValidOrInValidImagesAction.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jobj)}
			}).done(function(result){
				if(result != null && result.length > 0)
				{
					var str = '';
					str += '<h4 class="text-center">ALL '+type+' IMAGES</h4>';
					str += '<table class="table table-bordered m_top20 table-hover table-striped"  id="dayWiseReportTable">';
					str+='<thead class="alert alert-success">';
					str+= '<tr>';
					str+= '<th>NAME</th>';
					str+= '<th>IMAGE</th>';
					str+= '<th>STATUS</th>';
					str+= '</tr>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result)
					{
						str+= '<tr id="image'+result[i].tdpCadreId+'">';
						str+= '<td>'+result[i].name+'</td>';
						var img = 'images/cadre_images/'+result[i].image;
						str+= '<td><img src='+img+' style="width:70px"></img></td>';
						str+= '<td><a class="btn btn-mini btn-success"  style="margin-top: 15px;" onClick="getValidDetails('+result[i].tdpCadreId+',\'VALID\');"><i class="icon-ok icon-white"></i> VALID</a>&nbsp;&nbsp;<a class="btn btn-mini btn-danger" style="margin-top: 15px;" onClick="getValidDetails('+result[i].tdpCadreId+',\'INVALID\');"><i class="icon-remove icon-white"></i> INVALID</a></td>';
						
					}
					str+='</tbody>';
					str+= '</table>';
					$('#resultDiv').html(str);
					
					/*$('#dayWiseReportTable').dataTable({
						"iDisplayLength": 100,
						"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
						});*/
				}
			});
		}
		
	</script>
</body>
</html>