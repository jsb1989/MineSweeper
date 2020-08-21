import java.util.Random;
import java.util.Scanner;
public class MineSweeperBoard{
	private int rows,columns,NumOfMines,flags;
	private boolean MineFound;
	private char MineLocations[][];
	private char Board[][];
	//Constructor for board
	public MineSweeperBoard(int rows, int columns,int NumOfMines){
		this.rows=rows;
		this.columns=columns;
		this.NumOfMines=NumOfMines;
		this.flags=NumOfMines;
		this.MineFound=false;
		MineLocations=new char[rows][columns];
		Board=new char[rows][columns];
	}
	//Populates board player will see with ? and places mines
	public void createBoard(){
		Random rand = new Random();
		//Creates the empty board the player sees
		for(int i=0; i<this.rows;i++){
			for(int j=0; j<this.columns;j++){
				Board[i][j]='?';
			}
		}
		//The following creates a second board that keeps track of which spot has mines
		int RandRow=0,RandCol=0;
		//First Part fills in the cells for reference	
		for(int i=0; i<this.rows;i++){
			for(int j=0; j<this.columns;j++){
				MineLocations[i][j]='E';
			}
		}
		//Second part places the mines
		for(int k=0; k<this.NumOfMines;k++){
			RandRow=rand.nextInt(this.rows);
			RandCol=rand.nextInt(this.columns);
			int MineExit=0;
			while(MineExit==0){
				if(MineLocations[RandRow][RandCol]=='E'){
					MineLocations[RandRow][RandCol]='*';
					MineExit=1;
				} else {
					RandRow=rand.nextInt(this.rows);
					RandCol=rand.nextInt(this.columns);
				}
			}
		}
	}
	//displays the board to the user
	public void displayBoard(){
		System.out.printf("  |");
		for(int j=0;j<this.columns;j++){
				System.out.printf("%d|",j);
		}
		System.out.printf("\n");
		for(int i=0; i<this.rows;i++){
			if(i<10){
				System.out.printf(" %d",i);
			} else {
				System.out.printf("%d",i);
			}	
			for(int j=0; j<this.columns;j++){
				if(j<10){
					System.out.printf("|%c",Board[i][j]);
				} else {
					System.out.printf("|%c ",Board[i][j]);
				}
			}
			System.out.printf("|\n");
		}
		System.out.printf("  Flags left: %d\n\n",this.flags);
	}
	//Allows user to select a cell and will figure out if theres a mine or adjacent squares to one
	public void Selection(){
		int row,col,select;
		Scanner in= new Scanner(System.in);
		System.out.printf("Enter row: ");
		row=in.nextInt();
		System.out.printf("Enter column: ");
		col=in.nextInt();
		int selectExit=0;
		while(selectExit==0){
			System.out.printf("Would you like to 0. Select or 1.Flag : ");
			select=in.nextInt();
			switch(select){
				case 0:
					if(MineLocations[row][col]=='*'){
						this.MineFound=true;
						selectExit=1;
					}else{
						Board[row][col]=adjMines(row,col);
						selectExit=1;		
					}
					break;
				case 1:
					if(this.flags==0){
						System.out.printf("All Out Of Flags\n");
					} else {
						Board[row][col]='F';
						this.flags--;
					}
					selectExit=1;
					break;
				default:
					System.out.printf("0 or 1 Please\n");
					break;
			}	
		}		
	}
	//If mine was found it will return true
	public boolean isMine(){
		if(this.MineFound==true){
			return true;
		} else {
			return false;
		}
	}
	//Display game over board showing mine locations
	public void FinishedDisplay(){
		for(int i=0; i<this.rows;i++){
			for(int j=0; j<this.columns;j++){
				if(MineLocations[i][j]=='*'){
					Board[i][j]='*';
				}
			}
		}
		displayBoard();
	}
	//Returns true if user correctly flagged all mines
	public boolean checkFlags(){
		int count=0;
		//This will count how many mines were flagged
		for(int i=0; i<this.rows;i++){
			for(int j=0; j<this.columns;j++){
				if(MineLocations[i][j]=='*'&&Board[i][j]=='F'){
					count++;
				}
			}
		}
		//If the mines were correctly flagged returns true, else return false
		if(count==this.NumOfMines){
			return true;
		} else {
			return false;
		}
	}
	//Finds how many mines are adjacent and returns the number
	public char adjMines(int rows,int cols){
			int count=0,row=rows,col=cols;
			//Cell to the north of the selected cell
			if((row-1)>=0){
				if(MineLocations[row-1][col]=='*'){
					count++;
				}	
			}
			//Cell to the north-east of the selected cell
			if((row-1)>=0&&(col+1)<this.columns){
				if(MineLocations[row-1][col+1]=='*'){
					count++;
				}	
			}
			//Cell to the east of the selected cell
			if((col+1)<this.columns){
				if(MineLocations[row][col+1]=='*'){
					count++;
				}	
			}
			//Cell to the south-east of the selected cell
			if((row+1)<this.rows&&(col+1)<this.columns){
				if(MineLocations[row+1][col+1]=='*'){
					count++;
				}	
			}
			//Cell to the south of the selected cell
			if((row+1)<this.rows){
				if(MineLocations[row+1][col]=='*'){
					count++;
				}	
			}
			//Cell to the south-west of the selected cell
			if((row+1)<this.rows&&(col-1)>=0){
				if(MineLocations[row+1][col-1]=='*'){
					count++;
				}
			}
			//Cell to the west of the selected cell
			if((col-1)>=0){	
				if(MineLocations[row][col-1]=='*'){
					count++;
				}
			}
			//Cell to the north-west of the selected cell
			if((row-1)>=0&&(col-1)>=0){	
				if(MineLocations[row-1][col-1]=='*'){
					count++;
				}
			}
			//It will return the number of adjacent mines based on how many were counted
			switch(count){
				case 0:
					return '0';
				case 1:
					return '1';
				case 2:
					return '2';
				case 3:
					return '3';
				case 4:
					return '4';
				case 5:
					return '5';
				case 6:
					return '6';
				case 7:
					return '7';
				default:
					return '8';
			}
	}
}
