//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package _00_Intro_To_2D_Arrays;

public class WrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        int numCows = 0;
        int xCowCoord = -1;
        int yCowCoord = -1;
        final int NORTH = 0;
        final int EAST  = 1;
        final int SOUTH = 2;
        final int WEST  = 3;
        String strToSearch = "cow";
        String strToSearchRev = "woc";

        for( int direction = NORTH; direction <= WEST; direction++ ) {
            numCows = 0;
        
            switch( direction ){

            case EAST:
            case WEST:

                // Check if column width is large enough to fit a cow
                // Can use row 0 because field is guaranteed to be rectangular
                if( field[0].length >= strToSearch.length() ) {
                    
                    for( int row = 0; row < field.length; row++ ) {

                        for( int col = 0; col <= field[row].length - strToSearch.length(); col++ ) {
                            String curWord = "";

                            for( int letter = col; letter < col + strToSearch.length(); letter++ ) {
                                curWord += field[row][letter];
                            }

                            if( direction == EAST && curWord.equals( strToSearchRev ) ) {
                                yCowCoord = row;
                                xCowCoord = col + ( strToSearch.length() - 1 );
                                col += strToSearch.length();
                                numCows++;
                            } else if( direction == WEST && curWord.equals( strToSearch ) ) {
                                yCowCoord = row;
                                xCowCoord = col;
                                col += strToSearch.length();
                                numCows++;
                            }
                        }
                    }
                    // Else, there can't be any cows here so leave cow count at 0
                }
                break;

            case NORTH:
            case SOUTH:

                // Check if row height is large enough to fit a cow
                if( field.length >= strToSearch.length() ) {
                    
                    // Can use row 0 because field is guaranteed to be rectangular
                    for( int col = 0; col < field[0].length; col++ ) {
                
                        for( int row = 0; row <= field.length - strToSearch.length(); row++ ) {
                            String curWord = "";

                            for( int letter = row; letter < row + strToSearch.length(); letter++ ) {
                                curWord += field[letter][col];
                            }

                            if( direction == SOUTH && curWord.equals( strToSearchRev ) ) {                            
                                yCowCoord = row + ( strToSearch.length() - 1 );
                                xCowCoord = col;
                                row += strToSearch.length();
                                numCows++;
                            } else if( direction == NORTH && curWord.equals( strToSearch ) ) {
                                yCowCoord = row;
                                xCowCoord = col;
                                row += strToSearch.length();
                                numCows++;
                            }
                        }
                    }
                }
                // Else, there can't be any cows here so leave cow count at 0
                break;

            default:
                // Should never get here
                break;
            }

            // Rules say there is exactly 1 WWC
            if( numCows == 1 ) {
                break;
            }
        } // end for directions
        
        return new int[] { xCowCoord, yCowCoord };
    }
}
