<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	<title>Cadre Search</title>
	<meta name="description" content="Natural Language Form with custom text input and drop-down lists" />
	<meta name="keywords" content="Natural Language UI, sentence form, text input, contenteditable, html5, css3, jquery" />
	<meta name="author" content="Codrops" />
	<link rel="shortcut icon" href="../favicon.ico"> 
	<link rel="stylesheet" type="text/css" href="js/cadreSearch/Naturallanguage/css/component.css" />
	<link href="js/cadreSearch/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="js/cadreSearch/css/custom.css" rel="stylesheet" type="text/css">
	<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css' />
	<link href="js/cadreSearch/PageTransitions/css/animations.css" rel="stylesheet" type="text/css">
	<link href="js/cadreSearch/PageTransitions/css/component.css" rel="stylesheet" type="text/css"> 

</head>
	<body class="search-body-bg">			
        <div class="container">
        <div class="well search-heading m_top10"><h2 class="text-center search-head">SEARCH A CADRE/VOTER</h2></div>
        </div>
		<div id="pt-main" class="pt-perspective">
        <div class="pt-page pt-page-1 container">
            <div class="well search-content">
                <div class="main clearfix text-center">
                     <form id="nl-form" class="nl-form">
                        I WANT TO SEARCH
                        <select class="searchCls">
                            <option value="0" selected >ANY</option>
                            <option value="1" >CADRE</option>
                            <option value="2">VOTER</option>
                        </select>
                        IN 
                        <select class="stateCls">
                            <option value="0" selected>ANY</option>
                            <option value="1">ANDHRA PRADESH</option>
                            <option value="2">TELANGANA</option>
                        </select>
                        STATE
                        <br />IN  
                        <select class="districtCls">
                            <option value="0" selected>ANY</option>                 
                        </select>
                        DISTRICT,IN 
                        <select class="constiCls">
                            <option value="0" selected>ANY</option>                         
                        </select>
                        CONSTITUENCY.<br/>
                        WITH
                     <!--  
						<select class="casteCls">
                            <option value="0" selected>ANY</option>
                            <option value="1">Kamma</option>
                            <option value="2">Mudhiraj</option>
                        </select>
						-->
						  <input type="textarea" value="" placeholder="ANY"/>
						  
                        CASTE AND 
                        <input type="text" value="" placeholder="ANY"/>
                        NAME.
                        <br/>                        
                    </form>
                    <button class="btn btn-success get-details m_top10">
						<i class="glyphicon glyphicon-arrow-right"></i>&nbsp;&nbsp;Get Details
                    </button>
                </div>              
			</div>
		</div>
		<div class="pt-page pt-page-3 container">    <div>
            <ol class="breadcrumb search-breadcrumb">
                  <span class="text-breadcrumb text-capitalize">your searching:</span>
                  <li><a href="#" class="text-breadcrumb">Cadre</a></li>
                  <li><a href="#" class="text-breadcrumb">Andhra Pradesh</a></li>
                  <li><a href="#" class="text-breadcrumb">Nellore</a></li>
                  <li class="active">Kavali</li>
            </ol>
            <div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name get-details">KAVALI</h4>
					<span class="pull-right">500</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">KAVALI</h4>
					<span class="pull-right">1020</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">KAVALI</h4>
					<span class="pull-right">5120</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">GUNTUR</h4>
					<span class="pull-right">4120</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">KADAPA</h4>
					<span class="pull-right">1200</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">KRISHNA</h4>
					<span class="pull-right">4100</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">KURNOOL</h4>
					<span class="pull-right">4110</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">PRAKASHAM</h4>
					<span class="pull-right">1620</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">NELLORE</h4>
					<span class="pull-right">5214</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">SRIKAKULAM</h4>
					<span class="pull-right">1254</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">VISAKAPATNAM</h4>
					<span class="pull-right">1244</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">VIZIANAGARAM</h4>
					<span class="pull-right">2041</span>
				</div>
				<div class="constituency-box get-details">
					<h4 class="district-box-name">WEST GODAVARI</h4>
					<span class="pull-right">3201</span>
				</div>
            </div>
        </div></div>
            
		<div class="pt-page pt-page-4 container">
		<div>
		<ol class="breadcrumb search-breadcrumb">
		  <span class="text-breadcrumb text-capitalize">your searching:</span>
		  <li><a href="#" class="text-breadcrumb">Cadre</a></li>
		  <li><a href="#" class="text-breadcrumb">Andhra Pradesh</a></li>
		  <li class="active">District</li>
		</ol>
		<div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">ANANTHAPUR</h4>
				<span class="pull-right">500</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">CHITTOOR</h4>
				<span class="pull-right">1020</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">EASTGODAVARI</h4>
				<span class="pull-right">5120</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">GUNTUR</h4>
				<span class="pull-right">4120</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">KADAPA</h4>
				<span class="pull-right">1200</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">KRISHNA</h4>
				<span class="pull-right">4100</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">KURNOOL</h4>
				<span class="pull-right">4110</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">PRAKASHAM</h4>
				<span class="pull-right">1620</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">NELLORE</h4>
				<span class="pull-right">5214</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">SRIKAKULAM</h4>
				<span class="pull-right">1254</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">VISAKAPATNAM</h4>
				<span class="pull-right">1244</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">VIZIANAGARAM</h4>
				<span class="pull-right">2041</span>
			</div>
			<div class="district-box  get-details">
				<h4 class="district-box-name">WEST GODAVARI</h4>
				<span class="pull-right">3201</span>
			</div>
		</div>
		</div></div>
		<div class="pt-page pt-page-2 container">
			<div class="search-results">
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		  <div class="panel panel-default search-panel">
			<div class="panel-heading search-panel-heading" role="tab" id="headingOne">
			  <h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
				  <h3 style="display:inline-block;margin:0px;">CASTE NAME_1</h3>
				  <h5 style="display:inline-block;margin:0px;"> [MEMBERS 200]</h5>
				  <span><i class="glyphicon glyphicon-chevron-up pull-right" style="color:#E5E5E5"></i></span>
				  <span><i class="glyphicon glyphicon-search pull-right" style="color:#E5E5E5"></i></span>
				</a>
			  </h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
			  <div class="panel-body">
					<table class="table table-custom">
						<tr>
							<td width="5%"><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td width="15%"><h4>Name Of the member</h4></td>
							<td width="15%"><h4>Guardian Name</h4></td>
							<td width="15%"><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
					</table>
			  </div>
			</div>
		  </div>
		  <div class="panel panel-default search-panel">
			<div class="panel-heading search-panel-heading" role="tab" id="headingTwo">
			  <h4 class="panel-title">
				<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
				 <h3 style="display:inline-block;margin:0px;">CASTE NAME_2</h3>
				  <h5 style="display:inline-block;margin:0px;"> [MEMBERS 2100]</h5>
				  <span><i class="glyphicon glyphicon-chevron-up pull-right" style="color:#E5E5E5"></i></span>
				  <span><i class="glyphicon glyphicon-search pull-right" style="color:#E5E5E5"></i></span>
				</a>
			  </h4>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
			  <div class="panel-body">
			   <table class="table table-custom">
						<tr>
							<td width="5%"><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td width="15%"><h4>Name Of the member</h4></td>
							<td width="15%"><h4>Guardian Name</h4></td>
							<td width="15%"><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>

							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
					</table>
			  </div>
			</div>
		  </div>
		  <div class="panel panel-default search-panel">
			<div class="panel-heading search-panel-heading" role="tab" id="headingThree">
			  <h4 class="panel-title">
				<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
				  <h3 style="display:inline-block;margin:0px;">CASTE NAME_3</h3>
				  <h5 style="display:inline-block;margin:0px;"> [MEMBERS 500]</h5>
				  <span><i class="glyphicon glyphicon-chevron-up pull-right" style="color:#E5E5E5"></i></span>
				  <span><i class="glyphicon glyphicon-search pull-right" style="color:#E5E5E5"></i></span>
				</a>
			  </h4>
			</div>
			<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
			  <div class="panel-body">
				<table class="table table-custom">
						<tr>
							<td width="5%"><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td width="15%"><h4>Name Of the member</h4></td>
							<td width="15%"><h4>Guardian Name</h4></td>
							<td width="15%"><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
						<tr>
							<td><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td><h4>Name Of the member</h4></td>
							<td><h4>Guardian Name</h4></td>
							<td><h4>+91 9632580147</h4></td>
						</tr>
					</table>
			  </div>
			</div>
		  </div>	
		</div>
		</div></div>
	</div>   
	
	<script src="js/cadreSearch/js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="js/cadreSearch/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/cadreSearch/Naturallanguage/js/modernizr.custom.js"></script>
    <script src="js/cadreSearch/Naturallanguage/js/nlform.js"></script>
    <script src="js/cadreSearch/PageTransitions/js/modernizr.custom.js" type="text/javascript"></script>
    <script src="js/cadreSearch/PageTransitions/js/jquery.dlmenu.js" type="text/javascript"></script>
    <script src="js/cadreSearch/PageTransitions/js/pagetransitions.js" type="text/javascript"></script>
	
	<script>
	var selectionArr = ["searchClsType","stateClsType","districtClsType","constiClsType","casteClsType"];
	$(document).ready(function(){
		$('li').click(function(){
			var locationValue = $(this).attr('key');
			var locationType = $(this).attr('id');
			
			if(locationType == 'stateClsType'){
				getDistrictsAndConstis("district",locationValue);
				
			}
			else if(locationType == 'districtClsType'){
				getDistrictsAndConstis("constituency",locationValue);
				
			}
		});
	});
	
	</script>
	
	<script>
	
	function getDistrictsAndConstis(type,locationValue){
		var jObj = {
			type:type,			
			id:locationValue,
			task:"getDistrictsAndConstis"
		}				
		$.ajax({
          type:'GET',
          url: 'getDistrictsAndConstituenciesAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null){
				if(type == 'district'){
					for(var i in result){
						$('#districtCls').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
					}
				}
				//var nlform = new NLForm( document.getElementById( 'nl-form' ) );
			}	
			
		
						
		});
	
	}	
	var nlform = new NLForm( document.getElementById( 'nl-form' ) );
	</script>
</body>
</html>