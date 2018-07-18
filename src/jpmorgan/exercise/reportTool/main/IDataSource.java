package jpmorgan.exercise.reportTool.main;

import java.util.List;

/**
 * Interface for defying memory source
 * @author markomiho
 *
 */
public interface IDataSource {
	public List<Instruction> getAllMarketInstructions();
}
