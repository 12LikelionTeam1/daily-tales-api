package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWritingCollectionEntity is a Querydsl query type for WritingCollectionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWritingCollectionEntity extends EntityPathBase<WritingCollectionEntity> {

    private static final long serialVersionUID = -1184589142L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWritingCollectionEntity writingCollectionEntity = new QWritingCollectionEntity("writingCollectionEntity");

    public final QWritingCollectionId writingCollectionId;

    public QWritingCollectionEntity(String variable) {
        this(WritingCollectionEntity.class, forVariable(variable), INITS);
    }

    public QWritingCollectionEntity(Path<? extends WritingCollectionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWritingCollectionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWritingCollectionEntity(PathMetadata metadata, PathInits inits) {
        this(WritingCollectionEntity.class, metadata, inits);
    }

    public QWritingCollectionEntity(Class<? extends WritingCollectionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.writingCollectionId = inits.isInitialized("writingCollectionId") ? new QWritingCollectionId(forProperty("writingCollectionId")) : null;
    }

}

