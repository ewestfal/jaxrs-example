package com.westbrain.sandbox.jaxrs.group;

/**
 * A simple model representing a single member of a group.
 *
 * @see com.westbrain.sandbox.jaxrs.group.Group
 *
 * @author Eric Westfall (ewestfal@gmail.com)
 */
public class Member {

    private Long id;
    private String name;

    public Member() {}

    public Member(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
