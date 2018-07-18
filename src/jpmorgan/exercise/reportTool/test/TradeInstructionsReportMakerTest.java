package jpmorgan.exercise.reportTool.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import jpmorgan.exercise.reportTool.main.CurrencyEnum;
import jpmorgan.exercise.reportTool.main.Instruction;
import jpmorgan.exercise.reportTool.main.TradeEnum;
import jpmorgan.exercise.reportTool.main.TradeInstructionsReportMaker;

class TradeInstructionsReportMakerTest {

	@Test
	void testGetTradesByDate() {
		List<Instruction> sampleData = new ArrayList<Instruction>();
		
		sampleData.add(new Instruction("foo", TradeEnum.BUY, 0.50, CurrencyEnum.SGP, 
						LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200, 100.25));		
		sampleData.add(new Instruction("foo", TradeEnum.BUY, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200, 100.25));
		sampleData.add(new Instruction("foo", TradeEnum.BUY, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY,2), LocalDate.of(2016, Month.JANUARY, 3), 200, 101.25));
		sampleData.add(new Instruction("foo", TradeEnum.BUY, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY, 3), LocalDate.of(2016, Month.JANUARY, 4), 200, 102.25));
		sampleData.add(new Instruction("foo", TradeEnum.SELL, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY, 4), LocalDate.of(2016, Month.JANUARY, 4), 200, 103.25));
		sampleData.add(new Instruction("foo", TradeEnum.SELL, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY, 4), LocalDate.of(2016, Month.JANUARY, 6), 200, 103.25));
		
		TradeInstructionsReportMaker reportMaker = new TradeInstructionsReportMaker(sampleData);
		Map<LocalDate, Double> outgoingTradesByDate = reportMaker.getTradesByDate(TradeEnum.BUY);
		assertEquals(10225, outgoingTradesByDate.get(LocalDate.of(2016, Month.JANUARY, 4)), 0.1);
		
		Map<LocalDate, Double> incomingTradesByDate = reportMaker.getTradesByDate(TradeEnum.SELL);
		assertEquals(10325.0, incomingTradesByDate.get(LocalDate.of(2016, Month.JANUARY, 4)), 0.1);
	}

	@Test
	void testRankEntities() {
		List<Instruction> sampleData = new ArrayList<Instruction>();
		
		sampleData.add(new Instruction("foo", TradeEnum.BUY, 0.50, CurrencyEnum.SGP, 
						LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200, 100.25));		
		sampleData.add(new Instruction("bar", TradeEnum.BUY, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2), 200, 100.25));
		sampleData.add(new Instruction("foo", TradeEnum.BUY, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY,2), LocalDate.of(2016, Month.JANUARY, 3), 200, 101.25));
		sampleData.add(new Instruction("bar", TradeEnum.BUY, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY, 3), LocalDate.of(2016, Month.JANUARY, 4), 200, 102.25));
		sampleData.add(new Instruction("foo", TradeEnum.SELL, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY, 4), LocalDate.of(2016, Month.JANUARY, 4), 200, 103.25));
		sampleData.add(new Instruction("bar", TradeEnum.SELL, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY, 4), LocalDate.of(2016, Month.JANUARY, 6), 200, 103.25));
		
		TradeInstructionsReportMaker reportMaker = new TradeInstructionsReportMaker(sampleData);
		LinkedHashMap<String,Double> rankedIncomingEntities = reportMaker.rankEntities(TradeEnum.SELL);		
		assertEquals("bar", rankedIncomingEntities.entrySet().iterator().next().getKey());
		assertEquals(30575, rankedIncomingEntities.entrySet().iterator().next().getValue(), 0.1);
	}

}
