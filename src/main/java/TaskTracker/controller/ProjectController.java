package TaskTracker.controller;

import TaskTracker.model.Project;
import TaskTracker.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") int id){
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok().body(project);
    }

    @GetMapping("/project")
    public ResponseEntity<List<Project>> getProjectById(){
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok().body(projects);
    }

    @PostMapping("/project")
    public ResponseEntity<?> addProject(@RequestBody Project project){
        return ResponseEntity.ok().body(projectService.save(project));

    }

    @PutMapping("/project/{projectId}/{developerId}")
    public ResponseEntity<?> addDeveloper(
            @PathVariable("projectId")Integer projectId,
            @PathVariable("developerId")Integer developerId){
        return ResponseEntity.ok().body(projectService.addDeveloper(projectId,developerId));
    }


}
