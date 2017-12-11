package klijent;

import java.io.PrintWriter;
import java.net.Socket;

public class NitPisanje extends Thread {

	private PrintWriter pw;
	private Poruka p;
	private Socket s;
	
	public NitPisanje(Socket s,Poruka p,PrintWriter pw){
		this.s=s;this.p=p;this.pw=pw;
		start();
	}
	public void run(){
		while(true){
		pw.println(p.getPoruka());
		}
	}
}
