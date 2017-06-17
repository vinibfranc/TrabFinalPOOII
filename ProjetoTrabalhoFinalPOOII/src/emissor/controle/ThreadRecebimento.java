package emissor.controle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import emissor.visao.JanelaEmissor;

//Classe com l�gica do fluxo de execu��o respons�vel por receber os arquivos do diret�rio
public class ThreadRecebimento implements Runnable{
	private Socket socket;
	private JanelaEmissor je;
	
	// construtor da Thread
	public ThreadRecebimento(Socket socket, JanelaEmissor je) {
		super();
		this.socket = socket;
		this.je = je;
	}

	// m�todo de execu��o da Thread
	public void run() {
		recebeArquivos();
	}
	
	// recebe os arquivos com a utiliza��o da classe ObjectInputStream e do m�todo readObject
	public void recebeArquivos() {
		ObjectInputStream in = null;

		try {
			in = new ObjectInputStream(socket.getInputStream());
			JOptionPane.showMessageDialog(je,(String)in.readObject(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(je,"Erro ao enviar os arquivos", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
