<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>TDP Cadre Family Details</title>
	 <!-- Bootstrap -->
   
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
		 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
		<script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
		<link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script>
	
	</script>
	<style>
	body{
		background:white !important;
	}
.mandatory
{
	color:red !important;
		
}
tr.lowPerf{background:#FFA500 !important;}
tr.lowPerf td{background:#FFA500 !important;}
#dayWiseUsersDetailsId  thead th{
background-color: #dff0d8  !important;
color : #468847 !important;
line-height: 15px !important;
}
#dayWiseUsersDetailsId th{text-align:center !important;}
#dayWiseUsersDetailsId tr.odd td.sorting_1{
background-color: #d3d3d3 !important;
}
#dayWiseUsersDetailsId tr.even td.sorting_1 {
background-color: #fafafa !important;
}
#dayWiseUsersDetailsId tr.odd {
background-color: #E5E5E5 !important;
}

	</style>

<style type="text/css">
	.m_top10
	{
		margin-top:10px;
	}
	.m_top20
	{
		margin-top:20px;
	}
	.panel-custom
	{
		border:0px;
	}
	.panel-custom .panel-heading
	{
		background:#fff;
	}
	.shadow-box
	{
		box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75);
		background-color: rgb(255, 255, 255) !important;
	}
	
	/* radio button */
.onoffswitch {
    position: relative; width: 90px;
    -webkit-user-select:none; -moz-user-select:none; -ms-user-select: none;
}
.onoffswitch-checkbox {
    display: none;
}
.onoffswitch-label {
    display: block; overflow: hidden; cursor: pointer;
    border: 0px solid #999999; border-radius: 20px;
}
.onoffswitch-inner {
    display: block; width: 200%; margin-left: -100%;
    -moz-transition: margin 0.3s ease-in 0s; -webkit-transition: margin 0.3s ease-in 0s;
    -o-transition: margin 0.3s ease-in 0s; transition: margin 0.3s ease-in 0s;
}
.onoffswitch-inner:before, .onoffswitch-inner:after {
    display: block; float: left; width: 50%; height: 30px; padding: 0; line-height: 30px;
    font-size: 14px; color: white; font-family: Trebuchet, Arial, sans-serif; font-weight: bold;
    -moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box;
}
.onoffswitch-inner:before {
    content: "ON";
    padding-left: 10px;
    background-color: #62B762; color: #FFFFFF;
}
.onoffswitch-inner:after {
    content: "OFF";
    padding-right: 10px;
    background-color: #E35F5F; color: ;
    text-align: right;
}
.onoffswitch-switch {
    display: block; width: 18px; margin: 6px;
    background: #FFFFFF;
    border: 0px solid #999999; border-radius: 20px;
    position: absolute; top: 0; bottom: 0; right: 56px;
    -moz-transition: all 0.3s ease-in 0s; -webkit-transition: all 0.3s ease-in 0s;
    -o-transition: all 0.3s ease-in 0s; transition: all 0.3s ease-in 0s; 
}
.onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-inner {
    margin-left: 0;
}
.onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-switch {
    right: 0px; 
}
.disnone
{
	display:none
}
.button-edit , .button-done , .button-edit1 , .button-done1 , .button-edit2 , .button-done2 , .button-edit3 , .button-done3
{
	cursor:pointer;
	text-decoration:underline
}
.color-ccc , .color-ccc:hover, .color-ccc:active , .color-ccc:focus
{
	color:#CCCCCC;
	text-decoration:none
}
.border-btm
{
	border-bottom:1px solid #ccc
}
.pad_10
{
	padding:10px;
}
</style>
</head>

<body>
<!--<div class="container shadow-box">
	<h3 class="text-center border-btm">SEARCH CADRE</h3>
	<div class="row m_top10 ">
    	<div class="col-md-3 col-md-offset-2">
			<div id="errorDiv" ></div>
		</div>
	</div>
	
    <div class="row  pad_10">
    	
		<div class="col-md-3 col-md-offset-2">
			
        	<label class="control-label">Enter Membership ID</label>
            <input class="form-control" type="text" id="membershipNo"  placeholder="ENTER MEMBERSHIP ID "/>
        </div>
    	<div class="col-md-3">
        	<label class="control-label">Enter Mobile No</label>
            <input class="form-control" type="text"  placeholder="ENTER MOBILE NO " id="mobileNo" maxlength="10" />
        </div>
    	<div class="col-md-3">
        	<label class="control-label">Enter Voter ID</label>
            <input class="form-control" type="text" placeholder=" ENTER VOTERID" id="voterId"/>
        </div>
        <div class="col-md-4 col-md-offset-4 m_top20">
        	<button class="btn btn-success btn-block" onclick="getCadreDetails();">SEARCH</button>
        </div>
    </div>
</div>-->

<div class="container">
	<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center" style="background-color: lightyellow; border-radius: 4px;height: 35px;width: 800px;margin-left: 180px;">
			<h4 id="headingDiv" class="text-uppercase"><b> CADRE PROFILE UPDATION DETAILS </b></h4>
				
		</div>
	<div class="row m_top20" id="searchcadrenewDiv" style="display:none;">	
		
		
		<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0">
			<c:if test="${sessionScope.USER.accessType == 'STATE'}">
				<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0" id="statedisplaydivid">
					<label>State</label>
					<c:if test="${sessionScope.USER.stateName == 'both'}">
						<select id="statesDivId" onchange="getConstituenciesForStateAjax();" class="form-control">
							<option value="0">All</option>
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						</select>
					</c:if>
					<c:if test="${sessionScope.USER.stateName == 'TS'}">
						<select id="statesDivId" onchange="getConstituenciesForStateAjax();" class="form-control">
							<option value="2">Telangana</option>
						</select>
					</c:if>
					<c:if test="${sessionScope.USER.stateName == 'AP'}">
						<select id="statesDivId" onchange="getConstituenciesForStateAjax();" class="form-control">
							<option value="1">AndhraPradesh</option>
						</select>
					</c:if>
							
				</div>
			</c:if>
						
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0" id="constitunecyDiv">
				<label>Constituency</label>
				<select class="form-control " id="constituencyId" class="form-control" onchange="getMandalCorporationsByConstituency()"></select>
			</div>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0" style="padding-top: 10px" id="mandalDiv">
				<label>Mandal/Municipality</label>
					<select class="form-control " id="mandalList" class="form-control" onchange="getPanchayatWardByMandalSearch();">
						<option value="0"> Select Mandal/Municipality </option>
					</select>
			</div>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0" style="padding-top: 10px" id="panchayatDiv">
				<label>Panchayat</label>
				<select class="form-control " id="panchaytList" class="form-control">
					<option value="0"> Select Panchayat </option>
				</select>
			</div>
		</div>
				
		<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center" style="padding-top: 15px; padding-bottom: 15px;">
			<div class="form-inline ">
				<div class="radio">
					<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1"> Membership ID &nbsp;&nbsp;</label>
				
					<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2" > Voter ID &nbsp;&nbsp;</label>
				
					<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"  onclick="refreshExistingDetails();"  value="3"> Mobile No &nbsp;&nbsp;</label>
				
					<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  onclick="refreshExistingDetails();"  value="4"> Name &nbsp;&nbsp;</label>
					
					<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="trNo"  onclick="refreshExistingDetails();"  value="5"> TR No &nbsp;&nbsp;</label>
							
					<input type="hidden" id="cadreSearchType" value="membershipId" />
				</div>				  
			</div>
		</div>
				
		<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0"  id="basicSearchDiv">	
			<div class="row-fluid">      
				<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0">
					<input type="text" placeholder="ENTER MEMBERSHIP ID / VOTER ID / MOBILE NO / NAME"  class="form-control" id="searchBy">
					<div id="searchErrDiv"></div>
				</div>	
			</div>			
		</div>
							
		<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
			<div class="row-fluid"><div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 ">
				<input id="searchbtn" class="btn btn-success btn-block" type="button" onclick="getCadreDetailsBySearchCriteria(0)" value="SEARCH"></input></div>
			</div>	
		</div>
	</div>
	
	<div class="row">
		
		<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
			<div id="topPaginationDivId" class="paginationDivId"></div>
			<div id="errorDiv"></div>
			
			<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;overflow:scroll:900px;display:none;" id="cadreDetailsDiv"></div>
			<center><div><img src='images/Loading-data.gif' class="offset7"  id="searchDataImg" style=" margin-top: 20px;width:70px;height:60px;display:none;"/></div></center>
			<div id="paginationDivId"  class="paginationDivId"></div>
		</div>
	</div>
	
</div>

<div class="container shadow-box m_top20" id="tableDataDiv"></div>
<div class="container shadow-box m_top20" id="tdpCadreDetails" ></div>
<div class="container shadow-box m_top20" id="cadreAddressDivId" ></div>
<div class="container shadow-box m_top20" id="familyDetalsDiv" ></div>
<!--<img src='images/Loading-data.gif' class="offset5"  id="searchDataImg" style="width:70px;height:60px;display:none;margin-left:550px"/>-->
<div class="container shadow-box m_top20" id="btnDiv" style="">
		<button class="btn btn-success " style="margin-top:15px;margin-bottom:15px;;margin-left:500px;display:none;" onclick="updateFamilyInfo();" id="updateeBtn"> UPDATE DETAILS </button>
