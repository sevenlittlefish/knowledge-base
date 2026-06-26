package com.sevenlittlefish.knowledge.controller;

import com.sevenlittlefish.knowledge.service.StudyNotesService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("studyNotes")
public class StudyNotesController {

    @Resource
    private StudyNotesService studyNotesService;

    @PostMapping("loadNote")
    public void loadNote(String filePath) {
        studyNotesService.loadNote(filePath);
    }
}
