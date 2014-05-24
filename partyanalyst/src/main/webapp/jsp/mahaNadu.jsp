<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAHANADU</title>
 <script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
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

	<!-- YUI Dependency files (End) -->
<link href="styles/mahanadu/bootstrap.min.css" rel="stylesheet" media="screen">
  <style>
		body{color:#333333!important;}
		input{height:40px !important;box-shadow: 1px 0 5px rgba(0, 0, 0, 0.28) inset!important; border-radius:10px!important;border:1px solid #ccc!important;}
		select{height:40px; box-shadow: 1px 0 5px rgba(0, 0, 0, 0.28) inset!important;border-radius:10px!important;border:1px solid #ccc!important;}		
		label{font-size:17px;}
		.well-small{background:transparent!important;box-shadow: 1px 0 3px rgba(0, 0, 0, 0.41);}
		.container{margin-bottom:20px;}
		.text-error{color:red!important;}
		.text-center{color:#000000!important; }
		.mahanadubg{background:#fff url(images/mahaNadu/Mahanadu_bg.gif) repeat-x;}
		.m_top10{margin-top:10px;}
		.m_top15{margin-top:15px;}
		.m_top20{margin-top:20px;}
		.containerBorder{border: 1px solid rgb(204, 204, 204); padding:10px;}
		.MahanaduHeading{height:42px; width:100%; border-bottom:1px solid rgba(0,0,0,.5);border-radius:6px 6px 0px 0px; text-transform:uppercase;line-height:45px;
			background: #ffe933;
			background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZTkzMyIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmNmMzEiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
			background: -moz-linear-gradient(top,  #ffe933 0%, #ffcf31 100%);
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffe933), color-stop(100%,#ffcf31));
			background: -webkit-linear-gradient(top,  #ffe933 0%,#ffcf31 100%);
			background: -o-linear-gradient(top,  #ffe933 0%,#ffcf31 100%);
			background: -ms-linear-gradient(top,  #ffe933 0%,#ffcf31 100%);
			background: linear-gradient(to bottom,  #ffe933 0%,#ffcf31 100%);
			filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffe933', endColorstr='#ffcf31',GradientType=0 );
		}
		.multySelect{border: 1px solid rgb(204, 204, 204); border-radius: 4px; background: none repeat scroll 0% 0% rgb(255, 255, 255); padding: 5px 10px; height: 100px; overflow: auto;box-shadow: 1px 0 5px rgba(0, 0, 0, 0.28) inset!important; border-radius:10px!important;}
		.multySelect ul li{height:26px;}
		.multySelect ul li input{margin-top: 0px;}
	</style>
</head>
<body  class="mahanadubg">
    <div class="container">	
		<!----Header Div--------->
		<div class="row">
			<div class="span12 text-center">
				<a href="#" title="Telugu Desam Mahanadu 2014" >
					<img src="images/mahaNadu/MahanaduLogo.png" alt="Telugu Desam Mahanadu 2014" />
				</a>
			</div>
		</div><!----Header Div End--------->
		
		<!----Mahanadu Banner Div--------->
		<div class="row">
			<div class="span12 containerBorder m_top10">
				<img src="images/mahaNadu/MahanaduBanner.png" title="Telugu Desam Mahanadu 2014" alt="Telugu Desam Mahanadu 2014" />
			</div>
		</div><!----Mahanadu Banner Div End--------->
		
		<!----Form Div--------->
		<div class="row">
			<div class="span12 containerBorder">
				<p> * Please Submit First Name, Last Name and Mobile No to Generate Party Cadre Details</p>
				<div class="well well-small mahanadu-well form-inline">
				
				  <label>First Name<span class="text-error"><span class="text-error">* </span> </span></label>             
					<input class="input-medium" type="text">      
				
				  <label>&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp Last Name<span class="text-error">* </span></label>              
					<input class="input-medium" type="text">
					
				  <label >&nbsp&nbsp&nbsp&nbsp&nbsp Mobile No<span class="text-error">* </span></label>              
					<input class="input-medium" type="text">
					
				  &nbsp&nbsp&nbsp
				  <button type="submit" class="btn btn-success">&nbsp Submit &nbsp</button>				 
				</div>
				
				<!----Heading Div------->
				<div class="text-center MahanaduHeading"><h3>Party Cadre</h3></div><!----Heading Div END------->
				
				<!---- Personal Details ------>
				<h3>Personal Details</h3>
				<p class="text-right" style="margin-top:-40px;">Fields marked with <span class="text-error">* </span> are mandatory</p>
				<div class="well well-small mahanadu-well form-inline">				
					<div class="row-fluid">
						<div class="span5">	
							<label>First Name<span class="text-error">* </span>&nbsp&nbsp&nbsp</label>             
							<input  type="text">
							
							<label class="m_top20">Last Name<span class="text-error">* </span> &nbsp&nbsp&nbsp</label>             
							<input type="text">  
							
							<div class="row-fluid m_top20">
								<div class="span3">									
									<label>Gender <span class="text-error">* </span></label>
								</div>
								<div class="span3">
									<label style="margin-top: -10px;"><input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios">
									Male </label>
								</div>
                                <div class="span4 form-inline">
									<label style="margin-top: -10px;">
									  <input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios">
									  Female
									</label>
								</div>  	
							</div>
							<label>Father Name &nbsp;</label>             
							<input type="text">  
						</div>
						<div class="span5">	
							<label>Blood Group &nbsp&nbsp &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp</label>             
							<s:select id="bloodGroupId" cssClass="regionSelect input-medium" name="bloodGroup" list="#session.bloodGroups" listKey="id" listValue="name" headerKey="0" headerValue="Select Group" ></s:select>
							<br/><label class="m_top20">Age&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp	 </label>             
							<input type="text" class="input-medium">  
							
							<br/><label class="m_top20">No of Family Members &nbsp</label>             
							<input type="text" class="input-medium"> 
							
							<br>	<label class="m_top20">No of Voters &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></label>             
							<input type="text" class="input-medium"> 
						</div>
						<div class="span2">	
							<a class="thumbnail" href="#">
								<img  style="width: 140px; height: 120px;" src="images/mahaNadu/user image.jpg">
							</a>
							<button type="submit" class="btn ">Click Here to Upload Image</button>	
						</div>	
					</div>
				</div>
				
				<!-----Contact Details----->
				<h3>Contact Details</h3>
				<div class="well well-small mahanadu-well form-inline">
				
				  <label>Mobile No<span class="text-error"><span class="text-error">* </span> </span></label>             
					<input type="text">      
				
				  <label>&nbsp&nbsp&nbsp&nbsp&nbsp Land No</label>              
					<input type="text">
					
				  <label >&nbsp&nbsp&nbsp&nbsp&nbsp Email Id</label>              
					<input  type="text"> 
				</div>
				
				<!-----Current Address----->
				<h3>Current Address</h3>
					<div class="well well-small mahanadu-well form-inline">				
					<div class="row-fluid">
						<div class="span6">	
							<label>House No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<input type="text" class="input-xlarge">
							
							<br/><label class="m_top20">Street Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<input type="text" class="input-xlarge">  
							
							<br/><label class="m_top20">District<span class="text-error">* </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<s:select id="districtField" cssClass="regionSelect input-xlarge" name="district" list="#session.districtsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','cadreReg','constituencyField','currentAdd');cleanOptionsList('district')" ></s:select>
							
							<br/><label class="m_top20">Constituency<span class="text-error">* </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<s:select id="constituencyField" cssClass="regionSelect input-xlarge" name="constituencyID" list="#session.constituenciesList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'subRegionsInConstituency','cadreReg','mandalField','currentAdd', 'null');cleanOptionsList('constituency')"></s:select> 
						</div>
						<div class="span6">	
							<label>Mandal<span class="text-error">* </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</label>             
							<s:select id="mandalField" cssClass="regionSelect input-xlarge" name="mandal" list="#session.mandalsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'hamletsOrWardsInRegion','cadreReg','villageField','currentAdd','null');getBooths('currentAdd','constituencyField','boothField',this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'cadreReg','boothsInTehsilOrMunicipality')"></s:select>	
							
							<br><label class="m_top20">Village<span class="text-error">* </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;	 </label>             
							<s:select id="villageField" cssClass="regionSelect input-xlarge" name="village" list="#session.villagesList" listKey="id" listValue="name" onchange="getBoothsInWard('currentAdd','constituencyField','boothField',this.options[this.selectedIndex].value,'cadreReg','mandalField')"></s:select>	
							<br><label class="m_top20">Pin Code &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<input type="text" class="input-xlarge"> 
							
							<br>	<label class="m_top20">Booth No &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<s:select id="boothField" cssClass="regionSelect input-xlarge" name="booth" list="#session.boothsList" listKey="id" listValue="name"></s:select>
						</div>						
					</div>
				</div>
				
				<!-----Social Status----->
				<h3>Social Status</h3>
					<div class="well well-small mahanadu-well form-inline">				
					<div class="row-fluid">
						<div class="span6">	
							<label>Education&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<s:select id="educationField" cssClass="regionSelect input-xlarge" name="education" list="#session.eduQualsList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Education"></s:select>
							<br><label class="m_top20">Profession &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
                            <s:select id="professionField" cssClass="regionSelect input-xlarge" name="profession"list="#session.occupationsList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Occupation"></s:select>							
						</div>
						<div class="span6">	
							<label>Annual Income&nbsp;&nbsp;</label>  
                            <input type="text" class="input-small"></input> 							
							<label>&nbsp;&nbsp;Income Source</label>             
							<s:select id="incomeSource" cssClass="regionSelect input-small" name="incomeSource" list="#session.incSource" listKey="id" listValue="name" headerKey="0" headerValue="Select Group" ></s:select>
							<br><label class="m_top20">Cast Category &nbsp;&nbsp;</label>             
                            <s:select id="casteCateg" cssClass="regionSelect span8 xlarge" name="casteCateg" list="#session.casteCategory" listKey="id" listValue="name" headerKey="0" headerValue="Select Group" ></s:select>							
						</div>						
					</div>
				</div>
				
				<!-----Member Type----->
				<h3>Member Type</h3>
					<div class="well well-small mahanadu-well form-inline">
										
					<label>Member Type<span class="text-error"><span class="text-error">* </span> </span></label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked="">
									Active </label> &nbsp;&nbsp;
						<input type="text" class="input-medium"></input>  
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked="">
									Inactive </label> &nbsp;&nbsp;
					      
				
				                
					
					
				                
					</div>
				
				<!-----Member Designation----->
				<h3>Member Designation</h3>
					<div class="well well-small mahanadu-well form-inline">
						<div class="row-fluid">
							<div class="span2">
								<label> Party Designation </label>
							</div>
						<div class="span4">
							<div class="multySelect">
								<ul class="unstyled">    
									<li><input type="checkbox"> Politburo</li>
									<li><input type="checkbox"> State Vice President</li>
									<li><input type="checkbox"> State General Secretary</li>
									<li><input type="checkbox"> State Official Spokesmen</li>
								</ul>
							</div>
						</div>
						<div class="span2">
						<label> &nbsp; Govt Designation </label>
						</div>
						<div class="span4">
							<div class="multySelect">
								<ul class="unstyled">    
									<li><input type="checkbox"> MLA</li>
									<li><input type="checkbox"> MP</li>
									<li><input type="checkbox"> TTD Member </li>
									<li><input type="checkbox"> ZPTC</li>
									<li><input type="checkbox"> MPTC</li>
								</ul>
							</div>
						</div>
					</div>
					</div>
					
					<!-----Register Button----->
					<button class="btn btn-large pull-right btn-success" type="submit">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;  &nbsp; Register &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</button>
			</div><!----Form Div End--------->
		</div>
		
		<!---Footer---->
			
				<div class="row-fluid"> 
					<div style="border-top:2px solid #ffdc2d;" class="m_top20">
				   <div class="span12"><img class="pull-right" src="images/mahaNadu/PA_LOGO.png"></div>
				</div>
				  <!--COPYRIGHT SECTION END--> 
				</div>
			<!---Footer End---->
	</div>
</body>
</html>