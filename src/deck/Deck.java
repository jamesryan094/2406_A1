package deck;

import java.util.ArrayList;
import java.util.Collections;

import cards.Card;

/**
 * ArrayList to store, shuffle and deal Cards. Card type objects.
 *
 * <p>Created by james on 6/09/2016.
 */
public class Deck {

  private ArrayList<Card> cards = new ArrayList<>();

  /**
   * Removes the passed number of cards for starting hand from the deck and returns them in a
   * variable array
   *
   * @param numCardsPerHand indicates the number of cards to 'deal'
   * @return ArrayList of Card objects
   */
  public ArrayList<Card> dealHand(int numCardsPerHand) {
    ArrayList<Card> hand = new ArrayList<>();
    for (int i = 0; i < numCardsPerHand; i++) {
      hand.add(this.cards.remove(i));
    }
    return hand;
  }

  /**
   * Takes a Card object and adds it to the cards field
   *
   * @param card Card object to be added to deck
   */
  void addToDeck(Card card) {
    cards.add(card);
  }

  /**
   * Removes the last three cards of the unprocessed deck as, based on the PLIST, these are knows to
   * be rule cards which have no function in the command line version of Mineral Supertrumps
   */
  public void removeRuleCards() {
    cards.remove(cards.size() - 1);
    cards.remove(cards.size() - 1);
    cards.remove(cards.size() - 1);
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }

  public Card removeCard(int i) {
    return cards.remove(i);
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public Card getGeophys(){
    Card geophys = null;
    for(int i=0; i< cards.size(); ++i){
      Card currentCard = cards.get(i);
      if (currentCard.getTitle().equals("The Geophysicist")){
        geophys = currentCard;
        cards.remove(i);
      }
    }
    return geophys;
  }

  public Card getMagnetite(){
    Card mag = null;
    for(int i=0; i< cards.size(); ++i){
      Card currentCard = cards.get(i);
      if (currentCard.getTitle().equals("Magnetite")){
        mag = currentCard;
        cards.remove(i);
      }
    }
    return mag;
  }

}
