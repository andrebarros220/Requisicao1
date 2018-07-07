package managedbens;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import managedbeans.datamodel.UsuarioDataModel;
import modelo.Usuario;
import servicos.ServicoUsuario;
import util.messagesFaces;

@ManagedBean(name = "usuarioManagedBean")
@ViewScoped
public class UsuarioMB {

	@SuppressWarnings("unused")
	private Usuario user = new Usuario();
	private Usuario usuario = new Usuario();
	@SuppressWarnings("unused")
	private Usuario usuariologar = new Usuario();
	private Usuario usuariologado = new Usuario();
	private Usuario usuarioSelecionado;
	private ServicoUsuario servico = new ServicoUsuario();
	private ServicoUsuario servicoUsuario = new ServicoUsuario();
	private List<Usuario> usuarios;
	@SuppressWarnings("unused")
	private List<Usuario> Listausuarios;
	@SuppressWarnings("unused")
	private List<Usuario> usuariosaut = null;

	// Getters and Setters

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		if (usuarioSelecionado != null)
			this.usuarioSelecionado = servico.upDate(usuarioSelecionado);
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public DataModel<Usuario> getUsuarios() {
		if (usuarios == null)
			usuarios = servico.getUsuarios();
		return new UsuarioDataModel(usuarios);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		if (usuarios == null)
			usuarios = servicoUsuario.getUsuarios();
		return usuarios;
	}

	public void onRowSelect(SelectEvent event) {

	}

	public void onRowUnselect(SelectEvent event) {

	}
	public void redirecionar() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroUsuario.xhtml");
	}

	// Autenticar
	@SuppressWarnings("unused")
	public void autenticarUsuario() {

		Usuario usuariologado2 = new Usuario();

		try {
			System.out.println("Entrou no servico Usuario");
			usuariologado2 = servicoUsuario.autenticacao(usuario);

			if (usuariologado2 == null) {
				System.out.println("Não Encontrou usuario");
				FacesMessage msg = new FacesMessage("Usuario não encontrado");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.xhtml");
			}
			if (usuariologado != null) {

				FacesMessage msg = new FacesMessage("Usuario encontrado");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				FacesMessage msg1 = new FacesMessage("Redirecionando para a página ....");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrosAdmin.xhtml");
			}

			else {
				System.out.println("Usuario não encontrado........!!!!!");
			}
		} catch (NullPointerException | IOException e) {
			System.out.println("Usuario não encontrado ou ocorreu um erro!!!!!");
			FacesMessage msg = new FacesMessage("Usuario ou Senha Errada !!!!!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} finally {

		}

	}

	@SuppressWarnings("unused")
	private void Acesso() {
		// TODO Auto-generated method stub

	}

	// Editar na Linha
	public void onRowEdit(RowEditEvent event) {
		Usuario f = (Usuario) event.getObject();
		servico.upDateUsuario(f);
	}

	// Salvar
	public void salvar() {
		servico.saveUsuario(usuario);
		usuario = new Usuario();
		messagesFaces.adicionarMsgInfo("Salvo com sucesso !");
	}

	// Pesquisar
	public void pesquisar() {
		Usuario aux = servico.pesquisar(usuario.getID());
		usuario.setID(aux.getID());
		usuario.setNome(aux.getNome());
	}

	// Excluir
	public void remove(Usuario usuario) {

		if (servico.removeUsuario(usuario)) {
			usuarios.remove(usuario);
			usuarioSelecionado = null;
			messagesFaces.adicionarMsgInfo("Removido com sucesso !");
		} else {
			FacesMessage msg = new FacesMessage("Impossível remover ", "Nome: " + usuario.getNome());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

}
