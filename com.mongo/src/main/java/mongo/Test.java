package mongo;

import java.io.IOException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		Logica lc = new Logica();
		Scanner sc = new Scanner(System.in);
		boolean flag = true;

		while (flag) {

			System.out.println("Operaciones:" + "\n1- insertar" + "\n2- actualizar" + "\n3- eliminar"
					+ "\n4- mostrar documentos" + "\n5- buscar" + "\n6- reemplazar" + "\n7- salir");

			int op = sc.nextInt();

			switch (op) {

			case 1:
				lc.insert();
				break;
			case 2:
				lc.update();
				break;
			case 3:
				lc.delete();
				break;
			case 4:
				lc.read();
				break;
			case 5:
				lc.find();
				;
				break;
			case 6:
				lc.replace();
				break;
			case 7:
				System.out.println("adeu");
				flag = false;
				break;
			}

		}

	}

}
