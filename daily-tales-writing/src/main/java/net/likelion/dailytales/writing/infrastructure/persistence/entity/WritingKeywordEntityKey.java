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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WritingKeywordEntityKey that = (WritingKeywordEntityKey) o;

        if (!writingId.equals(that.writingId)) return false;
        return keyword.equals(that.keyword);
    }

    @Override
    public int hashCode() {
        int result = writingId.hashCode();
        result = 31 * result + keyword.hashCode();
        return result;
    }
}