</div>

		
<script>

	var tdpCadreId = '${task}';

	var relationsArr = new Array();
	var casteArr = new Array();
	var occupationArr = new Array();
	var educationArr = new Array();
	var bloodGroupArr = new Array();
	<c:forEach var="caste" items="${castesList}">
				var obj = {
					id :  '${caste.casteId}',
					name :  '${caste.castName}'
				}
				casteArr.push(obj);
		</c:forEach>

	var searchArr = new Array();
	
	<c:forEach var="caste" items="${educationList}">
				var obj = {
					id :  '${caste.id}',
					name :  '${caste.name}'
				}
				educationArr.push(obj);
		</c:forEach>
		
		<c:forEach var="caste" items="${occupationsList}">
				var obj = {
					id :  '${caste.id}',
					name :  '${caste.name}'
				}
				occupationArr.push(obj);
		</c:forEach>

				
	</script>
	<script>
	
	$(document).ready(function(){
		checkIsTdpCadreIdAvailableOrNot();
		getAllRelationDetails();
			$(document).keypress(function(e) {
				if(e.keyCode==13){
					 getCadreDetails();
				}
		  });
		  
		  
				$(".hide-inline , .hide-inline1 , .hide-inline2 , .hide-inline3").hide();
				$(".button-edit").click(function(){
					$(".hide-inline").removeClass("disnone");
					$(".show-inline").addClass("disnone");
					$(".hide-inline").show();
				});
				$(".button-done").click(function(){
					$(".show-inline").removeClass("disnone");
					$(".hide-inline").addClass("disnone");
					$(".hide-inline").hide();
				});
				$(".button-edit1").click(function(){
					$(".hide-inline1").removeClass("disnone");
					$(".show-inline1").addClass("disnone");
					$(".hide-inline1").show();
				});
				$(".button-done1").click(function(){
					$(".show-inline1").removeClass("disnone");
					$(".hide-inline1").addClass("disnone");
					$(".hide-inline1").hide();
				});
				$(".button-edit2").click(function(){
					$(".hide-inline2").removeClass("disnone");
					$(".show-inline2").addClass("disnone");
					$(".hide-inline2").show();
				});
				$(".button-done2").click(function(){
					$(".show-inline2").removeClass("disnone");
					$(".hide-inline2").addClass("disnone");
					$(".hide-inline2").hide();
				});
				$(".button-edit3").click(function(){
					$(".hide-inline3").removeClass("disnone");
					$(".show-inline3").addClass("disnone");
					$(".hide-inline3").show();
				});
				$(".button-done3").click(function(){
					$(".show-inline3").removeClass("disnone");
					$(".hide-inline3").addClass("disnone");
					$(".hide-inline3").hide();
				});
				 
	});
	
	function checkIsTdpCadreIdAvailableOrNot()
	{
		if(tdpCadreId != null && tdpCadreId.trim().length > 0 && parseInt(tdpCadreId)>0)
		{
			$('#searchcadrenewDiv').hide();
			
			var jsobj={
				cadreId:tdpCadreId
			};
			$('#searchDataImg').show();
			$.ajax({
				type:'GET',
				 url: 'cadreFormalDetailedInformationAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(result){
				//console.log(result);
				$('#searchDataImg').hide();
				if(result != null)
				{
					$('#cadreDetailsDiv').show();
					var str =" ";		
						str+='<div class="span12">';
							
						str+=' <table class="table table-bordered" style="margin-top: 20px;"  id="tableId">';
						str+=' <thead>';
						str+=' <tr>';
						
						str+=' <th class="alert-info"> Image  </th>';
						str+=' <th class="alert-info"> Cadre Name  </th>';
						str+=' <th class="alert-info" > Membership Number  </th>';
						str+=' <th class="alert-info" > Mobile Number </th>';
						//str+=' <th class="alert-info" > Constituency </th>';
						str+=' </tr>';
						str+=' </thead>';
						str+=' <tbody>';
							str+=' <tr>';
							str+='<td><a href="javascript:{};" style="cursor:pointer;" onclick="getCadreWithFamilyDetailsOfEachCadre(\''+result.cadreId+'\');"><img src="'+result.imagePath+'"  style="height:50px;width:50px;"/></a></td>';
							str+=' <td><a href="javascript:{};" style="cursor:pointer;" onclick="getCadreWithFamilyDetailsOfEachCadre(\''+result.cadreId+'\');">'+result.name+'</a></td>';
							str+=' <td>'+result.membershipNo+'</td>';
							str+=' <td>'+result.mobileNo+'</td>';
							//str+=' <td>'+result[i].constituencyId+'</td>';
							
							str+=' </tr>';

						str+='</tbody></table></div>';
						$("#cadreDetailsDiv").html(str);
						//$("#tableId").dataTable();
					
				}
			});
			//getCadreWithFamilyDetailsOfEachCadre(tdpCadreId);
		}
		else
		{
			$('#searchcadrenewDiv').show();
			if(accessType == "DISTRICT")
			{
				getAssemblyParlConstituencies(accessValue,"Assembly");
			}
			if(accessType == "STATE")
			{
				getConstituenciesForStateAjax();
			}

			if(accessType == "MP")
			{
				getAssemblyConstituencies(accessValue);
			}
		}
		
	}
/*	function getCadreDetails()
	{
				
		var mobileNo =	$('#mobileNo').val().trim();
		var voterId = $('#voterId').val().trim();
		var membershipNo =$('#membershipNo').val().trim();
		$('#tableDataDiv').html('');
		$('#tdpCadreDetails').html('');
		$('#cadreAddressDivId').html('');
		$('#familyDetalsDiv').html('');
		searchArr=[];
		if(mobileNo.trim().length == 0 && voterId.trim().length == 0 && membershipNo.trim().length == 0)
		{
				$('#errorDiv').html('Please Enter Any Search Criteria').css("color","red");	
				return false;
		}
	
		if(membershipNo != null && membershipNo.trim().length != 0 && membershipNo.trim().length != 8)
		{
				$('#errorDiv').html('Membership ID must be 8 charactors. ').css("color","red");	
				return false;
		}
		else if(mobileNo != null && mobileNo.trim().length != 0 && mobileNo.trim().length != 10)
		{
				$('#errorDiv').html('Please enter valid mobile no. ').css("color","red");	
				return false;
		}
		
		$('#searchDataImg').show();	
		$('#errorDiv').html('');	
		$("#tableDataDiv").html('');	
		$("#tdpCadreDetails").html('');	
		$("#familyDetalsDiv").html('');	
		$("#updateTableDiv").html('');	
		$("#updateFamilyTableDiv").html('');
		$("#btnDiv").hide();
		//$("#ajaxImage").show();
		var jsObj = {
			mobileNo:mobileNo,
			membershipNo:membershipNo,
			voterId:voterId,			
			task:"getDetails"            
		}
  
		$.ajax({
			type : "POST",
			url : "searchCadreForFamilyDetlsUpdationAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			//$("#ajaxImage").hide();
			$('#searchDataImg').hide();				
			if(result != null && result.length>0)			
			{
				buildTableCadreInfo(result);
			}
			else{
				$("#tableDataDiv").html("</span style='text-align:center;'> No data Available<span>");
			}
		});
		
	}

	
	function buildTableCadreInfo(result){
		var str="";	
		str+='<div class="span12">';
			
		str+=' <table class="table table-bordered" style="margin-top: 20px;"  id="tableId">';
		str+=' <thead>';
		str+=' <tr>';
		str+=' <th class="alert-info"> Cadre Name  </th>';
		str+=' <th class="alert-info"> Image  </th>';
		str+=' <th class="alert-info" > Membership Number  </th>';
		str+=' <th class="alert-info" > Mobile Number </th>';
		str+=' <th class="alert-info" > Constituency </th>';
		str+=' </tr>';
		str+=' </thead>';
		str+=' <tbody>';
	
		for(var i in result){
			str+=' <tr>';
		
		
			str+=' <td><a href="javascript:{};" style="cursor:pointer;" onclick="getFamilyDetails(\''+result[i].cadreId+'\');">'+result[i].nameType+'</a></td>';
			str+='<td><img src="images/cadre_images/'+result[i].imageBase64String+'"  style="height:50px;width:50px;"/></td>';
			str+=' <td>'+result[i].address+'</td>';
			str+=' <td>'+result[i].mobileNumber+'</td>';
			str+=' <td>'+result[i].constituencyId+'</td>';
			
			str+=' </tr>';
			
			var cadreObj={
				name:result[i].nameType,
				mobileNo:result[i].mobileNumber,
				gender:result[i].gender,
				age:result[i].age,
				votercardNo :result[i].voterCardNo,
				relationId:null,
				dob: result[i].dobStr != null ? (result[i].dobStr).substring(0,11):"",
				marriageDay:result[i].marriageDateStr != null ? (result[i].marriageDateStr).substring(0,11):"",
				email:result[i].email,
				casteStateId:result[i].casteId ,
				educationId:result[i].educationId,
				occupationId:result[i].occupationId,
				whatsappStatus :result[i].whatsAppStatus,
				partyMemberSince:result[i].partyMemberSinceStr != null ? (result[i].partyMemberSinceStr).substring(0,11):"", 
				tdpCadreId:result[i].cadreId,
				bloodGroupId : 0,
				//userAddress Data
				/* houseNo:result[i].addressVO.houseNo,
				street:result[i].addressVO.street,
				pinCode:result[i].addressVO.pinCode,
				stateId:result[i].addressVO.stateId,
				districtId:result[i].addressVO.districtId,
				constituencyId:result[i].addressVO.constituencyId,
				tehsilId:result[i].addressVO.tehsilId,
				panchaytId:result[i].addressVO.panchaytId,
				landMarkStr:result[i].addressVO.landMarkStr,
				addressVO:result[i].addressVO
				
				
			};
			
			/* searchArr.push(cadreObj);
		}
		//console.log(searchArr);
		str+='</tbody></table></div>';
		$("#tableDataDiv").html(str);
		$("#tableId").dataTable();
	}*/

