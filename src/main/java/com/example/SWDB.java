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

import com.example.query.Query1;

public class SWDB {

	private String queryResult;

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