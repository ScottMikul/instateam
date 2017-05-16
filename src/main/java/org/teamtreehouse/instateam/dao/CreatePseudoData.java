package org.teamtreehouse.instateam.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.teamtreehouse.instateam.model.Collaborator;
import org.teamtreehouse.instateam.model.Project;
import org.teamtreehouse.instateam.model.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scott on 5/14/2017.
 */
public class CreatePseudoData {
    @Autowired
    static ProjectDao projectDao;

    public static void begin(){
        createRoles();
        createCollaborators();
        createProjects();
    }
    private static void createRoles(){

    }
    private static void createProjects(){
        //ArrayList<Role> roles = new ArrayList<Role>();
        //roles.add(new Role(1,"Pooper"));
        //ArrayList<Collaborator> collaborators = new ArrayList<Collaborator>();
        //collaborators.add(new Collaborator(1,"name",new Role(1,"Pooper")));
        List<Project> projectList = new ArrayList<Project>();
        Project project1 = new Project.ProjectBuilder()
                .withName("websiteProject")
                .withStatus("good")
                .withDescription("Building a brand new wsbite project")
                .build();

        projectList.add(project1);

        for(Project p : projectList){
            //System.out.println(p.getDescription());
            projectDao.save(p);
        }
    }
    private static void createCollaborators(){

    }
}
