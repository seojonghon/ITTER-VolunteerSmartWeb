package org.africalib.gallery.backend.repository;


import org.africalib.gallery.backend.entity.POrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface POrderRepository extends JpaRepository<POrder, Integer> {

    List<POrder> findByMemberIdOrderByIdDesc(int memberId);
}
