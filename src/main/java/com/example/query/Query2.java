package com.example.query;

import com.example.RequestParams;

public class Query2 implements IQuery {

	@Override
	public String generateQuery(RequestParams params) {
		String queryString = "PREFIX : <http://localhost:8080/SW_project/cottagebooking#>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "\n"
				+ "SELECT ?cottage ?address ?city ?distanceFromLake ?distanceFromCity ?maxPeople ?bedrooms ?bookingStart ?bookingEnd\n"
				+ "WHERE {\n"
				+ "  # Filter cottages based on Booker requirements\n"
				+ "  ?cottage rdf:type :Cottage ;\n"
				+ "           :hasAddress ?address ;\n"
				+ "           :hasDistanceFromLake ?distanceFromLake ;\n"
				+ "           :hasMaxNumberOfPeople ?maxPeople ;\n"
				+ "           :hasNumberOfBedrooms ?bedrooms ;\n"
				+ "           :isLocatedAt ?location .\n"
				+ "}";
		
		return queryString;
	}

}
