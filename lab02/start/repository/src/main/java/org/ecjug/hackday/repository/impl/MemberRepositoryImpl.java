package org.ecjug.hackday.repository.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import org.ecjug.hackday.domain.model.Member;
import org.ecjug.hackday.repository.MemberRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

/**
 * 
 * @author ecabrerar
 * @date Aug 2, 2019
 *
 */

@ApplicationScoped
public class MemberRepositoryImpl implements MemberRepository {

    @Inject
    private MongoDatabase database;

    private MongoCollection<Member> collection;

    @Override
    public Member add(Member member) {
        Objects.requireNonNull(member, "Member can't be null");
        member.setId(new ObjectId(new Date()));
        dbCollection().insertOne(member);
        return member;
    }

    @Override
    public List<Member> memberByName(String name) {
        Objects.requireNonNull(name, "Name can't be null");
        return dbCollection().find(Filters.regex("name", name)).into(new ArrayList<>());
    }

    @Override
    public Member byId(String id) {
        return dbCollection().find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public List<Member> list() {
        List<Member> memberList = new ArrayList<>();
        MongoCursor<Member> mongoCursor = dbCollection().find().iterator();
        mongoCursor.forEachRemaining(memberList::add);
        return memberList;
    }


    private MongoCollection<Member> dbCollection() {
        if (this.collection == null) {
            this.collection = this.database.getCollection("Member", Member.class);
        }
        return this.collection;
    }
}