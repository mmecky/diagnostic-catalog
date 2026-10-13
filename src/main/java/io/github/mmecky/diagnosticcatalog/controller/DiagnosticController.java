package io.github.mmecky.diagnosticcatalog.controller;

import io.github.mmecky.diagnosticcatalog.model.Entry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class DiagnosticController {

    private static final List<Entry> ENTRIES = List.of(
            new Entry(
                    1L,
                    "ABS FL implausible signal",
                    "The front left ABS sensor ring is dirty",
                    "Clean the front left ABS sensor ring",
                    "ABS Sensor Ring (front left)",
                    Set.of("ABS", "brake", "sensor")
            ),
            new Entry(
                    2L,
                    "ABS FL power interruption",
                    "Power interruption at the front left ABS sensor",
                    "Check cable and plugs",
                    "ABS Sensor (front left)",
                    Set.of("ABS", "brake", "sensor", "cable break", "plug contact")
            ),
            new Entry(
                    3L,
                    "ABS undervoltage",
                    "Problems with the power supply",
                    "Check battery",
                    "ABS ECU",
                    Set.of("ABS", "ECU", "power supply", "corrosion", "plug contact")
            )
    );

    @GetMapping("/entries")
    public List<Entry> getEntries() {
        return ENTRIES;
    }
}
