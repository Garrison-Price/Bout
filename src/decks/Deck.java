package decks;

import cards.Card;
import java.util.Random;
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
        topCardIndex = correctIndex(topCardIndex);
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
        topCardIndex = correctIndex(topCardIndex);
        cards[topCardIndex] = newCard;
        return true;
    }
    
    //Adds a new Card to a random spot in the deck.
    //If the deck is full, false is returned.
    public boolean addCardRandom(Card newCard) {
        if(isFull()) 
            return false;
        int insertionPoint = (int)(Math.random()*cards.length) + topCardIndex;
        for(int currentCardIndex = (topCardIndex + currentSize + 1); currentCardIndex > insertionPoint; currentCardIndex--) {
            int correctedCurrentCardIndex = correctIndex(currentCardIndex);
            int correctedPreviousCardIndex = correctIndex(currentCardIndex - 1);
            cards[correctedCurrentCardIndex] = cards[correctedPreviousCardIndex];
        }
        cards[correctIndex(insertionPoint)] = newCard;
        return true;
    }
    
    //Randomly shuffles the deck and moves the top car index back to zero.
    public void randomizeDeck() {
        Card[] cardHolder = new Card[currentSize];
        int cardIndex = 0;
        while(!isEmpty())
            cardHolder[cardIndex++] = nextCard();
        shuffleArray(cardHolder);
        for(cardIndex = 0; cardIndex < currentSize; cardIndex++)
            addCardBottom(cardHolder[cardIndex]);
    }
    
    //Shuffle an Array of Cards using Fisher-Yates
    private void shuffleArray(Card[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);

            Card a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
    
    //Copies the contents of the current Deck to a new Deck and returns it.
    public Deck deckCopy() {
        Deck newDeck = new Deck(cards.length);
        for(int newDeckIndex = 0; newDeckIndex < currentSize; newDeckIndex++) {
            int nextCardIndex = topCardIndex+newDeckIndex;
            topCardIndex = correctIndex(topCardIndex);
            newDeck.addCardBottom(cards[nextCardIndex]);
        }
        return newDeck;
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
    
    //Adjusts the index at the end and beginning of the array.
    private int correctIndex(int index) {
        if(index == cards.length)
            index = 0;
        else if(index < 0)
            index = cards.length - 1;
        return index;
    }
}
