package change;
import java.util.*;

public class ChangeMakerImpl_Chavez implements ChangeMaker
{
	private List<Integer> denominations;
	
	//pre: denominations != null,
	// !denominations.contains(null), i in denominations > 0
	//post: Assigns to instance denominations based on given parameter
	public ChangeMakerImpl_Chavez(Set<Integer> denominations)
	{
		assert denominations != null : "ERROR ERROR ERROR Input denominations are null! WhAt oN EaRTh!?";
		assert !denominations.contains(null) : "ERROR ERROR ERROR denominations contain null! WhAt oN EaRTh!?";
		//assert statements to check that preconditions are met
		
		this.denominations = new ArrayList<Integer>();
		Object [] denominationsConvert= denominations.toArray();
		
		for(int index = 0; index < denominationsConvert.length; index++)
		{
			this.denominations.add((int) denominationsConvert[index]); //Assigns elements in set parameter to
																	//instance
		}
		
		assert !hasNegative(this.denominations) 
		: "ERROR ERROR ERROR denominations contain negative! WhAt oN EaRTh!?";
		//assert statements to check that preconditions are met
		
		Collections.sort(this.denominations);
		//Assures i in denomintions for denominations.get(i) < denominations.get(i + 1)
		
		
		
	}//end of the constructor

	//pre: none
	//post: isInGoodOrder(rv)
	public List<Integer> getDenominations() 
	{
		return denominations;
		
	}//end of the getDenominations method

	//pre: valueInCents >= 0
	//post: rv == true <==> valueInCents == 0 || valueInCents > i,
	//(remaining cent value after algorithim == 0)
	public boolean canMakeExactChange(int valueInCents) 
	{
		boolean canMakeExactChange = true;
		
		if(valueInCents == 0)
			return canMakeExactChange;
		
		if(valueInCents < 0 || valueInCents < Collections.min(getDenominations()))
			return !canMakeExactChange;
		
		List<Integer> exactChange = new ArrayList<Integer>();
		
		int ascendIndex = 0; //Used since parameter and instance differ in orientation
		int centValueDividend = valueInCents;
		for(int index = getDenominations().size() - 1; index >= 0 ; index--)
		{
			
			int denominationValue = getDenominations().get(index);
			exactChange.add(centValueDividend / denominationValue);
			centValueDividend -= exactChange.get(ascendIndex) * denominationValue;
			ascendIndex++;
			
		}
		
		if(centValueDividend == 0)
			return canMakeExactChange;
		
		return !canMakeExactChange;
		
	}//end of the canMakeExactChange method

	//pre: canMakeExactChange(valueInCents)
	//post: calculateValueOfChange(rv) == valueInCents, i in [0, rv.size() - 1) ==>
	//getDenominations.get(i) > rv.get(i+1) * getDenominations(i+1)
	public List<Integer> getExactChange(int valueInCents) 
	{
		List<Integer> exactChange = new ArrayList<Integer>();
		//Initialize return
		
		assert canMakeExactChange(valueInCents) 
		: "ERROR ERROR ERROR Exact change cannot be made! WhAt oN EaRTh!?";
		//Assert to verify precondition is true
		
		int ascendIndex = 0; //Used since parameter and instance differ in orientation
		int centValueDividend = valueInCents;
		for(int index = getDenominations().size() - 1; index >= 0 ; index--)
		{
			
			int denominationValue = getDenominations().get(index);
			exactChange.add(centValueDividend / denominationValue);
			centValueDividend -= exactChange.get(ascendIndex) * denominationValue;
			ascendIndex++;
			
		}
			
		
		
		return exactChange;
		
	}//end of the getExactChange method

	//pre: changeList.size() == getDenominations.size(), SIZE == changeList.size() 
	//post: getExactChange(rv) == changeList
	public int calculateValueOfChangeList(List<Integer> changeList) 
	{
		assert changeList.size() == getDenominations().size() : 
			"ERROR ERROR ERROR changeList and denominations are different sizes! WhAt oN EaRTh!?";
		//Assert to verify precondition is true
		
		int changeValue = 0;
		int ascendIndex = 0; //Used since parameter and instance differ in orientation
		
		for(int index = getDenominations().size() - 1; index >= 0; index--)
		{
			changeValue += getDenominations().get(index) * changeList.get(ascendIndex);
			ascendIndex++;
			
		}
		
		return changeValue;
	}
	
		
	
	//pre: none
	//post: i in list > 0
	private boolean hasNegative(List<Integer> list)
	{
		for(int index = 0; index < list.size(); index++)
			if(list.get(index) < 0)
				return true;
		
		return false;
		
	}//end of the hasNegative helper method
	

}//end of the ChangeMakerImpl_Chavez Class
