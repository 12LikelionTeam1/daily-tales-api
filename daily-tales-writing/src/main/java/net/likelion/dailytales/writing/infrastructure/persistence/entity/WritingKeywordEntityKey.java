package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WritingKeywordEntityKey {
    private String writingId = "";
    private String keyword = "";
}
