package com.gdu.moovod.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.moovod.domain.AttachDTO;
import com.gdu.moovod.domain.CsDTO;

@Mapper
public interface CsMapper {
	
	// getCsCount
	public int getCsCount();
	public List<CsDTO> getCsList(Map<String, Object> map);
	
	// addCs
	public int addCs(CsDTO csDTO);
	public int addAttach(AttachDTO attachDTO);
	
	// getCsByNo
	public CsDTO getCsByNo(int csNo);
	public List<AttachDTO> getAttachList(int csNo);
	
	// display, download
	public AttachDTO getAttachByNo(int attachNo);
	
	// download
	public int increaseDownloadCount(int attachNo);
	
	// downloadAll
	// public List<AttachDTO> getAttachList(int uploadNo);
	
	// removeCs
	public int removeCs(int csNo);
	
  //modifyUpload
  public int modifyCs(CsDTO csDTO);
 
  // removeAttach
  public int removeAttach(int attachNo);
 
  // RemoveWrongfileScheduler
  public List<AttachDTO> getAttachListInYesterday();
 
}