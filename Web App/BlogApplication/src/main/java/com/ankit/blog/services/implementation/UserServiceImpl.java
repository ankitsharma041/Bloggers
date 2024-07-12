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
			User newUser = this.modelMapper.map(userRequestDTO, User.class);
			userResponseDTO = this.modelMapper.map(newUser, UserResponseDTO.class);
			userResponseDTO.setMessage(userRequestDTO.getEmail() + " already exists");
			userResponseDTO.setStatusCode(400);
			
		} else {
			User newUser = this.modelMapper.map(userRequestDTO, User.class);
			newUser = this.userRepo.save(newUser);
			userResponseDTO = this.modelMapper.map(newUser, UserResponseDTO.class);
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

		// Create a font for the header and data cells
		HSSFFont calibriFont1 = workbook.createFont();
		calibriFont1.setFontName("Calibri");

		// Create header style with bold Calibri font
		HSSFCellStyle headerStyle1 = workbook.createCellStyle();
		headerStyle1.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
		headerStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle1.setBorderTop(BorderStyle.THIN);
		headerStyle1.setBorderBottom(BorderStyle.THIN);
		headerStyle1.setBorderLeft(BorderStyle.THIN);
		headerStyle1.setBorderRight(BorderStyle.THIN);

		HSSFFont headerFont1 = workbook.createFont();
		headerFont1.setBold(true);
		headerFont1.setFontName("Calibri");
		headerStyle1.setFont(headerFont1);

		// Create data style for even rows with Calibri font
		HSSFCellStyle evenRowStyle1 = workbook.createCellStyle();
		evenRowStyle1.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_GREEN.getIndex());
		evenRowStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		evenRowStyle1.setBorderTop(BorderStyle.THIN);
		evenRowStyle1.setBorderBottom(BorderStyle.THIN);
		evenRowStyle1.setBorderLeft(BorderStyle.THIN);
		evenRowStyle1.setBorderRight(BorderStyle.THIN);
		evenRowStyle1.setFont(calibriFont1);

		// Create data style for odd rows with Calibri font
		HSSFCellStyle oddRowStyle1 = workbook.createCellStyle();
		oddRowStyle1.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_TURQUOISE.getIndex());
		oddRowStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		oddRowStyle1.setBorderTop(BorderStyle.THIN);
		oddRowStyle1.setBorderBottom(BorderStyle.THIN);
		oddRowStyle1.setBorderLeft(BorderStyle.THIN);
		oddRowStyle1.setBorderRight(BorderStyle.THIN);
		oddRowStyle1.setFont(calibriFont1);

		// Create header row
		HSSFRow headerRow1 = sheet.createRow(0);
		HSSFCell cell1;

		int dataRowIndex = 1;
		int serialNumber = 1;
		for (User user : users) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			HSSFCellStyle rowStyle = (dataRowIndex % 2 == 0) ? evenRowStyle1 : oddRowStyle1;

			String[] headers = { "S.No", "USER_ID", "USER_NAME", "EMAIL_ID", "PASSWORD", "ABOUT_USER", "ROLE" };
			for (int i = 0; i < headers.length; i++) {
				cell1 = headerRow1.createCell(i);
				cell1.setCellValue(headers[i]);
				cell1.setCellStyle(headerStyle);
			}

			// Create data rows
			int dataRowIndex1 = 1;
			int serialNumber1 = 1;	
			for (User user1 : users) {
				HSSFRow dataRow1 = sheet.createRow(dataRowIndex1);
				HSSFCellStyle rowStyle1 = (dataRowIndex1 % 2 == 0) ? evenRowStyle : oddRowStyle;

				cell1 = dataRow1.createCell(0);
				cell1.setCellValue(serialNumber1);
				cell1.setCellStyle(rowStyle1);

				cell1 = dataRow1.createCell(1);
				cell1.setCellValue(user1.getId());
				cell1.setCellStyle(rowStyle1);

				cell1 = dataRow1.createCell(2);
				cell1.setCellValue(user1.getName());
				cell1.setCellStyle(rowStyle1);

				cell1 = dataRow1.createCell(3);
				cell1.setCellValue(user1.getEmail());
				cell1.setCellStyle(rowStyle1);

				cell1 = dataRow1.createCell(4);
				cell1.setCellValue(user1.getPassword());
				cell1.setCellStyle(rowStyle1);

				cell1 = dataRow1.createCell(5);
				cell1.setCellValue(user1.getAbout());
				cell1.setCellStyle(rowStyle1);

				cell1 = dataRow1.createCell(6);
				cell1.setCellValue(user1.getRole());
				cell1.setCellStyle(rowStyle1);

				dataRowIndex1++;
				serialNumber1++;
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
}
