package org.fiftyhands.statistics.app.repository;

import org.fiftyhands.statistics.app.entity.CovidMortalityRawData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidMortalityRawDataRepository extends JpaRepository<CovidMortalityRawData, Long> {

}
