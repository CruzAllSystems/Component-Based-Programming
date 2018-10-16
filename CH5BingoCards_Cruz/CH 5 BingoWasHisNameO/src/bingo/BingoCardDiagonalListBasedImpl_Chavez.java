package bingo;

import java.util.ArrayList;
import java.util.List;

public class BingoCardDiagonalListBasedImpl_Chavez extends BingoCardDiagonalListBased_Abstract
{
	public BingoCardDiagonalListBasedImpl_Chavez(Mystery_D mystery)
	{
		super(mystery);
		
	}//end of the Mystery constructor
	
	
	
	@Override
	//pre: 1 <= row <= ROW_COUNT
	// 1 <= column <= COLUMN_COUNT
	//column == 1 ("B") ==> 1 <= rv <= 15
	//column == 2 ("I") ==> 16 <= rv <= 30
	//column == 3 ("N") ==> ((31 <= rv <= 45) || ((row = 3) && (rv == FREE_SPACE)));
	//column == 4 ("G") ==> 46 <= rv <= 60
	//column == 5 ("O") ==> 61 <= rv <= 75
	//post: ((column - 1)*15 + 1) <= rv <= ((column - 1) + 1)*15
	//rv == FREE_SPACE <==> ((row == FREE_SPACE_ROW) && (column == FREE_SPACE_COLUMN))
	public Integer getEntry(int row, int column)
	{
		assert(1 <= row && row <= BingoCard.ROW_COUNT) : "Error! Input row number is illegal";
		assert(1 <= column && column <= BingoCard.COLUMN_COUNT) : "Error! Input column number is illegal";
		//Assert statements to check that preconditions are true
		
		List<List<Integer>> columnListList = convertToColumnListList(diagonalListList); 
		
		Integer entry = new Integer(0);
		List<Integer> currentColumn = columnListList.get(column - 1);
		
		if(row == BingoCard.FREE_SPACE_ROW && column == BingoCard.FREE_SPACE_COLUMN)
			return BingoCard.FREE_SPACE;
		
		//Ifs to allow for rv to fit postconditions
		if(column == 1)
		{
			for(int index = 0; index < currentColumn.size(); index++)
			{
				Integer currentEntry = currentColumn.get(index);
				assert(1 <= currentEntry && currentEntry <= 15) 
				: "Error! Bingo card is not semantically correct";
				
			}
			
		}
		
		if(column == 2)
		{
			for(int index = 0; index < currentColumn.size(); index++)
			{
				Integer currentEntry = currentColumn.get(index);
				assert(16 <= currentEntry && currentEntry <= 30) 
				: "Error! Bingo card is not semantically correct";
				
			}
			
		}
		
		if(column == 3)
		{
			for(int index = 0; index < currentColumn.size(); index++)
			{
				Integer currentEntry = currentColumn.get(index);
				assert(31 <= currentEntry && currentEntry <= 45) 
				: "Error! Bingo card is not semantically correct";
				
			}
			
		}
		
		if(column == 4)
		{
			for(int index = 0; index < currentColumn.size(); index++)
			{
				Integer currentEntry = currentColumn.get(index);
				assert(46 <= currentEntry && currentEntry <= 60) 
				: "Error! Bingo card is not semantically correct";
				
			}
			
		}
		
		if(column == 5)
		{
			for(int index = 0; index < currentColumn.size(); index++)
			{
				Integer currentEntry = currentColumn.get(index);
				assert(61 <= currentEntry && currentEntry <= 75) 
				: "Error! Bingo card is not semantically correct";
				
			}
			
		}
			
		entry = currentColumn.get(row - 1);
		
		return entry;
	}

	@Override
	//pre: 1 <= number <= 75
	//post: contains(number) <==> 
	//(isMarked(row, column) for some 1 <= row <= ROW_COUNT, 1 <= column <= COLUMN_COUNT)
	public void mark(int number)
	{
		assert(1 <= number && number <= 75) : "Error! Entry number is not valid";
		//Assert statement to check that precondition is true
		
		if(!contains(number))
			return;
		//Ensures rv matches postcondition
		
		integersMarked.add(number);

	}

