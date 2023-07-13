package lf.controller;

import lf.dto.AnswerDto;
import lf.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping(value = "/v1/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody AnswerDto answerDto) {
        Long id = answerService.add(answerDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/v1/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody AnswerDto answerDto) {
        answerService.update(answerDto);
        return new ResponseEntity<>("updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        answerService.deleteById(id);
        return new ResponseEntity<>("deleted successfully.", HttpStatus.OK);
    }

    /*    @GetMapping(value = "/v1/fetchById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        QuestionDto questionDto = answerService.findById(id);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }*/
}
