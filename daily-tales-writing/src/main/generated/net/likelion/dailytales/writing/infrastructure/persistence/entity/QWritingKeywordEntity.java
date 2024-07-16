package net.likelion.dailytales.writing.infrastructure.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWritingKeywordEntity is a Querydsl query type for WritingKeywordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWritingKeywordEntity extends EntityPathBase<WritingKeywordEntity> {

    private static final long serialVersionUID = -972143293L;

    public static final QWritingKeywordEntity writingKeywordEntity = new QWritingKeywordEntity("writingKeywordEntity");

    public final net.likelion.dailytales.common.QBaseEntity _super = new net.likelion.dailytales.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath keyword = createString("keyword");

    public final StringPath writingId = createString("writingId");

    public QWritingKeywordEntity(String variable) {
        super(WritingKeywordEntity.class, forVariable(variable));
    }

    public QWritingKeywordEntity(Path<? extends WritingKeywordEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWritingKeywordEntity(PathMetadata metadata) {
        super(WritingKeywordEntity.class, metadata);
    }

}

