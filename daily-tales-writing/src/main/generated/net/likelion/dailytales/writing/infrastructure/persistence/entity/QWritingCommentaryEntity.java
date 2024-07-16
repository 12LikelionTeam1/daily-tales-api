package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWritingCommentaryEntity is a Querydsl query type for WritingCommentaryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWritingCommentaryEntity extends EntityPathBase<WritingCommentaryEntity> {

    private static final long serialVersionUID = -889343115L;

    public static final QWritingCommentaryEntity writingCommentaryEntity = new QWritingCommentaryEntity("writingCommentaryEntity");

    public final net.likelion.dailytales.common.QBaseAuditEntity _super = new net.likelion.dailytales.common.QBaseAuditEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath id = createString("id");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QWritingCommentaryEntity(String variable) {
        super(WritingCommentaryEntity.class, forVariable(variable));
    }

    public QWritingCommentaryEntity(Path<? extends WritingCommentaryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWritingCommentaryEntity(PathMetadata metadata) {
        super(WritingCommentaryEntity.class, metadata);
    }

}

