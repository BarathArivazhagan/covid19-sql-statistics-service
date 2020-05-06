package org.fiftyhands.statistics.app.repository;

import org.fiftyhands.statistics.app.entity.CaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CaseHistoryRepository extends JpaRepository<CaseHistory, String> {

}
