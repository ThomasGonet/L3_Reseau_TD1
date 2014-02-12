package reseau;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Reseau {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			FileInputStream fis = new FileInputStream("wir");
			Scanner sc = new Scanner(fis);
			String dataGram = null;
			char[] data;
			
			if(sc.hasNextLine())
				dataGram = sc.nextLine();
			
			data = dataGram.toCharArray();
			
			int ihl = (Character.getNumericValue(data[1]))*4;
			
			System.out.println("Version ip : " + data[0]);
			System.out.println("Longueur de l'entete (en bit) : " + ihl );
			
			String ln = "" + data[4] + data[5] + data[6] + data[7];
			
			int lnt = Integer.parseInt(ln, 16);
			
			System.out.println("Longueur totale du datagramme (en octet(s)) : " + lnt);
			
			System.out.println("Longueur déduite du champ de données (en octet(s)) : " +  (lnt - (ihl / 4)) );
			
			String id = "" + data[8] + data[9] + data[10] + data[11];
			
			System.out.println("Identifiant : " + id + " (en hexa) " + Integer.parseInt(id, 16) + " (en decimal)");
			
			int flag = Integer.parseInt("" + data[12], 16);
			
			String fl = Integer.toBinaryString(flag);
			
			char[] fla = fl.toCharArray();
			
			int lengthFl = fl.length();
			
			String decalage = "" + fla[lengthFl - 1] + data[13] + data[14] + data[15];
			
			System.out.println("Decalage du fragment : " + Integer.parseInt(decalage, 16));
			
			System.out.println("TTL : " + Integer.parseInt("" + data[16] + data[17], 16));
			
			
			
			fis.close();
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
