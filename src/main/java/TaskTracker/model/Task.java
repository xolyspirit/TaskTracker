package TaskTracker.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "task")
public class Task implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "id",insertable = false, updatable = false )
    Project project;

    @OneToMany(mappedBy = "task",orphanRemoval = true,fetch = FetchType.EAGER)
    Set<Comment> comments;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(status, task.status) &&
                Objects.equals(body, task.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, status, body);
    }
}
