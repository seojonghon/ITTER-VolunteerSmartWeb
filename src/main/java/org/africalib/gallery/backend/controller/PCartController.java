package org.africalib.gallery.backend.controller;


import org.africalib.gallery.backend.entity.PCart;
import org.africalib.gallery.backend.entity.PItem;
import org.africalib.gallery.backend.repository.PCartRepository;
import org.africalib.gallery.backend.repository.PItemRepository;
import org.africalib.gallery.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PCartController {

    @Autowired
    JwtService jwtService;

    @Autowired
    PCartRepository PcartRepository;

    @Autowired
    PItemRepository PitemRepository;

    @GetMapping("/api/pcart/pitems")
    public ResponseEntity getCartItems(@CookieValue(value = "token", required = false) String token){

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        List<PCart> pcarts = PcartRepository.findByMemberId(memberId);
        List<Integer> itemIds = pcarts.stream().map(PCart::getItemId).toList();
        List<PItem> pitems = PitemRepository.findByIdIn(itemIds);

        return new ResponseEntity<>(pitems, HttpStatus.OK);
    }

    @PostMapping("/api/pcart/pitems/{itemId}")
    public ResponseEntity pushCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token", required = false) String token
    ) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        PCart pcart = PcartRepository.findByMemberIdAndItemId(memberId, itemId);

        if (pcart == null) {
            PCart newCart = new PCart();
            newCart.setMemberId(memberId);
            newCart.setItemId(itemId);
            PcartRepository.save(newCart);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/pcart/pitems/{itemId}")
    public ResponseEntity removeCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token", required = false) String token
    ){
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        PCart Pcart = PcartRepository.findByMemberIdAndItemId(memberId, itemId);

        PcartRepository.delete(Pcart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
