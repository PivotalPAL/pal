package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TimeEntryRepository {
    TimeEntry create(TimeEntry timeEntry);
    TimeEntry find(Long id);
    List<TimeEntry> list();
    TimeEntry update(long id, TimeEntry timeEntry);
    void delete(long id);

}
