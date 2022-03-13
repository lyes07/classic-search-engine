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
		
		String[] notValide = {"l","L","du","sur","des","par","et","le","sont","les","la","R","est","La","les","pour","de","Les","plus","par","petites","a", "à", "â", "abord", "afin", "ah", "ai", "aie", "ainsi", "allaient", "allo", "allô", "allons", "après", "assez", "attendu", "au", "aucun", "aucune", "aujourd", "aujourd'hui", "auquel", "aura", "auront", "aussi", "autre", "autres", "aux", "auxquelles", "auxquels", "avaient", "avais", "avait", "avant", "avec", "avoir", "ayant", "b", "bah", "beaucoup", "bien", "bigre", "boum", "bravo", "brrr", "c", "R", "ça", "car", "ce", "ceci", "cela", "celle", "celle-ci", "celle-là", "celles", "celles-ci", "celles-là", "celui", "celui-ci", "celui-là", "cent", "cependant", "certain", "certaine", "certaines", "certains", "certes", "ces", "cet", "cette", "ceux", "ceux-ci", "ceux-là", "chacun", "chaque", "cher", "chère", "chères", "chers", "chez", "chiche", "chut", "ci", "cinq", "cinquantaine", "cinquante", "cinquantième", "cinquième", "clac", "clic", "combien", "comme", "comment", "compris", "concernant", "contre", "couic", "crac", "d", "da", "dans", "de", "debout", "dedans", "dehors", "delà", "depuis", "derrière", "des", "dès", "désormais", "desquelles", "desquels", "dessous", "dessus", "deux", "deuxième", "deuxièmement", "devant", "devers", "devra", "différent", "différente", "différentes", "différents", "dire", "divers", "diverse", "diverses", "dix", "dix-huit", "dixième", "dix-neuf", "dix-sept", "doit", "doivent", "donc", "dont", "douze", "douzième", "dring", "du", "duquel", "durant", "e", "effet", "eh", "elle", "elle-même", "elles", "elles-mêmes", "en", "encore", "entre", "envers", "environ", "es", "ès", "est", "et", "etant", "étaient", "étais", "était", "étant", "etc", "été", "etre", "être", "eu", "euh", "eux", "eux-mêmes", "excepté", "f", "façon", "fais", "faisaient", "faisant", "fait", "feront", "fi", "flac", "floc", "font", "g", "gens", "h", "ha", "hé", "hein", "hélas", "hem", "hep", "hi", "ho", "holà", "hop", "hormis", "hors", "hou", "houp", "hue", "hui", "huit", "huitième", "hum", "hurrah", "i", "il", "ils", "importe", "j", "je", "jusqu", "jusque", "k", "l", "la", "là", "laquelle", "las", "le", "lequel", "les", "lès", "lesquelles", "lesquels", "leur", "leurs", "longtemps", "lorsque", "lui", "lui-même", "m", "ma", "maint", "mais", "malgré", "me", "même", "mêmes", "merci", "mes", "mien", "mienne", "miennes", "miens", "mille", "mince", "moi", "moi-même", "moins", "mon", "moyennant", "n", "na", "ne", "néanmoins", "neuf", "neuvième", "ni", "nombreuses", "nombreux", "non", "nos", "notre", "nôtre", "nôtres", "nous", "nous-mêmes", "nul", "o", "o|", "ô", "oh", "ohé", "olé", "ollé", "on", "ont", "onze", "onzième", "ore", "ou", "où", "ouf", "ouias", "oust", "ouste", "outre", "p", "paf", "pan", "par", "parmi", "partant", "particulier", "particulière", "particulièrement", "pas", "passé", "pendant", "personne", "peu", "peut", "peuvent", "peux", "pff", "pfft", "pfut", "pif", "plein", "plouf", "plus", "plusieurs", "plutôt", "pouah", "pour", "pourquoi", "premier", "première", "premièrement", "près", "proche", "psitt", "puisque", "q", "qu", "quand", "quant", "quanta", "quant-à-soi", "quarante", "quatorze", "quatre", "quatre-vingt", "quatrième", "quatrièmement", "que", "quel", "quelconque", "quelle", "quelles", "quelque", "quelques", "quelqu'un", "quels", "qui", "quiconque", "quinze", "quoi", "quoique", "r", "revoici", "revoilà", "rien", "s", "sa", "sacrebleu", "sans", "sapristi", "sauf", "se", "seize", "selon", "sept", "septième", "sera", "seront", "ses", "si", "sien", "sienne", "siennes", "siens", "sinon", "six", "sixième", "soi", "soi-même", "soit", "soixante", "son", "sont", "sous", "stop", "suis", "suivant", "sur", "surtout", "t", "ta", "tac", "tant", "te", "té", "tel", "telle", "tellement", "telles", "tels", "tenant", "tes", "tic", "tien", "tienne", "tiennes", "tiens", "toc", "toi", "toi-même", "ton", "touchant", "toujours", "tous", "tout", "toute", "toutes", "treize", "trente", "très", "trois", "troisième", "troisièmement", "trop", "tsoin", "tsouin", "tu", "u", "un", "une", "unes", "uns", "v", "va", "vais", "vas", "vé", "vers", "via", "vif", "vifs", "vingt", "vivat", "vive", "vives", "vlan", "voici", "voilà", "vont", "vos", "votre", "vôtre", "vôtres", "vous", "vous-mêmes", "vu", "w", "x", "y", "z", "zut"};

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
		int z = 25 + ((docNumber+1)*2)+1 +(3*(docNumber)+1) + (5*(docNumber)+1)+5;
		for(int h = 0; h<z; h++){
			System.out.print("-");
		}
		System.out.println();
		i=0;
		for (String s : finalAll) {
			System.out.print(ANSI_BLUE + s + ANSI_RESET);
			int x = 24-s.length();
			while(x>0){
				if(x==2){
					System.out.print("|");
					x--;
					continue;
				}
				System.out.print(" ");
				x--;
			}
			for(int k=0;k<docNumber-1;k++){
				System.out.print(ANSI_GREEN+(ft[i][k]+" | ")+ANSI_RESET);
			} 
			System.out.print(ANSI_GREEN+(ft[i][docNumber-1])+ANSI_RESET);
			System.out.print(" | ");
			System.out.print(ANSI_YELLOW+(ft[i][docNumber])+ANSI_RESET);
			System.out.print(" | ");
			for(int j = 0; j<docNumber-1;j++){
				System.out.print(ANSI_CYAN+(tf[i][j]+" | ")+ANSI_RESET);
			}
			System.out.print(ANSI_CYAN+(tf[i][docNumber-1])+ANSI_RESET);
			System.out.print(" | ");
			System.out.print(ANSI_PURPLE+(tf[i][docNumber])+ANSI_RESET);
			System.out.print(" | ");
			i++;
			System.out.println();
			for(int h = 0; h<z; h++){
				System.out.print("-");
			}
			System.out.println();
		}
		System.out.println();
		
		// take he user input
		System.out.print("Enter the Search Query : ");
		scanner = new Scanner(System.in);
		String[] q = scanner.nextLine().split(" ");

		//calculat R for each document
		double[] r = new double[docNumber];
		for(i=0; i<docNumber; i++){
			for (String s : q) {
				int k = 0;
				for (String elem : finalAll) {
					if(s.equals(elem)){
						r[i] += tf[k][i]*tf[k][docNumber];
						continue;
					}
					k++;
				}
			}
		}	
		
		// ranking the documents
		int[] rank = new int[docNumber];
		for(i=0; i<docNumber; i++){
			int max=0;
			for(int j=0; j<docNumber;j++){
				if(r[max]<r[j])max=j;
			}
			rank[i] = max + 1;
			r[max]=-1;
		}

		System.out.println("The documents rank is :");
		for(i=0; i<docNumber; i++){
			System.out.println("\td"+rank[i]);
		}

    }
}
