package com.logBook.LogbookBackend.model;

public record Log(double amount, LogType logType, String date) implements LogInterface {
}
