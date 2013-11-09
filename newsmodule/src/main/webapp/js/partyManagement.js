function buildLocationWiseNews(results)
{
	var str='';
	$("#newsReportAjaxImg").css("display","none");
	
	if(results != null && results != '')
	{
	$("#locationWiseNewsDiv").css("display","block");
		for(var i in results)
		{
		str+='<div class="container">';
		str+='<h4 id="mainheading">'+results[i].scope+' WISE </h4>';
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
			
				str+="<h4 class='media-heading'><span class='enadu fontStyle pagerRow' style='font-weight:bold;'>";
				str+='<input type="checkbox" class="find-table" value='+results[i].fileVOList[j].fileVOList[k].contentId+'>&nbsp;';
				str+="<a href='javascript:{getNewsDetailsByContentId("+results[i].fileVOList[j].fileVOList[k].contentId+")}'>"+results[i].fileVOList[j].fileVOList[k].fileTitle1+"</a></span></h4>";
				}
				else
				{
				str+="<h4 class='media-heading' style='text-transform: capitalize;color: #005580;'>";
				str+='<input type="checkbox" class="find-table" value='+results[i].fileVOList[j].fileVOList[k].contentId+'>&nbsp;';
				str+="<a  href='javascript:{getNewsDetailsByContentId("+results[i].fileVOList[j].fileVOList[k].contentId+")}'>"+results[i].fileVOList[j].fileVOList[k].fileTitle1+"</a></h4><br/>";
				}
			
				str+='<div  class="span3">';
				str+='<span style="font-weight:bold;font-size:14px;"> Source : </span>';
			for(var k1=0;k1<results[i].fileVOList[j].fileVOList[k].fileVOList.length;k1++)
				{
			    str+='<span class="label"> '+results[i].fileVOList[j].fileVOList[k].fileVOList[k1].source+'</span>&nbsp;';
				}
				str+='</div>';
				str+='<div  class="span4">';
				str+='<span style="font-weight:bold;font-size:14px;"> Candidate Name : </span>';
			    str+='<span class="label"> '+results[i].fileVOList[j].fileVOList[k].candidateName+'</span>&nbsp;';
				str+='</div>';
				str+='<div  class="span3 ">';
				str+='<span style="font-weight:bold;font-size:14px;"> file Date : </span>';
			    str+='<span class="label"> '+results[i].fileVOList[j].fileVOList[k].fileDate+'</span>&nbsp;';
				str+='</div>';
			     str+='<i class="pull-right">-'+results[i].fileVOList[j].fileVOList[k].locationName+' </i>';
		        str+='</hgroup>';
		
				 str+=' </div>';
				 str+=' </div>';
				 str+='</div>';
				 str+=' </div>';
				 str+='</div>';
			}
			}
		}
		 str +='<br/><div class="form-actions text-center"><input type="button" value="submit" class="btn btn-info" id="savenewsReport" onclick="saveNewsReport()"/>';
		 str+='<img style="display: none;" src="images/search.jpg" id="savenewsAjaxImg"></div>';
		 $("#locationWiseNewsDiv").html(str);
	}
}