	@Override
	//pre: none
	//post: rv == 
	//((getEntry(1, 1) == number) || (getEntry(1, 2) == number) || ... || (getEntry(1, COLUMN_COUNT) == number) ||
	//(getEntry(2, 1) == number) || (getEntry(2, 2) == number) || ... || (getEntry(2, COLUMN_COUNT) == number) ||
	//(getEntry(ROW_COUNT, 1) == number) || (getEntry(ROW_COUNT, 2) == number) || ... 
	//|| (getEntry(ROW_COUNT, COLUMN_COUNT) == number))
	public boolean contains(int number)
	{
		List<List<Integer>> columnListList = convertToColumnListList(diagonalListList);
		boolean containment = false;
		
		for(int index = 0; index < columnListList.size(); index++)
		{
			List<Integer> currentColumn = columnListList.get(index);
			for(int index2 = 0; index2 < currentColumn.size(); index2++)
			{
				if(currentColumn.get(index2) == BingoCard.FREE_SPACE)
					continue;
				
				if(number == currentColumn.get(index2))
					containment = true;
				
			}
			
		}//end of the check forLoop
		
		return containment;
	}

	@Override
	//part of pre: 1 <= row <= ROW_COUNT
	//1 <= column <= COLUMN_COUNT
	//post rv == true <==> intergersMarked.contains(rv)
	public boolean isMarked(int row, int column)
	{
		assert(1 <= row && row <= BingoCard.ROW_COUNT) : "Error! Input row number is illegal";
		assert(1 <= column && column <= BingoCard.COLUMN_COUNT) : "Error! Input column number is illegal";
		//Assert statements to check that preconditions are true
		
		if(row == BingoCard.FREE_SPACE_ROW && column == BingoCard.FREE_SPACE_COLUMN)
			return true;
		
		boolean markedI = false;
		
		Integer entry = getEntry(row, column);
		
		if(integersMarked.contains(entry))
			markedI = true;
		
		return markedI;
	}

	@Override
	//pre: none
	//post: rv == true <==> isLeftDiagonalWinner() || isHorizontalWinner() 
	//|| isVerticalWinner() || isRightDiagonalWinner()
	public boolean isWinner()
	{
		return isVerticalWinner() || isHorizontalWinner() || isLeftDiagonalWinner() || isRightDiagonalWinner();
		
	}
	
	//pre: none
	//post: rv == true <==> integersMarked.contains(columnListList.get(1...COLUMN_SIZE))
	private boolean isVerticalWinner()
	{
			boolean verticalWinner = true;
			List<List<Integer>> columnListList = convertToColumnListList(diagonalListList);
				
				for(int index = 0; index < columnListList.size(); index++)
				{
					List<Integer> currentColumn = columnListList.get(index);
					for(int index2 = 0; index2 < currentColumn.size(); index2++)
					{
						if(currentColumn.get(index2) == BingoCard.FREE_SPACE)
							continue;
						
						if(!integersMarked.contains(currentColumn.get(index2)))
							verticalWinner = false;
						
					}
					
					if(verticalWinner == true)
						return verticalWinner;
					
				}//end of the check forLoop
				
				return verticalWinner;
				
				
	}//end of the isVerticalWinner helper method
			
			//pre: none
			//post: rv == true <==> integersMarked.contains(all numbers in a row)
			private boolean isHorizontalWinner()
			{
				List<List<Integer>> columnListList = convertToColumnListList(diagonalListList);
				boolean horizontalWinner = true;
				
				for(int index = 0; index < columnListList.size(); index++)
				{
					for(int index2 = 0; index2 < columnListList.size(); index2++)
					{
						if(!integersMarked.contains(columnListList.get(index2).get(index)))
							horizontalWinner = false;
						
					}
					
					if(horizontalWinner == true)
						return horizontalWinner;
					
				}
				
				return horizontalWinner;
				
				
			}//end of the isHorizontalWinner helper method
			
