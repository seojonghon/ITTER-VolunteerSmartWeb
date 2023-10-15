package org.africalib.gallery.backend.controller;

import org.africalib.gallery.backend.entity.PItem;
import org.africalib.gallery.backend.repository.PItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PItemController {

    @Autowired
    PItemRepository PitemRepository;

    @GetMapping("/api/pitems")
    public List<PItem> getItems() {
        List<PItem> Pitems = PitemRepository.findAll();
        return Pitems;
    }
}
