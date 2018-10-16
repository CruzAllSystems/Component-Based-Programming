package dominoes;
import java.util.*;

public class DominoHighLowSetImpl_Chavez implements Domino
{
	private Set<Integer> highLowSet;
	
	//pre: MINIMUM_PIP_COUNT <= highPipCount <= MAXIMUM_PIP_COUNT
	//MINIMUM_PIP_COUNT <= lowPipCount <= MAXIMUM_PIP_COUNT
	//highPipCount >= lowPipCount
	//post: Adds highPipCount and lowPipCount to the highLowSet for return/use
	public DominoHighLowSetImpl_Chavez(int highPipCount, int lowPipCount)
	{
		assert highPipCount >= lowPipCount : "ERROR ERROR ERROR. highPipCount < lowPipCount!! WHat On eArTh!?"; 
		assert highPipCount >= Domino.MINIMUM_PIP_COUNT && highPipCount <= Domino.MAXIMUM_PIP_COUNT 
				: "ERROR ERROR ERROR. highPipCount is not in valid range!! WHat On eArTh!?";
		assert lowPipCount >= Domino.MINIMUM_PIP_COUNT && lowPipCount <= Domino.MAXIMUM_PIP_COUNT 
				: "ERROR ERROR ERROR. lowPipCount is not in valid range!! WHat On eArTh!?";
		//Assert statements to check that preconditions are true.
		
		highLowSet = new HashSet<Integer>();
		
		highLowSet.add(highPipCount);
		highLowSet.add(lowPipCount);
		
	}//end of the high/LowPipCount constructor
	
	public static final char SUM_DIFFERENCE_DELIMITER = ',';
	
	//pre: none
	//post: rv = true <==> str.length >= 3, str.contains(SUM_DIFFERENCE_DELIMETER), str != null, and the 
	//sum and difference are valid
	public static boolean isSumDifferenceString(String str)
	{
		if(str == null)
			return false;
		
		if(str.length() < 3)
			return false;
		
		if(!str.contains("" + SUM_DIFFERENCE_DELIMITER))
			return false;
		
		
		Scanner stringReader = new Scanner(str).useDelimiter("\\" + SUM_DIFFERENCE_DELIMITER);
		
		if(!stringReader.hasNextInt()) 
			return false;
		
		int sumCandidate = stringReader.nextInt();
		int differenceCandidate = stringReader.nextInt();
		
		if(sumCandidate < differenceCandidate) //Checks if the candidate variables are valid
			return false;
		
		return (2 * Domino.MINIMUM_PIP_COUNT <= sumCandidate &&  sumCandidate <= 2 * Domino.MAXIMUM_PIP_COUNT) &&
				(-(Domino.MAXIMUM_PIP_COUNT - Domino.MINIMUM_PIP_COUNT) <= differenceCandidate) && 
				(differenceCandidate <= Domino.MAXIMUM_PIP_COUNT - Domino.MINIMUM_PIP_COUNT);
		
		
		
		
	}//end of the isSumDifferenceString method
	
	//pre: isSumDifferenceString == true
	//post: adds to the highLowSet instance based on the parameter 
	public DominoHighLowSetImpl_Chavez(String sumDifferenceString)
	{
		assert isSumDifferenceString(sumDifferenceString) 
		: "ERROR ERROR ERROR. sumDifferenceString is not a valid input!! WHat On eArTh!?";
		
		Scanner sumDifferenceStringReader = new Scanner(sumDifferenceString)
				.useDelimiter("\\" + SUM_DIFFERENCE_DELIMITER);
		
		int sum = sumDifferenceStringReader.nextInt();
		int difference = sumDifferenceStringReader.nextInt();
		
		int lowPipCount = (sum - Math.abs(difference)) / 2;
		int highPipCount = (sum + Math.abs(difference)) / 2;
		
		highLowSet = new HashSet<Integer>();
		
		highLowSet.add(highPipCount);
		highLowSet.add(lowPipCount);
		
		
	}//end of the sumDifferenceString constructor
	
	//pre: none
	//post: returns true <==> k / 8 && k % 8 are valid pip count values
	public static boolean isLowPlus8TimesHighInteger(int k)
	{
		if(!(0 <= k && k <= 54 && k <= 9*(k/8)))
			return false;
		
		int lowPipCount = k % 8;
		int highPipCount = k / 8;
		
		return highPipCount >= Domino.MINIMUM_PIP_COUNT && highPipCount <= Domino.MAXIMUM_PIP_COUNT &&
		 lowPipCount >= Domino.MINIMUM_PIP_COUNT && lowPipCount <= Domino.MAXIMUM_PIP_COUNT;
		
		
	}//end of the isLowPlus8TimesHighInteger method.
	
	//pre: isLowPlus8TimesHighInteger(lowPlus8TimesHigh) == true
	//post: adds to highLowSet instance based on given parameter
	public DominoHighLowSetImpl_Chavez(int lowPlus8TimesHigh)
	{
		assert isLowPlus8TimesHighInteger(lowPlus8TimesHigh) 
		: "ERROR ERROR ERROR. lowPlus8TimesHigh is not a valid input!! WHat On eArTh!?";
		int highPipCount = lowPlus8TimesHigh / 8;
		int lowPipCount = lowPlus8TimesHigh % 8;
		
		highLowSet = new HashSet<Integer>();
		
		highLowSet.add(highPipCount);
		highLowSet.add(lowPipCount);
		
	}//end of the lowPlus8TimesHigh constructor
	
	//Getter methods
	//pre: none
	//post: rv = instance variable of the current domino instance
	public int getHighPipCount() 
	{
		Object [] highLowSetArray = highLowSet.toArray();
		assert highLowSetArray.length == 2 || highLowSetArray.length == 1; //Checks that the highLowSetArray 
																			//has valid pipcounts
		int [] highLowSetArrayIntCast = new int [highLowSetArray.length];
		
		for(int index = 0; index < highLowSetArrayIntCast.length; index++)
			highLowSetArrayIntCast[index] = (int) highLowSetArray[index]; //converts highLowSetArray into an
																		//array of ints
		Arrays.sort(highLowSetArrayIntCast);
		int highPipCount;
		
		if(highLowSetArrayIntCast.length == 1)
			highPipCount = highLowSetArrayIntCast[0];
		else
			highPipCount = highLowSetArrayIntCast[1];
		
		return highPipCount;
		
		
	}
	public int getLowPipCount() 
	{
		Object [] highLowSetArray = highLowSet.toArray();
		assert highLowSetArray.length == 2 || highLowSetArray.length == 1; //Checks that the highLowSetArray 
																		  //has valid pipcounts
		int [] highLowSetArrayIntCast = new int [highLowSetArray.length];
		
		for(int index = 0; index < highLowSetArrayIntCast.length; index++)
			highLowSetArrayIntCast[index] = (int) highLowSetArray[index];//converts highLowSetArray into an
																	//array of ints
		
		Arrays.sort(highLowSetArrayIntCast);
		
		return highLowSetArrayIntCast[0];
		
	}
	
	//pre: none
	//post: Succinctly prints the domino's properties/pipcount in a single line.
	public String toString()
	{
			
		return "This is the " + this.getHighPipCount() + "," + this.getLowPipCount() + " Domino."; 
			
	}//end of the toString method

}//end of the DominoHighLowSetImpl_Chavez class
