package com.example.fabrice.diary;

import java.io.Serializable;

public class DiaryEntry implements Serializable{

    private String title;
    private String content;
    private String recordedDate;

    public DiaryEntry(String title, String content, String recordedDate) {
        this.title = title;
        this.content = content;
        this.recordedDate = recordedDate;
    }

    public String getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(String recordedDate) {
        this.recordedDate = recordedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
