package scenebuilder.com.example.estoqueti.Model;

public class UsersPrivacy {

    private int idUsuario;
    private String nome;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString() {
        return nome;
    }
}
