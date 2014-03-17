function getTotalNewsWithPagination()
{

 responseFileIdsArray = new Array();
 
 $("#errorMsgNewsDiv").html('');
 $("#profileManagementMainOuterDiv4").addClass("yui-skin-sam yui-dt-sortable");
 $("#profileManagementMainOuterDiv4").css({'margin-left': 'auto', 'margin-right': 'auto', 'float':' none', 'width': '950px'});
  var newsColumns = [
				  
				   {key:"id", label:"ID"},
				   {key:"url", label:"URL"},
				   //{key:"content", label:"CONTENT"},
				   {key:"mediaSource", label:"MEDIA SOURCE"},
				   {key:"newsTitle", label:"NEWS TITLE"},
				   {key:"place", label:"PLACE"},
				   {key:"fileDateAsString", label:"DATE"}
		];
  
  var newsDataSource = new YAHOO.util.DataSource("getArticleNewsAction.action");
  newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
  newsDataSource.responseSchema = {
  resultsList: "articleVOList",
   fields: [
             
			        "id","mediaSource","newsTitle", "place","url", "fileDateAsString"],
			        

    metaFields: {
    totalRecords: "totalRecords" // Access to value in the server response
     },
  };
  
  
  var myConfigs = {
//initialRequest: "startIndex=0&results=30", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
 // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 30 
			    })  // Enables pagination
};

var newsDataTable = new YAHOO.widget.DataTable("profileManagementMainOuterDiv4",
newsColumns, newsDataSource, myConfigs);


newsDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
  
}
}
