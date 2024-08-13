package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form.............");
	}

// 한개의 파일 업로드 처리
//	@PostMapping("/uploadFormAction")
//	   public void uploadFormPost(MultipartFile uploadFile, Model model) {
//	      log.info("file Name :" + uploadFile.getOriginalFilename());
//	      log.info("fileSize : " + uploadFile.getSize());
//	      
//	      String uploadFolder = "C:\\upload\\temp";
//	      
//	      File saveFile = new File(uploadFolder, uploadFile.getOriginalFilename());
//	      
//	      try {
//	         uploadFile.transferTo(saveFile);
//	      } catch (Exception e) {
//	         e.printStackTrace();
//	      }
//	   }
	
	// 여러개의 파일 업로드 처리
	@PostMapping("/uploadAjaxAction")
	//ResponseEntity<List<AttachFileDTO>>를 반환 하는 형태로 수정하고, JSON 데이터를 반환하도록 변경
	// 내부에서는 각 파일에 맞게 AttachFileDTO를 생성해서 전달하는 구조로 변경
	public ResponseEntity<List<AttachFileDTO>> uploadFormPost(MultipartFile[] uploadFile, Model model) {
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();  // AttachFileDTO의 리스트를 반환하는 구조로 변경
		String uploadFolder = "C:\\upload\\temp";
		
		// (년/월/일 폴더 생성)
		String uploadFolderPath = getFolder();
		
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		if (uploadPath.exists() == false) {  // 폴더가 존재하지 않으면 새로운 폴더를 만들어 줌.
			uploadPath.mkdirs();
		}
		

		for (MultipartFile multipartFile : uploadFile) {
			
			// AttachFileDTO 얻어옴.
			AttachFileDTO attachDTO = new AttachFileDTO();

			log.info("-------------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());

			// 중복 방지를 위한 UUID 적용
			String uploadFileName = multipartFile.getOriginalFilename();
			attachDTO.setFileName(uploadFileName);  // AttachFileDTO용 원본 파일의 이름
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			//File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());  // 그냥 업로드
			//File saveFile = new File(uploadPath, multipartFile.getOriginalFilename());  // 년/월/일 폴더 생성
			File saveFile = new File(uploadPath, uploadFileName); // UUID 적용을 위해 uploadFileName 사용
			
			try {
				multipartFile.transferTo(saveFile);  // 이미지 파일을 업로드하면 원본 파일은 그대로 저장
				
				attachDTO.setUuid(uuid.toString());  // AttachFileDTO용 UUID값
				attachDTO.setUploadPath(uploadFolderPath); // AttachFileDTO용 업로드 경로
				
				// 이미지 파일 체크
				if (checkImageType(saveFile)) {  // 이미지 파일인지 체크
					attachDTO.setImage(true); // AttachFileDTO용 이미지 여부					
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));  // 파일 이름이 's_'로 시작하는 섬네일 파일이 생성됨.

					// Thumbnailator는 InputStream과 java.io.File 객체를 이용해서 파일을 생성할 수 있고, 뒤에 사이즈에 대한 부분을 파라미터로 witdh와 height를 지정할 수 있음
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);

					thumbnail.close();
				}
				
				list.add(attachDTO);  // list에 attachDTO 객체 추가
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		return new ResponseEntity<List<AttachFileDTO>>(list, HttpStatus.OK);  // AttachFileDTO 객체들의 리스트를 담고 있는 list 변수를 HTTP 응답의 본문으로 설정 및 HTTP 상태 코드를 200 OK로 설정
	}
	
	// 기본 파일 업로드 처리 용도 url
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}
	
	// (년/월/일 폴더 생성을 위한 정의)
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}
	
	// 섬네일 이미지 생성
	private boolean checkImageType(File file) {

		try {

			String contentType = Files.probeContentType(file.toPath()); // 파일의 콘텐츠 타입을 조회

			return contentType.startsWith("image"); // 콘텐츠 타입이 "image"로 시작하는지 확인

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
	
}
