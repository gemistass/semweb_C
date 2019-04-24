package misc;
import java.io.File;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;


public class Main {

	public static void main(String[] args) {
		final String SERVER_URL = "http://localhost:7200";
		final String SENSOR_REPOSITORY = "SensorRep";
		final File queryf_1 = new File("queries/query_1.sparql");
		final File queryf_2 = new File("queries/query_2.sparql");
		final File queryf_3 = new File("queries/query_3.sparql");
		
		final File resultf_1 = new File("results/query_1.result");
		final File resultf_2 = new File("results/query_2.result");
		final File resultf_3 = new File("results/query_3.result");

		RepositoryManager repositoryManager = new RemoteRepositoryManager(SERVER_URL);
		repositoryManager.init();

		Repository repository = repositoryManager.getRepository(SENSOR_REPOSITORY);
		RepositoryConnection connection = repository.getConnection();
		connection.begin();
		connection.commit();
		
		QueriesResultsFunctions qrf = new QueriesResultsFunctions();
		
		
		//Execute query 1
		String query = qrf.getQuery(queryf_1);
		if(!query.isEmpty())
			qrf.getResults(query,resultf_1,connection);
		//Execute query 2		
		query = qrf.getQuery(queryf_2);
		if(!query.isEmpty())
			qrf.getResults(query,resultf_2,connection);
		//Execute query 3
		query = qrf.getQuery(queryf_3);
		if(!query.isEmpty())
			qrf.getResults(query,resultf_3,connection);
		
		
		
		connection.close();
		repositoryManager.shutDown();

	}

}
