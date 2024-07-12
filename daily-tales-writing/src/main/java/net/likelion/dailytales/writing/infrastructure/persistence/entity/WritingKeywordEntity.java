package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.likelion.dailytales.common.BaseEntity;

import java.util.List;

@Entity(name = "writing_keyword")
@IdClass(WritingKeywordEntityKey.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WritingKeywordEntity extends BaseEntity {
    @Id
    @Column(name = "writing_id", nullable = false)
    private String writingId;

    @Id
    @Column(name = "keyword", nullable = false)
    private String keyword;

    public static List<WritingKeywordEntity> from(String writingId, List<String> keywords) {
        return keywords.stream()
                .map(keyword -> new WritingKeywordEntity(writingId, keyword))
                .toList();
    }
}
