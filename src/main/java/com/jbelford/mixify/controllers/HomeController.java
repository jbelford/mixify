package com.jbelford.mixify.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model, OAuth2AuthenticationToken token) throws Exception {
        model.addAttribute("username", token.getPrincipal().getName());
        return "index";
    }

}