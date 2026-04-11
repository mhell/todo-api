package se.lexicon.todo_app.dto;

public record TodoStatsDto(
    long completed,
    long pending,
    long overdue,
    long inProgress
) {}
