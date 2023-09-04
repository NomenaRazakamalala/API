package dao;

import com.mongodb.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DbConnect {
	public static Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver");
		String server = "localhost", port = "5432", database = "operateur";
		String user = "telma", password = "123456";
        String connUrl = "jdbc:postgresql://" + server + ":" + port + "/" + database;
        Connection conn = DriverManager.getConnection(connUrl, user, password);
        conn.setAutoCommit(false);
        return conn;
	}
	public static String getSequenceTable(String table) {
		return "nextval('sequence_" + GDaoUtil.lowerFirstCase(table) + "')";
	}
        public static  MongoDatabase getDatabaseMongo(){
            //MongoClient mongoClient = new MongoClient("mongodb+srv://rakotosonfabien:F0RluTvBkrhrPOSW@operateurwebfinals5.mxrkl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
            //DB database = mongoClient.getDB("operateur_telma");
            
            MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://rakotosonfabien:motdepasse123456@operateurwebfinals5.mxrkl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
            MongoClient mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase("operateur_telma");
            //DB database = mongoClient.getDB("operateur_telma");
            return database;
        }
}
//sequence_admins
//Client
