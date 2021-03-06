package com.Association.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    /**
     * méthode de connexion d'un membre
     * @param model
     * @param error
     * @param logout
     * @return page home si erreur retourne la page login
     */
    @RequestMapping(value = "/login")
    public ModelAndView loginGet(Model model, @RequestParam(value = "error", required = false)String error, @RequestParam(value = "logout", required = false) String logout){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String errorMessage = null;
        if(error != null) {
            logger.error("l'utilisateur a saisi un nni ou un mot de passe incorrect");
            errorMessage = "nni ou mot de passe incorrects !";
        }
        if(logout != null) {
            errorMessage = "Vous avez été déconecté avec succès !";
            logger.info("l'utilisateur c'est déconnecté");
        }
        model.addAttribute("errorMessge", errorMessage);

        if(!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("/login");
    }



   /**
     * méthode pour déconnecter un membre
     * @param request request  from http  server
     * @param response response from http server
     * @return page home
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            logger.info("Le Membre "+ SecurityContextHolder.getContext().getAuthentication().getName()+" s'est déconnecté");
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("home");
    }
}
