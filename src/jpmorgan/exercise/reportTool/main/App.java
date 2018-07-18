package jpmorgan.exercise.reportTool.main;

import java.util.List;

public class App {

	public static void main(String[] args) {
		InMemoryDataSource dataSource = new InMemoryDataSource();
		List<Instruction> instructions = dataSource.getAllMarketInstructions();
		TradeInstructionsReportMaker reportMaker = new TradeInstructionsReportMaker(instructions);
		reportMaker.printReportToConsole();
	}

}
