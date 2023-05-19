package com.gdu.moovod.batch;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.moovod.domain.AttachDTO;
import com.gdu.moovod.mapper.CsMapper;
import com.gdu.moovod.util.MyFileUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableScheduling
@Component
public class RemoveWrongFileScheduler {
	
	// field
	private MyFileUtil myFileUtil;
	private CsMapper csMapper;
	
	//                 초분시
	@Scheduled(cron = "0 0 2 1/1 * ?") // www.cronmaker.com에서 데일리로 선택 후 cron format에서 복사에서 썼음 맨끝 *는 지워서 쓰기.
	public void execute() { // 메소드명은 아무 의미 없다.
		
		// 어제 업로드 된 첨부 파일들의 정보(DB에서 가져오기 // 맵퍼작업하기)
		List<AttachDTO> attachList = csMapper.getAttachListInYesterday();
		
		// List<attachDTO> -> List<Path>로 번환하기
		List<Path> pathList = new ArrayList<Path>();
		if(attachList != null && attachList.isEmpty() == false) {
			for(AttachDTO attachDTO : attachList) {
				pathList.add(new File(attachDTO.getPath(), attachDTO.getFilesystemName()).toPath());  // Path 만들기 : File객체.toPath()
				if(attachDTO.getHasThumbnail() == 1) {
					pathList.add(new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName()).toPath());
				}
			}
		}
		
		// 어제 업로드 된 경로
		String yeterdayPath = myFileUtil.getYesterdayPath();
		
		// 어제 업로드 된 파일 목록 (HDD에서 확인) 중에서 DB에는 정보가 없는 파일 목록
		File dir = new File(yeterdayPath);
		File[] wrongFiles = dir.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) { // true를 반환하면 File[] wrongFiles에  포함된다. 매개변수 file dir, String name은 HDD에 저장된 파일을 읨한\

				// DB에 있는 목록 : pathList
				// HDD에 있는 파일 : File dir, String name - File.toPath 처리하서 Path로 변경
				return pathList.contains(new File(dir, name).toPath()) == false;
			}
		});
		
		// File[] wrongFiles 모두 삭제
				if(wrongFiles != null && wrongFiles.length != 0) {
					for(File wrongFile : wrongFiles) {
						wrongFile.delete();
					}
				}
				
			}
			
		}