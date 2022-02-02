package com.eventoapp.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity // INFORMANDO AO SPRING QUE A CLASSE JAVA SERA UMA ENTIDADE/BANCO NO BANCO
public class Convidado {

    @Id // INFORMANDO AO SPRING QUE ESSA CLASSE TERA UM ID NO BANCO
    private String rg;

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    private String nomeConvidado;
    // INFORMANDO AO SPRING QUE ESSA CLASSE TERA UMA RELAÇÃO COM OUTRA ENTIDADE DO TIPO EVENTO
    @ManyToOne // UM EVENTO PARA VARIOS CONVIDADOS
    private Evento evento;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNomeConvidado() {
        return nomeConvidado;
    }

    public void setNomeConvidado(String nomeConvidado) {
        this.nomeConvidado = nomeConvidado;
    }


}
