<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@page import="java.nio.file.Paths"%>

<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<jsp:useBean id="dataManager" scope="request"
	class="de.uhd.ifi.se.quizapp.model.DataManager" />


<div class="container">
	<h1>Beschriftungs-Aufgabe erstellen</h1>

	<script>
		inittinymce();
	</script>


	<h2>W&auml;hlen Sie ein Bild aus</h2>
	<div class="container imagePicker">
		<%
			File file = new File(Paths.get(new File("").getAbsolutePath(), "WebContent", "images").toString());
			String fileNames[] = file.list();
			out.print("<ul>");
			for (String s : fileNames)
				out.print("<li><img class='labelImagePreview' src=/images/" + s + "></li>");

			out.print("</ul>");
		%>
	</div>

	<form id="createExerciseForm" action="Administrator" method="post"
		accept-charset="UTF-8">
		<div id="formcontainer"></div>
		<textarea name='description' placeholder='Beschreibung' required>Beschrifte das Bild</textarea>
		<div class="row">
			<p>
				<label for="difficulty">Schwierigkeit ausw&auml;hlen</label><br>
				<span data-range-min="1" data-range-max="3"> <input
					onchange="updateSlider(this);" type="range" min="1" max="3"
					name="difficulty" value="1"></span><span id="sliderValue"></span><br>
			</p>
		</div>
		<div class="row">
			<div class="col">
				<div class="form-group">
					<label for="name">zugeordnete Information ausw&auml;hlen:</label><br>
					<select class="form-control" name="information" size="1" required>
						<%
							ArrayList<Information> informationList = dataManager.getInformation();
							for (Information eachInformation : informationList) {
						%>
						<option value="<%=eachInformation.getInformationId()%>">
							<%
								out.print(eachInformation.getInformationId() + " " + eachInformation.getName());
							%>
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<input type=hidden id="labelData" name="labelData" value="" />
			<div class="col-2 align-self-end">
				<input type='hidden' name='type' value='<%=ExerciseHandler.LABEL%>' />
				<input class="btn btn-primary" type="submit" name="createExercise"
					value="Speichern" /> <br>
			</div>
		</div>
	</form>
</div>
<h2>Klicken, um eine Beschriftung zu erstellen</h2>
<div id="Beschriftungsbild" class="container labelImageDropZone"></div>




<script>
	var idcounter = 0;
	$('.labelImageDropZone')
			.click(
					function(e) {
						if (e.target == this) {
							var elm = $(this);
							var xPos = e.pageX - elm.offset().left;
							var yPos = e.pageY - elm.offset().top;
							object = $(this)
									.append(
											"<input class='labels' id='"+idcounter+"' style='top:"+
											yPos+"px;left:"+xPos+"px;'  class='form-control' type='text' name='inputLabel[]' placeholder='Beschriftung' required />"
													+ "<p class='deleteLabel' style='top:"+
											yPos+"px;left:"+
											xPos+"px;'>X</p>");

							idcounter = idcounter + 1;
						} else if ($(e.target).is(".deleteLabel")) {
							$('.deleteLabel').click(function() {
								$(this).prev().remove();
								$(this).remove();
							});
							$(".deleteLabel").draggable();
						}
					});

	$('.imagePicker li img').each(function() {
		$(this).click(function() {
			window.location.href = "#Beschriftungsbild";
			var imageName = $(this).attr("src");
			$('.labelImageDropZone').css({
				"background-image" : "url('" + imageName + "')"
			});
			$(".labelImageDropZone").attr("data", imageName);
		});
	});

	$("form").submit(function() {
		if (createJSON() != false)
			$("#labelData").attr("value", JSON.stringify(createJSON()))
		else {
			alert("Es muss mindestens eine Beschriftung vorhanden sein.");
			return false;
		}
	});

	function createJSON() {

		if ($(".labels").length != 0) {
			jsonObj = [];
			item = {}
			item["image"] = $(".labelImageDropZone").attr("data");
			jsonObj.push(item);
			$(".labels").each(function() {

				var position = $(this).attr("style");
				var label = $(this).val();

				item = {}
				item["position"] = position;
				item["label"] = label;

				jsonObj.push(item);
			});
			return jsonObj;
		}
		return false;
	}

	$(".draggable").draggable({
		start : function(event, ui) {
			$(this).data('preventBehaviour', true);
		}
	});
	$(".draggable :input").on(
			'mousedown',
			function(e) {
				var mdown = document.createEvent("MouseEvents");
				mdown.initMouseEvent("mousedown", false, true, window, 0,
						e.screenX, e.screenY, e.clientX, e.clientY, true,
						false, false, true, 0, null);
				$(this).closest('.draggable')[0].dispatchEvent(mdown);
			}).on('click', function(e) {
		var $draggable = $(this).closest('.draggable');
		if ($draggable.data("preventBehaviour")) {
			e.preventDefault();
			$draggable.data("preventBehaviour", false)
		}
	});
</script>