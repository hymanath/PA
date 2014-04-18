<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Merge Keywords</title>
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
<style>
   .yui-dt-bd{
      max-height:900px;
	  overflow-y:scroll;
   }
</style>
<script type="text/javascript">
  var prevLimit =100;
  var prevSearchStr="";
  var selectedKeywordIds = new Array();
  
  var _timer = 0;
	function delayedCallMe() {
		if (_timer)
			window.clearTimeout(_timer);
		_timer = window.setTimeout(function() {
			getKeywords(prevLimit,$.trim($("#searchStrId").val()));
		}, 500);
	}
   function getLatestKeywords(){
   
       var limit = $("#totalRecords").val();
	   var searchStr = $.trim($("#searchStrId").val());
       getKeywords(limit,searchStr);
   }
	
	 $(".keywordCheckId").live("click",function(){
	      var fileId = $(this).attr('value');
		  if($(this).is(':checked') && selectedKeywordIds.indexOf(fileId) == -1)
		   selectedKeywordIds.push(fileId);
		  else{
		   if(selectedKeywordIds.indexOf(fileId) != -1)
			selectedKeywordIds = jQuery.removeFromArray(fileId, selectedKeywordIds);
		  }
		   var length = selectedKeywordIds.length;
		 $("#selectedNewsCount").html(''+length+''); 
		 $("#selectedNewsCount1").html(''+length+''); 
			
	   });
	function getKeywords(limit,searchStr){
	try{
	 {
				prevLimit = limit;
				prevSearchStr = searchStr;
			 
			 YAHOO.widget.DataTable.checkBox = function(elLiner, oRecord, oColumn, oData) 
				{
					var str='';
					var name = oData;
					var keywordId = oRecord.getData("id");
					
					if(selectedKeywordIds.indexOf(""+keywordId+"") != -1)
					 str +="<input type='checkbox' class='keywordCheckId'  value='"+keywordId+"' checked='checked'/>";
					else
					 str +="<input type='checkbox' class='keywordCheckId'   value='"+keywordId+"' />";
					elLiner.innerHTML=str;
								
				};
				
				
				  var newsColumns = [
						   {key:"Select",label:"SELECT",formatter:YAHOO.widget.DataTable.checkBox},
						   {key:"name", label:"KEYWORD"}
				  ];
			  
			  
			  var newsDataSource = new YAHOO.util.DataSource("getAllKeyWords.action?searchStr="+prevSearchStr+"&");
			  newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
			  newsDataSource.responseSchema = {
			  resultsList: "selectOptionsList",
			   fields: ["id","name"],
								

				metaFields: {
				totalRecords: "id" // Access to value in the server response
				 }
			  };
			  
			  
			  var myConfigs = {
			initialRequest: "startIndex=0&results="+prevLimit, // Initial request for first page of data
			dynamicData: true, // Enables dynamic server-driven data
			   paginator : new YAHOO.widget.Paginator({ 
							rowsPerPage    : prevLimit 
							}),  // Enables pagination
			  scrollable: "y",
              height:"900px"
			};

			var newsDataTable = new YAHOO.widget.DataTable("keyWordsTable",
			newsColumns, newsDataSource, myConfigs);

			newsDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
			oPayload.totalRecords = oResponse.meta.totalRecords;
			//$("#selectAllCheckBox").removeAttr("checked");
			$(".yui-dt-bd").removeAttr('style'); 
			$(".yui-dt-bd").css("max-height","900px");
			$(".yui-dt-bd").css("overflow","auto");
			return oPayload;
			  
			}
	 }
	}catch(e){
	  //alert(e);
	 }
	}
	
	function mergeKeywords(){
	         $("#statusDiv2").html("");
	         $("#errorMessageNewDiv").html("");
	         var errmsg ="";
			 var newKeyword = $.trim($('#aliasKeyword').val());
	          if(selectedKeywordIds.length == 0){
			  
			    errmsg ="<div>Please select keywords to merge.<div>"
			  }
			  if(newKeyword.length == 0){
			   errmsg+="<div>Please enter new keyword.<div>"
			  }
			  if(isValid(newKeyword)){
		        errmsg+="<div>Keyword Name should not contain #,$,%,& Special charactors.<div>"
	          }
			  if(errmsg.length > 0){
			   $("#errorMessageNewDiv").html(errmsg);
			    return;
			  }
			  var keywordsList = "";
			  for(var i in selectedKeywordIds){
			    keywordsList = selectedKeywordIds[i]+","+keywordsList;
			  }
			  $("#mergekeywordButnDIV").attr("disabled","disabled");	
			  $("#ajaxcallimg").show();
	          $.ajax({
				type: "GET",
				url: "isKeywordExistAction.action",
				data: { keywords :keywordsList,newKeyword:newKeyword,task:"mergeKeywords" }
				})
				.done(function( msg ) {
				 $("#mergekeywordButnDIV").removeAttr("disabled");	
			     $("#ajaxcallimg").hide();
				var status = msg.toLowerCase();
				if(status.indexOf("success") !=-1){
				   $('#aliasKeyword').val('');
				    selectedKeywordIds = new Array();
					getKeywords(prevLimit,prevSearchStr);
					$('#statusDiv2').html('<span style="color:green;font-weight:bold;">'+msg+'</span>');
				}
				else{
					$('#statusDiv2').html('<span style="color:red;font-weight:bold;">'+msg+'</span>');
				}
					window.setTimeout(function() {$('#statusDiv2').html("");}, 5000);
			  });
	}
	
	function isValid(str){
		 var iChars = "#$%&";
		 var flag = false;
			for (var i = 0; i < str.length; i++) {
				if (iChars.indexOf(str.charAt(i)) != -1) {			
					flag = true;
				}
			}
			return flag;
    }
	
	jQuery.removeFromArray = function(value, arr) {
      return jQuery.grep(arr, function(elem, index) {
      return elem !== value;
     });
   };
   
   $("#selectAllCheckBox").live("click",function(){
    var status =  $("#selectAllCheckBox").is(':checked');
	if(status){
	 $(".keywordCheckId").each(function(){
	   $(this).attr("checked","checked");
	   var keywordId = $(this).val();
	     if(selectedKeywordIds.indexOf(""+keywordId+"") == -1)
          selectedKeywordIds.push($(this).attr("value"));
	 });
	  
	}else{
	   selectedKeywordIds = new Array();
	   $(".keywordCheckId").each(function(){
	        $(this).removeAttr("checked");
	    });
	}
    
      var length = selectedKeywordIds.length;
          $("#selectedNewsCount1").html(''+length+''); 
		   $("#selectedNewsCount").html(''+length+''); 
  
   });
