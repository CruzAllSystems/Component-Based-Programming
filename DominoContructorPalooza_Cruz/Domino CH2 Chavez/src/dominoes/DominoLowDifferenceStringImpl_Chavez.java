package dominoes;
import java.util.*;

public class DominoLowDifferenceStringImpl_Chavez implements Domino
{
	private String lowDifferenceString;
	private static final char LOW_DIFFERENCE_DELIMITER = '*';
	
	//pre: isLowPlus8TimesHighInteger(lowPlus8TimesHighInteger) = true
	//post: Sets the instance lowDifference String according to the given parameter
	public DominoLowDifferenceStringImpl_Chavez(int lowPlus8TimesHigh)
	{
		assert DominoHighLowSetImpl_Chavez.isLowPlus8TimesHighInteger(lowPlus8TimesHigh); //Assert statement to
																				//ensure preconditions are true
		
		int lowPipCount = lowPlus8TimesHigh % 8;
		int highPipCount = lowPlus8TimesHigh / 8;
		
		//Assert statements to assure pipCounts are valid
		assert highPipCount >= Domino.MINIMUM_PIP_COUNT && highPipCount <= Domino.MAXIMUM_PIP_COUNT;
		assert lowPipCount >= Domino.MINIMUM_PIP_COUNT && lowPipCount <= Domino.MAXIMUM_PIP_COUNT;
		
		int difference = highPipCount - lowPipCount;
		
		lowDifferenceString = lowPipCount + "*" + difference;
		
	}//end of the lowPlus8TimesHigh constructor
	
	public static final int INDEX_OF_HIGH = 0;
	public static final int INDEX_OF_SUM = 1;
	
	//pre: highSum.size() == 2, highSum.get(INDEX_OF_SUM) > highSum.get(INDEX_OF_HIGH), Sum and high produce
	//valid pip counts
	//post: Sets the instance lowDifference String according to the given parameter
	public DominoLowDifferenceStringImpl_Chavez(List<Integer> highSum)
	{
		assert highSum.size() == 2; //Assert statements to check that preconditions are true.
		assert highSum.get(INDEX_OF_SUM) > highSum.get(INDEX_OF_HIGH);
		
		int highPipCount = highSum.get(INDEX_OF_HIGH);
		int sum = highSum.get(INDEX_OF_SUM);  //Evalution of all the neccessary domino values
		int lowPipCount = sum - highPipCount;
		
		//Asserts that make sure all pipcount values are valid
		assert highPipCount >= Domino.MINIMUM_PIP_COUNT && highPipCount <= Domino.MAXIMUM_PIP_COUNT;
		assert lowPipCount >= Domino.MINIMUM_PIP_COUNT && lowPipCount <= Domino.MAXIMUM_PIP_COUNT;
		
		int difference = highPipCount - lowPipCount;
		
		lowDifferenceString = lowPipCount + "*" + difference;
		
	}//end of the highSum list constructor

	//Getter methods
	//pre: none
	//post: rv = instance variable of the current domino instance
	public int getHighPipCount() 
	{
		int lowPipCount = Integer.parseInt(lowDifferenceString.substring(0, 1));
		int difference = Integer.parseInt(lowDifferenceString.substring(2));
		int highPipCount = lowPipCount + difference;
		
		return highPipCount;
	}

	
	public int getLowPipCount() 
	{
		int lowPipCount = Integer.parseInt(lowDifferenceString.substring(0, 1));
		return lowPipCount;
	}
	
	//pre: none
	//post: Succinctly prints the domino's properties/pipcount in a single line.
	public String toString()
	{
		
		return "This is the " + this.getHighPipCount() + "," + this.getLowPipCount() + " Domino."; 
		
	}//end of the toString method

}//end of the DominoLowDifferenceStringImpl_Chavez class
