package com.vishnu.io.streamapp.video.internal;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vishnu.io.streamapp.video.VideoRequestDto;
import com.vishnu.io.streamapp.video.VideoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
class VideoController {

   private final VideoService service;

   @PostMapping
   public ResponseEntity<String> create(
         @RequestParam("file") MultipartFile videoFile,
         @RequestParam("title") String title,
         @RequestParam("genre") Optional<String> genre,
         @RequestParam("description") String description) {

      VideoRequestDto videoMeta = new VideoRequestDto(title, description, genre);
      service.saveVideo(videoMeta, videoFile);
      return ResponseEntity
            .status(201)
            .body("Video Saved Successfully");
   }
}
