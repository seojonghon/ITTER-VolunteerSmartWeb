package org.africalib.gallery.backend.service;

import org.africalib.gallery.backend.entity.Board;
import org.africalib.gallery.backend.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService { // BoardService
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> getBoardById(int id) {
        return boardRepository.findById(id);
    }

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }
}
