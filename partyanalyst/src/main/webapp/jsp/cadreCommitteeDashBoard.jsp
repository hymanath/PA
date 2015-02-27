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
    background-color: #f3f3f3 !important;
}

#constiTableId_filter,#districtTableId_filter{font-size:12px !important;font-family:verdana;font-weight:bold;}
	</style>

<script>
	var userAccessType = '${pageAccessType}';
</script>	
</head>
<body>
	 <header style="align:center;background-color:#ef4036; display:flex;border-bottom:4px solid #13a751;">
		 	<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3 text-center">
				<img src="images/cadreCommitee/Committees_2014_logo.png" class="m_top10" title="Committee Logo" alt="committee" />
			</div>
			<div class="col-md-3  col-xs-3 col-sm-3">
               
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top20" data-toggle="dropdown" aria-expanded="false" style="margin-top: 60px;">
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
    	<div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 ">
                    <h3 class="panel-header">COMMITTEE DASHBOARD</h3>
                    <hr style="border-color:#F00;margin:0px 0px 10px 0px;" />
                </div>
        </div>
		
		<div class="row">
               <div class="col-md-12 col-xs-12  col-sm-12" style="padding-right:0px; padding-bottom:5px;">
					<div id="reportrange" class="pull-right" style="background:rgba(0,0,0,0.1); cursor: pointer; padding: 5px 10px; border: 1px solid rgba(0,0,0,0.1);">
					  <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
					  <span></span> <b class="caret"></b>
					</div>
                </div>
        </div>
		
    	<div class="row" id="APStateDiv" style="display:none;">
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
								<td colspan="6">
									<button id="mandal" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','mandal')">Mandal</button> | 
									<button id="town" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','town')">Town</button> |	
									<button id="division" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','division')">Division</button> |									
									<button id="mandalAll" class="btn btn-xs btn-success highlight highlightClick " onclick="getCommitteeDetails('AP','mandalAll')">All</button>
									<div class="pull-right">
										<img width="16" height="16" id="ajaxImageIdAPmandal" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;"/>
									</div>
								</td>
							</tr>
							<tr>
								<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div1"></div></h4></td>
								
								<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span><br/> Committees
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div29"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div2"></div></h5>
											</td>
										</tr>
									</table>
								</td>
								
								<td style="padding:10px;" width="10%"><span class="text-danger">Completed</span><br/> Committees<table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div3"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><div id="div4"></div>
                                  
									
									
								</li></td></tr></table></td>
								<td style="padding:10px;" width="20%"><span class="text-success">Started</span><br/>Affiliated Committees<br/><h4 class="m_top0"><div id="div5"></div></h4></td>
								<td style="padding:10px;" width="20%"><span class="text-success">Completed </span><br/>Affliated Committees<br/>
                                <h4 class="m_top0">
								<ul class="nav navbar-nav">
                              <li>
								<a><div id="div6"></div></a>
                              </h4></td>
								<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div7"></div></h4></td>
							<div id="apMandalDiv"></div>
							</tr>
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
								<td colspan="6">
										<button id="village"  class="btn btn-xs btn-success highlightClick1" onclick="getCommitteeDetails('AP','village')">Village</button> | 
										<button id="ward" class="btn btn-xs btn-success highlightClick1" onclick="getCommitteeDetails('AP','ward')">Ward</button> |										
										<button id="villageAll" class="btn btn-xs btn-success highlight highlightClick1 " onclick="getCommitteeDetails('AP','villageAll')">All</button>
									<div class="pull-right">
										<img width="16" height="16" id="ajaxImageIdAPvillage" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;"/>
									</div>
								</td>
							</tr>
							<tr>
								<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div8"></div></h4></td>
								<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span><br/> Committees
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div32"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div9"></div></h5>
											</td>
										</tr>
									</table>
								</td>
							   <td style="padding:10px;" width="10%"><span class="text-danger">Completed</span><br/> Committees<table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div10"></div></h4></td><td  class="row-table">
							   <ul class="nav navbar-nav">
								<li>
									<div id="div11"></div>
                                   
								</li>	
									
									</td></tr></table></td>
								<td style="padding:10px;" width="20%"><span class="text-success">Started</span><br/>Affiliated Committees<br/><h4 class="m_top0"><div id="div12"></div></h4></td> 
								<td style="padding:10px;" width="20%"><span class="text-success">Completed</span><br/>Affiliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div13"></div></a>
                               </h4></td>
								<td style="padding:10px;" width="28%">TOTAL <br/><b>MEMBERS</b><h4 class="m_top0"><div id="div14"></div></h4></td>
								<div id="apVillageDiv"></div>
							</tr>
						</table>
					</td>
				</tr>
			</table>
        </div>
		
		<div class="row" id="districtDiv" style="display:none;"> 
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
								<td colspan="6">
									<button id="mandal" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','mandal')">Mandal</button> | 
									<button id="town" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','town')">Town</button> |	
									<button id="division" class="btn btn-xs btn-success highlightClick" onclick="getCommitteeDetails('AP','division')">Division</button> |									
									<button id="mandalAll" class="btn btn-xs btn-success highlight highlightClick " onclick="getCommitteeDetails('AP','mandalAll')">All</button>
									<div class="pull-right">
										<img width="16" height="16" id="ajaxImageIdAPmandal" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;"/>
									</div>
								</td>
							</tr>
							<tr>
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
								
								<td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> <br/>Committees
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
								<td style="padding:10px;" width="20%"><span class="text-success">Completed </span><br/>Affliated Committees<br/>
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
								<td colspan="6">
										<button id="village"  class="btn btn-xs btn-success highlightClick1" onclick="getCommitteeDetails('AP','village')">Village</button> | 
										<button id="ward" class="btn btn-xs btn-success highlightClick1" onclick="getCommitteeDetails('AP','ward')">Ward</button> |										
										<button id="villageAll" class="btn btn-xs btn-success highlight highlightClick1 " onclick="getCommitteeDetails('AP','villageAll')">All</button>
									<div class="pull-right">
										<img width="16" height="16" id="ajaxImageIdAPvillage" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;"/>
									</div>
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
							   <td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> <br/>Committees<table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div101"></div></h4></td><td  class="row-table">
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
						</table>
					</td>
				</tr>
			</table>
        </div>
		
        <div class="row" id="TGStateDiv"  style="display:none;">
			<table class="table table-bordered" width="100%"  >
        	<tr>
        	    <td width="22%" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);text-align:center;padding: 0px;">
					<div class="col-md-12" >
						<h4>TELANGANA</h4>
						<div class="row" id="totalTSCount">
								
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
							<td colspan="6">
								<button id="tsMandal" class="btn btn-xs btn-success highlightClick2" onclick="getCommitteeDetails('TS','mandal')";>Mandal</button> | 
								<button id="tsTown" class="btn btn-xs btn-success highlightClick2" onclick="getCommitteeDetails('TS','town')";>Town</button> |
								<button id="tsDivision" class="btn btn-xs btn-success highlightClick2" onclick="getCommitteeDetails('TS','division')";>Division</button> |
								<button id="tsMandalAll" class="btn btn-xs  btn-success highlight highlightClick2 " onclick="getCommitteeDetails('TS','mandalAll')";>All</button>
								<div class="pull-right">
										<img width="16" height="16" class="ajaxImageIdTSmandal" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;"/>
								</div>
							</td>
						</tr>
						<tr>
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div15"></div></h4></td>
							<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span> Committees<br/>
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div35"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div16"></div></h5>
											</td>
										</tr>
									</table>
								</td>
						   <td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div17"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><div id="div18"></div>
                                 
									
								</li></td></tr></table></td>
							<td style="padding:10px;" width="20%"><span class="text-success">Started</span><br/>Affiliated Committees<br/><h4 class="m_top0"><div id="div19"></div></h4></td>
							<td style="padding:10px;" width="20%"><span class="text-success">Completed</span><br/>Affiliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div20"></div></a>
                               
                                </h4></td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div21"></div></h4></td>
							<div id="tsMandalDiv"></div>
						</tr>
						<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
							<td colspan="6">
								<button id="tsVillage" class="btn btn-xs btn-success highlightClick3" onclick="getCommitteeDetails('TS','village')";>Village</button> | 
								<button id="tsWard" class="btn btn-xs btn-success highlightClick3" onclick="getCommitteeDetails('TS','ward')";>Ward</button> |
								
								<button id="tsVillageAll" class="btn btn-xs btn-default  btn-success highlight highlightClick3" onclick="getCommitteeDetails('TS','villageAll')";>All</button>
								
								<div class="pull-right">
										<img width="16" height="16" class="ajaxImageIdTSvillage" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;"/>
								</div>
							</td>
						</tr>
						<tr>
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div22"></div></h4></td>
							<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span> Committees<br/>
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div38"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div23"></div></h5>
											</td>
										</tr>
									</table>
								</td>
						   <td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div24"></div></h4></td><td  class="row-table">
						   <ul class="nav navbar-nav">
                              <li><div id="div25"></div>
                                   
									
								</li>
									</td></tr></table></td>
							<td style="padding:10px;" width="20%"><span class="text-success">Started</span><br/>Affiliated Committees<br/><h4 class="m_top0"><div id="div26"></div></h4></td>
							<td style="padding:10px;" width="20%"><span class="text-success">Completed</span><br/>Affiliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div27"></div></a>
                             </h4></td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div28"></div></h4></td> 
							<div id="tsVillageDiv"></div>
						</tr>
					</table>
				</td>
      	    </tr>
      	  </table>
        </div>
		
        <div class="row m_top20">        	 
            <div class="row" id="commityClsi">
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <h4 id="headingId" class="text-success" style="display:inline-block">DISTRICT WISE COMMITTEES</h4>
					
					 <span class="btn btn-success btn-xs form-inline" id="statesBtnsId" style="display:none;">
						<label class="radio"><input type="radio" id="APId" style="vertical-align: text-bottom;" class="stateRd" value="AP" name="selectstate" checked="true">&nbsp;AP &nbsp;&nbsp;&nbsp;</label>
						<label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="stateRd" value="TS" name="selectstate" id="TSId">&nbsp;TS &nbsp;&nbsp;&nbsp;</label>
					</span>
					 <span class="btn btn-success btn-xs form-inline">
						<label class="radio"><input type="radio" id="districtId" style="vertical-align: text-bottom;" class="levelRd" value="district" name="select" checked="true">&nbsp;District &nbsp;&nbsp;&nbsp;</label>
						<label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="levelRd" value="consti" name="select">&nbsp;Constituency &nbsp;&nbsp;&nbsp;</label>
					</span>
					<span class="btn btn-success btn-xs form-inline">
						<label class="radio"><input type="checkbox" id="villageId" style="vertical-align: text-bottom;" class="scopeRd" value="village" name="selectCheck"  checked="true">&nbsp;VILLAGE / WARD &nbsp;</label>
					</span>&nbsp;&nbsp;
					<span class="btn btn-success btn-xs form-inline">
						<label class="checkbox"><input type="checkbox" id="mandalId" style="vertical-align: text-bottom;" class="scopeRd" value="mandal" name="selectCheck">&nbsp;TOWN / MANDAL / DIVISION &nbsp;&nbsp;</label>
					</span>
					
					<span class="btn btn-info pull-right excelId form-inline" onclick="exportToExcel()" style="margin-right: -200px;display:none;"> Export To Excel </span>
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
			  <div class="modal-dialog modal-lg">
				<div class="modal-content">
					
					
				<!-- Summary Block For POPUP	-->		
				
				<div class="container" style="width:881px;">
					<!--Content Start-->
					<div class="row" style="text-align:center;">
						<div class="col-md-6 col-md-offset-3">
							<h3 class="panel-header"><div id="mainCommTitleDivId">COMMITTEE SUMMARY</div></h3>
							<hr style="border-color:#F00;margin-top:10px;" />
						</div>
						
							<span type="button" style="font-size:30px;cursor:pointer;" class="pull-right" data-dismiss="modal" aria-hidden="true">&times;</span>
						
					</div>
					<!-- First Block Start-->
					<div class="row">
						<div class="col-md-12">
							<h3 style="color:#090" >MANDAL / TOWN / DIVISION</h2>
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
						<div class="col-md-12">
							<h3 style="color:#090" >VILLAGE / WARD</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div id="villageMainTableDivId"></div>
							
							<div id="villageAfflicatedTableDivId"></div>
							
							
						<img id="comitteeCntAjax" src="./images/icons/search.gif" alt="Processing Image" style="display:none;margin-left:400px;"/>
							<table class="table table-condensed table-bordered" style="border:5px solid #669934;background-color:rgba(0,0,0,0.1);border-radius:2px;" id="CommitteeDetails">
							</table>
						</div>  
					   
						 <img id="comitteeMemberAjax" src="./images/icons/search.gif" alt="Processing Image" style="display:none;margin-left:400px;"/>
						<div class="col-md-8 col-md-offset-1" id="committeeMemberDiv" >

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
	
	</script>
	
	<!----Bootstrap Date Range Picker Script---->
		<script type="text/javascript">
               $(document).ready(function() {
					$('#APStateDiv').hide();
					$('#TGStateDiv').hide();
					$('#districtDiv').hide();
					
					$('#statesBtnsId').hide();
                  var cb = function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                    //alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
                  }

                  var optionSet1 = {
                    startDate: moment().subtract(29, 'days'),
                    endDate: moment(),
                    minDate: '01/01/2012',
                    maxDate: '12/31/2015',
                    dateLimit: { days: 60 },
                    showDropdowns: true,
                    showWeekNumbers: true,
                    timePicker: false,
                    timePickerIncrement: 1,
                    timePicker12Hour: true,
                   /* ranges: {
                       'Today': [moment(), moment()],
                       'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                       'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                       'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                       'This Month': [moment().startOf('month'), moment().endOf('month')],
                       'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                    }, */
                    opens: 'left',
                    buttonClasses: ['btn btn-default'],
                    applyClass: 'btn-small btn-success rangeButton',
                    cancelClass: 'btn-small',
                    format: 'MM/DD/YYYY',
                    separator: ' to ',
                    locale: {
                        applyLabel: 'Submit',
                        cancelLabel: 'Clear',
                        fromLabel: 'From',
                        toLabel: 'To',
                        customRangeLabel: 'Custom',
                        daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
                        monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                        firstDay: 1
                    }
                  };

                  var optionSet2 = {
                    startDate: moment().subtract(7, 'days'),
                    endDate: moment(),
                    opens: 'left',
                    ranges: {
                       'Today': [moment(), moment()],
                       'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                       'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                       'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                       'This Month': [moment().startOf('month'), moment().endOf('month')],
                       'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                    }
                  };

                  $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

                  $('#reportrange').daterangepicker(optionSet1, cb);

                  $('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
                  $('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
                  $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
                    console.log("apply event fired, start/end dates are " 
                      + picker.startDate.format('MMMM D, YYYY') 
                      + " to " 
                      + picker.endDate.format('MMMM D, YYYY')
                    ); 
                  });
                  $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });

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
					
					if(userAccessType=="MP"){
						$("#commityClsi").hide();
					}else{
						$("#commityClsi").show();
					}
					//console.log(userAccessType);
					if(userAccessType == 'ALL'){
						getCommitteeCountByState("AP");
						getCommitteeCountByState("TS");
						getCommitteeDetails("AP","mandalAll");
						getCommitteeDetails("AP","villageAll");	
						getCommitteeDetails("TS","mandalAll");	
						getCommitteeDetails("TS","villageAll");
						getDistrictWiseCommittesSummary();
					}else if(userAccessType == 'TS'){
						getCommitteeCountByState("TS");
						getCommitteeDetails("TS","mandalAll");	
						getCommitteeDetails("TS","villageAll");
						getDistrictWiseCommittesSummary();
					}
					else if(userAccessType == 'AP'){
						getCommitteeCountByState("AP");
						getCommitteeDetails("AP","mandalAll");
						getCommitteeDetails("AP","villageAll");
						getDistrictWiseCommittesSummary();
					}else{
						
						if(userAccessType=="MP"){
							getCommitteeCountByState("AP");
							getCommitteeDetails("AP","mandalAll");
							getCommitteeDetails("AP","villageAll");
							getConstituencyWiseCommittesSummary();
						}else{
							getCommitteeCountByState("AP");
							getCommitteeDetails("AP","mandalAll");
							getCommitteeDetails("AP","villageAll");	
							getDistrictWiseCommittesSummary();
						}
						
						
					}
               });
			   
               </script>
	<!----/Bootstrap Date Range Picker Script END---->
			   
			   		
	<script>
	
	$(document).on('click','.rangeButton',function(){
			
					getCommitteeDetails("AP","mandalAll");
					getCommitteeDetails("AP","villageAll");	
					getCommitteeDetails("TS","mandalAll");	
					getCommitteeDetails("TS","villageAll");
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
	
	var startDate=$(".dp_startDate").val();
	var endDate=$(".dp_endDate").val();
	
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

		var jObj = {
			levelIdsArr : levelIdsArr,
			state:state,
			startDate:startDate,
			endDate:endDate,
			task:"committeeDetails"
		}
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
							$("#div2").html('<a id=\''+level+'IdAPstarted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;">['+result.mainCommittees+']</a>');
						}
						else{						
							$("#div2").html('<span style="margin-left:0px;">[0]</span>');
						}
						
						//$("#div2").html('['+result.mainCommittees+']');						
						
						$("#div3").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null){
							$("#div4").html('<a id=\''+level+'IdAPcompleted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;">['+result.completedCommittees+']</a>');
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
						
						
						$("#div9").html('<a id=\''+level+'IdAPstarted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;">['+result.mainCommittees+']</a>');
						}
						else{
						$("#div9").html('<span style="margin-left:0px;">[0]</span>');
						}
						$("#div10").html(result.completedCommitteePerc+"%");
				
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						//$("#div11").html('&nbsp;&nbsp;&nbsp;['+result.completedCommittees+']');
						$("#div11").html('<a id=\''+level+'IdAPcompleted\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;">['+result.completedCommittees+']</a>');
						
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
						
						$("#div16").html('<a id=\''+level+'IdTSstarted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;">['+result.mainCommittees+']</a>');
						}
						else{
						$("#div16").html('<span style="margin-left:0px;">[0]</span>');
						}
						$("#div17").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						//$("#div18").html('&nbsp;&nbsp;&nbsp;['+result.completedCommittees+']');
						$("#div18").html('<a id=\''+level+'IdTScompleted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;">['+result.completedCommittees+']</a>');
						
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
						$("#div23").html('<a id=\''+level+'IdTSstarted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'started\')" style="cursor:pointer;">['+result.mainCommittees+']</a>');
						}else{						
							$("#div23").html('<span style="margin-left:0px;">[0]</span>');
						}
						
						$("#div24").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null)
						
						//$("#div25").html('&nbsp;&nbsp;&nbsp;['+result.completedCommittees+']');
						$("#div25").html('<a id=\''+level+'IdTScompleted\' class="" onClick="getMainCommitteeMembersCount(\'TS\',\''+level+'\',\'main\','+1+'\,\'completed\')" style="cursor:pointer;">['+result.completedCommittees+']</a>');
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
					}
				}
			  }
			  else{
				  
				  if(level == 'mandal' || level == 'town' || level == 'division' || level == 'mandalAll')
					{
						$('#ajaxImageId'+state+'mandal').hide();
						
						$("#totalMainCount1").html(result.committeesCount);
						
						$("#percCount").html(result.startedCommitteePerc+"%");
						$("#percDtals").html('['+result.mainCommittees+']');						
						
						$("#compltdPerc").html(result.completedCommitteePerc+"%");
						
						if(result.completedCommittees > 0 && result.completedCommittees != null){
							$("#div41").html('<a id=\''+level+'IdAP\' class="btn" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+')" style="cursor:pointer;">['+result.completedCommittees+']</a>');
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
						$("#div111").html('<a id=\''+level+'IdAP\' class="" onClick="getMainCommitteeMembersCount(\'AP\',\''+level+'\',\'main\','+1+')" style="cursor:pointer;">['+result.completedCommittees+']</a>');
						
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
		var jObj = {
			state:state,
			task:"committeeDetails"
		}
				
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
		var state = state; 
		var mandalCheck=  $('#mandalId').is(':checked')?"true":"false";
		var villageCheck=  $('#villageId').is(':checked')?"true":"false";
		var selected = $("input[type='radio'][name='selectstate']:checked");
		if (selected.length > 0) {
			state = selected.val();
		}
		var startDate=$(".dp_startDate").val();
		var endDate=$(".dp_endDate").val();
		
		var jObj = {
			startDate:startDate,
			endDate:endDate,
			state:state,
			mandalCheck:mandalCheck,
			villageCheck:villageCheck
		}
				
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
			
			buildResultDistrictSummary(result,mandalCheck,villageCheck);	
						
		});
	}
	
		
	
	function buildResultDistrictSummary(result,mandalCheck,villageCheck){
		var districtInfoArr = [];
		var villageInfoArr = [];
		$("#headingId").html("DISTRICT WISE COMMITTEES");
		//$("#tableHeadingId").html("DISTRICT");
		$(".excelId").show();
		var str = '';
		
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
						
		str+='<table class="table table-bordered table-condensed " id="districtTableId" style="width:100%; background-color:rgba(0,0,0,0.1);">';
       
		if(mandalCheck == "true" && villageCheck == "true"){
			str+='<thead>';
            str+='<tr>';
			str+='<th rowspan="2" style="text-align:center">District Name</th>';
            str+='<th style="text-align:center" colspan="6">TOWN / MANDAL / DIVISION</th>';
            str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
            str+='</tr>';
            str+='<tr>';
            str+='<th >Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed</th>';
			str+='<th>Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed</th>';
            str+='</tr>';			
			str+='</thead>';	
			
		}
		else if(mandalCheck == "true"){
			str+='<thead>';
			str+='<tr>';
			str+='<th rowspan="2" style="text-align:center">District Name</th>';
            str+='<th style="text-align:center" colspan="6">TOWN / MANDAL / DIVISION</th>';
            str+='</tr>';
            str+='<tr>';
			str+='<th>Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed</th>';
			str+='</tr></thead>';	
		}
		else if(villageCheck == "true"){
			str+='<thead>';
			str+='<th rowspan="2" style="text-align:center">District Name</th>';
            str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
            str+='</tr>';
            str+='<tr>';
			str+='<th>Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed</th>';
			str+='</tr></thead>';	
		}
		str+='<tbody>';
		
		for(var i in result){
			if(result[i].townMandalDivisionVO != null || result[i].villageWardVO != null){
			str += '<tr>';
			str += '<td>'+result[i].districtName+'</td>';
			if(mandalCheck == "true"){
			
			if(result[i].townMandalDivisionVO!=null){
				if(result[i].townMandalDivisionVO.totalCommittees!=null){
					str += '<td>'+result[i].townMandalDivisionVO.totalCommittees+'</td>';
					mandTotal=mandTotal+result[i].townMandalDivisionVO.totalCommittees;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainStarted!=null){
					//str += '<td>'+result[i].townMandalDivisionVO.mainStarted+'<span id="mini-pie-chart-district'+i+'" class="pull-right mini-pie-chart-district"></span></td>';
					str += '<td>'+result[i].townMandalDivisionVO.mainStarted+'</td>';
					mandStarted=mandStarted+result[i].townMandalDivisionVO.mainStarted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainCompleted!=null){
					str += '<td>'+result[i].townMandalDivisionVO.mainCompleted+'</td>';
					mandCompleted=mandCompleted+result[i].townMandalDivisionVO.mainCompleted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].townMandalDivisionVO.membersCount!=null){
					str += '<td>'+result[i].townMandalDivisionVO.membersCount+'</td>';
					mandMembers=mandMembers+result[i].townMandalDivisionVO.membersCount;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflStarted!=null){
					str += '<td>'+result[i].townMandalDivisionVO.afflStarted+'</td>';
					mandAfStarted=mandAfStarted+result[i].townMandalDivisionVO.afflStarted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflCompleted!=null){
					str += '<td>'+result[i].townMandalDivisionVO.afflCompleted+' </td>';
					mandAfCompleted=mandAfCompleted+result[i].townMandalDivisionVO.afflCompleted;
				}else{
					str += '<td> - </td>';
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
					str += '<td>'+result[i].villageWardVO.totalCommittees+'</td>';
					panTotal=panTotal+result[i].villageWardVO.totalCommittees;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.mainStarted!=null){
					//str += '<td>'+result[i].villageWardVO.mainStarted+'<span id="mini-pie-chart-village'+i+'" class="pull-right mini-pie-chart-village"></span></td>';
					str += '<td>'+result[i].villageWardVO.mainStarted+'</td>';
					panStarted=panStarted+result[i].villageWardVO.mainStarted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.mainCompleted!=null){
					str += '<td>'+result[i].villageWardVO.mainCompleted+'</td>';
					panCompleted=panCompleted+result[i].villageWardVO.mainCompleted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.membersCount!=null){
					str += '<td>'+result[i].villageWardVO.membersCount+' </td>';
					panMembers=panMembers+result[i].villageWardVO.membersCount;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.afflStarted!=null){
					str += '<td>'+result[i].villageWardVO.afflStarted+'</td>';
					panAffStarted=panAffStarted+result[i].villageWardVO.afflStarted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.afflCompleted!=null){
					str += '<td>'+result[i].villageWardVO.afflCompleted+' </td>';
					panAffCompleted=panAffCompleted+result[i].villageWardVO.afflCompleted;
				}else{
					str += '<td> - </td>';
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
			str += '</tr>';
			
			if(result[i].townMandalDivisionVO != null){
				var details = [];
				if(result[i].townMandalDivisionVO.mainStarted != null){
					details = [result[i].townMandalDivisionVO.totalCommittees, result[i].townMandalDivisionVO.mainStarted];
				}else{
					details = [result[i].townMandalDivisionVO.totalCommittees, 0];
				}
				
				districtInfoArr.push(details);
			}else{
				 var details = [0, 0];
				districtInfoArr.push(details);
			}
			
			
			if(result[i].villageWardVO != null){
				var details = [];
				if(result[i].villageWardVO.mainStarted != null){
					details = [result[i].villageWardVO.totalCommittees, result[i].villageWardVO.mainStarted];
				}else{
					details = [result[i].villageWardVO.totalCommittees, 0];
				}
				//var villageDetails  = [result[i].villageWardVO.totalCommittees, result[i].villageWardVO.mainStarted];
				villageInfoArr.push(details);
			}else{
				var villageDetails  = [0, 0];
				villageInfoArr.push(villageDetails);
			}
			
			}
		}
		str += '</tbody><tfoot><tr class="no-sort" style="font-weight:bold;">';
		if(mandalCheck == "true" && villageCheck == "true"){
		str	+= '<td>TOTAL</td><td>'+mandTotal+'</td>'; 	
		str += '<td>'+mandStarted+'</td>'; 	
		str += '<td>'+mandCompleted+'</td>'; 	
		str += '<td>'+mandMembers+'</td>'; 	
		str += '<td>'+mandAfStarted+'</td>'; 	
		str += '<td>'+mandAfCompleted+'</td>';
		str += '<td>'+panTotal+'</td>'; 	
		str += '<td>'+panStarted+'</td>'; 	
		str += '<td>'+panCompleted+'</td>'; 	
		str += '<td>'+panMembers+'</td>'; 	
		str += '<td>'+panAffStarted+'</td>'; 	
		str += '<td>'+panAffCompleted+'</td>';		
		}
		else if(mandalCheck=="true"){
		str	+= '<td>TOTAL</td><td>'+mandTotal+'</td>'; 	
		str += '<td>'+mandStarted+'</td>'; 	
		str += '<td>'+mandCompleted+'</td>'; 	
		str += '<td>'+mandMembers+'</td>'; 	
		str += '<td>'+mandAfStarted+'</td>'; 	
		str += '<td>'+mandAfCompleted+'</td>'; 	
		}
		else if(villageCheck=="true"){
		str	+= '<td>TOTAL</td><td>'+panTotal+'</td>'; 			
		str += '<td>'+panStarted+'</td>'; 	
		str += '<td>'+panCompleted+'</td>'; 	
		str += '<td>'+panMembers+'</td>'; 	
		str += '<td>'+panAffStarted+'</td>'; 	
		str += '<td>'+panAffCompleted+'</td>';
}
		str += '</tr></tfoot></table>';			
		$("#distSummaryBody").html(str);
		$(".excelId").show();
		$("#districtTableId").dataTable({
			"iDisplayLength": 50,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
		
		if($('.mini-pie-chart-district').length > 0 ) {
			var visitData = districtInfoArr;
			var params = {
				type: "pie",
				 sliceColors: ["#0B3B0B", "#B18904"]

			}
			
			var vdata = villageInfoArr;
			var params1 = {
				type: "pie",
				 sliceColors: ["#0B3B0B", "#B18904"]

			}
			
			
			for(var t in result){
				$('#mini-pie-chart-district'+t+'').sparkline(visitData[t], params);
				$('#mini-pie-chart-village'+t+'').sparkline(vdata[t], params1);
			}
		}
		
		
			
	}
	function getMainCommitteeMembersCount(state,level,type,committeeId,committeeType){
		
		var startDate=$(".dp_startDate").val();
		var endDate=$(".dp_endDate").val();
		
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
						for(var i in result){
							str+='<li class="list-group-item "><span class="badge">'+result[i].membersCount+'</span>1 MEMBER COMMITTEE</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount1+'</span>2-4 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount2+'</span>5-7 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount3+'</span>ABOVE 7 MEMBER COMMITTEES</li>';
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
		
		var startDate=$(".dp_startDate").val();
		var endDate=$(".dp_endDate").val();
		
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
					alert('#'+level+'Id'+state+committeeType+'');
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
		
		var startDate=$(".dp_startDate").val();
		var endDate=$(".dp_endDate").val();
		
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
		var state = state; 
		var jObj = {
			state:state,
			levelIdsArr:levelIdsArr,
			startDate  :startDate,
			endDate    :endDate,
			committeeType:committeeType,
			task:"mainCommitteeMemberCnt",
		}
				
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
				
				if(!$(this).is(trgt) && $(this).has(trgt).length === 0 && !$(trgt).is('.multiLevelCls') && !$(trgt).is('.multilevelli') && !$(trgt).is('.multiLevelLiA')) {
					$("#"+attrId).popover('hide');
					$(".multiLevelLiA").popover('hide');
					$("#"+attrId).removeClass('clearCls');
				}
			});
		}
		
		
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
		$(".excelId").hide();
		$("#distSummaryBody").html('');
		$("#summaryAjax").show();
		var selected = $("input[type='radio'][name='selectstate']:checked");
		var mandalCheck=  $('#mandalId').is(':checked')?"true":"false";
		var villageCheck=  $('#villageId').is(':checked')?"true":"false";

		if (selected.length > 0) {
			state = selected.val();
		}
		
		var startDate = $(".dp_startDate").val();
		var endDate=$(".dp_endDate").val();	
		var jObj = {
			startDate:startDate,
			endDate:endDate,
			state:state,
			mandalCheck:mandalCheck,
			villageCheck:villageCheck
		}
				
		$.ajax({
          type:'GET',
          url: 'getConstituencyWiseCommittesSummaryAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
	
		$("#summaryAjax").hide();
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				
					buildConstiWiseSummary(result,mandalCheck,villageCheck);	
				
		});
	}
	
	function showAdvanceDashBoard(constiIdReq){
		//window.location.href="constituencyCommitteeSummaryAction.action?accessConstituencyId="+constiIdReq;
		 window.open('constituencyCommitteeSummaryAction.action?accessConstituencyId='+constiIdReq+'','location=no','_blank'); 
	}
	
	function buildConstiWiseSummary(result,mandalCheck,villageCheck){
	
	
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
		var str = '';
		$("#distSummaryBody").html("");
		
		str+='<table class="table table-bordered table-condensed " id="constiTableId" style="width:100%; background-color:rgba(0,0,0,0.1);">';
       
		if(mandalCheck == "true" && villageCheck == "true"){
			str+='<thead>';
            str+='<tr>';
			str+='<th rowspan="2" style="text-align:center">Constituency No</th>';
			str+='<th rowspan="2" style="text-align:center">Constituency Name</th>';
            str+='<th style="text-align:center" colspan="6">TOWN / MANDAL / DIVISION</th>';
            str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
            str+='</tr>';
            str+='<tr>';
            str+='<th >Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed</th>';
			str+='<th>Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed</th>';
            str+='</tr>';			
			str+='</thead>';	
			
		}
		else if(mandalCheck == "true"){
			str+='<thead>';
			str+='<tr>';
			str+='<th rowspan="2"  style="text-align:center">Constituency No</th>';
			str+='<th rowspan="2" style="text-align:center">Constituency Name</th>';
			 str+='<th style="text-align:center" colspan="6">TOWN / MANDAL / DIVISION</th>';
            str+='</tr>';
            str+='<tr>';
			str+='<th>Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed</th>';
			str+='</tr></thead>';	
		}
		else if(villageCheck == "true"){
			str+='<thead>';
			str+='<th rowspan="2"  style="text-align:center">Constituency No</th>';
			str+='<th rowspan="2" style="text-align:center">Constituency Name</th>';
    
			str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
            str+='</tr>';
            str+='<tr>';
			str+='<th>Total</th>';
			str+='<th>Started</th>';
			str+='<th>Completed</th>';
			str+='<th>Members</th>';
			str+='<th>Affl Committee Started</th>';
			str+='<th>Affl Committee Completed</th>';
			str+='</thead>';	
		}
		str+='<tbody>';
		for(var i in result){
		if(result[i].townMandalDivisionVO != null || result[i].villageWardVO != null){
		str += '<tr>';
			str += '<td >'+result[i].constiNo+'</td>';
			str += '<td ><span style="font-size: 12px;">'+result[i].name+'</span>';
			
				str += '&nbsp;&nbsp;<span style="cursor: pointer;" title="Click Here For '+result[i].name+' Committee Summary Report" onclick="getPopUpForSummary('+result[i].constiId+',\''+result[i].name+'\');" class="glyphicon glyphicon-dashboard"></span>&nbsp;&nbsp;<span style="cursor: pointer;"  onclick="showAdvanceDashBoard('+result[i].constiId+');" title="Click Here For '+result[i].name+' Advance Dashboard"  class="glyphicon glyphicon-list-alt"></span>';
			
			str += '</td>';
			if(mandalCheck == "true"){
			
			if(result[i].townMandalDivisionVO!=null){
				if(result[i].townMandalDivisionVO.totalCommittees!=null){
					str += '<td>'+result[i].townMandalDivisionVO.totalCommittees+'</td>';
					mandTotal=mandTotal+result[i].townMandalDivisionVO.totalCommittees;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainStarted!=null){
					//str += '<td>'+result[i].townMandalDivisionVO.mainStarted+'<span id="mini-pie-chart-constituency'+i+'" class="pull-right mini-pie-chart-district"></span></td>';
					str += '<td>'+result[i].townMandalDivisionVO.mainStarted+'</td>';
					mandStarted=mandStarted+result[i].townMandalDivisionVO.mainStarted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainCompleted!=null){
					str += '<td>'+result[i].townMandalDivisionVO.mainCompleted+'</td>';
					mandCompleted=mandCompleted+result[i].townMandalDivisionVO.mainCompleted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].townMandalDivisionVO.membersCount!=null){
					str += '<td>'+result[i].townMandalDivisionVO.membersCount+'</td>';
					mandMembers=mandMembers+result[i].townMandalDivisionVO.membersCount;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflStarted!=null){
					str += '<td>'+result[i].townMandalDivisionVO.afflStarted+'</td>';
					mandAfStarted=mandAfStarted+result[i].townMandalDivisionVO.afflStarted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflCompleted!=null){
					str += '<td>'+result[i].townMandalDivisionVO.afflCompleted+' </td>';
					mandAfCompleted=mandAfCompleted+result[i].townMandalDivisionVO.afflCompleted;
				}else{
					str += '<td> - </td>';
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
					str += '<td>'+result[i].villageWardVO.totalCommittees+'</td>';
					panTotal=panTotal+result[i].villageWardVO.totalCommittees;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.mainStarted!=null){
					//str += '<td>'+result[i].villageWardVO.mainStarted+'<span id="mini-pie-chart-constiVillage'+i+'" class="pull-right mini-pie-chart-village"></span></td>';
					str += '<td>'+result[i].villageWardVO.mainStarted+'</td>';
					panStarted=panStarted+result[i].villageWardVO.mainStarted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.mainCompleted!=null){
					str += '<td>'+result[i].villageWardVO.mainCompleted+'</td>';
					panCompleted=panCompleted+result[i].villageWardVO.mainCompleted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.membersCount!=null){
					str += '<td>'+result[i].villageWardVO.membersCount+' </td>';
					panMembers=panMembers+result[i].villageWardVO.membersCount;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.afflStarted!=null){
					str += '<td>'+result[i].villageWardVO.afflStarted+'</td>';
					panAffStarted=panAffStarted+result[i].villageWardVO.afflStarted;
				}else{
					str += '<td> - </td>';
				}
				
				if(result[i].villageWardVO.afflCompleted!=null){
					str += '<td>'+result[i].villageWardVO.afflCompleted+' </td>';
					panAffCompleted=panAffCompleted+result[i].villageWardVO.afflCompleted;
				}else{
					str += '<td> - </td>';
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
		str	+= '<td>TOTAL</td><td>'+mandTotal+'</td>'; 	
		str += '<td>'+mandStarted+'</td>'; 	
		str += '<td>'+mandCompleted+'</td>'; 	
		str += '<td>'+mandMembers+'</td>'; 	
		str += '<td>'+mandAfStarted+'</td>'; 	
		str += '<td>'+mandAfCompleted+'</td>';
		str += '<td>'+panStarted+'</td>'; 	
		str += '<td>'+panCompleted+'</td>'; 	
		str += '<td>'+panMembers+'</td>'; 	
		str += '<td>'+panAffStarted+'</td>'; 	
		str += '<td>'+panAffCompleted+'</td>'; 	
	}	
	else if(mandalCheck=="true"){
		str	+= '<td>TOTAL</td><td>'+mandTotal+'</td>'; 	
		str += '<td>'+mandStarted+'</td>'; 	
		str += '<td>'+mandCompleted+'</td>'; 	
		str += '<td>'+mandMembers+'</td>'; 	
		str += '<td>'+mandAfStarted+'</td>'; 	
		str += '<td>'+mandAfCompleted+'</td>'; 	
	}
	else if(villageCheck=="true"){
		str	+= '<td>TOTAL</td><td>'+panTotal+'</td>'; 	
		str += '<td>'+panStarted+'</td>'; 	
		str += '<td>'+panCompleted+'</td>'; 	
		str += '<td>'+panMembers+'</td>'; 	
		str += '<td>'+panAffStarted+'</td>'; 	
		str += '<td>'+panAffCompleted+'</td>'; 	
	}
		str += '</tr></tfoot></table>';
		
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
	
	/* Script For Summary POpUP  */
	
	function getPopUpForSummary(id,name){
		$( "#dialogSummary" ).modal("show");
		
		$("#CommitteeDetails").html(""); 
		$("#committeeMemberDiv").html("");
		$("#mainCommTitleDivId").html(name.toUpperCase()+" COMMITTEE SUMMARY");
		
		getSummary(id);
		getMandalMuncipalDivisonStartedCommittees(id);
	
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
	
	function getSummary(id){
		var Id=id;
		var jObj={
			constituencyId:Id
		}
		 $("#villageMainTableDivId").html("");
		 $("#villageAfflicatedTableDivId").html("");
		 
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
				if(result!=null){
					var notStarted=result[0].mainComittees-(result[0].startedCount+result[0].mainComitteesConformed);
					var str='';
					str+='<table class="table table-condensed table-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
						str+='<tr><td colspan="4" style="background-color:#669934"><h4>MAIN COMMITTEE</h4></td></tr>';
								str+='<tr><td width="25%"><h2 style="display:inline;">'+result[0].mainComittees+'</h2> TOTAL</td>';
							  if(result[0].startedCount > 0)
							  str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Started\',2,'+id+')">'+result[0].startedCount+'</a></h2> STARTED</td>';
								else
								{
									str+='<td width="25%"><h2 style="display:inline;">'+result[0].startedCount+'</h2> STARTED</td>';
								}
							  if(result[0].mainComitteesConformed > 0)
							{
									 str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Conform\',2,'+id+')">'+result[0].mainComitteesConformed+'</a></h2> CONFIRMED</td>';
							}
									else
									{
							   str+='<td width="25%"><h2 style="display:inline;">'+result[0].mainComitteesConformed+'</h2> CONFIRMED</td>';
									}
									if(notStarted > 0)
									{
										str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'NotStarted\',2,'+id+')">'+notStarted+'</a></h2> NOT YET STARTED</td>';
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
						var notConformed=result[i].totalAffilatedCommittees-(result[i].affilatedStartedCount+result[i].affComitteesConformed); 	
						str1+='<tr><td width="20%">'+result[i].affilatedCommitteeName+'</td>';
						str1+='<td width="20%">'+result[i].totalAffilatedCommittees+'</td>';
						if(result[i].affilatedStartedCount != null && result[i].affilatedStartedCount > 0)
						 {
							str1+='<td width="20%"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Started\',2,'+id+')">'+result[i].affilatedStartedCount+'</a></td>';
						 }
							else
						 {
						str1+='<td width="20%">'+result[i].affilatedStartedCount+'</td>';
						 }
						
						 if(result[i].affComitteesConformed != null && result[i].affComitteesConformed > 0)
						 {
						 str1+='<td width="20%"> <a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Conform\',2,'+id+')">'+result[i].affComitteesConformed+'</a></td>';
						 }
						else
						 {
						str1+='<td width="20%">'+result[i].affComitteesConformed+'</td>';
						 }
						 if(notConformed > 0)
						 {
							 str1+='<td width="20%"> <a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'NotStarted\',2,'+id+')">'+notConformed+'</a></td>';
						 }
							 else
						 {
						str1+='<td width="20%">'+notConformed+'</td>';
						 }
						str1+='</tr>';
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
	
		function getMandalMuncipalDivisonStartedCommittees(id){
			
			var Id = id;
			var jObj={
				constituencyId:Id
			}
			
			$("#mandalMainCommitteDivId").html("");
			 $("#mandalAffliCommitteDivId").html("");
			
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
				if(result!=null){
					var notStarted=result[0].mainComittees-(result[0].startedCount+result[0].mainComitteesConformed);
					var str='';
					str+='<table class="table table-condensed table-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
						str+='<tr><td colspan="4" style="background-color:#669934"><h4>MAIN COMMITTEE</h4></td></tr>';
								str+='<tr><td width="25%"><h2 style="display:inline;">'+result[0].mainComittees+'</h2> TOTAL</td>';
							   if(result[0].startedCount != null && result[0].startedCount > 0)
								{
							   str+='<td width="25%"><h2 style="display:inline;" ><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Started\',1,'+id+')">'+result[0].startedCount+'</a></h2> STARTED</td>';
								}
								else
								{
									 str+='<td width="25%"><h2 style="display:inline;" >'+result[0].startedCount+'</h2> STARTED</td>';
								
								}
								if(result[0].mainComitteesConformed != null && result[0].mainComitteesConformed > 0)
								{
									  str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Conform\',1,'+id+')">'+result[0].mainComitteesConformed+'</a></h2> CONFIRMED</td>';
									
								}
								else
								{
							   str+='<td width="25%"><h2 style="display:inline;">'+result[0].mainComitteesConformed+'</h2> CONFIRMED</td>';
								}
								if(notStarted != null && notStarted > 0)
								{
							   str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'NotStarted\',1,'+id+')">'+notStarted+'</a></h2> NOT YET STARTED</td>';
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
						var notConformed=result[i].totalAffilatedCommittees-(result[i].affilatedStartedCount+result[i].affComitteesConformed); 	
						str1+='<tr><td width="20%">'+result[i].affilatedCommitteeName+'</td>';
						str1+='<td width="20%">'+result[i].totalAffilatedCommittees+'</td>';
						if(result[i].affilatedStartedCount != null && result[i].affilatedStartedCount > 0)
						 {
						str1+='<td width="20%"><a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Started\',1,'+id+')" style="cursor:pointer;">'+result[i].affilatedStartedCount+'</a></td>';
						 }
						 else
						 {
						str1+='<td width="20%">'+result[i].affilatedStartedCount+'</td>';
						 }
					if(result[i].affComitteesConformed != null && result[i].affComitteesConformed > 0)
						 {
						str1+='<td width="20%"><a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Conform\',1,'+id+')" style="cursor:pointer;">'+result[i].affComitteesConformed+'</a></td>';
						
						 }
						 else
						str1+='<td width="20%">'+result[i].affComitteesConformed+'</td>';
						 if(notConformed != null && notConformed > 0)
						 {
							 str1+='<td width="20%"><a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'NotStarted\',1,'+id+')" style="cursor:pointer;">'+notConformed+'</td>';
						 }
						 else
						 {
						str1+='<td width="20%">'+notConformed+'</td>';
						 }
						 str1+='</tr>';
					
						
					 }
					 
					 str1+='</table>';
					  $("#mandalAffliCommitteDivId").html(str1);
				}
			});
			
		}
		function getMandalMuncipalDivisonTotalCommittees(){
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
			
				console.log(result);
			});
		}
		
		
	function getCommitteeDetailsByStatus(basicCommitteetypeId,status,levelId,constituencyId)
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
		var jsObj = 
	{
		basicCommitteetypeId:basicCommitteetypeId,
		status:status,
		levelId:levelId,
		constituencyId : constituencyId,
		task:"memberCnt"
	}
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
        str+='<td>FINAL</td>';
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
		str+='<td><button class="btn btn-success btn-sm" onclick="getCommitteeMemberInfo(\''+jsObj.basicCommitteetypeId+'\',\''+result[i].level+'\',\''+result[i].id+'\',\''+jsObj.status+'\');">view</button></td>';
			}
         str+='<tr>';
			
		}
	 $("#CommitteeDetails").html(str);           
	}
	function getCommitteeMemberInfo(basicCommitteetypeId,levelId,locationId,status)
	{
	
	$("#committeeMemberDiv").html('');
	$("#conformedBtn").html('');
	$("#comitteeMemberAjax").show();
	var jsObj = 
	{
		basicCommitteetypeId:basicCommitteetypeId,
		levelId:levelId,
		locationId:locationId,
		status:status,
		task:"memberInfo"
	}
	$.ajax({
          type:'GET',
          url: 'getCommitteeDetailsByStatusPopUpAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){
		  $("#comitteeMemberAjax").hide();
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
			  
			  if(result != null && result.length > 0){
				  buildCommitteeMemberDetails(result,jsObj);
				
			}
	   });
	}

	
	
	
	
	function buildCommitteeMemberDetails(result,jsObj)
	{
		var str='';
		str+='<table class="table table-bordered text-left" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#000000;">';
		str+='<thead style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.2);">';
        str+='<th colspan="4" class="text-uppercase">'+result[0].locationName+'  <b>'+result[0].committe+'  COMMITTEE</b></th>';
        str+='</thead>';
        str+='<tbody>';
		for(var i in result)
		{

		str+='<tr>';
		
		str+='<td><img width="32" id="imagecdr'+i+'" height="32" src="http://www.mytdp.com/images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);"/></td>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].membershipNo+'</td>';
		str+='<td>'+result[i].role+'';
		if(result[i].status != "Y"){
			<c:if test="${!fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS' )}">
				str+='<div class="pull-right  btn btn-default btn-sm" ><i style="cursor:pointer;" class="glyphicon glyphicon-trash " onclick="deleteCadreRole(\''+result[i].total+'\');"></i></div>';
			</c:if>
		}
		str+='</td>';
		str+='</tr>';//total - tdpCommitteMemberID
		}
		str+='</tbody>';
		str+='</table>';
		$("#committeeMemberDiv").html(str);
		if(result[0].status != "Y")
		{
			<c:if test="${!fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS' )}">
				var str1='';
				str1+='<button class="btn btn-success btn-lg" onclick="committeeComplete(\''+jsObj.basicCommitteetypeId+'\',\''+jsObj.levelId+'\',\''+jsObj.locationId+'\')">Finalize Committee</button>';
				$("#conformedBtn").html(str1);
			</c:if>
		}
	}
	function setDefaultImage(img)
	{
		img.src = "images/cadreCommitee/Member_thamb_image.png";
	}
	
	function committeeComplete(basicCommitteetypeId,levelId,locationId)
	{
	var r=confirm("Are You Sure To Conform ?");
		if(r)
		{
	var jsObj = 
	{
		basicCommitteetypeId:basicCommitteetypeId,
		levelId:levelId,
		locationId:locationId,
		task:"committeComplete"
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
				alert("Committee Confirmed")
				$("#conformedBtn").html('');
				  }
				
			}
	   });
		}
	}
	function deleteCadreRole(tdpCommitteeMemberId)
	{
	var r=confirm("Are You Sure To Remove ?");
		if(r)
		{
	var jsObj = 
	{
		tdpcommitteeMemberId:tdpCommitteeMemberId,
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
					  if(result[0].status == "Removed")
				alert("Removed Successfully..")
						  else
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
	
</script>		
</body>
</html>
