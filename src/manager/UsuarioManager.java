package manager;

import java.util.List;

import model.Usuario;
import dao.UsuarioDao;
import dao.UsuarioDaoHibernate;

public class UsuarioManager {

	private UsuarioDao usuarioDao;
	
	public UsuarioManager() {
		this.usuarioDao = new UsuarioDaoHibernate();
	}

	public Usuario carregar(Integer codigo) {
		return this.usuarioDao.carregar(codigo);
	}

	public Usuario buscarPorLogin(String login) throws Exception {
		return this.usuarioDao.buscarPorLogin(login);
	}

	public void salvar(Usuario usuario) {

		Long codigo = usuario.getCodigo();

		if (codigo == null || codigo == 0) {
			this.usuarioDao.atualizar(usuario);
		} else {
			this.usuarioDao.atualizar(usuario);
		}
	}

	public void excluir(Usuario usuario) {
		this.usuarioDao.excluir(usuario);
	}

	public List<Usuario> listar() {
		return this.usuarioDao.listar();
	}
	
	

}
