<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="allNotificationDiv"></div>
<script>
var myResults = window.opener.allAnnouncements;
	{
	    var str = "";
		for(var i in myResults)
		{
			str += '<div></br>';
			str += '<b>Title : </b><span>'+myResults[i].title+'</span></br>';
			str += '<b>Description : </b><span>'+myResults[i].description+'</span></br>';
			str += '<b>Date : </b><span>'+myResults[i].dateString+'</span></br>';
			str += '<b>Name : </b><span>'+myResults[i].name+'</span></br>';
			if(myResults[i].filePath =! null && myResults[i].filePath != "")
			{
				str += '<b>File Title : </b><a href="'+myResults[i].filePath+'"><span>'+myResults[i].fileTitle+'</span></a></br>';
				str += '<b>File Description : </b><span>'+myResults[i].fileDescription+'</span></br>';
				str += '<b>File Date : </b><span>'+myResults[i].fileDate+'</span></br>';
			}
		   str += '</div>';
	   } 
	  $('#allNotificationDiv').html(str);
	}
	


</script>
</body>
</html>