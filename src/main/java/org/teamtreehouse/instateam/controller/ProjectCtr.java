package org.teamtreehouse.instateam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teamtreehouse.instateam.model.Collaborator;
import org.teamtreehouse.instateam.model.Project;
import org.teamtreehouse.instateam.model.Role;
import org.teamtreehouse.instateam.service.ProjectService;
import org.teamtreehouse.instateam.service.RoleService;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by scott on 5/11/2017.
 */
@Controller
public class ProjectCtr {
    @Autowired
    ProjectService service;

    @Autowired
    RoleService rService;

    @RequestMapping("/")
    public String home(Model map){
        map.addAttribute("projects", service.findAll());
        return "index";
    }

    @RequestMapping("/projects/{id}")
    public String projectDetailPage(@PathVariable int id, Model model){
        Project project = service.findById(id);

       HashMap<String,String> map = new HashMap<>();
        for(Role r1:project.getRolesneeded()){
            map.put(r1.getName(),"[Unassigned]");
            for(Collaborator c1: project.getCollaborators()){
                if(r1.getId()==c1.getRole().getId()){
                    map.put(r1.getName(),c1.getName());
                    break;
                }
            }
        }
        model.addAttribute("roles",map);
        model.addAttribute("projectid", project.getId());
        model.addAttribute("projectname",project.getName());
       return "project_detail";
    }

    @RequestMapping("/editproject/{id}")
    public String editProjectPage(@PathVariable int id,Model model) {
        Project project = service.findById(id);
        List<Role> roles = rService.findAll();
        if(!model.containsAttribute("project")){
            List<Role> projectroles = project.getRolesneeded();
            int counter = 0;
            for (Role r : roles) {
                if (counter == projectroles.size()|| projectroles.get(counter) == null  || projectroles.get(counter).getId() != r.getId()) {
                    projectroles.add(counter, new Role());
                }
                counter++;
            }

            if (project.getCollaborators() == null) {
                project.setCollaborators(new ArrayList<>());
            }

            project.setRolesneeded(projectroles);
            model.addAttribute("project", project);
        }
        model.addAttribute("allroles",roles);
        model.addAttribute("action","edit");
        model.addAttribute("statuses",Status.values());
        return "edit_project";
    }

    @RequestMapping(value ="/editproject", method = RequestMethod.POST)
    public String editProject(@Valid Project project, BindingResult result, RedirectAttributes redirectAttributes) {
        String redirectString = String.format("redirect:/editproject/%d",project.getId());
        if(project.getId()==0){
            redirectString = "redirect:/";
        }
        if(result.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.project", result);
            redirectAttributes.addFlashAttribute("project",project);
            if (project.getId() == 0) {
                redirectString = "redirect:/newproject";
            }
            return redirectString;
        }

        List<Role> rolesNeeded = new ArrayList<>();
        int counter =0;
        for(Role role: project.getRolesneeded()){
            if (role.getId() != 1) {
                Role r = rService.findById(role.getId());
                rolesNeeded.add(r);
            }
        }

        project.setRolesneeded(rolesNeeded);

        if(project.getCollaborators()==null) {
            if (project.getId() == 0) {
                project.setCollaborators(new ArrayList<>());
            } else {
                project.setCollaborators(service.findById(project.getId()).getCollaborators());

            }
        }

        service.save(project);
        return redirectString;
    }

    @RequestMapping("/newproject")
    public String addProjectPage(Model model){
        List<Role> roles = rService.findAll();
        model.addAttribute("allroles",roles);
        model.addAttribute("action","add");
        if(!model.containsAttribute("project")){
            model.addAttribute("project",new Project());
        }
        model.addAttribute("statuses",Status.values());
        return "edit_project";
    }


}