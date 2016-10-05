$(document).ready(function() {

	$("#form").submit(function() {
		var number = $("#subscribeForm1").find("#number").val()
		var time = $("#subscribeForm1").find("#time").val()
		$.get(
			"mail.php",
			{
				number:number,
				time:time
			}
		).done(function() {
			$(this).find("input").val("");
			alert("Спасибо за заявку! Скоро мы с вами свяжемся.");
			$("#form").trigger("reset");
		});
		return false;
	});

});
