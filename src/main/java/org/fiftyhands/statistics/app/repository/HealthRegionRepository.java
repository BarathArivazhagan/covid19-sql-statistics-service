package org.fiftyhands.statistics.app.repository;

import java.util.List;
import java.util.Optional;

import org.fiftyhands.statistics.app.entity.HealthRegion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRegionRepository extends JpaRepository<HealthRegion, Long> {
	
	List<HealthRegion> findByProvinceProvinceId(Integer provinceId);

	Optional<HealthRegion> findByNameAndProvinceProvinceId(String healthRegion, Integer provinceId);
	

}
