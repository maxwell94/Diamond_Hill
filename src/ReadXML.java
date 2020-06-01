import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {

	private Dictionary<String, ArrayList<String>> simboli;
	private String[][] reels;
	private Dictionary<String, ArrayList<String>> reelStops;
	private Dictionary <String, Integer> wildsMoltiplicatori; 

	String nomeSimbolo = "";
	String win = "";

	ReadXML() {

		this.simboli = new Hashtable<String, ArrayList<String>>();
		this.reelStops = new Hashtable<String, ArrayList<String>>();
		this.wildsMoltiplicatori = new Hashtable<String, Integer>();

		this.reels = new String[5][];
		this.reels[0] = new String[] { "1_1", "2_1", "2_1", "3_1", "6_1", "4_1", "5_1", "6_1", "7_1", "5_1", "8_1",
				"1_1", "3_1", "9_1", "4_1", "10_1", "5_1", "11_1", "6_1", "7_1", "12_1", "1_1", "13_1", "2_1", "2_1",
				"14_1", "3_1", "15_1", "1_1", "16_1" };
		this.reels[1] = new String[] { "1_1", "7_1", "2_1", "5_1", "1_1", "6_1", "4_1", "5_1", "3_1", "3_1", "2_1",
				"2_1", "5_1", "1_1", "1_1", "6_1", "4_1", "4_1", "2_1", "7_1", "2_1", "3_1", "3_1", "5_1", "1_1", "2_1",
				"3_1", "3_1", "4_1", "6_1", "1_1", "5_1", "2_1", "7_1", "2_1", "2_1", "6_1", "4_1", "4_1", "7_1",
				"5_1" };
		this.reels[2] = new String[] { "1_1", "1_1", "5_1", "3_1", "3_1", "2_1", "4_1", "4_1", "4_1", "4_1", "7_1",
				"2_1", "1_1", "6_1", "4_1", "5_1", "3_1", "3_1", "2_1", "5_1", "2_1", "1_1", "3_1", "5_1", "4_1", "6_1",
				"1_1", "1_1", "5_1", "5_1", "3_1", "3_1", "1_1", "1_1", "2_1", "7_1", "4_1", "6_1", "2_1", "3_1", "5_1",
				"1_1", "1_1", "7_1", "2_1", "3_1", "3_1", "6_1", "4_1", "5_1", "2_1", "6_1", "2_1" };
		this.reels[3] = new String[] { "1_1", "7_1", "2_1", "5_1", "1_1", "6_1", "4_1", "5_1", "3_1", "3_1", "2_1",
				"2_1", "5_1", "1_1", "1_1", "6_1", "4_1", "4_1", "2_1", "7_1", "2_1", "3_1", "3_1", "5_1", "1_1", "2_1",
				"3_1", "3_1", "4_1", "6_1", "1_1", "5_1", "2_1", "7_1", "2_1", "2_1", "6_1", "4_1", "4_1", "7_1",
				"5_1" };
		this.reels[4] = new String[] { "1_1", "2_1", "2_1", "3_1", "6_1", "4_1", "5_1", "6_1", "7_1", "5_1", "8_1",
				"1_1", "3_1", "9_1", "4_1", "10_1", "5_1", "11_1", "6_1", "7_1", "12_1", "1_1", "13_1", "2_1", "2_1",
				"14_1", "3_1", "15_1", "1_1", "16_1" };
		
		this.wildsMoltiplicatori.put("8_1", 1);
		this.wildsMoltiplicatori.put("9_1", 2);
		this.wildsMoltiplicatori.put("10_1", 3);
		this.wildsMoltiplicatori.put("11_1", 4);
		this.wildsMoltiplicatori.put("12_1", 5);
		this.wildsMoltiplicatori.put("13_1", 6);
		this.wildsMoltiplicatori.put("14_1", 7);
		this.wildsMoltiplicatori.put("15_1", 8);
		this.wildsMoltiplicatori.put("16_1", 9);
		

	}
	
	public Dictionary<String, Integer> getWildsMoltiplicatori() {
		return this.wildsMoltiplicatori; 
	}

	public Dictionary<String, ArrayList<String>> getReelStops() {
		return this.reelStops;
	}

	public Dictionary<String, ArrayList<String>> getSimboli() {
		return simboli;
	}

	public String[][] getReels() {
		return this.reels;
	}

	public void setSimboli(Dictionary<String, ArrayList<String>> simboli) {
		this.simboli = simboli;
	}

	public void searchSymbolsAndMulitplier(NodeList nodeList) {

		ArrayList<String> simboloMultipliers = new ArrayList<String>();
		ArrayList<String> stops = new ArrayList<String>(); 
		
		
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node elemNode = nodeList.item(count);
			if (elemNode.getNodeType() == Node.ELEMENT_NODE) {
// get node name and value  
				System.out.println("\nNode Name =" + elemNode.getNodeName() + " [OPEN]");
				if (elemNode.getNodeName().equals("PayoutDefinition")) {

					if (elemNode.hasAttributes()) {

						NamedNodeMap nodeMap = elemNode.getAttributes();
						for (int i = 0; i < nodeMap.getLength(); i++) {
							Node node = nodeMap.item(i);
							// System.out.println("attr name : " + node.getNodeName());
							// System.out.println("attr value : " + node.getNodeValue());
							if (node.getNodeName().equals("name")) {
								nomeSimbolo = node.getNodeValue();								
								System.out.println("nome simbolo: " + node.getNodeValue());
							}
						}
					}

				} else if (elemNode.getNodeName().equals("Item")) {

					if (elemNode.hasAttributes()) {
						NamedNodeMap nodeMap = elemNode.getAttributes();
						for (int i = 0; i < nodeMap.getLength(); i++) {
							Node node = nodeMap.item(i);
							// System.out.println("attr name : " + node.getNodeName());
							// System.out.println("attr value : " + node.getNodeValue());
							if ( node.getNodeName().equals("multiplier") ) {
								System.out.println("multiplier: " + node.getNodeValue());
								simboloMultipliers.add(node.getNodeValue());
								
								simboli.put(nomeSimbolo, simboloMultipliers);
								System.out.println(simboli);
							}
							else if( node.getNodeName().equals("win") ) {
								System.out.println("win: " + node.getNodeValue());
								win = node.getNodeValue();
							}
							
						}
					}

				}else if (elemNode.getNodeName().equals("P")) {
					System.out.println("P value: "+elemNode.getTextContent());
					stops.add(elemNode.getTextContent());
					//reelStops.put(win, stops);
					//System.out.println(reelStops);
				}
				// System.out.println("Node Content =" + elemNode.getTextContent());

				if (elemNode.hasChildNodes()) {
					// recursive call if the node has child nodes
					searchSymbolsAndMulitplier(elemNode.getChildNodes());
				}
                
				System.out.println("Node Name =" + elemNode.getNodeName() + " [CLOSE]");
				if(  elemNode.getNodeName().equals("P") ) {
					reelStops.put(win, stops);
				}

			}
		}
	}


}