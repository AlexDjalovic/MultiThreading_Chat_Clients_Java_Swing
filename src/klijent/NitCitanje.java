package klijent;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextArea;

public class NitCitanje extends Thread {

	private BufferedReader br;
	private Poruka p;
	private Socket s;
	private JTextArea ta;
	
	
	public NitCitanje(Socket so,BufferedReader buf,Poruka por,JTextArea tr){
		s=so;br=buf;p=por;ta=tr;
		start();
	}
	public void run(){
		String nekaPoruka;
		while(true){
			try {
				nekaPoruka=br.readLine();
				System.out.println("u niti kod klijenta za citanje"+nekaPoruka);
				if(nekaPoruka!=null &&!nekaPoruka.isEmpty()){
					ta.append(nekaPoruka+"\n");
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
}