</script>	
	
</head>
<body>
  <center>
    <div class="container">
	  <div class="span12">
	     <div class="span12">
	       <h4 STYLE=" padding-top:29px;margin-bottom:25px;">MERGE KEYWORDS</h4>
		 </div>
		 
	     <div class="span12"><span class="span5 pull-left">Show <select id="totalRecords" onchange="getLatestKeywords();"><option value="100">100</option><option value="200">200</option><option value="500">500</option><option value="1000">1000</option></select> Records Per Page</span>  <span    class="span5 pull-right">Search:<input id="searchStrId" onkeyup="delayedCallMe();" type="text"></input></span></div>
         <div><b>Total KeyWords Selected : <span id="selectedNewsCount">0</span></b></div>
		 <div class="span12">
		    <div id="keyWordsTable" class="yui-skin-sam yui-dt-sortable"></div>
		 </div>
		 <div><b>Total KeyWords Selected : <span id="selectedNewsCount1">0</span></b></div>
		 <div>Enter New Keyword : <input type="text" id="aliasKeyword"></div>
		  Hint: New keyword should not contain #,$,%,& Special charactors.
		  <div><input type="button" id="mergekeywordButnDIV" class="btn btn-success" value="Merge Keywords" onclick="mergeKeywords()"></input><img id="ajaxcallimg" style="display:none;padding-left:10px;padding-top: 10px;" src="images/search.jpg"></div>
		  <div class="span12"><div style="color:red;font-weight:red;" id="errorMessageNewDiv"></div></div>
		  <div id="statusDiv2"></div>
	  </div>
    </div>
  </center> 
  <script type="text/javascript">
     getKeywords(prevLimit,prevSearchStr);
  </script>
</body>
</html>