package com.westbrain.sandbox.jaxrs.group;

import com.thedeanda.lorem.Lorem;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class GroupRepository {

    private static AtomicLong nextId = new AtomicLong();

    private final Map<Long, Group> groups = new HashMap<Long, Group>();
    private final Map<Long, List<String>> members = new HashMap<Long, List<String>>();

    public Iterable<Group> findAll() {
        return groups.values();
    }

    public Group findOne(Long id) {
        return groups.get(id);
    }

    public Group save(Group group) {
        if (group.getId() == null) {
            group.setId(nextId.incrementAndGet());

        }
        groups.put(group.getId(), group);
        return group;
    }

    public Group delete(Long id) {
        return groups.remove(id);
    }

    /**
     * Create 10 groups
     */
    @PostConstruct
    private void initializeData() {
        for (int i = 0; i < 10; i++) {
            save(new Group(Lorem.getWords(1), Lorem.getWords(7)));
        }
    }


}
