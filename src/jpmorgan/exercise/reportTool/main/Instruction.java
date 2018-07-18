package jpmorgan.exercise.reportTool.main;

import java.time.LocalDate;

/**
 * Class for storing trade instructions and normalizing settlement date and total value in USD.
 * @author markomiho
 *
 */
public class Instruction {
	private String entity;
	private TradeEnum tradeInstructuion;
	private double agreedFx;
	private CurrencyEnum currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private int units;
	private double pricePerUnit;
	private double totalValueInUSD;
	private LocalDate correctedSettlementDate;
	
	public Instruction(String entity, TradeEnum tradeInstructuion, double agreedFx,
			CurrencyEnum currency, LocalDate instructionDate, LocalDate settlementDate, int units, double pricePerUnit) {
		super();
		this.entity = entity;
		this.tradeInstructuion = tradeInstructuion;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
		
		this.totalValueInUSD = calculateTotalValueInUSD(agreedFx, currency, units, pricePerUnit);
		this.correctedSettlementDate = correctSettlementDate(settlementDate, currency);
	}

	private static double calculateTotalValueInUSD(double agreedFx, CurrencyEnum currency, int units, double pricePerUnit) {
		return (currency != CurrencyEnum.USD ? agreedFx : 1) * units * pricePerUnit;
	}	
	
	private LocalDate correctSettlementDate(LocalDate settlementDate, CurrencyEnum currency) {
		if(currency == CurrencyEnum.AED || currency == CurrencyEnum.SGP) {
			switch(settlementDate.getDayOfWeek()) {
	        case FRIDAY:
	        	correctedSettlementDate = settlementDate.plusDays(2);
	        	break;
	        case SATURDAY:
	        	correctedSettlementDate = settlementDate.plusDays(1);
	        	break;
	        default:
	        	correctedSettlementDate = settlementDate.plusDays(0);
			}
		}
		else {
			switch(settlementDate.getDayOfWeek()) {
	        case SATURDAY:
	        	correctedSettlementDate = settlementDate.plusDays(2);
	        	break;
	        case SUNDAY:
	        	correctedSettlementDate = settlementDate.plusDays(1);
	        	break;
	        default:
	        	correctedSettlementDate = settlementDate.plusDays(0);
			}
		}
		return correctedSettlementDate;	
	}
	
	public String getEntity() {
		return entity;
	}

	public TradeEnum getTradeInstructuion() {
		return tradeInstructuion;
	}

	public double getAgreedFx() {
		return agreedFx;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public int getUnits() {
		return units;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public double getTotalValueInUSD() {
		return totalValueInUSD;
	}

	public LocalDate getCorrectedSettlementDate() {
		return correctedSettlementDate;
	}
	
}
