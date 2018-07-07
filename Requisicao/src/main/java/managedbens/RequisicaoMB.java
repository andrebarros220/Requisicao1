package managedbens;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import modelo.Requisicao;
import servicos.ServicoRequisicao;


@ManagedBean(name = "requisicaoManagedBean")
@ViewScoped
public class RequisicaoMB {

	private Requisicao requisicao = new Requisicao();
	private ServicoRequisicao servicoRequisicao = new ServicoRequisicao();
	private Requisicao aux = new Requisicao();
	private List<Requisicao> rqlist;
	private List<Requisicao> rqtotal;
	private List<Requisicao> rqselecionadas;
	private List<Requisicao> requis;
	private List<Requisicao> requisicoes = null;

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public Requisicao getAux() {
		return aux;
	}

	public void setAux(Requisicao aux) {
		this.aux = aux;
	}

	public List<Requisicao> getRqlist() {
		return rqlist;
	}

	public void setRqlist(List<Requisicao> rqlist) {
		this.rqlist = rqlist;
	}

	public List<Requisicao> getRqtotal() {
		return rqtotal;
	}

	public void setRqtotal(List<Requisicao> rqtotal) {
		this.rqtotal = rqtotal;
	}

	public List<Requisicao> getRqselecionadas() {
		return rqselecionadas;
	}

	public void setRqselecionadas(List<Requisicao> rqselecionadas) {
		this.rqselecionadas = rqselecionadas;
	}
	
	public List<Requisicao> getListarequisicoes() {
		if (requisicoes == null)
			requisicoes = servicoRequisicao.getRequisicoes();

		return requisicoes;
	}
	
	public List<Requisicao> getRequis() {
		return requis;
	}

	public void setRequis(List<Requisicao> requis) {
		this.requis = requis;
	}
	
	

	// Alterar na Linha da Tabela
	public void onRowEdit(RowEditEvent event) {
		Requisicao r = (Requisicao) event.getObject();
		servicoRequisicao.upDateRequisicao(r);
	}

	// Salvar
	public void salvar() {
		try {
			servicoRequisicao.saveRequisicao(requisicao);
			requisicao = new Requisicao();
			util.messagesFaces.adicionarMsgInfo("Salvo com sucesso !");
		} catch (Exception e) {
			FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					" A Requisição não pode ser salva!!");
			FacesContext.getCurrentInstance().addMessage(null, error);

		}
		
	}
	
	// Pesquisar por Código
	public List<Requisicao> pesquisarRequisicaoCodigo() {
		try {
			requis = (List<Requisicao>) servicoRequisicao.requisicaoSelecaoCodigo(requisicao);
			FacesMessage msg = new FacesMessage("Sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (NullPointerException e) {
			FacesMessage msg = new FacesMessage("Código não encontrado!      " + "Refaça a busca!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return requis;
	}

	// Excluir
		public void remove(Requisicao requisicao) {
			try {
				requisicoes.remove(requisicao);
				servicoRequisicao.removeRequisicao(requisicao);
				new FacesMessage("Removido com sucesso !");
			} catch (NullPointerException e) {
				FacesMessage msg = new FacesMessage("Ocorreu um erro ao tentar Deletar");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} finally {

			}
		}
	
	
	
	
	
	
}
