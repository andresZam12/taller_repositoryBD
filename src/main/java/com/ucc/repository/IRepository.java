package com.ucc.repository;

import java.util.List;
import com.ucc.model.Actor;

public interface IRepository {
    List<Actor> findAll();
    Actor save(Actor actor);
}
