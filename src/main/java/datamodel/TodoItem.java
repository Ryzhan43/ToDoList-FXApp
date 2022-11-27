package datamodel;

import java.time.LocalDate;

public class TodoItem {
    private String shortDescription;
    private String details;
    private LocalDate deadLine;

    public TodoItem(String shortDescription, String deteils, LocalDate deadLine) {
        this.shortDescription = shortDescription;
        this.details = deteils;
        this.deadLine = deadLine;
    }


    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDeteils(String deteils) {
        this.details = deteils;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }


    public String getShortDescription() {
        return shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    @Override
    public String toString() {
        return  shortDescription;
    }
}
