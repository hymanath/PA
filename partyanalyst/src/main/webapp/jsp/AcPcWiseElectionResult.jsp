<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
<script type="text/javascript" src="js/districtWisePartyPerformanceAnalysis.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<title>AC AND PC WISE RESULT</title>
<!-- harish start -->


<style>
#navcontainer{width:960px; height:33px; background: #429b9b;
background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzQyOWI5YiIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjE2JSIgc3RvcC1jb2xvcj0iIzZhYmVjMCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjgwJSIgc3RvcC1jb2xvcj0iIzQyOWI5YiIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiMzMzliOWMiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
background: -moz-linear-gradient(top,  #429b9b 0%, #6abec0 16%, #429b9b 80%, #339b9c 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#429b9b), color-stop(16%,#6abec0), color-stop(80%,#429b9b), color-stop(100%,#339b9c));
background: -webkit-linear-gradient(top,  #429b9b 0%,#6abec0 16%,#429b9b 80%,#339b9c 100%);
background: -o-linear-gradient(top,  #429b9b 0%,#6abec0 16%,#429b9b 80%,#339b9c 100%);
background: -ms-linear-gradient(top,  #429b9b 0%,#6abec0 16%,#429b9b 80%,#339b9c 100%);
background: linear-gradient(to bottom,  #429b9b 0%,#6abec0 16%,#429b9b 80%,#339b9c 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#429b9b', endColorstr='#339b9c',GradientType=0 );
}
#navcontainer ul{margin: 0;padding: 10;list-style-type: none;text-transform:uppercase;text-weight:bold;font-size:12px;line-height: 38px;}
#navcontainer ul li { display: inline; }
#navcontainer ul li a{text-decoration: none;padding: .6em 1em;color: #333;/*background-color: #036;*/}
#navcontainer ul li a:hover{color: #fff;
background: #e5ba0b;
background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2U1YmEwYiIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEyJSIgc3RvcC1jb2xvcj0iI2Y2ZTAyOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjQ1JSIgc3RvcC1jb2xvcj0iI2U3YmUwYyIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNkOWE3MDgiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
background: -moz-linear-gradient(top,  #e5ba0b 0%, #f6e028 12%, #e7be0c 45%, #d9a708 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#e5ba0b), color-stop(12%,#f6e028), color-stop(45%,#e7be0c), color-stop(100%,#d9a708));
background: -webkit-linear-gradient(top,  #e5ba0b 0%,#f6e028 12%,#e7be0c 45%,#d9a708 100%);
background: -o-linear-gradient(top,  #e5ba0b 0%,#f6e028 12%,#e7be0c 45%,#d9a708 100%);
background: -ms-linear-gradient(top,  #e5ba0b 0%,#f6e028 12%,#e7be0c 45%,#d9a708 100%);
background: linear-gradient(to bottom,  #e5ba0b 0%,#f6e028 12%,#e7be0c 45%,#d9a708 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e5ba0b', endColorstr='#d9a708',GradientType=0 );
}

#navcontainer ul li a.Tabactive{color: #fff;
background: #e5ba0b;
background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2U1YmEwYiIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEyJSIgc3RvcC1jb2xvcj0iI2Y2ZTAyOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjQ1JSIgc3RvcC1jb2xvcj0iI2U3YmUwYyIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNkOWE3MDgiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
background: -moz-linear-gradient(top,  #e5ba0b 0%, #f6e028 12%, #e7be0c 45%, #d9a708 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#e5ba0b), color-stop(12%,#f6e028), color-stop(45%,#e7be0c), color-stop(100%,#d9a708));
background: -webkit-linear-gradient(top,  #e5ba0b 0%,#f6e028 12%,#e7be0c 45%,#d9a708 100%);
background: -o-linear-gradient(top,  #e5ba0b 0%,#f6e028 12%,#e7be0c 45%,#d9a708 100%);
background: -ms-linear-gradient(top,  #e5ba0b 0%,#f6e028 12%,#e7be0c 45%,#d9a708 100%);
background: linear-gradient(to bottom,  #e5ba0b 0%,#f6e028 12%,#e7be0c 45%,#d9a708 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e5ba0b', endColorstr='#d9a708',GradientType=0 );
}
  .hrwidth {

    width: 108%;
	 color: #000;
	background-color: #000;
	height: 3px !important;
	 margin: 0 0 0 -8px;

	}
	#casteTable th{color:#5FB9B9;background-color: #DDDDDD;border:1px solid black !important;}
#casteTable td
{
border:1px solid black !important;
}

</style>

<!-- harish end -->
<style>
div:focus {outline:none}
#seats{
height:0px;
}
#votes{
height:0px;
}

#seatsandvotes{
height:0px;
}

#allianceChk{
height:0px;
}
.buttonClass {
    background-color: #F52B43;
    border-radius: 6px 6px 6px 6px;
    color: #FFFFFF;
    cursor: pointer;
    font-weight: bold;
    padding: 4px;
}
.buttonStyle {
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
}
.main-mbg {
   background: #6d839a;
background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzZkODM5YSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjExJSIgc3RvcC1jb2xvcj0iI2IyYzBjYiIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjcyJSIgc3RvcC1jb2xvcj0iIzgyOTZhZSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiM2NTdkOTciIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
background: -moz-linear-gradient(top,  #6d839a 0%, #b2c0cb 11%, #8296ae 72%, #657d97 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#6d839a), color-stop(11%,#b2c0cb), color-stop(72%,#8296ae), color-stop(100%,#657d97));
background: -webkit-linear-gradient(top,  #6d839a 0%,#b2c0cb 11%,#8296ae 72%,#657d97 100%);
background: -o-linear-gradient(top,  #6d839a 0%,#b2c0cb 11%,#8296ae 72%,#657d97 100%);
background: -ms-linear-gradient(top,  #6d839a 0%,#b2c0cb 11%,#8296ae 72%,#657d97 100%);
background: linear-gradient(to bottom,  #6d839a 0%,#b2c0cb 11%,#8296ae 72%,#657d97 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#6d839a', endColorstr='#657d97',GradientType=0 );
     color: #FFFFFF;
   font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 18px;
    padding-left: 13px;
	padding-bottom:9px;
	  width: 220px;
   <!-- text-align: left;-->
   <!-- text-transform: uppercase;-->
    width: 220px;
}

table.searchresultsTable th {
    background-color: #C4DEFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
}
table.searchresultsTable td {
    background-color: #FFFFFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
}
table.searchresultsTable, table.searchresultsTable * td, table.searchresultsTable * th {
    -moz-border-bottom-colors: none;
    -moz-border-image: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-style: solid;
    border-width: 1px;
}
.selectBoxWidth {
    width: 200px;
}
.span2{
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
	font-weight: bold;
	margin-top: 8px;	
}
select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 200px;
}

select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input,#fromDate,#toDate {
    border-radius: 4px 4px 4px 4px;
	color: #000000;
    display: inline-block;
    font-size: 13px;
    line-height: 18px;
}
input, button, select, textarea {
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
}

.customclass{left:50% !important;margin-left:-70px;border:none !important;background:none !important}
.customclass1{border:none !important;background:none !important}
.fullheight{
   min-height:100%;
}
#indiaBannerId,#andhraImageDiv,.parliamentCls{display:none;}
.ui-widget-header{
 background-color: #FFFFFF !important;
 background-image: none !important;
 border-bottom-style: none !important;
}
.ui-button-text{
	display:none !important;
}
.tableClass1  table {border: 3px solid #B6D9E9}
.tableClass1  thead th,.tableClass1  thead tr,.tableClass1  tbody tr,.tableClass1  tbody td {
	border: 2px solid #B6D9E9;
	padding:5px;
	font-weight:bold;
	font-size:13px;
}

.parlResultTable{background:#ffffff;}
	.parlResultTable tbody tr:nth-child(even){
		background:#F7FAFD;
	}

	.exitPolls tbody tr:nth-child(even){
		background:#F7FAFD;
	}
	.exitPolls tr{font-family: Tahoma;font-size: 12px;color:black;}
	.prevResults a{margin:10px;}

.thBorder{
	border: 2px solid #B6D9E9
}
.headingClass{
	background-color:#8497AD;
	color:#fff;
	font-size:15px
}


<!--sravanthi code start-->
.chart-gauge1 {
width: 360px;
height: 200px;
text-align:center;
}

.chart-gauge {
width: 360px;
margin: auto;
height: 200px;
text-align:center;
}

.chart-color1 {
fill: #FF3333;
}

.chart-color2 {
fill: #FFCCCC;
}

.chart-color3 {
fill: #CCFFCC;
}
.chart-color4 {
fill: #33FF33;
}
.chart-color5 {
fill: #59A2BE;
}
.needle ,.needle-center{
fill: #9f8868;
}


.d3-slider-handle1 {position: absolute;width: 1.2em;height: 1.2em;border: 1px solid #FFCC00;border-radius: 4px;background: #FFCC99;background: linear-gradient(to bottom, #FFCC99 0%, #FFD2A6 100%);z-index: 3;}
.d3-slider-handle1:hover {border: 1px solid #F2C100;}
.d3-slider-horizontal .d3-slider-handle1 {top: -.3em;margin-left: -.6em;}
.d3-slider-vertical .d3-slider-handle1 {left: -.25em;margin-left: 0;margin-bottom: -.6em;}


.d3-slider {position: relative;font-family: Verdana,Arial,sans-serif;font-size: 1.1em;border: 1px solid #aaaaaa;z-index: 2;}
.d3-slider-horizontal {height: .8em;}
.d3-slider-vertical {width: .8em;height: 100px;}
.d3-slider-handle {position: absolute;width: 1.2em;height: 1.2em;border: 1px solid #FFCC00;border-radius: 4px;background: #FFCC99;background: linear-gradient(to bottom, #FFCC99 0%, #FFD2A6 100%);z-index: 3;}
.d3-slider-handle:hover {border: 1px solid #F2C100;}
.d3-slider-horizontal .d3-slider-handle {top: -.3em;margin-left: -.6em;}
.d3-slider-axis {position: relative;z-index: 1;}
.d3-slider-axis-bottom {top: .8em;}
.d3-slider-axis-right {left: .8em;}
.d3-slider-axis path {stroke-width: 0;fill: none;}
.d3-slider-axis line {fill: none;stroke: #aaa;shape-rendering: crispEdges;}
.d3-slider-axis text {font-size: 11px;}
.d3-slider-vertical .d3-slider-handle {left: -.25em;margin-left: 0;margin-bottom: -.6em;}
.div_sld{width:300px;margin:10px 0 0 50px;}
.div_sld1{width:300px;margin:10px 0 0 50px;}
.div_upeffect{width:400px;}
.map_wrapper{width:100%;padding:5px;max-width:700px;}
@media only screen and (max-width:767px){
.map_wrapper{width:99%;padding:0px;}
#mapswidget{width:98%;}
.div_upeffect{width:100%;}

.div_sld{width:80%;margin:10px auto;}
.div_sld1{width:80%;margin:10px auto;}
}
@media only screen and (max-width:340px){
.div_upeffect center{font-size:12px;}
}
.ht10{display:block;font-size:1px;height:10px;line-height:10px}
#slider3{width:333px;margin-bottom: 40px;}
.div_sld{width:333px;margin:auto;}
.div_sld1{width:333px;margin:auto;}

#unemp_chart_img{
  margin-top: 36px;
}
.div_sld_img{
    margin-bottom: 16px;
    margin-top: 25px;
}
#unemp_chart1_img{
    margin-top: 15px;
}
.div_sld1_img{
    margin-bottom: 15px;
}
<!--sravanthi code end-->



select {
background-color: #FFFFFF;
border: 1px solid #CCCCCC;
width: 150px;
}

select,textarea,input[type="text"],input[type="password"],input[type="datetime"],input[type="datetime-local"],input[type="date"],input[type="month"],input[type="time"],input[type="week"],input[type="number"],input[type="email"],input[type="url"],input[type="search"],input[type="tel"],input[type="color"],.uneditable-input,#fromDate,#toDate
{
border-radius: 4px 4px 4px 4px;
color: #000000;
display: inline-block;
font-size: 13px;
line-height: 18px;
padding: 4px;
}
.hero-unit{padding:22px;color:black;font-size:15px;margin-bottom: 5px;margin-top: 10px;}
   .hero-unit {
    color: black;
    font-size: 15px;
    margin-bottom: 5px;
    margin-top: 10px;
    padding: 22px;
	}
	label {
    display: inline-block;
	}
	label {
		margin-bottom: 5px;
	}
	label, input, button, select, textarea {
		font-size: 13px;
		font-weight: normal;
		line-height: 18px;
	}
	

	.leaflet-popup-content-wrapper {
    border-bottom-left-radius: 0px !important;
    border-bottom-right-radius: 0px !important;
    border-top-left-radius: 0px !important;
    border-top-right-radius: 0px !important;
    padding-bottom: 1px;
    padding-left: 1px;
    padding-right: 1px;
    padding-top: 1px;
    text-align: left;
	
	
	font-size: 30px !important;
	padding-top: 8px !important;
	padding-right: 8px !important;
	
	}
	.leaflet-popup-close-button{ color:red !important;}
	.leaflet-popup-close-button:hover{color:#00f !important;}
	
	.div_sld_img{
	  margin-top:60px;
	}
	.tableClass2  table {border: 3px solid #B6D9E9}
	.tableClass2  thead th,.tableClass2  thead tr,.tableClass2  tbody tr,.tableClass2  tbody td {
		border: 2px solid #B6D9E9;
		font-weight:bold;
		font-size:13px;
		border-left:2px solid #c3c3c3;
	}
	
</style>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
</script>
<script src="js/apac.js"></script> 
<script src="js/tgac.js"></script>
<script src="js/tgpc.js"></script>
<script src="js/appc.js"></script>
<script src="js/pc.js"></script>
<!--<script src="js/leaflet-lable.js"></script>-->
<script src="js/leaflet.js"></script>
<script src="js/leaflet-google.js"></script>
<!--<script src="http://cdn.leafletjs.com/leaflet-0.6.4/leaflet.js"></script>-->
<!--<script src="http://maps.google.com/maps/api/js?v=3.2&sensor=false"></script>-->
<script src="js/GOOGLE.js"></script>
<script src="js/Permalink.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="styles/politico.css">
<link rel="stylesheet" type="text/css" href="styles/leaflet.css">
<link rel="stylesheet" type="text/css" href="styles/leaflet-lable.css">
<script src='https://api.tiles.mapbox.com/mapbox.js/v1.6.2/mapbox.js'></script>
<link href='https://api.tiles.mapbox.com/mapbox.js/v1.6.2/mapbox.css' rel='stylesheet' />
</head>
<body>
<script type="text/javascript">
var sliderModiWave = 0;
var sliderCbnWave = 90;

var telanganaTabClck = false;
var seemAndraTabClck = false;
var indiaTabClck = false;
var stateAnalysTabClck = false;
var distAnalysTabClck = false;
var cbnEffTabClck = false;
var modiEffTabClck = false;

var telanganaTabCount = 0;
var seemAndraTabCount = 0;
var indiaTabCount = 0;
var stateAnalysTabCount = 0;
var distAnalysTabCount = 0;
var cbnEffTabCount = 0;
var modiEffTabCount = 0;
var casteAnalysisDivFlag = false;
$('document').ready(function(){

		$('.percentTypeClass').change(function(){
			$('.prcntClass1,.prcntClass,.cntClass').hide();
			if(this.value =="Both")
			{
				$('.prcntClass1,.prcntClass,.cntClass').show();

			}else if(this.value =="Seats")
			{
				$('.cntClass').show();

			}else if(this.value =="Votes")
			{
				$('.prcntClass1').show();

			}
		});
//sravanthi start
		$('#modiDiv').hide();
		$("#unemp_chart").hide();
		$("#unemp_chart_img").hide();
		$("#unemp_chart1").hide();
		$("#unemp_chart1_img").hide();
		$(".div_sld").hide();
		$(".div_sld1").hide();
		$(".div_sld_img").hide();
		$(".div_sld1_img").hide();
//sravanthi end
		//seemandraDistrict();
		//seemandraRegion();
		
	//	$('#results1Div,#subTitlesDiv').hide();
	 //$('#scopeId').trigger('change');
	 //$('#locaionsId1').multiselect({ noneSelectedText:"Select"});
	 $('.reportType').change(function(){
		// $('#percentDiv').hide();
		 $('#test,#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId,#marginAnalysis1,#constituencyResultsDiv').html('');
	 });

	 $('#scopeId').change(function(){
		// console.log(this);
		 $('#rgntxt').text("Select "+$('#scopeId  :selected').text());
		 
		 checkForPartyShareReport();
	 });
	 
	 $('#legend').show();
	 $('#areaSelectionDiv').hide();
	 $('#stateSelectDiv').hide();
	 $('#submitButtionDiv').hide();
	 //getElectionResultForAssemblyPrevious(1,"first",1,2);
	 //getElectionResultForParlimentPresent(1,"second",2,2);
	 $('#scopeId').change(function(){
	 
	 $('#rgntxt').text("Select "+$('#scopeId :selected').text());
	 });
		$('#results1Div').html('');
		
		$('#subTitlesDiv').html('');
		$('#andhraImageDiv').hide();
		$('#telanganaImageDiv').show();
		$('#legend').css("margin-top","-85px");	
		
		getRegionWiseResults("Telangana");
});
</script>
<script type="text/javascript">

var showConst=false;
	
	$('#stateButton').live('click',function(){
		
	if(showConst) {
			
			$(this).html('Show State Wise Report<i class="icon-chevron-down"></i>'); 
			$('#telanganaMainDiv').hide();
			$('#andhraMainDiv').hide();
			showConst=false;
		}
		else {
			
			$(this).html('Hide State Wise Report<i class="icon-chevron-up"></i>');
			$('#telanganaMainDiv').css("display","block");
			$('#andhraMainDiv').css("display","block");
			$("#stateAjaxImg").css("display","inline-block");
			getAndhraPartyResult('state');
			getAndhraPartyResultForMuncipal('state');
			showConst=true;
		}
	});




 function getTelanganaPartyResult(type)
	{
		$("#telanganaDiv").html('');
		
		var constituencyDetails={electionId:38,type:type,region:"telangana",taskToDo:"telangana"
		,alliance:false};
	$.ajax({
          type:'POST',
          url: 'getTelanganaPartyResultAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  //$("#ajaxLoad").css("display","none");
			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				buildResultForPartyResult(result,constituencyDetails,'telanganaDiv');
			}
	   });
	   
	
	}


	function getAndhraPartyResult(type)
	{
		$("#telanganaDiv").html('');
		$("#andhraDiv").html('');
		$("#telanganaMuncipaDiv").html('');
		$("#andhraMuncipalDiv").html('');
		
		$("#regionHead").html('');
		$("#regionHead1").html('');
		$("#telanganaMainDiv").css("display","none");
		var constituencyDetails={electionId:38,type:type,region:"andhra",taskToDo:"andhra"
		,alliance:false};
	$.ajax({
          type:'POST',
          url: 'getAndhraPartyResultAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			 
			if(result != null){
				
				buildResultForPartyResult(result,constituencyDetails,'andhraDiv');
			}
	   });
	   
	
	}


function getTelanganaPartyResultForMuncipal(type)
	{
		$("#telanganaMuncipaDiv").html('');
		var constituencyDetails={electionId:40,type:type,region:"telangana",taskToDo:"telanganamuncipal",alliance:false};
	$.ajax({
          type:'POST',
          url: 'getTelanganaPartyResultForMuncipalAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  //$("#ajaxLoad").css("display","none");
			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				buildResultForPartyResult(result,constituencyDetails,'telanganaMuncipaDiv');
			}
	   });
	   
	
	}

function getAndhraPartyResultForMuncipal(type)
	{
		$("#telanganaDiv").html('');
		$("#andhraDiv").html('');
		$("#telanganaMuncipaDiv").html('');
		$("#andhraMuncipalDiv").html('');
		$("#regionHead").html('');
		$("#regionHead1").html('');
		$("#telanganaMainDiv").css("display","none");
		var constituencyDetails={electionId:40,type:type,region:"andhra",taskToDo:"andhramuncipal"
		,alliance:false};
	$.ajax({
          type:'POST',
          url: 'getAndhraPartyResultForMuncipal.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			$("#stateAjaxImg").css("display","none");

			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				buildResultForPartyResult(result,constituencyDetails,'andhraMuncipalDiv');
				$("#stateAjaxImg").css("display","none");
				$("#districtAjaxImg").css("display","none");
			}
	   });
	   
	
	}

function buildResultForPartyResult(result,jObj,DivEle)
	{
	var str='';
	if(jObj.type == "district")
		{
	$("#telanganaMainDiv").css("display","block");
		}
	$("#andhraMainDiv").css("display","block");
	if(jObj.type == "district" && jObj.region == "andhra" && jObj.taskToDo != "andhramuncipal")
	$("#regionHead").html("ANDHRA REGION");
	else if(jObj.type == "district" && jObj.region == "telangana" && jObj.taskToDo != "telanganamuncipal")
		$("#regionHead1").html("TELANGANA REGION");
	
	if(jObj.taskToDo == "andhramuncipal" || jObj.taskToDo == "telanganamuncipal")
	str+='<h5>'+result[0].year+' - Muncipal Election Results</h5>';
	else
	str+='<h5>'+result[0].year+' - Assembly Election Results</h5>';
	str+='<div class="tableClass1">';
	str+='<table class="partyResultTable" style="width:40% !important;">';
	str+='<thead>';
	str+='<tr>';
	if(jObj.type == 'district')
	str+='<th>District</th>';
	else
	str+='<th>State</th>';
	for(var i in result[0].subList)
		{
	str+='<th>'+result[0].subList[i].partyName+' </th>';
		
		}
	str+='</tr>';
	str+='</thead>';
	for(var i in result)
		{
	
	str+='<tbody>';
	str+='<tr>';
	str+='<td>'+result[i].locationName+'</td>';
	for(var j in result[i].subList)
			{
	str+='<td>'+result[i].subList[j].percent+'</td>';
	
			}
	str+='</tr>';
	str+='</tbody>';
	
		}
	str+='</table>';
	str+='</div>';
	$("#"+DivEle).html(str);
	
	}





	
function testIt()
{
	$.ajax({
          type:'POST',
          url: 'getElectionResultsSummary.action',
          dataType: 'json',
          data: {},

          success: function(result){ 
			   buildReservationCategoryResult(result);
			   buildResultByConstituencyType(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });

}

function buildReservationCategoryResult(result)
{
	var str ='';

	str+='<table border="1">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th rowspan="3"></th>';
	   str+='<th colspan="12">SC</th>';
	   str+='<th colspan="12">ST</th>';
	   str+='<th colspan="12">GENERAL</th>';
	   //str+='<th colspan="6">RURAL</th>';
	   //str+='<th colspan="6">URBAN</th>';
	   //str+='<th colspan="6">RURAL-URBAN</th>';
	  str+='</tr>';

      str+='<tr>';
	   $.each(result.subList[0].reservationDetails,function(index,value){
		   $.each(value.partiesDetails,function(index1,value1){
			     str+='<th colspan="2">'+value1.name+'</th>';			 
	       });
	   });
	  str+='</tr>';

	   str+='<tr>';
	   $.each(result.subList[0].reservationDetails,function(index,value){
		   $.each(value.partiesDetails,function(index1,value1){
			     str+='<th>W</th>';	
				 str+='<th>L</th>';	
	       });
	   });
	  str+='</tr>';

	 str+='</thead>';
	 str+='<tbody>';
    
	 $.each(result.subList,function(index,value){
		 str+='<tr>';
		 str+='<td>'+value.name+'</td>';
		  $.each(value.reservationDetails,function(index1,value1){
		     $.each(value1.partiesDetails,function(index2,value2){
			     str+='<th><a href="javascript:{getConstituenciesDetailsForSubReport(\''+value1.name+'\','+value2.id+','+value.id+')}">'+value2.count+'</a></th>';
				  str+='<th><a href="javascript:{getConstituenciesDetailsForSubReport(\''+value1.name+'\','+value2.id+','+value.id+')}">0</a></th>';
	       });
	     });
		 str+='</tr>';
	 });

	 str+='</tbody>';
	str+='</table>';

	$('#test').html(str);

}

function buildResultByConstituencyType(result)
{
	
	var str ='';

	str+='<table border="1">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th rowspan="2"></th>';
	  // str+='<th colspan="6">SC</th>';
	  // str+='<th colspan="6">ST</th>';
	  // str+='<th colspan="6">GENERAL</th>';
	   str+='<th colspan="6">RURAL</th>';
	   str+='<th colspan="6">URBAN</th>';
	   str+='<th colspan="6">RURAL-URBAN</th>';
	  str+='</tr>';

      str+='<tr>';
	   $.each(result.subList[0].constituencyWiseDetails,function(index,value){
		   $.each(value.partiesDetails,function(index1,value1){
			     str+='<th>'+value1.name+'</th>';			 
	       });
	   });
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';
    
	 $.each(result.subList,function(index,value){
		 str+='<tr>';
		 str+='<td>'+value.name+'</td>';
		  $.each(value.constituencyWiseDetails,function(index1,value1){
		     $.each(value1.partiesDetails,function(index2,value2){
			     str+='<th><a href="javascript:{getConstituenciesDetailsForSubReport(\''+value1.name+'\','+value2.id+','+value.id+')}">'+value2.count+'</a></th>';			 
	       });
	     });
		 str+='</tr>';
	 });

	 str+='</tbody>';
	str+='</table>';

	$('#matridLeadId').html(str);

}

function getConstituenciesDetailsForSubReport(type,partyId,locationId)
{
	$.ajax({
          type:'POST',
          url: 'getConstituenciesDetailsForSubReport.action',
          dataType: 'json',
          data: {type:type,partyId:partyId,locationId:locationId,locationType:2},

          success: function(result){ 

			   console.log(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

var locationDtls={
	task:''	 
};
function getLocationDetailsForSelectedScope()
{
	
	$('#subReportId').attr('disabled',false); 
    if($('#scopeId').val() == 5)
	{
		$('#subReportId').attr('disabled',true);
	}
    
	locationDtls.task =$('#scopeId :selected').text();

	$.ajax({
          type:'POST',
          url: 'getElectionResultsLocations.action',
          dataType: 'json',
          data: {task:JSON.stringify(locationDtls)},

          success: function(result){ 
			   buildLocationDetails(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}
function buildLocationDetails(result)
{
	$('#locaionsId1').find('option').remove();

	$('#locaionsId1').append('<option value="0">ALL</option>');
	$.each(result,function(index,value){
		$('#locaionsId1').append('<option value="'+value.id+'">'+value.name+'</option>');
	});

	//$("#").multiselect('refresh'); 


}

function showSelectedReport()
{
	//$('#percentDiv').hide();

	$('#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId,#errorDiv,#test,#marginAnalysis1,#constituencyResultsDiv').html('');
 

	if($('#locaionsId1').val() == null)
	{
        $('#errorDiv').html('Please select location(s)');
		return;
	}
      
	   $('#ajaxImage').show();

    if($('#scopeId').val() == 5)
	{
		var val= $('input[name="inrReport"]:checked').val();
		if(val == "Party Report"){
			getParyWiseVotesShare();
		}else{
			getConstituencyWiseResults();
		}

	}
	else
	{	  

		if($('input:radio[name=report]:checked').val() == "Matrix Report")
		{
			   getMatrixReport();
			   getMarginsCountOfParties();
			 //  $('#percentDiv').show();
			   $('#bothId').attr('checked',true);
		}
		else
		{
				getSubReportForElectionResultByConstituencyType();
				getSubReportForElectionResultByConstituencyReservation();
				//$('#percentDiv').show();
		}
	}


}
var matrixReportDtls={
	electionId:'',
    scopeId:'',
    locationIds:[]
		
};

function getMatrixReport()
{
	matrixReportDtls.electionId = $('#electionId').val();
	matrixReportDtls.scopeId = $('#scopeId').val();

	if($('#locaionsId1').val() == 0)
	{
         $('#locaionsId1 option').each(function(index,value){
			 matrixReportDtls.locationIds.push(this.value);
		 })
	}else
	 matrixReportDtls.locationIds = $('#locaionsId1').val();

	$('#summaryDiv').addClass('offset1');

	$.ajax({
          type:'POST',
          url: 'getMatrixReportForElectionResult.action',
          dataType: 'json',
          data: {task:JSON.stringify(matrixReportDtls)},

          success: function(result){ 
			  	   $('#ajaxImage').hide();

				   if(result == null || result.length == 0)
			       {
					   $('#errorDiv').html('<h5>No Data Available..</h5>');
                     return;
				   }
			  $('#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId').html('');
			    buildMatrixReportSummaryDetails(result,"Won");
				//buildMatrixReportSummaryDetails(result,"Lead");
			    buildMatrixReport(result,"Won");
			   // buildMatrixReport(result,"Lead");
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function  buildMatrixReport(result,status)
{
	var str ='';

str+='<div class="tableClass1">';
    str+='<label style="margin-top: 10px; margin-bottom: 10px;" class="headingClass"> '+$('#scopeId :selected').text()+'&nbsp; Wise Won Summary</label>';
	str+='<table width="80%" class="" style="clear:both;" style="margin-top:25px;">';
	 str+='<thead>';
	  str+='<tr>';
	 //  str+='<th class="thBorder">'+status+'</th>';
	   str+='<th class="thBorder"></th>';
	    $.each(result[0].partiesDetails,function(index,value){
           str+='<th class="thBorder">'+value.name+'</th>';
		});
	  str+='</tr>';
	 str+='</thead>';

	 str+='<tbody>';
	
	  
	  $.each(result,function(index,value){
		   str+='<tr>';
		    str+='<td>'+value.name+'</td>';
            $.each(value.partiesDetails,function(index1,value1){
				if(status == "Won")
                 //str+='<td><a href="javascript:{getConstituencyDetails('+value.id+','+value1.id+',1);}">'+value1.winCount+'</a></td>';
				/*if(value1.winCount != 0 && value1.percent != null)
					 str+='<td><span class="cntClass">'+value1.winCount+'</span><span class="prcntClass">(</span><span class="prcntClass1">'+value1.percent+'</span><span class="prcntClass">)</span></td>';
					else*/
					 str+='<td>'+value1.winCount+'</td>';
				else
				 //str+='<td><a href="javascript:{getConstituencyDetails('+value.id+','+value1.id+',0);}">'+value1.leadCount+'</td>';
				str+='<td>'+value1.leadCount+'</td>';
		    });
			 str+='</tr>';
	  });
	  
	
 	 str+='</tbody>';
	str+='</table>';
	str+='</div>';

  if(status == "Won")
	$('#test').html(str);
  else
	 $('#matridLeadId').html(str);
}

function buildMatrixReportSummaryDetails(result,status)
{
	if(result == null || result.length  == 0)
		return;
   var str ='';
   if(status == "Won")
	   str+='<label  style="margin-top: 10px; margin-bottom: 10px;padding:2px;" class="headingClass">Won Summary</label>';
   else
	   str+='<label  style="margin-top: 10px; margin-bottom: 10px;padding:2px;" class="headingClass">Lead Summary</label>';
	str+='<div class="tableClass1">';
	str+='<table width="80%" class="" style="clear:both;">';
	str+='<thead>';
	str+='<tr>';

	$.each(result[0].summaryDetails,function(index,value){
		str+='<th style="border:1px solid #B6D9E9;" class="">'+value.name+'</th>';
		if(status == "Won")
		 str+='<th style="border:1px solid #B6D9E9;" class="">'+value.winTotalCount+'</th>';
		else
		 str+='<th style="border:1px solid #B6D9E9;" class="">'+value.leadTotalCount+'</th>';

	});

	str+='</tr>';
	str+='</thead>';
	str+='</table>';
	str+='</div>';
	
	if(status == "Won")
		$('#matrixWonSummaryId').html(str);
	else
		$('#matrixLeadSummaryId').html(str);

}

var subReportDtls={
	electionId:'',
    scopeId:'',
    locationIds:[]
		
};


function getSubReportForElectionResultByConstituencyType()
{
	subReportDtls.electionId = $('#electionId').val();
	subReportDtls.scopeId = $('#scopeId').val();

	if($('#locaionsId1').val() == 0)
	{
         $('#locaionsId1 option').each(function(index,value){
			 subReportDtls.locationIds.push(this.value);
		 })
	}else
	subReportDtls.locationIds = $('#locaionsId1').val();

	$.ajax({
          type:'POST',
          url: 'getSubReportForElectionResultByConstituencyType.action',
          dataType: 'json',
          data: {task:JSON.stringify(subReportDtls)},

          success: function(result){ 
			   $('#ajaxImage').hide();
			buildSubReportByConstituencyType(result,"constnType");
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function getSubReportForElectionResultByConstituencyReservation()
{
	subReportDtls.electionId = $('#electionId').val();
	subReportDtls.scopeId = $('#scopeId').val();


	if($('#locaionsId1').val() == 0)
	{
         $('#locaionsId1 option').each(function(index,value){
			 subReportDtls.locationIds.push(this.value);
		 })
	}else
	 subReportDtls.locationIds = $('#locaionsId1').val();

	$.ajax({
          type:'POST',
          url: 'getSubReportForElectionResultByConstituencyReservationType.action',
          dataType: 'json',
          data: {task:JSON.stringify(subReportDtls)},

          success: function(result){
			   $('#ajaxImage').hide();
			buildSubReportByConstituencyType(result,"reservationType");
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function buildSubReportByConstituencyType(result,type)
{

	 if(result == null || result.length == 0)
	 {	
		 $('#errorDiv').html('<h5>No Data Available..</h5>');
		 return;
	 }
	$('#summaryDiv').removeClass('offset1');

	var str ='';
	str+='<div class="tableClass1" style="margin-top:30px;">';

	//str+='<label><b>NOTE:</b>W  - Won , L - Lead</label><br>';
	  if(type == "reservationType")
		   str+='<label style="margin-top: 10px; margin-bottom: 10px;" class="headingClass">SC,ST,General Constituencies Analysis</label>';
	  else
	  str+='<label style="margin-top: 10px; margin-bottom: 10px;" class="headingClass">Rural,Urban,Rural-Urban Analysis</label>';

	  
          str+='<div class="tableClass1">';
		   str+='<table style="margin-bottom:13px;">';
			  str+='<thead>';
			   str+='<tr>';
				$.each(result[0].subList,function(index,value){

					if(value.name == "")
					   str+='<th>GENERAL</th>';
					else
					   str+='<th>'+value.name+'</th>';
					 str+='<th>'+value.count+'</th>';
				});
				
			   str+='</tr>';
			  str+='</thead>';
			str+='</table>';
			str+='</div>';

	str+='<table width="80%" class="" style="clear:both;" style="margin-top:25px;">';
	 str+='<thead>';
	  str+='<tr>';
		  str+='<th rowspan="3"></th>';
		  $.each(result[0].reservationDetails,function(index,value){
			 /* var spanCnt = value.partiesDetails.length * 2;
			  if(value.name == "")
			   str+='<th colspan="'+spanCnt+'" class="thBorder">GENERAL</th>'
		      else
			   str+='<th colspan="'+spanCnt+'" class="thBorder">'+value.name+'</th>'*/

			  if(value.name == "")
			   str+='<th colspan="'+value.partiesDetails.length+'" class="thBorder">GENERAL</th>';
		      else
			   str+='<th colspan="'+value.partiesDetails.length+'" class="thBorder">'+value.name+'</th>';
		  });
	  str+='</tr>';
	  str+='<tr>';
		  $.each(result[0].reservationDetails,function(index,value){
			   $.each(value.partiesDetails,function(index1,value1){
				  // str+='<th colspan="2" style="border:1px solid #B6D9E9;">'+value1.name+'</th>';
				   str+='<th style="border:1px solid #B6D9E9;">'+value1.name+'</th>'
			   });
		  });
	  str+='</tr>';

	  str+='<tr>';
		  $.each(result[0].reservationDetails,function(index,value){
			   $.each(value.partiesDetails,function(index1,value1){
				  // str+='<th style="border:1px solid #B6D9E9;">W</th>'
   				   //str+='<th style="border:1px solid #B6D9E9;">L</th>'
			   });
		  });
	  str+='</tr>';

	  str+='</thead>';
	 str+='<tbody>';

     $.each(result,function(index,value){
		  str+='<tr>';
		  
 		   str+='<td>'+value.name+'</td>';

		   $.each(value.reservationDetails,function(index1,value1){
			    $.each(value1.partiesDetails,function(index2,value2){
					/*if(value2.winCount != 0 && value2.percent != null)
					 str+='<td><span class="cntClass">'+value2.winCount+'</span><span class="prcntClass">(</span><span class="prcntClass1">'+value2.percent+'</span><span class="prcntClass">)</span></td>';
					else*/
					 str+='<td>'+value2.winCount+'</td>';
					//str+='<td>'+value2.leadCount+'</td>';
				 });
		   });

		  str+='</tr>';
	 });

	 str+='</tbody>';
	str+='</table>';
	str+='</div>';


  if(type == "reservationType")
	$('#matrixLeadSummaryId').html(str);
  else
	$('#matrixWonSummaryId').html(str);

}
var constnDtls = {
	locationId:'',
	scopeId:'',
	partyId:'',
	statusId:''
};

function getConstituencyDetails(locationId,partyId,statusId)
{
	constnDtls.locationId = locationId;	
	constnDtls.partyId = partyId;
	constnDtls.statusId = statusId;
	constnDtls.scopeId = $('#scopeId').val();
	$.ajax({
          type:'POST',
          url: 'getElectionResultsSummary.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			   buildReservationCategoryResult(result);
			   buildResultByConstituencyType(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}
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
	 if($('input:radio[name=report]:checked').val() == "Matrix Report")
   {
	if($('#constituencyResultsDiv').val() == 5)
	 tableToExcel('reportDiv', 'Assembly Wise Results');
	else
	  tableToExcel('reportDiv', 'Election Results');
   }
  else
	   tableToExcel('summaryDiv', 'Election Results');
}

function clearFields()
{
 	$('#test,#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId,#constituencyResultsDiv').html('');
}
</script>
<script type="text/javascript">
var details={
	electionId:'',
	constituencyIds:[] 	
};
function getConstituencyWiseResults()
	 {
	$('#ajaxImage').hide();
		details.electionId = $('#electionId').val();

		if($('#locaionsId1').val() == 0)
		{
         $('#locaionsId1 option').each(function(index,value){
			 details.constituencyIds.push(this.value);
		 })
		}else
		details.constituencyIds = $('#locaionsId1').val();

$('#ajaxImage').show();
		$.ajax({
	          type:'POST',
	          url: 'getdashBoardConstituencyWiseResults.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(details)},
	     	  }).done(function(result){ 
				  buildConstituencyResults(result);
				  $('#ajaxImage').hide();
		   });
	 }
	 function buildConstituencyResults(result)
	 {
		 
	   $('#constituencyResultsDiv').html('');
	   $('#constituencyResultsDiv').show();
	   $('#constituencyResultsDiv').show();
	   if(result != null)
	   {		
		var str = '';
		//str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">CONSTITUENCY WISE LIVE RESULTS</h4>';
		str+='<div class="tableClass1 offset1">';
		str+='<table width="80%" class="" style="clear:both;" style="margin-top:25px;">';
		//str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">';
		str += '<tr>';
		str += '<th>P #</th>';
		str += '<th>P Name</th>';
		str += '<th>A #</th>';
		str += '<th>A Name</th>';
		str += '<th>First</th>';
		str += '<th>Second</th>';
		str += '<th>Lead By</th>';
		str += '</tr>';
		
		for(var i in result)
		{	
            str += '<tr>';		
			str += '<td>'+result[i].parliamentNo+'</td>';
			str += '<td>'+result[i].parliamentName+'</td>';  
			str += '<td>'+result[i].assemblyNo+'</td>';
			str += '<td>'+result[i].assemblyName+'</td>';	
			if(result[i].firstRankCandidateName != null)
			 str += '<td>'+result[i].firstRankCandidateName+'</td>';
			else
			 str += '<td>---</td>';
				
			 if(result[i].secondRankCandidateName != null)
			  str += '<td>'+result[i].secondRankCandidateName+'</td>';
			 else
			  str += '<td>---</td>';
				
			  if(result[i].leadBy != null)
			   str += '<td>'+result[i].leadBy+'</td>';
			  else
			   str += '<td>---</td>';
			str += '</tr>';
		}
		str += '</table>';
		str+='</div>';
		$('#constituencyResultsDiv').html(str);
	} 
}
</script>
<div align="center" style="margin-top:20px;">
<img src="images/MEnuBG.jpg" width="960" height="32" border="0" usemap="#Map" />
</div>
<div align="center" id="bannerDiv">
<img src="images/TDP_portal_Banner.jpg" width="960" height="32" border="0" usemap="#Maps22" />
</div>

<div align="center" id="indiaBannerId">
<img src="images/India-Page-Banner.png" width="960" height="32" border="0" />
<!--<img width="960" height="32" border="0" usemap="#Maps22" src="images/India-Page-Banner.png">-->
</div>

 <!--sravanthi code start-->
<center>
<div id="modiDiv" style="display:none;">
<img id="unemp_chart_img" src="images/results/bjpseatstally.jpg" />
<div class="chart-gauge" id="unemp_chart" style="display: none; width: 394px;"></div>
<img id="unemp_chart1_img" src="images/results/tdpseatstally.jpg" />
<div class="chart-gauge1" id="unemp_chart1" style="display: block; width: 394px;"></div>

<img class="div_sld_img" src="images/results/bjpeffect.jpg" />
<div class="div_sld" style="display: none;"><div id="slider3" style="z-index:0;" class="d3-slider d3-slider-horizontal"><a class="d3-slider-handle" xlink:href="#"></a><svg class="d3-slider-axis d3-slider-axis-bottom" style="left: -12px;" width="558" height="32"><g transform="translate(12,0)"><g class="tick" style="opacity: 1;" transform="translate(0,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">0%</text></g><g class="tick" style="opacity: 1;" transform="translate(66.75,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">1%</text></g><g class="tick" style="opacity: 1;" transform="translate(133.5,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">2%</text></g><g class="tick" style="opacity: 1;" transform="translate(200.25,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">3%</text></g><g class="tick" style="opacity: 1;" transform="translate(267,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">4%</text></g><g class="tick" style="opacity: 1;" transform="translate(333.75,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">5%</text></g><path class="domain" d="M0,6V0H534V6"/></g></svg></div>
</div>

<img class="div_sld1_img" src="images/results/tdpeffect.jpg" />
<div class="div_sld1" style="display: block;"><div id="slider3" style="width:375px;-20px; z-index:0;" class="d3-slider d3-slider-horizontal"><a class="d3-slider-handle1" xlink:href="#" style="left: 50%;"></a><svg class="d3-slider-axis d3-slider-axis-bottom" style="left: -12px;" width="558" height="32"><g transform="translate(12,0)"><g class="tick" style="opacity: 1;" transform="translate(0,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">0%</text></g><g class="tick" style="opacity: 1;" transform="translate(37.25,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">1%</text></g><g class="tick" style="opacity: 1;" transform="translate(74.5,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">2%</text></g><g class="tick" style="opacity: 1;" transform="translate(111.75,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">3%</text></g><g class="tick" style="opacity: 1;" transform="translate(149,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">4%</text></g><g class="tick" style="opacity: 1;" transform="translate(186.25,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">5%</text></g><g class="tick" style="opacity: 1;" transform="translate(223.5,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">6%</text></g><g class="tick" style="opacity: 1;" transform="translate(260.75,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">7%</text></g><g class="tick" style="opacity: 1;" transform="translate(298,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">8%</text></g><g class="tick" style="opacity: 1;" transform="translate(335.25,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">9%</text></g><g class="tick" style="opacity: 1;" transform="translate(372.5,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">10%</text></g><path class="domain" d="M0,6V0H298V6"/></g></svg></div>
</div>

</div>
</center>
 <!--sravanthi code end-->
<div id="weathermap4" style="padding-bottom: 15px;"> </div>
<div class="container" style="display:none;margin-bottom:10px;font-family: verdana; font-size: 14px; border: 1px solid rgb(204, 204, 204); padding: 0px 10px 10px;" id="mapDiv">


<!--<h4 style="padding: 10px; margin-top: 10px; border-radius: 5px; text-align: center; background: none repeat scroll 0% 0% rgb(73, 175, 205);">LIVE ELECTION RESULTS COMPARISON</h4> -->
<div id="andhraImageDiv" style="text-align:center;margin-top:10px;" style="display:none;"><img src="images/Interactive-Seemandhra-Live-Election-Results-Comparision.png"></div>
 
 <div id="telanganaImageDiv" style="text-align:center;margin-top:10px;" style="display:none;"><img src="images/Interactive-Telangana-Live-Election-Results-Comparision.png"></div>

<map name="Map" id="menuMap">
	<area shape="rect" coords="442,-5,503,31" href="javascript:{getRegionWiseResults('Telangana');}" title="Telangana"/>
	<area shape="rect" coords="504,-4,576,30" href="javascript:{getRegionWiseResults('Semandhra');}" title="Semandhra" />
	<area shape="rect" coords="577,-8,611,30" href="javascript:{getRegionWiseResults('India');}" title="India"/>
	<area shape="rect" coords="611,1,693,29" href="javascript:{getRegionWiseResults('StateAnalysis');}" title="State Analysis"/>
	<area shape="rect" coords="693,0,791,33" href="javascript:{getRegionWiseResults('DistrictAnalysis');}" title="District Analysis "/>
	<area shape="rect" coords="791,1,858,30" href="javascript:{getRegionWiseResults('CBNEffect');}" title="CBN Effect"/>
	<area shape="rect" coords="858,-4,929,29" href="javascript:{getRegionWiseResults('ModiEffect');}" title="Modi Effect"/>
</map>
  
<div id="stateSelectDiv" align="center" style="margin-bottom: 20px;" >
	<div class="span4 offset4" style="margin-top: 10px;">
	<b>Select State</b>
	<select id="stateId">
	<option value="0">Select State</option>
	<option value="1">SeeamAndhra</option>
	<option value="2">Telangana</option>
	</select>
	</div>
</div>
</br></br>
<div class="row-fluid " id="areaSelectionDiv">
	<div class="span6">
		<div class="form-inline">
	
		
		<b>From Level</b>
		<select id="levelId1" class="input-medium">
		<option value="0">Select Level</option>
		<option value="1">Assembly</option>
		<option value="2">Parliment</option>
		</select>
		
		
		
		<b>From Year</b>
		<select id="yearId1" class="input-small">
		<option value="0">Select Year</option>
		<option value="1">2009</option>
		<option value="2">2014</option>
		</select>
		</div>
	</div>
		
	<div class="span6">
		<div class="form-inline">
		<b>Select Level</b>
		<select id="levelId2" class="input-medium">
		<option value="0">Select Level</option>
		<option value="1">Assembly</option>
		<option value="2">Parliment</option>
		</select>
		
		
		
		<b>Select Year</b>
		<select id="yearId2" class="input-small">
		<option value="0">Select Year</option>
		<option value="1">2009</option>
		<option value="2">2014</option> 
		</select>
		
		</div>
		</div>
	</div>

<br/>
<div class="row-fluid " id="submitButtionDiv">
	<div class="span2 offset5">
	 <a class="btn btn-info btn-block " value="Submit" onClick="getElectionDetails();getElectionResultForTotalAssembly(1);" >Submit</a>	
	</div>
</div>
</br></br>
<div class="row-fluid" id="legend" style="display:none;margin-bottom:-20px;">
<img id="legendImg" src="./images/icons/Party-colors-banner.png" alt="Processing Image" />
</div> 

</br></br>

<div class="row-fluid">
	<img id="stateAjaxImg1" src="./images/icons/barloader.gif" alt="Processing Image" style=" display: none; margin-left: 360px;padding-bottom: 10px;"/>
	<img id="stateAjaxImg4" src="./images/icons/barloader.gif" alt="Processing Image" style=" display: none; margin-left: 360px;padding-bottom: 10px;" />
	<div id="weathermap">
	
	</div>
	
	<div id="weathermap1">
	
	</div>

</div>

<div id="result"></div>
<div id="resultDiv"></div>



<div id="overviewDivId1" class="span5" style="margin: 12px 48px 0px 49px;"></div>
<div id="overviewDivId3" class="span5" style="margin:12px 10px 10px 39px"></div>
<div id="overviewDivId2" class="span10" style="margin:0px 0px 0px 39px"></div>
<div id="subTitlesDiv" style="margin-top:20px;clear:both;"></div>
<div id="popupDiv"></div>
<div id="results1Div" style="overflow:scroll;height:500px;margin-top:30px;" ></div>
</div>


<div class="parliamentCls offset2 container">
<div id="bannerDiv" align="center" style="display: block;">
<img width="960" height="32" border="0" usemap="#Maps22" src="images/India-Page-Banner.jpg">
</div>
	<div class="parliamentResultsDiv" style="background:#ffffff;float:left;width:975px;"></div>
	<div class="partyWiseResultDiv" style="background:#ffffff;float:left;width:975px;"></div>
	<div style="background:#ffffff;float:left;width:975px;"> 
	
		
		<h2 class='offset2' style="margin-bottom:5px;color:#27AFA6;">Exit Polls From Different Sources</h2>
		<table width='800' cellspacing='0' cellpadding='2' border='0' class='exitPolls offset1'>
			<tbody>
				<tr style='font-weight:bold;'>
					<td style='border-bottom:1px solid #B0BDDA;'>CHANNEL</td>
					<td style='border-bottom:1px solid #B0BDDA;'>NDA</td>
					<td style='border-bottom:1px solid #B0BDDA;'>UPA</td>
					<td style='border-bottom:1px solid #B0BDDA;'>OTHERS</td>
				</tr>
			
				<tr>
					<td>Today's Chanakya</td>
					<td>340</td>
					<td>70</td>
					<td>133</td>
				</tr>
				<tr>
					<td>Times Now</td>
					<td>249</td>
					<td>148</td>
					<td>146</td>
				</tr>
				<tr>
					<td>CNN-IBN</td>
					<td>270 - 282</td>
					<td>92 - 102</td>
					<td>150 - 160</td>
				</tr>
				<tr>
					<td>HeadLines Today</td>
					<td>272(+/- 11)</td>
					<td>115(+/- 5)</td>
					<td>156</td>
				</tr>
				<tr>
					<td>ABP News</td>
					<td>281</td>
					<td>97</td>
					<td>161</td>
				</tr>
				<tr>
					<td>C-Voter</td>
					<td>289</td>
					<td>101</td>
					<td>153</td>
				</tr>
				<tr>
					<td>Aaj tdak</td>
					<td>272</td>
					<td>115</td>
					<td> - </td>
				</tr>
				<tr>
					<td style='border-bottom:1px solid #B0BDDA;'>India TV</td>
					<td style='border-bottom:1px solid #B0BDDA;'>289</td>
					<td style='border-bottom:1px solid #B0BDDA;'>101</td>
					<td style='border-bottom:1px solid #B0BDDA;'> - </td>
				</tr>
			</tbody>
		</table>
		</div>
</div>


<!-- SAMBA START  -->
<div class="container hide" style="font-family: verdana; font-size: 14px; border: 1px solid rgb(204, 204, 204); padding: 0px 10px 10px; margin-top: 24px;" id="liveResultsDiv">
<div id="navcontainer" style="margin-left:-10px;">
<ul>
<li><a href="#" class="Tabactive highLight" onclick="buildMenuForStateAnalysis('partyWiseStatsDiv')" class="partyWiseStatsDiv">Party Wise Statistics Analysis</a></li>
<li><a href="#" class="InteractiveMapDiv highLight" onclick="buildMenuForStateAnalysis('InteractiveMapDiv')" >Interactive Map Analysis</a></li>
<li><a href="#" class="regionWiseAnalysisDiv highLight" onclick="buildMenuForStateAnalysis('regionWiseAnalysisDiv')">Region Wise Analysis</a></li>
<li><a href="#" class="casteAnalysisDiv highLight" onclick="buildMenuForStateAnalysis('casteAnalysisDiv')">Caste Wise Analysis</a></li>
<li><a href="#" class="partyRebelsEffect highLight" onclick="buildMenuForStateAnalysis('partyRebelsEffect')">Party Rebels Effect</a></li>
</ul>
</div>
<!-- start Caste Wise Analysis -->
<div style="margin-left:400px;margin-top:20px;display:none;" id="casteAnalysisAjax"><img src="./images/Loading-data.gif" alt="Processing Image" /></div>
<div id="casteAnalysisDiv" style="display:none;margin-top:20px;">

</div>
<!-- end Caste wise Analysis-->
<!-- start Region wise Analysis -->
<div class="container hide" style="font-family: verdana; font-size: 14px; border: 1px solid rgb(204, 204, 204); padding: 0px 10px 10px; margin-top: 24px;width:920px !important;" id="regionWiseAnalysisDiv">
<div  style="text-align:center;margin-top:10px;display:none;"><img src="images/Live AP State Election Results.jpg"></div>



<img id="stateAjaxImg" src="./images/icons/search.gif" alt="Processing Image" style="display:none;"/>



<div class="span12 " id="telanganaMainDiv" >
<h5 id="regionHead1"></h5>
<div id="telanganaDiv" class="span5"></div>
<div id="telanganaMuncipaDiv" class="span5"></div>

</div>
<div class="span12 " id="andhraMainDiv" style="margin-bottom:20px;">
<h5 id="regionHead"></h5>
<div id="andhraDiv" class="span5"></div>
<div id="andhraMuncipalDiv" class="span5"></div>

</div>
<br/><br/>
<div>
<table class="offset1 headingTbl">

<tr><td class="span3">Select Election<select id="electionId" class="input-block-level">
	  <option value="258">2014 Assembly </option>
	  <option value="38">2009 Assembly </option>
	  <option value="3">2004 Assembly </option>
	 </select></td>
						    <td class="span3"> Select State<select class="input-block-level" id="stateScope" name="stateScope" style="width:99%;" onchange="getLocationDetailsForSelectedScope1()" class="input-block-level">
							<option value="0">All</option>
								<option value="1">Andhra</option>
								<option value="2">Telangana</option>
								
						   </select>	</td>

	 <td class="span3"> Select Level<select class="input-block-level" id="scopeId" name="scopeId" style="width:99%;" onchange="getLocationDetailsForSelectedScope1()" >
								<option value="3">Region</option>
								<option value="2">District</option>
								<option value="4">Parliament</option>
								<option value="5">Assembly</option>
						   </select>	</td>

						   
			

<td class="span3"  id="regionSelect"> <span id="selectText">Select Region</span><img src="./images/icons/search.gif" alt="Processing Image" id="processImg" style="display:none;"/><select class="input-block-level" id="locaionsId1" multiple="true" style="width:96%;height:55px;"></select></td>	

	
</tr>
</table>
</div>
 <div  class="offset1" style="clear:both;">


<label class="radio inline">

<input type="radio" class="reportType matrixRprt" id="matrixReportId" value="Matrix Report" name="report" checked="true" style="margin-top:-5px;"><span>Matrix Report</span>
</label>

<label class="radio inline">
<input type="radio" class="reportType matrixRprt" id="subReportId" value="Sub Report" name="report"  style="margin-top:-5px;"><span>Sub Report</span>
</label>

</div>

<div  class="offset4 inrReportDivCls" style="clear:both;display:none;">
	<label class="radio inline">
		<input type="radio" class="inrRprtType" id="mainReportId" value="Main Report" name="inrReport" checked="true" style="margin-top:-5px;"><span>Main Report</span>
	</label>

	<label class="radio inline">
		<input type="radio" class="inrRprtType" id="partyReportId" value="Party Report" name="inrReport"  style="margin-top:-5px;"><span>Party Report</span>
	</label>
</div>

<div  class="offset3 parties" style="clear:both;"></div>
  
					<div class="offset1" style="margin-top:20px;">

					
						<a onClick="showSelectedReport()" value="Display" class="btn" style="margin-top:0px; background: none repeat scroll 0 0 #0088CC; color: #FFFFFF;font-weight: normal;">Display</a>
					

					
						<a onClick="clearFields()" value="Clear" class="btn" style="margin-top:0px; background: none repeat scroll 0 0 #0088CC;
    color: #FFFFFF;font-weight: normal;">Clear</a>
					

						
						<a onClick="exportToExcel()" class="btn" value="Export To Excel" style="margin-top:0px; background: none repeat scroll 0 0 #0088CC;
    color: #FFFFFF;font-weight: normal;">Export To Excel</a>
	
						
	<img id="ajaxImage" src="./images/Loading-data.gif" alt="Processing Image" style="margin-left:70px;display:none;"/>

					</div>


<!--<div  class="offset1 hide" style="clear:both;" id="percentDiv">

	<label class="radio inline">
	<input type="radio" class="percentTypeClass" id="bothId" value="Both" name="percentTypeClass"  style="margin-top:-5px;" checked="true"><span>Seats Count & Votes Percentage</span>
	</label>

	<label class="radio inline">
	<input type="radio" class="percentTypeClass" id="" value="Seats" name="percentTypeClass"  style="margin-top:-5px;"><span>Seats Count</span>
	</label>

	<label class="radio inline">
	<input type="radio" class="percentTypeClass" id="" value="Votes" name="percentTypeClass"  style="margin-top:-5px;"><span>Votes Percentage</span>
	</label>	

</div>-->

					<div id="errorDiv" class="offset1" style="font-weight:bold;color:red;"></div>


    <div class="offset1" id="summaryDiv" style="margin-top:20px;">
	 <div id="matrixWonSummaryId"></div>
	 <div id="matrixLeadSummaryId"></div>
	</div>
		
	<div id="reportDiv" style="margin-top:30px;">

	<div id="constituencyResultsDiv"></div>

	<div id="test"  class="pull-left offset1" style="margin-bottom:10px;"></div>
	<div id="matridLeadId"  class="pull-right" style="margin-right:100px;margin-bottom:15px;"></div>

	<div id="marginAnalysis1" class="offset1" style="clear:both;margin-top:20px;"></div>

	</div>

</div>



 <img id="stateAjaxImg3" src="./images/icons/barloader.gif" alt="Processing Image" style=" display: none; margin-left: 600px;padding-bottom: 10px;" />

 
 
  
 
 <img id="stateAjaxImg5" src="./images/icons/barloader.gif" alt="Processing Image" style=" display: none; margin-left: 600px;padding-bottom: 10px;" />
 
  
  
  </div>
   <div id="weathermap5" class="offset2"> </div>
   <center><img id="effectClrImg" style="display:none;" src="images/specialPage/parlicolorcodes.png"><div id="weathermap3"> </div></center>
<!-- SAMBA END -->

 <div id="processingDialogue"></div>
<div id="districtWiseAnalysis" class="container">
<div style="width:998px;padding-left:5px;">
   <div style="padding-left:371px;padding-top:7px;"><div class="main-mbg">District Wise Party Performance</div></div>
   <div style="background-color:#FFFFFF;min-height:360px;">
         
   <div style="padding-top:10px;padding-left:100px;width:80%;text-align:center;">
     <div id="errorMessage"></div>
     <table>  
	  <tr>
		  <td><div id="showHideState"><b>Select State :</b>&nbsp;&nbsp;<select  id="stateListId"  onchange="getElectionYears('Assembly');"><option value="0">Select State</option></select></div></td>
		  <td>&nbsp;&nbsp;&nbsp;&nbsp;<b>Select Year :</b>&nbsp;&nbsp;<select id="yearSelId" onchange="getAllParties('main');" ><option value="0">Select State</option></select></td>
		  <td>&nbsp;&nbsp;&nbsp;&nbsp;<b>Select Party :</b></td>
		  <td>&nbsp;&nbsp;<select id="partiesIdDiv" multiple="multiple" style="width:100px;"><option value="0">Select Party</option></select></td>
		  <td><input type="button" class="buttonStyle" onclick="getDetails();"value="Search" ></td>
		  <td><span id="select_ImgSpan" style="padding-left:10px;padding-top:5px;display:none;"><img src="images/icons/partypositions.gif"></span></td>
      </tr>
	 </table>
	 <table>
	   <tr>
	     <td><span id="resultImgDiv" style="padding-left:268px;padding-top:5px;display:none;"><img src="images/icons/goldAjaxLoad.gif"></span></td>
	   </tr>
	 </table>
   </div>
   <div style="padding-bottom:30px;width:100%">
     <div class="yui-skin-sam" style="width:990px;padding-left:5px;padding-right:5px;overflow:auto;text-align:center;"  >
	   <div id="districtAnyHeading"></div>
	   <div class="yui-dt" id="districtAny"></div>
	 </div>
   </div>
   <div id="analysisTable" style="margin-left:180px;"></div>
   <div id="analysisTableDisplay" style="overflow:auto;"></div>
   <div style="margin-top:20px;width:980px;overflow:hidden;">
     <div style="width:980px;padding-left:5px; margin-bottom: 23px;padding-right:5px;" id="districtChart" />
   </div>
   <div id="hideSelect" style="display:none;">
     <center><div style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);padding-top:10px;">View Party Wise Results In All Districts : </div></center>
     <center>
	     <div>
		    <table>
			   <tr>
			     <td><b>Select Parties : </b>&nbsp;&nbsp;</td>
			     <td><select id="parties" multiple="multiple" style="width:100px;" ></select></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <td><input type="button" class="buttonClass" value="View" onclick="getDataForChart('sub');" /></td>&nbsp;&nbsp;&nbsp;
				 <td><div id="searchImage" style="display:none;"><img src="images/icons/search.gif"></img width="18px" height="18px;"></div></td>
			   </tr>
			 </table>
		  </div>
	  </center>
   </div>
   <div style="margin-bottom:30px;margin-top:20px;width:980px;overflow:hidden;">
     <div style="width:980px;padding-left:5px;padding-right:5px;" id="districtPartyChart" ></div>
   </div>
   <div style="padding-bottom:30px;margin-left:0px;width:980px;overflow:hidden;">
     <div style="width:980px;padding-left:5px;padding-right:5px;" id="districtPartyChartMore" ></div>
   </div>
  </div>
</div>
</div>
<script type="text/javascript">

(function(){L.labelVersion="0.2.2-dev",L.Label=L.Class.extend({includes:L.Mixin.Events,options:{className:"",clickable:!1,direction:"right",noHide:!1,offset:[12,-15],opacity:1,zoomAnimation:!0},initialize:function(t,e){L.setOptions(this,t),this._source=e,this._animated=L.Browser.any3d&&this.options.zoomAnimation,this._isOpen=!1},onAdd:function(t){this._map=t,this._pane=this._source instanceof L.Marker?t._panes.markerPane:t._panes.popupPane,this._container||this._initLayout(),this._pane.appendChild(this._container),this._initInteraction(),this._update(),this.setOpacity(this.options.opacity),t.on("moveend",this._onMoveEnd,this).on("viewreset",this._onViewReset,this),this._animated&&t.on("zoomanim",this._zoomAnimation,this),L.Browser.touch&&!this.options.noHide&&L.DomEvent.on(this._container,"click",this.close,this)},onRemove:function(t){this._pane.removeChild(this._container),t.off({zoomanim:this._zoomAnimation,moveend:this._onMoveEnd,viewreset:this._onViewReset},this),this._removeInteraction(),this._map=null},setLatLng:function(t){return this._latlng=L.latLng(t),this._map&&this._updatePosition(),this},setContent:function(t){return this._previousContent=this._content,this._content=t,this._updateContent(),this},close:function(){var t=this._map;t&&(L.Browser.touch&&!this.options.noHide&&L.DomEvent.off(this._container,"click",this.close),t.removeLayer(this))},updateZIndex:function(t){this._zIndex=t,this._container&&this._zIndex&&(this._container.style.zIndex=t)},setOpacity:function(t){this.options.opacity=t,this._container&&L.DomUtil.setOpacity(this._container,t)},_initLayout:function(){this._container=L.DomUtil.create("div","leaflet-label "+this.options.className+" leaflet-zoom-animated"),this.updateZIndex(this._zIndex)},_update:function(){this._map&&(this._container.style.visibility="hidden",this._updateContent(),this._updatePosition(),this._container.style.visibility="")},_updateContent:function(){this._content&&this._map&&this._prevContent!==this._content&&"string"==typeof this._content&&(this._container.innerHTML=this._content,this._prevContent=this._content,this._labelWidth=this._container.offsetWidth)},_updatePosition:function(){var t=this._map.latLngToLayerPoint(this._latlng);this._setPosition(t)},_setPosition:function(t){var e=this._map,i=this._container,n=e.latLngToContainerPoint(e.getCenter()),o=e.layerPointToContainerPoint(t),s=this.options.direction,a=this._labelWidth,l=L.point(this.options.offset);"right"===s||"auto"===s&&o.x<n.x?(L.DomUtil.addClass(i,"leaflet-label-right"),L.DomUtil.removeClass(i,"leaflet-label-left"),t=t.add(l)):(L.DomUtil.addClass(i,"leaflet-label-left"),L.DomUtil.removeClass(i,"leaflet-label-right"),t=t.add(L.point(-l.x-a,l.y))),L.DomUtil.setPosition(i,t)},_zoomAnimation:function(t){var e=this._map._latLngToNewLayerPoint(this._latlng,t.zoom,t.center).round();this._setPosition(e)},_onMoveEnd:function(){this._animated&&"auto"!==this.options.direction||this._updatePosition()},_onViewReset:function(t){t&&t.hard&&this._update()},_initInteraction:function(){if(this.options.clickable){var t=this._container,e=["dblclick","mousedown","mouseover","mouseout","contextmenu"];L.DomUtil.addClass(t,"leaflet-clickable"),L.DomEvent.on(t,"click",this._onMouseClick,this);for(var i=0;e.length>i;i++)L.DomEvent.on(t,e[i],this._fireMouseEvent,this)}},_removeInteraction:function(){if(this.options.clickable){var t=this._container,e=["dblclick","mousedown","mouseover","mouseout","contextmenu"];L.DomUtil.removeClass(t,"leaflet-clickable"),L.DomEvent.off(t,"click",this._onMouseClick,this);for(var i=0;e.length>i;i++)L.DomEvent.off(t,e[i],this._fireMouseEvent,this)}},_onMouseClick:function(t){this.hasEventListeners(t.type)&&L.DomEvent.stopPropagation(t),this.fire(t.type,{originalEvent:t})},_fireMouseEvent:function(t){this.fire(t.type,{originalEvent:t}),"contextmenu"===t.type&&this.hasEventListeners(t.type)&&L.DomEvent.preventDefault(t),"mousedown"!==t.type?L.DomEvent.stopPropagation(t):L.DomEvent.preventDefault(t)}}),L.BaseMarkerMethods={showLabel:function(){return this.label&&this._map&&(this.label.setLatLng(this._latlng),this._map.showLabel(this.label)),this},hideLabel:function(){return this.label&&this.label.close(),this},setLabelNoHide:function(t){this._labelNoHide!==t&&(this._labelNoHide=t,t?(this._removeLabelRevealHandlers(),this.showLabel()):(this._addLabelRevealHandlers(),this.hideLabel()))},bindLabel:function(t,e){var i=this.options.icon?this.options.icon.options.labelAnchor:this.options.labelAnchor,n=L.point(i)||L.point(0,0);return n=n.add(L.Label.prototype.options.offset),e&&e.offset&&(n=n.add(e.offset)),e=L.Util.extend({offset:n},e),this._labelNoHide=e.noHide,this.label||(this._labelNoHide||this._addLabelRevealHandlers(),this.on("remove",this.hideLabel,this).on("move",this._moveLabel,this).on("add",this._onMarkerAdd,this),this._hasLabelHandlers=!0),this.label=new L.Label(e,this).setContent(t),this},unbindLabel:function(){return this.label&&(this.hideLabel(),this.label=null,this._hasLabelHandlers&&(this._labelNoHide||this._removeLabelRevealHandlers(),this.off("remove",this.hideLabel,this).off("move",this._moveLabel,this).off("add",this._onMarkerAdd,this)),this._hasLabelHandlers=!1),this},updateLabelContent:function(t){this.label&&this.label.setContent(t)},getLabel:function(){return this.label},_onMarkerAdd:function(){this._labelNoHide&&this.showLabel()},_addLabelRevealHandlers:function(){this.on("mouseover",this.showLabel,this).on("mouseout",this.hideLabel,this),L.Browser.touch&&this.on("click",this.showLabel,this)},_removeLabelRevealHandlers:function(){this.off("mouseover",this.showLabel,this).off("mouseout",this.hideLabel,this),L.Browser.touch&&this.off("click",this.showLabel,this)},_moveLabel:function(t){this.label.setLatLng(t.latlng)}},L.Icon.Default.mergeOptions({labelAnchor:new L.Point(9,-20)}),L.Marker.mergeOptions({icon:new L.Icon.Default}),L.Marker.include(L.BaseMarkerMethods),L.Marker.include({_originalUpdateZIndex:L.Marker.prototype._updateZIndex,_updateZIndex:function(t){var e=this._zIndex+t;this._originalUpdateZIndex(t),this.label&&this.label.updateZIndex(e)},_originalSetOpacity:L.Marker.prototype.setOpacity,setOpacity:function(t,e){this.options.labelHasSemiTransparency=e,this._originalSetOpacity(t)},_originalUpdateOpacity:L.Marker.prototype._updateOpacity,_updateOpacity:function(){var t=0===this.options.opacity?0:1;this._originalUpdateOpacity(),this.label&&this.label.setOpacity(this.options.labelHasSemiTransparency?this.options.opacity:t)},_originalSetLatLng:L.Marker.prototype.setLatLng,setLatLng:function(t){return this.label&&!this._labelNoHide&&this.hideLabel(),this._originalSetLatLng(t)}}),L.CircleMarker.mergeOptions({labelAnchor:new L.Point(0,0)}),L.CircleMarker.include(L.BaseMarkerMethods),L.Path.include({bindLabel:function(t,e){return this.label&&this.label.options===e||(this.label=new L.Label(e,this)),this.label.setContent(t),this._showLabelAdded||(this.on("mouseover",this._showLabel,this).on("mousemove",this._moveLabel,this).on("mouseout remove",this._hideLabel,this),L.Browser.touch&&this.on("click",this._showLabel,this),this._showLabelAdded=!0),this},unbindLabel:function(){return this.label&&(this._hideLabel(),this.label=null,this._showLabelAdded=!1,this.off("mouseover",this._showLabel,this).off("mousemove",this._moveLabel,this).off("mouseout remove",this._hideLabel,this)),this},updateLabelContent:function(t){this.label&&this.label.setContent(t)},_showLabel:function(t){this.label.setLatLng(t.latlng),this._map.showLabel(this.label)},_moveLabel:function(t){this.label.setLatLng(t.latlng)},_hideLabel:function(){this.label.close()}}),L.Map.include({showLabel:function(t){return this.addLayer(t)}}),L.FeatureGroup.include({clearLayers:function(){return this.unbindLabel(),this.eachLayer(this.removeLayer,this),this},bindLabel:function(t,e){return this.invoke("bindLabel",t,e)},unbindLabel:function(){return this.invoke("unbindLabel")},updateLabelContent:function(t){this.invoke("updateLabelContent",t)}})})(this,document);
</script>
<script type="text/javascript">
var stateType = '';

	$('document').ready(function(){
		stateType = 'Semandhra';
		$('#indiaBannerId').hide();
		$('.parliamentCls ').hide();

	$('#legend').show();
        $('#areaSelectionDiv').hide();
        $('#stateSelectDiv').hide();
        $('#submitButtionDiv').hide();
       // getElectionResultForAssemblyPrevious(1,"first",1,2);
        //getElectionResultForParlimentPresent(1,"second",2,2);
     $('#scopeId').change(function(){
         console.log(this);
         $('#rgntxt').text("Select "+$('#scopeId  :selected').text());
     });
	 
	    $(".d3-slider-handle").attr("style","left:"+sliderModiWave+"%");
		$(".d3-slider-handle1").attr("style","left:"+sliderCbnWave+"%");
	});
	//getConstituenctSelection();
	var map = "";
	var map1 = "";
	var electionAcData = '';
	var electionPcData = '';
	function getElectionDetails()
	{
		$('#legend').show();
		var stateVal = $('#stateId option:selected').val();
		var levelVal1 = $('#levelId1 option:selected').val();
		var levelVal2 = $('#levelId2 option:selected').val();
		var year1 = $('#yearId1 option:selected').val();
		var year2 = $('#yearId2 option:selected').val();

		if(levelVal1 == 1 && year1 == 1)
		{
			getElectionResultForAssemblyPresent(stateVal,"first",levelVal1,year1);
		}
		if (levelVal1 == 1 && year1 == 2)
		{
			getElectionResultForAssemblyPrevious(stateVal,"first",levelVal1,year1);
		}
		
		if(levelVal1 == 2 && year1 == 1)
		{
			getElectionResultForParlimentPrevious(stateVal,"first",levelVal1,year1);
		}
		if (levelVal1 == 2 && year1 == 2)
		{
			getElectionResultForParlimentPresent(stateVal,"first",levelVal1,year1);
		}
		
		if(levelVal2 == 1 && year2 == 1)
		{
			getElectionResultForAssemblyPresent(stateVal,"second",levelVal2,year2);
		}
		if(levelVal2 == 1 && year2 == 2)
		{
			getElectionResultForAssemblyPrevious(stateVal,"second",levelVal2,year2);
		}
		
		if(levelVal2 == 2 && year2 == 1)
		{
			getElectionResultForParlimentPrevious(stateVal,"second",levelVal2,year2);
		}
		if(levelVal2 == 2 && year2 == 2)
		{
			getElectionResultForParlimentPresent(stateVal,"second",levelVal2,year2);
		}
		
		
	}
	
	function getMapType(type,stateVal)
	{
		//$("#stateAjaxImg1").css("display","block");
		if(type == 1)
		{
			if(stateVal == 1)
			{
				document.getElementById('weathermap').innerHTML = "<div id='map' class='span6' style='height: 400px; border: 1px solid rgb(51, 51, 51); border-radius: 10px; position: relative; background: none repeat scroll 0% 0% rgb(255, 255, 255);'></div>"
				map = L.map('map', {
				center: [16.0000,80.0000],
				zoom: 6
				});
				map.dragging.disable();
				map.touchZoom.disable();
				map.doubleClickZoom.disable();
				map.scrollWheelZoom.disable();
			}
			else
			{
				document.getElementById('weathermap').innerHTML = "<div id='map' class='span6' style='height: 400px; border: 1px solid rgb(51, 51, 51); border-radius: 10px; position: relative; background: none repeat scroll 0% 0% rgb(255, 255, 255);'></div>"
				map = L.map('map', {
				center: [17.9000,79.0000],
				zoom: 7
				});
				map.dragging.disable();
				map.touchZoom.disable();
				map.doubleClickZoom.disable();
				map.scrollWheelZoom.disable();
			}
			
		
		}
		else
		{
			if(stateVal == 1)
			{
				document.getElementById('weathermap1').innerHTML = "<div id='map1' class='span6' style='height: 400px; float: right ! important; left: 0px; position: relative; border: 1px solid rgb(51, 51, 51); border-radius: 10px; background: none repeat scroll 0% 0% rgb(255, 255, 255);'></div>"
				map1 = L.map('map1', {
				center: [16.0000,80.0000],
				zoom: 6
				});
				map1.dragging.disable();
				map1.touchZoom.disable();
				map1.doubleClickZoom.disable();
				map1.scrollWheelZoom.disable();
			}
			else
			{
				document.getElementById('weathermap1').innerHTML = "<div id='map1' class='span6' style='height: 400px; float: right ! important; left: 0px; position: relative; border: 1px solid rgb(51, 51, 51); border-radius: 10px; background: none repeat scroll 0% 0% rgb(255, 255, 255);'></div>"
				map1 = L.map('map1', {
				center: [17.9000,79.0000],
				zoom: 7
				});
				map1.dragging.disable();
				map1.touchZoom.disable();
				map1.doubleClickZoom.disable();
				map1.scrollWheelZoom.disable();
			}			
		}		
			
			//$('#results1Div,#subTitlesDiv').show();
	}
	
	function getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo)
	{
		if(stateVal == 1)
		{
			if(locationLevel == 1)
			{
				if(year == 1)
				{
					if(mapNo == "first")
					{
						generateMapForApACPrevious(map);
					}
					else
					{
						generateMapForApACPrevious(map1);
					}
					
				}
				else
				{
					if(mapNo == "first")
					{
						generateMapForApACPresent(map);
					}
					else
					{
						generateMapForApACPresent(map1);
					}
					
				}
				
			}
			else
			{
				if(year == 1)
				{
					if(mapNo == "first")
					{
						generateMapForApPCPrevious(map);
					}
					else
					{
						generateMapForApPCPrevious(map1);
					}
					
				}
				else
				{
					if(mapNo == "first")
					{
						generateMapForApPCPresent(map);
					}
					else
					{
						generateMapForApPCPresent(map1);
					}
					
				}
			}
			
		}
		else
		{
			if(locationLevel == 1)
			{
				
				if(year == 1)
				{
					if(mapNo == "first")
					{
						generateMapForTgACPrevious(map);
					}
					else
					{
						generateMapForTgACPrevious(map1);
					}
					
				}
				else
				{
					if(mapNo == "first")
					{
						generateMapForTgACPresent(map);
					}
					else
					{
						generateMapForTgACPresent(map1);
					}
					
				}
				
			}
			else
			{
				if(year == 1)
				{
					if(mapNo == "first")
					{
						generateMapForTgPCPrevious(map);
					}
					else
					{
						generateMapForTgPCPrevious(map1);
					}
					
				}
				else
				{
					if(mapNo == "first")
					{
						generateMapForTgPCPresent(map);
					}
					else
					{
						generateMapForTgPCPresent(map1);
					}
					
				}
			}
			
		}
	}
	function getElectionResultForAssemblyPrevious(stateVal,mapNo,locationLevel,year)
	{
		if(mapNo == "first") 
		{
			getMapType(1,stateVal);
		}
		else
		{
			getMapType(2,stateVal);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		parties.push(1117);
		var jsObj=
		{
				electionId : 258,
				stateId : 1,
				electionScopeId : 2,
				parties : parties,
				scope : "ac",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			electionAcData = result;
			getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
			
			
		});	
	}
	
	function getElectionResultForAssemblyPresent(stateVal,mapNo,locationLevel,year)
	{
		if(mapNo == "first") 
		{
			getMapType(1,stateVal);
		}
		else
		{
			getMapType(2,stateVal);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		parties.push(1117);
		var jsObj=
		{
				electionId : 38,
				stateId : 1,
				electionScopeId : 2,
				parties : parties,
				scope : "ac",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			electionAcData = result;
			getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
			
		});	
	}
	function getElectionResultForParlimentPrevious(stateVal,mapNo,locationLevel,year)
	{
		if(mapNo == "first") 
		{
			getMapType(1,stateVal);
		}
		else
		{
			getMapType(2,stateVal);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		parties.push(1117);
		var jsObj=
		{
				electionId : 17,
				stateId : 1,
				electionScopeId : 1,
				parties : parties,
				scope : "ac",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			electionPcData = result;
			getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
		});	
	}
	
	function getElectionResultForParlimentPresent(stateVal,mapNo,locationLevel,year)
	{
		if(mapNo == "first") 
		{
			getMapType(1,stateVal);
		}
		else
		{
			getMapType(2,stateVal);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		parties.push(1117);
		var jsObj=
		{
				electionId : 260,
				stateId : 1,
				electionScopeId : 1,
				parties : parties,
				scope : "ac",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		 error:function() { 
	           $( "#processingDialogue" ).dialog('close');
	         }
		})
		.done(function( result ) {
		    $('#seemandraStateId').trigger('click');
			electionPcData = result;
			getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
		});	
	}
	
	var areatype ;
	function generateMapForApACPrevious(mapName)
	{
		areatype  = "ac";
		L.geoJson(apaccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
		
		$("#stateAjaxImg1").css("display","none");
			
	}
	
	function generateMapForApACPresent(mapName)
	{
		areatype  = "ac";
		L.geoJson(apaccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
		$("#stateAjaxImg1").css("display","none");
	}
	function generateMapForTgACPrevious(mapName)
	{
		areatype  = "ac";
		L.geoJson(tgaccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
		$("#stateAjaxImg1").css("display","none");
	}
	
	
	function generateMapForTgACPresent(mapName)
	{
		areatype  = "ac";
		L.geoJson(tgaccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
		
		$("#stateAjaxImg1").css("display","none");
	}
	function generateMapForApPCPrevious(mapName)
	{
		areatype  = "pc";
		L.geoJson(appccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
		$("#stateAjaxImg1").css("display","none");
	}
	
	function generateMapForApPCPresent(mapName)
	{
		areatype  = "pc";
		L.geoJson(appccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
		$("#stateAjaxImg1").css("display","none");
	}
	function generateMapForTgPCPrevious(mapName)
	{
		areatype  = "pc";
		L.geoJson(tgpccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
		$("#stateAjaxImg1").css("display","none");
	}
	
	
	function generateMapForTgPCPresent(mapName)
	{
		areatype  = "pc";
		L.geoJson(tgpccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
		$("#stateAjaxImg1").css("display","none");
	}	
	
	function onEachFeature(feature, layer)
	{  
		if(areatype  == "pc")
		{
			onEachFeature1(feature, layer);
		}
		else if(areatype  == "totPc")
		{
			onEachFeatureForIndPC1(feature, layer);
		}
		else if(areatype  == "totac")
		{
			onEachFeatureForAc(feature, layer);
		}
		else if(areatype  == "indpc")
		{
			onEachFeatureForIndPC(feature, layer);
		}
		else
		{
			var popupContent='';

			popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif;margin-left:-40px;">';
			popupContent +=' <header class="timeline-header" style="width:500px;">';
			//popupContent +=' <h3><b aria-hidden="true" class="stateface "></b> '+feature.properties.ac_name+'</a> </h3>';
			popupContent +=' </header>';
			popupContent +=' <ol class="timeline-list"> ';
			popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
			popupContent +=' <article class="results-group">';
			for(var i in electionAcData)
			{
				if(feature.properties.ac == electionAcData[i].hamletId)
				{
			if(i>0){
				popupContent +=' <span style="font-size: 12px;"> <span style="font-size:16px;font-weight:bold;">  Won Party: '+electionAcData[i].selectedCasteDetails[0].name+' ';
			
				var leadby = electionAcData[i].selectedCasteDetails[0].count - electionAcData[i].selectedCasteDetails[1].count;
				popupContent +=' <span style="margin-left:235px;"> Won By :  '+leadby+' Votes</span>';
	
			}
			
			}
		}
		
		popupContent +=' <header class="results-header" style="width: 515px;">';
		popupContent +=' </header>';

			popupContent +=' <table class="table">';
			popupContent +=' <thead>';
		popupContent +=' <tr>';
		popupContent +='<th> Candidate Name </th>';
		popupContent +='<th> Party </td>';
		popupContent +='<th> Votes Percentage </th>';
		popupContent +='<th> Gained Votes </th>';
		popupContent +=' </tr">';
			popupContent +=' </thead>';

popupContent +=' </table>';
		popupContent +=' <header class="results-header" style="width: 515px; margin-top: -30px;border-bottom-color: #004276;">';
		popupContent +=' </header>';
			popupContent +=' <div style="overflow:scroll;height:200px;">';	
	popupContent +=' <table class="results-table">';
	
	popupContent +=' <tbody>';
	
			var partyName ;
			for(var i in electionAcData)
			{
				if(feature.properties.ac == electionAcData[i].hamletId)
				{
					for(var j in electionAcData[i].selectedCasteDetails)
					{
						if(electionAcData[i].selectedCasteDetails[j].casteName != null)
						{

							popupContent +=' <tr class="type-democrat">';
						popupContent +=' <td class="results-title">';
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].casteName+'</span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td style="width:50px;padding-left:10px;">'+electionAcData[i].selectedCasteDetails[j].name+'';					
						popupContent +=' </td>';
						popupContent +=' <td class="results-percentage" >';
						if(electionAcData[i].selectedCasteDetails[j].persent != null){
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].persent+'%</span>';
						}
						else{
						popupContent +=' <span class="percentage-combo" ><span class="number">0 %</span>';
						}
						popupContent +=' <span class="graph">';
						popupContent +=' <span class="bar">';
						popupContent +=' <span style="width:'+electionAcData[i].selectedCasteDetails[j].persent+'%;" class="index"></span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td>';
						popupContent +=' <span style="font-weight:#000000;margin-left:35px;">'+electionAcData[i].selectedCasteDetails[j].count+' </span>';
						popupContent +=' </td>';
						popupContent +=' </tr>';




							if(electionAcData[i].selectedCasteDetails[0].count == 0)
							{
								layer.setStyle({
								color: '#000000',
								weight: 1,
								opacity: 0.6,
								fillOpacity: 0.65,
								fillColor: '#FFFFFF'
								});
								layer.bindPopup(popupContent);
								}
								else
								{
								partyName = electionAcData[i].selectedCasteDetails[0].name;
								fillColour(partyName,layer,popupContent);
							}

						}
						
			
					}
					
				}
			} 
			
			popupContent +=' </tbody>';
			popupContent +=' </table>';
			popupContent +=' </div>';
			popupContent +=' </article>';
			popupContent +=' </li> ';
			popupContent +=' </ol>';
			popupContent +=' </article>';


			$('.leaflet-popup-close-button').html('');
			if (feature.properties && feature.properties.popupContent)
			{
				popupContent += feature.properties.popupContent;
			}
			 
			
			layer.bindLabel(feature.properties.ac_name, {noHide:true});
			layer.on('click', function(e) {
				onClickForMap(popupContent,feature.properties.ac_name+' Assembly Constituency ');
			});
		}
			
		
	}
	
	function onClickForMap(popupContent,name)
	{
		$('#popupDiv').html(popupContent);
		$('#popupDiv').dialog(
		{
			width:600,
			title:" "+name+""
		}
		);
	}
	
	function onEachFeatureForIndPC(feature, layer)
	{

		var popupContent='';

			popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif;margin-left:-40px;">';
			popupContent +=' <header class="timeline-header">';
		//	popupContent +=' <h3><b aria-hidden="true" class="stateface "></b> '+feature.properties.pc_name+'</a> </h3>';
			popupContent +=' </header>';
			popupContent +=' <ol class="timeline-list"> ';
			popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
			popupContent +=' <article class="results-group">';
			
			for(var i in electionPcData)
			{
				if(feature.properties.pc_name == electionPcData[i].name)
				{
					if(electionPcData[i].selectedCasteDetails[0] != undefined){
					popupContent +=' <span style="font-size: 12px;"> <span style="font-size:16px;font-weight:bold;">  Won Party: '+electionPcData[i].selectedCasteDetails[0].name+' ';					
					
					var leadby = electionPcData[i].selectedCasteDetails[0].count - electionPcData[i].selectedCasteDetails[1].count;
					popupContent +=' <span style="margin-left:235px;"> Won By :  '+leadby+' Votes</span>';
					break;					
					}
			}
		}
		
		popupContent +=' <header class="results-header" style="width: 515px;">';
		popupContent +=' </header>';

		popupContent +=' <table class="table">';
			
		popupContent +=' <thead>';
		popupContent +=' <tr>';
		popupContent +='<th> Candidate Name </th>';
		popupContent +='<th> Party </td>';
		popupContent +='<th> Votes Percentage  </th>';
		popupContent +='<th> Gained Votes </th>';
		popupContent +=' </tr">';
			popupContent +=' </thead>';

popupContent +=' </table>';
		popupContent +=' <header class="results-header" style="width: 515px; margin-top: -30px;border-bottom-color: #004276;">';
		popupContent +=' </header>';
			popupContent +=' <div style="overflow:scroll;height:200px;">';	
	popupContent +=' <table class="results-table">';
	
	popupContent +=' <tbody>';
			var partyName ;
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#FFFFFF'
			});
			//layer.bindPopup(popupContent);

			for(var i in electionPcData)
			{
				if(feature.properties.pc_name == electionPcData[i].name)
				{
					for(var j in electionPcData[i].selectedCasteDetails)
					{
						if(electionPcData[i].selectedCasteDetails[j].casteName != null)
						{
							popupContent +=' <tr class="type-democrat">';
						popupContent +=' <td class="results-title">';
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionPcData[i].selectedCasteDetails[j].casteName+'</span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td style="width:50px;padding-left:10px;">'+electionPcData[i].selectedCasteDetails[j].name+'';					
						popupContent +=' </td>';
						popupContent +=' <td class="results-percentage" >';
						if(electionPcData[i].selectedCasteDetails[j].persent != null){
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionPcData[i].selectedCasteDetails[j].persent+'%</span>';
						}
						else{
						popupContent +=' <span class="percentage-combo" ><span class="number">0 %</span>';
						}
						popupContent +=' <span class="graph">';
						popupContent +=' <span class="bar">';
						popupContent +=' <span style="width:'+electionPcData[i].selectedCasteDetails[j].persent+'%;" class="index"></span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td>';
						popupContent +=' <span style="font-weight:#000000;margin-left:35px;">'+electionPcData[i].selectedCasteDetails[j].count+' </span>';
						popupContent +=' </td>';
						popupContent +=' </tr>';


							if(electionPcData[i].selectedCasteDetails[0].count == 0)
							{
								layer.setStyle({
								color: '#000000',
								weight: 1,
								opacity: 0.6,
								fillOpacity: 0.65,
								fillColor: '#FFFFFF'
								});
								//layer.bindPopup(popupContent);
								}
								else
								{
								partyName = electionPcData[i].selectedCasteDetails[0].aliancedWith;
								fillColour(partyName,layer,popupContent);
							}

						}
						
			
					}
					
				}
					
			} 
			
			popupContent +=' </tbody>';
			popupContent +=' </table>';
			popupContent +=' </div>';
			popupContent +=' </article>';
			popupContent +=' </li> ';
			popupContent +=' </ol>';
			popupContent +=' </article>';


			$('.leaflet-popup-close-button').html('');
			if (feature.properties && feature.properties.popupContent)
			{
				popupContent += feature.properties.popupContent;
			}			 
			
			layer.bindLabel(feature.properties.pc_name, {noHide:true});
			layer.on('click', function(e) {
			onClickForMap(popupContent,feature.properties.pc_name+' Parliament Constituency ');
			});
	}
	
	function onEachFeatureForIndPC1(feature, layer)
	{

		var popupContent='';
        var presnt = false;
			popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif;margin-left:-40px;">';
			popupContent +=' <header class="timeline-header">';
		//	popupContent +=' <h3><b aria-hidden="true" class="stateface "></b> '+feature.properties.pc_name+'</a> </h3>';
			popupContent +=' </header>';
			popupContent +=' <ol class="timeline-list"> ';
			popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
			popupContent +=' <article class="results-group">';
			
			for(var i in electionPcData)
			{
				if(feature.properties.pc_name == electionPcData[i].name)
				{
					if(electionPcData[i].selectedCasteDetails[0] != undefined){
					popupContent +=' <span style="font-size: 12px;"> <span style="font-size:16px;font-weight:bold;">  Won Party: '+electionPcData[i].selectedCasteDetails[0].name+' ';					
					
					var leadby = electionPcData[i].selectedCasteDetails[0].count - electionPcData[i].selectedCasteDetails[1].count;
					popupContent +=' <span style="margin-left:235px;"> Won By :  '+leadby+' Votes</span>';
					break;					
					}
			}
		}
		
		popupContent +=' <header class="results-header" style="width: 515px;">';
		popupContent +=' </header>';

		popupContent +=' <table class="table">';
			
		popupContent +=' <thead>';
		popupContent +=' <tr>';
		popupContent +='<th> Candidate Name </th>';
		popupContent +='<th> Party </td>';
		popupContent +='<th> Votes Percentage  </th>';
		popupContent +='<th> Gained Votes </th>';
		popupContent +=' </tr">';
			popupContent +=' </thead>';

popupContent +=' </table>';
		popupContent +=' <header class="results-header" style="width: 515px; margin-top: -30px;border-bottom-color: #004276;">';
		popupContent +=' </header>';
			popupContent +=' <div style="overflow:scroll;height:200px;">';	
	popupContent +=' <table class="results-table">';
	
	popupContent +=' <tbody>';
			var partyName ;
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#FFFFFF'
			});
			//layer.bindPopup(popupContent);

			for(var i in electionPcData)
			{
				if(feature.properties.pc_name == electionPcData[i].name)
				{
					for(var j in electionPcData[i].selectedCasteDetails)
					{
						if(electionPcData[i].selectedCasteDetails[j].casteName != null)
						{ presnt = true;
							popupContent +=' <tr class="type-democrat">';
						popupContent +=' <td class="results-title">';
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionPcData[i].selectedCasteDetails[j].casteName+'</span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td style="width:50px;padding-left:10px;">'+electionPcData[i].selectedCasteDetails[j].name+'';					
						popupContent +=' </td>';
						popupContent +=' <td class="results-percentage" >';
						if(electionPcData[i].selectedCasteDetails[j].persent != null){
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionPcData[i].selectedCasteDetails[j].persent+'%</span>';
						}
						else{
						popupContent +=' <span class="percentage-combo" ><span class="number">0 %</span>';
						}
						popupContent +=' <span class="graph">';
						popupContent +=' <span class="bar">';
						popupContent +=' <span style="width:'+electionPcData[i].selectedCasteDetails[j].persent+'%;" class="index"></span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td>';
						popupContent +=' <span style="font-weight:#000000;margin-left:35px;">'+electionPcData[i].selectedCasteDetails[j].count+' </span>';
						popupContent +=' </td>';
						popupContent +=' </tr>';


							if(electionPcData[i].selectedCasteDetails[0].count == 0)
							{
								layer.setStyle({
								color: '#000000',
								weight: 1,
								opacity: 0.6,
								fillOpacity: 0.65,
								fillColor: '#FFFFFF'
								});
								//layer.bindPopup(popupContent);
								}
								else
								{
								partyName = electionPcData[i].selectedCasteDetails[0].aliancedWith;
								fillColour(partyName,layer,popupContent);
							}

						}
						
			
					}
					
				}
					
			} 
			
			popupContent +=' </tbody>';
			popupContent +=' </table>';
			popupContent +=' </div>';
			popupContent +=' </article>';
			popupContent +=' </li> ';
			popupContent +=' </ol>';
			popupContent +=' </article>';


			$('.leaflet-popup-close-button').html('');
			if (feature.properties && feature.properties.popupContent)
			{
				popupContent += feature.properties.popupContent;
			}			 
			
			layer.bindLabel(feature.properties.pc_name, {noHide:true});
			if(presnt){
			layer.on('click', function(e) {
			onClickForMap(popupContent,feature.properties.pc_name+' Parliament Constituency ');
			});
			}
	}
	
	function onEachFeatureForAc(feature, layer)
	{
		var popupContent='';
        var prsnt = false;
			popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif;margin-left:-40px;">';
			popupContent +=' <header class="timeline-header" style="width:500px;">';
			//popupContent +=' <h3><b aria-hidden="true" class="stateface "></b> '+feature.properties.ac_name+'</a> </h3>';
			popupContent +=' </header>';
			popupContent +=' <ol class="timeline-list"> ';
			popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
			popupContent +=' <article class="results-group">';
			for(var i in electionAcData)
			{
				if(feature.properties.ac == electionAcData[i].hamletId)
				{
			if(i>0){
				popupContent +=' <span style="font-size: 12px;"> <span style="font-size:16px;font-weight:bold;">  Won Party: '+electionAcData[i].selectedCasteDetails[0].name+' ';
			
				var leadby = electionAcData[i].selectedCasteDetails[0].count - electionAcData[i].selectedCasteDetails[1].count;
				popupContent +=' <span style="margin-left:235px;"> Won By :  '+leadby+' Votes</span>';
	
			}
			
			}
		}
		
		popupContent +=' <header class="results-header" style="width: 515px;">';
		popupContent +=' </header>';

			popupContent +=' <table class="table">';
			popupContent +=' <thead>';
		popupContent +=' <tr>';
		popupContent +='<th> Candidate Name </th>';
		popupContent +='<th> Party </td>';
		popupContent +='<th> Votes Percentage </th>';
		popupContent +='<th> Gained Votes </th>';
		popupContent +=' </tr">';
			popupContent +=' </thead>';

popupContent +=' </table>';
		popupContent +=' <header class="results-header" style="width: 515px; margin-top: -30px;border-bottom-color: #004276;">';
		popupContent +=' </header>';
			popupContent +=' <div style="overflow:scroll;height:200px;">';	
	popupContent +=' <table class="results-table">';
	
	popupContent +=' <tbody>';
	
			var partyName ;
			for(var i in electionAcData)
			{
				if(feature.properties.ac == electionAcData[i].hamletId)
				{
					for(var j in electionAcData[i].selectedCasteDetails)
					{
						if(electionAcData[i].selectedCasteDetails[j].casteName != null)
						{
                           prsnt = true;
							popupContent +=' <tr class="type-democrat">';
						popupContent +=' <td class="results-title">';
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].casteName+'</span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td style="width:50px;padding-left:10px;">'+electionAcData[i].selectedCasteDetails[j].name+'';					
						popupContent +=' </td>';
						popupContent +=' <td class="results-percentage" >';
						if(electionAcData[i].selectedCasteDetails[j].persent != null){
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].persent+'%</span>';
						}
						else{
						popupContent +=' <span class="percentage-combo" ><span class="number">0 %</span>';
						}
						popupContent +=' <span class="graph">';
						popupContent +=' <span class="bar">';
						popupContent +=' <span style="width:'+electionAcData[i].selectedCasteDetails[j].persent+'%;" class="index"></span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td>';
						popupContent +=' <span style="font-weight:#000000;margin-left:35px;">'+electionAcData[i].selectedCasteDetails[j].count+' </span>';
						popupContent +=' </td>';
						popupContent +=' </tr>';




							if(electionAcData[i].selectedCasteDetails[0].count == 0)
							{
								layer.setStyle({
								color: '#000000',
								weight: 1,
								opacity: 0.6,
								fillOpacity: 0.65,
								fillColor: '#FFFFFF'
								});
								layer.bindPopup(popupContent);
								}
								else
								{
								partyName = electionAcData[i].selectedCasteDetails[0].name;
								fillColour(partyName,layer,popupContent);
							}

						}
						
			
					}
					
				}
			} 
			
			popupContent +=' </tbody>';
			popupContent +=' </table>';
			popupContent +=' </div>';
			popupContent +=' </article>';
			popupContent +=' </li> ';
			popupContent +=' </ol>';
			popupContent +=' </article>';
			if(!prsnt){
                  layer.setStyle({
								color: '#000000',
								weight: 1,
								opacity: 0.6,
								fillOpacity: 0.65,
								fillColor: '#FFFFFF'
								});
			}

			$('.leaflet-popup-close-button').html('');
			if (feature.properties && feature.properties.popupContent)
			{
				popupContent += feature.properties.popupContent;
			}
			 
			
			layer.bindLabel(feature.properties.ac_name, {noHide:true});
			if(prsnt){
			layer.on('click', function(e) {
				onClickForMap(popupContent,feature.properties.ac_name+' Assembly Constituency ');
			});
			}
	}
	function onEachFeatureForPc(feature, layer)
	{
		layer.setStyle({
		color: '#000000', 
		weight: 1,
		opacity: 0.6,
		fillOpacity: 0.65,
		fillColor: '#FFFAF0'
		});
		for(var i in psDetails)
		{
			if(psDetails[i].name.toUpperCase() == feature.properties.pc_name.toUpperCase())
			{
				layer.setStyle({
				color: '#000000', 
				weight: 1,
				opacity: 0.6,
				fillOpacity: 0.65,
				fillColor: '#FF4500'
				});
			}
			/* else
			{
				layer.setStyle({
				color: '#000000', 
				weight: 1,
				opacity: 0.6,
				fillOpacity: 0.65,
				fillColor: '#FFFAF0'
				});
			} */
		}
	}
	
	
	function onEachFeature1(feature, layer)
	{
		var popupContent='';

		popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif; margin-left: -40px;">';
		popupContent +=' <header class="timeline-header">';
	//	popupContent +=' <h3><b aria-hidden="true" class="stateface "></b> '+feature.properties.FIRST_pc_n+' Parliament Constituency </a></h3>';
		popupContent +=' </header>';
		popupContent +=' <ol class="timeline-list"> ';
		popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
		popupContent +=' <article class="results-group">';
		
		for(var i in electionPcData)
		{	
			if(feature.properties.pc == electionPcData[i].hamletId)
			{
			if(i>0){
				popupContent +=' <span style="font-size: 12px;"> <span style="font-size:16px;font-weight:bold;">  Won Party: '+electionPcData[i].selectedCasteDetails[0].name+' ';
			
				var leadby = electionPcData[i].selectedCasteDetails[0].count - electionPcData[i].selectedCasteDetails[1].count;
				popupContent +=' <span style="margin-left:235px;"> Won By :  '+leadby+' Votes</span>';
	
			}
			
			}
		}
		
		popupContent +=' <header class="results-header" style="width: 515px;">';
		popupContent +=' </header>';
		popupContent +=' <table class="table">';
			
		popupContent +=' <thead>';
		popupContent +=' <tr>';
		popupContent +='<th> Candidate Name </th>';
		popupContent +='<th> Party </td>';
		popupContent +='<th> Votes Percentage </th>';
		popupContent +='<th> Gained Votes </th>';
		popupContent +=' </tr">';
			popupContent +=' </thead>';

popupContent +=' </table>';
		popupContent +=' <header class="results-header" style="width: 515px; margin-top: -30px;border-bottom-color: #004276;">';
		popupContent +=' </header>';
		popupContent +=' <div style="overflow:scroll;height:200px;">';		
	popupContent +=' <table class="results-table">';
	
	popupContent +=' <tbody>';
		for(var i in electionPcData)
		{	
			if(feature.properties.pc == electionPcData[i].hamletId)
			{
				for(var j in electionPcData[i].selectedCasteDetails)
				{
					if(electionPcData[i].selectedCasteDetails[j].casteName != null)
					{
					
						
						popupContent +=' <tr class="type-democrat">';
						popupContent +=' <td class="results-title">';
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionPcData[i].selectedCasteDetails[j].casteName+'</span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td style="width:50px;padding-left:10px;">'+electionPcData[i].selectedCasteDetails[j].name+'';					
						popupContent +=' </td>';
						popupContent +=' <td class="results-percentage" >';
						if(electionPcData[i].selectedCasteDetails[j].persent != null){
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionPcData[i].selectedCasteDetails[j].persent+'%</span>';
						}
						else{
						popupContent +=' <span class="percentage-combo" ><span class="number">0 %</span>';
						}
						popupContent +=' <span class="graph">';
						popupContent +=' <span class="bar">';
						popupContent +=' <span style="width:'+electionPcData[i].selectedCasteDetails[j].persent+'%;" class="index"></span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td>';
						popupContent +=' <span style="font-weight:#000000;margin-left:35px;">'+electionPcData[i].selectedCasteDetails[j].count+' </span>';
						popupContent +=' </td>';
						popupContent +=' </tr>';
						

						
						if(electionPcData[i].selectedCasteDetails[0].count == 0)
						{
							layer.setStyle({
							color: '#000000',
							weight: 1,
							opacity: 0.6,
							fillOpacity: 0.65,
							fillColor: '#FFFFFF'
							});
							layer.bindPopup(popupContent);
							}
							else
							{
							partyName = electionPcData[i].selectedCasteDetails[0].name;
							fillColour(partyName,layer,popupContent);
						}
					}
					
		
				}
				
			}
		} 
		popupContent +=' </tbody>';
		popupContent +=' </table>';
		popupContent +=' </div>';		
		popupContent +=' </article>';
		popupContent +=' </li> ';
		popupContent +=' </ol>';
		popupContent +=' </article>';

		if (feature.properties && feature.properties.popupContent)
		{
			popupContent += feature.properties.popupContent;
		}
		 			
		layer.bindLabel(feature.properties.FIRST_pc_n, {noHide:false});
		
		layer.on('click', function(e) {
				onClickForMap(popupContent,feature.properties.FIRST_pc_n+' Parliament Constituency ');
		});
	}
	
	
	function getElectionResultForTotalParliment()
	{
		 //$("#stateAjaxImg3").css("display","block");
		var parties = new Array();
		//parties.push(872);
		//parties.push(362);
		parties.push(163);
		//parties.push(72);
		//parties.push(886);
		//parties.push(662);
		//parties.push(1117);
		var jsObj=
		{
				electionId : 260,
				stateId : 1,
				electionScopeId : 1,
				parties : parties,
				scope : "pc",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
		  try{
		    var resArr = new Array();
		    for(var i in result){
			
			  if(result[i].selectedCasteDetails != null && result[i].selectedCasteDetails.length > 0 &&  result[i].selectedCasteDetails[0].aliancedWith =="NDA"){
			   resArr.push(result[i]);
			  }
			}
			electionPcData = resArr;
						 un_emp = 0.0;
			 if(resArr != null && resArr.length > 0){ 
			  var totalSeats = resArr.length;			  
					 if(totalSeats <= 115){
						 if(totalSeats <= 0){
						  un_emp = 0.0;
						 }else if(totalSeats <= 23){
						   un_emp = 0.05;
						 }else if(totalSeats <= 46){
						   un_emp = 0.10;
						 }else if(totalSeats <= 69){
						   un_emp = 0.15;
						 }else if(totalSeats <= 92){
						   un_emp = 0.20;
						 }else if(totalSeats > 92){
						   un_emp = 0.25;
						 }
						 
					 }
					 else if(totalSeats <= 175){
					   if(totalSeats <= 127){
						   un_emp = 0.30;
						 }else if(totalSeats <= 139){
						   un_emp = 0.35;
						 }else if(totalSeats <= 151){
						   un_emp = 0.40;
						 }else if(totalSeats <= 163){
						   un_emp = 0.45;
						 }else if(totalSeats > 163){
						   un_emp = 0.50;
						 }
					 }
					 else if(totalSeats <= 275){
					   if(totalSeats <= 195){
						   un_emp = 0.55;
						 }else if(totalSeats <= 215){
						   un_emp = 0.60;
						 }else if(totalSeats <= 235){
						   un_emp = 0.65;
						 }else if(totalSeats <= 255){
						   un_emp = 0.70;
						 }else if(totalSeats > 255){
						   un_emp = 0.75;
						 }
					 }
					 else if(totalSeats > 275){
					  if(totalSeats <= 290){
						   un_emp = 0.80;
						 }else if(totalSeats <= 305){
						   un_emp = 0.85;
						 }else if(totalSeats <= 320){
						   un_emp = 0.90;
						 }else if(totalSeats <= 335){
						   un_emp = 0.95;
						 }else if(totalSeats > 335){
						   un_emp = 1;
						 }
					 }
 			    }
			    callModiMeter();
			//getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
			generateMapForTgPCTotal();
			$( "#processingDialogue" ).dialog('close');
		 }catch(e){
		   $( "#processingDialogue" ).dialog('close');
		 }
		});	
	}
	
	function generateMapForTgPCTotal()
	{
	   
		areatype = "totPc";
		document.getElementById('weathermap3').innerHTML = "<div id='map3'  style='height: 900px; border: 1px solid rgb(51, 51, 51); border-radius: 10px; position: relative; background: none repeat scroll 0% 0% rgb(255, 255, 255);width:956px;margin-top:20px;'></div>"
		map3 = L.map('map3', {
		center: [20.0000,81.0000],
		zoom: 5
		});
		map3.dragging.disable();
		map3.touchZoom.disable();
		map3.doubleClickZoom.disable();
		map3.scrollWheelZoom.disable();
		L.geoJson(campus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(map3); 
		$("#stateAjaxImg3").css("display","none");
	}

	function fillColour(partyName,layer,popupContent)
	{
		if(partyName == 'INC')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#66ccff'
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'TDP')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#FFCC00'
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'TRS')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#FF66CC' 
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'BJP')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#ff6600'
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'AIMIM')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#006600'
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'CPM' || partyName == 'CPI')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#CC0000'
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'LSP')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#0066FF'
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'PRP')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#B22222'
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'YSRC')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#99FF66'
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'NDA')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#934500'
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'UPA')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#004276'
			});
			//layer.bindPopup(popupContent);
		}
		else if (partyName == 'OTHERS')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#7C7C00'
			});
			//layer.bindPopup(popupContent);
		}
		else
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#666666'
			});
			//layer.bindPopup(popupContent);
		} 
	}

function getRegionWiseResults(searchType)
{
    ajaxProcessing();
	if(searchType == 'Telangana')
	{
	    //makeClickedFalse(1);
		$('#effectClrImg').hide();
		getElectionResultForAssemblyPrevious(2,"first",1,2);
		getElectionResultForParlimentPresent(2,"second",2,2);
		telanganaDistrict();
		parliamentWiseResult('Telangana');
		telanganaRegion();
		$('#results1Div,#subTitlesDiv').show();
		$('#liveResultsDiv').hide();
		$('#overviewDivId3').show();
		$('#mapDiv').show();
		$('#legend').show();
		$('#bannerDiv').show();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#div_sld1_img').hide();
		$('#slider3').hide();
		$('.parliamentCls').hide();
		$('#weathermap4').hide();
		$('#indiaBannerId').hide();
		$('#andhraImageDiv').hide();
		$('#telanganaImageDiv').show();
		$('#districtWiseAnalysis').hide();
		$('#legend').css("margin-top","-85px");	
		
		$('#subTitlesDiv').html('');
		$('#results1Div').html('');
		var subMenu ='';
		subMenu = '<h2 style="font-family: times new roman,serif,sans-serif;font-size:16px;font-size:25px;font-weight: 500;"> Telangana Election Results - 2014 </h2>';
		
			subMenu +='<ul class="nav nav-pills" style="font-weight: 500;">';
			subMenu +='	<li style="margin-top:10px;color:#ADADAD;"> Filter Options : </li> ';
			subMenu +='	<li class="active btnCls btnCls1">';
			subMenu +='	<span style="display:none;"> <button id="seemandraStateId" onclick="buildTelanganaStateWiseResult(\'AllTelangana\',\'btnCls1\');" class="btn btn-info" style="margin-left:20px;"> electin reslts</button> </span>';
			subMenu +='	<a style="margin-left:20px;" href="javascript:{buildTelanganaStateWiseResult(\'AllTelangana\',\'btnCls1\');}"> State wise Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li  class="btnCls btnCls2">';
			subMenu +='	<a style="margin-left:20px;" href="javascript:{buildTelanganaStateWiseResult(\'TParliament\',\'btnCls2\');}"> Parliament wise  Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li  class="btnCls btnCls4"><a style="margin-left:20px;" href="javascript:{buildTelanganaStateWiseResult(\'NorthTelangana\',\'btnCls4\');}">  North Telangana  </a></li>';
			subMenu +='	<li  class="btnCls btnCls3"><a style="margin-left:20px;" href="javascript:{buildTelanganaStateWiseResult(\'SouthTelangana\',\'btnCls3\');}"> South Telangana </a></li>';


			subMenu +='</ul>';
			//subMenu +=' <span style="margin-left:600px" id="constiLists"> <input type="text"  id="searchNameId"/> <input type="button" value="Search" onclick="searchByConstituencyName()" style="width:75px;height:30px;margin-top:-10px" class="btn btn-primary"/> </span>'
			
			subMenu +=' <span style="margin-left:600px" >  Search :  <select id="constiLists" onchange="searchByConstituencyName();" style="padding: 5px; height: 28px;"> </select>  <img id="stateAjaxImgg1" src="./images/icons/search.gif" alt="Processing Image" style="display:none;"/></span>'
			
			
		$('#subTitlesDiv').html(subMenu);
		$('#weathermap5').hide();
	}
	else if(searchType == 'Semandhra')
	{
	    $('#effectClrImg').hide();
		getElectionResultForAssemblyPrevious(1,"first",1,2);
		getElectionResultForParlimentPresent(1,"second",2,2);
		seemandraDistrict();
		seemandraRegion();
		parliamentWiseResult('Semandhra');
		$('#overviewDivId3').show();
		$('#results1Div,#subTitlesDiv').show();
		$('#liveResultsDiv').hide();
		$('#bannerDiv').show();
		$('#mapDiv').show();
		$('#legend').show();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#div_sld1_img').hide();
		$('#slider3').hide();
		$('.parliamentCls').hide();
		$('#weathermap4').hide();
		$('#indiaBannerId').hide();
		$('#results1Div').html('');
		$('#subTitlesDiv').html('');
		$('#andhraImageDiv').show();
		$('#telanganaImageDiv').hide();
		$('#districtWiseAnalysis').hide();
		$('#legend').css("margin-top","-85px");	
		var subMenu ='';
		
		subMenu = '<h2 style="font-family:Georgia,Times;font-size:16px;font-size:25px;"> Andhra Pradesh Election Results - 2014 </h2>';
		
			subMenu +='<ul class="nav nav-pills" style="font-weight: 500;">';
			subMenu +='	<li style="margin-top:10px;color:#ADADAD;"> Filter Options : </li> ';
			subMenu +='	<li class="active btnCls btnCls1">';
			subMenu +='	<span style="display:none;"> <button id="seemandraStateId" onclick="buildTelanganaStateWiseResult(\'AllSemandhra\',\'btnCls1\');" class="btn btn-info" style="margin-left:20px;"> electin reslts</button> </span>';
			subMenu +='	<a style="margin-left:20px;" href="javascript:{buildTelanganaStateWiseResult(\'AllSemandhra\',\'btnCls1\');}"> State Election Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li  class="btnCls btnCls2">';
			subMenu +='	<a style="margin-left:20px;" href="javascript:{buildTelanganaStateWiseResult(\'SParliament\',\'btnCls2\');;}"> Parliament wise  Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li  class="btnCls btnCls3"><a style="margin-left:20px;" href="javascript:{buildTelanganaStateWiseResult(\'NorthSemandhra\',\'btnCls3\');}"> North Andhra </a></li>';
			subMenu +='	<li  class="btnCls btnCls4"><a style="margin-left:20px;" href="javascript:{buildTelanganaStateWiseResult(\'SouthSemandhra\',\'btnCls4\');}"> South Andhra </a></li>';
			subMenu +='	<li  class="btnCls btnCls5"><a style="margin-left:20px;" href="javascript:{buildTelanganaStateWiseResult(\'Rayalaseema\',\'btnCls5\');}"> Rayalaseema </a></li>';
			subMenu +='</ul>';
			//subMenu +=' <span style="margin-left:600px" id="constiLists"> <input type="text"  id="searchNameId"/> <input type="button" value="Search" onclick="searchByConstituencyName()" style="width:75px;height:30px;margin-top:-10px" class="btn btn-primary"/> </span>'
			
			subMenu +=' <span style="margin-left:600px" > Search : <select id="constiLists" onchange="searchByConstituencyName();" style="padding: 5px; height: 28px;"> </select> <img id="stateAjaxImgg1" src="./images/icons/search.gif" alt="Processing Image" style="display:none;"/> </span>'
			
		$('#subTitlesDiv').html(subMenu);
		$('#weathermap5').hide();

	}
	else if(searchType == 'India')
	{
		$('#overviewDivId3').hide();
	    $('#effectClrImg').hide();
		$('#results1Div,#subTitlesDiv').html('');
		$('#results1Div,#subTitlesDiv').css('display','none');
		$('#bannerDiv').hide();
		$(".parliamentCls").show();
		$('#liveResultsDiv').hide();
		$('#mapDiv').hide();
		$('#legend').hide();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#div_sld1_img').hide();
		$('#slider3').hide();
		$('#weathermap4').hide();
		$('#indiaBannerId').hide();
		$('#andhraImageDiv').hide();
		$('#telanganaImageDiv').hide();
		$('#districtWiseAnalysis').hide();
		$('#weathermap5').show();
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		parties.push(1117);
		buildPartyWiseResultForParliment(1,260,parties,1);
	}
	else if(searchType == 'StateAnalysis')
	{
		$('#overviewDivId3').hide();
	    $('#effectClrImg').hide();
		$('#results1Div,#subTitlesDiv').html('');
		$('#results1Div,#subTitlesDiv').css('display','none');
		$('#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId,#errorDiv,#test,#constituencyResultsDiv').html('');
		$('#bannerDiv').show();
		$('#liveResultsDiv').show();
		$('#mapDiv').hide();
		$('#legend').hide();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#div_sld1_img').hide();
		$('#slider3').hide();
		$('.parliamentCls').hide();
		$('#weathermap4').hide();
		$('#indiaBannerId').hide();
		$('#andhraImageDiv').hide();
		$('#telanganaImageDiv').hide();
		$('#districtWiseAnalysis').hide();
		getLocationDetailsForSelectedScope1();
		$('#weathermap5').hide();
		
	}
	else if(searchType == 'DistrictAnalysis')
	{
		$('#overviewDivId3').hide();
	    $('#effectClrImg').hide();
		$('#results1Div,#subTitlesDiv').html('');
		$('#results1Div,#subTitlesDiv').css('display','none');
		$('#bannerDiv').hide();
		$('#liveResultsDiv').hide();
		$('#mapDiv').hide();
		$('#legend').hide();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#div_sld1_img').hide();
		$('#slider3').hide();
		$('.parliamentCls').hide();
		$('#weathermap4').hide();
		$('#indiaBannerId').hide();
		$('#andhraImageDiv').hide();
		$('#telanganaImageDiv').hide();
		$('#weathermap5').hide();
		$('#districtWiseAnalysis').show();
		 getAllStates();
		$( "#processingDialogue" ).dialog('close');
	}
	else if(searchType == 'CBNEffect')
	{
		$('#overviewDivId3').hide();
	    $('#effectClrImg').hide();
	    $("#modiDiv").show();
		$('#results1Div,#subTitlesDiv').html('');
		$('#results1Div,#subTitlesDiv').css('display','none');
		$('#liveResultsDiv').hide();
		$('#bannerDiv').show();
		$('#mapDiv').show();
		$('#legend').show();
		$('#areaSelectionDiv').show();
		$('#stateSelectDiv').show();
		$('#submitButtionDiv').show();
		//$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#div_sld1_img').hide();
		$('#slider3').hide();
		$('#weathermap4').show();
		$('#districtWiseAnalysis').hide();
		var parties = new Array();
		parties.push(872);
		getParliments(1,258,parties,2);
		$('.parliamentCls').hide();
		$('#indiaBannerId').hide();
		$('#andhraImageDiv').hide();
		$('#telanganaImageDiv').hide();
		$('#legend').css("margin-top","-20px");
		$('#weathermap5').hide();		
		$('#stateId').val(0);
		$('#levelId1').val(0);
		$('#yearId1').val(0);
		$('#levelId2').val(0),
		$('#yearId2').val(0);
		$('#overviewDivId2').html('');
		$('#overviewDivId1').html('');
		$('#weathermap').html('');
		$('#weathermap1').html('');

		//sravanthi code
		$('#modiDiv').show();
		$("#unemp_chart1").show();
		$("#unemp_chart1_img").show();
		$(".div_sld1").show();
		$('.div_sld1_img').show();
		$("#unemp_chart").hide();
		$("#unemp_chart_img").hide();
		$(".div_sld").hide();
		$(".div_sld_img").hide();
		$("#results1Div").hide();
		$("#unemp_chart1").html("");
		$("#unemp_chart").html("");
	}
	else if(searchType == 'ModiEffect')
	{
		$('#overviewDivId3').hide();
	    $('#effectClrImg').show();
	    $("#modiDiv").show();
		$('#results1Div,#subTitlesDiv').html('');
		$('#results1Div,#subTitlesDiv').css('display','none');
		$('#indiaBannerId').show();
		$('#bannerDiv').hide();
		$('.parliamentCls').hide();
		$('#liveResultsDiv').hide();
		$('#mapDiv').hide();
		$('#legend').hide();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		//$('#modiDiv').hide();
		$('#weathermap3').show();
		$('#unemchart1p_').show();
		$('#div_sld1').show();
		$('#div_sld1_img').show();
		$('#slider3').show();
		//getElectionResultForTotalParliment();
		$('#weathermap4').hide();
		var parties = new Array();
		parties.push(163);
		getParliments(1,260,parties,1);
		$('#andhraImageDiv').hide();
		$('#telanganaImageDiv').hide();
		$('#weathermap5').hide();
		$('#districtWiseAnalysis').hide();

		//sravanthi code
		$('#modiDiv').show();
		$("#unemp_chart").show();
		$("#unemp_chart_img").show();
		$(".div_sld").show();
		$(".div_sld_img").show();
		$("#results1Div").hide();
		$("#unemp_chart1").hide();
		$("#unemp_chart1_img").hide();
		$(".div_sld1").hide();
		$(".div_sld1_img").hide();
		$("#unemp_chart1").html("");
		$("#unemp_chart").html("");
	}
	}
    function makeClickedFalse(clicked){  
		telanganaTabClck = false;
		seemAndraTabClck = false;
		indiaTabClck = false;
		stateAnalysTabClck = false;
		distAnalysTabClck = false;
		if(clicked == 1){
			telanganaTabClck = true;
		}else if(clicked == 2){
			seemAndraTabClck = true;
		}else if(clicked == 3){
			indiaTabClck = true;
		}else if(clicked == 4){
			stateAnalysTabClck = true;
		}else if(clicked == 5){
			distAnalysTabClck = true;
		}
	}
	function buildPartyWiseResultForParliment(stateId,electionId,parties,electionScopeId)
	{
		//$("#stateAjaxImg5").css("display","block");
		var jsObj=
		{
				electionId : stateId,
				stateId : electionId,
				electionScopeId : electionScopeId,
				parties : parties,
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "cbnOrModiEffectAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		 error:function() { 
	           $( "#processingDialogue" ).dialog('close');
	         }
		})
		.done(function( result ) {
			psDetails = result;
			try{
			 getElectionResultForTotalParliment123();
			}catch(e){
			  $( "#processingDialogue" ).dialog('close');
			}
		});
	}
	function getElectionResultForTotalParliment123()
	{
		
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		parties.push(1117);
		var jsObj=
		{
				electionId : 260,
				stateId : 1,
				electionScopeId : 1,
				parties : parties,
				scope : "pc",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
		  try{
			electionPcData = result;
			//getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
			generateMapForTgPCTotal123();
			$( "#processingDialogue" ).dialog('close');
			}catch(e){
			  $( "#processingDialogue" ).dialog('close');
			}
		});	
	}
	
	function generateMapForTgPCTotal123()
	{

		areatype = "indpc";
		document.getElementById('weathermap5').innerHTML = "<h2 class='offset3'> 2014 Parliament Election Result Overview </h2><img class='offset4' src='images/specialPage/parlicolorcodes.png'> <div id='map5'   style='height: 900px; border: 1px solid rgb(51, 51, 51); border-radius: 10px; position: relative; background: none repeat scroll 0% 0% rgb(255, 255, 255);width:956px;margin-top:20px;'></div>"
		map5 = L.map('map5', {
		center: [20.0000,81.0000],
		zoom: 5
		});
		map5.dragging.disable();
		map5.touchZoom.disable();
		map5.doubleClickZoom.disable();
		map5.scrollWheelZoom.disable();

		L.geoJson(campus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(map5); 
		
		$("#stateAjaxImg5").css("display","none");
	}
	var psDetails ;
	function getParliments(stateId,electionId,parties,electionScopeId)
	{
		var jsObj=
		{
				electionId : stateId,
				stateId : electionId,
				electionScopeId : electionScopeId,
				parties : parties,
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "cbnOrModiEffectAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
	          error:function() { 
	           $( "#processingDialogue" ).dialog('close');
	         }
		})
		.done(function( result ) {
		  try{
			psDetails = result;
			if(electionScopeId == 1)
			{
				
				$(".d3-slider-handle").attr("style","left:"+sliderModiWave+"%");
				getElectionResultForTotalParliment();
			}
			
			else
			{
			     un_emp1 = 0.0;
				 if(result != null && result.length > 0){ 
				     var totalSeats = result[0].totalCount;			  
					 if(totalSeats <= 55){
						 if(totalSeats <= 0){
						  un_emp1 = 0.0;
						 }else if(totalSeats <= 11){
						   un_emp1 = 0.05;
						 }else if(totalSeats <= 22){
						   un_emp1 = 0.10;
						 }else if(totalSeats <= 33){
						   un_emp1 = 0.15;
						 }else if(totalSeats <= 44){
						   un_emp1 = 0.20;
						 }else if(totalSeats > 44){
						   un_emp1 = 0.25;
						 }
						 
					 }
					 else if(totalSeats <= 85){
					   if(totalSeats <= 61){
						   un_emp1 = 0.30;
						 }else if(totalSeats <= 67){
						   un_emp1 = 0.35;
						 }else if(totalSeats <= 73){
						   un_emp1 = 0.40;
						 }else if(totalSeats <= 79){
						   un_emp1 = 0.45;
						 }else if(totalSeats > 79){
						   un_emp1 = 0.50;
						 }
					 }
					 else if(totalSeats <= 110){
					   if(totalSeats <= 92){
						   un_emp1 = 0.55;
						 }else if(totalSeats <= 99){
						   un_emp1 = 0.60;
						 }else if(totalSeats <= 106){
						   un_emp1 = 0.65;
						 }else if(totalSeats <= 113){
						   un_emp1 = 0.70;
						 }else if(totalSeats > 113){
						   un_emp1 = 0.75;
						 }
					 }
					 else if(totalSeats > 110){
					  if(totalSeats <= 116){
						   un_emp1 = 0.80;
						 }else if(totalSeats <= 122){
						   un_emp1 = 0.85;
						 }else if(totalSeats <= 128){
						   un_emp1 = 0.90;
						 }else if(totalSeats <= 134){
						   un_emp1 = 0.95;
						 }else if(totalSeats > 134){
						   un_emp1 = 1;
						 }
					 }
					}
					 $(".d3-slider-handle1").attr("style","left:"+sliderCbnWave+"%");
					 callCBNMeter();
				getElectionResultForTotalAssembly(0);
			}
		  }catch(e){
		    $( "#processingDialogue" ).dialog('close');
		  }
		});	
	}
	
	function getElectionResultForTotalAssembly(level)
	{
	  if(level == 1){
	   $("#stateAjaxImg4").css("display","block");
	  }
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		parties.push(1117);
		var jsObj=
		{
				electionId : 258,
				stateId : 1,
				electionScopeId : 2,
				parties : parties,
				scope : "ac",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
		  try{
			var resArr = new Array();
		    for(var i in result){
			
			  if(result[i].selectedCasteDetails != null && result[i].selectedCasteDetails.length > 0 &&  result[i].selectedCasteDetails[0].name =="TDP"){
			   resArr.push(result[i]);
			  }
			}
			electionAcData = resArr;
			 if(level == 0){
			   getMapForAssembly();
			   }
			   $("#stateAjaxImg4").css("display","none");
			$( "#processingDialogue" ).dialog('close');
		  }catch(e){
		   $( "#processingDialogue" ).dialog('close');
		  }
		});	
	}
		function getMapForAssembly()
		{
	
			areatype = "totac";
			document.getElementById('weathermap4').innerHTML = "<div  id='map4'  style='height: 400px; border: 1px solid rgb(51, 51, 51); border-radius: 10px; position: relative; background: none repeat scroll 0% 0% rgb(255, 255, 255); width:956px;margin-left:auto;margin-right:auto;''></div>"
			map4 = L.map('map4', {
			center: [16.0000,80.0000],
			zoom: 6.4
			});
			map4.dragging.disable();
			map4.touchZoom.disable();
			map4.doubleClickZoom.disable();
			map4.scrollWheelZoom.disable();
			
			if($('#stateId option:selected').val() == 1 || $('#stateId option:selected').val() == 0 )
			{
				L.geoJson(apaccampus, {

				style: function (feature) {
					return feature.properties && feature.properties.style;
				},
				
				onEachFeature: onEachFeature,

				pointToLayer: function (feature, latlng) {
					return L.circleMarker(latlng, {
						
					});
				}
				}).addTo(map4); 
		    }
			else
			{
				L.geoJson(tgaccampus, {

				style: function (feature) {
					return feature.properties && feature.properties.style;
				},
				
				onEachFeature: onEachFeature,

				pointToLayer: function (feature, latlng) {
					return L.circleMarker(latlng, {
						
					});
				}
			}).addTo(map4); 
			}

			
		$("#stateAjaxImg4").css("display","none");
		}

	
	<!-- From SASI -->
	
	getStateWideParliamentsSummary();
	getPartyWiseWonLeadCountInLive();

	function getStateWideParliamentsSummary(){
		var jsObj={};
		$.ajax(
		  {
				type: "POST",
				url:"getStateWideParliamentLiveResults.action",
				data:{task :JSON.stringify(jsObj)}
		  }
		  ).done(function(result){
				buildStateWideParliaments(result);
		  });
	}

	function getPartyWiseWonLeadCountInLive(){
		var jsObj={};
		$.ajax(
		  {
				type: "POST",
				url:"getPartyWiseWonLeadCountInLive.action",
				data:{task :JSON.stringify(jsObj)}
		  }
		  ).done(function(result){
				buildPartyWideWonLeadCount(result);
		  });
	}
	
	function buildPartyWideWonLeadCount(results){
	if(results.statesList.length>0){
	var str = "";
		str += "<h2 class='offset2' style='margin-bottom:5px;margin-top:10px;color:#27AFA6;'>Party Wise Won/Lead Counts</h2>";
		str +="<table class='parlResultTable offset1' width='500' cellspacing='0' cellpadding='2' border='0'>";
		str +="<tbody style='font-family: Tahoma;font-size: 12px;'>";
			str+="<tr style='font-weight:bold;color:black;vertical-align:bottom;border-bottom:1px solid #004276;'>";
				str+="<td style='border-bottom:1px solid #004276;'> PARTY </td>";
				str+="<td style='border-bottom:1px solid #004276;'> ALLIANCE </td>";
				str+="<td style='border-bottom:1px solid #004276;'> WON </td>";
				<!--str+="<td style='border-bottom:1px solid #004276;'> LEAD </td>";-->
			str+="</tr>";
			for(var i in results.statesList){
				str+="<tr class='bodyRows' style='color:black;'>";
					var path = "images/party_flags/"+results.statesList[i].party+"01.jpg";
					
					str+="<td style='border-bottom:1px solid #004276;height:25px;'><img src="+path+" alt="+results.statesList[i].party+"></td>";
					str+="<td style='border-bottom:1px solid #004276;'>"+results.statesList[i].allianceGroup+"</td>";
					if(results.statesList[i].partyWonCount == null){
						str+="<td style='border-bottom:1px solid #004276;'> - </td>";
					}else{
						str+="<td style='border-bottom:1px solid #004276;'>"+results.statesList[i].partyWonCount+"</td>";
					}
					/*if(results.statesList[i].partyLeadCount == null){
						str+="<td style='border-bottom:1px solid #004276;'> - </td>";
					}else{
						str+="<td style='border-bottom:1px solid #004276;'>"+results.statesList[i].partyLeadCount+"</td>";
					}*/
				str+="</tr>";
			}
			
		str+="</tbody>";
	str+="</table>";
	
	$(".partyWiseResultDiv").html(str);
	}
	
}

function buildStateWideParliaments(results){
	var str = "";
	str +="<table class='parlResultTable offset1' width='800' cellspacing='0' cellpadding='2' border='0'>";
		str +="<tbody style='font-family: Tahoma;font-size: 12px;'>";
			str +="<tr>";
			str +="</tr>";
			str +="<tr><td bgcolor='#AACAEA' style='padding: 0px;' colspan='14'></td></tr>";
			str +="<tr>";
				str +="<td rowspan='3' style='font-weight:bold;color:black;vertical-align:bottom;border-bottom:1px solid #004276;'>STATE</td>";
				str +="<td rowspan='3' align='center' style='font-weight:bold;color:black;vertical-align:bottom;border-bottom:1px solid #004276;'>TOTAL</td>";
				str +="<td colspan='6' style='font-weight:bold;color:black;text-align:center;border-bottom:1px solid #004276;border-bottom-width:1px;'>2014</td>";
				str +="<td width='4%'> </td>";
				str +="<td colspan='4' style='font-weight:bold;color:black;text-align:center;border-bottom:1px solid #004276;border-bottom-width:1px;'>2009</td>";
			str +="</tr>";
			
			
			str +="<tr>";
				
				str +="<td colspan='2' align='center' style='font-weight:bold;color:black;border-bottom:1px solid #004276;background:#f2be8e;border-right:1px solid #934500;border-right-width:2px;border-left:1px solid #934500;border-left-width:2px;'>NDA</td>";
				str +="<td colspan='2' align='center' style='font-weight:bold;color:black;border-bottom:1px solid #004276;background:#8dbfa0;'>UPA</td>";
				str +="<td colspan='2' align='center' style='font-weight:bold;color:black;border-bottom:1px solid #004276;background:#f4f4a4;border-right:1px solid #7c7c00;border-right-width:2px;border-left:1px solid #7c7c00;border-left-width:2px;'>OTHERS</td>";
				str +="<td width='4%'> </td>";
				str +="<td  align='center' style='font-weight:bold;color:black;border-bottom:1px solid #004276;border-left:1px solid #934500;border-left-width:2px;border-right:1px solid #934500;border-right-width:2px;background:#f2be8e;'>NDA</td>";
				str +="<td  align='center' style='font-weight:bold;color:black;border-bottom:1px solid #004276;background:#8dbfa0'>UPA</td>";
				str +="<td  align='center' style='font-weight:bold;color:black;border-bottom:1px solid #004276;background:#f4f4a4;border-right:1px solid #7c7c00;border-right-width:2px;border-left:1px solid #7c7c00;border-left-width:2px;'>OTHERS</td>";
			str +="</tr>";
			str +="<tr style='color:black;'>";
				str +="<td align='right' style='background:#f2be8e;border-bottom:1px solid #004276;border-left:1px solid #934500;border-left-width:2px;'>WON</td>";
				str +="<td align='right' style='background:#f2be8e;border-bottom:1px solid #004276;border-right:1px solid #934500;border-right-width:2px;'>LEAD</td>";
				str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #004276;'>WON</td>";
				str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #004276;'>LEAD</td>";
				str +="<td align='right' style='background:#f4f4a4;border-bottom:1px solid #004276;border-left:1px solid #7c7c00;border-left-width:2px;'>WON</td>";
				str +="<td align='right' style='background:#f4f4a4;border-bottom:1px solid #004276;border-right:1px solid #7c7c00;border-right-width:2px;'>LEAD</td>";
				str +="<td width='4%'> </td>";
				str +="<td align='right' style='background:#f2be8e;border-bottom:1px solid #004276;border-right-width:2px;border-left:1px solid #934500;border-left-width:2px;border-right:1px solid #934500;border-right-width:2px;'>WON</td>";
				str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #004276;'>WON</td>";
				str +="<td align='right' style='background:#f4f4a4;border-bottom:1px solid #004276;border-right:1px solid #7c7c00;border-right-width:2px;border-left:1px solid #7c7c00;border-left-width:2px;'>WON</td>";
			str +="</tr>";
			
		
			
			for(var i in results.statesList){
				
			str +="<tr class='bodyRows' style='color:black;'>";
				str +="<td align='left' style='color:#000066;border-bottom:1px solid #004276;border-bottom-width:1px;'>"+results.statesList[i].state+"</td>";
				str +="<td align='center' style='border-bottom:1px solid #004276;border-bottom-width:1px;' >"+results.statesList[i].statesTotalCount+"</td>";
				if(results.statesList[i].ndaWonCount !=  null){
					str +="<td align='right' style='background:#f2be8e;border-left:1px solid #934500;border-left-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'>"+results.statesList[i].ndaWonCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f2be8e;border-left:1px solid #934500;border-left-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'> 0 </td>";
				}
				if(results.statesList[i].ndaLeadCount !=  null){
					str +="<td align='right' style='background:#f2be8e;border-right:1px solid #934500;border-right-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'>"+results.statesList[i].ndaLeadCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f2be8e;border-right:1px solid #934500;border-right-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'> 0 </td>";
				}
				if(results.statesList[i].upaWonCount !=  null){
					str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #004276;border-bottom-width:1px;'>"+results.statesList[i].upaWonCount+"</td>";
				}else{
					str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #004276;border-bottom-width:1px;'> 0 </td>";
				}
				if(results.statesList[i].upaLeadCount !=  null){
					str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #004276;border-bottom-width:1px;'>"+results.statesList[i].upaLeadCount+"</td>";
				}else{
					str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #004276;border-bottom-width:1px;'> 0 </td>";
				}
				
				if(results.statesList[i].othersWonCount !=  null){
					str +="<td align='right' style='background:#f4f4a4;border-left:1px solid #7c7c00;border-left-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'>"+results.statesList[i].othersWonCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f4f4a4;border-left:1px solid #7c7c00;border-left-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'> 0 </td>";
				}
				
				if(results.statesList[i].othersLeadCount !=  null){
					str +="<td align='right' style='background:#f4f4a4;border-right:1px solid #7c7c00;border-right-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'>"+results.statesList[i].othersLeadCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f4f4a4;border-right:1px solid #7c7c00;border-right-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'> 0 </td>";
				}
				
				str +="<td width='4%'> </td>";
				
				if(results.statesList[i].ndaAlliancesCount !=  null){
					str +="<td align='right' style='background:#f2be8e;border-left:1px solid #934500;border-left-width:2px;border-right:1px solid #934500;border-right-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'>"+results.statesList[i].ndaAlliancesCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f2be8e;border-left:1px solid #934500;;border-left-width:2px;border-right:1px solid #934500;border-right-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'> 0 </td>";
				}
				
				if(results.statesList[i].upaAlliancesCount !=  null){
					str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #004276;border-bottom-width:1px;'>"+results.statesList[i].upaAlliancesCount+"</td>";
				}else{
					str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #004276;border-bottom-width:1px;'> 0 </td>";
				}
				
				if(results.statesList[i].othersCount !=  null){
					str +="<td align='right' style='background:#f4f4a4;border-right:1px solid #7c7c00;border-right-width:2px;border-left:1px solid #7c7c00;border-left-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'>"+results.statesList[i].othersCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f4f4a4;border-right:1px solid #7c7c00;border-right-width:2px;border-left:1px solid #7c7c00;border-left-width:2px;border-bottom:1px solid #004276;border-bottom-width:1px;'> 0 </td>";
				}
			str +="</tr>";
			
			}
			
			str +="<tr><td bgcolor='#AACAEA' style='padding: 0px;' colspan='12'><img width='1' height='1' src='images/specialPage/spacer.gif'></td></tr>";
			str+="<tr style='font-weight:bold;color:black;'>";
				str +="<td>Totals</td>";
				str +="<td align='center'>"+results.overallStatesCount+"</td>";
				str +="<td align='right'>"+results.ttlNdaWonCount+"</td>";
				str +="<td align='right'>"+results.ttlNdaLeadCount+"</td>";
				str +="<td align='right'>"+results.ttlUpaWonCount+"</td>";
				str +="<td align='right'>"+results.ttlUpaLeadCount+"</td>";
				str +="<td align='right'>"+results.ttlOthersWonCount+"</td>";
				str +="<td align='right'>"+results.ttlOthersLeadCount+"</td>";
				str +="<td width='4%'> </td>";
				str +="<td align='right'>"+results.overAllNdaCount+"</td>";
				str +="<td align='right'>"+results.overAllUpaCount+"</td>";
				str +="<td align='right'>"+results.overAllOthersCount+"</td>";
			str+="</tr>";
			str +="<tr><td bgcolor='#AACAEA' style='padding: 0px;' colspan='12'><img width='1' height='1' src='images/specialPage/spacer.gif'></td></tr>";
		str +="</tbody>";
	str +="</table>";
	
	$(".parliamentResultsDiv").html(str);
}

var marginDetails =	{
			electionId : '',
			type:'',
			locationIds:[]
		}
		
function getMarginsCountOfParties(){

		marginDetails.electionId = $('#electionId').val();
		marginDetails.type = $('#scopeId').val();

		if($('#locaionsId1').val() == 0)
		{
         $('#locaionsId1 option').each(function(index,value){
			 marginDetails.locationIds.push(this.value);
		 })
		}else
		marginDetails.locationIds = $('#locaionsId1').val();

		
		$.ajax({
				type: "POST",
				url:"getMarginAnalysisOnLiveResultsForAssemblies.action",
				data:{task :JSON.stringify(marginDetails)}
		  }).done(function(result){
				if(result.partiesList != null && result.partiesList.length>0){
					buildPartyWiseMarginCount(result);
				}
		  });
}

function buildPartyWiseMarginCount(result){
	var str = '';
	str+='<label style="margin-bottom:3px;"  class="headingClass">Party Wise Margin Analysis</label>';

	str+='<div class="tableClass1">';
	str+='<table width="80%" class="" style="clear:both;" style="margin-top:25px;">';
	// str += "<table width='800' cellspacing='0' cellpadding='2' border='0'>";
		str +="<tbody>";
			str +="<tr>";
				str+="<td>PARTY</td>";
				for(var i in result.partiesList[0].marginsVO){
					str+="<td>"+result.partiesList[0].marginsVO[i].margin+"</td>";
				}
			str +="</tr>";
		
			for(var i in result.partiesList){
			str +="<tr>";
				str +="<td>"+result.partiesList[i].partyName+"</td>";
				for(var j in result.partiesList[i].marginsVO){
					
						if(result.partiesList[i].marginsVO[j].count == null){
							str+="<td> - </td>";
						}else{
							str+="<td>"+result.partiesList[i].marginsVO[j].count+"</td>";
						}
				
				}
			str +="</tr>";
			}
		str +="</tbody>";
	str += "</table>";
	str+='</div>';
	
	$("#marginAnalysis1").html(str);
}

var matrixReportDtls1={
		electionId:'',
	    scopeId:'',
	    locationIds:[]
			
	};
	function telanganaRegion()
	{
	    matrixReportDtls1.electionId = '';
	    matrixReportDtls1.scopeId = '';
	    matrixReportDtls1.locationIds = [];
		
		matrixReportDtls1.electionId = 258;
		matrixReportDtls1.scopeId = 3;//region
		
		matrixReportDtls1.locationIds.push(1);

		$.ajax({
	          type:'POST',
	          url: 'getWonAndLeadCountPartyWise.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(matrixReportDtls1)},

	          success: function(result){ 
				 buildSummaryReportResult(result,"region");
	         },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
	}

	function seemandraRegion()
	{
	    matrixReportDtls1.electionId = '';
	    matrixReportDtls1.scopeId = '';
	    matrixReportDtls1.locationIds = [];
		
		matrixReportDtls1.electionId = 258;
		matrixReportDtls1.scopeId = 3;//region
		
		matrixReportDtls1.locationIds.push(2);
		matrixReportDtls1.locationIds.push(3);

		$.ajax({
	          type:'POST',
	          url: 'getWonAndLeadCountPartyWise.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(matrixReportDtls1)},

	          success: function(result){ 
				   buildSummaryReportResult(result,"region");
	         },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
	}

	function telanganaDistrict()
	{
	  matrixReportDtls1.electionId = '';
	    matrixReportDtls1.scopeId = '';
	    matrixReportDtls1.locationIds = [];

		matrixReportDtls1.electionId = 258;
		matrixReportDtls1.scopeId = 2;//region
		
		matrixReportDtls1.locationIds.push(1);
		matrixReportDtls1.locationIds.push(2);
		matrixReportDtls1.locationIds.push(3);
		matrixReportDtls1.locationIds.push(4);
	    matrixReportDtls1.locationIds.push(5);
		matrixReportDtls1.locationIds.push(6);
		matrixReportDtls1.locationIds.push(7);
		matrixReportDtls1.locationIds.push(8);
		matrixReportDtls1.locationIds.push(9);
	    matrixReportDtls1.locationIds.push(10);

		

		$.ajax({
	          type:'POST',
	          url: 'getWonAndLeadCountPartyWise.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(matrixReportDtls1)},

	          success: function(result){ 
				  buildSummaryReportResult(result,"district");
	         },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
	}

	function parliamentWiseResult(type)
	{
	  matrixReportDtls1.electionId = '';
	    matrixReportDtls1.scopeId = '';
	    matrixReportDtls1.locationIds = [];

		matrixReportDtls1.electionId = 260;
		matrixReportDtls1.electionScopeId = 1;
		matrixReportDtls1.scopeId = 4;//region
		
		if(type == 'Telangana'){
			matrixReportDtls1.locationIds.push(3);
			matrixReportDtls1.locationIds.push(4);	
		}
		else{
			matrixReportDtls1.locationIds.push(1);
			matrixReportDtls1.locationIds.push(2);	
			matrixReportDtls1.locationIds.push(5);	
		}

		$.ajax({
	          type:'POST',
	          url: 'getWonAndLeadCountPartyWise.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(matrixReportDtls1)},

	          success: function(result){ 
				  buildSummaryReportparliamentWise(result,type);
	         },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
	}
	
	function buildSummaryReportparliamentWise(result,type)
	{
	  $('#overviewDivId3').html('');
	  var str ='';
	   str+='<h5 style="margin-top: 20px; margin-bottom: 10px; margin-left:-11px" class="headingClass">Parliament Wise Analysis</h5>';
	  
	  str+='<table style=" margin:26px 10px 0 -10px;"  cellpadding="5">';
	   str+='<thead>';
	    str+='<tr>';
		 //str+='<th rowspan="2" class="thBorder"></th>';
		str+='<th class="thBorder"></th>';
		$.each(result,function(index,value){
			str+='<th class="thBorder">'+value.name+'</th>';
		});
		 str+='<th class="thBorder">TOTAL</th>';
		str+='</tr>';
		
		 str+='<tr>';	
		$.each(result,function(index,value){
			//str+='<th class="thBorder">W</th>';
		});
		 	 //str+='<th class="thBorder"></th>';
		str+='</tr>';
	   str+='</thead>';
	  str+='<tbody>';
	  str+='<tr>';
		  str+='<td class="thBorder">'+type+'</td>';
		  var count = 0;
	  $.each(result,function(index,value){
			   str+='<td class="thBorder">'+value.winCount+'</td>';	
			   count = count+ value.winCount;
	  });
	  		  str+='<td class="thBorder">'+count+'</td>';
	    str+='</tr>';
	  str+='<tbody>';
	  str+='</table>';
	  
	  str+='</table>';
	  
	    $('#overviewDivId3').html(str);
	}
	
	function seemandraDistrict()
	{

	    matrixReportDtls1.electionId = '';
	    matrixReportDtls1.scopeId = '';
	    matrixReportDtls1.locationIds = [];
		matrixReportDtls1.electionId = 258;
		matrixReportDtls1.scopeId = 2;//region
		
		matrixReportDtls1.locationIds.push(11);
		matrixReportDtls1.locationIds.push(12);
		matrixReportDtls1.locationIds.push(13);
		matrixReportDtls1.locationIds.push(14);
	    matrixReportDtls1.locationIds.push(15);
		matrixReportDtls1.locationIds.push(16);
		matrixReportDtls1.locationIds.push(17);
		matrixReportDtls1.locationIds.push(18);
		matrixReportDtls1.locationIds.push(19);
	    matrixReportDtls1.locationIds.push(20);
		matrixReportDtls1.locationIds.push(21);
		matrixReportDtls1.locationIds.push(22);
		matrixReportDtls1.locationIds.push(23);


		$.ajax({
	          type:'POST',
	          url: 'getWonAndLeadCountPartyWise.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(matrixReportDtls1)},

	          success: function(result){ 
				   buildSummaryReportResult(result,"district");
	         },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
	}
	function buildSummaryReportResult(result,type)
	{
	  var str ='';
	  
	  if(type == "district")  
	   str+='<h5 style="margin-top: 20px; margin-bottom: 10px; margin-left:-11px;width:400px" class="headingClass">District Wise Analysis</h5>';
	  else
	   str+='<h5 style="margin-top: 20px; margin-bottom: 10px; margin-left:-11px" class="headingClass">Region Wise Analysis</h5>';
	  
	  
	  str+='<table style=" margin:26px 10px 0 -10px;"  cellpadding="5">';
	   str+='<thead>';
	    str+='<tr>';
		// str+='<th rowspan="2" class="thBorder"></th>';
		str+='<th class="thBorder"></th>';
		$.each(result[0].partiesDetails,function(index,value){
		 str+='<th class="thBorder">'+value.name+'</th>';
		});
		 str+='<th class="thBorder">TOTAL</th>';
		str+='</tr>';
		/*
		 str+='<tr>';	
		$.each(result[0].partiesDetails,function(index,value){
		 str+='<th class="thBorder">W</th>';
		 str+='<th class="thBorder">L</th>';
		});
		 
		str+='</tr>';
		*/
	   str+='</thead>';
	  str+='<tbody>';
	  var count =0;

	  $.each(result,function(index,value){
	  
		  str+='<tr>';
		   str+='<td class="thBorder">'+value.name+'</td>';
		   $.each(value.partiesDetails,function(index1,value1){
			   str+='<td class="thBorder">'+value1.winCount+'</td>';
			  // str+='<td class="thBorder">'+value1.leadCount+'</td>';
			  count = value1.winTotalCount;
		   });
		  
		   if(index != result.length-1){
		   
		    str+='<td class="thBorder">'+value.winCount+'</td>';
			
		   	 // console.log(" "+result.length+" ---  "+index);
			  // str+='<td class="thBorder">'+value.winCount+'</td>';
		   }
		   if(index == result.length-1){		   
				str+='<td class="thBorder">'+count+'</td>';
		   }
		  str+='</tr>';  
	  });
	  
	  str+='<tbody>';
	  str+='</table>';
	  
	  str+='</table>';
	  
	  if(type == "region")  
	    $('#overviewDivId1').html(str);
	  else
	    $('#overviewDivId2').html(str);
	}
function getLocationDetailsForSelectedScope1()
{	

var scope = $("#scopeId").val();
	
if(scope == 3)
	{
	$("#selectText").html("Select Region");
	
	}
else if(scope == 2)
	{
	$("#selectText").html("Select District");
	
	}
else if(scope == 4)
	{
	$("#selectText").html("Select Parliament");
	
	}
else if(scope == 5)
	{
		$("#selectText").html("Select Assembly");
	
	}
	
	if(!$("#processingDialogue").is(':visible')) { 
      $("#processImg").css("display","inline-block");
     }
	$('#subReportId').attr('disabled',false); 
    if($('#scopeId').val() == 5)
	{
		$('#subReportId').attr('disabled',true);
	}
    
	locationDtls.task =$('#scopeId :selected').text();
	locationDtls.stateType = $('#stateScope :selected').text();
	
	$.ajax({
          type:'POST',
          url: 'getElectionResultsLocations.action',
          dataType: 'json',
          data: {task:JSON.stringify(locationDtls)},

          success: function(result){ 
			  $("#processImg").css("display","none");
			  $( "#processingDialogue" ).dialog('close');
			   buildLocationDetails(result);
         },
          error:function() { 
		  $( "#processingDialogue" ).dialog('close');
           console.log('error', arguments);
         }
    });
}
	
	function buildCAndidateDetails(result){

		var popupContent = '';
		popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif;font-size:16px">';
					popupContent +=' <header class="timeline-header">';
					popupContent +=' <h3 style="font-size:17px;color:#FF0000;"><b aria-hidden="true" class="stateface "></b> '+result.name+'</a></h3>';
					popupContent +=' </header>';
					popupContent +=' <ol class="timeline-list"> ';
					popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
					popupContent +=' <article class="results-group">';
					popupContent +=' <header class="results-header" style="width: 750px; margin-top: -10px;margin-bottom: 10px;">';
					popupContent +=' </header>';
					popupContent +=' <span style="font-size: 12px;"> <span style="font-size:16px;font-weight:bold;">  Won Party: '+result.selectedCasteDetails[0].name+' ';
					var leadby = result.selectedCasteDetails[0].count - result.selectedCasteDetails[1].count;
					popupContent +=' <span style="margin-left:300px;"> Won By :  '+leadby+' Votes</span>';
					popupContent +=' <header class="results-header" style="width: 750px; border-bottom-color: #004276;border-bottom-width:1px;">';
					popupContent +=' </header>';
					//popupContent +=' <div style="overflow:scroll;height:200px;">';	
							popupContent +=' <table class="results-table" style="font-weight:bold;font-family:Arial,sans-serif">';
							popupContent +=' <tbody>';
							if(result.selectedCasteDetails.length >0){
							
								for(var j in result.selectedCasteDetails)
								{
									
									if(result.selectedCasteDetails[j].casteName != null ){

										popupContent +=' <tr class="type-democrat">';
										popupContent +=' <td class="results-title" style="width:200px;">';
										popupContent +=' <span class="percentage-combo" ><span class="number">'+result.selectedCasteDetails[j].casteName+'</span>';
										popupContent +=' </span>';
										popupContent +=' </td>';
										popupContent +=' <td class="results-title" style="width:200px;">';
										popupContent +=''+result.selectedCasteDetails[j].name+'';

										popupContent +=' </td>';
										popupContent +=' <td class="results-percentage" style=" padding-left: 25px;width: 200px;">';
										if(result.selectedCasteDetails[j].persent != null){
										popupContent +=' <span class="percentage-combo" ><span class="number">'+result.selectedCasteDetails[j].persent+'%</span>';
										}
										else{
										popupContent +=' <span class="percentage-combo" ><span class="number">0 %</span>';
										}
										popupContent +=' <span class="graph">';
										popupContent +=' <span class="bar">';
										popupContent +=' <span style="width:'+result.selectedCasteDetails[j].persent+'%;" class="index"></span>';
										popupContent +=' </span>';
										popupContent +=' </span>';
										popupContent +=' </span>';
										popupContent +=' </td>';
										popupContent +=' <td style="padding-left:35px;">';
										popupContent +=' <span style="font-weight:#000000">'+result.selectedCasteDetails[j].count+' </span>';
										popupContent +=' </td>';
										popupContent +=' </tr>';

									
									}
								}
							}					

					popupContent +=' </tbody>';
					popupContent +=' </table>';
				//	popupContent +=' </div>';
					popupContent +=' </article>';
				popupContent +=' </li> ';
				popupContent +=' </ol>';
				popupContent +=' </article>';				
		return 	popupContent;	
	}	
	
		function buildTelanganaStateWiseResult(searchType,btnId){
		$('#stateAjaxImgg1').show();
		$('#constiLists').find('option').remove().end().append('<option id="0"> Select Constituency </option>');	
		
		typeOfSerarch = searchType;
		$('.btnCls').removeClass('active');
		$('.'+btnId+'').addClass('active');
		$('#results1Div').html('');
		var parties = new Array();
		var electionScope = 2;
		var electionId = 258;
		var scope = "ac";
		parties.push(0);
		
		var regions = new Array();
		if(searchType == 'AllTelangana'){
			regions.push(3);
			regions.push(4);
		}
		else if(searchType == 'NorthTelangana'){
			regions.push(4);
		}
		else if(searchType == 'SouthTelangana'){
			regions.push(3);
		}
		else if(searchType == 'AllSemandhra'){
			regions.push(1);
			regions.push(2);
			regions.push(5);
		}
		else if(searchType == 'NorthSemandhra'){
			regions.push(1);
		}
		else if(searchType == 'SouthSemandhra'){
			regions.push(2);
		}
		else if(searchType == 'Rayalaseema'){
			regions.push(5);
		}
		
		if(searchType == 'Parliament'){
			electionScope = 1
		}
		
		
		if(searchType == 'SParliament'){
			electionScope = 1;
			electionId = 260;
			scope = "pc";
			regions.push(1);
			regions.push(2);
			regions.push(5);
		}
		if(searchType == 'TParliament'){
			electionScope = 1;
			electionId = 260;
			scope = "pc";
			regions.push(3);
			regions.push(4);
		}
		var jsObj=
		{
				electionId : electionId,
				stateId : 1,
				electionScopeId : electionScope,
				regionIds : regions,
				parties:parties,
				scope : scope,
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "filterAndGetElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
		try{
		
		 $( "#processingDialogue" ).dialog('close');
		 
		 for(var i in result[0].hamletCasteInfo){
					$('#constiLists').append('<option id='+result[0].hamletCasteInfo[i].id+'>'+result[0].hamletCasteInfo[i].name+'</option>');
		}
		
		if(result[0].hamletVoterInfo.length >0){

		var popupContent='';
			for(var i in result[0].hamletVoterInfo){	
				if(result[0].hamletVoterInfo[i].selectedCasteDetails.length >0){
					popupContent=buildCAndidateDetails(result[0].hamletVoterInfo[i]);							
					$('#results1Div').append(popupContent);
					popupContent='';
				}							
			}		
		}
		
			$('#stateAjaxImgg1').hide();
         }catch(e){
            $( "#processingDialogue" ).dialog('close');
         }		 
		});	
	}
	
function searchByConstituencyName(){

	var constiId = $('#constiLists  option:selected').val();	
	$('#results1Div').html('').append('<span style="font-weight:bold;margin-left:400px" > No data available...</span>');
	if(constiId != 'Select Constituency'){
		$('#stateAjaxImgg1').show();
		var value1 = $('#constiLists option:selected').text();	
	//		console.log(value1);
			$('#results1Div').html('');
			var parties = new Array();
			var electionScope = 2;
			var electionId = 258;
			var scope = "ac";
			parties.push(0);
			var regions = new Array();
			regions =[];
			if(typeOfSerarch == 'AllTelangana'){
				regions.push(3);
				regions.push(4);
			}
			else if(typeOfSerarch == 'NorthTelangana'){
				regions.push(4);
			}
			else if(typeOfSerarch == 'SouthTelangana'){
				regions.push(3);
			}
			else if(typeOfSerarch == 'AllSemandhra'){
				regions.push(1);
				regions.push(2);
				regions.push(5);
			}
			else if(typeOfSerarch == 'NorthSemandhra'){
				regions.push(1);
			}
			else if(typeOfSerarch == 'SouthSemandhra'){
				regions.push(2);
			}
			else if(typeOfSerarch == 'Rayalaseema'){
				regions.push(5);
			}
			
		
			if(typeOfSerarch == 'SParliament'){
				electionScope = 1;
				electionId = 260;
				scope = "pc";
				regions.push(1);
				regions.push(2);
				regions.push(5);
			}
			if(typeOfSerarch == 'TParliament'){
				electionScope = 1;
				electionId = 260;
				scope = "pc";
				regions.push(3);
				regions.push(4);
			}
				
			var jsObj=
			{
					electionId : electionId,
					stateId : 1,
					electionScopeId : electionScope,
					regionIds : regions,
					parties:parties,
					scope : scope,
					searchName:value1,
					task : "getElectionResults"
			};
			$.ajax({
			type: "GET",
			url: "searchAndGetElectionResultsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
			})
			.done(function( result ) {
			$('#stateAjaxImgg1').hide();
			if(result  != null){
				for(var i in result)
					{
						if(result[i].selectedCasteDetails[0]  != undefined){				
							var popupContent='';
							popupContent=buildCAndidateDetails(result[i]);							
							$('#results1Div').append(popupContent);
							popupContent='';								
						}
					}	
			}
			
			});	
	
	}
	
}	

</script>

<!--sravanthi code start-->
<script type="text/javascript">


var un_emp1 = '0.60';
var elems = document.getElementsByClassName('d3-slider-handle1')[0];
elems.style.left = '50%';
var width2;
var width1;
if(true)
{
var div_width1 = 465;
var div_height1 = 370;

var margin1 = {top: 20, right: 20, bottom: 30, left: 40},
width1 = div_width1 - margin1.left - margin1.right,
height1 = div_height1 - margin1.top - margin1.bottom;
}

var un_emp = '0.60';
var elems = document.getElementsByClassName('d3-slider-handle')[0];
elems.style.left = '20%';
if(true)
{
var div_width = 465;
var div_height = 370;

var margin = {top: 20, right: 20, bottom: 30, left: 40},
width2 = div_width - margin.left - margin.right,
height = div_height - margin.top - margin.bottom;
}
function ajaxProcessing()
{
$("#processingDialogue").dialog({
	  
	   width:530,
		height:$(window).height(),
		autoOpen: false,
		modal: true,
		position:'center',
		resizable: false,
		closeOnEscape: false,
		open: function (event, ui) {
                   $('.ui-widget-overlay').each(function() {
                         $(this).addClass("fullheight");
						 $(this).attr("style","height:"+$(document).height()+"px");
                    });
               }  
			
});
$('#processingDialogue').siblings('div.ui-dialog-titlebar').hide();
$('#processingDialogue').closest('.ui-dialog').addClass("customclass");
$('#processingDialogue').addClass("customclass1");
$('#processingDialogue').removeClass("ui-widget-content ui-dialog-content");
$('.ui-dialog').css({left:378 ,width:512});
$('#processingDialogue').closest('.ui-icon').css("display","none");
 $("#processingDialogue").dialog('open').html("<img src='images/Loading-data.gif' id='mainajaximg' style='height:84px;width:93px;'/>");
 $("#mainajaximg").attr("style","height:84px;width:93px;margin-top:"+$(window).height()/2+"px");
}

$(".matrixRprt").click(function() { 
		checkForPartyShareReport();
	});
	
	
	function checkForPartyShareReport(){
		var val= $('input[name="report"]:checked').val();
		var scope = $("#scopeId").val();
		
		if(val=="Matrix Report" && scope ==5){
			$(".inrReportDivCls,.parties").css("display","block");
		}else{
			$(".inrReportDivCls,.parties").css("display","none");
		}
	}
	
	$("#partyReportId").click(function(){
		if($('#locaionsId1').val() == null){
			$("#errorDiv").html("Please Select Constituencies");
		}
	});
	
	$(".inrRprtType").change(function() { 
		$('#constituencyResultsDiv').html('');
		var val= $('input[name="inrReport"]:checked').val();
		var scope = $("#scopeId").val();
		if(scope == 5 && val == "Party Report"){
			$(".parties").css("display","block");
		}else{
			$(".parties").css("display","none");
		}
	});
	
	$("#electionId").change(function(){
		$("#errorDiv").html("");
		 if( $('#scopeId').val() == 5 && $('input[name="inrReport"]:checked').val() == "Party Report")
		getPartiesInConsituenciesOfElection();
	});
	
	

$("#locaionsId1").change(function(){
	var val= $('input[name="inrReport"]:checked').val();
	if(val == "Party Report"){
		getPartiesInConsituenciesOfElection();
	}
});

$(".inrRprtType").live("click",function() { 
	var val= $('input[name="inrReport"]:checked').val();
	if(val == "Party Report"){
		getPartiesInConsituenciesOfElection();
	}
});


function getPartiesInConsituenciesOfElection(){
	var constituencyDetails={electionId:"",constituencyIds:[]};
	
	constituencyDetails.electionId = $("#electionId").val();


		if($('#locaionsId1').val() == 0)
		{
         $('#locaionsId1 option').each(function(index,value){
			 constituencyDetails.constituencyIds.push(this.value);
		 })
		}else
		constituencyDetails.constituencyIds = $('#locaionsId1').val();

	
$.ajax({
          type:'POST',
          url: 'getPartiesInConsituenciesOfElectionAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			if(result != null && result.length>0){
				buildPartiesInConstituencies(result);
			}else{
				$("#errorDiv").html(" No Parties For Selected Constituencies");
			}
	   });
}

function buildPartiesInConstituencies(result){
	$(".parties").html("");
	var str = "";
	str += "Select Parties  <select id='partiesId' style='width:250px;' multiple='true'>";
		for(var i in result){
			str +="<option value="+result[i].id+">"+result[i].name+"</option>";
		}
	str +="</select>";	
	
	$(".parties").html(str);
	
}

function getParyWiseVotesShare(){
	$("#errorDiv").html("");
	
	var constituencyDetails={electionId:"",constituencyIds:[],partyIds:[]};
	
	constituencyDetails.electionId = $("#electionId").val();

	if($('#locaionsId1').val() == 0)
	{
         $('#locaionsId1 option').each(function(index,value){
			 constituencyDetails.constituencyIds.push(this.value);
		 })
	}else
	 constituencyDetails.constituencyIds = $('#locaionsId1').val();


	//constituencyDetails.constituencyIds = $("#locaionsId1").val();
	constituencyDetails.partyIds = $("#partiesId").val();
	
	
	if($("#partiesId").val() == null){
		$("#errorDiv").html(" Please Select Parties");
		$("#ajaxImage").hide();
		return;
	}
	
	
	$.ajax({
          type:'POST',
          url: 'partysVotesShareInConstituenciesOfElection.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  if(result != null){
				buildPartyVotesShareTable(result);
			}
	   });
}

function buildPartyVotesShareTable(results){

	$("#ajaxImage").hide();
	$("#constituencyResultsDiv").html("");
	var str = '';
	str+= '<div class="tableClass2 offset1">'
	str += '<table  class="table table-bordered table-striped">';
		str +="<tbody>";
		str +='<tr>';
			str +='<td> A# </td>';
			str +='<td> ASSEMBLY </td>';
			str +='<td>Total Voters</td>';
			str +='<td>Valid Votes</td>';
			for(var i in results[0].partyResultVo){
				str +='<td>'+results[0].partyResultVo[i].partyName+'</td>';
			}
		str +='</tr>';
		for(var i in results){
			str +='<tr>';
			str +='<td>'+results[i].acNo+'</td>';
			str +='<td>'+results[i].constituencyName+'</td>';
			str +='<td>'+results[i].ttlVts+'</td>';
			str +='<td>'+results[i].validVts+'</td>';
			for(var j in results[i].partyResultVo){
				if(results[i].partyResultVo[j].partyVotes!=null){
					//str +='<td>'+results[i].partyResultVo[j].partyVotes+'('+results[i].partyResultVo[j].percent+')</td>';
					str +='<td>'+results[i].partyResultVo[j].percent+'</td>';
				}else{
					str +='<td> - </td>';
				}
			}
			str +='</tr>';
		}
		str +="</tbody>";
	str += '</table>';
	str += '</div>';
	$("#constituencyResultsDiv").html(str);
}
function buildMenuForStateAnalysis(searchType)
{
	if(searchType == 'partyWiseStatsDiv')
	{
	$("#partyRebelsEffect").hide();
	$("#casteAnalysisDiv").hide();
	$("#regionWiseAnalysisDiv").hide();
	$("#InteractiveMapDiv").hide();
	$("#partyWiseStatsDiv").show();
	}
	else if(searchType == 'InteractiveMapDiv')
	{
	$("#partyWiseStatsDiv").hide();
	$("#casteAnalysisDiv").hide();
	$("#regionWiseAnalysisDiv").hide();
	$("#partyRebelsEffect").hide();
	$("#InteractiveMapDiv").show();
	}
	else if(searchType == 'regionWiseAnalysisDiv')
	{
	$("#partyWiseStatsDiv").hide();
	$("#InteractiveMapDiv").hide();
	$("#casteAnalysisDiv").hide();
	$("#partyRebelsEffect").hide();
	$("#regionWiseAnalysisDiv").show();
	}
	else if(searchType == 'casteAnalysisDiv')
	{
	$("#partyWiseStatsDiv").hide();
	$("#InteractiveMapDiv").hide();
	$("#regionWiseAnalysisDiv").hide();
	$("#partyRebelsEffect").hide();
	$("#casteAnalysisDiv").show();
	
		if(!casteAnalysisDivFlag)
		{
			$("#casteAnalysisDiv").html('');
			getTopCasteData();
		}
	}
	else if(searchType == 'partyRebelsEffect')
	{
	$("#partyWiseStatsDiv").hide();
	$("#InteractiveMapDiv").hide();
	$("#regionWiseAnalysisDiv").hide();
	$("#casteAnalysisDiv").hide();
	$("#partyRebelsEffect").show();
	}
}
function getTopCasteData(){
	$("#casteAnalysisAjax").show();
	var jsObj = 
	{
		electionId:258,
		task:"getData"
	}
	$.ajax({
          type:'GET',
          url: 'getCasteWiseDataAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
			  if(result != null){
				casteAnalysisDivFlag = true;
				buildCasteData(result);
				//buildPartyVotesShareTable(result);
			}
	   });
}
function buildCasteData(result)
{
	var str = '';
	$("#casteAnalysisAjax").hide();
	str+='<table class="table table-bordered" style="font-size:11px;" id="casteTable">';
	str+='<tr>';
	str+='<th>CONSTITUENCY</th>';
	str+='<th>TOP-5 CASTE</th>';
	str+='<th>CANDIDATE NAME</th>';
	str+='<th>STATUS</th>';
	str+='<th>PARTY</th>';
	str+='<th>VOTES</th>';
	str+='</tr>';
	
	for(var i in result)
	{
		str+='<tr>';
		str+='<td rowspan="3">'+result[i].name+'</td>';
		str+='<td rowspan="3">';
		
		for(var j in result[i].casteList)
		{
			var tempVar = result[i].casteList.length - 1;
			str+=''+ result[i].casteList[j].name+'  '   +result[i].casteList[j].percentage+'%';
			
			if(j != tempVar)
			{
			str+='<hr class="hrwidth" />';
			}
		
		}
		
		str+='</td>';
		
		str += '<td style="width:275px;">'+result[i].candidateList[0].name+'</td>';
		str += '<td>'+result[i].candidateList[0].status+'</td>';
		str += '<td>'+result[i].candidateList[0].party+'</td>';
		str += '<td>'+result[i].candidateList[0].votes+'</td>';
		str += '</tr>';

		str += '<tr>';
		str += '<td style="width:275px;">'+result[i].candidateList[1].name+'</td>';
		str += '<td>'+result[i].candidateList[1].status+'</td>';
		str += '<td>'+result[i].candidateList[1].party+'</td>';
		str += '<td>'+result[i].candidateList[1].votes+'</td>';
		str += '</tr>';

		str += '<tr>';
		str += '<td style="width:275px;">'+result[i].candidateList[2].name+'</td>';
		str += '<td>'+result[i].candidateList[2].status+'</td>';
		str += '<td>'+result[i].candidateList[2].party+'</td>';
		str += '<td>'+result[i].candidateList[2].votes+' </td>';
		str+='</tr>';
	}
	
	str+='</table>';
	$("#casteAnalysisDiv").html(str);
}
$(".highLight").live( "click", function() {

	$(".highLight").removeClass("Tabactive");
	$(this).addClass("Tabactive");

});


</script>

<script type="text/javascript" src="js/newTest1.js"></script>
<script type="text/javascript" src="js/newTest.js"></script>
<!--sravanthi code end-->
</body>
</html>