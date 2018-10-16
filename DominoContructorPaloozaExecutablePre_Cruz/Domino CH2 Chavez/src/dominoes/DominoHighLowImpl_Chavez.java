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
		assert highPipCount >= lowPipCount : "ERROR ERROR ERROR. highPipCount < lowPipCount!! WHat On eArTh!?";
		assert highPipCount >= Domino.MINIMUM_PIP_COUNT && highPipCount <= Domino.MAXIMUM_PIP_COUNT 
		: "ERROR ERROR ERROR. highPipCount is out of acceptable range!! WHat On eArTh!?";
		assert lowPipCount >= Domino.MINIMUM_PIP_COUNT && lowPipCount <= Domino.MAXIMUM_PIP_COUNT 
		: "ERROR ERROR ERROR. lowPipCount is out of acceptable range!! WHat On eArTh!?";
		//Assert statements to check that preconditions are true.
		
		
		this.highPipCount = highPipCount;
		this.lowPipCount = lowPipCount;
		
	}//end of the high and low pip count constructor
	
	public static final char HIGH_LOW_STRING_SEPARATOR = ':';
	
	//pre: none
	//post: rv = true <==> str.length >= 3 && str != null && str.contains(HIGH_LOW_STRING OPERATOR) && the high >= low
	public static boolean isHighLowString(String str)
	{
		if(str == null)
			return false;
		
		if(str.length() < 3)
			return false;
		
		if(!str.contains("" + HIGH_LOW_STRING_SEPARATOR))
			return false;
		
		Scanner stringReader = new Scanner(str).useDelimiter("\\" + HIGH_LOW_STRING_SEPARATOR);
		
		if(!stringReader.hasNextInt())
			return false;
		
		int highPipCountCandidate = stringReader.nextInt();
		int lowPipCountCandidate = stringReader.nextInt();
		
		return highPipCountCandidate >= lowPipCountCandidate && 
	    highPipCountCandidate >= Domino.MINIMUM_PIP_COUNT && highPipCountCandidate <= Domino.MAXIMUM_PIP_COUNT &&
	    lowPipCountCandidate >= Domino.MINIMUM_PIP_COUNT && lowPipCountCandidate <= Domino.MAXIMUM_PIP_COUNT;
	  //Checks if the candidate variables are valid
		
		
	}//end of the isHighLowString method
	
	//pre: isHighLowString(highLowString)
	//post: Sets instance variables according to the given parameter
	public DominoHighLowImpl_Chavez(String highLowString)
	{
		assert isHighLowString(highLowString) == true : "ERROR ERROR ERROR. Given string !highLowString !! WHat On eArTh!?"; 
		//Assert statement to check if pre-conditions are true
		
		Scanner highLowStringReader = new Scanner(highLowString).useDelimiter("\\" + HIGH_LOW_STRING_SEPARATOR);
		
		highPipCount = highLowStringReader.nextInt();
		lowPipCount = highLowStringReader.nextInt();
		
	}//end of the highLowString domino constructor
	
	public static final int INDEX_OF_SUM = 0; //Index indicators for sumDifference []
	public static final int INDEX_OF_DIFFERENCE = 1;
	
	//pre: sumDifference.lengh == 2, sumDifference[INDEX_OF_SUM] >= sumDifference[INDEX_OF_DIFFERENCE],
		//(sumDifference[INDEX_OF_SUM] - sumDifference[INDEX_OF_DIFFERENCE]) % 2 == 0,
		//!(sumDifference[INDEX_OF_SUM] % 2 == 1 && sumDifference[INDEX_OF_DIFFERENCE] == 0)
	//post: Sets instance variables according to the given parameter
	public DominoHighLowImpl_Chavez(int [] sumDifference)
	{
		assert sumDifference.length == 2 : "ERROR ERROR ERROR. [] sumDifference.length != 2!! WHat On eArTh!?"; 
		assert sumDifference[INDEX_OF_SUM] >= sumDifference[INDEX_OF_DIFFERENCE] 
			   : "ERROR ERROR ERROR. sum < difference!! WHat On eArTh!?";
		//Checks that the preconditions are true.
		
		int sum = sumDifference[INDEX_OF_SUM];
		int difference = sumDifference[INDEX_OF_DIFFERENCE];
		
		assert (sum - difference) % 2 == 0 : "ERROR ERROR ERROR. (sum - difference) is odd!! WHat On eArTh!?";
		assert !(sum % 2 == 1 && difference == 0) 
		: "ERROR ERROR ERROR. difference is 0 despite sum being odd!! WHat On eArTh!?";
		//Checks that the preconditions are true.
		
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
		assert highLowSet.size() <= 2 && 1 <= highLowSet.size() 
		: "ERROR ERROR ERROR. highLowSet is not a valid size!! WHat On eArTh!?";
		assert !highLowSet.contains(null) : "ERROR ERROR ERROR. highLowSet is null!! WHat On eArTh!?"; 
		//Assert statements that check that pre-conditions are true.
		
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
