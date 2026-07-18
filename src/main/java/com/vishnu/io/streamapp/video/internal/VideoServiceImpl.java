package com.vishnu.io.streamapp.video.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vishnu.io.streamapp.video.VideoRequestDto;
import com.vishnu.io.streamapp.video.VideoResponseDto;
import com.vishnu.io.streamapp.video.VideoService;

import jakarta.annotation.PostConstruct;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
class VideoServiceImpl implements VideoService {

   @Value("${files.video}")
   String FILE_DIR;

   @PostConstruct
   public void init() {
      File file = new File(FILE_DIR);

      if (!file.exists()) {
         file.mkdir();
         LOGGER.info("Folder created.");
      } else {
         LOGGER.info("Folder for storing videos exist.");
      }
   }

   private static final Logger LOGGER = LogManager.getLogger();
   private final VideoRepository repo;

   VideoServiceImpl(VideoRepository repo) {
      this.repo = repo;
   }

   @Override
   public void saveVideo(VideoRequestDto dto, MultipartFile videoFile) {
      // Will fill the title, descirption using the dto.
      VideoEntity videoEntity = dtoToEntity(dto);

      String orgFileName = videoFile.getOriginalFilename();
      String contentType = videoFile.getContentType();

      videoEntity.setId(UUID.randomUUID());
      videoEntity.setContentType(contentType);
      videoEntity.setGenre(dto.getGenre().orElse(null));

      try {
         InputStream inStream = videoFile.getInputStream();
         Path path = Paths.get(FILE_DIR, orgFileName);
         LOGGER.info("PATH: " + path);

         Files.copy(inStream, path, StandardCopyOption.REPLACE_EXISTING);
         videoEntity.setFilepath(path.toString());
         repo.save(videoEntity);

      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   @Override
   public VideoResponseDto getVideoByID(String videoId) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getVideoByID'");
   }

   @Override
   public VideoResponseDto getVideoByTitle(String videoTitle) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getVideoByTitle'");
   }

   // Utility Methods.
   public VideoEntity dtoToEntity(VideoRequestDto dto) {
      VideoEntity entity = new VideoEntity();
      entity.setTitle(dto.getTitle());
      entity.setDescription(dto.getDescription());
      return entity;
   }

   public VideoResponseDto EntityToDto(VideoEntity en) {
      return new VideoResponseDto(
            en.getId(),
            en.getTitle(),
            en.getDescription(),
            Optional.ofNullable(en.getGenre()),
            en.getContentType(),
            en.getFilepath());
   }
}
