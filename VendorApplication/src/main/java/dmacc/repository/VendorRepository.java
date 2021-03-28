package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
}