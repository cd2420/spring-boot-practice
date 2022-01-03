package com.example.point.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPointReservation is a Querydsl query type for PointReservation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPointReservation extends EntityPathBase<PointReservation> {

    private static final long serialVersionUID = -875854760L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointReservation pointReservation = new QPointReservation("pointReservation");

    public final com.example.point.entity.common.QIdEntity _super = new com.example.point.entity.common.QIdEntity(this);

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final NumberPath<Integer> availableDays = createNumber("availableDays", Integer.class);

    public final DatePath<java.time.LocalDate> earnedDate = createDate("earnedDate", java.time.LocalDate.class);

    public final BooleanPath executed = createBoolean("executed");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QPointWallet pointWallet;

    public QPointReservation(String variable) {
        this(PointReservation.class, forVariable(variable), INITS);
    }

    public QPointReservation(Path<? extends PointReservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPointReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPointReservation(PathMetadata metadata, PathInits inits) {
        this(PointReservation.class, metadata, inits);
    }

    public QPointReservation(Class<? extends PointReservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pointWallet = inits.isInitialized("pointWallet") ? new QPointWallet(forProperty("pointWallet")) : null;
    }

}

