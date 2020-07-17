package com.shris.service;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shris.domain.IdContainer;
import com.shris.domain.User;
import com.shris.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(user -> users.add(user));
		return users;
	}

	public List<User> getUsersWithLimit(int limit) {
		List<User> users = userRepository.findUsersByLimit(limit);
		return users;
	}

	public List<User> getUsersByIdsUsingReqBody(List<IdContainer> idContainer) {
		List<User> users = new ArrayList<>();
		List<Integer> ids = new ArrayList<>();
		for (IdContainer id : idContainer) {
			ids.add(id.getId());
		}
		userRepository.findAllById(ids).forEach(user -> users.add(user));
		return users;
	}

	public List<User> getUsersByIdsUsingReqParam(List<Integer> ids) {
		List<User> users = new ArrayList<>();
		List<Integer> idList = new ArrayList<>();
		for (Integer id : idList) {
			ids.add(id);
		}
		userRepository.findAllById(ids).forEach(user -> users.add(user));
		return users;
	}

	public User findById(int uid) {
		User user = userRepository.findByUid(uid).get();
		return user;
	}

	public List<User> findByName(String name) {
		List<User> user = userRepository.findByName(name);
		return user;
	}

	public List<User> findByNameWithLimit(String name, int limit) {
		List<User> users = userRepository.findByNameWithLimit(name, limit);
		return users;
	}

	public void updateName(int uid, String name) {
		User user = userRepository.findByUid(uid).get();
		user.setName(name);
		userRepository.save(user);
	}

	public void updateNameFromRepo(int uid, String name) {
		userRepository.updateNameByUid(uid, name);
	}

	public List<User> updateMultipleFlags(List<Integer> uids) {
		List<User> users = new ArrayList<>();
		userRepository.updateMultipleFlagsByUids(uids);
		userRepository.findAllById(uids).forEach(user -> users.add(user));
		return users;
	}

	public void delete(int uid) {
		User user = userRepository.getOne(uid);
		userRepository.delete(user);
	}

	public void deleteById(int uid) {
		userRepository.deleteById(uid);
	}

	public void deleteByUId(int uid) {
		userRepository.deleteByUId(uid);
	}

	@SuppressWarnings("resource")
	public void upload() {
		try {
			Class.forName("org.h2.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:h2:mem:vivek", "sa", "");
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("Select * from user where flag ='Y'");

			HSSFWorkbook wb = new HSSFWorkbook();

			HSSFSheet sheet = wb.createSheet("sheet1");
			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell((short) 0).setCellValue("uid");
			rowhead.createCell((short) 1).setCellValue("age");
			rowhead.createCell((short) 2).setCellValue("email");
			rowhead.createCell((short) 3).setCellValue("flag");
			rowhead.createCell((short) 4).setCellValue("name");

			int index = 1;
			while (rs.next()) {
				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(rs.getInt(1));
				row.createCell((short) 1).setCellValue(rs.getInt(2));
				row.createCell((short) 2).setCellValue(rs.getString(3));
				row.createCell((short) 3).setCellValue(rs.getString(4));
				row.createCell((short) 4).setCellValue(rs.getString(5));
				index++;
			}
			FileOutputStream fileOut = new FileOutputStream("/home/vivek/excelFile.xls");

			ResultSet rs2 = st.executeQuery("Select * from user where flag ='N'");
			HSSFSheet sheet2 = wb.createSheet("sheet2");
			HSSFRow rowhead2 = sheet2.createRow((short) 0);
			rowhead2.createCell((short) 0).setCellValue("uid");
			rowhead2.createCell((short) 1).setCellValue("age");
			rowhead2.createCell((short) 2).setCellValue("email");
			rowhead2.createCell((short) 3).setCellValue("flag");
			rowhead2.createCell((short) 4).setCellValue("name");

			int index2 = 1;
			while (rs2.next()) {
				HSSFRow row2 = sheet2.createRow((short) index2);
				row2.createCell((short) 0).setCellValue(rs2.getInt(1));
				row2.createCell((short) 1).setCellValue(rs2.getInt(2));
				row2.createCell((short) 2).setCellValue(rs2.getString(3));
				row2.createCell((short) 3).setCellValue(rs2.getString(4));
				row2.createCell((short) 4).setCellValue(rs2.getString(5));
				index2++;
			}
			FileOutputStream fileOut2 = new FileOutputStream("/home/vivek/excelFile.xls");

			wb.write(fileOut);
			wb.write(fileOut2);
			fileOut.close();
			fileOut2.close();
			System.out.println("Data is saved in excel file.");
			rs.close();
			rs2.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("exception " + e.getMessage());
		}
	}

}
