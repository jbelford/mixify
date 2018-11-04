package com.jbelford.mixify.controllers;

import com.jbelford.mixify.config.SpotifyConfigConstants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private static String AUTH_BASE_URI = "oauth2/authorization/" + SpotifyConfigConstants.REGISTRATION_ID;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("authorizationUrl", AUTH_BASE_URI);
        return "login";
    }

}