function getFamilyDetails(tdpCadreId)
	{
		$('#searchDataImg').show();
		$("#btnDiv").show();
		$('#updateeBtn').hide();	
		$('#tdpCadreDetails').hide();	
		$('#familyDetalsDiv').hide();			
		$('#cadreAddressDivId').hide();			
		$('#updateFamilyTableDiv').hide();	
		var jsObj = {
			tdpCadreId:tdpCadreId,			
			task:"getFamilyDetails"            
		}
  
		$.ajax({
			type : "POST",
			url : "getFamilyDetailsByCadreIdAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			//$("#ajaxImage").hide();
			$('#searchDataImg').hide();	
			$("#btnDiv").show();
			$('#updateeBtn').show();	
			$('#tdpCadreDetails').show();	
			$('#cadreAddressDivId').show();	
			$('#familyDetalsDiv').show();	
			$('#updateFamilyTableDiv').show();	
			if(result != null && result.length>0)
			{
				buildfamilyDetails(result,tdpCadreId);
			}	
			else
			{
				buildfamilyDetails(result,tdpCadreId)
			}
				//$("#tdpCadreDetails").html("</span style='text-align:center;'> No data Available<span>");
		});
		
	}
	
	

	function buildfamilyDetails(result,tdpCadreId)
	{
	
		$('#nextBuildBtnId').remove();
		tdpCadreId = tdpCadreId;
		var str ='';
		str+='<h4  class="offset5" style="margin-bottom:0px"> CADRE DETAILS </h4>';
		str+='<div class="row" style="padding:10px">';
		str+='<div id="cadreErrorDiv" class="mandatory" style="margin-left: 18px;"></div>';
		if(searchArr != null && searchArr.length>0)
		{
			for(var l in searchArr)
			{
				if(searchArr[l].tdpCadreId == tdpCadreId)
				{
						str+='<div class="familyInfo">';
						str+='<input type="hidden" value="'+searchArr[l].tdpCadreId+'"  class="tdpCadre" "/>';
						str+='<input type="hidden" value="'+searchArr[l].votercardNo+'"  class="voter" />';
						if(searchArr[l].name != null)
						{
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Name</label>';
							str+='<input type="text" value="'+searchArr[l].name+'" readonly class="name form-control" />';
							str+='</div>';
						}
						else
						{
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Name</label>';
							str+=' <input type="text"  class="name form-control" />';
							str+='</div>';
						}
						if(searchArr[l].mobileNo != null)
						{
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Mobile Number</label>';
							str+=' <input type="text" id="cadreMobile" value="'+searchArr[l].mobileNo+'"  class="mobile form-control" maxlength="10" key="ErrorDivcadre'+l+'"/>';
							str+='</div>';
							
						}
						else
						{
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Mobile Number</label>';
							str+=' <input type="text"  class="mobile form-control"  maxlength="10"  key="ErrorDivcadre'+l+'"/>';
							str+='</div>';
						}
						if(searchArr[l].age != null)
						{
							str+='<div class="col-md-2 col-xs-6">';
							str+='<label class="control-label">Age</label>';
							str+=' <input type="text" value="'+searchArr[l].age+'"  class="age form-control" style="cursor:text;"/>';
							str+='</div>';						
						
							//str+='<td><input type="text" value="'+searchArr[l].dob+'"  class="dob" readonly="true" style="cursor:text;width: 100px"/> </td>';
						}
						else
						{
							str+='<div class="col-md-2 col-xs-6">';
							str+='<label class="control-label">Age</label>';
							str+=' <input type="text"  class="age form-control" style="cursor:text;"  style=""/>';
							str+='</div>';
						
							//str+='<td><input type="text"  class="dob" readonly="true" style="cursor:text;"  style="width: 100px"/> </td>';
						}
						if(searchArr[l].dob != null)
						{
							str+='<div class="col-md-2 col-xs-6">';
							str+='<label class="control-label">DOB</label>';
							str+=' <input type="text" value="'+searchArr[l].dob+'"  class="dob form-control" readonly="true" style="cursor:text;"/>';
							str+='</div>';						
						
							//str+='<td><input type="text" value="'+searchArr[l].dob+'"  class="dob" readonly="true" style="cursor:text;width: 100px"/> </td>';
						}
						else
						{
							str+='<div class="col-md-2 col-xs-6">';
							str+='<label class="control-label">DOB</label>';
							str+=' <input type="text"  class="dob form-control" readonly="true" style="cursor:text;"  style=""/>';
							str+='</div>';
						
							//str+='<td><input type="text"  class="dob" readonly="true" style="cursor:text;"  style="width: 100px"/> </td>';
						}
						
						if(searchArr[l].email != null)
						{
						
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Email</label>';
							str+=' <input type="text" value="'+searchArr[l].email+'"  class="email form-control"  style=""/> ';
							str+='</div>';	

							//str+='<td><input type="text" value="'+searchArr[l].email+'"  class="email"  style="width: 200px"/> </td>';
						}
						else
						{
						
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Email</label>';
							str+=' <input type="text" class="email form-control"  style=""/>';
							str+='</div>';	
							//str+='<td><input type="text" class="email"  style="width: 200px"/> </td>';
						}
						if(casteArr != null && casteArr.length>0)
						{
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Caste</label>';
							str+='<select id="cadreCaste" class="casteState form-control"  style="">';
							str+='<option value="0" selected="selected">Select Caste</option>';
							//console.log("details : " + searchArr[l].relationId);
							for(var k in casteArr)
							{
								if(searchArr[l].casteStateId != null && (casteArr[k].id ==searchArr[l].casteStateId ))
								{
								
									str+='<option value="'+casteArr[k].id+'" selected="selected">'+casteArr[k].name+'</option>';
								}
								else
								{
									str+='<option value="'+casteArr[k].id+'" >'+casteArr[k].name+'</option>';
								}
							}
							str+='</select></div>';
						}
						
						if(educationArr != null && educationArr.length>0)
						{
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Education</label>';
							str+='<select id="" class="education form-control" >';
							str+='<option value="0" selected="selected">Select Education</option>';
							for(var k in educationArr)
							{
								if(searchArr[l].educationId != null && (educationArr[k].id ==searchArr[l].educationId ))
								{
									str+='<option value="'+educationArr[k].id+'" selected="selected">'+educationArr[k].name+'</option>';
								}
								else
								{
									str+='<option value="'+educationArr[k].id+'" >'+educationArr[k].name+'</option>';
								}
							}
							str+='</select></div>';
						}
					
						if(occupationArr != null && occupationArr.length>0)
						{
						
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Occupation</label>';
							str+='<select id="" class="occupation form-control">';
							str+='<option value="0" selected="selected">Select Occupation</option>';
							for(var k in occupationArr)
							{
								if(searchArr[l].occupationId != null && (occupationArr[k].id ==searchArr[l].occupationId ))
								{
									str+='<option value="'+occupationArr[k].id+'" selected="selected">'+occupationArr[k].name+'</option>';
								}
								else
								{
									str+='<option value="'+occupationArr[k].id+'" >'+occupationArr[k].name+'</option>';
								}
							}
							str+='</select></div>';
						}
						if(searchArr[l].partyMemberSince != null)
						{
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Party Member Since</label>';
							str+=' <input type="text" value="'+searchArr[l].partyMemberSince+'"  class="membersince form-control"  readonly="true" style="cursor:text;"/> ';
							str+='</div>';	
						
							//str+='<td><input type="text" value="'+searchArr[l].partyMemberSince+'"  class="membersince"  readonly="true" style="cursor:text; width: 100px"/> </td>';
						}
						else
						{
						
							
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Party Member Since</label>';
							str+=' <input type="text"  class="membersince form-control"  readonly="true" style="cursor:text;"/> ';
							str+='</div>';	
							//str+='<td><input type="text"  class="membersince"  readonly="true" style="cursor:text;width: 100px"/> </td>';
						}
						
						
						if(searchArr[l].marriageDay != null)
						{
						
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Marriage Date</label>';
							str+=' <input type="text" value="'+searchArr[l].marriageDay+'"  class="marriageDay form-control" readonly="true" style="cursor:text;"/>';
							str+='</div>';	
						
							//str+='<td><input type="text" value="'+searchArr[l].marriageDay+'"  class="marriageDay" readonly="true" style="cursor:text;width: 100px"/> </td>';
						}
						else
						{
						
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Marriage Date</label>';
							str+=' <input type="text" class="marriageDay form-control" readonly="true" style="cursor:text;"/> ';
							str+='</div>';	

							//str+='<td><input type="text" class="marriageDay" readonly="true" style="cursor:text;width: 100px"/> </td>';
						}
						str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Interested For WhatsApp Group? </label>';
							
							str+='<select id="" class="whatsappStatus form-control">';
						//	console.log(searchArr[l].whatsappStatus);
							if(searchArr[l].whatsappStatus != null)
							{
								if( searchArr[l].whatsappStatus == 'YES')
								{
									str+='<option value="NO" >No</option>';
									str+='<option value="YES" selected="selected">Yes</option>';
								}
								else if( searchArr[l].whatsappStatus == 'NO')
								{
									str+='<option value="NO" selected="selected">No</option>';
									str+='<option value="YES" >Yes</option>';
								}
							}
							else
							{
								str+='<option value="NO" >No</option>';
								str+='<option value="YES">Yes</option>';
							}

						str+='</select></div>';
						if(searchArr[l].faceboohUrl != null)
						{
						
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Facebook URL : </label>';
							str+=' <input type="text"  value="'+searchArr[l].faceboohUrl+'" id="facebookUrl" class="facebookUrl form-control" style="cursor:text;"/>';
							str+='</div>';	
						}
						else
						{						
							str+='<div class="col-md-3 col-xs-6">';
							str+='<label class="control-label">Facebook URL : </label>';
							str+=' <input type="text"  id="facebookUrl"  class="facebookUrl form-control" style="cursor:text;"/> ';
							str+='</div>';	
						}
						str+='<div class="col-md-2 col-xs-6">';
							str+=' <div id="ErrorDivcadre'+l+'" class="mandatory"></div> ';
							str+='</div>';	
					str+='</div>';	
					
				}
			}
			
		}
		str+='</div>';	

		$("#tdpCadreDetails").html(str);
		
		
		str='';
		str+='<h4  class="offset5" style="margin-bottom:0px"> CADRE ADDRESS DETAILS </h4>';
		str+='<div class="row" style="padding:10px">';
		str+='<div class="col-md-12" style="padding:10px">';
		str+='<div id="cadreAddressErrorDiv" class="mandatory" style="margin-left: 18px;"></div>';
		str+='<div class="mandatory" style="margin-left: 18px;"></div>';
       
		for(var m in searchArr)
			{
				if(searchArr[m].tdpCadreId == tdpCadreId)
				{
					str+='<div class="addressDetails">';
					str+='<div class="row">';
					str+='<div class="col-md-3 col-xs-6">';
					str+='<label class="control-label">House.No</label>';
					if(searchArr[m].addressVO.houseNo !=null){
						str+=' <input type="text"  class="houseNo form-control" value="'+searchArr[m].addressVO.houseNo+'"/>';
					}else{
						str+=' <input type="text"  class="houseNo form-control" />';
					}
					str+='</div>';
					str+='<div class="col-md-3 col-xs-6">';
					str+='<label class="control-label">Street</label>';
					if(searchArr[m].addressVO.street !=null){
						str+=' <input type="text"  class="street form-control" value="'+searchArr[m].addressVO.street+'"/>';
					}else{
						str+=' <input type="text"  class="street form-control" />';
					}
					str+='</div>';
					str+='<div class="col-md-3 col-xs-6">';
					str+='<label class="control-label">LandMark</label>';
					if(searchArr[m].addressVO.landMarkStr !=null){
						str+=' <input type="text"  class="landmark form-control" value="'+searchArr[m].addressVO.landMarkStr+'"/>';
					}else{
						str+=' <input type="text"  class="landmark form-control" />';
					}
					
					str+='</div>';
					str+='</div>';
					str+='<div class="row">';
					str+='<div class="col-md-3 col-xs-6">';
					str+='<label class="control-label" selected="selected">Select State</label>';
					str+='<select id="cadreState" class="cadreState form-control" onchange="getDistrictsForState(this.value);">';
					str+='<option value="0" selected="selected">Select State</option>';
					if(searchArr[m].addressVO.stateId ==1){
						str+='<option value="1" selected="selected">Andhra Pradesh</option>';
						str+='<option value="36" >Telangana</option>';
					}else if(searchArr[m].addressVO.stateId ==36){
						str+='<option value="1" >Andhra Pradesh</option>';
						str+='<option value="36" selected="selected">Telangana</option>';
					}
					else{
						str+='<option value="1" >Andhra Pradesh</option>';
						str+='<option value="36" >Telangana</option>';
					}
					
					str+='</select>';
					str+='</div>';
					//district
					str+='<div class="col-md-3 col-xs-6">';
					str+='<label class="control-label">Select District</label>';
					str+='<select id="cadreDistrict" class="cadreDistrict form-control" onchange="getConstituenciesForDistrict(this.value);">';
					str+='<option value="0" selected="selected">Select District</option>';
					
					if(searchArr[m].addressVO.districtList !=null){
						for(var n in searchArr[m].addressVO.districtList){
							if(searchArr[m].addressVO.districtId == searchArr[m].addressVO.districtList[n].id){
								str+='<option value="'+searchArr[m].addressVO.districtList[n].id+'" selected="selected">'+searchArr[m].addressVO.districtList[n].name+'</option>';
							}else{
								str+='<option value="'+searchArr[m].addressVO.districtList[n].id+'">'+searchArr[m].addressVO.districtList[n].name+'</option>';
							}
							
						}
					}
					
					str+='</select>';
					str+='</div>';
					//constituency
					str+='<div class="col-md-3 col-xs-6">';
					str+='<label class="control-label">Select Constituency</label>';
					str+='<select id="cadreConstituency" class="cadreConstituency form-control" onchange="getMandalsForConstituency(this.value);">';
					str+='<option value="0" selected="selected">Select Constituency</option>';
					if(searchArr[m].addressVO.addressTypeList !=null){
						for(var o in searchArr[m].addressVO.addressTypeList){
							if(searchArr[m].addressVO.addressTypeList[o].id==searchArr[m].addressVO.constituencyId){
								str+='<option value="'+searchArr[m].addressVO.addressTypeList[o].id+'" selected="selected">'+searchArr[m].addressVO.addressTypeList[o].name+'</option>';
							}else{
								str+='<option value="'+searchArr[m].addressVO.addressTypeList[o].id+'" >'+searchArr[m].addressVO.addressTypeList[o].name+'</option>';
							}
						}
						
					}
					
					str+='</select></div>';
					str+='</div>';
					str+='<div class="row">';		
					str+='<div class="col-md-3 col-xs-6">';
					str+='<label class="control-label">Select Mandal/Muncipality</label>';
					str+='<select id="cadreMandal" class="cadreMandal form-control" onchange="getPanchayatsForMandal(this.value);">';
					
					str+='<option value="0" selected="selected">Select Mandal/Muncipality</option>';
					if(searchArr[m].addressVO.tehsilList !=null){
						for(var p in searchArr[m].addressVO.tehsilList){
							
							/* var tehsilId=searchArr[m].addressVO.tehsilList[p].id.toString();
							var tehsilIdComp=tehsilId.substring(1,tehsilId.length); */
							
							if(searchArr[m].addressVO.tehsilList[p].id == searchArr[m].addressVO.tehsilId){
								str+='<option value="'+searchArr[m].addressVO.tehsilList[p].id+'" selected="selected">'+searchArr[m].addressVO.tehsilList[p].name+'</option>';
							}else if(searchArr[m].addressVO.tehsilList[p].id ==searchArr[m].addressVO.localElectionBodyId){
								str+='<option value="'+searchArr[m].addressVO.tehsilList[p].id+'" selected="selected">'+searchArr[m].addressVO.tehsilList[p].name+'</option>';
							}
							else{
								str+='<option value="'+searchArr[m].addressVO.tehsilList[p].id+'">'+searchArr[m].addressVO.tehsilList[p].name+'</option>';
							}
							
						}
					}
					str+='</select></div>';
					str+='<div class="col-md-3 col-xs-6">';
					str+='<label class="control-label">Select Village/Ward</label>';
					str+='<select id="cadreVillage" class="cadreVillage form-control">';
					str+='<option value="0" selected="selected">Select Village/Ward</option>';
					if(searchArr[m].addressVO.panchayatList !=null){
						for(var q in searchArr[m].addressVO.panchayatList){
							
							/* var panchayatId=searchArr[m].addressVO.panchayatList[q].id.toString();
							var panchaytIdComp=panchayatId.substring(1,panchayatId.length); */
							if(searchArr[m].addressVO.panchayatList[q].id == searchArr[m].addressVO.panchaytId){
								str+='<option value="'+searchArr[m].addressVO.panchayatList[q].id+'" selected="selected">'+searchArr[m].addressVO.panchayatList[q].name+'</option>';
							}
							else if(searchArr[m].addressVO.panchayatList[q].id == searchArr[m].addressVO.wardId){
								str+='<option value="'+searchArr[m].addressVO.panchayatList[q].id+'" selected="selected">'+searchArr[m].addressVO.panchayatList[q].name+'</option>';
							}
							else{
								str+='<option value="'+searchArr[m].addressVO.panchayatList[q].id+'">'+searchArr[m].addressVO.panchayatList[q].name+'</option>';
							}
						}
					}
					str+='</select></div>';
					str+='<div class="col-md-3 col-xs-6">';
					str+='<label class="control-label">Pincode</label>';
					if(searchArr[m].addressVO.pinCodeStr !=null){
						str+=' <input type="text"  class="pincode form-control" value="'+searchArr[m].addressVO.pinCodeStr+'"/>';
					}
					else{
						str+=' <input type="text"  class="pincode form-control" />';
					}
					str+='</div>';
					str+='</div>';		
					str+='</div>';
				}
			}
		str+='</div>';
		str+='</div>';		
		$("#cadreAddressDivId").html(str);
		
		str='';
		str+='<div class="panel-heading">';
        str+='<h4 class="media-heading">Cadre Family Details</h4>';
        str+='</div>';
        str+='<div class="panel-body">';
       

		str+='<div id="familyMembers">' ;
		var isDataAvail = false;
		for(var i=0;i<result.length;i++)
		{
			if(result[i].name != null)
			{
				isDataAvail = true;
					str+='<div class="panel panel-default familyInfo" id="familyInnfo'+i+'">';
					str+='<input type="hidden" value="cadreId"  style="display:none;" class="tdpCadre"  />';
					if(result[i].name != null)
					{
					
						str+='<div class="panel-heading">';
						str+='<h4 class=" media-heading" >'+result[i].name+'</h4> <input type="hidden" value="'+result[i].name+'" class="name"/> <div id="ErrorDiv'+i+'"  class="mandatory"></div>';
						
						str+='<i class="glyphicon glyphicon-minus-sign pull-right" style="border: 1px solid rgb(0, 0, 0); padding: 2px; width: 20px; height: 20px; font-size: 14px;margin-top:-22px;cursor:pointer" onclick="removeDetails(\'familyInnfo'+i+'\');" title="Click To Remove Family Member"></i>';
						str+='</div>';

					}
					str+='<div class="panel-body">';
					str+='<div class="row">';
					str+='<input type="hidden" value="'+result[i].voterId+'"  class="voterIdCls form-control"/> ';
					str+='<input type="hidden" value="'+result[i].votercardNo+'"  class="votercardNocls form-control"/> ';
					if(result[i].mobileNo != null)
					{
						
						str+='<div class="col-md-2 col-xs-6">';
						str+='<label class="control-label">Mobile No</label>';
						str+='<input type="text" value="'+result[i].mobileNo+'"  class="mobile form-control"  maxlength="10" key="ErrorDiv'+i+'"/> ';
						str+='</div>';
					}
					else
					{
						str+='<div class="col-md-2 col-xs-6">';
						str+='<label class="control-label">Mobile No</label>';
						str+='<input type="text"  class="mobile form-control"    maxlength="10"  key="ErrorDiv'+i+'"/> ';
						str+='</div>';
					
					}
					if(result[i].age != null)
					{
						
						str+='<div class="col-md-2 col-xs-6">';
						str+='<label class="control-label">Age</label>';
						str+='<input type="text" value="'+result[i].age+'"  class="age form-control"  maxlength="10" key="ErrorDiv'+i+'"/> ';
						str+='</div>';
					}
					else
					{
						str+='<div class="col-md-2 col-xs-6">';
						str+='<label class="control-label">Age</label>';
						str+='<input type="text"  class="age form-control"    maxlength="10"  key="ErrorDiv'+i+'"/> ';
						str+='</div>';
					
					}
						
					if(result[i].dob != null)
					{
								str+='<div class="col-md-2 col-xs-6">';
								str+='<label class="control-label">DOB</label>';
								str+=' <input type="text" value="'+(result[i].dob).substring(0,11)+'"  class="dob form-control" readonly="true" style="cursor:text;"/>';
								str+='</div>';						
					}
					else
					{
								str+='<div class="col-md-2 col-xs-6">';
								str+='<label class="control-label">DOB</label>';
								str+=' <input type="text"  class="dob form-control" readonly="true" style="cursor:text;"  style=""/>';
								str+='</div>';
					}
						
					if(result[i].email != null)
					{
						str+=' <div class="col-md-2 col-xs-6">';
						str+='<label class="control-label">Email</label>';
						str+='<input type="text" value="'+result[i].email+'"  class="email form-control"  />';
						str+='</div>';
					}
					else
					{
					
						str+=' <div class="col-md-2 col-xs-6">';
						str+='<label class="control-label">Email</label>';
						str+='<input type="text" class="email form-control"  />';
						str+='</div>';
					}
					
					if(relationsArr != null && relationsArr.length>0)
					{
					
						str+=' <div class="col-md-2 col-xs-6">';
						str+='<label class="control-label">Relation  <span class="mandatory"> * </span></label>';
						str+='<select id="" class="relation form-control" >';
						str+='<option value="0" selected="selected">Select Relation</option>';
						for(var k in relationsArr)
						{
							if(relationsArr[k].id != 13)
							{
								if(result[i].relationId != null && (relationsArr[k].id ==result[i].relationId ))
								{
									str+='<option value="'+relationsArr[k].id+'" selected="selected">'+relationsArr[k].name+'</option>';
								}
								else
								{
									str+='<option value="'+relationsArr[k].id+'" >'+relationsArr[k].name+'</option>';
								}
							}					
						}
						str+='</select></div>';
					}
					
					
						
					if(educationArr != null && educationArr.length>0)
					{
						str+=' <div class="col-md-2 col-xs-6">';
						str+='<label class="control-label">Education</label>';
						str+='<select id="" class="education form-control" >';
						str+='<option value="0" selected="selected">Select Education</option>';
						for(var k in educationArr)
						{
								if(result[i].educationId != null && (educationArr[k].id ==result[i].educationId ))
								{
									str+='<option value="'+educationArr[k].id+'" selected="selected">'+educationArr[k].name+'</option>';
								}
								else
								{
									str+='<option value="'+educationArr[k].id+'" >'+educationArr[k].name+'</option>';
								}
						}
						str+='</select></div>';
					}

					if(occupationArr != null && occupationArr.length>0)
					{
						str+=' <div class="col-md-2 col-xs-6">';
						str+='<label class="control-label">Occupation</label>';
						str+='<select id="" class="occupation form-control"  >';
						str+='<option value="0" selected="selected">Select Occupation</option>';
									
						for(var k in occupationArr)
						{
									
							if(occupationArr[k].id != null && (occupationArr[k].id ==result[i].occupationId ))
							{
								str+='<option value="'+occupationArr[k].id+'" selected="selected">'+occupationArr[k].name+'</option>';
							}
							else
							{
								str+='<option value="'+occupationArr[k].id+'" >'+occupationArr[k].name+'</option>';
							}
						}
						str+='</select></div>';
					}
					
					str+='</div></div></div>';
			}
		}
		str+='<div id="nextBuildBtnId">';
				str+='<button id="addNewMember" class="newMemberCls btn btn-info pull-right" onclick="addNewMemberInFamily('+result.length+')" title="Add a new member in family." style="margin-top: 15px;margin-right:15px;"> + </button>';
			str+='</div>';
		$("#familyDetalsDiv").html(str);

		
		
		$( ".marriageDay" ).datepicker({
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date(),
					yearRange: "-100:+0"
		});
		$( ".dob" ).datepicker({	
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date(),
					yearRange: "-100:+0"
		});
		$( ".membersince" ).datepicker({	
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					yearRange: "-100:+0"
		});	

		if(!isDataAvail)
		{
			addNewMemberInFamily(0);
		}
	}
	
