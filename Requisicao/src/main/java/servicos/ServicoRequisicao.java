package servicos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.Requisicao;

public class ServicoRequisicao extends Servico {
	
private Requisicao aux = new Requisicao();
	
	public void upDateRequisicao(Requisicao p) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();

	}
	
	@SuppressWarnings("unchecked")
	public List<Requisicao> requisicaoSelecaoCodigo(Requisicao requisicao)
	{
		List<Requisicao> requisicaocodigoselecionado = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select c FROM Requisicao c where c.codigo = :codigo" );
		query.setParameter("codigo", requisicao.getCodigo());
		requisicaocodigoselecionado = query.getResultList();
		em.close();
		
		return requisicaocodigoselecionado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Requisicao> getRequisicoes() {
		List<Requisicao> requisicoes;
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select r From Requisicao r", Requisicao.class);
		requisicoes = q.getResultList();
		em.close();
		return requisicoes;
	}
	
	public void saveRequisicao(Requisicao requisicao) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(requisicao);
		em.getTransaction().commit();
		em.close();
	}
	
	public Requisicao  pesquisar(Requisicao requisicao) {
		EntityManager em = emf.createEntityManager();
		
		aux = em.find(Requisicao.class, requisicao.getCodigo());
		em.close();
		
		return aux;
	}
	
	public boolean removeRequisicao(Requisicao requisicao) {

		boolean sucesso = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Requisicao p = em.find(Requisicao.class,requisicao.getCodigo());
			em.remove(p);
			em.getTransaction().commit();
			em.close();
			sucesso = true;
		} catch (Exception e) {

		}

		return sucesso;
	}

}
