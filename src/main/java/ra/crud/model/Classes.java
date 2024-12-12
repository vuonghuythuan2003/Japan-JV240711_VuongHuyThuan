package ra.crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Classes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="class_id")
    private int classId;
    @NotBlank(message="Tên lớp không được để trống")
    @Size(max=100, message="Tên lớp không được vượt quá 100 ký tự")
    @Column(name="class_name", columnDefinition = "varchar(100)", unique = true, nullable = false)
    private String className;
    @Column(name="majors", nullable = false)
    @NotBlank(message="Chuyên nghành không được để trống")
    private String majors;
}
