package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "writing_collection")
@IdClass(WritingCollectionEntityKey.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WritingCollectionEntity {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "writing_id")
    private String writingId;

}
