package com.itgrids.voterdata.service.mongodb;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class KafkaErrorSync {

	public static void main(String[] args)
	{
		syncErrorRecords();
	}
	
	@SuppressWarnings("resource")
	public static void syncErrorRecords()
	{
		try{
			 MongoClient mongoClient = new MongoClient(IMongoConstants.MONGO_URL , 27017);
			 DB db = mongoClient.getDB( "kafkadb" );
			 //DBCollection data = db.getCollection("consumerErrorLog");
			 BasicDBObject query = new BasicDBObject();
			 query.put("imei","911447200547421");
			 db.getCollection("consumerErrorLog").find(query);
			 
			 //DBCursor cursor = data.find();
			 DBCursor cursor = db.getCollection("consumerErrorLog").find(query);
	         int i = 1;
				
	         while(cursor.hasNext()) { 
	            DBObject dbObject = cursor.next();
	            JSONObject jsonObj =  new JSONObject(JSON.serialize(dbObject));
	            JSONObject request = jsonObj.getJSONObject("requestString");
	            System.out.println(request.getString("uniqueID"));
	            i++;
	         }
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
