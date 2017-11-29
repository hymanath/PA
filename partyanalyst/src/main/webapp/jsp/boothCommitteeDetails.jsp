<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Constituency Page</title>
<link href="coreApi/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="coreApi/css/responsive.css" rel="stylesheet" type="text/css">
<link href="D2D_Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style>
@media print{
	.white_block_css{
		background-color:#fff !important;
		padding:8px !important;
		box-shadow:0px 4px 4px 4px rgba(0,0,0,0.3) !important;
		-webkit-print-color-adjust:exact;
		color-adjust:exact;
		
	}
	.table_alignment thead tr th{
		font-size:12px !important !important;
		padding:3px !important !important;
		font-weight:bold !important !important;
	}
	.table_alignment tbody tr td{
		font-size:12px !important !important;
		padding:3px !important !important;
		font-weight:bold !important !important;
	}
	.bg-D3{
		background-color:#d3d3d3 !important;
		-webkit-print-color-adjust:exact;
		color-adjust:exact;
	}
	.f_16{
		font-size:16px !important;
	}
	.font_weight{
		font-weight:bold !important;
	}
	.container{
		 width: 1000px;
	}
	.col-sm-1, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-sm-10, .col-sm-11, .col-sm-12 {
		float: left !important;
	}
	*::before, *::after {
    box-sizing: border-box !important;
	}
	.m_top10 {
		margin-top: 20px !important;
	}
	.row {
		margin-left: -15px !important;
		margin-right: -15px !important;
	}
	* {
		box-sizing: border-box !important;
	}
	@page { 
		size: auto;  
		margin: 3mm 0mm 0mm 0mm;  
	}
}
</style>
</head>
<body>
<div class="container" style="margin-top:50px;">
		<div class="white_block_css" id="printcontent">
			<div class="row">
				<div class="col-sm-12">
					<!--<button class="btn btn-md btn-success exportToPdf pull-right" attr_id="boothWiseDetailsDivId">Export Pdf</button>-->
					<button type="button" class="btn btn-md btn-success printViewCls pull-right" attr_divId="boothWiseDetailsDivId">Print</button>
				</div>
			</div>
			<div class="row m_top20">
				<div class="col-sm-12">
					<div id="boothWiseDetailsDivId"></div>
				</div>
			</div>
		</div>
		
	
	
</div>
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script type="text/javascript" src="http://cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js"></script>
<script type="text/javascript" src="http://cdn.rawgit.com/MrRio/jsPDF/master/dist/jspdf.min.js"></script>
<script type="text/javascript">

var globallevelId = "${param.levelId}";
var globallevelValue = "${param.levelValue}";
	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
