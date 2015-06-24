<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Cadre Details</title>
<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	
    	<!--Bootstrap DatePicker-->
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" type="text/css" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<style type="text/css">
		.block{background-color:#fff !important;margin-top:30px;}
		body{background:#d9dbd4 none repeat scroll 0 0}
		.pad_10{padding:10px;}
	</style>
</head>
<body>
	<div class="container">
		<div class="row" >
			<div class="col-md-7 col-md-offset-2 pad_10 block"  >
				
				<div class="media">
					<div class="media-left">
						<a href="#">
							<div id="imagePathId"></div>
						</a>
					</div>
					<div class="media-body">
					<table class="table table-bordered">
						<tr>
							<td width="50%">Name :&nbsp <span id="nameId"></span></td>
							<td>Mobile No :&nbsp <span id="mobileNoId"></span></td>
						</tr>
						<tr>
							<td>DOB:&nbsp <span id="dobId"></span></td>
							<td>AGE:&nbsp <span id="ageId"></span></td>
						</tr>
						<tr>
							<td>Qualification: &nbsp <span id="qualificationId"></span></td>
							<td>Occupation :&nbsp <span id="occupationId"></span></td>
						</tr>
					</table>
						<!--<h5 class="media-object" >Name      :<span id="nameId"></span></h5>
						<h5 class="media-object" >DOB       :<span id="dobId"></span></h5>
						<h5 class="media-object" >AGE       :<span id="ageId"></span></h5>
						<h5 class="media-object" >MobileNo  :<span id="mobileNoId"></span></h5>-->
					</div>
				</div>
				
				<table class="table table-bordered">
					<tr>
						<td width="50%">VoterCardNO : &nbsp <span id="voterIdSpan"></span> </td>
						<td>Panchayat :&nbsp <span id="panchayatId"></span></td>
					</tr>
					<tr>
						<td>Mandal :&nbsp<span id="mandalId"></span></td>
						<td>Constituency :&nbsp <span id="constituencyId"></span></td>
					</tr>
					<tr>
						<td>Party Position :&nbsp <span id="positionId"></span></td>
						<td>Public Representative :&nbsp <span id="representativeId"></span> </td>
					</tr>
				</table>
				<!--<p>Qualification :<span id="qualificationId"></span></p>
				<p>Occupation : <span id="occupationId"></span></p>
				<p>VoterCardNO : <span id="voterIdSpan"></span></p>
				<p>Panchayat : <span id="panchayatId"></span></p>
				<p>Mandal :<span id="mandalId"></span></p>
				<p>Constituency :<span id="constituencyId"></span></p>
				<p>Party Position :<span id="positionId"></span></p>
				<p>Public Representative : <span id="representativeId"></span></p>  -->
			</div>
			<div class="col-md-7 col-md-offset-2 pad_10 block">
				
				<h4 style="border-bottom:1px solid #999">Cadre Member Booth Performance</h4>

				<table class="table table-bordered">
					<tr>
						<th>Own Booth Perc</th>
						<th>Own Panchayat Perc</th>
						<th>Own Mandal Perc</th>
						<th>Own Constituency Perc</th>
						<th>Own Ward Perc</th>
					</tr>
					<tr id="ownBoothDetailsId">
						
					</tr>
				</table>
			</div>
			<div class="col-md-7 col-md-offset-2 pad_10 block">
				<h4 style="border-bottom:1px solid #999">Grievance Details</h4>
				<table class="table table-bordered table-responsive">
					<thead>
						<th>Complaint</th>
						<th>Severity</th>
						<th>Complaint Date</th>
						<th>Status</th>
						<th>Issue Type</th>
						<th>Grievance Type</th>
					</thead>
					<tbody id="grievanceDetailsId">
						
					<tbody>
				</table>
			</div>
			<div class="col-md-7 col-md-offset-2 pad_10 block">
				<h4 style="border-bottom:1px solid #999">Participation Details</h4>
				<div id="participationTableDivId">
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
	
	var globalCadreId;
	 $(document).ready(function() {
			globalCadreId='${param.cadreId}';
			cadreFormalDetailedInformation(globalCadreId);
			complaintDetailsOfCadre();
			getEventDetailsOfCadre();
	 });
		
			
	 var ownBoothDetailsVo;
		function cadreFormalDetailedInformation(globalCadreId){
			//2604438
			var localCadreId=globalCadreId;
			var jsobj={
				cadreId:localCadreId
			}
			$.ajax({
				type:'GET',
				 url: 'cadreFormalDetailedInformationAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(result){
				var str='';
				if(result !=null){ 
				//nameId dobId ageId qualificationId occupationId voterIdSpan panchayatId mandalId constituencyId positionId representativeId
					$("#nameId").html(result.name);
					 $("#dobId").html(result.dateOfBirth); 
					 $("#ageId").html(result.age);
					 $("#qualificationId").html(result.qualification);
					 $("#occupationId").html(result.occupation);
					 $("#voterIdSpan").html(result.voterIdCardNo);
					 $("#panchayatId").html(result.panchayatName);
					 $("#mandalId").html(result.tehsilName);
					 $("#constituencyId").html(result.constituencyName);
					 $("#positionId").html(result.partyPosition);
					 $("#representativeId").html(result.representativeType);
					 $("#mobileNoId").html(result.mobileNo);
					 if(result.imagePath !=null && result.imagePath !=""){
						 $("#imagePathId").html('<img src="'+result.imagePath+'" class="media-object img-circle" style="border:1px solid #ccc;margin-top:10px;" width="80px" height="80px;">'); 
					 }else{
						 $("#imagePathId").html('<img src="images/search_details_member_imahe.png" class="media-object img-circle" style="border:1px solid #ccc;margin-top:10px;" width="80px" height="80px;">');
					 }
					 
					 
					 if(result.ccmVO !=null && result.ccmVO!=""){
						 ownBoothDetailsVo=result.ccmVO;
						 buildingOwnBoothDetails(ownBoothDetailsVo);
					 }
					 
				}
			});
		}
		function buildingOwnBoothDetails(boothDetailsVo){
			
			var ownBoothDetailsVo=boothDetailsVo;
			var str='';
			
			if(ownBoothDetailsVo !=null && ownBoothDetailsVo !=""){
				if(ownBoothDetailsVo.ownBoothPerc !=null){//own booth
					str+='<td>'+ownBoothDetailsVo.ownBoothPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownPanchPerc !=null){//own panchayat
					str+='<td>'+ownBoothDetailsVo.ownPanchPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownMandalPerc !=null){//own Mandal
					str+='<td>'+ownBoothDetailsVo.ownMandalPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownConstiPerc !=null){//own Constituency
					str+='<td>'+ownBoothDetailsVo.ownConstiPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownWardPerc !=null){//own WardPerc
					str+='<td>'+ownBoothDetailsVo.ownWardPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				
				$("#ownBoothDetailsId").html(str);
			}
			
		}
		
		function complaintDetailsOfCadre(){
			var jsobj={
				cadreId:33160
			}
			$.ajax({
				type:'GET',
				 url: 'complaintDetailsOfCadreAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(result){
				var str='';
				if(result.knownList !=null && result.knownList.length>0){
					
					for(var i in result.knownList){
						str+='<tr>';
							str+='<td>'+result.knownList[i].name+'</td>';
							str+='<td>'+result.knownList[i].occupation+'</td>';
							str+='<td>'+result.knownList[i].dateOfBirth+'</td>';
							str+='<td>'+result.knownList[i].status+'</td>';
							str+='<td>'+result.knownList[i].representativeType+'</td>';
							str+='<td>'+result.knownList[i].type+'</td>';
						str+='</tr>';
					}
					$("#grievanceDetailsId").html(str);
				}
				else{
					$("#grievanceDetailsId").html("Result Not Available.");
				}
				
			});
		}
		function getEventDetailsOfCadre(){
			var jsobj={
				cadreId:8341157
			}
			$.ajax({
				type:'GET',
				 url: 'getEventDetailsOfCadreAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(results){
				var str='';
				if(results !=null){
					str+='<table class="table table-bordered table-responsive">';
					str+='<thead>';
						str+='<th>Main Event</th>';
						str+='<th>Sub Event</th>';
						str+='<th>Attended Count(Days)</th>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in results){
						var subLength;
						if(results[i].knownList !=null){
							subLength=results[i].knownList.length;
						}
						var participationType;
						
							if(results[i].id ==1){
								participationType="PARTY OFFICE";
							}
							else if(results[i].id ==7){
								participationType="MAHANADU";
							}else{
								participationType="OTHER";
							}
								//str+='<td  rowspan='+subLength+'>'+participationType+'</td>';
								
								if(results[i].knownList !=null){
									for(var j in results[i].knownList){
										str+='<tr>';
										if(j==0){
											str+='<td style="border-bottom:#fff;">'+participationType+'</td>';
										}else{
											str+='<td style="border:#fff;"></td>';
										}
										str+='<td>'+results[i].knownList[j].name+'</td>';
										str+='<td>'+results[i].knownList[j].total+'</td>';
										 str+='</tr>';
									}
								}
								else{
									str+='</tbody>';
									str+='</table>';
									$("#participationTableDivId").html("Data Not Available.");
									return;
								}	
						}
						
					str+='</tbody>';
				   str+='</table>';	
				   $("#participationTableDivId").html(str);
				}else{
					$("#participationTableDivId").html("Data Not Available.");
				}
			});
		}		
	</script>	
</body>
</html>