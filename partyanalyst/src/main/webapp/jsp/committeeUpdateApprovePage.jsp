<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Committee Update Approve Page</title>

   
    <!-- Bootstrap -->
    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
	<link rel="stylesheet" type="text/css" href="css/cadreCommitee/slick/slick.css"/>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/bootstrap.min.js"></script>
	<!----slick Js----->
	<script type="text/javascript" src="js/cadreCommittee/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/cadreCommittee/cadreCommittee.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

	 
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

	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
		
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/> 
	
	<script type="text/javascript" src="js/simplePagination/simplePagination3.js" ></script>

  </head>
  <body>
	<style>
		.light-theme a, .light-theme span{min-width:45px;}
		.light-theme a, .light-theme span{min-width:45px;}
		.approvedPosTxtCls{color:red;}
		
	</style>
	<div class="row" style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-top:12px solid rgba(19,167,81,0.8);border-bottom:12px solid rgba(19,167,81,0.8);display:flex">
		 	<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12 text-center">
				<img src="images/cadreCommitee/Committees_2014_logo.png" title="Committee Logo" alt="committee" />
			</div>
			<div class="col-md-3  col-xs-3 col-sm-3">
               <div class="" style="color:white;margin-top: 20px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top20" data-toggle="dropdown" aria-expanded="false" style="margin-top: 20px;">
                    Menu <img src="images/cadreCommitee/menu_icon.png" />
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);top: 91px;">
				  	  <c:if test="${sessionScope.USER.isAdmin == 'true'}">
						<li><a tabindex="-1" href="committeeDashBoardAction.action">Home</a></li>
					  </c:if>
					  <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN' )}">
						<li><a tabindex="-1" href="committeeDashBoardAction.action">Home</a></li>
					  </c:if>
				  	  <li><a tabindex="-1" href="constituencyCommitteeSummaryAction.action">Advanced DashBoard</a></li>
                      <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
                     <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
                    </ul>
                 
            </div>
		</div>
        <div class="container">
        	<!--Content Start-->
            <div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3">
                    <h3 class="panel-header">APPROVE REQUEST</h3>
                    <hr style="border-color:#F00;margin-top:10px;" />
                </div>
            </div>
            <!-- First Block Start-->
    		<div class="row">
            	<div class="col-md-offset-2 col-md-2"  style="background-color:rgba(0,0,0,0.1);"><h1 style="display:inline">
                <u id="totalCount">100</u></h1><small>REQUEST <BR/>FOR INCREASE POSITION</small></div>
                <div class="col-md-2"  style="background-color:rgba(0,0,0,0.1);margin-left:1px;"><h1 style="display:inline">
                <u id="pendingCount">40</u></h1><small>REQUESTS <BR/> PENDING</small></div>
				<div class="col-md-2"  style="background-color:rgba(0,0,0,0.1);"><h1 style="display:inline">
                <u id="approvedCount">100</u></h1><small>REQUESTS <BR/> APPROVED</small></div>
                <div class="col-md-2"  style="background-color:rgba(0,0,0,0.1);margin-left:1px;"><h1 style="display:inline">
                <u id="rejectedCount">40</u></h1><small>REQUESTS <BR/> REJECTED</small></div>
            </div>
           <!-- <div class="text-center m_top20">Note: Click on the <u>count</u> to view request details</div> -->
            <div class="row">
            	<div class="col-md-10 col-md-offset-1 m_top20" id="posTable">
                	
                </div>
				<div class="row">
					<div class="col-md-10 col-md-offset-1 m_top20">
						<div id="paginationId"></div>
					</div>
				</div>
				
				<div class="row"  style="margin-top: 37px;">
            	<div class="col-md-offset-2 col-md-2"  style="background-color:rgba(0,0,0,0.1);"><h1 style="display:inline">
                <u id="totalCount1">100</u></h1><small>REQUEST <BR/>FOR CHANGE POSITION</small></div>
                <div class="col-md-2"  style="background-color:rgba(0,0,0,0.1);margin-left:1px;"><h1 style="display:inline">
                <u id="pendingCount1">40</u></h1><small>REQUESTS <BR/> PENDING</small></div>
				<div class="col-md-2"  style="background-color:rgba(0,0,0,0.1);"><h1 style="display:inline">
                <u id="approvedCount1">100</u></h1><small>REQUESTS <BR/> APPROVED</small></div>
                <div class="col-md-2"  style="background-color:rgba(0,0,0,0.1);margin-left:1px;"><h1 style="display:inline">
                <u id="rejectedCount1">40</u></h1><small>REQUESTS <BR/> REJECTED</small></div>
                </div>
			    <div class="row" style="display:none;margin-top: 11px;" id="ErrDesgDiv" ></div>
				<div class="col-md-10 col-md-offset-1 m_top20" id="changeDesgTable"></div>
				<div class="row">
					<div class="col-md-10 col-md-offset-1 m_top20">
						<div id="paginationId1"></div>
					</div>
				</div>
				
            </div>
              
        
        </div>
	<footer class="text-center m_top20">
			&copy; 2015 Telugu Desam Party
	</footer>
	<script>
		getCandidateDetailsById(0);
		
		function getCandidateDetailsById(stNO){
				var jsObj = {
						startNo : stNO,
						endNo : 20
				}				   
				$.ajax({
					type : "POST",
					url : "getCommitteesForApprovalAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					//console.log(result);
					if(typeof result == "string"){
						if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
						  location.reload(); 
						}
			        }
					buildRequests(result,stNO);
				});
		}
		
		var ttlCnt = 0;
		getStatusForApproval();
		function getStatusForApproval(){
			var jsObj = {}				   
				$.ajax({
					type : "POST",
					url : "statusCountOfApprovalAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					
					if(typeof result == "string"){
					    
						if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
						  location.reload(); 
						}
			        }
					if(result.totalCount!=null){
						$("#totalCount").html(result.totalCount);
						ttlCnt=result.totalCount;
					}else{
						$("#totalCount").html(0);
					}
					
					if(result.pendingCount!=null){
						$("#pendingCount").html(result.pendingCount);
					}else{
						$("#pendingCount").html(0);
					}
					
					if(result.approvedCount!=null){
						$("#approvedCount").html(result.approvedCount);
					}else{
						$("#approvedCount").html(0);
					}
					
					if(result.rejectedCount!=null){
						$("#rejectedCount").html(result.rejectedCount);
					}else{
						$("#rejectedCount").html(0);
					}
					
					
					
				});
		}
		
		function buildRequests(result,stNO){
			var str = '';
			$("#posTable").html("");
			str+='<table class="table table-condensed" style="background-color:rgba(0,0,0,0.1);">';
						str+='<thead>';
                        str+='	<th colspan="5"><h4 class="text-success" style="display:inline">INCREASE POSITION REQUEST STATUS</h4></th>';
                        str+='    <th colspan="2"><div class="input-group pull-right">';
						//str+='<input type="text" class="form-control input-sm pull-right" placeholder="Search" aria-describedby="basic-addon2">';
						//str+='<span class="input-group-addon" id="basic-addon2"><i class="glyphicon glyphicon-search"></i></span>';
						str+='</div></th>';
						str+='</thead>';
						str+='<thead>';
						str+='<th width="5%">REQUEST NO</th>';
                        str+='<th width="12%">LOCATION</th>';
                            str+='<th width="18%">COMMITTEE NAME</th>';
                            str+='<th width="17%">POSITION NAME</th>';
                            str+='<th width="12%"><small>CURRENT MAX POSITONS</small></th>';
                            str+='<th width="13%"><small>REQUESTED MAX POSITIONS</small></th>';
							str+='<th width="8%"><small>APPROVED MAX POSITIONS</small></th>';
                            str+='<th width="18%"><small>UPDATE STATUS</small></th>';
                        str+='</thead>';
                         str+='<tbody>';
							for(var i in result){
								str+='<tr>';
                                str+='<td> #'+result[i].requestNo+'</td>';
								
								if(result[i].locationType=="Local Election Body" || result[i].locationType=="Ward"){
									str+='<td> '+result[i].location+'</td>';
								}else{
									str+='<td> '+result[i].location+' '+result[i].locationType+'</td>';
								}
							    str+='<td>'+result[i].committeeName+'</td>';
                                str+='<td>'+result[i].role+'</td>';
                                str+='<td class="crrntPos">'+result[i].currentPosCount+'</td>';
                                str+='<td class="rqstdPos">'+result[i].requestdPosCount+'</td>';
								if(result[i].status=="pending"){
									str+='<td class="approvedPos"><input class="approvedPosTxt" id="appId'+result[i].cadreCommitteeIncreasedPosId+'" type="text" value='+result[i].requestdPosCount+'><span class="approvedPosTxtCls" id="appId'+result[i].cadreCommitteeIncreasedPosId+'err"+></span></td>';
								}else{
									str+='<td class="approvedPos"><input readonly="readonly" id="appId'+result[i].cadreCommitteeIncreasedPosId+'" type="text" value='+result[i].requestdPosCount+'><span class="approvedPosTxtCls" id="appId'+result[i].cadreCommitteeIncreasedPosId+'err"+></span></td>';
								}
								
								str+='<td class="cls'+result[i].cadreCommitteeIncreasedPosId+'" data_attr='+result[i].tdpCommitteeRoleId+' data_attr_crrntCnt='+result[i].currentPosCount+' data_attr_pk='+result[i].cadreCommitteeIncreasedPosId+' data_attr_maxPos='+result[i].requestdPosCount+' >';
								if(result[i].status=="pending"){
									str+='<select class="form-control input-sm updateStatusCls">';
									str+='<option selected="selected" value="Pending"> Pending </option>';
									str+='<option value="Approved"> Approved </option>';
									str+='<option value="Rejected"> Rejected </option>';
									str+='</select>';
								}else{
									if(result[i].status!="Rejected"){
										str+='<span style="color:green">'+result[i].status+'</span>';
									}else{
										str+='<span style="color:red">'+result[i].status+'</span>';
									}
									
								}
                                str+='</td>';
								str+='</tr>';
							}
                        	
                        str+='</tbody>';
                    str+='</table>';
					
		$("#posTable").html(str); 
		if(stNO == 0 && result.length > 0){
			setTimeout(5000);
			$("#paginationId").pagination({
				items: ttlCnt,
				itemsOnPage: 20,
				cssStyle: 'light-theme',
				hrefTextPrefix: '#pages-',
				onPageClick: function(pageNumber) { 
					var num=(pageNumber-1)*20;
						getCandidateDetailsById(num);
				}
				
			});
		}
		getCommitteesForApprovalForChangeDesignations(0);
		}
		
		//updatePosCount();
		function updatePosCount(roleId, increasedPosId, maxCount, type, apprvCount){
			var jsObj = {
						tdpCommitteeRoleId : roleId,
						maxCount : maxCount,
						type : type,
						increasedPosId : increasedPosId,
						approveCount : apprvCount
				}				   
				$.ajax({
					type : "POST",
					url : "updateCommitteePosCountAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(typeof result == "string"){
						if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
						  location.reload(); 
						}
			        }
					if(result!="failed"){
						$("#appId"+increasedPosId).attr('readonly', true);
						$("#appId"+increasedPosId).removeClass("approvedPosTxt");
					   if(type!="Rejected"){
							$(".cls"+increasedPosId).html('<span style="color:green">'+type+'</span>');
					   }else{
							$(".cls"+increasedPosId).html('<span style="color:red">'+type+'</span>');
					   }
					   getStatusForApproval();
					}else{
						$(this).val("pending");
						alert(" Unable To Update .. Please Try Again");
					}
				});
		}
		$(document).on("change",".updateStatusCls",function(){
			if (confirm('Do you wan\'t to Continue?')) {
				var increasedPosId = $(this).parent().attr("data_attr_pk");
				var roleId = $(this).parent().attr("data_attr");
				var maxCount = $(this).parent().attr("data_attr_maxPos");
				var crrntCount = $(this).parent().attr("data_attr_crrntCnt");
				
				var type = $(this).val();
				
				var apprvCount = $("#appId"+increasedPosId).val();
				if(type=="Approved"){
					if(apprvCount>maxCount ){
						alert(" APPROVED MAX POSITIONS <= REQUESTED MAX POSITIONS ");
						$(this).val("Pending");
						return ;
					}
					
					if(apprvCount<crrntCount ){
						alert(" APPROVED MAX POSITIONS >= CURRENT MAX POSITONS ");
						$(this).val("Pending");
						return ;
					}
				}
				
				
				if(type!="Pending"){
					updatePosCount(roleId, increasedPosId, maxCount, type, apprvCount);
				}
			}else{
				$(this).val("Pending");
			}
		});
		
		$(document).on("keypress",".approvedPosTxt",function (e) {
			var txtId = $(this).attr("id");
			//if the letter is not digit then display error and don't type anything
			if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				//display error message
				
			   $("#"+txtId+"err").html("Digits Only").show().fadeOut("slow");
               return false;
			}
		});
		
	//-----------For Change Designations.----------------------------
	 var totalCount=0;
		
	statusForChangeDesignationsApproval();
     function statusForChangeDesignationsApproval(){
	   $.ajax({
					type : "POST",
					url : "statusForChangeDesignationsApprovalAction.action",
					data : {} ,
				}).done(function(result){
					if(result.totalCount!=null){
						$("#totalCount1").html(result.totalCount);
						totalCount=result.totalCount;
					}else{
						$("#totalCount1").html(0);
					}
					
					if(result.pendingCount!=null){
						$("#pendingCount1").html(result.pendingCount);
					}else{
						$("#pendingCount1").html(0);
					}
					
					if(result.approvedCount!=null){
						$("#approvedCount1").html(result.approvedCount);
					}else{
						$("#approvedCount1").html(0);
					}
					
					if(result.rejectedCount!=null){
						$("#rejectedCount1").html(result.rejectedCount);
					}else{
						$("#rejectedCount1").html(0);
					}
				});
	 
	 }
	
	
	
	
	
		//getCommitteesForApprovalForChangeDesignations(0);
		
		function getCommitteesForApprovalForChangeDesignations(stNO){
				var jsObj = {
						startNo : stNO,
						endNo : 20
				}				   
				$.ajax({
					type : "POST",
					url : "getCommitteesForApprovalForChangeDesignationsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					buildData(result,stNO);
				});
		}
	
		function buildData(result,stNO){
			var str = '';
			$("#changeDesgTable").html("");
			str+='<table class="table table-condensed" style="background-color:rgba(0,0,0,0.1);">';
						str+='<thead>';
                        str+='	<th colspan="5"><h4 class="text-success" style="display:inline">CHANGE POSITION REQUEST STATUS</h4></th>';
                        str+='  <th colspan="2"><div class="input-group pull-right">';
						//str+='<input type="text" class="form-control input-sm pull-right" placeholder="Search" aria-describedby="basic-addon2">';
						//str+='<span class="input-group-addon" id="basic-addon2"><i class="glyphicon glyphicon-search"></i></span>';
						str+='</div></th>';
						str+='</thead>';
						str+='<thead>';
                        str+='<th style="padding-left: 22px;">DATE</th>';
                        str+='<th>LOCATION</th>';
						str+='<th>COMMITTEE NAME</th>';
		                str+='<th>MEMBER IMAGE</th>';
						str+='<th style="padding-left: 20px;">COMMITTEE MEMBER NAME</th>';
                       // str+='<th>MEMBERSHIP NO</th>';
                        str+='<th style="padding-left: 18px;">CURRENT ROLE</th>';
						str+='<th style="padding-left: 17px;">REQUESTED ROLE</th>';
                        str+='<th style="padding-right: 51px;">STATUS</th>';
                        str+='<th style="padding-left: 12px;">REF-NO</th>';
                        str+='</thead>';
                         str+='<tbody>';
				  for(var i in result){
				   var rowcount = result[i].locationsList.length;
				   var paddingTop=(rowcount*20);
				  
				   str+='<tr>';
				   str+='<td rowspan="'+result[i].locationsList.length+'" style="text-align: center;padding-top:'+paddingTop+'px;">'+result[i].dateString+'</td>';
				   str+='<td rowspan="'+result[i].locationsList.length+'" style="text-align: center;padding-top:'+paddingTop+'px;">'+result[i].location+' '+result[i].locationType+'</td>';
				   str+='<td rowspan="'+result[i].locationsList.length+'" style="text-align: center;padding-top:'+paddingTop+'px;">'+result[i].committeeName+'</td>';
				   
				    for(var j in result[i].locationsList)
					{					
						 str+='<td ><img data-attr-tdpCommitteememberId="'+result[i].locationsList[j].tdpCommitteeMemberId+'" style="margin-top: 26px; margin-left: 12px;" width="35"  height="35" src="http://www.mytdp.com/images/cadre_images/'+result[i].locationsList[j].positionId+'" onerror="setDefaultImage(this);"/></td>';
						 str+='<td style="padding-top:'+paddingTop+'px;">'+result[i].locationsList[j].position+'</td>';
						// str+='<td style="padding-top:'+paddingTop+'px;">'+result[i].locationsList[j].memberShipNo+'</td>';
						 str+='<td data-attr-currentRoleId="'+result[i].locationsList[j].currentRoleId+'" style="padding-top:'+paddingTop+'px; padding-left: 18px;">'+result[i].locationsList[j].currentRole+'</td>';
						 str+='<td data-attr-newRoleId="'+result[i].locationsList[j].newRoleId+'" style="padding-top:'+paddingTop+'px; padding-left: 18px;">'+result[i].locationsList[j].newRole+'</td>';
						 if(j == 0) 
						 {
						     /*
							  if(result[i].status=="pending"){
									str+='<select class="form-control input-sm updateStatusCls">';
									str+='<option selected="selected" value="Pending"> Pending </option>';
									str+='<option value="Approved"> Approved </option>';
									str+='<option value="Rejected"> Rejected </option>';
									str+='</select>';
								}else{
									if(result[i].status!="Rejected"){
										str+='<span style="color:green">'+result[i].status+'</span>';
									}else{
										str+='<span style="color:red">'+result[i].status+'</span>';
									}
									
								}
							 */
							 var cadreCommitteeIncreasedPositionsId=result[i].refNo.split("#")[1];
							 str+='<td id="status'+cadreCommitteeIncreasedPositionsId+'" rowspan="'+result[i].locationsList.length+'" style="padding-top:'+paddingTop+'px;">';
							 if(result[i].status=="pending"){
							        
									str+='<select  class="form-control input-sm" onChange="approvingChangeDesignations(\''+result[i].refNo+'\',this)">';
									str+='<option selected="selected" value="Pending"> Pending </option>';
									str+='<option value="Approved"> Approved </option>';
									str+='<option value="Rejected"> Rejected </option>';
									str+='</select>';
									str+='</td>';
							 }
							 else{
									if(result[i].status!="Rejected"){
										str+='<span style="color:green">'+result[i].status+'</span>';
									}else{
										str+='<span style="color:red">'+result[i].status+'</span>';
									}
								  }
							str+='</td>';	  
								
							// str+='<td rowspan="'+result[i].locationsList.length+'" style="text-align: center;padding-top:'+paddingTop+'px;">'+result[i].status+'</td>';
							 str+='<td rowspan="'+result[i].locationsList.length+'" style="text-align: center;padding-top:'+paddingTop+'px;">'+result[i].refNo+'</td>';
						 }
						 str+='</tr>';
						 
				    }
				  				   
				  }
				  str+='</tbody>';
                  str+='</table>';
					
		$("#changeDesgTable").html(str);
		
		if(stNO == 0 && result.length > 0){
			$("#paginationId1").pagination({
				items: totalCount,
				itemsOnPage: 20,
				cssStyle: 'light-theme',
				hrefTextPrefix: '#page-',
				onPageClick: function(pageNumber) { 
					var num=(pageNumber-1)*20;
						getCommitteesForApprovalForChangeDesignations(num);
				}
			});
		}
		
		}
		
		function approvingChangeDesignations(refNo,thisVar){
		if (confirm('Do you wan\'t to Continue?')) {
		var cadreCommitteeIncreasedPositionsId=refNo.split("#")[1];
		var approvedStatus=$(thisVar).val();
		$('#ErrDesgDiv').hide();
		 
		if(approvedStatus=='Pending'){
		 return;
		}
		
		var jsObj ={
						cadreCommitteeIncreasedPositionsId : cadreCommitteeIncreasedPositionsId,
						status : approvedStatus
				   }				   
				$.ajax({
					type : "POST",
					url : "approvingChangeDesignationsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
				
				  
					if(result.resultCode=='1'){
					  if(approvedStatus=="Approved"){
					     $("#status"+cadreCommitteeIncreasedPositionsId).html(""); 
					     $("#status"+cadreCommitteeIncreasedPositionsId).html('<span style="color:green">Approved</span>');
					     statusForChangeDesignationsApproval();
					   }
					   if(approvedStatus=="Rejected"){
					     $("#status"+cadreCommitteeIncreasedPositionsId).html(""); 
					     $("#status"+cadreCommitteeIncreasedPositionsId).html('<span style="color:red">Rejected</span>');
					     statusForChangeDesignationsApproval();
					   }
					}
					
					else if(result.resultCode=='2'){
				    
					 var msgArray=[];
					 var msgStr="";
					 var desgMsg="";
					 
					 var message=result.message; 
					 msgArray = message.split(","); 
					
			         if(msgArray!=null && msgArray.length>0){
					   if(msgArray.length==1){
					     msgStr=msgArray[0];
						 desgMsg="Designation";
					   }
					   else{
					      for(var i in msgArray){
					        if(msgStr!="")
					         msgStr=msgStr+","+msgArray[i];
						    else
						     msgStr=msgStr;
					      }
						  desgMsg="Designations";
					    } 
					 }
					  var str="";
					  str+="<span style='color:red;margin-left: 221px;;font-size:17px'>Request Failed. The "+desgMsg+" "+msgStr+"  Is Exceeding Max Count.</span>";
					  $('#ErrDesgDiv').html(str);
					  $('#ErrDesgDiv').show();
				   }
			
				});
		}
		else{
		  $(thisVar).val("Pending");
		}
		
		}
	
	</script>
  </body>
</html>