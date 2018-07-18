package jpmorgan.exercise.reportTool.main;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple in-memory data source
 * @author markomiho
 *
 */
public class InMemoryDataSource implements IDataSource {
	private static List<Instruction> marketInstructions;
	
	public InMemoryDataSource()
	{
		if(marketInstructions == null)
		{
			marketInstructions = generateData();
		}
	}
	
	/**
	 * generates sample data
	 * @return
	 */
	private List<Instruction> generateData() {
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
				LocalDate.of(2016, Month.JANUARY, 4), LocalDate.of(2016, Month.JANUARY, 5), 200, 103.25));
		sampleData.add(new Instruction("foo", TradeEnum.SELL, 0.50, CurrencyEnum.SGP, 
				LocalDate.of(2016, Month.JANUARY, 4), LocalDate.of(2016, Month.JANUARY, 6), 200, 103.25));
		
		
		sampleData.add(new Instruction("bar", TradeEnum.SELL, 0.22, CurrencyEnum.AED, 
				LocalDate.of(2016, Month.JANUARY, 5), LocalDate.of(2016, Month.JANUARY, 7), 450, 150.5));
		
		sampleData.add(new Instruction("bar", TradeEnum.SELL, 0.22, CurrencyEnum.AED, 
				LocalDate.of(2016, Month.JANUARY, 6), LocalDate.of(2016, Month.JANUARY, 8), 450, 151.5));
		
		sampleData.add(new Instruction("bar", TradeEnum.SELL, 0.22, CurrencyEnum.AED, 
				LocalDate.of(2016, Month.JANUARY, 7), LocalDate.of(2016, Month.JANUARY, 9), 450, 152.5));
		
		sampleData.add(new Instruction("bar", TradeEnum.SELL, 0.22, CurrencyEnum.AED, 
				LocalDate.of(2016, Month.JANUARY, 8), LocalDate.of(2016, Month.JANUARY, 10), 450, 153.5));
		
		sampleData.add(new Instruction("bar", TradeEnum.SELL, 0.22, CurrencyEnum.AED, 
				LocalDate.of(2016, Month.JANUARY, 9), LocalDate.of(2016, Month.JANUARY, 11), 450, 154.5));
		
		sampleData.add(new Instruction("Crodux", TradeEnum.SELL, 1, CurrencyEnum.USD, 
				LocalDate.of(2016, Month.JANUARY, 5), LocalDate.of(2016, Month.JANUARY, 7), 450, 150.5));
		
		sampleData.add(new Instruction("Crodux", TradeEnum.BUY, 1, CurrencyEnum.USD, 
				LocalDate.of(2016, Month.JANUARY, 5), LocalDate.of(2016, Month.JANUARY, 8), 450, 150.5));
		
		sampleData.add(new Instruction("Crodux", TradeEnum.BUY, 1, CurrencyEnum.USD, 
				LocalDate.of(2016, Month.JANUARY, 5), LocalDate.of(2016, Month.JANUARY, 8), 450, 150.5));
		
		sampleData.add(new Instruction("Crodux", TradeEnum.SELL, 1, CurrencyEnum.USD, 
				LocalDate.of(2016, Month.JANUARY, 5), LocalDate.of(2016, Month.JANUARY, 3), 450, 150.5));
		
		return sampleData;
	}

	@Override
	public List<Instruction> getAllMarketInstructions() {		
		return marketInstructions;
	}
	
}
