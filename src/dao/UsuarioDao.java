package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDao {

	public void salvar(Usuario usuario);

	public void atualizar(Usuario usuario);

	public void excluir(Usuario usuario);

	public Usuario carregar(Integer codigo);

	public Usuario buscarPorLogin(String login) throws Exception;

	public List<Usuario> listar();
}
