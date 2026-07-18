package com.vishnu.io.streamapp.video.internal;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "videos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
class VideoEntity {

   @Id
   private UUID id;

   @Column(name = "title", nullable = false)
   private String title;

   @Column(name = "description", nullable = false)
   private String description;

   @Column(name = "content-type", nullable = false)
   private String contentType;

   @Column(name = "genre", nullable = true)
   private String genre;

   @Column(name = "filepath", nullable = false)
   private String filepath;
}
