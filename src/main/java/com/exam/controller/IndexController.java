package com.exam.controller;

import com.exam.model.entities.biler.Bil;
import com.exam.repository.BilRepo;
import com.exam.service.LejeaftaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

// Flavie
@Controller
public class IndexController {
    private BilRepo repo= new BilRepo();
    private LejeaftaleService service= new LejeaftaleService();
    private List<Bil> BilService= new ArrayList<>();

    @GetMapping("/")
    public String showHomepage(){
        return "index";
    }
    @GetMapping("/Medarbejder")
    public String showMedarbejder(){
        return "MedarbejderHome";
    }

    @GetMapping("/Admin")
    public String showAdminSide(){ return "Admin";}

    @GetMapping("/værksted")
    public String showManagementHome(Model model){
        model.addAttribute("Cprnummer","");
        model.addAttribute("LejeaftaleID","");
        model.addAttribute("SkadeType","");
        model.addAttribute("Skadetypepris", "");
        model.addAttribute("SkadetypeID ", "");
        return "værksted";
    }

    @GetMapping("/EditKunde")
    public String showCreatePage(){
        return "EditKunde";
    }
    @GetMapping("/Udvikler")
    public String showUdvikler(){
        return "Udvikler";
    }
    @GetMapping("/værksted")
    public String showVærksted(){
        return "værksted";
    }

}
