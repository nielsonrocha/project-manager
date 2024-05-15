package com.github.nielsonrocha.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping(value = "/")
  public String showWelcomePage(ModelMap model) {
    model.put("name", "Nielson");
    return "index";
  }
}
