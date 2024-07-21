package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WritingCollectionEntityKey {
    private String userId = "";
    private String writingId = "";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WritingCollectionEntityKey that = (WritingCollectionEntityKey) o;

        if (!userId.equals(that.userId)) return false;
        return writingId.equals(that.writingId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + writingId.hashCode();
        return result;
    }
}
