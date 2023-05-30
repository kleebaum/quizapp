<%@page import="java.util.List"%>
<%@page
	import="de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement"%>

<jsp:useBean id="exercise"
	class="de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise"
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
	<div class="row">
		<form id="exerciseform" action=Student method="post">
			<div class='group'>
				<%
					List<BooleanStatement> booleanStatements = exercise.getBooleanStatements();
					int type = 1;
					int id = exercise.getExerciseId();

					String form = "";
					int i = 0;
					for (BooleanStatement statement : booleanStatements) {
						int radioId = i * 2;
						int radioId2 = radioId + 1;
						form += "<input class='form-control' type='text' name='input" + i + "' value='"
								+ statement.getStatement() + "' readonly  />";
						form += "<fieldset><label for='" + radioId + "'>Richtig</label><input id='" + radioId + "' name='radio"
								+ i + "' type='radio' value='true' required />";
						form += "<label for='" + radioId2 + "'>Falsch</legend><input id='" + radioId2 + "' name='radio" + i
								+ "' type='radio' value='false'  /></fieldset>";
						i++;
					}
					out.print(form);
				%>
			</div>
			<input type='hidden' name='id' value='<%=exercise.getExerciseId()%>' />
			<input type='hidden' name='type' value='1' /> <input
				class="btn btn-primary" type='submit' name='checkResult'
				value='Senden' />
		</form>
	</div>
</div>