package com.ucc.repository;

import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;
import com.ucc.model.Actor;

public interface IRepository {
    List<Actor> findAll() throws SQLException;
    Actor save(Actor actor) throws SQLException;
}
