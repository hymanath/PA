<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Tdp Cadre Reports </title>


    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet"/>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <script src="js/icheck/icheck.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
		<script src="js/icheck/icheck.js"></script>
	
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 

		<script type="text/javascript" src="js/exportexcel.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
	<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.f-16{font-size: 16px;}
	 .bgc{background-color:#3598db}
	 
	.dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}
	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}

		#searchCadreTab  thead th {
			background-color: #dff0d8  !important;
			color : #468847 !important;
			line-height: 15px !important;
		}

	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	 .bgc{background-color:#3598db}
	 
	.dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}

	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}
	.offset1 {
	    margin-left: 70px;
	}
	.span10 {
	    width: 840px;
	}

	#dayWiseUsersDetailsId  thead th{
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 20px !important;
			}
			
			
	</style>
   
   
</head>


<body>
	<div class="container " id="dashboadElmnt">	
	
		<div id="locationWiseCadreInfoDiv">
		    <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-0 " style="padding:0px;">
					<h3 class="offset4 text-uppercase"> Generate Tdp Cadre Reports  </h3>
				</div>
			</div>
			<div class="row-fluid show-grid">
			   <div class="row-fluid offset4">						
					<div class="span8" style="margin-bottom:-20px;">
					<h5 class="text-align1">  State : 
					<select id="statesList" onChange="getdistricts(this.value);getConstituencyValues(this.value);"> 
					<option value="1"> Andhra Pradesh </option>
					<option value="2"> Telangana </option>
					
					</select></h5>
					</div>	
				</div>
				<div class="row-fluid offset4">						
					<div class="span8">
						<h5 class="text-align1" style="margin-left: -22px;">  District : 
					<select id="districtsList"  onChange="getConstituencies(this.value)"> 
						<option value="0"> Select District </option>							
					</select></h5>
					</div>
				</div>
				<div class="row-fluid offset4">						
					<div class="span8">
						<h5 class="text-align1" style="margin-left: -65px;margin-top:-10px">  Constituency : 
					<select id="constiList"> 
						<option value="0"> Select Constituency </option>							
					</select></h5>
					</div>
				</div>
				<a href="javascript:{generateDetailReports();}" class="btn btn-success col-xs-offset-4 border-radius-0 offset5  m_top20"> Generate Excel Reports  <span class="glyphicon glyphicon-chevron-right"></span></a>
				
				<div><img src='images/Loading-data.gif' class="offset5"  id="searchDashboardImg" style="width:70px;height:60px;display:none;"/>
				</div>
				<div id="reportsStatusDiv" style="padding: 10px;"></div>
						
			</div>
		</div>
	</div>
	
<script>

	function getdistricts(id){
	
	$("#districtsList").html("");
	var str ='';
		var jsObj={
			stateid:id
		}
		$.ajax({
			  type:'GET',
			  url: 'getDistrictsByStateWiseAction.action',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#districtsList").html(str);
	   });	
	
  }



 function getConstituencies(districtId){
		var stateId = $("#statesList").val();
		$("#constiList").html("");
		var str ="";
		var jObj ={
			districtId:districtId,				  
			task:"getConstituencyNames"             
		}	
		$.ajax({
			type : "POST",
			url : "sendUpdatesByemailsAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			$('#constiList').find('option').remove();
		   for(var i in result){
				$('#constiList').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			
			 $('#constiList').multiselect('refresh');
			  
		});
	}
	
	function getConstituencyValues(stateId){
	if(stateId == 1)	
		districtId = 11;
	else
		districtId = 1;
		
		var str ="";
		var jObj ={
			districtId:districtId,				  
			task:"getConstituencyNames"             
		}	
		$.ajax({
			type : "POST",
			url : "sendUpdatesByemailsAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
				$('#constiList').find('option').remove();
		   for(var i in result){
		   
				$('#constiList').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}			
			 $('#constiList').multiselect('refresh');
			  
		});
	}
	
	
function generateDetailReports(){		
	var constituencyIds = "";
	var selectedValues = $("#constiList").multiselect("getChecked").map(function(){
        return this.value;
    }).get();
	for(var j in selectedValues)
	{
        constituencyIds = constituencyIds+""+selectedValues[j]+",";
    }
	console.log(constituencyIds);
	
	var jsObj =
	{ 		
		constituencyIds : constituencyIds,	
		task: "constituencyWiseDetails"
	};
	$.ajax({
	type: "POST",
	url: "getLocationWiseDetailsForExcelReportAction.action",
	data: {task : JSON.stringify(jsObj)}
	})
	.done(function( result ) {
		buildCategoeryDetails(result,jsObj);
     });
	
	
	}

getdistricts(1);
getConstituencies(11);
$('#constiList').multiselect({noneSelectedText:"Select Constittuency(s)"});
</script>	
	
</body>
</html>