setTimeout(function(){
		getBoothInchargeCommitteeDetailsByLocation();
},2000);
function getBoothInchargeCommitteeDetailsByLocation(){
	$("#boothWiseDetailsDivId").html(spinner);
	  var jsObj={
		  
		levelId :globallevelId,
		levelValue:globallevelValue
	  }
	   $.ajax({
		  type : "POST",
		  url : "getBoothInchargeCommitteeDetailsByLocationAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result !=null && result.length>0){
				buildBoothInchargeCommitteeDetailsByLocation(result);
			}else{
				$("#boothWiseDetailsDivId").html("No Data Available");
			}
		});
}
function buildBoothInchargeCommitteeDetailsByLocation(result){
	
	var str='';
	
	if(globallevelId ==3){
		str+='<h4 class="text-capital text-center font_weight">District Wise Booth Committee Report</h4>';
		str+='<h5 class="f_16 m_top10">District : <span class="font_weight text-capital">'+result[0].districtName+'</span></h5>';
	}else if(globallevelId ==4){
		str+='<h4 class="text-capital text-center font_weight">Constituency Wise Booth Committee Report</h4>';
		str+='<h5 class="f_16 m_top10">District : <span class="font_weight text-capital">'+result[0].districtName+'</span> Constituency : <span class="font_weight text-capital">'+result[0].constituencyName+'</span></h5>';
	}else if(globallevelId ==5){
		str+='<h4 class="text-capital text-center font_weight">Mandal Wise Booth Committee Report</h4>';
		str+='<h5 class="f_16 m_top10">District : <span class="font_weight text-capital">'+result[0].districtName+'</span> Constituency : <span class="font_weight text-capital">'+result[0].constituencyName+'</span> Mandal : <span class="font_weight text-capital">'+result[0].tehsilName+'</span></h5>';
	}else if(globallevelId ==2){
		str+='<h4 class="text-capital text-center font_weight">State Level Booth Committee Report</h4>';
	}else if(globallevelId ==7){
		str+='<h4 class="text-capital text-center font_weight">Municipality Wise Booth Committee Report</h4>';
		str+='<h5 class="f_16 m_top10">District : <span class="font_weight text-capital">'+result[0].districtName+'</span> Constituency : <span class="font_weight text-capital">'+result[0].constituencyName+'</span> Municipality : <span class="font_weight text-capital">'+result[0].localElectionBody+'</span></h5>';
	}else if(globallevelId ==6){
		str+='<h4 class="text-capital text-center font_weight">Village Wise Booth Committee Report</h4>';
		str+='<h5 class="f_16 m_top10">District : <span class="font_weight text-capital">'+result[0].districtName+'</span> Constituency : <span class="font_weight text-capital">'+result[0].constituencyName+'</span> Mandal : <span class="font_weight text-capital">'+result[0].tehsilName+'</span> Village : <span class="font_weight text-capital">'+result[0].panchayat+'</span></h5>';
	}else if(globallevelId ==8){
		str+='<h4 class="text-capital text-center font_weight">Village Wise Booth Committee Report</h4>';
		str+='<h5 class="f_16 m_top10">District : <span class="font_weight text-capital">'+result[0].districtName+'</span> Constituency : <span class="font_weight text-capital">'+result[0].constituencyName+'</span> Municipality : <span class="font_weight text-capital">'+result[0].localElectionBody+'</span> Ward : <span class="font_weight text-capital">'+result[0].localElectionBody+'</span></h5>';
	}
	
	str+='<div class="table-responsive m_top10">';
		str+='<table class="table table-bordered table-condensed table_alignment" id="webserviceHealthDetailsTableId">';
			str+='<thead class="bg-D3">';
				str+='<tr>';
					if(globallevelId ==3){
						str+='<th>Constituency Name</th>';
						str+='<th>Mandal<br/>Municipality</th>';
						str+='<th>Panchayat<br/>Name</th>';
					}else if(globallevelId ==4){
						str+='<th>Mandal<br/>Municipality</th>';
						str+='<th>Panchayat<br/>Name</th>';
					}else if(globallevelId ==2){
						str+='<th>District Name</th>';
						str+='<th>Constituency Name</th>';
						str+='<th>Mandal<br/>Municipality</th>';
						str+='<th>Panchayat<br/>Name</th>';
					}
					
					
					
					
					str+='<th>Incharge<br/>Booth</th>';
					str+='<th>Own<br/>Booth</th>';
					str+='<th style="max-width:100px !important;">Name</th>';
					str+='<th>Membership No</th>';
					str+='<th>MobileNo</th>';
					str+='<th>Role</th>';
					str+='<th>Image</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
					if(globallevelId ==3){
						str+='<td>'+result[i].constituencyName+'</td>';
						if((result[i].panchayat != null && $.trim(result[i].panchayat).length > 0)){
								str+='<td>'+result[i].tehsilName+'</td>';
								str+='<td>'+result[i].panchayat+'</td>';
							}else{
								var leb = result[i].localElectionBody .split(' ');
								str+='<td>'+leb[0]+'<br/>'+leb[1]+'</td>';
								str+='<td>-</td>';
							}
						
						
						
						
					}else if(globallevelId ==4){
							if((result[i].panchayat != null && $.trim(result[i].panchayat).length > 0)){
								str+='<td>'+result[i].tehsilName+'</td>';
								str+='<td>'+result[i].panchayat+'</td>';
							}else{
								var leb = result[i].localElectionBody .split(' ');
								str+='<td>'+leb[0]+'<br/>'+leb[1]+'</td>';
								str+='<td>-</td>';
							}
						
						
						
						
					}else if(globallevelId ==2){
						str+='<td>'+result[i].districtName+'</td>';
						str+='<td>'+result[i].constituencyName+'</td>';
							if((result[i].panchayat != null && $.trim(result[i].panchayat).length > 0)){
								str+='<td>'+result[i].tehsilName+'</td>';
								str+='<td>'+result[i].panchayat+'</td>';
							}else{
								var leb = result[i].localElectionBody .split(' ');
								str+='<td>'+leb[0]+'<br/>'+leb[1]+'</td>';
								str+='<td>-</td>';
							}
							
							
						}
					
						str+='<td>'+result[i].boothName+'</td>';
						str+='<td>'+result[i].ownBoothNo+'</td>';
						str+='<td style="max-width:100px !important;">'+result[i].cadreName+'</td>';
						str+='<td>'+result[i].memberShipNo+'</td>';
						str+='<td>'+result[i].mobileNo+'</td>';
						str+='<td>'+result[i].role+'</td>';
						str+='<td><img width=50 height=50 src="http://www.mytdp.com/images/cadre_images/'+result[i].image+'"></img></td>';
						//str+='<td>';
						//str+='<img width=50 height=50  src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAlgCWAAD/2wBDAAMCAgICAgMCAgIDAwMDBAYEBAQEBAgGBgUGCQgKCgkICQkKDA8MCgsOCwkJDRENDg8QEBEQCgwSExIQEw8QEBD/2wBDAQMDAwQDBAgEBAgQCwkLEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBD/wAARCADIAMgDAREAAhEBAxEB/8QAHQAAAQUBAQEBAAAAAAAAAAAAAwIEBQYHAQAICf/EAEEQAAIBAwMCBAQEBAIIBgMAAAECAwAEEQUGIRIxBxNBUSJhcYEIFDKRFSNCoTNSCRYkQ1NiscElcoKS0fCy4fH/xAAbAQADAQEBAQEAAAAAAAAAAAAAAQIDBAUGB//EACwRAAICAgICAgIBAwQDAAAAAAABAhEDEgQhEzFBUQUiFAZhcSMygbGRocH/2gAMAwEAAhEDEQA/AMqa4x61+sH5UD/MjOOofvSsVhElVuO9Owschl4xxQUggkTHegYsSLQVZ4Mme9BIsPHnuKBoKhRhwRQUGQp/mFBSPMB6GgGwDsBjkUEnBIvyoCxfmADvQFg3nX1xQNtCVmjIwSKBCuqM+ooA8TGfUUrAH0R+lMGhPQo5zQTQh5FHqKaYhpNedBIBpEsY3N7wcEGlZLZHvqB7HjFOybR5b3PrSsT7Gb651DHUalsLYhNWUnluaVjskIdUTIw9UgUhz/FBj/Ex96dlbiTquDxJn70WLc9/FyP6zRYbnv4yc46jRYtxaauM8ueaLHuPINXXpwXPf3ospTHcepo39dFlqYttVQKT1UxtqhpNqYJyGP70J0Tsgf8AEiD3NDYbI4+q4Ges0rFshlNrQHaQ0WTswa6yT2kNLZDTbCDW2A/WaTkmNNnRrucc1O6HbCLrXPLkCr3QWxTaymP8U0t0G32MZ9bGThzimpJkuQyn1sHjqzS3RDYxl1kH0/vQJsYzav8AFwO3zotIS7FQ6r7kilsil0QLaic4D1kpg4tB4blzg9Xfmq2QqY+S7dQD1UeQpxPSam6jlzQslir7A/xh846zVqSZL6CLqUr9mosVhBfzE8mixBRfScHNFjsLHqMn+bFHsaY9h1NsD4qYwrao3ScN/em2O2xtJq0g/qqHkob/ALAG1twfibFZyyWwSbG82utzhqXkK1Yxm1pz/XQ8gasQuryn+uo3sai0LGrzAY8zFJzopREnXJVbBkqdytEEXWm7+ZQslsNEKOtSf8TIq1JkuKAzam0g/XVKTRDiNvzj5wXo3J1DpKrd2zRvRSx9nW8s+tS8haxiQUU9/wC9T5CvGRCQ/HzzWKmavEh4g6QAPSq8jF40O4zI478UKTQ/GhLxu1Vs2RLGgZgINbIxnjQ4t4iKtNmdIexW+e4q0DSCG3A7VVE0JMXSaAOqJAeBSspIXmRhjHNKykgTxsf1H6VjJlJDaS2kA5FZs0SGU1q+TzSGB/IOTyamyqHUOm/DllzSGkjkmngc4qWOkMbi0cHgdqB0gIhn7AUKLRDHcFpK68ritopkMJ+SYDmrSM2mwLWxB+9S0ikdVWQZGeKxm2jaCTOtI475rKUmaqKE9Z9aSky1FFnuNqyBsqpB+lRHpHRKNsAduXC91+9VsZOFBl0hoxjpNND1aPNp7L3Q1aZLTBGx90NaRlSMpRtji309eCQRmtFMz0H6Wa4+KhZEHjYs2seOBVrIn6E8YJ7NDk47Ubi8f0R+qXVro9pJfXbqEQcAnBY+gFZZ+VDjx2bNsHBycjIor0Zfr25tb1WRwl4beFhlY4z0gY/6mvmOV+UzZek6X18H1vE/F8bAuo2/v5Kbq+nX9vD+cuppVUEkFpSCfpXmvkTu77PSjhVV8DvZvijqWgXS2uqzPe6YxCsH5eHPqpP9wa9Hg/l8mCWuV3F/+jzPyH4fFyY7Y1Uv+za4zbXtrFe2kyywTIJI3Xsynsa+shOOWCyRdpnx2THLDN45qmjqwnq7Gk2CHsdv1L2qLLOvaEjIFMQxubQn0qaGCjsVzjpOaEhNWSEVmEX4lyatC0ETwIBkLTtC0IydSGIxRaJ0BCJj2Q1EvRcfZ4WkspCqjZ+lZS69mqHMO3ruXnpYfLFZtovVs3jS9qNqZbMRx9K5Xko9tcfYFr+xm06LzUjJGOeKamTk41FYGkdbBUhLMfQCqeSjm8TfwTuneGl9qSCR42UdwMVLzG0eC5+xxc+E91bL1LEWP0q45uglwHEh5NoXMLlJLUjBxwK1WVUc8uNq6Pf6pyuOICKHOhfxZHRsq67+SQKSyg+LIr3iLom49C2lNqu37V5bmOZEaOO3aWToPcoB68Y+9eX+T/J5eJCoR9/P1/k9r8N+Cw/kN5Z51rXX2Ylvnel/uz8jFd7aGhQ6arRdEhYSyk92fPds/IV8ri5GXNJzyz2v/wAf8H1fO4/ExVDjw0S6a67/ALlU1y70M2ZjtdRnWdwQcqOkH510W/k4FFIst1uHw+vtoXtrqEc99dXVpBDZ2klmkX8NnXHmzi4DFpi2CAuAAGOewobLTSMhujBEJBFAVDPwpOQB7fOpvshqzZfAXcceq2s+0LtsTWoNxZg/1R5+Nfqp5xX0n4nmXB4pfB81+Z4SUlnivZstvtqZ3yUOPavY8lnirBZN2m1upeYTRuaRwMLNtFzHlIhTWQHx2Qd5tueJiPJ7GtFNUYSxSTG0e3rsv/hNj6UboSxSfQ8O3bsqMRMftR5EivBMC+19QkGVtG+9Q8sQWCbERbC1G4bLwsoqPMi1xZMkY/DW8A6sEge9HmRX8SSH+n7HdZMSQYx7ioeRMuHGlfZaLPY6kA9A59MVDkjshxfk+ltB8OrW1jZPIAOfavP8h9BHFFDXdex7eW1MPlDng8U92E8cWUvRvDSCO8B8hSM+1U5ujnjx42aXpexIkiUGEAADsKhzSOpYVQ4vtjxmM4iGAPahZUweJeil6lsOPziPIB5PpW6mYTwRGSbGTrB8gY+lXsiVgQWXZShcCLGPlRsgeBDcbQKHKqR9OKltSi4v5EsKXZTN+/h82p4gWUsd5ZLa6gQRHeRIA3Uewcf1CuLPwcOW2lT/ALHRjnkj7d/5Pz+8TvDrUdjbmvduX9u1vc2sjL0kfCcdiD7HuK8GcZY5OMzuT29En4J+Cu+PG7dMW0drROEiw17fGPqitI/Vj/zH0XNZOauilFs+qt0f6MjSYtqSS6PvDUBrUcbMs1zgxSPjgMgX4Rn1rRJP0NwaPhxLTX/CjxGWy1WD8vqWg6gsdwqt8J6Ww4DdipGea1485YMql9M582NZYOMj9G7HZPnRRXEUA8uaNZUYc/Cyhh/YivrlNONnz0OM0+yXh2YUUfy+fpQpI08CFnaTuegRj9qe9FLAg6eHKz4MkIOflS8pX8SMuxUvhosI6hbjHrxUeUP4kV2ct9jIG6RbD9qHMa4yHEuwQSP5IGPlUPIV/HX0Fi2csYAaFfbtU7s0jgS+AzbQjVeIemjdg8Koj7vbKRnqVR+1G7IlhQbT9JQYBT1pqbKjj6PpS5aC3BCKB69q8+KbR6BDXcK37EdINaptEsVZ6DHG6uVXH0qHNtFxSssttbwRooYDgVi5Nmhy7NuUKLj9qEBB3OlwzMWwM1sptIhpMGmixD/d/uKt5GLRIRPo8RT9CihZGGiZFXOmRqCOkH7VcZWJwSIDXr/QNsWR1XXr2O0tw4QMw6mdj2RFHLN7KKcsyxdyI1v0RWvfhO21+ILXNIi35tS5sLyG3W6muIp2imtbR+Y4pscMzDnoP6c9+9fP87kx5Dc4qv8A6d2KHiVM+lvCb8OvhX4KaH/BNk6BHbxMxaWQrlpG92Pqfmc15ibNNvomdy6LZS20hSxLqBjpHAPp962hNplRbfs/PH8b34PIvEO6j3XtQWum6+g6XRl6YriP/K3T2I9GNdtLNHr2ZZY6Oym7an/Er+Gjb+jS+NmiW259leVHB+e09kll06HAAYyJgsFGOHXt2Nd/F5s+lM48mFR7o+nLOHTtT0+11TTbiO5tL2FLi3mT9MkbjKsPsa9eM9laMNUHttJjeTPQuaG3Q9UTltoydP6R+1Z7mih0PH0eDoIkUY+lRsx6DJdJtUdiFU/ajZhqgctlBnhRRbCkMbi3gRsMoIothSG1zDF5ZCgZqrIa6IS4ijBPWBjNFsmkBiiiD5QCqTYn0a5qDlpiFPFc8OkaSXY40626fjPrzU5Gi4JklLII0yo5rJK3RqRlxqpiJBbFXoJtDT+IGZs9Q9qpRaJsMt4keOog1LTQ12SVvMZlDKvBqHJF0euZo4kzIB+9OPsKZXdSvrQnAI6jxjNdC9GcvZnP4dtPXxx/EnrO59ViS5214fx+Vp9rJzGbzOBKVPBbIZs44wteL+RybPUvH2fcMVpBay3EyRgSXUnmSN6scY5ryjYHdzOF+A0FRX2Quq3C+URcPgAcfKqijaMTMdc27FuTW1EkqNbxMWc5/VXSpUqNVHog/E20sl29Po72cMts8TRPDIgKshGCD8q2wP8AZGOX/YzD/Ck2mibXO0reQeVolzJDbof93bM3VGo+QyVH0r6LGv0R48ZK2i8QzfEGHerplkxb6qqKOrjArNwbZVoRcayHBAYfWhYxOVjFtU79T/tVaAmhvLqER5EhJpa0DaGj3ayHhu1Ajj3fw4c9/WhAyMu2RxwaokVZqi/E3eiwNPkliab4uOfWudI1TRKwNEsQII7VnLtmifQ2vrzpQ4PFVFdg/RW7q5MrnkitlFmbZGTamYn6I+on5UpfqrYk7dFq2poF5qzrPICQeea8/LyUrOqOOjRBtcW9r+gZArh/kI1UOzLN/wCqPokcpc4C/OujHyF8jnjpWYlP4mwzXNz0XORbq0jYPsCcV0/yFVI4pPs1P/R0aU1vsDcW8ZISp3FrcxRiOSkYx/8AkxryeZK2aYY/q2fYDdEnc81wtmyI3U3MMJaJepifftSujSCtlD1oahqEpjaQorcEMauM1R1RiqIFLePSZsI56yeliTkE1WwygeIurLcqy5BHqoPtXVifaMMi6ZiW2njstx36rkG6jB78fCSf+9fScealCjwpfpkb+y/Wd58AJPetrNEw73ZIwposBlcTyr2Y0tkOhr+ZlbPxGjZBQBppWbuaTkIXHcOpAOakpB5puteDigGNHkkJ6RQ3YqHFt1v34pWNIvcsshkHmZBHtRQmPoL9ulVye2KhxVlqTR29mLRE55pKNOy3Kyq6hfPEWKg5H961iyGO9saedSulkmUnntXDycvwdOGCN+2fpdvBaqBEBgCvDzTbZ1VRZL6OCK2YsBkCue2EfZ8nfiO1WOO0uxCekgEDB+VNyaNci/U+Idj69d6lq2t6dPdsHbzeo9+mMLkn/wC+praGRxdnB4lKTP0x/B9pLaB+HzaEL2r2z3Vs980cgwyGVy3xfPBFLPN5P2ZcY6qjaxfRxjLmuVmtWQ+vaykMDeURkdzUNm2PGUe910mRumQN0+pHrStnUoUVbU9cunbkjHV39iae7HqjMd6X0kqMwKk55OK7MU7ObLGl0ZHrzarb29xqGiD/AMQtUae3X/iOnxBD8mwV+9e3xszUWeJmhcrLlsffm3d97YsN1aHcK1tfR9TIT8cEo4kif2ZGBB+ld8MqmrQtaJ83lvn9S/Y1WwUJe6hZSCRSstobtNbrn4hRZI3N1Cp/UKLCgcl5B36wKdoAbahCB+vtSsVgm1a3jGSR+9JyoLQ3k3HFEpKuP3qXIe1H0bdbKEhL28uWP7ViuR12avH2MV2bqaydJVcD1qv5CJ1HLbK1F1P8rqFR50ytSGvth3rMS1kxwaayOXQ1H7H23dFFhOOuMpg8g1zZ432bQepodjrcOnw5ZvSvLyY3Z0xdkPuDxAheJoY5PiI9K5TohjXs+aPGH83rVtckc5BPNL2GWNro+XdJ2febP03W9wG2eXUtTvLayVeknyTPII4Ex6lizSH2Cr70nKmkc8IPVv5P1h2naxaPtbStIgTC2dnDCo/8qAH+4rWYKB26u7huoHJGKwZqoqyr63euIiHckZGRWLbOmCRULqe4eKYxAmRvhUe+O/3qW2bRSaIW8E4UPNKT1HqJ+XpStiaKJrKyShlf4lLcE+tbYpOznyK0Va6sVicuiAEc17HGlUkeXnhSbMe0y3l8Jd4Xs9orLtrc95/tEa/psNQb9MnySUfCf+YCnwubrnlhl9k5sNY1kL0m+I+rpMnbivb7R53lDnfNvjiYUm2PyjeXfMZ7TA49qlzaDyWMpt/QqcGSoeRhuM237AxP+0Dg9s01kJeWjzb4jkGA4GatzFuMbreSHvL/AHrN5GG5BX2+OjIWXP3rOWR2G5+lwtGVehTiuZyPUFRWN2OcmlvFfJNDhYbtV/XzS2QJA2jusYZGP2o2RQ1udKmlbrEbAkYzirjkSQAf9XWYEyzEg/00OcfoCOk8PtFku1u5oHHuOrjNYPHC9q7NPNkaqxvuvRNpbT2zqW4p9KgunhixHHKM+bK3CIB7lsfbNKWTxRckqHj2yTSZmeyfDrRJtW0fb2r2SXD2kja1esf0y35II+oQ4UD2UV805yyZ1L7PccIxxa32fRFuUEAVlIKnORXpN27PMaI/UJn8osrcjj6CsJezaBUdXi6iTMxw2OBXPLtnVEgbkyfBGD0hW6ifU0qZRD3zAr5Y+L0waaAqWqRSeaMp8ua3gYZFfREahZZh68AEZ7V6PHa2ODNHrop2tbWS+tJUvrYvZXwMRb061wf3GQa8/kRnHL5V6OjDrOHjkZvubwr3ta3N9JokDX0Vq6h44v1gMvUrAeoIzivqOFyPNhv5R87zuFPHkco+jO9S/wBZNKumg1OwubR19JYitbSnXs4Y45X6EJqV+4GZPTI4rmlmS9G8McvlDK+uNSf9LsPTFYPJbNPExrANSLZMj5rRT6M3HslY3uxHgy/tVPIqEoMT03cx6RITWLmWoNji30Oab4pATzTUkx6H68rbqjdQjyRXO5WeoFKtMMFVGPlS3QC0s52HUVWpeRALls7hF6vMB+WKFMBqbeefjgkVpukACW2nXOVFPZAR0q3KPwM57e1abIDIfEK91DdHiztrZFvHINP0SBtc1HB+GSY/Bbo30OWxXHzm/wBccfk7OGqbm/guuh6db2m8vy8a4MVmFZx3ZzyzH7muHlYVhnBL4R14su6lJ/ZdpWZGKrmiMqVMxojbrzDlSpPpk1nJ2aQRAXlpjLytkJk1kzoiyC1CJmk4AGM4pFWQc8PlAEjJzjNOhld1bLSt0p//AGtYHPJkXMhli6SveuqEtWYyjZYdo7Zl3HtnWNES2WRoXW6gYjlJMHt9cYrohBZcckzGdwkpIRsuKODWtP1GaJVW4Q6Tdqw7PnML/UHqT/1Cp/H5niyav0bcrGssNol41rw+0Ld6TWuoaXbyxlSp8yIZH3xmvZ3jXZ5UoKR8975/BzfwXb3ezJ1aNic283AUfI1hkx79xZPjKNe/hb8TbaVbdNFiuMp19cb8A+3PrWXjcX2xPGyhbn8MN67Q6n1rat9bxocGQRFl+uRVUYTxtMpU90Q/QqMOcHIwf2pP0ZU0yS0tephkDJxWRpFFrtbVSoPSKpOjRJUfqvmPHTkVzdo60rOrLax8MM47/WppiHUEscmSDwKTi0AKWYsWYAdIqkq7AbxpKh8wEAGq2AHN8ROXOc1SYDdoFYdIZRn+o+nzqrfwBmexdMh3Huvcm7n5jurowxP7xxfAgHyyCa50/Jm2+jvyPxYVBfJLaZEI963wU5CpgH7VHOTclIOM6x0W6RgcHoHNchoyOu4yeA+COTUM0gV+/wCroeMHqzwTSqzS6ITUDgDHtTSodkLJG8kLEqM5ooNiA1G2fzmYL61pBdmT7I94OhcsMVvRLVmh+DylJtUYgdPlxH75NdXH6t/Zycj3Qw3pt2W11PUbaxHlDV7c3FrxgJdIc5Ht8WDWGfHpLaJvgn5I6s0rZ1+m7NvaZr7IqPeWqPIoGMS4w4P0YGuxZXKKZwyhrLUmm0mMuFZxg+oNPysloRdaLGsYEU2CPU4qllfyIj7vQYbmAxXXlTKR8SuoIP2NPyj1TXZnO6Pw/eHm8opl1Ha9osjDAngTy3B98itHJPozcEzLR+CDbUM7GDdmoQqSSiNErBfuRUaxF4kVzdf4Vd4aBbSXGgalDqqR8+X0dEhH/Sk4P4Dxo+xXluFbKz9QzzzUpRfdGlixeyhgWiPA5+tLQB/ZXqdOXOOqspRbKaONqMUbMmGZc8AU1BtEg5r53VVgDAZ5zT0r2AtFuGw3Sv8A6qQFf8SdWudsbE1vXPNCyQ2pSBF7tLIRGg/9zCk5qKsvHHaaQDw+2y2hbSsLQE5Eal8e/qT/AHrPjKo7G/JleShnYMtrvW/tnjK84Ut/UCMg1fLW0FIMMqVFnJLKfl615xvdkZeFQrFT9TWbNoEHKrO5x+k0JWy2R11ahmA+wrXVCsbjTwIsEZ49KQWQt9pg8xgFz7fOtYpEv0RF7piRLl5snP6faqIZcvCiBurUJY1YoBGpx2zya7OOv1OTP7LPvWyS80Rb2GI/mdLcXKn1Kj9Y/Y/2pZ4toMEqkkhh4S6gkGnazo4XqjsdUdoR/linQS4+gZm/eowdw7Dk9T6LjHL0SMFBA7it2lRzBmd5OSSaVFICZWJKeWCBxmlQrBhwjc/Cx9PerY6EXJjYdSsM+5pxYUMjP0nplwVPpVU2BMNYSBepMnFZ72SA6GBKyhsVeyoAwjgCD9RqdmAorCy4BII96Sv5ADGrI+QgYU7AdrK+eR0/MetSwM/8b7q4vbLaW3CP5Wr7iiMp/wAyQRPKB/7gp+1c+df6b/4/7Onjf70aLa2sttaQwCIfy1C8+wFaJqjCXtlbvLZrvecQMeOm3zgfU08jXiNMTomJoCFxjBHc+/yrzzf36IHUmPUVULj2FDN49EYerzCCp7DNTRp7OiFSw9vWtV2RL2eeFAuAOfQ0AiOvrVT8ZHSB600UVjVIoyzdTEgf3q4q2S+l2XHwig6bLU5pHVEMqIBnuQD/APNdcY1GjgytbF6uJrR0a3c9SsCpBXggjFPV0ZX3ZSNs7cl23NrJknjkW/u1kg6fSJECqD7Hv+1GHFo2zTJk8lNk0s4UENJjPat32RZJWssoQCOQMe/NZSj2FhWumB+OEZ9SvrRqiRvKGuMlEINNAho9pcA9RZgPp3q018jbBTSSRctCOKpJP0IsoedG6SCwPrXIAkr1jq6GHvxTsAi+Wy/AnPzpKwBuYw3S6oc1fYCM268eWQc/00f5AUkIc9XRIfWk2gK/unad1uPW9p34jYw6FqUt7L1ccNAyAD3+Iisp01RcJaeizz3V4mT5fFaJRfon4Iu2VZdwrdPGy5gKc9+M/wDzU5o3jpFR9EnqZjWIj/pXFRvCylanP5chBPOeaVHUlY2EhMfV0sCaKLSoV1kKDzmqXSJl7FZ5HT680DS6Gd2rSKYyp55p1QL2VnULXDkNn1Na4/YsnoumwtIuLXRBKV6fzUplGTglcYH/AErtTSXZ5s+2TF9Kscn5diQ3oc96tJNWiaAieGUdMjqQn6ulgSPqPSqUWIBPdaSAVMoUgZJfgVSi36D17OQSW8aebBcBwe3TIMUnB/IWgw1YrgM6cD/NQsdguzqagz/4TDq/tScUgHP5666CC6KR7A0qQAJhPcklmD8dhVKkBY1cOCBJjPbNcYBEu4QpRiOocVLj2B6O3E3IbpHsKe1AOF0+AqXI5pbsDv5WJAMD580nJsAsaoMVLsAjSEYUAAfvUtf3ABcr1fp7GqjKhpWRc9u8TC6Ucxnn6VblsqKXQK9vEaHkCudwpm+MqGolPzJlc/COT7VEulZ241t0DS9tnB6GBFZ736Nnjo486P2GKpSJcBSXVunMkgAHfNG6GsUmNzqFvdFvKdSB65ojkUgeJrsqW9d0aZoVhJJcSBmIwFAzlvTivU4eB5GcHJzLGqZnaePD21yk82qXEIgXHko3wccBQvpXtrhxao8d8qKITdv4kdz7gsmsLGOGxjYFXlj/AMSQehOf0n6V0Y+Bjh2c2TnSl0qM6s94bis7t7yz1m9jllPxuszZb68811PFja7RyxzZE/ZNRb93ReShr3U7mc4AyznOKl4ca9I1WefyWrRN6bmbFrZXU6K55HWec1nPFD20dOPLN9Gz7BsdW/L/AJjUIJW81erLMWNedncE6R1wv2XNUEbgxcr7n3rkfaNR/bTOW6c4J9DWdAOngYnMTYJ74PrU2gJhfKAKt7VzMDwKq46QDkZp/ADuIiNQzMQD6VDXQDiK6iAIz3qGqA60oJz/AE0gBG6Cv0qvFVqwOG5kz2o1sD35wAYcY9qWg0NJ9QVlKuMLg/erSooY3dm76UbuNh2/Y1lKSto3xGaa/q16J5NMFufOXlz6dNc2RWqPVxJJ2NdNuZI4/wCaCKzhHX2aydjmfWYIFJMwGKcuvQ4pN9lH3fv+302Jj+YUZJyfWs4YsmZvU2yZcWBLYi7PfVnp2lx389+v8/AVc+5/7Vmm8fs3hxnminEy/wAatz3Fzf2FvaXp5jM79J75/TX2X4hf6WzPh/zjlDLpFlC0yy1fVi0tujz9JwyqMkmvZWSMTxI4ZZOy4aV4V7y1G4htE0pxJMhkTzWCjA+dRPkJHRDhzfwW7TfAvdjySxNo7BoY1Zizggk+gx6/KsXzMf2bx4bstOg/h43HdI0t7DHY9BGBMSS49xj0rLJz4RSo1jxPs0PangeNLnE2oHq7FDGDzj61y5PyG3UTohgUUafbWb2UAt4LcoiDpGV9K4Zyc3bNV0Bm0WW5BaJAo/7/ACpeT7AANKvghMkgBHb3qlJMBC3F/bcFOvp/+96HGLAm0IUg5JbtzWI2FDsx4XGfQVDEEj88cBsigAnS45LikwCYZELOeCO+f+1KgOGRXOVcN86RQSKQ8dX70P0KhcsClQcZJqVJIEBNl1j9Ax86fkQykeKl1rembc/IaHH1S3dwqHIz0jHJ49K5eXJJJv5Pb/C4FnzU/gzDbO4p9TS50/WLiGy1q3VEtQ3wNdpzllVuTjAzj61PBzxnPxN9nf8Al+JLAtoLr5A3ut6zKfJkihOBgSL8OT9K9OWOEumeFGbTK7q9juq9tvPtppF6gTgoRke496w8MPgqUpNGWbk0fc15GVjsrm8lY8KinnHrn0wKWXP/AAsTnDpi4/ElzeRHHNWiT0Lw833ujSbfU9P07T3tGAEMsl8uG9OsBATgc96+U/kyb3fyfo8eLGGNY18EhL4TWs2hHcx3RYarFaxn8w1uGkAKnDqM8/Ce/btX1HG/qFYMShOPS+T4rl/0rLkzeXHO/wCxs3hT4OWthZ2Op2V7aXtnNGJIpok4kB5//X2r0Zc5Z4qcfk8B8Z8VvFL2ja7bbdpGqN+VgDxqQpVBkZrleXsgcLpUUQ6ljAB749aXksB/a2MCgYFZykwHbLHH2x2+9TdgNpPKeMgsQPWgBofy6SdXnAkDGM1tbYA5JIXwijqOeTTXQUDnsIOjLj7U1OgEPag8xjFCf2Ng2Toz5mRgcUrCjyLIHBUkYz3osR5fMkbLkdI9M0MdBeR8LDOexHtSTCgkduGx0gZ980DOFZMcZ6qQwsdzPEvxDJ+dJxTA9JfzKD8Iwe9LVBRnXiRrtrFbpZ3wnkFxOsKrFIUbLHHDDkcc15P5LL+0cS7Z9l/TfDlKMsv9v+ij6hsjZunapHImkyXszDzIZbi5kdxKoyOhmbg15EJPHnTSPpJxefA/JVfRzZWmbpu/EOW+bRLO42xcKhBnn/n27FeWQYwQCOxPOa+jwY80KlKVr6Piudl4sk44oay+zb7vR7OeERNDGFAxgKMAewrqtniLJ0U7cnhFtPdOm/wvVYriG2ErTn8nMYCzsME5Xmpy44Zo6yOnBzcmB3Ai9L8C/D/QLS2t9BtbmyjsEZLcQXcp8sNwcAnB98GsHwuNVKB0r8tyb9kT4Yfh9stq6Xqmh7jvX1VLnUZ7iCQEowt5DkIw4HV3zU4OHiww1krHy/y2bLNSg3H/AAaxsnZO3tiaaNJ23p4s7IymcQLIxVWOM9OScA4HHatYQjBVjVI8zPnnyJ75PfosZ+HJUFas5mCYs/wrwPegDsSSIcli1EuwFyB/WkgGs6PMjIp6fnVoCIGnXIctlT/atNhpBtL81y5Mg4PTihsGSCSI/wALDsenmp9hVgLaWWYMksYTp9vWhjob3D+Wx6nxk/sKaAQ8qHpKy80KwoW6hQCJBz7U7GLgJdTwfvSAUvmD4ASKAPecAcB+r3xQATAkA+Ij60MBRgi/UsnUfalY0Y94jbG3frnihp2tW01tb7dsrDqfqkYvJdFscIBjhfWuLLxPLmU7pH0f4/8ALvhYNI+/RG+Ivh3u7X9rQ3G07wS6vpkv5qzFw3QJGHBTI4wRxz71OXgQa/X2a4/zcpyqZrG0bWaLS7Jbqz/LTLbRedEDkJIVBYZ9ec12JOEEmeByJ7TdeicngMkfloSOe9FnOIFkRGiSZbpH70WOxUVisbGQJ057U7ROzC2tmFnMzMSSMHNS5CbsfdKA8nt7VNv4EcdlxgYqkFAQwU5/sKAo8ZXIyFxigKPFpSmekmjokAzA5PTVIdAnk+EqRkGrooFbQIGBj4AOSDSYmrC3CqjqzdS5NCBdEfDLc4ADd/U1dIYi5gkLF3OSfSi0AmOB5AVxn50MA8dk68sAPkTUgPFDiLAdRz7YNJugOflfOIdjjFLYAoskTlT2ocgDJEpGGAqbYCGgRTjFFsaBTWkEyFZFDDPY0rZSbXoXFahSGQYx6U7bBt0FUIDlVAPakRbFkKRgtigDuEAGGoA95hbKn070qASTjAU06+wOuTwATQAjqccGgBYK49vnQARZEAHSATRTfoTFBwckDFKvskbyPGqkkDNUmAzaVMDGD9a0LG5uEDNnqDA+lACZJmOHUnjsPU1SQHUgkLhUft6Um0A9isyy/wAxiajYAhsmUdceFA9BS2sDgjweUIPvnNAHGDFgoKkUWFBAoIPUOB3NL5ADcvCkXWo5PaqXsDtpdoyjzB0vjH1qZRr0FDo9AGR681KGhQUSc9PFMTQJoirDpb6fKgoUkcjZ+L6UCZxwy566F2KgWMnA7HmnTKFxtzluwpAwhIk5HcUEgTIQekjB96KAUoY8Dk0CsV0H+ofegGckcGIgcg00JDbzeMHt6j51VFCjJFGAxGc0AJTDAsMEHvQgGkqBV6gVLZ7/AC9q0QDXLmTyxwBx96YH/9k=">';
						//str+='</td>';
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#boothWiseDetailsDivId").html(str);
	$("#webserviceHealthDetailsTableId").dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
}
$(document).on("click",".printViewCls",function(){
	var divName = $(this).attr("attr_divId");
	
	printDiv(divName);
	
});
 function printDiv(divName) {
	
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.getElementById("printcontent").innerHTML;
	 document.title = "";
     document.getElementById("printcontent").innerHTML = printContents;
	 window.print();
     document.getElementById("printcontent").innerHTML = originalContents;
}
 $(document).on("click",".exportToPdf",function(){
	var id = $(this).attr("attr_id");
	getPdf(id);
});
function getPdf(id){
	var options = {
		pagesplit: true,
		background: '#FFFFFF',
		color:'#000'
	};
	 var pdf = new jsPDF('l','mm', 'landscape');
	  pdf.addHTML($("#webserviceHealthDetailsTableId"), 15, 15, options, function() {
		pdf.save('pageContent.pdf');
	  });
	
}
</script>
</body>
</html>