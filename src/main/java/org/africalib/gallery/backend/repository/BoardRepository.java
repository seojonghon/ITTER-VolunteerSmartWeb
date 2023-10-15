package org.africalib.gallery.backend.repository;


import org.africalib.gallery.backend.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Integer> {

    Board findByEmailAndPassword(String email, String password);

}
