package com.payroll.repository;

import com.payroll.model.PayslipRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayslipRecordRepository extends JpaRepository<PayslipRecord, Long> {
    List<PayslipRecord> findTop20ByOrderByGeneratedAtDesc();
}
