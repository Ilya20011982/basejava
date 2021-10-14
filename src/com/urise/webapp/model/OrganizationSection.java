package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<Experience> Experiences;

    public OrganizationSection(Experience... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Experience> Experiences) {
        Objects.requireNonNull(Experiences, "Experiences must not be null");
        this.Experiences = Experiences;
    }

    public List<Experience> getExperiences() {
        return Experiences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(Experiences, that.Experiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Experiences);
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "Experiences=" + Experiences +
                '}';
    }
}
