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
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
	<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
	<style>
	.slick-track
	{
		width:950px !important;
	}
	.table-bordered > thead > tr > th,
.table-bordered > tbody > tr > th,
.table-bordered > tfoot > tr > th,
.table-bordered > thead > tr > td,
.table-bordered > tbody > tr > td,
.table-bordered > tfoot > tr > td { border: 1px solid #cfcfcf;}

.table-yellow-bordered > thead > tr > th,
.table-yellow-bordered > tbody > tr > th,
.table-yellow-bordered > tfoot > tr > th,
.table-yellow-bordered > thead > tr > td,
.table-yellow-bordered > tbody > tr > td,
.table-yellow-bordered > tfoot > tr > td { border: 1px solid #cfcfcf;}
	.circle-text{
		line-height: 180px; font-size: 34px; top: 32px;  left: 0px; !important
	}
	.circle-info-half{
		line-height: 225px; left: 0px;  top: 34px;!important
	}
	table.dataTable thead th
	{
		padding:0px !important;
		width:45px !important;
	}
	#constiTableForDistrict > th{
		padding:3px !important;
	}
	.highlight{background-color: #fff !important;
	border-color: #ccc !important;
	color: #333 !important;}
	.navbar-nav > li > a {text-decoration:none;}
		a:hover {text-decoration:none;}
		.multiLevelLiA a{text-transform: uppercase;color:black;}
		body {
    background: url("") repeat scroll 0 0 !important;
}

#constiTableId tr.odd td.sorting_1,#districtTableId tr.odd td.sorting_1{
    background-color: #d3d3d3 !important;
}
#constiTableId tr.even td.sorting_1 , #districtTableId tr.even td.sorting_1{
    background-color: #fafafa !important;
}
#constiTableId tr.odd,#districtTableId tr.odd {
    background-color: #E5E5E5 !important;
}


.ivrdetails > li {
    margin-left: -17px;
    padding: 2px 8px;
	font-size: 13px;
}
.popOverStyle{
padding-left:0px; width:272px;margin-left:-14px;font-size: 11px;
}

.mainCls{
	background:#D6DCDE !important;
}
.affilCls, .hideCls {
	background:#E9E9E9 !important;
}


	
	label {
    display: inline-block;
    font-weight: normal !important;
	}

#constiTableId th{
    background-color: #d3d3d3 !important;
}
#constiTableId td{
    background-color: #F3F3F3 !important;
}

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
	.greyClass{ background-color:#777 !important ; color : #FFF !important;font-weight:bold !important }
	.orangeCls{background-color:orange !important;color:#fff !important;font-weight:bold !important}	
	.redCls{background-color:red !important;color:#fff !important;font-weight:bold !important}
	</style>

<script>
	var userAccessType = '${pageAccessType}';
</script>	
</head>
<body>
	<!--
	<c:if test="${!fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_SEARCH' )}">
	 <header style="align:center;background-color:#ef4036; display:flex;border-bottom:4px solid #13a751;" id="headerDivId">
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
					  <c:if test="${fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_DETAILED_REPORT' )}">
						 <li><a tabindex="-1" href="cadreCommitteeRolesDashboard.action">Committee Detailed Report </a></li>
					  </c:if>					 
				  	  <li><a tabindex="-1" href="committeeUpdateApproveAction.action">Approval Requests</a></li>
				  	  <li><a tabindex="-1" href="constituencyCommitteeSummaryAction.action">Advanced DashBoard</a></li>
                      <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
					</c:if>
					
                     <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
                    </ul>
                 
            </div>
	</header>
	</c:if>
	-->
	<div class="container">
    	<div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 ">
                    <h3 class="panel-header">COMMITTEE DASHBOARD</h3>
                    <hr style="border-color:#F00;margin:0px 0px 10px 0px;" />
                </div>
        </div>
		
		<div class="row">
			<div class="col-md-2 col-md-offset-6 col-xs-12 col-sm-2 col-sm-offset-4">
				<select class="form-control" id="tdpCommitteeYearId"></select>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
				<div class="input-group">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
					</span>
					<input type="text" class="form-control" id="reportrange"/>
				</div>
			</div>
			<div class="col-md-1 col-xs-12 col-sm-2">
				<button class="btn btn-success" id="getDetailsId" onclick="onLoadcimmitteeDashboardCalls();">SUBMIT</button>
			</div>
        </div>
		
    	<div class="row m_top10" id="APStateDiv" style="display:none;">
			<table class="table table-bordered" width="100%"  >
				<tr>
					<td width="22%" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);text-align:center;padding: 0px;">
						<div class="col-md-12">
							<h4>ANHDRA PRADESH</h4>
							<div class="row" id="totalAPCount">
								
							</div>
						
				
						</div>
					</td>
					<td width="78%" style="padding:0px;">
						<table width="100%" class="table table-bordered" style="background-color:transparent; margin-bottom:0px;"  >
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
								<td colspan="6" style="text-align: right;">
								        <button style="float:left;border: 0px none; background-color:#E5E5E;"onClick="showHideDivs('ap','Village');"> <span class="glyphicon glyphicon-chevron-down" aria-hidden="true" style="margin-top: 0px; padding-top: 0px;"></span><b>VILLAGE / WARD LEVEL COMMITTEES</b></button>
									<div id="apVillageButtonsDiv" style="" class="toggleCls"  >
										<button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdAPvillage" src="images/icons/search.gif" alt="Processing Image" style="display:none;"/></button>
										<button id="village"  class="btn btn-xs btn-success highlightClick1" onclick="getCommitteeDetails('AP','village')">Village</button> | 
										<button id="ward" class="btn btn-xs btn-success highlightClick1" onclick="getCommitteeDetails('AP','ward')">Ward</button> |										
										<button id="villageAll" class="btn btn-xs btn-success highlight highlightClick1 " onclick="getCommitteeDetails('AP','villageAll')">All</button>
									</div>
								</td>
							</tr>
							<tr id="apVillageBodyTR" class="apVillageBodyTR toggleCls12">
								<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div8"></div></h4></td>
								<td width="20%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;"><tbody>
									  <tr><td colspan="2" class="text-center"><b class="text-center">Main Committes</b></td></tr>
									  <tr>
									 
													  <td style="padding: 10px;">
													  <span class="text-success">Started</span><br>
																		<table>
																			<tbody><tr>
																				<td class="row-table">
																					<h4 class="m_top0"><div id="div32"></div></h4>
																				</td>
																				<td class="row-table">
																					<h5 class="m_top0"><div id="div9"></div></h5>
																				</td>
																			</tr>
														</tbody></table>
													  </td><td style="padding: 10px;">
									  <span class="text-success">Completed</span><br><table><tbody><tr><td class="row-table"><h4 class="row-table m_top0"><div id="div10"></div></h4></td><td class="row-table"><div id="div11">
																  </div></td></tr></tbody></table>
													  </td></tr>
									  </tbody></table>
																	</td>
							   
							   <td width="40%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;"><tbody>
  <tr><td colspan="2" class="text-center"><b class="text-center">Affiliated Committes</b></td></tr>
  <tr>
 
                  <td style="padding: 10px;">
                  <span class="text-success">Started</span><br>
									<table>
										<tbody><tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div12"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div9"></div></h5>
											</td>
										</tr>
                    </tbody></table>
                  </td><td style="padding: 10px;">
  <span class="text-success">Completed</span><br><table><tbody><tr><td class="row-table"><h4 class="row-table m_top0"><div id="div13"></div></h4></td><td class="row-table">
							  </td></tr></tbody></table>
                  </td></tr>
  </tbody></table>
								</td>
								<td style="padding:10px;" width="28%">TOTAL <br/><b>MEMBERS</b><h4 class="m_top0"><div id="div14"></div></h4></td>
								<div id="apVillageDiv"></div>
							</tr>
							<tr id="ivrDivIdAP" class="span apVillageBodyTR" ></tr>
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);" id="apMandalHeadingTR">
								<td colspan="6" style="text-align: right;">
								   <button style="float:left;border: 0px none; background-color:#E5E5E;" onClick="showHideDivs('ap','Mandal');"> <span class="glyphicon glyphicon-chevron-down" aria-hidden="true" style="margin-top: 0px; padding-top: 0px;"></span> <b>
								    MANDAL/TOWN/DIVISION LEVEL COMMITTEES</b></button>
									<div id="apMandalButtonsDiv" style="display:none;" class="toggleCls"  >
								    <button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdAPmandal" src="images/icons/search.gif" alt="Processing Image" style="display:none;" /></button>
									
									<button id="mandal" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','mandal')">Mandal</button> | 
									<button id="town" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','town')">Town</button> |	
									<button id="division" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','division')">Division</button> |									
									<button id="mandalAll" class="btn btn-xs btn-success highlight highlightClick " onclick="getCommitteeDetails('AP','mandalAll')">All</button></div>
								</td>
							</tr>
							<tr id="apMandalBodyTR" style="display:none;" class="toggleCls">
								<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div1"></div></h4></td>
								
								<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;">
								<tbody>
									  <tr><td colspan="2" class="text-center"><b class="text-center">Main Committes</b></td></tr>
									  <tr>
								<td style="padding:10px;">
									<span class="text-success">Started</span><br/>
									<table>
									<tbody>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div29"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div2"></div></h5>
											</td>
										</tr>
										</tbody>
									</table>
								</td>
								
								<td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div3"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><div id="div4"></div>
                                  
									
									
								</li></td></tr></tbody></table></td></tr>
									  </tbody></table>
																	</td>
								<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;"><tbody>
  <tr><td colspan="2" class="text-center"><b class="text-center">Affiliated Committes</b></td></tr>
  <tr>
								<td style="padding:10px;"><span class="text-success">Started</span><table>
										<tbody><tr>
											<td class="row-table"><h4 class="m_top0"><div id="div5"></div></h4></td></tr>
                    </tbody></table>
                  </td>
								<td style="padding:10px;"><span class="text-success">Completed </span><table><tbody><tr><td class="row-table">
                                <h4 class="row-table m_top0">
								
								<a><div id="div6"></div></a>
                              </h4></td><td class="row-table">
							  </td></tr></tbody></table>
                  </td></tr> </tbody></table>
								</td>
								<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div7"></div></h4></td>
							<div id="apMandalDiv"></div>
							</tr>
										
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); dispaly:none;" id="apDistrictHeadingTR">
							<td colspan="6" style="text-align: right;">
							    <button style="float:left;border: 0px none; background-color:#E5E5E;" onClick="showHideDivs('ap','District');"> <span class="glyphicon glyphicon-chevron-down" aria-hidden="true" style="margin-top: 0px; padding-top: 0px;"></span><b>  DISTRICT LEVEL COMMITTEES</b></button>
								
							    <button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdAPdistrict" src="images/icons/search.gif" alt="Processing Image" style="display:none;"/></button>
							</td>
						</tr>
						<tr id="apDistrictBodyTR" style="display:none" class="toggleCls">
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div150"></div></h4></td>
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;">
								<tbody>
									  <tr><td colspan="2" class="text-center"><b class="text-center">Main Committes</b></td></tr>
									  <tr>
							<td style="padding:10px;">
									<span class="text-success">Started</span>
									<table>
									<tbody>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div151"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div152"></div></h5>
											</td>
										</tr>
										</tbody>
									</table>
								</td>
						   <td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div153"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><div id="div154"></div>
                                 
									
								</li></td></tr></tbody></table></td></tr>
									  </tbody></table>
																	</td>
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;"><tbody>
  <tr><td colspan="2" class="text-center"><b class="text-center">Affiliated Committes</b></td></tr>
  <tr>
							<td style="padding:10px;"><span class="text-success">Started</span><table>
										<tbody><tr><td class="row-table"><h4 class="m_top0"><div id="div155"></div></h4></td></tr>
                    </tbody></table></td>
							<td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td class="row-table"><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div156"></div></a>
                               
                                </h4></td><td class="row-table">
							  </td></tr></tbody></table>
                  </td></tr> </tbody></table>
								</td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div157"></div></h4></td>
							<div id="apDistrictDiv"></div>
						</tr>
						
									
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);" id="apStateHeadingTR">
							<td colspan="6" style="text-align: right;">
							    <button style="float:left;border: 0px none; background-color:#E5E5E;" onClick="showHideDivs('ap','State');"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true" style="margin-top: 0px; padding-top: 0px;"></span><b>   STATE LEVEL COMMITTEES</b></button>
								
							    <button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdAPstate" src="images/icons/search.gif" alt="Processing Image" style="display:none;"/></button>
							</td>
						</tr>
						<tr id="apStateBodyTR" style="display:none" class="toggleCls">
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div166"></div></h4></td>
							
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;">
								<tbody>
									  <tr><td colspan="2" class="text-center"><b class="text-center">Main Committes</b></td></tr>
									  <tr>
							<td style="padding:10px;">
									<span class="text-success">Started</span>
									<table>
									<tbody>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div167"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div168"></div></h5>
											</td>
										</tr>
										</tbody>
									</table>
								</td>
						   <td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div169"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><div id="div170"></div>
                                 
									
								</li></td></tr></tbody></table></td></tr>
									  </tbody></table>
																	</td>
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;"><tbody>
  <tr><td colspan="2" class="text-center"><b class="text-center">Affiliated Committes</b></td></tr>
  <tr>
							<td style="padding:10px;"><span class="text-success">Started</span><table>
										<tbody><tr>
											<td class="row-table"><h4 class="m_top0"><div id="div171"></div></h4></td></tr>
                    </tbody></table>
                  </td>
							<td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td class="row-table"><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div172"></div></a>
                               
                                </h4></td><td class="row-table">
							  </td></tr></tbody></table>
                  </td></tr> </tbody></table>
								</td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div173"></div></h4></td>
							<div id="apStateDiv"></div>
						</tr>

						</table>
					</td>
				</tr>
			</table>
        </div>
		
		<div class="row m_top20" id="districtDiv" style="display:none;"> 
			<table class="table table-bordered" width="100%"  >
				<tr>
					<td width="22%" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);text-align:center;padding: 0px;">
						<div class="col-md-12">
							<h4 id="LocationIdDiv"  style="text-transform: uppercase;">ANHDRA PRADESH</h4>
							<div class="row" id="totalDistrictCount">
								
							</div>
						
				
						</div> 
					</td>
					<td width="78%" style="padding:0px;">
						<table width="100%" class="table table-bordered" style="background-color:transparent; margin-bottom:0px;"  >
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
								<td colspan="6" style="text-align: right;">
								    <button style="float:left;border: 0px none; background-color:#E5E5E;"> <b>  MANDAL/TOWN/DIVISION LEVEL COMMITTEES</b></button>
							
									<button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdAPmandal" src="images/icons/search.gif" alt="Processing Image" style="display:none;"/></button>
									
									<button id="mandal" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','mandal')">Mandal</button> | 
									<button id="town" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','town')">Town</button> |	
									<button id="division" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','division')">Division</button> |									
									<button id="mandalAll" class="btn btn-xs btn-success highlight highlightClick " onclick="getCommitteeDetails('AP','mandalAll')">All</button>
								</td>
							</tr>
							<tr id="districtMandalBodyTR">
								<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0">
								<div id="totalMainCount1"></div></h4></td>
								
								<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span><br/> Committees
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="percCount"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="percDtals"></div></h5>
											</td>
										</tr>
									</table>
								</td>
								
								<td style="padding:10px;" width="10%"><span class="text-success">Completed</span> <br/>Committees
									<table>
										<tr>
											<td  class="row-table">
												<h4 class="row-table m_top0">
												<div id="compltdPerc"></div>
												</h4>
											</td>
											<td  class="row-table">
												<ul class="nav navbar-nav">
												<li>
													<div id="div41"></div>
												</li>
												</ul>
											</td>
										</tr>
									</table>
								</td>
								<td style="padding:10px;" width="20%"><span class="text-success">Started</span><br/>Affiliated Committees<br/>
									<h4 class="m_top0">
										<div id="div51"></div>
									</h4>
								</td>
								<td style="padding:10px;" width="20%"><span class="text-success">Completed </span><br/>Affiliated Committees<br/>
									<h4 class="m_top0">
									<ul class="nav navbar-nav">
										<li>
										<a><div id="div61"></div></a>
										</li>
									</ul>
								  </h4>
								 </td>
								<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b>
									<h4 class="m_top0">
										<div id="div71"></div>
									</h4>
								</td>
								<div id="apMandalDiv"></div>
							</tr>
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
								<td colspan="6" style="text-align: right;">
								        <button style="float:left;border: 0px none; background-color:#E5E5E;"><b>VILLAGE / WARD LEVEL COMMITTEES</b></button>  

										<button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdAPvillage" src="images/icons/search.gif" alt="Processing Image" style="display:none;"/></button>
										<button id="village"  class="btn btn-xs btn-success highlightClick1" onclick="getCommitteeDetails('AP','village')">Village</button> | 
										<button id="ward" class="btn btn-xs btn-success highlightClick1" onclick="getCommitteeDetails('AP','ward')">Ward</button> |										
										<button id="villageAll" class="btn btn-xs btn-success highlight highlightClick1 " onclick="getCommitteeDetails('AP','villageAll')">All</button>
								</td>
							</tr>
							<tr>
								<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div81"></div></h4></td>
								<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span><br/> Committees
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div321"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div91"></div></h5>
											</td>
										</tr>
									</table>
								</td>
							   <td style="padding:10px;" width="10%"><span class="text-success">Completed</span> <br/>Committees<table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div101"></div></h4></td><td  class="row-table">
							   <ul class="nav navbar-nav">
								<li>
									<div id="div111"></div>
                                   
								</li>	
									
									</td></tr></table></td>
								<td style="padding:10px;" width="20%"><span class="text-success">Started</span><br/>Affiliated Committees<br/><h4 class="m_top0"><div id="div121"></div></h4></td> 
								<td style="padding:10px;" width="20%"><span class="text-success">Completed</span><br/>Affiliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div131"></div></a>
                               </h4></td>
								<td style="padding:10px;" width="28%">TOTAL <br/><b>MEMBERS</b><h4 class="m_top0"><div id="div141"></div></h4></td>
								<div id="apVillageDiv"></div>
							</tr>
								<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);" id="districtDistrictHeadingTR">
							<td colspan="6" style="text-align: right;">
							    <button style="float:left;border: 0px none; background-color:#E5E5E;"> <b> DISTRICT LEVEL COMMITTEES</b></button>
							    <button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdAPdistrict" src="images/icons/search.gif" alt="Processing Image" style="display:none;"/></button>
							</td>
						</tr>
						<tr id="districtDistrictBodyTR" >
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div182"></div></h4></td>
							<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span> Committees<br/>
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div183"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div184"></div></h5>
											</td>
										</tr>
									</table>
								</td>
						   <td style="padding:10px;" width="10%"><span class="text-success">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div185"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><div id="div186"></div>
                                 
									
								</li></td></tr></table></td>
							<td style="padding:10px;" width="20%"><span class="text-success">Started</span><br/>Affiliated Committees<br/><h4 class="m_top0"><div id="div187"></div></h4></td>
							<td style="padding:10px;" width="20%"><span class="text-success">Completed</span><br/>Affiliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div188"></div></a>
                               
                                </h4></td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div189"></div></h4></td>
							<div id="apDistrictDiv"></div>
						</tr>
						</table>
					</td>
				</tr>
			</table>
        </div>
		
        <div class="row" id="TGStateDiv"  style="display:none;">
			<table class="table table-bordered" width="100%"  >
        	<tr>
        	    <td width="22%" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);text-align:center;padding: 0px;">
					<div class="col-md-12"  >
						<h4 style="margin-top: 35px;" >TELANGANA</h4>
						<div class="row" id="totalTSCount" style="margin-top: 12px;">
								
						</div>
						<!--<div class="row ">
							<div class="col-md-5 col-md-offset-1 col-xs-3"><span style="font-size:2em;">40000</span></div>
							<div style="" class="col-md-6 col-xs-3 text-left"><small>TOTAL MAIN COMMITTEES</small></div>
						</div>
						<div class="myStat3" data-dimension="180" data-text="35%" data-info="COMPLETED" data-width="10" data-fontsize="34" data-percent="35" data-fgcolor="#349866" data-bgcolor="#6D6024"></div>-->
					</div>
				</td>
				
        	    <td width="78%" style="padding:0px;">
					<table width="100%" class="table table-bordered" style="background-color:transparent; margin-bottom:0px;"  >
						<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
							<td colspan="6" style="text-align: right;">
							     <button style="float:left;border: 0px none; background-color:#E5E5E;"onClick="showHideDivs('ts','Village');"> <span class="glyphicon glyphicon-chevron-down" aria-hidden="true" style="margin-top: 0px; padding-top: 0px;"></span><b>VILLAGE / WARD LEVEL COMMITTEES</b></button>
								 <div id="tsVillageButtonsDiv"  class="toggleCls1">
							    <button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdTSvillage" src="images/icons/search.gif" alt="Processing Image" style="display:none;"/></button>
								<button id="tsVillage" class="btn btn-xs btn-success highlightClick3" onclick="getCommitteeDetails('TS','village')";>Village</button> | 
								<button id="tsWard" class="btn btn-xs btn-success highlightClick3" onclick="getCommitteeDetails('TS','ward')";>Ward</button> |
								
								<button id="tsVillageAll" class="btn btn-xs btn-default  btn-success highlight highlightClick3" onclick="getCommitteeDetails('TS','villageAll')";>All</button>
								</div>
							</td>
						</tr>
						<tr id="tsVillageBodyTR" class="tsVillageBodyTR toggleCls12">
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div22"></div></h4></td>
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;">
								<tbody>
									  <tr><td colspan="2" class="text-center"><b class="text-center">Main Committes</b></td></tr>
									  <tr>
							<td style="padding:10px;">
									<span class="text-success">Started</span>
									<table>
									<tbody>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div38"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div23"></div></h5>
											</td>
										</tr>
										</tbody>
									</table>
								</td>
						   <td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div24"></div></h4></td><td  class="row-table">
						   <ul class="nav navbar-nav">
                              <li><div id="div25"></div>
                                   
									
								</li>
									</td></tr></tbody></table></td></tr>
									  </tbody></table>
																	</td>
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;"><tbody>
  <tr><td colspan="2" class="text-center"><b class="text-center">Affiliated Committes</b></td></tr>
  <tr>
							<td style="padding:10px;"><span class="text-success">Started</span><table>
										<tbody><tr>
											<td class="row-table"><h4 class="m_top0"><div id="div26"></div></h4></td></tr>
                    </tbody></table>
                  </td>
							<td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td class="row-table"><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div27"></div></a>
                             </h4></td><td class="row-table">
							  </td></tr></tbody></table>
                  </td></tr> </tbody></table>
								</td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div28"></div></h4></td> 
							<div id="tsVillageDiv"></div>
						</tr>
						<tr id="ivrDivIdTS"  class="span tsVillageBodyTR" ></tr>
						<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
							<td colspan="6" style="text-align: right;">
							    <button style="float:left;border: 0px none; background-color:#E5E5E;" onClick="showHideDivs('ts','Mandal');"> <span class="glyphicon glyphicon-chevron-down" aria-hidden="true" style="margin-top: 0px; padding-top: 0px;"></span> <b>  MANDAL/TOWN/DIVISION LEVEL COMMITTEES</b></button>
								<div id="tsMandalButtonsDiv" style="display:none;" class="toggleCls1">
							    <button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdTSmandal" src="images/icons/search.gif" alt="Processing Image" style="display:none;"/></button>
								
								<button id="tsMandal" class="btn btn-xs btn-success highlightClick2" onclick="getCommitteeDetails('TS','mandal')";>Mandal</button> | 
								<button id="tsTown" class="btn btn-xs btn-success highlightClick2" onclick="getCommitteeDetails('TS','town')";>Town</button> |
								<button id="tsDivision" class="btn btn-xs btn-success highlightClick2" onclick="getCommitteeDetails('TS','division')";>Division</button> |
								<button id="tsMandalAll" class="btn btn-xs  btn-success highlight highlightClick2 " onclick="getCommitteeDetails('TS','mandalAll')";>All</button></div>
							</td>
						</tr>
						<tr id="tsMandalBodyTR" style="display:none;" class="toggleCls1"> 
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div15"></div></h4></td>
							
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;">
								<tbody>
									  <tr><td colspan="2" class="text-center"><b class="text-center">Main Committes</b></td></tr>
									  <tr>
							<td style="padding:10px;">
									<span class="text-success">Started</span>
									<table>
									<tbody>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div35"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div16"></div></h5>
											</td>
										</tr>
										</tbody>
									</table>
								</td>
						   <td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div17"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><div id="div18"></div>
                                 
									
								</li></td></tr></tbody></table></td></tr>
									  </tbody></table></td>
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;"><tbody>
  <tr><td colspan="2" class="text-center"><b class="text-center">Affiliated Committes</b></td></tr>
  <tr>
							<td style="padding:10px;"><span class="text-success">Started</span><table>
										<tbody><tr>
											<td class="row-table"><h4 class="m_top0"><div id="div19"></div></h4></td></tr>
                    </tbody></table></td>
							<td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td class="row-table"><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div20"></div></a>
                               
                                </h4></td><td class="row-table">
							  </td></tr></tbody></table>
                  </td></tr> </tbody></table>
								</td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div21"></div></h4></td>
							<div id="tsMandalDiv"></div>
						</tr>

						<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);" id="tsDistrictHeadingTR">
							<td colspan="6" style="text-align: right;">
							    <button style="float:left;border: 0px none; background-color:#E5E5E;" onClick="showHideDivs('ts','District');"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true" style="margin-top: 0px; padding-top: 0px;"></span><b>  DISTRICT LEVEL COMMITTEES</b></button>
							    <button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdTSdistrict" src="images/icons/search.gif" alt="Processing Image" style="display:none;"/></button>
							</td>
						</tr>
						<tr id="tsDistrictBodyTR" style="display:none;" class="toggleCls1">
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div158"></div></h4></td>
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;">
								<tbody>
									  <tr><td colspan="2" class="text-center"><b class="text-center">Main Committes</b></td></tr>
									  <tr>
							<td style="padding:10px;">
									<span class="text-success">Started</span>
									<table>
									<tbody>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div159"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div160"></div></h5>
											</td>
										</tr>
										</tbody>
									</table>
								</td>
						   <td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div161"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><div id="div162"></div>
                                 
									
								</li></td></tr></tbody></table></td></tr>
									  </tbody></table></td>
									  <td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;"><tbody>
  <tr><td colspan="2" class="text-center"><b class="text-center">Affiliated Committes</b></td></tr>
  <tr>
							<td style="padding:10px;"><span class="text-success">Started</span><table>
										<tbody><tr>
											<td class="row-table"><h4 class="m_top0"><div id="div163"></div></h4></td></tr>
                    </tbody></table></td>
							<td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td class="row-table"><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div164"></div></a>
                               
                                </h4></td><td class="row-table">
							  </td></tr></tbody></table>
                  </td></tr> </tbody></table>
								</td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div165"></div></h4></td>
							<div id="tsDistrictDiv"></div>
						</tr>
	
						<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);" id="tsStateHeadingTR">
							<td colspan="6" style="text-align: right;">
							    <button style="float:left;border: 0px none; background-color:#E5E5E;" onClick="showHideDivs('ts','State');"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true" style="margin-top: 0px; padding-top: 0px;"></span><b>  STATE LEVEL COMMITTEES</b></button>
							    <button style="border: 0px none; background-color: rgb(211, 211, 211);"><img width="16" height="16" id="ajaxImageIdTSstate" src="images/icons/search.gif" alt="Processing Image" style="display:none;"/></button>
							</td>
						</tr>
						<tr id="tsStateBodyTR" style="display:none;" class="toggleCls1">
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div174"></div></h4></td>
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;">
								<tbody>
									  <tr><td colspan="2" class="text-center"><b class="text-center">Main Committes</b></td></tr>
									  <tr>
							<td style="padding:10px;">
									<span class="text-success">Started</span>
									<table>
									<tbody>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div175"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div176"></div></h5>
											</td>
										</tr>
										</tbody>
									</table>
								</td>
						   <td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div177"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><div id="div178"></div>
                                 
									
								</li></td></tr></tbody></table></td></tr>
									  </tbody></table></td>
							<td width="30%" style="padding: 0px;" colspan="2">
								<table class="table table-bordered" style="margin-bottom: 0px;"><tbody>
  <tr><td colspan="2" class="text-center"><b class="text-center">Affiliated Committes</b></td></tr>
  <tr>
							<td style="padding:10px;"><span class="text-success">Started</span><table>
										<tbody><tr>
											<td class="row-table"><h4 class="m_top0"><div id="div179"></div></h4></td></tr>
                    </tbody></table></td>
							<td style="padding:10px;"><span class="text-success">Completed</span><table><tbody><tr><td class="row-table"><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div180"></div></a>
                               
                                </h4></td><td class="row-table">
							  </td></tr></tbody></table>
                  </td></tr> </tbody></table>
								</td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div181"></div></h4></td>
							<div id="tsStateDiv"></div>
						</tr>
							
						
					</table>
				</td>
      	    </tr>
      	  </table>
        </div>
		
        <div class="row m_top20">        	 
            <div class="row" id="commityClsi">
             	<div class="col-md-12 col-xs-12 col-sm-12" >
                    
					
					 <div style="display:inline-block; margin-right: 15px;" id="searchScenariodiv">
                     <h5 class="text-success areaBtnsDiv" style="margin-bottom:5px;border-bottom:1px solid #F00;text-align:center;margin:0px 15px 5px 15px" >LOCATION</h5>
                     <span class="btn btn-success btn-xs form-inline" id="statesBtnsId" style="">
						<label class="radio"><input type="radio" id="APId" style="vertical-align: text-bottom;" class="stateRd" value="AP" name="selectstate" checked="true">&nbsp;AP &nbsp;&nbsp;&nbsp;</label>
						<label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="stateRd" value="TS" name="selectstate" id="TSId">&nbsp;TS &nbsp;&nbsp;&nbsp;</label>
					</span>
					 <span class="btn btn-success btn-xs form-inline" id="areaBtnsDiv">
						<label class="radio"><input type="radio" id="districtId" style="vertical-align: text-bottom;" class="levelRd" value="district" name="select" >&nbsp;DISTRICT &nbsp;&nbsp;&nbsp;</label>
						<label class="radio"><input type="radio" id="constiRdId" style="vertical-align: text-bottom;" class="levelRd" value="consti" name="select" checked="true" >&nbsp;CONSTITUENCY &nbsp;&nbsp;&nbsp;</label>
					</span>
                    </div>
                    <div style="display:inline-block; margin-right: 15px;">
                    <h5 class="text-success" style="margin-bottom:5px;border-bottom:1px solid #F00;text-align:center;margin:0px 15px 5px 15px"> COMMITTEE LEVEL </h5>
					<span class="btn btn-success btn-xs form-inline">
						<label class="radio"><input type="checkbox" id="villageId" style="vertical-align: text-bottom;" class="scopeRd" value="village" name="selectCheck"  checked="true"  >&nbsp; VILLAGE / WARD &nbsp;</label>
					</span>&nbsp;&nbsp;
					<span class="btn btn-success btn-xs form-inline">
						<label class="checkbox"><input type="checkbox" id="mandalId" style="vertical-align: text-bottom;" class="scopeRd" value="mandal" name="selectCheck" >&nbsp; TOWN / MANDAL / DIVISION &nbsp;&nbsp;</label>
					</span>
					
					
					<span class="btn btn-success btn-xs form-inline" id="districtCommDiv">
						<label class="checkbox"><input type="checkbox" id="districtCommId" style="vertical-align: text-bottom;" class="scopeRd" value="districtComm" name="selectCheck" disabled>&nbsp; DISTRICT &nbsp;&nbsp;</label>
					</span>
                    </div>
	
					<div style="display:inline-block">
                    <h5 class="text-success" style="margin-bottom:5px;border-bottom:1px solid #F00;text-align:center;margin:0px 15px 5px 15px"> CONSIDER AFFILIATED COMMITEES </h5>
					<span class="btn btn-success btn-xs form-inline  " id="considerAffDiv">
						<label class="checkbox"><input type="checkbox" id="considerAfflId" style="vertical-align: text-bottom;" class="scopeRd" name="selectCheck">&nbsp; WITH ALL AFFILIATED COMMITTEES &nbsp;&nbsp;</label>
					</span>
                    </div>
					<button id="detailReportId" class="btn btn-success btn-xs" style="dispaly:inline-block">Detailed Report</button>
					<div style="display:inline-block; margin-top:10px;text-align:center;" class="col-md-12">
                    	<h4 id="headingId" style="display:inline-block" class="text-success">DISTRICT WISE COMMITTEES</h4>
                        <span class="btn btn-info pull-right excelId form-inline" onclick="exportToExcel()" style="display:inline-block;"> Export To Excel </span>
                    </div>
              
                </div>
               
            </div>
			
            <div class="row">
            	<div class="col-md-12 col-xs-12 col-md-12">
						<center><img id="summaryAjax" style="width:80px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>
						<div id="distSummaryBody" style="overflow-x:scroll;">							
                        </div>
                                  
                </div>
            </div>
        </div>
		<div id="dialogSummary" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
			  <div class="modal-dialog" style="width:85%">
				<div class="modal-content" style="padding:15px">
					
					
				<!-- Summary Block For POPUP	-->		
				
				<div>
					<!--Content Start-->
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
							<h3 class="panel-header"><div id="mainCommTitleDivId">COMMITTEE SUMMARY</div></h3>
							<hr style="border-color:#F00;margin-top:10px;" />
						</div>
						
							<span type="button" style="font-size:30px;cursor:pointer;" class="pull-right" data-dismiss="modal" aria-hidden="true">&times;</span>
						
					</div>
					
					
					
					<!-- start -->
				
			 <!-- <div class="row">
            	<div class="col-md-12">
                	<h3 style="color:#090" id="stateDistrictTitle" ></h3>
                </div>
            </div> -->
			<div class="row">
			<div class="col-md-2 col-xs-12 col-sm-4" id="stateDiv" style="display:none;">
					<label>State</label>
						<span id="districtIdImg"><img src="images/search.gif" style="display:none;"/></span>
							<select id="popUpStateId" class="form-control" >
								<option value="0">All</option>
							</select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-4" id="popUpDistrictDiv" style="display:none;">
					<label>District</label>
						<span id="districtIdImg"><img src="images/search.gif" style="display:none;"/></span>
							<select id="popUpdistrictId" onchange="getConstituenciesForDistricts(this.value,this.id,'');" class="form-control" >
								<option value="0">All</option>
							</select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-4" id="popUpConstituencyDiv" style="display:none;">
					<label>Constituency</label>
						<span id="districtIdImg"><img src="images/search.gif" style="display:none;"/></span>
							<select id="popUpConstiesId"  class="form-control" >
								<option value="0">All</option>
							</select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-3" style="margin-top:25px;">
					<select class="form-control" id="tdpCommitteeYearId1"></select>
				</div>
				<div class="col-md-3 col-xs-12 col-sm-3" style="margin-top:25px;">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
						</span>
						<input type="text" class="form-control" id="reportrange1"/>
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-md-2 col-xs-12 col-sm-2 pull-right" style="margin-top: 10px;">
					<button class="btn btn-success" id="getDetailsId1">SUBMIT</button>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12">
					<h3 style="color:#090" id="stateDistrictTitle" ></h3>
				</div>
				</div>
				
			<div id="districtContent">	
			 <div class="row">
 				<div class="col-md-12">
				
				<div id="mainCommitteDivId"></div>
				<div id="AffliCommitteDivId"></div>
				</div>
				</div>
				</div>
					<!-- end-->
					<!-- First Block Start-->
					<div id="constituencyContent">
					<div class="row">
						<div class="col-md-12">
							<!--<h3 style="color:#090" >MANDAL / TOWN / DIVISION</h2>-->
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div id="mandalMainCommitteDivId"></div>
							
							<div id="mandalAffliCommitteDivId"></div>
							
						</div>           
					</div>
					<!--FIrst Block END-->
					<!--Second Block Start-->
					<div class="row">
						<div class="col-md-12" style="display:none;" id="villageDivId">
							<h3 style="color:#090" >VILLAGE / WARD</h2>
						</div>
						<div class="col-md-12" style="display:none;" id="mandalDivId">
							<h3 style="color:#090" >MANDAL / TOWN / DIVISION</h2>
						</div>
					</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div id="villageMainTableDivId"></div>
							
							<div id="villageAfflicatedTableDivId"></div>
							
							
						<img id="comitteeCntAjax" src="./images/icons/search.gif" alt="Processing Image" style="display:none;margin-left:400px;"/>
							<div class="table-responsive">
								<table class="table table-condensed table-bordered" style="border:5px solid #669934;background-color:rgba(0,0,0,0.1);border-radius:2px;" id="CommitteeDetails">
								</table>
							</div> 
						</div>  
					   
						 <img id="comitteeMemberAjax" src="./images/icons/search.gif" alt="Processing Image" style="display:none;margin-left:400px;"/>
						<div class="col-md-8 col-md-offset-1" id="committeeMemberDiv" >

						 </div>
						 <div class="col-md-8 col-md-offset-1"  >
								<div id="presGenSecrErrDivId" style="color:red;"></div>
						 </div>
						<div class="col-md-2" id="conformedBtn" style="padding-top:10px;"></div>
						
					</div>  
					
					<!--Second Block END-->
					<!--Content END-->
				
				</div>

			</div>
							
							
				<!-- End Summary Block For POPUP	-->					
						
					
				</div>
			  </div>
			
			</div>
			<div id="dialogSummaryDistsrict" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					
					 <div class="modal-header">
					  <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">x</span></button>
						<h3 class="panel-header text-center"></h3>
					  </div>
						<div id="cadreDetailsDiv" style="margin-top:25px;padding:10px;"></div>
				</div>
			</div>
    </div>
	<!-- district -->
	<div id="dialogSummaryForDist" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					
					 <div class="modal-header">
					  <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">x</span></button>
						<h3 class="panel-header text-center"></h3>
					  </div>
						<div id="cadreDetailsDivdist" style="margin-top:25px;padding:10px;"></div>
				</div>
			</div>
    </div>
	<!-- end -->

    </div>
