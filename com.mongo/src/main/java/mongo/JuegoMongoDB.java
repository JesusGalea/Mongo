package mongo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Sorts.ascending;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.*;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class JuegoMongoDB {
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	static String date1s = "2017-03-03";

	static String date2s = "1998-11-21";

	public static void main(String[] args) throws ParseException {

		//IP de la DB y puerto
		
		MongoClient mc = new MongoClient("localhost", 27017);

		//Nombre de la DB
		
		MongoDatabase db = mc.getDatabase("Jesus");
		
		//Nombre de la colección

		MongoCollection col = db.getCollection("Juegos");
		
		//INSERT

//		Document juego1 = new Document().append("_id", new ObjectId()).append("titulo", "BOTW")
//				.append("genero", "mundo abierto").append("precio", 60).append("fecha_lanzamiento", formatter.parse(date1s));
//
//		Document juego2 = new Document().append("_id", new ObjectId()).append("titulo", "Ocarina of time")
//				.append("genero", "Aventura").append("precio", 40).append("fecha_lanzamiento", formatter.parse(date2s));
//
//		Document juego3 = new Document().append("_id", new ObjectId()).append("titulo", "Tears of the kingdom")
//				.append("genero", "mundo abierto").append("precio", 60);
//
//		List<Document> juegos = Arrays.asList(juego1, juego2, juego3);
//
//		db.getCollection("Juegos").insertMany(juegos);
//
//		
		
		// FIND
		
//		Bson projection = fields(exclude("_id"), include("titulo"));
		
// FindIterable<Document> result = db.getCollection("Juegos").find(eq("genero", "mundo abierto")).projection(projection).sort(ascending("titulo"));
		
//		
//		
//		Iterator it = result.iterator();
//		
//		while(it.hasNext()) {
//		
//		Document findDoc = (Document) it.next();
//		System.out.println(findDoc.toJson());
//		}
		
		//DISTINCT
	
//		
//		DistinctIterable<String> resultGeneros = db.getCollection("Juegos").distinct("genero", String.class);
//		
//		Iterator itG = resultGeneros.iterator();
//		
//		while (itG.hasNext()) {
//			
//			System.out.println(itG.next());
//		}
		
		//UPDATE
		
//      db.getCollection("Juegos").updateOne(new Document("titulo", "BOTW"), new
//      Document("$set", new Document("titulo", "Breath of the wild")));
		
		//DELETE

//		db.getCollection("Juegos").deleteMany(new Document("genero", "mundo abierto"));
	
		db.
	}

}
