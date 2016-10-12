package deck;

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

import cards.Card;
import cards.MineralCard;
import cards.TrumpCard;

/** Class builds a Mineral Supertrump deck from file. Created by James on 23/08/2016. */
public class GenerateDeckFromPLIST {

  /**
   * Generate Deck type object that stores Card type objects created based on data parsed from a
   * PLIST file.
   *
   * @return a deck of Mineral Supertrump cards formatted as Card type objects.
   */
  public static Deck buildDeck() {
    Deck deck = new Deck();
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
        ArrayList<String> valArray = new ArrayList<>();
        //          Accessing just one card at a time. Checking that the node is an element node, and typecasting.
        Node cardNode = deckList.item(i);
        if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
          Element card = (Element) cardNode;
          //                    Getting all the child nodes in order to get at the values (since not all values are strings)
          NodeList childNodes = card.getChildNodes();
          for (int j = 0; j < childNodes.getLength(); j++) {
            Node attributeValNode = childNodes.item(j);
            String attributeValName = attributeValNode.getNodeName();
            switch (attributeValName) {
              case "array":
                ArrayList<String> occurrenceArray = new ArrayList<>();
                NodeList arrayStrings = attributeValNode.getChildNodes();
                //                            Need to nest another loop to iterate through and pull each occurrence string separately
                for (int k = 0; k < arrayStrings.getLength(); ++k) {
                  Node stringNode = arrayStrings.item(k);
                  if (stringNode.getNodeType() == Node.ELEMENT_NODE
                      && stringNode.getNodeName().equals("string")) {
                    Element occurrenceString = (Element) stringNode;

                    String occurrenceText = occurrenceString.getTextContent();
                    occurrenceArray.add(occurrenceText);
                  }
                }
                valArray.add(occurrenceArray.toString());
                break;
              case "string":
                valArray.add(attributeValNode.getTextContent());
                break;
              case "key":
                String keyText = attributeValNode.getTextContent();
                //                            Only these words are values stored as <key>, the rest are all geniune keys.
                if (keyText.equals("play") || keyText.equals("trump") || keyText.equals("rule")) {
                  valArray.add(attributeValNode.getTextContent());
                }
                break;
            }
          }
          if (valArray.size() == 13) {
            Card tempCard = new MineralCard(valArray);
            deck.addToDeck(tempCard);
          } else {
            Card tempCard = new TrumpCard(valArray);
            deck.addToDeck(tempCard);
          }
        }
      }
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
    return deck;
  }
}
