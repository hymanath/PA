<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Committee Dashboard</title>
	<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/css/style.css" rel="stylesheet"/>
    	<!--Bootstrap DatePicker-->
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 

<style>
	.widget
	{
		background-color:rgba(0,0,0,0.1);
		text-align:center;
		padding:10px 0px 10px 0px;
		margin-right:20px;
		border:1px solid #999;
		box-shadow:1px 1px 5px #999;
	}
	.borderbox
	{
		margin-top:10px;
		border:5px solid #CCC;
		border-radius:5px;
	}
	.tablecaption
	{
		background-color:#FC6;
		padding-left:5px;
		font-size:16px;
		font-weight:bold;
		color:#FFF;
	}
</style>
</head>

<body>
 <header style="align:center;background-color:#ef4036; display:flex;border-bottom:4px solid #13a751;">
		 	<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3 text-center">
				<img src="images/cadreCommitee/Committees_2014_logo.png" class="m_top10" title="Committee Logo" alt="committee" />
			</div>
			<div class="col-md-3  col-xs-3 col-sm-3">
              <div class="" style="color:white;margin-top: 20px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top20" data-toggle="dropdown" aria-expanded="false" style="margin-top: 20px;">
                    Menu <img src="images/cadreCommitee/menu_icon.png" />
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);top: 91px;">
                    <c:if test="${sessionScope.USER.isAdmin == 'true'}">
						<li><a tabindex="-1" href="dashBoardAction.action">Home</a></li>
					</c:if>
					<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN' )}">
						<li><a tabindex="-1" href="committeeDashBoardAction.action">Home</a></li>
					
				  	  <li><a tabindex="-1" href="committeeUpdateApproveAction.action">Approval Requests</a></li>
				  	  <li><a tabindex="-1" href="constituencyCommitteeSummaryAction.action">Advanced DashBoard</a></li>
                      <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
					</c:if>
                     <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
                    </ul>
                 
            </div>
	</header>
<div class="container">
	<div class="col-md-10 col-md-offset-1">
		<h3 style="text-align:center">CADRE COMMITTEE CASTE vs GENDER WISE POSITIONS DASHBOARD </h3>
		<hr style="margin-top:5px;border-color:#FC6"/>
	</div>
	<div class="col-md-3 col-md-offset-8" style="margin-top:10px;" id="buttonsDiv">
        <button class="btn btn-xs btn-success  ALLCls" onclick="getRolesBasedReport('ALL',0,0,'constiRoleSummary',0,0);"><label class="radio-inline">
			<input type="radio" name="optradio" id="ALL" value="ALL" >ALL</label>
		</button>
		<button class="btn btn-xs btn-danger  APCls" onclick="getRolesBasedReport('AP',0,0,'constiRoleSummary',0,0);"><label class="radio-inline">
			<input type="radio" name="optradio" id="AP"  value="AP" >AP</label>
		</button>
		<button class="btn btn-xs btn-danger  TSCls" onclick="getRolesBasedReport('TS',0,0,'constiRoleSummary',0,0);">
		<label class="radio-inline">
			<input type="radio" name="optradio" id="TS"  value="TS" >TS</label>
		</button>

    </div>
	<div class="col-md-8 col-md-offset-2" style="padding:30px 10px 30px 40px;background-color:rgba(0,0,0,0.1);margin-top:10px;box-shadow:1px 1px 5px #999;color:#686868">
          <div class="col-md-6 col-xs-10">
			<label>Committee Type</label>
			<select class="form-control" style="width:220px" id="committeeTypeId">
				<option value="0"> All </option>
				<option value="1">Main Committee</option>
				<option value="2">Affiliated Committee</option>
			</select>
		  </div>
		  <div class="col-md-6 col-xs-10">
			<label>Committee Level</label>
			<select class="form-control" style="width:220px" id="committeeLevelTypeId">
				<!--<option value="0">Select Committee Level</option>-->
				<option value="1">Village/Ward</option>
				<option value="2">Mandal/Town/Division</option>
			</select>
		  </div>
		  <div class="col-md-6 col-xs-10">
				<label>Position</label>
				<select class="form-control" style="width:220px" id="committeePostitionId" >
					<option value="0">All </option>
					<option value="1">President </option>
					<option value="2">Vice-President </option>
					<option value="3">General Secretary </option>
					<option value="4">Organizing Secretary </option>
					<option value="5">Secretary </option>
					<option value="6">Official Spokesman </option>
					<option value="7">Campaign Secretary </option>
					<option value="8">Treasurer </option>
					<option value="9">Executive Member </option>					
				</select>
		  </div>
		  <div class="col-md-6 col-xs-10">
				<label>Location Level</label>
				<select class="form-control" style="width:220px"  id="searchLevelId">
					<option value="1">State</option>
					<option value="2">District</option>
					<!--<option value="4">Parliament</option>-->
					<option value="3">Assembly</option>
				</select>
		  </div>
		  <div class="col-md-3 pull-right">
			<button type="submit" class="btn btn-default btn-success" style="margin-top:10px" onclick="getRolesBasedReport(0,0,0,'constiRoleSummary',0,0);">Get Details</button>
		  </div>
		
    </div>
	
	<div style="" class="col-md-3 col-md-offset-5">
		<img id="ajaxImageIdAPmandalconstiRoleSummary" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;">
	</div>
		<div id="constiRoleSummary" class="reportsCls"></div>
	<div style="" class="col-md-3 col-md-offset-5">
		<img id="ajaxImageIdAPmandalsecondDivId" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;">
	</div>
		<div id="secondDivId"  class="reportsCls"></div>
	<div style="" class="col-md-3 col-md-offset-5">
		<img id="ajaxImageIdAPmandalthirdDivId" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;">
	</div>
		<div id="thirdDivId"  class="reportsCls"></div>
	<div style="" class="col-md-3 col-md-offset-5">
		<img id="ajaxImageIdAPmandalfourthDivId" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;">
	</div>
		<div id="fourthDivId"  class="reportsCls"></div>
	<div style="" class="col-md-3 col-md-offset-5">
		<img id="ajaxImageIdAPmandalfifthDivId" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;">
	</div>
		<div id="fifthDivId"  class="reportsCls"></div>
		<div id="sixDivId"  class="reportsCls"></div>
		<div id="seventhDivId"  class="reportsCls"></div>	   
    </div>
 
