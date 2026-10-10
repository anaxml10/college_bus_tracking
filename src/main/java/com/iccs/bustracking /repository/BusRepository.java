
package com.iccs.bustracking.repository;

import com.iccs.bustracking.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, String> {
}
