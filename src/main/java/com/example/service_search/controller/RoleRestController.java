package com.example.service_search.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.service_search.dto.RoleDto;
import com.example.service_search.model.entity.Role;
import com.example.service_search.service.impl.RoleServiceImpl;

@RestController
@RequestMapping(value = "/roles")
public class RoleRestController {
	
	private final RoleServiceImpl roleService;
	
	@Autowired
	public RoleRestController(RoleServiceImpl roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping(value = "")
	public List<Role> findAllRoles() {
		return roleService.getAll();
	}
	
	@GetMapping(value = "/{roleId}")
	public Role findRoleById(@PathVariable Integer roleId) {
		return roleService.readById(roleId);
	}
	
	@PostMapping(value = "/")
	public void createRole(@RequestBody Role role) {
		roleService.createOrUpdate(role);
	}
	
	@PutMapping(value = "/{roleId}")
	public ResponseEntity<Role> updateRole(@PathVariable Integer roleId, @RequestBody Role role) {
		Role updatedRole = roleService.readById(roleId);
		updatedRole.setName(role.getName());
		roleService.createOrUpdate(updatedRole);
		return ResponseEntity.ok(updatedRole);
	}
	
	@DeleteMapping(value = "/{roleId}")
	public void deleteRole(@PathVariable Integer roleId) {
		roleService.delete(roleId);
	}
	
	//RETURNS EVERYTHING
	@GetMapping(value = "/all")
	public ModelAndView getAllRoles() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role_views/Role");
		modelAndView.getModel().put("roles", roleService.getAll());
		return modelAndView;
	}
	
	//RETURNS BY ID
	@GetMapping(value = "/by_id/{roleId}")
	public ModelAndView getRoleById(@PathVariable Integer roleId) throws IOException {
		
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			modelAndView.setViewName("role_views/RoleDetails");
			modelAndView.getModel().put("role", roleService.readById(roleId));
			return modelAndView;
		} catch(NoSuchElementException e) {
			modelAndView.setViewName("role_views/NoSuchRole");
			modelAndView.getModel().put("roleId", roleId);
			e.printStackTrace();
			return modelAndView;
		}
	}
	
	//CREATES A NEW ROLE
	@GetMapping(value = "/show_role_form")
	public ModelAndView showRoleForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role_views/AddRole");
		modelAndView.getModel().put("role", new RoleDto());
		return modelAndView;
	}
	
	@PostMapping(value = "")
	public ModelAndView addNewRole(@ModelAttribute(name = "role") RoleDto roleDto) {
		Role role = new Role();
		role.setName(roleDto.getName());
		roleService.createOrUpdate(role);
		return new ModelAndView("redirect:/roles/all");
	}
	
	//UPDATES A ROLE
	@GetMapping(value = "/edit/{roleId}")
    public ModelAndView showEditRoleForm(@PathVariable Integer roleId, @ModelAttribute (name = "newRole") Role newRole) {
        Role role = roleService.readById(roleId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("role_views/UpdateRole");
        modelAndView.addObject("role", role);
        modelAndView.getModel().put("oldName", role.getName());
        return modelAndView;
    }
	
	@PostMapping(value = "/updated")
	public ModelAndView updateRole(@ModelAttribute("role") RoleDto roleDto) {
		Role role = roleService.readById(roleDto.getRoleId());
		
		if(roleDto.getName().length() == 0) {
			role.setName(role.getName());
		} else if(roleDto.getName().length() > 0) {
			role.setName(roleDto.getName());
		}
		
		roleService.createOrUpdate(role);
		return new ModelAndView("redirect:/roles/all");
	}
	
	//@PostMapping("/updated")
    //public ResponseEntity<Role> update(@RequestBody Role roleDetails, HttpServletResponse response) throws IOException {
        //Role role = roleService.readById(roleDetails.getRoleId());
        //role.setName(roleDetails.getName());
		//roleService.createOrUpdate(role);
        //response.sendRedirect("/roles/all");
        //return ResponseEntity.ok(role);
    //}
	
	//DELETES A ROLE
	@GetMapping(value = "/delete/{roleId}")
	public ResponseEntity<Integer> deleteARole(@PathVariable Integer roleId, HttpServletResponse response) throws IOException {
		roleService.delete(roleId);
		response.sendRedirect("/roles/all");
	    return new ResponseEntity<Integer>(roleId, HttpStatus.OK);
	}
	
}
