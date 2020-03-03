package inf112.skeleton.app;

import java.util.Random;

public class Deck {
    int deckSize = 84;
    private Card[] deck;

    public Deck() {
        Card[] temp = new Card[deckSize];
        for (int numCards = 0; numCards < 84; numCards++){
            if (numCards < 18) {
                temp[numCards] = new MovementCard(numCards,1);
            } else if (numCards < 30) {
                temp[numCards] = new MovementCard(numCards,2);
            } else if (numCards < 36) {
                temp[numCards] = new MovementCard(numCards,3);
            } else if (numCards < 42) {
                temp[numCards] = new MovementCard(numCards,-1);
            } else if (numCards < 60){
                temp[numCards] = new RotationCard(numCards,Type.ROTATE_CLOCKWISE);
            } else if (numCards < 78){
                temp[numCards] = new RotationCard(numCards,Type.ROTATE_COUNTER_CLOCKWISE);
            } else {
                temp[numCards] = new RotationCard(numCards,Type.ROTATE_U);
            }
        }
        this.deck = temp;
    }

    //Implementing Fisher–Yates / Knuth shuffle
    public void shuffle() {
        Random rand = new Random();
        int length = deck.length;
        for (int i = length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            Card temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;

        }
    }

    @Override
    public String toString(){
        String result = "";
        for (Card i : deck){
            result += (i.toString() + "\n");
        }
        return result;
    }

    public void dealHand(Player player) {
        int choiceSize = player.getHealthPoints();
        Card[] cardChoices = new Card[choiceSize];
        for (int i = 0; i<choiceSize; i++) {
            cardChoices[i] = deck[i];
        }
        PlayerHand p = new PlayerHand();
        p.setPossibleHand(cardChoices);
        player.hand = p;
        deckSize -= choiceSize;
        resize(deck, choiceSize);
    }

    public Card[] resize (Card[] oldDeck, int sizeDifference) {
        Card[] newDeck = new Card[deckSize];
        for (int i = 0; i < deckSize; i ++) {
            newDeck[i] = oldDeck[8+i];
        }
        return newDeck;
    }



}
