package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private final CounterService counter;
    private final GaugeService gauge;
    private final TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository, CounterService counter, GaugeService gauge) {

        this.repository = timeEntryRepository;
        this.counter = counter;
        this.gauge=gauge;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = repository.create(timeEntryToCreate);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", repository.list().size());
        ResponseEntity<TimeEntry> response = new ResponseEntity<>(timeEntry, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = repository.find(id);

        if (timeEntry == null){
            return new ResponseEntity<>(new TimeEntry(), HttpStatus.NOT_FOUND);
        }
        counter.increment("TimeEntry.read");
        return ResponseEntity.ok(timeEntry);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        counter.increment("TimeEntry.listed");
        return ResponseEntity.ok(repository.list());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id,@RequestBody  TimeEntry update) {
        TimeEntry timeEntry = repository.update(id, update);
        if (timeEntry == null) {
            return new ResponseEntity<>(update, HttpStatus.NOT_FOUND);
        }
        counter.increment("TimeEntry.updated");
        return ResponseEntity.ok(timeEntry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        TimeEntry timeEntry = repository.find(id);
        repository.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", repository.list().size());
        return new ResponseEntity<>(timeEntry, HttpStatus.NO_CONTENT);
    }
}
