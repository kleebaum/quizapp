<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="de.uhd.ifi.se.quizapp.model.Information"%>
<%@page import="de.uhd.ifi.se.quizapp.controller.ExerciseHandler"%>
<%@page import="de.uhd.ifi.se.quizapp.model.labelimageexercise.*"%>
<jsp:useBean id="dataManager" scope="request"
	class="de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageDataManager" />

<div class="container">
	<h1>Aufgaben bearbeiten</h1>

	<%
		int id = Integer.parseInt(request.getParameter("id"));
		LabelImageExercise exercise = dataManager.getExercise(id);
		ArrayList<ImageLabel> labels = exercise.getLabels();
		String labelsinput = "";
		String imageSource = exercise.getImageSrc();

		for (ImageLabel label : labels) {
			labelsinput += "<input class='labels' type='text' value=" + label.getLabel() + " style="
					+ label.getPosition() + "/>";
			labelsinput += "<p class='deleteLabel' style=" + label.getPosition() + ">X</p>";
		}

		String form = "";

		form += "<input type=hidden id='labelData' name='labelData' value='' />";
		form += "<input type='hidden' name='type' value='" + ExerciseHandler.LABEL + "' />";
		form += "<input type='hidden' name='id' value='" + id + "' />";
	%>

	<form id="updateExerciseForm" action="Administrator" method="post"
		accept-charset="UTF-8">

		<div class="row">
			<textarea name='description' rows='2' style='width: 98%;'
				placeholder='Beschreibung' required><%=exercise.getDescription()%></textarea>
		</div>
		<div id="Beschriftungsbild" class="container labelImageDropZone"
			data=<%=imageSource%> style='background-image:url(<%=imageSource%>)'><%=labelsinput%></div>
		<div id="formcontainer"><%=form%></div>
		<div class="row">
			<label for="difficulty">Schwierigkeit auswählen</label> <span
				data-range-min="1" data-range-max="3"> <input
				onchange="updateSlider(this);" type="range" min="1" max="3"
				name="difficulty" value="<%=exercise.getDifficulty()%>">
			</span> <span id="sliderValue"></span> <br>
		</div>
		<br>
		<div class="row">
			<label for="name">Text auswählen:</label>
		</div>
		<br>
		<div class="row">
			<div class="col">
				<select class="form-control" name="information" size="1">
					<%
						ArrayList<Information> informationList = dataManager.getInformation();
						for (Information eachInformation : informationList) {
					%><option>
						<%
							out.print(eachInformation.getInformationId() + " " + eachInformation.getName());
						%>
					</option>
					<%
						}
					%>
				</select>
			</div>
			<div class="col-2">
				<input class="btn btn-primary" type="submit" name="updateExercise"
					value="Speichern" />
			</div>
			<br>
		</div>
	</form>
</div>

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
</script>