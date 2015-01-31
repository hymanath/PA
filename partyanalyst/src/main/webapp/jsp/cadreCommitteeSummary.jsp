<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SUMMARY</title>
	 <!-- Bootstrap -->
    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="dist/js/bootstrap.min.js"></script>
			<!--Circle js file-->
    <script src="js/jquery.classyloader.min.js"></script>
</head>

<body>
		<!--<header style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-bottom:6px solid rgba(19,167,81,0.8);display:flex">
		 	<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12 text-center">
				<img src="images/committee_logo.png" title="Committee Logo" alt="committee" />
			</div>
			<div class="col-md-3  col-xs-3 col-sm-3">
				<a href="#" class="dropdown-toggle btn btn-default btn-xs m_top20" data-toggle="dropdown" aria-expanded="false" style="margin-top: 60px;">
				Menu <img src="images/menu_icon.png" />
				</a>
				<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);top: 91px;">
				  <li><a tabindex="-1" href="cadreCommitteeAction.action">Home</a></li>
				  <li><a tabindex="-1" href="cadreCommitteeRequestAction.action">Request For Positions Increase</a></li>
				  <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
				  <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
				</ul>
            </div>
		</header>-->
        <div class="container">
        	<!--Content Start-->
            <div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3">
                    <h3 class="panel-header">COMMITTEE SUMMARY</h3>
                    <hr style="border-color:#F00;margin-top:10px;" />
                </div>
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
                	<!--<table class="table table-condensed" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">
                    	<tr>
                        	<td colspan="4" style="background-color:#669934"><h4>MAIN COMMITTEE</h4></td>
                        </tr>
                        <tr>
                        	<td width="25%"><h2 style="display:inline;"></h2>TOTAL</td>
                            <td width="25%"><h2 style="display:inline;"><div id="mandalTotalDivId"></div></h2>STARTED</td>
                            <td width="25%"><h2 style="display:inline;">10</h2>CONFORMED</td>
                            <td width="25%"><h2 style="display:inline;">10</h2>NOT YET STARTED</td>
                        </tr>
                    </table>-->
					<div id="mandalAffliCommitteDivId"></div>
                    <!--<table class="table table-condensed" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">
                    	<thead>
                        	<th colspan="5" style="background-color:#669934"><h4>AFFILIATED COMMITTEE</h4></th>
                        </thead>
                        <thead>
                        	<th width="20%">COMMITTEE TYPE</th>
                        	<th width="20%">TOTAL</th>
                            <th width="20%">STARTED</th>
                            <th width="20%">CONFORMED</th>
                            <th width="20%">NOT YET STARTED</th>
                        </thead>
                        <tr>
                        	<td width="20%">TELUGU RYTHU COMMITTEE</td>
                        	<td width="20%">10</td>
                            <td width="20%">4</td>
                            <td width="20%">7</td>
                            <td width="20%">2</td>
                        </tr>
                        <tr>
                        	<td width="20%">TELUGU MAHILA COMMITTEE</td>
                        	<td width="20%">10</td>
                            <td width="20%">4</td>
                            <td width="20%">7</td>
                            <td width="20%">2</td>
                        </tr>
                        <tr>
                        	<td width="20%">TELUGU YUVATHA COMMITTEE</td>
                        	<td width="20%">10</td>
                            <td width="20%">4</td>
                            <td width="20%">7</td>
                            <td width="20%">2</td>
                        </tr>
                        <tr>
                        	<td width="20%">SC COMMITTEE</td>
                        	<td width="20%">10</td>
                            <td width="20%">4</td>
                            <td width="20%">7</td>
                            <td width="20%">2</td>
                        </tr>

                    </table>-->
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
                	<!--<table class="table table-condensed" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">
                    	<tr>
                        	<td colspan="4" style="background-color:#669934"><h4>MAIN COMMITTEE</h4></td>
                        </tr>
                        <tr>
                        	<td width="25%"><h2 style="display:inline;"><div class=""></div>20</h2>TOTAL</td>
                            <td width="25%"><h2 style="display:inline;">20</h2>STARTED</td>
                            <td width="25%"><h2 style="display:inline;">10</h2>CONFORMED</td>
                            <td width="25%"><h2 style="display:inline;">10</h2>NOT YET STARTED</td>
                        </tr>
                    </table>-->
					
					<div id="villageAfflicatedTableDivId"></div>
					
                    <!--<table class="table table-condensed" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);border-radius:2px;">
                    	<thead>
                        	<th colspan="5" style="background-color:#669934"><h4>AFFILIATED COMMITTEE</h4></th>
                        </thead>
                        <thead>
                        	<th width="20%">COMMITTEE TYPE</th>
                        	<th width="20%">TOTAL</th>
                            <th width="20%">STARTED</th>
                            <th width="20%">CONFORMED</th>
                            <th width="20%">NOT YET STARTED</th>
                        </thead>
                        <tr>
                        	<td width="20%">TELUGU RYTHU COMMITTEE</td>
                        	<td width="20%">10</td>
                            <td width="20%">4</td>
                            <td width="20%">7</td>
                            <td width="20%">2</td>
                        </tr>
                        <tr>
                        	<td width="20%">TELUGU MAHILA COMMITTEE</td>
                        	<td width="20%">10</td>
                            <td width="20%">4</td>
                            <td width="20%">7</td>
                            <td width="20%">2</td>
                        </tr>
                        <tr>
                        	<td width="20%">TELUGU YUVATHA COMMITTEE</td>
                        	<td width="20%">10</td>
                            <td width="20%">4</td>
                            <td width="20%">7</td>
                            <td width="20%">2</td>
                        </tr>
                        <tr>
                        	<td width="20%">SC COMMITTEE</td>
                        	<td width="20%">10</td>
                            <td width="20%">4</td>
                            <td width="20%">7</td>
                            <td width="20%">2</td>
                        </tr>

                    </table>-->
                   <!-- <table class="table table-condensed" style="border:5px solid #669934;background-color:rgba(0,0,0,0.1);border-radius:2px;">
                	<tr>
                    	<td colspan="7" style="background-color:#669934">[MANDAL/TOWN/DIVISION] WISE TELUGU RYTHU COMMITTEE STARTED DETAILS<div class="pull-right"><i class="glyphicon glyphicon-remove"></i></div></td>
                    </tr>
                    <tr>
                    	<td>MANDAL NAME</td>
                        <td>COMMITTEE TYPE</td>
                        <td>COMMITTEE MEMBERS</td>
                        <td colspan="3">STATUS</td>
                        <td>FINAL</td>
                    </tr>
                    <tr>
                    	<td>KAVALI</td>
                        <td></td>
                        <td>5</td>
                        <td>Started</td>
                        <td><i class="glyphicon glyphicon-ok" style="color:#090"></i>Completed</td>
                        <td>Not Yet Started</td>
                        <td><button class="btn btn-success btn-sm" disabled="disabled">view</button></td>
                    </tr>
                     <tr>
                    	<td>KAVALI</td>
                        <td></td>
                        <td>5</td>
                        <td>Started</td>
                        <td>Completed</td>
                        <td>Not Yet Started</td>
                        <td><button class="btn btn-success btn-sm">view</button></td>
                    </tr>
                     <tr>
                    	<td>KAVALI</td>
                        <td></td>
                        <td>5</td>
                        <td>Started</td>
                        <td>Completed</td>
                        <td>Not Yet Started</td>
                        <td><button class="btn btn-success btn-sm">view</button></td>
                    </tr>
                     <tr>
                    	<td>KAVALI</td>
                        <td></td>
                        <td>5</td>
                        <td>Started</td>
                        <td>Completed</td>
                        <td>Not Yet Started</td>
                        <td><button class="btn btn-success btn-sm">view</button></td>
                    </tr>
                </table>-->
				<img id="comitteeCntAjax" src="./images/icons/search.gif" alt="Processing Image" style="display:none;margin-left:400px;"/>
                    <table class="table table-condensed table-yellow-bordered" style="border:5px solid #669934;background-color:rgba(0,0,0,0.1);border-radius:2px;" id="CommitteeDetails">
                	</table>
                </div>  
               <!-- <div class="col-md-8 col-md-offset-1">
				<table class="table table-bordered text-left" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#fff;">
					<thead style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.2);">
                    	<th colspan="4">KAVALI TELUGU RYTHU COMMITTEE</th>
                    </thead>
                    <tbody>
						<tr>
							<td>President</td>
							<td><img src="images/Member_thamb_image.png" /></td>
							<td>Ashok</td>
							<td>Membership Number</td>
						</tr>
						<tr>
							<td>Vice president</td>
							<td><img src="images/Member_thamb_image.png" /></td>
							<td>Srikanth</td>
							<td>Membership Number</td>
						</tr>
						<tr>
							<td>General secretary</td>
							<td><img src="images/Member_thamb_image.png" /></td>
							<td>Kamal</td>
							<td>Membership Number</td>
						</tr>
						<tr>
							<td>Working committee</td>
							<td><img src="images/Member_thamb_image.png" /></td>
							<td>Harish</td>
							<td>Membership Number</td>
						</tr>
						<tr>
							<td>Organising  Committee</td>
							<td><img src="images/Member_thamb_image.png" /></td>
							<td>Member Name</td>
							<td>Membership Number</td>
						</tr>						
					</tbody>
				</table>-->
                 <img id="comitteeMemberAjax" src="./images/icons/search.gif" alt="Processing Image" style="display:none;margin-left:400px;"/>
                <div class="col-md-8 col-md-offset-1" id="committeeMemberDiv" >

				 </div>   
                <div class="col-md-2" id="conformedBtn" style="padding-top:10px;"></div>
            </div>  
            <!--Second Block END-->
            <!--Content END-->
        
        </div>


		<!--<footer class="text-center m_top20">
			&copy; 2015 Telugu Desam Party
		</footer>-->


    <script>
	$(document).ready(function() {
		getSummary();
		//getStartedCommittees();
		getMandalMuncipalDivisonStartedCommittees();
		//getMandalMuncipalDivisonTotalCommittees();
		
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
	});
	</script>
	<script>
		function getSummary(){
		$.ajax({
				type : "POST",
				url : "getSummaryAction.action",
				data : {} ,
			}).done(function(result){
				if(result!=null){
					var notStarted=result[0].mainComittees-(result[0].startedCount+result[0].mainComitteesConformed);
					var str='';
					str+='<table class="table table-condensed table-yellow-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
						str+='<tr><td colspan="4" style="background-color:#669934"><h4>MAIN COMMITTEE</h4></td></tr>';
								str+='<tr><td width="25%"><h2 style="display:inline;">'+result[0].mainComittees+'</h2> TOTAL</td>';
							  if(result[0].startedCount > 0)
							  str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Started\',2)">'+result[0].startedCount+'</a></h2> STARTED</td>';
								else
								{
									str+='<td width="25%"><h2 style="display:inline;">'+result[0].startedCount+'</h2> STARTED</td>';
								}
							  if(result[0].mainComitteesConformed > 0)
							{
									 str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Conform\',2)">'+result[0].mainComitteesConformed+'</a></h2> CONFIRMED</td>';
							}
									else
									{
							   str+='<td width="25%"><h2 style="display:inline;">'+result[0].mainComitteesConformed+'</h2> CONFIRMED</td>';
									}
									if(notStarted > 0)
									{
										str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'NotStarted\',2)">'+notStarted+'</a></h2> NOT YET STARTED</td>';
									}
										else
								{
							   str+='<td width="25%"><h2 style="display:inline;">'+notStarted+'</h2> NOT YET STARTED</td>';
								}
								str+='</tr>';
					 str+='</table>';
					 $("#villageMainTableDivId").html(str);
			
					var str1='';
						str1+='<table class="table table-condensed table-yellow-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
							str1+='<thead><th colspan="5" style="background-color:#669934"><h4>AFFILIATED COMMITTEE</h4></th></thead>';
							str1+='<thead><th width="20%">COMMITTEE TYPE</th><th width="20%">TOTAL</th><th width="20%">STARTED</th>';
							str1+='<th width="20%">CONFIRMED/COMPLETED</th><th width="20%">NOT YET STARTED</th></thead>';
					 for(var i in result){
						var notConformed=result[i].totalAffilatedCommittees-(result[i].affilatedStartedCount+result[i].affComitteesConformed); 	
						str1+='<tr><td width="20%">'+result[i].affilatedCommitteeName+'</td>';
						str1+='<td width="20%">'+result[i].totalAffilatedCommittees+'</td>';
						if(result[i].affilatedStartedCount != null && result[i].affilatedStartedCount > 0)
						 {
							str1+='<td width="20%"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Started\',2)">'+result[i].affilatedStartedCount+'</a></td>';
						 }
							else
						 {
						str1+='<td width="20%">'+result[i].affilatedStartedCount+'</td>';
						 }
						
						 if(result[i].affComitteesConformed != null && result[i].affComitteesConformed > 0)
						 {
						 str1+='<td width="20%"> <a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Conform\',2)">'+result[i].affComitteesConformed+'</a></td>';
						 }
						else
						 {
						str1+='<td width="20%">'+result[i].affComitteesConformed+'</td>';
						 }
						 if(notConformed > 0)
						 {
							 str1+='<td width="20%"> <a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'NotStarted\',2)">'+notConformed+'</a></td>';
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
		
		function getStartedCommittees(){
			$.ajax({
					type : "POST",
					url : "getStartedCommitteesAction.action",
					data : {} ,
				}).done(function(result){
					console.log(result);
				});
		}
		
		function getMandalMuncipalDivisonStartedCommittees(){
			$.ajax({
				type : "POST",
				url : "gettingMandalMuncipalDivisonSummaryAction.action",
				data : {} ,
			}).done(function(result){
				if(result!=null){
					var notStarted=result[0].mainComittees-(result[0].startedCount+result[0].mainComitteesConformed);
					var str='';
					str+='<table class="table table-condensed table-yellow-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
						str+='<tr><td colspan="4" style="background-color:#669934"><h4>MAIN COMMITTEE</h4></td></tr>';
								str+='<tr><td width="25%"><h2 style="display:inline;">'+result[0].mainComittees+'</h2> TOTAL</td>';
							   if(result[0].startedCount != null && result[0].startedCount > 0)
								{
							   str+='<td width="25%"><h2 style="display:inline;" ><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Started\',1)">'+result[0].startedCount+'</a></h2> STARTED</td>';
								}
								else
								{
									 str+='<td width="25%"><h2 style="display:inline;" >'+result[0].startedCount+'</h2> STARTED</td>';
								
								}
								if(result[0].mainComitteesConformed != null && result[0].mainComitteesConformed > 0)
								{
									  str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'Conform\',1)">'+result[0].mainComitteesConformed+'</a></h2> CONFIRMED</td>';
									
								}
								else
								{
							   str+='<td width="25%"><h2 style="display:inline;">'+result[0].mainComitteesConformed+'</h2> CONFIRMED</td>';
								}
								if(notStarted != null && notStarted > 0)
								{
							   str+='<td width="25%"><h2 style="display:inline;"><a style="cursor:pointer;" onclick="getCommitteeDetailsByStatus(1,\'NotStarted\',1)">'+notStarted+'</a></h2> NOT YET STARTED</td>';
								}
								else
								{
									str+='<td width="25%"><h2 style="display:inline;">'+notStarted+'</h2> NOT YET STARTED</td>';
								}
								str+='</tr>';
							  
					 str+='</table>';
					 $("#mandalMainCommitteDivId").html(str);
			
				var str1='';
						str1+='<table class="table table-condensed table-yellow-bordered" style="border:1px solid #669934;background-color:rgba(0,0,0,0.1);">';
							str1+='<thead><th colspan="5" style="background-color:#669934"><h4>AFFILIATED COMMITTEE</h4></th></thead>';
							str1+='<thead><th width="20%">COMMITTEE TYPE</th><th width="20%">TOTAL</th><th width="20%">STARTED</th>';
							str1+='<th width="20%">CONFIRMED/COMPLETED</th><th width="20%">NOT YET STARTED</th></thead>';
					 for(var i in result){
						var notConformed=result[i].totalAffilatedCommittees-(result[i].affilatedStartedCount+result[i].affComitteesConformed); 	
						str1+='<tr><td width="20%">'+result[i].affilatedCommitteeName+'</td>';
						str1+='<td width="20%">'+result[i].totalAffilatedCommittees+'</td>';
						if(result[i].affilatedStartedCount != null && result[i].affilatedStartedCount > 0)
						 {
						str1+='<td width="20%"><a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Started\',1)" style="cursor:pointer;">'+result[i].affilatedStartedCount+'</a></td>';
						 }
						 else
						 {
						str1+='<td width="20%">'+result[i].affilatedStartedCount+'</td>';
						 }
					if(result[i].affComitteesConformed != null && result[i].affComitteesConformed > 0)
						 {
						str1+='<td width="20%"><a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'Conform\',1)" style="cursor:pointer;">'+result[i].affComitteesConformed+'</a></td>';
						
						 }
						 else
						str1+='<td width="20%">'+result[i].affComitteesConformed+'</td>';
						 if(notConformed != null && notConformed > 0)
						 {
							 str1+='<td width="20%"><a onclick="getCommitteeDetailsByStatus(\''+result[i].affilatedCommitteId+'\',\'NotStarted\',1)" style="cursor:pointer;">'+notConformed+'</td>';
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
				url : "getMandalMuncipalDivisonTotalCommitteesAction.action",
				data : {} ,
			}).done(function(result){
				if(result!=null){
					var str='';
					str+='';
					mandalMainCommitteDivId
					
				}
			
				console.log(result);
			});
		}
		function getCommitteeDetailsByStatus(basicCommitteetypeId,status,levelId)
	{
		$("html,body").animate({scrollTop: $("#CommitteeDetails").offset().top});
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
		task:"memberCnt"
	}
	$.ajax({
          type:'GET',
          url: 'getCommitteeDetailsByStatusAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
			  $("#comitteeCntAjax").hide();
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
          url: 'getCommitteeDetailsByStatusAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
			  $("#comitteeMemberAjax").hide();
			  if(result != null && result.length > 0){
				  buildCommitteeMemberDetails(result,jsObj);
				
			}
	   });
	}

	function buildCommitteeMemberDetails(result,jsObj)
	{
		var str='';
		str+='<table class="table table-bordered text-left" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#fff;">';
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
		if(result[i].status != "Y")
			{
		str+='<div class="pull-right  btn btn-default btn-sm" ><i style="cursor:pointer;" class="glyphicon glyphicon-trash " onclick="deleteCadreRole(\''+result[i].total+'\');"></i></div>';
			}
		str+='</td>';
		str+='</tr>';//total - tdpCommitteMemberID
		}
		str+='</tbody>';
		str+='</table>';
		$("#committeeMemberDiv").html(str);
		if(result[0].status != "Y")
		{
			var str1='';
			str1+='<button class="btn btn-success btn-lg" onclick="committeeComplete(\''+jsObj.basicCommitteetypeId+'\',\''+jsObj.levelId+'\',\''+jsObj.locationId+'\')">Finalize Committee</button>';
			$("#conformedBtn").html(str1);
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
          url: 'getCommitteeDetailsByStatusAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
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
          url: 'getCommitteeDetailsByStatusAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
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
	</script>
    		<!--Circle JS END-->
</body>
</html>
