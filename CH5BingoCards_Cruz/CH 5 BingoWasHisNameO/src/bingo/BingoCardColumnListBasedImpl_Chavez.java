package bingo;
import java.util.*;

public class BingoCardColumnListBasedImpl_Chavez extends BingoCardColumnListBased_Abstract 
{
	//STUDENT: DO *NOT* ADD ANY INSTANCE VARIABLES HERE OR IN THE ABSTRACT CLASS!!!

		public BingoCardColumnListBasedImpl_Chavez(Mystery_C mystery)
		{
			super(mystery);
			
		}//end of the Mystery constructor
		
		public BingoCardColumnListBasedImpl_Chavez(List<List<Integer>> columnListList, Set<Integer> integersMarked)
		{
			this.columnListList = new ArrayList<List<Integer>>();
			this.integersMarked = new HashSet<Integer>();
			
			for(int index = 0; index < columnListList.size(); index++)
			{
				List<Integer> cardColumn = columnListList.get(index);
				List<Integer> currentColumn = new ArrayList<Integer>();
				for(int index2 = 0; index2 < cardColumn.size(); index2++)
				{
					currentColumn.add(cardColumn.get(index2));
					
				}
				
				this.columnListList.add(currentColumn);
				
			}//end of the instantiating forLoop for column
			
			List integersMarkedList = new ArrayList(integersMarked);
			for(int index = 0; index < integersMarkedList.size(); index++)
			{
				this.integersMarked.add((Integer)integersMarkedList.get(index));
				
			}//end of the instantiating forLoop for marked
			
			
			
		}//end of the testing constructor
		
		
		
		@Override
		//pre: 1 <= row <= ROW_COUNT
		// 1 <= column <= COLUMN_COUNT
		//post: column == 1 ("B") ==> 1 <= rv <= 15
		//column == 2 ("I") ==> 16 <= rv <= 30
		//column == 3 ("N") ==> ((31 <= rv <= 45) || ((row = 3) && (rv == FREE_SPACE)));
		//column == 4 ("G") ==> 46 <= rv <= 60
		//column == 5 ("O") ==> 61 <= rv <= 75
		//((column - 1)*15 + 1) <= rv <= ((column - 1) + 1)*15
		//rv == FREE_SPACE <==> ((row == FREE_SPACE_ROW) && (column == FREE_SPACE_COLUMN))
		public Integer getEntry(int row, int column)
		{
			assert(1 <= row && row <= BingoCard.ROW_COUNT) : "Error! Input row number is illegal";
			assert(1 <= column && column <= BingoCard.COLUMN_COUNT) : "Error! Input column number is illegal";
			//Assert statements to check that preconditions are true
			
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
			
			
		}//end of the getEntry method

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
			
		}//end of the mark method

		@Override
		//pre: none
		//post: rv == 
		//((getEntry(1, 1) == number) || (getEntry(1, 2) == number) || ... || (getEntry(1, COLUMN_COUNT) == number) ||
		//(getEntry(2, 1) == number) || (getEntry(2, 2) == number) || ... || (getEntry(2, COLUMN_COUNT) == number) ||
		//(getEntry(ROW_COUNT, 1) == number) || (getEntry(ROW_COUNT, 2) == number) || ... 
		//|| (getEntry(ROW_COUNT, COLUMN_COUNT) == number))
		public boolean contains(int number)
		{
			
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
			
		}//end of the contains method

		@Override
		//part of pre: 1 <= row <= ROW_COUNT
		//1 <= column <= COLUMN_COUNT
		//post rv == true <==> intergersMarked.contains(rv)
		public boolean isMarked(int row, int column)
		{
			assert(1 <= row && row <= BingoCard.ROW_COUNT) : "Error! Input row number is illegal";
			assert(1 <= column && column <= BingoCard.COLUMN_COUNT) : "Error! Input column number is illegal";
			//Assert statements to check that preconditions are true
			
			boolean markedI = false;
			
			Integer entry = getEntry(row, column);
			
			if(integersMarked.contains(entry))
				markedI = true;
			
			return markedI;


		}//end of the isMarked method

		@Override
		//pre: none
		//post: rv == true <==> isLeftDiagonalWinner() || isHorizontalWinner() 
		//|| isVerticalWinner() || isRightDiagonalWinner()
		public boolean isWinner()
		{
			return isVerticalWinner() || isHorizontalWinner() || isLeftDiagonalWinner() || isRightDiagonalWinner();
			
		}//end of the isWinner method
		
		//pre: none
		//post: rv == true <==> integersMarked.contains(columnListList.get(1...COLUMN_SIZE))
		private boolean isVerticalWinner()
		{
			boolean verticalWinner = true;
			
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
			
			
		}//end of the isVerticalWinner helper method
		
		private boolean isRightDiagonalWinner()
		{
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
			
			
		}//end of the isVerticalWinner helper method
		
		

		@Override
		public String toString()
		{
			String temp = "";
			
			for(int index = 1; index <= columnListList.size(); index++)
			{
				for(int index2 = 1; index2 <= columnListList.size(); index2++)
				{
					temp += columnListList.get(index2 - 1).get(index - 1) + " | ";
					if(isMarked(index, index2))
						temp += "m";
					
					
				}
				
				temp += "\n";
				
			}
			
			return temp;
			
		}//end of the toString method

}//end of the BingoCardColumnListBasedImpl class
