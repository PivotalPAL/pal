package io.pivotal.pal.tracker;


import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private final Map<Long, TimeEntry> repository = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = repository.values().size() + 1L;
        timeEntry.setId(id);
        repository.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return repository.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries = new ArrayList<>();
        timeEntries.addAll(repository.values());
        return timeEntries;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (repository.containsKey(id)) {
            timeEntry.setId(id);
            repository.put(id, timeEntry);
        }
        return timeEntry;
    }

    @Override
    public void delete(long id) {
        repository.remove(id);
    }
}