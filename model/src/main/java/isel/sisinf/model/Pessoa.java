package isel.sisinf.model;

import jakarta.persistence.*;
import java.util.Set;

/*CREATE TABLE Pessoa(
	id serial PRIMARY KEY,
	nome varchar(40) NOT NULL,
	morada varchar(150) NOT NULL,
	email varchar(40) UNIQUE NOT NULL,
	telefone varchar(30) UNIQUE NOT NULL,
	noident char(12) UNIQUE NOT NULL,
	nacionalidade varchar(20) NOT NULL,
	atrdisc char(2) NOT NULL,
	constraint CHK_Pessoa_email CHECK (email ~ '.+@.+\.\w{1,}'),
	constraint CHK_Pessoa_telefone CHECK (telefone ~ '^\d{4,15}$'),
	constraint CHK_Pessoa_atrdisc CHECK (atrdisc IN('G', 'C'))
); */

@Entity
@Table(name = "Pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nome;

    @Column(nullable = false, length = 150)
    private String morada;

    @Column(nullable = false, unique = true, length = 40)
    private String email;

    @Column(nullable = false, unique = true, length = 30)
    private String telefone;

    @Column(nullable = false, unique = true, length = 12)
    private String noident;

    @Column(nullable = false, length = 20)
    private String nacionalidade;

    @Column(nullable = false, length = 2)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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