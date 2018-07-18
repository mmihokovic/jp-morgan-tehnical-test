package jpmorgan.exercise.reportTool.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

import jpmorgan.exercise.reportTool.main.CurrencyEnum;
import jpmorgan.exercise.reportTool.main.Instruction;
import jpmorgan.exercise.reportTool.main.TradeEnum;

class InstructionTest {

	@Test
	void testGetTotalValueInUSD() {
		Instruction instruction1 = new Instruction("bar", TradeEnum.SELL, 0.22, CurrencyEnum.AED, 
				LocalDate.of(2016, Month.JANUARY, 8), LocalDate.of(2016, Month.JANUARY, 10), 450, 153.5);
		
		assertEquals(15196.5, instruction1.getTotalValueInUSD());
		
		Instruction instruction2 = new Instruction("bar", TradeEnum.SELL, 0.22, CurrencyEnum.USD, 
				LocalDate.of(2016, Month.JANUARY, 8), LocalDate.of(2016, Month.JANUARY, 10), 450, 153.5);
		
		assertEquals(69075, instruction2.getTotalValueInUSD());
	}

	@Test
	void testGetCorrectedSettlementDate() {
		Instruction instruction1 = new Instruction("bar", TradeEnum.SELL, 0.22, CurrencyEnum.AED, 
				LocalDate.of(2018, Month.JULY, 20), LocalDate.of(2018, Month.JULY, 20), 450, 153.5);
		
		assertEquals(LocalDate.of(2018, Month.JULY, 22), instruction1.getCorrectedSettlementDate());
		
		Instruction instruction2 = new Instruction("bar", TradeEnum.SELL, 0.22, CurrencyEnum.USD, 
				LocalDate.of(2018, Month.JULY, 20), LocalDate.of(2018, Month.JULY, 21), 450, 153.5);
		
		assertEquals(LocalDate.of(2018, Month.JULY, 23), instruction2.getCorrectedSettlementDate());
	}

}
