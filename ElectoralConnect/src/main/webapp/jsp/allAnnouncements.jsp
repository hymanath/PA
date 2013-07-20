<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
#allNotificationDiv{width:800px;margin-left:auto;margin-right:auto;}
.comment_sec{ border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;box-shadow: 1px 1px 1px -1px;margin-bottom: 10px;margin-left:60px;padding: 12px;}
	.title_sec2{font-family: icon;font-size: 15px;font-weight: bold;}
	.title_sec3{font-family: icon;font-size: 15px;font-weight: bold;}
	.title_sec4{ font-family: Helvetica;font-size: 13px;}
	.title_sec1{color: #0989C9; font-size: 14px;font-weight: bold;text-transform: uppercase;}
	.getallcomment1{margin-top: 8px;padding: 7px;background-color:#fff;border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;line-height: 24px;}
	a:hover{color:red;}
	.link{color:#548BD4;font-weight:bold;}
	
	h3{display:inline-block;}
	#announceHead{text-align:center;border-bottom:1px solid #ccc;margin-bottom:2px;}
	#paginationId{width:250px;margin-left:auto;margin-right:auto;margin-top:4px;}
	
</style>
<body>
<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>

<div id="allNotificationDiv" class="getallcomment1" style="">
</div>
<div id="paginationId"></div>
<script>
var myResults = window.opener.allAnnouncements;
var myjsobj= window.opener.announcementsJsObj;

buildAllAnnouncementsWithPaging(myResults,myjsobj);
function buildAllAnnouncementsWithPaging(myResults,jsObj){
startRecord=jsObj.startRecord;
if(myResults != null)
{
	var str = "";
	 str +='<div id="announceHead"><h3> All '+myjsobj.announcementName+'</h3></div>';
	for(var i in myResults)
	{
	   
		str +='<div class="comment_sec" style="width:650px;">';
		
		str +='<div class=" title_sec1">'+myResults[i].title+'</div>';
		str +='<div class="title_sec4">'+myResults[i].description+'</div>';
		str +='<div ><span class="title_sec2">Date Posted:</span><span>'+myResults[i].dateString+' </span></div>';
		
		if(myResults[i].filePath != null && myResults[i].filePath != "")
		{
		   str +='<div ><span class="title_sec3">Releted Document:</span><a href="'+myResults[i].filePath+'"><span>'+myResults[i].fileName+'</span></a></div>';
		}
		
		str +='</div>';
		
   } 
   
  $('#allNotificationDiv').html(str);
		var itemsCount=myResults.allAnnouncementsCount;
		var maxRecord=myjsobj.maxRecord;
		if(startRecord==0){
			$("#paginationId").pagination({
				items: itemsCount,
				itemsOnPage: maxRecord,
				cssStyle: 'light-theme',
				onPageClick: function(pageNumber, event) {
					var num=(pageNumber-1)*10;
					getAllAnnoncementPaging(myjsobj.announcementName,num,10);
				}
			});
			
		}
}
}


function getAllAnnoncementPaging(name,startRecord,maxRecord)
{
var announcenentTypeId=myjsobj.announcenentTypeId;
	var jsObj =
		{  	
			announcenentTypeId : announcenentTypeId,
			announcementName   : name,
			task               : "getAllAnnouncementsForPaging",
			startRecord :startRecord,
			maxRecord:maxRecord
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getTopAnnouncementsAction.action?"+rparam;
		callAjaxForPaging(jsObj, url);
}

function callAjaxForPaging(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								
								if(jsObj.task =="getAllAnnouncementsForPaging")
								{
									buildAllAnnouncementsWithPaging(myResults,jsObj);
								}
								
								}catch (e) {
									console.log(e);
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}

</script>
</body>
</html>