<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AC AND PC WISE RESULT</title>

<style>
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
	
	.leaflet-popup-close-button{ color:red !important;
	font-size: 30px !important;
	padding-top: 8px !important;
	padding-right: 8px !important;
	
	}
	.leaflet-popup-close-button:hover{color:#00f !important;}
	
	
</style>
<script src="js/apac.js"></script> 
<script src="js/tgac.js"></script>
<script src="js/tgpc.js"></script>
<script src="js/appc.js"></script>
<!--<script src="js/leaflet-lable.js"></script>-->
<script src="js/leaflet.js"></script>
<script src="js/leaflet-google.js"></script>
<!--<script src="http://cdn.leafletjs.com/leaflet-0.6.4/leaflet.js"></script>-->
<!--<script src="http://maps.google.com/maps/api/js?v=3.2&sensor=false"></script>-->
<script src="js/GOOGLE.js"></script>
<script src="js/Permalink.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="styles/politico.css">
<link rel="stylesheet" type="text/css" href="styles/leaflet.css">
<link rel="stylesheet" type="text/css" href="styles/leaflet-lable.css">
</head>
<body>
<!--<div align="center" style="margin-top:20px;">
<img src="images/MEnuBG.jpg" width="960" height="32" border="0" usemap="#Map" />
</div>-->
<div class="container" style="font-family: verdana; font-size: 14px; border: 1px solid rgb(204, 204, 204); padding: 0px 10px 10px; margin-top: 24px;">
<h4 style="padding: 10px; margin-top: 10px; border-radius: 5px; text-align: center; background: none repeat scroll 0% 0% rgb(73, 175, 205);">LIVE ELECTION RESULTS COMPARISON</h4>
<div id="btnDivs">
	<!--<span id="optionsBtn1" class="btn btn-success" style="float:right;margin:10px 100px 10px 0px;"> Parliament Wise Results </span>
		<span id="optionsBtn2" class="btn btn-success" style="float:right;margin:10px 100px 10px 0px;display;none;"> Assembly Wise Results </span>-->
	<br><br>
</div>
<map name="Map" id="menuMap">
	<area shape="rect" coords="442,-5,503,31" href="#" title="Telangana"/>
	<area shape="rect" coords="504,-4,576,30" href="#" title="" />
	<area shape="rect" coords="577,-8,611,30" href="#" title=""/>
	<area shape="rect" coords="611,1,693,29" href="#" title=""/>
	<area shape="rect" coords="693,0,791,33" href="#" title=""/>
	<area shape="rect" coords="791,1,858,30" href="#" title=""/>
	<area shape="rect" coords="858,-4,929,29" href="#" title=""/>
	<area shape="rect" coords="929,0,958,30" href="#" title=""/>
</map>

<div align="center" style="margin-bottom: 20px;" >
	<div class="span4 offset4">
	<b>Select State</b>
	<select id="stateId">
	<option value="0">Select State</option>
	<option value="1">SeeamAndhra</option>
	<option value="2">Telangana</option>
	</select>
	</div>
</div>
</br></br>
<div class="row-fluid ">
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
		<!-- <option value="2">2004</option> -->
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
		<!-- <option value="2">2004</option> -->
		</select>
		
		</div>
		</div>
	</div>

<br/>
<div class="row-fluid ">
	<div class="span2 offset5">
	 <a class="btn btn-info btn-block " value="Submit" onClick="getElectionDetails();" >Submit</a>	
	</div>
</div>
</br></br>
<div class="row-fluid">
	<div id="weathermap"></div>
	<div id="weathermap1"></div>

</div>
<div id="result"></div>
<div id="resultDiv"></div>


<!--

<div align="center" style="" class="hero-unit">
<div id="partiesDiv"></div>
</div>
-->
<div id="subTitlesDiv" style="margin-left:200px;margin-top:20px;"></div>

<div id="results1Div" ></div>

</div>
<style>


</style>
<script>
/*
	Leaflet.label, a plugin that adds labels to markers and vectors for Leaflet powered maps.
	(c) 2012-2013, Jacob Toye, Smartrak

	https://github.com/Leaflet/Leaflet.label
	http://leafletjs.com
	https://github.com/jacobtoye
*/
(function(){L.labelVersion="0.2.2-dev",L.Label=L.Class.extend({includes:L.Mixin.Events,options:{className:"",clickable:!1,direction:"right",noHide:!1,offset:[12,-15],opacity:1,zoomAnimation:!0},initialize:function(t,e){L.setOptions(this,t),this._source=e,this._animated=L.Browser.any3d&&this.options.zoomAnimation,this._isOpen=!1},onAdd:function(t){this._map=t,this._pane=this._source instanceof L.Marker?t._panes.markerPane:t._panes.popupPane,this._container||this._initLayout(),this._pane.appendChild(this._container),this._initInteraction(),this._update(),this.setOpacity(this.options.opacity),t.on("moveend",this._onMoveEnd,this).on("viewreset",this._onViewReset,this),this._animated&&t.on("zoomanim",this._zoomAnimation,this),L.Browser.touch&&!this.options.noHide&&L.DomEvent.on(this._container,"click",this.close,this)},onRemove:function(t){this._pane.removeChild(this._container),t.off({zoomanim:this._zoomAnimation,moveend:this._onMoveEnd,viewreset:this._onViewReset},this),this._removeInteraction(),this._map=null},setLatLng:function(t){return this._latlng=L.latLng(t),this._map&&this._updatePosition(),this},setContent:function(t){return this._previousContent=this._content,this._content=t,this._updateContent(),this},close:function(){var t=this._map;t&&(L.Browser.touch&&!this.options.noHide&&L.DomEvent.off(this._container,"click",this.close),t.removeLayer(this))},updateZIndex:function(t){this._zIndex=t,this._container&&this._zIndex&&(this._container.style.zIndex=t)},setOpacity:function(t){this.options.opacity=t,this._container&&L.DomUtil.setOpacity(this._container,t)},_initLayout:function(){this._container=L.DomUtil.create("div","leaflet-label "+this.options.className+" leaflet-zoom-animated"),this.updateZIndex(this._zIndex)},_update:function(){this._map&&(this._container.style.visibility="hidden",this._updateContent(),this._updatePosition(),this._container.style.visibility="")},_updateContent:function(){this._content&&this._map&&this._prevContent!==this._content&&"string"==typeof this._content&&(this._container.innerHTML=this._content,this._prevContent=this._content,this._labelWidth=this._container.offsetWidth)},_updatePosition:function(){var t=this._map.latLngToLayerPoint(this._latlng);this._setPosition(t)},_setPosition:function(t){var e=this._map,i=this._container,n=e.latLngToContainerPoint(e.getCenter()),o=e.layerPointToContainerPoint(t),s=this.options.direction,a=this._labelWidth,l=L.point(this.options.offset);"right"===s||"auto"===s&&o.x<n.x?(L.DomUtil.addClass(i,"leaflet-label-right"),L.DomUtil.removeClass(i,"leaflet-label-left"),t=t.add(l)):(L.DomUtil.addClass(i,"leaflet-label-left"),L.DomUtil.removeClass(i,"leaflet-label-right"),t=t.add(L.point(-l.x-a,l.y))),L.DomUtil.setPosition(i,t)},_zoomAnimation:function(t){var e=this._map._latLngToNewLayerPoint(this._latlng,t.zoom,t.center).round();this._setPosition(e)},_onMoveEnd:function(){this._animated&&"auto"!==this.options.direction||this._updatePosition()},_onViewReset:function(t){t&&t.hard&&this._update()},_initInteraction:function(){if(this.options.clickable){var t=this._container,e=["dblclick","mousedown","mouseover","mouseout","contextmenu"];L.DomUtil.addClass(t,"leaflet-clickable"),L.DomEvent.on(t,"click",this._onMouseClick,this);for(var i=0;e.length>i;i++)L.DomEvent.on(t,e[i],this._fireMouseEvent,this)}},_removeInteraction:function(){if(this.options.clickable){var t=this._container,e=["dblclick","mousedown","mouseover","mouseout","contextmenu"];L.DomUtil.removeClass(t,"leaflet-clickable"),L.DomEvent.off(t,"click",this._onMouseClick,this);for(var i=0;e.length>i;i++)L.DomEvent.off(t,e[i],this._fireMouseEvent,this)}},_onMouseClick:function(t){this.hasEventListeners(t.type)&&L.DomEvent.stopPropagation(t),this.fire(t.type,{originalEvent:t})},_fireMouseEvent:function(t){this.fire(t.type,{originalEvent:t}),"contextmenu"===t.type&&this.hasEventListeners(t.type)&&L.DomEvent.preventDefault(t),"mousedown"!==t.type?L.DomEvent.stopPropagation(t):L.DomEvent.preventDefault(t)}}),L.BaseMarkerMethods={showLabel:function(){return this.label&&this._map&&(this.label.setLatLng(this._latlng),this._map.showLabel(this.label)),this},hideLabel:function(){return this.label&&this.label.close(),this},setLabelNoHide:function(t){this._labelNoHide!==t&&(this._labelNoHide=t,t?(this._removeLabelRevealHandlers(),this.showLabel()):(this._addLabelRevealHandlers(),this.hideLabel()))},bindLabel:function(t,e){var i=this.options.icon?this.options.icon.options.labelAnchor:this.options.labelAnchor,n=L.point(i)||L.point(0,0);return n=n.add(L.Label.prototype.options.offset),e&&e.offset&&(n=n.add(e.offset)),e=L.Util.extend({offset:n},e),this._labelNoHide=e.noHide,this.label||(this._labelNoHide||this._addLabelRevealHandlers(),this.on("remove",this.hideLabel,this).on("move",this._moveLabel,this).on("add",this._onMarkerAdd,this),this._hasLabelHandlers=!0),this.label=new L.Label(e,this).setContent(t),this},unbindLabel:function(){return this.label&&(this.hideLabel(),this.label=null,this._hasLabelHandlers&&(this._labelNoHide||this._removeLabelRevealHandlers(),this.off("remove",this.hideLabel,this).off("move",this._moveLabel,this).off("add",this._onMarkerAdd,this)),this._hasLabelHandlers=!1),this},updateLabelContent:function(t){this.label&&this.label.setContent(t)},getLabel:function(){return this.label},_onMarkerAdd:function(){this._labelNoHide&&this.showLabel()},_addLabelRevealHandlers:function(){this.on("mouseover",this.showLabel,this).on("mouseout",this.hideLabel,this),L.Browser.touch&&this.on("click",this.showLabel,this)},_removeLabelRevealHandlers:function(){this.off("mouseover",this.showLabel,this).off("mouseout",this.hideLabel,this),L.Browser.touch&&this.off("click",this.showLabel,this)},_moveLabel:function(t){this.label.setLatLng(t.latlng)}},L.Icon.Default.mergeOptions({labelAnchor:new L.Point(9,-20)}),L.Marker.mergeOptions({icon:new L.Icon.Default}),L.Marker.include(L.BaseMarkerMethods),L.Marker.include({_originalUpdateZIndex:L.Marker.prototype._updateZIndex,_updateZIndex:function(t){var e=this._zIndex+t;this._originalUpdateZIndex(t),this.label&&this.label.updateZIndex(e)},_originalSetOpacity:L.Marker.prototype.setOpacity,setOpacity:function(t,e){this.options.labelHasSemiTransparency=e,this._originalSetOpacity(t)},_originalUpdateOpacity:L.Marker.prototype._updateOpacity,_updateOpacity:function(){var t=0===this.options.opacity?0:1;this._originalUpdateOpacity(),this.label&&this.label.setOpacity(this.options.labelHasSemiTransparency?this.options.opacity:t)},_originalSetLatLng:L.Marker.prototype.setLatLng,setLatLng:function(t){return this.label&&!this._labelNoHide&&this.hideLabel(),this._originalSetLatLng(t)}}),L.CircleMarker.mergeOptions({labelAnchor:new L.Point(0,0)}),L.CircleMarker.include(L.BaseMarkerMethods),L.Path.include({bindLabel:function(t,e){return this.label&&this.label.options===e||(this.label=new L.Label(e,this)),this.label.setContent(t),this._showLabelAdded||(this.on("mouseover",this._showLabel,this).on("mousemove",this._moveLabel,this).on("mouseout remove",this._hideLabel,this),L.Browser.touch&&this.on("click",this._showLabel,this),this._showLabelAdded=!0),this},unbindLabel:function(){return this.label&&(this._hideLabel(),this.label=null,this._showLabelAdded=!1,this.off("mouseover",this._showLabel,this).off("mousemove",this._moveLabel,this).off("mouseout remove",this._hideLabel,this)),this},updateLabelContent:function(t){this.label&&this.label.setContent(t)},_showLabel:function(t){this.label.setLatLng(t.latlng),this._map.showLabel(this.label)},_moveLabel:function(t){this.label.setLatLng(t.latlng)},_hideLabel:function(){this.label.close()}}),L.Map.include({showLabel:function(t){return this.addLayer(t)}}),L.FeatureGroup.include({clearLayers:function(){return this.unbindLabel(),this.eachLayer(this.removeLayer,this),this},bindLabel:function(t,e){return this.invoke("bindLabel",t,e)},unbindLabel:function(){return this.invoke("unbindLabel")},updateLabelContent:function(t){this.invoke("updateLabelContent",t)}})})(this,document);
</script>
<script>
var stateType = '';
	$('document').ready(function(){
		stateType = 'Semandhra';
/*$('#optionsBtn2').hide();
		$('#optionsBtn1').click(function(){
				$('#map1').show();
				$('#map').hide();
				$('#optionsBtn1').hide();
				$('#optionsBtn2').show();
				
		});
		$('#optionsBtn2').click(function(){
				$('#map').show();
				$('#map1').hide();
				$('#optionsBtn2').hide();
				$('#optionsBtn1').show();
				
		});*/
	});
	//getConstituenctSelection();
	var map = "";
	var map1 = "";
	var electionAcData = '';
	var electionPcData = '';
	function getElectionDetails()
	{
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
	
	function getMapType(type)
	{
		if(type == 1)
		{
			document.getElementById('weathermap').innerHTML = "<div id='map' class='span6' style='height: 500px; border: 1px solid rgb(51, 51, 51); border-radius: 10px; position: relative; background: none repeat scroll 0% 0% rgb(255, 255, 255);'></div>"
			map = L.map('map', {
			center: [16.0000,80.0000],
			zoom: 6
			});
		}
		else
		{
			document.getElementById('weathermap1').innerHTML = "<div id='map1' class='span6' style='height: 500px; float: right ! important; left: 0px; position: relative; border: 1px solid rgb(51, 51, 51); border-radius: 10px; background: none repeat scroll 0% 0% rgb(255, 255, 255);'></div>"
			map1 = L.map('map1', {
			center: [16.0000,80.0000],
			zoom: 6
			});
		}
		
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
			getMapType(1);
		}
		else
		{
			getMapType(2);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		var jsObj=
		{
				electionId : 38,
				stateId : 1,
				electionScopeId : 2,
				parties : parties,
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
			getMapType(1);
		}
		else
		{
			getMapType(2);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		var jsObj=
		{
				electionId : 3,
				stateId : 1,
				electionScopeId : 2,
				parties : parties,
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
			getMapType(1);
		}
		else
		{
			getMapType(2);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		var jsObj=
		{
				electionId : 17,
				stateId : 1,
				electionScopeId : 1,
				parties : parties,
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
			getMapType(1);
		}
		else
		{
			getMapType(2);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		var jsObj=
		{
				electionId : 18,
				stateId : 1,
				electionScopeId : 1,
				parties : parties,
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
	
	
	function generateMapForApACPrevious(mapName)
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

		}).addTo(mapName); 
	}
	
	function generateMapForApACPresent(mapName)
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

		}).addTo(mapName); 
	}
	function generateMapForTgACPrevious(mapName)
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

		}).addTo(mapName); 
	}
	
	
	function generateMapForTgACPresent(mapName)
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

		}).addTo(mapName); 
	}
	
	var type ;
	
	
	
	
	function generateMapForApPCPrevious(mapName)
	{
		type = "pc";
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
	}
	
	function generateMapForApPCPresent(mapName)
	{
		type = "pc";
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
	}
	function generateMapForTgPCPrevious(mapName)
	{
		type = "pc";
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
	}
	
	
	function generateMapForTgPCPresent(mapName)
	{
		type = "pc";
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
	}
	
	/* var tableToExcel = (function() {
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
 */
	/* function getConstituenctSelection()
	{
		if($("input:radio[name=type]:checked").val() == 'ac')
		{
			$('#state').attr("disabled", false);
			getStatesForAssembly();
		}
		else
		{
			$('#state').attr("disabled", true); 
			getAssemblyElectionYears(1,"Parliament");
		}
	}	 */
	
	function onEachFeature(feature, layer)
	{
	
	var today=new Date();

    var month=new Array();
	month[0]="January";
	month[1]="February";
	month[2]="March";
	month[3]="April";
	month[4]="May";
	month[5]="June";
	month[6]="July";
	month[7]="August";
	month[8]="September";
	month[9]="October";
	month[10]="November";
	month[11]="December";
	

		if(type == "pc")
		{
			onEachFeature1(feature, layer);
		}
		else
		{
			var popupContent='';

			popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif;margin-left:-40px;">';
			popupContent +=' <header class="timeline-header">';
			popupContent +=' <h3><b aria-hidden="true" class="stateface "></b> '+feature.properties.ac_name+'</a> <span style="color:#303030;float:right;">'+month[today.getMonth()]+" "+today.getDate()+","+today.getFullYear()+" "+'</span></h3>';
			popupContent +=' </header>';
			popupContent +=' <ol class="timeline-list"> ';
			popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
			popupContent +=' <article class="results-group">';
			popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;margin-bottom: 10px;">';
			popupContent +=' </header>';
			//popupContent +=' <b style="font-size: 12px;">  Reporting : 0 % <span style="font-weight:bold;float:right;">  Leading Party: TDP </span></b>';
			popupContent +=' <table>';
		popupContent +=' <tr>';
		popupContent +='<td style="width:700px;"> Candidate Name </td>';
		popupContent +='<td style="width:300px;padding-left:15px;"> Party </td>';
		popupContent +='<td style="width:600px;"> Votes Percentage  </td>';
		popupContent +=' </tr">';
		popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;border-bottom-color: #004276;border-bottom-width: 2px;">';
		popupContent +=' </header>';
		popupContent +=' <tr class="type-democrat">';
		popupContent +=' </table>';
		popupContent +=' <span style="float: right; margin-right: -50px; margin-top: -20px;"> Total Votes </span>';
		
			popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;border-bottom-width: 2px;">';
			popupContent +=' </header>';

			for(var i in electionAcData)
			{
				if(feature.properties.ac == electionAcData[i].hamletId)
				{
					for(var j in electionAcData[i].selectedCasteDetails)
					{
						if(electionAcData[i].selectedCasteDetails[j].casteName != null)
						{
							popupContent +=' <div class="results-dataset">';
							popupContent +=' <div class="results-row layout-full">';
							popupContent +=' <div class="results-data pos-omega contains-mix is-de-emphasized">';
							popupContent +=' <div class="results-message">';
							popupContent +=' <table class="results-table" style="width:650px">';
							popupContent +=' <tbody>';
							popupContent +=' <tr class="type-democrat">';
							popupContent +=' <td class="results-title" style="width:120px;">';
							popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].casteName+'</span>';
							popupContent +=' </span>';
							popupContent +=' </td>';
							popupContent +=' <td class="results-title" style="width:40px;">';
							//popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].name+'</span>';
							if(electionAcData[i].selectedCasteDetails[j].name =='TDP'){
								popupContent +=' <span > <img src="images/party_flags/TDP.PNG" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='INC'){
								popupContent +=' <span > <img src="images/party_flags/INC.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='TRS'){
								popupContent +=' <span > <img src="images/party_flags/TRS.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='BJP'){
								popupContent +=' <span > <img src="images/party_flags/BJP.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='AIMIM'){
								popupContent +=' <span > <img src="images/party_flags/AIMIM.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='CPM'){
								popupContent +=' <span > <img src="images/party_flags/CPM.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='CPI'){
								popupContent +=' <span > <img src="images/party_flags/CPI.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='LSP'){
								popupContent +=' <span > <img src="images/party_flags/LSP.png" width=125% /></span>';
							}
							
							popupContent +=' </span>';
							popupContent +=' </td>';
							//popupContent +=' <td class="results-title" style="width: 30px;">';
							//popupContent +=' </td>';
							popupContent +=' <td class="results-percentage" style="width:100px;padding-left: 25px;">';
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
							popupContent +=' <td style="width: 100px;padding-left:35px;">';
							popupContent +=' <span style="font-weight:#000000">'+electionAcData[i].selectedCasteDetails[j].count+' </span>';
							popupContent +=' </td>';
							popupContent +=' </tr>';
							popupContent +=' </tbody>';
							popupContent +=' </table>';
							popupContent +=' </div>';
							popupContent +=' </div>';
							popupContent +=' </div>';
							popupContent +=' </div>';
						}
						
			
					}
					
				}
			} 
			

			popupContent +=' </article>';
			popupContent +=' </li> ';
			popupContent +=' </ol>';
			popupContent +=' </article>';


			$('.leaflet-popup-close-button').html('');
			if (feature.properties && feature.properties.popupContent)
			{
				popupContent += feature.properties.popupContent;
			}
			 
			if(feature.properties.area_type == 'GEN')
			{
				layer.setStyle({
				color: '#000000', 
				weight: 1,
				opacity: 0.6,
				fillOpacity: 0.65,
				name : "prasad",
				fillColor: '#00FF00'
				});
				layer.bindPopup(popupContent);
			}
			else if (feature.properties.area_type == 'ST')
			{
				layer.setStyle({
				color: '#000000', 
				weight: 1,
				opacity: 0.6,
				fillOpacity: 0.65,
				fillColor: '#FF00FF'
				});
				layer.bindPopup(popupContent);
			}
			else
			{
				layer.setStyle({
				color: '#000000', 
				weight: 1,
				opacity: 0.6,
				fillOpacity: 0.65,
				fillColor: '#FF0000'
				});
				layer.bindPopup(popupContent);
			} 
			layer.bindLabel(feature.properties.ac_name, {noHide:true});
			/* layer.setStyle({
				color: '#000000', 
				weight: 1,
				opacity: 0.6,
				fillOpacity: 0.65,
				fillColor: '#FFFFFF'
				});
				layer.bindPopup(popupContent); */
		}
		
	}
	
	function onEachFeature1(feature, layer)
	{
	
	var today=new Date();

    var month=new Array();
	month[0]="January";
	month[1]="February";
	month[2]="March";
	month[3]="April";
	month[4]="May";
	month[5]="June";
	month[6]="July";
	month[7]="August";
	month[8]="September";
	month[9]="October";
	month[10]="November";
	month[11]="December";
	
		var popupContent='';

		popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif; margin-left: -40px;">';
		popupContent +=' <header class="timeline-header">';
		popupContent +=' <h3><b aria-hidden="true" class="stateface "></b> '+feature.properties.FIRST_pc_n+'</a> <span style="color:#303030;float:right;">'+month[today.getMonth()]+" "+today.getDate()+","+today.getFullYear()+" "+'</span></h3>';
		popupContent +=' </header>';
		popupContent +=' <ol class="timeline-list"> ';
		popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
		popupContent +=' <article class="results-group">';
		popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;margin-bottom: 10px;">';
		popupContent +=' </header>';
	//	popupContent +=' <b style="font-size: 12px;">  Reporting : 0 % <span style="font-weight:bold;float:right;">  Leading Party: TDP </span></b>';
			popupContent +=' <table>';
		popupContent +=' <tr>';
		popupContent +='<td style="width:700px;"> Candidate Name </td>';
		popupContent +='<td style="width:300px;padding-left:15px;"> Party </td>';
		popupContent +='<td style="width:600px;"> Votes Percentage  </td>';
		popupContent +=' </tr">';
		popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;border-bottom-color: #004276;border-bottom-width: 2px;">';
		popupContent +=' </header>';
		popupContent +=' <tr class="type-democrat">';
		popupContent +=' </table>';
		popupContent +=' <span style="float: right; margin-right: -50px; margin-top: -20px;"> Total Votes </span>';
						
		popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;border-bottom-width: 2px;">';
		popupContent +=' </header>';
		//console.log(electionPcData);
		for(var i in electionPcData)
		{	
			if(feature.properties.pc == electionPcData[i].hamletId)
			{
				for(var j in electionPcData[i].selectedCasteDetails)
				{
					if(electionPcData[i].selectedCasteDetails[j].casteName != null)
					{
						popupContent +=' <div class="results-dataset">';
						popupContent +=' <div class="results-row layout-full">';
						popupContent +=' <div class="results-data pos-omega contains-mix is-de-emphasized">';
						popupContent +=' <div class="results-message">';
						popupContent +=' <table class="results-table" style="width:650px">';
						popupContent +=' <tbody>';
						popupContent +=' <tr class="type-democrat">';
						popupContent +=' <td class="results-title" style="width:120px;">';
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionPcData[i].selectedCasteDetails[j].casteName+'</span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td class="results-title" style="width: 25px;">';
						//popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].name+'</span>';
						if(electionPcData[i].selectedCasteDetails[j].name =='TDP'){
							popupContent +=' <span > <img src="images/party_flags/TDP.PNG" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='INC'){
							popupContent +=' <span > <img src="images/party_flags/INC.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='TRS'){
							popupContent +=' <span > <img src="images/party_flags/TRS.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='BJP'){
							popupContent +=' <span > <img src="images/party_flags/BJP.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='AIMIM'){
							popupContent +=' <span > <img src="images/party_flags/AIMIM.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='CPM'){
							popupContent +=' <span > <img src="images/party_flags/CPM.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='CPI'){
							popupContent +=' <span > <img src="images/party_flags/CPI.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='LSP'){
							popupContent +=' <span > <img src="images/party_flags/LSP.png" width=125% /></span>';
						}
						
						popupContent +=' </span>';
						popupContent +=' </td>';
						//popupContent +=' <td class="results-title" style="width: 30px;">';
						//popupContent +=' </td>';
						popupContent +=' <td class="results-percentage" style="width: 100px;padding-left: 25px;">';
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
						popupContent +=' <td style="width: 150px;padding-left:35px;">';
						popupContent +=' <span style="font-weight:#000000">'+electionPcData[i].selectedCasteDetails[j].count+' </span>';
						popupContent +=' </td>';
						popupContent +=' </tr>';
						popupContent +=' </tbody>';
						popupContent +=' </table>';
						popupContent +=' </div>';
						popupContent +=' </div>';
						popupContent +=' </div>';
						popupContent +=' </div>';
					}
					
		
				}
				
			}
		} 
		

		popupContent +=' </article>';
		popupContent +=' </li> ';
		popupContent +=' </ol>';
		popupContent +=' </article>';

		if (feature.properties && feature.properties.popupContent)
		{
			popupContent += feature.properties.popupContent;
		}
		 
		if(feature.properties.area_type == 'GEN')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			name : "prasad",
			fillColor: '#00FF00'
			});
			layer.bindPopup(popupContent);
		}
		else if (feature.properties.area_type == 'ST')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#FF00FF'
			});
			layer.bindPopup(popupContent);
		}
		else
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#FF0000'
			});
			layer.bindPopup(popupContent);
		} 
			
		layer.bindLabel(feature.properties.FIRST_pc_n, {noHide:false});
	}
	/* function getStatesForAssembly()
	{	
		var jsObj=
		{
			electionType:2,
			task:"getStates"						
		};
		$.ajax({
		type: "GET",
		url: "getStatesForHomepage.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			$('#state').find('option').remove();
			$('#state').append('<option value="0">Select State </option>');
			$.each(result,function(index,value){
				$('#state').append('<option value="'+value.id+'">'+value.name+'</option>');
			});


		});		
	} */
	
	
	/* function getAssemblyElectionYears(id,electionType)
	{
		var jsObj=
		{
				stateId:id,
				electionType:electionType,
				task:"getElectionYearsForAState"						
		};
		$.ajax({
		type: "GET",
		url: "electionYearsForstateAndElectionTypeAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			$('#electionYear').find('option').remove();
			$.each(result,function(index,value){
				$('#electionYear').append('<option value="'+value.id+'">'+value.name+'</option>');
			});


		});		
	}
	function getPartiesForSelection()
	{
		var electionTypeId = '';
		if($("input:radio[name=type]:checked").val() == 'ac')
		{
			electionTypeId = 2;
		}
		else
		{
			electionTypeId = 1;
		}
		var jsObj=
		{
				elmtId : "electionScopeSelect",
				stateId : $('#state option:selected').val(),
				electionTypeId : electionTypeId,
				task : "getPartiesInState"
		};
		$.ajax({
		type: "GET",
		url: "getPartiesInState.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
		
				var str='';
				str+='<table><tr><th align="left">Parties : </th><td>';
				for(var i in result){
				if(result[i].id != 366)
				str+='<input id="parties-'+i+'"  class="partySelForPanc" type="checkbox" value="'+result[i].id+'" name="parties"><label class="checkboxLabel" for="parties-'+i+'">'+result[i].name+'</label>';
				}
				str+='</td></tr>';
				str+='<table>';
				
				$('#partiesDiv').html(str);
		});	
	}	 */
	
	
	function buildResult(result){

	$('#subTitlesDiv').html('');
	$('#results1Div').html('');
	
	var subMenu = '';
		if(stateType == 'Semandhra'){
			subMenu = '<h2 style="font-family:Georgia,Times;font-size:16px;font-size:25px;"> Andhra Pradesh Election Results - 2014 </h2>';
			//"ff-tisa-web-pro",Georgia,Times,"Times New Roman",serif
			subMenu +='<ul class="nav nav-pills" style="font-weight: 500;">';
			subMenu +='	<li style="margin-top:10px;color:#ADADAD;"> Filter Options : </li> ';
			subMenu +='	<li class="active">';
			subMenu +='	<a style="margin-left:20px;" onlcick="stateWiseResult();"> State wise Election Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li >';
			subMenu +='	<a style="margin-left:20px;" onlcick="stateWiseResult();"> Parliament wise Election Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li><a style="margin-left:20px;"> North Andhra </a></li>';
			subMenu +='	<li><a style="margin-left:20px;"> South Andhra </a></li>';
			subMenu +='	<li><a style="margin-left:20px;"> Rayalaseema </a></li>';
			subMenu +='</ul>';
		}
		else if(stateType == 'Telangana'){
			subMenu = '<h2 style="font-family: times new roman,serif,sans-serif;font-size:16px;font-size:25px;font-weight: 500;"> Telangana Election Results - 2014 </h2>';
			subMenu +='<ul class="nav nav-pills" style="font-weight:500;">';
			subMenu +='	<li style="margin-top:10px;color:#ADADAD;"> Filter Options : </li> ';
			subMenu +='	<li class="active">';
			subMenu +='	<a style="margin-left:20px;" onlcick="stateWiseResult();"> State wise Election Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li >';
			subMenu +='	<a style="margin-left:20px;" onlcick="stateWiseResult();"> Parliament wise Election Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li><a style="margin-left:20px;"> South Telangana </a></li>';
			subMenu +='	<li><a style="margin-left:20px;"> North Telangana </a></li>';
			subMenu +='</ul>';
		}

	$('#subTitlesDiv').html(subMenu);
	subMenu='';
	
	var today=new Date();

    var month=new Array();
	month[0]="January";
	month[1]="February";
	month[2]="March";
	month[3]="April";
	month[4]="May";
	month[5]="June";
	month[6]="July";
	month[7]="August";
	month[8]="September";
	month[9]="October";
	month[10]="November";
	month[11]="December";
	

	var district = '';
		for(var i in result)
			{
			if(result[i].selectedCasteDetails[0]  != undefined){
				var popupContent='';
			popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif;font-size:16px">';
				/*
					if(district.length ==0 || district !=''+result[i].mandalName+''){
					district = result[i].mandalName;
						popupContent +='<div style="background:#5080A6;">'+result[i].mandalName+'</div>';
					}
				*/
			popupContent +=' <header class="timeline-header">';
			popupContent +=' <h3 style="font-size:17px;color:#FF0000;"><b aria-hidden="true" class="stateface "></b> '+result[i].name+'</a></h3>';
			popupContent +=' </header>';
			//popupContent +=' <span style="color:#303030;">'+month[today.getMonth()]+" "+today.getDate()+","+today.getFullYear()+" "+'</span>';
			popupContent +=' <ol class="timeline-list"> ';
			popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
			popupContent +=' <article class="results-group">';
			popupContent +=' <header class="results-header" style="width: 950px; margin-top: -10px;margin-bottom: 10px;">';
			popupContent +=' </header>';
			popupContent +=' <span style="font-size: 12px;">  Reporting : 0 % <span style="margin-left:150px;font-size:16px;font-weight:bold;">  Leading Party: '+result[i].selectedCasteDetails[0].name+' </span> <span style="color:#303030;margin-left:300px">'+month[today.getMonth()]+" "+today.getDate()+",  "+today.getFullYear()+" "+'</span></span>';
			popupContent +=' <header class="results-header" style="width: 950px; border-bottom-color: #004276;border-bottom-width: 2px;">';
			popupContent +=' </header>';
			
			popupContent +=' <div class="results-dataset">';
					popupContent +=' <div class="results-row layout-full">';
					popupContent +=' <div class="results-data pos-omega contains-mix is-de-emphasized">';
					popupContent +=' <div class="results-message">';
					if(result[i].selectedCasteDetails.length >0){
					
						for(var j in result[i].selectedCasteDetails)
						{
							
								popupContent +=' <table class="results-table" style="font-weight:bold;font-family:Arial,sans-serif">';
								popupContent +=' <tbody>';
								popupContent +=' <tr class="type-democrat">';
								popupContent +=' <td class="results-title" >';
								popupContent +=' <span class="percentage-combo" ><span class="number">'+result[i].selectedCasteDetails[j].casteName+'</span>';
								popupContent +=' </span>';
								popupContent +=' </td>';
								popupContent +=' <td class="results-title" style="width:200px;">';
								if(result[i].selectedCasteDetails[j].name =='TDP'){
									popupContent +=' <span > <img src="images/party_flags/TDP01.jpg" /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='INC'){
									popupContent +=' <span > <img src="images/party_flags/INC01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='TRS'){
									popupContent +=' <span > <img src="images/party_flags/TRS01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='BJP'){
									popupContent +=' <span > <img src="images/party_flags/BJP01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='AIMIM'){
									popupContent +=' <span > <img src="images/party_flags/AIMIM01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='CPM'){
									popupContent +=' <span > <img src="images/party_flags/CPM01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='CPI'){
									popupContent +=' <span > <img src="images/party_flags/CPI01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='LSP'){
									popupContent +=' <span > <img src="images/party_flags/LSP01.jpg" width=125% /></span>';
								}
								
								popupContent +=' </td>';
								//popupContent +=' <td class="results-title" style="width: 30px;">';
								//popupContent +=' </td>';
								popupContent +=' <td class="results-percentage" style=" padding-left: 25px;">';
								if(result[i].selectedCasteDetails[j].persent != null){
								popupContent +=' <span class="percentage-combo" ><span class="number">'+result[i].selectedCasteDetails[j].persent+'%</span>';
								}
								else{
								popupContent +=' <span class="percentage-combo" ><span class="number">0 %</span>';
								}
								popupContent +=' <span class="graph">';
								popupContent +=' <span class="bar">';
								popupContent +=' <span style="width:'+result[i].selectedCasteDetails[j].persent+'%;" class="index"></span>';
								popupContent +=' </span>';
								popupContent +=' </span>';
								popupContent +=' </span>';
								popupContent +=' </td>';
								popupContent +=' <td style="padding-left:35px;">';
								popupContent +=' <span style="font-weight:#000000">'+result[i].selectedCasteDetails[j].count+' </span>';
								popupContent +=' </td>';
								popupContent +=' </tr>';
								popupContent +=' </tbody>';
								popupContent +=' </table>';
							
							}
			
					}
			
					popupContent +=' </div>';
					popupContent +=' </div>';
					popupContent +=' </div>';
					popupContent +=' </div>';
		
			popupContent +=' </article>';
		popupContent +=' </li> ';
		popupContent +=' </ol>';
		popupContent +=' </article>';
		
		$('#results1Div').append(popupContent);
		popupContent='';
		}
		}
		
	}
	/*function buildResult(result)
	{
		
		var str = '';
		str += '<table class="table table-hover table-bordered" id="subLevelTable">';
		str += '<thead>';
		str += '<tr>';
		str += '<th>DISTRICT</th>';
		str += '<th>AC NO</th>';
		str += '<th>AC NAME</th>';
		for(var i in result[0].selectedCasteDetails)
		{
			str += '<th>'+result[0].selectedCasteDetails[i].name+'</th>';
		}
		str += '</tr>';
		str += '</thead><tbody>';
		for(var i in result)
		{
			str += '<tr>';
			str += '<td>'+result[i].mandalName+'</td>';
			str += '<td>'+result[i].hamletId+'</td>';
			str += '<td>'+result[i].name+'</td>';
			
			for(var j in result[i].selectedCasteDetails)
			{
				str += '<th>'+result[i].selectedCasteDetails[j].count+'</th>';
			}
			
			str += '</tr>';
		}
		str+= '</tbody>';
		str += '</table>';
		
		$('#results1Div').html(str);
		
		$('#subLevelTable').dataTable({
		"aaSorting": [[ 1, "asc" ]],
		"iDisplayLength": 300,
		"aLengthMenu": [[300,200,100, -1], [300,200,100,"All"]]
		});
		
		//generateReport('subLevelTable');
	}
	*/
	
	/* function generateReport(tableId)
	{
		tableToExcel(tableId, 'ELECTION RESULT');
	}
	 */
	
	

</script>
</body>
</html>