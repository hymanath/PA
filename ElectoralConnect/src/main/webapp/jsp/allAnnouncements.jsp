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
.comment_sec{ border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;box-shadow: 1px 1px 1px -1px;margin-bottom: 10px;margin-left:100px;padding: 12px;}
	.title_sec2{font-family: icon;font-size: 15px;font-weight: bold;}
	.title_sec3{font-family: icon;font-size: 15px;font-weight: bold;}
	.title_sec4{ font-family: open sans;font-size: 13px;}
	.title_sec1{color: #0989C9; font-size: 14px;font-weight: bold;text-transform: uppercase;}
	.getallcomment1{margin-top: 8px;padding: 7px;background-color:#fff;border: 1px solid #C3C3C3;border-radius: 5px 5px 5px 5px;line-height: 24px;}
	a:hover{color:red;}
	.link{color:#548BD4;font-weight:bold;}
	
	
</style>
<body>
<div id="allNotificationDiv" class="well getallcomment1" style="">
</div>
<script>
var myResults = window.opener.allAnnouncements;
if(myResults != null)
{
	var str = "";
	 str +='	<legend>All Notifications</legend>';
	for(var i in myResults)
	{
	   
		str +='<div class="comment_sec" style="width:550px;">';
		
		str +='<div class=" title_sec1">'+myResults[i].title+'</div>';
		str +='<div class="title_sec4">'+myResults[i].description+'</div>';
		str +='<div ><span class="title_sec2">Date Posted:</span><span>'+myResults[i].dateString+' </span></div>';
		<!--str += '<b>Name : </b><span>'+myResults[i].name+'</span></br>';-->
		if(myResults[i].filePath != null && myResults[i].filePath != "")
		{
		   str +='<div ><span class="title_sec3">Releted Document:</span><a href="'+myResults[i].filePath+'"><span>'+myResults[i].fileName+'</span></a></div>';
			
			<!--str += '<b>File Description : </b><span>'+myResults[i].fileDescription+'</span></br>';-->
			<!--str += '<b>File Date : </b><span>'+myResults[i].fileDate+'</span></br>';-->
		}
		
		str +='</div>';
		
   } 

  $('#allNotificationDiv').html(str);
}
	


</script>
</body>
</html>