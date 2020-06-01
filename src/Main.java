import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Main {

	public static void main(String[] args) {
		
		ReadXML readXML = new ReadXML();
		Dictionary<String, ArrayList<String>> symbolsAndMultipliers;
		String [][] reels;
		Dictionary<String, ArrayList<String>> reelStops;
		ArrayList<String> stops;
		Dictionary<String, Integer> dictWildsMultipliers; 
		
		String [][] slotMachine = {
				{"x"},
				{"x","x","x"},
				{"x","x","x","x"},
				{"x","x","x"},
				{"x"}
		};
		
		
		try {
			File file = new File("VS9x9_TNP.xml");
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			System.out.println("Root element: " + document.getDocumentElement().getNodeName());
			
			 if (document.hasChildNodes()) { 
				 readXML.searchSymbolsAndMulitplier(document.getChildNodes());
				 symbolsAndMultipliers = readXML.getSimboli(); //recupero i simboli e i relativi multipliers
				 
				 System.out.println("Symbols And Mulitpliers: "+symbolsAndMultipliers);
				 reels = readXML.getReels(); //recupero i rulli
				 System.out.println("\n");
				 reelStops = readXML.getReelStops();
				 
				 //Dict wildsMoltiplicatori
				 dictWildsMultipliers = readXML.getWildsMoltiplicatori();
				 
				 printSlotMachine(slotMachine);
				 //System.out.println(reelStops.get("47.4"));
				 stops = reelStops.get("2");
				 
				 System.out.println("stops: ");
				 printStopArray(stops);
				 
				 for(int i=0; i<stops.size(); i++)
				 {
					 String strStop = stops.get(i);
					 String [] strStopArr = strStop.split(";");
					 slotMachine = popolaSlot(slotMachine, strStopArr, reels);
					 printSlotMachine(slotMachine);
					 System.out.println("\n");
				 } 
				 
		     }
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void printSlotMachine(String [][] matrice) {
		
		System.out.println("Slot machine: ");
		for(int i=0; i<matrice.length; i++) {
			
			String [] strArray = matrice[i];
			for(int j=0; j<strArray.length; j++) {
				System.out.print(matrice[i][j]+" ");
			}
			System.out.println("\n");
		}
	}

	public static void printStopArray( ArrayList<String> arr) {
		
		for(int i=0; i<arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}

	public static void printStringArr(String [] arr) {
		
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static String [][] popolaSlot( String [][] slot, String [] stops, String [][] reels) {
		
		 for(int j=0; j<stops.length; j++) {
			 String posStr = stops [j];
			 int posInt = Integer.parseInt(posStr);
			 
			 // Se sono nel 2° o 4° rullo
			 if( j == 1 || j == 3) {
				 
				 if( posInt == reels[j].length - 1 ) { //se sono alla fine del rullo
					 slot [j][0] = reels[j][posInt];
					 slot [j][1] = reels [j][0];
					 slot [j][2] = reels [j][1];
				 
				 }else if ( posInt == reels[j].length - 2 ) {
					 slot [j][0] = reels[j][posInt];
					 slot [j][1] = reels [j][posInt + 1];
					 slot [j][2] = reels [j][0];
				 
				 }else {
					 slot [j][0] = reels[j][posInt];
					 slot [j][1] = reels[j][posInt + 1];
					 slot [j][2] = reels[j][posInt + 2];
				 }
				 
			 }else if( j == 2 ) {
				 
				 if( posInt == reels[j].length - 1 ) { //se sono alla fine del rullo
					 slot [j][0] = reels[j][posInt];
					 slot [j][1] = reels [j][0];
					 slot [j][2] = reels [j][1];
					 slot [j][3] = reels [j][2];
				 
				 }else if ( posInt == reels[j].length - 2 ) {
					 slot [j][0] = reels[j][posInt];
					 slot [j][1] = reels [j][posInt + 1];
					 slot [j][2] = reels [j][0];
					 slot [j][3] = reels [j][1];
				 
				 }else if ( posInt == reels[j].length - 3 ) {
					 slot [j][0] = reels[j][posInt];
					 slot [j][1] = reels[j][posInt + 1];
					 slot [j][2] = reels[j][posInt + 2];
					 slot [j][3] = reels[j][0];
				 
				 }else {
					 slot [j][0] = reels[j][posInt];
					 slot [j][1] = reels[j][posInt + 1];
					 slot [j][2] = reels[j][posInt + 2];
					 slot [j][3] = reels[j][posInt+3];
				 }
			 
			 }else {
				 
				 slot [j][0] = reels[j][posInt];
			 }
			 
		 }
		 
		 return slot;
	}
}
