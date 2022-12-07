package com.example.service_search.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	@Column(name = "name", columnDefinition = "TEXT", nullable = false)
	private String name;
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<User> users;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public Role() {
	}
	
	@Override
	public String toString() {
		return roleId.toString() + ". " + name.toLowerCase().substring(5);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roleId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	public boolean equals(Role role) {
		if(role == this) {
			return true;
		}
		if(role == null || role.getClass() != this.getClass()) {
			return false;
		}
		Role roleTwo = (Role) role;
		return roleId == roleTwo.roleId 
				&& (name == roleTwo.name || (name != null && name.equals(roleTwo.name)));
	}
	
}
