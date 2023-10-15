package org.africalib.gallery.backend.repository;

import org.africalib.gallery.backend.entity.PItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PItemRepository extends JpaRepository<PItem, Integer> {

    List<PItem> findByIdIn(List<Integer> ids);
}
