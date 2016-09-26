$(document).ready(function() {

	$("#subscribeForm").submit(function() {
		$.ajax({
			type: "POST",
			url: "mail.php",
			data: $(this).serialize()
		}).done(function() {
			$(this).find("input").val("");
			alert("Успешно! Мы вас ждем!");
			$("#subscribeForm").trigger("reset");
		});
		return false;
	});

});