function updateFamilyInfo()
{
		
	    var age = 0;
		var casteStateId= 0;
		var dob = "";
		var name="";
		var education=0;
		var email="";
		var gender="";
		var marriageDay="";
		var whatsappStatus="";
		var mobileNo="";
		var partyMemberSince = "";
		var voterId=0;
		var relationId = 0;
		var casteStateId = 0;
		var occupationId=0;
		var tdpCadre = 0;
	var dataArr = new Array();
	var finalCasteId = 0;
	var count = 0;
	var strErr = '';
	$('.mandatory').html('');
	var facebookUrl = "";
	var mycount  = 0;
	$("#cadreAddressErrorDiv").html("");
	
	$(".familyInfo").each(function(){
		
	strErr ="";
		var name = $(this).find(".name").val();
		var email = $(this).find(".email").val();
		var age = $(this).find(".age").val();
		//alert(age);
		var emailreg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var mobileNo = $(this).find(".mobile").val();
		var relation = $(this).find(".relation").val();
		var errStrDiv = $(this).find(".mobile").attr('key');
		if(name != null && name.trim().length == 0)
		{
			strErr+=" Name is required.";
			$('#'+errStrDiv+'').html(strErr);
			return;
		}	   
		if(relation != null && relation == 0)
		{
			strErr+=" Relations is required.";
			$('#'+errStrDiv+'').html(strErr);
			return;
		}
		if(email != null && email.length > 0 && emailreg.test(email) == false)
		{
			strErr+='Invalid Email Id.';
			$('#'+errStrDiv+'').html(strErr);
			return;
		}
		
		
		if($("#cadreMobile").val() == ""){
			strErr+='Cadre Mobile Number Is Reqiured ';
			$("#cadreErrorDiv").html(strErr);
			return;
		}
		else if ($("#cadreMobile").val() != null && $("#cadreMobile").val() != "" && $("#cadreMobile").val().length != 10 ) 
		{
			strErr+='Invalid Mobile No.';
			$("#cadreErrorDiv").html(strErr);
			return;			
		}
		else if (mobileNo !=null && mobileNo != "" && mobileNo.length != 10 ) 
		{
			strErr+='Invalid Mobile No.';
			$('#'+errStrDiv+'').html(strErr);
			return;			
		}
		if($("#cadreCaste").val() <= 0){
			strErr+='Caste Is Reqiured ';
			$("#cadreErrorDiv").html(strErr);
			return;
		}
		if($("#cadreState").val() <= 0){
			strErr+='State Is Reqiured ';
			$("#cadreAddressErrorDiv").html(strErr);
			return;
		}
		if($("#cadreDistrict").val() <= 0){
			strErr+='District Is Reqiured ';
			$("#cadreAddressErrorDiv").html(strErr);
			return;
		}
		if($("#cadreConstituency").val() <= 0){
			strErr+='Constituency Is Reqiured ';
			$("#cadreAddressErrorDiv").html(strErr);
			return;
		}
		if($("#cadreMandal").val() <= 0){
			strErr+='Mandal/Muncipality Is Reqiured ';
			$("#cadreAddressErrorDiv").html(strErr);
			return;
		}
		/*if($("#cadreVillage").val() <= 0){
			strErr+='Village/Ward Is Reqiured ';
			$("#cadreAddressErrorDiv").html(strErr);
			return;
		}*/
		
		
	});
	
	if(strErr != null && strErr.length == 0)
	{
		$(".familyInfo").each(function(){
			
		facebookUrl = "";
		if(mycount == 0){
			facebookUrl=$("#facebookUrl").val();;
		}
		mycount=parseInt(mycount)+1;	
	
		count = count+1;
		$this = $(this);
		if($(this).find(".age").val())
		var age = $(this).find(".age").val();
		
		var dob = $(this).find(".dob").val();
		var name = $(this).find(".name").val();
	    var education = $(this).find(".education").val();
		var email = $(this).find(".email").val();
		var gender = "";
		var marriageDay = "";
		var whatsAPP = "";
		var mobileNo = $(this).find(".mobile").val();
		var partyMemberSince = $(this).find(".membersince").val();
		var voterId = $(this).find(".voter").val();
		var relation = $(this).find(".relation").val();
	
	    var occupation = $(this).find(".occupation").val();
		
		var hNo=$(".houseNo").val();
		var street=$(".street").val();
		var landMark=$(".landmark").val();
		var stateId=$('.cadreState option:selected').val();
		var districtId=$('.cadreDistrict option:selected').val();
		var constituencyId=$('.cadreConstituency option:selected').val();
		
		var mandal=$('.cadreMandal option:selected').val();
		var mandalStr=mandal.toString();
		var mandalId=0;
		var localElectionBody=0;
		if(mandalStr.substring(0,1)==2){
			mandalId=mandalStr.substring(1,mandalStr.length);
		}else if(mandalStr.substring(0,1)==1){
			localElectionBody=mandalStr.substring(1,mandalStr.length);
		}
		
		var panchayat=$('.cadreVillage option:selected').val();
		var panchayatStr=panchayat.toString();
		var panchayatId=0;
		var wardId=0;
		if(panchayat != 0)
			if(panchayatStr.substring(0,1)==1)
				panchayatId=panchayatStr.substring(1,panchayatStr.length);
			else
				wardId=panchayatStr.substring(1,panchayatStr.length);
			
		
		//var panchayatId=$('.cadreVillage option:selected').val();
		var pincode=$('.pincode').val();
		
			if(count == 1)
			{
				age = $(this).find(".age").val();
				finalCasteId = casteStateId;
				casteStateId = $(this).find(".casteState").val();
				gender = $(this).find(".gender").val();
				marriageDay = $(this).find(".marriageDay").val();
				whatsAPP = $(this).find(".whatsappStatus").val();
				tdpCadre = $(this).find(".tdpCadre").val();
				dob = $(this).find(".dob").val();
				relation = 13;
			}
			else
			{
				age = $(this).find(".age").val();
				if(age == ""){
					age = 0;
				}
				relation = $(this).find(".relation").val();
				voterId = $(this).find(".votercardNocls").val();
				partyMemberSince="";
			}
				var obj = {
					age:age,
					casteStateId:casteStateId,
					dob:dob,
					name:name,
					education:education,
					email:email,
					facebookUrl:facebookUrl,
					gender:"",
					marriageDay:marriageDay,
					whatsappStatus:whatsAPP,
					mobileNo:mobileNo,
					partyMemberSince : partyMemberSince,
					voterId:voterId,
					relationId:relation,
					tdpCadreId:tdpCadre,
					occupationId:occupation,
					bloodGroup:0,
					//
					hNo:hNo,
					street:street,
					stateId:stateId,
					districtId:districtId,
					constituencyId:constituencyId,
					mandalId:mandalId,
					panchayatId:panchayatId,
					pincode:pincode,
					landMark:landMark,
					localElectionBody:localElectionBody,
					wardId:wardId
				};
				dataArr.push(obj);
			
		});
	}
	//console.log(dataArr);
	
	if(dataArr != null && dataArr.length>0)
	{
		$('#searchDataImg').show();	
		$('#updateeBtn').hide();
		$('#updateeBtn').hide();
		$('#updateTableDiv').hide();
		var jsObj = 
		{
			dataArr:dataArr,
			task:"updateFamilyInfo"
		};
		
		console.log(jsObj);
		//return;
		$.ajax({
			  type:'GET',
			  url: 'updateFamilyInfoAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)},
			  }).done(function(result){ 
				$('#searchDataImg').hide();	
				  if(result != null && result.resultCode ==0){
					  $("#familyDetalsDiv").html('');
						//getCadreDetails();
						refreshExistingDetails();
						$("#statesDivId").val($("#statesDivId option:first").val());
						$("#constituencyId").val($("#constituencyId option:first").val());
						$("#mandalList").val($("#mandalList option:first").val());
						$("#panchaytList").val($("#panchaytList option:first").val());
						checkResult("Family Details are updated successfully...");
						//$("#errorDiv").html("Family Details are updated successfully...").css("color","green");  
					}
					else
					{
						alert("Error occured while updating details.try again...").css("color","red");
					}
		   });		   
	}	
}

