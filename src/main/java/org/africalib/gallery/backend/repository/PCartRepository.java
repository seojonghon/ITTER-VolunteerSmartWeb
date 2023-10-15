package org.africalib.gallery.backend.repository;

import org.africalib.gallery.backend.entity.PCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PCartRepository extends JpaRepository<PCart, Integer> {

    List<PCart> findByMemberId(int memberId);
    PCart findByMemberIdAndItemId(int memberId, int itemId);

    void deleteByMemberId(int memberId);
}
