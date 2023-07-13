package lf.controller;

import lf.dto.QuestionDto;
import lf.dto.TagDto;
import lf.service.QuestionService;
import lf.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {


    @Autowired
    private TagService tagService;

    @PostMapping(value = "/v1/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody TagDto tagDto) {
        Long id = tagService.add(tagDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/v1/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody TagDto tagDto) {
        tagService.update(tagDto);
        return new ResponseEntity<>("updated successfully.", HttpStatus.OK);
    }

    @GetMapping(value = "/v1/fetchById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        TagDto questionDto = tagService.findById(id);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        tagService.deleteById(id);
        return new ResponseEntity<>("deleted successfully.", HttpStatus.OK);
    }

}
