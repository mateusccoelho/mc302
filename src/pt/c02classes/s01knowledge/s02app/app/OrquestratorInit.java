package pt.c02classes.s01knowledge.s02app.app;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;

import java.util.Scanner;

public class OrquestratorInit {

	public static void main(String[] args) {
		
		IEnquirer enq;
		IResponder resp;
		IStatistics stat = new Statistics();
		IBaseConhecimento base = new BaseConhecimento();
		Scanner entrada = new Scanner(System.in);
		String jogo = "Nada";
		
		while (!jogo.equalsIgnoreCase("A") && !jogo.equalsIgnoreCase("M")) {
			System.out.println("Deseja jogar (M)aze ou (A)nimals?");
			jogo = entrada.nextLine();
		}
		
		switch (jogo.toUpperCase()) {
			case "A": {
				base.setScenario("animals");
				enq = new EnquirerAnimals();
				String listaAnimais[] = base.listaNomes();
				System.out.println("Digite o numero correspondente ao animal que deseja jogar.");
				int i;
				for(i = 0; i < listaAnimais.length; i++)
					System.out.println(i + " - " + listaAnimais[i]);
				i = entrada.nextInt();
				System.out.println("Enquirer com " + listaAnimais[i] + "...");
				resp = new ResponderAnimals(stat, listaAnimais[i]);
				enq.connect(resp);
				enq.discover();
				break;
			}
			
			case "M": {
				base.setScenario("maze");
				enq = new EnquirerMaze();
				String listaMapas[] = base.listaNomes();
				System.out.println("Digite o numero correspondente ao labirinto que deseja jogar.");
				int i;
				for(i = 0; i < listaMapas.length; i++)
					System.out.println(i + " - " + listaMapas[i]);
				i = entrada.nextInt();
				System.out.println("Enquirer com " + listaMapas[i] + "...");
				resp = new ResponderMaze(stat, listaMapas[i]);
				enq.connect(resp);
				enq.discover();
				break;
			}
		}
		
		System.out.println("----------------------------------------------------------------------------------------\n");
		entrada.close();
	}

}
