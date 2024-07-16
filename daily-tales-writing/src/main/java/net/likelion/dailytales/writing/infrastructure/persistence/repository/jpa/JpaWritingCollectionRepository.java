package net.likelion.dailytales.writing.infrastructure.persistence.repository.jpa;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.writing.api.dto.response.GetWritingCollectionListResponse;
import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingCollectionEntity;
import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingCollectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface JpaWritingCollectionRepository extends JpaRepository<WritingCollectionEntity, WritingCollectionId> {

    List<WritingCollectionEntity> findAllByWritingCollectionId_UserId(String userId);
    @Query("SELECT new net.likelion.dailytales.writing.api.dto.response.GetWritingCollectionListResponse(" +
            "w.id, " +
            "w.title, " +
            "u.nickname, " +
            "u.profileImageUrl, " +
            "w.createdAt) " +
            "FROM writing w " +
            "JOIN users u ON w.writerId = u.id " +
            "WHERE w.id IN :writerId")
    List<GetWritingCollectionListResponse> findAllWritingsWithUserInfo(@Param("writerId") List<String> writerId); // writerId를 메서드 파라미터로 추가



}