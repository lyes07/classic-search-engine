import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
public class App {
    private static Scanner scanner;
    public static void main(String[] args) throws Exception {
    	ArrayList<String>  all = new ArrayList<>();
    	ArrayList<String>  d1 = new ArrayList<>();
    	ArrayList<String>  d2 = new ArrayList<>();
    	ArrayList<String>  d3 = new ArrayList<>();
    	ArrayList<String>  d4 = new ArrayList<>();

		File doc=new File("doc");
    	int docNumber=doc.list().length;
		
		ArrayList<String>[]  dAll = new ArrayList[docNumber];
		for (ArrayList<String> a : dAll) {
			a = new ArrayList<>();
		}
    	int i = 1;
        // reading the Files d1,d2,d3,d4
    	for (; i <=4; i++) {
    		ArrayList<String>  d=d1;
    		if (i == 2) d=d2;
    		if (i == 3) d=d3;
    		if (i == 4) d=d4;
    		scanner = new Scanner(new File("doc/d"+i+".txt"));
            String[] d1FileAsString = scanner.nextLine().split(" ");  
            for (String string : d1FileAsString) {
    			if (string.split("'").length == 2) {
    				String[] tempString = string.split("'");
    				all.add(tempString[0]);
    				all.add(tempString[1]);
    				d.add(tempString[0]);
    				d.add(tempString[1]);
    				continue;
    			}
    			if (string.split("-").length == 2) {
    				String[] tempString = string.split("-");
    				all.add(tempString[0]);
    				all.add(tempString[1]);
    				d.add(tempString[0]);
    				d.add(tempString[1]);
    				continue;
    			}
    			all.add(string);
    			d.add(string);
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
		int[][] ft = new int[length][5];
		for(int j = 0;j<4;j++){
			int k = 0;
			for (String string : finalAll) {
				ArrayList<String> temp=d1;
				if(j==1)temp=d2;
				if(j==2)temp=d3;
				if(j==3)temp=d4;
				for (String s : temp) {
					if(string.equals(s)){
						ft[k][j]+=1;
						ft[k][4]+=1;
					}
				}
				k++;
			}
		}

    	for(int k=0;k<length;k++){
			for(int j = 0; j<5;j++){
				System.out.print(ft[k][j]+"    ");
			}
			System.out.println();
		} 

		System.out.println("-----------------------------");

        double tf[][] = new double[length][5];
		i=0;
		for(;i<4;i++){
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
			for(int k=0;k<4;k++){
				if(tf[j][k]!= 0.0){
					num+=1;
				}
			}
			tf[j][4] = Math.log10((double)4/num);
		}

		for(int k=0;k<length;k++){
			for(int j = 0; j<5;j++){
				System.out.print(tf[k][j]+"    ");
			}
			System.out.println();
		} 
		

		
    }
}
