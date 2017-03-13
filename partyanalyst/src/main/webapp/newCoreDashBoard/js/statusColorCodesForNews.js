
var statusColorArr = [];
var statusarr = ['TDP','YSRC','INC','BJP'];
var sessionstatusColorArr=[];
var sessionArr = ['All Sessions','Morning Session','Afternoon Session','Session-3','Session-4','Session-5','Session-6','Session-7','Session-8','Session-9','Session-10'];
function setcolorsForStatus()
	{
		statusColorArr = new Array();
		var colorStatic = new Array('#FFCB00','#005DB0','#3D9834','#FD9832');
		
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
	
	function setSessioncolorsForStatus()
	{
		sessionstatusColorArr = new Array();
		var colorStatic = new Array('#0066DC','#320199','#660032','#B114E5','#EF0EAC','#2A6EB7','#517296','#CE7393','#5410A8','#E27C88','#7744DD');
		
		var colorCount = 0;
		
		for(var i in sessionArr)
		{
		 
				var obj = {
				 status : sessionArr[i],
				 color : colorStatic[colorCount]
				}
				sessionstatusColorArr.push(obj)
			  
				if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
				 colorCount++;
		}
		
		return sessionstatusColorArr;
	}
	function getColorCodeBySessionStatus(status)
	{

		if(sessionstatusColorArr != null && sessionstatusColorArr.length > 0)
		{
			for(var i in sessionstatusColorArr)
			{
			
				if(sessionstatusColorArr[i].status.toLowerCase() == status.toLowerCase())
					return sessionstatusColorArr[i].color;
			}
		}
	}