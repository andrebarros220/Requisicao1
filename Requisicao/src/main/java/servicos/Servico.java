package servicos;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Servico {
	
protected static EntityManagerFactory emf =Persistence.createEntityManagerFactory("Requisicao");
	
	public Servico()
	{
		
	}

}
