package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWritingCollectionId is a Querydsl query type for WritingCollectionId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QWritingCollectionId extends BeanPath<WritingCollectionId> {

    private static final long serialVersionUID = 2110209890L;

    public static final QWritingCollectionId writingCollectionId = new QWritingCollectionId("writingCollectionId");

    public final StringPath userId = createString("userId");

    public final StringPath writingId = createString("writingId");

    public QWritingCollectionId(String variable) {
        super(WritingCollectionId.class, forVariable(variable));
    }

    public QWritingCollectionId(Path<? extends WritingCollectionId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWritingCollectionId(PathMetadata metadata) {
        super(WritingCollectionId.class, metadata);
    }

}

