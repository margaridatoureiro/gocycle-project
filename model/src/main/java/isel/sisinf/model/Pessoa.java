package isel.sisinf.model;

import jakarta.persistence.*;

@Entity
@NamedQuery(name="Pessoa.findByKey", query="SELECT p FROM Pessoa p WHERE p.id = :key")
@NamedQuery(name = "Pessoa.getAll", query = "SELECT p FROM Pessoa p")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String morada;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String noident;

    @Column(nullable = false)
    private String nacionalidade;

    @Column(nullable = false)
    private String atrdisc;

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "Loja")

    public Pessoa() {
    }

    // Parameterized constructor
    public Pessoa(String nome, String morada, String email, String telefone, String noident, String nacionalidade, String atrdisc) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        this.noident = noident;
        this.nacionalidade = nacionalidade;
        this.atrdisc = atrdisc;
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNoident() {
        return noident;
    }

    public void setNoident(String noident) {
        this.noident = noident;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getAtrdisc() {
        return atrdisc;
    }

    public void setAtrdisc(String atrdisc) {
        this.atrdisc = atrdisc;
    }
}