function checkResult(string)
 {  
          var cssObj = {    
				'font-weight' : 'bold',
				'color' : 'green'
			}
           $("#errorDiv").css("visibility","visible");
		   $('#errorDiv').text(string+"...").css(cssObj);
          
		  
		if(tdpCadreId != null && tdpCadreId.trim().length > 0 && parseInt(tdpCadreId)>0)
		{
			setTimeout('$("#errorDiv").css("visibility","hidden");window.close();',2500);
		}
		else
		{
			 setTimeout('$("#errorDiv").css("visibility","hidden");',2500);
		}
 }

function getAllRelationDetails(){
	 $.ajax({
		type : "POST",
		url : "getAllRelationDetails.action"
	}).done(function(result){
	  if(result != null && result.length > 0){	   
	   for(var i in result){
		 //$('#relativeTypeId').append('<option value='+result[i].id+'>'+result[i].id+'</option>');
		 var obj={
				id:result[i].id,
				name:result[i].name
			};
			relationsArr.push(obj);
	    }
	  }
	});
}

function addNewMemberInFamily(size)
{
	$("#nextBuildBtnId").remove();
	$("#addNewMember").remove();
	size = parseInt(size)+1;
	var str='';

			str+='<div class="panel panel-default familyInfo" id="familyInnfo'+size+'">';
			str+='<input type="hidden" value="cadreId"  style="display:none;" class="tdpCadre" />';
			
			str+='<div class="panel-heading">';
            str+=' Name : <span class="mandatory"> * </span>  <h4 class="media-heading"> <input type="text"  class="name form-control"  style="width: 250px"  placeholder="Please enter name " />    </h4> <div id="ErrorDiv'+size+'"  class="mandatory"></div>';
			str+='<i class="glyphicon glyphicon-minus-sign pull-right" style="border: 1px solid rgb(0, 0, 0); padding: 2px; width: 20px; height: 20px; font-size: 14px;margin-top:-33px;cursor:pointer" onclick="removeDetails(\'familyInnfo'+size+'\');" title="Click To Remove Family Member"></i>';
            str+='</div>';
			
			
			str+='<div class="panel-body">';
            str+='<div class="row">';
			str+='<div class="col-md-2 col-xs-6">';
			/*
			str+='<input type="hidden" value="'+result[i].voterId+'"  class="voterIdCls form-control"/> ';
					str+='<input type="hidden" value="'+result[i].votercardNo+'"  class="votercardNocls form-control"/> ';*/
			str+='<label class="control-label">Voter Card No</label>';
			str+='<input type="text"  class="votercardNocls form-control" key="ErrorDiv'+size+'"/> ';
			str+='</div>';
			str+='<div class="col-md-2 col-xs-6">';
			str+='<label class="control-label">Age:</label>';
			str+='<input type="text"  class="age form-control" maxlength="10" key="ErrorDiv'+size+'"/> ';
			str+='</div>';
			str+='<div class="col-md-2 col-xs-6">';
			str+='<label class="control-label">Mobile No</label>';
			str+='<input type="text"  class="mobile form-control" maxlength="10" key="ErrorDiv'+size+'"/> ';
			str+='</div>';
			str+=' <div class="col-md-2 col-xs-6">';
			str+='<label class="control-label">DOB</label>';
			str+='<input type="text" class="dob form-control form-control" /> ';
			str+='</div>';
			str+=' <div class="col-md-2 col-xs-6">';
			str+='<label class="control-label">Email</label>';
			str+='<input type="text" class="email form-control" /> ';
			str+='</div>';
			str+=' <div class="col-md-2 col-xs-6">';
			str+='<label class="control-label">Relation  <span class="mandatory"> * </span> </label>';
			
			if(relationsArr != null && relationsArr.length>0)
				{
					str+='<select id="" class="relation form-control">';
					str+='<option value="0" selected="selected">Select Relation</option>';
					for(var k in relationsArr)
					{
						if(relationsArr[k].id != 13)
						{
							str+='<option value="'+relationsArr[k].id+'" >'+relationsArr[k].name+'</option>';
						}
					}
					str+='</select>';
				}
			str+='</select></div>';
		
					
			

			str+=' <div class="col-md-2 col-xs-6">';
			str+='<label class="control-label">Education</label>';
			if(educationArr != null && educationArr.length>0)
			{
					str+='<select id="" class="education form-control">';
					str+='<option value="0" selected="selected">Select Education</option>';
					for(var k in educationArr)
					{
						str+='<option value="'+educationArr[k].id+'" >'+educationArr[k].name+'</option>';
					}
					str+='</select>';
			}
			str+='</select></div>';
			
	
			str+=' <div class="col-md-2 col-xs-6">';
			str+='<label class="control-label">Occupation</label>';
			if(occupationArr != null && occupationArr.length>0)
			{
					str+='<select id="" class="occupation form-control" >';
					str+='<option value="0" selected="selected">Select Occupation</option>';
					for(var k in occupationArr)
					{
						str+='<option value="'+occupationArr[k].id+'" >'+occupationArr[k].name+'</option>';
					}
					str+='</select>';
			}

			str+='</div></div></div></div>';
			str+='<button id="addNewMember" class="newMemberCls btn btn-info pull-right" onclick="addNewMemberInFamily('+size+')" title="Add a new member in family." style="margin-top: 15px;margin-right:15px;"> + </button>';
		$('#familyMembers').append(str);
		
			$( ".marriageDay" ).datepicker({
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date(),
					yearRange: "-100:+0"
		});
		$( ".dob" ).datepicker({	
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date(),
					yearRange: "-100:+0"
		});
		$( ".membersince" ).datepicker({	
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					yearRange: "-100:+0"
		});

		
		
}

