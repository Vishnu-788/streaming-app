package com.vishnu.io.streamapp.video;

import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VideoRequestDto {
   private final String title;
   private final String description;
   private final Optional<String> genre;
}
