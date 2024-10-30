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

import com.example.query.Query1;

public class SWDB {

	BookingSuggestionResponse bookingSuggestion;

	public void searchForResult(String pathDB, RequestParams params) {
		System.out.println("Do query...");

		Model model = RDFDataMgr.loadModel(pathDB);
		OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
		OntModel ontModel = ModelFactory.createOntologyModel(ontModelSpec, model);

		String queryString = new Query1().generateQuery(params);
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

			bookingSuggestion = new BookingSuggestionResponse(rowData);
			bookingList.add(bookingSuggestion);
		}

		//Gson gson = new Gson();
		System.out.println("BookingSuggestion: ---\n" + this.bookingSuggestion);
	}

	public BookingSuggestionResponse getResult() {
		return bookingSuggestion;
	}
}