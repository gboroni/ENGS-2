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

import com.sicaa.model.Apresentacao;
import com.sicaa.model.Cont;
import com.sicaa.model.ErrorCustom;
import com.sicaa.repository.Apresentacaos;
import com.sicaa.repository.Contador;
import com.sicaa.repository.Temas;

@Controller
@RequestMapping("/apresentacaos")
public class ApresentacaosController {

	@Autowired
	private Apresentacaos apresentacaos;
	
	@Autowired
	private Temas temas;
	
	@Autowired
	private Contador contador;

	@GetMapping("/novo")
	public ModelAndView listar() {

		ModelAndView mvw = new ModelAndView("CadastroApresentacaos");
		mvw.addObject("apresentacaos", apresentacaos.findAllApresentacaos());
		mvw.addObject("temas",temas.findAll());
		mvw.addObject(new Apresentacao());
		return mvw;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Apresentacao apresentacao, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("CadastroApresentacaos");
		if (bindingResult.hasErrors()) {
			mv.addObject("mensagem", ErrorCustom.getMensagemErro(bindingResult.getAllErrors().get(0)));
		} else {
			List<String> msg = new ArrayList<String>();
			if (apresentacao.getId_tema() == null || apresentacao.getId_tema() <= 0) {
				msg.add("*" + "Selecione um tema!");
				;
			}
			if (apresentacao.getInicio() == null) {
				msg.add("*" + "Informe a data de início da apresentação!");
			}
			if (apresentacao.getFim() == null) {
				msg.add("*" + "Informe a data de término da apresentação!");
			}
			
			if (msg.size() == 0) {
				this.apresentacaos.save(apresentacao);
				msg.add("Salvo com sucesso!");
				mv = new ModelAndView("redirect:/apresentacaos/");
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
		List<Apresentacao> todosApresentacaos = apresentacaos.findAllApresentacaos();
		ModelAndView mv = new ModelAndView("ListaApresentacaos");
		mv.addObject("apresentacaos", todosApresentacaos);
		return mv;
	}
	
	@RequestMapping(params = { "mensagem" })
	public ModelAndView listarSucesso(@RequestParam(value = "mensagem") String mensagem) {
		List<Apresentacao> todosApresentacaos = apresentacaos.findAllApresentacaos();
		ModelAndView mv = new ModelAndView("ListaApresentacaos");
		mv.addObject("apresentacaos", todosApresentacaos);
		mv.addObject("mensagem",mensagem);
		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Apresentacao apresentacao) {
		ModelAndView mv = new ModelAndView("CadastroApresentacaos");
		mv.addObject(apresentacao);
		mv.addObject("temas",temas.findAll());
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		apresentacaos.delete(id);
		// attributes.addFlashAttribute("mensagem", "Título excluído com
		// sucesso!");
		return "redirect:/apresentacaos";
	}

	@RequestMapping(params = { "id", "action" })
	public ModelAndView excluir2(@RequestParam(value = "id") long id, @RequestParam(value = "action") String action) {
	
		Cont c = contador.findAvaliacaoByApresentacao((int)id);
		if (c.getCont() > 0) {
			List<String> msg = new ArrayList<String>();
			msg.add("*" + "Não é possível remover esta apresentação pois ela já foi avaliada!");
			ModelAndView mv = this.pesquisar();
			mv.addObject("mensagem_erro", msg);
			return mv;
		}
		if (action.equals("delete"))
			apresentacaos.delete(id);
		ModelAndView mv = new ModelAndView("ListaApresentacaos");
		List<String> msg = new ArrayList<>();
		msg.add("Excluido com sucesso!");
		mv = this.pesquisar();
		mv.addObject("mensagem", msg);
		return mv;
	}

}
