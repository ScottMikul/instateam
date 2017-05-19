package org.teamtreehouse.instateam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String getStuff(@PathVariable int id, Model model){
        Project project = service.findById(id);

       HashMap<String,String> map = new HashMap<>();
        for(Role r1:project.getRolesneeded()){
            for(Collaborator c1: project.getCollaborators()){
                if(r1.getId()==c1.getRole().getId()){
                    map.put(r1.getName(),c1.getName());
                }
                else{
                    map.put(r1.getName(),"[Unassigned]");
                }
            }
        }
        model.addAttribute("roles",map);
        model.addAttribute("projectid", project.getId());
       return "project_detail";
    }

    @RequestMapping("/editproject/{id}")
    public String editProjectPage(@PathVariable int id,Model model){
        Project project = service.findById(id);
        List<Role> roles = rService.findAll();
        model.addAttribute("project",project);
        model.addAttribute("allroles",roles);
        model.addAttribute("action","edit");

        return "edit_project";
    }

    @RequestMapping("/newproject")
    public String addProjectPage(Model model){
        List<Role> roles = rService.findAll();
        model.addAttribute("allroles",roles);
        model.addAttribute("action","add");
        return "edit_project";
    }

    @RequestMapping(value ="/editproject", method = RequestMethod.POST)
    public String editProject(@Valid Project project) {

        for(Role role : project.getRolesneeded()){
            role.setName(rService.findById(role.getId()).getName());
        }

        service.save(project);
        return "redirect:/";
    }



}
