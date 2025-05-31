// src/main/java/br/com/breez/breez/controller/KnowledgeController.java
package br.com.breez.breez.controller;

import br.com.breez.breez.model.Knowledge;
import br.com.breez.breez.service.KnowledgeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/conhecimento")
public class KnowledgeController {

    private final KnowledgeService knowledgeService;

    public KnowledgeController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    /** Lista todos os artigos cadastrados */
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("articles", knowledgeService.findAll());
        return "knowledge_list";
    }

    /** Exibe o formulário de criação de um novo artigo */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("knowledge", new Knowledge());
        model.addAttribute("formTitle", "Cadastrar Novo Artigo");
        return "knowledge_form";
    }

    /** Salva um novo artigo (ou atualização, se já existir ID) */
    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("knowledge") Knowledge knowledge,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            // Se houver erros de validação, retorna ao formulário
            model.addAttribute("formTitle", knowledge.getId() == null ? "Cadastrar Novo Artigo" : "Editar Artigo");
            return "knowledge_form";
        }
        knowledgeService.save(knowledge);
        return "redirect:/conhecimento";
    }

    /** Exibe o formulário de edição de um artigo existente */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Knowledge obj = knowledgeService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artigo não encontrado: " + id));
        model.addAttribute("knowledge", obj);
        model.addAttribute("formTitle", "Editar Artigo");
        return "knowledge_form";
    }

    /** Exclui um artigo */
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        knowledgeService.deleteById(id);
        return "redirect:/conhecimento";
    }

    /** Exibe detalhes de um único artigo */
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Knowledge obj = knowledgeService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artigo não encontrado: " + id));
        model.addAttribute("article", obj);
        return "knowledge_view";
    }
}
