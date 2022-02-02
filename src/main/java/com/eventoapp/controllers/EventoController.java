package com.eventoapp.controllers;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;

    // http://localhost:8080/cadastrarEvento
    // methodo GET para chamar a VIEW formEvento.
    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
    public String form(){
        return "evento/formEvento";
    }

    // methodo POST para salvar OBJETO do tipo EVENTO no BANCO.
    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
    public String form(Evento evento){ // Intanciando OBJETO Tipo Evento.

        er.save(evento); // METODO PARA SALVAR EVENTO. // METODO PARA SALVAR EVENTO.
        return "redirect:/cadastrarEvento";
    }

    @RequestMapping("/eventos") // METODO PARA LISTAR OS EVENTOS QUE SERÃO BUSCADOS NO BANCO
    public ModelAndView listaEventos() {
        ModelAndView mv = new ModelAndView("listaEventos"); // OBJETO PARA DEFINIR QUAL VIEW SERA RENDERIZADA
        Iterable<Evento> eventos = er.findAll(); // OBJETO PARA BUSCAR A LISTA NO BANCO
        mv.addObject("eventos", eventos);

        return mv; // RETURN MOSTRA QUAL SERA A VIEW QUE SERA RENDERIZADA.// RETURN MOSTRA QUAL SERA A VIEW QUE SERA RENDERIZADA.
    }

    @RequestMapping(value = "/{codigo}") // ANOTAÇÃO PARA REQUISITAR OU MANDAR INFO PARA O BACKEND MODEL/INFO DESEJADA.
    public ModelAndView detalhesEvento (@PathVariable("codigo") long codigo) {
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento"); // OBJETO PARA DEFINIR QUAL VIEW SERA RENDERIZADA S.S
        mv.addObject("evento", evento);
        System.out.println("evento" + evento);

        return mv;
    }

}
