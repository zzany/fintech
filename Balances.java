package tester;

public class Balances {
	long iT = 0;
	long Am = 0;
	long Wa = 0;
	long Gi = 0;
	long Ta = 0;
	long Be = 0;
	long St = 0;
	long eB = 0;
	long Ap = 0;
	long Ho = 0;
	long Kr = 0;
	long Ik = 0;
	long Co = 0;
	long Lo = 0;
	long Mc = 0;
	long Ma = 0;
	long No = 0;
	long Ga = 0;
	long Su = 0;
	long Sh = 0;
	long Sa = 0;
	long cash = 0;
	
	public Balances(int cash) {
		this.cash = cash;
	}
	
	public long getBalance(String card) {
		if (card.equals("iT")) {
			return this.iT;
		}
		if (card.equals("Am")) {
			return this.Am;
		}
		if (card.equals("Wa")) {
			return this.Wa;
		}
		if (card.equals("Gi")) {
			return this.Gi;
		}
		if (card.equals("Ta")) {
			return this.Ta;
		}
		if (card.equals("Be")) {
			return this.Be;
		}
		if (card.equals("St")) {
			return this.St;
		}
		if (card.equals("eB")) {
			return this.eB;
		}
		if (card.equals("Ap")) {
			return this.Ap;
		}
		if (card.equals("Ho")) {
			return this.Ho;
		}
		if (card.equals("Kr")) {
			return this.Kr;
		}
		if (card.equals("Ik")) {
			return this.Ik;
		}
		if (card.equals("Co")) {
			return this.Co;
		}
		if (card.equals("Lo")) {
			return this.Lo;
		}
		if (card.equals("Mc")) {
			return this.Mc;
		}
		if (card.equals("Ma")) {
			return this.Ma;
		}
		if (card.equals("No")) {
			return this.No;
		}
		if (card.equals("Ga")) {
			return this.Ga;
		}
		if (card.equals("Su")) {
			return this.Su;
		}
		if (card.equals("Sh")) {
			return this.Sh;
		}
		if (card.equals("Sa")) {
			return this.Sa;
		}
		if (card.equals("cash")) {
			return this.cash;
		}
		else {
			return 0;
		}
	}
	
	public void printBalance() {
		System.out.println("$"+this.iT+" iT");
	
		System.out.println("$"+this.Am+" Am");
	
		System.out.println("$"+this.Wa+" Wa");
	
		System.out.println("$"+this.Gi+" Gi");
	
		System.out.println("$"+this.Ta+" Ta");
	
		System.out.println("$"+this.Be+" Be");
	
		System.out.println("$"+this.St+" St");
	
		System.out.println("$"+this.eB+" eB");
	
		System.out.println("$"+this.Ap+" Ap");
	
		System.out.println("$"+this.Ho+" Ho");
	
		System.out.println("$"+this.Kr+" Kr");
	
		System.out.println("$"+this.Ik+" Ik");
	
		System.out.println("$"+this.Co+" Co");
	
		System.out.println("$"+this.Lo+" Lo");
	
		System.out.println("$"+this.Mc+" Mc");
	
		System.out.println("$"+this.Ma+" Ma");
	
		System.out.println("$"+this.No+" No");
	
		System.out.println("$"+this.Ga+" Ga");
	
		System.out.println("$"+this.Su+" Su");
	
		System.out.println("$"+this.Sh+" Sh");
	
		System.out.println("$"+this.Sa+" Sa");	

	}
	
	public void increment(String card, int amount) {
		if (card.equals("iT")) {
			this.iT = this.iT + amount;
		}
		if (card.equals("Am")) {
			this.Am = this.Am + amount;
		}
		if (card.equals("Wa")) {
			this.Wa = this.Wa + amount;
		}
		if (card.equals("Gi")) {
			this.Gi = this.Gi + amount;
		}
		if (card.equals("Ta")) {
			this.Ta = this.Ta + amount;
		}
		if (card.equals("Be")) {
			this.Be = this.Be + amount;
		}
		if (card.equals("St")) {
			this.St = this.St + amount;
		}
		if (card.equals("eB")) {
			this.eB = this.eB + amount;
		}
		if (card.equals("Ap")) {
			this.Ap = this.Ap + amount;
		}
		if (card.equals("Ho")) {
			this.Ho = this.Ho + amount;
		}
		if (card.equals("Kr")) {
			this.Kr = this.Kr + amount;
		}
		if (card.equals("Ik")) {
			this.Ik = this.Ik + amount;
		}
		if (card.equals("Co")) {
			this.Co = this.Co + amount;
		}
		if (card.equals("Lo")) {
			this.Lo = this.Lo + amount;
		}
		if (card.equals("Mc")) {
			this.Mc = this.Mc + amount;
		}
		if (card.equals("Ma")) {
			this.Ma = this.Ma + amount;
		}
		if (card.equals("No")) {
			this.No = this.No + amount;
		}
		if (card.equals("Ga")) {
			this.Ga = this.Ga + amount;
		}
		if (card.equals("Su")) {
			this.Su = this.Su + amount;
		}
		if (card.equals("Sh")) {
			this.Sh = this.Sh + amount;
		}
		if (card.equals("Sa")) {
			this.Sa = this.Sa + amount;
		}
		if (card.equals("cash")) {
			this.cash = this.cash + amount;
		}
	}
	
	public void decrement(String card, int amount) {
		if (card.equals("iT")) {
			this.iT = this.iT - amount;
		}
		if (card.equals("Am")) {
			this.Am = this.Am - amount;
		}
		if (card.equals("Wa")) {
			this.Wa = this.Wa - amount;
		}
		if (card.equals("Gi")) {
			this.Gi = this.Gi - amount;
		}
		if (card.equals("Ta")) {
			this.Ta = this.Ta - amount;
		}
		if (card.equals("Be")) {
			this.Be = this.Be - amount;
		}
		if (card.equals("St")) {
			this.St = this.St - amount;
		}
		if (card.equals("eB")) {
			this.eB = this.eB - amount;
		}
		if (card.equals("Ap")) {
			this.Ap = this.Ap - amount;
		}
		if (card.equals("Ho")) {
			this.Ho = this.Ho - amount;
		}
		if (card.equals("Kr")) {
			this.Kr = this.Kr - amount;
		}
		if (card.equals("Ik")) {
			this.Ik = this.Ik - amount;
		}
		if (card.equals("Co")) {
			this.Co = this.Co - amount;
		}
		if (card.equals("Lo")) {
			this.Lo = this.Lo - amount;
		}
		if (card.equals("Mc")) {
			this.Mc = this.Mc - amount;
		}
		if (card.equals("Ma")) {
			this.Ma = this.Ma - amount;
		}
		if (card.equals("No")) {
			this.No = this.No - amount;
		}
		if (card.equals("Ga")) {
			this.Ga = this.Ga - amount;
		}
		if (card.equals("Su")) {
			this.Su = this.Su - amount;
		}
		if (card.equals("Sh")) {
			this.Sh = this.Sh - amount;
		}
		if (card.equals("Sa")) {
			this.Sa = this.Sa - amount;
		}
		if (card.equals("cash")) {
			this.cash = this.cash - amount;
		}
	}
}
