package com.westbrain.sandbox.jaxrs.group;

import com.thedeanda.lorem.Lorem;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A "fake" in-memory repository for Group data.
 *
 * @auhor Eric Westfall (ewestfal@gmail.com)
 */
@Repository
public class GroupRepository {

    private static Random random = new Random();
    private static AtomicLong nextGroupId = new AtomicLong();
    private static AtomicLong nextMemberId = new AtomicLong();

    private final ConcurrentHashMap<Long, Group> groups = new ConcurrentHashMap<Long, Group>();
    private final ConcurrentHashMap<Long, Map<Long, Member>> members = new ConcurrentHashMap<Long, Map<Long, Member>>();

    public Iterable<Group> findAll() {
        return groups.values();
    }

    public Group findOne(Long id) {
        return groups.get(id);
    }

    public Group save(Group group) {
        if (group.getId() == null) {
            group.setId(nextGroupId.incrementAndGet());

        }
        groups.put(group.getId(), group);
        return group;
    }

    public Group delete(Long id) {
        return groups.remove(id);
    }

    public Member saveMember(Long groupId, Member member) {
        if (groupId == null) {
            throw new IllegalArgumentException("groupId was null");
        }
        this.members.putIfAbsent(groupId, new ConcurrentHashMap<Long, Member>());
        Map<Long, Member> groupMembers = this.members.get(groupId);
        if (member.getId() != null) {
            // if the id is not null, it's an update
            if (!groupMembers.containsKey(member.getId())) {
                throw new IllegalArgumentException("Tried to provide a member id for a member that doesn't exist");
            }
        } else {
            member.setId(nextMemberId.incrementAndGet());
        }
        groupMembers.put(member.getId(), member);
        return member;
    }

    public Iterable<Member> findMembers(Long groupId) {
        Map<Long, Member> groupMembers = members.get(groupId);
        if (groupMembers == null) {
            return null;
        }
        return groupMembers.values();
    }

    public Member findMember(Long groupId, Long memberId) {
        Map<Long, Member> groupMembers = this.members.get(groupId);
        if (groupMembers == null) {
            return null;
        }
        return groupMembers.get(memberId);
    }

    public Member deleteMember(Long id, Long memberId) {
        Map<Long, Member> groupMembers = members.get(id);
        if (groupMembers == null) {
            return null;
        }
        return groupMembers.remove(memberId);
    }

    /**
     * Create 10 groups each with 1-15 members
     */
    @PostConstruct
    private void initializeData() {
        for (int i = 0; i < 10; i++) {
            Group group = save(new Group(Lorem.getWords(1), Lorem.getWords(7)));
            int membersToGenerate = random.nextInt(15) + 1;
            for (int j = 0; j < membersToGenerate; j++) {
                saveMember(group.getId(), new Member(Lorem.getFirstName()));
            }
        }

    }


}
