package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import models.Address;
import models.User;
import utility.KeyGeneration;
import utility.Validation;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao<User> dao;

	@Override
	public void addUser(User user) {
		for (Address address : user.getAddresses()) {
			address.setUser(user);
		}
		dao.addUser(user);
	}

	@Override
	public User getUser(String email, String password) {
		return dao.getUser(email, password);
	}

	@Override
	public List<User> getAllUser() {
		return dao.getAllUsers();
	}

	@Override
	public User getUserData(int id) {
		User user = dao.getUserData(id);
		user.setPassword(KeyGeneration.decrypt(user.getPassword()));
		return user;
	}

	@Override
	public boolean deleteUser(int id) {
		return (boolean) dao.deleteUser(id);
	}

	@Override
	public String addAllUser(List<User> users) {
		String error = "";
		int count = 1;
		for (User user : users) {
			String inputError = "";
			inputError += Validation.checkName(user.getName());
			inputError += Validation.checkEmail(user.getEmail());
			inputError += Validation.checkPassword(user.getPassword());
			inputError += Validation.checkPhone(user.getPhone());
			inputError += Validation.checkGender(user.getGender());
			inputError += Validation.checkLang(user.getLang());
			inputError += Validation.checkGame(user.getGame());
			inputError += Validation.checkSecQues(user.getSecQues());
			if (inputError.isEmpty()) {
				user.setPassword(KeyGeneration.encrypt(user.getPassword()));
				dao.addUser(user);
			} else {
				error += "At row " + count + " " + inputError;
			}
			count++;
		}
		return error;
	}

	@Override
	public void updateUser(User user) {
		for (Address address : user.getAddresses()) {
			address.setUser(user);
		}
		List<Address> oldAddresses = getUserData(user.getId()).getAddresses();
		for (Address oldAddress : oldAddresses) {
			Address newAddress =  user.getAddresses().stream()
					.filter(address -> address.getAddress_id() == oldAddress.getAddress_id()).findAny().orElse(null);
			if (newAddress == null) {
				dao.deleteAddress(oldAddress);
			}
		}
		dao.updateUser(user);
	}

	@Override
	public boolean checkEmail(String email) {
		return dao.checkEmail(email);
	}

	@Override
	public boolean updatePassword(User user) {
		return dao.updatePassword(user);
	}
}
