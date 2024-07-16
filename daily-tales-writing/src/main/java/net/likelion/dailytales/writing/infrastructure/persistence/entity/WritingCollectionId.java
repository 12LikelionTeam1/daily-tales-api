package net.likelion.dailytales.writing.infrastructure.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class WritingCollectionId implements Serializable {
    @Column(name="user_id")
    private String userId;

    @Column(name="writing_id")
    private String writingId;

    @Builder
    public WritingCollectionId(String userid,String writingId){
        this.userId=userid;
        this.writingId=writingId;
    }

}
