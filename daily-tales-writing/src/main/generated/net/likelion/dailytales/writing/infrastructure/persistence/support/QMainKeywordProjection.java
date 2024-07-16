package net.likelion.dailytales.writing.infrastructure.persistence.support;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * net.likelion.dailytales.writing.infrastructure.persistence.support.QMainKeywordProjection is a Querydsl Projection type for MainKeywordProjection
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainKeywordProjection extends ConstructorExpression<MainKeywordProjection> {

    private static final long serialVersionUID = -662671960L;

    public QMainKeywordProjection(com.querydsl.core.types.Expression<String> keyword, com.querydsl.core.types.Expression<Long> frequency) {
        super(MainKeywordProjection.class, new Class<?>[]{String.class, long.class}, keyword, frequency);
    }

}

