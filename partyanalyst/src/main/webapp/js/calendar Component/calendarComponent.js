//var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
var maxDate = (new Date().getMonth() + 1) + "/" + new Date().getDate() + "/" + new Date().getFullYear();
var textBoxDivId;
function showDateCal(divId, textBoxId,pageDate) {
	
	textBoxDivId = textBoxId;
	var id = document.getElementById(divId);
	if (dateCalendar)
		dateCalendar.destroy();

	var navConfig = {
		strings : {
			month : "Choose Month",
			year : "Enter Year",
			submit : "OK",
			cancel : "Cancel",
			invalidYear : "Please enter a valid year"
		},
		monthFormat : YAHOO.widget.Calendar.SHORT,
		initialFocus : "year"
	};

	var dateCalendar = new YAHOO.widget.Calendar(id, {
		navigator : navConfig,
		pagedate: pageDate,
		maxdate: maxDate,
		title : "Choose a date:",
		close : true
	});
	dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true);
	dateCalendar.render();
	dateCalendar.show();
}
function displayDateText(type, args, obj) {
	var dates = args[0];
	var date = dates[0];
	var year = date[0], month = date[1], day = date[2];
	var divId = obj.containerId;
	var divElmt = document.getElementById(divId);

	var txtDate1 = document.getElementById(textBoxDivId);
	txtDate1.value = day + "/" + month + "/" + year;
	minDate = month + "/" + day + "/" + year;
	divElmt.style.display = 'none';
}