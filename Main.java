package tester;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.*;
import org.jgrapht.alg.cycle.HawickJamesSimpleCycles;
import org.jgrapht.alg.cycle.TiernanSimpleCycles;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
public class Main {

	
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

		
		//generate the edges
		for(int i = 0; i < 50; i++) {
			String cardOne = getCardOne();
			String cardTwo = getCardTwo(cardOne);
			DefaultEdge e = graph.getEdge(cardOne, cardTwo);
			if(e == null) {
				//we need to add the edge
				graph.addEdge(cardOne, cardTwo);
				Map<String, Integer> innerMap = new TreeMap<String, Integer>();
				innerMap.put(cardTwo, 1);
				if(edgeWeights.get(cardOne) != null) {
					Map<String,Integer> interMap = edgeWeights.get(cardOne);
					interMap.put(cardTwo, 1);
				}
				else {
					edgeWeights.put(cardOne, innerMap);
				}
			}
			else {
				//we need to increment the edge weight
				//System.out.println("removing edge " + cardOne + cardTwo);
				
				int oldWeight = edgeWeights.get(cardOne).get(cardTwo);
				Map<String,Integer> interMap = edgeWeights.get(cardOne);
				interMap.put(cardTwo, 1 + oldWeight);
				//edgeWeights.get(cardOne).put(cardTwo, 1 + oldWeight);
			}
		}
		
	
				
		TiernanSimpleCycles<String,DefaultEdge> cD = new TiernanSimpleCycles<String,DefaultEdge>(graph);
	
		List<List<String>> listofCycles = new ArrayList<List<String>>();
		
		
		//default case, find only cycles of length two
		int removedCards = 0;
		System.out.println("we are here");
		
		listofCycles = cD.findSimpleCycles();
		System.out.println("we are past the first part");
		while(!listofCycles.isEmpty()) {
			System.out.println("new cycle search");
			List<String> longest = null;
			int length = 0;
			for(List<String> ls : listofCycles) {
				if(ls.size() > length) {
					longest = ls;
					length = ls.size();
				}
			}
			
			for(int i = 0; i < (longest.size() - 1); i++) {
				String removeCardOne = longest.get(i);
				String removeCardTwo = longest.get(i+1);
				DefaultEdge removeE = graph.getEdge(removeCardOne, removeCardTwo);
				int removeEdgeWeight = edgeWeights.get(removeCardOne).get(removeCardTwo);
				if(removeEdgeWeight > 1) {
					
					Map<String, Integer> interMapRemove = edgeWeights.get(removeCardOne);
					interMapRemove.put(removeCardTwo, removeEdgeWeight - 1);
					
				}
				else {
					graph.removeEdge(removeE);
					
				}
				
				removedCards++;
			}
			String removeCardOne = longest.get(longest.size()-1);
			String removeCardTwo = longest.get(0);
			DefaultEdge removeE = graph.getEdge(removeCardOne, removeCardTwo);
			int removeEdgeWeight = edgeWeights.get(removeCardOne).get(removeCardTwo);
			if(removeEdgeWeight > 1) {
				
				Map<String, Integer> interMapRemove = edgeWeights.get(removeCardOne);
				interMapRemove.put(removeCardTwo, removeEdgeWeight - 1);
				
			}
			else {
				graph.removeEdge(removeE);
				
			}
			
			removedCards++;
			
			listofCycles = cD.findSimpleCycles();
			
			
			//either decrement or remove
			
			
		}
		
		System.out.println("removecards was " + removedCards);
		
		
		
		
		
		//default case, find only cycles of length two
		/*
		int removedCards = 0;
		//System.out.println("we are here");
		
		listofCycles = cD.findSimpleCycles();
		//System.out.println("we are past the first part");
		while(!listofCycles.isEmpty()) {
			//System.out.println("new cycle search");
			List<String> cycle = listofCycles.remove(0);
			while(cycle.size() != 2) {
				if(listofCycles.isEmpty()) {
					System.out.println("Number of removed cards was: " + removedCards);
					System.exit(1);
				}
				else {
					cycle = listofCycles.remove(0);
					//System.out.println(cycle);
				}
			}
			
			String removeCardOne = cycle.remove(0);
			
			String removeCardTwo = cycle.remove(0);
			//System.out.println(removeCardOne + removeCardTwo);
			DefaultEdge removeE = graph.getEdge(removeCardOne, removeCardTwo);
			DefaultEdge removeE2 = graph.getEdge(removeCardTwo, removeCardOne);
			//either decrement or remove
			//System.out.println("removeCardOne is" + removeCardOne);
			//System.out.println("removeCardTwo is " +removeCardTwo);
			if(edgeWeights.get(removeCardOne).isEmpty()) {
				//System.out.println("first part is null");
			}
			
			int removeEdgeWeight = edgeWeights.get(removeCardOne).get(removeCardTwo);
			int removeEdgeWeight2 = edgeWeights.get(removeCardTwo).get(removeCardOne);
			if(removeEdgeWeight > 1) {
				
				Map<String, Integer> interMapRemove = edgeWeights.get(removeCardOne);
				interMapRemove.put(removeCardTwo, removeEdgeWeight - 1);
				
				Map<String, Integer> interMapRemove2 = edgeWeights.get(removeCardTwo);
				interMapRemove2.put(removeCardOne, removeEdgeWeight2 - 1);
			}
			else {
				graph.removeEdge(removeE);
				graph.removeEdge(removeE2);
			}
			listofCycles = cD.findSimpleCycles();
			removedCards++;
			removedCards++;
		}
		*/
		
		
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
