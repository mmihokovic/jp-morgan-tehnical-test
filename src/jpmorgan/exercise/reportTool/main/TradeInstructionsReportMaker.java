package jpmorgan.exercise.reportTool.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Class for creating a trade instructions report.
 * @author markomiho
 *
 */
public class TradeInstructionsReportMaker {
	private List<Instruction> instructions;
	
	public TradeInstructionsReportMaker(List<Instruction> instructions) 
	{
		this.instructions = instructions;
	}
	
	public Map<LocalDate, Double> getTradesByDate(TradeEnum trade){
		List<LocalDate> dates = instructions.stream().filter(i -> i.getTradeInstructuion() == trade)
				.map(i -> i.getCorrectedSettlementDate()).distinct().sorted().collect(Collectors.toList());
		
		Map<LocalDate, Double> tradesByDate = new HashMap<LocalDate, Double>();
		
		dates.forEach(d -> {
			double dateTrade = instructions.stream().filter(i -> i.getCorrectedSettlementDate() == d)
					.mapToDouble(v -> v.getTotalValueInUSD()).sum();
			tradesByDate.put(d, dateTrade);
		});
		
		return tradesByDate;
	}
	
	/**
	 * Rank entities by trade value. It can be used for incoming and outgoing trades.
	 * @param trade Trade type, incoming or outgoing
	 * @return ranked entities by value
	 */
	public LinkedHashMap<String, Double> rankEntities(TradeEnum trade){
		List<String> entityNames = instructions.stream().filter(i -> i.getTradeInstructuion() == trade)
				.map(i -> i.getEntity()).distinct().collect(Collectors.toList());
		
		Map<String, Double> entityTrades = new HashMap<String, Double>();
		
		entityNames.forEach(e -> {
			double entityTrade = instructions.stream().filter(i -> i.getEntity() == e)
					.mapToDouble(v -> v.getTotalValueInUSD()).sum();
			entityTrades.put(e, entityTrade);
		});
		
		LinkedHashMap<String,Double> rankedEntites = entityTrades.entrySet().stream().sorted((e1,e2)->
        Double.compare(e2.getValue(), e1.getValue()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new));
		
		return rankedEntites;
	}
	
	/**
	 * Prints incoming and outgoing trades by date to console
	 */
	private void printTradesByDateToConsole() {
		Map<LocalDate, Double> incomingTradesByDate = getTradesByDate(TradeEnum.SELL);
		if(!incomingTradesByDate.isEmpty()) {
			System.out.println("Incoming trades by date:");
			incomingTradesByDate.forEach((d, v) -> System.out.println("Date: " + d + " Amount: " + v));
		}
		else {
			System.out.println("There are no incoming trades.");
		}
		
		
		Map<LocalDate, Double> outgoingTradesByDate = getTradesByDate(TradeEnum.BUY);
		if(!outgoingTradesByDate.isEmpty()) {
			System.out.println("Outgoing trades by date:");
			outgoingTradesByDate.forEach((d, v) -> System.out.println("Date: " + d + " Amount: " + v));
		}
		else {
			System.out.println("There are no outgoing trades.");
		}
	}
	
	/**
	 * Prints incoming and outgoing ranking entities to console
	 */
	private void printRankingEntitesToConsole() {
		LinkedHashMap<String,Double> rankedIncomingEntities = rankEntities(TradeEnum.SELL);
		if(!rankedIncomingEntities.isEmpty()) {
			System.out.println("Incoming trades ranking:");
			AtomicInteger incomingRank = new AtomicInteger(1);
			rankedIncomingEntities.forEach((e, v) -> System.out.println(incomingRank.getAndIncrement() + ". Entity: " + e + " Amount: " + v));
		}
		else {
			System.out.println("There are no incoming trades for ranking.");
		}
		
		LinkedHashMap<String,Double> rankedOutgoingEntities = rankEntities(TradeEnum.BUY);
		if(!rankedOutgoingEntities.isEmpty()) {
			System.out.println("Outgoing trades ranking:");
			AtomicInteger  outgoingRank = new AtomicInteger(1);
			rankedOutgoingEntities.forEach((e, v) -> System.out.println(outgoingRank.getAndIncrement() + ". Entity: " + e + " Amount: " + v));
		}
		else {
			System.out.println("There are no outgoing trades.");
		}
	}
	
	/**
	 * Prints report to console
	 */
	public void printReportToConsole() {
		printTradesByDateToConsole();
		System.out.println();
		printRankingEntitesToConsole();
		
	}
}
