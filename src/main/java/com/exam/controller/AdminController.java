package com.exam.controller;

import com.exam.model.entities.biler.LejeAftale;
import com.exam.service.LejeaftaleService;
import com.exam.utilities.BilAbonnement;
import com.exam.utilities.NoCarReadyToRentOutException;
import com.exam.utilities.RentingOutNoneReadyCarException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

  private final LejeaftaleService lejeaftaleService = new LejeaftaleService();

  // Marcus
  @GetMapping("/Admin/RegistrerAftale")
  public String registrerAftale(HttpSession session, Model model) {
    LejeAftale simuleretLejeAftale = setSessionAftale(session);
    model.addAttribute("lejeaftale", simuleretLejeAftale);
    return "RegistrerLejeAftale";
  }

  //note bare test
  @GetMapping("/Admin/RegistrerAftale/createAftale")
  public String createAftale(HttpSession session) {
    LejeAftale sessionAftale = (LejeAftale) session.getAttribute("sessionLejeAftale");
    session.invalidate();
    try {
      lejeaftaleService.createLejeaftale(sessionAftale);
      return "redirect:/Admin/RegistrerAftale";
    } catch (RentingOutNoneReadyCarException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at create, ud fra en HttpSession, den simulerede LejeAftale: " + sessionAftale);
      return "redirect:/Admin";
    }
  }

  @GetMapping("/Admin/RegistrerAftale/deleteAftale")
  public String deleteAftale(HttpSession session) {
    session.invalidate();
    return "redirect:/Admin/RegistrerAftale";
  }

  private LejeAftale setSessionAftale(HttpSession session) {
    try {
      LejeAftale sessionLejeAftale = (LejeAftale) session.getAttribute("sessionLejeAftale");
      if (sessionLejeAftale != null) {
        return sessionLejeAftale;
      } else {
        LejeAftale simuleretLejeAftale = BilAbonnement.simulateLejeAftale();
        session.setAttribute("sessionLejeAftale", simuleretLejeAftale);
        return simuleretLejeAftale;
      }
    } catch (NoCarReadyToRentOutException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at sætte en HttpSession for en simuleret LejeAftale, da der ikke er flere biler der kan udlejes.");
      return null;
    }
  }

}
