package org.teamtreehouse.instateam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teamtreehouse.instateam.service.CollaboratorService;

/**
 * Created by scott on 5/11/2017.
 */
@Controller
public class CollaboratorCtrl {
    @Autowired
    CollaboratorService service;

    @RequestMapping("/collaborators")
    public String collaboratorsPage(){

        return "collaborators";
    }
}
