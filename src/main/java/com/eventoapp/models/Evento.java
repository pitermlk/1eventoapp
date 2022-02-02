package com.eventoapp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Evento implements Serializable {

    public static final long serialVersioUID = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;

    // INFORMANDO AO SPRING QUE ESSA CLASSE TERA UMA RELAÇÃO COM OUTRA ENTIDADE DO TIPO EVENTO
    @OneToMany // UM EVENTO PARA VARIOS CONVIDADOS
    private List<Convidado> convidados;
    private String nome;
    private String local;
    private String data;


    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    private String horario;
}
