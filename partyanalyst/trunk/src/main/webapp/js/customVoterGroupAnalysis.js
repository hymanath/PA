
function getCasteWiseCustomVotersCount()
{
  var jsObj=
  {
	customVoterGroupId:customVoterGroupId,
	task:"getCasteWiseCustomVotersCount"
  };

  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
  var url = "getCasteWiseCustomVotersCountAction.action?"+rparam+"&save=";	
  callAjax(jsObj,url);
}


function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
 	 success : function( o ) {
	  try {												
		myResults = YAHOO.lang.JSON.parse(o.responseText);
		
			if(jsObj.task == "getCasteWiseCustomVotersCount")
			  buildCasteWiseCustomVotersCount(myResults,jsObj);

			}catch (e) {
							   
						}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
	
	var castesArr=[];
	var totalVotersArr=[];
	var maleVotersArr=[];
	var femaleVotersArr=[];
	var castPercentageArr=[];
	var castArray=[];
		
	function buildCasteWiseCustomVotersCount(results,jsObj)
	{
		
		$("#casteWiseVotersCountInnerDiv").html('');
		if(results == null || results.length == 0)
		{
			$("#casteWiseVotersCountInnerDiv").html('No Data Found.');
			return;
		}
		var str = '';
		str +='<table class="table table-bordered table-striped table-hover">';
		str +='<tr>';
		str +='<th>Caste</th>';
		str +='<th>Caste Category</th>';
		str +='<th>Male Voters</th>';
		str +='<th>Female Voters</th>';
		str +='<th>Voters</th>';
		str +='<th>Caste Percentage</th>';
		str +='</tr>';
		for(var i in results)
		{
		  str +='<tr>';
		  str +='<td>'+results[i].castName+'</td>';
		  str +='<td>'+results[i].casteCategoryName+'</td>';
		  str +='<td>'+results[i].maleVoters+'</td>';
		  str +='<td>'+results[i].femaleVoters+'</td>';
		  str +='<td>'+results[i].totalVoters+'</td>';
		  str +='<td>'+results[i].castePercentage+'</td>';
		  str +='</tr>';
		}
		str +='</table>';
		$("#casteWiseVotersCountInnerDiv").html(str);
		
		castArray = results;
		
		buildGraphBySlide(castArray);
		
	}


 var casteRange;	
$(function() {
$( "#slider" ).slider({
value:1,
min: 0,
max: 40,
step: 1,
slide: function( event, ui ) {
$( "#amount" ).val( "Percentage of Voters Caste: " + ui.value +" %");
},
change: function( event, ui ) {
$( "#amount" ).val( "Percentage of Voters Caste: " + ui.value +" %");
casteRange=ui.value;
buildGraphBySlide(castArray,casteRange);
}
});
casteRange=$( "#amount" ).val( "Percentage of Voters Caste: " + $( "#slider" ).slider( "value" ) +" %");
casteRange=$( "#slider" ).slider( "value" );
});



function buildGraphBySlide(castArray,casteRange)
{
	if(casteRange == null)
		casteRange = 1;

	    castesArr=[];
		totalVotersArr=[];
		maleVotersArr=[];
		femaleVotersArr=[];
		castPercentageArr=[];

		for(var i in castArray)
		{
		  if(castArray[i].castePercentage >=casteRange)
		  {
			 castesArr.push(castArray[i].castName);
			 totalVotersArr.push(castArray[i].totalVoters);
			 maleVotersArr.push(castArray[i].maleVoters);
			 femaleVotersArr.push(castArray[i].femaleVoters);
			 castPercentageArr.push(castArray[i].castePercentage);
		  }
		}
	 buildCasteWiseCustomVotersCountGraph(castesArr,totalVotersArr,maleVotersArr,femaleVotersArr,castPercentageArr);

	}

  function buildCasteWiseCustomVotersCountGraph(cs,tv,mv,fv,cp)
  {

   $('#casteWiseVotersCountGrapDiv').highcharts({
            chart: {
                zoomType: 'xy',
				marginRight: 130,
                marginBottom: 100,
				width:890,height:390
            },
			
            title: {
                text: 'Caste Wise Analysis'
            },
            
            xAxis: [{
                categories: cs,
				labels: {
                                align:'right',
                                style: {
                                      cursor: 'pointer',
                                      fontSize: '14px',
                                      //fontWeight:'bold'
                                },
                                rotation:300, 
                            } 
            }],
            yAxis: [{ // Primary yAxis
				min: 0,
                labels: {
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {
                        color: '#89A54E'
                    }
                },
                title: {
                    text: 'caste Percentage ',
                    style: {
                        color: '#89A54E'
                    }
                },
                opposite: true
    
            }, { // Secondary yAxis
                gridLineWidth: 0,
                title: {
                    text: 'Caste Percentage/Total Voters',
                    style: {
                        color: '#4572A7'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {
                        color: '#4572A7'
                    }
                }
    
            }, { // Tertiary yAxis
                gridLineWidth: 0,
                title: {
                    text: 'Total Voters',
                    style: {
                        color: '#AA4643'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {
                        color: '#AA4643'
                    }
                },
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            legend: {
                layout: 'vertical',
                align: 'left',
                x: 600,
                verticalAlign: 'top',
                y: 40,
                floating: true,
                backgroundColor: '#FFFFFF'
            },
            series: [ {
                name: 'Total Voters',
                type: 'spline',
                color: '#AA4643',
                yAxis: 2,
                data: tv,
               /* marker: {
                    enabled: false
                },*/
                //dashStyle: 'shortdot',
                tooltip: {
                    valueSuffix: ''
                }
    
            }, {
                name: 'Caste Percentage',
                color: '#89A54E',
                type: 'spline',
                data: cp,
                tooltip: {
                    valueSuffix: ''
                }
            }]
        });
		$('tspan:last').hide();
  }