package org.fiftyhands.statistics.app.service;

import java.util.List;

import org.fiftyhands.statistics.app.dto.CaseHistorySource;


public interface CaseHistoryService {
	
	void saveCaseHistories(List<CaseHistorySource> sources);
	
	Long getCaseHistoryCount();


}
