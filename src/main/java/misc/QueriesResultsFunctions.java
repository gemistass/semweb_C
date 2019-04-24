package misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.Binding;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.config.RepositoryConfig;
import org.eclipse.rdf4j.repository.config.RepositoryImplConfig;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.repository.sail.config.SailRepositoryConfig;
import org.eclipse.rdf4j.sail.config.SailImplConfig;
import org.eclipse.rdf4j.sail.memory.config.MemoryStoreConfig;
import org.eclipse.rdf4j.sail.spin.config.SpinSailConfig;
import org.eclipse.rdf4j.spin.*;
import org.jline.utils.Log;

public class QueriesResultsFunctions {

	public String getQuery(File query) {
		FileReader filewriter;
		Log.info(query);
		try {
			filewriter = new FileReader(query);

			int c;
			StringBuilder sb = new StringBuilder();

			while ((c = filewriter.read()) != -1)
				sb.append((char) c);

			System.out.println(sb);
			filewriter.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getResults(String query, File resultf, RepositoryConnection connection) {

		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult tupleQueryResult = tupleQuery.evaluate();

		try {
			FileWriter filewriter = new FileWriter(resultf);
			while (tupleQueryResult.hasNext()) {
				BindingSet bindingSet = tupleQueryResult.next();

				for (Binding binding : bindingSet) {
					
					String name = binding.getName();
					Value value = binding.getValue();
					System.out.println(name + " = " + value);
					filewriter.write(name + " = " + value + "\n");
				}
			}
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
