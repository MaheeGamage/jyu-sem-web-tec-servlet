package com.example;

import com.example.model.BookingSuggestionResponse;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.query.Query3;

public class SWDB {

	public ArrayList<BookingSuggestionResponse> searchForResult(String pathDB, RequestParams params) {
		System.out.println("Do query...");

		Model model = RDFDataMgr.loadModel(pathDB);
		OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
		OntModel ontModel = ModelFactory.createOntologyModel(ontModelSpec, model);

		String queryString = new Query3().generateQuery(params);
		System.out.println("queryString: ---\n" + queryString);

		Dataset dataset = DatasetFactory.create(ontModel);
		Query q = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(q, dataset);
		ResultSet resultSet = qexec.execSelect();

		ArrayList<BookingSuggestionResponse> bookingList = new ArrayList<>();
		while (resultSet.hasNext()) {
			QuerySolution row = resultSet.next();
			Map<String, String> rowData = new HashMap<>();

			row.varNames().forEachRemaining(varName -> {
				RDFNode node = row.get(varName);
				rowData.put(varName, (node != null ? node.toString() : "null"));
			});

			bookingList.add(new BookingSuggestionResponse(rowData));
		}

		return bookingList;
	}

	public BookingSuggestionResponse getResult() {
		return new BookingSuggestionResponse("Alice Johnson", // name of the booker
				"BK-20241029-67890", // booking number
				"456 Mountain View Drive", // address of the cottage
				"http://example.com/mountain.jpg", // image of the cottage
				8, // actual number of places
				4, // actual number of bedrooms
				100, // distance to lake in meters
				"Evergreen City", // nearest city
				30, // distance to nearest city in km
				"2024-12-15", // booking start date
				"2024-12-25" // booking end date
		);
	}
}