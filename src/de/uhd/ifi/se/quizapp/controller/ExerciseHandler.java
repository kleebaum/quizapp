package de.uhd.ifi.se.quizapp.controller;

import javax.servlet.http.HttpServletRequest;

import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Exercise;

public abstract class ExerciseHandler {

	private DataManager dataManager;

	public static final int TWOCHOICE = 1;
	public static final int SENTENCEPART = 2;
	public static final int LABEL = 3;

	protected int type;

	// next element in chain of responsibility
	protected ExerciseHandler successor;

	public ExerciseHandler getSuccessor() {
		return this.successor;
	}

	public void setSuccessor(ExerciseHandler successor) {
		this.successor = successor;
	}

	/**
	 * Trys to parse the selected information
	 */
	public boolean tryParse(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Removes the HTML Tags which are save on the Database
	 * 
	 * @param exercise
	 */
	protected void removingHTMLTags(Exercise exercise) {
		String label = exercise.getDescription();
		label = label.substring(3, label.length() - 4);
		exercise.setDescription(label);
	}

	public HttpServletRequest handleCreation(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleCreationInChain(request);
		}
		if (this.successor != null) {
			return this.successor.handleCreation(request);
		}
		return null;
	}

	abstract protected HttpServletRequest handleCreationInChain(HttpServletRequest request);

	public HttpServletRequest handleDeletion(HttpServletRequest request) {
		this.dataManager = new DataManager();
		int id = 1;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		dataManager.deleteExercise(id);

		request.setAttribute("message", "Die Aufgabe wurde erfolgreich geloescht.");
		return request;
	}

	/**
	 * TODO: Compare performance if this method is used instead of only using the
	 * jsp page
	 */
	public HttpServletRequest handleEditing(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleEditingInChain(request);
		}
		if (this.successor != null) {
			return this.successor.handleEditingInChain(request);
		}
		return null;
	}

	abstract protected HttpServletRequest handleEditingInChain(HttpServletRequest request);

	public HttpServletRequest handleUpdating(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleUpdatingInChain(request);
		}
		if (this.successor != null) {
			return this.successor.handleUpdating(request);
		}
		return null;
	}

	abstract protected HttpServletRequest handleUpdatingInChain(HttpServletRequest request);

	public HttpServletRequest handleFiltering(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleFilteringInChain(request);
		}
		if (this.successor != null) {
			if (this.successor.type == type) {
				return this.successor.handleFilteringInChain(request);
			}
			if (this.successor.successor != null) {
				if (this.successor.successor.type == type) {
					return this.successor.successor.handleFilteringInChain(request);
				}
			}
		}
		return null;
	}

	abstract protected HttpServletRequest handleFilteringInChain(HttpServletRequest request);

	public HttpServletRequest handleSolving(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleSolvingInChain(request);
		}
		if (this.successor != null) {
			if (this.successor.type == type) {
				return this.successor.handleSolvingInChain(request);
			}
			if (this.successor.successor != null) {
				if (this.successor.successor.type == type) {
					return this.successor.successor.handleSolving(request);
				}
			}
		}
		return null;
	}

	abstract protected HttpServletRequest handleSolvingInChain(HttpServletRequest request);

	public HttpServletRequest handleChecking(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		if (this.type == type) {
			return handleCheckingInChain(request);
		}
		if (this.successor != null) {
			if (this.successor.type == type) {
				return this.successor.handleCheckingInChain(request);
			}
			if (this.successor.successor != null) {
				if (this.successor.successor.type == type) {
					return this.successor.successor.handleCheckingInChain(request);
				}
			}
		}
		return null;
	}

	abstract protected HttpServletRequest handleCheckingInChain(HttpServletRequest request);

	public String calculateExerciseMetrics(int type) {
		if (this.type == type) {
			return calculateExerciseMetricsInChain(type);
		}
		if (this.successor != null) {
			if (this.successor.type == type) {
				return this.successor.calculateExerciseMetricsInChain(type);
			}
			if (this.successor.successor != null) {
				if (this.successor.successor.type == type) {
					return this.successor.successor.calculateExerciseMetricsInChain(type);
				}
			}
		}
		return null;
	}

	abstract protected String calculateExerciseMetricsInChain(int type);

}
