//Created by Joshua Barnett
import java.util.Scanner;

public class MineSweeper{
	public static void main(String[] args){
		int choice,MainExit=0,row=0,col=0,mine=0;
		Scanner in= new Scanner(System.in);
		//Welcome message
		System.out.printf("Welcome to Minesweeper\nPlease Enter Difficulty\n");
		while(MainExit==0){
			int DiffExit=0;
			//Choose the difficulty level and based on that it will create the game board
			while(DiffExit==0){
				System.out.printf("0 For Beginner\n1 For Medium\n2 For Hard\n");
				int difficulty=in.nextInt();
				switch(difficulty){
					case 0:
						row=8;
						col=8;
						mine=4;
						DiffExit=1;
						break;
					case 1:
						row=9;
						col=9;
						mine=8;
						DiffExit=1;
						break;
					case 2:
						row=10;
						col=10;
						mine=10;
						DiffExit=1;
						break;
					default:
						System.out.printf("Please enter between 0-2\n");
						break;
				}	
			}
		//Construction of Game Board and the creation of the elements of the board	
		MineSweeperBoard Board=new MineSweeperBoard(row,col,mine);	
		Board.createBoard();
		int GameExit=0;
			//Menu to choose to enter coordinates or end game
			while(GameExit==0){
				System.out.printf("     GAME BOARD\n");
				Board.displayBoard();
				System.out.printf("Choose Option\n");
				System.out.printf("0 To Enter Coordinates\n");
				System.out.printf("1 To End Game\n");
				choice=in.nextInt();
				int choiceExit=0;
				while(choiceExit==0){
					switch(choice){
						case 0:
							Board.Selection();
							//checks for a win after every selection
							if(Board.checkFlags()==true){
								System.out.printf("You Have Successfully Found All Mines!!!\n");
								Board.FinishedDisplay();
								GameExit=1;
								choiceExit=1;
							}
							//checks to see if a mine was clicked after every selection unless user has won
							if(Board.isMine()==true){
								System.out.printf("********KABOOM********\n YOU FOUND A MINE\n");
								Board.FinishedDisplay();
								GameExit=1;
								choiceExit=1;
							}
							choiceExit=1;
							break;
						case 1:
							GameExit=1;
							choiceExit=1;
							break;
						default:
							System.out.printf("Enter 0 or 1\n");
							break;
					}
				}	
			}
		int quitExit=0;	
		while(quitExit==0){
			System.out.printf("Would you like to play again?\n");
			System.out.printf("0 For yes, 1 For No\n");
			int Quit=in.nextInt();
				switch(Quit){
					case 0:
						quitExit=1;
						break;
					case 1:
						System.out.printf("Thanks for playing\n");
						MainExit=1;
						quitExit=1;
						break;
					default:
						System.out.printf("Enter 0 or 1\n");
						break;
				}
			}	
		}
	}
}
