package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import manager.UsuarioManager;
import model.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioController {

	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;

	public String novo() {
		destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "usuario";
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage(
					"A senha não foi confirmada corretamente.");
			context.addMessage(null, facesMessage);
			return null;
		}

		UsuarioManager usuarioManager = new UsuarioManager();
		usuarioManager.salvar(this.usuario);

		return this.destinoSalvar;
	}
	
	public String editar(){
		this.confirmarSenha = this.usuario.getSenha();
		return "/publico/usuario";
	}
	
	public String excluir(){
		UsuarioManager usuarioManager = new UsuarioManager();
		usuarioManager.excluir(this.usuario);
		this.lista = null;
		return null;
	}
	
	public String ativar(){
		if(this.usuario.isAtivo()){
			this.usuario.setAtivo(false);
		}else{
			this.usuario.setAtivo(true);
		}
		
		UsuarioManager usuarioManager = new UsuarioManager();
		usuarioManager.salvar(this.usuario);
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public List<Usuario> getLista() {
		
		if(this.lista == null){
			UsuarioManager usuarioManager = new UsuarioManager();
			this.lista = usuarioManager.listar();
		}
		return this.lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

}