			private boolean isLeftDiagonalWinner()
			{
				boolean leftDiagonalWinner = true;
				List<List<Integer>> columnListList = convertToColumnListList(diagonalListList);
				
				List<Integer> leftDiagList = new ArrayList<Integer>();
				leftDiagList.add(columnListList.get(0).get(0));
				leftDiagList.add(columnListList.get(1).get(1));
				leftDiagList.add(columnListList.get(3).get(3));
				leftDiagList.add(columnListList.get(4).get(4));
				
				for(int index = 0; index < leftDiagList.size(); index++)
				{
					if(!integersMarked.contains(leftDiagList.get(index)))
						leftDiagonalWinner = false;
					
				}
				
				return leftDiagonalWinner;
				
				
			}//end of the isLeftDiagonalWinner helper method
			
			private boolean isRightDiagonalWinner()
			{
				List<List<Integer>> columnListList = convertToColumnListList(diagonalListList);
				boolean rightDiagonalWinner = true;
				
				List<Integer> rightDiagList = new ArrayList<Integer>();
				rightDiagList.add(columnListList.get(0).get(4));
				rightDiagList.add(columnListList.get(1).get(3));
				rightDiagList.add(columnListList.get(3).get(1));
				rightDiagList.add(columnListList.get(4).get(0));
				
				for(int index = 0; index < rightDiagList.size(); index++)
				{
					if(!integersMarked.contains(rightDiagList.get(index)))
						rightDiagonalWinner = false;
					
				}
				
				return rightDiagonalWinner;
				
				
			}//end of the isRightDiagonalWinner helper method
	
	private List<List<Integer>> convertToColumnListList(List<List<Integer>> diagonalListList)
	{
		List<List<Integer>> columnListList = new ArrayList<List<Integer>>();
		List<Integer> column1 = new ArrayList<Integer>();
		List<Integer> column2 = new ArrayList<Integer>();
		List<Integer> column3 = new ArrayList<Integer>();
		List<Integer> column4 = new ArrayList<Integer>();
		List<Integer> column5 = new ArrayList<Integer>();
		
		column1.add(diagonalListList.get(4).get(3));
		column1.add(diagonalListList.get(3).get(3));
		column1.add(diagonalListList.get(2).get(2));
		column1.add(diagonalListList.get(1).get(1));
		column1.add(diagonalListList.get(0).get(0));
		
		column2.add(diagonalListList.get(5).get(3));
		column2.add(diagonalListList.get(4).get(2));
		column2.add(diagonalListList.get(3).get(2));
		column2.add(diagonalListList.get(2).get(1));
		column2.add(diagonalListList.get(1).get(0));
		
		column3.add(diagonalListList.get(6).get(2));
		column3.add(diagonalListList.get(5).get(2));
		column3.add(BingoCard.FREE_SPACE);
		column3.add(diagonalListList.get(3).get(1));
		column3.add(diagonalListList.get(2).get(0));
		
		column4.add(diagonalListList.get(7).get(1));
		column4.add(diagonalListList.get(6).get(1));
		column4.add(diagonalListList.get(5).get(1));
		column4.add(diagonalListList.get(4).get(1));
		column4.add(diagonalListList.get(3).get(0));
		
		column5.add(diagonalListList.get(8).get(0));
		column5.add(diagonalListList.get(7).get(0));
		column5.add(diagonalListList.get(6).get(1));
		column5.add(diagonalListList.get(5).get(1));
		column5.add(diagonalListList.get(4).get(0));
		
		columnListList.add(column1);
		columnListList.add(column2);
		columnListList.add(column3);
		columnListList.add(column4);
		columnListList.add(column5);
		
		return columnListList;
		
		
	}//end of the convertToColumnListList helper method

	@Override
	public String toString()
	{
		return "This one was just overall painful";
	}

}//end of the BingoCardDiagonalListBasedImpl class
