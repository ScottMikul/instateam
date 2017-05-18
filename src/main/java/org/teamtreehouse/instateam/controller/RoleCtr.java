package org.teamtreehouse.instateam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.teamtreehouse.instateam.model.Role;
import org.teamtreehouse.instateam.service.RoleService;

import java.util.List;

/**
 * Created by scott on 5/11/2017.
 */
@Controller
public class RoleCtr {
    @Autowired
    RoleService service;
    @RequestMapping("/roles")
    public String rolesPage(Model model){
        List<Role> roles = service.findAll();
        model.addAttribute("roles", roles);
        return "roles";
    }

    @RequestMapping("/addrole")
    public String addRole(@RequestParam String name){
        Role role = new Role();
        role.setName(name);
        service.save(role);
        return "redirect:/roles";
    }


}
