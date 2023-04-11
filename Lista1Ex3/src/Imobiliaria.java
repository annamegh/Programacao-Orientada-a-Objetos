
public class Imobiliaria {

	private Imovel imoveis[] = new Imovel[15];
	private int quantImoveis;
	
	public void cadastrarImovel(Imovel i) {
		if(this.quantImoveis < 15) {
			imoveis[this.quantImoveis] = i;
			this.quantImoveis++;
		}
	}
	public Imovel[] getImoveis() {
		return imoveis;
	}
	public int getQuantImoveis() {
		return quantImoveis;
	}
	
	public String toString() {
		String s = "Imóveis: \n";
		for(int i=0; i< this.quantImoveis; i++) {
			s += imoveis[i].toString() + "\n";
		}
		return s;
	}
	
	public Imovel[] filtrarPorArea (float x) {
		Imovel im[] = new Imovel[15];
		Imovel aux = new Imovel();
		int k=0;
		
		for(int i=0; i< this.quantImoveis; i++) {
			if(imoveis[i].area(imoveis[i].getLargura(), imoveis[i].getComprimento()) >= x) {
				im[k] = imoveis[i];
				k++;
			}
		}
		
		for(int i=0; i<k; i++) {
			for(int j=0; j<k-1; j++) {
				if(im[j].getPreco() > im[j+1].getPreco()) {
					aux = im[j];
					im[j] = im[j+1];
					im[j+1] = aux;
				}
			}
		}
		
		return im;
	}
	
	
	
}
