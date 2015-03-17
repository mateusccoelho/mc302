package pt.c02classes.s01knowledge.s02app.actors;



import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		
		char parede = ' ';
		
		if (responder.ask("norte").equalsIgnoreCase("passagem")&&responder.ask("sul").equalsIgnoreCase("passagem")&&responder.ask("leste").equalsIgnoreCase("passagem")&&responder.ask("oeste").equalsIgnoreCase("passagem")){
			while (responder.ask("leste").equalsIgnoreCase("passagem"))
				responder.move("leste");
			parede = 'D';
		}
		else if (responder.ask("norte").equalsIgnoreCase("passagem")) 
			parede = 'D';
		else if (responder.ask("sul").equalsIgnoreCase("passagem"))
			parede = 'E';
		else if (responder.ask("leste").equalsIgnoreCase("passagem"))
			parede = 'B';
		else if (responder.ask("oeste").equalsIgnoreCase("passagem"))
			parede = 'A';
			
		
		while(!responder.ask("aqui").equalsIgnoreCase("saida")) {
			
			if (responder.ask("leste").equalsIgnoreCase("saida"))
				responder.move("leste");
			else if (responder.ask("oeste").equalsIgnoreCase("saida"))
				responder.move("oeste");
			else if (responder.ask("norte").equalsIgnoreCase("saida"))
				responder.move("norte");
			else if (responder.ask("sul").equalsIgnoreCase("saida"))
				responder.move("sul");

			else if(parede == 'D') {
				if ((responder.ask("leste").equalsIgnoreCase("parede") || responder.ask("leste").equalsIgnoreCase("mundo")) && responder.ask("norte").equalsIgnoreCase("passagem")) {
					responder.move("norte");
				}
				else if(responder.ask("leste").equalsIgnoreCase("passagem")) {
					responder.move("leste");
					parede = 'B';
				}
				else if(responder.ask("oeste").equalsIgnoreCase("passagem")) {
					responder.move("oeste");
					parede = 'A';
				}
				else if ((responder.ask("leste").equalsIgnoreCase("parede") || responder.ask("leste").equalsIgnoreCase("mundo")) && (responder.ask("norte").equalsIgnoreCase("parede") || responder.ask("norte").equalsIgnoreCase("mundo")) && (responder.ask("oeste").equalsIgnoreCase("parede") || responder.ask("oeste").equalsIgnoreCase("mundo"))){
					responder.move("sul");
					parede = 'E';
				}
			}
			else if(parede == 'E') {
				if ((responder.ask("oeste").equalsIgnoreCase("parede") || responder.ask("leste").equalsIgnoreCase("mundo")) && responder.ask("sul").equalsIgnoreCase("passagem")) {
					responder.move("sul");
				}
				else if(responder.ask("oeste").equalsIgnoreCase("passagem")) {
					responder.move("oeste");
					parede = 'A';
				}
				else if(responder.ask("leste").equalsIgnoreCase("passagem")) {
					responder.move("leste");
					parede = 'B';
				}
				else if ((responder.ask("sul").equalsIgnoreCase("parede") || responder.ask("sul").equalsIgnoreCase("mundo")) && (responder.ask("leste").equalsIgnoreCase("parede") || responder.ask("leste").equalsIgnoreCase("mundo")) && (responder.ask("oeste").equalsIgnoreCase("parede") || responder.ask("oeste").equalsIgnoreCase("mundo"))){
					responder.move("norte");
					parede = 'D';
				}
			}
			else if(parede == 'B') {
				if ((responder.ask("sul").equalsIgnoreCase("parede") || responder.ask("sul").equalsIgnoreCase("mundo")) && responder.ask("leste").equalsIgnoreCase("passagem")) {
					responder.move("leste");
				}
				else if(responder.ask("sul").equalsIgnoreCase("passagem")) {
					responder.move("sul");
					parede = 'E';
				}
				else if(responder.ask("norte").equalsIgnoreCase("passagem")) {
					responder.move("norte");
					parede = 'D';
				}
				else if ((responder.ask("sul").equalsIgnoreCase("parede")|| responder.ask("sul").equalsIgnoreCase("mundo")) && (responder.ask("leste").equalsIgnoreCase("parede")|| responder.ask("leste").equalsIgnoreCase("mundo")) && (responder.ask("norte").equalsIgnoreCase("parede")|| responder.ask("norte").equalsIgnoreCase("mundo"))) {
					responder.move("oeste");
					parede = 'A';
				}
			}
			else if(parede == 'A') {
				if ((responder.ask("norte").equalsIgnoreCase("parede")|| responder.ask("norte").equalsIgnoreCase("mundo")) && responder.ask("oeste").equalsIgnoreCase("passagem")) {
					responder.move("oeste");
				}
				else if(responder.ask("norte").equalsIgnoreCase("passagem")) {
					responder.move("norte");
					parede = 'D';
				}
				else if(responder.ask("sul").equalsIgnoreCase("passagem")) {
					responder.move("sul");
					parede = 'E';
				}
				else if ((responder.ask("sul").equalsIgnoreCase("parede")|| responder.ask("sul").equalsIgnoreCase("mundo")) && (responder.ask("norte").equalsIgnoreCase("parede")|| responder.ask("norte").equalsIgnoreCase("mundo")) && (responder.ask("oeste").equalsIgnoreCase("parede")|| responder.ask("oeste").equalsIgnoreCase("mundo"))){
					responder.move("leste");
					parede = 'B';
				}
			}
		}
		
		if (responder.finalAnswer("cheguei"))
			System.out.println("Voc� encontrou a saida!");
		else
			System.out.println("Fu�m fu�m fu�m!");
		

		return true;
		
	}
}
