package lf.controller;

import lf.dao.QuestionDao;
import lf.dto.QuestionDto;
import lf.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/v1/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody QuestionDto questionDto) {
            Long id = questionService.add(questionDto);
            return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/v1/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody QuestionDto questionDto) {
        questionService.update(questionDto);
        return new ResponseEntity<>("updated successfully.", HttpStatus.OK);
    }

    @GetMapping(value = "/v1/fetchById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        QuestionDto questionDto = questionService.findById(id);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
         questionService.deleteById(id);
        return new ResponseEntity<>("deleted successfully.", HttpStatus.OK);
    }

}
