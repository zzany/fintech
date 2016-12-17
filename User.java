package tester;

public class User implements Comparable<User> {
	private String ownCard;
	private String cardWanted;
	private int ownCardValue;
	private double startTime;
	
	public User(String cardHad, String cardWanted, int cardValue, double startTime) {
		ownCard = cardHad;
		this.cardWanted = cardWanted;
		ownCardValue = cardValue;
		this.startTime = startTime;
	}
	
	public int getValue() {
		return ownCardValue;
	}
	
	public double getStartTime() {
		return startTime;
	}
	
	public String getOwnCard() {
		return ownCard;
	}

	public String getCardWanted() {
		return cardWanted;
	}
	
	@Override
	public boolean equals(Object o) {
		// If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof User)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members 
        User u = (User) o;
         
        // Compare the data members and return accordingly 
        return Double.compare(this.getStartTime(), u.getStartTime()) == 0;
	}

	public int compareTo(User arg0) {
		if(this.getStartTime() < arg0.getStartTime()) {
			return -1; 
		}
		else if (this.getStartTime() == arg0.getStartTime()) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
