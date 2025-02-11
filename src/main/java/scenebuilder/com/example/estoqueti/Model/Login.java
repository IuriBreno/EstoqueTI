package scenebuilder.com.example.estoqueti.Model;

public class Login {


    private String usuario;
    private String senha;
    private int tipoLogin;

//1 user adm
//0 user comum


//    public Login(String usuario, String senha) {
//        this.usuario = usuario;
//        this.senha = senha;
//    }

    private static Login usuarioLogado;

    public static void setUsuarioLogado(Login usuarioLogado) {
        Login.usuarioLogado = usuarioLogado;
    }

    public static Login getUsuarioLogado(){
        return usuarioLogado;
    }

    public int getTipoLogin() {
        return tipoLogin;
    }

    public void setTipoLogin(int tipoLogin) {
        this.tipoLogin = tipoLogin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
