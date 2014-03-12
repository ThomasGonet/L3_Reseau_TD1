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
			
			int ihl = (Character.getNumericValue(data[1]))*32;
			
			System.out.println("Version ip : " + data[0]);
			System.out.println("Longueur de l'entete (en bits) : " + ihl );
			
			String ln = "" + data[4] + data[5] + data[6] + data[7];
			
			int lnt = Integer.parseInt(ln, 16);
			
			System.out.println("Longueur totale du datagramme (en octet(s)) : " + lnt);
			
			System.out.println("Longueur déduite du champ de données (en octet(s)) : " +  (lnt - (ihl / 8)) );
			
			String id = "" + data[8] + data[9] + data[10] + data[11];
			
			System.out.println("Identifiant : " + id + " (en hexa) " + Integer.parseInt(id, 16) + " (en decimal)");
			
			int flag = Integer.parseInt("" + data[12], 16);
			
			String fl = Integer.toBinaryString(flag);
			
			char[] fla = fl.toCharArray();
			
			int lengthFl = fl.length();
			
			String decalage = "" + fla[lengthFl - 1] + data[13] + data[14] + data[15];
			
			System.out.println("Decalage du fragment : " + Integer.parseInt(decalage, 16));
			
			System.out.println("TTL : " + Integer.parseInt("" + data[16] + data[17], 16));
			
			int protocole =  Integer.parseInt("" + data[18] + data[19], 16);
			
			String str_protocole = null;
			
			switch (protocole) {
			case 1:
				str_protocole = "ICMP";
				break;
			case 2:
				str_protocole = "IGMP";
				break;
			case 6:
				str_protocole = "TCP";
				break;
			case 17:
				str_protocole = "UDP";
				break;
				
			default:
				str_protocole = "inconnu";
				break;
			}
			
			System.out.println("Protocole : " + protocole + " (" + str_protocole + ")");
			
			int checksum =  Integer.parseInt("" + data[20] + data[21] + data[22] + data[23], 16);
			
			System.out.println("Somme de contrôle : " + checksum);
			
			int as1 = Integer.parseInt("" + data[24] + data[25], 16);
			int as2 = Integer.parseInt("" + data[26] + data[27], 16);
			int as3 = Integer.parseInt("" + data[28] + data[29], 16);
			int as4 = Integer.parseInt("" + data[30] + data[31], 16);
			
			System.out.println("Adresse source : " + as1 + "." + as2 +"." + as3 +"." + as4 );
			
			int ad1 = Integer.parseInt("" + data[32] + data[33], 16);
			int ad2 = Integer.parseInt("" + data[34] + data[35], 16);
			int ad3 = Integer.parseInt("" + data[36] + data[37], 16);
			int ad4 = Integer.parseInt("" + data[38] + data[39], 16);
			
			System.out.println("Adresse de destination : " + ad1 + "." + ad2 +"." + ad3 +"." + ad4 );
			
			int somme = 0;
			String datagramme = "";
			
			for(int j = 0; j < 40; j++)
			{
				if(j == 20 ||j == 21 ||j == 22 ||j == 23)
					datagramme += "0";
				else
					datagramme += data[j];
			}
				
			//System.out.println(Integer.toBinaryString(Integer.parseInt(datagramme,16)));
			
			//System.out.println(datagramme);
			
			for(int i = 0; i < 40; i += 4){
					somme += Integer.parseInt(datagramme.substring(i, (i+4)), 16);
					somme = ((somme >> 16) & 0xFFFF) + (somme & 0xFFFF);
					//System.out.println(somme);
			}
			
			somme =  somme ^0xFFFF;
			System.out.println("Somme de contrôle calculée : " + somme);
			
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
