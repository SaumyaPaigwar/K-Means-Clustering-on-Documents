package Clustering;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
/*
 * Author : Saumya Paigwar
 * Date : 20/02/2017
 * For view output remove comments
 */
public class kMeans {
	
	public static String[] arr = {"a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", 
								"again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", 
								"always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", 
								"anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", 
								"asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", 
								"been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", 
								"beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", 
								"certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", 
								"containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did",
								"didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight",
								"either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", 
								"everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following",
								"follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
	static ArrayList<String> stopwords=new ArrayList<>( Arrays.asList(arr));
	static Map<String,String> docContent=new HashMap<String,String>();
	static Map<String,Double> docRank=new HashMap<String,Double>();
	static Set<String> keySet=new HashSet<String>();
	static Map<String,Double> cluster=new HashMap<String,Double>();
	static Set<String> clusterSet=new HashSet<String>();
	static Map<String,ArrayList> clusterGroups=new HashMap<String,ArrayList>();
	static Set<String> clusterGroupKeys=new HashSet<String>();
	
	
	public static void main(String[] args) throws Exception
	{
		kMeans kmean=new kMeans();
		Scanner sc=new Scanner(System.in);

		docContent=kmean.CreateDocContent();
		docRank=kmean.CalculateRank();
		
		//System.out.println("doc Content is "+docContent);
		//System.out.println("doc rank is "+docRank);
		
		System.out.println("Enter value of k");
		int k=sc.nextInt();
		kmean.createCluster(k);
		//System.out.println("cluster "+cluster);
		for(int i=0;i<1000;i++)
			{
			kmean.assignCluster();
			System.out.println("Upadted clusters "+i+" is "+cluster);
			//Thread.sleep(1000);
			}
		Thread.sleep(20000);
		kmean.finalClusterDocumentFolder();
		//System.out.println(clusterGroups);
		//System.out.println("Upadted clusters "+cluster);
		
	}
	
	//function to create final clusters and store the document in corresponding cluster's folders
	public void finalClusterDocumentFolder() throws IOException
	{
		clusterSet=cluster.keySet();
		
		Iterator itr=keySet.iterator();
		//int n=1;
		while(itr.hasNext())
		{
			String docname=(String)itr.next();
			if(!cluster.containsKey(docname))
			{
				Iterator citr=clusterSet.iterator();
				double min=Integer.MAX_VALUE;
				String destCluster="";
				String clust_name="";
				while(citr.hasNext())
				{
					 clust_name=(String)citr.next();
					double rank=(double)cluster.get(clust_name);
					if(min > Math.abs(rank-docRank.get(docname)) )
					{
						min=Math.abs(rank-docRank.get(docname));
						destCluster=clust_name;
					}
				}
				
				FileReader fr=new FileReader("AllDocuments\\"+docname+".txt");
				BufferedReader br=new BufferedReader(fr);
				String str=br.readLine();
				FileWriter file=new FileWriter("Clusters\\"+destCluster+"\\"+docname+".txt");
				BufferedWriter wr=new BufferedWriter(file);
				wr.write(str);
				wr.flush();
				
			}
			//System.out.println("Updating cluster "+docname);
			
		}
	}
	
	//function to update rank of each clusters after adding new documents to cluster
	public void UpdateRank()
	{
		Set<String> clusterGroupKeys=clusterGroups.keySet();
		Iterator itr=clusterGroupKeys.iterator();
		while(itr.hasNext())
		{
			String clusterName=(String)itr.next();
			ArrayList<Double> value= clusterGroups.get(clusterName);
			Iterator vitr=value.iterator();
			double num=0;
			while(vitr.hasNext())
			{
				num+=(double)vitr.next();
			}
			double updateRank=num/value.size();
			if(value.size()>0)
			cluster.put(clusterName, updateRank);
			
		}
	}
	
	//function for assigning all documents to the nearest cluster
	public void assignCluster()
	{
		clusterSet=cluster.keySet();
				
		Iterator itr=keySet.iterator();
		while(itr.hasNext())
		{
			String docname=(String)itr.next();
			if(!cluster.containsKey(docname))
			{
				Iterator citr=clusterSet.iterator();
				double min=Integer.MAX_VALUE;
				String destCluster="";
				String clust_name="";
				while(citr.hasNext())
				{
					 clust_name=(String)citr.next();
					double rank=(double)cluster.get(clust_name);
					if(min > Math.abs(rank-docRank.get(docname)) )
					{
						min=Math.abs(rank-docRank.get(docname));;//
						destCluster=clust_name;
					}
				}
				// arraylist of cluster
				ArrayList al=clusterGroups.get(destCluster);
				al.add(docRank.get(docname));
				
			}
			//System.out.println("Updating cluster "+docname);
		}
		UpdateRank();
	}
	
	//function for creating k random clusters
	public void createCluster(int k) throws IOException
	{
		Set<Integer> clusterNum=new HashSet<Integer>();
		for(int i=0;i<k;i++)
		{
			Random r=new Random();
			int num=r.nextInt(docContent.size())+1;
			while(clusterNum.contains(num))
				num=r.nextInt(docContent.size())+1;
			clusterNum.add(num);
			//System.out.println(num);
			String name="doc"+num+"";
			//System.out.println(name);
			double rank=docRank.get(name);
			cluster.put(name, rank);	
			clusterGroups.put(name, new ArrayList<Double>());
			System.out.println("Creating cluster for "+name );
			
			File file=new File("Clusters//"+name+"//");
			if(!file.exists())
				file.mkdirs();
			
			
			FileReader fr=new FileReader("AllDocuments\\"+name+".txt");
			BufferedReader br=new BufferedReader(fr);
			String str=br.readLine();
			FileWriter fw=new FileWriter("Clusters\\"+name+"\\"+name+".txt");
			BufferedWriter wr=new BufferedWriter(fw);
			wr.write(str);
			wr.flush();
			
		}
		
	}
	
	
	//function to calculate rank of each document and store it in a map
	public Map<String,Double> CalculateRank()
	{
		keySet=docContent.keySet();
		Iterator itr=keySet.iterator();
		
		while(itr.hasNext())
		{
			String docname=(String)itr.next();
			String sent=docContent.get(docname);
			String[] words=sent.split("\\s+");
			double rank=0;
			for(String w: words)
			{
				double tf=TermFreq(sent, w);
				double idf=InverseDocumentFreq(w);
				double tf_idf=tf*idf;
				rank+=tf_idf;
			}
			docRank.put(docname, rank);
			System.out.println(docname +"rank is "+rank);
		}
		return docRank;
	}
	
	
	//function to create map of document name and document content
	public Map<String,String> CreateDocContent() throws IOException
	{
		for(int i=1;i<=1000;i++)
		{
			String name="AllDocuments\\doc"+i+".txt";
			String dockey="doc"+i+"";
			FileReader fr=new FileReader(name);
			BufferedReader br=new BufferedReader(fr);
			String s="";
			String str=br.readLine() ;
			//System.out.println(str);
			str=str.replaceAll("\\s+", " ");
			String[] strArr=str.split(" ");
			for(String s1: strArr)
			{
				if(!stopwords.contains(s1))
					s+=s1+" ";
			}
				
			docContent.put(dockey, s);
			System.out.println("Document "+i+" is put in map");
			
		}		
		return docContent;
	}
	
	
	//function to calculate Term frequency
	public int TermFreq(String sentence,String term)
	{
		String[] str=sentence.split("\\s+");
		int cnt=0;
		for(String s: str)
			if(s.equals(term))
				cnt++;
		return cnt;
	}
	
	
	//function to calculate Inverse Document Frequency
	public double InverseDocumentFreq(String term)
	{
		Set<String> keys=docContent.keySet();
		Iterator itr=keys.iterator();
		int cnt=0;
		while(itr.hasNext())
		{
			String key=(String)itr.next();
			if(docContent.get(key).contains(term))
				cnt++;
		}
		
		return 1+Math.log(docContent.size()/cnt);
	}
	
	
}
