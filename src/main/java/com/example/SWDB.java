package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.RDFDataMgr;

public class SWDB {

	private String queryResult;

	public void searchForResult(String pathDB, RequestParams params) {
		System.out.println("Do query...");

		Model model = RDFDataMgr.loadModel(pathDB);
		OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
		OntModel ontModel = ModelFactory.createOntologyModel(ontModelSpec, model);

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("PREFIX cb: <http://example.org/ex#>\n");
		queryBuilder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n");
		queryBuilder.append("SELECT ?cottage\n");
		queryBuilder.append("WHERE {\n");
		queryBuilder.append("  ?cottage rdf:type cb:Cottage .\n");

		if (params.getNoOfPeople() > 0) {
			queryBuilder.append("  ?cottage cb:hasCapacity ?capacity .\n");
			queryBuilder.append("  FILTER(?capacity >= " + params.getNoOfPeople() + ") \n");
		}
		if (params.getBedroomCount() > 0) {
			queryBuilder.append("  ?cottage cb:hasBedroomCount ?bedrooms .\n");
			queryBuilder.append("  FILTER(?bedrooms >= " + params.getBedroomCount() + ") \n");
		}
		if (params.getMaxLakeDistance() > 0) {
			queryBuilder.append("  ?cottage cb:distanceToLake ?lakeDistance .\n");
			queryBuilder.append("  FILTER(?lakeDistance <= " + params.getMaxLakeDistance() + ") \n");
		}
		if (params.getCity() != null && !params.getCity().isEmpty()) {
			queryBuilder.append("  ?cottage cb:locatedInCity ?city .\n");
			queryBuilder.append("  FILTER(?city = \"" + params.getCity() + "\") \n");
		}
		if (params.getMaxCityDistance() > 0) {
			queryBuilder.append("  ?cottage cb:distanceToCity ?cityDistance .\n");
			queryBuilder.append("  FILTER(?cityDistance <= " + params.getMaxCityDistance() + ") \n");
		}
		if (params.getDayCount() > 0) {
			queryBuilder.append("  ?cottage cb:availableForDays ?days .\n");
			queryBuilder.append("  FILTER(?days >= " + params.getDayCount() + ") \n");
		}
		if (params.getStartDate() != null && !params.getStartDate().isEmpty()) {
			queryBuilder.append("  ?cottage cb:availableFrom ?startDate .\n");
			queryBuilder.append("  FILTER(?startDate = \"" + params.getStartDate() + "\") \n");
		}
		if (params.getMaxDayShifts() > 0) {
			queryBuilder.append("  ?cottage cb:maxDayShift ?dayShift .\n");
			queryBuilder.append("  FILTER(?dayShift <= " + params.getMaxDayShifts() + ") \n");
		}

		queryBuilder.append("}");

		String queryString = queryBuilder.toString();
		System.out.println("queryString: ---\n" + queryString);

		Dataset dataset = DatasetFactory.create(ontModel);
		Query q = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(q, dataset);
		ResultSet resultSet = qexec.execSelect();

		JsonArray resultsArray = new JsonArray();
		while (resultSet.hasNext()) {
			QuerySolution row = resultSet.next();
			JsonObject jsonRow = new JsonObject();
			row.varNames().forEachRemaining(varName -> {
				RDFNode node = row.get(varName);
				jsonRow.addProperty(varName, (node != null ? node.toString() : "null"));
			});
			resultsArray.add(jsonRow);
		}

		Gson gson = new Gson();
		this.queryResult = gson.toJson(resultsArray);
		System.out.println("JSON Result: ---\n" + this.queryResult);
	}

	public String getResult() {
		return this.queryResult != null ? this.queryResult : "No result available.";
	}
}
