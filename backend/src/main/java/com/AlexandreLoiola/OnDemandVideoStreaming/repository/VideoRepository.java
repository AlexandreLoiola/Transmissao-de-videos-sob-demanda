package com.AlexandreLoiola.OnDemandVideoStreaming.repository;

import com.AlexandreLoiola.OnDemandVideoStreaming.model.VideoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<VideoModel, UUID> {
    Optional<VideoModel> findByTitleAndIsActiveTrue(String title);
    Set<VideoModel> findByIsActiveTrue();
    Set<VideoModel> findByFolderAndIsActiveTrue(String folder);
}
