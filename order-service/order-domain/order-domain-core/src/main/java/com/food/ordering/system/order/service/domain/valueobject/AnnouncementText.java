package com.food.ordering.system.order.service.domain.valueobject;

import java.util.Objects;

public class AnnouncementText {
    private final String text;

    public AnnouncementText(String text) {
        this.text = text != null ? text : "";
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementText that = (AnnouncementText) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "AnnouncementText{" +
                "text='" + text + '\'' +
                '}';
    }
}
