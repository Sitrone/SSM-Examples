package com.sununiq.github.common;

import org.hibernate.Session;

public interface HOperation {
    void doAction(Session session);
}
