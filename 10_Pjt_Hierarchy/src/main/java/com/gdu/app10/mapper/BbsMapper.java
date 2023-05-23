package com.gdu.app10.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app10.domain.BbsDTO;

@Mapper
public interface BbsMapper {
  
	public int getBbsCount();
	public List<BbsDTO> getBbsList(Map<String, Object> map);
	public int addBbs(BbsDTO bbsDTO);
	public int removeBbs(int bbsNo);
	public int increaseGroupOrder(BbsDTO bbsDTO);
	public int addReply(BbsDTO replyDTO); // 타입은 못바꾸니까. 이름이라도 바꿔놈
	
}
