package com.ankit.blog.services.implementation;

import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.blog.entities.User;
import com.ankit.blog.exception.ResourceNotFoundException;
import com.ankit.blog.payload.UserDTO;
import com.ankit.blog.repository.UserRepo;
import com.ankit.blog.requestDTO.UserRequestDTO;
import com.ankit.blog.responseDTO.UserResponseDTO;
import com.ankit.blog.services.UserService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
	    Optional<User> dbUser = this.userRepo.findByEmail(userRequestDTO.getEmail());
	    UserResponseDTO userResponseDTO = new UserResponseDTO();
	    
	    if (dbUser.isPresent()) {
	        userResponseDTO.setMessage(userRequestDTO.getEmail() + " already exists");
	        userResponseDTO.setStatusCode(400);
	    } else {
	        // Convert userRequestDTO to User entity if needed
	        User newUser = this.modelMapper.map(userRequestDTO, User.class);
	        
	        // Save the new user
	        newUser = this.userRepo.save(newUser);
	        
	        // Map the saved User entity back to UserResponseDTO
	        userResponseDTO = this.modelMapper.map(newUser, UserResponseDTO.class);
	        
	        // Set success message and status code
	        userResponseDTO.setMessage("User added successfully");
	        userResponseDTO.setStatusCode(200);
	    }

	    return userResponseDTO;
	}

	@Override
	public UserDTO deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setAbout(user.getAbout());

		userDTO.setMessage("User " + user.getEmail() + " has been deleted");
		userDTO.setStatusCode(200);
		return userDTO;
	}

	@Override
	public UserDTO updateUser(Integer userId, User updateUser) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		if (updateUser.getName() != null) {
			user.setName(updateUser.getName());
		}
//		if (updateUser.getEmail() != null) {
//			user.setEmail(updateUser.getEmail());
//		}
		if (updateUser.getPassword() != null) {

			user.setPassword(updateUser.getPassword());
		}
		if (updateUser.getAbout() != null) {
			user.setAbout(updateUser.getAbout());
		}

		User updatedUser = this.userRepo.save(user);
		UserDTO userDTO = this.modelMapper.map(updatedUser, UserDTO.class);
		userDTO.setMessage("User details has been updated successfully");
		userDTO.setStatusCode(200);
		return userDTO;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public UserDTO getUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		userDTO.setMessage("User with userId " + user.getId() + " is " + user.getName());
		userDTO.setStatusCode(200);
		return userDTO;
	}


	@Override
	public void generateExcel(HttpServletResponse response) {
	    List<User> users = userRepo.findAll();

	    HSSFWorkbook workbook = new HSSFWorkbook();
	    HSSFSheet sheet = workbook.createSheet("User Info");

	    HSSFFont calibriFont = workbook.createFont();
	    calibriFont.setFontName("Calibri");

	 
	    HSSFCellStyle headerStyle = workbook.createCellStyle();
	    headerStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
	    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    headerStyle.setBorderTop(BorderStyle.THIN);
	    headerStyle.setBorderBottom(BorderStyle.THIN);
	    headerStyle.setBorderLeft(BorderStyle.THIN);
	    headerStyle.setBorderRight(BorderStyle.THIN);
	    
	    HSSFFont headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerFont.setFontName("Calibri");
	    headerStyle.setFont(headerFont);

	   
	    HSSFCellStyle evenRowStyle = workbook.createCellStyle();
	    evenRowStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_GREEN.getIndex());
	    evenRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    evenRowStyle.setBorderTop(BorderStyle.THIN);
	    evenRowStyle.setBorderBottom(BorderStyle.THIN);
	    evenRowStyle.setBorderLeft(BorderStyle.THIN);
	    evenRowStyle.setBorderRight(BorderStyle.THIN);
	    evenRowStyle.setFont(calibriFont);

	  
	    HSSFCellStyle oddRowStyle = workbook.createCellStyle();
	    oddRowStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_TURQUOISE.getIndex());
	    oddRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    oddRowStyle.setBorderTop(BorderStyle.THIN);
	    oddRowStyle.setBorderBottom(BorderStyle.THIN);
	    oddRowStyle.setBorderLeft(BorderStyle.THIN);
	    oddRowStyle.setBorderRight(BorderStyle.THIN);
	    oddRowStyle.setFont(calibriFont);

	   
	    HSSFRow headerRow = sheet.createRow(0);
	    HSSFCell cell;

	    String[] headers = {"S.No", "USER_ID", "USER_NAME", "EMAIL_ID", "PASSWORD", "ABOUT_USER", "ROLE"};
	    for (int i = 0; i < headers.length; i++) {
	        cell = headerRow.createCell(i);
	        cell.setCellValue(headers[i]);
	        cell.setCellStyle(headerStyle);
	    }

	    
	    int dataRowIndex = 1;
	    int serialNumber = 1;
	    for (User user : users) {
	        HSSFRow dataRow = sheet.createRow(dataRowIndex);
	        HSSFCellStyle rowStyle = (dataRowIndex % 2 == 0) ? evenRowStyle : oddRowStyle;

	        cell = dataRow.createCell(0);
	        cell.setCellValue(serialNumber);
	        cell.setCellStyle(rowStyle);

	        cell = dataRow.createCell(1);
	        cell.setCellValue(user.getId());
	        cell.setCellStyle(rowStyle);

	        cell = dataRow.createCell(2);
	        cell.setCellValue(user.getName());
	        cell.setCellStyle(rowStyle);

	        cell = dataRow.createCell(3);
	        cell.setCellValue(user.getEmail());
	        cell.setCellStyle(rowStyle);

	        cell = dataRow.createCell(4);
	        cell.setCellValue(user.getPassword());
	        cell.setCellStyle(rowStyle);

	        cell = dataRow.createCell(5);
	        cell.setCellValue(user.getAbout());
	        cell.setCellStyle(rowStyle);

	        cell = dataRow.createCell(6);
	        cell.setCellValue(user.getRole());
	        cell.setCellStyle(rowStyle);

	        dataRowIndex++;
	        serialNumber++;
	    }

	    try {
	        ServletOutputStream ops = response.getOutputStream();
	        workbook.write(ops);
	        workbook.close();
	        ops.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
