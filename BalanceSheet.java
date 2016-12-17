package tester;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.cycle.TiernanSimpleCycles;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class BalanceSheet {

	public static void main(String[] args) {
		Map<String,Map<String, Integer>> edgeWeights = new HashMap<String, Map<String, Integer>>();
		
		// Create the graph with the 21 vertices (first two letters of name of store)
		DirectedGraph<String,DefaultEdge> graph = 
				new DefaultDirectedGraph<String,DefaultEdge>(DefaultEdge.class);
		graph.addVertex("iT");
		graph.addVertex("Am");
		graph.addVertex("Wa");
		graph.addVertex("Gi");
		graph.addVertex("Ta");
		graph.addVertex("Be");
		graph.addVertex("St");
		graph.addVertex("eB");
		graph.addVertex("Ap");
		graph.addVertex("Ho");
		graph.addVertex("Kr");
		graph.addVertex("Ik");
		graph.addVertex("Co");
		graph.addVertex("Lo");
		graph.addVertex("Mc");
		graph.addVertex("Ma");
		graph.addVertex("No");
		graph.addVertex("Ga");
		graph.addVertex("Su");
		graph.addVertex("Sh");
		graph.addVertex("Sa");
		
		List<String> cards = new ArrayList<String>();
		cards.add("iT");
		cards.add("Am");
		cards.add("Wa");
		cards.add("Gi");
		cards.add("Ta");
		cards.add("Be");
		cards.add("St");
		cards.add("eB");
		cards.add("Ap");
		cards.add("Ho");
		cards.add("Kr");
		cards.add("Ik");
		cards.add("Co");
		cards.add("Lo");
		cards.add("Mc");
		cards.add("Ma");
		cards.add("No");
		cards.add("Ga");
		cards.add("Su");
		cards.add("Sh");
		cards.add("Sa");
		Map<String,Set<User>> unclearedByCard = new TreeMap<String,Set<User>>();
		Set<User> uncleared = new TreeSet<User>();
		Balances balance = new Balances(1500000);
		double totalTime = 0;
		long cardValueCleared = 0;
		int customersCleared = 0;
		long totalWaitTime = 0;
		long addedCash = 0;
		long oldClearedValue = 0;
		//start the simulation
		int NUM_USERS = 1000000;
		double addedBack = 0.01;
		for(int i = 0; i < NUM_USERS; i++) {
			if(cardValueCleared - oldClearedValue > 5000) {
				double added = (cardValueCleared - oldClearedValue) * addedBack;
				int toAdd = (int) Math.round(added);
				addedCash += toAdd;
				oldClearedValue = cardValueCleared;
				balance.increment("cash", toAdd);
			}
			String cardCleared = "";
			if(i % 10000 == 0) {
				System.out.println(i);
			}
			//update time to simulate a wait
			totalTime = totalTime + getWait();
			String cardHad = getCardOne();
			String cardWanted = getCardTwo(cardHad);
			int cardValue = getValue();
			
			User newUser = new User(cardHad, cardWanted, cardValue, totalTime);
			//check if we have it on our balance sheet
			if(balance.getBalance(cardWanted) >= cardValue) {
				cardValueCleared+= cardValue;
				customersCleared++;
				balance.increment(cardHad, cardValue);
				balance.decrement(cardWanted, cardValue);
				cardCleared = cardHad;
			}
			//check if we have enough cash to make the market
			else if(balance.getBalance("cash") >= cardValue) {
				cardValueCleared+= cardValue;
				customersCleared++;
				balance.increment(cardHad, cardValue);
				balance.decrement("cash", cardValue);
				cardCleared = cardHad;
			}
			//otherwise we cannot clear the customer
			else {
				uncleared.add(newUser);
				Set<User> unclearedSet = unclearedByCard.get(cardWanted);
				if(unclearedSet == null) {
					Set<User> newSet = new TreeSet<User>();
					newSet.add(newUser);
					unclearedByCard.put(cardWanted, newSet);
				}
				else {
					uncleared.add(newUser);
					unclearedByCard.put(cardWanted, uncleared);
				}
			}
			
		
				
			Collections.shuffle(cards);
			for(String s : cards){
				if(balance.getBalance(s) == 0) {
					break;
				}
				
				//see if we can clear any users left
				Set<User> usersCleared = new TreeSet<User>();
				if(unclearedByCard.get(s) != null) {
					for (User u : unclearedByCard.get(s)) {
						if(balance.getBalance(u.getCardWanted()) >= u.getValue()) {
							cardValueCleared+= u.getValue();
							customersCleared++;
							balance.increment(u.getOwnCard(), u.getValue());
							balance.decrement(u.getCardWanted(), u.getValue());
							totalWaitTime += (totalTime - u.getStartTime());
							usersCleared.add(u);
						}
					}
					Set<User> oldUsers = unclearedByCard.get(s);
					oldUsers.removeAll(usersCleared);
					unclearedByCard.put(s,oldUsers);
				}
			}
			
			
		}
		

		System.out.println("for " + NUM_USERS + " users");
		System.out.println("for adding back " + addedBack + "percent of cash gained");
		System.out.println("card value cleared was " + cardValueCleared);
		System.out.println("number of customers cleared was " + customersCleared);
		System.out.println("total wait time was " + totalWaitTime);
		System.out.println("cash balance remaining was " + balance.getBalance("cash"));
		System.out.println("Added cash was " + addedCash);
		
			
		
	}

	static double getWait() {
		Random newRandom = new Random();
		//get a random normal distribution mean 0 std 1
		double rand = newRandom.nextGaussian();
		//move mean to 5
		rand = rand  + 5;
		return rand;
	}
	
	static Integer getValue() {
		double rand = Math.random();
		if (rand < 0.25) {
			return 25;
		}
		else if (rand < 0.8) {
			return 50;
		}
		else {
			return 100;
		}
	}
	
	
	static String getCardOne() {
		//generate random number
		double rand = Math.random();
		if (rand < 0.19525066) {
			return "iT";
		}
		if (rand < 0.354441513) {
			return "Am";
		}
		if (rand < 0.460861917) {
			return "Wa";
		}
		if (rand < 0.532102023) {
			return "Gi";
		}
		if (rand < 0.590149516) {
			return "Ta";
		}
		if (rand < 0.64819701) {
			return "Be";
		}
		if (rand < 0.706244503) {
			return "St";
		}
		if (rand < 0.753737907) {
			return "eB";
		}
		if (rand < 0.792436236) {
			return "Ap";
		}
		if (rand < 0.817941953) {
			return "Ho";
		}
		if (rand < 0.839050132) {
			return "Kr";
		}
		if (rand < 0.860158311) {
			return "Ik";
		}
		if (rand < 0.876868953) {
			return "Co";
		}
		if (rand < 0.893579595) {
			return "Lo";
		}
		if (rand < 0.910290237) {
			return "Mc";
		}
		if (rand < 0.92700088) {
			return "Ma";
		}
		if (rand < 0.943711522) {
			return "No";
		}
		if (rand < 0.960422164) {
			return "Ga";
		}
		if (rand < 0.974494283) {
			return "Su";
		}
		if (rand < 0.988566403) {
			return "Sh";
		}
		if (rand < 1) {
			return "Sa";
		}
		//shouldn't get here
		return null;
	}
	
	static String getCardTwo(String cardOne) {
		//generate random number
		while(true) {
			double rand = Math.random();
			if (rand < 0.19525066) {
				if(cardOne.equals("iT")){
					//need new card
					continue;
				}
				else {
					return "iT";
				}
			}
			if (rand < 0.354441513) {
				if(cardOne.equals("Am")){
					//need new card
					continue;
				}
				else {
					return "Am";
				}
			}
			if (rand < 0.460861917) {
				if(cardOne.equals("Wa")){
					//need new card
					continue;
				}
				else {
					return "Wa";
				}
			}
			if (rand < 0.532102023) {
				if(cardOne.equals("Gi")){
					//need new card
					continue;
				}
				else {
					return "Gi";
				}
			}
			if (rand < 0.590149516) {
				if(cardOne.equals("Ta")){
					//need new card
					continue;
				}
				else {
					return "Ta";
				}
			}
			if (rand < 0.64819701) {
				if(cardOne.equals("Be")){
					//need new card
					continue;
				}
				else {
					return "Be";
				}
			}
			if (rand < 0.706244503) {
				if(cardOne.equals("St")){
					//need new card
					continue;
				}
				else {
					return "St";
				}
			}
			if (rand < 0.753737907) {
				if(cardOne.equals("eB")){
					//need new card
					continue;
				}
				else {
					return "eB";
				}
			}
			if (rand < 0.792436236) {
				if(cardOne.equals("Ap")){
					//need new card
					continue;
				}
				else {
					return "Ap";
				}
			}
			if (rand < 0.817941953) {
				if(cardOne.equals("Ho")){
					//need new card
					continue;
				}
				else {
					return "Ho";
				}
			}
			if (rand < 0.839050132) {
				if(cardOne.equals("Kr")){
					//need new card
					continue;
				}
				else {
					return "Kr";
				}
			}
			if (rand < 0.860158311) {
				if(cardOne.equals("Ik")){
					//need new card
					continue;
				}
				else {
					return "Ik";
				}
			}
			if (rand < 0.876868953) {
				if(cardOne.equals("Co")){
					//need new card
					continue;
				}
				else {
					return "Co";
				}
			}
			if (rand < 0.893579595) {
				if(cardOne.equals("Lo")){
					//need new card
					continue;
				}
				else {
					return "Lo";
				}
			}
			if (rand < 0.910290237) {
				if(cardOne.equals("Mc")){
					//need new card
					continue;
				}
				else {
					return "Mc";
				}
			}
			if (rand < 0.92700088) {
				if(cardOne.equals("Ma")){
					//need new card
					continue;
				}
				else {
					return "Ma";
				}
			}
			if (rand < 0.943711522) {
				if(cardOne.equals("No")){
					//need new card
					continue;
				}
				else {
					return "No";
				}
			}
			if (rand < 0.960422164) {
				if(cardOne.equals("Ga")){
					//need new card
					continue;
				}
				else {
					return "Ga";
				}
			}
			if (rand < 0.974494283) {
				if(cardOne.equals("Su")){
					//need new card
					continue;
				}
				else {
					return "Su";
				}
			}
			if (rand < 0.988566403) {
				if(cardOne.equals("Sh")){
					//need new card
					continue;
				}
				else {
					return "Sh";
				}
			}
			if (rand < 1) {
				if(cardOne.equals("Sa")){
					//need new card
					continue;
				}
				else {
					return "Sa";
				}
			}
		}
	}


}
