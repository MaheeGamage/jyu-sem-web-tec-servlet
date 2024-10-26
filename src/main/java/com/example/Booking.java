package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Booking
 */
@WebServlet("/Booking")
public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		SWDB mediator = new SWDB();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write(mediator.getResult());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		SWDB mediator = new SWDB();

//		if (request.getParameter("reqType").toString().equals("doQuery")) {
//			String param01 = request.getParameter("param01").toString();
//			String param02 = request.getParameter("param02").toString();
//			String param03 = request.getParameter("param03").toString();
//			String pathToDB = this.getServletContext().getRealPath("/res/BookingDB.ttl");
//			mediator.searchForResult(pathToDB, param01, param02, param03);
//		}

		Map<String, Map<String, String>> entityProperties = new HashMap<>();

		// Cottage entity with its properties
		Map<String, String> cottageProperties = new HashMap<>();
		cottageProperties.put("rdf:type", "Cottage");
		cottageProperties.put("hasMaxNumberOfPeople", "maxPeople");
		cottageProperties.put("hasNumberOfBedrooms", "bedrooms");
		cottageProperties.put("hasDistanceFromLake", "distanceFromLake");
		cottageProperties.put("hasAddress", "address");
		cottageProperties.put("isLocatedAt", "location");

		// Location entity with its properties
		Map<String, String> locationProperties = new HashMap<>();
		locationProperties.put("hasNearestCityName", "nearestCity");
		locationProperties.put("hasDistanceFromCity", "distanceFromCity");

		// Add entities to the main map
		entityProperties.put("cottage", cottageProperties);
		entityProperties.put("location", locationProperties);

		String pathToDB = this.getServletContext().getRealPath("/res/Cottages.ttl");
//		mediator.searchForResult(pathToDB, entityProperties);
		mediator.search2(pathToDB);

		PrintWriter out = response.getWriter();
		out.write(mediator.getResult());
		out.flush();
		out.close();
	}

}
