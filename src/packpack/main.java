package packpack;

import java.io.*;

public class main {
	
	//DRIVER
	public static void main(String[] args) throws IOException {
		HexaTable listofhexa=new HexaTable();
		listofhexa.load("in.txt");
		listofhexa.printSolution();
	}

}
