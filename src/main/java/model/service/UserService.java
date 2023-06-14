package model.service;

import model.dao.UserDAO;
import model.dto.Role;
import model.dto.UserDTO;

public class UserService {

	public int add(UserDTO dto) {

		UserDAO dao = new UserDAO();
		UserDTO data = dao.selectUser(dto);

		if (dto.getPassword() == "" || dto.getUserId() == "") {
			return 2;
		}

		if (data == null) {
			int count = dao.insert(dto);
			if (count == 2) {
				dao.commit();
				dao.close();
				return 1;
			}
			dao.rollback();
			dao.close();
			return 0;
		}
		dao.close();
		return -1;
	}

	public UserDTO login(UserDTO dto) {
		UserDAO dao = new UserDAO();
		UserDTO data = dao.selectUser(dto);
		if (data == null) {
			dao.close();
			return null;
		} else {
			if (data.getPassword().equals(dto.getPassword())) {
				dao.close();
				return data;
			}
		}
		dao.close();
		return null;
	}

	public boolean update(UserDTO userData, UserDTO dto, String password) {
		UserDAO dao = new UserDAO();

		UserDTO data = dao.selectUser(userData);

		if (data == null) {
			dao.close();
			return false;
		} else {
			if (data.getPassword().equals(password)) {
				dto.setUserId(data.getUserId());
//				System.out.print(password); 현재 비밀번호
				if (dto.getPassword().isEmpty()) {
					dto.setPassword(data.getPassword());
				}
				int count = dao.update(dto);
				if (count == 1) {
					dao.commit();
					dao.close();
					return true;
				}
			}
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public boolean notNull(UserDTO dto) {
		if (dto.getUserId() == "" || dto.getPassword() == "") {
			return false;
		} else {
			return true;
		}
	}

	public Role getRole(UserDTO dto) {
		UserDAO dao = new UserDAO();
		Role role = dao.selectRole(dto);
		dao.close();
		return role;
	}

	public boolean uploadImage(UserDTO user) {
		UserDAO dao = new UserDAO();
		int count = dao.updateImage(user);
		if (count == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;

	}

	public UserDTO getData(UserDTO dto) {
		UserDAO dao = new UserDAO();
		UserDTO data = dao.selectUser(dto);
		dao.close();
		return data;

	}
}