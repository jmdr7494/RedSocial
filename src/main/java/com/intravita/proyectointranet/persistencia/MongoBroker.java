package com.intravita.proyectointranet.persistencia;

import org.bson.BsonDocument;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//MongoClientURI uri  = new MongoClientURI("mongodb://user:pass@host:port/db"); 
//MongoClient client = new MongoClient(uri);
//MongoDatabase db = client.getDatabase(uri.getDatabase());

/**
 * MongoBroker- Broker para la conexion con la BBDD en MLab
 *
 * @author Intravita
 * @since sprint 1
 */

public class MongoBroker {
	private static MongoBroker yo;
	private MongoClientURI uri;
	private MongoClient mongoClient;
	private MongoDatabase db;

	private MongoBroker(){//En el constructor MongoBroker ha sido modificado con los parametros de mongoLab
		this.uri= new MongoClientURI("mongodb://daniel:1234@ds113435.mlab.com:13435/redsocial");
		this.mongoClient= new MongoClient(uri);
		this.db=mongoClient.getDatabase(uri.getDatabase());
	}


	public static MongoBroker get(){
		if (yo==null){
			yo = new MongoBroker();
		}
		return yo;
	}

	public MongoCollection<BsonDocument> getCollection (String collection){
		MongoCollection <BsonDocument> result=db.getCollection(collection, BsonDocument.class);

		if(result==null){
			db.createCollection(collection);
			result=db.getCollection(collection,BsonDocument.class);
		}

		return result;
	}
}