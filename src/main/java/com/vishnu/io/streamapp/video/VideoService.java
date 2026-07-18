package com.vishnu.io.streamapp.video;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
   void saveVideo(VideoRequestDto videoMeta, MultipartFile videoFile);

   VideoResponseDto getVideoByID(String videoId);

   VideoResponseDto getVideoByTitle(String videoTitle);
}
