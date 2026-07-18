package com.vishnu.io.streamapp.video;

import java.util.Optional;
import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VideoResponseDto {
   private final UUID id;
   private final String title;
   private final String description;
   private final Optional<String> genre;
   private final String contentType;
   private final String filepath;
}
