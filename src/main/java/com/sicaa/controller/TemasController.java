package com.sicaa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sicaa.model.Tema;
import com.sicaa.model.ErrorCustom;
import com.sicaa.repository.Temas;

@Controller
@RequestMapping("/temas")
public class TemasController {

	@Autowired
	private Temas temas;

	@GetMapping("/novo")
	public ModelAndView listar() {

		ModelAndView mvw = new ModelAndView("CadastroTemas");
		mvw.addObject("temas", temas.findAll());
		mvw.addObject(new Tema());
		return mvw;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Tema tema, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("CadastroTemas");
		if (bindingResult.hasErrors()) {
			mv.addObject("mensagem", ErrorCustom.getMensagemErro(bindingResult.getAllErrors().get(0)));
		} else {
			List<String> msg = new ArrayList<String>();
			if (tema.getNome().trim().equals("")) {
				msg.add("*" + "O tema não pode ser vazio!");
				;
			}
			if (msg.size() == 0) {
				this.temas.save(tema);
				msg.add("Salvo com sucesso!");
				mv = this.pesquisar();
				mv.addObject("mensagem", msg);
				return mv;
			} else {
				mv.addObject("mensagem", msg);
			}
		}
		return mv;
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Tema> todosTemas = temas.findAll();
		ModelAndView mv = new ModelAndView("ListaTemas");
		mv.addObject("temas", todosTemas);
		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Tema tema) {
		ModelAndView mv = new ModelAndView("CadastroTemas");
		mv.addObject(tema);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		temas.delete(id);
		// attributes.addFlashAttribute("mensagem", "Título excluído com
		// sucesso!");
		return "redirect:/temas";
	}

	@RequestMapping(params = { "id", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id, @RequestParam(value = "action") String action) {
		if (action.equals("delete"))
			temas.delete(id);
		ModelAndView mv = new ModelAndView("ListaTemas");
		List<String> msg = new ArrayList<>();
		msg.add("Excluido com sucesso!");
		mv = this.pesquisar();
		mv.addObject("mensagem", msg);
		return mv;
	}

}
