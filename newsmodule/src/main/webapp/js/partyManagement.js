/*function buildLocationWiseNews(results)
{
	alert('adfgaj');
	debugger;
	var str='';
	$("#newsReportAjaxImg").css("display","none");
	if(results != null && results != '')
	{
		for(var i in results)
		{
		str+='<div class="container">';
		str+='<h4 id="mainheading">'+results[i].scope+' Wise </h4>';
		for(var j=0; j< results[i].fileVOList.length;j++)
			{
			for(var k=0;k<results[i].fileVOList[j].fileVOList.length;k++)
			{
		var source = results[i].fileVOList[j].fileVOList[k].source.trim();
		str+=' <div class="row-fluid">';
        str+='<div class="span12 well">';
        str+='<div class="media">';
        str+=' <div class="media-body">';
        str+='<hgroup>';
		if(source == "Eenadu Telugu")
				{
				str+="<h4 class='media-heading'><span class='enadu fontStyle pagerRow' style='font-weight:bold;'><a href='javascript:{getNewsDetailsByContentId("+results[i].fileVOList[j].fileVOList[k].contentId+")}'>"+results[i].fileVOList[j].fileVOList[k].fileTitle1+"</a></span></h4>";
				}
				else
				{
				str+="<h4 class='media-heading' style='text-transform: capitalize;color: #005580;'><a  href='javascript:{getNewsDetailsByContentId("+results[i].fileVOList[j].fileVOList[k].contentId+")}'>"+results[i].fileVOList[j].fileVOList[k].fileTitle1+"</a></h4>";
				}
   
		str+=' <h5 style="border-bottom:1px solid #333"><span class="label">'+results[i].fileVOList[j].fileVOList[k].source+'</span><i class="pull-right">-'+results[i].fileVOList[j].fileVOList[k].locationName+' </i></h5>';
		 str+='</hgroup>';
		// str+='<a class="text-center" href="#"><img alt="" src="images/Logo.png" class="media-object"></a>';
         str+=' </div>';
         str+=' </div>';
         str+='</div>';
		 str+=' </div>';
		 str+='</div>';
			}
			}
		}
		 str +='<br/><input type="button" value="submit" class="btn btn-info" id="savenewsReport" onclick="saveNewsReport()"/>';
		 str+='<img style="display: none;" src="images/search.jpg" id="savenewsAjaxImg">';
		 $("#locationWiseNewsDiv").html(str);
	}
}*/