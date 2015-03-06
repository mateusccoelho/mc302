package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;

import pt.c01interfaces.s01knowledge.s01base.impl.Declaracao;
import pt.c01interfaces.s01knowledge.s01base.impl.Statistics;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

import java.util.ArrayList;
public class Enquirer implements IEnquirer
{
	
	
    IObjetoConhecimento obj;
    
    
	
	public void connect(IResponder responder)
	{
		boolean acertei = true;
		
		IBaseConhecimento bc = new BaseConhecimento();
		
		String listaAnimais[] = bc.listaNomes();
		
		ArrayList <IDeclaracao> perguntasFeitas = new ArrayList <IDeclaracao>();
        
		
		for (int animal = 0; animal < listaAnimais.length; animal++) {
			
			obj = bc.recuperaObjeto(listaAnimais[animal]);
			IDeclaracao decl = obj.primeira();
			
			boolean animalEsperado = true;
			while (decl != null && animalEsperado) {
				String pergunta = decl.getPropriedade();
				String respostaEsperada = decl.getValor();
				
				String resposta = responder.ask(pergunta);
				
				perguntasFeitas.add(decl);
				
				if (resposta.equalsIgnoreCase(respostaEsperada))
					decl = obj.proxima();
				else
					animalEsperado = false;
			}
			
			if(animalEsperado == true) {
				acertei = responder.finalAnswer(listaAnimais[animal]);
				break;
			}
		}
		System.out.println(perguntasFeitas);
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");

	}

}
