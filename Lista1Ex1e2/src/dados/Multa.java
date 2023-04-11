package dados;

public class Multa {

	private int atraso;
	private float valor;
	private boolean statusMulta = false;
	
	public Multa() {
		
	}
	public Multa(int atraso) {
		this.atraso = atraso;
		this.valor = atraso * 2;
		this.statusMulta = true; //true para multa não paga
	}
	
	public int getAtraso() {
		return atraso;
	}
	public void setAtraso(int atraso) {
		this.atraso = atraso;
		this.valor = this.atraso * 2;
		this.statusMulta = true;
		
		if(atraso == 0) {
			this.statusMulta = false;
		}
	}
	public float getValor() {
		return valor;
	}
	public void setValor() {
		this.valor = this.atraso * 2;
	}
	public boolean isStatus() {
		return statusMulta;
	}
	public void setStatus(boolean status) {
		this.statusMulta = status;
	}
	
	public String toString() {
		return "Atraso: " + atraso + " dias, valor: R$" + valor + ", multa paga: " + statusMulta + "";
	}
	
	public boolean equals (Object objeto) {
		Multa multa;
		if( ! (objeto instanceof Multa)){
			return false;
		}
		multa = (Multa) objeto;
		if(multa.equals(multa)){
			return true;
		}
		return false;
	}
	
	
}
