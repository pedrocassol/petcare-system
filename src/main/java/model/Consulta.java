package model;

import java.math.BigDecimal;

public class Consulta {

    private int id;
    private int idPet;
    private String nomePet;
    private String nomeProprietario;
    private String dataHora;
    private String veterinario;
    private String descricao;
    private BigDecimal valorEstimado;
    private String status;
    private String observacoes;

    public Consulta() {
    }

    public Consulta(int idPet, String dataHora, String veterinario, String descricao, BigDecimal valorEstimado,
                    String status,
                    String observacoes) {

        this.idPet = idPet;
        this.dataHora = dataHora;
        this.veterinario = veterinario;
        this.descricao = descricao;
        this.valorEstimado = valorEstimado;
        this.status = status;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(BigDecimal valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
