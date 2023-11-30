package com.servicebid.apis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servicebid.apis.models.User;
import com.servicebid.apis.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String namepart){
        try{

            List<User> users = new ArrayList<User>();
            
            if(namepart==null){
                userRepository.findAll().forEach(users::add);
            }else{
                userRepository.findByNameContaining(namepart).forEach(users::add);
            }

            if(users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		Optional<User> userData = userRepository.findByUserID(id);
		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/user/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User _user = userRepository
					.save(new User(user.getEmailAddress(), user.getName(), user.getMobileNumber(), user.getStatus(), user.getIsEnabled()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/user/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User _user = userData.get();
			if(user.getName()!=null && !user.getName().isBlank())_user.setName(user.getName());
			if(user.getMobileNumber()!=null && !user.getMobileNumber().isBlank())_user.setMobileNumber(user.getMobileNumber());
			if(user.getStatus()!=null && !user.getStatus().isBlank())_user.setStatus(user.getStatus());
			// the UX calling this api must always pass isEnabled:true, other wise it will be always set to false
			// if it passes, it will be set to the passed (boolean) value
			_user.setIsEnabled(user.getIsEnabled());
			return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<Exception>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @DeleteMapping("/users/deleteall")
	// public ResponseEntity<HttpStatus> deleteAllUsers() {
	// 	try {
	// 		userRepository.deleteAll();
	// 		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}

	// }

	@GetMapping("/users/enabled")
	public ResponseEntity<List<User>> findByEnabled() {
		try {
			List<User> users = userRepository.findByIsEnabled(true);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
