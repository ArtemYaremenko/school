-- liquibase formatted sql

-- changeset yaremenko:1

CREATE INDEX student_name_index ON student (name);

CREATE INDEX faculty_name_color_index ON faculty (name, color);