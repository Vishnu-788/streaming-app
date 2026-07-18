package com.vishnu.io.streamapp.video.internal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface VideoRepository extends JpaRepository<VideoEntity, String> {
   Optional<VideoEntity> findByTitle(String title);
}
