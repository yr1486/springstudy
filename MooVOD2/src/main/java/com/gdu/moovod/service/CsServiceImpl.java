package com.gdu.moovod.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.moovod.domain.AttachDTO;
import com.gdu.moovod.domain.CsDTO;
import com.gdu.moovod.mapper.CsMapper;
import com.gdu.moovod.util.MyFileUtil;
import com.gdu.moovod.util.PageUtil;

import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@Service
@AllArgsConstructor
public class CsServiceImpl implements CsService {

	// field
	private CsMapper csMapper;
	private MyFileUtil myFileUtil;
	private PageUtil pageUtil;
	
  @Override
  public void loadcsList(HttpServletRequest request, Model model) {

    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    
    int totalRecord = csMapper.getCsCount();
    
    int recordPerPage = 20;
    
    pageUtil.setPageUtil(page, totalRecord, recordPerPage);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("begin", pageUtil.getBegin());
    map.put("end", pageUtil.getEnd());
    
    List<CsDTO> csList = csMapper.getCsList(map);
    
    model.addAttribute("csList", csList);
    model.addAttribute("beginNo", totalRecord - (page - 1) * recordPerPage);
    //model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/bbs/list.do"));  // 아래 코드로 대체 가능합니다.
    model.addAttribute("pagination", pageUtil.getPagination(request.getRequestURI()));
    
  }


	
	@Transactional(readOnly = true)
	@Override
	public int addCs(MultipartHttpServletRequest multipartRequest) {
		/* Upload 테이블에 UploadDTO 넣기 */

		// 제목과 내용이 파라미터로 넘어옴
		String csTitle = multipartRequest.getParameter("csTitle");
    String csWriter = multipartRequest.getParameter("csWriter");
		String csContent = multipartRequest.getParameter("csContent");

		// DB로 보낼 UploadDTO 만들기
		CsDTO csDTO = new CsDTO();
		csDTO.setCsTitle(csTitle);
		csDTO.setCsWriter(csWriter);
		csDTO.setCsContent(csContent);

		// DB로 업로드DTO 보내기 // 보내려면, 서비스 넘버를 알아야 보낼 수 있어. 아주 중요!!!!!!!
		int addResult = csMapper.addCs(csDTO); // <selectkey>에 의해서, 업로드디티오객체의 업로드넘버 필드에
																// UPLOAD_SEQ.NEXTVAL

		/* Attach 테이블에 AttachDTO 넣기 */

		// 첨부된 파일 목록
		List<MultipartFile> files = multipartRequest.getFiles("files"); // <input type="file name="files"> // 네임보고
																		// 넘어오는거임.

		// 제목하고 이름만 적고 작성하기 누른경우. 첨부파일 없이.
		// 첨부된 파일이 있는지 체크.
		for (MultipartFile multipartFile : files) {
			
			if (multipartFile != null && multipartFile.isEmpty() == false) {

			// 첨부된 파일 목록 순회

				// 예외 처리
				try {

					// 첨부파일 저장 경로
					String path = myFileUtil.getPath();

					// 첨부파일의 저장 경로가 없으면 만들기
					File dir = new File(path);
					if (dir.exists() == false) {
						dir.mkdirs();

					}

					// 첨부파일의 원래 이름
					String originName = multipartFile.getOriginalFilename();
					originName = originName.substring(originName.lastIndexOf("\\") + 1);
					// 앞에 경로가 붙어서 넘어오는 파일 이름이 있을 수 있어서 => ...\..\..\여기가진짜 파일 이름
					// 인터넷익스플로러는 전체 경로가 넘어오기 때문에 마지막 역슬래시 뒤에 있는 파일명만 사용하기위해서. 코드 작성. 파일이름에는 역슬래시
					// 못들어가서 가능.

					// 첨부파일의 저장 이름
					String filesystemName = myFileUtil.getFilesystemName(originName);

					// 첨부 파일의 File 객체 (하드디스크에 저장할 첨부파일)
					File file = new File(dir, filesystemName);

					// 첨부 파일을 하드디스크(HDD)에 저장
					multipartFile.transferTo(file); // 여기서 실제 서버에 저장된다.

					// 썸네일(첨부파일이 이미지인 경우에만 썸네일이 가능하다)

					// 첨부 파일의 Content-Type 확인
					String contentType = Files.probeContentType(file.toPath()); // 해당파일의 경로가 어떻게 된다라는걸. 객체 형태로 전달하는 거임.
																				// 간혹 클래스들이. 파일객체말고. 패스객체를 요구?할때가 있음 그때
																				// 사용.
																				// path는 파일객체를 이용해서 쉽게 만들어낼 수 있는 또다른 객체.
																				// 이미지 파일의 Content-Type: image/jpeg,
																				// image/png, image/gif, ... ==> 컨텐트타입이
																				// 이미지로 시작하면. 이미지인거임. 뒤에 확장자 다른거 신경쓰지말고
																				// 생각하기
					// DB에 저장할 썸네일 유무 정보 처리
					// 첨부파일의 Content-Type이 이미지로 확인되면, 썸네일을 만든다.
					boolean hasThumbnail = contentType != null && contentType.startsWith("image");

					// 첨부 파일의 Content-Type이 이미지로 확인되면 썸네일을 만듬
					if (hasThumbnail) { // 머머로 시작한다. startsWith. 머머로 끝난다는 endsWith

						// HDD 썸네일 저장하기(Thumbnailator 디펜던시 사용)
						File thumbnail = new File(dir, "s_" + filesystemName);
						Thumbnails.of(file)
								.size(50, 50)
								.toFile(thumbnail);

					}

					/* DB에 첨부 파일 정보 저장하기 */

					// DB로 보낼 AttachDTO 만들기
					AttachDTO attachDTO = new AttachDTO();
					attachDTO.setFilesystemName(filesystemName);
					attachDTO.setHasThumbnail(hasThumbnail ? 1 : 0);
					attachDTO.setOriginName(originName);
					attachDTO.setPath(path);
					attachDTO.setCsNo(csDTO.getCsNo());

					// DB로 AttachDTO 보내기
					csMapper.addAttach(attachDTO);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return addResult;
	}

	@Override
	public void getCsByNo(int csNo, Model model) {
		model.addAttribute("cs", csMapper.getCsByNo(csNo));
		model.addAttribute("attachList", csMapper.getAttachList(csNo));
		// 하고 컨트롤러로 가기
		
	}

	@Override
	public ResponseEntity<byte[]> display(int attachNo) {
		AttachDTO attachDTO = csMapper.getAttachByNo(attachNo);
		
		ResponseEntity<byte[]> image = null;
		
		try {
			File thumbnail = new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName());
			image = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(thumbnail), HttpStatus.OK);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	@Override
	public ResponseEntity<Resource> download(int attachNo, String userAgent) {
		// 다운로드 할 첨부파일의 정보(경로, 원래이름, 저장된이름)
		AttachDTO attachDTO = csMapper.getAttachByNo(attachNo);
		
		// 다운로드할 첨부파일의 File 객체 > Resource 객체
		File file = new File(attachDTO.getPath(), attachDTO.getFilesystemName());
																	// 저장된이름
		Resource resource = new FileSystemResource(file);
		
		// 다운로드 할 첨부파일의 존재 여부 확인(다운로드 실패를 반환)
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		// 다운로드 횟수 증가하기
		csMapper.increaseDownloadCount(attachNo);
		
		// 다운로드 되는 파일명(첨부파일의 원래 이름, UserAgent(브라우저)에 따른 인코딩 세팅)이 필요함
		String originName = attachDTO.getOriginName();
		
		try {
			// IE(UserAgent에 Trident가 포함되어 있다)
			if(userAgent.contains("Trident")) {
				originName = URLEncoder.encode(originName, "UTF-8").replace("+", " "); // 공백이 있으면 +로 채워지기 때문에 리플레이스로 +를 제거한다.
			}
			// Edge (UserAgent에 Edg가 포함되어 있다)
			else if(userAgent.contains("Edg")) {
				originName = URLEncoder.encode(originName, "UTF-8");
			}
			// 나머지다른 other
			else {
				originName = new String(originName.getBytes("UTF-8"), "ISO-8859-1"); 
																		// 확장 아스키 코드
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		// 다운로드 응답 헤더 만들기 (Jsp/Servlet 코드)
				/*
				MultiValueMap<String, String> responseHeader = new HttpHeaders();
				responseHeader.add("Content-Type", "application/octet-stream");
				responseHeader.add("Content-Disposition", "attachment; filename=" + originName);
				responseHeader.add("Content-Length", file.length() + "");-> String으로 바꿔줘야해서 빈문자열 결합
				*/

				// 다운로드 응답 헤더 만들기 (Spring 코드)
				HttpHeaders responseHeader = new HttpHeaders();
				responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				responseHeader.setContentDisposition(ContentDisposition
																							.attachment()
																							.filename(originName)
																							.build());
				responseHeader.setContentLength(file.length());
				
				// 응답
				return new ResponseEntity<Resource>(resource, responseHeader, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Resource> downloadAll(int csNo) {
		// 모든 첨부파일 zip 파일로 압축해서 다운로드하는 서비스
		// com.gdu.app11.batch.RemoveTempfileScheduler에 의해서 주기적으로 zip 파일들은 삭제된다.
		// 압축 시 : 어디다 저장할거니, 이름은 뭘로 할거니.를 생각해야함.
		
		// 서버입장에서. 압축파일은 임시파일임. 다운로드할거면 일단 먼저 서버가 압축해서 가지고 있어야하고, 그다음 다운로드코드처럼 처리하면 됨
		// 그다음 임시파일을 어떻게 주기적으로 지워 줄건지도 고려해야함
		
		// zip파일이 저장될 경로
		String tempPath = myFileUtil.getTempPath();
		File dir = new File(tempPath);
		if(dir.exists() == false) { // 없으면 만들어라.
			dir.mkdirs();
		}
		
		// zip파일의 이름
		String tempfileName = myFileUtil.getTempfileName();
		
		// zip파일의 File 객체 선언
		File zfile = new File(tempPath, tempfileName);
		
		
		// zip파일을 생성하기 위한 Java IO Stream 선언
		BufferedInputStream bin = null; // 각 첨부 파일을 읽어 들이는 스트림
		ZipOutputStream zout = null; // zip파일을 만드는 스트림
		
		
		
		// 다운로드할 첨부파일'들'의 정보 (경로, 원래이름, 저장된이름) 가져오기
		List<AttachDTO> attachList = csMapper.getAttachList(csNo);
		
		try {
			// ZipOUtputStream zout 객체 생성
			// out = new FileOutputStream(zfile); 를 변수를 쓰지 않고 바로 아래()에 집어넣은거임!!
			zout = new ZipOutputStream(new FileOutputStream(zfile));
			
			// 첨부파일들을 하나씩 순회하면서 읽어들인 뒤 zip파일에 추가하기
			for(AttachDTO attachDTO : attachList) {
				
				// zip파일에 추가할 첨부파일 이름 등록(첨부파일의 '원래이름')
				ZipEntry zipEntry = new ZipEntry(attachDTO.getOriginName());
				zout.putNextEntry(zipEntry);
	
				
				// zip파일에 첨부파일 추가하기
				// 이름은 등록되었는데 아직 내용이 안들어갔으니.
				bin = new BufferedInputStream(new FileInputStream(new File(attachDTO.getPath(), attachDTO.getFilesystemName())));
			
				// bin -> zout으로 파일 복사하기
				byte[] b = new byte[1024];  // 첨부파일을 1KB 단위로 읽겠다.
				int readByte = 0;			// 실제로 읽어들인 바이트 수
				while((readByte = bin.read(b)) != -1) {
					zout.write(b, 0, readByte);
								//인덱스0부터 1024단위로 읽어들이는데, 
				}
				
				// bin -> zout으로 파일 복사하기 (Spring 코드)
				bin.close();
				zout.closeEntry();
				
				// 각 첨부 파일들의 다운로드 횟수 증가
				csMapper.increaseDownloadCount(attachDTO.getAttachNo());
			}
			zout.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// 다운로드할 zip파일의 File 객체 > Resource 객체
		Resource resource = new FileSystemResource(zfile);
		
		
		// 다운로드 응답 헤더 만들기 (Spring 코드)
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		responseHeader.setContentDisposition(ContentDisposition
											.attachment()
											.filename(tempfileName)
											.build());
		responseHeader.setContentLength(zfile.length());
		
		
		// 응답
		return new ResponseEntity<Resource>(resource, responseHeader, HttpStatus.OK);
		
	}

	@Override
	public int removeCs(int csNo) {
		
		// 삭제할 첨부파일들의 정보
		List<AttachDTO> attachList = csMapper.getAttachList(csNo);

		// 첨부 파일이 있으면 삭제
		if(attachList != null && attachList.isEmpty() == false) {
			// 삭제할 첨부 파일들을 순회하면서 하나씩 삭제
			for(AttachDTO attachDTO : attachList) {
				
				// 삭제할 첨부 파일의 File 객체
				File file = new File(attachDTO.getPath(), attachDTO.getFilesystemName());
				
				// 첨부파일 삭제
				if(file.exists()) {
					file.delete();
				}
				
				// 첨부파일이 썸네일을 가지고 있다면 "s_"로 시작하는 썸네일이 함께 존재하므로, 함께 제거해야한다.
				if(attachDTO.getHasThumbnail() == 1) {
					// 삭제할 썸네일의 File 객체
					File thumbnail = new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName());
					
					// 썸네일 삭제
					if(thumbnail.exists()) {
						thumbnail.delete();
					}
				}
			}
		}
		
		// DB에서 uploadNo값을 가지는 데이터를 삭제
		// 외래키 제약조건에 의해서(ON DELETE CASCADE) UPLOAD 테이블의 데이터가 삭제되면
		// ATTACH 테이블의 데이터도 함께 삭제 된다.
		int removeResult = csMapper.removeCs(csNo);
		
		return removeResult;
	}
	
  @Transactional(readOnly = true)  // INSERT문을 2개 이상 수행하기 때문에 트랜잭션 처리가 필요하다.
  @Override
  public int modifyCs(MultipartHttpServletRequest multipartRequest) {
    
    /* Cs 테이블의 정보 수정하기 */
    
    // 제목, 내용, 업로드번호 파라미터
    String csTitle = multipartRequest.getParameter("csTitle");
    String csContent = multipartRequest.getParameter("csContent");
    int csNo = Integer.parseInt(multipartRequest.getParameter("csNo"));
    
    // DB로 보낼 UploadDTO 만들기
    CsDTO csDTO = new CsDTO();
    csDTO.setCsTitle(csTitle);
    csDTO.setCsContent(csContent);
    csDTO.setCsNo(csNo);
    
    // DB로 UploadDTO 보내기
    int modifyResult = csMapper.modifyCs(csDTO);
    
    /* Attach 테이블에 AttachDTO 넣기 */
    
    // 첨부된 파일 목록
    List<MultipartFile> files = multipartRequest.getFiles("files");  // <input type="file" name="files">

    // 첨부가 없는 경우에도 files 리스트는 비어 있지 않고,
    // [MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]] 형식으로 MultipartFile을 하나 가진 것으로 처리된다.
    
    // 첨부된 파일 목록 순회
    for(MultipartFile multipartFile : files) {
      
      // 첨부된 파일이 있는지 체크
      if(multipartFile != null && multipartFile.isEmpty() == false) {
        
        // 예외 처리
        try {
          
          /* HDD에 첨부 파일 저장하기 */
          
          // 첨부 파일의 저장 경로
          String path = myFileUtil.getPath();
          
          // 첨부 파일의 저장 경로가 없으면 만들기
          File dir = new File(path);
          if(dir.exists() == false) {
            dir.mkdirs();
          }
          
          // 첨부 파일의 원래 이름
          String originName = multipartFile.getOriginalFilename();
          originName = originName.substring(originName.lastIndexOf("\\") + 1);  // IE는 전체 경로가 오기 때문에 마지막 역슬래시 뒤에 있는 파일명만 사용한다.
          
          // 첨부 파일의 저장 이름
          String filesystemName = myFileUtil.getFilesystemName(originName);
          
          // 첨부 파일의 File 객체 (HDD에 저장할 첨부 파일)
          File file = new File(dir, filesystemName);
          
          // 첨부 파일을 HDD에 저장
          multipartFile.transferTo(file);  // 실제로 서버에 저장된다.
          
          /* 썸네일(첨부 파일이 이미지인 경우에만 썸네일이 가능) */
          
          // 첨부 파일의 Content-Type 확인
          String contentType = Files.probeContentType(file.toPath());  // 이미지 파일의 Content-Type : image/jpeg, image/png, image/gif, ...
          
          // DB에 저장할 썸네일 유무 정보 처리
          boolean hasThumbnail = contentType != null && contentType.startsWith("image");
          
          // 첨부 파일의 Content-Type이 이미지로 확인되면 썸네일을 만듬
          if(hasThumbnail) {
            
            // HDD에 썸네일 저장하기 (thumbnailator 디펜던시 사용)
            File thumbnail = new File(dir, "s_" + filesystemName);
            Thumbnails.of(file)
              .size(50, 50)
              .toFile(thumbnail);
            
          }
          
          /* DB에 첨부 파일 정보 저장하기 */
          
          // DB로 보낼 AttachDTO 만들기
          AttachDTO attachDTO = new AttachDTO();
          attachDTO.setFilesystemName(filesystemName);
          attachDTO.setHasThumbnail(hasThumbnail ? 1 : 0);
          attachDTO.setOriginName(originName);
          attachDTO.setPath(path);
          attachDTO.setCsNo(csDTO.getCsNo());
          
          // DB로 AttachDTO 보내기
          csMapper.addAttach(attachDTO);
          
        } catch(Exception e) {
          e.printStackTrace();
        }
        
      }
      
    }
    
    return modifyResult;
    
  }
  
  
  @Override
  public int removeAttach(int attachNo) {
    
    // 삭제할 첨부 파일의 정보 가져오기
    AttachDTO attachDTO = csMapper.getAttachByNo(attachNo);
    
    // 첨부 파일이 있으면 삭제
    if(attachDTO != null) {
      
      // 삭제할 첨부 파일의 File 객체
      File file = new File(attachDTO.getPath(), attachDTO.getFilesystemName());
      
      // 첨부 파일 삭제
      if(file.exists()) {
        file.delete();
      }
      
      // 첨부 파일이 썸네일을 가지고 있다면 "s_"로 시작하는 썸네일이 함께 존재하므로 함께 제거해야 한다.
      if(attachDTO.getHasThumbnail() == 1) {
        
        // 삭제할 썸네일의 File 객체
        File thumbnail = new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName());
        
        // 썸네일 삭제
        if(thumbnail.exists()) {
          thumbnail.delete();
        }
          
      }
      
    }

    // DB에서 attachNo값을 가지는 ATTACH 테이블의 데이터를 삭제
    int removeResult = csMapper.removeAttach(attachNo);
    
    return removeResult;
    
  }
  
  
}
	

