<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="java.nio.file.Paths"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<form id="uploadForm" method="post" action="Administrator"
		enctype="multipart/form-data">
		W&auml;hle ein Bild zum Hochladen aus: <input class="btn btn-primary"
			type="file" name="dataFile" id="fileChooser" /> <input
			class="btn btn-primary" type="submit" value="Upload" />
	</form>

	<c:if test="${not empty filePath}">
		Link zum Bild:<input onclick="this.select();" type="text"
			value="${filePath}" title="kopieren und im Editor einfügen">
		<a id="uploadHelp" href="#" title="Hilfe">?</a>
	</c:if>

	<div
		style="display: none; background: rgba(0, 0, 0, 0.5); width: 100%;"
		id="dialog" title="Hilfe">
		<img style="z-index: 3;" src="../img/helpUpload.png">
	</div>
</div>
<button id="deleteFilesButton" class="btn btn-primary">Bilder
	löschen</button>


<form id="deleteFilesForm" style="display: none;" action="Administrator"
	method="POST">
	<%
		File file = new File(Paths.get(new File("").getAbsolutePath(), "WebContent", "images").toString());
		String fileNames[] = file.list();
		out.print("<ul>");
		for (String s : fileNames)
			out.print("<li><input style='display:none;'  name='deleteFiles' type='checkbox' value='" + file
					+ File.separator + s + "' /><img class='labelImagePreview' src='/images/" + s + "'></li>");

		out.print("</ul>");
	%>
	<input type="submit" name="deleteFilesFromServer"
		value="Ausgewählte Bilder löschen">
</form>

<script>
	$("#uploadHelp").click(function() {
		$(function() {
			$("#dialog").dialog({
				minWidth : 500
			});
		});
	});

	$("#deleteFilesButton").click(function() {
		$("#deleteFilesForm").toggle();
		$(".imagePicker").toggle();
	});

	$(".labelImagePreview").click(function() {
		$(this).toggleClass("labelImagePreviewSelected");
		if ($(this).hasClass("labelImagePreviewSelected")) {
			$(this).prev("input").prop("checked", true);
		} else {
			$(this).prev("input").prop("checked", false);
		}
	});

	$("#uploadForm").submit(function() {
		if ($("#fileChooser").val() == "") {
			alert("Wählen Sie ein Bild aus.");
			return false;
		}

	});
</script>

