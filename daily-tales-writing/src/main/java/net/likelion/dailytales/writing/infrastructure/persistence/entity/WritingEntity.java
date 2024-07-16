package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.likelion.dailytales.common.BaseAuditEntity;
import net.likelion.dailytales.core.domain.writing.Visibility;
import net.likelion.dailytales.core.domain.writing.Writing;
import net.likelion.dailytales.writing.application.SimpleWritingDto;

@Entity(name = "writing")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WritingEntity extends BaseAuditEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "writer_id", nullable = false)
    private String writerId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "visibility")
    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    public SimpleWritingDto toDto() {
        return new SimpleWritingDto(
                this.id,
                this.title,
                null,
                this.visibility,
                this.getCreatedAt()
        );
    }

    public static WritingEntity from(Writing writing) {
        return new WritingEntity(
                writing.id(),
                writing.writer().id(),
                writing.title(),
                writing.content(),
                writing.visibility()
        );
    }

    public WritingEntity applyUpdateVisibility(Visibility visibility) {
        return new WritingEntity(
                this.id,
                this.writerId,
                this.title,
                this.content,
                visibility
        );
    }
}
