<%@page
	import="de.uhd.ifi.se.quizapp.model.labelimageexercise.ImageLabel"%>
<%@page import="java.util.ArrayList"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise"%>
<jsp:useBean id="exercise"
	class="de.uhd.ifi.se.quizapp.model.labelimageexercise.LabelImageExercise"
	scope="request">
</jsp:useBean>

<div class="container">
	<div class="row">
		<div class="col" id='description'><%=exercise.getDescription()%></div>
	</div>
	<div class="row">
		<div class="col">
			<button class="btn btn-primary" onclick="goBack()">Zurück</button>
		</div>
	</div>
	<form id="exerciseform" action=Student method="post">
		<%
			ArrayList<ImageLabel> imageLabels = exercise.getLabels();
			String labelsinput = "";
			String imageSource = exercise.getImageSrc();
			int type = 3;
			int id = exercise.getExerciseId();

			int i = 0;
			for (ImageLabel label : imageLabels) {
				labelsinput += "<input name='solveinput" + i + "' class='labels' type='text' style="
						+ label.getPosition() + "/>";
				i++;
			}
		%>
		<div id="Beschriftungsbild" class="container labelImageDropZone"
			data=<%=imageSource%> style='background-image:url(<%=imageSource%>)'><%=labelsinput%></div>
</div>
<input type='hidden' name='id' value='<%=exercise.getExerciseId()%>' />
<input type='hidden' name='type' value='3' />
<input class="btn btn-primary" type='submit' name='checkResult'
	value='Senden' />
</form>
</div>
