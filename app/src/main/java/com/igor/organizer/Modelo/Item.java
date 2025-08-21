package com.igor.organizer.Modelo;

public class Item {
    private long id;
    private String texto;
    private String descricao;
    public Item(){}
    public Item(String texto, String descricao) {
        this.texto = texto;
        this.descricao = descricao;
    }
    public Item(long id,String texto,String descricao){
        this.id = id;
        this.texto = texto;
        this.descricao = descricao;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString() {
        return "Item{" +
                "texto='" + texto + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
