package TaskTracker.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Comment implements Serializable{
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "body", nullable = false, length = 1024)
    private String body;

    @ManyToOne
    @JoinColumn(name="id",insertable = false, updatable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name="id",insertable = false, updatable = false)
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                Objects.equals(body, comment.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, body);
    }
}
