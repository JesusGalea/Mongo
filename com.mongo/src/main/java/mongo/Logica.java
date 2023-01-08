package mongo;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Logica {

	public void insert() throws IOException {

		Scanner sc = new Scanner(System.in);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String coleccion = " ";
		boolean flag = true;
		// IP de la DB y puerto

		MongoClient mc = new MongoClient("localhost", 27017);

		// Nombre de la DB

		MongoDatabase db = mc.getDatabase("Comida");

		// Nombre de la colección

		System.out
		.println("Seleccione la colección que desee" + "\n1 - postres" + "\n2 - entrantes" + "\n3 - principales");
		int numcol = sc.nextInt();

		if (numcol == 1) {
			coleccion = "postres";
		} else if (numcol == 2) {
			coleccion = "entrantes";
		} else if (numcol == 3) {
			coleccion = "principal";
		} else {
			System.out.println("error");
		}

		MongoCollection col = db.getCollection(coleccion);

		while (flag) {

			System.out.println("nombre de la comida:");

			String nomCom = reader.readLine();

			System.out.println("descripcion:");

			String desc = reader.readLine();

			System.out.println("precio:");

			double precio = sc.nextDouble();

			Document comida = new Document().append("_id", new ObjectId()).append("nombre", nomCom)
					.append("descripcion", desc).append("precio", precio);

			db.getCollection(coleccion).insertOne(comida);

			System.out.println("desea insertar otro documento= (y/n)");

			char stop = sc.next().toLowerCase().charAt(0);

			if (stop == 'n') {
				flag = false;
			}

		}

	}

	public void update() throws IOException {

		Scanner sc = new Scanner(System.in);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String coleccion = " ";
		boolean flag = true;
		// IP de la DB y puerto

		MongoClient mc = new MongoClient("localhost", 27017);

		// Nombre de la DB

		MongoDatabase db = mc.getDatabase("Comida");

		// Nombre de la colección

		System.out
		.println("Seleccione la colección que desee" + "\n1 - postres" + "\n2 - entrantes" + "\n3 - principales");
		int numcol = sc.nextInt();

		if (numcol == 1) {
			coleccion = "postres";
		} else if (numcol == 2) {
			coleccion = "entrantes";
		} else if (numcol == 3) {
			coleccion = "principal";
		} else {
			System.out.println("error");
		}

		System.out.println("nombre de la comida a editar:");

		String nomEd = reader.readLine();

		System.out.println("nombre nuevo de la comida:");

		String nomEd2 = reader.readLine();

		db.getCollection(coleccion).updateOne(new Document("nombre", nomEd),
				new Document("$set", new Document("nombre", nomEd2)));

	}

	public void delete() throws IOException {
		Scanner sc = new Scanner(System.in);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String coleccion = " ";
		boolean flag = true;
		// IP de la DB y puerto

		MongoClient mc = new MongoClient("localhost", 27017);

		// Nombre de la DB

		MongoDatabase db = mc.getDatabase("Comida");

		// Nombre de la colección

		System.out
		.println("Seleccione la colección que desee" + "\n1 - postres" + "\n2 - entrantes" + "\n3 - principales");
		int numcol = sc.nextInt();

		if (numcol == 1) {
			coleccion = "postres";
		} else if (numcol == 2) {
			coleccion = "entrantes";
		} else if (numcol == 3) {
			coleccion = "principal";
		} else {
			System.out.println("error");
		}

		System.out.println("Borrar:" + "1 - una comida" + "2 - todo");

		int op = sc.nextInt();

		if (op == 1) {
			System.out.println("nombre de la comida a borrar:");

			String borr = reader.readLine();

			db.getCollection(coleccion).deleteOne(new Document("nombre", borr));
		} else if (op == 2) {
			db.getCollection(coleccion).deleteMany(new Document());
			System.out.println("hecho");
		} else {
			System.out.println("error");
		}

	}

	public void find() throws IOException {

		Scanner sc = new Scanner(System.in);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		Bson projection = fields(exclude("_id"), include("nombre", "descripcion", "precio"));

		String coleccion = " ";
		
		// IP de la DB y puerto

		MongoClient mc = new MongoClient("localhost", 27017);

		// Nombre de la DB

		MongoDatabase db = mc.getDatabase("Comida");

		// Nombre de la colección

		System.out
				.println("Seleccione la colección que desee" + "\n1 - postres" + "\n2 - entrantes" + "\n3 - principales");
		int numcol = sc.nextInt();

		if (numcol == 1) {
			coleccion = "postres";
		} else if (numcol == 2) {
			coleccion = "entrantes";
		} else if (numcol == 3) {
			coleccion = "principal";
		} else {
			System.out.println("error");
		}

		System.out.println("buscar por nombre o precio? (n/p)");

		char op = sc.next().toLowerCase().charAt(0);

		if (op == 'n') {

			System.out.println("nombre de la comida a buscar");

			String nombre = reader.readLine();

			FindIterable<Document> result = db.getCollection(coleccion).find(eq("nombre", nombre))
					.projection(projection).sort(ascending("nombre"));

			Iterator it = result.iterator();

			while (it.hasNext()) {

				Document findDoc = (Document) it.next();
				System.out.println(findDoc.toJson());
			}
		} else if (op == 'p') {
			System.out.println("precio maximo a buscar");

			double cantidad = sc.nextDouble();

			FindIterable<Document> result = db.getCollection(coleccion).find(lte("precio", cantidad))
					.projection(projection);

			Iterator it = result.iterator();

			while (it.hasNext()) {

				Document findDoc = (Document) it.next();
				System.out.println(findDoc.toJson());
			}
		}
	}

	public void read() throws IOException {
		Scanner sc = new Scanner(System.in);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		Bson projection = fields(exclude("_id"), include("nombre", "descripcion", "precio"));

		String coleccion = " ";
		boolean flag = true;
		// IP de la DB y puerto

		MongoClient mc = new MongoClient("localhost", 27017);

		// Nombre de la DB

		MongoDatabase db = mc.getDatabase("Comida");

		// Nombre de la colección

		System.out
		.println("Seleccione la colección que desee" + "\n1 - postres" + "\n2 - entrantes" + "\n3 - principales");
		int numcol = sc.nextInt();

		if (numcol == 1) {
			coleccion = "postres";
		} else if (numcol == 2) {
			coleccion = "entrantes";
		} else if (numcol == 3) {
			coleccion = "principal";
		} else {
			System.out.println("error");
		}

		MongoCollection<Document> collection = db.getCollection(coleccion);

		FindIterable<Document> iterable = collection.find().projection(projection);

		Iterator it = iterable.iterator();

		while (it.hasNext()) {

			Document findDoc = (Document) it.next();
			System.out.println(findDoc.toJson());
		}
	}

	public void replace() throws IOException {
		Scanner sc = new Scanner(System.in);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String coleccion = " ";
		boolean flag = true;
		// IP de la DB y puerto

		MongoClient mc = new MongoClient("localhost", 27017);

		// Nombre de la DB

		MongoDatabase db = mc.getDatabase("Comida");

		// Nombre de la colección

		System.out
		.println("Seleccione la colección que desee" + "\n1 - postres" + "\n2 - entrantes" + "\n3 - principales");
		int numcol = sc.nextInt();

		if (numcol == 1) {
			coleccion = "postres";
		} else if (numcol == 2) {
			coleccion = "entrantes";
		} else if (numcol == 3) {
			coleccion = "principal";
		} else {
			System.out.println("error");
		}

		System.out.println("nombre de la comida a editar:");

		String nomEd = reader.readLine();

		System.out.println("nombre nuevo:");

		String nomEd2 = reader.readLine();

		System.out.println("descripcion nueva:");

		String desc = reader.readLine();

		System.out.println("precio nuevo:");

		Double pre = sc.nextDouble();

		db.getCollection(coleccion).replaceOne(new Document("nombre", nomEd),
				new Document().append("nombre", nomEd2).append("descripcion", desc).append("precio", pre));
	}
}
