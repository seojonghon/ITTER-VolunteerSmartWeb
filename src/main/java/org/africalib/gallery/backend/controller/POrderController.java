package org.africalib.gallery.backend.controller;

import org.africalib.gallery.backend.dto.POrderDto;
import org.africalib.gallery.backend.entity.POrder;
import org.africalib.gallery.backend.repository.PCartRepository;
import org.africalib.gallery.backend.repository.POrderRepository;
import org.africalib.gallery.backend.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class POrderController {

    @Autowired
    POrderRepository PorderRepository;

    @Autowired
    PCartRepository PcartRepository;

    @Autowired
    JwtService jwtService;

    @GetMapping("/api/porders")
    public ResponseEntity getOrder(
            @CookieValue(value = "token", required = false) String token
    ) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        List<POrder> porders = PorderRepository.findByMemberIdOrderByIdDesc(memberId);
        return new ResponseEntity<>(porders, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/api/porders")
    public ResponseEntity pushOrder(
            @RequestBody POrderDto dto,
            @CookieValue(value = "token", required = false) String token
    ) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        POrder newOrder = new POrder();

        newOrder.setMemberId(memberId);
        newOrder.setName(dto.getName());
        newOrder.setAddress(dto.getAddress());
        newOrder.setPayment(dto.getPayment());
        newOrder.setCardNumber(dto.getCardNumber());
        newOrder.setPitems(dto.getPitems());
        PorderRepository.save(newOrder);
        PcartRepository.deleteByMemberId(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
