insert into component (id, name)
values (1, 'Power supply'),
       (2, 'Sensor board'),
       (3, 'Cooling'),
       (4, 'Display');

insert into entry (id, symptom, cause, remedy, component_id)
values (1,
        'Unit does not power on after cold storage',
        'Condensation on the main connector causes intermittent contact',
        'Dry the connector, apply contact grease, re-seat the plug',
        1),
       (2,
        'Readings drift after warm-up',
        'Reference voltage depends on temperature',
        'Replace the reference and recalibrate',
        2),
       (3,
        'Unit shuts down under load',
        'Fan is blocked by dust, housing overheats',
        'Clean the fan and the air inlet',
        3),
       (4,
        'Display flickers at low brightness',
        'Dimming signal is too slow for the panel',
        'Raise the minimum brightness setting',
        4),
       (5,
        'Unit restarts when the load is switched on',
        'Supply voltage drops below the threshold for a moment',
        'Replace the buffer capacitor',
        1);

insert into entry_tags (entry_id, tags)
values (1, 'cold-start'),
       (1, 'connector'),
       (1, 'intermittent'),
       (2, 'drift'),
       (2, 'temperature'),
       (3, 'dust'),
       (3, 'overheating'),
       (4, 'brightness'),
       (4, 'flicker'),
       (5, 'intermittent'),
       (5, 'restart');

alter table component alter column id restart with 100;
alter table entry alter column id restart with 100;
