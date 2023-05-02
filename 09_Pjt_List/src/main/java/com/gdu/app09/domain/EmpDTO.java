package com.gdu.app09.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private double salary;
	private double commissionPct;
	private int managerId;
//	private int departmentId;   // 원래 이랬지만, join하려면 아래처럼??
	private DeptDTO deptDTO; 		//DTO로 갈아끼우기. // 필드 이름은  jsp에서 사용된다.

}
