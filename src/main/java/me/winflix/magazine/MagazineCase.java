package me.winflix.magazine;

public class MagazineCase {
    String note;
    String magazine;
    boolean expected;

    public MagazineCase() {
    }

    public MagazineCase(String note, String magazine, boolean expected) {
        this.note = note;
        this.magazine = magazine;
        this.expected = expected;
    }

    public String getMagazine() {
        return magazine;
    }

    public String getNote() {
        return note;
    }

    public boolean isExpected() {
        return expected;
    }
}
