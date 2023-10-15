package org.africalib.gallery.backend.controller;

import org.africalib.gallery.backend.entity.Board;
import org.africalib.gallery.backend.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {  //BoardController
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }


    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable int id, @RequestBody Board board) {
        if (!boardService.getBoardById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        board.setId(id);
        Board updatedBoard = boardService.saveBoard(board);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable int id) {
        if (!boardService.getBoardById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}

