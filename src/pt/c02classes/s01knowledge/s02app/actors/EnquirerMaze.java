package pt.c02classes.s01knowledge.s02app.actors;



import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		
		char parede = 'D';
		int i = 0;
		
		while(!responder.ask("aqui").equalsIgnoreCase("saida")) {
			System.out.println(i);
			i++;
			if(parede == 'D') {
				if(responder.ask("leste").equalsIgnoreCase("parede") && responder.ask("norte").equalsIgnoreCase("passagem"))
					responder.move("norte");
				else if(responder.ask("leste").equalsIgnoreCase("passagem")) {
					responder.move("leste");
					parede = 'B';
				}
				else if(responder.ask("oeste").equalsIgnoreCase("passagem")) {
					responder.move("oeste");
					parede = 'A';
				}
				responder.move("sul");
				parede = 'E';
			}
			else if(parede == 'E') {
				if(responder.ask("oeste").equalsIgnoreCase("parede") && responder.ask("sul").equalsIgnoreCase("passagem"))
					responder.move("sul");
				else if(responder.ask("oeste").equalsIgnoreCase("passagem")) {
					responder.move("oeste");
					parede = 'A';
				}
				else if(responder.ask("leste").equalsIgnoreCase("passagem")) {
					responder.move("leste");
					parede = 'B';
				}
				responder.move("norte");
				parede = 'D';
			}
			else if(parede == 'B') {
				if(responder.ask("sul").equalsIgnoreCase("parede") && responder.ask("leste").equalsIgnoreCase("passagem"))
					responder.move("leste");
				else if(responder.ask("sul").equalsIgnoreCase("passagem")) {
					responder.move("sul");
					parede = 'E';
				}
				else if(responder.ask("norte").equalsIgnoreCase("passagem")) {
					responder.move("norte");
					parede = 'D';
				}
				else if(responder.ask("sul").equalsIgnoreCase("passagem")) {
				responder.move("oeste");
				parede = 'A';
				}
			}
			else if(parede == 'A') {
				if(responder.ask("norte").equalsIgnoreCase("parede") && responder.ask("oeste").equalsIgnoreCase("passagem"))
					responder.move("oeste");
				else if(responder.ask("norte").equalsIgnoreCase("passagem")) {
					responder.move("norte");
					parede = 'D';
				}
				else if(responder.ask("sul").equalsIgnoreCase("passagem")) {
					responder.move("sul");
					parede = 'E';
				}
				responder.move("leste");
				parede = 'B';
			}
		}
		
		if (responder.finalAnswer("cheguei"))
			System.out.println("Você encontrou a saida!");
		else
			System.out.println("Fuém fuém fuém!");
		

		return true;
		
	}
}
