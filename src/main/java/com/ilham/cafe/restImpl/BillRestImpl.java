package com.ilham.cafe.restImpl;

import com.ilham.cafe.POJO.Bill;
import com.ilham.cafe.constants.CafeConstants;
import com.ilham.cafe.dto.BillResponseDTO;
import com.ilham.cafe.rest.BillRest;
import com.ilham.cafe.service.BillService;
import com.ilham.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BillRestImpl implements BillRest {

    @Autowired
    BillService billService;

    @Override
    public ResponseEntity<BillResponseDTO> generateReport(Map<String, Object> requestMap) {
        try {
            return billService.generateReport(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new BillResponseDTO(CafeConstants.SOMETHING_WENT_WRONG, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Bill>> getBills() {
        try {
            return billService.getBills();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Bill>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        try {
            return billService.getPdf(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<BillResponseDTO> deleteBill(Integer id) {
        try {
            return billService.deleteBill(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new BillResponseDTO(CafeConstants.SOMETHING_WENT_WRONG, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