<!-- DetailedReport PopUp -->
	<div class="modal" tabindex="-1" role="dialog" id="detailedReportModalDivId">
		  <div class="modal-dialog modal-lg" style="width:85%">
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Committee Detailed Report</h4>
			  </div>
			  <div class="modal-body">
			  <div class="row">
				  	  <div class="col-md-3 col-xs-12 col-sm-3">
							<label>Committee Type</label>
							<select class="form-control" style="width:220px" id="committeeTypeId">
								<option value="0"> All </option>
								<option value="1">Main Committee</option>
								<option value="2">Affiliated Committee</option>
							</select>
						  </div>
						  <div class="col-md-3 col-xs-12 col-sm-3">
							<label>Committee Level</label>
							<select class="form-control" style="width:220px" id="committeeLevelTypeId">
								<!--<option value="0">Select Committee Level</option>-->
								<option value="1">Village/Ward</option>
								<option value="2">Mandal/Town/Division</option>
								<option value="3">District</option>
								<option value="4">State</option>
							</select>
						  </div>
						 <!-- <div class="col-md-3 col-xs-12 col-sm-3">
								<label> Designation </label>
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
						  </div>-->
						  <div class="col-md-3 col-xs-12 col-sm-3">
										<label> Status </label>
										<select class="form-control" style="width:220px" id="committeeStatusId" >
											<option value="1">Started </option>
											<option value="2">Completed </option>
											<option value="3">Not Yet Started</option>
										</select>
									</div>
									 </div>
						
						<div class="row m_top20">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<h4 class="panel-title text-capital"><b>Location</b></h4>
							</div>
						</div>
						<div class="row m_top10">
							
							<div class="col-md-3 col-xs-12 col-sm-3" id="statedisplaydivid">
								<label>State</label>
									<span id="statesDivIdImg"><img src="images/search.gif" style="display:none;"/></span>
									<select id="statesDivId"  onchange="getDistrictsForStates(this.value,this.id,'');" class="form-control" style="width:220px">
										<!--<option value="0">All</option>-->
										<option value="1">Andhra Pradesh</option>
										<option value="36">Telangana</option>
									</select>
							</div>
									<div class="col-md-3 col-xs-12 col-sm-3" id="allDistrictDiv">
										<label>District</label>
										<span id="districtIdImg"><img src="images/search.gif" style="display:none;"/></span>
										<select id="detsRptdistrictId" onchange="getConstituenciesForDistricts(this.value,this.id,'');" class="form-control" style="width:220px">
											<!--<option value="0">All</option>-->
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3" id="usrWisedistrictDiv" style="display:none;">
										<label>District</label>
										<span id="districtIdImg"><img src="images/search.gif" style="display:none;"/></span>
										<select id="distUsrdistrictId" onchange="getConstituenciesForDistricts(this.value,this.id,'');" class="form-control" style="width:220px">
											<option value="0">All</option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3" id="constitunecyDiv">
										<label>Constituency</label>
										<span id="constituencyIdImg"><img src="images/search.gif" style="display:none;"/></span>
										<select id="detsRptConstituencyId" onchange="getMandalCorporationsByConstituency('',this.id);" class="form-control" style="width:220px">
											<option value="0">All</option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3" id="usrConstitunecyDiv" style="display:none;">
										<label>Constituency</label>
										<span id="constituencyIdImg"><img src="images/search.gif" style="display:none;"/></span>
										<select id="assblyConstituencyId" onchange="getMandalCorporationsByConstituency('',this.id);" class="form-control" style="width:220px">
											<option value="0">Select Constituency</option>
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3" id="mandalDiv">
										<label>Mandal/Muncipality/Corporation</label>
										<span id="mandalListImg"><img src="images/search.gif" style="display:none;"/></span>
										<select id="mandalList" onchange="getPanchayatWardByMandal('',this.id);"  class="form-control" style="width:220px">
											<!--<option value="0">All</option>-->
										</select>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3" id="panchayatDiv">
										<label>Village/Ward</label>
										<span id="panchaytListImg"><img src="images/search.gif" style="display:none;"/></span>
										<select id="panchaytList"  class="form-control" style="width:220px">
											<!--<option value="0">All</option>-->
										</select>
									</div>
						  <div class="col-md-3 col-xs-12 col-sm-3" style="margin-top:25px;">
							<button type="submit" class="btn btn-default btn-success" onclick="getRolesBasedReport();">Get Details</button>
						  </div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<div id="detailedReportId"></div>
						</div>
					</div>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div>
		
	<!-- POP UP FOR Committee entry Finished -->
	<div id="entryFinishedpopUpId" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button aria-label="Close" data-dismiss="modal" class="close modalCloseBtn" type="button"><span aria-hidden="true">x</span></button>
					<div id="entryFinishedpopUpBodyId"></div>
					<button class="btn btn-primary btn-sm pull-right modalCloseBtn" aria-label="Close" data-dismiss="modal">Close</button>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
    </div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" https://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
	<script src=" https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
    <script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
    <script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
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
	<script src="js/cadreCommittee/cadreCommitteeDashBoard.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/cadreCommitteeDashboard1.js" type="text/javascript"></script>
	<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/committeeCreationDetails.js" type="text/javascript"></script>
	<script>
	
	</script>
	
	<!----Bootstrap Date Range Picker Script---->
		<script type="text/javascript">
               $(document).ready(function() {
				   getDistrictsForStates(1,"statesDivId",'');
				   $("#APId").prop("checked", true);
				  // $("#districtId").prop("checked", true);
					$("#constiRdId").prop("checked", true);
				   //$("#districtCommId").prop("checked", true);

					$('#APStateDiv').hide();
					$('#TGStateDiv').hide();
					$('#districtDiv').hide();
					
					$('#statesBtnsId').hide();
					getCadreEnrollmentYears(1);
					getCadreEnrollmentYears(2);
					$('#reportrange').val(moment().format("MM/DD/YYYY") +'-'+ moment().format("MM/DD/YYYY"));
					$("#reportrange").daterangepicker({
						startDate: moment(),
						endDate: moment(),
						opens: 'left',
						format: 'MM/DD/YYYY',
						ranges: {
						   'Today' : [moment(), moment()],
						   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
						   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
						   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
						   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
						   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
						   'This Month': [moment().startOf('month'), moment()],
						   'This Year': [moment().startOf('Year'), moment()]
						}
					});
					$('#reportrange1').val(moment().format("MM/DD/YYYY") +'-'+ moment().format("MM/DD/YYYY"));
					$("#reportrange1").daterangepicker({
						startDate: moment(),
						endDate: moment(),
						opens: 'left',
						format: 'MM/DD/YYYY',
						ranges: {
						   'Today' : [moment(), moment()],
						   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
						   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
						   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
						   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
						   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
						   'This Month': [moment().startOf('month'), moment()],
						   'This Year': [moment().startOf('Year'), moment()]
						}
					});

					$(".highlightClick").click(function(){
						$(".highlightClick").removeClass("highlight");
						$(this).addClass("highlight");
					});
					$(".highlightClick1").click(function(){
						$(".highlightClick1").removeClass("highlight");
						$(this).addClass("highlight");
					});
					$(".highlightClick2").click(function(){
						$(".highlightClick2").removeClass("highlight");
						$(this).addClass("highlight");
					});
					$(".highlightClick3").click(function(){
						$(".highlightClick3").removeClass("highlight");
						$(this).addClass("highlight");
					});
					
               });
			   
               </script>
	<!----/Bootstrap Date Range Picker Script END---->
			   
			   		
	<script>
	
	$(document).on('click','.rangeButton',function(){
		
					getCommitteeDetails("AP","mandalAll");
					getCommitteeDetails("AP","villageAll");	
					getCommitteeDetails("TS","mandalAll");	
					getCommitteeDetails("TS","villageAll");
					if(userAccessType == 'AP' || userAccessType == 'TS' || userAccessType == 'ALL' ){
					 		}

					//$('input:radio[name="selectstate"][id="APId"]').prop('checked', true);
					//$('input:radio[name="select"][id="districtId"]').prop('checked', true);
					
					var levelSelected = $("input[type='radio'][name='select']:checked").val();

					if(userAccessType!="MP" && levelSelected == 'district'){
						getDistrictWiseCommittesSummary();
					}else{
						getConstituencyWiseCommittesSummary();
					}
					
	});

	function getCommitteeDetails(state,level){
	
	if(level == 'mandal' || level == 'town' || level == 'division' || level == 'mandalAll')
	{
		$('#ajaxImageId'+state+'mandal').show();
	}else if(level == 'village' || level == 'ward' || level == 'villageAll')
	{	
		//alert('ajaxImageId'+state+'village');
		$('#ajaxImageId'+state+'village').show();
	}
	else if(level == 'district' ){
		$('#ajaxImageId'+state+'district').show();	
	}
	else if(level == 'state' ){
		$('#ajaxImageId'+state+'state').show();	
	}
	
	//var startDate=$(".dp_startDate").val();
	//var endDate=$(".dp_endDate").val();
	var dateStr = $('#reportrange').val();
	var dateStrArr = dateStr.split('-');
	var startDatStr = dateStrArr[0];
	var endDatStr = dateStrArr[1];
		
		var levelIdsArr = new Array();
		var state = state; 
		if(level == 'mandal')
		{
		   levelIdsArr.push(5);
		}else if(level == 'town')
		{
		   levelIdsArr.push(7);
		}else if(level == 'division')
		{
		   levelIdsArr.push(9);
		}else if(level == 'village')
		{
		   levelIdsArr.push(6);
		}else if(level == 'ward')
		{
		   levelIdsArr.push(8);
		}else if(level == 'mandalAll')
		{
		    levelIdsArr.push(5);
			levelIdsArr.push(7);
			levelIdsArr.push(9);
		}else if(level == 'villageAll')
		{
		    levelIdsArr.push(6);
			levelIdsArr.push(8);
		}
		else if(level == 'district')
		{
		    levelIdsArr.push(11);
			
		}
		else if(level == 'state')
		{
		    levelIdsArr.push(10);
			
		}
		var committeeSpanTypeIdsArr = [];
		committeeSpanTypeIdsArr.push($('#tdpCommitteeYearId').val());
		var jObj = {
			levelIdsArr : levelIdsArr,
			state:state,
			startDate:startDatStr,
			endDate:endDatStr,
			task:"committeeDetails",
			committeeSpanTypeIdsList:committeeSpanTypeIdsArr
		}
		//alert(2211)	;
		$.ajax({
          type:'GET',
          url: 'getDashBoardLocationWiseDetails.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			//console.log(result);
			var str='';
			var str1='';
			var str2='';
			var str3='';
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		      location.reload(); 
    	        }
			}
			if(result != null){
				//console.log(result.accessState);
				if(result.accessState == 'AP' || result.accessState == 'TG' || result.accessState == 'ALL')
				{				
				if(state == "AP"){
					if(level == 'mandal' || level == 'town' || level == 'division' || level == 'mandalAll')
					{
						$('#ajaxImageId'+state+'mandal').hide();
						
						$("#div1").html(result.committeesCount);
						
						$("#div29").html(result.startedCommitteePerc+"%");
						if(result.mainCommittees > 0 && result.mainCommittees != null){
							$("#div2").html('<a id=\''+level+'IdAPstarted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;"><b>['+result.mainCommittees+']</b></a>');
						}
						else{						
							$("#div2").html('<span style="margin-left:0px;">[0]</span>');
						}
						
						//$("#div2").html('['+result.mainCommittees+']');						
						
						$("#div3").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null){
							$("#div4").html('<a id=\''+level+'IdAPcompleted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						}
						else{						
							$("#div4").html('<span style="margin-left:0px;">[0]</span>');
						}
					
						//$("#div30").html(result.afflCommitteesPerc);
						//$("#div5").html(result.afflCommittees);
						if(result.afflCommittees!=0){
							$("#div5").html('<a id=\''+level+'IdAPAfflstarted\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\',\'started\')" style="cursor:pointer;">'+result.afflCommittees+'</a>');
						}else{
							$("#div5").html('<span> 0 </span>');
						}
						if(result.affliatedCompleted!=0){
							$("#div6").html('<a id=\''+level+'IdAPAfflcompleted\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\',\'completed\')" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div6").html('<span> 0 </span>');
						}
						//$("#div6").html(result.affliatedCompleted);
						//$("#div31").html(result.affliatedCompletedPerc);
												
						$("#div7").html(result.membersCount);
					}
					
					else if(level == 'village' || level == 'ward' || level == 'villageAll')
					{
						$('#ajaxImageId'+state+'village').hide();
						
						$("#div8").html(result.committeesCount);
						
						$("#div32").html(result.startedCommitteePerc+"%");
						//$("#div9").html('['+result.mainCommittees+']');						
						if(result.mainCommittees > 0 && result.mainCommittees != null){
						
						
						$("#div9").html('<a id=\''+level+'IdAPstarted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;"><b>['+result.mainCommittees+']</b></a>');
						}
						else{
						$("#div9").html('<span style="margin-left:0px;">[0]</span>');
						}
						$("#div10").html(result.completedCommitteePerc+"%");
				
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						//$("#div11").html('&nbsp;&nbsp;&nbsp;['+result.completedCommittees+']');
						$("#div11").html('<a id=\''+level+'IdAPcompleted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						
						else{
						$("#div11").html('<span style="margin-left:0px;">[0]</span>');
						}
						//$("#div33").html(result.afflCommitteesPerc);
						//$("#div12").html(result.afflCommittees);
						if(result.afflCommittees!=0){
							$("#div12").html('<a id=\''+level+'IdAPAfflstarted\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\',\'started\')" style="cursor:pointer;">'+result.afflCommittees+'</a>');
						}else{
							$("#div12").html('<span> 0 </span>');
						}
						//$("#div13").html(result.affliatedCompleted);
						//if(result.completedCommittees > 0 && result.completedCommittees != null)
						if(result.affliatedCompleted!=0){
							$("#div13").html('<a id=\''+level+'IdAPAfflcompleted\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\',\'completed\')" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div13").html('<span> 0 </span>');
						}
						
						//$("#div34").html(result.affliatedCompletedPerc);
												
						$("#div14").html(result.membersCount);
						
					
						if(level == 'village' || level == 'villageAll' || level == 'ward'){
						var str='';
						str+='';
						//str+='<td colspan="6"> srishailam </td>';
						str+='<td colspan="6"><ul class="list-inline ivrdetails"><li style="background: none repeat scroll 0% 0% rgb(51, 51, 51); color: rgb(255, 255, 255);margin-left: 5px;"> VILLAGE IVR DETAILS </li><br>';
						var totalIVRCallsCount= 0;
						if(result.committeeSummaryVO.count != null && result.committeeSummaryVO.count > 0)
						{
							str+='<li style="margin-left:0px"><table class="table table-bordered"><tbody><tr><th  >Total Villages </th></tr><tr><td>'+result.committeeSummaryVO.count+'</td></tr></tbody></table></li>';
						}
						if(result.committeeSummaryVO.totalIvrCalls != null && result.committeeSummaryVO.totalIvrCalls > 0)
						{							
							str+='<li><table class="table table-bordered"><tbody><tr><th  >  Total IVR Calls</th></tr><tr><td>'+result.committeeSummaryVO.totalIvrCalls+'</td></tr></tbody></table></li>';
							
							if(result.committeeSummaryVO.answeredIvrCalls != null && result.committeeSummaryVO.answeredIvrCalls > 0)
							{
								var totLiftpercentage = 0;
								var totLiftperc = 0;
								totLiftpercentage = (result.committeeSummaryVO.answeredIvrCalls *100)/ result.committeeSummaryVO.totalIvrCalls;
								totLiftperc = totLiftpercentage.toFixed(0)
								
								str+='<li><table class="table table-bordered"><tbody><tr><th  > IVR Lifted </th></tr><tr><td>'+result.committeeSummaryVO.answeredIvrCalls+' ('+totLiftperc+'%)</td></tr></tbody></table></li>';
								
							}
							var totalAnswerd = 0;
							for(var pm in result.committeeSummaryVO.optionsList){
								totalAnswerd = totalAnswerd + result.committeeSummaryVO.optionsList[pm].count;
								totalIVRCallsCount = totalIVRCallsCount+ result.committeeSummaryVO.optionsList[pm].count;
							}
							var totpercentage = 0;
							var totperc = 0;
							totpercentage = (totalAnswerd *100)/ result.committeeSummaryVO.answeredIvrCalls;
							totperc = totpercentage.toFixed(0)
							
							str+='<li><table class="table table-bordered"><tbody><tr><th  >IVR Answred </th></tr><tr><td>'+totalAnswerd+' ('+totperc+'%)</td></tr></tbody></table></li>';
						}
						//str += '</ul>';
						
						if(result.committeeSummaryVO.count != null && result.committeeSummaryVO.count > 0)
						{
							str+='<li style="width: 93px; background: none repeat scroll 0 0 #fff;margin-left: -60px;"></li>';
						var percentage = 0;
						var perc = 0;
						if(result.committeeSummaryVO.total >0){
												
						for(var i in result.committeeSummaryVO.optionsList){
						if(result.committeeSummaryVO.optionsList[i].id != 8){
							percentage = (result.committeeSummaryVO.optionsList[i].count *100)/ totalIVRCallsCount;
							perc = percentage.toFixed(0);
							str+='<li><table class="table table-bordered"><tbody><tr><th  >'+result.committeeSummaryVO.optionsList[i].name+'</th></tr><tr><td>'+result.committeeSummaryVO.optionsList[i].count+' ('+perc+'%)</td></tr></tbody></table></li>';
						}
						}
						}
						else{
						if(result.committeeSummaryVO.optionsList[i] != 8){
							for(var i in result.committeeSummaryVO.optionsList){
							str+='<li><table class="table table-bordered"><tbody><tr><th  >'+result.committeeSummaryVO.optionsList[i].name+'</th></tr><tr><td>'+result.committeeSummaryVO.optionsList[i].count+' (0%)</td></tr></tbody></table></li>';
						}
						}
						}
						
						str+='<li>';
					str+='<a style = "cursor: pointer;" class="popOverCls" id="ivrPopOverAP" data-placement="bottom" data-toggle="popover"  onClick="showPopOver(\'AP\');" title="Village IVR Details" data-html="true" data-content="<ul class=popOverStyle><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.noAnswer+'</span>No Answer Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.userBusy+'</span>User Busy Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.newtworkError+'</span>Network Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.switchCongestion+'</span>Congestion Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.interworkingCount+'</span>Internetwork Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.callRejectedCount+'</span>Rejected Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.unallocatedNumbers+'</span>Unallocated Numbers</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.otherError+'</span>Others</li></ul>" >?</a>';					
						str+=' </li>';
						str+='</ul>';
												}
						
						
					var totalWardIVRCount =0;
					if(result.committeeSummaryVO.totalWards != null && result.committeeSummaryVO.totalWards > 0)
					{
							str+='<ul class="list-inline ivrdetails" style ="margin-top: -30px;margin-left:0px;">';
							str+='<ul class="list-inline ivrdetails"><li style="background: none repeat scroll 0% 0% rgb(51, 51, 51); color: rgb(255, 255, 255);margin-left: 5px;"> WARD IVR DETAILS</li><br>';
							str+='<li style="margin-left: 0px;"><table class="table table-bordered"><tbody><tr><th  >Total Wards</th></tr><tr><td>'+result.committeeSummaryVO.totalWards+'</td></tr></tbody></table></li>';
							
							if(result.committeeSummaryVO.totalWardIvr != null && result.committeeSummaryVO.totalWardIvr > 0)
							{
								str+='<li style=""><table class="table table-bordered"><tbody><tr><th  >  Total IVR Calls</th></tr><tr><td>'+result.committeeSummaryVO.totalWardIvr+'</td></tr></tbody></table></li>';
								
								if(result.committeeSummaryVO.totalWardAnswerdIvr != null && result.committeeSummaryVO.totalWardAnswerdIvr > 0)
								{
									var totLiftpercentage = 0;
									var totLiftperc = 0;
									totLiftpercentage = (result.committeeSummaryVO.totalWardAnswerdIvr *100)/ result.committeeSummaryVO.totalWardIvr;
									totLiftperc = totLiftpercentage.toFixed(0)
									str+='<li style=""><table class="table table-bordered"><tbody><tr><th  > IVR Lifted </th></tr><tr><td>'+result.committeeSummaryVO.totalWardAnswerdIvr+' ('+totLiftperc+'%)</td></tr></tbody></table></li>';
								}
								var totalAnswerd = 0;
								for(var pm in result.committeeSummaryVO.optionsList1){
									totalAnswerd = totalAnswerd + result.committeeSummaryVO.optionsList1[pm].count
									totalWardIVRCount = totalWardIVRCount + result.committeeSummaryVO.optionsList1[pm].count
								}
								var totpercentage = 0;
								var totperc = 0;
								totpercentage = (totalAnswerd *100)/ result.committeeSummaryVO.totalWardAnswerdIvr;
								totperc = totpercentage.toFixed(0)
								str+='<li style=""><table class="table table-bordered"><tbody><tr><th  style=";width:115px;"> IVR Answred</th></tr><tr><td>'+totalAnswerd+' ('+totperc+'%)</td></tr></tbody></table></li>';
							}
							//str += '</ul>';
					}
						
						
						if(result.committeeSummaryVO.totalWards != null && result.committeeSummaryVO.totalWards > 0)
						{
							str+='<li style="width: 93px; background: none repeat scroll 0 0 #fff;margin-left: -60px;"></li>';
								var percentage = 0;
								var perc = 0;
								if(result.committeeSummaryVO.total >0)
								{
														
									for(var i in result.committeeSummaryVO.optionsList1)
									{
										if(result.committeeSummaryVO.optionsList1[i].id != 13)
										{
											percentage = (result.committeeSummaryVO.optionsList1[i].count *100)/ totalWardIVRCount;
											perc = percentage.toFixed(0);
											str+='<li><table class="table table-bordered"><tbody><tr><th  >'+result.committeeSummaryVO.optionsList1[i].name+'</th></tr><tr><td>'+result.committeeSummaryVO.optionsList1[i].count+' ('+perc+'%) </td></tr></tbody></table></li>';
										}
									}
								}
								else
								{
									if(result.committeeSummaryVO.optionsList1[i] != 13)
									{
										for(var i in result.committeeSummaryVO.optionsList1)
										{
											str+='<li><table class="table table-bordered"><tbody><tr><th  >'+result.committeeSummaryVO.optionsList1[i].name+'</th></tr><tr><td>'+result.committeeSummaryVO.optionsList1[i].count+'  (0%)</td></tr></tbody></table> </li>';											
										}
									}
								}
							if(result.committeeSummaryVO.totalWards != null && result.committeeSummaryVO.totalWards > 0)
							{
								str+='<li>';
								str+='<a style = "cursor: pointer;" class="popOverCls" id="ivrPopOverAP" data-placement="bottom" data-toggle="popover"  onClick="showPopOver(\'AP\');" title=" Wards IVR Details" data-html="true" data-content="<ul class=popOverStyle><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.noAnswer+'</span>No Answer Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.userBusy+'</span>User Busy Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.newtworkError+'</span>Network Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.switchCongestion+'</span>Congestion Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.interworkingCount+'</span>Internetwork Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.callRejectedCount+'</span>Rejected Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.unallocatedNumbers+'</span>Unallocated Numbers</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.otherError+'</span>Others</li></ul>" >?</a>';					
								str+=' </li>';
							}
							
								str+='</ul>';
						}
						
						str+='</td>';
						//str+='</tr></table>';
					$("#ivrDivIdAP").show();
					$( "#ivrDivIdAP" ).insertAfter( "#ApTRId" );
					$('#ivrPopOverAP').popover();
					$("#ivrDivIdAP").html(str);
					 }
					}					
					else if(level == 'district')
					{
						$('#ajaxImageId'+state+'district').hide();
						
						$("#div150").html(result.committeesCount);
						
						$("#div151").html(result.startedCommitteePerc+"%");
						if(result.mainCommittees > 0 && result.mainCommittees != null){
							$("#div152").html('<a id=\''+level+'IdAPstarted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;"><b>['+result.mainCommittees+']</b></a>');
						}
						else{						
							$("#div152").html('<span style="margin-left:0px;">[0]</span>');
						}
						
						//$("#div2").html('['+result.mainCommittees+']');						
						
						$("#div153").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null){
							$("#div154").html('<a id=\''+level+'IdAPcompleted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						}
						else{						
							$("#div154").html('<span style="margin-left:0px;">[0]</span>');
						}
					

						if(result.afflCommittees!=0){
							$("#div155").html('<a id=\''+level+'IdAPAfflstarted\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\',\'started\')" style="cursor:pointer;">'+result.afflCommittees+'</a>');
						}else{
							$("#div155").html('<span> 0 </span>');
						}
						if(result.affliatedCompleted!=0){
							$("#div156").html('<a id=\''+level+'IdAPAfflcompleted\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\',\'completed\')" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div156").html('<span> 0 </span>');
						}
					
						$("#div157").html(result.membersCount);
					}			
					else if(level == 'state')
					{
						$('#ajaxImageId'+state+'state').hide();
						
						$("#div166").html(result.committeesCount);
						
						$("#div167").html(result.startedCommitteePerc+"%");
						if(result.mainCommittees > 0 && result.mainCommittees != null){
							$("#div168").html('<a id=\''+level+'IdAPstarted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;"><b>['+result.mainCommittees+']</b></a>');
						}
						else{						
							$("#div168").html('<span style="margin-left:0px;">[0]</span>');
						}
						
						$("#div169").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null){
							$("#div170").html('<a id=\''+level+'IdAPcompleted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						}
						else{						
							$("#div170").html('<span style="margin-left:0px;">[0]</span>');
						}
					

						if(result.afflCommittees!=0){
							$("#div171").html('<a id=\''+level+'IdAPAfflstarted\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\',\'started\')" style="cursor:pointer;">'+result.afflCommittees+'</a>');
						}else{
							$("#div171").html('<span> 0 </span>');
						}
						if(result.affliatedCompleted!=0){
							$("#div172").html('<a id=\''+level+'IdAPAfflcompleted\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\',\'completed\')" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div172").html('<span> 0 </span>');
						}
					
						$("#div173").html(result.membersCount);
					}
				}
				else if(state == "TS"){
					if(level == 'mandal' || level == 'town' || level == 'division' || level == 'mandalAll')
					{
						$('#ajaxImageId'+state+'mandal').hide();
						
						$("#div15").html(result.committeesCount);
						
						
						$("#div35").html(result.startedCommitteePerc+"%");
						//$("#div16").html('['+result.mainCommittees+']');						
						if(result.mainCommittees > 0 && result.mainCommittees != null)
						{
						
						$("#div16").html('<a id=\''+level+'IdTSstarted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;"><b>['+result.mainCommittees+']</b></a>');
						}
						else{
						$("#div16").html('<span style="margin-left:0px;">[0]</span>');
						}
						$("#div17").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						//$("#div18").html('&nbsp;&nbsp;&nbsp;['+result.completedCommittees+']');
						$("#div18").html('<a id=\''+level+'IdTScompleted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						
						else{
						$("#div18").html('<span style="margin-left:0px;">[0]</span>');
						}
						//$("#div36").html(result.afflCommitteesPerc);
						//$("#div19").html(result.afflCommittees);
						if(result.afflCommittees!=0){
							$("#div19").html('<a id=\''+level+'IdTSAfflstarted\' onClick="getAflCommitteeCount(\'TS\',\''+level+'\',\'started\')" style="cursor:pointer;">'+result.afflCommittees+'</a>');
						}else{
							$("#div19").html('<span> 0 </span>');
						}
						//$("#div37").html(result.affliatedCompletedPerc);
						//$("#div20").html(result.affliatedCompleted);
						if(result.affliatedCompleted!=0){
							$("#div20").html('<a id=\''+level+'IdTSAfflcompleted\' onClick="getAflCommitteeCount(\'TS\',\''+level+'\',\'completed\')" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div20").html('<span> 0 </span>');
						}
												
						$("#div21").html(result.membersCount);
					
					
					}
					
				 else if(level == 'village' || level == 'ward' || level == 'villageAll')
					{
						$('#ajaxImageId'+state+'village').hide();
						
						$("#div22").html(result.committeesCount);
						
						$("#div38").html(result.startedCommitteePerc+"%");
						//$("#div23").html('['+result.mainCommittees+']');						
						if(result.mainCommittees > 0 && result.mainCommittees != null)
						{						
						$("#div23").html('<a id=\''+level+'IdTSstarted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;"><b>['+result.mainCommittees+']</b></a>');
						}else{						
							$("#div23").html('<span style="margin-left:0px;">[0]</span>');
						}
						
						$("#div24").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						//$("#div25").html('&nbsp;&nbsp;&nbsp;['+result.completedCommittees+']');
						$("#div25").html('<a id=\''+level+'IdTScompleted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						else{						
							$("#div25").html('<span style="margin-left:0px;">[0]</span>');
						}
						
						//$("#div39").html(result.afflCommitteesPerc);
						//$("#div26").html(result.afflCommittees);
						if(result.afflCommittees!=0){
							$("#div26").html('<a id=\''+level+'IdTSAfflstarted\' onClick="getAflCommitteeCount(\'TS\',\''+level+'\',\'started\')" style="cursor:pointer;">'+result.afflCommittees+'</a>');	
						}else{
							$("#div26").html('<span> 0 </span>');
						}	
						//$("#div40").html(result.affliatedCompletedPerc);
						//$("#div27").html(result.affliatedCompleted);
						if(result.affliatedCompleted!=0){
							$("#div27").html('<a id=\''+level+'IdTSAfflcompleted\' onClick="getAflCommitteeCount(\'TS\',\''+level+'\',\'completed\')" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');	
						}else{
							$("#div27").html('<span> 0 </span>');
						}						
						$("#div28").html(result.membersCount);
						if(level == 'village' || level == 'villageAll' || level == 'ward'){
							var str1='';
						str1+='';
						//str1+='<td colspan="6"> srishailam </td>';
						str1+='<td colspan="6"><ul class="list-inline ivrdetails"><li style="background: none repeat scroll 0% 0% rgb(51, 51, 51); color: rgb(255, 255, 255);margin-left: 15px"> VILLAGE IVR DETAILS </li><br>';
						var totalIvrCallsCount = 0;
						if(result.committeeSummaryVO.count != null && result.committeeSummaryVO.count > 0)
						{
							str1+='<li style="margin-left: 10px"> <table class="table table-bordered"><tbody><tr><th  > Total Villages </th></tr><tr><td>'+result.committeeSummaryVO.count+'</td></tr></tbody></table></li>';
						}
						if(result.committeeSummaryVO.totalIvrCalls != null && result.committeeSummaryVO.totalIvrCalls > 0)
						{
							str1+='<li style=""><table class="table table-bordered"><tbody><tr><th  >  Total IVR Calls </th></tr><tr><td>'+result.committeeSummaryVO.totalIvrCalls+'</td></tr></tbody></table></li>';
							
							if(result.committeeSummaryVO.answeredIvrCalls != null && result.committeeSummaryVO.answeredIvrCalls > 0)
							{
								var totLiftpercentage = 0;
								var totLiftperc = 0;
								totLiftpercentage = (result.committeeSummaryVO.answeredIvrCalls *100)/ result.committeeSummaryVO.totalIvrCalls;
								totLiftperc = totLiftpercentage.toFixed(0)
								str1+='<li style=""><table class="table table-bordered"><tbody><tr><th  > IVR Lifted  </th></tr><tr><td>'+result.committeeSummaryVO.answeredIvrCalls+' ('+totLiftperc+'%)</td></tr></tbody></table></li>';
							}
							var totalAnswerd = 0;
							for(var pm in result.committeeSummaryVO.optionsList){
								totalAnswerd = totalAnswerd + result.committeeSummaryVO.optionsList[pm].count;
								totalIvrCallsCount = totalIvrCallsCount+result.committeeSummaryVO.optionsList[pm].count;
							}
							var totpercentage = 0;
							var totperc = 0;
							totpercentage = (totalAnswerd *100)/ result.committeeSummaryVO.answeredIvrCalls;
							totperc = totpercentage.toFixed(0)
							str1+='<li style=""><table class="table table-bordered"><tbody><tr><th  > IVR Answred </th></tr><tr><td>'+totalAnswerd+' ('+totperc+'%)</td></tr></tbody></table></li>';
						}
						//str1 += '</ul>';
						
						if(result.committeeSummaryVO.count != null && result.committeeSummaryVO.count > 0)
						{
							str1+='<li style="width: 93px; background: none repeat scroll 0 0 #fff;margin-left: -60px;"></li>';
						var percentage = 0;
						var perc = 0;
						if(result.committeeSummaryVO.total >0){
												
						for(var i in result.committeeSummaryVO.optionsList){
						if(result.committeeSummaryVO.optionsList[i].id != 8){
							percentage = (result.committeeSummaryVO.optionsList[i].count *100)/ totalIvrCallsCount;
							perc = percentage.toFixed(0);
							str1+='<li><table class="table table-bordered"><tbody><tr><th  >'+result.committeeSummaryVO.optionsList[i].name+' </th></tr><tr><td>'+result.committeeSummaryVO.optionsList[i].count+' ('+perc+'%) </td></tr></tbody></table></li>';
						}
						}
						}
						else{
						if(result.committeeSummaryVO.optionsList[i] != 8){
							for(var i in result.committeeSummaryVO.optionsList){
								str1+='<li><table class="table table-bordered"><tbody><tr><th  >'+result.committeeSummaryVO.optionsList[i].name+' </th></tr><tr><td>'+result.committeeSummaryVO.optionsList[i].count+' (0%)</td></tr></tbody></table></li>';
							}
						}
						}
						
					str1+='<li>';
					str1+='<a style = "cursor: pointer;" class="popOverCls" id="ivrPopOverAP" data-placement="bottom" data-toggle="popover"  onClick="showPopOver(\'AP\');"  data-html="true" data-content="<ul class=popOverStyle><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.noAnswer+'</span>No Answer Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.userBusy+'</span>User Busy Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.newtworkError+'</span>Network Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.switchCongestion+'</span>Congestion Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.interworkingCount+'</span>Internetwork Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.callRejectedCount+'</span>Rejected Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.unallocatedNumbers+'</span>Unallocated Numbers</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.otherError+'</span>Others</li></ul>" title=" Village IVR Details" >?</a>';					
						str1+=' </li>';
						str1+='</ul>';
						}
						
						
					var totalWardIVRCount =0;
					if(result.committeeSummaryVO.totalWards != null && result.committeeSummaryVO.totalWards > 0)
					{
							str1+='<ul class="list-inline ivrdetails"><li style="width: 93px; background: none repeat scroll 0 0 #fff;"></li>';
							str1+='<li style=""><table class="table table-bordered"><tbody><tr><th  >Total Wards </th></tr><tr><td>'+result.committeeSummaryVO.totalWards+'</td></tr></tbody></table></li>';
							
							if(result.committeeSummaryVO.totalWardIvr != null && result.committeeSummaryVO.totalWardIvr > 0)
							{
								str1+='<li style=""><table class="table table-bordered"><tbody><tr><th  >  Total IVR Calls </th></tr><tr><td>'+result.committeeSummaryVO.totalWardIvr+'</td></tr></tbody></table></li>';
								
								if(result.committeeSummaryVO.totalWardAnswerdIvr != null && result.committeeSummaryVO.totalWardAnswerdIvr > 0)
								{
									var totLiftpercentage = 0;
									var totLiftperc = 0;
									totLiftpercentage = (result.committeeSummaryVO.totalWardAnswerdIvr *100)/ result.committeeSummaryVO.totalWardIvr;
									totLiftperc = totLiftpercentage.toFixed(0)
									str1+='<li style=""><table class="table table-bordered"><tbody><tr><th  > IVR Lifted  </th></tr><tr><td>'+result.committeeSummaryVO.totalWardAnswerdIvr+' ('+totLiftperc+'%)</td></tr></tbody></table></li>';
								}
								var totalAnswerd = 0;
								for(var pm in result.committeeSummaryVO.optionsList1){
									totalAnswerd = totalAnswerd + result.committeeSummaryVO.optionsList1[pm].count
									totalWardIVRCount = totalWardIVRCount + result.committeeSummaryVO.optionsList1[pm].count
								}
								var totpercentage = 0;
								var totperc = 0;
								totpercentage = (totalAnswerd *100)/ result.committeeSummaryVO.totalWardAnswerdIvr;
								totperc = totpercentage.toFixed(0)
								str1+='<li style=""><table class="table table-bordered"><tbody><tr><th  > IVR Answred </th></tr><tr><td>'+totalAnswerd+' ('+totperc+'%)</td></tr></tbody></table></li>';
							}
							//str1 += '</ul>';
					}
						
						
						if(result.committeeSummaryVO.totalWards != null && result.committeeSummaryVO.totalWards > 0)
						{
							str1+='<li style="width: 93px; background: none repeat scroll 0 0 #fff;margin-left: -60px;"></li>';
								var percentage = 0;
								var perc = 0;
								if(result.committeeSummaryVO.total >0)
								{
														
									for(var i in result.committeeSummaryVO.optionsList1)
									{
										if(result.committeeSummaryVO.optionsList1[i].id != 13)
										{
											percentage = (result.committeeSummaryVO.optionsList1[i].count *100)/ totalWardIVRCount;
											perc = percentage.toFixed(0);
											str1+='<li><table class="table table-bordered"><tbody><tr><th  >'+result.committeeSummaryVO.optionsList1[i].name+' </th></tr><tr><td>'+result.committeeSummaryVO.optionsList1[i].count+' ('+perc+'%)  </td></tr></tbody></table></li>';
										}
									}
								}
								else
								{
									if(result.committeeSummaryVO.optionsList1[i] != 13)
									{
										for(var i in result.committeeSummaryVO.optionsList1)
										{
											str1+='<li><table class="table table-bordered"><tbody><tr><th  >'+result.committeeSummaryVO.optionsList1[i].name+' </th></tr><tr><td>'+result.committeeSummaryVO.optionsList1[i].count+' (0%)</td></tr></tbody></table></li>';
										}
									}
								}
							if(result.committeeSummaryVO.totalWards != null && result.committeeSummaryVO.totalWards > 0)
							{
								str1+='<li>';
								str1+='<a style = "cursor: pointer;" class="popOverCls" id="ivrPopOverAP" data-placement="bottom" data-toggle="popover"  onClick="showPopOver(\'AP\');" title=" Wards IVR Details" data-html="true" data-content="<ul class=popOverStyle><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.noAnswer+'</span>No Answer Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.userBusy+'</span>User Busy Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.newtworkError+'</span>Network Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.switchCongestion+'</span>Congestion Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.interworkingCount+'</span>Internetwork Error</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.callRejectedCount+'</span>Rejected Calls</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.unallocatedNumbers+'</span>Unallocated Numbers</li><li  class=list-group-item><span class=badge>'+result.committeeSummaryVO.otherError+'</span>Others</li></ul>" >?</a>';					
								str1+=' </li>';
							}
							
								str1+='</ul>';
						}
						
						str1+='</td>';
						//str1+='</tr></table>';
					$("#ivrDivIdTS").show();
					$( "#ivrDivIdTS" ).insertAfter( "#tsTRId" );
					$('#ivrPopOverTS').popover();
					$("#ivrDivIdTS").html(str1);
						 }
				}
				else if(level == 'district')
				{
						$('#ajaxImageId'+state+'district').hide();
						
						$("#div158").html(result.committeesCount);
						
						
						$("#div159").html(result.startedCommitteePerc+"%");
						//$("#div16").html('['+result.mainCommittees+']');						
						if(result.mainCommittees > 0 && result.mainCommittees != null)
						{
						
						$("#div160").html('<a id=\''+level+'IdTSstarted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;"><b>['+result.mainCommittees+']</b></a>');
						}
						else{
						$("#div160").html('<span style="margin-left:0px;">[0]</span>');
						}
						$("#div161").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						$("#div162").html('<a id=\''+level+'IdTScompleted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						
						else{
						$("#div162").html('<span style="margin-left:0px;">[0]</span>');
						}
					
						if(result.afflCommittees!=0){
							$("#div163").html('<a id=\''+level+'IdTSAfflstarted\' onClick="getAflCommitteeCount(\'TS\',\''+level+'\',\'started\')" style="cursor:pointer;">'+result.afflCommittees+'</a>');
						}else{
							$("#div163").html('<span> 0 </span>');
						}
					
						if(result.affliatedCompleted!=0){
							$("#div164").html('<a id=\''+level+'IdTSAfflcompleted\' onClick="getAflCommitteeCount(\'TS\',\''+level+'\',\'completed\')" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div164").html('<span> 0 </span>');
						}
												
						$("#div165").html(result.membersCount);
					
					
					}
					else if(level == 'state')
					{
						$('#ajaxImageId'+state+'state').hide();
						
						$("#div174").html(result.committeesCount);
						
						
						$("#div175").html(result.startedCommitteePerc+"%");
						
						if(result.mainCommittees > 0 && result.mainCommittees != null)
						{
						
						$("#div176").html('<a id=\''+level+'IdTSstarted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;"><b>['+result.mainCommittees+']</b></a>');
						}
						else{
						$("#div176").html('<span style="margin-left:0px;">[0]</span>');
						}
						$("#div177").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						$("#div178").html('<a id=\''+level+'IdTScompleted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						
						else{
						$("#div178").html('<span style="margin-left:0px;">[0]</span>');
						}
					
						if(result.afflCommittees!=0){
							$("#div179").html('<a id=\''+level+'IdTSAfflstarted\' onClick="getAflCommitteeCount(\'TS\',\''+level+'\',\'started\')" style="cursor:pointer;">'+result.afflCommittees+'</a>');
						}else{
							$("#div179").html('<span> 0 </span>');
						}
					
						if(result.affliatedCompleted!=0){
							$("#div180").html('<a id=\''+level+'IdTSAfflcompleted\' onClick="getAflCommitteeCount(\'TS\',\''+level+'\',\'completed\')" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div180").html('<span> 0 </span>');
						}
												
						$("#div181").html(result.membersCount);
					
					
					}
	
				}
			  }
			
			  else if(result.accessState.indexOf("District") >= 0 ){

				  if(level == 'mandal' || level == 'town' || level == 'division' || level == 'mandalAll')
					{
						$('#ajaxImageId'+state+'mandal').hide();
						
						$("#totalMainCount1").html(result.committeesCount);
						
						$("#percCount").html(result.startedCommitteePerc+"%");
						$("#percDtals").html('['+result.mainCommittees+']');						
						
						$("#compltdPerc").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null){
							$("#div41").html('<a id=\''+level+'IdAP\' class="btn" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						}
						else{						
							$("#div41").html('<span style="margin-left:0px;">[0]</span>');
						}
					
						//$("#div30").html(result.afflCommitteesPerc);
						$("#div51").html(result.afflCommittees);
						
						if(result.affliatedCompleted!=0){
							$("#div61").html('<a id=\''+level+'IdAPAffl\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\')" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div61").html('<span> 0 </span>');
						}
						//$("#div6").html(result.affliatedCompleted);
						//$("#div31").html(result.affliatedCompletedPerc);
												
						$("#div71").html(result.membersCount);
					}
					
					else if(level == 'village' || level == 'ward' || level == 'villageAll')
					{
						$('#ajaxImageId'+state+'village').hide();
						
						$("#div81").html(result.committeesCount);
						
						$("#div321").html(result.startedCommitteePerc+"%");
						$("#div91").html('['+result.mainCommittees+']');						
						
						$("#div101").html(result.completedCommitteePerc+"%");
				
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						//$("#div11").html('&nbsp;&nbsp;&nbsp;['+result.completedCommittees+']');
						$("#div111").html('<a id=\''+level+'IdAP\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						
						else{
						$("#div111").html('<span style="margin-left:0px;">[0]</span>');
						}
						//$("#div33").html(result.afflCommitteesPerc);
						$("#div121").html(result.afflCommittees);
						
						$("#div131").html(result.affliatedCompleted);
						//if(result.completedCommittees > 0 && result.completedCommittees != null)
						if(result.affliatedCompleted!=0){
							$("#div131").html('<a id=\''+level+'IdAPAffl\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\')" style="cursor:pointer;" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div131").html('<span> 0 </span>');
						}
						
						//$("#div34").html(result.affliatedCompletedPerc);
												
						$("#div141").html(result.membersCount);
					
					}
					else if(level == 'district')
					{
						$('#ajaxImageId'+state+'village').hide();
						
						$("#div182").html(result.committeesCount);
						
						$("#div183").html(result.startedCommitteePerc+"%");
						$("#div184").html('['+result.mainCommittees+']');						
						
						$("#div185").html(result.completedCommitteePerc+"%");
				
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						//$("#div11").html('&nbsp;&nbsp;&nbsp;['+result.completedCommittees+']');
						$("#div186").html('<a id=\''+level+'IdAP\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						
						else{
						$("#div186").html('<span style="margin-left:0px;">[0]</span>');
						}
						//$("#div33").html(result.afflCommitteesPerc);
						$("#div187").html(result.afflCommittees);
						
						$("#div188").html(result.affliatedCompleted);
						//if(result.completedCommittees > 0 && result.completedCommittees != null)
						if(result.affliatedCompleted!=0){
							$("#div188").html('<a id=\''+level+'IdAPAffl\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\')" style="cursor:pointer;" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div188").html('<span> 0 </span>');
						}
						
						//$("#div34").html(result.affliatedCompletedPerc);
												
						$("#div189").html(result.membersCount);
					
					}
					
			  
			  
			  }
			  
			  	  else {
				  if(level == 'mandal' || level == 'town' || level == 'division' || level == 'mandalAll')
					{
						$('#ajaxImageId'+state+'mandal').hide();
						
						$("#totalMainCount1").html(result.committeesCount);
						
						$("#percCount").html(result.startedCommitteePerc+"%");
						$("#percDtals").html('['+result.mainCommittees+']');						
						
						$("#compltdPerc").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null){
							$("#div41").html('<a id=\''+level+'IdAP\' class="btn" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						}
						else{						
							$("#div41").html('<span style="margin-left:0px;">[0]</span>');
						}
					
						//$("#div30").html(result.afflCommitteesPerc);
						$("#div51").html(result.afflCommittees);
						
						if(result.affliatedCompleted!=0){
							$("#div61").html('<a id=\''+level+'IdAPAffl\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\')" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div61").html('<span> 0 </span>');
						}
						//$("#div6").html(result.affliatedCompleted);
						//$("#div31").html(result.affliatedCompletedPerc);
												
						$("#div71").html(result.membersCount);
					}
					
					else if(level == 'village' || level == 'ward' || level == 'villageAll')
					{
						$('#ajaxImageId'+state+'village').hide();
						
						$("#div81").html(result.committeesCount);
						
						$("#div321").html(result.startedCommitteePerc+"%");
						$("#div91").html('['+result.mainCommittees+']');						
						
						$("#div101").html(result.completedCommitteePerc+"%");
				
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						//$("#div11").html('&nbsp;&nbsp;&nbsp;['+result.completedCommittees+']');
						$("#div111").html('<a id=\''+level+'IdAP\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+')" style="cursor:pointer;"><b>['+result.completedCommittees+']</b></a>');
						
						else{
						$("#div111").html('<span style="margin-left:0px;">[0]</span>');
						}
						//$("#div33").html(result.afflCommitteesPerc);
						$("#div121").html(result.afflCommittees);
						
						$("#div131").html(result.affliatedCompleted);
						//if(result.completedCommittees > 0 && result.completedCommittees != null)
						if(result.affliatedCompleted!=0){
							$("#div131").html('<a id=\''+level+'IdAPAffl\' onClick="getAflCommitteeCount(\'AP\',\''+level+'\')" style="cursor:pointer;" style="cursor:pointer;">'+result.affliatedCompleted+'</a>');
						}else{
							$("#div131").html('<span> 0 </span>');
						}
						
						//$("#div34").html(result.affliatedCompletedPerc);
												
						$("#div141").html(result.membersCount);
					
					}
			  
			  
			  }
			  
			  
			}
			var enrollmentYearId=$("#tdpCommitteeYearId").val();
			if(enrollmentYerId == 1){
				$(".ivrdetails").show();
			}else if(enrollmentYerId == 2)
		$(".ivrdetails").hide();
		});
	}
	function getCommitteeCountByState(state){
	
		var selectedVal = "";
		$('#APStateDiv').hide();
		$('#TGStateDiv').hide();
		$('#statesBtnsId').hide();
		$('#districtDiv').hide();
		//<div class="row" id="districtDiv" style="display:none;"> LocationIdDiv
		var state = state; 
		var committeeSpanTypeIdsArr = [];
		committeeSpanTypeIdsArr.push($('#tdpCommitteeYearId').val());
		var dateStr = $('#reportrange').val();
		var dateStrArr = dateStr.split('-');
		var jObj = {
			state:state,
			task:"committeeDetails",
			committeeSpanTypeIdsList:committeeSpanTypeIdsArr,
			startDate:dateStrArr[0],
			endDate:dateStrArr[1]
		}
			//alert(2233)	;	
		$.ajax({
          type:'GET',
          url: 'getTotalCommitteeCntsByStateAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				var str='';
				str+='<div class="col-md-5 col-md-offset-1 col-xs-3"><span style="font-size:2em;">'+result.totalCommittees+'</span></div>';
				str+='<div style="" class="col-md-6 col-xs-3 text-left"><small>TOTAL MAIN COMMITTEES</small></div>';

				if(result.accessState == "TG")
				{
					if(state == "TS"){
						str+='<div class="myStatTS" data-dimension="180" data-text="'+result.totalCntPerc+'%" data-info="COMPLETED" data-width="10" data-fontsize="34" data-percent="'+result.totalCntPerc+'" data-fgcolor="#349866" data-bgcolor="#6D6024" style="margin-left: 40px;"></div>';
						$("#totalTSCount").html(str);
						$('.myStatTS').circliful();		
						$('#TGStateDiv').show();
						$('#TSId').prop("checked","checked");
					}
				}
				else if(result.accessState == "AP")
				{
					if(state == "AP"){	
						str+='<div class="myStatAP" data-dimension="180" data-text="'+result.totalCntPerc+'%" data-info="COMPLETED" data-width="10" data-fontsize="34" data-percent="'+result.totalCntPerc+'" data-fgcolor="#349866" data-bgcolor="#6D6024" style="margin-left: 40px;"></div>';				
						$("#totalAPCount").html(str);
						$('.myStatAP').circliful();
						$('#APStateDiv').show();
						$('#APId').prop("checked","checked");
					}
				}
				else if(result.accessState == "ALL")
				{
					if(state == "TS")
					{
						str+='<div class="myStatTS" data-dimension="180" data-text="'+result.totalCntPerc+'%" data-info="COMPLETED" data-width="10" data-fontsize="34" data-percent="'+result.totalCntPerc+'" data-fgcolor="#349866" data-bgcolor="#6D6024" style="margin-left: 40px;"></div>';
							$("#totalTSCount").html(str);
							$('.myStatTS').circliful();	
					}
					if(state == "AP")
					{	
						str+='<div class="myStatAP" data-dimension="180" data-text="'+result.totalCntPerc+'%" data-info="COMPLETED" data-width="10" data-fontsize="34" data-percent="'+result.totalCntPerc+'" data-fgcolor="#349866" data-bgcolor="#6D6024" style="margin-left: 40px;"></div>';				
							$("#totalAPCount").html(str);
							$('.myStatAP').circliful();
					}
						$('#APStateDiv').show();						
						$('#TGStateDiv').show();
						$('#statesBtnsId').show();
				}
				else{
					str+='<div class="myStatAP" data-dimension="180" data-text="'+result.totalCntPerc+'%" data-info="COMPLETED" data-width="10" data-fontsize="34" data-percent="'+result.totalCntPerc+'" data-fgcolor="#349866" data-bgcolor="#6D6024" style="margin-left: 40px;"></div>';				
							$("#totalDistrictCount").html(str);
							$("#LocationIdDiv").html(''+result.accessState+'');							
							$('.myStatAP').circliful();
							$('#districtDiv').show();
				}
		
		});
	}
	$(".stateRd").click(function(){
		var levelSelected = $("input[type='radio'][name='select']:checked").val();
		
		
		if(levelSelected == 'district'){
			getDistrictWiseCommittesSummary();
		}
		else if(levelSelected == 'consti'){
			getConstituencyWiseCommittesSummary();
		}
	});
	$(".scopeRd").click(function(){
		var levelSelected = $("input[type='radio'][name='select']:checked").val();
		
		//$("#distSummaryBody").html('<td colspan="13"><center><img id="summaryAjax" style="width:100px;height:20px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center></td>');
		
		if(levelSelected == 'district'){
			getDistrictWiseCommittesSummary();			
		}
		else if(levelSelected == 'consti'){
			getConstituencyWiseCommittesSummary();
		}
	});
	$(".levelRd").click(function(){
		
		var levelSelected1 = $("input[type='radio'][name='select']:checked").val();
		if(levelSelected1 == 'district'){
			getDistrictWiseCommittesSummary();
		}
		else if(levelSelected1 == 'consti'){
			getConstituencyWiseCommittesSummary();
		}
	});
	function getDistrictWiseCommittesSummary(){
		$(".excelId").hide();
		$("#distSummaryBody").html('');
		$("#summaryAjax").show();
		$("#districtCommDiv").show();
		//$("#stateCommDiv").show();
		var state = state; 
		var mandalCheck=  $('#mandalId').is(':checked')?"true":"false";
		var villageCheck=  $('#villageId').is(':checked')?"true":"false";
		var districtCommCheck =  $('#districtCommId').is(':checked')?"true":"false";
		
		var selected = $("input[type='radio'][name='selectstate']:checked");
		if (selected.length > 0) {
			state = selected.val();
		}
		if(districtCommCheck == "true"){
			$("#constiRdId").attr('disabled', true);		
		}
		else{
		$("#constiRdId").attr('disabled', false);	
		}
		//var startDate=$(".dp_startDate").val();
		//var endDate=$(".dp_endDate").val();
	var dateStr = $('#reportrange').val();		
	var dateStrArr = dateStr.split('-');
	var startDate = dateStrArr[0];
	var endDate = dateStrArr[1];
		var committeeSpanTypeIdsArr = [];
		committeeSpanTypeIdsArr.push($('#tdpCommitteeYearId').val());
		var jObj = {
			startDate:startDate,
			endDate:endDate,
			state:state,
			districtCommCheck:districtCommCheck,
			mandalCheck:mandalCheck,
			villageCheck:villageCheck,
			committeeSpanTypeIdsList:committeeSpanTypeIdsArr
		}
			//alert(111)	;
		$.ajax({
          type:'GET',
          url: 'getDistrictWiseCommittesSummaryAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			$("#summaryAjax").hide();
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		      location.reload(); 
    	        }
			}
		
			if(result == null){
				$(".excelId").hide();
				$("#distSummaryBody").html("<td style='text-align:center' colspan='13'><h4> NO DATA AVAILABLE </h4></td>");
				return;
			}
			
			buildResultDistrictSummary(result,mandalCheck,villageCheck,districtCommCheck,jObj);	
						
		});
	}
	
	function getMainCommitteeMembersCount(state,level,type,committeeId,committeeType){
		
		//var startDate=$(".dp_startDate").val();
		//var endDate=$(".dp_endDate").val();
		var dateStr = $('#reportrange').val();
		var dateStrArr = dateStr.split('-');
		var startDate = dateStrArr[0];
		var endDate = dateStrArr[1];
		var levelIdsArr = new Array();
		var state = state; 
		if(level == 'mandal')
		{
		   levelIdsArr.push(5);
		}else if(level == 'town')
		{
		   levelIdsArr.push(7);
		}else if(level == 'division')
		{
		   levelIdsArr.push(9);
		}else if(level == 'village')
		{
		   levelIdsArr.push(6);
		}else if(level == 'ward')
		{
		   levelIdsArr.push(8);
		}else if(level == 'mandalAll')
		{
		    levelIdsArr.push(5);
			levelIdsArr.push(7);
			levelIdsArr.push(9);
		}else if(level == 'villageAll')
		{
		    levelIdsArr.push(6);
			levelIdsArr.push(8);
		}
		else if(level == 'district')
		{
		    levelIdsArr.push(11);
			
		}
		else if(level == 'state')
		{
		    levelIdsArr.push(10);
			
		}
		var state = state; 
		var committeeSpanTypeIdsArr = [];
		committeeSpanTypeIdsArr.push($('#tdpCommitteeYearId').val());
		var jObj = {
			state:state,
			levelIdsArr:levelIdsArr,
			startDate  :startDate,
			endDate    :endDate,
			committeeId:committeeId,
			committeeType:committeeType,
			task:"mainCommitteeMemberCnt",
			committeeSpanTypeIdsList :committeeSpanTypeIdsArr
		}
			//alert(222)	;	
		$.ajax({
          type:'GET',
          url: 'getMembersRangeCountByLocationAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				if(type == 'main'){
					var str='';
					str+='<ul style="padding-left:0px; width:272px;margin-left:-14px;font-size: 11px;">';					
						for(var i in result){
							str+='<li class="list-group-item "><span class="badge">'+result[i].membersCount+'</span>1 MEMBER COMMITTEE</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount1+'</span>2-4 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount2+'</span>5-7 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount3+'</span>7-14 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount4+'</span>ABOVE 14 MEMBER COMMITTEES</li>';
						}
					str+='</ul>';
					$('#'+level+'Id'+state+committeeType+'').popover({
						html: true,
						placement: "bottom",
						title: '',
						content: str
								
					});
					
					if(!$('#'+level+'Id'+state+committeeType+'').hasClass("clearCls")){
						$('#'+level+'Id'+state+committeeType+'').addClass("clearCls");
						$('#'+level+'Id'+state+committeeType+'').popover("show");
					}else{
						$('#'+level+'Id'+state+committeeType+'').removeClass("clearCls");
					}
				}
				else if(type == 'affl'){
					var str='';
					str+='<ul style="padding-left:0px; width:272px;margin-left:-14px;font-size: 11px;">';					
						for(var i in result){
							str+='<li class="list-group-item "><span class="badge">'+result[i].membersCount+'</span>1 MEMBER COMMITTEE</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount1+'</span>2-4 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount2+'</span>5-7 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount3+'</span>7-14 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount4+'</span>ABOVE 14 MEMBER COMMITTEES</li>';
						}
					str+='</ul>';
					var popOverSettings = { 
						placement: 'left',
						container: 'body',
						html: true,
						title : '',
						content:"<div style='color:red'>This is your div content</div>"
						/* content: function () {
							return $('#popover-content').html();
						} */
					}
					$("#"+id).popover(popOverSettings);
				}
				
					
		});
	}
	
	function getMainCommitteeMembersCount1(state,level,type,committeeId,id, removeId,committeeType){
		
		//var startDate=$(".dp_startDate").val();
		//var endDate=$(".dp_endDate").val();
			var dateStr = $('#reportrange').val();		
			var dateStrArr = dateStr.split('-');
			var startDate = dateStrArr[0];
			var endDate = dateStrArr[1];
		var levelIdsArr = new Array();
		var state = state; 
		if(level == 'mandal')
		{
		   levelIdsArr.push(5);
		}else if(level == 'town')
		{
		   levelIdsArr.push(7);
		}else if(level == 'division')
		{
		   levelIdsArr.push(9);
		}else if(level == 'village')
		{
		   levelIdsArr.push(6);
		}else if(level == 'ward')
		{
		   levelIdsArr.push(8);
		}else if(level == 'mandalAll')
		{
		    levelIdsArr.push(5);
			levelIdsArr.push(7);
			levelIdsArr.push(9);
		}else if(level == 'villageAll')
		{
		    levelIdsArr.push(6);
			levelIdsArr.push(8);
		}
		else if(level == 'district')
		{
		    levelIdsArr.push(11);
			
		}
		else if(level == 'state')
		{
		    levelIdsArr.push(10);
			
		}
		var state = state; 
		var jObj = {
			state:state,
			levelIdsArr:levelIdsArr,
			startDate  :startDate,
			endDate    :endDate,
			committeeId:committeeId,
			committeeType:committeeType,
			task:"mainCommitteeMemberCnt",
		}
			//alert(333)	;	
		$.ajax({
          type:'GET',
          url: 'getMembersRangeCountByLocationAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				if(type == 'main'){
					var str='';
					str+='<ul style="padding-left:0px; width:272px;margin-left:-14px;font-size: 11px;">';					
						for(var i in result){
							str+='<li class="list-group-item "><span class="badge">'+result[i].membersCount+'</span>1 MEMBER COMMITTEE</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount1+'</span>2-4 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount2+'</span>5-7 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount3+'</span>ABOVE 7 MEMBER COMMITTEES</li>';
						}
					str+='</ul>';
					$('#'+level+'Id'+state+committeeType+'').popover({
						html: true,
						placement: "bottom",
						title: '',
						content: str
								
					});
					
					if(!$('#'+level+'Id'+state+committeeType+'').hasClass("clearCls")){
						$('#'+level+'Id'+state+committeeType+'').addClass("clearCls");
						$('#'+level+'Id'+state+committeeType+'').popover("show");
					}else{
						$('#'+level+'Id'+state+committeeType+'').removeClass("clearCls");
					}
				}
				else if(type == 'affl'){
					var str='';
					str+='<ul style="padding-left:0px; width:272px;margin-left:-14px;font-size: 11px;">';
						if(result.length>0){
							for(var i in result){
								str+='<li class="list-group-item resListLi"><span class="badge">'+result[i].membersCount+'</span>1 MEMBER COMMITTEE</li>';
								str+='<li class="list-group-item resListLi"><span class="badge">'+result[i].membersCount1+'</span>2-4 MEMBER COMMITTEES</li>';
								str+='<li class="list-group-item resListLi"><span class="badge">'+result[i].membersCount2+'</span>5-7 MEMBER COMMITTEES</li>';
								str+='<li class="list-group-item resListLi"><span class="badge">'+result[i].membersCount3+'</span>ABOVE 7 MEMBER COMMITTEES</li>';
							}
						}else{
							str+='<li class="list-group-item resListLi"> MEMBERS UNAVAILABLE </li>';
						}
						
					str+='</ul>';
					$('#'+id).popover({
						html: true,
						placement: "left",
						title: '',
						content: str
					});
					if(!$('#'+id).hasClass("multiLevelCls")){
						$('#'+removeId).popover("hide");
						$('#'+removeId).removeClass("multiLevelCls");
						$('#'+id).addClass("multiLevelCls");
						$('#'+id).popover("show");
						
					}else{
						$('#'+id).removeClass("multiLevelCls");
						$('#'+removeId).popover("hide");
					}
					//$("#"+id).popover(popOverSettings);
				}
				
					
		});
	}
	
	function getAflCommitteeCount(state,level,committeeType){
		
	//	var startDate=$(".dp_startDate").val();
	//	var endDate=$(".dp_endDate").val();
	var dateStr = $('#reportrange').val();		
	var dateStrArr = dateStr.split('-');
	var startDate = dateStrArr[0];
	var endDate = dateStrArr[1];	
		var levelIdsArr = new Array();
		var state = state; 
		if(level == 'mandal')
		{
		   levelIdsArr.push(5);
		}else if(level == 'town')
		{
		   levelIdsArr.push(7);
		}else if(level == 'division')
		{
		   levelIdsArr.push(9);
		}else if(level == 'village')
		{
		   levelIdsArr.push(6);
		}else if(level == 'ward')
		{
		   levelIdsArr.push(8);
		}else if(level == 'mandalAll')
		{
		    levelIdsArr.push(5);
			levelIdsArr.push(7);
			levelIdsArr.push(9);
		}else if(level == 'villageAll')
		{
		    levelIdsArr.push(6);
			levelIdsArr.push(8);
		}
		else if(level == 'district')
		{
		    levelIdsArr.push(11);
		}
		else if(level == 'state')
		{
		    levelIdsArr.push(10);
		}
		var state = state; 
		var committeeSpanTypeIdsArr = [];
		committeeSpanTypeIdsArr.push($('#tdpCommitteeYearId').val());
		var jObj = {
			state:state,
			levelIdsArr:levelIdsArr,
			startDate  :startDate,
			endDate    :endDate,
			committeeType:committeeType,
			task:"mainCommitteeMemberCnt",
			committeeSpanTypeIdsList :committeeSpanTypeIdsArr,
			activityMemberId : 0,
	        userTypeId : 0
		}
			//alert(444)	;	
		$.ajax({
          type:'GET',
          url: 'getStartedAffliCommitteesCountByLocation.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		      location.reload(); 
    	        }
			}
					var str1='';
					str1+='<ul multilevelul style="padding-left:0px; width:272px;margin-left:-14px; font-size: 11px; ">';	
					//str1+='<ul class="dropdown-menu arrow_box list-group"><div class="panel panel-default m_bottom0"><div class="panel-heading m_top0">COMMITTEE TYPE<span class="pull-right">TOTAL</span></div></div>';
					for(var i in result){  
					str1+='<li class="list-group-item multiLevelLiA" attr_state='+state+' attr_level='+level+' attr_type="affl" attr_resId='+result[i].id+' attr_commType='+committeeType+' id="'+level+'IdAffl'+state+committeeType+''+result[i].id+'" ><a  class="multilevelli" >'+result[i].name+'<span class="badge pull-right">'+result[i].afflCommittees+'</span></a></li>';
					
					//str1+='<li class="list-group-item multiLevelLiA" attr_state='+state+' attr_level='+level+' attr_type="affl" attr_resId=1 id="'+level+'IdAffl'+state+'1"><a     class="multilevelli" >'+result[i].name+' 1 <span class="pull-right badge">'+result[i].afflCommittees+'</span></a></li>';
					} 
					str1+='</ul>';
					$('#'+level+'Id'+state+'Affl'+committeeType).popover({
						html: true,
						placement: "bottom",
						title: '',
						content: str1
					});
					
					if(!$('#'+level+'Id'+state+'Affl'+committeeType).hasClass("clearCls")){
						$('#'+level+'Id'+state+'Affl'+committeeType).addClass("clearCls");
						$('#'+level+'Id'+state+'Affl'+committeeType).popover("show");
					}
					
		});
	}
	
	$(document).on("click",".multiLevelLiA",function(){
		//debugger;
		 
		var state =  $(this).attr("attr_state");
		var level =  $(this).attr("attr_level");
		var type =  $(this).attr("attr_type");
		var committeeId =  $(this).attr("attr_resId");
		var committeeType =  $(this).attr("attr_commType");
		var id = $(this).attr("id");
		var removeId = $(this).parent().find(".multiLevelCls").attr("id");
		getMainCommitteeMembersCount1(state,level,type,committeeId,id, removeId,committeeType);
		
		//$("#"+id).popover('show');
		
		//alert($(this).parent().closest("li").find(".multiLevelCls").html());
		
	});
	
	
	 /* $(document).click(function(e) {
		var target = e.target;
		if($(target).is(".artclBtn")){
		$(".articleListCls").toggleClass("open");
		getImagesOfFolderAction(pgNo,editionDwId);
		}else if (!$(target).is('#articlesListId') && !$(target).parent().is('#articlesListId') && !$(target).parent().parent().is('#articlesListId') && !$(target).parent().parent().parent().is('#articlesListId') && !$(target).parent().parent().parent().parent().is('#articlesListId') && !$(target).is('.alertClose') && !$(target).is('.alertEmailClose')) {
		$(".articleListCls").removeClass("open");
		}
	}); */

	
	 $('body').on('click', function (e) {

		if(!$(e.target).is('.multiLevelLiA') || !$(e.target).has('.multilevelCls') || !$(e.target).has('.multilevelli')){
			$('.clearCls').each(function () {
				var attrId= $(this).attr("id");
				var trgt = e.target;

				if(!$(this).is(trgt) && $(this).has(trgt).length === 0 && !$(trgt).is('.multiLevelCls') && !$(trgt).is('.multilevelli') && !$(trgt).is('.multiLevelLiA') ) {
				
					$("#"+attrId).popover('hide');
					$("#"+attrId).removeClass('clearCls');
					$(".multiLevelLiA").popover('hide');
					
				}
				
			});
		}
		$('.popOverCls').each(function () {				
				if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {					
					//$("#"+attrId).popover('hide');
					$(this).popover('hide');
				}
				
		});
	
	
				
		
		
		/* $('.multiLevelCls').each(function () {
			var attrId= $(this).attr("id");
			var trgt = e.target;
			if(!$(this).is(trgt) && $(this).has(trgt).length === 0 && $(this).has(trgt).length === 0) {
				$("#"+attrId).popover('hide');
				$("#"+attrId).removeClass('multiLevelCls');
			}
		}); */
		
		/* $('.multiLevelCls').each(function () {
			var attrId= $(this).attr("id");
			var trgt = e.target;
			alert($(trgt).html());
			if(!$(this).is(trgt) && $(this).has(trgt).length === 0 && $(this).has(trgt).length === 0) {
				$("#"+attrId).popover('hide');
				$("#"+attrId).removeClass('multiLevelCls');
			}
		}); */
	});
	
	function getConstituencyWiseCommittesSummary(){
		var state = ''; 
		$("#popUpdistrictId  option").remove();
		$("#popUpConstiesId option").remove();
		//$("#popUpConstiesId").trigger("chosen:updated");
		$(".excelId").hide();
		$("#distSummaryBody").html('');
		$("#summaryAjax").show();
		//$("#districtCommDiv").hide();
		//$("#stateCommDiv").hide();
		var selected = $("input[type='radio'][name='selectstate']:checked");
		var mandalCheck=  $('#mandalId').is(':checked')?"true":"false";
		var villageCheck=  $('#villageId').is(':checked')?"true":"false";

		if (selected.length > 0) {
			state = selected.val();
		}
		var committeeLevelIdsListArr = [];
		//var startDate = $(".dp_startDate").val();
		//var endDate=$(".dp_endDate").val();	
			var dateStr = $('#reportrange').val();		
			var dateStrArr = dateStr.split('-');
			var startDate = dateStrArr[0];
			var endDate = dateStrArr[1];
			if(mandalCheck == "true" && villageCheck == "true"){
				committeeLevelIdsListArr.push(5);
				committeeLevelIdsListArr.push(7);
				committeeLevelIdsListArr.push(9);
				committeeLevelIdsListArr.push(6);
				committeeLevelIdsListArr.push(8);
			}else if(villageCheck == "true"){
				committeeLevelIdsListArr.push(6);
				committeeLevelIdsListArr.push(8);
			}else if(mandalCheck == "true"){
				committeeLevelIdsListArr.push(5);
				committeeLevelIdsListArr.push(7);
				committeeLevelIdsListArr.push(9);
			}
			
				
				
				var mainOrAfflCommitteIdsArr = []; 
				mainOrAfflCommitteIdsArr.push(1);
				mainOrAfflCommitteIdsArr.push(2);
				
		var jObj = {
			startDate:startDate,
			endDate:endDate,
			state:state,
			mandalCheck:mandalCheck,
			villageCheck:villageCheck ,
			committeeEnrollmentId:[$('#tdpCommitteeYearId').val()],
			reqLocationTypeStr:"constituency",
			levelIds:committeeLevelIdsListArr
		}
			//alert(555)	;	
		$.ajax({
          type:'POST',
          url: 'getConstituencyWiseCommittesSummaryAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
	
		$("#summaryAjax").hide();
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				
					buildConstiWiseSummary(result,mandalCheck,villageCheck,jObj);	
				
		});
	}
	
	function showAdvanceDashBoard(constiIdReq){
		
		//window.location.href="constituencyCommitteeSummaryAction.action?accessConstituencyId="+constiIdReq;
		// window.open('constituencyCommitteeSummaryAction.action?accessConstituencyId='+constiIdReq+'','location=no','_blank');
	window.open('constituencyCommitteeSummaryAction.action?accessConstituencyId='+constiIdReq+'','location=no','top=10,left=25,status=no,toolbar=no,location=yes,menubar=no,titlebar=no,scrollbars=yes,modal=yes');		
	}
	
	function buildConstiWiseSummary(result,mandalCheck,villageCheck,jObj){
	
	
		$(".excelId").show();
		var constiInfoArr = [];
		var constiVillageInfoArr = [];
	
		$("#headingId").html("CONSTITUENCY WISE COMMITTEES");
		//$("#tableHeadingId").html("CONSTITUENCY");
		var mandTotal =0; 	
		var mandStarted =0; 	
		var mandCompleted =0; 	
		var mandMembers =0; 	
		var mandAfStarted =0; 	
		var mandAfCompleted =0;
		var panTotal =0; 	
		var panStarted =0; 	
		var panCompleted =0; 	
		var panMembers =0; 	
		var panAffStarted =0; 	
		var panAffCompleted =0;
		var percentage = 0;
		var perc = 0;
		
		var yuvathaStartedCount = 0;
		var mahilaStartedCount = 0;
		var rythuStartedCount = 0;
		var othersStartedCount = 0;
		var yuvathaCompltdCount = 0;
		var mahilaCompltdCount = 0;
		var rythuCompltdCount = 0;
		var othersCompltdCount = 0;
		
		
		var bcCellStartedCount = 0;
		var scCellStartedCount = 0;
		var stCellStartedCount = 0;
		var minorityStartedCount = 0;
		var CristianStartedCount = 0;
		var tnsfStartedCount = 0;
		var tntucStartedCount = 0;
		var tsnvStartedCount = 0;
		var legalCellStartedCount = 0;
		var doctorStartedCount = 0;
		var kalluGeethaStartedCount = 0;
		var chenethaStartedCount = 0;
		var rakshaVedikaStartedCount = 0;
		var tnusStartedCount = 0;
		var commercialStartedCount = 0;
		var culturalStartedCount = 0;
		var tradeStartedCount = 0;
		var  tradeCmpltdCount = 0;
		var  bcCellCmpltdCount = 0;
		var  scCellCmpltdCount = 0;
		var  stCellCmpltdCount = 0;
		var  minorityCmpltdCount = 0;
		var  CristianCmpltdCount = 0;
		var  tnsfCmpltdCount = 0;
		var  tntucCmpltdCount = 0;
		var  tsnvCmpltdCount = 0;
		var  legalCmpltdCount = 0;
		var  doctorCmpltdCount = 0;
		var  kalluGeethaCmpltdCount = 0;
		var  chenethaCmpltdCount = 0;
		var  rakshaVedikaCmpltdCount = 0;
		var  tnusCmpltdCount = 0;
		var  commercialCmpltdCount = 0;
		var  culturalCmpltdCount = 0;
		//alert(1234);
		var isConsiderAffl=  $('#considerAfflId').is(':checked')?"true":"false";
		console.log("isConsiderAffl  :"+isConsiderAffl);
		var str = '';
		$("#distSummaryBody").html("");
		if(result[0].stateList != null){
				var str3='';
				for(var i in result[0].stateList){
					str3+='<option value="'+result[0].stateList[i].stateId+'">'+result[0].stateList[i].stateName+'</option>';
				}
				$("#popUpStateId").html(str3);
			}
			
			if(result[0].districtWiseList != null){
				var str1='';
				str1+='<option value="0">All</option>';
				for(var i in result[0].districtWiseList){
					str1+='<option value="'+result[0].districtWiseList[i].districtId+'">'+result[0].districtWiseList[i].districtName+'</option>';
				}
				$("#popUpdistrictId").html(str1);
			}
			if(result[0].constinuncyList != null){
				var str2='';
				str2+='<option value="0">All</option>';
				for(var i in result[0].constinuncyList){
					str2+='<option value="'+result[0].constinuncyList[i].constiId+'">'+result[0].constinuncyList[i].name+'</option>';
					/* globalConstnciesIds.push(result[0].constinuncyList[i].constiId);
					globalConstncyNames.push(result[0].constinuncyList[i].name); */
				}
				$("#popUpConstiesId").html(str2);
			}
		str+='<table class="table table-bordered table-condensed " id="constiTableId" style="width:100%; background-color:rgba(0,0,0,0.1);">';
       
		if(mandalCheck == "true" && villageCheck == "true"){
			str+='<thead>';
            str+='<tr>';
			str+='<th rowspan="4" style="text-align:center">AC No</th>';
			str+='<th rowspan="4" style="text-align:center">AC Name</th>';
            if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center" colspan="47">TOWN / MANDAL / DIVISION </th>';
				}
				else
				{
					str+='<th style="text-align:center" colspan="15">TOWN / MANDAL / DIVISION </th>';
				}
            str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
			if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length-1)*2;
				str+=' <th style="text-align:center" colspan='+length+' rowspan="2">VILLAGE / WARD IVR DETAILS </th>';
				
				//var length1 = (result[0].cadreIVRVO.optionsList1.length-1)*2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS </th>';
			}
             str+='</tr>';
            str+='<tr>'; 
			if(mandalCheck == "true")
			{
				if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="43" class="affilCls"> Affiliated Committees </th>';
				}
				else
				{
					str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="11" class="affilCls"> Affiliated Committees </th>';
				}
			}
			if(villageCheck == "true")
			{
				str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
				str+='<th style="text-align:center;" colspan="2" class="affilCls"> Affiliated Committees </th>';
			}
			
			str+='</tr>';
            str+='<tr>';
            str+='<th rowspan="2" class="mainCls" >Total</th>';
			str+='<th  rowspan="2" class="mainCls" >Started</th>';
			str+='<th  rowspan="2" class="mainCls" >Completed</th>';
			str+='<th  rowspan="2" class="mainCls" >Members</th>';
			str+='<th rowspan="2" class="affilCls">Total</th>';
			str+='<th  rowspan="2" class="affilCls"> Started</th>';
			str+='<th  rowspan="2" class="affilCls"> Completed</th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
			str+='<th colspan="2"  class="affilCls" > Telugu Rythu </th>';
			if(isConsiderAffl =='true')
			{				
				str+='<th colspan="2" class="hideCls"> Trade  </th>';
				str+='<th colspan="2" class="hideCls"> BC Cell </th>';
				str+='<th colspan="2" class="hideCls"> SC Cell </th>';
				str+='<th colspan="2" class="hideCls"> ST Cell </th>';
				str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
				str+='<th colspan="2" class="hideCls"> Christian </th>';
				str+='<th colspan="2" class="hideCls"> TNSF </th>';
				str+='<th colspan="2" class="hideCls"> TNTUC </th>';
				str+='<th colspan="2" class="hideCls"> TSNV </th>';
				str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
				str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
				str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
				str+='<th colspan="2" class="hideCls"> Chenetha </th>';
				str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
				str+='<th colspan="2" class="hideCls"> TNUS   </th>';
				str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
				str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
			}
			else
			{
				str+='<th colspan="2"  class="affilCls" > Others </th>';
			}
			str+='<th  rowspan="2">Total</th>';
			str+='<th  rowspan="2">Started</th>';
			str+='<th  rowspan="2">Completed</th>';
			str+='<th  rowspan="2">Members</th>';
			str+='<th  rowspan="2"> Started</th>';
			str+='<th  rowspan="2"> Completed</th>';
            
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th  rowspan="2">'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th  rowspan="2">%</th>';
					}
				}
				
				/*for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}*/
			}	
			 str+='</tr>';
			 str+='<tr>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			
			if(isConsiderAffl == "true")
			{
				for(var z=0;z<17;z++)
				 {
					str+='<th  class="hideCls">Started</th>';
					str+='<th  class="hideCls">Completed</th>';
				 }
			}
			else
			{
				str+='<th class="affilCls">Started</th>';
				str+='<th class="affilCls">Completed</th>';
			}
			 
			str+='</tr>';	
			str+='</thead>';	
			
		}
		else if(mandalCheck == "true"){
			str+='<thead>';
			str+='<tr>';
			str+='<th rowspan="4"  style="text-align:center">AC No</th>';
			str+='<th rowspan="4" style="text-align:center">AC Name</th>';
		  if(isConsiderAffl =='true')
			{
				str+='<th style="text-align:center" colspan="47">TOWN / MANDAL / DIVISION </th>';
			}
			else
			{
				str+='<th style="text-align:center" colspan="15">TOWN / MANDAL / DIVISION </th>';
			}
			 if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length-1) *2;
				str+=' <th style="text-align:center" colspan='+length+' rowspan="2"> VILLAGE / WARD IVR DETAILS  </th>';
				
				//var length1 = (result[0].cadreIVRVO.optionsList1.length-1) *2;
				//str+=' <th style="text-align:center" colspan='+length1+'> WARD IVR DETAILS  </th>';
			}
            str+='</tr>';
            str+='<tr>'; 
			if(mandalCheck == "true")
			{
				if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="43" class="affilCls"> Affiliated Committees </th>';
				}
				else
				{
					str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="11" class="affilCls"> Affiliated Committees </th>';
				}
			}
			if(villageCheck == "true")
			{
				str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
				str+='<th style="text-align:center;" colspan="2" class="affilCls"> Affiliated Committees </th>';
			}
			
			str+='</tr>';
            str+='<tr>';
			str+='<th rowspan="2" class="mainCls" >Total</th>';
			str+='<th rowspan="2" class="mainCls" >Started</th>';
			str+='<th rowspan="2" class="mainCls" >Completed</th>';
			str+='<th rowspan="2" class="mainCls" >Members</th>';
			str+='<th rowspan="2" class="affilCls">Total</th>';
			str+='<th rowspan="2" class="affilCls"> Started</th>';
			str+='<th rowspan="2" class="affilCls"> Completed</th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
			str+='<th colspan="2"  class="affilCls" > Telugu Rythu </th>';
			if(isConsiderAffl =='true')
			{				
				str+='<th colspan="2" class="hideCls"> Trade  </th>';
				str+='<th colspan="2" class="hideCls"> BC Cell </th>';
				str+='<th colspan="2" class="hideCls"> SC Cell </th>';
				str+='<th colspan="2" class="hideCls"> ST Cell </th>';
				str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
				str+='<th colspan="2" class="hideCls"> Christian </th>';
				str+='<th colspan="2" class="hideCls"> TNSF </th>';
				str+='<th colspan="2" class="hideCls"> TNTUC </th>';
				str+='<th colspan="2" class="hideCls"> TSNV </th>';
				str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
				str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
				str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
				str+='<th colspan="2" class="hideCls"> Chenetha </th>';
				str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
				str+='<th colspan="2" class="hideCls"> TNUS   </th>';
				str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
				str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
			}
			else
			{
				str+='<th colspan="2"  class="affilCls" > Others </th>';
			}
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th  rowspan="2">'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th  rowspan="2">%</th>';
					}
				}
				/*
				for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				*/
			}
			 str+='</tr>';
			 str+='<tr>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			
			if(isConsiderAffl == "true")
			{
				for(var z=0;z<17;z++)
				 {
					str+='<th  class="hideCls">Started</th>';
					str+='<th  class="hideCls">Completed</th>';
				 }
			}
			else
			{
				str+='<th class="affilCls">Started</th>';
				str+='<th class="affilCls">Completed</th>';
			}
			 
			str+='</tr></thead>';	
		}
		else if(villageCheck == "true"){
			str+='<thead>';
			str+='<th rowspan="4"  style="text-align:center">AC No</th>';
			str+='<th rowspan="4" style="text-align:center">AC Name</th>';
    
			str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD  </th>';
			if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length-1)*2;
				str+=' <th style="text-align:center" colspan='+length+' rowspan="2">VILLAGE / WARD IVR DETAILS  </th>';
				
				//var length1 = (result[0].cadreIVRVO.optionsList1.length-1)*2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS  </th>';
				
			}
            str+='</tr>';
            str+='<tr>';
			
			if(villageCheck == "true")
			{
				str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
				str+='<th style="text-align:center;" colspan="2" class="affilCls"> Affiliated Committees </th>';
			}
				
			str+='</tr>';
            str+='<tr>';
			str+='</tr>';
            str+='<tr>';
			str+='<th class="mainCls" >Total</th>';
			str+='<th class="mainCls" >Started</th>';
			str+='<th class="mainCls" >Completed</th>';
			str+='<th class="mainCls" >Members</th>';
			str+='<th class="affilCls"> Started</th>';
			str+='<th class="affilCls"> Completed</th>';
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th>'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				
			/*	for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}*/
			}
			/* if(result[0].stateList != null){
				var str3='';
				for(var i in result[0].stateList){
					str3+='<option value="'+result[0].stateList[i].stateId+'">'+result[0].stateList[i].stateName+'</option>';
				}
				$("#popUpStateId").html(str3);
			}
			
			if(result[0].districtWiseList != null){
				var str1='';
				str1+='<option value="0">All</option>';
				for(var i in result[0].districtWiseList){
					str1+='<option value="'+result[0].districtWiseList[i].districtId+'">'+result[0].districtWiseList[i].districtName+'</option>';
				}
				$("#popUpdistrictId").html(str1);
			}
			if(result[0].constinuncyList != null){
				var str2='';
				str2+='<option value="0">All</option>';
				for(var i in result[0].constinuncyList){
					str2+='<option value="'+result[0].constinuncyList[i].constiId+'">'+result[0].constinuncyList[i].name+'</option>';
					/* globalConstnciesIds.push(result[0].constinuncyList[i].constiId);
					globalConstncyNames.push(result[0].constinuncyList[i].name); */
				/*}
				$("#popUpConstiesId").html(str2);
			} */
			str+='</thead>';	
		}
		
		str+='<tbody>';
		for(var i in result){
		if(result[i].townMandalDivisionVO != null || result[i].villageWardVO != null){
		str += '<tr id='+result[i].constiId+' class="removeCls1 clearCls1'+result[i].constiId+'">';
		str += '<td style="text-align:center" class="removeCls1 clearClsTD1'+result[i].constiId+'">'+result[i].constiNo+'</td>';
			str += '<td ><a onclick="getConstituencyWiseCommittesSummaryForMandal(\''+jObj.startDate+'\',\''+jObj.endDate+'\',\''+jObj.state+'\',\''+jObj.mandalCheck+'\',\''+jObj.villageCheck+'\',\''+result[i].constiId+'\',\''+result[i].name+'\');" style="color:#333333;font-weight:bold;cursor:pointer;"><span style="font-size: 12px;">'+result[i].name+'</span></a>';
				str += '&nbsp;&nbsp;<span style="cursor: pointer;" title="Click Here For '+result[i].name+' Committee Summary Report" onclick="getPopUpForSummary('+result[i].constiId+',\''+result[i].name+'\');" class="glyphicon glyphicon-dashboard"></span>&nbsp;&nbsp;<span style="cursor: pointer;"  onclick="showAdvanceDashBoard('+result[i].constiId+');" title="Click Here For '+result[i].name+' Advance Dashboard"  class="glyphicon glyphicon-list-alt"></span>';
			
			str += '</td>';
			if(mandalCheck == "true"){
			
			if(result[i].townMandalDivisionVO!=null){				
				if(result[i].townMandalDivisionVO.totalCommittees!=null){
					str += '<td style="text-align:center" ><span style="cursor: pointer;color:green;"  onclick="getPopUpForSummary('+result[i].constiId+',\''+result[i].name+'\',\'mandal\');">'+result[i].townMandalDivisionVO.totalCommittees+'</span></td>';
					mandTotal=mandTotal+result[i].townMandalDivisionVO.totalCommittees;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainStarted!=null){
					//str += '<td>'+result[i].townMandalDivisionVO.mainStarted+'<span id="mini-pie-chart-constituency'+i+'" class="pull-right mini-pie-chart-district"></span></td>';
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mainStarted+'</td>';
					mandStarted=mandStarted+result[i].townMandalDivisionVO.mainStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mainCompleted+'</td>';
					mandCompleted=mandCompleted+result[i].townMandalDivisionVO.mainCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.membersCount!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.membersCount+'</td>';
					mandMembers=mandMembers+result[i].townMandalDivisionVO.membersCount;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				if(result[i].townMandalDivisionVO.totalCommittees!=null){
					str += '<td style="text-align:center" >'+((result[i].townMandalDivisionVO.totalCommittees)*20)+'</td>';
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				if(result[i].townMandalDivisionVO.afflStarted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.afflStarted+'</td>';
					mandAfStarted=mandAfStarted+result[i].townMandalDivisionVO.afflStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.afflCompleted+' </td>';
					mandAfCompleted=mandAfCompleted+result[i].townMandalDivisionVO.afflCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.youvathaStarted != null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.youvathaStarted+' </td>';
					yuvathaStartedCount = yuvathaStartedCount+result[i].townMandalDivisionVO.youvathaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.youvathaCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.youvathaCmpltd+' </td>';
					yuvathaCompltdCount = yuvathaCompltdCount+result[i].townMandalDivisionVO.youvathaCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mahilaStarted != null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mahilaStarted+' </td>';
					mahilaStartedCount = mahilaStartedCount+result[i].townMandalDivisionVO.mahilaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mahilaCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mahilaCmpltd+' </td>';
					mahilaCompltdCount = mahilaCompltdCount+result[i].townMandalDivisionVO.mahilaCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				
				if(result[i].townMandalDivisionVO.rythuStarted != null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.rythuStarted+' </td>';
						rythuStartedCount = rythuStartedCount+result[i].townMandalDivisionVO.rythuStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.rythuCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.rythuCmpltd+' </td>';
						rythuCompltdCount = rythuCompltdCount+result[i].townMandalDivisionVO.rythuCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(isConsiderAffl == "true")
				{
					
						if(result[i].townMandalDivisionVO.tradeStarted != null){
							str += '<td style="text-align:center"  >'+result[i].townMandalDivisionVO.tradeStarted+' </td>';
							tradeStartedCount = tradeStartedCount+result[i].townMandalDivisionVO.tradeStarted;
						}else{
							str += '<td style="text-align:center"  > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tradeCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tradeCmpltd+' </td>';
							tradeCmpltdCount = tradeCmpltdCount+result[i].townMandalDivisionVO.tradeCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}

						if(result[i].townMandalDivisionVO.bcCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.bcCellStarted+' </td>';
							bcCellStartedCount = bcCellStartedCount+result[i].townMandalDivisionVO.bcCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.bcCellCmpltd!=null){
							str += '<td style="text-align:center">'+result[i].townMandalDivisionVO.bcCellCmpltd+' </td>';
							bcCellCmpltdCount = bcCellCmpltdCount+result[i].townMandalDivisionVO.bcCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.scCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.scCellStarted+' </td>';
							scCellStartedCount = scCellStartedCount+result[i].townMandalDivisionVO.scCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.scCellCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.scCellCmpltd+' </td>';
							scCellCmpltdCount = scCellCmpltdCount+result[i].townMandalDivisionVO.scCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.stCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.stCellStarted+' </td>';
							stCellStartedCount = stCellStartedCount+result[i].townMandalDivisionVO.stCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.stCellCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.stCellCmpltd+' </td>';
							stCellCmpltdCount = stCellCmpltdCount+result[i].townMandalDivisionVO.stCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.minorityStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.minorityStarted+' </td>';
							minorityStartedCount = minorityStartedCount+result[i].townMandalDivisionVO.minorityStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.minorityCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.minorityCmpltd+' </td>';
							minorityCmpltdCount = minorityCmpltdCount+result[i].townMandalDivisionVO.minorityCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.CristianStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.CristianStarted+' </td>';
							CristianStartedCount = CristianStartedCount+result[i].townMandalDivisionVO.CristianStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.CristianCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.CristianCmpltd+' </td>';
							CristianCmpltdCount = CristianCmpltdCount+result[i].townMandalDivisionVO.CristianCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tnsfStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tnsfStarted+' </td>';
							tnsfStartedCount = tnsfStartedCount+result[i].townMandalDivisionVO.tnsfStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tnsfCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tnsfCmpltd+' </td>';
							tnsfCmpltdCount = tnsfCmpltdCount+result[i].townMandalDivisionVO.tnsfCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tntucStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tntucStarted+' </td>';
							tntucStartedCount = tntucStartedCount+result[i].townMandalDivisionVO.tntucStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tntucCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tntucCmpltd+' </td>';
							tntucCmpltdCount = tntucCmpltdCount+result[i].townMandalDivisionVO.tntucCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tsnvStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tsnvStarted+' </td>';
							tsnvStartedCount = tsnvStartedCount+result[i].townMandalDivisionVO.tsnvStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tsnvCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tsnvCmpltd+' </td>';
							tsnvCmpltdCount = tsnvCmpltdCount+result[i].townMandalDivisionVO.tsnvCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.legalCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.legalCellStarted+' </td>';
							legalCellStartedCount = legalCellStartedCount+result[i].townMandalDivisionVO.legalCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.legalCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.legalCmpltd+' </td>';
							legalCmpltdCount = legalCmpltdCount+result[i].townMandalDivisionVO.legalCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.doctorStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.doctorStarted+' </td>';
							doctorStartedCount = doctorStartedCount+result[i].townMandalDivisionVO.doctorStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.doctorCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.doctorCmpltd+' </td>';
							doctorCmpltdCount = doctorCmpltdCount+result[i].townMandalDivisionVO.doctorCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.kalluGeethaStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.kalluGeethaStarted+' </td>';
							kalluGeethaStartedCount = kalluGeethaStartedCount+result[i].townMandalDivisionVO.kalluGeethaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.kalluGeethaCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.kalluGeethaCmpltd+' </td>';
							kalluGeethaCmpltdCount = kalluGeethaCmpltdCount+result[i].townMandalDivisionVO.kalluGeethaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.chenethaStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.chenethaStarted+' </td>';
							chenethaStartedCount = chenethaStartedCount+result[i].townMandalDivisionVO.chenethaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.chenethaCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.chenethaCmpltd+' </td>';
							chenethaCmpltdCount = chenethaCmpltdCount+result[i].townMandalDivisionVO.chenethaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.rakshaVedikaStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.rakshaVedikaStarted+' </td>';
							rakshaVedikaStartedCount = rakshaVedikaStartedCount+result[i].townMandalDivisionVO.rakshaVedikaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.rakshaVedikaCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.rakshaVedikaCmpltd+' </td>';
							rakshaVedikaCmpltdCount = rakshaVedikaCmpltdCount+result[i].townMandalDivisionVO.rakshaVedikaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tnusStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tnusStarted+' </td>';
							tnusStartedCount = tnusStartedCount+result[i].townMandalDivisionVO.tnusStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tnusCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tnusCmpltd+' </td>';
							tnusCmpltdCount = tnusCmpltdCount+result[i].townMandalDivisionVO.tnusCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.commercialStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.commercialStarted+' </td>';
							commercialStartedCount = commercialStartedCount+result[i].townMandalDivisionVO.commercialStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.commercialCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.commercialCmpltd+' </td>';
							commercialCmpltdCount = commercialCmpltdCount+result[i].townMandalDivisionVO.commercialCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.culturalStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.culturalStarted+' </td>';
							culturalStartedCount = culturalStartedCount+result[i].townMandalDivisionVO.culturalStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.culturalCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.culturalCmpltd+' </td>';
							culturalCmpltdCount = culturalCmpltdCount+result[i].townMandalDivisionVO.culturalCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
				}
				else
				{
					if(result[i].townMandalDivisionVO.othersStarted != null){
						str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.othersStarted+' </td>';
						othersStartedCount = othersStartedCount+result[i].townMandalDivisionVO.othersStarted;
					}else{
						str += '<td style="text-align:center" > - </td>';
					}
					
					if(result[i].townMandalDivisionVO.othersCmpltd!=null){
						str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.othersCmpltd+' </td>';
						othersCompltdCount = othersCompltdCount+result[i].townMandalDivisionVO.othersCmpltd;
					}else{
						str += '<td style="text-align:center" > - </td>';
					}
				}
				
			}else{
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				if(isConsiderAffl == "true")
				{
					for(var z=0;z<17;z++)
					 {
						str += '<td>  </td>';
						str += '<td>  </td>';
					 }
				}
				else
				{
					str += '<td>  </td>';
					str += '<td>  </td>';				
				}
			}
			}
			if(villageCheck == "true"){
			
			if(result[i].villageWardVO!=null){
				if(result[i].villageWardVO.totalCommittees!=null){
					str += '<td style="text-align:center"  ><span style="cursor: pointer;color:green;"  onclick="getPopUpForSummary('+result[i].constiId+',\''+result[i].name+'\',\'village\');">'+result[i].villageWardVO.totalCommittees+'</span></td>';
					panTotal=panTotal+result[i].villageWardVO.totalCommittees;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.mainStarted!=null){
					//str += '<td>'+result[i].villageWardVO.mainStarted+'<span id="mini-pie-chart-constiVillage'+i+'" class="pull-right mini-pie-chart-village"></span></td>';
					str += '<td style="text-align:center" >'+result[i].villageWardVO.mainStarted+'</td>';
					panStarted=panStarted+result[i].villageWardVO.mainStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.mainCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.mainCompleted+'</td>';
					panCompleted=panCompleted+result[i].villageWardVO.mainCompleted;
				}else{
					str += '<td > - </td>';
				}
				
				if(result[i].villageWardVO.membersCount!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.membersCount+' </td>';
					panMembers=panMembers+result[i].villageWardVO.membersCount;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.afflStarted!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.afflStarted+'</td>';
					panAffStarted=panAffStarted+result[i].villageWardVO.afflStarted;
				}else{
					str += '<td style="text-align:center" >  - </td>';
				}
				
				if(result[i].villageWardVO.afflCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.afflCompleted+' </td>';
					panAffCompleted=panAffCompleted+result[i].villageWardVO.afflCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
			}else{
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
			}
			}
			if(result[i].cadreIVRVO != null)
			{
				
				for(var tp in result[i].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[tp].id != 8){
					if(result[i].cadreIVRVO.total >0){
					 percentage = (result[i].cadreIVRVO.optionsList[tp].count *100)/ result[i].cadreIVRVO.total;
					 perc = percentage.toFixed(0);
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList[tp].count+'</td>';
					str+='<td style="text-align:center">'+perc+'</td>';
					}				
					else{
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList[tp].count+'</td>';
					str+='<td style="text-align:center">0</td>';
					}
					}					
				}
				
			/*	for(var tp in result[i].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[tp].id != 13){
					if(result[i].cadreIVRVO.total >0){
					 percentage = (result[i].cadreIVRVO.optionsList1[tp].count *100)/ result[i].cadreIVRVO.total;
					 perc = percentage.toFixed(0);
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList1[tp].count+'</td>';
					str+='<td style="text-align:center">'+perc+'</td>';
					}				
					else{
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList1[tp].count+'</td>';
					str+='<td style="text-align:center">0</td>';
					}
					}
				}
				*/
			}
			
			
		
			str += '</tr>';
			
			if(result[i].townMandalDivisionVO != null){
				var details = [result[i].townMandalDivisionVO.totalCommittees, result[i].townMandalDivisionVO.mainStarted];
				constiInfoArr.push(details);
			}else{
				 var details = [0, 0];
				constiInfoArr.push(details);
			}
			
			
			if(result[i].villageWardVO != null){
				var villageDetails  = [result[i].villageWardVO.totalCommittees, result[i].villageWardVO.mainStarted];
				constiVillageInfoArr.push(villageDetails);
			}else{
				var villageDetails  = [0, 0];
				constiVillageInfoArr.push(villageDetails);
			}
			
			
		}
		}
   
	    str += '</tbody><tfoot><tr class="no-sort" style="font-weight:bold;"><td></td>';
	if(mandalCheck=="true" && villageCheck=="true"){
		str	+= '<td style="text-align:center">TOTAL</td><td >'+mandTotal+'</td>'; 	
		str += '<td style="text-align:center" >'+mandStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandMembers+'</td>'; 
		str += '<td style="text-align:center" >'+(mandTotal * 20 )+'</td>';		
		str += '<td style="text-align:center" >'+mandAfStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfCompleted+'</td>';
			
		str += '<td style="text-align:center" >'+yuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+yuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+rythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+rythuCompltdCount+'</td>';
			
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+tradeStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+stCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+stCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+tnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+legalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+legalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+chenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+chenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+commercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+commercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+othersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+othersCompltdCount+'</td>';
			}
			
		str += '<td style="text-align:center">'+panTotal+'</td>';
		str += '<td style="text-align:center">'+panStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panCompleted+'</td>'; 	
		str += '<td style="text-align:center">'+panMembers+'</td>'; 	
		str += '<td style="text-align:center">'+panAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panAffCompleted+'</td>'; 	
	}	
	else if(mandalCheck=="true"){
		str	+= '<td style="text-align:center">TOTAL</td><td >'+mandTotal+'</td>'; 	
		str += '<td style="text-align:center" >'+mandStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandMembers+'</td>';
		str += '<td style="text-align:center" >'+(mandTotal * 20 )+'</td>';		
		str += '<td style="text-align:center" >'+mandAfStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+yuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+yuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+rythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+rythuCompltdCount+'</td>';
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+tradeStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+stCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+stCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+tnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+legalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+legalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+chenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+chenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+commercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+commercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+othersStartedCount+'</td>';
				str += '<td style="text-align:center" class="affilCls">'+othersCompltdCount+'</td>';
			} 
		
	}
	else if(villageCheck=="true"){
		str	+= '<td style="text-align:center">TOTAL</td><td>'+panTotal+'</td>'; 	
		str += '<td style="text-align:center">'+panStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panCompleted+'</td>'; 	
		str += '<td style="text-align:center">'+panMembers+'</td>'; 	
		str += '<td style="text-align:center">'+panAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panAffCompleted+'</td>'; 	
	}
		str += '</tr></tfoot></table>';
		
		//console.log("sridhar");
		$("#distSummaryBody").html(str);
		$("#constiTableId").dataTable({
			"iDisplayLength": 20,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
		
		if( $('.mini-pie-chart-constiVillage').length > 0 ) {
			var visitData2 = constiVillageInfoArr;
			var params = {
				type: "pie",
				 sliceColors: ["#0B3B0B", "#B18904"]

			}
			for(var e in result){
				$('#mini-pie-chart-constiVillage'+e+'').sparkline(visitData2[e], params);
			}
		}
		
		
		if($('.mini-pie-chart-constituency').length > 0 ) {
			var visitData = constiInfoArr;
			var params = {
				type: "pie",
				 sliceColors: ["#0B3B0B", "#B18904"]

			}
			for(var t in result){
			
				$('#mini-pie-chart-constituency'+t+'').sparkline(visitData[t], params);
			}
		}
	}
	
	
	/* script for District */
	var globalDistrictId =0;
	function getPopUpForSummaryForDistrict(id,name,type){
		globalDistrictId=id;
		//$("#popUpdistrictId  option").remove();
		$("#popUpConstiesId option").remove();
		$("#popUpConstiesId").append('<option value="0">All</option>');
	$("#districtContent").show();
	$("#constituencyContent").hide();
		$("#dialogSummary" ).modal("show");
		$("#CommitteeDetails").html(""); 
		$("#committeeMemberDiv").html("");
		$("#presGenSecrErrDivId").html("");
		$("#mainCommTitleDivId").html(name.toUpperCase()+" COMMITTEE SUMMARY");
		var selectedTdpCommitteeYearId =$("#tdpCommitteeYearId").val();
		$("#tdpCommitteeYearId1").val(selectedTdpCommitteeYearId);
		var selectedDate = $("#reportrange").val();
		$("#reportrange1").val(selectedDate);
		var committeeEnrollmentId =$("#tdpCommitteeYearId").val();
				var date =$("#reportrange").val();
				var fromDate;
				var toDate;
				var dates = date.split("-");
				fromDate = dates[0];
				toDate = dates[1];
		getCommitteeSummaryInfo(id,committeeEnrollmentId,fromDate,toDate,"District");
		$('.loader5').ClassyLoader({
				speed: 10,
				diameter: 80,
				fontSize: '30px',
				fontFamily: 'Courier',
				fontColor: 'rgba(0,0,0,0.4)',
				lineColor: 'rgba(0,166,81,0.9)',
				percentage: 80,
				lineWidth: 15,
				remainingLineColor: 'rgba(0,0,0,0.5)',
				
			});
			$('.loader4').ClassyLoader({
				speed: 10,
				diameter: 80,
				fontSize: '30px',
				fontFamily: 'Courier',
				fontColor: 'rgba(0,0,0,0.4)',
				lineColor: 'rgba(0,166,81,0.9)',
				percentage: 40,
				lineWidth: 15,
				remainingLineColor: 'rgba(0,0,0,0.5)',
				
			});
	}
	
		function getCommitteeSummaryInfo(id,committeeEnrollmentId,fromDate,toDate,reqLocationType)
		{
		 $("#mainCommitteDivId").html('<img id=""  class="ajximgCls" style="margin-center: 10px;width:80px;margin-left:600px;" src="images/Loading-data.gif"/>');
		 $("#AffliCommitteDivId").html('');
		 $("#CommitteeDetails").html("");
		 $("#conformedBtn").html(""); 
         $("#stateDistrictTitle").html("");	
		$("#cadreDetailsDiv").html("");	
		//$("#districtContent").html(''		
				var reqLocationType = reqLocationType;//'District';
				var locationId = id;
				var committeeLevelIdsListArr = [];
				committeeLevelIdsListArr.push(6);
				committeeLevelIdsListArr.push(8);
				
				var mainOrAfflCommitteIdsArr = []; 
				mainOrAfflCommitteIdsArr.push(1);
				mainOrAfflCommitteIdsArr.push(2);
			var jsObj = {
					locationId:id,
					reqLocationType :reqLocationType,
					committeeEnrollmentId :[committeeEnrollmentId],
					startDate : fromDate,
					endDate   : toDate,
					committeeLevelIdsList:committeeLevelIdsListArr,
					mainOrAfflCommitteIds:mainOrAfflCommitteIdsArr,
					task:""
				}
				//alert(666)	;
			$.ajax({
				type : "POST",
				url : "gettingCommitteeSummaryInfoAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				var typeId = 0;
					if(reqLocationType == "State" || reqLocationType == "Constituency"){
						typeId = 1;
					}
				//NAME BUILDING
				if(result[0].locationId == 10){
					$("#stateDistrictTitle").html(''+result[0].locationName+'');
				}
				if(result[0].locationId == 11){
					$("#stateDistrictTitle").html(''+result[0].locationName+'');
				}
				
				if(result!=null && result[0] != null){
					if(result[0].mainCommitteesExist == false && result[0].affliatedCommitteesExist == false){
						$("#mainCommitteDivId").html("NO DATA AVAILABLE..");
						return;
					}
					//Building main committees
					if(result[0].mainCommitteesExist == true){
						var notStarted=result[0].mainComittees-(result[0].startedCount+result[0].mainComitteesConformed);
						var str='';
						str+='<table class="table table-condensed table-yellow-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
							str+='<tr><td colspan="4" style="background-color:#669934"><h4>MAIN COMMITTEE</h4></td></tr>';
							str+='<tr><td width="25%"><h2 style="display:inline;">'+result[0].mainComittees+'</h2> TOTAL</td>';
							if(result[0].startedCount != null && result[0].startedCount > 0){
							   str+='<td width="25%"><h2 style="display:inline;" ><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Started\',\''+result[0].locationId+'\',\''+id+'\','+typeId+')">'+result[0].startedCount+'</a></h2> STARTED</td>';
							}else{
								str+='<td width="25%"><h2 style="display:inline;" >'+result[0].startedCount+'</h2> STARTED</td>';
							}
							if(result[0].mainComitteesConformed != null && result[0].mainComitteesConformed > 0){
							  str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Conform\',\''+result[0].locationId+'\',\''+id+'\','+typeId+')">'+result[0].mainComitteesConformed+'</a></h2> CONFIRMED</td>';
							}else{
								str+='<td width="25%"><h2 style="display:inline;">'+result[0].mainComitteesConformed+'</h2> CONFIRMED</td>';
							}		
							if(notStarted != null && notStarted > 0){
							   str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'NotStarted\',\''+result[0].locationId+'\',\''+id+'\','+typeId+')">'+notStarted+'</a></h2> NOT YET STARTED</td>';
							}else{
								str+='<td width="25%"><h2 style="display:inline;">'+notStarted+'</h2> NOT YET STARTED</td>';
							}
							str+='</tr>';
						 str+='</table>';
						 $("#mainCommitteDivId").html(str);
					}else{
						var str='';
						str+='<table class="table table-condensed table-yellow-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
						str+='<tr><td colspan="4" style="background-color:#669934"><h4>MAIN COMMITTEE</h4></td></tr>';
						str+='<tr><td> NO DATA AVAILABLE</td></tr>';
						str+='</table>';
					   $("#mainCommitteDivId").html(str);	
					}
					 
			        //Building Affliated committees.
					if(result[0].affliatedCommitteesExist == true){
						 var str1='';
						str1+='<table class="table table-condensed table-yellow-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
						str1+='<thead><th colspan="5" style="background-color:#669934"><h4>AFFILIATED COMMITTEE</h4></th></thead>';
						str1+='<thead><th width="20%">COMMITTEE TYPE</th><th width="20%">TOTAL</th><th width="20%">STARTED</th>';
						str1+='<th width="20%">CONFIRMED/COMPLETED</th><th width="20%">NOT YET STARTED</th></thead>';
					   for(var i in result){
						 var notConformed=result[i].totalAffilatedCommittees-(result[i].affilatedStartedCount+result[i].affComitteesConformed); 	
						  str1+='<tr><td width="20%">'+result[i].affilatedCommitteeName+'</td>';
						  str1+='<td width="20%">'+result[i].totalAffilatedCommittees+'</td>';
						  if(result[i].affilatedStartedCount != null && result[i].affilatedStartedCount > 0){
		                    str1+='<td width="20%">';
		                    str1+='<a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Started\',\''+result[0].locationId+'\',\''+id+'\','+typeId+')" style="cursor:pointer;">'+result[i].affilatedStartedCount+'</a></td>';
						  }else{
						    str1+='<td width="20%">'+result[i].affilatedStartedCount+'</td>';
						  }
					      if(result[i].affComitteesConformed != null && result[i].affComitteesConformed > 0){
						    str1+='<td width="20%">';
						    str1+='<a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Conform\',\''+result[0].locationId+'\',\''+id+'\','+typeId+')" style="cursor:pointer;">'+result[i].affComitteesConformed+'</a></td>';
						  }
						 else{
							str1+='<td width="20%">'+result[i].affComitteesConformed+'</td>'; 
						 }
						 if(notConformed != null && notConformed > 0){
							 str1+='<td width="20%"><a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'NotStarted\',\''+result[0].locationId+'\',\''+id+'\','+typeId+')" style="cursor:pointer;">'+notConformed+'</td>';
						 }
						 else{
						   str1+='<td width="20%">'+notConformed+'</td>';
						 }
						 str1+='</tr>';
					 }
					 str1+='</table>';
					 $("#AffliCommitteDivId").html(str1);
					}else{
						 var str1='';
						str1+='<table class="table table-condensed table-yellow-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
						str1+='<thead><th colspan="5" style="background-color:#669934"><h4>AFFILIATED COMMITTEE</h4></th></thead>';
						str1+='<tbody>'
						str1+='<tr><td> NO DATA AVAILABLE</td></tr>';
						str1+='</table>';
						$("#AffliCommitteDivId").html(str1);
					} 
				 }else{
					 $("#mainCommitteDivId").html("NO DATA AVAILABLE..");
				 }
			    });
		}
	
	/* end */
	/* Script For Summary POpUP  */
	
	var GlobalLocationId = 0;
	function getPopUpForSummary(id,name,levelType){
	//	$("#districtContent").hide();
		$("#constituencyContent").show();
		$( "#dialogSummary" ).modal("show");
		$("#presGenSecrErrDivId").html("");
		$("#CommitteeDetails").html(""); 
		$("#committeeMemberDiv").html("");
		$("#mainCommTitleDivId").html(name.toUpperCase()+" COMMITTEE SUMMARY");
		var committeeLevelIdListArr = [];
			
		if(levelType == "mandal"){
			committeeLevelIdListArr.push(5);
			committeeLevelIdListArr.push(7);
			committeeLevelIdListArr.push(9);
		}else if(levelType == "village"){
			committeeLevelIdListArr.push(6);
			committeeLevelIdListArr.push(8);
		}
		if(id == 0){
			getSummary(GlobalLocationId,"Constituency",committeeLevelIdListArr);
			getMandalMuncipalDivisonStartedCommittees(GlobalLocationId,committeeLevelIdListArr);
		}else{
			GlobalLocationId = id;
			getSummary(id,"Constituency",committeeLevelIdListArr);
			getMandalMuncipalDivisonStartedCommittees(id,committeeLevelIdListArr);
		}
		
		
	
		$('.loader5').ClassyLoader({
				speed: 10,
				diameter: 80,
				fontSize: '30px',
				fontFamily: 'Courier',
				fontColor: 'rgba(0,0,0,0.4)',
				lineColor: 'rgba(0,166,81,0.9)',
				percentage: 80,
				lineWidth: 15,
				remainingLineColor: 'rgba(0,0,0,0.5)',
				
			});
			$('.loader4').ClassyLoader({
				speed: 10,
				diameter: 80,
				fontSize: '30px',
				fontFamily: 'Courier',
				fontColor: 'rgba(0,0,0,0.4)',
				lineColor: 'rgba(0,166,81,0.9)',
				percentage: 40,
				lineWidth: 15,
				remainingLineColor: 'rgba(0,0,0,0.5)',
				
			});
	}
	var glblSummeryCommitteeLvlIds = [];
	function getSummary(id,reqLoctionType,committeeLevelIdListArr){
		$("#villageDivId").hide();
		$("#mandalDivId").hide();
		glblSummeryCommitteeLvlIds=committeeLevelIdListArr;
		$("#CommitteeDetails").html(""); 
		$("#stateDistrictTitle").html(""); 
		$("#districtContent").hide();
		$("#constituencyContent").show();
		$("#committeeMemberDiv").html("");
		var Id=id;
		var committeeEnrollmentId =$("#tdpCommitteeYearId1").val();
		 var dateStr = $('#reportrange').val();		
		 var dateStrArr = dateStr.split('-');
		 var fromDate = dateStrArr[0];
		var toDate = dateStrArr[1];
			
			var mainOrAfflCommitteIdsArr = []; 
			mainOrAfflCommitteIdsArr.push(1);
			mainOrAfflCommitteIdsArr.push(2);
			
		var jObj={
			constituencyId:Id ,
			locationId:id,
			reqLocationType :reqLoctionType,//"constituency",
			committeeEnrollmentId :[committeeEnrollmentId],
			startDate : fromDate,
			endDate   : toDate,
			levelIds : committeeLevelIdListArr,
			
			task:""
		}
		 $("#villageMainTableDivId").html("");
		 $("#villageAfflicatedTableDivId").html("");
		// alert(777)	;
		$.ajax({
				type : "POST",
				url : "getSummaryActionPopUp.action",
				data : {task:JSON.stringify(jObj)} ,
			}).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				var levelId  = 0;
				if(committeeLevelIdListArr.length == 2){
					$("#villageDivId").show();
					levelId  = 2;
				}else if(committeeLevelIdListArr.length == 3){
					$("#mandalDivId").show();
					levelId  = 1;
				}else{
					levelId = committeeLevelIdListArr[0];
				}
				if(result!=null){
					var typeId = 0;
					if(reqLoctionType == "State" || reqLoctionType == "District"){
						typeId = 1;
					}
					var notStarted=result[0].mainComittees-(result[0].startedCount+result[0].mainComitteesConformed);
					var str='';
					str+='<table class="table table-condensed table-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
						str+='<tr><td colspan="4" style="background-color:#669934"><h4>MAIN COMMITTEE</h4></td></tr>';
								str+='<tr><td width="25%"><h2 style="display:inline;">'+result[0].mainComittees+'</h2> TOTAL</td>';
							  if(result[0].startedCount > 0)
							  str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Started\',\''+levelId+'\','+id+','+typeId+')">'+result[0].startedCount+'</a></h2> STARTED</td>';
								else
								{
									str+='<td width="25%"><h2 style="display:inline;">'+result[0].startedCount+'</h2> STARTED</td>';
								}
							  if(result[0].mainComitteesConformed > 0)
							{
									 str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Conform\',\''+levelId+'\','+id+','+typeId+')">'+result[0].mainComitteesConformed+'</a></h2> CONFIRMED</td>';
							}
									else
									{
							   str+='<td width="25%"><h2 style="display:inline;">'+result[0].mainComitteesConformed+'</h2> CONFIRMED</td>';
									}
									if(notStarted > 0)
									{
										str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'NotStarted\',\''+levelId+'\','+id+','+typeId+')">'+notStarted+'</a></h2> NOT YET STARTED</td>';
									}
										else
								{
							   str+='<td width="25%"><h2 style="display:inline;">'+notStarted+'</h2> NOT YET STARTED</td>';
								}
								str+='</tr>';
					 str+='</table>';
					 $("#villageMainTableDivId").html(str);
			
					var str1='';
						str1+='<table class="table table-condensed table-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
							str1+='<thead><th colspan="5" style="background-color:#669934"><h4>AFFILIATED COMMITTEE</h4></th></thead>';
							str1+='<thead><th width="20%">COMMITTEE TYPE</th><th width="20%">TOTAL</th><th width="20%">STARTED</th>';
							str1+='<th width="20%">CONFIRMED/COMPLETED</th><th width="20%">NOT YET STARTED</th></thead>';
					 for(var i in result){
						 if(result[i].affilatedCommitteeName != null){
							var notConformed=result[i].totalAffilatedCommittees-(result[i].affilatedStartedCount+result[i].affComitteesConformed); 	
							str1+='<tr><td width="20%">'+result[i].affilatedCommitteeName+'</td>';
							str1+='<td width="20%">'+result[i].totalAffilatedCommittees+'</td>';
							if(result[i].affilatedStartedCount != null && result[i].affilatedStartedCount > 0)
							 {
								str1+='<td width="20%"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Started\',\''+levelId+'\','+id+','+typeId+')">'+result[i].affilatedStartedCount+'</a></td>';
							 }
								else
							 {
							str1+='<td width="20%">'+result[i].affilatedStartedCount+'</td>';
							 }
							
							 if(result[i].affComitteesConformed != null && result[i].affComitteesConformed > 0)
							 {
							 str1+='<td width="20%"> <a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Conform\',\''+levelId+'\','+id+','+typeId+')">'+result[i].affComitteesConformed+'</a></td>';
							 }
							else
							 {
							str1+='<td width="20%">'+result[i].affComitteesConformed+'</td>';
							 }
							 if(notConformed > 0)
							 {
								 str1+='<td width="20%"> <a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'NotStarted\',\''+levelId+'\','+id+','+typeId+')">'+notConformed+'</a></td>';
							 }
								 else
							 {
							str1+='<td width="20%">'+notConformed+'</td>';
							 }
							str1+='</tr>';
						 }
					 }
					 
					 str1+='</table>';
					  $("#villageAfflicatedTableDivId").html(str1);
				}
			});
		}
	/*	function getStartedCommittees(){
			$.ajax({
					type : "POST",
					url : "getStartedCommitteesAction.action",
					data : {} ,
				}).done(function(result){
					console.log(result);
				});
		} */
	
		function getMandalMuncipalDivisonStartedCommittees(id,committeeLevelIdsListArr){
			$("#CommitteeDetails").html(""); 
			$("#committeeMemberDiv").html("");
			var Id = id;
			var committeeEnrollmentId =$("#tdpCommitteeYearId1").val();
		
		var dateStr = $('#reportrange').val();		
			var dateStrArr = dateStr.split('-');
			var fromDate = dateStrArr[0];
			var toDate = dateStrArr[1];
				
				var mainOrAfflCommitteIdsArr = []; 
				mainOrAfflCommitteIdsArr.push(1);
				mainOrAfflCommitteIdsArr.push(2);
				
			var jObj={
				constituencyId:Id ,
				locationId:id,
				reqLocationType :"constituency",
				committeeEnrollmentId :[committeeEnrollmentId],
				startDate : fromDate,
				endDate   : toDate,
				levelIds:committeeLevelIdsListArr,
				task:""
			}
			
			$("#mandalMainCommitteDivId").html("");
			 $("#mandalAffliCommitteDivId").html("");
			//alert(888)	;
			$.ajax({
				type : "POST",
				url : "gettingMandalMuncipalDivisonSummaryPopUpAction.action",
				data : {task:JSON.stringify(jObj)} ,
			}).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				if(result!=null && result.length >0){
					var notStarted=result[0].mainComittees-(result[0].startedCount+result[0].mainComitteesConformed);
					var str='';
					str+='<table class="table table-condensed table-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
						str+='<tr><td colspan="4" style="background-color:#669934"><h4>MAIN COMMITTEE</h4></td></tr>';
								str+='<tr><td width="25%"><h2 style="display:inline;">'+result[0].mainComittees+'</h2> TOTAL</td>';
							   if(result[0].startedCount != null && result[0].startedCount > 0)
								{
							   str+='<td width="25%"><h2 style="display:inline;" ><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Started\',1,'+id+',0)">'+result[0].startedCount+'</a></h2> STARTED</td>';
								}
								else
								{
									 str+='<td width="25%"><h2 style="display:inline;" >'+result[0].startedCount+'</h2> STARTED</td>';
								
								}
								if(result[0].mainComitteesConformed != null && result[0].mainComitteesConformed > 0)
								{
									  str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Conform\',1,'+id+',0)">'+result[0].mainComitteesConformed+'</a></h2> CONFIRMED</td>';
									
								}
								else
								{
							   str+='<td width="25%"><h2 style="display:inline;">'+result[0].mainComitteesConformed+'</h2> CONFIRMED</td>';
								}
								if(notStarted != null && notStarted > 0)
								{
							   str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'NotStarted\',1,'+id+',0)">'+notStarted+'</a></h2> NOT YET STARTED</td>';
								}
								else
								{
									str+='<td width="25%"><h2 style="display:inline;">'+notStarted+'</h2> NOT YET STARTED</td>';
								}
								str+='</tr>';
							  
					 str+='</table>';
					 $("#mandalMainCommitteDivId").html(str);
			
				var str1='';
						str1+='<table class="table table-condensed table-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
							str1+='<thead><th colspan="5" style="background-color:#669934"><h4>AFFILIATED COMMITTEE</h4></th></thead>';
							str1+='<thead><th width="20%">COMMITTEE TYPE</th><th width="20%">TOTAL</th><th width="20%">STARTED</th>';
							str1+='<th width="20%">CONFIRMED/COMPLETED</th><th width="20%">NOT YET STARTED</th></thead>';
					 for(var i in result){
						 if(result[i].affilatedCommitteeName != null){
								var notConformed=result[i].totalAffilatedCommittees-(result[i].affilatedStartedCount+result[i].affComitteesConformed); 	
								str1+='<tr><td width="20%">'+result[i].affilatedCommitteeName+'</td>';
								str1+='<td width="20%">'+result[i].totalAffilatedCommittees+'</td>';
								if(result[i].affilatedStartedCount != null && result[i].affilatedStartedCount > 0)
								 {
								str1+='<td width="20%"><a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Started\',1,'+id+',0)" style="cursor:pointer;">'+result[i].affilatedStartedCount+'</a></td>';
								 }
								 else
								 {
								str1+='<td width="20%">'+result[i].affilatedStartedCount+'</td>';
								 }
							if(result[i].affComitteesConformed != null && result[i].affComitteesConformed > 0)
								 {
								str1+='<td width="20%"><a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Conform\',1,'+id+',0)" style="cursor:pointer;">'+result[i].affComitteesConformed+'</a></td>';
								
								 }
								 else
								str1+='<td width="20%">'+result[i].affComitteesConformed+'</td>';
								 if(notConformed != null && notConformed > 0)
								 {
									 str1+='<td width="20%"><a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'NotStarted\',1,'+id+',0)" style="cursor:pointer;">'+notConformed+'</td>';
								 }
								 else
								 {
								str1+='<td width="20%">'+notConformed+'</td>';
								 }
								 str1+='</tr>';
						 }
					 }
					 
					 str1+='</table>';
					  $("#mandalAffliCommitteDivId").html(str1);
				}
			});
			
		}
		function getMandalMuncipalDivisonTotalCommittees(){
			//alert(999)	;
			$.ajax({
				type : "POST",
				url : "getMandalMuncipalDivisonTotalCommitteesPopUpAction.action",
				data : {} ,
			}).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
			
				if(result!=null){
					var str='';
					str+='';
					mandalMainCommitteDivId
					
				}
			
				//console.log(result);
			});
		}
		
		
	function getCommitteeDetailsByStatus(basicCommitteetypeId,status,levelId,constituencyId,typeId)
	{
		//$("html,body").animate({scrollTop: $("#dialogSummary").offset().top});
		$('#dialogSummary').animate({ scrollTop: $("#CommitteeDetails").offset().top}, 'slow');
		$("#CommitteeDetails").show();
		$("#committeeMemberDiv").show();
		$("#conformedBtn").show();
		$("#comitteeCntAjax").show();
		$("#CommitteeDetails").html('');
		$("#committeeMemberDiv").html('');
		$("#conformedBtn").html('');
		$("#presGenSecrErrDivId").html("");
		var committeeEnrollmentId =$("#tdpCommitteeYearId1").val();
		var date =$("#reportrange1").val();
		var fromDate;
		var toDate;
		var dates = date.split("-");
		fromDate = dates[0];
		toDate = dates[1];
		
		var stateId = $("#popUpStateId").val();
		var districtId = $("#popUpdistrictId").val();
		var constiencyId = $("#popUpConstiesId").val();
		
		var levelSlected = $("input[type='radio'][name='select']:checked").val();
		if(levelSlected == 'district'){
			if(typeId == 0){
				status = status+":district";
			}else if(typeId == 1){
				if(stateId != null && stateId.length > 0 && districtId == 0 && constiencyId == 0){
					status = status+":state";
				}else if(districtId != null && districtId.length > 0 && constiencyId == 0 ){
					status = status+":district";
				}else if(constiencyId != null && constiencyId.length > 0 ){
					status = status+":consti";
				}
			}
			
		}
		else if(levelSlected == 'consti'){
			if(typeId == 0){
					status = status+":consti";
			}else if(typeId == 1){
				if(stateId != null && stateId.length > 0 && districtId == 0 && constiencyId == 0){
					status = status+":state";
				}else if(districtId != null && districtId.length > 0 && constiencyId == 0 ){
					status = status+":district";
				}else if(constiencyId != null && constiencyId.length > 0 ){
					status = status+":consti";
				}
			}
			
		}
		
		var jsObj = 
	{
		basicCommitteetypeId:basicCommitteetypeId,
		status:status,
		levelId:levelId,
		constituencyId : constituencyId,
		committeeEnrollmentId :[committeeEnrollmentId],
		fromDate :fromDate,
		toDate :toDate,
		task:"memberCnt"
	}
	//alert(4444);
	$.ajax({
          type:'GET',
          url: 'getCommitteeDetailsByStatusPopUpAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
			  $("#comitteeCntAjax").hide();
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}		  
			  
			  if(result != null && result.length > 0){
				  buildCommitteeDetails(result,jsObj);
				
			}
	   });
			
	}

	function  buildCommitteeDetails(result,jsObj){
		var str ='';
		var status = "";
		if(jsObj.status == "NotStarted")
		status = "Not Started";
		else if(jsObj.status == "Conform")
		status = "CONFIRMED/COMPLETED";
		else
		status = jsObj.status;
		var title = '';
		if(jsObj.levelId == 2)
		title = "[VILLAGE / WARD]";
		if(jsObj.levelId == 1)
		title = "[MANDAL/TOWN/DIVISION]";
		str+='<tr>';
        str+='<td colspan="7" style="background-color:#669934" class="text-uppercase">'+title+' WISE <b>'+result[0].committe+' COMMITTEE <i style="color:#EF4036;">'+status+'</i></b> DETAILS <div class="pull-right" ><i class="glyphicon glyphicon-remove" style="cursor:pointer;" id="closeMemberId" onclick="removeDiv();"></i></div></td>';
        str+=' </tr>';
        str+='<tr>';
        str+='<td>Location NAME</td>';
       // str+='<td>COMMITTEE TYPE</td>
        str+='<td>COMMITTEE MEMBERS COUNT</td>';
        str+='<td>STATUS</td>';
       // str+='<td>FINAL</td>';
         str+='</tr>';
		 for(var i in result)
		{
		 
         str+='<tr>';
         str+='<td>'+result[i].name+'</td>';
		 if(result[i].total == null)
		  str+='<td>0</td>';
			 else
		 str+='<td>'+result[i].total+'</td>';
		 if(jsObj.status == 'Started')
		 str+='<td>Started</td>';
		 if(jsObj.status == 'Conform')
		 str+='<td>Completed</td>';
		 if(jsObj.status == 'NotStarted')
		 str+='<td>Not Yet Started</td>';
		 if(jsObj.status == 'NotStarted')
			{
			 str+='<td><button class="btn btn-success btn-sm" disabled="disabled">view</button></td>';
			}
			else
			{
		str+='<td><button class="btn btn-success btn-sm" onclick="getCommitteeMemberInfo(\''+jsObj.basicCommitteetypeId+'\',\''+result[i].level+'\',\''+result[i].id+'\',\''+jsObj.status+'\','+jsObj.constituencyId+',\'ajaxImgStyle'+i+'\','+i+');getCommitteeMembersAvailableInfo(\''+jsObj.basicCommitteetypeId+'\',\''+result[i].level+'\',\''+result[i].id+'\',\'infoDivId\');">view</button></td>';
			}
         //str+='<tr>';
		 str+='<tr id="'+result[i].id+'infoTrId" class=" " style="display:none">';
		 //str+='<td colspan="4" id="'+result[i].id+'infoDivId" class="variable-width"></td>';
		 str+='<td colspan="4">';
		 //str+='<div class="" id="committeeDetailsDiv">';	
					
			
			str+='<div class="col-md-12 col-xs-12">';
				str+='<div class="variable-width summeryCls" id="'+result[i].id+'infoDivId"></div>';
			str+='</div>';
		
		//str+='</div>';
		str+='</td>';
		 str+='</tr>';
		 str+='<tr id="'+result[i].id+'resultTrId" class="dtlsTR" style="display:none;background-color: lightsteelblue;">';
		 str+='<td colspan="4" id="'+result[i].id+'resultDiv" class="buildDivDtals"></td>';
		 str+='</tr>';
			
		}
	 $("#CommitteeDetails").html(str);           
	}
	
	var isWorking = false;
	function getCommitteeMemberInfo(basicCommitteetypeId,levelId,locationId,status,constituencyId,ajaxImgId,index)
	{
		if(!isWorking)
		{
			isWorking=true;
			$(".dtlsTR").hide();			
			$("#"+locationId+"resultTrId").show();
			$("#"+locationId+"resultDiv").html('<img id="ajaxImgStyle'+index+'"  class="ajximgCls" style="margin-center: 10px;width:80px;" src="images/Loading-data.gif"/>');
			
			$("#committeeMemberDiv").html('');
			$("#conformedBtn").html('');
			$("#presGenSecrErrDivId").html("");
			$("#"+ajaxImgId+"").show();
			var committeeEnrollmentId =$("#tdpCommitteeYearId1").val();
			var date =$("#reportrange1").val();
		    var fromDate;
		    var toDate;
		    var dates = date.split("-");
		    fromDate = dates[0];
		    toDate = dates[1];
			//alert(1111);
			var jsObj = 
			{
				basicCommitteetypeId:basicCommitteetypeId,
				levelId:levelId,
				locationId:locationId,
				status:status,
				committeeEnrollmentId :[committeeEnrollmentId],
				fromDate :fromDate,
				toDate :toDate,
				task:"memberInfo"
			}
			//alert(6666);
			$.ajax({
				  type:'GET',
				  url: 'getCommitteeDetailsByStatusPopUpAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)},
				  }).done(function(result){
				$("#"+ajaxImgId+"").show();
				$("#"+locationId+"resultDiv").html('');
				  isWorking=false;
						if(typeof result == "string"){
							if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
							  location.reload(); 
							}
						}
					  
					  if(result != null && result.length > 0){
						  buildCommitteeMemberDetails(result,jsObj,constituencyId,locationId);
						
					}
			   });
		}
	
	}

	
	function removeDtlsDiv(divId)
	{
		$('#'+divId+'').hide();
	}

	var isPresidentAvail = false;
	var isGenSecAvail = false;
	
	function buildCommitteeMemberDetails(result,jsObj,constituencyId,locationId)
	{
		var str='';
		str+='<table class="table table-bordered text-left" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#000000;">';
		str+='<thead style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.2);">';
        str+='<th colspan="5" class="text-uppercase">'+result[0].locationName+'  <b>'+result[0].committe+'  COMMITTEE</b></th>';
        str+='</thead>';
        str+='<tbody>';
		str+='<i class="glyphicon glyphicon-remove" style="cursor:pointer;" id="closeCommitteeMemberDetailsId" onclick="removeDtlsDiv(\''+locationId+'resultTrId\');"></i>';
		for(var i in result)
		{

		str+='<tr>';
		
		str+='<td><img width="32" id="imagecdr'+i+'" height="32" src="https://www.mytdp.com/images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);"/></td>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].membershipNo+'</td>';
		str+='<td>'+result[i].role+'</td>';
		var status = result[i].occupation;
		if(status == "F"){
			str+='<td style="color:#449D44;font-weight:bold;">Finalized';
		}else if(status == "P"){
			str+='<td class="orangeCls">Proposed';
		}
		
		var roleStr = result[i].role;
		
		if(roleStr == "President"){
			isPresidentAvail = true;
		}
		if(roleStr == "General Secretary"){
			isGenSecAvail = true;
		}
		
		if(result[i].status != "Y"){
			
				str+='<div class="pull-right  btn btn-default btn-sm deleteCls deleteCls'+i+'" ><i style="cursor:pointer;" class="glyphicon glyphicon-trash " onclick="deleteCadreRole(\''+result[i].total+'\',\'deleteCls'+i+'\');"></i></div>';
			
		}
		str+='</td>';
		str+='</tr>';//total - tdpCommitteMemberID
		}
		str+='</tbody>';
		str+='</table>';
		//$("#committeeMemberDiv").html(str);
		//$("#"+locationId+"resultTrId").show();
		
		if(result[0].status != "Y")
		{
			str+='<button class="btn btn-success btn-lg pull-right confirmBtnCls" onclick="committeeComplete(\''+jsObj.basicCommitteetypeId+'\',\''+jsObj.levelId+'\',\''+jsObj.locationId+'\','+constituencyId+')"> Entry Finished </button>';
		}
		
		$("#"+locationId+"resultDiv").html(str);
		if(result[0].status != "Y")
		{
			
				var str1='';
				str1+='<button class="btn btn-success btn-lg" onclick="committeeComplete(\''+jsObj.basicCommitteetypeId+'\',\''+jsObj.levelId+'\',\''+jsObj.locationId+'\','+constituencyId+')">Entry Finished </button>';
				//$("#conformedBtn").html(str1);
			
		}

		$('#dialogSummary').animate({ scrollTop: $("#imagecdr0").offset().top}, 'slow');
	/*	$("html, body").animate({
			scrollTop: $("#"+locationId+"resultDiv").offset().top 
		}, 2000);
	*/
		
	}
	function setDefaultImage(img)
	{
		img.src = "images/cadreCommitee/Member_thamb_image.png";
	}
	
	function committeeComplete(basicCommitteetypeId,levelId,locationId,constituencyId)
	{
		var committeeEnrollmentId =$("#tdpCommitteeYearId1").val();
		var date =$("#reportrange1").val();
		    var fromDate;
		    var toDate;
		    var dates = date.split("-");
		    fromDate = dates[0];
		    toDate = dates[1];
	    $("#presGenSecrErrDivId").html("");
		
		/* if(levelId == 6 || levelId == 8){
			if(isPresidentAvail == false && isGenSecAvail == false){
				$("#presGenSecrErrDivId").html("Please add President and General Secretary details to confirm the Committee...");
				return;
			}
			else if(isPresidentAvail == false){
				$("#presGenSecrErrDivId").html("Please add President details to confirm the Committee...");
				return;
			}
			else if(isGenSecAvail == false){
				$("#presGenSecrErrDivId").html("Please add General Secretary details to confirm the Committee...");
				return;
			}
		} */
		
	    var r=confirm("Are you sure want to confirm ?");
		if(r){
		    
			$("#entryFinishedpopUpId").modal('show');
		    $('#entryFinishedpopUpBodyId').html('<center><img style="width:80px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>');
			
			var jsObj = 
			{
				basicCommitteetypeId:basicCommitteetypeId,
				levelId:levelId,
				locationId:locationId,
				committeeEnrollmentId :[committeeEnrollmentId],
				fromDate :fromDate,
				toDate :toDate,
				task:"committeComplete"
			}
	     
		$.ajax({
			  type:'GET',
			  url: 'committeeConfirmationAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)},
			  }).done(function(result){
				   if(typeof result == "string"){
						if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
						  location.reload(); 
						}
				  } 
				  if(result != null){
					 if(result.errorCode != null){
						  if(result.errorCode  == 0){
							  $('#entryFinishedpopUpBodyId').html('<p> This Committee Does Not Have members.</p>');
						  }
						  if(result.errorCode  == 1){
							   if(result.subList != null && result.subList.length > 0){
								    var str = '';
									str+='<h4 class="panel-title text-center" style="color:red" ><b> Entry Not Finished </b></h4>';
									/* str+='<ol>';
								   for( var i in result.subList){
									   str+='<li> '+result.subList[i].role+' Role Should Have Atleast <b>'+result.subList[i].minCount+'</b> Members But Having Only <b>'+result.subList[i].occupiedCount+' </b>Members</li>'
								   }
								   str+='</ol>'; */
								   str+='<table class="table table-bordered">';
								   str+='<thead>';
								     str+='<tr>';
									   str+='<th>SNO</th>';
									   str+='<th>Role</th>';
									   str+='<th>Total Members</th>';
									   str+='<th>Added Members</th>';
									   str+='<th>Minimum Members Required</th>';
								     str+='</tr>';
								   str+='</thead>';
								   str+='<tbody>';
								    for(var i in result.subList){
										
										str+='<tr>';
										var count = parseInt(i) + 1;
										  str+='<td>'+count+'</td>';
										  str+='<td>'+result.subList[i].role+'</td>';
										  if(result.subList[i].totalCount > 0){
											   str+='<td>'+result.subList[i].totalCount+'</td>';
										  }else{
											  str+='<td> -  </td>';
										  }
										  str+='<td>'+result.subList[i].occupiedCount+'</td>';
										  str+='<td>'+result.subList[i].minCount+'</td>';
										str+='</tr>';
								    } 
								   str+='</tbody>';
								   str+='</table>';
								   $('#entryFinishedpopUpBodyId').html(str);
							   }
						  }
						  if(result.errorCode  == 2){
							  $('#entryFinishedpopUpBodyId').html('<h4 class="panel-title text-center" style="color:green;" ><b> Entry Finished Successfully </b></h4>');
							  $("#conformedBtn").html('');
							  $("#presGenSecrErrDivId").html("");
							  $(".deleteCls,.confirmBtnCls").remove(); 
						  }
						  if(result.errorCode  == 3){
							   $('#entryFinishedpopUpBodyId').html('<h4 class="panel-title text-center" style="color:red;" ><b> exception occurred.. try Later..</b></h4>');
						  }
					  }
					
					
					//getSummary(constituencyId);
					//getMandalMuncipalDivisonStartedCommittees(constituencyId);
				}
		   });
		}
	}
	function deleteCadreRole(tdpCommitteeMemberId,className)
	{
		var committeeEnrollmentId =$("#tdpCommitteeYearId1").val();
		var date =$("#reportrange1").val();
		var fromDate;
		var toDate;
		var dates = date.split("-");
		fromDate = dates[0];
		toDate = dates[1];
	var r=confirm("Are You Sure To Remove ?");
		if(r)
		{
	var jsObj = 
	{
		tdpcommitteeMemberId:tdpCommitteeMemberId,
		committeeEnrollmentId :[committeeEnrollmentId],
		fromDate :fromDate,
		toDate :toDate,
		task:"deleterole"
	}
	
	$.ajax({
          type:'GET',
          url: 'getCommitteeDetailsByStatusPopUpAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
			  if(result != null){
				  {
					if(result[0].status == "Removed"){
						alert("Removed Successfully..")
						$('.'+className+'').hide();
					}else
						alert("Committee Already Confirmed")
				  }
				
			}
	   });
		}
	
	}
	function removeDiv()
	{
		$("#CommitteeDetails").hide();
		$("#committeeMemberDiv").hide();
		$("#conformedBtn").hide();

	}	
	 function removeTrDiv()
	 {
		 $(".detailsCls").html('');
		 $("#committeeMemberDiv").hide();
	 }
	/* Script For Summary POpUP  */
	

 
	
	</script>
			   
