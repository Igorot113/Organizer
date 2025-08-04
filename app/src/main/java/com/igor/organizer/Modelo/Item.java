package com.igor.organizer.Modelo;

public class Item {
    private String texto;
    private String descricao;
    public Item(){}
    public Item(String texto, String descricao) {
        this.texto = texto;
        this.descricao = descricao;
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
