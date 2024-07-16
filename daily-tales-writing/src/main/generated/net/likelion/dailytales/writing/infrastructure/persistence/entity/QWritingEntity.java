package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWritingEntity is a Querydsl query type for WritingEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWritingEntity extends EntityPathBase<WritingEntity> {

    private static final long serialVersionUID = -1999317588L;

    public static final QWritingEntity writingEntity = new QWritingEntity("writingEntity");

    public final net.likelion.dailytales.common.QBaseAuditEntity _super = new net.likelion.dailytales.common.QBaseAuditEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath id = createString("id");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final EnumPath<net.likelion.dailytales.core.domain.writing.Visibility> visibility = createEnum("visibility", net.likelion.dailytales.core.domain.writing.Visibility.class);

    public final StringPath writerId = createString("writerId");

    public QWritingEntity(String variable) {
        super(WritingEntity.class, forVariable(variable));
    }

    public QWritingEntity(Path<? extends WritingEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWritingEntity(PathMetadata metadata) {
        super(WritingEntity.class, metadata);
    }

}

