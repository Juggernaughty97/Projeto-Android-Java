package com.projecto;

public class Dados {
    private int Id;
    private int Numero;
    private int Idade;
    private String Nome;
    private String Telefone;
    private String Email;

    public Dados(int Id, int Numero, String Nome, String  Telefone, int  Idade, String Email){
        this.Id = Id;
        this.Numero = Numero;
        this.Idade = Idade;
        this.Nome = Nome;
        this.Telefone = Telefone;
        this.Email = Email;
    }

    public int getId(){ return Id; }

    public void setId(int id){ Id = id; }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public int getIdade() {
        return Idade;
    }

    public void setIdade(int idade) {
        Idade = idade;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
