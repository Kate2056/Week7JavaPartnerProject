package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListEvent;

/**
 * Servlet implementation class AddEventServlet
 */
@WebServlet("/addEventServlet")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddEventServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String artist = request.getParameter("artist");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String hour = request.getParameter("hour");
		String minutes = request.getParameter("minutes");
		
		LocalDate ld;
		try{
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		}
		catch(NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		LocalTime lt;
		try{
			lt = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minutes), 0);
		}
		catch(NumberFormatException ex) {
			lt = LocalTime.now();
		}
		
		ListEvent le = new ListEvent(artist, ld, lt);
		ListEventHelper dao = new ListEventHelper();
		dao.insertEvent(le);
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}

}
