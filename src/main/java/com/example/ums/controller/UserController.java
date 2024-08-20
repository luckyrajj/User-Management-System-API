package com.example.ums.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ums.exception.UserNotFoundByIdException;
import com.example.ums.requestdto.UserRequest;
import com.example.ums.responsedto.UserResponse;
import com.example.ums.service.UserService;
import com.example.ums.util.AppResponseBuilder;
import com.example.ums.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;




@RestController
public class UserController {

	private UserService userService;
	private AppResponseBuilder responseBuilder;

	public UserController(UserService userService,AppResponseBuilder responseBuilder) {
		super();
		this.userService = userService;
		this.responseBuilder=responseBuilder;
	}
	@Operation(description = "This API endpoint is used to save the user in the database,where you "
			+ "don't have to pass the unique indentifier value it will automatically get generated. The endpoint required the "
			+ "body along with it and response with saved user object ",responses = {
					@ApiResponse(responseCode = "201",description = "user created"),
					@ApiResponse(responseCode = "500",description = "Internal Server Error",content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest) {
		UserResponse response=userService.saveUser(userRequest);
		return responseBuilder.success(HttpStatus.CREATED, "user created", response);
	}
	
	@Operation(description = "This API endpoint is used to find the user based on the unique identifer "
			+ "from the database. The endpoint required the path variable `userId` and response with user data",responses = {
					@ApiResponse(responseCode = "302",description = "user object found"),
					@ApiResponse(responseCode = "404",description = "failed to fing user",content = {
							@Content(schema = @Schema(anyOf = UserNotFoundByIdException.class))
					})
			})
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable int userId) {
		UserResponse response=userService.findUserById(userId);
		return responseBuilder.success(HttpStatus.FOUND, "user found ", response);
	}
	
	@Operation(description = "This API endpoint is used to update the  exisiting user based ont the unique identifer. The endpoint"
			+ " require the path variable `userId` and the `body` and response with the updated user data",responses = {
					@ApiResponse(responseCode = "200",description = "user updated successfully"),
					@ApiResponse(responseCode = "404",description = "failed to update user",content = {
							@Content(schema = @Schema(anyOf = UserNotFoundByIdException.class))
					})
			})
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable int  userId, @RequestBody  UserRequest userRequest) {
		UserResponse userResponse=userService.updateUserById(userId,userRequest);
		return responseBuilder.success(HttpStatus.OK,"user updated successfully", userResponse);
	}

	@Operation(description = "This API endpoint is used to delete the already existing user based on the "
			+ " unique identifier. The endpoint require the path variable `userId` and response with deleted user data",responses = {
					@ApiResponse(responseCode = "200",description = "user deleted successfully"),
					@ApiResponse(responseCode = "404",description = "failed to delete user",content = {
							@Content(schema = @Schema(anyOf = UserNotFoundByIdException.class))
					})
			})
	@DeleteMapping("users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId){
		UserResponse response=userService.deleteUser(userId);
		return responseBuilder.success(HttpStatus.OK, "user deleted successfully", response);
	}
	
	@Operation(description = "This API endpoint is used to find the list of all users from the database",responses = {
			@ApiResponse(responseCode = "302",description = "list of user found"),
			@ApiResponse(responseCode = "404",description = "no list of user found",content = {
					@Content(schema = @Schema(anyOf = UserNotFoundByIdException.class))
			})
	})
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser() {
		List<UserResponse> users=userService.findAllUsers();
		return responseBuilder.success(HttpStatus.FOUND, "users list found successfully", users);
	}
	
	
}
