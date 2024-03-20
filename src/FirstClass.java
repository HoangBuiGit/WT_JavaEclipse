import java.lang.reflect.Array;
import java.util.*;

public class FirstClass {
	
	static int HandValue(List<Card> Hand) {
		int result = 0;
		//System.out.printf("\nHand Size is %d",Hand.size());
		for(int i=0; i < Hand.size();i++) {
			result =result+ Hand.get(i).V;
			
		}
		return result;
	}
	
	static ArrayList<String> Hand_list(List<Card> Hand) {
		ArrayList<String> result = new ArrayList<String>();
		//System.out.printf("\nHand Size is %d",Hand.size());
		for(int i=0; i < Hand.size();i++) {
			result.add(Hand.get(i).C);
			
		}
		return result;
	}
	
	static List<Card> Create_Deck(String[] All_Types, String[] All_Ranks, int[] All_Values) {
		List<Card> Deck = new ArrayList<Card>();
		
		
		for(int i=0;i<All_Types.length; i++) {
			for(int j=0; j<All_Ranks.length; j++) {
				
				Card New_Card = new Card(All_Ranks[j]+" of "+All_Types[i],All_Values[j]);
				Deck.add(New_Card);
			}
		}
		
		return Deck;
	}
	
	
	
	public static void main(String[] args) {
		String[] All_Types = new String[] {"Hearts","Diamonds","Clubs","Spades"};
		String[] All_Ranks = new String[] {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
		int[] All_Values = new int[] {11,2,3,4,5,6,7,8,9,10,10,10,10};
		ArrayList<String> Acepetions = new ArrayList<String>(Arrays.asList("Ace of Hearts","Ace of Diamonds","Ace of Clubs","Ace of Spades"));
		//System.out.println(Arrays.toString(All_CardTypes));
		
		List<Card> Deck = new ArrayList<Card>();
		Deck = Create_Deck(All_Types,All_Ranks,All_Values);
		
		
		Collections.shuffle(Deck);

		
		Scanner User_input = new Scanner(System.in);
		System.out.println("\nStart of Game.");
	

		List<Card> Dealer = new ArrayList<Card>();
		Dealer.add(Deck.get(0));
		Dealer.add(Deck.get(1));
		
		List<Card> Hand = new ArrayList<Card>();
		Hand.add(Deck.get(2));
		Hand.add(Deck.get(3));
		
		System.out.printf("The Dealer's first card is %s",Dealer.get(0).C);
		System.out.printf("\nWith value %d\n",Dealer.get(0).V);
		int HandCounter = 4;
		
		int PlayerHand = HandValue(Hand);
		int DealerHand = HandValue(Dealer);
		
		ArrayList<String> Hand_of_player = Hand_list(Hand);
		System.out.println("\nYour hand is " + Hand_of_player.toString());
		System.out.printf("Your hand value is %d",PlayerHand);
		
		//Hand_of_player.retainAll(Acepetions);
		//int NAce = Hand_of_player.size();
		
		int NAce = 0;
		
		//Hand_of_player.retainAll(Acepetions);
		//System.out.println("\nYour hand is " + Hand_of_player.toString());
		
		System.out.println("\nEnter action:");
		String Test_input = User_input.nextLine();
		while(!"q".equals(Test_input)) {

			
			//if(Test_input)
			//System.out.println("\nPlease enter k,q or p:");
			if("k".equals(Test_input)) {
				Hand.add(Deck.get(HandCounter));
				PlayerHand = PlayerHand + Hand.get(HandCounter-2).V;
				
				HandCounter++;
				//PlayerHand = HandValue(Hand);
				Hand_of_player = Hand_list(Hand);
				System.out.println("\nYour hand is " + Hand_of_player.toString());
				Hand_of_player.retainAll(Acepetions);
				
				System.out.printf("\nYour hand value is %d",PlayerHand);
				if(PlayerHand==21) {
					System.out.println("\nBLACKJACK. You won");
					Test_input = "q";
				}
				else if(PlayerHand>21 && Hand_of_player.isEmpty()||PlayerHand>21 && NAce==Hand_of_player.size()) {
					System.out.println("\nBust.");
					Test_input = "q";
				}
				else if(PlayerHand>21 && NAce!=Hand_of_player.size()) {
					PlayerHand = PlayerHand -10;
					System.out.printf("\nYou have one ace. So you can continue. With value %d. Enter action:",PlayerHand);
					NAce++;
					Test_input = User_input.nextLine();
				}
				
				else {
					System.out.println("\nEnter action:");
					Test_input = User_input.nextLine();
				}	
			}
			else if("p".equals(Test_input)) {
				System.out.printf("\nDealer hand value starts with %d",DealerHand);
				while(DealerHand < 17) {
					Dealer.add(Deck.get(HandCounter));
					DealerHand = HandValue(Dealer);
					HandCounter++;
				}
				
				System.out.printf("\nDealer hand value is %d",DealerHand);
				System.out.printf("\nYour hand value is %d",PlayerHand);
				if(DealerHand>21) {
					System.out.println("\nDealer bust. You won.");
					Test_input = "q";
				}
				else if (PlayerHand<DealerHand){
					System.out.println("\nYou lost. Dealer has more points.");
					Test_input = "q";
				}
				else if (PlayerHand>DealerHand){
					System.out.println("\nYou won. You have more points.");
					Test_input = "q";
				}
				else if(PlayerHand==DealerHand){
					System.out.println("\nTie you both have the same amount of points.");
					Test_input = "q";
				}
				Test_input="q";
			}
			else {
				System.out.println("\nPlease enter either k to get a card, p to pass or q to stop:");
				Test_input = User_input.nextLine();
			}
			
		}
		
		System.out.println("\nGame has ended!");
		
		//System.out.printf("C: %d \n V: %d",card_1.C, card_1.V);		
	}
}