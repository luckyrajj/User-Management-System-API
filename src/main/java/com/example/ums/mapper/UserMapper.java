package com.example.ums.mapper;

import org.springframework.stereotype.Component;

import com.example.ums.entity.User;
import com.example.ums.requestdto.UserRequest;
import com.example.ums.responsedto.UserResponse;

@Component
public class UserMapper {

	public User mapToUserEntity(UserRequest userRequest,User user) {
		user.setUserName(userRequest.getUserName());
		user.setUserEmail(userRequest.getUserEmail());
		user.setUserPassword(userRequest.getUserPassword());
		user.setUserContact(userRequest.getUserContact());
		return user;
	}
	
	public UserResponse mapToUserResponse(User user) {
		UserResponse response=new UserResponse();
		response.setUserId(user.getUserId());
		response.setUserName(user.getUserName());
		response.setUserEmail(user.getUserEmail());
		response.setUserContact(user.getUserContact());
		return response;
	}
}
