package apresentacao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import dados.Musica;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Play {

	public Play(Musica m) {
		try {
			FileInputStream fileInputStream = new FileInputStream(m.getArquivo());
			Player player = new Player(fileInputStream);
			System.out.println("Song is playing...");
			player.play(300);
			} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			} 
		catch (JavaLayerException e) {
			e.printStackTrace();
			}
	}

}
