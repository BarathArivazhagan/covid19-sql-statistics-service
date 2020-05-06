package org.fiftyhands.statistics.app.service;

import java.util.List;

import org.fiftyhands.statistics.app.dto.CovidMortalityDTO;
import org.fiftyhands.statistics.app.entity.CovidMortalityRawData;

public interface CovidMortalityRawDataService {

	void saveMortalitySources(List<CovidMortalityDTO> sources);

	CovidMortalityRawData saveCovidMortalityRawData(CovidMortalityRawData data);

	List<CovidMortalityRawData> saveCovidMortalityRawDatas(List<CovidMortalityRawData> datas);

	Long getCovidMortalityCount();

}