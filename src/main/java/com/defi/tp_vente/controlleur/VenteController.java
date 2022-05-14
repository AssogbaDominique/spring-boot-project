package com.defi.tp_vente.controlleur;

import com.defi.tp_vente.model.Vente;
import com.defi.tp_vente.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VenteController {
    @Autowired
    private VenteService venteService;
    @GetMapping("/vente/show")
    public String showAllVentes(Model model)
    {
        model.addAttribute("Liste_Ventes",venteService.showAllVentes());
        return "vente/Liste_Ventes";
    }
    @GetMapping("/vente/form")
    public String showFromVente() { return  "Vente/formvente"; }
    @PostMapping(name = "/vente/save")
    public String saveVente(Vente vente)
    {
        venteService.saveVente(vente);
        return "redirect:/vente/show";
    }
}
