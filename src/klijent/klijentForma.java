package klijent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class klijentForma extends JFrame implements adresa{

	private JPanel contentPane;
	private JTextField TFNIK;
	private JTextField TFPOSALJI;
	private String user;
	JTextArea textArea;
	Poruka p;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					klijentForma frame = new klijentForma();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public klijentForma() {
		setTitle("KLIJENT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 517, 129);
		contentPane.add(panel);
		panel.setLayout(null);
		
		TFNIK = new JTextField();
		TFNIK.setBounds(41, 37, 226, 22);
		panel.add(TFNIK);
		TFNIK.setColumns(10);
		
		JButton btnNIK = new JButton("NIK\r\n");
		btnNIK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			user=	TFNIK.getText();
			System.out.println(user);
			
			if(user!=null && !user.isEmpty()){
				 //p=new Poruka();
			try {
				p=new Poruka();
				Socket s = new Socket(adresa,PORT);
				 BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter pw=new PrintWriter(s.getOutputStream(),true);//jebeno true odredjuje da li ce se prikazati na server formi 
				
				pw.println(user);
				NitPisanje np=new NitPisanje(s, p, pw);
				NitCitanje nc=new NitCitanje(s, br, p, textArea);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			}}
		});
		btnNIK.setBounds(358, 36, 97, 25);
		panel.add(btnNIK);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 322, 517, 106);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		TFPOSALJI = new JTextField();
		TFPOSALJI.setColumns(10);
		TFPOSALJI.setBounds(40, 40, 226, 22);
		panel_1.add(TFPOSALJI);
		
		JButton btnPosaljiPoruku = new JButton("POSALJI PORUKU");
		btnPosaljiPoruku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String poruka=TFPOSALJI.getText().toString();
				textArea.append("ja "+poruka+"\n");
				p.setPoruka(poruka);
			}
		});
		btnPosaljiPoruku.setBounds(328, 39, 145, 25);
		panel_1.add(btnPosaljiPoruku);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 155, 517, 154);
		contentPane.add(scrollPane);
		
		 textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
}
