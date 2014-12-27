package decks;

import cards.Card;
import mainPack.Constants;

/**
 * Deck
 * The deck is an array based, circular queue.
 * It stores Cards with a few insertion methods that can either put new Cards
 * on top of the deck, on the bottom of the deck, or randomly in the middle.
 * The only way to remove a card from the deck is to draw it off of the top.
 * The deck can be randomized and can also be copied from another deck.
 * @author Garrison Price
 */
public class Deck {
    private Card[] cards;
    private int topCardIndex;
    private int currentSize;
    
    //Initializes a new Empty Deck with the default maximum size.
    public Deck() {
        cards = new Card[Constants.DECK_DEFAULT_SIZE];
        currentSize = topCardIndex = 0;
    }
    
    //Initializes a new Empty Deck with the maximum size provided.
    public Deck(int size) {
        cards = new Card[size];
        currentSize = topCardIndex = 0;
    }
    
    //Returns and Removes the top card from the deck.
    //If there are no cards in the deck, null is returned.
    public Card nextCard() {
        if(isEmpty())
            return null;
        correctTopCardIndex();
        currentSize--;
        return cards[topCardIndex++];
    }
    
    //Adds a new Card to the bottom of the deck.
    //If the deck is full, false is returned.
    public boolean addCardBottom(Card newCard) {
        if(isFull()) 
            return false;
        currentSize++;
        cards[(topCardIndex + currentSize) % cards.length] = newCard;
        return true;
    }
    
    //Adds a new Card to the top of the deck.
    //If the deck is full, false is returned.
    public boolean addCardTop(Card newCard) {
        if(isFull()) 
            return false;
        currentSize++;
        topCardIndex--;
        correctTopCardIndex();
        cards[topCardIndex] = newCard;
        return true;
    }
    
    //Adds a new Card to a random spot in the deck.
    //If the deck is full, false is returned.
    public boolean addCardRandom(Card newCard) {
        if(isFull()) 
            return false;
        //Not Implemented Yet
        return true;
    }
    
    //Randomly shuffles the deck and moves the top car index back to zero.
    public void randomizeDeck() {
        //Not Implemented Yet
    }
    
    //Copies the contents of the current Deck to a new Deck
    //This needs more thought as to how it will be used.
    public Deck deckCopy() {
        //Not Implemented Yet
        return null;
    }
    
    public boolean isEmpty() {
        return currentSize == 0;
    }
    
    public boolean isFull() {
        return currentSize == cards.length;
    }
    
    public int size() {
        return currentSize;
    }
    
    //Adjusts the top card index at the end and beginning of the array.
    private void correctTopCardIndex() {
        if(topCardIndex == cards.length)
            topCardIndex = 0;
        if(topCardIndex < 0)
            topCardIndex = cards.length - 1;
    }
}
