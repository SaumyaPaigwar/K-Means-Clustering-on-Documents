

package Filehandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

// Create documents for all document present in training data set 
public class createDocument_in_folders {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			FileReader fr=new FileReader("Data\\training.txt");
			BufferedReader br=new BufferedReader(fr);
			String str=br.readLine();
			int j=1;
			while(str!=null)
			{
				//System.out.println(str);
				String name="AllDocuments\\doc"+j+".txt";
				j++;
				FileWriter file=new FileWriter(name);
				BufferedWriter out=new BufferedWriter(file);
				out.write(str);
				out.flush();
				file.close();
				str="";
				str=br.readLine();
			}
			//System.out.println(j);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
}
