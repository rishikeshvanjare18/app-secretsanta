package com.rishi.SecretSantaBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "myUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer username;
    private String name;
    private String email;
    private String password;
    
    private boolean assigned;
    
    @ManyToOne
    @JoinColumn(name = "assigned_child_id")
    private User assignedChild;

    @OneToOne(mappedBy = "assignedChild")
    private User santa;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUsername() {
		return username;
	}
	public void setUsername(Integer username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getAssignedChild() {
		return assignedChild;
	}
	public void setAssignedChild(User assignedChild) {
		this.assignedChild = assignedChild;
	}
	public User getSanta() {
		return santa;
	}
	public void setSanta(User santa) {
		this.santa = santa;
	}
	public boolean isAssigned() {
		return assigned;
	}
	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
}
