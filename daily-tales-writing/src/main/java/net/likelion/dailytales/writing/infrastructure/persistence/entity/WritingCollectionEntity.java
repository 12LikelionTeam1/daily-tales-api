package net.likelion.dailytales.writing.infrastructure.persistence.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class WritingCollectionEntity {

    @EmbeddedId
    private WritingCollectionId writingCollectionId;
    @Builder
    public WritingCollectionEntity(WritingCollectionId writingCollectionId){
        this.writingCollectionId=writingCollectionId;
    }

}
