package fantasy;

import java.util.ArrayList;
import java.util.Collections;

public class Fantasy {
	
	public static void main(String[] args){
		Person Chris = new Person("Chris",8);		Person Dave = new Person("Dave",6);
		Person Ryan = new Person("Ryan",5);			Person John = new Person("John",5);
		Person Patrick = new Person("Patrick",5);	Person Fano = new Person("Fano",2);
		Person Rich = new Person("Rich",2);			Person Joe = new Person("Joe",3);
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(Chris);people.add(Dave);people.add(John);people.add(Rich);
		people.add(Ryan);people.add(Fano);people.add(Joe);people.add(Patrick);
		Collections.sort(people);
		
		int n;
		int cutoff;
		//WEEK 10 TOGGLES
		int win1 = 1; // 1 if John wins, 0 if Dave wins
		int win2 = 1; // 1 if Ryan wins, 0 if Patrick wins
		int win3 = 0; // 1 if Fano wins, 0 if Rich wins
		int win4 = 1; // 1 if Joe wins,  0 if Chris wins
		int temp;
		for(int j=0;j<16;j++){
			System.out.println("*********************************************");
			temp = j;
		System.out.println("Case "+(temp+1)+" ");
//		//WEEK 11 TOGGLES
//		int win5 = 2; // 1 if John wins, 0 if Joe wins
//		int win6 = 2; // 1 if Dave wins, 0 if Rich wins
//		int win7 = 2; // 1 if Fano wins, 0 if Patrick wins
//		int win8 = 2; // 1 if Chris wins,  0 if Ryan wins
//		
//		
		int win5 = temp%2; temp/=2;
		int win6 = temp%2; temp/=2;
		int win7 = temp%2; temp/=2;
		int win8 = temp%2; 
		if(win5 == 0) System.out.print("Joe wins, "); else System.out.print("John wins, ");
		if(win6 == 0) System.out.print("Rich wins, "); else System.out.print("Dave wins, ");
		if(win7 == 0) System.out.print("Patrick wins, "); else System.out.print("Fano wins, ");
		if(win8 == 0) System.out.println("Ryan wins."); else System.out.println("Chris wins.");
		
		for(int data = 0; data<65536; data++){
			
			if((data/1) %2==win1 || (data/2)%2 == win2|| (data/4)%2 == win3 || (data/8)%2==win4){
				continue;
			}
			if((data/16) %2==win5 || (data/32)%2 == win6|| (data/64)%2 == win7 || (data/128)%2==win8){
				continue;
			}
			
			n = data;
			//WEEK 10
			if( n%2 == 0) John.wins(); else Dave.wins();   n/=2;
			if( n%2 == 0) Ryan.wins(); else Patrick.wins();n/=2;
			if( n%2 == 0) Fano.wins(); else Rich.wins();   n/=2;
			if( n%2 == 0) Joe.wins();  else Chris.wins();  n/=2;
			//WEEK 11
			if( n%2 == 0) John.wins(); else Joe.wins();    n/=2;
			if( n%2 == 0) Dave.wins(); else Rich.wins();   n/=2;
			if( n%2 == 0) Fano.wins(); else Patrick.wins();n/=2;
			if( n%2 == 0) Chris.wins();else Ryan.wins();   n/=2;
			//WEEK 12
			if( n%2 == 0) John.wins(); else Ryan.wins();   n/=2;
			if( n%2 == 0) Chris.wins();else Patrick.wins();n/=2;
			if( n%2 == 0) Dave.wins(); else Fano.wins();   n/=2;
			if( n%2 == 0) Rich.wins(); else Joe.wins();    n/=2;
			//WEEK 13
			if( n%2 == 0) John.wins(); else Patrick.wins();n/=2;
			if( n%2 == 0) Joe.wins();  else Dave.wins();   n/=2;
			if( n%2 == 0) Fano.wins(); else Chris.wins();  n/=2;
			if( n%2 == 0) Ryan.wins(); else Rich.wins();
			
			Collections.sort(people);
			
			cutoff = people.get(3).wins;
			for(Person p : people){
				if(p.wins>=cutoff){
					p.playoffs++;
					//if(p.name.equals("Rich")) System.out.println(Integer.toBinaryString(data));
				}if(p.wins<=cutoff){
					p.cuts++;
				}
				p.reset();
			}
		}
		for(Person p : people){
			if(p.playoffs==0&&!p.name.equals("Fano")){
				System.out.println(p.name+" can't make playoffs!");
				
			}else if(p.cuts == 0){
				System.out.println(p.name+" has clinched playoffs!");
				
			}
			p.playoffs=0;
			p.cuts=0;
		}
		
		
	}
	}
}

class Person implements Comparable<Person>{
	String name;
	int wins;
	int start_wins;
	int playoffs;
	int cuts;
	
	public Person(String n, int w){
		name = n;
		wins = w;
		start_wins = w;
		playoffs = 0;
		cuts = 0;
	}
	public int compareTo(Person other) {
		// TODO Auto-generated method stub
		
		if(wins>other.wins){
			return -1;
		}else if(wins<other.wins){
			return 1;
		}return 0;
	}
	public void wins(){
		wins++;
		//System.out.println(name+" wins!");
	}
	public void reset(){
		wins = start_wins;
	}
	
}
