package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.likelion.dailytales.common.BaseAuditEntity;
import net.likelion.dailytales.core.domain.writing.Writing;
import net.likelion.dailytales.core.domain.writing.WritingCommentary;

@Entity(name = "writing_commentary")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WritingCommentaryEntity extends BaseAuditEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "content")
    private String content;

    public WritingCommentary toDomain() {
        return new WritingCommentary(content);
    }

    public WritingCommentaryEntity applyUpdateCommentary(String content) {
        return new WritingCommentaryEntity(
                this.id,
                content
        );
    }

    public static WritingCommentaryEntity from(Writing writing) {
        return new WritingCommentaryEntity(
                writing.id(),
                writing.commentary().content()
        );
    }
}
