package org.fiftyhands.statistics.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="case_entity")
public class CaseEntity {
	
	@Id
	@Column(name="case_id")
	private Long caseId;

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public CaseEntity() {
		super();

	}
	
	

}
