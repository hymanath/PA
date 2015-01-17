<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Committee Management</title>

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
   	
	<style>
	#publicrepresantative,#mandalaffiliated,#advancedSearchDiv,#committeeDetailsDiv
	{
		display:none;
	}
	</style>
  </head>
  <body>
	<div class="container-fluid">
		<div class="row" style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-top:12px solid rgba(19,167,81,0.8);border-bottom:12px solid rgba(19,167,81,0.8);display:flex">
		 	<div class="col-md-12 col-sm-12 col-xs-12 text-center">
				<img src="images/cadreCommitee/committee_logo.png" title="Committee Logo" alt="committee" />
			</div>
		</div>
		<div class="row m_top20">
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12" >
				<div class="row" >
					<div class="col-md-4 col-md-offset-0 col-sm-4 col-xs-4" >
						<a class="btn btn-success btn-block arrow_selected" id="basicCommitteeTab" href="javascript:{showAndHideTabs('basicCommitteeDiv');}">Committee <br>Management</a>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4">
						<a class="btn btn-success btn-block "   id="publicrepresantativeTab" href="javascript:{showAndHideTabs('publicrepresantative');}">Public represantative <br>Electoral Management</a>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4">
						<a class="btn btn-success btn-block "  id="mandalaffiliatedTab" href="javascript:{showAndHideTabs('mandalaffiliated');}" >Mandal affiliated <br>Electoral Management</a>				
					</div>
				</div> 		
			</div> 		
		</div> 
		<input type="hidden" value="1" id="committeeMngtType"/>		
		<input type="hidden" value="1" id="areaTypeId"/>
		<div class="row" id="basicCommitteeDiv">
		<div class="row m_top20">
			<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 ">
				<div class="radio pull-right">
				  <label>
				    <input type="radio" name="committeeType" onclick="validateSearchType('1')" checked="true"> Village / Ward / Division
				  </label>
			    </div>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-6">
				<div class="radio">
				  <label>
				    <input type="radio" name="committeeType" onclick="validateSearchType('2')"> Mandal / Town / GHMC 
				  </label>
			    </div>
			</div>
			
		</div> 
		
		<div class="row m_top20">
			<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 ">
				<div class="form-group col-xs-12 pull-right">
					<label for="exampleInputEmail1">SELECT LOCATION</label>
					<select  class="form-control" id="committeeLocationId" ><option value="0">Select Location</option></select >
				 </div>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-6">
				<div class="form-group col-xs-12">
					<label for="exampleInputEmail1">COMMITTEE TYPE</label>
					<select class="form-control" id="committeeTypeId" ><option value="1">Main Committee</option><option value="2">Affiliated Committee</option></select >
				 </div>
			</div>
		</div>
		
		<div class="row">	
			<div class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-2 col-xs-12 ">
				<div class="form-group col-xs-12">
					<label for="exampleInputEmail1">COMMITTEE TYPE</label>
					<select class="form-control" id="committeeId"><option>Affiliated Committee #1</option></select >
				 </div>
			</div>			
		</div> 
		
		<div class="row">	
			<div class="col-md-12 col-sm-12 col-xs-12 text-center">
					<ul class="list-inline">
						<li><a class="btn btn-success" href="">VIEW</a></li>
						<li><a class="btn btn-success" href="">ADD</a></li>
					</ul>
			</div> 
		</div> 
		
		<!-------VIEW BLOCK------>
		<div class="row" id="committeeDetailsDiv">	
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">
				<h4>AFFILIATED COMMITTEE NAME</h4>
				<hr style="margin: 0px;">
				<ul class="list-inline pull-right ">
					<li><span style="color:#fff;font-weight:bold;">TOTAL </span></li>
					<li><span style="color:#C9302C;font-weight:bold;">APPLIED </span></li>
					<li><span style="color:#449D44;font-weight:bold;">AVAIL</span></li>
				</ul>
				
			</div>			
			
			<div class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1 ">
				<div class="variable-width">
				  <div class="slick_widget">
					<h5>PRESIDENT</h5>
					<ul class="list-inline text-center" >
						<li class="btn btn-xs btn-default" disabled="disabled">2</li>
						<li class="btn btn-xs btn-danger" disabled="disabled">0</li>
						<li class="btn btn-xs btn-success" disabled="disabled">2</li>
					</ul>
				  </div>
				  
				   <div class="slick_widget">
					<h5>VICE PRESIDENT</h5>
					<ul class="list-inline text-center" >
						<li class="btn btn-xs btn-default" disabled="disabled">1</li>
						<li class="btn btn-xs btn-danger" disabled="disabled">0</li>
						<li class="btn btn-xs btn-success" disabled="disabled">1</li>
					</ul>
				  </div>
				   <div class="slick_widget">
					<h5>GENERAL SECRETARY</h5>
					<ul class="list-inline text-center" >
						<li class="btn btn-xs btn-default" disabled="disabled">2</li>
						<li class="btn btn-xs btn-danger" disabled="disabled">1</li>
						<li class="btn btn-xs btn-success" disabled="disabled">1</li>
					</ul>
				  </div>
				  <div class="slick_widget">
					<h5>WROKING COMMITTEE</h5>
					<ul class="list-inline text-center" >
						<li class="btn btn-xs btn-default" disabled="disabled">2</li>
						<li class="btn btn-xs btn-danger" disabled="disabled">2</li>
						<li class="btn btn-xs btn-success" disabled="disabled">0</li>
					</ul>
				  </div>
				  
				   <div class="slick_widget">
					<h5>ORGANISING COMMITTEE</h5>
					<ul class="list-inline text-center" >
						<li class="btn btn-xs btn-default" disabled="disabled">1</li>
						<li class="btn btn-xs btn-danger" disabled="disabled">0</li>
						<li class="btn btn-xs btn-success" disabled="disabled">1</li>
					</ul>
				  </div>
				  <div class="slick_widget">
					<h5>ORGANISING COMMITTEE</h5>
					<ul class="list-inline text-center" >
						<li class="btn btn-xs btn-default" disabled="disabled">1</li>
						<li class="btn btn-xs btn-danger" disabled="disabled">0</li>
						<li class="btn btn-xs btn-success" disabled="disabled">1</li>
					</ul>
				  </div>
				  <div class="slick_widget">
					<h5>ORGANISING COMMITTEE</h5>
					<ul class="list-inline text-center" >
						<li class="btn btn-xs btn-default" disabled="disabled">1</li>
						<li class="btn btn-xs btn-danger" disabled="disabled">0</li>
						<li class="btn btn-xs btn-success" disabled="disabled">1</li>
					</ul>
				  </div>
				   
				   
				 
				</div>
			</div>
		
			<div class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 col-xs-offset-0 text-center">
				<table class="table table-bordered text-left" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#fff;">
					<tbody>
						<tr>
							<td>President</td>
							<td><img src="images/cadreCommitee/Member_thamb_image.png" /></td>
							<td>Member Name</td>
							<td>Membership Number</td>
						</tr>
						<tr>
							<td>Vice president</td>
							<td><img src="images/cadreCommitee/Member_thamb_image.png" /></td>
							<td>Member Name</td>
							<td>Membership Number</td>
						</tr>
						<tr>
							<td>General secretary</td>
							<td><img src="images/cadreCommitee/Member_thamb_image.png" /></td>
							<td>Member Name</td>
							<td>Membership Number</td>
						</tr>
						<tr>
							<td>Working committee</td>
							<td><img src="images/cadreCommitee/Member_thamb_image.png" /></td>
							<td>Member Name</td>
							<td>Membership Number</td>
						</tr>
						<tr>
							<td>Organising  Committee</td>
							<td><img src="images/cadreCommitee/Member_thamb_image.png" /></td>
							<td>Member Name</td>
							<td>Membership Number</td>
						</tr>						
					</tbody>
				</table>
			</div>
		</div>
		<!-------/VIEW BLOCK END------>
		
		<!-------ADD BLOCK------>
		<div class="row">	
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">
				<h4>SEARCH BASED ON</h4>
				<hr style="margin: 0px;">
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center">
				<div class="form-inline m_top20">
					<div class="radio">
						<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" id="membershipId" value="1"> Membership ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  value="2" > Voter ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"  value="3"> Mobile No &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  value="4"> Name &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="advancedSearch"  value="5"> Advanced Search &nbsp;&nbsp;</label>
						<input type="hidden" id="cadreSearchType" value="membershipId" />
					</div>				  
				</div>
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">	
				<div class="row">      
					<div class="col-md-9 col-sm-9 col-xs-9 ">
						<input type="text" placeholder="ENTER NO / NAME"  class="form-control" >
					</div>	
					<div class="col-md-3 col-sm-3 col-xs-3 ">
						<button class="btn btn-success btn-block" type="button" onclick="getCadreDetailsBySearchCriteria()">SEARCH</button>
					</div>			
				</div>			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20" id="advancedSearchDiv">	
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"> 					
					<h6>Advanced Search</h6>
					<div class="row">					
						<div class="col-md-2 col-sm-2 col-xs-2 ">
							<label>Caste-Group
								<select class="form-control"><option>SC</option></select>
							</label>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 ">
							<label>Caste Name
								<select class="form-control"><option>SC- Caste Name #1</option></select>
							</label>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-3 ">
							<label>Age
								<select class="form-control"><option>B/W 20 - 40</option></select>
							</label>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-3 ">
							<label>Gender
								<select class="form-control"><option>Male</option></select>
							</label>
						</div>
						<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4 m_top10">
							<button type="submit" class="btn btn-success btn-block">SEARCH</button>
						</div>
					</div>			
				</div>			
			</div>	
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;" id="cadreDetailsDiv"></div>			
			</div>
		</div>	
	</div>		
		<!-------/ADD BLOCK END------>
		
		<!-- start public Representatives Block  -->
		
		<div class="row" id="publicrepresantative" >	
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1  m_top20 text-center">
				<h3 class="text-success text-uppercase" >Process to update <br> public representatives as mandal Electoral</h3>
				<h4 class=" m_top20">SEARCH BASED ON</h4>
				<hr style="margin: 0px;">
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center">
				<div class="form-inline m_top20">
					<div class="radio">
						<label><input type="radio" name="searchBasedOn"> Membership ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn"> Voter ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn"> Mobile No &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn"> Name &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn"> Advanced Search &nbsp;&nbsp;</label>
					</div>				  
				</div>
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">	
				<div class="row">      
					<div class="col-md-9 col-sm-9 col-xs-9 ">
						<input type="text" placeholder="ENTER NO / NAME"  class="form-control" >
					</div>	
					<div class="col-md-3 col-sm-3 col-xs-3 ">
						<button class="btn btn-success btn-block" type="submit">SEARCH</button>
					</div>			
				</div>			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">	
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"> 					
					<h6>Advanced Search</h6>
					<div class="row">					
						<div class="col-md-2 col-sm-2 col-xs-2 ">
							<label>Caste-Group
								<select class="form-control"><option>SC</option></select>
							</label>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 ">
							<label>Caste Name
								<select class="form-control"><option>SC- Caste Name #1</option></select>
							</label>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-3 ">
							<label>Age
								<select class="form-control"><option>B/W 20 - 40</option></select>
							</label>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-3 ">
							<label>Gender
								<select class="form-control"><option>Male</option></select>
							</label>
						</div>
						<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4 m_top10">
							<button type="submit" class="btn btn-success btn-block">SEARCH</button>
						</div>
					</div>			
				</div>			
			</div>	
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;">
					<div class="media">
					  <a href="#" class="media-left">
						<img style="width: 64px; height: 64px;" src="images/cadreCommitee/search_details_member_imahe.png" />
					  </a>
					  <div class="media-body">
						<h4 class="media-heading">Member Name</h4>
						<ul class="list-inline">
							<li>Age:33;</i>
							<li>Gender: Male;</i>
							<li>Mobile No: 96325871;</i>
							<li>Caste: Caste Name;</i>
							<li>Voter ID: 12365478;</i>
							<li>Aadhar: 12365478</i>
						</ul>
					  </div>
					</div>
					<div class="form-inline ">
							<label><input type="radio" name="searchedDetails"> &nbsp;&nbsp;</label>
						<a href="#" class="btn btn-success btn-medium">UPDATE PROFILE</a>
					</div>	
				</div>
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;">
					<div class="media">
					  <a href="#" class="media-left">
						<img style="width: 64px; height: 64px;" src="images/cadreCommitee/search_details_member_imahe.png" />
					  </a>
					  <div class="media-body">
						<h4 class="media-heading">Member Name</h4>
						<ul class="list-inline">
							<li>Age:33;</i>
							<li>Gender: Male;</i>
							<li>Mobile No: 96325871;</i>
							<li>Caste: Caste Name;</i>
							<li>Voter ID: 12365478;</i>
							<li>Aadhar: 12365478</i>
						</ul>
					  </div>
					</div>
					<div class="form-inline ">
						<label><input type="radio" name="searchedDetails"> &nbsp;&nbsp;</label>
						<a href="#" class="btn btn-success btn-medium">UPDATE PROFILE</a>
					</div>	
				</div>
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;">
					<div class="media">
					  <a href="#" class="media-left">
						<img style="width: 64px; height: 64px;" src="images/cadreCommitee/search_details_member_imahe.png" />
					  </a>
					  <div class="media-body">
						<h4 class="media-heading">Member Name</h4>
						<ul class="list-inline">
							<li>Age:33;</i>
							<li>Gender: Male;</i>
							<li>Mobile No: 96325871;</i>
							<li>Caste: Caste Name;</i>
							<li>Voter ID: 12365478;</i>
							<li>Aadhar: 12365478</i>
						</ul>
					  </div>
					</div>
					<div class="form-inline ">						
							<label><input type="radio" name="searchedDetails"> &nbsp;&nbsp;</label>					
						<a href="#" class="btn btn-success btn-medium">UPDATE PROFILE</a>
					</div>	
				</div>				
			</div>
		</div>	
		<!-- end public Representatives Block  -->
		
		<!-- start mandal committee Block  -->
		
		<div class="container-fluid" id="mandalaffiliated">
				
		<div class="row m_top20">
			<div class="com-md-8 col-sm-12 col-xs-12 text-center">
				<h3 class="text-success text-uppercase">Process to add NOT affiliated committee member <br>as mandal affiliated electoral</h3>
			</div>
		</div>
			
		<div class="row m_top20">
			<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 ">
				<div class="form-group col-xs-12 pull-right">
					<label for="exampleInputEmail1">SELECT LOCATION</label>
					<select  class="form-control"><option>Location Name #1</option></select >
				 </div>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-6">
				<div class="form-group col-xs-12">
					<label for="exampleInputEmail1">COMMITTEE TYPE</label>
					<select class="form-control"><option>Main Committee</option><option>Affiliated Committee</option></select >
				 </div>
			</div>
		</div>
		
		<div class="row">	
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">
				<h4>SEARCH BASED ON</h4>
				<hr style="margin: 0px;">
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center">
				<div class="form-inline m_top20">
					<div class="radio">
						<label><input type="radio" name="searchBasedOn"> Membership ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn"> Voter ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn"> Mobile No &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn"> Name &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn"> Advanced Search &nbsp;&nbsp;</label>
					</div>				  
				</div>
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">	
				<div class="row">      
					<div class="col-md-9 col-sm-9 col-xs-9 ">
						<input type="text" placeholder="ENTER NO / NAME"  class="form-control" >
					</div>	
					<div class="col-md-3 col-sm-3 col-xs-3 ">
						<button class="btn btn-success btn-block" type="submit">SEARCH</button>
					</div>			
				</div>			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">	
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"> 					
					<h6>Advanced Search</h6>
					<div class="row">					
						<div class="col-md-2 col-sm-2 col-xs-2 ">
							<label>Caste-Group
								<select class="form-control"><option>SC</option></select>
							</label>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 ">
							<label>Caste Name
								<select class="form-control"><option>SC- Caste Name #1</option></select>
							</label>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-3 ">
							<label>Age
								<select class="form-control"><option>B/W 20 - 40</option></select>
							</label>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-3 ">
							<label>Gender
								<select class="form-control"><option>Male</option></select>
							</label>
						</div>
						<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4 m_top10">
							<button type="submit" class="btn btn-success btn-block">SEARCH</button>
						</div>
					</div>			
				</div>			
			</div>	
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;">
					<div class="media">
					  <a href="#" class="media-left">
						<img style="width: 64px; height: 64px;" src="images/cadreCommitee/search_details_member_imahe.png" />
					  </a>
					  <div class="media-body">
						<h4 class="media-heading">Member Name</h4>
						<ul class="list-inline">
							<li>Age:33;</i>
							<li>Gender: Male;</i>
							<li>Mobile No: 96325871;</i>
							<li>Caste: Caste Name;</i>
							<li>Voter ID: 12365478;</i>
							<li>Aadhar: 12365478</i>
						</ul>
					  </div>
					</div>
					<div class="form-inline ">
							<label><input type="radio" name="searchedDetails"> &nbsp;&nbsp;</label>
						<a href="#" class="btn btn-success btn-medium">UPDATE PROFILE</a>
					</div>	
				</div>
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;">
					<div class="media">
					  <a href="#" class="media-left">
						<img style="width: 64px; height: 64px;" src="images/cadreCommitee/search_details_member_imahe.png" />
					  </a>
					  <div class="media-body">
						<h4 class="media-heading">Member Name</h4>
						<ul class="list-inline">
							<li>Age:33;</i>
							<li>Gender: Male;</i>
							<li>Mobile No: 96325871;</i>
							<li>Caste: Caste Name;</i>
							<li>Voter ID: 12365478;</i>
							<li>Aadhar: 12365478</i>
						</ul>
					  </div>
					</div>
					<div class="form-inline ">
						<label><input type="radio" name="searchedDetails"> &nbsp;&nbsp;</label>
						<a href="#" class="btn btn-success btn-medium">UPDATE PROFILE</a>
					</div>	
				</div>
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;">
					<div class="media">
					  <a href="#" class="media-left">
						<img style="width: 64px; height: 64px;" src="images/cadreCommitee/search_details_member_imahe.png" />
					  </a>
					  <div class="media-body">
						<h4 class="media-heading">Member Name</h4>
						<ul class="list-inline">
							<li>Age:33;</i>
							<li>Gender: Male;</i>
							<li>Mobile No: 96325871;</i>
							<li>Caste: Caste Name;</i>
							<li>Voter ID: 12365478;</i>
							<li>Aadhar: 12365478</i>
						</ul>
					  </div>
					</div>
					<div class="form-inline ">						
							<label><input type="radio" name="searchedDetails"> &nbsp;&nbsp;</label>					
						<a href="#" class="btn btn-success btn-medium">UPDATE PROFILE</a>
					</div>	
				</div>				
			</div>
		</div>		
		
		
	</div>
		<!-- end mandal committee Block  -->

		
	</div>

	<script>		
		$('.variable-width').slick({
		  dots: false,
		  infinite: false,
		  speed: 300,
		  //slidesToShow: 4,
		  //slidesToScroll: 3,
		  autoplay: true,
		  autoplaySpeed: 2000,
		  //centerMode: true,
		  variableWidth: true
		});  

		$('.searchTypeCls').click(function(){
			var id = $(this).attr('id');
			$('#advancedSearchDiv').hide();
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
			if(id.trim() == 'advancedSearch')
			{
				$('#advancedSearchDiv').show();
				$('#cadreSearchType').val('advancedSearch');
			}
		});
		$.ajax({
			type : "POST",
			url : "getCommitteLocationsAction.action",
			data : {locationType:""} ,
		}).done(function(result){
			$("#committeeLocationId  option").remove();
			$("#committeeLocationId").append('<option value="0">Select Location</option>');
			for(var i in result){
			   $("#committeeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			}
				
		});
	</script>
  </body>
</html>