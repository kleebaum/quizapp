package de.uhd.ifi.se.quizapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import de.uhd.ifi.se.quizapp.model.Administrator;
import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Information;

@WebServlet("/Administrator")
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private transient DataManager dataManager;

	private TwoChoiceExerciseHandler exerciseHandler;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorServlet() {
		super();
		exerciseHandler = new TwoChoiceExerciseHandler(ExerciseHandler.TWOCHOICE);
		SentencePartExerciseHandler sentencePartExerciseHandler = new SentencePartExerciseHandler(
				ExerciseHandler.SENTENCEPART);
		LabelImageExerciseHandler labelImageExerciseHandler = new LabelImageExerciseHandler(ExerciseHandler.LABEL);

		exerciseHandler.setSuccessor(sentencePartExerciseHandler);
		sentencePartExerciseHandler.setSuccessor(labelImageExerciseHandler);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		// Connect to database
		dataManager = new DataManager();

		// @Descision UTF-8 Kodierung wegen den Umlauten.
		// @Alternative ISO-8859-1
		// @Problem Zeichen wurden nicht richtig kodiert
		// @Solution mit UTF-8 werden sie richtig kodiert

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;

		if (request.getParameter("createExercise") != null) {
			request = exerciseHandler.handleCreation(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createExercise");
		}

		else if (request.getParameter("createInformation") != null) {
			request = this.handleCreateInformationRequest(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createInformation");
		}

		else if (request.getParameter("deleteExercise") != null) {
			request = exerciseHandler.handleDeletion(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createExercise");
		}

		else if (request.getParameter("deleteInformation") != null) {
			request = this.handleDeleteInformationRequest(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createInformation");
		}

		else if (request.getParameter("editExercise") != null) {
			// request = exerciseHandler.handleEditing(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=updateExercise");
		}

		else if (request.getParameter("updateExercise") != null) {
			request = exerciseHandler.handleUpdating(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createExercise");
		}

		else if (request.getParameter("editInformation") != null) {
			request = this.handleEditInformationRequest(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=updateInformation");
		}

		else if (request.getParameter("updateInformation") != null) {
			request = this.handleUpdateInformationRequest(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createInformation");
		}

		else if (request.getParameter("resetDatabase") != null) {
			dataManager.resetDatabase();
			dataManager.fillDatabaseWithSampleData();
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=settings");
		}

		else if (request.getParameter("changeAdministratorPassword") != null) {
			request = this.handleChangingPassword(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=settings");
		}

		else if (request.getParameter("deleteUser") != null) {
			try {
				request = this.handleDeleteUser(request);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=manageUsers");
		}

		else if (request.getParameter("changeRole") != null) {
			request = this.handleChangingRole(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=manageUsers");
		}

		else if (ServletFileUpload.isMultipartContent(request)) {
			request = this.uploadFile(request);
			String redirection = (String) request.getAttribute("redirection");
			if (redirection.equals("settings")) {
				dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=settings");
			} else if (redirection.equals("createInformation")) {
				dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createInformation");
			}

		} else if (request.getParameter("login") != null) {
			HttpSession session = request.getSession();
			request = this.handleLogin(request, session);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp");
		} else if (request.getParameter("logout") != null) {
			HttpSession session = request.getSession();
			if (session.getAttribute("administrator") != null) {
				Administrator administrator = (Administrator) session.getAttribute("administrator");
				if (administrator.isValid()) {
					administrator.setValid(false);
					request.setAttribute("message", "Logout erfolgreich.");
					administrator = null;
					session.setAttribute("administrator", administrator);
				}
			}
			dispatcher = request.getRequestDispatcher("/admin/index.jsp");
		} else if (request.getParameter("deleteFilesFromServer") != null) {
			request = this.deleteFiles(request);
			dispatcher = request.getRequestDispatcher("/admin/index.jsp?p=createInformation");
		}

		// No request
		else {
			out.println("No request.");
		}

		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public HttpServletRequest handleCreateInformationRequest(HttpServletRequest request) {
		String text = request.getParameter("text");
		String name = request.getParameter("name");

		Information info = new Information(name, text);

		dataManager.insertInformation(info);

		String message = "Die Information wurde erfolgreich erstellt.";
		request.setAttribute("message", message);
		return request;
	}

	public HttpServletRequest handleDeleteInformationRequest(HttpServletRequest request) {
		int id = 0;
		if (request.getParameter("id") != null)
			id = Integer.parseInt(request.getParameter("id"));

		try {
			dataManager.deleteInformation(id);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}

		String message = "Die Information erfolgreich gel&ouml;scht.";
		request.setAttribute("message", message);
		return request;
	}

	public HttpServletRequest handleEditInformationRequest(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Information information = new Information();
		try {
			information = dataManager.getInformation(id);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		request.setAttribute("information", information);
		return request;
	}

	public HttpServletRequest handleUpdateInformationRequest(HttpServletRequest request) {
		String text = request.getParameter("text");
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));

		Information info = new Information(id, name, text);

		// Update information in database
		try {
			dataManager.updateInformation(info);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}

		String message = "Die Information wurde erfolgreich aktualisiert.";
		request.setAttribute("message", message);
		return request;
	}

	public HttpServletRequest uploadFile(HttpServletRequest request) throws ServletException {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String message = "Der Upload war nicht erfolgreich.";
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iterator = items.iterator();
			while (iterator.hasNext()) {
				FileItem item = (FileItem) iterator.next();

				if (!item.isFormField()) {
					String fileName = item.getName();

					String contentType = item.getContentType();
					ArrayList<String> allowedFileTypes = new ArrayList<String>(
							Arrays.asList("image/jpeg", "image/png"));
					File path = null;
					String redirection = "createInformation";
					if (contentType.equals("application/octet-stream")) {
						path = new File(Paths.get(new File("").getAbsolutePath(), "WebContent", "db").toString());
						redirection = "settings";
					} else if (allowedFileTypes.contains(contentType)) {
						path = new File(Paths.get(new File("").getAbsolutePath(), "WebContent", "images").toString());
					} else {
						message = "Dateiformat ist nicht erlaubt.";
						request.setAttribute("redirection", redirection);
						request.setAttribute("message", message);
						return request;
					}
					File uploadedFile = new File(path + "/" + fileName);
					item.write(uploadedFile);
					request.setAttribute("filePath", "/images/" + uploadedFile.getName());
					request.setAttribute("redirection", redirection);
					message = "Upload war erfolgreich.";
					request.setAttribute("message", message);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	public HttpServletRequest handleLogin(HttpServletRequest request, HttpSession session) {

		String message = null;

		if (request.getParameter("username") == null || request.getParameter("password") == null) {
			message = "Login war nicht erfolgreich";
			request.setAttribute("message", message);
			return request;
		}

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			byte[] digest = messageDigest.digest();
			password = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}

		Administrator administrator = null;
		message = "Login war nicht erfolgreich";
		try {
			administrator = this.dataManager.getAdministrator(username);
			if (password.equals(administrator.getPasswordHash())) {
				administrator.setValid(true);
				session.setAttribute("administrator", administrator);
				message = "Login erfolgreich";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("message", message);
		return request;
	}

	public HttpServletRequest deleteFiles(HttpServletRequest request) {
		String[] files;
		if (request.getParameterValues("deleteFiles") != null) {
			files = request.getParameterValues("deleteFiles");
			System.out.println(files);
			for (int i = 0; i < files.length; i++) {

				String path = Paths.get(files[i]).toString();
				System.out.println(path);

				File file = new File(path);

				if (file.delete()) {
					System.out.println(file.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
			}
		}
		return request;
	}

	public HttpServletRequest handleChangingPassword(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Administrator administrator = (Administrator) session.getAttribute("administrator");
		String newpassword = "";

		if (request.getParameter("password") != null) {
			newpassword = request.getParameter("password");
			try {
				try {
					administrator.hashPassword(newpassword);
					dataManager.changePassword(administrator.getUsername(), administrator.getPasswordHash());
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return request;
	}

	public HttpServletRequest handleDeleteUser(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		String username = request.getParameter("username");

		dataManager.deleteUser(username);

		return request;
	}

	public HttpServletRequest handleChangingRole(HttpServletRequest request) {
		String username = request.getParameter("username");
		String newRole = request.getParameter("role");

		try {
			dataManager.changeRole(username, newRole);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
}