<script>
	var tableToExcel = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
function exportToExcel()
{
	var levelSelected1 = $("input[type='radio'][name='select']:checked").val();
		if(levelSelected1 == 'district'){
			 tableToExcel('districtTableId', 'District Wise Committees');
		}
		else if(levelSelected1 == 'consti'){
			tableToExcel('constiTableId', 'Constituency Wise Committees');
		}
	  
}
function exportConstituencyToExcel(divId,locationName)
{
	//console.log(divId);
	tableToExcel(divId, ' '+locationName+' Constituency Wise Committees');
}

function exportToExcelForConstitueny(constiTableId)
{
tableToExcel(''+constiTableId+'', 'Constituency Wise Committees');
	
}
function showPopOver(state){
	$('#ivrPopOver'+state+'').popover();
}


function showHideDivs(divId1,divId2){
var isVillage = false;
if(divId1 =="ap"){
	$(".toggleCls").hide();
	if(divId1+divId2+"BodyTR" == "apMandalBodyTR"){
		$("#apVillageBodyTR").hide();
		$(".apVillageBodyTR").hide();
		$("#apDistrictBodyTR").removeClass("toggleCls12");	
		$("#apStateBodyTR").removeClass("toggleCls12");
	}else if(divId1+divId2+"BodyTR" == "apDistrictBodyTR"){
		$("#apMandalBodyTR").removeClass("toggleCls12");		
		$("#apStateBodyTR").removeClass("toggleCls12");
		$("#apVillageBodyTR").hide();
		$(".apVillageBodyTR").hide();
	}else if(divId1+divId2+"BodyTR" == "apStateBodyTR"){		
		$("#apMandalBodyTR").removeClass("toggleCls12");	
		$("#apDistrictBodyTR").removeClass("toggleCls12");
		$("#apVillageBodyTR").hide();
		$(".apVillageBodyTR").hide();		
	}else if(divId1+divId2+"BodyTR" == "apVillageBodyTR"){
		$("#apDistrictBodyTR").removeClass("toggleCls12");
		$("#apStateBodyTR").removeClass("toggleCls12");
		$("#apMandalBodyTR").removeClass("toggleCls12");
		isVillage = true;
	}

}
else{
	$(".toggleCls1").hide();
	if(divId1+divId2+"BodyTR" == "tsMandalBodyTR"){
		$("#tsVillageBodyTR").hide();
		$(".tsVillageBodyTR").hide();
		$("#tsDistrictBodyTR").removeClass("toggleCls12");	
		$("#tsStateBodyTR").removeClass("toggleCls12");
	}else if(divId1+divId2+"BodyTR" == "tsDistrictBodyTR"){
			$("#tsVillageBodyTR").hide();
			$(".tsVillageBodyTR").hide();
		$("#tsMandalBodyTR").removeClass("toggleCls12");		
		$("#tsStateBodyTR").removeClass("toggleCls12");	
	}else if(divId1+divId2+"BodyTR" == "tsStateBodyTR"){
		$("#tsVillageBodyTR").hide();	
		$(".tsVillageBodyTR").hide();		
		$("#tsMandalBodyTR").removeClass("toggleCls12");	
		$("#tsDistrictBodyTR").removeClass("toggleCls12");	
	}else if(divId1+divId2+"BodyTR" == "tsVillageBodyTR"){
		$("#tsMandalBodyTR").removeClass("toggleCls12");		
		$("#tsStateBodyTR").removeClass("toggleCls12");	
		$("#tsDistrictBodyTR").removeClass("toggleCls12");	
		isVillage = true;
	}
	
}
if(!$("#"+divId1+divId2+"BodyTR").hasClass("toggleCls12")){
		$("#"+divId1+divId2+"BodyTR").addClass("toggleCls12");
		$("#"+divId1+divId2+"BodyTR").show();
		$("#"+divId1+divId2+"ButtonsDiv").show();
		$("#"+divId1+divId2+"ButtonsDiv").addClass("toggleCls12");
		
		$("."+divId1+divId2+"BodyTR").addClass("toggleCls12");
		$("."+divId1+divId2+"BodyTR").show();
		$("."+divId1+divId2+"ButtonsDiv").show();
		$("."+divId1+divId2+"ButtonsDiv").addClass("toggleCls12");
		
	}else{
		$("#"+divId1+divId2+"BodyTR").removeClass("toggleCls12");
		$("#"+divId1+divId2+"BodyTR").hide();
		$("#"+divId1+divId2+"ButtonsDiv").hide();
		$("#"+divId1+divId2+"ButtonsDiv").removeClass("toggleCls12");
		
		$("."+divId1+divId2+"BodyTR").removeClass("toggleCls12");
		$("."+divId1+divId2+"BodyTR").hide();
		$("."+divId1+divId2+"ButtonsDiv").hide();
		$("."+divId1+divId2+"ButtonsDiv").removeClass("toggleCls12");
		
		if(isVillage)
		{
			$("#"+divId1+divId2+"BodyTR").addClass("toggleCls12");
			$("#"+divId1+divId2+"BodyTR").show();
			$("#"+divId1+divId2+"ButtonsDiv").show();
			$("#"+divId1+divId2+"ButtonsDiv").addClass("toggleCls12");
			
			$("."+divId1+divId2+"BodyTR").addClass("toggleCls12");
			$("."+divId1+divId2+"BodyTR").show();
			$("."+divId1+divId2+"ButtonsDiv").show();
			$("."+divId1+divId2+"ButtonsDiv").addClass("toggleCls12");
		}
	}
}

 function closeDiv(trID)
   {	  
	$("."+trID).remove();
   }

	function getConstituencyWiseCommittesSummaryForSubLevel(startDate,endDate,state,mandalCheck,villageCheck,districtId,districtName){
	
	//var count = $("#districtTableId tr").find("td").length; 
	var cellCount = $("#districtTableId tr").eq(0).children("td").length;
	
	$('.added').remove('');
	$(".removeicon").hide();
	$(".removeCls").removeClass("selected");
	
	$('.clearCls'+districtId).after('<tr class="selectedchild"><td id="subLevelDiv'+districtId+'" colspan="'+cellCount+'" class="added" style="padding: 20px; background: none repeat scroll 0% 0% rgba(255, 255, 255, 1);"><div align="center"><img id="ajaxImgStyle1" style="display:block;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls'+districtId).addClass("selected");
	$('.clearClsTD'+districtId).addClass("selected");
	$("#ajaxImgStyle1").show();
	$("#iconDiv"+districtId).show();
		var jObj = {
			startDate:startDate,
			endDate:endDate,
			state:state,
			mandalCheck:mandalCheck,
			villageCheck:villageCheck,
			accessType:"DISTRICT",
			accessValue:districtId,
			task:"District"
		}
				//alert(1166)	;
		$.ajax({
          type:'GET',
          url: 'getConstituencyWiseCommittesSummaryForDistrictAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
	
		
				
					buildConstiWiseSummaryForDistrict(result,mandalCheck,villageCheck,"subLevelDiv"+districtId,districtId,districtName,jObj);	
				
		});
	}

  
