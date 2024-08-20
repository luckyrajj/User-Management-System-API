package com.example.ums.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ums.entity.User;
import com.example.ums.exception.UserNotFoundByIdException;
import com.example.ums.mapper.UserMapper;
import com.example.ums.repository.UserRepository;
import com.example.ums.requestdto.UserRequest;
import com.example.ums.responsedto.UserResponse;
@Service
public class UserService {

	private UserRepository userRepo;
	private UserMapper userMapper;

	public UserService(UserRepository userRepo,UserMapper userMapper) {
		super();
		this.userRepo = userRepo;
		this.userMapper=userMapper;
	}

	public UserResponse saveUser(UserRequest userRequest) {
		User user=userMapper.mapToUserEntity(userRequest, new User());
		user=userRepo.save(user);
		UserResponse userResponse=userMapper.mapToUserResponse(user);
		return userResponse;
	}

	public UserResponse findUserById(int userId) {
//		Optional<User>optional=userRepo.findById(userId);
//		User user=null;
//		if(optional.isPresent())
//			return userMapper.mapToUserResponse(optional.get());
//		else
//			throw new UserNotFoundByIdException("user not found by the respective Id");
		return userRepo.findById(userId).map(user->userMapper.mapToUserResponse(user))
				.orElseThrow(()-> new UserNotFoundByIdException("failed to find the user"));
	}

	public UserResponse updateUserById(int userId,UserRequest userRequest) {
//		Optional<User> optional=userRepo.findById(userId);
//		User user=null;
//		if(optional.isPresent()) {
//			user=userMapper.mapToUserEntity(userRequest, optional.get());
//		user=userRepo.save(user);
//		return userMapper.mapToUserResponse(user);
//		}
//		else {
//			throw new UserNotFoundByIdException("user not found by the respective Id");
//		}
		return userRepo.findById(userId).map(user->{
			userMapper.mapToUserEntity(userRequest, user);
			user=userRepo.save(user);
			return userMapper.mapToUserResponse(user);
		}).orElseThrow(()-> new UserNotFoundByIdException("failed to update the user"));
	}

	public UserResponse deleteUser(int userId) {
//		Optional<User> optional=userRepo.findById(userId);
//		User user=null;
//		if(optional.isPresent()) {
//			user=optional.get();
//			userRepo.delete(user);
//		return userMapper.mapToUserResponse(user);
//		}
//		else {
//			throw new UserNotFoundByIdException("user not found by the respective Id");
//		}
		 return userRepo.findById(userId).map(user->{
			userRepo.delete(user);
			return userMapper.mapToUserResponse(user);
		}).orElseThrow(()->new UserNotFoundByIdException("failed to delete the user"));
	}

	public List<UserResponse> findAllUsers() {
//		List<User> users=userRepo.findAll();
//		List<UserResponse> lists=new ArrayList<>();
//		for(User user:users) {
//			lists.add(userMapper.mapToUserResponse(user));
//		}
//		return lists;
//		List<UserResponse> responses=new ArrayList<>();
//		  userRepo.findAll().forEach(user->responses.add(userMapper.mapToUserResponse(user)));
//		  return responses;
		
		return userRepo.findAll().stream().filter(user->user.getUserName()!=null)
				.map(user->userMapper.mapToUserResponse(user)).toList();
	}
	
}
