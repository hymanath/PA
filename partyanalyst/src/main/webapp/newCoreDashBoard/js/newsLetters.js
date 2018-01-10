	var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

	$(".chosen-select").chosen();
	var currentFromDate = moment().format("DD-MM-YYYY");
	var currentToDate = moment().format("DD-MM-YYYY");
	$("#dateRangeFromDateNewsId").daterangepicker({
		singleDatePicker: true,
		startDate: currentFromDate,
		locale: {
		  format: 'DD-MM-YYYY'
		},
	});
	$("#dateRangeToDateNewsId").daterangepicker({
		singleDatePicker: true,
		endDate: currentToDate,
		locale: {
		  format: 'DD-MM-YYYY'
		},
	});

