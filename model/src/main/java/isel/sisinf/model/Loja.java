package isel.sisinf.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

/*CREATE TABLE Loja(
	codigo integer PRIMARY KEY,
	email varchar(40) UNIQUE NOT NULL,
	endereco varchar(100) NOT NULL,
	localidade varchar(30) NOT NULL,
	gestor integer NOT NULL REFERENCES Pessoa(id),
	constraint CHK_Loja_email CHECK (email ~ '.+@.+\.\w{1,}')
);
 */

@Entity
@NamedQuery(name="Loja.findByKey", query="SELECT l FROM Loja l WHERE l.codigo = :key")
@NamedQuery(name = "Loja.getAll", query = "SELECT l FROM Loja l")
public class Loja implements Serializable {
    @Id
    private Integer codigo;

    @Column(nullable = false, unique = true, length = 40)
    private String email;

    @Column(nullable = false, length = 100)
    private String endereco;

    @Column(nullable = false, length = 30)
    private String localidade;

    @ManyToOne
    @JoinColumn(name = "gestor", nullable = false)
    private Pessoa gestor;


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}