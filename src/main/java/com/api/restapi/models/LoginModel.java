package com.api.restapi.models;

public class LoginModel {
    private Long id;
    private String nome_funcionario;
    private String email;
    private String nome_empresa;
    private String telefone;

    public LoginModel(Long id, String nome_funcionario, String email, String nome_empresa, String telefone) {
        setId(id);
        setNome_funcionario(nome_funcionario);
        setEmail(email);
        setNome_empresa(nome_empresa);
        setTelefone(telefone);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    

    
}