</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
    <script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
    <!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>   
    <!--Jquery Sparkline-->
    <script src="js/cadreCommittee/js/jquery.sparkline.js" type="text/javascript"></script>
    <!-- Custom JS File-->
    <script src="js/cadreCommittee/js/custom.js" type="text/javascript"></script>
    <!--Hover Menu-->
    <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
	 <script src="js/jquery.classyloader.min.js"></script>
    <script type="text/javascript" src="js/jquery.dataTables.js"></script>

	
<script>

var userInitialAccessType = '${pageAccessType}';
//console.log(userInitialAccessType);
	$(document).ready(function(){		
			$('#ALL').prop("checked",true);
			if(userInitialAccessType != 'ALL')
			{
				$('#buttonsDiv').hide();
			}
	});
	
	function buildRoleWiseDetailsInfo(result,divId,accessType,locationName)
	{
		var locatinLevelId =$('#searchLevelId').val();
		var totalResult = result.cadreCommitteeRolesInfoVOList;
			var str='';
			str+='<div class="col-md-10 col-md-offset-1">';
			if(locationName == 0)
				str+='<h3 style="text-align:center">'+$("#searchLevelId option:selected").text()+' Wise '+$("#committeePostitionId option:selected").text()+' Position(s) Overview </h3>';
			else
				str+='		<h3 style="text-align:center"> '+locationName+' '+$("#committeePostitionId option:selected").text()+' Position(s) Overview </h3>';
			str+='		<hr style="margin-top:5px;border-color:#FC6">';
			str+='	</div>';
		if(totalResult != null && totalResult.length>0)
		{
			for(var i in totalResult)
			{
				str+='<div class="col-md-10 col-md-offset-1 widget" style="margin-top:10px;font-size: 16px;">';
				str+='<div class="col-md-4" >';
				str+='<div class="col-md-5" style="padding:5px;">Total Candidates</div>';
				str+='<div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block">'+totalResult[i].totalCount+'</div>';
				str+='</div>';
				str+='<div class="col-md-4" style="border-left:1px solid #FFF" >';
				str+='<div class="col-md-5" style="padding:0px;">';
				str+='<i class="icon-male"></i>';
				str+='Male Candidates</div>';
				str+='<div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block">'+totalResult[i].maleCount+'</div>';
				str+='</div>';
				str+='<div class="col-md-4" style="border-left:1px solid #FFF" >';
				str+='<div class="col-md-5" style="padding:0px;">';
				str+='<i class="icon-female"></i>';
				str+='Female Candidates</div>';
				var femaleCount = totalResult[i].femaleCount != null?totalResult[i].femaleCount:0;
				str+='<div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block">'+femaleCount+'</div>';
				str+='</div>';
				str+='</div>';
			}			
		}
		
			str+='<div class="col-md-10 col-md-offset-1" style="margin-top:20px;">';
			str+='<div class="table-responsive">';
		var casteCategoryResult = result.casteCategoryWiseList;
		if(casteCategoryResult != null && casteCategoryResult.length>0)
		{
			
				str+='<table class="table table-bordered" style="border:2px solid #FC6 !important;margin-bottom:35px" id="casteCategoryId'+divId+'">';
				str+='<caption class="tablecaption" >Caste Category Wise '+$("#committeePostitionId option:selected").text()+' Position(s) Information';
				str+='<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
				str+='</caption>';
				str+='<thead>';
				str+='<tr>';
				str+='<th width="25%">Caste Category</th>';
				str+='<th width="25%">Total Count</th>';
				str+='<th width="25%">Male</th>';
				str+='<th width="25%">Female</th>';
				str+='</tr>';
				str+='</thead>';
				for(var i in casteCategoryResult)
				{
					str+='<tr>';
					str+='<td>'+casteCategoryResult[i].casteCategory+'</td>';
					str+='<td>'+casteCategoryResult[i].totalCount+'</td>';
					str+='<td>'+casteCategoryResult[i].maleCount+'</td>';
					str+='<td>'+casteCategoryResult[i].femaleCount+'</td>';
					str+='</tr>';
				}
				str+='</table>';			
		}
			
		var casteWiseResult = result.casteWiseList;
		if(casteWiseResult != null  && casteWiseResult.length>0)
		{
			str+='<table class="table table-bordered" style="border:2px solid #FC6 !important" id="casteDetailsId'+divId+'">';
			str+='<caption class="tablecaption" >Caste Wise '+$("#committeePostitionId option:selected").text()+' Position(s) Information';
			str+='<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
			str+='</caption>';
			str+='<thead>';
			str+='<tr>';
			str+='<th width="25%">Caste Name</th>';
			str+='<th width="25%">Total Count</th>';
			str+='<th width="25%">Male</th>';
			str+='<th width="25%">Female</th>';
			str+='</tr>';
			str+='</thead>';
			for(var i in casteWiseResult)
			{
				str+='<tr>';
				str+='<td>'+casteWiseResult[i].caste+'</td>';
					str+='<td>'+casteWiseResult[i].totalCount+'</td>';
					str+='<td>'+casteWiseResult[i].maleCount+'</td>';
					str+='<td>'+casteWiseResult[i].femaleCount+'</td>';
				str+='</tr>';
			}
			str+='</table>    ';
		}	
		
		var ageRangeWiseResult = result.ageRangeWiseList;
		if(ageRangeWiseResult != null  && ageRangeWiseResult.length>0)
		{
			str+='<table class="table table-bordered" style="border:2px solid #FC6 !important" id="ageRangeID'+divId+'">';
			str+='<caption class="tablecaption">Age Range Wise '+$("#committeePostitionId option:selected").text()+' Position(s) Information';
			str+='<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>                </caption>';
			str+='<thead>';
			str+='<tr>';
			str+='<th width="25%">Between Age</th>';
			str+='<th width="25%">Total Count</th>';
			str+='<th width="25%">Male</th>';
			str+='<th width="25%">Female</th>';
			str+='</tr>';
			str+='</thead>';
			for(var i in ageRangeWiseResult)
			{
				str+='<tr>';
				str+='<td>'+ageRangeWiseResult[i].ageRange+'</td>';
				str+='<td>'+ageRangeWiseResult[i].totalCount+'</td>';
				str+='<td>'+ageRangeWiseResult[i].maleCount+'</td>';
				str+='<td>'+ageRangeWiseResult[i].femaleCount+'</td>';
				str+='</tr>';
			}
			str+='</table> ';
		}
		
		if(result.locationName != null)
		{
				var locationOverview = result.locationWiseList;
				if(locationOverview != null  && locationOverview.length>0)
				{
					str+='<table class="table table-bordered" style="border:2px solid #FC6 !important" id="locationOverveiewDiv'+divId+'">';
					str+='<caption class="tablecaption"> '+result.locationName+' Wise '+$("#committeePostitionId option:selected").text()+' Position(s) Committee Information';
					str+='<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>                </caption>';
					str+='<thead>';
					str+='<tr>';
					str+='<th width="25%">'+result.locationName+' Name</th>';
					str+='<th width="25%">Total Count</th>';
					str+='<th width="25%">Male</th>';
					str+='<th width="25%">Female</th>';
					str+='</tr>';
					str+='</thead>';
					
					var nextDivId = 'secondDivId';
					if(divId == 'constiRoleSummary')
					{
						nextDivId = 'secondDivId';
					}
					else if(divId == 'secondDivId')
					{
						nextDivId = 'thirdDivId';
					}
					else if(divId == 'thirdDivId')
					{
						nextDivId = 'fourthDivId';
					}
					else if(divId == 'fourthDivId')
					{
						nextDivId = 'fifthDivId';
					}
					else if(divId == 'fifthDivId')
					{
						nextDivId = 'sixDivId';
					}
					
					
					for(var i in locationOverview)
					{
						str+='<tr>';
						str+='<td><a href="javascript:{getRolesBasedReport(\''+result.locationName+'\','+result.locationId+','+locationOverview[i].locationId+',\''+nextDivId+'\',\''+locationOverview[i].locationName+'  '+result.locationName+'\',\'dontClearDivs\')}">'+locationOverview[i].locationName+'</a></td>';
						str+='<td>'+locationOverview[i].totalCount+'</td>';
						str+='<td>'+locationOverview[i].maleCount+'</td>';
						str+='<td>'+locationOverview[i].femaleCount+'</td>';
						str+='</tr>';
					}
					str+='</table> ';
				}
		}
		else{
			
			var locationOverview = result.locationWiseList;
				if(locationOverview != null  && locationOverview.length>0)
				{
					str+='<table class="table table-bordered" style="border:2px solid #FC6 !important" id="locationOverveiewDiv'+divId+'">';
					str+='<caption class="tablecaption"> '+result.locationName+' Wise  Committee Information';
					str+='<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>                </caption>';
					str+='<thead>';
					str+='<tr>';
					str+='<th width="25%">'+result.locationName+' Name</th>';
					str+='<th width="25%">Total Count</th>';
					str+='<th width="25%">Male</th>';
					str+='<th width="25%">Female</th>';
					str+='</tr>';
					str+='</thead>';
					
					var nextDivId = 'secondDivId';
					if(divId == 'constiRoleSummary')
					{
						nextDivId = 'secondDivId';
					}
					else if(divId == 'secondDivId')
					{
						nextDivId = 'thirdDivId';
					}
					else if(divId == 'thirdDivId')
					{
						nextDivId = 'fourthDivId';
					}
					else if(divId == 'fourthDivId')
					{
						nextDivId = 'fifthDivId';
					}
					else if(divId == 'fifthDivId')
					{
						nextDivId = 'sixDivId';
					}
					
					
					for(var i in locationOverview)
					{
						str+='<tr>';
						str+='<td><a href="javascript:{getRolesBasedReport(\''+result.locationName+'\','+result.locationId+','+locationOverview[i].locationId+',\''+nextDivId+'\',\''+locationOverview[i].locationName+'  '+result.locationName+'\',\'dontClearDivs\')}">'+locationOverview[i].locationName+'</a></td>';
						str+='<td>'+locationOverview[i].totalCount+'</td>';
						str+='<td>'+locationOverview[i].maleCount+'</td>';
						str+='<td>'+locationOverview[i].femaleCount+'</td>';
						str+='</tr>';
					}
					str+='</table> ';
				}
		}
			str+='</div>  '; 
			str+='</div>';

		$('#'+divId+'').html(str);
		
		
		$("#casteDetailsId"+divId+"").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 1, "desc" ]],
			"aLengthMenu": [[15,30, 50, 100, -1], [15,30, 50, 100, "All"]]
		});
		$("#locationOverveiewDiv"+divId+"").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 1, "desc" ]],
			"aLengthMenu": [[15,30, 50, 100, -1], [15,30, 50, 100, "All"]]
		});
		
		$("#casteCategoryId"+divId+"").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 1, "desc" ]],
			"aLengthMenu": [[15,30, 50, 100, -1], [15,30, 50, 100, "All"]]
		});
		
		$("#ageRangeID"+divId+"").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 1, "desc" ]],
			"aLengthMenu": [[15,30, 50, 100, -1], [15,30, 50, 100, "All"]]
		});
		
		$('#casteCategoryId'+divId+'_length,#ageRangeID'+divId+'_length').hide();
		$('#casteCategoryId'+divId+'_filter,#ageRangeID'+divId+'_filter').hide();
		$('#casteCategoryId'+divId+'_info,#ageRangeID'+divId+'_info').hide();
		$('#casteCategoryId'+divId+'_paginate,#ageRangeID'+divId+'_paginate').hide();
		
		$('#casteDetailsId'+divId+'_info').css('margin-bottom','35px');
		$('#ageRangeID'+divId+'_info').css('margin-bottom','35px');
		$('#locationOverveiewDiv'+divId+'_length').css('margin-bottom','35px');
		
		$('html, body').animate({scrollTop:$('#casteCategoryId'+divId+'').offset().top}, 'slow');
	}
	
	function getRolesBasedReport(accessType,levelId,locationId,divId,locationName,clearDivs)
	{
		$('#ajaxImageIdAPmandal'+divId+'').show();
		$('html, body').animate({scrollTop:$('#ajaxImageIdAPmandal'+divId+'').offset().top}, 'slow');
		
		var rolesArr = new Array();
		var casteCategoryArr = new Array();
		var casteCategoryGroupArr = new Array();
		var casteIdsArr = new Array();
		var selectedRadio = $('#committeeLevelTypeId').val();
		var roleId = $('#committeePostitionId').val();		
		var locationLevelId =$('#searchLevelId').val();
		var committeeTypeId =$('#committeeTypeId').val();
		$('#'+divId+'').html('');
		var userAccessType =userInitialAccessType;
		var radioval  =  $('input[name=optradio]:radio:checked').val()
		//console.log(radioval);
		$('.btn-xs').removeClass("btn-success");
		$('.btn-xs').addClass("btn-danger");

		if(levelId != 0)
		{
			locationLevelId = levelId;
		}
		if(accessType != 0)
		{
			userAccessType = accessType ;
			//$('.reportsCls').html('');
			$('#'+accessType+'').prop("checked",true);			
			$('.'+accessType+'Cls').removeClass("btn-danger");			
			$('.'+accessType+'Cls').addClass("btn-success");			
		}
		else
		{
			userAccessType  =  $('input[name=optradio]:radio:checked').val()
			$('.'+userAccessType+'Cls').removeClass("btn-danger");			
			$('.'+userAccessType+'Cls').addClass("btn-success");			
			$('#'+userAccessType+'').prop("checked",true);
		}
		var castePercentage =0;
		var searchTypeId =0;
		if(clearDivs == 0)
		{
			$('.reportsCls').html('');
		}
		if(locationId != 0)
		{
			castePercentage = locationId;
		}
		if(roleId == 0)
		{
			rolesArr.push(1);
			rolesArr.push(2);
			rolesArr.push(3);
			rolesArr.push(4);
			rolesArr.push(5);
			rolesArr.push(6);
			rolesArr.push(7);
			rolesArr.push(8);
			rolesArr.push(9);
		}
		else
		{
			rolesArr.push(roleId);
		}
		
		var jObj = {
			rolesArr : rolesArr,
			casteCategoryArr : casteCategoryArr,
			casteCategoryGroupArr : casteCategoryGroupArr,
			casteIdsArr : casteIdsArr,
			locationLevelId: locationLevelId,
			committeeTypeId : committeeTypeId,
			userAccessType : userAccessType,
			castePercentage:castePercentage,
			searchTypeId:searchTypeId,
			selectedRadio:selectedRadio
		};
		$.ajax({
          type:'GET',
          url: 'getCommitteeRolesBasedReport.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null)
			{
				
				$('#ajaxImageIdAPmandal'+divId+'').hide();
				buildRoleWiseDetailsInfo(result,divId,accessType,locationName);
			}
			else{
				$('#'+divId+'').html('No Data Available');
			}
		});
	}
	
</script>
</body>
</html>
 