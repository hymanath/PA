<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <title>TDP Cadre Search </title>

    
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet"/>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
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
	
	<link href="css/bootstrap.min.css" rel="stylesheet"/>	
	<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
	<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
	
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
	  .selcUnselc{margin:5px;}
	  
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
		<h2 style="color:white;text-decoration: underline;" class="offset2">Cadre Membership Card Dispatcher Details</h2>
		<div class="span6 offset3 show-grid pad-10b" style="">
		<div id="errorDiv" style="color:#ff0020;"></div>
			<h5 class="text-align">SELECT CONSTITUENCY</h5>

			<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userConstituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" style="width:460px;" onChange="getConstituencyWiseDetails();"/>
			<select class="textWidth" id="panchayatList" onchange="getLocationWiseDetails();" multiple="true"><option value="0"> Select Location </option></select>		
			<select class="span4 marginWidth" id="boothsList" multiple="true"> <option value="0"> Select Booth </option> </select> 	
			<!-- <select style="width:150px;" id="vilagecovrdList"> <option value="0"> Select Covered Village </option> </select>  -->
			<img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>
				
				
					
					
					<a class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2 searchBtn"> Search  <span class="glyphicon glyphicon-chevron-right"></span></a>
					
					
		</div>
		<div class="span9 offset2 show-grid pad-10b cadreDetailsDiv" style="display:none">
			<h5 style="text-align:center;"> CADRE DETAILS </h5>
			<span class='btn btn-info btn-mini selcUnselc' onclick="selectAllMems('selc')">SELECT ALL</span><span class='btn btn-danger btn-mini selcUnselc' onclick="selectAllMems('unselc')">UNSELECT ALL</span>
			<div id="dataTableDIV" class="yui-skin-sam"></div>
			<span class='btn btn-info btn-mini selcUnselc' onclick="selectAllMems('selc')">SELECT ALL</span><span class='btn btn-danger btn-mini selcUnselc' onclick="selectAllMems('unselc')">UNSELECT ALL</span>
		</div>
		
			    	
		<div class="span9 offset2 show-grid pad-10b cardSenderDetailsDiv" style="display:none">
		
			<h5 class="text-align">MEMBERSHIP CARD DISPATCHER DETAILS</h5>

			<div id="cardSenderDiv" style="color:#ff0020;" class="span11 row-fluid" >
				<div class="span5">
					<div class=""><label style="color:gray">Name:</label> <input type="text" name="name" id="hostName"></div></br>
					<div class=""><label style="color:gray">Mobile Number:</label> <input type="text" name="mobileNumber" id="hostNumber"></div></br>
				</div>
				<div class="span5">
					<div class=""><label style="color:gray">Message:</label> <textarea name="message" id="hostMessage" cols="30" rows ="4" readonly="true"></textarea> </div></br>
				</div>
				
		    </div>
			<div class="errorMsgDiv" class="offset1">
					<span class="errorMsg offset1" style="color:red"></span>
					<span class="statusMsg offset1" style="color:green"></span>
				</div>
		    <div id="tdpCadreIdsDiv" style="color:#ff0020;">
		   
		    </div>
			
			<img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>
				
			<a href="javascript:{cardSenderAndReceiver();}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2"> Submit <span class="glyphicon glyphicon-chevron-right"></span></a>
			
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
	var defaultMsg = " మీ యొక్క పార్టీ గుర్తింపు కార్డు మీ నియోజకవర్గానికి పంపబడినది, రెండు రోజులు తర్వాత దయచేసి సంప్రదించగలరు .  ";
	$("#hostMessage").val(defaultMsg);
	
	/* $(document).ready(function(){
		 $('#panchayatList').multiselect({}); 	
		 $('#boothsList').multiselect({}); 	
	}); */
	
	function getConstituencyWiseDetails(){
		var cosntiteucnyId = $('#userConstituencyId').val();
		
		$('#errorDiv').html('');
		$('#searchNameId').val('');
		//$('#panchayatList').multiselect('refresh'); 	
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
					if(result != null ){
						for(var i in result){
							$('#panchayatList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
						}
					}
					
					//$('#panchayatList').multiselect({noneSelectedText:"Select Panchayat(s)"});
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
					if(result != null ){
						for(var i in result){
							$('#boothsList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
						}
					}
					
				});
				/* $('#boothsList').multiselect({
					noneSelectedText:"Select Booth(s)"}); */
	}

	function cardSenderAndReceiver(){			
		var hName=$("#hostName").val();
		var hNumber = $("#hostNumber").val();
		var hMessage = $("#hostMessage").val();
		var cadreIds = [];
		var mobileNumbers= [];
		$('input:checkbox.cadreId').each(function () {
			var sThisVal = (this.checked ? $(this).val() : "");
			if(sThisVal!=""){
				cadreIds.push(sThisVal);
				var mobi = $(".mobileNm"+sThisVal).text();
				mobileNumbers.push(mobi);
			}
		});
		
		if(hName.trim()==""){
			$(".errorMsg").html(" Please Enter Card Dispatcher Name ");
			return;
		}
		//alert(isNaN(parseInt(hNumber)));
		if(hNumber.trim()==""){
			$(".errorMsg").html(" Please Enter Card Dispatcher Mobile Number ");
			return;
		}
		if(hNumber.trim()!=""){
			if(hNumber.trim().length!=10){
				$(".errorMsg").html(" Please Enter Valid Mobile Number ");
				return;
			}
			
		}
		if(hMessage.trim()==""){
			$(".errorMsg").html(" Please Enter Message ");
			return;
		}
		if(cadreIds.length<=0){
			$(".errorMsg").html(" Please Select AtLeast One Cadre Member ");
			return;
		}
		
			var jsObj ={
					name:hName,
					mobileno:hNumber,
					message:hMessage,
					tdpcadreids:cadreIds,
					mobileNumbers : mobileNumbers,
					task:"setCardSenderReceiverDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "setCardSenderAndReceiverDetailsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					$('#loadingImg').hide();
					if(result != null ){
						getTotalCadreMembers();
						$(".statusMsg").html(" Dispatched Status Updated Successfully.");
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
		});
	}
	var membersChecked = [];
	function getTotalCadreMembers(){
		
		
		
		var consiId = $('#userConstituencyId').val();
		var panId = $('#panchayatList').val();
		var bthId = $('#boothsList').val();
		
		if(consiId==0){
			$("#errorDiv").html("Please Select Constituency");
			return;
		}
		if(panId == null || panId ==0){
			$("#errorDiv").html("Please Select Panchayat");
			return;
		}
		
		$(".cadreDetailsDiv").css("display","block");
		$(".cardSenderDetailsDiv").css("display","block");
		
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
				console.log(membersChecked);
				
				if(membersChecked.indexOf(fileId) != -1){
					str +="<input type='checkbox' class='cadreId' value='"+fileId+"'  checked='checked' onclick='addToArray("+fileId+")'/>";
				}else{
					str +="<input type='checkbox' class='cadreId' value='"+fileId+"' onclick='addToArray("+fileId+")'/>";
				}
			elLiner.innerHTML=str;
		};
		
		YAHOO.widget.DataTable.image = function(elLiner, oRecord, oColumn, oData){
			var str='';
			var name = oData;
			var image = oRecord.getData("date");
			
			
			str +='<img style="width:80px;height:80px;cursor:pointer;" src="'+image+'" onerror="setDefaultImage(this);" />';
			
			elLiner.innerHTML=str;
		};
		
		YAHOO.widget.DataTable.mobileNumber = function(elLiner, oRecord, oColumn, oData){
			var str='';
			var name = oData;
			var mobileNumber = oRecord.getData("number");
			var id = oRecord.getData("id");
			
			
			
			str +='<span class="mobileNm'+id+'">'+mobileNumber+'</span>';
			
			elLiner.innerHTML=str;
		};
		
		
	
	   var newsColumns = [
				   {key:"SELECT" ,label:"SELECT",formatter:YAHOO.widget.DataTable.checkBox},
				   {key:"PHOTO",label:"PHOTO",formatter:YAHOO.widget.DataTable.image},
				   {key:"name", label:"NAME",sortable: true},
				   {key:"memberShipNo", label:"MEMBERSHIP NO",sortable: true},
				   {key:"percentStr", label:"RELATIVE NAME",sortable: true},
				   {key:"mobileNo", label:"MOBILE NO",sortable: true,formatter:YAHOO.widget.DataTable.mobileNumber}
				   
		];
		
		
  
		var newsDataSource = new YAHOO.util.DataSource("getRegisteredDetailsByLocation.action?&locationIds="+locationIds+"&locationType="+locationType+"&maxIndex=10&");
		newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		newsDataSource.responseSchema = {
		resultsList: "cadreRegisterInfoList",
		fields: ["id","name","percentStr","number","date","memberShipNo"],
		metaFields: {
		totalRecords: "totalCount"// Access to value in the server response
		 },
	  };
  
  
	  var myConfigs = {
		initialRequest: "&sort=name&dir=asc&startIndex=0&results=10", // Initial request for first page of data
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
		//$(".selectall").attr('checked', false); 
		return oPayload;
	}
}
	$(".searchBtn").click(function(){
		getTotalCadreMembers();
	});
	
	function setDefaultImage(img){
		img.src = "images/mahaNadu/user image.jpg";
	}
	
	$(".dfltMsg").click(function(){
		$("#hostMessage").val("");
		if ($('input.dfltMsg').is(':checked')) {
			$("#hostMessage").val(defaultMsg);
		}
	});
	
	function addToArray(cadreId){
		var idx = $.inArray(cadreId, membersChecked);
		if (idx == -1) {
		  membersChecked.push(cadreId);
		} else {
		  membersChecked.splice(idx, 1);
		}
	}
	 
	
	
		function selectAllMems(fromBtn){
			//if ($('input.selectall').is(':checked')) {
			if(fromBtn =="selc"){
				$('.cadreId').each(function() { 
					this.checked = true;  
					addToArray(parseInt($(this).val()));
				});
			}else{
				$('.cadreId').each(function() { 
					this.checked = false;
					membersChecked = [];
					//addToArray(parseInt($(this).val()));
				});  
			}
		}
		
		
	
	</script>
  </body>
</html>