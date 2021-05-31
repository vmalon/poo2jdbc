package application;

import java.util.Date;
import java.util.Scanner;
import entities.Aluno;
import jdbc.AlunoJDBC;
import jdbc.db;

public class Programa {

	public static void main(String[] args) {
		
        try {
        	
            int opcao = 0;
            Scanner console = new Scanner(System.in);
            
            do {
                System.out.print("######## Menu ########"
                                 + "\n1- Cadastrar"
                                 + "\n2- Listar"
                                 + "\n3- Alterar"
                                 + "\n4- Excluir"
                                 + "\n5- Sair");
                System.out.print("\n\tOpção: ");
                opcao = Integer.parseInt(console.nextLine());
                System.out.println("\n\n\n\n");
                
                if (opcao == 1){
                    
                    Aluno a = new Aluno();
                    AlunoJDBC acao = new AlunoJDBC();
                    
                    System.out.print("\n*** Cadastrar Aluno ***\n\r");
                    System.out.print("Nome: ");
                    a.setNome(console.nextLine());
                    System.out.print("Sexo: ");
                    a.setSexo(console.nextLine());
                    System.out.print("Data de nascimento: ");
                    a.setDt_nasc( new Date(console.nextLine()) );
                    
                    acao.salvar(a);
                    console.nextLine();
                    System.out.println("\n\n\n\n");
                }
                
                if(opcao == 2) {
                	  AlunoJDBC acao = new AlunoJDBC();
                	  var alunos = acao.listar();
                	  for(Aluno aluno : alunos) {
                		  System.out.println("Id: " + aluno.getId());
                		  System.out.println("Nome: " + aluno.getNome());
                		  System.out.println("Sexo: " + aluno.getSexo());
                		  System.out.println("Data Nascimento: " + aluno.getDt_nasc() + "\n");
                	  }
                }
                if(opcao == 3) {
              	  	  AlunoJDBC acao = new AlunoJDBC();
              	  	  var aluno = new Aluno();
              	  	  System.out.println("Digite a matrícula do Aluno: ");
              	  	  aluno.setId(Integer.parseInt(console.nextLine()));
              	  	  System.out.println("Digite o novo Nome: ");
              		  aluno.setNome(console.nextLine());
              		  System.out.println("Digite a nova Data de Nascimento: ");
              		  aluno.setDt_nasc( new Date(console.nextLine()));
              		  System.out.println("Digite o Sexo: ");
              		  aluno.setSexo(console.nextLine());
              		  acao.alterar(aluno);
              		  System.out.print("Atualizado com sucesso \n");
              }
              if(opcao == 4) {
            	  AlunoJDBC acao = new AlunoJDBC();
            	  var matricula = 0;
            	  System.out.print("Digite a matrícula do Aluno a ser excluído");
            	  matricula = Integer.parseInt(console.nextLine());
            	  acao.apagar(matricula);
            	  System.out.print("Excluído com sucesso \n");
            	  acao.listar();
              }  
              
             if(opcao == 5) {
            	 db.fechaConexao();
            	 System.out.println("Conexão fechada com sucesso !");
             }
              
            } while(opcao != 5);
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
	}
}
