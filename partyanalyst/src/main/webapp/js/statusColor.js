
var statusColorArr = [];
var statusarr = ['Not Verified','In Progress','Completed','Not Eligible','Not Possible','approved','Verified','Applied'
,'Intimated','Waiting for Documents','Documents Collected','Documents Submitted','Rejected','Cheque Received'];
function setcolorsForStatus()
	{
		statusColorArr = new Array();
		var colorStatic = new Array('#31708f','#f39c12','#449d44','#c0392b','#e74c3c',
		'#2ecc71','#1abc9c','#d35400','#3498db','#2980b9','#9b59b6','#8e44ad','#7f8c8d','#16a085');
		
		var colorCount = 0;
		
		for(var i in statusarr)
		{
		 
				var obj = {
				 status : statusarr[i],
				 color : colorStatic[colorCount]
				}
				statusColorArr.push(obj)
			  
				if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
				 colorCount++;
		}
		
		return statusColorArr;
	}

	function getColorCodeByStatus(status)
	{

		if(statusColorArr != null && statusColorArr.length > 0)
		{
			for(var i in statusColorArr)
			{
			
				if(statusColorArr[i].status.toLowerCase() == status.toLowerCase())
					return statusColorArr[i].color;
			}
		}
	}


// Financial


var financialColorArr = [];
var typearr = ['EDP','CM Relief Fund','Educational','Financial Support','Pension'];
function setcolorsForFinancialchart()
	{
		financialColorArr = new Array();
		var colorStatic = new Array('#D64D54','#66CDCC','#00B17D','#663300','#4169E1');
		
		var colorCount = 0;
		
		for(var i in typearr)
		{
		 
				var obj = {
				 type : typearr[i],
				 color : colorStatic[colorCount]
				}
				financialColorArr.push(obj)
			  
				if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
				 colorCount++;
		}
		
		return financialColorArr;
	}

	function getColorCodeByType(type)
	{

		if(financialColorArr != null && financialColorArr.length > 0)
		{
			for(var i in financialColorArr)
			{
				if(financialColorArr[i].type == type)
					return financialColorArr[i].color;
			}
		}
	}

