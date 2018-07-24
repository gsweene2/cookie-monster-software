package com.garrett.firstwebsite.security;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {

        //SecurityContext securityContext = SecurityContextHolder.getContext();
        //Authentication authentication = securityContext.getAuthentication();
        String id = null;
        Long myUserId = 0L;
        if (authentication != null)
            if (authentication.getPrincipal() instanceof UserDetails)
                id = ((UserDetails) authentication.getPrincipal()).getUsername();
            else if (authentication.getPrincipal() instanceof String)
                id = (String) authentication.getPrincipal();
        try {
            myUserId =  Long.valueOf(id != null ? id : "1");
        } catch (NumberFormatException e) {
        }

        User me = (User)authentication.getPrincipal();


        SecurityContextHolder.getContext().getAuthentication().getDetails();

        /*return "Name: " + authentication.getName() + " " +
                "Authorities: " + authentication.getAuthorities() + " " +
                "Details: "+ authentication.getDetails().toString() + " " +
                "Principal: " + authentication.getPrincipal() + " " +
                "Credentials: " + authentication.getCredentials() + " " +
                "User ID: " + String.valueOf(myUserId);
                */
        return "id: " + me.getUsername() + " " + me.getAuthorities();
    }
}