function removeDetails(divId)
{
	var x=window.confirm("Are you sure want to remove it?")
	if (x)
	{
		$('#'+divId+'').remove();
	}
}

function getDistrictsForState(state){
	
	$("#cadreConstituency").find('option').remove();
	$("#cadreMandal").find('option').remove();
	$("#cadreVillage").find('option').remove();
	$("#cadreConstituency").append('<option value="0">Select Constituency</option>');
	$("#cadreMandal").append('<option value="0">Select Mandal/Muncipality</option>');
	$("#cadreVillage").append('<option value="0">Select Village/Ward</option>');
   
	   var jsObj=
	   {				
					stateId:state,
					elmtId:"districtList_d",
					type:"default",
					task:"getDistrictsForState"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getDistrictsForStateAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
			   location.reload(); 
		   }
		   $("#cadreDistrict").empty();
		 for(var i in result){
		   if(result[i].id == 0){
			  $("#cadreDistrict").append('<option value='+result[i].id+'>All</option>');
		   }else{
			  $("#cadreDistrict").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		 }
	   });
  }
  
function getConstituenciesForDistrict(district){
	
	$("#cadreMandal").find('option').remove();
	$("#cadreVillage").find('option').remove();
	$("#cadreMandal").append('<option value="0">Select Mandal/Muncipality</option>');
	$("#cadreVillage").append('<option value="0">Select Village/Ward</option>');
	
		var jsObj=
	   {				
					districtId:district,
					elmtId:"districtList_d",
					type:"default",
					task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesForADistrictAjaxAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
			   location.reload(); 
		   }
		   $("#cadreConstituency").empty();
		 for(var i in result){
		   if(result[i].id == 0){
			  $("#cadreConstituency").append('<option value='+result[i].id+'>All</option>');
		   }else{
			  $("#cadreConstituency").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		 }
	   });
  }
  
    function getMandalsForConstituency(constiId){
		
		$("#cadreVillage").find('option').remove();
		$("#cadreVillage").append('<option value="0">Select Village/Ward</option>');
      $("#cadreMandal option").remove();
	  if(constiId == 0){
	    return;
	  }
      var jsObj=
		{
			id:constiId,
			task:"subRegionsInConstituency",
			taskType:"",
			selectElementId:"",
			address:"",
			areaType:"null",
			constId:constiId				
		}
    $.ajax({
          type:'GET',
          url: 'locationsHierarchiesAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
     for(var i in result){
	   if(result[i].id == 0){
          $("#cadreMandal").append('<option value='+result[i].id+'>All</option>');
	   }else{
	      $("#cadreMandal").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });	
  }
  
      function getPanchayatsForMandal(mandalId){
		
		$("#cadreVillage").find('option').remove();
		  if(mandalId == 0)
		  {
			return;
		  }
		var jsObj={
			mandalId :mandalId
		}
		$.ajax({
			type:"POST",
			url :"getPanchayatDetailsByMandalIdAddingParamAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
			}
		for(var i in result){
	   if(result[i].id == 0){
          $("#cadreVillage").append('<option value='+result[i].id+'>All</option>');
	   }else{
	      $("#cadreVillage").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });	
  }
  //cadreWithFamilyDetailsByCadreId
  //getCadreWithFamilyDetailsOfEachCadre();
  function getCadreWithFamilyDetailsOfEachCadre(cadreId){
	    $('#searchDataImg').show();
	    $("#tdpCadreDetails").html("");
		$("#cadreAddressDivId").html("");
		$("#familyDetalsDiv").html("");
		$("#tdpCadreDetails").hide();
		$("#cadreAddressDivId").hide();
		$("#familyDetalsDiv").hide();
		//$("#btnDiv").hide();
	  
	  searchArr=[];
	  var cadreId=cadreId;
	  var jsObj={
		  cadreId:cadreId
	  }
	  $.ajax({
			type:"POST",
			url :"getCadreWithFamilyDetailsOfEachCadreAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
	  }).done(function(result){
		  if(result !=null){
			  for(var i in result){
				  
				  var cadreObj={
						name:result[i].nameType,
						mobileNo:result[i].mobileNumber,
						gender:result[i].gender,
						age:result[i].age,
						votercardNo :result[i].voterCardNo,
						relationId:null,
						faceboohUrl:result[i].faceboohUrl,
						dob: result[i].dobStr != null ? (result[i].dobStr).substring(0,11):"",
						marriageDay:result[i].marriageDateStr != null ? (result[i].marriageDateStr).substring(0,11):"",
						email:result[i].email,
						casteStateId:result[i].casteId ,
						educationId:result[i].educationId,
						occupationId:result[i].occupationId,
						whatsappStatus :result[i].whatsAppStatus,
						partyMemberSince:result[i].partyMemberSinceStr != null ? (result[i].partyMemberSinceStr).substring(0,11):"", 
						tdpCadreId:result[i].cadreId,
						bloodGroupId : 0,
						//userAddress Data
						addressVO:result[i].addressVO
						
					};
					
					searchArr.push(cadreObj);
			  }	 
		 }
		 getFamilyDetails(cadreId);
	  });
	  
  }
</script>


<script>

var accessType = "${sessionScope.USER.accessType}";
var accessValue = "${sessionScope.USER.accessValue}";
var accessState = "${sessionScope.USER.stateName}";


function getAssemblyParlConstituencies(districtId,type)
	{
	 $("#constituencyId  option").remove();
		var str='';
		var jsObj={
			mainUserLocationId:districtId,
			reportLevel:type
		}
		$.ajax({
			  type:'GET',
			  url: 'getSubUserAccessValueAction.action',
			  data: {task:JSON.stringify(jsObj)}
			
	   }).done(function(result){
	   str +='<option value=0>ALL</option>';
			for(var i in result)
			{
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			
			if(type=="Assembly"){
				$("#constituencyId").html(str);
				
			}		
	   });		
	}
	
function getConstituenciesForStateAjax()
	{
		var stateId = $("#statesDivId").val();
		getConstituenciesForState(stateId);
	}
	
function getConstituenciesForState(state)
	{
	   var jsObj=
	   {				
			stateId:state,
			elmtId:"stateList",
			type:"default",
			task:"getConstituenciesForState"				
		}
		$.ajax({
		  type:'GET',
		  url: 'getConstituenciesForStateAjaxAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
	   
		   $("#constituencyId").empty();
		   
		 for(var i in result){
		   if(result[i].id == 0){
			 $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
		   }else{
			 $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		}
	});
  }
  
function getAssemblyConstituencies(constiId)
	{
	var str;
	var jsObj = 
	   {
		  parliamentConstiId : constiId,
		  electionYear:2005,
		  task:"getAssemblyDetailsForParliamnt"             
	   }	
		$.ajax({
			type : "POST",
			url : "getAssemblyDetailsForParliamntAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			 $("#constituencyId").empty();
			 var result1 = result.selectOptionsList;
			 if(result1 != null && result1.length > 0)
				{
				  $("#constituencyId").append('<option value=0>ALL</option>');
					 for(var i in result1){
					 $("#constituencyId").append('<option value='+result1[i].id+'>'+result1[i].name+'</option>');
					   
					 }
				}
	  });
	}

function getMandalCorporationsByConstituency()
	{		
			var constituencyId = $('#constituencyId').val();
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			
				var jsObj ={					
					constituencyId:constituencyId
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){

				if(result !=null)
				{
					for(var i in result)
					{
						$("#mandalList").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
					}	
				}				
				});
	}
	
	 function getPanchayatWardByMandalSearch(){
		     
			var mandalId=$("#mandalList").val();
			
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			
			var jsObj={
				mandalId:mandalId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				
			for(var i in result){
				$("#panchaytList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			}
				
		});	
			
	} 
function refreshExistingDetails()
{ 
	$("#searchBy").val("");
	$("#cadreDetailsDiv").html("");
	$(".paginationDivId").html('');
	$("#tdpCadreDetails").html("");
	$("#cadreAddressDivId").html("");
	$("#familyDetalsDiv").html("");
	$("#cadreDetailsDiv").hide();
	$("#tdpCadreDetails").hide();
	$("#cadreAddressDivId").hide();
	$("#familyDetalsDiv").hide();
	$("#btnDiv").hide();
}

$('.searchTypeCls').click(function(){
			
		
			
			var id = $(this).attr('id');
				
			$('#advancedSearchDiv').hide();			
			$('#basicSearchDiv').show();
			$('#committeLocationsDiv').hide();
		
			if(id.trim() == 'membershipId')
			{
				
				
				$('#cadreSearchType').val('membershipId');
			}
			if(id.trim() == 'voterId')
			{
			
				$('#cadreSearchType').val('voterId');
			}
			if(id.trim() == 'mobileNo')
			{
				
				
				$('#cadreSearchType').val('mobileNo');
			}
			
			if(id.trim() == 'name')
			{
			
				$('#cadreSearchType').val('name');
			}
			if(id.trim() == 'trNo')
			{	
				
				$('#cadreSearchType').val('trNo');
				
			}
		});

	function getCadreDetailsBySearchCriteria(startIndex)
		{
		//committeTypeID means 
			//for committiee management 1
			//for Mandal/Muncipality Main Committee Electoral Management 2
			//for Mandal/Muncipality Affiliated Committee Electoral Management 3
		//areaTypeId means
		    //for panchayat level 1
			//for mandal level 2
			
		
		var locationLevel = 0;
		var locationValue = 0;
		var searchName = '';
		var mobileNo = '';
		var casteCategory = '';
		var casteStateId = 0;
		var fromAge = 0;
		var toAge = 0;
		var memberShipCardNo = '';
		var trNumber = '';
		var voterCardNo = '';
		var gender = '';
		var houseNo = '';
		
		
$('#cadreDetailsDiv,#searchErrDiv,#committeeLocationIdErr,#committeLocationIdErr,#advancedSearchErrDiv').html('');
if(startIndex == 0)
			$(".paginationDivId").html('');
		
$(".paginationDivId").hide();
$("#cadreDetailsDiv").html("");
//$("#errorDiv").html("");
//$("#errorDiv").hide();
//$(".paginationDivId").html('');
$("#tdpCadreDetails").html("");
$("#cadreAddressDivId").html("");
$("#familyDetalsDiv").html("");
$("#cadreDetailsDiv").hide();
$("#tdpCadreDetails").hide();
$("#cadreAddressDivId").hide();
$("#familyDetalsDiv").hide();
//$("#btnDiv").hide();

//$("#paginationDivId").hide();
		$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
		$("#cadreDetailsDiv").hide();
		var searchBy = $('#searchBy').val().trim();
		var searchRadioType =$('#cadreSearchType').val();;
		var parentLocation = 0;
		var panchayatId = $("#panchaytList").val();
		var mandalId = $("#mandalList").val();
		var constituencyId = $("#constituencyId").val();
		
		if(panchayatId !=0)
		{
			if(panchayatId.substr(0,1) == 1){
				  locationLevel = 6;
			}
			else if(panchayatId.substr(0,1) == 2){
				 locationLevel = 8;
				 
			}								
			locationValue = panchayatId.substr(1);
		}
		else if(mandalId !=0)
		{
			if(mandalId.substr(0,1) == 1){
				 locationLevel = 7;
			}
			else if(mandalId.substr(0,1) == 2){
				 locationLevel = 5;
			}
			else if(mandalId.substr(0,1) == 3){
				 locationLevel = 8;
			}
			locationValue = mandalId.substr(1);
		}
		else if(constituencyId == 0)
		{
			if(accessType == "DISTRICT")
			{
				locationLevel = 3;
				locationValue = accessValue;
			}
			if(accessType == "MP")
			{
				locationLevel = 10;
				locationValue = accessValue;
			}
			if(accessType == "STATE")
			{
				var stateId = $("#statesDivId").val();
				locationLevel = 2;
				if($("#statesDivId").val() == 0)
				{
				stateId = 3;
				}
				locationValue = stateId;
			}	
		}
		else
		{
			locationValue = constituencyId;
			locationLevel = 4;	
		}
		
		if(searchRadioType == 'membershipId')
		{
			memberShipCardNo = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Membership Card No.');
				return;
			}
		}			
		if(searchRadioType == 'voterId')
		{
			voterCardNo = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Voter Card No.');
				return;
			}
		}
		if(searchRadioType == 'mobileNo')
		{	
			mobileNo = $('#searchBy').val().trim();
			
			if(searchRadioType=="mobileNo"){
					
					var numericExpression = /^[0-9]+$/;
					if(!$('#searchBy').val().match(numericExpression)){
						$('#searchErrDiv').html('Enter Numerics Only.');
						return;
					}
			}	
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Mobile No.');
				return;
			}
			
			else if(mobileNo.trim().length != 10)
			{
				$('#searchErrDiv').html('Invalid Mobile No.');
				return;				
			}
			
			
			
		}
		if(searchRadioType == 'name')
		{
			searchName = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Name.');
				return;
			}
			else if(searchBy.trim().length < 3)
			{
				$('#searchErrDiv').html('Please enter Minimum 3 Characters.');
				return;
			}
		}
		if(searchRadioType == 'trNo')
		{
			trNumber = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter trNo.');
				return;
			}
			
		}
		
		$("#searchDataImg").show();
		var jsObj =
		{
			locationLevel :locationLevel,
			locationValue:locationValue,
			searchName : searchName,
			mobileNo: mobileNo,
			casteCategory : casteCategory,
			fromAge : fromAge,
			toAge : toAge,
			memberShipCardNo: memberShipCardNo,
			casteStateId : casteStateId,
			trNumber : trNumber,
			voterCardNo:voterCardNo,
			gender:gender,
			houseNo:houseNo,
			removedStatus:"false",
			startIndex:startIndex,
			maxIndex : 50,
			enrollmentId : 3,
			task:"tdpCadreSearch"
		}
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				 if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				$("#searchDataImg").hide();
				$('#cadreDetailsDiv').show();
				//alert(result);
				if(result != null && result.previousRoles != null && result.previousRoles.length>0)
				{
					$("#errorDiv").html("");
					//$("#errorDiv").hide();
				buildCadreDetails(result.previousRoles,jsObj);
				}
				else
				{
					
					$('#cadreDetailsDiv').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
				}
			});  

	}
	
	function buildCadreDetails(result,jsObj)
	{
		$(".paginationDivId").show();
		//$("#paginationDivId").show();
		var str ="";
		
		str+='<div class="span12">';
			
		str+=' <table class="table table-bordered" style="margin-top: 20px;"  id="tableId">';
		str+=' <thead>';
		str+=' <tr>';
		str+=' <th class="alert-info"> Cadre Name  </th>';
		str+=' <th class="alert-info"> Image  </th>';
		str+=' <th class="alert-info" > Membership Number  </th>';
		str+=' <th class="alert-info" > Mobile Number </th>';
		//str+=' <th class="alert-info" > Constituency </th>';
		str+=' </tr>';
		str+=' </thead>';
		str+=' <tbody>';
		
		for(var i in result)
		{
			str+=' <tr>';
			
			str+=' <td><a href="javascript:{};" style="cursor:pointer;" onclick="getCadreWithFamilyDetailsOfEachCadre(\''+result[i].tdpCadreId+'\');">'+result[i].cadreName+'</a></td>';
			str+='<td><img src="images/cadre_images/'+result[i].imageURL+'"  style="height:50px;width:50px;"/></td>';
			str+=' <td>'+result[i].memberShipCardId+'</td>';
			str+=' <td>'+result[i].mobileNo+'</td>';
			//str+=' <td>'+result[i].constituencyId+'</td>';
			
			str+=' </tr>';
			
			
		}
		str+='</tbody></table></div>';
		$("#cadreDetailsDiv").html(str);
		//$("#tableId").dataTable();
	}

</script>

</body>
</html>
