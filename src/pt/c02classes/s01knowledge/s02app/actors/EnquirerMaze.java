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
		
		while(!responder.ask("aqui").equalsIgnoreCase("saida") && i < 20) {
			i++;
			if(parede == 'D') {
				if(responder.ask("leste").equalsIgnoreCase("parede") && responder.ask("norte").equalsIgnoreCase("passagem")) {
					responder.move("norte");
					System.out.println("Andei Norte");
				}
				else if(responder.ask("leste").equalsIgnoreCase("passagem")) {
					responder.move("leste");
					System.out.println("Andei Leste");
					parede = 'B';
				}
				else if(responder.ask("oeste").equalsIgnoreCase("passagem")) {
					responder.move("oeste");
					System.out.println("Andei Oeste");
					parede = 'A';
				}
				else if (responder.ask("leste").equalsIgnoreCase("parede") && responder.ask("norte").equalsIgnoreCase("parede") && responder.ask("oeste").equalsIgnoreCase("parede")){
					responder.move("sul");
					parede = 'E';
				}
				else if (responder.ask("norte").equalsIgnoreCase("saida"))
					responder.move("norte");
			}
			else if(parede == 'E') {
				if(responder.ask("oeste").equalsIgnoreCase("parede") && responder.ask("sul").equalsIgnoreCase("passagem")) {
					System.out.println("Andei Sul");
					responder.move("sul");
				}
				else if(responder.ask("oeste").equalsIgnoreCase("passagem")) {
					System.out.println("Andei Oeste");
					responder.move("oeste");
					parede = 'A';
				}
				else if(responder.ask("leste").equalsIgnoreCase("passagem")) {
					responder.move("leste");
					System.out.println("Andei Leste");
					parede = 'B';
				}
				else if (responder.ask("sul").equalsIgnoreCase("parede") && responder.ask("leste").equalsIgnoreCase("parede") && responder.ask("oeste").equalsIgnoreCase("parede")){
					responder.move("norte");
					System.out.println("Andei Norte");
					parede = 'D';
				}
				else if (responder.ask("sul").equalsIgnoreCase("saida"))
					responder.move("sul");
			}
			else if(parede == 'B') {
				if(responder.ask("sul").equalsIgnoreCase("parede") && responder.ask("leste").equalsIgnoreCase("passagem")) {
					responder.move("leste");
					System.out.println("Andei Leste");
				}
				else if(responder.ask("sul").equalsIgnoreCase("passagem")) {
					responder.move("sul");
					System.out.println("Andei Sul");
					parede = 'E';
				}
				else if(responder.ask("norte").equalsIgnoreCase("passagem")) {
					responder.move("norte");
					System.out.println("Andei Norte");
					parede = 'D';
				}
				else if (responder.ask("sul").equalsIgnoreCase("parede") && responder.ask("leste").equalsIgnoreCase("parede") && responder.ask("norte").equalsIgnoreCase("parede")) {
					responder.move("oeste");
					System.out.println("Andei Oeste");
					parede = 'A';
				}
				else if (responder.ask("leste").equalsIgnoreCase("saida"))
						responder.move("leste");
			}
			else if(parede == 'A') {
				if(responder.ask("norte").equalsIgnoreCase("parede") && responder.ask("oeste").equalsIgnoreCase("passagem")) {
					responder.move("oeste");
					System.out.println("Andei Oeste");
				}
				else if(responder.ask("norte").equalsIgnoreCase("passagem")) {
					responder.move("norte");
					System.out.println("Andei Norte");
					parede = 'D';
				}
				else if(responder.ask("sul").equalsIgnoreCase("passagem")) {
					responder.move("sul");
					System.out.println("Andei Sul");
					parede = 'E';
				}
				else if (responder.ask("sul").equalsIgnoreCase("parede") && responder.ask("norte").equalsIgnoreCase("parede") && responder.ask("oeste").equalsIgnoreCase("parede")){
					responder.move("leste");
					System.out.println("Andei Leste");
					parede = 'B';
				}
				else if (responder.ask("oeste").equalsIgnoreCase("saida"))
					responder.move("oeste");
			}
		}
		
		if (responder.finalAnswer("cheguei"))
			System.out.println("Você encontrou a saida!");
		else
			System.out.println("Fuém fuém fuém!");
		

		return true;
		
	}
}
