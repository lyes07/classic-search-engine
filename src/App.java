import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class App {
    private static Scanner scanner;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
    public static void main(String[] args) throws Exception {
    	ArrayList<String>  all = new ArrayList<>();

		// the number of documents dynamicly
		File doc=new File("doc");
    	int docNumber=doc.list().length;

		@SuppressWarnings("unchecked")
		ArrayList<String>[]  d =(ArrayList<String>[]) new ArrayList[docNumber];
		for(int i = 0 ;i<docNumber;i++){
			d[i] = new ArrayList<String>();
		}

		// reading the Files d1,d2,d3,...,dn an extracting the words
    	int i = 0;
    	for (; i <docNumber; i++) {
    		ArrayList<String>  temp=d[i];
    		scanner = new Scanner(new File("doc/d"+(i+1)+".txt"));
            String[] d1FileAsString = scanner.nextLine().split(" ");  
            for (String string : d1FileAsString) {
    			if (string.split("'").length == 2) {
    				String[] tempString = string.split("'");
    				all.add(tempString[0]);
    				all.add(tempString[1]);
    				temp.add(tempString[0]);
    				temp.add(tempString[1]);
    				continue;
    			}
    			if (string.split("-").length == 2) {
    				String[] tempString = string.split("-");
    				all.add(tempString[0]);
    				all.add(tempString[1]);
    				temp.add(tempString[0]);
    				temp.add(tempString[1]);
    				continue;
    			}
    			all.add(string);
    			temp.add(string);
    		}
		}
        
		LinkedHashSet<String> finalAll = new LinkedHashSet<String>();
		finalAll.addAll(all);
		
		String[] notValide = {"l","L","du","sur","des","par","et","le","sont","les","la","R","est","La","les","pour","de","Les","plus","par","petites"};

		for (String string : notValide) {
			if(finalAll.contains(string)){
				finalAll.remove(string);
			}
		}

		int length = finalAll.size();
		int[][] ft = new int[length][docNumber+1];
		for(int j = 0;j<docNumber;j++){
			int k = 0;
			for (String string : finalAll) {
				ArrayList<String> temp=d[j];
				for (String s : temp) {
					if(string.equals(s)){
						ft[k][j]+=1;
						ft[k][docNumber]+=1;
					}
				}
				k++;
			}
		}

        double tf[][] = new double[length][docNumber+1];
		i=0;
		for(;i<docNumber;i++){
			int max=0;
			for(int j = 0; j<length;j++){
				if(ft[j][i]>max)max = ft[j][i];
			}
			for(int j = 0; j<length;j++){
				tf[j][i] = (double)ft[j][i]/max;
			}
		}

		for(int j = 0; j<length;j++){
			int num = 0;
			for(int k=0;k<docNumber;k++){
				if(tf[j][k]!= 0.0){
					num+=1;
				}
			}
			tf[j][docNumber] = (double)((int)(Math.log10((double)docNumber/num)*1000))/1000;
		}

		// printing the table
		System.out.println();
		i=0;
		for (String s : finalAll) {
			System.out.print(ANSI_BLUE + s + ANSI_RESET);
			int x = 20-s.length();
			while(x>0){
				System.out.print(" ");
				x--;
			}
			for(int k=0;k<docNumber;k++){
				System.out.print(ANSI_GREEN+(ft[i][k]+"     ")+ANSI_RESET);
			} 
			System.out.print(ANSI_YELLOW+(ft[i][docNumber]+"     ")+ANSI_RESET);
			for(int j = 0; j<docNumber;j++){
				System.out.print(ANSI_CYAN+(tf[i][j]+"     ")+ANSI_RESET);
			}
			System.out.print(ANSI_PURPLE+(tf[i][docNumber]+"     ")+ANSI_RESET);
			i++;
			System.out.println();
		}
		System.out.println();
		System.out.print("Enter the Search Query : ");
		scanner = new Scanner(System.in);
		String q = scanner.nextLine();
    }
}
