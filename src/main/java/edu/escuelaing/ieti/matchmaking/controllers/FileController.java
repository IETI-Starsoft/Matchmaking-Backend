package edu.escuelaing.ieti.matchmaking.controllers;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @GetMapping("/{userId}/{filename}")
    public ResponseEntity<InputStreamResource> getFileByName(@PathVariable String filename, @PathVariable String userId) throws IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is(userId + filename)));
        if (file == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GridFsResource resource = gridFsTemplate.getResource(file.getFilename());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(resource.getContentType()))
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId ,RedirectAttributes redirectAttributes) throws IOException {
        String fileName = userId + file.getOriginalFilename();
        gridFsTemplate.store(file.getInputStream(), fileName, file.getContentType());
        return fileName;
    }

}
