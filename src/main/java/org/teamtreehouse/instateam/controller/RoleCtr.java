package org.teamtreehouse.instateam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teamtreehouse.instateam.model.Role;
import org.teamtreehouse.instateam.service.RoleService;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ValidateOnExecution;
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
        if(!model.containsAttribute("rolelist")){
            List<Role> roles = service.findAll();
            RoleListWrapper wrapper = new RoleListWrapper(roles);
            model.addAttribute("rolelist", wrapper);
        }
        if (!model.containsAttribute("role")) {
            model.addAttribute("role", new Role());
        }
        return "roles";
    }

    @RequestMapping("/addrole")
    public String addRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.role", result);
            redirectAttributes.addFlashAttribute("role",role);
            return "redirect:/roles";
        }
        service.save(role);
        return "redirect:/roles";
    }

    @RequestMapping(value="/manageroles", method= RequestMethod.POST)
    public String manageRoles(@Valid RoleListWrapper wrapper, BindingResult result, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.rolelist", result);
            redirectAttributes.addFlashAttribute("rolelist",wrapper);
            return "redirect:/roles";
        }

        for(Role role: wrapper.getRoles()){

            service.save(role);

        }
        return "redirect:/roles";
    }


}
