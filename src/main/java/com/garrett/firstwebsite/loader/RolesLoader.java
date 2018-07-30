package com.garrett.firstwebsite.loader;

import com.garrett.firstwebsite.role.Role;
import com.garrett.firstwebsite.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RolesLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){

            Role isAdmin = roleRepository.findByRole("ADMIN");
            if (isAdmin == null) {
                Role admin = new Role(1, "ADMIN");
                roleRepository.save(admin);
            }


            Role isUser = roleRepository.findByRole("USER");

            if (isUser == null) {
                Role user = new Role(2, "USER");
                roleRepository.save(user);
            }
    }
}
