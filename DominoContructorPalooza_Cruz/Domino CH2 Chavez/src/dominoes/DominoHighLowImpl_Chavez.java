package dominoes;
import java.util.*;

public class DominoHighLowImpl_Chavez implements Domino
{
	private int highPipCount;
	private int lowPipCount;
	
	//pre: MINIMUM_PIP_COUNT <= highPipCount <= MAXIMUM_PIP_COUNT
	//MINIMUM_PIP_COUNT <= lowPipCount <= MAXIMUM_PIP_COUNT
	//highPipCount >= lowPipCount
	//post: Sets instance variables equal to the given parameters
	public DominoHighLowImpl_Chavez(int highPipCount, int lowPipCount)
	{
		assert highPipCount >= lowPipCount; //Assert statements to check that preconditions are true.
		assert highPipCount >= Domino.MINIMUM_PIP_COUNT && highPipCount <= Domino.MAXIMUM_PIP_COUNT;
		assert lowPipCount >= Domino.MINIMUM_PIP_COUNT && lowPipCount <= Domino.MAXIMUM_PIP_COUNT;
		
		this.highPipCount = highPipCount;
		this.lowPipCount = lowPipCount;
		
	}//end of the high and low pip count constructor
	
	public static final char HIGH_LOW_STRING_SEPARATOR = ':';
	
	//pre: none
	//post: rv = true <==> str.length == 3 && the correct separator is used && the high >= low
	public static boolean isHighLowString(String str)
	{
		char delimeterCandidate = str.charAt(1);
		String highPipCountCandidate = str.substring(0,1);
		String lowPipCountCanditate = str.substring(2);
		
		return (str.length() == 3 && delimeterCandidate == HIGH_LOW_STRING_SEPARATOR && Integer.parseInt(highPipCountCandidate) >= Integer.parseInt(lowPipCountCanditate));
		
	}//end of the isHighLowString method
	
	//pre: isHighLowString(highLowString) = true, pipCounts of highLow String are valid
	//post: Sets instance variables according to the given parameter
	public DominoHighLowImpl_Chavez(String highLowString)
	{
		assert isHighLowString(highLowString); //Assert statements to check if pre-conditions are true
		assert Integer.parseInt(highLowString.substring(0,1)) >= Domino.MINIMUM_PIP_COUNT;
		assert Integer.parseInt(highLowString.substring(0,1)) <= Domino.MAXIMUM_PIP_COUNT;
		assert Integer.parseInt(highLowString.substring(2)) >= Domino.MINIMUM_PIP_COUNT;
		assert Integer.parseInt(highLowString.substring(2)) <= Domino.MAXIMUM_PIP_COUNT;
		
		highPipCount = Integer.parseInt(highLowString.substring(0,1));
		lowPipCount = Integer.parseInt(highLowString.substring(2));
		
	}//end of the highLowString domino constructor
	
	public static final int INDEX_OF_SUM = 0; //Index indicators for sumDifference []
	public static final int INDEX_OF_DIFFERENCE = 1;
	
	//pre: sumDifference.lengh == 2, sumDifference[INDEX_OF_SUM] >= sumDifference[INDEX_OF_DIFFERENCE]
	//post: Sets instance variables according to the given parameter
	public DominoHighLowImpl_Chavez(int [] sumDifference)
	{
		assert sumDifference.length == 2; //Checks that the preconditions are true.
		assert sumDifference[INDEX_OF_SUM] >= sumDifference[INDEX_OF_DIFFERENCE];
		
		int sum = sumDifference[INDEX_OF_SUM];
		int difference = sumDifference[INDEX_OF_DIFFERENCE];
		
		assert 2 * Domino.MINIMUM_PIP_COUNT <= sum &&  sum <= 2 * Domino.MAXIMUM_PIP_COUNT;  //Assert statements that 
		assert -(Domino.MAXIMUM_PIP_COUNT - Domino.MINIMUM_PIP_COUNT) <= difference; //check sum and difference are
		assert difference <= Domino.MAXIMUM_PIP_COUNT - Domino.MINIMUM_PIP_COUNT; //valid for the algorithim
		
		lowPipCount = (sum - Math.abs(difference)) / 2;
		highPipCount = (sum + Math.abs(difference)) / 2;
		
		
		
	}//end of the sumDifference constructor
	
	//pre: 1 <= highLowSet.size() <= 2, ! highLowSet.contains(null)
	//post: Sets instance variables according to the given parameter
	public DominoHighLowImpl_Chavez(Set<Integer> highLowSet)
	{
		assert highLowSet.size() <= 2 && 1 <= highLowSet.size();
		assert !highLowSet.contains(null); //Assert statements that check that pre-conditions are true.
		
		Object [] objectHighLow = highLowSet.toArray(); //Converts the set to an object array so it's a bit
														//easier to work with
		
		if(objectHighLow.length == 1)
		{
			highPipCount = (int) objectHighLow[0];
			lowPipCount = (int) objectHighLow[0];
			
		}											//Assigns values based on the set/array size.
		
		if(objectHighLow.length == 2)
		{
			highPipCount = (int) objectHighLow[1];
			lowPipCount = (int) objectHighLow[0];
			
		}
		
		
		
		
	}//end of the highLowSet constructor

	//Getter methods
	//pre: none
	//post: rv = instance variable of the current domino instance
	public int getHighPipCount() {return highPipCount;}
	public int getLowPipCount() {return lowPipCount;}
	
	//pre: none
	//post: Succinctly prints the domino's properties/pipcount in a single line.
	public String toString()
	{
			
		return "This is the " + this.getHighPipCount() + "," + this.getLowPipCount() + " Domino."; 
			
	}//end of the toString method
	

}//end of the DominoHighLowImpl_Chavez class
