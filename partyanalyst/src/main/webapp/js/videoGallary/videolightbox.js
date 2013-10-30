jQuery(function () 
{
	var $ = jQuery;
	var swfID = "video_overlay";
	if (!document.getElementById("vcontainer")) 
	{
		$("body").append($("<div id='voverlay'></div>"));
		$("#voverlay").append($("<div id = 'vcontainer'></div>"));
	}

	$("#videogallery a[rel]").overlay(
	{
		api:true,
		expose:0 ? {color:"#ffffff", loadSpeed:400, opacity:0} : null, 
		onClose:function () 
		{
			swfobject.removeSWF(swfID);
		}, 
		onBeforeLoad:function () 
		{
			var c = document.getElementById(swfID);
			if (!c) 
			{
				var d = $("<div></div>");
				d.attr({id:swfID});
				$("#vcontainer").append(d);
			}
			
			var wmkText = "partyanalyst.com";
			var wmkLink = "http://partyanalyst.com";
			c = wmkText ? $("<div></div>") : 0;if (c) 
			{
				c.css({position:"absolute", right:"32px", top:"53px", padding:"0 0 0 0"
			});
			
			$("#vcontainer").append(c);
		}
		
		if (c && document.all)
		{
			var f = $("<iframe src=\"javascript:false\"></iframe>");
			f.css({position:"absolute", left:0, top:0, width:"100%", height:"100%", filter:"alpha(opacity=0)"});
			f.attr({scrolling:"no", framespacing:0, border:0, frameBorder:"no"});
			c.append(f);}var d = c ? $(document.createElement("A")) : c;
			
			if (d)
			{
				d.css({position:"relative", display:"block", 'background-color':"#E4EFEB", color:"#837F80", 'font-family':"Lucida Grande,Arial,Verdana,sans-serif", 'font-size':"11px", 'font-weight':"normal", 'font-style':"normal", padding:"1px 5px", opacity:0.7, filter:"alpha(opacity=70)", width:"auto", height:"auto", margin:"0 0 0 0", outline:"none"});
				d.attr({href:wmkLink});d.html(wmkText);d.bind("contextmenu", function (eventObject) {return false;});c.append(d);}var src = this.getTrigger().attr("href");if (typeof d != "number" && (!c || !c.html || !c.html())) {return;}if (false) {var this_overlay = this;window.videolb_complite_event = function () {this_overlay.close();};window.onYouTubePlayerReady = function (playerId) {var player = $("#" + swfID).get(0);if (player.addEventListener) {player.addEventListener("onStateChange", "videolb_YTStateChange");} else {player.attachEvent("onStateChange", "videolb_YTStateChange");}window.videolb_YTStateChange = function (newState) {if (!newState) {this_overlay.close();}};};}swfobject.createSWF({data:src, width:"100%", height:"100%", wmode:"opaque"}, {allowScriptAccess:"always", allowFullScreen:true, FlashVars:false ? "complete_event=videolb_complite_event()&enablejsapi=1" : ""}, swfID);
			}
		}
		);

		$("#videoGallaryPopUpDiv a[rel]").overlay(
	{
		api:true,
		expose:0 ? {color:"#ffffff", loadSpeed:400, opacity:0} : null, 
		onClose:function () 
		{
			swfobject.removeSWF(swfID);
		}, 
		onBeforeLoad:function () 
		{
			var c = document.getElementById(swfID);
			if (!c) 
			{
				var d = $("<div></div>");
				d.attr({id:swfID});
				$("#vcontainer").append(d);
			}
			
			var wmkText = "partyanalyst.com";
			var wmkLink = "http://partyanalyst.com";
			c = wmkText ? $("<div></div>") : 0;if (c) 
			{
				c.css({position:"absolute", right:"32px", top:"53px", padding:"0 0 0 0"
			});
			
			$("#vcontainer").append(c);
		}
		
		if (c && document.all)
		{
			var f = $("<iframe src=\"javascript:false\"></iframe>");
			f.css({position:"absolute", left:0, top:0, width:"100%", height:"100%", filter:"alpha(opacity=0)"});
			f.attr({scrolling:"no", framespacing:0, border:0, frameBorder:"no"});
			c.append(f);}var d = c ? $(document.createElement("A")) : c;
			
			if (d)
			{
				d.css({position:"relative", display:"block", 'background-color':"#E4EFEB", color:"#837F80", 'font-family':"Lucida Grande,Arial,Verdana,sans-serif", 'font-size':"11px", 'font-weight':"normal", 'font-style':"normal", padding:"1px 5px", opacity:0.7, filter:"alpha(opacity=70)", width:"auto", height:"auto", margin:"0 0 0 0", outline:"none"});
				d.attr({href:wmkLink});d.html(wmkText);d.bind("contextmenu", function (eventObject) {return false;});c.append(d);}var src = this.getTrigger().attr("href");if (typeof d != "number" && (!c || !c.html || !c.html())) {return;}if (false) {var this_overlay = this;window.videolb_complite_event = function () {this_overlay.close();};window.onYouTubePlayerReady = function (playerId) {var player = $("#" + swfID).get(0);if (player.addEventListener) {player.addEventListener("onStateChange", "videolb_YTStateChange");} else {player.attachEvent("onStateChange", "videolb_YTStateChange");}window.videolb_YTStateChange = function (newState) {if (!newState) {this_overlay.close();}};};}swfobject.createSWF({data:src, width:"100%", height:"100%", wmode:"opaque"}, {allowScriptAccess:"always", allowFullScreen:true, FlashVars:false ? "complete_event=videolb_complite_event()&enablejsapi=1" : ""}, swfID);
			}
		}
		);
		
	}
);