package ru.sold_out.serving_web.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "fullName", "dayOfBirth"})
@DynamicUpdate
public class CatOwner implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String fullName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dayOfBirth;

	@OneToMany(
			mappedBy = "catOwner",
			fetch = FetchType.LAZY,
			cascade = {
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH,
					CascadeType.REMOVE
			}
	)
	private Set<Cat> cats;

	@OneToOne(
			cascade = {
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH,
			})
	@JoinColumn(name = "usr_id", unique = true)
	private User user;

	public CatOwner(String fullName, Date dayOfBirth, User user) {
		this.fullName = fullName;
		this.dayOfBirth = dayOfBirth;
		this.user = user;
	}

	public CatOwner(Long id, String fullName, Date dayOfBirth, User user) {
		this.id = id;
		this.fullName = fullName;
		this.dayOfBirth = dayOfBirth;
		this.user = user;
	}
}
