package controllers;

import dataTypes.DtUser;
import entities.User;
import handlers.UserService;
import interfaces.UserInterface;
import org.w3c.dom.UserDataHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController implements UserInterface {
	private final UserService userHandler = UserService.getInstance();
	public UserController() {
		super();
	}
	
	@Override
	public DtUser chooseUser(String email) {
		DtUser dtU = null;
		return dtU;
	}

	@Override
	public String [] listUsersByNickname() {
		//This should go to the UsersHandlers and ask only for all the Dts, then we should
		return userHandler.getAllUsersNickname();
	}

	@Override
	public void updateUserInfo(DtUser updatedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newMember(DtUser DtNewUser) {
		// TODO Auto-generated method stub
		
	}
}
