package models;


public class Cliente {
	//Atributos
		private int matricula;
		private String nome;
		private String endereco;
		private String modalidade;
		

		//Construtores
		public Cliente() 
		{
			super();
		}

		

		public int getMatricula() {
			return matricula;
		}
		public void setMatricula(int matricula) {
			this.matricula = matricula;
		}


		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}


		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}


		public String getModalidade() {
			return modalidade;
		}
		public void setModalidade(String modalidade) {
			this.modalidade = modalidade;
		}	
		
}
