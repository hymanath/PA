<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js">
</script> 
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<title></title>
<style>
#showNews #showNewsCount table{
border:1px solid #dddddd;
}
thead{
background:#dddddd;
}
.yui-skin-sam .yui-pg-container{

text-align:center;
}
</style>
<script type="text/javascript">
function getNews(task,queryType,fileType,sourceId,languegeId,categoryId,newsImportanceId,locationScope,location){
var jsObj=
	      { 
		    queryType:queryType,
			fileType:fileType,
			sourceId :sourceId,
            languegeId :languegeId,
            categoryId :categoryId,
            newsImportanceId :newsImportanceId,
            locationScope :locationScope,
            location :location,
			task:task
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "getNewsToDisplayAction.action?"+rparam;						
      callAjax(jsObj,url);
}
function callAjax(jsObj,url){
var myResults;	
var callback = {			
    success : function( o ) {
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);	
			   
			 if(jsObj.queryType == "getCount")
			 {
			   showNewsCountDetails(myResults,jsObj);
			 }
			 else if(jsObj.queryType == "getNews")
			 {	
				showNewsDetails(myResults);
			 }
			
			}catch (e) {   		
		   	alert("Invalid JSON result" + e);   
		}  
    },
    scope : this,
    failure : function( o ) {
     			//alert( "Failed to load result" + o.status + " " + o.statusText);
              }
    };

YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function getMaxCount(result)
{
  var count = 0;
  for(var i in result){
   if(result[i].fileVOList !=null && result[i].fileVOList.length > 0)
      if(result[i].fileVOList.length > count)
         count = result[i].fileVOList.length;
	}
   return count;
}
function showNewsCountDetails(result,jsObj){
document.getElementById("showNewsCount").innerHTML='';
  var maxCount = getMaxCount(result);
  var str = "";
  str+= '<table border="1px" align="center">';
  str+= '     <tr style="text-align:center">';
   str+= '       <th>CATEGORY</th><th>SOURCE</th><th>LANGUAGE</th><th>NEWS IMPORTANCE</th><th>IMPACT LEVEL</th><th>LOCATION</th>';
   str+= '     </tr>';
  for(i=0 ; i < maxCount ; i++)
   {
   str+= '<tr style="text-align:center">';
      if(result[0].fileVOList[i] != null)
       str+= '<td>'+result[0].fileVOList[i].categoryType+' -  <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'Public\',\'\',\'\',\''+result[0].fileVOList[i].categoryId+'\',\'\',\'\',\'\');"> '+result[0].fileVOList[i].sizeOfGallary+'</a></td>';
	  else
	   str+= '<td style="text-align:center">--</td>';
	  if(result[1].fileVOList[i] != null)
	    str+= '<td>'+ result[1].fileVOList[i].source+' -   <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'Public\',\''+result[1].fileVOList[i].sourceId+'\',\'\',\'\',\'\',\'\',\'\');"> '+result[1].fileVOList[i].sizeOfGallary+'</a></td>';
	  else
	    str+= '<td>--</td>';
	  if(result[2].fileVOList[i] != null)
	   str+= '<td>'+  result[2].fileVOList[i].language+' -   <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'Public\',\'\',\''+result[2].fileVOList[i].languegeId+'\',\'\',\'\',\'\',\'\');">'+ result[2].fileVOList[i].sizeOfGallary+'</a></td>';
	  else
	    str+= '<td>--</td>';
	  if(result[3].fileVOList[i] != null)
	    str+= '<td>'+ result[3].fileVOList[i].importance +' -   <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'Public\',\'\',\'\',\'\',\''+result[3].fileVOList[i].newsImportanceId+'\',\'\',\'\');">'+result[3].fileVOList[i].sizeOfGallary+'</a></td>';
	  else
	   str+= '<td>--</td>';
	  if(result[4].fileVOList[i] != null)	
	    str+= '<td>'+ result[4].fileVOList[i].locationScopeValue+' -   <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'Public\',\'\',\'\',\'\',\'\',\''+result[4].fileVOList[i].locationScope+'\',\'\');">'+ result[4].fileVOList[i].sizeOfGallary+'</a></td>';
	  else
	    str+= '<td>--</td>';
	  if(result[5].fileVOList[i] != null)
	      if(result[5].fileVOList[i].location != null)
	        str+= '<td>'+ result[5].fileVOList[i].locationValue+' -  <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'Public\',\'\',\'\',\'\',\'\',\''+result[5].fileVOList[i].locationScope+'\',\''+result[5].fileVOList[i].location+'\');">'+ result[5].fileVOList[i].sizeOfGallary+'</a></td>';
	      else
		    str+= '<td>'+ result[5].fileVOList[i].locationValue+' -  <a href="javascript:{}" onclick="getNews(\''+jsObj.task+'\',\'getNews\',\'Public\',\'\',\'\',\'\',\'\',\''+result[5].fileVOList[i].locationScope+'\',\'\');">'+ result[5].fileVOList[i].sizeOfGallary+'</a></td>';
	  else
	   str+= '<td>--</td>';
	str+= '	  </tr>';
   }
  
  str+= '<table>';
  
 document.getElementById("showNewsCount").innerHTML = str;
}
function showNewsDetails(result){

  document.getElementById("showNews").innerHTML='';
  var newsResultColumnDefs = [ 		    	             
		    	            
							{key:"categoryType", label: "NEWS CATEGORY", sortable: true},
		    	           	{key:"source", label: "SOURCE", sortable: true},
							{key:"fileTitle1", label: "TITLE", sortable: true},
							{key:"description", label: "DESCRIPTIONS", sortable: true},
		    				{key:"locationScopeValue", label: "IMPACT AREA",sortable:true},
							{key:"locationValue", label: "AREA NAME", sortable: true},
							{key:"fileDate", label: "NEWS DATE", sortable: true}
							
		    	        ]; 
	var newsResultDataSource = new YAHOO.util.DataSource(result); 
	


    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,
				template : "{PageLinks} {RowsPerPageDropdown}",
                pageLinks : 5, 
                rowsPerPageOptions : [ 5, 10, 15, 20 ]
			    }) 
				};
	var myDataSource = new YAHOO.util.DataSource(result);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "categoryType","source","fileTitle1","description","locationScopeValue","locationValue","fileDate"]
					};

		var newsResultDataSource = new YAHOO.widget.DataTable("showNews", newsResultColumnDefs,myDataSource, myConfigs);
}
</script>
</head>
<body>
    <table align="center">
	   <tr>
	      <td><input type="radio" name="dates" checked="true" value="today" onclick="getNews('byTodayDate','getCount','Public','','','','','','');getNews('byTodayDate','getNews','Public','','','','','','');"><font color="navy"><b>Today</b></font></input></td>
		  <td><input type="radio" name="dates"  value="thisweek" onclick="getNews('byThisWeek','getCount','Public','','','','','','');getNews('byThisWeek','getNews','Public','','','','','','');"><font color="navy"><b>This Week</b></font></input></td>
		  <td><input type="radio" name="dates"  value="thismonth" onclick="getNews('byThisMonth','getCount','Public','','','','','','');getNews('byThisMonth','getNews','Public','','','','','','');"><font color="navy"><b>This Month</b></font></input></td>
	   </tr>
    </table>
<div id="showNewsCount"></div>
<div id="showNews" class="yui-skin-sam" style="width:900px;" ></div>
<script type="text/javascript">
getNews("byTodayDate","getCount","Public","","","","","","");
getNews("byTodayDate","getNews","Public","","","","","","");
</script>
</body>
</html>