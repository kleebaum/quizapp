$(document).ready(function() {
	$(".mobilemenu").click(function() {
		$(this).toggleClass('toggled');
		$(".collapse").toggle();
	});
});

$(document).ready(function() {
	$(".mobilesidemenu").click(function() {
		$("#sideMenu ul").toggle();
	});
});