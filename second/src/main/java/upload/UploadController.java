package upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	// 1. uploadform 보여주는 메서드
	@GetMapping("/fileupload")
	public String uploadform() {
		return "upload/uploadform";
	}
	
	// 2. 파일 선택 받아서 업로드 처리해주는 메서드
	@PostMapping("/fileupload")
	public String uploadprocess(@ModelAttribute("dto")UploadDTO dto) throws IOException{
		// c:/upload 폴더에 업로드했던 이름 그대로 저장
		// 저장한 결과 객체를 뷰에 전달
		// uploadprocess.jsp
		String savePath = "c:/upload/";
		MultipartFile mf1 = dto.getFile1();
		// 파일명 추출
		String filename1 = mf1.getOriginalFilename();
	
		// ** UUID 랜덤하게 긴 길이의 문자열 - 중복방지 **
		// a(uuid랜덤문자열).txt 
		// 파일이름(beforeext1) . 확장자(ext1) - 분리
		String beforeext1 = filename1.substring(0, filename1.lastIndexOf('.'));
		String ext1 = filename1.substring(filename1.lastIndexOf('.'));
		
		// UUID 생성 - UUID.randomUUID()
		String newfilename1 = beforeext1 + "(" + UUID.randomUUID().toString() + ")" + ext1;
		
 		// 파일내용 추출해서 c:/upload/filename1 저장
		// java file 객체
		File serverfile1 = new File(savePath + newfilename1);
		// multipart form으로 받은 내용을 File객체로 옮기는 메서드 : transferTo()
		mf1.transferTo(serverfile1);
		
		
		
		MultipartFile mf2 = dto.getFile2();
		// 파일명 추출
		String filename2 = mf2.getOriginalFilename();
		String beforeext2 = filename2.substring(0, filename2.lastIndexOf('.'));
		String ext2 = filename2.substring(filename2.lastIndexOf('.'));
		
		// UUID 생성 - UUID.randomUUID()
		String newfilename2 = beforeext2 + "(" + UUID.randomUUID().toString() + ")" + ext2;
		
		File serverfile2 = new File(savePath + newfilename2);
		// multipart form으로 받은 내용을 File객체로 옮기는 메서드 : transferTo()
		mf2.transferTo(serverfile2);
		
		return "upload/uploadprocess";
	}
	
}
