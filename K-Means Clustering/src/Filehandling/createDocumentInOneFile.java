package Filehandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


// For creating single file containing documents belong to same class
public class createDocumentInOneFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			FileReader fr=new FileReader("Data\\training.txt");
			BufferedReader br=new BufferedReader(fr);
			String str=br.readLine();
			int j=1;
			while(str!=null)
			{
				String out=str+"\n";
				String s=str.replaceAll("\\s+", " ");
				String[] arr=s.split(" ");
				String filename = arr[0];
				FileWriter file=new FileWriter("Documents\\"+filename+".txt", true);
				BufferedWriter wtr=new BufferedWriter(file);
				file.write(str);
				file.write("\n");
				file.flush();
				str=br.readLine();
			}
			System.out.println(j);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
}



