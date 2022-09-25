package ru.sold_out.serving_web.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sold_out.serving_web.entity.additional_info.Role;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@DynamicUpdate
@Table(name = "usr")
public class User implements Serializable, UserDetails {
	@Serial
	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean active;

	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;

	@OneToOne(
			mappedBy = "user",
			cascade = {
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH,
					CascadeType.REMOVE
			}
	)
	private CatOwner catOwner;

	public User(String username, String password, Boolean active, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive();
	}
}
