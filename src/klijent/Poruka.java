package klijent;

public class Poruka {

	private String poruka;
	
	public synchronized String getPoruka(){
		try {
			System.out.println("ceka se neko");
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return poruka;
	}
	public synchronized void setPoruka(String p){
		poruka=p;
		notify();
	}
}
