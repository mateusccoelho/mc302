package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
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
		// Procura por animal, o primeiro da lista eh o esperado
		for (int animal = 0; animal < listaAnimais.length; animal++) {
			
			obj = bc.recuperaObjeto(listaAnimais[animal]);
			IDeclaracao decl = obj.primeira();
			
			boolean animalEsperado = true;
			// Faz todas as perguntas o animal ser encontrado
			while (decl != null && animalEsperado) {
				String pergunta = decl.getPropriedade();
				String respostaEsperada = decl.getValor();
				
				int i;
				boolean perguntaFeita = false;
				// Ve se a pergunta nao foi feita antes
				for(i = 0; i < perguntasFeitas.size(); i++) {
					if(perguntasFeitas.get(i).getPropriedade().equalsIgnoreCase(pergunta)) {
						perguntaFeita = true;
						break;
					}
				}
				// Se ela ja foi feita, buscamos sua resposta
				if(perguntaFeita) {
					// Se eh a resposta certa passamos para proxima pergunta
					if(perguntasFeitas.get(i).getValor().equalsIgnoreCase(respostaEsperada))
						decl = obj.proxima();
					// Se nao, esse nao eh o animal procurado
					else
						animalEsperado = false;
				}
				// Se ele nao foi feita ainda, salvamos ela em perguntas feitas
				if(perguntaFeita) {
					if(perguntasFeitas.get(i).getValor().equalsIgnoreCase(respostaEsperada))
						decl = obj.proxima();
					else
						animalEsperado = false;
				}
				else {
					String resposta = responder.ask(pergunta);
					perguntasFeitas.add(decl);
					if (resposta.equalsIgnoreCase(respostaEsperada))
						decl = obj.proxima();
					else
						animalEsperado = false;
				}
				
			}
			// Se acertamos todas as perguntas fazemos o questionamento final
			if(animalEsperado) {
				acertei = responder.finalAnswer(listaAnimais[animal]);
				break;
			}
		}
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");

	}

}
