package com.gdu.moovod.batch;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.moovod.util.MyFileUtil;

@EnableAspectJAutoProxy
@Component // 스프링컨테이너에 빈으로 둬라. 라는뜻. 쓸떄 . 뉴 안하고 autowired로 가져오는거.


// batch 작업 : 데이터를 실시간으로 처리하는게 아니라, 일괄적으로 모아서 한번에 처리하는 작업을 의미.
/*
 	배치의 특징
		대량의 데이터를 처리한다.
		특정 시간에 프로그램을 실행한다.
		일괄적으로 처리한다
 */

public class RemoveTempfileScheduler {

	// 임시 폴더(temp)의 모든 파일을 매일 새벽 3시에 지우는 스케쥴러

	@Autowired
	private MyFileUtil myFileUtil;

	@Scheduled(cron = "0 0 3 1/1 * ?") // www.cronmaker.com에서 데일리로 선택 후 cron format에서 복사에서 썼음 맨끝 *는 지워서 쓰기.
	public void execute() { // 메소드명은 아무 의미 없다.

		// 임시 폴더의 File 객체
		File dir = new File(myFileUtil.getTempPath());

		// 임시 폴더가 존재하면 폴더 내의 모든 파일을 가져와서 하나씩 삭제
		if (dir.exists()) { // == true는 생략가능 // 폴더가 있으면
			for (File file : dir.listFiles()) { // 모든거 가져와서
				file.delete(); // 삭제
			}

		}

	}

}
