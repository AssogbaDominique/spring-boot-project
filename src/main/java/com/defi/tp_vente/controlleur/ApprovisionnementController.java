package com.defi.tp_vente.controlleur;

import com.defi.tp_vente.model.Approvisionnement;
import com.defi.tp_vente.model.Article;
import com.defi.tp_vente.service.ApprovisionnementService;
import com.defi.tp_vente.service.ArticleService;
import com.defi.tp_vente.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class ApprovisionnementController {
    @Autowired
    private ApprovisionnementService approvisionnementService;
    @Autowired
    private ArticleService articleService;
    @GetMapping("/appro/show")
    public String showAllArticle(Model model){
        model.addAttribute("listeAppro",approvisionnementService.showAllApprovisionnements());
        return "Approvisionnement/ListeAppro";
    }
    @GetMapping("/appro/form")
    public String ShowFormArticle(Model model){
        model.addAttribute("listeArticle",articleService.showAllArticles());
        return "Approvisionnement/formAppro";
    }
    @PostMapping("/appro/save")
    public String saveApprovisionnement(Approvisionnement approvisionnement){
        approvisionnement.setQteAppro(approvisionnement.getQteAppro());
        approvisionnement.setDateCreation(LocalDate.now());
        approvisionnementService.saveApprovisionnement(approvisionnement);
        articleService.updateStockArticle(approvisionnement.getQteAppro(), approvisionnement.getArticle_id());
        return "redirect:/appro/show";
    }
    @GetMapping("/appro/edit/{id}")
    public String formEdit(@PathVariable("id") int id, Model model){
        model.addAttribute( "Un_appro",approvisionnementService.showOneApprovisionnement(id));
        model.addAttribute("listeArticle",articleService.showAllArticles());
        return "Approvisonement/formAppro";
    }
    @PostMapping("/appro/update")
    public String updateArticle(@ModelAttribute("approvisionnement") Approvisionnement approvisionnement)
    {
        approvisionnementService.saveApprovisionnement(approvisionnement);
        return "redirect:/article/show";
    }
    @GetMapping("/appro/delete/{id}")
    public String deleteArticle(@PathVariable("id") int id){
        articleService.deleteArticle(id);
        return "redirect:/appro/show";
    }
}
