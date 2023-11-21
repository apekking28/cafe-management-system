package com.ilham.cafe.service;

import com.ilham.cafe.POJO.Bill;
import com.ilham.cafe.dto.BillResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BillService {
    ResponseEntity<BillResponseDTO> generateReport(Map<String, Object> requestMap);

    ResponseEntity<List<Bill>> getBills();

    ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

    ResponseEntity<BillResponseDTO> deleteBill(Integer id);
}
