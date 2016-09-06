/**Practising parsing an XML plist file
 * Created by James on 23/08/2016.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Deck {
    public static void main(String[] args) {
//        Open the plist file as a file
        File cardsFile = new File("MstCards_151021.plist");
//        making a doc builder to help parse the XML into a DOM
        DocumentBuilderFactory factoryBuilder = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuild = factoryBuilder.newDocumentBuilder();
            Document properties = docBuild.parse(cardsFile);
            properties.getDocumentElement().normalize();
//         made list of all the nodes in the document with the tag dict. These are all the cards, each list element is 1 card.
            NodeList deckList = properties.getElementsByTagName("dict");

            for (int i = 1; i < deckList.getLength(); ++i) {
                ArrayList keyArray = new ArrayList();
                ArrayList valArray = new ArrayList();
//          Accessing just one card at a time. Checking that the node is an element node, and typecasting.
                Node cardNode = deckList.item(i);
                if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element card = (Element) cardNode;
//          All card properties are a key value pair, getting keys here
                    NodeList allAttributes = card.getElementsByTagName("key");
                    for (int j = 0; j < allAttributes.getLength(); ++j) {


                        Node attributeNode = allAttributes.item(j);
                        if (attributeNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element attribute = (Element) attributeNode;
                            String attributeText = attribute.getTextContent().toLowerCase();
//                            Need to skip the one key in each card which is actually a value, not a property
                            if (attributeText.equals("play") || attributeText.equals("trump") || attributeText.equals("rule")){

                            }
                            else{

                                keyArray.add(attributeText);
                            }

                        }


                    }
                    System.out.println(keyArray);
//                    Getting all the child nodes in order to get at the values (since not all values are strings)
                    NodeList childNodes = card.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node attributeValNode = childNodes.item(j);
                        String attributeValName = attributeValNode.getNodeName();
                        if (attributeValName.equals("array")){
                            ArrayList occurrenceArray = new ArrayList();
                            NodeList arrayStrings = attributeValNode.getChildNodes();
//                            Need to nest another loop to iterate through and pull each occurrence string separately
                            for (int k = 0; k < arrayStrings.getLength(); ++k){
                                Node stringNode = arrayStrings.item(k);
                                if (stringNode.getNodeType() == Node.ELEMENT_NODE && stringNode.getNodeName().equals("string")){
                                    Element occurrenceString = (Element) stringNode;

                                    String occurrenceText = occurrenceString.getTextContent();
                                    occurrenceArray.add(occurrenceText);
                                }
                            }
                            valArray.add(occurrenceArray);
                        }
                        else if (attributeValName.equals("string")) {
                            valArray.add(attributeValNode.getTextContent());
                        }
                        else if (attributeValName.equals("key")){
                            String keyText = attributeValNode.getTextContent();
//                            Only these words are values stored as <key>, the rest are all geniune keys.
                            if (keyText.equals("play") || keyText.equals("trump") || keyText.equals("rule")){
                                valArray.add(attributeValNode.getTextContent());
                            }
                        }
                    }
                    if (valArray.size() == 13) {
                        MineralCard tempCard = new MineralCard(valArray);
                        tempCard.displayAttributes();
                    }
                    else{
                        TrumpCard tempCard = new TrumpCard(valArray);
                        tempCard.displayAttributes();
                    }
                    System.out.println(valArray);
                    System.out.println("------------------------------------------------------");

                }

            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
