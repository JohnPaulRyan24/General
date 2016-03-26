import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ATM{
	public static void main(String[] args) throws FileNotFoundException{
		
		String[] temp = {"Kansas","Oregon","Xavier","MichiganSt","Kansas","MichiganSt","Kansas"};
		Person John = new Person(temp,620);
		String[] temp1 = {"Villanova","Oregon","UNC","MichiganSt","Villanova","UNC","UNC"};
		Person Dad = new Person(temp1,590);
		String[] temp2 = {"Iowa","Oklahoma","Michigan","Syracuse","Oklahoma","Syracuse","Oklahoma"};
		Person Clare = new Person(temp2,560);
		String[] temp3 = {"Miami","TexasAM","UNC","UVA","Miami","UVA","UVA"};
		Person Patrick = new Person(temp3,470);
		Person[] group = {John,Dad, Clare,Patrick};
		String[] t = null;
		File o = new File("cases.txt");
		PrintWriter pr = new PrintWriter(o);
		Person p = null;
		int index,tempmax;
		for(int x=0;x<128;x++){
			t=Turnouts.next();
			for(int y=0;y<4;y++){
				p = group[y];
				p.score(t);
			}
			tempmax = 0;
			index=0;
			for(int y=0;y<4;y++){
				if(group[y].score>tempmax){
					tempmax = group[y].score;
					index=y;
				}
			}
			group[index].wins++;
			pr.println("Person "+index+" wins in the following case:");
			for(int y=0;y<7;y++){
				pr.println(t[y]);
			}
			pr.println("");
			for(int y=0;y<4;y++){
				group[y].reset();
			}
		}
		for(int x=0;x<4;x++){
			System.out.println("PERSON "+x+" GOT "+group[x].wins+" WINS.");
		}
		pr.close();
		
	}
	
}
class Turnouts{
	public static int count=0;
	static String[] players = {"Kansas","Villanova","Oregon","Oklahoma","UNC","NotreDame","UVA","Syracuse"};
	
	public static String[] next(){
		int temp = count;
		String[] turnout = new String[7];
		for(int x=0;x<4;x++){
			if(temp%2==1){
				turnout[x] = players[2*x];
			}else{
				turnout[x] = players[2*x+1];
			}
			temp/=2;
		}
		if(temp%2==1){
			turnout[4]=turnout[0];
		}else{
			turnout[4]=turnout[1];
		}
		temp/=2;
		if(temp%2==1){
			turnout[5]=turnout[2];
		}else{
			turnout[5]=turnout[3];
		}
		temp/=2;
		if(temp%2==1){
			turnout[6]=turnout[4];
		}else{
			turnout[6]=turnout[5];
		}
		count++;
		return turnout;
	}
	
	
	
	
	
	
}
class Person{
	String[] picks;
	public int score;
	public int wins;
	final int currentscore;
	public void reset(){
		score =currentscore;
	}
	public Person(String[] picked,int c){
		currentscore=c;
		wins=0;
		score=c;
		picks = new String[7];
		for(int x=0;x<7;x++){
			picks[x]=picked[x];
		}
	}
	public void score(String[] turnout){
		for(int x=0;x<4;x++){
			if(turnout[x].equals(picks[x])){
				score+=80;
			}
		}
		for(int x=4;x<6;x++){
			if(turnout[x].equals(picks[x])){
				score+=160;
			}
		}
		if(turnout[6].equals(picks[6])){
			score+=320;
		}
			
	}
	
}

