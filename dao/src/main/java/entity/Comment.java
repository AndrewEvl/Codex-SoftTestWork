package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@ToString
@NoArgsConstructor
@Data
public class Comment extends BaseEntity {

    private String text;
    private Project project;
    private User user;

}
