package com.example.habitossaude.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "metas")
public class Meta {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descricao;
    private String dataMeta;
    private boolean concluida;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataMeta() {
        return dataMeta;
    }

    public void setDataMeta(String dataMeta) {
        this.dataMeta = dataMeta;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
