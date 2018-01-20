package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@ToString
@NoArgsConstructor
@Data
public class Task extends BaseEntity {
}
