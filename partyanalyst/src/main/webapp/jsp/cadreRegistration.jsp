<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Community News Portal</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">	
	<!-- Custom Styles-->
    <link href="css/style.css" rel="stylesheet">
	<!-- CSS animation -->
    <link href="css/animate.css" rel="stylesheet">	
	<!-- icheck Css-->
	<link href="icheck/skins/all.css?v=1.0.2" rel="stylesheet">
	
	<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	 .bgc{background-color:#3598db}
	</style>
   
	
</head>
  <body class="bgc">
	<div class="container m_top10 " id="yourElement">
		<div class="span12 show-grid" style="position: relative;">
			<h3 class="text-align">CADRE REGISTRATION</h3>
		</div>
	</div>
<div>
	<form action="tdpCadreRegistrationAction.action" method="POST" name="cadreForm">	
		<div class="container m_top10"style="position: relative;">
		
				<div class="span12" >
				<div class="row-fluid">
					<div class="span6   show-grid"  id="fadeInLeft">
											<h5 class="text-align1">CANDIDATE NAME</h5>
											<input type="text" class="form-control border-radius-0 text-align2" placeholder="Text input" name="cadreRegistrationVO.voterName"></input>
									<div class="row-fluid">
										<div class="span7">
											<h5 class="text-align1">DATE OF BIRTH</h5>
												
												<div class="input-prepend text-align2 ">
													<span class="add-on "><span class="icon-calendar "></span></span>
													<input type="text" class="form-control  span12 border-radius-0 border-right-0 " name="cadreRegistrationVO.dobStr"></input></span>
													</div>
													
													<h5 class="text-align1">GENDER</h5>	
												<div class="row-fluid form-inline" style="margin-left:5px;">
														<label class="radio"><input type="radio" value="option1"  name="cadreRegistrationVO.gender"> MALE</input></label>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="radio"><input type="radio" value="option1"  name="cadreRegistrationVO.gender"> FEMALE</input></label>
												</div>			
										</div>
								
								
										<div class="span4  m_top10">
											<div class="well  pad-5">
												<img src="user.jpg" class="img-responsive" />
											<button class="btn btn-primary btn-xs btn-block border-radius-0 m_top10 " type="button" >Upload Photo </button>
											</div>
										</div>
								
									</div>
										<h5 class="text-align1">GURIDIAN NAME</h5>
										<input type="text" class="form-control border-radius-0 text-align2" placeholder="Text input" name="cadreRegistrationVO.relativeName"></input>
								<div class="m_top10">
										<div class="row-fluid">
										
											<div class="span6">
											<h5 class="text-align1">VOTER ID</h5>
												<input type="text" class="form-control border-radius-0 text-align2" placeholder="Voter Id" name="cadreRegistrationVO.voterCardNumber"></input>
											</div>
											
											<div class="span6">
											<h5 class="text-align1">H NO</h5>
												<input type="text" class="form-control border-radius-0 " placeholder="House Number" name="cadreRegistrationVO.houseNo"></input>
											</div>
										</div>
								</div>	
								
								<div class="m_top10">
										<div class="row-fluid">
										
											<div class="span6">
											<h5 class="text-align1">PARTY MEMBER SINCES</h5>
												<input type="text" class="form-control border-radius-0 text-align2" placeholder="" name="cadreRegistrationVO.partyMemberSinceStr"></input>
											</div>
											
											<div class="span6">
											<h5 class="text-align1">BLOOD GROUP</h5>
												<select id="bloodGroupId" name="cadreRegistrationVO.bloodGroupId">
													<option value="1">A +</option>
												</select>
												
											</div>
										</div>
								</div>	
								<!--<div class="m_top10">
									
											<h5 class="text-align1">REFERED BY</h5>
												<input type="text" class="form-control border-radius-0 text-align1" placeholder="">
							
								</div>-->
						
					</div>
					<div class="span6 show-grid pad-10b" id="fadeInRight" style="height:643px; " >
					
								<div class=" m_top20" >
										<h5 class="text-align1">STREET/HAMLET</h5>
										<input type="text" class="form-control border-radius-0  input-block-level" placeholder="Text input" name="cadreRegistrationVO.street"></input>
								</div>	
							<div class=" m_top20" >
										<h5 class="text-align1">CASTE NAME</h5>
										<select id="casteStateId" name="cadreRegistrationVO.casteId">
												<option value="1">Mala</option>
										</select>
										
							</div>
							<div class=" m_top20" >
										<h5 class="text-align1">MOBILE NUMBER</h5>
										<input type="text" class="form-control border-radius-0 input-block-level" placeholder="Text input"  name="cadreRegistrationVO.mobileNumber"></input>
							</div>
							<div class=" m_top20" >
								<h5 class="text-align1">EDUCATION</h5>
								<select class="form-control border-radius-0 input-block-level" name="cadreRegistrationVO.educationId">
									<option value = "1">kuppam</option>
									<option value = "2">Nellore</option>
									<option value = "3">Anantapur</option>
								</select>
							</div>
							<div class=" m_top20" >
								<h5 class="text-align1">OCCUPATION</h5>
								<select class="form-control border-radius-0 input-block-level" name="cadreRegistrationVO.occupationId">
									<option value = "1">kuppam</option>
									<option value = "2">Nellore</option>
									<option value = "3">Anantapur</option>
								  </select>
							</div>
							<div class=" m_top20" >
									<h5 class="text-align1">PREVIOUS ENROLLMENT NUMBER</h5>
									<input type="text" class="form-control border-radius-0 input-block-level" placeholder="Text input" name="cadreRegistrationVO.previousEnrollmentNumber"></input>
							</div>
						
					</div>
			</div>
		
		</div>
	</div>
	<div id="fadeInUp">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">PREVIOUS ROLS PARTICIPATED IN PARTIES</h3>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<div class="row" >
				
					<div class="block-hover-addBtn pull-right">
						<div class="btn-group-vertical">
							<a href="" class="btn btn-default btn-xs border-radius-0 btn-plus border-none"><span class="icon-plus"></span></a>
							<a href="" class="btn btn-default btn-xs border-radius-0 btn-minus border-none"><span class="icon-minus"></span></a>
						</div>
					</div>
					
					<div class="span3">
							<div class=" m_top20" >
								<h5 class="text-align1">OCCUPATION</h5>
									<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousRollesList[0].designationLevelId">
									    <option value = "1">kuppam</option>
										<option value = "2">Nellore</option>
										<option value = "3">Anantapur</option>
									 </select>
							</div>
					</div>
					<div class="span3">
						<div class=" m_top20" >
							<h5 class="text-align1">OCCUPATION</h5>
								<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousRollesList[0].designationLevelValue">
								  <option value = "1">kuppam</option>
								  <option value = "2">Nellore</option>
								  <option value = "3">Anantapur</option>
								  </select>
						</div>
		
					</div>
		
					<div class="span3">
						<div class=" m_top20" >
							<h5 class="text-align1">FROM</h5>
								<div class="input-prepend text-align2 ">
													<span class="add-on "><span class="icon-calendar "></span></span>
													<input type="text" class="form-control span2 border-radius-0 border-right-0 " name="cadreRegistrationVO.previousRollesList[0].fromDateStr"></input></span>
													</div>
						</div>
					</div>
					<div class="span3 ">
						<div class=" m_top20" >
							<h5 class="text-align1">TO</h5>
								<div class="input-prepend  ">
													<span class="add-on "><span class="icon-calendar "></span></span>
													<input type="text" class="form-control span2  border-radius-0 border-right-0 " name="cadreRegistrationVO.previousRollesList[0].toDateStr"></input></span>
													</div>
						</div>
					</div>
				</div>
			</div>
	</div>
	</div>
	<div id="fadeInUp1">
		<div class="container m_top10">
		<div class="span12 show-grid" style="position: relative;">
			<h3 class="text-align ">PREVIOUSLY PARTICIPATED IN ELECTION</h3>
		</div>
		</div>
		<div class="container m_top10">
		<div class="span12 show-grid" style="position: relative;">
		<div class="row-fluid">
		<div class="span4">
		<div class=" m_top20" >
		<h5 class="text-align1">Election Type</h5>
						<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousParicaptedElectionsList[0].electionTypeId">
							  <option value ="1">kuppam</option>
							  <option value = "2">Nellore</option>
							  <option value = "3">Anantapur</option>
				  </select>
			</div>
		</div>
		<div class="span4">
		<div class=" m_top20" >
		<h5 class="text-align1">Year</h5>
				<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousParicaptedElectionsList[0].electionYear">
				 <option value ="1">kuppam</option>
							  <option value = "2">Nellore</option>
							  <option value = "3">Anantapur</option>
				  </select>
			</div>
		</div>
		<div class="span4">
		<div class=" m_top20" >
		<h5 class="text-align1">Constituency</h5>
						<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousParicaptedElectionsList[0].constituencyId">
				  <option value ="1">kuppam</option>
							  <option value = "2">Nellore</option>
							  <option value = "3">Anantapur</option>
				  </select>
			</div>
		</div>
		</div>
		</div>
		</div>
		<div class="container m_top10">
		<div class="span12 show-grid" style="position: relative;">
		<a class="btn btn-primary m_top20 border-radius-0 text-align2" href="search-constituency.html"><span class="icon-chevron-left icon-white"></span>&nbsp;&nbsp;Back </a>
		<input type="submit" class="btn btn-success text-align3 m_top20 pull-right border-radius-0">Success &nbsp;&nbsp;<span class=" icon-chevron-right icon-white"></span></input>
		</div>
		</div>
	</div>
</form>
</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	<script src="icheck/jquery.js"></script>
	 <script src="icheck/icheck.js"></script>
<!-- iCheck -->
	<script>
		$(document).ready(function(){
		  $('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });
		});
		</script>
    <script>$('#yourElement').addClass('animated fadeInDown');</script>
    <script>$('#fadeInLeft').addClass('animated fadeInLeft');</script>
    <script>$('#fadeInRight').addClass('animated fadeInRight');</script>
    <script>$('#fadeInUp').addClass('animated fadeInUp');</script>
    <script>$('#fadeInUp1').addClass('animated fadeInUp');</script>
	<!----->
	
	
  </body>
</html>