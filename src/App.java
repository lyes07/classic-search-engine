import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class App {
    private static Scanner scanner;
    public static void main(String[] args) throws Exception {
    	ArrayList<String>  all = new ArrayList<>();

		File doc=new File("doc");
    	int docNumber=doc.list().length;

		@SuppressWarnings("unchecked")
		ArrayList<String>[]  d =(ArrayList<String>[]) new ArrayList[docNumber];
		for(int i = 0 ;i<docNumber;i++){
			d[i] = new ArrayList<String>();
		}
    	int i = 0;
        // reading the Files d1,d2,d3,...,dn
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
        
        for (String string : finalAll) {
			System.out.println(string);
		} 

		System.out.println("-----------------------------");

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

    	for(int k=0;k<length;k++){
			for(int j = 0; j<docNumber+1;j++){
				System.out.print(ft[k][j]+"    ");
			}
			System.out.println();
		} 

		System.out.println("-----------------------------");

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
			tf[j][docNumber] = Math.log10((double)docNumber/num);
		}

		for(int k=0;k<length;k++){
			for(int j = 0; j<docNumber+1;j++){
				System.out.print(tf[k][j]+"    ");
			}
			System.out.println();
		} 
		

		
    }
}