function buildConstiWiseSummaryForDistrict(result,mandalCheck,villageCheck,divId,districtId,districtName,jObj){
	
	

	
		var constiInfoArr = [];
		var constiVillageInfoArr = [];
	
		
		
		var mandTotal =0; 	
		var mandStarted =0; 	
		var mandCompleted =0; 	
		var mandMembers =0; 	
		var mandAfStarted =0; 	
		var mandAfCompleted =0;
		var panTotal =0; 	
		var panStarted =0; 	
		var panCompleted =0; 	
		var panMembers =0; 	
		var panAffStarted =0; 	
		var panAffCompleted =0;
		var percentage = 0;
		var perc = 0;
		
			
		var yuvathaStartedCount = 0;
		var mahilaStartedCount = 0;
		var rythuStartedCount = 0;
		var othersStartedCount = 0;
		
		var yuvathaCompltdCount = 0;
		var mahilaCompltdCount = 0;
		var rythuCompltdCount = 0;
		var othersCompltdCount = 0;
		
		var str = '';
		/*str+='<span class="btn btn-info excelId form-inline" style="float:left;margin-left:250px;" onclick="exportToExcel(\'constiTableForDistrict\')"> Export To Excel </span>';*/
		str+='<span id="iconDiv'+districtId+'" class="pull-left removeicon btn btn-xs btn-danger" onclick="closeDiv(\'selectedchild\');" style="display: block; padding-top: 5px; padding-bottom: 5px; margin-top: -21px; margin-left: -21px; border-radius: 0px;"   ><i class="glyphicon glyphicon-remove" title="Click here to close '+districtName+' District Constituency Wise Report"></i></span>&nbsp;&nbsp;<button class="btn btn-info excelId form-inline btn-xs" style="float: left; margin-left: 5px; margin-bottom: 0px; z-index: 2; margin-top: -20px;" onclick="exportConstituencyToExcel(\'constiTableForDistrict\',\''+districtName+' District\')" value="Export To Excel">Export To Excel </button></a>';
		str+='<table class="table table-bordered table-condensed " id="constiTableForDistrict" style="width: 1100px; background-color: rgba(0, 0, 0, 0.1) !important;float:left;">';
       
		if(mandalCheck == "true" && villageCheck == "true"){
			str+='<thead class="aler alert-success">';
            str+='<tr>';
			str+='<th rowspan="3" style="text-align:center">AC No</th>';
			str+='<th rowspan="3" style="text-align:center">AC Name</th>';
            str+='<th style="text-align:center" colspan="14">TOWN / MANDAL / DIVISION</th>';
            str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
			if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length-1)*2;
				str+=' <th style="text-align:center" colspan='+length+'>VILLAGE / WARD IVR DETAILS</th>';
				
				//var length1 = (result[0].cadreIVRVO.optionsList1.length-1)*2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS</th>';
			}
            str+='</tr>';
            str+='<tr>';
            str+='<th  rowspan="2">Total</th>';
			str+='<th  rowspan="2">Started</th>';
			str+='<th  rowspan="2">Completed</th>';
			str+='<th  rowspan="2">Members</th>';
			str+='<th  rowspan="2">Affl Committee Started</th>';
			str+='<th  rowspan="2">Affl Committee Completed  </th>';
			str+='<th colspan="2" > Telugu Yuvatha  </th>';
			str+='<th colspan="2" > Telugu Mahila </th>';
			str+='<th colspan="2"  > Telugu Rythu </th>';
			str+='<th colspan="2"  >  Others </th>';
			str+='<th  rowspan="2">Total</th>';
			str+='<th  rowspan="2">Started</th>';
			str+='<th  rowspan="2">Completed</th>';
			str+='<th  rowspan="2">Members</th>';
			str+='<th  rowspan="2">Affl Committee Started</th>';
			str+='<th  rowspan="2">Affl Committee Completed  </th>';
            
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th  rowspan="2">'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th  rowspan="2">%</th>';
					}
				}
				
				/*for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}*/
			}	
			 str+='</tr>';
			 str+='<tr>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='</tr>';	
			str+='</thead>';	
			
		}
		else if(mandalCheck == "true"){
			str+='<thead class="aler alert-success">';
			str+='<tr width="1000px !important">';
			str+='<th rowspan="4"  style="text-align:center; width:2% !important">AC No</th>';
			str+='<th rowspan="4" style="text-align:center;width:1% !important">AC Name</th>';
			 str+='<th style="text-align:center;width:50% !important" colspan="15">TOWN / MANDAL / DIVISION</th>';
			 if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length-1) *2;
				str+=' <th style="text-align:center" colspan='+length+'>VILLAGE / WARD IVR DETAILS</th>';
				
				//var length1 = (result[0].cadreIVRVO.optionsList1.length-1) *2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS</th>';
			}
            str+='</tr>';
            str+='<tr>';
			str+='<th colspan="4"  style="text-align:center" class="mainCls" > Main Committees </th>';
			str+='<th colspan="11"  style="text-align:center" class="affilCls"> Affiliated Committees </th>';
			str+='</tr>';
            str+='<tr>';
			
			str+='<th rowspan="2" class="mainCls" >Total</th>';
			str+='<th rowspan="2" class="mainCls" >Started</th>';
			str+='<th rowspan="2" class="mainCls" >Completed</th>';
			str+='<th rowspan="2" class="mainCls" >Members</th>';
			str+='<th rowspan="2" class="affilCls">Total</th>';
			str+='<th rowspan="2" class="affilCls"> Started</th>';
			str+='<th  rowspan="2" class="affilCls"> Completed </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
			str+='<th colspan="2"  class="affilCls" > Telugu Rythu </th>';
			str+='<th colspan="2" class="affilCls"  >  Others </th>';
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th rowspan="2">'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th rowspan="2">%</th>';
					}
				}
				/*
				for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				*/
			}
			 str+='</tr>';
			 str+='<tr>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='</tr>';	
			str+='</thead>';	
			
		}
		else if(villageCheck == "true"){
			str+='<thead class="aler alert-success">';
			str+='<th rowspan="2"  style="text-align:center">AC No</th>';
			str+='<th rowspan="2" style="text-align:center">AC Name</th>';
    
			str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
			if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length-1)*2;
				str+=' <th style="text-align:center" colspan='+length+'>VILLAGE / WARD IVR DETAILS</th>';
				
			//	var length1 = (result[0].cadreIVRVO.optionsList1.length-1)*2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS</th>';
				
			}
            str+='</tr>';
            str+='<tr>';
			str+='<th>Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed  </th>';
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th>'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				/*
				for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				*/
			}
			str+='</thead>';	
		}
		
		str+='<tbody>';
		for(var i in result){
		if(result[i].townMandalDivisionVO != null || result[i].villageWardVO != null){
		str += '<tr id='+result[i].constiId+' class="removeCls1 clearCls1'+result[i].constiId+'">';
		str += '<td style="text-align:center" class="removeCls1 clearClsTD1'+result[i].constiId+'">'+result[i].constiNo+'</td>';
			str += '<td ><a onclick="getConstituencyWiseCommittesSummaryForMandal(\''+jObj.startDate+'\',\''+jObj.endDate+'\',\''+jObj.state+'\',\''+jObj.mandalCheck+'\',\''+jObj.villageCheck+'\',\''+result[i].constiId+'\',\''+result[i].name+'\');" style="color:#333333;font-weight:bold;cursor:pointer;"><span style="font-size: 12px;">'+result[i].name+'</span></a>';
				str += '&nbsp;&nbsp;<span style="cursor: pointer;" title="Click Here For '+result[i].name+' Committee Summary Report" onclick="getPopUpForSummary('+result[i].constiId+',\''+result[i].name+'\');" class="glyphicon glyphicon-dashboard"></span>&nbsp;&nbsp;<span style="cursor: pointer;"  onclick="showAdvanceDashBoard('+result[i].constiId+');" title="Click Here For '+result[i].name+' Advance Dashboard"  class="glyphicon glyphicon-list-alt"></span>';
			
			str += '</td>';
			if(mandalCheck == "true"){
			
			if(result[i].townMandalDivisionVO!=null){
				if(result[i].townMandalDivisionVO.totalCommittees!=null){
					str += '<td style="text-align:center" ><span style="cursor: pointer;color:green;" onclick="getPopUpForSummary('+result[i].constiId+',\''+result[i].name+'\',\'mandal\');">'+result[i].townMandalDivisionVO.totalCommittees+'<span></td>';
					mandTotal=mandTotal+result[i].townMandalDivisionVO.totalCommittees;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainStarted!=null){
					//str += '<td>'+result[i].townMandalDivisionVO.mainStarted+'<span id="mini-pie-chart-constituency'+i+'" class="pull-right mini-pie-chart-district"></span></td>';
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mainStarted+'</td>';
					mandStarted=mandStarted+result[i].townMandalDivisionVO.mainStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mainCompleted+'</td>';
					mandCompleted=mandCompleted+result[i].townMandalDivisionVO.mainCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.membersCount!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.membersCount+'</td>';
					mandMembers=mandMembers+result[i].townMandalDivisionVO.membersCount;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.totalCommittees!=null){
					str += '<td style="text-align:center" >'+(20 * result[i].townMandalDivisionVO.totalCommittees)+'</td>';
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflStarted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.afflStarted+'</td>';
					mandAfStarted=mandAfStarted+result[i].townMandalDivisionVO.afflStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.afflCompleted+' </td>';
					mandAfCompleted=mandAfCompleted+result[i].townMandalDivisionVO.afflCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.youvathaStarted != null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.youvathaStarted+' </td>';
					yuvathaStartedCount = yuvathaStartedCount+result[i].townMandalDivisionVO.youvathaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.youvathaCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.youvathaCmpltd+' </td>';
					yuvathaCompltdCount = yuvathaCompltdCount+result[i].townMandalDivisionVO.youvathaCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mahilaStarted != null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mahilaStarted+' </td>';
					mahilaStartedCount = mahilaStartedCount + result[i].townMandalDivisionVO.mahilaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mahilaCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mahilaCmpltd+' </td>';
					mahilaCompltdCount = mahilaCompltdCount+result[i].townMandalDivisionVO.mahilaCmpltd;					
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				
				if(result[i].townMandalDivisionVO.rythuStarted != null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.rythuStarted+' </td>';
					rythuStartedCount = rythuStartedCount+result[i].townMandalDivisionVO.rythuStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.rythuCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.rythuCmpltd+' </td>';
					rythuCompltdCount = rythuCompltdCount+result[i].townMandalDivisionVO.rythuCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.othersStarted != null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.othersStarted+' </td>';
					othersStartedCount = othersStartedCount+result[i].townMandalDivisionVO.othersStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.othersCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.othersCmpltd+' </td>';
					othersCompltdCount = othersCompltdCount+result[i].townMandalDivisionVO.othersCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
			}else{
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
			}
			}
			if(villageCheck == "true"){
			
			if(result[i].villageWardVO!=null){
				if(result[i].villageWardVO.totalCommittees!=null){
					str += '<td style="text-align:center" ><span style="cursor: pointer;color:green;"  onclick="getPopUpForSummary('+result[i].constiId+',\''+result[i].name+'\',\'village\');">'+result[i].villageWardVO.totalCommittees+'</span></td>';
					panTotal=panTotal+result[i].villageWardVO.totalCommittees;
				}else{
					str += '<td style="text-align:center"> - </td>';
				}
				
				if(result[i].villageWardVO.mainStarted!=null){
					//str += '<td>'+result[i].villageWardVO.mainStarted+'<span id="mini-pie-chart-constiVillage'+i+'" class="pull-right mini-pie-chart-village"></span></td>';
					str += '<td style="text-align:center">'+result[i].villageWardVO.mainStarted+'</td>';
					panStarted=panStarted+result[i].villageWardVO.mainStarted;
				}else{
					str += '<td style="text-align:center"> - </td>';
				}
				
				if(result[i].villageWardVO.mainCompleted!=null){
					str += '<td style="text-align:center">'+result[i].villageWardVO.mainCompleted+'</td>';
					panCompleted=panCompleted+result[i].villageWardVO.mainCompleted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.membersCount!=null){
					str += '<td style="text-align:center">'+result[i].villageWardVO.membersCount+' </td>';
					panMembers=panMembers+result[i].villageWardVO.membersCount;
				}else{
					str += '<td style="text-align:center"> - </td>';
				}
				
				if(result[i].villageWardVO.afflStarted!=null){
					str += '<td style="text-align:center">'+result[i].villageWardVO.afflStarted+'</td>';
					panAffStarted=panAffStarted+result[i].villageWardVO.afflStarted;
				}else{
					str += '<td style="text-align:center">  - </td>';
				}
				
				if(result[i].villageWardVO.afflCompleted!=null){
					str += '<td style="text-align:center">'+result[i].villageWardVO.afflCompleted+' </td>';
					panAffCompleted=panAffCompleted+result[i].villageWardVO.afflCompleted;
				}else{
					str += '<td style="text-align:center"> - </td>';
				}
			}else{
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
			}
			}
			if(result[i].cadreIVRVO != null)
			{
				
				for(var tp in result[i].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[tp].id != 8){
					if(result[i].cadreIVRVO.total >0){
					 percentage = (result[i].cadreIVRVO.optionsList[tp].count *100)/ result[i].cadreIVRVO.total;
					 perc = percentage.toFixed(0);
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList[tp].count+'</td>';
					str+='<td style="text-align:center">'+perc+'</td>';
					}				
					else{
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList[tp].count+'</td>';
					str+='<td style="text-align:center">0</td>';
					}
					}					
				}
			/*	
				for(var tp in result[i].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[tp].id != 13){
					if(result[i].cadreIVRVO.total >0){
					 percentage = (result[i].cadreIVRVO.optionsList1[tp].count *100)/ result[i].cadreIVRVO.total;
					 perc = percentage.toFixed(0);
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList1[tp].count+'</td>';
					str+='<td style="text-align:center">'+perc+'</td>';
					}				
					else{
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList1[tp].count+'</td>';
					str+='<td style="text-align:center">0</td>';
					}
					}
				}
				*/
			}
			
			
		
			str += '</tr>';
			
			if(result[i].townMandalDivisionVO != null){
				var details = [result[i].townMandalDivisionVO.totalCommittees, result[i].townMandalDivisionVO.mainStarted];
				constiInfoArr.push(details);
			}else{
				 var details = [0, 0];
				constiInfoArr.push(details);
			}
			
			
			if(result[i].villageWardVO != null){
				var villageDetails  = [result[i].villageWardVO.totalCommittees, result[i].villageWardVO.mainStarted];
				constiVillageInfoArr.push(villageDetails);
			}else{
				var villageDetails  = [0, 0];
				constiVillageInfoArr.push(villageDetails);
			}
			
			
		}
		}
   
	    str += '</tbody><tfoot><tr class="no-sort" style="font-weight:bold;"><td></td>';
	if(mandalCheck=="true" && villageCheck=="true"){
		str	+= '<td style="text-align:center">TOTAL</td><td >'+mandTotal+'</td>'; 	
		str += '<td style="text-align:center"  >'+mandStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandMembers+'</td>'; 
		str	+= '<td style="text-align:center" >'+(mandTotal *20)+'</td>';		
		str += '<td style="text-align:center" >'+mandAfStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+yuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+yuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+rythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+rythuCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+othersStartedCount+'</td>';
		str += '<td style="text-align:center" >'+othersCompltdCount+'</td>';
		
		str += '<td style="text-align:center">'+panTotal+'</td>';
		str += '<td style="text-align:center">'+panStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panCompleted+'</td>'; 	
		str += '<td style="text-align:center">'+panMembers+'</td>'; 	
		str += '<td style="text-align:center">'+panAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panAffCompleted+'</td>'; 	
	}	
	else if(mandalCheck=="true"){
		str	+= '<td style="text-align:center">TOTAL</td><td >'+mandTotal+'</td>'; 	
		str += '<td style="text-align:center" >'+mandStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandMembers+'</td>'; 	
		str	+= '<td style="text-align:center" >'+(mandTotal *20)+'</td>';		 	
		str += '<td style="text-align:center" >'+mandAfStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfCompleted+'</td>'; 	
		
		str += '<td style="text-align:center" >'+yuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+yuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+rythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+rythuCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+othersStartedCount+'</td>';
		str += '<td style="text-align:center" >'+othersCompltdCount+'</td>';
		
	}
	else if(villageCheck=="true"){
		str	+= '<td style="text-align:center">TOTAL</td><td>'+panTotal+'</td>'; 	
		str += '<td style="text-align:center">'+panStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panCompleted+'</td>'; 	
		str += '<td style="text-align:center">'+panMembers+'</td>'; 	
		str += '<td style="text-align:center">'+panAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panAffCompleted+'</td>'; 	
	}
		str += '</tr></tfoot></table>';
		
		$("#"+divId).html(str);
		$("#constiTableForDistrict").dataTable({
			"iDisplayLength": 20,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
		
		if( $('.mini-pie-chart-constiVillage').length > 0 ) {
			var visitData2 = constiVillageInfoArr;
			var params = {
				type: "pie",
				 sliceColors: ["#0B3B0B", "#B18904"]

			}
			for(var e in result){
				$('#mini-pie-chart-constiVillage'+e+'').sparkline(visitData2[e], params);
			}
		}
		
		
		if($('.mini-pie-chart-constituency').length > 0 ) {
			var visitData = constiInfoArr;
			var params = {
				type: "pie",
				 sliceColors: ["#0B3B0B", "#B18904"]

			}
			for(var t in result){
			
				$('#mini-pie-chart-constituency'+t+'').sparkline(visitData[t], params);
			}
		}
			
	}
	function getConstituencyWiseCommittesSummaryForMandal(startDate,endDate,state,mandalCheck,villageCheck,constituencyId,constituencyName){
	
	//var count = $("#districtTableId tr").find("td").length; 
	var cellCount = $("#constiTableForDistrict tr").eq(0).children("td").length;
	
	$('.added1').remove('');
	$(".removeicon1").hide();
	$(".removeCls1").removeClass("selectedchild1");
	
	$('.clearCls1'+constituencyId).after('<tr class="selectedchild1"><td id="subLevelMandalDiv'+constituencyId+'" colspan="'+cellCount+'" class="added1" style="padding: 20px; background: #fff;"><div align="center"><img id="ajaxImgStyle2" style="display:block;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls1'+constituencyId).addClass("selected");
	$('.clearClsTD1'+constituencyId).addClass("selected");
	$("#ajaxImgStyle2").show();
	$("#iconDiv1"+constituencyId).show();
		var jObj = {
			startDate:startDate,
			endDate:endDate,
			state:state,
			mandalCheck:mandalCheck,
			villageCheck:villageCheck,
			accessType:"Constituency",
			accessValue:constituencyId,
			task:"Constituency"
		}
				//alert(1177)	;
		$.ajax({
          type:'GET',
          url: 'getConstituencyWiseCommittesSummaryForDistrictAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
	
		buildMandalWiseSummaryForConstituencyfunction(result,mandalCheck,villageCheck,"subLevelMandalDiv"+constituencyId,constituencyId,constituencyName,jObj);	
				
		});
	}
function  buildMandalWiseSummaryForConstituencyfunction(result,mandalCheck,villageCheck,divId,constituencyId,constituencyName,jObj){
		var constiInfoArr = [];
		var constiVillageInfoArr = [];
		var mandTotal =0; 	
		var mandStarted =0; 	
		var mandCompleted =0; 	
		var mandMembers =0; 	
		var mandAfStarted =0; 	
		var mandAfCompleted =0;
		var panTotal =0; 	
		var panStarted =0; 	
		var panCompleted =0; 	
		var panMembers =0; 	
		var panAffStarted =0; 	
		var panAffCompleted =0;
		var percentage = 0;
		var perc = 0;
		var str = '';
		/*str+='<span class="btn btn-info excelId form-inline" style="float:left;margin-left:250px;" onclick="exportToExcel(\'constiTableForDistrict\')"> Export To Excel </span>';*/
		str+='<span id="iconDiv1'+constituencyId+'" class="pull-left removeicon1 btn btn-xs btn-danger" onclick="closeDiv(\'selectedchild1\');" style="display: block; padding-top: 5px; padding-bottom: 5px; margin-top: -21px; margin-left: -21px; border-radius: 0px;"   ><i class="glyphicon glyphicon-remove" title="Click here to close '+constituencyName+' District Constituency Wise Report"></i></span>&nbsp;&nbsp;<button class="btn btn-info excelId form-inline btn-xs" style="float: left; margin-left: 5px; margin-bottom: 0px; z-index: 2; margin-top: -20px;" onclick="exportConstituencyToExcel(\'mandalTableForConstituency\',\''+constituencyName+' Constituency\')" value="Export To Excel">Export To Excel </button></a>';
		str+='<table class="table table-bordered table-condensed " id="mandalTableForConstituency" style="width: 1100px; background-color: rgba(0, 0, 0, 0.1) !important;">';
       
		if(mandalCheck == "true" && villageCheck == "true"){
			str+='<thead class="aler alert-success">';
            str+='<tr>';
			str+='<th rowspan="2" style="text-align:center">AC No</th>';
			str+='<th rowspan="2" style="text-align:center">AC Name</th>';
            str+='<th style="text-align:center" colspan="6">TOWN / MANDAL / DIVISION</th>';
            str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
			if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length-1)*2;
				str+=' <th style="text-align:center" colspan='+length+'>VILLAGE / WARD IVR DETAILS</th>';
				
				//var length1 = (result[0].cadreIVRVO.optionsList1.length-1)*2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS</th>';
			}
            str+='</tr>';
            str+='<tr>';
            str+='<th>Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed  </th>';
			str+='<th>Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed  </th>';
            
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th>'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				
				/*for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}*/
			}	
			str+='</tr>';	
			str+='</thead>';	
			
		}
		else if(mandalCheck == "true"){
			str+='<thead class="aler alert-success">';
			str+='<tr>';
			str+='<th rowspan="4"  style="text-align:center">AC No</th>';
			str+='<th rowspan="4" style="text-align:center">AC Name</th>';
			 str+='<th style="text-align:center" colspan="6">TOWN / MANDAL / DIVISION</th>';
			 if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length-1) *2;
				str+=' <th style="text-align:center" colspan='+length+'>VILLAGE / WARD IVR DETAILS</th>';
				
				//var length1 = (result[0].cadreIVRVO.optionsList1.length-1) *2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS</th>';
			}  
            str+='</tr>';
            str+='<tr>';
			str+='<th colspan="4"  style="text-align:center" class="mainCls" > Main Committees </th>';
			str+='<th colspan="11"  style="text-align:center" class="affilCls"> Affiliated Committees </th>';
			
			str+='</tr>';
            str+='<tr>';
			str+='<th class="mainCls" >Total</th>';
			str+='<th class="mainCls" >Started</th>';
			str+='<th class="mainCls" >Completed</th>';
			str+='<th class="mainCls" >Members</th>';
			str+='<th class="affilCls"> Started</th>';
			str+='<th class="affilCls"> Completed  </th>';
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th>'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				/*
				for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				*/
			}
			str+='</tr></thead>';	
		}
		else if(villageCheck == "true"){
			str+='<thead class="aler alert-success">';
			str+='<th rowspan="2"  style="text-align:center">AC No</th>';
			str+='<th rowspan="2" style="text-align:center">AC Name</th>';
    
			str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
			if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length-1)*2;
				str+=' <th style="text-align:center" colspan='+length+'>VILLAGE / WARD IVR DETAILS</th>';
				
			//	var length1 = (result[0].cadreIVRVO.optionsList1.length-1)*2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS</th>';
				
			}
            str+='</tr>';
            str+='<tr>';
			str+='<th>Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed  </th>';
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th>'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				/*
				for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				*/
			}
			str+='</thead>';	
		}
		
		str+='<tbody>';
		for(var i in result){
		if(result[i].townMandalDivisionVO != null || result[i].villageWardVO != null){
		str += '<tr id='+result[i].constiId+' class="removeCls1 clearCls1'+result[i].constiId+'">';
		str += '<td style="text-align:center" class="removeCls1 clearClsTD1'+result[i].constiId+'">'+result[i].constiNo+'</td>';
			str += '<td>'+result[i].name+'';
				str += '&nbsp;&nbsp;';
			
			str += '</td>';
			if(mandalCheck == "true"){
			
			if(result[i].townMandalDivisionVO!=null){
				if(result[i].townMandalDivisionVO.totalCommittees!=null){
					str += '<td style="text-align:center" ><span style="cursor: pointer;color:green;"  onclick="getPopUpForSummary('+result[i].constiId+',\''+result[i].name+'\',\'mandal\');">'+result[i].townMandalDivisionVO.totalCommittees+'</span></td>';
					mandTotal=mandTotal+result[i].townMandalDivisionVO.totalCommittees;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainStarted!=null){
					//str += '<td>'+result[i].townMandalDivisionVO.mainStarted+'<span id="mini-pie-chart-constituency'+i+'" class="pull-right mini-pie-chart-district"></span></td>';
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mainStarted+'</td>';
					mandStarted=mandStarted+result[i].townMandalDivisionVO.mainStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mainCompleted+'</td>';
					mandCompleted=mandCompleted+result[i].townMandalDivisionVO.mainCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.membersCount!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.membersCount+'</td>';
					mandMembers=mandMembers+result[i].townMandalDivisionVO.membersCount;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflStarted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.afflStarted+'</td>';
					mandAfStarted=mandAfStarted+result[i].townMandalDivisionVO.afflStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.afflCompleted+' </td>';
					mandAfCompleted=mandAfCompleted+result[i].townMandalDivisionVO.afflCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
			}else{
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
			}
			}
			if(villageCheck == "true"){
			
			if(result[i].villageWardVO!=null){
				if(result[i].villageWardVO.totalCommittees!=null){
					str += '<td style="text-align:center"  ><span style="cursor: pointer;color:green;"  onclick="getPopUpForSummary('+result[i].constiId+',\''+result[i].name+'\',\'village\');">'+result[i].villageWardVO.totalCommittees+'</span></td>';
					panTotal=panTotal+result[i].villageWardVO.totalCommittees;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.mainStarted!=null){
					//str += '<td>'+result[i].villageWardVO.mainStarted+'<span id="mini-pie-chart-constiVillage'+i+'" class="pull-right mini-pie-chart-village"></span></td>';
					str += '<td style="text-align:center" >'+result[i].villageWardVO.mainStarted+'</td>';
					panStarted=panStarted+result[i].villageWardVO.mainStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.mainCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.mainCompleted+'</td>';
					panCompleted=panCompleted+result[i].villageWardVO.mainCompleted;
				}else{
					str += '<td > - </td>';
				}
				
				if(result[i].villageWardVO.membersCount!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.membersCount+' </td>';
					panMembers=panMembers+result[i].villageWardVO.membersCount;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.afflStarted!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.afflStarted+'</td>';
					panAffStarted=panAffStarted+result[i].villageWardVO.afflStarted;
				}else{
					str += '<td style="text-align:center" >  - </td>';
				}
				
				if(result[i].villageWardVO.afflCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.afflCompleted+' </td>';
					panAffCompleted=panAffCompleted+result[i].villageWardVO.afflCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
			}else{
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
			}
			}
			if(result[i].cadreIVRVO != null)
			{
				
				for(var tp in result[i].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[tp].id != 8){
					if(result[i].cadreIVRVO.total >0){
					 percentage = (result[i].cadreIVRVO.optionsList[tp].count *100)/ result[i].cadreIVRVO.total;
					 perc = percentage.toFixed(0);
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList[tp].count+'</td>';
					str+='<td style="text-align:center">'+perc+'</td>';
					}				
					else{
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList[tp].count+'</td>';
					str+='<td style="text-align:center">0</td>';
					}
					}					
				}
			/*	
				for(var tp in result[i].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[tp].id != 13){
					if(result[i].cadreIVRVO.total >0){
					 percentage = (result[i].cadreIVRVO.optionsList1[tp].count *100)/ result[i].cadreIVRVO.total;
					 perc = percentage.toFixed(0);
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList1[tp].count+'</td>';
					str+='<td style="text-align:center">'+perc+'</td>';
					}				
					else{
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList1[tp].count+'</td>';
					str+='<td style="text-align:center">0</td>';
					}
					}
				}
				*/
			}
			
			
		
			str += '</tr>';
			
			if(result[i].townMandalDivisionVO != null){
				var details = [result[i].townMandalDivisionVO.totalCommittees, result[i].townMandalDivisionVO.mainStarted];
				constiInfoArr.push(details);
			}else{
				 var details = [0, 0];
				constiInfoArr.push(details);
			}
			
			
			if(result[i].villageWardVO != null){
				var villageDetails  = [result[i].villageWardVO.totalCommittees, result[i].villageWardVO.mainStarted];
				constiVillageInfoArr.push(villageDetails);
			}else{
				var villageDetails  = [0, 0];
				constiVillageInfoArr.push(villageDetails);
			}
			
			
		}
		}
   
	    str += '</tbody><tfoot><tr class="no-sort" style="font-weight:bold;"><td></td>';
	if(mandalCheck=="true" && villageCheck=="true"){
		str	+= '<td style="text-align:center">TOTAL</td><td >'+mandTotal+'</td>'; 	
		str += '<td style="text-align:center" >'+mandStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandMembers+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfCompleted+'</td>';
		str += '<td style="text-align:center">'+panTotal+'</td>';
		str += '<td style="text-align:center">'+panStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panCompleted+'</td>'; 	
		str += '<td style="text-align:center">'+panMembers+'</td>'; 	
		str += '<td style="text-align:center">'+panAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panAffCompleted+'</td>'; 	
	}	
	else if(mandalCheck=="true"){
		str	+= '<td style="text-align:center">TOTAL</td><td >'+mandTotal+'</td>'; 	
		str += '<td style="text-align:center" >'+mandStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandMembers+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfCompleted+'</td>'; 	
	}
	else if(villageCheck=="true"){
		str	+= '<td style="text-align:center">TOTAL</td><td>'+panTotal+'</td>'; 	
		str += '<td style="text-align:center">'+panStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panCompleted+'</td>'; 	
		str += '<td style="text-align:center">'+panMembers+'</td>'; 	
		str += '<td style="text-align:center">'+panAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panAffCompleted+'</td>'; 	
	}
		str += '</tr></tfoot></table>';
		
		$("#"+divId).html(str);
		$("#mandalTableForConstituency").dataTable({
			"iDisplayLength": 20,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
		
		
	}

	function gettingCadreDetails(locationId,locationName,basicCmmtyName,basicCmmtyId,locationTypeId){	
		//gettingConstituenciesByDistrict(locationId);
		$("#performanceId").hide();
		var dates = $("#reportrange").val();
		var dateArray = dates.split("-");
		var fromDateStr=dateArray[0];
		var toDateStr=dateArray[1];
		var enrollmentIdsArr = new Array();
		enrollmentIdsArr.push($("#tdpCommitteeYearId").val());
		$("#cadreDetailsDiv").html('');
		 var jsObj={
		         locationId:locationId,locationType:locationTypeId,basicCommitteeTypeId:basicCmmtyId,type:"committeembrs",casteStateId:0,gender:"",fromAge:0,toAge:0,committeeEnrollmentId:enrollmentIdsArr,
				startDate :fromDateStr,endDate : toDateStr
		       };
			  // alert(1188)	;
		 $.ajax({
			type : "GET",
			url : "gettingCadreDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			 $("#dialogSummaryDistsrict").modal("show");
			buildingResults(result,locationName,basicCmmtyName,basicCmmtyId,locationTypeId,locationId);
		});
		
		
	}
		
	function gettingCadreDetailsPerformance(locationTypeId,locationId){
		
		 var jsObj={
		         locationId:locationId,locationTypeId:locationTypeId
		       };
			  // alert(1199)	;
		 $.ajax({
			type : "GET",
			url : "gettingPerformanceOfCadreAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			console.log(result);
		});
	}
$(document).on("click","#performanceId",function(){
	var locationId = $(this).attr("attr_distId");
	var locationTypeId = 11;
	
	window.open("peformanceOfCadreAction.action?distId="+locationId, '_blank');
	window.focus();
	
	//gettingCadreDetailsPerformance(locationTypeId,locationId);
});

function capitalize(str) {

   // return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
   var words = str.split(" ");
   var arr = Array();
   for (i in words)
   {
      temp = words[i].toLowerCase();
      temp = temp.charAt(0).toUpperCase() + temp.substring(1);
      arr.push(temp);
   }
   return arr.join(" ");
}
$(document).on("change","#tdpCommitteeYearId",function(){
	$( "#reportrange" ).val('');
	getCommitteeDetailsByEnrollement(1);
});	

function onLoadcimmitteeDashboardCalls(){
		if(userAccessType == 'ALL'){
			getCommitteeCountByState("AP");
			getCommitteeCountByState("TS");
			getCommitteeDetails("AP","mandalAll");
			getCommitteeDetails("AP","villageAll");	
			getCommitteeDetails("TS","mandalAll");	
			getCommitteeDetails("TS","villageAll");						
			getCommitteeDetails("AP","district");
			getCommitteeDetails("AP","state");
			getCommitteeDetails("TS","district");
			getCommitteeDetails("TS","state");
			//getDistrictWiseCommittesSummary();
			getConstituencyWiseCommittesSummary();
			$("#apDistrictHeadingTR").show();
			//$("#apDistrictBodyTR").show();
			$("#tsDistrictHeadingTR").show();
			//$("#tsDistrictBodyTR").show();
			
			$("#apStateHeadingTR").show();
			//$("#apStateBodyTR").show();
			$("#tsStateHeadingTR").show();
			//$("#tsStateBodyTR").show();
			$("#stateDiv").show();
			$("#popUpDistrictDiv").show();
			$("#popUpConstituencyDiv").show();
		}else if(userAccessType == 'TS'){
			getCommitteeCountByState("TS");
			getCommitteeDetails("TS","mandalAll");	
			getCommitteeDetails("TS","villageAll");
			getCommitteeDetails("TS","district");
			getCommitteeDetails("TS","state");
			//$("#apDistrictHeadingTR").hide();
			//$("#apDistrictBodyTR").hide();
			$("#tsDistrictHeadingTR").show();
			//$("#tsDistrictBodyTR").show();
			
			//$("#apStateHeadingTR").hide();
			//$("#apStateBodyTR").hide();
			$("#tsStateHeadingTR").show();
			//$("#tsStateBodyTR").show();
			//getDistrictWiseCommittesSummary();
			getConstituencyWiseCommittesSummary();
			$("#stateDiv").show();
			$("#popUpDistrictDiv").show();
			$("#popUpConstituencyDiv").show();
			$("#usrWisedistrictDiv").show();
			$("#allDistrictDiv").hide();
			$("#usrConstitunecyDiv").hide();
			$("#statedisplaydivid").hide();
		}
		else if(userAccessType == 'AP'){
			getCommitteeCountByState("AP");
			getCommitteeDetails("AP","mandalAll");
			getCommitteeDetails("AP","villageAll");
			getCommitteeDetails("AP","district");
			getCommitteeDetails("AP","state");
			$("#apDistrictHeadingTR").show();
			//$("#apDistrictBodyTR").show();
			//$("#tsDistrictHeadingTR").hide();
			//$("#tsDistrictBodyTR").hide();
			
			$("#apStateHeadingTR").show();
			//$("#apStateBodyTR").show();
			//$("#tsStateHeadingTR").hide();
			//$("#tsStateBodyTR").hide();
			//getDistrictWiseCommittesSummary();
			getConstituencyWiseCommittesSummary();
			$("#stateDiv").show();
			$("#popUpDistrictDiv").show();
			$("#popUpConstituencyDiv").show();
			$("#usrWisedistrictDiv").show();
			$("#allDistrictDiv").hide();
			$("#usrConstitunecyDiv").hide();
			$("#statedisplaydivid").hide();
		}else{					
			if(userAccessType=="MP"){
				//$("#districtCommDiv").hide();
				getCommitteeCountByState("TS");
				getCommitteeDetails("TS","mandalAll");
				getCommitteeDetails("TS","villageAll");
				/*$("#apDistrictHeadingTR").hide();
				$("#apDistrictBodyTR").hide();
				$("#tsDistrictHeadingTR").hide();
				$("#tsDistrictBodyTR").hide();
				$("#apStateHeadingTR").hide();
				$("#apStateBodyTR").hide();
				$("#tsStateHeadingTR").hide();
				$("#tsStateBodyTR").hide();*/
				$("#districtDistrictHeadingTR").hide();
				//$("#districtDistrictBodyTR").hide();

				getConstituencyWiseCommittesSummary();
				$("#popUpConstituencyDiv").show();
				$("#usrConstitunecyDiv").show();
				$("#usrWisedistrictDiv").hide();
				$("#allDistrictDiv").hide();
				$("#statedisplaydivid").hide();
				$("#constitunecyDiv").hide();
			}else if( userAccessType.indexOf("District") >= 0){
				//$('#areaBtnsDiv').hide();
				//$('.areaBtnsDiv').hide();
				//$('#constiRdId').prop('checked','checked');
				
				//getConstituencyWiseCommittesSummary();
				getCommitteeCountByState("AP");
				getCommitteeDetails("AP","mandalAll");
				getCommitteeDetails("AP","villageAll");
				getCommitteeDetails("AP","district");
				//getConstituencyWiseCommittesSummary();
				//getCommitteeDetails("AP","state");
				/*$("#apDistrictHeadingTR").show();
				$("#apDistrictBodyTR").show();
				$("#tsDistrictHeadingTR").show();
				$("#tsDistrictBodyTR").show();*/
				
				$("#apStateHeadingTR").hide();
				//$("#apStateBodyTR").hide();
				$("#tsStateHeadingTR").hide();
				//$("#tsStateBodyTR").hide();
				//getDistrictWiseCommittesSummary();
				getConstituencyWiseCommittesSummary();
				$("#popUpDistrictDiv").show();
				$("#popUpConstituencyDiv").show();
				$("#usrConstitunecyDiv").show();
				$("#usrWisedistrictDiv").hide();
				$("#allDistrictDiv").hide();
				$("#statedisplaydivid").hide();
				$("#constitunecyDiv").hide();
			}else {
				getCommitteeCountByState("AP");
				getCommitteeDetails("AP","mandalAll");
				getCommitteeDetails("AP","villageAll");
				getCommitteeDetails("AP","district");
				getCommitteeDetails("AP","state");
				//getDistrictWiseCommittesSummary();
				getConstituencyWiseCommittesSummary();
			}
			
			
		}
}

$(document).on("change","#tdpCommitteeYearId1",function(){
	$( "#reportrange1" ).val('');
	getCommitteeDetailsByEnrollement(2);
});
 $(document).on("click","#getDetailsId1",function(){
	var committeeEnrollmentId =$("#tdpCommitteeYearId1").val();
	var stateId = $("#popUpStateId").val();
	var districtId = $("#popUpdistrictId").val();
	var constituencyId = $("#popUpConstiesId").val();
				var date =$("#reportrange1").val();
				var fromDate;
				var toDate;
				var dates = date.split("-");
				fromDate = dates[0];
				toDate = dates[1];
				
		var levelSlected = $("input[type='radio'][name='select']:checked").val();
		if(levelSlected == 'district'){
			if(stateId != null && stateId.length > 0 && districtId == 0 && constituencyId == 0){
				getCommitteeSummaryInfo(stateId,committeeEnrollmentId,fromDate,toDate,"State");
				//getMandalMuncipalDivisonStartedCommittees(stateId);
			}else if(districtId != null && districtId.length > 0 && constituencyId == 0){
				getCommitteeSummaryInfo(districtId,committeeEnrollmentId,fromDate,toDate,"District");
				//getMandalMuncipalDivisonStartedCommittees(districtId);
			}else if(constituencyId != null && constituencyId.length > 0 ){
				getCommitteeSummaryInfo(constituencyId,committeeEnrollmentId,fromDate,toDate,"Constituency");
				//getMandalMuncipalDivisonStartedCommittees(constituencyId);
			}
			//getCommitteeSummaryInfo(globalDistrictId,committeeEnrollmentId,fromDate,toDate);
		}
		else if(levelSlected == 'consti'){
			if(stateId != null && stateId.length > 0 && districtId == 0 && constituencyId == 0){
				getSummary(stateId,"State",glblSummeryCommitteeLvlIds);
				getMandalMuncipalDivisonStartedCommittees(stateId,glblSummeryCommitteeLvlIds);
			}else if(districtId != null && districtId.length > 0 && constituencyId == 0){
				getSummary(districtId,"District",glblSummeryCommitteeLvlIds);
				getMandalMuncipalDivisonStartedCommittees(districtId,glblSummeryCommitteeLvlIds);
			}else if(constituencyId != null && constituencyId.length > 0 ){
				getSummary(constituencyId,"Constituency",glblSummeryCommitteeLvlIds);
				getMandalMuncipalDivisonStartedCommittees(constituencyId,glblSummeryCommitteeLvlIds);
			}
				
			/* }else{
				getSummary(GlobalLocationId);
				getMandalMuncipalDivisonStartedCommittees(GlobalLocationId);
			} */
			/* getSummary(GlobalLocationId);
			getMandalMuncipalDivisonStartedCommittees(GlobalLocationId); */
		}
});
var slickCount = 0;
function getCommitteeMembersAvailableInfo(basicCommitteetypeId,levelId,levelValue,divId){
		var fromDate;
        var toDate;
		var committeeEnrollmentId = 1;
		$('.summeryCls').html('');
		if(divId == 'infoDivId'){
			   $("#"+levelValue+"infoTrId").show();
			$("#"+levelValue+"infoDivId").html('<img id="ajaxImgStyle"  class="ajximgCls" style="margin-center: 10px;width:80px;" src="images/Loading-data.gif"/>');
			committeeEnrollmentId =$("#tdpCommitteeYearId1").val();
			 var date =$("#reportrange1").val();
        
        var dates = date.split("-");
        fromDate = dates[0];
        toDate = dates[1]; 
			  }else{
				  $("#"+divId).html('<img id=""  class="ajximgCls" style="margin-center: 10px;width:90px;" src="images/Loading-data.gif"/>');
				  var dates = $("#reportrange").val();
		var dateArray = dates.split("-");
		 fromDate=dateArray[0];
		 toDate=dateArray[1];
		committeeEnrollmentId =$("#tdpCommitteeYearId").val();
			  }
		
		 var jsObj={
		         levelId:levelId,levelValue:levelValue,enrollmentYrId:committeeEnrollmentId,startDate:fromDate,endDate:toDate,
				 basicCommitteetypeId:basicCommitteetypeId
		       };
			   
		 $.ajax({
			type : "GET",
			url : "getCommitteeMembersAvailableInfoAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			//console.log(result);
			if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
			    }
				//Summary Building
				slickCount = slickCount+1;
				var counts = result.result;
				var str='';
				str+='<div class="col-md-4 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">';
				str+='<h4><span id="affComitteeMainTitle"></span></h4>';
				str+='<hr style="margin: 0px;">';
				str+='<ul class="list-inline pull-right ">';
					str+='<li><span style="color:#777;font-weight:bold;">TOTAL </span></li>';
					str+='<li><span style="color:orange;font-weight:bold;">PROPOSED </span></li>';
					str+='<li><span style="color:#449D44;font-weight:bold;">FINALIZED</span></li>';
					str+='<li><span style="color:#F65050;font-weight:bold;">VACANCY</span></li>';
				str+='</ul>';
				
			str+='</div>';	
			
				var str ='<div id="variable-width'+slickCount+'" class="row">';
			    for(var i in  counts){
					str+='<div class="col-md-3 col-xs-12 col-sm-3">';
					str+='<div class=" slick_widget text-center">';
					str+='	<h5>'+counts[i].locationName+'</h5>';
					str+='	<ul class="list-inline text-center" >';
					
					if(counts[i].totalCount!=0){
						str+='<li class="btn btn-xs  greyClass"  disabled="disabled">'+counts[i].totalCount+'</li>';
					}else{
						str+='<li class="btn btn-xs  greyClass" disabled="disabled">N/A</li>';
					}
					
					if(counts[i].roleType != null && counts[i].roleType == 'P'){
						if(counts[i].proposedCount != null && counts[i].proposedCount != 0){
						   str+='<li class="btn btn-xs orangeCls" disabled="disabled" style="margin-left: 5px;">'+counts[i].proposedCount+'</li>';
						}else{
							str+='<li class="btn btn-xs orangeCls" disabled="disabled" style="margin-left: 5px;">0</li>';
						}
					}else{
						str+='<li class="btn btn-xs orangeCls" disabled="disabled" style="margin-left: 5px;">0</li>';
					}
					
					if(counts[i].finalizedCount != 0){
					  str+='<li class="btn btn-xs btn-success" disabled="disabled" style="margin-left: 5px;font-weight:bold !important">'+counts[i].finalizedCount+'</li>';	
					}else{
						str+='<li class="btn btn-xs btn-success" disabled="disabled" style="margin-left: 5px;font-weight:bold !important">0</li>';
					}
					
					if(counts[i].totalCount!= 0){
						str+='<li class="btn btn-xs redCls" disabled="disabled" style="margin-left: 5px;">'+counts[i].vaccancyCount+'</li>';
					}else{
						str+='<li class="btn btn-xs redCls" disabled="disabled" style="margin-left: 5px;">N/A</li>';
					}
					str+='	</ul>';
					str+='</div>';
					str+='</div>';
			   }
			   
			   if(divId == 'infoDivId'){
			   $("#"+levelValue+"infoDivId").html(str);
			  }else{
				  $("#"+divId).html(str);
			  }
			   /*  $('#variable-width'+slickCount).slick({
					  slide: '.slickW',
					  slidesToShow: 3,
					  slidesToScroll: 3,
					  dots: false,
					  infinite: false,
					  speed: 300,
					  autoplay: false,
					  autoplaySpeed: 2000,
					  variableWidth: true
					}); */
				/*var getSlickWidth = ($('#dialogSummary').width()-($('#dialogSummary').width()) * (0.2)) ;
				//alert(getSlickWidth)
			   $('#variable-width'+slickCount).find(".slick-track").css("width",getSlickWidth);*/
		});
	}
	$(document).on("click",".modalCloseBtn",function(){
		setTimeout(function(){
			$("body").addClass("modal-open");
		},500)
	});
getUserWiseDetails();	
</script>		
<script>
var tableToExcel = (function() {
   var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
</body>
</html>
