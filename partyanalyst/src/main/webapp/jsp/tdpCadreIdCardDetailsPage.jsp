<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <title>TDP Cadre Search </title>

    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet"/>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <script src="js/icheck/icheck.js"></script>

	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<!-- YUI Dependency files (End) -->
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	
	<style>
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
	.textWidth{
	    width: 156px;
	}
	.marginWidth{
	  margin-left: 6px;
	  }
	  .detailsCls{
	    cursor:pointer;
	  }
	</style>
   
	
</head>
  <body class="bgc">
  
  	<!-- Header Row -->
		<div class="row-fluid">
			<div class="span12 header-bg text-center">
				<div class="row-fluid">
				  <div class="span4 offset4 ">
						<img src="images/cadre_images/2014-cadre-Registration-Logo.png">
				  </div>
				  <div class="span4">
					 <a href="newlogoutAction.action" style="font-weight: bold;" class="btn btn-mini pull-left m_top20">Logout</a>
				  </div>
				</div>
			</div>
		</div><!-- Header Row End-->
		
	<div class="container" id="yourElement">
	<div id="myDiv"></div>
	<div id="tableDivForCadre" class="table-responsive"></div>
	
		<div class="span6 offset3 show-grid pad-10b" style="">
		<div id="errorDiv" style="color:#ff0020;"></div>
			<h5 class="text-align">SELECT CONSTITUENCY</h5>

			<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userConstituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" style="width:460px;" onChange="getConstituencyWiseDetails();"/>
			<select class="textWidth" id="panchayatList" onchange="getLocationWiseDetails();" multiple><option value="0"> Select Location </option></select>		
			<select class="span4 marginWidth" id="boothsList" multiple> <option value="0"> Select Booth </option> </select> 	
			<!-- <select style="width:150px;" id="vilagecovrdList"> <option value="0"> Select Covered Village </option> </select>  -->
			<img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>
				
				
					
					
					<a class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2 searchBtn"> Search  <span class="glyphicon glyphicon-chevron-right"></span></a>
					
					
		</div>
		<div class="span8 offset2 show-grid pad-10b" style="">
			<h5 style="text-align:center;"> CADRE DETAILS </h5>
			<div id="dataTableDIV"></div>
		</div>
		
	</div>
	

		<!-- Footer Row -->
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
	
	<script>
		
	function getConstituencyWiseDetails(){
		var cosntiteucnyId = $('#userConstituencyId').val();
		
		$('#errorDiv').html('');
		$('#searchNameId').val('');
		$('#panchayatList').find('option').remove();
		$('#panchayatList').append('<option value="0"> Select Location </option>');
		
		$('#boothsList').find('option').remove();
		$('#boothsList').append('<option value="0"> Select Booth </option>');
						
		if(cosntiteucnyId == 0 )
		{
			$('#errorDiv').html('Please Select Constituency.');
			return;
		}
		$('#filterSearchDiv').show();
		$('#loadingImg').show();
		var jsObj = 
			   {
				  constituencyId:cosntiteucnyId,				
				  task:"getConstituncyWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getConstituncyWiseDetailsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
						
							$('#loadingImg').hide();
					if(result != null )
					{
						for(var i in result)
						{
							$('#panchayatList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
						}
					}
				});
	}
	
	function getLocationWiseDetails(){
		var cosntiteucnyId = $('#userConstituencyId').val();
		var locationId = $('#panchayatList').val();
		$('#errorDiv').html('');
		$('#boothsList').find('option').remove();
		$('#boothsList').append('<option value="0"> Select Booth </option>');
		$('#loadingImg').show();			
		var jsObj = 
			   {
					constituencyId:cosntiteucnyId,	
					locationId : locationId,
					task:"getLocationWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getMultipleLocationWiseDetailsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					$('#loadingImg').hide();
					if(result != null )
					{
						for(var i in result)
						{
							$('#boothsList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
						}
					}
				});
	}
	
	function getRegCadreOfLocation(num){
		var cosntiteucnyId = $('#userConstituencyId').val();
		var locationIds = $('#panchayatList').val();
		var boothIds = $('#boothsList').val();
		
		$('#loadingImg').show();			
		var jsObj = {
			constituencyId:cosntiteucnyId,	
			locationIds : locationIds,
			boothIds : boothIds,
			firstRecord:num,
			maxRecords:10,
			task:"getRegCadreOfLocation"             
		}				   
		$.ajax({
			type : "POST",
			url : "getRegisteredDetailsByLocation.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$('#loadingImg').hide();
			console.log(result);
		});
	}
	
	function getTotalNewsToChangeKeywords(){
		var locationIds = [];
		var boothIds = $('#boothsList').val();
		var locationIds = $('#panchayatList').val();
		var locationType = "panchayat";
		if(boothIds!=null){
			locationIds =  boothIds;
			locationType ="booth"
		}
		
		YAHOO.widget.DataTable.checkBox = function(elLiner, oRecord, oColumn, oData){
			var str='';
			var name = oData;
			var fileId = oRecord.getData("id");
			
			
			str +="<input type='checkbox' class='cadreId' value='"+fileId+"'/>";
			
			elLiner.innerHTML=str;
		};
	
	   var newsColumns = [
				   {key:"SELECT",label:"SELECT",formatter:YAHOO.widget.DataTable.checkBox},
				   {key:"name", label:"NAME"},
				   {key:"percentStr", label:"RELATIVE NAME"},
				   {key:"number", label:"MOBILE NO"}
		];
		
		
  
		var newsDataSource = new YAHOO.util.DataSource("getRegisteredDetailsByLocation.action?&locationIds="+locationIds+"&locationType="+locationType+"&maxIndex=10&");
		newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		newsDataSource.responseSchema = {
		resultsList: "cadreRegisterInfoList",
		fields: ["id","name","percentStr","number"],
		metaFields: {
		totalRecords: "totalCount"// Access to value in the server response
		 },
	  };
  
  
	  var myConfigs = {
		initialRequest: "&sort=relativeName&dir=asc&startIndex=0&results=10", // Initial request for first page of data
		dynamicData: true, // Enables dynamic server-driven data
		sortedBy : {key:"name", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
	    paginator : new YAHOO.widget.Paginator({ 
					rowsPerPage    : 10 
					})  // Enables pagination
	};

	var newsDataTable = new YAHOO.widget.DataTable("dataTableDIV",
	newsColumns, newsDataSource, myConfigs);

	newsDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
}
	$(".searchBtn").click(function(){
		getTotalNewsToChangeKeywords();
	});
	
	</script>
  </body>
</html>