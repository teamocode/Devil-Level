package com.shredquest.zombiegame.application.logic;

import com.shredquest.zombiegame.application.entities.EntityManager;
import com.shredquest.zombiegame.application.input.Input;

public interface Logic {
    void govern(EntityManager entityManager, Input input);
}
