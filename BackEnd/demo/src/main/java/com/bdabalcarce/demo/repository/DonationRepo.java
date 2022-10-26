package com.bdabalcarce.demo.repository;
import com.bdabalcarce.demo.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DonationRepo extends JpaRepository<Donation, Integer> {
}