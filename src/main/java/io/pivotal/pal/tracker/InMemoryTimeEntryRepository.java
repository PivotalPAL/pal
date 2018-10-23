package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    public TimeEntry create(TimeEntry timeEntry) {
        return null;
    }

    @Override
    public TimeEntry find(long l) {

        return null;
    }

    public TimeEntry find(Long id) {
        return null;
    }

    @Override
    public List<TimeEntry> list() {
        return null;
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        return null;
    }

    @Override
    public TimeEntry delete(long l) {
        return null;
    }
}
