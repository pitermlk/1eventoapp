package com.eventoapp.controllers;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;

    @Autowired // REALIZANDO UMA INJEÇÃO DE DEPENDENCIA INTERFACE PARA REALIZAR O CRUD COM O SPRING
    private ConvidadoRepository cr;

    // http://localhost:8080/cadastrarEvento
    // methodo GET para chamar a VIEW formEvento..
    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
    public String form(){
        return "evento/formEvento";
    }

    // methodo POST para salvar OBJETO do tipo EVENTO no BANCO.
    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
    public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes){ // Intanciando OBJETO Tipo Evento.
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem","Vefirique os campos !!");
            return "redirect:/cadastrarEvento";
        }
        er.save(evento); // METODO PARA SALVAR EVENTO. // METODO PARA SALVAR EVENTO..
        attributes.addFlashAttribute("mensagem","Evento Cadastrado com sucesso. !!");
        return "redirect:/cadastrarEvento";
    }

    @RequestMapping("/eventos") // METODO PARA LISTAR OS EVENTOS QUE SERÃO BUSCADOS NO BANCO
    public ModelAndView listaEventos() {
        ModelAndView mv = new ModelAndView("listaEventos"); // OBJETO PARA DEFINIR QUAL VIEW SERA RENDERIZADA
        Iterable<Evento> eventos = er.findAll(); // OBJETO PARA BUSCAR A LISTA NO BANCO
        mv.addObject("eventos", eventos);

        return mv; // RETURN MOSTRA QUAL SERA A VIEW QUE SERA RENDERIZADA.// RETURN MOSTRA QUAL SERA A VIEW QUE SERA RENDERIZADA.
    }

//    @RequestMapping(value = "/{codigo}") // ANOTAÇÃO PARA REQUISITAR OU MANDAR INFO PARA O BACKEND MODEL/INFO DESEJADA.
//    public ModelAndView detalhesEvento (@PathVariable("codigo") long codigo) {
//        Evento evento = er.findByCodigo(codigo);
//        ModelAndView mv = new ModelAndView("evento/detalhesEvento"); // OBJETO PARA DEFINIR QUAL VIEW SERA RENDERIZADA S.S
//        mv.addObject("evento", evento);
//        System.out.println("evento" + evento);
//
//        return mv;
//    }


    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET) // ANOTAÇÃO PARA REQUISITAR OU MANDAR INFO PARA O BACKEND MODEL/INFO DESEJADA.
    public ModelAndView detalhesEvento (@PathVariable("codigo") long codigo) {
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento"); // OBJETO PARA DEFINIR QUAL VIEW SERA RENDERIZADA
        mv.addObject("evento", evento);
        // PARA BUSCAR O EVENTO COM O CR.FINDALL PRECISAMOS IR NA INTERFACE CONVIDADO E INSTANCIAR A BUSCA.
        // Iterable<Convidado> findByEvento (Evento evento);
        Iterable<Convidado> convidados = cr.findByEvento(evento); // BUSCANDO CONVIDADO ESPECIFICO POR EVENTO.
        mv.addObject("convidados",convidados); // METODO PARA ADICIONAR UM OBJETO NA VIEW.
        return mv;
    }

    // API PARA SALVAR CONVIDADO NO BANCO.
    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST) // ANOTAÇÃO PARA REQUISITAR OU MANDAR INFO PARA O BACKEND MODEL/INFO DESEJADA.
    public String detalhesEventoPost (@PathVariable("codigo") long codigo, @Valid Convidado convidado,  BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()){ // IF PARA VALIDAR SE EXISTEM CAMPOS OBRIGATORIOS QUE ESTAO VAZIOS.
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/{codigo}";
        }

        Evento evento = er.findByCodigo(codigo); // INSTANCIA UM OBJETO DO TIPO EVENTO PARA BUSCAR O EVENTO NO CODIGO
        convidado.setEvento(evento);
        cr.save(convidado);
        attributes.addFlashAttribute("mensagem","Mensagem enviada com sucesso");
        return "redirect:/{codigo}";
    }

}
