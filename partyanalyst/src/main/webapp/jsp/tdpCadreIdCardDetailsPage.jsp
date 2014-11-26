<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <title>TDP Cadre Search </title>

    <link href="css/animate.css" rel="stylesheet"/>	
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
	
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
	
	<link href="css/bootstrap.min.css" rel="stylesheet"/>	
	<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

	
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
	  .ui-state-active, .ui-state-default{ background-image: none !important;}
	</style>
   
	
</head>


<body  class="bgc">
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
	<div class="container " id="dashboadElmnt">	

		<div id="locationWiseCadreInfoDiv">

		<div id="tableDivForCadre" class="table-responsive"></div>
		<div class="show-grid " style="margin-top: -25px;">

		<div id="errorDiv" style="color:#ff0020;" align="center"></div>
			<div class="row-fluid ">
			<div class="span12 well well-small  border-radius-0 mb-0 " style="padding:0px;margin-bottom:0px;">
					<h3 class="offset2 text-uppercase"> MEMBERSHIP CARD DISPATCHER DETAILS 
					<c:if test="${sessionScope.USER.isAdmin == 'true'}">
					<a class="btn btn-success" style="float: right;margin-right:25px;" href="cadreDashBoardAction.action"> home </a>					
					</c:if> 
					</h3>
				</div>
			   <div style="min-height: 300px;background:#ffffff;margin-left:0px;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
			   <table  style="margin-left: 270px;">
					 <tr>
					   <td><b>Select Constituency : </b></td>
					   <td><s:select theme="simple" cssClass=" span12 input-block-level" id="userConstituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" style="width:250px;" onChange="getConstituencyWiseDetails();"/>
						</td>
					 </tr>
				     <tr>
						<td><b>Select Panchayat </b></td>
						<td><select class="textWidth" id="panchayatList" onchange="getLocationWiseDetails();" multiple="true" style="width:250px;" ><option value="0" > Select Panchayat </option></select>
						</td>
				     </tr>
					  <tr>
						<td><b> </b></td>
						<td><b> (OR)</b>
						</td>
				     </tr>
				   <tr>
					   <td><b>Select Booth : </b></td>
					   <td><select class="span4 marginWidth" id="boothsList" multiple="true" style="width:250px;"> <option value="0"> Select Booth </option> </select></td>
				   </tr>
				  
				   <tr>
				      <td></td><td>
					 <img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>
					   
					   <a class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2 searchBtn"> Search  <span class="glyphicon glyphicon-chevron-right"></span></a>
				
				<div><img src='images/Loading-data.gif' class="offset5"  id="searchDashboardImg" style="width:70px;height:60px;display:none;"/></div>
				
					  </td>
				  </tr>
			</table>
			<div class=" show-grid cadreDetailsDiv" style="display:none;text-align:center;margin-top:15px;">
				<h5 style="text-align: center; background-color: skyblue; border-radius: 5px; padding: 5px; height: 20px;"> CADRE DETAILS </h5>
				<span class='btn btn-info btn-mini selcUnselc' onclick="selectAllMems('selc')">SELECT ALL</span><span class='btn btn-danger btn-mini selcUnselc' onclick="selectAllMems('unselc')">UNSELECT ALL</span>
				<div id="dataTableDIV" class="yui-skin-sam offset pad-10b"></div>
				<span class='btn btn-info btn-mini selcUnselc' onclick="selectAllMems('selc')">SELECT ALL</span><span class='btn btn-danger btn-mini selcUnselc' onclick="selectAllMems('unselc')">UNSELECT ALL</span>
			 </div>
			<div class="show-grid cardSenderDetailsDiv" style="display:none;text-align: center;margin-top: 25px">
		
					<h4 class="text-align">MEMBERSHIP CARD DISPATCHER DETAILS</h4>

					<div id="cardSenderDiv" style="color:#ff0020;" class="span11 row-fluid" >
						<div class="span5 offset2">
							<div class=" offset2"><label style="color:gray">Name:</label> <input type="text" name="name" id="hostName"></div></br>
							<div class=""><label style="color:gray">Mobile Number:</label> <input type="text" name="mobileNumber" id="hostNumber"></div></br>
						</div>
						<div class="span5 ">
							<div class=""><label style="color:gray">Message:</label> <textarea name="message" id="hostMessage" cols="30" rows ="4" readonly="true"></textarea> </div></br>
						</div>
						
					</div>
						<div class="errorMsgDiv" class="offset1">
							<span class="errorMsg offset1" style="color:red"></span>
							<span class="statusMsg offset1" style="color:green"></span>
						</div>
						<div id="tdpCadreIdsDiv" style="color:#ff0020;"></div>
					
					<img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>
						
					<a href="javascript:{cardSenderAndReceiver();}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2"> Submit <span class="glyphicon glyphicon-chevron-right"></span></a>
					
			</div>
			</div>
		</div>
					
		</div>
		
		
			    	
		
	</div>
	</div>
		<!-- Footer Row -->
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					TDP Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
<script>
	var defaultMsg = " మీ యొక్క పార్టీ గుర్తింపు కార్డు మీ నియోజకవర్గానికి పంపబడినది, రెండు రోజులు తర్వాత దయచేసి సంప్రదించగలరు .  ";
	$("#hostMessage").val(defaultMsg);
	
	 $(document).ready(function(){
		 $('#panchayatList').multiselect({}); 	
		 $('#boothsList').multiselect({});
		 $("#boothsList").multiselect("uncheckAll");
		 $("#panchayatList").multiselect("uncheckAll");		 
	}); 
	
	function getConstituencyWiseDetails(){
		var cosntiteucnyId = $('#userConstituencyId').val();
		
		$('#errorDiv').html('');
		$('#searchNameId').val('');
		 	
		$('#panchayatList').find('option').remove();
		//$('#panchayatList').append('<option value="0"> Select Panchayat </option>');
		
		$('#boothsList').find('option').remove();
		//$('#boothsList').append('<option value="0"> Select Booth </option>');
						
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
					$('#panchayatList').multiselect('refresh');
					$('#panchayatList').multiselect({noneSelectedText:"Select Panchayat(s)"});
					$("#panchayatList").multiselect("uncheckAll");
				});
				
				
	}
	function getLocationWiseDetails(){
	
		var cosntiteucnyId = $('#userConstituencyId').val();
		var locationId = $('#panchayatList').val();
		$('#errorDiv').html('');
		 
		$('#boothsList').find('option').remove();
		//$('#boothsList').append('<option value="0"> Select Booth </option>');
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
						
						$('#boothsList').multiselect({noneSelectedText:'Select Booth(s)'}); 
				$('#boothsList').multiselect('refresh');
				$("#boothsList").multiselect("uncheckAll");
					}					
				});				
				
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
		
		//var locationIds = [];
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
				//console.log(membersChecked);
				
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
		 $('#dataTableDIV').find('table').addClass("table table-bordered");
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