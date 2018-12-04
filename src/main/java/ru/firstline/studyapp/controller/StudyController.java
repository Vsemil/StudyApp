package ru.firstline.studyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.firstline.studyapp.model.dto.Study;
import ru.firstline.studyapp.service.StudyService;

@RestController
@RequestMapping(value = "study")
public class StudyController {
    @Autowired
    private StudyService studyService;

    @GetMapping(value = "/all")
    public Page<Study> getAll(Pageable pageable,
                              @RequestParam(value="searchWord", required = false) String searchWord) {
        if (searchWord == null) {
            return studyService.findAll(pageable);
        } else {
            return studyService.findAll(pageable, searchWord);
        }
    }

    @GetMapping(value = "/{id}")
    public Study getById(@PathVariable String id) {
        return studyService.findById(id);
    }

    @PostMapping(value = "/save")
    public Study save(@RequestBody Study study) {
        return studyService.save(study);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        studyService.delete(id);
    }

    @PutMapping(value = "/setStatus")
    public void setStatus(@RequestBody Study study){
        studyService.setStatus(study);
    }
}
