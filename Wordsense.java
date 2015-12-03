

/* author: Yogesh K Jeswani */


import java.util.HashSet;
import java.util.Iterator;

import edu.smu.tspell.wordnet.*;

public class Wordsense {
	
  static HashSet<String> content=new HashSet<>();
  
  static HashSet<String> defgloss=new HashSet<>();
  
  static int maxoverlap=0;
  
  static StringBuffer best= new StringBuffer();
  
  static StringBuffer bestsense=new StringBuffer();
  
  static String temp1=null;
  
  static int compute(HashSet<String> content, HashSet<String> defgloss){
	  
	 Iterator itr=content.iterator();
	 int count=0;
	 
	 System.out.println("The overlapping words are/is ");
	 
	 while(itr.hasNext()){
		 
		 String comp=(String) itr.next();
		 
		 if(defgloss.contains(comp)){
			System.out.print(comp+" ");
			 count++;
		 }
		 
		 
	 }
	 
	 System.out.println();
	 System.out.println();
	  
	 return count;
  }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("wordnet.database.dir", "C:/Users/yjeswani/Downloads/WordNet-3.0/WordNet-3.0/dict");
		
			
			
		String context="The bank can guarantee deposits will eventually cover future tuition costs because it invests in adjustable-rate mortgage securities";
		  //String context="The bank of the river contains pebbles";
			String [] con=context.split(" ");
			
			
			for(int a=0;a<con.length;a++){
				//System.out.println(con[a]);
				/*if(con[a].equals("it")|| con[a].equals("can")|| con[a].equals("in")){
					
				}else*/
				content.add(con[a]);
			}
			WordNetDatabase wd = WordNetDatabase.getFileInstance();
			Synset[] synsets = wd.getSynsets("bank");
			//System.out.println(synsets.length);
			
			for (int i = 0; i < synsets.length; i++)
			{
				System.out.println("");
				
				String[] wordForms = synsets[i].getWordForms();
				
				String defn= synsets[i].getDefinition();
				
				defn=defn.replace("(","");
				defn=defn.replace(")","");
				
				String definition[]=defn.split(" ");
				
				for(int j=0;j<definition.length;j++){
					//System.out.println(definition[j]);
					defgloss.add(definition[j]);
				}
				
				String arr[]=synsets[i].getUsageExamples();
				
				for(int k=0;k<arr.length;k++){
					
					arr[k]=arr[k].replace("\"", "");
					
					//System.out.println(arr[k]);
					
					String temp[] =arr[k].split(" ");
					
					for(String s: temp){
						//System.out.println(s);
						defgloss.add(s);
					}
				}
				
				int temp=0;
				
				temp=compute(content,defgloss);
				
				//System.out.println(temp);
				
				System.out.println("The Overlap for the Sense below is ");
				
				for (int j = 0; j < wordForms.length; j++)
				{
					System.out.print((j > 0 ? ", " : "") +
							wordForms[j]);
					
					best.append(wordForms[j]+" ");
				}
				
				best.append(":"+synsets[i].getDefinition());
				System.out.print(": " + synsets[i].getDefinition());
				System.out.print("   "+ temp);
				System.out.println();
				System.out.println();
				
				if(maxoverlap<temp){
					maxoverlap=temp;
					
					//bestsense=best;
					temp1=best.toString();
					//System.out.println("The BestSense is "+bestsense);
					best.delete(0, best.length());
					//System.out.println(best);
					
				}
				
				
				defgloss.clear();
				
		}
			
			System.out.println("The best sense for given context is :"+ temp1);
		
		}

}
