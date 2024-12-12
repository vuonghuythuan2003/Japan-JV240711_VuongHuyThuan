package ra.crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @Column(name="student_name",columnDefinition = "varchar(100)", unique = true, nullable = false)
    @NotBlank(message = "Tên sinh viên không được để trống")
    @Size(max = 100, message = "Tên sinh viên không được vượt quá 100 ký tự")
    private String studentName;

    @Column(name = "phone_number", columnDefinition = "varchar(11)", unique = true, nullable = false, length = 11)
    @NotBlank(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @Column(name = "email", columnDefinition = "varchar(100)", unique = true, nullable = false)
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    @Size(max = 100, message = "Email không được vượt quá 100 ký tự")
    private String studentEmail;

    @Column(name = "address",columnDefinition = "varchar(150)", nullable = false)
    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(max = 150, message = "Địa chỉ không được vượt quá 150 ký tự")
    private String studentAddress;

    @Column(name = "sex", nullable = false)
    private Boolean sex;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classes classEntity;

    @Column(name = "image_url", columnDefinition = "varchar(255)")
    @Size(max = 255, message = "Đường dẫn ảnh không được vượt quá 255 ký tự")
    private String imageUrl;

    @Column(name = "status", nullable = false)
    private Integer status;

}
