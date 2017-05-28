package org.teamtreehouse.instateam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teamtreehouse.instateam.model.Collaborator;
import org.teamtreehouse.instateam.model.Project;
import org.teamtreehouse.instateam.model.Role;
import org.teamtreehouse.instateam.service.CollaboratorService;
import org.teamtreehouse.instateam.service.ProjectService;
import org.teamtreehouse.instateam.service.RoleService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by scott on 5/11/2017.
 */
@Controller
public class CollaboratorCtrl {
    @Autowired
    CollaboratorService service;

    @Autowired
    ProjectService pService;

    @Autowired
    RoleService rService;

    @RequestMapping("/collaborators")
    public String collaboratorsPage(Model model){
        List<Collaborator> list = service.findAll();
        CollaboratorListWrapper wrapper = new CollaboratorListWrapper(list);
        model.addAttribute("wrapper",wrapper);
        List <Role> allroles = rService.findAll();
        model.addAttribute("allroles",allroles);
        if(!model.containsAttribute("collab")) model.addAttribute("collab", new Collaborator());
        return "collaborators";
    }

    @RequestMapping(value="/addcollab", method = RequestMethod.POST)
    public String addCollab(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.collab", result);
            redirectAttributes.addFlashAttribute("collab",collaborator);
            return "redirect:/collaborators";
        }
        Role role = rService.findById(collaborator.getRole().getId());
        collaborator.setRole(role);
        service.save(collaborator);
        return "redirect:/collaborators";
    }

    @RequestMapping(value="/addProjectCollab/{projectid}", method = RequestMethod.POST)
    public String addProjectCollab(@RequestParam int id, @PathVariable int projectid){
        String redirectString = String.format("redirect:/project_collaborators/%d",projectid);
        Project project = pService.findById(projectid);
        List<Collaborator> list = project.getCollaborators();
        Collaborator collaborator = service.findById(id);
        list.add(collaborator);
        project.setCollaborators(list);
        pService.save(project);
        return redirectString;
    }

    @RequestMapping("/project_collaborators/{id}")
    public String showProjectCollaboratorManager(@PathVariable int id, Model model ){
        Project project = pService.findById(id);
        CollaboratorListWrapper collabs = new CollaboratorListWrapper(project.getCollaborators());
        model.addAttribute("wrapper",collabs);
        List<Collaborator> allCollaborators = service.findAll();
        List<Collaborator> collaboratorsToRemove = new ArrayList<>();
        for (Collaborator r :allCollaborators) {
            collaboratorsToRemove.addAll(project.getCollaborators().stream().filter(c -> r.getId() == c.getId()).map(c -> r).collect(Collectors.toList()));
        }

        allCollaborators.removeAll(collaboratorsToRemove);
        model.addAttribute("allcollabs", allCollaborators);
        model.addAttribute("collab",new Collaborator());
        List<Role> allroles = rService.findAll();
        model.addAttribute("allroles", allroles);
        model.addAttribute("id", id);
        return "project_collaborators";
    }

    @RequestMapping(value = "/project_collaborators" ,method = RequestMethod.POST)
    public String processCollaboratorManagerRequsest(CollaboratorListWrapper wrapper, BindingResult result){

        service.saveAll(wrapper.getCollabs());
        return "redirect:/";
    }

    @RequestMapping(value="/managecollabs", method = RequestMethod.POST)
    public String manageCollaborators(CollaboratorListWrapper collabs, BindingResult result){

        service.saveAll(collabs.getCollabs());
        return "redirect:/collaborators";
    }
}
